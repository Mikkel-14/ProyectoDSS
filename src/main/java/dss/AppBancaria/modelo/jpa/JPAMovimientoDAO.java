package dss.AppBancaria.modelo.jpa;


import dss.AppBancaria.modelo.dao.MovimientoDAO;

import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Movimiento;


import javax.persistence.Query;
import java.util.List;

public class JPAMovimientoDAO extends JPAGenericDAO<Movimiento,Integer> implements MovimientoDAO {
    public JPAMovimientoDAO() {
        super(Movimiento.class);
    }

    @Override
    public List<Movimiento> listarMovimientos(Cuenta cuenta) {
        Query q = em.createNamedQuery("listarMovimientos", Movimiento.class);
        q.setParameter("cuenta",cuenta);
        List<Movimiento> movimientos = (List<Movimiento>) q.getResultList();
        if(movimientos.size() !=0) {
            return movimientos;
        }
        return null;
    }
}
