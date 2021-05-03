package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version11
  extends AdministradorBaseDatos
{
  private static final String const_CampoLicencia = "fldlicencia";
  private static final String const_CampoNumeroAleatorio = "fldnumeroaleatorio";
  public Version11(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion11() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      
      if (verificarExistenciaCampo("fldlicencia", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario drop column fldlicencia;");
      }
      if (verificarExistenciaCampo("fldnumeroaleatorio", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario drop column fldnumeroaleatorio;");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion11() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion11();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version11.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */