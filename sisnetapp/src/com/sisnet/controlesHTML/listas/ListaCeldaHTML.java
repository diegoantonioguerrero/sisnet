package com.sisnet.controlesHTML.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.CeldaHTML;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Iterator;
public class ListaCeldaHTML
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHtml = ManejadorCadenasHTML.getManejadorCadenasHTML();
  public void adicionar(CeldaHTML pCeldaHTML) {
    if (pCeldaHTML == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      adicionar(pCeldaHTML);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String dibujar() {
    String listaCelda_local = "";
    int numeroFila_local = -1;
    Iterator iterador_local = null;
    CeldaHTML celdaHTML_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        celdaHTML_local = (CeldaHTML)iterador_local.next();
        if (celdaHTML_local != ConstantesGeneral.VALOR_NULO) {
          if (numeroFila_local != celdaHTML_local.getNumeroFila()) {
            numeroFila_local = celdaHTML_local.getNumeroFila();
            if (!mc.esCadenaVacia(listaCelda_local)) {
              listaCelda_local = mc.concatenarCadena(listaCelda_local, " </tr>\n ");
            }
            listaCelda_local = mc.concatenarCadena(listaCelda_local, " <tr ");
          } 
          listaCelda_local = mc.concatenarCadena(listaCelda_local, celdaHTML_local.dibujar());
        } 
        if (!iterador_local.hasNext()) {
          listaCelda_local = mc.concatenarCadena(listaCelda_local, " </tr>\n ");
        }
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      iterador_local = null;
      celdaHTML_local = null;
    } 
    
    return listaCelda_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\listas\ListaCeldaHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */