package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version36
  extends AdministradorBaseDatos
{
  public Version36(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlActualizarCamposVersion36() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaCampo("fldmargensuperiorgrupoinformacion", "GRUPOINFORMACION"))
      {
        listaCadenas_local.adicionar("alter table GRUPOINFORMACION add fldmargensuperiorgrupoinformacion integer default 25");
      }
      
      if (verificarExistenciaCampo("fldcolorgrupoinformacion", "GRUPOINFORMACION"))
      {
        listaCadenas_local.adicionar("alter table GRUPOINFORMACION drop fldcolorgrupoinformacion");
      }
      
      if (!verificarExistenciaCampo("fldcambiarrenglon", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldcambiarrenglon bool default true");
      }
      
      if (!verificarExistenciaCampo("fldmargensuperiorcampo", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldmargensuperiorcampo integer default 0");
      }
      
      if (!verificarExistenciaCampo("fldanchoetiquetacampo", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldanchoetiquetacampo integer default 200");
      }
      
      if (!verificarExistenciaCampo("fldanchocontrolcampo", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldanchocontrolcampo integer default 400");
      }
      
      if (!verificarExistenciaCampo("fldcantidadrenglonescontrolcampo", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldcantidadrenglonescontrolcampo integer default 5");
      }
      
      if (!verificarExistenciaCampo("fldmargenizquierdaetiquetacampo", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldmargenizquierdaetiquetacampo integer default 0");
      }
      
      if (!verificarExistenciaCampo("fldmargenizquierdacontrolcampo", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table CAMPO add fldmargenizquierdacontrolcampo integer default 0");
      }
      
      listaCadenas_local.adicionar("update USUARIO set fldtiemposesion =60  where fldidtipousuario =0");
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion36() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlActualizarCamposVersion36();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version36.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */