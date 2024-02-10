package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorArchivos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.baseDatos.CamposAdministrador;
import com.sisnet.baseDatos.ConexionPostgres;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.TipoUsuario;
import com.sisnet.constantes.ConstantesBaseDatos;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesVersion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaCondiciones;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaAplicacion;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaEstado;
import com.sisnet.objetosManejo.manejoPaginaJsp.objetosNavegacionPagina.NavegacionPaginaUbicacionPagina;
import com.sisnet.utilidades.ConversorFormatoFecha;
import com.sisnet.utilidades.ConversorNumerosLetras;
import com.sisnet.utilidades.Encriptor;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Iterator;
public class ManejadorAplicacion
{
  private static com.sisnet.aplicacion.manejadores.ManejadorAplicacion manejadorAplicacionSingleton = null;
  private static CamposAdministrador cad = CamposAdministrador.getCamposAdministrador();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  public static com.sisnet.aplicacion.manejadores.ManejadorAplicacion getManejadorAplicacion() {
    if (manejadorAplicacionSingleton == null) {
      manejadorAplicacionSingleton = new com.sisnet.aplicacion.manejadores.ManejadorAplicacion();
    }
    return manejadorAplicacionSingleton;
  }
  public ListaGeneral obtenerListaOpcionesGenero(String pDescripcionGeneroSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    
    if (pDescripcionGeneroSeleccionado == ConstantesGeneral.VALOR_NULO) {
      return listaGeneral_local;
    }
    
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("Femenino", "F", mc.sonCadenasIguales(pDescripcionGeneroSeleccionado, "F"));
      
      listaGeneral_local.adicionar("Masculino", "M", mc.sonCadenasIguales(pDescripcionGeneroSeleccionado, "M"));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public ListaGeneral obtenerListaOpcionesTipoIdentificacion(String pTipoIdentificacionSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    
    if (pTipoIdentificacionSeleccionado == ConstantesGeneral.VALOR_NULO) {
      return listaGeneral_local;
    }
    
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("C\u00e9dula de Ciudadan\u00eda", "C", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "C"));
      
      listaGeneral_local.adicionar("Nit", "N", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "N"));
      
      listaGeneral_local.adicionar("C\u00e9dula de Extranjeria", "E", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "E"));
      
      listaGeneral_local.adicionar("Pasaporte", "P", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "P"));
      
      listaGeneral_local.adicionar("Sociedad Extranjera sin Nit", "S", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "S"));
      
      listaGeneral_local.adicionar("Tarjeta Seguro Extranjera", "T", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "T"));
      
      listaGeneral_local.adicionar("Sin n\u00famero", "I", mc.sonCadenasIguales(pTipoIdentificacionSeleccionado, "I"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public ListaGeneral obtenerListaOpcionesTipoLicencia(String pTipoLicenciaSeleccionada) {
    ListaGeneral listaGeneral_local = null;
    
    if (pTipoLicenciaSeleccionada == ConstantesGeneral.VALOR_NULO) {
      return listaGeneral_local;
    }
    
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("Intranet", "I", mc.sonCadenasIgualesIgnorarMayusculas(pTipoLicenciaSeleccionada, "Intranet"));
      
      listaGeneral_local.adicionar("Web", "W", mc.sonCadenasIgualesIgnorarMayusculas(pTipoLicenciaSeleccionada, "Web"));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public String obtenerDescripcionTipoLicencia(String pTipoLicencia) {
    String descripcionTipoLicencia_local = "";
    
    if (pTipoLicencia == ConstantesGeneral.VALOR_NULO) {
      return descripcionTipoLicencia_local;
    }
    
    try {
      descripcionTipoLicencia_local = "Web";
      if (mc.sonCadenasIguales(pTipoLicencia, "I")) {
        descripcionTipoLicencia_local = "Intranet";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return descripcionTipoLicencia_local;
  }
  public ListaGeneral obtenerListaOpcionesTipoDato(String pTipoDatoSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("TEXTO", "T", (mc.sonCadenasIguales(pTipoDatoSeleccionado, "T") || mc.sonCadenasIguales(pTipoDatoSeleccionado, "")));
      
      listaGeneral_local.adicionar("N\u00daMERO ENTERO", "E", mc.sonCadenasIguales(pTipoDatoSeleccionado, "E"));
      
      listaGeneral_local.adicionar("N\u00daMERO CON DECIMALES", "R", mc.sonCadenasIguales(pTipoDatoSeleccionado, "R"));
      
      listaGeneral_local.adicionar("FECHA", "F", mc.sonCadenasIguales(pTipoDatoSeleccionado, "F"));
      
      listaGeneral_local.adicionar("HORA", "H", mc.sonCadenasIguales(pTipoDatoSeleccionado, "H"));
      
      listaGeneral_local.adicionar("CORREO ELECTR\u00D3NICO", "K", mc.sonCadenasIguales(pTipoDatoSeleccionado, "K"));
      
      listaGeneral_local.adicionar("DOCUMENTO", "DD", mc.sonCadenasIguales(pTipoDatoSeleccionado, "DD"));
      
      listaGeneral_local.adicionar("NUMERACI\u00D3N SEMIAUTOM\u00c1TICA NUM\u00C9RICA", "LL", mc.sonCadenasIguales(pTipoDatoSeleccionado, "LL"));
      
      listaGeneral_local.adicionar("NUMERACI\u00D3N SEMIAUTOM\u00c1TICA ALFANUM\u00C9RICA", "MM", mc.sonCadenasIguales(pTipoDatoSeleccionado, "MM"));
      
      listaGeneral_local.adicionar("P\u00c1RRAFO", "NN", mc.sonCadenasIguales(pTipoDatoSeleccionado, "NN"));
      
      listaGeneral_local.adicionar("ARCHIVO", "J", mc.sonCadenasIguales(pTipoDatoSeleccionado, "J"));
      
      listaGeneral_local.adicionar("CONSECUTIVO INTERNO", "W", mc.sonCadenasIguales(pTipoDatoSeleccionado, "W"));
      
      listaGeneral_local.adicionar("N\u00daMERO ENTERO S\u00D3LO POSITIVO", "BB", mc.sonCadenasIguales(pTipoDatoSeleccionado, "BB"));
      
      listaGeneral_local.adicionar("N\u00daMERO CON DECIMALES S\u00D3LO POSITIVO", "GG", mc.sonCadenasIguales(pTipoDatoSeleccionado, "GG"));
      
      listaGeneral_local.adicionar("N\u00daMERO ENTERO CON FORMATO", "XX", mc.sonCadenasIguales(pTipoDatoSeleccionado, "XX"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public ListaGeneral obtenerListaOpcionesDependientesEnlazado(int pTipoDependienteEnlaceSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("NO DEPENDIENTE ", String.valueOf(0), (pTipoDependienteEnlaceSeleccionado == 0));
      
      listaGeneral_local.adicionar("DEPENDIENTE DE ENLAZADO RECALCULABLE", String.valueOf(1), (pTipoDependienteEnlaceSeleccionado == 1));
      
      listaGeneral_local.adicionar("DEPENDIENTE DE ENLAZADO NO RECALCULABLE", String.valueOf(2), (pTipoDependienteEnlaceSeleccionado == 2));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public ListaGeneral obtenerListaOpcionesTipoHabilitacion(int pTipoHabilitacionSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("HABILITADO", String.valueOf(1), (pTipoHabilitacionSeleccionado == 1));
      
      listaGeneral_local.adicionar("NO HABILITADO PERO VISIBLE", String.valueOf(2), (pTipoHabilitacionSeleccionado == 2));
      
      listaGeneral_local.adicionar("OCULTO", String.valueOf(3), (pTipoHabilitacionSeleccionado == 3));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public ListaGeneral obtenerListaOpcionesTipoCampoCalculado(int pTipoCampoCalculadoSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("NO CALCULADO", String.valueOf(1), (pTipoCampoCalculadoSeleccionado == 1));
      
      listaGeneral_local.adicionar("IGUAL A OTRO CAMPO", String.valueOf(2), (pTipoCampoCalculadoSeleccionado == 2));
      
      listaGeneral_local.adicionar("IGUAL A OTRO CAMPO PRIMER REGISTRO", String.valueOf(3), (pTipoCampoCalculadoSeleccionado == 3));
      
      listaGeneral_local.adicionar("IGUAL A OTRO CAMPO \u00daLTIMO REGISTRO", String.valueOf(4), (pTipoCampoCalculadoSeleccionado == 4));
      
      listaGeneral_local.adicionar("IGUAL AL PRIMER CAMPO SI TIENE VALOR", String.valueOf(25), (pTipoCampoCalculadoSeleccionado == 25));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N SIN ESPACIOS", String.valueOf(12), (pTipoCampoCalculadoSeleccionado == 12));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N CON ESPACIOS", String.valueOf(11), (pTipoCampoCalculadoSeleccionado == 11));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N CON GUION", String.valueOf(21), (pTipoCampoCalculadoSeleccionado == 21));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N CON CAMBIO DE RENGL\u00D3N", String.valueOf(27), (pTipoCampoCalculadoSeleccionado == 27));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N COLUMNA SIN ESPACIOS", String.valueOf(22), (pTipoCampoCalculadoSeleccionado == 22));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N COLUMNA CON ESPACIOS", String.valueOf(23), (pTipoCampoCalculadoSeleccionado == 23));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N COLUMNA CON GUION", String.valueOf(24), (pTipoCampoCalculadoSeleccionado == 24));
      
      listaGeneral_local.adicionar("CONCATENACI\u00D3N COLUMNA CON CAMBIO DE RENGL\u00D3N", String.valueOf(28), (pTipoCampoCalculadoSeleccionado == 28));
      
      listaGeneral_local.adicionar("SUMA", String.valueOf(5), (pTipoCampoCalculadoSeleccionado == 5));
      
      listaGeneral_local.adicionar("RESTA", String.valueOf(6), (pTipoCampoCalculadoSeleccionado == 6));
      
      listaGeneral_local.adicionar("MULTIPLICACI\u00D3N", String.valueOf(7), (pTipoCampoCalculadoSeleccionado == 7));
      
      listaGeneral_local.adicionar("DIVISI\u00D3N", String.valueOf(8), (pTipoCampoCalculadoSeleccionado == 8));
      
      listaGeneral_local.adicionar("SUMAR UNIDAD", String.valueOf(14), (pTipoCampoCalculadoSeleccionado == 14));
      
      listaGeneral_local.adicionar("SUMA DESDE - HASTA", String.valueOf(26), (pTipoCampoCalculadoSeleccionado == 26));
      
      listaGeneral_local.adicionar("ELEVADO A LA", String.valueOf(43), (pTipoCampoCalculadoSeleccionado == 43));
      
      listaGeneral_local.adicionar("VALOR ABSOLUTO", String.valueOf(13), (pTipoCampoCalculadoSeleccionado == 13));
      
      listaGeneral_local.adicionar("SALDO COLUMNA", String.valueOf(10), (pTipoCampoCalculadoSeleccionado == 10));
      
      listaGeneral_local.adicionar("TOTAL COLUMNA", String.valueOf(9), (pTipoCampoCalculadoSeleccionado == 9));
      
      listaGeneral_local.adicionar("SI DIFERENTE DE CERO", String.valueOf(20), (pTipoCampoCalculadoSeleccionado == 20));
      
      listaGeneral_local.adicionar("SI IGUAL A CERO", String.valueOf(19), (pTipoCampoCalculadoSeleccionado == 19));
      
      listaGeneral_local.adicionar("SI MENOR A CERO", String.valueOf(15), (pTipoCampoCalculadoSeleccionado == 15));
      
      listaGeneral_local.adicionar("SI MENOR O IGUAL A CERO", String.valueOf(16), (pTipoCampoCalculadoSeleccionado == 16));
      
      listaGeneral_local.adicionar("SI MAYOR QUE CERO", String.valueOf(17), (pTipoCampoCalculadoSeleccionado == 17));
      
      listaGeneral_local.adicionar("SI MAYOR O IGUAL A CERO", String.valueOf(18), (pTipoCampoCalculadoSeleccionado == 18));
      
      listaGeneral_local.adicionar("N\u00daMERO INTERNO DE LA FECHA", String.valueOf(30), (pTipoCampoCalculadoSeleccionado == 30));
      
      listaGeneral_local.adicionar("N\u00daMERO INTERNO DE LA HORA", String.valueOf(29), (pTipoCampoCalculadoSeleccionado == 29));
      
      listaGeneral_local.adicionar("FECHA INICIO DE A\u00d1O ACTUAL", String.valueOf(37), (pTipoCampoCalculadoSeleccionado == 37));
      
      listaGeneral_local.adicionar("FECHA INICIO DE A\u00d1O ANTERIOR", String.valueOf(39), (pTipoCampoCalculadoSeleccionado == 39));
      
      listaGeneral_local.adicionar("FECHA INICIO DE A\u00d1O SIGUIENTE", String.valueOf(41), (pTipoCampoCalculadoSeleccionado == 41));
      
      listaGeneral_local.adicionar("FECHA INICIO DE MES ACTUAL", String.valueOf(31), (pTipoCampoCalculadoSeleccionado == 31));
      
      listaGeneral_local.adicionar("FECHA INICIO DE MES ANTERIOR", String.valueOf(33), (pTipoCampoCalculadoSeleccionado == 33));
      
      listaGeneral_local.adicionar("FECHA INICIO DE MES SIGUIENTE", String.valueOf(35), (pTipoCampoCalculadoSeleccionado == 35));
      
      listaGeneral_local.adicionar("FECHA FIN DE A\u00d1O ACTUAL", String.valueOf(38), (pTipoCampoCalculadoSeleccionado == 38));
      
      listaGeneral_local.adicionar("FECHA FIN DE A\u00d1O ANTERIOR", String.valueOf(40), (pTipoCampoCalculadoSeleccionado == 40));
      
      listaGeneral_local.adicionar("FECHA FIN DE A\u00d1O SIGUIENTE", String.valueOf(42), (pTipoCampoCalculadoSeleccionado == 42));
      
      listaGeneral_local.adicionar("FECHA FIN DE MES ACTUAL", String.valueOf(32), (pTipoCampoCalculadoSeleccionado == 32));
      
      listaGeneral_local.adicionar("FECHA FIN DE MES ANTERIOR", String.valueOf(34), (pTipoCampoCalculadoSeleccionado == 34));
      
      listaGeneral_local.adicionar("FECHA FIN DE MES SIGUIENTE", String.valueOf(36), (pTipoCampoCalculadoSeleccionado == 36));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  public ListaGeneral obtenerListaOpcionesTipoBloqueoUsuario(int pTipoBloqueoSeleccionado) {
    ListaGeneral listaGeneral_local = null;
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("Habilitado", String.valueOf(1), (pTipoBloqueoSeleccionado == 1));
      
      listaGeneral_local.adicionar("Bloqueado Manualmente", String.valueOf(2), (pTipoBloqueoSeleccionado == 2));
      
      listaGeneral_local.adicionar("Bloqueo por contrase\u00f1a fallida", String.valueOf(3), (pTipoBloqueoSeleccionado == 3));
      
      listaGeneral_local.adicionar("Bloqueo por vencimiento de contrase\u00f1a", String.valueOf(4), (pTipoBloqueoSeleccionado == 4));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaGeneral_local;
  }
  private ListaCampo obtenerCamposTablaAplicacion() {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdAplicacion(true));
      listaCampo_local.adicionar(cad.obtenerCampoNombreAplicacion());
      listaCampo_local.adicionar(cad.obtenerCampoTituloAplicacion());
      listaCampo_local.adicionar(cad.obtenerCampoEsTabla());
      listaCampo_local.adicionar(cad.obtenerCampoIdAplicacionConsulta());
      listaCampo_local.adicionar(cad.obtenerCampoIdAplicacionReporte());
      listaCampo_local.adicionar(cad.obtenerCampoActualizarInformacionEnlazada());
      listaCampo_local.adicionar(cad.obtenerCampoAplicacionesActualizar());
      listaCampo_local.adicionar(cad.obtenerCampoAdvertenciaEjecucion());
      listaCampo_local.adicionar(cad.obtenerCampoPermitirConsultaGeneral());
      listaCampo_local.adicionar(cad.obtenerCampoTamanoMaximoArchivos());
      listaCampo_local.adicionar(cad.obtenerCampoEsOculta());
      listaCampo_local.adicionar(cad.obtenerCampoHacerDobleCalculo());
      listaCampo_local.adicionar(cad.obtenerCampoNumeroRegistrosPagina());
      listaCampo_local.adicionar(cad.obtenerCampoTipoEventosUsuario());
      listaCampo_local.adicionar(cad.obtenerCampoEventosAcciones());
      listaCampo_local.adicionar(cad.obtenerCampoIdAplicacionImpresionMasiva());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaTabla() {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdTabla());
      listaCampo_local.adicionar(cad.obtenerCampoNombreTabla());
      listaCampo_local.adicionar(cad.obtenerCampoDescripcionTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaGrupoInformacion() {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdGrupoInformacion(true));
      listaCampo_local.adicionar(cad.obtenerCampoIdAplicacion(false));
      listaCampo_local.adicionar(cad.obtenerCampoNombreGrupoInformacion());
      listaCampo_local.adicionar(cad.obtenerCampoDescripcionGrupoInformacion());
      listaCampo_local.adicionar(cad.obtenerCampoGrupoInformacionPrincipal());
      listaCampo_local.adicionar(cad.obtenerCampoGrupoInformacionMultiple());
      listaCampo_local.adicionar(cad.obtenerCampoPosicion());
      listaCampo_local.adicionar(cad.obtenerCampoMostrarDetalle());
      listaCampo_local.adicionar(cad.obtenerCampoMargenSuperiorGrupoInformacion());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaCampo(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdCampo(pNombreBaseDatos, true));
      listaCampo_local.adicionar(cad.obtenerCampoIdGrupoInformacion(false));
      listaCampo_local.adicionar(cad.obtenerCampoNombreCampo());
      listaCampo_local.adicionar(cad.obtenerCampoEtiquetaCampo());
      listaCampo_local.adicionar(cad.obtenerCampoSeudonimo());
      listaCampo_local.adicionar(cad.obtenerCampoTipoDato());
      listaCampo_local.adicionar(cad.obtenerCampoEsImagen());
      listaCampo_local.adicionar(cad.obtenerCampoAltoImagenPantallaPresentacion());
      listaCampo_local.adicionar(cad.obtenerCampoAltoImagenPantallaEdicion());
      listaCampo_local.adicionar(cad.obtenerCampoTienePlantilla());
      listaCampo_local.adicionar(cad.obtenerCampoIdAplicacionPlantilla());
      listaCampo_local.adicionar(cad.obtenerCampoValorUnico());
      listaCampo_local.adicionar(cad.obtenerCampoLongitudCampo());
      listaCampo_local.adicionar(cad.obtenerCampoNumeroDecimales());
      listaCampo_local.adicionar(cad.obtenerCampoAnchoColumna());
      listaCampo_local.adicionar(cad.obtenerCampoCambiarRenglon());
      listaCampo_local.adicionar(cad.obtenerCampoMargenSuperiorCampo());
      listaCampo_local.adicionar(cad.obtenerCampoMargenIzquierdaEtiquetaCampo());
      listaCampo_local.adicionar(cad.obtenerCampoMargenIzquierdaControlCampo());
      listaCampo_local.adicionar(cad.obtenerCampoAnchoEtiquetaCampo());
      listaCampo_local.adicionar(cad.obtenerCampoAnchoControlCampo());
      listaCampo_local.adicionar(cad.obtenerCampoCantidadRenglonesControlCampo());
      listaCampo_local.adicionar(cad.obtenerCampoLlavePrimaria());
      listaCampo_local.adicionar(cad.obtenerCampoLlaveForanea());
      listaCampo_local.adicionar(cad.obtenerCampoObligatorio());
      listaCampo_local.adicionar(cad.obtenerCampoVisibleUsuarioPrincipal());
      listaCampo_local.adicionar(cad.obtenerCampoVisibleUsuarioSecundario());
      listaCampo_local.adicionar(cad.obtenerCampoModificable());
      listaCampo_local.adicionar(cad.obtenerCampoPosicion());
      listaCampo_local.adicionar(cad.obtenerCampoHabilitadoPor());
      listaCampo_local.adicionar(cad.obtenerCampoBorrarValorNoHabilitado());
      listaCampo_local.adicionar(cad.obtenerCampoListaDependiente());
      listaCampo_local.adicionar(cad.obtenerCampoEnlazado());
      listaCampo_local.adicionar(cad.obtenerCampoFiltradoRegistrosEnlazados());
      listaCampo_local.adicionar(cad.obtenerCampoCampoOrigenFiltrado());
      listaCampo_local.adicionar(cad.obtenerCampoValorFiltrado());
      listaCampo_local.adicionar(cad.obtenerCampoCampoDestinoFiltrado());
      listaCampo_local.adicionar(cad.obtenerCampoDependienteEnlazado());
      listaCampo_local.adicionar(cad.obtenerCampoOpcionDesconocido());
      listaCampo_local.adicionar(cad.obtenerCampoEnlaceDepende());
      listaCampo_local.adicionar(cad.obtenerCampoOrigenEnlace());
      listaCampo_local.adicionar(cad.obtenerCampoTipoHabilitacion());
      listaCampo_local.adicionar(cad.obtenerCampoOcultarEtiquetaControlExaminar());
      listaCampo_local.adicionar(cad.obtenerCampoOcultarEtiquetaControlVer());
      listaCampo_local.adicionar(cad.obtenerCampoCampoCalculado());
      listaCampo_local.adicionar(cad.obtenerCampoEsRecalculable());
      listaCampo_local.adicionar(cad.obtenerCampoIdCampoValor());
      listaCampo_local.adicionar(cad.obtenerCampoIdCampoOrigenUno());
      listaCampo_local.adicionar(cad.obtenerCampoFormatoCampoOrigenUno());
      listaCampo_local.adicionar(cad.obtenerCampoIdCampoOrigenDos());
      listaCampo_local.adicionar(cad.obtenerCampoFormatoCampoOrigenDos());
      listaCampo_local.adicionar(cad.obtenerCampoFormatoCampoCalculado());
      listaCampo_local.adicionar(cad.obtenerCampoIncluirOpcionConsulta());
      listaCampo_local.adicionar(cad.obtenerCampoRecargarPantalla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaValorDependiente(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdValorDependiente(pNombreBaseDatos));
      listaCampo_local.adicionar(cad.obtenerCampoIdCampo(pNombreBaseDatos, false));
      listaCampo_local.adicionar(cad.obtenerCampoIdValorMaestro(pNombreBaseDatos));
      listaCampo_local.adicionar(cad.obtenerCampoIdValorDetalle(pNombreBaseDatos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaDependienteHabilitacion(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdDependienteHabilitacion(pNombreBaseDatos));
      listaCampo_local.adicionar(cad.obtenerCampoIdCampo(pNombreBaseDatos, false));
      listaCampo_local.adicionar(cad.obtenerCampoIdValorMaestro(pNombreBaseDatos));
      listaCampo_local.adicionar(cad.obtenerCampoHabilitacion(pNombreBaseDatos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaTipoUsuario() {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdTipoUsuario(true));
      listaCampo_local.adicionar(cad.obtenerCampoNombreTipoUsuario());
      listaCampo_local.adicionar(cad.obtenerCampoPermitirUtilizarMenu());
      listaCampo_local.adicionar(cad.obtenerCampoNivelAcceso());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerCamposTablaUsuario(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    Campo campoContrasena_local = null;
    Campo campoConfirmarContrasena_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoIdUsuario(pNombreBaseDatos));
      listaCampo_local.adicionar(cad.obtenerCampoNombreUsuario("Nombre Cuenta",""));
      listaCampo_local.adicionar(cad.obtenerCampoNombreCompletoUsuario());
      listaCampo_local.adicionar(cad.obtenerCampoTipoLicencia());
      listaCampo_local.adicionar(cad.obtenerCampoIdTipoUsuario(false));
      listaCampo_local.adicionar(cad.obtenerCampoFechaVencimiento());
      listaCampo_local.adicionar(cad.obtenerCampoDiasVigenciaContrasena());
      listaCampo_local.adicionar(cad.obtenerCampoContrasenasFallidasPermitidas());
      listaCampo_local.adicionar(cad.obtenerCampoFechaUltimaContrasenaFallida());
      listaCampo_local.adicionar(cad.obtenerCampoCantidadContrasenasFallidas());
      listaCampo_local.adicionar(cad.obtenerCampoTiempoSesion());
      listaCampo_local.adicionar(cad.obtenerCampoTipoBloqueo());
      campoContrasena_local = cad.obtenerCampoContrasena("Contrase\u00f1a", null);
      
      campoContrasena_local.setObligatorio(true);
      campoContrasena_local.setModificable(true);
      campoContrasena_local.setVisibleUsuarioPrincipal(false);
      campoContrasena_local.setExcluirValidacionContrasena(false);
      listaCampo_local.adicionar(campoContrasena_local);
      campoConfirmarContrasena_local = cad.obtenerCampoConfirmarContrasena();
      campoConfirmarContrasena_local.setVisibleUsuarioPrincipal(false);
      listaCampo_local.adicionar(campoConfirmarContrasena_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoContrasena_local = null;
      campoConfirmarContrasena_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposTabla(String pNombreBaseDatos, int pIdTabla) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      switch (pIdTabla) {
        case 1:
        case 10:
        case 11:
          listaCampo_local = obtenerCamposTablaAplicacion();
          break;
        case 2:
          listaCampo_local = obtenerCamposTablaGrupoInformacion();
          break;
        case 3:
          listaCampo_local = obtenerCamposTablaTabla();
          break;
        case 4:
          listaCampo_local = obtenerCamposTablaCampo(pNombreBaseDatos);
          break;
        case 5:
          listaCampo_local = obtenerCamposTablaUsuario(pNombreBaseDatos);
          break;
        case 6:
          listaCampo_local = obtenerCamposTablaValorTabla(pNombreBaseDatos);
          break;
        case 7:
          listaCampo_local = obtenerCamposTablaValorDependiente(pNombreBaseDatos);
          break;
        case 8:
          listaCampo_local = obtenerCamposTablaDependienteHabilitacion(pNombreBaseDatos);
          break;
        case 9:
          listaCampo_local = obtenerCamposTablaTipoUsuario();
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public String obtenerNombrePaginaAplicacion(int pNumeroPagina) {
    String nombrePagina_local = "";
    try {
      switch (pNumeroPagina) {
        
        case 4:
          nombrePagina_local = "sisnet.jsp";
          break;
        case 3:
          nombrePagina_local = "login.jsp";
          break;
        case 1:
          nombrePagina_local = "consultageneral.jsp";
          break;
        case 5:
          nombrePagina_local = "incluirregistro.jsp";
          break;
        case 7:
          nombrePagina_local = "modificarregistro.jsp";
          break;
        case 21:
          nombrePagina_local = "loginacceso.jsp";
          break;
        case 22:
          nombrePagina_local = "cambiarcontrasena.jsp";
          break;
        
        case 12:
          nombrePagina_local = "consultaprincipal.jsp";
          break;
        case 13:
          nombrePagina_local = "incluirregistroprincipal.jsp";
          break;
        case 14:
          nombrePagina_local = "modificarregistroprincipal.jsp";
          break;
        case 15:
          nombrePagina_local = "consultagrupoinformacion.jsp";
          break;
        case 16:
          nombrePagina_local = "incluirregistroaplicacion.jsp";
          break;
        case 17:
          nombrePagina_local = "incluirvalortabla.jsp";
          break;
        case 18:
          nombrePagina_local = "dependencias.jsp";
          break;
        case 19:
          nombrePagina_local = "fckeditor/editortexto/editor.jsp";
          break;
        case 20:
          nombrePagina_local = "exportacion.jsp";
          break;
        case 23:
          nombrePagina_local = "importacion.jsp";
          break;
        case 24:
          nombrePagina_local = "motoraplicacion.jsp";
          break;
        case 25:
          nombrePagina_local = "mensaje.jsp";
          break;
        case 26:
          nombrePagina_local = "descargararchivo.jsp";
          break;
        case 27:
          nombrePagina_local = "fckeditor/editortexto/impresionmasiva.jsp";
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombrePagina_local;
  }
  private ListaCampo obtenerCamposTablaValorTabla(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoLlavePrimariaTabla(pNombreBaseDatos));
      listaCampo_local.adicionar(cad.obtenerCampoValorTabla(pNombreBaseDatos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposLogin(boolean pExistenAplicaciones) {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoSeleccionaAplicacion(pExistenAplicaciones));
      listaCampo_local.adicionar(cad.obtenerCampoNombreUsuario("Nombre Cuenta", "Escriba el nombre de la cuenta"));
      listaCampo_local.adicionar(cad.obtenerCampoContrasena("Contrase\u00f1a", "Escriba su Contrase\u00f1a"));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposLoginAcceso() {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoNombreUsuario("Nombre Cuenta", "Escriba el nombre de la cuenta"));
      listaCampo_local.adicionar(cad.obtenerCampoContrasena("Contrase\u00f1a", "Escriba su Contrase\u00f1a"));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposCambioContrasena() {
    ListaCampo listaCampo_local = null;
    Campo campoContrasena_local = null;
    
    try {
      campoContrasena_local = cad.obtenerCampoContrasena("Nueva Contrase\u00f1a", null);
      campoContrasena_local.setExcluirValidacionContrasena(false);
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(campoContrasena_local);
      listaCampo_local.adicionar(cad.obtenerCampoConfirmarContrasena());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoContrasena_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposCambiarUsuarioAdministrador() {
    ListaCampo listaCampo_local = null;
    Campo campoContrasena_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoNombreUsuario("Nuevo Nombre Usuario", ""));
      campoContrasena_local = cad.obtenerCampoContrasena("Nueva Contrase\u00f1a", null);
      campoContrasena_local.setExcluirValidacionContrasena(false);
      listaCampo_local.adicionar(campoContrasena_local);
      listaCampo_local.adicionar(cad.obtenerCampoConfirmarContrasena());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoContrasena_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposImportacion(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoArchivoImportar(pNombreBaseDatos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCamposConfiguracion() {
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.adicionar(cad.obtenerCampoSuperUsuario());
      listaCampo_local.adicionar(cad.obtenerCampoContrasenaSuperUsuario());
      listaCampo_local.adicionar(cad.obtenerCampoNumeroPuertoConexion());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaGeneralCampos(String pNombreBaseDatos) {
    ListaCampo listaCampo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaAplicacion());
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaTabla());
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaGrupoInformacion());
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaCampo(pNombreBaseDatos));
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaUsuario(pNombreBaseDatos));
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaValorDependiente(pNombreBaseDatos));
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaDependienteHabilitacion(pNombreBaseDatos));
      listaCampo_local.concatenarListaCampo(obtenerCamposTablaTipoUsuario());
      if (listaCampo_local.contarElementos() == 0) {
        listaCampo_local = null;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public String obtenerEtiquetaCampoPorNombreCampo(String pNombreBaseDatos, String pNombreCampo) {
    String etiquetacampo_local = "";
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return etiquetacampo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return etiquetacampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      listaCampo_local = obtenerListaGeneralCampos(pNombreBaseDatos);
      iterator_local = listaCampo_local.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (mc.sonCadenasIguales(campo_local.getNombreCampo(), pNombreCampo)) {
          etiquetacampo_local = campo_local.getEtiquetaCampo();
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterator_local = null;
      campo_local = null;
    } 
    
    return etiquetacampo_local;
  }
  public String obtenerNombreLlavePrimariaTablaAdministrador(int pIdGrupoInformacion) {
    String nombreLLavePrimaria = "";
    try {
      switch (pIdGrupoInformacion) {
        case 1:
        case 10:
        case 11:
          nombreLLavePrimaria = "fldidaplicacion";
          break;
        case 2:
          nombreLLavePrimaria = "fldidgrupoinformacion";
          break;
        case 3:
          nombreLLavePrimaria = "fldidtabla";
          break;
        case 4:
          nombreLLavePrimaria = "fldidcampo";
          break;
        case 5:
          nombreLLavePrimaria = "fldidusuario";
          break;
        case 9:
          nombreLLavePrimaria = "fldidtipousuario";
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreLLavePrimaria;
  }
  public boolean obtenerEquivalenteBooleanoValor(String pCadena) {
    boolean equivalenteBooleanoValor_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return equivalenteBooleanoValor_local;
    }
    try {
      equivalenteBooleanoValor_local = mc.sonCadenasIguales(pCadena, "Si");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return equivalenteBooleanoValor_local;
  }
  public ConexionPostgres obtenerConexionPostgres(String pRutaArchivo) {
    ConexionPostgres conexionPostgres_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return conexionPostgres_local;
    }
    
    try {
      manejadorArchivos_local = new ManejadorArchivos();
      if (manejadorArchivos_local.existeArchivo(pRutaArchivo)) {
        conexionPostgres_local = (ConexionPostgres)manejadorArchivos_local.recuperarObjetoArchivo(pRutaArchivo);
      } else {
        conexionPostgres_local = new ConexionPostgres();
        manejadorArchivos_local.guardarObjetoArchivo(pRutaArchivo, conexionPostgres_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorArchivos_local = null;
    } 
    
    return conexionPostgres_local;
  }
  public ObjetoConexionBaseDatos obtenerConexionBaseDatosSisnet(String pNombreBaseDatos, String rutaArchivo) {
    ObjetoConexionBaseDatos objetoConexionBaseDatos_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return objetoConexionBaseDatos_local;
    }
    
    try {
    	ConexionPostgres conexionPostgres_local = this.obtenerConexionPostgres(rutaArchivo);
      objetoConexionBaseDatos_local = new ObjetoConexionBaseDatos("org.postgresql.Driver", ConstantesBaseDatos.const_UbicacionServidor, String.valueOf(conexionPostgres_local.getNumeroPuertoConexion()) 
    		  /*"5432"*/, pNombreBaseDatos, "postgres", "postgres");
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return objetoConexionBaseDatos_local;
  }
  public ObjetoConexionBaseDatos obtenerConexionBaseDatosPostgres(ConexionPostgres pConexionPostgres) {
    ObjetoConexionBaseDatos objetoConexionBaseDatos_local = null;
    
    if (pConexionPostgres == ConstantesGeneral.VALOR_NULO) {
      return objetoConexionBaseDatos_local;
    }
    
    try {
      objetoConexionBaseDatos_local = new ObjetoConexionBaseDatos("org.postgresql.Driver", ConstantesBaseDatos.const_UbicacionServidor, String.valueOf(pConexionPostgres.getNumeroPuertoConexion()), "postgres", pConexionPostgres.getSuperUsuario(), pConexionPostgres.getContrasenaSuperUsuario());
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return objetoConexionBaseDatos_local;
  }
  public ObjetoConexionBaseDatos obtenerConexionBaseDatosAplicacion(String pNombreAplicacion, String rutaArchivo) {
    ObjetoConexionBaseDatos objetoConexionBaseDatos_local = null;
    
    if (pNombreAplicacion == ConstantesGeneral.VALOR_NULO) {
      return objetoConexionBaseDatos_local;
    }
    
    try {
    	ConexionPostgres conexionPostgres_local = this.obtenerConexionPostgres(rutaArchivo);

      objetoConexionBaseDatos_local = new ObjetoConexionBaseDatos("org.postgresql.Driver", ConstantesBaseDatos.const_UbicacionServidor,String.valueOf(conexionPostgres_local.getNumeroPuertoConexion()) 
    		  /*"5432"*/, pNombreAplicacion, "postgres", "postgres");
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return objetoConexionBaseDatos_local;
  }
  public String obtenerHoraActualSistema() {
    String horaActual_local = "";
    SimpleDateFormat simpleDateFormat_local = null;
    java.sql.Date fechaSistema_local = null;
    
    try {
      simpleDateFormat_local = new SimpleDateFormat("HH:mm");
      fechaSistema_local = new java.sql.Date(new java.util.Date().getTime());
      horaActual_local = simpleDateFormat_local.format(fechaSistema_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      simpleDateFormat_local = null;
      fechaSistema_local = null;
    } 
    return horaActual_local;
  }
  public String obtenerNombreGrupoInformacionAdministrador(int pIdGrupoInformacion) {
    String nombreGrupoInformacionAdministrador = "";
    
    try {
      switch (pIdGrupoInformacion) {
        case 1:
        case 10:
        case 11:
          nombreGrupoInformacionAdministrador = "APLICACION";
          break;
        case 2:
          nombreGrupoInformacionAdministrador = "GRUPOINFORMACION";
          break;
        case 3:
          nombreGrupoInformacionAdministrador = "TABLA";
          break;
        case 4:
          nombreGrupoInformacionAdministrador = "CAMPO";
          break;
        case 7:
          nombreGrupoInformacionAdministrador = "VALORDEPENDIENTE";
          break;
        case 6:
          nombreGrupoInformacionAdministrador = "CAMPO";
          break;
        case 5:
          nombreGrupoInformacionAdministrador = "USUARIO";
          break;
        case 9:
          nombreGrupoInformacionAdministrador = "TIPOUSUARIO";
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreGrupoInformacionAdministrador;
  }
  public String obtenerDescripcionGrupoInformacionAdministrador(int pIdGrupoInformacion, String pEstado) {
    String nombreGrupoInformacionAdministrador = "";
    boolean consultando_local = false;
    boolean modificando_local = false;
    
    if (pEstado == ConstantesGeneral.VALOR_NULO) {
      return nombreGrupoInformacionAdministrador;
    }
    
    try {
      consultando_local = mc.sonCadenasIguales(pEstado, "Consultando");
      modificando_local = mc.sonCadenasIguales(pEstado, "Modificando");
      switch (pIdGrupoInformacion) {
        case 1:
          nombreGrupoInformacionAdministrador = "APLICACI\u00D3N";
          if (modificando_local) {
            nombreGrupoInformacionAdministrador = "PROPIEDADES DE LA APLICACI\u00D3N";
          }
          break;
        case 10:
          nombreGrupoInformacionAdministrador = "TABLA";
          if (modificando_local) {
            nombreGrupoInformacionAdministrador = "PROPIEDADES DE LA APLICACI\u00D3N";
          }
          break;
        case 11:
          nombreGrupoInformacionAdministrador = "EVENTOS - ACCIONES";
          break;
        case 2:
          nombreGrupoInformacionAdministrador = "GRUPO DE INFORMACI\u00D3N";
          if (consultando_local) {
            nombreGrupoInformacionAdministrador = "GRUPOS DE INFORMACI\u00D3N";
          }
          break;
        case 3:
          nombreGrupoInformacionAdministrador = "TABLA";
          if (consultando_local) {
            nombreGrupoInformacionAdministrador = "TABLAS";
          }
          break;
        case 4:
          nombreGrupoInformacionAdministrador = "CAMPO";
          if (consultando_local) {
            nombreGrupoInformacionAdministrador = "CAMPOS";
          }
          break;
        case 5:
          nombreGrupoInformacionAdministrador = "USUARIO";
          break;
        case 9:
          nombreGrupoInformacionAdministrador = "TIPO USUARIO";
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreGrupoInformacionAdministrador;
  }
  public String conformarNombreCampoLlavePrimaria(String pNombreCampo) {
    String nombreCampoLlavePrimaria_local = "";
    
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return nombreCampoLlavePrimaria_local;
    }
    
    try {
      nombreCampoLlavePrimaria_local = pNombreCampo;
      if (!mc.comienzaCon(pNombreCampo, "fldid")) {
        nombreCampoLlavePrimaria_local = mc.convertirAMinusculas(mc.concatenarCadena("fldid", pNombreCampo));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreCampoLlavePrimaria_local;
  }
  public int obtenerAnchoCamposConfiguracion(String pNombreBaseDatos, int pIdGrupoInformacion) {
    int anchoCamposTipoTexto_local = 0;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return anchoCamposTipoTexto_local;
    }
    
    try {
      listaCampo_local = obtenerCamposTabla(pNombreBaseDatos, pIdGrupoInformacion);
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.esVisibleUsuarioPrincipal()) {
          anchoCamposTipoTexto_local += campo_local.getAnchoColumna();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterador_local = null;
      campo_local = null;
    } 
    
    return anchoCamposTipoTexto_local;
  }
  private String obtenerNumeroConFormato(String pValorCampo, boolean pEsTipoDatoNumeroEntero, String pFormatoAplicar, String pComplementoFormato) {
    String numeroConFormato_local = "";
    String valorInicial_local = null;
    String valorEntero_local = null;
    String valorSeparador_local = null;
    String valorDecimal_local = null;
    String[] valores_local = null;
    ConversorNumerosLetras conversorNumerosLetras_local = null;
    
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return numeroConFormato_local;
    }
    if (pFormatoAplicar == ConstantesGeneral.VALOR_NULO) {
      return numeroConFormato_local;
    }
    if (pComplementoFormato == ConstantesGeneral.VALOR_NULO) {
      return numeroConFormato_local;
    }
    
    try {
      numeroConFormato_local = pValorCampo;
      if (mc.esCadenaNumerica(pValorCampo, pEsTipoDatoNumeroEntero)) {
        if (!mc.sonCadenasIguales(pFormatoAplicar, "NOO")) {
          if (pEsTipoDatoNumeroEntero) {
            numeroConFormato_local = mc.formatearNumeroEntero(Long.parseLong(pValorCampo));
          } else {
            numeroConFormato_local = mc.formatearNumeroReal(Double.parseDouble(pValorCampo));
          } 
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "SPA")) {
          valorInicial_local = "";
          valorEntero_local = "";
          valorSeparador_local = "";
          valorDecimal_local = "";
          if (!mc.sonCadenasIguales(pComplementoFormato, "") && 
            mc.verificarExistenciaSubCadena(pComplementoFormato, String.valueOf(','))) {
            valores_local = mc.fraccionarCadena(pComplementoFormato, String.valueOf(','));
            if (valores_local.length > 3) {
              valorInicial_local = valores_local[0] + ' ';
              valorEntero_local = valores_local[1] + ' ';
              valorSeparador_local = valores_local[2] + ' ';
              valorDecimal_local = valores_local[3] + ' ';
            }
            else if (valores_local.length > 2) {
              valorEntero_local = valores_local[0] + ' ';
              valorSeparador_local = valores_local[1] + ' ';
              valorDecimal_local = valores_local[2] + ' ';
            } 
          } 
          
          conversorNumerosLetras_local = new ConversorNumerosLetras();
          numeroConFormato_local = conversorNumerosLetras_local.obtenerNumeroEnLetras(pValorCampo, 1, valorInicial_local, valorEntero_local, valorSeparador_local, valorDecimal_local);
          
          if (mc.sonCadenasIguales(numeroConFormato_local, "")) {
            numeroConFormato_local = "XXXXXXXXXXXXXXXXXXXX";
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valores_local = null;
      valorEntero_local = null;
      valorDecimal_local = null;
      valorInicial_local = null;
      valorSeparador_local = null;
      conversorNumerosLetras_local = null;
    } 
    return numeroConFormato_local;
  }
  public String obtenerHoraConFormato(String pFormatoAplicar, String pValorCampo) {
    String horaConFormato_local = "";
    SimpleDateFormat simpleDateFormat_local = null;
    if (pFormatoAplicar == ConstantesGeneral.VALOR_NULO) {
      return horaConFormato_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return horaConFormato_local;
    }
    try {
      horaConFormato_local = pValorCampo;
      if (mc.obtenerLongitudCadena(pValorCampo) == 5) {
        horaConFormato_local = mc.concatenarCadena(horaConFormato_local, String.valueOf(':'));
        horaConFormato_local = mc.concatenarCadena(horaConFormato_local, "00");
      } 
      if (mc.verificarFormatoHora(horaConFormato_local) && 
        !mc.sonCadenasIguales(horaConFormato_local, "")) {
        if (mc.sonCadenasIguales(pFormatoAplicar, "H12")) {
          simpleDateFormat_local = new SimpleDateFormat("hh:mm a");
          horaConFormato_local = simpleDateFormat_local.format(Time.valueOf(horaConFormato_local));
        } else if (mc.sonCadenasIguales(pFormatoAplicar, "H24")) {
          simpleDateFormat_local = new SimpleDateFormat("HH:mm");
          horaConFormato_local = simpleDateFormat_local.format(Time.valueOf(horaConFormato_local));
        }
      
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      simpleDateFormat_local = null;
    } 
    return horaConFormato_local;
  }
  private String obtenerGeneroConFormato(String pValorCampo, String pFormatoAplicar) {
    String generoConFormato_local = "";
    boolean esFemenino_local = false;
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return generoConFormato_local;
    }
    if (pFormatoAplicar == ConstantesGeneral.VALOR_NULO) {
      return generoConFormato_local;
    }
    try {
      esFemenino_local = mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo, "Femenino");
      generoConFormato_local = pValorCampo;
      if (esFemenino_local) {
        if (mc.sonCadenasIguales(pFormatoAplicar, "SENSPA")) {
          generoConFormato_local = "se\u00f1ora";
        }
        
        if (mc.sonCadenasIguales(pFormatoAplicar, "DOCSPA")) {
          generoConFormato_local = "doctora";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ABOSPA")) {
          generoConFormato_local = "abogada";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLSPA")) {
          generoConFormato_local = "la";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELASPA")) {
          generoConFormato_local = "ella";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLSENSPA")) {
          generoConFormato_local = "la se\u00f1ora";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLDOCSPA")) {
          generoConFormato_local = "la doctora";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLABOSPA")) {
          generoConFormato_local = "la abogada";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "AA1SPA")) {
          generoConFormato_local = "a";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "AA2SPA")) {
          generoConFormato_local = "a";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLSPA")) {
          generoConFormato_local = "a la";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLSENSPA")) {
          generoConFormato_local = "a la se\u00f1ora";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLABOSPA")) {
          generoConFormato_local = "a la abogada";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLDOCSPA")) {
          generoConFormato_local = "a la doctora";
        }
      } else {
        
        if (mc.sonCadenasIguales(pFormatoAplicar, "SENSPA")) {
          generoConFormato_local = "se\u00f1or";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "DOCSPA")) {
          generoConFormato_local = "doctor";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ABOSPA")) {
          generoConFormato_local = "abogado";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLSPA")) {
          generoConFormato_local = "el";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELASPA")) {
          generoConFormato_local = "\u00e9l";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLSENSPA")) {
          generoConFormato_local = "el se\u00f1or";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLDOCSPA")) {
          generoConFormato_local = "el doctor";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ELLABOSPA")) {
          generoConFormato_local = "el abogado";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "AA1SPA")) {
          generoConFormato_local = "o";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "AA2SPA")) {
          generoConFormato_local = "";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLSPA")) {
          generoConFormato_local = "al";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLSENSPA")) {
          generoConFormato_local = "al se\u00f1or";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLABOSPA")) {
          generoConFormato_local = "al abogado";
        }
        if (mc.sonCadenasIguales(pFormatoAplicar, "ALLDOCSPA")) {
          generoConFormato_local = "al doctor";
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return generoConFormato_local;
  }
  public String obtenerValorCampoConFormato(String pValorCampo, boolean pEsTipoDatoNumeroEntero, boolean pEsTipoDatoNumeroReal, boolean pEsTipoDatoFecha, boolean pEsTipoDatoHora, String pFormatoAplicar, String pComplementoFormato) {
    String valorCampoPlantillaConformato_local = "";
    ConversorFormatoFecha conversorFormatoFecha_local = null;
    
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoPlantillaConformato_local;
    }
    if (pFormatoAplicar == ConstantesGeneral.VALOR_NULO) {
      return valorCampoPlantillaConformato_local;
    }
    if (pComplementoFormato == ConstantesGeneral.VALOR_NULO) {
      return valorCampoPlantillaConformato_local;
    }
    
    try {
      valorCampoPlantillaConformato_local = pValorCampo;
      if (!mc.sonCadenasIguales(pValorCampo, "")) {
        if (pEsTipoDatoNumeroEntero || pEsTipoDatoNumeroReal) {
          valorCampoPlantillaConformato_local = obtenerNumeroConFormato(pValorCampo, pEsTipoDatoNumeroEntero, pFormatoAplicar, pComplementoFormato);
        }
        
        if (pEsTipoDatoHora) {
          if (mc.sonCadenasIguales(pFormatoAplicar, "")) {
            pFormatoAplicar = "H24";
          }
          valorCampoPlantillaConformato_local = obtenerHoraConFormato(pFormatoAplicar, pValorCampo);
        } 
        if (pEsTipoDatoFecha && !mc.esCadenaVacia(pValorCampo) && !mc.sonCadenasIguales(pValorCampo, "null")) {
          
          conversorFormatoFecha_local = new ConversorFormatoFecha();
          valorCampoPlantillaConformato_local = conversorFormatoFecha_local.obtenerFechaConFormato(pFormatoAplicar, pValorCampo);
        } 
        if (mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo, "Femenino") || mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo, "Masculino"))
        {
          valorCampoPlantillaConformato_local = obtenerGeneroConFormato(pValorCampo, pFormatoAplicar);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      conversorFormatoFecha_local = null;
    } 
    return valorCampoPlantillaConformato_local;
  }
  public String obtenerValorCampoConEstilo(String pValorCampo, String pEstiloAplicar) {
    String valorPlantilla_local = "";
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorPlantilla_local;
    }
    if (pEstiloAplicar == ConstantesGeneral.VALOR_NULO) {
      return valorPlantilla_local;
    }
    try {
      valorPlantilla_local = pValorCampo;
      if (mc.sonCadenasIguales(pEstiloAplicar, "MIN")) {
        valorPlantilla_local = mc.convertirAMinusculas(pValorCampo);
      }
      if (mc.sonCadenasIguales(pEstiloAplicar, "MAY")) {
        valorPlantilla_local = mc.convertirAMayusculas(pValorCampo);
      }
      if (mc.sonCadenasIguales(pEstiloAplicar, "CAP")) {
        valorPlantilla_local = mc.convertirEstiloLetraCapital(pValorCampo);
      }
      if (mc.sonCadenasIguales(pEstiloAplicar, "TIT")) {
        valorPlantilla_local = mc.darFormatoTitulo(pValorCampo);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {}
    
    return valorPlantilla_local;
  }
  public String obtenerLimitesSubcadena(String pCadenaFormato) {
    String cadenaExtraer_local = "";
    int posicionPorcentaje_local = -1;
    
    if (pCadenaFormato == ConstantesGeneral.VALOR_NULO) {
      return cadenaExtraer_local;
    }
    try {
      posicionPorcentaje_local = mc.obtenerPosicionSubCadena(pCadenaFormato, String.valueOf('%'));
      if (posicionPorcentaje_local != -1) {
        cadenaExtraer_local = mc.obtenerSubCadena(pCadenaFormato, mc.obtenerPosicionSubCadena(pCadenaFormato, String.valueOf('(')) + 1, mc.obtenerPosicionSubCadena(pCadenaFormato, String.valueOf(')')));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadenaExtraer_local;
  }
  public String obtenerConsecutivoCadena(String pCadena) {
    String cadena_local = "";
    String parteAlfabetica_local = null;
    int parteNumerica_local = -1;
    int longitudCadenaNumericaReal_local = -1;
    String consecutivo_local = null;
    int longitudCadenaNumerica_local = -1;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    
    try {
      if (mc.esNumero(pCadena.charAt(mc.obtenerLongitudCadena(pCadena) - 1))) {
        parteAlfabetica_local = mc.obtenerParteAlfabeticaCadena(pCadena);
        parteNumerica_local = mc.obtenerParteNumericaCadena(pCadena);
        longitudCadenaNumericaReal_local = mc.obtenerLongitudCadena(pCadena) - mc.obtenerLongitudCadena(parteAlfabetica_local);
        
        parteNumerica_local++;
        consecutivo_local = String.valueOf(parteNumerica_local);
        longitudCadenaNumerica_local = mc.obtenerLongitudCadena(consecutivo_local);
        if (longitudCadenaNumerica_local < longitudCadenaNumericaReal_local) {
          consecutivo_local = mc.completarCadena(consecutivo_local, false, '0', longitudCadenaNumericaReal_local);
        }
        
        cadena_local = parteAlfabetica_local + consecutivo_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      parteAlfabetica_local = null;
      consecutivo_local = null;
    } 
    return cadena_local;
  }
  public int calcularAnchoTablaOpcionesConsulta() {
    int anchoTablaOpcionesConsulta_local = 0;
    try {
      anchoTablaOpcionesConsulta_local = 430 + Integer.valueOf("200").intValue() * 2;
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return anchoTablaOpcionesConsulta_local;
  }
  public String configuracionVersion() {
    String configuracionVersion_local = "";
    
    try {
      configuracionVersion_local = mc.concatenarCadena(configuracionVersion_local, "Sisnet Web ");
      configuracionVersion_local = mc.concatenarCadena(configuracionVersion_local, " B. ");
      configuracionVersion_local = mc.concatenarCadena(configuracionVersion_local, String.valueOf(40).toString());
      configuracionVersion_local = mc.concatenarCadena(configuracionVersion_local, String.valueOf(' '));
      configuracionVersion_local = mc.concatenarCadena(configuracionVersion_local, String.valueOf(ConstantesVersion.FECHA_VERSION).toString());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return configuracionVersion_local;
  }
  public String conformarBloqueCamposTablaCampo() {
    String bloqueCamposTablaCampo = "";
    try {
      bloqueCamposTablaCampo = "fldidcampo,CAMPO.fldidgrupoinformacion,fldnombrecampo,fldetiquetacampo,fldseudonimo,fldtipodato,fldtieneplantilla,fldidaplicacionplantilla,fldvalorunico,fldlongitudcampo,fldnumerodecimales,fldanchocolumna,fldllaveprimaria,fldllaveforanea,fldobligatorio,fldvisibleusuarioprincipal,fldvisibleusuariosecundario,fldmodificable,CAMPO.fldposicion,fldhabilitadopor,fldborrarvalornohabilitado,fldlistadependiente,fldenlazado,fldfiltradoregistrosenlazados,fldcampoorigenfiltrado,fldcampodestinofiltrado,fldvalorfiltrado,flddependienteenlazado,fldopciondesconocido,fldcampoenlacedepende,fldcampoorigenenlace,fldtipohabilitacion,fldcampocalculado,fldesrecalculable,fldidcampovalor,fldidcampoorigenuno,fldformatocampoorigenuno,fldidcampoorigendos,fldformatocampoorigendos,fldformatocampocalculado,fldincluiropcionconsulta,fldrecargarpantalla,fldcambiarrenglon,fldmargensuperiorcampo,fldanchoetiquetacampo,fldanchocontrolcampo,fldcantidadrenglonescontrolcampo,fldmargenizquierdaetiquetacampo,fldmargenizquierdacontrolcampo,fldesimagen,fldaltoimagenpantallapresentacion,fldaltoimagenpantallaedicion,fldocultaretiquetacontrolexaminar,fldocultaretiquetacontrolver";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaCampo;
  }
  public String conformarBloqueCamposTablaCampoInsercion() {
    String bloqueCamposTablaCampo = "";
    try {
      bloqueCamposTablaCampo = "fldidcampo,fldidgrupoinformacion,fldnombrecampo,fldetiquetacampo,fldseudonimo,fldtipodato,fldtieneplantilla,fldidaplicacionplantilla,fldvalorunico,fldlongitudcampo,fldnumerodecimales,fldanchocolumna,fldllaveprimaria,fldllaveforanea,fldobligatorio,fldvisibleusuarioprincipal,fldvisibleusuariosecundario,fldmodificable,fldposicion,fldhabilitadopor,fldborrarvalornohabilitado,fldlistadependiente,fldenlazado,fldfiltradoregistrosenlazados,fldcampoorigenfiltrado,fldcampodestinofiltrado,fldvalorfiltrado,flddependienteenlazado,fldopciondesconocido,fldcampoenlacedepende,fldcampoorigenenlace,fldtipohabilitacion,fldcampocalculado,fldesrecalculable,fldidcampovalor,fldidcampoorigenuno,fldformatocampoorigenuno,fldidcampoorigendos,fldformatocampoorigendos,fldformatocampocalculado,fldincluiropcionconsulta,fldrecargarpantalla,fldcambiarrenglon,fldmargensuperiorcampo,fldanchoetiquetacampo,fldanchocontrolcampo,fldcantidadrenglonescontrolcampo,fldmargenizquierdaetiquetacampo,fldmargenizquierdacontrolcampo,fldesimagen,fldaltoimagenpantallapresentacion,fldaltoimagenpantallaedicion,fldocultaretiquetacontrolexaminar,fldocultaretiquetacontrolver";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaCampo;
  }
  public String conformarBloqueCamposTablaGrupoInformacion() {
    String bloqueCamposTablaGrupoInformacion_local = "";
    try {
      bloqueCamposTablaGrupoInformacion_local = "GRUPOINFORMACION.fldidgrupoinformacion,GRUPOINFORMACION.fldidaplicacion,fldnombregrupoinformacion,flddescripciongrupoinformacion,fldgrupoinformacionprincipal,fldgrupoinformacionmultiple,GRUPOINFORMACION.fldposicion,fldmostrardetalle,fldmargensuperiorgrupoinformacion";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaGrupoInformacion_local;
  }
  public String conformarBloqueCamposTablaGrupoInformacionInsercion() {
    String bloqueCamposTablaGrupoInformacion_local = "";
    try {
      bloqueCamposTablaGrupoInformacion_local = "fldidgrupoinformacion,fldidaplicacion,fldnombregrupoinformacion,flddescripciongrupoinformacion,fldgrupoinformacionprincipal,fldgrupoinformacionmultiple,fldposicion,fldmostrardetalle,fldmargensuperiorgrupoinformacion";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaGrupoInformacion_local;
  }
  public String conformarBloqueCamposTablaAplicacion() {
    String bloqueCamposTablaAplicacion_local = "";
    try {
      bloqueCamposTablaAplicacion_local = "APLICACION.fldidaplicacion,fldnombreaplicacion,fldtituloaplicacion,fldestabla,fldidaplicacionconsulta,fldidaplicacionreporte,fldactualizarinformacionenlazada,fldaplicacionesactualizar,fldadvertenciaejecucion,fldpermitirconsultageneral,fldtamanomaximoarchivos,fldesoculta,fldhacerdoblecalculo,fldnumeroregistrospagina,fldtipoeventosusuario,fldeventosacciones,fldidaplicacionimpresionmasiva";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaAplicacion_local;
  }
  public String conformarBloqueCamposTablaAplicacionInsercion() {
    String bloqueCamposTablaAplicacion_local = "";
    try {
      bloqueCamposTablaAplicacion_local = "fldidaplicacion,fldnombreaplicacion,fldtituloaplicacion,fldestabla,fldidaplicacionconsulta,fldidaplicacionreporte,fldactualizarinformacionenlazada,fldaplicacionesactualizar,fldadvertenciaejecucion,fldpermitirconsultageneral,fldtamanomaximoarchivos,fldesoculta,fldhacerdoblecalculo,fldnumeroregistrospagina,fldtipoeventosusuario,fldeventosacciones";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaAplicacion_local;
  }
  public String conformarBloqueCamposTablaTabla() {
    String bloqueCamposTablaTabla_local = "";
    try {
      bloqueCamposTablaTabla_local = "fldidtabla,fldnombretabla,flddescripciontabla";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaTabla_local;
  }
  public String conformarBloqueCamposTablaUsuario() {
    String bloqueCamposTablaUsuario_local = "";
    
    try {
      bloqueCamposTablaUsuario_local = "fldidusuario,fldnombreusuario,fldcontrasena,fldnombrecompletousuario,fldtipolicencia,fldfechavencimiento,USUARIO.fldidtipousuario,flddiasvigenciacontrasena,fldcontrasenasfallidaspermitidas,fldfechaultimacontrasenafallida,fldcantidadcontrasenasfallidas,fldtiemposesion,fldtipobloqueo,fldasignacionadministrador";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaUsuario_local;
  }
  public String conformarBloqueCamposTablaUsuarioInsercion() {
    String bloqueCamposTablaUsuario_local = "";
    try {
      bloqueCamposTablaUsuario_local = "fldidusuario,fldnombreusuario,fldcontrasena,fldnombrecompletousuario,fldtipolicencia,fldfechavencimiento,fldidtipousuario,flddiasvigenciacontrasena,fldcontrasenasfallidaspermitidas,fldfechaultimacontrasenafallida,fldcantidadcontrasenasfallidas,fldtiemposesion,fldtipobloqueo,fldasignacionadministrador";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaUsuario_local;
  }
  public String conformarBloqueCamposTablaTipoUsuario() {
    String bloqueCamposTablaTipoUsuario_local = "";
    try {
      bloqueCamposTablaTipoUsuario_local = "TIPOUSUARIO.fldidtipousuario,fldnombretipousuario,fldpermitirutilizarmenu,fldnivelacceso";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaTipoUsuario_local;
  }
  public String conformarBloqueCamposTablaTipoUsuarioInsercion() {
    String bloqueCamposTablaTipoUsuario_local = "";
    try {
      bloqueCamposTablaTipoUsuario_local = "fldidtipousuario,fldnombretipousuario,fldpermitirutilizarmenu,fldnivelacceso";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return bloqueCamposTablaTipoUsuario_local;
  }
  public NavegacionPaginaAplicacion obtenerNuevoElementoNavegacionAplicacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria, Tabla pTablaActual, Tabla pTablaDepende, Campo pCampoArchivo) {
    NavegacionPaginaAplicacion navegacionPaginaAplicacion_local = null;
    
    try {
      navegacionPaginaAplicacion_local = new NavegacionPaginaAplicacion(pGrupoInformacion, pValorLlavePrimaria, pTablaActual, pTablaDepende, pCampoArchivo);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return navegacionPaginaAplicacion_local;
  }
  public NavegacionPaginaEstado obtenerNuevoElementoNavegacionEstado(String pEstadoActual, int pAccion, int pNumeroPagina, int pNumeroError, int pTipoError) {
    NavegacionPaginaEstado navegacionPaginaEstado_local = null;
    try {
      navegacionPaginaEstado_local = new NavegacionPaginaEstado(pEstadoActual, pAccion, pNumeroPagina, pNumeroError, pTipoError);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return navegacionPaginaEstado_local;
  }
  public NavegacionPaginaUbicacionPagina obtenerNuevoElementoNavegacionUbicacionAplicacion(boolean pConfiguracion, boolean pRecargarPagina, boolean pEsDocumento, int pPlantillaUtilizar, boolean pEjecutarConsulta) {
    NavegacionPaginaUbicacionPagina navegacionPaginaUbicacionPagina_local = null;
    try {
      navegacionPaginaUbicacionPagina_local = new NavegacionPaginaUbicacionPagina(pConfiguracion, pRecargarPagina, pEsDocumento, pPlantillaUtilizar, pEjecutarConsulta);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return navegacionPaginaUbicacionPagina_local;
  }
  public String obtenerEstiloValorCampo(String pCadenaFormatoCampo) {
    String estilo_local = "";
    int posicionAsterisco_local = -1;
    
    if (pCadenaFormatoCampo == ConstantesGeneral.VALOR_NULO) {
      return estilo_local;
    }
    
    try {
      posicionAsterisco_local = mc.obtenerPosicionSubCadena(pCadenaFormatoCampo, String.valueOf('*'));
      if (posicionAsterisco_local != -1) {
        estilo_local = mc.obtenerSubCadena(pCadenaFormatoCampo, posicionAsterisco_local + 1, mc.obtenerLongitudCadena(pCadenaFormatoCampo));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return estilo_local;
  }
  public String obtenerComplementoFormatoNumericoCampo(String pCadenaFormatoCampo) {
    String valorAplicar_local = "";
    int posicionParentesisCuadrado_local = -1;
    
    if (pCadenaFormatoCampo == ConstantesGeneral.VALOR_NULO) {
      return valorAplicar_local;
    }
    
    try {
      posicionParentesisCuadrado_local = mc.obtenerPosicionSubCadena(pCadenaFormatoCampo, String.valueOf('['));
      if (posicionParentesisCuadrado_local != -1) {
        valorAplicar_local = mc.obtenerSubCadena(pCadenaFormatoCampo, posicionParentesisCuadrado_local + 1, mc.obtenerPosicionSubCadena(pCadenaFormatoCampo, String.valueOf(']')));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return valorAplicar_local;
  }
  public String obtenerFormatoValorCampo(String pCadenaFormatoCampo) {
    String formato_local = "";
    int posicionParentesisCuadrado_local = -1;
    int posicionAsterisco_local = -1;
    int posicionFinal_local = -1;
    
    if (pCadenaFormatoCampo == ConstantesGeneral.VALOR_NULO) {
      return formato_local;
    }
    
    try {
      posicionParentesisCuadrado_local = mc.obtenerPosicionSubCadena(pCadenaFormatoCampo, String.valueOf('['));
      posicionAsterisco_local = mc.obtenerPosicionSubCadena(pCadenaFormatoCampo, String.valueOf('*'));
      posicionFinal_local = mc.obtenerLongitudCadena(pCadenaFormatoCampo);
      if (posicionAsterisco_local != -1) {
        posicionFinal_local = posicionAsterisco_local;
      }
      if (posicionParentesisCuadrado_local != -1) {
        posicionFinal_local = posicionParentesisCuadrado_local;
      }
      formato_local = mc.obtenerSubCadena(pCadenaFormatoCampo, 0, posicionFinal_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return formato_local;
  }
  public String obtenerContenidoEntreComillas(String pCadena) {
    String contenido_local = "";
    int posicionComillasInicial_local = -1;
    int posicionComillasFinal_local = -1;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return pCadena;
    }
    
    try {
      posicionComillasInicial_local = mc.obtenerPosicionSubCadena(pCadena, String.valueOf('"')) + 1;
      
      posicionComillasFinal_local = mc.obtenerUltimaPosicionSubCadena(pCadena, String.valueOf('"'));
      if (posicionComillasInicial_local != -1 && posicionComillasFinal_local != -1)
      {
        contenido_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pCadena, posicionComillasInicial_local, posicionComillasFinal_local));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return contenido_local;
  }
  public String obtenerContrasenaDinamicaAdministradorSistema() {
    String contrasenaDinamica_local = "";
    int numeroContrasena_local = 0;
    int posicion_local = 0;
    String fecha_local = null;
    Encriptor encriptor_local = null;
    
    try {
      fecha_local = mc.reemplazarCadena(mf.obtenerFechaActualSistema(true), String.valueOf('-'), "");
      
      for (posicion_local = 0; posicion_local < mc.obtenerLongitudCadena(fecha_local); posicion_local++) {
        numeroContrasena_local += Integer.parseInt(mc.obtenerSubCadena(fecha_local, posicion_local, posicion_local + 1));
      }
      
      numeroContrasena_local = 5027 + numeroContrasena_local;
      encriptor_local = new Encriptor(String.valueOf(numeroContrasena_local), false);
      contrasenaDinamica_local = mc.convertirAMayusculas(encriptor_local.encriptarCadena());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fecha_local = null;
      encriptor_local = null;
    } 
    return contrasenaDinamica_local;
  }
  public ListaCampo extraerListaCamposImportarGrupoInformacionMultiple(int pIdGrupoInformacion, ListaCampo pListaCampo) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      iterador_local = pListaCampo.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaGeneral obtenerListaGeneralAplicaciones(ListaAplicacion pListaAplicacion, int pIdAplicacionSeleccionada, boolean pInsertarEscojaValor) {
    ListaGeneral listaGeneral_local = null;
    Iterator iterator_local = null;
    Aplicacion aplicacion_local = null;
    
    if (pListaAplicacion == ConstantesGeneral.VALOR_NULO) {
      return listaGeneral_local;
    }
    
    try {
      listaGeneral_local = new ListaGeneral();
      
      if (pInsertarEscojaValor) {
        listaGeneral_local.adicionar("Escoja un valor", String.valueOf(0), (0 == pIdAplicacionSeleccionada));
      }
      
      iterator_local = pListaAplicacion.iterator();
      while (iterator_local.hasNext()) {
        aplicacion_local = (Aplicacion)iterator_local.next();
        listaGeneral_local.adicionar(aplicacion_local.getTituloAplicacion(), String.valueOf(aplicacion_local.getIdAplicacion()), (aplicacion_local.getIdAplicacion() == pIdAplicacionSeleccionada));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      aplicacion_local = null;
    } 
    
    return listaGeneral_local;
  }
  public Campo conformarCampoLlavePrimaria(GrupoInformacion pGrupoInformacion) {
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple() || pGrupoInformacion.esGrupoInformacionPrincipal()) {
        campo_local = new Campo();
        campo_local.setGrupoInformacion(pGrupoInformacion);
        campo_local.setNombreCampo(conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion()));
        campo_local.setSeudonimo(conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion()));
        campo_local.getFormatoCampo().setTipoDato("E");
        campo_local.getRestriccionCampo().setLlavePrimaria(true);
        campo_local.setObligatorio(true);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo conformarCampoLlaveForanea(GrupoInformacion pGrupoInformacion) {
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        campo_local = new Campo();
        campo_local.setGrupoInformacion(pGrupoInformacion);
        campo_local.setNombreCampo(conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion()));
        campo_local.setSeudonimo(conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion()));
        campo_local.getFormatoCampo().setTipoDato("E");
        campo_local.getFormatoCampo().setLongitudCampo(10);
        campo_local.getRestriccionCampo().setLlaveForanea(true);
        campo_local.setObligatorio(true);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public String complementarNombreCampoNombreArchivo(String pNombreCampo) {
    String nombreCampoArchivo_local = "";
    
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return nombreCampoArchivo_local;
    }
    
    try {
      nombreCampoArchivo_local = mc.convertirAMinusculas(mc.concatenarCadena("nombrearchivo", pNombreCampo));
      
      if (mc.obtenerLongitudCadena(nombreCampoArchivo_local) > 30) {
        nombreCampoArchivo_local = mc.obtenerSubCadena(nombreCampoArchivo_local, 0, 30);
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreCampoArchivo_local;
  }
  public Campo conformarCampoNombreArchivo(Campo pCampoArchivo) {
    Campo campo_local = null;
    
    if (pCampoArchivo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(pCampoArchivo.getGrupoInformacion());
      campo_local.setNombreCampo(complementarNombreCampoNombreArchivo(pCampoArchivo.getNombreCampo()));
      campo_local.setSeudonimo(complementarNombreCampoNombreArchivo(pCampoArchivo.getNombreCampo()));
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(100);
      campo_local.setModificable(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  private GrupoInformacion obtenerGrupoInformacionAplicacion(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(1);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("APLICACION");
      grupoInformacion_local.setDescripcionGrupoInformacion("APLICACI\u00D3N");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
      grupoInformacion_local.setMargenSuperior(0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionAplicacionTabla(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(10);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("APLICACION");
      grupoInformacion_local.setDescripcionGrupoInformacion("TABLA");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
      grupoInformacion_local.setMargenSuperior(0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionAplicacionEventosAcciones(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(11);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("APLICACION");
      grupoInformacion_local.setDescripcionGrupoInformacion("EVENTOS - ACCIONES");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
      grupoInformacion_local.setMargenSuperior(0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionTabla(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(3);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("TABLA");
      grupoInformacion_local.setDescripcionGrupoInformacion("TABLA");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionGrupoInformacion(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(2);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("GRUPOINFORMACION");
      grupoInformacion_local.setDescripcionGrupoInformacion("GRUPO DE INFORMACI\u00D3N");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionCampo(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(4);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("CAMPO");
      grupoInformacion_local.setDescripcionGrupoInformacion("CAMPO");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
      grupoInformacion_local.setMargenSuperior(0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionValorDependiente(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(7);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("VALORDEPENDIENTE");
      grupoInformacion_local.setDescripcionGrupoInformacion("VALOR DEPENDIENTE");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionDependienteHabilitacion(Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(8);
      grupoInformacion_local.setAplicacion(pAplicacion);
      grupoInformacion_local.setNombreGrupoInformacion("DEPENDIENTEHABILITACION");
      grupoInformacion_local.setDescripcionGrupoInformacion("DEPENDIENTE HABILITACI\u00D3N");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public Aplicacion obtenerAplicacionAdministradorSisnetWeb(String pNombreBaseDatos) {
    Aplicacion aplicacion_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    
    try {
      aplicacion_local = new Aplicacion();
      aplicacion_local.setIdAplicacion(0);
      aplicacion_local.setNombreAplicacion(pNombreBaseDatos);
      aplicacion_local.setTituloAplicacion("Administrador Web Sisnet Ltda");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return aplicacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionTipoUsuario(String pNombreBaseDatos) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(9);
      grupoInformacion_local.setAplicacion(obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos));
      grupoInformacion_local.setNombreGrupoInformacion("TIPOUSUARIO");
      grupoInformacion_local.setDescripcionGrupoInformacion("TIPO USUARIO");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionUsuario(String pNombreBaseDatos) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(5);
      grupoInformacion_local.setAplicacion(obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos));
      grupoInformacion_local.setNombreGrupoInformacion("USUARIO");
      grupoInformacion_local.setDescripcionGrupoInformacion("USUARIO");
      grupoInformacion_local.setGrupoInformacionMultiple(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private GrupoInformacion obtenerGrupoInformacionCambiarContrasena(String pNombreBaseDatos, int pIdGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    try {
      grupoInformacion_local = new GrupoInformacion();
      grupoInformacion_local.setIdGrupoInformacion(pIdGrupoInformacion);
      grupoInformacion_local.setAplicacion(obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos));
      grupoInformacion_local.setNombreGrupoInformacion("Cambiar Contrase\u00f1a");
      grupoInformacion_local.setDescripcionGrupoInformacion("Cambiar Contrase\u00f1a");
      grupoInformacion_local.setMargenSuperior(0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionPorId(int pIdGrupoInformacion, Aplicacion pAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      switch (pIdGrupoInformacion) {
        case 1:
          grupoInformacion_local = obtenerGrupoInformacionAplicacion(pAplicacion);
          break;
        case 10:
          grupoInformacion_local = obtenerGrupoInformacionAplicacionTabla(pAplicacion);
          break;
        case 11:
          grupoInformacion_local = obtenerGrupoInformacionAplicacionEventosAcciones(pAplicacion);
          break;
        case 3:
          grupoInformacion_local = obtenerGrupoInformacionTabla(pAplicacion);
          break;
        case 2:
          grupoInformacion_local = obtenerGrupoInformacionGrupoInformacion(pAplicacion);
          break;
        case 4:
          grupoInformacion_local = obtenerGrupoInformacionCampo(pAplicacion);
          break;
        case 7:
          grupoInformacion_local = obtenerGrupoInformacionValorDependiente(pAplicacion);
          break;
        case 8:
          grupoInformacion_local = obtenerGrupoInformacionDependienteHabilitacion(pAplicacion);
          break;
        case 9:
          grupoInformacion_local = obtenerGrupoInformacionTipoUsuario(pAplicacion.getNombreAplicacion());
          break;
        case 5:
          grupoInformacion_local = obtenerGrupoInformacionUsuario(pAplicacion.getNombreAplicacion());
          break;
        case 12:
        case 13:
          grupoInformacion_local = obtenerGrupoInformacionCambiarContrasena(pAplicacion.getNombreAplicacion(), pIdGrupoInformacion);
          break;
      } 
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public TipoUsuario obtenerTipoUsuarioAdministradorSistema() {
    TipoUsuario tipoUsuario_local = null;
    
    try {
      tipoUsuario_local = new TipoUsuario();
      tipoUsuario_local.setIdTipoUsuario(0);
      tipoUsuario_local.setNombreTipoUsuario("ADMINISTRADOR");
      tipoUsuario_local.setPermitirUtilizarMenu(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoUsuario_local;
  }
  public TipoUsuario obtenerTipoUsuarioLocal() {
    TipoUsuario tipoUsuario_local = null;
    
    try {
      tipoUsuario_local = new TipoUsuario();
      tipoUsuario_local.setIdTipoUsuario(1000);
      tipoUsuario_local.setNombreTipoUsuario("LOCAL");
      tipoUsuario_local.setPermitirUtilizarMenu(false);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoUsuario_local;
  }
  public String obtenerNombreArchivo(String pRutaArchivo) {
    String nombreArchivo_local = "";
    String rutaArchivo_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return nombreArchivo_local;
    }
    
    try {
      nombreArchivo_local = pRutaArchivo;
      rutaArchivo_local = mc.reemplazarCadena(pRutaArchivo, "\\\\", String.valueOf('/'));
      
      if (mc.verificarExistenciaSubCadena(rutaArchivo_local, String.valueOf('/'))) {
        nombreArchivo_local = mc.obtenerSubCadena(rutaArchivo_local, mc.obtenerUltimaPosicionSubCadena(rutaArchivo_local, String.valueOf('/')) + 1, mc.obtenerLongitudCadena(rutaArchivo_local));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      rutaArchivo_local = null;
    } 
    
    return nombreArchivo_local;
  }
  public String obtenerSeparadorArchivos() {
    return System.getProperty("file.separator");
  }
  public ListaCadenas copiarListaCadenas(ListaCadenas pListaCadenas) {
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return listaCadenas_local;
    }
    
    try {
      listaCadenas_local = new ListaCadenas();
      iterador_local = pListaCadenas.iterator();
      while (iterador_local.hasNext()) {
        listaCadenas_local.adicionar((String)iterador_local.next());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return listaCadenas_local;
  }
  public String obtenerHoraActualSistemaFormatoLargo() {
    String horaActual_local = "";
    SimpleDateFormat simpleDateFormat_local = null;
    Date fechaSistema_local = null;
    
    try {
      simpleDateFormat_local = new SimpleDateFormat("h:mm:ss a");
      fechaSistema_local = new java.sql.Date(new java.util.Date().getTime());
      horaActual_local = simpleDateFormat_local.format(fechaSistema_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      simpleDateFormat_local = null;
      fechaSistema_local = null;
    } 
    return horaActual_local;
  }
  public ListaGeneral obtenerListaOpcionesFiltradoRegistrosEnlazados(int pDescripcionFiltradoRegistrosEnlazados) {
    ListaGeneral listaGeneral_local = null;
    
    if (Integer.valueOf(pDescripcionFiltradoRegistrosEnlazados) == ConstantesGeneral.VALOR_NULO) {
      return listaGeneral_local;
    }
    
    try {
      listaGeneral_local = new ListaGeneral();
      listaGeneral_local.adicionar("Escoja un valor", String.valueOf(0), (0 == pDescripcionFiltradoRegistrosEnlazados));
      
      listaGeneral_local.adicionar("TODOS LOS REGISTROS", String.valueOf(1), (pDescripcionFiltradoRegistrosEnlazados == 1));
      
      listaGeneral_local.adicionar("FILTRADO POR IGUAL A", String.valueOf(2), (pDescripcionFiltradoRegistrosEnlazados == 2));
      
      listaGeneral_local.adicionar("FILTRADO POR NO IGUAL A", String.valueOf(3), (pDescripcionFiltradoRegistrosEnlazados == 3));
      
      listaGeneral_local.adicionar("FILTRADO POR MENOR QUE", String.valueOf(4), (pDescripcionFiltradoRegistrosEnlazados == 4));
      
      listaGeneral_local.adicionar("FILTRADO POR MAYOR QUE", String.valueOf(5), (pDescripcionFiltradoRegistrosEnlazados == 5));
      
      listaGeneral_local.adicionar("FILTRADO POR MENOR O IGUAL A", String.valueOf(6), (pDescripcionFiltradoRegistrosEnlazados == 6));
      
      listaGeneral_local.adicionar("FILTRADO POR MAYOR O IGUAL A", String.valueOf(7), (pDescripcionFiltradoRegistrosEnlazados == 7));
      
      listaGeneral_local.adicionar("FILTRADO POR COMIENZA CON", String.valueOf(8), (pDescripcionFiltradoRegistrosEnlazados == 8));
      
      listaGeneral_local.adicionar("FILTRADO POR COMIENZA CON PERO NO IGUAL A", String.valueOf(9), (pDescripcionFiltradoRegistrosEnlazados == 9));
      
      listaGeneral_local.adicionar("FILTRADO POR NO COMIENZA CON", String.valueOf(10), (pDescripcionFiltradoRegistrosEnlazados == 10));
      
      listaGeneral_local.adicionar("FILTRADO POR CONTIENE A", String.valueOf(11), (pDescripcionFiltradoRegistrosEnlazados == 11));
      
      listaGeneral_local.adicionar("FILTRADO POR CONTIENE A PERO NO IGUAL A", String.valueOf(12), (pDescripcionFiltradoRegistrosEnlazados == 12));
      
      listaGeneral_local.adicionar("FILTRADO POR NO CONTIENE A", String.valueOf(13), (pDescripcionFiltradoRegistrosEnlazados == 13));
      
      listaGeneral_local.adicionar("FILTRADO POR CONTIENE PALABRAS", String.valueOf(14), (pDescripcionFiltradoRegistrosEnlazados == 14));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaGeneral_local;
  }
  public ListaCondiciones obtenerListaCondicionesRelacionAplicativos(String pBloqueCondiciones) {
    ListaCondiciones listaCondiciones_local = null;
    int posicionAbreParentesisSiguiente_local = -1;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String operador_local = "";
    String condiciones_local = null;
    String expresion_local = null;
    
    try {
      condiciones_local = pBloqueCondiciones;
      listaCondiciones_local = new ListaCondiciones();
      while (mc.verificarExistenciaSubCadena(condiciones_local, String.valueOf('(')) && mc.verificarExistenciaSubCadena(condiciones_local, String.valueOf(')')))
      {
        posicionInicial_local = mc.obtenerPosicionSubCadena(condiciones_local, String.valueOf('(')) + 1;
        
        posicionFinal_local = mc.obtenerPosicionSubCadena(condiciones_local, String.valueOf(')'));
        
        expresion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(condiciones_local, posicionInicial_local, posicionFinal_local));
        operador_local = "";
        if (mc.verificarExistenciaSubCadenaAPartirPosicion(condiciones_local, String.valueOf('('), posicionFinal_local)) {
          
          posicionAbreParentesisSiguiente_local = mc.obtenerPosicionSubCadenaAPartirPosicion(condiciones_local, String.valueOf('('), posicionFinal_local);
          
          operador_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(condiciones_local, posicionFinal_local + 1, posicionAbreParentesisSiguiente_local));
          
          operador_local = mc.obtenerOperadorCondicionalEquivalente(operador_local);
        } 
        if (!listaCondiciones_local.verificarExistenciaExpresionCondicion(expresion_local) && mc.verificarExpresionCondicionValida(expresion_local))
        {
          listaCondiciones_local.adicionar(expresion_local, operador_local, false);
        }
        if (posicionAbreParentesisSiguiente_local != -1) {
          condiciones_local = mc.obtenerSubCadena(condiciones_local, posicionAbreParentesisSiguiente_local, mc.obtenerLongitudCadena(condiciones_local));
          continue;
        } 
        condiciones_local = "";
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      operador_local = null;
      expresion_local = null;
      condiciones_local = null;
    } 
    
    return listaCondiciones_local;
  }
  private ListaCadenas obtenerListaComparadoresRelacionAplicativos() {
    ListaCadenas listaComparadores_local = null;
    
    try {
      listaComparadores_local = new ListaCadenas();
      listaComparadores_local.adicionar("ESCONTENIDOPORPERONOIGUALA");
      listaComparadores_local.adicionar("COMIENZACONPERONOIGUALA");
      listaComparadores_local.adicionar("CONTIENEAPERONOIGUALA");
      listaComparadores_local.adicionar("ESCOMIENZODEPERONOIGUALA");
      listaComparadores_local.adicionar("NOIGUALA");
      listaComparadores_local.adicionar("NOESCONTENIDOPOR");
      listaComparadores_local.adicionar("NOCOMIENZACON");
      listaComparadores_local.adicionar("NOCONTIENEA");
      listaComparadores_local.adicionar("NOESCOMIENZODE");
      listaComparadores_local.adicionar(String.valueOf('='));
      listaComparadores_local.adicionar("MENOROIGUALA");
      listaComparadores_local.adicionar("MAYOROIGUALA");
      listaComparadores_local.adicionar("MENORQUE");
      listaComparadores_local.adicionar("MAYORQUE");
      listaComparadores_local.adicionar("ESCONTENIDOPOR");
      listaComparadores_local.adicionar("COMIENZACON");
      listaComparadores_local.adicionar("CONTIENEA");
      listaComparadores_local.adicionar("ESCOMIENZODE");
      listaComparadores_local.adicionar("NOCONTIENEPALABRAS");
      listaComparadores_local.adicionar("CONTIENEPALABRAS");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaComparadores_local;
  }
  public String obtenerOperadorExpresion(String pExpresion) {
    String operadorExpresion_local = "";
    String operador_local = null;
    Iterator iteradorOperadores_local = null;
    ListaCadenas listaOperadores_local = null;
    
    if (pExpresion == ConstantesGeneral.VALOR_NULO) {
      return operadorExpresion_local;
    }
    
    try {
      listaOperadores_local = obtenerListaComparadoresRelacionAplicativos();
      iteradorOperadores_local = listaOperadores_local.iterator();
      while (iteradorOperadores_local.hasNext()) {
        operador_local = (String)iteradorOperadores_local.next();
        if (mc.esContenidoPor(operador_local, pExpresion)) {
          operadorExpresion_local = operador_local;
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      operador_local = null;
      listaOperadores_local = null;
      iteradorOperadores_local = null;
    } 
    
    return operadorExpresion_local;
  }
  public boolean esVariableSistema(String pOperando) {
    boolean variableSistema_local = false;
    
    if (pOperando == ConstantesGeneral.VALOR_NULO) {
      return variableSistema_local;
    }
    
    try {
      variableSistema_local = (mc.comienzaCon(pOperando, String.valueOf('%')) && !mc.verificarExistenciaSubCadena(pOperando, String.valueOf(' ')));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return variableSistema_local;
  }
  public boolean esNumeroIgual(double pNumero, double pNumeroComparar) {
    boolean numeroIgual_local = false;
    
    try {
      numeroIgual_local = (pNumero == pNumeroComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroIgual_local;
  }
  public boolean esNumeroMenor(double pNumero, double pNumeroComparar) {
    boolean numeroMenor_local = false;
    
    try {
      numeroMenor_local = (pNumero < pNumeroComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroMenor_local;
  }
  public boolean esNumeroMayor(double pNumero, double pNumeroComparar) {
    boolean numeroMayor_local = false;
    
    try {
      numeroMayor_local = (pNumero > pNumeroComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroMayor_local;
  }
  public boolean esNumeroMenorIgual(double pNumero, double pNumeroComparar) {
    boolean numeroMenorIgual_local = false;
    
    try {
      numeroMenorIgual_local = (esNumeroMenor(pNumero, pNumeroComparar) || esNumeroIgual(pNumero, pNumeroComparar));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroMenorIgual_local;
  }
  public boolean esNumeroMayorIgual(double pNumero, double pNumeroComparar) {
    boolean numeroIgual_local = false;
    
    try {
      numeroIgual_local = (esNumeroMayor(pNumero, pNumeroComparar) || esNumeroIgual(pNumero, pNumeroComparar));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroIgual_local;
  }
  public String obtenerSeudonimoValido(String pSeudonimoValidar) {
    String seudonimoValido_local = "";
    
    if (pSeudonimoValidar == ConstantesGeneral.VALOR_NULO) {
      return seudonimoValido_local;
    }
    
    try {
      seudonimoValido_local = pSeudonimoValidar;
      if (mc.verificarExistenciaSubCadena(pSeudonimoValidar, String.valueOf('#'))) {
        seudonimoValido_local = mc.obtenerSubCadena(pSeudonimoValidar, 0, mc.obtenerPosicionSubCadena(pSeudonimoValidar, String.valueOf('#')));
      }
      
      if (mc.verificarExistenciaSubCadena(pSeudonimoValidar, String.valueOf('*'))) {
        seudonimoValido_local = mc.obtenerSubCadena(pSeudonimoValidar, 0, mc.obtenerPosicionSubCadena(pSeudonimoValidar, String.valueOf('*')));
      }
      
      if (mc.comienzaCon(pSeudonimoValidar, String.valueOf('%')) && mc.verificarExistenciaSubCadena(pSeudonimoValidar, String.valueOf('%')))
      {
        seudonimoValido_local = mc.obtenerSubCadena(pSeudonimoValidar, mc.obtenerPosicionSubCadena(pSeudonimoValidar, String.valueOf('%')) + 1, mc.obtenerLongitudCadena(pSeudonimoValidar));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return seudonimoValido_local;
  }
  private void ordenarArregloNumerosEnteros(int[] pArregloNumerico) {
    int posicionI_local = 0;
    int posicionJ_local = 0;
    int temporal_local = 0;
    
    if (pArregloNumerico == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      for (posicionI_local = pArregloNumerico.length - 1; posicionI_local > 0; 
        posicionI_local--) {
        for (posicionJ_local = 0; posicionJ_local < posicionI_local; posicionJ_local++) {
          if (pArregloNumerico[posicionJ_local] > pArregloNumerico[posicionJ_local + 1]) {
            temporal_local = pArregloNumerico[posicionJ_local];
            pArregloNumerico[posicionJ_local] = pArregloNumerico[posicionJ_local + 1];
            pArregloNumerico[posicionJ_local + 1] = temporal_local;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public int obtenerPrimerValorPositivoDeArreglo(int[] pArregloNumerico) {
    int primerValorPositivo_local = -1;
    int contador_local = 0;
    
    if (pArregloNumerico == ConstantesGeneral.VALOR_NULO) {
      return primerValorPositivo_local;
    }
    
    try {
      ordenarArregloNumerosEnteros(pArregloNumerico);
      for (contador_local = 0; contador_local < pArregloNumerico.length; contador_local++) {
        if (pArregloNumerico[contador_local] >= 0) {
          primerValorPositivo_local = pArregloNumerico[contador_local];
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return primerValorPositivo_local;
  }
  public String conformarCadenasFiltroPorPalabras(String pCadenaDescomponer, String pNombreCompletoCampo, String pOperador) {
    String cadenasFiltroPorPalabras_local = "";
    String[] palabras_local = null;
    int i_local = -1;
    
    if (pCadenaDescomponer == ConstantesGeneral.VALOR_NULO) {
      return cadenasFiltroPorPalabras_local;
    }
    
    if (pOperador == ConstantesGeneral.VALOR_NULO) {
      return cadenasFiltroPorPalabras_local;
    }
    
    try {
      palabras_local = mc.fraccionarCadena(pCadenaDescomponer, String.valueOf(' '));
      for (i_local = 0; i_local < palabras_local.length; i_local++) {
        if (!mc.esCadenaVacia(mc.borrarEspaciosLaterales(palabras_local[i_local]))) {
          cadenasFiltroPorPalabras_local = mc.concatenarCadena(cadenasFiltroPorPalabras_local, " upper(");
          
          cadenasFiltroPorPalabras_local = mc.concatenarCadena(cadenasFiltroPorPalabras_local, pNombreCompletoCampo);
          cadenasFiltroPorPalabras_local = mc.concatenarCadena(cadenasFiltroPorPalabras_local, String.valueOf(')'));
          
          cadenasFiltroPorPalabras_local = mc.concatenarCadena(cadenasFiltroPorPalabras_local, pOperador);
          cadenasFiltroPorPalabras_local = mc.concatenarCadena(cadenasFiltroPorPalabras_local, mc.colocarEntreComillas(String.valueOf('%') + mc.borrarEspaciosLaterales(palabras_local[i_local]) + String.valueOf('%')));
          
          if (i_local < palabras_local.length - 1) {
            cadenasFiltroPorPalabras_local = mc.concatenarCadena(cadenasFiltroPorPalabras_local, " and ");
          }
        } 
      } 
      
      if (!mc.esCadenaVacia(cadenasFiltroPorPalabras_local)) {
        cadenasFiltroPorPalabras_local = mc.colocarEntreParentesis(cadenasFiltroPorPalabras_local);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      palabras_local = null;
    } 
    
    return cadenasFiltroPorPalabras_local;
  }
  public String conformarNombreArchivoParaDescarga(int pNumeroArchivo, String pNombreArchivo) {
    String nombreArchivo_local = null;
    String prefijoNombreArchivo_local = null;
    
    try {
      prefijoNombreArchivo_local = String.valueOf(pNumeroArchivo);
      prefijoNombreArchivo_local = mc.completarCadena(prefijoNombreArchivo_local, false, '0', 10);
      
      prefijoNombreArchivo_local = mc.concatenarCadena(prefijoNombreArchivo_local, String.valueOf('-'));
      
      nombreArchivo_local = mc.concatenarCadena(prefijoNombreArchivo_local, pNombreArchivo);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      prefijoNombreArchivo_local = null;
    } 
    
    return nombreArchivo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */