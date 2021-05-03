package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaSubtotalReporte;
public class BloqueSubtotal
{
  private ListaSubtotalReporte aListaSubtotalReporte;
  public BloqueSubtotal(ListaSubtotalReporte pListaSubtotalReporte) {
    setListaSubtotalReporte(pListaSubtotalReporte);
  }
  public ListaSubtotalReporte getListaSubtotalReporte() {
    return this.aListaSubtotalReporte;
  }
  public void setListaSubtotalReporte(ListaSubtotalReporte pListaSubtotalReporte) {
    this.aListaSubtotalReporte = pListaSubtotalReporte;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\BloqueSubtotal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */