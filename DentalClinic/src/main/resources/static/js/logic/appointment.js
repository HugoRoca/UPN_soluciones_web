let isNewAppointment = false;
let idPatient = 0;
let idDoctor = 0;
let patients = [];
let doctors = [];
let table;

function editAppointment(data) {
    isNewAppointment = false;

    idPatient = data.patient.id;
    idDoctor = data.doctor.doctorId;

    $("#appointmentDate").val(data.appointmentDate);
    $("#subject").val(data.subject);
    $("#status").val(data.status);
    $("#type").val(data.type);

    $('#appointmentModal').modal('show');
}

$(document).ready(function () {
    table = loadTable();
    loadDoctors();
    loadPatients();

    $('#saveAppointment').on('click', function () {
        const id = $('#appointmentId').val();
        const appointmentData = {
            patient: {id: $("#patientSelect").val()},
            doctor: {doctorId: $("#doctorSelect").val()},
            appointmentDate: $("#appointmentDate").val(),
            subject: $("#subject").val(),
            status: $("#status").val(),
            type: $("#type").val()
        };
      
        if (isNewAppointment) {
            createAppointment(appointmentData);
        } else {
            updateAppointment(id, appointmentData);
        }
    });

    $(document).on('click', '#add-appointment', function () {
        isNewAppointment = true;
        $('#appointmentModal').modal('show');
    });

    $(document).on('click', '.edit-btn', function () {
        const data = JSON.parse($(this).attr('data-info'));
        $("#appointmentId").val(data.appointmentId);
        editAppointment(data);
    });

    $(document).on('click', '.delete-btn', function () {
        const appointmentId = $(this).data('id');

        $('#idToDelete').val(appointmentId);

        $('.text-custom-modal').html(`¿Estás seguro de que deseas eliminar este registro?`);

        const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
        modal.show();
    });

    $('#confirmDelete').click(function () {
        const id = $('#idToDelete').val();

        deleteAppointment(id);
    });

    $('#statusFilter').on('change', function () {
        table.column(6).search(this.value).draw();
    });

    $('#typeFilter').on('change', function () {
        table.column(1).search(this.value).draw();
    });

    $('#appointmentModal').on('shown.bs.modal', function () {
        actionsOnOpenModal();
    });

    $('#appointmentModal').on('hidden.bs.modal', function () {
        $("#patientSelect").select2('destroy');
        idPatient = 0;
        idDoctor = 0;
    });
});

function loadTable() {
    return $('#appointmentsTable').DataTable({
        ajax: {
            url: '/api/appointment',
            dataSrc: ''
        },
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: ''
            },
            {data: "type"},
            {data: "subject"},
            {
                "data": null,
                "render": function (data, type, row) {
                    return `${row.patient.firstName} ${row.patient.lastName}`;
                }
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    return `${row.doctor.firstName} ${row.doctor.lastName}`;
                }
            },
            {data: 'appointmentDate'},
            {data: 'status'},
            {
                data: null,
                render: function (data) {
                    return `<button class="btn btn-warning btn-sm edit-btn" data-id="${data.appointmentId}" data-info='${JSON.stringify(data)}'>Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="${data.appointmentId}">Eliminar</button>`;
                }
            }
        ],
        responsive: true,
        pageLength: 15,
        columnDefs: [{
                targets: 5,
                render: $.fn.dataTable.render.moment('YYYY-MM-DDTHH:mm:ss', 'DD/MM/YYYY')
            }],
        layout: {
            topStart: {
                buttons: ['copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5']
            }
        },
        "createdRow": function (row, data, dataIndex) {
            if (data?.status === "PENDIENTE") {
                $(row).addClass("table-row-pending");
            }
        }
    });
}

function loadPatients() {
    $.ajax({
        url: "/api/patient/search",
        method: "GET",
        dataType: "json",
        success: function (data) {
            patients = data.map(patient => ({
                    id: patient.id,
                    text: `${patient.firstName} ${patient.lastName}`
                }));
        },
        error: function (xhr, status, error) {
            console.error("Error al cargar los pacientes:", error);
            showAlert("Error al cargar los pacientes.", "danger");
        }
    });
}

function loadDoctors() {
    $.ajax({
        url: "/api/doctor/search",
        method: "GET",
        dataType: "json",
        success: function (data) {
            doctors = data;
        },
        error: function (xhr, status, error) {
            console.error("Error al cargar los doctores:", error);
            showAlert("Error al cargar los doctores.", "danger");
        }
    });
}

function actionsOnOpenModal() {
    showLoading();

    if (!$("#patientSelect").hasClass("select2-hidden-accessible")) {
        $("#patientSelect").select2({
            theme: 'bootstrap-5',
            placeholder: "Buscar paciente...",
            allowClear: true,
            minimumInputLength: 0,
            dropdownParent: $("#appointmentModal"),
            data: patients
        });
    }

    $("#doctorSelect").empty();
    $("#doctorSelect").append('<option value="">Selecciona un doctor...</option>');
    doctors.forEach(function (doctor) {
        $("#doctorSelect").append(
                `<option value="${doctor.doctorId}">${doctor.firstName} ${doctor.lastName}</option>`
                );
    });

    if (!isNewAppointment) {
        setTimeout(function () {
            $("#patientSelect").val(idPatient).trigger('change');
            //$(`#doctorSelect option[value="${idDoctor}"]`).attr("selected", "selected");
            $("#doctorSelect").val(idDoctor);
            hideLoading();
        }, 1000);
    } else {
        hideLoading();
    }
}

function createAppointment(appointmentData) {
    $.ajax({
        url: "/api/appointment",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(appointmentData),
        success: function (response) {
            showAlert("Cita creada exitosamente.", "success");
            
            $('#appointmentModal').modal('hide');
            table.ajax.reload();
        },
        error: function (xhr, status, error) {
            console.error("Error al crear la cita:", error);
            showAlert("Error al crear la cita", "danger");
        }
    });
}

function updateAppointment(id, appointmentData) {
    $.ajax({
        url: `/api/appointment/${id}`,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(appointmentData),
        success: function (response) {
            showAlert("Cita actualizada exitosamente", "success");
            $('#appointmentModal').modal('hide');
            table.ajax.reload();
        },
        error: function (xhr, status, error) {
            console.error("Error al actualizar la cita:", error);
            showAlert("Error al actualizar la cita.", "success");
        }
    });
}

function deleteAppointment(id) {
    $.ajax({
        url: `/api/appointment/${id}`,
        method: "DELETE",
        success: function () {
            showAlert("Cita eliminada exitosamente.", "success");
            $('#confirmDeleteModal').modal('hide');
            table.ajax.reload();
        },
        error: function (xhr, status, error) {
            console.error("Error al eliminar la cita:", error);
            showAlert("Error al eliminar la cita..", "danger");
        }
    });
}