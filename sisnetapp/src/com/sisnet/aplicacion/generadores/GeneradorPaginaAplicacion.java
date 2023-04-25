package com.sisnet.aplicacion.generadores;

//import com.sisnet.aplicacion.generadores.GeneradorPagina;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorArchivos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.aplicacion.manejadores.ManejadorOperaciones;
import com.sisnet.aplicacion.manejadores.ManejadorPermisoUsuario;
import com.sisnet.aplicacion.manejadores.ManejadorPlantilla;
import com.sisnet.aplicacion.manejadores.ManejadorSesion;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorInformacionRecalculable;
import com.sisnet.baseDatos.CamposAdministrador;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultaSQLPrincipal;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesMensajesAplicacion;
import com.sisnet.controlesHTML.Formulario;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.ListaBotones;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaConsulta;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.ListaParametrosRedireccion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import com.sisnet.objetosManejo.manejoPaginaJsp.Boton;
import com.sisnet.objetosManejo.manejoPaginaJsp.ItemConsulta;
import com.sisnet.objetosManejo.manejoPaginaJsp.Pagina;
import com.sisnet.objetosManejo.manejoReportes.ReporteExcel;
import com.sisnet.utilidades.CargadorPropiedades;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class GeneradorPaginaAplicacion extends GeneradorPagina {
	private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
	private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
	private static ManejadorOperaciones op = ManejadorOperaciones.getManejadorOperaciones();
	private static CamposAdministrador cad = CamposAdministrador.getCamposAdministrador();
	private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
	private int aNumeroRegistrosConsultaPrincipal;
	private boolean aHuboAsignacionEventos;
	// Create a HashMap object called valuesOfExternalTable, to hold referenced
	// values in a external table
	HashMap<String, HashMap<String, String>> valuesOfExternalTable = new HashMap<String, HashMap<String, String>>();

	// Create a HashMap object called valuesOfRelatedTable, to hold referenced
	// values in a external table
	HashMap<Aplicacion, HashMap<String, String>> valuesOfRelatedTable = new HashMap<Aplicacion, HashMap<String, String>>();

	public GeneradorPaginaAplicacion(int pTipoUsuario) {
		super(pTipoUsuario);
		setNumeroRegistrosConsultaPrincipal(0);
		setHuboAsignacionEventos(false);
	}

	public int getNumeroRegistrosConsultaPrincipal() {
		return this.aNumeroRegistrosConsultaPrincipal;
	}

	public void setNumeroRegistrosConsultaPrincipal(int pNumeroRegistrosConsultaPrincipal) {
		this.aNumeroRegistrosConsultaPrincipal = pNumeroRegistrosConsultaPrincipal;
	}

	public boolean esHuboAsignacionEventos() {
		return this.aHuboAsignacionEventos;
	}

	public void setHuboAsignacionEventos(boolean pHuboAsignacionEventos) {
		this.aHuboAsignacionEventos = pHuboAsignacionEventos;
	}

	private String conformarMenuOpcionesConsultaCampos(int pIdAplicacion) {
		String menuOpcionesConsultaCampos_local = "";
		String parametrosRedireccion_local = null;
		int idGrupoInformacion_local = -1;
		ListaGrupoInformacion listaGruposInformacion_local = null;
		ListaCampo listaCampo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Iterator iterador_local = null;
		Iterator iteradorCampos_local = null;
		GrupoInformacion grupoInformacion_local = null;
		Campo campo_local = null;

		try {
			listaGruposInformacion_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaGruposInformacionOpcionConsulta(pIdAplicacion);

			iterador_local = listaGruposInformacion_local.iterator();
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			menuOpcionesConsultaCampos_local = mc.concatenarCadena(menuOpcionesConsultaCampos_local,
					"startSubmenu(\"menubusqueda_b1\",\"menubusqueda_menu\",250);\n");

			while (iterador_local.hasNext()) {
				grupoInformacion_local = (GrupoInformacion) iterador_local.next();
				idGrupoInformacion_local = grupoInformacion_local.getIdGrupoInformacion();
				listaCampo_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerListaCamposOpcionConsulta(idGrupoInformacion_local);
				iteradorCampos_local = listaCampo_local.iterator();
				while (iteradorCampos_local.hasNext()) {
					campo_local = (Campo) iteradorCampos_local.next();
					listaParametrosRedireccion_local.borrarElementos();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(35));

					listaParametrosRedireccion_local.adicionar("fldidcampo", String.valueOf(campo_local.getIdCampo()));
					parametrosRedireccion_local = listaParametrosRedireccion_local.concatenarParametros();
					menuOpcionesConsultaCampos_local = mc.concatenarCadena(menuOpcionesConsultaCampos_local,
							"submenuItem(\"" + mc.darFormatoTitulo(campo_local.getEtiquetaCampo()) + "\",\""
									+ parametrosRedireccion_local + "\",\"\",\"menubusqueda_plain\");\n");
				}
			}

			menuOpcionesConsultaCampos_local = mc.concatenarCadena(menuOpcionesConsultaCampos_local,
					"endSubmenu(\"menubusqueda_b1\");\n");
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			parametrosRedireccion_local = null;
			listaGruposInformacion_local = null;
			listaCampo_local = null;
			listaParametrosRedireccion_local = null;
			iterador_local = null;
			iteradorCampos_local = null;
			grupoInformacion_local = null;
			campo_local = null;
		}
		return menuOpcionesConsultaCampos_local;
	}

	private String conformarMenuOrdenadoPorCampos(int pIdAplicacion) {
		String menuOrdenadoPorCampos_local = "";
		String parametrosRedireccion_local = null;
		int idGrupoInformacion_local = -1;
		ListaGrupoInformacion listaGruposInformacion_local = null;
		ListaCampo listaCampo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Iterator iterador_local = null;
		Iterator iteradorCampos_local = null;
		GrupoInformacion grupoInformacion_local = null;
		Campo campo_local = null;

		try {
			listaGruposInformacion_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaGruposInformacionOpcionConsulta(pIdAplicacion);

			iterador_local = listaGruposInformacion_local.iterator();

			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			while (iterador_local.hasNext()) {
				grupoInformacion_local = (GrupoInformacion) iterador_local.next();
				idGrupoInformacion_local = grupoInformacion_local.getIdGrupoInformacion();
				listaCampo_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerListaCamposOpcionConsulta(idGrupoInformacion_local);
				iteradorCampos_local = listaCampo_local.iterator();
				menuOrdenadoPorCampos_local = mc.concatenarCadena(menuOrdenadoPorCampos_local,
						"startSubmenu(\"menuordenadopor_b1\",\"menuordenadopor_menu\",250);\n");

				while (iteradorCampos_local.hasNext()) {
					campo_local = (Campo) iteradorCampos_local.next();
					listaParametrosRedireccion_local.borrarElementos();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(35));

					listaParametrosRedireccion_local.adicionar("ordenadopor", String.valueOf(true));

					listaParametrosRedireccion_local.adicionar("fldidcampo", String.valueOf(campo_local.getIdCampo()));

					parametrosRedireccion_local = listaParametrosRedireccion_local.concatenarParametros();
					menuOrdenadoPorCampos_local = mc.concatenarCadena(menuOrdenadoPorCampos_local,
							"submenuItem(\"" + mc.darFormatoTitulo(campo_local.getEtiquetaCampo()) + "\",\""
									+ parametrosRedireccion_local + "\",\"\",\"menuordenadopor_plain\");\n");
				}

				menuOrdenadoPorCampos_local = mc.concatenarCadena(menuOrdenadoPorCampos_local,
						"endSubmenu(\"menuordenadopor_b1\");\n");
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			parametrosRedireccion_local = null;
			listaGruposInformacion_local = null;
			listaCampo_local = null;
			listaParametrosRedireccion_local = null;
			iterador_local = null;
			iteradorCampos_local = null;
			grupoInformacion_local = null;
			campo_local = null;
		}
		return menuOrdenadoPorCampos_local;
	}

	private String insertarCampoTipoValorDependiente(int pIdTabla, int pValorTablaSeleccionado, String pEventos,
			ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones) {
		String campoValorDependiente_local = "";
		Tabla tabla_local = null;
		ListaGeneral listaValoresTabla_local = null;

		if (pEventos == ConstantesGeneral.VALOR_NULO) {
			return campoValorDependiente_local;
		}
		if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
			return campoValorDependiente_local;
		}

		try {
			tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla);
			listaValoresTabla_local = getAdministradorBaseDatosAplicacion().obtenerListaValoresTabla(
					tabla_local.getNombreTabla(), pValorTablaSeleccionado, pListaCadenas, pExcluirValores,
					pExistenRestricciones, false);

			campoValorDependiente_local = mc.concatenarCadena(campoValorDependiente_local,
					getGeneradorComponentesHtml().insertarCampoListaDesplegable("Valor", "fldidvalordetalle",
							listaValoresTabla_local, pEventos, "center", false, false));

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			tabla_local = null;
			listaValoresTabla_local = null;
		}
		return campoValorDependiente_local;
	}

	private String insertarCampoTipoValorDependienteConsulta(int pIdTabla, int pValorTablaSeleccionado) {
		String campoValorDependiente_local = "";
		Tabla tabla_local = null;
		ListaGeneral listaValoresTabla_local = null;
		ListaCadenas listaCadenas_local = null;

		try {
			tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla);
			listaCadenas_local = new ListaCadenas();
			listaValoresTabla_local = getAdministradorBaseDatosAplicacion().obtenerListaValoresTabla(
					tabla_local.getNombreTabla(), pValorTablaSeleccionado, listaCadenas_local, false, false, false);

			campoValorDependiente_local = mc.concatenarCadena(campoValorDependiente_local,
					getGeneradorComponentesHtml().insertarCampoListaDesplegableConsulta("Valor", "fldidvalordetalle",
							listaValoresTabla_local, "center"));

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			tabla_local = null;
			listaCadenas_local = null;
			listaValoresTabla_local = null;
		}
		return campoValorDependiente_local;
	}

	private ListaGeneral obtenerListaPlantillasOReportes(Aplicacion pAplicacionPlantillaOReporte,
			int pValorSeleccionado, boolean pEsPlantilla) {
		ListaGeneral listaValores_local = null;
		int idPrimerGrupo_local = -1;
		Campo campo_local = null;

		if (pAplicacionPlantillaOReporte == ConstantesGeneral.VALOR_NULO) {
			return listaValores_local;
		}

		try {
			if (pAplicacionPlantillaOReporte.getIdAplicacion() != 0) {
				idPrimerGrupo_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerIdPrimerGrupoInformacionNoMultipleAplicacion(
								pAplicacionPlantillaOReporte.getIdAplicacion());

				campo_local = getManejadorSesion().obtenerMotorAplicacion().obtenerCampoPorPosicion(1,
						idPrimerGrupo_local);

				if (campo_local != ConstantesGeneral.VALOR_NULO && getAdministradorBaseDatosAplicacion()
						.verificarExistenciaTabla(pAplicacionPlantillaOReporte.getNombreAplicacion())) {
					listaValores_local = getAdministradorBaseDatosAplicacion().obtenerListaValoresPlantillasOReportes(
							pAplicacionPlantillaOReporte.getNombreAplicacion(), campo_local.getNombreCampo(),
							ap.conformarNombreCampoLlavePrimaria(pAplicacionPlantillaOReporte.getNombreAplicacion()),
							pValorSeleccionado, pEsPlantilla);

				}

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campo_local = null;
		}

		return listaValores_local;
	}

	private ListaGeneral obtenerListaReportesImpresionMasiva(Aplicacion pAplicacionImpresionMasiva) {
		ListaGeneral listaValores_local = null;
		Campo campo_local = null;

		if (pAplicacionImpresionMasiva == ConstantesGeneral.VALOR_NULO) {
			return listaValores_local;
		}

		try {
			if (pAplicacionImpresionMasiva.getIdAplicacion() != 0) {
				campo_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerPrimerCampoValorUnicoAplicacion(pAplicacionImpresionMasiva.getIdAplicacion());

				if (campo_local != ConstantesGeneral.VALOR_NULO && getAdministradorBaseDatosAplicacion()
						.verificarExistenciaTabla(pAplicacionImpresionMasiva.getNombreAplicacion())) {
					listaValores_local = getAdministradorBaseDatosAplicacion()
							.obtenerListaValoresReportesImpresionMasiva(
									pAplicacionImpresionMasiva.getNombreAplicacion(), campo_local.getNombreCampo(),
									ap.conformarNombreCampoLlavePrimaria(
											pAplicacionImpresionMasiva.getNombreAplicacion()));

				}
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campo_local = null;
		}

		return listaValores_local;
	}

	private String insertarCampoSeleccionarPlantilla(Campo pCampo, Aplicacion pAplicacionPlantilla,
			int pPlantillaUtilizar) {
		String campoDependiente_local = "";
		ListaGeneral listaCampos_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return campoDependiente_local;
		}
		if (pAplicacionPlantilla == ConstantesGeneral.VALOR_NULO) {
			return campoDependiente_local;
		}

		try {
			listaCampos_local = obtenerListaPlantillasOReportes(pAplicacionPlantilla, pPlantillaUtilizar, true);

			campoDependiente_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo,
					listaCampos_local, "", false, false);

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			listaCampos_local = null;
		}
		return campoDependiente_local;
	}

	private String insertarCampoEnlazadoConsulta(String pEtiquetaCampoConsulta, String pNombreCampoConsulta,
			Campo pCampo, int pRegistroSeleccionado) {
		String insertarLista_local = "";
		ListaGeneral listaGeneralCampos_local = null;
		ListaCadenas listaRestricciones_local = null;
		Campo campoValorUnico_local = null;

		if (pEtiquetaCampoConsulta == ConstantesGeneral.VALOR_NULO) {
			return insertarLista_local;
		}
		if (pNombreCampoConsulta == ConstantesGeneral.VALOR_NULO) {
			return insertarLista_local;
		}
		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarLista_local;
		}

		try {
			if (pCampo.getEnlaceCampo().getEnlazado().getIdAplicacion() != -1) {
				listaRestricciones_local = new ListaCadenas();
				campoValorUnico_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerPrimerCampoValorUnicoAplicacion(
								pCampo.getEnlaceCampo().getEnlazado().getIdAplicacion());

				listaGeneralCampos_local = getAdministradorBaseDatosAplicacion()
						.obtenerListaRegistrosPrimerCampoValorUnico(
								pCampo.getEnlaceCampo().getEnlazado().getNombreAplicacion(),
								campoValorUnico_local.getNombreCampo(), pRegistroSeleccionado,
								pCampo.getEnlaceCampo().esOpcionDesconocido(), listaRestricciones_local, false, false,
								"", true, false, false);
			}

			insertarLista_local = mc.concatenarCadena(insertarLista_local,
					getGeneradorComponentesHtml().insertarCampoListaDesplegableConsulta(pEtiquetaCampoConsulta,
							pNombreCampoConsulta, listaGeneralCampos_local, "left"));

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			listaGeneralCampos_local = null;
			listaRestricciones_local = null;
			campoValorUnico_local = null;
		}
		return insertarLista_local;
	}

	private String insertarCampoConsulta(Campo pCampo, int pIdTabla, int pNivelConsulta, boolean pEsCampoDesde,
			boolean pCambiarEtiquetaCampo) {
		String insertarCamposConsulta_local = "";
		int longitudCampo_local = -1;
		int idTabla_local = -1;
		int idValorSeleccionado_local = -1;
		String valorCampo_local = null;
		String tipoDato_local = null;
		String etiquetaCampo_local = null;
		String nombreCampo_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarCamposConsulta_local;
		}

		try {
			valorCampo_local = "";
			tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
			etiquetaCampo_local = pCampo.getEtiquetaCampo();
			longitudCampo_local = pCampo.getFormatoCampo().getLongitudCampo();
			nombreCampo_local = pCampo.conformarNombreCampoConsulta(pNivelConsulta, pEsCampoDesde);
			if (pCambiarEtiquetaCampo) {
				etiquetaCampo_local = pCampo.conformarEtiquetaCampoConsulta(pEsCampoDesde);
			}
			if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
				valorCampo_local = pCampo.getValorCampo().toString();
			}
			if (!pCampo.getRestriccionCampo().esLlaveForanea()) {
				if (pCampo.esCampoEnlazado()) {
					if (mc.sonCadenasIguales(valorCampo_local, "")) {
						valorCampo_local = String.valueOf(-1);
					}
					return mc.concatenarCadena(insertarCamposConsulta_local, insertarCampoEnlazadoConsulta(
							etiquetaCampo_local, nombreCampo_local, pCampo, Integer.parseInt(valorCampo_local)));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "T") || mc.sonCadenasIguales(tipoDato_local, "K")
						|| mc.sonCadenasIguales(tipoDato_local, "X") || mc.sonCadenasIguales(tipoDato_local, "MM")
						|| mc.sonCadenasIguales(tipoDato_local, "DD") || mc.sonCadenasIguales(tipoDato_local, "NN")) {

					return mc.concatenarCadena(insertarCamposConsulta_local,
							getGeneradorComponentesHtml().insertarCampoTextoConsulta(etiquetaCampo_local,
									nombreCampo_local, valorCampo_local, longitudCampo_local, "left"));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "E") || mc.sonCadenasIguales(tipoDato_local, "LL")
						|| mc.sonCadenasIguales(tipoDato_local, "BB") || mc.sonCadenasIguales(tipoDato_local, "XX")) {

					return mc.concatenarCadena(insertarCamposConsulta_local,
							getGeneradorComponentesHtml().insertarCampoNumeroEnteroConsulta(etiquetaCampo_local,
									nombreCampo_local, longitudCampo_local, valorCampo_local, "left",
									mc.sonCadenasIguales(tipoDato_local, "BB")));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "R") || mc.sonCadenasIguales(tipoDato_local, "GG")) {
					return mc.concatenarCadena(insertarCamposConsulta_local,
							getGeneradorComponentesHtml().insertarCampoNumeroRealConsulta(etiquetaCampo_local,
									nombreCampo_local, valorCampo_local, "left",
									mc.sonCadenasIguales(tipoDato_local, "GG")));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "F")) {
					return mc.concatenarCadena(insertarCamposConsulta_local,
							getGeneradorComponentesHtml().insertarCampoFechaConsulta(etiquetaCampo_local,
									nombreCampo_local, valorCampo_local, "%Y-%m-%d", "left"));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "H")) {
					return mc.concatenarCadena(insertarCamposConsulta_local,
							getGeneradorComponentesHtml().insertarCampoHoraConsulta(etiquetaCampo_local,
									nombreCampo_local, valorCampo_local, "left"));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "FF")) {
					if (mc.sonCadenasIguales(valorCampo_local, "")) {
						valorCampo_local = String.valueOf(0);
					}
					return mc.concatenarCadena(insertarCamposConsulta_local,
							insertarCampoTipoValorDependienteConsulta(pIdTabla, Integer.parseInt(valorCampo_local)));

				}

			} else if (!mc.sonCadenasIguales(etiquetaCampo_local, "") && mc.esCadenaNumerica(tipoDato_local, true)) {

				if (mc.sonCadenasIguales(valorCampo_local, "")) {
					valorCampo_local = String.valueOf(-1);
				}
				idTabla_local = Integer.parseInt(tipoDato_local);
				idValorSeleccionado_local = Integer.parseInt(valorCampo_local);

				return mc.concatenarCadena(insertarCamposConsulta_local, insertarCampoTipoTablaConsulta(idTabla_local,
						idValorSeleccionado_local, nombreCampo_local, etiquetaCampo_local));
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			valorCampo_local = null;
			tipoDato_local = null;
			etiquetaCampo_local = null;
			nombreCampo_local = null;
		}

		return insertarCamposConsulta_local;
	}

	private String dibujarCampoConsulta(Campo pCampo, int pNivelConsulta, boolean pEsCampoDesde,
			boolean pCambiarEtiquetaCampo) {
		String campoConsulta_local = "";

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return campoConsulta_local;
		}

		try {
			campoConsulta_local = mc.concatenarCadena(campoConsulta_local,
					insertarCampoConsulta(pCampo, -1, pNivelConsulta, pEsCampoDesde, pCambiarEtiquetaCampo));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return campoConsulta_local;
	}

	private String dibujarCampoOrdenadoPorConsulta(Campo pCampo, int pNivelConsulta, boolean pOrdenAscendente) {
		String campoOrdenadoPorConsulta_local = "";
		String nombreCampo_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return campoOrdenadoPorConsulta_local;
		}

		try {
			nombreCampo_local = mc.concatenarCadena(pCampo.getNombreCampo(), String.valueOf(pNivelConsulta));

			campoOrdenadoPorConsulta_local = getGeneradorComponentesHtml()
					.insertarCampoOrdenadoPorConsulta(pCampo.getEtiquetaCampo());

			campoOrdenadoPorConsulta_local = mc.concatenarCadena(campoOrdenadoPorConsulta_local,
					getGeneradorComponentesHtml().insertarCampoCajaSeleccionConsulta(nombreCampo_local, "Ascendente",
							pOrdenAscendente, 1, "left"));

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			nombreCampo_local = null;
		}

		return campoOrdenadoPorConsulta_local;
	}

	private String dibujarCampoNuloConsulta() {
		String campoNuloConsulta_local = "";

		try {
			campoNuloConsulta_local = mc.concatenarCadena(campoNuloConsulta_local,
					getGeneradorComponentesHtml().insertarCampoNuloConsulta());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return campoNuloConsulta_local;
	}

	private String insertarCamposListaConsulta() {
		String camposConsultaNivel_local = "";
		int nivelConsulta_local = -1;
		boolean insertarOpcionDesdeHasta_local = false;
		boolean esOrdenadoPor_local = false;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Iterator iterator_local = null;
		ItemConsulta itemConsulta_local = null;
		Campo campoDesde_local = null;
		Campo campoHasta_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO
					&& getManejadorSesion().obtenerAtributoListaConsulta() != ConstantesGeneral.VALOR_NULO) {
				iterator_local = getManejadorSesion().obtenerAtributoListaConsulta().iterator();
				while (iterator_local.hasNext()) {
					insertarOpcionDesdeHasta_local = false;
					itemConsulta_local = (ItemConsulta) iterator_local.next();
					nivelConsulta_local = itemConsulta_local.getNivelConsulta();
					camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local,
							getGeneradorComponentesHtml().abrirFilaTabla());

					campoDesde_local = itemConsulta_local.getCampoDesde();
					esOrdenadoPor_local = itemConsulta_local.esOrdenadoPor();

					insertarOpcionDesdeHasta_local = (campoDesde_local.utilizarOpcionDesdeHastaParaConsulta()
							&& !esOrdenadoPor_local);

					if (insertarOpcionDesdeHasta_local) {
						camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local, dibujarCampoConsulta(
								campoDesde_local, nivelConsulta_local, true, insertarOpcionDesdeHasta_local));
					} else {

						camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local,
								dibujarCampoNuloConsulta());
					}

					if (!esOrdenadoPor_local) {
						campoHasta_local = itemConsulta_local.getCampoHasta();
						camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local, dibujarCampoConsulta(
								campoHasta_local, nivelConsulta_local, false, insertarOpcionDesdeHasta_local));
					} else {

						camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local,
								dibujarCampoOrdenadoPorConsulta(campoDesde_local, nivelConsulta_local,
										itemConsulta_local.esOrdenAscendente()));
					}

					listaParametrosRedireccion_local = new ListaParametrosRedireccion();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(37));

					listaParametrosRedireccion_local.adicionar("nivelConsulta", String.valueOf(nivelConsulta_local));

					if (itemConsulta_local.esPermitirBorrar()) {
						camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local,
								getGeneradorComponentesHtml().insertarCeldaBorrarOpcionConsulta(
										listaParametrosRedireccion_local.concatenarParametros()));
					} else {

						camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local,
								getGeneradorComponentesHtml().crearCeldaTransparente("", "", ""));
					}

					camposConsultaNivel_local = mc.concatenarCadena(camposConsultaNivel_local,
							getGeneradorComponentesHtml().cerrarFilaTabla());
				}

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			iterator_local = null;
			campoDesde_local = null;
			campoHasta_local = null;
			itemConsulta_local = null;
			listaParametrosRedireccion_local = null;
		}

		return camposConsultaNivel_local;
	}

	private String insertarMenuOpcionesConsulta(int pIdAplicacion) {
		String menuOpcionesConsulta_local = "";
		String contenidoCeldaMenuOpciones_local = null;

		try {
			contenidoCeldaMenuOpciones_local = "<script src=\"../utilidades/javascript/menubusqueda/xaramenu.js\"></script>";

			contenidoCeldaMenuOpciones_local = mc.concatenarCadena(contenidoCeldaMenuOpciones_local,
					"<script src=\"../utilidades/javascript/menubusqueda/menubusqueda.js\"></script>\n");

			contenidoCeldaMenuOpciones_local = mc.concatenarCadena(contenidoCeldaMenuOpciones_local,
					"<script languaje=\"javascript\">\n");

			contenidoCeldaMenuOpciones_local = mc.concatenarCadena(contenidoCeldaMenuOpciones_local,
					"startMainMenu(\"\",0,0,1,0,0)\nmainMenuItem(\"menubusqueda_b1\",\".gif\",18,126,\"javascript:;\",\"\",\"Seleccionar Opci\u00F3n\",1,2,\"menubusqueda_plain\", true);\nendMainMenu(\"\",0,0);\n");

			contenidoCeldaMenuOpciones_local = mc.concatenarCadena(contenidoCeldaMenuOpciones_local,
					conformarMenuOpcionesConsultaCampos(pIdAplicacion));

			contenidoCeldaMenuOpciones_local = mc.concatenarCadena(contenidoCeldaMenuOpciones_local,
					" loc=\"\";\n </script>\n");

			menuOpcionesConsulta_local = mc.concatenarCadena(menuOpcionesConsulta_local,
					contenidoCeldaMenuOpciones_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			contenidoCeldaMenuOpciones_local = null;
		}
		return menuOpcionesConsulta_local;
	}

	private String insertarMenuOrdenadoPor(int pIdAplicacion) {
		String menuOrdenadoPor_local = "";
		String contenidoCeldaMenuOrdenadoPor_local = null;

		try {
			contenidoCeldaMenuOrdenadoPor_local = "<script src=\"../utilidades/javascript/menuordenadopor/xaramenu.js\"></script>";

			contenidoCeldaMenuOrdenadoPor_local = mc.concatenarCadena(contenidoCeldaMenuOrdenadoPor_local,
					"<script src=\"../utilidades/javascript/menuordenadopor/menuordenadopor.js\"></script>\n");

			contenidoCeldaMenuOrdenadoPor_local = mc.concatenarCadena(contenidoCeldaMenuOrdenadoPor_local,
					"<script languaje=\"javascript\">\n");

			contenidoCeldaMenuOrdenadoPor_local = mc.concatenarCadena(contenidoCeldaMenuOrdenadoPor_local,
					"startMainMenu(\"\",0,0,1,0,0)\nmainMenuItem(\"menuordenadopor_b1\",\".gif\",18,126,\"javascript:;\",\"\",\"Seleccionar Opci\u00F3n\",1,2,\"menuordenadopor_plain\", true);\nendMainMenu(\"\",0,0);\n");

			contenidoCeldaMenuOrdenadoPor_local = mc.concatenarCadena(contenidoCeldaMenuOrdenadoPor_local,
					conformarMenuOrdenadoPorCampos(pIdAplicacion));

			contenidoCeldaMenuOrdenadoPor_local = mc.concatenarCadena(contenidoCeldaMenuOrdenadoPor_local,
					" loc=\"\";\n </script>\n");

			menuOrdenadoPor_local = mc.concatenarCadena(menuOrdenadoPor_local, contenidoCeldaMenuOrdenadoPor_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			contenidoCeldaMenuOrdenadoPor_local = null;
		}
		return menuOrdenadoPor_local;
	}

	private int calcularAnchoTablaOpcionesConsulta() {
		int anchoTablaOpcionesConsulta_local = -1;

		try {
			anchoTablaOpcionesConsulta_local = 100 + Integer.parseInt("300") * 2 + 400;

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return anchoTablaOpcionesConsulta_local;
	}

	private String insertarFormularioConsultas(Formulario pFormulario) {
		String formularioAplicacion_local = "";
		ListaBotones listaBotones_local = null;
		GeneradorComponentesHtml gch = getGeneradorComponentesHtml();
		if (pFormulario == ConstantesGeneral.VALOR_NULO) {
			return formularioAplicacion_local;
		}

		try {
			gch.setAnchoCampos("300");

			formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local, gch.abrirTablaFormulario(
					"tablaAreaConsultas", String.valueOf(calcularAnchoTablaOpcionesConsulta()), "center"));

			formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local, insertarCamposListaConsulta());

			formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local, gch.cerrarTablaSinSalto());

			listaBotones_local = new ListaBotones();
			listaBotones_local.adicionar("ejecutarconsulta", true, "", "Ejecutar Consulta", "", 0, false, true);

			formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
					gch.insertarBotones(listaBotones_local, 0, "center"));

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			listaBotones_local = null;
		}

		return formularioAplicacion_local;
	}

	private String conformarOpcionesPrincipalesMenuPrincipal() {
		String opcionesPrincipalesMenuPrincipal_local = "";

		try {
			opcionesPrincipalesMenuPrincipal_local = opcionesPrincipalesMenuPrincipal_local
					.concat("startMainMenu(\"\",18,2,2,3,0);\n");

			opcionesPrincipalesMenuPrincipal_local = opcionesPrincipalesMenuPrincipal_local.concat(
					"mainMenuItem(\"menuprincipal_b1\",\".gif\",18,45,\"javascript:;\",\"\",\"Men\u00fa\",2,2,\"menuprincipal_plain\", true);\n");

			opcionesPrincipalesMenuPrincipal_local = opcionesPrincipalesMenuPrincipal_local.concat(
					"mainMenuItem(\"menuprincipal_b2\",\".gif\",18,45,\"javascript:;\",\"\",\"Aplicaci\u00f3n\",2,2,\"menuprincipal_plain\", true);\n");

			opcionesPrincipalesMenuPrincipal_local = opcionesPrincipalesMenuPrincipal_local.concat(
					"mainMenuItem(\"menuprincipal_b3\",\".gif\",18,45,\"javascript:;\",\"\",\"Tabla\",2,2,\"menuprincipal_plain\", true);\n");

			opcionesPrincipalesMenuPrincipal_local = opcionesPrincipalesMenuPrincipal_local.concat(
					"mainMenuItem(\"menuprincipal_b4\",\".gif\",18,45,\"javascript:;\",\"\",\"Reportes\",2,2,\"menuprincipal_plain\", true);\n");

			opcionesPrincipalesMenuPrincipal_local = opcionesPrincipalesMenuPrincipal_local
					.concat("endMainMenu(\"\",18,2);\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		}

		return opcionesPrincipalesMenuPrincipal_local;
	}

	private String conformarMenuConfiguracionMenuPrincipal(boolean pEsUsuarioAdministradorSistema) {
		String menuConfiguracionMenuPrincipal_local = "";
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String destinoConfiguracionAplicaciones_local = null;
		Aplicacion aplicacionActual_local = null;

		try {
			aplicacionActual_local = getManejadorSesion().obtenerAplicacionActual();
			menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
					"startSubmenu(\"menuprincipal_b1_1\",\"menuprincipal_menu\",230);\n");

			listaParametrosRedireccion_local = new ListaParametrosRedireccion();

			if (pEsUsuarioAdministradorSistema) {
				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(86));

				listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

				listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(1));

				destinoConfiguracionAplicaciones_local = listaParametrosRedireccion_local.concatenarParametros();
				menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
						"submenuItem(\"Crear Aplicaci\u00f3n\",\"" + destinoConfiguracionAplicaciones_local
								+ "\",\"\",\"menuprincipal_plain\","
								+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(86));

				listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

				listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(10));

				destinoConfiguracionAplicaciones_local = listaParametrosRedireccion_local.concatenarParametros();
				menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
						"submenuItem(\"Crear Tabla\",\"" + destinoConfiguracionAplicaciones_local
								+ "\",\"\",\"menuprincipal_plain\","
								+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");
			}

			if (pEsUsuarioAdministradorSistema) {
				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(88));

				listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

				if (aplicacionActual_local.esTabla()) {
					listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(10));
				} else {

					listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(1));
				}

				listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
						String.valueOf(aplicacionActual_local.getIdAplicacion()));

				destinoConfiguracionAplicaciones_local = listaParametrosRedireccion_local.concatenarParametros();
				menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
						"submenuItem(\"Propiedades de la Aplicaci\u00f3n\",\"" + destinoConfiguracionAplicaciones_local
								+ "\",\"\",\"menuprincipal_plain\","
								+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(4));

				listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

				listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(11));

				listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
						String.valueOf(aplicacionActual_local.getIdAplicacion()));

				destinoConfiguracionAplicaciones_local = listaParametrosRedireccion_local.concatenarParametros();
				menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
						"submenuItem(\"Configuraci\u00f3n de Eventos - Resultados\",\""
								+ destinoConfiguracionAplicaciones_local + "\",\"\",\"menuprincipal_plain\","
								+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");
			}

			listaParametrosRedireccion_local.borrarElementos();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(84));

			listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

			listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(3));

			menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
					"submenuItem(\"Configuraci\u00f3n de Tablas Internas\",\""
							+ listaParametrosRedireccion_local.concatenarParametros()
							+ "\",\"\",\"menuprincipal_plain\","
							+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");

			listaParametrosRedireccion_local.borrarElementos();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(84));

			listaParametrosRedireccion_local.adicionar("configuracion", String.valueOf(true));

			listaParametrosRedireccion_local.adicionar("grupoinformacionactual", String.valueOf(2));

			menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
					"submenuItem(\"Configuraci\u00f3n Grupo Informaci\u00f3n\",\""
							+ listaParametrosRedireccion_local.concatenarParametros()
							+ "\",\"\",\"menuprincipal_plain\","
							+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");

			menuConfiguracionMenuPrincipal_local = mc.concatenarCadena(menuConfiguracionMenuPrincipal_local,
					"endSubmenu(\"menuprincipal_b1_1\");\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			aplicacionActual_local = null;
			listaParametrosRedireccion_local = null;
			destinoConfiguracionAplicaciones_local = null;
		}

		return menuConfiguracionMenuPrincipal_local;
	}

	private String conformarMenuAplicarEvento() {
		String menuAplicarEvento_local = "";
		Aplicacion aplicacionActual_local = null;
		int numeroEventoUsuario_local = 0;
		ListaCadenas listaEventosUsuario_local = null;
		Iterator iteradorEventosUsuario_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;

		try {
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			aplicacionActual_local = getManejadorSesion().obtenerAplicacionActual();
			menuAplicarEvento_local = mc.concatenarCadena(menuAplicarEvento_local,
					"startSubmenu(\"menuprincipal_b1_2\",\"menuprincipal_menu\",230);\n");

			listaEventosUsuario_local = mc
					.obtenerParrafoComoListaCadenas(aplicacionActual_local.getTipoEventosUsuario());
			iteradorEventosUsuario_local = listaEventosUsuario_local.iterator();
			for (numeroEventoUsuario_local = 0; iteradorEventosUsuario_local.hasNext(); numeroEventoUsuario_local++) {
				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(45));

				listaParametrosRedireccion_local.adicionar("opcionEvento", String.valueOf(numeroEventoUsuario_local));

				menuAplicarEvento_local = mc.concatenarCadena(menuAplicarEvento_local,
						"submenuItem(\"" + (String) iteradorEventosUsuario_local.next() + "\",\""
								+ listaParametrosRedireccion_local.concatenarParametros()
								+ "\",\"\",\"menuprincipal_plain\","
								+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");
			}

			menuAplicarEvento_local = mc.concatenarCadena(menuAplicarEvento_local,
					"endSubmenu(\"menuprincipal_b1_2\");\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			aplicacionActual_local = null;
			listaEventosUsuario_local = null;
			iteradorEventosUsuario_local = null;
			listaParametrosRedireccion_local = null;
		}

		return menuAplicarEvento_local;
	}

	private String conformarMenuAplicacionMenuPrincipal() {
		String menuAplicacionMenuPrincipal_local = "";
		ListaAplicacion listaAplicacion_local = null;
		Iterator iteradorAplicaciones_local = null;
		Aplicacion aplicacion_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String destinoConfiguracion_local = null;

		try {
			destinoConfiguracion_local = "javascript:;";
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaAplicacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaAplicaciones(-1, true);

			iteradorAplicaciones_local = listaAplicacion_local.iterator();

			menuAplicacionMenuPrincipal_local = mc.concatenarCadena(menuAplicacionMenuPrincipal_local,
					"startSubmenu(\"menuprincipal_b2\",\"menuprincipal_menu\",250);\n");

			while (iteradorAplicaciones_local.hasNext()) {
				aplicacion_local = (Aplicacion) iteradorAplicaciones_local.next();
				if (getManejadorPermisoUsuario().verificarPermisoVerRegistrosAplicacion(aplicacion_local)) {
					listaParametrosRedireccion_local.borrarElementos();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(1));

					listaParametrosRedireccion_local.adicionar("fldidaplicacion",
							String.valueOf(aplicacion_local.getIdAplicacion()));

					destinoConfiguracion_local = listaParametrosRedireccion_local.concatenarParametros();
					menuAplicacionMenuPrincipal_local = mc.concatenarCadena(menuAplicacionMenuPrincipal_local,
							"submenuItem(\"" + aplicacion_local.getTituloAplicacion() + "\",\""
									+ destinoConfiguracion_local + "\",\"\",\"menuprincipal_plain\","
									+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");

					continue;
				}

				destinoConfiguracion_local = "javascript: mensajeAplicacionNoPermitida();";
				menuAplicacionMenuPrincipal_local = mc.concatenarCadena(menuAplicacionMenuPrincipal_local,
						"submenuItem(\"" + aplicacion_local.getTituloAplicacion() + "\",\"" + destinoConfiguracion_local
								+ "\",\"\",\"menuprincipal_plain\",\"\");\n");
			}

			menuAplicacionMenuPrincipal_local = mc.concatenarCadena(menuAplicacionMenuPrincipal_local,
					"endSubmenu(\"menuprincipal_b2\");\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			listaAplicacion_local = null;
			iteradorAplicaciones_local = null;
			aplicacion_local = null;
			listaParametrosRedireccion_local = null;
			destinoConfiguracion_local = null;
		}

		return menuAplicacionMenuPrincipal_local;
	}

	private String conformarMenuTablasMenuPrincipal() {
		String menuTablasMenuPrincipal_local = "";
		ListaAplicacion listaAplicacion_local = null;
		Iterator iteradorAplicaciones_local = null;
		Aplicacion aplicacion_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String destinoConfiguracion_local = null;

		try {
			destinoConfiguracion_local = "javascript:;";
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaAplicacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaAplicacionesTabla(-1,
					true);

			iteradorAplicaciones_local = listaAplicacion_local.iterator();
			menuTablasMenuPrincipal_local = mc.concatenarCadena(menuTablasMenuPrincipal_local,
					"startSubmenu(\"menuprincipal_b3\",\"menuprincipal_menu\",250);\n");

			while (iteradorAplicaciones_local.hasNext()) {
				aplicacion_local = (Aplicacion) iteradorAplicaciones_local.next();
				if (getManejadorPermisoUsuario().verificarPermisoVerRegistrosAplicacion(aplicacion_local)) {
					listaParametrosRedireccion_local.borrarElementos();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(1));

					listaParametrosRedireccion_local.adicionar("fldidaplicacion",
							String.valueOf(aplicacion_local.getIdAplicacion()));

					destinoConfiguracion_local = listaParametrosRedireccion_local.concatenarParametros();
					menuTablasMenuPrincipal_local = mc.concatenarCadena(menuTablasMenuPrincipal_local,
							"submenuItem(\"" + aplicacion_local.getTituloAplicacion() + "\",\""
									+ destinoConfiguracion_local + "\",\"\",\"menuprincipal_plain\","
									+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");

					continue;
				}

				destinoConfiguracion_local = "javascript: mensajeAplicacionNoPermitida();";
				menuTablasMenuPrincipal_local = mc.concatenarCadena(menuTablasMenuPrincipal_local,
						"submenuItem(\"" + aplicacion_local.getTituloAplicacion() + "\",\"" + destinoConfiguracion_local
								+ "\",\"\",\"menuprincipal_plain\",\"\");\n");
			}

			menuTablasMenuPrincipal_local = mc.concatenarCadena(menuTablasMenuPrincipal_local,
					"endSubmenu(\"menuprincipal_b3\");\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			listaAplicacion_local = null;
			iteradorAplicaciones_local = null;
			aplicacion_local = null;
			listaParametrosRedireccion_local = null;
			destinoConfiguracion_local = null;
		}

		return menuTablasMenuPrincipal_local;
	}

	private String conformarMenuReportesMenuPrincipal() {
		String menuReportesMenuPrincipal_local = "";
		ListaGeneral listaReportes_local = null;
		ItemLista itemLista_local = null;
		Iterator iteradorAplicaciones_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String destinoConfiguracion_local = null;

		try {
			destinoConfiguracion_local = "javascript:;";
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();

			menuReportesMenuPrincipal_local = mc.concatenarCadena(menuReportesMenuPrincipal_local,
					"startSubmenu(\"menuprincipal_b4\",\"menuprincipal_menu\",250);\n");

			if (getManejadorPermisoUsuario()
					.verificarPermisoOpcionReportes(getManejadorSesion().obtenerAplicacionActual())) {
				listaReportes_local = obtenerListaPlantillasOReportes(
						getManejadorSesion().obtenerAplicacionActual().getAplicacionReporte(), -1, false);

				if (listaReportes_local != ConstantesGeneral.VALOR_NULO) {
					iteradorAplicaciones_local = listaReportes_local.iterator();
					while (iteradorAplicaciones_local.hasNext()) {
						itemLista_local = (ItemLista) iteradorAplicaciones_local.next();
						listaParametrosRedireccion_local.borrarElementos();
						listaParametrosRedireccion_local.adicionar("accion", String.valueOf(39));

						listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
								itemLista_local.getValorItem());

						destinoConfiguracion_local = listaParametrosRedireccion_local.concatenarParametros();
						menuReportesMenuPrincipal_local = mc.concatenarCadena(menuReportesMenuPrincipal_local,
								"submenuItem(\"" + itemLista_local.getNombreItem() + "\",\""
										+ destinoConfiguracion_local + "\",\"\",\"menuprincipal_plain\","
										+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");
					}
				}
			}

			menuReportesMenuPrincipal_local = mc.concatenarCadena(menuReportesMenuPrincipal_local,
					"endSubmenu(\"menuprincipal_b4\");\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			listaReportes_local = null;
			itemLista_local = null;
			iteradorAplicaciones_local = null;
			destinoConfiguracion_local = null;
		}

		return menuReportesMenuPrincipal_local;
	}

	private String conformarMenuImpresionMasivaMenuPrincipal() {
		String menuImpresionMasivaMenuPrincipal_local = "";
		ListaGeneral listaReportesImpresionMasiva_local = null;
		ItemLista itemLista_local = null;
		Iterator iteradorAplicaciones_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String destinoConfiguracion_local = null;

		try {
			destinoConfiguracion_local = "javascript:;";
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();

			menuImpresionMasivaMenuPrincipal_local = mc.concatenarCadena(menuImpresionMasivaMenuPrincipal_local,
					"startSubmenu(\"menuprincipal_b1_3\",\"menuprincipal_menu\",250);\n");

			if (getManejadorPermisoUsuario()
					.verificarPermisoImpresionMasiva(getManejadorSesion().obtenerAplicacionActual())) {
				listaReportesImpresionMasiva_local = obtenerListaReportesImpresionMasiva(
						getManejadorSesion().obtenerAplicacionActual().getAplicacionImpresionMasiva());

				if (listaReportesImpresionMasiva_local != ConstantesGeneral.VALOR_NULO) {
					iteradorAplicaciones_local = listaReportesImpresionMasiva_local.iterator();
					while (iteradorAplicaciones_local.hasNext()) {
						itemLista_local = (ItemLista) iteradorAplicaciones_local.next();
						listaParametrosRedireccion_local.borrarElementos();
						listaParametrosRedireccion_local.adicionar("accion", String.valueOf(49));

						listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
								itemLista_local.getValorItem());

						destinoConfiguracion_local = listaParametrosRedireccion_local.concatenarParametros();
						menuImpresionMasivaMenuPrincipal_local = mc.concatenarCadena(
								menuImpresionMasivaMenuPrincipal_local,
								"submenuItem(\"" + itemLista_local.getNombreItem() + "\",\""
										+ destinoConfiguracion_local + "\",\"\",\"menuprincipal_plain\","
										+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");
					}
				}
			}

			menuImpresionMasivaMenuPrincipal_local = mc.concatenarCadena(menuImpresionMasivaMenuPrincipal_local,
					"endSubmenu(\"menuprincipal_b1_3\");\n");
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			listaReportesImpresionMasiva_local = null;
			itemLista_local = null;
			iteradorAplicaciones_local = null;
			destinoConfiguracion_local = null;
		}

		return menuImpresionMasivaMenuPrincipal_local;
	}

	private String insertarMenuPrincipal() {
		String menuPrincipal_local = "";
		boolean usuarioAdministradorSistema_local = false;
		boolean usuarioAdministradorSistemaSisnet_local = false;
		String destinoConfiguracion_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;

		try {
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					"<script src=\"../utilidades/javascript/menuprincipal/xaramenu.js?v=1\"></script>");

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					"<script src=\"../utilidades/javascript/menuprincipal/menuprincipal.js\"></script>\n");

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					getGeneradorComponentesHtml().abrirBloqueJavascript());
			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, conformarOpcionesPrincipalesMenuPrincipal());

			destinoConfiguracion_local = "javascript: mensajeUsuarioAdministrador();";
			usuarioAdministradorSistema_local = (getManejadorSesion().obtenerTipoUsuarioActual() == 0);

			if (usuarioAdministradorSistema_local
					|| getManejadorPermisoUsuario().obtenerPermisoUtilizarMenuConfiguracion()) {
				usuarioAdministradorSistemaSisnet_local = mc.sonCadenasIgualesIgnorarMayusculas(
						getManejadorSesion().obtenerUsuarioActual().getNombreUsuario(), "ADMINISTRADOR");

				menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
						conformarMenuConfiguracionMenuPrincipal(usuarioAdministradorSistemaSisnet_local));

				destinoConfiguracion_local = "javascript:;";
			}

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, conformarMenuAplicarEvento());

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					"startSubmenu(\"menuprincipal_b1\",\"menuprincipal_menu\",150);\n");

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					"mainMenuItem(\"menuprincipal_b1_1\",\"Configuraci\u00f3n\",0,0,\"" + destinoConfiguracion_local
							+ "\",\"\",\"\",1,1,\"menuprincipal_l\");\n");

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					"mainMenuItem(\"menuprincipal_b1_2\",\"Aplicar Evento\",0,0,\"" + destinoConfiguracion_local
							+ "\",\"\",\"\",1,1,\"menuprincipal_l\");\n");

			if (getManejadorPermisoUsuario()
					.verificarPermisoImportacion(getManejadorSesion().obtenerAplicacionActual())) {
				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(40));

				menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
						"submenuItem(\"Importaci\u00f3n\",\"" + listaParametrosRedireccion_local.concatenarParametros()
								+ "\",\"\",\"menuprincipal_plain\","
								+ "\"javascript: mostrarMensajeProcesandoInformacion();\"" + ");\n");
			} else {

				menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
						"submenuItem(\"Importaci\u00f3n\",\"javascript: mensajeAutorizacionImportacion();\",\"\",\"menuprincipal_plain\",\"\");\n");
			}

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					"mainMenuItem(\"menuprincipal_b1_3\",\"Impresi\u00f3n Masiva\",0,0,\"javascript:;\",\"\",\"\",1,1,\"menuprincipal_l\");\n");

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, "endSubmenu(\"menuprincipal_b1\");\n");

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, conformarMenuAplicacionMenuPrincipal());
			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, conformarMenuTablasMenuPrincipal());
			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, conformarMenuReportesMenuPrincipal());
			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, conformarMenuImpresionMasivaMenuPrincipal());

			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local, "loc=\"\";\n");
			menuPrincipal_local = mc.concatenarCadena(menuPrincipal_local,
					getGeneradorComponentesHtml().cerrarBloqueJavascript());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			destinoConfiguracion_local = null;
			listaParametrosRedireccion_local = null;
		}
		return menuPrincipal_local;
	}

	private String insertarTitulosInformacionPaginaPrincipal(String pTituloPrincipal, String pInformacionActual) {
		String informacionAplicacion_local = "";
		GeneradorComponentesHtml gch_local = getGeneradorComponentesHtml();
		if (pTituloPrincipal == ConstantesGeneral.VALOR_NULO) {
			return informacionAplicacion_local;
		}
		if (pInformacionActual == ConstantesGeneral.VALOR_NULO) {
			return informacionAplicacion_local;
		}

		try {
			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local,
					gch_local.abrirTablaTitulo("tablaInformacionAplicacion", "100%", ""));

			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local, gch_local.abrirFilaTabla());
			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local,
					gch_local.crearCeldaInformacionAplicacionAncho(insertarMenuPrincipal(), "20%", "left", ""));

			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local,
					gch_local.crearCeldaInformacionAplicacionAncho(pTituloPrincipal, "60%", "center", ""));

			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local,
					gch_local.crearCeldaInformacionAplicacionSubtituloAncho(pInformacionActual, "15%", "center", ""));

			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local,
					gch_local.crearCeldaInformacionAplicacionAncho(gch_local.insertarMenuSalir(), "5%", "right", ""));

			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local, gch_local.cerrarFilaTabla());

			informacionAplicacion_local = mc.concatenarCadena(informacionAplicacion_local, gch_local.cerrarTabla());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return informacionAplicacion_local;
	}

	private String dibujarAreaMenus(int pIdAplicacion) {
		String areaMenus_local = "";
		String tablaInterna_local = null;
		GeneradorComponentesHtml gch_local = getGeneradorComponentesHtml();
		try {
			tablaInterna_local = gch_local.abrirTablaTransparenteInterna("", "", "left");

			tablaInterna_local = mc.concatenarCadena(tablaInterna_local, gch_local.abrirFilaTabla());
			tablaInterna_local = mc.concatenarCadena(tablaInterna_local,
					gch_local.crearCeldaTituloEspecial(insertarMenuOpcionesConsulta(pIdAplicacion), "left", ""));

			tablaInterna_local = mc.concatenarCadena(tablaInterna_local,
					gch_local.crearCeldaTituloEspecial(insertarMenuOrdenadoPor(pIdAplicacion), "left", ""));

			tablaInterna_local = mc.concatenarCadena(tablaInterna_local, gch_local.cerrarFilaTabla());
			tablaInterna_local = mc.concatenarCadena(tablaInterna_local, gch_local.cerrarTablaSinSalto());

			areaMenus_local = mc.concatenarCadena(areaMenus_local,
					gch_local.abrirTablaTransparente("tablaMenus", "100%", "left"));

			areaMenus_local = mc.concatenarCadena(areaMenus_local, gch_local.abrirFilaTabla());
			areaMenus_local = mc.concatenarCadena(areaMenus_local,
					gch_local.crearCeldaTituloEspecial(tablaInterna_local, "left", "36%"));

			areaMenus_local = mc.concatenarCadena(areaMenus_local, gch_local
					.crearCeldaTituloEspecial(gch_local.insertarTituloTipo1("OPCIONES DE CONSULTA"), "center", "28%"));

			areaMenus_local = mc.concatenarCadena(areaMenus_local,
					gch_local.crearCeldaTituloEspecial("", "center", "36%"));

			areaMenus_local = mc.concatenarCadena(areaMenus_local, gch_local.cerrarFilaTabla());
			areaMenus_local = mc.concatenarCadena(areaMenus_local, gch_local.cerrarTablaSinSalto());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			tablaInterna_local = null;
		}

		return areaMenus_local;
	}

	private String dibujarAreaConsultas(Aplicacion pAplicacion) {
		String areaConsultas_local = "";
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Formulario formulario_local = null;
		String eventoOnSubmit_local = null;
		boolean advertirConsultaPrincipal_local = false;
		ListaConsulta listaConsulta_local = null;

		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return areaConsultas_local;
		}

		try {
			eventoOnSubmit_local = "";
			listaConsulta_local = getManejadorSesion().obtenerAtributoListaConsulta();
			if (listaConsulta_local != ConstantesGeneral.VALOR_NULO) {
				advertirConsultaPrincipal_local = (listaConsulta_local.contarElementos() == 0);
			}
			if (!pAplicacion.esPermitirConsultaGeneral() && advertirConsultaPrincipal_local) {
				eventoOnSubmit_local = mc.concatenarCadena(" return ", "advertenciaConsultaPrincipalSinOpciones();");

			} else if (pAplicacion.esAdvertenciaEjecucion() && advertirConsultaPrincipal_local) {
				eventoOnSubmit_local = mc.concatenarCadena(" return ", "advertenciaConsultaPrincipal();");
			}

			areaConsultas_local = mc.concatenarCadena(areaConsultas_local,
					dibujarAreaMenus(pAplicacion.getIdAplicacion()));

			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(36));

			formulario_local = new Formulario();
			formulario_local.setNombre("formularioConsultas");
			formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
			formulario_local.setEventoOnSubmit(eventoOnSubmit_local);
			areaConsultas_local = mc.concatenarCadena(areaConsultas_local,
					getGeneradorComponentesHtml().insertarParrafoSeparacion());
			formulario_local.setContenido(insertarFormularioConsultas(formulario_local));
			areaConsultas_local = mc.concatenarCadena(areaConsultas_local, formulario_local.dibujar());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			formulario_local = null;
			listaParametrosRedireccion_local = null;
			eventoOnSubmit_local = null;
			listaConsulta_local = null;
		}
		return areaConsultas_local;
	}

	private int calcularAnchoTablaConsultaPrincipal(ListaGrupoInformacion pListaGrupoInformacion) {
		int anchoTabla_local = 0;
		Iterator iterator_local = null;
		GrupoInformacion grupoInformacion_local = null;
		int anchoGrupoInformacion_local = 0;

		if (pListaGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return anchoTabla_local;
		}

		try {
			anchoTabla_local += 30;
			iterator_local = pListaGrupoInformacion.iterator();
			while (iterator_local.hasNext()) {
				grupoInformacion_local = (GrupoInformacion) iterator_local.next();
				if (getManejadorPermisoUsuario().verificarPermisoVerGrupoInformacion(grupoInformacion_local)
						|| getManejadorPermisoUsuario()
								.verificarExistenciaCampoPermisoVer(grupoInformacion_local.getIdGrupoInformacion())) {

					anchoGrupoInformacion_local = calcularAnchoGrupoInformacion(grupoInformacion_local, true);

					anchoTabla_local += anchoGrupoInformacion_local;
					grupoInformacion_local.setAnchoGrupoInformacion(anchoGrupoInformacion_local);
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			iterator_local = null;
			grupoInformacion_local = null;
		}
		return anchoTabla_local;
	}

	private String dibujarFilaDescripcionCampos(ListaGrupoInformacion pListaGrupoInformacion) {
		String filaDescripcionCampos_local = "";
		String etiquetaCampo_local = null;
		Iterator iterator_local = null;
		Iterator iteratorCampos_local = null;
		GrupoInformacion grupoInformacion_local = null;
		Campo campo_local = null;
		ListaCampo listaCampo_local = null;
		boolean tieneDocumentos_local = false;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String destino_local = null;
		Boton boton_local = null;
		String eventosBoton_local = null;
		int anchoColumna_local = 0;

		if (pListaGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return filaDescripcionCampos_local;
		}

		try {
			ManejadorSesion ms_local = getManejadorSesion();
			filaDescripcionCampos_local = mc.concatenarCadena(filaDescripcionCampos_local,
					getGeneradorComponentesHtml().abrirFilaTabla());

			tieneDocumentos_local = getAdministradorBaseDatosSisnet().verificarGrupoInformacionContieneCampoDocumento(
					ms_local.obtenerMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(
							ms_local.obtenerGrupoInformacionActual().getAplicacion().getIdAplicacion()));

			if (getManejadorPermisoUsuario().verificarPermisoIncluirRegistrosAplicacion(
					ms_local.obtenerGrupoInformacionActual().getAplicacion())
					|| getManejadorPermisoUsuario().verificarExistenciaGrupoInformacionNoMultiplePermisoIncluir(
							ms_local.obtenerGrupoInformacionActual().getAplicacion().getIdAplicacion())) {

				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(85));

				if (tieneDocumentos_local) {
					listaParametrosRedireccion_local.adicionar("esdocumento", String.valueOf(true));
				}

				destino_local = listaParametrosRedireccion_local.concatenarParametros();
			} else {
				destino_local = "javascript: mensajeAutorizacionInclusion();";
			}

			eventosBoton_local = "";
			if (!mc.sonCadenasIguales(destino_local, "javascript: mensajeAutorizacionInclusion();")) {
				eventosBoton_local = "onClick=\"mostrarMensajeProcesandoInformacion();\" ";
			}
			boton_local = new Boton("incluir", false, eventosBoton_local, "Incluir Registro", destino_local, 0, false);
			boton_local.setWithCustomImages(true);

			filaDescripcionCampos_local = mc.concatenarCadena(filaDescripcionCampos_local, getGeneradorComponentesHtml()
					.crearCeldaEncabezadoAncho(getGeneradorComponentesHtml().insertarBoton(boton_local, 0), 30));

			iterator_local = pListaGrupoInformacion.iterator();
			while (iterator_local.hasNext()) {
				grupoInformacion_local = (GrupoInformacion) iterator_local.next();
				if (grupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
					listaCampo_local = ms_local.obtenerMotorAplicacion()
							.obtenerListaCamposVisiblesGrupoInformacionPorAncho(
									grupoInformacion_local.getIdGrupoInformacion());

					if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
						iteratorCampos_local = listaCampo_local.iterator();
						while (iteratorCampos_local.hasNext()) {
							campo_local = (Campo) iteratorCampos_local.next();
							if (campo_local.esTipoDatoArchivo() && !campo_local.esImagen()) {
								anchoColumna_local = 57;
							} else {
								anchoColumna_local = campo_local.getAnchoColumna();
							}
							if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
								etiquetaCampo_local = campo_local.getEtiquetaCampo();
								if (grupoInformacion_local.esGrupoInformacionMultiple()) {
									filaDescripcionCampos_local = mc.concatenarCadena(filaDescripcionCampos_local,
											getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
													mc.darFormatoTitulo(etiquetaCampo_local), anchoColumna_local));

									filaDescripcionCampos_local = mc.concatenarCadena(filaDescripcionCampos_local,
											getGeneradorComponentesHtml().crearCeldaEncabezadoAncho("", 18));

									continue;
								}
								filaDescripcionCampos_local = mc.concatenarCadena(filaDescripcionCampos_local,
										getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
												mc.darFormatoTitulo(etiquetaCampo_local), anchoColumna_local));
							}
						}
					}
				}
			}

			filaDescripcionCampos_local = mc.concatenarCadena(filaDescripcionCampos_local,
					getGeneradorComponentesHtml().cerrarFilaTabla());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			etiquetaCampo_local = null;
			iterator_local = null;
			iteratorCampos_local = null;
			grupoInformacion_local = null;
			campo_local = null;
			listaCampo_local = null;
			listaParametrosRedireccion_local = null;
			destino_local = null;
			boton_local = null;
			eventosBoton_local = null;
		}
		return filaDescripcionCampos_local;
	}

	private String dibujarCeldasObtenerResultadoConsultaPrincipal(Aplicacion pAplicacion,
			ListaGrupoInformacion pListaGrupoInformacion) {
		String celdasObtenerResultadoConsulta_local = "";

		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return celdasObtenerResultadoConsulta_local;
		}
		if (pListaGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return celdasObtenerResultadoConsulta_local;
		}

		try {
			celdasObtenerResultadoConsulta_local = mc.concatenarCadena(celdasObtenerResultadoConsulta_local,
					dibujarFilaDescripcionCampos(pListaGrupoInformacion));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return celdasObtenerResultadoConsulta_local;
	}

	private String dibujarDatosGrupoInformacionNoMultiple(GrupoInformacion pGrupoInformacion,
			String pNombreGrupoInformacionPrincipal, int pValorLlavePrimaria, boolean pResaltarRegistro) {
		String datosGrupoInformacionNoMultiple_local = "";
		boolean esDocumento_local = false;
		boolean esCampoParrafo_local = false;
		String alineacionContenido_local = null;
		String destinoVinculoModificar_local = null;
		String consultaSQLGrupoInformacion_local = null;
		String nombreCampo_local = null;
		String tipoDato_local = null;
		int anchoColumna_local = 0;
		String valorCampo_local = null;
		Tabla tabla_local = null;
		ListaCampo listaCampo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ResultSet resultSet_local = null;
		Iterator iterator_local = null;
		Campo campo_local = null;
		Campo campoEnlazado_local = null;
		ManejadorSesion ms_local;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionNoMultiple_local;
		}
		if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionNoMultiple_local;
		}

		try {
			ms_local = getManejadorSesion();
			esDocumento_local = getAdministradorBaseDatosSisnet().verificarGrupoInformacionContieneCampoDocumento(
					ms_local.obtenerMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(
							pGrupoInformacion.getAplicacion().getIdAplicacion()));

			alineacionContenido_local = "left";
			listaCampo_local = ms_local.obtenerMotorAplicacion()
					.obtenerListaCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), true);

			consultaSQLGrupoInformacion_local = ca.conformarConsultaSQLListaCamposGrupoInformacionNoMultiple(
					pNombreGrupoInformacionPrincipal, pValorLlavePrimaria, listaCampo_local);

			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(87));

			listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pValorLlavePrimaria));

			if (esDocumento_local) {
				listaParametrosRedireccion_local.adicionar("esdocumento", String.valueOf(true));
			}

			destinoVinculoModificar_local = listaParametrosRedireccion_local.concatenarParametros();

			resultSet_local = getResultadoConsultaSQL()
					.obtenerResultadoConsultaAplicacion(consultaSQLGrupoInformacion_local);
			if (resultSet_local == ConstantesGeneral.VALOR_NULO) {
				return datosGrupoInformacionNoMultiple_local;
			}

			while (resultSet_local.next()) {
				iterator_local = listaCampo_local.iterator();
				while (iterator_local.hasNext()) {
					campo_local = (Campo) iterator_local.next();
					campoEnlazado_local = null;
					if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
						nombreCampo_local = campo_local.getNombreCampo();
						tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
						esCampoParrafo_local = campo_local.esTipoDatoParrafo();
						anchoColumna_local = campo_local.getAnchoColumna();
						alineacionContenido_local = campo_local.obtenerAlineacionContenidoCelda();
						if (!campo_local.esTipoDatoDocumento() && !campo_local.esTipoDatoArchivo()) {
							valorCampo_local = "";
							if (resultSet_local.getObject(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
								valorCampo_local = resultSet_local.getObject(nombreCampo_local).toString();
								if (campo_local.esCampoEnlazado() && mc.esCadenaNumerica(valorCampo_local, true)) {

									campoEnlazado_local = ms_local.obtenerMotorAplicacion()
											.obtenerPrimerCampoValorUnicoAplicacion(
													campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion());

									valorCampo_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
											.obtenerValorCampoEnlazado(campo_local.getEnlaceCampo().getEnlazado(),
													Integer.parseInt(valorCampo_local));
								}

								if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO) {
									if (mc.esCadenaNumerica(valorCampo_local,
											campoEnlazado_local.esTipoDatoNumeroEntero())) {
										campoEnlazado_local.setValorCampo(valorCampo_local);
										valorCampo_local = campoEnlazado_local.aplicarFormatoNumeros();
										campoEnlazado_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
										alineacionContenido_local = "right";
									} else {
										alineacionContenido_local = "left";
									}

								} else if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
									valorCampo_local = mc.completarCadena(valorCampo_local, false, '0',
											campo_local.getFormatoCampo().getLongitudCampo());

								} else if (mc.esCadenaNumerica(valorCampo_local,
										campo_local.esTipoDatoNumeroEntero())) {
									campo_local.setValorCampo(valorCampo_local);
									valorCampo_local = campo_local.aplicarFormatoNumeros();
									campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
								}

								if (mc.sonCadenasIguales(tipoDato_local, "H")) {
									valorCampo_local = ap.obtenerHoraConFormato("H24", valorCampo_local);
								}
							}
						}

						if (campo_local.getRestriccionCampo().esLlaveForanea()) {
							tabla_local = getAdministradorBaseDatosSisnet()
									.obtenerTablaPorId(Integer.parseInt(tipoDato_local));
							if (mc.esCadenaNumerica(valorCampo_local, true)) {
								valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(
										tabla_local.getNombreTabla(), Integer.parseInt(valorCampo_local));
							}

							datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionNoMultiple_local,
									getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
											alineacionContenido_local, destinoVinculoModificar_local,
											anchoColumna_local, pResaltarRegistro,
											"onClick=\"mostrarMensajeProcesandoInformacion();\" ",
											esCampoParrafo_local));

							continue;
						}

						if (campo_local.esTipoDatoArchivo()) {
							if (campo_local.esImagen()) {
								valorCampo_local = descargarArchivo(pValorLlavePrimaria, campo_local);
								if (!mc.esCadenaVacia(valorCampo_local)) {
									datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
											datosGrupoInformacionNoMultiple_local,
											getGeneradorComponentesHtml().crearCeldaHipervinculoImagen(valorCampo_local,
													"center", destinoVinculoModificar_local, anchoColumna_local,
													campo_local.getAltoImagenPantallaPresentacion(), pResaltarRegistro,
													"onClick=\"mostrarMensajeProcesandoInformacion();\" ", ""));

									continue;
								}

								datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
										datosGrupoInformacionNoMultiple_local,
										getGeneradorComponentesHtml().crearCeldaHipervinculo("",
												alineacionContenido_local, destinoVinculoModificar_local,
												anchoColumna_local, pResaltarRegistro, "", esCampoParrafo_local));

								continue;
							}

							datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionNoMultiple_local,
									insertarCeldaDescargarArchivo(campo_local, pValorLlavePrimaria, pResaltarRegistro));

							continue;
						}
						datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
								datosGrupoInformacionNoMultiple_local,
								getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
										alineacionContenido_local, destinoVinculoModificar_local, anchoColumna_local,
										pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
										esCampoParrafo_local));

					}

				}

			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			tabla_local = null;
			campo_local = null;
			tipoDato_local = null;
			iterator_local = null;
			resultSet_local = null;
			listaCampo_local = null;
			valorCampo_local = null;
			nombreCampo_local = null;
			campoEnlazado_local = null;
			alineacionContenido_local = null;
			destinoVinculoModificar_local = null;
			listaParametrosRedireccion_local = null;
			consultaSQLGrupoInformacion_local = null;
		}
		return datosGrupoInformacionNoMultiple_local;
	}

	private String dibujarDatosGrupoInformacionNoMultipleNew(GrupoInformacion pGrupoInformacion,
			String pNombreGrupoInformacionPrincipal, int pValorLlavePrimaria, boolean pResaltarRegistro,
			boolean esDocumento_local, ListaCampo pListaCamposParaMostrar) {
		String datosGrupoInformacionNoMultiple_local = "";
		// boolean esDocumento_local = false;
		boolean esCampoParrafo_local = false;
		String alineacionContenido_local = null;
		String destinoVinculoModificar_local = null;
		String consultaSQLGrupoInformacion_local = null;
		String nombreCampo_local = null;
		String tipoDato_local = null;
		int anchoColumna_local = 0;
		String valorCampo_local = null;
		Tabla tabla_local = null;

		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ResultSet resultSet_local = null;
		Iterator iterator_local = null;
		Campo campo_local = null;
		Campo campoEnlazado_local = null;
		ManejadorSesion ms_local;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionNoMultiple_local;
		}
		if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionNoMultiple_local;
		}

		try {
			ms_local = getManejadorSesion();
			/*
			 * esDocumento_local = getAdministradorBaseDatosSisnet().
			 * verificarGrupoInformacionContieneCampoDocumento(
			 * getManejadorSesion().obtenerMotorAplicacion().
			 * obtenerGrupoInformacionPrincipalAplicacion(
			 * pGrupoInformacion.getAplicacion().getIdAplicacion()));
			 */

			alineacionContenido_local = "left";

			consultaSQLGrupoInformacion_local = ca.conformarConsultaSQLListaCamposGrupoInformacionNoMultiple(
					pNombreGrupoInformacionPrincipal, pValorLlavePrimaria, pListaCamposParaMostrar);

			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(87));

			listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pValorLlavePrimaria));

			if (esDocumento_local) {
				listaParametrosRedireccion_local.adicionar("esdocumento", String.valueOf(true));
			}

			destinoVinculoModificar_local = listaParametrosRedireccion_local.concatenarParametros();

			resultSet_local = getResultadoConsultaSQL()
					.obtenerResultadoConsultaAplicacion(consultaSQLGrupoInformacion_local);
			if (resultSet_local == ConstantesGeneral.VALOR_NULO) {
				return datosGrupoInformacionNoMultiple_local;
			}

			while (resultSet_local.next()) {
				iterator_local = pListaCamposParaMostrar.iterator();
				while (iterator_local.hasNext()) {
					campo_local = (Campo) iterator_local.next();
					campoEnlazado_local = null;
					if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
						nombreCampo_local = campo_local.getNombreCampo();
						tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
						esCampoParrafo_local = campo_local.esTipoDatoParrafo();
						anchoColumna_local = campo_local.getAnchoColumna();
						alineacionContenido_local = campo_local.obtenerAlineacionContenidoCelda();
						if (!campo_local.esTipoDatoDocumento() && !campo_local.esTipoDatoArchivo()) {
							valorCampo_local = "";
							if (resultSet_local.getObject(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
								valorCampo_local = resultSet_local.getObject(nombreCampo_local).toString();
								if (campo_local.esCampoEnlazado() && mc.esCadenaNumerica(valorCampo_local, true)) {

									campoEnlazado_local = ms_local.obtenerMotorAplicacion()
											.obtenerPrimerCampoValorUnicoAplicacion(
													campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion());

									valorCampo_local = getValorEnlazado(valorCampo_local, campo_local);
								}

								if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO) {
									if (mc.esCadenaNumerica(valorCampo_local,
											campoEnlazado_local.esTipoDatoNumeroEntero())) {
										campoEnlazado_local.setValorCampo(valorCampo_local);
										valorCampo_local = campoEnlazado_local.aplicarFormatoNumeros();
										campoEnlazado_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
										alineacionContenido_local = "right";
									} else {
										alineacionContenido_local = "left";
									}

								} else if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
									valorCampo_local = mc.completarCadena(valorCampo_local, false, '0',
											campo_local.getFormatoCampo().getLongitudCampo());

								} else if (mc.esCadenaNumerica(valorCampo_local,
										campo_local.esTipoDatoNumeroEntero())) {
									campo_local.setValorCampo(valorCampo_local);
									valorCampo_local = campo_local.aplicarFormatoNumeros();
									campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
								}

								if (mc.sonCadenasIguales(tipoDato_local, "H")) {
									valorCampo_local = ap.obtenerHoraConFormato("H24", valorCampo_local);
								}
							}
						}

						if (campo_local.getRestriccionCampo().esLlaveForanea()) {
							valorCampo_local = getExternalTableValue(tipoDato_local, valorCampo_local);

							datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionNoMultiple_local,
									getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
											alineacionContenido_local, destinoVinculoModificar_local,
											anchoColumna_local, pResaltarRegistro,
											"onClick=\"mostrarMensajeProcesandoInformacion();\" ",
											esCampoParrafo_local));

							continue;
						}

						if (campo_local.esTipoDatoArchivo()) {
							if (campo_local.esImagen()) {
								valorCampo_local = descargarArchivo(pValorLlavePrimaria, campo_local);
								if (!mc.esCadenaVacia(valorCampo_local)) {
									datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
											datosGrupoInformacionNoMultiple_local,
											getGeneradorComponentesHtml().crearCeldaHipervinculoImagen(valorCampo_local,
													"center", destinoVinculoModificar_local, anchoColumna_local,
													campo_local.getAltoImagenPantallaPresentacion(), pResaltarRegistro,
													"onClick=\"mostrarMensajeProcesandoInformacion();\" ", ""));

									continue;
								}

								datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
										datosGrupoInformacionNoMultiple_local,
										getGeneradorComponentesHtml().crearCeldaHipervinculo("",
												alineacionContenido_local, destinoVinculoModificar_local,
												anchoColumna_local, pResaltarRegistro, "", esCampoParrafo_local));

								continue;
							}

							datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionNoMultiple_local,
									insertarCeldaDescargarArchivo(campo_local, pValorLlavePrimaria, pResaltarRegistro));

							continue;
						}
						datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
								datosGrupoInformacionNoMultiple_local,
								getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
										alineacionContenido_local, destinoVinculoModificar_local, anchoColumna_local,
										pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
										esCampoParrafo_local));

					}

				}

			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			tabla_local = null;
			campo_local = null;
			tipoDato_local = null;
			iterator_local = null;
			resultSet_local = null;
			valorCampo_local = null;
			nombreCampo_local = null;
			campoEnlazado_local = null;
			alineacionContenido_local = null;
			destinoVinculoModificar_local = null;
			listaParametrosRedireccion_local = null;
			consultaSQLGrupoInformacion_local = null;
		}
		return datosGrupoInformacionNoMultiple_local;
	}

	private String dibujarDatosGrupoInformacionNoMultipleOptimized(GrupoInformacion pGrupoInformacion,
			String pNombreGrupoInformacionPrincipal, int pValorLlavePrimaria, boolean pResaltarRegistro,
			boolean esDocumento_local, ListaCampo pListaCamposParaMostrar, List<HashMap> groupData) {
		String datosGrupoInformacionNoMultiple_local = "";
		// boolean esDocumento_local = false;
		boolean esCampoParrafo_local = false;
		String alineacionContenido_local = null;
		String destinoVinculoModificar_local = null;
		// String consultaSQLGrupoInformacion_local = null;
		String nombreCampo_local = null;
		String tipoDato_local = null;
		int anchoColumna_local = 0;
		String valorCampo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Iterator iterator_local = null;
		Campo campo_local = null;
		Campo campoEnlazado_local = null;
		ManejadorSesion ms_local;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionNoMultiple_local;
		}
		if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionNoMultiple_local;
		}

		try {
			ms_local = getManejadorSesion();
			/*
			 * esDocumento_local = getAdministradorBaseDatosSisnet().
			 * verificarGrupoInformacionContieneCampoDocumento(
			 * getManejadorSesion().obtenerMotorAplicacion().
			 * obtenerGrupoInformacionPrincipalAplicacion(
			 * pGrupoInformacion.getAplicacion().getIdAplicacion()));
			 */

			alineacionContenido_local = "left";

			// List<List> groupData = (List<List>)
			// fullGroupData.get(pGrupoInformacion.getIdGrupoInformacion());
			/*
			 * consultaSQLGrupoInformacion_local =
			 * ca.conformarConsultaSQLListaCamposGrupoInformacionNoMultiple(
			 * pNombreGrupoInformacionPrincipal, pValorLlavePrimaria,
			 * pListaCamposParaMostrar);
			 */
			String nombrellave = ap.conformarNombreCampoLlavePrimaria(pNombreGrupoInformacionPrincipal);
			int referenceKey;
			HashMap hmReference = null;
			// for (List<HashMap> ls : groupData) {
			for (HashMap hm : groupData) {
				referenceKey = Integer.parseInt(hm.get(nombrellave).toString());
				if (referenceKey == pValorLlavePrimaria) {
					hmReference = hm;
					break;
				}
			}
			// }

			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(87));

			listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pValorLlavePrimaria));

			if (esDocumento_local) {
				listaParametrosRedireccion_local.adicionar("esdocumento", String.valueOf(true));
			}

			destinoVinculoModificar_local = listaParametrosRedireccion_local.concatenarParametros();

			if (hmReference == ConstantesGeneral.VALOR_NULO) {
				return datosGrupoInformacionNoMultiple_local;
			}

			iterator_local = pListaCamposParaMostrar.iterator();
			while (iterator_local.hasNext()) {
				campo_local = (Campo) iterator_local.next();
				campoEnlazado_local = null;
				if (!getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
					continue;
				}
				nombreCampo_local = campo_local.getNombreCampo().toLowerCase();
				tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
				esCampoParrafo_local = campo_local.esTipoDatoParrafo();
				anchoColumna_local = campo_local.getAnchoColumna();
				alineacionContenido_local = campo_local.obtenerAlineacionContenidoCelda();
				if (!campo_local.esTipoDatoDocumento() && !campo_local.esTipoDatoArchivo()) {
					valorCampo_local = "";
					if (hmReference.get(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
						valorCampo_local = hmReference.get(nombreCampo_local).toString();
						if (campo_local.esCampoEnlazado() && mc.esCadenaNumerica(valorCampo_local, true)) {

							campoEnlazado_local = ms_local.obtenerMotorAplicacion()
									.obtenerPrimerCampoValorUnicoAplicacion(
											campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion());
							// anterior version de obtener campo enlazado
							/*
							 * valorCampo_local =
							 * getManejadorInformacionRecalculable().
							 * getManejadorCampoEnlazado()
							 * .obtenerValorCampoEnlazado(campo_local.
							 * getEnlaceCampo().getEnlazado(),
							 * Integer.parseInt(valorCampo_local));
							 */
							valorCampo_local = getValorEnlazado(valorCampo_local, campo_local);
						}

						if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO) {
							if (mc.esCadenaNumerica(valorCampo_local, campoEnlazado_local.esTipoDatoNumeroEntero())) {
								campoEnlazado_local.setValorCampo(valorCampo_local);
								valorCampo_local = campoEnlazado_local.aplicarFormatoNumeros();
								campoEnlazado_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
								alineacionContenido_local = "right";
							} else {
								alineacionContenido_local = "left";
							}

						} else if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
							valorCampo_local = mc.completarCadena(valorCampo_local, false, '0',
									campo_local.getFormatoCampo().getLongitudCampo());

						} else if (mc.esCadenaNumerica(valorCampo_local, campo_local.esTipoDatoNumeroEntero())) {
							campo_local.setValorCampo(valorCampo_local);
							valorCampo_local = campo_local.aplicarFormatoNumeros();
							campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
						}

						if (mc.sonCadenasIguales(tipoDato_local, "H")) {
							valorCampo_local = ap.obtenerHoraConFormato("H24", valorCampo_local);
						}
					}
				}

				if (campo_local.getRestriccionCampo().esLlaveForanea()) {
					valorCampo_local = this.getExternalTableValue(tipoDato_local, valorCampo_local);

					datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(datosGrupoInformacionNoMultiple_local,
							getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
									alineacionContenido_local, destinoVinculoModificar_local, anchoColumna_local,
									pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
									esCampoParrafo_local));

					continue;
				}

				if (campo_local.esTipoDatoArchivo()) {
					if (campo_local.esImagen()) {
						valorCampo_local = descargarArchivo(pValorLlavePrimaria, campo_local);
						if (!mc.esCadenaVacia(valorCampo_local)) {
							datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionNoMultiple_local,
									getGeneradorComponentesHtml().crearCeldaHipervinculoImagen(valorCampo_local,
											"center", destinoVinculoModificar_local, anchoColumna_local,
											campo_local.getAltoImagenPantallaPresentacion(), pResaltarRegistro,
											"onClick=\"mostrarMensajeProcesandoInformacion();\" ", ""));

							continue;
						}

						datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(
								datosGrupoInformacionNoMultiple_local,
								getGeneradorComponentesHtml().crearCeldaHipervinculo("", alineacionContenido_local,
										destinoVinculoModificar_local, anchoColumna_local, pResaltarRegistro, "",
										esCampoParrafo_local));

						continue;
					}

					datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(datosGrupoInformacionNoMultiple_local,
							insertarCeldaDescargarArchivo(campo_local, pValorLlavePrimaria, pResaltarRegistro));

					continue;
				}
				datosGrupoInformacionNoMultiple_local = mc.concatenarCadena(datosGrupoInformacionNoMultiple_local,
						getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
								alineacionContenido_local, destinoVinculoModificar_local, anchoColumna_local,
								pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
								esCampoParrafo_local));

			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			campo_local = null;
			tipoDato_local = null;
			iterator_local = null;
			// resultSet_local = null;
			valorCampo_local = null;
			nombreCampo_local = null;
			campoEnlazado_local = null;
			alineacionContenido_local = null;
			destinoVinculoModificar_local = null;
			listaParametrosRedireccion_local = null;
			// consultaSQLGrupoInformacion_local = null;
		}
		return datosGrupoInformacionNoMultiple_local;
	}

	private String getValorEnlazado(String valorCampo_local, Campo campo_local) {
		// coco
		Aplicacion aplicacion = campo_local.getEnlaceCampo().getEnlazado();
		if (this.valuesOfRelatedTable.containsKey(aplicacion)
				&& this.valuesOfRelatedTable.get(aplicacion).containsKey(valorCampo_local)) {
			valorCampo_local = this.valuesOfRelatedTable.get(aplicacion).get(valorCampo_local);

		} else {
			String valorCampo_localReferencia = valorCampo_local;
			valorCampo_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
					.obtenerValorCampoEnlazado(aplicacion, Integer.parseInt(valorCampo_local));

			if (!this.valuesOfRelatedTable.containsKey(aplicacion)) {
				HashMap<String, String> data = new HashMap<String, String>();
				data.put(valorCampo_localReferencia, valorCampo_local);
				this.valuesOfRelatedTable.put(aplicacion, data);
			} else {
				HashMap<String, String> data = this.valuesOfRelatedTable.get(aplicacion);
				data.put(valorCampo_localReferencia, valorCampo_local);
				this.valuesOfRelatedTable.put(aplicacion, data);
			}

		}

		return valorCampo_local;

	}

	private String getExternalTableValue(String tipoDato_local, String valorCampo_local) {
		if (mc.esCadenaNumerica(valorCampo_local, true)) {

			if (this.valuesOfExternalTable.containsKey(tipoDato_local)
					&& this.valuesOfExternalTable.get(tipoDato_local).containsKey(valorCampo_local)) {
				valorCampo_local = this.valuesOfExternalTable.get(tipoDato_local).get(valorCampo_local);

			} else {

				Tabla tabla_local;
				int tableIDLocal = Integer.parseInt(tipoDato_local);
				tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(tableIDLocal);
				String valorCampo_localReferencia = valorCampo_local;
				valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(tabla_local.getNombreTabla(),
						Integer.parseInt(valorCampo_localReferencia));

				if (!this.valuesOfExternalTable.containsKey(tipoDato_local)) {
					HashMap<String, String> data = new HashMap<String, String>();
					data.put(valorCampo_localReferencia, valorCampo_local);
					this.valuesOfExternalTable.put(tipoDato_local, data);
				} else {
					HashMap<String, String> data = this.valuesOfExternalTable.get(tipoDato_local);
					data.put(valorCampo_localReferencia, valorCampo_local);
					this.valuesOfExternalTable.put(tipoDato_local, data);
				}

			}

		}
		return valorCampo_local;
	}

	private String dibujarDatosGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion,
			String pNombreGrupoInformacionPrincipal, int pValorLlavePrimaria, boolean pResaltarRegistro) {
		String datosGrupoInformacionMultiple_local = "";
		int idAplicacion_local = -1;
		boolean existenRegistrosGrupo_local = false;
		boolean esCampoDocumento_local = false;
		boolean esCampoArchivo_local = false;
		boolean esCampoParrafo_local = false;
		String nombreGrupoInformacion_local = null;
		String alineacionContenido_local = null;
		String nombreLlaveGrupoInformacion_local = null;
		String nombreLlavePrimariaPrincipal_local = null;
		String consultaSQLGrupoInformacion_local = null;
		String destinoVinculoModificar_local = null;
		int anchoColumna_local = 0;
		String nombreCampo_local = null;
		String tipoDato_local = null;
		String valorCampo_local = null;
		ManejadorConsultaSQL manejadorConsultaSQL_local = null;
		ListaCampo listaCampo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ResultSet resultSet_local = null;
		Iterator iterator_local = null;
		Campo campo_local = null;
		Campo campoEnlazado_local = null;
		int valorLlavePrimariaGrupoInformacion_local = -1;
		ManejadorSesion ms_local;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionMultiple_local;
		}
		if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacionMultiple_local;
		}

		try {
			ms_local = getManejadorSesion();
			nombreGrupoInformacion_local = pGrupoInformacion.getNombreGrupoInformacion();
			alineacionContenido_local = "left";
			if (nombreGrupoInformacion_local == ConstantesGeneral.VALOR_NULO) {
				return datosGrupoInformacionMultiple_local;
			}

			idAplicacion_local = pGrupoInformacion.getAplicacion().getIdAplicacion();
			listaCampo_local = ms_local.obtenerMotorAplicacion()
					.obtenerListaCamposVisiblesGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion(), true);

			if (listaCampo_local.contarElementos() <= 0) {
				return datosGrupoInformacionMultiple_local;
			}
			nombreLlaveGrupoInformacion_local = getAdministradorBaseDatosSisnet()
					.obtenerNombreLlavePrimariaGrupoInformacion(pGrupoInformacion, false);

			nombreLlavePrimariaPrincipal_local = getAdministradorBaseDatosSisnet()
					.obtenerNombreLlavePrimariaGrupoInformacion(ms_local.obtenerMotorAplicacion()
							.obtenerGrupoInformacionPrincipalAplicacion(idAplicacion_local), false);

			consultaSQLGrupoInformacion_local = ca.conformarConsultaSQLListaCamposGrupoInformacionMultiple(
					nombreGrupoInformacion_local, nombreLlavePrimariaPrincipal_local, nombreLlaveGrupoInformacion_local,
					pValorLlavePrimaria, listaCampo_local);

			manejadorConsultaSQL_local = getResultadoConsultaSQL()
					.obtenerManejadorConsultaAplicacion(consultaSQLGrupoInformacion_local);

			resultSet_local = manejadorConsultaSQL_local.getResultSet();
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			if (resultSet_local == ConstantesGeneral.VALOR_NULO) {
				return datosGrupoInformacionMultiple_local;
			}
			existenRegistrosGrupo_local = (manejadorConsultaSQL_local.getNumeroRegistros() > 0);
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(87));

			listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pValorLlavePrimaria));

			destinoVinculoModificar_local = listaParametrosRedireccion_local.concatenarParametros();
			if (resultSet_local.next()) {
				valorLlavePrimariaGrupoInformacion_local = resultSet_local.getInt(nombreLlaveGrupoInformacion_local);
				iterator_local = listaCampo_local.iterator();
				while (iterator_local.hasNext()) {
					campo_local = (Campo) iterator_local.next();
					campoEnlazado_local = null;
					// si no tiene permiso de ver el campo continua
					if (!getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
						continue;
					}
					nombreCampo_local = campo_local.getNombreCampo();
					tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
					anchoColumna_local = campo_local.getAnchoColumna();
					valorCampo_local = "";
					esCampoDocumento_local = campo_local.esTipoDatoDocumento();
					esCampoArchivo_local = campo_local.esTipoDatoArchivo();
					esCampoParrafo_local = campo_local.esTipoDatoParrafo();

					alineacionContenido_local = campo_local.obtenerAlineacionContenidoCelda();

					if (!esCampoDocumento_local && !esCampoArchivo_local) {
						if (resultSet_local.getObject(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
							valorCampo_local = resultSet_local.getObject(nombreCampo_local).toString();
						}
						if (campo_local.getRestriccionCampo().esLlaveForanea()) {
							if (mc.sonCadenasIguales(valorCampo_local, "")) {
								valorCampo_local = String.valueOf(-1);
							}
							valorCampo_local = getExternalTableValue(tipoDato_local, valorCampo_local);
						} else if (campo_local.esCampoEnlazado() && !mc.esCadenaVacia(valorCampo_local)) {
							campoEnlazado_local = ms_local.obtenerMotorAplicacion()
									.obtenerPrimerCampoValorUnicoAplicacion(
											campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion());

							valorCampo_local = getValorEnlazado(valorCampo_local, campo_local);
						}

						if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO) {
							if (mc.esCadenaNumerica(valorCampo_local, campoEnlazado_local.esTipoDatoNumeroEntero())) {
								campoEnlazado_local.setValorCampo(valorCampo_local);
								valorCampo_local = campoEnlazado_local.aplicarFormatoNumeros();
								campoEnlazado_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
								alineacionContenido_local = "right";
							} else {
								alineacionContenido_local = "left";
							}

						} else if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
							valorCampo_local = mc.completarCadena(valorCampo_local, false, '0',
									campo_local.getFormatoCampo().getLongitudCampo());

						} else if (mc.esCadenaNumerica(valorCampo_local, campo_local.esTipoDatoNumeroEntero())) {
							campo_local.setValorCampo(valorCampo_local);
							valorCampo_local = campo_local.aplicarFormatoNumeros();
							campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
						}
					}

					if (!esCampoDocumento_local && !esCampoArchivo_local) {
						datosGrupoInformacionMultiple_local = mc.concatenarCadena(datosGrupoInformacionMultiple_local,
								getGeneradorComponentesHtml().crearCeldaHipervinculo(valorCampo_local,
										alineacionContenido_local, destinoVinculoModificar_local, anchoColumna_local,
										pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
										esCampoParrafo_local));

						if (existenRegistrosGrupo_local) {
							listaParametrosRedireccion_local.borrarElementos();
							listaParametrosRedireccion_local.adicionar("accion", String.valueOf(63));

							listaParametrosRedireccion_local.adicionar("grupoinformacionactual",
									String.valueOf(pGrupoInformacion.getIdGrupoInformacion()));

							listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
									String.valueOf(pValorLlavePrimaria));

							datosGrupoInformacionMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionMultiple_local,
									getGeneradorComponentesHtml().insertarCeldaVerDetalles(
											listaParametrosRedireccion_local.concatenarParametros(),
											pResaltarRegistro));

							continue;
						}
						datosGrupoInformacionMultiple_local = mc.concatenarCadena(datosGrupoInformacionMultiple_local,
								getGeneradorComponentesHtml().crearCeldaHipervinculo("", "right", "", 18,
										pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
										esCampoParrafo_local));

						continue;
					}

					if (campo_local.esTipoDatoArchivo()) {
						if (campo_local.esImagen()) {
							valorCampo_local = descargarArchivo(valorLlavePrimariaGrupoInformacion_local, campo_local);
							if (!mc.esCadenaVacia(valorCampo_local)) {
								datosGrupoInformacionMultiple_local = mc.concatenarCadena(
										datosGrupoInformacionMultiple_local,
										getGeneradorComponentesHtml().crearCeldaHipervinculoImagen(valorCampo_local,
												"center", destinoVinculoModificar_local, anchoColumna_local,
												campo_local.getAltoImagenPantallaPresentacion(), pResaltarRegistro,
												"onClick=\"mostrarMensajeProcesandoInformacion();\" ", ""));

							} else {

								datosGrupoInformacionMultiple_local = mc
										.concatenarCadena(datosGrupoInformacionMultiple_local,
												getGeneradorComponentesHtml().crearCeldaHipervinculo("", "center", "",
														anchoColumna_local, pResaltarRegistro, "",
														esCampoParrafo_local));

							}

						} else {

							datosGrupoInformacionMultiple_local = mc.concatenarCadena(
									datosGrupoInformacionMultiple_local, insertarCeldaDescargarArchivo(campo_local,
											valorLlavePrimariaGrupoInformacion_local, pResaltarRegistro));
						}

						listaParametrosRedireccion_local.borrarElementos();
						listaParametrosRedireccion_local.adicionar("accion", String.valueOf(63));

						listaParametrosRedireccion_local.adicionar("grupoinformacionactual",
								String.valueOf(pGrupoInformacion.getIdGrupoInformacion()));

						listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
								String.valueOf(pValorLlavePrimaria));

						datosGrupoInformacionMultiple_local = mc.concatenarCadena(datosGrupoInformacionMultiple_local,
								getGeneradorComponentesHtml().insertarCeldaVerDetalles(
										listaParametrosRedireccion_local.concatenarParametros(), pResaltarRegistro));
					}

				}
			} else {

				iterator_local = listaCampo_local.iterator();
				while (iterator_local.hasNext()) {
					campo_local = (Campo) iterator_local.next();
					anchoColumna_local = campo_local.getAnchoColumna() + 18;
					datosGrupoInformacionMultiple_local = mc.concatenarCadena(datosGrupoInformacionMultiple_local,
							getGeneradorComponentesHtml().crearCeldaHipervinculo("", "right", "", anchoColumna_local,
									pResaltarRegistro, "onClick=\"mostrarMensajeProcesandoInformacion();\" ",
									esCampoParrafo_local));

					datosGrupoInformacionMultiple_local = mc.concatenarCadena(datosGrupoInformacionMultiple_local,
							getGeneradorComponentesHtml().crearCeldaHipervinculo("", "right", "", 18, pResaltarRegistro,
									"onClick=\"mostrarMensajeProcesandoInformacion();\" ", esCampoParrafo_local));
				}

			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			campo_local = null;
			tipoDato_local = null;
			iterator_local = null;
			resultSet_local = null;
			valorCampo_local = null;
			listaCampo_local = null;
			nombreCampo_local = null;
			campoEnlazado_local = null;
			alineacionContenido_local = null;
			manejadorConsultaSQL_local = null;
			nombreGrupoInformacion_local = null;
			destinoVinculoModificar_local = null;
			listaParametrosRedireccion_local = null;
			nombreLlaveGrupoInformacion_local = null;
			consultaSQLGrupoInformacion_local = null;
			nombreLlavePrimariaPrincipal_local = null;
		}
		return datosGrupoInformacionMultiple_local;
	}

	private int contarRegistrosConsultaPrincipal(Aplicacion pAplicacion, ListaGrupoInformacion pListaGrupoInformacion) {
		int contarRegistrosConsultaPrincipal_local = 0;
		int valorLlavePrimaria_local = -1;
		String nombreLlavePrimaria_local = null;
		String consultaSQLPrincipal_local = null;
		ResultSet resultSet_local = null;
		ListaCadenas listaCadenas_local = null;

		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return contarRegistrosConsultaPrincipal_local;
		}
		if (pListaGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return contarRegistrosConsultaPrincipal_local;
		}

		try {
			ManejadorInformacionRecalculable mir_local = getManejadorInformacionRecalculable();
			nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pAplicacion.getNombreAplicacion());
			consultaSQLPrincipal_local = conformarConsultaPrincipal(pAplicacion);
			if (mc.esCadenaVacia(consultaSQLPrincipal_local)) {
				return 0;
			}
			resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQLPrincipal_local);

			mir_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
			mir_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());

			if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
				listaCadenas_local = new ListaCadenas();
				while (resultSet_local.next()) {
					valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrimaria_local);

					if (!listaCadenas_local.verificarExistenciaCadena(String.valueOf(valorLlavePrimaria_local))) {
						contarRegistrosConsultaPrincipal_local++;
						listaCadenas_local.adicionar(String.valueOf(valorLlavePrimaria_local));
					}
				}
			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			resultSet_local = null;
			listaCadenas_local = null;
			nombreLlavePrimaria_local = null;
			consultaSQLPrincipal_local = null;
		}
		return contarRegistrosConsultaPrincipal_local;
	}

	private int obtenerNumeroPaginasNavegacion() {
		int numeroPaginasNavegacion_local = 1;
		int numeroRegistrosPagina_local = 0;

		try {
			numeroRegistrosPagina_local = getManejadorSesion().obtenerAplicacionActual().getNumeroRegistrosPagina();
			if (numeroRegistrosPagina_local == 0) {
				numeroPaginasNavegacion_local = 1;
			} else {
				numeroPaginasNavegacion_local = op
						.dividir(getNumeroRegistrosConsultaPrincipal(), numeroRegistrosPagina_local).intValue();
				if (getNumeroRegistrosConsultaPrincipal() > numeroPaginasNavegacion_local
						* numeroRegistrosPagina_local) {
					numeroPaginasNavegacion_local++;
				}
			}
			if (numeroPaginasNavegacion_local == 0) {
				numeroPaginasNavegacion_local = 1;
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return numeroPaginasNavegacion_local;
	}

	private String insertarImagenProcesandoInformacion(boolean pEsDocumento) {
		String insertarImagenProcesandoInformacion_local = "";
		int numeroDirectoriosAnteriores_local = 0;

		try {
			if (pEsDocumento) {
				numeroDirectoriosAnteriores_local = 2;
			}

			insertarImagenProcesandoInformacion_local = getGeneradorComponentesHtml().insertarImagenCentrada(
					"imagenprocesandoinformacion",
					mc.complementarDirectorio(numeroDirectoriosAnteriores_local) + "../imagenes/"
							+ CargadorPropiedades.getCargadorPropiedades()
									.obtenerValorPropiedadImagenes("IMAGEN_PROCESANDO_INFORMACION"),
					"", "", false, true);

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return insertarImagenProcesandoInformacion_local;
	}

	private String dibujarDatosConsultaPrincipalOld(Aplicacion pAplicacion, ListaGrupoInformacion pListaGrupoInformacion) {
		String datosConsultaPrincipal_local = "";
		int valorLlavePrimaria_local = -1;
		int numeroRegistrosConsulta_local = 0;
		boolean alternar_local = false;
		String nombreLlavePrimaria_local = null;
		String consultaSQLPrincipal_local = null;
		String destinoBorrar_local = null;
		ResultSet resultSet_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Iterator iterator_local = null;
		GrupoInformacion grupoInformacion_local = null;
		ListaCadenas listaCadenas_local = null;
		String condiciones_local = null;
		String nombreAplicacion = null;
		int numeroPaginaNavegacion_local = 0;
		int numeroRegistrosPagina_local = 0;
		int numeroPrimerRegistro_local = 0;
		int numeroUltimoRegistro_local = 0;
		ManejadorSesion ms_local;

		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return datosConsultaPrincipal_local;
		}
		if (pListaGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosConsultaPrincipal_local;
		}

		try {
			nombreAplicacion = pAplicacion.getNombreAplicacion();
			ms_local = getManejadorSesion();
			ManejadorInformacionRecalculable mir_local = getManejadorInformacionRecalculable();
			ManejadorPermisoUsuario mpu_local = getManejadorPermisoUsuario();
			GeneradorComponentesHtml gch_local = getGeneradorComponentesHtml();
			int idRegistroVisitado = ms_local.obtenerIdRegistroVisitado();
			int counter = this.contarRegistrosConsultaPrincipal(pAplicacion, pListaGrupoInformacion);
			this.setNumeroRegistrosConsultaPrincipal(counter);
			nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(nombreAplicacion);
			consultaSQLPrincipal_local = conformarConsultaPrincipal(pAplicacion);
			ms_local.actualizarConsultaPrincipal(consultaSQLPrincipal_local);

			if (mc.esCadenaVacia(consultaSQLPrincipal_local)) {
				return datosConsultaPrincipal_local;
			}

			resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQLPrincipal_local);
			mir_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
			mir_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());

			if (resultSet_local == ConstantesGeneral.VALOR_NULO) {
				return datosConsultaPrincipal_local;
			}
			numeroPaginaNavegacion_local = ms_local.obtenerNumeroPaginaNavegacion();
			if (numeroPaginaNavegacion_local > obtenerNumeroPaginasNavegacion()) {
				numeroPaginaNavegacion_local = obtenerNumeroPaginasNavegacion();
				ms_local.actualizarNumeroPaginaNavegacion(numeroPaginaNavegacion_local);
			}
			numeroRegistrosPagina_local = ms_local.obtenerAplicacionActual().getNumeroRegistrosPagina();
			numeroPrimerRegistro_local = (numeroPaginaNavegacion_local - 1) * numeroRegistrosPagina_local + 1;

			numeroUltimoRegistro_local = numeroPaginaNavegacion_local * numeroRegistrosPagina_local;
			if (numeroRegistrosPagina_local == 0) {
				numeroUltimoRegistro_local = this.getNumeroRegistrosConsultaPrincipal();
			}

			listaCadenas_local = new ListaCadenas();
			while (resultSet_local.next()) {
				valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrimaria_local);
				alternar_local = (idRegistroVisitado == valorLlavePrimaria_local);

				condiciones_local = ap.conformarNombreCampoLlavePrimaria(nombreAplicacion) + " = "
						+ valorLlavePrimaria_local;

				if (pAplicacion.esActualizarInformacionEnlazada()) {
					mir_local.actualizarInformacionRecalculableAplicacion(pAplicacion, -1, condiciones_local);
					mir_local.actualizarInformacionRecalculableAplicacion(pAplicacion, -1, condiciones_local);
				}

				numeroRegistrosConsulta_local++;
				if (!listaCadenas_local.verificarExistenciaCadena(String.valueOf(valorLlavePrimaria_local))
						&& numeroRegistrosConsulta_local >= numeroPrimerRegistro_local
						&& numeroRegistrosConsulta_local <= numeroUltimoRegistro_local) {
					if (mpu_local.verificarPermisoBorrarRegistrosAplicacion(pAplicacion)
							|| mpu_local.verificarExistenciaGrupoInformacionNoMultiplePermisoBorrar(
									pAplicacion.getIdAplicacion())) {

						listaCadenas_local.adicionar(String.valueOf(valorLlavePrimaria_local));
						listaParametrosRedireccion_local = new ListaParametrosRedireccion();
						listaParametrosRedireccion_local.adicionar("accion", String.valueOf(94));

						listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
								String.valueOf(valorLlavePrimaria_local));

						destinoBorrar_local = listaParametrosRedireccion_local.concatenarParametros();
					} else {
						destinoBorrar_local = "javascript: mensajeAutorizacionBorrar();";
					}
					datosConsultaPrincipal_local = mc.concatenarCadena(datosConsultaPrincipal_local,
							gch_local.abrirFilaTabla());

					datosConsultaPrincipal_local = mc.concatenarCadena(datosConsultaPrincipal_local,
							gch_local.insertarCeldaBorrarRegistro(destinoBorrar_local, 0, alternar_local,
									!mc.sonCadenasIguales(destinoBorrar_local,
											"javascript: mensajeAutorizacionBorrar();")));

					iterator_local = pListaGrupoInformacion.iterator();
					while (iterator_local.hasNext()) {
						grupoInformacion_local = (GrupoInformacion) iterator_local.next();

						if (mpu_local.verificarPermisoVerGrupoInformacion(grupoInformacion_local) || mpu_local
								.verificarExistenciaCampoPermisoVer(grupoInformacion_local.getIdGrupoInformacion())) {

							if (grupoInformacion_local.esGrupoInformacionMultiple()) {
								datosConsultaPrincipal_local += dibujarDatosGrupoInformacionMultiple(
										grupoInformacion_local, nombreAplicacion, valorLlavePrimaria_local,
										alternar_local);

								continue;
							}
							datosConsultaPrincipal_local += dibujarDatosGrupoInformacionNoMultiple(
									grupoInformacion_local, nombreAplicacion, valorLlavePrimaria_local, alternar_local);
						}
					}

					datosConsultaPrincipal_local += gch_local.cerrarFilaTabla();

					alternar_local = !alternar_local;
				}

			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			iterator_local = null;
			resultSet_local = null;
			listaCadenas_local = null;
			grupoInformacion_local = null;
			nombreLlavePrimaria_local = null;
			consultaSQLPrincipal_local = null;
			listaParametrosRedireccion_local = null;
			condiciones_local = null;
		}
		return datosConsultaPrincipal_local;
	}

	private String dibujarDatosConsultaPrincipal(Aplicacion pAplicacion,
			ListaGrupoInformacion pListaGrupoInformacion) {
		StringBuilder datosConsultaPrincipal_local = new StringBuilder();
		Hashtable<Integer, Boolean> contenedorEsDocumentoLocal = new Hashtable<Integer, Boolean>();
		Hashtable<Integer, ListaCampo> contenedorListaCampo = new Hashtable<Integer, ListaCampo>();

		int valorLlavePrimaria_local = -1;
		int numeroRegistrosConsulta_local = 0;
		boolean alternar_local = false;
		boolean esDocumento_local;
		String nombreLlavePrimaria_local = null;
		String consultaSQLPrincipal_local = null;
		String destinoBorrar_local = null;
		ResultSet resultSet_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Iterator iterator_local = null;
		GrupoInformacion grupoInformacion_local = null;
		ListaCadenas listaCadenas_local = null;
		String condiciones_local = null;
		String nombreAplicacion = null;
		String celdasDatos = null;
		int numeroPaginaNavegacion_local = 0;
		int numeroRegistrosPagina_local = 0;
		int numeroPrimerRegistro_local = 0;
		int numeroUltimoRegistro_local = 0;
		ManejadorSesion ms_local;
		ListaCampo listaCamposParaMostrar_local;

		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return datosConsultaPrincipal_local.toString();
		}
		if (pListaGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosConsultaPrincipal_local.toString();
		}

		try {
			nombreAplicacion = pAplicacion.getNombreAplicacion();
			ms_local = getManejadorSesion();
			ManejadorInformacionRecalculable mir_local = getManejadorInformacionRecalculable();
			ManejadorPermisoUsuario mpu_local = getManejadorPermisoUsuario();
			GeneradorComponentesHtml gch_local = getGeneradorComponentesHtml();
			int idRegistroVisitado = ms_local.obtenerIdRegistroVisitado();
			int counter = this.contarRegistrosConsultaPrincipal(pAplicacion, pListaGrupoInformacion);
			this.setNumeroRegistrosConsultaPrincipal(counter);
			nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(nombreAplicacion);
			consultaSQLPrincipal_local = conformarConsultaPrincipal(pAplicacion);
			ms_local.actualizarConsultaPrincipal(consultaSQLPrincipal_local);

			if (mc.esCadenaVacia(consultaSQLPrincipal_local)) {
				return datosConsultaPrincipal_local.toString();
			}

			resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQLPrincipal_local);
			mir_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
			mir_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());

			if (resultSet_local == ConstantesGeneral.VALOR_NULO) {
				return datosConsultaPrincipal_local.toString();
			}
			numeroPaginaNavegacion_local = ms_local.obtenerNumeroPaginaNavegacion();
			if (numeroPaginaNavegacion_local > obtenerNumeroPaginasNavegacion()) {
				numeroPaginaNavegacion_local = obtenerNumeroPaginasNavegacion();
				ms_local.actualizarNumeroPaginaNavegacion(numeroPaginaNavegacion_local);
			}
			numeroRegistrosPagina_local = ms_local.obtenerAplicacionActual().getNumeroRegistrosPagina();
			numeroPrimerRegistro_local = (numeroPaginaNavegacion_local - 1) * numeroRegistrosPagina_local + 1;

			numeroUltimoRegistro_local = numeroPaginaNavegacion_local * numeroRegistrosPagina_local;
			if (numeroRegistrosPagina_local == 0) {
				numeroUltimoRegistro_local = this.getNumeroRegistrosConsultaPrincipal();
			}

			// java.util.Arrays.asList(null)
			ArrayList<Integer> items = new ArrayList<Integer>();

			while (resultSet_local.next()) {
				valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrimaria_local);
				items.add(valorLlavePrimaria_local);
			}
			if (items.size() == 0) {
				return datosConsultaPrincipal_local.toString();
			}
			ArrayList<Integer> subItems = getIdsForPage(items, numeroPrimerRegistro_local, numeroUltimoRegistro_local);

			int breaker = pListaGrupoInformacion.size();
			if (breaker > 1) {
				breaker = 2;
			}

			iterator_local = pListaGrupoInformacion.iterator();
			Hashtable<Integer, List> fullGroupData = new Hashtable<Integer, List>();
			while (iterator_local.hasNext()) {
				grupoInformacion_local = (GrupoInformacion) iterator_local.next();
				// cache para manejar el listado de campos a mostrar
				if (contenedorListaCampo.containsKey(grupoInformacion_local.getIdGrupoInformacion())) {
					listaCamposParaMostrar_local = contenedorListaCampo
							.get(grupoInformacion_local.getIdGrupoInformacion());

				} else {
					listaCamposParaMostrar_local = ms_local.obtenerMotorAplicacion()
							.obtenerListaCamposVisiblesGrupoInformacion(grupoInformacion_local.getIdGrupoInformacion(),
									true);

					contenedorListaCampo.put(grupoInformacion_local.getIdGrupoInformacion(),
							listaCamposParaMostrar_local);
				}
				List fullData = this.getData(grupoInformacion_local, listaCamposParaMostrar_local, subItems);
				fullGroupData.put(grupoInformacion_local.getIdGrupoInformacion(), fullData);
			}

			listaCadenas_local = new ListaCadenas();
			// while (resultSet_local.next()) {
			for (Integer subValorLlavePrimaria_local : items) {
				// valorLlavePrimaria_local =
				// resultSet_local.getInt(nombreLlavePrimaria_local);
				valorLlavePrimaria_local = subValorLlavePrimaria_local.intValue();
				alternar_local = (idRegistroVisitado == valorLlavePrimaria_local);

				condiciones_local = ap.conformarNombreCampoLlavePrimaria(nombreAplicacion) + " = "
						+ valorLlavePrimaria_local;

				if (pAplicacion.esActualizarInformacionEnlazada()) {
					mir_local.actualizarInformacionRecalculableAplicacion(pAplicacion, -1, condiciones_local);
					mir_local.actualizarInformacionRecalculableAplicacion(pAplicacion, -1, condiciones_local);
				}

				numeroRegistrosConsulta_local++;
				// si se completa el numero de registros de la página se detiene
				// el listado
				if (numeroRegistrosConsulta_local > numeroUltimoRegistro_local) {
					break;
				}

				/*
				 * && numeroRegistrosConsulta_local >=
				 * numeroPrimerRegistro_local && numeroRegistrosConsulta_local
				 * <= numeroUltimoRegistro_local
				 */
				// Si el registro ya está desplegado no se tiene en cuenta
				if (listaCadenas_local.verificarExistenciaCadena(String.valueOf(valorLlavePrimaria_local))
						|| numeroRegistrosConsulta_local < numeroPrimerRegistro_local) {
					continue;
				}

				if (mpu_local.verificarPermisoBorrarRegistrosAplicacion(pAplicacion) || mpu_local
						.verificarExistenciaGrupoInformacionNoMultiplePermisoBorrar(pAplicacion.getIdAplicacion())) {

					listaCadenas_local.adicionar(String.valueOf(valorLlavePrimaria_local));
					listaParametrosRedireccion_local = new ListaParametrosRedireccion();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(94));

					listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
							String.valueOf(valorLlavePrimaria_local));

					destinoBorrar_local = listaParametrosRedireccion_local.concatenarParametros();
				} else {
					destinoBorrar_local = "javascript: mensajeAutorizacionBorrar();";
				}
				datosConsultaPrincipal_local.append(gch_local.abrirFilaTabla());

				datosConsultaPrincipal_local.append(gch_local.insertarCeldaBorrarRegistro(destinoBorrar_local, 0,
						alternar_local,
						!mc.sonCadenasIguales(destinoBorrar_local, "javascript: mensajeAutorizacionBorrar();")));

				iterator_local = pListaGrupoInformacion.iterator();
				breaker = pListaGrupoInformacion.size();
				if (breaker > 1) {
					breaker = 2;
				}
				while (iterator_local.hasNext()) {
					grupoInformacion_local = (GrupoInformacion) iterator_local.next();

					if (!mpu_local.verificarPermisoVerGrupoInformacion(grupoInformacion_local) && !mpu_local
							.verificarExistenciaCampoPermisoVer(grupoInformacion_local.getIdGrupoInformacion())) {
						continue;
					}
					if (grupoInformacion_local.esGrupoInformacionMultiple()) {
						datosConsultaPrincipal_local.append(dibujarDatosGrupoInformacionMultiple(grupoInformacion_local,
								nombreAplicacion, valorLlavePrimaria_local, alternar_local));

						continue;
					}

					// cache para determinar esDocumento_local
					if (contenedorEsDocumentoLocal.containsKey(grupoInformacion_local.getIdGrupoInformacion())) {
						esDocumento_local = contenedorEsDocumentoLocal
								.get(grupoInformacion_local.getIdGrupoInformacion());

					} else {
						esDocumento_local = getAdministradorBaseDatosSisnet()
								.verificarGrupoInformacionContieneCampoDocumento(
										ms_local.obtenerMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(
												grupoInformacion_local.getAplicacion().getIdAplicacion()));
						contenedorEsDocumentoLocal.put(grupoInformacion_local.getIdGrupoInformacion(),
								esDocumento_local);
					}

					// cache para manejar el listado de campos a mostrar
					if (contenedorListaCampo.containsKey(grupoInformacion_local.getIdGrupoInformacion())) {
						listaCamposParaMostrar_local = contenedorListaCampo
								.get(grupoInformacion_local.getIdGrupoInformacion());

					} else {
						listaCamposParaMostrar_local = ms_local.obtenerMotorAplicacion()
								.obtenerListaCamposVisiblesGrupoInformacion(
										grupoInformacion_local.getIdGrupoInformacion(), true);

						contenedorListaCampo.put(grupoInformacion_local.getIdGrupoInformacion(),
								listaCamposParaMostrar_local);
					}

					/*
					 * celdasDatos = dibujarDatosGrupoInformacionNoMultiple(
					 * grupoInformacion_local, nombreAplicacion,
					 * valorLlavePrimaria_local, alternar_local,
					 * esDocumento_local, listaCamposParaMostrar_local);
					 */
					List<HashMap> groupData = (List<HashMap>) fullGroupData
							.get(grupoInformacion_local.getIdGrupoInformacion());
					celdasDatos = dibujarDatosGrupoInformacionNoMultipleOptimized(grupoInformacion_local,
							nombreAplicacion, valorLlavePrimaria_local, alternar_local, esDocumento_local,
							listaCamposParaMostrar_local, groupData);
					datosConsultaPrincipal_local.append(celdasDatos);

				}

				datosConsultaPrincipal_local.append(gch_local.cerrarFilaTabla());

				alternar_local = !alternar_local;

			}

		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			iterator_local = null;
			resultSet_local = null;
			listaCadenas_local = null;
			grupoInformacion_local = null;
			nombreLlavePrimaria_local = null;
			consultaSQLPrincipal_local = null;
			listaParametrosRedireccion_local = null;
			condiciones_local = null;
			listaCamposParaMostrar_local = null;
			contenedorEsDocumentoLocal = null;
			contenedorListaCampo = null;

		}
		return datosConsultaPrincipal_local.toString();
	}

	/*
	 * GrupoInformacion pGrupoInformacion, String
	 * pNombreGrupoInformacionPrincipal, int pValorLlavePrimaria, boolean
	 * pResaltarRegistro, boolean esDocumento_local, pListaCamposParaMostrar
	 */
	private List getData(GrupoInformacion gr, ListaCampo pListaCamposParaMostrar,
			ArrayList<Integer> pitemsValorLlavePrimaria) {

		if(gr.esGrupoInformacionMultiple()){
			return new ArrayList();
		}
		String pNombreGrupoInformacionPrincipal = gr.getAplicacion().getNombreAplicacion();
		String consultaSQLGrupoInformacion_local = ca.conformarConsultaSQLListaCamposGrupoInformacionNoMultiple(
				pNombreGrupoInformacionPrincipal, pitemsValorLlavePrimaria, pListaCamposParaMostrar);

		ResultSet resultSet_local = getResultadoConsultaSQL()
				.obtenerResultadoConsultaAplicacion(consultaSQLGrupoInformacion_local);
		if (resultSet_local == ConstantesGeneral.VALOR_NULO) {
			return null;
		}
		List dataFull = null;
		try {
			dataFull = resultSetToArrayList(resultSet_local);
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			resultSet_local = null;
		}
		return dataFull;
	}

	public List resultSetToArrayList(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		ArrayList list = new ArrayList(10);
		while (rs.next()) {
			HashMap row = new HashMap(columns);
			for (int i = 1; i <= columns; ++i) {
				row.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));
			}
			list.add(row);
		}

		return list;
	}

	private ArrayList<Integer> getIdsForPage(ArrayList<Integer> items, int numeroPrimerRegistro_local,
			int numeroUltimoRegistro_local) {

		numeroPrimerRegistro_local--;
		ArrayList<Integer> subItems = new ArrayList<Integer>();
		if (items.size() == 0) {
			return subItems;
		}

		if (numeroUltimoRegistro_local > items.size()) {
			subItems = new ArrayList<Integer>(items.subList(numeroPrimerRegistro_local, items.size()));
		} else {
			subItems = new ArrayList<Integer>(items.subList(numeroPrimerRegistro_local, numeroUltimoRegistro_local));
		}

		return subItems;

	}

	private String dibujarTablaNavegacion() {
		String tablaNavegacion_local = "";
		Boton botonAnteriorPagina_local = null;
		Boton botonSiguientePagina_local = null;
		ListaParametrosRedireccion listaParametrosRedireccionPaginaAnterior_local = null;
		ListaParametrosRedireccion listaParametrosRedireccionPaginaSiguiente_local = null;

		try {
			tablaNavegacion_local = getGeneradorComponentesHtml().abrirTabla("tablaNavegacion", "450", "center");

			tablaNavegacion_local = mc.concatenarCadena(tablaNavegacion_local,
					getGeneradorComponentesHtml().abrirFilaTabla());

			tablaNavegacion_local = mc.concatenarCadena(tablaNavegacion_local,
					getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
							mc.concatenarCadena(String.valueOf(getNumeroRegistrosConsultaPrincipal()),
									" Registros Encontrados - P\u00e1gina "
											+ getManejadorSesion().obtenerNumeroPaginaNavegacion() + " de "
											+ obtenerNumeroPaginasNavegacion()),
							350));

			listaParametrosRedireccionPaginaAnterior_local = new ListaParametrosRedireccion();
			listaParametrosRedireccionPaginaAnterior_local.adicionar("accion", String.valueOf(47));

			botonAnteriorPagina_local = new Boton("anteriorpagina", false, "",
					ConstantesGeneral.const_DescripcionBotonAnteriorPagina,
					listaParametrosRedireccionPaginaAnterior_local.concatenarParametros(), 0, false);
			botonAnteriorPagina_local.setWithCustomImages(true);

			tablaNavegacion_local = mc.concatenarCadena(tablaNavegacion_local,
					getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
							getGeneradorComponentesHtml().insertarBoton(botonAnteriorPagina_local, 0), 51));

			listaParametrosRedireccionPaginaSiguiente_local = new ListaParametrosRedireccion();
			listaParametrosRedireccionPaginaSiguiente_local.adicionar("accion", String.valueOf(46));

			botonSiguientePagina_local = new Boton("siguientepagina", false, "",
					ConstantesGeneral.const_DescripcionBotonSiguientePagina,
					listaParametrosRedireccionPaginaSiguiente_local.concatenarParametros(), 0, false);
			botonSiguientePagina_local.setWithCustomImages(true);

			tablaNavegacion_local = mc.concatenarCadena(tablaNavegacion_local,
					getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
							getGeneradorComponentesHtml().insertarBoton(botonSiguientePagina_local, 0), 51));

			tablaNavegacion_local = mc.concatenarCadena(tablaNavegacion_local,
					getGeneradorComponentesHtml().cerrarFilaTabla());
			tablaNavegacion_local = mc.concatenarCadena(tablaNavegacion_local,
					getGeneradorComponentesHtml().cerrarTablaSinSalto());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			botonAnteriorPagina_local = null;
			botonSiguientePagina_local = null;
			listaParametrosRedireccionPaginaAnterior_local = null;
			listaParametrosRedireccionPaginaSiguiente_local = null;
		}

		return tablaNavegacion_local;
	}

	private String dibujarTablaResultadosConsultaPrincipal(Aplicacion pAplicacion) {
		String tablaResultadosConsulta_local = "";
		int anchoTabla_local = -1;
		ListaGrupoInformacion listaGrupoInformacion_local = null;

		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return tablaResultadosConsulta_local;
		}

		try {
			GeneradorComponentesHtml gch_local = getGeneradorComponentesHtml();
			listaGrupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaGruposInformacionAplicacionConsultaPrincipal(pAplicacion.getIdAplicacion());

			anchoTabla_local = calcularAnchoTablaConsultaPrincipal(listaGrupoInformacion_local);
			tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local,
					gch_local.insertarTituloTipo1("RESULTADO CONSULTA"));

			tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local,
					gch_local.abrirTabla("tablaResultadosConsulta", String.valueOf(anchoTabla_local), "center"));

			tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local,
					dibujarCeldasObtenerResultadoConsultaPrincipal(pAplicacion, listaGrupoInformacion_local));
			// indicates if query must be executed
			if (getManejadorSesion().ejecutarConsulta()) {
				tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local,
						dibujarDatosConsultaPrincipal(pAplicacion, listaGrupoInformacion_local));
			}

			tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local, gch_local.cerrarTabla());

			tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local,
					insertarImagenProcesandoInformacion(false));

			tablaResultadosConsulta_local = mc.concatenarCadena(tablaResultadosConsulta_local,
					dibujarTablaNavegacion());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaGrupoInformacion_local = null;
		}
		return tablaResultadosConsulta_local;
	}

	public String completarTablaVinculosAplicaciones(int pNumeroColumnas) {
		String tablaVinculoAplicaciones_local = "";
		int contador_local = 0;

		try {
			for (contador_local = 0; contador_local < pNumeroColumnas; contador_local++) {
				tablaVinculoAplicaciones_local = mc.concatenarCadena(tablaVinculoAplicaciones_local,
						getGeneradorComponentesHtml().crearCeldaAnchoTransparente("", "center", "20%"));

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return tablaVinculoAplicaciones_local;
	}

	private String dibujarTablaVinculosAplicaciones() {
		String tablaVinculosAplicaciones_local = "";
		int cantidadAplicaciones_local = -1;
		int numeroFilas_local = -1;
		int contador_local = -1;
		int i_local = 0;
		int numeroColumnasACompletar_local = 0;
		int numeroAplicacionesDeReferencia_local = 0;
		String destino_local = null;
		ListaAplicacion listaAplicacion_local = null;
		Iterator iterador_local = null;
		Aplicacion aplicacion_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		String eventoOnClick_local = null;

		try {
			listaAplicacion_local = getManejadorSesion().obtenerMotorAplicacion().obtenerListaAplicaciones(-1, true);

			iterador_local = listaAplicacion_local.iterator();
			tablaVinculosAplicaciones_local = getGeneradorComponentesHtml().abrirTablaMarca("TABLA", "100%", "center");

			cantidadAplicaciones_local = listaAplicacion_local.contarElementos();
			numeroFilas_local = cantidadAplicaciones_local / 5;
			if (cantidadAplicaciones_local % 5 > 0) {
				numeroFilas_local++;
			}
			numeroAplicacionesDeReferencia_local = numeroFilas_local * 5;
			numeroColumnasACompletar_local = numeroAplicacionesDeReferencia_local - cantidadAplicaciones_local;
			for (i_local = 0; i_local < numeroFilas_local; i_local++) {
				tablaVinculosAplicaciones_local = mc.concatenarCadena(tablaVinculosAplicaciones_local,
						getGeneradorComponentesHtml().abrirFilaTabla());

				contador_local = 0;
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				while (iterador_local.hasNext() && contador_local < 5) {
					eventoOnClick_local = "";
					aplicacion_local = (Aplicacion) iterador_local.next();
					if (getManejadorPermisoUsuario().verificarPermisoVerRegistrosAplicacion(aplicacion_local)) {
						listaParametrosRedireccion_local.borrarElementos();
						listaParametrosRedireccion_local.adicionar("accion", String.valueOf(1));

						listaParametrosRedireccion_local.adicionar("fldidaplicacion",
								String.valueOf(aplicacion_local.getIdAplicacion()));

						destino_local = listaParametrosRedireccion_local.concatenarParametros();
						eventoOnClick_local = "onClick=\"mostrarMensajeProcesandoInformacion();\" ";
					} else {
						destino_local = "javascript: mensajeAplicacionNoPermitida();";
					}
					tablaVinculosAplicaciones_local = mc.concatenarCadena(tablaVinculosAplicaciones_local,
							getGeneradorComponentesHtml()
									.crearCeldaAnchoMarca(getGeneradorComponentesHtml().insertarVinculoMarcaAplicacion(
											mc.darFormatoTitulo(aplicacion_local.getTituloAplicacion()), destino_local,
											eventoOnClick_local), "20%"));

					contador_local++;
				}
				if (!iterador_local.hasNext()) {
					tablaVinculosAplicaciones_local = mc.concatenarCadena(tablaVinculosAplicaciones_local,
							completarTablaVinculosAplicaciones(numeroColumnasACompletar_local));
				}

				tablaVinculosAplicaciones_local = mc.concatenarCadena(tablaVinculosAplicaciones_local,
						getGeneradorComponentesHtml().cerrarFilaTabla());
			}

			tablaVinculosAplicaciones_local = mc.concatenarCadena(tablaVinculosAplicaciones_local,
					getGeneradorComponentesHtml().cerrarTabla());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			destino_local = null;
			iterador_local = null;
			aplicacion_local = null;
			listaAplicacion_local = null;
			listaParametrosRedireccion_local = null;
			eventoOnClick_local = null;
		}
		return tablaVinculosAplicaciones_local;
	}

	public Pagina obtenerPaginaConsultaPrincipal() {
		Pagina pagina_local = null;
		ListaCampo listaCampo_local = null;
		Aplicacion aplicacion_local = null;
		ManejadorSesion ms_local = getManejadorSesion();
		try {
			if (ms_local.obtenerExistenEventosEnEjecucion()) {
				pagina_local = obtenerPaginaErrorEjecucionEventos();
				return pagina_local;
			}

			if (ms_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaCampo_local = new ListaCampo();
				ms_local.actualizarGrupoInformacionActual(
						ms_local.obtenerMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(
								ms_local.obtenerAplicacionActual().getIdAplicacion()));

				aplicacion_local = ms_local.obtenerAplicacionActual();
				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, true));

				pagina_local.setInicioCuerpoPagina(mc.concatenarCadena(getGeneradorComponentesHtml().abrirBody(""),
						dibujarTablaVinculosAplicaciones()));

				pagina_local.setTitulo(insertarTitulosInformacionPaginaPrincipal(
						ms_local.obtenerTituloAplicacionActual(), ms_local.obtenerInformacionActual()));

				pagina_local.setOpcionesConsulta(dibujarAreaConsultas(aplicacion_local));
				insertarMensajePagina(pagina_local);
				pagina_local.setContenidoDatos(dibujarTablaResultadosConsultaPrincipal(aplicacion_local));
				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {
			aplicacion_local = null;
		}

		return pagina_local;
	}

	private String insertarCampoTipoArchivo(Campo pCampo, boolean pEsModificacion, String pEvento,
			boolean pEsCampoOculto) {
		String campoTipoArchivo_local = "";
		int valorLlavePrimaria_local = -1;
		String destinoDescarga_local = null;
		String nombreArchivo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return campoTipoArchivo_local;
		}
		if (pEvento == ConstantesGeneral.VALOR_NULO) {
			return campoTipoArchivo_local;
		}

		try {
			campoTipoArchivo_local = getGeneradorComponentesHtml().insertarCampoTextoArchivo(pCampo, pEvento,
					pEsCampoOculto);

			if (pEsModificacion && !pEsCampoOculto && !pCampo.ocultarEtiquetaControlVer()) {
				valorLlavePrimaria_local = getManejadorSesion().obtenerValorLlavePrimaria();
				nombreArchivo_local = mc.convertirTildeANoTilde(
						getAdministradorBaseDatosAplicacion().obtenerNombreArchivo(valorLlavePrimaria_local, pCampo));

				if (!mc.esCadenaVacia(nombreArchivo_local)) {
					listaParametrosRedireccion_local = new ListaParametrosRedireccion();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(60));

					listaParametrosRedireccion_local.adicionar("grupoinformacionactual",
							String.valueOf(pCampo.getGrupoInformacion().getIdGrupoInformacion()));

					listaParametrosRedireccion_local.adicionar("valorllaveprimaria",
							String.valueOf(valorLlavePrimaria_local));

					listaParametrosRedireccion_local.adicionar("campoArchivo", String.valueOf(pCampo.getIdCampo()));

					destinoDescarga_local = listaParametrosRedireccion_local.concatenarParametros();

					campoTipoArchivo_local = mc.concatenarCadena(campoTipoArchivo_local,
							getGeneradorComponentesHtml().cerrarTablaSinSalto());

					campoTipoArchivo_local = mc.concatenarCadena(campoTipoArchivo_local,
							getGeneradorComponentesHtml().abrirTablaTransparente("", "", ""));

					campoTipoArchivo_local = mc.concatenarCadena(campoTipoArchivo_local,
							getGeneradorComponentesHtml().abrirFilaTabla());
					if (pCampo.getEstiloCampo().getAnchoEtiqueta() > 0) {
						campoTipoArchivo_local = mc.concatenarCadena(campoTipoArchivo_local,
								getGeneradorComponentesHtml().crearCeldaEtiquetaSinMargenSuperior(
										pCampo.getEstiloCampo(), pCampo.getEtiquetaCampo()));
					}

					if (pCampo.esImagen()) {
						campoTipoArchivo_local = mc.concatenarCadena(campoTipoArchivo_local,
								getGeneradorComponentesHtml().insertarCeldaDescargarArchivoImagen(pCampo,
										descargarArchivo(valorLlavePrimaria_local, pCampo), nombreArchivo_local));
					} else {

						campoTipoArchivo_local = mc.concatenarCadena(campoTipoArchivo_local,
								getGeneradorComponentesHtml().insertarCeldaDescargarArchivo(pCampo,
										destinoDescarga_local, nombreArchivo_local, 0, true, false,
										pCampo.getEstiloCampo().getAnchoControl()));
					}

				}

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			nombreArchivo_local = null;
			destinoDescarga_local = null;
			listaParametrosRedireccion_local = null;
		}

		return campoTipoArchivo_local;
	}

	private String obtenerValorCampoFormulario(Campo pCampo, ListaCampo pListaCampo, boolean pEsModificacion,
			boolean pEsRegistroPrincipal, int pValorEnlace) {
		String valorCampoFormulario_local = null;
		int dependienteEnlazado_local = 0;
		int valorLlavePrincipal_local = -1;
		int valorLlaveGrupoInformacion_local = -1;
		boolean esCampoHabilitado_local = true;
		boolean esCampoCalculado_local = false;
		boolean esDependienteEnlazado_local = false;
		boolean esObtenerValorDependiente_local = false;
		boolean esDependienteEnlazadoRecalculable_local = false;
		String tipoDato_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return valorCampoFormulario_local;
		}
		if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
			return valorCampoFormulario_local;
		}

		try {
			valorCampoFormulario_local = "";
			esCampoCalculado_local = (pCampo.getCalculoCampo().getCampoCalculado() != 1);
			tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
			dependienteEnlazado_local = pCampo.getEnlaceCampo().getDependienteEnlazado();
			esDependienteEnlazado_local = (dependienteEnlazado_local != 0);
			esDependienteEnlazadoRecalculable_local = (dependienteEnlazado_local == 1);
			esObtenerValorDependiente_local = (esDependienteEnlazadoRecalculable_local || !pEsModificacion
					|| getManejadorSesion().esRecargarPagina());

			if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
				esCampoHabilitado_local = getManejadorHabilitacionCampos().verificarHabilitarCampo(pListaCampo, pCampo,
						getManejadorSesion().obtenerValorLlavePrimariaAnterior(),
						getManejadorSesion().obtenerValorLlavePrimaria(), false);
			} else {

				esCampoHabilitado_local = getManejadorHabilitacionCampos().verificarHabilitarCampo(pListaCampo, pCampo,
						getManejadorSesion().obtenerValorLlavePrimaria(),
						getManejadorSesion().obtenerValorLlavePrimaria(), false);
			}

			if (mc.sonCadenasIguales(tipoDato_local, "LL") || mc.sonCadenasIguales(tipoDato_local, "MM")) {

				getManejadorInformacionRecalculable().asignarValorSemiautomaticoCampo(pCampo,
						getManejadorSesion().esRecargarPagina(),
						getManejadorSesion().obtenerValorLlavePrimariaAnterior());

				valorCampoFormulario_local = pCampo.getValorCampo().toString();
			}

			if (mc.sonCadenasIguales(tipoDato_local, "W")) {
				if (!pEsModificacion) {
					getManejadorInformacionRecalculable().asignarValorConsecutivoInterno(pCampo,
							getManejadorSesion().obtenerValorLlavePrimariaAnterior());

				} else if (getManejadorSesion().esRecargarPagina()
						&& Integer.parseInt(pCampo.getValorCampo().toString()) == 0 && esCampoHabilitado_local) {
					getManejadorInformacionRecalculable().asignarValorConsecutivoInterno(pCampo,
							getManejadorSesion().obtenerValorLlavePrimariaAnterior());
				}
			}

			getManejadorInformacionRecalculable().getManejadorCampoCalculado().setListaCampo(pListaCampo);
			getManejadorInformacionRecalculable().getManejadorCampoCalculado().setEstado("Incluyendo");
			if (pEsModificacion) {
				getManejadorInformacionRecalculable().getManejadorCampoCalculado().setEstado("Modificando");
			}

			if (esCampoCalculado_local && ((pEsRegistroPrincipal && pEsModificacion) || !pEsRegistroPrincipal)
					&& (pCampo.getGrupoInformacion().getAplicacion().esActualizarInformacionEnlazada()
							|| !pEsModificacion)) {
				valorLlavePrincipal_local = getManejadorSesion().obtenerValorLlavePrimaria();
				if (getManejadorSesion().obtenerGrupoInformacionActual().esGrupoInformacionMultiple()) {
					if (pEsModificacion) {
						valorLlavePrincipal_local = getManejadorSesion().obtenerValorLlavePrimariaAnterior();
						valorLlaveGrupoInformacion_local = getManejadorSesion().obtenerValorLlavePrimaria();
					} else {
						valorLlavePrincipal_local = getManejadorSesion().obtenerValorLlavePrimariaAnterior();
					}
				}

				if (pCampo.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO) {
					if (pCampo.esBorrarValorNoHabilitado()) {
						getManejadorInformacionRecalculable().getManejadorCampoCalculado().asignarValorCampoCalculado(
								pCampo, valorLlaveGrupoInformacion_local, valorLlavePrincipal_local);
					}

					if (!esCampoHabilitado_local) {
						pCampo.setValorCampo(null);
					}
				} else {
					getManejadorInformacionRecalculable().getManejadorCampoCalculado().asignarValorCampoCalculado(
							pCampo, valorLlaveGrupoInformacion_local, valorLlavePrincipal_local);
				}
			}

			if (esCampoCalculado_local && pCampo.esTipoDatoNumerico()
					&& pCampo.getValorCampo() == ConstantesGeneral.VALOR_NULO) {
				valorCampoFormulario_local = String.valueOf(0);
			}

			if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
				valorCampoFormulario_local = pCampo.getValorCampo().toString();
			}
			if (esCampoCalculado_local && mc.sonCadenasIguales(tipoDato_local, "R")
					&& mc.esCadenaNumerica(valorCampoFormulario_local, false)) {
				valorCampoFormulario_local = String
						.valueOf(op.redondearNumero(BigDecimal.valueOf(Double.parseDouble(valorCampoFormulario_local)),
								pCampo.getFormatoCampo().getNumeroDecimales()));
			}

			if (esDependienteEnlazado_local && esObtenerValorDependiente_local) {
				if (pCampo.esCampoEnlazado()) {
					valorCampoFormulario_local = String.valueOf(getManejadorInformacionRecalculable()
							.getManejadorCampoEnlazado().obtenerValorEnlazadoDependienteEnlazado(pCampo, pValorEnlace,
									valorCampoFormulario_local, esDependienteEnlazadoRecalculable_local));

				} else if (pCampo.getGrupoInformacion().getAplicacion().esActualizarInformacionEnlazada()
						|| getManejadorSesion().esRecargarPagina() || esHuboAsignacionEventos()) {

					if (pCampo.esTipoDatoTabla()) {
						if (pValorEnlace != -2) {
							valorCampoFormulario_local = getManejadorInformacionRecalculable()
									.getManejadorCampoEnlazado().obtenerValorTablaDependienteEnlazado(pCampo,
											pValorEnlace, valorCampoFormulario_local);
						}
					} else {

						valorCampoFormulario_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
								.obtenerValorDependienteEnlazado(pCampo, pValorEnlace, valorCampoFormulario_local);
					}
				}
			}

			getManejadorInformacionRecalculable().getManejadorCampoCalculado().setListaCampo(null);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			tipoDato_local = null;
		}

		return valorCampoFormulario_local;
	}

	private void asignarValoresListaCampoFormulario(Formulario pFormulario, boolean pEsModificacion,
			boolean pEsRegistroPrincipal) {
		int valorEnlace_local = 0;
		Iterator iterador_local = null;
		Campo campo_local = null;

		if (pFormulario == ConstantesGeneral.VALOR_NULO) {
			return;
		}

		try {
			if (pFormulario.getListaCampo() != ConstantesGeneral.VALOR_NULO) {
				iterador_local = pFormulario.getListaCampo().iterator();
				while (iterador_local.hasNext()) {
					campo_local = (Campo) iterador_local.next();
					if (campo_local.getEnlaceCampo().getDependienteEnlazado() != 0) {
						valorEnlace_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
								.obtenerValorEnlaceDependienteEnlazado(campo_local, pFormulario.getListaCampo(),
										getManejadorSesion().obtenerValorLlavePrimariaAnterior());
					}

					pFormulario.getListaCampo().asignarValorCampo(campo_local.getNombreCampo(),
							obtenerValorCampoFormulario(campo_local, pFormulario.getListaCampo(), pEsModificacion,
									pEsRegistroPrincipal, valorEnlace_local));
				}

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
	}

	private String obtenerEventoInhabilitarEnter(Campo pCampo, String pEvento) {
		String eventoInhabilitarEnter_local = "";

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return eventoInhabilitarEnter_local;
		}
		if (pEvento == ConstantesGeneral.VALOR_NULO) {
			return eventoInhabilitarEnter_local;
		}

		try {
			if (!mc.verificarExistenciaSubCadena(pEvento, " readonly ")
					&& !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "NN") && !pCampo.esTipoDatoTabla()
					&& !pCampo.esCampoEnlazado()) {

				eventoInhabilitarEnter_local = " onKeyDown=\"return inhabilitarEnter();\" ";
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return eventoInhabilitarEnter_local;
	}

	private String obtenerValorFiltradoPorCampoOrigen(Campo pCampo, ListaCampo pListaCampo) {
		String valorCampoOrigenFiltrado_local = "";
		Object valorCampo_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return valorCampoOrigenFiltrado_local;
		}
		if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
			return valorCampoOrigenFiltrado_local;
		}

		try {
			if (pCampo.getEnlaceCampo().getCampoOrigenFiltrado() != ConstantesGeneral.VALOR_NULO) {
				if (getAdministradorBaseDatosAplicacion().verificarCamposMismoGrupo(pCampo,
						pCampo.getEnlaceCampo().getCampoOrigenFiltrado())) {

					valorCampoOrigenFiltrado_local = pListaCampo
							.obtenerValorCampo(pCampo.getEnlaceCampo().getCampoOrigenFiltrado().getNombreCampo());
				} else {

					valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(
							pCampo.getEnlaceCampo().getCampoOrigenFiltrado(),
							ap.conformarNombreCampoLlavePrimaria(
									pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()),
							getManejadorSesion().obtenerValorLlavePrimariaAnterior());

					if (valorCampo_local != ConstantesGeneral.VALOR_NULO) {
						valorCampoOrigenFiltrado_local = valorCampo_local.toString();
					}
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			valorCampo_local = null;
		}

		return valorCampoOrigenFiltrado_local;
	}

	private String insertarCamposModificables(ListaCampo pListaCampo, Campo pCampo, boolean pEsModificacion,
			boolean pExistePermisoModificar, int pIdTabla, int pValorEnlace) {
		String insertarCamposModificables_local = "";
		int idValorSeleccionado_local = -1;
		int idTabla_local = -1;
		int dependienteEnlazado_local = 0;
		int listaDependiente_local = 0;
		boolean esCampoHabilitado_local = false;
		boolean esRecargarPagina_local = false;
		boolean esDependienteEnlazado_local = false;
		boolean esCampoCalculado_local = false;
		boolean existenRestricciones_local = false;
		boolean excluirValores_local = false;
		String valorCampo_local = null;
		String etiquetaCampo_local = null;
		String tipoDato_local = null;
		String nombreCampo_local = null;
		String evento_local = null;
		String eventoOnBlur_local = null;
		String eventoHabilitar_local = null;
		String eventoCambioMaestro_local = null;
		String valorFiltradoPorCampoOrigen_local = null;
		ListaCadenas listaEventos_local = null;
		ListaCadenas listaRestriccionesCampoValores_local = null;
		boolean esCampoOculto_local = false;

		if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarCamposModificables_local;
		}
		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarCamposModificables_local;
		}

		try {
			esRecargarPagina_local = getManejadorSesion().esRecargarPagina();
			valorCampo_local = "";
			evento_local = "";
			tipoDato_local = pCampo.getFormatoCampo().getTipoDato();
			etiquetaCampo_local = pCampo.getEtiquetaCampo();
			nombreCampo_local = pCampo.getNombreCampo();
			dependienteEnlazado_local = pCampo.getEnlaceCampo().getDependienteEnlazado();
			esCampoCalculado_local = (pCampo.getCalculoCampo().getCampoCalculado() != 1);
			esCampoHabilitado_local = true;
			eventoOnBlur_local = "";
			listaEventos_local = new ListaCadenas();
			esCampoOculto_local = (pCampo.getTipoHabilitacion() == 3);

			if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
				valorCampo_local = pCampo.getValorCampo().toString();
			}

			if (!mc.sonCadenasIguales(tipoDato_local, "QQ")
					&& !getManejadorPermisoUsuario().verificarPermisoVerCampo(pCampo) && !pCampo.esObligatorio()) {
				return insertarCamposModificables_local;
			}

			if (!pCampo.esModificable()) {
				evento_local = " readonly ";
			}

			esDependienteEnlazado_local = (dependienteEnlazado_local != 0);

			if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
				esCampoHabilitado_local = getManejadorHabilitacionCampos().verificarHabilitarCampo(pListaCampo, pCampo,
						getManejadorSesion().obtenerValorLlavePrimariaAnterior(),
						getManejadorSesion().obtenerValorLlavePrimaria(), false);
			} else {

				esCampoHabilitado_local = getManejadorHabilitacionCampos().verificarHabilitarCampo(pListaCampo, pCampo,
						getManejadorSesion().obtenerValorLlavePrimaria(),
						getManejadorSesion().obtenerValorLlavePrimaria(), false);
			}

			if (esDependienteEnlazado_local) {
				esCampoHabilitado_local = (pValorEnlace == -2);
			}

			eventoOnBlur_local = obtenerEventoOnBlur(pCampo);

			if (esCampoCalculado_local && pCampo.getTipoHabilitacion() != 3
					&& !mc.sonCadenasIguales(tipoDato_local, "F") && esCampoHabilitado_local) {

				evento_local = " readonly ";
			} else if (!esCampoHabilitado_local) {
				evento_local = " disabled ";
				if (esCampoCalculado_local) {
					evento_local = mc.concatenarCadena(evento_local, " readonly ");
				}
			}

			evento_local = mc.concatenarCadena(evento_local, eventoOnBlur_local);
			if (!mc.sonCadenasIguales(tipoDato_local, "QQ") && pEsModificacion && (!pExistePermisoModificar
					|| !getManejadorPermisoUsuario().verificarPermisoModificarCampo(pCampo))) {
				evento_local = mc.concatenarCadena(evento_local, " disabled ");
			}

			eventoHabilitar_local = conformarEventoHabilitarCampos(pCampo);
			listaEventos_local.adicionar(eventoHabilitar_local);
			listaRestriccionesCampoValores_local = getManejadorPermisoUsuario()
					.obtenerListaRestriccionesCampoValores(pCampo);
			if (listaRestriccionesCampoValores_local.contarElementos() == 0) {
				listaRestriccionesCampoValores_local = null;
				listaRestriccionesCampoValores_local = getManejadorPermisoUsuario()
						.obtenerListaRestriccionesCampoValoresMenos(pCampo);
				excluirValores_local = true;
			}
			existenRestricciones_local = (listaRestriccionesCampoValores_local.contarElementos() > 0);
			evento_local = mc.concatenarCadena(evento_local, obtenerEventoInhabilitarEnter(pCampo, evento_local));
			if (!pCampo.getRestriccionCampo().esLlaveForanea()) {
				if (pCampo.esCampoEnlazado()) {
					if (mc.sonCadenasIguales(valorCampo_local, "")) {
						valorCampo_local = String.valueOf(0);
					}
					if (getManejadorSesion().obtenerMotorAplicacion().verificarCampoTieneCamposDependientes(pCampo)) {
						evento_local = mc.concatenarCadena(evento_local,
								" onChange = \" validarHabilitacion" + nombreCampo_local + '(' + ");\"");
					}

					valorFiltradoPorCampoOrigen_local = obtenerValorFiltradoPorCampoOrigen(pCampo, pListaCampo);
					return mc.concatenarCadena(insertarCamposModificables_local,
							insertarCampoEnlazado(pCampo, Integer.parseInt(valorCampo_local),
									pCampo.getEnlaceCampo().esOpcionDesconocido(), evento_local,
									listaRestriccionesCampoValores_local, excluirValores_local,
									existenRestricciones_local, esCampoOculto_local, valorFiltradoPorCampoOrigen_local,
									mc.sonCadenasIguales(getManejadorSesion().obtenerEstadoActual(), "Incluyendo"),
									esCampoCalculado_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "T") || mc.sonCadenasIguales(tipoDato_local, "K")
						|| mc.sonCadenasIguales(tipoDato_local, "X") || mc.sonCadenasIguales(tipoDato_local, "MM")) {

					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoTexto(pCampo, valorCampo_local, evento_local,
									esCampoOculto_local, esCampoCalculado_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "E") || mc.sonCadenasIguales(tipoDato_local, "LL")
						|| mc.sonCadenasIguales(tipoDato_local, "W") || mc.sonCadenasIguales(tipoDato_local, "BB")
						|| mc.sonCadenasIguales(tipoDato_local, "XX")) {

					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoNumeroEntero(pCampo, valorCampo_local,
									evento_local, mc.sonCadenasIguales(tipoDato_local, "BB"), esCampoOculto_local,
									esCampoCalculado_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "R") || mc.sonCadenasIguales(tipoDato_local, "GG")) {
					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoNumeroReal(pCampo, valorCampo_local,
									evento_local, mc.sonCadenasIguales(tipoDato_local, "GG"), esCampoOculto_local,
									esCampoCalculado_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "B")) {
					if (mc.sonCadenasIguales(valorCampo_local, "")) {
						valorCampo_local = String.valueOf(false);
					}
					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoCajaSeleccion(nombreCampo_local,
									etiquetaCampo_local, Boolean.parseBoolean(valorCampo_local), evento_local, 2,
									"left", esCampoOculto_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "F")) {
					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoFecha(pCampo, valorCampo_local, "%Y-%m-%d",
									evento_local, esCampoHabilitado_local, esCampoCalculado_local,
									esCampoOculto_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "H")) {
					if (!esRecargarPagina_local) {
						valorCampo_local = ap.obtenerHoraConFormato("H24", valorCampo_local);
					}
					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoHora(pCampo, valorCampo_local, evento_local,
									esCampoHabilitado_local, esCampoOculto_local, esCampoCalculado_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "FF")) {
					if (mc.sonCadenasIguales(valorCampo_local, "")) {
						valorCampo_local = String.valueOf(0);
					}
					listaRestriccionesCampoValores_local = new ListaCadenas();
					return mc.concatenarCadena(insertarCamposModificables_local,
							insertarCampoTipoValorDependiente(pIdTabla, Integer.parseInt(valorCampo_local),
									evento_local, listaRestriccionesCampoValores_local, false, false));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "NN")) {
					return mc.concatenarCadena(insertarCamposModificables_local,
							getGeneradorComponentesHtml().insertarCampoParrafo(pCampo, valorCampo_local, evento_local,
									esCampoOculto_local, esCampoCalculado_local));
				}

				if (mc.sonCadenasIguales(tipoDato_local, "QQ")) {
					return insertarCampoSeleccionarPlantilla(pCampo,
							getManejadorSesion().obtenerMotorAplicacion()
									.obtenerAplicacionPlantillaPrimerCampoDocumentoConPlantillaGrupoInformacion(
											pCampo.getGrupoInformacion()),
							getManejadorSesion().obtenerPlantillaUtilizar());
				}

				if (mc.sonCadenasIguales(tipoDato_local, "J")) {
					return insertarCampoTipoArchivo(pCampo, pEsModificacion, evento_local, esCampoOculto_local);

				}
			} else if (!mc.sonCadenasIguales(etiquetaCampo_local, "") && pCampo.esTipoDatoTabla()) {
				if (mc.sonCadenasIguales(valorCampo_local, "")) {
					valorCampo_local = String.valueOf(0);
				}
				idTabla_local = Integer.parseInt(tipoDato_local);
				eventoCambioMaestro_local = obtenerEventoCambioMaestro(pCampo);
				listaEventos_local.adicionar(eventoCambioMaestro_local);
				evento_local = mc.concatenarCadena(evento_local, conformarEventoOnChange(listaEventos_local));
				if (esCampoCalculado_local && !mc.verificarExistenciaSubCadena(evento_local, " disabled ")) {
					evento_local = mc.concatenarCadena(evento_local, " disabled ");
				}

				idValorSeleccionado_local = 0;
				if (!mc.sonCadenasIguales(valorCampo_local, "")) {
					idValorSeleccionado_local = Integer.parseInt(valorCampo_local);
				}
				if (pCampo.getListaDependiente() != ConstantesGeneral.VALOR_NULO) {
					listaDependiente_local = pCampo.getListaDependiente().getIdCampo();
				}
				if (listaDependiente_local == 0) {
					return mc.concatenarCadena(insertarCamposModificables_local,
							insertarCampoTipoTabla(pCampo, idTabla_local, idValorSeleccionado_local, evento_local,
									listaRestriccionesCampoValores_local, excluirValores_local,
									existenRestricciones_local, esCampoOculto_local, esCampoCalculado_local));
				}

				return mc.concatenarCadena(insertarCamposModificables_local,
						insertarCampoListaDependiente(pListaCampo, pCampo, idValorSeleccionado_local, evento_local,
								listaRestriccionesCampoValores_local, excluirValores_local, existenRestricciones_local,
								esCampoOculto_local, esCampoCalculado_local));

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			evento_local = null;
			tipoDato_local = null;
			valorCampo_local = null;
			nombreCampo_local = null;
			etiquetaCampo_local = null;
			eventoHabilitar_local = null;
			eventoCambioMaestro_local = null;
			valorFiltradoPorCampoOrigen_local = null;
			listaRestriccionesCampoValores_local = null;
		}

		return insertarCamposModificables_local;
	}

	private String insertarFormulario(Formulario pFormulario, int pIdTabla, ListaBotones pListaBotones,
			boolean pEsModificacion, int pUbicacionBotones, String pAlineacionBotones) {
		int valorEnlace_local = -1;
		boolean existePermisoModificar_local = false;
		String formularioAplicacion_local = "";
		String valorCampo_local = null;
		Iterator iterador_local = null;
		Campo campo_local = null;
		GrupoInformacion grupoInformacion_local = null;
		GrupoInformacion grupoInformacionNoMultipleVisible_local = null;
		int contador_local = 0;
		int anchoTabla_local = 0;

		if (pFormulario == ConstantesGeneral.VALOR_NULO) {
			return formularioAplicacion_local;
		}
		if (pListaBotones == ConstantesGeneral.VALOR_NULO) {
			return formularioAplicacion_local;
		}
		if (pAlineacionBotones == ConstantesGeneral.VALOR_NULO) {
			return formularioAplicacion_local;
		}

		try {
			if (pUbicacionBotones == 0 || pUbicacionBotones == 2) {
				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().insertarBotones(pListaBotones, 0, pAlineacionBotones));
			}

			grupoInformacion_local = getManejadorSesion().obtenerGrupoInformacionActual();

			if (!grupoInformacion_local.esGrupoInformacionMultiple()) {
				grupoInformacionNoMultipleVisible_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerPrimerGrupoInformacionNoMultipleNoPrincipal(
								grupoInformacion_local.getAplicacion().getIdAplicacion());
			}

			if (pEsModificacion) {
				existePermisoModificar_local = getManejadorSesion().esConfiguracion();
				if (!existePermisoModificar_local) {
					if (grupoInformacion_local.esGrupoInformacionMultiple()) {
						existePermisoModificar_local = (getManejadorPermisoUsuario()
								.verificarPermisoModificarGrupoInformacion(grupoInformacion_local)
								|| getManejadorPermisoUsuario().verificarExistenciaCampoPermisoModificar(
										grupoInformacion_local.getIdGrupoInformacion()));
					} else {

						existePermisoModificar_local = (getManejadorPermisoUsuario()
								.verificarPermisoModificarRegistrosAplicacion(
										getManejadorSesion().obtenerAplicacionActual())
								|| getManejadorPermisoUsuario().verificarExistenciaCampoGrupoNoMultiplePermisoModificar(
										getManejadorSesion().obtenerAplicacionActual().getIdAplicacion()));
					}
				}
			}

			if (grupoInformacionNoMultipleVisible_local != ConstantesGeneral.VALOR_NULO) {
				grupoInformacion_local = grupoInformacionNoMultipleVisible_local;
			}

			formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
					getGeneradorComponentesHtml().insertarCampoOculto("recargarPagina", String.valueOf(false)));

			if (pFormulario.getListaCampo() != ConstantesGeneral.VALOR_NULO) {
				iterador_local = pFormulario.getListaCampo().iterator();
				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().abrirTablaFormularioDistribucion(grupoInformacion_local,
								pFormulario.getListaCampo().obtenerListaCamposVisiblesFormulario()
										.calcularAnchoTablaFormulario()));

				while (iterador_local.hasNext()) {
					campo_local = (Campo) iterador_local.next();
					if (campo_local.getTipoHabilitacion() != 3) {
						if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
							valorCampo_local = campo_local.getValorCampo().toString();
						}
						if (campo_local.getEnlaceCampo().getDependienteEnlazado() != 0) {
							valorEnlace_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
									.obtenerValorEnlaceDependienteEnlazado(campo_local, pFormulario.getListaCampo(),
											getManejadorSesion().obtenerValorLlavePrimariaAnterior());
						}

						if (campo_local.esTipoDatoDocumento()) {
							formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
									getGeneradorComponentesHtml().cerrarTablaFormulario());

							formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
									insertarCampoTipoDocumento(
											obtenerContenidoDocumento(campo_local, valorEnlace_local, valorCampo_local),
											campo_local.getNombreCampo()));
							continue;
						}
						if (campo_local.getRestriccionCampo().esLlavePrimaria()
								|| mc.esCadenaVacia(campo_local.getEtiquetaCampo())) {
							if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "B")
									&& mc.esCadenaVacia(valorCampo_local)) {
								valorCampo_local = String.valueOf(false);
							}
							formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
									getGeneradorComponentesHtml().insertarCampoOculto(campo_local.getNombreCampo(),
											valorCampo_local));
							continue;
						}
						contador_local++;
						if (campo_local.getEstiloCampo().cambiarRenglon()) {
							if (contador_local != 1) {
								formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
										getGeneradorComponentesHtml().cerrarFilaTabla());

								formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
										getGeneradorComponentesHtml().cerrarTablaSinSalto());
							}

							anchoTabla_local = pFormulario.getListaCampo().obtenerListaCamposVisiblesFormulario()
									.calcularAnchoTablaInternaFormulario(campo_local.getIdCampo());

							formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
									getGeneradorComponentesHtml()
											.abrirTablaInternaFormulario(String.valueOf(anchoTabla_local), ""));

							formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
									getGeneradorComponentesHtml().abrirFilaTabla());
						}

						formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
								insertarCamposModificables(pFormulario.getListaCampo(), campo_local, pEsModificacion,
										existePermisoModificar_local, pIdTabla, valorEnlace_local));
					}
				}

				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().cerrarFilaTabla());

				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().cerrarTablaSinSalto());

				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().cerrarTablaFormulario());
			}

			if (pUbicacionBotones == 1 || pUbicacionBotones == 2) {
				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().insertarBotones(pListaBotones, 0, pAlineacionBotones));
			}

			if (pFormulario.getListaCampo() != ConstantesGeneral.VALOR_NULO) {
				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().abrirTablaFormulario("tablaFormularioOculto", "", "center"));

				iterador_local = null;
				iterador_local = pFormulario.getListaCampo().iterator();
				while (iterador_local.hasNext()) {
					campo_local = (Campo) iterador_local.next();
					if (campo_local.getTipoHabilitacion() == 3) {
						if (campo_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
							valorCampo_local = campo_local.getValorCampo().toString();
						}
						if (campo_local.getEnlaceCampo().getDependienteEnlazado() != 0) {
							valorEnlace_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
									.obtenerValorEnlaceDependienteEnlazado(campo_local, pFormulario.getListaCampo(),
											getManejadorSesion().obtenerValorLlavePrimariaAnterior());
						}

						formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
								insertarCamposModificables(pFormulario.getListaCampo(), campo_local, pEsModificacion,
										existePermisoModificar_local, pIdTabla, valorEnlace_local));
					}
				}

				formularioAplicacion_local = mc.concatenarCadena(formularioAplicacion_local,
						getGeneradorComponentesHtml().cerrarTablaSinSalto());
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campo_local = null;
			iterador_local = null;
			valorCampo_local = null;
			grupoInformacion_local = null;
			grupoInformacionNoMultipleVisible_local = null;
		}
		return formularioAplicacion_local;
	}

	public Pagina obtenerPaginaIncluirRegistroPrincipal(GrupoInformacion pGrupoInformacion) throws IOException {
		Pagina pagina_local = null;
		ManejadorSesion ms_local = getManejadorSesion();
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaBotones listaBotones_local = null;
		ListaCampo listaCampo_local = null;
		Formulario formulario_local = null;
		boolean huboErrorEventos_local = false;
		Campo campoPlantilla_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return pagina_local;
		}

		try {
			if (ms_local.obtenerExistenEventosEnEjecucion()) {
				pagina_local = obtenerPaginaErrorEjecucionEventos();
				return pagina_local;
			}

			if (ms_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaBotones_local = new ListaBotones();
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(89));

				listaCampo_local = ms_local.obtenerMotorAplicacion()
						.obtenerListaCamposGruposInformacionNoMultiplesConArchivos(
								pGrupoInformacion.getAplicacion().getIdAplicacion());

				if (ms_local.obtenerMotorAplicacion().comprobarCamposDocumentosConPlantilla(pGrupoInformacion)) {
					campoPlantilla_local = cad.obtenerCampoPlantillaUtilizar(pGrupoInformacion);
					campoPlantilla_local.setEstiloCampo(ms_local.obtenerMotorAplicacion()
							.obtenerPrimerCampoTipoDocumento(pGrupoInformacion).getEstiloCampo());

					listaCampo_local.adicionar(campoPlantilla_local);
				}

				huboErrorEventos_local = (!mc.esCadenaVacia(ms_local.obtenerMensajeEventos())
						&& ms_local.obtenerTipoMensajeEventos() == 2);

				if (!ms_local.esRecargarPagina() && !huboErrorEventos_local) {
					ms_local.obtenerManejadorEventos().setNombreEvento("INICIOINCLUIRPRINCIPAL");

					ms_local.obtenerManejadorEventos().setListaCampoValoresAnteriores(null);
					ms_local.obtenerManejadorEventos().setGrupoInformacion(ms_local.obtenerGrupoInformacionActual());

					ms_local.obtenerManejadorEventos()
							.setValorLlaveGrupoPrincipal(ms_local.obtenerValorLlavePrimariaAnterior());

					ms_local.obtenerManejadorEventos()
							.setValorLlaveGrupoInformacion(ms_local.obtenerValorLlavePrimaria());

					ms_local.obtenerManejadorEventos().setListaCampo(listaCampo_local);
					ms_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);

					ms_local.actualizarExistenEventosEnEjecucion(true);
					ms_local.obtenerManejadorEventos().ejecutarEvento();
					ms_local.actualizarExistenEventosEnEjecucion(
							!ms_local.obtenerManejadorEventos().haFinalizadoEjecucion());

					setHuboAsignacionEventos(ms_local.obtenerManejadorEventos().esHuboAsignacion());
					ms_local.obtenerManejadorEventos().setHuboAsignacion(false);
					ms_local.actualizarMensajeEventos(ms_local.obtenerManejadorEventos().getMensajeEventos());

					ms_local.actualizarTipoMensajeEventos(ms_local.obtenerManejadorEventos().getTipoMensajeEventos());

					ms_local.obtenerManejadorEventos().setMensajeEventos("");
				}

				ms_local.asignarValoresAtributosSesionACampos(listaCampo_local);

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit(" return (verificarCampos() && preguntaIncluirRegistro());");
				formulario_local.setEsMultipart(getAdministradorBaseDatosSisnet()
						.verificarGrupoInformacionContieneCampoArchivo(ms_local.obtenerGrupoInformacionActual()));

				formulario_local.setListaCampo(listaCampo_local);

				insertarMensajePagina(pagina_local);

				asignarValoresListaCampoFormulario(formulario_local, false, true);

				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(formulario_local.getListaCampo(),
						formulario_local.getNombre(), 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml()
						.abrirBody(conformarEventosBody(formulario_local.getListaCampo(), "formularioIncluir")));

				pagina_local.setTitulo(insertarEncabezadoPagina(ms_local.obtenerTituloAplicacionActual(),
						ms_local.obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				listaBotones_local.adicionar("aceptarinclusion", true, "", "Aceptar Inclusi\u00f3n", "", 0, false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));

				listaBotones_local.adicionar("cancelarinclusion", false,
						" onClick=\"return (preguntaCancelarInclusion());\" ", "Cancelar Inclusi\u00f3n",
						listaParametrosRedireccion_local.concatenarParametros(), 0, false);

				formulario_local
						.setContenido(insertarFormulario(formulario_local, -1, listaBotones_local, false, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());
				pagina_local.setFinCuerpoPagina(mc.concatenarCadena(insertarImagenProcesandoInformacion(false),
						getGeneradorComponentesHtml().cerrarBody()));

				ms_local.actualizarRecargarPagina(false);
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			formulario_local = null;
			listaCampo_local = null;
			listaBotones_local = null;
			listaParametrosRedireccion_local = null;
			campoPlantilla_local = null;
		}
		return pagina_local;
	}

	private boolean verificarDocumentoConPlantillaVacio(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria) {
		boolean documentoVacio_local = false;
		String nombreLlave_local = null;
		ListaCampo listaCamposDocumento_local = null;
		Iterator iterador_local = null;
		Campo campo_local = null;
		Object valorDocumento_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return documentoVacio_local;
		}

		try {
			listaCamposDocumento_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaCamposTipoDocumentoConPlantillaGrupoInformacion(pGrupoInformacion);

			if (listaCamposDocumento_local != ConstantesGeneral.VALOR_NULO) {
				iterador_local = listaCamposDocumento_local.iterator();
				nombreLlave_local = ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion());
				if (!pGrupoInformacion.esGrupoInformacionMultiple()) {
					nombreLlave_local = ap
							.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion());
				}
				if (iterador_local.hasNext()) {
					campo_local = (Campo) iterador_local.next();
					valorDocumento_local = getAdministradorBaseDatosAplicacion().obtenerValorCampo(campo_local,
							nombreLlave_local, pValorLlavePrimaria);

					documentoVacio_local = (valorDocumento_local == ConstantesGeneral.VALOR_NULO);
					if (!documentoVacio_local) {
						documentoVacio_local = mc.esCadenaVacia(valorDocumento_local.toString());
					}
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campo_local = null;
			iterador_local = null;
			nombreLlave_local = null;
			valorDocumento_local = null;
			listaCamposDocumento_local = null;
		}

		return documentoVacio_local;
	}

	public Pagina obtenerPaginaIncluirRegistro(GrupoInformacion pGrupoInformacion, boolean pEsModificacion)
			throws IOException {
		Pagina pagina_local = null;
		boolean existenCamposDocumentoConPlantilla_local = false;
		boolean huboErrorEventos_local = false;
		String accionAceptar_local = null;
		String nombreBotonAceptar_local = null;
		String descripcionBotonAceptar_local = null;
		String nombreBotonCancelar_local = null;
		String descripcionBotonCancelar_local = null;
		String eventosBody_local = null;
		String eventosFormulario_local = null;
		String accionCancelar_local = null;
		String eventoBotonCancelar_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaBotones listaBotones_local = null;
		ListaCampo listaCampo_local = null;
		ListaCampo listaCampoValoresAnteriores_local = null;
		ListaCampo listaCampoGruposNoMultiples_local = null;
		Formulario formulario_local = null;
		Campo campoPlantilla_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return pagina_local;
		}

		try {
			if (getManejadorSesion().obtenerExistenEventosEnEjecucion()) {
				pagina_local = obtenerPaginaErrorEjecucionEventos();
				return pagina_local;
			}

			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaBotones_local = new ListaBotones();
				accionAceptar_local = String.valueOf(64);
				nombreBotonAceptar_local = "aceptarinclusion";
				descripcionBotonAceptar_local = "Aceptar Inclusi\u00f3n";
				nombreBotonCancelar_local = "cancelarinclusion";
				descripcionBotonCancelar_local = "Cancelar Inclusi\u00f3n";
				eventoBotonCancelar_local = " onClick=\"return (preguntaCancelarInclusion());\" ";
				if (pEsModificacion) {
					accionAceptar_local = String.valueOf(65);
					nombreBotonAceptar_local = "aceptarmodificacion";
					descripcionBotonAceptar_local = "Aceptar Modificaci\u00f3n";
					nombreBotonCancelar_local = "cancelarmodificacion";
					descripcionBotonCancelar_local = "Cancelar Modificaci\u00f3n";
					eventoBotonCancelar_local = " onClick=\"return (preguntaCancelarModificacion());\" ";
				}

				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				if (pEsModificacion
						&& !getManejadorPermisoUsuario().verificarPermisoModificarGrupoInformacion(pGrupoInformacion)
						&& !getManejadorPermisoUsuario()
								.verificarExistenciaCampoPermisoModificar(pGrupoInformacion.getIdGrupoInformacion())) {

					eventosFormulario_local = "return mensajeAutorizacionModificacion();";
				} else {
					listaParametrosRedireccion_local.adicionar("accion", accionAceptar_local);
					existenCamposDocumentoConPlantilla_local = getManejadorSesion().obtenerMotorAplicacion()
							.comprobarCamposDocumentosConPlantilla(pGrupoInformacion);

					if (pEsModificacion) {
						eventosFormulario_local = "return (verificarCampos() && preguntaModificarRegistro());";
					} else {
						eventosFormulario_local = " return (verificarCampos() && preguntaIncluirRegistro());";
					}

					if (existenCamposDocumentoConPlantilla_local && pEsModificacion
							&& !verificarDocumentoConPlantillaVacio(pGrupoInformacion,
									getManejadorSesion().obtenerValorLlavePrimaria())) {
						eventosFormulario_local = "return (verificarCampos() && recordarCambioPlantilla() && preguntaModificarRegistro());";
					}
				}
				listaCampo_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerListaCamposGrupoInformacionConArchivos(pGrupoInformacion.getIdGrupoInformacion());

				listaCampoValoresAnteriores_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerCopiaListaCamposGrupoInformacionConArchivos(pGrupoInformacion.getIdGrupoInformacion());

				if (pGrupoInformacion.esGrupoInformacionMultiple()) {
					listaCampoGruposNoMultiples_local = getManejadorSesion().obtenerMotorAplicacion()
							.obtenerCopiaListaCamposGruposInformacionNoMultiplesConArchivos(
									pGrupoInformacion.getAplicacion().getIdAplicacion());

					getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(
							getManejadorInformacionRecalculable().getMotorAplicacion()
									.obtenerGrupoInformacionPrincipalAplicacion(
											pGrupoInformacion.getAplicacion().getIdAplicacion()),
							listaCampoGruposNoMultiples_local,
							getManejadorSesion().obtenerValorLlavePrimariaAnterior());
				}

				if (existenCamposDocumentoConPlantilla_local) {
					campoPlantilla_local = cad.obtenerCampoPlantillaUtilizar(pGrupoInformacion);
					campoPlantilla_local.setEstiloCampo(getManejadorSesion().obtenerMotorAplicacion()
							.obtenerPrimerCampoTipoDocumento(pGrupoInformacion).getEstiloCampo());

					listaCampo_local.adicionar(campoPlantilla_local);
				}

				eventosBody_local = conformarEventosBody(listaCampo_local, "formularioIncluir");

				if (pEsModificacion) {
					getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(pGrupoInformacion,
							listaCampo_local, getManejadorSesion().obtenerValorLlavePrimaria());

					getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(pGrupoInformacion,
							listaCampoValoresAnteriores_local, getManejadorSesion().obtenerValorLlavePrimaria());
				}

				if (pGrupoInformacion.esGrupoInformacionMultiple()) {
					listaCampoGruposNoMultiples_local.concatenar(listaCampoValoresAnteriores_local);
					listaCampoValoresAnteriores_local = listaCampoGruposNoMultiples_local;
				}

				getManejadorSesion().obtenerManejadorEventos().setRealizarAccionUsuario(true);

				huboErrorEventos_local = (!mc.esCadenaVacia(getManejadorSesion().obtenerMensajeEventos())
						&& getManejadorSesion().obtenerTipoMensajeEventos() == 2);

				if (!getManejadorSesion().esRecargarPagina() && !huboErrorEventos_local) {
					if (!pEsModificacion) {
						getManejadorSesion().obtenerManejadorEventos()
								.setNombreEvento("INICIOINCLUIR " + getManejadorSesion().obtenerGrupoInformacionActual()
										.getDescripcionGrupoInformacion());

						getManejadorSesion().obtenerManejadorEventos()
								.setGrupoInformacion(getManejadorSesion().obtenerGrupoInformacionActual());

						getManejadorSesion().obtenerManejadorEventos()
								.setValorLlaveGrupoPrincipal(getManejadorSesion().obtenerValorLlavePrimariaAnterior());

						getManejadorSesion().obtenerManejadorEventos()
								.setValorLlaveGrupoInformacion(getManejadorSesion().obtenerValorLlavePrimaria());

						getManejadorSesion().obtenerManejadorEventos().setListaCampo(listaCampo_local);
						getManejadorSesion().obtenerManejadorEventos()
								.setListaCampoValoresAnteriores(listaCampoValoresAnteriores_local);

						getManejadorSesion().actualizarExistenEventosEnEjecucion(true);
						getManejadorSesion().obtenerManejadorEventos().ejecutarEvento();
						getManejadorSesion().actualizarExistenEventosEnEjecucion(
								!getManejadorSesion().obtenerManejadorEventos().haFinalizadoEjecucion());

						setHuboAsignacionEventos(getManejadorSesion().obtenerManejadorEventos().esHuboAsignacion());
						getManejadorSesion().obtenerManejadorEventos().setHuboAsignacion(false);
					} else {
						getManejadorSesion().obtenerManejadorEventos()
								.setNombreEvento("INICIOMODIFICAR " + getManejadorSesion()
										.obtenerGrupoInformacionActual().getDescripcionGrupoInformacion());

						getManejadorSesion().obtenerManejadorEventos()
								.setGrupoInformacion(getManejadorSesion().obtenerGrupoInformacionActual());

						getManejadorSesion().obtenerManejadorEventos()
								.setValorLlaveGrupoPrincipal(getManejadorSesion().obtenerValorLlavePrimariaAnterior());

						getManejadorSesion().obtenerManejadorEventos()
								.setValorLlaveGrupoInformacion(getManejadorSesion().obtenerValorLlavePrimaria());

						getManejadorSesion().obtenerManejadorEventos().setListaCampo(listaCampo_local);
						getManejadorSesion().obtenerManejadorEventos()
								.setListaCampoValoresAnteriores(listaCampoValoresAnteriores_local);

						getManejadorSesion().actualizarExistenEventosEnEjecucion(true);
						getManejadorSesion().obtenerManejadorEventos().ejecutarEvento();
						getManejadorSesion().actualizarExistenEventosEnEjecucion(
								!getManejadorSesion().obtenerManejadorEventos().haFinalizadoEjecucion());
					}
				}

				getManejadorSesion()
						.actualizarMensajeEventos(getManejadorSesion().obtenerManejadorEventos().getMensajeEventos());

				getManejadorSesion().actualizarTipoMensajeEventos(
						getManejadorSesion().obtenerManejadorEventos().getTipoMensajeEventos());

				getManejadorSesion().obtenerManejadorEventos().setMensajeEventos("");

				insertarMensajePagina(pagina_local);

				getManejadorSesion().asignarValoresSesionCamposGrupoInformacion(listaCampo_local);

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit(eventosFormulario_local);
				formulario_local.setEsMultipart(getAdministradorBaseDatosSisnet()
						.verificarGrupoInformacionContieneCampoArchivo(pGrupoInformacion));

				formulario_local.setListaCampo(listaCampo_local);

				asignarValoresListaCampoFormulario(formulario_local, pEsModificacion, false);

				pagina_local.setEncabezadoPagina(
						obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(eventosBody_local));

				accionCancelar_local = String.valueOf(95);
				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(accionCancelar_local, 0),
						0));

				listaBotones_local.adicionar(nombreBotonAceptar_local, true, "", descripcionBotonAceptar_local, "", 0,
						false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", accionCancelar_local);
				listaBotones_local.adicionar(nombreBotonCancelar_local, false, eventoBotonCancelar_local,
						descripcionBotonCancelar_local, listaParametrosRedireccion_local.concatenarParametros(), 0,
						false);

				formulario_local.setContenido(
						insertarFormulario(formulario_local, -1, listaBotones_local, pEsModificacion, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());
				pagina_local.setFinCuerpoPagina(mc.concatenarCadena(insertarImagenProcesandoInformacion(false),
						getGeneradorComponentesHtml().cerrarBody()));

				getManejadorSesion().actualizarRecargarPagina(false);
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			formulario_local = null;
			listaCampo_local = null;
			eventosBody_local = null;
			listaBotones_local = null;
			accionAceptar_local = null;
			accionCancelar_local = null;
			eventosFormulario_local = null;
			nombreBotonAceptar_local = null;
			eventoBotonCancelar_local = null;
			nombreBotonCancelar_local = null;
			descripcionBotonAceptar_local = null;
			descripcionBotonCancelar_local = null;
			listaParametrosRedireccion_local = null;
			listaCampoGruposNoMultiples_local = null;
			listaCampoValoresAnteriores_local = null;
			campoPlantilla_local = null;
		}
		return pagina_local;
	}

	private String dibujarConsultaGruposInformacionMultiplesAplicacion(int pIdAplicacion, int pValorLlavePrimaria,
			int pNivelesAnterioresDirectorio) {
		String consultaGruposInformacionMultiples_local = "";
		boolean existeDocumento_local = false;
		ListaGrupoInformacion listaGrupoInformacion_local = null;
		Iterator iterator_local = null;
		GrupoInformacion grupoInformacion_local = null;

		try {
			listaGrupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaGruposInformacionMultiplesAplicacion(pIdAplicacion);

			if (listaGrupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
				iterator_local = listaGrupoInformacion_local.iterator();
				while (iterator_local.hasNext()) {
					grupoInformacion_local = (GrupoInformacion) iterator_local.next();
					if (grupoInformacion_local.esMostrarDetalle()) {
						existeDocumento_local = getAdministradorBaseDatosSisnet()
								.verificarGrupoInformacionContieneCampoDocumento(grupoInformacion_local);

						consultaGruposInformacionMultiples_local = mc
								.concatenarCadena(consultaGruposInformacionMultiples_local,
										dibujarConsultaGeneralGrupoInformacion(grupoInformacion_local,
												pValorLlavePrimaria, existeDocumento_local, false,
												pNivelesAnterioresDirectorio));
					}

				}

			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaGrupoInformacion_local = null;
			iterator_local = null;
			grupoInformacion_local = null;
		}
		return consultaGruposInformacionMultiples_local;
	}

	public Pagina obtenerPaginaModificarRegistroPrincipal(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria)
			throws IOException {
		Pagina pagina_local = null;
		boolean existenCamposDocumentoConPlantilla_local = false;
		String eventosFormulario_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaCampo listaCampo_local = null;
		Formulario formulario_local = null;
		ListaCampo listaCampoValoresAnteriores_local = null;
		boolean huboErrorEventos_local = false;
		Campo campoPlantilla_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return pagina_local;
		}

		try {
			if (getManejadorSesion().obtenerExistenEventosEnEjecucion()) {
				pagina_local = obtenerPaginaErrorEjecucionEventos();
				return pagina_local;
			}

			if (getManejadorSesion().getSesion() == ConstantesGeneral.VALOR_NULO) {
				return pagina_local;
			}
			pagina_local = new Pagina();
			ListaBotones listaBotones_local = new ListaBotones();
			listaParametrosRedireccion_local = new ListaParametrosRedireccion();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(92));
	
			existenCamposDocumentoConPlantilla_local = getManejadorSesion().obtenerMotorAplicacion()
					.comprobarCamposDocumentosConPlantilla(pGrupoInformacion);
	
			if (!getManejadorPermisoUsuario()
					.verificarPermisoModificarRegistrosAplicacion(pGrupoInformacion.getAplicacion())
					&& !getManejadorPermisoUsuario().verificarExistenciaGrupoInformacionNoMultiplePermisoModificar(
							pGrupoInformacion.getAplicacion().getIdAplicacion())
					&& !getManejadorPermisoUsuario().verificarExistenciaCampoGrupoNoMultiplePermisoModificar(
							pGrupoInformacion.getAplicacion().getIdAplicacion())) {
	
				eventosFormulario_local = "return mensajeAutorizacionModificacion();";
			} else {
				eventosFormulario_local = "return (verificarCampos() && preguntaModificarRegistro());";
				if (existenCamposDocumentoConPlantilla_local
						&& !verificarDocumentoConPlantillaVacio(pGrupoInformacion,
								getManejadorSesion().obtenerValorLlavePrimaria())) {
					eventosFormulario_local = "return (verificarCampos() && recordarCambioPlantilla() && preguntaModificarRegistro());";
				}
			}
	
			listaCampo_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaCamposGruposInformacionNoMultiplesConArchivos(
							pGrupoInformacion.getAplicacion().getIdAplicacion());
	
			listaCampoValoresAnteriores_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerCopiaListaCamposGruposInformacionNoMultiplesConArchivos(
							pGrupoInformacion.getAplicacion().getIdAplicacion());
	
			if (existenCamposDocumentoConPlantilla_local) {
				campoPlantilla_local = cad.obtenerCampoPlantillaUtilizar(pGrupoInformacion);
				campoPlantilla_local.setEstiloCampo(getManejadorSesion().obtenerMotorAplicacion()
						.obtenerPrimerCampoTipoDocumento(pGrupoInformacion).getEstiloCampo());
	
				listaCampo_local.adicionar(campoPlantilla_local);
			}
			getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(pGrupoInformacion,
					listaCampo_local, pValorLlavePrimaria);
	
			getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(pGrupoInformacion,
					listaCampoValoresAnteriores_local, pValorLlavePrimaria);
	
			getManejadorSesion().asignarValoresAtributosSesionACampos(listaCampo_local);
	
			huboErrorEventos_local = (!mc.esCadenaVacia(getManejadorSesion().obtenerMensajeEventos())
					&& getManejadorSesion().obtenerTipoMensajeEventos() == 2);
	
			if (!getManejadorSesion().esRecargarPagina() && !huboErrorEventos_local) {
				getManejadorSesion().obtenerManejadorEventos().setNombreEvento("INICIOMODIFICARPRINCIPAL");
	
				getManejadorSesion().obtenerManejadorEventos()
						.setGrupoInformacion(getManejadorSesion().obtenerGrupoInformacionActual());
	
				getManejadorSesion().obtenerManejadorEventos()
						.setValorLlaveGrupoPrincipal(getManejadorSesion().obtenerValorLlavePrimariaAnterior());
	
				getManejadorSesion().obtenerManejadorEventos()
						.setValorLlaveGrupoInformacion(getManejadorSesion().obtenerValorLlavePrimaria());
	
				getManejadorSesion().obtenerManejadorEventos().setListaCampo(listaCampo_local);
				getManejadorSesion().obtenerManejadorEventos()
						.setListaCampoValoresAnteriores(listaCampoValoresAnteriores_local);
	
				getManejadorSesion().obtenerManejadorEventos().setRealizarAccionUsuario(true);
	
				getManejadorSesion().actualizarExistenEventosEnEjecucion(true);
				getManejadorSesion().obtenerManejadorEventos().ejecutarEvento();
				getManejadorSesion().actualizarExistenEventosEnEjecucion(
						!getManejadorSesion().obtenerManejadorEventos().haFinalizadoEjecucion());
	
				getManejadorSesion().actualizarMensajeEventos(
						getManejadorSesion().obtenerManejadorEventos().getMensajeEventos());
	
				getManejadorSesion().actualizarTipoMensajeEventos(
						getManejadorSesion().obtenerManejadorEventos().getTipoMensajeEventos());
	
				getManejadorSesion().obtenerManejadorEventos().setMensajeEventos("");
			}
	
			insertarMensajePagina(pagina_local);
	
			formulario_local = new Formulario();
			formulario_local.setNombre("formularioIncluir");
			formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
			formulario_local.setEventoOnSubmit(eventosFormulario_local);
			formulario_local.setEsMultipart(getAdministradorBaseDatosSisnet()
					.verificarGrupoInformacionContieneCampoArchivo(pGrupoInformacion));
	
			formulario_local.setListaCampo(listaCampo_local);
	
			asignarValoresListaCampoFormulario(formulario_local, true, true);
	
			pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(formulario_local.getListaCampo(),
					formulario_local.getNombre(), 0, false, false));
	
			pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(
					conformarEventosBody(formulario_local.getListaCampo(), formulario_local.getNombre())));
	
			pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
					getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));
	
			listaBotones_local.adicionar("aceptarmodificacion", true, "", "Aceptar Modificaci\u00f3n", "", 0,
					false);
	
			listaParametrosRedireccion_local.borrarElementos();
			listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));
	
			listaBotones_local.adicionar("cancelarmodificacion", false,
					" onClick=\"return (preguntaCancelarModificacion());\" ", "Cancelar Modificaci\u00f3n",
					listaParametrosRedireccion_local.concatenarParametros(), 0, false);
	
			formulario_local
					.setContenido(insertarFormulario(formulario_local, -1, listaBotones_local, true, 1, "center"));
	
			pagina_local.setContenidoFormulario(formulario_local.dibujar());
	
			pagina_local.setContenidoDatos(dibujarConsultaGruposInformacionMultiplesAplicacion(
					pGrupoInformacion.getAplicacion().getIdAplicacion(), pValorLlavePrimaria, 0));
	
			pagina_local.setFinCuerpoPagina(mc.concatenarCadena(insertarImagenProcesandoInformacion(false),
					getGeneradorComponentesHtml().cerrarBody()));
	
			getManejadorSesion().actualizarRecargarPagina(false);
			
		} catch (Throwable excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {

			listaCampo_local = null;
			formulario_local = null;
			listaParametrosRedireccion_local = null;
			listaCampoValoresAnteriores_local = null;
			campoPlantilla_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaConsultaGrupoInformacion(boolean pSalirAplicacion) throws IOException {
		Pagina pagina_local = null;
		String accion_local = null;
		ListaCampo listaCampo_local = null;
		GrupoInformacion grupoInformacion_local = null;

		try {
			listaCampo_local = new ListaCampo();
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				grupoInformacion_local = getManejadorSesion().obtenerGrupoInformacionActual();

				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, true));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));

				if (pSalirAplicacion) {
					accion_local = String.valueOf(96);
				} else {
					accion_local = String.valueOf(97);
					if (grupoInformacion_local.getIdGrupoInformacion() == 2) {
						accion_local = String.valueOf(61);
					}
				}

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(accion_local, 0), 0));

				insertarMensajePagina(pagina_local);

				pagina_local.setContenidoDatos(dibujarConsultaGeneralGrupoInformacion(grupoInformacion_local,
						getManejadorSesion().esConfiguracion()));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			accion_local = null;
			listaCampo_local = null;
			grupoInformacion_local = null;
		}

		return pagina_local;
	}

	public Pagina obtenerPaginaIncluirActuacion(GrupoInformacion pGrupoInformacion, boolean pEsModificacion)
			throws IOException {
		Pagina pagina_local = null;
		String nombreBotonAceptar_local = null;
		String descripcionBotonAceptar_local = null;
		String nombreBotonCancelar_local = null;
		String descripcionBotonCancelar_local = null;
		String eventoBotonCancelar_local = null;
		ListaCampo listaCampo_local = null;
		ListaBotones listaBotones_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		Formulario formulario_local = null;
		int plantillaUtilizar_local = 0;
		ManejadorPlantilla manejadorPlantilla_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return pagina_local;
		}

		try {
			if (getManejadorSesion().obtenerExistenEventosEnEjecucion()) {
				pagina_local = obtenerPaginaErrorEjecucionEventos();
				return pagina_local;
			}

			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				nombreBotonAceptar_local = "aceptarinclusion";
				descripcionBotonAceptar_local = "Aceptar Inclusi\u00f3n";
				nombreBotonCancelar_local = "cancelarinclusion";
				descripcionBotonCancelar_local = "Cancelar Inclusi\u00f3n";
				eventoBotonCancelar_local = " onClick=\"return (preguntaCancelarInclusion());\" ";
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(24));

				if (pEsModificacion) {
					nombreBotonAceptar_local = "aceptarmodificacion";
					descripcionBotonAceptar_local = "Aceptar Modificaci\u00f3n";
					nombreBotonCancelar_local = "cancelarmodificacion";
					descripcionBotonCancelar_local = "Cancelar Modificaci\u00f3n";
					eventoBotonCancelar_local = " onClick=\"return (preguntaCancelarModificacion());\" ";
				}

				listaCampo_local = getManejadorSesion().obtenerMotorAplicacion()
						.obtenerListaCamposTipoDocumentoGrupoInformacion(pGrupoInformacion);

				listaParametrosRedireccion_local
						.setRecursoDestino(mc.concatenarCadena(mc.complementarDirectorio(2), "administradorServlet"));

				plantillaUtilizar_local = getManejadorSesion().obtenerPlantillaUtilizar();
				manejadorPlantilla_local = new ManejadorPlantilla();
				manejadorPlantilla_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
				manejadorPlantilla_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
				manejadorPlantilla_local.setMotorAplicacion(getManejadorSesion().obtenerMotorAplicacion());
				manejadorPlantilla_local
						.setRutaDescargaArchivos(
								(new URL(getManejadorRequest().obtenerURLAplicacion(),
										getManejadorRequest().obtenerRecursoAplicacion()
												+ getManejadorSesion().obtenerRutaDirectorioUsuarioActual()))
														.toString());

				manejadorPlantilla_local.setManejadorSesion(getManejadorSesion());
				manejadorPlantilla_local.asignarValorPlantillaCamposDocumento(listaCampo_local,
						getManejadorSesion().obtenerValorLlavePrimaria(),
						getManejadorSesion().obtenerValorLlavePrimariaAnterior(), plantillaUtilizar_local);

				if (pEsModificacion && plantillaUtilizar_local == 0) {
					getManejadorInformacionRecalculable().asignarValoresConsultaRegistroCampos(pGrupoInformacion,
							listaCampo_local, getManejadorSesion().obtenerValorLlavePrimaria());
				}

				if (pEsModificacion) {
					getManejadorSesion().obtenerManejadorEventos().setNombreEvento("INICIOMODIFICARDOCUMENTO "
							+ getManejadorSesion().obtenerGrupoInformacionActual().getDescripcionGrupoInformacion());

					getManejadorSesion().obtenerManejadorEventos()
							.setGrupoInformacion(getManejadorSesion().obtenerGrupoInformacionActual());

					getManejadorSesion().obtenerManejadorEventos()
							.setValorLlaveGrupoPrincipal(getManejadorSesion().obtenerValorLlavePrimariaAnterior());

					getManejadorSesion().obtenerManejadorEventos()
							.setValorLlaveGrupoInformacion(getManejadorSesion().obtenerValorLlavePrimaria());

					getManejadorSesion().obtenerManejadorEventos().setListaCampo(listaCampo_local);
					getManejadorSesion().obtenerManejadorEventos().setRealizarAccionUsuario(true);

					getManejadorSesion().actualizarExistenEventosEnEjecucion(true);
					getManejadorSesion().obtenerManejadorEventos().ejecutarEvento();
					getManejadorSesion().actualizarExistenEventosEnEjecucion(
							!getManejadorSesion().obtenerManejadorEventos().haFinalizadoEjecucion());

					getManejadorSesion().actualizarMensajeEventos(
							getManejadorSesion().obtenerManejadorEventos().getMensajeEventos());

					getManejadorSesion().actualizarTipoMensajeEventos(
							getManejadorSesion().obtenerManejadorEventos().getTipoMensajeEventos());

					getManejadorSesion().obtenerManejadorEventos().setMensajeEventos("");
				}

				getManejadorSesion().asignarValoresSesionCamposGrupoInformacion(listaCampo_local);

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit(" return preguntaModificarRegistro();");
				formulario_local.setListaCampo(listaCampo_local);

				insertarMensajePagina(pagina_local);

				asignarValoresListaCampoFormulario(formulario_local, pEsModificacion, false);

				pagina_local.setEncabezadoPagina(
						obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 2, true, false));

				pagina_local.setInicioCuerpoPagina(mc.concatenarCadena(getGeneradorComponentesHtml().abrirBody(""),
						insertarImagenProcesandoInformacion(true)));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 2), 2));

				listaBotones_local = new ListaBotones();
				listaBotones_local.adicionar(nombreBotonAceptar_local, true, "", descripcionBotonAceptar_local, "", 2,
						false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));

				listaBotones_local.adicionar(nombreBotonCancelar_local, false, eventoBotonCancelar_local,
						descripcionBotonCancelar_local, listaParametrosRedireccion_local.concatenarParametros(), 2,
						false);

				formulario_local.setContenido(
						insertarFormulario(formulario_local, -1, listaBotones_local, pEsModificacion, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());
				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			nombreBotonAceptar_local = null;
			descripcionBotonAceptar_local = null;
			nombreBotonCancelar_local = null;
			descripcionBotonCancelar_local = null;
			eventoBotonCancelar_local = null;
			listaCampo_local = null;
			listaBotones_local = null;
			listaParametrosRedireccion_local = null;
			formulario_local = null;
			manejadorPlantilla_local = null;
		}
		return pagina_local;
	}

	private String obtenerContenidoDocumento(Campo pCampo, int pValorEnlace, String pValorCampo) {
		String contenidoDocumento_local = "";
		String nombreCampo_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return contenidoDocumento_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return contenidoDocumento_local;
		}

		try {
			nombreCampo_local = pCampo.getNombreCampo();
			if (pCampo.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
				contenidoDocumento_local = pCampo.getValorCampo().toString();
			}
			if (getManejadorSesion().esRecargarPagina() && getManejadorSesion()
					.obtenerValorAtributoSesion(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
				contenidoDocumento_local = getManejadorSesion().obtenerValorAtributoSesion(nombreCampo_local)
						.toString();
			}

			if (pCampo.getEnlaceCampo().getDependienteEnlazado() != 0) {
				contenidoDocumento_local = getManejadorInformacionRecalculable().getManejadorCampoEnlazado()
						.obtenerValorDependienteEnlazado(pCampo.getEnlaceCampo().getCampoOrigenEnlace(), pValorEnlace,
								pValorCampo);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			nombreCampo_local = null;
		}
		return contenidoDocumento_local;
	}

	private String insertarCampoTipoDocumento(String pValorCampo, String pNombreCampo) {
		String campoTipoDocumento_local = "";
		String cadena_local = null;
		int longitudCadena_local = -1;
		int posicionFinal_local = -1;
		ListaCadenas listaCadenas_local = null;
		Iterator iterator_local = null;

		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return campoTipoDocumento_local;
		}
		if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
			return campoTipoDocumento_local;
		}

		try {
			campoTipoDocumento_local = "<script type=\"text/javascript\"> \nvar sBasePath = document.location.pathname.substring(0,document.location.pathname.lastIndexOf('editortexto')) ;\nvar sSkin ; \n var oFCKeditor = new FCKeditor( '"
					+ pNombreCampo + "' ) ; \n oFCKeditor.BasePath = sBasePath ;\n"
					+ "var sSkinPath = sBasePath + 'editor/skins/office2003/' ; \n oFCKeditor.Config['SkinPath'] = sSkinPath ;\n"
					+ " oFCKeditor.Config['PreloadImages'] = \n sSkinPath + 'images/toolbar.start.gif' + ';' + \n"
					+ "\n sSkinPath + 'images/toolbar.end.gif' + ';' + \n sSkinPath + 'images/toolbar.bg.gif' + ';' + \n"
					+ "\n sSkinPath + 'images/toolbar.buttonarrow.gif' ; \n" + "\n oFCKeditor.Value = ";

			listaCadenas_local = mc.fraccionarContenidoDocumento(pValorCampo);
			if (listaCadenas_local != ConstantesGeneral.VALOR_NULO) {
				iterator_local = listaCadenas_local.iterator();
				while (iterator_local.hasNext()) {
					cadena_local = "'";
					cadena_local = mc
							.borrarEspaciosLaterales(mc.concatenarCadena(cadena_local, (String) iterator_local.next()));
					longitudCadena_local = mc.obtenerLongitudCadena(cadena_local);
					posicionFinal_local = longitudCadena_local - mc.obtenerLongitudCadena("{L}");

					if (posicionFinal_local > 0) {
						cadena_local = mc.obtenerSubCadena(cadena_local, 0, posicionFinal_local);
						if (iterator_local.hasNext()) {
							cadena_local = mc.concatenarCadena(cadena_local, "' + \n");
						} else {
							cadena_local = mc.concatenarCadena(cadena_local, "';\n");
						}
						campoTipoDocumento_local = mc.concatenarCadena(campoTipoDocumento_local, cadena_local);
					}
				}
			}
			campoTipoDocumento_local = mc.concatenarCadena(campoTipoDocumento_local,
					"oFCKeditor.Create() ; \n </script>\n ");
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			cadena_local = null;
			listaCadenas_local = null;
			iterator_local = null;
		}
		return campoTipoDocumento_local;
	}

	private String obtenerEventoCambioMaestro(Campo pCampo) {
		String eventosCambioLista_local = "";
		String nombreCampoDependiente_local = null;
		ListaGeneral listaCamposDependientes_local = null;
		Iterator iterador_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return eventosCambioLista_local;
		}

		try {
			if (getAdministradorBaseDatosSisnet().verificarEsCampoMaestro(pCampo.getIdCampo())) {
				listaCamposDependientes_local = getAdministradorBaseDatosSisnet()
						.obtenerListaCamposDependientesCampoMaestro(pCampo);
				iterador_local = listaCamposDependientes_local.iterator();
				while (iterador_local.hasNext()) {
					nombreCampoDependiente_local = ((ItemLista) iterador_local.next()).getNombreItem();
					if (!mc.sonCadenasIguales(nombreCampoDependiente_local, "")) {
						eventosCambioLista_local = mc.concatenarCadena(eventosCambioLista_local,
								"cambiar" + nombreCampoDependiente_local + "(); ");
					}
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			nombreCampoDependiente_local = null;
			listaCamposDependientes_local = null;
			iterador_local = null;
		}
		return eventosCambioLista_local;
	}

	private String conformarEventoOnChange(ListaCadenas pListaCadenas) {
		String eventoOnChange_local = "";
		String cadena_local = null;
		Iterator iterator_local = null;

		if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
			return eventoOnChange_local;
		}
		try {
			eventoOnChange_local = " onChange=\"";
			iterator_local = pListaCadenas.iterator();
			while (iterator_local.hasNext()) {
				cadena_local = (String) iterator_local.next();
				eventoOnChange_local = mc.concatenarCadena(eventoOnChange_local, cadena_local);
			}

			eventoOnChange_local = mc.concatenarCadena(eventoOnChange_local, "\" ");
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			cadena_local = null;
			iterator_local = null;
		}
		return eventoOnChange_local;
	}

	private String insertarCampoTipoTabla(Campo pCampo, int pIdTabla, int pIdValorSeleccionado, String pEvento,
			ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones, boolean pEsCampoOculto,
			boolean pEsCampoCalculado) {
		String listaTipoTabla_local = "";
		ListaGeneral listaValoresTabla_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return listaTipoTabla_local;
		}
		if (pEvento == ConstantesGeneral.VALOR_NULO) {
			return listaTipoTabla_local;
		}
		if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
			return listaTipoTabla_local;
		}

		try {
			listaValoresTabla_local = getAdministradorBaseDatosAplicacion().obtenerListaValoresTabla(
					getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla).getNombreTabla(),
					pIdValorSeleccionado, pListaCadenas, pExcluirValores, pExistenRestricciones, false);

			listaTipoTabla_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo,
					listaValoresTabla_local, pEvento, pEsCampoOculto, pEsCampoCalculado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaValoresTabla_local = null;
		}
		return listaTipoTabla_local;
	}

	private String insertarCampoTipoTablaConsulta(int pIdTabla, int pIdValorSeleccionado, String pNombreCampo,
			String pEtiquetaCampo) {
		String listaTipoTabla_local = "";
		ListaGeneral listaValoresTabla_local = null;
		ListaCadenas listaCadenas_local = null;

		if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
			return listaTipoTabla_local;
		}
		if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO) {
			return listaTipoTabla_local;
		}

		try {
			listaCadenas_local = new ListaCadenas();
			listaValoresTabla_local = getAdministradorBaseDatosAplicacion().obtenerListaValoresTabla(
					getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla).getNombreTabla(),
					pIdValorSeleccionado, listaCadenas_local, false, false, true);

			listaTipoTabla_local = getGeneradorComponentesHtml().insertarCampoListaDesplegableConsulta(pEtiquetaCampo,
					pNombreCampo, listaValoresTabla_local, "left");
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaValoresTabla_local = null;
			listaCadenas_local = null;
		}
		return listaTipoTabla_local;
	}

	private ListaGeneral obtenerListaValoresDependientes(int pIdTabla, int pIdCampo, int pIdValorMaestro,
			int pIdValorSeleccionado, ListaCadenas pListaCadenas, boolean pExcluirValores,
			boolean pExistenRestricciones) {
		ListaGeneral listaValoresDependientes_local = null;
		ListaGeneral listaIdValoresDependientes_local = null;
		Iterator iterador_local = null;
		ItemLista itemLista_local = null;
		int valorItem_local = -1;
		String valorTabla_local = null;
		String nombreItemLista_local = null;
		String valorItemLista_local = null;
		boolean esSeleccionado_local = false;

		if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
			return listaIdValoresDependientes_local;
		}

		try {
			listaValoresDependientes_local = new ListaGeneral();
			listaValoresDependientes_local.adicionar("Escoja un valor", String.valueOf(0), (pIdValorSeleccionado == 0));

			listaIdValoresDependientes_local = getAdministradorBaseDatosSisnet()
					.obtenerListaIdValoresDetalleDeMaestro(pIdCampo, pIdValorMaestro, pIdValorSeleccionado);

			iterador_local = listaIdValoresDependientes_local.iterator();
			while (iterador_local.hasNext()) {
				itemLista_local = (ItemLista) iterador_local.next();
				valorItem_local = Integer.parseInt(itemLista_local.getValorItem());
				if (valorItem_local != 0) {
					valorTabla_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(
							getAdministradorBaseDatosSisnet().obtenerTablaPorId(pIdTabla).getNombreTabla(),
							valorItem_local);

					itemLista_local.setNombreItem(valorTabla_local);
					nombreItemLista_local = itemLista_local.getNombreItem();
					valorItemLista_local = itemLista_local.getValorItem();
					esSeleccionado_local = itemLista_local.esSeleccionado();
					if (pExistenRestricciones) {
						if (pExcluirValores) {
							if (!pListaCadenas.verificarExistenciaCadena(valorTabla_local)) {
								listaValoresDependientes_local.adicionar(nombreItemLista_local, valorItemLista_local,
										esSeleccionado_local);
							}
						} else if (pListaCadenas.verificarExistenciaCadena(valorTabla_local)) {
							listaValoresDependientes_local.adicionar(nombreItemLista_local, valorItemLista_local,
									esSeleccionado_local);
						}
					} else {

						listaValoresDependientes_local.adicionar(nombreItemLista_local, valorItemLista_local,
								esSeleccionado_local);
					}
				}
				itemLista_local.setSeleccionado((valorItem_local == pIdValorSeleccionado));
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaIdValoresDependientes_local = null;
			iterador_local = null;
			itemLista_local = null;
			valorTabla_local = null;
			nombreItemLista_local = null;
			valorItemLista_local = null;
		}

		return listaValoresDependientes_local;
	}

	private String conformarEventoHabilitarCampos(Campo pCampo) {
		String eventoHabilitarCampos_local = "";
		String nombreCampoHabilitado_local = null;
		ListaGeneral listaCamposHabilitados_local = null;
		Iterator iterador_local = null;
		ItemLista itemLista_local = null;

		try {
			if (getAdministradorBaseDatosSisnet().verificarEsCampoHabilitaOtros(pCampo.getIdCampo())) {
				listaCamposHabilitados_local = getAdministradorBaseDatosSisnet()
						.obtenerListaCamposHabilitadosPorCampo(pCampo.getIdCampo());
				iterador_local = listaCamposHabilitados_local.iterator();
				while (iterador_local.hasNext()) {
					itemLista_local = (ItemLista) iterador_local.next();
					nombreCampoHabilitado_local = itemLista_local.getNombreItem();
					if (getAdministradorBaseDatosSisnet().verificarCamposMismoGrupo(pCampo,
							getManejadorSesion().obtenerMotorAplicacion()
									.obtenerCampoPorId(Integer.parseInt(itemLista_local.getValorItem())))
							&& !mc.sonCadenasIguales(nombreCampoHabilitado_local, "")) {

						eventoHabilitarCampos_local = mc.concatenarCadena(eventoHabilitarCampos_local,
								"habilitar" + nombreCampoHabilitado_local + "(); ");
					}
				}

			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			nombreCampoHabilitado_local = null;
			listaCamposHabilitados_local = null;
			iterador_local = null;
			itemLista_local = null;
		}
		return eventoHabilitarCampos_local;
	}

	private String insertarCampoListaDependiente(ListaCampo pListaCampo, Campo pCampo, int pIdValorSeleccionado,
			String pEvento, ListaCadenas pListaCadenas, boolean pExcluirValores, boolean pExistenRestricciones,
			boolean pEsCampoOculto, boolean pEsCampoCalculado) {
		String insertarCampoListaDependiente_local = "";
		ListaGeneral listaValoresDependientes_local = null;
		int idValorMaestro_local = -1;

		if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarCampoListaDependiente_local;
		}
		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarCampoListaDependiente_local;
		}
		if (pEvento == ConstantesGeneral.VALOR_NULO) {
			return insertarCampoListaDependiente_local;
		}
		if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
			return insertarCampoListaDependiente_local;
		}

		try {
			idValorMaestro_local = getManejadorHabilitacionCampos().obtenerIdValorMaestro(pListaCampo, pCampo, true,
					getManejadorSesion().obtenerValorLlavePrimariaAnterior(),
					getManejadorSesion().obtenerValorLlavePrimaria(), false);

			listaValoresDependientes_local = obtenerListaValoresDependientes(
					Integer.parseInt(pCampo.getFormatoCampo().getTipoDato()), pCampo.getIdCampo(), idValorMaestro_local,
					pIdValorSeleccionado, pListaCadenas, pExcluirValores, pExistenRestricciones);

			insertarCampoListaDependiente_local = getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo,
					listaValoresDependientes_local, pEvento, pEsCampoOculto, pEsCampoCalculado);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaValoresDependientes_local = null;
		}
		return insertarCampoListaDependiente_local;
	}

	private String obtenerEventoOnBlur(Campo pCampo) {
		String eventoOnBlur_local = "";

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return eventoOnBlur_local;
		}

		try {
			eventoOnBlur_local = " onBlur=\"";
			if (pCampo.esTipoDatoNumerico()) {
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "colocarValorPorDefecto(");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "document.");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "formularioIncluir");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf('.'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, pCampo.getNombreCampo());
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(','));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local,
						String.valueOf(pCampo.esTipoDatoNumeroEntero()));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(','));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local,
						String.valueOf(pCampo.esTipoDatoNumericoBorrarCerosIzquierda()));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(')'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(';'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(' '));
			}
			if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "XX")) {
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "completarValorConCeros(");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "document.");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "formularioIncluir");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf('.'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, pCampo.getNombreCampo());
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(','));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local,
						String.valueOf(pCampo.getFormatoCampo().getLongitudCampo()));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(')'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(';'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(' '));
			}
			if (pCampo.esTipoDatoHora()) {
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, " validacionHoras(");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "document.");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "formularioIncluir");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf('.'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, pCampo.getNombreCampo() + ')');

				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(';'));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(' '));
			}
			if (!pCampo.esCampoCalculado()) {
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local,
						"ejecutarOperaciones(); ejecutarOperaciones(); ejecutarOperaciones();");
			}
			if (pCampo.esRecargarPantalla()) {
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(' '));
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "enviarFormulario()");
				eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, String.valueOf(';'));
			}
			eventoOnBlur_local = mc.concatenarCadena(eventoOnBlur_local, "\" ");
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return eventoOnBlur_local;
	}

	private String insertarCampoEnlazado(Campo pCampo, int pRegistroSeleccionado, boolean pOpcionDesconocido,
			String pEvento, ListaCadenas pListaRestricciones, boolean pExcluirValores, boolean pExistenRestricciones,
			boolean pEsCampoOculto, String pValorFiltradoPorCampoOrigen, boolean pEsInclusion,
			boolean pEsCampoCalculado) {
		String insertarLista_local = "";
		String condicionesFiltrado_local = null;
		ListaGeneral listaGeneralValores_local = null;
		Campo campoValorUnico_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return insertarLista_local;
		}
		if (pEvento == ConstantesGeneral.VALOR_NULO) {
			return insertarLista_local;
		}
		if (pListaRestricciones == ConstantesGeneral.VALOR_NULO) {
			return insertarLista_local;
		}

		try {
			if (!mc.verificarExistenciaSubCadena(pEvento, " disabled ")
					&& pCampo.getCalculoCampo().getCampoCalculado() != 1) {
				pEvento = mc.concatenarCadena(pEvento, " disabled ");
			}
			campoValorUnico_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerPrimerCampoValorUnicoAplicacion(pCampo.getEnlaceCampo().getEnlazado().getIdAplicacion());

			if (campoValorUnico_local != ConstantesGeneral.VALOR_NULO) {
				condicionesFiltrado_local = "";
				if (pCampo.esCampoFiltrado()) {
					condicionesFiltrado_local = getManejadorInformacionRecalculable()
							.conformarCondicionesFiltradoCampoEnlazado(pCampo, pValorFiltradoPorCampoOrigen,
									getManejadorSesion().obtenerUsuarioActual(), pRegistroSeleccionado, pEsInclusion,
									getManejadorSesion().obtenerIdSesion());
				}

				listaGeneralValores_local = getAdministradorBaseDatosAplicacion()
						.obtenerListaRegistrosPrimerCampoValorUnico(
								pCampo.getEnlaceCampo().getEnlazado().getNombreAplicacion(),
								campoValorUnico_local.getNombreCampo(), pRegistroSeleccionado, pOpcionDesconocido,
								pListaRestricciones, pExcluirValores, pExistenRestricciones, condicionesFiltrado_local,
								false, pCampo.esCampoFiltrado(), !pEsInclusion);

				insertarLista_local = mc.concatenarCadena(insertarLista_local,
						getGeneradorComponentesHtml().insertarCampoListaDesplegable(pCampo, listaGeneralValores_local,
								pEvento, pEsCampoOculto, pEsCampoCalculado));
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campoValorUnico_local = null;
			listaGeneralValores_local = null;
			condicionesFiltrado_local = null;
		}
		return insertarLista_local;
	}

	public Pagina obtenerPaginaIncluirValorTabla(boolean pEsModificacion) throws IOException {
		Pagina pagina_local = null;
		int idTabla_local = -1;
		int valorLlavePrimaria_local = -1;
		int idTablaActual_local = -1;
		String nombreBotonAceptar_local = null;
		String descripcionBotonAceptar_local = null;
		String nombreBotonCancelar_local = null;
		String descripcionBotonCancelar_local = null;
		String eventoBotonCancelar_local = null;
		String consultaSQL_local = null;
		String valorTabla_local = null;
		Tabla tabla_local = null;
		ListaBotones listaBotones_local = null;
		ListaCampo listaCampo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaCampo listacampoConsultaSQL = null;
		Formulario formulario_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				getGeneradorComponentesHtml().setAnchoCampos("300");
				idTablaActual_local = getManejadorSesion().obtenerTablaActual().getIdTabla();
				nombreBotonAceptar_local = "aceptarinclusion";
				descripcionBotonAceptar_local = "Aceptar Inclusi\u00f3n";
				nombreBotonCancelar_local = "cancelarinclusion";
				descripcionBotonCancelar_local = "Cancelar Inclusi\u00f3n";
				eventoBotonCancelar_local = " onClick=\"return (preguntaCancelarInclusion());\" ";
				listaBotones_local = new ListaBotones();
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				idTabla_local = 6;
				listaCampo_local = ap.obtenerCamposTabla(getManejadorRequest().obtenerNombreRecursoAplicacion(),
						idTabla_local);

				if (pEsModificacion) {
					nombreBotonAceptar_local = "aceptarmodificacion";
					descripcionBotonAceptar_local = "Aceptar Modificaci\u00f3n";
					nombreBotonCancelar_local = "cancelarmodificacion";
					descripcionBotonCancelar_local = "Cancelar Modificaci\u00f3n";
					eventoBotonCancelar_local = " onClick=\"return (preguntaCancelarModificacion());\" ";
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(74));

					tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(idTablaActual_local);
					consultaSQL_local = ca.consultaSQLSeleccionValorTabla(tabla_local.getNombreTabla(),
							getManejadorSesion().obtenerValorLlavePrimaria());

					listacampoConsultaSQL = asignarValoresConsultaACampos(consultaSQL_local, false);
					valorLlavePrimaria_local = Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo(
							ap.conformarNombreCampoLlavePrimaria(tabla_local.getNombreTabla()).toString()));

					valorTabla_local = listacampoConsultaSQL.obtenerValorCampo(tabla_local.getNombreTabla());
					listaCampo_local.asignarValorCampo("fldllaveprimariatabla",
							Integer.valueOf(valorLlavePrimaria_local));
					listaCampo_local.asignarValorCampo("fldvalortabla", valorTabla_local);
				} else {
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(73));
				}

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit("return verificarCampos();");
				formulario_local.setListaCampo(listaCampo_local);

				asignarValoresListaCampoFormulario(formulario_local, pEsModificacion, false);

				pagina_local.setEncabezadoPagina(
						obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml()
						.abrirBody(conformarEventosBody(listaCampo_local, "formularioIncluir")));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				insertarMensajePagina(pagina_local);

				listaBotones_local.adicionar(nombreBotonAceptar_local, true, "", descripcionBotonAceptar_local, "", 0,
						false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));

				listaBotones_local.adicionar(nombreBotonCancelar_local, false, eventoBotonCancelar_local,
						descripcionBotonCancelar_local, listaParametrosRedireccion_local.concatenarParametros(), 0,
						false);

				formulario_local.setContenido(
						insertarFormulario(formulario_local, -1, listaBotones_local, pEsModificacion, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());
				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			tabla_local = null;
			valorTabla_local = null;
			formulario_local = null;
			listaCampo_local = null;
			consultaSQL_local = null;
			listaBotones_local = null;
			listacampoConsultaSQL = null;
			nombreBotonAceptar_local = null;
			nombreBotonCancelar_local = null;
			eventoBotonCancelar_local = null;
			descripcionBotonAceptar_local = null;
			descripcionBotonCancelar_local = null;
			listaParametrosRedireccion_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaDependencias() throws IOException {
		Pagina pagina_local = null;
		int idTablaDepende_local = -1;
		int idTabla_local = -1;
		ListaCampo listaCampo_local = null;

		try {
			listaCampo_local = new ListaCampo();
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				idTablaDepende_local = getManejadorSesion().obtenerTablaDepende().getIdTabla();
				idTabla_local = getManejadorSesion().obtenerTablaActual().getIdTabla();
				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				pagina_local.setContenidoDatos(dibujarConsultaGeneralTablaDepende(idTabla_local, idTablaDepende_local,
						getManejadorSesion().obtenerValorLlavePrimariaAnterior()));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaCampo_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaIncluirValorDependiente(boolean pEsModificacion) throws IOException {
		Pagina pagina_local = null;
		int idTablaActual_local = -1;
		String nombreBotonAceptar_local = null;
		String descripcionBotonAceptar_local = null;
		String nombreBotonCancelar_local = null;
		String descripcionBotonCancelar_local = null;
		String consultaSQL_local = null;
		ListaBotones listaBotones_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaCampo listaCampo_local = null;
		ListaCampo listacampoConsultaSQL = null;
		Formulario formulario_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				getGeneradorComponentesHtml().setAnchoCampos("300");
				idTablaActual_local = getManejadorSesion().obtenerTablaActual().getIdTabla();
				nombreBotonAceptar_local = "aceptarinclusion";
				descripcionBotonAceptar_local = "Aceptar Inclusi\u00f3n";
				nombreBotonCancelar_local = "cancelarinclusion";
				descripcionBotonCancelar_local = "Cancelar Inclusi\u00f3n";
				listaBotones_local = new ListaBotones();
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaCampo_local = ap.obtenerCamposTabla(getManejadorRequest().obtenerNombreRecursoAplicacion(), 7);

				if (pEsModificacion) {
					nombreBotonAceptar_local = "aceptarmodificacion";
					descripcionBotonAceptar_local = "Aceptar Modificaci\u00f3n";
					nombreBotonCancelar_local = "cancelarmodificacion";
					descripcionBotonCancelar_local = "Cancelar Modificaci\u00f3n";
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(21));

					consultaSQL_local = ca
							.consultaSQLValorDependiente(getManejadorSesion().obtenerValorLlavePrimaria());
					listacampoConsultaSQL = asignarValoresConsultaACampos(consultaSQL_local, true);
					listaCampo_local.asignarValorCampo("fldidvalordependiente", Integer.valueOf(
							Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldidvalordependiente"))));

					listaCampo_local.asignarValorCampo("fldidcampo",
							Integer.valueOf(Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldidcampo"))));

					listaCampo_local.asignarValorCampo("fldidvalormaestro", Integer
							.valueOf(Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldidvalormaestro"))));

					listaCampo_local.asignarValorCampo("fldidvalordetalle", Integer
							.valueOf(Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldidvalordetalle"))));
				} else {

					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(20));
				}

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit("return verificarCampos();");
				formulario_local.setListaCampo(listaCampo_local);

				asignarValoresListaCampoFormulario(formulario_local, pEsModificacion, false);

				pagina_local.setEncabezadoPagina(
						obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml()
						.abrirBody(conformarEventosBody(listaCampo_local, "formularioIncluir")));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				insertarMensajePagina(pagina_local);

				listaBotones_local.adicionar(nombreBotonAceptar_local, true, "", descripcionBotonAceptar_local, "", 0,
						false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));

				listaBotones_local.adicionar(nombreBotonCancelar_local, false, "", descripcionBotonCancelar_local,
						listaParametrosRedireccion_local.concatenarParametros(), 0, false);

				formulario_local.setContenido(insertarFormulario(formulario_local, idTablaActual_local,
						listaBotones_local, pEsModificacion, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());
				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			formulario_local = null;
			consultaSQL_local = null;
			listaCampo_local = null;
			listaBotones_local = null;
			listacampoConsultaSQL = null;
			nombreBotonAceptar_local = null;
			nombreBotonCancelar_local = null;
			descripcionBotonAceptar_local = null;
			descripcionBotonCancelar_local = null;
			listaParametrosRedireccion_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaDependienteHabilitacion() throws IOException {
		Pagina pagina_local = null;
		int idTablaDepende_local = -1;
		ListaCampo listaCampo_local = null;

		try {
			listaCampo_local = new ListaCampo();
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				idTablaDepende_local = getManejadorSesion().obtenerTablaDepende().getIdTabla();
				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				pagina_local.setContenidoDatos(dibujarConsultaGeneralDependienteHabilitacion(idTablaDepende_local,
						getManejadorSesion().obtenerValorLlavePrimariaAnterior()));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaCampo_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaIncluirDependienteHabilitacion(boolean pEsModificacion) throws IOException {
		Pagina pagina_local = null;
		String nombreBotonAceptar_local = null;
		String descripcionBotonAceptar_local = null;
		String nombreBotonCancelar_local = null;
		String descripcionBotonCancelar_local = null;
		String consultaSQL_local = null;
		ListaBotones listaBotones_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaCampo listaCampo_local = null;
		Formulario formulario_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				nombreBotonAceptar_local = "aceptarinclusion";
				descripcionBotonAceptar_local = "Aceptar Inclusi\u00f3n";
				nombreBotonCancelar_local = "cancelarinclusion";
				descripcionBotonCancelar_local = "Cancelar Inclusi\u00f3n";
				listaBotones_local = new ListaBotones();
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaCampo_local = ap.obtenerCamposTabla(getManejadorRequest().obtenerNombreRecursoAplicacion(), 8);

				if (pEsModificacion) {
					nombreBotonAceptar_local = "aceptarmodificacion";
					descripcionBotonAceptar_local = "Aceptar Modificaci\u00f3n";
					nombreBotonCancelar_local = "cancelarmodificacion";
					descripcionBotonCancelar_local = "Cancelar Modificaci\u00f3n";
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(33));

					consultaSQL_local = ca.consultaSQLHabilitacion(getManejadorSesion().obtenerValorLlavePrimaria());

					ListaCampo listacampoConsultaSQL = asignarValoresConsultaACampos(consultaSQL_local, true);
					listaCampo_local.asignarValorCampo("fldiddependientehabilitacion", Integer.valueOf(
							Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldiddependientehabilitacion"))));

					listaCampo_local.asignarValorCampo("fldidcampo",
							Integer.valueOf(Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldidcampo"))));

					listaCampo_local.asignarValorCampo("fldidvalormaestro", Integer
							.valueOf(Integer.parseInt(listacampoConsultaSQL.obtenerValorCampo("fldidvalormaestro"))));

					listaCampo_local.asignarValorCampo("fldhabilitacion",
							Boolean.valueOf((listacampoConsultaSQL
									.obtenerValorCampo("fldhabilitacion") != ConstantesGeneral.VALOR_NULO
									&& mc.sonCadenasIguales(listacampoConsultaSQL.obtenerValorCampo("fldhabilitacion"),
											"true"))));

				} else {

					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(32));
				}

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit("return verificarCampos();");
				formulario_local.setListaCampo(listaCampo_local);

				asignarValoresListaCampoFormulario(formulario_local, pEsModificacion, false);

				pagina_local.setEncabezadoPagina(
						obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml()
						.abrirBody(conformarEventosBody(listaCampo_local, "formularioIncluir")));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				insertarMensajePagina(pagina_local);

				listaBotones_local.adicionar(nombreBotonAceptar_local, true, "", descripcionBotonAceptar_local, "", 0,
						false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));

				listaBotones_local.adicionar(nombreBotonCancelar_local, false, "", descripcionBotonCancelar_local,
						listaParametrosRedireccion_local.concatenarParametros(), 0, false);

				formulario_local.setContenido(
						insertarFormulario(formulario_local, -1, listaBotones_local, pEsModificacion, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());
				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			formulario_local = null;
			consultaSQL_local = null;
			listaCampo_local = null;
			listaBotones_local = null;
			nombreBotonAceptar_local = null;
			nombreBotonCancelar_local = null;
			descripcionBotonAceptar_local = null;
			descripcionBotonCancelar_local = null;
			listaParametrosRedireccion_local = null;
		}
		return pagina_local;
	}

	private String insertarCeldaDescargarArchivo(Campo pCampo, int pValorLlavePrimaria, boolean pColorGris) {
		String celdaDescargarArchivo_local = "";
		String destinoDescarga_local = null;
		String nombreArchivo_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return celdaDescargarArchivo_local;
		}

		try {
			nombreArchivo_local = getAdministradorBaseDatosAplicacion().obtenerNombreArchivo(pValorLlavePrimaria,
					pCampo);

			if (!mc.esCadenaVacia(nombreArchivo_local)) {
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(60));

				listaParametrosRedireccion_local.adicionar("grupoinformacionactual",
						String.valueOf(pCampo.getGrupoInformacion().getIdGrupoInformacion()));

				listaParametrosRedireccion_local.adicionar("valorllaveprimaria", String.valueOf(pValorLlavePrimaria));

				listaParametrosRedireccion_local.adicionar("campoArchivo", String.valueOf(pCampo.getIdCampo()));

				destinoDescarga_local = listaParametrosRedireccion_local.concatenarParametros();
				nombreArchivo_local = getAdministradorBaseDatosAplicacion().obtenerNombreArchivo(pValorLlavePrimaria,
						pCampo);

				celdaDescargarArchivo_local = getGeneradorComponentesHtml().insertarCeldaDescargarArchivo(pCampo,
						destinoDescarga_local, nombreArchivo_local, 0, false, pColorGris, 57);
			} else {

				celdaDescargarArchivo_local = getGeneradorComponentesHtml().crearCeldaContenidoImagen("", "justify",
						String.valueOf(57), pColorGris);
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			nombreArchivo_local = null;
			destinoDescarga_local = null;
			listaParametrosRedireccion_local = null;
		}

		return celdaDescargarArchivo_local;
	}

	private String dibujarDatosGrupoInformacionAplicacion(GrupoInformacion pGrupoInformacion, boolean pIncluirBorrar,
			boolean pExisteDecrementarIncrementarPosicion, int pValorLlavePrimaria, boolean pEsDocumento,
			boolean pEsSoloConsulta, int pNivelesAnterioresDirectorio) {
		String datosGrupoInformacion_local = "";
		int anchoTabla_local = -1;
		boolean alternar_local = false;
		String nombreLlavePrimaria_local = null;
		String alineacionContenido_local = null;
		String consultaSQL_local = null;
		int valorLlavePrimaria_local = -1;
		String nombreCampo_local = null;
		String tipoDato_local = null;
		int anchoColumna_local = 0;
		String contenido_local = null;
		Tabla tabla_local = null;
		String destinoIncluir_local = null;
		String destinoBorrar_local = null;
		Boton boton_local = null;
		Iterator iterador_local = null;
		Iterator iteratorCampos_local = null;
		Campo campo_local = null;
		ResultSet resultSet_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaCampo listaCampo_local = null;
		ListaCampo listaCamposDocumento_local = null;
		ListaParametrosRedireccion listaParametrosRedireccionModificar_local = null;
		ListaParametrosRedireccion listaParametrosRedireccionBorrar_local = null;
		Campo campoEnlazado_local = null;
		ListaCadenas listaNombresCampos_local = null;
		String eventosBoton_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return datosGrupoInformacion_local;
		}

		try {
			nombreLlavePrimaria_local = "";
			alineacionContenido_local = "left";
			anchoTabla_local = calcularAnchoGrupoInformacionMultipleAplicacion(pGrupoInformacion, pIncluirBorrar,
					pExisteDecrementarIncrementarPosicion, false);

			datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
					getGeneradorComponentesHtml().abrirTabla("", String.valueOf(anchoTabla_local), "center"));

			datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
					getGeneradorComponentesHtml().abrirFilaTabla());
			if (pIncluirBorrar) {
				if (getManejadorPermisoUsuario().verificarPermisoIncluirGrupoInformacion(pGrupoInformacion)) {
					listaParametrosRedireccion_local = new ListaParametrosRedireccion();
					listaParametrosRedireccion_local.adicionar("accion", String.valueOf(86));

					listaParametrosRedireccion_local.adicionar("grupoinformacionactual",
							String.valueOf(pGrupoInformacion.getIdGrupoInformacion()));

					if (pEsDocumento) {
						listaParametrosRedireccion_local.adicionar("esdocumento", String.valueOf(true));
					}

					destinoIncluir_local = listaParametrosRedireccion_local.concatenarParametros();
				} else {
					destinoIncluir_local = "javascript: mensajeAutorizacionInclusion();";
				}

				eventosBoton_local = "";
				if (!mc.sonCadenasIguales(destinoIncluir_local, "javascript: mensajeAutorizacionInclusion();")) {
					eventosBoton_local = "onClick=\"mostrarMensajeProcesandoInformacion();\" ";
				}

				boton_local = new Boton("incluir", false, eventosBoton_local, "Incluir Registro", destinoIncluir_local,
						0, false);

				datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
						getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
								getGeneradorComponentesHtml().insertarBoton(boton_local, pNivelesAnterioresDirectorio),
								30));
			}

			listaCampo_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaCamposVisiblesGrupoInformacionMultipleConArchivos(
							pGrupoInformacion.getIdGrupoInformacion());

			listaCamposDocumento_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaCamposTipoDocumentoGrupoInformacion(pGrupoInformacion);

			nombreLlavePrimaria_local = getAdministradorBaseDatosSisnet()
					.obtenerNombreLlavePrimariaGrupoInformacion(pGrupoInformacion, false);

			listaCampo_local.concatenarListaCampo(listaCamposDocumento_local);
			iterador_local = listaCampo_local.iterator();
			while (iterador_local.hasNext()) {
				campo_local = (Campo) iterador_local.next();
				if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
					datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
							getGeneradorComponentesHtml().crearCeldaEncabezadoAncho(
									mc.darFormatoTitulo(campo_local.getEtiquetaCampo()),
									calcularAnchoCampo(campo_local)));
				}
			}

			datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
					getGeneradorComponentesHtml().cerrarFilaTabla());

			listaNombresCampos_local = getAdministradorBaseDatosSisnet()
					.obtenerListaNombresCamposVisiblesGrupoInformacionMultiple(
							pGrupoInformacion.getIdGrupoInformacion());

			consultaSQL_local = getAdministradorBaseDatosSisnet()
					.conformarConsultaSQLSeleccionGrupoInformacionAplicacion(pGrupoInformacion,
							listaNombresCampos_local, pValorLlavePrimaria);

			resultSet_local = getResultadoConsultaSQL().obtenerResultadoConsultaAplicacion(consultaSQL_local);
			if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
				alternar_local = false;
				while (resultSet_local.next()) {
					valorLlavePrimaria_local = resultSet_local.getInt(nombreLlavePrimaria_local);
					listaParametrosRedireccionModificar_local = new ListaParametrosRedireccion();
					listaParametrosRedireccionModificar_local.adicionar("accion", String.valueOf(88));

					if (pEsDocumento) {
						listaParametrosRedireccionModificar_local.adicionar("esdocumento", String.valueOf(true));
					}

					datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
							getGeneradorComponentesHtml().abrirFilaTabla());

					if (pIncluirBorrar) {
						if (getManejadorPermisoUsuario().verificarPermisoBorrarGrupoInformacion(pGrupoInformacion)) {
							listaParametrosRedireccionBorrar_local = new ListaParametrosRedireccion();
							listaParametrosRedireccionBorrar_local.adicionar("accion", String.valueOf(66));

							listaParametrosRedireccionBorrar_local.adicionar("grupoinformacionactual",
									String.valueOf(pGrupoInformacion.getIdGrupoInformacion()));

							listaParametrosRedireccionBorrar_local.adicionar("valorllaveprimaria",
									String.valueOf(valorLlavePrimaria_local));

							destinoBorrar_local = listaParametrosRedireccionBorrar_local.concatenarParametros();
						} else {
							destinoBorrar_local = "javascript: mensajeAutorizacionBorrar();";
						}
						datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
								getGeneradorComponentesHtml().insertarCeldaBorrarRegistro(destinoBorrar_local,
										pNivelesAnterioresDirectorio, alternar_local,
										!mc.sonCadenasIguales(destinoBorrar_local,
												"javascript: mensajeAutorizacionBorrar();")));
					}

					iteratorCampos_local = listaCampo_local.iterator();
					while (iteratorCampos_local.hasNext()) {
						campo_local = (Campo) iteratorCampos_local.next();
						campoEnlazado_local = null;
						if (getManejadorPermisoUsuario().verificarPermisoVerCampo(campo_local)) {
							nombreCampo_local = campo_local.getNombreCampo();
							tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
							anchoColumna_local = campo_local.getAnchoColumna();
							alineacionContenido_local = campo_local.obtenerAlineacionContenidoCelda();
							if (!mc.sonCadenasIguales(campo_local.getEtiquetaCampo(), "")) {
								contenido_local = "";
								if (!campo_local.esTipoDatoDocumento() && !campo_local.esTipoDatoArchivo()
										&& resultSet_local
												.getObject(nombreCampo_local) != ConstantesGeneral.VALOR_NULO) {
									contenido_local = resultSet_local.getObject(nombreCampo_local).toString();
									if (campo_local.esCampoEnlazado()) {
										campoEnlazado_local = getManejadorSesion().obtenerMotorAplicacion()
												.obtenerPrimerCampoValorUnicoAplicacion(
														campo_local.getEnlaceCampo().getEnlazado().getIdAplicacion());

										contenido_local = getManejadorInformacionRecalculable()
												.getManejadorCampoEnlazado()
												.obtenerValorEnlace(campo_local.getEnlaceCampo().getEnlazado(),
														Integer.parseInt(contenido_local));
									}

									if (campoEnlazado_local != ConstantesGeneral.VALOR_NULO) {
										if (mc.esCadenaNumerica(contenido_local,
												campoEnlazado_local.esTipoDatoNumeroEntero())) {
											campoEnlazado_local.setValorCampo(contenido_local);
											contenido_local = campoEnlazado_local.aplicarFormatoNumeros();
											campoEnlazado_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
											alineacionContenido_local = "right";
										} else {
											alineacionContenido_local = "left";
										}

									} else if (mc.sonCadenasIguales(tipoDato_local, "XX")) {
										contenido_local = mc.completarCadena(contenido_local, false, '0',
												campo_local.getFormatoCampo().getLongitudCampo());

									} else if (mc.esCadenaNumerica(contenido_local,
											campo_local.esTipoDatoNumeroEntero())) {
										campo_local.setValorCampo(contenido_local);
										contenido_local = campo_local.aplicarFormatoNumeros();
										campo_local.setValorCampo(ConstantesGeneral.VALOR_NULO);
									}

									if (mc.sonCadenasIguales(tipoDato_local, "H")) {
										contenido_local = ap.obtenerHoraConFormato("H24", contenido_local);
									}
								}

								if (campo_local.getRestriccionCampo().esLlaveForanea()) {
									contenido_local = getExternalTableValue(tipoDato_local, contenido_local);
								}

								listaParametrosRedireccionModificar_local.adicionar("grupoinformacionactual",
										String.valueOf(pGrupoInformacion.getIdGrupoInformacion()));

								listaParametrosRedireccionModificar_local.adicionar("valorllaveprimaria",
										String.valueOf(valorLlavePrimaria_local));

								if (!campo_local.esTipoDatoDocumento() && !campo_local.esTipoDatoArchivo()) {
									if (!pEsSoloConsulta) {
										datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
												getGeneradorComponentesHtml().crearCeldaHipervinculo(contenido_local,
														alineacionContenido_local,
														listaParametrosRedireccionModificar_local
																.concatenarParametros(),
														anchoColumna_local, alternar_local,
														"onClick=\"mostrarMensajeProcesandoInformacion();\" ",
														campo_local.esTipoDatoParrafo()));

										continue;
									}

									datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
											getGeneradorComponentesHtml().crearCeldaSoloLectura(contenido_local,
													alineacionContenido_local, alternar_local));

									continue;
								}

								if (campo_local.esTipoDatoDocumento()) {
									listaParametrosRedireccionModificar_local.actualizarParametroRedireccion("accion",
											String.valueOf(23));

									datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
											getGeneradorComponentesHtml().insertarCeldaVerDocumento(
													listaParametrosRedireccionModificar_local.concatenarParametros(),
													pNivelesAnterioresDirectorio, alternar_local));

									continue;
								}

								if (campo_local.esTipoDatoArchivo() && campo_local.esImagen()) {
									contenido_local = descargarArchivo(valorLlavePrimaria_local, campo_local);
									if (!mc.esCadenaVacia(contenido_local)) {
										datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
												getGeneradorComponentesHtml().crearCeldaHipervinculoImagen(
														contenido_local, "center",
														listaParametrosRedireccionModificar_local
																.concatenarParametros(),
														anchoColumna_local,
														campo_local.getAltoImagenPantallaPresentacion(), false,
														"onClick=\"mostrarMensajeProcesandoInformacion();\" ", ""));

										continue;
									}

									datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
											getGeneradorComponentesHtml().crearCeldaHipervinculo("", "center", "",
													anchoColumna_local, false, "", false));

									continue;
								}

								datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
										insertarCeldaDescargarArchivo(campo_local, valorLlavePrimaria_local,
												alternar_local));
							}
						}
					}

					datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
							getGeneradorComponentesHtml().cerrarFilaTabla());

					alternar_local = !alternar_local;
				}
			}
			datosGrupoInformacion_local = mc.concatenarCadena(datosGrupoInformacion_local,
					getGeneradorComponentesHtml().cerrarTabla());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			tabla_local = null;
			boton_local = null;
			campo_local = null;
			iterador_local = null;
			tipoDato_local = null;
			resultSet_local = null;
			contenido_local = null;
			listaCampo_local = null;
			consultaSQL_local = null;
			nombreCampo_local = null;
			campoEnlazado_local = null;
			iteratorCampos_local = null;
			nombreLlavePrimaria_local = null;
			alineacionContenido_local = null;
			listaCamposDocumento_local = null;
			listaParametrosRedireccion_local = null;
			listaParametrosRedireccionBorrar_local = null;
			listaParametrosRedireccionModificar_local = null;
			listaNombresCampos_local = null;
			eventosBoton_local = null;
		}
		return datosGrupoInformacion_local;
	}

	private String dibujarConsultaGeneralGrupoInformacion(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria,
			boolean pEsDocumento, boolean pEsSoloConsulta, int pNivelesAnterioresDirectorio) {
		String consultaGeneralGrupoInformacion_local = "";
		boolean incluirBorrar_local = false;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return consultaGeneralGrupoInformacion_local;
		}

		try {
			if (getManejadorPermisoUsuario().verificarPermisoVerGrupoInformacion(pGrupoInformacion)
					|| getManejadorPermisoUsuario()
							.verificarExistenciaCampoPermisoVer(pGrupoInformacion.getIdGrupoInformacion())) {
				consultaGeneralGrupoInformacion_local = mc.concatenarCadena(consultaGeneralGrupoInformacion_local,
						getGeneradorComponentesHtml()
								.insertarTituloTipoEspecial(pGrupoInformacion.getDescripcionGrupoInformacion()));

				if (pGrupoInformacion.getAplicacion().esActualizarInformacionEnlazada()) {
					getManejadorInformacionRecalculable()
							.actualizarInformacionRecalculableGrupoInformacion(pGrupoInformacion, pValorLlavePrimaria);
				}

				incluirBorrar_local = !pEsSoloConsulta;
				consultaGeneralGrupoInformacion_local = mc.concatenarCadena(consultaGeneralGrupoInformacion_local,
						dibujarDatosGrupoInformacionAplicacion(pGrupoInformacion, incluirBorrar_local, false,
								pValorLlavePrimaria, pEsDocumento, pEsSoloConsulta, pNivelesAnterioresDirectorio));
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return consultaGeneralGrupoInformacion_local;
	}

	private String dibujarTablaVinculosGruposInformacionMultiples(int pIdAplicacion) {
		String tablaVinculosGruposInformacionMultiples_local = "";
		int cantidadGrupos_local = -1;
		int numeroFilas_local = -1;
		int contador_local = -1;
		ListaGrupoInformacion listaGrupoInformacion_local = null;
		Iterator iterador_local = null;
		GrupoInformacion grupoInformacion_local = null;

		try {
			listaGrupoInformacion_local = getManejadorSesion().obtenerMotorAplicacion()
					.obtenerListaGruposInformacionMultiplesAplicacion(pIdAplicacion);

			iterador_local = listaGrupoInformacion_local.iterator();
			tablaVinculosGruposInformacionMultiples_local = getGeneradorComponentesHtml().abrirTablaMarca("TABLA", "",
					"center");

			cantidadGrupos_local = listaGrupoInformacion_local.contarElementos();
			numeroFilas_local = cantidadGrupos_local / 4;
			if (cantidadGrupos_local % 4 > 0) {
				numeroFilas_local++;
			}
			for (int i = 0; i < numeroFilas_local; i++) {
				tablaVinculosGruposInformacionMultiples_local = mc.concatenarCadena(
						tablaVinculosGruposInformacionMultiples_local, getGeneradorComponentesHtml().abrirFilaTabla());

				contador_local = 0;
				while (iterador_local.hasNext() && contador_local < 4) {
					grupoInformacion_local = (GrupoInformacion) iterador_local.next();
					if (grupoInformacion_local.esMostrarDetalle()) {
						tablaVinculosGruposInformacionMultiples_local = mc.concatenarCadena(
								tablaVinculosGruposInformacionMultiples_local,
								getGeneradorComponentesHtml()
										.crearCeldaAnchoMarca(
												getGeneradorComponentesHtml().insertarVinculoMarca(
														mc.darFormatoTitulo(grupoInformacion_local
																.getDescripcionGrupoInformacion()),
														'#' + mc.convertirCadenaFormatoNombre(grupoInformacion_local
																.getDescripcionGrupoInformacion())),
												"200"));

						contador_local++;
					}
				}
				tablaVinculosGruposInformacionMultiples_local = mc.concatenarCadena(
						tablaVinculosGruposInformacionMultiples_local, getGeneradorComponentesHtml().cerrarFilaTabla());
			}

			tablaVinculosGruposInformacionMultiples_local = mc.concatenarCadena(
					tablaVinculosGruposInformacionMultiples_local, getGeneradorComponentesHtml().cerrarTabla());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			iterador_local = null;
			grupoInformacion_local = null;
			listaGrupoInformacion_local = null;
		}
		return tablaVinculosGruposInformacionMultiples_local;
	}

	public Pagina obtenerPaginaConsultaGeneral(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria)
			throws IOException {
		Pagina pagina_local = null;
		ListaCampo listaCampo_local = null;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return pagina_local;
		}

		try {
			pagina_local = new Pagina();
			listaCampo_local = new ListaCampo();
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));
				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(97), 0), 0));

				insertarMensajePagina(pagina_local);

				pagina_local.setContenidoDatos(
						dibujarConsultaGeneralGrupoInformacion(pGrupoInformacion, pValorLlavePrimaria, false, true, 0));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaCampo_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaExportacion(boolean pEsReporte) throws IOException {
		Pagina pagina_local = null;
		String recurso_local = null;
		String nombreArchivo_local = null;
		ListaBotones listaBotones_local = null;
		ListaCampo listaCampo_local = null;
		StringBuffer ruta_local = null;
		URL url_local = null;
		URL urlDescarga_local = null;
		ReporteExcel reporteExcel_local = null;
		Aplicacion aplicacionActual_local = null;
		ManejadorArchivos manejadorArchivos_local = null;
		String rutaArchivo_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				aplicacionActual_local = getManejadorSesion().obtenerAplicacionActual();
				listaBotones_local = new ListaBotones();
				listaCampo_local = new ListaCampo();

				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, false));

				pagina_local.setInicioCuerpoPagina(
						getGeneradorComponentesHtml().abrirBody(conformarEventosBody(listaCampo_local, "")));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				if (pEsReporte) {
					reporteExcel_local = new ReporteExcel(aplicacionActual_local,
							getManejadorSesion().obtenerValorLlavePrimaria(),
							getManejadorSesion().obtenerUsuarioActual(), mf.obtenerFechaActualSistema(true),
							ap.obtenerHoraActualSistema(), getManejadorSesion().obtenerIdSesion());

					reporteExcel_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
					reporteExcel_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
					reporteExcel_local.setMotorAplicacion(getManejadorSesion().obtenerMotorAplicacion());
					reporteExcel_local.cargarReporte();

					nombreArchivo_local = mc.concatenarCadena(reporteExcel_local.getNombreReporte(),
							String.valueOf('-'));
				} else {

					nombreArchivo_local = "";
				}

				ruta_local = getManejadorRequest().getRequest().getRequestURL();
				recurso_local = getManejadorRequest().getRequest().getContextPath();
				url_local = new URL(ruta_local.toString());
				urlDescarga_local = new URL(url_local,
						recurso_local + "/administrador/exportacion/" + nombreArchivo_local
								+ getManejadorSesion().obtenerUsuarioActual().getNombreUsuario() + ".xls");

				rutaArchivo_local = "/administrador/exportacion/" + nombreArchivo_local
						+ getManejadorSesion().obtenerUsuarioActual().getNombreUsuario() + ".xls";

				rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(rutaArchivo_local);

				manejadorArchivos_local = new ManejadorArchivos();
				if (manejadorArchivos_local.existeArchivo(rutaArchivo_local)) {
					pagina_local.setMensaje(getGeneradorComponentesHtml()
							.insertarMensaje(ConstantesMensajesAplicacion.const_DescripcionMensajeExportacion));

					listaBotones_local.adicionar("descargar", false, "", "Descargar Archivo",
							urlDescarga_local.toString(), 0, true);

					pagina_local.setBotonesPrincipales(
							getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));
				} else {

					pagina_local.setMensaje(getGeneradorComponentesHtml().insertarMensajeError(
							ConstantesMensajesAplicacion.const_DescripcionMensajeErrorAlCrearArchivo));
				}

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {
			url_local = null;
			ruta_local = null;
			recurso_local = null;
			listaCampo_local = null;
			urlDescarga_local = null;
			listaBotones_local = null;
			aplicacionActual_local = null;
			manejadorArchivos_local = null;
			rutaArchivo_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaImportacion() throws IOException {
		Pagina pagina_local = null;
		String recurso_local = null;
		String archivoErrores_local = null;
		Formulario formulario_local = null;
		ListaCampo listaCampo_local = null;
		ListaBotones listaBotones_local = null;
		ListaParametrosRedireccion listaParametrosRedireccion_local = null;
		ListaCadenas listaCadenas_local = null;
		ManejadorArchivos manejadorArchivos_local = null;
		StringBuffer ruta_local = null;
		URL url_local = null;
		URL urlDescarga_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaCampo_local = ap.obtenerCamposImportacion(getManejadorRequest().obtenerNombreRecursoAplicacion());
				getManejadorSesion().asignarValoresAtributosSesionACampos(listaCampo_local);
				listaBotones_local = new ListaBotones();
				listaParametrosRedireccion_local = new ListaParametrosRedireccion();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(41));

				formulario_local = new Formulario();
				formulario_local.setNombre("formularioIncluir");
				formulario_local.setAction(listaParametrosRedireccion_local.concatenarParametros());
				formulario_local.setEventoOnSubmit("return verificarCampos();");
				formulario_local.setEsMultipart(true);
				formulario_local.setListaCampo(listaCampo_local);

				asignarValoresListaCampoFormulario(formulario_local, false, false);

				pagina_local.setEncabezadoPagina(
						obtenerBloqueHeadPagina(listaCampo_local, formulario_local.getNombre(), 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml()
						.abrirBody(conformarEventosBody(listaCampo_local, "formularioIncluir")));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(95), 0), 0));

				insertarMensajePagina(pagina_local);

				listaBotones_local.adicionar("aceptar", true, "", "Aceptar", "", 0, false);

				listaParametrosRedireccion_local.borrarElementos();
				listaParametrosRedireccion_local.adicionar("accion", String.valueOf(95));

				listaBotones_local.adicionar("cancelar", false, "", "Cancelar",
						listaParametrosRedireccion_local.concatenarParametros(), 0, false);

				formulario_local
						.setContenido(insertarFormulario(formulario_local, -1, listaBotones_local, false, 1, "center"));

				pagina_local.setContenidoFormulario(formulario_local.dibujar());

				ruta_local = getManejadorRequest().getRequest().getRequestURL();
				recurso_local = getManejadorRequest().getRequest().getContextPath();
				url_local = new URL(ruta_local.toString());
				archivoErrores_local = "/administrador/importacion/importacion"
						+ getManejadorSesion().obtenerUsuarioActual().getNombreUsuario() + ".rtf";

				manejadorArchivos_local = new ManejadorArchivos();
				listaCadenas_local = manejadorArchivos_local
						.leerArchivo(getManejadorSesion().obtenerRutaRealArchivoSesion(archivoErrores_local));

				if (listaCadenas_local.contarElementos() > 0) {
					pagina_local.setMensaje(getGeneradorComponentesHtml().insertarMensajeError(
							ConstantesMensajesAplicacion.const_DescripcionMensajeErrorImportacion));

					urlDescarga_local = new URL(url_local, recurso_local + archivoErrores_local);
					listaBotones_local.borrarElementos();
					listaBotones_local.adicionar("descargar", false, "", "Descargar Archivo",
							urlDescarga_local.toString(), 0, true);

					pagina_local.setBotonesPrincipales(
							getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));
				}

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			url_local = null;
			ruta_local = null;
			recurso_local = null;
			formulario_local = null;
			listaCampo_local = null;
			urlDescarga_local = null;
			listaBotones_local = null;
			listaCadenas_local = null;
			archivoErrores_local = null;
			manejadorArchivos_local = null;
			listaParametrosRedireccion_local = null;
		}
		return pagina_local;
	}

	private String conformarConsultaPrincipal(Aplicacion pAplicacion) {
		String consultaPrincipal_local = "";
		String nombreAplicacion = null;
		ListaConsulta listaConsultaRestricciones_local = null;
		boolean existenOpcionesConsulta_local = false;
		int tipoUsuario_local = -1;
		ConsultaSQLPrincipal consultaSQLPrincipal_local = null;
		Campo campoValorUnico_local = null;
		ManejadorSesion ms_local = getManejadorSesion();
		Aplicacion app = ms_local.obtenerGrupoInformacionActual().getAplicacion();
		if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
			return consultaPrincipal_local;
		}

		try {// aqui
			nombreAplicacion = pAplicacion.getNombreAplicacion();
			tipoUsuario_local = ms_local.obtenerTipoUsuarioActual();
			consultaSQLPrincipal_local = new ConsultaSQLPrincipal(app, ms_local.obtenerAtributoListaConsulta());

			consultaSQLPrincipal_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
			consultaSQLPrincipal_local.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
			consultaSQLPrincipal_local.setMotorAplicacion(ms_local.obtenerMotorAplicacion());
			consultaSQLPrincipal_local.setUsuario(ms_local.obtenerUsuarioActual());
			consultaSQLPrincipal_local.setIdSesion(ms_local.obtenerIdSesion());
			consultaSQLPrincipal_local.setPermitirConsultaSinOpciones(app.esPermitirConsultaGeneral());

			listaConsultaRestricciones_local = consultaSQLPrincipal_local.obtenerListaConsultaRestriccionesAplicacion(
					tipoUsuario_local, ms_local.obtenerAplicacionActual(), true);

			if (listaConsultaRestricciones_local == ConstantesGeneral.VALOR_NULO
					|| listaConsultaRestricciones_local.contarElementos() == 0) {

				listaConsultaRestricciones_local = consultaSQLPrincipal_local
						.obtenerListaConsultaRestriccionesAplicacion(tipoUsuario_local,
								ms_local.obtenerAplicacionActual(), false);
			} else {

				consultaSQLPrincipal_local.setSoloRegistrosDonde(true);
			}
			existenOpcionesConsulta_local = (ms_local.obtenerAtributoListaConsulta() != ConstantesGeneral.VALOR_NULO
					&& ms_local.obtenerAtributoListaConsulta().contarElementos() > 0);

			campoValorUnico_local = ms_local.obtenerMotorAplicacion()
					.obtenerPrimerCampoValorUnicoAplicacion(pAplicacion.getIdAplicacion());

			if (campoValorUnico_local != ConstantesGeneral.VALOR_NULO) {
				consultaPrincipal_local = ca.conformarConsultaSQLPrincipal(nombreAplicacion,
						ap.conformarNombreCampoLlavePrimaria(nombreAplicacion), campoValorUnico_local.getNombreCampo(),
						-1);
			} else {

				consultaPrincipal_local = ca.conformarConsultaSQLPrincipal(nombreAplicacion,
						ap.conformarNombreCampoLlavePrimaria(nombreAplicacion), "", -1);
			}

			existenOpcionesConsulta_local = (ms_local.obtenerAtributoListaConsulta() != ConstantesGeneral.VALOR_NULO
					&& (ms_local.obtenerAtributoListaConsulta().contarElementos() > 0
							|| listaConsultaRestricciones_local.contarElementos() > 0));

			if (existenOpcionesConsulta_local) {
				consultaSQLPrincipal_local.setListaConsultaRestricciones(listaConsultaRestricciones_local);
				consultaPrincipal_local = consultaSQLPrincipal_local.obtenerConsultaSQLPrincipal();
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			consultaSQLPrincipal_local = null;
			campoValorUnico_local = null;
		}

		return consultaPrincipal_local;
	}

	private String obtenerEncabezadoPaginaMotorAplicacion() {
		String encabezadoPagina_local = "";

		try {
			encabezadoPagina_local = getGeneradorComponentesHtml().abrirHead();
			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					"<META HTTP-EQUIV=\"Refresh\" CONTENT=\"1;URL=administradorServlet?accion=62\">");
			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					getGeneradorComponentesHtml().getTituloPagina());
			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					getGeneradorComponentesHtml().getHojaEstiloSisnet(0));

			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					getGeneradorComponentesHtml().cerrarHead());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return encabezadoPagina_local;
	}

	private String obtenerContenidoPaginaMotorAplicacion() {
		String contenidoPagina_local = "";

		try {
			contenidoPagina_local = mc
					.concatenarCadena(contenidoPagina_local,
							getGeneradorComponentesHtml().insertarImagenCentrada("",
									"../imagenes/" + CargadorPropiedades.getCargadorPropiedades()
											.obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"),
									"", "", true, false));

			contenidoPagina_local = mc.concatenarCadena(contenidoPagina_local, getGeneradorComponentesHtml()
					.insertarMensaje(ConstantesMensajesAplicacion.getMensajeAplicacion(28)));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return contenidoPagina_local;
	}

	public Pagina obtenerPaginaMotorAplicacion() throws IOException {
		Pagina pagina_local = null;

		try {
			pagina_local = new Pagina();
			pagina_local.setEncabezadoPagina(obtenerEncabezadoPaginaMotorAplicacion());
			pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));
			pagina_local.setContenidoDatos(obtenerContenidoPaginaMotorAplicacion());
			pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return pagina_local;
	}

	private String obtenerEncabezadoPaginaMensaje() {
		String encabezadoPagina_local = "";

		try {
			encabezadoPagina_local = getGeneradorComponentesHtml().abrirHead();
			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					"<META HTTP-EQUIV=\"Refresh\" CONTENT=\"1;URL=administradorServlet?accion=43\">");
			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					getGeneradorComponentesHtml().getTituloPagina());
			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					getGeneradorComponentesHtml().getHojaEstiloSisnet(0));

			encabezadoPagina_local = mc.concatenarCadena(encabezadoPagina_local,
					getGeneradorComponentesHtml().cerrarHead());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return encabezadoPagina_local;
	}

	private String obtenerContenidoPaginaMensaje() {
		String contenidoPagina_local = "";

		try {
			contenidoPagina_local = mc
					.concatenarCadena(contenidoPagina_local,
							getGeneradorComponentesHtml().insertarImagenCentrada("",
									"../imagenes/" + CargadorPropiedades.getCargadorPropiedades()
											.obtenerValorPropiedadImagenes("IMAGEN_LOGO_ENTRADA"),
									"", "", true, false));

			contenidoPagina_local = mc.concatenarCadena(contenidoPagina_local, getGeneradorComponentesHtml()
					.insertarMensaje(ConstantesMensajesAplicacion.getMensajeAplicacion(31)));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return contenidoPagina_local;
	}

	public Pagina obtenerPaginaMensaje() throws IOException {
		Pagina pagina_local = null;

		try {
			pagina_local = new Pagina();
			pagina_local.setEncabezadoPagina(obtenerEncabezadoPaginaMensaje());
			pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));
			pagina_local.setContenidoDatos(obtenerContenidoPaginaMensaje());
			pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return pagina_local;
	}

	private String descargarArchivo(int pValorLlavePrimaria, Campo pCampoArchivo) {
		String destino_local = "";
		String nombreArchivo_local = null;
		String recurso_local = null;
		URL url_local = null;
		URL urlDescarga_local = null;
		String rutaArchivo_local = null;
		ManejadorArchivos manejadorArchivos_local = null;
		int numeroArchivo_local = 0;

		if (pCampoArchivo == ConstantesGeneral.VALOR_NULO) {
			return destino_local;
		}

		try {
			nombreArchivo_local = mc.convertirTildeANoTilde(
					getAdministradorBaseDatosAplicacion().obtenerNombreArchivo(pValorLlavePrimaria, pCampoArchivo));

			if (!mc.esCadenaVacia(nombreArchivo_local)) {
				numeroArchivo_local = getManejadorSesion().obtenerConsecutivoArchivos();
				nombreArchivo_local = ap.conformarNombreArchivoParaDescarga(numeroArchivo_local, nombreArchivo_local);
				recurso_local = getManejadorRequest().obtenerRecursoAplicacion();
				url_local = getManejadorRequest().obtenerURLAplicacion();

				urlDescarga_local = new URL(url_local, recurso_local
						+ getManejadorSesion().obtenerRutaDirectorioUsuarioActual() + nombreArchivo_local);

				rutaArchivo_local = getManejadorSesion().obtenerRutaDirectorioUsuarioActual() + nombreArchivo_local;
				rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(rutaArchivo_local);
				getAdministradorBaseDatosAplicacion().descargarArchivoDeBaseDatos(pCampoArchivo, rutaArchivo_local,
						pValorLlavePrimaria);

				destino_local = "javascript:;";
				manejadorArchivos_local = new ManejadorArchivos();
				if (manejadorArchivos_local.existeArchivo(rutaArchivo_local)) {
					destino_local = urlDescarga_local.toString();
				}
				numeroArchivo_local++;
				getManejadorSesion().actualizarConsecutivoArchivos(numeroArchivo_local);
			}
		} catch (Exception excepcion_local) {
			excepcion_local.printStackTrace();
		} finally {
			url_local = null;
			recurso_local = null;
			rutaArchivo_local = null;
			urlDescarga_local = null;
			manejadorArchivos_local = null;
		}

		return destino_local;
	}

	public Pagina obtenerPaginaDescargarArchivo() throws IOException {
		Pagina pagina_local = null;
		String destino_local = null;
		ListaBotones listaBotones_local = null;
		ListaCampo listaCampo_local = null;
		String nombreArchivo_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaBotones_local = new ListaBotones();
				listaCampo_local = new ListaCampo();

				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, false));

				pagina_local.setInicioCuerpoPagina(
						getGeneradorComponentesHtml().abrirBody(conformarEventosBody(listaCampo_local, "")));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						"Descargando Archivo", obtenerBotonAtras(String.valueOf(95), 0), 0));

				pagina_local.setMensaje(getGeneradorComponentesHtml()
						.insertarMensaje(ConstantesMensajesAplicacion.const_DescripcionMensajeArchivoDescargado));

				nombreArchivo_local = mc.convertirTildeANoTilde(getAdministradorBaseDatosAplicacion()
						.obtenerNombreArchivo(getManejadorSesion().obtenerValorLlavePrimaria(),
								getManejadorSesion().obtenerCampoArchivo()));

				destino_local = descargarArchivo(getManejadorSesion().obtenerValorLlavePrimaria(),
						getManejadorSesion().obtenerCampoArchivo());

				listaBotones_local.adicionar("descargar", false, "", nombreArchivo_local, destino_local, 0, true);

				pagina_local.setBotonesPrincipales(
						getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {
			destino_local = null;
			listaCampo_local = null;
			listaBotones_local = null;
			nombreArchivo_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaErrorEjecucionEventos() throws IOException {
		Pagina pagina_local = null;
		ListaBotones listaBotones_local = null;
		ListaCampo listaCampo_local = null;

		try {
			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaBotones_local = new ListaBotones();
				listaCampo_local = new ListaCampo();

				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 0, false, false));

				pagina_local.setInicioCuerpoPagina(getGeneradorComponentesHtml().abrirBody(""));

				pagina_local.setMensaje(getGeneradorComponentesHtml().insertarMensajeError(
						ConstantesMensajesAplicacion.const_DescripcionMensajeFalloEjecucionEventos));

				listaBotones_local.adicionar("aceptar", false, " onClick=\"return cerrarVentana();\"", "Aceptar",
						"login.jsp", 0, false);

				pagina_local.setBotonesPrincipales(
						getGeneradorComponentesHtml().insertarBotones(listaBotones_local, 0, "center"));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {
			listaCampo_local = null;
			listaBotones_local = null;
		}
		return pagina_local;
	}

	public Pagina obtenerPaginaImpresionMasiva() {
		Pagina pagina_local = null;
		ListaCampo listaCampo_local = null;
		String rutaArchivoImpresionMasiva_local = null;
		String contenidoImpresionMasiva_local = null;
		ManejadorArchivos manejadorArchivos_local = null;
		ListaCadenas listaCadenas_local = null;

		try {
			if (getManejadorSesion().obtenerExistenEventosEnEjecucion()) {
				pagina_local = obtenerPaginaErrorEjecucionEventos();
				return pagina_local;
			}

			if (getManejadorSesion().getSesion() != ConstantesGeneral.VALOR_NULO) {
				pagina_local = new Pagina();
				listaCampo_local = new ListaCampo();
				manejadorArchivos_local = new ManejadorArchivos();
				contenidoImpresionMasiva_local = "";

				insertarMensajePagina(pagina_local);

				pagina_local.setEncabezadoPagina(obtenerBloqueHeadPagina(listaCampo_local, "", 2, true, false));

				pagina_local.setInicioCuerpoPagina(mc.concatenarCadena(getGeneradorComponentesHtml().abrirBody(""),
						insertarImagenProcesandoInformacion(true)));

				pagina_local.setTitulo(insertarEncabezadoPagina(getManejadorSesion().obtenerTituloAplicacionActual(),
						getManejadorSesion().obtenerInformacionActual(), obtenerBotonAtras(String.valueOf(83), 2), 2));

				rutaArchivoImpresionMasiva_local = getManejadorSesion().obtenerRutaRealArchivoSesion(
						"/administrador/exportacion/" + getManejadorSesion().obtenerIdSesion() + ".txt");

				listaCadenas_local = manejadorArchivos_local.leerArchivo(rutaArchivoImpresionMasiva_local);
				if (listaCadenas_local != ConstantesGeneral.VALOR_NULO) {
					contenidoImpresionMasiva_local = listaCadenas_local.concantenarValores();
				}
				pagina_local.setContenidoFormulario(insertarCampoTipoDocumento(contenidoImpresionMasiva_local,
						getManejadorSesion().obtenerIdSesion()));

				pagina_local.setFinCuerpoPagina(getGeneradorComponentesHtml().cerrarBody());
			}
		} catch (Throwable excepcion) {
			excepcion.printStackTrace();
		} finally {

			listaCampo_local = null;
			rutaArchivoImpresionMasiva_local = null;
			contenidoImpresionMasiva_local = null;
			manejadorArchivos_local = null;
			listaCadenas_local = null;
		}
		return pagina_local;
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * aplicacion\generadores\GeneradorPaginaAplicacion.class Java compiler version:
 * 6 (50.0) JD-Core Version: 1.1.3
 */