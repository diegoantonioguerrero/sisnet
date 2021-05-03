package com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaAplicacion;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaEstado;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaUbicacionPagina;
import java.io.Serializable;
public class NavegacionPagina
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private NavegacionPaginaAplicacion aNavegacionPaginaAplicacion;
  private NavegacionPaginaEstado aNavegacionPaginaEstado;
  private NavegacionPaginaUbicacionPagina aNavegacionPaginaUbicacionPagina;
  public NavegacionPagina(NavegacionPaginaAplicacion pNavegacionPaginaAplicacion, NavegacionPaginaEstado pNavegacionPaginaEstado, NavegacionPaginaUbicacionPagina pNavegacionPaginaUbicacionPagina) {
    setNavegacionPaginaAplicacion(pNavegacionPaginaAplicacion);
    setNavegacionPaginaEstado(pNavegacionPaginaEstado);
    setNavegacionPaginaUbicacionPagina(pNavegacionPaginaUbicacionPagina);
  }
  public NavegacionPaginaAplicacion getNavegacionPaginaAplicacion() {
    return this.aNavegacionPaginaAplicacion;
  }
  public void setNavegacionPaginaAplicacion(NavegacionPaginaAplicacion pNavegacionPaginaAplicacion) {
    this.aNavegacionPaginaAplicacion = pNavegacionPaginaAplicacion;
  }
  public NavegacionPaginaEstado getNavegacionPaginaEstado() {
    return this.aNavegacionPaginaEstado;
  }
  public void setNavegacionPaginaEstado(NavegacionPaginaEstado pNavegacionPaginaEstado) {
    this.aNavegacionPaginaEstado = pNavegacionPaginaEstado;
  }
  public NavegacionPaginaUbicacionPagina getNavegacionPaginaUbicacionPagina() {
    return this.aNavegacionPaginaUbicacionPagina;
  }
  public void setNavegacionPaginaUbicacionPagina(NavegacionPaginaUbicacionPagina pNavegacionPaginaUbicacionPagina) {
    this.aNavegacionPaginaUbicacionPagina = pNavegacionPaginaUbicacionPagina;
  }
  public String getNombreLlavePrimaria() {
    String nombreLlavePrimaria_local = "";
    
    try {
      if (getNavegacionPaginaAplicacion().getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(getNavegacionPaginaAplicacion().getGrupoInformacion().getNombreGrupoInformacion());
      } else {
        nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(getNavegacionPaginaAplicacion().getGrupoInformacion().getAplicacion().getNombreAplicacion());
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreLlavePrimaria_local;
  }
  public String obtenerNombreAplicacionActual() {
    String nombreAplicacionActual_local = "";
    
    try {
      nombreAplicacionActual_local = getNavegacionPaginaAplicacion().getGrupoInformacion().getAplicacion().getNombreAplicacion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreAplicacionActual_local;
  }
  public String obtenerTituloAplicacionActual() {
    String tituloAplicacionActual_local = "";
    
    try {
      tituloAplicacionActual_local = getNavegacionPaginaAplicacion().getGrupoInformacion().getAplicacion().getTituloAplicacion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tituloAplicacionActual_local;
  }
  public String obtenerNombreGrupoInformacionActual() {
    String nombreGrupoInformacionActual_local = "";
    
    try {
      nombreGrupoInformacionActual_local = getNavegacionPaginaAplicacion().getGrupoInformacion().getNombreGrupoInformacion();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreGrupoInformacionActual_local;
  }
  public String obtenerDescripcionGrupoInformacionActual() {
    String descripcionGrupoInformacionActual_local = "";
    
    try {
      if (getNavegacionPaginaUbicacionPagina().esConfiguracion()) {
        if (getNavegacionPaginaEstado().getNumeroPagina() == 17) {
          descripcionGrupoInformacionActual_local = mc.concatenarCadena(descripcionGrupoInformacionActual_local, " VALOR ");
        }
        descripcionGrupoInformacionActual_local = mc.concatenarCadena(descripcionGrupoInformacionActual_local, ap.obtenerDescripcionGrupoInformacionAdministrador(getNavegacionPaginaAplicacion().getGrupoInformacion().getIdGrupoInformacion(), getNavegacionPaginaEstado().getEstadoActual()));
      }
      else {
        
        descripcionGrupoInformacionActual_local = getNavegacionPaginaAplicacion().getGrupoInformacion().getDescripcionGrupoInformacion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionGrupoInformacionActual_local;
  }
  public String obtenerNombreTablaActual() {
    String nombreTablaActual_local = "";
    
    try {
      nombreTablaActual_local = getNavegacionPaginaAplicacion().getTablaActual().getNombreTabla();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreTablaActual_local;
  }
  public String obtenerDescripcionTablaActual() {
    String descripcionTablaActual_local = "";
    
    try {
      descripcionTablaActual_local = getNavegacionPaginaAplicacion().getTablaActual().getDescripcionTabla();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionTablaActual_local;
  }
  public String obtenerNombreTablaDepende() {
    String nombreTablaDepende_local = "";
    
    try {
      nombreTablaDepende_local = getNavegacionPaginaAplicacion().getTablaDepende().getNombreTabla();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreTablaDepende_local;
  }
  public String obtenerDescripcionTablaDepende() {
    String descripcionTablaDepende_local = "";
    
    try {
      descripcionTablaDepende_local = getNavegacionPaginaAplicacion().getTablaDepende().getDescripcionTabla();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return descripcionTablaDepende_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\objetosNavegacionPagina\NavegacionPagina.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */