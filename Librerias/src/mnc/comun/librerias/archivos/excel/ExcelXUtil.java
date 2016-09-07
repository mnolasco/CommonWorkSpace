package mnc.comun.librerias.archivos.excel;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

import mnc.comun.librerias.archivos.ArchivoUtil;

public class ExcelXUtil extends ArchivoUtil {

  private String ruta;

  private XSSFWorkbook libroExcelX;
  private List<HojaExcelUtil> listaHojas;

  public ExcelXUtil(String ruta) {
    this.ruta = ruta;
    listaHojas = new ArrayList<>();
    libroExcelX = new XSSFWorkbook();
  }

  public void agregarHoja(HojaExcelUtil hojaExcel) {
    listaHojas.add(hojaExcel);
  }

  private XSSFSheet crearHojaExcel(String nombreHoja) {
    return libroExcelX.createSheet(nombreHoja);
  }

  public void crear() throws IOException {
    escribir(listaHojas);
    crearLibro();
  }

  private void escribir(List<HojaExcelUtil> listaHojas) {
    for (HojaExcelUtil hoja : listaHojas) {
      XSSFSheet hojaExcelX = crearHojaExcel(hoja.getNombre());
      if (hoja.isCrearTitulo()) {
        escribirTituloGeneral(hojaExcelX, hoja.getTitulo(), 0, 0);
      }
      escribirTituloColumnas(hojaExcelX, hoja.getColumnas(), hoja.getFilaTitulosColumnas());
      escribirData(hojaExcelX, hoja.getData(), hoja.getFilaData());
      expandirColumnas(hojaExcelX, hoja.getColumnas().length);
    }
  }

  private void crearLibro() throws IOException {
    String rutaCarpeta = getRutaCarpeta(ruta);
    crearCarpeta(rutaCarpeta);
    crearArchivo(ruta);

    OutputStream streamExcel = Files.newOutputStream(Paths.get(ruta));
    libroExcelX.write(streamExcel);
    streamExcel.flush();
    streamExcel.close();
  }

  private void escribirTituloColumnas(XSSFSheet hojaExcelX, String[] columnas, Integer fila) {
    XSSFRow filaExcel = hojaExcelX.createRow(fila);
    short tamanioTexto = 12;
    for (int i = 0; i < columnas.length; i++) {
      XSSFCell celda = filaExcel.createCell(i);
      XSSFFont estiloTextoCelda = crearEstiloTexto(XSSFFont.COLOR_NORMAL, XSSFFont.BOLDWEIGHT_BOLD, tamanioTexto);
      XSSFCellStyle estiloCelda =
        crearEstiloCelda(XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN,
                         XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN, estiloTextoCelda);
      escribirDatoEnCelda(columnas[i], celda, estiloCelda);
    }
  }

  private void escribirTituloGeneral(XSSFSheet hojaExcelX, String titulo, int numeroFila, int numeroColumna) {
    short tamanioTexto = 13;
    XSSFFont estiloTextoCeldaX = crearEstiloTexto(XSSFFont.COLOR_NORMAL, XSSFFont.BOLDWEIGHT_BOLD, tamanioTexto);
    XSSFCellStyle estiloCeldaX =
      crearEstiloCelda(XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.BORDER_NONE, XSSFCellStyle.BORDER_NONE,
                       XSSFCellStyle.BORDER_NONE, XSSFCellStyle.BORDER_NONE, estiloTextoCeldaX);
    XSSFRow filaExcelX = hojaExcelX.createRow(numeroFila);
    XSSFCell celdaX = filaExcelX.createCell(numeroColumna);
    escribirDatoEnCelda(titulo, celdaX, estiloCeldaX);
  }

  private void escribirData(XSSFSheet hojaExcelX, List<Object[]> data, int numeroFila) {
    short tamanioTexto = 11;
    XSSFFont estiloTextoCeldaX = crearEstiloTexto(XSSFFont.COLOR_NORMAL, XSSFFont.BOLDWEIGHT_NORMAL, tamanioTexto);
    XSSFCellStyle estiloCeldaX =
      crearEstiloCelda(XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN,
                       XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN, estiloTextoCeldaX);

    for (int fila = 0; fila < data.size(); fila++) {
      XSSFRow filaExcelX = hojaExcelX.createRow(numeroFila + fila);
      Object[] columnas = data.get(fila);
      for (int columna = 0; columna < columnas.length; columna++) {
        XSSFCell celdaX = filaExcelX.createCell(columna);
        Object dato = columnas[columna];
        escribirDatoEnCelda(dato, celdaX, estiloCeldaX);
      }
    }
  }

