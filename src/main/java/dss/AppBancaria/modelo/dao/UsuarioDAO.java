package dss.AppBancaria.modelo.dao;

import dss.AppBancaria.modelo.entidad.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, String> {
    public Usuario autorizar(String identificacion , String contraseña);
}
