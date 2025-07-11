/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

$("#loginForm").on("submit", function(event) {
    event.preventDefault(); // Evita recargar la página

    const username = $("#username").val();
    const password = $("#password").val();

    $.ajax({
        url: "/api/auth/login",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({ username, password }),
        success: function(data) {
            localStorage.setItem("token", JSON.stringify(data)); // Asegúrate de guardar los datos correctamente en localStorage
            location.href = "/dashboard";
        },
        error: function(xhr, status, error) {
            alert("Usuario y/o contraseña incorrectas.")
            $("#username").focus();
        }
    });
});

