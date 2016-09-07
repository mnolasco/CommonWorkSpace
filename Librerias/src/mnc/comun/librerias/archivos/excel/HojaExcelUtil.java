package mnc.comun.librerias.archivos.excel;

import java.util.List;

public class HojaExcelUtil {
  
  private String titulo;
  private String nombre;
  private String[] titulosColumnas;
  
  private List<Object[]> data;
  
  private Integer filaData;
  private Integer filaTitulosColumnas;
  
  private boolean crearTitulo;

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setData(List<Object[]> data) {
    this.data = data;
  }

  public List<Object[]> getData() {
    return data;
  }

  public void setColumnas(String[] columnas) {
    this.titulosColumnas = columnas;
  }

  public String[] getColumnas() {
    return titulosColumnas;
  }

  public void setFilaData(Integer filaData) {
    this.filaData = filaData;
  }

  public Integer getFilaData() {
    return filaData;
  }

  public void setFilaTitulosColumnas(Integer filaTitulosColumnas) {
    this.filaTitulosColumnas = filaTitulosColumnas;
  }

  public Integer getFilaTitulosColumnas() {
    return filaTitulosColumnas;
  }

  public void setCrearTitulo(boolean crearTitulo) {
    this.crearTitulo = crearTitulo;
  }

  public boolean isCrearTitulo() {
    return crearTitulo;
  }
}
