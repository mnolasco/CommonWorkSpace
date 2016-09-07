package mnc.comun.librerias.archivos;

import java.util.Properties;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PropertyUtil {

  private static Properties propertyFile;

  public static String leerValor(String ruta, String property) throws FileNotFoundException, IOException {
    buscarArchivoProperty(ruta);
    return propertyFile.getProperty(property);
  }

  public static Properties buscarArchivoProperty(String ruta) throws FileNotFoundException, IOException {
    Properties propertyFile = new Properties();
    FileInputStream stream = null;
    try {
      stream = new FileInputStream(ruta);
      propertyFile.load(stream);
    } finally {
      stream.close();
    }
    return propertyFile;
  }
}