  public void escribirDatosEnFila(XSSFSheet hojaExcelX, Object datos[], int numeroFila) {
    short tamanioTexto = 10;
    XSSFFont estiloTextoCeldaX = crearEstiloTexto(XSSFFont.COLOR_NORMAL, XSSFFont.BOLDWEIGHT_NORMAL, tamanioTexto);
    XSSFCellStyle estiloCeldaX =
      crearEstiloCelda(XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN,
                       XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN, estiloTextoCeldaX);
    XSSFRow filaExcelX = hojaExcelX.createRow(numeroFila);

    for (int columna = 0; columna < datos.length; columna++) {
      XSSFCell celdaX = filaExcelX.createCell(columna);
      Object dato = datos[columna];
      escribirDatoEnCelda(dato, celdaX, estiloCeldaX);
    }
  }

  private void escribirDatoEnCelda(Object dato, XSSFCell celdaX, XSSFCellStyle estiloCeldaX) {
    if (dato instanceof Double) {
      celdaX.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
      celdaX.setCellValue((Double) dato);
      celdaX.setCellStyle(estiloCeldaX);
    } else if (dato instanceof Integer) {
      celdaX.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
      celdaX.setCellValue((Integer) dato);
      celdaX.setCellStyle(estiloCeldaX);
    } else if (dato instanceof String) {
      celdaX.setCellType(XSSFCell.CELL_TYPE_STRING);
      celdaX.setCellValue((String) dato);
      celdaX.setCellStyle(estiloCeldaX);
    } else if (dato instanceof BigDecimal) {
      celdaX.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
      celdaX.setCellValue(Double.valueOf(BigDecimal.valueOf(Double.valueOf(dato.toString())).setScale(2).toString()));
      estiloCeldaX.setDataFormat(libroExcelX.createDataFormat().getFormat("#,##0.00"));
      celdaX.setCellStyle(estiloCeldaX);
    } else if (dato instanceof Date) {
      short tamanioTexto = 10;
      XSSFFont estiloTextoCeldaX = crearEstiloTexto(XSSFFont.COLOR_NORMAL, XSSFFont.BOLDWEIGHT_NORMAL, tamanioTexto);
      XSSFCellStyle estiloCeldaXDate =
        crearEstiloCelda(XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN,
                         XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN, estiloTextoCeldaX);
      XSSFDataFormat df = libroExcelX.createDataFormat();
      estiloCeldaXDate.setDataFormat(df.getFormat("dd-mm-yyyy"));
      celdaX.setCellValue((Date) dato);
      celdaX.setCellStyle(estiloCeldaXDate);
    } else if (dato == null) {
      celdaX.setCellType(XSSFCell.CELL_TYPE_STRING);
      celdaX.setCellValue("");
      celdaX.setCellStyle(estiloCeldaX);
    }
  }

  private void expandirColumnas(XSSFSheet hojaExcelX, int numeroColumnas) {
    for (int i = 0; i < numeroColumnas; i++) {
      hojaExcelX.autoSizeColumn(i);
    }
  }

  private XSSFFont crearEstiloTexto(short color, short negrita, short tamanioTexto) {
    XSSFFont estiloTextoCeldaX = libroExcelX.createFont();
    estiloTextoCeldaX.setColor(color);
    estiloTextoCeldaX.setBoldweight(negrita);
    estiloTextoCeldaX.setFontHeightInPoints(tamanioTexto);
    return estiloTextoCeldaX;
  }

  private XSSFCellStyle crearEstiloCelda(short alineacion, short bordeInferior, short bordeSuperior, short bordeDerecho,
                                         short bordeIzquierdo, XSSFFont estiloTextoCeldaX) {
    XSSFCellStyle estiloCeldaX = libroExcelX.createCellStyle();
    estiloCeldaX.setAlignment(alineacion);
    estiloCeldaX.setBorderBottom(bordeInferior);
    estiloCeldaX.setBorderTop(bordeSuperior);
    estiloCeldaX.setBorderLeft(bordeIzquierdo);
    estiloCeldaX.setBorderRight(bordeDerecho);
    estiloCeldaX.setFont(estiloTextoCeldaX);
    return estiloCeldaX;
  }

  public void setRuta(String ruta) {
    this.ruta = ruta;
  }

  public String getRuta() {
    return ruta;
  }
}
