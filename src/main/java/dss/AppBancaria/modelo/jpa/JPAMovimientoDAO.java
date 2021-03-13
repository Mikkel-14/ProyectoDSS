package dss.AppBancaria.modelo.jpa;


import dss.AppBancaria.modelo.dao.MovimientoDAO;

import dss.AppBancaria.modelo.entidad.Movimiento;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento,Integer> implements MovimientoDAO {
    public JPAMovimientoDAO() {
        super(Movimiento.class);

    }
}
