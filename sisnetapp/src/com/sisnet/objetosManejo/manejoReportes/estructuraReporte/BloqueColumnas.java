package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaColumnasReporte;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaOrdenReporte;
public class BloqueColumnas
{
  private boolean aMostrarDetalle;
  private ListaColumnasReporte aListaColumnasReporte;
  private ListaOrdenReporte aListaOrdenReporte;
  public BloqueColumnas(boolean pMostrarDetalles, ListaColumnasReporte pListaColumnasReporte, ListaOrdenReporte pListaOrdenReporte) {
    setMostrarDetalle(pMostrarDetalles);
    setListaColumnasReporte(pListaColumnasReporte);
    setListaOrdenReporte(pListaOrdenReporte);
  }
  public boolean esMostrarDetalle() {
    return this.aMostrarDetalle;
  }
  public void setMostrarDetalle(boolean pMostrarDetalle) {
    this.aMostrarDetalle = pMostrarDetalle;
  }
  public ListaColumnasReporte getListaColumnasReporte() {
    return this.aListaColumnasReporte;
  }
  public void setListaColumnasReporte(ListaColumnasReporte pListaColumnasReporte) {
    this.aListaColumnasReporte = pListaColumnasReporte;
  }
  public ListaOrdenReporte getListaOrdenReporte() {
    return this.aListaOrdenReporte;
  }
  public void setListaOrdenReporte(ListaOrdenReporte pListaOrdenReporte) {
    this.aListaOrdenReporte = pListaOrdenReporte;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\BloqueColumnas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */