package dss.AppBancaria.controlador.seguridad;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESTest {
    @Test
    public void when_textoAEncriptar_then_decifrarOK(){
        String texto = "Desarrollo de Software Seguro";
        AES cipher = new AES("2017207930000000");
        try{
            String cifrado =cipher.cifrar(texto);
            assertEquals(texto,cipher.decifrar(cifrado));
        }catch (Exception e){

        }
    }

}