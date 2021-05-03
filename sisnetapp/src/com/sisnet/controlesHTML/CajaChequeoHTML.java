package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class CajaChequeoHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private boolean aEsChequeado;
  private String aValor;
  public CajaChequeoHTML() {
    setEsChequeado(false);
    setValor("");
  }
  public boolean esChequeado() {
    return this.aEsChequeado;
  }
  public void setEsChequeado(boolean pEsChequeado) {
    this.aEsChequeado = pEsChequeado;
  }
  public String getValor() {
    return this.aValor;
  }
  public void setValor(String pValor) {
    this.aValor = pValor;
  }
  public String dibujar() {
    String cajaChequeo_local = "";
    
    try {
      setEsChequeado(mc.sonCadenasIgualesIgnorarMayusculas(getValor(), "true"));
      cajaChequeo_local = " <input ";
      cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, mcHTML.conformarAtributoHTML(" type=\"", "checkbox"));
      
      cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, mcHTML.conformarAtributoHTML(" class=\"", getEstilo()));
      
      cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, mcHTML.conformarAtributoHTML(" id=\"", getId()));
      
      cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      
      if (esChequeado()) {
        cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, " checked ");
      }
      cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, mcHTML.conformarAtributoHTML(" value=\"", getValor()));
      
      cajaChequeo_local = mc.concatenarCadena(cajaChequeo_local, ">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      cajaChequeo_local = "";
    } 
    
    return cajaChequeo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\CajaChequeoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */