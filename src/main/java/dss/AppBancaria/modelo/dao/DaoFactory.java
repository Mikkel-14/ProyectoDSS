package dss.AppBancaria.modelo.dao;



public abstract class DaoFactory {

    public abstract UsuarioDAO creaUsuarioDAO();
    public abstract CuentaDAO crearCuentaDAO();
    public abstract TransferenciaDAO creaTransferenciaDAO();
    public abstract MovimientoDAO crearMovimientoDAO();


}
