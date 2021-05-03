package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
import com.sisnet.controlesHTML.HipervinculoHTML;
import com.sisnet.controlesHTML.ImagenHTML;
public class BotonHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aDestino;
  private String aEventoOnClick;
  private boolean aEsEntradaFormulario;
  public BotonHTML() {
    setDestino("");
    setEventoOnClick("");
    setEsEntradaFormulario(false);
  }
  public String getDestino() {
    return this.aDestino;
  }
  public void setDestino(String pDestino) {
    this.aDestino = pDestino;
  }
  public String getEventoOnClick() {
    return this.aEventoOnClick;
  }
  public void setEventoOnClick(String pEventoOnClick) {
    this.aEventoOnClick = pEventoOnClick;
  }
  public boolean esEntradaFormulario() {
    return this.aEsEntradaFormulario;
  }
  public void setEsEntradaFormulario(boolean pEsEntradaFormulario) {
    this.aEsEntradaFormulario = pEsEntradaFormulario;
  }
  public String dibujar() {
    String boton_local = "";
    String contenido_local = null;
    ImagenHTML imagenHTML_local = null;
    HipervinculoHTML hipervinculoHTML_local = null;
    
    try {
      if (esEntradaFormulario()) {
        imagenHTML_local = new ImagenHTML();
        imagenHTML_local.setNombre(getNombre());
        imagenHTML_local.setEventoOnClick(getEventoOnClick());
        boton_local = imagenHTML_local.dibujar();
      } else {
        hipervinculoHTML_local = new HipervinculoHTML();
        hipervinculoHTML_local.setDestino(getDestino());
        hipervinculoHTML_local.setEstilo(getEstilo());
        contenido_local = " <img ";
        contenido_local = mc.concatenarCadena(contenido_local, mcHTML.conformarAtributoHTML(" src=\"", mc.concatenarCadena("../imagenes/botones/", getNombre() + ".gif")));
        
        contenido_local = mc.concatenarCadena(contenido_local, "> ");
        hipervinculoHTML_local.setContenido(contenido_local);
        hipervinculoHTML_local.setEventoOnClick(getEventoOnClick());
        boton_local = hipervinculoHTML_local.dibujar();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      boton_local = "";
    } 
    
    return boton_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\BotonHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */