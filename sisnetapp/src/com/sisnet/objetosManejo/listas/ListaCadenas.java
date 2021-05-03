package com.sisnet.objetosManejo.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Iterator;
public class ListaCadenas
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(String pCadena) {
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      super.adicionar(pCadena);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String obtenerCadena(int pPosicion) {
    String cadena_local = "";
    try {
      cadena_local = get(pPosicion).toString();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadena_local;
  }
  public boolean verificarExistenciaCadena(String pCadena) {
    boolean existenciaCadena_local = false;
    Iterator iterator_local = null;
    String cadena_local = null;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return existenciaCadena_local;
    }
    
    try {
      iterator_local = iterator();
      
      while (iterator_local.hasNext()) {
        cadena_local = (String)iterator_local.next();
        existenciaCadena_local = mc.sonCadenasIguales(cadena_local, pCadena);
        
        if (existenciaCadena_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      cadena_local = null;
    } 
    
    return existenciaCadena_local;
  }
  public String asignarCadena(int pPosicion, String pCadena) {
    String cadena_local = "";
    
    try {
      if (get(pPosicion) != ConstantesGeneral.VALOR_NULO) {
        set(pPosicion, pCadena);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadena_local;
  }
  public String concantenarValoresConComas() {
    String cadena_local = "";
    Iterator iterator_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        cadena_local = mc.concatenarCadena(cadena_local, (String)iterator_local.next());
        if (iterator_local.hasNext()) {
          cadena_local = mc.concatenarCadena(cadena_local, String.valueOf(','));
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
    
    return cadena_local;
  }
  public String concantenarValores() {
    String cadena_local = "";
    Iterator iterator_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        cadena_local = mc.concatenarCadena(cadena_local, (String)iterator_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
    
    return cadena_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaCadenas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */