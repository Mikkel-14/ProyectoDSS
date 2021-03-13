package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.modelo.dao.*;

public class JPAFactory extends DaoFactory {

    @Override
    public UsuarioDAO creaUsuarioDAO() {
        return new JPAUsuario();
    }

    @Override
    public CuentaDAO crearCuentaDAO() {
        return new JPACuentaDAO();
    }

    @Override
    public TransferenciaDAO creaTransferenciaDAO() {
        return new JPATransferenciaDAO();
    }

    @Override
    public MovimientoDAO crearMovimientoDAO() {
        return new JPAMovimientoDAO();
    }
}
