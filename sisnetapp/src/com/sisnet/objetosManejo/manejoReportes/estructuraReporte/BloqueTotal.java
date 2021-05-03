package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
public class BloqueTotal
{
  private ListaCampo aListaCampo;
  public BloqueTotal(ListaCampo pListaCampo) {
    setListaCampo(pListaCampo);
  }
  public ListaCampo getListaCampo() {
    return this.aListaCampo;
  }
  public void setListaCampo(ListaCampo pListaCampo) {
    this.aListaCampo = pListaCampo;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\BloqueTotal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */