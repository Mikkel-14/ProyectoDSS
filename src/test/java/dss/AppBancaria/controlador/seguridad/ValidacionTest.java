package dss.AppBancaria.controlador.seguridad;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidacionTest {
    @Test
    public void when_ingresaCedulaValida_then_ok(){
        String cedulaValida = "1723171714";
        assertTrue(Validacion.validadorDeCedula(cedulaValida));
    }

    @Test
    public void when_ingresaCedulaInvalida_then_err(){
        String cedulaInvalida = "admin";
        assertFalse(Validacion.validadorDeCedula(cedulaInvalida));
    }

    @Test
    public void when_ingresaNombreValido_then_OK(){
        String nombre = "Mikkel";
        assertTrue(Validacion.validadorStrings(nombre));
    }

    @Test
    public void when_ingresaNombreInvalido_then_err(){
        String nombre = "<script>alert('no estoy durmiendo');</script>";
        assertFalse(Validacion.validadorStrings(nombre));
    }

    @Test
    public void when_ingresaSaldoValido_then_ok(){
        String saldo = "98.00";
        assertTrue(Validacion.validadorSaldo(saldo));
    }
    @Test
    public void when_ingresaCuentaValida_then_ok(){
        String cuenta = "10000001";
        assertTrue(Validacion.validadorCuenta(cuenta));
    }

}