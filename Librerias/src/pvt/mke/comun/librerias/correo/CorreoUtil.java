package pvt.mke.comun.librerias.correo;

import java.util.Properties;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetAddress;

import javax.activation.DataSource;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class CorreoUtil {

  private static Session mailSession;
  private static MimeMessage mailMessage;
  private static Multipart multipartMessage;


  public static void conectar(String servidor, String usuario, String clave) throws MessagingException {

    Properties mailProperties = new Properties();
    mailProperties.setProperty("mail.smtp.auth", "true");
    mailProperties.put("mail.transport.protocol", "smtp");
    mailProperties.put("mail.smtp.host", servidor);
    mailProperties.put("mail.smtp.starttls.enable", "true");
    mailProperties.put("mail.smtp.port", "587");

    Authenticator smptAuthenticator = new SMTPAuthenticator(usuario, clave);

    mailSession = Session.getInstance(mailProperties, smptAuthenticator);
  }

  public static void conectar(String servidor) throws MessagingException {
    Properties mailProperties = new Properties();
    mailProperties.put("mail.transport.protocol", "smtp");
    mailProperties.put("mail.smtp.host", servidor);
    
    mailSession = Session.getDefaultInstance(mailProperties);
  }

  public static void enviar(String servidor, String usuario, String clave,
                            MensajeCorreoUtil mensaje) throws MessagingException, UnsupportedEncodingException {
    conectar(servidor, usuario, clave);
    escribir(mensaje);
    enviar();
  }

  public static void enviar(String servidor, MensajeCorreoUtil mensaje) throws MessagingException,
                                                                               UnsupportedEncodingException {
    conectar(servidor);
    escribir(mensaje);
    enviar();
  }

  private static void enviar() throws MessagingException {
    Transport.send(mailMessage);
  }

  private static void escribir(MensajeCorreoUtil mensaje) throws MessagingException, UnsupportedEncodingException {
    prepararMensaje(mensaje);

    agregarParaCorreos(mensaje);
    agregarCopiaCorreos(mensaje);
    agregarCopiaOcultaCorreos(mensaje);
    agregarTexto(mensaje);
    agregarAdjunto(mensaje);

    consolidarMensaje();
  }

  private static void prepararMensaje(MensajeCorreoUtil mensaje) throws MessagingException,
                                                                        UnsupportedEncodingException {
    mailMessage = new MimeMessage(mailSession);
    multipartMessage = new MimeMultipart("mixed"); // El mixed permite adjuntar varios archivos

    mailMessage.setSubject(mensaje.getAsunto());
    mailMessage.setFrom(new InternetAddress(mensaje.getDeCorreo(), mensaje.getAliasRemitente()));
  }

  private static void consolidarMensaje() throws MessagingException {
    mailMessage.setContent(multipartMessage);
  }

  private static void agregarTexto(MensajeCorreoUtil mensaje) throws MessagingException {
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(mensaje.getMensaje(), "text/html");
    multipartMessage.addBodyPart(messageBodyPart);
  }

  private static void agregarAdjunto(MensajeCorreoUtil mensaje) throws MessagingException {
    String[] adjuntos = mensaje.getAdjuntos();
    if (adjuntos != null) {
      int totalAdjuntos = adjuntos.length;
      if (totalAdjuntos > 0) {
        for (int i = 0; i < totalAdjuntos; i++) {
          DataSource sourceAttachment = new FileDataSource(adjuntos[i]);
          MimeBodyPart attachmentPart = new MimeBodyPart();
          attachmentPart.setDataHandler(new DataHandler(sourceAttachment));

          String nombre = adjuntos[i].substring(adjuntos[i].lastIndexOf("/"));
          attachmentPart.setFileName(nombre);

          //Para agregar imagenes dentro del cuerpo del mensaje html.El id utilizado es "image"
          if (nombre.contains(".jpg") || nombre.contains(".png") || nombre.contains(".gif")) {
            attachmentPart.setHeader("Content-ID", "<image>");
          }
          multipartMessage.addBodyPart(attachmentPart);
        }
      }
    }
  }

  private static void agregarParaCorreos(MensajeCorreoUtil mensaje) throws MessagingException {

    if (mensaje.getParaCorreos() != null) {
      int totalCorreosPara = mensaje.getParaCorreos().length;
      if (totalCorreosPara > 0) {
        InternetAddress[] paraCorreos = new InternetAddress[totalCorreosPara];
        for (int i = 0; i < totalCorreosPara; i++) {
          InternetAddress direccionCorreo = new InternetAddress(mensaje.getParaCorreos()[i]);
          paraCorreos[i] = direccionCorreo;
        }
        mailMessage.setRecipients(Message.RecipientType.TO, paraCorreos);
      }
    }
  }

  private static void agregarCopiaCorreos(MensajeCorreoUtil mensaje) throws MessagingException {

    if (mensaje.getCcCorreos() != null) {
      int totalCorreosPara = mensaje.getCcCorreos().length;
      InternetAddress[] copiaCorreos = new InternetAddress[totalCorreosPara];
      for (int i = 0; i < totalCorreosPara; i++) {
        InternetAddress direccionCorreo = new InternetAddress(mensaje.getCcCorreos()[i]);
        copiaCorreos[i] = direccionCorreo;
      }
      mailMessage.setRecipients(Message.RecipientType.CC, copiaCorreos);
    }
  }

  private static void agregarCopiaOcultaCorreos(MensajeCorreoUtil mensaje) throws MessagingException {

    if (mensaje.getCcoCorreos() != null) {
      int totalCorreosPara = mensaje.getCcoCorreos().length;
      InternetAddress[] copiaOcultaCorreos = new InternetAddress[totalCorreosPara];
      for (int i = 0; i < totalCorreosPara; i++) {
        InternetAddress direccionCorreo = new InternetAddress(mensaje.getCcoCorreos()[i]);
        copiaOcultaCorreos[i] = direccionCorreo;
      }
      mailMessage.setRecipients(Message.RecipientType.BCC, copiaOcultaCorreos);
    }
  }
}