package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.objetosManejo.ScriptBaseDatosSisnet;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version01
  extends AdministradorBaseDatos
{
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  public Version01(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  public void generarBaseDatosSisnet() {
    try {
      crearTablasBaseDatosSisnet();
      insertarRegistroConfiguracion(1);
      getConexionBaseDatos().cerrarConeccionBaseDatos();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private int crearTablasBaseDatosSisnet() {
    int crearTabla_local = 0;
    ScriptBaseDatosSisnet scriptBaseDatosSisnet_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      crearTabla_local = 0;
      scriptBaseDatosSisnet_local = new ScriptBaseDatosSisnet();
      listaCadenas_local = scriptBaseDatosSisnet_local.obtenerListadoInstruccionesSqlBaseDatosSisnetVersion01();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        crearTabla_local = ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCadenas_local = null;
      iterador_local = null;
      scriptBaseDatosSisnet_local = null;
    } 
    
    return crearTabla_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version01.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */