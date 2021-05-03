package com.sisnet.calculadoraFinanciera.recursosCalculadora;
import java.math.BigDecimal;
import java.math.BigInteger;
public class OperacionesBasicas
{
  private static com.sisnet.calculadoraFinanciera.recursosCalculadora.OperacionesBasicas operacionesBasicasSingleton = null;
  public static com.sisnet.calculadoraFinanciera.recursosCalculadora.OperacionesBasicas getOperacionesBasicas() {
    if (operacionesBasicasSingleton == null) {
      operacionesBasicasSingleton = new com.sisnet.calculadoraFinanciera.recursosCalculadora.OperacionesBasicas();
    }
    return operacionesBasicasSingleton;
  }
  public double elevarNumero(double pNumeroElevar, double pExponente) {
    double numero_local = 0.0D;
    
    try {
      numero_local = Math.exp(pExponente * Math.log(pNumeroElevar));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public BigInteger obtenerParteEntera(double pNumero) {
    BigInteger numeroEntero_local = null;
    BigDecimal numeroDecimal_local = null;
    
    try {
      numeroDecimal_local = new BigDecimal(pNumero);
      numeroDecimal_local.setScale(0, 0);
      numeroEntero_local = numeroDecimal_local.toBigInteger();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroDecimal_local = null;
    } 
    
    return numeroEntero_local;
  }
  public double truncarAMilesimas(double pNumeroTruncar) {
    double milesimas_local = 0.0D;
    
    try {
      milesimas_local = obtenerParteEntera(pNumeroTruncar * 100.0D + 0.5D).doubleValue();
      
      milesimas_local *= 0.01D;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return milesimas_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\recursosCalculadora\OperacionesBasicas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */