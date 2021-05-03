package com.sisnet.utilidades;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import java.security.MessageDigest;
public class Encriptor
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aCodigoSeguridad = "1587436897";
  private String aCadenaEncriptar;
  private boolean aUsarCodigo;
  public Encriptor(String pCadenaEncriptar, boolean pUsarCodigo) {
    setCadenaEncriptar(pCadenaEncriptar);
    setUsarCodigo(pUsarCodigo);
  }
  public String getCodigoSeguridad() {
    return this.aCodigoSeguridad;
  }
  public void setCodigoSeguridad(String pCodigoSeguridad) {
    this.aCodigoSeguridad = pCodigoSeguridad;
  }
  public String getCadenaEncriptar() {
    return this.aCadenaEncriptar;
  }
  public void setCadenaEncriptar(String pCadenaEncriptar) {
    this.aCadenaEncriptar = pCadenaEncriptar;
  }
  public boolean esUsarCodigo() {
    return this.aUsarCodigo;
  }
  public void setUsarCodigo(boolean pUsarCodigo) {
    this.aUsarCodigo = pUsarCodigo;
  }
  public String encriptarCadena() {
    String encriptarCadena_local = "";
    String cadenaConcatenada_local = null;
    int conversion_local = -1;
    int longitud_local = -1;
    StringBuffer encriptacion_local = null;
    MessageDigest md5_local = null;
    byte[] byte_local = null;
    
    try {
      if (esUsarCodigo()) {
        cadenaConcatenada_local = mc.concatenarCadena(getCadenaEncriptar(), getCodigoSeguridad());
      } else {
        cadenaConcatenada_local = getCadenaEncriptar();
      } 
      md5_local = MessageDigest.getInstance("MD5");
      byte_local = md5_local.digest(cadenaConcatenada_local.getBytes());
      longitud_local = byte_local.length;
      encriptacion_local = new StringBuffer(longitud_local);
      
      for (int i = 0; i < longitud_local; i++) {
        conversion_local = byte_local[i] & 0xFF;
        if (conversion_local < 16) {
          encriptarCadena_local = encriptacion_local.append('0' + Integer.toHexString(conversion_local)).toString();
        } else {
          
          encriptarCadena_local = encriptacion_local.append(Integer.toHexString(conversion_local)).toString();
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      encriptacion_local = null;
      md5_local = null;
      byte_local = null;
      cadenaConcatenada_local = null;
    } 
    return encriptarCadena_local;
  }
  public static void main(String[] args) {
    com.sisnet.utilidades.Encriptor encriptor = new com.sisnet.utilidades.Encriptor("", false);
    System.out.println(encriptor.encriptarCadena());
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisne\\utilidades\Encriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */