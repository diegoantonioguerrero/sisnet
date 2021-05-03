package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version05
  extends AdministradorBaseDatos
{
  public Version05(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion05() {
    ListaCadenas listaCadenas_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      
      listaCadenas_local.adicionar("alter table campo alter column fldtipodato type varchar(3)");
      
      if (!verificarExistenciaCampo("fldestabla", "APLICACION")) {
        listaCadenas_local.adicionar("alter table aplicacion add fldestabla bool;");
      }
      if (!verificarExistenciaCampo("fldidaplicacionconsulta", "APLICACION")) {
        listaCadenas_local.adicionar("alter table aplicacion add fldidaplicacionconsulta integer;");
      }
      if (!verificarExistenciaCampo("fldesrecalculable", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldesrecalculable bool;");
      }
      if (!verificarExistenciaCampo("fldidcampovalor", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldidcampovalor integer;");
      }
      if (!verificarExistenciaCampo("fldidcampoorigenuno", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldidcampoorigenuno integer;");
      }
      if (!verificarExistenciaCampo("fldidcampoorigendos", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldidcampoorigendos integer;");
      }
      
      listaCadenas_local.adicionar("update aplicacion set fldestabla = false where fldestabla is null;");
      listaCadenas_local.adicionar("update aplicacion set fldidaplicacionconsulta = -1 where fldidaplicacionconsulta is null;");
      listaCadenas_local.adicionar("update campo set fldesrecalculable = false where fldesrecalculable is null;");
      listaCadenas_local.adicionar("update campo set fldidcampovalor = -1 where fldidcampovalor is null;");
      listaCadenas_local.adicionar("update campo set fldidcampoorigenuno = -1 where fldidcampoorigenuno is null;");
      listaCadenas_local.adicionar("update campo set fldidcampoorigendos = -1 where fldidcampoorigendos is null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion05() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion05();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version05.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */