package pvt.mke.comun.librerias.ftp;

import java.io.InputStream;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class SFTPUtil {

  private static Session sesion;
  private static ChannelSftp canal;
  private static final String CANAL_SFTP = "sftp";
  private static final String CANAL_COMANDO = "exec";

  public static boolean transferirArchivo(String servidor, String usuario, String clave, String ruta,
                                          InputStream fileStream) throws JSchException, SftpException {
    boolean transferido = false;
    try {
      conectar(servidor, usuario, clave);
      canal.put(fileStream, ruta);
      transferido = true;
    } finally {
      desconectar();
    }
    return transferido;
  }

  public static boolean crearCarpeta(String servidor, String usuario, String clave, String ruta) throws JSchException,
                                                                                                        SftpException {
    conectar(servidor, usuario, clave);
    try {
      boolean existe = archivoExiste(ruta);
      if (!existe) {
        canal.mkdir(ruta);
        return true;
      }
    } finally {
      desconectar();
    }
    return false;
  }

  public static InputStream buscarArchivo(String servidor, String usuario, String clave,
                                          String ruta) throws JSchException, SftpException {
    InputStream inputStream = null;
    try {
      conectar(servidor, usuario, clave);
      inputStream = canal.get(ruta);
    } finally {
      desconectar();
    }
    return inputStream;
  }

  public static void eliminarArchivo(String servidor, String usuario, String clave, String ruta) throws JSchException,
                                                                                                        SftpException {
    try {
      conectar(servidor, usuario, clave);
      canal.rm(ruta);
    } finally {
      desconectar();
    }
  }

  public static void eliminarCarpeta(String servidor, String usuario, String clave, String ruta) throws JSchException,
                                                                                                        SftpException {
    ChannelExec canalComando = null;
    try {
      abrirSesion(servidor, usuario, clave);
      canalComando = (ChannelExec) sesion.openChannel(CANAL_COMANDO);
      canalComando.setCommand("/bin/rm -rf " + "\"" + ruta + "\"");
      canalComando.connect();
    } finally {
      canalComando.disconnect();
      desconectar();
    }
  }

  public static void crearCarpetas(String servidor, String usuario, String clave, String ruta) throws JSchException,
                                                                                                      SftpException {
    ChannelExec canalComando = null;
    try {
      abrirSesion(servidor, usuario, clave);
      canalComando = (ChannelExec) sesion.openChannel(CANAL_COMANDO);
      canalComando.setCommand("/bin/mkdir -p " + "\"" + ruta + "\"");
      canalComando.connect();
    } finally {
      canalComando.disconnect();
      desconectar();
    }
  }

  public static void moverArchivo(String servidor, String usuario, String clave, String rutaOrigen,
                                  String rutaDestino) throws JSchException, SftpException {
    ChannelExec canalComando = null;
    try {
      abrirSesion(servidor, usuario, clave);
      canalComando = (ChannelExec) sesion.openChannel(CANAL_COMANDO);
      canalComando.setCommand("/bin/mv " + "\"" + rutaOrigen + "\"" + " " + "\"" + rutaDestino + "\"");
      canalComando.connect();
    } finally {
      canalComando.disconnect();
      desconectar();
    }
  }

  private static boolean archivoExiste(String rutaArchivo) {
    boolean archivoExiste = false;
    if (canal != null) {
      try {
        if (canal.get(rutaArchivo) != null) {
          archivoExiste = true;
        }
      } catch (SftpException e) {
        return archivoExiste;
      }
    }
    return archivoExiste;

  }

  private static void conectar(String servidor, String usuario, String clave) throws JSchException {
    abrirSesion(servidor, usuario, clave);
    abrirCanal(sesion);
  }

  private static void abrirSesion(String servidor, String usuario, String clave) throws JSchException {
    JSch jsch = new JSch();
    sesion = jsch.getSession(usuario, servidor, 22);
    sesion.setConfig("StrictHostKeyChecking", "no");
    sesion.setPassword(clave);
    sesion.connect();
    sesion.setTimeout(0);
  }

  private static void abrirCanal(Session sesion) throws JSchException {
    canal = (ChannelSftp) sesion.openChannel(CANAL_SFTP);
    canal.connect();
  }

  private static void desconectar() {
    if (canal != null) {
      canal.exit();
    }
    if (sesion != null) {
      sesion.disconnect();
    }
  }
}
