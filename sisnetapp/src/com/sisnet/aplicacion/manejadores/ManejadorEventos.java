package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.aplicacion.manejadores.ManejadorOperaciones;
import com.sisnet.aplicacion.manejadores.ManejadorPlantilla;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.ManejadorVariablesSistema;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorInformacionRecalculable;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaAcciones;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaCiclos;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaCondiciones;
import com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaEventos;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Accion;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Ciclo;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Evento;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.ExpresionCondicion;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Iterator;
public class ManejadorEventos
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorOperaciones op = ManejadorOperaciones.getManejadorOperaciones();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private String aNombreEvento;
  private GrupoInformacion aGrupoInformacion;
  private ListaEventos aListaEventos;
  private ListaCampo aListaCampo;
  private ListaCampo aListaCampoValoresAnteriores;
  private boolean aRealizarAccionUsuario;
  private Usuario aUsuario;
  private String aIdSesion;
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private ManejadorInformacionRecalculable aManejadorInformacionRecalculable;
  private ManejadorPlantilla aManejadorPlantilla;
  private MotorAplicacion aMotorAplicacion;
  private ManejadorVariablesSistema aManejadorVariablesSistema;
  private String aMensajeEventos;
  private int aTipoMensajeEventos;
  private int aValorLlaveGrupoPrincipal;
  private int aValorLlaveGrupoInformacion;
  private boolean aHuboError;
  private ListaCiclos aListaCiclos;
  private int aNumeroEvento;
  private boolean aHuboAsignacion;
  private boolean aHaFinalizadoEjecucion;
  public ManejadorEventos() {
    setNombreEvento("");
    setListaEventos(new ListaEventos());
    setListaCampo(null);
    setListaCampoValoresAnteriores(null);
    setRealizarAccionUsuario(false);
    setManejadorInformacionRecalculable(new ManejadorInformacionRecalculable());
    setManejadorPlantilla(new ManejadorPlantilla());
    setManejadorVariablesSistema(new ManejadorVariablesSistema());
    setMensajeEventos("");
    setValorLlaveGrupoPrincipal(-1);
    setValorLlaveGrupoInformacion(-1);
    setHuboError(false);
    setNumeroEvento(-1);
    setHuboAsignacion(false);
    setHaFinalizadoEjecucion(false);
  }
  public String getNombreEvento() {
    return this.aNombreEvento;
  }
  public void setNombreEvento(String pNombreEvento) {
    this.aNombreEvento = mc.convertirAMayusculas(mc.borrarEspaciosLaterales(pNombreEvento));
    if (getManejadorVariablesSistema() != ConstantesGeneral.VALOR_NULO) {
      getManejadorVariablesSistema().setNombreEvento(this.aNombreEvento);
    }
  }
  public GrupoInformacion getGrupoInformacion() {
    return this.aGrupoInformacion;
  }
  public void setGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    this.aGrupoInformacion = pGrupoInformacion;
    if (getMotorAplicacion() != ConstantesGeneral.VALOR_NULO && pGrupoInformacion != ConstantesGeneral.VALOR_NULO) {
      getListaEventos().borrarElementos();
      cargarListaEvento();
    } 
  }
  public ListaCampo getListaCampo() {
    return this.aListaCampo;
  }
  public void setListaCampo(ListaCampo pListaCampo) {
    this.aListaCampo = pListaCampo;
  }
  public ListaCampo getListaCampoValoresAnteriores() {
    return this.aListaCampoValoresAnteriores;
  }
  public void setListaCampoValoresAnteriores(ListaCampo pListaCampoValoresAnteriores) {
    this.aListaCampoValoresAnteriores = pListaCampoValoresAnteriores;
  }
  public boolean esRealizarAccionUsuario() {
    return this.aRealizarAccionUsuario;
  }
  public void setRealizarAccionUsuario(boolean pRealizarAccionUsuario) {
    this.aRealizarAccionUsuario = pRealizarAccionUsuario;
  }
  public ListaEventos getListaEventos() {
    return this.aListaEventos;
  }
  public void setListaEventos(ListaEventos pListaEventos) {
    this.aListaEventos = pListaEventos;
  }
  public Usuario getUsuario() {
    return this.aUsuario;
  }
  public void setUsuario(Usuario pUsuario) {
    this.aUsuario = pUsuario;
    getManejadorVariablesSistema().setUsuarioActual(pUsuario);
  }
  public String getIdSesion() {
    return this.aIdSesion;
  }
  public void setIdSesion(String pIdSesion) {
    this.aIdSesion = pIdSesion;
    getManejadorVariablesSistema().setIdSesion(pIdSesion);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
    getManejadorInformacionRecalculable().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
    getManejadorPlantilla().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
    getManejadorInformacionRecalculable().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
    getManejadorPlantilla().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
    getManejadorVariablesSistema().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
  }
  public ManejadorInformacionRecalculable getManejadorInformacionRecalculable() {
    return this.aManejadorInformacionRecalculable;
  }
  public void setManejadorInformacionRecalculable(ManejadorInformacionRecalculable pManejadorInformacionRecalculable) {
    this.aManejadorInformacionRecalculable = pManejadorInformacionRecalculable;
  }
  public ManejadorPlantilla getManejadorPlantilla() {
    return this.aManejadorPlantilla;
  }
  public void setManejadorPlantilla(ManejadorPlantilla pManejadorPlantilla) {
    this.aManejadorPlantilla = pManejadorPlantilla;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
    getManejadorInformacionRecalculable().setMotorAplicacion(pMotorAplicacion);
    getManejadorPlantilla().setMotorAplicacion(pMotorAplicacion);
    getManejadorVariablesSistema().setMotorAplicacion(pMotorAplicacion);
    if (pMotorAplicacion != ConstantesGeneral.VALOR_NULO && getGrupoInformacion() != ConstantesGeneral.VALOR_NULO) {
      getListaEventos().borrarElementos();
      cargarListaEvento();
    } 
  }
  public ManejadorVariablesSistema getManejadorVariablesSistema() {
    return this.aManejadorVariablesSistema;
  }
  public void setManejadorVariablesSistema(ManejadorVariablesSistema pManejadorVariablesSistema) {
    this.aManejadorVariablesSistema = pManejadorVariablesSistema;
  }
  public String getMensajeEventos() {
    return this.aMensajeEventos;
  }
  public void setMensajeEventos(String pMensajeEventos) {
    this.aMensajeEventos = pMensajeEventos;
  }
  public int getTipoMensajeEventos() {
    return this.aTipoMensajeEventos;
  }
  public void setTipoMensajeEventos(int pTipoMensajeEventos) {
    this.aTipoMensajeEventos = pTipoMensajeEventos;
  }
  public int getValorLlaveGrupoPrincipal() {
    return this.aValorLlaveGrupoPrincipal;
  }
  public void setValorLlaveGrupoPrincipal(int pValorLlaveGrupoPrincipal) {
    this.aValorLlaveGrupoPrincipal = pValorLlaveGrupoPrincipal;
  }
  public int getValorLlaveGrupoInformacion() {
    return this.aValorLlaveGrupoInformacion;
  }
  public void setValorLlaveGrupoInformacion(int pValorLlaveGrupoInformacion) {
    this.aValorLlaveGrupoInformacion = pValorLlaveGrupoInformacion;
  }
  public boolean esHuboError() {
    return this.aHuboError;
  }
  public void setHuboError(boolean pHuboError) {
    this.aHuboError = pHuboError;
  }
  public ListaCiclos getListaCiclos() {
    return this.aListaCiclos;
  }
  public void setListaCiclos(ListaCiclos pListaCiclos) {
    this.aListaCiclos = pListaCiclos;
  }
  public int getNumeroEvento() {
    return this.aNumeroEvento;
  }
  public void setNumeroEvento(int pNumeroEvento) {
    this.aNumeroEvento = pNumeroEvento;
  }
  public boolean esHuboAsignacion() {
    return this.aHuboAsignacion;
  }
  public void setHuboAsignacion(boolean pHuboAsignacion) {
    this.aHuboAsignacion = pHuboAsignacion;
  }
  public boolean haFinalizadoEjecucion() {
    return this.aHaFinalizadoEjecucion;
  }
  public void setHaFinalizadoEjecucion(boolean pHaFinalizadoEjecucion) {
    this.aHaFinalizadoEjecucion = pHaFinalizadoEjecucion;
  }
  private Evento descomponerEvento(String pEvento, boolean pRealizarAccionUsuario, int pNumeroEvento) {
    Evento evento_local = null;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String nombresEventos_local = null;
    String cadenaEvento_local = null;
    
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return evento_local;
    }
    
    try {
      cadenaEvento_local = pEvento;
      if (mc.verificarExistenciaSubCadena(cadenaEvento_local, "%EVENTO")) {
        posicionInicial_local = mc.obtenerPosicionSubCadena(cadenaEvento_local, "%EVENTO");
        posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(cadenaEvento_local, "\r\n", posicionInicial_local);
        
        evento_local = new Evento();
        evento_local.setMotorAplicacion(getMotorAplicacion());
        evento_local.setNumeroEvento(pNumeroEvento);
        if (posicionInicial_local < posicionFinal_local) {
          nombresEventos_local = mc.obtenerSubCadena(cadenaEvento_local, posicionInicial_local, posicionFinal_local);
          if (mc.verificarExistenciaSubCadena(nombresEventos_local, String.valueOf('='))) {
            evento_local.setNombresEventos(mc.borrarEspaciosLaterales(mc.obtenerSubCadena(nombresEventos_local, mc.obtenerPosicionSubCadena(nombresEventos_local, String.valueOf('=')) + 1, mc.obtenerLongitudCadena(nombresEventos_local))));
          }
          
          posicionInicial_local = posicionFinal_local + mc.obtenerLongitudCadena("\r\n");
          posicionFinal_local = mc.obtenerPosicionSubCadena(cadenaEvento_local, "$ACCION");
          if (posicionInicial_local < posicionFinal_local) {
            evento_local.setCondiciones(mc.obtenerSubCadena(cadenaEvento_local, posicionInicial_local, posicionFinal_local));
          }
          
          posicionInicial_local = posicionFinal_local;
          posicionFinal_local = mc.obtenerPosicionSubCadena(cadenaEvento_local, "$FINEVENTO");
          if (posicionInicial_local < posicionFinal_local) {
            evento_local.setAcciones(mc.obtenerSubCadena(cadenaEvento_local, posicionInicial_local, posicionFinal_local));
          }
          evento_local.setRealizarAccionUsuario(pRealizarAccionUsuario);
          evento_local.setGrupoInformacion(getGrupoInformacion());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombresEventos_local = null;
    } 
    
    return evento_local;
  }
  private ListaEventos cargarListaEventos(String pEventosAcciones) {
    ListaEventos listaEventos_local = null;
    int posicionInicioEvento_local = -1;
    int posicionFinEvento_local = -1;
    int contadorEventos_local = 0;
    String eventosAcciones_local = null;
    String evento_local = null;
    
    if (pEventosAcciones == ConstantesGeneral.VALOR_NULO) {
      return listaEventos_local;
    }
    
    try {
      eventosAcciones_local = pEventosAcciones;
      listaEventos_local = new ListaEventos();
      
      while (mc.verificarExistenciaSubCadena(eventosAcciones_local, "$EVENTO") && posicionInicioEvento_local <= posicionFinEvento_local) {
        posicionInicioEvento_local = mc.obtenerPosicionSubCadena(eventosAcciones_local, "$EVENTO");
        posicionFinEvento_local = mc.obtenerPosicionSubCadena(eventosAcciones_local, "$FINEVENTO") + mc.obtenerLongitudCadena("$FINEVENTO");
        
        if (posicionInicioEvento_local < posicionFinEvento_local) {
          contadorEventos_local++;
          evento_local = mc.obtenerSubCadena(eventosAcciones_local, posicionInicioEvento_local, posicionFinEvento_local);
          eventosAcciones_local = mc.obtenerSubCadena(eventosAcciones_local, posicionFinEvento_local, mc.obtenerLongitudCadena(eventosAcciones_local));
          
          listaEventos_local.adicionar(descomponerEvento(evento_local, (mc.obtenerPosicionSubCadena(eventosAcciones_local, "SIREALIZARACCIONUSUARIO") == -1), contadorEventos_local));
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      evento_local = null;
      eventosAcciones_local = null;
    } 
    
    return listaEventos_local;
  }
  private void mostrarEstructuraEvento(Evento pEvento) {
    Iterator iteradorNombres_local = null;
    ListaCadenas listaNombresEvento_local = null;
    Iterator iteradorCondiciones_local = null;
    ListaCondiciones listaCondiciones_local = null;
    ExpresionCondicion expresionCondicion_local = null;
    Iterator iteradorAcciones_local = null;
    ListaAcciones listaAcciones_local = null;
    Accion accion_local = null;
    GrupoInformacion grupoInformacionAccion_local = null;
    Iterator iteradorCiclos_local = null;
    ListaCiclos listaCiclos_local = null;
    Ciclo ciclo_local = null;
    GrupoInformacion grupoInformacionCiclo_local = null;
    Iterator iteradorCondicionesCiclo_local = null;
    ListaCondiciones listaCondicionesCiclo_local = null;
    ExpresionCondicion expresionCondicionCiclo_local = null;
    Iterator iteradorAsignaciones_local = null;
    ListaCadenas listaAsignaciones_local = null;
    
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      System.out.println("**************************************************************************");
      System.out.println("Evento Acci\u00f3n " + pEvento.getNumeroEvento() + ": ");
      listaNombresEvento_local = pEvento.getListaNombresEventos();
      if (listaNombresEvento_local != ConstantesGeneral.VALOR_NULO) {
        System.out.println("---> Lista de nombres de eventos");
        iteradorNombres_local = listaNombresEvento_local.iterator();
        while (iteradorNombres_local.hasNext()) {
          System.out.println("------> Nombre Evento: " + (String)iteradorNombres_local.next());
        }
      } 
      listaCondiciones_local = pEvento.getListaCondiciones();
      if (listaCondiciones_local != ConstantesGeneral.VALOR_NULO) {
        System.out.println("---> Lista de condiciones");
        iteradorCondiciones_local = listaCondiciones_local.iterator();
        while (iteradorCondiciones_local.hasNext()) {
          expresionCondicion_local = (ExpresionCondicion)iteradorCondiciones_local.next();
          System.out.println("------> Expresi\u00f3n: " + expresionCondicion_local.getExpresion() + "  Operador: " + expresionCondicion_local.getOperadorAndOr());
        } 
      } 
      
      listaAcciones_local = pEvento.getListaAcciones();
      if (listaAcciones_local != ConstantesGeneral.VALOR_NULO) {
        System.out.println("---> Lista de acciones");
        iteradorAcciones_local = listaAcciones_local.iterator();
        while (iteradorAcciones_local.hasNext()) {
          accion_local = (Accion)iteradorAcciones_local.next();
          System.out.println("------> Accion a realizar: " + accion_local.getAccionRealizar());
          System.out.println("------> Etiqueta: " + accion_local.getEtiqueta());
          grupoInformacionAccion_local = accion_local.getGrupoInformacion();
          if (grupoInformacionAccion_local != ConstantesGeneral.VALOR_NULO) {
            System.out.println("------> Grupo de informaci\u00f3n acci\u00f3n: " + grupoInformacionAccion_local.getNombreGrupoInformacion() + " aplicaci\u00f3n: " + grupoInformacionAccion_local.getAplicacion().getNombreAplicacion());
          }
          
          listaCiclos_local = accion_local.getListaCiclos();
          if (listaCiclos_local != ConstantesGeneral.VALOR_NULO) {
            System.out.println("------> Lista de ciclos");
            iteradorCiclos_local = listaCiclos_local.iterator();
            while (iteradorCiclos_local.hasNext()) {
              ciclo_local = (Ciclo)iteradorCiclos_local.next();
              grupoInformacionCiclo_local = ciclo_local.getGrupoInformacion();
              if (grupoInformacionCiclo_local != ConstantesGeneral.VALOR_NULO) {
                System.out.println("---------> Grupo de informaci\u00f3n ciclo: " + grupoInformacionCiclo_local.getNombreGrupoInformacion() + " aplicaci\u00f3n: " + grupoInformacionCiclo_local.getAplicacion().getNombreAplicacion());
              }
              
              listaCondicionesCiclo_local = ciclo_local.getListaCondiciones();
              if (listaCondicionesCiclo_local != ConstantesGeneral.VALOR_NULO) {
                System.out.println("---------> Lista de condiciones de ciclo");
                iteradorCondicionesCiclo_local = listaCondicionesCiclo_local.iterator();
                while (iteradorCondicionesCiclo_local.hasNext()) {
                  expresionCondicionCiclo_local = (ExpresionCondicion)iteradorCondicionesCiclo_local.next();
                  System.out.println("------------> Expresi\u00f3n: " + expresionCondicionCiclo_local.getExpresion() + "  Operador: " + expresionCondicionCiclo_local.getOperadorAndOr());
                } 
              } 
              
              System.out.println("---------> S\u00f3lo primer registro: " + ciclo_local.esSoloUnRegistro());
            } 
          } 
          listaAsignaciones_local = accion_local.getListaAsignaciones();
          if (listaAsignaciones_local != ConstantesGeneral.VALOR_NULO) {
            iteradorAsignaciones_local = listaAsignaciones_local.iterator();
            System.out.println("------> Lista de asignaciones");
            while (iteradorAsignaciones_local.hasNext()) {
              System.out.println("---------> " + (String)iteradorAsignaciones_local.next());
            }
          } 
          System.out.println("------> Mensaje Acci\u00f3n: " + accion_local.getMensaje());
          System.out.println("------> Tiempo de espera Acci\u00f3n: " + accion_local.getTiempoEspera());
        } 
      } 
      System.out.println("---> Realizar acci\u00f3n usuario: " + pEvento.esRealizarAccionUsuario());
      System.out.println("*****************************************************************");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      ciclo_local = null;
      accion_local = null;
      listaCiclos_local = null;
      listaAcciones_local = null;
      iteradorCiclos_local = null;
      iteradorNombres_local = null;
      listaCondiciones_local = null;
      listaAsignaciones_local = null;
      listaNombresEvento_local = null;
      expresionCondicion_local = null;
      iteradorCondiciones_local = null;
      iteradorAsignaciones_local = null;
      grupoInformacionCiclo_local = null;
      listaCondicionesCiclo_local = null;
      grupoInformacionAccion_local = null;
      expresionCondicionCiclo_local = null;
      iteradorCondicionesCiclo_local = null;
    } 
  }
  private void cargarListaEvento() {
    String eventosAcciones_local = null;
    
    try {
      eventosAcciones_local = mc.convertirAMayusculas(getGrupoInformacion().getAplicacion().getEventosAcciones());
      setListaEventos(cargarListaEventos(eventosAcciones_local));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      eventosAcciones_local = null;
    } 
  }
  private boolean verificarEsSeudonimo(String pCadenaSeudonimo) {
    boolean esSeudonimo_local = false;
    String seudonimo_local = null;
    
    if (pCadenaSeudonimo == ConstantesGeneral.VALOR_NULO) {
      return esSeudonimo_local;
    }
    
    try {
      seudonimo_local = pCadenaSeudonimo;
      if (mc.verificarExistenciaSubCadena(seudonimo_local, String.valueOf('#'))) {
        seudonimo_local = mc.obtenerSubCadena(seudonimo_local, 0, mc.obtenerPosicionSubCadena(seudonimo_local, String.valueOf('#')));
      
      }
      else if (mc.verificarExistenciaSubCadena(seudonimo_local, String.valueOf('*'))) {
        seudonimo_local = mc.obtenerSubCadena(seudonimo_local, 0, mc.obtenerPosicionSubCadena(seudonimo_local, String.valueOf('*')));
      } 
      
      esSeudonimo_local = (getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local) != ConstantesGeneral.VALOR_NULO);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      seudonimo_local = null;
    } 
    
    return esSeudonimo_local;
  }
  private String obtenerValorCampoListaCiclos(String pSeudonimoCampo, int pNumeroCiclo) {
    String valorCampoListaCiclos_local = "null";
    Iterator iterator_local = null;
    Ciclo ciclo_local = null;
    Campo campo_local = null;
    
    if (pSeudonimoCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoListaCiclos_local;
    }
    
    try {
      if (getListaCiclos() != ConstantesGeneral.VALOR_NULO) {
        iterator_local = getListaCiclos().iterator();
        while (iterator_local.hasNext()) {
          ciclo_local = (Ciclo)iterator_local.next();
          if (ciclo_local.getNumeroCiclo() < pNumeroCiclo) {
            if (ciclo_local.getListaCampos() != ConstantesGeneral.VALOR_NULO) {
              campo_local = ciclo_local.getListaCampos().obtenerCampoPorSeudonimo(pSeudonimoCampo);
              if (campo_local != ConstantesGeneral.VALOR_NULO && campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
              {
                valorCampoListaCiclos_local = campo_local.getValorCampo().toString();
              }
            }
          
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      ciclo_local = null;
      campo_local = null;
    } 
    
    return valorCampoListaCiclos_local;
  }
  private String obtenerValorOperandoPorSeudonimo(String pSeudonimo, int pNumeroCiclo) {
    String valorOperando_local = "";
    int posicionRegistro_local = -1;
    int valorLlave_local = -1;
    String seudonimo_local = null;
    String cadenaPosicionRegistro_local = null;
    String nombreLlave_local = null;
    Object valor_local = null;
    Campo campo_local = null;
    
    if (pSeudonimo == ConstantesGeneral.VALOR_NULO) {
      return valorOperando_local;
    }
    
    try {
      if (getListaCampo() != ConstantesGeneral.VALOR_NULO) {
        getListaCampo().cambiarFormatoCamposFecha();
      }
      if (getListaCampoValoresAnteriores() != ConstantesGeneral.VALOR_NULO) {
        getListaCampoValoresAnteriores().cambiarFormatoCamposFecha();
      }
      seudonimo_local = pSeudonimo;
      if (mc.verificarExistenciaSubCadena(seudonimo_local, String.valueOf('#'))) {
        cadenaPosicionRegistro_local = mc.obtenerSubCadena(pSeudonimo, mc.obtenerPosicionSubCadena(pSeudonimo, String.valueOf('#')) + 1, mc.obtenerLongitudCadena(pSeudonimo));
      }
      
      seudonimo_local = ap.obtenerSeudonimoValido(pSeudonimo);
      
      campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
      if (campo_local != ConstantesGeneral.VALOR_NULO) {
        if (mc.verificarExistenciaSubCadena(pSeudonimo, String.valueOf('#'))) {
          if (mc.esCadenaNumerica(cadenaPosicionRegistro_local, true)) {
            posicionRegistro_local = Integer.parseInt(cadenaPosicionRegistro_local);
          }
          else if (mc.sonCadenasIgualesIgnorarMayusculas(cadenaPosicionRegistro_local, String.valueOf('U'))) {
            
            posicionRegistro_local = getAdministradorBaseDatosAplicacion().obtenerCantidadRegistros(campo_local, obtenerValorLlavePrimariaPrincipal(campo_local.getGrupoInformacion(), pNumeroCiclo));
          } 
          
          valorOperando_local = getAdministradorBaseDatosAplicacion().obtenerValorCampoRegistroPorPosicion(campo_local, obtenerValorLlavePrimariaPrincipal(campo_local.getGrupoInformacion(), pNumeroCiclo), posicionRegistro_local, getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(campo_local.getGrupoInformacion()));
          
          return valorOperando_local;
        } 
        if (mc.verificarExistenciaSubCadena(pSeudonimo, String.valueOf('*'))) {
          if (getListaCampoValoresAnteriores().obtenerValorCampoPorSeudonimo(campo_local.getSeudonimo()) != ConstantesGeneral.VALOR_NULO)
          {
            valorOperando_local = getListaCampoValoresAnteriores().obtenerValorCampoPorSeudonimo(campo_local.getSeudonimo());
          }
          return valorOperando_local;
        } 
        if (getListaCiclos() != ConstantesGeneral.VALOR_NULO) {
          valorOperando_local = obtenerValorCampoListaCiclos(campo_local.getSeudonimo(), pNumeroCiclo);
          if (!mc.verificarCadenaNull(valorOperando_local)) {
            return valorOperando_local;
          }
        } 
        if (getListaCampo() != ConstantesGeneral.VALOR_NULO && getListaCampo().obtenerCampoPorSeudonimo(seudonimo_local) != ConstantesGeneral.VALOR_NULO) {
          
          valorOperando_local = getListaCampo().obtenerValorCampoPorSeudonimo(campo_local.getSeudonimo());
          return valorOperando_local;
        } 
        if (campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
          nombreLlave_local = ap.conformarNombreCampoLlavePrimaria(campo_local.getGrupoInformacion().getNombreGrupoInformacion());
          valorLlave_local = getValorLlaveGrupoInformacion();
        } else {
          nombreLlave_local = ap.conformarNombreCampoLlavePrimaria(campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion());
          
          valorLlave_local = getValorLlaveGrupoPrincipal();
        } 
        valor_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campo_local, nombreLlave_local, valorLlave_local);
        if (valor_local != ConstantesGeneral.VALOR_NULO) {
          valorOperando_local = valor_local.toString();
        }
        if (mc.verificarCadenaNull(valorOperando_local) && 
          campo_local.esTipoDatoTexto()) {
          valorOperando_local = "";
        }
        
        if (!mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "NN")) {
          valorOperando_local = mc.convertirAMayusculas(valorOperando_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valor_local = null;
      campo_local = null;
      seudonimo_local = null;
      nombreLlave_local = null;
      cadenaPosicionRegistro_local = null;
    } 
    
    return valorOperando_local;
  }
  private String obtenerValorOperando(String pOperando) {
    String valorOperando_local = "";
    Object valorVariable_local = null;
    Campo campo_local = null;
    
    if (pOperando == ConstantesGeneral.VALOR_NULO) {
      return valorOperando_local;
    }
    
    try {
      if (ap.esVariableSistema(pOperando)) {
        valorVariable_local = getManejadorVariablesSistema().obtenerValorVariableSistema(pOperando).getValorVariable();
        if (valorVariable_local != ConstantesGeneral.VALOR_NULO) {
          valorOperando_local = valorVariable_local.toString();
          campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(ap.obtenerSeudonimoValido(pOperando));
          if (campo_local != ConstantesGeneral.VALOR_NULO && (campo_local.esCampoEnlazado() || campo_local.esTipoDatoTabla())) {
            valorOperando_local = obtenerValorCampoTablaOEnlazadoComoTexto(campo_local, Integer.parseInt(valorOperando_local));
          }
          
          return valorOperando_local;
        } 
      } 
      if (mc.comienzaCon(pOperando, String.valueOf('"')) && mc.terminaCon(pOperando, String.valueOf('"')))
      {
        return mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pOperando, 1, mc.obtenerUltimaPosicionSubCadena(pOperando, String.valueOf('"'))));
      }
      
      if (mc.esCadenaNumerica(pOperando, true) || mc.esCadenaNumerica(pOperando, false))
      {
        return pOperando;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorVariable_local = null;
      campo_local = null;
    } 
    
    return valorOperando_local;
  }
  private String obtenerValorCampoTablaOEnlazadoComoTexto(Campo pCampo, int pIdValor) {
    String valorCampoTablaOEnlazadoComoTexto_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoTablaOEnlazadoComoTexto_local;
    }
    
    try {
      valorCampoTablaOEnlazadoComoTexto_local = String.valueOf(pIdValor);
      if (pCampo.esTipoDatoTabla()) {
        valorCampoTablaOEnlazadoComoTexto_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(pCampo.getFormatoCampo().getTipoDato())).getNombreTabla(), pIdValor);
      }
      
      if (pCampo.esCampoEnlazado()) {
        valorCampoTablaOEnlazadoComoTexto_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado().obtenerValorCampoEnlazado(pCampo.getEnlaceCampo().getEnlazado(), pIdValor);
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorCampoTablaOEnlazadoComoTexto_local;
  }
  private void interpretarListaCondicionesEvento(ListaCondiciones pListaCondiciones) {
    int posicionOperador_local = -1;
    String expresion_local = null;
    String operadorExpresion_local = null;
    String operandoUno_local = null;
    String operandoDos_local = null;
    Iterator iteradorCondiciones_local = null;
    ExpresionCondicion expresionCondicion_local = null;
    String seudonimo_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    boolean esOperandoUnoNumerico_local = false;
    boolean esOperandoDosNumerico_local = false;
    
    if (pListaCondiciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iteradorCondiciones_local = pListaCondiciones.iterator();
      while (iteradorCondiciones_local.hasNext()) {
        expresionCondicion_local = (ExpresionCondicion)iteradorCondiciones_local.next();
        expresion_local = expresionCondicion_local.getExpresion();
        operadorExpresion_local = ap.obtenerOperadorExpresion(expresion_local);
        if (!mc.esCadenaVacia(operadorExpresion_local)) {
          posicionOperador_local = mc.obtenerPosicionSubCadena(expresion_local, operadorExpresion_local);
          operandoUno_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(expresion_local, 0, posicionOperador_local));
          
          operandoDos_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(expresion_local, posicionOperador_local + mc.obtenerLongitudCadena(operadorExpresion_local), mc.obtenerLongitudCadena(expresion_local)));
          
          if (verificarEsSeudonimo(operandoDos_local)) {
            seudonimo_local = operandoDos_local;
            operandoDos_local = obtenerValorOperandoPorSeudonimo(operandoDos_local, -1);
            
            seudonimo_local = ap.obtenerSeudonimoValido(seudonimo_local);
            campoOrigenDos_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
            esOperandoDosNumerico_local = (campoOrigenDos_local.esTipoDatoNumerico() && !campoOrigenDos_local.esTipoDatoTabla() && !campoOrigenDos_local.esCampoEnlazado());
            
            if ((campoOrigenDos_local.esTipoDatoTabla() || campoOrigenDos_local.esCampoEnlazado()) && 
              !mc.esCadenaVacia(operandoDos_local) && mc.esCadenaNumerica(operandoDos_local, true))
            {
              operandoDos_local = obtenerValorCampoTablaOEnlazadoComoTexto(campoOrigenDos_local, Integer.parseInt(operandoDos_local));
            }
            
            if (mc.esCadenaVacia(operandoDos_local) && 
              campoOrigenDos_local.esTipoDatoNumerico() && !campoOrigenDos_local.esCampoEnlazado()) {
              operandoDos_local = String.valueOf(0);
            }
          } else {
            
            operandoDos_local = obtenerValorOperando(operandoDos_local);
            esOperandoDosNumerico_local = (mc.esCadenaNumerica(operandoDos_local, false) || mc.esCadenaNumerica(operandoDos_local, true));
          } 
          
          if (verificarEsSeudonimo(operandoUno_local)) {
            seudonimo_local = operandoUno_local;
            operandoUno_local = obtenerValorOperandoPorSeudonimo(seudonimo_local, -1);
            seudonimo_local = ap.obtenerSeudonimoValido(seudonimo_local);
            campoOrigenUno_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
            esOperandoUnoNumerico_local = (campoOrigenUno_local.esTipoDatoNumerico() && !campoOrigenUno_local.esTipoDatoTabla() && !campoOrigenUno_local.esCampoEnlazado());
            
            if ((campoOrigenUno_local.esTipoDatoTabla() || campoOrigenUno_local.esCampoEnlazado()) && 
              !mc.esCadenaVacia(operandoUno_local) && mc.esCadenaNumerica(operandoUno_local, true))
            {
              operandoUno_local = obtenerValorCampoTablaOEnlazadoComoTexto(campoOrigenUno_local, Integer.parseInt(operandoUno_local));
            }
            
            if (mc.esCadenaVacia(operandoUno_local) && 
              campoOrigenUno_local.esTipoDatoNumerico() && !campoOrigenUno_local.esCampoEnlazado()) {
              operandoUno_local = String.valueOf(0);
            
            }
          }
          else if (ap.esVariableSistema(operandoUno_local)) {
            seudonimo_local = mc.obtenerSubCadena(operandoUno_local, mc.obtenerPosicionSubCadena(operandoUno_local, String.valueOf('%')) + 1, mc.obtenerLongitudCadena(operandoUno_local));
            
            operandoUno_local = obtenerValorOperando(operandoUno_local);
            campoOrigenUno_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
            if (campoOrigenUno_local != ConstantesGeneral.VALOR_NULO) {
              esOperandoUnoNumerico_local = (campoOrigenUno_local.esTipoDatoNumerico() && !campoOrigenUno_local.esTipoDatoTabla() && !campoOrigenUno_local.esCampoEnlazado());
              
              if (campoOrigenUno_local != ConstantesGeneral.VALOR_NULO) {
                if ((campoOrigenUno_local.esTipoDatoTabla() || campoOrigenUno_local.esCampoEnlazado()) && mc.esCadenaNumerica(operandoUno_local, true))
                {
                  operandoUno_local = obtenerValorCampoTablaOEnlazadoComoTexto(campoOrigenUno_local, Integer.parseInt(operandoUno_local));
                }
                
                if (mc.esCadenaVacia(operandoUno_local) && 
                  campoOrigenUno_local.esTipoDatoNumerico() && !campoOrigenUno_local.esCampoEnlazado()) {
                  operandoUno_local = String.valueOf(0);
                }
              } 
            } 
          } else {
            
            operandoUno_local = obtenerValorOperando(operandoUno_local);
            esOperandoUnoNumerico_local = (mc.esCadenaNumerica(operandoUno_local, false) || mc.esCadenaNumerica(operandoUno_local, true));
          } 
          
          expresionCondicion_local.setOperandoIzquierda(operandoUno_local);
          expresionCondicion_local.setOperandoDerecha(operandoDos_local);
          expresionCondicion_local.setOperadorEvaluacion(operadorExpresion_local);
          expresionCondicion_local.setSonOperandosNumericos((esOperandoUnoNumerico_local && esOperandoDosNumerico_local));
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      expresion_local = null;
      operadorExpresion_local = null;
      expresionCondicion_local = null;
      iteradorCondiciones_local = null;
      seudonimo_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
  }
  private void interpretarEvento(Evento pEvento) {
    ListaCondiciones listaCondiciones_local = null;
    
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCondiciones_local = pEvento.getListaCondiciones();
      interpretarListaCondicionesEvento(listaCondiciones_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCondiciones_local = null;
    } 
  }
  private boolean evaluarCondicion(String pOperandoIzquierda, String pOperandoDerecha, String pOperador, boolean pSonOperandoNumericos) {
    boolean resultadoCondicion_local = false;
    
    if (pOperandoIzquierda == ConstantesGeneral.VALOR_NULO) {
      return resultadoCondicion_local;
    }
    if (pOperandoDerecha == ConstantesGeneral.VALOR_NULO) {
      return resultadoCondicion_local;
    }
    if (pOperador == ConstantesGeneral.VALOR_NULO) {
      return resultadoCondicion_local;
    }
    
    try {
      if (pSonOperandoNumericos) {
        if (mc.sonCadenasIguales(pOperador, String.valueOf('='))) {
          return ap.esNumeroIgual(Double.parseDouble(pOperandoIzquierda), Double.parseDouble(pOperandoDerecha));
        }
        if (mc.sonCadenasIguales(pOperador, "NOIGUALA")) {
          return !ap.esNumeroIgual(Double.parseDouble(pOperandoIzquierda), Double.parseDouble(pOperandoDerecha));
        }
        if (mc.sonCadenasIguales(pOperador, "MENORQUE")) {
          return ap.esNumeroMenor(Double.parseDouble(pOperandoIzquierda), Double.parseDouble(pOperandoDerecha));
        }
        if (mc.sonCadenasIguales(pOperador, "MAYORQUE")) {
          return ap.esNumeroMayor(Double.parseDouble(pOperandoIzquierda), Double.parseDouble(pOperandoDerecha));
        }
        if (mc.sonCadenasIguales(pOperador, "MENOROIGUALA")) {
          return ap.esNumeroMenorIgual(Double.parseDouble(pOperandoIzquierda), Double.parseDouble(pOperandoDerecha));
        }
        if (mc.sonCadenasIguales(pOperador, "MAYOROIGUALA")) {
          return ap.esNumeroMayorIgual(Double.parseDouble(pOperandoIzquierda), Double.parseDouble(pOperandoDerecha));
        }
      } else {
        if (mc.sonCadenasIguales(pOperador, String.valueOf('='))) {
          return mc.sonCadenasIguales(pOperandoIzquierda, pOperandoDerecha);
        }
        if (mc.sonCadenasIguales(pOperador, "NOIGUALA")) {
          return !mc.sonCadenasIguales(pOperandoIzquierda, pOperandoDerecha);
        }
        if (mc.sonCadenasIguales(pOperador, "MENORQUE")) {
          return mc.esCadenaMenorQue(pOperandoIzquierda, pOperandoDerecha);
        }
        if (mc.sonCadenasIguales(pOperador, "MAYORQUE")) {
          return mc.esCadenaMayorQue(pOperandoIzquierda, pOperandoDerecha);
        }
        if (mc.sonCadenasIguales(pOperador, "MENOROIGUALA")) {
          return mc.esCadenaMenorOIgualA(pOperandoIzquierda, pOperandoDerecha);
        }
        if (mc.sonCadenasIguales(pOperador, "MAYOROIGUALA")) {
          return mc.esCadenaMayorOIgualA(pOperandoIzquierda, pOperandoDerecha);
        }
      } 
      if (mc.sonCadenasIguales(pOperador, "ESCONTENIDOPOR")) {
        return mc.esContenidoPor(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "ESCONTENIDOPORPERONOIGUALA")) {
        return mc.esContenidoPorPeroNoIgualA(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "NOESCONTENIDOPOR")) {
        return !mc.esContenidoPor(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "COMIENZACON")) {
        return mc.comienzaCon(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "COMIENZACONPERONOIGUALA")) {
        return mc.comienzaConPeroNoIgualA(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "NOCOMIENZACON")) {
        return !mc.comienzaCon(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "CONTIENEA")) {
        return mc.contieneA(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "CONTIENEAPERONOIGUALA")) {
        return mc.contieneAPeroNoIgualA(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "NOCONTIENEA")) {
        return !mc.contieneA(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "ESCOMIENZODE")) {
        return mc.esComienzoDe(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "ESCOMIENZODEPERONOIGUALA")) {
        return mc.esComienzoDePeroNoIgualA(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "NOESCOMIENZODE")) {
        return !mc.esComienzoDe(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "CONTIENEPALABRAS")) {
        return mc.contienePalabras(pOperandoIzquierda, pOperandoDerecha);
      }
      if (mc.sonCadenasIguales(pOperador, "NOCONTIENEPALABRAS")) {
        return mc.noContienePalabras(pOperandoIzquierda, pOperandoDerecha);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return resultadoCondicion_local;
  }
  private void evaluarCondicionesEvento(Evento pEvento) {
    ExpresionCondicion expresionCondicion_local = null;
    Iterator iterator_local = null;
    boolean resultadoCondiciones_local = false;
    String operador_local = "";
    
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pEvento.getListaCondiciones() == ConstantesGeneral.VALOR_NULO) {
        return;
      }
      if (pEvento.getListaCondiciones().contarElementos() == 0) {
        return;
      }
      if (pEvento.getListaCondiciones() != ConstantesGeneral.VALOR_NULO) {
        iterator_local = pEvento.getListaCondiciones().iterator();
        while (iterator_local.hasNext()) {
          expresionCondicion_local = (ExpresionCondicion)iterator_local.next();
          expresionCondicion_local.setCondicionValida(evaluarCondicion(expresionCondicion_local.getOperandoIzquierda(), expresionCondicion_local.getOperandoDerecha(), expresionCondicion_local.getOperadorEvaluacion(), expresionCondicion_local.sonOperandosNumericos()));
        } 
        
        iterator_local = null;
        operador_local = "or";
        
        iterator_local = pEvento.getListaCondiciones().iterator();
        while (iterator_local.hasNext()) {
          expresionCondicion_local = (ExpresionCondicion)iterator_local.next();
          if (mc.sonCadenasIguales(operador_local, "or")) {
            resultadoCondiciones_local = (resultadoCondiciones_local || expresionCondicion_local.esCondicionValida());
          }
          if (mc.sonCadenasIguales(operador_local, "and")) {
            resultadoCondiciones_local = (resultadoCondiciones_local && expresionCondicion_local.esCondicionValida());
          }
          operador_local = expresionCondicion_local.getOperadorAndOr();
        } 
        pEvento.setEventoValido(resultadoCondiciones_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      expresionCondicion_local = null;
    } 
  }
  private boolean verificarEsOperacionAsignacion(String pValorAsignacion) {
    boolean esOperacionAsignacion_local = false;
    
    if (pValorAsignacion == ConstantesGeneral.VALOR_NULO) {
      return esOperacionAsignacion_local;
    }
    
    try {
      esOperacionAsignacion_local = (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADD ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADI ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " RES ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " SUB ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " MUL ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " DIV ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDESPACIO ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADIESPACIO ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDGUION ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADIGUION ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADICAMBIODERENGLON ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDCAMBIODERENGLON "));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esOperacionAsignacion_local;
  }
  private String obtenerOperacionAsignacion(String pValorAsignacion) {
    String operacionAsignacion_local = "";
    
    if (pValorAsignacion == ConstantesGeneral.VALOR_NULO) {
      return operacionAsignacion_local;
    }
    
    try {
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADI ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADD "))
      {
        return " ADI ";
      }
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " RES ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " SUB "))
      {
        return " RES ";
      }
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDESPACIO ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADIESPACIO "))
      {
        return " ADIESPACIO ";
      }
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDGUION ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADIGUION "))
      {
        return " ADIGUION ";
      }
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDCAMBIODERENGLON ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " ADICAMBIODERENGLON "))
      {
        return " ADICAMBIODERENGLON ";
      }
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " MUL ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " MUL "))
      {
        return " MUL ";
      }
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " DIV ") || mc.verificarExistenciaSubCadena(pValorAsignacion, " DIV "))
      {
        return " DIV ";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return operacionAsignacion_local;
  }
  private int obtenerPosicionOperador(String pValorAsignacion) {
    int posicionOperador_local = -1;
    
    try {
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADI ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADI ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADD ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADD ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADIESPACIO ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADIESPACIO ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDESPACIO ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADDESPACIO ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADIGUION ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADIGUION ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDGUION ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADDGUION ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADICAMBIODERENGLON ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADICAMBIODERENGLON ");
        
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " ADDCAMBIODERENGLON ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " ADDCAMBIODERENGLON ");
        
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " RES ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " RES ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " SUB ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " SUB ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " MUL ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " MUL ");
        return posicionOperador_local;
      } 
      if (mc.verificarExistenciaSubCadena(pValorAsignacion, " DIV ")) {
        posicionOperador_local = mc.obtenerPosicionSubCadena(pValorAsignacion, " DIV ");
        return posicionOperador_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return posicionOperador_local;
  }
  private String obtenerValorRedondeadoComoTexto(String pValorOperando, int pNumeroDecimales) {
    String valorRedondeadoComoTexto_local = "";
    
    if (pValorOperando == ConstantesGeneral.VALOR_NULO) {
      return valorRedondeadoComoTexto_local;
    }
    
    try {
      valorRedondeadoComoTexto_local = op.redondearNumero(BigDecimal.valueOf(Double.valueOf(pValorOperando).doubleValue()), pNumeroDecimales).toString();
      
      if (pNumeroDecimales == 0) {
        valorRedondeadoComoTexto_local = String.valueOf((int)Double.parseDouble(valorRedondeadoComoTexto_local));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorRedondeadoComoTexto_local;
  }
  private String obtenerValorOperandoPorOperacionAsignacion(String pValorAsignacion, int pNumeroDecimales, int pNumeroCiclo) {
    String valorOperando_local = "";
    int posicionOperador_local = -1;
    int longitudResultado_local = 0;
    boolean esNumericoTipoDatoIzquierda_local = false;
    boolean esNumericoTipoDatoDerecha_local = false;
    boolean esFechaTipoDatoIzquierda_local = false;
    boolean esFechaTipoDatoDerecha_local = false;
    String operacion_local = null;
    String operandoIzquierda_local = null;
    String operandoDerecha_local = null;
    String valorOperandoIzquierda_local = null;
    String valorOperandoDerecha_local = null;
    Campo campoIzquierda_local = null;
    Campo campoDerecha_local = null;
    
    if (pValorAsignacion == ConstantesGeneral.VALOR_NULO) {
      return valorOperando_local;
    }
    
    try {
      if (verificarEsOperacionAsignacion(pValorAsignacion)) {
        operacion_local = obtenerOperacionAsignacion(pValorAsignacion);
        posicionOperador_local = obtenerPosicionOperador(pValorAsignacion);
        operandoIzquierda_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pValorAsignacion, 0, posicionOperador_local));
        
        valorOperandoIzquierda_local = obtenerValorOperando(operandoIzquierda_local);
        if (mc.esCadenaVacia(valorOperandoIzquierda_local) && verificarEsSeudonimo(operandoIzquierda_local)) {
          valorOperandoIzquierda_local = obtenerValorOperandoPorSeudonimo(operandoIzquierda_local, pNumeroCiclo);
          campoIzquierda_local = getMotorAplicacion().obtenerCampoPorSeudonimo(operandoIzquierda_local);
          if (mc.esCadenaNumerica(valorOperandoIzquierda_local, true) && (campoIzquierda_local.esCampoEnlazado() || campoIzquierda_local.esTipoDatoTabla()))
          {
            valorOperandoIzquierda_local = obtenerValorCampoTablaOEnlazadoComoTexto(campoIzquierda_local, Integer.parseInt(valorOperandoIzquierda_local));
          }
        } 
        
        if (campoIzquierda_local != ConstantesGeneral.VALOR_NULO) {
          esNumericoTipoDatoIzquierda_local = campoIzquierda_local.esTipoDatoNumerico();
          esFechaTipoDatoIzquierda_local = mc.sonCadenasIgualesIgnorarMayusculas("F", campoIzquierda_local.getFormatoCampo().getTipoDato());
        } else {
          
          esNumericoTipoDatoIzquierda_local = (mc.esCadenaNumerica(valorOperandoIzquierda_local, true) || mc.esCadenaNumerica(valorOperandoIzquierda_local, false));
          
          esFechaTipoDatoIzquierda_local = mc.verificarFormatoFecha(valorOperandoIzquierda_local);
        } 
        posicionOperador_local += mc.obtenerLongitudCadena(operacion_local);
        operandoDerecha_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pValorAsignacion, posicionOperador_local, mc.obtenerLongitudCadena(pValorAsignacion)));
        
        valorOperandoDerecha_local = obtenerValorOperando(operandoDerecha_local);
        if (mc.esCadenaVacia(valorOperandoDerecha_local) && verificarEsSeudonimo(operandoDerecha_local)) {
          valorOperandoDerecha_local = obtenerValorOperandoPorSeudonimo(operandoDerecha_local, pNumeroCiclo);
          campoDerecha_local = getMotorAplicacion().obtenerCampoPorSeudonimo(operandoDerecha_local);
          if (mc.esCadenaNumerica(valorOperandoDerecha_local, true) && (campoDerecha_local.esCampoEnlazado() || campoDerecha_local.esTipoDatoTabla()))
          {
            valorOperandoDerecha_local = obtenerValorCampoTablaOEnlazadoComoTexto(campoDerecha_local, Integer.parseInt(valorOperandoDerecha_local));
          }
        } 
        
        if (campoDerecha_local != ConstantesGeneral.VALOR_NULO) {
          esNumericoTipoDatoDerecha_local = campoDerecha_local.esTipoDatoNumerico();
          esFechaTipoDatoDerecha_local = mc.sonCadenasIgualesIgnorarMayusculas("F", campoDerecha_local.getFormatoCampo().getTipoDato());
        } else {
          
          esNumericoTipoDatoDerecha_local = (mc.esCadenaNumerica(valorOperandoDerecha_local, true) || mc.esCadenaNumerica(valorOperandoDerecha_local, false));
          
          esFechaTipoDatoDerecha_local = mc.verificarFormatoFecha(valorOperandoDerecha_local);
        } 
        if (mc.sonCadenasIguales(operacion_local, " RES ") && !esNumericoTipoDatoIzquierda_local && !esNumericoTipoDatoDerecha_local && !esFechaTipoDatoIzquierda_local && !esNumericoTipoDatoDerecha_local && !esFechaTipoDatoIzquierda_local && !esFechaTipoDatoDerecha_local)
        {
          
          return valorOperando_local;
        }
        if (mc.sonCadenasIguales(operacion_local, " ADI ")) {
          if (esNumericoTipoDatoIzquierda_local && esNumericoTipoDatoDerecha_local) {
            valorOperando_local = op.sumar(Double.parseDouble(valorOperandoIzquierda_local), Double.parseDouble(valorOperandoDerecha_local)).toString();
            
            valorOperando_local = obtenerValorRedondeadoComoTexto(valorOperando_local, pNumeroDecimales);
            return valorOperando_local;
          } 
          if (esFechaTipoDatoIzquierda_local && esNumericoTipoDatoDerecha_local) {
            valorOperandoIzquierda_local = mc.reemplazarCaracter(valorOperandoIzquierda_local, '/', '-');
            
            valorOperandoDerecha_local = op.redondearNumeroHaciaAbajo(BigDecimal.valueOf(Double.parseDouble(valorOperandoDerecha_local)), 0).toString();
            
            valorOperando_local = String.valueOf(mf.sumarDiasFecha(Date.valueOf(valorOperandoIzquierda_local), Integer.parseInt(valorOperandoDerecha_local)));
            
            return valorOperando_local;
          } 
          valorOperando_local = mc.concatenarCadena(valorOperandoIzquierda_local, valorOperandoDerecha_local);
          return valorOperando_local;
        } 
        if (mc.sonCadenasIguales(operacion_local, " ADIESPACIO ")) {
          longitudResultado_local = mc.obtenerLongitudCadena(mc.concatenarCadena(valorOperandoIzquierda_local, valorOperandoDerecha_local)) + 1;
          
          valorOperando_local = op.concatenar(valorOperandoIzquierda_local, valorOperandoDerecha_local, String.valueOf(' '), longitudResultado_local);
          
          return valorOperando_local;
        } 
        if (mc.sonCadenasIguales(operacion_local, " ADIGUION ")) {
          longitudResultado_local = mc.obtenerLongitudCadena(mc.concatenarCadena(valorOperandoIzquierda_local, valorOperandoDerecha_local)) + 1;
          
          valorOperando_local = op.concatenar(valorOperandoIzquierda_local, valorOperandoDerecha_local, String.valueOf('-'), longitudResultado_local);
          
          return valorOperando_local;
        } 
        if (mc.sonCadenasIguales(operacion_local, " ADICAMBIODERENGLON ")) {
          longitudResultado_local = mc.obtenerLongitudCadena(mc.concatenarCadena(valorOperandoIzquierda_local, valorOperandoDerecha_local)) + mc.obtenerLongitudCadena("\r\n");
          
          valorOperando_local = op.concatenar(valorOperandoIzquierda_local, valorOperandoDerecha_local, "\r\n", longitudResultado_local);
          
          return valorOperando_local;
        } 
        if (mc.sonCadenasIguales(operacion_local, " RES ")) {
          if (esNumericoTipoDatoIzquierda_local && esNumericoTipoDatoDerecha_local) {
            valorOperando_local = op.restar(Double.parseDouble(valorOperandoIzquierda_local), Double.parseDouble(valorOperandoDerecha_local)).toString();
            
            valorOperando_local = obtenerValorRedondeadoComoTexto(valorOperando_local, pNumeroDecimales);
            return valorOperando_local;
          } 
          if (esFechaTipoDatoIzquierda_local && esNumericoTipoDatoDerecha_local) {
            valorOperandoIzquierda_local = mc.reemplazarCaracter(valorOperandoIzquierda_local, '/', '-');
            
            valorOperandoDerecha_local = op.redondearNumeroHaciaAbajo(BigDecimal.valueOf(Double.parseDouble(valorOperandoDerecha_local)), 0).toString();
            
            valorOperando_local = String.valueOf(mf.restarDiasFecha(Date.valueOf(valorOperandoIzquierda_local), Integer.parseInt(valorOperandoDerecha_local)));
            
            return valorOperando_local;
          } 
          if (esFechaTipoDatoIzquierda_local && esFechaTipoDatoDerecha_local) {
            valorOperandoIzquierda_local = mc.reemplazarCaracter(valorOperandoIzquierda_local, '/', '-');
            
            valorOperandoDerecha_local = mc.reemplazarCaracter(valorOperandoDerecha_local, '/', '-');
            
            valorOperando_local = String.valueOf(mf.restarFechas(Date.valueOf(valorOperandoIzquierda_local), Date.valueOf(valorOperandoDerecha_local)));
            
            return valorOperando_local;
          } 
        } 
        if (mc.sonCadenasIguales(operacion_local, " MUL ") && 
          esNumericoTipoDatoIzquierda_local && esNumericoTipoDatoDerecha_local) {
          valorOperando_local = op.multiplicar(Double.parseDouble(valorOperandoIzquierda_local), Double.parseDouble(valorOperandoDerecha_local)).toString();
          
          valorOperando_local = obtenerValorRedondeadoComoTexto(valorOperando_local, pNumeroDecimales);
          return valorOperando_local;
        } 
        
        if (mc.sonCadenasIguales(operacion_local, " DIV ") && 
          esNumericoTipoDatoIzquierda_local && esNumericoTipoDatoDerecha_local) {
          valorOperando_local = op.dividir(Double.parseDouble(valorOperandoIzquierda_local), Double.parseDouble(valorOperandoDerecha_local)).toString();
          
          valorOperando_local = obtenerValorRedondeadoComoTexto(valorOperando_local, pNumeroDecimales);
          return valorOperando_local;
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      operacion_local = null;
      campoDerecha_local = null;
      campoIzquierda_local = null;
      operandoDerecha_local = null;
      operandoIzquierda_local = null;
      valorOperandoDerecha_local = null;
      valorOperandoIzquierda_local = null;
    } 
    
    return valorOperando_local;
  }
  public String obtenerValorRealAsignacion(String pAsignacion, int pNumeroCiclo) {
    String valorRealAsignacion_local = "";
    Campo campo_local = null;
    Tabla tabla_local = null;
    int posicionIgual_local = -1;
    String seudonimo_local = null;
    String valorAsignacion_local = null;
    Campo campoOperandoPorSeudonimo_local = null;
    boolean esCampoTablaOEnlazado_local = false;
    
    if (pAsignacion == ConstantesGeneral.VALOR_NULO) {
      return valorRealAsignacion_local;
    }
    
    try {
      posicionIgual_local = mc.obtenerPosicionSubCadena(pAsignacion, String.valueOf('='));
      seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pAsignacion, 0, posicionIgual_local));
      
      valorAsignacion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pAsignacion, posicionIgual_local + 1, mc.obtenerLongitudCadena(pAsignacion)));
      
      valorRealAsignacion_local = obtenerValorOperando(valorAsignacion_local);
      if (mc.esCadenaVacia(valorRealAsignacion_local) && !verificarEsOperacionAsignacion(valorAsignacion_local)) {
        valorRealAsignacion_local = obtenerValorOperandoPorSeudonimo(valorAsignacion_local, pNumeroCiclo);
        campoOperandoPorSeudonimo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(ap.obtenerSeudonimoValido(valorAsignacion_local));
        
        if (campoOperandoPorSeudonimo_local != ConstantesGeneral.VALOR_NULO) {
          esCampoTablaOEnlazado_local = (campoOperandoPorSeudonimo_local.esCampoEnlazado() || campoOperandoPorSeudonimo_local.esTipoDatoTabla());
        }
      } 
      
      campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
      if (campo_local != ConstantesGeneral.VALOR_NULO) {
        if (mc.esCadenaVacia(valorRealAsignacion_local)) {
          valorRealAsignacion_local = obtenerValorOperandoPorOperacionAsignacion(valorAsignacion_local, campo_local.getFormatoCampo().getNumeroDecimales(), pNumeroCiclo);
        }
        
        if (campo_local.esCampoEnlazado() && !mc.esCadenaNumerica(valorRealAsignacion_local, true)) {
          valorRealAsignacion_local = String.valueOf(getManejadorPlantilla().getManejadorCampoEnlazado().obtenerIdValorCampoEnlazado(campo_local.getEnlaceCampo().getEnlazado(), valorRealAsignacion_local));
        }
        
        if (campo_local.esTipoDatoTabla() && !mc.esCadenaNumerica(valorRealAsignacion_local, true)) {
          tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(campo_local.getFormatoCampo().getTipoDato()));
          
          valorRealAsignacion_local = String.valueOf(getAdministradorBaseDatosAplicacion().obtenerIdValorTabla(tabla_local.getNombreTabla(), valorRealAsignacion_local));
        } 
        
        if (campo_local.esTipoDatoNumerico()) {
          if (mc.esCadenaNumerica(valorRealAsignacion_local, false)) {
            valorRealAsignacion_local = op.redondearNumeroHaciaAbajo(BigDecimal.valueOf(Double.parseDouble(valorRealAsignacion_local)), campo_local.getFormatoCampo().getNumeroDecimales()).toString();
          } else {
            
            valorRealAsignacion_local = String.valueOf(0);
          } 
        }
        if (campo_local.esTipoDatoTexto()) {
          if (esCampoTablaOEnlazado_local) {
            valorRealAsignacion_local = obtenerValorCampoTablaOEnlazadoComoTexto(campoOperandoPorSeudonimo_local, Integer.parseInt(valorRealAsignacion_local));
          }
          
          valorRealAsignacion_local = mc.truncarCadena(valorRealAsignacion_local, campo_local.getFormatoCampo().getLongitudCampo());
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      tabla_local = null;
      seudonimo_local = null;
      valorAsignacion_local = null;
      campoOperandoPorSeudonimo_local = null;
    } 
    
    return valorRealAsignacion_local;
  }
  private String obtenerComparadorBasicoEquivalenteSQLCiclo(String pComparador) {
    String comparadorSQL_local = "";
    
    if (pComparador == ConstantesGeneral.VALOR_NULO) {
      return comparadorSQL_local;
    }
    
    try {
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, String.valueOf('='))) {
        return " = ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOIGUALA")) {
        return " <> ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MENORQUE")) {
        return " < ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MAYORQUE")) {
        return " > ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MENOROIGUALA")) {
        return " <= ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MAYOROIGUALA")) {
        return " >= ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "CONTIENEA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "COMIENZACON") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "COMIENZACONPERONOIGUALA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "CONTIENEAPERONOIGUALA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "CONTIENEPALABRAS"))
      {
        
        return " like ";
      }
      if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOCONTIENEA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOCOMIENZACON") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOCONTIENEPALABRAS"))
      {
        
        return " not like ";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return comparadorSQL_local;
  }
  private String conformarCondicionFiltradoSQLCiclo(Campo pCampo, String pComparador, String pValorCondicion) {
    String condicionSQL_local = "";
    String comparador_local = null;
    String valor_local = null;
    String nombreCompuestoCampo_local = null;
    boolean esTipoDatoTexto_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return condicionSQL_local;
    }
    if (pComparador == ConstantesGeneral.VALOR_NULO) {
      return condicionSQL_local;
    }
    if (pValorCondicion == ConstantesGeneral.VALOR_NULO) {
      return condicionSQL_local;
    }
    
    try {
      esTipoDatoTexto_local = pCampo.esTipoDatoTexto();
      nombreCompuestoCampo_local = pCampo.conformarNombreCompuestoCampo();
      comparador_local = obtenerComparadorBasicoEquivalenteSQLCiclo(pComparador);
      if (!mc.esCadenaVacia(comparador_local)) {
        if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, String.valueOf('=')) || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOIGUALA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MENORQUE") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MAYORQUE") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MENOROIGUALA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "MAYOROIGUALA")) {
          
          valor_local = pValorCondicion;
          if (esTipoDatoTexto_local || mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "J"))
          {
            valor_local = mc.colocarEntreComillas(valor_local);
          }
        } else {
          if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "CONTIENEA") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOCONTIENEA")) {
            
            valor_local = mc.concatenarCadena("%", pValorCondicion);
            valor_local = mc.concatenarCadena(valor_local, "%");
            valor_local = mc.colocarEntreComillas(valor_local);
          } 
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "CONTIENEAPERONOIGUALA")) {
            valor_local = mc.concatenarCadena("%", pValorCondicion);
            valor_local = mc.concatenarCadena(valor_local, "%");
            valor_local = mc.colocarEntreComillas(valor_local);
            valor_local = mc.concatenarCadena(valor_local, " and ");
            if (esTipoDatoTexto_local) {
              valor_local = mc.concatenarCadena(valor_local, " upper(");
              valor_local = mc.concatenarCadena(valor_local, nombreCompuestoCampo_local);
              valor_local = mc.concatenarCadena(valor_local, String.valueOf(')'));
            } else {
              valor_local = mc.concatenarCadena(valor_local, nombreCompuestoCampo_local);
            } 
            valor_local = mc.concatenarCadena(valor_local, " not like ");
            valor_local = mc.concatenarCadena(valor_local, mc.colocarEntreComillas(mc.convertirAMayusculas(pValorCondicion)));
          } 
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "COMIENZACON") || mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOCOMIENZACON")) {
            
            valor_local = mc.concatenarCadena(pValorCondicion, "%");
            valor_local = mc.colocarEntreComillas(valor_local);
          } 
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "COMIENZACONPERONOIGUALA")) {
            valor_local = mc.concatenarCadena(pValorCondicion, "%");
            valor_local = mc.colocarEntreComillas(valor_local);
            valor_local = mc.concatenarCadena(valor_local, " and ");
            if (esTipoDatoTexto_local) {
              valor_local = mc.concatenarCadena(valor_local, " upper(");
              valor_local = mc.concatenarCadena(valor_local, nombreCompuestoCampo_local);
              valor_local = mc.concatenarCadena(valor_local, String.valueOf(')'));
            } else {
              valor_local = mc.concatenarCadena(valor_local, nombreCompuestoCampo_local);
            } 
            valor_local = mc.concatenarCadena(valor_local, " not like ");
            valor_local = mc.concatenarCadena(valor_local, mc.colocarEntreComillas(mc.convertirAMayusculas(pValorCondicion)));
          } 
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "CONTIENEPALABRAS")) {
            condicionSQL_local = ap.conformarCadenasFiltroPorPalabras(pValorCondicion, nombreCompuestoCampo_local, " like ");
          }
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pComparador, "NOCONTIENEPALABRAS")) {
            condicionSQL_local = ap.conformarCadenasFiltroPorPalabras(pValorCondicion, nombreCompuestoCampo_local, " not like ");
          }
        } 
        
        if (mc.esCadenaVacia(pValorCondicion)) {
          if (pCampo.esTipoDatoFecha() || pCampo.esTipoDatoHora()) {
            if (mc.sonCadenasIguales(comparador_local, " = ")) {
              comparador_local = " isnull ";
            }
            if (mc.sonCadenasIguales(comparador_local, " <> ")) {
              comparador_local = " is not null ";
            }
            valor_local = "";
          }
        
        } else if (pCampo.esTipoDatoFecha() && mc.sonCadenasIguales(comparador_local, " <> ")) {
          
          valor_local = mc.concatenarCadena(valor_local, " or ");
          valor_local = mc.concatenarCadena(valor_local, nombreCompuestoCampo_local);
          valor_local = mc.concatenarCadena(valor_local, " isnull ");
        } 
        
        if (mc.esCadenaVacia(condicionSQL_local)) {
          if (esTipoDatoTexto_local && !mc.esCadenaVacia(pValorCondicion)) {
        	//dguerrero 2021-04-13 Se remueve la conversion a mayusculas de los campos
        	/*
        	 * condicionSQL_local = mc.concatenarCadena(" upper(CAST(", nombreCompuestoCampo_local);
        	 * condicionSQL_local = mc.concatenarCadena(condicionSQL_local, " AS TEXT))");
        	 */
        	condicionSQL_local = mc.concatenarCadena(" ", nombreCompuestoCampo_local);
            condicionSQL_local = mc.concatenarCadena(condicionSQL_local, comparador_local);
          } else {
            condicionSQL_local = mc.concatenarCadena(nombreCompuestoCampo_local, comparador_local);
          } 
          condicionSQL_local = mc.concatenarCadena(condicionSQL_local, mc.convertirAMayusculas(valor_local));
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valor_local = null;
      comparador_local = null;
      nombreCompuestoCampo_local = null;
    } 
    
    return condicionSQL_local;
  }
  private String obtenerCondicionesInterpretadas(ListaCondiciones pListaCondiciones, int pNumeroCiclo) {
    String condicionesInterpretadas_local = "";
    int posicionOperador_local = -1;
    String seudonimo_local = null;
    String operador_local = null;
    String valorComparacion_local = null;
    ExpresionCondicion expresionCondicion_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    if (pListaCondiciones == ConstantesGeneral.VALOR_NULO) {
      return condicionesInterpretadas_local;
    }
    
    try {
      iterador_local = pListaCondiciones.iterator();
      while (iterador_local.hasNext()) {
        expresionCondicion_local = (ExpresionCondicion)iterador_local.next();
        operador_local = ap.obtenerOperadorExpresion(expresionCondicion_local.getExpresion());
        posicionOperador_local = mc.obtenerPosicionSubCadena(expresionCondicion_local.getExpresion(), operador_local);
        seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(expresionCondicion_local.getExpresion(), 0, posicionOperador_local));
        
        campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
        if (campo_local != ConstantesGeneral.VALOR_NULO) {
          posicionOperador_local += mc.obtenerLongitudCadena(operador_local) + 1;
          valorComparacion_local = mc.reemplazarCadena(expresionCondicion_local.getExpresion(), operador_local, String.valueOf('='));
          
          valorComparacion_local = obtenerValorRealAsignacion(valorComparacion_local, pNumeroCiclo);
          condicionesInterpretadas_local = mc.concatenarCadena(condicionesInterpretadas_local, conformarCondicionFiltradoSQLCiclo(campo_local, operador_local, valorComparacion_local));
          
          if (!mc.esCadenaVacia(condicionesInterpretadas_local)) {
            condicionesInterpretadas_local = mc.colocarEntreParentesis(condicionesInterpretadas_local);
            condicionesInterpretadas_local = mc.concatenarCadena(condicionesInterpretadas_local, String.valueOf(' '));
            
            condicionesInterpretadas_local = mc.concatenarCadena(condicionesInterpretadas_local, expresionCondicion_local.getOperadorAndOr());
            
            condicionesInterpretadas_local = mc.concatenarCadena(condicionesInterpretadas_local, String.valueOf(' '));
          }
        
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      operador_local = null;
      seudonimo_local = null;
      valorComparacion_local = null;
      expresionCondicion_local = null;
    } 
    
    return condicionesInterpretadas_local;
  }
  private ListaCadenas obtenerListaNombresCamposDeAsignaciones(ListaCadenas pListaAsignaciones) {
    ListaCadenas listaNombresCampos_local = null;
    Iterator iterator_local = null;
    String asignacion_local = null;
    int posicionIgual_local = -1;
    String seudonimo_local = null;
    Campo campo_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return listaNombresCampos_local;
    }
    
    try {
      listaNombresCampos_local = new ListaCadenas();
      iterator_local = pListaAsignaciones.iterator();
      while (iterator_local.hasNext()) {
        asignacion_local = (String)iterator_local.next();
        posicionIgual_local = mc.obtenerPosicionSubCadena(asignacion_local, String.valueOf('='));
        seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, 0, posicionIgual_local));
        
        campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
        if (campo_local != ConstantesGeneral.VALOR_NULO) {
          listaNombresCampos_local.adicionar(campo_local.getNombreCampo());
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      asignacion_local = null;
      seudonimo_local = null;
      campo_local = null;
    } 
    
    return listaNombresCampos_local;
  }
  private void realizarAsignacionesListaCampo(ListaCadenas pListaAsignaciones, ListaCampo pListaCampo, int pNumeroCiclo) {
    Iterator iterator_local = null;
    String asignacion_local = null;
    int posicionIgual_local = -1;
    String seudonimo_local = null;
    String valorAsignacion_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaAsignaciones.iterator();
      while (iterator_local.hasNext()) {
        asignacion_local = (String)iterator_local.next();
        posicionIgual_local = mc.obtenerPosicionSubCadena(asignacion_local, String.valueOf('='));
        seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, 0, posicionIgual_local));
        
        valorAsignacion_local = obtenerValorRealAsignacion(asignacion_local, pNumeroCiclo + 1);
        if (pListaCampo.obtenerCampoPorSeudonimo(seudonimo_local) != ConstantesGeneral.VALOR_NULO) {
          pListaCampo.obtenerCampoPorSeudonimo(seudonimo_local).setValorCampo(valorAsignacion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      asignacion_local = null;
      seudonimo_local = null;
      valorAsignacion_local = null;
    } 
  }
  private String conformarConsultaSQLCiclo(ListaCampo pListaCampo, String pCondiciones, int pValorLlavePrimaria, boolean pEsOrdenDescendente) {
    String consultaCiclo_local = "";
    String nombreGrupoInformacion_local = null;
    Campo primerCampo_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return consultaCiclo_local;
    }
    if (pCondiciones == ConstantesGeneral.VALOR_NULO) {
      return consultaCiclo_local;
    }
    
    try {
      primerCampo_local = pListaCampo.obtenerPrimerCampo();
      nombreGrupoInformacion_local = primerCampo_local.getGrupoInformacion().getNombreGrupoInformacion();
      if (!primerCampo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = primerCampo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(primerCampo_local.getGrupoInformacion());
      
      consultaCiclo_local = "select " + pListaCampo.concatenarCamposGrupoInformacion();
      consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " from " + nombreGrupoInformacion_local);
      
      if (!mc.esCadenaVacia(pCondiciones)) {
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " where " + pCondiciones);
      }
      
      if (primerCampo_local.getGrupoInformacion().esGrupoInformacionMultiple() && pValorLlavePrimaria != -1) {
        if (!mc.verificarExistenciaSubCadena(consultaCiclo_local, " where ")) {
          consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " where ");
        } else {
          consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " and ");
        } 
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, ap.conformarNombreCampoLlavePrimaria(primerCampo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()));
        
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " = ");
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, String.valueOf(pValorLlavePrimaria));
      } 
      
      if (!mc.esCadenaVacia(nombreCampoValorUnico_local)) {
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " order by " + nombreCampoValorUnico_local);
      
      }
      else if (primerCampo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " order by " + ap.conformarNombreCampoLlavePrimaria(primerCampo_local.getGrupoInformacion().getNombreGrupoInformacion()));
      }
      else {
        
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " order by " + ap.conformarNombreCampoLlavePrimaria(primerCampo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()));
      } 
      
      if (pEsOrdenDescendente) {
        consultaCiclo_local = mc.concatenarCadena(consultaCiclo_local, " desc ");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      primerCampo_local = null;
      nombreCampoValorUnico_local = null;
      nombreGrupoInformacion_local = null;
    } 
    
    return consultaCiclo_local;
  }
  private int obtenerValorLlavePrimariaPrincipal(GrupoInformacion pGrupoInformacionCiclo, int pNumeroCiclo) {
    int valorLlavePrimariaPrincipal_local = -1;
    
    if (pGrupoInformacionCiclo == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimariaPrincipal_local;
    }
    
    try {
      if (pGrupoInformacionCiclo.esGrupoInformacionMultiple()) {
        if (!mc.verificarCadenaNull(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacionCiclo.getAplicacion().getNombreAplicacion()), pNumeroCiclo))) {
          
          valorLlavePrimariaPrincipal_local = Integer.parseInt(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacionCiclo.getAplicacion().getNombreAplicacion()), pNumeroCiclo));
        
        }
        else if (pGrupoInformacionCiclo.getAplicacion().getIdAplicacion() == getGrupoInformacion().getAplicacion().getIdAplicacion()) {
          valorLlavePrimariaPrincipal_local = getValorLlaveGrupoPrincipal();
        }
      
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorLlavePrimariaPrincipal_local;
  }
  private int obtenerValorLlavePrimaria(GrupoInformacion pGrupoInformacionCiclo, int pNumeroCiclo) {
    int valorLlavePrimaria_local = -1;
    
    if (pGrupoInformacionCiclo == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    
    try {
      if (pGrupoInformacionCiclo.esGrupoInformacionMultiple()) {
        if (!mc.verificarCadenaNull(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacionCiclo.getNombreGrupoInformacion()), pNumeroCiclo))) {
          
          valorLlavePrimaria_local = Integer.parseInt(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacionCiclo.getNombreGrupoInformacion()), pNumeroCiclo));
        
        }
        else if (pGrupoInformacionCiclo.getIdGrupoInformacion() == getGrupoInformacion().getIdGrupoInformacion()) {
          valorLlavePrimaria_local = getValorLlaveGrupoInformacion();
        }
      
      }
      else if (!mc.verificarCadenaNull(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacionCiclo.getAplicacion().getNombreAplicacion()), pNumeroCiclo))) {
        
        valorLlavePrimaria_local = Integer.parseInt(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacionCiclo.getAplicacion().getNombreAplicacion()), pNumeroCiclo));
      
      }
      else if (pGrupoInformacionCiclo.getAplicacion().getIdAplicacion() == getGrupoInformacion().getAplicacion().getIdAplicacion()) {
        valorLlavePrimaria_local = getValorLlaveGrupoPrincipal();
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorLlavePrimaria_local;
  }
  private void inicializarCiclo(Ciclo pCiclo) {
    String consultaSQLCiclo_local = null;
    ListaCampo listaCampo_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    int valorLlavePrimariaPrincipal_local = -1;
    boolean errorCiclo_local = false;
    
    if (pCiclo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pCiclo.getListaCondiciones() != ConstantesGeneral.VALOR_NULO) {
        if (pCiclo.getListaCondiciones().contarElementos() > 0) {
          pCiclo.setCondicionesInterpretadas(obtenerCondicionesInterpretadas(pCiclo.getListaCondiciones(), pCiclo.getNumeroCiclo()));
        }
        
        errorCiclo_local = (!mc.esCadenaVacia(pCiclo.getCondiciones()) && pCiclo.getListaCondiciones().contarElementos() == 0);
      } 
      
      if (pCiclo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        listaCampo_local = getMotorAplicacion().obtenerCopiaListaCamposGrupoInformacionConArchivos(pCiclo.getGrupoInformacion().getIdGrupoInformacion());
      } else {
        
        listaCampo_local = getMotorAplicacion().obtenerCopiaListaCamposGruposInformacionNoMultiplesConArchivos(pCiclo.getGrupoInformacion().getAplicacion().getIdAplicacion());
      } 
      
      pCiclo.setListaCampos(listaCampo_local);
      
      valorLlavePrimariaPrincipal_local = obtenerValorLlavePrimariaPrincipal(pCiclo.getGrupoInformacion(), pCiclo.getNumeroCiclo());
      
      consultaSQLCiclo_local = conformarConsultaSQLCiclo(listaCampo_local, pCiclo.getCondicionesInterpretadas(), valorLlavePrimariaPrincipal_local, pCiclo.esOrdenDescendente());
      
      manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultSet_local = manejadorResultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQLCiclo_local);
      pCiclo.setResultSet(resultSet_local);
      pCiclo.setHaFinalizado((resultSet_local == ConstantesGeneral.VALOR_NULO || errorCiclo_local));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consultaSQLCiclo_local = null;
      listaCampo_local = null;
      manejadorResultadoConsultaSQL_local = null;
      resultSet_local = null;
    } 
  }
  private boolean actualizarCiclosSecundarios(ListaCiclos pListaCiclos) {
    boolean ciclosSecundarios_local = false;
    Iterator iterador_local = null;
    Ciclo ciclo_local = null;
    
    if (pListaCiclos == ConstantesGeneral.VALOR_NULO) {
      return ciclosSecundarios_local;
    }
    
    try {
      iterador_local = pListaCiclos.iterator();
      while (iterador_local.hasNext()) {
        ciclo_local = (Ciclo)iterador_local.next();
        if (ciclo_local.getNumeroCiclo() != 0) {
          if (ciclo_local.getResultSet() == ConstantesGeneral.VALOR_NULO) {
            inicializarCiclo(ciclo_local);
            if (ciclo_local.getResultSet().next()) {
              getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(ciclo_local.getListaCampos(), ciclo_local.getResultSet());
              
              if (ciclo_local.getNumeroCiclo() == pListaCiclos.contarElementos() - 1)
                ciclosSecundarios_local = true; 
              continue;
            } 
            ciclo_local.setHaFinalizado(true);
            ciclo_local.setResultSet(null);
            pListaCiclos.finalizarSiguientesCiclos(ciclo_local.getNumeroCiclo());
            
            break;
          } 
          if (pListaCiclos.verificarHaFinalizadoSiguienteCiclo(ciclo_local.getNumeroCiclo())) {
            if (!ciclo_local.esSoloUnRegistro() && ciclo_local.getResultSet().next()) {
              getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(ciclo_local.getListaCampos(), ciclo_local.getResultSet());
            } else {
              
              ciclo_local.setHaFinalizado(!ciclo_local.esSoloUnRegistro());
            } 
          }
          if (ciclo_local.esSoloUnRegistro()) {
            ciclo_local.setHaFinalizado(pListaCiclos.verificarHaFinalizadoSiguienteCiclo(ciclo_local.getNumeroCiclo()));
          }
          if (ciclo_local.esHaFinalizado()) {
            ciclo_local.setResultSet(null);
            pListaCiclos.finalizarSiguientesCiclos(ciclo_local.getNumeroCiclo());
            
            break;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      ciclo_local = null;
      iterador_local = null;
    } 
    
    return ciclosSecundarios_local;
  }
  public String interpretarCadenaMensaje(String pCadenaMensaje, int pNumeroCiclo) {
    String mensaje_local = null;
    int posicionAbreParentesis_local = -1;
    int posicionCierraParentesis_local = -1;
    boolean salir_local = false;
    String contenido_local = null;
    String seudonimo_local = null;
    String valorReemplazo_local = null;
    String mensajeTemporal_local = null;
    Object valorVariable_local = null;
    Campo campo_local = null;
    
    if (pCadenaMensaje == ConstantesGeneral.VALOR_NULO) {
      return mensaje_local;
    }
    
    try {
      mensaje_local = pCadenaMensaje;
      
      while (mc.verificarExistenciaSubCadena(mensaje_local, String.valueOf('(')) && mc.verificarExistenciaSubCadena(mensaje_local, String.valueOf(')')) && !salir_local)
      {
        posicionAbreParentesis_local = mc.obtenerPosicionSubCadena(mensaje_local, String.valueOf('('));
        
        posicionCierraParentesis_local = mc.obtenerPosicionSubCadena(mensaje_local, String.valueOf(')'));
        
        valorReemplazo_local = "";
        if (posicionAbreParentesis_local < posicionCierraParentesis_local) {
          contenido_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(mensaje_local, posicionAbreParentesis_local + 1, posicionCierraParentesis_local));
          
          if (verificarEsSeudonimo(contenido_local)) {
            valorReemplazo_local = obtenerValorOperandoPorSeudonimo(contenido_local, pNumeroCiclo + 1);
            
            seudonimo_local = ap.obtenerSeudonimoValido(contenido_local);
            campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local);
            if (campo_local.esTipoDatoTabla() || (campo_local.esCampoEnlazado() && !mc.esCadenaVacia(valorReemplazo_local))) {
              valorReemplazo_local = obtenerValorCampoTablaOEnlazadoComoTexto(campo_local, Integer.parseInt(valorReemplazo_local));
            }
            
            if (mc.esCadenaVacia(valorReemplazo_local) && 
              campo_local.esTipoDatoNumerico() && !campo_local.esCampoEnlazado()) {
              valorReemplazo_local = String.valueOf(0);
            }
          } 
          
          if (ap.esVariableSistema(contenido_local)) {
            valorVariable_local = getManejadorVariablesSistema().obtenerValorVariableSistema(contenido_local).getValorVariable();
            
            campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(ap.obtenerSeudonimoValido(contenido_local));
            if (valorVariable_local != ConstantesGeneral.VALOR_NULO) {
              if (campo_local != ConstantesGeneral.VALOR_NULO && (campo_local.esCampoEnlazado() || campo_local.esTipoDatoTabla())) {
                valorReemplazo_local = obtenerValorCampoTablaOEnlazadoComoTexto(campo_local, Integer.parseInt(valorVariable_local.toString()));
              
              }
              else if (!mc.sonCadenasIguales(valorVariable_local.toString(), contenido_local)) {
                valorReemplazo_local = valorVariable_local.toString();
              } 
            }
          } 
          
          mensajeTemporal_local = mc.concatenarCadena(mc.obtenerSubCadena(mensaje_local, 0, posicionAbreParentesis_local), valorReemplazo_local);
          
          mensaje_local = mc.concatenarCadena(mensajeTemporal_local, mc.obtenerSubCadena(mensaje_local, posicionCierraParentesis_local + 1, mc.obtenerLongitudCadena(mensaje_local)));
          continue;
        } 
        salir_local = true;
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      contenido_local = null;
      seudonimo_local = null;
      valorVariable_local = null;
      valorReemplazo_local = null;
    } 
    
    return mensaje_local;
  }
  private void ejecutarAccionMensajeError(Accion pAccion) {
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        setHuboError(true);
        pAccion.setMensaje(interpretarCadenaMensaje(pAccion.getMensaje(), -1));
      } else {
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              setHuboError(true);
              pAccion.setMensaje(interpretarCadenaMensaje(pAccion.getMensaje(), cicloPrincipal_local.getNumeroCiclo()));
            } else {
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                if (actualizarCiclosSecundarios(pAccion.getListaCiclos())) {
                  setHuboError(true);
                  pAccion.setMensaje(interpretarCadenaMensaje(pAccion.getMensaje(), pAccion.getListaCiclos().contarElementos()));
                  break;
                } 
              } 
            } 
            cicloPrincipal_local.setHaFinalizado((cicloPrincipal_local.esSoloUnRegistro() || esHuboError()));
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          } 
        }
      } 
      if (esHuboError()) {
        setMensajeEventos(pAccion.getMensaje());
        setTipoMensajeEventos(2);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
  }
  private boolean ejecutarAccionMensajeNormal(Accion pAccion) {
    boolean accionMensajeNormal_local = false;
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return accionMensajeNormal_local;
    }
    
    try {
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        accionMensajeNormal_local = true;
        pAccion.setMensaje(interpretarCadenaMensaje(pAccion.getMensaje(), -1));
      } else {
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              accionMensajeNormal_local = true;
              pAccion.setMensaje(interpretarCadenaMensaje(pAccion.getMensaje(), cicloPrincipal_local.getNumeroCiclo()));
            } else {
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                accionMensajeNormal_local = actualizarCiclosSecundarios(pAccion.getListaCiclos());
                if (accionMensajeNormal_local) {
                  pAccion.setMensaje(interpretarCadenaMensaje(pAccion.getMensaje(), pAccion.getListaCiclos().contarElementos()));
                  break;
                } 
              } 
            } 
            cicloPrincipal_local.setHaFinalizado((cicloPrincipal_local.esSoloUnRegistro() || accionMensajeNormal_local));
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          } 
        }
      } 
      if (accionMensajeNormal_local) {
        setMensajeEventos(pAccion.getMensaje());
        setTipoMensajeEventos(3);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
    
    return accionMensajeNormal_local;
  }
  private void ejecutarAccionAsignar(Accion pAccion) {
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pAccion.getGrupoInformacion() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), getListaCampo(), -1);
        
        setHuboAsignacion(true);
      } else {
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), getListaCampo(), cicloPrincipal_local.getNumeroCiclo());
              
              setHuboAsignacion(true);
            } else {
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                actualizarCiclosSecundarios(pAccion.getListaCiclos());
                if (!pAccion.getListaCiclos().verificarExistenciaCicloSecundarioFinalizado()) {
                  realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), getListaCampo(), pAccion.getListaCiclos().contarElementos());
                  
                  setHuboAsignacion(true);
                } 
              } 
            } 
            cicloPrincipal_local.setHaFinalizado(cicloPrincipal_local.esSoloUnRegistro());
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          } 
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
  }
  private void incluirRegistro(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, int pNumeroCiclo, boolean pActualizarInformacionRecalculable) {
    int incluirRegistro_local = -1;
    String condiciones_local = null;
    int valorLlavePrimariaPrincipal_local = -1;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      valorLlavePrimariaPrincipal_local = obtenerValorLlavePrimariaPrincipal(pGrupoInformacion, pNumeroCiclo + 1);
      
      pListaCampo.borrarCamposTipoArchivoParaInclusion();
      incluirRegistro_local = getAdministradorBaseDatosAplicacion().incluirRegistroAplicacion(pGrupoInformacion, valorLlavePrimariaPrincipal_local, pListaCampo);
      
      if (incluirRegistro_local == 0) {
        if (pGrupoInformacion.esGrupoInformacionMultiple()) {
          if (pActualizarInformacionRecalculable) {
            getManejadorInformacionRecalculable().actualizarInformacionRecalculableGrupoInformacion(pGrupoInformacion, valorLlavePrimariaPrincipal_local);
          }
        } else {
          
          valorLlavePrimariaPrincipal_local = getAdministradorBaseDatosAplicacion().obtenerIdUltimoRegistroIncluido(pGrupoInformacion.getAplicacion().getNombreAplicacion(), ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion()), "", -1);
        } 
        
        condiciones_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
        condiciones_local = mc.concatenarCadena(condiciones_local, " = ");
        condiciones_local = mc.concatenarCadena(condiciones_local, String.valueOf(valorLlavePrimariaPrincipal_local));
        
        if (pActualizarInformacionRecalculable) {
          getManejadorInformacionRecalculable().actualizarInformacionRecalculableAplicacion(pGrupoInformacion.getAplicacion(), -1, condiciones_local);
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void ejecutarAccionIncluir(Accion pAccion) {
    ListaCampo listaCampo_local = null;
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pAccion.getGrupoInformacion() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (!pAccion.getGrupoInformacion().esGrupoInformacionMultiple()) {
        listaCampo_local = getMotorAplicacion().obtenerCopiaListaCamposGruposInformacionNoMultiples(pAccion.getGrupoInformacion().getAplicacion().getIdAplicacion());
      } else {
        
        listaCampo_local = getMotorAplicacion().obtenerCopiaListaCamposGrupoInformacion(pAccion.getGrupoInformacion().getIdGrupoInformacion());
      } 
      
      listaCampo_local.asignarValorPorDefectoCamposNumericos();
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        if (!pAccion.getGrupoInformacion().esGrupoInformacionMultiple()) {
          realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), listaCampo_local, -1);
          
          incluirRegistro(pAccion.getGrupoInformacion(), listaCampo_local, -1, pAccion.esActualizarInformacionRecalculable());
        } 
      } else {
        
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), listaCampo_local, cicloPrincipal_local.getNumeroCiclo());
              
              incluirRegistro(pAccion.getGrupoInformacion(), listaCampo_local, cicloPrincipal_local.getNumeroCiclo(), pAccion.esActualizarInformacionRecalculable());
            } else {
              
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                actualizarCiclosSecundarios(pAccion.getListaCiclos());
                if (!pAccion.getListaCiclos().verificarExistenciaCicloSecundarioFinalizado()) {
                  realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), listaCampo_local, pAccion.getListaCiclos().contarElementos());
                  
                  incluirRegistro(pAccion.getGrupoInformacion(), listaCampo_local, pAccion.getListaCiclos().contarElementos(), pAccion.esActualizarInformacionRecalculable());
                } 
              } 
            } 
            
            cicloPrincipal_local.setHaFinalizado(cicloPrincipal_local.esSoloUnRegistro());
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          }
        
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
  }
  private void modificarRegistro(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, int pNumeroCiclo, boolean pActualizarInformacionRecalculable) {
    int modificarRegistro_local = -1;
    String condiciones_local = null;
    int valorLlavePrimaria_local = -1;
    GrupoInformacion grupoInformacionPrincipal_local = null;
    int valorLlavePrimariaPrincipal_local = -1;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      valorLlavePrimaria_local = obtenerValorLlavePrimaria(pGrupoInformacion, pNumeroCiclo + 1);
      
      modificarRegistro_local = getAdministradorBaseDatosAplicacion().modificarRegistroAplicacion(pGrupoInformacion, pListaCampo, valorLlavePrimaria_local);
      
      if (modificarRegistro_local == 0) {
        grupoInformacionPrincipal_local = getMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion());
        
        valorLlavePrimariaPrincipal_local = obtenerValorLlavePrimaria(grupoInformacionPrincipal_local, pNumeroCiclo + 1);
        
        if (pGrupoInformacion.esGrupoInformacionMultiple() && 
          pActualizarInformacionRecalculable) {
          getManejadorInformacionRecalculable().actualizarInformacionRecalculableGrupoInformacion(pGrupoInformacion, valorLlavePrimariaPrincipal_local);
        }
        
        condiciones_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
        condiciones_local = mc.concatenarCadena(condiciones_local, " = ");
        condiciones_local = mc.concatenarCadena(condiciones_local, String.valueOf(valorLlavePrimariaPrincipal_local));
        
        if (pActualizarInformacionRecalculable) {
          getManejadorInformacionRecalculable().actualizarInformacionRecalculableAplicacion(pGrupoInformacion.getAplicacion(), -1, condiciones_local);
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      grupoInformacionPrincipal_local = null;
    } 
  }
  private ListaCampo obtenerListaCampoAsignacionArchivoNulo(ListaCadenas pListaAsignaciones) {
    ListaCampo listaCampoAsignacionArchivoNulo_local = null;
    int posicionIgual_local = -1;
    String asignacion_local = null;
    String seudonimo_local = null;
    String valorAsignacion_local = null;
    Campo campo_local = null;
    Iterator iterator_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return listaCampoAsignacionArchivoNulo_local;
    }
    
    try {
      listaCampoAsignacionArchivoNulo_local = new ListaCampo();
      iterator_local = pListaAsignaciones.iterator();
      while (iterator_local.hasNext()) {
        asignacion_local = (String)iterator_local.next();
        posicionIgual_local = mc.obtenerPosicionSubCadena(asignacion_local, String.valueOf('='));
        seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, 0, posicionIgual_local));
        
        valorAsignacion_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, posicionIgual_local + 1, mc.obtenerLongitudCadena(asignacion_local)));
        
        if (mc.verificarExistenciaSubCadena(valorAsignacion_local, String.valueOf('"'))) {
          valorAsignacion_local = ap.obtenerContenidoEntreComillas(valorAsignacion_local);
          campo_local = getMotorAplicacion().obtenerCopiaCampoPorSeudonimo(seudonimo_local);
          if (mc.esCadenaVacia(valorAsignacion_local) && campo_local != ConstantesGeneral.VALOR_NULO && mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "J"))
          {
            listaCampoAsignacionArchivoNulo_local.adicionar(campo_local);
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterator_local = null;
      seudonimo_local = null;
      asignacion_local = null;
      valorAsignacion_local = null;
    } 
    
    return listaCampoAsignacionArchivoNulo_local;
  }
  private ListaCampo complementarAsignacionesNulasArchivos(ListaCampo pListaCampoArchivosNulos) {
    ListaCampo listaCampoAsignaciones_local = null;
    Campo campo_local = null;
    Campo campoNombreArchivo_local = null;
    Iterator iterator_local = null;
    
    if (pListaCampoArchivosNulos == ConstantesGeneral.VALOR_NULO) {
      return listaCampoAsignaciones_local;
    }
    
    try {
      listaCampoAsignaciones_local = new ListaCampo();
      iterator_local = pListaCampoArchivosNulos.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        campoNombreArchivo_local = ap.conformarCampoNombreArchivo(campo_local);
        campoNombreArchivo_local.setValorCampo("");
        listaCampoAsignaciones_local.adicionar(campoNombreArchivo_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterator_local = null;
      campoNombreArchivo_local = null;
    } 
    
    return listaCampoAsignaciones_local;
  }
  private void copiarArchivos(ListaCadenas pListaAsignaciones, int pNumeroCiclo) {
    Iterator iterator_local = null;
    String asignacion_local = null;
    int posicionIgual_local = -1;
    String seudonimoOrigen_local = null;
    String seudonimoDestino_local = null;
    Campo campoOrigen_local = null;
    Campo campoDestino_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaAsignaciones.iterator();
      while (iterator_local.hasNext()) {
        asignacion_local = (String)iterator_local.next();
        posicionIgual_local = mc.obtenerPosicionSubCadena(asignacion_local, String.valueOf('='));
        seudonimoDestino_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, 0, posicionIgual_local));
        
        seudonimoOrigen_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, posicionIgual_local + 1, mc.obtenerLongitudCadena(asignacion_local)));
        
        campoOrigen_local = getMotorAplicacion().obtenerCopiaCampoPorSeudonimo(seudonimoOrigen_local);
        campoDestino_local = getMotorAplicacion().obtenerCopiaCampoPorSeudonimo(seudonimoDestino_local);
        if (campoOrigen_local != ConstantesGeneral.VALOR_NULO && campoDestino_local != ConstantesGeneral.VALOR_NULO) {
          getAdministradorBaseDatosAplicacion().copiarArchivoEntreCampos(campoOrigen_local, campoDestino_local, obtenerValorLlavePrimaria(campoOrigen_local.getGrupoInformacion(), pNumeroCiclo + 1), obtenerValorLlavePrimaria(campoDestino_local.getGrupoInformacion(), pNumeroCiclo + 1));
        
        }
      
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      asignacion_local = null;
    } 
  }
  private void ejecutarAccionModificar(Accion pAccion) {
    ListaCampo listaCampo_local = null;
    boolean existenCamposTipoArchivo_local = false;
    Ciclo cicloPrincipal_local = null;
    ListaCadenas listaNombresCamposDeAsignaciones_local = null;
    ListaCampo listaCampoNombresArchivosNulos_local = null;
    ListaCampo listaCampoAsignacionArchivoNulo_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pAccion.getGrupoInformacion() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaNombresCamposDeAsignaciones_local = obtenerListaNombresCamposDeAsignaciones(pAccion.getListaAsignaciones());
      existenCamposTipoArchivo_local = verificarExistenciaCamposTipoArchivoEnListaCamposDeAsignaciones(pAccion.getListaAsignaciones());
      if (!pAccion.getGrupoInformacion().esGrupoInformacionMultiple()) {
        listaCampo_local = getMotorAplicacion().obtenerCopiaListaCamposGruposInformacionNoMultiples(pAccion.getGrupoInformacion().getAplicacion().getIdAplicacion());
      } else {
        
        listaCampo_local = getMotorAplicacion().obtenerCopiaListaCamposGrupoInformacion(pAccion.getGrupoInformacion().getIdGrupoInformacion());
      } 
      
      listaCampo_local.borrarCamposNoEncontradosLista(listaNombresCamposDeAsignaciones_local);
      if (existenCamposTipoArchivo_local) {
        listaCampoAsignacionArchivoNulo_local = obtenerListaCampoAsignacionArchivoNulo(pAccion.getListaAsignaciones());
        
        if (listaCampoAsignacionArchivoNulo_local.contarElementos() > 0) {
          listaCampoNombresArchivosNulos_local = complementarAsignacionesNulasArchivos(listaCampoAsignacionArchivoNulo_local);
          listaCampo_local.concatenar(listaCampoNombresArchivosNulos_local);
        } 
      } 
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        if (!pAccion.getGrupoInformacion().esGrupoInformacionMultiple()) {
          realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), listaCampo_local, -1);
          modificarRegistro(pAccion.getGrupoInformacion(), listaCampo_local, -1, pAccion.esActualizarInformacionRecalculable());
        } 
      } else {
        
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() == ConstantesGeneral.VALOR_NULO) {
        	return;
        }
        long filasProcesadas = 0;
        ResultSet resulset = cicloPrincipal_local.getResultSet();
        ManejadorInformacionRecalculable manejadorInformacionRecalculable = getManejadorInformacionRecalculable();
		  while (resulset.next() && !cicloPrincipal_local.esHaFinalizado()) {
			  filasProcesadas++;
			  System.out.println("FilasProcesadas : " + filasProcesadas);
			  manejadorInformacionRecalculable.asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), resulset);
		    
		    if (pAccion.getListaCiclos().contarElementos() == 1) {
		      realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), listaCampo_local, cicloPrincipal_local.getNumeroCiclo());
		      
		      if (listaCampoAsignacionArchivoNulo_local != ConstantesGeneral.VALOR_NULO && listaCampoAsignacionArchivoNulo_local.contarElementos() == 0 && sonTodosLosCamposTipoArchivoEnListaCamposDeAsignaciones(pAccion.getListaAsignaciones())) {
		        
		        copiarArchivos(pAccion.getListaAsignaciones(), cicloPrincipal_local.getNumeroCiclo());
		      } else {
		        modificarRegistro(pAccion.getGrupoInformacion(), listaCampo_local, cicloPrincipal_local.getNumeroCiclo(), pAccion.esActualizarInformacionRecalculable());
		      } 
		    } else {
		      
		      while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
		        actualizarCiclosSecundarios(pAccion.getListaCiclos());
		        if (!pAccion.getListaCiclos().verificarExistenciaCicloSecundarioFinalizado()) {
		          realizarAsignacionesListaCampo(pAccion.getListaAsignaciones(), listaCampo_local, pAccion.getListaCiclos().contarElementos());
		          
		          if (listaCampoAsignacionArchivoNulo_local != ConstantesGeneral.VALOR_NULO && listaCampoAsignacionArchivoNulo_local.contarElementos() == 0 && sonTodosLosCamposTipoArchivoEnListaCamposDeAsignaciones(pAccion.getListaAsignaciones())) {
		            
		            copiarArchivos(pAccion.getListaAsignaciones(), pAccion.getListaCiclos().contarElementos()); continue;
		          } 
		          modificarRegistro(pAccion.getGrupoInformacion(), listaCampo_local, pAccion.getListaCiclos().contarElementos(), pAccion.esActualizarInformacionRecalculable());
		        } 
		      } 
		    } 
		    
		    cicloPrincipal_local.setHaFinalizado(cicloPrincipal_local.esSoloUnRegistro());
		    if (!cicloPrincipal_local.esHaFinalizado()) {
		      pAccion.getListaCiclos().inicializarCiclosSecundarios();
		    }
		  } 
        
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
      listaNombresCamposDeAsignaciones_local = null;
      listaCampoAsignacionArchivoNulo_local = null;
    } 
  }
  private void borrarRegistro(GrupoInformacion pGrupoInformacion, int pNumeroCiclo, boolean pActualizarInformacionRecalculable) {
    int borrarRegistro_local = -1;
    String condiciones_local = null;
    int valorLlavePrimaria_local = -1;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      valorLlavePrimaria_local = obtenerValorLlavePrimaria(pGrupoInformacion, pNumeroCiclo + 1);
      borrarRegistro_local = getAdministradorBaseDatosAplicacion().borrarRegistroAplicacion(pGrupoInformacion, valorLlavePrimaria_local);
      
      if (borrarRegistro_local == 0 && 
        !mc.verificarCadenaNull(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion()), pNumeroCiclo + 1)))
      {
        valorLlavePrimaria_local = Integer.parseInt(obtenerValorCampoListaCiclos(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion()), pNumeroCiclo + 1));
        
        condiciones_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
        condiciones_local = mc.concatenarCadena(condiciones_local, " = ");
        condiciones_local = mc.concatenarCadena(condiciones_local, String.valueOf(valorLlavePrimaria_local));
        
        if (pActualizarInformacionRecalculable) {
          getManejadorInformacionRecalculable().actualizarInformacionRecalculableAplicacion(pGrupoInformacion.getAplicacion(), -1, condiciones_local);
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void ejecutarAccionBorrar(Accion pAccion) {
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pAccion.getGrupoInformacion() == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        if (!pAccion.getGrupoInformacion().esGrupoInformacionMultiple()) {
          borrarRegistro(pAccion.getGrupoInformacion(), -1, pAccion.esActualizarInformacionRecalculable());
        }
      } else {
        
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              borrarRegistro(pAccion.getGrupoInformacion(), cicloPrincipal_local.getNumeroCiclo(), pAccion.esActualizarInformacionRecalculable());
            } else {
              
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                actualizarCiclosSecundarios(pAccion.getListaCiclos());
                if (!pAccion.getListaCiclos().verificarExistenciaCicloSecundarioFinalizado()) {
                  borrarRegistro(pAccion.getGrupoInformacion(), pAccion.getListaCiclos().contarElementos(), pAccion.esActualizarInformacionRecalculable());
                }
              } 
            } 
            
            cicloPrincipal_local.setHaFinalizado(cicloPrincipal_local.esSoloUnRegistro());
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          } 
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
  }
  private ListaCampo obtenerListaCamposDeAsignaciones(ListaCadenas pListaAsignaciones) {
    ListaCampo listaCampoAsignaciones_local = null;
    Iterator iterator_local = null;
    String asignacion_local = null;
    int posicionIgual_local = -1;
    String seudonimo_local = null;
    Campo campo_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return listaCampoAsignaciones_local;
    }
    
    try {
      listaCampoAsignaciones_local = new ListaCampo();
      iterator_local = pListaAsignaciones.iterator();
      while (iterator_local.hasNext()) {
        asignacion_local = (String)iterator_local.next();
        posicionIgual_local = mc.obtenerPosicionSubCadena(asignacion_local, String.valueOf('='));
        seudonimo_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(asignacion_local, 0, posicionIgual_local));
        
        campo_local = getMotorAplicacion().obtenerCopiaCampoPorSeudonimo(seudonimo_local);
        if (campo_local != ConstantesGeneral.VALOR_NULO) {
          listaCampoAsignaciones_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      asignacion_local = null;
      seudonimo_local = null;
      campo_local = null;
    } 
    
    return listaCampoAsignaciones_local;
  }
  private boolean verificarExistenciaCamposTipoArchivoEnListaCamposDeAsignaciones(ListaCadenas pListaAsignaciones) {
    boolean existenCamposTipoArchivo_local = false;
    ListaCampo listaCampoAsignaciones_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return existenCamposTipoArchivo_local;
    }
    
    try {
      listaCampoAsignaciones_local = obtenerListaCamposDeAsignaciones(pListaAsignaciones);
      if (listaCampoAsignaciones_local != ConstantesGeneral.VALOR_NULO) {
        existenCamposTipoArchivo_local = listaCampoAsignaciones_local.verificarExistenciaCampoTipoArchivo();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampoAsignaciones_local = null;
    } 
    
    return existenCamposTipoArchivo_local;
  }
  private boolean sonTodosLosCamposTipoArchivoEnListaCamposDeAsignaciones(ListaCadenas pListaAsignaciones) {
    boolean todosLosCamposSonTipoArchivo_local = false;
    ListaCampo listaCampoAsignaciones_local = null;
    
    if (pListaAsignaciones == ConstantesGeneral.VALOR_NULO) {
      return todosLosCamposSonTipoArchivo_local;
    }
    
    try {
      listaCampoAsignaciones_local = obtenerListaCamposDeAsignaciones(pListaAsignaciones);
      if (listaCampoAsignaciones_local != ConstantesGeneral.VALOR_NULO) {
        todosLosCamposSonTipoArchivo_local = listaCampoAsignaciones_local.sonTodosLosCamposTipoArchivo();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampoAsignaciones_local = null;
    } 
    
    return todosLosCamposSonTipoArchivo_local;
  }
  private void ejecutarAccionEsperar(Accion pAccion) {
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        Thread.sleep(pAccion.getTiempoEspera());
      } else {
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              Thread.sleep(pAccion.getTiempoEspera());
            } else {
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                actualizarCiclosSecundarios(pAccion.getListaCiclos());
                if (!pAccion.getListaCiclos().verificarExistenciaCicloSecundarioFinalizado()) {
                  Thread.sleep(pAccion.getTiempoEspera());
                }
              } 
            } 
            cicloPrincipal_local.setHaFinalizado(cicloPrincipal_local.esSoloUnRegistro());
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          }
        
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
  }
  private String ejecutarAccionIrA(Accion pAccion) {
    String etiquetaAccionIrA_local = "";
    boolean existenValorCondicion_local = false;
    Ciclo cicloPrincipal_local = null;
    
    if (pAccion == ConstantesGeneral.VALOR_NULO) {
      return etiquetaAccionIrA_local;
    }
    
    try {
      if (pAccion.getListaCiclos() == ConstantesGeneral.VALOR_NULO) {
        etiquetaAccionIrA_local = pAccion.getEtiquetaDestino();
      } else {
        cicloPrincipal_local = pAccion.getListaCiclos().obtenerCicloPrincipal();
        inicializarCiclo(cicloPrincipal_local);
        if (cicloPrincipal_local.getResultSet() != ConstantesGeneral.VALOR_NULO) {
          while (cicloPrincipal_local.getResultSet().next() && !cicloPrincipal_local.esHaFinalizado()) {
            getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(cicloPrincipal_local.getListaCampos(), cicloPrincipal_local.getResultSet());
            
            if (pAccion.getListaCiclos().contarElementos() == 1) {
              existenValorCondicion_local = true;
              etiquetaAccionIrA_local = pAccion.getEtiquetaDestino();
            } else {
              while (!pAccion.getListaCiclos().verificarHanFinalizadoTodosLosCiclosSecundarios()) {
                existenValorCondicion_local = actualizarCiclosSecundarios(pAccion.getListaCiclos());
                if (existenValorCondicion_local) {
                  etiquetaAccionIrA_local = pAccion.getEtiquetaDestino();
                  break;
                } 
              } 
            } 
            cicloPrincipal_local.setHaFinalizado((cicloPrincipal_local.esSoloUnRegistro() || existenValorCondicion_local));
            if (!cicloPrincipal_local.esHaFinalizado()) {
              pAccion.getListaCiclos().inicializarCiclosSecundarios();
            }
          } 
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cicloPrincipal_local = null;
    } 
    
    return etiquetaAccionIrA_local;
  }
  private void ejecutarAccionesEvento(Evento pEvento, String pEtiquetaInicial) {
    Iterator iterator_local = null;
    Accion accion_local = null;
    String nombreAccion_local = null;
    String etiquetaAccion_local = null;
    
    if (pEvento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pEvento.getListaAcciones() != ConstantesGeneral.VALOR_NULO) {
        iterator_local = pEvento.obtenerListaAccionesDesdeEtiqueta(pEtiquetaInicial).iterator();
        while (iterator_local.hasNext()) {
          accion_local = (Accion)iterator_local.next();
          nombreAccion_local = accion_local.getAccionRealizar();
          setListaCiclos(accion_local.getListaCiclos());
          if (getListaCiclos() != ConstantesGeneral.VALOR_NULO) {
            getListaCiclos().inicializarCiclosSecundarios();
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$MENSAJEERROR")) {
            ejecutarAccionMensajeError(accion_local);
            if (esHuboError()) {
              break;
            }
          } 
          if (mc.sonCadenasIguales(nombreAccion_local, "$MENSAJENORMAL")) {
            ejecutarAccionMensajeNormal(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$ASIGNAR")) {
            ejecutarAccionAsignar(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$INCLUIRSINRECALCULAR")) {
            ejecutarAccionIncluir(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$INCLUIR")) {
            ejecutarAccionIncluir(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$MODIFICARSINRECALCULAR")) {
            ejecutarAccionModificar(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$MODIFICAR")) {
            ejecutarAccionModificar(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$BORRARSINRECALCULAR")) {
            ejecutarAccionBorrar(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$BORRAR")) {
            ejecutarAccionBorrar(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$ESPERAR")) {
            ejecutarAccionEsperar(accion_local);
          }
          if (mc.sonCadenasIguales(nombreAccion_local, "$IRA")) {
            etiquetaAccion_local = ejecutarAccionIrA(accion_local);
            if (!mc.esCadenaVacia(etiquetaAccion_local)) {
              ejecutarAccionesEvento(pEvento, etiquetaAccion_local);
              break;
            } 
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      accion_local = null;
      nombreAccion_local = null;
      etiquetaAccion_local = null;
    } 
  }
  public void ejecutarEvento() {
    ListaEventos listaEventos_local = null;
    Evento evento_local = null;
    Iterator iteradorEventos_local = null;
    
    try {
      setHaFinalizadoEjecucion(false);
      listaEventos_local = getListaEventos().obtenerListaEventoPorNombre(getNombreEvento());
      if (listaEventos_local != ConstantesGeneral.VALOR_NULO) {
        setHuboError(false);
        iteradorEventos_local = listaEventos_local.iterator();
        while (iteradorEventos_local.hasNext()) {
          evento_local = (Evento)iteradorEventos_local.next();
          interpretarEvento(evento_local);
          evaluarCondicionesEvento(evento_local);
          if (esRealizarAccionUsuario() == evento_local.esRealizarAccionUsuario()) {
            if (evento_local.esEventoValido()) {
              ejecutarAccionesEvento(evento_local, "");
            } else {
              mostrarEstructuraEvento(evento_local);
            } 
          }
          if (esHuboError()) {
            break;
          }
        } 
      } 
      setHaFinalizadoEjecucion(true);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      evento_local = null;
      listaEventos_local = null;
      iteradorEventos_local = null;
    } 
  }
  private String obtenerNombreEventoUsuario() {
    String nombreEvento_local = "";
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = mc.obtenerParrafoComoListaCadenas(getGrupoInformacion().getAplicacion().getTipoEventosUsuario());
      nombreEvento_local = listaCadenas_local.obtenerCadena(getNumeroEvento());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCadenas_local = null;
    } 
    
    return nombreEvento_local;
  }
  public void ejecutarEventoUsuario() {
    try {
      setNombreEvento(obtenerNombreEventoUsuario());
      ejecutarEvento();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorEventos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */