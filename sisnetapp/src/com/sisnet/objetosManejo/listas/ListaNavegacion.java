package com.sisnet.objetosManejo.listas;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPagina;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaAplicacion;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaEstado;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaUbicacionPagina;
public class ListaNavegacion
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  public void adicionar(NavegacionPaginaAplicacion pNavegacionPaginaAplicacion, NavegacionPaginaEstado pNavegacionPaginaEstado, NavegacionPaginaUbicacionPagina pNavegacionPaginaUbicacionPagina) {
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
      super.adicionar(new NavegacionPagina(pNavegacionPaginaAplicacion, pNavegacionPaginaEstado, pNavegacionPaginaUbicacionPagina));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private NavegacionPagina obtenerUltimoNavegacionPagina() {
    NavegacionPagina navegacionPagina_local = null;
    
    try {
      navegacionPagina_local = (NavegacionPagina)obtenerUltimoElemento();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return navegacionPagina_local;
  }
  public Aplicacion obtenerAplicacionActual() {
    Aplicacion aplicacionActual_local = null;
    
    try {
      aplicacionActual_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getGrupoInformacion().getAplicacion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return aplicacionActual_local;
  }
  public GrupoInformacion obtenerGrupoInformacionActual() {
    GrupoInformacion grupoInformacionActual_local = null;
    
    try {
      grupoInformacionActual_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getGrupoInformacion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacionActual_local;
  }
  public String obtenerNombreLlavePrimaria() {
    String nombreLlavePrimaria_local = "";
    
    try {
      nombreLlavePrimaria_local = obtenerUltimoNavegacionPagina().getNombreLlavePrimaria();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreLlavePrimaria_local;
  }
  public int obtenerValorLlavePrimaria() {
    int valorLlavePrimaria_local = 0;
    
    try {
      valorLlavePrimaria_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getValorLlavePrimaria();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorLlavePrimaria_local;
  }
  public String obtenerEstadoActual() {
    String estadoActual_local = "";
    
    try {
      estadoActual_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().getEstadoActual();
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return estadoActual_local;
  }
  public int obtenerAccion() {
    int accion_local = 0;
    
    try {
      accion_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().getAccion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return accion_local;
  }
  public int obtenerNumeroPagina() {
    int numeroPagina_local = 0;
    
    try {
      numeroPagina_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().getNumeroPagina();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroPagina_local;
  }
  public int obtenerNumeroError() {
    int numeroError_local = 0;
    
    try {
      numeroError_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().getNumeroError();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroError_local;
  }
  public int obtenerTipoError() {
    int tipoError_local = 0;
    
    try {
      tipoError_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().getTipoError();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoError_local;
  }
  public boolean esConfiguracion() {
    boolean configuracion_local = false;
    
    try {
      configuracion_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().esConfiguracion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return configuracion_local;
  }
  public Tabla obtenerTablaActual() {
    Tabla tablaActual_local = null;
    
    try {
      tablaActual_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getTablaActual();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tablaActual_local;
  }
  public Tabla obtenerTablaDepende() {
    Tabla tablaDepende_local = null;
    
    try {
      tablaDepende_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getTablaDepende();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tablaDepende_local;
  }
  public boolean esRecargarPagina() {
    boolean recargaPagina_local = false;
    
    try {
      recargaPagina_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().esRecargarPagina();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return recargaPagina_local;
  }
  public boolean esDocumento() {
    boolean esDocumento_local = false;
    
    try {
      esDocumento_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().esDocumento();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esDocumento_local;
  }
  public int obtenerIdAplicacionPlantilla() {
    int idAplicacionPlantilla_local = 0;
    
    try {
      idAplicacionPlantilla_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().getPlantillaUtilizar();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return idAplicacionPlantilla_local;
  }
  public int obtenerValorLlavePrimariaAnterior() {
    int valorLlavePrimariaAnterior_local = -1;
    int posicion_local = -1;
    
    try {
      posicion_local = size() - 2;
      if (posicion_local > -1) {
        valorLlavePrimariaAnterior_local = ((NavegacionPagina)get(posicion_local)).getNavegacionPaginaAplicacion().getValorLlavePrimaria();
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorLlavePrimariaAnterior_local;
  }
  public void actualizarGrupoInformacionActual(GrupoInformacion pGrupoInformacion) {
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().setGrupoInformacion(pGrupoInformacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarValorLlavePrimaria(int pValorLlavePrimaria) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().setValorLlavePrimaria(pValorLlavePrimaria);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarEstadoActual(String pEstadoActual) {
    if (pEstadoActual == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().setEstadoActual(pEstadoActual);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarAccion(int pAccion) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().setAccion(pAccion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarNumeroPagina(int pNumeroPagina) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().setNumeroPagina(pNumeroPagina);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarNumeroError(int pNumeroError) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().setNumeroError(pNumeroError);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTipoError(int pTipoError) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaEstado().setTipoError(pTipoError);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarConfiguracion(boolean pConfiguracion) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().setEsConfiguracion(pConfiguracion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTablaActual(Tabla pTabla) {
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().setTablaActual(pTabla);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarTablaDepende(Tabla pTabla) {
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().setTablaDepende(pTabla);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarRecargarPagina(boolean pRecargarPagina) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().setRecargarPagina(pRecargarPagina);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarEjecutarConsulta(boolean pEjecutarConsulta) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().setEjecutarConsulta(pEjecutarConsulta);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarEsDocumento(boolean pEsDocumento) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().setEsDocumento(pEsDocumento);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarPlantillaUtilizar(int pPlantillaUtilizar) {
    try {
      obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().setPlantillaUtilizar(pPlantillaUtilizar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void clonarUltimoElemento() {
    NavegacionPagina navegacionPagina_local = null;
    try {
      navegacionPagina_local = obtenerUltimoNavegacionPagina();
      adicionar(ap.obtenerNuevoElementoNavegacionAplicacion(navegacionPagina_local.getNavegacionPaginaAplicacion().getGrupoInformacion(), navegacionPagina_local.getNavegacionPaginaAplicacion().getValorLlavePrimaria(), navegacionPagina_local.getNavegacionPaginaAplicacion().getTablaActual(), navegacionPagina_local.getNavegacionPaginaAplicacion().getTablaDepende(), navegacionPagina_local.getNavegacionPaginaAplicacion().getCampoArchivo()), ap.obtenerNuevoElementoNavegacionEstado(navegacionPagina_local.getNavegacionPaginaEstado().getEstadoActual(), navegacionPagina_local.getNavegacionPaginaEstado().getAccion(), navegacionPagina_local.getNavegacionPaginaEstado().getNumeroPagina(), navegacionPagina_local.getNavegacionPaginaEstado().getNumeroError(), navegacionPagina_local.getNavegacionPaginaEstado().getTipoError()), ap.obtenerNuevoElementoNavegacionUbicacionAplicacion(navegacionPagina_local.getNavegacionPaginaUbicacionPagina().esConfiguracion(), navegacionPagina_local.getNavegacionPaginaUbicacionPagina().esRecargarPagina(), navegacionPagina_local.getNavegacionPaginaUbicacionPagina().esDocumento(), navegacionPagina_local.getNavegacionPaginaUbicacionPagina().getPlantillaUtilizar(), navegacionPagina_local.getNavegacionPaginaUbicacionPagina().esEjecutarConsulta()));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      navegacionPagina_local = null;
    } 
  }
  public String obtenerNombreAplicacionActual() {
    String nombreAplicacionActual_local = "";
    
    try {
      nombreAplicacionActual_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getGrupoInformacion().getAplicacion().getNombreAplicacion());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreAplicacionActual_local;
  }
  public String obtenerTituloAplicacionActual() {
    String tituloAplicacionActual_local = "";
    
    try {
      tituloAplicacionActual_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getGrupoInformacion().getAplicacion().getTituloAplicacion());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tituloAplicacionActual_local;
  }
  public String obtenerNombreGrupoInformacionActual() {
    String nombreGrupoInformacionActual_local = "";
    
    try {
      nombreGrupoInformacionActual_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getGrupoInformacion().getNombreGrupoInformacion());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreGrupoInformacionActual_local;
  }
  public String obtenerDescripcionGrupoInformacionActual() {
    String descripcionGrupoInformacionActual_local = "";
    
    try {
      descripcionGrupoInformacionActual_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getGrupoInformacion().getDescripcionGrupoInformacion());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionGrupoInformacionActual_local;
  }
  public String obtenerNombreTablaActual() {
    String nombreTablaActual_local = "";
    
    try {
      nombreTablaActual_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getTablaActual().getNombreTabla());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreTablaActual_local;
  }
  public String obtenerDescripcionTablaActual() {
    String descripcionTablaActual_local = "";
    
    try {
      descripcionTablaActual_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getTablaActual().getDescripcionTabla());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionTablaActual_local;
  }
  public String obtenerNombreTablaDepende() {
    String nombreTablaDepende_local = "";
    
    try {
      nombreTablaDepende_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getTablaDepende().getNombreTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreTablaDepende_local;
  }
  public String obtenerDescripcionTablaDepende() {
    String descripcionTablaDepende_local = "";
    
    try {
      descripcionTablaDepende_local = mc.convertirAMayusculas(obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getTablaDepende().getDescripcionTabla());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionTablaDepende_local;
  }
  public boolean ejecutarConsulta() {
    boolean ejecutarConsulta_local = false;
    
    try {
      ejecutarConsulta_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaUbicacionPagina().esEjecutarConsulta();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return ejecutarConsulta_local;
  }
  public void borrarUltimoElemento() {
    int cantidad_local = -1;
    
    try {
      cantidad_local = contarElementos();
      if (cantidad_local > 1) {
        remove(get(cantidad_local - 1));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Campo obtenerCampoArchivo() {
    Campo campoArchivo_local = null;
    
    try {
      campoArchivo_local = obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().getCampoArchivo();
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
      obtenerUltimoNavegacionPagina().getNavegacionPaginaAplicacion().setCampoArchivo(pCampoArchivo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaNavegacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */