package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version32
  extends AdministradorBaseDatos
{
  public Version32(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlActualizarCamposVersion32() {
    ListaCadenas listaCadenas_local = null;
    int i = 0;
    
    try {
      listaCadenas_local = new ListaCadenas();
      for (i = 1; i <= 15; i++) {
        if (!verificarExistenciaCampo("fldcontrol" + i, "APLICACION")) {
          listaCadenas_local.adicionar("alter table aplicacion add fldcontrol" + i + " bool default false");
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion32() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlActualizarCamposVersion32();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */