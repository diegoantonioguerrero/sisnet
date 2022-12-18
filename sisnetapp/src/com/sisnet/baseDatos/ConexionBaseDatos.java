package com.sisnet.baseDatos;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.sql.Connection;
import java.sql.DriverManager;
public class ConexionBaseDatos
{
  private String aDriverJdbc;
  private String aUbicacionServidor;
  private String aPuertoConexion;
  private String aNombreBaseDatos;
  private String aUsuario;
  private String aContrasena;
  private int aErrorConexion;
  private Connection aConexion;
  public ConexionBaseDatos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    setDriverJdbc(pObjetoConexionBaseDatos.getDriverJdbc());
    setUbicacionServidor(pObjetoConexionBaseDatos.getUbicacionServidor());
    setPuertoConexion(pObjetoConexionBaseDatos.getPuertoConexion());
    setNombreBaseDatos(pObjetoConexionBaseDatos.getNombreBaseDatos());
    setUsuario(pObjetoConexionBaseDatos.getUsuario());
    setContrasena(pObjetoConexionBaseDatos.getContrasena());
    setErrorConexion(0);
    setConexion(null);
  }
  public String getDriverJdbc() {
    return this.aDriverJdbc;
  }
  public void setDriverJdbc(String pDriverJdbc) {
    this.aDriverJdbc = pDriverJdbc;
  }
  public String getUbicacionServidor() {
    return this.aUbicacionServidor;
  }
  public void setUbicacionServidor(String pUbicacionServidor) {
    this.aUbicacionServidor = pUbicacionServidor;
  }
  public String getPuertoConexion() {
    return this.aPuertoConexion;
  }
  public void setPuertoConexion(String pPuertoConexion) {
    this.aPuertoConexion = pPuertoConexion;
  }
  public String getNombreBaseDatos() {
    return this.aNombreBaseDatos;
  }
  public void setNombreBaseDatos(String pNombreBaseDatos) {
    this.aNombreBaseDatos = pNombreBaseDatos;
  }
  public String getUsuario() {
    return this.aUsuario;
  }
  public void setUsuario(String pUsuario) {
    this.aUsuario = pUsuario;
  }
  public String getContrasena() {
    return this.aContrasena;
  }
  public void setContrasena(String pContrasena) {
    this.aContrasena = pContrasena;
  }
  public int getErrorConexion() {
    return this.aErrorConexion;
  }
  public void setErrorConexion(int pErrorConexion) {
    this.aErrorConexion = pErrorConexion;
  }
  public Connection getConexion() {
    return this.aConexion;
  }
  public void setConexion(Connection pConexion) {
    this.aConexion = pConexion;
  }
  public boolean conectarBaseDatos() {
    String url_local = null;
    try {
      Class.forName(getDriverJdbc());
      url_local = "jdbc:postgresql://" + getUbicacionServidor() + ":" + getPuertoConexion() + '/' + getNombreBaseDatos();
      //url_local = "jdbc:postgresql://127.0.0.1:" + getPuertoConexion() + '/' + getNombreBaseDatos();
      //url_local = "jdbc:postgresql://" + getUbicacionServidor() + '/' + getNombreBaseDatos();

      setConexion(DriverManager.getConnection(url_local, getUsuario(), getContrasena()));
      return true;
    }
    catch (Exception e) {
    	System.err.println("Error connecting: " + url_local );
      e.printStackTrace();
      setErrorConexion(6);
      return false;
    } finally {
      
      url_local = null;
    } 
  }
  public void cerrarConeccionBaseDatos() {
    try {
      getConexion().close();
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
      setErrorConexion(8);
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\ConexionBaseDatos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */