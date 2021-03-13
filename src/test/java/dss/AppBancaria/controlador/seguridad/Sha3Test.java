package dss.AppBancaria.controlador.seguridad;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sha3Test {
    @Test
    public void when_ingresaValor_then_haskOK(){
        Sha3 f = new Sha3();
        String valor = f.valorHash("abc");
        assertEquals("3a985da74fe225b2045c172d6bd390bd855f086e3e9d525b46bfe24511431532", valor);
    }
}