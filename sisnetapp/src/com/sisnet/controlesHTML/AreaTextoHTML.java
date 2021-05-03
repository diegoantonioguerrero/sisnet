package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class AreaTextoHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHtml = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private int aMaximaLongitud;
  private int aNumeroFilas;
  private String aValor;
  private boolean aEsSoloLectura;
  private boolean aEsHabilitado;
  private String aEventoOnBlur;
  public AreaTextoHTML() {
    setMaximaLongitud(10485760);
    setNumeroFilas(5);
    setValor("");
    setEsSoloLectura(false);
    setEsHabilitado(false);
    setEventoOnBlur("");
  }
  public int getMaximaLongitud() {
    return this.aMaximaLongitud;
  }
  public void setMaximaLongitud(int pMaximaLongitud) {
    this.aMaximaLongitud = pMaximaLongitud;
  }
  public int getNumeroFilas() {
    return this.aNumeroFilas;
  }
  public void setNumeroFilas(int pNumeroFilas) {
    this.aNumeroFilas = pNumeroFilas;
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
  public String getEventoOnBlur() {
    return this.aEventoOnBlur;
  }
  public void setEventoOnBlur(String pEventoOnBlur) {
    this.aEventoOnBlur = pEventoOnBlur;
  }
  public String dibujar() {
    String areaTexto_local = "";
    
    try {
      areaTexto_local = " <textarea ";
      areaTexto_local = mc.concatenarCadena(areaTexto_local, mcHtml.conformarAtributoHTML(" class=\"", getEstilo()));
      
      areaTexto_local = mc.concatenarCadena(areaTexto_local, mcHtml.conformarAtributoHTML(" id=\"", getId()));
      areaTexto_local = mc.concatenarCadena(areaTexto_local, mcHtml.conformarAtributoHTML(" name=\"", getNombre()));
      
      areaTexto_local = mc.concatenarCadena(areaTexto_local, mcHtml.conformarAtributoHTML(" px rows=\"", String.valueOf(getNumeroFilas())));
      
      areaTexto_local = mc.concatenarCadena(areaTexto_local, mcHtml.conformarAtributoHTML(" maxlength=\"", String.valueOf(getMaximaLongitud())));
      
      areaTexto_local = mc.concatenarCadena(areaTexto_local, mcHtml.conformarAtributoHTML(" onBlur=\"", getEventoOnBlur()));
      
      areaTexto_local = mc.concatenarCadena(areaTexto_local, getValor());
      areaTexto_local = mc.concatenarCadena(areaTexto_local, " </textarea>\n ");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return areaTexto_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\AreaTextoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */