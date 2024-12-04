package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorArchivos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorSesion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.AtributoRequest;
import com.sisnet.objetosManejo.listas.ListaAtributosRequest;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItemFactory;
public class ManejadorRequest
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private HttpServletRequest aRequest;
  private ListaAtributosRequest aListaAtributosRequest;
  public ManejadorRequest(HttpServletRequest pRequest) {
    setRequest(pRequest);
  }
  public HttpServletRequest getRequest() {
    return this.aRequest;
  }
  public void setRequest(HttpServletRequest pRequest) {
    this.aRequest = pRequest;
    setListaAtributosRequest(cargarListaParametrosRequest());
  }
  public ListaAtributosRequest getListaAtributosRequest() {
    return this.aListaAtributosRequest;
  }
  public void setListaAtributosRequest(ListaAtributosRequest pListaAtributosRequest) {
    this.aListaAtributosRequest = pListaAtributosRequest;
  }
  public HttpSession obtenerNuevaSesion() {
    HttpSession sesion_local = null;
    
    try {
      sesion_local = getRequest().getSession(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return sesion_local;
  }
  private ListaAtributosRequest cargarListaParametrosRequest() {
    ListaAtributosRequest listaAtributosRequest_local = null;
    Enumeration<String> atributos_local = null;
    String nombreAtributo_local = null;
    
    try {
      listaAtributosRequest_local = new ListaAtributosRequest();
      atributos_local = getRequest().getParameterNames();
      while (atributos_local.hasMoreElements()) {
        nombreAtributo_local = atributos_local.nextElement();
        listaAtributosRequest_local.adicionar(nombreAtributo_local, getRequest().getParameter(nombreAtributo_local));
      } 
      if (listaAtributosRequest_local.contarElementos() == 0) {
        listaAtributosRequest_local = null;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      atributos_local = null;
      nombreAtributo_local = null;
    } 
    
    return listaAtributosRequest_local;
  }
  public void borrarParametrosRequest() {
    ListaAtributosRequest listaAtributosRequest_local = null;
    Iterator iterador_local = null;
    AtributoRequest atributoRequest_local = null;
    String nombreAtributo_local = null;
    
    if (getRequest() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaAtributosRequest_local = cargarListaParametrosRequest();
      if (listaAtributosRequest_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaAtributosRequest_local.iterator();
        while (iterador_local.hasNext()) {
          atributoRequest_local = (AtributoRequest)iterador_local.next();
          nombreAtributo_local = atributoRequest_local.getNombreAtributo();
          getRequest().removeAttribute(nombreAtributo_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaAtributosRequest_local = null;
      iterador_local = null;
      atributoRequest_local = null;
      nombreAtributo_local = null;
    } 
  }
  private ListaAtributosRequest cargarListaAtributosRequest() {
    ListaAtributosRequest listaAtributosRequest_local = null;
    Enumeration<String> atributos_local = null;
    String nombreAtributo_local = null;
    
    try {
      listaAtributosRequest_local = new ListaAtributosRequest();
      atributos_local = getRequest().getAttributeNames();
      while (atributos_local.hasMoreElements()) {
        nombreAtributo_local = atributos_local.nextElement();
        listaAtributosRequest_local.adicionar(nombreAtributo_local, getRequest().getAttribute(nombreAtributo_local));
      } 
      if (listaAtributosRequest_local.contarElementos() == 0) {
        listaAtributosRequest_local = null;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      atributos_local = null;
      nombreAtributo_local = null;
    } 
    
    return listaAtributosRequest_local;
  }
  public void borrarAtributosRequest() {
    ListaAtributosRequest listaAtributosRequest_local = null;
    Iterator iterador_local = null;
    AtributoRequest atributoRequest_local = null;
    String nombreAtributo_local = null;
    
    if (getRequest() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaAtributosRequest_local = cargarListaAtributosRequest();
      if (listaAtributosRequest_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaAtributosRequest_local.iterator();
        while (iterador_local.hasNext()) {
          atributoRequest_local = (AtributoRequest)iterador_local.next();
          nombreAtributo_local = atributoRequest_local.getNombreAtributo();
          getRequest().removeAttribute(nombreAtributo_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaAtributosRequest_local = null;
      iterador_local = null;
      atributoRequest_local = null;
      nombreAtributo_local = null;
    } 
  }
  public Object obtenerValorAtributoRequest(String pNombreAtributo, ManejadorSesion pManejadorSesion) {
    Object valor_local = ConstantesGeneral.VALOR_NULO;
    AtributoRequest atributoRequest_local = null;
    Iterator iteradorAtributoRequest_local = null;
    DiskFileItemFactory diskFileItemFactory_local = null;
    ServletFileUpload servletFileUpload_local = null;
    List items_local = null;
    FileItem fileItem_local = null;
    
    if (getListaAtributosRequest() == ConstantesGeneral.VALOR_NULO) {
      return valor_local;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return valor_local;
    }
    
    try {
      diskFileItemFactory_local = new DiskFileItemFactory();
      servletFileUpload_local = new ServletFileUpload((FileItemFactory)diskFileItemFactory_local);
      
      if (ServletFileUpload.isMultipartContent(getRequest())) {
        items_local = servletFileUpload_local.parseRequest(getRequest());
        
        if (pManejadorSesion.obtenerListaAtributosRequestMultiparte() == ConstantesGeneral.VALOR_NULO || pManejadorSesion.esRecargarPagina())
        {
          if (items_local.size() > 0) {
            pManejadorSesion.actualizarAtributoListaAtributosRequestMultiparte(items_local);
          }
        }
      } 
      
      if (pManejadorSesion != ConstantesGeneral.VALOR_NULO && pManejadorSesion.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
        
        iteradorAtributoRequest_local = pManejadorSesion.obtenerListaAtributosRequestMultiparte().iterator();
      }
      else if (items_local != ConstantesGeneral.VALOR_NULO) {
        iteradorAtributoRequest_local = items_local.iterator();
      } 
      
      if (iteradorAtributoRequest_local != ConstantesGeneral.VALOR_NULO) {
        while (iteradorAtributoRequest_local.hasNext()) {
          fileItem_local = (FileItem)iteradorAtributoRequest_local.next();
          if (mc.sonCadenasIgualesIgnorarMayusculas(fileItem_local.getFieldName(), pNombreAtributo)) {
            valor_local = fileItem_local.getString();
            
            break;
          } 
        } 
      }
      iteradorAtributoRequest_local = getListaAtributosRequest().iterator();
      while (iteradorAtributoRequest_local.hasNext()) {
        atributoRequest_local = (AtributoRequest)iteradorAtributoRequest_local.next();
        if (mc.sonCadenasIgualesIgnorarMayusculas(atributoRequest_local.getNombreAtributo(), pNombreAtributo)) {
          valor_local = atributoRequest_local.getValorAtributo();
          
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      atributoRequest_local = null;
      diskFileItemFactory_local = null;
      servletFileUpload_local = null;
      fileItem_local = null;
      items_local = null;
    } 
    
    return valor_local;
  }
  public HttpSession obtenerSesion() {
    HttpSession sesion_local = null;
    if (getRequest() == ConstantesGeneral.VALOR_NULO) {
      return sesion_local;
    }
    
    try {
      if (getRequest().getSession().getAttributeNames().hasMoreElements()) {
        sesion_local = getRequest().getSession();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return sesion_local;
  }
  public void asignarValoresAtributosRequestACampos(ListaCampo pListaCampo, ManejadorSesion pManejadorSesion) {
    Iterator iterador_local = null;
    Campo campo_local = null;
    Object valor_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pManejadorSesion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = pListaCampo.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        valor_local = obtenerValorAtributoRequest(campo_local.getNombreCampo(), pManejadorSesion);
        
        if (valor_local != ConstantesGeneral.VALOR_NULO) {
          if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "I")) {
            valor_local = ap.obtenerDescripcionTipoLicencia(valor_local.toString());
          }
          if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "B") && (mc.sonCadenasIguales(valor_local.toString(), "Si") || mc.sonCadenasIguales(valor_local.toString(), "No")))
          {
            
            valor_local = String.valueOf(ap.obtenerEquivalenteBooleanoValor(valor_local.toString()));
          }
          campo_local.setValorCampo(valor_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
      valor_local = null;
    } 
  }
  public void subirArchivo(FileItem pFileItem, String pDirectorioCargaArchivo) {
    String rutaArchivo_local = null;
    String nombreArchivo_local = null;
    byte[] contenidoArchivo_local = null;
    File fileDestino_local = null;
    FileOutputStream fileOutputStream_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    
    if (pFileItem == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDirectorioCargaArchivo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (!pFileItem.isFormField()) {
        rutaArchivo_local = pFileItem.getName();
        nombreArchivo_local = mc.convertirTildeANoTilde(ap.obtenerNombreArchivo(rutaArchivo_local));
        nombreArchivo_local = mc.reemplazarCadena(nombreArchivo_local, " ", "_");
        manejadorArchivos_local = new ManejadorArchivos();
        if (manejadorArchivos_local.existeArchivo(pDirectorioCargaArchivo + ap.obtenerSeparadorArchivos() + nombreArchivo_local))
        {
          manejadorArchivos_local.borrarArchivo(pDirectorioCargaArchivo + ap.obtenerSeparadorArchivos() + nombreArchivo_local);
        }
        contenidoArchivo_local = pFileItem.get();
        if (contenidoArchivo_local != ConstantesGeneral.VALOR_NULO) {
          fileDestino_local = new File(pDirectorioCargaArchivo + ap.obtenerSeparadorArchivos() + nombreArchivo_local);
          fileOutputStream_local = new FileOutputStream(fileDestino_local);
          fileOutputStream_local.write(contenidoArchivo_local);
          fileOutputStream_local.close();
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fileDestino_local = null;
      rutaArchivo_local = null;
      nombreArchivo_local = null;
      contenidoArchivo_local = null;
      fileOutputStream_local = null;
      manejadorArchivos_local = null;
    } 
  }
  public String obtenerNombreRecursoAplicacion() {
    String nombreRecursoAplicacion_local = null;
    
    try {
      nombreRecursoAplicacion_local = getRequest().getContextPath();
      nombreRecursoAplicacion_local = mc.obtenerSubCadena(nombreRecursoAplicacion_local, 1, mc.obtenerLongitudCadena(nombreRecursoAplicacion_local));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreRecursoAplicacion_local;
  }
  public String obtenerNombreRecursoAplicacionWeb() {
    String nombreRecursoAplicacionWeb_local = null;
    
    try {
      nombreRecursoAplicacionWeb_local = mc.concatenarCadena(obtenerNombreRecursoAplicacion(), "web");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreRecursoAplicacionWeb_local;
  }
  public String obtenerRecursoAplicacion() {
    String recursoAplicacion_local = null;
    
    try {
      recursoAplicacion_local = getRequest().getContextPath();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return recursoAplicacion_local;
  }
  public URL obtenerURLAplicacion() {
    URL URL_local = null;
    StringBuffer ruta_local = null;
    
    try {
      ruta_local = getRequest().getRequestURL();
      URL_local = new URL(ruta_local.toString());
      System.out.println("Ruteo" + URL_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      ruta_local = null;
    } 
    
    return URL_local;
  }
  public URL obtenerURLAplicacionConRecurso() {
    URL URL_local = null;
    URL URLRuta_local = null;
    String recurso_local = null;
    
    try {
      URLRuta_local = obtenerURLAplicacion();
      recurso_local = obtenerRecursoAplicacion();
      URL_local = new URL(URLRuta_local, recurso_local + ap.obtenerSeparadorArchivos());
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      URLRuta_local = null;
      recurso_local = null;
    } 
    
    return URL_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */