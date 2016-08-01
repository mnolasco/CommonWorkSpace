package pvt.mke.comun.librerias.sql;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class SqlUtil {

  private int contador = 0;
  private String sentenciaSql;
  private Connection conexion;
  private String concatenador = "";
  private boolean tieneTipoRetorno;
  private CallableStatement callStatement;
  private Map<String, Object> mapParametrosRetorno = new HashMap<>();
  private List<Parametro> listaParametros = new ArrayList<Parametro>();
  private Map<String, Object> mapParametros = new HashMap<String, Object>();

  public SqlUtil(String sentenciaSql, Connection conexion) {
    this.conexion = conexion;
    this.sentenciaSql = sentenciaSql;
  }

  public void agregarParametroEntrada(Object parametro) {
    listaParametros.add(new Parametro(Parametro.ENTRADA, parametro, null, 0));
  }

  public void agregarParametroSalida(String llave, int tipoParametroSql) {
    listaParametros.add(new Parametro(Parametro.SALIDA, null, llave, tipoParametroSql));
  }

  public void agregarParametroEntradaSalida(Object parametro, String llave, int tipoParametroSql) {
    listaParametros.add(new Parametro(Parametro.ENTRADA_SALIDA, parametro, llave, tipoParametroSql));
  }

  public void agregarParametroRetorno(String llave, int tipoParametroSql) {
    listaParametros.add(new Parametro(Parametro.RETORNO, null, llave, tipoParametroSql));
  }

  public void ejecutarPlSql() throws SQLException {
    construirSentenciaSql();
    if (listaParametros != null) {
      agregarParametros();
    }
    callStatement.execute();

    if (tieneTipoRetorno) {
      Iterator<String> iterador = mapParametros.keySet().iterator();
      while (iterador.hasNext()) {
        String llave = iterador.next();
        mapParametros.put(llave, getParametroRetorno(llave));
      }
    }
  }

  private void construirSentenciaSql() throws SQLException {
    if (listaParametros != null) {
      for (Parametro parametro : listaParametros) {
        switch (parametro.tipo) {
          case Parametro.ENTRADA:
          case Parametro.SALIDA:
          case Parametro.ENTRADA_SALIDA:
            concatenador += "?,";
            break;
          case Parametro.RETORNO:
            tieneTipoRetorno = true;
            break;
        }
      }
      if (concatenador.length() > 0) {
        sentenciaSql += "(" + concatenador.substring(0, concatenador.length() - 1) + ")";
      }
    }
    sentenciaSql = tieneTipoRetorno ? "begin ? := " + sentenciaSql + "; end;" : "begin " + sentenciaSql + "; end;";
    callStatement = conexion.prepareCall(sentenciaSql);
  }

  private void agregarParametros() throws SQLException {
    for (Parametro parametro : listaParametros) {
      switch (parametro.tipo) {
        case Parametro.ENTRADA:
          contador++;
          callStatement.setObject(contador, parametro.valor);
          break;
        case Parametro.SALIDA:
          contador++;
          callStatement.registerOutParameter(contador, parametro.tipoParametroSql);
          mapParametrosRetorno.put(parametro.llave, contador);
          tieneTipoRetorno = true;
          mapParametros.put(parametro.llave, null);
          break;
        case Parametro.ENTRADA_SALIDA:
          contador++;
          callStatement.setObject(contador, parametro.valor);
          callStatement.registerOutParameter(contador, parametro.tipoParametroSql);
          mapParametrosRetorno.put(parametro.llave, contador);
          tieneTipoRetorno = true;
          mapParametros.put(parametro.llave, null);
          break;
        case Parametro.RETORNO:
          callStatement.registerOutParameter(++contador, parametro.tipoParametroSql);
          mapParametrosRetorno.put(parametro.llave, contador);
          tieneTipoRetorno = true;
          mapParametros.put(parametro.llave, null);
          break;
      }
    }
  }

  public Object getParametro(String llave) {
    return mapParametros.get(llave);
  }

  private Object getParametroRetorno(String llave) throws SQLException {
    return callStatement.getObject(Integer.parseInt(mapParametrosRetorno.get(llave).toString()));
  }

  public void cerrarSentenciaSql() throws SQLException {
    if (callStatement != null) {
      callStatement.close();
    }
  }
}