package com.sisnet.version;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version40
  extends AdministradorBaseDatos
{
  public Version40(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlActualizarCamposVersion40() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaCampo("fldfechacambiocontrasena", "USUARIO")) {
        
        listaCadenas_local.adicionar("alter table USUARIO add fldfechacambiocontrasena date ");
        
        listaCadenas_local.adicionar("update USUARIO set fldfechacambiocontrasena = '" + ManejadorFechas.getManejadorFechas().obtenerFechaActualSistema(true) + "' " + " where " + "fldidtipousuario" + " = " + 'I');
      } 
      
      if (!verificarExistenciaCampo("fldasignacionadministrador", "USUARIO"))
      {
        listaCadenas_local.adicionar("alter table USUARIO add fldasignacionadministrador bool default false ");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion40() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlActualizarCamposVersion40();
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
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version40.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */