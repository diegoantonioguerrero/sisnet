package com.sisnet.aplicacion.manejadores.informacionRecalculable;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.aplicacion.manejadores.ManejadorHabilitacionCampos;
import com.sisnet.aplicacion.manejadores.ManejadorOperaciones;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoEnlazado;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Iterator;
public class ManejadorCampoCalculado
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorOperaciones op = ManejadorOperaciones.getManejadorOperaciones();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private ManejadorHabilitacionCampos aManejadorHabilitacionCampos;
  private MotorAplicacion aMotorAplicacion;
  private ListaCampo aListaCampo;
  private String aEstado;
  public ManejadorCampoCalculado() {
    setManejadorHabilitacionCampos(new ManejadorHabilitacionCampos());
    setEstado("");
    setListaCampo(null);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
    getManejadorHabilitacionCampos().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
    getManejadorHabilitacionCampos().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
  }
  public ManejadorHabilitacionCampos getManejadorHabilitacionCampos() {
    return this.aManejadorHabilitacionCampos;
  }
  public void setManejadorHabilitacionCampos(ManejadorHabilitacionCampos pManejadorHabilitacionCampos) {
    this.aManejadorHabilitacionCampos = pManejadorHabilitacionCampos;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  public ListaCampo getListaCampo() {
    return this.aListaCampo;
  }
  public void setListaCampo(ListaCampo pListaCampo) {
    this.aListaCampo = pListaCampo;
  }
  public String getEstado() {
    return this.aEstado;
  }
  public void setEstado(String pEstado) {
    this.aEstado = pEstado;
  }
  private boolean esModificacion() {
    boolean esModificacion_local = false;
    
    try {
      esModificacion_local = mc.sonCadenasIguales("Modificando", getEstado());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esModificacion_local;
  }
  private int actualizarValorCampoCalculado(Campo pCampo, int pValorLlavePrimaria, int pValorCampo) {
    int errorActualizacion_local = -1;
    String nombreLlavePrimaria_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorActualizacion_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      errorActualizacion_local = getAdministradorBaseDatosAplicacion().actualizarValorCampo(pCampo, nombreLlavePrimaria_local, pValorLlavePrimaria, pValorCampo);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
    } 
    return errorActualizacion_local;
  }
  private int actualizarValorCampoCalculado(Campo pCampo, int pValorLlavePrimaria, double pValorCampo) {
    int errorActualizacion_local = -1;
    String nombreLlavePrimaria_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorActualizacion_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      errorActualizacion_local = getAdministradorBaseDatosAplicacion().actualizarValorCampo(pCampo, nombreLlavePrimaria_local, pValorLlavePrimaria, pValorCampo);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
    } 
    return errorActualizacion_local;
  }
  private int actualizarValorCampoCalculado(Campo pCampo, int pValorLlavePrimaria, String pValorCampo) {
    int errorActualizacion_local = -1;
    String nombreLlavePrimaria_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorActualizacion_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return errorActualizacion_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "F")) {
        if (mc.verificarFormatoFecha(pValorCampo)) {
          pValorCampo = mc.convertirFormatoFechaDDMMAAAA(pValorCampo);
        } else {
          pValorCampo = "null";
        } 
      }
      if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "H") && (mc.esCadenaVacia(pValorCampo) || !mc.verificarFormatoHora(pValorCampo)))
      {
        pValorCampo = "null";
      }
      errorActualizacion_local = getAdministradorBaseDatosAplicacion().actualizarValorCampo(pCampo, nombreLlavePrimaria_local, pValorLlavePrimaria, pValorCampo);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
    } 
    return errorActualizacion_local;
  }
  private Object totalizarCampoCalculado(Campo pCampo, int pValorLlavePrimaria, int pValorLlavePrincipal) {
    Object totalCampo_local = null;
    String valorLlaveReferencia_local = null;
    String nombreCampoRefencia_local = null;
    String nombreLlavePrimaria_local = null;
    Campo campoValorUnico_local = null;
    Object valorReferencia_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return totalCampo_local;
    }
    
    try {
      campoValorUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion().getIdGrupoInformacion());
      
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getNombreGrupoInformacion());
      if (campoValorUnico_local != ConstantesGeneral.VALOR_NULO) {
        nombreCampoRefencia_local = campoValorUnico_local.getNombreCampo();
        valorReferencia_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValorUnico_local, nombreLlavePrimaria_local, pValorLlavePrimaria);
        
        if (valorReferencia_local == ConstantesGeneral.VALOR_NULO) {
          valorLlaveReferencia_local = "";
        } else {
          valorLlaveReferencia_local = valorReferencia_local.toString();
        } 
        totalCampo_local = getAdministradorBaseDatosAplicacion().totalizarCampo(pCampo, valorLlaveReferencia_local, nombreCampoRefencia_local, pValorLlavePrincipal, esModificacion(), campoValorUnico_local.esTipoDatoTexto());
      } else {
        
        nombreCampoRefencia_local = nombreLlavePrimaria_local;
        valorLlaveReferencia_local = String.valueOf(pValorLlavePrimaria);
        totalCampo_local = getAdministradorBaseDatosAplicacion().totalizarCampo(pCampo, valorLlaveReferencia_local, nombreCampoRefencia_local, pValorLlavePrincipal, esModificacion(), false);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoValorUnico_local = null;
      valorReferencia_local = null;
      nombreCampoRefencia_local = null;
      nombreLlavePrimaria_local = null;
      valorLlaveReferencia_local = null;
    } 
    return totalCampo_local;
  }
  private int totalizarCampoCalculadoNumeroEntero(Campo pCampo, int pValorLlavePrimaria, int pValorLlavePrincipal) {
    int totalCampoEntero_local = 0;
    Object totalCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return totalCampoEntero_local;
    }
    
    try {
      if (pCampo.esTipoDatoNumeroEntero()) {
        totalCampo_local = totalizarCampoCalculado(pCampo, pValorLlavePrimaria, pValorLlavePrincipal);
        if (totalCampo_local != ConstantesGeneral.VALOR_NULO) {
          totalCampoEntero_local = Integer.parseInt(totalCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      totalCampo_local = null;
    } 
    return totalCampoEntero_local;
  }
  private BigDecimal totalizarCampoCalculadoNumeroReal(Campo pCampo, int pValorLlavePrimaria, int pValorLlavePrincipal) {
    BigDecimal totalCampoReal_local = null;
    Object totalCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return totalCampoReal_local;
    }
    
    try {
      if (pCampo.esTipoDatoNumeroReal()) {
        totalCampo_local = totalizarCampoCalculado(pCampo, pValorLlavePrimaria, pValorLlavePrincipal);
        if (totalCampo_local != ConstantesGeneral.VALOR_NULO) {
          totalCampoReal_local = new BigDecimal(Double.parseDouble(totalCampo_local.toString()));
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      totalCampo_local = null;
    } 
    return totalCampoReal_local;
  }
  private Object obtenerValorCampoOperando(Campo pCampo, int pValorLlavePrimaria) {
    Object valorCampo_local = null;
    String nombreLlavePrimaria_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    
    try {
      if (getListaCampo() != ConstantesGeneral.VALOR_NULO && 
        getListaCampo().obtenerCampoPorSeudonimo(pCampo.getSeudonimo()) != ConstantesGeneral.VALOR_NULO) {
        valorCampo_local = getListaCampo().obtenerCampoPorSeudonimo(pCampo.getSeudonimo()).getValorCampo();
      }
      
      if (valorCampo_local == ConstantesGeneral.VALOR_NULO) {
        nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
        
        valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(pCampo, nombreLlavePrimaria_local, pValorLlavePrimaria);
      } 
      
      if (valorCampo_local != ConstantesGeneral.VALOR_NULO && 
        pCampo.esTipoDatoTabla() && mc.esCadenaNumerica(valorCampo_local.toString(), true))
      {
        valorCampo_local = obtenerValorCampoOperandoTabla(pCampo.getFormatoCampo().getTipoDato(), Integer.parseInt(valorCampo_local.toString()));
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
    } 
    return valorCampo_local;
  }
  private Object obtenerValorCampoOperandoHabilitadoPor(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    Object valorCampoHabilitadoPor_local = null;
    Campo campoValor_local = null;
    String consulta_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    int valorLlavePrimariaGrupoInformacion_local = -1;
    int idValorMaestro_local = -1;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoHabilitadoPor_local;
    }
    
    try {
      idValorMaestro_local = getManejadorHabilitacionCampos().obtenerIdValorMaestroCampoHabilitadoPor(new ListaCampo(), pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion, true);
      
      campoValor_local = pCampoCalculado.getCalculoCampo().getCampoValor();
      if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
        if (!getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampoCalculado, campoValor_local)) {
          if (campoValor_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            consulta_local = ca.consultaSQLValoresRegistro(pCampoCalculado.getHabilitadoPor(), pValorLlavePrimariaPrincipal, getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampoCalculado.getHabilitadoPor().getGrupoInformacion()));
            
            manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
            manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
            manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
            resultSet_local = manejadorResultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consulta_local);
            if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
              while (resultSet_local.next()) {
                if (idValorMaestro_local == resultSet_local.getInt(pCampoCalculado.getHabilitadoPor().getNombreCampo())) {
                  valorLlavePrimariaGrupoInformacion_local = resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(campoValor_local.getGrupoInformacion().getNombreGrupoInformacion()));
                  
                  valorCampoHabilitadoPor_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValor_local, ap.conformarNombreCampoLlavePrimaria(campoValor_local.getGrupoInformacion().getNombreGrupoInformacion()), valorLlavePrimariaGrupoInformacion_local);
                }
              
              }
            
            }
          } else {
            
            valorCampoHabilitadoPor_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValor_local, ap.conformarNombreCampoLlavePrimaria(campoValor_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
          
          }
        
        }
        else if (campoValor_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
          valorCampoHabilitadoPor_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValor_local, getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(campoValor_local.getGrupoInformacion(), false), pValorLlavePrimariaGrupoInformacion);
        }
        else {
          
          valorCampoHabilitadoPor_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValor_local, ap.conformarNombreCampoLlavePrimaria(campoValor_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
        } 
        
        if (campoValor_local.esCampoEnlazado() && pCampoCalculado.esTipoDatoTexto() && mc.esCadenaNumerica(valorCampoHabilitadoPor_local.toString(), true))
        {
          valorCampoHabilitadoPor_local = obtenerValorCampoOperandoEnlazado(campoValor_local, Integer.parseInt(valorCampoHabilitadoPor_local.toString()));
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoValor_local = null;
      consulta_local = null;
      manejadorResultadoConsultaSQL_local = null;
      resultSet_local = null;
    } 
    return valorCampoHabilitadoPor_local;
  }
  private int obtenerValorCampoOperandoNumeroEnteroHabilitadoPor(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    int valorCampoNumeroEntero_local = 0;
    Object valorCampo_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroEntero_local;
    }
    
    try {
      if (pCampoCalculado.esTipoDatoNumeroEntero()) {
        valorCampo_local = obtenerValorCampoOperandoHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoNumeroEntero_local = Integer.parseInt(valorCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoNumeroEntero_local;
  }
  private double obtenerValorCampoOperandoNumeroRealHabilitadoPor(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    double valorCampoNumeroReal_local = 0.0D;
    Object valorCampo_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroReal_local;
    }
    
    try {
      if (pCampoCalculado.esTipoDatoNumeroReal()) {
        valorCampo_local = obtenerValorCampoOperandoHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
        
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoNumeroReal_local = Double.parseDouble(valorCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoNumeroReal_local;
  }
  private String obtenerValorCampoOperandoTextoHabilitadoPor(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoTexto_local = "";
    Object valorCampo_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoTexto_local;
    }
    
    try {
      valorCampo_local = obtenerValorCampoOperandoHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
        valorCampoTexto_local = valorCampo_local.toString();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoTexto_local;
  }
  private Date obtenerValorCampoOperandoFechaHabilitadoPor(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    Date valorCampoFecha_local = null;
    Object valorCampo_local = null;
    
    try {
      if (pCampoCalculado.esTipoDatoFecha()) {
        valorCampo_local = obtenerValorCampoOperandoHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
        
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoFecha_local = Date.valueOf(valorCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoFecha_local;
  }
  public int obtenerIdValorTabla(Campo pCampo, String pValorTabla) {
    int idValorTabla_local = 0;
    Tabla tabla_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO) {
      return idValorTabla_local;
    }
    
    try {
      tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(pCampo.getFormatoCampo().getTipoDato()));
      idValorTabla_local = getAdministradorBaseDatosAplicacion().obtenerIdValorTabla(tabla_local.getNombreTabla(), pValorTabla);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tabla_local = null;
    } 
    
    return idValorTabla_local;
  }
  private int obtenerValorCampoOperandoNumeroEntero(Campo pCampo, int pValorLlavePrimaria) {
    int valorCampoNumeroEntero_local = 0;
    Object valorCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroEntero_local;
    }
    
    try {
      if (pCampo.esTipoDatoNumeroEntero() || pCampo.esTipoDatoTabla()) {
        valorCampo_local = obtenerValorCampoOperando(pCampo, pValorLlavePrimaria);
        if (pCampo.esTipoDatoTabla() && valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampo_local = Integer.valueOf(obtenerIdValorTabla(pCampo, valorCampo_local.toString()));
        }
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoNumeroEntero_local = Integer.parseInt(valorCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    
    return valorCampoNumeroEntero_local;
  }
  private double obtenerValorCampoOperandoNumeroReal(Campo pCampo, int pValorLlavePrimaria) {
    double valorCampoNumeroReal_local = 0.0D;
    Object valorCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroReal_local;
    }
    
    try {
      if (pCampo.esTipoDatoNumeroReal()) {
        valorCampo_local = obtenerValorCampoOperando(pCampo, pValorLlavePrimaria);
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoNumeroReal_local = Double.parseDouble(valorCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoNumeroReal_local;
  }
  private Date obtenerValorCampoOperandoFecha(Campo pCampo, int pValorLlavePrimaria) {
    Date valorCampoFecha_local = null;
    Object valorCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoFecha_local;
    }
    
    try {
      if (pCampo.esTipoDatoFecha()) {
        valorCampo_local = obtenerValorCampoOperando(pCampo, pValorLlavePrimaria);
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(valorCampo_local.toString()))
        {
          valorCampoFecha_local = Date.valueOf(valorCampo_local.toString());
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoFecha_local;
  }
  private String obtenerValorCampoOperandoTexto(Campo pCampo, int pValorLlavePrimaria) {
    String valorCampoTexto_local = "";
    Object valorCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoTexto_local;
    }
    
    try {
      valorCampo_local = obtenerValorCampoOperando(pCampo, pValorLlavePrimaria);
      if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
        valorCampoTexto_local = valorCampo_local.toString();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
    } 
    return valorCampoTexto_local;
  }
  private String obtenerValorCampoOperandoEnlazado(Campo pCampo, int pValorEnlace) {
    String valorCampoCalculado_local = "";
    ManejadorCampoEnlazado manejadorCampoEnlazado_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      manejadorCampoEnlazado_local = new ManejadorCampoEnlazado();
      manejadorCampoEnlazado_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      manejadorCampoEnlazado_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      manejadorCampoEnlazado_local.setMotorAplicacion(getMotorAplicacion());
      valorCampoCalculado_local = manejadorCampoEnlazado_local.obtenerValorEnlace(pCampo.getEnlaceCampo().getEnlazado(), pValorEnlace);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      manejadorCampoEnlazado_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String obtenerValorCampoOperandoTabla(String pTipoDato, int pValorTabla) {
    String valorCampoCalculado_local = "";
    Tabla tabla_local = null;
    
    if (pTipoDato == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(pTipoDato));
      if (tabla_local != ConstantesGeneral.VALOR_NULO) {
        valorCampoCalculado_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(tabla_local.getNombreTabla(), pValorTabla);
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tabla_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String obtenerValorCampoOperandoTipoDato(Campo pCampo, int pValorLlavePrimaria, boolean pRetornarValorEnteroParaEnlazado) {
    String valorCampoCalculado_local = "";
    String tipoDato_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
      if (pCampo.esTipoDatoNumeroEntero()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoNumeroEntero(pCampo, pValorLlavePrimaria));
        if (!pRetornarValorEnteroParaEnlazado && 
          pCampo.esCampoEnlazado() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
          valorCampoCalculado_local = obtenerValorCampoOperandoEnlazado(pCampo, Integer.parseInt(valorCampoCalculado_local));
        }
        
        if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
          valorCampoCalculado_local = mc.completarCadena(valorCampoCalculado_local, false, '0', pCampo.getFormatoCampo().getLongitudCampo());
        }
        
        return valorCampoCalculado_local;
      } 
      if (pCampo.esTipoDatoNumeroReal()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoNumeroReal(pCampo, pValorLlavePrimaria));
        return valorCampoCalculado_local;
      } 
      if (pCampo.esTipoDatoFecha()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoFecha(pCampo, pValorLlavePrimaria));
        return valorCampoCalculado_local;
      } 
      if (pCampo.esTipoDatoHora()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperando(pCampo, pValorLlavePrimaria));
        valorCampoCalculado_local = ap.obtenerHoraConFormato("HH:mm", valorCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
      valorCampoCalculado_local = obtenerValorCampoOperandoTexto(pCampo, pValorLlavePrimaria);
      if (pCampo.esTipoDatoTabla() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
        valorCampoCalculado_local = obtenerValorCampoOperandoTabla(tipoDato_local, Integer.parseInt(valorCampoCalculado_local));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private int obtenerValorCampoOperandoPrimerRegistroNumeroEntero(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    int valorCampoNumeroEntero_local = 0;
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroEntero_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdPrimerRegistroIncluido(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoNumeroEntero_local = obtenerValorCampoOperandoNumeroEntero(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    
    return valorCampoNumeroEntero_local;
  }
  private double obtenerValorCampoOperandoPrimerRegistroNumeroReal(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    double valorCampoNumeroReal_local = 0.0D;
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroReal_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdPrimerRegistroIncluido(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoNumeroReal_local = obtenerValorCampoOperandoNumeroReal(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return valorCampoNumeroReal_local;
  }
  private Date obtenerValorCampoOperandoPrimerRegistroFecha(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    Date valorCampoFecha_local = null;
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoFecha_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdPrimerRegistroIncluido(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoFecha_local = obtenerValorCampoOperandoFecha(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return valorCampoFecha_local;
  }
  private String obtenerValorCampoOperandoPrimerRegistroTexto(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    String valorCampoTexto_local = "";
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoTexto_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdPrimerRegistroIncluido(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoTexto_local = obtenerValorCampoOperandoTexto(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    
    return valorCampoTexto_local;
  }
  private String obtenerValorCampoOperandoPrimerRegistroTipoDato(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    String valorCampoCalculado_local = "";
    String tipoDato_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
      if (pCampo.esTipoDatoNumeroEntero()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoPrimerRegistroNumeroEntero(pCampo, pValorLlavePrimariaPrincipal));
        
        if (pCampo.esCampoEnlazado() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
          valorCampoCalculado_local = obtenerValorCampoOperandoEnlazado(pCampo, Integer.parseInt(valorCampoCalculado_local));
        }
        if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "XX")) {
          valorCampoCalculado_local = mc.completarCadena(valorCampoCalculado_local, false, '0', pCampo.getFormatoCampo().getLongitudCampo());
        }
        
        return valorCampoCalculado_local;
      } 
      if (pCampo.esTipoDatoNumeroReal()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoPrimerRegistroNumeroReal(pCampo, pValorLlavePrimariaPrincipal));
        return valorCampoCalculado_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "F")) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoPrimerRegistroFecha(pCampo, pValorLlavePrimariaPrincipal));
        return valorCampoCalculado_local;
      } 
      valorCampoCalculado_local = obtenerValorCampoOperandoPrimerRegistroTexto(pCampo, pValorLlavePrimariaPrincipal);
      if (pCampo.esTipoDatoTabla() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
        valorCampoCalculado_local = obtenerValorCampoOperandoTabla(tipoDato_local, Integer.parseInt(valorCampoCalculado_local));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private int obtenerValorCampoOperandoUltimoRegistroNumeroEntero(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    int valorCampoNumeroEntero_local = 0;
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroEntero_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdUltimoRegistroIncluidoGrupoInformacion(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoNumeroEntero_local = obtenerValorCampoOperandoNumeroEntero(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return valorCampoNumeroEntero_local;
  }
  private double obtenerValorCampoOperandoUltimoRegistroNumeroReal(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    double valorCampoNumeroReal_local = 0.0D;
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoNumeroReal_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdUltimoRegistroIncluidoGrupoInformacion(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoNumeroReal_local = obtenerValorCampoOperandoNumeroReal(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return valorCampoNumeroReal_local;
  }
  private Date obtenerValorCampoOperandoUltimoRegistroFecha(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    Date valorCampoFecha_local = null;
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoFecha_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdUltimoRegistroIncluidoGrupoInformacion(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoFecha_local = obtenerValorCampoOperandoFecha(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return valorCampoFecha_local;
  }
  private String obtenerValorCampoOperandoUltimoRegistroTexto(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    String valorCampoTexto_local = "";
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrimariaPrincipal_local = null;
    String nombreCampoValorUnico_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoTexto_local;
    }
    
    try {
      nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
      
      nombreLlavePrimariaPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
      nombreCampoValorUnico_local = getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
      
      valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdUltimoRegistroIncluidoGrupoInformacion(pCampo.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimaria_local, nombreCampoValorUnico_local, nombreLlavePrimariaPrincipal_local, pValorLlavePrimariaPrincipal);
      
      valorCampoTexto_local = obtenerValorCampoOperandoTexto(pCampo, valorLlavePrimaria_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      nombreCampoValorUnico_local = null;
      nombreLlavePrimariaPrincipal_local = null;
    } 
    return valorCampoTexto_local;
  }
  private String obtenerValorCampoOperandoUltimoRegistroTipoDato(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    String valorCampoCalculado_local = "";
    String tipoDato_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
      if (pCampo.esTipoDatoNumeroEntero()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoUltimoRegistroNumeroEntero(pCampo, pValorLlavePrimariaPrincipal));
        
        if (pCampo.esCampoEnlazado() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
          valorCampoCalculado_local = obtenerValorCampoOperandoEnlazado(pCampo, Integer.parseInt(valorCampoCalculado_local));
        }
        if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "XX")) {
          valorCampoCalculado_local = mc.completarCadena(valorCampoCalculado_local, false, '0', pCampo.getFormatoCampo().getLongitudCampo());
        }
        
        return valorCampoCalculado_local;
      } 
      if (pCampo.esTipoDatoNumeroReal()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoUltimoRegistroNumeroReal(pCampo, pValorLlavePrimariaPrincipal));
        
        return valorCampoCalculado_local;
      } 
      if (mc.sonCadenasIguales(tipoDato_local, "F")) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoUltimoRegistroFecha(pCampo, pValorLlavePrimariaPrincipal));
        
        return valorCampoCalculado_local;
      } 
      valorCampoCalculado_local = obtenerValorCampoOperandoUltimoRegistroTexto(pCampo, pValorLlavePrimariaPrincipal);
      if (pCampo.esTipoDatoTabla() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
        valorCampoCalculado_local = obtenerValorCampoOperandoTabla(tipoDato_local, Integer.parseInt(valorCampoCalculado_local));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String obtenerValorCampoOperandoHabilitadoPorTipoDato(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    String tipoDato_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      tipoDato_local = pCampoCalculado.getFormatoCampo().getTipoDato();
      if (pCampoCalculado.esTipoDatoNumeroEntero()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoNumeroEnteroHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion));
        
        if (pCampoCalculado.esCampoEnlazado() && mc.esCadenaNumerica(valorCampoCalculado_local, true))
        {
          valorCampoCalculado_local = obtenerValorCampoOperandoEnlazado(pCampoCalculado, Integer.parseInt(valorCampoCalculado_local));
        }
        
        if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
          valorCampoCalculado_local = mc.completarCadena(valorCampoCalculado_local, false, '0', pCampoCalculado.getFormatoCampo().getLongitudCampo());
        }
        
        return valorCampoCalculado_local;
      } 
      if (pCampoCalculado.esTipoDatoNumeroReal()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoNumeroRealHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion));
        
        return valorCampoCalculado_local;
      } 
      if (pCampoCalculado.esTipoDatoFecha()) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoOperandoFechaHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion));
        
        return valorCampoCalculado_local;
      } 
      valorCampoCalculado_local = obtenerValorCampoOperandoTextoHabilitadoPor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      if (pCampoCalculado.esTipoDatoTabla() && mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
        valorCampoCalculado_local = obtenerValorCampoOperandoTabla(tipoDato_local, Integer.parseInt(valorCampoCalculado_local));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String totalizarCampoCalculadoTipoDato(Campo pCampo, int pValorLlavePrimaria, int pValorLlavePrincipal) {
    String valorCampoCalculado_local = "";
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      if (pCampo.esTipoDatoNumeroEntero()) {
        valorCampoCalculado_local = String.valueOf(totalizarCampoCalculadoNumeroEntero(pCampo, pValorLlavePrimaria, pValorLlavePrincipal));
        
        return valorCampoCalculado_local;
      } 
      if (pCampo.esTipoDatoNumeroReal()) {
        valorCampoCalculado_local = String.valueOf(totalizarCampoCalculadoNumeroReal(pCampo, pValorLlavePrimaria, pValorLlavePrincipal));
        
        return valorCampoCalculado_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorCampoCalculado_local;
  }
  private int actualizarValorCampoCalculadoTipoDato(Campo pCampo, int pValorLlavePrimaria, String pValorCampo) {
    int errorActualizacion_local = 0;
    int valorCampoNumeroEntero_local = 0;
    double valorCampoNumeroReal_local = 0.0D;
    String valorCampoTexto_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorActualizacion_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return errorActualizacion_local;
    }
    
    try {
      if (pCampo.esTipoDatoNumeroEntero()) {
        if (mc.esCadenaNumerica(pValorCampo, true)) {
          valorCampoNumeroEntero_local = Integer.parseInt(pValorCampo);
        }
        errorActualizacion_local = actualizarValorCampoCalculado(pCampo, pValorLlavePrimaria, valorCampoNumeroEntero_local);
        return errorActualizacion_local;
      } 
      if (pCampo.esTipoDatoNumeroReal()) {
        if (mc.esCadenaNumerica(pValorCampo, false)) {
          valorCampoNumeroReal_local = Double.parseDouble(pValorCampo);
        }
        errorActualizacion_local = actualizarValorCampoCalculado(pCampo, pValorLlavePrimaria, valorCampoNumeroReal_local);
        return errorActualizacion_local;
      } 
      valorCampoTexto_local = pValorCampo;
      errorActualizacion_local = actualizarValorCampoCalculado(pCampo, pValorLlavePrimaria, valorCampoTexto_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampoTexto_local = null;
    } 
    
    return errorActualizacion_local;
  }
  private String sumarValoresCampoCalculado(Campo pCampo, int pValorLlaveOrigenUno, int pValorLlaveOrigenDos) {
    String valorCampoCalculado_local = "";
    int valorEnteroCampoCalculado_local = 0;
    int valorEnteroOrigenUno_local = 0;
    int valorEnteroOrigenDos_local = 0;
    double valorRealOrigenUno_local = 0.0D;
    double valorRealOrigenDos_local = 0.0D;
    boolean esOperacionValida_local = false;
    boolean esOrigenUnoEntero_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenUnoFecha_local = false;
    boolean esOrigenDosEntero_local = false;
    boolean esOrigenDosReal_local = false;
    boolean esOrigenDosFecha_local = false;
    String tipoDatoOrigenUno_local = null;
    String tipoDatoOrigenDos_local = null;
    BigDecimal valorRealCampoCalculado_local = null;
    Date valorFechaOrigenUno_local = null;
    Date valorFechaOrigenDos_local = null;
    Date valorFechaCampoCalculado_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      tipoDatoOrigenUno_local = campoOrigenUno_local.getFormatoCampo().getTipoDato();
      tipoDatoOrigenDos_local = campoOrigenDos_local.getFormatoCampo().getTipoDato();
      esOrigenUnoEntero_local = (campoOrigenUno_local.esTipoDatoNumeroEntero() || campoOrigenUno_local.esTipoDatoTabla());
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenUnoFecha_local = mc.sonCadenasIguales(tipoDatoOrigenUno_local, "F");
      esOrigenDosEntero_local = (campoOrigenDos_local.esTipoDatoNumeroEntero() || campoOrigenDos_local.esTipoDatoTabla());
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      esOrigenDosFecha_local = mc.sonCadenasIguales(tipoDatoOrigenDos_local, "F");
      esOperacionValida_local = ((!esOrigenUnoFecha_local || !esOrigenDosFecha_local) && (!esOrigenUnoReal_local || !esOrigenDosFecha_local) && (!esOrigenUnoFecha_local || !esOrigenDosReal_local));
      
      if (!esOperacionValida_local) {
        return valorCampoCalculado_local;
      }
      valorEnteroOrigenUno_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorRealOrigenUno_local = obtenerValorCampoOperandoNumeroReal(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorFechaOrigenUno_local = obtenerValorCampoOperandoFecha(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorEnteroOrigenDos_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorRealOrigenDos_local = obtenerValorCampoOperandoNumeroReal(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorFechaOrigenDos_local = obtenerValorCampoOperandoFecha(campoOrigenDos_local, pValorLlaveOrigenDos);
      if (esOrigenUnoEntero_local && esOrigenDosEntero_local) {
        valorEnteroCampoCalculado_local = op.sumar(valorEnteroOrigenUno_local, valorEnteroOrigenDos_local);
        valorCampoCalculado_local = String.valueOf(valorEnteroCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.sumar(valorEnteroOrigenUno_local, valorRealOrigenDos_local);
        valorCampoCalculado_local = String.valueOf(valorRealCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosFecha_local) {
        valorFechaCampoCalculado_local = mf.sumarDiasFecha(valorFechaOrigenDos_local, valorEnteroOrigenUno_local);
        valorCampoCalculado_local = String.valueOf(valorFechaCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.sumar(valorRealOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.sumar(valorRealOrigenUno_local, valorEnteroOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoFecha_local && esOrigenDosEntero_local) {
        valorFechaCampoCalculado_local = mf.sumarDiasFecha(valorFechaOrigenUno_local, valorEnteroOrigenDos_local);
        valorCampoCalculado_local = String.valueOf(valorFechaCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDatoOrigenUno_local = null;
      tipoDatoOrigenDos_local = null;
      valorFechaOrigenUno_local = null;
      valorFechaOrigenDos_local = null;
      valorFechaCampoCalculado_local = null;
      valorRealCampoCalculado_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String restarValoresCampoCalculado(Campo pCampo, int pValorLlaveOrigenUno, int pValorLlaveOrigenDos) {
    String valorCampoCalculado_local = "";
    int valorEnteroCampoCalculado_local = 0;
    int valorEnteroOrigenUno_local = 0;
    int valorEnteroOrigenDos_local = 0;
    double valorRealOrigenUno_local = 0.0D;
    double valorRealOrigenDos_local = 0.0D;
    boolean esOperacionValida_local = false;
    boolean esOrigenUnoEntero_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenUnoFecha_local = false;
    boolean esOrigenDosEntero_local = false;
    boolean esOrigenDosReal_local = false;
    boolean esOrigenDosFecha_local = false;
    String tipoDatoOrigenUno_local = null;
    String tipoDatoOrigenDos_local = null;
    BigDecimal valorRealCampoCalculado_local = null;
    Date valorFechaOrigenUno_local = null;
    Date valorFechaOrigenDos_local = null;
    Date valorFechaCampoCalculado_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      tipoDatoOrigenUno_local = campoOrigenUno_local.getFormatoCampo().getTipoDato();
      tipoDatoOrigenDos_local = campoOrigenDos_local.getFormatoCampo().getTipoDato();
      esOrigenUnoEntero_local = (campoOrigenUno_local.esTipoDatoNumeroEntero() || campoOrigenUno_local.esTipoDatoTabla());
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenUnoFecha_local = mc.sonCadenasIguales(tipoDatoOrigenUno_local, "F");
      esOrigenDosEntero_local = (campoOrigenDos_local.esTipoDatoNumeroEntero() || campoOrigenDos_local.esTipoDatoTabla());
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      esOrigenDosFecha_local = mc.sonCadenasIguales(tipoDatoOrigenDos_local, "F");
      esOperacionValida_local = ((!esOrigenUnoReal_local || !esOrigenDosFecha_local) && (!esOrigenUnoFecha_local || !esOrigenDosReal_local));
      
      if (!esOperacionValida_local) {
        return valorCampoCalculado_local;
      }
      valorEnteroOrigenUno_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorRealOrigenUno_local = obtenerValorCampoOperandoNumeroReal(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorFechaOrigenUno_local = obtenerValorCampoOperandoFecha(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorEnteroOrigenDos_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorRealOrigenDos_local = obtenerValorCampoOperandoNumeroReal(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorFechaOrigenDos_local = obtenerValorCampoOperandoFecha(campoOrigenDos_local, pValorLlaveOrigenDos);
      if (esOrigenUnoEntero_local && esOrigenDosEntero_local) {
        valorCampoCalculado_local = String.valueOf(op.restar(valorEnteroOrigenUno_local, valorEnteroOrigenDos_local));
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.restar(valorEnteroOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosFecha_local) {
        valorFechaCampoCalculado_local = mf.restarDiasFecha(valorFechaOrigenDos_local, valorEnteroOrigenUno_local);
        valorCampoCalculado_local = String.valueOf(valorFechaCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.restar(valorRealOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.restar(valorRealOrigenUno_local, valorEnteroOrigenDos_local);
        valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoFecha_local && esOrigenDosFecha_local) {
        valorEnteroCampoCalculado_local = mf.restarFechas(valorFechaOrigenUno_local, valorFechaOrigenDos_local);
        valorCampoCalculado_local = String.valueOf(valorEnteroCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoFecha_local && esOrigenDosEntero_local) {
        valorFechaCampoCalculado_local = mf.restarDiasFecha(valorFechaOrigenUno_local, valorEnteroOrigenDos_local);
        valorCampoCalculado_local = String.valueOf(valorFechaCampoCalculado_local);
        return valorCampoCalculado_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDatoOrigenUno_local = null;
      tipoDatoOrigenDos_local = null;
      valorFechaOrigenUno_local = null;
      valorFechaOrigenDos_local = null;
      valorRealCampoCalculado_local = null;
      valorFechaCampoCalculado_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String multiplicarValoresCampoCalculado(Campo pCampo, int pValorLlaveOrigenUno, int pValorLlaveOrigenDos) {
    String valorCampoCalculado_local = "";
    int valorEnteroOrigenUno_local = 0;
    int valorEnteroOrigenDos_local = 0;
    double valorRealOrigenUno_local = 0.0D;
    double valorRealOrigenDos_local = 0.0D;
    boolean esOrigenUnoEntero_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenDosEntero_local = false;
    boolean esOrigenDosReal_local = false;
    BigDecimal valorRealCampoCalculado_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      esOrigenUnoEntero_local = campoOrigenUno_local.esTipoDatoNumeroEntero();
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenDosEntero_local = campoOrigenDos_local.esTipoDatoNumeroEntero();
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      
      valorEnteroOrigenUno_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorRealOrigenUno_local = obtenerValorCampoOperandoNumeroReal(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorEnteroOrigenDos_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorRealOrigenDos_local = obtenerValorCampoOperandoNumeroReal(campoOrigenDos_local, pValorLlaveOrigenDos);
      if (esOrigenUnoEntero_local && esOrigenDosEntero_local) {
        valorCampoCalculado_local = String.valueOf(op.multiplicar(valorEnteroOrigenUno_local, valorEnteroOrigenDos_local));
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.multiplicar(valorEnteroOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.multiplicar(valorRealOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.multiplicar(valorRealOrigenUno_local, valorEnteroOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorRealCampoCalculado_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String dividirValoresCampoCalculado(Campo pCampo, int pValorLlaveOrigenUno, int pValorLlaveOrigenDos) {
    String valorCampoCalculado_local = "";
    int valorEnteroOrigenUno_local = 0;
    int valorEnteroOrigenDos_local = 0;
    double valorRealOrigenUno_local = 0.0D;
    double valorRealOrigenDos_local = 0.0D;
    boolean esOrigenUnoEntero_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenDosEntero_local = false;
    boolean esOrigenDosReal_local = false;
    BigDecimal valorRealCampoCalculado_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      esOrigenUnoEntero_local = campoOrigenUno_local.esTipoDatoNumeroEntero();
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenDosEntero_local = campoOrigenDos_local.esTipoDatoNumeroEntero();
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      
      valorEnteroOrigenUno_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorRealOrigenUno_local = obtenerValorCampoOperandoNumeroReal(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorEnteroOrigenDos_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorRealOrigenDos_local = obtenerValorCampoOperandoNumeroReal(campoOrigenDos_local, pValorLlaveOrigenDos);
      if (esOrigenUnoEntero_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.dividir(valorEnteroOrigenUno_local, valorEnteroOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.dividir(valorEnteroOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.dividir(valorRealOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.dividir(valorRealOrigenUno_local, valorEnteroOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampoCalculado_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String concatenarValoresCampoCalculado(Campo pCampo, int pValorLlaveOrigenUno, int pValorLlaveOrigenDos) {
    String valorCampoCalculado_local = "";
    int valorEnteroOrigenUno_local = 0;
    int valorEnteroOrigenDos_local = 0;
    boolean esOrigenUnoEntero_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenDosEntero_local = false;
    boolean esOrigenDosReal_local = false;
    boolean esformatoOrigenUno_local = false;
    boolean esformatoOrigenDos_local = false;
    String valorOrigenUno_local = null;
    String valorOrigenDos_local = null;
    String formatoOrigenUno_local = null;
    String formatoOrigenDos_local = null;
    String union_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      union_local = "";
      if (pCampo.getCalculoCampo().getCampoCalculado() == 11) {
        union_local = String.valueOf(' ');
      }
      if (pCampo.getCalculoCampo().getCampoCalculado() == 21) {
        union_local = String.valueOf('-');
      }
      if (pCampo.getCalculoCampo().getCampoCalculado() == 27) {
        union_local = "\r\n";
      }
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      
      if (campoOrigenUno_local != ConstantesGeneral.VALOR_NULO && campoOrigenDos_local != ConstantesGeneral.VALOR_NULO) {
        formatoOrigenUno_local = pCampo.getCalculoCampo().getFormatoCampoOrigenUno();
        formatoOrigenDos_local = pCampo.getCalculoCampo().getFormatoCampoOrigenDos();
        esOrigenUnoEntero_local = campoOrigenUno_local.esTipoDatoNumeroEntero();
        esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
        esOrigenDosEntero_local = campoOrigenDos_local.esTipoDatoNumeroEntero();
        esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
        esformatoOrigenUno_local = !mc.sonCadenasIguales(formatoOrigenUno_local, "");
        esformatoOrigenDos_local = !mc.sonCadenasIguales(formatoOrigenDos_local, "");
        
        if (esOrigenUnoEntero_local) {
          valorEnteroOrigenUno_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenUno_local, pValorLlaveOrigenUno);
          if (campoOrigenUno_local.esCampoEnlazado()) {
            valorOrigenUno_local = obtenerValorCampoOperandoEnlazado(campoOrigenUno_local, valorEnteroOrigenUno_local);
          }
          else if (mc.sonCadenasIguales(campoOrigenUno_local.getFormatoCampo().getTipoDato(), "XX")) {
            
            valorOrigenUno_local = mc.completarCadena(String.valueOf(valorEnteroOrigenUno_local), false, '0', campoOrigenUno_local.getFormatoCampo().getLongitudCampo());
          } else {
            
            valorOrigenUno_local = String.valueOf(valorEnteroOrigenUno_local);
          }
        
        }
        else if (esOrigenUnoReal_local) {
          valorOrigenUno_local = String.valueOf(obtenerValorCampoOperandoNumeroReal(campoOrigenUno_local, pValorLlaveOrigenUno));
        } else {
          valorOrigenUno_local = obtenerValorCampoOperandoTexto(campoOrigenUno_local, pValorLlaveOrigenUno);
        } 
        
        if (esOrigenDosEntero_local) {
          valorEnteroOrigenDos_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenDos_local, pValorLlaveOrigenDos);
          if (campoOrigenDos_local.esCampoEnlazado()) {
            valorOrigenDos_local = obtenerValorCampoOperandoEnlazado(campoOrigenDos_local, valorEnteroOrigenDos_local);
          }
          else if (mc.sonCadenasIguales(campoOrigenDos_local.getFormatoCampo().getTipoDato(), "XX")) {
            
            valorOrigenDos_local = mc.completarCadena(String.valueOf(valorEnteroOrigenDos_local), false, '0', campoOrigenDos_local.getFormatoCampo().getLongitudCampo());
          } else {
            
            valorOrigenDos_local = String.valueOf(valorEnteroOrigenDos_local);
          }
        
        }
        else if (esOrigenDosReal_local) {
          valorOrigenDos_local = String.valueOf(obtenerValorCampoOperandoNumeroReal(campoOrigenDos_local, pValorLlaveOrigenDos));
        } else {
          valorOrigenDos_local = obtenerValorCampoOperandoTexto(campoOrigenDos_local, pValorLlaveOrigenDos);
        } 
        
        if (esformatoOrigenUno_local) {
          valorOrigenUno_local = getAdministradorBaseDatosSisnet().obtenerValorCampoCalculadoConFormato(pCampo, valorOrigenUno_local, 1);
        }
        
        if (esformatoOrigenDos_local) {
          valorOrigenDos_local = getAdministradorBaseDatosSisnet().obtenerValorCampoCalculadoConFormato(pCampo, valorOrigenDos_local, 2);
        }
      } 
      
      valorCampoCalculado_local = op.concatenar(valorOrigenUno_local, valorOrigenDos_local, union_local, pCampo.getFormatoCampo().getLongitudCampo());
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      union_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
      valorOrigenUno_local = null;
      valorOrigenDos_local = null;
      formatoOrigenUno_local = null;
      formatoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private int obtenerValorLlavePrimariaCampoOrigen(Campo pCampoOrigen, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    int valorLlavePrimaria_local = -1;
    
    try {
      if (pCampoOrigen.getGrupoInformacion().esGrupoInformacionMultiple()) {
        valorLlavePrimaria_local = pValorLlavePrimariaGrupoInformacion;
      } else {
        valorLlavePrimaria_local = pValorLlavePrimariaPrincipal;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return valorLlavePrimaria_local;
  }
  private String sumarValoresCampoCalculadoAplicacion(Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    int valorLlavePrimariaUno_local = -1;
    int valorLlavePrimariaDos_local = -1;
    boolean esOperacionValida_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenUnoFecha_local = false;
    boolean esOrigenDosReal_local = false;
    boolean esOrigenDosFecha_local = false;
    String tipoDatoOrigenUno_local = null;
    String tipoDatoOrigenDos_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      tipoDatoOrigenUno_local = campoOrigenUno_local.getFormatoCampo().getTipoDato();
      tipoDatoOrigenDos_local = campoOrigenDos_local.getFormatoCampo().getTipoDato();
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenUnoFecha_local = mc.sonCadenasIguales(tipoDatoOrigenUno_local, "F");
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      esOrigenDosFecha_local = mc.sonCadenasIguales(tipoDatoOrigenDos_local, "F");
      esOperacionValida_local = ((!esOrigenUnoFecha_local || !esOrigenDosFecha_local) && (!esOrigenUnoReal_local || !esOrigenDosFecha_local) && (!esOrigenUnoFecha_local || !esOrigenDosReal_local));
      
      if (!esOperacionValida_local) {
        return valorCampoCalculado_local;
      }
      valorLlavePrimariaUno_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenUno_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorLlavePrimariaDos_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenDos_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorCampoCalculado_local = sumarValoresCampoCalculado(pCampo, valorLlavePrimariaUno_local, valorLlavePrimariaDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDatoOrigenUno_local = null;
      tipoDatoOrigenDos_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String restarValoresCampoCalculadoAplicacion(Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    int valorLlavePrimariaUno_local = -1;
    int valorLlavePrimariaDos_local = -1;
    boolean esOperacionValida_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenUnoFecha_local = false;
    boolean esOrigenDosReal_local = false;
    boolean esOrigenDosFecha_local = false;
    String tipoDatoOrigenUno_local = null;
    String tipoDatoOrigenDos_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      tipoDatoOrigenUno_local = campoOrigenUno_local.getFormatoCampo().getTipoDato();
      tipoDatoOrigenDos_local = campoOrigenDos_local.getFormatoCampo().getTipoDato();
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenUnoFecha_local = mc.sonCadenasIguales(tipoDatoOrigenUno_local, "F");
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      esOrigenDosFecha_local = mc.sonCadenasIguales(tipoDatoOrigenDos_local, "F");
      esOperacionValida_local = ((!esOrigenUnoReal_local || !esOrigenDosFecha_local) && (!esOrigenUnoFecha_local || !esOrigenDosReal_local));
      
      if (!esOperacionValida_local) {
        return valorCampoCalculado_local;
      }
      valorLlavePrimariaUno_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenUno_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorLlavePrimariaDos_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenDos_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorCampoCalculado_local = restarValoresCampoCalculado(pCampo, valorLlavePrimariaUno_local, valorLlavePrimariaDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDatoOrigenUno_local = null;
      tipoDatoOrigenDos_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String multiplicarValoresCampoCalculadoAplicacion(Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    int valorLlavePrimariaUno_local = -1;
    int valorLlavePrimariaDos_local = -1;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      valorLlavePrimariaUno_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenUno_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorLlavePrimariaDos_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenDos_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorCampoCalculado_local = multiplicarValoresCampoCalculado(pCampo, valorLlavePrimariaUno_local, valorLlavePrimariaDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String dividirValoresCampoCalculadoAplicacion(Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    int valorLlavePrimariaUno_local = -1;
    int valorLlavePrimariaDos_local = -1;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      valorLlavePrimariaUno_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenUno_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorLlavePrimariaDos_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenDos_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorCampoCalculado_local = dividirValoresCampoCalculado(pCampo, valorLlavePrimariaUno_local, valorLlavePrimariaDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private boolean verificarTipoCalculoCondicion(int pTipoCalculo) {
    return (pTipoCalculo == 15 || pTipoCalculo == 16 || pTipoCalculo == 17 || pTipoCalculo == 18 || pTipoCalculo == 19 || pTipoCalculo == 20);
  }
  private boolean verificarMenorQueCero(BigDecimal pValorNumero) {
    boolean menorQueCero_local = false;
    
    if (pValorNumero == ConstantesGeneral.VALOR_NULO) {
      return menorQueCero_local;
    }
    
    try {
      menorQueCero_local = (pValorNumero.compareTo(BigDecimal.valueOf(0L)) == -1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return menorQueCero_local;
  }
  private boolean verificarMayorQueCero(BigDecimal pValorNumero) {
    boolean mayorQueCero_local = false;
    
    if (pValorNumero == ConstantesGeneral.VALOR_NULO) {
      return mayorQueCero_local;
    }
    
    try {
      mayorQueCero_local = (pValorNumero.compareTo(BigDecimal.valueOf(0L)) == 1);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return mayorQueCero_local;
  }
  private boolean verificarIgualACero(BigDecimal pValorNumero) {
    boolean igualACero_local = false;
    
    if (pValorNumero == ConstantesGeneral.VALOR_NULO) {
      return igualACero_local;
    }
    
    try {
      igualACero_local = (pValorNumero.compareTo(BigDecimal.valueOf(0L)) == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return igualACero_local;
  }
  private int obtenerValorCampoCalculadoCondicion(String pValorCampoCalculado, int pTipoCalculo) {
    int valorCampoCalculado_local = 2;
    boolean condicion_local = false;
    BigDecimal valorNumerico_local = null;
    
    try {
      if (mc.esCadenaNumerica(pValorCampoCalculado, false)) {
        valorNumerico_local = new BigDecimal(pValorCampoCalculado);
        switch (pTipoCalculo) {
          case 15:
            condicion_local = verificarMenorQueCero(valorNumerico_local);
            break;
          case 16:
            condicion_local = !verificarMayorQueCero(valorNumerico_local);
            break;
          case 17:
            condicion_local = verificarMayorQueCero(valorNumerico_local);
            break;
          case 18:
            condicion_local = !verificarMenorQueCero(valorNumerico_local);
            break;
          case 19:
            condicion_local = verificarIgualACero(valorNumerico_local);
            break;
          case 20:
            condicion_local = !verificarIgualACero(valorNumerico_local);
            break;
        } 
        if (condicion_local) {
          valorCampoCalculado_local = 1;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorNumerico_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String concatenarValoresColumnaCampoCalculado(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal) {
    String valorCampoCalculado_local = "";
    String union_local = null;
    String consulta_local = null;
    String valor_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL = null;
    ResultSet resultSet_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      switch (pCampoCalculado.getCalculoCampo().getCampoCalculado()) {
        case 23:
          union_local = String.valueOf(' '); break;
        case 24:
          union_local = String.valueOf('-'); break;
        case 28:
          union_local = "\r\n"; break;
        default: union_local = ""; break;
      } 
      consulta_local = ca.consultaSQLValoresCampo(pCampoCalculado.getCalculoCampo().getCampoValor(), pValorLlavePrimariaPrincipal, getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampoCalculado.getCalculoCampo().getCampoValor().getGrupoInformacion()));
      
      manejadorResultadoConsultaSQL = new ManejadorResultadoConsultaSQL();
      manejadorResultadoConsultaSQL.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      manejadorResultadoConsultaSQL.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      resultSet_local = manejadorResultadoConsultaSQL.obtenerResultadoConsultaAplicacion(consulta_local);
      
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          valor_local = resultSet_local.getString(pCampoCalculado.getCalculoCampo().getCampoValor().getNombreCampo());
          if (valor_local == ConstantesGeneral.VALOR_NULO) {
            valor_local = "";
          } else {
            if (pCampoCalculado.getCalculoCampo().getCampoValor().esTipoDatoTabla()) {
              valor_local = obtenerValorCampoOperandoTabla(pCampoCalculado.getCalculoCampo().getCampoValor().getFormatoCampo().getTipoDato(), Integer.parseInt(valor_local));
            }
            
            if (pCampoCalculado.getCalculoCampo().getCampoValor().esCampoEnlazado()) {
              valor_local = obtenerValorCampoOperandoEnlazado(pCampoCalculado.getCalculoCampo().getCampoValor(), Integer.parseInt(valor_local));
            }
          } 
          
          if (mc.esCadenaVacia(valorCampoCalculado_local)) {
            valorCampoCalculado_local = valor_local; continue;
          } 
          valorCampoCalculado_local = op.concatenar(valorCampoCalculado_local, valor_local, union_local, pCampoCalculado.getFormatoCampo().getLongitudCampo());
        }
      
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      union_local = null;
      consulta_local = null;
      resultSet_local = null;
      manejadorResultadoConsultaSQL = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String obtenerValorPrimerCampoConValor(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    int valorLlavePrimaria_local = -1;
    boolean esValorNumericoValido_local = false;
    boolean esTipoDatoNumerico_local = false;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      if (pCampoCalculado.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO) {
        if (pCampoCalculado.getCalculoCampo().getCampoOrigenUno().getGrupoInformacion().esGrupoInformacionMultiple()) {
          valorLlavePrimaria_local = pValorLlavePrimariaGrupoInformacion;
        } else {
          valorLlavePrimaria_local = pValorLlavePrimariaPrincipal;
        } 
        esTipoDatoNumerico_local = pCampoCalculado.getCalculoCampo().getCampoOrigenUno().esTipoDatoNumerico();
        valorCampoCalculado_local = obtenerValorCampoOperandoTipoDato(pCampoCalculado.getCalculoCampo().getCampoOrigenUno(), valorLlavePrimaria_local, pCampoCalculado.esTipoDatoNumerico());
        
        if (esTipoDatoNumerico_local) {
          esValorNumericoValido_local = (mc.esCadenaNumerica(valorCampoCalculado_local, pCampoCalculado.getCalculoCampo().getCampoOrigenUno().esTipoDatoNumeroEntero()) && Double.parseDouble(valorCampoCalculado_local) != 0.0D);
        }
        
        if (mc.esCadenaVacia(valorCampoCalculado_local) || mc.sonCadenasIgualesIgnorarMayusculas(valorCampoCalculado_local, "null") || (pCampoCalculado.esTipoDatoNumerico() && !esValorNumericoValido_local))
        {
          
          if (pCampoCalculado.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO) {
            esTipoDatoNumerico_local = pCampoCalculado.getCalculoCampo().getCampoOrigenDos().esTipoDatoNumerico();
            if (pCampoCalculado.getCalculoCampo().getCampoOrigenDos().getGrupoInformacion().esGrupoInformacionMultiple()) {
              valorLlavePrimaria_local = pValorLlavePrimariaGrupoInformacion;
            } else {
              valorLlavePrimaria_local = pValorLlavePrimariaPrincipal;
            } 
            valorCampoCalculado_local = obtenerValorCampoOperandoTipoDato(pCampoCalculado.getCalculoCampo().getCampoOrigenDos(), valorLlavePrimaria_local, esTipoDatoNumerico_local);
          }
        
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorCampoCalculado_local;
  }
  private String sumarValoresCamposDesdeHasta(Campo pCampoOrigenUno, Campo pCampoOrigenDos, int pValorLlavePrimaria) {
    String sumaValores_local = "";
    ListaCampo listaCamposEntrePosiciones_local = null;
    String consulta_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    if (pCampoOrigenUno == ConstantesGeneral.VALOR_NULO) {
      return sumaValores_local;
    }
    if (pCampoOrigenDos == ConstantesGeneral.VALOR_NULO) {
      return sumaValores_local;
    }
    
    try {
      listaCamposEntrePosiciones_local = getMotorAplicacion().obtenerListaCamposNumericosEntrePosicionesGrupoInformacion(pCampoOrigenUno.getGrupoInformacion(), pCampoOrigenUno.getPosicion(), pCampoOrigenDos.getPosicion());
      
      if (listaCamposEntrePosiciones_local != ConstantesGeneral.VALOR_NULO) {
        consulta_local = ca.consultaSQLSumaDesdeHasta(listaCamposEntrePosiciones_local, pValorLlavePrimaria);
        manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
        manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
        manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
        resultSet_local = manejadorResultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consulta_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && resultSet_local.next()) {
          sumaValores_local = resultSet_local.getString("sumadesdehasta");
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      listaCamposEntrePosiciones_local = null;
      manejadorResultadoConsultaSQL_local = null;
    } 
    
    return sumaValores_local;
  }
  private boolean verificarTipoCalculoObtieneFechaONumeroInterno(int pTipoCalculo) {
    return (pTipoCalculo == 29 || pTipoCalculo == 30 || pTipoCalculo == 31 || pTipoCalculo == 32 || pTipoCalculo == 33 || pTipoCalculo == 34 || pTipoCalculo == 35 || pTipoCalculo == 36 || pTipoCalculo == 37 || pTipoCalculo == 38 || pTipoCalculo == 39 || pTipoCalculo == 40 || pTipoCalculo == 41 || pTipoCalculo == 42);
  }
  private String obtenerValorCampoCalculadoObtieneFechaONumeroInterno(String pValorCampoCalculado, int pTipoCalculo) {
    String valorCampoCalculado_local = "";
    Date fechaOrigen_local = null;
    
    if (pValorCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      if (pTipoCalculo == 29 || pTipoCalculo == 30) {
        
        switch (pTipoCalculo) {
          case 29:
            valorCampoCalculado_local = String.valueOf(op.obtenerNumeroInternoHora(pValorCampoCalculado));
            break;
          case 30:
            valorCampoCalculado_local = String.valueOf(mf.obtenerNumeroInternoFecha(pValorCampoCalculado));
            break;
        } 
      } else {
        if (mc.verificarFormatoFecha(pValorCampoCalculado)) {
          fechaOrigen_local = Date.valueOf(pValorCampoCalculado);
        }
        switch (pTipoCalculo) {
          case 31:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaInicioDeMes(fechaOrigen_local));
            break;
          case 32:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaFinDeMes(fechaOrigen_local));
            break;
          case 33:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaInicioDeMesAnterior(fechaOrigen_local));
            break;
          case 34:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaFinDeMesAnterior(fechaOrigen_local));
            break;
          case 35:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaInicioDeMesSiguiente(fechaOrigen_local));
            break;
          case 36:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaFinDeMesSiguiente(fechaOrigen_local));
            break;
          case 37:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaInicioDeAno(fechaOrigen_local));
            break;
          case 38:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaFinDeAno(fechaOrigen_local));
            break;
          case 39:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaInicioDeAnoAnterior(fechaOrigen_local));
            break;
          case 40:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaFinDeAnoAnterior(fechaOrigen_local));
            break;
          case 41:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaInicioDeAnoSiguiente(fechaOrigen_local));
            break;
          case 42:
            valorCampoCalculado_local = String.valueOf(mf.obtenerFechaFinDeAnoSiguiente(fechaOrigen_local));
            break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fechaOrigen_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String complementarValorCampoCalculado(Campo pCampoCalculado, String pValorCampoCalculado) {
    String valorCampoCalculado_local = "";
    String tipoDato_local = null;
    int longitudCampoCalculado_local = -1;
    Campo campoValor_local = null;
    int tipoCampoCalculado_local = -1;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    if (pValorCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return valorCampoCalculado_local;
    }
    
    try {
      valorCampoCalculado_local = pValorCampoCalculado;
      tipoDato_local = pCampoCalculado.getFormatoCampo().getTipoDato();
      longitudCampoCalculado_local = pCampoCalculado.getFormatoCampo().getLongitudCampo();
      campoValor_local = pCampoCalculado.getCalculoCampo().getCampoValor();
      tipoCampoCalculado_local = pCampoCalculado.getCalculoCampo().getCampoCalculado();
      
      if (verificarTipoCalculoCondicion(tipoCampoCalculado_local)) {
        valorCampoCalculado_local = String.valueOf(obtenerValorCampoCalculadoCondicion(valorCampoCalculado_local, tipoCampoCalculado_local));
      } else {
        
        if (pCampoCalculado.esTipoDatoTexto() && !pCampoCalculado.esTipoDatoParrafo()) {
          valorCampoCalculado_local = mc.convertirAMayusculas(valorCampoCalculado_local);
        }
        if (verificarTipoCalculoObtieneFechaONumeroInterno(tipoCampoCalculado_local)) {
          valorCampoCalculado_local = obtenerValorCampoCalculadoObtieneFechaONumeroInterno(valorCampoCalculado_local, tipoCampoCalculado_local);
        }
        
        if (pCampoCalculado.esTipoDatoTabla() && 
          !mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
          valorCampoCalculado_local = String.valueOf(obtenerIdValorTabla(pCampoCalculado, valorCampoCalculado_local));
        }
        
        if (mc.sonCadenasIguales(valorCampoCalculado_local, "null") || mc.esCadenaVacia(valorCampoCalculado_local))
        {
          if (pCampoCalculado.esTipoDatoNumerico()) {
            valorCampoCalculado_local = String.valueOf(0);
          }
        }
        if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
          if (pCampoCalculado.esCampoEnlazado() && campoValor_local.esCampoEnlazado()) {
            valorCampoCalculado_local = String.valueOf(getAdministradorBaseDatosAplicacion().obtenerIdValorCampoEnlazado(getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(pCampoCalculado.getEnlaceCampo().getEnlazado().getIdAplicacion()), valorCampoCalculado_local, false));
          
          }
          else if (campoValor_local.esTipoDatoTabla() && !mc.esCadenaNumerica(valorCampoCalculado_local, true)) {
            
            valorCampoCalculado_local = String.valueOf(obtenerIdValorTabla(campoValor_local, valorCampoCalculado_local));
          } 
        }
        
        if (pCampoCalculado.esTipoDatoNumerico() && mc.esCadenaNumerica(valorCampoCalculado_local, false)) {
          
          valorCampoCalculado_local = String.valueOf(op.redondearNumero(BigDecimal.valueOf(Double.parseDouble(valorCampoCalculado_local)), pCampoCalculado.getFormatoCampo().getNumeroDecimales()));
          
          if (pCampoCalculado.getFormatoCampo().getNumeroDecimales() == 0) {
            valorCampoCalculado_local = String.valueOf((int)Double.parseDouble(valorCampoCalculado_local));
          }
          if (mc.sonCadenasIguales(tipoDato_local, "BB") || mc.sonCadenasIguales(tipoDato_local, "GG"))
          {
            if (verificarMenorQueCero(new BigDecimal(valorCampoCalculado_local))) {
              valorCampoCalculado_local = String.valueOf(0);
            }
          }
        } 
        valorCampoCalculado_local = getAdministradorBaseDatosSisnet().obtenerValorCampoCalculadoConFormato(pCampoCalculado, valorCampoCalculado_local, 0);
        
        if (mc.obtenerLongitudCadena(valorCampoCalculado_local) > longitudCampoCalculado_local && longitudCampoCalculado_local != -1)
        {
          valorCampoCalculado_local = mc.obtenerSubCadena(valorCampoCalculado_local, 0, longitudCampoCalculado_local);
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
      campoValor_local = null;
    } 
    
    return valorCampoCalculado_local;
  }
  private String elevarValorCampoCalculado(Campo pCampo, int pValorLlaveOrigenUno, int pValorLlaveOrigenDos) {
    String valorCampoCalculado_local = "";
    int valorEnteroOrigenUno_local = 0;
    int valorEnteroOrigenDos_local = 0;
    double valorRealOrigenUno_local = 0.0D;
    double valorRealOrigenDos_local = 0.0D;
    boolean esOrigenUnoEntero_local = false;
    boolean esOrigenUnoReal_local = false;
    boolean esOrigenDosEntero_local = false;
    boolean esOrigenDosReal_local = false;
    BigDecimal valorRealCampoCalculado_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      esOrigenUnoEntero_local = campoOrigenUno_local.esTipoDatoNumeroEntero();
      esOrigenUnoReal_local = campoOrigenUno_local.esTipoDatoNumeroReal();
      esOrigenDosEntero_local = campoOrigenDos_local.esTipoDatoNumeroEntero();
      esOrigenDosReal_local = campoOrigenDos_local.esTipoDatoNumeroReal();
      
      valorEnteroOrigenUno_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorRealOrigenUno_local = obtenerValorCampoOperandoNumeroReal(campoOrigenUno_local, pValorLlaveOrigenUno);
      valorEnteroOrigenDos_local = obtenerValorCampoOperandoNumeroEntero(campoOrigenDos_local, pValorLlaveOrigenDos);
      valorRealOrigenDos_local = obtenerValorCampoOperandoNumeroReal(campoOrigenDos_local, pValorLlaveOrigenDos);
      if (esOrigenUnoEntero_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.elevarA(valorEnteroOrigenUno_local, valorEnteroOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoEntero_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.elevarA(valorEnteroOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosReal_local) {
        valorRealCampoCalculado_local = op.elevarA(valorRealOrigenUno_local, valorRealOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
      if (esOrigenUnoReal_local && esOrigenDosEntero_local) {
        valorRealCampoCalculado_local = op.elevarA(valorRealOrigenUno_local, valorEnteroOrigenDos_local);
        if (valorRealCampoCalculado_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoCalculado_local = valorRealCampoCalculado_local.toString();
        }
        return valorCampoCalculado_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampoCalculado_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private void asignarValorCampoCalculadoParaRecalculo(Campo pCampoCalculado, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    int tipoCampoCalculado_local = 1;
    int valorLlaveOrigenUno_local = -1;
    int valorLlaveOrigenDos_local = -1;
    int valorLlavePrimaria_local = -1;
    int valorLlavePrimariaPrincipal_local = -1;
    boolean camposMismoGrupo_local = false;
    boolean esGrupoMultipleCampoCalculado_local = false;
    String valorCampoCalculado_local = null;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    Campo campoValor_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      campoValor_local = pCampoCalculado.getCalculoCampo().getCampoValor();
      campoOrigenUno_local = pCampoCalculado.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampoCalculado.getCalculoCampo().getCampoOrigenDos();
      esGrupoMultipleCampoCalculado_local = pCampoCalculado.getGrupoInformacion().esGrupoInformacionMultiple();
      
      if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
        camposMismoGrupo_local = getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampoCalculado, campoValor_local);
      } else {
        camposMismoGrupo_local = getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(campoOrigenUno_local, campoOrigenDos_local);
        
        valorLlaveOrigenUno_local = getAdministradorBaseDatosSisnet().obtenerValorLlavePrimariaCampoOrigen(campoOrigenUno_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion, camposMismoGrupo_local);
        
        valorLlaveOrigenDos_local = getAdministradorBaseDatosSisnet().obtenerValorLlavePrimariaCampoOrigen(campoOrigenDos_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion, camposMismoGrupo_local);
      } 
      
      if (pCampoCalculado.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        valorCampoCalculado_local = pCampoCalculado.getValorCampo().toString();
      } else {
        valorCampoCalculado_local = "";
      } 
      
      if (camposMismoGrupo_local || esGrupoMultipleCampoCalculado_local) {
        valorLlavePrimaria_local = pValorLlavePrimariaGrupoInformacion;
      } else {
        valorLlavePrimaria_local = pValorLlavePrimariaPrincipal;
      } 
      
      if (esGrupoMultipleCampoCalculado_local) {
        valorLlavePrimariaPrincipal_local = pValorLlavePrimariaPrincipal;
      } else {
        valorLlavePrimariaPrincipal_local = pValorLlavePrimariaGrupoInformacion;
      } 
      
      tipoCampoCalculado_local = pCampoCalculado.getCalculoCampo().getCampoCalculado();
      switch (tipoCampoCalculado_local) {
        case 2:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        case 20:
        case 29:
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
          if (pCampoCalculado.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = obtenerValorCampoOperandoHabilitadoPorTipoDato(pCampoCalculado, valorLlavePrimariaPrincipal_local, valorLlavePrimaria_local);
            break;
          } 
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            if (campoValor_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
              valorCampoCalculado_local = obtenerValorCampoOperandoTipoDato(campoValor_local, pValorLlavePrimariaGrupoInformacion, pCampoCalculado.esTipoDatoNumerico());
              break;
            } 
            valorCampoCalculado_local = obtenerValorCampoOperandoTipoDato(campoValor_local, pValorLlavePrimariaPrincipal, pCampoCalculado.esTipoDatoNumerico());
          } 
          break;
        
        case 3:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = obtenerValorCampoOperandoPrimerRegistroTipoDato(campoValor_local, valorLlavePrimariaPrincipal_local);
          }
          break;
        
        case 4:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = obtenerValorCampoOperandoUltimoRegistroTipoDato(campoValor_local, valorLlavePrimariaPrincipal_local);
          }
          break;
        
        case 5:
          valorCampoCalculado_local = sumarValoresCampoCalculado(pCampoCalculado, valorLlaveOrigenUno_local, valorLlaveOrigenDos_local);
          break;
        
        case 6:
          valorCampoCalculado_local = restarValoresCampoCalculado(pCampoCalculado, valorLlaveOrigenUno_local, valorLlaveOrigenDos_local);
          break;
        
        case 7:
          valorCampoCalculado_local = multiplicarValoresCampoCalculado(pCampoCalculado, valorLlaveOrigenUno_local, valorLlaveOrigenDos_local);
          break;
        
        case 8:
          valorCampoCalculado_local = dividirValoresCampoCalculado(pCampoCalculado, valorLlaveOrigenUno_local, valorLlaveOrigenDos_local);
          break;
        
        case 9:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            setEstado("Incluyendo");
            valorCampoCalculado_local = totalizarCampoCalculadoTipoDato(campoValor_local, -1, valorLlavePrimariaPrincipal_local);
          } 
          break;
        
        case 10:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            setEstado("Modificando");
            valorCampoCalculado_local = totalizarCampoCalculadoTipoDato(campoValor_local, valorLlavePrimaria_local, valorLlavePrimariaPrincipal_local);
          } 
          break;
        
        case 11:
        case 12:
        case 21:
        case 27:
          valorCampoCalculado_local = concatenarValoresCampoCalculado(pCampoCalculado, valorLlaveOrigenUno_local, valorLlaveOrigenDos_local);
          break;
        
        case 13:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = obtenerValorAbsolutoTipoDato(campoValor_local, pValorLlavePrimariaGrupoInformacion);
          }
          break;
        case 14:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = sumarUnidadTipoDato(campoValor_local, pValorLlavePrimariaGrupoInformacion);
          }
          break;
        case 22:
        case 23:
        case 24:
        case 28:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = concatenarValoresColumnaCampoCalculado(pCampoCalculado, pValorLlavePrimariaPrincipal);
          }
          break;
        case 25:
          valorCampoCalculado_local = obtenerValorPrimerCampoConValor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 26:
          valorCampoCalculado_local = sumarValoresCamposDesdeHasta(pCampoCalculado.getCalculoCampo().getCampoOrigenUno(), pCampoCalculado.getCalculoCampo().getCampoOrigenDos(), valorLlavePrimaria_local);
          break;
        
        case 43:
          valorCampoCalculado_local = elevarValorCampoCalculado(pCampoCalculado, valorLlaveOrigenUno_local, valorLlaveOrigenDos_local);
          break;
      } 
      
      valorCampoCalculado_local = complementarValorCampoCalculado(pCampoCalculado, valorCampoCalculado_local);
      actualizarValorCampoCalculadoTipoDato(pCampoCalculado, valorLlavePrimaria_local, valorCampoCalculado_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampoCalculado_local = null;
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
      campoValor_local = null;
    } 
  }
  private void asignarValorNuloCampoCalculado(Campo pCampo, int pValorLlavePrimaria) {
    String valorNulo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      valorNulo_local = "null";
      if (pCampo.esTipoDatoNumerico()) {
        valorNulo_local = String.valueOf(0);
      }
      actualizarValorCampoCalculado(pCampo, pValorLlavePrimaria, valorNulo_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorNulo_local = null;
    } 
  }
  public void actualizarValoresCamposCalculadosRecalculablesGrupoNoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrincipal) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposCalculadosRecalculablesGrupoInformacion(pGrupoInformacion);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          if (getManejadorHabilitacionCampos().verificarHabilitarCampo(listaCampo_local, campo_local, pValorLlavePrincipal, pValorLlavePrincipal, true)) {
            
            asignarValorCampoCalculadoParaRecalculo(campo_local, pValorLlavePrincipal, pValorLlavePrincipal); continue;
          } 
          if (campo_local.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO) {
            if (campo_local.esBorrarValorNoHabilitado())
              asignarValorNuloCampoCalculado(campo_local, pValorLlavePrincipal); 
            continue;
          } 
          asignarValorNuloCampoCalculado(campo_local, pValorLlavePrincipal);
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
    } 
  }
  public void actualizarValoresCamposCalculadosNoRecalculablesGrupoNoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrincipal) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposCalculadosNoRecalculablesGrupoInformacion(pGrupoInformacion);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          if (getManejadorHabilitacionCampos().verificarHabilitarCampo(listaCampo_local, campo_local, pValorLlavePrincipal, pValorLlavePrincipal, true)) {
            
            asignarValorCampoCalculadoParaRecalculo(campo_local, pValorLlavePrincipal, pValorLlavePrincipal); continue;
          } 
          asignarValorNuloCampoCalculado(campo_local, pValorLlavePrincipal);
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
    } 
  }
  public void actualizarValoresCamposCalculadosRecalculablesGrupoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrincipal) {
    int valorLlavePrimaria_local = -1;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;
    ListaCadenas listaNombresCamposGrupoInformacion_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    ManejadorResultadoConsultaSQL resultadoConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposCalculadosRecalculablesGrupoInformacion(pGrupoInformacion);
      listaNombresCamposGrupoInformacion_local = getAdministradorBaseDatosSisnet().obtenerListaNombresCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), false);
      
      consultaSQL_local = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionGrupoInformacionAplicacion(pGrupoInformacion, listaNombresCamposGrupoInformacion_local, pValorLlavePrincipal);
      
      resultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      resultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = resultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQL_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          valorLlavePrimaria_local = Integer.parseInt(resultSet_local.getObject(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion())).toString());
          
          if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
            iterador_local = listaCampo_local.iterator();
            while (iterador_local.hasNext()) {
              campo_local = (Campo)iterador_local.next();
              if (getManejadorHabilitacionCampos().verificarHabilitarCampo(listaCampo_local, campo_local, pValorLlavePrincipal, valorLlavePrimaria_local, true)) {
                
                asignarValorCampoCalculadoParaRecalculo(campo_local, pValorLlavePrincipal, valorLlavePrimaria_local); continue;
              } 
              if (campo_local.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO) {
                if (campo_local.esBorrarValorNoHabilitado())
                  asignarValorNuloCampoCalculado(campo_local, valorLlavePrimaria_local); 
                continue;
              } 
              asignarValorNuloCampoCalculado(campo_local, valorLlavePrimaria_local);
            }
          
          }
        
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      resultSet_local = null;
      listaCampo_local = null;
      consultaSQL_local = null;
      listaNombresCamposGrupoInformacion_local = null;
      resultadoConsultaSQL_local = null;
    } 
  }
  public void actualizarValoresCamposCalculadosNoRecalculablesGrupoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrincipal) {
    int valorLlavePrimaria_local = -1;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;
    ListaCadenas listaNombresCamposGrupoInformacion_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    ManejadorResultadoConsultaSQL resultadoConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposCalculadosNoRecalculablesGrupoInformacion(pGrupoInformacion);
      listaNombresCamposGrupoInformacion_local = getAdministradorBaseDatosSisnet().obtenerListaNombresCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), false);
      
      consultaSQL_local = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionGrupoInformacionAplicacion(pGrupoInformacion, listaNombresCamposGrupoInformacion_local, pValorLlavePrincipal);
      
      resultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      resultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = resultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQL_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          valorLlavePrimaria_local = Integer.parseInt(resultSet_local.getObject(ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion())).toString());
          
          if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
            iterador_local = listaCampo_local.iterator();
            while (iterador_local.hasNext()) {
              campo_local = (Campo)iterador_local.next();
              if (getManejadorHabilitacionCampos().verificarHabilitarCampo(listaCampo_local, campo_local, pValorLlavePrincipal, valorLlavePrimaria_local, true)) {
                
                asignarValorCampoCalculadoParaRecalculo(campo_local, pValorLlavePrincipal, valorLlavePrimaria_local); continue;
              } 
              asignarValorNuloCampoCalculado(campo_local, valorLlavePrimaria_local);
            }
          
          } 
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      resultSet_local = null;
      listaCampo_local = null;
      consultaSQL_local = null;
      listaNombresCamposGrupoInformacion_local = null;
      resultadoConsultaSQL_local = null;
    } 
  }
  private String concatenarValoresCampoCalculadoAplicacion(Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion) {
    String valorCampoCalculado_local = "";
    int valorLlavePrimariaUno_local = -1;
    int valorLlavePrimariaDos_local = -1;
    Campo campoOrigenUno_local = null;
    Campo campoOrigenDos_local = null;
    
    try {
      campoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno();
      campoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos();
      valorLlavePrimariaUno_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenUno_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorLlavePrimariaDos_local = obtenerValorLlavePrimariaCampoOrigen(campoOrigenDos_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
      
      valorCampoCalculado_local = concatenarValoresCampoCalculado(pCampo, valorLlavePrimariaUno_local, valorLlavePrimariaDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoOrigenUno_local = null;
      campoOrigenDos_local = null;
    } 
    return valorCampoCalculado_local;
  }
  private String obtenerValorAbsolutoTipoDato(Campo pCampoValor, int pValorLlavePrimaria) {
    String valorAbsoluto_local = "";
    int valorEntero_local = -1;
    String valorCampo_local = null;
    BigDecimal valorReal_local = null;
    
    if (pCampoValor == ConstantesGeneral.VALOR_NULO) {
      return valorAbsoluto_local;
    }
    
    try {
      valorCampo_local = obtenerValorCampoOperandoTipoDato(pCampoValor, pValorLlavePrimaria, pCampoValor.esTipoDatoNumerico());
      if (pCampoValor.esTipoDatoNumeroEntero() && mc.esCadenaNumerica(valorCampo_local, pCampoValor.esTipoDatoNumeroEntero())) {
        valorEntero_local = Integer.parseInt(valorCampo_local);
        valorAbsoluto_local = String.valueOf(op.obtenerValorAbsoluto(valorEntero_local));
        return valorAbsoluto_local;
      } 
      if (pCampoValor.esTipoDatoNumeroReal() && mc.esCadenaNumerica(valorCampo_local, pCampoValor.esTipoDatoNumeroEntero())) {
        valorReal_local = new BigDecimal(valorCampo_local);
        valorAbsoluto_local = String.valueOf(op.obtenerValorAbsoluto(valorReal_local));
        return valorAbsoluto_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return valorAbsoluto_local;
  }
  private String sumarUnidadTipoDato(Campo pCampoValor, int pValorLlavePrimaria) {
    String sumarUnidad_local = "";
    int valorEntero_local = -1;
    BigDecimal valorReal_local = null;
    
    if (pCampoValor == ConstantesGeneral.VALOR_NULO) {
      return sumarUnidad_local;
    }
    
    try {
      if (pCampoValor.esTipoDatoNumeroEntero() || pCampoValor.esTipoDatoTabla()) {
        valorEntero_local = obtenerValorCampoOperandoNumeroEntero(pCampoValor, pValorLlavePrimaria);
        sumarUnidad_local = String.valueOf(op.sumarUnidad(valorEntero_local));
        return sumarUnidad_local;
      } 
      if (pCampoValor.esTipoDatoNumeroReal()) {
        valorReal_local = new BigDecimal(obtenerValorCampoOperandoNumeroReal(pCampoValor, pValorLlavePrimaria));
        sumarUnidad_local = String.valueOf(op.sumarUnidad(valorReal_local));
        return sumarUnidad_local;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorReal_local = null;
    } 
    
    return sumarUnidad_local;
  }
  public void asignarValorCampoCalculado(Campo pCampoCalculado, int pValorLlavePrimariaGrupoInformacion, int pValorLlavePrimariaPrincipal) {
    int tipoCampoCalculado_local = 1;
    int valorLlavePrimaria_local = -1;
    int valorLlavePrimariaPrincipal_local = -1;
    int valorLlavePrimariaGrupo_local = -1;
    boolean camposMismoGrupo_local = false;
    boolean esGrupoMultipleCampoCalculado_local = false;
    String valorCampoCalculado_local = null;
    Campo campoValor_local = null;
    
    if (pCampoCalculado == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (esModificacion() && !pCampoCalculado.getCalculoCampo().esRecalculable()) {
        return;
      }
      
      campoValor_local = pCampoCalculado.getCalculoCampo().getCampoValor();
      esGrupoMultipleCampoCalculado_local = pCampoCalculado.getGrupoInformacion().esGrupoInformacionMultiple();
      camposMismoGrupo_local = getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampoCalculado, campoValor_local);
      
      if (pCampoCalculado.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
        valorCampoCalculado_local = pCampoCalculado.getValorCampo().toString();
      } else {
        valorCampoCalculado_local = "";
      } 
      
      if (camposMismoGrupo_local) {
        valorLlavePrimaria_local = pValorLlavePrimariaGrupoInformacion;
      } else {
        valorLlavePrimaria_local = pValorLlavePrimariaPrincipal;
      } 
      
      if (esGrupoMultipleCampoCalculado_local) {
        valorLlavePrimariaGrupo_local = pValorLlavePrimariaGrupoInformacion;
        valorLlavePrimariaPrincipal_local = pValorLlavePrimariaPrincipal;
      } else {
        valorLlavePrimariaPrincipal_local = valorLlavePrimariaGrupo_local = pValorLlavePrimariaPrincipal;
      } 
      
      tipoCampoCalculado_local = pCampoCalculado.getCalculoCampo().getCampoCalculado();
      switch (tipoCampoCalculado_local) {
        case 2:
        case 15:
        case 16:
        case 17:
        case 18:
        case 19:
        case 20:
        case 29:
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
          if (pCampoCalculado.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = obtenerValorCampoOperandoHabilitadoPorTipoDato(pCampoCalculado, valorLlavePrimariaPrincipal_local, valorLlavePrimaria_local);
            break;
          } 
          valorCampoCalculado_local = obtenerValorCampoOperandoTipoDato(campoValor_local, valorLlavePrimaria_local, pCampoCalculado.esTipoDatoNumerico());
          break;
        
        case 3:
          valorCampoCalculado_local = obtenerValorCampoOperandoPrimerRegistroTipoDato(campoValor_local, valorLlavePrimariaPrincipal_local);
          break;
        
        case 4:
          valorCampoCalculado_local = obtenerValorCampoOperandoUltimoRegistroTipoDato(campoValor_local, valorLlavePrimariaPrincipal_local);
          break;
        
        case 5:
          valorCampoCalculado_local = sumarValoresCampoCalculadoAplicacion(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 6:
          valorCampoCalculado_local = restarValoresCampoCalculadoAplicacion(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 7:
          valorCampoCalculado_local = multiplicarValoresCampoCalculadoAplicacion(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 8:
          valorCampoCalculado_local = dividirValoresCampoCalculadoAplicacion(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 9:
          valorCampoCalculado_local = totalizarCampoCalculadoTipoDato(campoValor_local, -1, valorLlavePrimariaPrincipal_local);
          break;
        
        case 10:
          valorCampoCalculado_local = totalizarCampoCalculadoTipoDato(campoValor_local, valorLlavePrimaria_local, valorLlavePrimariaPrincipal_local);
          break;
        
        case 11:
        case 12:
        case 21:
        case 27:
          valorCampoCalculado_local = concatenarValoresCampoCalculadoAplicacion(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 13:
          valorCampoCalculado_local = obtenerValorAbsolutoTipoDato(campoValor_local, pValorLlavePrimariaGrupoInformacion);
          break;
        case 14:
          valorCampoCalculado_local = sumarUnidadTipoDato(campoValor_local, pValorLlavePrimariaGrupoInformacion);
          break;
        case 22:
        case 23:
        case 24:
        case 28:
          if (campoValor_local != ConstantesGeneral.VALOR_NULO) {
            valorCampoCalculado_local = concatenarValoresColumnaCampoCalculado(pCampoCalculado, pValorLlavePrimariaPrincipal);
          }
          break;
        case 25:
          valorCampoCalculado_local = obtenerValorPrimerCampoConValor(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
        
        case 26:
          valorCampoCalculado_local = sumarValoresCamposDesdeHasta(pCampoCalculado.getCalculoCampo().getCampoOrigenUno(), pCampoCalculado.getCalculoCampo().getCampoOrigenDos(), valorLlavePrimaria_local);
          break;
        
        case 43:
          valorCampoCalculado_local = elevarValorCampoCalculado(pCampoCalculado, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion);
          break;
      } 
      
      valorCampoCalculado_local = complementarValorCampoCalculado(pCampoCalculado, valorCampoCalculado_local);
      pCampoCalculado.setValorCampo(valorCampoCalculado_local);
      if (esModificacion()) {
        actualizarValorCampoCalculadoTipoDato(pCampoCalculado, valorLlavePrimariaGrupo_local, valorCampoCalculado_local);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampoCalculado_local = null;
      campoValor_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\informacionRecalculable\ManejadorCampoCalculado.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */