package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.AES;
import dss.AppBancaria.controlador.seguridad.Paillier;
import dss.AppBancaria.modelo.entidad.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;

@WebServlet(name = "ModuloUsuarioController", value = "/ModuloUsuarioController")
public class ModuloUsuarioController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usr = (Usuario)request.getSession().getAttribute("usuario");
        AES cipherC = new AES();
        try {
            String cedula = cipherC.decifrar(usr.getNumCedula());
            String part = "269845";
            AES cipherN = new AES(cedula+part);
            String nombresCompletos = cipherN.decifrar(usr.getNombre())+" " +cipherN.decifrar(usr.getApellido());
            Integer numCuenta = usr.getCuenta().getId();
            BigInteger saldoI = Paillier.getInstance().decrypt(usr.getCuenta().getSaldoActual());
            Double saldo = saldoI.doubleValue()/100;
            System.out.println(saldoI);
            request.setAttribute("numCuenta",numCuenta);
            request.setAttribute("nombresCompletos", nombresCompletos);
            request.setAttribute("saldo",saldo);
            getServletContext().getRequestDispatcher("/moduloUsuario.jsp").forward(request,response);
        } catch (Exception e){}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
