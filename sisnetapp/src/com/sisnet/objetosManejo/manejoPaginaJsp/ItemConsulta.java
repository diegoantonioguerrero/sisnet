package com.sisnet.objetosManejo.manejoPaginaJsp;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import java.io.Serializable;
public class ItemConsulta
  implements Serializable
{
  private Campo aCampoDesde;
  private Campo aCampoHasta;
  private int aNivelConsulta;
  private boolean aOrdenadoPor;
  private boolean aOrdenAscendente;
  private boolean aPermitirBorrar;
  private boolean aOmitirOpcionConsulta;
  public ItemConsulta(Campo pCampoDesde, Campo pCampoHasta, int pNivelConsulta, boolean pOrdenadoPor, boolean pOrdenAscendente, boolean pPermitirBorrar) {
    setCampoDesde(pCampoDesde);
    setCampoHasta(pCampoHasta);
    setNivelConsulta(pNivelConsulta);
    setOrdenadoPor(pOrdenadoPor);
    setOrdenAscendente(pOrdenAscendente);
    setPermitirBorrar(pPermitirBorrar);
    setOmitirOpcionConsulta(false);
  }
  public Campo getCampoDesde() {
    return this.aCampoDesde;
  }
  public void setCampoDesde(Campo pCampoDesde) {
    this.aCampoDesde = pCampoDesde;
  }
  public int getNivelConsulta() {
    return this.aNivelConsulta;
  }
  public void setNivelConsulta(int pNivelConsulta) {
    this.aNivelConsulta = pNivelConsulta;
  }
  public Campo getCampoHasta() {
    return this.aCampoHasta;
  }
  public void setCampoHasta(Campo pCampoHasta) {
    this.aCampoHasta = pCampoHasta;
  }
  public boolean esOrdenAscendente() {
    return this.aOrdenAscendente;
  }
  public void setOrdenAscendente(boolean pOrdenAscendente) {
    this.aOrdenAscendente = pOrdenAscendente;
  }
  public boolean esOrdenadoPor() {
    return this.aOrdenadoPor;
  }
  public void setOrdenadoPor(boolean pOrdenadoPor) {
    this.aOrdenadoPor = pOrdenadoPor;
  }
  public boolean esPermitirBorrar() {
    return this.aPermitirBorrar;
  }
  public void setPermitirBorrar(boolean pPermitirBorrar) {
    this.aPermitirBorrar = pPermitirBorrar;
  }
  public boolean omitirOpcionConsulta() {
    return this.aOmitirOpcionConsulta;
  }
  public void setOmitirOpcionConsulta(boolean pOmitirOpcionConsulta) {
    this.aOmitirOpcionConsulta = pOmitirOpcionConsulta;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\ItemConsulta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */