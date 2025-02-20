let isNewAppointment = false;
let idPatient = 0;
let idDoctor = 0;

function editAppointment(data) {
    isNewAppointment = false;

    idPatient = data.patient.id;
    idDoctor = data.doctor.doctorId;

    $("#appointmentDate").val(data.appointmentDate);
    $("#subject").val(data.subject);
    $(`#status option[value="${data.status}"]`);

    const modal = new bootstrap.Modal(document.getElementById('appointmentModal'));
    modal.show();
}

$(document).ready(function () {
    $(document).on('click', '#add-appointment', function () {
        const modal = new bootstrap.Modal(document.getElementById('appointmentModal'));
        modal.show();
    });

    $(document).on('click', '.edit-btn', function () {
        const data = JSON.parse($(this).attr('data-info'));
        editAppointment(data);
    });

    $('#statusFilter').on('change', function () {
        table.column(6).search(this.value).draw();
    });
    
    $('#typeFilter').on('change', function () {
        table.column(1 ).search(this.value).draw();
    });

    $('#appointmentModal').on('shown.bs.modal', function () {
        if (!$("#patientSelect").hasClass("select2-hidden-accessible")) {
            $("#patientSelect").select2({
                theme: 'bootstrap-5',
                placeholder: "Buscar paciente...",
                allowClear: true,
                minimumInputLength: 0,
                dropdownParent: $("#appointmentModal"),
                ajax: {
                    url: "/api/patient/search",
                    dataType: "json",
                    delay: 250,
                    data: function (params) {
                        return {query: params.term || ""};
                    },
                    processResults: function (data) {
                        return {
                            results: data.map(patient => ({
                                    id: patient.id,
                                    text: `${patient.firstName} ${patient.lastName}`
                                }))
                        };
                    },
                    cache: true
                }
            });
        }

        $.ajax({
            url: "/api/doctor/search",
            method: "GET",
            dataType: "json",
            success: function (data) {
                $("#doctorSelect").empty();

                $("#doctorSelect").append('<option value="">Selecciona un doctor...</option>');

                data.forEach(function (doctor) {
                    $("#doctorSelect").append(
                            `<option value="${doctor.doctorId}">${doctor.firstName} ${doctor.lastName}</option>`
                            );
                });
            },
            error: function (xhr, status, error) {
                console.error("Error al cargar los doctores:", error);
                $("#doctorSelect").html('<option value="">Error al cargar los doctores</option>');
            }
        });

        if (!isNewAppointment) {
            setTimeout(function () {
                $("#patientSelect").val(idPatient).trigger('change');
                $(`#doctorSelect option[value="${idDoctor}"]`).attr("selected", "selected");
            }, 2000);
        }
    });

    $('doctorSelect').on('select2:open', function () {
        setTimeout(function () {
            document.querySelector('.select2-search__field').focus();
        }, 0);
    });

    $('#appointmentModal').on('hidden.bs.modal', function () {
        $("#patientSelect").select2('destroy');
        $("#doctorSelect").select2('destroy');
    });

    let table = $('#appointmentsTable').DataTable({
        ajax: {
            url: '/api/appointments',
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
                console.log(row);
                $(row).addClass("table-row-pending");
            }
        }
    });
});