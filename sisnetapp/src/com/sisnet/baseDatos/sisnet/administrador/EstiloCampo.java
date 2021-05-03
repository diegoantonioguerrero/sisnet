package com.sisnet.baseDatos.sisnet.administrador;
import java.io.Serializable;
public class EstiloCampo
  implements Serializable
{
  private boolean aCambiarRenglon;
  private int aMargenSuperior;
  private int aAnchoEtiqueta;
  private int aAnchoControl;
  private int aCantidadRenglones;
  private int aMargenIzquierdaEtiqueta;
  private int aMargenIzquierdaControl;
  public EstiloCampo() {
    setCambiarRenglon(true);
    setMargenSuperior(0);
    setAnchoEtiqueta(200);
    setAnchoControl(400);
    setCantidadRenglones(5);
    setMargenIzquierdaEtiqueta(0);
    setMargenIzquierdaControl(0);
  }
  public boolean cambiarRenglon() {
    return this.aCambiarRenglon;
  }
  public void setCambiarRenglon(boolean aCambiarRenglon) {
    this.aCambiarRenglon = aCambiarRenglon;
  }
  public int getMargenSuperior() {
    return this.aMargenSuperior;
  }
  public void setMargenSuperior(int aMargenSuperior) {
    this.aMargenSuperior = aMargenSuperior;
  }
  public int getAnchoEtiqueta() {
    return this.aAnchoEtiqueta;
  }
  public void setAnchoEtiqueta(int aAnchoEtiqueta) {
    this.aAnchoEtiqueta = aAnchoEtiqueta;
  }
  public int getAnchoControl() {
    return this.aAnchoControl;
  }
  public void setAnchoControl(int aAnchoControl) {
    this.aAnchoControl = aAnchoControl;
  }
  public int getCantidadRenglones() {
    return this.aCantidadRenglones;
  }
  public void setCantidadRenglones(int aCantidadRenglones) {
    this.aCantidadRenglones = aCantidadRenglones;
  }
  public int getMargenIzquierdaEtiqueta() {
    return this.aMargenIzquierdaEtiqueta;
  }
  public void setMargenIzquierdaEtiqueta(int aMargenIzquierdaEtiqueta) {
    this.aMargenIzquierdaEtiqueta = aMargenIzquierdaEtiqueta;
  }
  public int getMargenIzquierdaControl() {
    return this.aMargenIzquierdaControl;
  }
  public void setMargenIzquierdaControl(int aMargenIzquierdaControl) {
    this.aMargenIzquierdaControl = aMargenIzquierdaControl;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\EstiloCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */