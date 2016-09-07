package mnc.comun.librerias.adf;

import java.sql.SQLException;

import java.net.SocketException;

import oracle.jbo.DMLException;
import oracle.jbo.SQLStmtException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCErrorHandlerImpl;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

public class ADFErrorHandlerUtil extends DCErrorHandlerImpl {

  public ADFErrorHandlerUtil() {
    super(true);
  }

  public ADFErrorHandlerUtil(boolean value) {
    super(value);
  }

  @Override
  public String getDisplayMessage(BindingContext ctx, Exception exception) {
    String message = "";
    if (exception instanceof DMLException) {
      String msg = exception.getMessage();
      int i = msg.indexOf("JBO-26061");
      if (i >= 0) {
        message = "No se puede abrir conexión de base datos.";
      }
    } else if (exception instanceof SQLStmtException) {
      message = "No se pudo ejecutar una sentencia sql.";
    } else if (exception instanceof CommunicationsException) {
      message = "La aplicación no puede conectarse a la base datos.";
    } else if (exception instanceof SocketException) {
      message = "Conexión Fallida.";
    } else if (exception instanceof MySQLNonTransientConnectionException) {
      message = "No se puede ejecutar ninguna operación sql.";
    } else if (exception instanceof NullPointerException) {
      message = "Se encontró un objeto nulo donde no se esperaba.";
    } else if (exception instanceof SQLException) {
      message = "No se puede acceder a la base de datos.";
    } else {
      message = exception.getMessage();
    }
    return message;
  }

  @Override
  public void reportException(DCBindingContainer bindingContainer, Exception exception) {
    /*if (exception instanceof SQLStmtException) {
      SQLStmtException sqlstmtException = (SQLStmtException) exception;
      Object[] exceptionDetails = sqlstmtException.getDetails();
      if (exceptionDetails != null) {
        for (int i = 0; i < exceptionDetails.length; i++) {
          if (exceptionDetails[i] instanceof CommunicationsException) {
            super.reportException(bindingContainer, (Exception) exceptionDetails[i]);
          }
        }
      } else {
        super.reportException(bindingContainer, exception);
      }
    }*/
    super.reportException(bindingContainer, exception);
  }

  @Override
  protected boolean skipException(Exception exception) {
    /*if (exception instanceof SQLStmtException) {
      return true;
    } else if (exception instanceof CommunicationsException) {
      return false;
    } else {
      return super.skipException(exception);
    }*/
    return super.skipException(exception);
  }
}