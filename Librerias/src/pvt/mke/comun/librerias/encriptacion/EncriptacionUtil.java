package pvt.mke.comun.librerias.encriptacion;

import java.security.Key;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class EncriptacionUtil {

  private final static String ALGORITMO = "AES";
  
  public static String encriptar(String texto, String clave) {
    String encriptado = null;
    try {
      Key key = getSecretKey(clave);
      Cipher cipher = getCipher();
      cipher.init(Cipher.ENCRYPT_MODE, key);
      encriptado = new String(cipher.doFinal(texto.getBytes()));
    } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    return encriptado;
  }

  public static String desencriptar(String texto, String clave) {
    String desencriptado = null;
    try {
      Key key = getSecretKey(clave);
      Cipher cipher = getCipher();
      cipher.init(Cipher.DECRYPT_MODE, key);
      desencriptado = new String(cipher.doFinal(texto.getBytes()));
    } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }
    return desencriptado;
  }

  private static Key getSecretKey(String clave) {
    return new SecretKeySpec(clave.getBytes(), ALGORITMO);
  }

  private static Cipher getCipher() {
    try {
      return Cipher.getInstance(ALGORITMO);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      return null;
    }
  }
}