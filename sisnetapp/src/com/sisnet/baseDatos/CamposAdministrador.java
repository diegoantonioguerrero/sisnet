package com.sisnet.baseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
public class CamposAdministrador
{
  private static com.sisnet.baseDatos.CamposAdministrador camposAdministradorSingleton = null;
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  public static com.sisnet.baseDatos.CamposAdministrador getCamposAdministrador()
  {
    if (camposAdministradorSingleton == ConstantesGeneral.VALOR_NULO)
    {
      camposAdministradorSingleton = new com.sisnet.baseDatos.CamposAdministrador();
    }
    return camposAdministradorSingleton;
  }
  public Campo obtenerCampoIdAplicacion(boolean pEsLlavePrimaria)
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidaplicacion");
      campo_local.setSeudonimo("fldidaplicacion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(pEsLlavePrimaria);
      campo_local.getRestriccionCampo().setLlaveForanea(!pEsLlavePrimaria);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreAplicacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombreaplicacion");
      campo_local.setSeudonimo("fldnombreaplicacion");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(30);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTituloAplicacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtituloaplicacion");
      campo_local.setEtiquetaCampo("Aplicaci\u00f3n");
      campo_local.setSeudonimo("fldtituloaplicacion");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(100);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEsTabla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldestabla");
      campo_local.setSeudonimo("fldestabla");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(false);
      campo_local.setVisibleUsuarioPrincipal(false);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdAplicacionConsulta()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidaplicacionconsulta");
      campo_local.setEtiquetaCampo("Aplicaci\u00f3n de Consultas Frecuentes");
      campo_local.setSeudonimo("fldidaplicacionconsulta");
      campo_local.getFormatoCampo().setTipoDato("A");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdAplicacionReporte()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidaplicacionreporte");
      campo_local.setEtiquetaCampo("Aplicaci\u00f3n de Reportes");
      campo_local.setSeudonimo("fldidaplicacionreporte");
      campo_local.getFormatoCampo().setTipoDato("A");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoActualizarInformacionEnlazada()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldactualizarinformacionenlazada");
      campo_local.setEtiquetaCampo("Recargar constantemente informaci\u00f3n de aplicativos originales");
      campo_local.setSeudonimo("fldactualizarinformacionenlazada");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAplicacionesActualizar()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldaplicacionesactualizar");
      campo_local.setEtiquetaCampo("Aplicativos destino que deben actualizarse durante la modificaci\u00f3n de registros del aplicativo actual");
      campo_local.setSeudonimo("fldaplicacionesactualizar");
      campo_local.getFormatoCampo().setTipoDato("NN");
      campo_local.getFormatoCampo().setLongitudCampo(10485760);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTipoEventosUsuario()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtipoeventosusuario");
      campo_local.setEtiquetaCampo("Tipos de eventos del usuario");
      campo_local.setSeudonimo("fldtipoeventosusuario");
      campo_local.getFormatoCampo().setTipoDato("NN");
      campo_local.getFormatoCampo().setLongitudCampo(10485760);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEventosAcciones()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldeventosacciones");
      campo_local.setEtiquetaCampo("Eventos - Accciones");
      campo_local.setSeudonimo("fldeventosacciones");
      campo_local.getFormatoCampo().setTipoDato("NN");
      campo_local.getFormatoCampo().setLongitudCampo(10485760);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAdvertenciaEjecucion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldadvertenciaejecucion");
      campo_local.setEtiquetaCampo("Advertir sobre tiempo de ejecuci\u00f3n consulta principal");
      campo_local.setSeudonimo("fldadvertenciaejecucion");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoPermitirConsultaGeneral()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldpermitirconsultageneral");
      campo_local.setEtiquetaCampo("Permitir consulta sin opciones");
      campo_local.setSeudonimo("fldpermitirconsultageneral");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTamanoMaximoArchivos()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtamanomaximoarchivos");
      campo_local.setEtiquetaCampo("Tama\u00f1o m\u00e1ximo permitido para archivos adjuntos");
      campo_local.setSeudonimo("fldtamanomaximoarchivos");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEsOculta()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldesoculta");
      campo_local.setSeudonimo("fldesoculta");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(false);
      campo_local.setVisibleUsuarioPrincipal(false);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoHacerDobleCalculo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldhacerdoblecalculo");
      campo_local.setEtiquetaCampo("Hacer doble calculo");
      campo_local.setSeudonimo("fldhacerdoblecalculo");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNumeroRegistrosPagina()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnumeroregistrospagina");
      campo_local.setEtiquetaCampo("N\u00famero de registros por p\u00e1gina");
      campo_local.setSeudonimo("fldnumeroregistrospagina");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdAplicacionImpresionMasiva()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidaplicacionimpresionmasiva");
      campo_local.setEtiquetaCampo("Aplicativo de plantillas impresi\u00f3n masiva");
      campo_local.setSeudonimo("fldidaplicacionimpresionmasiva");
      campo_local.getFormatoCampo().setTipoDato("A");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdTabla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidtabla");
      campo_local.setSeudonimo("fldidtabla");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreTabla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombretabla");
      campo_local.setSeudonimo("fldnombretabla");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(30);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoDescripcionTabla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("flddescripciontabla");
      campo_local.setEtiquetaCampo("Descripci\u00f3n de la tabla");
      campo_local.setSeudonimo("flddescripciontabla");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(50);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setAnchoColumna(300);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdGrupoInformacion(boolean pEsLlavePrimaria)
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidgrupoinformacion");
      campo_local.setSeudonimo("fldidgrupoinformacion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(pEsLlavePrimaria);
      campo_local.getRestriccionCampo().setLlaveForanea(!pEsLlavePrimaria);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreGrupoInformacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombregrupoinformacion");
      campo_local.setSeudonimo("fldnombregrupoinformacion");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(30);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoDescripcionGrupoInformacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("flddescripciongrupoinformacion");
      campo_local.setEtiquetaCampo("Descripci\u00f3n del Grupo de Informaci\u00f3n");
      campo_local.setSeudonimo("flddescripciongrupoinformacion");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(100);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setAnchoColumna(300);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoGrupoInformacionPrincipal()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldgrupoinformacionprincipal");
      campo_local.setSeudonimo("fldgrupoinformacionprincipal");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setValorCampo(Boolean.valueOf(false));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoGrupoInformacionMultiple()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldgrupoinformacionmultiple");
      campo_local.setEtiquetaCampo("Grupo de Informaci\u00f3n M\u00faltiple");
      campo_local.setSeudonimo("fldgrupoinformacionmultiple");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoPosicion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldposicion");
      campo_local.setSeudonimo("fldposicion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoMostrarDetalle()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldmostrardetalle");
      campo_local.setEtiquetaCampo("Mostrar detalle del grupo m\u00faltiple en la pantalla dos");
      campo_local.setSeudonimo("fldmostrardetalle");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoMargenSuperiorGrupoInformacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldmargensuperiorgrupoinformacion");
      campo_local.setEtiquetaCampo("Margen Superior");
      campo_local.setSeudonimo("fldmargensuperiorgrupoinformacion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(25));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdCampo(String pNombreBaseDatos, boolean pEsLlavePrimaria)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(4, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldidcampo");
      campo_local.setSeudonimo("fldidcampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(pEsLlavePrimaria);
      campo_local.getRestriccionCampo().setLlaveForanea(!pEsLlavePrimaria);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombrecampo");
      campo_local.setSeudonimo("fldnombrecampo");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(30);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEtiquetaCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldetiquetacampo");
      campo_local.setEtiquetaCampo("Etiqueta del Campo");
      campo_local.setSeudonimo("fldetiquetacampo");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(50);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setAnchoColumna(300);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoSeudonimo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldseudonimo");
      campo_local.setEtiquetaCampo("Seud\u00f3nimo");
      campo_local.setSeudonimo("fldseudonimo");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(50);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTipoDato()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtipodato");
      campo_local.setEtiquetaCampo("Tipo de Dato");
      campo_local.setSeudonimo("fldtipodato");
      campo_local.getFormatoCampo().setTipoDato("P");
      campo_local.getFormatoCampo().setLongitudCampo(3);
      campo_local.setObligatorio(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTienePlantilla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtieneplantilla");
      campo_local.setEtiquetaCampo("Tiene Plantilla");
      campo_local.setSeudonimo("fldtieneplantilla");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdAplicacionPlantilla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidaplicacionplantilla");
      campo_local.setEtiquetaCampo("Aplicaci\u00f3n de la Plantilla");
      campo_local.setSeudonimo("fldidaplicacionplantilla");
      campo_local.getFormatoCampo().setTipoDato("HH");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoValorUnico()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldvalorunico");
      campo_local.setEtiquetaCampo("Es valor \u00fanico");
      campo_local.setSeudonimo("fldvalorunico");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoLongitudCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldlongitudcampo");
      campo_local.setEtiquetaCampo("Longitud del Campo");
      campo_local.setSeudonimo("fldlongitudcampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setValorCampo(Integer.valueOf(50));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNumeroDecimales()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnumerodecimales");
      campo_local.setEtiquetaCampo("N\u00famero de Decimales");
      campo_local.setSeudonimo("fldnumerodecimales");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setValorCampo(Integer.valueOf(0));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAnchoColumna()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldanchocolumna");
      campo_local.setEtiquetaCampo("Ancho de la Columna");
      campo_local.setSeudonimo("fldanchocolumna");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(200));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoLlavePrimaria()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldllaveprimaria");
      campo_local.setSeudonimo("fldllaveprimaria");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoLlaveForanea()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldllaveforanea");
      campo_local.setSeudonimo("fldllaveforanea");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoObligatorio()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldobligatorio");
      campo_local.setEtiquetaCampo("Campo Obligatorio");
      campo_local.setSeudonimo("fldobligatorio");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoVisibleUsuarioPrincipal()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldvisibleusuarioprincipal");
      campo_local.setEtiquetaCampo("Campo Visible Pantalla Uno");
      campo_local.setSeudonimo("fldvisibleusuarioprincipal");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoVisibleUsuarioSecundario()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldvisibleusuariosecundario");
      campo_local.setEtiquetaCampo("Campo Visible Pantalla Dos");
      campo_local.setSeudonimo("fldvisibleusuariosecundario");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoModificable()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldmodificable");
      campo_local.setSeudonimo("fldmodificable");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoHabilitadoPor()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldhabilitadopor");
      campo_local.setEtiquetaCampo("Habilitado Por");
      campo_local.setSeudonimo("fldhabilitadopor");
      campo_local.getFormatoCampo().setTipoDato("RR");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoListaDependiente()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldlistadependiente");
      campo_local.setEtiquetaCampo("Lista Dependiente de");
      campo_local.setSeudonimo("fldlistadependiente");
      campo_local.getFormatoCampo().setTipoDato("SS");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEnlazado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldenlazado");
      campo_local.setEtiquetaCampo("Enlazado a");
      campo_local.setSeudonimo("fldenlazado");
      campo_local.getFormatoCampo().setTipoDato("TT");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoDependienteEnlazado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("flddependienteenlazado");
      campo_local.setEtiquetaCampo("Dependiente de Enlazado");
      campo_local.setSeudonimo("flddependienteenlazado");
      campo_local.getFormatoCampo().setTipoDato("UU");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoOpcionDesconocido()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldopciondesconocido");
      campo_local.setEtiquetaCampo("Con Opci\u00f3n Desconocido");
      campo_local.setSeudonimo("fldopciondesconocido");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEnlaceDepende()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcampoenlacedepende");
      campo_local.setEtiquetaCampo("Campo Enlazado del Cual Depende");
      campo_local.setSeudonimo("fldcampoenlacedepende");
      campo_local.getFormatoCampo().setTipoDato("II");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoOrigenEnlace()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcampoorigenenlace");
      campo_local.setEtiquetaCampo("Campo Origen del Enlace");
      campo_local.setSeudonimo("fldcampoorigenenlace");
      campo_local.getFormatoCampo().setTipoDato("JJ");
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTipoHabilitacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtipohabilitacion");
      campo_local.setEtiquetaCampo("Tipo de Habilitaci\u00f3n");
      campo_local.setSeudonimo("fldtipohabilitacion");
      campo_local.getFormatoCampo().setTipoDato("OO");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoCampoCalculado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcampocalculado");
      campo_local.setEtiquetaCampo("Campo Calculado");
      campo_local.setSeudonimo("fldcampocalculado");
      campo_local.getFormatoCampo().setTipoDato("PP");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEsRecalculable()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldesrecalculable");
      campo_local.setEtiquetaCampo("Es Recalculable");
      campo_local.setSeudonimo("fldesrecalculable");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdCampoValor()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidcampovalor");
      campo_local.setEtiquetaCampo("Campo Valor");
      campo_local.setSeudonimo("fldidcampovalor");
      campo_local.getFormatoCampo().setTipoDato("CC");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdCampoOrigenUno()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidcampoorigenuno");
      campo_local.setEtiquetaCampo("Campo Origen Uno");
      campo_local.setSeudonimo("fldidcampoorigenuno");
      campo_local.getFormatoCampo().setTipoDato("M");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoFormatoCampoOrigenUno()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldformatocampoorigenuno");
      campo_local.setEtiquetaCampo("Formato Campo Origen Uno");
      campo_local.setSeudonimo("fldformatocampoorigenuno");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(15);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdCampoOrigenDos()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidcampoorigendos");
      campo_local.setEtiquetaCampo("Campo Origen Dos");
      campo_local.setSeudonimo("fldidcampoorigendos");
      campo_local.getFormatoCampo().setTipoDato("N");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoFormatoCampoOrigenDos()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldformatocampoorigendos");
      campo_local.setEtiquetaCampo("Formato Campo Origen Dos");
      campo_local.setSeudonimo("fldformatocampoorigendos");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(15);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoFormatoCampoCalculado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldformatocampocalculado");
      campo_local.setEtiquetaCampo("Formato Campo Calculado");
      campo_local.setSeudonimo("fldformatocampocalculado");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(15);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIncluirOpcionConsulta()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldincluiropcionconsulta");
      campo_local.setEtiquetaCampo("Utilizado Para Consulta");
      campo_local.setSeudonimo("fldincluiropcionconsulta");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoBorrarValorNoHabilitado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldborrarvalornohabilitado");
      campo_local.setEtiquetaCampo("Borrar Valor no Habilitado Por");
      campo_local.setSeudonimo("fldborrarvalornohabilitado");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoFiltradoRegistrosEnlazados()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldfiltradoregistrosenlazados");
      campo_local.setEtiquetaCampo("Filtrado de Registros Enlazados");
      campo_local.setSeudonimo("fldfiltradoregistrosenlazados");
      campo_local.getFormatoCampo().setTipoDato("KK");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoCampoOrigenFiltrado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcampoorigenfiltrado");
      campo_local.setEtiquetaCampo("Campo Origen Para Filtrado");
      campo_local.setSeudonimo("fldcampoorigenfiltrado");
      campo_local.getFormatoCampo().setTipoDato("VV");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoCampoDestinoFiltrado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcampodestinofiltrado");
      campo_local.setEtiquetaCampo("Campo Destino Para Filtrado");
      campo_local.setSeudonimo("fldcampodestinofiltrado");
      campo_local.getFormatoCampo().setTipoDato("WW");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoValorFiltrado()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldvalorfiltrado");
      campo_local.setEtiquetaCampo("Valor Para Filtrado");
      campo_local.setSeudonimo("fldvalorfiltrado");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(100);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoRecargarPantalla()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldrecargarpantalla");
      campo_local.setEtiquetaCampo("Recargar Pantalla al Editar Campo");
      campo_local.setSeudonimo("fldrecargarpantalla");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoCambiarRenglon()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcambiarrenglon");
      campo_local.setEtiquetaCampo("Cambiar Rengl\u00f3n");
      campo_local.setSeudonimo("fldcambiarrenglon");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoMargenSuperiorCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldmargensuperiorcampo");
      campo_local.setEtiquetaCampo("M\u00e1rgen Superior");
      campo_local.setSeudonimo("fldmargensuperiorcampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(0));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAnchoEtiquetaCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldanchoetiquetacampo");
      campo_local.setEtiquetaCampo("Ancho Etiqueta");
      campo_local.setSeudonimo("fldanchoetiquetacampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(200));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAnchoControlCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldanchocontrolcampo");
      campo_local.setEtiquetaCampo("Ancho Control");
      campo_local.setSeudonimo("fldanchocontrolcampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(400));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoCantidadRenglonesControlCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcantidadrenglonescontrolcampo");
      campo_local.setEtiquetaCampo("Cantidad Renglones Control");
      campo_local.setSeudonimo("fldcantidadrenglonescontrolcampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(5));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoMargenIzquierdaEtiquetaCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldmargenizquierdaetiquetacampo");
      campo_local.setEtiquetaCampo("M\u00e1rgen Izquierda Etiqueta");
      campo_local.setSeudonimo("fldmargenizquierdaetiquetacampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(0));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoMargenIzquierdaControlCampo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldmargenizquierdacontrolcampo");
      campo_local.setEtiquetaCampo("M\u00e1rgen Izquierda Control");
      campo_local.setSeudonimo("fldmargenizquierdacontrolcampo");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(0));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoEsImagen()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldesimagen");
      campo_local.setEtiquetaCampo("Es archivo tipo foto");
      campo_local.setSeudonimo("fldesimagen");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAltoImagenPantallaPresentacion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldaltoimagenpantallapresentacion");
      campo_local.setEtiquetaCampo("Alto de foto en pantalla presentaci\u00f3n");
      campo_local.setSeudonimo("fldaltoimagenpantallapresentacion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(40));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoAltoImagenPantallaEdicion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldaltoimagenpantallaedicion");
      campo_local.setEtiquetaCampo("Alto de foto en pantalla edici\u00f3n");
      campo_local.setSeudonimo("fldaltoimagenpantallaedicion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(50));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoOcultarEtiquetaControlExaminar()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldocultaretiquetacontrolexaminar");
      campo_local.setEtiquetaCampo("Ocultar etiqueta y control EXAMINAR archivo");
      campo_local.setSeudonimo("fldocultaretiquetacontrolexaminar");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoOcultarEtiquetaControlVer()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldocultaretiquetacontrolver");
      campo_local.setEtiquetaCampo("Ocultar etiqueta y control VER archivo");
      campo_local.setSeudonimo("fldocultaretiquetacontrolver");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdValorDependiente(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(7, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldidvalordependiente");
      campo_local.setSeudonimo("fldidvalordependiente");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdValorMaestro(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(7, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldidvalormaestro");
      campo_local.setSeudonimo("fldidvalormaestro");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlaveForanea(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdValorDetalle(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(7, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldidvalordetalle");
      campo_local.setEtiquetaCampo("Valor");
      campo_local.setSeudonimo("fldidvalordetalle");
      campo_local.getFormatoCampo().setTipoDato("FF");
      campo_local.getFormatoCampo().setLongitudCampo(50);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdDependienteHabilitacion(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(8, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldiddependientehabilitacion");
      campo_local.setSeudonimo("fldiddependientehabilitacion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoHabilitacion(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(8, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldhabilitacion");
      campo_local.setEtiquetaCampo("Habilitar Control");
      campo_local.setSeudonimo("fldhabilitacion");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdTipoUsuario(boolean pEsLlavePrimaria)
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldidtipousuario");
      campo_local.setSeudonimo("fldidtipousuario");
      campo_local.getRestriccionCampo().setLlavePrimaria(pEsLlavePrimaria);
      campo_local.getRestriccionCampo().setLlaveForanea(!pEsLlavePrimaria);
      campo_local.setObligatorio(true);
      if (pEsLlavePrimaria)
      {
        campo_local.getFormatoCampo().setTipoDato("E");
      }
      else
      {
        campo_local.setEtiquetaCampo("Tipo de Usuario");
        campo_local.getFormatoCampo().setTipoDato("Q");
        campo_local.setModificable(true);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreTipoUsuario()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombretipousuario");
      campo_local.setEtiquetaCampo("Tipo de Usuario");
      campo_local.setSeudonimo("fldnombretipousuario");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(50);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setAnchoColumna(300);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoPermitirUtilizarMenu()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldpermitirutilizarmenu");
      campo_local.setEtiquetaCampo("Permitir Utilizar el Men\u00fa de Configuraci\u00f3n");
      campo_local.setSeudonimo("fldpermitirutilizarmenu");
      campo_local.getFormatoCampo().setTipoDato("B");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNivelAcceso()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnivelacceso");
      campo_local.setEtiquetaCampo("Niveles de Acceso");
      campo_local.setSeudonimo("fldnivelacceso");
      campo_local.getFormatoCampo().setTipoDato("NN");
      campo_local.getFormatoCampo().setLongitudCampo(10000);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoIdUsuario(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(5, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldidusuario");
      campo_local.setSeudonimo("fldidusuario");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreUsuario(String pEtiquetaCampo, String pPlaceHolder)
  {
    Campo campo_local = null;
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombreusuario");
      campo_local.setEtiquetaCampo(pEtiquetaCampo);
      campo_local.setPlaceHolder(pPlaceHolder);
      campo_local.setSeudonimo("fldnombreusuario");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(15);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setAnchoColumna(200);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoContrasena(String pEtiquetaCampo, String pPlaceHolder)
  {
    Campo campo_local = null;
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcontrasena");
      campo_local.setEtiquetaCampo(pEtiquetaCampo);
      campo_local.setPlaceHolder(pPlaceHolder);
      campo_local.setSeudonimo("fldcontrasena");
      campo_local.getFormatoCampo().setTipoDato("C");
      campo_local.getFormatoCampo().setLongitudCampo(32);
      campo_local.setObligatorio(true);
      if (mc.esCadenaVacia(pEtiquetaCampo))
      {
        campo_local.setValorCampo("");
      }
      else
      {
        campo_local.setModificable(true);
        campo_local.setVisibleUsuarioPrincipal(true);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNombreCompletoUsuario()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnombrecompletousuario");
      campo_local.setEtiquetaCampo("Nombre Completo del Usuario");
      campo_local.setSeudonimo("fldnombrecompletousuario");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(60);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setAnchoColumna(200);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTipoLicencia()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtipolicencia");
      campo_local.setEtiquetaCampo("Tipo de Licencia");
      campo_local.setSeudonimo("fldtipolicencia");
      campo_local.getFormatoCampo().setTipoDato("I");
      campo_local.getFormatoCampo().setLongitudCampo(1);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoFechaVencimiento()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldfechavencimiento");
      campo_local.setEtiquetaCampo("Fecha de Vencimiento de la Contrase\u00f1a");
      campo_local.setSeudonimo("fldfechavencimiento");
      campo_local.getFormatoCampo().setTipoDato("F");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setValorCampo(mf.obtenerFechaUltimoDiaMesSiguiente());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoDiasVigenciaContrasena()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("flddiasvigenciacontrasena");
      campo_local.setEtiquetaCampo("D\u00edas de Vigencia de la Contrase\u00f1a");
      campo_local.setSeudonimo("flddiasvigenciacontrasena");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(30));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoContrasenasFallidasPermitidas()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcontrasenasfallidaspermitidas");
      campo_local.setEtiquetaCampo("Cantidad M\u00e1xima de Contrase\u00f1as Fallidas Diarias Permitidas");
      campo_local.setSeudonimo("fldcontrasenasfallidaspermitidas");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(5));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoFechaUltimaContrasenaFallida()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldfechaultimacontrasenafallida");
      campo_local.setEtiquetaCampo("Fecha de la \u00faltima Contrase\u00f1a Fallida");
      campo_local.setSeudonimo("fldfechaultimacontrasenafallida");
      campo_local.getFormatoCampo().setTipoDato("F");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoCantidadContrasenasFallidas()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcantidadcontrasenasfallidas");
      campo_local.setEtiquetaCampo("Cantidad de Contrase\u00f1as Fallidas");
      campo_local.setSeudonimo("fldcantidadcontrasenasfallidas");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(0));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTiempoSesion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtiemposesion");
      campo_local.setEtiquetaCampo("M\u00e1ximo de minutos para cerrar sesi\u00f3n inactiva");
      campo_local.setSeudonimo("fldtiemposesion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setValorCampo(Integer.valueOf(120));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoTipoBloqueo()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldtipobloqueo");
      campo_local.setEtiquetaCampo("Tipo de Bloqueo");
      campo_local.setSeudonimo("fldtipobloqueo");
      campo_local.getFormatoCampo().setTipoDato("AA");
      campo_local.setModificable(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoSeleccionaAplicacion(boolean pExistenAplicaciones)
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setEtiquetaCampo("Aplicaci\u00f3n");
      campo_local.setSeudonimo("fldidaplicacion");
      campo_local.setObligatorio(true);
      campo_local.setVisibleUsuarioPrincipal(true);
      if (pExistenAplicaciones)
      {
        campo_local.setNombreCampo("fldidaplicacion");
        campo_local.getFormatoCampo().setTipoDato("Z");
      }
      else
      {
        campo_local.setNombreCampo("fldtituloaplicacion");
        campo_local.getFormatoCampo().setTipoDato("T");
        campo_local.getFormatoCampo().setLongitudCampo(100);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoLlavePrimariaTabla(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(3, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldllaveprimariatabla");
      campo_local.setEtiquetaCampo("fldllaveprimariatabla");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.getRestriccionCampo().setLlavePrimaria(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoValorTabla(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(3, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldvalortabla");
      campo_local.setEtiquetaCampo("Valor");
      campo_local.setSeudonimo("fldvalortabla");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(80);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoConfirmarContrasena()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldconfirmarcontrasena");
      campo_local.setEtiquetaCampo("Confirmar Contrase\u00f1a");
      campo_local.setSeudonimo("fldconfirmarcontrasena");
      campo_local.getFormatoCampo().setTipoDato("C");
      campo_local.getFormatoCampo().setLongitudCampo(32);
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoArchivoImportar(String pNombreBaseDatos)
  {
    Campo campo_local = null;
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(ap.obtenerGrupoInformacionPorId(4, ap.obtenerAplicacionAdministradorSisnetWeb(pNombreBaseDatos)));
      campo_local.setNombreCampo("fldarchivoimportar");
      campo_local.setEtiquetaCampo("Archivo a Importar");
      campo_local.setSeudonimo("fldarchivoimportar");
      campo_local.getFormatoCampo().setTipoDato("J");
      campo_local.setObligatorio(true);
      campo_local.setModificable(true);
      campo_local.setVisibleUsuarioPrincipal(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoPlantillaUtilizar(GrupoInformacion pGrupoInformacion)
  {
    Campo campo_local = null;
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return campo_local;
    }
    try
    {
      campo_local = new Campo();
      campo_local.setGrupoInformacion(pGrupoInformacion);
      campo_local.setNombreCampo("FLDPLANTILLAUTILIZAR");
      campo_local.setEtiquetaCampo("Plantilla a Utilizar");
      campo_local.setSeudonimo("FLDPLANTILLAUTILIZAR");
      campo_local.getFormatoCampo().setTipoDato("QQ");
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setValorCampo(Integer.valueOf(-1));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoSuperUsuario()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldsuperusuario");
      campo_local.setEtiquetaCampo("Administrador Postgres");
      campo_local.setSeudonimo("fldsuperusuario");
      campo_local.getFormatoCampo().setTipoDato("T");
      campo_local.getFormatoCampo().setLongitudCampo(32);
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoContrasenaSuperUsuario()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldcontrasenasuperusuario");
      campo_local.setEtiquetaCampo("Contrase\u00f1a Administrador");
      campo_local.setSeudonimo("fldcontrasenasuperusuario");
      campo_local.getFormatoCampo().setTipoDato("C");
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
  public Campo obtenerCampoNumeroPuertoConexion()
  {
    Campo campo_local = null;
    try
    {
      campo_local = new Campo();
      campo_local.setNombreCampo("fldnumeropuertoconexion");
      campo_local.setEtiquetaCampo("Puerto de Conexi\u00f3n");
      campo_local.setSeudonimo("fldnumeropuertoconexion");
      campo_local.getFormatoCampo().setTipoDato("E");
      campo_local.setVisibleUsuarioPrincipal(true);
      campo_local.setObligatorio(true);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return campo_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\CamposAdministrador.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */