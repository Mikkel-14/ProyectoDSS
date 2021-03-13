package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Movimiento;
import dss.AppBancaria.modelo.entidad.Usuario;
//import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;
public class JPAFactoryTest{
    private Cuenta c = new Cuenta(new BigInteger("500"));
    private Usuario user = new Usuario("1723171714","abc123","Miguel", "Munoz", c);
    /*@Before*/
    @Test
    public void when_crearUsuarioDAO_then_insercionYlecturaOK(){
        DaoFactory fabrica = new JPAFactory();
        fabrica.creaUsuarioDAO().crear(user);
        assertEquals(user,fabrica.creaUsuarioDAO().leer("1723171714"));
    }

    @Test
    public void when_crearCuentaDAO_then_lecturaOK(){
        DaoFactory fabrica = new JPAFactory();
        Integer id = 100000;
        assertEquals(id,fabrica.crearCuentaDAO().leer(100000).getId());
    }

    @Test
    public void when_crearMovimientoDAO_then_creacionOK(){
        DaoFactory fabrica = new JPAFactory();
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        Cuenta c1 = fabrica.crearCuentaDAO().leer(100000);
        Movimiento mov = new Movimiento(new BigInteger("-30"),out,c1);
        fabrica.crearMovimientoDAO().crear(mov);
        Integer id = 1;
        assertEquals(id, fabrica.crearMovimientoDAO().leer(1).getId());
    }

    @Test
    public void when_createTransferenciaDAO_crearYcomprobarMovsOK(){
        //todo
    }


}