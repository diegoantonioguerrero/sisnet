package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class ElementoOcultoHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aValor;
  public ElementoOcultoHTML() {
    setValor("");
  }
  public String getValor() {
    return this.aValor;
  }
  public void setValor(String pValor) {
    this.aValor = pValor;
  }
  public String dibujar() {
    String elementoOculto_local = "";
    
    try {
      elementoOculto_local = " <input ";
      elementoOculto_local = mc.concatenarCadena(elementoOculto_local, mcHTML.conformarAtributoHTML(" type=\"", "hidden"));
      
      elementoOculto_local = mc.concatenarCadena(elementoOculto_local, mcHTML.conformarAtributoHTML(" id=\"", getId()));
      
      elementoOculto_local = mc.concatenarCadena(elementoOculto_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      
      elementoOculto_local = mc.concatenarCadena(elementoOculto_local, mcHTML.conformarAtributoHTML(" value=\"", getValor()));
      
      elementoOculto_local = mc.concatenarCadena(elementoOculto_local, ">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      elementoOculto_local = "";
    } 
    
    return elementoOculto_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\ElementoOcultoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */