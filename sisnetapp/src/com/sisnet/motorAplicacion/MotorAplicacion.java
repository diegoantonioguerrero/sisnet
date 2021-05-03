package com.sisnet.motorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import java.util.Iterator;
public class MotorAplicacion
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private ListaAplicacion aListaAplicacion;
  private ListaGrupoInformacion aListaGrupoInformacion;
  private ListaCampo aListaCampo;
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  public MotorAplicacion() {
    setListaAplicacion(new ListaAplicacion());
    setListaGrupoInformacion(new ListaGrupoInformacion());
    setListaCampo(new ListaCampo());
  }
  public ListaAplicacion getListaAplicacion() {
    return this.aListaAplicacion;
  }
  public void setListaAplicacion(ListaAplicacion pListaAplicacion) {
    this.aListaAplicacion = pListaAplicacion;
  }
  public ListaGrupoInformacion getListaGrupoInformacion() {
    return this.aListaGrupoInformacion;
  }
  public void setListaGrupoInformacion(ListaGrupoInformacion pListaGrupoInformacion) {
    this.aListaGrupoInformacion = pListaGrupoInformacion;
  }
  public ListaCampo getListaCampo() {
    return this.aListaCampo;
  }
  public void setListaCampo(ListaCampo pListaCampo) {
    this.aListaCampo = pListaCampo;
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
  }
  private void cargarListaAplicaciones() {
    try {
      setListaAplicacion(getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(0, -1, true));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void cargarListaGruposInformacion() {
    Iterator iterador_local = null;
    
    try {
      iterador_local = getListaAplicacion().iterator();
      while (iterador_local.hasNext()) {
        getListaGrupoInformacion().concatenar(getAdministradorBaseDatosSisnet().obtenerListaGruposInformacionAplicacionMotor(((Aplicacion)iterador_local.next()).getIdAplicacion()));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
  }
  private void cargarListaCampos() {
    Iterator iterador_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      while (iterador_local.hasNext()) {
        getListaCampo().concatenar(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacionMotor(((GrupoInformacion)iterador_local.next()).getIdGrupoInformacion()));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
  }
  private void reconfigurarListaAplicacion() {
    Iterator iterador_local = null;
    Aplicacion aplicacion_local = null;
    
    try {
      iterador_local = getListaAplicacion().iterator();
      while (iterador_local.hasNext()) {
        aplicacion_local = (Aplicacion)iterador_local.next();
        aplicacion_local.setAplicacionConsulta(getListaAplicacion().obtenerAplicacionPorId(aplicacion_local.getIdAplicacionConsulta()));
        aplicacion_local.setAplicacionReporte(getListaAplicacion().obtenerAplicacionPorId(aplicacion_local.getIdAplicacionReporte()));
        aplicacion_local.setAplicacionImpresionMasiva(getListaAplicacion().obtenerAplicacionPorId(aplicacion_local.getIdAplicacionImpresionMasiva()));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      aplicacion_local = null;
    } 
  }
  private void reconfigurarListaGrupoInformacion() {
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        grupoInformacion_local.setAplicacion(getListaAplicacion().obtenerAplicacionPorId(grupoInformacion_local.getIdAplicacion()));
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void asignarIncluirOpcionConsultaGrupoInformacion(int pIdGrupoInformacion) {
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getIdGrupoInformacion() == pIdGrupoInformacion) {
          grupoInformacion_local.setIncluirOpcionConsulta(true);
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void asignarVisibleUsuarioPrincipalGrupoInformacion(int pIdGrupoInformacion) {
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getIdGrupoInformacion() == pIdGrupoInformacion) {
          grupoInformacion_local.setVisibleUsuarioPrincipal(true);
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void reconfigurarListaCampo() {
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        campo_local.setGrupoInformacion(getListaGrupoInformacion().obtenerGrupoInformacionPorId(campo_local.getIdGrupoInformacion()));
        
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO) {
          if (campo_local.esIncluirOpcionConsulta()) {
            asignarIncluirOpcionConsultaGrupoInformacion(campo_local.getGrupoInformacion().getIdGrupoInformacion());
          }
          if (campo_local.esVisibleUsuarioPrincipal()) {
            asignarVisibleUsuarioPrincipalGrupoInformacion(campo_local.getGrupoInformacion().getIdGrupoInformacion());
          }
          campo_local.setHabilitadoPor(getListaCampo().obtenerCampoPorId(campo_local.getIdHabilitadoPor()));
          campo_local.setListaDependiente(getListaCampo().obtenerCampoPorId(campo_local.getIdListaDependiente()));
          campo_local.getCalculoCampo().setCampoValor(getListaCampo().obtenerCampoPorId(campo_local.getCalculoCampo().getIdCampoValor()));
          
          campo_local.getCalculoCampo().setCampoOrigenUno(getListaCampo().obtenerCampoPorId(campo_local.getCalculoCampo().getIdCampoOrigenUno()));
          
          campo_local.getCalculoCampo().setCampoOrigenDos(getListaCampo().obtenerCampoPorId(campo_local.getCalculoCampo().getIdCampoOrigenDos()));
          
          campo_local.getEnlaceCampo().setEnlazado(getListaAplicacion().obtenerAplicacionPorId(campo_local.getEnlaceCampo().getIdEnlazado()));
          
          campo_local.getEnlaceCampo().setCampoOrigenFiltrado(getListaCampo().obtenerCampoPorId(campo_local.getEnlaceCampo().getIdCampoOrigenFiltrado()));
          
          campo_local.getEnlaceCampo().setCampoDestinoFiltrado(getListaCampo().obtenerCampoPorId(campo_local.getEnlaceCampo().getIdCampoDestinoFiltrado()));
          
          campo_local.getEnlaceCampo().setCampoEnlaceDepende(getListaCampo().obtenerCampoPorId(campo_local.getEnlaceCampo().getIdCampoEnlaceDepende()));
          
          campo_local.getEnlaceCampo().setCampoOrigenEnlace(getListaCampo().obtenerCampoPorId(campo_local.getEnlaceCampo().getIdCampoOrigenEnlace()));
          
          campo_local.getPlantillaCampo().setAplicacionPlantilla(getListaAplicacion().obtenerAplicacionPorId(campo_local.getPlantillaCampo().getIdAplicacionPlantilla()));
          continue;
        } 
        iterador_local.remove();
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
  }
  private void reconfigurar() {
    try {
      reconfigurarListaAplicacion();
      reconfigurarListaGrupoInformacion();
      reconfigurarListaCampo();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void borrarCamposAplicacion(int pIdAplicacion) {
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    try {
      iterator_local = getListaCampo().iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion)
        {
          iterator_local.remove();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
  }
  private void recargarCamposAplicacion(int pIdAplicacion) {
    Iterator iterator_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterator_local = getListaGrupoInformacion().iterator();
      while (iterator_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterator_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion) {
          getListaCampo().concatenar(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacionMotor(grupoInformacion_local.getIdGrupoInformacion()));
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      grupoInformacion_local = null;
    } 
  }
  public void cargarMotorAplicacion(int pIdAplicacionActual) {
    try {
      System.out.println("Inicio carga Motor " + ap.obtenerHoraActualSistemaFormatoLargo());
      if (getListaCampo().contarElementos() == 0) {
        cargarListaAplicaciones();
        cargarListaGruposInformacion();
        cargarListaCampos();
      } else {
        borrarCamposAplicacion(pIdAplicacionActual);
        recargarCamposAplicacion(pIdAplicacionActual);
      } 
      reconfigurar();
      System.out.println("Fin carga Motor " + ap.obtenerHoraActualSistemaFormatoLargo());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void recargarAplicaciones() {
    try {
      getListaAplicacion().borrarElementos();
      cargarListaAplicaciones();
      getListaGrupoInformacion().borrarElementos();
      cargarListaGruposInformacion();
      reconfigurar();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void borrarGruposInformacionAplicacion(int pIdAplicacion) {
    Iterator iterator_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterator_local = getListaGrupoInformacion().iterator();
      while (iterator_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterator_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion) {
          iterator_local.remove();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      grupoInformacion_local = null;
    } 
  }
  public void recargarGruposInformacionAplicacion(int pIdAplicacion) {
    try {
      borrarGruposInformacionAplicacion(pIdAplicacion);
      getListaGrupoInformacion().concatenar(getAdministradorBaseDatosSisnet().obtenerListaGruposInformacionAplicacionMotor(pIdAplicacion));
      reconfigurar();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public ListaAplicacion obtenerListaAplicaciones(int pIdAplicacion, boolean pSoloVisibles) {
    ListaAplicacion listaAplicacion_local = null;
    Iterator iterador_local = null;
    Aplicacion aplicacion_local = null;
    
    try {
      iterador_local = getListaAplicacion().iterator();
      listaAplicacion_local = new ListaAplicacion();
      while (iterador_local.hasNext()) {
        aplicacion_local = (Aplicacion)iterador_local.next();
        if (!aplicacion_local.esTabla() && aplicacion_local.getIdAplicacion() != pIdAplicacion) {
          if (pSoloVisibles) {
            if (!aplicacion_local.esOculta())
              listaAplicacion_local.adicionar(aplicacion_local); 
            continue;
          } 
          listaAplicacion_local.adicionar(aplicacion_local);
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      aplicacion_local = null;
    } 
    
    return listaAplicacion_local;
  }
  public ListaAplicacion obtenerListaAplicacionesTabla(int pIdAplicacion, boolean pSoloVisibles) {
    ListaAplicacion listaAplicacion_local = null;
    Iterator iterador_local = null;
    Aplicacion aplicacion_local = null;
    
    try {
      iterador_local = getListaAplicacion().iterator();
      listaAplicacion_local = new ListaAplicacion();
      while (iterador_local.hasNext()) {
        aplicacion_local = (Aplicacion)iterador_local.next();
        if (aplicacion_local.esTabla() && aplicacion_local.getIdAplicacion() != pIdAplicacion) {
          if (pSoloVisibles) {
            if (!aplicacion_local.esOculta())
              listaAplicacion_local.adicionar(aplicacion_local); 
            continue;
          } 
          listaAplicacion_local.adicionar(aplicacion_local);
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      aplicacion_local = null;
    } 
    
    return listaAplicacion_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionOpcionConsulta(int pIdAplicacion) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion && grupoInformacion_local.esIncluirOpcionConsulta() && !grupoInformacion_local.esGrupoInformacionPrincipal())
        {
          listaGrupoInformacion_local.adicionar(grupoInformacion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
    
    return listaGrupoInformacion_local;
  }
  private boolean verificarCampoNoRestringidoDocumentoArchivoGrupoInformacionConEtiqueta(Campo pCampo, int pIdGrupoInformacion) {
    boolean campoNoRestringido_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNoRestringido_local;
    }
    
    try {
      campoNoRestringido_local = (pCampo.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && pCampo.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion && !mc.esCadenaVacia(pCampo.getEtiquetaCampo()) && !pCampo.esTipoDatoDocumento() && !pCampo.esTipoDatoArchivo());
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoNoRestringido_local;
  }
  private boolean verificarCampoNoRestringidoDocumentoGrupoInformacionConEtiqueta(Campo pCampo, int pIdGrupoInformacion) {
    boolean campoNoRestringido_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNoRestringido_local;
    }
    
    try {
      campoNoRestringido_local = (pCampo.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && pCampo.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion && !mc.esCadenaVacia(pCampo.getEtiquetaCampo()) && !pCampo.esTipoDatoDocumento());
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoNoRestringido_local;
  }
  private boolean verificarCampoNoRestringidoDocumentoArchivoGrupoInformacionSinEtiqueta(Campo pCampo, int pIdGrupoInformacion) {
    boolean campoNoRestringido_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNoRestringido_local;
    }
    
    try {
      campoNoRestringido_local = (pCampo.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && pCampo.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion && !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "DD") && !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "J"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoNoRestringido_local;
  }
  private boolean verificarCampoNoRestringidoDocumentoGrupoInformacionSinEtiqueta(Campo pCampo, int pIdGrupoInformacion) {
    boolean campoNoRestringido_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNoRestringido_local;
    }
    
    try {
      campoNoRestringido_local = (pCampo.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && pCampo.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion && !pCampo.esTipoDatoDocumento());
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoNoRestringido_local;
  }
  public ListaCampo obtenerListaCamposOpcionConsulta(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoArchivoGrupoInformacionConEtiqueta(campo_local, pIdGrupoInformacion) && campo_local.esIncluirOpcionConsulta()) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionAplicacionConsultaPrincipal(int pIdAplicacion) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion && grupoInformacion_local.esVisibleUsuarioPrincipal()) {
          listaGrupoInformacion_local.adicionar(grupoInformacion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
    
    return listaGrupoInformacion_local;
  }
  public ListaCampo obtenerListaCamposVisiblesGrupoInformacionPorAncho(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    boolean esCampoVisible_local = false;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        esCampoVisible_local = (verificarCampoNoRestringidoDocumentoGrupoInformacionConEtiqueta(campo_local, pIdGrupoInformacion) && campo_local.esVisibleUsuarioPrincipal());
        
        if (esCampoVisible_local) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposVisiblesGrupoInformacionMultiplePorAncho(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoArchivoGrupoInformacionConEtiqueta(campo_local, pIdGrupoInformacion) && campo_local.esVisibleUsuarioSecundario()) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposVisiblesGrupoInformacion(int pIdGrupoInformacion, boolean pVerificarVisibilidad) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    boolean esCampoVisible_local = false;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        esCampoVisible_local = verificarCampoNoRestringidoDocumentoGrupoInformacionConEtiqueta(campo_local, pIdGrupoInformacion);
        
        if (esCampoVisible_local) {
          if (pVerificarVisibilidad) {
            if (campo_local.esVisibleUsuarioPrincipal())
              listaCampo_local.adicionar(campo_local); 
            continue;
          } 
          listaCampo_local.adicionar(campo_local);
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  private boolean verificarCampoGrupoInformacion(Campo pCampo, int pIdGrupoInformacion) {
    boolean campoNoRestringido_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNoRestringido_local;
    }
    
    try {
      campoNoRestringido_local = (pCampo.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion && !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "DD") && !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "J"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoNoRestringido_local;
  }
  private boolean verificarCampoGrupoInformacionConArchivos(Campo pCampo, int pIdGrupoInformacion) {
    boolean campoNoRestringido_local = false;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return campoNoRestringido_local;
    }
    
    try {
      campoNoRestringido_local = (pCampo.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion && !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "DD"));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campoNoRestringido_local;
  }
  public ListaCampo obtenerListaCamposGruposInformacionNoMultiples(int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion)
        {
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && verificarCampoGrupoInformacion(campo_local, campo_local.getGrupoInformacion().getIdGrupoInformacion()))
          {
            listaCampo_local.adicionar(campo_local);
          }
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposGruposInformacionNoMultiplesConArchivos(int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion)
        {
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && verificarCampoGrupoInformacionConArchivos(campo_local, campo_local.getGrupoInformacion().getIdGrupoInformacion()))
          {
            
            listaCampo_local.adicionar(campo_local);
          }
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposTipoArchivoGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (pGrupoInformacion.esGrupoInformacionMultiple()) {
          if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "J"))
          {
            
            listaCampo_local.adicionar(campo_local); } 
          continue;
        } 
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion())
        {
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "J"))
          {
            listaCampo_local.adicionar(campo_local);
          }
        }
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposCalculadosGrupoInformacionNoMultiple(Campo pCampo) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pCampo.getGrupoInformacion().getAplicacion().getIdAplicacion())
        {
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getCalculoCampo().getCampoCalculado() != 1)
          {
            if (campo_local.getCalculoCampo().getCampoValor() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoValor().getIdCampo() == pCampo.getIdCampo()) {
              
              listaCampo_local.adicionar(campo_local); continue;
            } 
            if (campo_local.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoOrigenUno().getIdCampo() == pCampo.getIdCampo()) {
              
              listaCampo_local.adicionar(campo_local); continue;
            } 
            if (campo_local.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoOrigenDos().getIdCampo() == pCampo.getIdCampo())
            {
              listaCampo_local.adicionar(campo_local);
            }
          }
        
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposCalculadosGrupoInformacionMultiple(Campo pCampo) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pCampo.getGrupoInformacion().getIdGrupoInformacion())
        {
          if (campo_local.getCalculoCampo().getCampoCalculado() != 1) {
            if (campo_local.getCalculoCampo().getCampoValor() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoValor().getIdCampo() == pCampo.getIdCampo()) {
              
              listaCampo_local.adicionar(campo_local); continue;
            } 
            if (campo_local.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoOrigenUno().getIdCampo() == pCampo.getIdCampo()) {
              
              listaCampo_local.adicionar(campo_local); continue;
            } 
            if (campo_local.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoOrigenDos().getIdCampo() == pCampo.getIdCampo())
            {
              listaCampo_local.adicionar(campo_local);
            }
          }
        
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposCalculadosGrupoInformacion(Campo pCampo) {
    ListaCampo listaCampo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        listaCampo_local = obtenerListaCamposCalculadosGrupoInformacionMultiple(pCampo);
      } else {
        listaCampo_local = obtenerListaCamposCalculadosGrupoInformacionNoMultiple(pCampo);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public Campo obtenerPrimerCampoValorUnicoAplicacion(int pIdAplicacion) {
    Campo campo_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCampo_local = obtenerListaCamposGruposInformacionNoMultiples(pIdAplicacion);
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getFormatoCampo().esValorUnico() && !campo_local.getRestriccionCampo().esLlavePrimaria()) {
          break;
        }
        campo_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterador_local = null;
    } 
    
    return campo_local;
  }
  public Campo obtenerPrimerCampoValorUnicoGrupoInformacion(int pIdGrupoInformacion) {
    Campo campo_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCampo_local = obtenerListaCamposGrupoInformacion(pIdGrupoInformacion);
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getFormatoCampo().esValorUnico() && !campo_local.getRestriccionCampo().esLlavePrimaria()) {
          break;
        }
        campo_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterador_local = null;
    } 
    
    return campo_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionMultiplesAplicacion(int pIdAplicacion) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion && grupoInformacion_local.esGrupoInformacionMultiple()) {
          listaGrupoInformacion_local.adicionar(grupoInformacion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
    
    return listaGrupoInformacion_local;
  }
  public ListaCampo obtenerListaCamposVisiblesGrupoInformacionMultiple(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoArchivoGrupoInformacionConEtiqueta(campo_local, pIdGrupoInformacion) && campo_local.esVisibleUsuarioSecundario()) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposVisiblesGrupoInformacionMultipleConArchivos(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoGrupoInformacionConEtiqueta(campo_local, pIdGrupoInformacion) && campo_local.esVisibleUsuarioSecundario())
        {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposTipoDocumentoGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (pGrupoInformacion.esGrupoInformacionMultiple()) {
          if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.esTipoDatoDocumento())
          {
            listaCampo_local.adicionar(campo_local); } 
          continue;
        } 
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && 
          !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.esTipoDatoDocumento()) {
          listaCampo_local.adicionar(campo_local);
        }
      }
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposGrupoInformacion(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoArchivoGrupoInformacionSinEtiqueta(campo_local, pIdGrupoInformacion)) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposGrupoInformacionConArchivos(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoGrupoInformacionSinEtiqueta(campo_local, pIdGrupoInformacion)) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public void borrarValoresListaCampos(int pIdAplicacion) {
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion) {
          campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
  }
  private boolean verificarGrupoContieneCamposCalculadosRecalculablesNoMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposCalculadosRecalculablesNoMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposCalculadosRecalculablesNoMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposCalculadosRecalculablesNoMultiple_local = (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1);
        
        if (contieneCamposCalculadosRecalculablesNoMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposCalculadosRecalculablesNoMultiple_local;
  }
  private boolean verificarGrupoContieneCamposCalculadosRecalculablesMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposCalculadosRecalculablesMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposCalculadosRecalculablesMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposCalculadosRecalculablesMultiple_local = (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1);
        
        if (contieneCamposCalculadosRecalculablesMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposCalculadosRecalculablesMultiple_local;
  }
  public boolean verificarGrupoContieneCamposCalculadosRecalculables(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposCalculadosRecalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposCalculadosRecalculables_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        contieneCamposCalculadosRecalculables_local = verificarGrupoContieneCamposCalculadosRecalculablesNoMultiple(pGrupoInformacion);
      } else {
        contieneCamposCalculadosRecalculables_local = verificarGrupoContieneCamposCalculadosRecalculablesMultiple(pGrupoInformacion);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return contieneCamposCalculadosRecalculables_local;
  }
  private ListaCampo obtenerListaCamposCalculadosRecalculablesGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1)
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposCalculadosRecalculablesGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1)
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposCalculadosRecalculablesGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        listaCampo_local = obtenerListaCamposCalculadosRecalculablesGrupoInformacionNoMultiple(pGrupoInformacion);
      } else {
        listaCampo_local = obtenerListaCamposCalculadosRecalculablesGrupoInformacionMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private boolean verificarGrupoContieneCamposCalculadosNoRecalculablesNoMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposCalculadosNoRecalculablesNoMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposCalculadosNoRecalculablesNoMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposCalculadosNoRecalculablesNoMultiple_local = (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && !campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1);
        
        if (contieneCamposCalculadosNoRecalculablesNoMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposCalculadosNoRecalculablesNoMultiple_local;
  }
  private boolean verificarGrupoContieneCamposCalculadosNoRecalculablesMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposCalculadosNoRecalculablesMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposCalculadosNoRecalculablesMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposCalculadosNoRecalculablesMultiple_local = (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && !campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1);
        
        if (contieneCamposCalculadosNoRecalculablesMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposCalculadosNoRecalculablesMultiple_local;
  }
  public boolean verificarGrupoContieneCamposCalculadosNoRecalculables(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposCalculadosNoRecalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposCalculadosNoRecalculables_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        contieneCamposCalculadosNoRecalculables_local = verificarGrupoContieneCamposCalculadosNoRecalculablesNoMultiple(pGrupoInformacion);
      } else {
        contieneCamposCalculadosNoRecalculables_local = verificarGrupoContieneCamposCalculadosNoRecalculablesMultiple(pGrupoInformacion);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return contieneCamposCalculadosNoRecalculables_local;
  }
  private ListaCampo obtenerListaCamposCalculadosNoRecalculablesGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && !campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1)
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposCalculadosNoRecalculablesGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && !campo_local.getCalculoCampo().esRecalculable() && campo_local.getCalculoCampo().getCampoCalculado() != 1)
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposCalculadosNoRecalculablesGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        listaCampo_local = obtenerListaCamposCalculadosNoRecalculablesGrupoInformacionNoMultiple(pGrupoInformacion);
      } else {
        listaCampo_local = obtenerListaCamposCalculadosNoRecalculablesGrupoInformacionMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private boolean verificarGrupoContieneCamposDependientesEnlazadoCalculablesNoMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposDependientesEnlazadoCalculablesNoMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposDependientesEnlazadoCalculablesNoMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposDependientesEnlazadoCalculablesNoMultiple_local = (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 1);
        
        if (contieneCamposDependientesEnlazadoCalculablesNoMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposDependientesEnlazadoCalculablesNoMultiple_local;
  }
  private boolean verificarGrupoContieneCamposDependientesEnlazadoCalculablesMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposDependientesEnlazadoCalculablesMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposDependientesEnlazadoCalculablesMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposDependientesEnlazadoCalculablesMultiple_local = (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 1);
        
        if (contieneCamposDependientesEnlazadoCalculablesMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposDependientesEnlazadoCalculablesMultiple_local;
  }
  public boolean verificarGrupoContieneCamposDependientesEnlazadoCalculables(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposDependientesEnlazadoCalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposDependientesEnlazadoCalculables_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        contieneCamposDependientesEnlazadoCalculables_local = verificarGrupoContieneCamposDependientesEnlazadoCalculablesNoMultiple(pGrupoInformacion);
      } else {
        contieneCamposDependientesEnlazadoCalculables_local = verificarGrupoContieneCamposDependientesEnlazadoCalculablesMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return contieneCamposDependientesEnlazadoCalculables_local;
  }
  private ListaCampo obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 1)
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 1)
        {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        listaCampo_local = obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacionNoMultiple(pGrupoInformacion);
      } else {
        listaCampo_local = obtenerListaCamposDependientesEnlazadoRecalculablesGrupoInformacionMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  private boolean verificarGrupoContieneCamposDependientesEnlazadoNoCalculablesNoMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposDependientesEnlazadoNoCalculablesNoMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposDependientesEnlazadoNoCalculablesNoMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposDependientesEnlazadoNoCalculablesNoMultiple_local = (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 2);
        
        if (contieneCamposDependientesEnlazadoNoCalculablesNoMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposDependientesEnlazadoNoCalculablesNoMultiple_local;
  }
  private boolean verificarGrupoContieneCamposDependientesEnlazadoNoCalculablesMultiple(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposDependientesEnlazadoNoCalculablesMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposDependientesEnlazadoNoCalculablesMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        contieneCamposDependientesEnlazadoNoCalculablesMultiple_local = (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 2);
        
        if (contieneCamposDependientesEnlazadoNoCalculablesMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return contieneCamposDependientesEnlazadoNoCalculablesMultiple_local;
  }
  public boolean verificarGrupoContieneCamposDependientesEnlazadoNoCalculables(GrupoInformacion pGrupoInformacion) {
    boolean contieneCamposDependientesEnlazadoNoCalculables_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return contieneCamposDependientesEnlazadoNoCalculables_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        contieneCamposDependientesEnlazadoNoCalculables_local = verificarGrupoContieneCamposDependientesEnlazadoNoCalculablesNoMultiple(pGrupoInformacion);
      } else {
        
        contieneCamposDependientesEnlazadoNoCalculables_local = verificarGrupoContieneCamposDependientesEnlazadoNoCalculablesMultiple(pGrupoInformacion);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return contieneCamposDependientesEnlazadoNoCalculables_local;
  }
  private ListaCampo obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 2)
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.getEnlaceCampo().getDependienteEnlazado() == 2)
        {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        listaCampo_local = obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacionNoMultiple(pGrupoInformacion);
      } else {
        listaCampo_local = obtenerListaCamposDependientesEnlazadoNoRecalculablesGrupoInformacionMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public boolean verificarCampoTieneCamposDependientes(Campo pCampoEnlazado) {
    boolean campoTieneCamposDependientes_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO) {
      return campoTieneCamposDependientes_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        campoTieneCamposDependientes_local = (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pCampoEnlazado.getGrupoInformacion().getIdGrupoInformacion() && campo_local.getEnlaceCampo().getCampoEnlaceDepende() != ConstantesGeneral.VALOR_NULO && campo_local.getEnlaceCampo().getCampoEnlaceDepende().getIdCampo() == pCampoEnlazado.getIdCampo() && (campo_local.getEnlaceCampo().getDependienteEnlazado() == 1 || campo_local.getEnlaceCampo().getDependienteEnlazado() == 2));
        
        if (campoTieneCamposDependientes_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return campoTieneCamposDependientes_local;
  }
  public ListaCampo obtenerListaCamposDependientesEnlazado(Campo pCampoEnlazado) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pCampoEnlazado.getGrupoInformacion().getIdGrupoInformacion() && campo_local.getEnlaceCampo().getCampoEnlaceDepende() != ConstantesGeneral.VALOR_NULO && campo_local.getEnlaceCampo().getCampoEnlaceDepende().getIdCampo() == pCampoEnlazado.getIdCampo() && (campo_local.getEnlaceCampo().getDependienteEnlazado() == 1 || campo_local.getEnlaceCampo().getDependienteEnlazado() == 2))
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public boolean verificarEsAplicacionEnlazada(int pIdAplicacion) {
    boolean esAplicacionEnlazada_local = false;
    
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        esAplicacionEnlazada_local = (campo_local.esCampoEnlazado() && campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion() == pIdAplicacion);
        
        if (esAplicacionEnlazada_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return esAplicacionEnlazada_local;
  }
  public ListaCampo obtenerListaCamposRelacionadosAplicacionEnlazada(int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.esCampoEnlazado() && campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion() == pIdAplicacion) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private boolean comprobarCamposDocumentosConPlantillaNoMultiple(GrupoInformacion pGrupoInformacion) {
    boolean camposDocumentosConPlantillaNoMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return camposDocumentosConPlantillaNoMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        camposDocumentosConPlantillaNoMultiple_local = (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getFormatoCampo().getTipoDato(), "DD") && campo_local.getPlantillaCampo().esTienePlantilla());
        
        if (camposDocumentosConPlantillaNoMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return camposDocumentosConPlantillaNoMultiple_local;
  }
  private boolean comprobarCamposDocumentosConPlantillaMultiple(GrupoInformacion pGrupoInformacion) {
    boolean camposDocumentosConPlantillaMultiple_local = false;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return camposDocumentosConPlantillaMultiple_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        camposDocumentosConPlantillaMultiple_local = (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getFormatoCampo().getTipoDato(), "DD") && campo_local.getPlantillaCampo().esTienePlantilla());
        
        if (camposDocumentosConPlantillaMultiple_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return camposDocumentosConPlantillaMultiple_local;
  }
  public boolean comprobarCamposDocumentosConPlantilla(GrupoInformacion pGrupoInformacion) {
    boolean camposDocumentosConPlantilla_local = false;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return camposDocumentosConPlantilla_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        camposDocumentosConPlantilla_local = comprobarCamposDocumentosConPlantillaNoMultiple(pGrupoInformacion);
      } else {
        camposDocumentosConPlantilla_local = comprobarCamposDocumentosConPlantillaMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return camposDocumentosConPlantilla_local;
  }
  private ListaCampo obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getFormatoCampo().getTipoDato(), "DD") && campo_local.getPlantillaCampo().esTienePlantilla())
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private ListaCampo obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getFormatoCampo().getTipoDato(), "DD") && campo_local.getPlantillaCampo().esTienePlantilla())
        {
          
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
        listaCampo_local = obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacionNoMultiple(pGrupoInformacion);
      } else {
        listaCampo_local = obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacionMultiple(pGrupoInformacion);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public Aplicacion obtenerAplicacionPlantillaPrimerCampoDocumentoConPlantillaGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    Aplicacion aplicacion_local = null;
    ListaCampo listaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    
    try {
      listaCampo_local = obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacion(pGrupoInformacion);
      if (listaCampo_local.contarElementos() > 0) {
        aplicacion_local = listaCampo_local.obtenerPrimerCampo().getPlantillaCampo().getAplicacionPlantilla();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
    } 
    
    return aplicacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionPorId(int pIdGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      grupoInformacion_local = getListaGrupoInformacion().obtenerGrupoInformacionPorId(pIdGrupoInformacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionPrincipalAplicacion(int pIdAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (pIdAplicacion == grupoInformacion_local.getAplicacion().getIdAplicacion() && grupoInformacion_local.esGrupoInformacionPrincipal()) {
          break;
        }
        
        grupoInformacion_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionPorDescripcion(int pIdAplicacion, String pDescripcionGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      if (mc.sonCadenasIgualesIgnorarMayusculas(pDescripcionGrupoInformacion, "No multiple")) {
        grupoInformacion_local = obtenerGrupoInformacionPrincipalAplicacion(pIdAplicacion);
      } else {
        grupoInformacion_local = getListaGrupoInformacion().obtenerGrupoInformacionPorDescripcion(pIdAplicacion, pDescripcionGrupoInformacion);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerPrimerGrupoInformacionNoMultipleNoPrincipal(int pIdAplicacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      grupoInformacion_local = obtenerListaGruposInformacionNoMultiplesAplicacion(pIdAplicacion).obtenerPrimerGrupoInformacionNoMultipleNoPrincipal();
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public GrupoInformacion obtenerGrupoInformacionPorDescripcion(String pDescripcionGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      grupoInformacion_local = getListaGrupoInformacion().obtenerGrupoInformacionPorDescripcion(pDescripcionGrupoInformacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return grupoInformacion_local;
  }
  public Campo obtenerCampoPorId(int pIdCampo) {
    Campo campo_local = null;
    
    try {
      campo_local = getListaCampo().obtenerCampoPorId(pIdCampo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo obtenerCampoPorEtiqueta(String pEtiquetaCampo, GrupoInformacion pGrupoInformacion, int pNumeroCampo) {
    Campo campo_local = null;
    
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      campo_local = getListaCampo().obtenerCampoPorEtiqueta(pEtiquetaCampo, pGrupoInformacion, pNumeroCampo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo obtenerCampoPorEtiqueta(int pIdAplicacion, String pEtiquetaCampo) {
    Campo campo_local = null;
    
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      campo_local = getListaCampo().obtenerCampoPorEtiqueta(pIdAplicacion, pEtiquetaCampo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo obtenerCampoConsultaPorEtiqueta(int pIdAplicacion, String pEtiquetaCampo) {
    Campo campo_local = null;
    int idCampo_local = 0;
    
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      if (getListaCampo().obtenerCampoPorEtiqueta(pIdAplicacion, pEtiquetaCampo) != ConstantesGeneral.VALOR_NULO) {
        idCampo_local = getListaCampo().obtenerCampoPorEtiqueta(pIdAplicacion, pEtiquetaCampo).getIdCampo();
        campo_local = getAdministradorBaseDatosSisnet().obtenerCampoPorId(idCampo_local, false);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo obtenerCampoPorSeudonimo(String pSeudonimoCampo, int pIdAplicacion) {
    Campo campo_local = null;
    
    if (pSeudonimoCampo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      campo_local = getListaCampo().obtenerCampoPorSeudonimo(pSeudonimoCampo, pIdAplicacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo obtenerCampoPorSeudonimo(String pSeudonimoCampo) {
    Campo campo_local = null;
    
    if (pSeudonimoCampo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      campo_local = getListaCampo().obtenerCampoPorSeudonimo(pSeudonimoCampo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public Campo obtenerCopiaCampoPorSeudonimo(String pSeudonimoCampo) {
    Campo campo_local = null;
    Campo campoAuxiliar_local = null;
    
    try {
      campoAuxiliar_local = obtenerCampoPorSeudonimo(pSeudonimoCampo);
      campo_local = new Campo();
      copiarCampo(campoAuxiliar_local, campo_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoAuxiliar_local = null;
    } 
    
    return campo_local;
  }
  public Campo obtenerCampoPorPosicion(int pPosicionCampo, int pIdGrupoInformacion) {
    Campo campo_local = null;
    
    try {
      campo_local = getListaCampo().obtenerCampoPorPosicion(pPosicionCampo, pIdGrupoInformacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public void borrarCampoPorId(int pIdCampo) {
    try {
      getListaCampo().borrarCampoPorId(pIdCampo);
      reconfigurar();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionarCampoPorId(int pIdCampo) {
    Campo campo_local = null;
    
    try {
      campo_local = getAdministradorBaseDatosSisnet().obtenerCampoPorId(pIdCampo, false);
      if (campo_local != ConstantesGeneral.VALOR_NULO) {
        getListaCampo().adicionar(campo_local);
        reconfigurar();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
    } 
  }
  public void modificarCampoPorId(int pIdCampo) {
    try {
      getListaCampo().borrarCampoPorId(pIdCampo);
      adicionarCampoPorId(pIdCampo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Campo obtenerCampoConsultaPorId(int pIdCampo) {
    Campo campo_local = null;
    
    try {
      campo_local = getAdministradorBaseDatosSisnet().obtenerCampoPorId(pIdCampo, false);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public ListaCampo obtenerListaCamposEnlazados(int pIdAplicacion, int pIdAplicacionEnlazada) {
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    try {
      iterator_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion && campo_local.esCampoEnlazado() && campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion() == pIdAplicacionEnlazada)
        {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public Aplicacion obtenerAplicacionPorId(int pIdAplicacion) {
    Aplicacion aplicacion_local = null;
    
    try {
      aplicacion_local = getListaAplicacion().obtenerAplicacionPorId(pIdAplicacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return aplicacion_local;
  }
  public Aplicacion obtenerAplicacionPorTitulo(String pTituloAplicacion) {
    Aplicacion aplicacion_local = null;
    Iterator iterator_local = null;
    
    if (pTituloAplicacion == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    
    try {
      iterator_local = getListaAplicacion().iterator();
      while (iterator_local.hasNext()) {
        aplicacion_local = (Aplicacion)iterator_local.next();
        if (mc.sonCadenasIguales(pTituloAplicacion, aplicacion_local.getTituloAplicacion())) {
          break;
        }
        
        aplicacion_local = null;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
    
    return aplicacion_local;
  }
  public ListaCampo obtenerListaCamposNumericosEntrePosicionesGrupoInformacion(GrupoInformacion pGrupoInformacion, int pPosicionInicial, int pPosicionFinal) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      if (pPosicionInicial < pPosicionFinal) {
        iterador_local = getListaCampo().iterator();
        listaCampo_local = new ListaCampo();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          if (pGrupoInformacion.esGrupoInformacionMultiple()) {
            if (campo_local.getGrupoInformacion().getIdGrupoInformacion() == pGrupoInformacion.getIdGrupoInformacion() && campo_local.esTipoDatoNumerico() && !campo_local.esCampoEnlazado() && campo_local.getPosicion() >= pPosicionInicial && campo_local.getPosicion() <= pPosicionFinal)
            {
              
              listaCampo_local.adicionar(campo_local); } 
            continue;
          } 
          if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pGrupoInformacion.getAplicacion().getIdAplicacion() && 
            !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && !campo_local.esCampoEnlazado() && campo_local.esTipoDatoNumerico() && campo_local.getPosicion() >= pPosicionInicial && campo_local.getPosicion() <= pPosicionFinal)
          {
            
            listaCampo_local.adicionar(campo_local);
          }
        }
      
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerListaCamposAplicacion(int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    try {
      iterator_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion && !campo_local.getRestriccionCampo().esLlavePrimaria() && !mc.esCadenaVacia(campo_local.getEtiquetaCampo()))
        {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  private boolean verificarGrupoInformacionContieneCamposVisibles(int pIdGrupoInformacion) {
    boolean grupoInformacionContieneCampoVisible_local = false;
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    try {
      listaCampo_local = obtenerListaCamposGrupoInformacion(pIdGrupoInformacion);
      iterator_local = listaCampo_local.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (campo_local.esVisibleUsuarioPrincipal()) {
          grupoInformacionContieneCampoVisible_local = true;
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
      iterator_local = null;
      campo_local = null;
    } 
    
    return grupoInformacionContieneCampoVisible_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionAplicacion(int pIdAplicacion, boolean pVerificarExistenCamposVisibles) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion && !grupoInformacion_local.esGrupoInformacionPrincipal())
        {
          if (pVerificarExistenCamposVisibles) {
            if (verificarGrupoInformacionContieneCamposVisibles(grupoInformacion_local.getIdGrupoInformacion()))
              listaGrupoInformacion_local.adicionar(grupoInformacion_local); 
            continue;
          } 
          listaGrupoInformacion_local.adicionar(grupoInformacion_local);
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
    
    return listaGrupoInformacion_local;
  }
  public ListaGrupoInformacion obtenerListaGruposInformacionNoMultiplesAplicacion(int pIdAplicacion) {
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      iterador_local = getListaGrupoInformacion().iterator();
      listaGrupoInformacion_local = new ListaGrupoInformacion();
      while (iterador_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterador_local.next();
        if (grupoInformacion_local.getAplicacion().getIdAplicacion() == pIdAplicacion && !grupoInformacion_local.esGrupoInformacionMultiple())
        {
          listaGrupoInformacion_local.adicionar(grupoInformacion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
    } 
    
    return listaGrupoInformacion_local;
  }
  public int obtenerIdPrimerGrupoInformacionNoMultipleAplicacion(int pIdAplicacion) {
    int idGrupoInformacion_local = -1;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    Iterator iterator_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    try {
      listaGrupoInformacion_local = obtenerListaGruposInformacionNoMultiplesAplicacion(pIdAplicacion);
      iterator_local = listaGrupoInformacion_local.iterator();
      
      while (iterator_local.hasNext()) {
        grupoInformacion_local = (GrupoInformacion)iterator_local.next();
        if (!grupoInformacion_local.esGrupoInformacionPrincipal()) {
          idGrupoInformacion_local = grupoInformacion_local.getIdGrupoInformacion();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaGrupoInformacion_local = null;
      iterator_local = null;
      grupoInformacion_local = null;
    } 
    
    return idGrupoInformacion_local;
  }
  public Campo obtenerCampoLlavePrimariaGrupoInformacion(int pIdGrupoInformacion) {
    Campo campoLlavePrimaria_local = null;
    GrupoInformacion grupoInformacion_local = null;
    ListaCampo listaCampo_local = null;
    
    try {
      grupoInformacion_local = obtenerGrupoInformacionPorId(pIdGrupoInformacion);
      if (!grupoInformacion_local.esGrupoInformacionMultiple()) {
        grupoInformacion_local = obtenerGrupoInformacionPrincipalAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion());
      }
      listaCampo_local = obtenerListaCamposGrupoInformacion(grupoInformacion_local.getIdGrupoInformacion());
      
      campoLlavePrimaria_local = listaCampo_local.obtenerCampoLlavePrimaria();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      grupoInformacion_local = null;
      listaCampo_local = null;
    } 
    
    return campoLlavePrimaria_local;
  }
  public Campo obtenerCampoLlaveForaneaGrupoInformacion(int pIdGrupoInformacion) {
    Campo campoLlavePrimaria_local = null;
    ListaCampo listaCampo_local = null;
    
    try {
      listaCampo_local = obtenerListaCamposGrupoInformacion(pIdGrupoInformacion);
      campoLlavePrimaria_local = listaCampo_local.obtenerCampoLlaveForanea();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCampo_local = null;
    } 
    
    return campoLlavePrimaria_local;
  }
  public GrupoInformacion obtenerGrupoInformacionRelacionAplicativos(String pDescripcionGrupoInformacion) {
    GrupoInformacion grupoInformacion_local = null;
    Aplicacion aplicacion_local = null;
    Campo campo_local = null;
    
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    if (mc.esCadenaVacia(pDescripcionGrupoInformacion)) {
      return grupoInformacion_local;
    }
    
    try {
      aplicacion_local = obtenerAplicacionPorTitulo(pDescripcionGrupoInformacion);
      if (aplicacion_local != ConstantesGeneral.VALOR_NULO) {
        grupoInformacion_local = obtenerGrupoInformacionPrincipalAplicacion(aplicacion_local.getIdAplicacion());
      } else {
        grupoInformacion_local = obtenerGrupoInformacionPorDescripcion(pDescripcionGrupoInformacion);
        if (grupoInformacion_local == ConstantesGeneral.VALOR_NULO) {
          campo_local = obtenerCampoPorSeudonimo(pDescripcionGrupoInformacion);
          if (campo_local != ConstantesGeneral.VALOR_NULO) {
            grupoInformacion_local = campo_local.getGrupoInformacion();
          }
        } 
      } 
      if (grupoInformacion_local != ConstantesGeneral.VALOR_NULO && !grupoInformacion_local.esGrupoInformacionMultiple()) {
        grupoInformacion_local = obtenerGrupoInformacionPrincipalAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion());
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      aplicacion_local = null;
      campo_local = null;
    } 
    
    return grupoInformacion_local;
  }
  private void copiarCampo(Campo pCampoOrigen, Campo pCampoDestino) {
    if (pCampoOrigen == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pCampoDestino == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pCampoDestino.setIdCampo(pCampoOrigen.getIdCampo());
      pCampoDestino.setNombreCampo(pCampoOrigen.getNombreCampo());
      pCampoDestino.setEtiquetaCampo(pCampoOrigen.getEtiquetaCampo());
      pCampoDestino.setSeudonimo(pCampoOrigen.getSeudonimo());
      pCampoDestino.setPosicion(pCampoOrigen.getPosicion());
      pCampoDestino.setObligatorio(pCampoOrigen.esObligatorio());
      pCampoDestino.setVisibleUsuarioPrincipal(pCampoOrigen.esVisibleUsuarioPrincipal());
      pCampoDestino.setVisibleUsuarioSecundario(pCampoOrigen.esVisibleUsuarioSecundario());
      pCampoDestino.setModificable(pCampoOrigen.esModificable());
      pCampoDestino.setAnchoColumna(pCampoOrigen.getAnchoColumna());
      pCampoDestino.setTipoHabilitacion(pCampoOrigen.getTipoHabilitacion());
      pCampoDestino.setHabilitadoPor(pCampoOrigen.getHabilitadoPor());
      pCampoDestino.setListaDependiente(pCampoOrigen.getListaDependiente());
      pCampoDestino.setIncluirOpcionConsulta(pCampoOrigen.esIncluirOpcionConsulta());
      pCampoDestino.setBorrarValorNoHabilitado(pCampoOrigen.esBorrarValorNoHabilitado());
      pCampoDestino.setRecargarPantalla(pCampoOrigen.esRecargarPantalla());
      pCampoDestino.setFormatoCampo(pCampoOrigen.getFormatoCampo());
      pCampoDestino.setRestriccionCampo(pCampoOrigen.getRestriccionCampo());
      pCampoDestino.setPlantillaCampo(pCampoOrigen.getPlantillaCampo());
      pCampoDestino.setEnlaceCampo(pCampoOrigen.getEnlaceCampo());
      pCampoDestino.setCalculoCampo(pCampoOrigen.getCalculoCampo());
      pCampoDestino.setGrupoInformacion(pCampoOrigen.getGrupoInformacion());
      pCampoDestino.setEstiloCampo(pCampoOrigen.getEstiloCampo());
      pCampoDestino.setEsImagen(pCampoOrigen.esImagen());
      pCampoDestino.setAltoImagenPantallaPresentacion(pCampoOrigen.getAltoImagenPantallaPresentacion());
      pCampoDestino.setAltoImagenPantallaEdicion(pCampoOrigen.getAltoImagenPantallaEdicion());
      pCampoDestino.setOcultarEtiquetaControlExaminar(pCampoOrigen.ocultarEtiquetaControlExaminar());
      pCampoDestino.setOcultarEtiquetaControlVer(pCampoOrigen.ocultarEtiquetaControlVer());
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Campo obtenerCopiaCampoPorId(int pIdCampo) {
    Campo campo_local = null;
    Campo campoAuxiliar_local = null;
    
    try {
      campoAuxiliar_local = obtenerCampoPorId(pIdCampo);
      campo_local = new Campo();
      copiarCampo(campoAuxiliar_local, campo_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campoAuxiliar_local = null;
    } 
    
    return campo_local;
  }
  public ListaCampo obtenerCopiaListaCamposGruposInformacionNoMultiplesConArchivos(int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    Campo campoAuxiliar_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion)
        {
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && verificarCampoGrupoInformacionConArchivos(campo_local, campo_local.getGrupoInformacion().getIdGrupoInformacion())) {
            
            campoAuxiliar_local = obtenerCopiaCampoPorId(campo_local.getIdCampo());
            listaCampo_local.adicionar(campoAuxiliar_local);
          } 
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      campoAuxiliar_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCopiaListaCamposGruposInformacionNoMultiples(int pIdAplicacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    Campo campoAuxiliar_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() == pIdAplicacion)
        {
          if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
            campoAuxiliar_local = obtenerCopiaCampoPorId(campo_local.getIdCampo());
            listaCampo_local.adicionar(campoAuxiliar_local);
          } 
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      campoAuxiliar_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCopiaListaCamposGrupoInformacionConArchivos(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    Campo campoAuxiliar_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (verificarCampoNoRestringidoDocumentoGrupoInformacionSinEtiqueta(campo_local, pIdGrupoInformacion)) {
          campoAuxiliar_local = obtenerCopiaCampoPorId(campo_local.getIdCampo());
          listaCampo_local.adicionar(campoAuxiliar_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      campoAuxiliar_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCampo obtenerCopiaListaCamposGrupoInformacion(int pIdGrupoInformacion) {
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    Campo campoAuxiliar_local = null;
    
    try {
      iterador_local = getListaCampo().iterator();
      listaCampo_local = new ListaCampo();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getGrupoInformacion() != ConstantesGeneral.VALOR_NULO && campo_local.getGrupoInformacion().getIdGrupoInformacion() == pIdGrupoInformacion) {
          
          campoAuxiliar_local = obtenerCopiaCampoPorId(campo_local.getIdCampo());
          listaCampo_local.adicionar(campoAuxiliar_local);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      campoAuxiliar_local = null;
    } 
    
    return listaCampo_local;
  }
  public void reorganizarPosicionesCampos(Campo pCampo) {
    ListaCampo listaCampo_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      listaCampo_local = obtenerListaCamposGrupoInformacionConArchivos(pCampo.getGrupoInformacion().getIdGrupoInformacion());
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          if (campo_local.getPosicion() > pCampo.getPosicion()) {
            campo_local.setPosicion(campo_local.getPosicion() - 1);
          }
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
  public Campo obtenerPrimerCampoTipoDocumento(GrupoInformacion pGrupoInformacion) {
    Campo campo_local = null;
    
    try {
      campo_local = obtenerListaCamposTipoDocumentoGrupoInformacion(pGrupoInformacion).obtenerPrimerCampo();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return campo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\motorAplicacion\MotorAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */