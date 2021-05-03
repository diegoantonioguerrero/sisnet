package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version02
  extends AdministradorBaseDatos
{
  public Version02(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  public void actualizarBaseDatosSisnet(String pNombreBaseDatos) {
    boolean baseDatosSisnet_local = false;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      baseDatosSisnet_local = verificarExistenciaBaseDatos(pNombreBaseDatos);
      if (baseDatosSisnet_local) {
        actualizarRegistroConfiguracion();
        actualizarTablasBaseDatosSisnetVersion2();
        actualizarRegistrosVersion2();
        crearTablasBaseDatosSisnetVersion2();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion02() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaCampo("fldseudonimo", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldseudonimo varchar(50);");
      }
      if (!verificarExistenciaCampo("fldtieneplantilla", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldtieneplantilla bool;");
      }
      if (!verificarExistenciaCampo("fldidaplicacionplantilla", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldidaplicacionplantilla integer;");
      }
      if (!verificarExistenciaCampo("fldopciondesconocido", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldopciondesconocido bool;");
      }
      if (!verificarExistenciaCampo("fldcampoenlacedepende", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldcampoenlacedepende integer;");
      }
      if (!verificarExistenciaCampo("fldcampoorigenenlace", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldcampoorigenenlace integer;");
      }
      if (!verificarExistenciaCampo("fldanchocolumna", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldanchocolumna integer;");
      }
      if (!verificarExistenciaCampo("fldtipohabilitacion", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldtipohabilitacion integer;");
      }
      if (!verificarExistenciaCampo("fldcampocalculado", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldcampocalculado integer;");
      }
      
      if (verificarExistenciaCampo("fldopcionescoja", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo drop column fldopcionescoja;");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCadenas_local;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlTablasSisnetVersion02() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaTabla("DEPENDIENTEHABILITACION")) {
        listaCadenas_local.adicionar("create table dependientehabilitacion ( fldiddependientehabilitacion integer not null );");
        listaCadenas_local.adicionar("alter table dependientehabilitacion add fldidcampo integer not null;");
        listaCadenas_local.adicionar("alter table dependientehabilitacion add fldidvalormaestro integer not null;");
        listaCadenas_local.adicionar("alter table dependientehabilitacion add fldhabilitacion boolean not null;");
        listaCadenas_local.adicionar("alter table dependientehabilitacion add constraint pkdependientehabilitacion primary key (fldiddependientehabilitacion);");
      } else {
        
        if (!verificarExistenciaCampo("fldidcampo", "DEPENDIENTEHABILITACION")) {
          listaCadenas_local.adicionar("alter table dependientehabilitacion add fldidcampo integer not null;");
        }
        if (!verificarExistenciaCampo("fldidvalormaestro", "DEPENDIENTEHABILITACION")) {
          listaCadenas_local.adicionar("alter table dependientehabilitacion add fldidvalormaestro integer not null;");
        }
        if (!verificarExistenciaCampo("fldhabilitacion", "DEPENDIENTEHABILITACION")) {
          listaCadenas_local.adicionar("alter table dependientehabilitacion add fldhabilitacion boolean not null;");
        }
        if (!verificarExistenciaCampo("fldiddependientehabilitacion", "DEPENDIENTEHABILITACION")) {
          listaCadenas_local.adicionar("alter table dependientehabilitacion add constraint pkdependientehabilitacion primary key (fldiddependientehabilitacion);");
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCadenas_local;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlActualizarRegistrosSisnetVersion02() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (verificarExistenciaCampo("fldseudonimo", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldseudonimo = fldnombrecampo::varchar(50) where fldseudonimo is null or fldseudonimo = '';");
      }
      if (verificarExistenciaCampo("fldtieneplantilla", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldtieneplantilla = false where fldtieneplantilla is null;");
      }
      if (verificarExistenciaCampo("fldidaplicacionplantilla", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldidaplicacionplantilla = -1 where fldidaplicacionplantilla is null;");
      }
      if (verificarExistenciaCampo("fldanchocolumna", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldanchocolumna = 200 where fldanchocolumna is null;");
      }
      if (verificarExistenciaCampo("fldnumerodecimales", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldnumerodecimales = 2 where fldtipodato = 'R' and fldnumerodecimales < 2;");
      }
      
      if (verificarExistenciaCampo("fldaplicacionenlace", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldaplicacionenlace = -1 where fldaplicacionenlace is null;");
      }
      if (verificarExistenciaCampo("fldopcionescoja", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldopcionescoja = false where fldopcionescoja is null;");
      }
      if (verificarExistenciaCampo("fldopciondesconocido", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldopciondesconocido = false where fldopciondesconocido is null;");
      }
      if (verificarExistenciaCampo("fldcampoenlacedepende", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldcampoenlacedepende = -1 where fldcampoenlacedepende is null;");
      }
      if (verificarExistenciaCampo("fldcampoorigenenlace", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldcampoorigenenlace = -1 where fldcampoorigenenlace is null;");
      }
      if (verificarExistenciaCampo("fldtipohabilitacion", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldtipohabilitacion = 1 where fldtipohabilitacion is null;");
      }
      
      if (verificarExistenciaCampo("fldcampocalculado", "CAMPO")) {
        listaCadenas_local.adicionar("update campo set fldcampocalculado = 1 where fldcampocalculado is null;");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCadenas_local;
  }
  private int actualizarTablasBaseDatosSisnetVersion2() {
    int tabla_local = 0;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      tabla_local = 0;
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion02();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        tabla_local = ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCadenas_local = null;
      iterador_local = null;
    } 
    
    return tabla_local;
  }
  private int crearTablasBaseDatosSisnetVersion2() {
    int tabla_local = 0;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      tabla_local = 0;
      listaCadenas_local = obtenerListadoInstruccionesSqlTablasSisnetVersion02();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        tabla_local = ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCadenas_local = null;
      iterador_local = null;
    } 
    
    return tabla_local;
  }
  public void actualizarRegistrosVersion2() {
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCadenas_local = obtenerListadoInstruccionesSqlActualizarRegistrosSisnetVersion02();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCadenas_local = null;
      iterador_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version02.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */