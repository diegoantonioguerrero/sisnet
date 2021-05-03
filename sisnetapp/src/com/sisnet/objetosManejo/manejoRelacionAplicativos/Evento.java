package com.sisnet.objetosManejo.manejoRelacionAplicativos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaAcciones;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaCondiciones;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Accion;
import java.util.Iterator;
public class Evento
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private String aNombresEventos;
  private String aCondiciones;
  private String aAcciones;
  private ListaCadenas aListaNombresEventos;
  private ListaCondiciones aListaCondiciones;
  private ListaAcciones aListaAcciones;
  private boolean aRealizarAccionUsuario;
  private MotorAplicacion aMotorAplicacion;
  private GrupoInformacion aGrupoInformacion;
  private boolean aEventoValido;
  private int aNumeroEvento;
  public Evento() {
    setNombresEventos("");
    setCondiciones("");
    setAcciones("");
    setListaNombresEventos(null);
    setListaCondiciones(null);
    setListaAcciones(null);
    setMotorAplicacion(null);
    setEventoValido(true);
  }
  public String getNombresEventos() {
    return this.aNombresEventos;
  }
  public void setNombresEventos(String pNombresEventos) {
    this.aNombresEventos = pNombresEventos;
    if (pNombresEventos != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(pNombresEventos)) {
      setListaNombresEventos(obtenerListaNombresEventos());
    }
  }
  public String getCondiciones() {
    return this.aCondiciones;
  }
  public void setCondiciones(String pCondiciones) {
    this.aCondiciones = mc.borrarEspaciosLaterales(pCondiciones);
    if (pCondiciones != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(pCondiciones)) {
      setListaCondiciones(ap.obtenerListaCondicionesRelacionAplicativos(pCondiciones));
    }
  }
  public String getAcciones() {
    return this.aAcciones;
  }
  public void setAcciones(String pAcciones) {
    this.aAcciones = pAcciones;
    if (pAcciones != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(pAcciones)) {
      setListaAcciones(obtenerListaAcciones());
    }
  }
  public ListaCadenas getListaNombresEventos() {
    return this.aListaNombresEventos;
  }
  public void setListaNombresEventos(ListaCadenas pListaNombresEventos) {
    this.aListaNombresEventos = pListaNombresEventos;
  }
  public ListaCondiciones getListaCondiciones() {
    return this.aListaCondiciones;
  }
  public void setListaCondiciones(ListaCondiciones pListaCondiciones) {
    this.aListaCondiciones = pListaCondiciones;
  }
  public ListaAcciones getListaAcciones() {
    return this.aListaAcciones;
  }
  public void setListaAcciones(ListaAcciones pListaAcciones) {
    this.aListaAcciones = pListaAcciones;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  public boolean esRealizarAccionUsuario() {
    return this.aRealizarAccionUsuario;
  }
  public void setRealizarAccionUsuario(boolean pRealizarAccionUsuario) {
    this.aRealizarAccionUsuario = pRealizarAccionUsuario;
  }
  public GrupoInformacion getGrupoInformacion() {
    return this.aGrupoInformacion;
  }
  public void setGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    this.aGrupoInformacion = pGrupoInformacion;
  }
  public boolean esEventoValido() {
    return this.aEventoValido;
  }
  public void setEventoValido(boolean pEventoValido) {
    this.aEventoValido = pEventoValido;
  }
  public int getNumeroEvento() {
    return this.aNumeroEvento;
  }
  public void setNumeroEvento(int pNumeroEvento) {
    this.aNumeroEvento = pNumeroEvento;
  }
  private ListaCadenas obtenerListaNombresEventos() {
    ListaCadenas listaCadenas_local = null;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String nombresEventos_local = null;
    String nombreEvento_local = null;
    
    try {
      nombresEventos_local = getNombresEventos();
      if (mc.verificarExistenciaSubCadena(nombresEventos_local, String.valueOf('(')) && mc.verificarExistenciaSubCadena(nombresEventos_local, String.valueOf(')'))) {
        
        posicionInicial_local = mc.obtenerPosicionSubCadena(nombresEventos_local, String.valueOf('(')) + 1;
        
        posicionFinal_local = mc.obtenerPosicionSubCadena(nombresEventos_local, String.valueOf(')'));
        
        nombresEventos_local = mc.obtenerSubCadena(nombresEventos_local, posicionInicial_local, posicionFinal_local);
      } 
      nombresEventos_local = mc.concatenarCadena(nombresEventos_local, String.valueOf(','));
      listaCadenas_local = new ListaCadenas();
      while (mc.verificarExistenciaSubCadena(nombresEventos_local, String.valueOf(','))) {
        posicionInicial_local = 0;
        posicionFinal_local = mc.obtenerPosicionSubCadena(nombresEventos_local, String.valueOf(','));
        if (posicionInicial_local < posicionFinal_local) {
          nombreEvento_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(nombresEventos_local, posicionInicial_local, posicionFinal_local));
          
          if (!listaCadenas_local.verificarExistenciaCadena(nombreEvento_local)) {
            listaCadenas_local.adicionar(nombreEvento_local);
          }
        } 
        posicionInicial_local = posicionFinal_local + 1;
        posicionFinal_local = mc.obtenerLongitudCadena(nombresEventos_local);
        if (posicionInicial_local < posicionFinal_local) {
          nombresEventos_local = mc.obtenerSubCadena(nombresEventos_local, posicionInicial_local, posicionFinal_local); continue;
        } 
        nombresEventos_local = "";
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombresEventos_local = null;
    } 
    
    return listaCadenas_local;
  }
  private String obtenerAccionRealizar(String pContenidoAccion) {
    String accionRealizar_local = "";
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return accionRealizar_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$MENSAJEERROR")) {
        return "$MENSAJEERROR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$MENSAJENORMAL")) {
        return "$MENSAJENORMAL";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$ASIGNAR")) {
        return "$ASIGNAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$INCLUIRSINRECALCULAR")) {
        return "$INCLUIRSINRECALCULAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$INCLUIR")) {
        return "$INCLUIR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$MODIFICARSINRECALCULAR")) {
        return "$MODIFICARSINRECALCULAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$MODIFICAR")) {
        return "$MODIFICAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$BORRARSINRECALCULAR")) {
        return "$BORRARSINRECALCULAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$BORRAR")) {
        return "$BORRAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$ESPERAR")) {
        return "$ESPERAR";
      }
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$IRA")) {
        return "$IRA";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return accionRealizar_local;
  }
  private String obtenerBloqueCiclos(String pContenidoAccion, String pAccionRealizar) {
    String bloqueCiclos_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    int posicionCicloInverso_local = -1;
    int posicionCiclo_local = -1;
    int posicionPrimer_local = -1;
    int posicionUltimo_local = -1;
    int[] arregloPosiciones_local = new int[4];
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return bloqueCiclos_local;
    }
    if (pAccionRealizar == ConstantesGeneral.VALOR_NULO) {
      return bloqueCiclos_local;
    }
    if (mc.esCadenaVacia(pAccionRealizar)) {
      return bloqueCiclos_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$CINVERSO") || mc.verificarExistenciaSubCadena(pContenidoAccion, "$CICLO") || mc.verificarExistenciaSubCadena(pContenidoAccion, "$PRIMER") || mc.verificarExistenciaSubCadena(pContenidoAccion, "$ULTIMO"))
      {
        
        posicionCicloInverso_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$CINVERSO");
        posicionCiclo_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$CICLO");
        posicionPrimer_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$PRIMER");
        posicionUltimo_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$ULTIMO");
        arregloPosiciones_local[0] = posicionCicloInverso_local;
        arregloPosiciones_local[1] = posicionCiclo_local;
        arregloPosiciones_local[2] = posicionPrimer_local;
        arregloPosiciones_local[3] = posicionUltimo_local;
        posicionInicial_local = ap.obtenerPrimerValorPositivoDeArreglo(arregloPosiciones_local);
        posicionFinal_local = mc.obtenerPosicionSubCadena(pContenidoAccion, pAccionRealizar);
        if (posicionInicial_local < posicionFinal_local) {
          bloqueCiclos_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return bloqueCiclos_local;
  }
  private String obtenerBloqueAsignaciones(String pContenidoAccion, String pAccionRealizar) {
    String bloqueAsignaciones_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return bloqueAsignaciones_local;
    }
    if (pAccionRealizar == ConstantesGeneral.VALOR_NULO) {
      return bloqueAsignaciones_local;
    }
    if (mc.esCadenaVacia(pAccionRealizar)) {
      return bloqueAsignaciones_local;
    }
    
    try {
      posicionInicial_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", mc.obtenerPosicionSubCadena(pContenidoAccion, pAccionRealizar));
      
      if (posicionInicial_local != -1) {
        posicionInicial_local += mc.obtenerLongitudCadena("\r\n");
        posicionFinal_local = mc.obtenerLongitudCadena(pContenidoAccion);
        if (posicionInicial_local < posicionFinal_local) {
          bloqueAsignaciones_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return bloqueAsignaciones_local;
  }
  private GrupoInformacion obtenerGrupoInformacionAccion(String pContenidoAccion, String pAccionRealizar) {
    GrupoInformacion grupoInformacion_local = null;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    int posicionCiclo_local = -1;
    int posicionAlgun_local = -1;
    String descripcionGrupoInformacion_local = null;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    if (pAccionRealizar == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    if (mc.esCadenaVacia(pAccionRealizar)) {
      return grupoInformacion_local;
    }
    
    try {
      descripcionGrupoInformacion_local = "";
      posicionInicial_local = mc.obtenerPosicionSubCadena(pContenidoAccion, pAccionRealizar) + mc.obtenerLongitudCadena(pAccionRealizar);
      if (mc.verificarExistenciaSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local)) {
        
        posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
      } else {
        
        posicionFinal_local = mc.obtenerLongitudCadena(pContenidoAccion);
      } 
      
      if (posicionInicial_local < posicionFinal_local) {
        descripcionGrupoInformacion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
      }
      
      if (mc.esCadenaVacia(descripcionGrupoInformacion_local)) {
        posicionCiclo_local = mc.obtenerUltimaPosicionSubCadena(pContenidoAccion, "$CICLO");
        posicionAlgun_local = mc.obtenerUltimaPosicionSubCadena(pContenidoAccion, "$PRIMER");
        if (posicionCiclo_local != -1 && posicionCiclo_local > posicionAlgun_local) {
          posicionInicial_local = posicionCiclo_local + mc.obtenerLongitudCadena("$CICLO");
          posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
        
        }
        else if (posicionAlgun_local != -1) {
          posicionInicial_local = posicionAlgun_local + mc.obtenerLongitudCadena("$PRIMER");
          posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
        } else {
          
          posicionInicial_local = -1;
          posicionFinal_local = -1;
        } 
        
        if (posicionInicial_local < posicionFinal_local) {
          descripcionGrupoInformacion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
        }
      } 
      
      if (!mc.esCadenaVacia(descripcionGrupoInformacion_local)) {
        grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionRelacionAplicativos(descripcionGrupoInformacion_local);
      } else {
        grupoInformacion_local = getGrupoInformacion();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      descripcionGrupoInformacion_local = null;
    } 
    
    return grupoInformacion_local;
  }
  private String obtenerMensajeAccion(String pContenidoAccion) {
    String mensajeAccion_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    int posicionMensajeNormal_local = -1;
    int posicionMensajeError_local = -1;
    String mensaje_local = null;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return mensajeAccion_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$MENSAJENORMAL") || mc.verificarExistenciaSubCadena(pContenidoAccion, "$MENSAJEERROR")) {
        
        posicionMensajeNormal_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$MENSAJENORMAL");
        posicionMensajeError_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$MENSAJEERROR");
        if (posicionMensajeNormal_local != -1 && posicionMensajeNormal_local < posicionMensajeError_local) {
          
          posicionInicial_local = posicionMensajeNormal_local;
        }
        else if (posicionMensajeError_local != -1) {
          posicionInicial_local = posicionMensajeError_local;
        } else {
          posicionInicial_local = posicionMensajeNormal_local;
        } 
        
        posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
        
        if (posicionFinal_local == -1) {
          posicionFinal_local = mc.obtenerLongitudCadena(pContenidoAccion);
        }
        if (posicionInicial_local < posicionFinal_local) {
          mensaje_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
          
          if (mc.verificarExistenciaSubCadena(mensaje_local, String.valueOf('"'))) {
            posicionInicial_local = mc.obtenerPosicionSubCadena(mensaje_local, String.valueOf('"')) + 1;
            
            posicionFinal_local = mc.obtenerUltimaPosicionSubCadena(mensaje_local, String.valueOf('"'));
            
            if (posicionInicial_local < posicionFinal_local) {
              mensajeAccion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(mensaje_local, posicionInicial_local, posicionFinal_local));
            }
          }
        
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      mensaje_local = null;
    } 
    
    return mensajeAccion_local;
  }
  private int obtenerTiempoDeEsperaAccion(String pContenidoAccion, String pAccionRealizar) {
    int tiempoDeEspera_local = 0;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String cadenaTiempoDeEspera_local = null;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return tiempoDeEspera_local;
    }
    if (pAccionRealizar == ConstantesGeneral.VALOR_NULO) {
      return tiempoDeEspera_local;
    }
    if (mc.esCadenaVacia(pAccionRealizar)) {
      return tiempoDeEspera_local;
    }
    
    try {
      cadenaTiempoDeEspera_local = "";
      if (mc.sonCadenasIguales(pAccionRealizar, "$ESPERAR")) {
        posicionInicial_local = mc.obtenerPosicionSubCadena(pContenidoAccion, pAccionRealizar) + mc.obtenerLongitudCadena(pAccionRealizar);
        
        posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
        
        if (posicionFinal_local == -1) {
          posicionFinal_local = mc.obtenerLongitudCadena(pContenidoAccion);
        }
        if (posicionInicial_local < posicionFinal_local) {
          cadenaTiempoDeEspera_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
        }
      } 
      
      if (!mc.esCadenaVacia(cadenaTiempoDeEspera_local) && mc.esCadenaNumerica(cadenaTiempoDeEspera_local, true))
      {
        tiempoDeEspera_local = Integer.parseInt(cadenaTiempoDeEspera_local) * 1000;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cadenaTiempoDeEspera_local = null;
    } 
    
    return tiempoDeEspera_local;
  }
  private String obtenerEtiquetaAccion(String pContenidoAccion) {
    String etiquetaAccion_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return etiquetaAccion_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$ETIQUETA")) {
        posicionInicial_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$ETIQUETA") + mc.obtenerLongitudCadena("$ETIQUETA");
        
        posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
        
        if (posicionInicial_local < posicionFinal_local) {
          etiquetaAccion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return etiquetaAccion_local;
  }
  private String obtenerEtiquetaDestinoAccion(String pContenidoAccion) {
    String etiquetaDestinoAccion_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return etiquetaDestinoAccion_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pContenidoAccion, "$IRA")) {
        posicionInicial_local = mc.obtenerPosicionSubCadena(pContenidoAccion, "$IRA") + mc.obtenerLongitudCadena("$IRA");
        
        posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pContenidoAccion, "\r\n", posicionInicial_local);
        
        if (posicionFinal_local == -1) {
          posicionFinal_local = mc.obtenerLongitudCadena(pContenidoAccion);
        }
        if (posicionInicial_local < posicionFinal_local) {
          etiquetaDestinoAccion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pContenidoAccion, posicionInicial_local, posicionFinal_local));
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return etiquetaDestinoAccion_local;
  }
  private Accion descomponerAccion(String pContenidoAccion) {
    Accion accion_local = null;
    String contenidoAccion_local = null;
    String accionRealizar_local = null;
    
    if (pContenidoAccion == ConstantesGeneral.VALOR_NULO) {
      return accion_local;
    }
    
    try {
      contenidoAccion_local = pContenidoAccion;
      accion_local = new Accion();
      accion_local.setMotorAplicacion(getMotorAplicacion());
      accionRealizar_local = obtenerAccionRealizar(contenidoAccion_local);
      accion_local.setGrupoInformacion(obtenerGrupoInformacionAccion(pContenidoAccion, accionRealizar_local));
      accion_local.setAccionRealizar(accionRealizar_local);
      accion_local.setCiclos(obtenerBloqueCiclos(pContenidoAccion, accionRealizar_local));
      accion_local.setAsignaciones(obtenerBloqueAsignaciones(pContenidoAccion, accionRealizar_local));
      accion_local.setMensaje(obtenerMensajeAccion(pContenidoAccion));
      accion_local.setTiempoEspera(obtenerTiempoDeEsperaAccion(pContenidoAccion, accionRealizar_local));
      accion_local.setEtiqueta(obtenerEtiquetaAccion(pContenidoAccion));
      accion_local.setEtiquetaDestino(obtenerEtiquetaDestinoAccion(pContenidoAccion));
      accion_local.setActualizarInformacionRecalculable((!mc.sonCadenasIguales(accionRealizar_local, "$INCLUIRSINRECALCULAR") && !mc.sonCadenasIguales(accionRealizar_local, "$MODIFICARSINRECALCULAR") && !mc.sonCadenasIguales(accionRealizar_local, "$BORRARSINRECALCULAR")));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      accionRealizar_local = null;
      contenidoAccion_local = null;
    } 
    
    return accion_local;
  }
  private ListaAcciones obtenerListaAcciones() {
    ListaAcciones listaAcciones_local = null;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String acciones_local = null;
    String contenidoAccion_local = null;
    
    try {
      acciones_local = getAcciones();
      listaAcciones_local = new ListaAcciones();
      while (mc.verificarExistenciaSubCadena(acciones_local, "$INICIOBLOQUE") && mc.verificarExistenciaSubCadena(acciones_local, "$FINBLOQUE"))
      {
        posicionInicial_local = mc.obtenerPosicionSubCadena(acciones_local, "$INICIOBLOQUE") + mc.obtenerLongitudCadena("$INICIOBLOQUE");
        
        posicionFinal_local = mc.obtenerPosicionSubCadena(acciones_local, "$FINBLOQUE");
        if (posicionInicial_local < posicionFinal_local) {
          contenidoAccion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(acciones_local, posicionInicial_local, posicionFinal_local));
          
          listaAcciones_local.adicionar(descomponerAccion(contenidoAccion_local));
        } 
        posicionInicial_local = posicionFinal_local + mc.obtenerLongitudCadena("$FINBLOQUE");
        posicionFinal_local = mc.obtenerLongitudCadena(acciones_local);
        if (posicionInicial_local < posicionFinal_local) {
          acciones_local = mc.obtenerSubCadena(acciones_local, posicionInicial_local, posicionFinal_local); continue;
        } 
        acciones_local = "";
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      acciones_local = null;
    } 
    
    return listaAcciones_local;
  }
  public ListaAcciones obtenerListaAccionesDesdeEtiqueta(String pEtiqueta) {
    ListaAcciones listaAcciones_local = null;
    Iterator iterator_local = null;
    Accion accion_local = null;
    boolean continuar_local = false;
    
    if (pEtiqueta == ConstantesGeneral.VALOR_NULO) {
      return listaAcciones_local;
    }
    
    try {
      listaAcciones_local = new ListaAcciones();
      iterator_local = getListaAcciones().iterator();
      while (iterator_local.hasNext()) {
        accion_local = (Accion)iterator_local.next();
        if (mc.sonCadenasIguales(accion_local.getEtiqueta(), pEtiqueta)) {
          continuar_local = true;
        }
        if (continuar_local) {
          listaAcciones_local.adicionar(accion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      accion_local = null;
    } 
    
    return listaAcciones_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoRelacionAplicativos\Evento.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */