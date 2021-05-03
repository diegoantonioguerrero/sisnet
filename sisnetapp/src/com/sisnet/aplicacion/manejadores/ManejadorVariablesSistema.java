package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.VariableSistema;
public class ManejadorVariablesSistema
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private MotorAplicacion aMotorAplicacion;
  private Usuario aUsuarioActual;
  private String aIdSesion;
  private String aNombreEvento;
  public ManejadorVariablesSistema() {
    setAdministradorBaseDatosAplicacion(null);
    setMotorAplicacion(null);
    setUsuarioActual(null);
    setIdSesion(null);
    setNombreEvento("");
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  public Usuario getUsuarioActual() {
    return this.aUsuarioActual;
  }
  public void setUsuarioActual(Usuario pUsuarioActual) {
    this.aUsuarioActual = pUsuarioActual;
  }
  public String getIdSesion() {
    return this.aIdSesion;
  }
  public void setIdSesion(String pIdSesion) {
    this.aIdSesion = pIdSesion;
  }
  public String getNombreEvento() {
    return this.aNombreEvento;
  }
  public void setNombreEvento(String pNombreEvento) {
    this.aNombreEvento = pNombreEvento;
  }
  private VariableSistema obtenerValorEtiquetaAplicacionGeneral(String pEtiquetaSistema) {
    VariableSistema variableSistema_local = null;
    String seudonimoCampo_local = null;
    Aplicacion aplicacionGeneral_local = null;
    Campo campo_local = null;
    Object valorCampo_local = null;
    
    if (pEtiquetaSistema == ConstantesGeneral.VALOR_NULO) {
      return variableSistema_local;
    }
    
    try {
      if (getAdministradorBaseDatosAplicacion().verificarExistenciaTabla("GENERAL")) {
        aplicacionGeneral_local = getMotorAplicacion().obtenerAplicacionPorTitulo("GENERAL");
        
        seudonimoCampo_local = mc.obtenerSubCadena(pEtiquetaSistema, mc.obtenerPosicionSubCadena(pEtiquetaSistema, String.valueOf('%')) + 1, mc.obtenerLongitudCadena(pEtiquetaSistema));
        
        campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimoCampo_local, aplicacionGeneral_local.getIdAplicacion());
        if (campo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campo_local, ap.conformarNombreCampoLlavePrimaria(aplicacionGeneral_local.getNombreAplicacion()), 1);
          
          if (valorCampo_local == ConstantesGeneral.VALOR_NULO) {
            valorCampo_local = "";
          }
          variableSistema_local = new VariableSistema(pEtiquetaSistema, campo_local.getFormatoCampo().getTipoDato(), valorCampo_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      valorCampo_local = null;
      seudonimoCampo_local = null;
      aplicacionGeneral_local = null;
    } 
    
    return variableSistema_local;
  }
  public VariableSistema obtenerValorVariableSistema(String pEtiquetaSistema) {
    VariableSistema variableSistema_local = null;
    
    if (pEtiquetaSistema == ConstantesGeneral.VALOR_NULO) {
      return variableSistema_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%EVENTO")) {
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "T", getNombreEvento());
        
        return variableSistema_local;
      } 
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%USUNOM") || mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%USERNAME")) {
        
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "T", getUsuarioActual().getNombreCompletoUsuario());
        
        return variableSistema_local;
      } 
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%USUCTA") || mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%USERACCOUNT")) {
        
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "T", getUsuarioActual().getNombreUsuario());
        
        return variableSistema_local;
      } 
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%USUTIP") || mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%USERTYPE")) {
        
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "T", getUsuarioActual().getTipoUsuario().getNombreTipoUsuario());
        
        return variableSistema_local;
      } 
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%FECACT") || mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%DATEACTUAL")) {
        
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "F", mf.obtenerFechaActualSistema(true));
        
        return variableSistema_local;
      } 
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%HORACT") || mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%HOURACTUAL")) {
        
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "H", ap.obtenerHoraActualSistema());
        
        return variableSistema_local;
      } 
      if (mc.verificarExistenciaSubCadena(pEtiquetaSistema, "%NUMSES")) {
        variableSistema_local = new VariableSistema(pEtiquetaSistema, "T", getIdSesion());
        
        return variableSistema_local;
      } 
      variableSistema_local = obtenerValorEtiquetaAplicacionGeneral(pEtiquetaSistema);
      if (variableSistema_local == ConstantesGeneral.VALOR_NULO && getUsuarioActual().getListaVariablesSistema() != ConstantesGeneral.VALOR_NULO)
      {
        variableSistema_local = getUsuarioActual().getListaVariablesSistema().obtenerVariableSistema(pEtiquetaSistema);
      }
      if (variableSistema_local == ConstantesGeneral.VALOR_NULO) {
        variableSistema_local = new VariableSistema("", "T", pEtiquetaSistema);
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {}
    
    return variableSistema_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorVariablesSistema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */