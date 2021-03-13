package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Usuario;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JPATransferenciaDAOTest {
    @Test
    public void when_crearTransferenciaDAO_then_transferenciaOK(){
        Usuario a = new Usuario("1723171714", "abc123", "Miguel", "Munoz");
        Usuario b = new Usuario("1723171715", "abc123", "Mikkel", "Munoz");
        DaoFactory fabrica = new JPAFactory();
        a.getCuenta().setSaldoActual(50.0);
        fabrica.creaUsuarioDAO().crear(a);
        fabrica.creaUsuarioDAO().crear(b);
        fabrica.creaTransferenciaDAO().realizarTransferencia(a.getCuenta(),b.getCuenta(),20.0);
        a = fabrica.creaUsuarioDAO().leer("1723171714");
        Double esperado =30.0;
        assertEquals(esperado,a.getCuenta().getSaldoActual());
    }
}