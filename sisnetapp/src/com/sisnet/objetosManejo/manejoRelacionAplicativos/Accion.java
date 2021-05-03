package com.sisnet.objetosManejo.manejoRelacionAplicativos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaCiclos;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Ciclo;
import java.util.Iterator;
public class Accion
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private GrupoInformacion aGrupoInformacion;
  private String aAccionRealizar;
  private String aCiclos;
  private String aAsignaciones;
  private ListaCiclos aListaCiclos;
  private ListaCadenas aListaAsignaciones;
  private ListaCampo aListaAsignacionesInterpretada;
  private String aMensaje;
  private int aTiempoEspera;
  private MotorAplicacion aMotorAplicacion;
  private String aEtiqueta;
  private String aEtiquetaDestino;
  private boolean aActualizarInformacionRecalculable;
  public Accion() {
    setGrupoInformacion(null);
    setAccionRealizar("");
    setCiclos("");
    setAsignaciones("");
    setListaCiclos(null);
    setListaAsignaciones(null);
    setListaAsignacionesInterpretada(null);
    setMensaje("");
    setTiempoEspera(0);
    setMotorAplicacion(null);
    setMotorAplicacion(null);
  }
  public GrupoInformacion getGrupoInformacion() {
    return this.aGrupoInformacion;
  }
  public void setGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    this.aGrupoInformacion = pGrupoInformacion;
  }
  public String getAccionRealizar() {
    return this.aAccionRealizar;
  }
  public void setAccionRealizar(String pAccionRealizar) {
    this.aAccionRealizar = pAccionRealizar;
  }
  public String getCiclos() {
    return this.aCiclos;
  }
  public void setCiclos(String pCiclos) {
    this.aCiclos = pCiclos;
    if (pCiclos != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(pCiclos)) {
      setListaCiclos(obtenerListaCiclos());
    }
  }
  public String getAsignaciones() {
    return this.aAsignaciones;
  }
  public void setAsignaciones(String pAsignaciones) {
    this.aAsignaciones = pAsignaciones;
    if (pAsignaciones != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(pAsignaciones)) {
      setListaAsignaciones(obtenerListaAsignaciones());
    }
  }
  public ListaCiclos getListaCiclos() {
    return this.aListaCiclos;
  }
  public void setListaCiclos(ListaCiclos pListaCiclos) {
    this.aListaCiclos = pListaCiclos;
  }
  public ListaCadenas getListaAsignaciones() {
    return this.aListaAsignaciones;
  }
  public void setListaAsignaciones(ListaCadenas pListaAsignaciones) {
    this.aListaAsignaciones = pListaAsignaciones;
  }
  public ListaCampo getListaAsignacionesInterpretada() {
    return this.aListaAsignacionesInterpretada;
  }
  public void setListaAsignacionesInterpretada(ListaCampo pListaAsignacionesInterpretada) {
    this.aListaAsignacionesInterpretada = pListaAsignacionesInterpretada;
  }
  public String getMensaje() {
    return this.aMensaje;
  }
  public void setMensaje(String pMensaje) {
    this.aMensaje = pMensaje;
  }
  public int getTiempoEspera() {
    return this.aTiempoEspera;
  }
  public void setTiempoEspera(int pTiempoEspera) {
    this.aTiempoEspera = pTiempoEspera;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  public String getEtiqueta() {
    return this.aEtiqueta;
  }
  public void setEtiqueta(String pEtiqueta) {
    this.aEtiqueta = pEtiqueta;
  }
  public String getEtiquetaDestino() {
    return this.aEtiquetaDestino;
  }
  public void setEtiquetaDestino(String pEtiquetaDestino) {
    this.aEtiquetaDestino = pEtiquetaDestino;
  }
  public boolean esActualizarInformacionRecalculable() {
    return this.aActualizarInformacionRecalculable;
  }
  public void setActualizarInformacionRecalculable(boolean pActualizarInformacionRecalculable) {
    this.aActualizarInformacionRecalculable = pActualizarInformacionRecalculable;
  }
  private String obtenerCadenaCiclo(String pBloqueCiclo) {
    String cadenaCiclo_local = "";
    int posicionCicloInverso_local = -1;
    int posicionCiclo_local = -1;
    int posicionPrimer_local = -1;
    int posicionUltimo_local = -1;
    int[] arregloPosiciones_local = new int[4];
    int menorPosicion_local = -1;
    
    if (pBloqueCiclo == ConstantesGeneral.VALOR_NULO) {
      return cadenaCiclo_local;
    }
    
    try {
      cadenaCiclo_local = pBloqueCiclo;
      if (mc.verificarExistenciaSubCadena(pBloqueCiclo, "$CINVERSO") || mc.verificarExistenciaSubCadena(pBloqueCiclo, "$CICLO") || mc.verificarExistenciaSubCadena(pBloqueCiclo, "$PRIMER") || mc.verificarExistenciaSubCadena(pBloqueCiclo, "$ULTIMO")) {
        
        posicionCicloInverso_local = mc.obtenerPosicionSubCadena(pBloqueCiclo, "$CINVERSO");
        posicionCiclo_local = mc.obtenerPosicionSubCadena(pBloqueCiclo, "$CICLO");
        posicionPrimer_local = mc.obtenerPosicionSubCadena(pBloqueCiclo, "$PRIMER");
        posicionUltimo_local = mc.obtenerPosicionSubCadena(pBloqueCiclo, "$ULTIMO");
        arregloPosiciones_local[0] = posicionCicloInverso_local;
        arregloPosiciones_local[1] = posicionCiclo_local;
        arregloPosiciones_local[2] = posicionPrimer_local;
        arregloPosiciones_local[3] = posicionUltimo_local;
        menorPosicion_local = ap.obtenerPrimerValorPositivoDeArreglo(arregloPosiciones_local);
        
        if (0 < menorPosicion_local) {
          cadenaCiclo_local = mc.obtenerSubCadena(pBloqueCiclo, 0, menorPosicion_local);
        } else {
          cadenaCiclo_local = "";
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      arregloPosiciones_local = null;
    } 
    
    return cadenaCiclo_local;
  }
  private GrupoInformacion obtenerGrupoInformacionCiclo(String pCadenaCiclo) {
    GrupoInformacion grupoInformacion_local = null;
    int posicionFinal_local = -1;
    String descripcionGrupoInformacion_local = null;
    
    if (pCadenaCiclo == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      posicionFinal_local = mc.obtenerPosicionSubCadena(pCadenaCiclo, "\r\n");
      if (posicionFinal_local == -1) {
        posicionFinal_local = mc.obtenerLongitudCadena(pCadenaCiclo);
      }
      descripcionGrupoInformacion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pCadenaCiclo, 0, posicionFinal_local));
      
      grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionRelacionAplicativos(descripcionGrupoInformacion_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  private String obtenerCondicionesCiclo(String pCadenaCiclo) {
    String condiciones_local = "";
    int posicionSaltoLinea_local = -1;
    
    if (pCadenaCiclo == ConstantesGeneral.VALOR_NULO) {
      return condiciones_local;
    }
    
    try {
      posicionSaltoLinea_local = mc.obtenerPosicionSubCadena(pCadenaCiclo, "\r\n") + mc.obtenerLongitudCadena("\r\n");
      
      if (posicionSaltoLinea_local == -1) {
        posicionSaltoLinea_local = 0;
      }
      condiciones_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pCadenaCiclo, posicionSaltoLinea_local, mc.obtenerLongitudCadena(pCadenaCiclo)));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return condiciones_local;
  }
  private ListaCiclos obtenerListaCiclos() {
    ListaCiclos listaCiclos_local = null;
    int numeroCiclo_local = 0;
    int posicionCicloInverso_local = -1;
    int posicionCiclo_local = -1;
    int posicionPrimer_local = -1;
    int posicionUltimo_local = -1;
    int menorPosicion_local = -1;
    String bloqueCiclos_local = null;
    String cadenaCiclo_local = null;
    Ciclo ciclo_local = null;
    int posicionFinal_local = -1;
    int[] arregloPosiciones_local = new int[4];
    
    try {
      bloqueCiclos_local = getCiclos();
      if (mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$CINVERSO") || mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$CICLO") || mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$PRIMER") || mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$ULTIMO")) {
        
        listaCiclos_local = new ListaCiclos();
        
        while (mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$CINVERSO") || mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$CICLO") || mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$PRIMER") || mc.verificarExistenciaSubCadena(bloqueCiclos_local, "$ULTIMO")) {
          
          posicionCicloInverso_local = mc.obtenerPosicionSubCadena(bloqueCiclos_local, "$CINVERSO");
          if (posicionCicloInverso_local != -1) {
            posicionCicloInverso_local += mc.obtenerLongitudCadena("$CINVERSO");
          }
          posicionCiclo_local = mc.obtenerPosicionSubCadena(bloqueCiclos_local, "$CICLO");
          if (posicionCiclo_local != -1) {
            posicionCiclo_local += mc.obtenerLongitudCadena("$CICLO");
          }
          posicionPrimer_local = mc.obtenerPosicionSubCadena(bloqueCiclos_local, "$PRIMER");
          if (posicionPrimer_local != -1) {
            posicionPrimer_local += mc.obtenerLongitudCadena("$PRIMER");
          }
          posicionUltimo_local = mc.obtenerPosicionSubCadena(bloqueCiclos_local, "$ULTIMO");
          if (posicionUltimo_local != -1) {
            posicionUltimo_local += mc.obtenerLongitudCadena("$ULTIMO");
          }
          arregloPosiciones_local[0] = posicionCicloInverso_local;
          arregloPosiciones_local[1] = posicionCiclo_local;
          arregloPosiciones_local[2] = posicionPrimer_local;
          arregloPosiciones_local[3] = posicionUltimo_local;
          menorPosicion_local = ap.obtenerPrimerValorPositivoDeArreglo(arregloPosiciones_local);
          
          bloqueCiclos_local = mc.obtenerSubCadena(bloqueCiclos_local, menorPosicion_local, mc.obtenerLongitudCadena(bloqueCiclos_local));
          cadenaCiclo_local = obtenerCadenaCiclo(bloqueCiclos_local);
          if (!mc.esCadenaVacia(cadenaCiclo_local)) {
            ciclo_local = new Ciclo();
            ciclo_local.setGrupoInformacion(obtenerGrupoInformacionCiclo(cadenaCiclo_local));
            posicionFinal_local = mc.obtenerPosicionSubCadena(cadenaCiclo_local, "\r\n");
            if (posicionFinal_local <= mc.obtenerLongitudCadena(cadenaCiclo_local)) {
              cadenaCiclo_local = mc.obtenerSubCadena(cadenaCiclo_local, posicionFinal_local, mc.obtenerLongitudCadena(cadenaCiclo_local));
            }
            
            ciclo_local.setCondiciones(obtenerCondicionesCiclo(cadenaCiclo_local));
            ciclo_local.setSoloUnRegistro((menorPosicion_local == posicionPrimer_local || menorPosicion_local == posicionUltimo_local));
            ciclo_local.setOrdenDescendente((menorPosicion_local == posicionCicloInverso_local || menorPosicion_local == posicionUltimo_local));
            ciclo_local.setNumeroCiclo(numeroCiclo_local);
            listaCiclos_local.adicionar(ciclo_local);
            numeroCiclo_local++;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      ciclo_local = null;
      cadenaCiclo_local = null;
      bloqueCiclos_local = null;
    } 
    
    return listaCiclos_local;
  }
  private ListaCadenas obtenerListaAsignaciones() {
    ListaCadenas listaAsignaciones_local = null;
    int posicionIgual_local = -1;
    String asignaciones_local = null;
    String asignacion_local = null;
    String seudonimo_local = null;
    Iterator iterador_local = null;
    ListaCadenas listaAsignacionesInicial_local = null;
    
    try {
      asignaciones_local = getAsignaciones();
      listaAsignaciones_local = new ListaCadenas();
      listaAsignacionesInicial_local = mc.obtenerParrafoComoListaCadenas(asignaciones_local);
      if (listaAsignacionesInicial_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaAsignacionesInicial_local.iterator();
        while (iterador_local.hasNext()) {
          asignacion_local = (String)iterador_local.next();
          if (mc.verificarExistenciaSubCadena(asignacion_local, String.valueOf('='))) {
            posicionIgual_local = mc.obtenerPosicionSubCadena(asignacion_local, String.valueOf('='));
            seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, 0, posicionIgual_local));
            
            if (getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local) != ConstantesGeneral.VALOR_NULO) {
              listaAsignaciones_local.adicionar(asignacion_local);
            }
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      seudonimo_local = null;
      asignacion_local = null;
      asignaciones_local = null;
      listaAsignacionesInicial_local = null;
    } 
    
    return listaAsignaciones_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoRelacionAplicativos\Accion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */