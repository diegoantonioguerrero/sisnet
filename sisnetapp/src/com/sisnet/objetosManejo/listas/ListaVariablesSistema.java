package com.sisnet.objetosManejo.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.VariableSistema;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Iterator;
public class ListaVariablesSistema
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(VariableSistema pVariableSistema) {
    if (pVariableSistema == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(pVariableSistema);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public VariableSistema obtenerVariableSistema(String pNombreVariable) {
    VariableSistema variableSistema_local = null;
    Iterator iterador_local = null;
    
    if (pNombreVariable == ConstantesGeneral.VALOR_NULO) {
      return variableSistema_local;
    }
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        variableSistema_local = (VariableSistema)iterador_local.next();
        if (mc.sonCadenasIguales(variableSistema_local.getNombreVariable(), pNombreVariable)) {
          break;
        }
        variableSistema_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return variableSistema_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaVariablesSistema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */