package com.sisnet.aplicacion.manejadores.informacionRecalculable;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.ManejadorVariablesSistema;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoCalculado;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoEnlazado;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import java.sql.ResultSet;
import java.util.Iterator;
public class ManejadorInformacionRecalculable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private ManejadorCampoEnlazado aManejadorCampoEnlazado;
  private ManejadorCampoCalculado aManejadorCampoCalculado;
  private MotorAplicacion aMotorAplicacion;
  private ManejadorVariablesSistema aManejadorVariablesSistema;
  public ManejadorInformacionRecalculable() {
    setManejadorCampoEnlazado(new ManejadorCampoEnlazado());
    setManejadorCampoCalculado(new ManejadorCampoCalculado());
    setManejadorVariablesSistema(new ManejadorVariablesSistema());
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
    getManejadorCampoEnlazado().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
    getManejadorCampoCalculado().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
    getManejadorCampoEnlazado().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
    getManejadorCampoCalculado().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
    getManejadorVariablesSistema().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
  }
  public ManejadorCampoEnlazado getManejadorCampoEnlazado() {
    return this.aManejadorCampoEnlazado;
  }
  public void setManejadorCampoEnlazado(ManejadorCampoEnlazado pManejadorCampoEnlazado) {
    this.aManejadorCampoEnlazado = pManejadorCampoEnlazado;
  }
  public ManejadorCampoCalculado getManejadorCampoCalculado() {
    return this.aManejadorCampoCalculado;
  }
  public void setManejadorCampoCalculado(ManejadorCampoCalculado pManejadorCampoCalculado) {
    this.aManejadorCampoCalculado = pManejadorCampoCalculado;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
    getManejadorCampoEnlazado().setMotorAplicacion(pMotorAplicacion);
    getManejadorCampoCalculado().setMotorAplicacion(pMotorAplicacion);
    getManejadorVariablesSistema().setMotorAplicacion(pMotorAplicacion);
  }
  public ManejadorVariablesSistema getManejadorVariablesSistema() {
    return this.aManejadorVariablesSistema;
  }
  public void setManejadorVariablesSistema(ManejadorVariablesSistema pManejadorVariablesSistema) {
    this.aManejadorVariablesSistema = pManejadorVariablesSistema;
  }
  private void actualizarInformacionRecalculableEnlazadaGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    boolean camposDependientesRecalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      camposDependientesRecalculables_local = getMotorAplicacion().verificarGrupoContieneCamposDependientesEnlazadoCalculables(pGrupoInformacion);
      
      if (camposDependientesRecalculables_local) {
        if (pGrupoInformacion.esGrupoInformacionMultiple()) {
          getManejadorCampoEnlazado().actualizarCamposDependientesEnlazadoRecalculablesGrupoMultiple(pGrupoInformacion, pValorLlavePrimaria);
        } else {
          
          getManejadorCampoEnlazado().actualizarCamposDependientesEnlazadosRecalculablesGrupoInformacionNoMultiple(pGrupoInformacion, pValorLlavePrimaria);
        }
      
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void actualizarInformacionRecalculableCalculadaGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    boolean camposCalculadosRecalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      camposCalculadosRecalculables_local = getMotorAplicacion().verificarGrupoContieneCamposCalculadosRecalculables(pGrupoInformacion);
      if (camposCalculadosRecalculables_local) {
        if (pGrupoInformacion.esGrupoInformacionMultiple()) {
          getManejadorCampoCalculado().actualizarValoresCamposCalculadosRecalculablesGrupoMultiple(pGrupoInformacion, pValorLlavePrimaria);
        } else {
          
          getManejadorCampoCalculado().actualizarValoresCamposCalculadosRecalculablesGrupoNoMultiple(pGrupoInformacion, pValorLlavePrimaria);
        }
      
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarInformacionRecalculableGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      actualizarInformacionRecalculableEnlazadaGrupoInformacion(pGrupoInformacion, pValorLlavePrimaria);
      actualizarInformacionRecalculableCalculadaGrupoInformacion(pGrupoInformacion, pValorLlavePrimaria);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void actualizarInformacionNoRecalculableEnlazadaGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    boolean camposDependientesNoRecalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      camposDependientesNoRecalculables_local = getMotorAplicacion().verificarGrupoContieneCamposDependientesEnlazadoNoCalculables(pGrupoInformacion);
      
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        if (camposDependientesNoRecalculables_local) {
          getManejadorCampoEnlazado().actualizarCamposDependientesEnlazadoNoRecalculablesGrupoMultiple(pGrupoInformacion, pValorLlavePrimaria);
        
        }
      }
      else if (camposDependientesNoRecalculables_local) {
        getManejadorCampoEnlazado().actualizarCamposDependientesEnlazadosNoRecalculablesGrupoInformacionNoMultiple(pGrupoInformacion, pValorLlavePrimaria);
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void actualizarInformacionNoRecalculableCalculadaGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    boolean camposCalculadosNoRecalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      camposCalculadosNoRecalculables_local = getMotorAplicacion().verificarGrupoContieneCamposCalculadosNoRecalculables(pGrupoInformacion);
      
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        if (camposCalculadosNoRecalculables_local) {
          getManejadorCampoCalculado().actualizarValoresCamposCalculadosNoRecalculablesGrupoMultiple(pGrupoInformacion, pValorLlavePrimaria);
        
        }
      }
      else if (camposCalculadosNoRecalculables_local) {
        getManejadorCampoCalculado().actualizarValoresCamposCalculadosNoRecalculablesGrupoNoMultiple(pGrupoInformacion, pValorLlavePrimaria);
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarInformacionNoRecalculableGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      actualizarInformacionNoRecalculableEnlazadaGrupoInformacion(pGrupoInformacion, pValorLlavePrimaria);
      actualizarInformacionNoRecalculableCalculadaGrupoInformacion(pGrupoInformacion, pValorLlavePrimaria);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void actualizarInformacionRecalculableAplicacion(Aplicacion pAplicacion, int pIdAplicacionOrigen, String pCondiciones) {
    int valorLlavePrimaria_local = -1;
    String consulta_local = null;
    String nombreLlavePrincipal_local = null;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    ManejadorResultadoConsultaSQL resultadoConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    boolean revisionGruposNoMultiples_local = false;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      nombreLlavePrincipal_local = ap.conformarNombreCampoLlavePrimaria(pAplicacion.getNombreAplicacion());
      listaGrupoInformacion_local = getAdministradorBaseDatosSisnet().obtenerListaGruposInformacionEnlazadosAplicacion(pAplicacion.getIdAplicacion(), pIdAplicacionOrigen);
      
      consulta_local = ca.consultaSQLPrincipalCondicionada(pAplicacion, pCondiciones);
      
      resultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      resultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = resultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consulta_local);
      while (resultSet_local.next()) {
        valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrincipal_local);
        if (listaGrupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
          iterador_local = listaGrupoInformacion_local.iterator();
          while (iterador_local.hasNext()) {
            grupoInformacion_local = (GrupoInformacion)iterador_local.next();
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              actualizarInformacionRecalculableEnlazadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
              actualizarInformacionRecalculableCalculadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local); continue;
            } 
            if (!revisionGruposNoMultiples_local) {
              actualizarInformacionRecalculableEnlazadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
              actualizarInformacionRecalculableCalculadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
            } 
            revisionGruposNoMultiples_local = true;
          }
        
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      iterador_local = null;
      resultSet_local = null;
      grupoInformacion_local = null;
      resultadoConsultaSQL_local = null;
      nombreLlavePrincipal_local = null;
      listaGrupoInformacion_local = null;
    } 
  }
  public void actualizarInformacionNoRecalculableAplicacion(Aplicacion pAplicacion, int pIdAplicacionOrigen, String pCondiciones) {
    int valorLlavePrimaria_local = -1;
    String consulta_local = null;
    String nombreLlavePrincipal_local = null;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    ManejadorResultadoConsultaSQL resultadoConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    boolean revisionGruposNoMultiples_local = false;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      nombreLlavePrincipal_local = ap.conformarNombreCampoLlavePrimaria(pAplicacion.getNombreAplicacion());
      listaGrupoInformacion_local = getAdministradorBaseDatosSisnet().obtenerListaGruposInformacionEnlazadosAplicacion(pAplicacion.getIdAplicacion(), pIdAplicacionOrigen);
      
      consulta_local = ca.consultaSQLPrincipalCondicionada(pAplicacion, pCondiciones);
      
      resultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      resultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      resultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = resultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consulta_local);
      while (resultSet_local.next()) {
        valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrincipal_local);
        if (listaGrupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
          iterador_local = listaGrupoInformacion_local.iterator();
          while (iterador_local.hasNext()) {
            grupoInformacion_local = (GrupoInformacion)iterador_local.next();
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              actualizarInformacionNoRecalculableEnlazadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
              actualizarInformacionNoRecalculableCalculadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local); continue;
            } 
            if (!revisionGruposNoMultiples_local) {
              actualizarInformacionNoRecalculableEnlazadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
              actualizarInformacionNoRecalculableCalculadaGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
            } 
            revisionGruposNoMultiples_local = true;
          }
        
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      iterador_local = null;
      resultSet_local = null;
      grupoInformacion_local = null;
      resultadoConsultaSQL_local = null;
      nombreLlavePrincipal_local = null;
      listaGrupoInformacion_local = null;
    } 
  }
  private void obtenerValoresNumeracionSemiautomatica(Campo pCampo, int pValorLlavePrimariaAnterior) {
    String nombreLlavePrimaria_local = null;
    int valorLlavePrimaria_local = -1;
    boolean esGrupoInformacionMultiple_local = false;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      grupoInformacion_local = pCampo.getGrupoInformacion();
      esGrupoInformacionMultiple_local = grupoInformacion_local.esGrupoInformacionMultiple();
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(grupoInformacion_local.getAplicacion().getNombreAplicacion());
      if (esGrupoInformacionMultiple_local) {
        valorLlavePrimaria_local = pValorLlavePrimariaAnterior;
      }
      if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "LL"))
      {
        pCampo.setValorCampo(Integer.valueOf(getAdministradorBaseDatosAplicacion().obtenerConsecutivoNumerico(pCampo, nombreLlavePrimaria_local, valorLlavePrimaria_local)));
      }
      
      if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "MM"))
      {
        pCampo.setValorCampo(getAdministradorBaseDatosAplicacion().obtenerConsecutivoAlfanumerico(pCampo, nombreLlavePrimaria_local, valorLlavePrimaria_local));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      nombreLlavePrimaria_local = null;
      grupoInformacion_local = null;
    } 
  }
  public void asignarValorSemiautomaticoCampo(Campo pCampo, boolean pEsRecargarPagina, int pValorLlavePrimaria) {
    String tipoDato_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
      if (mc.sonCadenasIguales(tipoDato_local, "LL") && (
        pCampo.getValorCampo() == ConstantesGeneral.VALOR_NULO || Integer.parseInt(pCampo.getValorCampo().toString()) == 0) && !pEsRecargarPagina)
      {
        obtenerValoresNumeracionSemiautomatica(pCampo, pValorLlavePrimaria);
      }
      
      if (mc.sonCadenasIguales(tipoDato_local, "MM") && (
        pCampo.getValorCampo() == ConstantesGeneral.VALOR_NULO || mc.esCadenaVacia(pCampo.getValorCampo().toString())) && !pEsRecargarPagina)
      {
        obtenerValoresNumeracionSemiautomatica(pCampo, pValorLlavePrimaria);
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
  }
  public void asignarValoresSemiautomaticosListaCampo(ListaCampo pListaCampo, int pValorLlavePrimariaAnterior) {
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "LL"))
        {
          if (campo_local.getTipoHabilitacion() == 3 || campo_local.getFormatoCampo().esValorUnico()) {
            
            campo_local.setValorCampo(Integer.valueOf(0));
            asignarValorSemiautomaticoCampo(campo_local, false, pValorLlavePrimariaAnterior);
          } 
        }
        
        if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "MM"))
        {
          if (campo_local.getTipoHabilitacion() == 3 || campo_local.getFormatoCampo().esValorUnico()) {
            
            campo_local.setValorCampo("");
            asignarValorSemiautomaticoCampo(campo_local, false, pValorLlavePrimariaAnterior);
          } 
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
  }
  public void asignarValoresConsultaRegistroCampos(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, int pValorLlavePrimaria) {
    String consultaSQL_local = null;
    String nombreCampo_local = null;
    String valorCampo_local = null;
    ResultSet resultSet_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      consultaSQL_local = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionRegistroGrupoInformacion(pGrupoInformacion, pListaCampo, pValorLlavePrimaria);
      
      manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      
      resultSet_local = manejadorResultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQL_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          if (pListaCampo != ConstantesGeneral.VALOR_NULO) {
            iterator_local = pListaCampo.iterator();
            while (iterator_local.hasNext()) {
              campo_local = (Campo)iterator_local.next();
              nombreCampo_local = campo_local.getNombreCampo();
              if (!mc.sonCadenasIgualesIgnorarMayusculas(nombreCampo_local, "FLDPLANTILLAUTILIZAR")) {
                valorCampo_local = "";
                if (resultSet_local.getObject(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
                  valorCampo_local = resultSet_local.getObject(nombreCampo_local).toString();
                }
                if (campo_local.esTipoDatoHora()) {
                  valorCampo_local = mc.recortarCadenaHora(valorCampo_local);
                }
                campo_local.setValorCampo(mc.borrarEspaciosLaterales(valorCampo_local));
              } 
            } 
          } 
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      consultaSQL_local = null;
      nombreCampo_local = null;
      valorCampo_local = null;
      resultSet_local = null;
      iterator_local = null;
      campo_local = null;
      manejadorResultadoConsultaSQL_local = null;
    } 
  }
  public void asignarValoresConsultaRegistroCampos(ListaCampo pListaCampo, ResultSet pResultSet) {
    String nombreCampo_local = null;
    String valorCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pResultSet == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        nombreCampo_local = campo_local.getNombreCampo();
        valorCampo_local = "";
        if (pResultSet.getObject(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
          valorCampo_local = pResultSet.getObject(nombreCampo_local).toString();
        }
        campo_local.setValorCampo(mc.borrarEspaciosLaterales(valorCampo_local));
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      nombreCampo_local = null;
      valorCampo_local = null;
      iterator_local = null;
      campo_local = null;
    } 
  }
  public void asignarValorConsecutivoInterno(Campo pCampo, int pValorLlavePrimaria) {
    String tipoDato_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
      if (mc.sonCadenasIguales(tipoDato_local, "W")) {
        pCampo.setValorCampo(Integer.valueOf(getAdministradorBaseDatosAplicacion().obtenerConsecutivoInterno(pCampo, pValorLlavePrimaria)));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      tipoDato_local = null;
    } 
  }
  public void asignarValoresConsecutivosInternosListaCampo(ListaCampo pListaCampo, int pValorLlavePrimariaAnterior) {
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "W") && 
          campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
          asignarValorConsecutivoInterno(campo_local, pValorLlavePrimariaAnterior);
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
  }
  public void reorganizarValoresConsecutivoInternoRegistro(ListaCampo pListaCampo, int pValorLlavePrimariaPrincipal) {
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "W")) {
          getAdministradorBaseDatosAplicacion().reorganizarValoresConsecutivoInternoCampo(campo_local, pValorLlavePrimariaPrincipal, getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(campo_local.getGrupoInformacion()));
        
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
  }
  private String conformarCondicionFiltradoSQLCampoEnlazado(Campo pCampo, int pComparador, String pValorCondicion) {
    String condicionSQL_local = "";
    String comparador_local = null;
    String valor_local = null;
    String nombreCompuestoCampo_local = null;
    boolean esTipoDatoTexto_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return condicionSQL_local;
    }
    if (pValorCondicion == ConstantesGeneral.VALOR_NULO) {
      return condicionSQL_local;
    }
    if (mc.esCadenaVacia(pValorCondicion)) {
      return condicionSQL_local;
    }
    
    try {
      esTipoDatoTexto_local = pCampo.esTipoDatoTexto();
      nombreCompuestoCampo_local = pCampo.conformarNombreCompuestoCampo();
      comparador_local = getManejadorCampoEnlazado().obtenerComparadorBasicoEquivalenteSQLFiltradoCampoEnlazado(pComparador);
      if (!mc.esCadenaVacia(comparador_local)) {
        switch (pComparador) {
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          case 7:
            valor_local = pValorCondicion;
            if (esTipoDatoTexto_local) {
              valor_local = mc.colocarEntreComillas(valor_local);
            }
            break;
          case 11:
          case 13:
            valor_local = mc.concatenarCadena("%", pValorCondicion);
            valor_local = mc.concatenarCadena(valor_local, "%");
            valor_local = mc.colocarEntreComillas(valor_local);
            break;
          case 12:
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
            valor_local = mc.concatenarCadena(valor_local, mc.colocarEntreComillas(pValorCondicion));
            break;
          case 8:
          case 10:
            valor_local = mc.concatenarCadena(pValorCondicion, "%");
            valor_local = mc.colocarEntreComillas(valor_local);
            break;
          case 9:
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
            valor_local = mc.concatenarCadena(valor_local, mc.colocarEntreComillas(pValorCondicion));
            break;
          case 14:
            condicionSQL_local = ap.conformarCadenasFiltroPorPalabras(pValorCondicion, nombreCompuestoCampo_local, " like ");
            break;
          default:
            valor_local = "";
            break;
        } 
        if (mc.esCadenaVacia(pValorCondicion) && (
          mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "F") || mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "H"))) {
          
          if (mc.sonCadenasIguales(comparador_local, " = ")) {
            comparador_local = " isnull ";
          }
          if (mc.sonCadenasIguales(comparador_local, " <> ")) {
            comparador_local = " is not null ";
          }
          valor_local = "";
        } 
        
        if (mc.esCadenaVacia(condicionSQL_local)) {
          if (esTipoDatoTexto_local && !mc.esCadenaVacia(pValorCondicion)) {
            condicionSQL_local = mc.concatenarCadena(" upper(", nombreCompuestoCampo_local);
            condicionSQL_local = mc.concatenarCadena(condicionSQL_local, String.valueOf(')'));
            condicionSQL_local = mc.concatenarCadena(condicionSQL_local, comparador_local);
          } else {
            condicionSQL_local = mc.concatenarCadena(nombreCompuestoCampo_local, comparador_local);
          } 
          condicionSQL_local = mc.concatenarCadena(condicionSQL_local, valor_local);
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
  public String conformarCondicionesFiltradoCampoEnlazado(Campo pCampoEnlazado, String pValorFiltradoPorCampoOrigen, Usuario pUsuarioActual, int pValorEnlaceSeleccionado, boolean pEsInclusion, String pIdSesion) {
    String condicionesCampoEnlazado_local = "";
    String valorFiltradoPorConfiguracion_local = null;
    String valorFiltradoPorCampoOrigen_local = null;
    String condicionFiltradoParaValorConfiguracion_local = null;
    String condicionFiltradoParaValorCampoOrigen_local = null;
    String condicionValorSeleccionado_local = null;
    Campo campoDestinoFiltrado_local = null;
    Campo campoLlavePrincipalDestinoFiltrado_local = null;
    Campo campoOrigenFiltrado_local = null;
    
    if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO) {
      return condicionesCampoEnlazado_local;
    }
    if (pUsuarioActual == ConstantesGeneral.VALOR_NULO) {
      return condicionesCampoEnlazado_local;
    }
    if (pIdSesion == ConstantesGeneral.VALOR_NULO) {
      return condicionesCampoEnlazado_local;
    }
    
    try {
      if (pCampoEnlazado.getEnlaceCampo().getCampoDestinoFiltrado() != ConstantesGeneral.VALOR_NULO) {
        if (pCampoEnlazado.getEnlaceCampo().getCampoOrigenFiltrado() != ConstantesGeneral.VALOR_NULO) {
          campoOrigenFiltrado_local = pCampoEnlazado.getEnlaceCampo().getCampoOrigenFiltrado();
        }
        campoDestinoFiltrado_local = pCampoEnlazado.getEnlaceCampo().getCampoDestinoFiltrado();
        campoLlavePrincipalDestinoFiltrado_local = getMotorAplicacion().obtenerCampoLlavePrimariaGrupoInformacion(getMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(campoDestinoFiltrado_local.getGrupoInformacion().getAplicacion().getIdAplicacion()).getIdGrupoInformacion());
        
        valorFiltradoPorConfiguracion_local = pCampoEnlazado.getEnlaceCampo().getValorFiltrado();
        if (ap.esVariableSistema(pCampoEnlazado.getEnlaceCampo().getValorFiltrado())) {
          getManejadorVariablesSistema().setUsuarioActual(pUsuarioActual);
          getManejadorVariablesSistema().setIdSesion(pIdSesion);
          valorFiltradoPorConfiguracion_local = getManejadorVariablesSistema().obtenerValorVariableSistema(valorFiltradoPorConfiguracion_local).getValorVariable().toString();
        } 
        
        if (campoDestinoFiltrado_local.esTipoDatoTabla()) {
          valorFiltradoPorConfiguracion_local = String.valueOf(getManejadorCampoCalculado().obtenerIdValorTabla(campoDestinoFiltrado_local, valorFiltradoPorConfiguracion_local));
          
          if (mc.sonCadenasIguales(valorFiltradoPorConfiguracion_local, String.valueOf(0))) {
            valorFiltradoPorConfiguracion_local = "";
          }
        }
        else if (campoDestinoFiltrado_local.esCampoEnlazado()) {
          valorFiltradoPorConfiguracion_local = String.valueOf(getManejadorCampoEnlazado().obtenerIdValorCampoEnlazado(campoDestinoFiltrado_local.getEnlaceCampo().getEnlazado(), valorFiltradoPorConfiguracion_local));
          
          if (mc.sonCadenasIguales(valorFiltradoPorConfiguracion_local, String.valueOf('0'))) {
            valorFiltradoPorConfiguracion_local = "";
          }
        } 
        
        if (!mc.esCadenaVacia(pValorFiltradoPorCampoOrigen)) {
          valorFiltradoPorCampoOrigen_local = pValorFiltradoPorCampoOrigen;
          if (campoDestinoFiltrado_local.esTipoDatoTabla()) {
            if (!mc.esCadenaNumerica(valorFiltradoPorCampoOrigen_local, true)) {
              valorFiltradoPorCampoOrigen_local = String.valueOf(getManejadorCampoCalculado().obtenerIdValorTabla(campoDestinoFiltrado_local, valorFiltradoPorCampoOrigen_local));
            
            }
          
          }
          else if (campoDestinoFiltrado_local.esCampoEnlazado() && 
            !mc.esCadenaNumerica(valorFiltradoPorCampoOrigen_local, true)) {
            valorFiltradoPorCampoOrigen_local = String.valueOf(getManejadorCampoEnlazado().obtenerIdValorCampoEnlazado(campoDestinoFiltrado_local.getEnlaceCampo().getEnlazado(), valorFiltradoPorCampoOrigen_local));
          } 
        } 
        
        condicionFiltradoParaValorConfiguracion_local = valorFiltradoPorConfiguracion_local;
        if (!mc.esCadenaVacia(valorFiltradoPorConfiguracion_local)) {
          condicionFiltradoParaValorConfiguracion_local = conformarCondicionFiltradoSQLCampoEnlazado(campoDestinoFiltrado_local, pCampoEnlazado.getEnlaceCampo().getFiltradoRegistrosEnlazados(), valorFiltradoPorConfiguracion_local);
        }
        
        if (campoOrigenFiltrado_local != ConstantesGeneral.VALOR_NULO && 
          campoDestinoFiltrado_local.esTipoDatoTexto()) {
          if (campoOrigenFiltrado_local.esTipoDatoTabla() && mc.esCadenaNumerica(valorFiltradoPorCampoOrigen_local, true))
          {
            valorFiltradoPorCampoOrigen_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(campoOrigenFiltrado_local.getFormatoCampo().getTipoDato())).getNombreTabla(), Integer.parseInt(valorFiltradoPorCampoOrigen_local));
          }
          
          if (campoOrigenFiltrado_local.esCampoEnlazado() && mc.esCadenaNumerica(valorFiltradoPorCampoOrigen_local, true))
          {
            valorFiltradoPorCampoOrigen_local = getManejadorCampoEnlazado().obtenerValorCampoEnlazado(campoOrigenFiltrado_local.getEnlaceCampo().getEnlazado(), Integer.parseInt(valorFiltradoPorCampoOrigen_local));
          }
        } 
        
        condicionFiltradoParaValorCampoOrigen_local = conformarCondicionFiltradoSQLCampoEnlazado(campoDestinoFiltrado_local, pCampoEnlazado.getEnlaceCampo().getFiltradoRegistrosEnlazados(), valorFiltradoPorCampoOrigen_local);
        
        condicionesCampoEnlazado_local = condicionFiltradoParaValorConfiguracion_local;
        if (mc.esCadenaVacia(condicionesCampoEnlazado_local)) {
          condicionesCampoEnlazado_local = condicionFiltradoParaValorCampoOrigen_local;
        }
        
        if (!mc.esCadenaVacia(condicionesCampoEnlazado_local) && 
          pValorEnlaceSeleccionado != 0 && !pEsInclusion) {
          condicionValorSeleccionado_local = mc.concatenarCadena(campoLlavePrincipalDestinoFiltrado_local.conformarNombreCompuestoCampo(), " = " + pValorEnlaceSeleccionado);
          
          condicionValorSeleccionado_local = mc.colocarEntreParentesis(condicionValorSeleccionado_local);
          condicionesCampoEnlazado_local = mc.concatenarCadena(condicionesCampoEnlazado_local, " or ");
          
          condicionesCampoEnlazado_local = mc.concatenarCadena(condicionesCampoEnlazado_local, condicionValorSeleccionado_local);
        } 
        
        if (!mc.esCadenaVacia(condicionesCampoEnlazado_local)) {
          condicionesCampoEnlazado_local = mc.colocarEntreParentesis(condicionesCampoEnlazado_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoOrigenFiltrado_local = null;
      campoDestinoFiltrado_local = null;
      condicionValorSeleccionado_local = null;
      valorFiltradoPorCampoOrigen_local = null;
      valorFiltradoPorConfiguracion_local = null;
      campoLlavePrincipalDestinoFiltrado_local = null;
      condicionFiltradoParaValorCampoOrigen_local = null;
      condicionFiltradoParaValorConfiguracion_local = null;
    } 
    
    return condicionesCampoEnlazado_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\informacionRecalculable\ManejadorInformacionRecalculable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */