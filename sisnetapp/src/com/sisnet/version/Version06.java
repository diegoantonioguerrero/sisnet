package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version06
  extends AdministradorBaseDatos
{
  public Version06(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion06() {
    ListaCadenas listaCadenas_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      
      if (!verificarExistenciaCampo("fldformatocampoorigenuno", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldformatocampoorigenuno varchar(30);");
      }
      if (!verificarExistenciaCampo("fldformatocampoorigendos", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldformatocampoorigendos varchar(30);");
      }
      if (!verificarExistenciaCampo("fldformatocampocalculado", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldformatocampocalculado varchar(30);");
      }
      listaCadenas_local.adicionar("update campo set fldformatocampoorigenuno = '' where fldformatocampoorigenuno is null;");
      listaCadenas_local.adicionar("update campo set fldformatocampoorigendos = '' where fldformatocampoorigendos is null;");
      listaCadenas_local.adicionar("update campo set fldformatocampocalculado = '' where fldformatocampocalculado is null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion06() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion06();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version06.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */