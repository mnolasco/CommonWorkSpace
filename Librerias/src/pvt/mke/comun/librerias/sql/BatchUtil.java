package pvt.mke.comun.librerias.sql;

import java.util.List;

import java.sql.SQLException;
import java.sql.PreparedStatement;

public class BatchUtil {

  /**
   * Inserta una cantidad grande de registros de 1000 en 1000.
   * @param preparedStmt Prepared Statement
   * @param data Lista de datos
   * @throws SQLException
   */
  public static void insertar(PreparedStatement preparedStmt, List<String> data) throws SQLException {
    int numeroLinea = 1;
    for (String linea : data) {
      linea = linea.replaceAll("\"", "");
      String[] arregloCampos = linea.split(",");
      for (int i = 0; i < arregloCampos.length; i++) {
        preparedStmt.setString(i + 1, arregloCampos[i]);
      }
      preparedStmt.addBatch();
      if (numeroLinea % 1000 == 0) {
        preparedStmt.executeBatch();
      }
      numeroLinea = numeroLinea++;
      preparedStmt.executeBatch();
    }
  }
}