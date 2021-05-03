package com.sisnet.baseDatos.sisnet.usuario;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import java.io.Serializable;
public class TipoUsuario
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private int aIdTipoUsuario;
  private String aNombreTipoUsuario;
  private boolean aPermitirUtilizarMenu;
  private String aNivelAcceso;
  public TipoUsuario() {
    setIdTipoUsuario(-1);
    setNombreTipoUsuario("");
    setPermitirUtilizarMenu(false);
    setNivelAcceso("");
  }
  public int getIdTipoUsuario() {
    return this.aIdTipoUsuario;
  }
  public void setIdTipoUsuario(int pIdTipoUsuario) {
    this.aIdTipoUsuario = pIdTipoUsuario;
  }
  public String getNombreTipoUsuario() {
    return this.aNombreTipoUsuario;
  }
  public void setNombreTipoUsuario(String pNombreTipoUsuario) {
    this.aNombreTipoUsuario = mc.convertirAMayusculas(pNombreTipoUsuario);
  }
  public boolean esPermitirUtilizarMenu() {
    return this.aPermitirUtilizarMenu;
  }
  public void setPermitirUtilizarMenu(boolean pPermitirUtilizarMenu) {
    this.aPermitirUtilizarMenu = pPermitirUtilizarMenu;
  }
  public String getNivelAcceso() {
    return this.aNivelAcceso;
  }
  public void setNivelAcceso(String pNivelAcceso) {
    this.aNivelAcceso = mc.convertirAMayusculas(pNivelAcceso);
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisne\\usuario\TipoUsuario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */