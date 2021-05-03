package com.sisnet.objetosManejo.manejoReportes;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaColumnasReporte;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaOrdenReporte;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaSubtotalReporte;
import java.util.Iterator;
public class CargadorReporte
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  protected static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private Aplicacion aAplicacionReporte;
  private int aValorLlavePrimariaReporte;
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private MotorAplicacion aMotorAplicacion;
  public CargadorReporte(Aplicacion pAplicacionReporte, int pValorLlavePrimariaReporte) {
    setAplicacionReporte(pAplicacionReporte);
    setValorLlavePrimariaReporte(pValorLlavePrimariaReporte);
  }
  public Aplicacion getAplicacionReporte() {
    return this.aAplicacionReporte;
  }
  public void setAplicacionReporte(Aplicacion pAplicacionReporte) {
    this.aAplicacionReporte = pAplicacionReporte;
  }
  public int getValorLlavePrimariaReporte() {
    return this.aValorLlavePrimariaReporte;
  }
  public void setValorLlavePrimariaReporte(int pValorLlavePrimariaReporte) {
    this.aValorLlavePrimariaReporte = pValorLlavePrimariaReporte;
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
  public String obtenerNombreReporte() {
    String nombreReporte_local = "";
    int valorLlavePrimariaReporte_local = -1;
    Object valorCampo_local = null;
    Campo campoUnico_local = null;
    
    try {
      valorLlavePrimariaReporte_local = getValorLlavePrimariaReporte();
      campoUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(getAplicacionReporte().getIdAplicacion());
      valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoUnico_local, ap.conformarNombreCampoLlavePrimaria(getAplicacionReporte().getNombreAplicacion()), valorLlavePrimariaReporte_local);
      
      if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
        nombreReporte_local = valorCampo_local.toString();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
      campoUnico_local = null;
    } 
    return nombreReporte_local;
  }
  public String obtenerEstructuraReporte() {
    String estructuraReporte_local = "";
    int valorLlavePrimariaReporte_local = -1;
    Object valorCampo_local = null;
    Campo campoUnico_local = null;
    Campo campoEstructura_local = null;
    
    try {
      campoUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(getAplicacionReporte().getIdAplicacion());
      valorLlavePrimariaReporte_local = getValorLlavePrimariaReporte();
      campoEstructura_local = getMotorAplicacion().obtenerCampoPorPosicion(2, campoUnico_local.getGrupoInformacion().getIdGrupoInformacion());
      
      if (campoEstructura_local != ConstantesGeneral.VALOR_NULO) {
        valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoEstructura_local, ap.conformarNombreCampoLlavePrimaria(getAplicacionReporte().getNombreAplicacion()), valorLlavePrimariaReporte_local);
        
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          estructuraReporte_local = valorCampo_local.toString();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
      campoEstructura_local = null;
      campoUnico_local = null;
    } 
    return estructuraReporte_local;
  }
  public ListaCadenas obtenerBloqueGeneral(String pEstructuraReporte) {
    ListaCadenas listaBloqueGeneral_local = null;
    String bloqueGeneral_local = null;
    
    if (pEstructuraReporte == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueGeneral_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pEstructuraReporte, "$GENERAL") && mc.verificarExistenciaSubCadena(pEstructuraReporte, "$FINGENERAL")) {
        
        bloqueGeneral_local = mc.obtenerSubCadena(pEstructuraReporte, mc.obtenerPosicionSubCadena(pEstructuraReporte, "$GENERAL") + mc.obtenerLongitudCadena("$GENERAL"), mc.obtenerPosicionSubCadena(pEstructuraReporte, "$FINGENERAL"));
        
        listaBloqueGeneral_local = mc.obtenerParrafoComoListaCadenas(bloqueGeneral_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloqueGeneral_local = null;
    } 
    return listaBloqueGeneral_local;
  }
  public String obtenerEstructuraEncabezado(String pEstructuraReporte, String pFecha, String pHora) {
    String estructuraEncabezado_local = null;
    String estructura_local = null;
    
    if (pEstructuraReporte == ConstantesGeneral.VALOR_NULO) {
      return estructuraEncabezado_local;
    }
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return estructuraEncabezado_local;
    }
    if (pHora == ConstantesGeneral.VALOR_NULO) {
      return estructuraEncabezado_local;
    }
    
    try {
      estructura_local = mc.convertirAMayusculas(pEstructuraReporte);
      if (mc.verificarExistenciaSubCadena(estructura_local, "$ENCABEZADO") && mc.verificarExistenciaSubCadena(estructura_local, "$FINENCABEZADO")) {
        
        estructuraEncabezado_local = mc.obtenerSubCadena(pEstructuraReporte, mc.obtenerPosicionSubCadena(estructura_local, "$ENCABEZADO") + mc.obtenerLongitudCadena("$ENCABEZADO"), mc.obtenerPosicionSubCadena(estructura_local, "$FINENCABEZADO"));
        
        estructuraEncabezado_local = asignarFechaHoraLineasEncabezadoPie(estructuraEncabezado_local, pFecha, pHora);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estructura_local = null;
    } 
    return estructuraEncabezado_local;
  }
  public String obtenerEstructuraPie(String pEstructuraReporte, String pFecha, String pHora) {
    String estructuraPie_local = null;
    
    if (pEstructuraReporte == ConstantesGeneral.VALOR_NULO) {
      return estructuraPie_local;
    }
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return estructuraPie_local;
    }
    if (pHora == ConstantesGeneral.VALOR_NULO) {
      return estructuraPie_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pEstructuraReporte, "$PIE") && mc.verificarExistenciaSubCadena(pEstructuraReporte, "$FINPIE")) {
        
        estructuraPie_local = mc.obtenerSubCadena(pEstructuraReporte, mc.obtenerPosicionSubCadena(pEstructuraReporte, "$PIE") + mc.obtenerLongitudCadena("$PIE"), mc.obtenerPosicionSubCadena(pEstructuraReporte, "$FINPIE"));
        
        estructuraPie_local = asignarFechaHoraLineasEncabezadoPie(estructuraPie_local, pFecha, pHora);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return estructuraPie_local;
  }
  public ListaCadenas obtenerBloqueIzquierda(String pEstructura) {
    ListaCadenas listaBloqueIzquierdo_local = null;
    String bloqueIzquierdo_local = null;
    String estructura_local = null;
    
    if (pEstructura == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueIzquierdo_local;
    }
    
    try {
      estructura_local = mc.convertirAMayusculas(pEstructura);
      if (mc.verificarExistenciaSubCadena(estructura_local, "$IZQUIERDA") && mc.verificarExistenciaSubCadena(estructura_local, "$FINIZQUIERDA")) {
        
        bloqueIzquierdo_local = mc.obtenerSubCadena(pEstructura, mc.obtenerPosicionSubCadena(estructura_local, "$IZQUIERDA") + mc.obtenerLongitudCadena("$IZQUIERDA"), mc.obtenerPosicionSubCadena(estructura_local, "$FINIZQUIERDA"));
        
        listaBloqueIzquierdo_local = mc.obtenerParrafoComoListaCadenas(bloqueIzquierdo_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estructura_local = null;
      bloqueIzquierdo_local = null;
    } 
    return listaBloqueIzquierdo_local;
  }
  public ListaCadenas obtenerBloqueCentro(String pEstructura) {
    ListaCadenas listaBloqueCentro_local = null;
    String bloqueCentro_local = null;
    String estructura_local = null;
    
    if (pEstructura == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueCentro_local;
    }
    
    try {
      estructura_local = mc.convertirAMayusculas(pEstructura);
      if (mc.verificarExistenciaSubCadena(estructura_local, "$CENTRO") && mc.verificarExistenciaSubCadena(estructura_local, "$FINCENTRO")) {
        
        bloqueCentro_local = mc.obtenerSubCadena(pEstructura, mc.obtenerPosicionSubCadena(estructura_local, "$CENTRO") + mc.obtenerLongitudCadena("$CENTRO"), mc.obtenerPosicionSubCadena(estructura_local, "$FINCENTRO"));
        
        listaBloqueCentro_local = mc.obtenerParrafoComoListaCadenas(bloqueCentro_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estructura_local = null;
      bloqueCentro_local = null;
    } 
    return listaBloqueCentro_local;
  }
  public ListaCadenas obtenerBloqueDerecha(String pEstructura) {
    ListaCadenas listaBloqueDerecha_local = null;
    String bloqueDerecha_local = null;
    String estructura_local = null;
    
    if (pEstructura == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueDerecha_local;
    }
    
    try {
      estructura_local = mc.convertirAMayusculas(pEstructura);
      if (mc.verificarExistenciaSubCadena(estructura_local, "$DERECHA") && mc.verificarExistenciaSubCadena(estructura_local, "$FINDERECHA")) {
        
        bloqueDerecha_local = mc.obtenerSubCadena(pEstructura, mc.obtenerPosicionSubCadena(estructura_local, "$DERECHA") + mc.obtenerLongitudCadena("$DERECHA"), mc.obtenerPosicionSubCadena(estructura_local, "$FINDERECHA"));
        
        listaBloqueDerecha_local = mc.obtenerParrafoComoListaCadenas(bloqueDerecha_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloqueDerecha_local = null;
      estructura_local = null;
    } 
    return listaBloqueDerecha_local;
  }
  public ListaCadenas obtenerBloqueColumnas(String pEstructuraReporte) {
    ListaCadenas listaBloqueColumnas_local = null;
    String bloqueColumnas_local = null;
    
    if (pEstructuraReporte == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueColumnas_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pEstructuraReporte, "$COLUMNAS") && mc.verificarExistenciaSubCadena(pEstructuraReporte, "$FINCOLUMNAS")) {
        
        bloqueColumnas_local = mc.obtenerSubCadena(pEstructuraReporte, mc.obtenerPosicionSubCadena(pEstructuraReporte, "$COLUMNAS") + mc.obtenerLongitudCadena("$COLUMNAS"), mc.obtenerPosicionSubCadena(pEstructuraReporte, "$FINCOLUMNAS"));
        
        listaBloqueColumnas_local = mc.obtenerParrafoComoListaCadenas(bloqueColumnas_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloqueColumnas_local = null;
    } 
    return listaBloqueColumnas_local;
  }
  public ListaCadenas obtenerBloqueSubtotales(String pEstructuraReporte) {
    ListaCadenas listaBloqueSubtotales_local = null;
    String bloqueSubtotales_local = null;
    
    if (pEstructuraReporte == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueSubtotales_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pEstructuraReporte, "$SUBTOTALES") && mc.verificarExistenciaSubCadena(pEstructuraReporte, "$FINSUBTOTALES")) {
        
        bloqueSubtotales_local = mc.obtenerSubCadena(pEstructuraReporte, mc.obtenerPosicionSubCadena(pEstructuraReporte, "$SUBTOTALES") + mc.obtenerLongitudCadena("$SUBTOTALES"), mc.obtenerPosicionSubCadena(pEstructuraReporte, "$FINSUBTOTALES"));
        
        listaBloqueSubtotales_local = mc.obtenerParrafoComoListaCadenas(bloqueSubtotales_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloqueSubtotales_local = null;
    } 
    return listaBloqueSubtotales_local;
  }
  public ListaCadenas obtenerBloqueTotalFinal(String pEstructuraReporte) {
    ListaCadenas listaBloqueTotalFinal_local = null;
    String bloqueTotalFinal_local = null;
    
    if (pEstructuraReporte == ConstantesGeneral.VALOR_NULO) {
      return listaBloqueTotalFinal_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pEstructuraReporte, "$TOTALFINAL") && mc.verificarExistenciaSubCadena(pEstructuraReporte, "$FINTOTALFINAL")) {
        
        bloqueTotalFinal_local = mc.obtenerSubCadena(pEstructuraReporte, mc.obtenerPosicionSubCadena(pEstructuraReporte, "$TOTALFINAL") + mc.obtenerLongitudCadena("$TOTALFINAL"), mc.obtenerPosicionSubCadena(pEstructuraReporte, "$FINTOTALFINAL"));
        
        listaBloqueTotalFinal_local = mc.obtenerParrafoComoListaCadenas(bloqueTotalFinal_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloqueTotalFinal_local = null;
    } 
    return listaBloqueTotalFinal_local;
  }
  public ListaCampo obtenerListaCamposTotales(int pIdAplicacionActual, ListaCadenas pListaBloqueTotalFinal) {
    ListaCampo listaCampo_local = null;
    int posicion_local = 0;
    String cadenaSeudonimos_local = null;
    String[] seudonimos_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    if (pListaBloqueTotalFinal == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = pListaBloqueTotalFinal.iterator();
      while (iterador_local.hasNext()) {
        cadenaSeudonimos_local = mc.convertirAMayusculas((String)iterador_local.next());
        if (mc.verificarExistenciaSubCadena(cadenaSeudonimos_local, "$EN COLUMNAS:")) {
          cadenaSeudonimos_local = mc.borrarEspaciosLaterales(mc.convertirAMayusculas(mc.obtenerSubCadena(cadenaSeudonimos_local, mc.obtenerPosicionSubCadena(cadenaSeudonimos_local, "$EN COLUMNAS:") + mc.obtenerLongitudCadena("$EN COLUMNAS:"), mc.obtenerLongitudCadena(cadenaSeudonimos_local))));
          
          seudonimos_local = mc.fraccionarCadena(cadenaSeudonimos_local, String.valueOf(' '));
          
          listaCampo_local = new ListaCampo();
          for (posicion_local = 0; posicion_local < seudonimos_local.length; posicion_local++) {
            campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimos_local[posicion_local], pIdAplicacionActual);
            
            if (campo_local != ConstantesGeneral.VALOR_NULO) {
              listaCampo_local.adicionar(campo_local);
            }
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      seudonimos_local = null;
      cadenaSeudonimos_local = null;
      iterador_local = null;
    } 
    return listaCampo_local;
  }
  public int obtenerTamanoPagina(ListaCadenas pListaBloqueGeneral) {
    int tamanoPagina_local = 0;
    boolean tieneTamanoPagina_local = false;
    String linea_local = null;
    Iterator iterador_local = null;
    
    if (pListaBloqueGeneral == ConstantesGeneral.VALOR_NULO) {
      return tamanoPagina_local;
    }
    
    try {
      iterador_local = pListaBloqueGeneral.iterator();
      while (iterador_local.hasNext() && !tieneTamanoPagina_local) {
        linea_local = (String)iterador_local.next();
        tieneTamanoPagina_local = mc.verificarExistenciaSubCadena(linea_local, "$TAMANO PAGINA:");
        if (tieneTamanoPagina_local) {
          linea_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(linea_local, mc.obtenerPosicionSubCadena(linea_local, "$TAMANO PAGINA:") + mc.obtenerLongitudCadena("$TAMANO PAGINA:"), mc.obtenerLongitudCadena(linea_local)));
          
          if (mc.esCadenaNumerica(linea_local, true)) {
            tamanoPagina_local = Integer.parseInt(linea_local);
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      linea_local = null;
      iterador_local = null;
    } 
    return tamanoPagina_local;
  }
  public boolean verificarMostrarDetallesColumnas(ListaCadenas pListaBloqueColumnas) {
    boolean mostrarDetalles_local = false;
    String linea_local = null;
    Iterator iterador_local = null;
    
    if (pListaBloqueColumnas == ConstantesGeneral.VALOR_NULO) {
      return mostrarDetalles_local;
    }
    
    try {
      iterador_local = pListaBloqueColumnas.iterator();
      while (iterador_local.hasNext() && !mostrarDetalles_local) {
        linea_local = (String)iterador_local.next();
        mostrarDetalles_local = mc.verificarExistenciaSubCadena(linea_local, "$CON DETALLE:");
        if (mostrarDetalles_local) {
          linea_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(linea_local, mc.obtenerPosicionSubCadena(linea_local, "$CON DETALLE:") + mc.obtenerLongitudCadena("$CON DETALLE:"), mc.obtenerLongitudCadena(linea_local)));
          
          mostrarDetalles_local = mc.sonCadenasIgualesIgnorarMayusculas(linea_local, "Si");
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      linea_local = null;
      iterador_local = null;
    } 
    return mostrarDetalles_local;
  }
  private String obtenerSeudonimoColumna(String pColumna) {
    String seudonimo_local = "";
    
    if (pColumna == ConstantesGeneral.VALOR_NULO) {
      return seudonimo_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pColumna, "$COLUMNA:")) {
        seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pColumna, mc.obtenerPosicionSubCadena(pColumna, "$COLUMNA:") + mc.obtenerLongitudCadena("$COLUMNA:"), mc.obtenerPosicionSubCadena(pColumna, String.valueOf('"'))));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {}
    
    return seudonimo_local;
  }
  private String obtenerNombreColumna(String pColumna) {
    String nombreColumna_local = "";
    String columna_local = null;
    
    if (pColumna == ConstantesGeneral.VALOR_NULO) {
      return nombreColumna_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pColumna, "$COLUMNA:")) {
        columna_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pColumna, mc.obtenerPosicionSubCadena(pColumna, "$COLUMNA:") + mc.obtenerLongitudCadena("$COLUMNA:"), mc.obtenerLongitudCadena(pColumna)));
        
        if (mc.verificarExistenciaSubCadena(columna_local, String.valueOf('"'))) {
          nombreColumna_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(columna_local, mc.obtenerPosicionSubCadena(columna_local, String.valueOf('"')) + 1, mc.obtenerUltimaPosicionSubCadena(columna_local, String.valueOf('"'))));
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      columna_local = null;
    } 
    return nombreColumna_local;
  }
  private int obtenerAnchoColumna(String pColumna) {
    int anchoColumna_local = 0;
    int posicionComillas_local = -1;
    String columna_local = null;
    String ancho_local = null;
    String[] detallesColumna_local = null;
    
    if (pColumna == ConstantesGeneral.VALOR_NULO) {
      return anchoColumna_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pColumna, "$COLUMNA:")) {
        columna_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pColumna, mc.obtenerPosicionSubCadena(pColumna, "$COLUMNA:") + mc.obtenerLongitudCadena("$COLUMNA:"), mc.obtenerLongitudCadena(pColumna)));
        
        if (mc.verificarExistenciaSubCadena(columna_local, String.valueOf('"'))) {
          posicionComillas_local = mc.obtenerUltimaPosicionSubCadena(columna_local, String.valueOf('"')) + 1;
          
          detallesColumna_local = mc.fraccionarCadena(mc.borrarEspaciosLaterales(mc.obtenerSubCadena(columna_local, posicionComillas_local, mc.obtenerLongitudCadena(columna_local))), String.valueOf(' '));
          
          if (detallesColumna_local.length > 0) {
            ancho_local = detallesColumna_local[0];
            if (mc.esCadenaNumerica(ancho_local, true)) {
              anchoColumna_local = Integer.parseInt(ancho_local);
            }
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      ancho_local = null;
      columna_local = null;
      detallesColumna_local = null;
    } 
    return anchoColumna_local;
  }
  private int obtenerEquivalenteTipoRepeticion(String pDescripcionTipoRepeticion) {
    int tipoRepeticion_local = -1;
    
    if (pDescripcionTipoRepeticion == ConstantesGeneral.VALOR_NULO) {
      return tipoRepeticion_local;
    }
    
    try {
      if (mc.sonCadenasIguales(pDescripcionTipoRepeticion, "SIREPETIR")) {
        return 1;
      }
      if (mc.sonCadenasIguales(pDescripcionTipoRepeticion, "NOREPETIR")) {
        return 2;
      }
      if (mc.sonCadenasIguales(pDescripcionTipoRepeticion, "NOREPETIRPRIMERO")) {
        return 3;
      }
      if (mc.sonCadenasIguales(pDescripcionTipoRepeticion, "NOREPETIRULTIMO")) {
        return 4;
      }
      if (mc.sonCadenasIguales(pDescripcionTipoRepeticion, "NOREPETIRTOTAL")) {
        return 5;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoRepeticion_local;
  }
  private int obtenerTipoRepeticionCampoColumna(String pColumna) {
    int tipoRepeticion_local = -1;
    int posicionComillas_local = -1;
    String descripcionTipoRepeticion_local = "";
    String columna_local = null;
    String[] detallesColumna_local = null;
    
    if (pColumna == ConstantesGeneral.VALOR_NULO) {
      return tipoRepeticion_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pColumna, "$COLUMNA:")) {
        columna_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pColumna, mc.obtenerPosicionSubCadena(pColumna, "$COLUMNA:") + mc.obtenerLongitudCadena("$COLUMNA:"), mc.obtenerLongitudCadena(pColumna)));
        
        if (mc.verificarExistenciaSubCadena(columna_local, String.valueOf('"'))) {
          posicionComillas_local = mc.obtenerUltimaPosicionSubCadena(columna_local, String.valueOf('"')) + 1;
          
          detallesColumna_local = mc.fraccionarCadena(mc.borrarEspaciosLaterales(mc.obtenerSubCadena(columna_local, posicionComillas_local, mc.obtenerLongitudCadena(columna_local))), String.valueOf(' '));
          
          if (detallesColumna_local.length > 1) {
            descripcionTipoRepeticion_local = detallesColumna_local[1];
          }
        } 
        tipoRepeticion_local = obtenerEquivalenteTipoRepeticion(descripcionTipoRepeticion_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      columna_local = null;
      detallesColumna_local = null;
      descripcionTipoRepeticion_local = null;
    } 
    return tipoRepeticion_local;
  }
  private String obtenerFormatoValoresColumna(String pColumna) {
    String formatoValoresColumna_local = "";
    int posicionComillas_local = -1;
    String columna_local = null;
    String[] detallesColumna_local = null;
    
    if (pColumna == ConstantesGeneral.VALOR_NULO) {
      return formatoValoresColumna_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pColumna, "$COLUMNA:")) {
        columna_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pColumna, mc.obtenerPosicionSubCadena(pColumna, "$COLUMNA:") + mc.obtenerLongitudCadena("$COLUMNA:"), mc.obtenerLongitudCadena(pColumna)));
        
        if (mc.verificarExistenciaSubCadena(columna_local, String.valueOf('"'))) {
          posicionComillas_local = mc.obtenerUltimaPosicionSubCadena(columna_local, String.valueOf('"')) + 1;
          
          detallesColumna_local = mc.fraccionarCadena(mc.borrarEspaciosLaterales(mc.obtenerSubCadena(columna_local, posicionComillas_local, mc.obtenerLongitudCadena(columna_local))), String.valueOf(' '));
          
          if (detallesColumna_local.length > 2) {
            formatoValoresColumna_local = detallesColumna_local[2];
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      columna_local = null;
      detallesColumna_local = null;
    } 
    return formatoValoresColumna_local;
  }
  public ListaColumnasReporte cargarListaColumnasReporte(int pIdAplicacionActual, ListaCadenas pListaBloqueColumnas) {
    ListaColumnasReporte listaColumnasReporte_local = null;
    String columna_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaBloqueColumnas == ConstantesGeneral.VALOR_NULO) {
      return listaColumnasReporte_local;
    }
    
    try {
      listaColumnasReporte_local = new ListaColumnasReporte();
      iterador_local = pListaBloqueColumnas.iterator();
      while (iterador_local.hasNext()) {
        columna_local = (String)iterador_local.next();
        if (mc.verificarExistenciaSubCadena(columna_local, "$COLUMNA:")) {
          campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(obtenerSeudonimoColumna(columna_local), pIdAplicacionActual);
          
          if (campo_local != ConstantesGeneral.VALOR_NULO) {
            listaColumnasReporte_local.adicionar(obtenerNombreColumna(columna_local), obtenerAnchoColumna(columna_local), obtenerTipoRepeticionCampoColumna(columna_local), obtenerFormatoValoresColumna(columna_local), campo_local);
          }
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      columna_local = null;
      iterador_local = null;
    } 
    return listaColumnasReporte_local;
  }
  private int obtenerTipoOrdenamiento(String pDescripcionTipoOrdenamiento) {
    int tipoOrdenamiento_local = 1;
    
    if (pDescripcionTipoOrdenamiento == ConstantesGeneral.VALOR_NULO) {
      return tipoOrdenamiento_local;
    }
    
    try {
      if (mc.sonCadenasIguales(pDescripcionTipoOrdenamiento, "DES")) {
        return 2;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoOrdenamiento_local;
  }
  public ListaOrdenReporte cargarListaOrdenReporte(int pIdAplicacionActual, ListaCadenas pListaBloqueColumnas) {
    ListaOrdenReporte listaOrdenReporte_local = null;
    int contador_local = 0;
    int posicion_local = -1;
    String columna_local = null;
    String seudonimo_local = null;
    String orden_local = null;
    String ordenSeudonimo_local = null;
    String[] detallesColumna_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaBloqueColumnas == ConstantesGeneral.VALOR_NULO) {
      return listaOrdenReporte_local;
    }
    
    try {
      listaOrdenReporte_local = new ListaOrdenReporte();
      iterador_local = pListaBloqueColumnas.iterator();
      while (iterador_local.hasNext()) {
        columna_local = mc.convertirAMayusculas((String)iterador_local.next());
        if (mc.verificarExistenciaSubCadena(columna_local, "$ORDEN:")) {
          columna_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(columna_local, mc.obtenerPosicionSubCadena(columna_local, "$ORDEN:") + mc.obtenerLongitudCadena("$ORDEN:"), mc.obtenerLongitudCadena(columna_local)));
          
          detallesColumna_local = mc.fraccionarCadena(columna_local, String.valueOf(' '));
          
          for (contador_local = 0; contador_local < detallesColumna_local.length; contador_local++) {
            ordenSeudonimo_local = detallesColumna_local[contador_local];
            if (mc.verificarExistenciaSubCadena(ordenSeudonimo_local, String.valueOf('-'))) {
              
              posicion_local = mc.obtenerPosicionSubCadena(ordenSeudonimo_local, String.valueOf('-')) + 1;
              
              orden_local = mc.obtenerSubCadena(ordenSeudonimo_local, 0, posicion_local);
              
              seudonimo_local = mc.obtenerSubCadena(ordenSeudonimo_local, posicion_local, mc.obtenerLongitudCadena(ordenSeudonimo_local));
              
              campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local, pIdAplicacionActual);
              if (campo_local != ConstantesGeneral.VALOR_NULO) {
                listaOrdenReporte_local.adicionar(obtenerTipoOrdenamiento(orden_local), campo_local);
              }
            } 
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      orden_local = null;
      campo_local = null;
      columna_local = null;
      iterador_local = null;
      seudonimo_local = null;
      ordenSeudonimo_local = null;
      detallesColumna_local = null;
    } 
    return listaOrdenReporte_local;
  }
  private String obtenerSeudonimoCampoDeCambioParaSubtotales(String pLineaSubtotales) {
    String seudonimoCampoCambio_local = "";
    
    if (pLineaSubtotales == ConstantesGeneral.VALOR_NULO) {
      return seudonimoCampoCambio_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pLineaSubtotales, "$AL CAMBIAR:")) {
        seudonimoCampoCambio_local = mc.convertirAMayusculas(mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pLineaSubtotales, mc.obtenerLongitudCadena("$AL CAMBIAR:"), mc.obtenerPosicionSubCadena(pLineaSubtotales, String.valueOf('"')))));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return seudonimoCampoCambio_local;
  }
  private String obtenerInicioCambioParaSubtotales(String pLineaSubtotales) {
    String inicioCambio_local = "";
    int posicionInicial_local = -1;
    
    if (pLineaSubtotales == ConstantesGeneral.VALOR_NULO) {
      return inicioCambio_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pLineaSubtotales, "$AL CAMBIAR:")) {
        posicionInicial_local = mc.obtenerPosicionSubCadena(pLineaSubtotales, String.valueOf('"')) + 1;
        
        inicioCambio_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pLineaSubtotales, posicionInicial_local, mc.obtenerPosicionSubCadenaAPartirPosicion(pLineaSubtotales, String.valueOf('"'), posicionInicial_local)));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return inicioCambio_local;
  }
  private String obtenerFinalCambioParaSubtotales(String pLineaSubtotales) {
    String finalCambio_local = "";
    int posicionInicial_local = -1;
    
    if (pLineaSubtotales == ConstantesGeneral.VALOR_NULO) {
      return finalCambio_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pLineaSubtotales, "$AL CAMBIAR:")) {
        posicionInicial_local = mc.obtenerPosicionSubCadena(pLineaSubtotales, String.valueOf('"'));
        posicionInicial_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pLineaSubtotales, String.valueOf('"'), posicionInicial_local + 1);
        
        posicionInicial_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pLineaSubtotales, String.valueOf('"'), posicionInicial_local + 1) + 1;
        
        finalCambio_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pLineaSubtotales, posicionInicial_local, mc.obtenerUltimaPosicionSubCadena(pLineaSubtotales, String.valueOf('"'))));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return finalCambio_local;
  }
  private ListaCampo obtenerListaCamposSubtotales(int pIdAplicacionActual, String pLineaSubtotales) {
    ListaCampo listaCampo_local = null;
    int posicion_local = 0;
    String cadenaSeudonimos_local = null;
    String[] seudonimos_local = null;
    Campo campo_local = null;
    
    if (pLineaSubtotales == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pLineaSubtotales, "$AL CAMBIAR:")) {
        cadenaSeudonimos_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pLineaSubtotales, mc.obtenerUltimaPosicionSubCadena(pLineaSubtotales, String.valueOf('"')), mc.obtenerLongitudCadena(pLineaSubtotales)));
        
        seudonimos_local = mc.fraccionarCadena(mc.convertirAMayusculas(cadenaSeudonimos_local), String.valueOf(' '));
        
        listaCampo_local = new ListaCampo();
        for (posicion_local = 0; posicion_local < seudonimos_local.length; posicion_local++) {
          campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimos_local[posicion_local], pIdAplicacionActual);
          
          if (campo_local != ConstantesGeneral.VALOR_NULO) {
            listaCampo_local.adicionar(campo_local);
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      seudonimos_local = null;
      cadenaSeudonimos_local = null;
    } 
    return listaCampo_local;
  }
  public ListaSubtotalReporte cargarListaSubtotalReporte(int pIdAplicacionActual, ListaCadenas pListaBloqueSubtotales) {
    ListaSubtotalReporte listaSubtotalReporte_local = null;
    String linea_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaBloqueSubtotales == ConstantesGeneral.VALOR_NULO) {
      return listaSubtotalReporte_local;
    }
    
    try {
      listaSubtotalReporte_local = new ListaSubtotalReporte();
      iterador_local = pListaBloqueSubtotales.iterator();
      while (iterador_local.hasNext()) {
        linea_local = (String)iterador_local.next();
        campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(obtenerSeudonimoCampoDeCambioParaSubtotales(linea_local), pIdAplicacionActual);
        
        if (campo_local != ConstantesGeneral.VALOR_NULO) {
          listaSubtotalReporte_local.adicionar(obtenerInicioCambioParaSubtotales(linea_local), obtenerFinalCambioParaSubtotales(linea_local), campo_local, obtenerListaCamposSubtotales(pIdAplicacionActual, linea_local));
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      linea_local = null;
      campo_local = null;
      iterador_local = null;
    } 
    return listaSubtotalReporte_local;
  }
  private boolean verificarInsertarHoraReporte(String pLinea) {
    boolean insertarHoraReporte_local = false;
    
    if (pLinea == ConstantesGeneral.VALOR_NULO) {
      return insertarHoraReporte_local;
    }
    
    try {
      insertarHoraReporte_local = mc.verificarExistenciaSubCadena(pLinea, "{%HOR}");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return insertarHoraReporte_local;
  }
  private boolean verificarInsertarFechaReporte(String pLinea) {
    boolean insertarFechaReporte_local = false;
    
    if (pLinea == ConstantesGeneral.VALOR_NULO) {
      return insertarFechaReporte_local;
    }
    
    try {
      insertarFechaReporte_local = mc.verificarExistenciaSubCadena(pLinea, "{%FEC}");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return insertarFechaReporte_local;
  }
  private String asignarFechaHoraLineasEncabezadoPie(String pEstructuraEncabezadoPie, String pFecha, String pHora) {
    String encabezadoPie_local = "";
    
    if (pEstructuraEncabezadoPie == ConstantesGeneral.VALOR_NULO) {
      return encabezadoPie_local;
    }
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return encabezadoPie_local;
    }
    if (pHora == ConstantesGeneral.VALOR_NULO) {
      return encabezadoPie_local;
    }
    
    try {
      encabezadoPie_local = mc.convertirAMayusculas(pEstructuraEncabezadoPie);
      if (verificarInsertarFechaReporte(encabezadoPie_local)) {
        encabezadoPie_local = mc.reemplazarCadena(encabezadoPie_local, "\\{%FEC\\}", pFecha);
      }
      if (verificarInsertarHoraReporte(encabezadoPie_local)) {
        encabezadoPie_local = mc.reemplazarCadena(encabezadoPie_local, "\\{%HOR\\}", pHora);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return encabezadoPie_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\CargadorReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */