package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
public class ValorDependiente
{
  private int aIdValorDependiente;
  private Campo aCampo;
  private int aIdValorMaestro;
  private int aIdValorDetalle;
  public ValorDependiente() {
    setIdValorDependiente(-1);
    setCampo(null);
    setIdValorMaestro(-1);
    setIdValorDetalle(-1);
  }
  public int getIdValorDependiente() {
    return this.aIdValorDependiente;
  }
  public void setIdValorDependiente(int pIdValorDependiente) {
    this.aIdValorDependiente = pIdValorDependiente;
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
  public int getIdValorDetalle() {
    return this.aIdValorDetalle;
  }
  public void setIdValorDetalle(int pIdValorDetalle) {
    this.aIdValorDetalle = pIdValorDetalle;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\ValorDependiente.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */