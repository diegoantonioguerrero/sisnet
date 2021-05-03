package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
public class SubtotalReporte
{
  private String aDescripcionInicialSubreporte;
  private String aDescripcionFinalSubreporte;
  private Campo aCampoDetalle;
  private ListaCampo aListaCamposSubtotal;
  private String aValorCampo;
  private boolean aModificoValor;
  private int aNumeroFilaCambio;
  public SubtotalReporte(String pDesripcionInicialSubreporte, String pDesripcionFinalSubreporte, Campo pcampoDetalle, ListaCampo pListaCamposSubtotal) {
    setDesripcionInicialSubreporte(pDesripcionInicialSubreporte);
    setDesripcionFinalSubreporte(pDesripcionFinalSubreporte);
    setCampoDetalle(pcampoDetalle);
    setListaCamposSubtotal(pListaCamposSubtotal);
    setValorCampo("");
    setModificoValor(false);
    setNumeroFilaCambio(-1);
  }
  public String getDesripcionInicialSubreporte() {
    return this.aDescripcionInicialSubreporte;
  }
  public void setDesripcionInicialSubreporte(String pDesripcionInicialSubreporte) {
    this.aDescripcionInicialSubreporte = pDesripcionInicialSubreporte;
  }
  public String getDesripcionFinalSubreporte() {
    return this.aDescripcionFinalSubreporte;
  }
  public void setDesripcionFinalSubreporte(String pDesripcionFinalSubreporte) {
    this.aDescripcionFinalSubreporte = pDesripcionFinalSubreporte;
  }
  public Campo getCampoDetalle() {
    return this.aCampoDetalle;
  }
  public void setCampoDetalle(Campo pCampoDetalle) {
    this.aCampoDetalle = pCampoDetalle;
  }
  public ListaCampo getListaCamposSubtotal() {
    return this.aListaCamposSubtotal;
  }
  public void setListaCamposSubtotal(ListaCampo pListaCamposSubtotal) {
    this.aListaCamposSubtotal = pListaCamposSubtotal;
  }
  public String getValorCampo() {
    return this.aValorCampo;
  }
  public void setValorCampo(String pValorCampo) {
    this.aValorCampo = pValorCampo;
  }
  public boolean esModificoValor() {
    return this.aModificoValor;
  }
  public void setModificoValor(boolean pModificoValor) {
    this.aModificoValor = pModificoValor;
  }
  public int getNumeroFilaCambio() {
    return this.aNumeroFilaCambio;
  }
  public void setNumeroFilaCambio(int pNumeroFilaCambio) {
    this.aNumeroFilaCambio = pNumeroFilaCambio;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\SubtotalReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */