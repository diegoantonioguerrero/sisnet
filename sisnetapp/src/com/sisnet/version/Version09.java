package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version09
  extends AdministradorBaseDatos
{
  public Version09(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion09() {
    ListaCadenas listaCadenas_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      
      if (!verificarExistenciaCampo("fldincluiropcionconsulta", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldincluiropcionconsulta bool;");
        listaCadenas_local.adicionar("update campo set fldincluiropcionconsulta = true;");
      } 
      
      listaCadenas_local.adicionar("delete from usuario where fldidtipousuario is null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion09() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion09();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version09.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */