$(document).ready(function () {
    let currentDoctorId = null;
    var table = loadTable();

    $(document).on('click', '#add-doctor', function () {
        currentDoctorId = null;
        $('#doctorForm')[0].reset();
        $('#doctorModalLabel').text('Agregar Doctor');
        $('#doctorModal').modal('show');
    });

    $(document).on('click', '.edit-btn', function () {
        const doctorData = JSON.parse($(this).attr('data-info'));
        currentDoctorId = doctorData.doctorId;

        $('#doctorId').val(doctorData.doctorId);
        $('#firstName').val(doctorData.firstName);
        $('#lastName').val(doctorData.lastName);
        $('#specialty').val(doctorData.specialty);
        $('#phone').val(doctorData.phone);
        $('#email').val(doctorData.email);

        $('#doctorModalLabel').text('Editar Doctor');
        $('#doctorModal').modal('show');
    });

    $('#saveDoctorBtn').on('click', function () {
        const doctorData = {
            doctorId: currentDoctorId,
            firstName: $('#firstName').val(),
            lastName: $('#lastName').val(),
            specialty: $('#specialty').val(),
            phone: $('#phone').val(),
            email: $('#email').val()
        };

        const url = currentDoctorId ? `/api/doctor/${currentDoctorId}` : '/api/doctor';
        const method = currentDoctorId ? 'PUT' : 'POST';

        $.ajax({
            url: url,
            method: method,
            contentType: 'application/json',
            data: JSON.stringify(doctorData),
            success: function (response) {
                showAlert(currentDoctorId ? 'Doctor actualizado correctamente' : 'Doctor agregado correctamente', "success");
                $('#doctorModal').modal('hide');
                location.reload();
            },
            error: function (xhr, status, error) {
                console.error('Error al guardar el doctor: ' + error);
                showAlert("Error al guardar el doctor");
            }
        });
    });
});

function loadTable() {
    console.log("doctorData", doctorData);

    return $('#doctorTable').DataTable({
        data: doctorData,
        columns: [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: ''
            },
            {
                "data": null,
                "render": function (data, type, row) {
                    return `${row.firstName} ${row.lastName}`;
                }
            },
            {data: 'phone'},
            {data: 'specialty'},
            {
                data: null,
                render: function (data) {
                    return `<button class="btn btn-warning btn-sm edit-btn" data-id="${data.doctorId}" data-info='${JSON.stringify(data)}'>Editar</button>`;
                }
            }
        ],
        responsive: true,
        pageLength: 15,
        layout: {
            topStart: {
                buttons: ['copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5']
            }
        }
    });
}