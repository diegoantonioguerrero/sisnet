package com.sisnet.aplicacion.generadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.EstiloCampo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesConversorCaracteresHtml;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesMensajesAplicacion;
import com.sisnet.constantes.ConstantesVersion;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.ListaBotones;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.manejoPaginaJsp.Boton;
import com.sisnet.utilidades.CargadorPropiedades;
import com.sisnet.utilidades.ConversorCaracteresHtml;
import java.util.Iterator;
import java.util.UUID;

public class GeneradorComponentesHtml
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private String aAnchoCampos;
  private ConversorCaracteresHtml aConversorCaracteresHtml;
  private static CargadorPropiedades cp = CargadorPropiedades.getCargadorPropiedades();
  public GeneradorComponentesHtml() {
    setAnchoCampos("400");
    setConversorCaracteresHtml(new ConversorCaracteresHtml());
  }
  public String getAnchoCampos() {
    return this.aAnchoCampos;
  }
  public void setAnchoCampos(String pAnchoCampos) {
    this.aAnchoCampos = pAnchoCampos;
  }
  public ConversorCaracteresHtml getConversorCaracteresHtml() {
    return this.aConversorCaracteresHtml;
  }
  public void setConversorCaracteresHtml(ConversorCaracteresHtml pConversorCaracteresHtml) {
    this.aConversorCaracteresHtml = pConversorCaracteresHtml;
  }
  public String abrirHead() {
    return "<head>\n";
  }
  public String cerrarHead() {
    return "</head>\n";
  }
  public String abrirBloqueJavascript() {
    return "<script language=\"javascript\">\n <!-- Begin \n";
  }
  public String cerrarBloqueJavascript() {
    return "// End --> \n </script>\n";
  }
  public String getMetaData() {
    String metaData_local = "";
    try {
      metaData_local = mc.concatenarCadena(metaData_local, "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      
      metaData_local = mc.concatenarCadena(metaData_local, "<META name=\"language\" content=\"es\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return metaData_local;
  }
  public String getTituloPagina() {
    String tituloPagina_local = "";
    try {
      tituloPagina_local = mc.concatenarCadena(tituloPagina_local, "<title>");
      tituloPagina_local = mc.concatenarCadena(tituloPagina_local, ap.configuracionVersion());
      tituloPagina_local = mc.concatenarCadena(tituloPagina_local, "</title>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tituloPagina_local;
  }
  public String obtenerTituloPagina(String pTituloPagina) {
    String tituloPagina_local = "";
    
    if (pTituloPagina == ConstantesGeneral.VALOR_NULO) {
      return tituloPagina_local;
    }
    
    try {
      tituloPagina_local = mc.concatenarCadena(tituloPagina_local, "<title>");
      tituloPagina_local = mc.concatenarCadena(tituloPagina_local, pTituloPagina);
      tituloPagina_local = mc.concatenarCadena(tituloPagina_local, "</title>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tituloPagina_local;
  }
  public String getHojaEstiloSisnet(int pNivelesAnterioresDirectorio) {
    String hojaEstilo_local = "";
    try {
    	UUID uuid = UUID.randomUUID();

      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, "<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, mc.complementarDirectorio(pNivelesAnterioresDirectorio));
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, "../utilidades/css/");
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, "sisnet.css?p=" +     	uuid.toString() + "\"/>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return hojaEstilo_local;
  }
  public String getHojaEstiloSisnetInicial() {
    String hojaEstilol_local = "";
    try {
    	UUID uuid = UUID.randomUUID();
      hojaEstilol_local = mc.concatenarCadena(hojaEstilol_local, "<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      hojaEstilol_local = mc.concatenarCadena(hojaEstilol_local, "utilidades/css/");
      hojaEstilol_local = mc.concatenarCadena(hojaEstilol_local, "sisnet.css?p=" +     	uuid.toString() + "\"/>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return hojaEstilol_local;
  }
  public String getHojaEstiloCalendario(int pNivelesAnterioresDirectorio) {
    String hojaEstilo_local = "";
    try {
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, "<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, mc.complementarDirectorio(pNivelesAnterioresDirectorio));
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, "../utilidades/javascript/calendario/skins/");
      hojaEstilo_local = mc.concatenarCadena(hojaEstilo_local, "calendario.css\"/>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return hojaEstilo_local;
  }
  public String incluirLibreriaJavascript(String pDirectorio, String pNombreLibreria, int pNivelesAnterioresDirectorio) {
    String javascript_local = "";
    
    if (pDirectorio == ConstantesGeneral.VALOR_NULO) {
      return javascript_local;
    }
    if (pNombreLibreria == ConstantesGeneral.VALOR_NULO) {
      return javascript_local;
    }
    try {
      javascript_local = mc.concatenarCadena(javascript_local, "<script language=\"JavaScript\" type=\"text/javascript\" src=\"");
      javascript_local = mc.concatenarCadena(javascript_local, mc.complementarDirectorio(pNivelesAnterioresDirectorio));
      javascript_local = mc.concatenarCadena(javascript_local, pDirectorio + pNombreLibreria + "\" ></script>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return javascript_local;
  }
  public String obtenerMetaRedireccionamientoLogin(boolean pTransaccionBaseDatos) {
    String metaDireccionamientoSisnet_local = "";
    try {
      metaDireccionamientoSisnet_local = "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"";
      if (pTransaccionBaseDatos) {
        metaDireccionamientoSisnet_local = mc.concatenarCadena(metaDireccionamientoSisnet_local, String.valueOf(10));
      } else {
        
        metaDireccionamientoSisnet_local = mc.concatenarCadena(metaDireccionamientoSisnet_local, String.valueOf(0));
      } 
      
      metaDireccionamientoSisnet_local = mc.concatenarCadena(metaDireccionamientoSisnet_local, ";URL=login.jsp\">");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return metaDireccionamientoSisnet_local;
  }
  public String obtenerMetaRedireccionamientoConfiguracion() {
    String metaDireccionamientoSisnet_local = "";
    try {
      metaDireccionamientoSisnet_local = "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"";
      metaDireccionamientoSisnet_local = mc.concatenarCadena(metaDireccionamientoSisnet_local, String.valueOf(0));
      
      metaDireccionamientoSisnet_local = mc.concatenarCadena(metaDireccionamientoSisnet_local, ";URL=configuracion.jsp\">");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return metaDireccionamientoSisnet_local;
  }
  private String abrirCentrar() {
    return "<center>\n";
  }
  private String cerrarCentrar() {
    return "</center>\n";
  }
  private String saltarLinea() {
    return "<br/>\n";
  }
  public String obtenerMensajesPagina(int pNumeroError, int pTipoError) {
    String mensajePagina_local = "";
    
    try {
      if (pNumeroError == -1) {
        return mensajePagina_local;
      }
      switch (pTipoError) {
        case 1:
          mensajePagina_local = insertarMensajeAdvertencia(ConstantesMensajesAplicacion.getMensajeAplicacion(pNumeroError));
          break;
        case 2:
          mensajePagina_local = insertarMensajeError(ConstantesMensajesAplicacion.getMensajeAplicacion(pNumeroError));
          break;
        case 3:
          mensajePagina_local = insertarMensaje(ConstantesMensajesAplicacion.getMensajeAplicacion(pNumeroError));
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return mensajePagina_local;
  }
  public String obtenerMensajesPagina(String pMensaje, int pTipoMensaje) {
    String mensajePagina_local = "";
    
    if (pMensaje == ConstantesGeneral.VALOR_NULO) {
      return mensajePagina_local;
    }
    
    try {
      if (mc.esCadenaVacia(pMensaje)) {
        return mensajePagina_local;
      }
      switch (pTipoMensaje) {
        case 1:
          mensajePagina_local = insertarMensajeAdvertencia(pMensaje);
          break;
        case 2:
          mensajePagina_local = insertarMensajeError(pMensaje);
          break;
        case 3:
          mensajePagina_local = insertarMensaje(pMensaje);
          break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return mensajePagina_local;
  }
  public String abrirBody(String pEventosBody) {
    String body_local = "";
    String bloquearMenuContextual_local = "";
    
    if (pEventosBody == ConstantesGeneral.VALOR_NULO) {
      return body_local;
    }
    
    try {
      if (mc.sonCadenasIgualesIgnorarMayusculas(cp.obtenerValorPropiedadSisnet("BLOQUEAR_MENU_CONTEXTUAL"), "Si"))
      {
        bloquearMenuContextual_local = " oncontextmenu=\"return false\" ";
      }
      if (mc.esCadenaVacia(pEventosBody)) {
        body_local = "<body " + bloquearMenuContextual_local + " bgcolor=\"#9fb6cd\">\n";
      } else {
        body_local = "<body " + pEventosBody + bloquearMenuContextual_local + " bgcolor=\"#9fb6cd\">\n";
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloquearMenuContextual_local = null;
    } 
    
    return body_local;
  }
  public String cerrarBody() {
    return "</body>\n";
  }
  public String abrirFormulario(String pNombre, String pAccion, String pEventos) {
    String formulario_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return formulario_local;
    }
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return formulario_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return formulario_local;
    }
    try {
      formulario_local = mc.concatenarCadena(formulario_local, "<form action=\"" + pAccion);
      formulario_local = mc.concatenarCadena(formulario_local, "\" name=\"" + pNombre);
      formulario_local = mc.concatenarCadena(formulario_local, "\" id=\"" + pNombre);
      formulario_local = mc.concatenarCadena(formulario_local, "\" method=\"post\" " + pEventos + ">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return formulario_local;
  }
  public String cerrarFormulario() {
    return "</form>\n";
  }
  public String insertarImagen(String pUbicacion, String pDescripcion, String pEventos) {
    String imagen_local = "";
    
    if (pUbicacion == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    try {
      imagen_local = mc.concatenarCadena(imagen_local, "<img src=\"" + pUbicacion);
      imagen_local = mc.concatenarCadena(imagen_local, "\" alt=\"" + pDescripcion);
      imagen_local = mc.concatenarCadena(imagen_local, "\" " + pEventos + " >\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return imagen_local;
  }
  public String insertarImagenCentrada(String pNombre, String pUbicacion, String pDescripcion, String pEventos, boolean pSaltoLinea, boolean pEsImagenOculta) {
    String imagen_local = "";
    
    if (pUbicacion == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    try {
      imagen_local = mc.concatenarCadena(imagen_local, abrirCentrar());
      imagen_local = mc.concatenarCadena(imagen_local, "<img id=\"" + pNombre);
      imagen_local = mc.concatenarCadena(imagen_local, "\" src=\"" + pUbicacion);
      imagen_local = mc.concatenarCadena(imagen_local, "\" alt=\"" + pDescripcion + "\"");
      
      if (pEsImagenOculta) {
        imagen_local = mc.concatenarCadena(imagen_local, " style=\"display:none\" ");
      }
      
      imagen_local = mc.concatenarCadena(imagen_local, pEventos + " >\n");
      imagen_local = mc.concatenarCadena(imagen_local, cerrarCentrar());
      if (pSaltoLinea) {
        imagen_local = mc.concatenarCadena(imagen_local, saltarLinea());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return imagen_local;
  }
  public String insertarLineaFlash() {
    return "<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\"1600\" height=\"36\" align=\"absmiddle\"> <param name=\"movie\" value=\"tasas/lineasbottom.swf\"> <param name=\"quality\" value=\"high\"> <embed src=\"tasas/lineasbottom.swf\" width=\"1600\" height=\"36\" align=\"absmiddle\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\"> </embed> </object>";
  }
  public String insertarBanner(String pNombreImagen, int pNivelesAnterioresDirectorio) {
    String banner_local = "";
    
    if (pNombreImagen == ConstantesGeneral.VALOR_NULO) {
      return banner_local;
    }
    try {
      banner_local = insertarImagenCentrada("", mc.complementarDirectorio(pNivelesAnterioresDirectorio) + "../imagenes/logos/" + pNombreImagen + ".gif", "", "", true, false);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return banner_local;
  }
  public String insertarTextoVineta(String pMensaje, int pTipo) {
    String texto_local = "";
    
    if (pMensaje == ConstantesGeneral.VALOR_NULO) {
      return texto_local;
    }
    try {
      if (mc.sonCadenasIguales(String.valueOf(pTipo), String.valueOf(1))) {
        texto_local = "<ul type= circle><li>" + pMensaje + "</li>" + "</ul>";
      }
      else if (mc.sonCadenasIguales(String.valueOf(pTipo), String.valueOf(2))) {
        texto_local = "<ul type= square><li>" + pMensaje + "</li>" + "</ul>";
      } else {
        texto_local = "<ul><li>" + pMensaje + "</li>" + "</ul>";
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return texto_local;
  }
  public String insertarMarquesina(String pContenido, String pAlineacion) {
    String marquesina_local = "";
    
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return marquesina_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return marquesina_local;
    }
    try {
      marquesina_local = mc.concatenarCadena(marquesina_local, "<marquee bgcolor=\"#006699\" behavior=\"alternate\" direction=\"" + pAlineacion + "\"><b>");
      
      marquesina_local = mc.concatenarCadena(marquesina_local, "<font color=\"#FFFFCC\" size=\"5\">" + pContenido + "</font>" + "</b>" + "</marquee>");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return marquesina_local;
  }
  public String insertarTituloTipo1(String pTitulo) {
    String titulo_local = "";
    
    if (pTitulo == ConstantesGeneral.VALOR_NULO) {
      return titulo_local;
    }
    try {
      titulo_local = mc.concatenarCadena(titulo_local, "<div align=\"left\"><h1 id=\"" + mc.convertirAMinusculas(mc.convertirCadenaFormatoNombre(pTitulo)) + "\">");
      
      titulo_local = mc.concatenarCadena(titulo_local, getConversorCaracteresHtml().getCadenaHtml(pTitulo) + "</h1></div>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return titulo_local;
  }
  public String insertarTituloTipoEspecial(String pTitulo) {
    String titulo_local = "";
    
    if (pTitulo == ConstantesGeneral.VALOR_NULO) {
      return titulo_local;
    }
    try {
      titulo_local = mc.concatenarCadena(titulo_local, "<div align=\"left\"><h5 class=\"h1especial\" id=\"" + mc.convertirAMinusculas(mc.convertirCadenaFormatoNombre(pTitulo)) + "\">");
      
      titulo_local = mc.concatenarCadena(titulo_local, getConversorCaracteresHtml().getCadenaHtml(pTitulo) + "</h5></div>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return titulo_local;
  }
  public String insertarTituloTipo2(String pTitulo) {
    String titulo_local = "";
    
    if (pTitulo == ConstantesGeneral.VALOR_NULO) {
      return titulo_local;
    }
    try {
      titulo_local = mc.concatenarCadena(titulo_local, "<div align=\"left\"><h2 id=\"" + mc.convertirAMinusculas(mc.convertirCadenaFormatoNombre(pTitulo)) + "\">");
      
      titulo_local = mc.concatenarCadena(titulo_local, getConversorCaracteresHtml().getCadenaHtml(pTitulo) + "</h2></div>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return titulo_local;
  }
  public String insertarTituloTipo3(String pTitulo) {
    String titulo_local = "";
    
    if (pTitulo == ConstantesGeneral.VALOR_NULO) {
      return titulo_local;
    }
    try {
      titulo_local = mc.concatenarCadena(titulo_local, "<div align=\"left\"><h3 id=\"" + mc.convertirAMinusculas(mc.convertirCadenaFormatoNombre(pTitulo)) + "\">");
      
      titulo_local = mc.concatenarCadena(titulo_local, getConversorCaracteresHtml().getCadenaHtml(pTitulo) + "</h3></div>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return titulo_local;
  }
  public String insertarTexto(String pTitulo) {
    String titulo_local = "";
    
    if (pTitulo == ConstantesGeneral.VALOR_NULO) {
      return titulo_local;
    }
    try {
      titulo_local = mc.concatenarCadena(titulo_local, "<h4>" + getConversorCaracteresHtml().getCadenaHtml(pTitulo) + "</h4>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return titulo_local;
  }
  public String insertarMarquee(String pContenido) {
    String marquee_local = "";
    
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return marquee_local;
    }
    try {
      marquee_local = mc.concatenarCadena(marquee_local, "<marquee>" + pContenido);
      marquee_local = mc.concatenarCadena(marquee_local, "</marquee>\n" + saltarLinea());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return marquee_local;
  }
  public String insertarMensajeAdvertencia(String pMensajeAdvertencia) {
    String mensajeAdvertencia_local = "";
    
    if (pMensajeAdvertencia == ConstantesGeneral.VALOR_NULO) {
      return mensajeAdvertencia_local;
    }
    try {
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "<table class=\"advertencia\" align=\"center\">\n");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "<tr>");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "<th class=\"tituloadvertencia\">");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "Advertencia");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "</th></tr>\n");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "<tr><th>");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, getConversorCaracteresHtml().getCadenaHtml(pMensajeAdvertencia) + "</th>");
      
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, "</tr>\n</table>\n");
      mensajeAdvertencia_local = mc.concatenarCadena(mensajeAdvertencia_local, saltarLinea());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return mensajeAdvertencia_local;
  }
  public String insertarMensajeError(String pMensajeError) {
    String mensajeError_local = "";
    
    if (pMensajeError == ConstantesGeneral.VALOR_NULO) {
      return mensajeError_local;
    }
    try {
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "<table class=\"error\" align=\"center\">\n");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "<tr>");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "<th class=\"tituloerror\">");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "Error");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "</th></tr>\n");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "<tr><th>");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, getConversorCaracteresHtml().getCadenaHtml(pMensajeError) + "</th>");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, "</tr>\n</table>\n");
      mensajeError_local = mc.concatenarCadena(mensajeError_local, saltarLinea());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return mensajeError_local;
  }
  public String insertarMensaje(String pMensajeMensaje) {
    String mensaje_local = "";
    
    if (pMensajeMensaje == ConstantesGeneral.VALOR_NULO) {
      return mensaje_local;
    }
    try {
      mensaje_local = mc.concatenarCadena(mensaje_local, "<table class=\"mensaje\" align=\"center\">\n");
      mensaje_local = mc.concatenarCadena(mensaje_local, "<tr>");
      mensaje_local = mc.concatenarCadena(mensaje_local, "<th class=\"titulomensaje\">");
      mensaje_local = mc.concatenarCadena(mensaje_local, "Mensaje");
      mensaje_local = mc.concatenarCadena(mensaje_local, "</th></tr>\n");
      mensaje_local = mc.concatenarCadena(mensaje_local, "<tr><th>");
      mensaje_local = mc.concatenarCadena(mensaje_local, getConversorCaracteresHtml().getCadenaHtml(pMensajeMensaje) + "</th>");
      mensaje_local = mc.concatenarCadena(mensaje_local, "</tr>\n</table>\n");
      mensaje_local = mc.concatenarCadena(mensaje_local, saltarLinea());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return mensaje_local;
  }
  public String abrirTabla(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"tabla\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaTransparente(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"transparente\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaTransparenteInterna(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"transparenteinterna\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return tabla_local;
  }
  public String abrirTablaInternaFormulario(String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"internaformulario\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaTitulo(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"titulo\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaMarca(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"marca\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaFormulario(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"formulario\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaFormularioDistribucion(GrupoInformacion pGrupoInformacion, int pAnchoTabla) {
    String tabla_local = "";
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    
    try {
      tabla_local = "<table style=\"border-collapse: collapse; border: medium none; margin-top:" + pGrupoInformacion.getMargenSuperior() + "\" align=\"center\" cellspacing=\"0\" border=\"0\" width=\"" + pAnchoTabla + "\"> " + "<tr> " + "<td style=\"border: black 1px solid; text-align: center; font-family: arial; font-weight:bold; font-size:16px;" + " filter:progid:DXImageTransform.Microsoft.Gradient(startColorstr='#66CC66',endColorstr='#006600', gradientType='0');\">" + pGrupoInformacion.getDescripcionGrupoInformacion() + "</td> " + "</tr> " + "<tr> " + "<td style=\"border: black 1px solid; " + " filter:progid:DXImageTransform.Microsoft.Gradient(startColorstr='#66CC66',endColorstr='#006600', gradientType='0');\">" + "<div style=\"margin: 5\">";
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String abrirTablaInformacionAplicacion(String pNombre, String pAncho, String pAlineacion) {
    String tabla_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    try {
      tabla_local = mc.concatenarCadena(tabla_local, "<table class=\"tablainformacionaplicacion\" align=\"" + pAlineacion);
      tabla_local = mc.concatenarCadena(tabla_local, "\" name=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" id=\"" + pNombre);
      tabla_local = mc.concatenarCadena(tabla_local, "\" width=\"" + pAncho + "\">\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tabla_local;
  }
  public String cerrarTabla() {
    return "</table>\n" + saltarLinea();
  }
  public String cerrarTablaSinSalto() {
    return "</table>\n";
  }
  public String cerrarTablaFormulario() {
    return "</div> </td> </tr> </table>";
  }
  public String abrirFilaTabla() {
    return "<tr>\n";
  }
  public String cerrarFilaTabla() {
    return "</tr>\n";
  }
  public String crearCelda(String pContenidoCelda, String pAlineacion, String pAncho) {
    String celda_local = "";
    String ancho_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      if (!mc.sonCadenasIguales(pAncho, "")) {
        ancho_local = " width=\"" + pAncho + "\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tablaContenido\" " + ancho_local);
      celda_local = mc.concatenarCadena(celda_local, " align=\" " + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      ancho_local = null;
    } 
    return celda_local;
  }
  public String crearCeldaSoloLectura(String pContenidoCelda, String pAlineacion, boolean pEsColorGris) {
    String celda_local = "";
    String claseCelda_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      claseCelda_local = "class=\"tablaContenido\" ";
      if (pEsColorGris) {
        claseCelda_local = "class=\"tablaContenidoAlterno\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th " + claseCelda_local);
      celda_local = mc.concatenarCadena(celda_local, " align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      claseCelda_local = null;
    } 
    return celda_local;
  }
  public String crearCeldaAnchoTransparente(String pContenidoCelda, String pAlineacion, String pAncho) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"transparente\" width=\"" + pAncho);
      celda_local = mc.concatenarCadena(celda_local, "\" align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaAnchoMarca(String pContenidoCelda, String pAnchoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAnchoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"marca\" width=\"" + pAnchoCelda + "\" align=\"center\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaHipervinculo(String pContenidoCelda, String pAlineacion, String pDestino, int pAncho, boolean pEsColorGris, String pEventosHipervinculo, boolean pEsParrafo) {
    String celda_local = "";
    String estilo_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (Integer.valueOf(pAncho) == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pEventosHipervinculo == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      estilo_local = "class=\"tablaContenido\" ";
      if (pEsColorGris) {
        estilo_local = "class=\"tablaContenidoAlterno\" ";
      }
      if (pEsParrafo) {
        estilo_local = "class=\"tablaContenidoParrafo\" ";
        if (pEsColorGris) {
          estilo_local = "class=\"tablaContenidoParrafoAlterno\" ";
        }
      } 
      celda_local = mc.concatenarCadena(celda_local, "<th width=\"" + pAncho);
      celda_local = mc.concatenarCadena(celda_local, "\" " + estilo_local);
      celda_local = mc.concatenarCadena(celda_local, "align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, insertarVinculoTexto(getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda), pDestino, false, pEventosHipervinculo, pEsParrafo) + "</th> \n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    return celda_local;
  }
  public String crearCeldaHipervinculoImagen(String pRuta, String pAlineacion, String pDestino, int pAnchoImagen, int pAltoImagen, boolean pResaltarRegistro, String pEventos, String pDescripcion) {
    String celda_local = "";
    String estilo_local = null;
    
    if (pRuta == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      estilo_local = "class=\"tablaContenido\" ";
      if (pResaltarRegistro) {
        estilo_local = "class=\"tablaContenidoAlterno\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th width=\"" + pAnchoImagen);
      celda_local = mc.concatenarCadena(celda_local, "\" " + estilo_local);
      celda_local = mc.concatenarCadena(celda_local, "align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, insertarHiperVinculoImagenConDimensionesAnchoAlto(pRuta, pDestino, pAnchoImagen, pAltoImagen, false, pEventos, pDescripcion) + "</th> \n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    
    return celda_local;
  }
  public String crearCeldaSisnet(String pContenidoCelda, String pAlineacion) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tablaContenidoSisnet\" ");
      celda_local = mc.concatenarCadena(celda_local, "align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaContenidoImagen(String pContenidoCelda, String pAlineacion, String pAncho, boolean pEsColorGris) {
    String celda_local = "";
    String estilo_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      estilo_local = "class=\"tablaContenidoImagen\" ";
      if (pEsColorGris) {
        estilo_local = "class=\"tablaContenidoImagenAlterno\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th width=\"" + pAncho + "\" " + estilo_local);
      celda_local = mc.concatenarCadena(celda_local, " align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    return celda_local;
  }
  public String crearCeldaTransparenteContenidoImagen(String pContenidoCelda, String pAlineacion, String pAncho) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th width=\"" + pAncho);
      celda_local = mc.concatenarCadena(celda_local, "\" class=\"transparente\" align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, pContenidoCelda + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaTransparente(String pContenidoCelda, String pAlineacion, String pAncho) {
    String celda_local = "";
    String ancho_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      ancho_local = "";
      if (!mc.sonCadenasIguales(pAncho, "")) {
        ancho_local = " width=\"" + pAncho + "\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"transparente\" " + ancho_local);
      celda_local = mc.concatenarCadena(celda_local, " align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, pContenidoCelda + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaTituloEspecial(String pContenidoCelda, String pAlineacion, String pAncho) {
    String celda_local = "";
    String ancho_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      ancho_local = "";
      if (!mc.sonCadenasIguales(pAncho, "")) {
        ancho_local = " width=\"" + pAncho + "\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tituloespecial\" " + ancho_local);
      celda_local = mc.concatenarCadena(celda_local, " align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, pContenidoCelda + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaCampoDistribucion(EstiloCampo pEstiloCampo, String pContenidoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th style=\"background-color:transparent; vertical-align:top; text-align:left;\" width=\"" + pEstiloCampo.getAnchoControl());
      
      celda_local = mc.concatenarCadena(celda_local, "\">");
      celda_local = mc.concatenarCadena(celda_local, "<div style=\"margin-top:" + pEstiloCampo.getMargenSuperior() + "; margin-left:" + pEstiloCampo.getMargenIzquierdaControl() + "\">");
      
      celda_local = mc.concatenarCadena(celda_local, pContenidoCelda + "</div></th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaCampoDistribucionSinMargenSuperior(EstiloCampo pEstiloCampo, String pContenidoCelda, boolean pColocarFondoBlanco) {
    String celda_local = "";
    String colorFondo_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      colorFondo_local = "trasnparent";
      if (pColocarFondoBlanco) {
        colorFondo_local = "#FFFFFF";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th style=\"background-color:" + colorFondo_local + "; vertical-align:top; " + "text-align:left;\" width=\"" + pEstiloCampo.getAnchoControl());
      
      celda_local = mc.concatenarCadena(celda_local, "\">");
      celda_local = mc.concatenarCadena(celda_local, "<div style=\"margin-top:0;margin-left:" + pEstiloCampo.getMargenIzquierdaControl() + "\">");
      
      celda_local = mc.concatenarCadena(celda_local, pContenidoCelda + "</div></th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      colorFondo_local = null;
    } 
    
    return celda_local;
  }
  public String crearCeldaEncabezado(String pContenidoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena("<th class=\"tabla\">", getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEncabezadoAncho(String pContenidoCelda, int pAnchoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tabla\" width=\"" + pAnchoCelda + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEncabezadoCombinadaFilas(String pContenidoCelda, int pNumeroFilas) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tabla\" rowspan=\"" + pNumeroFilas + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEncabezadoCombinadaFilasAncho(String pContenidoCelda, int pNumeroFilas, int pAnchoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tabla\" rowspan=\"" + pNumeroFilas);
      celda_local = mc.concatenarCadena(celda_local, "\" width=\"" + pAnchoCelda + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaInformacionAplicacionAncho(String pContenidoCelda, String pAnchoCelda, String pAlineacion, String pEventos) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAnchoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"titulo\" align=\"" + pAlineacion);
      celda_local = mc.concatenarCadena(celda_local, "\" valign=\"top\" width=\"" + pAnchoCelda + "\" " + pEventos + ">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaInformacionAplicacionSubtituloAncho(String pContenidoCelda, String pAnchoCelda, String pAlineacion, String pEventos) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAnchoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"subtitulo\" align=\"" + pAlineacion);
      celda_local = mc.concatenarCadena(celda_local, "\" valign=\"top\" width=\"" + pAnchoCelda + "\" " + pEventos + ">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEncabezadoCombinadaColumnas(String pContenidoCelda, int pNumeroColumnas) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tabla\" colspan=\"" + pNumeroColumnas + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEncabezadoCombinadaColumnasAncho(String pContenidoCelda, int pNumeroColumnas, int pAnchoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"tabla\" colspan=\"" + pNumeroColumnas);
      celda_local = mc.concatenarCadena(celda_local, "\" width=\"" + pAnchoCelda + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaCombinadaFilas(String pContenidoCelda, int pNumeroFilas, String pAlineacion) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<td align=\"" + pAlineacion);
      celda_local = mc.concatenarCadena(celda_local, "\" rowspan=\"" + pNumeroFilas + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaCombinadaColumnasAncho(String pContenidoCelda, String pAncho, int pNumeroColumnas, String pAlineacion, boolean pEsColorGris) {
    String celda_local = "";
    String clase_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      clase_local = " class=\"tablaContenido\" ";
      if (pEsColorGris) {
        clase_local = " class=\"tablaContenidoAlterno\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th " + clase_local + " width=\"" + pAncho);
      celda_local = mc.concatenarCadena(celda_local, "\" align=\"" + pAlineacion + "\" colspan=\"" + pNumeroColumnas + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      clase_local = null;
    } 
    return celda_local;
  }
  public String crearCeldaCombinadaColumnasTransparente(String pContenidoCelda, int pNumeroColumnas, String pAlineacion) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"transparente\" align=\"" + pAlineacion);
      celda_local = mc.concatenarCadena(celda_local, "\" colspan=\"" + pNumeroColumnas + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  private String crearCeldaCombinadaColumnasEtiqueta(String pContenidoCelda, int pNumeroColumnas, String pAlineacion) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"etiqueta\" align=\"" + pAlineacion);
      celda_local = mc.concatenarCadena(celda_local, "\" colspan=\"" + pNumeroColumnas + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEtiqueta(String pContenidoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th width=\"200");
      celda_local = mc.concatenarCadena(celda_local, "\" class=\"etiqueta\" align=\"left\">");
      
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(mc.darFormatoTitulo(pContenidoCelda)) + "</th> \n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEtiqueta(EstiloCampo pEstiloCampo, String pContenidoCelda) {
    String celda_local = "";
    
    if (pEstiloCampo == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th style=\"background-color:transparent; text-align:left; font-size:11px; font-weight:bold; vertical-align:top\" width=\"" + pEstiloCampo.getAnchoEtiqueta());
      
      celda_local = mc.concatenarCadena(celda_local, "\" align=\"left\">");
      
      celda_local = mc.concatenarCadena(celda_local, "<div style=\"margin-top:" + pEstiloCampo.getMargenSuperior() + "; margin-left:" + pEstiloCampo.getMargenIzquierdaEtiqueta() + "\">");
      
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(mc.darFormatoTitulo(pContenidoCelda)) + "</div></th> \n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEtiquetaSinMargenSuperior(EstiloCampo pEstiloCampo, String pContenidoCelda) {
    String celda_local = "";
    
    if (pEstiloCampo == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th style=\"background-color:transparent; text-align:left; font-size:11px; font-weight:bold; vertical-align:top\" width=\"" + pEstiloCampo.getAnchoEtiqueta());
      
      celda_local = mc.concatenarCadena(celda_local, "\" align=\"left\">");
      
      celda_local = mc.concatenarCadena(celda_local, "<div style=\"margin-top:0;margin-left:" + pEstiloCampo.getMargenIzquierdaEtiqueta() + "\">");
      
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(mc.darFormatoTitulo(pContenidoCelda)) + "</div></th> \n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  private String crearCeldaEtiquetaTransparente() {
    String celda_local = "";
    
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th class=\"transparente\" width=\"200");
      
      celda_local = mc.concatenarCadena(celda_local, "\" align=\"left\"></th> \n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaImagenFondo(String pContenidoCelda, String pNombreImagen, String pAlineacion, String pAnchoCelda, int pNivelesAnterioresDirectorio) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pNombreImagen == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAnchoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<td width=\"" + pAnchoCelda + "\" class=\"transparente\" ");
      celda_local = mc.concatenarCadena(celda_local, "background=\"" + mc.complementarDirectorio(pNivelesAnterioresDirectorio) + "../imagenes/botones/" + pNombreImagen + pAnchoCelda + ".gif" + "\" ");
      
      celda_local = mc.concatenarCadena(celda_local, "align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</td> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String insertarBoton(Boton pBoton, int pNivelesAnterioresDirectorio) {
    String boton_local = "";
    String directorio_local = null;
    
    if (pBoton == ConstantesGeneral.VALOR_NULO) {
      return boton_local;
    }
    try {
      directorio_local = mc.complementarDirectorio(pNivelesAnterioresDirectorio);
      if (pBoton.esEntradaFormulario()) {
        boton_local = mc.concatenarCadena(boton_local, insertarEntradaImagen(pBoton.getNombreBoton(), directorio_local + pBoton.getUbicacionImagen1(), pBoton.getDescripcionBoton(), "" + pBoton.getEventos(), pBoton.isWithCustomImages()));
      }
      else {
        
        boton_local = mc.concatenarCadena(boton_local, insertarHipervinculoImagen(directorio_local + pBoton.getUbicacionImagen1(), pBoton.getDescripcionBoton(), pBoton.getDestinoBoton(), "" + pBoton.getEventos(), pBoton.esAbrirVentanaNueva(), pBoton.isWithCustomImages()));
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      directorio_local = null;
    } 
    return boton_local;
  }
  public String insertarBotones(ListaBotones pListaBotones, int pNivelesAnterioresDirectorio, String pAlineacion) {
    String tablaBotones_local = "";
    Iterator iterator_local = null;
    
    if (pListaBotones == ConstantesGeneral.VALOR_NULO) {
      return tablaBotones_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return tablaBotones_local;
    }
    
    try {
      tablaBotones_local = "<table class=\"botones\" align=\"" + pAlineacion + "\">";
      tablaBotones_local = mc.concatenarCadena(tablaBotones_local, abrirFilaTabla());
      iterator_local = pListaBotones.iterator();
      while (iterator_local.hasNext()) {
        tablaBotones_local = mc.concatenarCadena(tablaBotones_local, "<td>");
        Boton boton_local = (Boton)iterator_local.next();
        tablaBotones_local = mc.concatenarCadena(tablaBotones_local, insertarBoton(boton_local, pNivelesAnterioresDirectorio));
        tablaBotones_local = mc.concatenarCadena(tablaBotones_local, "</td>");
      } 
      tablaBotones_local = mc.concatenarCadena(tablaBotones_local, cerrarFilaTabla());
      tablaBotones_local = mc.concatenarCadena(tablaBotones_local, cerrarTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
    
    return tablaBotones_local;
  }
  public String insertarVinculoTexto(String pTexto, String pDestino, boolean pCentrar, String pEventos, boolean pEsParrafo) {
    String vinculoTexto_local = "";
    
    if (pTexto == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    
    try {
      if (pEsParrafo) {
        vinculoTexto_local = " <pre>";
      }
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, "<a href=\"" + pDestino);
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, "\" class=\"nounderline\" ");
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, pEventos + ">");
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, getConversorCaracteresHtml().getCadenaHtml(pTexto) + "</a>");
      if (pEsParrafo) {
        vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, " </pre>\n ");
      }
      if (pCentrar) {
        vinculoTexto_local = mc.concatenarCadena(abrirCentrar(), vinculoTexto_local);
        vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, cerrarCentrar());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return vinculoTexto_local;
  }
  public String insertarHiperVinculoImagenConDimensionesAnchoAlto(String pRuta, String pDestino, int pAnchoImagen, int pAltoImagen, boolean pAbrirNuevaVentana, String pEventos, String pDescripcion) {
    String hipervinculoImagen_local = "";
    
    if (pRuta == ConstantesGeneral.VALOR_NULO) {
      return hipervinculoImagen_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return hipervinculoImagen_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return hipervinculoImagen_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return hipervinculoImagen_local;
    }
    
    try {
      hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, "<a href=\"" + pDestino);
      hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, "\" class=\"nounderline\"");
      if (pAbrirNuevaVentana) {
        hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, " target=\"_blank\" ");
      }
      
      hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, pEventos);
      hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, ">");
      hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, "<img class=\"imagen\" src=\"" + pRuta + "\" width=\"" + (pAnchoImagen - 6) + "\" height=\"" + (pAltoImagen - 6) + "\" alt=\"" + pDescripcion + "\">");
      
      hipervinculoImagen_local = mc.concatenarCadena(hipervinculoImagen_local, "</a>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return hipervinculoImagen_local;
  }
  public String insertarVinculoMarca(String pTexto, String pDestino) {
    String vinculoTexto_local = "";
    
    if (pTexto == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    try {
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, "<a href=\"" + pDestino);
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, "\" class=\"marca\">");
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, getConversorCaracteresHtml().getCadenaHtml(pTexto) + "</a>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return vinculoTexto_local;
  }
  public String insertarVinculoMarcaAplicacion(String pTexto, String pDestino, String pEventoOnClick) {
    String vinculoTexto_local = "";
    
    if (pTexto == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    if (pEventoOnClick == ConstantesGeneral.VALOR_NULO) {
      return vinculoTexto_local;
    }
    
    try {
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, "<a href=\"" + pDestino);
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, "\" class=\"marca\"" + pEventoOnClick + ">");
      vinculoTexto_local = mc.concatenarCadena(vinculoTexto_local, getConversorCaracteresHtml().getCadenaHtml(pTexto) + "</a>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return vinculoTexto_local;
  }
  private String insertarHipervinculoImagen(String pUbicacion, String pDescripcion, String pDestino, String pEventos, boolean pAbrirVentanaNueva, boolean pWithCustomImages) {
    String vinculo_local = "";
    
    if (pUbicacion == ConstantesGeneral.VALOR_NULO) {
      return vinculo_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return vinculo_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return vinculo_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return vinculo_local;
    }
    
    try {
      vinculo_local = mc.concatenarCadena(vinculo_local, "<a href=\"" + pDestino);
      vinculo_local = mc.concatenarCadena(vinculo_local, "\" class=\"nounderline\" ");
      if (pAbrirVentanaNueva) {
        vinculo_local = mc.concatenarCadena(vinculo_local, " target=\"_blank\" ");
      }
      vinculo_local = mc.concatenarCadena(vinculo_local, pEventos);
      vinculo_local = mc.concatenarCadena(vinculo_local, ">");
      vinculo_local = mc.concatenarCadena(vinculo_local, "<img src=\"" + pUbicacion);
      vinculo_local = mc.concatenarCadena(vinculo_local, "\" alt=\"" + pDescripcion + "\"");
      vinculo_local = mc.concatenarCadena(vinculo_local, " title=\"" + pDescripcion + "\"");
      if(pWithCustomImages)
      {
    	  vinculo_local = mc.concatenarCadena(vinculo_local, " data-customimg=\"true\"");
      }
      vinculo_local = mc.concatenarCadena(vinculo_local, "></a>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return vinculo_local;
  }
  public String insertarCajaTexto(String pNombre, String pContenido, String pEventos, int pLongitud, boolean pEsCampoOculto, boolean pEsCampoCalculado, String pPlaceHolder) {
    String cajaTexto_local = "";
    String longitudPermitida_local = null;
    String estilo_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    
    try {
      if (pLongitud != -1) {
        longitudPermitida_local = " maxlength=\"" + pLongitud + "\" ";
      }
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display: none;";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #9FCC9F; vertical-align:middle;";
      } 
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "<input type=\"text\" style=\"" + estilo_local);
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "width:" + getAnchoCampos() + "px\" id=\"" + pNombre + "\" name=\"" + pNombre);
      
      if(pPlaceHolder != ConstantesGeneral.VALOR_NULO && !pPlaceHolder.isEmpty())
      {
    	  pPlaceHolder = " placeholder=\"" + pPlaceHolder + "\" ";
      }
      

      pContenido = getConversorCaracteresHtml().getCadenaHtml(pContenido);
      pContenido = mc.reemplazarCadena(pContenido, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterQuotation),
              ConstantesConversorCaracteresHtml.const_EquivalenteHtmlQuotation);

      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "\" value=\"" + pContenido + "\"");
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, longitudPermitida_local + pPlaceHolder + pEventos + " \n>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
      longitudPermitida_local = null;
    } 
    return cajaTexto_local;
  }
  public String insertarCajaTexto(Campo pCampo, String pContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTexto_local = "";
    String longitudPermitida_local = null;
    String estilo_local = null;
    int ancho_local = 0;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    
    try {
      ancho_local = pCampo.getEstiloCampo().getAnchoControl() - pCampo.getEstiloCampo().getMargenIzquierdaControl();
      if (pCampo.esTipoDatoFecha()) {
        ancho_local -= 20;
      }
      if (pCampo.getFormatoCampo().getLongitudCampo() != -1) {
        longitudPermitida_local = " maxlength=\"" + pCampo.getFormatoCampo().getLongitudCampo() + "\" ";
      }
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display: none;";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #9FCC9F; vertical-align:middle;";
      } 
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "<input type=\"text\" style=\"" + estilo_local);
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "width:" + ancho_local + "px\"");
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, " id=\"" + pCampo.getNombreCampo() + "\" name=\"" + pCampo.getNombreCampo());
      
      pContenido = getConversorCaracteresHtml().getCadenaHtml(pContenido);
      pContenido = mc.reemplazarCadena(pContenido, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterQuotation),
              ConstantesConversorCaracteresHtml.const_EquivalenteHtmlQuotation);
      
      pContenido = mc.reemplazarCadena(pContenido, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterQuotationSingle),
    		  String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterQuotationSingleCustom));

      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "\" value=\"" + pContenido + "\"");
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, longitudPermitida_local + pEventos + " \n>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
      longitudPermitida_local = null;
    } 
    return cajaTexto_local;
  }
  public String insertarCajaTextoContrasena(String pNombre, String pValorCampo, String pEventos, boolean pEsCampoOculto, String pPlaceHolder) {
    String cajaTextoContrasena_local = "";
    String estilo_local = null;

    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoContrasena_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoContrasena_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoContrasena_local;
    }
    
    
    try {
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display: none;";
      }
      
      if(pPlaceHolder != ConstantesGeneral.VALOR_NULO && !pPlaceHolder.isEmpty())
      {
    	  pPlaceHolder = " placeholder=\"" + pPlaceHolder + "\" ";
      }
      cajaTextoContrasena_local = mc.concatenarCadena(cajaTextoContrasena_local, "<input type=\"password\" style=\"" + estilo_local);
      cajaTextoContrasena_local = mc.concatenarCadena(cajaTextoContrasena_local, "width:" + getAnchoCampos() + "px\" maxlength=\"" + ' ');
      
      cajaTextoContrasena_local = mc.concatenarCadena(cajaTextoContrasena_local, "\" id=\"" + pNombre + "\" name=\"" + pNombre);
      cajaTextoContrasena_local = mc.concatenarCadena(cajaTextoContrasena_local, "\" value=\"" + pValorCampo);
     
      cajaTextoContrasena_local = mc.concatenarCadena(cajaTextoContrasena_local, "\" " + pPlaceHolder + pEventos + " \n>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    return cajaTextoContrasena_local;
  }
  public String insertarCajaTextoHora(String pNombre, String pContenido, String pEvento, boolean pHabilitado, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTextoHora_local = "";
    String habilitado_local = null;
    String estilo_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoHora_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoHora_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoHora_local;
    }
    
    try {
      if (!pHabilitado) {
        habilitado_local = " disabled=true ";
      } else {
        habilitado_local = "";
      } 
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display: none;";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #9FCC9F; vertical-align:middle;";
      } 
      
      cajaTextoHora_local = mc.concatenarCadena(cajaTextoHora_local, "<input type=\"text\" style=\"" + estilo_local + "width:" + getAnchoCampos() + "px\"");
      
      cajaTextoHora_local = mc.concatenarCadena(cajaTextoHora_local, "id=\"" + pNombre + "\" name=\"" + pNombre + "\" ");
      cajaTextoHora_local = mc.concatenarCadena(cajaTextoHora_local, "maxlength=\"8\" ");
      cajaTextoHora_local = mc.concatenarCadena(cajaTextoHora_local, "\" value=\"" + getConversorCaracteresHtml().getCadenaHtml(pContenido) + "\" ");
      
      cajaTextoHora_local = mc.concatenarCadena(cajaTextoHora_local, "onkeypress=\"return esValorHora(event);\" ");
      cajaTextoHora_local = mc.concatenarCadena(cajaTextoHora_local, habilitado_local + pEvento + " >");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
      habilitado_local = null;
    } 
    return cajaTextoHora_local;
  }
  public String insertarCajaTextoHora(Campo pCampo, String pContenido, String pEvento, boolean pHabilitado, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTextoHora_local = "";
    String habilitado_local = null;
    String evento_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoHora_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoHora_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoHora_local;
    }
    
    try {
      evento_local = " onkeypress=\"return esValorHora(event);\" ";
      if (!pHabilitado) {
        habilitado_local = " disabled=true ";
      } else {
        habilitado_local = "";
      } 
      pCampo.getFormatoCampo().setLongitudCampo(8);
      cajaTextoHora_local = insertarCajaTexto(pCampo, pContenido, evento_local + habilitado_local + pEvento, pEsCampoOculto, pEsCampoCalculado);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      habilitado_local = null;
      evento_local = null;
    } 
    return cajaTextoHora_local;
  }
  public String insertarCajaTextoNumeroEntero(String pNombre, String pContenido, int pLongitud, String pEvento, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTextoNumeroEntero_local = "";
    String eventos_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroEntero_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroEntero_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroEntero_local;
    }
    
    try {
      eventos_local = "onKeyPress=\"return esNumeroEnteroDigitacion(event);\"";
      if (pSoloPositivo) {
        eventos_local = "onKeyPress=\"return esNumeroEnteroSoloPositivoDigitacion(event);\"";
      }
      if (pLongitud < 1 || pLongitud > 10) {
        pLongitud = 10;
      }
      cajaTextoNumeroEntero_local = insertarCajaTexto(pNombre, pContenido, pEvento + eventos_local, pLongitud, pEsCampoOculto, pEsCampoCalculado, null);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      eventos_local = null;
    } 
    return cajaTextoNumeroEntero_local;
  }
  public String insertarCajaTextoNumeroEntero(Campo pCampo, String pContenido, String pEvento, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTextoNumeroEntero_local = "";
    String eventos_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroEntero_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroEntero_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroEntero_local;
    }
    
    try {
      eventos_local = " onKeyPress=\"return esNumeroEnteroDigitacion(event);\" onpaste=\"esNumeroEnteroDigitacionPaste(event, " + pSoloPositivo + ");\" ondrop=\"esNumeroEnteroDigitacionDrop(event, " + pSoloPositivo + ");\" ";
      if (pSoloPositivo) {
        eventos_local = " onKeyPress=\"return esNumeroEnteroSoloPositivoDigitacion(event);\" onpaste=\"esNumeroEnteroDigitacionPaste(event, " + pSoloPositivo + ");\" ondrop=\"esNumeroEnteroDigitacionDrop(event, " + pSoloPositivo + ");\" ";
      }
      int longitudCampo = pCampo.getFormatoCampo().getLongitudCampo();
      if (longitudCampo < 1 || longitudCampo > 10)
      {
        pCampo.getFormatoCampo().setLongitudCampo(10);
      }
      cajaTextoNumeroEntero_local = insertarCajaTexto(pCampo, pContenido, pEvento + eventos_local, pEsCampoOculto, pEsCampoCalculado);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      eventos_local = null;
    } 
    return cajaTextoNumeroEntero_local;
  }
  public String insertarCajaTextoNumeroReal(String pNombre, String pContenido, String pEvento, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTextoNumeroReal_local = "";
    String eventos_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroReal_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroReal_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroReal_local;
    }
    
    try {
      eventos_local = "onKeyPress=\"return esNumeroRealDigitacion(event);\"";
      if (pSoloPositivo) {
        eventos_local = "onKeyPress=\"return esNumeroRealSoloPositivoDigitacion(event);\"";
      }
      cajaTextoNumeroReal_local = insertarCajaTexto(pNombre, pContenido, pEvento + eventos_local, 50, pEsCampoOculto, pEsCampoCalculado, null);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      eventos_local = null;
    } 
    return cajaTextoNumeroReal_local;
  }
  public String insertarCajaTextoNumeroReal(Campo pCampo, String pContenido, String pEvento, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaTextoNumeroReal_local = "";
    String eventos_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroReal_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroReal_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoNumeroReal_local;
    }
    
    try {
      eventos_local = " onKeyPress=\"return esNumeroRealDigitacion(event);\" onpaste=\"esNumeroRealDigitacionPaste(event, " + pSoloPositivo + ");\" ondrop=\"esNumeroRealDigitacionDrop(event, " + pSoloPositivo + ");\" ";
      if (pSoloPositivo) {
        eventos_local = " onKeyPress=\"return esNumeroRealSoloPositivoDigitacion(event);\" onpaste=\"esNumeroRealDigitacionPaste(event, " + pSoloPositivo + "); \" ondrop=\"esNumeroRealDigitacionDrop(event, " + pSoloPositivo + ");\" ";
      }
      cajaTextoNumeroReal_local = insertarCajaTexto(pCampo, pContenido, pEvento + eventos_local, pEsCampoOculto, pEsCampoCalculado);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      eventos_local = null;
    } 
    return cajaTextoNumeroReal_local;
  }
  public String insertarCajaTextoArchivo(Campo pCampo, String pEventos, boolean pEsCampoOculto) {
    String cajaTexto_local = "";
    String estilo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaTexto_local;
    }
    
    try {
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display:none; ";
      }
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "<input type=\"file\" style=\"" + estilo_local);
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, "width:" + (pCampo.getEstiloCampo().getAnchoControl() - pCampo.getEstiloCampo().getMargenIzquierdaControl()) + "px\" id=\"" + pCampo.getNombreCampo() + "\" name=\"" + pCampo.getNombreCampo() + "\" ");
      
      cajaTexto_local = mc.concatenarCadena(cajaTexto_local, pEventos + " \n>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    
    return cajaTexto_local;
  }
  public String insertarCampoTextoArchivo(Campo pCampo, String pEventos, boolean pEsCampoOculto) {
    String campoTexto_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    
    try {
      if (!pCampo.ocultarEtiquetaControlExaminar()) {
        if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
          campoTexto_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
        }
        if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
          campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaTextoArchivo(pCampo, pEventos, pEsCampoOculto)));
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoTexto_local;
  }
  public String insertarParrafo(String pContenido, String pAlineacion) {
    String parrafo_local = "";
    
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return parrafo_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return parrafo_local;
    }
    try {
      parrafo_local = mc.concatenarCadena(parrafo_local, "<p aling=" + pAlineacion + ">");
      parrafo_local = mc.concatenarCadena(parrafo_local, pContenido + "</p>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return parrafo_local;
  }
  public String insertarListaDesplegable(String pNombre, ListaGeneral pListaContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String listaDesplegable_local = "";
    String item_local = null;
    String estilo_local = null;
    Iterator iterator_local = null;
    ItemLista itemLista_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return listaDesplegable_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return listaDesplegable_local;
    }
    
    try {
      estilo_local = "background-color : #E2FFFF; font-family: tahoma; font-size: 11px; ";
      if (pEsCampoOculto) {
        estilo_local = "background-color : #E2FFFF; font-family: tahoma; font-size: 11px; display: none; ";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "background-color : #9FCC9F; font-family: tahoma; font-size: 11px; ";
      } 
      
      listaDesplegable_local = "<select style=\"" + estilo_local + "width:" + getAnchoCampos() + "px\" id=\"" + pNombre + "\" name=\"" + pNombre + "\"" + pEventos + " >\n";
      
      if (pListaContenido != ConstantesGeneral.VALOR_NULO) {
        iterator_local = pListaContenido.iterator();
        while (iterator_local.hasNext()) {
          itemLista_local = (ItemLista)iterator_local.next();
          if (itemLista_local.esSeleccionado()) {
            item_local = "<option  selected value=\"" + itemLista_local.getValorItem() + "\">" + getConversorCaracteresHtml().getCadenaHtml(itemLista_local.getNombreItem()) + "</option>\n";
          } else {
            
            item_local = "<option  value=\"" + itemLista_local.getValorItem() + "\">" + getConversorCaracteresHtml().getCadenaHtml(itemLista_local.getNombreItem()) + "</option>\n";
          } 
          
          listaDesplegable_local = mc.concatenarCadena(listaDesplegable_local, item_local);
        } 
      } 
      listaDesplegable_local = mc.concatenarCadena(listaDesplegable_local, "</select>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      item_local = null;
      estilo_local = null;
      iterator_local = null;
      itemLista_local = null;
    } 
    return listaDesplegable_local;
  }
  public String insertarListaDesplegable(Campo pCampo, ListaGeneral pListaContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String listaDesplegable_local = "";
    String item_local = null;
    Iterator iterator_local = null;
    ItemLista itemLista_local = null;
    String estilo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaDesplegable_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return listaDesplegable_local;
    }
    
    try {
      estilo_local = "background-color : #E2FFFF; font-family: tahoma; font-size: 11px; ";
      if (pEsCampoOculto) {
        estilo_local = "background-color : #E2FFFF; font-family: tahoma; font-size: 11px; display: none; ";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "background-color : #9FCC9F; font-family: tahoma; font-size: 11px; ";
      } 
      
      listaDesplegable_local = "<select style=\"" + estilo_local;
      listaDesplegable_local = mc.concatenarCadena(listaDesplegable_local, "width:" + (pCampo.getEstiloCampo().getAnchoControl() - pCampo.getEstiloCampo().getMargenIzquierdaControl()) + "px\" ");
      
      listaDesplegable_local = mc.concatenarCadena(listaDesplegable_local, "id=\"" + pCampo.getNombreCampo() + "\" name=\"" + pCampo.getNombreCampo() + "\" " + pEventos + ">\n");
      
      if (pListaContenido != ConstantesGeneral.VALOR_NULO) {
        iterator_local = pListaContenido.iterator();
        while (iterator_local.hasNext()) {
          itemLista_local = (ItemLista)iterator_local.next();
          if (itemLista_local.esSeleccionado()) {
            item_local = "<option  selected value=\"" + itemLista_local.getValorItem() + "\">" + getConversorCaracteresHtml().getCadenaHtml(itemLista_local.getNombreItem()) + "</option>\n";
          } else {
            
            item_local = "<option  value=\"" + itemLista_local.getValorItem() + "\">" + getConversorCaracteresHtml().getCadenaHtml(itemLista_local.getNombreItem()) + "</option>\n";
          } 
          
          listaDesplegable_local = mc.concatenarCadena(listaDesplegable_local, item_local);
        } 
      } 
      listaDesplegable_local = mc.concatenarCadena(listaDesplegable_local, "</select>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      item_local = null;
      iterator_local = null;
      itemLista_local = null;
      estilo_local = null;
    } 
    return listaDesplegable_local;
  }
  public String insertarCajaSeleccion(String pNombre, String pContenido, boolean pSeleccion, String pEventos, boolean pEsCampoOculto) {
    String cajaSeleccion_local = "";
    String nombreEstilo_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaSeleccion_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaSeleccion_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaSeleccion_local;
    }
    try {
      nombreEstilo_local = "checkbox";
      if (pEsCampoOculto) {
        nombreEstilo_local = "checkboxOculto";
      }
      cajaSeleccion_local = "<input class=\"" + nombreEstilo_local + "\" type=\"checkbox\"  ";
      if (pSeleccion) {
        cajaSeleccion_local = cajaSeleccion_local + " checked ";
      }
      cajaSeleccion_local = cajaSeleccion_local + "name=\"" + pNombre + "\" id=\"" + pNombre + "\" " + " value=\"true\" " + pEventos + " /> " + getConversorCaracteresHtml().getCadenaHtml(mc.darFormatoTitulo(pContenido)) + "\n";
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreEstilo_local = null;
    } 
    
    return cajaSeleccion_local;
  }
  public String insertarRadioSeleccion(String pGrupo, String pNombre, String pContenido, String pValor, boolean pSeleccion, String pEventos) {
    String opcionSeleccion_local = "";
    
    if (pGrupo == ConstantesGeneral.VALOR_NULO) {
      return opcionSeleccion_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return opcionSeleccion_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return opcionSeleccion_local;
    }
    if (pValor == ConstantesGeneral.VALOR_NULO) {
      return opcionSeleccion_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return opcionSeleccion_local;
    }
    try {
      opcionSeleccion_local = "<input type=\"radio\" class=\"radio\" ";
      if (pSeleccion) {
        opcionSeleccion_local = opcionSeleccion_local + " checked ";
      }
      opcionSeleccion_local = opcionSeleccion_local + " name=\"" + pGrupo + "\" id=\"" + pNombre + "\" value=\"" + pValor + "\" " + pEventos + " /> " + getConversorCaracteresHtml().getCadenaHtml(pContenido) + "\n";
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return opcionSeleccion_local;
  }
  public String insertarEntradaImagen(String pNombre, String pUbicacion, String pDescripcion, String pEventos, boolean pWithCustomImages) {
    String imagen_local = "";
    String nombre_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pUbicacion == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return imagen_local;
    }
    try {
      nombre_local = "submit" + pNombre;
      imagen_local = "<input type=\"image\" id=\"" + nombre_local + "\" name=\"" + nombre_local + "\" src=\"" + pUbicacion + "\" " + "alt=\"" + pDescripcion + "\" title=\"" + pDescripcion + "\"" + pEventos;
      if(pWithCustomImages)
      {
    	  imagen_local += " data-customimg=\"true\"";
      }

      imagen_local+= " />\n";
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombre_local = null;
    } 
    return imagen_local;
  }
  public String insertarCampoOculto(String pNombre, String pValor) {
    String campoOculto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoOculto_local;
    }
    if (pValor == ConstantesGeneral.VALOR_NULO) {
      return campoOculto_local;
    }
    try {
      campoOculto_local = "<input type=\"hidden\" id=\"" + pNombre + "\"   name=\"" + pNombre + "\" value=\"" + pValor + "\">\n";
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoOculto_local;
  }
  public String crearCeldaTexto(String pNombre, String pContenido, String pEventos, String pAncho, int pLongitud, String pAlineacion, boolean pEsCampoOculto, boolean pEsCampoCalculado, String pPlaceHolder) {
    String celdaTexto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    try {
      celdaTexto_local = crearCeldaTransparente(insertarCajaTexto(pNombre, pContenido, pEventos, pLongitud, pEsCampoOculto, pEsCampoCalculado, pPlaceHolder), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  private String crearCeldaTextoContrasena(String pNombre, String pValorCampo, String pEventos, String pAlineacion, String pAncho, boolean pEsCampoOculto, String pPlaceHolder) {
    String celdaTexto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    
    try {
      celdaTexto_local = crearCeldaTransparente(insertarCajaTextoContrasena(pNombre, pValorCampo, pEventos, pEsCampoOculto, pPlaceHolder), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  private String crearCeldaTextoFecha(String pNombre, String pContenido, String pFormatoFecha, String pEvento, String pAncho, String pAlineacion, boolean pEsCampoCalculado, boolean pEsCampoOculto, boolean pEsCampoHabilitado) {
    String celdaTexto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    
    try {
      celdaTexto_local = crearCeldaTransparente(insertarCajaTextoFecha(pNombre, pContenido, pFormatoFecha, pEvento, pEsCampoCalculado, pEsCampoOculto, pEsCampoHabilitado), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  public String insertarCajaTextoFecha(String pNombre, String pContenido, String pFormatoFecha, String pEvento, boolean pEsCampoCalculado, boolean pEsCampoOculto, boolean pEsCampoHabilitado) {
    String cajaTextoFecha_local = "";
    int ancho_local = -1;
    String nombreBoton_local = null;
    String contenido_local = null;
    String anchoCampos_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    
    try {
      contenido_local = pContenido;
      if (mc.sonCadenasIgualesIgnorarMayusculas(contenido_local, "null")) {
        contenido_local = "";
      }
      
      if (pEsCampoOculto) {
        cajaTextoFecha_local = insertarCajaTexto(pNombre, contenido_local, " readonly " + pEvento, 10, pEsCampoOculto, pEsCampoCalculado, null);
      } else {
        
        anchoCampos_local = getAnchoCampos();
        ancho_local = Integer.parseInt(anchoCampos_local) - 20;
        setAnchoCampos(String.valueOf(ancho_local));
        nombreBoton_local = mc.concatenarCadena("btn", pNombre);
        if (pEsCampoCalculado || !pEsCampoHabilitado) {
          cajaTextoFecha_local = insertarCajaTexto(pNombre, contenido_local, " readonly " + pEvento, 10, pEsCampoOculto, pEsCampoCalculado, null) + "<input type=\"button\" class=\"Boton\" id=\"" + nombreBoton_local + "\" name=\"" + nombreBoton_local + "\" value=\"...\" onclick=\"return showCalendar('" + pNombre + "', '" + pFormatoFecha + "', '24');\"" + " disabled " + ">\n";
        
        }
        else {
          
          cajaTextoFecha_local = insertarCajaTexto(pNombre, contenido_local, " readonly " + pEvento, 10, pEsCampoOculto, pEsCampoCalculado, null) + "<input type=\"button\" class=\"Boton\" id=\"" + nombreBoton_local + "\" name=\"" + nombreBoton_local + "\" value=\"...\" onclick=\"return showCalendar('" + pNombre + "', '" + pFormatoFecha + "', '24');\"" + ">\n";
        } 
        
        setAnchoCampos(anchoCampos_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      contenido_local = null;
      nombreBoton_local = null;
      anchoCampos_local = null;
    } 
    return cajaTextoFecha_local;
  }
  public String insertarCajaTextoFecha(Campo pCampo, String pContenido, String pFormatoFecha, String pEvento, boolean pEsCampoCalculado, boolean pEsCampoOculto, boolean pEsCampoHabilitado) {
    String cajaTextoFecha_local = "";
    String nombreBoton_local = null;
    String contenido_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return cajaTextoFecha_local;
    }
    
    try {
      contenido_local = pContenido;
      if (mc.sonCadenasIgualesIgnorarMayusculas(contenido_local, "null")) {
        contenido_local = "";
      }
      
      if (pEsCampoOculto) {
        cajaTextoFecha_local = insertarCajaTexto(pCampo, contenido_local, " readonly " + pEvento, pEsCampoOculto, pEsCampoCalculado);
      } else {
        
        nombreBoton_local = mc.concatenarCadena("btn", pCampo.getNombreCampo());
        if (pEsCampoCalculado || !pEsCampoHabilitado) {
          cajaTextoFecha_local = insertarCajaTexto(pCampo, contenido_local, " class='date' readonly " + pEvento, pEsCampoOculto, pEsCampoCalculado) + "<input type=\"button\" class=\"Boton\" id=\"" + nombreBoton_local + "\" name=\"" + nombreBoton_local + "\" value=\"...\" onclick=\"return showCalendar('" + pCampo.getNombreCampo() + "', '" + pFormatoFecha + "', '24');\"" + " disabled " + ">\n";
        
        }
        else {
          
          cajaTextoFecha_local = insertarCajaTexto(pCampo, contenido_local, " class='date' readonly " + pEvento, pEsCampoOculto, pEsCampoCalculado) + "<input type=\"button\" class=\"Boton\" id=\"" + nombreBoton_local + "\" name=\"" + nombreBoton_local + "\" value=\"...\" onclick=\"return showCalendar('" + pCampo.getNombreCampo() + "', '" + pFormatoFecha + "', '24');\"" + ">\n";
        }
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      contenido_local = null;
      nombreBoton_local = null;
    } 
    return cajaTextoFecha_local;
  }
  private String crearCeldaTextoHora(String pNombre, String pContenido, String pEvento, String pAncho, String pAlineacion, boolean pHabilitado, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String celdaTexto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    try {
      celdaTexto_local = crearCeldaTransparente(insertarCajaTextoHora(pNombre, pContenido, pEvento, pHabilitado, pEsCampoOculto, pEsCampoCalculado), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  private String crearCeldaTextoNumeroEntero(String pNombre, String pContenido, int pLongitud, String pEvento, String pAncho, String pAlineacion, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String celdaTexto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    try {
      celdaTexto_local = crearCeldaTransparente(insertarCajaTextoNumeroEntero(pNombre, pContenido, pLongitud, pEvento, pSoloPositivo, pEsCampoOculto, pEsCampoCalculado), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  private String crearCeldaTextoNumeroReal(String pNombre, String pContenido, String pEvento, String pAncho, String pAlineacion, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String celdaTexto_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    
    try {
      celdaTexto_local = crearCeldaTransparente(insertarCajaTextoNumeroReal(pNombre, pContenido, pEvento, pSoloPositivo, pEsCampoOculto, pEsCampoCalculado), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  private String crearCeldaListaDesplegable(String pNombre, ListaGeneral pListaContenido, String pEventos, String pAncho, String pAlineacion, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String celdaLista_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaLista_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celdaLista_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaLista_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaLista_local;
    }
    
    try {
      celdaLista_local = crearCeldaTransparente(insertarListaDesplegable(pNombre, pListaContenido, pEventos, pEsCampoOculto, pEsCampoCalculado), pAlineacion, pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaLista_local;
  }
  private String crearCeldaSeleccion(String pNombre, String pContenido, boolean pSeleccion, String pEventos, int pNumeroColumnas, String pAlineacion, boolean pEsCampoOculto) {
    String celda_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      if (pEsCampoOculto) {
        celda_local = crearCeldaCombinadaColumnasTransparente(insertarCajaSeleccion(pNombre, "", pSeleccion, pEventos, pEsCampoOculto), pNumeroColumnas, pAlineacion);
      } else {
        
        celda_local = crearCeldaCombinadaColumnasEtiqueta(insertarCajaSeleccion(pNombre, pContenido, pSeleccion, pEventos, pEsCampoOculto), pNumeroColumnas, pAlineacion);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String insertarCampo(String pEtiqueta, String pContenido, String pAlineacion) {
    String campo_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    try {
      campo_local = mc.concatenarCadena(campo_local, abrirFilaTabla());
      campo_local = mc.concatenarCadena(campo_local, crearCeldaEtiqueta(pEtiqueta));
      campo_local = mc.concatenarCadena(campo_local, crearCelda(pContenido, pAlineacion, ""));
      campo_local = mc.concatenarCadena(campo_local, cerrarFilaTabla());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campo_local;
  }
  public String insertarCampoTexto(String pEtiqueta, String pNombre, String pContenido, String pEventos, int pLongitud, String pAlineacion, boolean pEsCampoOculto, boolean pEsCampoCalculado, String pPlaceHolder) {
    String campoTexto_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    
    try {
      campoTexto_local = mc.concatenarCadena(campoTexto_local, abrirFilaTabla());
      if (pEsCampoOculto) {
        campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaEtiquetaTransparente());
      } else {
        campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaEtiqueta(pEtiqueta));
      } 
      campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaTexto(pNombre, pContenido, pEventos, getAnchoCampos(), pLongitud, pAlineacion, pEsCampoOculto, pEsCampoCalculado, pPlaceHolder));
      
      campoTexto_local = mc.concatenarCadena(campoTexto_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoTexto_local;
  }
  public String insertarCampoTexto(Campo pCampo, String pContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoTexto_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    
    try {
    	
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoTexto_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      //coco
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        String eventos_local = " onKeyPress=\"return replaceSingleQuote(event);\" onPaste=\"return replaceSingleQuotePaste(event);\" onDrop=\"return replaceSingleQuotePaste(event);\" ";
        campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaTexto(pCampo, pContenido, pEventos + eventos_local, pEsCampoOculto, pEsCampoCalculado)));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoTexto_local;
  }
  public String insertarCampoTextoConsulta(String pEtiqueta, String pNombre, String pContenido, int pLongitud, String pAlineacion) {
    String campoTexto_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    try {
      campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaEtiqueta(pEtiqueta));
      campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaTexto(pNombre, pContenido, "", getAnchoCampos(), pLongitud, pAlineacion, false, false, null));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoTexto_local;
  }
  public String insertarCampoOrdenadoPorConsulta(String pEtiquetaCampo) {
    String campoTexto_local = "";
    
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO) {
      return campoTexto_local;
    }
    
    try {
      campoTexto_local = mc.concatenarCadena(campoTexto_local, crearCeldaEtiqueta(pEtiquetaCampo));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoTexto_local;
  }
  public String insertarCampoNuloConsulta() {
    String campoNuloConsulta_local = "";
    try {
      campoNuloConsulta_local = mc.concatenarCadena(campoNuloConsulta_local, crearCeldaEtiquetaTransparente());
      campoNuloConsulta_local = mc.concatenarCadena(campoNuloConsulta_local, crearCeldaAnchoTransparente("", "left", "200"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNuloConsulta_local;
  }
  public String insertarCampoContrasena(String pEtiqueta, String pNombre, String pValorCampo, String pEventos, String pAlineacion, boolean pEsCampoOculto, String pPlaceHolder) {
    String campoContrasena_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoContrasena_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoContrasena_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return campoContrasena_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoContrasena_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoContrasena_local;
    }
    
    try {
      campoContrasena_local = mc.concatenarCadena(campoContrasena_local, abrirFilaTabla());
      campoContrasena_local = mc.concatenarCadena(campoContrasena_local, crearCeldaEtiqueta(pEtiqueta));
      campoContrasena_local = mc.concatenarCadena(campoContrasena_local, crearCeldaTextoContrasena(pNombre, pValorCampo, pEventos, pAlineacion, getAnchoCampos(), pEsCampoOculto, pPlaceHolder));
      
      campoContrasena_local = mc.concatenarCadena(campoContrasena_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoContrasena_local;
  }
  public String insertarCampoFecha(String pEtiqueta, String pNombre, String pContenido, String pFormatoFecha, String pEvento, String pAlineacion, boolean pHabilitado, boolean pEsCampoCalculado, boolean pEsCampoOculto) {
    String campoFecha_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    
    try {
      if (!pHabilitado && pEsCampoCalculado) {
        pContenido = "";
      }
      campoFecha_local = mc.concatenarCadena(campoFecha_local, abrirFilaTabla());
      if (pEsCampoOculto) {
        campoFecha_local = mc.concatenarCadena(campoFecha_local, crearCeldaEtiquetaTransparente());
      } else {
        campoFecha_local = mc.concatenarCadena(campoFecha_local, crearCeldaEtiqueta(pEtiqueta));
      } 
      campoFecha_local = mc.concatenarCadena(campoFecha_local, crearCeldaTextoFecha(pNombre, pContenido, pFormatoFecha, pEvento, getAnchoCampos(), pAlineacion, pEsCampoCalculado, pEsCampoOculto, pHabilitado));
      
      campoFecha_local = mc.concatenarCadena(campoFecha_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoFecha_local;
  }
  public String insertarCampoFecha(Campo pCampo, String pContenido, String pFormatoFecha, String pEvento, boolean pEsCampoHabilitado, boolean pEsCampoCalculado, boolean pEsCampoOculto) {
    String campoFecha_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    
    try {
      if (!pEsCampoHabilitado && pEsCampoCalculado) {
        pContenido = "";
      }
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoFecha_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        campoFecha_local = mc.concatenarCadena(campoFecha_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaTextoFecha(pCampo, pContenido, pFormatoFecha, pEvento, pEsCampoCalculado, pEsCampoOculto, pEsCampoHabilitado)));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoFecha_local;
  }
  public String insertarCampoFechaConsulta(String pEtiqueta, String pNombre, String pContenido, String pFormatoFecha, String pAlineacion) {
    String campoFecha_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pFormatoFecha == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoFecha_local;
    }
    
    try {
      campoFecha_local = mc.concatenarCadena(campoFecha_local, crearCeldaEtiqueta(pEtiqueta));
      campoFecha_local = mc.concatenarCadena(campoFecha_local, crearCeldaTextoFecha(pNombre, pContenido, pFormatoFecha, "", getAnchoCampos(), pAlineacion, false, false, true));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoFecha_local;
  }
  public String insertarCampoHora(String pEtiqueta, String pNombre, String pContenido, String pEvento, String pAlineacion, boolean pHabilitado, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoHora_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    
    try {
      if (!pHabilitado && pEsCampoCalculado) {
        pContenido = "";
      }
      campoHora_local = mc.concatenarCadena(campoHora_local, abrirFilaTabla());
      if (pEsCampoOculto) {
        campoHora_local = mc.concatenarCadena(campoHora_local, crearCeldaEtiquetaTransparente());
      } else {
        campoHora_local = mc.concatenarCadena(campoHora_local, crearCeldaEtiqueta(pEtiqueta));
      } 
      campoHora_local = mc.concatenarCadena(campoHora_local, crearCeldaTextoHora(pNombre, pContenido, pEvento, getAnchoCampos(), pAlineacion, pHabilitado, pEsCampoOculto, pEsCampoCalculado));
      
      campoHora_local = mc.concatenarCadena(campoHora_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoHora_local;
  }
  public String insertarCampoHora(Campo pCampo, String pContenido, String pEvento, boolean pEsCampoHabilitado, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoHora_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    
    try {
      if (!pEsCampoHabilitado && pEsCampoCalculado) {
        pContenido = "";
      }
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoHora_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        campoHora_local = mc.concatenarCadena(campoHora_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaTextoHora(pCampo, pContenido, pEvento, pEsCampoHabilitado, pEsCampoOculto, pEsCampoCalculado)));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoHora_local;
  }
  public String insertarCampoHoraConsulta(String pEtiqueta, String pNombre, String pContenido, String pAlineacion) {
    String campoHora_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoHora_local;
    }
    
    try {
      campoHora_local = mc.concatenarCadena(campoHora_local, crearCeldaEtiqueta(pEtiqueta));
      campoHora_local = mc.concatenarCadena(campoHora_local, crearCeldaTextoHora(pNombre, pContenido, "", getAnchoCampos(), pAlineacion, true, false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoHora_local;
  }
  public String insertarCampoNumeroEntero(String pEtiqueta, String pNombre, int pLongitud, String pContenido, String pEvento, String pAlineacion, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoNumeroEntero_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    
    try {
      campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, abrirFilaTabla());
      if (pEsCampoOculto) {
        campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, crearCeldaEtiquetaTransparente());
      } else {
        campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, crearCeldaEtiqueta(pEtiqueta));
      } 
      campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, crearCeldaTextoNumeroEntero(pNombre, pContenido, pLongitud, pEvento, getAnchoCampos(), pAlineacion, pSoloPositivo, pEsCampoOculto, pEsCampoCalculado));
      
      campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNumeroEntero_local;
  }
  public String insertarCampoNumeroEntero(Campo pCampo, String pContenido, String pEvento, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoNumeroEntero_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    
    try {
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoNumeroEntero_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaTextoNumeroEntero(pCampo, pContenido, pEvento, pSoloPositivo, pEsCampoOculto, pEsCampoCalculado)));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNumeroEntero_local;
  }
  public String insertarCampoNumeroEnteroConsulta(String pEtiqueta, String pNombre, int pLongitud, String pContenido, String pAlineacion, boolean pSoloPositivo) {
    String campoNumeroEntero_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroEntero_local;
    }
    try {
      campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, crearCeldaEtiqueta(pEtiqueta));
      campoNumeroEntero_local = mc.concatenarCadena(campoNumeroEntero_local, crearCeldaTextoNumeroEntero(pNombre, pContenido, pLongitud, "", getAnchoCampos(), pAlineacion, pSoloPositivo, false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNumeroEntero_local;
  }
  public String insertarCampoNumeroReal(String pEtiqueta, String pNombre, String pContenido, String pEvento, String pAlineacion, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoNumeroReal_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    
    try {
      campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, abrirFilaTabla());
      if (pEsCampoOculto) {
        campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, crearCeldaEtiquetaTransparente());
      } else {
        campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, crearCeldaEtiqueta(pEtiqueta));
      } 
      campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, crearCeldaTextoNumeroReal(pNombre, pContenido, pEvento, getAnchoCampos(), pAlineacion, pSoloPositivo, pEsCampoOculto, pEsCampoCalculado));
      
      campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, cerrarFilaTabla());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNumeroReal_local;
  }
  public String insertarCampoNumeroReal(Campo pCampo, String pContenido, String pEvento, boolean pSoloPositivo, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoNumeroReal_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    
    try {
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoNumeroReal_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaTextoNumeroReal(pCampo, pContenido, pEvento, pSoloPositivo, pEsCampoOculto, pEsCampoCalculado)));
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNumeroReal_local;
  }
  public String insertarCampoNumeroRealConsulta(String pEtiqueta, String pNombre, String pContenido, String pAlineacion, boolean pSoloPositivo) {
    String campoNumeroReal_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoNumeroReal_local;
    }
    
    try {
      campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, crearCeldaEtiqueta(pEtiqueta));
      campoNumeroReal_local = mc.concatenarCadena(campoNumeroReal_local, crearCeldaTextoNumeroReal(pNombre, pContenido, "", getAnchoCampos(), pAlineacion, pSoloPositivo, false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoNumeroReal_local;
  }
  public String insertarCampoListaDesplegable(Campo pCampo, ListaGeneral pListaContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoListaDesplegable_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    
    try {
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoListaDesplegable_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        campoListaDesplegable_local = mc.concatenarCadena(campoListaDesplegable_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarListaDesplegable(pCampo, pListaContenido, pEventos, pEsCampoOculto, pEsCampoCalculado)));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoListaDesplegable_local;
  }
  public String insertarCampoListaDesplegable(String pEtiquetaCampo, String pNombreCampo, ListaGeneral pListaContenido, String pEventos, String pAlineacion, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoListaDesplegable_local = "";
    
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    
    try {
      campoListaDesplegable_local = abrirFilaTabla();
      if (!pEsCampoOculto) {
        campoListaDesplegable_local = mc.concatenarCadena(campoListaDesplegable_local, crearCeldaEtiqueta(pEtiquetaCampo));
      }
      campoListaDesplegable_local = mc.concatenarCadena(campoListaDesplegable_local, crearCeldaListaDesplegable(pNombreCampo, pListaContenido, pEventos, getAnchoCampos(), pAlineacion, pEsCampoOculto, pEsCampoCalculado));
      
      campoListaDesplegable_local = mc.concatenarCadena(campoListaDesplegable_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoListaDesplegable_local;
  }
  public String insertarCampoListaDesplegableConsulta(String pEtiqueta, String pNombre, ListaGeneral pListaContenido, String pAlineacion) {
    String campoListaDesplegable_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoListaDesplegable_local;
    }
    
    try {
      campoListaDesplegable_local = mc.concatenarCadena(campoListaDesplegable_local, crearCeldaEtiqueta(pEtiqueta));
      campoListaDesplegable_local = mc.concatenarCadena(campoListaDesplegable_local, crearCeldaListaDesplegable(pNombre, pListaContenido, "", getAnchoCampos(), pAlineacion, false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoListaDesplegable_local;
  }
  public String insertarCampoCajaSeleccion(String pNombre, String pContenido, boolean pSeleccion, String pEventos, int pNumeroColumnas, String pAlineacion, boolean pEsCampoOculto) {
    String campoCajaSeleccion_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    
    try {
      campoCajaSeleccion_local = mc.concatenarCadena(campoCajaSeleccion_local, abrirFilaTabla());
      campoCajaSeleccion_local = mc.concatenarCadena(campoCajaSeleccion_local, crearCeldaSeleccion(pNombre, pContenido, pSeleccion, pEventos, pNumeroColumnas, pAlineacion, pEsCampoOculto));
      
      campoCajaSeleccion_local = mc.concatenarCadena(campoCajaSeleccion_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoCajaSeleccion_local;
  }
  public String insertarCampoCajaSeleccionConsulta(String pNombre, String pContenido, boolean pSeleccion, int pNumeroColumnas, String pAlineacion) {
    String campoCajaSeleccion_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoCajaSeleccion_local;
    }
    try {
      campoCajaSeleccion_local = mc.concatenarCadena(campoCajaSeleccion_local, crearCeldaSeleccion(pNombre, pContenido, pSeleccion, "", pNumeroColumnas, pAlineacion, false));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoCajaSeleccion_local;
  }
  public String insertarCeldaBorrarRegistro(String pDestino, int pNivelesAnterioresDirectorio, boolean pEsColorGris, boolean pRealizarPregunta) {
    String celdaBorrar_local = "";
    String eventosBorrar_local = null;
    String contenidoCelda_local = null;
    
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaBorrar_local;
    }
    try {
      eventosBorrar_local = "";
      if (pRealizarPregunta) {
        eventosBorrar_local = " onClick=\"return preguntarEliminar();\" ";
      }
      contenidoCelda_local = insertarHipervinculoImagen(mc.complementarDirectorio(pNivelesAnterioresDirectorio) + "../imagenes/botones/" + "borrarregistro.gif", "Borrar Registro", pDestino, eventosBorrar_local, false, true);
      
      celdaBorrar_local = crearCeldaContenidoImagen(contenidoCelda_local, "center", String.valueOf(30), pEsColorGris);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      eventosBorrar_local = null;
      contenidoCelda_local = null;
    } 
    return celdaBorrar_local;
  }
  public String insertarCeldaBorrarOpcionConsulta(String pDestino) {
    String celdaBorrarOpcionConsulta_local = "";
    String contenidoCelda_local = null;
    
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaBorrarOpcionConsulta_local;
    }
    try {
      contenidoCelda_local = insertarHipervinculoImagen("../imagenes/botones/borraropcionconsulta.gif", "Borrar Opci\u00f3n Consulta", pDestino, "", false, false);
      
      celdaBorrarOpcionConsulta_local = crearCeldaTransparenteContenidoImagen(contenidoCelda_local, "center", String.valueOf(100));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      contenidoCelda_local = null;
    } 
    return celdaBorrarOpcionConsulta_local;
  }
  public String insertarCeldaDecrementarPosicion(String pDestino, boolean pHabilitada, boolean pEsColorGris) {
    String celdaDecrementarPosicion_local = "";
    String celdaDecrementar_local = null;
    
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaDecrementarPosicion_local;
    }
    try {
      celdaDecrementar_local = "decrementar.gif";
      if (!pHabilitada) {
        celdaDecrementar_local = "decrementar2.gif";
      }
      celdaDecrementarPosicion_local = crearCeldaContenidoImagen(insertarHipervinculoImagen("../imagenes/botones/" + celdaDecrementar_local, "Decrementar Posici\u00f3n", pDestino, "", false, false), "center", String.valueOf(30), pEsColorGris);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      celdaDecrementar_local = null;
    } 
    return celdaDecrementarPosicion_local;
  }
  public String insertarCeldaIncrementarPosicion(String pDestino, boolean pHabilitada, boolean pEsColorGris) {
    String celdaIncrementarPosicion_local = "";
    String celdaIncrementar_local = null;
    
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaIncrementarPosicion_local;
    }
    try {
      celdaIncrementar_local = "incrementar.gif";
      if (!pHabilitada) {
        celdaIncrementar_local = "incrementar2.gif";
      }
      celdaIncrementarPosicion_local = crearCeldaContenidoImagen(insertarHipervinculoImagen("../imagenes/botones/" + celdaIncrementar_local, "Incrementar Posici\u00f3n", pDestino, "", false, false), "center", String.valueOf(30), pEsColorGris);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      celdaIncrementar_local = null;
    } 
    return celdaIncrementarPosicion_local;
  }
  public String insertarCeldaVerDetalles(String pDestino, boolean pEsColorGris) {
    String celdaVerDetalles_local = "";
    
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaVerDetalles_local;
    }
    try {
      celdaVerDetalles_local = crearCeldaContenidoImagen(insertarHipervinculoImagen("../imagenes/botones/ver.gif", "Ver Detalles", pDestino, "", false, false), "right", String.valueOf(18), pEsColorGris);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaVerDetalles_local;
  }
  public String insertarCeldaVerDocumento(String pDestino, int pNivelesAnterioresDirectorio, boolean pEsColorGris) {
    String celdaVerDocumento_local = "";
    
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaVerDocumento_local;
    }
    try {
      celdaVerDocumento_local = crearCeldaContenidoImagen(insertarHipervinculoImagen(mc.complementarDirectorio(pNivelesAnterioresDirectorio) + "../imagenes/botones/" + "verdocumento.gif", "Ver Documento", mc.complementarDirectorio(pNivelesAnterioresDirectorio) + pDestino, "", false, false), "center", String.valueOf(57), pEsColorGris);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaVerDocumento_local;
  }
  public String insertarCampoTipoIdentificacion(String pEtiqueta, String pNombre, String pTipoIdentificacionSeleccionada, String pEvento) {
    String insertarLista_local = "";
    ListaGeneral listaTipoIdentificacion_local = null;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pTipoIdentificacionSeleccionada == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    try {
      listaTipoIdentificacion_local = ap.obtenerListaOpcionesTipoIdentificacion(pTipoIdentificacionSeleccionada);
      insertarLista_local = mc.concatenarCadena(insertarLista_local, insertarCampoListaDesplegable(pEtiqueta, pNombre, listaTipoIdentificacion_local, pEvento, "center", false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaTipoIdentificacion_local = null;
    } 
    return insertarLista_local;
  }
  public String insertarCampoTipoLicencia(String pEtiqueta, String pNombre, String pValorCampo, String pEvento) {
    String insertarLista_local = "";
    ListaGeneral listaTipoLicencia_local = null;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    
    try {
      listaTipoLicencia_local = ap.obtenerListaOpcionesTipoLicencia(pValorCampo);
      insertarLista_local = mc.concatenarCadena(insertarLista_local, insertarCampoListaDesplegable(pEtiqueta, pNombre, listaTipoLicencia_local, pEvento, "center", false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaTipoLicencia_local = null;
    } 
    return insertarLista_local;
  }
  public String insertarCampoGenero(String pEtiqueta, String pNombre, String pGeneroSeleccionado, String pEvento) {
    String insertarLista_local = "";
    ListaGeneral listaGenero_local = null;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pGeneroSeleccionado == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    try {
      listaGenero_local = ap.obtenerListaOpcionesGenero(pGeneroSeleccionado);
      insertarLista_local = mc.concatenarCadena(insertarLista_local, insertarCampoListaDesplegable(pEtiqueta, pNombre, listaGenero_local, pEvento, "center", false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaGenero_local = null;
    } 
    return insertarLista_local;
  }
  public String insertarCampoTipoHabilitacion(String pEtiqueta, String pNombre, int pTipoHabilitacionSeleccionado, String pEvento) {
    String insertarLista_local = "";
    ListaGeneral listaGeneral_local = null;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    try {
      listaGeneral_local = ap.obtenerListaOpcionesTipoHabilitacion(pTipoHabilitacionSeleccionado);
      insertarLista_local = mc.concatenarCadena(insertarLista_local, insertarCampoListaDesplegable(pEtiqueta, pNombre, listaGeneral_local, pEvento, "center", false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaGeneral_local = null;
    } 
    return insertarLista_local;
  }
  public String insertarCampoTipoCampoCalculado(String pEtiqueta, String pNombre, int pCampoCalculadoSeleccionado, String pEvento) {
    String insertarLista_local = "";
    ListaGeneral listaGeneral_local = null;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return insertarLista_local;
    }
    
    try {
      listaGeneral_local = ap.obtenerListaOpcionesTipoCampoCalculado(pCampoCalculadoSeleccionado);
      insertarLista_local = mc.concatenarCadena(insertarLista_local, insertarCampoListaDesplegable(pEtiqueta, pNombre, listaGeneral_local, pEvento, "center", false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaGeneral_local = null;
    } 
    return insertarLista_local;
  }
  public String insertarMenuSalir() {
    String menuSalir_local = "";
    try {
      menuSalir_local = menuSalir_local.concat("<script src=\"../utilidades/javascript/menusalir/xaramenu.js\"></script>\n");
      menuSalir_local = menuSalir_local.concat("<script src=\"../utilidades/javascript/menusalir/menusalir.js\"></script>\n");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return menuSalir_local;
  }
  public String insertarCajaParrafo(String pNombre, String pContenido, int pMaximaLongitud, String pEventos, int pFilas, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaParrafo_local = "";
    String estilo_local = null;
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return cajaParrafo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaParrafo_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaParrafo_local;
    }
    
    try {
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display:none; ";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #9FCC9F; vertical-align:middle; ";
      } 
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, "<textarea style=\"" + estilo_local + "width:" + getAnchoCampos() + "px\"");
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, "\"px rows= " + pFilas + "\" id=\"" + pNombre);
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, "\" name=\"" + pNombre + "\" maxlength=\"" + String.valueOf(pMaximaLongitud) + "\"" + " ");
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, pEventos + " onkeyup=\"return controlarMaximaLongitud(this)\">");
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, pContenido + "</textarea>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    
    return cajaParrafo_local;
  }
  public String insertarCajaParrafo(Campo pCampo, String pContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String cajaParrafo_local = "";
    String estilo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return cajaParrafo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return cajaParrafo_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return cajaParrafo_local;
    }
    
    try {
      estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; ";
      if (pEsCampoOculto) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #E2FFFF; vertical-align:middle; display:none; ";
      }
      else if (pEsCampoCalculado) {
        estilo_local = "font-family: tahoma; font-size: 11px; background-color : #9FCC9F; vertical-align:middle; ";
      } 
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, "<textarea style=\"" + estilo_local + "width:" + (pCampo.getEstiloCampo().getAnchoControl() - pCampo.getEstiloCampo().getMargenIzquierdaControl()) + "px\" ");
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, "rows=\"" + pCampo.getEstiloCampo().getCantidadRenglones() + "\" id=\"" + pCampo.getNombreCampo());
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, "\" name=\"" + pCampo.getNombreCampo() + "\" maxlength=\"" + pCampo.getFormatoCampo().getLongitudCampo() + "\" ");
      
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, pEventos + " onkeyup=\"return controlarMaximaLongitud(this)\">");
      cajaParrafo_local = mc.concatenarCadena(cajaParrafo_local, pContenido + "</textarea>");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estilo_local = null;
    } 
    
    return cajaParrafo_local;
  }
  public String crearCeldaParrafo(String pNombre, String pContenido, int pMaximaLongitud, String pEventos, String pAncho, int pFilas, String pAlineacion, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String celdaParrafo_local = "";
    
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return celdaParrafo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return celdaParrafo_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return celdaParrafo_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celdaParrafo_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaParrafo_local;
    }
    try {
      celdaParrafo_local = crearCeldaTransparente(insertarCajaParrafo(pNombre, pContenido, pMaximaLongitud, pEventos, pFilas, pEsCampoOculto, pEsCampoCalculado), "left", pAncho);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaParrafo_local;
  }
  public String insertarCampoParrafo(String pEtiqueta, String pNombre, String pContenido, int pMaximaLongitud, String pEventos, int pFilas, String pAlineacion, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoParrafo_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    
    try {
      campoParrafo_local = mc.concatenarCadena(campoParrafo_local, abrirFilaTabla());
      if (pEsCampoOculto) {
        campoParrafo_local = mc.concatenarCadena(campoParrafo_local, crearCeldaEtiquetaTransparente());
      } else {
        campoParrafo_local = mc.concatenarCadena(campoParrafo_local, crearCeldaEtiqueta(pEtiqueta));
      } 
      campoParrafo_local = mc.concatenarCadena(campoParrafo_local, crearCeldaParrafo(pNombre, pContenido, pMaximaLongitud, pEventos, getAnchoCampos(), pFilas, pAlineacion, pEsCampoOculto, pEsCampoCalculado));
      
      campoParrafo_local = mc.concatenarCadena(campoParrafo_local, cerrarFilaTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoParrafo_local;
  }
  public String insertarCampoParrafo(Campo pCampo, String pContenido, String pEventos, boolean pEsCampoOculto, boolean pEsCampoCalculado) {
    String campoParrafo_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pEventos == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    
    try {
      if (!pEsCampoOculto && pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
        campoParrafo_local = crearCeldaEtiqueta(pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo());
      }
      if (pCampo.getEstiloCampo().getAnchoControl() > 0) {
        String eventos_local = " onKeyPress=\"return replaceSingleQuote(event);\" onPaste=\"return replaceSingleQuotePaste(event);\" onDrop=\"return replaceSingleQuotePaste(event);\" ";
        campoParrafo_local = mc.concatenarCadena(campoParrafo_local, crearCeldaCampoDistribucion(pCampo.getEstiloCampo(), insertarCajaParrafo(pCampo, pContenido, pEventos + eventos_local, pEsCampoOculto, pEsCampoCalculado)));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoParrafo_local;
  }
  public String insertarCampoParrafoConsulta(String pEtiqueta, String pNombre, String pContenido, int pMaximaLongitud, int pFilas, String pAlineacion) {
    String campoParrafo_local = "";
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pContenido == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return campoParrafo_local;
    }
    
    try {
      campoParrafo_local = mc.concatenarCadena(campoParrafo_local, crearCeldaEtiqueta(pEtiqueta));
      campoParrafo_local = mc.concatenarCadena(campoParrafo_local, crearCeldaParrafo(pNombre, pContenido, pMaximaLongitud, "", getAnchoCampos(), pFilas, pAlineacion, false, false));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return campoParrafo_local;
  }
  public String insertarDerechosReservados(boolean pPantallaInicial) {
    String derechosReservados_local = "";
    
    try {
      derechosReservados_local = mc.concatenarCadena(derechosReservados_local, insertarTexto(String.valueOf(ConstantesGeneral.const_CaracterCopyright).toString() + ' ' + "Derechos Reservados" + ' ' + " 2010"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return derechosReservados_local;
  }
  private String insertarCeldaVersion(String pAlineacion) {
    String celdaVersion_local = "";
    
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celdaVersion_local;
    }
    
    try {
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, abrirTablaTransparente("", "", "center"));
      
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, abrirFilaTabla());
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, "<th class=\"celdaVersion\" align=\"" + pAlineacion + "\">");
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, " B. 40</th>");
      
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, cerrarFilaTabla());
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, abrirFilaTabla());
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, "<th class=\"celdaVersion\" align=\"" + pAlineacion);
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, "\">Fecha Versi\u00f3n: " + String.valueOf(ConstantesVersion.FECHA_VERSION).toString() + "</th>\n");
      
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, cerrarFilaTabla());
      celdaVersion_local = mc.concatenarCadena(celdaVersion_local, cerrarTabla());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaVersion_local;
  }
  public String insertarNumeroYFechaVersion(int pNumeroVersion, String pAlineacion, String pDirectorioImagen) {
    String numeroFechaVersion_local = "";
    
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return numeroFechaVersion_local;
    }
    if (pDirectorioImagen == ConstantesGeneral.VALOR_NULO) {
      return numeroFechaVersion_local;
    }
    try {
      numeroFechaVersion_local = mc.concatenarCadena(numeroFechaVersion_local, insertarCeldaVersion(pAlineacion));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return numeroFechaVersion_local;
  }
  public String abrirFrameset() {
    String frameset_local = "";
    try {
      frameset_local = "<frameset rows=\"0%,100%\">\n";
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return frameset_local;
  }
  public String cerrarFrameset() {
    String frameset_local = "";
    try {
      frameset_local = "</frameset>\n";
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return frameset_local;
  }
  public String crearFrame(String pDestino) {
    String frame_local = "";
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return frame_local;
    }
    
    try {
      if (mc.esCadenaVacia(pDestino)) {
        frame_local = "<frame>\n";
      } else {
        frame_local = "<frame src =\"" + pDestino + "\">\n";
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return frame_local;
  }
  public String crearCeldaEncabezadoCombinadaColumnasAnchoSinEstilo(String pContenidoCelda, int pNumeroColumnas, int pAnchoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th colspan=\"" + pNumeroColumnas + "\" width=\"" + pAnchoCelda + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaEncabezadoAnchoSinEstilo(String pContenidoCelda, int pAnchoCelda) {
    String celda_local = "";
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    try {
      celda_local = mc.concatenarCadena(celda_local, "<th width =\"" + pAnchoCelda + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public String crearCeldaSinEstilo(String pContenidoCelda, String pAlineacion, String pAncho) {
    String celda_local = "";
    String ancho_local = null;
    
    if (pContenidoCelda == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAncho == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    if (pAlineacion == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      if (!mc.esCadenaVacia(pAncho)) {
        ancho_local = " width=\"" + pAncho + "\" ";
      }
      celda_local = mc.concatenarCadena(celda_local, "<th " + ancho_local + " align=\"" + pAlineacion + "\">");
      celda_local = mc.concatenarCadena(celda_local, getConversorCaracteresHtml().getCadenaHtml(pContenidoCelda) + "</th> \n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      ancho_local = null;
    } 
    return celda_local;
  }
  public String insertarCeldaDescargarArchivo(Campo pCampo, String pDestino, String pNombreArchivo, int pNivelesAnterioresDirectorio, boolean pEsCampoFormulario, boolean pEsColorGris, int pAncho) {
    String celdaDescargarArchivo_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return celdaDescargarArchivo_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaDescargarArchivo_local;
    }
    if (pNombreArchivo == ConstantesGeneral.VALOR_NULO) {
      return celdaDescargarArchivo_local;
    }
    
    try {
      if (pEsCampoFormulario) {
        celdaDescargarArchivo_local = crearCeldaCampoDistribucionSinMargenSuperior(pCampo.getEstiloCampo(), insertarHipervinculoImagen(mc.complementarDirectorio(pNivelesAnterioresDirectorio) + "../imagenes/botones/" + "descargar" + ".gif", pNombreArchivo, pDestino, "", false, false), false);
      
      }
      else {
        
        celdaDescargarArchivo_local = crearCeldaContenidoImagen(insertarHipervinculoImagen(mc.complementarDirectorio(pNivelesAnterioresDirectorio) + "../imagenes/botones/" + "descargar" + ".gif", pNombreArchivo, pDestino, "", false, false), "center", String.valueOf(pAncho), pEsColorGris);
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return celdaDescargarArchivo_local;
  }
  public String insertarCeldaDescargarArchivoImagen(Campo pCampo, String pDestino, String pDescripcion) {
    String celdaDescargarArchivo_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return celdaDescargarArchivo_local;
    }
    if (pDestino == ConstantesGeneral.VALOR_NULO) {
      return celdaDescargarArchivo_local;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return celdaDescargarArchivo_local;
    }
    
    try {
      celdaDescargarArchivo_local = crearCeldaCampoDistribucionSinMargenSuperior(pCampo.getEstiloCampo(), insertarHiperVinculoImagenConDimensionesAnchoAlto(pDestino, pDestino, pCampo.getEstiloCampo().getAnchoControl(), pCampo.getAltoImagenPantallaEdicion(), true, "", pDescripcion), true);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return celdaDescargarArchivo_local;
  }
  public String insertarParrafoSeparacion() {
    return "<p>&nbsp;</p>\n";
  }
  public String ocultarBarraEstado() {
    String ocultarBarraEstado_local = "";
    
    try {
      ocultarBarraEstado_local = "if (document.layers) \n document.captureEvents(Event.MOUSEOVER | Event.MOUSEOUT) \n document.onmouseover=ocultarEstado \n document.onmouseout=ocultarEstado \n";
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return ocultarBarraEstado_local;
  }
  public String inhabilitarSeleccion() {
    StringBuffer inhabilitarSeleccion_local = null;
    
    try {
      inhabilitarSeleccion_local = new StringBuffer();
      inhabilitarSeleccion_local.append("function disabletext(e){\n");
      inhabilitarSeleccion_local.append("return false\n");
      inhabilitarSeleccion_local.append("}\n");
      inhabilitarSeleccion_local.append("function reEnable(){\n");
      inhabilitarSeleccion_local.append("return true\n");
      inhabilitarSeleccion_local.append("}\n");
      inhabilitarSeleccion_local.append("document.onselectstart=new Function (\"return false\")\n");
      inhabilitarSeleccion_local.append("if (window.sidebar){\n");
      inhabilitarSeleccion_local.append("document.onmousedown=disabletext\n");
      inhabilitarSeleccion_local.append("document.onclick=reEnable\n");
      inhabilitarSeleccion_local.append("}\n");
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return inhabilitarSeleccion_local.toString();
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\generadores\GeneradorComponentesHtml.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */