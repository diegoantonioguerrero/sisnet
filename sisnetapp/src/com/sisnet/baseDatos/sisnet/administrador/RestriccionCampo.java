package com.sisnet.baseDatos.sisnet.administrador;
import java.io.Serializable;
public class RestriccionCampo
  implements Serializable
{
  private boolean aLlavePrimaria;
  private boolean aLlaveForanea;
  public RestriccionCampo() {
    setLlavePrimaria(false);
    setLlaveForanea(false);
  }
  public boolean esLlavePrimaria() {
    return this.aLlavePrimaria;
  }
  public void setLlavePrimaria(boolean pLlavePrimaria) {
    this.aLlavePrimaria = pLlavePrimaria;
  }
  public boolean esLlaveForanea() {
    return this.aLlaveForanea;
  }
  public void setLlaveForanea(boolean pLlaveForanea) {
    this.aLlaveForanea = pLlaveForanea;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\RestriccionCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */