package com.sisnet.objetosManejo.listas.objetosRelacionAplicativos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Accion;
import java.util.Iterator;
public class ListaAcciones
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(Accion pAccion) {
    try {
      super.adicionar(pAccion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public boolean verificaExistenciaAccionPorEtiqueta(String pEtiqueta) {
    boolean existenciaAccionPorEtiqueta_local = false;
    Iterator iterator_local = null;
    Accion accion_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        accion_local = (Accion)iterator_local.next();
        existenciaAccionPorEtiqueta_local = mc.sonCadenasIguales(accion_local.getEtiqueta(), pEtiqueta);
        if (existenciaAccionPorEtiqueta_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      accion_local = null;
    } 
    
    return existenciaAccionPorEtiqueta_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosRelacionAplicativos\ListaAcciones.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */