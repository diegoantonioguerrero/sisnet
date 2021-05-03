package com.sisnet.calculadoraFinanciera.objetosCalculadora.listas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.aplicacion.manejadores.ManejadorOperaciones;
import com.sisnet.calculadoraFinanciera.objetosCalculadora.CuotaInversionOReintegro;
import com.sisnet.calculadoraFinanciera.recursosCalculadora.ManejadorRecursosCalculadora;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import java.io.Serializable;
import java.sql.Date;
//import java.util.Date;
import java.util.Iterator;
public class ListaCuotaInversionOReintegro
  extends Lista
  implements Serializable
{
  private static ManejadorOperaciones op = ManejadorOperaciones.getManejadorOperaciones();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private static ManejadorRecursosCalculadora rc = ManejadorRecursosCalculadora.getManejadorRecursosCalculadora();
  public void adicionar(CuotaInversionOReintegro pCuotaInversionOReintegro) {
    if (pCuotaInversionOReintegro == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      adicionar(pCuotaInversionOReintegro);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public CuotaInversionOReintegro obtenerCuotaInversionOReintegroPorPosicion(int pPosicionCuotaInversionOReintegro) {
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    if (pPosicionCuotaInversionOReintegro >= contarElementos()) {
      return cuotaInversionOReintegro_local;
    }
    
    try {
      cuotaInversionOReintegro_local = (CuotaInversionOReintegro)get(pPosicionCuotaInversionOReintegro);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cuotaInversionOReintegro_local;
  }
  public void llenarListaSegunFechas(Date pFechaInicialNegocio, Date pFechaFinalNegocio, int pDiasPeriodo) {
    int diasNegocio_local = 0;
    int diasPeriodo_local = 0;
    int cantidadPeriodos_local = 0;
    int numeroCuota_local = 0;
    Date fechaCuota_local = null;
    Date fechaCuotaEquivalente_local = null;
    Date fechaInicialNegocio_local = null;
    Date fechaFinalNegocio_local = null;
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    if (pFechaInicialNegocio == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFechaFinalNegocio == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDiasPeriodo <= 0) {
      return;
    }
    
    try {
      fechaInicialNegocio_local = new Date(pFechaInicialNegocio.getTime());
      fechaFinalNegocio_local = new Date(pFechaFinalNegocio.getTime());
      diasNegocio_local = mf.restarFechas(fechaFinalNegocio_local, fechaInicialNegocio_local);
      diasPeriodo_local = pDiasPeriodo;
      cantidadPeriodos_local = diasNegocio_local / pDiasPeriodo;
      fechaCuotaEquivalente_local = fechaInicialNegocio_local;
      fechaCuota_local = new Date(fechaCuotaEquivalente_local.getTime());
      for (numeroCuota_local = 0; numeroCuota_local <= cantidadPeriodos_local; numeroCuota_local++) {
        cuotaInversionOReintegro_local = new CuotaInversionOReintegro();
        cuotaInversionOReintegro_local.setNumeroCuota(numeroCuota_local);
        cuotaInversionOReintegro_local.setFechaCuota(fechaCuota_local);
        adicionar(cuotaInversionOReintegro_local);
        diasPeriodo_local = rc.obtenerDiasEquivalentesPeriodo(fechaCuota_local, pDiasPeriodo);
        fechaCuotaEquivalente_local = mf.sumarDiasFecha(fechaCuotaEquivalente_local, diasPeriodo_local);
        fechaCuota_local = new Date(fechaCuotaEquivalente_local.getTime());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fechaCuota_local = null;
      fechaFinalNegocio_local = null;
      fechaInicialNegocio_local = null;
      fechaCuotaEquivalente_local = null;
      cuotaInversionOReintegro_local = null;
    } 
  }
  private void reconfigurarTamanoLista(int pTamanoLista) {
    int i_local = 0;
    Iterator iterador_local = null;
    
    try {
      if (contarElementos() > pTamanoLista) {
        for (i_local = 0; iterador_local.hasNext(); i_local++) {
          iterador_local.next();
          if (i_local > pTamanoLista) {
            iterador_local.remove();
          }
        } 
      } else {
        while (contarElementos() < pTamanoLista) {
          adicionar(new CuotaInversionOReintegro());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
  }
  public void recalcularFechaCuotas(Date pFechaInicialNegocio, Date pFechaFinalNegocio, int pDiasPeriodo) {
    int diasNegocio_local = 0;
    int diasPeriodo_local = 0;
    int cantidadPeriodos_local = 0;
    int numeroCuota_local = 0;
    Date fechaCuota_local = null;
    Date fechaCuotaEquivalente_local = null;
    Date fechaInicialNegocio_local = null;
    Date fechaFinalNegocio_local = null;
    Iterator iterador_local = null;
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    if (pFechaInicialNegocio == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFechaFinalNegocio == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDiasPeriodo <= 0) {
      return;
    }
    
    try {
      fechaInicialNegocio_local = new Date(pFechaInicialNegocio.getTime());
      fechaFinalNegocio_local = new Date(pFechaFinalNegocio.getTime());
      diasNegocio_local = mf.restarFechas(fechaFinalNegocio_local, fechaInicialNegocio_local);
      diasPeriodo_local = pDiasPeriodo;
      cantidadPeriodos_local = diasNegocio_local / pDiasPeriodo;
      if (contarElementos() != cantidadPeriodos_local + 1) {
        reconfigurarTamanoLista(cantidadPeriodos_local + 1);
      }
      iterador_local = iterator();
      fechaCuotaEquivalente_local = fechaInicialNegocio_local;
      fechaCuota_local = new Date(fechaCuotaEquivalente_local.getTime());
      for (numeroCuota_local = 0; iterador_local.hasNext(); numeroCuota_local++) {
        cuotaInversionOReintegro_local = (CuotaInversionOReintegro)iterador_local.next();
        cuotaInversionOReintegro_local.setNumeroCuota(numeroCuota_local);
        cuotaInversionOReintegro_local.setFechaCuota(fechaCuota_local);
        diasPeriodo_local = rc.obtenerDiasEquivalentesPeriodo(fechaCuota_local, pDiasPeriodo);
        fechaCuotaEquivalente_local = mf.sumarDiasFecha(fechaCuotaEquivalente_local, diasPeriodo_local);
        fechaCuota_local = new Date(fechaCuotaEquivalente_local.getTime());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      fechaCuota_local = null;
      fechaFinalNegocio_local = null;
      fechaInicialNegocio_local = null;
      fechaCuotaEquivalente_local = null;
      cuotaInversionOReintegro_local = null;
    } 
  }
  public int obtenerNumeroUltimaCuota() {
    int numeroUltimaCuota_local = 0;
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    try {
      if (contarElementos() > 0) {
        cuotaInversionOReintegro_local = obtenerCuotaInversionOReintegroPorPosicion(contarElementos() - 1);
        
        numeroUltimaCuota_local = cuotaInversionOReintegro_local.getNumeroCuota();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cuotaInversionOReintegro_local = null;
    } 
    
    return numeroUltimaCuota_local;
  }
  public java.util.Date obtenerFechaUltimaCuota() {
    java.util.Date fechaUltimaCuota_local = null;
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    try {
      if (contarElementos() > 0) {
        cuotaInversionOReintegro_local = obtenerCuotaInversionOReintegroPorPosicion(contarElementos() - 1);
        
        fechaUltimaCuota_local = cuotaInversionOReintegro_local.getFechaCuota();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cuotaInversionOReintegro_local = null;
    } 
    
    return fechaUltimaCuota_local;
  }
  public double obtenerSumaValoresCuotas() {
    double sumaValoresCuotas_local = 0.0D;
    Iterator iterador_local = null;
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        cuotaInversionOReintegro_local = (CuotaInversionOReintegro)iterador_local.next();
        sumaValoresCuotas_local += cuotaInversionOReintegro_local.getValorCuota();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return sumaValoresCuotas_local;
  }
  public void asignarValorCuotaPeriodo(double pValorCuota, int pPeriodo) {
    CuotaInversionOReintegro cuotaInversionOReintegro_local = null;
    
    try {
      if (contarElementos() > 0) {
        cuotaInversionOReintegro_local = obtenerCuotaInversionOReintegroPorPosicion(pPeriodo);
        cuotaInversionOReintegro_local.setValorCuota(pValorCuota);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cuotaInversionOReintegro_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\calculadoraFinanciera\objetosCalculadora\listas\ListaCuotaInversionOReintegro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */