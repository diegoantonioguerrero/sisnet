package com.sisnet.aplicacion.manejadores.informacionRecalculable;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.sql.ResultSet;
import java.util.Iterator;
public class ManejadorCampoEnlazado
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private MotorAplicacion aMotorAplicacion;
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
  public String obtenerValorCampoEnlazado(Aplicacion pAplicacionEnlace, int pValorEnlace) {
    String valorCampoEnlazado_local = "";
    Object valorCampo_local = null;
    Campo campoValorUnico_local = null;
    
    if (pAplicacionEnlace == ConstantesGeneral.VALOR_NULO) {
      return valorCampoEnlazado_local;
    }
    
    try {
      if (pValorEnlace != -2) {
        campoValorUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(pAplicacionEnlace.getIdAplicacion());
        valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValorUnico_local, ap.conformarNombreCampoLlavePrimaria(pAplicacionEnlace.getNombreAplicacion()), pValorEnlace);
        
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorCampoEnlazado_local = valorCampo_local.toString();
        }
      } else {
        valorCampoEnlazado_local = "DESCONOCIDO";
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
      campoValorUnico_local = null;
    } 
    
    return valorCampoEnlazado_local;
  }
  public int obtenerIdValorCampoEnlazado(Aplicacion pAplicacionEnlace, String pValorCampoEnlazado) {
    int idValorCampoEnlazado_local = 0;
    Campo campoValorUnico_local = null;
    
    if (pAplicacionEnlace == ConstantesGeneral.VALOR_NULO) {
      return idValorCampoEnlazado_local;
    }
    if (pValorCampoEnlazado == ConstantesGeneral.VALOR_NULO) {
      return idValorCampoEnlazado_local;
    }
    
    try {
      if (!mc.sonCadenasIguales(pValorCampoEnlazado, "DESCONOCIDO")) {
        campoValorUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(pAplicacionEnlace.getIdAplicacion());
        idValorCampoEnlazado_local = getAdministradorBaseDatosAplicacion().obtenerIdValorCampoEnlazado(campoValorUnico_local, pValorCampoEnlazado, !campoValorUnico_local.esTipoDatoTexto());
      } else {
        
        idValorCampoEnlazado_local = -2;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoValorUnico_local = null;
    } 
    
    return idValorCampoEnlazado_local;
  }
  public String obtenerValorDependienteEnlazado(Campo pCampo, int pValorEnlace, String pValorCampo) {
    String valorDependiente_local = "";
    String nombreLlavePrimaria_local = null;
    Object valorCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorDependiente_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorDependiente_local;
    }
    
    try {
      if (pValorEnlace != -2) {
        if (pCampo.getEnlaceCampo().getCampoOrigenEnlace() != ConstantesGeneral.VALOR_NULO) {
          nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getEnlaceCampo().getCampoOrigenEnlace().getGrupoInformacion().getAplicacion().getNombreAplicacion());
          
          if (pValorEnlace != -1) {
            valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(pCampo.getEnlaceCampo().getCampoOrigenEnlace(), nombreLlavePrimaria_local, pValorEnlace);
            
            if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
              valorDependiente_local = valorCampo_local.toString();
            }
          } 
        } 
      } else {
        valorDependiente_local = pValorCampo;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      nombreLlavePrimaria_local = null;
      valorCampo_local = null;
    } 
    return valorDependiente_local;
  }
  public String obtenerValorTablaDependienteEnlazado(Campo pCampo, int pValorEnlace, String pValorCampo) {
    String valorCampo_local = "";
    String valorDependienteEnlazado_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorCampo_local;
    }
    if (pValorEnlace == -2) {
      return valorCampo_local;
    }
    
    try {
      if (pValorEnlace != 0) {
        valorCampo_local = pValorCampo;
        valorDependienteEnlazado_local = obtenerValorDependienteEnlazado(pCampo, pValorEnlace, pValorCampo);
        if (mc.esCadenaNumerica(valorDependienteEnlazado_local, true)) {
          valorCampo_local = valorDependienteEnlazado_local;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorDependienteEnlazado_local = null;
    } 
    
    return valorCampo_local;
  }
  public int obtenerValorEnlaceDependienteEnlazado(Campo pCampo, ListaCampo pListaCampo, int pValorLlavePrimariaAnterior) {
    int valorEnlace_local = -1;
    String registroValorEnlace_local = null;
    String nombreGrupoInformacionPrincipal_local = null;
    Campo campoRelacionado_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    
    try {
      campoRelacionado_local = pCampo.getEnlaceCampo().getCampoEnlaceDepende();
      if (campoRelacionado_local != ConstantesGeneral.VALOR_NULO) {
        if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo, campoRelacionado_local)) {
          if (!mc.sonCadenasIguales(pListaCampo.obtenerValorCampo(campoRelacionado_local.getNombreCampo()), ""))
          {
            valorEnlace_local = Integer.parseInt(pListaCampo.obtenerValorCampo(campoRelacionado_local.getNombreCampo()));
          }
        } else {
          nombreGrupoInformacionPrincipal_local = ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion());
          
          registroValorEnlace_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoRelacionado_local, ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacionPrincipal_local), pValorLlavePrimariaAnterior).toString();
          
          if (mc.esCadenaNumerica(registroValorEnlace_local, true)) {
            valorEnlace_local = Integer.parseInt(registroValorEnlace_local);
          }
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      registroValorEnlace_local = null;
      nombreGrupoInformacionPrincipal_local = null;
      campoRelacionado_local = null;
    } 
    return valorEnlace_local;
  }
  public int obtenerValorEnlazadoDependienteEnlazado(Campo pCampo, int pValorEnlace, String pValorCampo, boolean pEsRecalculable) {
    int valorEnlace_local = 0;
    int valorCampo_local = -1;
    int valorDependiente_local = -1;
    String valorDependienteEnlazado_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    
    try {
      if (mc.esCadenaNumerica(pValorCampo, true)) {
        valorCampo_local = Integer.parseInt(pValorCampo);
      }
      if (pValorEnlace != -2) {
        if (pValorEnlace != 0) {
          valorDependienteEnlazado_local = obtenerValorDependienteEnlazado(pCampo, pValorEnlace, pValorCampo);
          if (mc.esCadenaNumerica(valorDependienteEnlazado_local, true)) {
            valorDependiente_local = Integer.parseInt(valorDependienteEnlazado_local);
          }
          if (!pEsRecalculable) {
            valorEnlace_local = valorCampo_local;
          }
          if (valorDependiente_local != -1) {
            valorEnlace_local = valorDependiente_local;
          }
        }
      
      } else if (mc.esCadenaNumerica(pValorCampo, true)) {
        valorEnlace_local = Integer.parseInt(pValorCampo);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorDependienteEnlazado_local = null;
    } 
    
    return valorEnlace_local;
  }
  public void asignarValoresDependientesEnlazado(ListaCampo pListaCampo, int pValorLlavePrimariaAnterior) {
    int dependienteEnlazado_local = -1;
    int valorEnlace_local = -1;
    boolean esRecalculable_local = false;
    String tipoDato_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pValorLlavePrimariaAnterior != -1) {
        iterador_local = pListaCampo.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          dependienteEnlazado_local = campo_local.getEnlaceCampo().getDependienteEnlazado();
          esRecalculable_local = (dependienteEnlazado_local == 1);
          if (esRecalculable_local) {
            tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
            valorEnlace_local = obtenerValorEnlaceDependienteEnlazado(campo_local, pListaCampo, pValorLlavePrimariaAnterior);
            if (valorEnlace_local != -2) {
              if (mc.esCadenaNumerica(tipoDato_local, true)) {
                campo_local.setValorCampo(obtenerValorTablaDependienteEnlazado(campo_local, valorEnlace_local, ""));
                continue;
              } 
              if (campo_local.esCampoEnlazado()) {
                campo_local.setValorCampo(Integer.valueOf(obtenerValorEnlazadoDependienteEnlazado(campo_local, valorEnlace_local, "", esRecalculable_local)));
                continue;
              } 
              campo_local.setValorCampo(obtenerValorDependienteEnlazado(campo_local, valorEnlace_local, ""));
            }
          
          }
        
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
      tipoDato_local = null;
    } 
  }
  private String obtenerValorEnlace(Campo pCampo, int pValorLlavePrimaria) {
    String valorEnlace_local = "";
    String nombreGrupoInformacion_local = null;
    Object valorCampo_local = null;
    Campo campoEnlaceDepende_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    
    try {
      campoEnlaceDepende_local = pCampo.getEnlaceCampo().getCampoEnlaceDepende();
      if (campoEnlaceDepende_local != ConstantesGeneral.VALOR_NULO) {
        if (campoEnlaceDepende_local.getGrupoInformacion().esGrupoInformacionMultiple() && getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo, campoEnlaceDepende_local)) {
          
          nombreGrupoInformacion_local = campoEnlaceDepende_local.getGrupoInformacion().getNombreGrupoInformacion();
        } else {
          nombreGrupoInformacion_local = campoEnlaceDepende_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
        } 
        valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoEnlaceDepende_local, ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local), pValorLlavePrimaria);
        
        if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
          valorEnlace_local = valorCampo_local.toString();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      valorCampo_local = null;
      campoEnlaceDepende_local = null;
      nombreGrupoInformacion_local = null;
    } 
    
    return valorEnlace_local;
  }
  private void actualizarCamposDependientesEnlazadoGrupoInformacion(ListaCampo pListaCampo, String pNombreLlavePrincipal, int pValorLlavePrincipal, int pValorLlavePrimaria) {
    int idValorEnlace_local = -1;
    String tipoDato_local = null;
    String valorEnlace_local = null;
    String valorCampo_local = null;
    String valorDependienteEnlazado_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = pListaCampo.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
        if (campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
          valorEnlace_local = obtenerValorEnlace(campo_local, pValorLlavePrimaria);
        } else {
          valorEnlace_local = obtenerValorEnlace(campo_local, pValorLlavePrincipal);
        } 
        valorCampo_local = "";
        if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
          valorCampo_local = campo_local.getValorCampo().toString();
        }
        if (!mc.sonCadenasIguales(valorEnlace_local, "")) {
          valorDependienteEnlazado_local = "";
          idValorEnlace_local = Integer.parseInt(valorEnlace_local);
          if (idValorEnlace_local >= 0) {
            if (mc.esCadenaNumerica(tipoDato_local, true)) {
              valorDependienteEnlazado_local = obtenerValorTablaDependienteEnlazado(campo_local, idValorEnlace_local, valorCampo_local);
            
            }
            else if (campo_local.esCampoEnlazado()) {
              valorDependienteEnlazado_local = String.valueOf(obtenerValorEnlazadoDependienteEnlazado(campo_local, idValorEnlace_local, valorCampo_local, (campo_local.getEnlaceCampo().getDependienteEnlazado() == 1)));
            }
            else {
              
              valorDependienteEnlazado_local = obtenerValorDependienteEnlazado(campo_local, idValorEnlace_local, valorCampo_local);
            } 
            
            getAdministradorBaseDatosAplicacion().actualizarRegistrosCampoDependienteEnlazado(campo_local, valorDependienteEnlazado_local, idValorEnlace_local, pNombreLlavePrincipal, pValorLlavePrincipal, pValorLlavePrimaria);
          }
        
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      tipoDato_local = null;
      iterador_local = null;
      valorEnlace_local = null;
      valorCampo_local = null;
      valorDependienteEnlazado_local = null;
    } 
  }
  public void actualizarCamposDependientesEnlazadosRecalculablesGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    String nombreLlavePrincipal_local = null;
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacion(pGrupoInformacion);
      nombreLlavePrincipal_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
      actualizarCamposDependientesEnlazadoGrupoInformacion(listaCampo_local, nombreLlavePrincipal_local, pValorLlavePrimaria, -1);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      nombreLlavePrincipal_local = null;
    } 
  }
  public void actualizarCamposDependientesEnlazadosNoRecalculablesGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    String nombreLlavePrincipal_local = null;
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacion(pGrupoInformacion);
      nombreLlavePrincipal_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
      actualizarCamposDependientesEnlazadoGrupoInformacion(listaCampo_local, nombreLlavePrincipal_local, pValorLlavePrimaria, -1);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      nombreLlavePrincipal_local = null;
    } 
  }
  public void actualizarCamposDependientesEnlazadoRecalculablesGrupoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrincipal_local = null;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;
    ListaCampo listaCampo_local = null;
    ListaCadenas listaNombresCamposGrupoInformacion_local = null;
    ManejadorResultadoConsultaSQL resultadoConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaNombresCamposGrupoInformacion_local = getAdministradorBaseDatosSisnet().obtenerListaNombresCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), false);
      
      listaCampo_local = getMotorAplicacion().obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacion(pGrupoInformacion);
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
      nombreLlavePrincipal_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
      consultaSQL_local = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionGrupoInformacionAplicacion(pGrupoInformacion, listaNombresCamposGrupoInformacion_local, pValorLlavePrimaria);
      
      resultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      resultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = resultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQL_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          valorLlavePrimaria_local = Integer.parseInt(resultSet_local.getObject(nombreLlavePrimaria_local).toString());
          actualizarCamposDependientesEnlazadoGrupoInformacion(listaCampo_local, nombreLlavePrincipal_local, pValorLlavePrimaria, valorLlavePrimaria_local);
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      resultSet_local = null;
      listaCampo_local = null;
      consultaSQL_local = null;
      nombreLlavePrimaria_local = null;
      nombreLlavePrincipal_local = null;
      listaNombresCamposGrupoInformacion_local = null;
      resultadoConsultaSQL_local = null;
    } 
  }
  public void actualizarCamposDependientesEnlazadoNoRecalculablesGrupoMultiple(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    int valorLlavePrimaria_local = -1;
    String nombreLlavePrimaria_local = null;
    String nombreLlavePrincipal_local = null;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;
    ListaCampo listaCampo_local = null;
    ListaCadenas listaNombresCamposGrupoInformacion_local = null;
    ManejadorResultadoConsultaSQL resultadoConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaNombresCamposGrupoInformacion_local = getAdministradorBaseDatosSisnet().obtenerListaNombresCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), false);
      
      listaCampo_local = getMotorAplicacion().obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacion(pGrupoInformacion);
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
      nombreLlavePrincipal_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
      consultaSQL_local = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionGrupoInformacionAplicacion(pGrupoInformacion, listaNombresCamposGrupoInformacion_local, pValorLlavePrimaria);
      
      resultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      resultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = resultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQL_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          valorLlavePrimaria_local = Integer.parseInt(resultSet_local.getObject(nombreLlavePrimaria_local).toString());
          actualizarCamposDependientesEnlazadoGrupoInformacion(listaCampo_local, nombreLlavePrincipal_local, pValorLlavePrimaria, valorLlavePrimaria_local);
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      resultSet_local = null;
      listaCampo_local = null;
      consultaSQL_local = null;
      nombreLlavePrimaria_local = null;
      nombreLlavePrincipal_local = null;
      listaNombresCamposGrupoInformacion_local = null;
      resultadoConsultaSQL_local = null;
    } 
  }
  public String obtenerValorEnlace(Aplicacion pAplicacionEnlace, int pValorEnlace) {
    String valorEnlace_local = "";
    Campo campoValorUnico_local = null;
    Object valorEnlaceObtenido_local = null;
    
    if (pAplicacionEnlace == ConstantesGeneral.VALOR_NULO) {
      return valorEnlace_local;
    }
    if (pValorEnlace == -1) {
      return valorEnlace_local;
    }
    if (pValorEnlace == -2) {
      valorEnlace_local = "DESCONOCIDO";
      return valorEnlace_local;
    } 
    
    try {
      campoValorUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(pAplicacionEnlace.getIdAplicacion());
      valorEnlaceObtenido_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoValorUnico_local, ap.conformarNombreCampoLlavePrimaria(pAplicacionEnlace.getNombreAplicacion()), pValorEnlace);
      
      if (valorEnlaceObtenido_local != ConstantesGeneral.VALOR_NULO) {
        valorEnlace_local = valorEnlaceObtenido_local.toString();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      campoValorUnico_local = null;
      valorEnlaceObtenido_local = null;
    } 
    
    return valorEnlace_local;
  }
  public String obtenerComparadorBasicoEquivalenteSQLFiltradoCampoEnlazado(int pComparador) {
    String comparadorSQL_local = "";
    
    try { switch (pComparador)
      { case 2:
          comparadorSQL_local = " = ";
          
          return comparadorSQL_local;case 3: comparadorSQL_local = " <> "; return comparadorSQL_local;case 4: comparadorSQL_local = " < "; return comparadorSQL_local;case 5: comparadorSQL_local = " > "; return comparadorSQL_local;case 6: comparadorSQL_local = " <= "; return comparadorSQL_local;case 7: comparadorSQL_local = " >= "; return comparadorSQL_local;case 8: case 9: case 11: case 12: case 14: comparadorSQL_local = " like "; return comparadorSQL_local;case 10: case 13: comparadorSQL_local = " not like "; return comparadorSQL_local; }  comparadorSQL_local = ""; } catch (Exception excepcion) { excepcion.printStackTrace(); }  return comparadorSQL_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\informacionRecalculable\ManejadorCampoEnlazado.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */