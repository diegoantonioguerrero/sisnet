package com.sisnet.parches;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.baseDatos.AdministradorBaseDatosParches;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Parche001
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private AdministradorBaseDatosParches aAdministradorBaseDatosParches;
  private boolean aAplicarParche001;
  private boolean aCrearCampoAplicarParche001;
  public Parche001(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    setAdministradorBaseDatosParches(new AdministradorBaseDatosParches(pObjetoConexionBaseDatos));
    setCrearCampoAplicarParche001(!getAdministradorBaseDatosParches().verificarExistenciaCampo("fldaplicarparche001", "CONFIGURACION"));
    
    setAplicarParche001(getAdministradorBaseDatosParches().verificarAplicarParche001(esCrearCampoAplicarParche001(), obtenerListadoInstruccionesSqlCrearCampoAplicarParche001()));
  }
  public AdministradorBaseDatosParches getAdministradorBaseDatosParches() {
    return this.aAdministradorBaseDatosParches;
  }
  public void setAdministradorBaseDatosParches(AdministradorBaseDatosParches pAdministradorBaseDatosParches) {
    this.aAdministradorBaseDatosParches = pAdministradorBaseDatosParches;
  }
  public boolean esAplicarParche001() {
    return this.aAplicarParche001;
  }
  public void setAplicarParche001(boolean pAplicarParche001) {
    this.aAplicarParche001 = pAplicarParche001;
  }
  public boolean esCrearCampoAplicarParche001() {
    return this.aCrearCampoAplicarParche001;
  }
  public void setCrearCampoAplicarParche001(boolean pCrearCampoAplicarParche001) {
    this.aCrearCampoAplicarParche001 = pCrearCampoAplicarParche001;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlCrearCampoAplicarParche001() {
    ListaCadenas listaCadenas_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      listaCadenas_local.adicionar("alter table configuracion add fldaplicarparche001 bool;");
      listaCadenas_local.adicionar("update configuracion set fldaplicarparche001 = true where fldaplicarparche001 is null;");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void aplicarParche001() {
    int idAplicacion_local = -1;
    int posicionGrupos_local = -1;
    int idGrupoInformacion_local = -1;
    int posicionCampo_local = -1;
    int idCampo_local = -1;
    ListaGeneral listaAplicaciones_local = null;
    ListaGrupoInformacion listagrupoInformacion_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iteradorAplicaciones_local = null;
    Iterator iteradorGrupos_local = null;
    Iterator iteradorCampos_local = null;
    
    try {
      listaAplicaciones_local = getAdministradorBaseDatosParches().obtenerListaGeneralAplicaciones();
      iteradorAplicaciones_local = listaAplicaciones_local.iterator();
      while (iteradorAplicaciones_local.hasNext()) {
        idAplicacion_local = Integer.parseInt(((ItemLista)iteradorAplicaciones_local.next()).getValorItem());
        posicionGrupos_local = 1;
        listagrupoInformacion_local = getAdministradorBaseDatosParches().obtenerListaGruposInformacionAplicacion(idAplicacion_local);
        iteradorGrupos_local = listagrupoInformacion_local.iterator();
        while (iteradorGrupos_local.hasNext()) {
          idGrupoInformacion_local = ((GrupoInformacion)iteradorGrupos_local.next()).getIdGrupoInformacion();
          posicionCampo_local = 1;
          listaCampo_local = getAdministradorBaseDatosParches().obtenerListaCamposGrupoSinLlavePrimaria(idGrupoInformacion_local);
          iteradorCampos_local = listaCampo_local.iterator();
          while (iteradorCampos_local.hasNext()) {
            idCampo_local = ((Campo)iteradorCampos_local.next()).getIdCampo();
            getAdministradorBaseDatosParches().actualizarPosicionCampo(idCampo_local, posicionCampo_local);
            posicionCampo_local++;
          } 
          getAdministradorBaseDatosParches().actualizarPosicionGrupoInformacion(idGrupoInformacion_local, posicionGrupos_local);
          posicionGrupos_local++;
          iteradorCampos_local = null;
          listaCampo_local = null;
        } 
        iteradorGrupos_local = null;
        listagrupoInformacion_local = null;
      } 
      if (getAdministradorBaseDatosParches().obtenerCantidadRegistrosTabla("CONFIGURACION", "fldnumeroversion") > 1) {
        
        getAdministradorBaseDatosParches().borrarRegistrosConfiguracion();
        getAdministradorBaseDatosParches().insertarRegistroConfiguracion();
      } 
      getAdministradorBaseDatosParches().actualizarAplicarParche001();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaAplicaciones_local = null;
      listagrupoInformacion_local = null;
      listaCampo_local = null;
      iteradorGrupos_local = null;
      iteradorCampos_local = null;
      iteradorAplicaciones_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\parches\Parche001.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */