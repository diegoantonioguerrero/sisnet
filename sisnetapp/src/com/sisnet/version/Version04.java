package com.sisnet.version;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.parches.Parche002;
public class Version04
{
  private ObjetoConexionBaseDatos aObjetoConexionBaseDatos;
  public Version04(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    setObjetoConexionBaseDatos(pObjetoConexionBaseDatos);
  }
  public ObjetoConexionBaseDatos getObjetoConexionBaseDatos() {
    return this.aObjetoConexionBaseDatos;
  }
  public void setObjetoConexionBaseDatos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    this.aObjetoConexionBaseDatos = pObjetoConexionBaseDatos;
  }
  public void aplicarParche002() {
    Parche002 parche002_local = null;
    try {
      parche002_local = new Parche002(getObjetoConexionBaseDatos());
      if (parche002_local.esAplicarParche002()) {
        parche002_local.aplicarParche002();
      }
      parche002_local.getAdministradorBaseDatosParches().getConexionBaseDatos().cerrarConeccionBaseDatos();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      parche002_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version04.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */