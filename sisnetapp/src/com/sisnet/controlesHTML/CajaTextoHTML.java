package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class CajaTextoHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private int aMaximaLongitud;
  private String aValor;
  private boolean aEsSoloLectura;
  private boolean aEsHabilitado;
  private String aEventoOnKeyPress;
  private String aEventoOnBlur;
  private boolean aEsPassword;
  public CajaTextoHTML() {
    setMaximaLongitud(0);
    setValor("");
    setEsSoloLectura(false);
    setEsHabilitado(true);
    setEventoOnKeyPress("");
    setEventoOnBlur("");
  }
  public int getMaximaLongitud() {
    return this.aMaximaLongitud;
  }
  public void setMaximaLongitud(int pMaximaLongitud) {
    this.aMaximaLongitud = pMaximaLongitud;
  }
  public String getValor() {
    return this.aValor;
  }
  public void setValor(String pValor) {
    this.aValor = pValor;
  }
  public boolean esSoloLectura() {
    return this.aEsSoloLectura;
  }
  public void setEsSoloLectura(boolean pEsSoloLectura) {
    this.aEsSoloLectura = pEsSoloLectura;
  }
  public boolean esHabilitado() {
    return this.aEsHabilitado;
  }
  public void setEsHabilitado(boolean pEsHabilitado) {
    this.aEsHabilitado = pEsHabilitado;
  }
  public String getEventoOnKeyPress() {
    return this.aEventoOnKeyPress;
  }
  public void setEventoOnKeyPress(String pEventoOnKeyPress) {
    this.aEventoOnKeyPress = pEventoOnKeyPress;
  }
  public String getEventoOnBlur() {
    return this.aEventoOnBlur;
  }
  public void setEventoOnBlur(String pEventoOnBlur) {
    this.aEventoOnBlur = pEventoOnBlur;
  }
  public boolean esPassword() {
    return this.aEsPassword;
  }
  public void setEsPassword(boolean pEsPassword) {
    this.aEsPassword = pEsPassword;
  }
  public String dibujar() {
    String cajaTexto_local = "";
    String tipoCajaTexto_local = null;
    
    try {
      cajaTexto_local = " <input ";
      tipoCajaTexto_local = "text";
      if (esPassword()) {
        tipoCajaTexto_local = "password";
      }
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" type=\"", tipoCajaTexto_local));
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" class=\"", getEstilo()));
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" id=\"", getId()));
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" maxlength=\"", String.valueOf(getMaximaLongitud())));
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" value=\"", getValor()));
      
      if (esSoloLectura()) {
        cajaTexto_local = mc.concatenarCadena(cajaTexto_local, " readonly ");
      }
      if (!esHabilitado()) {
        cajaTexto_local = mc.concatenarCadena(cajaTexto_local, " disabled ");
      }
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" onKeyPress=\"", getEventoOnKeyPress()));
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, mcHTML.conformarAtributoHTML(" onBlur=\"", getEventoOnBlur()));
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, ">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      cajaTexto_local = "";
    } 
    
    return cajaTexto_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\CajaTextoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */