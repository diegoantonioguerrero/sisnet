package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import java.io.Serializable;
public class EnlaceCampo
  implements Serializable
{
  private int aIdEnlazado;
  private Aplicacion aEnlazado;
  private int aFiltradoRegistrosEnlazados;
  private int aIdCampoOrigenFiltrado;
  private Campo aCampoOrigenFiltrado;
  private int aIdCampoDestinoFiltrado;
  private Campo aCampoDestinoFiltrado;
  private String aValorFiltrado;
  private boolean aOpcionDesconocido;
  private int aDependienteEnlazado;
  private int aIdCampoEnlaceDepende;
  private Campo aCampoEnlaceDepende;
  private int aIdCampoOrigenEnlace;
  private Campo aCampoOrigenEnlace;
  public EnlaceCampo() {
    setIdEnlazado(-1);
    setEnlazado(null);
    setFiltradoRegistrosEnlazados(0);
    setIdCampoOrigenFiltrado(-1);
    setCampoOrigenFiltrado(null);
    setIdCampoDestinoFiltrado(-1);
    setCampoDestinoFiltrado(null);
    setValorFiltrado("");
    setOpcionDesconocido(false);
    setDependienteEnlazado(0);
    setIdCampoEnlaceDepende(-1);
    setCampoEnlaceDepende(null);
    setIdCampoOrigenEnlace(-1);
    setCampoOrigenEnlace(null);
  }
  public int getIdEnlazado() {
    return this.aIdEnlazado;
  }
  public void setIdEnlazado(int pIdEnlazado) {
    this.aIdEnlazado = pIdEnlazado;
  }
  public Aplicacion getEnlazado() {
    return this.aEnlazado;
  }
  public void setEnlazado(Aplicacion pEnlazado) {
    this.aEnlazado = pEnlazado;
  }
  public int getFiltradoRegistrosEnlazados() {
    return this.aFiltradoRegistrosEnlazados;
  }
  public void setFiltradoRegistrosEnlazados(int pFiltradoRegistrosEnlazados) {
    this.aFiltradoRegistrosEnlazados = pFiltradoRegistrosEnlazados;
  }
  public int getIdCampoOrigenFiltrado() {
    return this.aIdCampoOrigenFiltrado;
  }
  public void setIdCampoOrigenFiltrado(int pIdCampoOrigenFiltrado) {
    this.aIdCampoOrigenFiltrado = pIdCampoOrigenFiltrado;
  }
  public Campo getCampoOrigenFiltrado() {
    return this.aCampoOrigenFiltrado;
  }
  public void setCampoOrigenFiltrado(Campo pCampoOrigenFiltrado) {
    this.aCampoOrigenFiltrado = pCampoOrigenFiltrado;
  }
  public int getIdCampoDestinoFiltrado() {
    return this.aIdCampoDestinoFiltrado;
  }
  public void setIdCampoDestinoFiltrado(int pIdCampoDestinoFiltrado) {
    this.aIdCampoDestinoFiltrado = pIdCampoDestinoFiltrado;
  }
  public Campo getCampoDestinoFiltrado() {
    return this.aCampoDestinoFiltrado;
  }
  public void setCampoDestinoFiltrado(Campo pCampoDestinoFiltrado) {
    this.aCampoDestinoFiltrado = pCampoDestinoFiltrado;
  }
  public String getValorFiltrado() {
    return this.aValorFiltrado;
  }
  public void setValorFiltrado(String pValorFiltrado) {
    this.aValorFiltrado = pValorFiltrado;
  }
  public boolean esOpcionDesconocido() {
    return this.aOpcionDesconocido;
  }
  public void setOpcionDesconocido(boolean pOpcionDesconocido) {
    this.aOpcionDesconocido = pOpcionDesconocido;
  }
  public int getDependienteEnlazado() {
    return this.aDependienteEnlazado;
  }
  public void setDependienteEnlazado(int pDependienteEnlazado) {
    this.aDependienteEnlazado = pDependienteEnlazado;
  }
  public int getIdCampoEnlaceDepende() {
    return this.aIdCampoEnlaceDepende;
  }
  public void setIdCampoEnlaceDepende(int pIdCampoEnlaceDepende) {
    this.aIdCampoEnlaceDepende = pIdCampoEnlaceDepende;
  }
  public Campo getCampoEnlaceDepende() {
    return this.aCampoEnlaceDepende;
  }
  public void setCampoEnlaceDepende(Campo pCampoEnlaceDepende) {
    this.aCampoEnlaceDepende = pCampoEnlaceDepende;
  }
  public int getIdCampoOrigenEnlace() {
    return this.aIdCampoOrigenEnlace;
  }
  public void setIdCampoOrigenEnlace(int pIdCampoOrigenEnlace) {
    this.aIdCampoOrigenEnlace = pIdCampoOrigenEnlace;
  }
  public Campo getCampoOrigenEnlace() {
    return this.aCampoOrigenEnlace;
  }
  public void setCampoOrigenEnlace(Campo pCampoOrigenEnlace) {
    this.aCampoOrigenEnlace = pCampoOrigenEnlace;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\EnlaceCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */