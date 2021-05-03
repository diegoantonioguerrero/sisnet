package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version16
  extends AdministradorBaseDatos
{
  public Version16(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion16() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaCampo("fldactualizarinformacionenlazada", "APLICACION")) {
        listaCadenas_local.adicionar("alter table aplicacion add fldactualizarinformacionenlazada bool");
      }
      if (!verificarExistenciaCampo("fldaplicacionesactualizar", "APLICACION")) {
        listaCadenas_local.adicionar("alter table aplicacion add fldaplicacionesactualizar varchar(10000)");
      }
      
      listaCadenas_local.adicionar("update aplicacion set fldactualizarinformacionenlazada = true where fldactualizarinformacionenlazada is null;");
      listaCadenas_local.adicionar("update aplicacion set fldaplicacionesactualizar = '' where fldaplicacionesactualizar is null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion16() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion16();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version16.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */