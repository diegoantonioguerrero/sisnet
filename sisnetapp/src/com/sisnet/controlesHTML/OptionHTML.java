package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
public class OptionHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aValor;
  private String aDescripcion;
  private boolean aEsSeleccionado;
  public OptionHTML() {
    setValor("");
    setDescripcion("");
    setEsSeleccionado(false);
  }
  public String getValor() {
    return this.aValor;
  }
  public void setValor(String pValor) {
    this.aValor = pValor;
  }
  public String getDescripcion() {
    return this.aDescripcion;
  }
  public void setDescripcion(String pDescripcion) {
    this.aDescripcion = pDescripcion;
  }
  public boolean esSeleccionado() {
    return this.aEsSeleccionado;
  }
  public void setEsSeleccionado(boolean pEsSeleccionado) {
    this.aEsSeleccionado = pEsSeleccionado;
  }
  public String dibujar() {
    String option_local = "";
    
    try {
      option_local = " <option ";
      option_local = mc.concatenarCadena(option_local, mcHTML.conformarAtributoHTML(" value=\"", getValor()));
      if (esSeleccionado()) {
        option_local = mc.concatenarCadena(option_local, " selected ");
      }
      option_local = mc.concatenarCadena(option_local, "> ");
      option_local = mc.concatenarCadena(option_local, getDescripcion());
      option_local = mc.concatenarCadena(option_local, " </option>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      option_local = "";
    } 
    
    return option_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\OptionHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */