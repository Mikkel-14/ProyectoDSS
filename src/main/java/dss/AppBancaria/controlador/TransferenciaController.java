package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.Paillier;
import dss.AppBancaria.controlador.seguridad.Validacion;
import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Usuario;
import dss.AppBancaria.modelo.jpa.JPAFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@WebServlet(name = "transferenciaController", value = "/transferenciaController")
public class TransferenciaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idusr = (String)request.getSession().getAttribute("usuario");
        DaoFactory fabrica = new JPAFactory();
        Usuario usr=fabrica.creaUsuarioDAO().leer(idusr);
        Integer numCuenta = usr.getCuenta().getId();
        request.setAttribute("numCuenta",numCuenta);
        getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cuentaDestino= request.getParameter("cuentaDestino");
        String valorString = request.getParameter("valor");
        String idusr = (String) request.getSession().getAttribute("usuario");
        DaoFactory fabrica = new JPAFactory();
        Usuario usr = fabrica.creaUsuarioDAO().leer(idusr);
        if (Validacion.validadorCuenta(cuentaDestino) && Validacion.validadorSaldo(String.valueOf(valorString)) && !String.valueOf(usr.getCuenta().getId()).equals(cuentaDestino)) {
            Double valor = Double.parseDouble(valorString);
            Integer idCuentaAEnviar = Integer.parseInt(cuentaDestino);
            Cuenta emisor = usr.getCuenta();
            Cuenta aEnviar = fabrica.crearCuentaDAO().leer(idCuentaAEnviar);
            if (aEnviar != null) {
                try {
                    Double saldoEmisor = Paillier.getInstance().decrypt(emisor.getSaldoActual()).doubleValue() / 100;
                    if (saldoEmisor.compareTo(valor) < 0) {
                        request.setAttribute("numCuenta", usr.getCuenta().getId());
                        request.setAttribute("numCuentaT", idCuentaAEnviar);
                        request.setAttribute("mensajeError", "No tiene suficientes fondos");
                    } else {
                        fabrica.creaTransferenciaDAO().realizarTransferencia(emisor, aEnviar, BigDecimal.valueOf(valor * 100).toBigInteger());
                        request.setAttribute("mensajeExitoso", "Se ha realizado su transferencia exitosamente");
                        request.setAttribute("numCuenta", usr.getCuenta().getId());
                    }
                    getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("numCuenta", usr.getCuenta().getId());
                request.setAttribute("mensajeError", "No se encuentra la cuenta");
                getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("numCuenta", usr.getCuenta().getId());
            request.setAttribute("mensajeError", "Los valores de entrada no son correctos");
            getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request, response);
        }
    }
}
