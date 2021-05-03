package com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import java.io.Serializable;
public class NavegacionPaginaAplicacion
  implements Serializable
{
  private GrupoInformacion aGrupoInformacion;
  private int aValorLlavePrimaria;
  private Tabla aTablaActual;
  private Tabla aTablaDepende;
  private Campo aCampoArchivo;
  public NavegacionPaginaAplicacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria, Tabla pTablaActual, Tabla pTablaDepende, Campo pCampoArchivo) {
    setGrupoInformacion(pGrupoInformacion);
    setValorLlavePrimaria(pValorLlavePrimaria);
    setTablaActual(pTablaActual);
    setTablaDepende(pTablaDepende);
    setCampoArchivo(pCampoArchivo);
  }
  public GrupoInformacion getGrupoInformacion() {
    return this.aGrupoInformacion;
  }
  public void setGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    this.aGrupoInformacion = pGrupoInformacion;
  }
  public int getValorLlavePrimaria() {
    return this.aValorLlavePrimaria;
  }
  public void setValorLlavePrimaria(int pValorLlavePrimaria) {
    this.aValorLlavePrimaria = pValorLlavePrimaria;
  }
  public Tabla getTablaActual() {
    return this.aTablaActual;
  }
  public void setTablaActual(Tabla pTablaActual) {
    this.aTablaActual = pTablaActual;
  }
  public Tabla getTablaDepende() {
    return this.aTablaDepende;
  }
  public void setTablaDepende(Tabla pTablaDepende) {
    this.aTablaDepende = pTablaDepende;
  }
  public Campo getCampoArchivo() {
    return this.aCampoArchivo;
  }
  public void setCampoArchivo(Campo pCampoArchivo) {
    this.aCampoArchivo = pCampoArchivo;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\objetosNavegacionPagina\NavegacionPaginaAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */