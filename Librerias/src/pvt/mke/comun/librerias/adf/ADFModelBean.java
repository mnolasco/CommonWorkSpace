package pvt.mke.comun.librerias.adf;

import java.util.Map;
import java.util.HashMap;

import oracle.jbo.Row;

import oracle.adf.model.BindingContext;
import oracle.adf.model.AttributeBinding;
import oracle.adf.model.OperationBinding;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.jbo.uicli.binding.JUCtrlListBinding;

/**
 * Maneja operaciones con los componentes definidos en el pageDef. Esta clase debe ser extendida solo por los managed
 * beans y es utilizada dentro del contexto del framework ADF. No puede ser utilizado en Servlets.
 */
public class ADFModelBean {

  protected final String ACTION_ERRORS = "errors";
  protected final String ACTION_RESULTS = "results";

  /**
   * Obtiene la referencia al archivo DataBindings.cpx
   * @return BindingContext
   */
  protected BindingContext getBindingContext() {
    return BindingContext.getCurrent();
  }

  /**
   * Obtiene la referencia al pageDef.xml asociado.
   * @return DCBindingContainer
   */
  protected DCBindingContainer getBindingContainer() {
    BindingContext bindingContext = getBindingContext();
    DCBindingContainer bindingContainer = null;
    if (bindingContext != null) {
      bindingContainer = (DCBindingContainer) getBindingContext().getCurrentBindingsEntry();
    }
    return bindingContainer;
  }

  /**
   * Obtiene la referencia a un pageDef.xml específico.
   * @param pageDefId Id del page definido en el archivo DataBindings.cpx
   * @return DCBindingContainer
   */
  protected DCBindingContainer getBindingContainer(String pageDefId) {
    BindingContext bindingContext = getBindingContext();
    DCBindingContainer bindingContainer = null;
    if (bindingContext != null) {
      bindingContainer = bindingContext.findBindingContainer(pageDefId);
    }
    return bindingContainer;
  }

  /**
   * Obtiene la referencia al data control del pageDef Asociado.
   * @param dataControl Nombre del DataControl
   * @return DCDataControl
   */
  protected DCDataControl getDataControl(String dataControl) {
    DCBindingContainer bindingContainer = getBindingContainer();
    DCDataControl dcDataControl = null;
    if (bindingContainer != null) {
      dcDataControl = bindingContainer.findDataControl(dataControl);
    }
    return dcDataControl;
  }

  /**
   * Obtiene la referencia al data control de un pageDef específico.
   * @param dataControl Nombre del DataControl
   * @return DCDataControl
   */
  protected DCDataControl getDataControl(String pageDefId, String dataControl) {
    DCBindingContainer bindingContainer = getBindingContainer(pageDefId);
    DCDataControl dcDataControl = null;
    if (bindingContainer != null) {
      dcDataControl = bindingContainer.findDataControl(dataControl);
    }
    return dcDataControl;
  }

  /**
   * Obtiene un componente del pageDef específico.
   * @param pageDefId Id del page definido en el archivo DataBindings.cpx
   * @param objectName Nombre del objecto definido en el archivo del pageDef
   * @return page def
   */
  protected Object getObjectFromBindingContainer(String pageDefId, String objectName) {
    Object objectBinding = null;
    DCBindingContainer bindingContainer = getBindingContainer(pageDefId);
    if (bindingContainer != null) {
      objectBinding = bindingContainer.get(objectName);
    }
    return objectBinding;
  }

  /**
   * Ejecuta una operacion del pageDef asociado
   * @param operation Nombre del action o method binding
   * @param parameters Parametros del action binding
   * @return map con los resultados y errores si es que existen.
   */
  protected Map<String, Object> executeAction(String operation, Map<String, Object> parameters) {
    DCBindingContainer bindingContainer = getBindingContainer();
    Map<String, Object> results = new HashMap<>();
    if (bindingContainer != null) {
      OperationBinding operationBinding = (OperationBinding) bindingContainer.getOperationBinding(operation);
      if (parameters != null) {
        operationBinding.getParamsMap().putAll(parameters);
      }
      operationBinding.execute();
      results.put(ACTION_ERRORS, operationBinding.getErrors());
      results.put(ACTION_RESULTS, operationBinding.getResult());
    }
    return results;
  }

  /**
   * Ejecuta una operacion de un pageDef específico
   * @param pageDefId Id del page definido en el archivo DataBindings.cpx
   * @param operation Nombre del action o method binding
   * @param parameters Parametros del action binding
   * @return map con los resultados y errores si es que existen.
   */
  protected Map<String, Object> executeAction(String pageDefId, String operation, Map<String, Object> parameters) {
    DCBindingContainer bindingContainer = getBindingContainer(pageDefId);
    Map<String, Object> results = new HashMap<>();
    if (bindingContainer != null) {
      OperationBinding operationBinding = (OperationBinding) bindingContainer.getOperationBinding(operation);
      if (parameters != null) {
        operationBinding.getParamsMap().putAll(parameters);
      }
      operationBinding.execute();
      results.put(ACTION_ERRORS, operationBinding.getErrors());
      results.put(ACTION_RESULTS, operationBinding.getResult());
    }
    return results;
  }

  /**
   * Obtiene la referencia del iterador asociado.
   * @param iterator Id del iterador en el pageDef
   * @return DCIteratorBinding
   */
  protected DCIteratorBinding getIterator(String iterator) {
    DCBindingContainer bindingContainer = getBindingContainer();
    DCIteratorBinding iteratorBinding = null;
    if (bindingContainer != null) {
      iteratorBinding = bindingContainer.findIteratorBinding(iterator);
    }
    return iteratorBinding;
  }

  /**
   * Obtiene la referencia del iterador de un pagedef específico.
   * @param iterator Id del iterador en el pageDef
   * @return DCIteratorBinding
   */
  protected DCIteratorBinding getIterator(String pageDefId, String iterator) {
    DCBindingContainer bindingContainer = getBindingContainer(pageDefId);
    DCIteratorBinding iteratorBinding = null;
    if (bindingContainer != null) {
      iteratorBinding = bindingContainer.findIteratorBinding(iterator);
    }
    return iteratorBinding;
  }

  /**
   * Obtiene las filas del iterador asociado. Para ello es necesario que se setee la propiedad
   * del range size a -1 ya que sin ello solo traeria las filas del rango definido en el pageDef.
   * @param iterator Id del iterador en el page def
   * @return Row[]
   */
  protected Row[] getRows(String iterator) {
    DCIteratorBinding iteratorBinding = getBindingContainer().findIteratorBinding(iterator);
    Row[] rows = null;
    if (iteratorBinding != null) {
      iteratorBinding.setRangeSize(-1);
      rows = iteratorBinding.getAllRowsInRange();
    }
    return rows;
  }

  /**
   * Obtiene las filas del iterador de un pageDef específico. Para ello es necesario que se setee la propiedad
   * del range size a -1 ya que sin ello solo traeria las filas del rango definido en el page def.
   * @param pageDefId Id del page definido en el archivo DataBindings.cpx
   * @param iterator Id del iterador en el page def
   * @return Row[]
   */
  protected Row[] getRows(String pageDefId, String iterator) {
    DCIteratorBinding iteratorBinding = getBindingContainer(pageDefId).findIteratorBinding(iterator);
    Row[] rows = null;
    if (iteratorBinding != null) {
      iteratorBinding.setRangeSize(-1);
      rows = iteratorBinding.getAllRowsInRange();
    }
    return rows;
  }

  /**
   * Obtiene la fila seleccionada del iterador.
   * @param iteratorId Id del iterador en el page def
   * @return Row
   */
  protected Row getCurrentRow(String iteratorId) {
    DCIteratorBinding iteratorBinding = getIterator(iteratorId);
    Row row = null;
    if (iteratorBinding != null) {
      row = iteratorBinding.getCurrentRow();
    }
    return row;
  }

  /**
   * Obtiene la fila seleccionada del iterador y del pageDef específico.
   * @param pageDefId Id del page definido en el archivo DataBindings.cpx
   * @param iteratorId Id del iterador en el page def
   * @return Row
   */
  protected Row getCurrentRow(String pageDefId, String iteratorId) {
    DCIteratorBinding iteratorBinding = getIterator(pageDefId, iteratorId);
    Row row = null;
    if (iteratorBinding != null) {
      row = iteratorBinding.getCurrentRow();
    }
    return row;
  }

  /**
   * Obtiene el numero de filas del iterador del pageDef asociado.
   * @param iterator Id del iterador en el pageDef
   * @return Numero de filas
   */
  protected long getRowCount(String iterator) {
    DCIteratorBinding iteratorBinding = getBindingContainer().findIteratorBinding(iterator);
    long rowCount = 0;
    if (iteratorBinding != null) {
      iteratorBinding.setRangeSize(-1);
      rowCount = iteratorBinding.getEstimatedRowCount();
    }
    return rowCount;
  }

  /**
   * Obtiene los valores seleccionados de una lista del pageDef asociado.
   * @param bindingId Id del page definido en el archivo DataBindings.cpx
   * @return Object[]
   */
  protected Object[] getSelectedValuesFromList(String bindingId) {
    JUCtrlListBinding listBinding = (JUCtrlListBinding) getBindingContainer().get(bindingId);
    Object[] selectedValues = null;
    if (listBinding != null) {
      selectedValues = listBinding.getSelectedValues();
    }
    return selectedValues;
  }

  /**
   * Obtiene los valores seleccionados de una lista de un pageDef específico.
   * @param bindingId Id del page definido en el archivo DataBindings.cpx
   * @return Object[]
   */
  protected Object[] getSelectedValuesFromList(String pageDefId, String bindingId) {
    JUCtrlListBinding listBinding = (JUCtrlListBinding) getBindingContainer(pageDefId).get(bindingId);
    Object[] selectedValues = null;
    if (listBinding != null) {
      selectedValues = listBinding.getSelectedValues();
    }
    return selectedValues;
  }

  /**
   *Inicializa el estado de una Row.
   * @param iteratorId id del iterador asociado en el pageDef
   */
  protected void setNewRowState(String iteratorId) {
    getCurrentRow(iteratorId).setNewRowState(Row.STATUS_NEW);
  }

  /**
   *Inicializa el estado de una Row de un pageDef específico.
   * @param iteratorId id del iterador asociado en el pageDef
   */
  protected void setNewRowState(String pageDefId, String iteratorId) {
    getCurrentRow(pageDefId, iteratorId).setNewRowState(Row.STATUS_NEW);
  }

  /**
   * Obtiene el valor de un atributo de un Row del pageDef asociado.
   * @param iterator Nombre del iterador
   * @param attribute Id del atributo en el page def
   * @return Valor del atributo
   */
  protected Object getAttributeFromRow(String iterator, String attribute) {
    DCIteratorBinding iteratorBinding = getIterator(iterator);
    Row row = null;
    if (iteratorBinding != null) {
      row = iteratorBinding.getCurrentRow();
      if (row != null) {
        return row.getAttribute(attribute);
      }
    }
    return null;
  }

  /**
   * Obtiene el valor de un atributo del pageDef asociado.
   * @param attribute Id del atributo en el page def
   * @return Valor del atributo
   */
  protected Object getAttributeValue(String attribute) {
    AttributeBinding attributeBinding = null;
    Object value = null;
    DCBindingContainer bindingContainer = getBindingContainer();
    if (bindingContainer != null) {
      attributeBinding = (AttributeBinding) bindingContainer.getControlBinding(attribute);
      if (attributeBinding != null) {
        value = attributeBinding.getInputValue();
      }
    }
    return value;
  }

  /**
   * Obtiene el valor de un atributo de un pageDef específico.
   * @param pageDefId Id del page definido en el archivo DataBindings.cpx
   * @param attribute Id del atributo en el page def
   * @return Valor del atributo
   */
  protected Object getAttributeValue(String attribute, String pageDefId) {
    AttributeBinding attributeBinding = null;
    Object value = null;
    DCBindingContainer bindingContainer = getBindingContainer(pageDefId);
    if (bindingContainer != null) {
      attributeBinding = (AttributeBinding) bindingContainer.getControlBinding(attribute);
      if (attributeBinding != null) {
        value = attributeBinding.getInputValue();
      }
    }
    return value;
  }

  /**
   * Permite asignar un valor a un atributo del pageDef asociado.
   * @param attributeName Nombre del atributo en el page def
   * @param attributeValue Valor a asignar al atributo
   */
  protected void setAttributeValue(String attributeName, Object attributeValue) {
    AttributeBinding attributeBinding = null;
    DCBindingContainer bindingContainer = getBindingContainer();
    if (bindingContainer != null) {
      attributeBinding = (AttributeBinding) bindingContainer.getControlBinding(attributeName);
      if (attributeBinding != null) {
        attributeBinding.setInputValue(attributeValue);
      }
    }
  }

  /**
   * Permite asignar un valor a un atributo de un pageDef específico.
   * @param attributeName Nombre del atributo en el page def
   * @param attributeValue Valor a asignar al atributo
   * @param pageDefId Id del pageDef
   */
  protected void setAttributeValue(String pageDefId, String attributeName, Object attributeValue) {
    AttributeBinding attributeBinding = null;
    DCBindingContainer bindingContainer = getBindingContainer(pageDefId);
    if (bindingContainer != null) {
      attributeBinding = (AttributeBinding) bindingContainer.getControlBinding(attributeName);
      if (attributeBinding != null) {
        attributeBinding.setInputValue(attributeValue);
      }
    }
  }
}
