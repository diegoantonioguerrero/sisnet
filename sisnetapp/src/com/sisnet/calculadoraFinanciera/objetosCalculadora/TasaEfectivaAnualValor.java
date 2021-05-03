package com.sisnet.calculadoraFinanciera.objetosCalculadora;
public class TasaEfectivaAnualValor
{
  private double aTasaEfectivaAnual;
  private double aValor;
  public TasaEfectivaAnualValor() {
    setTasaEfectivaAnual(0.0D);
    setValor(0.0D);
  }
  public TasaEfectivaAnualValor(double aTasaEfectivaAnual, double aValor) {
    this.aTasaEfectivaAnual = aTasaEfectivaAnual;
    this.aValor = aValor;
  }
  public double getTasaEfectivaAnual() {
    return this.aTasaEfectivaAnual;
  }
  public void setTasaEfectivaAnual(double pTasaEfectivaAnual) {
    this.aTasaEfectivaAnual = pTasaEfectivaAnual;
  }
  public double getValor() {
    return this.aValor;
  }
  public void setValor(double pValor) {
    this.aValor = pValor;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\objetosCalculadora\TasaEfectivaAnualValor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */