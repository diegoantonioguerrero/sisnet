package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import java.io.Serializable;
public class Tabla
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private int aIdTabla;
  private String aNombreTabla;
  private String aDescripcionTabla;
  public Tabla() {
    setIdTabla(-1);
    setNombreTabla("");
    setDescripcionTabla("");
  }
  public int getIdTabla() {
    return this.aIdTabla;
  }
  public void setIdTabla(int pIdTabla) {
    this.aIdTabla = pIdTabla;
  }
  public String getNombreTabla() {
    return this.aNombreTabla;
  }
  public void setNombreTabla(String pNombreTabla) {
    this.aNombreTabla = mc.convertirAMayusculas(pNombreTabla);
  }
  public String getDescripcionTabla() {
    return this.aDescripcionTabla;
  }
  public void setDescripcionTabla(String pDescripcionTabla) {
    this.aDescripcionTabla = mc.convertirAMayusculas(pDescripcionTabla);
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\Tabla.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */