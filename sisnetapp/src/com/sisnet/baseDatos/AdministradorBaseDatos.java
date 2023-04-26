package com.sisnet.baseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.baseDatos.CamposAdministrador;
import com.sisnet.baseDatos.ConexionBaseDatos;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.TipoUsuario;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesVersion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoManejadorConsultaSQL;
import com.sisnet.utilidades.Encriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
public class AdministradorBaseDatos
{
  protected static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  private static CamposAdministrador cad = CamposAdministrador.getCamposAdministrador();
  protected static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  protected static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private String aDriverJdbc;
  private String aUbicacionServidor;
  private String aPuertoConexion;
  private String aNombreBaseDatos;
  private String aUsuario;
  private String aContrasena;
  private int aError;
  private ConexionBaseDatos aConexionBaseDatos;
  public AdministradorBaseDatos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    if (pObjetoConexionBaseDatos != ConstantesGeneral.VALOR_NULO) {
      setDriverJdbc(pObjetoConexionBaseDatos.getDriverJdbc());
      setUbicacionServidor(pObjetoConexionBaseDatos.getUbicacionServidor());
      setPuertoConexion(pObjetoConexionBaseDatos.getPuertoConexion());
      setNombreBaseDatos(pObjetoConexionBaseDatos.getNombreBaseDatos());
      setUsuario(pObjetoConexionBaseDatos.getUsuario());
      setContrasena(pObjetoConexionBaseDatos.getContrasena());
      setConexionBaseDatos(new ConexionBaseDatos(pObjetoConexionBaseDatos));
      getConexionBaseDatos().conectarBaseDatos();
      setError(0);
    } 
  }
  public String getDriverJdbc() {
    return this.aDriverJdbc;
  }
  public void setDriverJdbc(String pDriverJdbc) {
    this.aDriverJdbc = pDriverJdbc;
  }
  public String getUbicacionServidor() {
    return this.aUbicacionServidor;
  }
  public void setUbicacionServidor(String pUbicacionServidor) {
    this.aUbicacionServidor = pUbicacionServidor;
  }
  public String getPuertoConexion() {
    return this.aPuertoConexion;
  }
  public void setPuertoConexion(String pPuertoConexion) {
    this.aPuertoConexion = pPuertoConexion;
  }
  public String getNombreBaseDatos() {
    return this.aNombreBaseDatos;
  }
  public void setNombreBaseDatos(String pNombreBaseDatos) {
    this.aNombreBaseDatos = pNombreBaseDatos;
  }
  public String getUsuario() {
    return this.aUsuario;
  }
  public void setUsuario(String pUsuario) {
    this.aUsuario = pUsuario;
  }
  public String getContrasena() {
    return this.aContrasena;
  }
  public void setContrasena(String pContrasena) {
    this.aContrasena = pContrasena;
  }
  public int getError() {
    return this.aError;
  }
  public void setError(int pError) {
    this.aError = pError;
  }
  public ConexionBaseDatos getConexionBaseDatos() {
    return this.aConexionBaseDatos;
  }
  public void setConexionBaseDatos(ConexionBaseDatos pConexionBaseDatos) {
    this.aConexionBaseDatos = pConexionBaseDatos;
  }
  public boolean verificarExistenciaBaseDatos(String pNombreBaseDatos) {
    boolean verificarExistenciaBaseDatos_local = false;
    int verificarExistencia_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaBaseDatos_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLVerificarExistenciaBaseDatos(mc.convertirAMinusculas(pNombreBaseDatos));
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      verificarExistencia_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (verificarExistencia_local == 0) {
        verificarExistenciaBaseDatos_local = manejadorConsultaSQL_local.getResultSet().next();
      }
    } catch (SQLException excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return verificarExistenciaBaseDatos_local;
  }
  public boolean verificarExistenciaTabla(String pNombreTabla) {
    boolean verificarExistenciaTabla_local = false;
    int verificarExistencia_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaTabla_local;
    }
    try {
      consulta_local = ca.obtenerCadenaSQLVerificarExistenciaRestriccion(mc.concatenarCadena("pk", mc.convertirAMinusculas(pNombreTabla)));
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      verificarExistencia_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (verificarExistencia_local == 0) {
        verificarExistenciaTabla_local = manejadorConsultaSQL_local.getResultSet().next();
      }
    } catch (SQLException excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return verificarExistenciaTabla_local;
  }
  public boolean verificarExistenciaLlaveForanea(String pNombreTabla, String pNombreTablaReferencia) {
    boolean verificarExistenciaLlave_local = false;
    int verificarExistencia_local = -1;
    String nombreRestriccion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaLlave_local;
    }
    if (pNombreTablaReferencia == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaLlave_local;
    }
    
    try {
      nombreRestriccion_local = mc.concatenarCadena("fk", mc.convertirAMinusculas(pNombreTabla) + mc.convertirAMinusculas(pNombreTablaReferencia));
      
      consulta_local = ca.obtenerCadenaSQLVerificarExistenciaRestriccion(nombreRestriccion_local);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      verificarExistencia_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (verificarExistencia_local == 0) {
        verificarExistenciaLlave_local = manejadorConsultaSQL_local.getResultSet().next();
      }
    } catch (SQLException excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return verificarExistenciaLlave_local;
  }
  public boolean verificarExistenciaIndice(Campo pCampo) {
    boolean verificarExistenciaLlave_local = false;
    int verificarExistencia_local = -1;
    String nombreIndice_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaLlave_local;
    }
    
    try {
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreIndice_local = mc.concatenarCadena("idx", mc.convertirAMinusculas(pCampo.getGrupoInformacion().getNombreGrupoInformacion() + pCampo.getNombreCampo()));
      } else {
        
        nombreIndice_local = mc.concatenarCadena("idx", mc.convertirAMinusculas(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion() + pCampo.getNombreCampo()));
      } 
      
      consulta_local = ca.obtenerCadenaSQLVerificarExistenciaIndice(nombreIndice_local);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      verificarExistencia_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (verificarExistencia_local == 0) {
        verificarExistenciaLlave_local = manejadorConsultaSQL_local.getResultSet().next();
      }
    } catch (SQLException excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return verificarExistenciaLlave_local;
  }
  public boolean verificarExistenciaCampo(String pNombreCampo, String pNombreTabla) {
    boolean verificarExistenciaCampo_local = false;
    int columna_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSetMetaData ResultSetMetaData_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaCampo_local;
    }
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return verificarExistenciaCampo_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCamposTabla(pNombreTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        ResultSetMetaData_local = resultSet_local.getMetaData();
        columna_local = 1;
        while (columna_local <= ResultSetMetaData_local.getColumnCount()) {
          verificarExistenciaCampo_local = mc.sonCadenasIgualesIgnorarMayusculas(ResultSetMetaData_local.getColumnName(columna_local), pNombreCampo);
          
          if (verificarExistenciaCampo_local) {
            break;
          }
          columna_local++;
        } 
      } 
    } catch (SQLException excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      ResultSetMetaData_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return verificarExistenciaCampo_local;
  }
  protected int actualizarRegistroConfiguracion() {
    int registroConfiguracion_local = -1;
    String registro_local = null;
    
    try {
      registro_local = mc.concatenarCadena("update ", "CONFIGURACION");
      registro_local = mc.concatenarCadena(registro_local, " set ");
      registro_local = mc.concatenarCadena(registro_local, "fldnumeroversion=40,");
      
      registro_local = mc.concatenarCadena(registro_local, "fldfechaversion=" + mc.colocarEntreComillas(ConstantesVersion.FECHA_VERSION.toString()));
      
      registroConfiguracion_local = ejecutarInstruccionSQL(registro_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      registro_local = null;
    } 
    
    return registroConfiguracion_local;
  }
  public int crearBaseDatos(String pNombreBaseDatos) {
    int crearBaseDatos_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return crearBaseDatos_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCrearBaseDatos(pNombreBaseDatos);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      crearBaseDatos_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (crearBaseDatos_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return crearBaseDatos_local;
  }
  public int crearBaseDatosSisnet(String pNombreBaseDatos) {
    int baseDatosSisnet_local = 0;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return baseDatosSisnet_local;
    }
    
    try {
      baseDatosSisnet_local = crearBaseDatos(pNombreBaseDatos);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return baseDatosSisnet_local;
  }
  public int crearTabla(String pNombreTabla) {
    int crearTabla_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return crearTabla_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCrearTabla(pNombreTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      crearTabla_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (crearTabla_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
        crearRestriccionLlavePrimaria(pNombreTabla, ap.conformarNombreCampoLlavePrimaria(pNombreTabla), "pk" + pNombreTabla);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return crearTabla_local;
  }
  public int borrarTabla(String pNombreTabla) {
    int borrarTabla_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return borrarTabla_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLBorrarTabla(pNombreTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      borrarTabla_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (borrarTabla_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return borrarTabla_local;
  }
  public int crearCampo(String pNombreTabla, Campo pCampo, String pNombreLlavePrimariaGrupoPrincipal) {
    int crearcampo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return crearcampo_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return crearcampo_local;
    }
    if (pNombreLlavePrimariaGrupoPrincipal == ConstantesGeneral.VALOR_NULO) {
      return crearcampo_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCrearCampo(pNombreTabla, pCampo, pNombreLlavePrimariaGrupoPrincipal);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      crearcampo_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (crearcampo_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return crearcampo_local;
  }
  public int borrarCampoBaseDatos(String pNombreTabla, String pNombreCampo) {
    int borrarcampo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return borrarcampo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return borrarcampo_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLBorrarCampo(pNombreTabla, pNombreCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      borrarcampo_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (borrarcampo_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return borrarcampo_local;
  }
  public int crearIndice(String pNombreIndice, String pNombreTabla, String pNombreCampo) {
    int crearIndice_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreIndice == ConstantesGeneral.VALOR_NULO) {
      return crearIndice_local;
    }
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return crearIndice_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return crearIndice_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCrearIndice(pNombreIndice, pNombreTabla, pNombreCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      crearIndice_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (crearIndice_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return crearIndice_local;
  }
  public int borrarIndice(String pNombreIndice) {
    int borrarIndice_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreIndice == ConstantesGeneral.VALOR_NULO) {
      return borrarIndice_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLBorrarIndice(mc.convertirAMinusculas(pNombreIndice));
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      borrarIndice_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (borrarIndice_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return borrarIndice_local;
  }
  private int crearRestriccionLlavePrimaria(String pNombreTabla, String pNombreCampo, String pNombreRestriccion) {
    int crearRestriccion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCrearRestriccionLlavePrimaria(pNombreTabla, pNombreCampo, pNombreRestriccion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      crearRestriccion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (crearRestriccion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return crearRestriccion_local;
  }
  public int crearRestriccionLlaveForanea(String pNombreTabla, String pNombreCampo, String pNombreTablaReferencia, String pNombreCampoReferencia, String pNombreRestriccion) {
    int crearRestriccion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    if (pNombreTablaReferencia == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    if (pNombreCampoReferencia == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO) {
      return crearRestriccion_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCrearRestriccionLlaveForanea(pNombreTabla, pNombreCampo, pNombreTablaReferencia, pNombreCampoReferencia, pNombreRestriccion);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      crearRestriccion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (crearRestriccion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return crearRestriccion_local;
  }
  public int ejecutarInstruccionSQL(String pConsulta) {
    int ejecutarInstruccionSQL_local = -1;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pConsulta == ConstantesGeneral.VALOR_NULO) {
      return ejecutarInstruccionSQL_local;
    }
    
    try {
      if (!mc.esCadenaVacia(pConsulta)) {
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), pConsulta, "actualizacion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        ejecutarInstruccionSQL_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (ejecutarInstruccionSQL_local != 0) {
          cancelarTransaccion();
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } else {
          confirmarTransaccion();
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return ejecutarInstruccionSQL_local;
  }
  protected int confirmarTransaccion() {
    int confirmarTransaccion_local = -1;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), "commit;", "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      confirmarTransaccion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (confirmarTransaccion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return confirmarTransaccion_local;
  }
  protected int cancelarTransaccion() {
    int cancelarTransaccion_local = -1;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), "rollback;", "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      cancelarTransaccion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (cancelarTransaccion_local != 0) {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return cancelarTransaccion_local;
  }
  public int obtenerTipoUsuarioPorContrasena(String pUsuario, String pContrasena) {
    int tipoUsuario_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return tipoUsuario_local;
    }
    if (pContrasena == ConstantesGeneral.VALOR_NULO) {
      return tipoUsuario_local;
    }
    
    try {
      consulta_local = ca.consultaSQLObtenerTipoUsuarioPorContrasena(pUsuario, pContrasena);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          tipoUsuario_local = resultSet_local.getInt("fldidtipousuario");
        } else {
          tipoUsuario_local = -1;
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
    return tipoUsuario_local;
  }
  public boolean obtenerAsignacionAdministradorUsuario(String pUsuario) {
    boolean esAsignacionAdministradorUsuario_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return esAsignacionAdministradorUsuario_local;
    }
    
    try {
      consulta_local = ca.consultaSQLObtenerAsignacionAdministradorUsuario(pUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          esAsignacionAdministradorUsuario_local = resultSet_local.getBoolean("fldasignacionadministrador");
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
    return esAsignacionAdministradorUsuario_local;
  }
  public boolean verificarUsuarioPorNombre(String pNombreUsuario) {
    boolean existeUsuario_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return existeUsuario_local;
    }
    
    try {
      consulta_local = ca.consultaSQLVerificarUsuarioPorNombre(pNombreUsuario);
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
  public boolean verificarExistenciaGrupoInformacionPorDescripcion(String pDescripcionGrupoInformacion, int pIdAplicacion) {
    boolean existeGrupoInformacion_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return existeGrupoInformacion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLGrupoInformacionPorDescripcion(pIdAplicacion, pDescripcionGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeGrupoInformacion_local = resultSet_local.next();
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
    
    return existeGrupoInformacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionValoresConsulta(ResultSet pResultSet, boolean pEsCargaMotor) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pResultSet == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(pResultSet.getInt("fldidgrupoinformacion"));
      grupoInformacion_local.setIdAplicacion(pResultSet.getInt("fldidaplicacion"));
      if (!pEsCargaMotor) {
        grupoInformacion_local.setAplicacion(obtenerAplicacionPorId(pResultSet.getInt("fldidaplicacion"), pEsCargaMotor));
      }
      
      grupoInformacion_local.setNombreGrupoInformacion(pResultSet.getString("fldnombregrupoinformacion"));
      
      grupoInformacion_local.setDescripcionGrupoInformacion(pResultSet.getString("flddescripciongrupoinformacion"));
      
      grupoInformacion_local.setGrupoInformacionPrincipal(pResultSet.getBoolean("fldgrupoinformacionprincipal"));
      
      grupoInformacion_local.setGrupoInformacionMultiple(pResultSet.getBoolean("fldgrupoinformacionmultiple"));
      
      grupoInformacion_local.setPosicion(pResultSet.getInt("fldposicion"));
      grupoInformacion_local.setMostrarDetalle(pResultSet.getBoolean("fldmostrardetalle"));
      grupoInformacion_local.setMargenSuperior(pResultSet.getInt("fldmargensuperiorgrupoinformacion"));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public boolean verificarExistenciaCampoPorEtiqueta(Campo pCampo, int pNumeroCampo) {
    boolean existeCampo_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return existeCampo_local;
    }
    
    try {
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple() && pNumeroCampo != 0) {
        consulta_local = ca.consultaSQLCampoPorEtiquetaGrupoMultiple(pCampo.getGrupoInformacion().getIdGrupoInformacion(), pCampo.getEtiquetaCampo());
      } else {
        consulta_local = ca.consultaSQLCampoPorEtiquetaGruposNoMultiples(pCampo.getGrupoInformacion().getAplicacion().getIdAplicacion(), pCampo.getEtiquetaCampo());
      } 
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeCampo_local = resultSet_local.next();
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
    
    return existeCampo_local;
  }
  public boolean verificarExistenciaCampoPorEtiqueta(String pEtiqueta, GrupoInformacion pGrupoInformacion, int pNumeroCampo) {
    boolean existeCampo_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return existeCampo_local;
    }
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return existeCampo_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple() && pNumeroCampo != 0) {
        consulta_local = ca.consultaSQLCampoPorEtiquetaGrupoMultiple(pGrupoInformacion.getIdGrupoInformacion(), pEtiqueta);
      } else {
        consulta_local = ca.consultaSQLCampoPorEtiquetaGruposNoMultiples(pGrupoInformacion.getAplicacion().getIdAplicacion(), pEtiqueta);
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeCampo_local = resultSet_local.next();
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
    
    return existeCampo_local;
  }
  public boolean verificarEsCampoMaestro(int pIdCampo) {
    boolean esCampoMaestro_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLVerificarEsCampoMaestro(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        esCampoMaestro_local = resultSet_local.next();
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
    
    return esCampoMaestro_local;
  }
  public boolean verificarEsCampoHabilitaOtros(int pIdCampo) {
    boolean esCampoMaestro_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLVerificarEsCampoHabilitaOtros(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        esCampoMaestro_local = resultSet_local.next();
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
    
    return esCampoMaestro_local;
  }
  public boolean verificarAplicacionTieneGruposMultiples(int pIdAplicacion) {
    boolean aplicacionTieneGruposMultiples_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLGruposInformacionMultiplesAplicacion(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        aplicacionTieneGruposMultiples_local = resultSet_local.next();
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
    
    return aplicacionTieneGruposMultiples_local;
  }
  public boolean verificarValorRegistro(Campo pCampo, String pNombreLlavePrincipal, int pValorIdentificador) {
    boolean valorRegistro_local = false;
    String consulta_local = null;
    String nombreGrupoInformacion_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorRegistro_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO) {
      return valorRegistro_local;
    }
    
    try {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consulta_local = ca.consultaSQLVerificarValorRegistro(nombreGrupoInformacion_local, pCampo.getNombreCampo(), pNombreLlavePrincipal, pValorIdentificador);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        valorRegistro_local = resultSet_local.next();
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
    
    return valorRegistro_local;
  }
  public int borrarCampo(int pIdCampo) {
    int borrarRegistro_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLBorrarCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      borrarRegistro_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (borrarRegistro_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return borrarRegistro_local;
  }
  private int obtenerConsecutivoTabla(String pNombreTabla, String pNombreCampo) {
    int consecutivo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return consecutivo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return consecutivo_local;
    }
    
    try {
      consecutivo_local = 1;
      consulta_local = ca.consultaSQLIdUltimoRegistro(pNombreTabla, pNombreCampo, "", -1);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0 && 
        manejadorConsultaSQL_local.getResultSet().next()) {
        consecutivo_local = manejadorConsultaSQL_local.getResultSet().getInt("ultimoregistro") + 1;
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return consecutivo_local;
  }
  public int obtenerIdUltimoRegistro(String pNombreTabla, String pNombreIdentificador, String pNombreLlavePrimaria, int pValorLlavePrimaria) {
    int idUltimoRegistro_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistro_local;
    }
    if (pNombreIdentificador == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistro_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistro_local;
    }
    
    try {
      idUltimoRegistro_local = 1;
      consulta_local = ca.consultaSQLIdUltimoRegistro(pNombreTabla, pNombreIdentificador, pNombreLlavePrimaria, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0 && 
        manejadorConsultaSQL_local.getResultSet().next()) {
        idUltimoRegistro_local = manejadorConsultaSQL_local.getResultSet().getInt("ultimoregistro");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return idUltimoRegistro_local;
  }
  public int obtenerIdUltimoRegistroIncluido(String pNombreTabla, String pNombreLlavePrimaria, String pNombreLlavePrincipal, int pValorLlavePrincipal) {
    int idUltimoRegistroIncluido_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistroIncluido_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistroIncluido_local;
    }
    
    try {
      consulta_local = ca.consultaSQLIdUltimoRegistroIncluido(pNombreTabla, pNombreLlavePrimaria, pNombreLlavePrincipal, pValorLlavePrincipal);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0 && 
        manejadorConsultaSQL_local.getResultSet().next()) {
        idUltimoRegistroIncluido_local = manejadorConsultaSQL_local.getResultSet().getInt("ultimoregistro");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return idUltimoRegistroIncluido_local;
  }
  public int obtenerIdUltimoRegistroIncluidoGrupoInformacion(String pNombreGrupoInformacion, String pNombreLlavePrimaria, String pNombreCampoValorUnico, String pNombreLlavePrincipal, int pValorLlavePrincipal) {
    int idUltimoRegistroIncluido_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistroIncluido_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return idUltimoRegistroIncluido_local;
    }
    
    try {
      consulta_local = ca.consultaSQLIdUltimoRegistroIncluidoGrupoInformacion(pNombreGrupoInformacion, pNombreLlavePrimaria, pNombreCampoValorUnico, pNombreLlavePrincipal, pValorLlavePrincipal);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0 && 
        manejadorConsultaSQL_local.getResultSet().next()) {
        idUltimoRegistroIncluido_local = manejadorConsultaSQL_local.getResultSet().getInt("ultimoregistro");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return idUltimoRegistroIncluido_local;
  }
  public int obtenerIdPrimerRegistroIncluido(String pNombreTabla, String pNombreLlavePrimaria, String pNombreCampoValorUnico, String pNombreLlavePrincipal, int pValorLlavePrincipal) {
    int idPrimerRegistroIncluido_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return idPrimerRegistroIncluido_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return idPrimerRegistroIncluido_local;
    }
    
    try {
      consulta_local = ca.consultaSQLIdPrimerRegistroIncluido(pNombreTabla, pNombreLlavePrimaria, pNombreCampoValorUnico, pNombreLlavePrincipal, pValorLlavePrincipal);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0 && 
        manejadorConsultaSQL_local.getResultSet().next()) {
        idPrimerRegistroIncluido_local = manejadorConsultaSQL_local.getResultSet().getInt("primerregistro");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return idPrimerRegistroIncluido_local;
  }
  public ManejadorConsultaSQL obtenerManejadorConsulta(String pConsultaSQL) {
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO) {
      return manejadorConsultaSQL_local;
    }
    
    try {
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), pConsultaSQL, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      manejadorConsultaSQL_local.ejecutarConsulta();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return manejadorConsultaSQL_local;
  }
  public int obtenerNumeroGruposInformacionAplicacion(int pIdAplicacion) {
    int numeroGruposInformacionAplicacion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLNumeroGruposInformacionAplicacion(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          numeroGruposInformacionAplicacion_local = resultSet_local.getInt("numerogruposinformacion");
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
    
    return numeroGruposInformacionAplicacion_local;
  }
  public String obtenerNombreLlavePrimariaGrupoInformacion(GrupoInformacion pGrupoInformacion, boolean pEsConfiguracion) {
    String nombreIdentificadorGrupoInformacion_local = "";
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return nombreIdentificadorGrupoInformacion_local;
    }
    
    try {
      if (pEsConfiguracion) {
        nombreIdentificadorGrupoInformacion_local = ap.obtenerNombreLlavePrimariaTablaAdministrador(pGrupoInformacion.getIdGrupoInformacion());
      }
      else if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        nombreIdentificadorGrupoInformacion_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
      } else {
        nombreIdentificadorGrupoInformacion_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreIdentificadorGrupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionPorId(int pIdGrupoInformacion, boolean pEsCargaMotor) {
    GrupoInformacion grupoInformacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLGrupoInformacionPorId(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          grupoInformacion_local = obtenerGrupoInformacionValoresConsulta(resultSet_local, pEsCargaMotor);
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
    
    return grupoInformacion_local;
  }
  public Campo obtenerCampoPorId(int pIdCampo, boolean pEsCargaMotor) {
    Campo campo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLCampoPorId(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          campo_local = obtenerCampoValoresConsulta(resultSet_local, pEsCargaMotor);
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
    return campo_local;
  }
  public Campo obtenerCampoValoresConsulta(ResultSet pResultSet, boolean pEsCargaMotor) {
    Campo campo_local = null;
    
    if (pResultSet == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      campo_local = new Campo();
      campo_local.setIdCampo(pResultSet.getInt("fldidcampo"));
      campo_local.setNombreCampo(pResultSet.getString("fldnombrecampo"));
      campo_local.setEtiquetaCampo(pResultSet.getString("fldetiquetacampo"));
      campo_local.setSeudonimo(pResultSet.getString("fldseudonimo"));
      campo_local.getFormatoCampo().setTipoDato(pResultSet.getString("fldtipodato"));
      campo_local.getPlantillaCampo().setTienePlantilla(pResultSet.getBoolean("fldtieneplantilla"));
      campo_local.getFormatoCampo().setValorUnico(pResultSet.getBoolean("fldvalorunico"));
      campo_local.getFormatoCampo().setLongitudCampo(pResultSet.getInt("fldlongitudcampo"));
      campo_local.getFormatoCampo().setNumeroDecimales(pResultSet.getInt("fldnumerodecimales"));
      campo_local.setAnchoColumna(pResultSet.getInt("fldanchocolumna"));
      campo_local.getRestriccionCampo().setLlavePrimaria(pResultSet.getBoolean("fldllaveprimaria"));
      campo_local.getRestriccionCampo().setLlaveForanea(pResultSet.getBoolean("fldllaveforanea"));
      campo_local.setObligatorio(pResultSet.getBoolean("fldobligatorio"));
      campo_local.setVisibleUsuarioPrincipal(pResultSet.getBoolean("fldvisibleusuarioprincipal"));
      campo_local.setVisibleUsuarioSecundario(pResultSet.getBoolean("fldvisibleusuariosecundario"));
      campo_local.setModificable(pResultSet.getBoolean("fldmodificable"));
      campo_local.setPosicion(pResultSet.getInt("fldposicion"));
      campo_local.getEnlaceCampo().setOpcionDesconocido(pResultSet.getBoolean("fldopciondesconocido"));
      campo_local.setTipoHabilitacion(pResultSet.getInt("fldtipohabilitacion"));
      campo_local.getCalculoCampo().setCampoCalculado(pResultSet.getInt("fldcampocalculado"));
      campo_local.setBorrarValorNoHabilitado(pResultSet.getBoolean("fldborrarvalornohabilitado"));
      campo_local.getEnlaceCampo().setFiltradoRegistrosEnlazados(pResultSet.getInt("fldfiltradoregistrosenlazados"));
      campo_local.getEnlaceCampo().setValorFiltrado(pResultSet.getString("fldvalorfiltrado"));
      campo_local.getEnlaceCampo().setDependienteEnlazado(pResultSet.getInt("flddependienteenlazado"));
      campo_local.getCalculoCampo().setRecalculable(pResultSet.getBoolean("fldesrecalculable"));
      campo_local.getCalculoCampo().setFormatoCampoOrigenUno(pResultSet.getString("fldformatocampoorigenuno"));
      campo_local.getCalculoCampo().setFormatoCampoOrigenDos(pResultSet.getString("fldformatocampoorigendos"));
      campo_local.getCalculoCampo().setFormatoCampoCalculado(pResultSet.getString("fldformatocampocalculado"));
      campo_local.setIncluirOpcionConsulta(pResultSet.getBoolean("fldincluiropcionconsulta"));
      campo_local.setRecargarPantalla(pResultSet.getBoolean("fldrecargarpantalla"));
      campo_local.getEstiloCampo().setCambiarRenglon(pResultSet.getBoolean("fldcambiarrenglon"));
      campo_local.getEstiloCampo().setMargenSuperior(pResultSet.getInt("fldmargensuperiorcampo"));
      campo_local.getEstiloCampo().setAnchoEtiqueta(pResultSet.getInt("fldanchoetiquetacampo"));
      campo_local.getEstiloCampo().setAnchoControl(pResultSet.getInt("fldanchocontrolcampo"));
      campo_local.getEstiloCampo().setCantidadRenglones(pResultSet.getInt("fldcantidadrenglonescontrolcampo"));
      campo_local.getEstiloCampo().setMargenIzquierdaEtiqueta(pResultSet.getInt("fldmargenizquierdaetiquetacampo"));
      campo_local.getEstiloCampo().setMargenIzquierdaControl(pResultSet.getInt("fldmargenizquierdacontrolcampo"));
      campo_local.setEsImagen(pResultSet.getBoolean("fldesimagen"));
      campo_local.setAltoImagenPantallaPresentacion(pResultSet.getInt("fldaltoimagenpantallapresentacion"));
      campo_local.setAltoImagenPantallaEdicion(pResultSet.getInt("fldaltoimagenpantallaedicion"));
      campo_local.setOcultarEtiquetaControlExaminar(pResultSet.getBoolean("fldocultaretiquetacontrolexaminar"));
      
      campo_local.setOcultarEtiquetaControlVer(pResultSet.getBoolean("fldocultaretiquetacontrolver"));
      
      campo_local.setIdGrupoInformacion(pResultSet.getInt("fldidgrupoinformacion"));
      campo_local.getPlantillaCampo().setIdAplicacionPlantilla(pResultSet.getInt("fldidaplicacionplantilla"));
      
      campo_local.getEnlaceCampo().setIdCampoEnlaceDepende(pResultSet.getInt("fldcampoenlacedepende"));
      
      campo_local.getEnlaceCampo().setIdCampoOrigenEnlace(pResultSet.getInt("fldcampoorigenenlace"));
      
      campo_local.setIdHabilitadoPor(pResultSet.getInt("fldhabilitadopor"));
      campo_local.setIdListaDependiente(pResultSet.getInt("fldlistadependiente"));
      campo_local.getEnlaceCampo().setIdEnlazado(pResultSet.getInt("fldenlazado"));
      campo_local.getEnlaceCampo().setIdCampoOrigenFiltrado(pResultSet.getInt("fldcampoorigenfiltrado"));
      
      campo_local.getEnlaceCampo().setIdCampoDestinoFiltrado(pResultSet.getInt("fldcampodestinofiltrado"));
      
      campo_local.getCalculoCampo().setIdCampoValor(pResultSet.getInt("fldidcampovalor"));
      campo_local.getCalculoCampo().setIdCampoOrigenUno(pResultSet.getInt("fldidcampoorigenuno"));
      
      campo_local.getCalculoCampo().setIdCampoOrigenDos(pResultSet.getInt("fldidcampoorigendos"));
      
      if (!pEsCargaMotor) {
        campo_local.setGrupoInformacion(obtenerGrupoInformacionPorId(pResultSet.getInt("fldidgrupoinformacion"), pEsCargaMotor));
        
        campo_local.getPlantillaCampo().setAplicacionPlantilla(obtenerAplicacionPorId(pResultSet.getInt("fldidaplicacionplantilla"), pEsCargaMotor));
        
        campo_local.getEnlaceCampo().setCampoEnlaceDepende(obtenerCampoPorId(pResultSet.getInt("fldcampoenlacedepende"), pEsCargaMotor));
        
        campo_local.getEnlaceCampo().setCampoOrigenEnlace(obtenerCampoPorId(pResultSet.getInt("fldcampoorigenenlace"), pEsCargaMotor));
        
        campo_local.setHabilitadoPor(obtenerCampoPorId(pResultSet.getInt("fldhabilitadopor"), pEsCargaMotor));
        
        campo_local.setListaDependiente(obtenerCampoPorId(pResultSet.getInt("fldlistadependiente"), pEsCargaMotor));
        
        campo_local.getEnlaceCampo().setEnlazado(obtenerAplicacionPorId(pResultSet.getInt("fldenlazado"), pEsCargaMotor));
        
        campo_local.getEnlaceCampo().setCampoOrigenFiltrado(obtenerCampoPorId(pResultSet.getInt("fldcampoorigenfiltrado"), pEsCargaMotor));
        
        campo_local.getEnlaceCampo().setCampoDestinoFiltrado(obtenerCampoPorId(pResultSet.getInt("fldcampodestinofiltrado"), pEsCargaMotor));
        
        campo_local.getCalculoCampo().setCampoValor(obtenerCampoPorId(pResultSet.getInt("fldidcampovalor"), pEsCargaMotor));
        
        campo_local.getCalculoCampo().setCampoOrigenUno(obtenerCampoPorId(pResultSet.getInt("fldidcampoorigenuno"), pEsCargaMotor));
        
        campo_local.getCalculoCampo().setCampoOrigenDos(obtenerCampoPorId(pResultSet.getInt("fldidcampoorigendos"), pEsCargaMotor));
      } 
      
      campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public ListaCampo obtenerListaCamposGrupoInformacionMotor(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      consulta_local = ca.consultaSQLCamposGrupoInformacionMotor(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaCampo_local.adicionar(obtenerCampoValoresConsulta(resultSet_local, true));
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
    
    return listaCampo_local;
  }
  public ListaAplicacion obtenerListaAplicaciones(int pTipoSeleccionAplicacion, int pIdAplicacion, boolean pEsCargaMotor) {
    ListaAplicacion listaAplicaciones_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaAplicaciones_local = new ListaAplicacion();
      consulta_local = ca.consultaSQLAplicaciones(pTipoSeleccionAplicacion, pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaAplicaciones_local.adicionar(obtenerAplicacionValoresConsulta(resultSet_local, pEsCargaMotor));
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
    
    return listaAplicaciones_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionAplicacion(int pIdAplicacion, boolean pVerificarExistenCamposVisibles) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      consulta_local = ca.consultaSQLGruposInformacionAplicacion(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          if (pVerificarExistenCamposVisibles) {
            if (verificarGrupoInformacionContieneCamposVisibles(resultSet_local.getInt("fldidgrupoinformacion"))) {
              listaGrupoInformacion_local.adicionar(obtenerGrupoInformacionValoresConsulta(resultSet_local, false));
            }
            continue;
          } 
          listaGrupoInformacion_local.adicionar(obtenerGrupoInformacionValoresConsulta(resultSet_local, false));
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
    
    return listaGrupoInformacion_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionAplicacionMotor(int pIdAplicacion) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      consulta_local = ca.consultaSQLGruposInformacionAplicacionMotor(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGrupoInformacion_local.adicionar(obtenerGrupoInformacionValoresConsulta(resultSet_local, true));
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
    
    return listaGrupoInformacion_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionEnlazadosAplicacion(int pIdAplicacion, int pIdAplicacionEnlace) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      if (pIdAplicacionEnlace == -1) {
        consulta_local = ca.consultaSQLGruposInformacionEnlazadosAplicacion(pIdAplicacion);
      } else {
        consulta_local = ca.consultaSQLGruposInformacionEnlazadosAplicacion(pIdAplicacion, pIdAplicacionEnlace);
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
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
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaGrupoInformacion_local;
  }
  public int obtenerIdPrimerGrupoInformacionAplicacion(int pIdAplicacion) {
    int idGrupoInformacion_local = -1;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
      listaGrupoInformacion_local = obtenerListaGruposInformacionAplicacion(pIdAplicacion, false);
      iterador_local = listaGrupoInformacion_local.iterator();
      if (iterador_local.hasNext()) {
        idGrupoInformacion_local = ((GrupoInformacion)iterador_local.next()).getIdGrupoInformacion();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaGrupoInformacion_local = null;
      iterador_local = null;
    } 
    
    return idGrupoInformacion_local;
  }
  public String obtenerNombrePrimerCampoValorUnicoGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    String nombreCampo_local = "";
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return nombreCampo_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        nombreCampo_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
      } else {
        nombreCampo_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
      } 
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLNombrePrimerCampoValorUnicoGrupoInformacionMultiple(pGrupoInformacion.getIdGrupoInformacion());
      } else {
        consulta_local = ca.consultaSQLNombrePrimerCampoValorUnicoGruposInformacionNoMultiple(pGrupoInformacion.getAplicacion().getIdAplicacion());
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          nombreCampo_local = resultSet_local.getString("fldnombrecampo");
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
    
    return nombreCampo_local;
  }
  private String obtenerConsultaGrupoInformacionAdministrador(int pIdAplicacion, int pIdGrupoInformacion) {
    String consultaGrupoInformacionAdministrador_local = "";
    
    try {
      switch (pIdGrupoInformacion) {
        case 2:
          consultaGrupoInformacionAdministrador_local = ca.consultaSQLGruposInformacionAplicacion(pIdAplicacion);
          break;
        case 3:
          consultaGrupoInformacionAdministrador_local = ca.consultaSQLTablasAplicacion();
          break;
        case 5:
          consultaGrupoInformacionAdministrador_local = ca.consultaSQLUsuarios();
          break;
        case 9:
          consultaGrupoInformacionAdministrador_local = ca.consultaSQLTiposUsuario();
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return consultaGrupoInformacionAdministrador_local;
  }
  public String conformarConsultaSQLSeleccionGrupoInformacion(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, boolean pEsConfiguracion) {
    String consultaSQLSeleccionGrupoInformacion_local = "";
    String nombreLlavePrimaria = null;
    String campos_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return consultaSQLSeleccionGrupoInformacion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return consultaSQLSeleccionGrupoInformacion_local;
    }
    
    try {
      consultaSQLSeleccionGrupoInformacion_local = "select ";
      if (pEsConfiguracion) {
        consultaSQLSeleccionGrupoInformacion_local = obtenerConsultaGrupoInformacionAdministrador(pGrupoInformacion.getAplicacion().getIdAplicacion(), pGrupoInformacion.getIdGrupoInformacion());
      }
      else {
        
        nombreLlavePrimaria = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
        campos_local = "";
        iterador_local = pListaCampo.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          campos_local = mc.concatenarCadena(campos_local, campo_local.getNombreCampo());
          if (iterador_local.hasNext()) {
            campos_local = mc.concatenarCadena(campos_local, String.valueOf(','));
          }
        } 
        consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, campos_local);
        consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, " from " + pGrupoInformacion.getNombreGrupoInformacion() + " order by " + nombreLlavePrimaria);
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria = null;
      iterador_local = null;
      campos_local = null;
      campo_local = null;
    } 
    
    return consultaSQLSeleccionGrupoInformacion_local;
  }
  public String conformarConsultaSQLSeleccionGrupoInformacionAplicacion(GrupoInformacion pGrupoInformacion, ListaCadenas pListaNombresCampos, int pValorLlaveForanea) {
    String consultaSQLSeleccionGrupoInformacion_local = "";
    GrupoInformacion grupoInformacionPrincipal_local = null;
    String campos_local = null;
    String nombreLlavePrimaria = null;
    String nombreLlaveForanea_local = null;
    Iterator iterador_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return consultaSQLSeleccionGrupoInformacion_local;
    }
    if (pListaNombresCampos == ConstantesGeneral.VALOR_NULO) {
      return consultaSQLSeleccionGrupoInformacion_local;
    }
    
    try {
      consultaSQLSeleccionGrupoInformacion_local = "select ";
      campos_local = "";
      nombreLlavePrimaria = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
      grupoInformacionPrincipal_local = obtenerGrupoInformacionPrincipalAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion());
      nombreLlaveForanea_local = ap.conformarNombreCampoLlavePrimaria(grupoInformacionPrincipal_local.getNombreGrupoInformacion());
      iterador_local = pListaNombresCampos.iterator();
      consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, nombreLlavePrimaria);
      if (pListaNombresCampos.contarElementos() > 0) {
        consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, String.valueOf(','));
      }
      
      while (iterador_local.hasNext()) {
        campos_local = mc.concatenarCadena(campos_local, (String)iterador_local.next());
        if (iterador_local.hasNext()) {
          campos_local = mc.concatenarCadena(campos_local, String.valueOf(','));
        }
      } 
      consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, campos_local);
      consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, " from " + pGrupoInformacion.getNombreGrupoInformacion());
      
      if (pValorLlaveForanea != -1) {
        consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, " where " + nombreLlaveForanea_local + '=' + pValorLlaveForanea);
      }
      
      consultaSQLSeleccionGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionGrupoInformacion_local, " order by " + obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pGrupoInformacion));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campos_local = null;
      iterador_local = null;
      nombreLlavePrimaria = null;
      nombreLlaveForanea_local = null;
      grupoInformacionPrincipal_local = null;
    } 
    
    return consultaSQLSeleccionGrupoInformacion_local;
  }
  public String conformarConsultaSQLSeleccionRegistroGrupoInformacion(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, int pValorLlavePrimaria) {
    String consultaSQLSeleccionRegistroGrupoInformacion_local = "";
    String nombreIdentificadorGrupoInformacion_local = null;
    String campos_local = null;
    String nombrecampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return consultaSQLSeleccionRegistroGrupoInformacion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return consultaSQLSeleccionRegistroGrupoInformacion_local;
    }
    
    try {
      consultaSQLSeleccionRegistroGrupoInformacion_local = "select ";
      campos_local = "";
      iterador_local = pListaCampo.iterator();
      nombreIdentificadorGrupoInformacion_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        nombrecampo_local = campo_local.getNombreCampo();
        if (!mc.sonCadenasIgualesIgnorarMayusculas(nombrecampo_local, "FLDPLANTILLAUTILIZAR") && !mc.sonCadenasIgualesIgnorarMayusculas(nombrecampo_local, "fldconfirmarcontrasena")) {
          
          if (!mc.sonCadenasIguales(campos_local, "")) {
            campos_local = mc.concatenarCadena(campos_local, String.valueOf(','));
          }
          campos_local = mc.concatenarCadena(campos_local, nombrecampo_local);
        } 
      } 
      consultaSQLSeleccionRegistroGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionRegistroGrupoInformacion_local, campos_local);
      consultaSQLSeleccionRegistroGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionRegistroGrupoInformacion_local, " from " + pGrupoInformacion.getNombreGrupoInformacion());
      
      consultaSQLSeleccionRegistroGrupoInformacion_local = mc.concatenarCadena(consultaSQLSeleccionRegistroGrupoInformacion_local, " where " + nombreIdentificadorGrupoInformacion_local + '=' + pValorLlavePrimaria);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      campos_local = null;
      iterador_local = null;
      nombrecampo_local = null;
      nombreIdentificadorGrupoInformacion_local = null;
    } 
    
    return consultaSQLSeleccionRegistroGrupoInformacion_local;
  }
  private Aplicacion obtenerAplicacionValoresConsulta(ResultSet pResultSet, boolean pEsCargaMotor) {
    Aplicacion aplicacion_local = null;
    
    if (pResultSet == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    
    try {
      aplicacion_local = new Aplicacion();
      aplicacion_local.setIdAplicacion(pResultSet.getInt("fldidaplicacion"));
      aplicacion_local.setNombreAplicacion(pResultSet.getString("fldnombreaplicacion"));
      aplicacion_local.setTituloAplicacion(pResultSet.getString("fldtituloaplicacion"));
      aplicacion_local.setEsTabla(pResultSet.getBoolean("fldestabla"));
      aplicacion_local.setIdAplicacionConsulta(pResultSet.getInt("fldidaplicacionconsulta"));
      aplicacion_local.setIdAplicacionReporte(pResultSet.getInt("fldidaplicacionreporte"));
      aplicacion_local.setIdAplicacionImpresionMasiva(pResultSet.getInt("fldidaplicacionimpresionmasiva"));
      if (!pEsCargaMotor) {
        aplicacion_local.setAplicacionConsulta(obtenerAplicacionPorId(pResultSet.getInt("fldidaplicacionconsulta"), pEsCargaMotor));
        
        aplicacion_local.setAplicacionReporte(obtenerAplicacionPorId(pResultSet.getInt("fldidaplicacionreporte"), pEsCargaMotor));
        
        aplicacion_local.setAplicacionImpresionMasiva(obtenerAplicacionPorId(pResultSet.getInt("fldidaplicacionimpresionmasiva"), pEsCargaMotor));
      } 
      
      aplicacion_local.setActualizarInformacionEnlazada(pResultSet.getBoolean("fldactualizarinformacionenlazada"));
      aplicacion_local.setAplicacionesActualizar(pResultSet.getString("fldaplicacionesactualizar"));
      aplicacion_local.setAdvertenciaEjecucion(pResultSet.getBoolean("fldadvertenciaejecucion"));
      aplicacion_local.setPermitirConsultaGeneral(pResultSet.getBoolean("fldpermitirconsultageneral"));
      aplicacion_local.setTamanoMaximoArchivos(pResultSet.getInt("fldtamanomaximoarchivos"));
      aplicacion_local.setEsOculta(pResultSet.getBoolean("fldesoculta"));
      aplicacion_local.setHacerDobleCalculo(pResultSet.getBoolean("fldhacerdoblecalculo"));
      aplicacion_local.setNumeroRegistrosPagina(pResultSet.getInt("fldnumeroregistrospagina"));
      aplicacion_local.setTipoEventosUsuario(pResultSet.getString("fldtipoeventosusuario"));
      aplicacion_local.setEventosAcciones(pResultSet.getString("fldeventosacciones"));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return aplicacion_local;
  }
  public Aplicacion obtenerAplicacionPorId(int pIdAplicacion, boolean pEsCargaMotor) {
    Aplicacion aplicacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLAplicacionPorId(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          aplicacion_local = obtenerAplicacionValoresConsulta(resultSet_local, pEsCargaMotor);
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
    return aplicacion_local;
  }
  public int obtenerIdPrimeraAplicacion(int pTipoSeleccionAplicacion, int pIdAplicacion) {
    int idAplicacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLAplicaciones(pTipoSeleccionAplicacion, pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          idAplicacion_local = resultSet_local.getInt("fldidaplicacion");
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
    
    return idAplicacion_local;
  }
  public int obtenerAnchoCamposDocumento(int pIdGrupoInformacion) {
    int anchoCamposDocumento_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLNumeroCamposDocumento(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          anchoCamposDocumento_local = resultSet_local.getInt("fldnumerocamposdocumentos");
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
    
    return anchoCamposDocumento_local * 57;
  }
  public int obtenerAnchoCamposArchivo(int pIdGrupoInformacion) {
    int anchoCamposArchivo_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLNumeroCamposArchivo(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          anchoCamposArchivo_local = resultSet_local.getInt("fldnumerocampos");
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
    
    return anchoCamposArchivo_local * 57;
  }
  public GrupoInformacion obtenerGrupoInformacionPrincipalAplicacion(int pIdAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLGrupoInformacionPrincipal(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          grupoInformacion_local = obtenerGrupoInformacionValoresConsulta(resultSet_local, false);
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
    
    return grupoInformacion_local;
  }
  public int cambiarNombreGrupoInformacionBaseDatosAplicacion(String pNombreGrupoInformacion, String pNuevoNombreGrupoInformacion) {
    int cambiarNombreGrupoInformacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return cambiarNombreGrupoInformacion_local;
    }
    if (pNuevoNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return cambiarNombreGrupoInformacion_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCambiarNombreGrupoInformacion(pNombreGrupoInformacion, pNuevoNombreGrupoInformacion);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      cambiarNombreGrupoInformacion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (cambiarNombreGrupoInformacion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return cambiarNombreGrupoInformacion_local;
  }
  public int cambiarNombreCampoGrupoInformacionBaseDatosAplicacion(String pNombreGrupoInformacion, String pNombreCampo, String pNuevoNombreCampo) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNuevoNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.obtenerCadenaSQLCambiarNombreCampo(pNombreGrupoInformacion, pNombreCampo, pNuevoNombreCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return errorEjecucion_local;
  }
  private String conformarCadenaSQLInsercion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria, ListaCampo pListaCampo) {
    String cadenaSQLInsercion_local = "";
    int consecutivo_local = 0;
    boolean esLlaveForanea_local = false;
    boolean tieneEtiqueta_local = false;
    boolean esValorCampoNulo_local = false;
    String nombreGrupoInformacion_local = null;
    String tipoDato_local = null;
    String valorCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return cadenaSQLInsercion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return cadenaSQLInsercion_local;
    }
    
    try {
      cadenaSQLInsercion_local = ca.obtenerCadenaSQLInsertarRegistroAplicacion(pListaCampo, pGrupoInformacion.getNombreGrupoInformacion());
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
        valorCampo_local = "";
        if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
          valorCampo_local = campo_local.getValorCampo().toString();
        }
        esLlaveForanea_local = campo_local.getRestriccionCampo().esLlaveForanea();
        tieneEtiqueta_local = !mc.sonCadenasIguales(campo_local.getEtiquetaCampo(), "");
        if (!mc.sonCadenasIguales(tipoDato_local, "QQ")) {
          nombreGrupoInformacion_local = pGrupoInformacion.getNombreGrupoInformacion();
          if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
            nombreGrupoInformacion_local = pGrupoInformacion.getAplicacion().getNombreAplicacion();
          }
          if (mc.sonCadenasIgualesIgnorarMayusculas(ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local), campo_local.getNombreCampo())) {
            
            consecutivo_local = obtenerConsecutivoTabla(nombreGrupoInformacion_local, ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local));
            
            valorCampo_local = String.valueOf(consecutivo_local);
          }
          else if (mc.sonCadenasIguales(tipoDato_local, "B")) {
            valorCampo_local = String.valueOf(mc.sonCadenasIguales(valorCampo_local, mc.convertirAMayusculas("true")));
          } 
          
          if (mc.sonCadenasIguales(valorCampo_local, "") && (
            campo_local.esTipoDatoNumerico() || mc.sonCadenasIguales(tipoDato_local, "F") || mc.sonCadenasIguales(tipoDato_local, "H") || campo_local.esTipoDatoTabla()))
          {
            valorCampo_local = "null";
          }
          
          esValorCampoNulo_local = (mc.sonCadenasIguales(valorCampo_local, "") || mc.sonCadenasIgualesIgnorarMayusculas(valorCampo_local, "null"));
          
          if (campo_local.esTipoDatoTexto() && !esLlaveForanea_local) {
            valorCampo_local = mc.borrarEspaciosLaterales(valorCampo_local);
            if (!esValorCampoNulo_local || mc.sonCadenasIguales(valorCampo_local, "")) {
              if (mc.sonCadenasIguales(tipoDato_local, "F")) {
                valorCampo_local = mc.convertirFormatoFechaDDMMAAAA(valorCampo_local);
              }
              valorCampo_local = mc.reemplazarCadena(valorCampo_local, "'", "’");
              cadenaSQLInsercion_local = mc.concatenarCadena(cadenaSQLInsercion_local, mc.colocarEntreComillas(valorCampo_local));
            } else {
              cadenaSQLInsercion_local = mc.concatenarCadena(cadenaSQLInsercion_local, valorCampo_local);
            } 
          } else {
            if (esLlaveForanea_local && !tieneEtiqueta_local) {
              valorCampo_local = String.valueOf(pValorLlavePrimaria);
            }
            cadenaSQLInsercion_local = mc.concatenarCadena(cadenaSQLInsercion_local, valorCampo_local);
          } 
          if (iterator_local.hasNext()) {
            cadenaSQLInsercion_local = mc.concatenarCadena(cadenaSQLInsercion_local, String.valueOf(','));
          }
        } 
      } 
      cadenaSQLInsercion_local = mc.concatenarCadena(cadenaSQLInsercion_local, String.valueOf(')'));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      tipoDato_local = null;
      iterator_local = null;
      valorCampo_local = null;
      nombreGrupoInformacion_local = null;
    } 
    
    return cadenaSQLInsercion_local;
  }
  private String conformarCadenaSQLInsercionValorTabla(String pNombreTabla, String pValorTabla) {
    String cadenaSQLInsercionValorTabla_local = "";
    int valorLlavePrimaria_local = -1;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return cadenaSQLInsercionValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO) {
      return cadenaSQLInsercionValorTabla_local;
    }
    
    try {
      cadenaSQLInsercionValorTabla_local = ca.obtenerCadenaSQLInsertarRegistroValorTabla(pNombreTabla);
      valorLlavePrimaria_local = obtenerConsecutivoTabla(pNombreTabla, ap.conformarNombreCampoLlavePrimaria(pNombreTabla));
      cadenaSQLInsercionValorTabla_local = mc.concatenarCadena(cadenaSQLInsercionValorTabla_local, String.valueOf(valorLlavePrimaria_local) + String.valueOf(','));
      
      cadenaSQLInsercionValorTabla_local = mc.concatenarCadena(cadenaSQLInsercionValorTabla_local, mc.colocarEntreComillas(mc.convertirAMayusculas(pValorTabla)));
      
      cadenaSQLInsercionValorTabla_local = mc.concatenarCadena(cadenaSQLInsercionValorTabla_local, String.valueOf(')'));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadenaSQLInsercionValorTabla_local;
  }
  private String conformarCadenaSQLModificacion(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, int pValorLlavePrimaria) {
    String cadenaSQLModificacion_local = "";
    String valorCampo_local = null;
    String tipoDato_local = null;
    String nombreCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return cadenaSQLModificacion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return cadenaSQLModificacion_local;
    }
    
    try {
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
        nombreCampo_local = campo_local.getNombreCampo();
        
        if (!campo_local.getRestriccionCampo().esLlavePrimaria() && (campo_local.esModificable() || mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "W"))) {
          
          valorCampo_local = "";
          if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
            valorCampo_local = campo_local.getValorCampo().toString();
          }
          
          if (mc.sonCadenasIguales(cadenaSQLModificacion_local, "")) {
            cadenaSQLModificacion_local = ca.obtenerCadenaSQLModificarRegistroAplicacion(pGrupoInformacion.getNombreGrupoInformacion());
          } else {
            cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, String.valueOf(','));
          } 
          if (mc.sonCadenasIguales(tipoDato_local, "B")) {
            valorCampo_local = String.valueOf(mc.sonCadenasIguales(valorCampo_local, mc.convertirAMayusculas("true")));
          }
          cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, nombreCampo_local + '=');
          if (campo_local.esTipoDatoNumerico() && mc.sonCadenasIguales(valorCampo_local, "")) {
            valorCampo_local = "null";
          }
          if (campo_local.esTipoDatoTexto()) {
            valorCampo_local = mc.borrarEspaciosLaterales(valorCampo_local);
            valorCampo_local = mc.reemplazarCadena(valorCampo_local, "'", "''");

            if (mc.sonCadenasIguales(tipoDato_local, "F") || mc.sonCadenasIguales(tipoDato_local, "H")) {
              
              if (mc.sonCadenasIguales(tipoDato_local, "F")) {
                valorCampo_local = mc.convertirFormatoFechaDDMMAAAA(valorCampo_local);
              }
              if (mc.sonCadenasIguales(valorCampo_local, "")) {
                valorCampo_local = "null";
              }
              if (mc.sonCadenasIgualesIgnorarMayusculas(valorCampo_local, "null")) {
                cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, valorCampo_local); continue;
              } 
              cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, mc.colocarEntreComillas(valorCampo_local));
              continue;
            } 
            if (!mc.sonCadenasIgualesIgnorarMayusculas(valorCampo_local, "null")) {
              cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, mc.colocarEntreComillas(valorCampo_local)); continue;
            } 
            cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, valorCampo_local);
            
            continue;
          } 
          if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
            if (!mc.sonCadenasIguales(valorCampo_local, "")) {
              cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, valorCampo_local); continue;
            } 
            cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, "null");
          } 
        } 
      } 
      
      cadenaSQLModificacion_local = mc.concatenarCadena(cadenaSQLModificacion_local, " where " + ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion()) + '=' + pValorLlavePrimaria);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      tipoDato_local = null;
      iterator_local = null;
      valorCampo_local = null;
      nombreCampo_local = null;
    } 
    
    return cadenaSQLModificacion_local;
  }
  public int incluirRegistroAplicacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria, ListaCampo pListaCampo) {
    int registroAplicacion_local = -1;
    String consultaSQLInsertar_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    
    try {
      consultaSQLInsertar_local = conformarCadenaSQLInsercion(pGrupoInformacion, pValorLlavePrimaria, pListaCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consultaSQLInsertar_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registroAplicacion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registroAplicacion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consultaSQLInsertar_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return registroAplicacion_local;
  }
  public int incluirRegistroValorTabla(String pNombreTabla, String pValorTabla) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = conformarCadenaSQLInsercionValorTabla(pNombreTabla, pValorTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarRegistroValorTabla(String pNombreTabla, int pValorLlavePrimaria, String pValorTabla) {
    int registroValorTabla_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return registroValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO) {
      return registroValorTabla_local;
    }
    
    try {
      consulta_local = ca.conformarConsultaSQLModificacionValorTabla(pNombreTabla, pValorLlavePrimaria, pValorTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registroValorTabla_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registroValorTabla_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return registroValorTabla_local;
  }
  public int borrarRegistroValorTabla(String pNombreTabla, int pValorLlavePrimaria) {
    int registroValorTabla_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return registroValorTabla_local;
    }
    
    try {
      consulta_local = ca.conformarConsultaSQLBorrarValorTabla(pNombreTabla, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registroValorTabla_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registroValorTabla_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return registroValorTabla_local;
  }
  public int modificarRegistroAplicacion(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, int pValorLlavePrimaria) {
    int registroAplicacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    
    try {
      if (pListaCampo.contarElementos() == 0) {
        return registroAplicacion_local;
      }
      consulta_local = conformarCadenaSQLModificacion(pGrupoInformacion, pListaCampo, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registroAplicacion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registroAplicacion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return registroAplicacion_local;
  }
  public int borrarRegistroAplicacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    int registroAplicacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    
    try {
      consulta_local = ca.conformarConsultaSQLBorrado(pGrupoInformacion, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registroAplicacion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registroAplicacion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return registroAplicacion_local;
  }
  public int borrarRegistrosGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion) {
    int registroAplicacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    
    try {
      consulta_local = ca.conformarConsultaSQLBorradoRegistrosGrupoInformacionMultiple(pGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registroAplicacion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registroAplicacion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return registroAplicacion_local;
  }
  public Tabla obtenerTablaPorId(int pIdTabla) {
    Tabla tabla_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLTablaPorId(pIdTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          tabla_local = new Tabla();
          tabla_local.setIdTabla(resultSet_local.getInt("fldidtabla"));
          tabla_local.setNombreTabla(resultSet_local.getString("fldnombretabla"));
          tabla_local.setDescripcionTabla(resultSet_local.getString("flddescripciontabla"));
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
    
    return tabla_local;
  }
  public ListaGeneral obtenerListaTablasAplicacion() {
    ListaGeneral listaGeneralTablasAplicacion_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGeneralTablasAplicacion_local = new ListaGeneral();
      consulta_local = ca.consultaSQLTablasAplicacion();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralTablasAplicacion_local.adicionar(resultSet_local.getString("flddescripciontabla"), resultSet_local.getObject("fldidtabla").toString(), false);
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
    
    return listaGeneralTablasAplicacion_local;
  }
  public ListaCampo obtenerListaCamposMismoNombreEnAplicacion(String pNombreCampo, int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      consulta_local = ca.consultaSQLCamposMismoNombreEnAplicacion(pNombreCampo, pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
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
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaGeneral obtenerListaCamposTipoTablaAplicacion(int pIdAplicacion, boolean pOpcionEscojaValor) {
    ListaGeneral listaGeneralCamposAplicacion_local = null;
    String nombreItem_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    ListaGeneral listaTablas_local = null;
    
    try {
      listaGeneralCamposAplicacion_local = new ListaGeneral();
      listaTablas_local = obtenerListaTablasAplicacion();
      if (listaTablas_local.contarElementos() > 0) {
        consulta_local = ca.consultaSQLCamposTipoTablaAplicacion(pIdAplicacion, listaTablas_local);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        if (pOpcionEscojaValor) {
          listaGeneralCamposAplicacion_local.adicionar("Escoja un valor", String.valueOf(0), false);
        }
        
        if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          while (resultSet_local.next()) {
            nombreItem_local = mc.concatenarCadena(resultSet_local.getString("fldetiquetacampo") + ' ', mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")));
            
            listaGeneralCamposAplicacion_local.adicionar(nombreItem_local, resultSet_local.getObject("fldidcampo").toString(), false);
          } 
        } else {
          
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      nombreItem_local = null;
      listaTablas_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaGeneralCamposAplicacion_local;
  }
  private ListaGeneral obtenerListaCamposTipoTablaGrupoInformacion(GrupoInformacion pGrupoInformacion, boolean pGruposMultiples, boolean pOpcionEscojaValor) {
    ListaGeneral listaCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    ListaGeneral listaTablas_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampos_local;
    }
    
    try {
      listaCampos_local = new ListaGeneral();
      listaTablas_local = obtenerListaTablasAplicacion();
      if (!pGrupoInformacion.esGrupoInformacionMultiple() || pGruposMultiples) {
        consulta_local = ca.consultaSQLCamposTipoTablaGruposInformacionAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion(), pGruposMultiples, listaTablas_local);
      } else {
        
        consulta_local = ca.consultaSQLCamposTipoTablaGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), listaTablas_local);
      } 
      if (listaTablas_local.contarElementos() > 0) {
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        if (pOpcionEscojaValor) {
          listaCampos_local.adicionar("Escoja un valor", String.valueOf(0), false);
        }
        
        if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          while (resultSet_local.next()) {
            listaCampos_local.adicionar(resultSet_local.getString("fldetiquetacampo") + ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")), resultSet_local.getObject("fldidcampo").toString(), false);
          }
        }
        else {
          
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      listaTablas_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaCampos_local;
  }
  public ListaGeneral obtenerListaCamposMismoTipoGrupoInformacion(int pIdGrupoInformacion, int pIdCampo, String pTipoDato) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pTipoDato == ConstantesGeneral.VALOR_NULO) {
      return listaGeneralCampos_local;
    }
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      if (mc.sonCadenasIguales(pTipoDato, "T")) {
        consulta_local = ca.consultaSQLCamposGrupoInformacion(pIdGrupoInformacion, false, false);
      } else {
        
        consulta_local = ca.consultaSQLCamposMismoTipoGrupoInformacion(pIdGrupoInformacion, pIdCampo, pTipoDato);
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      listaGeneralCampos_local.adicionar("Escoja un valor", String.valueOf(0), false);
      
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampos_local.adicionar(resultSet_local.getString("fldetiquetacampo"), resultSet_local.getObject("fldidcampo").toString(), false);
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
    
    return listaGeneralCampos_local;
  }
  public ListaGeneral obtenerListaCamposEnlazadosGrupoInformacion(GrupoInformacion pGrupoInformacion, int pIdCampo) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    String valor_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaGeneralCampos_local;
    }
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      consulta_local = ca.consultaSQLCamposEnlazadosGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      listaGeneralCampos_local.adicionar("Escoja un valor", String.valueOf(0), false);
      
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          valor_local = mc.concatenarCadena(resultSet_local.getString("fldetiquetacampo"), ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")));
          
          listaGeneralCampos_local.adicionar(valor_local, resultSet_local.getObject("fldidcampo").toString(), false);
        } 
        
        resultSet_local = null;
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
      consulta_local = ca.consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion(pGrupoInformacion);
      objetoManejadorConsultaSQL_local.setConsultaSQL(consulta_local);
      manejadorConsultaSQL_local.setConsultaSQL(consulta_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          valor_local = mc.concatenarCadena(resultSet_local.getString("fldetiquetacampo"), ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")));
          
          listaGeneralCampos_local.adicionar(valor_local, resultSet_local.getObject("fldidcampo").toString(), false);
        } 
        
        resultSet_local = null;
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
    
    return listaGeneralCampos_local;
  }
  public ListaGeneral obtenerListaCamposEnlazadosAplicacion(int pIdAplicacion) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      consulta_local = ca.consultaSQLCamposEnlazadosAplicacion(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampos_local.adicionar(resultSet_local.getString("fldetiquetacampo"), resultSet_local.getObject("fldidcampo").toString(), false);
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
    
    return listaGeneralCampos_local;
  }
  public ListaGeneral obtenerListaCamposOrigenEnlace(int pIdAplicacion, String pTipoDato) {
    ListaGeneral listaCampos_local = null;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iteradorGrupos_local = null;
    
    if (pTipoDato == ConstantesGeneral.VALOR_NULO) {
      return listaCampos_local;
    }
    
    try {
      listaGrupoInformacion_local = obtenerListaGruposInformacionAplicacion(pIdAplicacion, false);
      iteradorGrupos_local = listaGrupoInformacion_local.iterator();
      if (iteradorGrupos_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iteradorGrupos_local.next();
        listaCampos_local = obtenerListaCamposMismoTipoGrupoInformacion(grupoInformacion_local.getIdGrupoInformacion(), -1, pTipoDato);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iteradorGrupos_local = null;
      grupoInformacion_local = null;
      listaGrupoInformacion_local = null;
    } 
    
    return listaCampos_local;
  }
  public ListaGeneral obtenerListaCamposDependientesCampoMaestro(Campo pCampoMaestro) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampoMaestro == ConstantesGeneral.VALOR_NULO) {
      return listaGeneralCampos_local;
    }
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      if (pCampoMaestro.getGrupoInformacion().esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLCamposListasDependientesCampoMaestro(pCampoMaestro.getIdCampo(), pCampoMaestro.getGrupoInformacion().getIdGrupoInformacion());
      } else {
        
        consulta_local = ca.consultaSQLCamposListasDependientesCampoMaestroGruposNoMultiples(pCampoMaestro.getIdCampo());
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampos_local.adicionar(resultSet_local.getString("fldnombrecampo"), resultSet_local.getObject("fldidcampo").toString(), false);
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
    
    return listaGeneralCampos_local;
  }
  public ListaGeneral obtenerListaCamposHabilitadosPorCampo(int pIdCampo) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      consulta_local = ca.consultaSQLCamposHabilitadosPorCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampos_local.adicionar(resultSet_local.getString("fldnombrecampo"), resultSet_local.getObject("fldidcampo").toString(), false);
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
    
    return listaGeneralCampos_local;
  }
  public boolean verificarCamposMismoGrupo(Campo pCampo, Campo pCampoComparar) {
    boolean camposMismoGrupo_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return camposMismoGrupo_local;
    }
    if (pCampoComparar == ConstantesGeneral.VALOR_NULO) {
      return camposMismoGrupo_local;
    }
    
    try {
      camposMismoGrupo_local = (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple() && !pCampoComparar.getGrupoInformacion().esGrupoInformacionMultiple());
      
      if (!camposMismoGrupo_local) {
        camposMismoGrupo_local = (pCampo.getGrupoInformacion().getIdGrupoInformacion() == pCampoComparar.getGrupoInformacion().getIdGrupoInformacion());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return camposMismoGrupo_local;
  }
  public int borrarRegistrosCamposSisnet(Campo pCampo) {
    int registrosCamposSisnet_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return registrosCamposSisnet_local;
    }
    
    try {
      consulta_local = ca.consultaSQLBorrarRegistrosCamposSisnet(pCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registrosCamposSisnet_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registrosCamposSisnet_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return registrosCamposSisnet_local;
  }
  public int borrarRegistrosCamposGrupoInformacion(int pIdGrupoInformacion) {
    int registrosCamposSisnet_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLBorrarRegistrosCamposGrupoInformacion(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      registrosCamposSisnet_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (registrosCamposSisnet_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return registrosCamposSisnet_local;
  }
  public int obtenerUltimaPosicionGrupoInformacion(int pIdAplicacion) {
    int ultimaPosicionGrupoInformacion_local = 1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLUltimaPosicionGrupoInformacion(pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          ultimaPosicionGrupoInformacion_local = resultSet_local.getInt("ultimaposicion");
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return ultimaPosicionGrupoInformacion_local;
  }
  public int obtenerUltimaPosicionCamposGrupoInformacion(int pIdGrupoInformacion) {
    int ultimaPosicionCampos_local = 1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLUltimaPosicionCamposGrupoInformacion(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          ultimaPosicionCampos_local = resultSet_local.getInt("ultimaposicion");
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return ultimaPosicionCampos_local;
  }
  public int insertarRegistroValorDependiente(int pIdCampo, int pValorMaestro, int pValorDetalle) {
    int errorEjecucion_local = -1;
    int consecutivoValorDependiente_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consecutivoValorDependiente_local = obtenerConsecutivoTabla("VALORDEPENDIENTE", "fldidvalordependiente");
      
      consulta_local = ca.conformarConsultaSQLInsercionValorDependiente(consecutivoValorDependiente_local, pIdCampo, pValorMaestro, pValorDetalle);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int insertarRegistroDependienteHabilitacion(int pIdCampo, int pValorMaestro, boolean pHabilitado) {
    int errorEjecucion_local = -1;
    int consecutivoValorDependiente_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consecutivoValorDependiente_local = obtenerConsecutivoTabla("DEPENDIENTEHABILITACION", "fldiddependientehabilitacion");
      
      consulta_local = ca.conformarConsultaSQLInsercionDependienteHabilitacion(consecutivoValorDependiente_local, pIdCampo, pValorMaestro, pHabilitado);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarRegistroValorDependendencia(int pIdValorDependiente, int pIdValorDetalle) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.conformarConsultaSQLModificacionValorDependencia(pIdValorDependiente, pIdValorDetalle);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarRegistroDependendienteHabilitacion(int pIdDependienteHabilitacion, boolean pHabilitacion) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.conformarConsultaSQLModificacionDependienteHabilitacion(pIdDependienteHabilitacion, pHabilitacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int borrarRegistroValorDependiente(int pIdValorDependiente) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.conformarConsultaSQLBorrarValorDependiente(pIdValorDependiente);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int borrarRegistrosValoresDependientesCampo(int pIdCampo) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.conformarConsultaSQLBorrarValoresDependientesCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int borrarRegistroDependienteHabilitacion(int pIdDependienteHabilitacion) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.conformarConsultaSQLBorrarDependienteHabilitacion(pIdDependienteHabilitacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int borrarRegistrosDependientesHabilitacionCampo(int pIdCampo) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.conformarConsultaSQLBorrarDependientesHabilitacionCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public String obtenerValorTabla(String pNombreTabla, int pIdValor) {
    String valorTabla_local = "";
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return valorTabla_local;
    }
    if (pIdValor == 0) {
      return valorTabla_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValorTabla(pNombreTabla, pIdValor);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          valorTabla_local = resultSet_local.getString(pNombreTabla);
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
    return valorTabla_local;
  }
  public boolean verificarExistenciaValorTabla(String pNombreTabla, String pValorTabla) {
    boolean existeValorTabla_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return existeValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO) {
      return existeValorTabla_local;
    }
    
    try {
      consulta_local = ca.consultaSQLIdValorTabla(pNombreTabla, pValorTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeValorTabla_local = resultSet_local.next();
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
    return existeValorTabla_local;
  }
  public int obtenerIdValorTabla(String pNombreTabla, String pValorTabla) {
    int idValorTabla_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return idValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO) {
      return idValorTabla_local;
    }
    
    try {
      consulta_local = ca.consultaSQLIdValorTabla(pNombreTabla, mc.convertirAMayusculas(pValorTabla));
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          idValorTabla_local = resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(pNombreTabla));
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
    
    return idValorTabla_local;
  }
  public ListaGeneral obtenerListaValoresTabla(String pNombreTabla, int pIdValorSeleccionado, ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones, boolean pEsOpcionConsulta) {
    ListaGeneral listaValoresTabla_local = null;
    int idValorTabla_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    String valorTabla_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return listaValoresTabla_local;
    }
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return listaValoresTabla_local;
    }
    
    try {
      consulta_local = ca.conformarConsultaSQLSeleccionTabla(pNombreTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        listaValoresTabla_local = new ListaGeneral();
        if (pEsOpcionConsulta) {
          listaValoresTabla_local.adicionar("TODOS", String.valueOf(-1), (-1 == pIdValorSeleccionado));
        }
        else {
          
          listaValoresTabla_local.adicionar("Escoja un valor", String.valueOf(0), (0 == pIdValorSeleccionado));
        } 
        
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          idValorTabla_local = resultSet_local.getInt("fldid" + pNombreTabla);
          valorTabla_local = mc.borrarEspaciosLaterales(resultSet_local.getString(pNombreTabla));
          if (pExistenRestricciones) {
            if (pExcluirValores) {
              if (!pListaCadenas.verificarExistenciaCadena(valorTabla_local)) {
                listaValoresTabla_local.adicionar(valorTabla_local, String.valueOf(idValorTabla_local), (idValorTabla_local == pIdValorSeleccionado));
              }
              continue;
            } 
            if (pListaCadenas.verificarExistenciaCadena(valorTabla_local)) {
              listaValoresTabla_local.adicionar(valorTabla_local, String.valueOf(idValorTabla_local), (idValorTabla_local == pIdValorSeleccionado));
            }
            
            continue;
          } 
          listaValoresTabla_local.adicionar(valorTabla_local, String.valueOf(idValorTabla_local), (idValorTabla_local == pIdValorSeleccionado));
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
      valorTabla_local = null;
    } 
    return listaValoresTabla_local;
  }
  public ListaGeneral obtenerListaValoresPlantillasOReportes(String pNombreGrupoPrincipal, String pNombreCampo, String pCampoLlavePrimaria, int pValorSeleccionado, boolean pEsPlantilla) {
    ListaGeneral listaValores_local = null;
    String valor_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreGrupoPrincipal == ConstantesGeneral.VALOR_NULO) {
      return listaValores_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return listaValores_local;
    }
    if (pCampoLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return listaValores_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValoresAplicacionRelacionada(pNombreGrupoPrincipal, pNombreCampo, pCampoLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        listaValores_local = new ListaGeneral();
        if (pEsPlantilla) {
          listaValores_local.adicionar("SIN PLANTILLA", String.valueOf(0), false);
        }
        
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          valor_local = resultSet_local.getObject(pCampoLlavePrimaria).toString();
          listaValores_local.adicionar(resultSet_local.getObject(pNombreCampo).toString(), valor_local, (Integer.parseInt(valor_local) == pValorSeleccionado));
        } 
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      valor_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaValores_local;
  }
  public ListaGeneral obtenerListaIdValoresMaestroCampo(int pIdCampo) {
    ListaGeneral listaIdValoresMaestro_local = null;
    int idValorMaestro_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLIdValoresMaestroCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        listaIdValoresMaestro_local = new ListaGeneral();
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          idValorMaestro_local = resultSet_local.getInt("fldidvalormaestro");
          listaIdValoresMaestro_local.adicionar("", String.valueOf(idValorMaestro_local), false);
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
    
    return listaIdValoresMaestro_local;
  }
  public ListaGeneral obtenerListaIdValoresDetalleDeMaestro(int pIdCampo, int pIdValorMaestro, int pIdValorSeleccionado) {
    ListaGeneral listaIdValoresDetalle_local = null;
    int idValorDetalle_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLIdValoresDetalleDeMaestro(pIdCampo, pIdValorMaestro);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        listaIdValoresDetalle_local = new ListaGeneral();
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (pIdValorSeleccionado == 0) {
          listaIdValoresDetalle_local.adicionar("Escoja un valor", String.valueOf(0), false);
        }
        
        while (resultSet_local.next()) {
          idValorDetalle_local = resultSet_local.getInt("fldidvalordetalle");
          listaIdValoresDetalle_local.adicionar("", String.valueOf(idValorDetalle_local), (idValorDetalle_local == pIdValorSeleccionado));
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
    
    return listaIdValoresDetalle_local;
  }
  public ListaGeneral obtenerListaValoresHabilitadosCampo(int pIdCampo) {
    ListaGeneral listaValoresHabilitados_local = null;
    int idValorMaestro_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLValoresHabilitadosCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        listaValoresHabilitados_local = new ListaGeneral();
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          idValorMaestro_local = resultSet_local.getInt("fldidvalormaestro");
          listaValoresHabilitados_local.adicionar("", String.valueOf(idValorMaestro_local), false);
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
    
    return listaValoresHabilitados_local;
  }
  public boolean verificarGrupoInformacionContieneCampoDocumento(GrupoInformacion pGrupoInformacion) {
    boolean grupoInformacionContieneCampoDocumento_local = false;
    int numeroCamposDocumentos_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacionContieneCampoDocumento_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLNumeroCamposDocumento(pGrupoInformacion.getIdGrupoInformacion());
      } else {
        consulta_local = ca.consultaSQLNumeroCamposDocumentosGruposNoMultiples(pGrupoInformacion.getAplicacion().getIdAplicacion());
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          numeroCamposDocumentos_local = resultSet_local.getInt("fldnumerocamposdocumentos");
          grupoInformacionContieneCampoDocumento_local = (numeroCamposDocumentos_local > 0);
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
    
    return grupoInformacionContieneCampoDocumento_local;
  }
  public boolean verificarGrupoInformacionContieneCampoArchivo(GrupoInformacion pGrupoInformacion) {
    boolean grupoInformacionContieneCampoArchivo_local = false;
    int numeroCamposArchivo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacionContieneCampoArchivo_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLNumeroCamposArchivo(pGrupoInformacion.getIdGrupoInformacion());
      } else {
        consulta_local = ca.consultaSQLNumeroCamposArchivoGruposNoMultiples(pGrupoInformacion.getAplicacion().getIdAplicacion());
      } 
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          numeroCamposArchivo_local = resultSet_local.getInt("fldnumerocampos");
          grupoInformacionContieneCampoArchivo_local = (numeroCamposArchivo_local > 0);
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
    
    return grupoInformacionContieneCampoArchivo_local;
  }
  private boolean verificarGrupoInformacionContieneCamposVisibles(int pIdGrupoInformacion) {
    boolean grupoInformacionContieneCampoVisible_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLVerificarCamposVisibles(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          int numeroCamposVisibles_local = resultSet_local.getInt("fldnumerocamposcamposvisiblesusuario");
          grupoInformacionContieneCampoVisible_local = (numeroCamposVisibles_local > 0);
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
    
    return grupoInformacionContieneCampoVisible_local;
  }
  public int obtenerPosicionGrupoInformacion(int pIdGrupoInformacion) {
    int posicionGrupoInformacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLPosicionGrupoInformacion(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          posicionGrupoInformacion_local = resultSet_local.getInt("fldposicion");
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
    
    return posicionGrupoInformacion_local;
  }
  public void actualizarPosicionesGruposInformacionAplicacion(GrupoInformacion pGrupoInformacion) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      consulta_local = ca.consultaSQLActualizarPosicionesGruposInformacionAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion(), pGrupoInformacion.getPosicion());
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public void actualizarGrupoInformacionIncrementar(int pIdGrupoInformacion) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLActualizarGrupoInformacionIncrementar(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public void actualizarCampoIncrementar(int pIdCampo) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLActualizarCampoIncrementar(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public void actualizarGrupoInformacionDecrementar(int pIdGrupoInformacion) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLActualizarGrupoInformacionDecrementar(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public void actualizarCampoDecrementar(int pIdCampo) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLActualizarCampoDecrementar(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public int obtenerIdGrupoInformacionSiguiente(int pPosicionActualGrupoInformacion, int pIdAplicacion) {
    int idGrupoInformacionSiguiente_local = -1;
    int posicionGrupoInformacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      posicionGrupoInformacion_local = pPosicionActualGrupoInformacion + 1;
      consulta_local = ca.consultaSQLGrupoInformacionPorPosicion(posicionGrupoInformacion_local, pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          idGrupoInformacionSiguiente_local = resultSet_local.getInt("fldidgrupoinformacion");
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
    
    return idGrupoInformacionSiguiente_local;
  }
  public int obtenerIdGrupoInformacionAnterior(int pPosicionActualGrupoInformacion, int pIdAplicacion) {
    int idGrupoInformacionAnterior_local = -1;
    int posicionGrupoInformacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      posicionGrupoInformacion_local = pPosicionActualGrupoInformacion - 1;
      consulta_local = ca.consultaSQLGrupoInformacionPorPosicion(posicionGrupoInformacion_local, pIdAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          idGrupoInformacionAnterior_local = resultSet_local.getInt("fldidgrupoinformacion");
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
    
    return idGrupoInformacionAnterior_local;
  }
  public int obtenerUltimaPosicionCampo(int pIdGrupoInformacion) {
    int ultimaPosicionGrupoInformacion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLUltimaPosicionCampo(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          ultimaPosicionGrupoInformacion_local = resultSet_local.getInt("ultimaposicion");
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
    
    return ultimaPosicionGrupoInformacion_local;
  }
  public int obtenerPosicionCampo(int pIdCampo) {
    int posicionCampo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLPosicionCampo(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          posicionCampo_local = resultSet_local.getInt("fldposicion");
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
    
    return posicionCampo_local;
  }
  public void actualizarPosicionesCamposGrupoInformacion(Campo pCampo) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      consulta_local = ca.consultaSQLActualizarPosicionesCamposGrupoInformacion(pCampo.getGrupoInformacion().getIdGrupoInformacion(), pCampo.getPosicion());
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public int obtenerIdCampoSiguiente(int pPosicionActualCampo, int pIdGrupoInformacion) {
    int idCampoSiguiente_local = -1;
    int posicionCampo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      posicionCampo_local = pPosicionActualCampo + 1;
      consulta_local = ca.consultaSQLCampoPorPosicion(posicionCampo_local, pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          idCampoSiguiente_local = resultSet_local.getInt("fldidcampo");
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
    
    return idCampoSiguiente_local;
  }
  public int obtenerIdCampoAnterior(int pPosicionActualCampo, int pIdGrupoInformacion) {
    int idCampoAnterior_local = -1;
    int posicionCampo_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      posicionCampo_local = pPosicionActualCampo - 1;
      consulta_local = ca.consultaSQLCampoPorPosicion(posicionCampo_local, pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          idCampoAnterior_local = resultSet_local.getInt("fldidcampo");
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
    
    return idCampoAnterior_local;
  }
  public ListaGeneral obtenerListaRegistrosPrimerCampoValorUnico(String pNombreGrupoPrincipal, String pNombreCampo, int pRegistroSeleccionado, boolean pOpcionDesconocido, ListaCadenas pListaRestricciones, boolean pExcluirValores, boolean pExistenRestricciones, String pCondicionesFiltrado, boolean pEsOpcionConsulta, boolean pEsCampoFiltrado, boolean pEsModificacion) {
    ListaGeneral listaRegistros_local = null;
    int idRegistro_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    String valorCampo_local = null;
    
    if (pNombreGrupoPrincipal == ConstantesGeneral.VALOR_NULO) {
      return listaRegistros_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return listaRegistros_local;
    }
    if (pListaRestricciones == ConstantesGeneral.VALOR_NULO) {
      return listaRegistros_local;
    }
    if (pCondicionesFiltrado == ConstantesGeneral.VALOR_NULO) {
      return listaRegistros_local;
    }
    
    try {
      listaRegistros_local = new ListaGeneral();
      if (pEsOpcionConsulta) {
        listaRegistros_local.adicionar("TODOS", String.valueOf(-1), (-1 == pRegistroSeleccionado));
      }
      else {
        
        listaRegistros_local.adicionar("Escoja un valor", String.valueOf(0), (0 == pRegistroSeleccionado));
      } 
      
      if (pOpcionDesconocido) {
        listaRegistros_local.adicionar("DESCONOCIDO", String.valueOf(-2), (-2 == pRegistroSeleccionado));
      }
      
      if (pEsCampoFiltrado && mc.esCadenaVacia(pCondicionesFiltrado) && pEsModificacion) {
        pCondicionesFiltrado = "fldid" + pNombreGrupoPrincipal;
        pCondicionesFiltrado = mc.concatenarCadena(pCondicionesFiltrado, " = ");
        pCondicionesFiltrado = mc.concatenarCadena(pCondicionesFiltrado, String.valueOf(pRegistroSeleccionado));
      } 
      if (pEsCampoFiltrado && mc.esCadenaVacia(pCondicionesFiltrado)) {
        return listaRegistros_local;
      }
      
      consulta_local = ca.consultaSQLRegistrosCampoValorUnico(pNombreGrupoPrincipal, pNombreCampo, pCondicionesFiltrado);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          idRegistro_local = resultSet_local.getInt("fldid" + pNombreGrupoPrincipal);
          valorCampo_local = mc.borrarEspaciosLaterales(resultSet_local.getString(pNombreCampo));
          if (pExistenRestricciones) {
            if (pExcluirValores) {
              if (!pListaRestricciones.verificarExistenciaCadena(valorCampo_local) || idRegistro_local == pRegistroSeleccionado) {
                listaRegistros_local.adicionar(valorCampo_local, String.valueOf(idRegistro_local), (idRegistro_local == pRegistroSeleccionado));
              }
              continue;
            } 
            if (pListaRestricciones.verificarExistenciaCadena(valorCampo_local) || idRegistro_local == pRegistroSeleccionado) {
              listaRegistros_local.adicionar(valorCampo_local, String.valueOf(idRegistro_local), (idRegistro_local == pRegistroSeleccionado));
            }
            
            continue;
          } 
          listaRegistros_local.adicionar(valorCampo_local, String.valueOf(idRegistro_local), (idRegistro_local == pRegistroSeleccionado));
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
      valorCampo_local = null;
    } 
    
    return listaRegistros_local;
  }
  public Object obtenerValorCampo(Campo pCampo, String pNombreLlave, int pValorIdentificador) {
    Object valorCampo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValorCampo(pCampo, pNombreLlave, pValorIdentificador);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          valorCampo_local = resultSet_local.getObject(pCampo.getNombreCampo());
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
    
    return valorCampo_local;
  }
  public int actualizarValorCampo(Campo pCampo, String pNombreLlave, int pValorIdentificador, int pValorCampo) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLActualizarValorCampo(pCampo, pNombreLlave, pValorIdentificador, String.valueOf(pValorCampo), true);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int actualizarValorCampo(Campo pCampo, String pNombreLlave, int pValorIdentificador, double pValorCampo) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLActualizarValorCampo(pCampo, pNombreLlave, pValorIdentificador, String.valueOf(pValorCampo), true);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int actualizarValorCampo(Campo pCampo, String pNombreLlave, int pValorIdentificador, String pValorCampo) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLActualizarValorCampo(pCampo, pNombreLlave, pValorIdentificador, pValorCampo, false);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public String obtenerValorCampoRegistroPorPosicion(Campo pCampo, int pValorLlavePrincipal, int pPosicionRegistro, String pNombreCampoValorUnico) {
    String valorRegistro_local = "";
    int posicion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorRegistro_local;
    }
    if (pNombreCampoValorUnico == ConstantesGeneral.VALOR_NULO) {
      return valorRegistro_local;
    }
    
    try {
      if (pPosicionRegistro > 0) {
        consulta_local = ca.consultaSQLValoresRegistro(pCampo, pValorLlavePrincipal, pNombreCampoValorUnico);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          while (resultSet_local.next() && posicion_local < pPosicionRegistro) {
            if (posicion_local == pPosicionRegistro - 1 && 
              resultSet_local.getObject(pCampo.getNombreCampo()) != ConstantesGeneral.VALOR_NULO) {
              valorRegistro_local = resultSet_local.getObject(pCampo.getNombreCampo()).toString();
            }
            
            posicion_local++;
          } 
        } else {
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return valorRegistro_local;
  }
  public int obtenerValorLlavePrimariaRegistroPorPosicion(Campo pCampo, int pValorLlavePrincipal, int pPosicionRegistro, String pNombreCampoValorUnico) {
    int valorLlavePrimaria_local = -1;
    int posicion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    if (pNombreCampoValorUnico == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    
    try {
      if (pPosicionRegistro > 0) {
        consulta_local = ca.consultaSQLValoresRegistro(pCampo, pValorLlavePrincipal, pNombreCampoValorUnico);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          while (resultSet_local.next() && posicion_local < pPosicionRegistro) {
            if (posicion_local == pPosicionRegistro - 1 && 
              resultSet_local.getObject(pNombreCampoValorUnico) != ConstantesGeneral.VALOR_NULO) {
              valorLlavePrimaria_local = resultSet_local.getInt(pNombreCampoValorUnico);
            }
            
            posicion_local++;
          } 
        } else {
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return valorLlavePrimaria_local;
  }
  public int obtenerPosicionRegistroGrupoInformacion(Campo pCampo, int pValorIdentificador, int pValorIdentificadorGrupo, String pNombreCampoValorUnico) {
    int posicion_local = 0;
    boolean posicionRegistro_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return posicion_local;
    }
    if (pNombreCampoValorUnico == ConstantesGeneral.VALOR_NULO) {
      return posicion_local;
    }
    
    try {
      posicion_local = 0;
      consulta_local = ca.consultaSQLValoresRegistro(pCampo, pValorIdentificador, pNombreCampoValorUnico);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next() && !posicionRegistro_local) {
          posicionRegistro_local = (resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getNombreGrupoInformacion())) == pValorIdentificadorGrupo);
          
          posicion_local++;
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
    
    return posicion_local;
  }
  public int obtenerCantidadRegistros(Campo pCampo, int pValorLlavePrincipal) {
    int cantidadRegistros_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cantidadRegistros_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValorCampo(pCampo, ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrincipal);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          cantidadRegistros_local++;
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
    
    return cantidadRegistros_local;
  }
  public String obtenerContenidoPlantilla(String pNombreGrupoInformacion, String pCampoLlavePrimaria, int pValorLlavePrimaria, String pCampoContenido) {
    String contenidoPlantilla_local = "";
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contenidoPlantilla_local;
    }
    if (pCampoLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return contenidoPlantilla_local;
    }
    if (pCampoContenido == ConstantesGeneral.VALOR_NULO) {
      return contenidoPlantilla_local;
    }
    
    try {
      consulta_local = ca.consultaSQLContenidoPlantilla(pNombreGrupoInformacion, pCampoLlavePrimaria, pValorLlavePrimaria, pCampoContenido);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next() && 
          resultSet_local.getObject(pCampoContenido) != ConstantesGeneral.VALOR_NULO) {
          contenidoPlantilla_local = resultSet_local.getObject(pCampoContenido).toString();
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
    
    return contenidoPlantilla_local;
  }
  public int actualizarRegistrosCampoDependienteEnlazado(Campo pCampo, String pValorCampo, int pValorEnlace, String pNombreLlavePrincipal, int pValorLlavePrincipal, int pValorLlavePrimaria) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLActualizarValorDependienteEnlazado(pCampo, pValorCampo, pValorEnlace, pNombreLlavePrincipal, pValorLlavePrincipal, pValorLlavePrimaria);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  private boolean obtenerDisponiblidadValorNumerico(Campo pCampo, int pValorIdentificador, int pValorLlavePrimaria) {
    boolean disponibilidadValorNumerico_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return disponibilidadValorNumerico_local;
    }
    
    try {
      consulta_local = ca.consultaSQLVerificaDisponibilidadValorNumerico(pCampo, pValorIdentificador, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        disponibilidadValorNumerico_local = !resultSet_local.next();
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
    
    return disponibilidadValorNumerico_local;
  }
  private boolean obtenerDisponiblidadValorAlfanumerico(Campo pCampo, String pValorIdentificador, int pValorLlavePrimaria) {
    boolean disponibilidadValorAlfanumerico_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return disponibilidadValorAlfanumerico_local;
    }
    if (pValorIdentificador == ConstantesGeneral.VALOR_NULO) {
      return disponibilidadValorAlfanumerico_local;
    }
    
    try {
      consulta_local = ca.consultaSQLVerificaDisponibilidadValorAlfanumerico(pCampo, pValorIdentificador, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        disponibilidadValorAlfanumerico_local = !resultSet_local.next();
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
    
    return disponibilidadValorAlfanumerico_local;
  }
  public int obtenerConsecutivoNumerico(Campo pCampo, String pNombreLlavePrimaria, int pValorLlavePrimaria) {
    int consecutivoNumerico_local = -1;
    int errorEjecucion_local = -1;
    int idUltimoRegistro_local = -1;
    boolean finalizar_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    String nombreGrupoInformacion_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return consecutivoNumerico_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return consecutivoNumerico_local;
    }
    
    try {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consecutivoNumerico_local = 1;
      idUltimoRegistro_local = obtenerIdUltimoRegistro(nombreGrupoInformacion_local, ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local), pNombreLlavePrimaria, pValorLlavePrimaria);
      
      while (idUltimoRegistro_local >= 1 && !finalizar_local) {
        consulta_local = ca.consultaSQLUltimoValorAlfanumericoRegistrado(pCampo, nombreGrupoInformacion_local, idUltimoRegistro_local, pNombreLlavePrimaria, pValorLlavePrimaria);
        
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (errorEjecucion_local == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          if (resultSet_local.next() && 
            resultSet_local.getInt(pCampo.getNombreCampo()) != 0) {
            consecutivoNumerico_local = resultSet_local.getInt(pCampo.getNombreCampo());
            consecutivoNumerico_local++;
            finalizar_local = true;
          } 
          
          if (finalizar_local) {
            while (!obtenerDisponiblidadValorNumerico(pCampo, consecutivoNumerico_local, pValorLlavePrimaria)) {
              consecutivoNumerico_local++;
            }
          }
        } 
        if (!finalizar_local) {
          idUltimoRegistro_local--;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      nombreGrupoInformacion_local = null;
    } 
    
    return consecutivoNumerico_local;
  }
  public String obtenerConsecutivoAlfanumerico(Campo pCampo, String pNombreLlavePrimaria, int pValorLlavePrimaria) {
    String consecutivoAlfanumerico_local = "";
    int errorEjecucion_local = -1;
    int idUltimoRegistro_local = -1;
    boolean finalizar_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    String nombreGrupoInformacion_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return consecutivoAlfanumerico_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return consecutivoAlfanumerico_local;
    }
    
    try {
      consecutivoAlfanumerico_local = String.valueOf(1);
      consecutivoAlfanumerico_local = mc.completarCadena(consecutivoAlfanumerico_local, false, '0', pCampo.getFormatoCampo().getLongitudCampo());
      
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      idUltimoRegistro_local = obtenerIdUltimoRegistro(nombreGrupoInformacion_local, ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local), pNombreLlavePrimaria, pValorLlavePrimaria);
      
      while (idUltimoRegistro_local >= 1) {
        consulta_local = ca.consultaSQLUltimoValorAlfanumericoRegistrado(pCampo, nombreGrupoInformacion_local, idUltimoRegistro_local, pNombreLlavePrimaria, pValorLlavePrimaria);
        
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (errorEjecucion_local == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          if (resultSet_local.next() && 
            resultSet_local.getString(pCampo.getNombreCampo()) != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(resultSet_local.getString(pCampo.getNombreCampo()))) {
            
            consecutivoAlfanumerico_local = resultSet_local.getString(pCampo.getNombreCampo());
            finalizar_local = true;
          } 
          
          if (finalizar_local) {
            consecutivoAlfanumerico_local = ap.obtenerConsecutivoCadena(consecutivoAlfanumerico_local);
            while (!obtenerDisponiblidadValorAlfanumerico(pCampo, consecutivoAlfanumerico_local, pValorLlavePrimaria) && !mc.esCadenaVacia(consecutivoAlfanumerico_local))
            {
              consecutivoAlfanumerico_local = ap.obtenerConsecutivoCadena(consecutivoAlfanumerico_local);
            }
          } 
        } 
        idUltimoRegistro_local--;
        if (finalizar_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      nombreGrupoInformacion_local = null;
    } 
    
    return consecutivoAlfanumerico_local;
  }
  public boolean comprobarCamposEnlazadosGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    boolean camposEnlazados_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return camposEnlazados_local;
    }
    
    try {
      consulta_local = ca.consultaSQLCamposEnlazadosGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), -1);
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion(pGrupoInformacion);
      }
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        camposEnlazados_local = resultSet_local.next();
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
    
    return camposEnlazados_local;
  }
  public int obtenerCantidadRegistrosTabla(String pNombreTabla, String pNombreCampo) {
    int cantidadRegistrosTabla_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return cantidadRegistrosTabla_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return cantidadRegistrosTabla_local;
    }
    
    try {
      consulta_local = ca.consultaSQLCantidadRegistrosTabla(pNombreTabla, pNombreCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          cantidadRegistrosTabla_local = resultSet_local.getInt("cantidadregistrostabla");
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
    
    return cantidadRegistrosTabla_local;
  }
  public int obtenerValorEnlace(Campo pCampo, String pNombreLlavePrimaria, int pValorEnlace) {
    int valorEnlace_local = pValorEnlace;
    String consulta_local = null;
    ResultSet resultSet_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValorEnlace(pCampo, pNombreLlavePrimaria, valorEnlace_local);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next() && 
          mc.esCadenaNumerica(resultSet_local.getString(pCampo.getNombreCampo()), true)) {
          valorEnlace_local = resultSet_local.getInt(pCampo.getNombreCampo());
        }
      } else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      consulta_local = null;
      resultSet_local = null;
      objetoManejadorConsultaSQL_local = null;
      manejadorConsultaSQL_local = null;
    } 
    return valorEnlace_local;
  }
  public Object totalizarCampo(Campo pCampo, String pValorIdentificador, String pNombreCampoReferencia, int pValorLlavePrincipal, boolean pEsModificacion, boolean pEsValorTexto) {
    Object totalCampo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return totalCampo_local;
    }
    if (pNombreCampoReferencia == ConstantesGeneral.VALOR_NULO) {
      return totalCampo_local;
    }
    
    try {
      consulta_local = ca.consultaSQLTotalizarCampo(pCampo, pValorIdentificador, pNombreCampoReferencia, pValorLlavePrincipal, pEsModificacion, pEsValorTexto);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          totalCampo_local = resultSet_local.getObject("totalcampo");
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
    
    return totalCampo_local;
  }
  public ListaGeneral obtenerListaCamposGrupoInformacion(GrupoInformacion pGrupoInformacion, boolean pSoloNumericos, boolean pSoloFechas) {
    ListaGeneral listaGeneralCampo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaGeneralCampo_local;
    }
    
    try {
      listaGeneralCampo_local = new ListaGeneral();
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLCamposGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), pSoloNumericos, pSoloFechas);
      } else {
        consulta_local = ca.consultaSQLCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion(), pSoloNumericos, pSoloFechas);
      } 
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampo_local.adicionar(resultSet_local.getString("fldetiquetacampo") + ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")), resultSet_local.getString("fldidcampo"), false);
        
        }
      }
      else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
      if (pSoloNumericos) {
        listaGeneralCampo_local.concatenarListaGeneral(obtenerListaCamposTipoTablaGrupoInformacion(pGrupoInformacion, false, false));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaGeneralCampo_local;
  }
  public ListaGeneral obtenerListaCamposGruposInformacionNoMultiplesAplicacion(GrupoInformacion pGrupoInformacion, boolean pSoloNumericos, boolean pSoloFechas) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaGeneralCampos_local;
    }
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      consulta_local = ca.consultaSQLCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion(), pSoloNumericos, pSoloFechas);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampos_local.adicionar(resultSet_local.getString("fldetiquetacampo") + ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")), resultSet_local.getString("fldidcampo"), false);
        
        }
      }
      else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
      if (pSoloNumericos) {
        listaGeneralCampos_local.concatenarListaGeneral(obtenerListaCamposTipoTablaGrupoInformacion(pGrupoInformacion, false, false));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaGeneralCampos_local;
  }
  public ListaGeneral obtenerListaCamposGruposInformacionMultiplesAplicacion(GrupoInformacion pGrupoInformacion, boolean pSoloNumericos, boolean pSoloFechas) {
    ListaGeneral listaGeneralCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaGeneralCampos_local;
    }
    
    try {
      listaGeneralCampos_local = new ListaGeneral();
      consulta_local = ca.consultaSQLCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion(), pSoloNumericos, pSoloFechas);
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneralCampos_local.adicionar(resultSet_local.getString("fldetiquetacampo") + ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")), resultSet_local.getString("fldidcampo"), false);
        
        }
      }
      else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
      if (pSoloNumericos) {
        listaGeneralCampos_local.concatenarListaGeneral(obtenerListaCamposTipoTablaGrupoInformacion(pGrupoInformacion, true, false));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaGeneralCampos_local;
  }
  public ListaGeneral obtenerListaCamposAplicacion(int pIdAplicacion, boolean pSoloNumericos, boolean pSoloFechas, boolean pSoloHoras) {
    ListaGeneral listaGeneral_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaGeneral_local = new ListaGeneral();
      consulta_local = ca.consultaSQLCamposAplicacion(pIdAplicacion, pSoloNumericos, pSoloFechas, pSoloHoras);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaGeneral_local.adicionar(resultSet_local.getString("fldetiquetacampo") + ' ' + mc.colocarEntreParentesis(resultSet_local.getString("flddescripciongrupoinformacion")), resultSet_local.getString("fldidcampo"), false);
        
        }
      }
      else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
      if (pSoloNumericos) {
        listaGeneral_local.concatenarListaGeneral(obtenerListaCamposTipoTablaAplicacion(pIdAplicacion, false));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaGeneral_local;
  }
  public boolean verificarEsCampoValor(int pIdCampo) {
    boolean esCampoValor_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pIdCampo == -1) {
      return esCampoValor_local;
    }
    
    try {
      consulta_local = ca.consultaSQLVerificarEsCampoValor(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        esCampoValor_local = resultSet_local.next();
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
    return esCampoValor_local;
  }
  public boolean verificarEsCampoOrigen(int pIdCampo) {
    boolean esCampoValor_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pIdCampo == -1) {
      return esCampoValor_local;
    }
    
    try {
      consulta_local = ca.consultaSQLVerificarEsCampoOrigen(pIdCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        esCampoValor_local = resultSet_local.next();
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
    return esCampoValor_local;
  }
  public int obtenerNumeroCamposExportar(int pIdGrupoInformacion) {
    int numeroCampos_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLNumeroCamposExportar(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          numeroCampos_local = resultSet_local.getInt("fldnumerocampos");
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
    
    return numeroCampos_local;
  }
  public ListaGeneral obtenerListaTiposUsuario() {
    ListaGeneral listaAplicaciones_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaAplicaciones_local = new ListaGeneral();
      consulta_local = ca.consultaSQLListaTiposUsuario();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaAplicaciones_local.adicionar(resultSet_local.getString("fldnombretipousuario"), resultSet_local.getObject("fldidtipousuario").toString(), false);
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
    return listaAplicaciones_local;
  }
  public TipoUsuario obtenerTipoUsuarioPorId(int pIdTipoUsuario) {
    TipoUsuario tipoUsuario_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      if (pIdTipoUsuario == 0) {
        tipoUsuario_local = ap.obtenerTipoUsuarioAdministradorSistema();
        return tipoUsuario_local;
      } 
      if (pIdTipoUsuario == 1000) {
        tipoUsuario_local = ap.obtenerTipoUsuarioLocal();
        return tipoUsuario_local;
      } 
      consulta_local = ca.consultaSQLTipoUsuarioPorId(pIdTipoUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          tipoUsuario_local = new TipoUsuario();
          tipoUsuario_local.setIdTipoUsuario(resultSet_local.getInt("fldidtipousuario"));
          tipoUsuario_local.setNombreTipoUsuario(resultSet_local.getString("fldnombretipousuario"));
          tipoUsuario_local.setPermitirUtilizarMenu(resultSet_local.getBoolean("fldpermitirutilizarmenu"));
          tipoUsuario_local.setNivelAcceso(resultSet_local.getString("fldnivelacceso"));
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
    return tipoUsuario_local;
  }
  private Usuario obtenerUsuarioValoresConsulta(ResultSet pResultSet) {
    Usuario usuario_local = null;
    
    if (pResultSet == ConstantesGeneral.VALOR_NULO) {
      return usuario_local;
    }
    
    try {
      usuario_local = new Usuario();
      usuario_local.setIdUsuario(pResultSet.getInt("fldidusuario"));
      usuario_local.setNombreUsuario(pResultSet.getString("fldnombreusuario"));
      usuario_local.setContrasena(pResultSet.getString("fldcontrasena"));
      usuario_local.setNombreCompletoUsuario(pResultSet.getString("fldnombrecompletousuario"));
      usuario_local.setTipoLicencia(pResultSet.getString("fldtipolicencia"));
      usuario_local.setFechaVencimiento(pResultSet.getDate("fldfechavencimiento"));
      usuario_local.setTipoUsuario(obtenerTipoUsuarioPorId(pResultSet.getInt("fldidtipousuario")));
      usuario_local.setDiasVigenciaContrasena(pResultSet.getInt("flddiasvigenciacontrasena"));
      usuario_local.setContrasenasFallidasPermitidas(pResultSet.getInt("fldcontrasenasfallidaspermitidas"));
      usuario_local.setFechaUltimaContrasenaFallida(pResultSet.getDate("fldfechaultimacontrasenafallida"));
      usuario_local.setCantidadContrasenasFallidas(pResultSet.getInt("fldcantidadcontrasenasfallidas"));
      usuario_local.setTiempoSesion(pResultSet.getInt("fldtiemposesion"));
      usuario_local.setTipoBloqueo(pResultSet.getInt("fldtipobloqueo"));
      usuario_local.setAsignacionAdministrador(pResultSet.getBoolean("fldasignacionadministrador"));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return usuario_local;
  }
  public Usuario obtenerUsuarioPorId(int pIdUsuario) {
    Usuario usuario_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (Integer.valueOf(pIdUsuario) == ConstantesGeneral.VALOR_NULO) {
      return usuario_local;
    }
    
    try {
      consulta_local = ca.consultaSQLUsuarioPorId(pIdUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          usuario_local = obtenerUsuarioValoresConsulta(resultSet_local);
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
    
    return usuario_local;
  }
  public Usuario obtenerUsuarioPorNombre(String pNombreUsuario) {
    Usuario usuario_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return usuario_local;
    }
    
    try {
      consulta_local = ca.consultaSQLUsuarioPorNombre(pNombreUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          usuario_local = obtenerUsuarioValoresConsulta(resultSet_local);
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
    
    return usuario_local;
  }
  private int asignarFechaUltimaContrasenaFallida(String pNombreUsuario) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLAsignarFechaUltimaContrasenaFallida(pNombreUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    return errorEjecucion_local;
  }
  public int asignarCantidadContrasenasFallidas(Usuario pUsuario) {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLAsignarCantidadContrasenasFallidas(pUsuario.getNombreUsuario());
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
        asignarFechaUltimaContrasenaFallida(pUsuario.getNombreUsuario());
        if (pUsuario.getCantidadContrasenasFallidas() >= pUsuario.getContrasenasFallidasPermitidas()) {
          asignarTipoBloqueo(pUsuario.getNombreUsuario(), 3);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int borrarCantidadContrasenasFallidas() {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLBorrarCantidadContrasenasFallidas();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  private int asignarTipoBloqueo(String pNombreUsuario, int pTipoBloqueo) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLAsignarTipoBloqueo(pNombreUsuario, pTipoBloqueo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int asignarBloqueoPorVencimientoContrasena() {
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLAsignarBloqueoPorVencimientoContrasena();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int cambiarNombreUsuario(String pNombreUsuario, String pNuevoNombreUsuario) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNuevoNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLCambiarNombreUsuario(pNombreUsuario, pNuevoNombreUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int cambiarContrasenaUsuario(String pNombreUsuario, String pNuevaContrasena) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pNuevaContrasena == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLCambiarContrasenaUsuario(pNombreUsuario, pNuevaContrasena);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int cambiarContrasenaUsuarioAdministrador() {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLCambiarContrasenaUsuarioAdministrador();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int obtenerIdValorCampoEnlazado(Campo pCampo, String pValorEnlazado, boolean pEsValorNumerico) {
    int idValorCampoEnlazado_local = 0;
    String consulta_local = null;
    String nombreGrupoInformacion_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorCampoEnlazado_local;
    }
    if (pValorEnlazado == ConstantesGeneral.VALOR_NULO) {
      return idValorCampoEnlazado_local;
    }
    
    try {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consulta_local = ca.consultaSQLIdValorCampoEnlazado(pCampo, pValorEnlazado, pEsValorNumerico);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          idValorCampoEnlazado_local = resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local));
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
      nombreGrupoInformacion_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return idValorCampoEnlazado_local;
  }
  public int obtenerIdRegistroValorCampo(Campo pCampo, String pValorCampo, boolean pEsTipoNumerico) {
    int idRegistroValorCampo_local = -1;
    String consulta_local = null;
    String nombreGrupoInformacion_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return idRegistroValorCampo_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return idRegistroValorCampo_local;
    }
    
    try {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consulta_local = ca.consultaSQLIdRegistroValorCampo(pCampo, pValorCampo, pEsTipoNumerico);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          idRegistroValorCampo_local = resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local));
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
      nombreGrupoInformacion_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return idRegistroValorCampo_local;
  }
  public ListaGeneral obtenerListaCampoComoListaGeneral(ListaCampo pListaCampo, String pCampoSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    String idCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("Escoja un valor", String.valueOf(0), false);
      
      return listaGeneral_local;
    } 
    if (pCampoSeleccionado == ConstantesGeneral.VALOR_NULO) {
      return listaGeneral_local;
    }
    
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("Escoja un valor", String.valueOf(0), false);
      
      iterador_local = pListaCampo.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        idCampo_local = String.valueOf(campo_local.getIdCampo());
        listaGeneral_local.adicionar(campo_local.getEtiquetaCampo() + ' ' + mc.colocarEntreParentesis(campo_local.getGrupoInformacion().getDescripcionGrupoInformacion()), idCampo_local, mc.sonCadenasIguales(idCampo_local, pCampoSeleccionado));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      idCampo_local = null;
      iterador_local = null;
    } 
    
    return listaGeneral_local;
  }
  public int obtenerValorLlavePrimariaCampoOrigen(Campo pCampoOrigen, int pValorLlavePrincipal, int pValorLlavePrimaria, boolean pCamposMismoGrupo) {
    int valorLlavePrimaria_local = -1;
    
    if (pCampoOrigen == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    
    try {
      if (pCamposMismoGrupo || pCampoOrigen.getGrupoInformacion().esGrupoInformacionMultiple()) {
        valorLlavePrimaria_local = pValorLlavePrimaria;
      } else {
        valorLlavePrimaria_local = pValorLlavePrincipal;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return valorLlavePrimaria_local;
  }
  public String obtenerValorCampoCalculadoConFormato(Campo pCampo, String pValorCampo, int pOrigenConcatenacion) {
    String valorCampoCalculadoConFormato_local = "";
    int campoCalculado_local = 1;
    boolean esCampoTextoParrafo_local = false;
    boolean conCampoValor_local = false;
    boolean conCamposOrigen_local = false;
    boolean esCampoOrigenUnoFecha_local = false;
    boolean esCampoOrigenDosFecha_local = false;
    boolean noEsConcatenacion_local = false;
    String tipoDatoAuxiliarCampo_local = null;
    String formatoCampo_local = null;
    String formato_local = null;
    String complementoFormato_local = null;
    String estilo_local = null;
    Campo campoOrigen_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculadoConFormato_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculadoConFormato_local;
    }
    
    try {
      valorCampoCalculadoConFormato_local = pValorCampo;
      esCampoTextoParrafo_local = (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "T") || mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "NN"));
      
      campoCalculado_local = pCampo.getCalculoCampo().getCampoCalculado();
      if (esCampoTextoParrafo_local) {
        noEsConcatenacion_local = (campoCalculado_local != 11 && campoCalculado_local != 12 && campoCalculado_local != 21 && campoCalculado_local != 27 && campoCalculado_local != 23 && campoCalculado_local != 24 && campoCalculado_local != 28 && campoCalculado_local != 22);
        
        formatoCampo_local = "";
        conCampoValor_local = (pCampo.getCalculoCampo().getCampoValor() != ConstantesGeneral.VALOR_NULO);
        conCamposOrigen_local = (pCampo.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO && pCampo.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO);
        
        if (noEsConcatenacion_local) {
          formatoCampo_local = pCampo.getCalculoCampo().getFormatoCampoCalculado();
        } else {
          if (pOrigenConcatenacion == 0) {
            formatoCampo_local = pCampo.getCalculoCampo().getFormatoCampoCalculado();
            campoOrigen_local = pCampo;
          } 
          if (pOrigenConcatenacion == 1) {
            formatoCampo_local = pCampo.getCalculoCampo().getFormatoCampoOrigenUno();
            campoOrigen_local = pCampo.getCalculoCampo().getCampoOrigenUno();
          } 
          if (pOrigenConcatenacion == 2) {
            formatoCampo_local = pCampo.getCalculoCampo().getFormatoCampoOrigenDos();
            campoOrigen_local = pCampo.getCalculoCampo().getCampoOrigenDos();
          } 
        } 
        estilo_local = ap.obtenerEstiloValorCampo(formatoCampo_local);
        formato_local = ap.obtenerFormatoValorCampo(formatoCampo_local);
        complementoFormato_local = ap.obtenerComplementoFormatoNumericoCampo(formatoCampo_local);
        
        if (noEsConcatenacion_local) {
          if (conCampoValor_local) {
            valorCampoCalculadoConFormato_local = ap.obtenerValorCampoConFormato(pValorCampo, pCampo.getCalculoCampo().getCampoValor().esTipoDatoNumeroEntero(), pCampo.getCalculoCampo().getCampoValor().esTipoDatoNumeroReal(), pCampo.getCalculoCampo().getCampoValor().esTipoDatoFecha(), pCampo.getCalculoCampo().getCampoValor().esTipoDatoHora(), formato_local, complementoFormato_local);
            
            valorCampoCalculadoConFormato_local = ap.obtenerValorCampoConEstilo(valorCampoCalculadoConFormato_local, estilo_local);
            return valorCampoCalculadoConFormato_local;
          } 
          if (conCamposOrigen_local) {
            esCampoOrigenUnoFecha_local = mc.sonCadenasIguales(pCampo.getCalculoCampo().getCampoOrigenUno().getFormatoCampo().getTipoDato(), "F");
            
            esCampoOrigenDosFecha_local = mc.sonCadenasIguales(pCampo.getCalculoCampo().getCampoOrigenDos().getFormatoCampo().getTipoDato(), "F");
            
            if (pCampo.getCalculoCampo().getCampoCalculado() != 25 && ((esCampoOrigenUnoFecha_local && !esCampoOrigenDosFecha_local) || (!esCampoOrigenUnoFecha_local && esCampoOrigenDosFecha_local))) {
              
              tipoDatoAuxiliarCampo_local = campoOrigen_local.getFormatoCampo().getTipoDato();
              campoOrigen_local.getFormatoCampo().setTipoDato("F");
            }
            else if (campoOrigen_local != ConstantesGeneral.VALOR_NULO) {
              tipoDatoAuxiliarCampo_local = campoOrigen_local.getFormatoCampo().getTipoDato();
              campoOrigen_local.getFormatoCampo().setTipoDato("R");
            } 
            
            if (campoOrigen_local != null) {
              valorCampoCalculadoConFormato_local = ap.obtenerValorCampoConFormato(pValorCampo, campoOrigen_local.esTipoDatoNumeroEntero(), campoOrigen_local.esTipoDatoNumeroReal(), campoOrigen_local.esTipoDatoFecha(), campoOrigen_local.esTipoDatoHora(), formato_local, complementoFormato_local);
            }
            
            valorCampoCalculadoConFormato_local = ap.obtenerValorCampoConEstilo(valorCampoCalculadoConFormato_local, estilo_local);
            if (tipoDatoAuxiliarCampo_local != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(tipoDatoAuxiliarCampo_local))
            {
              campoOrigen_local.getFormatoCampo().setTipoDato(tipoDatoAuxiliarCampo_local);
            }
            return valorCampoCalculadoConFormato_local;
          } 
        } else {
          valorCampoCalculadoConFormato_local = ap.obtenerValorCampoConFormato(pValorCampo, campoOrigen_local.esTipoDatoNumeroEntero(), campoOrigen_local.esTipoDatoNumeroReal(), campoOrigen_local.esTipoDatoFecha(), campoOrigen_local.esTipoDatoHora(), formato_local, complementoFormato_local);
          
          valorCampoCalculadoConFormato_local = ap.obtenerValorCampoConEstilo(valorCampoCalculadoConFormato_local, estilo_local);
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
      formato_local = null;
      campoOrigen_local = null;
      complementoFormato_local = null;
      formatoCampo_local = null;
      tipoDatoAuxiliarCampo_local = null;
    } 
    
    return valorCampoCalculadoConFormato_local;
  }
  public int incluirCampo(Campo pCampo) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      pCampo.validarCampoSegunTipoDato();
      pCampo.validarCampoSegunSelecciones();
      pCampo.setIdCampo(obtenerConsecutivoTabla("CAMPO", "fldidcampo"));
      consulta_local = ca.consultaSQLInsertarCampo(pCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public boolean verificarExistenciaGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    boolean existeGrupoInformacion_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return existeGrupoInformacion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLVerificarExistenciaGrupoInformacion(pGrupoInformacion.getAplicacion().getIdAplicacion(), pGrupoInformacion.getNombreGrupoInformacion());
      
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeGrupoInformacion_local = resultSet_local.next();
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
    
    return existeGrupoInformacion_local;
  }
  public boolean verificarExistenciaGrupoInformacionMultiplePorNombre(String pNombreGrupoInformacion) {
    boolean existeGrupoInformacion_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return existeGrupoInformacion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLGrupoInformacionMultiplePorNombre(pNombreGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeGrupoInformacion_local = resultSet_local.next();
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
    
    return existeGrupoInformacion_local;
  }
  private String conformarNombreGrupoInformacionMultiple(String pNombreGrupoInformacion) {
    String nombreGrupoInformacion_local = "";
    int contador_local = 0;
    String nombreOriginal_local = null;
    String cadenaSubContador_local = null;
    
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return nombreGrupoInformacion_local;
    }
    
    try {
      nombreGrupoInformacion_local = pNombreGrupoInformacion;
      nombreOriginal_local = pNombreGrupoInformacion;
      while (verificarExistenciaGrupoInformacionMultiplePorNombre(nombreGrupoInformacion_local)) {
        contador_local++;
        cadenaSubContador_local = mc.concatenarCadena(String.valueOf('_'), String.valueOf(contador_local));
        nombreGrupoInformacion_local = mc.concatenarCadena(nombreOriginal_local, cadenaSubContador_local);
        if (mc.obtenerLongitudCadena(nombreGrupoInformacion_local) > 25) {
          nombreGrupoInformacion_local = mc.concatenarCadena(mc.obtenerSubCadena(nombreOriginal_local, 0, 25 - mc.obtenerLongitudCadena(cadenaSubContador_local)), cadenaSubContador_local);
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreOriginal_local = null;
      cadenaSubContador_local = null;
    } 
    
    return nombreGrupoInformacion_local;
  }
  public boolean verificarExistenciaTablaPorNombre(String pNombreTabla) {
    boolean existeTabla_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return existeTabla_local;
    }
    
    try {
      consulta_local = ca.consultaSQLTablaPorNombre(pNombreTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeTabla_local = resultSet_local.next();
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
    
    return existeTabla_local;
  }
  public boolean verificarExistenciaAplicacionPorNombre(String pNombreAplicacion) {
    boolean existeAplicacion_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreAplicacion == ConstantesGeneral.VALOR_NULO) {
      return existeAplicacion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLAplicacionPorNombre(pNombreAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        existeAplicacion_local = resultSet_local.next();
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
    
    return existeAplicacion_local;
  }
  public int incluirGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    int errorEjecucion_local = 0;
    boolean crearGrupoInformacion_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      crearGrupoInformacion_local = !verificarExistenciaGrupoInformacion(pGrupoInformacion);
      if (crearGrupoInformacion_local && pGrupoInformacion.esGrupoInformacionMultiple()) {
        crearGrupoInformacion_local = (!verificarExistenciaTablaPorNombre(pGrupoInformacion.getNombreGrupoInformacion()) && !verificarExistenciaAplicacionPorNombre(pGrupoInformacion.getNombreGrupoInformacion()));
        
        if (crearGrupoInformacion_local) {
          pGrupoInformacion.setNombreGrupoInformacion(conformarNombreGrupoInformacionMultiple(pGrupoInformacion.getNombreGrupoInformacion()));
        }
      } 
      
      if (crearGrupoInformacion_local) {
        pGrupoInformacion.setIdGrupoInformacion(obtenerConsecutivoTabla("GRUPOINFORMACION", "fldidgrupoinformacion"));
        
        consulta_local = ca.consultaSQLInsertarGrupoInformacion(pGrupoInformacion);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (errorEjecucion_local != 0) {
          cancelarTransaccion();
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } else {
          confirmarTransaccion();
          if (pGrupoInformacion.esGrupoInformacionMultiple()) {
            incluirCampo(ap.conformarCampoLlavePrimaria(pGrupoInformacion));
            incluirCampo(ap.conformarCampoLlaveForanea(pGrupoInformacion));
          } 
          if (pGrupoInformacion.esGrupoInformacionPrincipal()) {
            incluirCampo(ap.conformarCampoLlavePrimaria(pGrupoInformacion));
          }
        } 
      } else {
        errorEjecucion_local = 29;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  private GrupoInformacion conformarGrupoInformacionPrincipal(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setNombreGrupoInformacion(pAplicacion.getNombreAplicacion());
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setGrupoInformacionPrincipal(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public int incluirAplicacion(Aplicacion pAplicacion) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      if (!verificarExistenciaAplicacionPorNombre(pAplicacion.getNombreAplicacion()) && !verificarExistenciaTablaPorNombre(pAplicacion.getNombreAplicacion()) && !verificarExistenciaGrupoInformacionMultiplePorNombre(pAplicacion.getNombreAplicacion())) {
        
        pAplicacion.setIdAplicacion(obtenerConsecutivoTabla("APLICACION", "fldidaplicacion"));
        consulta_local = ca.consultaSQLInsertarAplicacion(pAplicacion);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (errorEjecucion_local != 0) {
          cancelarTransaccion();
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } else {
          confirmarTransaccion();
          incluirGrupoInformacion(conformarGrupoInformacionPrincipal(pAplicacion));
        } 
      } else {
        errorEjecucion_local = 29;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int incluirTabla(Tabla pTabla) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      if (!verificarExistenciaTablaPorNombre(pTabla.getNombreTabla()) && !verificarExistenciaAplicacionPorNombre(pTabla.getNombreTabla()) && !verificarExistenciaGrupoInformacionMultiplePorNombre(pTabla.getNombreTabla())) {
        
        pTabla.setIdTabla(obtenerConsecutivoTabla("TABLA", "fldidtabla"));
        consulta_local = ca.consultaSQLInsertarTabla(pTabla);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (errorEjecucion_local != 0) {
          cancelarTransaccion();
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } else {
          confirmarTransaccion();
        } 
      } else {
        errorEjecucion_local = 29;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int incluirTipoUsuario(TipoUsuario pTipoUsuario) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pTipoUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      pTipoUsuario.setIdTipoUsuario(obtenerConsecutivoTabla("TIPOUSUARIO", "fldidtipousuario"));
      consulta_local = ca.consultaSQLInsertarTipoUsuario(pTipoUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int incluirUsuario(Usuario pUsuario) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      if (pUsuario.getIdUsuario() != 0) {
        pUsuario.setIdUsuario(obtenerConsecutivoTabla("USUARIO", "fldidusuario"));
      }
      consulta_local = ca.consultaSQLInsertarUsuario(pUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int insertarRegistroUsuarioAdministrador() {
    int errorEjecucion_local = 0;
    Usuario usuario_local = null;
    TipoUsuario tipoUsuario_local = null;
    String contrasena_local = null;
    
    try {
      if (obtenerValorCampo(cad.obtenerCampoIdUsuario(getNombreBaseDatos()), "fldidusuario", 0) == ConstantesGeneral.VALOR_NULO) {
        
        contrasena_local = mc.convertirAMayusculas((new Encriptor("9000135027", false)).encriptarCadena());
        
        tipoUsuario_local = new TipoUsuario();
        tipoUsuario_local.setIdTipoUsuario(0);
        
        usuario_local = new Usuario();
        usuario_local.setIdUsuario(0);
        usuario_local.setNombreUsuario("ADMINISTRADOR");
        usuario_local.setContrasena(contrasena_local);
        usuario_local.setTipoLicencia("W");
        usuario_local.setTipoUsuario(tipoUsuario_local);
        usuario_local.setFechaVencimiento(ConstantesGeneral.const_FechaCaducidadSisnet);
        errorEjecucion_local = incluirUsuario(usuario_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      usuario_local = null;
      contrasena_local = null;
      tipoUsuario_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarAplicacion(Aplicacion pAplicacion) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarAplicacion(pAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarEventosAccionesAplicacion(Aplicacion pAplicacion) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarEventosAccionesAplicacion(pAplicacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarTabla(Tabla pTabla) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarTabla(pTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarGrupoInformacion(pGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarCampo(Campo pCampo) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarCampo(pCampo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarTipoUsuario(TipoUsuario pTipoUsuario) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pTipoUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarTipoUsuario(pTipoUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int modificarUsuario(Usuario pUsuario) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarUsuario(pUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int cargarArchivoABaseDatos(Campo pCampo, String pRutaArchivo, int pValorLlavePrimaria) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    File file_local = null;
    FileInputStream fileInputStream_local = null;
    byte[] byte_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      file_local = new File(pRutaArchivo);
      fileInputStream_local = new FileInputStream(file_local);
      byte_local = new byte[(int)file_local.length()];
      fileInputStream_local.read(byte_local);
      consulta_local = ca.consultaSQLCargarArchivoABaseDatos(pCampo, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      manejadorConsultaSQL_local.setPreparedStatement(getConexionBaseDatos().getConexion().prepareStatement(consulta_local));
      manejadorConsultaSQL_local.getPreparedStatement().setString(1, mc.borrarEspacios(file_local.getName()));
      
      manejadorConsultaSQL_local.getPreparedStatement().setBytes(2, byte_local);
      try {
        manejadorConsultaSQL_local.getPreparedStatement().executeUpdate();
        fileInputStream_local.close();
        manejadorConsultaSQL_local.getPreparedStatement().close();
      } catch (SQLException exception) {
        if (mc.esCadenaNumerica(exception.getSQLState(), true)) {
          errorEjecucion_local = Integer.parseInt(exception.getSQLState());
        }
        exception.printStackTrace();
      } 
      
      if (errorEjecucion_local == 0) {
        confirmarTransaccion();
      } else {
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      byte_local = null;
      file_local = null;
      consulta_local = null;
      fileInputStream_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public boolean descargarArchivoDeBaseDatos(Campo pCampo, String pRutaArchivo, int pValorLlavePrimaria) {
    boolean documentoBaseDatos_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    FileOutputStream fileOutputStream_local = null;
    File file_local = null;
    byte[] byte_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return documentoBaseDatos_local;
    }
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return documentoBaseDatos_local;
    }
    
    try {
      consulta_local = ca.consultaSQLObtenerArchivoDeBaseDatos(pCampo, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          byte_local = resultSet_local.getBytes(pCampo.getNombreCampo());
          if (byte_local != ConstantesGeneral.VALOR_NULO) {
            file_local = new File(pRutaArchivo);
            fileOutputStream_local = new FileOutputStream(file_local);
            fileOutputStream_local.write(byte_local);
            fileOutputStream_local.close();
          } 
        } 
      } else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      file_local = null;
      byte_local = null;
      consulta_local = null;
      resultSet_local = null;
      fileOutputStream_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return documentoBaseDatos_local;
  }
  public String obtenerNombreArchivo(int pValorLlavePrimaria, Campo pCampoArchivo) {
    String nombreArchivo_local = "";
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampoArchivo == ConstantesGeneral.VALOR_NULO) {
      return nombreArchivo_local;
    }
    
    try {
      consulta_local = ca.consultaSQLNombreArchivo(pValorLlavePrimaria, pCampoArchivo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          nombreArchivo_local = resultSet_local.getString(ap.complementarNombreCampoNombreArchivo(pCampoArchivo.getNombreCampo()));
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
    
    return nombreArchivo_local;
  }
  public int asignarValorConsecutivoInternoCampo(Campo pCampo, int pValorLlavePrimariaGrupoInformacion, int pPosicionRegistro) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      consulta_local = ca.consultaSQLAsignarValorConsecutivoInternoCampo(pCampo, pValorLlavePrimariaGrupoInformacion, pPosicionRegistro);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorEjecucion_local != 0) {
        cancelarTransaccion();
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int reorganizarValoresConsecutivoInternoCampo(Campo pCampo, int pValorLlavePrimariaPrincipal, String pNombreCampoValorUnico) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    int consecutivoInterno_local = 0;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLValoresRegistro(pCampo, pValorLlavePrimariaPrincipal, pNombreCampoValorUnico);
        
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          while (resultSet_local.next()) {
            if (Integer.valueOf(resultSet_local.getInt(pCampo.getNombreCampo())) != ConstantesGeneral.VALOR_NULO && resultSet_local.getInt(pCampo.getNombreCampo()) != 0) {
              
              consecutivoInterno_local++;
              asignarValorConsecutivoInternoCampo(pCampo, resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getNombreGrupoInformacion())), consecutivoInterno_local);
            } 
          } 
        } else {
          
          setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      resultSet_local = null;
    } 
    
    return errorEjecucion_local;
  }
  public int obtenerConsecutivoInterno(Campo pCampo, int pValorLlavePrimaria) {
    int consecutivoNumerico_local = -1;
    int errorEjecucion_local = -1;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return consecutivoNumerico_local;
    }
    
    try {
      consecutivoNumerico_local = 1;
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        consulta_local = ca.consultaSQLUltimoValorConsecutivoInterno(pCampo, pValorLlavePrimaria);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        errorEjecucion_local = manejadorConsultaSQL_local.ejecutarConsulta();
        if (errorEjecucion_local == 0) {
          resultSet_local = manejadorConsultaSQL_local.getResultSet();
          if (resultSet_local.next()) {
            consecutivoNumerico_local = resultSet_local.getInt("ultimoconsecutivointerno");
            consecutivoNumerico_local++;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return consecutivoNumerico_local;
  }
  public ListaCadenas obtenerListaNombresCamposVisiblesGrupoInformacion(int pIdGrupoInformacion, boolean pVerificarVisibilidad) {
    ListaCadenas listaNombreCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaNombreCampos_local = new ListaCadenas();
      consulta_local = ca.consultaSQLNombresCamposVisiblesGrupoInformacion(pIdGrupoInformacion, pVerificarVisibilidad);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaNombreCampos_local.adicionar(resultSet_local.getString("fldnombrecampo"));
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
    
    return listaNombreCampos_local;
  }
  public ListaCadenas obtenerListaNombresCamposVisiblesGrupoInformacionMultiple(int pIdGrupoInformacion) {
    ListaCadenas listaNombresCampos_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      listaNombresCampos_local = new ListaCadenas();
      consulta_local = ca.consultaSQLNombresCamposVisiblesGrupoInformacionMultiple(pIdGrupoInformacion);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          listaNombresCampos_local.adicionar(resultSet_local.getString("fldnombrecampo"));
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
    
    return listaNombresCampos_local;
  }
  public boolean verificarHabilitacionValorMaestro(int pIdCampo, int pIdValorMaestro) {
    boolean habilitacionValorMaestro_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLSeleccionDependienteHabilitacion(pIdCampo, pIdValorMaestro);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          habilitacionValorMaestro_local = resultSet_local.getBoolean("fldhabilitacion");
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return habilitacionValorMaestro_local;
  }
  public ResultSet obtenerValoresTablaInterna(String pNombreTabla) {
    ResultSet resultSet_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return resultSet_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValoresTablaInterna(pNombreTabla);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return resultSet_local;
  }
  private byte[] obtenerArchivoDeBaseDatos(Campo pCampo, int pValorLlavePrimaria) {
    byte[] archivo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return archivo_local;
    }
    
    try {
      consulta_local = ca.consultaSQLObtenerArchivoDeBaseDatos(pCampo, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          archivo_local = resultSet_local.getBytes(pCampo.getNombreCampo());
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
    
    return archivo_local;
  }
  private String obtenerNombreArchivoDeBaseDatos(Campo pCampo, int pValorLlavePrimaria) {
    String nombreArchivo_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    Campo campoNombreArchivo_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return nombreArchivo_local;
    }
    
    try {
      campoNombreArchivo_local = ap.conformarCampoNombreArchivo(pCampo);
      consulta_local = ca.consultaSQLObtenerArchivoDeBaseDatos(campoNombreArchivo_local, pValorLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && 
          resultSet_local.next()) {
          nombreArchivo_local = resultSet_local.getString(campoNombreArchivo_local.getNombreCampo());
        }
      } else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      campoNombreArchivo_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return nombreArchivo_local;
  }
  public int copiarArchivoEntreCampos(Campo pCampoOrigen, Campo pCampoDestino, int pValorLlavePrimariaOrigen, int pValorLlavePrimariaDestino) {
    int errorEjecucion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    byte[] archivo_local = null;
    
    if (pCampoOrigen == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pCampoDestino == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      archivo_local = obtenerArchivoDeBaseDatos(pCampoOrigen, pValorLlavePrimariaOrigen);
      if (archivo_local != ConstantesGeneral.VALOR_NULO) {
        consulta_local = ca.consultaSQLCargarArchivoABaseDatos(pCampoDestino, pValorLlavePrimariaDestino);
        objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
        
        manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
        manejadorConsultaSQL_local.setPreparedStatement(getConexionBaseDatos().getConexion().prepareStatement(consulta_local));
        manejadorConsultaSQL_local.getPreparedStatement().setString(1, obtenerNombreArchivoDeBaseDatos(pCampoOrigen, pValorLlavePrimariaOrigen));
        
        manejadorConsultaSQL_local.getPreparedStatement().setBytes(2, archivo_local);
        try {
          manejadorConsultaSQL_local.getPreparedStatement().executeUpdate();
          manejadorConsultaSQL_local.getPreparedStatement().close();
        } catch (SQLException exception) {
          if (mc.esCadenaNumerica(exception.getSQLState(), true)) {
            errorEjecucion_local = Integer.parseInt(exception.getSQLState());
          }
          exception.printStackTrace();
        } 
        
        if (errorEjecucion_local == 0) {
          confirmarTransaccion();
        } else {
          cancelarTransaccion();
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      archivo_local = null;
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorEjecucion_local;
  }
  protected int modificarTamanoCampo(String pNombreTabla, String pNombreCampo, int pNuevaLongitud) {
    int errorModificacion_local = 0;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO) {
      return errorModificacion_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return errorModificacion_local;
    }
    
    try {
      consulta_local = ca.conformarConsultaSQLModificarTamanoCampo(pNombreTabla, pNombreCampo, pNuevaLongitud);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      errorModificacion_local = manejadorConsultaSQL_local.ejecutarConsulta();
      if (errorModificacion_local != 0) {
        cancelarTransaccion();
      } else {
        confirmarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return errorModificacion_local;
  }
  protected int insertarRegistroConfiguracion(int pNumeroVersion) {
    int registroConfiguracion_local = -1;
    String registro_local = null;
    
    try {
      registro_local = mc.concatenarCadena("insert into ", "CONFIGURACION");
      registro_local = mc.concatenarCadena(registro_local, " values( ");
      registro_local = mc.concatenarCadena(registro_local, pNumeroVersion + String.valueOf(','));
      registro_local = mc.concatenarCadena(registro_local, mc.colocarEntreComillas(ConstantesVersion.FECHA_VERSION.toString()) + ')' + ';');
      
      registroConfiguracion_local = ejecutarInstruccionSQL(registro_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      registro_local = null;
    } 
    
    return registroConfiguracion_local;
  }
  public int reemplazarRegistroConfiguracion(int pNumeroVersion) {
    int numeroVersionActual_local = -1;
    String consulta_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    
    try {
      consulta_local = ca.consultaSQLBorrarRegistrosConfiguracion();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        confirmarTransaccion();
        insertarRegistroConfiguracion(pNumeroVersion);
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
        cancelarTransaccion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      consulta_local = null;
    } 
    
    return numeroVersionActual_local;
  }
  public int obtenerNumeroVersionActual() {
    int numeroVersionActual_local = -1;
    ResultSet resultSet_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    String consulta_local = null;
    
    try {
      consulta_local = ca.consultaSQLObtenerNumeroVersion();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          numeroVersionActual_local = resultSet_local.getInt("fldnumeroversion");
        }
        if (manejadorConsultaSQL_local.getNumeroRegistros() > 1) {
          reemplazarRegistroConfiguracion(numeroVersionActual_local);
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
      consulta_local = null;
    } 
    
    return numeroVersionActual_local;
  }
  private Date obtenerFechaVersionActual() {
    Date fechaVersionActual_local = null;
    ResultSet resultSet_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    String consulta_local = null;
    
    try {
      consulta_local = ca.consultaSQLObtenerFechaVersion();
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          fechaVersionActual_local = resultSet_local.getDate("fldfechaversion");
        }
      } else {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
      consulta_local = null;
      resultSet_local = null;
    } 
    
    return fechaVersionActual_local;
  }
  public boolean verificarActualizarVersion() {
    boolean actualizarVersion_local = false;
    
    try {
      actualizarVersion_local = (obtenerNumeroVersionActual() <= 40);
      if (!actualizarVersion_local) {
        actualizarVersion_local = (obtenerFechaVersionActual().compareTo(ConstantesVersion.FECHA_VERSION) < 0);
      }
    } catch (Exception exepcion) {
      exepcion.printStackTrace();
    } 
    
    return actualizarVersion_local;
  }
  public ListaGeneral obtenerListaValoresReportesImpresionMasiva(String pNombreGrupoPrincipal, String pNombreCampo, String pNombreCampoLlavePrimaria) {
    ListaGeneral listaValores_local = null;
    String valor_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreGrupoPrincipal == ConstantesGeneral.VALOR_NULO) {
      return listaValores_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return listaValores_local;
    }
    if (pNombreCampoLlavePrimaria == ConstantesGeneral.VALOR_NULO) {
      return listaValores_local;
    }
    
    try {
      consulta_local = ca.consultaSQLValoresAplicacionRelacionada(pNombreGrupoPrincipal, pNombreCampo, pNombreCampoLlavePrimaria);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        listaValores_local = new ListaGeneral();
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        while (resultSet_local.next()) {
          valor_local = resultSet_local.getObject(pNombreCampoLlavePrimaria).toString();
          listaValores_local.adicionar(resultSet_local.getObject(pNombreCampo).toString(), valor_local, false);
        } 
      } else {
        
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      valor_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return listaValores_local;
  }
  public Date obtenerFechaCambioContrasenaUsuario(String pNombreUsuario) {
    Date fechaCambioContrasenaUsuario_local = null;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return fechaCambioContrasenaUsuario_local;
    }
    
    try {
      consulta_local = ca.consultaSQLFechaCambioContrasena(pNombreUsuario);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          fechaCambioContrasenaUsuario_local = resultSet_local.getDate("fldfechacambiocontrasena");
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
    
    return fechaCambioContrasenaUsuario_local;
  }
  private void modificarFechaVencimientoUsuario(String pNombreUsuario, Date pFecha) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarFechaVencimientoUsuario(pNombreUsuario, pFecha);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  private void modificarTipoBloqueoUsuario(String pNombreUsuario, int pTipoBloqueo) {
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      consulta_local = ca.consultaSQLModificarTipoBloqueoUsuario(pNombreUsuario, pTipoBloqueo);
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getConexionBaseDatos(), consulta_local, "actualizacion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() != 0) {
        setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
  }
  public void desbloquearUsuarioVencimiento(String pNombreUsuario, int pDiasVencimiento) {
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      modificarFechaVencimientoUsuario(pNombreUsuario, mf.sumarDiasFecha(Date.valueOf(mf.obtenerFechaActualSistema(true)), pDiasVencimiento));
      
      modificarTipoBloqueoUsuario(pNombreUsuario, 1);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\AdministradorBaseDatos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */