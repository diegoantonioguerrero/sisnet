package com.sisnet.utilidades;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import java.sql.Date;
import java.text.SimpleDateFormat;
public class ConversorFormatoFecha
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private SimpleDateFormat aSimpleDateFormat;
  private String aFormatoFecha;
  private String aFechaConvertir;
  private String aFecha;
  public ConversorFormatoFecha() {
    setFecha("");
    setFechaConvertir("");
    setFormatoFecha("");
    setSimpleDateFormat(null);
  }
  public SimpleDateFormat getSimpleDateFormat() {
    return this.aSimpleDateFormat;
  }
  public void setSimpleDateFormat(SimpleDateFormat pSimpleDateFormat) {
    this.aSimpleDateFormat = pSimpleDateFormat;
  }
  public String getFormatoFecha() {
    return this.aFormatoFecha;
  }
  public void setFormatoFecha(String pFormatoFecha) {
    this.aFormatoFecha = pFormatoFecha;
  }
  public String getFechaConvertir() {
    return this.aFechaConvertir;
  }
  public void setFechaConvertir(String pFechaConvertir) {
    this.aFechaConvertir = pFechaConvertir;
  }
  public String getFecha() {
    return this.aFecha;
  }
  public void setFecha(String pFecha) {
    this.aFecha = pFecha;
  }
  public String obtenerFechaConFormato(String pFormatoFecha, String pFechaConvertir) {
    String fechaConFormato_local = "";
    
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return fechaConFormato_local;
    }
    if (pFechaConvertir == ConstantesGeneral.VALOR_NULO) {
      return fechaConFormato_local;
    }
    
    try {
      fechaConFormato_local = pFechaConvertir;
      if (!mc.esCadenaVacia(pFormatoFecha)) {
        setFechaConvertir(pFechaConvertir);
        setFormatoFecha(obtenerFormatoFecha(pFormatoFecha));
        setSimpleDateFormat(new SimpleDateFormat(getFormatoFecha()));
        setFecha(getSimpleDateFormat().format(Date.valueOf(getFechaConvertir())));
        setSimpleDateFormat(null);
        fechaConFormato_local = getFecha();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fechaConFormato_local;
  }
  private String obtenerFormatoFecha(String pFormatoFecha) {
    String formatoFecha_local = "MMMMM dd 'de' yyyy";
    
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return formatoFecha_local;
    }
    try {
      if (mc.sonCadenasIguales(pFormatoFecha, "AMD")) {
        formatoFecha_local = "yyyy/MM/dd";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DMA")) {
        formatoFecha_local = "dd/MM/yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DMA1SPA")) {
        formatoFecha_local = "dd 'de' MMMMM 'de' yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DMA2SPA")) {
        formatoFecha_local = "dd 'd\u00edas del mes de' MMMMM 'de' yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DMA3SPA")) {
        formatoFecha_local = "EEEEE, dd 'de' MMMMM 'de' yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "MDA1SPA")) {
        formatoFecha_local = "MMMMM dd 'de' yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "MDA2SPA")) {
        formatoFecha_local = "MMM dd/yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "MDA3SPA")) {
        formatoFecha_local = "MMM dd/yy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "AA")) {
        formatoFecha_local = "yyyy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "AAC")) {
        formatoFecha_local = "yy";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "MM")) {
        formatoFecha_local = "MM";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "MMLSPA")) {
        formatoFecha_local = "MMMMM";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "MMCSPA")) {
        formatoFecha_local = "MMM";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DD")) {
        formatoFecha_local = "dd";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DDLSPA")) {
        formatoFecha_local = "EEEEE";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DDCSPA")) {
        formatoFecha_local = "EEE";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "AMD0")) {
        formatoFecha_local = "yyyyMMdd";
      }
      if (mc.sonCadenasIguales(pFormatoFecha, "DMA0")) {
        formatoFecha_local = "ddMMyyyy";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return formatoFecha_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisne\\utilidades\ConversorFormatoFecha.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */