package dss.AppBancaria.controlador.seguridad;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {
    //AES - Simetrico
    private static final String ALGORITMO="AES";
    private static final String KEY ="7933729383246720";
    //las llaves siempre se trabajan en bytes
    private byte[] secretKey;
    public AES(String key) {
        //transformamos el string a bytes
        this.secretKey = key.getBytes();
    }

    public AES(){
        this.secretKey = KEY.getBytes();
    }
    //generar clave
    private Key generateKey() {
        return new SecretKeySpec(secretKey, ALGORITMO);
    }

    //cifrar mensaje
    public String cifrar(String mensajePlano) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(ALGORITMO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encodeValue = c.doFinal(mensajePlano.getBytes());
        return Base64.getEncoder().encodeToString(encodeValue);
    }

    public String decifrar(String mensajeCifrado) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(ALGORITMO);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] valorCifrado = Base64.getDecoder().decode(mensajeCifrado.getBytes());
        byte[] valorDecifrado = c.doFinal(valorCifrado);
        return new String(valorDecifrado);
    }
}
