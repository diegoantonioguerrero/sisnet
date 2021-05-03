package com.sisnet.objetosManejo.manejoPaginaJsp;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
public class ParametroRedireccion
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aNombreParametro;
  private String aValorParametro;
  public ParametroRedireccion(String pNombreParametro, String pValorParametro) {
    setNombreParametro(pNombreParametro);
    setValorParametro(pValorParametro);
  }
  public String getNombreParametro() {
    return this.aNombreParametro;
  }
  public void setNombreParametro(String pNombreParametro) {
    this.aNombreParametro = pNombreParametro;
  }
  public String getValorParametro() {
    return this.aValorParametro;
  }
  public void setValorParametro(String pValorParametro) {
    this.aValorParametro = pValorParametro;
  }
  public String conformarParametro() {
    String parametro_local = "";
    
    try {
      if (!mc.esCadenaVacia(getValorParametro())) {
        parametro_local = mc.concatenarCadena(getNombreParametro(), String.valueOf('='));
        parametro_local = mc.concatenarCadena(parametro_local, getValorParametro());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return parametro_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\ParametroRedireccion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */