package mnc.comun.librerias.encriptacion;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncriptacionUtil {

  public static String encriptar(String texto, String key) {
    StandardPBEStringEncryptor encriptor = new StandardPBEStringEncryptor();
    encriptor.setPassword(key);
    return encriptor.encrypt(texto);
  }

  public static String desencriptar(String texto, String key) {
    StandardPBEStringEncryptor encriptor = new StandardPBEStringEncryptor();
    encriptor.setPassword(key);
    String replacedText = texto.replace(" ", "+");
    return encriptor.decrypt(replacedText);
  }
}
