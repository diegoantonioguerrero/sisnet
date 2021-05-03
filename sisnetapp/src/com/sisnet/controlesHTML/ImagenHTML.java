package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class ImagenHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aSrc;
  private String aEventoOnClick;
  public ImagenHTML() {
    setSrc("");
  }
  public String getSrc() {
    return this.aSrc;
  }
  public void setSrc(String pSrc) {
    this.aSrc = pSrc;
  }
  public String getEventoOnClick() {
    return this.aEventoOnClick;
  }
  public void setEventoOnClick(String pEventoOnClick) {
    this.aEventoOnClick = pEventoOnClick;
  }
  public String dibujar() {
    String imagen_local = "";
    
    try {
      setSrc(mc.concatenarCadena("../imagenes/botones/", getNombre() + ".gif"));
      
      setId(mc.concatenarCadena("submit", getId()));
      setNombre(getId());
      
      imagen_local = mc.concatenarCadena(" <input ", mcHTML.conformarAtributoHTML(" type=\"", "image"));
      
      imagen_local = mc.concatenarCadena(imagen_local, mcHTML.conformarAtributoHTML(" id=\"", getId()));
      imagen_local = mc.concatenarCadena(imagen_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      imagen_local = mc.concatenarCadena(imagen_local, mcHTML.conformarAtributoHTML(" src=\"", getSrc()));
      imagen_local = mc.concatenarCadena(imagen_local, mcHTML.conformarAtributoHTML(" onClick=\"", getEventoOnClick()));
      
      imagen_local = mc.concatenarCadena(imagen_local, ">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      imagen_local = "";
    } 
    
    return imagen_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\ImagenHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */