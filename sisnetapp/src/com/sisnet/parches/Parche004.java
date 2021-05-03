package com.sisnet.parches;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
public class Parche004
  extends AdministradorBaseDatos
{
  public Parche004(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  public void aplicarParche004() {
    try {
      if (verificarExistenciaCampo("fldeventosacciones", "APLICACION")) {
        modificarTamanoCampo("APLICACION", "fldeventosacciones", 10485760);
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\parches\Parche004.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */