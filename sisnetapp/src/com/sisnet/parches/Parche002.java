package com.sisnet.parches;
import com.sisnet.baseDatos.AdministradorBaseDatosParches;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
public class Parche002
{
  private AdministradorBaseDatosParches aAdministradorBaseDatosParches;
  private boolean aAplicarParche002;
  private boolean aCrearCampoAplicarParche002;
  private static final int const_TipoEnlaceListaDependiente = 1;
  private static final int const_TipoEnlaceEnlazado = 2;
  private static final int const_TipoEnlaceDependienteEnlazadoCalculable = 3;
  private static final int const_TipoEnlaceDependienteEnlazadoNoRecalculable = 5;
  private static final int const_TipoEnlaceHabilitadoPor = 6;
  private static final String const_CampoDepende = "fldidcampodepende";
  private static final String const_CampoAplicacionEnlace = "fldaplicacionenlace";
  private static final String const_CampoTipoEnlace = "fldtipoenlace";
  public Parche002(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    setAdministradorBaseDatosParches(new AdministradorBaseDatosParches(pObjetoConexionBaseDatos));
    setCrearCampoAplicarParche002(!getAdministradorBaseDatosParches().verificarExistenciaCampo("fldaplicarparche002", "CONFIGURACION"));
    
    setAplicarParche002(getAdministradorBaseDatosParches().verificarAplicarParche002(esCrearCampoAplicarParche002(), obtenerListadoInstruccionesSqlCrearCampoAplicarParche002()));
  }
  public AdministradorBaseDatosParches getAdministradorBaseDatosParches() {
    return this.aAdministradorBaseDatosParches;
  }
  public void setAdministradorBaseDatosParches(AdministradorBaseDatosParches pAdministradorBaseDatosParches) {
    this.aAdministradorBaseDatosParches = pAdministradorBaseDatosParches;
  }
  public boolean esAplicarParche002() {
    return this.aAplicarParche002;
  }
  public void setAplicarParche002(boolean pAplicarParche002) {
    this.aAplicarParche002 = pAplicarParche002;
  }
  public boolean esCrearCampoAplicarParche002() {
    return this.aCrearCampoAplicarParche002;
  }
  public void setCrearCampoAplicarParche002(boolean pCrearCampoAplicarParche002) {
    this.aCrearCampoAplicarParche002 = pCrearCampoAplicarParche002;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlCrearCampoAplicarParche002() {
    ListaCadenas listaCadenas_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      listaCadenas_local.adicionar("alter table configuracion add fldaplicarparche002 boolean;");
      listaCadenas_local.adicionar("update configuracion set fldaplicarparche002 = true where fldaplicarparche002 is null;");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlCamposParche002() {
    ListaCadenas listaCadenas_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      listaCadenas_local.adicionar("alter table campo add fldhabilitadopor integer;");
      listaCadenas_local.adicionar("alter table campo add fldlistadependiente integer;");
      listaCadenas_local.adicionar("alter table campo add fldenlazado integer;");
      listaCadenas_local.adicionar("alter table campo add flddependienteenlazado integer;");
      listaCadenas_local.adicionar("update campo set fldhabilitadopor = 0 where fldhabilitadopor is null");
      
      listaCadenas_local.adicionar("update campo set fldlistadependiente = 0 where fldlistadependiente is null");
      
      listaCadenas_local.adicionar("update campo set fldenlazado = 0 where fldenlazado is null");
      
      listaCadenas_local.adicionar("update campo set flddependienteenlazado = 0 where flddependienteenlazado is null");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlActualizarCamposParche002() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (getAdministradorBaseDatosParches().verificarExistenciaCampo("fldtipoenlace", "CAMPO")) {
        if (getAdministradorBaseDatosParches().verificarExistenciaCampo("fldidcampodepende", "CAMPO")) {
          listaCadenas_local.adicionar("update campo set fldhabilitadopor = fldidcampodepende where fldtipoenlace = 6;");
          
          listaCadenas_local.adicionar("update campo set fldlistadependiente = fldidcampodepende where fldtipoenlace = 1;");
        } 
        
        if (getAdministradorBaseDatosParches().verificarExistenciaCampo("fldaplicacionenlace", "CAMPO")) {
          listaCadenas_local.adicionar("update campo set fldenlazado = fldaplicacionenlace where fldtipoenlace = 2;");
        }
        if (getAdministradorBaseDatosParches().verificarExistenciaCampo("fldtipoenlace", "CAMPO")) {
          listaCadenas_local.adicionar("update campo set flddependienteenlazado = 1 where (fldtipoenlace = 3 or fldtipoenlace = 5);");
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public ListaCadenas obtenerListadoInstruccionesSqlBorrarCampos() {
    ListaCadenas listaCadenas_local = null;
    AdministradorBaseDatosParches administradorBaseDatosParches_local = null;
    try {
      listaCadenas_local = new ListaCadenas();
      administradorBaseDatosParches_local = getAdministradorBaseDatosParches();
      if (administradorBaseDatosParches_local.verificarExistenciaCampo("fldidcampodepende", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table campo drop column fldidcampodepende;");
      }
      if (administradorBaseDatosParches_local.verificarExistenciaCampo("fldaplicacionenlace", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table campo drop column fldaplicacionenlace;");
      }
      if (administradorBaseDatosParches_local.verificarExistenciaCampo("fldtipoenlace", "CAMPO"))
      {
        listaCadenas_local.adicionar("alter table campo drop column fldtipoenlace;");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      administradorBaseDatosParches_local = null;
    } 
    return listaCadenas_local;
  }
  public void aplicarParche002() {
    AdministradorBaseDatosParches administradorBaseDatosParches_local = null;
    ListaCadenas listaInstruccionesCrearCamposParche002_local = null;
    ListaCadenas listaInstruccionesActualizarCamposParche002_local = null;
    ListaCadenas listaInstruccionesBorrarCamposParche002_local = null;
    try {
      administradorBaseDatosParches_local = getAdministradorBaseDatosParches();
      listaInstruccionesCrearCamposParche002_local = obtenerListadoInstruccionesSqlCamposParche002();
      administradorBaseDatosParches_local.crearCamposParche002(listaInstruccionesCrearCamposParche002_local);
      listaInstruccionesActualizarCamposParche002_local = obtenerListadoInstruccionesSqlActualizarCamposParche002();
      administradorBaseDatosParches_local.actualizarCamposParche002(listaInstruccionesActualizarCamposParche002_local);
      listaInstruccionesBorrarCamposParche002_local = obtenerListadoInstruccionesSqlBorrarCampos();
      administradorBaseDatosParches_local.borrarCamposParche002(listaInstruccionesBorrarCamposParche002_local);
      administradorBaseDatosParches_local.actualizarAplicarParche002();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      administradorBaseDatosParches_local = null;
      listaInstruccionesCrearCamposParche002_local = null;
      listaInstruccionesBorrarCamposParche002_local = null;
      listaInstruccionesActualizarCamposParche002_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\parches\Parche002.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */