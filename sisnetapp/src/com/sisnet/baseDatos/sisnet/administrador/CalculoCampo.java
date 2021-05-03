package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import java.io.Serializable;
public class CalculoCampo
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private int aCampoCalculado;
  private boolean aRecalculable;
  private int aIdCampoValor;
  private Campo aCampoValor;
  private int aIdCampoOrigenUno;
  private Campo aCampoOrigenUno;
  private int aIdCampoOrigenDos;
  private Campo aCampoOrigenDos;
  private String aFormatoCampoOrigenUno;
  private String aFormatoCampoOrigenDos;
  private String aFormatoCampoCalculado;
  public CalculoCampo() {
    setCampoCalculado(1);
    setRecalculable(false);
    setIdCampoValor(-1);
    setCampoValor(null);
    setIdCampoOrigenUno(-1);
    setCampoOrigenUno(null);
    setIdCampoOrigenDos(-1);
    setCampoOrigenDos(null);
    setFormatoCampoOrigenUno("");
    setFormatoCampoOrigenDos("");
    setFormatoCampoCalculado("");
  }
  public int getCampoCalculado() {
    return this.aCampoCalculado;
  }
  public void setCampoCalculado(int pCampoCalculado) {
    this.aCampoCalculado = pCampoCalculado;
  }
  public boolean esRecalculable() {
    return this.aRecalculable;
  }
  public void setRecalculable(boolean pRecalculable) {
    this.aRecalculable = pRecalculable;
  }
  public int getIdCampoValor() {
    return this.aIdCampoValor;
  }
  public void setIdCampoValor(int pIdCampoValor) {
    this.aIdCampoValor = pIdCampoValor;
  }
  public Campo getCampoValor() {
    return this.aCampoValor;
  }
  public void setCampoValor(Campo pCampoValor) {
    this.aCampoValor = pCampoValor;
  }
  public int getIdCampoOrigenUno() {
    return this.aIdCampoOrigenUno;
  }
  public void setIdCampoOrigenUno(int pIdCampoOrigenUno) {
    this.aIdCampoOrigenUno = pIdCampoOrigenUno;
  }
  public Campo getCampoOrigenUno() {
    return this.aCampoOrigenUno;
  }
  public void setCampoOrigenUno(Campo pCampoOrigenUno) {
    this.aCampoOrigenUno = pCampoOrigenUno;
  }
  public int getIdCampoOrigenDos() {
    return this.aIdCampoOrigenDos;
  }
  public void setIdCampoOrigenDos(int pIdCampoOrigenDos) {
    this.aIdCampoOrigenDos = pIdCampoOrigenDos;
  }
  public Campo getCampoOrigenDos() {
    return this.aCampoOrigenDos;
  }
  public void setCampoOrigenDos(Campo pCampoOrigenDos) {
    this.aCampoOrigenDos = pCampoOrigenDos;
  }
  public String getFormatoCampoOrigenUno() {
    return this.aFormatoCampoOrigenUno;
  }
  public void setFormatoCampoOrigenUno(String pFormatoCampoOrigenUno) {
    this.aFormatoCampoOrigenUno = mc.convertirAMayusculas(pFormatoCampoOrigenUno);
  }
  public String getFormatoCampoOrigenDos() {
    return this.aFormatoCampoOrigenDos;
  }
  public void setFormatoCampoOrigenDos(String pFormatoCampoOrigenDos) {
    this.aFormatoCampoOrigenDos = mc.convertirAMayusculas(pFormatoCampoOrigenDos);
  }
  public String getFormatoCampoCalculado() {
    return this.aFormatoCampoCalculado;
  }
  public void setFormatoCampoCalculado(String pFormatoCampoCalculado) {
    this.aFormatoCampoCalculado = mc.convertirAMayusculas(pFormatoCampoCalculado);
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\CalculoCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */