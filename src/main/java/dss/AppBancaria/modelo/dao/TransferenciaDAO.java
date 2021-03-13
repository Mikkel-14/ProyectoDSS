package dss.AppBancaria.modelo.dao;


import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Transferencia;

import java.math.BigInteger;

public interface TransferenciaDAO extends GenericDAO<Transferencia, Integer>{
    public void realizarTransferencia(Cuenta cuentaEmisor, Cuenta receptora, BigInteger monto);
}
