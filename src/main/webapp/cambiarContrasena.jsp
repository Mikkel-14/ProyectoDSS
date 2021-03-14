<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Cambio de Contraseña</title>
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
                <a class="btn py-3 my-4 text-center aOn" href="#">Cambiar Contraseña</a>
            </div>
            <div class="col-10 position-relative imgDiv" >
                <form action="cambiarContrasenaController" class="position-absolute top-50 start-50 translate-middle container" method="post">
                    <div class="row px-5 mx-4">
                        <label for="contraAntigua" class="form-label">Contraseña Actual</label>
                    </div>
                    <div class="row px-5 mx-4">
                        <input type="password" class="form-control" id="contraAntigua"
                               placeholder="Contaseña Actual" name="actpasswd" required >
                    </div>
                    <div class="row px-5 mx-4 mt-2">
                        <label for="contraNew" class="form-label">Contraseña Nueva</label>
                    </div>
                    <div class="row px-5 mx-4">
                        <input type="password" class="form-control" id="contraNew"
                               placeholder="Contaseña Actual" name="passwd" required >
                    </div>
                    <div class="row px-5 mx-4 mt-2">
                        <label for="confirmacion" class="form-label">Confirmar Contraseña</label>
                    </div>
                    <div class="row px-5 mx-4">
                        <input type="password" class="form-control" id="confirmacion"
                               placeholder="Contaseña Actual" name="repasswd" required >
                    </div>
                    <div class="row mx-auto px-5 mt-3">
                        <div class="col-5"></div>
                        <div class="col-2">
                            <button type="submit" class="btn btn-primary">Cambiar Contraseña</button>
                        </div>
                        <div class="col-5"></div>
                    </div>
                    <div class="row mt-2 px-5 mx-4 text-danger">${mensajeError}</div>
                    <div class="row mt-2 px-5 mx-4 text-success">${mensajeExitoso}</div>
                </form>

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
