<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
             pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <c:choose>
    <c:when test="${(empty sessionScope.usuario) }">
        <meta http-equiv="refresh" content="0; url=index.jsp"/>
        </head>

    </c:when>
    <c:otherwise>
    <meta charset="ISO-8859-1">
    <title>Módulo Usuario</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
            crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link
            href="https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap"
            rel="stylesheet">
    <link rel="stylesheet" href="css/moduloUsuario.css">
    <script src="https://kit.fontawesome.com/7e57fa7d19.js"></script>

</head>



<body>

<div class="container-fluid" id="altura">
    <nav class="row purpura" style="height: 6%" >
        <div class="col-9 d-flex flex-column justify-content-center align-items-start">
            DSSBANK
        </div>
        <div class="col-3 d-flex flex-row justify-content-end align-items-center">
            <div class="col-3 d-flex flex-row justify-content-end align-items-center">
                <a href="ModuloUsuarioController" class="btn">Usuario</a>
                <a href="CerrarSesionController" class="btn"><i class="fas fa-sign-out-alt is"></i></a>
            </div>
        </div>
    </nav>

    <div class="row" style="height: 94%;">
        <!-- una fila con dos columnas: la primera es la navegacion izq
       y la segunda el espacio en blanco-->
        <div class="col-2 purpura2 d-flex flex-column justify-content-start">
            <a class="btn py-3 my-4 text-center" href="transferenciaController">Transferencia</a>
            <a class="btn py-3 my-4 text-center" href="listarMovimientoController">Listar Movimientos</a>
            <a class="btn py-3 my-4 text-center" href="cambiarContrasena.jsp">Cambiar Contraseña</a>
        </div>
        <div class="col-10 position-relative imgDiv" >
            <div class="info position-absolute top-50 start-50 translate-middle">
                <label class="fs-3">Número de Cuenta</label><br>
                <label class="ps-5 fs-4 text-secondary">${numCuenta}</label><br>
                <label class="mt-5 fs-3">Usuario</label><br>
                <label class="ps-5 fs-4 text-secondary">${nombresCompletos}</label><br>
                <label class="mt-5 fs-3">Saldo Actual</label><br>
                <label class="ps-5 fs-4 text-secondary">$ ${saldo}</label><br>
            </div>

        </div>
    </div>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</c:otherwise>
</c:choose>
</html>
