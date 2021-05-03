package com.sisnet.parches;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
public class Parche003
  extends AdministradorBaseDatos
{
  public Parche003(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  public void aplicarParche003() {
    String consultaSql_local = null;
    
    try {
      if (verificarExistenciaCampo("fldnombrecompletousuario", "USUARIO")) {
        consultaSql_local = "update usuario set fldnombrecompletousuario = fldnombreusuario where fldnombrecompletousuario is null";
        ejecutarInstruccionSQL(consultaSql_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consultaSql_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\parches\Parche003.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */