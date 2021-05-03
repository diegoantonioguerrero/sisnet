package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version23
  extends AdministradorBaseDatos
{
  public Version23(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion23() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaCampo("fldborrarvalornohabilitado", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldborrarvalornohabilitado bool");
        listaCadenas_local.adicionar("update campo set fldborrarvalornohabilitado = true;");
      } 
      if (!verificarExistenciaCampo("fldfiltradoregistrosenlazados", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldfiltradoregistrosenlazados int");
        listaCadenas_local.adicionar("update campo set fldfiltradoregistrosenlazados = 1 where fldenlazado <> 0");
        
        listaCadenas_local.adicionar("update campo set fldfiltradoregistrosenlazados = -1 where fldenlazado = 0");
      } 
      
      if (!verificarExistenciaCampo("fldcampodestinofiltrado", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldcampodestinofiltrado int");
        listaCadenas_local.adicionar("update campo set fldcampodestinofiltrado = -1;");
      } 
      if (!verificarExistenciaCampo("fldcampoorigenfiltrado", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldcampoorigenfiltrado int");
        listaCadenas_local.adicionar("update campo set fldcampoorigenfiltrado = -1;");
      } 
      if (!verificarExistenciaCampo("fldvalorfiltrado", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldvalorfiltrado varchar(100)");
      }
      if (!verificarExistenciaCampo("fldrecargarpantalla", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldrecargarpantalla bool");
        listaCadenas_local.adicionar("update campo set fldrecargarpantalla = false;");
      } 
      if (!verificarExistenciaCampo("fldmostrardetalle", "GRUPOINFORMACION")) {
        listaCadenas_local.adicionar("alter table grupoinformacion add fldmostrardetalle bool");
        listaCadenas_local.adicionar("update grupoinformacion set fldmostrardetalle = true;");
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion23() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion23();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */