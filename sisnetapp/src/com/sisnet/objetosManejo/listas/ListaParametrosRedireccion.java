package com.sisnet.objetosManejo.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoPaginaJsp.ParametroRedireccion;
import java.util.Iterator;
public class ListaParametrosRedireccion
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aRecursoDestino;
  public ListaParametrosRedireccion() {
    setRecursoDestino("administradorServlet");
  }
  public String getRecursoDestino() {
    return this.aRecursoDestino;
  }
  public void setRecursoDestino(String pRecursoDestino) {
    this.aRecursoDestino = pRecursoDestino;
  }
  public void adicionar(String pNombreParametro, String pValorParametro) {
    if (pNombreParametro == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorParametro == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(new ParametroRedireccion(pNombreParametro, pValorParametro));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public boolean verificarExistenciaParametro(String pNombreParametro) {
    boolean existenciaParametro_local = false;
    String nombreParametro_local = null;
    Iterator iterador_local = null;
    
    if (this == ConstantesGeneral.VALOR_NULO) {
      return existenciaParametro_local;
    }
    if (pNombreParametro == ConstantesGeneral.VALOR_NULO) {
      return existenciaParametro_local;
    }
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        nombreParametro_local = ((ParametroRedireccion)iterador_local.next()).getNombreParametro();
        existenciaParametro_local = mc.sonCadenasIguales(nombreParametro_local, pNombreParametro);
        if (existenciaParametro_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      nombreParametro_local = null;
    } 
    
    return existenciaParametro_local;
  }
  public void actualizarParametroRedireccion(String pNombreParametro, String pValorParametro) {
    ParametroRedireccion parametroRedireccion_local = null;
    Iterator iterador_local = null;
    
    if (this == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombreParametro == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorParametro == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (verificarExistenciaParametro(pNombreParametro)) {
        iterador_local = iterator();
        while (iterador_local.hasNext()) {
          parametroRedireccion_local = (ParametroRedireccion)iterador_local.next();
          if (mc.sonCadenasIguales(parametroRedireccion_local.getNombreParametro(), pNombreParametro)) {
            parametroRedireccion_local.setValorParametro(pValorParametro);
            break;
          } 
        } 
      } else {
        adicionar(pNombreParametro, pValorParametro);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      parametroRedireccion_local = null;
    } 
  }
  public String concatenarParametros() {
    String parametros_local = "";
    ParametroRedireccion parametroRedireccion_local = null;
    Iterator iterator_local = null;
    
    try {
      if (contarElementos() > 0) {
        parametros_local = mc.concatenarCadena(getRecursoDestino(), String.valueOf('?'));
        
        iterator_local = iterator();
        while (iterator_local.hasNext()) {
          parametroRedireccion_local = (ParametroRedireccion)iterator_local.next();
          parametros_local = mc.concatenarCadena(parametros_local, parametroRedireccion_local.conformarParametro());
          if (iterator_local.hasNext()) {
            parametros_local = mc.concatenarCadena(parametros_local, String.valueOf('&'));
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      parametroRedireccion_local = null;
    } 
    
    return parametros_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaParametrosRedireccion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */