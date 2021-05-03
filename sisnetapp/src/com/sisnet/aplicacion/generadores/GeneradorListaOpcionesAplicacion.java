package com.sisnet.aplicacion.generadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.listas.ListaOptionHTML;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.util.Iterator;
public class GeneradorListaOpcionesAplicacion
{
  private static final ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static final ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private MotorAplicacion aMotorAplicacion;
  public GeneradorListaOpcionesAplicacion() {
    setAdministradorBaseDatosSisnet(null);
    setAdministradorBaseDatosAplicacion(null);
    setMotorAplicacion(null);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  public ListaOptionHTML conformarListaOpcionesTipoIdentificacion() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar("C", "C\u00e9dula de Ciudadan\u00eda", false);
      
      listaOptionHTML_local.adicionar("N", "Nit", false);
      
      listaOptionHTML_local.adicionar("E", "C\u00e9dula de Extranjer\u00eda", false);
      
      listaOptionHTML_local.adicionar("P", "Pasaporte", false);
      
      listaOptionHTML_local.adicionar("S", "Sociedad Extranjera sin Nit", false);
      
      listaOptionHTML_local.adicionar("T", "Tarjeta Seguro Extranjera", false);
      
      listaOptionHTML_local.adicionar("I", "Sin n\u00famero", false);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoLicencia() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar("I", "Intranet", false);
      
      listaOptionHTML_local.adicionar("W", "Web", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesGenero() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar("F", "Femenino", false);
      
      listaOptionHTML_local.adicionar("M", "Masculino", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoDato() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar("T", "TEXTO", false);
      
      listaOptionHTML_local.adicionar("E", "N\u00daMERO ENTERO", false);
      
      listaOptionHTML_local.adicionar("R", "N\u00daMERO CON DECIMALES", false);
      
      listaOptionHTML_local.adicionar("F", "FECHA", false);
      
      listaOptionHTML_local.adicionar("H", "HORA", false);
      
      listaOptionHTML_local.adicionar("K", "CORREO ELECTR\u00D3NICO", false);
      
      listaOptionHTML_local.adicionar("DD", "DOCUMENTO", false);
      
      listaOptionHTML_local.adicionar("LL", "NUMERACI\u00D3N SEMIAUTOM\u00c1TICA NUMÃ‰RICA", false);
      
      listaOptionHTML_local.adicionar("MM", "NUMERACI\u00D3N SEMIAUTOM\u00c1TICA ALFANUMÃ‰RICA", false);
      
      listaOptionHTML_local.adicionar("NN", "P\u00c1RRAFO", false);
      
      listaOptionHTML_local.adicionar("J", "ARCHIVO", false);
      
      listaOptionHTML_local.adicionar("W", "CONSECUTIVO INTERNO", false);
      
      listaOptionHTML_local.adicionar("BB", "N\u00daMERO ENTERO S\u00D3LO POSITIVO", false);
      
      listaOptionHTML_local.adicionar("GG", "N\u00daMERO CON DECIMALES S\u00D3LO POSITIVO", false);
      
      listaOptionHTML_local.adicionar("XX", "N\u00daMERO ENTERO CON FORMATO", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesDependientesEnlazado() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(0), "NO DEPENDIENTE ", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(1), "DEPENDIENTE DE ENLAZADO RECALCULABLE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(2), "DEPENDIENTE DE ENLAZADO NO RECALCULABLE", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoHabilitacion() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(1), "HABILITADO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(2), "NO HABILITADO PERO VISIBLE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(3), "OCULTO", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoCampoCalculado() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(1), "NO CALCULADO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(2), "IGUAL A OTRO CAMPO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(3), "IGUAL A OTRO CAMPO PRIMER REGISTRO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(4), "IGUAL A OTRO CAMPO \u00daLTIMO REGISTRO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(5), "SUMA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(6), "RESTA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(7), "MULTIPLICACI\u00D3N", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(8), "DIVISI\u00D3N", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(9), "TOTAL COLUMNA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(10), "SALDO COLUMNA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(11), "CONCATENACI\u00D3N CON ESPACIOS", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(12), "CONCATENACI\u00D3N SIN ESPACIOS", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(13), "VALOR ABSOLUTO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(14), "SUMAR UNIDAD", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(15), "SI MENOR A CERO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(16), "SI MENOR O IGUAL A CERO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(17), "SI MAYOR QUE CERO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(18), "SI MAYOR O IGUAL A CERO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(19), "SI IGUAL A CERO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(20), "SI DIFERENTE DE CERO", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(21), "CONCATENACI\u00D3N CON GUION", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(27), "CONCATENACI\u00D3N CON CAMBIO DE RENGL\u00D3N", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(22), "CONCATENACI\u00D3N COLUMNA SIN ESPACIOS", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(23), "CONCATENACI\u00D3N COLUMNA CON ESPACIOS", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(24), "CONCATENACI\u00D3N COLUMNA CON GUION", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(28), "CONCATENACI\u00D3N COLUMNA CON CAMBIO DE RENGL\u00D3N", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(25), "IGUAL AL PRIMER CAMPO SI TIENE VALOR", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(26), "SUMA DESDE - HASTA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(29), "N\u00daMERO INTERNO DE LA HORA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(30), "N\u00daMERO INTERNO DE LA FECHA", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(31), "FECHA INICIO DE MES ACTUAL", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(32), "FECHA FIN DE MES ACTUAL", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(33), "FECHA INICIO DE MES ANTERIOR", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(34), "FECHA FIN DE MES ANTERIOR", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(35), "FECHA INICIO DE MES SIGUIENTE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(36), "FECHA FIN DE MES SIGUIENTE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(37), "FECHA INICIO DE A\u00d1O ACTUAL", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(38), "FECHA FIN DE A\u00d1O ACTUAL", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(39), "FECHA INICIO DE A\u00d1O ANTERIOR", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(40), "FECHA INICIO DE A\u00d1O ANTERIOR", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(41), "FECHA INICIO DE A\u00d1O SIGUIENTE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(42), "FECHA FIN DE A\u00d1O SIGUIENTE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(43), "ELEVADO A LA", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoBloqueo() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(1), "Habilitado", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(2), "Bloqueado Manualmente", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(3), "Bloqueo por contrase\u00f1a fallida", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(4), "Bloqueo por vencimiento de contrase\u00f1a", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesFiltradoRegistrosCampoEnlazado() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(0), "Escoja un valor", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(1), "TODOS LOS REGISTROS", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(2), "FILTRADO POR IGUAL A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(3), "FILTRADO POR NO IGUAL A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(4), "FILTRADO POR MENOR QUE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(5), "FILTRADO POR MAYOR QUE", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(6), "FILTRADO POR MENOR O IGUAL A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(7), "FILTRADO POR MAYOR O IGUAL A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(8), "FILTRADO POR COMIENZA CON", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(9), "FILTRADO POR COMIENZA CON PERO NO IGUAL A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(10), "FILTRADO POR NO COMIENZA CON", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(11), "FILTRADO POR CONTIENE A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(12), "FILTRADO POR CONTIENE A PERO NO IGUAL A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(13), "FILTRADO POR NO CONTIENE A", false);
      
      listaOptionHTML_local.adicionar(String.valueOf(14), "FILTRADO POR CONTIENE PALABRAS", false);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  private ListaOptionHTML convertirListaGeneralAListaOptionHtml(ListaGeneral pListaGeneral) {
    ListaOptionHTML listaOptionHTML_local = null;
    Iterator iterador_local = null;
    ItemLista itemLista_local = null;
    
    if (pListaGeneral == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      iterador_local = pListaGeneral.iterator();
      while (iterador_local.hasNext()) {
        itemLista_local = (ItemLista)iterador_local.next();
        listaOptionHTML_local.adicionar(itemLista_local.getValorItem(), itemLista_local.getNombreItem(), false);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      itemLista_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  private ListaOptionHTML convertirListaAplicacionAListaOptionHtml(ListaAplicacion pListaAplicacion) {
    ListaOptionHTML listaOptionHTML_local = null;
    Iterator iterador_local = null;
    Aplicacion aplicacion_local = null;
    
    if (pListaAplicacion == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      iterador_local = pListaAplicacion.iterator();
      while (iterador_local.hasNext()) {
        aplicacion_local = (Aplicacion)iterador_local.next();
        listaOptionHTML_local.adicionar(String.valueOf(aplicacion_local.getIdAplicacion()), aplicacion_local.getTituloAplicacion(), false);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      aplicacion_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoUsuario() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaGeneral listaTipoUsuario_local = null;
    
    try {
      listaTipoUsuario_local = getAdministradorBaseDatosSisnet().obtenerListaTiposUsuario();
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(listaTipoUsuario_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaTipoUsuario_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesTipoDatoConTablas() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaGeneral listaTablasAplicacion_local = null;
    
    try {
      listaTablasAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaTablasAplicacion();
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(listaTablasAplicacion_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaTablasAplicacion_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesAplicacion() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaAplicacion listaAplicacion_local = null;
    
    try {
      listaAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(3, -1, false);
      
      listaOptionHTML_local = convertirListaAplicacionAListaOptionHtml(listaAplicacion_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaAplicacion_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesAplicacionTabla() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaAplicacion listaAplicacionesTabla_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaAplicacionesTabla_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(2, -1, false);
      
      listaOptionHTML_local.adicionar(String.valueOf(0), "Escoja un valor", false);
      
      listaOptionHTML_local.concatenarCon(convertirListaAplicacionAListaOptionHtml(listaAplicacionesTabla_local));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaAplicacionesTabla_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesHabilitadoPor() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaGeneral listaCamposTipoTabla_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(0), "NORMAL", false);
      
      listaCamposTipoTabla_local = getAdministradorBaseDatosSisnet().obtenerListaCamposTipoTablaAplicacion(-1, false);
      
      listaOptionHTML_local.concatenarCon(convertirListaGeneralAListaOptionHtml(listaCamposTipoTabla_local));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCamposTipoTabla_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesListaDependiente() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaGeneral listaCamposTipoTabla_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(0), "NO DEPENDIENTE ", false);
      
      listaCamposTipoTabla_local = getAdministradorBaseDatosSisnet().obtenerListaCamposTipoTablaAplicacion(-1, false);
      
      listaOptionHTML_local.concatenarCon(convertirListaGeneralAListaOptionHtml(listaCamposTipoTabla_local));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCamposTipoTabla_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesEnlazado() {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaAplicacion listaAplicacion_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(0), "NO ENLAZADO", false);
      
      listaAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(0, -1, false);
      
      listaOptionHTML_local.concatenarCon(convertirListaAplicacionAListaOptionHtml(listaAplicacion_local));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaAplicacion_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesAplicacionPlantilla() {
    ListaOptionHTML listaOptionHTML_local = null;
    
    try {
      listaOptionHTML_local = conformarListaOpcionesAplicacionTabla();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoEnlaceDepende(Campo pCampo) {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaGeneral listaGeneral_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      listaGeneral_local = getAdministradorBaseDatosSisnet().obtenerListaCamposEnlazadosGrupoInformacion(pCampo.getGrupoInformacion(), pCampo.getIdCampo());
      
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(listaGeneral_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaGeneral_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoOrigenEnlace(int pIdCampo) {
    ListaOptionHTML listaOptionHTML_local = null;
    int aplicacionEnlace_local = -1;
    int campoEnlaceDepende_local = -1;
    int idGrupoInformacionEnlace_local = -1;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campoEnlace_local = null;
    Campo campoEnlazado_local = null;
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      aplicacionEnlace_local = -1;
      if (pIdCampo != -1) {
        campoEnlace_local = getMotorAplicacion().obtenerCampoPorId(pIdCampo);
        if (campoEnlace_local != ConstantesGeneral.VALOR_NULO && campoEnlace_local.getEnlaceCampo().getCampoEnlaceDepende() != ConstantesGeneral.VALOR_NULO) {
          
          campoEnlaceDepende_local = campoEnlace_local.getEnlaceCampo().getCampoEnlaceDepende().getIdCampo();
          campoEnlazado_local = getMotorAplicacion().obtenerCampoPorId(campoEnlaceDepende_local);
          if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO) {
            aplicacionEnlace_local = campoEnlazado_local.getEnlaceCampo().getEnlazado().getIdAplicacion();
            campoEnlazado_local = null;
          } 
        } 
      } 
      idGrupoInformacionEnlace_local = getAdministradorBaseDatosSisnet().obtenerIdPrimerGrupoInformacionAplicacion(aplicacionEnlace_local);
      
      listaCampo_local = getMotorAplicacion().obtenerListaCamposGrupoInformacion(idGrupoInformacionEnlace_local);
      
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext()) {
        campoEnlazado_local = (Campo)iterador_local.next();
        listaOptionHTML_local.adicionar(String.valueOf(campoEnlazado_local.getIdCampo()), campoEnlazado_local.getEtiquetaCampo(), false);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterador_local = null;
      campoEnlace_local = null;
      campoEnlazado_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoValor(ListaCampo pListaCampo) {
    ListaOptionHTML listaOptionHTML_local = null;
    int tipoCampoCalculado_local = 1;
    int habilitadoPor_local = 0;
    int idGrupoInformacion_local = -1;
    String campoCalculado_local = null;
    String campoHabilitadoPor_local = null;
    ListaGeneral listaGeneralCampos_local = null;
    GrupoInformacion grupoInformacionHabilita_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      campoCalculado_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
      campoHabilitadoPor_local = pListaCampo.obtenerValorCampo("fldhabilitadopor");
      idGrupoInformacion_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion"));
      if (mc.esCadenaNumerica(campoCalculado_local, true)) {
        tipoCampoCalculado_local = Integer.parseInt(campoCalculado_local);
      } else {
        tipoCampoCalculado_local = 1;
      } 
      if (mc.esCadenaNumerica(campoHabilitadoPor_local, true)) {
        habilitadoPor_local = Integer.parseInt(campoHabilitadoPor_local);
      } else {
        habilitadoPor_local = 0;
      } 
      if (mc.esCadenaNumerica(String.valueOf(idGrupoInformacion_local), true)) {
        grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);
      }
      
      switch (tipoCampoCalculado_local) {
        case 2:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, false);
          
          if (habilitadoPor_local != 0) {
            grupoInformacionHabilita_local = getMotorAplicacion().obtenerCampoPorId(habilitadoPor_local).getGrupoInformacion();
            
            if (grupoInformacionHabilita_local.esGrupoInformacionMultiple()) {
              listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacionHabilita_local, false, false));
              
              break;
            } 
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
            }
            
            break;
          } 
          
          if (grupoInformacion_local.esGrupoInformacionMultiple()) {
            listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
          }
          break;
        
        case 3:
        case 4:
        case 22:
        case 23:
        case 24:
        case 28:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(grupoInformacion_local, false, false);
          break;
        
        case 9:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(grupoInformacion_local, true, false);
          break;
        
        case 10:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false);
          break;
        
        case 13:
        case 14:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        case 20:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), true, false, false);
          break;
        
        case 29:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), false, false, true);
          break;
        
        case 30:
        case 31:
        case 32:
        case 33:
        case 34:
        case 35:
        case 36:
        case 37:
        case 38:
        case 39:
        case 40:
        case 41:
        case 42:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), false, true, false);
          break;
      } 
      
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(listaGeneralCampos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoCalculado_local = null;
      grupoInformacion_local = null;
      campoHabilitadoPor_local = null;
      listaGeneralCampos_local = null;
      grupoInformacionHabilita_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoOrigenUno(ListaCampo pListaCampo) {
    ListaOptionHTML listaOptionHTML_local = null;
    int idAplicacion_local = -1;
    int idGrupoInformacion_local = -1;
    int tipoCampoCalculado_local = 0;
    String campoCalculado_local = null;
    GrupoInformacion grupoInformacion_local = null;
    ListaGeneral listaGeneralCampos_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      campoCalculado_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
      idGrupoInformacion_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion"));
      if (mc.esCadenaNumerica(campoCalculado_local, true)) {
        tipoCampoCalculado_local = Integer.parseInt(campoCalculado_local);
      } else {
        tipoCampoCalculado_local = 1;
      } 
      if (mc.esCadenaNumerica(String.valueOf(idGrupoInformacion_local), true)) {
        grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);
        
        idAplicacion_local = grupoInformacion_local.getAplicacion().getIdAplicacion();
      } 
      
      switch (tipoCampoCalculado_local) {
        case 5:
        case 6:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, true, false, false);
          
          listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, false, true, false));
          break;
        
        case 7:
        case 8:
        case 43:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, true, false, false);
          break;
        
        case 11:
        case 12:
        case 21:
        case 27:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, false, false, false);
          break;
        
        case 25:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, false);
          
          if (grupoInformacion_local.esGrupoInformacionMultiple()) {
            listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
          }
          break;
        
        case 26:
          listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false);
          break;
      } 
      
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(listaGeneralCampos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoCalculado_local = null;
      grupoInformacion_local = null;
      listaGeneralCampos_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoOrigenDos(ListaCampo pListaCampo) {
    ListaOptionHTML listaOptionHTML_local = null;
    int idAplicacion_local = -1;
    int idGrupoInformacion_local = -1;
    int tipoCampoCalculado_local = 0;
    int idCampoOrigenUno_local = -1;
    boolean esGrupoMultiple_local = false;
    String campoCalculado_local = null;
    ListaGeneral listaGeneralCampos_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Campo campoOrigenUno_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      idGrupoInformacion_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion"));
      campoCalculado_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
      idCampoOrigenUno_local = -1;
      
      if (!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldidcampoorigenuno"))) {
        idCampoOrigenUno_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidcampoorigenuno"));
        
        if (idCampoOrigenUno_local != -1) {
          if (mc.esCadenaNumerica(campoCalculado_local, true)) {
            tipoCampoCalculado_local = Integer.parseInt(campoCalculado_local);
          } else {
            tipoCampoCalculado_local = 1;
          } 
          if (mc.esCadenaNumerica(String.valueOf(idCampoOrigenUno_local), true)) {
            campoOrigenUno_local = getMotorAplicacion().obtenerCampoPorId(idCampoOrigenUno_local);
            grupoInformacion_local = campoOrigenUno_local.getGrupoInformacion();
          } else {
            if (mc.esCadenaNumerica(String.valueOf(idGrupoInformacion_local), true)) {
              grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);
            }
            
            idCampoOrigenUno_local = -1;
          } 
          esGrupoMultiple_local = grupoInformacion_local.esGrupoInformacionMultiple();
          idAplicacion_local = grupoInformacion_local.getAplicacion().getIdAplicacion();
          
          switch (tipoCampoCalculado_local) {
            case 5:
            case 6:
              listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, true, false);
              
              listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, true));
              
              if (esGrupoMultiple_local) {
                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false));
                
                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, true));
              } 
              break;
            
            case 7:
            case 8:
            case 43:
              listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, true, false);
              
              if (esGrupoMultiple_local) {
                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false));
              }
              break;
            
            case 11:
            case 12:
            case 21:
            case 27:
              listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, false, false, false);
              break;
            
            case 25:
              listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, false);
              
              if (grupoInformacion_local.esGrupoInformacionMultiple()) {
                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
              }
              break;
            
            case 26:
              listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false);
              break;
          } 
        
        } 
      } 
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(listaGeneralCampos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoOrigenUno_local = null;
      campoCalculado_local = null;
      grupoInformacion_local = null;
      listaGeneralCampos_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoDestinoFiltrado(ListaCampo pListaCampo) {
    ListaOptionHTML listaOptionHTML_local = null;
    int idAplicacionEnlazada_local = -1;
    String aplicacionEnlazada_local = null;
    ListaCampo listaCampo_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      aplicacionEnlazada_local = pListaCampo.obtenerValorCampo("fldenlazado");
      if (mc.esCadenaNumerica(aplicacionEnlazada_local, true)) {
        idAplicacionEnlazada_local = Integer.parseInt(aplicacionEnlazada_local);
      }
      listaCampo_local = getMotorAplicacion().obtenerListaCamposAplicacion(idAplicacionEnlazada_local);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        listaOptionHTML_local = new ListaOptionHTML();
        listaOptionHTML_local.adicionar(String.valueOf(0), "Escoja un valor", false);
        
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          listaOptionHTML_local.adicionar(String.valueOf(campo_local.getIdCampo()), mc.concatenarCadena(campo_local.getEtiquetaCampo(), ' ' + mc.colocarEntreParentesis(campo_local.getGrupoInformacion().getDescripcionGrupoInformacion())), false);
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
      aplicacionEnlazada_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoOrigenFiltrado(int pIdAplicacionActual) {
    ListaOptionHTML listaOptionHTML_local = null;
    ListaCampo listaCampo_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposAplicacion(pIdAplicacionActual);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        listaOptionHTML_local = new ListaOptionHTML();
        listaOptionHTML_local.adicionar(String.valueOf(0), "Escoja un valor", false);
        
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          listaOptionHTML_local.adicionar(String.valueOf(campo_local.getIdCampo()), mc.concatenarCadena(campo_local.getEtiquetaCampo(), ' ' + mc.colocarEntreParentesis(campo_local.getGrupoInformacion().getDescripcionGrupoInformacion())), false);
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesValorDependiente(int pIdTabla, ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones) {
    ListaOptionHTML listaOptionHTML_local = null;
    Tabla tabla_local = null;
    
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla);
      listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(getAdministradorBaseDatosAplicacion().obtenerListaValoresTabla(tabla_local.getNombreTabla(), -1, pListaCadenas, pExcluirValores, pExistenRestricciones, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tabla_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesPlantillaDocumento(Aplicacion pAplicacionPlantilla) {
    ListaOptionHTML listaOptionHTML_local = null;
    int idPrimerGrupo_local = -1;
    Campo campo_local = null;
    
    if (pAplicacionPlantilla == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      if (pAplicacionPlantilla.getIdAplicacion() != 0) {
        idPrimerGrupo_local = getMotorAplicacion().obtenerIdPrimerGrupoInformacionNoMultipleAplicacion(pAplicacionPlantilla.getIdAplicacion());
        
        campo_local = getMotorAplicacion().obtenerCampoPorPosicion(1, idPrimerGrupo_local);
        if (campo_local != ConstantesGeneral.VALOR_NULO && 
          getAdministradorBaseDatosAplicacion().verificarExistenciaTabla(pAplicacionPlantilla.getNombreAplicacion())) {
          listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(getAdministradorBaseDatosAplicacion().obtenerListaValoresPlantillasOReportes(pAplicacionPlantilla.getNombreAplicacion(), campo_local.getNombreCampo(), ap.conformarNombreCampoLlavePrimaria(pAplicacionPlantilla.getNombreAplicacion()), -1, true));
        
        }
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoTipoTabla(int pIdTabla, ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones) {
    ListaOptionHTML listaOptionHTML_local = null;
    Tabla tabla_local = null;
    
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla);
      if (tabla_local != ConstantesGeneral.VALOR_NULO) {
        listaOptionHTML_local = convertirListaGeneralAListaOptionHtml(getAdministradorBaseDatosAplicacion().obtenerListaValoresTabla(tabla_local.getNombreTabla(), -1, pListaCadenas, pExcluirValores, pExistenRestricciones, false));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tabla_local = null;
    } 
    
    return listaOptionHTML_local;
  }
  public ListaOptionHTML conformarListaOpcionesCampoListaDependiente(int pIdTabla, int pIdCampo, int pIdValorMaestro, ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones) {
    ListaOptionHTML listaOptionHTML_local = null;
    int valorItem_local = -1;
    String valorTabla_local = null;
    ListaGeneral listaIdValoresDependientes_local = null;
    Iterator iterador_local = null;
    ItemLista itemLista_local = null;
    Tabla tabla_local = null;
    
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return listaOptionHTML_local;
    }
    
    try {
      listaOptionHTML_local = new ListaOptionHTML();
      listaOptionHTML_local.adicionar(String.valueOf(0), "Escoja un valor", false);
      
      listaIdValoresDependientes_local = getAdministradorBaseDatosSisnet().obtenerListaIdValoresDetalleDeMaestro(pIdCampo, pIdValorMaestro, -1);
      
      if (listaIdValoresDependientes_local != ConstantesGeneral.VALOR_NULO) {
        tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla);
        iterador_local = listaIdValoresDependientes_local.iterator();
        while (iterador_local.hasNext()) {
          itemLista_local = (ItemLista)iterador_local.next();
          valorItem_local = Integer.parseInt(itemLista_local.getValorItem());
          if (valorItem_local != 0 && tabla_local != ConstantesGeneral.VALOR_NULO) {
            valorTabla_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(tabla_local.getNombreTabla(), valorItem_local);
            
            itemLista_local.setNombreItem(valorTabla_local);
            if (pExistenRestricciones) {
              if (pExcluirValores) {
                if (!pListaCadenas.verificarExistenciaCadena(valorTabla_local)) {
                  listaOptionHTML_local.adicionar(itemLista_local.getValorItem(), itemLista_local.getNombreItem(), false);
                }
                continue;
              } 
              if (pListaCadenas.verificarExistenciaCadena(valorTabla_local)) {
                listaOptionHTML_local.adicionar(itemLista_local.getValorItem(), itemLista_local.getNombreItem(), false);
              }
              
              continue;
            } 
            listaOptionHTML_local.adicionar(itemLista_local.getValorItem(), itemLista_local.getNombreItem(), false);
          }
        
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      tabla_local = null;
      iterador_local = null;
      itemLista_local = null;
      valorTabla_local = null;
      listaIdValoresDependientes_local = null;
    } 
    
    return listaOptionHTML_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\generadores\GeneradorListaOpcionesAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */