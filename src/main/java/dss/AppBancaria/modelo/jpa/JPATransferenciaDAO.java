package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.controlador.seguridad.Paillier;
import dss.AppBancaria.modelo.dao.TransferenciaDAO;

import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Movimiento;
import dss.AppBancaria.modelo.entidad.Transferencia;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class JPATransferenciaDAO extends JPAGenericDAO<Transferencia,Integer> implements TransferenciaDAO {

    public JPATransferenciaDAO(){
        super(Transferencia.class);
    }

    @Override
    public void realizarTransferencia(Cuenta cuentaEmisor, Cuenta receptora, BigInteger monto) {
        //ALERTA: Este algoritmo supone que todos sus parametros se encuentran encriptados, sino no sirve
        LocalDateTime ldt = LocalDateTime.now();
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        em.getTransaction().begin();
        try {
            Paillier cipher = Paillier.getInstance();
            BigInteger montoCifrado = cipher.encrypt(monto);
            Transferencia trans = new Transferencia(cuentaEmisor,receptora,montoCifrado,out);
            em.persist(trans);
            em.getTransaction().commit();
            BigInteger montoDescontado = cipher.sumar(cuentaEmisor.getSaldoActual(),cipher.encrypt(monto.negate()));
            BigInteger montoAdd = cipher.sumar(receptora.getSaldoActual(),montoCifrado);
        }catch(Exception e){
            System.err.println("No se pudo realizar la transaccion");
            if(em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
    }
}
