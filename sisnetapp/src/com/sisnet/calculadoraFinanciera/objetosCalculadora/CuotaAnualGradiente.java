package com.sisnet.calculadoraFinanciera.objetosCalculadora;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.listas.ListaCuotasAnuales;
public class CuotaAnualGradiente
{
  private double aTasaEfectivaAnual;
  private ListaCuotasAnuales aListaCuotasAnuales;
  public double getTasaEfectivaAnual() {
    return this.aTasaEfectivaAnual;
  }
  public void setTasaEfectivaAnual(double pTasaEfectivaAnual) {
    this.aTasaEfectivaAnual = pTasaEfectivaAnual;
  }
  public ListaCuotasAnuales getListaCuotasAnuales() {
    return this.aListaCuotasAnuales;
  }
  public void setListaCuotasAnuales(ListaCuotasAnuales pListaCuotasAnuales) {
    this.aListaCuotasAnuales = pListaCuotasAnuales;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\objetosCalculadora\CuotaAnualGradiente.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */