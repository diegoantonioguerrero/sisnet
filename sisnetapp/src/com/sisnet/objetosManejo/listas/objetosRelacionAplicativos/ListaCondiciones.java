package com.sisnet.objetosManejo.listas.objetosRelacionAplicativos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.ExpresionCondicion;
import java.util.Iterator;
public class ListaCondiciones
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(String pExpresion, String pOperador, boolean pResultado) {
    try {
      super.adicionar(new ExpresionCondicion(pExpresion, pOperador, pResultado));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public boolean verificarExistenciaExpresionCondicion(String pExpresion) {
    boolean existeCondicion_local = false;
    Iterator iterator_local = null;
    ExpresionCondicion expresionCondicion_local = null;
    
    if (pExpresion == ConstantesGeneral.VALOR_NULO) {
      return existeCondicion_local;
    }
    
    try {
      iterator_local = iterator();
      
      while (iterator_local.hasNext()) {
        expresionCondicion_local = (ExpresionCondicion)iterator_local.next();
        existeCondicion_local = mc.sonCadenasIguales(expresionCondicion_local.getExpresion(), pExpresion);
        if (existeCondicion_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      expresionCondicion_local = null;
    } 
    
    return existeCondicion_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosRelacionAplicativos\ListaCondiciones.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */