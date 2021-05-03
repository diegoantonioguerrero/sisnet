package com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina;
import java.io.Serializable;
public class NavegacionPaginaUbicacionPagina
  implements Serializable
{
  private boolean aEsConfiguracion;
  private boolean aRecargarPagina;
  private boolean aEsDocumento;
  private int aPlantillaUtilizar;
  private boolean aEjecutarConsulta;
  public NavegacionPaginaUbicacionPagina(boolean pEsConfiguracion, boolean pRecargarPagina, boolean pEsDocumento, int pPlantillaUtilizar, boolean pEjecutarConsulta) {
    setEsConfiguracion(pEsConfiguracion);
    setRecargarPagina(pRecargarPagina);
    setEsDocumento(pEsDocumento);
    setPlantillaUtilizar(pPlantillaUtilizar);
    setEjecutarConsulta(pEjecutarConsulta);
  }
  public boolean esConfiguracion() {
    return this.aEsConfiguracion;
  }
  public void setEsConfiguracion(boolean pConfiguracion) {
    this.aEsConfiguracion = pConfiguracion;
  }
  public boolean esRecargarPagina() {
    return this.aRecargarPagina;
  }
  public void setRecargarPagina(boolean pRecargarPagina) {
    this.aRecargarPagina = pRecargarPagina;
  }
  public boolean esDocumento() {
    return this.aEsDocumento;
  }
  public void setEsDocumento(boolean pEsDocumento) {
    this.aEsDocumento = pEsDocumento;
  }
  public int getPlantillaUtilizar() {
    return this.aPlantillaUtilizar;
  }
  public void setPlantillaUtilizar(int pPlantillaUtilizar) {
    this.aPlantillaUtilizar = pPlantillaUtilizar;
  }
  public boolean esEjecutarConsulta() {
    return this.aEjecutarConsulta;
  }
  public void setEjecutarConsulta(boolean pEjecutarConsulta) {
    this.aEjecutarConsulta = pEjecutarConsulta;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\objetosNavegacionPagina\NavegacionPaginaUbicacionPagina.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */