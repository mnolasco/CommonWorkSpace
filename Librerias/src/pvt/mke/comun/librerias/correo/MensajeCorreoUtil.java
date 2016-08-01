package pvt.mke.comun.librerias.correo;

public class MensajeCorreoUtil {

  private String asunto;
  private String mensaje;
  private String deCorreo;
  private String[] adjuntos;
  private String[] ccCorreos;
  private String[] ccoCorreos;
  private String[] paraCorreos;
  private String aliasRemitente;

  public void setDeCorreo(String deCorreo) {
    this.deCorreo = deCorreo;
  }

  public String getDeCorreo() {
    return deCorreo;
  }

  public void setCcCorreos(String[] ccCorreo) {
    this.ccCorreos = ccCorreo;
  }

  public String[] getCcCorreos() {
    return ccCorreos;
  }

  public void setCcoCorreos(String[] ccoCorreo) {
    this.ccoCorreos = ccoCorreo;
  }

  public String[] getCcoCorreos() {
    return ccoCorreos;
  }

  public void setParaCorreos(String[] paraCorreo) {
    this.paraCorreos = paraCorreo;
  }

  public String[] getParaCorreos() {
    return paraCorreos;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAliasRemitente(String aliasRemitente) {
    this.aliasRemitente = aliasRemitente;
  }

  public String getAliasRemitente() {
    return aliasRemitente;
  }

  public void setAdjuntos(String[] adjuntos) {
    this.adjuntos = adjuntos;
  }

  public String[] getAdjuntos() {
    return adjuntos;
  }
}
