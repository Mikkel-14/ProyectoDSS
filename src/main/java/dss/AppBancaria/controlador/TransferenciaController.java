package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.Paillier;
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
        Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
        Integer numCuenta = usr.getCuenta().getId();
        request.setAttribute("numCuenta",numCuenta);
        getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
        Cuenta emisor = usr.getCuenta();
        Integer idCuentaAEnviar = Integer.parseInt(request.getParameter("cuentaDestino"));
        DaoFactory fabrica = new JPAFactory();
        Cuenta aEnviar =fabrica.crearCuentaDAO().leer(idCuentaAEnviar);
        Double valor = Double.parseDouble(request.getParameter("valor"));
        if(aEnviar != null){
            try{
                Double saldoEmisor = Paillier.getInstance().decrypt(emisor.getSaldoActual()).doubleValue();
                if(saldoEmisor.compareTo(valor)<0){
                    request.setAttribute("numCuenta",usr.getCuenta().getId());
                    request.setAttribute("numCuentaT",idCuentaAEnviar);
                    request.setAttribute("mensajeError", "No tiene suficientes fondos");
                }
                else{
                    fabrica.creaTransferenciaDAO().realizarTransferencia(emisor,aEnviar, BigDecimal.valueOf(valor*100).toBigInteger());
                    request.setAttribute("mensajeExitoso", "Se ha realizado su transferencia exitosamente");
                    request.setAttribute("numCuenta",usr.getCuenta().getId());
                }
                getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request,response);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        } else{
            request.setAttribute("numCuenta",usr.getCuenta().getId());
            request.setAttribute("mensajeError", "No se encuentra la cuenta");
            getServletContext().getRequestDispatcher("/transferencia.jsp").forward(request,response);
        }
    }
}
