package com.sisnet.aplicacion.generadores;

import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorHabilitacionCampos;
import com.sisnet.aplicacion.manejadores.ManejadorPermisoUsuario;
import com.sisnet.aplicacion.manejadores.ManejadorRequest;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.ManejadorSesion;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorInformacionRecalculable;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ConexionBaseDatos;
import com.sisnet.baseDatos.ConexionPostgres;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesMensajesAplicacion;
import com.sisnet.controlesHTML.Formulario;
import com.sisnet.objetosManejo.AtributoSesion;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.ListaBotones;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.ListaParametrosRedireccion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import com.sisnet.objetosManejo.manejoPaginaJsp.Boton;
import com.sisnet.objetosManejo.manejoPaginaJsp.Pagina;
import com.sisnet.parches.Parche003;
import com.sisnet.parches.Parche004;
import com.sisnet.utilidades.CargadorPropiedades;
import com.sisnet.version.Version01;
import com.sisnet.version.Version02;
import com.sisnet.version.Version03;
import com.sisnet.version.Version04;
import com.sisnet.version.Version05;
import com.sisnet.version.Version06;
import com.sisnet.version.Version08;
import com.sisnet.version.Version09;
import com.sisnet.version.Version10;
import com.sisnet.version.Version11;
import com.sisnet.version.Version12;
import com.sisnet.version.Version14;
import com.sisnet.version.Version16;
import com.sisnet.version.Version17;
import com.sisnet.version.Version18;
import com.sisnet.version.Version19;
import com.sisnet.version.Version21;
import com.sisnet.version.Version22;
import com.sisnet.version.Version23;
import com.sisnet.version.Version27;
import com.sisnet.version.Version28;
import com.sisnet.version.Version29;
import com.sisnet.version.Version30;
import com.sisnet.version.Version32;
import com.sisnet.version.Version33;
import com.sisnet.version.Version36;
import com.sisnet.version.Version37;
import com.sisnet.version.Version38;
import com.sisnet.version.Version39;
import com.sisnet.version.Version40;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Iterator;
public class GeneradorPagina
{
    private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
    protected static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
    private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
    private static CargadorPropiedades cp = CargadorPropiedades.getCargadorPropiedades();
    private GeneradorComponentesHtml aGeneradorComponentesHtml;
    private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
    private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
    private ManejadorResultadoConsultaSQL aResultadoConsultaSQL;
    private ManejadorInformacionRecalculable aManejadorInformacionRecalculable;
    private ManejadorPermisoUsuario aManejadorPermisoUsuario;
    private ManejadorRequest aManejadorRequest;
    private ManejadorSesion aManejadorSesion;
    private ManejadorHabilitacionCampos aManejadorHabilitacionCampos;
    private int pTipoUsuario;
    public GeneradorPagina(int pTipoUsuario)
    {
    	this.setTipoUsuario(pTipoUsuario);
        setGeneradorComponentesHtml(new GeneradorComponentesHtml());
        setResultadoConsultaSQL(new ManejadorResultadoConsultaSQL());
        setManejadorInformacionRecalculable(new ManejadorInformacionRecalculable());
        //setManejadorPermisoUsuario(new ManejadorPermisoUsuario(pTipoUsuario));
        setManejadorHabilitacionCampos(new ManejadorHabilitacionCampos());
    }
    public GeneradorComponentesHtml getGeneradorComponentesHtml()
    {
        return this.aGeneradorComponentesHtml;
    }
    public void setGeneradorComponentesHtml(GeneradorComponentesHtml pGeneradorComponentesHtml)
    {
        this.aGeneradorComponentesHtml = pGeneradorComponentesHtml;
    }
    public AdministradorBaseDatos getAdministradorBaseDatosSisnet()
    {
        return this.aAdministradorBaseDatosSisnet;
    }
    public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet)
    {
        this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
        getResultadoConsultaSQL().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
        getManejadorInformacionRecalculable().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
        //getManejadorPermisoUsuario().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
        getManejadorHabilitacionCampos().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
    }
    public AdministradorBaseDatos getAdministradorBaseDatosAplicacion()
    {
        return this.aAdministradorBaseDatosAplicacion;
    }
    public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion)
    {
        this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
        getResultadoConsultaSQL().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
        getManejadorInformacionRecalculable().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
        getManejadorHabilitacionCampos().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
    }
    public ManejadorResultadoConsultaSQL getResultadoConsultaSQL()
    {
        return this.aResultadoConsultaSQL;
    }
    public void setResultadoConsultaSQL(ManejadorResultadoConsultaSQL pResultadoConsultaSQL)
    {
        this.aResultadoConsultaSQL = pResultadoConsultaSQL;
    }
    public ManejadorInformacionRecalculable getManejadorInformacionRecalculable()
    {
        return this.aManejadorInformacionRecalculable;
    }
    public void setManejadorInformacionRecalculable(ManejadorInformacionRecalculable pManejadorInformacionRecalculable)
    {
        this.aManejadorInformacionRecalculable = pManejadorInformacionRecalculable;
    }
    public ManejadorPermisoUsuario getManejadorPermisoUsuario()
    {
    	if(this.aManejadorPermisoUsuario == null)
    	{
    		this.aManejadorPermisoUsuario = ManejadorPermisoUsuario.getManejadorPermisoUsuario(this.getTipoUsuario(),
    				this.getAdministradorBaseDatosSisnet(), this.getManejadorSesion().obtenerMotorAplicacion());
    	}
        return this.aManejadorPermisoUsuario;
    }
    public void setManejadorPermisoUsuario(ManejadorPermisoUsuario pManejadorPermisoUsuario)
    {
        this.aManejadorPermisoUsuario = pManejadorPermisoUsuario;
    }
    public ManejadorRequest getManejadorRequest()
    {
        return this.aManejadorRequest;
    }
    public void setManejadorRequest(ManejadorRequest pManejadorRequest)
    {
        this.aManejadorRequest = pManejadorRequest;
    }
    public ManejadorSesion getManejadorSesion()
    {
        return this.aManejadorSesion;
    }
    public void setManejadorSesion(ManejadorSesion pManejadorSesion)
    {
        this.aManejadorSesion = pManejadorSesion;
        getManejadorInformacionRecalculable().setMotorAplicacion(pManejadorSesion.obtenerMotorAplicacion());
        //getManejadorPermisoUsuario().setMotorAplicacion(pManejadorSesion.obtenerMotorAplicacion());
    }
    public ManejadorHabilitacionCampos getManejadorHabilitacionCampos()
    {
        return this.aManejadorHabilitacionCampos;
    }
    public void setManejadorHabilitacionCampos(ManejadorHabilitacionCampos pManejadorHabilitacionCampos)
    {
        this.aManejadorHabilitacionCampos = pManejadorHabilitacionCampos;
    }
    private String validarCampoBooleanoInvisible(String pNombreCampo, ListaCampo pListaCampo)
    {
        String evento_local = "";
        boolean estiloInvisible_local = false;
        String valorCampo_local = null;

        if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
        {
            return evento_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return evento_local;
        }

        try
        {
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldtieneplantilla"))
            {
                evento_local = mc.concatenarCadena(evento_local, ConstantesGeneral.const_EventoMostrarAplicacionPlantilla);
                estiloInvisible_local = !mc.sonCadenasIguales(pListaCampo.obtenerValorCampo("fldtipodato"), "DD");
            }

            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldopciondesconocido"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldenlazado");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 0);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldesrecalculable"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(1);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 1);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldborrarvalornohabilitado"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldhabilitadopor");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 0);
            }

            if (estiloInvisible_local)
            {
                evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            valorCampo_local = null;
        }

        return evento_local;
    }
    private String insertarCampoTipoHabilitadoPor(Campo pCampo, String pHabilitadoPorSeleccionado, String pEvento, int pIdAplicacion)
    {
        String campoHabilitadoPor_local = "";
        ListaGeneral listaCamposTipoTabla_local = null;
        ListaGeneral listaCamposHabilitan_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return campoHabilitadoPor_local;
        }
        if (pHabilitadoPorSeleccionado == ConstantesGeneral.VALOR_NULO)
        {
            return campoHabilitadoPor_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return campoHabilitadoPor_local;
        }

        try
        {
            listaCamposHabilitan_local = new ListaGeneral();
            listaCamposHabilitan_local.adicionar("NORMAL", String.valueOf(0), mc.sonCadenasIguales(String.valueOf(0), pHabilitadoPorSeleccionado));

            listaCamposTipoTabla_local = getAdministradorBaseDatosSisnet().obtenerListaCamposTipoTablaAplicacion(pIdAplicacion, false);

            iterador_local = listaCamposTipoTabla_local.iterator();
            while (iterador_local.hasNext())
            {
                itemLista_local = (ItemLista)iterador_local.next();
                listaCamposHabilitan_local.adicionar(itemLista_local.getNombreItem(), itemLista_local.getValorItem(), mc.sonCadenasIguales(itemLista_local.getValorItem(), pHabilitadoPorSeleccionado));
            }

            campoHabilitadoPor_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaCamposHabilitan_local, pEvento, "center", false, false);

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaCamposHabilitan_local = null;
            listaCamposTipoTabla_local = null;
            iterador_local = null;
            itemLista_local = null;
        }

        return campoHabilitadoPor_local;
    }
    private String insertarCampoTipoListaDependiente(Campo pCampo, String pListaDependienteSeleccionado, String pEvento, int pIdAplicacion)
    {
        String campoListaDependiente_local = "";
        ListaGeneral listaCamposTipoTabla_local = null;
        ListaGeneral listaCamposListaDepende_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return campoListaDependiente_local;
        }
        if (pListaDependienteSeleccionado == ConstantesGeneral.VALOR_NULO)
        {
            return campoListaDependiente_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return campoListaDependiente_local;
        }

        try
        {
            listaCamposListaDepende_local = new ListaGeneral();
            listaCamposListaDepende_local.adicionar("NO DEPENDIENTE ", String.valueOf(0), mc.sonCadenasIguales(String.valueOf(0), pListaDependienteSeleccionado));

            listaCamposTipoTabla_local = getAdministradorBaseDatosSisnet().obtenerListaCamposTipoTablaAplicacion(pIdAplicacion, false);

            iterador_local = listaCamposTipoTabla_local.iterator();
            while (iterador_local.hasNext())
            {
                itemLista_local = (ItemLista)iterador_local.next();
                listaCamposListaDepende_local.adicionar(itemLista_local.getNombreItem(), itemLista_local.getValorItem(), mc.sonCadenasIguales(itemLista_local.getValorItem(), pListaDependienteSeleccionado));
            }

            campoListaDependiente_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaCamposListaDepende_local, pEvento, "center", false, false);

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaCamposListaDepende_local = null;
            listaCamposTipoTabla_local = null;
            iterador_local = null;
            itemLista_local = null;
        }

        return campoListaDependiente_local;
    }
    private String insertarCampoTipoEnlazado(Campo pCampo, String pTipoEnlazadoSeleccionado, String pEvento)
    {
        String tipoEnlazado_local = "";
        ListaAplicacion listaAplicacion_local = null;
        ListaGeneral listaCamposListaDepende_local = null;
        Iterator iterador_local = null;
        Aplicacion aplicacion_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return tipoEnlazado_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return tipoEnlazado_local;
        }

        try
        {
            listaCamposListaDepende_local = new ListaGeneral();
            listaCamposListaDepende_local.adicionar("NO ENLAZADO", String.valueOf(0), mc.sonCadenasIguales(String.valueOf(0), pTipoEnlazadoSeleccionado));

            listaAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(0, -1, false);

            iterador_local = listaAplicacion_local.iterator();
            while (iterador_local.hasNext())
            {
                aplicacion_local = (Aplicacion)iterador_local.next();
                listaCamposListaDepende_local.adicionar(aplicacion_local.getTituloAplicacion(), String.valueOf(aplicacion_local.getIdAplicacion()), mc.sonCadenasIguales(String.valueOf(aplicacion_local.getIdAplicacion()), pTipoEnlazadoSeleccionado));
            }

            tipoEnlazado_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaCamposListaDepende_local, pEvento, "center", false, false);

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            listaAplicacion_local = null;
            aplicacion_local = null;
        }

        return tipoEnlazado_local;
    }
    private String insertarCampoTipoDependienteEnlazado(Campo pCampo, int pDependienteEnlazadoSeleccionado, String pEvento)
    {
        String campoTipoDependienteEnlazado_local = "";
        ListaGeneral listaGeneral_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return campoTipoDependienteEnlazado_local;
        }
        if (Integer.valueOf(pDependienteEnlazadoSeleccionado) == ConstantesGeneral.VALOR_NULO)
        {
            return campoTipoDependienteEnlazado_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return campoTipoDependienteEnlazado_local;
        }

        try
        {
            listaGeneral_local = ap.obtenerListaOpcionesDependientesEnlazado(pDependienteEnlazadoSeleccionado);
            campoTipoDependienteEnlazado_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneral_local, pEvento, "center", false, false);

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneral_local = null;
        }

        return campoTipoDependienteEnlazado_local;
    }
    private String insertarCampoTipoCampoValor(Campo pCampo, ListaCampo pListaCampo, String pEvento)
    {
        String insertarLista_local = "";
        int tipoCampoCalculado_local = 1;
        int habilitadoPor_local = 0;
        int idGrupoInformacion_local = -1;
        String campoCalculado_local = null;
        String campoHabilitadoPor_local = null;
        String campoSeleccionado_local = null;
        ListaGeneral listaGeneralCampos_local = null;
        GrupoInformacion grupoInformacionHabilita_local = null;
        GrupoInformacion grupoInformacion_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            campoCalculado_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
            campoHabilitadoPor_local = pListaCampo.obtenerValorCampo("fldhabilitadopor");
            idGrupoInformacion_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion"));
            if (mc.esCadenaNumerica(campoCalculado_local, true))
            {
                tipoCampoCalculado_local = Integer.parseInt(campoCalculado_local);
            }
            else
            {
                tipoCampoCalculado_local = 1;
            }
            if (mc.esCadenaNumerica(campoHabilitadoPor_local, true))
            {
                habilitadoPor_local = Integer.parseInt(campoHabilitadoPor_local);
            }
            else
            {
                habilitadoPor_local = 0;
            }
            if (mc.esCadenaNumerica(String.valueOf(idGrupoInformacion_local), true))
            {
                grupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);
            }

            if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO)
            {
                campoSeleccionado_local = pCampo.getValorCampo().toString();
            }
            else
            {
                campoSeleccionado_local = "";
            }

            switch (tipoCampoCalculado_local)
            {
                case 2:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, false);

                    if (habilitadoPor_local != 0)
                    {
                        grupoInformacionHabilita_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(habilitadoPor_local).getGrupoInformacion();

                        if (grupoInformacionHabilita_local.esGrupoInformacionMultiple())
                        {
                            listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacionHabilita_local, false, false));

                            break;
                        }
                        if (grupoInformacion_local.esGrupoInformacionMultiple())
                        {
                            listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
                        }

                        break;
                    }

                    if (grupoInformacion_local.esGrupoInformacionMultiple())
                    {
                        listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
                    }
                    break;

                case 3:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(grupoInformacion_local, false, false);
                    break;

                case 4:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(grupoInformacion_local, false, false);
                    break;

                case 9:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(grupoInformacion_local, true, false);
                    break;

                case 10:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false);
                    break;

                case 13:
                case 14:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), true, false, false);
                    break;

                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), true, false, false);
                    break;

                case 22:
                case 23:
                case 24:
                case 28:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(grupoInformacion_local, false, false);
                    break;

                case 29:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), false, false, true);
                    break;

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
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(grupoInformacion_local.getAplicacion().getIdAplicacion(), false, true, false);
                    break;
            }

            if (listaGeneralCampos_local != ConstantesGeneral.VALOR_NULO)
            {
                listaGeneralCampos_local.seleccionarItemListaPorValor(campoSeleccionado_local);
            }
            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneralCampos_local, pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campoCalculado_local = null;
            grupoInformacion_local = null;
            campoSeleccionado_local = null;
            campoHabilitadoPor_local = null;
            listaGeneralCampos_local = null;
            grupoInformacionHabilita_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoCampoOrigenUno(Campo pCampo, ListaCampo pListaCampo, String pEvento)
    {
        String insertarLista_local = "";
        int idAplicacion_local = -1;
        int idGrupoInformacion_local = -1;
        int tipoCampoCalculado_local = 0;
        String campoCalculado_local = null;
        String campoSeleccionado_local = null;
        GrupoInformacion grupoInformacion_local = null;
        ListaGeneral listaGeneralCampos_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            campoCalculado_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
            idGrupoInformacion_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion"));
            if (mc.esCadenaNumerica(campoCalculado_local, true))
            {
                tipoCampoCalculado_local = Integer.parseInt(campoCalculado_local);
            }
            else
            {
                tipoCampoCalculado_local = 1;
            }
            if (mc.esCadenaNumerica(String.valueOf(idGrupoInformacion_local), true))
            {
                grupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);

                idAplicacion_local = grupoInformacion_local.getAplicacion().getIdAplicacion();
            }
            if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO)
            {
                campoSeleccionado_local = pCampo.getValorCampo().toString();
            }
            else
            {
                campoSeleccionado_local = "";
            }

            switch (tipoCampoCalculado_local)
            {
                case 5:
                case 6:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, true, false, false);

                    listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, false, true, false));
                    break;

                case 7:
                case 8:
                case 43:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, true, false, false);
                    break;

                case 11:
                case 12:
                case 21:
                case 27:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, false, false, false);
                    break;

                case 25:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, false);

                    if (grupoInformacion_local.esGrupoInformacionMultiple())
                    {
                        listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
                    }
                    break;

                case 26:
                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false);
                    break;
            }

            if (listaGeneralCampos_local != ConstantesGeneral.VALOR_NULO)
            {
                listaGeneralCampos_local.seleccionarItemListaPorValor(campoSeleccionado_local);
            }
            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneralCampos_local, pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campoCalculado_local = null;
            campoSeleccionado_local = null;
            grupoInformacion_local = null;
            listaGeneralCampos_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoCampoOrigenDos(Campo pCampo, ListaCampo pListaCampo, String pEvento)
    {
        String insertarLista_local = "";
        int idAplicacion_local = -1;
        int idGrupoInformacion_local = -1;
        int tipoCampoCalculado_local = 0;
        int idCampoOrigenUno_local = -1;
        boolean esGrupoMultiple_local = false;
        String campoCalculado_local = null;
        String campoSeleccionado_local = null;
        ListaGeneral listaGeneralCampos_local = null;
        GrupoInformacion grupoInformacion_local = null;
        Campo campoOrigenUno_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            idGrupoInformacion_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion"));
            campoCalculado_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
            idCampoOrigenUno_local = -1;

            if (!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldidcampoorigenuno")))
            {
                idCampoOrigenUno_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidcampoorigenuno"));

                if (idCampoOrigenUno_local != -1)
                {
                    if (mc.esCadenaNumerica(campoCalculado_local, true))
                    {
                        tipoCampoCalculado_local = Integer.parseInt(campoCalculado_local);
                    }
                    else
                    {
                        tipoCampoCalculado_local = 1;
                    }
                    if (mc.esCadenaNumerica(String.valueOf(idCampoOrigenUno_local), true))
                    {
                        campoOrigenUno_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(idCampoOrigenUno_local);
                        grupoInformacion_local = campoOrigenUno_local.getGrupoInformacion();
                    }
                    else
                    {
                        if (mc.esCadenaNumerica(String.valueOf(idGrupoInformacion_local), true))
                        {
                            grupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);
                        }

                        idCampoOrigenUno_local = -1;
                    }
                    esGrupoMultiple_local = grupoInformacion_local.esGrupoInformacionMultiple();
                    idAplicacion_local = grupoInformacion_local.getAplicacion().getIdAplicacion();
                    if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO)
                    {
                        campoSeleccionado_local = pCampo.getValorCampo().toString();
                    }
                    else
                    {
                        campoSeleccionado_local = "";
                    }

                    switch (tipoCampoCalculado_local)
                    {
                        case 5:
                        case 6:
                            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, true, false);

                            listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, true));

                            if (esGrupoMultiple_local)
                            {
                                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false));

                                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, true));
                            }
                            break;

                        case 7:
                        case 8:
                        case 43:
                            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, true, false);

                            if (esGrupoMultiple_local)
                            {
                                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false));
                            }
                            break;

                        case 11:
                        case 12:
                        case 21:
                        case 27:
                            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(idAplicacion_local, false, false, false);
                            break;

                        case 25:
                            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(grupoInformacion_local, false, false);

                            if (grupoInformacion_local.esGrupoInformacionMultiple())
                            {
                                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, false, false));
                            }
                            break;

                        case 26:
                            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(grupoInformacion_local, true, false);
                            break;
                    }

                }
            }
            if (listaGeneralCampos_local != ConstantesGeneral.VALOR_NULO)
            {
                listaGeneralCampos_local.seleccionarItemListaPorValor(campoSeleccionado_local);
            }
            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneralCampos_local, pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
            campoOrigenUno_local = null;
            campoCalculado_local = null;
            grupoInformacion_local = null;
            campoSeleccionado_local = null;
        }

        return insertarLista_local;
    }
    private String validarCampoTextoInvisible(String pNombreCampo, ListaCampo pListaCampo)
    {
        String evento_local = "";
        boolean estiloInvisible_local = false;
        String valorCampo_local = null;

        if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
        {
            return evento_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return evento_local;
        }

        try
        {
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldformatocampoorigenuno"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldidcampoorigenuno");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 0);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldformatocampoorigendos"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldidcampoorigendos");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 0);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldformatocampocalculado"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldcampocalculado");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(1);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 1);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(pNombreCampo, "fldvalorfiltrado"))
            {
                valorCampo_local = pListaCampo.obtenerValorCampo("fldenlazado");
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                estiloInvisible_local = (Integer.parseInt(valorCampo_local) == 0);
            }
            if (estiloInvisible_local)
            {
                evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            valorCampo_local = null;
        }

        return evento_local;
    }
    private String insertarCampoTipoBloqueo(Campo pCampo, int pTipoBloqueoSeleccionado, String pEvento)
    {
        String campoTipoBloqueo_local = "";
        ListaGeneral listaGeneral_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return campoTipoBloqueo_local;
        }
        if (Integer.valueOf(pTipoBloqueoSeleccionado) == ConstantesGeneral.VALOR_NULO)
        {
            return campoTipoBloqueo_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return campoTipoBloqueo_local;
        }

        try
        {
            listaGeneral_local = ap.obtenerListaOpcionesTipoBloqueoUsuario(pTipoBloqueoSeleccionado);
            campoTipoBloqueo_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneral_local, pEvento, "center", false, false);

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneral_local = null;
        }

        return campoTipoBloqueo_local;
    }
    private String insertarCampoTipoAplicacion(String pEventosCampo, int pIdAplicacionSeleccionada, boolean pEsCampoOculto)
    {
        String insertarLista_local = "";
        ListaAplicacion listaAplicacion_local = null;
        ListaGeneral listaGeneralAplicacion_local = null;

        if (pEventosCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(3, -1, false);

            listaGeneralAplicacion_local = ap.obtenerListaGeneralAplicaciones(listaAplicacion_local, pIdAplicacionSeleccionada, false);

            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable("Aplicaci\u00f3n", mc.convertirAMayusculas("fldidaplicacion"), listaGeneralAplicacion_local, pEventosCampo, "center", pEsCampoOculto, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaAplicacion_local = null;
            listaGeneralAplicacion_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoAplicacionTabla(Campo pCampo, String pEventosCampo, int pIdAplicacionSeleccionada, boolean pEsCampoOculto)
    {
        String insertarLista_local = "";
        ListaAplicacion listaAplicacion_local = null;
        ListaGeneral listaGeneralAplicacion_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEventosCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(2, -1, false);

            listaGeneralAplicacion_local = ap.obtenerListaGeneralAplicaciones(listaAplicacion_local, pIdAplicacionSeleccionada, true);

            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneralAplicacion_local, pEventosCampo, "center", pEsCampoOculto, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaAplicacion_local = null;
            listaGeneralAplicacion_local = null;
        }

        return insertarLista_local;
    }
    protected String insertarCampoTipoUsuario(Campo pCampo, String pEvento, String pTipoUsuarioSeleccionado)
    {
        String insertarLista_local = "";
        ListaGeneral listaTipoUsuario_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pTipoUsuarioSeleccionado == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaTipoUsuario_local = getAdministradorBaseDatosSisnet().obtenerListaTiposUsuario();
            if (listaTipoUsuario_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaTipoUsuario_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    itemLista_local.setSeleccionado(mc.sonCadenasIguales(itemLista_local.getValorItem(), pTipoUsuarioSeleccionado));
                }
            }
            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaTipoUsuario_local, pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            itemLista_local = null;
            listaTipoUsuario_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoAplicacionPlantilla(Campo pCampo, int pTipoAplicacionSeleccionado, String pEvento)
    {
        String insertarLista_local = "";
        ListaGeneral listaGeneralAplicacion_local = null;
        ListaAplicacion listaAplicacion_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaAplicaciones(2, -1, false);

            listaGeneralAplicacion_local = ap.obtenerListaGeneralAplicaciones(listaAplicacion_local, pTipoAplicacionSeleccionado, true);

            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneralAplicacion_local, pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralAplicacion_local = null;
            listaAplicacion_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoEnlaceDepende(Campo pCampo, int pTipoEnlaceDependeSeleccionado, String pEvento)
    {
        String insertarLista_local = "";
        int valorItem_local = -1;
        ListaGeneral listaGeneral_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaGeneral_local = getAdministradorBaseDatosSisnet().obtenerListaCamposEnlazadosGrupoInformacion(pCampo.getGrupoInformacion(), pCampo.getIdCampo());

            if (listaGeneral_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaGeneral_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    valorItem_local = Integer.parseInt(itemLista_local.getValorItem());
                    itemLista_local.setSeleccionado((valorItem_local == pTipoEnlaceDependeSeleccionado));
                }
                insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneral_local, pEvento, "center", false, false));

            }

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            listaGeneral_local = null;
            itemLista_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoOrigenEnlace(Campo pCampo, int pTipoOrigenEnlaceSeleccionado, String pEvento, int pIdCampo)
    {
        String insertarLista_local = "";
        int valorItem_local = 0;
        int aplicacionEnlace_local = -1;
        int campoEnlaceDepende_local = -1;
        int idGrupoInformacionEnlace_local = -1;
        ListaGeneral listaGeneral_local = null;
        ListaCampo listaCampo_local = null;
        Iterator iterador_local = null;
        Campo campoEnlace_local = null;
        Campo campoEnlazado_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaGeneral_local = new ListaGeneral();
            aplicacionEnlace_local = -1;
            if (pIdCampo != -1)
            {
                campoEnlace_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(pIdCampo);
                if (campoEnlace_local != ConstantesGeneral.VALOR_NULO && campoEnlace_local.getEnlaceCampo().getCampoEnlaceDepende() != ConstantesGeneral.VALOR_NULO)
                {

                    campoEnlaceDepende_local = campoEnlace_local.getEnlaceCampo().getCampoEnlaceDepende().getIdCampo();
                    campoEnlazado_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(campoEnlaceDepende_local);
                    if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO)
                    {
                        aplicacionEnlace_local = campoEnlazado_local.getEnlaceCampo().getEnlazado().getIdAplicacion();
                        campoEnlazado_local = null;
                    }
                }
                campoEnlace_local = null;
            }
            idGrupoInformacionEnlace_local = getAdministradorBaseDatosSisnet().obtenerIdPrimerGrupoInformacionAplicacion(aplicacionEnlace_local);

            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposGrupoInformacion(idGrupoInformacionEnlace_local);

            iterador_local = listaCampo_local.iterator();
            while (iterador_local.hasNext())
            {
                campoEnlazado_local = (Campo)iterador_local.next();
                valorItem_local = campoEnlazado_local.getIdCampo();
                listaGeneral_local.adicionar(campoEnlazado_local.getEtiquetaCampo(), String.valueOf(valorItem_local), (valorItem_local == pTipoOrigenEnlaceSeleccionado));
            }

            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneral_local, pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneral_local = null;
            listaCampo_local = null;
            iterador_local = null;
            campoEnlace_local = null;
            campoEnlazado_local = null;
        }

        return insertarLista_local;
    }
    private ListaGeneral obtenerListaTiposDatoConTablas(String pTipoDatoSeleccionado)
    {
        ListaGeneral listaTiposDato_local = null;
        String valorItem_local = null;
        ListaGeneral listaTablasAplicacion_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pTipoDatoSeleccionado == ConstantesGeneral.VALOR_NULO)
        {
            return listaTiposDato_local;
        }

        try
        {
            listaTiposDato_local = ap.obtenerListaOpcionesTipoDato(pTipoDatoSeleccionado);
            listaTablasAplicacion_local = getAdministradorBaseDatosSisnet().obtenerListaTablasAplicacion();
            iterador_local = listaTablasAplicacion_local.iterator();
            while (iterador_local.hasNext())
            {
                itemLista_local = (ItemLista)iterador_local.next();
                valorItem_local = itemLista_local.getValorItem();
                listaTiposDato_local.adicionar(itemLista_local.getNombreItem(), valorItem_local, mc.sonCadenasIguales(valorItem_local, pTipoDatoSeleccionado));
            }

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            valorItem_local = null;
            listaTablasAplicacion_local = null;
            itemLista_local = null;
        }

        return listaTiposDato_local;
    }
    private String insertarCampoTipoDato(Campo pCampo, String pTipoDatoSeleccionado, String pEvento)
    {
        String insertarLista_local = "";

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pTipoDatoSeleccionado == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), obtenerListaTiposDatoConTablas(pTipoDatoSeleccionado), pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoFiltradoRegistrosEnlazados(Campo pCampo, int pTipoFiltradoRegistrosSeleccionado, String pEvento)
    {
        String insertarLista_local = "";

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), ap.obtenerListaOpcionesFiltradoRegistrosEnlazados(pTipoFiltradoRegistrosSeleccionado), pEvento, "center", false, false));

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoCampoOrigenFiltrado(Campo pCampo, int pIdCampoOrigenFiltradoSeleccionado, String pEvento, int pIdAplicacionActual)
    {
        String insertarLista_local = "";
        ListaCampo listaCampo_local = null;
        Campo campo_local = null;
        ListaGeneral listaGeneral_local = null;
        Iterator iterador_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposAplicacion(pIdAplicacionActual);
            if (listaCampo_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaCampo_local.iterator();
                listaGeneral_local = new ListaGeneral();
                listaGeneral_local.adicionar("Escoja un valor", String.valueOf(0), (pIdCampoOrigenFiltradoSeleccionado == 0));

                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    listaGeneral_local.adicionar(mc.concatenarCadena(campo_local.getEtiquetaCampo(), ' ' + mc.colocarEntreParentesis(campo_local.getGrupoInformacion().getDescripcionGrupoInformacion())), String.valueOf(campo_local.getIdCampo()), (campo_local.getIdCampo() == pIdCampoOrigenFiltradoSeleccionado));
                }

                insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneral_local, pEvento, "center", false, false));

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
            listaCampo_local = null;
            listaGeneral_local = null;
        }

        return insertarLista_local;
    }
    private String insertarCampoTipoCampoDestinoFiltrado(Campo pCampo, int pIdCampoOrigenFiltradoSeleccionado, String pEvento, ListaCampo pListaCampo)
    {
        String insertarLista_local = "";
        int idAplicacionEnlazada_local = -1;
        String aplicacionEnlazada_local = null;
        ListaCampo listaCampo_local = null;
        Campo campo_local = null;
        ListaGeneral listaGeneral_local = null;
        Iterator iterador_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pEvento == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarLista_local;
        }

        try
        {
            aplicacionEnlazada_local = pListaCampo.obtenerValorCampo("fldenlazado");
            if (mc.esCadenaNumerica(aplicacionEnlazada_local, true))
            {
                idAplicacionEnlazada_local = Integer.parseInt(aplicacionEnlazada_local);
            }
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposAplicacion(idAplicacionEnlazada_local);
            if (listaCampo_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaCampo_local.iterator();
                listaGeneral_local = new ListaGeneral();
                listaGeneral_local.adicionar("Escoja un valor", String.valueOf(0), (pIdCampoOrigenFiltradoSeleccionado == 0));

                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    listaGeneral_local.adicionar(mc.concatenarCadena(campo_local.getEtiquetaCampo(), ' ' + mc.colocarEntreParentesis(campo_local.getGrupoInformacion().getDescripcionGrupoInformacion())), String.valueOf(campo_local.getIdCampo()), (campo_local.getIdCampo() == pIdCampoOrigenFiltradoSeleccionado));
                }

                insertarLista_local = mc.concatenarCadena(insertarLista_local, getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo.getEtiquetaCampo(), pCampo.getNombreCampo(), listaGeneral_local, pEvento, "center", false, false));

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
            listaCampo_local = null;
            listaGeneral_local = null;
        }
        return insertarLista_local;
    }
    private boolean verificarEsCampoFiltrado(ListaCampo pListaCampo)
    {
        boolean esCampoFiltrado_local = false;
        String tipoFiltrado_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return esCampoFiltrado_local;
        }

        try
        {
            tipoFiltrado_local = pListaCampo.obtenerValorCampo("fldfiltradoregistrosenlazados");
            if (mc.esCadenaNumerica(tipoFiltrado_local, true))
            {
                esCampoFiltrado_local = (Integer.parseInt(tipoFiltrado_local) != 1 && Integer.parseInt(tipoFiltrado_local) != 0);
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return esCampoFiltrado_local;
    }
    private String insertarCamposModificables(Campo pCampo, ListaCampo pListaCampo, boolean pEsModificacion, String pUsuarioActual, int pIdAplicacionActual, int pIdCampo)
    {
        int longitudCampo_local = -1;
        boolean esLlavePrimaria_local = false;
        boolean esModificable_local = false;
        boolean esVisibleUsuarioPrincipal_local = false;
        String insertarCamposModificables_local = "";
        String valorCampo_local = null;
        String evento_local = null;
        String tipoCampo_local = null;
        String etiquetaCampo_local = null;
        String nombreCampo_local = null;
        String placeHolder_local = null;
        boolean esCampoOculto_local = false;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return insertarCamposModificables_local;
        }
        if (pUsuarioActual == ConstantesGeneral.VALOR_NULO)
        {
            return insertarCamposModificables_local;
        }

        try
        {
            valorCampo_local = "";
            evento_local = "";
            tipoCampo_local = "";
            etiquetaCampo_local = "";
            nombreCampo_local = "";
            tipoCampo_local = pCampo.getFormatoCampo().getTipoDato();
            etiquetaCampo_local = pCampo.getEtiquetaCampo();
            nombreCampo_local = pCampo.getNombreCampo();
            placeHolder_local = pCampo.getPlaceHolder();
            longitudCampo_local = pCampo.getFormatoCampo().getLongitudCampo();
            esLlavePrimaria_local = pCampo.getRestriccionCampo().esLlavePrimaria();
            esModificable_local = pCampo.esModificable();
            esVisibleUsuarioPrincipal_local = pCampo.esVisibleUsuarioPrincipal();

            if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO)
            {
                valorCampo_local = pCampo.getValorCampo().toString();
            }
            if (esLlavePrimaria_local || (!esModificable_local && !esVisibleUsuarioPrincipal_local) || mc.esCadenaVacia(etiquetaCampo_local))
            {

                if (mc.sonCadenasIguales(tipoCampo_local, "B") && mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(false);
                }
                esCampoOculto_local = true;
            }
            if (!esModificable_local && pEsModificacion && !esCampoOculto_local)
            {
                evento_local = " disabled ";
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "T") || mc.sonCadenasIguales(tipoCampo_local, "K") || mc.sonCadenasIguales(tipoCampo_local, "X"))
            {

                evento_local = mc.concatenarCadena(evento_local, validarCampoTextoInvisible(nombreCampo_local, pListaCampo));
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoTexto(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local, longitudCampo_local, "center", esCampoOculto_local, false, placeHolder_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "E") || mc.sonCadenasIguales(tipoCampo_local, "BB"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoNumeroEntero(etiquetaCampo_local, nombreCampo_local, longitudCampo_local, valorCampo_local, evento_local, "center", mc.sonCadenasIguales(tipoCampo_local, "BB"), esCampoOculto_local, false));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "R") || mc.sonCadenasIguales(tipoCampo_local, "GG"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoNumeroReal(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local, "center", mc.sonCadenasIguales(tipoCampo_local, "GG"), esCampoOculto_local, false));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "C"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoContrasena(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local, "400", esCampoOculto_local, placeHolder_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "B"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(false);
                }
                evento_local = mc.concatenarCadena(evento_local, validarCampoBooleanoInvisible(nombreCampo_local, pListaCampo));
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoCajaSeleccion(nombreCampo_local, etiquetaCampo_local, Boolean.parseBoolean(valorCampo_local), evento_local, 2, "left", esCampoOculto_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "D"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoTipoIdentificacion(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "F"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoFecha(etiquetaCampo_local, nombreCampo_local, valorCampo_local, "%Y-%m-%d", evento_local, "left", true, false, esCampoOculto_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "H"))
            {
                valorCampo_local = ap.obtenerHoraConFormato("H24", valorCampo_local);
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoHora(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local, "left", true, esCampoOculto_local, false));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "I"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoTipoLicencia(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "Q"))
            {
                if (!mc.esCadenaNumerica(valorCampo_local, true))
                {
                    valorCampo_local = String.valueOf(-1);
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoUsuario(pCampo, evento_local, valorCampo_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "G"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoGenero(etiquetaCampo_local, nombreCampo_local, valorCampo_local, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "P"))
            {
                evento_local = mc.concatenarCadena(evento_local, ConstantesGeneral.const_EventoActivarListaPlantillas);
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoDato(pCampo, valorCampo_local, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "Z"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoAplicacion(evento_local, Integer.parseInt(valorCampo_local), esCampoOculto_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "A"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(-1);
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoAplicacionTabla(pCampo, evento_local, Integer.parseInt(valorCampo_local), esCampoOculto_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "RR"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                evento_local = mc.concatenarCadena(evento_local, " onChange=\"activarCamposParaHabilitadoPor();\" ");
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoHabilitadoPor(pCampo, valorCampo_local, evento_local, pIdAplicacionActual));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "SS"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoListaDependiente(pCampo, valorCampo_local, evento_local, pIdAplicacionActual));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "TT"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                evento_local = mc.concatenarCadena(evento_local, " onChange=\"activarCamposParaEnlazado();\" ");
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoEnlazado(pCampo, valorCampo_local, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "UU"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                evento_local = mc.concatenarCadena(evento_local, " onchange=\"activarListasDesplegablesDependientesEnlazado(" + nombreCampo_local + ");\"");

                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoDependienteEnlazado(pCampo, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "HH"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || Integer.parseInt(valorCampo_local) == 0)
                {

                    valorCampo_local = String.valueOf(0);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoAplicacionPlantilla(pCampo, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "II"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || Integer.parseInt(valorCampo_local) == -1)
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                evento_local = mc.concatenarCadena(evento_local, " onChange=\"cambiarCamposOrigenEnlace();\" ");
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoEnlaceDepende(pCampo, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "JJ"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || Integer.parseInt(valorCampo_local) == -1)
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoOrigenEnlace(pCampo, Integer.parseInt(valorCampo_local), evento_local, pIdCampo));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "NN"))
            {
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoParrafo(etiquetaCampo_local, nombreCampo_local, valorCampo_local, longitudCampo_local, evento_local, 5, "center", esCampoOculto_local, false));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "OO"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(1);
                }
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoTipoHabilitacion(etiquetaCampo_local, nombreCampo_local, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "PP"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(1);
                }
                evento_local = mc.concatenarCadena(evento_local, " onChange=\"validarConfiguracionCalculos();\" ");
                return mc.concatenarCadena(insertarCamposModificables_local, getGeneradorComponentesHtml().insertarCampoTipoCampoCalculado(etiquetaCampo_local, nombreCampo_local, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "CC"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || Integer.parseInt(valorCampo_local) == -1)
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoCampoValor(pCampo, pListaCampo, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "M"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || Integer.parseInt(valorCampo_local) == -1)
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                evento_local = mc.concatenarCadena(evento_local, " onChange=\"cambiarOrigenDos();\" ");
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoCampoOrigenUno(pCampo, pListaCampo, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "N"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || Integer.parseInt(valorCampo_local) == -1)
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoCampoOrigenDos(pCampo, pListaCampo, evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "AA"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoBloqueo(pCampo, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "KK"))
            {
                if (mc.esCadenaVacia(valorCampo_local))
                {
                    valorCampo_local = String.valueOf(0);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                evento_local = mc.concatenarCadena(evento_local, " onChange=\"activarCamposParaFiltradoRegistrosEnlazados();\" ");
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoFiltradoRegistrosEnlazados(pCampo, Integer.parseInt(valorCampo_local), evento_local));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "WW"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || (Integer.parseInt(valorCampo_local) == -1 && !verificarEsCampoFiltrado(pListaCampo)))
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoCampoDestinoFiltrado(pCampo, Integer.parseInt(valorCampo_local), evento_local, pListaCampo));
            }

            if (mc.sonCadenasIguales(tipoCampo_local, "VV"))
            {
                if (mc.esCadenaVacia(valorCampo_local) || (Integer.parseInt(valorCampo_local) == -1 && !verificarEsCampoFiltrado(pListaCampo)))
                {

                    valorCampo_local = String.valueOf(-1);
                    evento_local = mc.concatenarCadena(evento_local, " style=\"display:none\" ");
                }
                return mc.concatenarCadena(insertarCamposModificables_local, insertarCampoTipoCampoOrigenFiltrado(pCampo, Integer.parseInt(valorCampo_local), evento_local, pIdAplicacionActual));
            }

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            evento_local = null;
            tipoCampo_local = null;
            valorCampo_local = null;
            nombreCampo_local = null;
            etiquetaCampo_local = null;
        }

        return insertarCamposModificables_local;
    }
    private String insertarFormulario(Formulario pFormulario, ListaCampo pListaCampo, ListaBotones pListaBotones, boolean pModificacion, int pUbicacionBotones, String pUsuarioActual, int pIdAplicacionActual, String pAlineacion)
    {
        String formularioConfiguracion_local = "";
        int idCampo_local = -1;
        Iterator iterador_local = null;
        Campo campo_local = null;
        GrupoInformacion grupoInformacion_local = null;

        if (pFormulario == ConstantesGeneral.VALOR_NULO)
        {
            return formularioConfiguracion_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return formularioConfiguracion_local;
        }
        if (pListaBotones == ConstantesGeneral.VALOR_NULO)
        {
            return formularioConfiguracion_local;
        }
        if (pUsuarioActual == ConstantesGeneral.VALOR_NULO)
        {
            return formularioConfiguracion_local;
        }
        if (pAlineacion == ConstantesGeneral.VALOR_NULO)
        {
            return formularioConfiguracion_local;
        }

        try
        {
            if (pUbicacionBotones == 0 || pUbicacionBotones == 2)
            {
                formularioConfiguracion_local = mc.concatenarCadena(formularioConfiguracion_local, getGeneradorComponentesHtml().insertarBotones(pListaBotones, 0, pAlineacion));
            }

            iterador_local = pListaCampo.iterator();

            formularioConfiguracion_local = mc.concatenarCadena(formularioConfiguracion_local, getGeneradorComponentesHtml().abrirTablaFormulario("tablaFormulario", "", "center"));

            while (iterador_local.hasNext())
            {
                campo_local = (Campo)iterador_local.next();
                if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldnombrecampo"))
                {
                    if (!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldidcampo")))
                    {
                        idCampo_local = Integer.parseInt(pListaCampo.obtenerValorCampo("fldidcampo"));
                    }
                    if (!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldidgrupoinformacion")))
                    {
                        grupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerGrupoInformacionPorId(Integer.parseInt(pListaCampo.obtenerValorCampo("fldidgrupoinformacion")));
                    }
                }

                campo_local.setGrupoInformacion(grupoInformacion_local);

                formularioConfiguracion_local = mc.concatenarCadena(formularioConfiguracion_local, insertarCamposModificables(campo_local, pListaCampo, pModificacion, pUsuarioActual, pIdAplicacionActual, idCampo_local));
            }

            formularioConfiguracion_local = mc.concatenarCadena(formularioConfiguracion_local, getGeneradorComponentesHtml().cerrarTabla());
            if (pUbicacionBotones == 1 || pUbicacionBotones == 2)
            {
                formularioConfiguracion_local = mc.concatenarCadena(formularioConfiguracion_local, getGeneradorComponentesHtml().insertarBotones(pListaBotones, 0, pAlineacion));
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
            grupoInformacion_local = null;
        }

        return formularioConfiguracion_local;
    }
    protected ListaCampo asignarValoresConsultaACampos(String pConsultaSQL, boolean pUtilizarBaseDatosSisnet)
    {
        ListaCampo listaCampo_local = null;
        ListaCampo listaCampoTabla_local = null;
        AtributoSesion atributoSesion_local = null;
        Iterator iterador_local = null;
        Campo campo_local = null;
        ResultSet resultSet_local = null;
        Object valor_local = null;
        String nombreCampo_local = null;
        String tipoDato_local = null;
        String valorCampo_local = null;

        if (pConsultaSQL == ConstantesGeneral.VALOR_NULO)
        {
            return listaCampo_local;
        }

        try
        {
            if (pUtilizarBaseDatosSisnet)
            {
                listaCampoTabla_local = getResultadoConsultaSQL().obtenerListadoCamposConsultaSQL(pConsultaSQL, true);
            }
            else
            {

                listaCampoTabla_local = getResultadoConsultaSQL().obtenerListadoCamposConsultaSQL(pConsultaSQL, false);
            }

            if (listaCampoTabla_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaCampoTabla_local.iterator();
                if (pUtilizarBaseDatosSisnet)
                {
                    resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaSisnet(pConsultaSQL);
                }
                else
                {
                    resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(pConsultaSQL);
                }
                listaCampo_local = new ListaCampo();
                resultSet_local.next();
                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    nombreCampo_local = campo_local.getNombreCampo();
                    campo_local.setEtiquetaCampo(ap.obtenerEtiquetaCampoPorNombreCampo(getManejadorRequest().obtenerNombreRecursoAplicacion(), nombreCampo_local));

                    if (resultSet_local.getObject(nombreCampo_local) != null)
                    {
                        campo_local.setValorCampo(resultSet_local.getObject(nombreCampo_local));
                    }
                    campo_local.getFormatoCampo().setTipoDato(campo_local.obtenerTipoDatoCampoPorNombreCampo(getManejadorRequest().obtenerNombreRecursoAplicacion()));

                    tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
                    if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO)
                    {
                        valorCampo_local = campo_local.getValorCampo().toString();
                        if (mc.sonCadenasIguales(tipoDato_local, "B"))
                        {
                            campo_local.setValorCampo(Boolean.valueOf(valorCampo_local));
                        }
                        if (mc.sonCadenasIguales(tipoDato_local, "I"))
                        {
                            campo_local.setValorCampo(ap.obtenerDescripcionTipoLicencia(valorCampo_local));
                        }
                    }
                    else
                    {
                        campo_local.setValorCampo("");
                    }
                    valor_local = campo_local.getValorCampo();
                    atributoSesion_local = new AtributoSesion(nombreCampo_local, valor_local);
                    getManejadorSesion().adicionarAtributoSesion(atributoSesion_local);
                    listaCampo_local.adicionar(campo_local);
                }
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {

            listaCampoTabla_local = null;
            atributoSesion_local = null;
            iterador_local = null;
            campo_local = null;
            valor_local = null;
            nombreCampo_local = null;
            resultSet_local = null;
            tipoDato_local = null;
            valorCampo_local = null;
        }

        return listaCampo_local;
    }
    protected String conformarEventosBody(ListaCampo pListaCampo, String pNombreFormulario)
    {
        String eventosBody_local = "";

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return eventosBody_local;
        }
        if (pNombreFormulario == ConstantesGeneral.VALOR_NULO)
        {
            return eventosBody_local;
        }

        try
        {
            if (pListaCampo.contarElementos() > 0)
            {
                if (getManejadorSesion().obtenerMotorAplicacion() != ConstantesGeneral.VALOR_NULO && (
                  getManejadorSesion().obtenerMotorAplicacion().verificarGrupoContieneCamposCalculadosNoRecalculables(pListaCampo.obtenerPrimerCampo().getGrupoInformacion()) || getManejadorSesion().obtenerMotorAplicacion().verificarGrupoContieneCamposCalculadosRecalculables(pListaCampo.obtenerPrimerCampo().getGrupoInformacion())))
                {

                    if (mc.esCadenaVacia(eventosBody_local))
                    {
                        eventosBody_local = " onLoad=\"";
                    }
                    eventosBody_local = mc.concatenarCadena(eventosBody_local, "ejecutarOperaciones(); ejecutarOperaciones(); ejecutarOperaciones();");
                    eventosBody_local = mc.concatenarCadena(eventosBody_local, String.valueOf(';'));
                }

                if (!mc.esCadenaVacia(eventosBody_local))
                {
                    eventosBody_local = mc.concatenarCadena(eventosBody_local, String.valueOf('"'));
                }
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return eventosBody_local;
    }
    protected String insertarEncabezadoPagina(String pTituloPrincipal, String pInformacionActual, Boton pBotonAtras, int pNumeroNivelesAnteriores)
    {
        String encabezadoPagina_local = "";

        if (pTituloPrincipal == ConstantesGeneral.VALOR_NULO)
        {
            return encabezadoPagina_local;
        }
        if (pInformacionActual == ConstantesGeneral.VALOR_NULO)
        {
            return encabezadoPagina_local;
        }
        if (pBotonAtras == ConstantesGeneral.VALOR_NULO)
        {
            return encabezadoPagina_local;
        }

        try
        {
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().abrirTablaTitulo("", "100%", ""));

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().abrirFilaTabla());
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().crearCeldaInformacionAplicacionAncho("", "25%", "center", ""));

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().crearCeldaInformacionAplicacionAncho(pTituloPrincipal, "50%", "center", ""));

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().crearCeldaInformacionAplicacionSubtituloAncho(pInformacionActual, "20%", "center", ""));

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().crearCeldaInformacionAplicacionAncho(getGeneradorComponentesHtml().insertarBoton(pBotonAtras, pNumeroNivelesAnteriores), "5%", "right", ""));

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().cerrarFilaTabla());
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().cerrarTablaSinSalto());
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return encabezadoPagina_local;
    }
    private String conformarListaOpcionesConCampos(ListaGeneral pListaGeneralCampos)
    {
        String listaOpcionesConCampos = "";
        ItemLista itemLista_local = null;
        Iterator iterador_local = null;

        if (pListaGeneralCampos == ConstantesGeneral.VALOR_NULO)
        {
            return listaOpcionesConCampos;
        }

        try
        {
            iterador_local = pListaGeneralCampos.iterator();
            while (iterador_local.hasNext())
            {
                itemLista_local = (ItemLista)iterador_local.next();
                listaOpcionesConCampos = mc.concatenarCadena(listaOpcionesConCampos, "Control.options.add(new Option(\"" + itemLista_local.getNombreItem() + "\" , \"" + itemLista_local.getValorItem() + "\"));\n");
            }

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            itemLista_local = null;
            iterador_local = null;
        }

        return listaOpcionesConCampos;
    }
    private String conformarJavascriptObtenerListaCamposMismoGrupo(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposMismoGrupo(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(pGrupoInformacion, false, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposNumericosMismoGrupo(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposNumericosMismoGrupo(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(pGrupoInformacion, true, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposGruposNoMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposGruposNoMultiples(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion, false, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposNumericosGruposNoMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposNumericosGruposNoMultiples(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion, true, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposFechasGruposNoMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposFechasGruposNoMultiples(Control){\n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion, false, true);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposMismoGrupoConNoMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposMismoGrupoConNoMultiples(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion, false, false);

            if (pGrupoInformacion.esGrupoInformacionMultiple())
            {
                listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(pGrupoInformacion, false, false));
            }

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposNumericosMismoGrupoConNoMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposNumericosMismoGrupoConNoMultiples(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionNoMultiplesAplicacion(pGrupoInformacion, true, false);

            listaGeneralCampos_local.concatenarListaGeneral(getAdministradorBaseDatosSisnet().obtenerListaCamposGrupoInformacion(pGrupoInformacion, true, false));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposGruposMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerlistaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerlistaCampos_local;
        }

        try
        {
            javascriptObtenerlistaCampos_local = mc.concatenarCadena(javascriptObtenerlistaCampos_local, "function obtenerlistaCamposGruposMultiples(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion, false, false);

            javascriptObtenerlistaCampos_local = mc.concatenarCadena(javascriptObtenerlistaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerlistaCampos_local = mc.concatenarCadena(javascriptObtenerlistaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerlistaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposNumericosGruposMultiples(GrupoInformacion pGrupoInformacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptObtenerListaCampos_local;
        }

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposNumericosGruposMultiples(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion, true, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposAplicacion(int pIdAplicacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerListaCamposAplicacion(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(pIdAplicacion, false, false, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposNumericosAplicacion(int pIdAplicacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposNumericosAplicacion(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(pIdAplicacion, true, false, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposFechaAplicacion(int pIdAplicacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposFechaAplicacion(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(pIdAplicacion, false, true, false);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }
        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptObtenerListaCamposHoraAplicacion(int pIdAplicacion)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaGeneral listaGeneralCampos_local = null;

        try
        {
            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerlistaCamposHoraAplicacion(Control){ \n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(pIdAplicacion, false, false, true);

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

            javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaGeneralCampos_local = null;
        }
        return javascriptObtenerListaCampos_local;
    }
    private String conformarJavascriptCambiarCampoValor(GrupoInformacion pGrupoInformacion)
    {
        String javascriptCambiarCampoValor_local = "";
        String campos_local = null;
        String idCampos_local = null;
        ListaGeneral listaGeneralCampos_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptCambiarCampoValor_local;
        }

        try
        {
            campos_local = "";
            idCampos_local = "";
            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion, false, false);

            if (listaGeneralCampos_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaGeneralCampos_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    if (!mc.sonCadenasIguales(campos_local, ""))
                    {
                        campos_local = mc.concatenarCadena(campos_local, String.valueOf(','));
                        idCampos_local = mc.concatenarCadena(idCampos_local, String.valueOf(','));
                    }
                    campos_local = mc.concatenarCadena(campos_local, mc.colocarEntreComillas(itemLista_local.getNombreItem()));
                    idCampos_local = mc.concatenarCadena(idCampos_local, mc.colocarEntreComillas(itemLista_local.getValorItem()));
                }
            }
            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "function cambiarCampoValor(){ \n ");
            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "limpiarLista (document.formularioIncluir." + mc.convertirAMayusculas("fldidcampovalor") + ");\n" + "campo_local = document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldhabilitadopor") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldhabilitadopor") + ".selectedIndex].text;\n" + "var nombreGrupoReferencia_local = obtenerNombreGrupoInformacionCampo(campo_local);\n");

            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "var seleccion_local = parseInt(document.formularioIncluir." + mc.convertirAMayusculas("fldhabilitadopor") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldhabilitadopor") + ".selectedIndex].value); \n");

            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "  if(seleccion_local != 0){\n     if(verificarCampoEnGrupoMultiple(document.formularioIncluir." + mc.convertirAMayusculas("fldhabilitadopor") + ")){\n");

            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "var campos_local; \n var idCampos_local;\n   campos_local = new Array(" + campos_local + "); \n" + "   idCampos_local = new Array(" + idCampos_local + "); \n");

            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "var i = 0;\n       for(i=0; i<campos_local.length; i++){\n         if(obtenerNombreGrupoInformacionCampo(campos_local[i]) == nombreGrupoReferencia_local){\n            document.formularioIncluir." + mc.convertirAMayusculas("fldidcampovalor") + ".options.add(new Option(campos_local[i], idCampos_local[i]));\n" + "        }\n" + "      }\n" + "    } else { " + "       obtenerlistaCamposGruposNoMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + ");\n" + "    }\n" + "  } else {" + "      obtenerlistaCamposMismoGrupoConNoMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); \n  }\n");

            javascriptCambiarCampoValor_local = mc.concatenarCadena(javascriptCambiarCampoValor_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campos_local = null;
            idCampos_local = null;
            listaGeneralCampos_local = null;
            iterador_local = null;
            itemLista_local = null;
        }

        return javascriptCambiarCampoValor_local;
    }
    private String conformarJavascriptCambiarOrigenDos()
    {
        String javascriptCambiarOrigenDos_local = "";
        try
        {
            javascriptCambiarOrigenDos_local = "function cambiarOrigenDos(){ \n "
    		+"const opcioncampoConcatenado = [11,12,21,22,23,24,27,28];\n";
            javascriptCambiarOrigenDos_local += "limpiarLista (document.formularioIncluir.FLDIDCAMPOORIGENDOS);\n";
            javascriptCambiarOrigenDos_local += "var seleccion_local = parseInt(document.formularioIncluir." + mc.convertirAMayusculas("fldcampocalculado") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampocalculado") + ".selectedIndex].value);\n";
            javascriptCambiarOrigenDos_local += "var esSumaResta_local = ((seleccion_local == 5) || (seleccion_local == 6));\n";
            javascriptCambiarOrigenDos_local += "  if(esSumaResta_local || seleccion_local == 7 || seleccion_local == 8 || seleccion_local == 43){\n"
            +"     if(verificarCampoEnGrupoMultiple(document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigenuno") + ")){\n" 
            + "       obtenerlistaCamposNumericosGruposNoMultiples(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "       obtenerlistaCamposFechasGruposNoMultiples(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "       llenarCampoOrigenDos(esSumaResta_local);\n     } else { \n" 
            + "       obtenerlistaCamposNumericosGruposNoMultiples(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "       obtenerlistaCamposFechasGruposNoMultiples(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "     } \n " 
            + " } else { \n" 
            + "     if(seleccion_local == 13 || seleccion_local == -13 || opcioncampoConcatenado.indexOf(seleccion_local) > -1) { \n" 
            + "           obtenerListaCamposAplicacion(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "     } else { \n" 
            + "         if(seleccion_local == 25 || " 
            + " seleccion_local == 33) { \n" 
            + "           obtenerlistaCamposMismoGrupoConNoMultiples(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "         } \n" 
            + "         if(seleccion_local == 31) { \n" 
            + "           obtenerlistaCamposMismoGrupoConNoMultiples(document.formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "         } \n" + "         if(seleccion_local == 32) { \n" 
            + "           obtenerlistaCamposNumericosMismoGrupo(document." + "formularioIncluir.FLDIDCAMPOORIGENDOS); \n" 
            + "         } \n" 
            + "     } \n" + " } \n "
            +"}\n";
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        return javascriptCambiarOrigenDos_local;
    }
    private String conformarJavascriptVerificarCampoEnGrupoMultiple(GrupoInformacion pGrupoInformacion)
    {
        String javascriptVerificarCampo_local = "";
        String idCampos_local = null;
        ListaGeneral listaGeneralCampos_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptVerificarCampo_local;
        }

        try
        {
            idCampos_local = "";
            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "function verificarCampoEnGrupoMultiple(Control){ \n var camposGruposMultiples_local = new Array(");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion, false, false);

            if (listaGeneralCampos_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaGeneralCampos_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    idCampos_local = mc.concatenarCadena(idCampos_local, mc.colocarEntreComillas(itemLista_local.getValorItem()));
                    if (iterador_local.hasNext())
                    {
                        idCampos_local = mc.concatenarCadena(idCampos_local, String.valueOf(','));
                    }
                }
            }
            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, idCampos_local + "); \n");
            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "var seleccion_local = Control[Control.selectedIndex].value;\n");

            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "var i = 0;\n var esCampoGrupoMultiple_local = false;\n for(i=0; i<camposGruposMultiples_local.length; i++){\n\t   esCampoGrupoMultiple_local = (camposGruposMultiples_local[i] == seleccion_local);\n\t   if(esCampoGrupoMultiple_local){\n\t\t      break;\n\t   }\n}");

            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "return esCampoGrupoMultiple_local; \n");
            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            idCampos_local = null;
            iterador_local = null;
            listaGeneralCampos_local = null;
            itemLista_local = null;
        }

        return javascriptVerificarCampo_local;
    }
    private String conformarJavascriptLlenarOrigenDos(GrupoInformacion pGrupoInformacion)
    {
        String javascriptVerificarCampo_local = "";
        String campos_local = null;
        String idCampos_local = null;
        String camposConFechas_local = null;
        String idCamposConFechas_local = null;
        ListaGeneral listaGeneralCampos_local = null;
        ListaGeneral listaGeneralCamposConFechas_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptVerificarCampo_local;
        }

        try
        {
            campos_local = "";
            idCampos_local = "";
            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "function llenarCampoOrigenDos(pConFechas){ \n campo_local = document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigenuno") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + ".selectedIndex].text;\n" + "var nombreGrupoReferencia_local = obtenerNombreGrupoInformacionCampo(campo_local);\n");

            listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion, true, false);

            if (listaGeneralCampos_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaGeneralCampos_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    campos_local = mc.concatenarCadena(campos_local, mc.colocarEntreComillas(itemLista_local.getNombreItem()));
                    idCampos_local = mc.concatenarCadena(idCampos_local, mc.colocarEntreComillas(itemLista_local.getValorItem()));
                    if (iterador_local.hasNext())
                    {
                        campos_local = mc.concatenarCadena(campos_local, String.valueOf(','));
                        idCampos_local = mc.concatenarCadena(idCampos_local, String.valueOf(','));
                    }
                }
            }
            iterador_local = null;
            camposConFechas_local = campos_local;
            idCamposConFechas_local = idCampos_local;
            listaGeneralCamposConFechas_local = getAdministradorBaseDatosSisnet().obtenerListaCamposGruposInformacionMultiplesAplicacion(pGrupoInformacion, false, true);

            if (listaGeneralCamposConFechas_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaGeneralCamposConFechas_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    if (!mc.sonCadenasIguales(camposConFechas_local, ""))
                    {
                        camposConFechas_local = mc.concatenarCadena(camposConFechas_local, String.valueOf(','));

                        idCamposConFechas_local = mc.concatenarCadena(idCamposConFechas_local, String.valueOf(','));
                    }

                    camposConFechas_local = mc.concatenarCadena(camposConFechas_local, mc.colocarEntreComillas(itemLista_local.getNombreItem()));

                    idCamposConFechas_local = mc.concatenarCadena(idCamposConFechas_local, mc.colocarEntreComillas(itemLista_local.getValorItem()));
                }
            }

            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "var campos_local; \n var idCampos_local;\n if(pConFechas == true){ \n   campos_local = new Array(" + camposConFechas_local + "); \n" + "   idCampos_local = new Array(" + idCamposConFechas_local + "); \n" + " } else {\n" + "   campos_local = new Array(" + campos_local + "); \n" + "   idCampos_local = new Array(" + idCampos_local + "); \n }\n");

            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "var i = 0;\n for(i=0; i<campos_local.length; i++){\n   if(obtenerNombreGrupoInformacionCampo(campos_local[i]) == nombreGrupoReferencia_local){\n      document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigendos") + ".options.add(new Option(campos_local[i], idCampos_local[i]));\n" + "   }\n" + "}");

            javascriptVerificarCampo_local = mc.concatenarCadena(javascriptVerificarCampo_local, "}\n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campos_local = null;
            idCampos_local = null;
            iterador_local = null;
            listaGeneralCampos_local = null;
            listaGeneralCamposConFechas_local = null;
            itemLista_local = null;
        }

        return javascriptVerificarCampo_local;
    }
    private String conformarJavascriptValidarConfiguracionCalculos(GrupoInformacion pGrupoInformacion)
    {
        String javascriptValidarConfiguracion_local = "";
        String campoValorNoVisible_local = null;
        String campoValorVisible_local = null;
        String campoRecalculableNoVisible_local = null;
        String campoRecalculableVisible_local = null;
        String campoOrigenUnoNoVisible_local = null;
        String campoOrigenUnoVisible_local = null;
        String campoFormatoOrigenUnoNoVisible_local = null;
        String campoFormatoOrigenUnoVisible_local = null;
        String campoOrigenDosNoVisible_local = null;
        String campoOrigenDosVisible_local = null;
        String campoFormatoOrigenDosNoVisible_local = null;
        String campoFormatoOrigenDosVisible_local = null;
        String campoFormatoCampoCalculadoNoVisible_local = null;
        String campoFormatoCampoCalculadoVisible_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptValidarConfiguracion_local;
        }

        try
        {
            campoValorNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldidcampovalor") + ".style.display='none';\n \t";

            campoValorVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldidcampovalor") + ".style.display='inline';\n \t";

            campoRecalculableNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldesrecalculable") + ".style.display='none';\n \t";

            campoRecalculableVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldesrecalculable") + ".style.display='inline';\n \t";

            campoOrigenUnoNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigenuno") + ".style.display='none';\n \t";

            campoOrigenUnoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigenuno") + ".style.display='inline';\n \t";

            campoFormatoOrigenUnoNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldformatocampoorigenuno") + ".style.display='none';\n \t";

            campoFormatoOrigenUnoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldformatocampoorigenuno") + ".style.display='inline';\n \t";

            campoOrigenDosNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigendos") + ".style.display='none';\n \t";

            campoOrigenDosVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigendos") + ".style.display='inline';\n \t ";

            campoFormatoOrigenDosNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldformatocampoorigendos") + ".style.display='none';\n \t";

            campoFormatoOrigenDosVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldformatocampoorigendos") + ".style.display='inline';\n \t";

            campoFormatoCampoCalculadoNoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldformatocampocalculado") + ".style.display='none';\n \t";

            campoFormatoCampoCalculadoVisible_local = "document.formularioIncluir." + mc.convertirAMayusculas("fldformatocampocalculado") + ".style.display='inline';\n \t";

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "function validarConfiguracionCalculos(){ \nvar seleccion_local = parseInt(document.formularioIncluir." + mc.convertirAMayusculas("fldcampocalculado") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampocalculado") + ".selectedIndex].value);\n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "limpiarLista (document.formularioIncluir." + mc.convertirAMayusculas("fldidcampovalor") + ");\n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "limpiarLista (document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigenuno") + ");\n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "limpiarLista (document.formularioIncluir." + mc.convertirAMayusculas("fldidcampoorigendos") + ");\n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "switch(seleccion_local){\n");
            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 1: " + campoValorNoVisible_local + campoRecalculableNoVisible_local + campoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoCampoCalculadoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoFormatoOrigenDosNoVisible_local + " break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 2: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "cambiarCampoValor(); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 3: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposGruposMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 4: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposGruposMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 5: \ncase 6: \n\t" + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); \n \t" + "obtenerlistaCamposFechaAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 7: \ncase 8: \ncase 43: \n\t" + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 9: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosGruposMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 10: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosMismoGrupo(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 11: " + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerListaCamposAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + ");  \n \t" + "obtenerListaCamposAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigendos") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 12: " + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerListaCamposAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); \n \t" + "obtenerListaCamposAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigendos") + "); break; \n \t");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 13: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 14: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 15: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 16: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 17: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 18: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 19: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 20: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 21: \ncase 27: " + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposMismoGrupoConNoMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); \n \t" + "obtenerlistaCamposMismoGrupoConNoMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigendos") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 22: \ncase 23: \ncase 24: \ncase 28: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposGruposMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 25: " + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposMismoGrupoConNoMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); \n \t" + "obtenerlistaCamposMismoGrupoConNoMultiples(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigendos") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 26: " + campoValorNoVisible_local + campoRecalculableVisible_local + campoOrigenUnoVisible_local + campoFormatoOrigenUnoVisible_local + campoOrigenDosVisible_local + campoFormatoOrigenDosVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposNumericosMismoGrupo(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigenuno") + "); \n \t" + "obtenerlistaCamposNumericosMismoGrupo(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampoorigendos") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 29: " + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposHoraAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "case 29: \ncase 30: \ncase 31: \ncase 32: \ncase 33: \ncase 34: \ncase 35: \ncase 36: \ncase 37: \ncase 38: \ncase 39: \ncase 40: \ncase 41: \ncase 42: \n" + campoValorVisible_local + campoRecalculableVisible_local + campoOrigenUnoNoVisible_local + campoFormatoOrigenUnoNoVisible_local + campoOrigenDosNoVisible_local + campoFormatoOrigenDosNoVisible_local + campoFormatoCampoCalculadoVisible_local + "obtenerlistaCamposFechaAplicacion(document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldidcampovalor") + "); break; \n");

            javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, "} \n } \n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campoValorVisible_local = null;
            campoValorNoVisible_local = null;
            campoOrigenDosVisible_local = null;
            campoOrigenUnoVisible_local = null;
            campoOrigenDosNoVisible_local = null;
            campoOrigenUnoNoVisible_local = null;
            campoRecalculableVisible_local = null;
            campoRecalculableNoVisible_local = null;
            campoFormatoOrigenUnoVisible_local = null;
            campoFormatoOrigenDosVisible_local = null;
            campoFormatoOrigenUnoNoVisible_local = null;
            campoFormatoOrigenDosNoVisible_local = null;
            campoFormatoCampoCalculadoVisible_local = null;
            campoFormatoCampoCalculadoNoVisible_local = null;
        }

        return javascriptValidarConfiguracion_local;
    }
    private String insertarJavascriptValidarConfiguracionCalculos(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo)
    {
        String javascriptValidarConfiguracion_local = "";
        Campo campo_local = null;

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptValidarConfiguracion_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptValidarConfiguracion_local;
        }

        try
        {
            campo_local = pListaCampo.obtenerCampoPorNombre("fldcampocalculado");
            if (campo_local != ConstantesGeneral.VALOR_NULO)
            {
                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion()));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposGruposMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposGruposNoMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposMismoGrupo(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposMismoGrupoConNoMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposFechaAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion()));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposHoraAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion()));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposNumericosAplicacion(pGrupoInformacion.getAplicacion().getIdAplicacion()));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposNumericosGruposMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposNumericosGruposNoMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposFechasGruposNoMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposNumericosMismoGrupo(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptObtenerListaCamposNumericosMismoGrupoConNoMultiples(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptVerificarCampoEnGrupoMultiple(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptLlenarOrigenDos(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptCambiarOrigenDos());

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptCambiarCampoValor(pGrupoInformacion));

                javascriptValidarConfiguracion_local = mc.concatenarCadena(javascriptValidarConfiguracion_local, conformarJavascriptValidarConfiguracionCalculos(pGrupoInformacion));
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
        return javascriptValidarConfiguracion_local;
    }
    private String conformarJavascriptValidarHabilitacion(Campo pCampoEnlazado, boolean pExistenCamposDependientesListas)
    {
        String scriptValidarHabilitacion_local = "";
        ListaCampo listaCampo_local = null;

        if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO)
        {
            return scriptValidarHabilitacion_local;
        }

        try
        {
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposDependientesEnlazado(pCampoEnlazado);
            if (listaCampo_local.contarElementos() > 0)
            {
                scriptValidarHabilitacion_local = "function validarHabilitacion" + pCampoEnlazado.getNombreCampo() + "(){ \n " + "if(document.formularioIncluir." + pCampoEnlazado.getNombreCampo() + "[document.formularioIncluir." + pCampoEnlazado.getNombreCampo() + ".selectedIndex].value == -2){ \n" + "habilitarDependientes" + pCampoEnlazado.getNombreCampo() + "(); \n" + "limpiarDependientes" + pCampoEnlazado.getNombreCampo() + "(); \n " + "enviarFormulario(); \n } else { \n" + "deshabilitarDependientes" + pCampoEnlazado.getNombreCampo() + "(); \n";

                if (pExistenCamposDependientesListas)
                {
                    scriptValidarHabilitacion_local = mc.concatenarCadena(scriptValidarHabilitacion_local, "habilitarListas(); \n ");
                }
                scriptValidarHabilitacion_local = mc.concatenarCadena(scriptValidarHabilitacion_local, "enviarFormulario(); }\n ");

                scriptValidarHabilitacion_local = mc.concatenarCadena(scriptValidarHabilitacion_local, " } \n");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaCampo_local = null;
        }

        return scriptValidarHabilitacion_local;
    }
    private String conformarJavascriptHabilitarDependientes(Campo pCampoEnlazado)
    {
        String scriptHabilitarDependientes_local = "";
        ListaCampo listaCampo_local = null;
        Iterator iterador_local = null;
        Campo campo_local = null;

        if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO)
        {
            return scriptHabilitarDependientes_local;
        }

        try
        {
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposDependientesEnlazado(pCampoEnlazado);
            if (listaCampo_local.contarElementos() > 0)
            {
                scriptHabilitarDependientes_local = "function habilitarDependientes" + pCampoEnlazado.getNombreCampo() + "(){ \n ";

                iterador_local = listaCampo_local.iterator();
                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    scriptHabilitarDependientes_local = mc.concatenarCadena(scriptHabilitarDependientes_local, "document.formularioIncluir." + campo_local.getNombreCampo() + ".disabled = false; \n");

                    campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
                }
                scriptHabilitarDependientes_local = mc.concatenarCadena(scriptHabilitarDependientes_local, " } \n");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            listaCampo_local = null;
            campo_local = null;
        }

        return scriptHabilitarDependientes_local;
    }
    private String obtenerJavascriptDeshabilitarDependientes(Campo pCampoEnlazado)
    {
        String scriptDeshabilitarDependientes_local = "";
        ListaCampo listaCampo_local = null;
        Iterator iterador_local = null;
        Campo campo_local = null;

        if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO)
        {
            return scriptDeshabilitarDependientes_local;
        }

        try
        {
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposDependientesEnlazado(pCampoEnlazado);
            if (listaCampo_local.contarElementos() > 0)
            {
                scriptDeshabilitarDependientes_local = "function deshabilitarDependientes" + pCampoEnlazado.getNombreCampo() + "(){ \n ";
                iterador_local = listaCampo_local.iterator();
                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    if (campo_local.getEnlaceCampo().getDependienteEnlazado() == 1)
                    {
                        scriptDeshabilitarDependientes_local = mc.concatenarCadena(scriptDeshabilitarDependientes_local, "document.formularioIncluir." + campo_local.getNombreCampo() + ".disabled = true; \n");
                    }

                    campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
                }
                scriptDeshabilitarDependientes_local = mc.concatenarCadena(scriptDeshabilitarDependientes_local, " } \n");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            listaCampo_local = null;
            campo_local = null;
        }

        return scriptDeshabilitarDependientes_local;
    }
    private String obtenerJavascriptLimpiarDependientes(Campo pCampoEnlazado)
    {
        String scriptLimpiarDependientes_local = "";
        boolean esEnlazado_local = false;
        String nombreCampo_local = null;
        String valorCampo_local = null;
        ListaCampo listaCampo_local = null;
        Iterator iterador_local = null;
        Campo campo_local = null;

        if (pCampoEnlazado == ConstantesGeneral.VALOR_NULO)
        {
            return scriptLimpiarDependientes_local;
        }

        try
        {
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposDependientesEnlazado(pCampoEnlazado);
            if (listaCampo_local.contarElementos() > 0)
            {
                scriptLimpiarDependientes_local = "function limpiarDependientes" + pCampoEnlazado.getNombreCampo() + "(){ \n ";
                iterador_local = listaCampo_local.iterator();
                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    nombreCampo_local = campo_local.getNombreCampo();
                    valorCampo_local = mc.colocarEntreComillas("");
                    esEnlazado_local = campo_local.esCampoEnlazado();
                    if (campo_local.esTipoDatoTabla() || esEnlazado_local)
                    {
                        valorCampo_local = mc.colocarEntreComillas(String.valueOf(0));
                        scriptLimpiarDependientes_local = mc.concatenarCadena(scriptLimpiarDependientes_local, " opcion = new Option(\"Escoja un valor\", \"0\"); \nif (!verificarValorNulo(document.formularioIncluir." + nombreCampo_local + ")){ \n " + "document.formularioIncluir." + nombreCampo_local + ".add(opcion, 0); \n} \n");

                        if (esEnlazado_local && getManejadorSesion().obtenerMotorAplicacion().verificarCampoTieneCamposDependientes(campo_local))
                        {
                            scriptLimpiarDependientes_local = mc.concatenarCadena(scriptLimpiarDependientes_local, "limpiarDependientes" + nombreCampo_local + "(); \n");
                        }
                    }

                    if (campo_local.esTipoDatoNumerico())
                    {
                        valorCampo_local = mc.colocarEntreComillas(String.valueOf(0));
                    }
                    scriptLimpiarDependientes_local = mc.concatenarCadena(scriptLimpiarDependientes_local, "document.formularioIncluir." + nombreCampo_local + ".value = " + valorCampo_local + "; \n");

                    campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
                }
                scriptLimpiarDependientes_local = mc.concatenarCadena(scriptLimpiarDependientes_local, " } \n");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            valorCampo_local = null;
            nombreCampo_local = null;
            listaCampo_local = null;
            campo_local = null;
        }

        return scriptLimpiarDependientes_local;
    }
    private String insertarJavascriptHabilitarDependientes(ListaCampo pListaCampo, boolean pExistenCamposDependientesTabla)
    {
        String javascriptHabilitarCampos_local = "";
        Iterator iterador_local = null;
        Campo campo_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptHabilitarCampos_local;
        }

        try
        {
            iterador_local = pListaCampo.iterator();
            while (iterador_local.hasNext())
            {
                campo_local = (Campo)iterador_local.next();
                if (campo_local.esCampoEnlazado())
                {
                    javascriptHabilitarCampos_local = mc.concatenarCadena(javascriptHabilitarCampos_local, conformarJavascriptHabilitarDependientes(campo_local));

                    javascriptHabilitarCampos_local = mc.concatenarCadena(javascriptHabilitarCampos_local, obtenerJavascriptDeshabilitarDependientes(campo_local));

                    javascriptHabilitarCampos_local = mc.concatenarCadena(javascriptHabilitarCampos_local, obtenerJavascriptLimpiarDependientes(campo_local));

                    javascriptHabilitarCampos_local = mc.concatenarCadena(javascriptHabilitarCampos_local, conformarJavascriptValidarHabilitacion(campo_local, pExistenCamposDependientesTabla));
                }

                campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
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
        return javascriptHabilitarCampos_local;
    }
    private String conformarCodigoLimpiarListasDependientes(Campo pCampo)
    {
        String listaLimpiarCampos_local = "";
        int idCampo_local = -1;
        ListaGeneral listaCamposDependientes_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return listaLimpiarCampos_local;
        }

        try
        {
            if (getAdministradorBaseDatosSisnet().verificarEsCampoMaestro(pCampo.getIdCampo()))
            {
                listaCamposDependientes_local = getAdministradorBaseDatosSisnet().obtenerListaCamposDependientesCampoMaestro(pCampo);
                iterador_local = listaCamposDependientes_local.iterator();
                while (iterador_local.hasNext())
                {
                    itemLista_local = (ItemLista)iterador_local.next();
                    idCampo_local = Integer.parseInt(itemLista_local.getValorItem());
                    if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo, getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(idCampo_local)))
                    {
                        listaLimpiarCampos_local = mc.concatenarCadena(listaLimpiarCampos_local, "limpiarLista(formularioIncluir." + itemLista_local.getNombreItem() + "); \n");
                    }

                    itemLista_local = (ItemLista)ConstantesGeneral.VALOR_NULO;
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
            itemLista_local = null;
            listaCamposDependientes_local = null;
        }
        return listaLimpiarCampos_local;
    }
    private String concatenarDescripcionesLista(ListaGeneral pListaGeneral, String pNombreTabla, ListaCadenas pListaRestricciones, boolean pExcluirValores, boolean pExistenRestricciones)
    {
        String descripcionesLista_local = "";
        int valorItem_local = 0;
        String descripcionLista_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;
        ListaCadenas listaDescripcionesLista_local = null;

        if (pListaGeneral == ConstantesGeneral.VALOR_NULO)
        {
            return descripcionesLista_local;
        }
        if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
        {
            return descripcionesLista_local;
        }
        if (pListaRestricciones == ConstantesGeneral.VALOR_NULO)
        {
            return descripcionesLista_local;
        }

        try
        {
            listaDescripcionesLista_local = new ListaCadenas();
            iterador_local = pListaGeneral.iterator();
            while (iterador_local.hasNext())
            {
                itemLista_local = (ItemLista)iterador_local.next();
                valorItem_local = Integer.parseInt(itemLista_local.getValorItem());
                if (valorItem_local == 0)
                {
                    itemLista_local.setNombreItem("Escoja un valor");
                }
                else
                {
                    itemLista_local.setNombreItem(getAdministradorBaseDatosAplicacion().obtenerValorTabla(pNombreTabla, valorItem_local));
                }

                descripcionLista_local = itemLista_local.getNombreItem();
                if (pExistenRestricciones)
                {
                    if (pExcluirValores)
                    {
                        if (!pListaRestricciones.verificarExistenciaCadena(descripcionLista_local) || valorItem_local == -1)
                        {
                            listaDescripcionesLista_local.adicionar(mc.colocarEntreComillas(descripcionLista_local));
                        }
                        continue;
                    }
                    if (pListaRestricciones.verificarExistenciaCadena(descripcionLista_local) || valorItem_local == -1)
                    {
                        listaDescripcionesLista_local.adicionar(mc.colocarEntreComillas(descripcionLista_local));
                    }
                    continue;
                }
                listaDescripcionesLista_local.adicionar(mc.colocarEntreComillas(descripcionLista_local));
            }

            iterador_local = listaDescripcionesLista_local.iterator();
            while (iterador_local.hasNext())
            {
                descripcionesLista_local = mc.concatenarCadena(descripcionesLista_local, (String)iterador_local.next());
                if (iterador_local.hasNext())
                {
                    descripcionesLista_local = mc.concatenarCadena(descripcionesLista_local, String.valueOf(','));
                }
            }

            descripcionesLista_local = mc.colocarEntreParentesis(descripcionesLista_local) + ';';
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            itemLista_local = null;
            descripcionLista_local = null;
            listaDescripcionesLista_local = null;
        }

        return descripcionesLista_local;
    }
    private String concatenarIdentificadoresLista(ListaGeneral pListaGeneral, String pNombreTabla, ListaCadenas pListaRestricciones, boolean pExcluirValores, boolean pExistenRestricciones)
    {
        String identificadoresLista_local = "";
        int valorItem_local = 0;
        String descripcionLista_local = null;
        String identificadorLista_local = null;
        Iterator iterador_local = null;
        ItemLista itemLista_local = null;
        ListaCadenas listaIdentificadoresLista_local = null;

        if (pListaGeneral == ConstantesGeneral.VALOR_NULO)
        {
            return identificadoresLista_local;
        }
        if (pListaRestricciones == ConstantesGeneral.VALOR_NULO)
        {
            return identificadoresLista_local;
        }

        try
        {
            listaIdentificadoresLista_local = new ListaCadenas();
            iterador_local = pListaGeneral.iterator();
            while (iterador_local.hasNext())
            {
                itemLista_local = (ItemLista)iterador_local.next();
                valorItem_local = Integer.parseInt(itemLista_local.getValorItem());
                if (valorItem_local == 0)
                {
                    itemLista_local.setNombreItem("Escoja un valor");
                }
                else
                {
                    itemLista_local.setNombreItem(getAdministradorBaseDatosAplicacion().obtenerValorTabla(pNombreTabla, valorItem_local));
                }

                identificadorLista_local = itemLista_local.getValorItem();
                descripcionLista_local = itemLista_local.getNombreItem();
                if (pExistenRestricciones)
                {
                    if (pExcluirValores)
                    {
                        if (!pListaRestricciones.verificarExistenciaCadena(descripcionLista_local) || valorItem_local == -1)
                        {
                            listaIdentificadoresLista_local.adicionar(mc.colocarEntreComillas(identificadorLista_local));
                        }
                        continue;
                    }
                    if (pListaRestricciones.verificarExistenciaCadena(descripcionLista_local) || valorItem_local == -1)
                    {
                        listaIdentificadoresLista_local.adicionar(mc.colocarEntreComillas(identificadorLista_local));
                    }

                    continue;
                }
                listaIdentificadoresLista_local.adicionar(mc.colocarEntreComillas(identificadorLista_local));
            }

            iterador_local = listaIdentificadoresLista_local.iterator();
            while (iterador_local.hasNext())
            {
                identificadoresLista_local = mc.concatenarCadena(identificadoresLista_local, (String)iterador_local.next());
                if (iterador_local.hasNext())
                {
                    identificadoresLista_local = mc.concatenarCadena(identificadoresLista_local, String.valueOf(','));
                }
            }

            identificadoresLista_local = mc.colocarEntreParentesis(identificadoresLista_local) + ';';
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            itemLista_local = null;
            descripcionLista_local = null;
            identificadorLista_local = null;
            listaIdentificadoresLista_local = null;
        }

        return identificadoresLista_local;
    }
    private String obtenerJavascriptListasDependientes(String pNombreCampoMaestro, Campo pCampo)
    {
        String scriptValores_local = "";
        String nombreTablaDetalle_local = null;
        int idValorMaestro_local = -1;
        ListaGeneral listaIdValoresMaestro_local = null;
        Iterator iteradorMaestro_local = null;
        ListaGeneral listaDetalle_local = null;
        ItemLista valorMaestro_local = null;
        ListaCadenas listaRestriccionesCampoValores_local = null;
        boolean excluirValores_local = false;
        boolean existenRestricciones_local = false;

        if (pNombreCampoMaestro == ConstantesGeneral.VALOR_NULO)
        {
            return scriptValores_local;
        }
        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return scriptValores_local;
        }

        try
        {
            listaRestriccionesCampoValores_local = getManejadorPermisoUsuario().obtenerListaRestriccionesCampoValores(pCampo);
            if (listaRestriccionesCampoValores_local.contarElementos() == 0)
            {
                listaRestriccionesCampoValores_local = null;
                listaRestriccionesCampoValores_local = getManejadorPermisoUsuario().obtenerListaRestriccionesCampoValoresMenos(pCampo);
                excluirValores_local = true;
            }
            existenRestricciones_local = (listaRestriccionesCampoValores_local.contarElementos() > 0);
            nombreTablaDetalle_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(pCampo.getFormatoCampo().getTipoDato())).getNombreTabla();

            listaIdValoresMaestro_local = getAdministradorBaseDatosSisnet().obtenerListaIdValoresMaestroCampo(pCampo.getIdCampo());
            iteradorMaestro_local = listaIdValoresMaestro_local.iterator();
            while (iteradorMaestro_local.hasNext())
            {
                valorMaestro_local = (ItemLista)iteradorMaestro_local.next();
                idValorMaestro_local = Integer.parseInt(valorMaestro_local.getValorItem());
                scriptValores_local = mc.concatenarCadena(scriptValores_local, "var " + pCampo.getNombreCampo() + "_" + idValorMaestro_local + "=new Array");

                listaDetalle_local = getAdministradorBaseDatosSisnet().obtenerListaIdValoresDetalleDeMaestro(pCampo.getIdCampo(), idValorMaestro_local, 0);

                scriptValores_local = mc.concatenarCadena(scriptValores_local, concatenarDescripcionesLista(listaDetalle_local, nombreTablaDetalle_local, listaRestriccionesCampoValores_local, excluirValores_local, existenRestricciones_local) + "\n");

                scriptValores_local = mc.concatenarCadena(scriptValores_local, "var ID" + pCampo.getNombreCampo() + "_" + idValorMaestro_local + "=new Array");

                scriptValores_local = mc.concatenarCadena(scriptValores_local, concatenarIdentificadoresLista(listaDetalle_local, nombreTablaDetalle_local, listaRestriccionesCampoValores_local, excluirValores_local, existenRestricciones_local) + "\n");
            }

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "function cambiar" + pCampo.getNombreCampo() + "(){ \n" + "limpiarLista(" + "formularioIncluir" + '.' + pCampo.getNombreCampo() + "); \n" + "\t var idValorMaestro; \n\t idValorMaestro = document." + "formularioIncluir" + '.' + pNombreCampoMaestro + "[document." + "formularioIncluir" + '.' + pNombreCampoMaestro + ".selectedIndex].value; \n");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "if (idValorMaestro != 0){ \n\t lista" + pCampo.getNombreCampo() + " = eval(\"" + pCampo.getNombreCampo() + "_\" + idValorMaestro); \n" + "\t listaID" + pCampo.getNombreCampo() + " = eval(\"ID" + pCampo.getNombreCampo() + "_\" + idValorMaestro); \n" + "\t numero" + pCampo.getNombreCampo() + " = lista" + pCampo.getNombreCampo() + ".length; \n" + "\t document." + "formularioIncluir" + '.' + pCampo.getNombreCampo() + ".length = numero" + pCampo.getNombreCampo() + "; \n");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "for(i=0;i<numero" + pCampo.getNombreCampo() + ";i++){ \n" + "\t document." + "formularioIncluir" + '.' + pCampo.getNombreCampo() + ".options[i].value = listaID" + pCampo.getNombreCampo() + "[i]; \n" + "\t document." + "formularioIncluir" + '.' + pCampo.getNombreCampo() + ".options[i].text = lista" + pCampo.getNombreCampo() + "[i]; \n }\n} ");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "else{ \n\t document.formularioIncluir." + pCampo.getNombreCampo() + ".length = " + '1' + "; \n\t document." + "formularioIncluir" + '.' + pCampo.getNombreCampo() + ".options[0].value = \"-\"; \n" + "\t document." + "formularioIncluir" + '.' + pCampo.getNombreCampo() + ".options[0].text = \"-\"; \n } \n" + "document." + "formularioIncluir" + '.' + pCampo.getNombreCampo() + ".options[0].selected = true;  \n");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, conformarCodigoLimpiarListasDependientes(pCampo));
            scriptValores_local = mc.concatenarCadena(scriptValores_local, "}; \n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            listaDetalle_local = null;
            iteradorMaestro_local = null;
            nombreTablaDetalle_local = null;
            listaIdValoresMaestro_local = null;
            valorMaestro_local = null;
            listaRestriccionesCampoValores_local = null;
        }

        return scriptValores_local;
    }
    private String insertarJavascriptListaDependiente(ListaCampo pListaCampo)
    {
        String javaScriptListaDependiente_local = "";
        Campo campoMaestro_local = null;
        Campo campo_local = null;
        Iterator iterador_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javaScriptListaDependiente_local;
        }

        try
        {
            iterador_local = pListaCampo.iterator();
            while (iterador_local.hasNext())
            {
                campo_local = (Campo)iterador_local.next();
                if (campo_local.getListaDependiente() != ConstantesGeneral.VALOR_NULO)
                {
                    campoMaestro_local = campo_local.getListaDependiente();
                    if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(campo_local, campoMaestro_local))
                    {
                        javaScriptListaDependiente_local = mc.concatenarCadena(javaScriptListaDependiente_local, obtenerJavascriptListasDependientes(campoMaestro_local.getNombreCampo(), campo_local));
                    }
                }

                campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            campoMaestro_local = null;
            campo_local = null;
        }

        return javaScriptListaDependiente_local;
    }
    private String obtenerCadenaVerificar(String pNombreFormulario, Campo pCampo)
    {
        String cadenaVerificarCampo_local = "";

        if (pNombreFormulario == ConstantesGeneral.VALOR_NULO)
        {
            return cadenaVerificarCampo_local;
        }
        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return cadenaVerificarCampo_local;
        }

        try
        {
            if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "H"))
            {
                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, " validacionHoras(");
                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, pNombreFormulario + '.');

                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, pCampo.getNombreCampo() + ')');
            }
            else
            {

                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, " verificarCampo( document.");
                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, pNombreFormulario + '.');

                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, pCampo.getNombreCampo() + ',');

                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, pCampo.obtenerEquivalenteTipoDatoJavascript() + ',');

                cadenaVerificarCampo_local = mc.concatenarCadena(cadenaVerificarCampo_local, (!pCampo.esObligatorio() ? 1 : 0) + String.valueOf(')'));
            }

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return cadenaVerificarCampo_local;
    }
    private String insertarJavascriptVerificarCampos(String pNombreFormulario, ListaCampo pListaCampo, boolean pEsSoloConsulta)
    {
        String javascriptVerificarCampos_local = "";
        StringBuffer stringBuffer_local = null;
        String tipoDato_local = null;
        StringBuffer cadenaVerificarCampo_local = null;
        String sufijo_local = null;
        int contador_local = 0;
        ListaCadenas listaVerificarCampo_local = null;
        Iterator iteradorlistaCampos_local = null;
        Iterator iteradorListaEncriptar_local = null;
        Iterator iteradorListaVerificarCampo_local = null;
        Campo campo_local = null;

        if (pNombreFormulario == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptVerificarCampos_local;
        }
        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptVerificarCampos_local;
        }
        if (pEsSoloConsulta)
        {
            return javascriptVerificarCampos_local;
        }

        try
        {
            listaVerificarCampo_local = new ListaCadenas();
            sufijo_local = "&&\n";

            iteradorlistaCampos_local = pListaCampo.iterator();
            stringBuffer_local = new StringBuffer();
            stringBuffer_local.append("function verificarCampos() {\n if(");
            while (iteradorlistaCampos_local.hasNext())
            {
                campo_local = (Campo)iteradorlistaCampos_local.next();

                if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local))
                {
                    tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
                    if (!campo_local.getRestriccionCampo().esLlavePrimaria() && campo_local.esModificable() && !mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getTipoHabilitacion() != 3 && campo_local.esObligatorio())
                    {

                        if (mc.sonCadenasIguales(tipoDato_local, "T") || mc.sonCadenasIguales(tipoDato_local, "X") || mc.sonCadenasIguales(tipoDato_local, "K") || mc.sonCadenasIguales(tipoDato_local, "E") || mc.sonCadenasIguales(tipoDato_local, "R") || mc.sonCadenasIguales(tipoDato_local, "BB") || mc.sonCadenasIguales(tipoDato_local, "GG") || mc.sonCadenasIguales(tipoDato_local, "H") || mc.sonCadenasIguales(tipoDato_local, "FF") || mc.sonCadenasIguales(tipoDato_local, "LL") || mc.sonCadenasIguales(tipoDato_local, "J") || mc.sonCadenasIguales(tipoDato_local, "NN") || mc.sonCadenasIguales(tipoDato_local, "C") || mc.esCadenaNumerica(tipoDato_local, true))
                        {

                            cadenaVerificarCampo_local = new StringBuffer();
                            cadenaVerificarCampo_local.append(obtenerCadenaVerificar(pNombreFormulario, campo_local));
                            listaVerificarCampo_local.adicionar(cadenaVerificarCampo_local.toString());
                            cadenaVerificarCampo_local = null;
                        }
                    }
                    if (!campo_local.getRestriccionCampo().esLlavePrimaria() && campo_local.esModificable() && !mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.getTipoHabilitacion() != 3)
                    {

                        if (mc.sonCadenasIguales(tipoDato_local, "C") && !campo_local.excluirValidacionContrasena())
                        {

                            cadenaVerificarCampo_local = new StringBuffer();
                            cadenaVerificarCampo_local.append("validarContrasena( document." + pNombreFormulario);
                            cadenaVerificarCampo_local.append(String.valueOf('.'));
                            cadenaVerificarCampo_local.append(campo_local.getNombreCampo());
                            cadenaVerificarCampo_local.append(".value )");
                            listaVerificarCampo_local.adicionar(cadenaVerificarCampo_local.toString());
                            cadenaVerificarCampo_local = null;
                        }
                        if (mc.sonCadenasIguales(tipoDato_local, "C") && pListaCampo.existeCampoConfirmarContrasena() && !mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldconfirmarcontrasena"))
                        {

                            cadenaVerificarCampo_local = new StringBuffer();
                            cadenaVerificarCampo_local.append("verificarContrasena(document." + pNombreFormulario);
                            cadenaVerificarCampo_local.append(String.valueOf('.'));
                            cadenaVerificarCampo_local.append(campo_local.getNombreCampo());
                            cadenaVerificarCampo_local.append(".value , document.");
                            cadenaVerificarCampo_local.append(pNombreFormulario);
                            cadenaVerificarCampo_local.append(String.valueOf('.'));
                            cadenaVerificarCampo_local.append(mc.convertirAMayusculas("fldconfirmarcontrasena"));
                            cadenaVerificarCampo_local.append(".value )");
                            listaVerificarCampo_local.adicionar(cadenaVerificarCampo_local.toString());
                            cadenaVerificarCampo_local = null;
                        }
                    }
                }
            }
            if (listaVerificarCampo_local.contarElementos() > 0)
            {
                iteradorListaVerificarCampo_local = listaVerificarCampo_local.iterator();
                while (iteradorListaVerificarCampo_local.hasNext())
                {
                    contador_local++;
                    stringBuffer_local.append(iteradorListaVerificarCampo_local.next().toString());
                    if (contador_local != listaVerificarCampo_local.size())
                    {
                        stringBuffer_local.append(sufijo_local);
                    }
                }
                stringBuffer_local.append("){ \n");
            }
            else
            {
                stringBuffer_local = null;
                stringBuffer_local = new StringBuffer();
                stringBuffer_local.append("");
            }
            iteradorListaEncriptar_local = pListaCampo.iterator();
            while (iteradorListaEncriptar_local.hasNext())
            {
                campo_local = (Campo)iteradorListaEncriptar_local.next();
                if (campo_local.esModificable() && mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "C"))
                {

                    stringBuffer_local.append("document." + pNombreFormulario + '.');
                    stringBuffer_local.append(campo_local.getNombreCampo());
                    stringBuffer_local.append(".value = hex_md5( document." + pNombreFormulario + '.');

                    stringBuffer_local.append(campo_local.getNombreCampo() + ".value); \n");
                }
            }

            if (!mc.esCadenaVacia(stringBuffer_local.toString()))
            {
                stringBuffer_local.append("return true; \n }\n");
                stringBuffer_local.append("else \n return false; \n }; \n");
            }
            else
            {
                stringBuffer_local.append("function verificarCampos() { return true; } \n");
            }
            javascriptVerificarCampos_local = stringBuffer_local.toString();
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            sufijo_local = null;
            tipoDato_local = null;
            campo_local = null;
            listaVerificarCampo_local = null;
            cadenaVerificarCampo_local = null;
            iteradorlistaCampos_local = null;
            iteradorListaEncriptar_local = null;
            iteradorListaVerificarCampo_local = null;
            stringBuffer_local = null;
        }

        return javascriptVerificarCampos_local;
    }
    private String conformarJavascriptRecordarCambioPlantilla()
    {
        String javascriptRecordarCambioPlantilla_local = "";

        try
        {
            javascriptRecordarCambioPlantilla_local = mc.concatenarCadena(javascriptRecordarCambioPlantilla_local, "function recordarCambioPlantilla(){\n");

            javascriptRecordarCambioPlantilla_local = mc.concatenarCadena(javascriptRecordarCambioPlantilla_local, "if(formularioIncluir.FLDPLANTILLAUTILIZAR.value != 0){\n");

            javascriptRecordarCambioPlantilla_local = mc.concatenarCadena(javascriptRecordarCambioPlantilla_local, "return confirm(\"Recuerde que ya existe un documento almacenado, al utilizar una plantilla el contenido del documento ser\u00e1 reemplazado. ¿Desea Continuar?\"); \n");

            javascriptRecordarCambioPlantilla_local = mc.concatenarCadena(javascriptRecordarCambioPlantilla_local, "} \n return true; \n} \n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return javascriptRecordarCambioPlantilla_local;
    }
    private String insertarJavascriptRecordarCambioPlantilla(GrupoInformacion pGrupoInformacion, boolean pEsModificacion)
    {
        String javascriptRecordarCambioPlantilla_local = "";

        if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptRecordarCambioPlantilla_local;
        }

        try
        {
            if (pEsModificacion && getManejadorSesion().obtenerMotorAplicacion().comprobarCamposDocumentosConPlantilla(pGrupoInformacion))
            {
                javascriptRecordarCambioPlantilla_local = conformarJavascriptRecordarCambioPlantilla();
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        return javascriptRecordarCambioPlantilla_local;
    }
    private String obtenerOperacionesSegunTiposOperadores(Campo pCampo)
    {
        String eventoSegunOperadores_local = "";
        int numeroDecimales_local = 0;
        boolean esNumeroEnteroOrigenUno_local = false;
        boolean esNumeroRealOrigenUno_local = false;
        boolean esFechaOrigenUno_local = false;
        boolean esNumeroRealOrigenDos_local = false;
        boolean esFechaOrigenDos_local = false;
        String nombreCampoCalculado_local = null;
        String nombreCampoOrigenUno_local = null;
        String nombreCampoOrigenDos_local = null;
        String nombreCampoUno_local = null;
        String nombreCampoDos_local = null;
        String tipoCampoOrigenUno_local = null;
        String tipoCampoOrigenDos_local = null;
        String funcion_local = null;

        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return eventoSegunOperadores_local;
        }

        try
        {
            numeroDecimales_local = pCampo.getFormatoCampo().getNumeroDecimales();
            nombreCampoCalculado_local = mc.concatenarCadena("document.formularioIncluir.", pCampo.getNombreCampo());

            nombreCampoOrigenUno_local = mc.concatenarCadena("document.formularioIncluir.", pCampo.getCalculoCampo().getCampoOrigenUno().getNombreCampo());

            nombreCampoOrigenDos_local = mc.concatenarCadena("document.formularioIncluir.", pCampo.getCalculoCampo().getCampoOrigenDos().getNombreCampo());

            tipoCampoOrigenUno_local = pCampo.getCalculoCampo().getCampoOrigenUno().getFormatoCampo().getTipoDato();
            tipoCampoOrigenDos_local = pCampo.getCalculoCampo().getCampoOrigenDos().getFormatoCampo().getTipoDato();

            esNumeroEnteroOrigenUno_local = mc.sonCadenasIguales(tipoCampoOrigenUno_local, "E");
            esNumeroRealOrigenUno_local = mc.sonCadenasIguales(tipoCampoOrigenUno_local, "R");
            esFechaOrigenUno_local = mc.sonCadenasIguales(tipoCampoOrigenUno_local, "F");
            esNumeroRealOrigenDos_local = mc.sonCadenasIguales(tipoCampoOrigenDos_local, "R");
            esFechaOrigenDos_local = mc.sonCadenasIguales(tipoCampoOrigenDos_local, "F");

            if ((esFechaOrigenUno_local && esNumeroRealOrigenDos_local) || (esNumeroRealOrigenUno_local && esFechaOrigenDos_local))
            {
                eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, "this.operacionNoValida(); \n ");

                return eventoSegunOperadores_local;
            }

            switch (pCampo.getCalculoCampo().getCampoCalculado())
            {
                case 5:
                    if (esFechaOrigenUno_local && esFechaOrigenDos_local)
                    {
                        eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, "this.operacionNoValida(); \n ");

                        break;
                    }
                    if (esNumeroEnteroOrigenUno_local && esFechaOrigenDos_local)
                    {
                        nombreCampoUno_local = nombreCampoOrigenDos_local;
                        nombreCampoDos_local = nombreCampoOrigenUno_local;
                        funcion_local = "this.sumarDiasFecha(";
                    }
                    else
                    {
                        if (esFechaOrigenUno_local)
                        {
                            funcion_local = "this.sumarDiasFecha(";
                        }
                        else
                        {
                            funcion_local = "this.sumarValores(";
                        }
                        nombreCampoUno_local = nombreCampoOrigenUno_local;
                        nombreCampoDos_local = nombreCampoOrigenDos_local;
                    }
                    eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, funcion_local + nombreCampoUno_local + ".value " + ',' + nombreCampoDos_local + ".value " + ',' + nombreCampoCalculado_local + ".id ");

                    if (mc.sonCadenasIguales(funcion_local, "this.sumarValores("))
                    {
                        eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, String.valueOf(',') + String.valueOf(numeroDecimales_local) + String.valueOf(',') + ((mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "GG")) ? 1 : 0));
                    }

                    eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, String.valueOf(')') + String.valueOf(';') + " \n ");
                    break;

                case 6:
                    if (esNumeroEnteroOrigenUno_local && esFechaOrigenDos_local)
                    {
                        nombreCampoUno_local = nombreCampoOrigenDos_local;
                        nombreCampoDos_local = nombreCampoOrigenUno_local;
                        funcion_local = "this.restarDiasFecha(";
                    }
                    else
                    {
                        if (esFechaOrigenUno_local && esFechaOrigenDos_local)
                        {
                            funcion_local = "this.restarDosFechas(";
                        }
                        else if (esFechaOrigenUno_local)
                        {
                            funcion_local = "this.restarDiasFecha(";
                        }
                        else
                        {
                            funcion_local = "this.restarValores(";
                        }

                        nombreCampoUno_local = nombreCampoOrigenUno_local;
                        nombreCampoDos_local = nombreCampoOrigenDos_local;
                    }
                    eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, funcion_local + nombreCampoUno_local + ".value " + ',' + nombreCampoDos_local + ".value " + ',' + nombreCampoCalculado_local + ".id ");

                    if (mc.sonCadenasIguales(funcion_local, "this.restarValores("))
                    {
                        eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, String.valueOf(',') + String.valueOf(numeroDecimales_local) + String.valueOf(',') + ((mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "GG")) ? 1 : 0));
                    }

                    eventoSegunOperadores_local = mc.concatenarCadena(eventoSegunOperadores_local, String.valueOf(')') + String.valueOf(';') + " \n ");
                    break;
            }

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            funcion_local = null;
            nombreCampoUno_local = null;
            nombreCampoDos_local = null;
            tipoCampoOrigenUno_local = null;
            tipoCampoOrigenDos_local = null;
            nombreCampoCalculado_local = null;
            nombreCampoOrigenUno_local = null;
            nombreCampoOrigenDos_local = null;
        }

        return eventoSegunOperadores_local;
    }
    private String insertarJavascriptEjecutarOperaciones(ListaCampo pListaCampo)
    {
        String javascriptEjecutarOperaciones_local = "";
        int numeroDecimales_local = -1;
        boolean esCampoValor_local = false;
        boolean esCampoOrigen_local = false;
        String document_local = null;
        String operacion_local = null;
        String nombreCampoCalculado_local = null;
        String valorCampoValor_local = null;
        String valorCampoOrigenUno_local = null;
        String valorCampoOrigenDos_local = null;
        ListaCadenas listaCadenas_local = null;
        ListaCampo listaCampo_local = null;
        Iterator iteradorCampos_local = null;
        Iterator iterador_local = null;
        Campo campo_local = null;
        Campo campoLista_local = null;
        boolean verificarCamposMismoGrupoInformacion_local = false;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptEjecutarOperaciones_local;
        }

        try
        {
            javascriptEjecutarOperaciones_local = "\n function ejecutarOperaciones(){ \n ";
            listaCadenas_local = new ListaCadenas();
            document_local = mc.concatenarCadena("document.", "formularioIncluir.");

            iteradorCampos_local = pListaCampo.iterator();
            while (iteradorCampos_local.hasNext())
            {
                campoLista_local = (Campo)iteradorCampos_local.next();
                esCampoValor_local = getAdministradorBaseDatosSisnet().verificarEsCampoValor(campoLista_local.getIdCampo());
                esCampoOrigen_local = getAdministradorBaseDatosSisnet().verificarEsCampoOrigen(campoLista_local.getIdCampo());
                if (esCampoValor_local || esCampoOrigen_local)
                {
                    listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposCalculadosGrupoInformacion(campoLista_local);

                    if (listaCampo_local != ConstantesGeneral.VALOR_NULO)
                    {
                        iterador_local = listaCampo_local.iterator();
                        while (iterador_local.hasNext())
                        {
                            campo_local = (Campo)iterador_local.next();
                            nombreCampoCalculado_local = document_local + campo_local.getNombreCampo();
                            numeroDecimales_local = campo_local.getFormatoCampo().getNumeroDecimales();
                            if (esCampoValor_local && campo_local.getCalculoCampo().getCampoValor() != ConstantesGeneral.VALOR_NULO)
                            {
                                if (campo_local.getCalculoCampo().getCampoValor().esTipoDatoTabla() || campo_local.getCalculoCampo().getCampoValor().esCampoEnlazado())
                                {

                                    if (campo_local.esTipoDatoTexto())
                                    {
                                        valorCampoValor_local = mc.concatenarCadena(document_local + campo_local.getCalculoCampo().getCampoValor().getNombreCampo(), '[' + document_local + campo_local.getCalculoCampo().getCampoValor().getNombreCampo() + ".selectedIndex]" + ".text ");

                                    }
                                    else
                                    {

                                        valorCampoValor_local = mc.concatenarCadena(document_local, campo_local.getCalculoCampo().getCampoValor().getNombreCampo() + ".value ");
                                    }

                                }
                                else
                                {

                                    valorCampoValor_local = mc.concatenarCadena(document_local, campo_local.getCalculoCampo().getCampoValor().getNombreCampo() + ".value ");
                                }
                            }

                            if (esCampoOrigen_local)
                            {
                                verificarCamposMismoGrupoInformacion_local = (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(campo_local, campo_local.getCalculoCampo().getCampoOrigenUno()) && getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(campo_local, campo_local.getCalculoCampo().getCampoOrigenDos()));

                                if (verificarCamposMismoGrupoInformacion_local)
                                {
                                    if (campo_local.getCalculoCampo().getCampoOrigenUno().esTipoDatoTabla() || campo_local.getCalculoCampo().getCampoOrigenUno().esCampoEnlazado())
                                    {

                                        if (campo_local.esTipoDatoTexto())
                                        {
                                            valorCampoOrigenUno_local = mc.concatenarCadena(document_local + campo_local.getCalculoCampo().getCampoOrigenUno().getNombreCampo(), '[' + document_local + campo_local.getCalculoCampo().getCampoOrigenUno().getNombreCampo() + ".selectedIndex]" + ".text ");

                                        }
                                        else
                                        {

                                            valorCampoOrigenUno_local = mc.concatenarCadena(document_local, campo_local.getCalculoCampo().getCampoOrigenUno().getNombreCampo() + ".value ");
                                        }

                                    }
                                    else
                                    {

                                        valorCampoOrigenUno_local = mc.concatenarCadena(document_local, campo_local.getCalculoCampo().getCampoOrigenUno().getNombreCampo() + ".value ");
                                    }

                                    if (campo_local.getCalculoCampo().getCampoOrigenDos().esTipoDatoTabla() || campo_local.getCalculoCampo().getCampoOrigenDos().esCampoEnlazado())
                                    {

                                        if (campo_local.esTipoDatoTexto())
                                        {
                                            valorCampoOrigenDos_local = mc.concatenarCadena(document_local + campo_local.getCalculoCampo().getCampoOrigenDos().getNombreCampo(), '[' + document_local + campo_local.getCalculoCampo().getCampoOrigenDos().getNombreCampo() + ".selectedIndex]" + ".text ");

                                        }
                                        else
                                        {

                                            valorCampoOrigenDos_local = mc.concatenarCadena(document_local, campo_local.getCalculoCampo().getCampoOrigenDos().getNombreCampo() + ".value ");
                                        }

                                    }
                                    else
                                    {

                                        valorCampoOrigenDos_local = mc.concatenarCadena(document_local, campo_local.getCalculoCampo().getCampoOrigenDos().getNombreCampo() + ".value ");
                                    }
                                }
                            }

                            operacion_local = "";
                            switch (campo_local.getCalculoCampo().getCampoCalculado())
                            {
                                case 2:
                                    operacion_local = mc.concatenarCadena(operacion_local, "this.copiarValorCampo(" + nombreCampoCalculado_local + ',');

                                    operacion_local = mc.concatenarCadena(operacion_local, valorCampoValor_local + ',' + campo_local.esTipoDatoNumerico() + ',' + numeroDecimales_local + ',');

                                    operacion_local = mc.concatenarCadena(operacion_local, String.valueOf(campo_local.getFormatoCampo().getLongitudCampo()));

                                    operacion_local = mc.concatenarCadena(operacion_local, String.valueOf(')') + String.valueOf(';') + " \n ");
                                    break;

                                case 13:
                                    operacion_local = "this.obtenerValorAbsoluto(" + document_local + campo_local.getCalculoCampo().getCampoValor().getNombreCampo() + ".id " + ',' + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;

                                case 14:
                                    operacion_local = "this.sumarUnidad(" + document_local + campo_local.getCalculoCampo().getCampoValor().getNombreCampo() + ".id " + ',' + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;
                                
                                case 15:
                                    operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ",17," + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;

                                case 16:
                                    operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ",20," + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;

                                case 17:
                                    operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ",21," + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;

                                case 18:
                                    operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ",22," + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;

                                case 19:
                                    operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ",23," + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;

                                case 20:
                                    //operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ',' + '24' + ',' + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    operacion_local = "this.evaluarCondicion(" + valorCampoValor_local + ",24," + nombreCampoCalculado_local + ".id " + ')' + ';' + " \n ";
                                    break;
                            }

                            if (verificarCamposMismoGrupoInformacion_local)
                            {
                                switch (campo_local.getCalculoCampo().getCampoCalculado())
                                {
                                    case 5:
                                    case 6:
                                        operacion_local = obtenerOperacionesSegunTiposOperadores(campo_local);
                                        break;
                                    case 7:
                                        operacion_local = "this.multiplicarValores(" + valorCampoOrigenUno_local + ',' + valorCampoOrigenDos_local + ',' + nombreCampoCalculado_local + ".id " + ',' + numeroDecimales_local + String.valueOf(',') + ((mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "GG")) ? 1 : 0) + ')' + ';' + " \n ";
                                        break;

                                    case 8:
                                        operacion_local = "this.dividirValores(" + valorCampoOrigenUno_local + ',' + valorCampoOrigenDos_local + ',' + nombreCampoCalculado_local + ".id " + ',' + numeroDecimales_local + String.valueOf(',') + ((mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "BB") || mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "GG")) ? 1 : 0) + ')' + ';' + " \n ";
                                        break;

                                    case 11:
                                        operacion_local = "this.concatenarValores(" + valorCampoOrigenUno_local + ',' + valorCampoOrigenDos_local + ',' + nombreCampoCalculado_local + ".id " + ',' + mc.colocarEntreComillasDobles(String.valueOf(' ')) + ',' + campo_local.getFormatoCampo().getLongitudCampo() + ')' + ';' + " \n ";
                                        break;

                                    case 12:
                                        operacion_local = "this.concatenarValores(" + valorCampoOrigenUno_local + ',' + valorCampoOrigenDos_local + ',' + nombreCampoCalculado_local + ".id " + ',' + mc.colocarEntreComillasDobles("") + ',' + campo_local.getFormatoCampo().getLongitudCampo() + ')' + ';' + " \n ";
                                        break;

                                    case 21:
                                        operacion_local = "this.concatenarValores(" + valorCampoOrigenUno_local + ',' + valorCampoOrigenDos_local + ',' + nombreCampoCalculado_local + ".id " + ',' + mc.colocarEntreComillasDobles(String.valueOf('-')) + ',' + campo_local.getFormatoCampo().getLongitudCampo() + ')' + ';' + " \n ";
                                        break;

                                    case 27:
                                        operacion_local = "this.concatenarValores(" + valorCampoOrigenUno_local + ',' + valorCampoOrigenDos_local + ',' + nombreCampoCalculado_local + ".id " + ',' + mc.colocarEntreComillasDobles("{S}") + ',' + campo_local.getFormatoCampo().getLongitudCampo() + ')' + ';' + " \n ";
                                        break;

                                    case 25:
                                        operacion_local = "this.copiarValorPrimerCampoConValor(" + nombreCampoCalculado_local + ',' + document_local + campo_local.getCalculoCampo().getCampoOrigenUno().getNombreCampo() + ',' + document_local + campo_local.getCalculoCampo().getCampoOrigenDos().getNombreCampo() + ',' + campo_local.esTipoDatoNumerico() + ',' + numeroDecimales_local + ')' + ';' + " \n ";
                                        break;

                                    case 26:
                                        if (campo_local.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO && campo_local.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO && getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposNumericosEntrePosicionesGrupoInformacion(campo_local.getGrupoInformacion(), campo_local.getCalculoCampo().getCampoOrigenUno().getPosicion(), campo_local.getCalculoCampo().getCampoOrigenDos().getPosicion()) != ConstantesGeneral.VALOR_NULO)
                                        {

                                            operacion_local = "this.sumarDesdeHasta" + campo_local.getNombreCampo() + "();\n ";
                                        }
                                        break;
                                }

                            }
                            if (!listaCadenas_local.verificarExistenciaCadena(operacion_local))
                            {
                                listaCadenas_local.adicionar(operacion_local);
                            }
                        }
                    }
                }
            }
            if (listaCadenas_local != ConstantesGeneral.VALOR_NULO)
            {
                iterador_local = listaCadenas_local.iterator();
                while (iterador_local.hasNext())
                {
                    javascriptEjecutarOperaciones_local = mc.concatenarCadena(javascriptEjecutarOperaciones_local, (String)iterador_local.next());
                }
            }

            javascriptEjecutarOperaciones_local = mc.concatenarCadena(javascriptEjecutarOperaciones_local, "}; \n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campo_local = null;
            iterador_local = null;
            document_local = null;
            operacion_local = null;
            listaCampo_local = null;
            campoLista_local = null;
            listaCadenas_local = null;
            iteradorCampos_local = null;
            valorCampoValor_local = null;
            valorCampoOrigenUno_local = null;
            valorCampoOrigenDos_local = null;
            nombreCampoCalculado_local = null;
        }

        return javascriptEjecutarOperaciones_local;
    }
    private String obtenerJavascriptCampoEnlaceDepende(int pIdAplicacion)
    {
        String scriptValores_local = "";
        int idCampoEnlazado_local = -1;
        int idAplicacionEnlace_local = -1;
        boolean esTipoTabla_local = false;
        String tipoDato_local = null;
        String arregloDescripciones_local = null;
        String arregloIdentificadores_local = null;
        ListaGeneral listaCamposEnlazados_local = null;
        ListaGeneral listaTiposDato_local = null;
        ListaGeneral listaCampos_local = null;
        Iterator iteratorCamposEnlazados_local = null;
        Iterator iteratorTipoDato_local = null;
        Iterator iteratorCampos_local = null;
        ItemLista itemTipoDato_local = null;
        ItemLista itemCampo_local = null;
        Campo campo_local = null;

        try
        {
            listaTiposDato_local = obtenerListaTiposDatoConTablas("");
            listaCamposEnlazados_local = getAdministradorBaseDatosSisnet().obtenerListaCamposEnlazadosAplicacion(pIdAplicacion);
            iteratorTipoDato_local = listaTiposDato_local.iterator();

            while (iteratorTipoDato_local.hasNext() && !esTipoTabla_local)
            {
                itemTipoDato_local = (ItemLista)iteratorTipoDato_local.next();
                tipoDato_local = itemTipoDato_local.getValorItem();
                esTipoTabla_local = mc.esCadenaNumerica(tipoDato_local, true);
                iteratorCamposEnlazados_local = listaCamposEnlazados_local.iterator();

                while (iteratorCamposEnlazados_local.hasNext())
                {
                    arregloDescripciones_local = "";
                    arregloIdentificadores_local = "";

                    idCampoEnlazado_local = Integer.parseInt(((ItemLista)iteratorCamposEnlazados_local.next()).getValorItem());
                    campo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(idCampoEnlazado_local);
                    idAplicacionEnlace_local = campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion();

                    if (esTipoTabla_local)
                    {
                        listaCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposTipoTablaAplicacion(idAplicacionEnlace_local, true);

                        tipoDato_local = "Ta";
                    }
                    else
                    {
                        listaCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposOrigenEnlace(idAplicacionEnlace_local, tipoDato_local);
                    }

                    arregloDescripciones_local = "var camposEnlaceTipo_" + tipoDato_local + idCampoEnlazado_local + "=new Array(";
                    arregloIdentificadores_local = "var idcamposEnlaceTipo_" + tipoDato_local + idCampoEnlazado_local + "=new Array(";

                    iteratorCampos_local = listaCampos_local.iterator();
                    if (listaCampos_local.contarElementos() > 0)
                    {
                        while (iteratorCampos_local.hasNext())
                        {
                            itemCampo_local = (ItemLista)iteratorCampos_local.next();
                            arregloDescripciones_local = mc.concatenarCadena(arregloDescripciones_local, mc.colocarEntreComillas(itemCampo_local.getNombreItem()));

                            arregloIdentificadores_local = mc.concatenarCadena(arregloIdentificadores_local, mc.colocarEntreComillas(itemCampo_local.getValorItem()));

                            if (iteratorCampos_local.hasNext())
                            {
                                arregloDescripciones_local = mc.concatenarCadena(arregloDescripciones_local, String.valueOf(','));

                                arregloIdentificadores_local = mc.concatenarCadena(arregloIdentificadores_local, String.valueOf(','));
                                continue;
                            }
                            arregloDescripciones_local = mc.concatenarCadena(arregloDescripciones_local, String.valueOf(')'));

                            arregloIdentificadores_local = mc.concatenarCadena(arregloIdentificadores_local, String.valueOf(')'));
                        }

                    }
                    else
                    {

                        arregloDescripciones_local = mc.concatenarCadena(arregloDescripciones_local, String.valueOf(')'));

                        arregloIdentificadores_local = mc.concatenarCadena(arregloIdentificadores_local, String.valueOf(')'));
                    }

                    scriptValores_local = mc.concatenarCadena(scriptValores_local, arregloDescripciones_local + ";\n");
                    scriptValores_local = mc.concatenarCadena(scriptValores_local, arregloIdentificadores_local + ";\n");
                }
            }
            scriptValores_local = mc.concatenarCadena(scriptValores_local, "function cambiarCamposOrigenEnlace(){ \n\t var tipoDato \n\t tipoDato = document.formularioIncluir." + mc.convertirAMayusculas("fldtipodato") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldtipodato") + ".selectedIndex].value; \n" + "if(verificarEsNumero (tipoDato)) { tipoDato = \"Ta\"; }" + "\t var tipoDatoEnlace \n\t tipoDatoEnlace = tipoDato + document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampoenlacedepende") + "[document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampoenlacedepende") + ".selectedIndex].value; \n");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "if (tipoDatoEnlace != ''){ \n\t listaCampos = eval(\"camposEnlaceTipo_\" + tipoDatoEnlace); \n\t listaIdCampos = eval(\"idcamposEnlaceTipo_\" + tipoDatoEnlace); \n\t numeroCampos = listaCampos.length; \n\t document.formularioIncluir." + mc.convertirAMayusculas("fldcampoorigenenlace") + ".length = numeroCampos; \n");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "for(i=0;i<numeroCampos;i++){ \n\t document.formularioIncluir." + mc.convertirAMayusculas("fldcampoorigenenlace") + ".options[i].value = listaIdCampos[i]; \n" + "\t document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampoorigenenlace") + ".options[i].text = listaCampos[i]; \n }\n} ");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "else{ \n\t document.formularioIncluir." + mc.convertirAMayusculas("fldcampoorigenenlace") + ".length = " + "1" + "; \n\t document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampoorigenenlace") + ".options[0].value = \"-\"; \n" + "\t document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampoorigenenlace") + ".options[0].text = \"-\"; \n } \n" + "document." + "formularioIncluir" + '.' + mc.convertirAMayusculas("fldcampoorigenenlace") + ".options[0].selected = true;  \n");

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "}; \n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            campo_local = null;
            tipoDato_local = null;
            itemCampo_local = null;
            listaCampos_local = null;
            itemTipoDato_local = null;
            listaTiposDato_local = null;
            iteratorCampos_local = null;
            iteratorTipoDato_local = null;
            listaCamposEnlazados_local = null;
            arregloDescripciones_local = null;
            arregloIdentificadores_local = null;
            iteratorCamposEnlazados_local = null;
        }

        return scriptValores_local;
    }
    private String insertarJavascriptCampoEnlaceDepende(int pIdAplicacion, ListaCampo pListaCampo)
    {
        String javaScriptTipoDato_local = "";
        Iterator iterador_local = null;
        Campo campo_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javaScriptTipoDato_local;
        }

        try
        {
            iterador_local = pListaCampo.iterator();
            while (iterador_local.hasNext())
            {
                campo_local = (Campo)iterador_local.next();
                if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldcampoenlacedepende"))
                {
                    javaScriptTipoDato_local = mc.concatenarCadena(javaScriptTipoDato_local, obtenerJavascriptCampoEnlaceDepende(pIdAplicacion));
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

        return javaScriptTipoDato_local;
    }
    private String obtenerJavascriptHabilitarListas(ListaCampo pListaCampo)
    {
        String scriptHabilitarListas_local = "";
        Iterator iterador_local = null;
        Campo campo_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return scriptHabilitarListas_local;
        }

        try
        {
            if (pListaCampo.contarElementos() > 0)
            {
                iterador_local = pListaCampo.iterator();
                while (iterador_local.hasNext())
                {
                    campo_local = (Campo)iterador_local.next();
                    if (mc.esCadenaNumerica(campo_local.getFormatoCampo().getTipoDato(), true))
                    {
                        if (mc.sonCadenasIguales(scriptHabilitarListas_local, ""))
                        {
                            scriptHabilitarListas_local = "function habilitarListas(){ \n ";
                        }
                        scriptHabilitarListas_local = mc.concatenarCadena(scriptHabilitarListas_local, "document.formularioIncluir." + campo_local.getNombreCampo() + ".disabled = false; \n");
                    }

                    campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
                }
                if (!mc.sonCadenasIguales(scriptHabilitarListas_local, ""))
                {
                    scriptHabilitarListas_local = mc.concatenarCadena(scriptHabilitarListas_local, " } \n");
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

        return scriptHabilitarListas_local;
    }
    private String obtenerJavascriptHabilitaCampos(String pNombreCampoHabilita, Campo pCampo)
    {
        String scriptValores_local = "";
        String valores_local = null;
        boolean habilitarBotones_local = false;
        ListaGeneral listaValoresHabilitados_local = null;
        Iterator iterador_local = null;

        if (pNombreCampoHabilita == ConstantesGeneral.VALOR_NULO)
        {
            return scriptValores_local;
        }
        if (pCampo == ConstantesGeneral.VALOR_NULO)
        {
            return scriptValores_local;
        }

        try
        {
            scriptValores_local = mc.concatenarCadena(scriptValores_local, "function habilitar" + pCampo.getNombreCampo() + "(){ \n if(");
            listaValoresHabilitados_local = getAdministradorBaseDatosSisnet().obtenerListaValoresHabilitadosCampo(pCampo.getIdCampo());
            iterador_local = listaValoresHabilitados_local.iterator();
            habilitarBotones_local = (pCampo.getTipoHabilitacion() != 3 && mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "F"));

            while (iterador_local.hasNext())
            {
                if (!mc.sonCadenasIguales(valores_local, ""))
                {
                    valores_local = mc.concatenarCadena(valores_local, " || ");
                }
                valores_local = mc.concatenarCadena(valores_local, "(document.formularioIncluir." + pNombreCampoHabilita + ".value == '");
                valores_local = mc.concatenarCadena(valores_local, ((ItemLista)iterador_local.next()).getValorItem() + "')");
            }

            scriptValores_local = mc.concatenarCadena(scriptValores_local, valores_local + "){ \n \t");

            if (pCampo.esCampoCalculado())
            {
                if (!pCampo.esTipoDatoTabla() && !pCampo.esCampoEnlazado())
                {
                    scriptValores_local = mc.concatenarCadena(scriptValores_local, "document.formularioIncluir." + pCampo.getNombreCampo() + ".disabled = false; \n ");
                }
            }
            else
            {

                scriptValores_local = mc.concatenarCadena(scriptValores_local, "document.formularioIncluir." + pCampo.getNombreCampo() + ".disabled = false; \n ");
            }

            if (habilitarBotones_local)
            {
                scriptValores_local = mc.concatenarCadena(scriptValores_local, " \n \t document.formularioIncluir." + mc.concatenarCadena("btn", pCampo.getNombreCampo()) + ".disabled = false; \n ");
            }

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "} else { \n ");
            if (pCampo.esBorrarValorNoHabilitado())
            {
                if (pCampo.esTipoDatoNumerico() || pCampo.esTipoDatoTabla())
                {
                    scriptValores_local = mc.concatenarCadena(scriptValores_local, "\t document.formularioIncluir." + pCampo.getNombreCampo() + ".value = 0; \n");
                }
                else
                {

                    scriptValores_local = mc.concatenarCadena(scriptValores_local, "\t document.formularioIncluir." + pCampo.getNombreCampo() + ".value = ''; \n");
                }
            }

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "\t document.formularioIncluir." + pCampo.getNombreCampo() + ".disabled = true; \n ");

            if (habilitarBotones_local)
            {
                scriptValores_local = mc.concatenarCadena(scriptValores_local, " \n \t document.formularioIncluir." + mc.concatenarCadena("btn", pCampo.getNombreCampo()) + ".disabled = true; \n ");
            }

            scriptValores_local = mc.concatenarCadena(scriptValores_local, "} \n } \n");
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            valores_local = null;
            iterador_local = null;
            listaValoresHabilitados_local = null;
        }

        return scriptValores_local;
    }
    private String insertarJavascriptHabilitarCampos(ListaCampo pListaCampo)
    {
        String javascriptHabilitarCampos_local = "";
        Iterator iterador_local = null;
        Campo campo_local = null;
        Campo campoHabilita_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return javascriptHabilitarCampos_local;
        }

        try
        {
            iterador_local = pListaCampo.iterator();
            while (iterador_local.hasNext())
            {
                campo_local = (Campo)iterador_local.next();
                if (campo_local.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO)
                {
                    campoHabilita_local = campo_local.getHabilitadoPor();
                    if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(campo_local, campoHabilita_local))
                    {
                        javascriptHabilitarCampos_local = mc.concatenarCadena(javascriptHabilitarCampos_local, obtenerJavascriptHabilitaCampos(campoHabilita_local.getNombreCampo(), campo_local));
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
        return javascriptHabilitarCampos_local;
    }
    protected void insertarMensajePagina(Pagina pPagina)
    {
        if (pPagina == ConstantesGeneral.VALOR_NULO)
        {
            return;
        }

        try
        {
            if (getManejadorSesion() != ConstantesGeneral.VALOR_NULO && !mc.esCadenaVacia(getManejadorSesion().obtenerMensajeEventos()))
            {

                pPagina.setMensaje(getGeneradorComponentesHtml().obtenerMensajesPagina(getManejadorSesion().obtenerMensajeEventos(), getManejadorSesion().obtenerTipoMensajeEventos()));

                getManejadorSesion().actualizarMensajeEventos("");
                getManejadorSesion().actualizarTipoMensajeEventos(-1);
                getManejadorSesion().obtenerManejadorEventos().setMensajeEventos("");
                getManejadorSesion().obtenerManejadorEventos().setHuboError(false);
            }
            else if ((String)getManejadorRequest().obtenerValorAtributoRequest("tipoerror", getManejadorSesion()) != ConstantesGeneral.VALOR_NULO)
            {

                pPagina.setMensaje(getGeneradorComponentesHtml().obtenerMensajesPagina(Integer.parseInt(getManejadorRequest().obtenerValorAtributoRequest("numeroerror", getManejadorSesion()).toString()), Integer.parseInt(getManejadorRequest().obtenerValorAtributoRequest("tipoerror", getManejadorSesion()).toString())));

                if (getManejadorSesion().obtenerValorAtributoSesion("listaNavegacion") != ConstantesGeneral.VALOR_NULO)
                {

                    getManejadorSesion().actualizarNumeroError(0);
                    getManejadorSesion().actualizarTipoError(0);
                }
                if (getManejadorSesion().obtenerManejadorEventos() != ConstantesGeneral.VALOR_NULO)
                {
                    getManejadorSesion().obtenerManejadorEventos().setMensajeEventos("");
                    getManejadorSesion().obtenerManejadorEventos().setHuboError(false);
                }

            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
    }
    private String obtenerJavascriptSumaDesdeHasta(ListaCampo pListaCampo)
    {
        String scriptSumaDesdeHasta_local = "";
        Iterator iteradorCampos_local = null;
        Iterator iterador_local = null;
        ListaCampo listaCampo_local = null;
        Campo campoCalculado_local = null;
        Campo campo_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return scriptSumaDesdeHasta_local;
        }

        try
        {
            iteradorCampos_local = pListaCampo.iterator();
            while (iteradorCampos_local.hasNext())
            {
                campoCalculado_local = (Campo)iteradorCampos_local.next();
                if (campoCalculado_local.getCalculoCampo().getCampoCalculado() == 26 && campoCalculado_local.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO && campoCalculado_local.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO)
                {

                    listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposNumericosEntrePosicionesGrupoInformacion(campoCalculado_local.getGrupoInformacion(), campoCalculado_local.getCalculoCampo().getCampoOrigenUno().getPosicion(), campoCalculado_local.getCalculoCampo().getCampoOrigenDos().getPosicion());

                    if (listaCampo_local != ConstantesGeneral.VALOR_NULO && listaCampo_local.contarElementos() > 0)
                    {

                        scriptSumaDesdeHasta_local = mc.concatenarCadena(scriptSumaDesdeHasta_local, "function sumarDesdeHasta" + campoCalculado_local.getNombreCampo() + "() { \n" + "    var suma = 0; \n" + "    suma = ");

                        iterador_local = null;
                        iterador_local = listaCampo_local.iterator();
                        while (iterador_local.hasNext())
                        {
                            campo_local = (Campo)iterador_local.next();
                            scriptSumaDesdeHasta_local = mc.concatenarCadena(scriptSumaDesdeHasta_local, "parseFloat(document.formularioIncluir." + campo_local.getNombreCampo() + ".value)");

                            if (iterador_local.hasNext())
                            {
                                scriptSumaDesdeHasta_local = mc.concatenarCadena(scriptSumaDesdeHasta_local, " + ");
                            }
                            campo_local = (Campo)ConstantesGeneral.VALOR_NULO;
                        }
                        scriptSumaDesdeHasta_local = mc.concatenarCadena(scriptSumaDesdeHasta_local, "; \n    suma = redondearNumero(suma, " + campoCalculado_local.getFormatoCampo().getNumeroDecimales() + "); \n" + "    document.formularioIncluir." + campoCalculado_local.getNombreCampo() + ".value = suma; \n");

                        scriptSumaDesdeHasta_local = mc.concatenarCadena(scriptSumaDesdeHasta_local, " }; \n");
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
            campo_local = null;
            iterador_local = null;
            listaCampo_local = null;
            iteradorCampos_local = null;
            campoCalculado_local = null;
        }

        return scriptSumaDesdeHasta_local;
    }
    private String insertarJavascriptObtenerListasCamposTodasLasAplicaciones(int pIdAplicacion, ListaCampo pListaCampo)
    {
        String javascriptObtenerListaCampos_local = "";
        ListaAplicacion listaAplicacion_local = null;
        Iterator iterador_local = null;
        Aplicacion aplicacion_local = null;
        ListaGeneral listaGeneralCampos_local = null;

        try
        {
            if (pListaCampo.obtenerCampoPorNombre("fldcampodestinofiltrado") != ConstantesGeneral.VALOR_NULO)
            {
                listaAplicacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaAplicaciones(pIdAplicacion, false);

                listaAplicacion_local.concatenar(getManejadorSesion().obtenerMotorAplicacion().obtenerListaAplicacionesTabla(pIdAplicacion, false));

                iterador_local = listaAplicacion_local.iterator();
                while (iterador_local.hasNext())
                {
                    aplicacion_local = (Aplicacion)iterador_local.next();
                    javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function obtenerListaCamposAplicacion" + aplicacion_local.getIdAplicacion() + "(Control){ \n");

                    listaGeneralCampos_local = getAdministradorBaseDatosSisnet().obtenerListaCamposAplicacion(aplicacion_local.getIdAplicacion(), false, false, false);

                    javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, conformarListaOpcionesConCampos(listaGeneralCampos_local));

                    javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "}\n");
                }
                iterador_local = null;
                iterador_local = listaAplicacion_local.iterator();
                javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "function llenarCampoDestinoFiltrado(){\n  if(document.formularioIncluir." + mc.convertirAMayusculas("fldenlazado") + ".value != " + mc.colocarEntreComillasDobles(String.valueOf(0)) + "){\n" + "     limpiarLista(document.formularioIncluir." + mc.convertirAMayusculas("fldcampodestinofiltrado") + "); \n     var aplicacionEnlazada_local = parseInt(document.formularioIncluir." + mc.convertirAMayusculas("fldenlazado") + ".value); \n");

                while (iterador_local.hasNext())
                {
                    aplicacion_local = (Aplicacion)iterador_local.next();
                    javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "     if(aplicacionEnlazada_local == " + aplicacion_local.getIdAplicacion() + "){\n" + "        obtenerListaCamposAplicacion" + aplicacion_local.getIdAplicacion() + "(" + "document.formularioIncluir." + mc.convertirAMayusculas("fldcampodestinofiltrado") + ");\n" + "     }\n");
                }

                javascriptObtenerListaCampos_local = mc.concatenarCadena(javascriptObtenerListaCampos_local, "  }\n}\n");
            }
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            iterador_local = null;
            aplicacion_local = null;
            listaAplicacion_local = null;
            listaGeneralCampos_local = null;
        }

        return javascriptObtenerListaCampos_local;
    }
    private String obtenerSeccionJavascript(ListaCampo pListaCampo, String pNombreFormulario, boolean pEsSoloConsulta)
    {
        String seccionJavascript_local = "";
        StringBuffer stringBuffer_local = null;
        boolean existenCamposDependientesTabla_local = false;
        String scriptHabilitarListas_local = null;
        GrupoInformacion grupoInformacion_local = null;

        try
        {
            stringBuffer_local = new StringBuffer();
            stringBuffer_local.append(insertarJavascriptVerificarCampos(pNombreFormulario, pListaCampo, pEsSoloConsulta));
            stringBuffer_local.append(insertarJavascriptListaDependiente(pListaCampo));
            if (getManejadorSesion().obtenerAplicacionActual() != ConstantesGeneral.VALOR_NULO)
            {
                stringBuffer_local.append(insertarJavascriptCampoEnlaceDepende(getManejadorSesion().obtenerAplicacionActual().getIdAplicacion(), pListaCampo));
            }

            stringBuffer_local.append(insertarJavascriptHabilitarCampos(pListaCampo));
            scriptHabilitarListas_local = obtenerJavascriptHabilitarListas(pListaCampo);
            existenCamposDependientesTabla_local = !mc.sonCadenasIguales(scriptHabilitarListas_local, "");
            stringBuffer_local.append(scriptHabilitarListas_local);
            stringBuffer_local.append(insertarJavascriptHabilitarDependientes(pListaCampo, existenCamposDependientesTabla_local));

            if (getManejadorSesion().obtenerMotorAplicacion() != ConstantesGeneral.VALOR_NULO)
            {
                if (mc.sonCadenasIguales(getManejadorSesion().obtenerEstadoActual(), "Modificando"))
                {
                    grupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerGrupoInformacionPorId(getManejadorSesion().obtenerValorLlavePrimariaAnterior());

                }
                else if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO)
                {
                    grupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerGrupoInformacionPorId(getManejadorSesion().obtenerValorLlavePrimaria());
                }

                stringBuffer_local.append(insertarJavascriptValidarConfiguracionCalculos(grupoInformacion_local, pListaCampo));
                if (grupoInformacion_local != ConstantesGeneral.VALOR_NULO && grupoInformacion_local.getAplicacion() != ConstantesGeneral.VALOR_NULO)
                {
                    stringBuffer_local.append(insertarJavascriptObtenerListasCamposTodasLasAplicaciones(grupoInformacion_local.getAplicacion().getIdAplicacion(), pListaCampo));
                }

                stringBuffer_local.append(insertarJavascriptRecordarCambioPlantilla(getManejadorSesion().obtenerGrupoInformacionActual(), mc.sonCadenasIguales(getManejadorSesion().obtenerEstadoActual(), "Modificando")));
            }

            stringBuffer_local.append(obtenerJavascriptSumaDesdeHasta(pListaCampo));
            stringBuffer_local.append(insertarJavascriptEjecutarOperaciones(pListaCampo));
            seccionJavascript_local = stringBuffer_local.toString();
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            scriptHabilitarListas_local = null;
            grupoInformacion_local = null;
            stringBuffer_local = null;
        }

        return seccionJavascript_local;
    }
    protected String obtenerBloqueHeadPagina(ListaCampo pListaCampo, String pNombreFormulario, int pNivelesAnterioresDirectorio, boolean pExisteActuacion, boolean pEsSoloConsulta)
    {
        String encabezadoPagina_local = "";
        StringBuffer stringBuffer_local = null;

        if (pListaCampo == ConstantesGeneral.VALOR_NULO)
        {
            return "";
        }
        if (pNombreFormulario == ConstantesGeneral.VALOR_NULO)
        {
            return "";
        }

        try
        {
            stringBuffer_local = new StringBuffer();
            stringBuffer_local.append(getGeneradorComponentesHtml().abrirHead());
            stringBuffer_local.append(getGeneradorComponentesHtml().getMetaData());
            stringBuffer_local.append(getGeneradorComponentesHtml().getTituloPagina());
            stringBuffer_local.append(getGeneradorComponentesHtml().getHojaEstiloSisnet(pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().getHojaEstiloCalendario(pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/calendario/", "calendario.js", pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/calendario/", "calendario-espanol.js", pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/calendario/", "mostrarcalendario.js", pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "encripcion.js", pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "jquery-3.6.0.min.js", pNivelesAnterioresDirectorio));
            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "sisnet.js?v=1", pNivelesAnterioresDirectorio));

            stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "operaciones.js", pNivelesAnterioresDirectorio));

            if (pExisteActuacion)
            {
                stringBuffer_local.append(getGeneradorComponentesHtml().incluirLibreriaJavascript("../", "fckeditor.js", 0));
            }

            stringBuffer_local.append(getGeneradorComponentesHtml().abrirBloqueJavascript());
            stringBuffer_local.append(getGeneradorComponentesHtml().ocultarBarraEstado());
            if (mc.sonCadenasIgualesIgnorarMayusculas(cp.obtenerValorPropiedadSisnet("BLOQUEAR_SELECCION_CONTENIDO"), "Si"))
            {
                stringBuffer_local.append(getGeneradorComponentesHtml().inhabilitarSeleccion());
            }
            stringBuffer_local.append(obtenerSeccionJavascript(pListaCampo, pNombreFormulario, pEsSoloConsulta));
            stringBuffer_local.append(getGeneradorComponentesHtml().cerrarBloqueJavascript());
            stringBuffer_local.append(getGeneradorComponentesHtml().cerrarHead());
            encabezadoPagina_local = stringBuffer_local.toString();
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }
        finally
        {
            stringBuffer_local = null;
        }

        return encabezadoPagina_local;
    }
    private String obtenerEncabezadoPaginaInicio()
    {
        String encabezadoPagina_local = "";

        try
        {
            encabezadoPagina_local = getGeneradorComponentesHtml().abrirHead();
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, "<META HTTP-EQUIV=\"Refresh\" CONTENT=\"1;URL=administrador/sisnet.jsp\">");
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getTituloPagina());
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getHojaEstiloSisnetInicial());

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().cerrarHead());
        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return encabezadoPagina_local;
    }
    private String obtenerContenidoPaginaInicio()
    {
        String contenidoPagina_local = "";

        try
        {
            contenidoPagina_local = getGeneradorComponentesHtml().insertarImagenCentrada("", "imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", false, false);

        }
        catch (Exception excepcion)
        {
            excepcion.printStackTrace();
        }

        return contenidoPagina_local;
    }
    public Pagina obtenerPaginaInicioAplicacion() throws IOException
    {
        Pagina pagina_local = null;
    
    try {
            pagina_local = new Pagina();
            pagina_local.setEncabezadoPagina(obtenerEncabezadoPaginaInicio());
            pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));
            pagina_local.setContenidoDatos(obtenerContenidoPaginaInicio());
            pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
        } catch (Exception excepcion) {
      excepcion.printStackTrace();
    }
