package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.controlesHTML.ElementoHTML;
public class CajaArchivoHTML
  extends ElementoHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  public String dibujar() {
    String cajaArchivo_local = "";
    
    try {
      cajaArchivo_local = " <input ";
      cajaArchivo_local = mc.concatenarCadena(cajaArchivo_local, mcHTML.conformarAtributoHTML(" type=\"", "file"));
      
      cajaArchivo_local = mc.concatenarCadena(cajaArchivo_local, mcHTML.conformarAtributoHTML(" class=\"", getEstilo()));
      
      cajaArchivo_local = mc.concatenarCadena(cajaArchivo_local, mcHTML.conformarAtributoHTML(" id=\"", getId()));
      
      cajaArchivo_local = mc.concatenarCadena(cajaArchivo_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      
      cajaArchivo_local = mc.concatenarCadena(cajaArchivo_local, ">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
      cajaArchivo_local = "";
    } 
    
    return cajaArchivo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\CajaArchivoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */