package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version21
  extends AdministradorBaseDatos
{
  public Version21(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion21() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (verificarExistenciaLlaveForanea("TABLA", "")) {
        listaCadenas_local.adicionar("alter table tabla drop constraint fktabla cascade");
      }
      if (verificarExistenciaCampo("fldidaplicacion", "TABLA")) {
        listaCadenas_local.adicionar("alter table tabla drop column fldidaplicacion");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion21(String pNombreBaseDatosInformacion) {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    if (pNombreBaseDatosInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion21();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        consultaSql_local = (String)iterador_local.next();
        ejecutarInstruccionSQL(consultaSql_local);
      } 
      if (!verificarExistenciaBaseDatos(pNombreBaseDatosInformacion)) {
        crearBaseDatos(pNombreBaseDatosInformacion);
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */