package com.sisnet.objetosManejo;
public class ItemLista
{
  private String aNombreItem;
  private String aValorItem;
  private boolean aSeleccionado;
  public ItemLista(String pNombreItem, String pValorItem, boolean pSeleccionado) {
    setNombreItem(pNombreItem);
    setValorItem(pValorItem);
    setSeleccionado(pSeleccionado);
  }
  public String getNombreItem() {
    return this.aNombreItem;
  }
  public void setNombreItem(String pNombreItem) {
    this.aNombreItem = pNombreItem;
  }
  public String getValorItem() {
    return this.aValorItem;
  }
  public void setValorItem(String pValorItem) {
    this.aValorItem = pValorItem;
  }
  public boolean esSeleccionado() {
    return this.aSeleccionado;
  }
  public void setSeleccionado(boolean pSeleccionado) {
    this.aSeleccionado = pSeleccionado;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\ItemLista.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */