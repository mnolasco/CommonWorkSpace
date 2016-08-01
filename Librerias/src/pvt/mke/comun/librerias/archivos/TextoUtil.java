package pvt.mke.comun.librerias.archivos;

import java.util.List;
import java.util.regex.Pattern;

import java.io.File;
import java.io.Writer;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;

public class TextoUtil extends ArchivoUtil {

  public static final String COMA = ",";
  public static final String PALOTE = "|";
  public static final String UFT8 = "utf8";
  public static final String DOBLE_COMILLAS = "\"";

  /**Escribe data en un archivo de texto.
   * @param ruta Ruta completa del archivo incluye la carpeta y nombre del archivo
   * @param data Lista con cadena de textos separados por coma o palote
   * @param utf8 Flag que permite convertir el archivo a utf8
   * @param entreComillas Flag que permite encerrar entre doble comillas cada texto de la cadena
   * @param separador Tipo de separador en la cadena de texto. Palote o coma
   * @throws IOException Error al procesar archivo
   */
  public static void escribir(String ruta, List<String> data, boolean utf8, boolean entreComillas,
                              String separador) throws IOException {
    String rutaCarpeta = getRutaCarpeta(ruta);
    crearCarpeta(rutaCarpeta);
    crearArchivo(ruta);
    escribirLineas(ruta, data, utf8, entreComillas, separador);
  }

  private static void escribirLineas(String ruta, List<String> data, boolean utf8, boolean entreComillas,
                                     String separador) throws IOException {
    BufferedWriter writer = null;
    try {
      writer = Files.newBufferedWriter(Paths.get(ruta),StandardCharsets.UTF_8, StandardOpenOption.WRITE);
      for (String linea : data) {
        if (entreComillas) {
          linea = cerrarColumnasEntreComillas(linea, separador);
        }
        writer.write(linea);
        writer.newLine();
      }
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
    if (utf8) {
      codificarUTF8(new File(ruta));
    }
  }

  /**Encierra entre doble comillas cada columna de la cadena de texto.
   * @param linea cadena de texto. Cada columna es concatenada por el tipo de separador.
   * @param separador Tipo de separador: COMA, PALOTE
   */
  private static String cerrarColumnasEntreComillas(String linea, String separador) {
    String lineaEntreComillas = "";
    String[] datos = linea.split(Pattern.quote(separador));
    int ultimaPosicion = datos.length - 1;

    for (int posicion = 0; posicion < datos.length; posicion++) {
      if (posicion == ultimaPosicion) {
        datos[posicion] = DOBLE_COMILLAS.concat(datos[posicion]).concat(DOBLE_COMILLAS);
        lineaEntreComillas = lineaEntreComillas.concat(datos[posicion]);
      } else {
        datos[posicion] = DOBLE_COMILLAS.concat(datos[posicion]).concat(DOBLE_COMILLAS).concat(separador);
        lineaEntreComillas = lineaEntreComillas.concat(datos[posicion]);
      }
    }
    return lineaEntreComillas;
  }

  /**Permite codificar el archivo a UTF-8.
   * @param archivo
   * @throws FileNotFoundException Archivo no encontrado
   * @throws UnsupportedEncodingException Error al codificar a utf8
   * @throws IOException Error al procesar archivo
   */
  private static void codificarUTF8(File archivo) throws FileNotFoundException, UnsupportedEncodingException,
                                                         IOException {
    byte[] utf8BOM = null;
    String asString = null;
    FileInputStream fis = null;
    Writer out = null;

    try {
      fis = new FileInputStream(archivo);
      byte[] contents = new byte[fis.available()];
      fis.read(contents, 0, contents.length);
      asString = new String(contents);

      utf8BOM = new byte[3];
      utf8BOM[0] = (byte) 0xEF;
      utf8BOM[1] = (byte) 0xBB;
      utf8BOM[2] = (byte) 0xBF;

      FileOutputStream fos = new FileOutputStream(archivo);
      out = new OutputStreamWriter(fos, UFT8);
      DataOutputStream dos = new DataOutputStream(fos);
      dos.write(utf8BOM);
      out.write(new String(asString.getBytes()));
    } finally {
      if (fis != null) {
        fis.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }
}