package dss.AppBancaria.modelo.dao;


import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Movimiento;


import java.util.List;

public interface MovimientoDAO extends GenericDAO<Movimiento, Integer>{
    public List<Movimiento> listarMovimientos(Cuenta cuenta);
}