return pagina_local;
  }
  private boolean verificarExistenciaBaseDatosSisnet()
{
    boolean existeSisnet_local = false;
    String rutaArchivo_local = null;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ConexionPostgres conexionPostgres_local = null;

    try
    {
    	/*coco*/
        rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(com.sisnet.constantes.ConstantesBaseDatos.const_rutaConfigPostgres);
        conexionPostgres_local = ap.obtenerConexionPostgres(rutaArchivo_local);
        administradorBaseDatos_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosPostgres(conexionPostgres_local));
        existeSisnet_local = administradorBaseDatos_local.verificarExistenciaBaseDatos(getManejadorRequest().obtenerNombreRecursoAplicacion());

        administradorBaseDatos_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        rutaArchivo_local = null;
        conexionPostgres_local = null;
        administradorBaseDatos_local = null;
    }

    return existeSisnet_local;
}
private void actualizarVersionUno(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version01 version01_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version01_local = new Version01(pObjetoConexionBaseDatos);
        version01_local.generarBaseDatosSisnet();
        version01_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version01_local = null;
    }
}
private void actualizarVersionDos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version02 version02_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version02_local = new Version02(pObjetoConexionBaseDatos);
        version02_local.actualizarBaseDatosSisnet(getManejadorRequest().obtenerNombreRecursoAplicacion());
        version02_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version02_local = null;
    }
}
private void actualizarVersionTres(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version03 version03_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version03_local = new Version03(pObjetoConexionBaseDatos);
        version03_local.aplicarParche001();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version03_local = null;
    }
}
private void actualizarVersionCuatro(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version04 version04_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version04_local = new Version04(pObjetoConexionBaseDatos);
        version04_local.aplicarParche002();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version04_local = null;
    }
}
private void actualizarVersionCinco(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version05 version05_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version05_local = new Version05(pObjetoConexionBaseDatos);
        version05_local.actualizarBaseDatosSisnetVersion05();
        version05_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version05_local = null;
    }
}
private void actualizarVersionSeis(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version06 version06_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version06_local = new Version06(pObjetoConexionBaseDatos);
        version06_local.actualizarBaseDatosSisnetVersion06();
        version06_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version06_local = null;
    }
}
private void actualizarVersionOcho(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version08 version08_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version08_local = new Version08(pObjetoConexionBaseDatos);
        version08_local.actualizarBaseDatosSisnetVersion08();
        version08_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version08_local = null;
    }
}
private void actualizarVersionNueve(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version09 version09_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version09_local = new Version09(pObjetoConexionBaseDatos);
        version09_local.actualizarBaseDatosSisnetVersion09();
        version09_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version09_local = null;
    }
}
private void actualizarVersionDiez(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version10 version10_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version10_local = new Version10(pObjetoConexionBaseDatos);
        version10_local.actualizarBaseDatosSisnetVersion10();
        version10_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version10_local = null;
    }
}
private void actualizarVersionOnce(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version11 version11_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version11_local = new Version11(pObjetoConexionBaseDatos);
        version11_local.actualizarBaseDatosSisnetVersion11();
        version11_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version11_local = null;
    }
}
private void actualizarVersionDoce(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version12 version12_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version12_local = new Version12(pObjetoConexionBaseDatos);
        version12_local.actualizarBaseDatosSisnetVersion12();
        version12_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version12_local = null;
    }
}
private void actualizarVersionCatorce(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version14 version14_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version14_local = new Version14(pObjetoConexionBaseDatos);
        version14_local.actualizarBaseDatosSisnetVersion14();
        version14_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version14_local = null;
    }
}
private void actualizarVersionDieciseis(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version16 version16_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version16_local = new Version16(pObjetoConexionBaseDatos);
        version16_local.actualizarBaseDatosSisnetVersion16();
        version16_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version16_local = null;
    }
}
private void actualizarVersionDiecisiete(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version17 version17_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version17_local = new Version17(pObjetoConexionBaseDatos);
        version17_local.actualizarBaseDatosSisnetVersion17();
        version17_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version17_local = null;
    }
}
private void actualizarVersionDieciocho(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version18 version18_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version18_local = new Version18(pObjetoConexionBaseDatos);
        version18_local.actualizarBaseDatosSisnetVersion18();
        version18_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version18_local = null;
    }
}
private void actualizarVersionDiecinueve(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version19 version19_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version19_local = new Version19(pObjetoConexionBaseDatos);
        version19_local.actualizarBaseDatosSisnetVersion19();
        version19_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version19_local = null;
    }
}
private void actualizarVersionVeintiuno(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version21 version21_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version21_local = new Version21(pObjetoConexionBaseDatos);
        version21_local.actualizarBaseDatosSisnetVersion21(getManejadorRequest().obtenerNombreRecursoAplicacionWeb());

        version21_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version21_local = null;
    }
}
private void actualizarVersionVeintidos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version22 version22_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version22_local = new Version22(pObjetoConexionBaseDatos);
        version22_local.actualizarBaseDatosSisnetVersion22();
        version22_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version22_local = null;
    }
}
private void actualizarVersionVeintitres(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version23 version23_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version23_local = new Version23(pObjetoConexionBaseDatos);
        version23_local.actualizarBaseDatosSisnetVersion23();
        version23_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version23_local = null;
    }
}
private void actualizarVersionVeintisiete(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version27 version27_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version27_local = new Version27(pObjetoConexionBaseDatos);
        version27_local.actualizarBaseDatosSisnetVersion27();
        version27_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version27_local = null;
    }
}
private void actualizarVersionVeintiocho(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version28 version28_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version28_local = new Version28(pObjetoConexionBaseDatos);
        version28_local.actualizarBaseDatosSisnetVersion28();
        version28_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version28_local = null;
    }
}
private void actualizarVersionVeintinueve(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version29 version29_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version29_local = new Version29(pObjetoConexionBaseDatos);
        version29_local.actualizarBaseDatosSisnetVersion29();
        version29_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version29_local = null;
    }
}
private void actualizarVersionTreinta(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version30 version30_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version30_local = new Version30(pObjetoConexionBaseDatos);
        version30_local.actualizarBaseDatosSisnetVersion30();
        version30_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version30_local = null;
    }
}
private void actualizarVersionTreintaYDos(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version32 version32_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version32_local = new Version32(pObjetoConexionBaseDatos);
        version32_local.actualizarBaseDatosSisnetVersion32();
        version32_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version32_local = null;
    }
}
private void actualizarVersionTreintaYTres(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version33 version33_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version33_local = new Version33(pObjetoConexionBaseDatos);
        version33_local.actualizarBaseDatosSisnetVersion33();
        version33_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version33_local = null;
    }
}
private void actualizarVersionTreintaYSeis(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version36 version36_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version36_local = new Version36(pObjetoConexionBaseDatos);
        version36_local.actualizarBaseDatosSisnetVersion36();
        version36_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version36_local = null;
    }
}
private void actualizarVersionTreintaYSiete(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version37 version37_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version37_local = new Version37(pObjetoConexionBaseDatos);
        version37_local.actualizarBaseDatosSisnetVersion37();
        version37_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version37_local = null;
    }
}
private void actualizarVersionTreintaYOcho(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version38 version38_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version38_local = new Version38(pObjetoConexionBaseDatos);
        version38_local.actualizarBaseDatosSisnetVersion38();
        version38_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version38_local = null;
    }
}
private void actualizarVersionTreintaYNueve(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version39 version39_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version39_local = new Version39(pObjetoConexionBaseDatos);
        version39_local.actualizarBaseDatosSisnetVersion39();
        version39_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version39_local = null;
    }
}
private void actualizarVersionCuarenta(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Version40 version40_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        version40_local = new Version40(pObjetoConexionBaseDatos);
        version40_local.actualizarBaseDatosSisnetVersion40();
        version40_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version40_local = null;
    }
}
private void aplicarParcheTres(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Parche003 parche003_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        parche003_local = new Parche003(pObjetoConexionBaseDatos);
        parche003_local.aplicarParche003();
        parche003_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        parche003_local = null;
    }
}
private void aplicarParcheCuatro(ObjetoConexionBaseDatos pObjetoConexionBaseDatos)
{
    Parche004 parche004_local = null;

    if (pObjetoConexionBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }

    try
    {
        parche004_local = new Parche004(pObjetoConexionBaseDatos);
        parche004_local.aplicarParche004();
        parche004_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        parche004_local = null;
    }
}
private void actualizarVersion()
{
    String rutaArchivo_local = null;
    int numeroVersion_local = -1;
    ObjetoConexionBaseDatos objetoConexionBaseDatos_local = null;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ConexionPostgres conexionPostgres_local = null;
    Version01 version01_local = null;

    boolean actualizarVersion_local = false;

    try
    {
    	/*coco*/
         rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(com.sisnet.constantes.ConstantesBaseDatos.const_rutaConfigPostgres);
         conexionPostgres_local = ap.obtenerConexionPostgres(rutaArchivo_local);

        if (!verificarExistenciaBaseDatosSisnet())
        {
            administradorBaseDatos_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosPostgres(conexionPostgres_local));
            administradorBaseDatos_local.crearBaseDatosSisnet(getManejadorRequest().obtenerNombreRecursoAplicacion());
            administradorBaseDatos_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
            objetoConexionBaseDatos_local = ap.obtenerConexionBaseDatosSisnet(getManejadorRequest().obtenerNombreRecursoAplicacion(), rutaArchivo_local);
            actualizarVersionUno(objetoConexionBaseDatos_local);
        }
        objetoConexionBaseDatos_local = ap.obtenerConexionBaseDatosSisnet(getManejadorRequest().obtenerNombreRecursoAplicacion(), rutaArchivo_local);
        version01_local = new Version01(objetoConexionBaseDatos_local);
        switch (numeroVersion_local)
        {

        }

        actualizarVersion_local = version01_local.verificarActualizarVersion();
        if (actualizarVersion_local)
        {
            numeroVersion_local = version01_local.obtenerNumeroVersionActual();
            version01_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
            if (numeroVersion_local <= 2)
            {
                actualizarVersionDos(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 3)
            {
                actualizarVersionTres(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 4)
            {
                actualizarVersionCuatro(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 5)
            {
                actualizarVersionCinco(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 6)
            {
                actualizarVersionSeis(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 8)
            {
                actualizarVersionOcho(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 9)
            {
                actualizarVersionNueve(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 10)
            {
                actualizarVersionDiez(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 11)
            {
                actualizarVersionOnce(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 12)
            {
                actualizarVersionDoce(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 14)
            {
                actualizarVersionCatorce(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 16)
            {
                actualizarVersionDieciseis(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 17)
            {
                actualizarVersionDiecisiete(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 18)
            {
                actualizarVersionDieciocho(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 19)
            {
                actualizarVersionDiecinueve(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 21)
            {
                actualizarVersionVeintiuno(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 22)
            {
                actualizarVersionVeintidos(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 23)
            {
                actualizarVersionVeintitres(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 27)
            {
                actualizarVersionVeintisiete(objetoConexionBaseDatos_local);
            }
            aplicarParcheTres(objetoConexionBaseDatos_local);
            if (numeroVersion_local <= 28)
            {
                actualizarVersionVeintiocho(objetoConexionBaseDatos_local);
            }
            aplicarParcheCuatro(objetoConexionBaseDatos_local);
            if (numeroVersion_local <= 29)
            {
                actualizarVersionVeintinueve(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 30)
            {
                actualizarVersionTreinta(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 32)
            {
                actualizarVersionTreintaYDos(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 33)
            {
                actualizarVersionTreintaYTres(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 36)
            {
                actualizarVersionTreintaYSeis(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 37)
            {
                actualizarVersionTreintaYSiete(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 38)
            {
                actualizarVersionTreintaYOcho(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 39)
            {
                actualizarVersionTreintaYNueve(objetoConexionBaseDatos_local);
            }
            if (numeroVersion_local <= 40)
            {
                actualizarVersionCuarenta(objetoConexionBaseDatos_local);
            }
        }
        if (version01_local.getConexionBaseDatos().getConexion() != ConstantesGeneral.VALOR_NULO)
        {
            version01_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
        }
        administradorBaseDatos_local = new AdministradorBaseDatos(objetoConexionBaseDatos_local);
        administradorBaseDatos_local.insertarRegistroUsuarioAdministrador();
        administradorBaseDatos_local.borrarCantidadContrasenasFallidas();
        administradorBaseDatos_local.asignarBloqueoPorVencimientoContrasena();
        administradorBaseDatos_local.cambiarContrasenaUsuarioAdministrador();
        administradorBaseDatos_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version01_local = null;
        conexionPostgres_local = null;
        administradorBaseDatos_local = null;
        objetoConexionBaseDatos_local = null;
    }
}
private boolean verificarRealizaTransaccion()
{
    boolean realizaTransaccion_local = false;
    boolean existeSisnet_local = false;//false debe devolverse
    boolean actualizarVersion_local = false;
    ObjetoConexionBaseDatos objetoConexionBaseDatos_local = null;
    Version01 version01_local = null;
    String rutaArchivo_local = null;

    try
    {
        existeSisnet_local = verificarExistenciaBaseDatosSisnet();
        if (existeSisnet_local)
        {
        	/*coco*/
            rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(com.sisnet.constantes.ConstantesBaseDatos.const_rutaConfigPostgres);
            /**/
            objetoConexionBaseDatos_local = ap.obtenerConexionBaseDatosSisnet(getManejadorRequest().obtenerNombreRecursoAplicacion() , rutaArchivo_local);
            version01_local = new Version01(objetoConexionBaseDatos_local);
            actualizarVersion_local = version01_local.verificarActualizarVersion();
            version01_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
        }
        realizaTransaccion_local = (!existeSisnet_local || actualizarVersion_local);
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        version01_local = null;
        objetoConexionBaseDatos_local = null;
    }

    return realizaTransaccion_local;
}
private String obtenerEncabezadoPaginaSisnet(boolean pExisteConexion)
{
    String encabezadoPagina_local = "";

    try
    {
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().abrirHead());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getMetaData());
        if (pExisteConexion)
        {
            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().obtenerMetaRedireccionamientoLogin(verificarRealizaTransaccion()));
        }
        else
        {

            encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().obtenerMetaRedireccionamientoConfiguracion());
        }

        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getTituloPagina());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getHojaEstiloSisnet(0));

        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "sisnet.js?v=1", 0));

        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().cerrarHead());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return encabezadoPagina_local;
}
private String obtenerContenidoPaginaSisnet()
{
    String contenidoPagina_local = "";

    try
    {
        contenidoPagina_local = mc.concatenarCadena(contenidoPagina_local, getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", true, false));

        contenidoPagina_local = mc.concatenarCadena(contenidoPagina_local, getGeneradorComponentesHtml().insertarMensaje(ConstantesMensajesAplicacion.getMensajeAplicacion(1)));

        contenidoPagina_local = mc.concatenarCadena(contenidoPagina_local, getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/progreso.gif", "", "", true, false));

    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return contenidoPagina_local;
}
private String obtenerDatosVersionPaginaSisnet()
{
    String datosVersionPagina_local = "";

    try
    {
        datosVersionPagina_local = mc.concatenarCadena(datosVersionPagina_local, getGeneradorComponentesHtml().insertarNumeroYFechaVersion(40, "center", "../imagenes/"));

        datosVersionPagina_local = mc.concatenarCadena(datosVersionPagina_local, getGeneradorComponentesHtml().insertarDerechosReservados(false));
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return datosVersionPagina_local;
}
private boolean verificarConexionBaseDatosPostgres()
{
    boolean conectado_local = false;
    String rutaArchivo_local = null;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ConexionPostgres conexionPostgres_local = null;

    try
    {
    	/*coco*/
    	rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(com.sisnet.constantes.ConstantesBaseDatos.const_rutaConfigPostgres);
    	conexionPostgres_local = ap.obtenerConexionPostgres(rutaArchivo_local);
    	administradorBaseDatos_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosPostgres(conexionPostgres_local));
        conectado_local = (administradorBaseDatos_local.getConexionBaseDatos().getConexion() != ConstantesGeneral.VALOR_NULO);
        if (conectado_local)
        {
            administradorBaseDatos_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
        }
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        rutaArchivo_local = null;
        conexionPostgres_local = null;
        administradorBaseDatos_local = null;
    }

    return conectado_local;
}
public Pagina obtenerPaginaSisnet()
{
    Pagina pagina_local = null;
    boolean hayConexion_local = false;

    try
    {
        pagina_local = new Pagina();
        hayConexion_local = verificarConexionBaseDatosPostgres();
        pagina_local.setEncabezadoPagina(obtenerEncabezadoPaginaSisnet(hayConexion_local));
        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));
        pagina_local.setContenidoDatos(obtenerContenidoPaginaSisnet());
        if (hayConexion_local)
        {
            actualizarVersion();
        }
        pagina_local.setVersion(obtenerDatosVersionPaginaSisnet());
        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    return pagina_local;
}
public Pagina obtenerPaginaLogin() throws IOException
{
    Pagina pagina_local = null;
    boolean existenAplicaciones_local = false;
    String botones_local = null;
    ListaCampo listaCampoLogin_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaBotones listaBotones_local = null;
    Formulario formularioLogin_local = null;
    
    try {
        existenAplicaciones_local = (getAdministradorBaseDatosSisnet().obtenerIdPrimeraAplicacion(1, -1) != -1);

        if (getManejadorRequest().obtenerValorAtributoRequest("accion", getManejadorSesion()) != ConstantesGeneral.VALOR_NULO)
        {

            if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO && Integer.parseInt(getManejadorRequest().obtenerValorAtributoRequest("accion", getManejadorSesion()).toString()) == 96)
            {

                getManejadorSesion().obtenerManejadorEventos().setNombreEvento("FINALSESIONAPLICATIVO");

                getManejadorSesion().obtenerManejadorEventos().setListaCampo(null);
                getManejadorSesion().obtenerManejadorEventos().setListaCampoValoresAnteriores(null);
                getManejadorSesion().obtenerManejadorEventos().setGrupoInformacion(getManejadorSesion().obtenerGrupoInformacionActual());

                getManejadorSesion().obtenerManejadorEventos().setRealizarAccionUsuario(true);

                getManejadorSesion().actualizarExistenEventosEnEjecucion(true);
                getManejadorSesion().obtenerManejadorEventos().ejecutarEvento();
                getManejadorSesion().actualizarExistenEventosEnEjecucion(!getManejadorSesion().obtenerManejadorEventos().haFinalizadoEjecucion());

                getManejadorSesion().cerrarSesion();
            }
        }
        else if (getManejadorSesion().obtenerAccion() == 96)
        {
            getManejadorSesion().cerrarSesion();
        }
        listaCampoLogin_local = ap.obtenerCamposLogin(existenAplicaciones_local);
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaBotones_local = new ListaBotones();
        getGeneradorComponentesHtml().setAnchoCampos("200");

        pagina_local = new Pagina();
        pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampoLogin_local, "formularioLogin", 0, false, false));

        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody("onLoad=\"formularioLogin.FLDNOMBREUSUARIO.focus();\""));
        pagina_local.setContenidoDatos(getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", true, false));

        insertarMensajePagina(pagina_local);

        if (!existenAplicaciones_local)
        {
            pagina_local.setMensaje(getGeneradorComponentesHtml().insertarMensajeAdvertencia("No existen aplicaciones registradas, digite el t\u00edtulo de la aplicaci\u00f3n inicial. Recuerde que debe ser un usuario administrador del sistema"));
        }

        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(99));

        formularioLogin_local = new Formulario();
        formularioLogin_local.setNombre("formularioLogin");
        formularioLogin_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
        formularioLogin_local.setEventoOnSubmit("return verificarCampos();");
        formularioLogin_local.setListaCampo(listaCampoLogin_local);

        listaBotones_local.adicionar("ingresar", true, "", "Ingresar", "", 0, false, true);

        listaBotones_local.adicionar("cancelar", false, " onClick=\"return cerrarVentana();\"", "Cancelar", "login.jsp", 0, false, true);

        formularioLogin_local.setContenido(insertarFormulario(formularioLogin_local, listaCampoLogin_local, listaBotones_local, false, 1, "", -1, "center"));

        pagina_local.setContenidoFormulario(formularioLogin_local.dibujar());

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(11));

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(9));

        listaBotones_local.borrarElementos();
        listaBotones_local.adicionar("tiposusuario", false, "", "Tipos de Usuario", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(11));

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(5));

        listaBotones_local.adicionar("registrousuario", false, "", "Registro Usuario", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(11));

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(12));

        listaBotones_local.adicionar("cambiarcontrasena", false, "", "Cambiar Contrase\u00f1a", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        botones_local = mc.concatenarCadena(botones_local, getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));

        botones_local = mc.concatenarCadena(botones_local, getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(11));

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(13));

        listaBotones_local.borrarElementos();
        listaBotones_local.adicionar("botonoculto", false, "", "", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        botones_local = mc.concatenarCadena(botones_local, getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));

        pagina_local.setBotonesSecundarios(botones_local);
        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
    } catch (Throwable excepcion) {
        excepcion.printStackTrace();
    } finally {

        botones_local = null;
        listaBotones_local = null;
        formularioLogin_local = null;
        listaCampoLogin_local = null;
        listaParametrosRedireccion_local = null;
    } 
    return pagina_local;
}
private String dibujarContenidoTablaAdministrador(int pIdTabla)
{
    String contenidoTablaAdministrador_local = "";

    try
    {
        contenidoTablaAdministrador_local = mc.concatenarCadena(contenidoTablaAdministrador_local, getGeneradorComponentesHtml().insertarTituloTipo2("Valores de Tabla"));

        contenidoTablaAdministrador_local = mc.concatenarCadena(contenidoTablaAdministrador_local, dibujarConsultaGeneralTabla(pIdTabla));
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return contenidoTablaAdministrador_local;
}
protected String dibujarConsultaGeneralGrupoInformacion(GrupoInformacion pGrupoInformacion, boolean pEsConfiguracion)
{
    String consultaGeneralGrupoInformacion_local = "";

    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
        return consultaGeneralGrupoInformacion_local;
    }

    try
    {
        consultaGeneralGrupoInformacion_local = mc.concatenarCadena(consultaGeneralGrupoInformacion_local, dibujarDatosGrupoInformacion(pGrupoInformacion, true, true, pEsConfiguracion));

    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return consultaGeneralGrupoInformacion_local;
}
protected Boton obtenerBotonAtras(String pDestinoBoton, int pNumeroDirectoriosAnteriores)
{
    Boton botonAtras_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    String destino_local = null;

    if (pDestinoBoton == ConstantesGeneral.VALOR_NULO)
    {
        return botonAtras_local;
    }

    try
    {
        destino_local = mc.concatenarCadena(mc.complementarDirectorio(pNumeroDirectoriosAnteriores), "administradorServlet");

        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaParametrosRedireccion_local.setRecursoDestino(destino_local);
        listaParametrosRedireccion_local.adicionar("accion", pDestinoBoton);
        botonAtras_local = new Boton("atras", false, " onClick=\"return (preguntaRegresarPantallaAnterior());\" ", "Atr\u00e1s", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        listaParametrosRedireccion_local = null;
        destino_local = null;
    }
    return botonAtras_local;
}
private void asignarCamposNoModificablesAplicacion(ListaCampo pListaCampo)
{
    boolean camposAsignados_local = false;
    Campo campo_local = null;
    Iterator iterador_local = null;

    if (pListaCampo == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }
    try
    {
        if (!mc.sonCadenasIgualesIgnorarMayusculas(getManejadorSesion().obtenerUsuarioActual().getNombreUsuario(), "ADMINISTRADOR"))
        {

            iterador_local = pListaCampo.iterator();
            while (iterador_local.hasNext() && !camposAsignados_local)
            {
                campo_local = (Campo)iterador_local.next();
                if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldtituloaplicacion"))
                {
                    campo_local.setModificable(false);
                }
                if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldidaplicacionconsulta"))
                {
                    campo_local.setModificable(false);
                }
                if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldidaplicacionreporte"))
                {
                    campo_local.setModificable(false);
                    camposAsignados_local = true;
                }
                if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldidaplicacionimpresionmasiva"))
                {

                    campo_local.setModificable(false);
                    camposAsignados_local = true;
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
        campo_local = null;
        iterador_local = null;
    }
}
private void asignarCamposOcultosAplicacion(ListaCampo pListaCampo)
{
    Campo campo_local = null;
    Iterator iterador_local = null;

    if (pListaCampo == ConstantesGeneral.VALOR_NULO)
    {
        return;
    }
    try
    {
        iterador_local = pListaCampo.iterator();
        while (iterador_local.hasNext())
        {
            campo_local = (Campo)iterador_local.next();
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldtituloaplicacion"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldidaplicacionconsulta"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldidaplicacionreporte"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldidaplicacionimpresionmasiva"))
            {

                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldactualizarinformacionenlazada"))
            {

                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldaplicacionesactualizar"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldadvertenciaejecucion"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldpermitirconsultageneral"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldtamanomaximoarchivos"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldesoculta"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldtipoeventosusuario"))
            {
                campo_local.setModificable(true);
                campo_local.setVisibleUsuarioPrincipal(true);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldeventosacciones"))
            {
                campo_local.setModificable(true);
                campo_local.setVisibleUsuarioPrincipal(true);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldhacerdoblecalculo"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
            }
            if (mc.sonCadenasIgualesIgnorarMayusculas(campo_local.getNombreCampo(), "fldnumeroregistrospagina"))
            {
                campo_local.setModificable(false);
                campo_local.setVisibleUsuarioPrincipal(false);
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
public Pagina obtenerPaginaIncluirRegistro(GrupoInformacion pGrupoInformacion, boolean pEsModificacion) throws IOException
{
    Pagina pagina_local = null;
    int valorLlavePrimaria_local = -1;
    int valorHabilitadoPor_local = 0;
    int valorListaDependiente_local = 0;
    boolean esListaDependiente_local = false;
    boolean esHabilitado_local = false;
    String nombreBotonAceptar_local = null;
    String descripcionBotonAceptar_local = null;
    String nombreBotonCancelar_local = null;
    String descripcionBotonCancelar_local = null;
    String consultaSeleccionRegistro = null;
    String destinoVinculoVerDependencias_local = null;
    String idCampo_local = null;
    String tipoDato_local = null;
    String accion_local = null;
    String accionCancelar_local = null;
    String eventoFormulario_local = null;
    String eventosBody_local = null;
    ListaBotones listaBotones_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaCampo listaCampo_local = null;
    ListaCampo listaCampoConsultaSQL_local = null;
    Formulario formulario_local = null;
    Campo campoDepende_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
        return pagina_local;
    }
    
    try {
        pagina_local = new Pagina();
        valorLlavePrimaria_local = getManejadorSesion().obtenerValorLlavePrimaria();
        nombreBotonAceptar_local = "aceptarinclusion";
        descripcionBotonAceptar_local = "Aceptar Inclusi\u00f3n";
        nombreBotonCancelar_local = "cancelarinclusion";
        descripcionBotonCancelar_local = "Cancelar Inclusi\u00f3n";
        getGeneradorComponentesHtml().setAnchoCampos("300");

        listaBotones_local = new ListaBotones();
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaCampo_local = ap.obtenerCamposTabla(getManejadorRequest().obtenerNombreRecursoAplicacion(), pGrupoInformacion.getIdGrupoInformacion());

        getManejadorSesion().asignarValoresAtributosSesionACampos(listaCampo_local);
        if (pEsModificacion && (pGrupoInformacion.getIdGrupoInformacion() == 1 || pGrupoInformacion.getIdGrupoInformacion() == 10 || pGrupoInformacion.getIdGrupoInformacion() == 11))
        {

            if (pGrupoInformacion.getIdGrupoInformacion() == 11)
            {
                asignarCamposOcultosAplicacion(listaCampo_local);
            }
            else
            {
                asignarCamposNoModificablesAplicacion(listaCampo_local);
            }
        }
        if (!pEsModificacion && (pGrupoInformacion.getIdGrupoInformacion() == 1 || pGrupoInformacion.getIdGrupoInformacion() == 10))
        {

            listaCampo_local.asignarValorCampo("fldadvertenciaejecucion", "true");
            listaCampo_local.asignarValorCampo("fldesoculta", Boolean.valueOf(false));
            listaCampo_local.asignarValorCampo("fldnumeroregistrospagina", Integer.valueOf(20));
            if (pGrupoInformacion.getIdGrupoInformacion() == 10)
            {
                listaCampo_local.asignarValorCampo("fldpermitirconsultageneral", "true");
            }
            else
            {

                listaCampo_local.asignarValorCampo("fldpermitirconsultageneral", Boolean.valueOf(false));
            }
        }

        if (!pEsModificacion && pGrupoInformacion.getIdGrupoInformacion() == 2)
        {
            listaCampo_local.asignarValorCampo("fldposicion", Integer.valueOf(getAdministradorBaseDatosSisnet().obtenerUltimaPosicionGrupoInformacion(pGrupoInformacion.getAplicacion().getIdAplicacion()) + 1));
        }

        if (!pEsModificacion && pGrupoInformacion.getIdGrupoInformacion() == 4)
        {
            listaCampo_local.asignarValorCampo("fldvisibleusuarioprincipal", "true");
            listaCampo_local.asignarValorCampo("fldvisibleusuariosecundario", "true");

            listaCampo_local.asignarValorCampo("fldseudonimo", "");
            listaCampo_local.asignarValorCampo("fldincluiropcionconsulta", "true");
            listaCampo_local.asignarValorCampo("fldborrarvalornohabilitado", "true");
            listaCampo_local.asignarValorCampo("fldcambiarrenglon", "true");
        }
        if (pEsModificacion)
        {
            nombreBotonAceptar_local = "aceptarmodificacion";
            descripcionBotonAceptar_local = "Aceptar Modificaci\u00f3n";
            nombreBotonCancelar_local = "cancelarmodificacion";
            descripcionBotonCancelar_local = "Cancelar Modificaci\u00f3n";
            consultaSeleccionRegistro = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionRegistroGrupoInformacion(pGrupoInformacion, listaCampo_local, valorLlavePrimaria_local);

            listaCampoConsultaSQL_local = asignarValoresConsultaACampos(consultaSeleccionRegistro, true);

            listaCampo_local.copiarValoresListaCampoConsultaSQL(listaCampoConsultaSQL_local);
            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(91));

            if (pGrupoInformacion.getIdGrupoInformacion() == 5)
            {
                listaCampo_local.asignarObligatoriedadCampo("fldcontrasena", !pEsModificacion);
                listaCampo_local.asignarObligatoriedadCampo("fldconfirmarcontrasena", !pEsModificacion);
                listaCampo_local.asignarValorCampo("fldcontrasena", "");
            }

        }
        else if (pGrupoInformacion.getIdGrupoInformacion() == 5)
        {
            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(9));
        }
        else
        {

            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(90));
        }

        eventosBody_local = conformarEventosBody(listaCampo_local, "formularioIncluir");

        eventoFormulario_local = "return verificarCampos();";
        if (pGrupoInformacion.getIdGrupoInformacion() == 4)
        {
            eventoFormulario_local = "return (verificarCampos() && validarCombinacionCampo());";
        }
        formulario_local = new Formulario();
        formulario_local.setNombre("formularioIncluir");
        formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
        formulario_local.setEventoOnSubmit(eventoFormulario_local);
        formulario_local.setListaCampo(listaCampo_local);

        pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 0, false, false));

        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(eventosBody_local));

        accionCancelar_local = String.valueOf(95);
        if (pGrupoInformacion.getIdGrupoInformacion() == 1 || pGrupoInformacion.getIdGrupoInformacion() == 10)
        {
            listaCampo_local.asignarValorCampo("fldestabla", Boolean.valueOf((pGrupoInformacion.getIdGrupoInformacion() == 10)));
        }

        pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(), getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(accionCancelar_local, 0), 0));

        insertarMensajePagina(pagina_local);

        listaBotones_local.adicionar(nombreBotonAceptar_local, true, "", descripcionBotonAceptar_local, "", 0, false);

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", accionCancelar_local);
        listaBotones_local.adicionar(nombreBotonCancelar_local, false, "", descripcionBotonCancelar_local, listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        formulario_local.setContenido(insertarFormulario(formulario_local, listaCampo_local, listaBotones_local, pEsModificacion, 1, "", pGrupoInformacion.getAplicacion().getIdAplicacion(), "center"));

        pagina_local.setContenidoFormulario(formulario_local.dibujar());

        if (pEsModificacion && pGrupoInformacion.getIdGrupoInformacion() == 3)
        {
            pagina_local.setContenidoDatos(dibujarContenidoTablaAdministrador(valorLlavePrimaria_local));
        }
        if (pEsModificacion && pGrupoInformacion.getIdGrupoInformacion() == 2)
        {
            pagina_local.setContenidoDatos(dibujarConsultaGeneralCamposGrupoInformacion(valorLlavePrimaria_local));
        }
        if (pEsModificacion && pGrupoInformacion.getIdGrupoInformacion() == 4)
        {
            valorHabilitadoPor_local = Integer.parseInt(listaCampo_local.obtenerValorCampo("fldhabilitadopor"));
            valorListaDependiente_local = Integer.parseInt(listaCampo_local.obtenerValorCampo("fldlistadependiente"));

            esHabilitado_local = (valorHabilitadoPor_local != 0);
            esListaDependiente_local = (valorListaDependiente_local != 0);
            if (esHabilitado_local)
            {
                campoDepende_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(valorHabilitadoPor_local);
            }
            if (esListaDependiente_local)
            {
                campoDepende_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorId(valorListaDependiente_local);
            }
            destinoVinculoVerDependencias_local = " javascript:restriccionVerDependencias(); ";
            if ((esListaDependiente_local || esHabilitado_local) && campoDepende_local != ConstantesGeneral.VALOR_NULO)
            {
                idCampo_local = listaCampo_local.obtenerValorCampo("fldidcampo");
                tipoDato_local = listaCampo_local.obtenerValorCampo("fldtipodato");
                listaParametrosRedireccion_local.borrarElementos();
                if (esHabilitado_local)
                {
                    accion_local = String.valueOf(29);
                }
                if (esListaDependiente_local)
                {
                    accion_local = String.valueOf(17);
                    listaParametrosRedireccion_local.adicionar("tabla", tipoDato_local);
                }
                listaParametrosRedireccion_local.adicionar("accion", accion_local);
                listaParametrosRedireccion_local.adicionar("valorllaveprimaria", idCampo_local);
                listaParametrosRedireccion_local.adicionar("tablaDepende", campoDepende_local.getFormatoCampo().getTipoDato());

                listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

                destinoVinculoVerDependencias_local = listaParametrosRedireccion_local.concatenarParametros();
            }
            listaBotones_local.borrarElementos();
            listaBotones_local.adicionar("verdependencias", false, "", "Ver Dependencias", destinoVinculoVerDependencias_local, 0, false);

            pagina_local.setBotonesPrincipales(getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));
        }

        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
    } catch (Throwable excepcion) {
        excepcion.printStackTrace();
    } finally {

        accion_local = null;
        idCampo_local = null;
        tipoDato_local = null;
        listaCampo_local = null;
        formulario_local = null;
        eventosBody_local = null;
        listaBotones_local = null;
        campoDepende_local = null;
        accionCancelar_local = null;
        eventoFormulario_local = null;
        nombreBotonAceptar_local = null;
        nombreBotonCancelar_local = null;
        consultaSeleccionRegistro = null;
        listaCampoConsultaSQL_local = null;
        descripcionBotonAceptar_local = null;
        descripcionBotonCancelar_local = null;
        listaParametrosRedireccion_local = null;
        destinoVinculoVerDependencias_local = null;
    } 
    
    return pagina_local;
}
protected int calcularAnchoCampo(Campo pCampo)
{
    int anchoCampo_local = 0;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
        return anchoCampo_local;
    }
    try
    {
        if (pCampo.esTipoDatoDocumento() || (pCampo.esTipoDatoArchivo() && !pCampo.esImagen()))
        {
            anchoCampo_local = 57;
        }
        else
        {
            anchoCampo_local = pCampo.getAnchoColumna();
        }
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    return anchoCampo_local;
}
protected int calcularAnchoGrupoInformacion(GrupoInformacion pGrupoInformacion, boolean pVerificarVisibilidadPrincipal)
{
    int anchoGrupoInformacion_local = 0;
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;

    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
        return anchoGrupoInformacion_local;
    }

    try
    {
        if (pVerificarVisibilidadPrincipal)
        {
            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposVisiblesGrupoInformacionPorAncho(pGrupoInformacion.getIdGrupoInformacion());
        }
        else
        {

            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposVisiblesGrupoInformacionMultiplePorAncho(pGrupoInformacion.getIdGrupoInformacion());
        }

        if (listaCampo_local != ConstantesGeneral.VALOR_NULO)
        {
            iterator_local = listaCampo_local.iterator();
            while (iterator_local.hasNext())
            {
                campo_local = (Campo)iterator_local.next();

                if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local))
                {
                    if (pVerificarVisibilidadPrincipal)
                    {
                        if (campo_local.esVisibleUsuarioPrincipal())
                        {
                            if (campo_local.esTipoDatoArchivo())
                            {
                                anchoGrupoInformacion_local += 57;
                            }
                            else
                            {
                                anchoGrupoInformacion_local += campo_local.getAnchoColumna();
                            }

                        }
                    }
                    else if (campo_local.esVisibleUsuarioSecundario())
                    {
                        anchoGrupoInformacion_local += campo_local.getAnchoColumna();
                    }

                    if (pVerificarVisibilidadPrincipal && pGrupoInformacion.esGrupoInformacionMultiple())
                    {
                        anchoGrupoInformacion_local += 18;
                    }
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
        listaCampo_local = null;
        iterator_local = null;
        campo_local = null;
    }

    return anchoGrupoInformacion_local;
}
protected int calcularAnchoGrupoInformacionMultipleAplicacion(GrupoInformacion pGrupoInformacion, boolean pIncluirBorrar, boolean pExisteDecrementoIncrementoPosicion, boolean pEsConfiguracion)
{
    int anchoGrupoInformacionMultipleAplicacion_local = 0;

    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
        return anchoGrupoInformacionMultipleAplicacion_local;
    }

    try
    {
        if (pIncluirBorrar)
        {
            anchoGrupoInformacionMultipleAplicacion_local += 30;
        }
        if (pExisteDecrementoIncrementoPosicion)
        {
            anchoGrupoInformacionMultipleAplicacion_local += 60;
        }

        if (pEsConfiguracion)
        {
            anchoGrupoInformacionMultipleAplicacion_local += ap.obtenerAnchoCamposConfiguracion(getManejadorRequest().obtenerNombreRecursoAplicacion(), pGrupoInformacion.getIdGrupoInformacion());
        }
        else
        {

            anchoGrupoInformacionMultipleAplicacion_local += calcularAnchoGrupoInformacion(pGrupoInformacion, false);

            anchoGrupoInformacionMultipleAplicacion_local += getAdministradorBaseDatosSisnet().obtenerAnchoCamposDocumento(pGrupoInformacion.getIdGrupoInformacion());

            anchoGrupoInformacionMultipleAplicacion_local += getAdministradorBaseDatosSisnet().obtenerAnchoCamposArchivo(pGrupoInformacion.getIdGrupoInformacion());
        }

    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return anchoGrupoInformacionMultipleAplicacion_local;
}
private String dibujarDatosGrupoInformacion(GrupoInformacion pGrupoInformacion, boolean pIncluirBorrar, boolean pExisteDecrementarIncrementarPosicion, boolean pEsConfiguracion)
{
    String datosGrupoInformacion_local = "";
    String nombreLlavePrimaria_local = null;
    String valorLlavePrimaria_local = null;
    String consultaSQL_local = null;
    String contenido_local = null;
    int anchoTabla_local = -1;
    boolean esGrupoInformacion_local = false;
    boolean alternar_local = false;
    boolean esPrimerGrupoInformacion_local = false;
    boolean esUltimoGrupoInformacion_local = false;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaParametrosRedireccion listaParametrosRedireccionBorrar = null;
    ListaParametrosRedireccion listaParametrosRedireccionModificar = null;
    ListaParametrosRedireccion listaParametrosRedireccionDecrementarPosicionGrupoInformacion = null;
    ListaParametrosRedireccion listaParametrosRedireccionIncrementarPosicionGrupoInformacion = null;
    Boton boton_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Iterator iteratorCamposGrupoInformacion_local = null;
    ResultSet resultSet_local = null;
    Campo campo_local = null;

    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
        return datosGrupoInformacion_local;
    }

    try
    {
        listaCampo_local = new ListaCampo();
        anchoTabla_local = calcularAnchoGrupoInformacionMultipleAplicacion(pGrupoInformacion, pIncluirBorrar, pExisteDecrementarIncrementarPosicion, pEsConfiguracion);

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().abrirFilaTabla());
        esGrupoInformacion_local = (pGrupoInformacion.getIdGrupoInformacion() == 2);
        if (pIncluirBorrar)
        {
            listaParametrosRedireccion_local = new ListaParametrosRedireccion();
            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(86));

            listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(pEsConfiguracion));

            boton_local = new Boton("incluir", false, "", "Incluir Registro", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

            datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(getGeneradorComponentesHtml().insertarBoton(boton_local, 0), 30));

            if (esGrupoInformacion_local)
            {
                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("", 30));

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("", 30));
            }
        }

        if (pEsConfiguracion)
        {
            listaCampo_local = ap.obtenerCamposTabla(getManejadorRequest().obtenerNombreRecursoAplicacion(), pGrupoInformacion.getIdGrupoInformacion());
        }
        else
        {

            listaCampo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), true);
        }

        consultaSQL_local = getAdministradorBaseDatosSisnet().conformarConsultaSQLSeleccionGrupoInformacion(pGrupoInformacion, listaCampo_local, pEsConfiguracion);

        if (pEsConfiguracion)
        {
            resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaSisnet(consultaSQL_local);
            nombreLlavePrimaria_local = ap.obtenerNombreLlavePrimariaTablaAdministrador(pGrupoInformacion.getIdGrupoInformacion());
        }
        else
        {
            resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQL_local);
            nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet().obtenerNombreLlavePrimariaGrupoInformacion(pGrupoInformacion, pEsConfiguracion);
        }

        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext())
        {
            campo_local = (Campo)iterador_local.next();
            if (campo_local.esVisibleUsuarioPrincipal())
            {
                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(campo_local.getEtiquetaCampo(), calcularAnchoCampo(campo_local)));
            }
        }

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().cerrarFilaTabla());

        if (resultSet_local != ConstantesGeneral.VALOR_NULO)
        {
            alternar_local = false;
            while (resultSet_local.next())
            {
                valorLlavePrimaria_local = resultSet_local.getObject(nombreLlavePrimaria_local).toString();
                listaParametrosRedireccionBorrar = new ListaParametrosRedireccion();
                listaParametrosRedireccionBorrar.adicionar("accion", String.valueOf(93));

                listaParametrosRedireccionDecrementarPosicionGrupoInformacion = new ListaParametrosRedireccion();
                listaParametrosRedireccionDecrementarPosicionGrupoInformacion.adicionar("accion", String.valueOf(25));

                listaParametrosRedireccionIncrementarPosicionGrupoInformacion = new ListaParametrosRedireccion();
                listaParametrosRedireccionIncrementarPosicionGrupoInformacion.adicionar("accion", String.valueOf(26));

                listaParametrosRedireccionModificar = new ListaParametrosRedireccion();
                listaParametrosRedireccionModificar.adicionar("accion", String.valueOf(88));

                listaParametrosRedireccionModificar.adicionar("configuracion", String.valueOf(pEsConfiguracion));

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().abrirFilaTabla());

                if (pIncluirBorrar)
                {
                    listaParametrosRedireccionBorrar.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                    listaParametrosRedireccionBorrar.adicionar("configuracion", String.valueOf(pEsConfiguracion));

                    datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaBorrarRegistro(listaParametrosRedireccionBorrar.concatenarParametros(), 0, alternar_local, true));

                    if (esGrupoInformacion_local)
                    {
                        listaParametrosRedireccionDecrementarPosicionGrupoInformacion.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                        esPrimerGrupoInformacion_local = (getAdministradorBaseDatosSisnet().obtenerPosicionGrupoInformacion(Integer.parseInt(valorLlavePrimaria_local)) == 1);

                        if (esPrimerGrupoInformacion_local)
                        {
                            datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaDecrementarPosicion("", false, alternar_local));
                        }
                        else
                        {

                            datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaDecrementarPosicion(listaParametrosRedireccionDecrementarPosicionGrupoInformacion.concatenarParametros(), true, alternar_local));
                        }

                        listaParametrosRedireccionIncrementarPosicionGrupoInformacion.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                        esUltimoGrupoInformacion_local = (getAdministradorBaseDatosSisnet().obtenerPosicionGrupoInformacion(Integer.parseInt(valorLlavePrimaria_local)) == getAdministradorBaseDatosSisnet().obtenerUltimaPosicionGrupoInformacion(pGrupoInformacion.getAplicacion().getIdAplicacion()));

                        if (esUltimoGrupoInformacion_local)
                        {
                            datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaIncrementarPosicion("", false, alternar_local));
                        }
                        else
                        {

                            datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaIncrementarPosicion(listaParametrosRedireccionIncrementarPosicionGrupoInformacion.concatenarParametros(), true, alternar_local));
                        }
                    }
                }

                iteratorCamposGrupoInformacion_local = listaCampo_local.iterator();
                while (iteratorCamposGrupoInformacion_local.hasNext())
                {
                    campo_local = (Campo)iteratorCamposGrupoInformacion_local.next();
                    if (!mc.esCadenaVacia(campo_local.getEtiquetaCampo()) && campo_local.esVisibleUsuarioPrincipal())
                    {
                        contenido_local = resultSet_local.getObject(campo_local.getNombreCampo()).toString();
                        listaParametrosRedireccionModificar.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaHipervinculo(contenido_local, "left", listaParametrosRedireccionModificar.concatenarParametros(), calcularAnchoCampo(campo_local), alternar_local, "", campo_local.esTipoDatoParrafo()));
                    }
                }

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().cerrarFilaTabla());

                alternar_local = !alternar_local;
            }
        }
        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().cerrarTabla());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {

        boton_local = null;
        campo_local = null;
        iterador_local = null;
        contenido_local = null;
        resultSet_local = null;
        listaCampo_local = null;
        consultaSQL_local = null;
        valorLlavePrimaria_local = null;
        nombreLlavePrimaria_local = null;
        listaParametrosRedireccion_local = null;
        listaParametrosRedireccionBorrar = null;
        listaParametrosRedireccionModificar = null;
        iteratorCamposGrupoInformacion_local = null;
        listaParametrosRedireccionDecrementarPosicionGrupoInformacion = null;
        listaParametrosRedireccionIncrementarPosicionGrupoInformacion = null;
    }

    return datosGrupoInformacion_local;
}
private String dibujarDatosTabla(int pIdTabla)
{
    String datosTabla_local = "";
    String nombreTabla_local = null;
    String nombreLlavePrimaria_local = null;
    String consultaSQL_local = null;
    String valorLlavePrimaria_local = null;
    String contenido_local = null;
    int anchoTabla_local = -1;
    boolean alternar_local = false;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaParametrosRedireccion listaParametrosRedireccionBorrar = null;
    ListaParametrosRedireccion listaParametrosRedireccionModificar = null;
    Boton boton_local = null;
    ResultSet resultSet_local = null;

    try
    {
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(71));

        listaParametrosRedireccion_local.adicionar("tabla", String.valueOf(pIdTabla));

        listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

        boton_local = new Boton("incluir", false, "", "Incluir Registro", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        anchoTabla_local = 330;

        datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

        datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().abrirFilaTabla());
        datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(getGeneradorComponentesHtml().insertarBoton(boton_local, 0), 30));

        datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("Valor", 300));

        datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().cerrarFilaTabla());
        nombreTabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla).getNombreTabla();
        nombreLlavePrimaria_local = "fldid" + nombreTabla_local;
        consultaSQL_local = ca.conformarConsultaSQLSeleccionTabla(nombreTabla_local);

        resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQL_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO)
        {
            alternar_local = false;
            while (resultSet_local.next())
            {
                valorLlavePrimaria_local = resultSet_local.getObject(nombreLlavePrimaria_local).toString();
                listaParametrosRedireccionBorrar = new ListaParametrosRedireccion();
                listaParametrosRedireccionBorrar.adicionar("accion", String.valueOf(75));

                listaParametrosRedireccionBorrar.adicionar("tabla", String.valueOf(pIdTabla));
                listaParametrosRedireccionBorrar.adicionar("configuracion", String.valueOf(true));

                listaParametrosRedireccionBorrar.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                listaParametrosRedireccionModificar = new ListaParametrosRedireccion();
                listaParametrosRedireccionModificar.adicionar("accion", String.valueOf(72));

                listaParametrosRedireccionModificar.adicionar("tabla", String.valueOf(pIdTabla));

                datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().abrirFilaTabla());
                datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().insertarCeldaBorrarRegistro(listaParametrosRedireccionBorrar.concatenarParametros(), 0, alternar_local, true));

                contenido_local = resultSet_local.getObject(nombreTabla_local).toString();
                listaParametrosRedireccionModificar.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                listaParametrosRedireccionModificar.adicionar("configuracion", String.valueOf(true));

                datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().crearCeldaHipervinculo(contenido_local, "left", listaParametrosRedireccionModificar.concatenarParametros(), 300, alternar_local, "", false));

                datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().cerrarFilaTabla());
                alternar_local = !alternar_local;
            }
        }
        datosTabla_local = mc.concatenarCadena(datosTabla_local, getGeneradorComponentesHtml().cerrarTabla());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {

        boton_local = null;
        contenido_local = null;
        resultSet_local = null;
        nombreTabla_local = null;
        consultaSQL_local = null;
        valorLlavePrimaria_local = null;
        nombreLlavePrimaria_local = null;
        listaParametrosRedireccion_local = null;
        listaParametrosRedireccionBorrar = null;
        listaParametrosRedireccionModificar = null;
    }

    return datosTabla_local;
}
private String dibujarCamposGrupoInformacionAdministrador(int pIdGrupoInformacion)
{
    String datosGrupoInformacion_local = "";
    String valorLlavePrimaria_local = null;
    String contenido_local = null;
    String consultaSQL_local = null;
    String nombreLlavePrimaria_local = null;
    int anchoTabla_local = -1;
    boolean alternar_local = false;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaParametrosRedireccion listaParametrosRedireccionDecrementarCampos = null;
    ListaParametrosRedireccion listaParametrosRedireccionIncrementarCampos = null;
    ListaParametrosRedireccion listaParametrosRedireccionModificar = null;
    Boton boton_local = null;
    ResultSet resultSet_local = null;
    AdministradorBaseDatos administradorBaseDatos_local = null;

    try
    {
        anchoTabla_local = 390;

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().abrirFilaTabla());
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(67));

        listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

        boton_local = new Boton("incluir", false, "", "Incluir Registro", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(getGeneradorComponentesHtml().insertarBoton(boton_local, 0), 30));

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("", 30));

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("", 30));

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("Campos", 300));

        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().cerrarFilaTabla());

        consultaSQL_local = ca.consultaSQLCamposGrupoSinLlavePrimaria(pIdGrupoInformacion);
        nombreLlavePrimaria_local = ap.obtenerNombreLlavePrimariaTablaAdministrador(4);

        resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaSisnet(consultaSQL_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO)
        {
            alternar_local = false;
            while (resultSet_local.next())
            {
                valorLlavePrimaria_local = resultSet_local.getObject(nombreLlavePrimaria_local).toString();
                ListaParametrosRedireccion listaParametrosRedireccionBorrar = new ListaParametrosRedireccion();
                listaParametrosRedireccionBorrar.adicionar("accion", String.valueOf(70));

                listaParametrosRedireccionBorrar.adicionar("configuracion", String.valueOf(true));

                listaParametrosRedireccionDecrementarCampos = new ListaParametrosRedireccion();
                listaParametrosRedireccionDecrementarCampos.adicionar("accion", String.valueOf(27));

                listaParametrosRedireccionIncrementarCampos = new ListaParametrosRedireccion();
                listaParametrosRedireccionIncrementarCampos.adicionar("accion", String.valueOf(28));

                listaParametrosRedireccionModificar = new ListaParametrosRedireccion();
                listaParametrosRedireccionModificar.adicionar("accion", String.valueOf(68));

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().abrirFilaTabla());

                listaParametrosRedireccionBorrar.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaBorrarRegistro(listaParametrosRedireccionBorrar.concatenarParametros(), 0, alternar_local, true));

                administradorBaseDatos_local = getAdministradorBaseDatosSisnet();
                listaParametrosRedireccionDecrementarCampos.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                if (administradorBaseDatos_local.obtenerPosicionCampo(Integer.parseInt(valorLlavePrimaria_local)) == 1)
                {
                    datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaDecrementarPosicion("", false, alternar_local));
                }
                else
                {

                    datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaDecrementarPosicion(listaParametrosRedireccionDecrementarCampos.concatenarParametros(), true, alternar_local));
                }

                listaParametrosRedireccionIncrementarCampos.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                if (administradorBaseDatos_local.obtenerPosicionCampo(Integer.parseInt(valorLlavePrimaria_local)) == administradorBaseDatos_local.obtenerUltimaPosicionCampo(pIdGrupoInformacion))
                {

                    datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaIncrementarPosicion("", false, alternar_local));
                }
                else
                {

                    datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().insertarCeldaIncrementarPosicion(listaParametrosRedireccionIncrementarCampos.concatenarParametros(), true, alternar_local));
                }

                contenido_local = resultSet_local.getObject("fldetiquetacampo").toString();
                listaParametrosRedireccionModificar.adicionar("valorllaveprimaria", valorLlavePrimaria_local);

                listaParametrosRedireccionModificar.adicionar("configuracion", String.valueOf(true));

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().crearCeldaHipervinculo(contenido_local, "left", listaParametrosRedireccionModificar.concatenarParametros(), 300, alternar_local, "", false));

                datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().cerrarFilaTabla());

                alternar_local = !alternar_local;
            }
        }
        datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local, getGeneradorComponentesHtml().cerrarTabla());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {

        boton_local = null;
        resultSet_local = null;
        contenido_local = null;
        consultaSQL_local = null;
        valorLlavePrimaria_local = null;
        nombreLlavePrimaria_local = null;
        administradorBaseDatos_local = null;
        listaParametrosRedireccion_local = null;
        listaParametrosRedireccionModificar = null;
        listaParametrosRedireccionDecrementarCampos = null;
        listaParametrosRedireccionIncrementarCampos = null;
    }

    return datosGrupoInformacion_local;
}
private String dibujarConsultaGeneralTabla(int pIdTabla)
{
    String consultaGeneralTabla_local = "";

    try
    {
        consultaGeneralTabla_local = mc.concatenarCadena(consultaGeneralTabla_local, dibujarDatosTabla(pIdTabla));
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return consultaGeneralTabla_local;
}
private String dibujarConsultaGeneralCamposGrupoInformacion(int pIdGrupoInformacion)
{
    String consultaGeneralGrupoInformacion_local = "";

    try
    {
        consultaGeneralGrupoInformacion_local = mc.concatenarCadena(consultaGeneralGrupoInformacion_local, dibujarCamposGrupoInformacionAdministrador(pIdGrupoInformacion));
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return consultaGeneralGrupoInformacion_local;
}
private String dibujarDatosTablaDepende(int pIdTabla, int pIdCampo, int pIdValorMaestro)
{
    String datosTablaDepende_local = "";
    String consultaSQL_local = null;
    String nombreTabla_local = null;
    String contenido_local = null;
    int anchoTabla_local = -1;
    int valorLlavePrimaria_local = -1;
    boolean alternar_local = false;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaParametrosRedireccion listaParametrosRedireccionBorrar = null;
    ListaParametrosRedireccion listaParametrosRedireccionModificar = null;
    Boton boton_local = null;
    ResultSet resultSet_local = null;

    try
    {
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(18));

        listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pIdValorMaestro));

        listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

        boton_local = new Boton("incluir", false, "", "Incluir Registro", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        anchoTabla_local = 330;

        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirFilaTabla());
        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(getGeneradorComponentesHtml().insertarBoton(boton_local, 0), 30));

        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("Valor", 300));

        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarFilaTabla());
        consultaSQL_local = ca.conformarConsultaSQLSeleccionTablaDepende(pIdCampo, pIdValorMaestro);
        nombreTabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla).getNombreTabla();

        resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaSisnet(consultaSQL_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO)
        {
            alternar_local = false;
            while (resultSet_local.next())
            {
                valorLlavePrimaria_local = resultSet_local.getInt("fldidvalordependiente");
                listaParametrosRedireccionBorrar = new ListaParametrosRedireccion();
                listaParametrosRedireccionBorrar.adicionar("accion", String.valueOf(22));

                listaParametrosRedireccionBorrar.adicionar("valorllaveprimaria", String.valueOf(valorLlavePrimaria_local));

                listaParametrosRedireccionBorrar.adicionar("configuracion", String.valueOf(true));

                listaParametrosRedireccionModificar = new ListaParametrosRedireccion();
                listaParametrosRedireccionModificar.adicionar("accion", String.valueOf(19));

                listaParametrosRedireccionModificar.adicionar("valorllaveprimaria", String.valueOf(valorLlavePrimaria_local));

                listaParametrosRedireccionModificar.adicionar("configuracion", String.valueOf(true));

                datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirFilaTabla());

                datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().insertarCeldaBorrarRegistro(listaParametrosRedireccionBorrar.concatenarParametros(), 0, alternar_local, true));

                contenido_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(nombreTabla_local, resultSet_local.getInt("fldidvalordetalle"));

                datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaHipervinculo(contenido_local, "left", listaParametrosRedireccionModificar.concatenarParametros(), 300, alternar_local, "", false));

                datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarFilaTabla());
                alternar_local = !alternar_local;
            }
        }
        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarTabla());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {
        boton_local = null;
        resultSet_local = null;
        contenido_local = null;
        consultaSQL_local = null;
        nombreTabla_local = null;
        listaParametrosRedireccion_local = null;
        listaParametrosRedireccionBorrar = null;
        listaParametrosRedireccionModificar = null;
    }

    return datosTablaDepende_local;
}
protected String dibujarConsultaGeneralTablaDepende(int pIdTabla, int pIdTablaDepende, int pIdCampo)
{
    String consultaGeneralTablaDepende_local = "";
    String nombreTabla_local = null;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;

    try
    {
        nombreTabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTablaDepende).getNombreTabla();
        consultaSQL_local = ca.conformarConsultaSQLSeleccionTabla(nombreTabla_local);
        resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQL_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO)
        {
            while (resultSet_local.next())
            {
                consultaGeneralTablaDepende_local = mc.concatenarCadena(consultaGeneralTablaDepende_local, getGeneradorComponentesHtml().insertarTituloTipo2(resultSet_local.getObject(nombreTabla_local).toString()));

                consultaGeneralTablaDepende_local = mc.concatenarCadena(consultaGeneralTablaDepende_local, dibujarDatosTablaDepende(pIdTabla, pIdCampo, resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(nombreTabla_local))));
            }

        }
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {

        resultSet_local = null;
        nombreTabla_local = null;
        consultaSQL_local = null;
    }

    return consultaGeneralTablaDepende_local;
}
private String dibujarDatosDependienteHabilitacion(int pIdCampo, int pIdValorMaestro)
{
    String datosTablaDepende_local = "";
    String consultaSQL_local = null;
    String contenido_local = null;
    int anchoTabla_local = -1;
    int valorLlavePrimaria_local = -1;
    ResultSet resultSet_local = null;
    ListaParametrosRedireccion listaParametrosRedireccionBorrar = null;
    ListaParametrosRedireccion listaParametrosRedireccionModificar = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    Boton boton_local = null;

    try
    {
        consultaSQL_local = ca.consultaSQLSeleccionDependienteHabilitacion(pIdCampo, pIdValorMaestro);
        resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaSisnet(consultaSQL_local);
        anchoTabla_local = 330;
        if (resultSet_local != ConstantesGeneral.VALOR_NULO && resultSet_local.next())
        {
            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirFilaTabla());
            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("", 30));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("Valor", 300));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarFilaTabla());

            valorLlavePrimaria_local = resultSet_local.getInt("fldiddependientehabilitacion");
            listaParametrosRedireccionBorrar = new ListaParametrosRedireccion();
            listaParametrosRedireccionBorrar.adicionar("accion", String.valueOf(34));

            listaParametrosRedireccionBorrar.adicionar("valorllaveprimaria", String.valueOf(valorLlavePrimaria_local));

            listaParametrosRedireccionBorrar.adicionar("configuracion", String.valueOf(true));

            listaParametrosRedireccionModificar = new ListaParametrosRedireccion();
            listaParametrosRedireccionModificar.adicionar("accion", String.valueOf(31));

            listaParametrosRedireccionModificar.adicionar("valorllaveprimaria", String.valueOf(valorLlavePrimaria_local));

            listaParametrosRedireccionModificar.adicionar("configuracion", String.valueOf(true));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirFilaTabla());
            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().insertarCeldaBorrarRegistro(listaParametrosRedireccionBorrar.concatenarParametros(), 0, false, true));

            contenido_local = "No Habilitado";
            if (resultSet_local.getBoolean("fldhabilitacion"))
            {
                contenido_local = "Habilitado";
            }
            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCelda(getGeneradorComponentesHtml().insertarVinculoTexto(contenido_local, listaParametrosRedireccionModificar.concatenarParametros(), false, "", false), "left", ""));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarFilaTabla());
        }
        else
        {
            listaParametrosRedireccion_local = new ListaParametrosRedireccion();
            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(30));

            listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pIdValorMaestro));

            listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

            boton_local = new Boton("incluir", false, "", "Incluir Registro", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().abrirFilaTabla());
            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(getGeneradorComponentesHtml().insertarBoton(boton_local, 0), 30));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("Valor", 300));

            datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarFilaTabla());
        }
        datosTablaDepende_local = mc.concatenarCadena(datosTablaDepende_local, getGeneradorComponentesHtml().cerrarTabla());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {

        boton_local = null;
        contenido_local = null;
        resultSet_local = null;
        consultaSQL_local = null;
        listaParametrosRedireccion_local = null;
        listaParametrosRedireccionBorrar = null;
        listaParametrosRedireccionModificar = null;
    }

    return datosTablaDepende_local;
}
protected String dibujarConsultaGeneralDependienteHabilitacion(int pIdTablaDepende, int pIdCampo)
{
    String consultaGeneralTablaDepende_local = "";
    String nombreTabla_local = null;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;

    try
    {
        nombreTabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTablaDepende).getNombreTabla();
        consultaSQL_local = ca.conformarConsultaSQLSeleccionTabla(nombreTabla_local);
        resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQL_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO)
        {
            while (resultSet_local.next())
            {
                consultaGeneralTablaDepende_local = mc.concatenarCadena(consultaGeneralTablaDepende_local, getGeneradorComponentesHtml().insertarTituloTipo2(resultSet_local.getObject(nombreTabla_local).toString()));

                consultaGeneralTablaDepende_local = mc.concatenarCadena(consultaGeneralTablaDepende_local, dibujarDatosDependienteHabilitacion(pIdCampo, resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(nombreTabla_local))));
            }

        }
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }
    finally
    {

        resultSet_local = null;
        nombreTabla_local = null;
        consultaSQL_local = null;
    }
    return consultaGeneralTablaDepende_local;
}
public Pagina obtenerPaginaLoginAcceso() throws IOException
{
    Pagina pagina_local = null;
    int idGrupoInformacion_local = -1;
    ListaCampo listaCampo_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaBotones listaBotones_local = null;
    Formulario formulario_local = null;
    
    try {
        pagina_local = new Pagina();
        listaCampo_local = ap.obtenerCamposLoginAcceso();
        getManejadorRequest().asignarValoresAtributosRequestACampos(listaCampo_local, getManejadorSesion());
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaBotones_local = new ListaBotones();
        getGeneradorComponentesHtml().setAnchoCampos("200");

        pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "formularioLogin", 0, false, false));

        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(conformarEventosBody(listaCampo_local, "formularioLogin")));

        pagina_local.setContenidoDatos(getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", true, false));

        insertarMensajePagina(pagina_local);

        idGrupoInformacion_local = getManejadorSesion().obtenerGrupoInformacionActual().getIdGrupoInformacion();
        if (idGrupoInformacion_local == 12)
        {
            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(12));
        }
        else
        {

            listaParametrosRedireccion_local.adicionar("accion", String.valueOf(10));

            if (idGrupoInformacion_local == 5)
            {
                listaParametrosRedireccion_local.adicionar("validarUsuarioLocal", String.valueOf(true));
            }
        }

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(idGrupoInformacion_local));

        formulario_local = new Formulario();
        formulario_local.setNombre("formularioLogin");
        formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
        formulario_local.setEventoOnSubmit("return verificarCampos();");
        formulario_local.setListaCampo(listaCampo_local);

        listaBotones_local.adicionar("ingresar", true, "", "Ingresar", "", 0, false);

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(96));

        listaParametrosRedireccion_local.setRecursoDestino("login.jsp");
        listaBotones_local.adicionar("cancelar", false, "", "Cancelar", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        formulario_local.setContenido(insertarFormulario(formulario_local, listaCampo_local, listaBotones_local, false, 1, "", -1, "center"));

        pagina_local.setContenidoFormulario(formulario_local.dibujar());

        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
        listaParametrosRedireccion_local.borrarElementos();
    } catch (Throwable excepcion) {
        excepcion.printStackTrace();
    } finally {

        formulario_local = null;
        listaCampo_local = null;
        listaBotones_local = null;
        listaParametrosRedireccion_local = null;
    } 
    
    return pagina_local;
}
public Pagina obtenerPaginaCambiarContrasena() throws IOException
{
    Pagina pagina_local = null;
    ListaCampo listaCampo_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaBotones listaBotones_local = null;
    Formulario formulario_local = null;
    
    try {
        pagina_local = new Pagina();
        listaCampo_local = ap.obtenerCamposCambioContrasena();
        getManejadorRequest().asignarValoresAtributosRequestACampos(listaCampo_local, getManejadorSesion());
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaBotones_local = new ListaBotones();
        getGeneradorComponentesHtml().setAnchoCampos("200");

        pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "formularioCambioContrasena", 0, false, false));

        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(conformarEventosBody(listaCampo_local, "formularioCambioContrasena")));

        pagina_local.setContenidoDatos(getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", true, false));

        insertarMensajePagina(pagina_local);
        pagina_local.setMensaje(mc.concatenarCadena(getGeneradorComponentesHtml().insertarMensaje(ConstantesMensajesAplicacion.const_DescripcionMensajeDigiteNuevaContrasena), pagina_local.getMensaje()));

        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(13));

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(getManejadorSesion().obtenerGrupoInformacionActual()));

        formulario_local = new Formulario();
        formulario_local.setNombre("formularioCambioContrasena");
        formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
        formulario_local.setEventoOnSubmit("return (verificarCampos() && verificarContrasena(document.formularioCambioContrasena.fldcontrasena.value ,document.formularioCambioContrasena.fldconfirmarcontrasena.value ));");

        formulario_local.setListaCampo(listaCampo_local);

        listaBotones_local.adicionar("aceptarmodificacion", true, "", "Aceptar Modificaci\u00f3n", "", 0, false);

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(96));

        listaParametrosRedireccion_local.setRecursoDestino("login.jsp");
        listaBotones_local.adicionar("cancelarmodificacion", false, "", "Cancelar Modificaci\u00f3n", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        formulario_local.setContenido(insertarFormulario(formulario_local, listaCampo_local, listaBotones_local, false, 1, "", -1, "center"));

        pagina_local.setContenidoFormulario(formulario_local.dibujar());

        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
        listaParametrosRedireccion_local.borrarElementos();
    } catch (Throwable excepcion) {
        excepcion.printStackTrace();
    } finally {

        formulario_local = null;
        listaCampo_local = null;
        listaBotones_local = null;
        listaParametrosRedireccion_local = null;
    } 
    
    return pagina_local;
}
public Pagina obtenerPaginaCambiarUsuarioAdministrador() throws IOException
{
    Pagina pagina_local = null;
    ListaCampo listaCampo_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaBotones listaBotones_local = null;
    Formulario formulario_local = null;
    
    try {
        pagina_local = new Pagina();
        listaCampo_local = ap.obtenerCamposCambiarUsuarioAdministrador();
        getManejadorRequest().asignarValoresAtributosRequestACampos(listaCampo_local, getManejadorSesion());
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        listaBotones_local = new ListaBotones();
        getGeneradorComponentesHtml().setAnchoCampos("200");

        pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "formularioCambioContrasena", 0, false, false));

        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(conformarEventosBody(listaCampo_local, "formularioCambioContrasena")));

        pagina_local.setContenidoDatos(getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", true, false));

        insertarMensajePagina(pagina_local);

        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(15));

        listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(getManejadorSesion().obtenerGrupoInformacionActual()));

        formulario_local = new Formulario();
        formulario_local.setNombre("formularioCambioContrasena");
        formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
        formulario_local.setEventoOnSubmit("return verificarCampos();");
        formulario_local.setListaCampo(listaCampo_local);

        listaBotones_local.adicionar("aceptarmodificacion", true, "", "Aceptar Modificaci\u00f3n", "", 0, false);

        listaParametrosRedireccion_local.borrarElementos();
        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(96));

        listaParametrosRedireccion_local.setRecursoDestino("login.jsp");
        listaBotones_local.adicionar("cancelarmodificacion", false, "", "Cancelar Modificaci\u00f3n", listaParametrosRedireccion_local.concatenarParametros(), 0, false);

        formulario_local.setContenido(insertarFormulario(formulario_local, listaCampo_local, listaBotones_local, false, 1, "", -1, "center"));

        pagina_local.setContenidoFormulario(formulario_local.dibujar());

        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
        listaParametrosRedireccion_local.borrarElementos();
    } catch (Throwable excepcion) {
        excepcion.printStackTrace();
    } finally {

        listaCampo_local = null;
        listaParametrosRedireccion_local = null;
        listaBotones_local = null;
        formulario_local = null;
    } 
    
    return pagina_local;
}
private String obtenerEncabezadoPaginaConfiguracion()
{
    String encabezadoPagina_local = "";

    try
    {
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().abrirHead());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getMetaData());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getTituloPagina());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().getHojaEstiloSisnet(0));

        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "jquery-3.6.0.min.js", 0));
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().incluirLibreriaJavascript("../utilidades/javascript/", "sisnet.js?v=1", 0));

        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().abrirBloqueJavascript());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, insertarJavascriptVerificarCampos("formularioIncluir", ap.obtenerCamposConfiguracion(), false));

        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().cerrarBloqueJavascript());
        encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local, getGeneradorComponentesHtml().cerrarHead());
    }
    catch (Exception excepcion)
    {
        excepcion.printStackTrace();
    }

    return encabezadoPagina_local;
}
public Pagina obtenerPaginaConfiguracion() throws IOException
{
    Pagina pagina_local = null;
    String rutaArchivo_local = null;
    ListaCampo listaCampos_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ListaBotones listaBotones_local = null;
    Formulario formulario_local = null;
    ConexionPostgres conexionPostgres_local = null;
    
    try {
        rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(mc.concatenarCadena("/administrador/conf/", "sisnet.conf"));

        conexionPostgres_local = ap.obtenerConexionPostgres(rutaArchivo_local);
        listaCampos_local = ap.obtenerCamposConfiguracion();
        listaCampos_local.asignarValorCampo("fldsuperusuario", conexionPostgres_local.getSuperUsuario());
        listaCampos_local.asignarValorCampo("fldcontrasenasuperusuario", conexionPostgres_local.getContrasenaSuperUsuario());

        listaCampos_local.asignarValorCampo("fldnumeropuertoconexion", Integer.valueOf(conexionPostgres_local.getNumeroPuertoConexion()));

        listaBotones_local = new ListaBotones();
        listaParametrosRedireccion_local = new ListaParametrosRedireccion();
        getGeneradorComponentesHtml().setAnchoCampos("200");

        pagina_local = new Pagina();
        pagina_local.setEncabezadoPagina(obtenerEncabezadoPaginaConfiguracion());
        pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(conformarEventosBody(listaCampos_local, "formularioIncluir")));

        pagina_local.setContenidoDatos(getGeneradorComponentesHtml().insertarImagenCentrada("", "../imagenes/" + CargadorPropiedades.getCargadorPropiedades().obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"), "", "", true, false));

        listaParametrosRedireccion_local.adicionar("accion", String.valueOf(42));

        formulario_local = new Formulario();
        formulario_local.setNombre("formularioIncluir");
        formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
        formulario_local.setEventoOnSubmit("return verificarCampos();");
        formulario_local.setListaCampo(listaCampos_local);

        listaBotones_local.adicionar("aceptarinclusion", true, "", "Aceptar Inclusi\u00f3n", "", 0, false);

        listaBotones_local.adicionar("cancelar", false, " onClick=\"return cerrarVentana();\"", "Cancelar", "login.jsp", 0, false);

        formulario_local.setContenido(insertarFormulario(formulario_local, listaCampos_local, listaBotones_local, false, 1, "ADMINISTRADOR", -1, "center"));

        pagina_local.setContenidoFormulario(formulario_local.dibujar());

        pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
    } catch (Throwable excepcion) {
        excepcion.printStackTrace();
    } finally {

        formulario_local = null;
        listaCampos_local = null;
        rutaArchivo_local = null;
        listaBotones_local = null;
        conexionPostgres_local = null;
        listaParametrosRedireccion_local = null;
    } 
    
    return pagina_local;
}
private int getTipoUsuario() {
	return pTipoUsuario;
}
private void setTipoUsuario(int pTipoUsuario) {
	this.pTipoUsuario = pTipoUsuario;
}
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\generadores\GeneradorPagina.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */