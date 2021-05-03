package com.sisnet.version;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.parches.Parche001;
public class Version03
{
  private ObjetoConexionBaseDatos aObjetoConexionBaseDatos;
  public Version03(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    setObjetoConexionBaseDatos(pObjetoConexionBaseDatos);
  }
  public ObjetoConexionBaseDatos getObjetoConexionBaseDatos() {
    return this.aObjetoConexionBaseDatos;
  }
  public void setObjetoConexionBaseDatos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    this.aObjetoConexionBaseDatos = pObjetoConexionBaseDatos;
  }
  public void aplicarParche001() {
    Parche001 parche001_local = null;
    try {
      parche001_local = new Parche001(getObjetoConexionBaseDatos());
      if (parche001_local.esAplicarParche001()) {
        parche001_local.aplicarParche001();
      }
      parche001_local.getAdministradorBaseDatosParches().getConexionBaseDatos().cerrarConeccionBaseDatos();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      parche001_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version03.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */