package com.sisnet.calculadoraFinanciera.recursosCalculadora;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.CuotaAnualGradiente;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.CuotaInversionOReintegro;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.DatosTasaInternaDeRetorno;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.TasaEfectivaAnualValor;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.listas.ListaCuotaInversionOReintegro;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.listas.ListaCuotasAnuales;
import com.sisnet.calculadoraFinanciera.recursosCalculadora.ManejadorRecursosCalculadora;
import com.sisnet.calculadoraFinanciera.recursosCalculadora.OperacionesBasicas;
import com.sisnet.constantes.ConstantesGeneral;
import java.sql.Date;
//import java.util.Date;
import java.util.Iterator;
public class OperacionesFinancieras
{
  private static OperacionesBasicas ob = OperacionesBasicas.getOperacionesBasicas();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorRecursosCalculadora rc = ManejadorRecursosCalculadora.getManejadorRecursosCalculadora();
  private double convertirATasaEfectivaAnual(double pTasaConvertir, double pDiasInteres, String pTipoInteres) {
    double tasaEfectivaAnual_local = 0.0D;
    double tasa_local = 0.0D;
    double n_local = 0.0D;
    double a_local = 0.0D;
    
    if (pTipoInteres == null) {
      return tasaEfectivaAnual_local;
    }
    if (pDiasInteres == 0.0D) {
      return tasaEfectivaAnual_local;
    }
    
    try {
      tasa_local = pTasaConvertir / 100.0D;
      n_local = 360.0D / pDiasInteres;
      a_local = tasa_local / n_local;
      if (mc.sonCadenasIgualesIgnorarMayusculas(pTipoInteres, "A")) {
        tasaEfectivaAnual_local = (ob.elevarNumero(1.0D / (1.0D - a_local), n_local) - 1.0D) * 100.0D;
      }
      else {
        
        tasaEfectivaAnual_local = (ob.elevarNumero(a_local + 1.0D, n_local) - 1.0D) * 100.0D;
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tasaEfectivaAnual_local;
  }
  private double convertirLaTasaEfectivaAnualAAnticipadaOVencida(double pTasaEfectivaAnual, int pDiasInteres, String pTipoInteres) {
    double tasaEfectiva_local = 0.0D;
    double tasaEfectivaAnual_local = 0.0D;
    double n_local = 0.0D;
    
    if (pTipoInteres == null) {
      return tasaEfectiva_local;
    }
    if (pDiasInteres == 0) {
      return tasaEfectiva_local;
    }
    
    try {
      tasaEfectivaAnual_local = pTasaEfectivaAnual / 100.0D;
      n_local = 360.0D / pDiasInteres;
      if (mc.sonCadenasIgualesIgnorarMayusculas(pTipoInteres, "A")) {
        tasaEfectiva_local = n_local * (1.0D - ob.elevarNumero(1.0D + tasaEfectivaAnual_local, -1.0D / n_local)) * 100.0D;
      }
      else {
        
        tasaEfectiva_local = n_local * (ob.elevarNumero(1.0D + tasaEfectivaAnual_local, 1.0D / n_local) - 1.0D) * 100.0D;
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tasaEfectiva_local;
  }
  public double convertirTasasInteres(double pTasaConvertir, int pDiasInteresOrigen, String pTipoInteresOrigen, int pDiasInteresDestino, String pTipoInteresDestino) {
    double tasaConvertida_local = 0.0D;
    
    if (pTipoInteresOrigen == null) {
      return tasaConvertida_local;
    }
    if (pTipoInteresDestino == null) {
      return tasaConvertida_local;
    }
    
    try {
      tasaConvertida_local = ob.truncarAMilesimas(convertirLaTasaEfectivaAnualAAnticipadaOVencida(convertirATasaEfectivaAnual(pTasaConvertir, pDiasInteresOrigen, pTipoInteresOrigen), pDiasInteresDestino, pTipoInteresDestino));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tasaConvertida_local;
  }
  private double obtenerFactorAnualidad(double pTasaNominal, int pPeriodo, int pNumeroCuotas) {
    double factorAnualidad_local = 0.0D;
    double tasaEfectivaSegunPeriodicidad_local = 0.0D;
    
    try {
      tasaEfectivaSegunPeriodicidad_local = pTasaNominal / 100.0D / 360.0D / rc.obtenerDiasDelPeriodo(pPeriodo);
      
      factorAnualidad_local = tasaEfectivaSegunPeriodicidad_local / (1.0D - ob.elevarNumero(1.0D + tasaEfectivaSegunPeriodicidad_local, -pNumeroCuotas));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return factorAnualidad_local;
  }
  public double calcularCuotaFijaAnual(double pMontoPrestamo, double pTasaNominal, int pPeriodo, int pNumeroCuotas) {
    double anualidad_local = 0.0D;
    
    if (pMontoPrestamo == 0.0D) {
      return anualidad_local;
    }
    if (pTasaNominal == 0.0D) {
      return anualidad_local;
    }
    
    try {
      anualidad_local = ob.truncarAMilesimas(pMontoPrestamo * obtenerFactorAnualidad(pTasaNominal, pPeriodo, pNumeroCuotas));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return anualidad_local;
  }
  private double obtenerFactorValorPresente(double pTasaEfectiva, int pDiasTranslado) {
    double factorValorPresente_local = 0.0D;
    double tasaEfectiva_local = 0.0D;
    
    try {
      tasaEfectiva_local = pTasaEfectiva / 100.0D;
      factorValorPresente_local = ob.elevarNumero(1.0D + tasaEfectiva_local, pDiasTranslado / 360.0D);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return factorValorPresente_local;
  }
  public TasaEfectivaAnualValor calcularValorPresente(double pValorFuturo, double pTasa, int pPeriodo, String pTipoInteres, int pDiasTranslado) {
    TasaEfectivaAnualValor tasaEfectivaAnualValor_local = null;
    double valorPresente_local = 0.0D;
    double tasaEfectivaAnual_local = 0.0D;
    
    if (pTipoInteres == ConstantesGeneral.VALOR_NULO) {
      return tasaEfectivaAnualValor_local;
    }
    
    try {
      tasaEfectivaAnual_local = ob.truncarAMilesimas(convertirATasaEfectivaAnual(pTasa, rc.obtenerDiasDelPeriodo(pPeriodo), pTipoInteres));
      
      valorPresente_local = ob.truncarAMilesimas(pValorFuturo / obtenerFactorValorPresente(tasaEfectivaAnual_local, pDiasTranslado));
      tasaEfectivaAnualValor_local = new TasaEfectivaAnualValor(tasaEfectivaAnual_local, valorPresente_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tasaEfectivaAnualValor_local;
  }
  public TasaEfectivaAnualValor calcularValorFuturo(double pValorPresente, double pTasa, int pPeriodo, String pTipoInteres, int pDiasTranslado) {
    TasaEfectivaAnualValor tasaEfectivaAnualValor_local = null;
    double valorFuturo_local = 0.0D;
    double tasaEfectivaAnual_local = 0.0D;
    
    if (pTipoInteres == ConstantesGeneral.VALOR_NULO) {
      return tasaEfectivaAnualValor_local;
    }
    
    try {
      tasaEfectivaAnual_local = ob.truncarAMilesimas(convertirATasaEfectivaAnual(pTasa, rc.obtenerDiasDelPeriodo(pPeriodo), pTipoInteres));
      
      valorFuturo_local = ob.truncarAMilesimas(pValorPresente * obtenerFactorValorPresente(tasaEfectivaAnual_local, pDiasTranslado));
      tasaEfectivaAnualValor_local = new TasaEfectivaAnualValor(tasaEfectivaAnual_local, valorFuturo_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tasaEfectivaAnualValor_local;
  }
  public CuotaAnualGradiente calcularCuotaFijaAnualGradiente(double pMontoPrestamo, double pTasaNominal, int pPeriodo, double pIncrementoAnual, int pPlazoEnAnios) {
    CuotaAnualGradiente cuotaAnualGradiente_local = null;
    int numeroPeriodosAnio_local = 0;
    int contador_local = 0;
    double tasaEfectivaAnual_local = 0.0D;
    double factorUno_local = 0.0D;
    double fnip_local = 0.0D;
    double tasaEfectivaAnualSobreCien_local = 0.0D;
    double incrementoAnualSobreCien_local = 0.0D;
    double factorDosNumerador_local = 0.0D;
    double factorDosDivisor_local = 0.0D;
    double valorCuota_local = 0.0D;
    double valorCuotaAnterior_local = 0.0D;
    ListaCuotasAnuales listaCuotasAnuales_local = null;
    
    if (pMontoPrestamo <= 0.0D) {
      return cuotaAnualGradiente_local;
    }
    if (pTasaNominal <= 0.0D) {
      return cuotaAnualGradiente_local;
    }
    
    try {
      numeroPeriodosAnio_local = 360 / rc.obtenerDiasDelPeriodo(pPeriodo);
      tasaEfectivaAnual_local = ob.truncarAMilesimas(convertirATasaEfectivaAnual(pTasaNominal, rc.obtenerDiasDelPeriodo(pPeriodo), "V"));
      
      factorUno_local = pTasaNominal / 100.0D / numeroPeriodosAnio_local;
      fnip_local = (ob.elevarNumero(1.0D + factorUno_local, numeroPeriodosAnio_local) - 1.0D) / factorUno_local;
      
      tasaEfectivaAnualSobreCien_local = tasaEfectivaAnual_local / 100.0D;
      incrementoAnualSobreCien_local = pIncrementoAnual / 100.0D;
      factorDosNumerador_local = tasaEfectivaAnualSobreCien_local - incrementoAnualSobreCien_local;
      factorDosDivisor_local = 1.0D - ob.elevarNumero((1.0D + incrementoAnualSobreCien_local) / (1.0D + tasaEfectivaAnualSobreCien_local), pPlazoEnAnios);
      
      if (factorDosDivisor_local != 0.0D && fnip_local != 0.0D) {
        cuotaAnualGradiente_local = new CuotaAnualGradiente();
        cuotaAnualGradiente_local.setTasaEfectivaAnual(tasaEfectivaAnual_local);
        listaCuotasAnuales_local = new ListaCuotasAnuales();
        
        for (contador_local = 0; contador_local < pPlazoEnAnios; contador_local++) {
          if (contador_local == 0) {
            valorCuota_local = ob.obtenerParteEntera(pMontoPrestamo * factorDosNumerador_local / factorDosDivisor_local / fnip_local).doubleValue();
            
            listaCuotasAnuales_local.adicionar(contador_local + 1, valorCuota_local);
          } else {
            valorCuota_local = ob.obtenerParteEntera(valorCuotaAnterior_local * (1.0D + incrementoAnualSobreCien_local)).doubleValue();
            
            listaCuotasAnuales_local.adicionar(contador_local + 1, valorCuota_local);
          } 
          valorCuotaAnterior_local = valorCuota_local;
        } 
        cuotaAnualGradiente_local.setListaCuotasAnuales(listaCuotasAnuales_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCuotasAnuales_local = null;
    } 
    
    return cuotaAnualGradiente_local;
  }
  private double obtenerValorFuturoListaValores(ListaCuotaInversionOReintegro pListaCuotaInversionOReintegro, double pPorcentajeActual) {
    double valorFuturoListaValores_local = 0.0D;
    double sumaTemporal_local = 0.0D;
    Iterator iterador_local = null;
    java.util.Date fechaFinalNegocio_local = null;
    Date fechaFinalEquivalente_local = null;
    Date fechaCuotaEquivalente_local = null;
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    if (pListaCuotaInversionOReintegro == ConstantesGeneral.VALOR_NULO) {
      return valorFuturoListaValores_local;
    }
    
    try {
      fechaFinalNegocio_local = pListaCuotaInversionOReintegro.obtenerFechaUltimaCuota();
      fechaFinalEquivalente_local = new Date(fechaFinalNegocio_local.getTime());
      iterador_local = pListaCuotaInversionOReintegro.iterator();
      while (iterador_local.hasNext()) {
        cuotaInversionOReintegro_local = (CuotaInversionOReintegro)iterador_local.next();
        if (cuotaInversionOReintegro_local.getValorCuota() > 0.0D) {
          if (cuotaInversionOReintegro_local.getFechaCuota() != ConstantesGeneral.VALOR_NULO) {
            fechaCuotaEquivalente_local = new Date(cuotaInversionOReintegro_local.getFechaCuota().getTime());
          } else {
            fechaCuotaEquivalente_local = null;
          } 
          sumaTemporal_local += cuotaInversionOReintegro_local.getValorCuota() * obtenerFactorValorPresente(pPorcentajeActual, mf.restarFechas(fechaFinalEquivalente_local, fechaCuotaEquivalente_local));
        } 
      } 
      
      valorFuturoListaValores_local = sumaTemporal_local;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      fechaFinalNegocio_local = null;
      cuotaInversionOReintegro_local = null;
    } 
    
    return valorFuturoListaValores_local;
  }
  private double calcularTasaInternaDeRetornoDesdeHasta(ListaCuotaInversionOReintegro pListaCuotaInversion, ListaCuotaInversionOReintegro pListaCuotaReintegro, double pPorcentajeDesde, double pPorcentajeHasta) {
    double tasaInternaDeRetorno_local = -1.0D;
    double porcentajeActual_local = 0.0D;
    double porcentajeDesde_local = 0.0D;
    double porcentajeHasta_local = 0.0D;
    
    try {
      porcentajeDesde_local = pPorcentajeDesde;
      porcentajeHasta_local = pPorcentajeHasta;
      porcentajeActual_local = porcentajeHasta_local;
      if (obtenerValorFuturoListaValores(pListaCuotaInversion, porcentajeActual_local) < obtenerValorFuturoListaValores(pListaCuotaReintegro, porcentajeActual_local))
      {
        return tasaInternaDeRetorno_local;
      }
      while (Math.abs(porcentajeDesde_local - porcentajeHasta_local) > 0.001D) {
        porcentajeActual_local = (porcentajeDesde_local + porcentajeHasta_local) / 2.0D;
        if (obtenerValorFuturoListaValores(pListaCuotaInversion, porcentajeActual_local) >= obtenerValorFuturoListaValores(pListaCuotaReintegro, porcentajeActual_local)) {
          
          porcentajeHasta_local = porcentajeActual_local; continue;
        } 
        porcentajeDesde_local = porcentajeActual_local;
      } 
      
      tasaInternaDeRetorno_local = ob.truncarAMilesimas(porcentajeHasta_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tasaInternaDeRetorno_local;
  }
  public DatosTasaInternaDeRetorno calcularTasaInternaDeRetorno(Date pFechaInicialNegocio, Date pFechaFinalNegocio, int pDiasPeriodo, ListaCuotaInversionOReintegro pListaCuotaInversion, ListaCuotaInversionOReintegro pListaCuotaReintegro) {
    DatosTasaInternaDeRetorno datosTasaInternaRetorno_local = null;
    double tasaInternaRetorno_local = -1.0D;
    
    if (pFechaInicialNegocio == ConstantesGeneral.VALOR_NULO) {
      return datosTasaInternaRetorno_local;
    }
    if (pFechaFinalNegocio == ConstantesGeneral.VALOR_NULO) {
      return datosTasaInternaRetorno_local;
    }
    if (pListaCuotaInversion == ConstantesGeneral.VALOR_NULO) {
      return datosTasaInternaRetorno_local;
    }
    if (pListaCuotaReintegro == ConstantesGeneral.VALOR_NULO) {
      return datosTasaInternaRetorno_local;
    }
    
    try {
      datosTasaInternaRetorno_local = new DatosTasaInternaDeRetorno();
      pListaCuotaInversion.recalcularFechaCuotas(pFechaInicialNegocio, pFechaFinalNegocio, pDiasPeriodo);
      pListaCuotaReintegro.recalcularFechaCuotas(pFechaInicialNegocio, pFechaFinalNegocio, pDiasPeriodo);
      tasaInternaRetorno_local = calcularTasaInternaDeRetornoDesdeHasta(pListaCuotaInversion, pListaCuotaReintegro, 0.0D, 100.0D);
      
      if (tasaInternaRetorno_local == -1.0D) {
        tasaInternaRetorno_local = calcularTasaInternaDeRetornoDesdeHasta(pListaCuotaInversion, pListaCuotaReintegro, 99.0D, 500.0D);
      }
      
      if (tasaInternaRetorno_local == -1.0D) {
        tasaInternaRetorno_local = calcularTasaInternaDeRetornoDesdeHasta(pListaCuotaInversion, pListaCuotaReintegro, 499.0D, 10000.0D);
      }
      
      if (tasaInternaRetorno_local != -1.0D) {
        datosTasaInternaRetorno_local.setTasaInternaDeRetorno(tasaInternaRetorno_local);
      } else {
        datosTasaInternaRetorno_local.setTasaInternaDeRetorno(10000.0D);
      } 
      datosTasaInternaRetorno_local.setTotalInversion(pListaCuotaInversion.obtenerSumaValoresCuotas());
      datosTasaInternaRetorno_local.setTotalReintegro(pListaCuotaReintegro.obtenerSumaValoresCuotas());
      datosTasaInternaRetorno_local.setGanaciaNegocio(datosTasaInternaRetorno_local.getTotalReintegro() - datosTasaInternaRetorno_local.getTotalInversion());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return datosTasaInternaRetorno_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\recursosCalculadora\OperacionesFinancieras.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */