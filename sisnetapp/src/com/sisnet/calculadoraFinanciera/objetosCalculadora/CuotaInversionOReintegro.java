package com.sisnet.calculadoraFinanciera.objetosCalculadora;
import java.io.Serializable;
import java.util.Date;
public class CuotaInversionOReintegro
  implements Serializable
{
  private int aNumeroCuota;
  private Date aFechaCuota;
  private double aValorCuota;
  public CuotaInversionOReintegro() {
    setFechaCuota(null);
    setNumeroCuota(-1);
    setValorCuota(0.0D);
  }
  public CuotaInversionOReintegro(int pNumeroCuota, Date pFechaCuota, double pValorCuota) {
    setNumeroCuota(pNumeroCuota);
    setFechaCuota(pFechaCuota);
    setValorCuota(pValorCuota);
  }
  public int getNumeroCuota() {
    return this.aNumeroCuota;
  }
  public void setNumeroCuota(int pNumeroCuota) {
    this.aNumeroCuota = pNumeroCuota;
  }
  public Date getFechaCuota() {
    return this.aFechaCuota;
  }
  public void setFechaCuota(Date pFechaCuota) {
    this.aFechaCuota = pFechaCuota;
  }
  public double getValorCuota() {
    return this.aValorCuota;
  }
  public void setValorCuota(double pValorCuota) {
    this.aValorCuota = pValorCuota;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\objetosCalculadora\CuotaInversionOReintegro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */