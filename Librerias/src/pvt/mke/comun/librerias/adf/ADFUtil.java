package pvt.mke.comun.librerias.adf;

import java.util.Map;
import java.util.Iterator;

import java.io.IOException;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.el.MethodExpression;
import javax.el.ExpressionFactory;

import javax.faces.context.FacesContext;
import javax.faces.component.UIComponent;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.application.FacesMessage;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import oracle.adf.controller.ControllerContext;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

import org.apache.myfaces.trinidad.util.Service;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;

public class ADFUtil {

  public static AdfFacesContext getADFContext() {
    return AdfFacesContext.getCurrentInstance();
  }

  public static FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }

  public static Object getFromRequestScope(String key) {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      Map requestMap = facesContext.getExternalContext().getRequestMap();
      return requestMap.get(key);
    }
    return null;
  }

  public static Object getFromPageFlowScope(String key) {
    AdfFacesContext adfContext = getADFContext();
    Map pageFlowMap = null;
    if (adfContext != null) {
      pageFlowMap = adfContext.getPageFlowScope();
      return pageFlowMap.get(key);
    }
    return null;
  }

  public static Object getFromSessionScope(String key) {
    FacesContext facesContext = getFacesContext();
    Map sessionMap = null;
    if (facesContext != null) {
      sessionMap = facesContext.getExternalContext().getSessionMap();
      return sessionMap.get(key);
    }
    return null;
  }

  public static void removeFromSessionScope(String key) {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      Map sessionState = facesContext.getExternalContext().getSessionMap();
      sessionState.remove(key);
    }
  }

  public static void storeOnSessionScope(String key, Object value) {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      facesContext.getExternalContext().getSessionMap().put(key, value);
    }
  }

  public static void storeOnPageFlowScope(String key, Object value) {
    AdfFacesContext adfContext = getADFContext();
    if (adfContext != null) {
      adfContext.getPageFlowScope().put(key, value);
    }
  }

  public static String getClientIp() {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      ExternalContext externalContext = facesContext.getExternalContext();
      if (externalContext != null) {
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        return request.getRemoteAddr();
      }
    }
    return null;
  }

  public static String getServerIp() {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      ExternalContext externalContext = facesContext.getExternalContext();
      if (externalContext != null) {
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        return request.getServerName();
      }
    }
    return null;
  }

  public static String getHttpScheme() {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      ExternalContext externalContext = facesContext.getExternalContext();
      if (externalContext != null) {
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        return request.getScheme();
      }
    }
    return null;
  }

  public static String getServerPort() {
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      ExternalContext externalContext = facesContext.getExternalContext();
      if (externalContext != null) {
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        return String.valueOf(request.getServerPort());
      }
    }
    return null;
  }
  
  public HttpSession getSession() {
    FacesContext facesContext = getFacesContext();
    ExternalContext externalContext = facesContext.getExternalContext();
    return (HttpSession) externalContext.getSession(false);
  }

  public static UIComponent findComponent(String componentId) {
    UIComponent component = null;
    FacesContext facesContext = getFacesContext();
    if (facesContext != null) {
      UIComponent rootComponent = facesContext.getViewRoot();
      component = findComponent(rootComponent, componentId);
    }
    return component;
  }

  public static String getValueFromInputComponent(String componentId) {
    UIComponent component = findComponent(componentId);
    if (component != null) {
      RichInputText inputText = (RichInputText) component;
      return (String) inputText.getValue();
    }
    return null;
  }

  public static void showMessage(String message, FacesMessage.Severity messageType) {
    FacesMessage facesMessage = new FacesMessage(messageType, message, "");
    getFacesContext().addMessage(null, facesMessage);
  }

  public static void showPopUp(String popupId) {
    RichPopup popUp = (RichPopup) findComponent(popupId);
    RichPopup.PopupHints hints = new RichPopup.PopupHints();
    popUp.setContentDelivery(RichPopup.CONTENT_DELIVERY_LAZY_UNCACHED);
    popUp.show(hints);
  }

  public static void closePopup(String popupId) {
    RichPopup popUp = (RichPopup) findComponent(popupId);
    popUp.hide();
  }

  public static void refreshComponent(UIComponent component) {
    if (component != null) {
      AdfFacesContext adfContext = getADFContext();
      if (adfContext != null) {
        adfContext.addPartialTarget(component);
      }
    }
  }

  public static void setStyleClassToButton(String buttonId, String styleClass) {
    UIComponent component = findComponent(buttonId);
    if (component != null && component instanceof RichButton) {
      RichButton button = (RichButton) component;
      button.setStyleClass(styleClass);
    }
  }

  public static Object invokeExpressionBinding(String expression, Class[] paramTypes, Object[] params) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    ELContext expressionContext = facesContext.getELContext();
    ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
    MethodExpression methodExpression =
      expressionFactory.createMethodExpression(expressionContext, expression, Object.class, paramTypes);
    return methodExpression.invoke(expressionContext, params);
  }

  public static Object resolveExpressionBinding(String expression) {
    FacesContext facesContext = getFacesContext();
    ValueExpression expressionValue = null;
    if (facesContext != null) {
      Application app = facesContext.getApplication();
      ExpressionFactory elFactory = app.getExpressionFactory();
      ELContext elContext = facesContext.getELContext();
      expressionValue = elFactory.createValueExpression(elContext, expression, Object.class);
      return expressionValue.getValue(elContext);
    }
    return null;
  }

  public static Object getManagedBean(String beanName) {
    StringBuffer buff = new StringBuffer("#{");
    buff.append(beanName);
    buff.append("}");
    return resolveExpressionBinding(buff.toString());
  }

  public static void redirectToView(String view) throws IOException {
    FacesContext facesContext = getFacesContext();
    ExternalContext externalContext = facesContext.getExternalContext();
    String urlView = ControllerContext.getInstance().getGlobalViewActivityURL(view);
    externalContext.redirect(urlView);
  }

  public static void callJavaScriptFunction(String function) {
    FacesContext facesContext = getFacesContext();
    ExtendedRenderKitService service = Service.getRenderKitService(facesContext, ExtendedRenderKitService.class);
    service.addScript(facesContext, function);
  }

  /**
   * Obtiene una instancia de un application module invocado cuando no exista el Binding Context, por ejemplo cuando se
   * requiere llamar a un metodo del application module desde un servlet.
   * @param packageAppModule ruta del paquete donde se encuentra el Application Module
   * @param appConfigName nombrel del archivo de configuracion que utiliza el application module en el archivo bc4j.xcfg
   * @return ApplicationModule
   */
  public static ApplicationModule getApplicationModule(String packageAppModule, String appConfigName) {
    return Configuration.createRootApplicationModule(packageAppModule, appConfigName);
  }

  private static UIComponent findComponent(UIComponent component, String componentId) {
    UIComponent childrenComponent = null;
    UIComponent result = null;

    if (componentId.equals(component.getId())) {
      return component;
    }

    Iterator childrensIterator = component.getFacetsAndChildren();
    while (childrensIterator.hasNext() && (result == null)) {
      childrenComponent = (UIComponent) childrensIterator.next();
      if (componentId.equals(childrenComponent.getId())) {
        result = childrenComponent;
        break;
      }
      result = findComponent(childrenComponent, componentId);
      if (result != null) {
        break;
      }
    }
    return result;
  }
}