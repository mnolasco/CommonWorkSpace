package pvt.mke.comun.pruebas;


import java.io.UnsupportedEncodingException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import javax.mail.SendFailedException;

import pvt.mke.comun.librerias.correo.CorreoUtil;
import pvt.mke.comun.librerias.correo.MensajeCorreoUtil;

public class PruebaCorreo {
  public static void main(String[] args) {
    String deCorreo = "portal@oncosalud.com.pe";
    
    String mensajHtml ="<div><img src=\"cid:image\">\n" + 
    "\n" + 
    "<p style='margin-bottom:12.0pt'><b><span style='font-size:14.0pt;font-family:\n" + 
    "\"Calibri\",\"sans-serif\";color:#006890'>Sr(a) MIGUEL NOLASCO</span></b></p>\n" + 
    "\n" + 
    "<p style='margin-bottom:12.0pt'><span style='font-size:11.0pt;font-family:\"Calibri\",\"sans-serif\";\n" + 
    "color:#1F497D'>Nos es grato saludarte e informarte que de acuerdo a tu\n" + 
    "solicitud hemos procedido a modificar tus datos</span><span style='font-family:\n" + 
    "\"Calibri\",\"sans-serif\"'>. </span></p>\n" + 
    "\n" + 
    "<table border=0 cellpadding=0 width=\"100%\"\n" + 
    " style='width:100.0%'>\n" + 
    " <tr>\n" + 
    "  <td style='padding:2.25pt 2.25pt 2.25pt 2.25pt'>\n" + 
    "  <table border=0 cellpadding=0 width=\"100%\"\n" + 
    "   style='width:100.0%'>\n" + 
    "   <tr>\n" + 
    "    <td style='padding:.75pt .75pt .75pt .75pt'>\n" + 
    "    <table border=0 cellpadding=0 width=555\n" + 
    "     style='width:415.95pt'>\n" + 
    "     <tr style='height:23.85pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.85pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Grupo Familiar: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.85pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/grupoFamiliar/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:22.9pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.9pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Titular: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.9pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/nombreCompleto/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:23.5pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.5pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Programa:</span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.5pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/programa/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:23.75pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.75pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Doc. de Identidad: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.75pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/docIden/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:21.35pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:21.35pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Direcci&oacute;n: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:21.35pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/direccion/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:21.75pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:21.75pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Departamento: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:21.75pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/departamento/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:22.15pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.15pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Provincia: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.15pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/provincia/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:23.25pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.25pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Distrito: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:23.25pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/distrito/</span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:22.25pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.25pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Tel&eacute;fono/Celular:</span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.25pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:#006890'>/telefono/ </span></b></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "     <tr style='height:22.0pt'>\n" + 
    "      <td width=190 style='width:142.6pt;background:#006890;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.0pt'>\n" + 
    "      <p><b><span style='font-family:\"Calibri\",\"sans-serif\";\n" + 
    "      color:white'>Correo electr&oacute;nico: </span></b></p>\n" + 
    "      </td>\n" + 
    "      <td width=358 style='width:268.85pt;background:#E1EDFF;padding:2.25pt 2.25pt 2.25pt 2.25pt;\n" + 
    "      height:22.0pt'>\n" + 
    "      <p><a href=\"mailto:/correo/\"><b><span\n" + 
    "      style='font-family:\"Calibri\",\"sans-serif\";color:#006890'>/correo/</span></b></a></p>\n" + 
    "      </td>\n" + 
    "     </tr>\n" + 
    "    </table>\n" + 
    "    </td>\n" + 
    "   </tr>\n" + 
    "  </table>\n" + 
    "  </td>\n" + 
    " </tr>\n" + 
    " <tr>\n" + 
    "  <td style='padding:2.25pt 2.25pt 2.25pt 2.25pt'>\n" + 
    "  <p><br>\n" + 
    "  <span style='font-size:11.0pt;font-family:\"Calibri\",\"sans-serif\";color:#1F497D'>Queremos\n" + 
    "  aprovechar esta oportunidad para agradecerte la confianza depositada en\n" + 
    "  nosotros y reiterarte que estamos a tu disposici&oacute;n para cualquier consulta a\n" + 
    "  trav&eacute;s de nuestra central telef&oacute;nica  513-7900  o en nuestro correo \n" + 
    "  electr&oacute;nico </span><span style='font-size:11.0pt;font-family:\"Calibri\",\"sans-serif\"'> </span><a\n" + 
    "  href=\"mailto:contactos@oncosalud.com.pe\"><span style='font-size:11.0pt;\n" + 
    "  font-family:\"Calibri\",\"sans-serif\"'>contactos@oncosalud.com.pe</span></a><span\n" + 
    "  style='font-size:11.0pt;font-family:\"Calibri\",\"sans-serif\"'> &nbsp;<br>\n" + 
    "  <span style='color:#1F497D'><br>\n" + 
    "  </span></span><span style='color:#1F497D'><br>\n" + 
    "  <br>\n" + 
    "  </span></p>\n" + 
    "  </td>\n" + 
    " </tr>\n" + 
    " <tr>\n" + 
    "  <td style='padding:2.25pt 2.25pt 2.25pt 2.25pt'>\n" + 
    "  <p align=center style='text-align:center'><span\n" + 
    "  style='font-size:10.0pt;font-family:\"Calibri\",\"sans-serif\"'>********************\n" + 
    "  AVISO LEGAL ********************</span></p>\n" + 
    "  <p align=center style='text-align:center'><span\n" + 
    "  style='font-size:10.0pt;font-family:\"Calibri\",\"sans-serif\"'>Este mensaje es\n" + 
    "  solamente para la persona a la que va dirigido. Si usted ha recibido este\n" + 
    "  mensaje por error, le rogamos que borre de su sistema inmediatamente el\n" + 
    "  mensaje as&iacute; como todas sus copias, destruya todas las copias del mismo de su\n" + 
    "  disco duro y notifique al remitente. No debe, directa o indirectamente, usar,\n" + 
    "  revelar, distribuir, imprimir o copiar ninguna de las partes de este mensaje\n" + 
    "  si no es usted el destinatario.</span></p>\n" + 
    "  </td>\n" + 
    " </tr>\n" + 
    "</table>\n" + 
    "</div>";

    MensajeCorreoUtil mensajeCorreo = new MensajeCorreoUtil();
    mensajeCorreo.setAsunto("PRUEBA DE ENVÍO DE CORREO");
    mensajeCorreo.setAliasRemitente("AUNA");
    mensajeCorreo.setDeCorreo(deCorreo);
    mensajeCorreo.setParaCorreos(new String[] { "mke.nolasco@gmail.com" });
    // mensajeCorreo.setCcCorreos(new String[]{"ese_bandido_31@hotmail.com"});
    mensajeCorreo.setMensaje(mensajHtml);
    mensajeCorreo.setAdjuntos(new String[] {
                             "E:/plantilla PPT ecofriendly2.pptx","E:/auna.jpg" });

    try {
      CorreoUtil.enviar("198.198.111.147", mensajeCorreo);
      
      //Para logearse con google mail hay que ir al link y seleccionar la opcion desactivar
      //https://www.google.com/settings/security/lesssecureapps
      //CorreoUtil.enviar("smtp.gmail.com", "mke.nolasco@gmail.com", "198pdam+", mensajeCorreo); // gmail via tls
    } catch (MessagingException e) {
      if(e instanceof SendFailedException){
        System.out.println("Algun correo inexistente");
      }else if (e instanceof AuthenticationFailedException){
        System.out.println("Error de autenticación con el servidor.");
      }
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
