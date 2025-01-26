package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.sql.ResultSet;
public class ManejadorHabilitacionCampos
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  public ManejadorHabilitacionCampos() {
    setAdministradorBaseDatosSisnet(null);
    setAdministradorBaseDatosAplicacion(null);
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
  public int obtenerIdValorMaestroListaDependiente(ListaCampo pListaCampo, Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion, boolean pEsRevision) {
    int idValorMaestroListaDependiente_local = -1;
    Campo campoDeQueDepende_local = null;
    Object valorMaestro_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorMaestroListaDependiente_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorMaestroListaDependiente_local;
    }
    
    try {
      campoDeQueDepende_local = pCampo.getListaDependiente();
      if (campoDeQueDepende_local != ConstantesGeneral.VALOR_NULO) {
        if (pEsRevision) {
          if (campoDeQueDepende_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getNombreGrupoInformacion()), pValorLlavePrimariaGrupoInformacion);
          }
          else {
            
            valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
          } 
          
          if (valorMaestro_local != ConstantesGeneral.VALOR_NULO) {
            idValorMaestroListaDependiente_local = Integer.parseInt(valorMaestro_local.toString());
          }
        }
        else if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo, campoDeQueDepende_local)) {
          valorMaestro_local = pListaCampo.obtenerValorCampo(campoDeQueDepende_local.getNombreCampo());
          if (mc.esCadenaNumerica(valorMaestro_local.toString(), true)) {
            idValorMaestroListaDependiente_local = Integer.parseInt(valorMaestro_local.toString());
          }
        } else {
          valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
          
          if (valorMaestro_local != ConstantesGeneral.VALOR_NULO) {
            idValorMaestroListaDependiente_local = Integer.parseInt(valorMaestro_local.toString());
          }
        }
      
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoDeQueDepende_local = null;
      valorMaestro_local = null;
    } 
    
    return idValorMaestroListaDependiente_local;
  }
  private int obtenerUltimoIdHabilitaCampo(Campo pCampo, int pValorLlavePrimariaPrincipal) {
    int ultimoIdHabilitaCampo_local = -1;
    String consulta_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    int idValorMaestro_local = -1;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return ultimoIdHabilitaCampo_local;
    }
    
    try {
      String nombrePrimerCampoValorUnicoGrupoInformacion = this.aAdministradorBaseDatosSisnet.obtenerNombrePrimerCampoValorUnicoGrupoInformacion(
    		  pCampo.getHabilitadoPor().getGrupoInformacion());
      consulta_local = ca.consultaSQLValoresRegistro(pCampo.getHabilitadoPor(), pValorLlavePrimariaPrincipal, nombrePrimerCampoValorUnicoGrupoInformacion);
      
      manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      resultSet_local = manejadorResultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consulta_local);
      if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
        while (resultSet_local.next()) {
          if (Integer.valueOf(resultSet_local.getInt(pCampo.getHabilitadoPor().getNombreCampo())) != ConstantesGeneral.VALOR_NULO) {
            idValorMaestro_local = resultSet_local.getInt(pCampo.getHabilitadoPor().getNombreCampo());
            if (getAdministradorBaseDatosSisnet().verificarHabilitacionValorMaestro(pCampo.getIdCampo(), idValorMaestro_local)) {
              ultimoIdHabilitaCampo_local = idValorMaestro_local;
            }
          } 
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      manejadorResultadoConsultaSQL_local = null;
      resultSet_local = null;
    } 
    
    return ultimoIdHabilitaCampo_local;
  }
  public int obtenerIdValorMaestroCampoHabilitadoPor(ListaCampo pListaCampo, Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion, boolean pEsRevision) {
    int idValorMaestroListaDependiente_local = -1;
    Campo campoDeQueDepende_local = null;
    Object valorMaestro_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorMaestroListaDependiente_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorMaestroListaDependiente_local;
    }
    
    try {
      campoDeQueDepende_local = pCampo.getHabilitadoPor();
      if (campoDeQueDepende_local != ConstantesGeneral.VALOR_NULO) {
        if (pEsRevision) {
          if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo, campoDeQueDepende_local)) {
            if (campoDeQueDepende_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
              valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getNombreGrupoInformacion()), pValorLlavePrimariaGrupoInformacion);
            }
            else {
              
              valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
            
            }
          
          }
          else if (campoDeQueDepende_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            idValorMaestroListaDependiente_local = obtenerUltimoIdHabilitaCampo(pCampo, pValorLlavePrimariaPrincipal);
          } else {
            valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
          } 
          
          if (valorMaestro_local != ConstantesGeneral.VALOR_NULO) {
            idValorMaestroListaDependiente_local = Integer.parseInt(valorMaestro_local.toString());
          }
        }
        else if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo, campoDeQueDepende_local)) {
          valorMaestro_local = pListaCampo.obtenerValorCampo(campoDeQueDepende_local.getNombreCampo());
          if (mc.esCadenaNumerica(valorMaestro_local.toString(), true)) {
            idValorMaestroListaDependiente_local = Integer.parseInt(valorMaestro_local.toString());
          }
        } else {
          if (campoDeQueDepende_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            idValorMaestroListaDependiente_local = obtenerUltimoIdHabilitaCampo(pCampo, pValorLlavePrimariaPrincipal);
          } else {
            valorMaestro_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campoDeQueDepende_local, ap.conformarNombreCampoLlavePrimaria(campoDeQueDepende_local.getGrupoInformacion().getAplicacion().getNombreAplicacion()), pValorLlavePrimariaPrincipal);
          } 
          
          if (valorMaestro_local != ConstantesGeneral.VALOR_NULO) {
            idValorMaestroListaDependiente_local = Integer.parseInt(valorMaestro_local.toString());
          }
        }
      
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoDeQueDepende_local = null;
      valorMaestro_local = null;
    } 
    
    return idValorMaestroListaDependiente_local;
  }
  public int obtenerIdValorMaestro(ListaCampo pListaCampo, Campo pCampo, boolean pEsListaDependiente, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion, boolean pEsRevision) {
    int idValorMaestro_local = -1;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorMaestro_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return idValorMaestro_local;
    }
    
    try {
      if (pEsListaDependiente) {
        idValorMaestro_local = obtenerIdValorMaestroListaDependiente(pListaCampo, pCampo, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion, pEsRevision);
      } else {
        
        idValorMaestro_local = obtenerIdValorMaestroCampoHabilitadoPor(pListaCampo, pCampo, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion, pEsRevision);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return idValorMaestro_local;
  }
  public boolean verificarHabilitarCampo(ListaCampo pListaCampo, Campo pCampo, int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupoInformacion, boolean pEsRevision) {
    boolean habilitarCampo_local = false;
    int idValorMaestro_local = -1;
    boolean esHabilitadoPor_local = false;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return habilitarCampo_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return habilitarCampo_local;
    }
    
    try {
      habilitarCampo_local = true;
      esHabilitadoPor_local = (pCampo.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO);
      if (esHabilitadoPor_local) {
        idValorMaestro_local = obtenerIdValorMaestro(pListaCampo, pCampo, false, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupoInformacion, pEsRevision);
        
        habilitarCampo_local = getAdministradorBaseDatosSisnet().verificarHabilitacionValorMaestro(pCampo.getIdCampo(), idValorMaestro_local);
      } 
      habilitarCampo_local = ((pCampo.getTipoHabilitacion() == 1 || pCampo.getTipoHabilitacion() == 3) && habilitarCampo_local);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return habilitarCampo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorHabilitacionCampos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */