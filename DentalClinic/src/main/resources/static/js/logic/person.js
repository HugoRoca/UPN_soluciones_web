let isNewPerson = false;

function editPerson(personData) {
    isNewPerson = false;

    $("#personModalLabel").html("Editar Persona");

    $('#personId').val(personData.id);
    $('#documentType').val(personData.documentType);
    $('#documentNumber').val(personData.documentNumber);
    $('#name').val(personData.firstName);
    $('#lastName').val(personData.lastName);
    $('#email').val(personData.email);
    $('#phone').val(personData.phone);

    const modal = new bootstrap.Modal(document.getElementById('personModal'));
    modal.show();
}

function addPerson() {
    isNewPerson = true;

    $("#personModalLabel").html("Nueva persona");

    $('#personId').val('');
    $('#documentType').val('');
    $('#documentNumber').val('');
    $('#name').val('');
    $('#lastName').val('');
    $('#email').val('');
    $('#phone').val('');

    const modal = new bootstrap.Modal(document.getElementById('personModal'));
    modal.show();
}

$(document).ready(function () {
    $(document).on('click', '.edit-person', function () {
        const data = JSON.parse($(this).attr('data-info'));
        editPerson(data);
    });

    $(document).on('click', '.delete-person', function () {
        const data = JSON.parse($(this).attr('data-info'));

        $('#deletePersonId').val(data.id);

        $('.text-custom-person').html(`¿Estás seguro de que deseas eliminar este registro de la persona ${data.name} ${data.lastName}?`);

        const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
        modal.show();
    });

    $(document).on('click', '#add-person', function () {
        addPerson();
    });

    $('#confirmDelete').click(function () {
        const personId = $('#deletePersonId').val();

        $.ajax({
            url: `/api/patient/${personId}`,
            type: 'DELETE',
            success: function (response) {
                alert('Registro eliminado correctamente');
                $('#confirmDeleteModal').modal('hide');
                $('#personTable').DataTable().ajax.reload(); // Recargar la tabla
            },
            error: function (error) {
                if (error.responseJSON.message.includes("foreign")) {
                    alert(`No se puede eliminar este registro, tiene historia clínica!`);
                    return;
                }

                alert('Error al eliminar el registro');
                console.error(error);
            }
        });
    });

    $('#savePerson').click(function () {
        const personData = {
            documentType: $('#documentType').val(),
            documentNumber: $('#documentNumber').val(),
            firstName: $('#name').val(),
            lastName: $('#lastName').val(),
            email: $('#email').val(),
            phone: $('#phone').val(),
            address: $('#address').val()
        };

        if (isNewPerson) {
            // Crear persona (POST)
            $.ajax({
                url: "/api/patient",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(personData),
                success: function (response) {
                    alert("Persona creada con éxito");
                    $('#personModal').modal('hide');
                    $('#personTable').DataTable().ajax.reload(); // Recargar la tabla
                },
                error: function (error) {
                    alert("Error al crear la persona");
                    console.error(error);
                }
            });
        } else {
            // Editar persona (PUT)
            const personId = $('#personId').val();
            $.ajax({
                url: `/api/patient/${personId}`,
                type: "PUT",
                contentType: "application/json",
                data: JSON.stringify(personData),
                success: function (response) {
                    alert("Persona actualizada con éxito");
                    $('#personModal').modal('hide');
                    $('#personTable').DataTable().ajax.reload(); // Recargar la tabla
                },
                error: function (error) {
                    alert("Error al actualizar la persona");
                    console.error(error);
                }
            });
        }
    });


    $('#personTable').DataTable({
        "ajax": {
            "url": "/api/patient",
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
                    return `${row.documentType} - ${row.documentNumber}`;

                }
            },
            {"data": "firstName"},
            {"data": "lastName"},
            {"data": "phone"},
            {
                "data": null,
                "render": function (data, type, row) {
                    return `<button class="btn btn-warning edit-person" data-info='${JSON.stringify(row)}'>Editar</button>
                        <button class="btn btn-danger delete-person" data-info='${JSON.stringify(row)}'>Eliminar</button>`;
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
});



