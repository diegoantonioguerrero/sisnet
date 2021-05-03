package com.sisnet.controlesHTML;
public class ElementoHTML
{
  private String aId;
  private String aNombre;
  private String aEstilo;
  public ElementoHTML() {
    setId("");
    setNombre("");
    setEstilo("");
  }
  public String getId() {
    return this.aId;
  }
  public void setId(String pId) {
    this.aId = pId;
  }
  public String getNombre() {
    return this.aNombre;
  }
  public void setNombre(String pNombre) {
    this.aNombre = pNombre;
  }
  public String getEstilo() {
    return this.aEstilo;
  }
  public void setEstilo(String pEstilo) {
    this.aEstilo = pEstilo;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\ElementoHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */