package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.modelo.dao.TransferenciaDAO;

import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Movimiento;
import dss.AppBancaria.modelo.entidad.Transferencia;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class JPATransferenciaDAO extends JPAGenericDAO<Transferencia,Integer> implements TransferenciaDAO {

    public JPATransferenciaDAO(){
        super(Transferencia.class);
    }

    @Override
    public void realizarTransferencia(Cuenta cuentaEmisor, Cuenta receptora, Double monto) {
        //TODO ALGO TIENE QUE VER EL HOMOMORFICO
        LocalDateTime ldt = LocalDateTime.now();
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        Transferencia trans = new Transferencia(cuentaEmisor,receptora,monto,out);
        this.crear(trans);
        Double montoDescontado = cuentaEmisor.getSaldoActual()-monto;
        cuentaEmisor.setSaldoActual(montoDescontado);
        Double montoAdded = receptora.getSaldoActual() + monto;
        receptora.setSaldoActual(montoAdded);
        JPACuentaDAO c = new JPACuentaDAO();
        c.actualizar(cuentaEmisor);
        c.actualizar(receptora);
        Movimiento mov1 = new Movimiento(monto*-1.0,out,cuentaEmisor);
        Movimiento mov2 = new Movimiento(monto,out,receptora);
        JPAMovimientoDAO d = new JPAMovimientoDAO();
        d.crear(mov1);
        d.crear(mov2);
    }
}
