<%-- 
    Document   : calculator
    Created on : Jan 21, 2025, 9:25:20 PM
    Author     : hugoroca
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RESULT</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Resultado</h1>
        <div class="mt-4">
            <%
                double num1 = Double.parseDouble(request.getParameter("num1"));
                double num2 = Double.parseDouble(request.getParameter("num2"));
                String operation = request.getParameter("operation");
                double resultado = 0;
                String mensaje = "";

                switch (operation) {
                    case "suma":
                        resultado = num1 + num2;
                        mensaje = "La suma de " + num1 + " y " + num2 + " es: ";
                        break;
                    case "resta":
                        resultado = num1 - num2;
                        mensaje = "La resta de " + num1 + " y " + num2 + " es: ";
                        break;
                    case "multiplicacion":
                        resultado = num1 * num2;
                        mensaje = "La multiplicación de " + num1 + " y " + num2 + " es: ";
                        break;
                    case "division":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                            mensaje = "La división de " + num1 + " entre " + num2 + " es: ";
                        } else {
                            mensaje = "Error: No se puede dividir entre 0.";
                        }
                        break;
                    default:
                        mensaje = "Operación no válida.";
                        break;
                }
            %>
            <div class="alert alert-info text-center">
                <%= mensaje %>
                <% if (!mensaje.contains("Error")) { %>
                    <strong><%= resultado %></strong>
                <% } %>
            </div>
        </div>
        <div class="text-center mt-4">
            <a href="index.html" class="btn btn-secondary">Volver</a>
        </div>
    </div>
</body>
</html>
