package com.sisnet.objetosManejo.manejoRelacionAplicativos;
public class ExpresionCondicion
{
  private String aExpresion;
  private String aOperadorAndOr;
  private boolean aCondicionValida;
  private String aOperandoIzquierda;
  private String aOperandoDerecha;
  private String aOperadorEvaluacion;
  private boolean aSonOperandosNumericos;
  public ExpresionCondicion(String pExpresion, String pOperadorAndOr, boolean pResultado) {
    setExpresion(pExpresion);
    setOperadorAndOr(pOperadorAndOr);
    setCondicionValida(pResultado);
    setSonOperandosNumericos(false);
  }
  public String getExpresion() {
    return this.aExpresion;
  }
  public void setExpresion(String pExpresion) {
    this.aExpresion = pExpresion;
  }
  public String getOperadorAndOr() {
    return this.aOperadorAndOr;
  }
  public void setOperadorAndOr(String pOperadorAndOr) {
    this.aOperadorAndOr = pOperadorAndOr;
  }
  public boolean esCondicionValida() {
    return this.aCondicionValida;
  }
  public void setCondicionValida(boolean pCondicionValida) {
    this.aCondicionValida = pCondicionValida;
  }
  public String getOperandoIzquierda() {
    return this.aOperandoIzquierda;
  }
  public void setOperandoIzquierda(String pOperandoIzquierda) {
    this.aOperandoIzquierda = pOperandoIzquierda;
  }
  public String getOperandoDerecha() {
    return this.aOperandoDerecha;
  }
  public void setOperandoDerecha(String pOperandoDerecha) {
    this.aOperandoDerecha = pOperandoDerecha;
  }
  public String getOperadorEvaluacion() {
    return this.aOperadorEvaluacion;
  }
  public void setOperadorEvaluacion(String pOperadorEvaluacion) {
    this.aOperadorEvaluacion = pOperadorEvaluacion;
  }
  public boolean sonOperandosNumericos() {
    return this.aSonOperandosNumericos;
  }
  public void setSonOperandosNumericos(boolean pSonOperandosNumericos) {
    this.aSonOperandosNumericos = pSonOperandosNumericos;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoRelacionAplicativos\ExpresionCondicion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */