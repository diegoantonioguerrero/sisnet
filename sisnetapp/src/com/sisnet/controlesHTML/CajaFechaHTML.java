package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.BotonHTML;
import com.sisnet.controlesHTML.CajaTextoHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class CajaFechaHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private CajaTextoHTML aCajaTextoHTML;
  private BotonHTML aBotonHTML;
  public CajaFechaHTML() {
    setCajaTextoHTML(null);
    setBotonHTML(null);
  }
  public CajaTextoHTML getCajaTextoHTML() {
    return this.aCajaTextoHTML;
  }
  public void setCajaTextoHTML(CajaTextoHTML aCajaTextoHTML) {
    this.aCajaTextoHTML = aCajaTextoHTML;
  }
  public BotonHTML getBotonHTML() {
    return this.aBotonHTML;
  }
  public void setBotonHTML(BotonHTML aBotonHTML) {
    this.aBotonHTML = aBotonHTML;
  }
  public String dibujar() {
    String cajaFecha_local = "";
    
    try {
      if (getCajaTextoHTML() != ConstantesGeneral.VALOR_NULO && getBotonHTML() != ConstantesGeneral.VALOR_NULO) {
        cajaFecha_local = mc.concatenarCadena(getCajaTextoHTML().dibujar(), getBotonHTML().dibujar());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cajaFecha_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\CajaFechaHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */