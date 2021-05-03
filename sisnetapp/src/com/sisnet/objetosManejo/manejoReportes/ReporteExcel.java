package com.sisnet.objetosManejo.manejoReportes;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoEnlazado;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultaSQLPrincipal;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaConsulta;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaColumnasReporte;
import com.sisnet.objetosManejo.listas.objetosReporte.ListaOrdenReporte;
import com.sisnet.objetosManejo.manejoPaginaJsp.ItemConsulta;
import com.sisnet.objetosManejo.manejoReportes.CargadorReporte;
import com.sisnet.objetosManejo.manejoReportes.GeneradorArchivoExcel;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.BloqueColumnas;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.BloqueEncabezadoPie;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.BloqueGeneral;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.BloqueSubtotal;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.BloqueTotal;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.ColumnaReporte;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.OrdenReporte;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.SubtotalReporte;
import java.sql.ResultSet;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
public class ReporteExcel
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  protected static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private Aplicacion aAplicacionActual;
  private int aValorLlavePrimariaReporte;
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private ManejadorResultadoConsultaSQL aResultadoConsultaSQL;
  private ManejadorCampoEnlazado aManejadorCampoEnlazado;
  private String aNombreReporte;
  private BloqueGeneral aBloqueGeneral;
  private BloqueEncabezadoPie aBloqueEncabezado;
  private BloqueEncabezadoPie aBloquePie;
  private BloqueColumnas aBloqueColumnas;
  private BloqueSubtotal aBloqueSubtotal;
  private BloqueTotal aBloqueTotal;
  private Usuario aUsuario;
  private String aIdSesion;
  private String aFecha;
  private String aHora;
  private ListaCadenas aListaFilasSubtotal;
  private MotorAplicacion aMotorAplicacion;
  public ReporteExcel(Aplicacion pAplicacionActual, int pValorLlavePrimariaReporte, Usuario pUsuario, String pFecha, String pHora, String pIdSesion) {
    setAplicacionActual(pAplicacionActual);
    setValorLlavePrimariaReporte(pValorLlavePrimariaReporte);
    setUsuario(pUsuario);
    setIdSesion(pIdSesion);
    setFecha(pFecha);
    setHora(pHora);
    setResultadoConsultaSQL(new ManejadorResultadoConsultaSQL());
    setManejadorCampoEnlazado(new ManejadorCampoEnlazado());
    setListaFilasSubtotal(new ListaCadenas());
  }
  public Aplicacion getAplicacionActual() {
    return this.aAplicacionActual;
  }
  public void setAplicacionActual(Aplicacion pAplicacionActual) {
    this.aAplicacionActual = pAplicacionActual;
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
    getResultadoConsultaSQL().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
    getManejadorCampoEnlazado().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
    getResultadoConsultaSQL().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
    getManejadorCampoEnlazado().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
  }
  public ManejadorResultadoConsultaSQL getResultadoConsultaSQL() {
    return this.aResultadoConsultaSQL;
  }
  public void setResultadoConsultaSQL(ManejadorResultadoConsultaSQL pResultadoConsultaSQL) {
    this.aResultadoConsultaSQL = pResultadoConsultaSQL;
  }
  public ManejadorCampoEnlazado getManejadorCampoEnlazado() {
    return this.aManejadorCampoEnlazado;
  }
  public void setManejadorCampoEnlazado(ManejadorCampoEnlazado pManejadorCampoEnlazado) {
    this.aManejadorCampoEnlazado = pManejadorCampoEnlazado;
  }
  public String getNombreReporte() {
    return this.aNombreReporte;
  }
  public void setNombreReporte(String pNombreReporte) {
    this.aNombreReporte = pNombreReporte;
  }
  public BloqueGeneral getBloqueGeneral() {
    return this.aBloqueGeneral;
  }
  public void setBloqueGeneral(BloqueGeneral pBloqueGeneral) {
    this.aBloqueGeneral = pBloqueGeneral;
  }
  public BloqueEncabezadoPie getBloqueEncabezado() {
    return this.aBloqueEncabezado;
  }
  public void setBloqueEncabezado(BloqueEncabezadoPie pBloqueEncabezado) {
    this.aBloqueEncabezado = pBloqueEncabezado;
  }
  public BloqueEncabezadoPie getBloquePie() {
    return this.aBloquePie;
  }
  public void setBloquePie(BloqueEncabezadoPie pBloquePie) {
    this.aBloquePie = pBloquePie;
  }
  public BloqueColumnas getBloqueColumnas() {
    return this.aBloqueColumnas;
  }
  public void setBloqueColumnas(BloqueColumnas pBloqueColumnas) {
    this.aBloqueColumnas = pBloqueColumnas;
  }
  public BloqueSubtotal getBloqueSubtotal() {
    return this.aBloqueSubtotal;
  }
  public void setBloqueSubtotal(BloqueSubtotal pBloqueSubtotal) {
    this.aBloqueSubtotal = pBloqueSubtotal;
  }
  public BloqueTotal getBloqueTotal() {
    return this.aBloqueTotal;
  }
  public void setBloqueTotal(BloqueTotal pBloqueTotal) {
    this.aBloqueTotal = pBloqueTotal;
  }
  public Usuario getUsuario() {
    return this.aUsuario;
  }
  public void setUsuario(Usuario pUsuario) {
    this.aUsuario = pUsuario;
  }
  public String getIdSesion() {
    return this.aIdSesion;
  }
  public void setIdSesion(String pIdSesion) {
    this.aIdSesion = pIdSesion;
  }
  public String getFecha() {
    return this.aFecha;
  }
  public void setFecha(String pFecha) {
    this.aFecha = pFecha;
  }
  public String getHora() {
    return this.aHora;
  }
  public void setHora(String pHora) {
    this.aHora = pHora;
  }
  public ListaCadenas getListaFilasSubtotal() {
    return this.aListaFilasSubtotal;
  }
  public void setListaFilasSubtotal(ListaCadenas pListaFilasSubtotal) {
    this.aListaFilasSubtotal = pListaFilasSubtotal;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
    getManejadorCampoEnlazado().setMotorAplicacion(pMotorAplicacion);
  }
  public void cargarReporte() {
    String estructuraReporte_local = null;
    String estructuraEncabezado_local = null;
    String estructuraPie_local = null;
    CargadorReporte cargadorReporte_local = null;
    BloqueGeneral bloqueGeneral_local = null;
    BloqueEncabezadoPie bloqueEncabezado_local = null;
    BloqueEncabezadoPie bloquePie_local = null;
    BloqueColumnas bloqueColumnas_local = null;
    BloqueSubtotal bloqueSubtotal_local = null;
    BloqueTotal bloqueTotal_local = null;
    ListaCadenas listaBloqueColumnas_local = null;
    
    try {
      cargadorReporte_local = new CargadorReporte(getAplicacionActual().getAplicacionReporte(), getValorLlavePrimariaReporte());
      cargadorReporte_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      cargadorReporte_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      cargadorReporte_local.setMotorAplicacion(getMotorAplicacion());
      setNombreReporte(cargadorReporte_local.obtenerNombreReporte());
      estructuraReporte_local = mc.convertirAMayusculas(cargadorReporte_local.obtenerEstructuraReporte());
      
      bloqueGeneral_local = new BloqueGeneral(cargadorReporte_local.obtenerTamanoPagina(cargadorReporte_local.obtenerBloqueGeneral(estructuraReporte_local)));
      estructuraEncabezado_local = cargadorReporte_local.obtenerEstructuraEncabezado(estructuraReporte_local, getFecha(), getHora());
      bloqueEncabezado_local = new BloqueEncabezadoPie(cargadorReporte_local.obtenerBloqueIzquierda(estructuraEncabezado_local), cargadorReporte_local.obtenerBloqueCentro(estructuraEncabezado_local), cargadorReporte_local.obtenerBloqueDerecha(estructuraEncabezado_local));
      
      estructuraPie_local = cargadorReporte_local.obtenerEstructuraPie(estructuraReporte_local, getFecha(), getHora());
      bloquePie_local = new BloqueEncabezadoPie(cargadorReporte_local.obtenerBloqueIzquierda(estructuraPie_local), cargadorReporte_local.obtenerBloqueCentro(estructuraPie_local), cargadorReporte_local.obtenerBloqueDerecha(estructuraPie_local));
      
      listaBloqueColumnas_local = cargadorReporte_local.obtenerBloqueColumnas(estructuraReporte_local);
      bloqueColumnas_local = new BloqueColumnas(cargadorReporte_local.verificarMostrarDetallesColumnas(listaBloqueColumnas_local), cargadorReporte_local.cargarListaColumnasReporte(getAplicacionActual().getIdAplicacion(), listaBloqueColumnas_local), cargadorReporte_local.cargarListaOrdenReporte(getAplicacionActual().getIdAplicacion(), listaBloqueColumnas_local));
      
      bloqueSubtotal_local = new BloqueSubtotal(cargadorReporte_local.cargarListaSubtotalReporte(getAplicacionActual().getIdAplicacion(), cargadorReporte_local.obtenerBloqueSubtotales(estructuraReporte_local)));
      
      bloqueTotal_local = new BloqueTotal(cargadorReporte_local.obtenerListaCamposTotales(getAplicacionActual().getIdAplicacion(), cargadorReporte_local.obtenerBloqueTotalFinal(estructuraReporte_local)));
      
      setBloqueGeneral(bloqueGeneral_local);
      setBloqueEncabezado(bloqueEncabezado_local);
      setBloquePie(bloquePie_local);
      setBloqueColumnas(bloqueColumnas_local);
      setBloqueSubtotal(bloqueSubtotal_local);
      setBloqueTotal(bloqueTotal_local);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloquePie_local = null;
      bloqueTotal_local = null;
      bloqueGeneral_local = null;
      estructuraPie_local = null;
      bloqueColumnas_local = null;
      bloqueSubtotal_local = null;
      cargadorReporte_local = null;
      bloqueEncabezado_local = null;
      estructuraReporte_local = null;
      listaBloqueColumnas_local = null;
      estructuraEncabezado_local = null;
    } 
  }
  private void crearTituloReporte(HSSFSheet pHoja, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    HSSFRow filaTitulo_local = null;
    HSSFCell celdaTitulo_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      filaTitulo_local = pGeneradorArchivoExcel.crearFila(pHoja, 1);
      celdaTitulo_local = pGeneradorArchivoExcel.crearCeldaCombinada(pHoja, filaTitulo_local, 1, 6, 6);
      
      pGeneradorArchivoExcel.asignarEstiloNegrita(celdaTitulo_local, 12);
      pGeneradorArchivoExcel.asignarValorCelda(celdaTitulo_local, getNombreReporte());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      filaTitulo_local = null;
      celdaTitulo_local = null;
    } 
  }
  private void crearCeldasEncabezadoReporte(HSSFSheet pHoja, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int contadorFilas_local = 0;
    int contadorColumnas_local = 0;
    int auxiliar_local = 0;
    HSSFRow fila_local = null;
    HSSFCell celda_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      for (contadorFilas_local = 0; contadorFilas_local < getBloqueEncabezado().getNumeroLineas(); contadorFilas_local++) {
        fila_local = pGeneradorArchivoExcel.crearFila(pHoja, contadorFilas_local + 3);
        auxiliar_local = 1;
        for (contadorColumnas_local = 0; contadorColumnas_local < 3; contadorColumnas_local++) {
          celda_local = pGeneradorArchivoExcel.crearCeldaCombinada(pHoja, fila_local, contadorColumnas_local + auxiliar_local, 2, 2);
          
          pGeneradorArchivoExcel.asignarEstiloNegrita(celda_local, 10);
          auxiliar_local++;
        } 
      } 
      asignarValoresCeldasEncabezado(pHoja, pGeneradorArchivoExcel);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fila_local = null;
      celda_local = null;
    } 
  }
  private void asignarValoresCeldasListaEncabezadoPie(HSSFSheet pHoja, int pFilaInicial, int pNumeroColumna, ListaCadenas pListaValoresCeldas, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int contador_local = 0;
    Iterator iterador_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaValoresCeldas == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = pListaValoresCeldas.iterator();
      contador_local = pFilaInicial;
      while (iterador_local.hasNext()) {
        pGeneradorArchivoExcel.asignarValorCelda(pGeneradorArchivoExcel.obtenerCelda(pGeneradorArchivoExcel.obtenerFila(pHoja, contador_local), pNumeroColumna), (String)iterador_local.next());
        
        contador_local++;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
  }
  private void asignarValoresCeldasEncabezado(HSSFSheet pHoja, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      asignarValoresCeldasListaEncabezadoPie(pHoja, 3, 1, getBloqueEncabezado().getLineasIzquierda(), pGeneradorArchivoExcel);
      
      asignarValoresCeldasListaEncabezadoPie(pHoja, 3, 3, getBloqueEncabezado().getLineasCentro(), pGeneradorArchivoExcel);
      
      asignarValoresCeldasListaEncabezadoPie(pHoja, 3, 5, getBloqueEncabezado().getLineasDerecha(), pGeneradorArchivoExcel);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private ListaConsulta extraerListaConsultaReporte(ListaConsulta pListaConsulta) {
    ListaConsulta listaConsulta_local = null;
    Iterator iterador_local = null;
    ItemConsulta itemConsulta_local = null;
    
    if (pListaConsulta == ConstantesGeneral.VALOR_NULO) {
      return listaConsulta_local;
    }
    
    try {
      iterador_local = pListaConsulta.iterator();
      listaConsulta_local = new ListaConsulta();
      while (iterador_local.hasNext()) {
        itemConsulta_local = (ItemConsulta)iterador_local.next();
        if (!itemConsulta_local.esOrdenadoPor()) {
          listaConsulta_local.adicionar(itemConsulta_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      itemConsulta_local = null;
    } 
    return listaConsulta_local;
  }
  private void complementarOrdenListaConsulta(ListaConsulta pListaConsulta) {
    ListaOrdenReporte listaOrdenReporte_local = null;
    OrdenReporte ordenReporte_local = null;
    Iterator iteradorOrden_local = null;
    ItemConsulta itemConsulta_local = null;
    Campo campo_local = null;
    
    if (pListaConsulta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getBloqueColumnas() != ConstantesGeneral.VALOR_NULO) {
        listaOrdenReporte_local = getBloqueColumnas().getListaOrdenReporte();
        if (listaOrdenReporte_local != ConstantesGeneral.VALOR_NULO) {
          iteradorOrden_local = listaOrdenReporte_local.iterator();
          while (iteradorOrden_local.hasNext()) {
            ordenReporte_local = (OrdenReporte)iteradorOrden_local.next();
            campo_local = ordenReporte_local.getCampo();
            itemConsulta_local = new ItemConsulta(campo_local, campo_local, pListaConsulta.obtenerUltimoNivel() + 1, true, (ordenReporte_local.getTipoOrdenamiento() == 1), false);
            
            pListaConsulta.adicionar(itemConsulta_local);
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      itemConsulta_local = null;
      ordenReporte_local = null;
      iteradorOrden_local = null;
      listaOrdenReporte_local = null;
    } 
  }
  private String obtenerConsultaReporte(ListaConsulta pListaConsulta) {
    String consultaReporte_local = "";
    ListaConsulta listaConsulta_local = null;
    ListaConsulta listaConsultaRestricciones_local = null;
    ConsultaSQLPrincipal consultaSQLPrincipal_local = null;
    
    if (pListaConsulta == ConstantesGeneral.VALOR_NULO) {
      return consultaReporte_local;
    }
    
    try {
      listaConsulta_local = extraerListaConsultaReporte(pListaConsulta);
      complementarOrdenListaConsulta(listaConsulta_local);
      consultaSQLPrincipal_local = new ConsultaSQLPrincipal(getAplicacionActual(), listaConsulta_local);
      consultaSQLPrincipal_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      consultaSQLPrincipal_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      consultaSQLPrincipal_local.setMotorAplicacion(getMotorAplicacion());
      consultaSQLPrincipal_local.setUsuario(getUsuario());
      consultaSQLPrincipal_local.setIdSesion(getIdSesion());
      consultaSQLPrincipal_local.setPermitirConsultaSinOpciones(getAplicacionActual().esPermitirConsultaGeneral());
      
      listaConsultaRestricciones_local = consultaSQLPrincipal_local.obtenerListaConsultaRestriccionesAplicacion(getUsuario().getTipoUsuario().getIdTipoUsuario(), getAplicacionActual(), true);
      
      if (listaConsultaRestricciones_local == ConstantesGeneral.VALOR_NULO || listaConsultaRestricciones_local.contarElementos() == 0) {
        
        listaConsultaRestricciones_local = consultaSQLPrincipal_local.obtenerListaConsultaRestriccionesAplicacion(getUsuario().getTipoUsuario().getIdTipoUsuario(), getAplicacionActual(), false);
      } else {
        
        consultaSQLPrincipal_local.setSoloRegistrosDonde(true);
      } 
      consultaSQLPrincipal_local.setListaConsultaRestricciones(listaConsultaRestricciones_local);
      
      consultaReporte_local = consultaSQLPrincipal_local.obtenerConsultaSQLPrincipal();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaConsulta_local = null;
      listaConsultaRestricciones_local = null;
      consultaSQLPrincipal_local = null;
    } 
    return consultaReporte_local;
  }
  private void crearCeldasEncabezado(HSSFSheet pHoja, int pNumeroFila, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroColumna_local = 0;
    HSSFRow filaEncabezado_local = null;
    HSSFCell celdaEncabezado_local = null;
    ListaColumnasReporte listaColumnasReporte_local = null;
    ColumnaReporte columnaReporte_local = null;
    Iterator iterador_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      filaEncabezado_local = pGeneradorArchivoExcel.crearFila(pHoja, pNumeroFila);
      listaColumnasReporte_local = getBloqueColumnas().getListaColumnasReporte();
      if (listaColumnasReporte_local != ConstantesGeneral.VALOR_NULO) {
        numeroColumna_local = 0;
        iterador_local = listaColumnasReporte_local.iterator();
        while (iterador_local.hasNext()) {
          columnaReporte_local = (ColumnaReporte)iterador_local.next();
          celdaEncabezado_local = pGeneradorArchivoExcel.crearCeldaEncabezado(filaEncabezado_local, numeroColumna_local);
          pGeneradorArchivoExcel.asignarAnchoColumna(pHoja, numeroColumna_local, columnaReporte_local.getAnchoColumna() * 10);
          pGeneradorArchivoExcel.asignarValorCelda(celdaEncabezado_local, columnaReporte_local.getNombreColumna());
          numeroColumna_local++;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      filaEncabezado_local = null;
      columnaReporte_local = null;
      celdaEncabezado_local = null;
      listaColumnasReporte_local = null;
    } 
  }
  private String obtenerValorCampoGrupoNoMultiple(Campo pCampo, int pValorLlavePrimaria) {
    String valorCampo_local = "";
    String nombreLlavePrimaria_local = null;
    Object valor_local = null;
    Aplicacion aplicacionEnlazada_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    
    try {
      aplicacionEnlazada_local = pCampo.getEnlaceCampo().getEnlazado();
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(getAplicacionActual().getNombreAplicacion());
      valor_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(pCampo, nombreLlavePrimaria_local, pValorLlavePrimaria);
      if (valor_local != ConstantesGeneral.VALOR_NULO) {
        valorCampo_local = valor_local.toString();
      }
      if (pCampo.esTipoDatoTabla()) {
        if (mc.esCadenaNumerica(valorCampo_local, true)) {
          valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(pCampo.getFormatoCampo().getTipoDato())).getNombreTabla(), Integer.parseInt(valorCampo_local));
        } else {
          
          valorCampo_local = "";
        } 
      }
      if (aplicacionEnlazada_local != ConstantesGeneral.VALOR_NULO) {
        if (mc.esCadenaNumerica(valorCampo_local, true)) {
          valorCampo_local = getManejadorCampoEnlazado().obtenerValorCampoEnlazado(aplicacionEnlazada_local, Integer.parseInt(valorCampo_local));
        } else {
          valorCampo_local = "";
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valor_local = null;
      nombreLlavePrimaria_local = null;
      aplicacionEnlazada_local = null;
    } 
    return valorCampo_local;
  }
  private void crearCeldasValoresCampoGrupoMultiple(HSSFSheet pHoja, ColumnaReporte pColumnaReporte, ResultSet pResultSet, int pNumeroFilaInicial, int pNumeroRegistros, int pTipoRepeticion, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int contadorFila_local = -1;
    boolean esTipoDatoEntero_local = false;
    String valorCampo_local = null;
    Campo campo_local = null;
    Object valor_local = null;
    HSSFRow fila_local = null;
    Aplicacion aplicacionEnlazada_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pColumnaReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pResultSet == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      campo_local = pColumnaReporte.getCampo();
      aplicacionEnlazada_local = campo_local.getEnlaceCampo().getEnlazado();
      esTipoDatoEntero_local = campo_local.esTipoDatoNumeroEntero();
      
      for (contadorFila_local = pNumeroFilaInicial; contadorFila_local < pNumeroRegistros + pNumeroFilaInicial; contadorFila_local++) {
        pGeneradorArchivoExcel.crearFila(pHoja, contadorFila_local);
      }
      contadorFila_local = pNumeroFilaInicial;
      while (pResultSet.next()) {
        valor_local = pResultSet.getObject(campo_local.getNombreCampo());
        if (valor_local != ConstantesGeneral.VALOR_NULO) {
          valorCampo_local = valor_local.toString();
        } else {
          valorCampo_local = "";
        } 
        if (campo_local.esTipoDatoTabla()) {
          if (mc.esCadenaNumerica(valorCampo_local, true)) {
            valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(campo_local.getFormatoCampo().getTipoDato())).getNombreTabla(), Integer.parseInt(valorCampo_local));
          } else {
            
            valorCampo_local = "";
          } 
        }
        if (aplicacionEnlazada_local != ConstantesGeneral.VALOR_NULO) {
          if (mc.esCadenaNumerica(valorCampo_local, true)) {
            valorCampo_local = getManejadorCampoEnlazado().obtenerValorCampoEnlazado(aplicacionEnlazada_local, Integer.parseInt(valorCampo_local));
          } else {
            valorCampo_local = "";
          } 
        }
        valorCampo_local = obtenerValorCampoConFormato(pColumnaReporte, valorCampo_local);
        fila_local = pGeneradorArchivoExcel.obtenerFila(pHoja, contadorFila_local);
        if (campo_local.esTipoDatoNumerico() && mc.esCadenaNumerica(valorCampo_local, esTipoDatoEntero_local) && aplicacionEnlazada_local == ConstantesGeneral.VALOR_NULO) {
          
          if (esTipoDatoEntero_local) {
            pGeneradorArchivoExcel.crearCeldaNumeroEntero(fila_local, pColumnaReporte.getNumeroColumna(), Integer.parseInt(valorCampo_local));
          } else {
            pGeneradorArchivoExcel.crearCeldaNumeroDecimal(fila_local, pColumnaReporte.getNumeroColumna(), Double.parseDouble(valorCampo_local));
          } 
        } else {
          pGeneradorArchivoExcel.crearCeldaTexto(fila_local, pColumnaReporte.getNumeroColumna(), valorCampo_local);
        } 
        contadorFila_local++;
        if (pTipoRepeticion == 3) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fila_local = null;
      valor_local = null;
      campo_local = null;
      valorCampo_local = null;
      aplicacionEnlazada_local = null;
    } 
  }
  private void obtenerValoresCampoGrupoMultiple(HSSFSheet pHoja, ColumnaReporte pColumnaReporte, int pValorLlavePrimaria, int pNumeroFilaInicial, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int tipoRepeticion_local = -1;
    int valorLlavePrimariaGrupoInformacion_local = -1;
    String nombreGrupoInformacion_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreLlavePrimariaGrupoInformacion_local = null;
    String consulta_local = null;
    String nombreCampo_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    Campo campo_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pColumnaReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      campo_local = pColumnaReporte.getCampo();
      nombreCampo_local = campo_local.getNombreCampo();
      tipoRepeticion_local = pColumnaReporte.getTipoRepeticion();
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(getAplicacionActual().getNombreAplicacion());
      nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getNombreGrupoInformacion();
      if (tipoRepeticion_local == 4) {
        nombreLlavePrimariaGrupoInformacion_local = ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local);
        valorLlavePrimariaGrupoInformacion_local = getAdministradorBaseDatosAplicacion().obtenerIdUltimoRegistro(nombreGrupoInformacion_local, nombreLlavePrimariaGrupoInformacion_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimaria);
        
        consulta_local = ca.conformarConsultaSQLUltimoValorCampoGrupoMultiple(nombreGrupoInformacion_local, nombreLlavePrimariaGrupoInformacion_local, nombreCampo_local, valorLlavePrimariaGrupoInformacion_local);
      } else {
        
        consulta_local = ca.conformarConsultaSQLValoresCampoGrupoMultiple(getAplicacionActual().getNombreAplicacion(), nombreLlavePrimariaPrincipal_local, nombreGrupoInformacion_local, nombreCampo_local, pValorLlavePrimaria);
      } 
      
      manejadorConsultaSQL_local = getResultadoConsultaSQL().obtenerManejadorConsultaAplicacion(consulta_local);
      resultSet_local = manejadorConsultaSQL_local.getResultSet();
      crearCeldasValoresCampoGrupoMultiple(pHoja, pColumnaReporte, resultSet_local, pNumeroFilaInicial, manejadorConsultaSQL_local.getNumeroRegistros(), tipoRepeticion_local, pGeneradorArchivoExcel);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      consulta_local = null;
      resultSet_local = null;
      nombreLlavePrimariaPrincipal_local = null;
      manejadorConsultaSQL_local = null;
      nombreGrupoInformacion_local = null;
      nombreLlavePrimariaGrupoInformacion_local = null;
    } 
  }
  private String obtenerSubtotalCampo(Campo pCampo, int pValorLlavePrimaria) {
    String subtotalCampo_local = "";
    String nombreLlavePrimaria_local = null;
    String consulta_local = null;
    ResultSet resultSet_local = null;
    Object valor_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return subtotalCampo_local;
    }
    
    try {
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(ap.conformarNombreCampoLlavePrimaria(getAplicacionActual().getNombreAplicacion()));
      consulta_local = ca.conformarConsultaSQLSubtotalCampo(getAplicacionActual().getNombreAplicacion(), nombreLlavePrimaria_local, pCampo.getGrupoInformacion().getNombreGrupoInformacion(), pCampo.getNombreCampo(), pValorLlavePrimaria);
      
      resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consulta_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        if (resultSet_local.next()) {
          valor_local = resultSet_local.getObject("totalcampo");
        }
        if (valor_local != ConstantesGeneral.VALOR_NULO) {
          subtotalCampo_local = valor_local.toString();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valor_local = null;
      consulta_local = null;
      resultSet_local = null;
      nombreLlavePrimaria_local = null;
    } 
    return subtotalCampo_local;
  }
  private String obtenerValorCampoConFormato(ColumnaReporte pColumnaReporte, String pValorCampo) {
    String valorCampo_local = "";
    String formatoCampo_local = null;
    String formatoValorCampo_local = null;
    String estiloValorCampo_local = null;
    
    if (pColumnaReporte == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    
    try {
      valorCampo_local = pValorCampo;
      formatoCampo_local = pColumnaReporte.getFormatoEstilo();
      formatoValorCampo_local = ap.obtenerFormatoValorCampo(formatoCampo_local);
      estiloValorCampo_local = ap.obtenerEstiloValorCampo(formatoCampo_local);
      valorCampo_local = ap.obtenerValorCampoConFormato(valorCampo_local, pColumnaReporte.getCampo().esTipoDatoNumeroEntero(), pColumnaReporte.getCampo().esTipoDatoNumeroReal(), pColumnaReporte.getCampo().esTipoDatoFecha(), pColumnaReporte.getCampo().esTipoDatoHora(), formatoValorCampo_local, ap.obtenerComplementoFormatoNumericoCampo(formatoCampo_local));
      
      valorCampo_local = ap.obtenerValorCampoConEstilo(valorCampo_local, estiloValorCampo_local);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      formatoCampo_local = null;
      estiloValorCampo_local = null;
      formatoValorCampo_local = null;
    } 
    return valorCampo_local;
  }
  private void crearFilasRegistroContenidoReporte(HSSFSheet pHoja, int pNumeroFila, int pValorLlavePrimaria, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroColumna_local = -1;
    boolean mostrarDetalles_local = false;
    boolean esTipoDatoEntero_local = false;
    String valorCelda_local = null;
    HSSFRow filaContenido_local = null;
    ListaColumnasReporte listaColumnasReporte_local = null;
    Iterator iteradorColumnas_local = null;
    ColumnaReporte columnaReporte_local = null;
    Campo campo_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      mostrarDetalles_local = getBloqueColumnas().esMostrarDetalle();
      listaColumnasReporte_local = getBloqueColumnas().getListaColumnasReporte();
      if (listaColumnasReporte_local != ConstantesGeneral.VALOR_NULO) {
        iteradorColumnas_local = listaColumnasReporte_local.iterator();
        filaContenido_local = pGeneradorArchivoExcel.crearFila(pHoja, pNumeroFila);
        while (iteradorColumnas_local.hasNext()) {
          columnaReporte_local = (ColumnaReporte)iteradorColumnas_local.next();
          campo_local = columnaReporte_local.getCampo();
          esTipoDatoEntero_local = campo_local.esTipoDatoNumeroEntero();
          numeroColumna_local = columnaReporte_local.getNumeroColumna();
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            valorCelda_local = obtenerValorCampoGrupoNoMultiple(campo_local, pValorLlavePrimaria);
            valorCelda_local = obtenerValorCampoConFormato(columnaReporte_local, valorCelda_local);
            if (campo_local.esTipoDatoNumerico() && mc.esCadenaNumerica(valorCelda_local, esTipoDatoEntero_local) && !campo_local.esCampoEnlazado()) {
              
              if (esTipoDatoEntero_local) {
                pGeneradorArchivoExcel.crearCeldaNumeroEntero(filaContenido_local, numeroColumna_local, Integer.parseInt(valorCelda_local));
                continue;
              } 
              pGeneradorArchivoExcel.crearCeldaNumeroDecimal(filaContenido_local, numeroColumna_local, Double.parseDouble(valorCelda_local));
              
              continue;
            } 
            pGeneradorArchivoExcel.crearCeldaTexto(filaContenido_local, numeroColumna_local, valorCelda_local);
            continue;
          } 
          if ((!mostrarDetalles_local || columnaReporte_local.getTipoRepeticion() == 5) && campo_local.esTipoDatoNumerico() && !campo_local.esCampoEnlazado()) {
            
            valorCelda_local = obtenerSubtotalCampo(campo_local, pValorLlavePrimaria);
            if (mc.esCadenaNumerica(valorCelda_local, esTipoDatoEntero_local)) {
              if (esTipoDatoEntero_local) {
                pGeneradorArchivoExcel.crearCeldaNumeroEntero(filaContenido_local, numeroColumna_local, Integer.parseInt(valorCelda_local));
                continue;
              } 
              pGeneradorArchivoExcel.crearCeldaNumeroDecimal(filaContenido_local, numeroColumna_local, Double.parseDouble(valorCelda_local));
              
              continue;
            } 
            pGeneradorArchivoExcel.crearCeldaTexto(filaContenido_local, numeroColumna_local, valorCelda_local);
            continue;
          } 
          obtenerValoresCampoGrupoMultiple(pHoja, columnaReporte_local, pValorLlavePrimaria, pNumeroFila, pGeneradorArchivoExcel);
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      valorCelda_local = null;
      filaContenido_local = null;
      columnaReporte_local = null;
      iteradorColumnas_local = null;
      listaColumnasReporte_local = null;
    } 
  }
  private void insertarFinalSubtotal(HSSFSheet pHoja, int pNumeroFila, SubtotalReporte pSubtotalReporte, String pValorCampoDetalle, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroColumna_local = -1;
    String valorCelda_local = null;
    String descripcionFinal_local = null;
    Campo campo_local = null;
    HSSFRow fila_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pSubtotalReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorCampoDetalle == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      campo_local = pSubtotalReporte.getCampoDetalle();
      descripcionFinal_local = pSubtotalReporte.getDesripcionFinalSubreporte();
      if (!mc.sonCadenasIguales(descripcionFinal_local, "")) {
        numeroColumna_local = obtenerNumeroColumnaCampo(campo_local.getIdCampo());
        valorCelda_local = mc.concatenarCadena(descripcionFinal_local + ' ', pValorCampoDetalle);
        fila_local = pGeneradorArchivoExcel.crearFila(pHoja, pNumeroFila);
        pGeneradorArchivoExcel.crearCeldaTextoConNegrita(fila_local, numeroColumna_local, valorCelda_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fila_local = null;
      campo_local = null;
      valorCelda_local = null;
      descripcionFinal_local = null;
    } 
  }
  private void insertarLineasInicioSubtotal(HSSFSheet pHoja, int pValorLlavePrimaria, int pNumeroFila, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    SubtotalReporte subtotalReporte_local = null;
    Iterator iterador_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getBloqueSubtotal().getListaSubtotalReporte() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = getBloqueSubtotal().getListaSubtotalReporte().iterator();
        while (iterador_local.hasNext()) {
          subtotalReporte_local = (SubtotalReporte)iterador_local.next();
          if (subtotalReporte_local.esModificoValor()) {
            insertarEncabezadoSubtotal(pHoja, pNumeroFila, subtotalReporte_local, pValorLlavePrimaria, pGeneradorArchivoExcel);
            pNumeroFila = pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja) + 1;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      subtotalReporte_local = null;
    } 
  }
  private boolean insertarLineaSubtotal(HSSFSheet pHoja, int pValorLlavePrimaria, int pNumeroFilaInicial, int pNumeroFila, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    boolean lineaSubtotales_local = false;
    int numeroColumna_local = -1;
    String valorCampo_local = null;
    String valorCampoActual_local = null;
    SubtotalReporte subtotalReporte_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return lineaSubtotales_local;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return lineaSubtotales_local;
    }
    
    try {
      if (getBloqueSubtotal().getListaSubtotalReporte() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = getBloqueSubtotal().getListaSubtotalReporte().iterator();
        for (numeroColumna_local = 0; iterador_local.hasNext(); numeroColumna_local++) {
          subtotalReporte_local = (SubtotalReporte)iterador_local.next();
          campo_local = subtotalReporte_local.getCampoDetalle();
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            valorCampoActual_local = obtenerValorCampoGrupoNoMultiple(campo_local, pValorLlavePrimaria);
            valorCampo_local = subtotalReporte_local.getValorCampo();
            if (mc.sonCadenasIguales(valorCampo_local, "")) {
              subtotalReporte_local.setValorCampo(valorCampoActual_local);
              subtotalReporte_local.setNumeroFilaCambio(pNumeroFilaInicial);
            } else {
              subtotalReporte_local.setModificoValor(!mc.sonCadenasIguales(valorCampo_local, valorCampoActual_local));
              if (subtotalReporte_local.esModificoValor()) {
                subtotalReporte_local.setValorCampo(valorCampoActual_local);
                lineaSubtotales_local = true;
                insertarFinalSubtotal(pHoja, pNumeroFila, subtotalReporte_local, valorCampo_local, pGeneradorArchivoExcel);
              } 
            } 
          } 
        } 
        if (lineaSubtotales_local) {
          pNumeroFila = pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja) + 1;
          insertarLineasInicioSubtotal(pHoja, pValorLlavePrimaria, pNumeroFila, pGeneradorArchivoExcel);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      valorCampo_local = null;
      subtotalReporte_local = null;
    } 
    return lineaSubtotales_local;
  }
  private void crearCeldaFormulaSuma(HSSFSheet pHoja, int pNumeroFila, int pNumeroColumna, String pRango, String pFormatoCelda, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    String formulaSuma_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pRango == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFormatoCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      formulaSuma_local = mc.concatenarCadena("SUM", mc.colocarEntreParentesis(pRango));
      pGeneradorArchivoExcel.crearCeldaFormula(pGeneradorArchivoExcel.crearFila(pHoja, pNumeroFila), pNumeroColumna, formulaSuma_local, pFormatoCelda, true);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      formulaSuma_local = null;
    } 
  }
  private String obtenerEquivalenteColumna(int pNumeroColumna) {
    String equivalenteColumna_local = "";
    int numeroCaracteres_local = -1;
    int segundaPosicion_local = -1;
    int primeraPosicion_local = -1;
    
    try {
      numeroCaracteres_local = mc.obtenerLongitudCadena("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      primeraPosicion_local = pNumeroColumna / numeroCaracteres_local - 1;
      segundaPosicion_local = pNumeroColumna % numeroCaracteres_local;
      if (primeraPosicion_local != -1) {
        equivalenteColumna_local = String.valueOf(mc.obtenerCarater("ABCDEFGHIJKLMNOPQRSTUVWXYZ", primeraPosicion_local));
      }
      equivalenteColumna_local = mc.concatenarCadena(equivalenteColumna_local, String.valueOf(mc.obtenerCarater("ABCDEFGHIJKLMNOPQRSTUVWXYZ", segundaPosicion_local)));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return equivalenteColumna_local;
  }
  private String conformarRangoVertical(int pNumeroFilaInicial, int pNumeroFilaFinal, int pNumeroColumna) {
    String rangoVertical_local = "";
    String equivalenteColumna_local = null;
    
    try {
      equivalenteColumna_local = obtenerEquivalenteColumna(pNumeroColumna);
      rangoVertical_local = mc.concatenarCadena(equivalenteColumna_local + pNumeroFilaInicial + ':', equivalenteColumna_local + pNumeroFilaFinal);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      equivalenteColumna_local = null;
    } 
    return rangoVertical_local;
  }
  private int obtenerNumeroColumnaCampo(int pIdCampo) {
    int numeroColumna_local = -1;
    ColumnaReporte columnaReporte_local = null;
    Iterator iteradorColumnas_local = null;
    
    try {
      if (getBloqueColumnas().getListaColumnasReporte() != ConstantesGeneral.VALOR_NULO) {
        iteradorColumnas_local = getBloqueColumnas().getListaColumnasReporte().iterator();
        while (iteradorColumnas_local.hasNext()) {
          columnaReporte_local = (ColumnaReporte)iteradorColumnas_local.next();
          if (columnaReporte_local.getCampo().getIdCampo() == pIdCampo) {
            numeroColumna_local = columnaReporte_local.getNumeroColumna();
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      columnaReporte_local = null;
      iteradorColumnas_local = null;
    } 
    return numeroColumna_local;
  }
  private void subtotalizarReporte(HSSFSheet pHoja, int pNumeroFila, boolean pEsSubtotalCierre, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroColumna_local = -1;
    String rango_local = null;
    String formatoCelda_local = null;
    SubtotalReporte subtotalReporte_local = null;
    Iterator iteradorSubtotales_local = null;
    ListaCampo listacampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getBloqueSubtotal().getListaSubtotalReporte() != ConstantesGeneral.VALOR_NULO) {
        iteradorSubtotales_local = getBloqueSubtotal().getListaSubtotalReporte().iterator();
        while (iteradorSubtotales_local.hasNext()) {
          subtotalReporte_local = (SubtotalReporte)iteradorSubtotales_local.next();
          if (subtotalReporte_local.esModificoValor() || pEsSubtotalCierre) {
            listacampo_local = subtotalReporte_local.getListaCamposSubtotal();
            if (listacampo_local != ConstantesGeneral.VALOR_NULO) {
              iterador_local = listacampo_local.iterator();
              while (iterador_local.hasNext()) {
                campo_local = (Campo)iterador_local.next();
                numeroColumna_local = obtenerNumeroColumnaCampo(campo_local.getIdCampo());
                rango_local = conformarRangoVertical(subtotalReporte_local.getNumeroFilaCambio(), pNumeroFila, numeroColumna_local);
                if (campo_local.esTipoDatoNumeroEntero()) {
                  formatoCelda_local = "#,##0";
                } else {
                  formatoCelda_local = "#,##0.00";
                } 
                crearCeldaFormulaSuma(pHoja, pNumeroFila, numeroColumna_local, rango_local, formatoCelda_local, pGeneradorArchivoExcel);
              } 
            } 
            if (pEsSubtotalCierre) {
              insertarFinalSubtotal(pHoja, pNumeroFila, subtotalReporte_local, subtotalReporte_local.getValorCampo(), pGeneradorArchivoExcel);
            }
            
            subtotalReporte_local.setNumeroFilaCambio(pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja) + 2);
          } 
        } 
        getListaFilasSubtotal().adicionar(String.valueOf(pNumeroFila + 1));
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      rango_local = null;
      campo_local = null;
      iterador_local = null;
      listacampo_local = null;
      subtotalReporte_local = null;
      iteradorSubtotales_local = null;
    } 
  }
  private void complementarDatosReporteRepetir(HSSFSheet pHoja, int pNumeroFilaInicial, int pNumeroFilaFinal, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroColumna_local = -1;
    int contador_local = -1;
    String formula_local = null;
    String formatoCelda_local = null;
    ColumnaReporte columnaReporte_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    HSSFRow fila_local = null;
    
    try {
      if (pNumeroFilaInicial <= pNumeroFilaFinal && 
        getBloqueColumnas().getListaColumnasReporte() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = getBloqueColumnas().getListaColumnasReporte().iterator();
        while (iterador_local.hasNext()) {
          columnaReporte_local = (ColumnaReporte)iterador_local.next();
          campo_local = columnaReporte_local.getCampo();
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && columnaReporte_local.getTipoRepeticion() == 1)
          {
            numeroColumna_local = columnaReporte_local.getNumeroColumna();
            for (contador_local = pNumeroFilaInicial; contador_local <= pNumeroFilaFinal; contador_local++) {
              formula_local = mc.concatenarCadena(obtenerEquivalenteColumna(numeroColumna_local), String.valueOf(pNumeroFilaInicial));
              fila_local = pGeneradorArchivoExcel.obtenerFila(pHoja, contador_local);
              formatoCelda_local = "General";
              if (campo_local.esTipoDatoNumerico()) {
                if (campo_local.esTipoDatoNumeroEntero()) {
                  formatoCelda_local = "#,##0";
                }
                else if (campo_local.esTipoDatoNumeroReal()) {
                  formatoCelda_local = "#,##0.00";
                } 
              }
              
              pGeneradorArchivoExcel.crearCeldaFormula(fila_local, numeroColumna_local, formula_local, formatoCelda_local, false);
            }
          
          }
        
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fila_local = null;
      campo_local = null;
      formula_local = null;
      iterador_local = null;
      formatoCelda_local = null;
      columnaReporte_local = null;
    } 
  }
  private void insertarEncabezadoSubtotal(HSSFSheet pHoja, int pNumeroFila, SubtotalReporte pSubtotalReporte, int pValorLlavePrimaria, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroColumna_local = -1;
    String valorCampo_local = null;
    String valorCelda_local = null;
    String descripcionInicial_local = null;
    Campo campo_local = null;
    HSSFRow fila_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pSubtotalReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      campo_local = pSubtotalReporte.getCampoDetalle();
      descripcionInicial_local = pSubtotalReporte.getDesripcionInicialSubreporte();
      numeroColumna_local = obtenerNumeroColumnaCampo(campo_local.getIdCampo());
      valorCampo_local = obtenerValorCampoGrupoNoMultiple(campo_local, pValorLlavePrimaria);
      valorCelda_local = mc.concatenarCadena(descripcionInicial_local + ' ', valorCampo_local);
      fila_local = pGeneradorArchivoExcel.crearFila(pHoja, pNumeroFila);
      pGeneradorArchivoExcel.crearCeldaTextoConNegrita(fila_local, numeroColumna_local, valorCelda_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fila_local = null;
      campo_local = null;
      valorCampo_local = null;
      valorCelda_local = null;
      descripcionInicial_local = null;
    } 
  }
  private void crearFilasInicialesSubtalesReporte(HSSFSheet pHoja, int pNumeroFila, int pValorLlavePrimaria, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    String descripcionInicial_local = null;
    SubtotalReporte subtotalReporte_local = null;
    Iterator iterador_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (getBloqueSubtotal().getListaSubtotalReporte() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = getBloqueSubtotal().getListaSubtotalReporte().iterator();
        while (iterador_local.hasNext()) {
          subtotalReporte_local = (SubtotalReporte)iterador_local.next();
          descripcionInicial_local = subtotalReporte_local.getDesripcionInicialSubreporte();
          if (!mc.sonCadenasIguales(descripcionInicial_local, "")) {
            insertarEncabezadoSubtotal(pHoja, pNumeroFila, subtotalReporte_local, pValorLlavePrimaria, pGeneradorArchivoExcel);
            pNumeroFila++;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      subtotalReporte_local = null;
      descripcionInicial_local = null;
    } 
  }
  private int crearContenidoDatosReporte(HSSFSheet pHoja, int pNumeroFilaInicial, ListaConsulta pListaConsulta, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroFilaInicial_local = -1;
    int numeroFila_local = -1;
    int valorLlavePrimaria_local = -1;
    boolean insertarSubtotalCierre_local = false;
    String consultaReporte_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    ResultSet resultSet_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    if (pListaConsulta == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    
    try {
      consultaReporte_local = obtenerConsultaReporte(pListaConsulta);
      nombreLlavePrimariaPrincipal_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(getMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(getAplicacionActual().getIdAplicacion()), false);
      
      resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaReporte_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        numeroFila_local = pNumeroFilaInicial;
        numeroFilaInicial_local = pNumeroFilaInicial + 1;
        while (resultSet_local.next()) {
          valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrimariaPrincipal_local);
          if (insertarLineaSubtotal(pHoja, valorLlavePrimaria_local, numeroFilaInicial_local, numeroFila_local, pGeneradorArchivoExcel)) {
            pGeneradorArchivoExcel.crearFila(pHoja, numeroFila_local);
            subtotalizarReporte(pHoja, numeroFila_local, false, pGeneradorArchivoExcel);
            numeroFila_local = pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja) + 1;
            numeroFilaInicial_local = numeroFila_local + 1;
            insertarSubtotalCierre_local = true;
          }
          else if (numeroFilaInicial_local != 4 + getBloqueEncabezado().getNumeroLineas() + 2) {
            
            numeroFilaInicial_local = numeroFila_local + 1;
          } else {
            crearFilasInicialesSubtalesReporte(pHoja, numeroFila_local, valorLlavePrimaria_local, pGeneradorArchivoExcel);
            numeroFila_local = pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja) + 1;
            numeroFilaInicial_local = numeroFila_local + 1;
          } 
          
          crearFilasRegistroContenidoReporte(pHoja, numeroFila_local, valorLlavePrimaria_local, pGeneradorArchivoExcel);
          numeroFila_local = pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja);
          complementarDatosReporteRepetir(pHoja, numeroFilaInicial_local, numeroFila_local, pGeneradorArchivoExcel);
          numeroFila_local++;
        } 
        if (insertarSubtotalCierre_local) {
          pGeneradorArchivoExcel.crearFila(pHoja, numeroFila_local);
          subtotalizarReporte(pHoja, numeroFila_local, insertarSubtotalCierre_local, pGeneradorArchivoExcel);
          numeroFila_local++;
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      resultSet_local = null;
      consultaReporte_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return numeroFila_local;
  }
  private String conformarFormulaSumaSubtotales(int pNumeroColumna) {
    String formula_local = "";
    String equivalenteColumna_local = null;
    Iterator iterador_local = null;
    
    try {
      if (getListaFilasSubtotal() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = getListaFilasSubtotal().iterator();
        equivalenteColumna_local = obtenerEquivalenteColumna(pNumeroColumna);
        while (iterador_local.hasNext()) {
          formula_local = mc.concatenarCadena(formula_local, equivalenteColumna_local + (String)iterador_local.next());
          if (iterador_local.hasNext()) {
            formula_local = mc.concatenarCadena(formula_local, String.valueOf('+'));
          }
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      equivalenteColumna_local = null;
    } 
    return formula_local;
  }
  private int crearFilaTotales(HSSFSheet pHoja, int pNumeroFila, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int numeroFila_local = -1;
    int numeroColumna_local = -1;
    String formula_local = null;
    String formatoCelda_local = null;
    Campo campo_local = null;
    Iterator iteradorCamposTotalizar_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    
    try {
      if (getBloqueTotal().getListaCampo() != ConstantesGeneral.VALOR_NULO) {
        iteradorCamposTotalizar_local = getBloqueTotal().getListaCampo().iterator();
        while (iteradorCamposTotalizar_local.hasNext()) {
          campo_local = (Campo)iteradorCamposTotalizar_local.next();
          numeroColumna_local = obtenerNumeroColumnaCampo(campo_local.getIdCampo());
          formula_local = conformarFormulaSumaSubtotales(numeroColumna_local);
          if (campo_local.esTipoDatoNumeroEntero()) {
            formatoCelda_local = "#,##0";
          } else {
            formatoCelda_local = "#,##0.00";
          } 
          pGeneradorArchivoExcel.crearCeldaFormula(pGeneradorArchivoExcel.crearFila(pHoja, pNumeroFila), numeroColumna_local, formula_local, formatoCelda_local, true);
        } 
        
        numeroFila_local++;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      formula_local = null;
      formatoCelda_local = null;
      iteradorCamposTotalizar_local = null;
    } 
    return numeroFila_local;
  }
  private void asignarValoresCeldasPie(HSSFSheet pHoja, int pNumeroFilaInicial, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      asignarValoresCeldasListaEncabezadoPie(pHoja, pNumeroFilaInicial, 1, getBloquePie().getLineasIzquierda(), pGeneradorArchivoExcel);
      
      asignarValoresCeldasListaEncabezadoPie(pHoja, pNumeroFilaInicial, 3, getBloquePie().getLineasCentro(), pGeneradorArchivoExcel);
      
      asignarValoresCeldasListaEncabezadoPie(pHoja, pNumeroFilaInicial, 5, getBloquePie().getLineasDerecha(), pGeneradorArchivoExcel);
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void crearCeldasPieReporte(HSSFSheet pHoja, GeneradorArchivoExcel pGeneradorArchivoExcel) {
    int filaInicial_local = 0;
    int contadorFilas_local = 0;
    int contadorColumnas_local = 0;
    int auxiliar_local = 0;
    HSSFRow fila_local = null;
    HSSFCell celda_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pGeneradorArchivoExcel == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      filaInicial_local = pGeneradorArchivoExcel.obtenerNumeroUltimaFila(pHoja);
      for (contadorFilas_local = filaInicial_local; contadorFilas_local < filaInicial_local + getBloquePie().getNumeroLineas(); contadorFilas_local++) {
        fila_local = pGeneradorArchivoExcel.crearFila(pHoja, contadorFilas_local + 3);
        auxiliar_local = 1;
        for (contadorColumnas_local = 0; contadorColumnas_local < 3; contadorColumnas_local++) {
          celda_local = pGeneradorArchivoExcel.crearCeldaCombinada(pHoja, fila_local, contadorColumnas_local + auxiliar_local, 2, 2);
          
          pGeneradorArchivoExcel.asignarEstiloNegrita(celda_local, 10);
          auxiliar_local++;
        } 
      } 
      filaInicial_local += 3;
      asignarValoresCeldasPie(pHoja, filaInicial_local, pGeneradorArchivoExcel);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fila_local = null;
      celda_local = null;
    } 
  }
  private String obtenerNombreHojaReporte() {
    String nombreHojaReporte_local = "";
    
    try {
      nombreHojaReporte_local = mc.convertirCadenaFormatoNombre(getNombreReporte());
      if (mc.esCadenaVacia(nombreHojaReporte_local)) {
        nombreHojaReporte_local = "Hoja_01";
      }
      if (mc.obtenerLongitudCadena(nombreHojaReporte_local) > 31) {
        nombreHojaReporte_local = mc.obtenerSubCadena(nombreHojaReporte_local, 0, 31);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return nombreHojaReporte_local;
  }
  public void generarReporte(String pUsuario, String pRuta, ListaConsulta pListaConsulta) {
    int numeroFila_local = -1;
    String nombreArchivo_local = null;
    GeneradorArchivoExcel generadorArchivoExcel_local = null;
    HSSFSheet hoja_local = null;
    
    if (pUsuario == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pRuta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaConsulta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      nombreArchivo_local = mc.concatenarCadena(getNombreReporte() + '-', pUsuario);
      generadorArchivoExcel_local = new GeneradorArchivoExcel(nombreArchivo_local, mc.concatenarCadena(pRuta, System.getProperty("file.separator")));
      
      hoja_local = generadorArchivoExcel_local.crearHoja(mc.concatenarCadena(obtenerNombreHojaReporte(), String.valueOf('1')));
      
      crearTituloReporte(hoja_local, generadorArchivoExcel_local);
      
      crearCeldasEncabezadoReporte(hoja_local, generadorArchivoExcel_local);
      
      numeroFila_local = generadorArchivoExcel_local.obtenerNumeroUltimaFila(hoja_local) + 2;
      crearCeldasEncabezado(hoja_local, numeroFila_local, generadorArchivoExcel_local);
      
      numeroFila_local++;
      numeroFila_local = crearContenidoDatosReporte(hoja_local, numeroFila_local, pListaConsulta, generadorArchivoExcel_local);
      numeroFila_local = crearFilaTotales(hoja_local, numeroFila_local, generadorArchivoExcel_local);
      
      crearCeldasPieReporte(hoja_local, generadorArchivoExcel_local);
      generadorArchivoExcel_local.escribirArchivo();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      hoja_local = null;
      nombreArchivo_local = null;
      generadorArchivoExcel_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\ReporteExcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */