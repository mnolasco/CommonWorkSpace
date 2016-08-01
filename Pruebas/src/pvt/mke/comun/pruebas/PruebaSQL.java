package pvt.mke.comun.pruebas;

import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;

import pvt.mke.comun.librerias.sql.ConexionBDUtil;
import pvt.mke.comun.librerias.sql.SqlUtil;

import oracle.jdbc.OracleTypes;

public class PruebaSQL {
  public static void main(String[] args) {
    String sql="PCK_SEGURIDAD.PR_VALIDAR_USUARIO";
    String sqlFunction="PCK_UTILITARIOS.FN_GENERAR_LLAVE";
    Connection conexion = null;
    try {
      conexion = ConexionBDUtil.getConexionOracle("jdbc:oracle:thin:@10.41.190.196:1521:HCDDEV ", "hcu_web", "hcu123!");
      SqlUtil sqlUtil = new SqlUtil(sqlFunction,conexion);
      
      sqlUtil.agregarParametroRetorno("LLAVE", OracleTypes.VARCHAR);
      sqlUtil.ejecutarPlSql();
      String llave = (String) sqlUtil.getParametro("LLAVE");
      System.out.println(llave);
      
      //Llamada a procedimiento
      /*sqlUtil.agregarParametroEntrada("1");
      sqlUtil.agregarParametroEntrada("mnolasco");
      sqlUtil.agregarParametroEntrada("67894");
      sqlUtil.agregarParametroSalida("RESULTSET", OracleTypes.CURSOR);
      sqlUtil.ejecutarPlSql();
      ResultSet rs = (ResultSet) sqlUtil.getParametro("RESULTSET");
      while(rs.next()){
        System.out.println(rs.getString(4));
      }*/
      sqlUtil.cerrarSentenciaSql();
      /*rs.close();*/
      
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }finally{
      try{
        ConexionBDUtil.cerrar(conexion);
      }catch(SQLException e){
        e.printStackTrace();
      }
    }
  }
}
