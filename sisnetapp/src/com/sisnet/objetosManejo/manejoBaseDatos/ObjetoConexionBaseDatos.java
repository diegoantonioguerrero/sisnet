package com.sisnet.objetosManejo.manejoBaseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
public class ObjetoConexionBaseDatos
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aDriverJdbc;
  private String aUbicacionServidor;
  private String aPuertoConexion;
  private String aNombreBaseDatos;
  private String aUsuario;
  private String aContrasena;
  public ObjetoConexionBaseDatos(String pDriverJdbc, String pUbicacionServidor, String pPuertoConexion, String pNombreBaseDatos, String pUsuario, String pContrasena) {
    setDriverJdbc(pDriverJdbc);
    setUbicacionServidor(pUbicacionServidor);
    setPuertoConexion(pPuertoConexion);
    setNombreBaseDatos(mc.convertirAMinusculas(pNombreBaseDatos));
    setUsuario(pUsuario);
    setContrasena(pContrasena);
  }
  public String getDriverJdbc() {
    return this.aDriverJdbc;
  }
  public String getUbicacionServidor() {
    return this.aUbicacionServidor;
  }
  public String getPuertoConexion() {
    return this.aPuertoConexion;
  }
  public String getNombreBaseDatos() {
    return this.aNombreBaseDatos;
  }
  public String getUsuario() {
    return this.aUsuario;
  }
  public String getContrasena() {
    return this.aContrasena;
  }
  public void setDriverJdbc(String pDriverJdbc) {
    this.aDriverJdbc = pDriverJdbc;
  }
  public void setUbicacionServidor(String pUbicacionServidor) {
    this.aUbicacionServidor = pUbicacionServidor;
  }
  public void setPuertoConexion(String pPuertoConexion) {
    this.aPuertoConexion = pPuertoConexion;
  }
  public void setNombreBaseDatos(String pNombreBaseDatos) {
    this.aNombreBaseDatos = pNombreBaseDatos;
  }
  public void setUsuario(String pUsuario) {
    this.aUsuario = pUsuario;
  }
  public void setContrasena(String pContrasena) {
    this.aContrasena = pContrasena;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoBaseDatos\ObjetoConexionBaseDatos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */