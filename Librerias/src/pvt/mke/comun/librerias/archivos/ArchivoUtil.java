package pvt.mke.comun.librerias.archivos;

import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ArchivoUtil {

  /**Verifica si la carpeta y/o subcarpetas del archivo estan creadas. Si no lo estan, los crea.
   * @param ruta Ruta de la carpeta del archivo
   * @throws IOException Error al procesar archivo
   */
  protected static void crearCarpeta(String ruta) throws IOException {
    boolean carpetaExiste = archivoExiste(ruta);
    if (!carpetaExiste) {
      Path folderPath = Paths.get(ruta);
      Files.createDirectories(folderPath);
    }
  }

  /**Verfica si el archivo existe.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @return true si el archivo existe
   */
  protected static boolean archivoExiste(String ruta) {
    Path folderPath = Paths.get(ruta);
    return Files.exists(folderPath);
  }

  /**Identifica la ruta de la carpeta del archivo.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @return Ruta de la carpeta del archivo
   */
  protected static String getRutaCarpeta(String ruta) {
    int posicionFinal = ruta.lastIndexOf("/");
    String rutaCarpeta = ruta.substring(0, posicionFinal);
    return rutaCarpeta;
  }

  /**Crea un archivo vacio.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @throws IOException Error al procesar archivo
   */
  protected static void crearArchivo(String ruta) throws IOException {
    Path filePath = Paths.get(ruta);
    Files.createFile(filePath);
  }

  /**Identifica el tamaño del archivo en bytes.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @return Tamaño del archivo en bytes
   * @throws IOException
   */
  protected static long getTamanioArchivo(String ruta) throws IOException {
    Path sourcePath = Paths.get(ruta);
    return Files.size(sourcePath);
  }
  
  /**Identifica el nombre del archivo.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @return Nombre del archivo
   *
   */
  protected static String getNombre(String ruta){
    Path sourcePath = Paths.get(ruta);
    return sourcePath.getFileName().toString();
  }
  
  /**Elimina un archivo.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @throws IOException
   *
   */
  protected static void eliminarArchivo(String ruta) throws IOException {
    Path filePath = Paths.get(ruta);
    Files.delete(filePath);
  }
}
