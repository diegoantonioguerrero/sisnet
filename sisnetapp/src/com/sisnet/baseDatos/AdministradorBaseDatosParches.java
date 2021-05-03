package com.sisnet.baseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesVersion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoManejadorConsultaSQL;
import java.sql.ResultSet;
import java.util.Iterator;
public class AdministradorBaseDatosParches
  extends AdministradorBaseDatos
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public AdministradorBaseDatosParches(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private void crearCampoAplicarParche001Configuracion(ListaCadenas pListadoInstruccionesCrearCampoAplicarParche001) {
    Iterator iterador_local = null;
    if (pListadoInstruccionesCrearCampoAplicarParche001 == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      iterador_local = pListadoInstruccionesCrearCampoAplicarParche001.iterator();
      while (iterador_local.hasNext()) {
        ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterador_local = null;
    } 
  }
  private String consultaVerificarAplicarParcheUno() {
    return "select fldaplicarparche001 from CONFIGURACION;";
  }
  public boolean verificarAplicarParche001(boolean pEsCrearCampoAplicarParche001, ListaCadenas pListadoInstruccionesCrearCampoAplicarParche001) {
    boolean aplicarParche001_local = false;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    if (pListadoInstruccionesCrearCampoAplicarParche001 == ConstantesGeneral.VALOR_NULO) {
      return aplicarParche001_local;
    }
    try {
      if (pEsCrearCampoAplicarParche001) {
        crearCampoAplicarParche001Configuracion(pListadoInstruccionesCrearCampoAplicarParche001);
      }
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaVerificarAplicarParcheUno(), "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          aplicarParche001_local = resultSet_local.getBoolean("fldaplicarparche001");
        }
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      resultSet_local = null;
    } 
    return aplicarParche001_local;
  }
  private String consultaSQLAplicaciones() {
    return "select fldidaplicacion,fldnombreaplicacion,fldtituloaplicacion from APLICACION order by fldtituloaplicacion";
  }
  public ListaGeneral obtenerListaGeneralAplicaciones() {
    ListaGeneral listaGeneralAplicaciones_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    try {
      listaGeneralAplicaciones_local = new ListaGeneral();
      consulta_local = consultaSQLAplicaciones();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralAplicaciones_local.adicionar(resultSet_local.getString("fldtituloaplicacion").trim(), resultSet_local.getObject("fldidaplicacion").toString(), false);
        
        }
      }
      else {
        
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
    return listaGeneralAplicaciones_local;
  }
  private String consultaBorrarRegistrosConfirguracion() {
    return " delete  from CONFIGURACION;";
  }
  public void borrarRegistrosConfiguracion() {
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaBorrarRegistrosConfirguracion(), "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  private String consultaInsertarRegistroConfiguracion() {
    return "insert into CONFIGURACION values( 40," + mc.colocarEntreComillas(ConstantesVersion.FECHA_VERSION.toString()) + ',' + (int)Character.MIN_VALUE + ')' + ';';
  }
  public void insertarRegistroConfiguracion() {
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaInsertarRegistroConfiguracion(), "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      objetoManejadorConsultaSQL_local = null;
      manejadorConsultaSQL_local = null;
    } 
  }
  private String consultaActualizarAplicarParche001() {
    return "update CONFIGURACION set fldaplicarparche001=false;";
  }
  public void actualizarAplicarParche001() {
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaActualizarAplicarParche001(), "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      objetoManejadorConsultaSQL_local = null;
      manejadorConsultaSQL_local = null;
    } 
  }
  private String consultaSQLGruposInformacionAplicacion(int pIdAplicacion) {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + '=' + pIdAplicacion + " and " + "fldgrupoinformacionprincipal" + " <> " + '1' + " order by " + "fldidgrupoinformacion";
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionAplicacion(int pIdAplicacion) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaSQLGruposInformacionAplicacion(pIdAplicacion), "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGrupoInformacion_local.adicionar(obtenerGrupoInformacionValoresConsulta(resultSet_local, false));
        }
      } else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      resultSet_local = null;
    } 
    return listaGrupoInformacion_local;
  }
  private String consultaSQLCamposGrupoSinLlavePrimaria(int pIdGrupoInformacion) {
    return "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + '=' + pIdGrupoInformacion + " and " + "fldllaveprimaria" + '=' + (int)Character.MIN_VALUE + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " order by " + "fldidcampo";
  }
  public ListaCampo obtenerListaCamposGrupoSinLlavePrimaria(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaSQLCamposGrupoSinLlavePrimaria(pIdGrupoInformacion), "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaCampo_local.adicionar(obtenerCampoValoresConsulta(resultSet_local, false));
        }
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      objetoManejadorConsultaSQL_local = null;
      manejadorConsultaSQL_local = null;
      resultSet_local = null;
    } 
    return listaCampo_local;
  }
  private String consultaActualizarPosicionCampo(int pIdCampo, int pPosicion) {
    return "update CAMPO set fldposicion=" + pPosicion + " where " + "fldidcampo" + '=' + pIdCampo + ';';
  }
  public void actualizarPosicionCampo(int pIdCampo, int pPosicionCampo) {
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaActualizarPosicionCampo(pIdCampo, pPosicionCampo), "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      objetoManejadorConsultaSQL_local = null;
      manejadorConsultaSQL_local = null;
    } 
  }
  private String consultaActualizarPosicionGrupoInformacion(int pIdGrupoInformacion, int pPosicion) {
    return "update GRUPOINFORMACION set fldposicion=" + pPosicion + " where " + "fldidgrupoinformacion" + '=' + pIdGrupoInformacion + ';';
  }
  public void actualizarPosicionGrupoInformacion(int pIdGrupoInformacion, int pPosicionGrupoInformacion) {
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaActualizarPosicionGrupoInformacion(pIdGrupoInformacion, pPosicionGrupoInformacion), "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  private void crearCampoAplicarParche002Configuracion(ListaCadenas pListadoInstruccionesCrearCampoAplicarParche002) {
    Iterator iterador_local = null;
    if (pListadoInstruccionesCrearCampoAplicarParche002 == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      iterador_local = pListadoInstruccionesCrearCampoAplicarParche002.iterator();
      while (iterador_local.hasNext()) {
        ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterador_local = null;
    } 
  }
  public void crearCamposParche002(ListaCadenas pListadoInstruccionesCamposParche002) {
    Iterator iterador_local = null;
    if (pListadoInstruccionesCamposParche002 == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      iterador_local = pListadoInstruccionesCamposParche002.iterator();
      while (iterador_local.hasNext()) {
        ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterador_local = null;
    } 
  }
  private String consultaVerificarAplicarParcheDos() {
    return "select fldaplicarparche002 from CONFIGURACION;";
  }
  public boolean verificarAplicarParche002(boolean pEsCrearCampoAplicarParche002, ListaCadenas pListadoInstruccionesCrearCampoAplicarParche002) {
    boolean aplicarParche002_local = false;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    if (pListadoInstruccionesCrearCampoAplicarParche002 == ConstantesGeneral.VALOR_NULO) {
      return aplicarParche002_local;
    }
    try {
      if (pEsCrearCampoAplicarParche002) {
        crearCampoAplicarParche002Configuracion(pListadoInstruccionesCrearCampoAplicarParche002);
      }
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaVerificarAplicarParcheDos(), "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          aplicarParche002_local = resultSet_local.getBoolean("fldaplicarparche002");
        }
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      resultSet_local = null;
    } 
    return aplicarParche002_local;
  }
  private String consultaActualizarAplicarParche002() {
    return "update CONFIGURACION set fldaplicarparche002=false;";
  }
  public void actualizarAplicarParche002() {
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaActualizarAplicarParche002(), "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      objetoManejadorConsultaSQL_local = null;
      manejadorConsultaSQL_local = null;
    } 
  }
  public void actualizarCamposParche002(ListaCadenas pListadoInstruccionesActualizarCamposParche002) {
    Iterator iterador_local = null;
    if (pListadoInstruccionesActualizarCamposParche002 == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      iterador_local = pListadoInstruccionesActualizarCamposParche002.iterator();
      while (iterador_local.hasNext()) {
        ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterador_local = null;
    } 
  }
  public void borrarCamposParche002(ListaCadenas pListadoInstruccionesBorrarCamposParche002) {
    Iterator iterador_local = null;
    if (pListadoInstruccionesBorrarCamposParche002 == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      iterador_local = pListadoInstruccionesBorrarCamposParche002.iterator();
      while (iterador_local.hasNext()) {
        ejecutarInstruccionSQL((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterador_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\AdministradorBaseDatosParches.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */