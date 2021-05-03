package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.ElementoHTML;
import com.sisnet.controlesHTML.listas.ListaCeldaHTML;
public class TablaHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHtml = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aAncho;
  private int aAlineacion;
  private ListaCeldaHTML aListaCeldaHTML;
  public TablaHTML() {
    setAncho("");
    setAlineacion(0);
    setListaCeldaHTML(new ListaCeldaHTML());
  }
  public String getAncho() {
    return this.aAncho;
  }
  public void setAncho(String pAncho) {
    this.aAncho = pAncho;
  }
  public int getAlineacion() {
    return this.aAlineacion;
  }
  public void setAlineacion(int pAlineacion) {
    this.aAlineacion = pAlineacion;
  }
  public ListaCeldaHTML getListaCeldaHTML() {
    return this.aListaCeldaHTML;
  }
  public void setListaCeldaHTML(ListaCeldaHTML pListaCeldaHTML) {
    this.aListaCeldaHTML = pListaCeldaHTML;
  }
  public String dibujar() {
    String tabla_local = "";
    
    try {
      tabla_local = " <table ";
      tabla_local = mc.concatenarCadena(tabla_local, mcHtml.conformarAtributoHTML(" class=\"", getEstilo()));
      
      tabla_local = mc.concatenarCadena(tabla_local, mcHtml.conformarAtributoHTML(" id=\"", getId()));
      
      tabla_local = mc.concatenarCadena(tabla_local, mcHtml.conformarAtributoHTML(" name=\"", getNombre()));
      
      tabla_local = mc.concatenarCadena(tabla_local, mcHtml.conformarAtributoHTML(" align=\"", String.valueOf(getAlineacion())));
      
      tabla_local = mc.concatenarCadena(tabla_local, mcHtml.conformarAtributoHTML(" width=\"", getAncho()));
      
      tabla_local = mc.concatenarCadena(tabla_local, "> ");
      if (getListaCeldaHTML() != ConstantesGeneral.VALOR_NULO) {
        tabla_local = mc.concatenarCadena(tabla_local, getListaCeldaHTML().dibujar());
      }
      tabla_local = mc.concatenarCadena(tabla_local, " </table>\n ");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tabla_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\TablaHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */