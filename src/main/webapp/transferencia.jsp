<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Transferencia</title>
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
            <a class="btn py-3 my-4 text-center aOn" href="#">Transferencia</a>
            <a class="btn py-3 my-4 text-center" href="listarMovimientoController">Listar Movimientos</a>
        </div>
        <div class="col-10 position-relative imgDiv" >
            <form action="transferenciaController" class="info position-absolute top-50 start-50 translate-middle" method="post">
                <label class="fs-3 form-label">Número de Cuenta</label><br>
                <input class=" fs-4 text-secondary form-control"type="text" value="${numCuenta}" readonly required><br>
                <label class="mt-5 fs-3 form-label ">Cuenta a Transferir</label><br>
                <input class=" fs-4 text-secondary form-control"type="text" placeholder="Cuenta a Transferir" name="cuentaDestino" value="${numCuentaT}" required><br>
                <label class="mt-5 fs-3 form-label form-label">Valor</label><br>
                <input class="fs-4 text-secondary form-control"type="text" placeholder="$ 00.0" name="valor" required><br>
                <input type="submit" class="mt-5 btn btn-primary" value="Transferir">
                <div class="text-danger">${mensajeError}</div>
                <div class="text-success">${mensajeExitoso}</div>
            </form>

        </div>
    </div>
</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>
