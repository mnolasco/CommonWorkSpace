package mnc.comun.librerias.sql;

public class Parametro {
  
  protected int tipo = 0;
  protected int tipoParametroSql = 0;

  protected String llave;
  protected Object valor;
  
  public final static int SALIDA = 2;
  public final static int ENTRADA = 1;
  public final static int RETORNO = 4;
  public final static int ENTRADA_SALIDA = 3;

  public Parametro(int tipoParametro, Object valor, String llave, int tipoParametroSql) {
    this.valor = valor;
    this.llave = llave;
    this.tipoParametroSql = tipoParametroSql;
    this.tipo = tipoParametro;
  }
}
