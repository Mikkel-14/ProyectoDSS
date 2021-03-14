package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.Paillier;
import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Movimiento;
import dss.AppBancaria.modelo.entidad.Usuario;
import dss.AppBancaria.modelo.jpa.JPAFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listarMovimientoController", value = "/listarMovimientoController")
public class ListarMovimientoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarSolicitud(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void procesarSolicitud (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cedulaC = (String)request.getSession().getAttribute("usuario");
        DaoFactory fabrica = new JPAFactory();
        Usuario usr =fabrica.creaUsuarioDAO().leer(cedulaC);
        List<Movimiento> movimientos = fabrica.crearMovimientoDAO().listarMovimientos(usr.getCuenta());
        List<Movimiento> mvts = new ArrayList<>();
        if(movimientos.size() !=0) {
            for (Movimiento mov : movimientos) {
                BigInteger montoC = mov.getMonto();
                try {
                    mov.setMonto(Paillier.getInstance().decrypt(montoC));
                    mvts.add(mov);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        request.setAttribute("nomina",mvts);
        getServletContext().getRequestDispatcher("/listarMovimientos.jsp").forward(request,response);

    }
}
