package com.sisnet.calculadoraFinanciera.objetosCalculadora;
import java.io.Serializable;
public class CuotaAnual
  implements Serializable
{
  private int aAnoCuota;
  private double aValorCuota;
  public CuotaAnual() {}
  public CuotaAnual(int pAnoCuota, double pValorCuota) {
    setAnoCuota(pAnoCuota);
    setValorCuota(pValorCuota);
  }
  public int getAnoCuota() {
    return this.aAnoCuota;
  }
  public void setAnoCuota(int pAnoCuota) {
    this.aAnoCuota = pAnoCuota;
  }
  public double getValorCuota() {
    return this.aValorCuota;
  }
  public void setValorCuota(double pValorCuota) {
    this.aValorCuota = pValorCuota;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\objetosCalculadora\CuotaAnual.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */