package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import java.io.Serializable;
public class FormatoCampo
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aTipoDato;
  private boolean aValorUnico;
  private int aLongitudCampo;
  private int aNumeroDecimales;
  public FormatoCampo() {
    setTipoDato("");
    setValorUnico(false);
    setLongitudCampo(0);
    setNumeroDecimales(0);
  }
  public String getTipoDato() {
    return this.aTipoDato;
  }
  public void setTipoDato(String pTipoDato) {
    this.aTipoDato = mc.convertirAMayusculas(pTipoDato);
  }
  public boolean esValorUnico() {
    return this.aValorUnico;
  }
  public void setValorUnico(boolean pValorUnico) {
    this.aValorUnico = pValorUnico;
  }
  public int getLongitudCampo() {
    return this.aLongitudCampo;
  }
  public void setLongitudCampo(int pLongitudCampo) {
    this.aLongitudCampo = pLongitudCampo;
  }
  public int getNumeroDecimales() {
    return this.aNumeroDecimales;
  }
  public void setNumeroDecimales(int pNumeroDecimales) {
    this.aNumeroDecimales = pNumeroDecimales;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\FormatoCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */