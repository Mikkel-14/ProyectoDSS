package dss.AppBancaria.controlador.seguridad;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public class Sha3 {
    private SHA3.Digest256 funcionHash;
    public Sha3(){
        funcionHash = new SHA3.Digest256();
    }

    public String valorHash(String entrada){
        return Hex.toHexString(funcionHash.digest(entrada.getBytes()));
    }
}
