<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "bandera" value = "false"/>

<!DOCTYPE html>
<html>
<c:remove var="usuario"/>
<c:remove var="tipo"/>

<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Banca Web</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
            crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="css/loginStyles.css">
</head>
<body>

<div class="container-fluid d-flex flex-column justify-content-center"
     id="altura" >
    <form class="bg-white rounded-3 container shadow-sm" action="loginController" method="POST">
        <div class="row mb-3">
            <h3>Inicio de Sesión</h3>
            <h4>Banca Web</h4>
        </div>
        <div class="row px-4">
            <label for="cedula" class="form-label">Cédula</label>
        </div>
        <div class="row px-4 mb-4">
            <input type="text" class="form-control" id="cedula"
                   placeholder="Número de cédula" name="usuario" value="<c:out value="${cookie['usuario'].getValue()}"/>" required >
        </div>
        <div class="row px-4">
            <label for="passwd" class="form-label">Contraseña</label>
        </div>
        <div class="row px-4 mb-2">
            <input type="password" class="form-control" id="passwd"
                   placeholder="Contraseña" name="password" value="<c:out value="${cookie['password'].getValue()}"/>" required>
        </div>
        <div class="row px-4 mb-2 ml-20">
            <div class="col-10 form-switch">
                <input type="checkbox" id="flexSwitchCheckDefault" class="form-check-input" name="recordarme" <c:if test="${cookie['recordar'].getValue() == 'on'}">checked="checked"</c:if>/>
                <label class="form-check-label" for="flexSwitchCheckDefault">Recordarme</label>
            </div>
        </div>
        <div class="row mx-auto">
            <div class="col-5 pe-0">
                <button type="submit" class="btn btn-primary">Iniciar
                    Sesión</button>
            </div>
            <div class="col-7 px-0 my-auto mx-0">
                <a href="registroUsuarios.jsp">¿No tienes cuenta? Regístrate</a>
            </div>
        </div>
        <div class="row mx-auto">
            <div class="col my-auto mx-auto">
                <div class="text-danger">${mensajeError}</div>
            </div>
        </div>
    </form>
</div>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>