package pvt.mke.comun.pruebas;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import pvt.mke.comun.librerias.ftp.SFTPUtil;

public class PruebaSFTP {
  public static void main(String[] args) {
    String servidor = "10.41.190.132";
    String usuario = "svc_auna";
    String clave = "@un@20L5";
    String ruta = "/data02/nuevaCarpeta/prueba.txt";

    File file = new File("E:/prueba/archivos/prueba.txt");
    InputStream stream = null;
    try {
      stream = new FileInputStream(file);
      /*SFTPUtil.crearCarpeta(servidor, usuario, clave, "/data02/nuevaCarpeta");
      SFTPUtil.transferirArchivo(servidor, usuario, clave, ruta, stream);*/
      
      /*InputStream a = SFTPUtil.buscarArchivo(servidor, usuario, clave, "/data02/nuevaCarpeta/prueba45.txt");
    System.out.println(a);*/
      /*SFTPUtil.eliminarArchivo(servidor, usuario, clave, "/data02/prueba/prueba.txt");*/
     /*SFTPUtil.eliminarCarpeta(servidor, usuario, clave, "/data02/prueba/abc");*/
      SFTPUtil.crearCarpetas(servidor, usuario, clave, "/data02/prueba/abc/123");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (JSchException e) {
      e.printStackTrace();
    } catch (SftpException e) {
      e.printStackTrace();
    } finally {
      try {
        stream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
