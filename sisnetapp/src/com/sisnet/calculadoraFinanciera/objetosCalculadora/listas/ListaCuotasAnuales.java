package com.sisnet.calculadoraFinanciera.objetosCalculadora.listas;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.CuotaAnual;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Iterator;
public class ListaCuotasAnuales
  extends Lista
{
  public void adicionar(int pAnoCuota, double pValorCuota) {
    try {
      adicionar(new CuotaAnual(pAnoCuota, pValorCuota));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public double obtenerValorCuotaPorAno(int pAnoCuota) {
    double valorCuota_local = 0.0D;
    Iterator iterador_local = null;
    CuotaAnual cuotaAnual_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        cuotaAnual_local = (CuotaAnual)iterador_local.next();
        if (cuotaAnual_local.getAnoCuota() == pAnoCuota) {
          valorCuota_local = cuotaAnual_local.getValorCuota();
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      cuotaAnual_local = null;
    } 
    
    return valorCuota_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\objetosCalculadora\listas\ListaCuotasAnuales.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */