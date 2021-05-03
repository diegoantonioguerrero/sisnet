package com.sisnet.aplicacion.manejadores;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.sql.ResultSet;
public class ManejadorResultadoConsultaSQL
{
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
  }
  private ManejadorConsultaSQL obtenerManejadorConsultaSisnet(String pConsultaSQL) {
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO) {
      return manejadorConsultaSQL_local;
    }
    
    try {
      manejadorConsultaSQL_local = getAdministradorBaseDatosSisnet().obtenerManejadorConsulta(pConsultaSQL);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return manejadorConsultaSQL_local;
  }
  public ManejadorConsultaSQL obtenerManejadorConsultaAplicacion(String pConsultaSQL) {
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO) {
      return manejadorConsultaSQL_local;
    }
    
    try {
      manejadorConsultaSQL_local = getAdministradorBaseDatosAplicacion().obtenerManejadorConsulta(pConsultaSQL);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return manejadorConsultaSQL_local;
  }
  public ResultSet obtenerResultadoConsultaSisnet(String pConsultaSQL) {
    ResultSet resultadoConsulta_local = null;
    
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO) {
      return resultadoConsulta_local;
    }
    
    try {
      resultadoConsulta_local = obtenerManejadorConsultaSisnet(pConsultaSQL).getResultSet();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return resultadoConsulta_local;
  }
  public ResultSet obtenerResultadoConsultaAplicacion(String pConsultaSQL) {
    ResultSet resultadoConsultaGeneral_local = null;
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO) {
      return resultadoConsultaGeneral_local;
    }
    
    try {
      resultadoConsultaGeneral_local = obtenerManejadorConsultaAplicacion(pConsultaSQL).getResultSet();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return resultadoConsultaGeneral_local;
  }
  public ListaCampo obtenerListadoCamposConsultaSQL(String pConsultaSQL, boolean pUtilizarAdministradorBaseDatosSisnet) {
    ListaCampo listadoCampos_local = null;
    
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO) {
      return listadoCampos_local;
    }
    
    try {
      if (pUtilizarAdministradorBaseDatosSisnet) {
        listadoCampos_local = obtenerManejadorConsultaSisnet(pConsultaSQL).obtenerListaCampos();
      } else {
        listadoCampos_local = obtenerManejadorConsultaAplicacion(pConsultaSQL).obtenerListaCampos();
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listadoCampos_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorResultadoConsultaSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */