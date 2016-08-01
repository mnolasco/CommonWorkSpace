package pvt.mke.comun.pruebas;

import pvt.mke.comun.librerias.encriptacion.EncriptacionUtil;

public class PruebaEncriptacion {
  public static void main(String[] args) {
    String texto = "198pdami";
    String clave = "S1sC0r3m3L9801+%";
    
    String encriptado = EncriptacionUtil.encriptar(texto, clave);
    System.out.println("Encriptado: " + encriptado);
    
    String encriptado2 = "%|9s’/ƒÝpÖ¥Ptul";
    String des = EncriptacionUtil.desencriptar(encriptado2, clave);
      System.out.println("Desencriptado:" +des);
    
    /*try {
      String text = "198pdami"; //%|9s’/ƒÝpÖ¥Ptul
      String key = "S1sC0r3m3L9801+%";

      Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES");

      // encrypt the text
      cipher.init(Cipher.ENCRYPT_MODE, aesKey);
      byte[] encrypted = cipher.doFinal(text.getBytes());
      System.out.println("Encrypted: " + new String(encrypted));

      //decrypt the text
      cipher.init(Cipher.DECRYPT_MODE, aesKey);
      String decrypted = new String(cipher.doFinal(encrypted));

      System.out.println("Decrypted: " +decrypted);

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }*/
  }
}
