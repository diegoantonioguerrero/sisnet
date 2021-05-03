package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
public class DependienteHabilitacion
{
  private int aIdDependienteHabilitacion;
  private Campo aCampo;
  private int aIdValorMaestro;
  private boolean aHabilitacion;
  public DependienteHabilitacion() {
    setIdDependienteHabilitacion(-1);
    setCampo(null);
    setIdValorMaestro(-1);
    setHabilitacion(false);
  }
  public int getIdDependienteHabilitacion() {
    return this.aIdDependienteHabilitacion;
  }
  public void setIdDependienteHabilitacion(int pIdDependienteHabilitacion) {
    this.aIdDependienteHabilitacion = pIdDependienteHabilitacion;
  }
  public Campo getCampo() {
    return this.aCampo;
  }
  public void setCampo(Campo pCampo) {
    this.aCampo = pCampo;
  }
  public int getIdValorMaestro() {
    return this.aIdValorMaestro;
  }
  public void setIdValorMaestro(int pIdValorMaestro) {
    this.aIdValorMaestro = pIdValorMaestro;
  }
  public boolean esHabilitacion() {
    return this.aHabilitacion;
  }
  public void setHabilitacion(boolean pHabilitacion) {
    this.aHabilitacion = pHabilitacion;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\DependienteHabilitacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */