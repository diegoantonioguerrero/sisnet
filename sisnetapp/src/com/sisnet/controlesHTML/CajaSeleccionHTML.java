package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.ElementoHTML;
import com.sisnet.controlesHTML.listas.ListaOptionHTML;
public class CajaSeleccionHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private ListaOptionHTML aListaOptionHTML;
  public CajaSeleccionHTML() {
    setListaOptionHTML(null);
  }
  public ListaOptionHTML getListaOptionHTML() {
    return this.aListaOptionHTML;
  }
  public void setListaOptionHTML(ListaOptionHTML pListaOptionHTML) {
    this.aListaOptionHTML = pListaOptionHTML;
  }
  public String dibujar() {
    String cajaSeleccion_local = "";
    
    try {
      cajaSeleccion_local = " <select ";
      cajaSeleccion_local = mc.concatenarCadena(cajaSeleccion_local, mcHTML.conformarAtributoHTML(" class=\"", getEstilo()));
      
      cajaSeleccion_local = mc.concatenarCadena(cajaSeleccion_local, mcHTML.conformarAtributoHTML(" id=\"", getId()));
      
      cajaSeleccion_local = mc.concatenarCadena(cajaSeleccion_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      
      cajaSeleccion_local = mc.concatenarCadena(cajaSeleccion_local, ">\n");
      if (getListaOptionHTML() != ConstantesGeneral.VALOR_NULO) {
        cajaSeleccion_local = mc.concatenarCadena(cajaSeleccion_local, getListaOptionHTML().dibujar());
      }
      cajaSeleccion_local = mc.concatenarCadena(cajaSeleccion_local, " </select>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cajaSeleccion_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\CajaSeleccionHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */