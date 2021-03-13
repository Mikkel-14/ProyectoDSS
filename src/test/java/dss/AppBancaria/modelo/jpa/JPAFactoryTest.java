package dss.AppBancaria.modelo.jpa;

import dss.AppBancaria.modelo.dao.DaoFactory;
import dss.AppBancaria.modelo.entidad.Cuenta;
import dss.AppBancaria.modelo.entidad.Movimiento;
import dss.AppBancaria.modelo.entidad.Usuario;
//import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.*;
public class JPAFactoryTest{
    private Cuenta c = new Cuenta(500.0, null);
    //@Before
    @Test
    public void when_crearUsuarioDAO_then_insercionYlecturaOK(){
        DaoFactory fabrica = new JPAFactory();
        Usuario user = new Usuario("1723171714","abc123","Miguel", "Munoz", c);
        c.setUsuario(user);
        fabrica.creaUsuarioDAO().crear(user);
        assertEquals(user,fabrica.creaUsuarioDAO().leer("1723171714"));
    }

    @Test
    public void when_crearCuentaDAO_then_lecturaOK(){
        DaoFactory fabrica = new JPAFactory();
        assertEquals(this.c.getId(),fabrica.crearCuentaDAO().leer(1).getId());
    }

    @Test
    public void when_crearMovimientoDAO_then_creacionOK(){
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        Movimiento mov = new Movimiento(-30.0,out,this.c);
        DaoFactory fabrica = new JPAFactory();
        fabrica.crearMovimientoDAO().crear(mov);
        assertEquals(mov, fabrica.crearMovimientoDAO().leer(1));
    }

    @Test
    public void when_createTransferenciaDAO_crearYcomprobarMovsOK(){
        //todo
    }


}