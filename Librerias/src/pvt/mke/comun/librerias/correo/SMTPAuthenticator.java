package pvt.mke.comun.librerias.correo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
  
  private String usuario;
  private String clave;
  
  public SMTPAuthenticator(String usuario, String clave){
    this.usuario = usuario;
    this.clave = clave;
  }
  
  @Override
  public PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(usuario, clave);
  }
}