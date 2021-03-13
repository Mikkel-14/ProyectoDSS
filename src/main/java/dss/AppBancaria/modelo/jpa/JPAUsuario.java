package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.modelo.dao.UsuarioDAO;
import dss.AppBancaria.modelo.entidad.Usuario;

public class JPAUsuario extends JPAGenericDAO<Usuario,String> implements UsuarioDAO{
    public JPAUsuario(){
        super(Usuario.class);
    }

    @Override
    public Usuario autorizar(String identificacion, String contraseña) {
        Usuario usuarioEntidad = this.leer(identificacion);
        if (usuarioEntidad != null){
            if(usuarioEntidad.getPassword().equals(contraseña)){
                return usuarioEntidad;
            }
        }
        return null;
    }
}
