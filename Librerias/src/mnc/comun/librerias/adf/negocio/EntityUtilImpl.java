package mnc.comun.librerias.adf.negocio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.DBTransaction;

public class EntityUtilImpl extends EntityImpl {

  private final String MYSQL_NEXT_AUTOINCREMENT_SQL =
    "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = ?";
  private final String MYSQL_LAST_INSERT_ID_SQL = "SELECT last_insert_id()";

  public Integer getLastMySQLId(DBTransaction transaccion) throws SQLException {
    PreparedStatement statement = transaccion.createPreparedStatement(MYSQL_LAST_INSERT_ID_SQL, 1);
    statement.execute();
    ResultSet resultSet = statement.getResultSet();
    if (resultSet.next()) {
      return resultSet.getInt(1);
    }
    return -1;
  }

  public Integer getNextAutoIncrementMySQL(String tabla, DBTransaction transaccion) throws SQLException {
    PreparedStatement statement = transaccion.createPreparedStatement(MYSQL_NEXT_AUTOINCREMENT_SQL, 1);
    statement.setString(1, tabla);
    statement.execute();
    ResultSet resultSet = statement.getResultSet();
    if (resultSet.next()) {
      return resultSet.getInt(1);
    }
    return -1;
  }
}