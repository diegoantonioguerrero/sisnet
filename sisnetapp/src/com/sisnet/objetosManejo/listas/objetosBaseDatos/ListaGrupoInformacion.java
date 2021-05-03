package com.sisnet.objetosManejo.listas.objetosBaseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Collection;
import java.util.Iterator;

public class ListaGrupoInformacion
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(GrupoInformacion pGrupoInformacion) {
    try {
      super.adicionar(pGrupoInformacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void concatenar(com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion pListaGrupoInformacion) {
    addAll((Collection)pListaGrupoInformacion);
  }
  public GrupoInformacion obtenerGrupoInformacionPorId(int pIdGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
      if (pIdGrupoInformacion > 0) {
        iterador_local = iterator();
        while (iterador_local.hasNext()) {
          grupoInformacion_local = (GrupoInformacion)iterador_local.next();
          if (pIdGrupoInformacion == grupoInformacion_local.getIdGrupoInformacion()) {
            break;
          }
          grupoInformacion_local = null;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerPrimerGrupoInformacionNoMultipleNoPrincipal() {
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (!grupoInformacion_local.esGrupoInformacionPrincipal() && !grupoInformacion_local.esGrupoInformacionMultiple()) {
          break;
        }
        grupoInformacion_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return grupoInformacion_local;
  }
  public void modificarGrupoInformacionPorId(GrupoInformacion pGrupoInformacionModificado) {
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    if (pGrupoInformacionModificado == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (pGrupoInformacionModificado.getIdGrupoInformacion() == grupoInformacion_local.getIdGrupoInformacion()) {
          grupoInformacion_local = pGrupoInformacionModificado;
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
  }
  public void borrarGrupoInformacionPorId(int pIdGrupoInformacion) {
    Iterator iterador_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        if (pIdGrupoInformacion == ((GrupoInformacion)iterador_local.next()).getIdGrupoInformacion()) {
          iterador_local.remove();
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
  }
  public GrupoInformacion obtenerGrupoInformacionPorDescripcion(int pIdAplicacion, String pDescripcionGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (pIdAplicacion == grupoInformacion_local.getAplicacion().getIdAplicacion() && mc.sonCadenasIgualesIgnorarMayusculas(pDescripcionGrupoInformacion, grupoInformacion_local.getDescripcionGrupoInformacion())) {
          break;
        }
        
        grupoInformacion_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionPorDescripcion(String pDescripcionGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (mc.sonCadenasIgualesIgnorarMayusculas(pDescripcionGrupoInformacion, grupoInformacion_local.getDescripcionGrupoInformacion())) {
          break;
        }
        grupoInformacion_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return grupoInformacion_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosBaseDatos\ListaGrupoInformacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */