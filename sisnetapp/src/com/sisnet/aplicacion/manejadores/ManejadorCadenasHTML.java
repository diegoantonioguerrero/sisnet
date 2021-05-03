package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
public class ManejadorCadenasHTML
{
  private static com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML manejadorCadenasHTMLSingleton = null;
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public static com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML getManejadorCadenasHTML() {
    if (manejadorCadenasHTMLSingleton == ConstantesGeneral.VALOR_NULO) {
      manejadorCadenasHTMLSingleton = new com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML();
    }
    return manejadorCadenasHTMLSingleton;
  }
  public String conformarAtributoHTML(String pNombreAtributo, String pValorAtributo) {
    String atributoHtml_local = "";
    
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return atributoHtml_local;
    }
    if (pValorAtributo == ConstantesGeneral.VALOR_NULO) {
      return atributoHtml_local;
    }
    
    try {
      if (!mc.esCadenaVacia(pValorAtributo)) {
        atributoHtml_local = mc.concatenarCadena(pNombreAtributo, pValorAtributo);
        atributoHtml_local = mc.concatenarCadena(atributoHtml_local, "\"");
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return atributoHtml_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorCadenasHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */