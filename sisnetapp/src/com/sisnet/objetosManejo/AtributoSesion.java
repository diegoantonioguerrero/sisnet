package com.sisnet.objetosManejo;
public class AtributoSesion
{
  private String aNombreAtributo;
  private Object aValorAtributo;
  public AtributoSesion(String pNombreAtributo, Object pValorAtributo) {
    setNombreAtributo(pNombreAtributo);
    setValorAtributo(pValorAtributo);
  }
  public String getNombreAtributo() {
    return this.aNombreAtributo;
  }
  public void setNombreAtributo(String pNombreAtributo) {
    this.aNombreAtributo = pNombreAtributo;
  }
  public Object getValorAtributo() {
    return this.aValorAtributo;
  }
  public void setValorAtributo(Object pValorAtributo) {
    this.aValorAtributo = pValorAtributo;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\AtributoSesion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */