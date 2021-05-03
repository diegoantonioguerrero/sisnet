package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class HipervinculoHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aDestino;
  private String aContenido;
  private String aEventoOnClick;
  public HipervinculoHTML() {
    setDestino("");
    setContenido("");
    setEventoOnClick("");
  }
  public String getDestino() {
    return this.aDestino;
  }
  public void setDestino(String pDestino) {
    this.aDestino = pDestino;
  }
  public String getContenido() {
    return this.aContenido;
  }
  public void setContenido(String pContenido) {
    this.aContenido = pContenido;
  }
  public String getEventoOnClick() {
    return this.aEventoOnClick;
  }
  public void setEventoOnClick(String pEventoOnClick) {
    this.aEventoOnClick = pEventoOnClick;
  }
  public String dibujar() {
    String hipervinculo_local = "";
    
    try {
      hipervinculo_local = " <a ";
      hipervinculo_local = mc.concatenarCadena(hipervinculo_local, mcHTML.conformarAtributoHTML(" class=\"", getEstilo()));
      
      hipervinculo_local = mc.concatenarCadena(hipervinculo_local, mcHTML.conformarAtributoHTML(" href=\"", getDestino()));
      
      hipervinculo_local = mc.concatenarCadena(hipervinculo_local, mcHTML.conformarAtributoHTML(" onClick=\"", getEventoOnClick()));
      
      hipervinculo_local = mc.concatenarCadena(hipervinculo_local, "> ");
      hipervinculo_local = mc.concatenarCadena(hipervinculo_local, getContenido());
      hipervinculo_local = mc.concatenarCadena(hipervinculo_local, " </a>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      hipervinculo_local = "";
    } 
    
    return hipervinculo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\HipervinculoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */