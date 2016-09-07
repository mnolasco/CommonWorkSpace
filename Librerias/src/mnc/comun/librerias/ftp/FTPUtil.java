package mnc.comun.librerias.ftp;

import java.io.IOException;
import java.io.InputStream;

import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUtil {

  private static FTPClient ftp;
  
  public static boolean transferirArchivo(String servidor, String usuario, String clave, InputStream archivoStream,
                                          String ruta) throws SocketException, IOException {
    boolean transferido = false;

    boolean conectado = conectar(servidor, usuario, clave);
    if (conectado) {
      String rutaCarpeta = ruta.substring(1, ruta.lastIndexOf("/"));
      crearCarpetas(rutaCarpeta);
      transferido = ftp.storeFile(ruta, archivoStream);
      desconectar();
    }
    return transferido;
  }

  public static FTPFile[] listarArchivos(String servidor, String usuario, String clave,
                                         String ruta) throws IOException {
    boolean conectado = conectar(servidor, usuario, clave);
    FTPFile[] archivos = null;
    if (conectado) {
      archivos = ftp.listFiles(ruta);
      desconectar();
    }
    return archivos;
  }

  public static InputStream buscarArchivo(String servidor, String usuario, String clave,
                                          String ruta) throws IOException {
    InputStream fileStream = null;
    boolean conectado = conectar(servidor, usuario, clave);
    if (conectado) {
      fileStream = ftp.retrieveFileStream(ruta);
      desconectar();
    }
    return fileStream;
  }

  public static boolean crearCarpeta(String servidor, String usuario, String clave, String ruta) throws IOException {
    boolean conectado = conectar(servidor, usuario, clave);
    boolean creado = false;
    if (conectado) {
      creado = ftp.makeDirectory(ruta);
      desconectar();
    }
    return creado;
  }


  private static boolean conectar(String servidor, String usuario, String clave) throws SocketException, IOException {
    boolean connectedToServer = false;

    ftp = new FTPClient();
    ftp.enterLocalPassiveMode();
    ftp.connect(servidor);
    connectedToServer = ftp.login(usuario, clave);
    ftp.setFileType(FTP.BINARY_FILE_TYPE);
    return connectedToServer;
  }

  private static boolean crearCarpetas(String ruta) throws IOException {
    String[] carpetas = ruta.split("/");
    if (carpetas != null && carpetas.length > 0) {
      for (String carpeta : carpetas) {
        boolean existe = ftp.changeWorkingDirectory(carpeta);
        if (!existe) {
          boolean creado = ftp.makeDirectory(carpeta);
          if (creado) {
            ftp.changeWorkingDirectory(carpeta);
          } else {
            return false;
          }
        }
      }
    }
    return true;
  }

  private static void desconectar() throws IOException {
    if (ftp != null) {
      ftp.logout();
      ftp.disconnect();
    }
  }
}