package dss.AppBancaria.modelo.jpa;


import dss.AppBancaria.modelo.*;
import dss.AppBancaria.modelo.dao.CuentaDAO;
import dss.AppBancaria.modelo.entidad.Cuenta;

public class JPACuentaDAO extends JPAGenericDAO<Cuenta,Integer> implements CuentaDAO {

    public JPACuentaDAO() {
        super(Cuenta.class);

    }

}
