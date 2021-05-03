package com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina;
import java.io.Serializable;
public class NavegacionPaginaEstado
  implements Serializable
{
  private String aEstadoActual;
  private int aAccion;
  private int aNumeroPagina;
  private int aNumeroError;
  private int aTipoError;
  public NavegacionPaginaEstado(String pEstadoActual, int pAccion, int pNumeroPagina, int pNumeroError, int pTipoError) {
    setEstadoActual(pEstadoActual);
    setAccion(pAccion);
    setNumeroPagina(pNumeroPagina);
    setNumeroError(pNumeroError);
    setTipoError(pTipoError);
  }
  public String getEstadoActual() {
    return this.aEstadoActual;
  }
  public void setEstadoActual(String pEstadoActual) {
    this.aEstadoActual = pEstadoActual;
  }
  public int getAccion() {
    return this.aAccion;
  }
  public void setAccion(int pAccion) {
    this.aAccion = pAccion;
  }
  public int getNumeroPagina() {
    return this.aNumeroPagina;
  }
  public void setNumeroPagina(int pNumeroPagina) {
    this.aNumeroPagina = pNumeroPagina;
  }
  public int getNumeroError() {
    return this.aNumeroError;
  }
  public void setNumeroError(int pNumeroError) {
    this.aNumeroError = pNumeroError;
  }
  public int getTipoError() {
    return this.aTipoError;
  }
  public void setTipoError(int pTipoError) {
    this.aTipoError = pTipoError;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\objetosNavegacionPagina\NavegacionPaginaEstado.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */