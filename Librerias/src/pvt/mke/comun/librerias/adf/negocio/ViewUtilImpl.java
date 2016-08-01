package pvt.mke.comun.librerias.adf.negocio;

import oracle.jbo.server.ViewObjectImpl;

public class ViewUtilImpl extends ViewObjectImpl {

  /**
   * Permite que no se ejecute el query del view object al exponerlo en una página.
   */
  public void executeEmptyRowSet() {
    this.executeEmptyRowSet();
  }
}