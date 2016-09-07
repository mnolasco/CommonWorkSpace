package mnc.comun.librerias.archivos.excel;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import mnc.comun.librerias.archivos.ArchivoUtil;

public class ExcelUtil extends ArchivoUtil {

  public static void main(String[] args) {
    HojaExcelUtil hoja1 = new HojaExcelUtil();
    hoja1.setNombre("HOJA1");
    hoja1.setTitulo("TITULO DEL REPORTE");
    
    String[] columnas1 = {"NOMBRES","APELLIDOS","DNI","FECHA_NACIMIENTO"};
    hoja1.setColumnas(columnas1);

    Object[] data1 = { "MIGUEL ANGEL", "NOLASCO CANDIA", "41704601", "13-04-193" };
    Object[] data2 = { "DEICITA FLOR", "SILVA MEDINA", "46564813", "19-02-1990" };
    Object[] data3 = { "ADMIN", "ADMIN", "41704601", "04-05-1998" };

    List<Object[]> data = new ArrayList<>();
    data.add(data1);
    data.add(data2);
    data.add(data3);

    hoja1.setData(data);
    hoja1.setFilaData(3);
    hoja1.setFilaTitulosColumnas(2);
    
    HojaExcelUtil hoja2 = new HojaExcelUtil();
    hoja2.setNombre("HOJA2");
    hoja2.setColumnas(columnas1);
    hoja2.setTitulo("TITULO DEL REPORTE 2");

    Object[] data4 = { "GUILLERMO", "ROJAS BEDOYA", "41704601", "13-04-193" };
    Object[] data5 = { "ALFREDO JUAN", "MEDINA ROJAS", "46564813", "19-02-1990" };
    Object[] data6 = { "ADMIN", "ADMIN", "41704601", "04-05-1998" };

    List<Object[]> listaData2 = new ArrayList<>();
    listaData2.add(data4);
    listaData2.add(data5);
    listaData2.add(data6);

    hoja2.setData(listaData2);
    hoja2.setFilaData(3);
    hoja2.setFilaTitulosColumnas(2);

    ExcelXUtil libroExcel = new ExcelXUtil("E:/excel/demo1.xlsx");
    libroExcel.agregarHoja(hoja1);
    libroExcel.agregarHoja(hoja2);
    try {
      libroExcel.crear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}