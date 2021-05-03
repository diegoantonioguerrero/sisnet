package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
public class OrdenReporte
{
  private int aTipoOrdenamiento;
  private Campo aCampo;
  public OrdenReporte(int pTipoOrdenamiento, Campo pCampo) {
    setTipoOrdenamiento(pTipoOrdenamiento);
    setCampo(pCampo);
  }
  public int getTipoOrdenamiento() {
    return this.aTipoOrdenamiento;
  }
  public void setTipoOrdenamiento(int pTipoOrdenamiento) {
    this.aTipoOrdenamiento = pTipoOrdenamiento;
  }
  public Campo getCampo() {
    return this.aCampo;
  }
  public void setCampo(Campo pCampo) {
    this.aCampo = pCampo;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\OrdenReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */