package com.sisnet.aplicacion.manejadores;
/*import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorEventos;
*/
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.AtributoSesion;
import com.sisnet.objetosManejo.listas.ListaAtributosSesion;
import com.sisnet.objetosManejo.listas.ListaConsulta;
import com.sisnet.objetosManejo.listas.ListaNavegacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.manejoPaginaJsp.ItemConsulta;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaAplicacion;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaEstado;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaUbicacionPagina;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class ManejadorSesion
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private HttpSession aSesion;
  private ListaAtributosSesion aListaAtributosSesion;
  public ManejadorSesion(HttpSession pSesion) {
    setSesion(pSesion);
  }
  public HttpSession getSesion() {
    return this.aSesion;
  }
  public void setSesion(HttpSession pSesion) {
    this.aSesion = pSesion;
    setListaAtributosSesion(cargarListaAtributosSesion());
  }
  public ListaAtributosSesion getListaAtributosSesion() {
    setListaAtributosSesion(cargarListaAtributosSesion());
    return this.aListaAtributosSesion;
  }
  public void setListaAtributosSesion(ListaAtributosSesion pListaAtributosSesion) {
    this.aListaAtributosSesion = pListaAtributosSesion;
  }
  public Object obtenerValorAtributoSesion(String pNombreAtributo) {
    Object valor_local = ConstantesGeneral.VALOR_NULO;
    Iterator iterador_local = null;
    AtributoSesion atributoSesion_local = null;
    
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return valor_local;
    }
    
    try {
      if (getListaAtributosSesion() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = getListaAtributosSesion().iterator();
        while (iterador_local.hasNext()) {
          atributoSesion_local = (AtributoSesion)iterador_local.next();
          if (mc.sonCadenasIguales(atributoSesion_local.getNombreAtributo(), pNombreAtributo)) {
            valor_local = atributoSesion_local.getValorAtributo();
            break;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return valor_local;
  }
  private ListaNavegacion obtenerAtributoListaNavegacion() {
    ListaNavegacion listaNavegacion_local = null;
    
    try {
      listaNavegacion_local = (ListaNavegacion)obtenerValorAtributoSesion("listaNavegacion");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaNavegacion_local;
  }
  public void asignarValorAtributoSesion(String pNombreAtributo, Object pValorAtributo) {
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      getSesion().setAttribute(pNombreAtributo, pValorAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarValorAtributoSesionNulo(String pNombreAtributo) {
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      getSesion().setAttribute(pNombreAtributo, null);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private ListaAtributosSesion cargarListaAtributosSesion() {
    ListaAtributosSesion listaAtributosSesion_local = null;
    Enumeration<String> atributos_local = null;
    String nombreAtributo_local = null;
    
    if (getSesion() == ConstantesGeneral.VALOR_NULO) {
      return listaAtributosSesion_local;
    }
    
    try {
      listaAtributosSesion_local = new ListaAtributosSesion();
      
      atributos_local = getSesion().getAttributeNames();
      while (atributos_local.hasMoreElements()) {
        nombreAtributo_local = atributos_local.nextElement();
        listaAtributosSesion_local.adicionar(nombreAtributo_local, getSesion().getAttribute(nombreAtributo_local));
      } 
      if (listaAtributosSesion_local.contarElementos() == 0) {
        listaAtributosSesion_local = null;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      atributos_local = null;
      nombreAtributo_local = null;
    } 
    
    return listaAtributosSesion_local;
  }
  public void adicionarAtributoSesion(AtributoSesion pAtributoSesion) {
    if (pAtributoSesion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      getSesion().setAttribute(pAtributoSesion.getNombreAtributo(), pAtributoSesion.getValorAtributo());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void borrarAtributosSesion() {
    Iterator iterador_local = null;
    AtributoSesion atributoSesion_local = null;
    
    if (getListaAtributosSesion() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = getListaAtributosSesion().iterator();
      while (iterador_local.hasNext()) {
        atributoSesion_local = (AtributoSesion)iterador_local.next();
        getSesion().removeAttribute(atributoSesion_local.getNombreAtributo());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      atributoSesion_local = null;
    } 
  }
  public void borrarAtributosCamposSesion() {
    Iterator iterador_local = null;
    AtributoSesion atributoSesion_local = null;
    String nombreAtributo_local = null;
    
    try {
      iterador_local = getListaAtributosSesion().iterator();
      while (iterador_local.hasNext()) {
        atributoSesion_local = (AtributoSesion)iterador_local.next();
        nombreAtributo_local = atributoSesion_local.getNombreAtributo();
        if (!mc.sonCadenasIguales(nombreAtributo_local, "listaNavegacion") && !mc.sonCadenasIguales(nombreAtributo_local, "listaConsulta") && !mc.sonCadenasIguales(nombreAtributo_local, "basedatossisnet") && !mc.sonCadenasIguales(nombreAtributo_local, "basedatosaplicacion") && !mc.sonCadenasIguales(nombreAtributo_local, "manejadorEventos") && !mc.sonCadenasIguales(nombreAtributo_local, "motorAplicacion") && !mc.sonCadenasIguales(nombreAtributo_local, "listaAtributosRequestMultiparte") && !mc.sonCadenasIguales(nombreAtributo_local, "listaCampoValoresAnteriores") && !mc.sonCadenasIguales(nombreAtributo_local, "mensajeEventos") && !mc.sonCadenasIguales(nombreAtributo_local, "tipoMensajeEventos") && !mc.sonCadenasIguales(nombreAtributo_local, "usuarioActual") && !mc.sonCadenasIguales(nombreAtributo_local, "numeroPaginaNavegacion") && !mc.sonCadenasIguales(nombreAtributo_local, "existenEventosEnEjecucion") && !mc.sonCadenasIguales(nombreAtributo_local, "consultaPrincipal") && !mc.sonCadenasIguales(nombreAtributo_local, "idregistrovisitado") && !mc.sonCadenasIguales(nombreAtributo_local, "consecutivoarchivos"))
        {
          
          getSesion().removeAttribute(atributoSesion_local.getNombreAtributo());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      atributoSesion_local = null;
      nombreAtributo_local = null;
    } 
  }
  public AdministradorBaseDatos obtenerAdministradorBaseDatosSisnet() {
    AdministradorBaseDatos administradorBaseDatos_local = null;
    
    try {
      administradorBaseDatos_local = (AdministradorBaseDatos)obtenerValorAtributoSesion("basedatossisnet");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return administradorBaseDatos_local;
  }
  public AdministradorBaseDatos obtenerAdministradorBaseDatosAplicacion() {
    AdministradorBaseDatos administradorBaseDatos_local = null;
    
    try {
      administradorBaseDatos_local = (AdministradorBaseDatos)obtenerValorAtributoSesion("basedatosaplicacion");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return administradorBaseDatos_local;
  }
  public ListaConsulta obtenerAtributoListaConsulta() {
    ListaConsulta listaConsulta_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO && 
        getListaAtributosSesion() != ConstantesGeneral.VALOR_NULO) {
        listaConsulta_local = (ListaConsulta)obtenerValorAtributoSesion("listaConsulta");
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaConsulta_local;
  }
  public void limpiarAtributoListaConsulta() {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        obtenerAtributoListaConsulta().borrarElementos();
        actualizarAtributoListaConsulta();
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarAtributoListaConsulta() {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        asignarValorAtributoSesion("listaConsulta", obtenerAtributoListaConsulta());
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Aplicacion obtenerAplicacionActual() {
    Aplicacion aplicacionActual_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        aplicacionActual_local = obtenerAtributoListaNavegacion().obtenerAplicacionActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return aplicacionActual_local;
  }
  public GrupoInformacion obtenerGrupoInformacionActual() {
    GrupoInformacion grupoInformacionActual_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        grupoInformacionActual_local = obtenerAtributoListaNavegacion().obtenerGrupoInformacionActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacionActual_local;
  }
  public String obtenerNombreLlavePrimaria() {
    String nombreLlavePrimaria_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        nombreLlavePrimaria_local = obtenerAtributoListaNavegacion().obtenerNombreLlavePrimaria();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreLlavePrimaria_local;
  }
  public int obtenerValorLlavePrimaria() {
    int valorLlavePrimaria_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = obtenerAtributoListaNavegacion().obtenerValorLlavePrimaria();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorLlavePrimaria_local;
  }
  public String obtenerEstadoActual() {
    String estadoActual_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        estadoActual_local = obtenerAtributoListaNavegacion().obtenerEstadoActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return estadoActual_local;
  }
  public Usuario obtenerUsuarioActual() {
    Usuario usuarioActual_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO && 
        getListaAtributosSesion() != ConstantesGeneral.VALOR_NULO) {
        usuarioActual_local = (Usuario)obtenerValorAtributoSesion("usuarioActual");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return usuarioActual_local;
  }
  public int obtenerTipoUsuarioActual() {
    int tipoUsuarioActual_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        tipoUsuarioActual_local = obtenerUsuarioActual().getTipoUsuario().getIdTipoUsuario();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoUsuarioActual_local;
  }
  public int obtenerAccion() {
    int accion_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        accion_local = obtenerAtributoListaNavegacion().obtenerAccion();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return accion_local;
  }
  public int obtenerNumeroPagina() {
    int numeroPagina_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        numeroPagina_local = obtenerAtributoListaNavegacion().obtenerNumeroPagina();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroPagina_local;
  }
  public int obtenerNumeroError() {
    int numeroError_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        numeroError_local = obtenerAtributoListaNavegacion().obtenerNumeroError();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroError_local;
  }
  public int obtenerTipoError() {
    int tipoError_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        tipoError_local = obtenerAtributoListaNavegacion().obtenerTipoError();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoError_local;
  }
  public boolean esConfiguracion() {
    boolean configuracion_local = false;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        configuracion_local = obtenerAtributoListaNavegacion().esConfiguracion();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return configuracion_local;
  }
  public Tabla obtenerTablaActual() {
    Tabla tabla_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        tabla_local = obtenerAtributoListaNavegacion().obtenerTablaActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tabla_local;
  }
  public Tabla obtenerTablaDepende() {
    Tabla tablaDepende_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        tablaDepende_local = obtenerAtributoListaNavegacion().obtenerTablaDepende();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tablaDepende_local;
  }
  public boolean esRecargarPagina() {
    boolean recargarPagina_local = false;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        recargarPagina_local = obtenerAtributoListaNavegacion().esRecargarPagina();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return recargarPagina_local;
  }
  public boolean ejecutarConsulta() {
    boolean ejecutarConsulta_local = false;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        ejecutarConsulta_local = obtenerAtributoListaNavegacion().ejecutarConsulta();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return ejecutarConsulta_local;
  }
  public boolean esDocumento() {
    boolean esDocumento_local = false;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        esDocumento_local = obtenerAtributoListaNavegacion().esDocumento();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esDocumento_local;
  }
  public int obtenerPlantillaUtilizar() {
    int plantillaUtilizar_local = 0;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        plantillaUtilizar_local = obtenerAtributoListaNavegacion().obtenerIdAplicacionPlantilla();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return plantillaUtilizar_local;
  }
  public int obtenerValorLlavePrimariaAnterior() {
    int valorLlavePrimariaAnterior_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimariaAnterior_local = obtenerAtributoListaNavegacion().obtenerValorLlavePrimariaAnterior();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorLlavePrimariaAnterior_local;
  }
  public String obtenerNombreAplicacionActual() {
    String nombreAplicacionActual_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        nombreAplicacionActual_local = obtenerAtributoListaNavegacion().obtenerNombreAplicacionActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreAplicacionActual_local;
  }
  public String obtenerTituloAplicacionActual() {
    String tituloAplicacionActual_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        tituloAplicacionActual_local = obtenerAtributoListaNavegacion().obtenerTituloAplicacionActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tituloAplicacionActual_local;
  }
  public String obtenerNombreGrupoInformacionActual() {
    String nombreGrupoInformacionActual = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        nombreGrupoInformacionActual = obtenerAtributoListaNavegacion().obtenerNombreGrupoInformacionActual();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreGrupoInformacionActual;
  }
  private String obtenerDescripcionGrupoInformacionActual() {
    String descripcionGrupoInformacionActual_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        if (esConfiguracion()) {
          descripcionGrupoInformacionActual_local = ap.obtenerDescripcionGrupoInformacionAdministrador(obtenerGrupoInformacionActual().getIdGrupoInformacion(), obtenerEstadoActual());
        } else {
          
          descripcionGrupoInformacionActual_local = obtenerAtributoListaNavegacion().obtenerDescripcionGrupoInformacionActual();
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionGrupoInformacionActual_local;
  }
  private String obtenerNombreUsuarioActual() {
    String nombreUsuarioActual_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        nombreUsuarioActual_local = obtenerUsuarioActual().getNombreUsuario();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreUsuarioActual_local;
  }
  public String obtenerInformacionActual() {
    String informacionActual_local = "";
    
    try {
      informacionActual_local = obtenerNombreUsuarioActual();
      if (obtenerNumeroPagina() == 12) {
        informacionActual_local = mc.concatenarCadena(informacionActual_local, " Consulta General");
      } else {
        
        informacionActual_local = mc.concatenarCadena(informacionActual_local, ' ' + obtenerEstadoActual());
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return informacionActual_local;
  }
  public ManejadorEventos obtenerManejadorEventos() {
    ManejadorEventos manejadorEventos_local = null;
    
    try {
      manejadorEventos_local = (ManejadorEventos)obtenerValorAtributoSesion("manejadorEventos");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return manejadorEventos_local;
  }
  public void copiarAtributosRequestSesion(HttpServletRequest pRequest) {
    Enumeration<String> enumeration_local = null;
    String nombre_local = null;
    Object valor_local = null;
    Iterator iterator_local = null;
    FileItem fileItem_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (ServletFileUpload.isMultipartContent(pRequest)) {
        if (obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
          iterator_local = obtenerListaAtributosRequestMultiparte().iterator();
          while (iterator_local.hasNext()) {
            fileItem_local = (FileItem)iterator_local.next();
            nombre_local = fileItem_local.getFieldName();
            valor_local = fileItem_local.getString();
            asignarValorAtributoSesion(nombre_local, valor_local);
          } 
        } 
      } else {
        enumeration_local = pRequest.getParameterNames();
        while (enumeration_local.hasMoreElements()) {
          nombre_local = enumeration_local.nextElement();
          valor_local = pRequest.getParameter(nombre_local);
          asignarValorAtributoSesion(nombre_local, valor_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      enumeration_local = null;
      nombre_local = null;
      valor_local = null;
      iterator_local = null;
      fileItem_local = null;
    } 
  }
  public void asignarValoresAtributosSesionACampos(ListaCampo pListaCampo) {
    Iterator iterador_local = null;
    Campo campo_local = null;
    Object valor_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = pListaCampo.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          valor_local = obtenerValorAtributoSesion(campo_local.getNombreCampo());
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
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
      valor_local = null;
    } 
  }
  public void cerrarSesion() {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        borrarAtributosSesion();
        getSesion().invalidate();
        setSesion(null);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void borrarUltimoElementoListaNavegacion() {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        obtenerAtributoListaNavegacion().borrarUltimoElemento();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarManejadorEventos() {
    ManejadorEventos manejadorEventos_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        manejadorEventos_local = obtenerManejadorEventos();
        manejadorEventos_local.setAdministradorBaseDatosSisnet(obtenerAdministradorBaseDatosSisnet());
        manejadorEventos_local.setAdministradorBaseDatosAplicacion(obtenerAdministradorBaseDatosAplicacion());
        manejadorEventos_local.setGrupoInformacion(obtenerGrupoInformacionActual());
        manejadorEventos_local.setUsuario(obtenerUsuarioActual());
        manejadorEventos_local.setIdSesion(obtenerIdSesion());
        manejadorEventos_local.setMotorAplicacion(obtenerMotorAplicacion());
        asignarValorAtributoSesion("manejadorEventos", manejadorEventos_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorEventos_local = null;
    } 
  }
  public void adicionarElementoNavegacionPagina(NavegacionPaginaAplicacion pNavegacionPaginaAplicacion, NavegacionPaginaEstado pNavegacionPaginaEstado, NavegacionPaginaUbicacionPagina pNavegacionPaginaUbicacionPagina) {
    if (pNavegacionPaginaAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNavegacionPaginaEstado == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNavegacionPaginaUbicacionPagina == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO && 
        obtenerAtributoListaNavegacion() != ConstantesGeneral.VALOR_NULO) {
        obtenerAtributoListaNavegacion().adicionar(pNavegacionPaginaAplicacion, pNavegacionPaginaEstado, pNavegacionPaginaUbicacionPagina);
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionarItemConsulta(Campo pCampoDesde, Campo pCampoHasta, int pNivelConsulta, boolean pOrdenadoPor, boolean pOrdenAscendente, boolean pPermitirBorrar) {
    ItemConsulta itemConsulta_local = null;
    boolean adicionar_local = false;
    
    if (pCampoDesde == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pCampoHasta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO && 
        obtenerAtributoListaConsulta() != ConstantesGeneral.VALOR_NULO) {
        adicionar_local = !pOrdenadoPor;
        if (pOrdenadoPor) {
          adicionar_local = !obtenerAtributoListaConsulta().verificarExistenciaOrdenadoPor(pCampoDesde.getNombreCampo());
        }
        if (adicionar_local) {
          itemConsulta_local = new ItemConsulta(pCampoDesde, pCampoHasta, pNivelConsulta, pOrdenadoPor, pOrdenAscendente, pPermitirBorrar);
          
          obtenerAtributoListaConsulta().adicionar(itemConsulta_local);
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      itemConsulta_local = null;
    } 
  }
  public void eliminarItemListaConsulta(int pNivelConsulta) {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        obtenerAtributoListaConsulta().eliminar(pNivelConsulta);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public int obtenerUltimoNivelConsulta() {
    int ultimoNivelConsulta_local = 0;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        ultimoNivelConsulta_local = obtenerAtributoListaConsulta().obtenerUltimoNivel();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return ultimoNivelConsulta_local;
  }
  public void actualizarGrupoInformacionActual(GrupoInformacion pGrupoInformacion) {
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerAtributoListaNavegacion().actualizarGrupoInformacionActual(pGrupoInformacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarValorLlavePrimaria(int pValorLlavePrimaria) {
    try {
      obtenerAtributoListaNavegacion().actualizarValorLlavePrimaria(pValorLlavePrimaria);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarEstadoActual(String pEstadoActual) {
    if (pEstadoActual == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerAtributoListaNavegacion().actualizarEstadoActual(pEstadoActual);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarAccion(int pAccion) {
    try {
      obtenerAtributoListaNavegacion().actualizarAccion(pAccion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarNumeroPagina(int pNumeroPagina) {
    try {
      obtenerAtributoListaNavegacion().actualizarNumeroPagina(pNumeroPagina);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarNumeroError(int pNumeroError) {
    try {
      obtenerAtributoListaNavegacion().actualizarNumeroError(pNumeroError);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTipoError(int pTipoError) {
    try {
      obtenerAtributoListaNavegacion().actualizarTipoError(pTipoError);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarMensajeEventos(String pMensaje) {
    if (pMensaje == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      asignarValorAtributoSesion("mensajeEventos", pMensaje);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTipoMensajeEventos(int pTipoMensaje) {
    try {
      asignarValorAtributoSesion("tipoMensajeEventos", Integer.valueOf(pTipoMensaje));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarConfiguracion(boolean pConfiguracion) {
    try {
      obtenerAtributoListaNavegacion().actualizarConfiguracion(pConfiguracion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTablaActual(Tabla pTabla) {
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerAtributoListaNavegacion().actualizarTablaActual(pTabla);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTablaDepende(Tabla pTabla) {
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerAtributoListaNavegacion().actualizarTablaDepende(pTabla);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarRecargarPagina(boolean pRecargarPagina) {
    try {
      obtenerAtributoListaNavegacion().actualizarRecargarPagina(pRecargarPagina);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarEjecutarConsulta(boolean pEjecutarConsulta) {
    try {
      obtenerAtributoListaNavegacion().actualizarEjecutarConsulta(pEjecutarConsulta);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarEsDocumento(boolean pEsDocumento) {
    try {
      obtenerAtributoListaNavegacion().actualizarEsDocumento(pEsDocumento);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarPlantillaUtilizar(int pPlantillaUtilizar) {
    try {
      obtenerAtributoListaNavegacion().actualizarPlantillaUtilizar(pPlantillaUtilizar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void clonarUltimoElementoListaNavegacion() {
    try {
      obtenerAtributoListaNavegacion().clonarUltimoElemento();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarValoresSesionCamposGrupoInformacion(ListaCampo pListaCampo) {
    Iterator iterator_local = null;
    AtributoSesion atributoSesion_local = null;
    Object valor_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = getListaAtributosSesion().iterator();
      while (iterator_local.hasNext()) {
        atributoSesion_local = (AtributoSesion)iterator_local.next();
        valor_local = atributoSesion_local.getValorAtributo();
        if (valor_local != ConstantesGeneral.VALOR_NULO) {
          pListaCampo.asignarValorCampo(atributoSesion_local.getNombreAtributo(), valor_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      atributoSesion_local = null;
      valor_local = null;
    } 
  }
  public void asignarMaximoTiempoInactividad(int pTiempoSesion) {
    try {
      getSesion().setMaxInactiveInterval(pTiempoSesion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String obtenerRutaRealArchivoSesion(String pRutaArchivo) {
    String rutaRealArchivo_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return rutaRealArchivo_local;
    }
    
    try {
      rutaRealArchivo_local = getSesion().getServletContext().getRealPath(pRutaArchivo);
      rutaRealArchivo_local = mc.reemplazarCadena(rutaRealArchivo_local, String.valueOf("\\\\"), ap.obtenerSeparadorArchivos() + ap.obtenerSeparadorArchivos());
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return rutaRealArchivo_local;
  }
  public String obtenerIdSesion() {
    String idSesion_local = "";
    
    if (getSesion() == ConstantesGeneral.VALOR_NULO) {
      return idSesion_local;
    }
    
    try {
      idSesion_local = getSesion().getId();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return idSesion_local;
  }
  public MotorAplicacion obtenerMotorAplicacion() {
    MotorAplicacion motorAplicacion_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO && 
        getListaAtributosSesion() != ConstantesGeneral.VALOR_NULO) {
        motorAplicacion_local = (MotorAplicacion)obtenerValorAtributoSesion("motorAplicacion");
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return motorAplicacion_local;
  }
  public List obtenerListaAtributosRequestMultiparte() {
    List listaAtributosRequestMultiparte_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO && 
        getListaAtributosSesion() != ConstantesGeneral.VALOR_NULO) {
        listaAtributosRequestMultiparte_local = (List)obtenerValorAtributoSesion("listaAtributosRequestMultiparte");
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaAtributosRequestMultiparte_local;
  }
  public void actualizarAtributoListaAtributosRequestMultiparte(List pListaAtributosRequestMultiparte) {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        asignarValorAtributoSesion("listaAtributosRequestMultiparte", pListaAtributosRequestMultiparte);
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarAtributoListaAtributosRequestMultiparteNulo() {
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        asignarValorAtributoSesionNulo("listaAtributosRequestMultiparte");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Campo obtenerCampoArchivo() {
    Campo campoArchivo_local = null;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        campoArchivo_local = obtenerAtributoListaNavegacion().obtenerCampoArchivo();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoArchivo_local;
  }
  public void actualizarCampoArchivo(Campo pCampoArchivo) {
    if (pCampoArchivo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerAtributoListaNavegacion().actualizarCampoArchivo(pCampoArchivo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String obtenerMensajeEventos() {
    String mensaje_local = "";
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        mensaje_local = obtenerValorAtributoSesion("mensajeEventos").toString();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return mensaje_local;
  }
  public int obtenerTipoMensajeEventos() {
    int tipoMensaje_local = -1;
    
    try {
      if (getSesion() != ConstantesGeneral.VALOR_NULO) {
        tipoMensaje_local = Integer.parseInt(obtenerValorAtributoSesion("tipoMensajeEventos").toString());
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoMensaje_local;
  }
  public void actualizarListaCampoValoresAnteriores(ListaCampo pListaCampoValoresAnteriores) {
    if (pListaCampoValoresAnteriores == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      asignarValorAtributoSesion("listaCampoValoresAnteriores", pListaCampoValoresAnteriores);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public ListaCampo obtenerListaCampoValoresAnteriores() {
    ListaCampo listaCampoValoresAnteriores_local = null;
    
    try {
      listaCampoValoresAnteriores_local = (ListaCampo)obtenerValorAtributoSesion("listaCampoValoresAnteriores");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampoValoresAnteriores_local;
  }
  public void actualizarUsuarioActual(Usuario pUsuario) {
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      asignarValorAtributoSesion("usuarioActual", pUsuario);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public int obtenerNumeroPaginaNavegacion() {
    int numeroPaginaNavegacion_local = 0;
    
    try {
      numeroPaginaNavegacion_local = Integer.parseInt(obtenerValorAtributoSesion("numeroPaginaNavegacion").toString());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroPaginaNavegacion_local;
  }
  public void incrementarNumeroPaginaNavegacion() {
    try {
      asignarValorAtributoSesion("numeroPaginaNavegacion", Integer.valueOf(obtenerNumeroPaginaNavegacion() + 1));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void decrementarNumeroPaginaNavegacion() {
    try {
      if (obtenerNumeroPaginaNavegacion() > 1) {
        asignarValorAtributoSesion("numeroPaginaNavegacion", Integer.valueOf(obtenerNumeroPaginaNavegacion() - 1));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarNumeroPaginaNavegacion(int pNumeroPaginaNavegacion) {
    try {
      asignarValorAtributoSesion("numeroPaginaNavegacion", Integer.valueOf(pNumeroPaginaNavegacion));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public boolean obtenerExistenEventosEnEjecucion() {
    boolean existenEventosEnEjecucion_local = false;
    
    try {
      existenEventosEnEjecucion_local = Boolean.parseBoolean(obtenerValorAtributoSesion("existenEventosEnEjecucion").toString());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return existenEventosEnEjecucion_local;
  }
  public void actualizarExistenEventosEnEjecucion(boolean pExistenEventosEnEjecucion) {
    try {
      asignarValorAtributoSesion("existenEventosEnEjecucion", Boolean.valueOf(pExistenEventosEnEjecucion));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String obtenerConsultaPrincipal() {
    String consultaPrincipal_local = "";
    
    try {
      consultaPrincipal_local = obtenerValorAtributoSesion("consultaPrincipal").toString();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return consultaPrincipal_local;
  }
  public void actualizarConsultaPrincipal(String pConsultaPrincipal) {
    try {
      asignarValorAtributoSesion("consultaPrincipal", pConsultaPrincipal);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public int obtenerIdRegistroVisitado() {
    int idRegistroVisitado_local = -1;
    
    try {
      idRegistroVisitado_local = Integer.parseInt(obtenerValorAtributoSesion("idregistrovisitado").toString());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return idRegistroVisitado_local;
  }
  public void actualizarIdRegistroVisitado(int pIdRegistroVisitado) {
    try {
      asignarValorAtributoSesion("idregistrovisitado", Integer.valueOf(pIdRegistroVisitado));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public int obtenerConsecutivoArchivos() {
    int consecutivoArchivos_local = 0;
    
    try {
      consecutivoArchivos_local = Integer.parseInt(obtenerValorAtributoSesion("consecutivoarchivos").toString());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return consecutivoArchivos_local;
  }
  public void actualizarConsecutivoArchivos(int pConsecutivoArchivos) {
    try {
      asignarValorAtributoSesion("consecutivoarchivos", Integer.valueOf(pConsecutivoArchivos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String obtenerRutaDirectorioUsuarioActual() {
    String directorioUsuarioActual_local = null;
    
    try {
      directorioUsuarioActual_local = "/administrador/" + obtenerUsuarioActual().getNombreUsuario() + ap.obtenerSeparadorArchivos();
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return directorioUsuarioActual_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorSesion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */