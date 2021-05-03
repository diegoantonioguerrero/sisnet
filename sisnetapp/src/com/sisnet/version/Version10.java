package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoManejadorConsultaSQL;
import com.sisnet.utilidades.Encriptor;
import java.sql.ResultSet;
import java.util.Iterator;
public class Version10
  extends AdministradorBaseDatos
{
  public Version10(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListaIdUsuarioOrdenDescendente() {
    ListaCadenas listaCadenas_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = "select fldidusuario from usuario where fldidtipousuario <> 0 order by fldidusuario desc";
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        listaCadenas_local = new ListaCadenas();
        while (resultSet_local.next()) {
          listaCadenas_local.adicionar(String.valueOf(resultSet_local.getInt("fldidusuario")));
        }
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaCadenas_local;
  }
  private void actualizarIdentificadoresUsuarios() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCadenas_local = obtenerListaIdUsuarioOrdenDescendente();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        consultaSql_local = "update usuario set fldidusuario = fldidusuario + 1 where fldidusuario = " + (String)iterador_local.next();
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
  private boolean verificarExistenciaUsuarioLocal() {
    boolean existeUsuario_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = "select fldidusuario from usuario where fldnombreusuario = 'LOCAL'";
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeUsuario_local = resultSet_local.next();
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return existeUsuario_local;
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion10() {
    ListaCadenas listaCadenas_local = null;
    String contrasena_local = null;
    Encriptor encriptor_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!verificarExistenciaUsuarioLocal()) {
        actualizarIdentificadoresUsuarios();
        encriptor_local = new Encriptor("123456", false);
        contrasena_local = mc.convertirAMayusculas(encriptor_local.encriptarCadena());
        listaCadenas_local.adicionar("insert into usuario(fldidusuario, fldnombreusuario, fldcontrasena, fldnumeroaleatorio, fldtipolicencia, fldfechavencimiento, fldlicencia, fldidtipousuario) values (1, 'LOCAL', '" + contrasena_local + "', 1587436897, 'W', '2050-12-31', '00000000000000000000000000000000', 0);");
      } 
      
      listaCadenas_local.adicionar("update usuario set fldnombreusuario = 'ADMINISTRADOR' where fldnombreusuario = 'administrador';");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      encriptor_local = null;
      contrasena_local = null;
    } 
    
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion10() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion10();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version10.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */