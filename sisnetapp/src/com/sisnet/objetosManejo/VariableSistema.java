package com.sisnet.objetosManejo;
import java.io.Serializable;
public class VariableSistema
  implements Serializable
{
  private String aNombreVariable;
  private String aTipoDato;
  private Object aValorVariable;
  public VariableSistema(String pNombreVariable, String pTipoDatoVariable, Object pValorVariable) {
    setNombreVariable(pNombreVariable);
    setTipoDato(pTipoDatoVariable);
    setValorVariable(pValorVariable);
  }
  public String getNombreVariable() {
    return this.aNombreVariable;
  }
  public void setNombreVariable(String pNombreVariable) {
    this.aNombreVariable = pNombreVariable;
  }
  public String getTipoDato() {
    return this.aTipoDato;
  }
  public void setTipoDato(String pTipoDato) {
    this.aTipoDato = pTipoDato;
  }
  public Object getValorVariable() {
    return this.aValorVariable;
  }
  public void setValorVariable(Object pValorVariable) {
    this.aValorVariable = pValorVariable;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\VariableSistema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */