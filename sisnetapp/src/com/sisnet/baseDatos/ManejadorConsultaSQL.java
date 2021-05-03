package com.sisnet.baseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.ConexionBaseDatos;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoManejadorConsultaSQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
public class ManejadorConsultaSQL
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  private ConexionBaseDatos aConeccionBaseDatos;
  private String aConsultaSQL;
  private String aTipoConsulta;
  private int aTimeOut;
  private int aErrorConsultaSQL;
  private ResultSet aResultSet;
  private ResultSet aResultSetContador;
  private Statement aStatement;
  private Statement aStatementContador;
  private PreparedStatement aPreparedStatement;
  private int aNumeroRegistros;
  public ManejadorConsultaSQL(ObjetoManejadorConsultaSQL pObjetoManejadorConsultaSQL) {
    setConeccionBaseDatos(pObjetoManejadorConsultaSQL.getConeccionBaseDatos());
    setConsultaSQL(pObjetoManejadorConsultaSQL.getConsultaSQL());
    setTipoConsulta(pObjetoManejadorConsultaSQL.getTipoConsulta());
    setTimeOut(pObjetoManejadorConsultaSQL.getTimeOut());
    setErrorConsultaSQL(0);
    setResultSet(null);
    setResultSetContador(null);
    setStatement(null);
    setStatementContador(null);
    setPreparedStatement(null);
    asignarNumeroRegistrosActualizacion(0);
  }
  public ConexionBaseDatos getConeccionBaseDatos() {
    return this.aConeccionBaseDatos;
  }
  public void setConeccionBaseDatos(ConexionBaseDatos pConeccionBaseDatos) {
    this.aConeccionBaseDatos = pConeccionBaseDatos;
  }
  public String getConsultaSQL() {
    return this.aConsultaSQL;
  }
  public void setConsultaSQL(String pConsultaSQL) {
    this.aConsultaSQL = pConsultaSQL;
  }
  public String getTipoConsulta() {
    return this.aTipoConsulta;
  }
  public void setTipoConsulta(String pTipoConsulta) {
    this.aTipoConsulta = pTipoConsulta;
  }
  public int getTimeOut() {
    return this.aTimeOut;
  }
  public void setTimeOut(int pTimeOut) {
    this.aTimeOut = pTimeOut;
  }
  public int getErrorConsultaSQL() {
    return this.aErrorConsultaSQL;
  }
  public void setErrorConsultaSQL(int pErrorConsultaSQL) {
    this.aErrorConsultaSQL = pErrorConsultaSQL;
  }
  public ResultSet getResultSet() {
    return this.aResultSet;
  }
  public void setResultSet(ResultSet pResultSet) {
    this.aResultSet = pResultSet;
  }
  public Statement getStatement() {
    return this.aStatement;
  }
  public void setStatement(Statement pStatement) {
    this.aStatement = pStatement;
  }
  public PreparedStatement getPreparedStatement() {
    return this.aPreparedStatement;
  }
  public void setPreparedStatement(PreparedStatement pPreparedStatement) {
    this.aPreparedStatement = pPreparedStatement;
  }
  public ResultSet getResultSetContador() {
    return this.aResultSetContador;
  }
  public void setResultSetContador(ResultSet pResultSetContador) {
    this.aResultSetContador = pResultSetContador;
  }
  public Statement getStatementContador() {
    return this.aStatementContador;
  }
  public void setStatementContador(Statement pStatementContador) {
    this.aStatementContador = pStatementContador;
  }
  public int getNumeroRegistros() {
    return this.aNumeroRegistros;
  }
  public void setNumeroRegistros(int pNumeroRegistros) {
    this.aNumeroRegistros = pNumeroRegistros;
  }
  private void asignarNumeroRegistrosActualizacion(int pNumeroRegistros) {
    this.aNumeroRegistros = pNumeroRegistros;
  }
  private void asignarNumeroRegistrosConsulta() throws SQLException {
    int numeroRegistros_local = 0;
    try {
      if (getResultSetContador().next()) {
        numeroRegistros_local = getResultSetContador().getInt("totalregistros");
      }
      setNumeroRegistros(numeroRegistros_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public int ejecutarConsulta() {
    int errorEjecutarConsulta_local = 0;
    String consultaContarRegistros_local = null;
    String query = null;
    try {
      if (mc.sonCadenasIguales(getTipoConsulta(), "actualizacion")) {
    	  query = getConsultaSQL();
        setPreparedStatement(getConeccionBaseDatos().getConexion().prepareStatement(query));
        asignarNumeroRegistrosActualizacion(getPreparedStatement().executeUpdate());
        getPreparedStatement().setQueryTimeout(getTimeOut());
      } else {
        setStatement(getConeccionBaseDatos().getConexion().createStatement());
        query = getConsultaSQL();
        setResultSet(getStatement().executeQuery(query));
        System.out.println("Executed query: " + query);
        consultaContarRegistros_local = ca.conformarConsultaContarRegistros(getConsultaSQL());
        setStatementContador(getConeccionBaseDatos().getConexion().createStatement());
        setResultSetContador(getStatementContador().executeQuery(consultaContarRegistros_local));
        asignarNumeroRegistrosConsulta();
        getStatement().setQueryTimeout(getTimeOut());
      } 
    } catch (SQLException exception) {
      if (mc.esCadenaNumerica(exception.getSQLState(), true)) {
        errorEjecutarConsulta_local = Integer.parseInt(exception.getSQLState());
      }
      System.out.println(query);
      exception.printStackTrace();
    } finally {
      consultaContarRegistros_local = null;
    } 
    return errorEjecutarConsulta_local;
  }
  public ListaCampo obtenerListaCampos() {
    ListaCampo listaCampos_local = null;
    ResultSetMetaData ResultSetMetaData_local = null;
    
    try {
      if (getResultSet() != ConstantesGeneral.VALOR_NULO) {
        listaCampos_local = new ListaCampo();
        
        ResultSetMetaData_local = getResultSet().getMetaData();
        for (int i = 1; i <= ResultSetMetaData_local.getColumnCount(); i++) {
          listaCampos_local.adicionar(ResultSetMetaData_local.getColumnName(i), ResultSetMetaData_local.getColumnTypeName(i), ResultSetMetaData_local.getColumnDisplaySize(i), ResultSetMetaData_local.getPrecision(i));
        
        }
      }
    
    }
    catch (Exception excepcion) {
      setErrorConsultaSQL(7);
      excepcion.printStackTrace();
    } finally {
      ResultSetMetaData_local = null;
    } 
    return listaCampos_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\ManejadorConsultaSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */