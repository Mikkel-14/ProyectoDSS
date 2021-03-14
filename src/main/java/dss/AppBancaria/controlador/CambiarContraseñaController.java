package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.Sha3;
import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Usuario;
import dss.AppBancaria.modelo.jpa.JPAFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "cambiarContrasenaController", value = "/cambiarContrasenaController")
public class CambiarContraseñaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actpasswd = request.getParameter("actpasswd");
        String passwd = request.getParameter("passwd");
        String repasswd = request.getParameter("repasswd");
        String idusr = (String) request.getSession().getAttribute("usuario");
        DaoFactory fabrica = new JPAFactory();
        Usuario usr = fabrica.creaUsuarioDAO().leer(idusr);
        boolean validar=false;
        Sha3 sha = new Sha3();
        String mensaje="";
        validar=passwd.equals(repasswd);
        if(validar==true){
            String hPass = sha.valorHash(actpasswd);
            validar=hPass.equals(usr.getPassword());
            if(validar==false){
                mensaje="La contraseña actual no es correcta";
            }

        }else {
            mensaje="Las contraseñas no coincide";
        }

        if(validar==false){
            request.setAttribute("mensajeError", mensaje);
            getServletContext().getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);
        }else{
            String newhPass = sha.valorHash(passwd);
            usr.setPassword(newhPass);
            DaoFactory fabrica2 = new JPAFactory();
            fabrica2.creaUsuarioDAO().actualizar(usr);
            request.setAttribute("mensajeExitoso", "Contraseña cambiada");
            getServletContext().getRequestDispatcher("/cambiarContrasena.jsp").forward(request, response);

        }


    }


}
