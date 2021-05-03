package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
//import java.util.Date;
public class ManejadorFechas
{
  private static com.sisnet.aplicacion.manejadores.ManejadorFechas manejadorFechas;
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public static com.sisnet.aplicacion.manejadores.ManejadorFechas getManejadorFechas() {
    if (manejadorFechas == null) {
      manejadorFechas = new com.sisnet.aplicacion.manejadores.ManejadorFechas();
    }
    
    return manejadorFechas;
  }
  public String obtenerFechaActualSistema(boolean pFormatoCorto) {
    String fechaActual_local = "";
    SimpleDateFormat simpleDateFormat_local = null;
    String fecha_local = null;
    Date fechaSistema_local = null;
    
    try {
      simpleDateFormat_local = new SimpleDateFormat("EEEEEEEEEE, dd MMMMMMMMM yyyy hh:mm aa ");
      if (pFormatoCorto) {
        simpleDateFormat_local = new SimpleDateFormat("yyyy-MM-dd");
      }
      fechaSistema_local = new java.sql.Date(new java.util.Date().getTime());
      fecha_local = simpleDateFormat_local.format(fechaSistema_local);
      fechaActual_local = mc.concatenarCadena(fechaActual_local, fecha_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      simpleDateFormat_local = null;
      fecha_local = null;
      fechaSistema_local = null;
    } 
    
    return fechaActual_local;
  }
  public String obtenerFechaUltimoDiaMesSiguiente() {
    String fechaUltimoDia_local = "";
    int ultimoDia_local = -1;
    String fechaActual_local = null;
    Calendar fecha_local = null;
    Date fechaSistema_local = null;
    SimpleDateFormat simpleDateFormat_local = null;
    
    try {
      fechaActual_local = obtenerFechaActualSistema(true);
      fechaActual_local = mc.convertirFormatoFechaDDMMAAAA(fechaActual_local);
      fechaSistema_local = Date.valueOf(fechaActual_local);
      fecha_local = Calendar.getInstance();
      fecha_local.setTimeInMillis(fechaSistema_local.getTime());
      fecha_local.add(5, 30);
      ultimoDia_local = fecha_local.getActualMaximum(5);
      
      fechaUltimoDia_local = String.valueOf(new Date(fecha_local.getTimeInMillis()));
      simpleDateFormat_local = new SimpleDateFormat("yyyy-MM-dd");
      fechaUltimoDia_local = simpleDateFormat_local.format(new Date(fecha_local.getTimeInMillis()));
      fechaUltimoDia_local = mc.obtenerSubCadena(fechaUltimoDia_local, 0, mc.obtenerUltimaPosicionSubCadena(fechaUltimoDia_local, String.valueOf('-')) + 1) + ultimoDia_local;
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fecha_local = null;
      fechaActual_local = null;
      fechaSistema_local = null;
      simpleDateFormat_local = null;
    } 
    
    return fechaUltimoDia_local;
  }
  public Date obtenerFechaInicioDeMes(Date pFecha) {
    Date fechaInicioDeMes_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaInicioDeMes_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.set(5, 1);
      fechaInicioDeMes_local = new Date(calendario_local.getTimeInMillis());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaInicioDeMes_local;
  }
  public Date obtenerFechaFinDeMes(Date pFecha) {
    Date fechaFinDeMes_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaFinDeMes_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.set(5, calendario_local.getActualMaximum(5));
      
      fechaFinDeMes_local = new Date(calendario_local.getTimeInMillis());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaFinDeMes_local;
  }
  public Date obtenerFechaInicioDeMesAnterior(Date pFecha) {
    Date fechaInicioDeMes_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaInicioDeMes_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(2, -1);
      fechaInicioDeMes_local = new Date(calendario_local.getTimeInMillis());
      fechaInicioDeMes_local = obtenerFechaInicioDeMes(fechaInicioDeMes_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaInicioDeMes_local;
  }
  public Date obtenerFechaFinDeMesAnterior(Date pFecha) {
    Date fechaFinDeMes_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaFinDeMes_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(2, -1);
      fechaFinDeMes_local = new Date(calendario_local.getTimeInMillis());
      fechaFinDeMes_local = obtenerFechaFinDeMes(fechaFinDeMes_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaFinDeMes_local;
  }
  public Date obtenerFechaInicioDeMesSiguiente(Date pFecha) {
    Date fechaInicioDeMes_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaInicioDeMes_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(2, 1);
      fechaInicioDeMes_local = new Date(calendario_local.getTimeInMillis());
      fechaInicioDeMes_local = obtenerFechaInicioDeMes(fechaInicioDeMes_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaInicioDeMes_local;
  }
  public Date obtenerFechaFinDeMesSiguiente(Date pFecha) {
    Date fechaFinDeMes_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaFinDeMes_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(2, 1);
      fechaFinDeMes_local = new Date(calendario_local.getTimeInMillis());
      fechaFinDeMes_local = obtenerFechaFinDeMes(fechaFinDeMes_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaFinDeMes_local;
  }
  public Date obtenerFechaInicioDeAno(Date pFecha) {
    Date fechaInicioDeAno_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaInicioDeAno_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.set(5, 1);
      calendario_local.set(2, 0);
      fechaInicioDeAno_local = new Date(calendario_local.getTimeInMillis());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaInicioDeAno_local;
  }
  public Date obtenerFechaFinDeAno(Date pFecha) {
    Date fechaFinDeAno_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaFinDeAno_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.set(5, 31);
      calendario_local.set(2, 11);
      fechaFinDeAno_local = new Date(calendario_local.getTimeInMillis());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaFinDeAno_local;
  }
  public Date obtenerFechaInicioDeAnoAnterior(Date pFecha) {
    Date fechaInicioDeAno_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaInicioDeAno_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(1, -1);
      fechaInicioDeAno_local = new Date(calendario_local.getTimeInMillis());
      fechaInicioDeAno_local = obtenerFechaInicioDeAno(fechaInicioDeAno_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaInicioDeAno_local;
  }
  public Date obtenerFechaFinDeAnoAnterior(Date pFecha) {
    Date fechaFinDeAno_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaFinDeAno_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(1, -1);
      fechaFinDeAno_local = new Date(calendario_local.getTimeInMillis());
      fechaFinDeAno_local = obtenerFechaFinDeAno(fechaFinDeAno_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaFinDeAno_local;
  }
  public Date obtenerFechaInicioDeAnoSiguiente(Date pFecha) {
    Date fechaInicioDeAno_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaInicioDeAno_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(1, 1);
      fechaInicioDeAno_local = new Date(calendario_local.getTimeInMillis());
      fechaInicioDeAno_local = obtenerFechaInicioDeAno(fechaInicioDeAno_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaInicioDeAno_local;
  }
  public Date obtenerFechaFinDeAnoSiguiente(Date pFecha) {
    Date fechaFinDeAno_local = null;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaFinDeAno_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      calendario_local.add(1, 1);
      fechaFinDeAno_local = new Date(calendario_local.getTimeInMillis());
      fechaFinDeAno_local = obtenerFechaFinDeAno(fechaFinDeAno_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return fechaFinDeAno_local;
  }
  public int obtenerNumeroMesFecha(Date pFecha) {
    int numeroMesFecha_local = 0;
    Calendar calendario_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return numeroMesFecha_local;
    }
    
    try {
      calendario_local = Calendar.getInstance();
      calendario_local.setTimeInMillis(pFecha.getTime());
      numeroMesFecha_local = calendario_local.get(2);
      numeroMesFecha_local++;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      calendario_local = null;
    } 
    
    return numeroMesFecha_local;
  }
  public int obtenerNumeroMesActual() {
    int numeroMesActual_local = 0;
    
    try { return numeroMesActual_local; } catch (Exception excepcion_local) { return numeroMesActual_local; } finally { Exception exception = null; }
  }
  public Date sumarDiasFecha(Date pFecha, int pNumeroDias) {
    Calendar fecha_local = null;
    Date sumaFecha_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return sumaFecha_local;
    }
    
    try {
      fecha_local = Calendar.getInstance();
      fecha_local.setTimeInMillis(pFecha.getTime());
      fecha_local.add(5, pNumeroDias);
      sumaFecha_local = new Date(fecha_local.getTimeInMillis());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fecha_local = null;
    } 
    return sumaFecha_local;
  }
  public int restarFechas(Date pFechaInicial, Date pFechaFinal) {
    int fecha_local = 0;
    
    if (pFechaInicial == null) {
      return fecha_local;
    }
    if (pFechaFinal == null) {
      return fecha_local;
    }
    
    try {
      fecha_local = (int)((pFechaInicial.getTime() - pFechaFinal.getTime()) / 86400000L);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return fecha_local;
  }
  public Date restarDiasFecha(Date pFecha, int pNumeroDias) {
    Calendar fecha_local = null;
    Date restaFecha_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return restaFecha_local;
    }
    
    try {
      fecha_local = Calendar.getInstance();
      fecha_local.setTimeInMillis(pFecha.getTime());
      fecha_local.add(5, -pNumeroDias);
      restaFecha_local = new Date(fecha_local.getTimeInMillis());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fecha_local = null;
    } 
    
    return restaFecha_local;
  }
  public int obtenerNumeroInternoFecha(String pFecha) {
    int numeroInternoFecha_local = 0;
    Date date_local = null;
    Date dateInicial_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return numeroInternoFecha_local;
    }
    
    try {
      if (mc.verificarFormatoFecha(pFecha)) {
        date_local = Date.valueOf(pFecha);
        dateInicial_local = Date.valueOf("1900-01-01");
        numeroInternoFecha_local = restarFechas(date_local, dateInicial_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      date_local = null;
    } 
    
    return numeroInternoFecha_local;
  }
  public static void main(String[] args) {}
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorFechas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */