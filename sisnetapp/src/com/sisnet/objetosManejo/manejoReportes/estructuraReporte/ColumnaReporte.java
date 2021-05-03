package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
public class ColumnaReporte
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  protected static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aNombreColumna;
  private int aAnchoColumna;
  private int aTipoRepeticion;
  private String aFormatoEstilo;
  private Campo aCampo;
  private int aNumeroColumna;
  public ColumnaReporte(String pNombreColumna, int pAnchoColumna, int pTipoRepeticion, String pFormatoEstilo, Campo pcampo, int pNumeroColumna) {
    setNombreColumna(pNombreColumna);
    setAnchoColumna(pAnchoColumna);
    setTipoRepeticion(pTipoRepeticion);
    setCampo(pcampo);
    setFormatoEstilo(pFormatoEstilo);
    setNumeroColumna(pNumeroColumna);
  }
  public String getNombreColumna() {
    return this.aNombreColumna;
  }
  public void setNombreColumna(String pNombreColumna) {
    this.aNombreColumna = pNombreColumna;
  }
  public int getAnchoColumna() {
    return this.aAnchoColumna;
  }
  public void setAnchoColumna(int pAnchoColumna) {
    this.aAnchoColumna = pAnchoColumna;
  }
  public int getTipoRepeticion() {
    return this.aTipoRepeticion;
  }
  public void setTipoRepeticion(int pTipoRepeticion) {
    this.aTipoRepeticion = pTipoRepeticion;
  }
  public String getFormatoEstilo() {
    return this.aFormatoEstilo;
  }
  public void setFormatoEstilo(String pFormatoEstilo) {
    if (getCampo().esTipoDatoNumerico() && !mc.verificarExistenciaSubCadena(pFormatoEstilo, "SPA"))
    {
      pFormatoEstilo = "NOO";
    }
    this.aFormatoEstilo = pFormatoEstilo;
  }
  public Campo getCampo() {
    return this.aCampo;
  }
  public void setCampo(Campo pCampo) {
    this.aCampo = pCampo;
  }
  public int getNumeroColumna() {
    return this.aNumeroColumna;
  }
  public void setNumeroColumna(int pNumeroColumna) {
    this.aNumeroColumna = pNumeroColumna;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\ColumnaReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */