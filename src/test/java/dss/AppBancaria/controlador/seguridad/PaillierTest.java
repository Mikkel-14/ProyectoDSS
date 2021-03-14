package dss.AppBancaria.controlador.seguridad;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class PaillierTest {
    @Test
    public void when_ingresaSaldo_then_descifrarOK() throws Exception{
        BigInteger saldoBase = new BigInteger("5000");
        Paillier.getInstance().printValues();
        BigInteger encriptado = Paillier.getInstance().encrypt(saldoBase);
        Paillier.getInstance().printValues();
        assertEquals(saldoBase,Paillier.getInstance().decrypt(encriptado));
    }
}