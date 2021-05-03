package com.sisnet.baseDatos.consultasBaseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorPermisoUsuario;
import com.sisnet.aplicacion.manejadores.ManejadorVariablesSistema;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoEnlazado;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaConsulta;
import com.sisnet.objetosManejo.manejoPaginaJsp.ItemConsulta;
import java.util.Iterator;
public class ConsultaSQLPrincipal
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private ListaConsulta aListaConsulta;
  private ListaCadenas aListaGruposInformacion;
  private ListaConsulta aListaConsultaRestricciones;
  private ListaConsulta aListaConsultaOrdenadoPor;
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private ManejadorCampoEnlazado aManejadorCampoEnlazado;
  private String aBloqueFrom;
  private String aBloqueJoin;
  private String aBloqueCondiciones;
  private String aBloqueRestriccionesAplicacion;
  private String aBloqueOrderBy;
  private Aplicacion aAplicacion;
  private MotorAplicacion aMotorAplicacion;
  private Usuario aUsuario;
  private String aIdSesion;
  private boolean aSoloRegistrosDonde;
  private boolean aPermitirConsultaSinOpciones;
  public ConsultaSQLPrincipal(Aplicacion pAplicacion, ListaConsulta pListaConsulta)
  {
    setAplicacion(pAplicacion);
    setListaConsulta(pListaConsulta);
    setListaConsultaRestricciones(null);
    setManejadorCampoEnlazado(new ManejadorCampoEnlazado());
    setUsuario(null);
    setIdSesion(null);
    setSoloRegistrosDonde(false);
    setPermitirConsultaSinOpciones(false);
  }
  public ListaConsulta getListaConsulta()
  {
    return this.aListaConsulta;
  }
  public void setListaConsulta(ListaConsulta pListaConsulta)
  {
    this.aListaConsulta = pListaConsulta;
  }
  public ListaCadenas getListaGruposInformacion()
  {
    return this.aListaGruposInformacion;
  }
  public void setListaGruposInformacion(ListaCadenas pListaGruposInformacion)
  {
    this.aListaGruposInformacion = pListaGruposInformacion;
  }
  public ListaConsulta getListaConsultaRestricciones()
  {
    return this.aListaConsultaRestricciones;
  }
  public void setListaConsultaRestricciones(ListaConsulta pListaConsultaRestricciones)
  {
    this.aListaConsultaRestricciones = pListaConsultaRestricciones;
  }
  public ListaConsulta getListaConsultaOrdenadoPor()
  {
    return this.aListaConsultaOrdenadoPor;
  }
  public void setListaConsultaOrdenadoPor(ListaConsulta pListaConsultaOrdenadoPor)
  {
    this.aListaConsultaOrdenadoPor = pListaConsultaOrdenadoPor;
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet()
  {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet)
  {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
    getManejadorCampoEnlazado().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion()
  {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion)
  {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
    getManejadorCampoEnlazado().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
  }
  public ManejadorCampoEnlazado getManejadorCampoEnlazado()
  {
    return this.aManejadorCampoEnlazado;
  }
  public void setManejadorCampoEnlazado(ManejadorCampoEnlazado pManejadorCampoEnlazado)
  {
    this.aManejadorCampoEnlazado = pManejadorCampoEnlazado;
  }
  public String getBloqueFrom()
  {
    return this.aBloqueFrom;
  }
  public void setBloqueFrom(String pBloqueFrom)
  {
    this.aBloqueFrom = pBloqueFrom;
  }
  public String getBloqueJoin()
  {
    return this.aBloqueJoin;
  }
  public void setBloqueJoin(String pBloqueJoin)
  {
    this.aBloqueJoin = pBloqueJoin;
  }
  public String getBloqueCondiciones()
  {
    return this.aBloqueCondiciones;
  }
  public void setBloqueCondiciones(String pBloqueCondiciones)
  {
    this.aBloqueCondiciones = pBloqueCondiciones;
  }
  public String getBloqueRestriccionesAplicacion()
  {
    return this.aBloqueRestriccionesAplicacion;
  }
  public void setBloqueRestriccionesAplicacion(String pBloqueRestriccionesAplicacion)
  {
    this.aBloqueRestriccionesAplicacion = pBloqueRestriccionesAplicacion;
  }
  public String getBloqueOrderBy()
  {
    return this.aBloqueOrderBy;
  }
  public void setBloqueOrderBy(String pBloqueOrderBy)
  {
    this.aBloqueOrderBy = pBloqueOrderBy;
  }
  public Aplicacion getAplicacion()
  {
    return this.aAplicacion;
  }
  public void setAplicacion(Aplicacion pAplicacion)
  {
    this.aAplicacion = pAplicacion;
  }
  public MotorAplicacion getMotorAplicacion()
  {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion)
  {
    this.aMotorAplicacion = pMotorAplicacion;
    getManejadorCampoEnlazado().setMotorAplicacion(pMotorAplicacion);
  }
  public Usuario getUsuario()
  {
    return this.aUsuario;
  }
  public void setUsuario(Usuario pUsuario)
  {
    this.aUsuario = pUsuario;
  }
  public String getIdSesion()
  {
    return this.aIdSesion;
  }
  public void setIdSesion(String pIdSesion)
  {
    this.aIdSesion = pIdSesion;
  }
  public boolean esSoloRegistrosDonde()
  {
    return this.aSoloRegistrosDonde;
  }
  public void setSoloRegistrosDonde(boolean pSoloRegistrosDonde)
  {
    this.aSoloRegistrosDonde = pSoloRegistrosDonde;
  }
  public boolean esPermitirConsultaSinOpciones()
  {
    return this.aPermitirConsultaSinOpciones;
  }
  public void setPermitirConsultaSinOpciones(boolean pPermitirConsultaSinOpciones)
  {
    this.aPermitirConsultaSinOpciones = pPermitirConsultaSinOpciones;
  }
  private void extraerListaOrdenadoPor()
  {
    ListaConsulta listaConsulta_local = null;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    try
    {
      listaConsulta_local = new ListaConsulta();
      iterator_local = getListaConsulta().iterator();
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (itemConsulta_local.esOrdenadoPor())
        {
          listaConsulta_local.adicionar(itemConsulta_local);
        }
      }
      setListaConsultaOrdenadoPor(listaConsulta_local);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      iterator_local = null;
      itemConsulta_local = null;
      listaConsulta_local = null;
    }
  }
  private String complementarBloqueSelect()
  {
    String bloqueSelect_local = "";
    String nombreGrupoInformacion_local = null;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    Campo campo_local = null;
    try
    {
      iterator_local = getListaConsultaOrdenadoPor().iterator();
      if (iterator_local.hasNext())
      {
        bloqueSelect_local = String.valueOf(',');
      }
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        campo_local = itemConsulta_local.getCampoDesde();
        nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getNombreGrupoInformacion();
        if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple())
        {
          nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
        }
        bloqueSelect_local = mc.concatenarCadena(bloqueSelect_local, nombreGrupoInformacion_local);
        bloqueSelect_local = mc.concatenarCadena(bloqueSelect_local, String.valueOf('.'));
        bloqueSelect_local = mc.concatenarCadena(bloqueSelect_local, campo_local.getNombreCampo());
        if (iterator_local.hasNext())
        {
          bloqueSelect_local = mc.concatenarCadena(bloqueSelect_local, String.valueOf(','));
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
      iterator_local = null;
      itemConsulta_local = null;
      nombreGrupoInformacion_local = null;
    }
    return bloqueSelect_local;
  }
  private void extraerGruposInformacion()
  {
    ListaCadenas listaGruposInformacion_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    ItemConsulta itemConsulta_local = null;
    try
    {
      listaGruposInformacion_local = new ListaCadenas();
      listaGruposInformacion_local.adicionar(getAplicacion().getNombreAplicacion());
      iterator_local = getListaConsulta().iterator();
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (!itemConsulta_local.omitirOpcionConsulta())
        {
          campo_local = itemConsulta_local.getCampoDesde();
          if (campo_local.getGrupoInformacion().esGrupoInformacionMultiple() &&
            listaGruposInformacion_local.indexOf(campo_local.getGrupoInformacion().getNombreGrupoInformacion()) == -1)
          {
            listaGruposInformacion_local.adicionar(campo_local.getGrupoInformacion().getNombreGrupoInformacion());
          }
        }
      }
      setListaGruposInformacion(listaGruposInformacion_local);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      iterator_local = null;
      campo_local = null;
      listaGruposInformacion_local = null;
      itemConsulta_local = null;
    }
  }
  private void conformarBloqueFrom()
  {
    String bloqueFrom_local = "";
    Iterator iterator_local = null;
    String cadena_local = null;
    try
    {
      bloqueFrom_local = " from ";
      iterator_local = getListaGruposInformacion().iterator();
      while (iterator_local.hasNext())
      {
        cadena_local = (String)iterator_local.next();
        bloqueFrom_local = mc.concatenarCadena(bloqueFrom_local, cadena_local);
        if (iterator_local.hasNext())
        {
          bloqueFrom_local = mc.concatenarCadena(bloqueFrom_local, String.valueOf(','));
        }
      }
      setBloqueFrom(bloqueFrom_local);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      bloqueFrom_local = null;
      iterator_local = null;
      cadena_local = null;
    }
  }
  private void conformarBloqueJoin()
  {
    String bloqueJoin_local = "";
    String nombreGrupoInformacionPrincipal_local = null;
    Iterator iterator_local = null;
    String cadena_local = null;
    try
    {
      iterator_local = getListaGruposInformacion().iterator();
      bloqueJoin_local = " where ";
      nombreGrupoInformacionPrincipal_local = getListaGruposInformacion().obtenerCadena(0);
      while (iterator_local.hasNext())
      {
        cadena_local = (String)iterator_local.next();
        if (!mc.sonCadenasIguales(cadena_local, nombreGrupoInformacionPrincipal_local))
        {
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, nombreGrupoInformacionPrincipal_local);
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, String.valueOf('.'));
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, ap.conformarNombreCampoLlavePrimaria(getAplicacion().getNombreAplicacion()));
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, " = ");
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, cadena_local);
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, String.valueOf('.'));
          bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, ap.conformarNombreCampoLlavePrimaria(getAplicacion().getNombreAplicacion()));
          if (iterator_local.hasNext())
          {
            bloqueJoin_local = mc.concatenarCadena(bloqueJoin_local, " and ");
          }
        }
      }
      if (mc.sonCadenasIguales(bloqueJoin_local, " where "))
      {
        bloqueJoin_local = "";
      }
      setBloqueJoin(bloqueJoin_local);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      bloqueJoin_local = null;
      nombreGrupoInformacionPrincipal_local = null;
      iterator_local = null;
      cadena_local = null;
    }
  }
  private boolean verificarOtrosValoresCampoConsulta(int pIdCampo, int pNivelConsulta, boolean pEsRestriccion)
  {
    boolean otrosValoresCampoConsulta_local = false;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    Campo campo_local = null;
    try
    {
      if (pEsRestriccion)
      {
        iterator_local = getListaConsultaRestricciones().iterator();
      }
      else
      {
        iterator_local = getListaConsulta().iterator();
      }
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (itemConsulta_local.getNivelConsulta() > pNivelConsulta && !itemConsulta_local.esOrdenadoPor())
        {
          campo_local = itemConsulta_local.getCampoDesde();
          if (campo_local.getIdCampo() == pIdCampo)
          {
            otrosValoresCampoConsulta_local = true;
            break;
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
      iterator_local = null;
      itemConsulta_local = null;
      campo_local = null;
    }
    return otrosValoresCampoConsulta_local;
  }
  private ListaCadenas obtenerOtrosValoresCampoConsulta(int pIdCampo, boolean pEsCampoCadenaTabla, boolean pEsRestriccion)
  {
    ListaCadenas listaCadenas_local = null;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    Campo campo_local = null;
    try
    {
      listaCadenas_local = new ListaCadenas();
      if (pEsRestriccion)
      {
        iterator_local = getListaConsultaRestricciones().iterator();
      }
      else
      {
        iterator_local = getListaConsulta().iterator();
      }
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (!itemConsulta_local.esOrdenadoPor())
        {
          campo_local = itemConsulta_local.getCampoHasta();
          if (!pEsCampoCadenaTabla)
          {
            campo_local = itemConsulta_local.getCampoDesde();
          }
          if (campo_local.getIdCampo() == pIdCampo &&
            campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
          {
            listaCadenas_local.adicionar(campo_local.getValorCampo().toString());
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
      iterator_local = null;
      itemConsulta_local = null;
      campo_local = null;
    }
    return listaCadenas_local;
  }
  private String conformarCadenaMultipleLIKE(ListaCadenas pListaValoresCampos, String pNombreCampo, boolean pEsRestriccion)
  {
    String cadenaMultipleLIKE_local = "";
    String cadenaAuxiliar_local = null;
    String operador_local = null;
    String conector_local = null;
    Iterator iterator_local = null;
    String cadena_local = null;
    if (pListaValoresCampos == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaMultipleLIKE_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaMultipleLIKE_local;
    }
    try
    {
      cadenaAuxiliar_local = "";
      operador_local = " like ";
      conector_local = " or ";
      if (pEsRestriccion && !esSoloRegistrosDonde())
      {
        operador_local = " not like ";
        conector_local = " and ";
      }
      iterator_local = pListaValoresCampos.iterator();
      while (iterator_local.hasNext())
      {
        cadena_local = (String)iterator_local.next();
        cadenaAuxiliar_local = mc.concatenarCadena(cadenaAuxiliar_local, ap.conformarCadenasFiltroPorPalabras(cadena_local, pNombreCampo, operador_local));
        if (iterator_local.hasNext())
        {
          cadenaAuxiliar_local = mc.concatenarCadena(cadenaAuxiliar_local, conector_local);
        }
      }
      cadenaMultipleLIKE_local = mc.concatenarCadena(cadenaMultipleLIKE_local, mc.colocarEntreParentesis(cadenaAuxiliar_local));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      cadena_local = null;
      iterator_local = null;
      operador_local = null;
      cadenaAuxiliar_local = null;
    }
    return cadenaMultipleLIKE_local;
  }
  private String conformarCadenaIN(ListaCadenas pListaValoresCampos, Campo pCampo, boolean pEsRestriccion)
  {
    String cadenaIN_local = "";
    String cadenaAuxiliar_local = null;
    Iterator iterator_local = null;
    String cadena_local = null;
    if (pListaValoresCampos == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaIN_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaIN_local;
    }
    try
    {
      cadenaIN_local = " in ";
      if (pEsRestriccion && !esSoloRegistrosDonde())
      {
        cadenaIN_local = " not in ";
      }
      cadenaAuxiliar_local = "";
      iterator_local = pListaValoresCampos.iterator();
      while (iterator_local.hasNext())
      {
        cadena_local = (String)iterator_local.next();
        if (pCampo.esTipoDatoTexto())
        {
          cadena_local = mc.colocarEntreComillas(cadena_local);
        }
        cadenaAuxiliar_local = mc.concatenarCadena(cadenaAuxiliar_local, cadena_local);
        if (iterator_local.hasNext())
        {
          cadenaAuxiliar_local = mc.concatenarCadena(cadenaAuxiliar_local, String.valueOf(','));
        }
      }
      cadenaIN_local = mc.concatenarCadena(cadenaIN_local, mc.colocarEntreParentesis(cadenaAuxiliar_local));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      cadenaAuxiliar_local = null;
      iterator_local = null;
      cadena_local = null;
    }
    return cadenaIN_local;
  }
  private String complementarBloqueCondiciones(ListaCadenas pListaBloqueCondiciones)
  {
    String bloqueCondiciones_local = "";
    Iterator iterator_local = null;
    String cadena_local = null;
    if (pListaBloqueCondiciones == ConstantesGeneral.VALOR_NULO)
    {
      return bloqueCondiciones_local;
    }
    try
    {
      if (pListaBloqueCondiciones.contarElementos() > 0)
      {
        iterator_local = pListaBloqueCondiciones.iterator();
        while (iterator_local.hasNext())
        {
          cadena_local = (String)iterator_local.next();
          bloqueCondiciones_local = mc.concatenarCadena(bloqueCondiciones_local, cadena_local);
          if (iterator_local.hasNext())
          {
            bloqueCondiciones_local = mc.concatenarCadena(bloqueCondiciones_local, " and ");
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
      iterator_local = null;
      cadena_local = null;
    }
    return bloqueCondiciones_local;
  }
  private String conformarBloqueCondiciones(boolean pEsRestriccion)
  {
    String bloqueCondiciones_local = "";
    boolean esValorDesdeNulo_local = false;
    boolean esValorHastaNulo_local = false;
    boolean esValorCampoNulo_local = false;
    boolean esCampoCadenaTablaEnlazado_local = false;
    boolean existenOtrosValoresCampoConsulta_local = false;
    String bloqueCondicionesAuxiliar_local = null;
    String operador_local = null;
    String valorCampoDesde_local = null;
    String valorCampoHasta_local = null;
    String tipoDato_local = null;
    ListaCadenas listaCadenas_local = null;
    ListaCadenas listaBloqueCondiciones_local = null;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    Campo campoDesde_local = null;
    Campo campoHasta_local = null;
    ListaCadenas listaValoresCampo_local = null;
    boolean esCampoTablaEnlazado_local = false;
    boolean omitirOpcionConsulta_local = false;
    ManejadorVariablesSistema manejadorVariablesSistema_local = null;
    try
    {
      manejadorVariablesSistema_local = new ManejadorVariablesSistema();
      manejadorVariablesSistema_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      manejadorVariablesSistema_local.setMotorAplicacion(getMotorAplicacion());
      manejadorVariablesSistema_local.setUsuarioActual(getUsuario());
      manejadorVariablesSistema_local.setIdSesion(getIdSesion());
      listaCadenas_local = new ListaCadenas();
      listaBloqueCondiciones_local = new ListaCadenas();
      if (pEsRestriccion)
      {
        iterator_local = getListaConsultaRestricciones().iterator();
      }
      else
      {
        iterator_local = getListaConsulta().iterator();
      }
      while (iterator_local.hasNext())
      {
        bloqueCondicionesAuxiliar_local = "";
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (!itemConsulta_local.esOrdenadoPor())
        {
          campoDesde_local = itemConsulta_local.getCampoDesde();
          campoHasta_local = itemConsulta_local.getCampoHasta();
          if (listaCadenas_local.indexOf(String.valueOf(campoDesde_local.getIdCampo())) == -1)
          {
            listaCadenas_local.adicionar(String.valueOf(campoDesde_local.getIdCampo()));
            operador_local = "";
            valorCampoDesde_local = "";
            valorCampoHasta_local = "";
            tipoDato_local = campoDesde_local.getFormatoCampo().getTipoDato();
            if (campoDesde_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
            {
              valorCampoDesde_local = mc.convertirAMayusculas(mc.borrarEspaciosLaterales(campoDesde_local.getValorCampo().toString()));
            }
            if (campoHasta_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
            {
              valorCampoHasta_local = mc.convertirAMayusculas(mc.borrarEspaciosLaterales(campoHasta_local.getValorCampo().toString()));
            }
            if (ap.esVariableSistema(valorCampoDesde_local) &&
              manejadorVariablesSistema_local.obtenerValorVariableSistema(valorCampoDesde_local) != ConstantesGeneral.VALOR_NULO)
            {
              valorCampoDesde_local = manejadorVariablesSistema_local.obtenerValorVariableSistema(valorCampoDesde_local).getValorVariable().toString();
            }
            if (ap.esVariableSistema(valorCampoHasta_local) &&
              manejadorVariablesSistema_local.obtenerValorVariableSistema(valorCampoHasta_local) != ConstantesGeneral.VALOR_NULO)
            {
              valorCampoHasta_local = manejadorVariablesSistema_local.obtenerValorVariableSistema(valorCampoHasta_local).getValorVariable().toString();
            }
            esValorDesdeNulo_local = (mc.sonCadenasIguales(valorCampoDesde_local, "") || (campoDesde_local.esTipoDatoNumerico() && mc.sonCadenasIguales(valorCampoDesde_local, String.valueOf('0'))));
            esValorHastaNulo_local = (mc.sonCadenasIguales(valorCampoHasta_local, "") || (campoDesde_local.esTipoDatoNumerico() && mc.sonCadenasIguales(valorCampoHasta_local, String.valueOf('0'))));
            esValorCampoNulo_local = (esValorDesdeNulo_local && esValorHastaNulo_local);
            esCampoCadenaTablaEnlazado_local = (campoDesde_local.esTipoDatoTabla() || campoDesde_local.esTipoDatoTexto() || campoDesde_local.esCampoEnlazado());
            esCampoTablaEnlazado_local = (campoDesde_local.esTipoDatoTabla() || campoDesde_local.esCampoEnlazado());
            existenOtrosValoresCampoConsulta_local = verificarOtrosValoresCampoConsulta(campoDesde_local.getIdCampo(), itemConsulta_local.getNivelConsulta(), pEsRestriccion);
            omitirOpcionConsulta_local = ((esCampoTablaEnlazado_local && mc.sonCadenasIguales(valorCampoHasta_local, String.valueOf(-1))) || (esValorDesdeNulo_local && esValorHastaNulo_local));
            if (!omitirOpcionConsulta_local)
            {
              if (!existenOtrosValoresCampoConsulta_local)
              {
                if (!esValorCampoNulo_local)
                {
                  if (mc.sonCadenasIguales(tipoDato_local, "F"))
                  {
                    if (!esValorDesdeNulo_local)
                    {
                      valorCampoDesde_local = mc.convertirFormatoFechaDDMMAAAA(valorCampoDesde_local);
                      valorCampoDesde_local = mc.colocarEntreComillas(valorCampoDesde_local);
                    }
                    if (!esValorHastaNulo_local)
                    {
                      valorCampoHasta_local = mc.convertirFormatoFechaDDMMAAAA(valorCampoHasta_local);
                      valorCampoHasta_local = mc.colocarEntreComillas(valorCampoHasta_local);
                    }
                    if (mc.sonCadenasIguales(valorCampoDesde_local, valorCampoHasta_local))
                    {
                      operador_local = " = ";
                      if (pEsRestriccion && !esSoloRegistrosDonde())
                      {
                        operador_local = " <> ";
                      }
                      if (!esValorDesdeNulo_local)
                      {
                        operador_local = mc.concatenarCadena(operador_local, valorCampoDesde_local);
                      }
                      else
                      {
                        operador_local = mc.concatenarCadena(operador_local, valorCampoHasta_local);
                      }
                    }
                    else if (!esValorDesdeNulo_local && !esValorHastaNulo_local)
                    {
                      operador_local = " between ";
                      if (pEsRestriccion && !esSoloRegistrosDonde())
                      {
                        operador_local = " not between ";
                      }
                      operador_local = mc.concatenarCadena(operador_local, valorCampoDesde_local);
                      operador_local = mc.concatenarCadena(operador_local, " and ");
                      operador_local = mc.concatenarCadena(operador_local, valorCampoHasta_local);
                    }
                    else
                    {
                      operador_local = " = ";
                      if (pEsRestriccion && !esSoloRegistrosDonde())
                      {
                        operador_local = " <> ";
                      }
                      if (!esValorDesdeNulo_local)
                      {
                        operador_local = mc.concatenarCadena(operador_local, valorCampoDesde_local);
                      }
                      else
                      {
                        operador_local = mc.concatenarCadena(operador_local, valorCampoHasta_local);
                      }
                    }
                  }
                  else if (campoDesde_local.esTipoDatoTexto())
                  {
                    operador_local = " like ";
                    if (pEsRestriccion && !esSoloRegistrosDonde())
                    {
                      operador_local = " not like ";
                    }
                    operador_local = ap.conformarCadenasFiltroPorPalabras(valorCampoHasta_local, campoDesde_local.conformarNombreCompuestoCampo(), operador_local);
                  }
                  else if (!esValorDesdeNulo_local)
                  {
                    if (!esValorHastaNulo_local)
                    {
                      operador_local = " between ";
                      if (pEsRestriccion && !esSoloRegistrosDonde())
                      {
                        operador_local = " not between ";
                      }
                      operador_local = mc.concatenarCadena(operador_local, valorCampoDesde_local);
                      operador_local = mc.concatenarCadena(operador_local, " and ");
                      operador_local = mc.concatenarCadena(operador_local, valorCampoHasta_local);
                    }
                    else
                    {
                      operador_local = " = ";
                      if (pEsRestriccion && !esSoloRegistrosDonde())
                      {
                        operador_local = " <> ";
                      }
                      operador_local = mc.concatenarCadena(operador_local, valorCampoDesde_local);
                    }
                  }
                  else if (!esValorHastaNulo_local)
                  {
                    operador_local = " = ";
                    if (pEsRestriccion && !esSoloRegistrosDonde())
                    {
                      operador_local = " <> ";
                    }
                    operador_local = mc.concatenarCadena(operador_local, valorCampoHasta_local);
                  }
                  else
                  {
                    operador_local = " isnull ";
                    if (pEsRestriccion && !esSoloRegistrosDonde())
                    {
                      operador_local = " is not null ";
                    }
                  }
                }
                else
                {
                  operador_local = " isnull ";
                  if (pEsRestriccion && !esSoloRegistrosDonde())
                  {
                    operador_local = " is not null ";
                  }
                }
              }
              else
              {
                listaValoresCampo_local = obtenerOtrosValoresCampoConsulta(campoDesde_local.getIdCampo(), esCampoCadenaTablaEnlazado_local, pEsRestriccion);
                if (campoDesde_local.esTipoDatoTexto())
                {
                  operador_local = conformarCadenaMultipleLIKE(listaValoresCampo_local, campoDesde_local.conformarNombreCompuestoCampo(), pEsRestriccion);
                }
                else
                {
                  operador_local = conformarCadenaIN(listaValoresCampo_local, campoDesde_local, pEsRestriccion);
                }
              }
              if (campoDesde_local.esTipoDatoTexto() && existenOtrosValoresCampoConsulta_local)
              {
                bloqueCondicionesAuxiliar_local = operador_local;
              }
              else if (campoDesde_local.esTipoDatoTexto() && !campoDesde_local.esTipoDatoFecha())
              {
                bloqueCondicionesAuxiliar_local = mc.concatenarCadena(bloqueCondicionesAuxiliar_local, operador_local);
              }
              else
              {
                bloqueCondicionesAuxiliar_local = mc.concatenarCadena(campoDesde_local.conformarNombreCompuestoCampo(), operador_local);
              }
              listaBloqueCondiciones_local.adicionar(bloqueCondicionesAuxiliar_local);
            }
          }
        }
      }
      bloqueCondiciones_local = mc.concatenarCadena(bloqueCondiciones_local, complementarBloqueCondiciones(listaBloqueCondiciones_local));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      operador_local = null;
      tipoDato_local = null;
      iterator_local = null;
      campoDesde_local = null;
      campoHasta_local = null;
      listaCadenas_local = null;
      itemConsulta_local = null;
      valorCampoDesde_local = null;
      valorCampoHasta_local = null;
      listaValoresCampo_local = null;
      listaBloqueCondiciones_local = null;
      bloqueCondicionesAuxiliar_local = null;
      manejadorVariablesSistema_local = null;
    }
    return bloqueCondiciones_local;
  }
  private int obtenerCantidadOrdenadoPor()
  {
    int cantidadOrdenadoPor_local = 0;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    try
    {
      iterator_local = getListaConsulta().iterator();
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (itemConsulta_local.esOrdenadoPor())
        {
          cantidadOrdenadoPor_local++;
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
      itemConsulta_local = null;
    }
    return cantidadOrdenadoPor_local;
  }
  private ListaConsulta obtenerCamposOrdenadoPor()
  {
    ListaConsulta listaConsultaOrdenadoPor_local = null;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    try
    {
      listaConsultaOrdenadoPor_local = new ListaConsulta();
      iterator_local = getListaConsulta().iterator();
      while (iterator_local.hasNext())
      {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (itemConsulta_local.esOrdenadoPor())
        {
          listaConsultaOrdenadoPor_local.adicionar(itemConsulta_local);
        }
      }
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }
    finally
    {
      iterator_local = null;
      itemConsulta_local = null;
    }
    return listaConsultaOrdenadoPor_local;
  }
  private void conformarBloqueOrderBy()
  {
    String bloqueOrderBy_local = "";
    String tipoOrdenamiento_local = null;
    String nombreGrupoInformacion_local = null;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    Campo campo_local = null;
    Campo campoValorUnico_local = null;
    boolean campoValorUnicoUtilizado_local = false;
    try
    {
      bloqueOrderBy_local = " order by ";
      campoValorUnico_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(getAplicacion().getIdAplicacion());
      if (obtenerCantidadOrdenadoPor() == 0)
      {
        if (campoValorUnico_local != ConstantesGeneral.VALOR_NULO && !campoValorUnico_local.getGrupoInformacion().esGrupoInformacionMultiple())
        {
          setBloqueOrderBy(mc.concatenarCadena(bloqueOrderBy_local, mc.concatenarCadena(campoValorUnico_local.getGrupoInformacion().getAplicacion().getNombreAplicacion(), '.' + campoValorUnico_local.getNombreCampo())));
        }
        else
        {
          setBloqueOrderBy(mc.concatenarCadena(bloqueOrderBy_local, ap.conformarNombreCampoLlavePrimaria(getAplicacion().getNombreAplicacion())));
        }
        return;
      }
      iterator_local = obtenerCamposOrdenadoPor().iterator();
      while (iterator_local.hasNext())
      {
        tipoOrdenamiento_local = " asc ";
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        campo_local = itemConsulta_local.getCampoHasta();
        nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getNombreGrupoInformacion();
        if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple())
        {
          nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
        }
        bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, nombreGrupoInformacion_local);
        bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, String.valueOf('.'));
        bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, campo_local.getNombreCampo());
        if (!itemConsulta_local.esOrdenAscendente())
        {
          tipoOrdenamiento_local = " desc ";
        }
        bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, tipoOrdenamiento_local);
        if (campoValorUnico_local != ConstantesGeneral.VALOR_NULO)
        {
          campoValorUnicoUtilizado_local = (campo_local.getIdCampo() == campoValorUnico_local.getIdCampo());
        }
        if (iterator_local.hasNext())
        {
          bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, String.valueOf(','));
        }
      }
      if (!campoValorUnicoUtilizado_local &&
        campoValorUnico_local != ConstantesGeneral.VALOR_NULO)
      {
        bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, String.valueOf(','));
        bloqueOrderBy_local = mc.concatenarCadena(bloqueOrderBy_local, mc.concatenarCadena(campoValorUnico_local.getGrupoInformacion().getAplicacion().getNombreAplicacion(), '.' + campoValorUnico_local.getNombreCampo()));
      }
      setBloqueOrderBy(bloqueOrderBy_local);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      bloqueOrderBy_local = null;
      iterator_local = null;
      itemConsulta_local = null;
      tipoOrdenamiento_local = null;
      campo_local = null;
      campoValorUnico_local = null;
    }
  }
  private String descomponerCadenaRestriccionAplicacion(String pCadenaRestriccion, boolean pEsNombreCampo)
  {
    String cadenaRestriccionAplicacion_local = "";
    int posicionIgual_local = -1;
    if (pCadenaRestriccion == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaRestriccionAplicacion_local;
    }
    try
    {
      posicionIgual_local = mc.obtenerPosicionSubCadena(pCadenaRestriccion, String.valueOf('='));
      if (posicionIgual_local != -1)
      {
        if (pEsNombreCampo)
        {
          cadenaRestriccionAplicacion_local = mc.obtenerSubCadena(pCadenaRestriccion, 0, posicionIgual_local);
        }
        else
        {
          cadenaRestriccionAplicacion_local = mc.obtenerSubCadena(pCadenaRestriccion, posicionIgual_local + 1, mc.obtenerLongitudCadena(pCadenaRestriccion));
        }
        if (!mc.esCadenaVacia(cadenaRestriccionAplicacion_local))
        {
          cadenaRestriccionAplicacion_local = ap.obtenerContenidoEntreComillas(cadenaRestriccionAplicacion_local);
        }
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaRestriccionAplicacion_local;
  }
  public ListaConsulta obtenerListaConsultaRestriccionesAplicacion(int pTipoUsuario, Aplicacion pAplicacion, boolean pEsSoloRegistrosDonde)
  {
    ListaConsulta listaConsulta_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterator_local = null;
    String cadenaRestriccion_local = null;
    String etiquetaCampo_local = null;
    String valorCampo_local = null;
    Campo campoDesde_local = null;
    Campo campoHasta_local = null;
    ItemConsulta itemConsulta_local = null;
    Tabla tabla_local = null;
    String tipoDato_local = null;
    ManejadorPermisoUsuario manejadorPermisoUsuario_local = null;
    Campo campo_local = null;
    int nivelConsulta_local = 0;
    ManejadorVariablesSistema manejadorVariablesSistema_local = null;
    try
    {
      manejadorVariablesSistema_local = new ManejadorVariablesSistema();
      manejadorVariablesSistema_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
      manejadorVariablesSistema_local.setMotorAplicacion(getMotorAplicacion());
      manejadorVariablesSistema_local.setUsuarioActual(getUsuario());
      manejadorVariablesSistema_local.setIdSesion(getIdSesion());
      manejadorPermisoUsuario_local = new ManejadorPermisoUsuario(pTipoUsuario);
      manejadorPermisoUsuario_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
      manejadorPermisoUsuario_local.setMotorAplicacion(getMotorAplicacion());
      listaConsulta_local = new ListaConsulta();
      listaCadenas_local = manejadorPermisoUsuario_local.obtenerListaRestriccionesAplicacion(pAplicacion, pEsSoloRegistrosDonde);
      iterator_local = listaCadenas_local.iterator();
      while (iterator_local.hasNext())
      {
        cadenaRestriccion_local = (String)iterator_local.next();
        etiquetaCampo_local = descomponerCadenaRestriccionAplicacion(cadenaRestriccion_local, true);
        valorCampo_local = descomponerCadenaRestriccionAplicacion(cadenaRestriccion_local, false);
        campo_local = getMotorAplicacion().obtenerCampoConsultaPorEtiqueta(pAplicacion.getIdAplicacion(), etiquetaCampo_local);
        if (campo_local != ConstantesGeneral.VALOR_NULO)
        {
          nivelConsulta_local++;
          campoDesde_local = campo_local;
          campoHasta_local = campo_local;
          tipoDato_local = campoDesde_local.getFormatoCampo().getTipoDato();
          if (ap.esVariableSistema(valorCampo_local))
          {
            valorCampo_local = manejadorVariablesSistema_local.obtenerValorVariableSistema(valorCampo_local).getValorVariable().toString();
          }
          if (campoDesde_local.esTipoDatoTabla())
          {
            tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(tipoDato_local));
            valorCampo_local = String.valueOf(getAdministradorBaseDatosAplicacion().obtenerIdValorTabla(tabla_local.getNombreTabla(), valorCampo_local));
          }
          if (campoDesde_local.esCampoEnlazado())
          {
            valorCampo_local = String.valueOf(getManejadorCampoEnlazado().obtenerIdValorCampoEnlazado(campoDesde_local.getEnlaceCampo().getEnlazado(), valorCampo_local));
          }
          campoDesde_local.setValorCampo(valorCampo_local);
          campoHasta_local.setValorCampo(valorCampo_local);
          itemConsulta_local = new ItemConsulta(campoDesde_local, campoHasta_local, nivelConsulta_local, false, false, false);
          listaConsulta_local.adicionar(itemConsulta_local);
        }
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      listaCadenas_local = null;
      iterator_local = null;
      cadenaRestriccion_local = null;
      etiquetaCampo_local = null;
      valorCampo_local = null;
      campoDesde_local = null;
      campoHasta_local = null;
      itemConsulta_local = null;
      tabla_local = null;
      tipoDato_local = null;
      manejadorPermisoUsuario_local = null;
      campo_local = null;
      manejadorVariablesSistema_local = null;
    }
    return listaConsulta_local;
  }
  public String obtenerConsultaSQLPrincipal()
  {
    String consultaSQLPrincipal_local = "";
    boolean existeJoin_local = false;
    Campo campo_local = null;
    boolean noPermitirRealizarConsulta_local = false;
    try
    {
      consultaSQLPrincipal_local = "select " + getAplicacion().getNombreAplicacion() + String.valueOf('.') + ap.conformarNombreCampoLlavePrimaria(getAplicacion().getNombreAplicacion());
      getListaConsulta().validarOpcionesConsulta();
      extraerListaOrdenadoPor();
      extraerGruposInformacion();
      if (getListaConsulta().contarElementos() > 0 || getListaConsultaRestricciones().contarElementos() > 0)
      {
        consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, complementarBloqueSelect());
        conformarBloqueFrom();
        consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, getBloqueFrom());
        conformarBloqueJoin();
        consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, getBloqueJoin());
        existeJoin_local = !mc.esCadenaVacia(getBloqueJoin());
        setBloqueCondiciones(conformarBloqueCondiciones(false));
        if (existeJoin_local && !mc.esCadenaVacia(getBloqueCondiciones()))
        {
          consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, " and ");
        }
        else if (!mc.esCadenaVacia(getBloqueCondiciones()))
        {
          consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, " where ");
        }
        if (!esPermitirConsultaSinOpciones())
        {
          noPermitirRealizarConsulta_local = (getListaConsulta().contarElementos() > 0 && mc.esCadenaVacia(getBloqueCondiciones()));
        }
        if (!noPermitirRealizarConsulta_local)
        {
          consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, getBloqueCondiciones());
          setBloqueRestriccionesAplicacion(conformarBloqueCondiciones(true));
          if (!mc.esCadenaVacia(getBloqueRestriccionesAplicacion()))
          {
            if (existeJoin_local || !mc.esCadenaVacia(getBloqueCondiciones()))
            {
              consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, " and ");
            }
            else
            {
              consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, " where ");
            }
            consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, mc.colocarEntreParentesis(getBloqueRestriccionesAplicacion()));
          }
          conformarBloqueOrderBy();
          consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, getBloqueOrderBy());
        }
        else
        {
          consultaSQLPrincipal_local = "";
        }
      }
      else
      {
        consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, " from ");
        consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, getAplicacion().getNombreAplicacion());
        consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, " order by ");
        campo_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(getAplicacion().getIdAplicacion());
        if (campo_local != ConstantesGeneral.VALOR_NULO && !campo_local.getGrupoInformacion().esGrupoInformacionMultiple())
        {
          consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, campo_local.getNombreCampo());
        }
        else
        {
          consultaSQLPrincipal_local = mc.concatenarCadena(consultaSQLPrincipal_local, ap.conformarNombreCampoLlavePrimaria(getAplicacion().getNombreAplicacion()));
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
    }
    return consultaSQLPrincipal_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\consultasBaseDatos\ConsultaSQLPrincipal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */