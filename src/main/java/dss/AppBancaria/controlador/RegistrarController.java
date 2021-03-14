package dss.AppBancaria.controlador;

import dss.AppBancaria.controlador.seguridad.Paillier;
import dss.AppBancaria.controlador.seguridad.Sha3;
import dss.AppBancaria.controlador.seguridad.Validacion;
import dss.AppBancaria.controlador.seguridad.AES;
import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Usuario;
import dss.AppBancaria.modelo.jpa.JPAFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigInteger;

@WebServlet(name = "registrarController", value = "/registrarController")
public class RegistrarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean verificar=verificarDatos(request,response);
        if (verificar==true){
            registrarUsuario(request,response);
        }

    }
    private  boolean verificarDatos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");

        String password = request.getParameter("password");
        String rpassword = request.getParameter("rpassword");

        boolean bandera = false;
        bandera = Validacion.validadorDeCedula(cedula);
        String mensaje = "";
        if (bandera == false) {
            mensaje = "Cédula incorrecta";
        } else {
            bandera = Validacion.validadorStrings("nombre") && Validacion.validadorStrings("apellido") && Validacion.validadorTelefono(telefono);
            if (bandera == false) {

                mensaje = "Datos de usuario incorrectos";
            } else {
                bandera = password.equals(rpassword);
                if (bandera == false) {
                    mensaje = "Las contraseñas no coincide";
                }
            }
        }

        if(bandera==false){
            request.setAttribute("Nombre", nombre);
            request.setAttribute("Cedula", cedula);
            request.setAttribute("Telefono", telefono);
            request.setAttribute("Apellido", apellido);
            request.setAttribute("mensajeError",mensaje);
            getServletContext().getRequestDispatcher("/registroUsuarios.jsp").forward(request, response);

        }
        return bandera;

    }
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response){
        AES cipher = new AES();
        //buscar que el usuario no este previamente registrado
        String cedula = request.getParameter("cedula");
        DaoFactory fabrica = new JPAFactory();
        try {
            Usuario usr = fabrica.creaUsuarioDAO().leer(cipher.cifrar(cedula));
            if (usr != null){
                request.setAttribute("mensajeError","Usuario ya existe");
            }else{
                String cedulaCifrada=cipher.cifrar(cedula);
                AES cipherUser=new AES(cedula+"269845");
                String nombre = cipherUser.cifrar(request.getParameter("nombre"));
                String apellido = cipherUser.cifrar(request.getParameter("apellido"));
                String telefono = cipherUser.cifrar(request.getParameter("telefono"));
                String password = request.getParameter("password");
                Sha3 sha = new Sha3();
                String hPass = sha.valorHash(password);
                BigInteger saldoBase = new BigInteger("5000");
                Cuenta nuevaCuenta = new Cuenta(Paillier.getInstance().encrypt(saldoBase));
                usr = new Usuario(cedulaCifrada,hPass,nombre,apellido, nuevaCuenta);
                fabrica.creaUsuarioDAO().crear(usr);
                request.setAttribute("mensajeExito","Se ha registrado el usuario");
                getServletContext().getRequestDispatcher("/registroUsuarios.jsp").forward(request, response);
            }
        }catch(Exception e){}
    }
}
