package pvt.mke.comun.librerias.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexionBDUtil {

  public static Connection getConexionOracle(String url, String usuario, String clave) throws ClassNotFoundException,
                                                                                              SQLException {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    return DriverManager.getConnection(url, usuario, clave);
  }

  public static Connection getConexionSQLServer(String url, String usuario, String clave) throws ClassNotFoundException,
                                                                                                 SQLException {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    return DriverManager.getConnection(url, usuario, clave);
  }

  public static Connection getConexionMYSQL(String url, String usuario, String clave) throws ClassNotFoundException,
                                                                                             SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    return DriverManager.getConnection(url, usuario, clave);
  }

  public static boolean cerrar(Connection conexion) throws SQLException {
    boolean conexionCerrada = false;
    if (conexion != null) {
      conexion.close();
      conexionCerrada = true;
    }
    return conexionCerrada;
  }
}