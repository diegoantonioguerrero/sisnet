package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import java.io.Serializable;
public class PlantillaCampo
  implements Serializable
{
  private boolean aTienePlantilla;
  private int aIdAplicacionPlantilla;
  private Aplicacion aAplicacionPlantilla;
  public PlantillaCampo() {
    setTienePlantilla(false);
    setIdAplicacionPlantilla(-1);
    setAplicacionPlantilla(null);
  }
  public boolean esTienePlantilla() {
    return this.aTienePlantilla;
  }
  public void setTienePlantilla(boolean pTienePlantilla) {
    this.aTienePlantilla = pTienePlantilla;
  }
  public int getIdAplicacionPlantilla() {
    return this.aIdAplicacionPlantilla;
  }
  public void setIdAplicacionPlantilla(int pIdAplicacionPlantilla) {
    this.aIdAplicacionPlantilla = pIdAplicacionPlantilla;
  }
  public Aplicacion getAplicacionPlantilla() {
    return this.aAplicacionPlantilla;
  }
  public void setAplicacionPlantilla(Aplicacion pAplicacionPlantilla) {
    this.aAplicacionPlantilla = pAplicacionPlantilla;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\PlantillaCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */