package com.sisnet.calculadoraFinanciera.recursosCalculadora;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.constantes.ConstantesGeneral;
import java.sql.Date;
//import java.util.Date;
import java.util.GregorianCalendar;
public class ManejadorRecursosCalculadora
{
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static com.sisnet.calculadoraFinanciera.recursosCalculadora.ManejadorRecursosCalculadora manejadorRecursosCalculadoraSingleton = null;
  public static com.sisnet.calculadoraFinanciera.recursosCalculadora.ManejadorRecursosCalculadora getManejadorRecursosCalculadora() {
    if (manejadorRecursosCalculadoraSingleton == null) {
      manejadorRecursosCalculadoraSingleton = new com.sisnet.calculadoraFinanciera.recursosCalculadora.ManejadorRecursosCalculadora();
    }
    return manejadorRecursosCalculadoraSingleton;
  }
  public int obtenerDiasDelPeriodo(int pPeriodo) {
    int diasPeriodo_local = 0;
    
    try {
      switch (pPeriodo) {
        case 1:
          diasPeriodo_local = 30;
          break;
        case 2:
          diasPeriodo_local = 60;
          break;
        case 3:
          diasPeriodo_local = 90;
          break;
        case 4:
          diasPeriodo_local = 180;
          break;
        case 5:
          diasPeriodo_local = 360;
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return diasPeriodo_local;
  }
  private int obtenerDiasDelMes(Date pFecha) {
    int diasDelMes_local = 0;
    GregorianCalendar calendarioGregoriano_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return diasDelMes_local;
    }
    
    try {
      calendarioGregoriano_local = new GregorianCalendar();
      calendarioGregoriano_local.setTimeInMillis(pFecha.getTime());
      diasDelMes_local = calendarioGregoriano_local.getActualMaximum(5);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendarioGregoriano_local = null;
    } 
    
    return diasDelMes_local;
  }
  public int obtenerDiasEquivalentesPeriodo(Date pFecha, int pDiasPeriodo) {
    int diasPeriodo_local = 0;
    Date fecha_local = null;
    Date fechaEquivalente_local = null;
    
    try {
      diasPeriodo_local = pDiasPeriodo;
      if (diasPeriodo_local % 30 == 0) {
        diasPeriodo_local = obtenerDiasDelMes(pFecha);
        if (pDiasPeriodo / 30 > 1) {
          fechaEquivalente_local = new Date(pFecha.getTime());
          fechaEquivalente_local = mf.sumarDiasFecha(fechaEquivalente_local, diasPeriodo_local);
          fecha_local = new Date(fechaEquivalente_local.getTime());
          diasPeriodo_local += obtenerDiasEquivalentesPeriodo(fecha_local, pDiasPeriodo - 30);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fecha_local = null;
      fechaEquivalente_local = null;
    } 
    
    return diasPeriodo_local;
  }
  public boolean esPeriodoValido(int pPeriodo) {
    boolean periodoValido_local = false;
    
    try { switch (pPeriodo)
      { case 1:
        case 2:
        case 3:
        case 4:
        case 5:
          periodoValido_local = true;
          
          return periodoValido_local; }  periodoValido_local = false; } catch (Exception excepcion) { excepcion.printStackTrace(); }  return periodoValido_local;
  }
  public boolean esTipoInteresValido(String pTipoInteres) {
    boolean interesValido_local = false;
    
    if (pTipoInteres == ConstantesGeneral.VALOR_NULO) {
      return interesValido_local;
    }
    
    try {
      interesValido_local = (mc.sonCadenasIgualesIgnorarMayusculas(pTipoInteres, "A") || mc.sonCadenasIgualesIgnorarMayusculas(pTipoInteres, "V"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return interesValido_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\recursosCalculadora\ManejadorRecursosCalculadora.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */