package mnc.comun.pruebas;

import mnc.comun.librerias.encriptacion.EncriptacionUtil;

public class PruebaEncriptacion {
  private static final String keyValue = "%4Dm1n29+";

  public static void main(String[] args) {

    String texto = "12345abc+";
    System.out.println("Encritpado: " + EncriptacionUtil.encriptar(texto, keyValue));
    //System.out.println("Desencriptado: " + EncriptacionUtil.desencriptar("+WBLXtXZ4qntaB0StfVbWUhOF07r/maQ", keyValue));
  }
}
