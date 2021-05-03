package com.sisnet.objetosManejo.manejoBaseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.ConexionBaseDatos;
public class ObjetoManejadorConsultaSQL
{
  protected static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private ConexionBaseDatos aConeccionBaseDatos;
  private String aConsultaSQL;
  private String aTipoConsulta;
  private int aTimeOut;
  public ObjetoManejadorConsultaSQL(ConexionBaseDatos pConeccionBaseDatos, String pConsultaSQL, String pTipoConsulta) {
    setConeccionBaseDatos(pConeccionBaseDatos);
    setConsultaSQL(pConsultaSQL);
    setTipoConsulta(pTipoConsulta);
  }
  public ConexionBaseDatos getConeccionBaseDatos() {
    return this.aConeccionBaseDatos;
  }
  public String getConsultaSQL() {
    return this.aConsultaSQL;
  }
  public String getTipoConsulta() {
    return this.aTipoConsulta;
  }
  public int getTimeOut() {
    return this.aTimeOut;
  }
  public void setConeccionBaseDatos(ConexionBaseDatos pConeccionBaseDatos) {
    this.aConeccionBaseDatos = pConeccionBaseDatos;
  }
  public void setConsultaSQL(String pConsultaSQL) {
    this.aConsultaSQL = pConsultaSQL;
  }
  public void setTipoConsulta(String pTipoConsulta) {
    this.aTipoConsulta = pTipoConsulta;
    setTimeOut(1000);
    if (mc.sonCadenasIguales(getTipoConsulta(), "seleccion")) {
      setTimeOut(6000);
    }
  }
  public void setTimeOut(int pTimeOut) {
    this.aTimeOut = pTimeOut;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoBaseDatos\ObjetoManejadorConsultaSQL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */