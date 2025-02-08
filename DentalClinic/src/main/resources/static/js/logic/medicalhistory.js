function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('es-ES', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    });
}

function showConsultationModal(consultation) {
    // Paciente
    $('#patientDocument').text(`${consultation.patient.documentType} - ${consultation.patient.documentNumber}`);
    $('#patientName').text(`${consultation.patient.firstName} ${consultation.patient.lastName}`);
    $('#patientPhone').text(consultation.patient.phone);
    $('#patientEmail').text(consultation.patient.email);
    $('#patientBirthDate').text(new Date(consultation.patient.birthDate).toLocaleDateString());
    $('#patientRegistrationDate').text(new Date(consultation.patient.registrationDate).toLocaleString());

    // Doctor
    $('#doctorName').text(`${consultation.doctor.firstName} ${consultation.doctor.lastName}`);
    $('#doctorSpecialty').text(consultation.doctor.specialty);
    $('#doctorPhone').text(consultation.doctor.phone);
    $('#doctorEmail').text(consultation.doctor.email);

    // Consulta
    $('#consultationDate').text(new Date(consultation.consultationDate).toLocaleString());
    $('#diagnosis').text(consultation.diagnosis);
    $('#treatment').text(consultation.treatment);

    // Mostrar el modal
    const modal = new bootstrap.Modal(document.getElementById('consultationModal'));
    modal.show();
}

$(document).ready(function () {
    $(document).on('click', '.see-more', function () {
        const data = JSON.parse($(this).attr('data-info'));
        showConsultationModal(data);
    });

    $('#historialTable').DataTable({
        "ajax": {
            "url": "/api/medical-record",
            "dataSrc": ""
        },
        "columns": [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: ''
            },
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
            {"data": "diagnosis"},
            {"data": "consultationDate"},
            {
                "data": null,
                "render": function (data, type, row) {
                    return `<button class="btn btn-primary see-more" data-info='${JSON.stringify(row)}'>Ver</button>`;
                }
            }
        ],
        responsive: true,
        pageLength: 15,
        columnDefs: [{
                targets: 4,
                render: $.fn.dataTable.render.moment('YYYY-MM-DDTHH:mm:ss', 'DD/MM/YYYY')
            }],
        layout: {
            topStart: {
                buttons: ['copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5']
            }
        }
    });
});