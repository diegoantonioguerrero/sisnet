package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version27
  extends AdministradorBaseDatos
{
  private static final String const_TablaPersona = "persona";
  private static final String const_TablaLugar = "lugar";
  private static final String const_TablaEmpresa = "empresa";
  private static final String const_CampoIdPersona = "fldidpersona";
  public Version27(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlBorrarRestricciones() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (verificarExistenciaLlaveForanea("USUARIO", "persona")) {
        listaCadenas_local.adicionar("alter table usuario drop constraint fkusuariopersona cascade");
      }
      if (verificarExistenciaLlaveForanea("persona", "empresa")) {
        listaCadenas_local.adicionar("alter table persona drop constraint fkpersonaempresa cascade");
      }
      if (verificarExistenciaLlaveForanea("persona", "lugar")) {
        listaCadenas_local.adicionar("alter table persona drop constraint fkpersonalugar cascade");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  private void borrarRestricciones() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCadenas_local = obtenerListadoInstruccionesSqlBorrarRestricciones();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        consultaSql_local = (String)iterador_local.next();
        ejecutarInstruccionSQL(consultaSql_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      consultaSql_local = null;
      listaCadenas_local = null;
    } 
  }
  private ListaCadenas obtenerListadoInstruccionesSqlBorrarTablas() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (verificarExistenciaTabla("persona")) {
        listaCadenas_local.adicionar("drop table persona cascade");
      }
      if (verificarExistenciaTabla("empresa")) {
        listaCadenas_local.adicionar("drop table empresa cascade");
      }
      if (verificarExistenciaTabla("lugar")) {
        listaCadenas_local.adicionar("drop table lugar cascade");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  private void borrarTablas() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCadenas_local = obtenerListadoInstruccionesSqlBorrarTablas();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        consultaSql_local = (String)iterador_local.next();
        ejecutarInstruccionSQL(consultaSql_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      consultaSql_local = null;
      listaCadenas_local = null;
    } 
  }
  private ListaCadenas obtenerListadoInstruccionesSqlActualizarCampos() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (verificarExistenciaCampo("fldidpersona", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario drop column fldidpersona cascade");
      }
      if (!verificarExistenciaCampo("fldnombrecompletousuario", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldnombrecompletousuario varchar(60)");
      }
      if (!verificarExistenciaCampo("fldtamanomaximoarchivos", "APLICACION")) {
        listaCadenas_local.adicionar("alter table aplicacion add fldtamanomaximoarchivos integer default 0");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  private void actualizarCampos() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCadenas_local = obtenerListadoInstruccionesSqlActualizarCampos();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        consultaSql_local = (String)iterador_local.next();
        ejecutarInstruccionSQL(consultaSql_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      consultaSql_local = null;
      listaCadenas_local = null;
    } 
  }
  public void actualizarBaseDatosSisnetVersion27() {
    try {
      actualizarRegistroConfiguracion();
      borrarRestricciones();
      borrarTablas();
      actualizarCampos();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version27.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */