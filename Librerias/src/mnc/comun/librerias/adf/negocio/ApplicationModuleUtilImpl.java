package mnc.comun.librerias.adf.negocio;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jbo.server.ApplicationModuleImpl;

public class ApplicationModuleUtilImpl extends ApplicationModuleImpl {

  public Connection getConnection() throws SQLException {
    Statement statement = getDBTransaction().createStatement(0);
    return statement.getConnection();
  }
}
