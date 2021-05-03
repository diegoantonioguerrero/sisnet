package com.sisnet.objetosManejo.manejoRelacionAplicativos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaCondiciones;
import java.sql.ResultSet;
public class Ciclo
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private GrupoInformacion aGrupoInformacion;
  private String aCondiciones;
  private ListaCondiciones aListaCondiciones;
  private ResultSet aResultSet;
  private ListaCampo aListaCampos;
  private String aCondicionesInterpretadas;
  private boolean aSoloUnRegistro;
  private boolean aHaFinalizado;
  private int aNumeroCiclo;
  private boolean aOrdenDescendente;
  public Ciclo() {
    setGrupoInformacion(null);
    setCondiciones("");
    setListaCondiciones(null);
    setResultSet(null);
    setListaCampos(null);
    setCondicionesInterpretadas("");
    setSoloUnRegistro(false);
    setHaFinalizado(false);
    setNumeroCiclo(0);
    setOrdenDescendente(false);
  }
  public GrupoInformacion getGrupoInformacion() {
    return this.aGrupoInformacion;
  }
  public void setGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    this.aGrupoInformacion = pGrupoInformacion;
  }
  public String getCondiciones() {
    return this.aCondiciones;
  }
  public void setCondiciones(String pCondiciones) {
    this.aCondiciones = pCondiciones;
    if (pCondiciones != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(pCondiciones)) {
      setListaCondiciones(ap.obtenerListaCondicionesRelacionAplicativos(pCondiciones));
    }
  }
  public ListaCondiciones getListaCondiciones() {
    return this.aListaCondiciones;
  }
  public void setListaCondiciones(ListaCondiciones pListaCondiciones) {
    this.aListaCondiciones = pListaCondiciones;
  }
  public ResultSet getResultSet() {
    return this.aResultSet;
  }
  public void setResultSet(ResultSet pResultSet) {
    this.aResultSet = pResultSet;
  }
  public ListaCampo getListaCampos() {
    return this.aListaCampos;
  }
  public void setListaCampos(ListaCampo pListaCampos) {
    this.aListaCampos = pListaCampos;
  }
  public String getCondicionesInterpretadas() {
    return this.aCondicionesInterpretadas;
  }
  public void setCondicionesInterpretadas(String pCondicionesInterpretadas) {
    this.aCondicionesInterpretadas = pCondicionesInterpretadas;
  }
  public boolean esSoloUnRegistro() {
    return this.aSoloUnRegistro;
  }
  public void setSoloUnRegistro(boolean pSoloPrimerRegistro) {
    this.aSoloUnRegistro = pSoloPrimerRegistro;
  }
  public boolean esHaFinalizado() {
    return this.aHaFinalizado;
  }
  public void setHaFinalizado(boolean pHaFinalizado) {
    this.aHaFinalizado = pHaFinalizado;
  }
  public int getNumeroCiclo() {
    return this.aNumeroCiclo;
  }
  public void setNumeroCiclo(int pNumeroCiclo) {
    this.aNumeroCiclo = pNumeroCiclo;
  }
  public boolean esOrdenDescendente() {
    return this.aOrdenDescendente;
  }
  public void setOrdenDescendente(boolean pOrdenDescendente) {
    this.aOrdenDescendente = pOrdenDescendente;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoRelacionAplicativos\Ciclo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */