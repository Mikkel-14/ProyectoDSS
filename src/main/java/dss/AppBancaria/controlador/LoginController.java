package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.AES;
import dss.AppBancaria.controlador.seguridad.Sha3;
import dss.AppBancaria.controlador.seguridad.Validacion;
import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Usuario;
import dss.AppBancaria.modelo.jpa.JPAFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "loginController", value = "/loginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cedula =request.getParameter("usuario");
        String contrasena = request.getParameter("password");
        String recordar= request.getParameter("recordarme");
        Cookie galletaUser= new Cookie("usuario", "");
        Cookie galletaPassword = new Cookie("password", "");
        Cookie galletaBandera = new Cookie("recordar", "");
        if (recordar!=null && recordar.equals("on")) {

            galletaUser.setValue(cedula);
            galletaPassword.setValue(contrasena);
            galletaBandera.setValue(recordar);

        }
        else{

            galletaUser.setMaxAge(0);
            galletaPassword.setMaxAge(0);
            galletaBandera.setMaxAge(0);
            response.addCookie(galletaPassword);
            response.addCookie(galletaUser);
            response.addCookie(galletaBandera);
        }
        if(Validacion.validadorDeCedula(cedula)){
            AES cipher = new AES();
            Sha3 hash = new Sha3();
            try {
                String cedulaC = cipher.cifrar(cedula);
                String contraHash = hash.valorHash(contrasena);
                DaoFactory fabrica = new JPAFactory();
                Usuario usr =fabrica.creaUsuarioDAO().autorizar(cedulaC,contraHash);
                if(usr !=null){
                    response.addCookie(galletaPassword);
                    response.addCookie(galletaUser);
                    response.addCookie(galletaBandera);

                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("usuario", cedulaC);
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);


                    //TODO dirigir a la siguiente

                }else{
                    request.setAttribute("mensajeError","Usuario o contraseña incorrectos");
                    getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
                }
            } catch (Exception e){}
        } else{
            request.setAttribute("mensajeError","El usuario debe ser una cedula valida");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }

}
