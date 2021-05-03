package com.sisnet.baseDatos.sisnet.usuario;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.usuario.TipoUsuario;
import com.sisnet.objetosManejo.listas.ListaVariablesSistema;
import java.io.Serializable;
import java.sql.Date;
public class Usuario
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private int aIdUsuario;
  private String aNombreUsuario;
  private String aContrasena;
  private String aNombreCompletoUsuario;
  private String aTipoLicencia;
  private TipoUsuario aTipoUsuario;
  private Date aFechaVencimiento;
  private int aDiasVigenciaContrasena;
  private int aContrasenasFallidasPermitidas;
  private Date aFechaUltimaContrasenaFallida;
  private int aCantidadContrasenasFallidas;
  private int aTiempoSesion;
  private int aTipoBloqueo;
  private boolean aAsignacionAdministrador;
  private ListaVariablesSistema aListaVariablesSistema;
  public Usuario() {
    setIdUsuario(-1);
    setNombreUsuario("");
    setContrasena("");
    setNombreCompletoUsuario("");
    setTipoLicencia("");
    setTipoUsuario(null);
    setFechaVencimiento(null);
    setDiasVigenciaContrasena(-1);
    setContrasenasFallidasPermitidas(-1);
    setFechaUltimaContrasenaFallida(null);
    setCantidadContrasenasFallidas(-1);
    setTiempoSesion(-1);
    setTipoBloqueo(-1);
    setListaVariablesSistema(null);
  }
  public int getIdUsuario() {
    return this.aIdUsuario;
  }
  public void setIdUsuario(int pIdUsuario) {
    this.aIdUsuario = pIdUsuario;
  }
  public String getNombreUsuario() {
    return this.aNombreUsuario;
  }
  public void setNombreUsuario(String pNombreUsuario) {
    this.aNombreUsuario = mc.convertirAMayusculas(mc.convertirCadenaFormatoNombre(pNombreUsuario));
  }
  public String getContrasena() {
    return this.aContrasena;
  }
  public void setContrasena(String pContrasena) {
    this.aContrasena = mc.convertirAMayusculas(pContrasena);
  }
  public String getNombreCompletoUsuario() {
    return this.aNombreCompletoUsuario;
  }
  public void setNombreCompletoUsuario(String pNombreCompletoUsuario) {
    this.aNombreCompletoUsuario = pNombreCompletoUsuario;
  }
  public String getTipoLicencia() {
    return this.aTipoLicencia;
  }
  public void setTipoLicencia(String pTipoLicencia) {
    this.aTipoLicencia = mc.convertirAMayusculas(pTipoLicencia);
  }
  public TipoUsuario getTipoUsuario() {
    return this.aTipoUsuario;
  }
  public void setTipoUsuario(TipoUsuario pTipoUsuario) {
    this.aTipoUsuario = pTipoUsuario;
  }
  public Date getFechaVencimiento() {
    return this.aFechaVencimiento;
  }
  public void setFechaVencimiento(Date pFechaVencimiento) {
    this.aFechaVencimiento = pFechaVencimiento;
  }
  public int getDiasVigenciaContrasena() {
    return this.aDiasVigenciaContrasena;
  }
  public void setDiasVigenciaContrasena(int pDiasVigenciaContrasena) {
    this.aDiasVigenciaContrasena = pDiasVigenciaContrasena;
  }
  public int getContrasenasFallidasPermitidas() {
    return this.aContrasenasFallidasPermitidas;
  }
  public void setContrasenasFallidasPermitidas(int pContrasenasFallidasPermitidas) {
    this.aContrasenasFallidasPermitidas = pContrasenasFallidasPermitidas;
  }
  public Date getFechaUltimaContrasenaFallida() {
    return this.aFechaUltimaContrasenaFallida;
  }
  public void setFechaUltimaContrasenaFallida(Date pFechaUltimaContrasenaFallida) {
    this.aFechaUltimaContrasenaFallida = pFechaUltimaContrasenaFallida;
  }
  public int getCantidadContrasenasFallidas() {
    return this.aCantidadContrasenasFallidas;
  }
  public void setCantidadContrasenasFallidas(int pCantidadContrasenasFallidas) {
    this.aCantidadContrasenasFallidas = pCantidadContrasenasFallidas;
  }
  public int getTiempoSesion() {
    return this.aTiempoSesion;
  }
  public void setTiempoSesion(int pTiempoSesion) {
    this.aTiempoSesion = pTiempoSesion;
  }
  public int getTipoBloqueo() {
    return this.aTipoBloqueo;
  }
  public void setTipoBloqueo(int pTipoBloqueo) {
    this.aTipoBloqueo = pTipoBloqueo;
  }
  public ListaVariablesSistema getListaVariablesSistema() {
    return this.aListaVariablesSistema;
  }
  public void setListaVariablesSistema(ListaVariablesSistema pListaVariablesSistema) {
    this.aListaVariablesSistema = pListaVariablesSistema;
  }
  public boolean esAsignacionAdministrador() {
    return this.aAsignacionAdministrador;
  }
  public void setAsignacionAdministrador(boolean aAsignacionAdministrador) {
    this.aAsignacionAdministrador = aAsignacionAdministrador;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisne\\usuario\Usuario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */