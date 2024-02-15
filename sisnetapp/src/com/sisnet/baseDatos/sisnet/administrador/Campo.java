package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.CalculoCampo;
import com.sisnet.baseDatos.sisnet.administrador.EnlaceCampo;
import com.sisnet.baseDatos.sisnet.administrador.EstiloCampo;
import com.sisnet.baseDatos.sisnet.administrador.FormatoCampo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.PlantillaCampo;
import com.sisnet.baseDatos.sisnet.administrador.RestriccionCampo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.AreaTextoHTML;
import com.sisnet.controlesHTML.BotonHTML;
import com.sisnet.controlesHTML.CajaArchivoHTML;
import com.sisnet.controlesHTML.CajaChequeoHTML;
import com.sisnet.controlesHTML.CajaFechaHTML;
import com.sisnet.controlesHTML.CajaSeleccionHTML;
import com.sisnet.controlesHTML.CajaTextoHTML;
import com.sisnet.controlesHTML.CeldaHTML;
import com.sisnet.controlesHTML.listas.ListaOptionHTML;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.io.Serializable;
import java.util.Iterator;
public class Campo
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private int aIdCampo;
  private int aIdGrupoInformacion;
  private GrupoInformacion aGrupoInformacion;
  private String aNombreCampo;
  private String aEtiquetaCampo;
  private String aSeudonimo;
  private int aPosicion;
  private boolean aObligatorio;
  private boolean aVisibleUsuarioPrincipal;
  private boolean aVisibleUsuarioSecundario;
  private boolean aModificable;
  private int aAnchoColumna;
  private int aTipoHabilitacion;
  private int aIdHabilitadoPor;
  private com.sisnet.baseDatos.sisnet.administrador.Campo aHabilitadoPor;
  private boolean aBorrarValorNoHabilitado;
  private int aIdListaDependiente;
  private com.sisnet.baseDatos.sisnet.administrador.Campo aListaDependiente;
  private boolean aIncluirOpcionConsulta;
  private boolean aRecargarPantalla;
  private FormatoCampo aFormatoCampo;
  private RestriccionCampo aRestriccionCampo;
  private PlantillaCampo aPlantillaCampo;
  private EnlaceCampo aEnlaceCampo;
  private CalculoCampo aCalculoCampo;
  private Object aValorCampo;
  private ListaOptionHTML aListaOptionHTML;
  private EstiloCampo aEstiloCampo;
  private boolean aEsImagen;
  private int aAltoImagenPantallaPresentacion;
  private int aAltoImagenPantallaEdicion;
  private boolean aOcultarEtiquetaControlExaminar;
  private boolean aOcultarEtiquetaControlVer;
  private boolean aExcluirValidacionContrasena;
  
  private String aPlaceHolder;
  
  public Campo() {
    setIdCampo(-1);
    setIdGrupoInformacion(-1);
    setGrupoInformacion(null);
    setNombreCampo("");
    setEtiquetaCampo("");
    setSeudonimo("");
    setPosicion(-1);
    setObligatorio(false);
    setVisibleUsuarioPrincipal(false);
    setVisibleUsuarioSecundario(false);
    setModificable(false);
    setAnchoColumna(0);
    setTipoHabilitacion(1);
    setIdHabilitadoPor(0);
    setHabilitadoPor(null);
    setBorrarValorNoHabilitado(false);
    setIdListaDependiente(0);
    setListaDependiente(null);
    setIncluirOpcionConsulta(false);
    setFormatoCampo(new FormatoCampo());
    setRestriccionCampo(new RestriccionCampo());
    setPlantillaCampo(new PlantillaCampo());
    setEnlaceCampo(new EnlaceCampo());
    setCalculoCampo(new CalculoCampo());
    setValorCampo(null);
    setRecargarPantalla(false);
    setEsImagen(false);
    setEstiloCampo(new EstiloCampo());
    setExcluirValidacionContrasena(true);
  }
  public int getIdCampo() {
    return this.aIdCampo;
  }
  public void setIdCampo(int pIdCampo) {
    this.aIdCampo = pIdCampo;
  }
  public int getIdGrupoInformacion() {
    return this.aIdGrupoInformacion;
  }
  public void setIdGrupoInformacion(int pIdGrupoInformacion) {
    this.aIdGrupoInformacion = pIdGrupoInformacion;
  }
  public GrupoInformacion getGrupoInformacion() {
    return this.aGrupoInformacion;
  }
  public void setGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    this.aGrupoInformacion = pGrupoInformacion;
  }
  public String getNombreCampo() {
    return this.aNombreCampo;
  }
  public void setNombreCampo(String pNombreCampo) {
    this.aNombreCampo = mc.convertirAMayusculas(pNombreCampo);
  }
  public String getEtiquetaCampo() {
    return this.aEtiquetaCampo;
  }
  public void setEtiquetaCampo(String pEtiquetaCampo) {
    this.aEtiquetaCampo = mc.convertirAMayusculas(pEtiquetaCampo);
  }
  public String getSeudonimo() {
    return this.aSeudonimo;
  }
  public void setSeudonimo(String pSeudonimo) {
    this.aSeudonimo = mc.convertirAMayusculas(pSeudonimo);
  }
  public int getPosicion() {
    return this.aPosicion;
  }
  public void setPosicion(int pPosicion) {
    this.aPosicion = pPosicion;
  }
  public boolean esObligatorio() {
    return this.aObligatorio;
  }
  public void setObligatorio(boolean pObligatorio) {
    this.aObligatorio = pObligatorio;
  }
  public boolean esVisibleUsuarioPrincipal() {
    return this.aVisibleUsuarioPrincipal;
  }
  public void setVisibleUsuarioPrincipal(boolean pVisibleUsuarioPrincipal) {
    this.aVisibleUsuarioPrincipal = pVisibleUsuarioPrincipal;
  }
  public boolean esVisibleUsuarioSecundario() {
    return this.aVisibleUsuarioSecundario;
  }
  public void setVisibleUsuarioSecundario(boolean pVisibleUsuarioSecundario) {
    this.aVisibleUsuarioSecundario = pVisibleUsuarioSecundario;
  }
  public boolean esModificable() {
    return this.aModificable;
  }
  public void setModificable(boolean pModificable) {
    this.aModificable = pModificable;
  }
  public int getAnchoColumna() {
    return this.aAnchoColumna;
  }
  public void setAnchoColumna(int pAnchoColumna) {
    this.aAnchoColumna = pAnchoColumna;
  }
  public int getTipoHabilitacion() {
    return this.aTipoHabilitacion;
  }
  public void setTipoHabilitacion(int pTipoHabilitacion) {
    this.aTipoHabilitacion = pTipoHabilitacion;
  }
  public int getIdHabilitadoPor() {
    return this.aIdHabilitadoPor;
  }
  public void setIdHabilitadoPor(int pIdHabilitadoPor) {
    this.aIdHabilitadoPor = pIdHabilitadoPor;
  }
  public com.sisnet.baseDatos.sisnet.administrador.Campo getHabilitadoPor() {
    return this.aHabilitadoPor;
  }
  public void setHabilitadoPor(com.sisnet.baseDatos.sisnet.administrador.Campo pHabilitadoPor) {
    this.aHabilitadoPor = pHabilitadoPor;
  }
  public boolean esBorrarValorNoHabilitado() {
    return this.aBorrarValorNoHabilitado;
  }
  public void setBorrarValorNoHabilitado(boolean pBorrarValorNoHabilitado) {
    this.aBorrarValorNoHabilitado = pBorrarValorNoHabilitado;
  }
  public int getIdListaDependiente() {
    return this.aIdListaDependiente;
  }
  public void setIdListaDependiente(int pIdListaDependiente) {
    this.aIdListaDependiente = pIdListaDependiente;
  }
  public com.sisnet.baseDatos.sisnet.administrador.Campo getListaDependiente() {
    return this.aListaDependiente;
  }
  public void setListaDependiente(com.sisnet.baseDatos.sisnet.administrador.Campo pListaDependiente) {
    this.aListaDependiente = pListaDependiente;
  }
  public boolean esIncluirOpcionConsulta() {
    return this.aIncluirOpcionConsulta;
  }
  public void setIncluirOpcionConsulta(boolean pIncluirOpcionConsulta) {
    this.aIncluirOpcionConsulta = pIncluirOpcionConsulta;
  }
  public boolean esRecargarPantalla() {
    return this.aRecargarPantalla;
  }
  public void setRecargarPantalla(boolean pRecargarPantalla) {
    this.aRecargarPantalla = pRecargarPantalla;
  }
  public FormatoCampo getFormatoCampo() {
    return this.aFormatoCampo;
  }
  public void setFormatoCampo(FormatoCampo pFormatoCampo) {
    this.aFormatoCampo = pFormatoCampo;
  }
  public RestriccionCampo getRestriccionCampo() {
    return this.aRestriccionCampo;
  }
  public void setRestriccionCampo(RestriccionCampo pRestriccionCampo) {
    this.aRestriccionCampo = pRestriccionCampo;
  }
  public PlantillaCampo getPlantillaCampo() {
    return this.aPlantillaCampo;
  }
  public void setPlantillaCampo(PlantillaCampo pPlantillaCampo) {
    this.aPlantillaCampo = pPlantillaCampo;
  }
  public EnlaceCampo getEnlaceCampo() {
    return this.aEnlaceCampo;
  }
  public void setEnlaceCampo(EnlaceCampo pEnlaceCampo) {
    this.aEnlaceCampo = pEnlaceCampo;
  }
  public CalculoCampo getCalculoCampo() {
    return this.aCalculoCampo;
  }
  public void setCalculoCampo(CalculoCampo pCalculoCampo) {
    this.aCalculoCampo = pCalculoCampo;
  }
  public Object getValorCampo() {
    return this.aValorCampo;
  }
  public void setValorCampo(Object pValorCampo) {
    this.aValorCampo = pValorCampo;
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      if (esTipoDatoTexto()) {
        this.aValorCampo = "";
      }
      if (esTipoDatoNumerico() || esTipoDatoTabla()) {
        this.aValorCampo = Integer.valueOf(0);
      }
    } else {
      if (esTipoDatoTexto()) {
        pValorCampo = mc.borrarEspaciosLaterales(pValorCampo.toString());
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "T")) {
        this.aValorCampo = mc.borrarEspaciosLaterales(mc.convertirAMayusculas(pValorCampo.toString()));
        return;
      } 
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "MM")) {
        this.aValorCampo = mc.borrarEspaciosLaterales(mc.convertirAMayusculas(pValorCampo.toString()));
        return;
      } 
      if (esTipoDatoNumeroEntero() && !esCampoEnlazado() && !mc.esCadenaNumerica(pValorCampo.toString(), true) && !mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo.toString(), "null")) {
        
        this.aValorCampo = Integer.valueOf(0);
        return;
      } 
      if (esTipoDatoNumeroReal() && !mc.esCadenaNumerica(pValorCampo.toString(), false) && !mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo.toString(), "null")) {
        
        this.aValorCampo = Integer.valueOf(0);
        return;
      } 
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "F") && !mc.verificarFormatoFecha(pValorCampo.toString())) {
        
        this.aValorCampo = null;
        return;
      } 
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "H") && !mc.verificarFormatoHora(pValorCampo.toString())) {
        
        this.aValorCampo = null;
        return;
      } 
    } 
  }
  public ListaOptionHTML getListaOptionHTML() {
    return this.aListaOptionHTML;
  }
  public void setListaOptionHTML(ListaOptionHTML pListaOptionHTML) {
    this.aListaOptionHTML = pListaOptionHTML;
  }
  public EstiloCampo getEstiloCampo() {
    return this.aEstiloCampo;
  }
  public void setEstiloCampo(EstiloCampo pEstiloCampo) {
    this.aEstiloCampo = pEstiloCampo;
  }
  public boolean esImagen() {
    return this.aEsImagen;
  }
  public void setEsImagen(boolean pEsImagen) {
    this.aEsImagen = pEsImagen;
  }
  public int getAltoImagenPantallaPresentacion() {
    return this.aAltoImagenPantallaPresentacion;
  }
  public void setAltoImagenPantallaPresentacion(int pAltoImagenPantallaPresentacion) {
    this.aAltoImagenPantallaPresentacion = pAltoImagenPantallaPresentacion;
  }
  public int getAltoImagenPantallaEdicion() {
    return this.aAltoImagenPantallaEdicion;
  }
  public void setAltoImagenPantallaEdicion(int pAltoImagenPantallaEdicion) {
    this.aAltoImagenPantallaEdicion = pAltoImagenPantallaEdicion;
  }
  public boolean ocultarEtiquetaControlExaminar() {
    return this.aOcultarEtiquetaControlExaminar;
  }
  public void setOcultarEtiquetaControlExaminar(boolean pOcultarEtiquetaControlExaminar) {
    this.aOcultarEtiquetaControlExaminar = pOcultarEtiquetaControlExaminar;
  }
  public boolean ocultarEtiquetaControlVer() {
    return this.aOcultarEtiquetaControlVer;
  }
  public void setOcultarEtiquetaControlVer(boolean pOcultarEtiquetaControlVer) {
    this.aOcultarEtiquetaControlVer = pOcultarEtiquetaControlVer;
  }
  public boolean excluirValidacionContrasena() {
    return this.aExcluirValidacionContrasena;
  }
  public void setExcluirValidacionContrasena(boolean pExcluirValidacionContrasena) {
    this.aExcluirValidacionContrasena = pExcluirValidacionContrasena;
  }
  public void validarCampoSegunTipoDato() {
    int longitud_local = -1;
    int numeroDecimales_local = -1;
    
    if (this == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      longitud_local = getFormatoCampo().getLongitudCampo();
      if ((esTipoDatoNumerico() && !mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "B")) || esTipoDatoTabla()) {
        
        if (esTipoDatoNumeroReal()) {
          numeroDecimales_local = getFormatoCampo().getNumeroDecimales();
          if (longitud_local > 1000) {
            longitud_local = 1000;
          }
          if (longitud_local < 1) {
            longitud_local = 10;
          }
          if (numeroDecimales_local > 1000) {
            numeroDecimales_local = 1000;
          }
          if (numeroDecimales_local < 2) {
            numeroDecimales_local = 2;
          }
        } else {
          if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "XX")) {
            if (longitud_local < 1 || longitud_local > 10) {
              longitud_local = 10;
            }
          } else {
            longitud_local = 10;
          } 
          numeroDecimales_local = 0;
        } 
        getFormatoCampo().setNumeroDecimales(numeroDecimales_local);
      } 
      
      if (esTipoDatoTexto() && (longitud_local < 1 || longitud_local > 10485759))
      {
        
        longitud_local = 50;
      }
      if (esTipoDatoDocumento()) {
        longitud_local = 10485760;
      }
      if (esTipoDatoParrafo() && (
        longitud_local < 255 || longitud_local > 10485760))
      {
        longitud_local = 255;
      }
      
      if (esCampoEnlazado()) {
        longitud_local = 10;
      }
      setSeudonimo(mc.convertirTildeANoTilde(getSeudonimo()));
      getFormatoCampo().setLongitudCampo(longitud_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void validarCampoSegunSelecciones() {
    if (this == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (!getPlantillaCampo().esTienePlantilla()) {
        getPlantillaCampo().setAplicacionPlantilla(null);
      }
      if (getEnlaceCampo().getDependienteEnlazado() == 0) {
        getEnlaceCampo().setCampoEnlaceDepende(null);
        getEnlaceCampo().setCampoOrigenEnlace(null);
      } 
      if (esCampoEnlazado() || getCalculoCampo().getCampoCalculado() == 30 || getCalculoCampo().getCampoCalculado() == 29)
      {
        
        getFormatoCampo().setTipoDato("E");
      }
      if (!esCampoEnlazado() || getEnlaceCampo().getFiltradoRegistrosEnlazados() == 0 || getEnlaceCampo().getFiltradoRegistrosEnlazados() == 1)
      {
        getEnlaceCampo().setCampoOrigenFiltrado(null);
        getEnlaceCampo().setCampoDestinoFiltrado(null);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public String aplicarFormatoNumeros() {
    String formatoNumeros_local = null;
    String valorCampo_local = null;
    String tipoDato_local = null;
    
    try {
      tipoDato_local = getFormatoCampo().getTipoDato();
      valorCampo_local = "";
      if (getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        valorCampo_local = getValorCampo().toString();
      }
      formatoNumeros_local = valorCampo_local;
      if (mc.sonCadenasIguales(valorCampo_local, "DESCONOCIDO")) {
        return formatoNumeros_local;
      }
      if (mc.sonCadenasIguales(tipoDato_local, "E") || mc.sonCadenasIguales(tipoDato_local, "LL") || mc.sonCadenasIguales(tipoDato_local, "BB") || mc.sonCadenasIguales(tipoDato_local, "W"))
      {
        
        formatoNumeros_local = mc.formatearNumeroEntero(Long.parseLong(valorCampo_local));
      }
      if (mc.sonCadenasIguales(tipoDato_local, "R") || mc.sonCadenasIguales(tipoDato_local, "GG"))
      {
        formatoNumeros_local = mc.formatearNumeroReal(Double.parseDouble(valorCampo_local));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
      valorCampo_local = null;
    } 
    
    return formatoNumeros_local;
  }
  public String conformarEtiquetaCampoConsulta(boolean pEsCampoDesde) {
    String etiquetaCampoConsulta_local = "";
    
    try {
      etiquetaCampoConsulta_local = getEtiquetaCampo();
      etiquetaCampoConsulta_local = mc.concatenarCadena(etiquetaCampoConsulta_local, String.valueOf(' '));
      
      if (pEsCampoDesde) {
        etiquetaCampoConsulta_local = mc.concatenarCadena(etiquetaCampoConsulta_local, mc.darFormatoTitulo("DESDE"));
      } else {
        
        etiquetaCampoConsulta_local = mc.concatenarCadena(etiquetaCampoConsulta_local, mc.darFormatoTitulo("HASTA"));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return etiquetaCampoConsulta_local;
  }
  public String conformarNombreCampoConsulta(int pNivelConsulta, boolean pEsCampoDesde) {
    String nombreCampoConsulta_local = "";
    
    try {
      nombreCampoConsulta_local = getNombreCampo();
      if (pEsCampoDesde) {
        nombreCampoConsulta_local = mc.concatenarCadena(nombreCampoConsulta_local, "DESDE");
      } else {
        
        nombreCampoConsulta_local = mc.concatenarCadena(nombreCampoConsulta_local, "HASTA");
      } 
      
      nombreCampoConsulta_local = mc.concatenarCadena(nombreCampoConsulta_local, String.valueOf(pNivelConsulta));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreCampoConsulta_local;
  }
  public String conformarNombreCompuestoCampo() {
    String nombreCompuestoCampo_local = "";
    String nombreGrupoInformacion_local = null;
    
    try {
      nombreGrupoInformacion_local = getGrupoInformacion().getNombreGrupoInformacion();
      if (!getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      nombreCompuestoCampo_local = mc.concatenarCadena(nombreGrupoInformacion_local, String.valueOf('.') + getNombreCampo());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreGrupoInformacion_local = null;
    } 
    
    return nombreCompuestoCampo_local;
  }
  public String obtenerAlineacionContenidoCelda() {
    String alineacionContenidoCelda_local = "";
    
    try {
      alineacionContenidoCelda_local = "left";
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "E") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "LL") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "R") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "W") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "GG"))
      {
        
        alineacionContenidoCelda_local = "right";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return alineacionContenidoCelda_local;
  }
  public String obtenerEquivalenteTipoDatoJavascript() {
    String equivalenteTipoDato_local = "";
    
    try {
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "D")) {
        return "verificarEsAlfabetico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "Q")) {
        return "verificarEsAlfabetico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "I")) {
        return "verificarEsAlfabetico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "G")) {
        return "verificarEsAlfabetico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "F")) {
        return "verificarEsNumeroTelefonico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "T")) {
        return "verificarEsAlfanumerico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "X")) {
        return "verificarEsAlfabeticoConPuntos";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "C")) {
        return "verificarEsAlfanumerico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "K")) {
        return "verificarEsEmail";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "E") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "BB"))
      {
        return "verificarEsEntero";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "R") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "GG"))
      {
        return "verificarEsNumero";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "FF")) {
        return "verificarEsAlfanumerico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "LL"))
      {
        return "verificarEsNumero";
      }
      if (esTipoDatoTabla()) {
        return "verificarEsEntero";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "J")) {
        return "verificarEsAlfanumerico";
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "NN")) {
        return "verificarEsParrafo";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return equivalenteTipoDato_local;
  }
  public String obtenerTipoDatoCampoPorNombreCampo(String pNombreBaseDatos) {
    String tipoDatocampo_local = "";
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    com.sisnet.baseDatos.sisnet.administrador.Campo campo_local = null;
    
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO) {
      return tipoDatocampo_local;
    }
    
    try {
      listaCampo_local = ap.obtenerListaGeneralCampos(pNombreBaseDatos);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterator_local = listaCampo_local.iterator();
        while (iterator_local.hasNext()) {
          campo_local = (Campo)iterator_local.next();
          if (mc.sonCadenasIguales(campo_local.getNombreCampo(), getNombreCampo())) {
            tipoDatocampo_local = campo_local.getFormatoCampo().getTipoDato();
            break;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterator_local = null;
      campo_local = null;
    } 
    return tipoDatocampo_local;
  }
  public boolean utilizarOpcionDesdeHastaParaConsulta() {
    boolean opcionDesdeHasta_local = false;
    
    try {
      opcionDesdeHasta_local = ((mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "E") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "R") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "F") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "H")) && !esCampoEnlazado());
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return opcionDesdeHasta_local;
  }
  public boolean esTipoDatoNumerico() {
    boolean tipoCampoNumerico_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        tipoCampoNumerico_local = (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "B") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "E") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "LL") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "R") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "OO") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "PP") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "W") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "GG") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "XX"));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoCampoNumerico_local;
  }
  public boolean esTipoDatoNumericoBorrarCerosIzquierda() {
    boolean tipoCampoNumerico_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        tipoCampoNumerico_local = (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "E") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "R") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "GG"));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoCampoNumerico_local;
  }
  public boolean esTipoDatoNumeroEntero() {
    boolean tipoDatoNumeroEntero = false;
    
    try {
      tipoDatoNumeroEntero = (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "E") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "LL") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "W") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "XX"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoDatoNumeroEntero;
  }
  public boolean esTipoDatoNumeroReal() {
    boolean tipoDatoNumeroReal = false;
    
    try {
      tipoDatoNumeroReal = (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "R") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "GG"));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoDatoNumeroReal;
  }
  public boolean esTipoDatoTabla() {
    boolean tipoCampoTabla_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        tipoCampoTabla_local = mc.esCadenaNumerica(getFormatoCampo().getTipoDato(), true);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoCampoTabla_local;
  }
  public boolean esTipoDatoTexto() {
    boolean esTipoDatoTexto_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        esTipoDatoTexto_local = (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "C") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "K") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "F") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "G") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "H") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "X") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "T") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "P") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "D") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "I") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "Q") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "NN") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "MM") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "DD"));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esTipoDatoTexto_local;
  }
  public boolean esTipoDatoParrafo() {
    boolean esTipoDatoParrafo_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        esTipoDatoParrafo_local = mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "NN");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esTipoDatoParrafo_local;
  }
  public boolean esTipoDatoDocumento() {
    boolean esTipoDatoDocumento_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        esTipoDatoDocumento_local = mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "DD");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esTipoDatoDocumento_local;
  }
  public boolean esTipoDatoArchivo() {
    boolean esTipoDatoArchivo_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        esTipoDatoArchivo_local = mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "J");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esTipoDatoArchivo_local;
  }
  public boolean esTipoDatoHora() {
    boolean esTipoDatoHora_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        esTipoDatoHora_local = mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "H");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esTipoDatoHora_local;
  }
  public boolean esTipoDatoFecha() {
    boolean esTipoDatoFecha_local = false;
    
    try {
      if (getFormatoCampo() != ConstantesGeneral.VALOR_NULO) {
        esTipoDatoFecha_local = mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "F");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esTipoDatoFecha_local;
  }
  public boolean esCampoEnlazado() {
    boolean campoEnlazado_local = false;
    
    try {
      campoEnlazado_local = (getEnlaceCampo().getEnlazado() != ConstantesGeneral.VALOR_NULO);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoEnlazado_local;
  }
  public boolean esCampoCalculado() {
    boolean campoCalculado_local = false;
    
    try {
      campoCalculado_local = (getCalculoCampo().getCampoCalculado() != 1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoCalculado_local;
  }
  public boolean esCampoOculto() {
    boolean campoOculto_local = false;
    
    return campoOculto_local;
  }
  public boolean esCampoHabilitado() {
    boolean campoHabilitado_local = false;
    
    return campoHabilitado_local;
  }
  public void decrementarPosicion() {
    setPosicion(getPosicion() - 1);
  }
  public void incrementarPosicion() {
    setPosicion(getPosicion() + 1);
  }
  public CeldaHTML conformarCeldaParaEtiqueta() {
    CeldaHTML celdaHTML_local = null;
    
    try {
      celdaHTML_local = new CeldaHTML();
      celdaHTML_local.setEstilo("etiqueta");
      celdaHTML_local.setNumeroFila(0);
      celdaHTML_local.setContenido(getEtiquetaCampo());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return celdaHTML_local;
  }
  private boolean esTipoControlTexto() {
    boolean tipoControl_local = false;
    
    try {
      tipoControl_local = (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "T") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "K") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "X") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "MM") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "C") || esTipoDatoNumeroEntero() || esTipoDatoNumeroReal() || (esTipoDatoHora() && !esCampoEnlazado()));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoControl_local;
  }
  private boolean esTipoControlLista() {
    boolean tipoControl_local = false;
    
    try {
      tipoControl_local = ((esTipoDatoNumeroEntero() && esCampoEnlazado()) || esTipoDatoTabla() || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "FF") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "QQ") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "D") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "I") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "Q") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "G") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "P") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "Z") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "A") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "HH") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "RR") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "SS") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "TT") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "UU") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "II") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "JJ") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "OO") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "PP") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "CC") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "M") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "N") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "AA") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "KK") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "WW") || mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "VV"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoControl_local;
  }
  private int obtenerTipoControl() {
    int tipoControl_local = 0;
    
    try {
      if (esTipoControlTexto()) {
        return 1;
      }
      if (esTipoControlLista()) {
        return 6;
      }
      if (esTipoDatoFecha()) {
        return 2;
      }
      if (esTipoDatoParrafo()) {
        return 3;
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "J")) {
        return 5;
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "DD")) {
        return 4;
      }
      if (mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "B")) {
        return 7;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tipoControl_local;
  }
  private CajaTextoHTML crearControlParaCajaTexto() {
    CajaTextoHTML cajaTextoHTML_local = null;
    
    try {
      cajaTextoHTML_local = new CajaTextoHTML();
      cajaTextoHTML_local.setEstilo("text");
      if (esCampoCalculado()) {
        cajaTextoHTML_local.setEstilo("textcalculado");
      }
      if (esCampoOculto()) {
        cajaTextoHTML_local.setEstilo("textoculto");
      }
      cajaTextoHTML_local.setId(getNombreCampo());
      cajaTextoHTML_local.setNombre(getNombreCampo());
      cajaTextoHTML_local.setMaximaLongitud(getFormatoCampo().getLongitudCampo());
      if (getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        cajaTextoHTML_local.setValor(getValorCampo().toString());
      }
      cajaTextoHTML_local.setEsHabilitado(esCampoHabilitado());
      cajaTextoHTML_local.setEsPassword(mc.sonCadenasIguales(getFormatoCampo().getTipoDato(), "C"));
      
      cajaTextoHTML_local.setEsSoloLectura(esCampoCalculado());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cajaTextoHTML_local;
  }
  private CajaFechaHTML crearControlParaCajaFecha() {
    CajaFechaHTML cajaFechaHTML_local = null;
    String funcionMostrarCalendario_local = null;
    CajaTextoHTML cajaTextoHTML_local = null;
    BotonHTML botonHTML_local = null;
    
    try {
      cajaTextoHTML_local = new CajaTextoHTML();
      cajaTextoHTML_local.setEstilo("text");
      if (esCampoCalculado()) {
        cajaTextoHTML_local.setEstilo("textcalculado");
      }
      if (esCampoOculto()) {
        cajaTextoHTML_local.setEstilo("textoculto");
      }
      cajaTextoHTML_local.setId(getNombreCampo());
      cajaTextoHTML_local.setNombre(getNombreCampo());
      cajaTextoHTML_local.setMaximaLongitud(getFormatoCampo().getLongitudCampo());
      if (getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        cajaTextoHTML_local.setValor(getValorCampo().toString());
      }
      cajaTextoHTML_local.setEsHabilitado(esCampoHabilitado());
      cajaTextoHTML_local.setEsSoloLectura(true);
      botonHTML_local = new BotonHTML();
      botonHTML_local.setId(mc.concatenarCadena("btn", getNombreCampo()));
      botonHTML_local.setNombre("ver");
      if (!esCampoCalculado()) {
        funcionMostrarCalendario_local = mc.concatenarCadena("showCalendar('", botonHTML_local.getId());
        
        funcionMostrarCalendario_local = mc.concatenarCadena(funcionMostrarCalendario_local, "', '%Y-%m-%d', '24')");
        
        botonHTML_local.setEventoOnClick(funcionMostrarCalendario_local);
      } 
      cajaFechaHTML_local = new CajaFechaHTML();
      cajaFechaHTML_local.setCajaTextoHTML(cajaTextoHTML_local);
      cajaFechaHTML_local.setBotonHTML(botonHTML_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      botonHTML_local = null;
      cajaTextoHTML_local = null;
      funcionMostrarCalendario_local = null;
    } 
    
    return cajaFechaHTML_local;
  }
  private AreaTextoHTML crearControlParaAreaTexto() {
    AreaTextoHTML areaTextoHTML_local = null;
    
    try {
      areaTextoHTML_local = new AreaTextoHTML();
      areaTextoHTML_local.setEstilo("parrafo");
      if (esCampoCalculado()) {
        areaTextoHTML_local.setEstilo("parrafocalculado");
      }
      if (esCampoOculto()) {
        areaTextoHTML_local.setEstilo("parrafooculto");
      }
      areaTextoHTML_local.setId(getNombreCampo());
      areaTextoHTML_local.setNombre(getNombreCampo());
      areaTextoHTML_local.setNumeroFilas(5);
      areaTextoHTML_local.setMaximaLongitud(10485760);
      if (getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        areaTextoHTML_local.setValor(getValorCampo().toString());
      }
      areaTextoHTML_local.setEsHabilitado(esCampoHabilitado());
      areaTextoHTML_local.setEsSoloLectura(esCampoCalculado());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return areaTextoHTML_local;
  }
  private CajaArchivoHTML crearControlParaCajaArchivo() {
    CajaArchivoHTML cajaArchivoHTML_local = null;
    
    try {
      cajaArchivoHTML_local = new CajaArchivoHTML();
      cajaArchivoHTML_local.setEstilo("text");
      cajaArchivoHTML_local.setId(getNombreCampo());
      cajaArchivoHTML_local.setNombre(getNombreCampo());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cajaArchivoHTML_local;
  }
  private CajaSeleccionHTML crearControlParaCajaSeleccion() {
    CajaSeleccionHTML cajaSeleccionHTML_local = null;
    
    try {
      cajaSeleccionHTML_local = new CajaSeleccionHTML();
      cajaSeleccionHTML_local.setEstilo("select");
      if (esCampoCalculado()) {
        cajaSeleccionHTML_local.setEstilo("selectcalculado");
      }
      if (esCampoOculto()) {
        cajaSeleccionHTML_local.setEstilo("selectoculto");
      }
      cajaSeleccionHTML_local.setId(getNombreCampo());
      cajaSeleccionHTML_local.setNombre(getNombreCampo());
      cajaSeleccionHTML_local.setListaOptionHTML(getListaOptionHTML());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cajaSeleccionHTML_local;
  }
  private CajaChequeoHTML crearControlParaCajaChequeo() {
    CajaChequeoHTML cajaChequeoHTML_local = null;
    
    try {
      cajaChequeoHTML_local = new CajaChequeoHTML();
      cajaChequeoHTML_local.setEstilo("checkbox");
      if (esCampoOculto()) {
        cajaChequeoHTML_local.setEstilo("checkboxOculto");
      }
      cajaChequeoHTML_local.setId(getNombreCampo());
      cajaChequeoHTML_local.setNombre(getNombreCampo());
      if (getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        cajaChequeoHTML_local.setValor(getValorCampo().toString());
        cajaChequeoHTML_local.setEsChequeado(mc.sonCadenasIgualesIgnorarMayusculas(cajaChequeoHTML_local.getValor(), "true"));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cajaChequeoHTML_local;
  }
  public CeldaHTML conformarCeldaControlParaFormulario() {
    CeldaHTML celdaHTML_local = null;
    CajaTextoHTML cajaTextoHTML_local = null;
    CajaFechaHTML cajaFechaHTML_local = null;
    AreaTextoHTML areaTextoHTML_local = null;
    CajaArchivoHTML cajaArchivoHTML_local = null;
    CajaSeleccionHTML cajaSeleccionHTML_local = null;
    CajaChequeoHTML cajaChequeoHTML_local = null;
    
    try {
      celdaHTML_local = new CeldaHTML();
      celdaHTML_local.setAlineacion(2);
      switch (obtenerTipoControl()) {
        case 1:
          cajaTextoHTML_local = crearControlParaCajaTexto();
          if (cajaTextoHTML_local != ConstantesGeneral.VALOR_NULO) {
            celdaHTML_local.setContenido(cajaTextoHTML_local.dibujar());
          }
          break;
        case 2:
          cajaFechaHTML_local = crearControlParaCajaFecha();
          if (cajaFechaHTML_local != ConstantesGeneral.VALOR_NULO) {
            celdaHTML_local.setContenido(cajaFechaHTML_local.dibujar());
          }
          break;
        case 3:
          areaTextoHTML_local = crearControlParaAreaTexto();
          if (areaTextoHTML_local != ConstantesGeneral.VALOR_NULO) {
            celdaHTML_local.setContenido(areaTextoHTML_local.dibujar());
          }
          break;
        case 5:
          cajaArchivoHTML_local = crearControlParaCajaArchivo();
          if (cajaArchivoHTML_local != ConstantesGeneral.VALOR_NULO) {
            celdaHTML_local.setContenido(cajaArchivoHTML_local.dibujar());
          }
          break;
        case 6:
          cajaSeleccionHTML_local = crearControlParaCajaSeleccion();
          if (cajaSeleccionHTML_local != ConstantesGeneral.VALOR_NULO) {
            celdaHTML_local.setContenido(cajaSeleccionHTML_local.dibujar());
          }
          break;
        case 7:
          cajaChequeoHTML_local = crearControlParaCajaChequeo();
          if (cajaChequeoHTML_local != ConstantesGeneral.VALOR_NULO)
            celdaHTML_local.setContenido(cajaChequeoHTML_local.dibujar()); 
          break;
        default:
          celdaHTML_local = null; break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cajaTextoHTML_local = null;
      cajaFechaHTML_local = null;
      areaTextoHTML_local = null;
      cajaArchivoHTML_local = null;
      cajaSeleccionHTML_local = null;
      cajaChequeoHTML_local = null;
    } 
    
    return celdaHTML_local;
  }
  public boolean esCampoFiltrado() {
    boolean esCampoFiltrado_local = false;
    
    try {
      esCampoFiltrado_local = (getEnlaceCampo().getFiltradoRegistrosEnlazados() != 1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esCampoFiltrado_local;
  }
  public String obtenerTipoDatoEquivalenteCampo() {
    String tipoDatoEquivalente_local = "";
    String tipoDato_local = null;
    
    try {
      tipoDato_local = getFormatoCampo().getTipoDato();
      if (mc.sonCadenasIguales(tipoDato_local, "E")) {
    	  tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "integer");
          
          return tipoDatoEquivalente_local;
      }else if ( mc.sonCadenasIguales(tipoDato_local, "R") || mc.sonCadenasIguales(tipoDato_local, "LL") || mc.sonCadenasIguales(tipoDato_local, "W") || mc.sonCadenasIguales(tipoDato_local, "BB") || mc.sonCadenasIguales(tipoDato_local, "GG") || mc.sonCadenasIguales(tipoDato_local, "XX") || mc.esCadenaNumerica(tipoDato_local, true)) {

        
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "numeric");
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, mc.colocarEntreParentesis(String.valueOf(getFormatoCampo().getLongitudCampo()) + ',' + String.valueOf(getFormatoCampo().getNumeroDecimales())));
        
        return tipoDatoEquivalente_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "T") || mc.sonCadenasIguales(tipoDato_local, "DD") || mc.sonCadenasIguales(tipoDato_local, "K") || mc.sonCadenasIguales(tipoDato_local, "NN") || mc.sonCadenasIguales(tipoDato_local, "MM")) {
        
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "varchar");
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, mc.colocarEntreParentesis(String.valueOf(getFormatoCampo().getLongitudCampo())));
        
        return tipoDatoEquivalente_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "B")) {
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "bool");
        return tipoDatoEquivalente_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "F")) {
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "date");
        return tipoDatoEquivalente_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "H")) {
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "time");
        return tipoDatoEquivalente_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "J")) {
        tipoDatoEquivalente_local = mc.concatenarCadena(tipoDatoEquivalente_local, "bytea");
        return tipoDatoEquivalente_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
    
    return tipoDatoEquivalente_local;
  }
  
  @Override
  public String toString()
  {
	return this.aNombreCampo + "[" + this.aEtiquetaCampo + "]";   
  }
public String getPlaceHolder() {
	return aPlaceHolder;
}
public void setPlaceHolder(String aPlaceHolder) {
	this.aPlaceHolder = aPlaceHolder;
}
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\Campo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */