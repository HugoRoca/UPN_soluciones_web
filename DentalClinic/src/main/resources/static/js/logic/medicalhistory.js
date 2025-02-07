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
;

function seeMore(consultationData) {
    $('#consultationDate').text(formatDate(consultationData.consultation_date));
    $('#diagnosis').text(consultationData.diagnosis);
    $('#observations').text(consultationData.observations);
    $('#patientName').text(consultationData.person.name);
    $('#patientLastName').text(consultationData.person.lastName);
    $('#documentType').text(consultationData.person.documentType);
    $('#documentNumber').text(consultationData.person.documentNumber);
    $('#address').text(consultationData.person.address);
    $('#phone').text(consultationData.person.phone);
    $('#email').text(consultationData.person.email);

    // Mostrar la modal
    const modal = new bootstrap.Modal(document.getElementById('consultationModal'));
    modal.show();
}
;

$(document).ready(function () {
    $(document).on('click', '.see-more', function () {
        const data = JSON.parse($(this).attr('data-info'));
        seeMore(data);
    });

    $('#historialTable').DataTable({
        "ajax": {
            "url": "/api/medical-history",
            "dataSrc": ""
        },
        "columns": [
            {
                className: 'dt-control',
                orderable: false,
                data: null,
                defaultContent: ''
            },
            {"data": "person.name"},
            {"data": "person.lastName"},
            {"data": "consultation_date"},
            {"data": "diagnosis"},
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
                targets: 3,
                render: $.fn.dataTable.render.moment('YYYY-MM-DDTHH:mm:ss.SSSSZ', 'DD/MM/YYYY')
            }],
        layout: {
            topStart: {
                buttons: ['copyHtml5', 'excelHtml5', 'csvHtml5', 'pdfHtml5']
            }
        }
    });
});