package com.sisnet.objetosManejo.listas.objetosBaseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.CeldaHTML;
import com.sisnet.controlesHTML.listas.ListaCeldaHTML;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import java.util.Collection;
import java.util.Iterator;
public class ListaCampo extends Lista
{
   private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
public void adicionar(Campo pCampo)
{
  try
  {
    super.adicionar(pCampo);
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
}
public void adicionar(String pNombreCampo, String pTipoDato, int pLongitud, int pNumeroDecimales)
{
  Campo campo_local = null;
  try
  {
    campo_local = new Campo();
    campo_local.setNombreCampo(pNombreCampo);
    campo_local.getFormatoCampo().setTipoDato(pTipoDato);
    campo_local.getFormatoCampo().setLongitudCampo(pLongitud);
    campo_local.getFormatoCampo().setNumeroDecimales(pNumeroDecimales);
    super.adicionar(campo_local);
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
  }
}
public void concatenar(com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo pListaCampo)
{
  addAll((Collection)pListaCampo);
}
public Campo obtenerPrimerCampo()
{
  Campo campo_local = null;
  try
  {
    if (contarElementos() > 0)
    {
      campo_local = (Campo)get(0);
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  return campo_local;
}
public void asignarValorPorDefectoCamposNumericos()
{
  Iterator iterador_local = null;
  Campo campo_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if ((campo_local.esTipoDatoNumerico() || campo_local.esTipoDatoTabla()) && campo_local.getValorCampo() == ConstantesGeneral.VALOR_NULO)
      {
        campo_local.setValorCampo(Integer.valueOf(0));
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
    iterador_local = null;
  }
}
public Campo obtenerCampoPorNombre(String pNombreCampo)
{
  Iterator iterador_local = null;
  Campo campo_local = null;
  if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
  {
    return campo_local;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), pNombreCampo))
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoPorId(int pIdCampo)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  try
  {
    if (pIdCampo > 0)
    {
      iterador_local = iterator();
      while (iterador_local.hasNext())
      {
        campo_local = (Campo)iterador_local.next();
        if (pIdCampo == campo_local.getIdCampo())
        {
          break;
        }
        campo_local = null;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public void modificarCampoPorId(Campo pCampoModificado)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  if (pCampoModificado == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (pCampoModificado.getIdCampo() == campo_local.getIdCampo())
      {
        campo_local = pCampoModificado;
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
    campo_local = null;
  }
}
public void borrarCampoPorId(int pIdCampo)
{
  Iterator iterador_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      if (pIdCampo == ((Campo)iterador_local.next()).getIdCampo())
      {
        iterador_local.remove();
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
}
public Campo obtenerCampoPorEtiqueta(String pEtiquetaCampo, GrupoInformacion pGrupoInformacion, int pNumeroCampo)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO)
  {
    return campo_local;
  }
  if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
  {
    return campo_local;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (pGrupoInformacion.esGrupoInformacionMultiple() && pNumeroCampo != 0)
      {
        if (pGrupoInformacion.getIdGrupoInformacion() == campo_local.getGrupoInformacion().getIdGrupoInformacion() && mc.sonCadenasIgualesIgnorarMayusculas(pEtiquetaCampo, campo_local.getEtiquetaCampo()))
        {
          break;
        }
        campo_local = null; continue;
      }
      if (pGrupoInformacion.getAplicacion().getIdAplicacion() == campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple() && mc.sonCadenasIgualesIgnorarMayusculas(pEtiquetaCampo, campo_local.getEtiquetaCampo()))
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoPorEtiqueta(int pIdAplicacion, String pEtiquetaCampo)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO)
  {
    return campo_local;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (pIdAplicacion == campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() && mc.sonCadenasIgualesIgnorarMayusculas(pEtiquetaCampo, campo_local.getEtiquetaCampo()))
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoPorPosicion(int pPosicionCampo, int pIdGrupoInformacion)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (pIdGrupoInformacion == campo_local.getGrupoInformacion().getIdGrupoInformacion() && pPosicionCampo == campo_local.getPosicion())
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoPorSeudonimo(String pSeudonimoCampo, int pIdAplicacion)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  if (pSeudonimoCampo == ConstantesGeneral.VALOR_NULO)
  {
    return campo_local;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (pIdAplicacion == campo_local.getGrupoInformacion().getAplicacion().getIdAplicacion() && mc.sonCadenasIgualesIgnorarMayusculas(pSeudonimoCampo, campo_local.getSeudonimo()))
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoPorSeudonimo(String pSeudonimoCampo)
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  if (pSeudonimoCampo == ConstantesGeneral.VALOR_NULO)
  {
    return campo_local;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (mc.sonCadenasIgualesIgnorarMayusculas(pSeudonimoCampo, campo_local.getSeudonimo()))
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoLlavePrimaria()
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (campo_local.getRestriccionCampo().esLlavePrimaria())
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public Campo obtenerCampoLlaveForanea()
{
  Campo campo_local = null;
  Iterator iterador_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (campo_local.getRestriccionCampo().esLlaveForanea() && mc.esCadenaVacia(campo_local.getEtiquetaCampo()))
      {
        break;
      }
      campo_local = null;
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return campo_local;
}
public void borrarCamposNoEncontradosLista(ListaCadenas pListaNombresCampo)
{
  boolean existeCampo_local = false;
  Iterator iteradorCampos_local = null;
  Campo campo_local = null;
  if (pListaNombresCampo == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iteradorCampos_local = iterator();
    while (iteradorCampos_local.hasNext())
    {
      campo_local = (Campo)iteradorCampos_local.next();
      existeCampo_local = pListaNombresCampo.verificarExistenciaCadena(campo_local.getNombreCampo());
      if (!existeCampo_local)
      {
        iteradorCampos_local.remove();
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
    iteradorCampos_local = null;
  }
}
public void borrarCamposTipoArchivoParaInclusion()
{
  Iterator iterador_local = null;
  Campo campo_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (campo_local.esTipoDatoArchivo())
      {
        iterador_local.remove();
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
    campo_local = null;
  }
}
public void cambiarFormatoCamposFecha()
{
  Iterator iterador_local = null;
  Campo campo_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "F") && campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
      {
        campo_local.setValorCampo(mc.reemplazarCadena(campo_local.getValorCampo().toString(), String.valueOf('/'), String.valueOf('-')));
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
    campo_local = null;
  }
}
public boolean verificarExistenciaCampoTipoArchivo()
{
  boolean hayCamposTipoArchivo_local = false;
  Iterator iterador_local = null;
  Campo campo_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      hayCamposTipoArchivo_local = mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getFormatoCampo().getTipoDato(), "J");
      if (hayCamposTipoArchivo_local)
      {
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
    iterador_local = null;
  }
  return hayCamposTipoArchivo_local;
}
public void consultarValoresCampos()
{
  Iterator iterador_local = null;
  Campo campo_local = null;
  String cadenaIgual_local = null;
  try
  {
    cadenaIgual_local = mc.concatenarCadena(String.valueOf(' '), String.valueOf('=') + String.valueOf(' '));
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      System.out.println(campo_local.getSeudonimo() + cadenaIgual_local + String.valueOf(campo_local.getValorCampo()));
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
    iterador_local = null;
    cadenaIgual_local = null;
  }
}
public boolean sonTodosLosCamposTipoArchivo()
{
  boolean todosLosCamposSonTipoArchivo_local = true;
  Iterator iterador_local = null;
  Campo campo_local = null;
  try
  {
    if (contarElementos() == 0)
    {
      return false;
    }
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      todosLosCamposSonTipoArchivo_local = mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getFormatoCampo().getTipoDato(), "J");
      if (!todosLosCamposSonTipoArchivo_local)
      {
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
    iterador_local = null;
  }
  return todosLosCamposSonTipoArchivo_local;
}
public void asignarValorCampo(String pNombreCampo, Object pValorCampo)
{
  Iterator iterator_local = null;
  Campo campo_local = null;
  if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  if (pValorCampo == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), pNombreCampo))
      {
        if (campo_local.esTipoDatoTexto())
        {
          pValorCampo = mc.borrarEspaciosLaterales(pValorCampo.toString());
        }
        obtenerCampoPorNombre(campo_local.getNombreCampo()).setValorCampo(pValorCampo);
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
}
public String concatenarCamposGrupoInformacion()
{
  String camposGrupoInformacion_local = "";
  Iterator iterador_local = null;
  Campo campo_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      campo_local = (Campo)iterador_local.next();
      if (!mc.sonCadenasIguales(campo_local.getNombreCampo(), "fldconfirmarcontrasena"))
      {
        camposGrupoInformacion_local = mc.concatenarCadena(camposGrupoInformacion_local, campo_local.getNombreCampo());
        if (iterador_local.hasNext())
        {
          camposGrupoInformacion_local = mc.concatenarCadena(camposGrupoInformacion_local, String.valueOf(','));
        }
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
    campo_local = null;
  }
  return camposGrupoInformacion_local;
}
public void concatenarListaCampo(com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo pListaCampoAdicionar)
{
  Iterator iterator_local = null;
  Campo campo_local = null;
  if (pListaCampoAdicionar == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iterator_local = pListaCampoAdicionar.iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      adicionar(campo_local);
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
}
public void copiarValoresListaCampoConsultaSQL(com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo pListaCampoConsultaSQL)
{
  Iterator iterator_local = null;
  Campo campo_local = null;
  if (pListaCampoConsultaSQL == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iterator_local = pListaCampoConsultaSQL.iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
      {
        asignarValorCampo(campo_local.getNombreCampo(), campo_local.getValorCampo());
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
}
public String obtenerValorCampo(String pNombreCampo)
{
  String valorCampo_local = "";
  Iterator iterator_local = null;
  Campo campo_local = null;
  if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
  {
    return valorCampo_local;
  }
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), pNombreCampo))
      {
        if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
        {
          valorCampo_local = campo_local.getValorCampo().toString();
        }
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
  return valorCampo_local;
}
public String obtenerValorCampoPorSeudonimo(String pSeudonimoCampo)
{
  String valorCampo_local = "";
  Iterator iterator_local = null;
  Campo campo_local = null;
  if (pSeudonimoCampo == ConstantesGeneral.VALOR_NULO)
  {
    return valorCampo_local;
  }
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getSeudonimo(), pSeudonimoCampo))
      {
        if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
        {
          valorCampo_local = campo_local.getValorCampo().toString();
        }
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
  return valorCampo_local;
}
public ListaCeldaHTML conformarListaCeldasFormulario()
{
  ListaCeldaHTML listaCeldaHTML_local = null;
  int contadorFila_local = 0;
  Iterator iterador_local = null;
  Campo campo_local = null;
  CeldaHTML celdaHTML_local = null;
  CeldaHTML celdaEtiquetaHTML_local = null;
  try
  {
    listaCeldaHTML_local = new ListaCeldaHTML();
    iterador_local = iterator();
    for (contadorFila_local = 0; iterador_local.hasNext(); contadorFila_local++)
    {
      campo_local = (Campo)iterador_local.next();
      celdaEtiquetaHTML_local = campo_local.conformarCeldaParaEtiqueta();
      if (celdaEtiquetaHTML_local != ConstantesGeneral.VALOR_NULO)
      {
        celdaEtiquetaHTML_local.setNumeroFila(contadorFila_local);
        listaCeldaHTML_local.adicionar(celdaEtiquetaHTML_local);
      }
      celdaHTML_local = campo_local.conformarCeldaControlParaFormulario();
      if (celdaHTML_local != ConstantesGeneral.VALOR_NULO)
      {
        celdaHTML_local.setNumeroFila(contadorFila_local);
        listaCeldaHTML_local.adicionar(celdaHTML_local);
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    campo_local = null;
    iterador_local = null;
    celdaHTML_local = null;
    celdaEtiquetaHTML_local = null;
  }
  return listaCeldaHTML_local;
}
public com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo obtenerListaCamposVisiblesFormulario()
{
  com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo listaCampo_local = null;
  Iterator iterator_local = null;
  Campo campo_local = null;
  try
  {
    listaCampo_local = new com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo();
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (campo_local.getTipoHabilitacion() != 3 && !campo_local.esTipoDatoDocumento() && !campo_local.getRestriccionCampo().esLlavePrimaria() && !mc.esCadenaVacia(campo_local.getEtiquetaCampo()))
      {
        listaCampo_local.adicionar(campo_local);
      }
    }
  }
  catch (Exception excepcion_local)
  {
    excepcion_local.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
  return listaCampo_local;
}
public int calcularAnchoTablaFormulario()
{
  int anchoTablaFormulario_local = 0;
  int anchoParcial_local = 0;
  Iterator iterator_local = null;
  Campo campo_local = null;
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (campo_local.getEstiloCampo().cambiarRenglon())
      {
        if (anchoParcial_local > anchoTablaFormulario_local)
        {
          anchoTablaFormulario_local = anchoParcial_local;
        }
        anchoParcial_local = 0;
      }
      anchoParcial_local += campo_local.getEstiloCampo().getAnchoEtiqueta() + campo_local.getEstiloCampo().getAnchoControl();
    }
  }
  catch (Exception excepcion_local)
  {
    excepcion_local.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
  return anchoTablaFormulario_local;
}
public int calcularAnchoTablaInternaFormulario(int pIdCampo)
{
  int anchoTablaInternaFormulario_local = 0;
  Iterator iterator_local = null;
  Campo campo_local = null;
  boolean encontrado_local = false;
  int contador_local = 0;
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (campo_local.getIdCampo() == pIdCampo)
      {
        encontrado_local = true;
      }
 	  if (encontrado_local)
      {
        if (campo_local.getEstiloCampo().cambiarRenglon())
        {
          contador_local++;
          if (contador_local == 2)
          {
            break;
          }
        }
        anchoTablaInternaFormulario_local += campo_local.getEstiloCampo().getAnchoEtiqueta() + campo_local.getEstiloCampo().getAnchoControl();
      }
/*      
if (encontrado_local)
      {
        contador_local++;
        if (campo_local.getEstiloCampo().cambiarRenglon() && contador_local == 2)
        {
          break;
        }
        anchoTablaInternaFormulario_local += campo_local.getEstiloCampo().getAnchoEtiqueta() + campo_local.getEstiloCampo().getAnchoControl();
      }
*/
    }
  }
  catch (Exception excepcion_local)
  {
    excepcion_local.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
  return anchoTablaInternaFormulario_local;
}
public boolean existeCampoConfirmarContrasena()
{
  boolean existeCampoConfirmarContrasena_local = false;
  Iterator iterator_local = null;
  Campo campo_local = null;
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      existeCampoConfirmarContrasena_local = mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldconfirmarcontrasena");
      if (existeCampoConfirmarContrasena_local)
      {
        break;
      }
    }
  }
  catch (Exception excepcion_local)
  {
    excepcion_local.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
  return existeCampoConfirmarContrasena_local;
}
public void asignarObligatoriedadCampo(String pNombreCampo, boolean pEsObligatorio)
{
  Iterator iterator_local = null;
  Campo campo_local = null;
  if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iterator_local = iterator();
    while (iterator_local.hasNext())
    {
      campo_local = (Campo)iterator_local.next();
      if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), pNombreCampo))
      {
        campo_local.setObligatorio(pEsObligatorio);
        break;
      }
    }
  }
  catch (Exception excepcion_local)
  {
    excepcion_local.printStackTrace();
  }
  finally
  {
    iterator_local = null;
    campo_local = null;
  }
}
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosBaseDatos\ListaCampo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */