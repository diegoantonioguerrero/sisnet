package com.sisnet.aplicacion.manejadores;

import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.ManejadorSesion;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoEnlazado;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.sql.ResultSet;
import java.util.Iterator;

public class ManejadorPlantilla {
	private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
	private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
	private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
	private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
	private ManejadorCampoEnlazado aManejadorCampoEnlazado;
	private MotorAplicacion aMotorAplicacion;
	private String aRutaDescargaArchivos;
	private ManejadorSesion aManejadorSesion;

	public ManejadorPlantilla() {
		setManejadorCampoEnlazado(new ManejadorCampoEnlazado());
	}

	public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
		return this.aAdministradorBaseDatosSisnet;
	}

	public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
		this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
		getManejadorCampoEnlazado().setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
	}

	public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
		return this.aAdministradorBaseDatosAplicacion;
	}

	public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
		this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
		getManejadorCampoEnlazado().setAdministradorBaseDatosAplicacion(pAdministradorBaseDatosAplicacion);
	}

	public ManejadorCampoEnlazado getManejadorCampoEnlazado() {
		return this.aManejadorCampoEnlazado;
	}

	public void setManejadorCampoEnlazado(ManejadorCampoEnlazado pManejadorCampoEnlazado) {
		this.aManejadorCampoEnlazado = pManejadorCampoEnlazado;
	}

	public MotorAplicacion getMotorAplicacion() {
		return this.aMotorAplicacion;
	}

	public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
		this.aMotorAplicacion = pMotorAplicacion;
		getManejadorCampoEnlazado().setMotorAplicacion(pMotorAplicacion);
	}

	public String getRutaDescargaArchivos() {
		return this.aRutaDescargaArchivos;
	}

	public void setRutaDescargaArchivos(String pRutaDescargaArchivos) {
		this.aRutaDescargaArchivos = mc.reemplazarCadena(pRutaDescargaArchivos, ap.obtenerSeparadorArchivos(),
				String.valueOf('/'));
	}

	public ManejadorSesion getManejadorSesion() {
		return this.aManejadorSesion;
	}

	public void setManejadorSesion(ManejadorSesion pManejadorSesion) {
		this.aManejadorSesion = pManejadorSesion;
	}

	private String interpretarPlantilla(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria,
			int pValorLlavePrimariaAnterior, String pContenidoPlantilla) {
		String plantilla_local = "";
		int valorLlavePrimariaPrincipal_local = -1;
		int valorLlavePrimariaGrupo_local = -1;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return plantilla_local;
		}
		if (pContenidoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return plantilla_local;
		}

		try {
			if (pGrupoInformacion.esGrupoInformacionMultiple()) {
				valorLlavePrimariaPrincipal_local = pValorLlavePrimariaAnterior;
				valorLlavePrimariaGrupo_local = pValorLlavePrimaria;
			} else {
				valorLlavePrimariaPrincipal_local = pValorLlavePrimaria;
			}
			plantilla_local = pContenidoPlantilla;
			plantilla_local = reemplazarContenidoPlantilla(plantilla_local, valorLlavePrimariaPrincipal_local,
					pGrupoInformacion.getAplicacion().getIdAplicacion(), valorLlavePrimariaGrupo_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return plantilla_local;
	}

	private String interpretarTablasPlantilla(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria,
			int pValorLlavePrimariaAnterior, String pContenidoPlantilla) {
		String tablasPlantilla_local = "";
		int valorLlavePrimaria_local = -1;

		if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
			return tablasPlantilla_local;
		}
		if (pContenidoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return tablasPlantilla_local;
		}

		try {
			if (pGrupoInformacion.esGrupoInformacionMultiple()) {
				valorLlavePrimaria_local = pValorLlavePrimariaAnterior;
			} else {
				valorLlavePrimaria_local = pValorLlavePrimaria;
			}
			tablasPlantilla_local = pContenidoPlantilla;
			tablasPlantilla_local = crearTablaPlantilla(tablasPlantilla_local,
					pGrupoInformacion.getAplicacion().getIdAplicacion(), valorLlavePrimaria_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return tablasPlantilla_local;
	}

	private String obtenerContenidoPlantillaDocumento(int pIdAplicacionPlantilla, int pPlantillaUtilizar,
			int pPosicionCampoPlantilla) {
		String contenidoPlantillaDocumento_local = "";
		String nombreAplicacion_local = null;
		int idPrimerGrupo_local = -1;
		String campoContenido_local = null;

		try {
			nombreAplicacion_local = getMotorAplicacion().obtenerAplicacionPorId(pIdAplicacionPlantilla)
					.getNombreAplicacion();
			idPrimerGrupo_local = getMotorAplicacion()
					.obtenerIdPrimerGrupoInformacionNoMultipleAplicacion(pIdAplicacionPlantilla);

			campoContenido_local = getMotorAplicacion()
					.obtenerCampoPorPosicion(pPosicionCampoPlantilla, idPrimerGrupo_local).getNombreCampo();

			if (getAdministradorBaseDatosAplicacion().verificarExistenciaTabla(nombreAplicacion_local)) {
				contenidoPlantillaDocumento_local = getAdministradorBaseDatosAplicacion().obtenerContenidoPlantilla(
						nombreAplicacion_local, ap.conformarNombreCampoLlavePrimaria(nombreAplicacion_local),
						pPlantillaUtilizar, campoContenido_local);

			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campoContenido_local = null;
		}

		return contenidoPlantillaDocumento_local;
	}

	public void asignarValorPlantillaCamposDocumento(ListaCampo pListaTCampo, int pPlantillaUtilizar) {

		Iterator iterator_local = null;
		Campo campoDocumento_local = null;
		String contenidoPlantilla_local = null;

		if (pListaTCampo == ConstantesGeneral.VALOR_NULO) {
			return;
		}

		int pValorLlavePrimaria = this.aManejadorSesion.obtenerValorLlavePrimaria();
		int pValorLlavePrimariaAnterior = this.aManejadorSesion.obtenerValorLlavePrimariaAnterior();

		try {
			iterator_local = pListaTCampo.iterator();
			while (iterator_local.hasNext()) {
				campoDocumento_local = (Campo) iterator_local.next();
				if (campoDocumento_local.getPlantillaCampo().getAplicacionPlantilla() != ConstantesGeneral.VALOR_NULO) {
					contenidoPlantilla_local = obtenerContenidoPlantillaDocumento(
							campoDocumento_local.getPlantillaCampo().getAplicacionPlantilla().getIdAplicacion(),
							pPlantillaUtilizar, 2);

					contenidoPlantilla_local = interpretarTablasPlantilla(campoDocumento_local.getGrupoInformacion(),
							pValorLlavePrimaria, pValorLlavePrimariaAnterior, contenidoPlantilla_local);

					contenidoPlantilla_local = interpretarPlantilla(campoDocumento_local.getGrupoInformacion(),
							pValorLlavePrimaria, pValorLlavePrimariaAnterior, contenidoPlantilla_local);

					campoDocumento_local.setValorCampo(contenidoPlantilla_local);
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			iterator_local = null;
			campoDocumento_local = null;
			contenidoPlantilla_local = null;
		}
	}

	public String obtenerContenidoImpresionMasiva(GrupoInformacion pGrupoInformacionActual, int pIdPlantillaUtilizar,
			String pConsultaPrincipal) {
		String contenidoImpresionMasiva_local = "";
		ResultSet resultSet_local = null;
		ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
		int valorLlavePrimaria_local = -1;
		String contenidoPlantillaEncabezadoDocumento = null;
		String contenidoPlantillaContenidoDocumento = null;
		String encabezadoDocumento_local = null;
		String contenidoDocumento_local = null;
		boolean generarEncabezado_local = false;

		if (pGrupoInformacionActual == ConstantesGeneral.VALOR_NULO) {
			return contenidoImpresionMasiva_local;
		}
		if (pConsultaPrincipal == ConstantesGeneral.VALOR_NULO) {
			return contenidoImpresionMasiva_local;
		}

		try {
			manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
			manejadorResultadoConsultaSQL_local
					.setAdministradorBaseDatosAplicacion(getAdministradorBaseDatosAplicacion());
			manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(getAdministradorBaseDatosSisnet());
			if (!mc.esCadenaVacia(pConsultaPrincipal)) {
				resultSet_local = manejadorResultadoConsultaSQL_local
						.obtenerResultadoConsultaAplicacion(pConsultaPrincipal);
			}
			if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
				generarEncabezado_local = true;
				while (resultSet_local.next()) {
					valorLlavePrimaria_local = resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(
							pGrupoInformacionActual.getAplicacion().getNombreAplicacion()));

					if (generarEncabezado_local) {
						contenidoPlantillaEncabezadoDocumento = obtenerContenidoPlantillaDocumento(
								pGrupoInformacionActual.getAplicacion().getAplicacionImpresionMasiva()
										.getIdAplicacion(),
								pIdPlantillaUtilizar, 2);

						generarEncabezado_local = false;
						encabezadoDocumento_local = interpretarTablasPlantilla(pGrupoInformacionActual,
								valorLlavePrimaria_local, -1, contenidoPlantillaEncabezadoDocumento);

						encabezadoDocumento_local = interpretarPlantilla(pGrupoInformacionActual,
								valorLlavePrimaria_local, -1, encabezadoDocumento_local);

						contenidoImpresionMasiva_local = encabezadoDocumento_local;
					}
					contenidoPlantillaContenidoDocumento = obtenerContenidoPlantillaDocumento(
							pGrupoInformacionActual.getAplicacion().getAplicacionImpresionMasiva().getIdAplicacion(),
							pIdPlantillaUtilizar, 3);

					contenidoDocumento_local = interpretarTablasPlantilla(pGrupoInformacionActual,
							valorLlavePrimaria_local, -1, contenidoPlantillaContenidoDocumento);

					contenidoDocumento_local = interpretarPlantilla(pGrupoInformacionActual, valorLlavePrimaria_local,
							-1, contenidoDocumento_local);

					contenidoImpresionMasiva_local = mc.concatenarCadena(contenidoImpresionMasiva_local,
							contenidoDocumento_local);
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			resultSet_local = null;
			manejadorResultadoConsultaSQL_local = null;
			contenidoPlantillaEncabezadoDocumento = null;
			contenidoPlantillaContenidoDocumento = null;
			encabezadoDocumento_local = null;
			contenidoDocumento_local = null;
		}

		return contenidoImpresionMasiva_local;
	}

	public String obtenerValorRegistroCampo(Campo pCampo, int pValorLlavePrimaria, int pValorLlavePrimariaGrupo,
			String pRegistroConsultar) {
		String valorCampo_local = "";
		int posicion_local = -1;
		boolean esArchivoImagen_local = false;
		String nombreCampoInicial_local = null;
		int numeroArchivo_local = 0;
		String rutaArchivo_local = null;
		String primerCampoValorUnico;
		String campoLlaveValorUnico = null;

		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return valorCampo_local;
		}
		if (pRegistroConsultar == ConstantesGeneral.VALOR_NULO) {
			return valorCampo_local;
		}

		try {
			primerCampoValorUnico = aAdministradorBaseDatosSisnet
					.obtenerNombrePrimerCampoValorUnicoGrupoInformacion(pCampo.getGrupoInformacion());
			
			campoLlaveValorUnico = aAdministradorBaseDatosSisnet
					.obtenerNombreLlavePrimariaGrupoInformacion(pCampo.getGrupoInformacion(), false);
			
			posicion_local = 1;
			if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
				if (mc.esCadenaNumerica(pRegistroConsultar, true)) {
					posicion_local = Integer.parseInt(pRegistroConsultar);
				} else if (mc.sonCadenasIguales(pRegistroConsultar, String.valueOf('U'))) {
					posicion_local = aAdministradorBaseDatosAplicacion.obtenerCantidadRegistros(pCampo,
							pValorLlavePrimaria);
				} else if (mc.sonCadenasIguales(pRegistroConsultar, String.valueOf('A'))) {
					posicion_local = aAdministradorBaseDatosAplicacion.obtenerPosicionRegistroGrupoInformacion(pCampo,
							pValorLlavePrimaria, pValorLlavePrimariaGrupo, primerCampoValorUnico);
				}

			}

			esArchivoImagen_local = (pCampo.esTipoDatoArchivo() && pCampo.esImagen());
			if (esArchivoImagen_local) {
				nombreCampoInicial_local = pCampo.getNombreCampo();
				pCampo.setNombreCampo(ap.complementarNombreCampoNombreArchivo(pCampo.getNombreCampo()));
			}
			valorCampo_local = aAdministradorBaseDatosAplicacion.obtenerValorCampoRegistroPorPosicion(pCampo,
					pValorLlavePrimaria, posicion_local, primerCampoValorUnico);

			if (esArchivoImagen_local) {
				pCampo.setNombreCampo(nombreCampoInicial_local);
				if (!mc.esCadenaVacia(valorCampo_local)) {
					numeroArchivo_local = getManejadorSesion().obtenerConsecutivoArchivos();
					valorCampo_local = ap.conformarNombreArchivoParaDescarga(numeroArchivo_local, valorCampo_local);
					rutaArchivo_local = getManejadorSesion().obtenerRutaDirectorioUsuarioActual() + valorCampo_local;
					rutaArchivo_local = getManejadorSesion().obtenerRutaRealArchivoSesion(rutaArchivo_local);
					valorCampo_local = mc.concatenarCadena(getRutaDescargaArchivos(), valorCampo_local);

					int valorLlavePrimaria = aAdministradorBaseDatosAplicacion.obtenerValorLlavePrimariaRegistroPorPosicion(pCampo,
							pValorLlavePrimaria, posicion_local, primerCampoValorUnico, campoLlaveValorUnico);
					aAdministradorBaseDatosAplicacion.descargarArchivoDeBaseDatos(pCampo, rutaArchivo_local, valorLlavePrimaria);

					numeroArchivo_local++;
					getManejadorSesion().actualizarConsecutivoArchivos(numeroArchivo_local);
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			nombreCampoInicial_local = null;
			rutaArchivo_local = null;
			primerCampoValorUnico = null;
		}

		return valorCampo_local;
	}

	private String obtenerRegistroConsultarPlantilla(String pCampoPlantilla) {
		String registroConsultar_local = "";
		int posicionGuion_local = -1;
		int posicionFinal_local = -1;
		int posicionAsterisco_local = -1;
		int posicionNumeral_local = -1;
		int posicionPorcentaje_local = -1;
		int posicionFinalSubcadenaMenos_local = -1;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return registroConsultar_local;
		}

		try {
			posicionGuion_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('-'));
			posicionFinal_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "}");
			posicionAsterisco_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('*'));
			posicionNumeral_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('#'));
			posicionPorcentaje_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('%'));
			posicionFinalSubcadenaMenos_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "/-") + 1;

			if (posicionNumeral_local != -1) {
				if (posicionAsterisco_local != -1) {
					posicionFinal_local = posicionAsterisco_local;
				}
				if (posicionPorcentaje_local != -1) {
					posicionFinal_local = posicionPorcentaje_local;
				}
				if (posicionGuion_local != -1 && posicionGuion_local != posicionFinalSubcadenaMenos_local) {
					posicionFinal_local = posicionGuion_local;
				}
				registroConsultar_local = mc.obtenerSubCadena(pCampoPlantilla, posicionNumeral_local + 1,
						posicionFinal_local);
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return registroConsultar_local;
	}

	private int obtenerPosicionFinalSeudonimoCampoPlantilla(String pCampoPlantilla) {
		int posicionGuion_local = -1;
		int posicionFinal_local = -1;
		int posicionAsterisco_local = -1;
		int posicionNumeral_local = -1;
		int posicionPorcentaje_local = -1;
		int posicionFinalSubcadenaMenos_local = -1;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return posicionFinal_local;
		}

		try {
			posicionGuion_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('-'));
			posicionAsterisco_local = mc.obtenerUltimaPosicionSubCadena(pCampoPlantilla, String.valueOf('*'));
			posicionNumeral_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('#'));
			posicionPorcentaje_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('%'));
			posicionFinal_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "}");
			posicionFinalSubcadenaMenos_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "/-") + 1;

			if (posicionAsterisco_local != -1) {
				posicionFinal_local = posicionAsterisco_local;
			}
			if (posicionPorcentaje_local != -1) {
				posicionFinal_local = posicionPorcentaje_local;
			}
			if (posicionGuion_local != -1 && posicionGuion_local != posicionFinalSubcadenaMenos_local) {
				posicionFinal_local = posicionGuion_local;
			}
			if (posicionNumeral_local != -1) {
				posicionFinal_local = posicionNumeral_local;
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return posicionFinal_local;
	}

	private int obtenerPosicionLimiteNumerico(String pLimiteSubcadena, boolean pIncrementaPosicion) {
		int posicion_local = -1;

		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}

		try {
			posicion_local = Integer.parseInt(pLimiteSubcadena);
			if (posicion_local > -1 && pIncrementaPosicion) {
				posicion_local++;
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return posicion_local;
	}

	private String obtenerContenidoEntreSlash(String pCadena) {
		String contenido_local = "";
		int posicionInicial_local = -1;
		int posicionFinal_local = -1;

		if (pCadena == ConstantesGeneral.VALOR_NULO) {
			return contenido_local;
		}

		try {
			posicionInicial_local = mc.obtenerPosicionSubCadena(pCadena, String.valueOf('/')) + 1;

			posicionFinal_local = mc.obtenerUltimaPosicionSubCadena(pCadena, String.valueOf('/'));
			if (posicionInicial_local != -1 && posicionFinal_local != -1) {
				contenido_local = mc.borrarEspaciosLaterales(
						mc.obtenerSubCadena(pCadena, posicionInicial_local, posicionFinal_local));
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return contenido_local;
	}

	private int obtenerPosicionLimiteSubcadena(String pLimiteSubcadena, String pValorCampo) {
		int posicion_local = -1;
		String subcadenaLimite_local = null;

		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}

		try {
			subcadenaLimite_local = obtenerContenidoEntreSlash(pLimiteSubcadena);
			if (!mc.sonCadenasIguales(subcadenaLimite_local, "")) {
				posicion_local = mc.obtenerPosicionSubCadenaIgnorarMayusculas(pValorCampo, subcadenaLimite_local);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			subcadenaLimite_local = null;
		}

		return posicion_local;
	}

	private int obtenerPosicionLimiteSubcadenaIncluida(String pLimiteSubcadena, String pValorCampo) {
		int posicion_local = -1;
		String subcadenaLimite_local = null;

		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}

		try {
			subcadenaLimite_local = obtenerContenidoEntreSlash(pLimiteSubcadena);
			if (!mc.sonCadenasIguales(subcadenaLimite_local, "")) {
				posicion_local = mc.obtenerPosicionSubCadenaIgnorarMayusculas(pValorCampo, subcadenaLimite_local);
				if (posicion_local != -1) {
					posicion_local += mc.obtenerLongitudCadena(subcadenaLimite_local);
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			subcadenaLimite_local = null;
		}
		return posicion_local;
	}

	private int obtenerPosicionLimiteSubcadenaMas(String pLimiteSubcadena, String pValorCampo) {
		int posicion_local = -1;
		int numeroCaracteres_local = -1;
		String caracteresAgregar_local = null;
		String subcadenaLimite_local = null;

		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}

		try {
			subcadenaLimite_local = obtenerContenidoEntreSlash(pLimiteSubcadena);
			caracteresAgregar_local = mc.obtenerSubCadena(pLimiteSubcadena,
					mc.obtenerPosicionSubCadena(pLimiteSubcadena, String.valueOf('+')) + 1,
					mc.obtenerLongitudCadena(pLimiteSubcadena));

			if (mc.esCadenaNumerica(caracteresAgregar_local, true)) {
				numeroCaracteres_local = Integer.parseInt(caracteresAgregar_local);
			}
			if (!mc.sonCadenasIguales(subcadenaLimite_local, "")) {
				posicion_local = mc.obtenerPosicionSubCadenaIgnorarMayusculas(pValorCampo, subcadenaLimite_local);
				if (posicion_local != -1 && numeroCaracteres_local > -1) {
					posicion_local += numeroCaracteres_local;
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			subcadenaLimite_local = null;
			caracteresAgregar_local = null;
		}

		return posicion_local;
	}

	private int obtenerPosicionLimiteSubcadenaMenos(String pLimiteSubcadena, String pValorCampo) {
		int posicion_local = -1;
		int numeroCaracteres_local = -1;
		String caracteresAgregar_local = null;
		String subcadenaLimite_local = null;

		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}

		try {
			subcadenaLimite_local = obtenerContenidoEntreSlash(pLimiteSubcadena);
			caracteresAgregar_local = mc.obtenerSubCadena(pLimiteSubcadena,
					mc.obtenerPosicionSubCadena(pLimiteSubcadena, String.valueOf('-')) + 1,
					mc.obtenerLongitudCadena(pLimiteSubcadena));

			if (mc.esCadenaNumerica(caracteresAgregar_local, true)) {
				numeroCaracteres_local = Integer.parseInt(caracteresAgregar_local);
			}
			if (!mc.sonCadenasIguales(subcadenaLimite_local, "")) {
				posicion_local = mc.obtenerPosicionSubCadenaIgnorarMayusculas(pValorCampo, subcadenaLimite_local);
				if (posicion_local != -1 && numeroCaracteres_local != -1) {
					posicion_local -= numeroCaracteres_local;
					if (posicion_local < -1) {
						posicion_local = -1;
					}
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			subcadenaLimite_local = null;
			caracteresAgregar_local = null;
		}

		return posicion_local;
	}

	private int obtenerPosicionFinalParaSubcadena(String pLimiteSubcadena, String pValorCampo) {
		int posicion_local = -1;

		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return posicion_local;
		}

		try {
			if (mc.sonCadenasIguales(pLimiteSubcadena, "")) {
				return mc.obtenerLongitudCadena(pValorCampo);
			}
			if (mc.esCadenaNumerica(pLimiteSubcadena, true)) {
				return obtenerPosicionLimiteNumerico(pLimiteSubcadena, false);
			}
			if (mc.verificarExistenciaSubCadena(pLimiteSubcadena, "/I")) {
				return obtenerPosicionLimiteSubcadenaIncluida(pLimiteSubcadena, pValorCampo);
			}
			if (mc.verificarExistenciaSubCadena(pLimiteSubcadena, "/+")) {
				return obtenerPosicionLimiteSubcadenaMas(pLimiteSubcadena, pValorCampo);
			}
			if (mc.verificarExistenciaSubCadena(pLimiteSubcadena, "/-")) {
				return obtenerPosicionLimiteSubcadenaMenos(pLimiteSubcadena, pValorCampo);
			}
			posicion_local = obtenerPosicionLimiteSubcadena(pLimiteSubcadena, pValorCampo);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
		}

		return posicion_local;
	}

	private String asignarSubcadenaValorCampoPlantilla(String pLimitesSubcadena, String pValorCampo) {
		String subcadena_local = "";
		int posicionInicial_local = -1;
		int posicionFinal_local = -1;
		int longitudCadena_local = -1;
		String limiteUno_local = null;
		String limiteDos_local = null;
		String[] limites_local = null;

		if (pLimitesSubcadena == ConstantesGeneral.VALOR_NULO) {
			return subcadena_local;
		}
		if (pValorCampo == ConstantesGeneral.VALOR_NULO) {
			return subcadena_local;
		}

		try {
			if (mc.sonCadenasIguales(pLimitesSubcadena, "")) {
				return pValorCampo;
			}
			limiteUno_local = "";
			limiteDos_local = "";
			longitudCadena_local = mc.obtenerLongitudCadena(pValorCampo);
			limites_local = mc.fraccionarCadena(pLimitesSubcadena, String.valueOf(','));
			if (limites_local.length > 1) {
				limiteUno_local = limites_local[0];
				limiteDos_local = limites_local[1];
			} else {
				limiteUno_local = limites_local[0];
			}
			if (mc.esCadenaNumerica(limiteUno_local, true)) {
				posicionInicial_local = obtenerPosicionLimiteNumerico(limiteUno_local, true);
			} else {
				posicionInicial_local = obtenerPosicionLimiteSubcadena(limiteUno_local, pValorCampo);
			}
			posicionFinal_local = obtenerPosicionFinalParaSubcadena(limiteDos_local, pValorCampo);
			if (mc.esCadenaNumerica(limiteDos_local, true) && posicionFinal_local != -1) {
				posicionFinal_local = posicionInicial_local + posicionFinal_local;
			}
			if (posicionInicial_local > longitudCadena_local) {
				posicionInicial_local = -1;
			}
			if (posicionFinal_local > longitudCadena_local) {
				posicionFinal_local = longitudCadena_local;
			}
			if (posicionInicial_local != -1 && posicionFinal_local != -1
					&& posicionInicial_local < posicionFinal_local) {
				subcadena_local = mc.obtenerSubCadena(pValorCampo, posicionInicial_local, posicionFinal_local);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			limites_local = null;
			limiteUno_local = null;
			limiteDos_local = null;
		}

		return subcadena_local;
	}

	private String obtenerValorCorrectoPlantilla(String pValorCampoPlantilla, Campo pCampo,
			int pValorLlavePrimariaPrincipal, int pValorLlavePrimariaGrupo, String pFormatoAplicar,
			String pEstiloAplicar, String pValorAplicar, String pLimiteSubcadena) {
		String valorCorrectoCampoPlantilla_local = "";
		Tabla tabla_local = null;

		if (pValorCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return valorCorrectoCampoPlantilla_local;
		}
		if (pCampo == ConstantesGeneral.VALOR_NULO) {
			return valorCorrectoCampoPlantilla_local;
		}
		if (pFormatoAplicar == ConstantesGeneral.VALOR_NULO) {
			return valorCorrectoCampoPlantilla_local;
		}
		if (pEstiloAplicar == ConstantesGeneral.VALOR_NULO) {
			return valorCorrectoCampoPlantilla_local;
		}
		if (pValorAplicar == ConstantesGeneral.VALOR_NULO) {
			return valorCorrectoCampoPlantilla_local;
		}
		if (pLimiteSubcadena == ConstantesGeneral.VALOR_NULO) {
			return valorCorrectoCampoPlantilla_local;
		}

		try {
			valorCorrectoCampoPlantilla_local = pValorCampoPlantilla;
			if(pCampo.esTipoDatoParrafo() && valorCorrectoCampoPlantilla_local != null) {
				valorCorrectoCampoPlantilla_local = valorCorrectoCampoPlantilla_local.replace("{$SL}\r", "<br>");
				valorCorrectoCampoPlantilla_local = valorCorrectoCampoPlantilla_local.replace("{$sl}\r", "<br>");
				valorCorrectoCampoPlantilla_local = valorCorrectoCampoPlantilla_local.replace("{$sL}\r", "<br>");
				valorCorrectoCampoPlantilla_local = valorCorrectoCampoPlantilla_local.replace("{$Sl}\r", "<br>");
				valorCorrectoCampoPlantilla_local = valorCorrectoCampoPlantilla_local
						.replace("{$SL}", "<br>")
						.replace("{$sl}", "<br>")
						.replace("{$sL}", "<br>")
						.replace("{$Sl}", "<br>");
				valorCorrectoCampoPlantilla_local = valorCorrectoCampoPlantilla_local.replace("\r", "<br>");
			}
			
			if (mc.verificarExistenciaSubCadena(valorCorrectoCampoPlantilla_local, "{$")) {
				valorCorrectoCampoPlantilla_local = reemplazarContenidoPlantilla(valorCorrectoCampoPlantilla_local,
						pValorLlavePrimariaPrincipal, pCampo.getGrupoInformacion().getAplicacion().getIdAplicacion(),
						pValorLlavePrimariaGrupo);
			}

			if (pCampo.esTipoDatoTabla()) {
				tabla_local = getAdministradorBaseDatosSisnet()
						.obtenerTablaPorId(Integer.parseInt(pCampo.getFormatoCampo().getTipoDato()));

				if (mc.esCadenaNumerica(valorCorrectoCampoPlantilla_local, true)) {
					valorCorrectoCampoPlantilla_local = getAdministradorBaseDatosAplicacion().obtenerValorTabla(
							tabla_local.getNombreTabla(), Integer.parseInt(valorCorrectoCampoPlantilla_local));
				}
			}

			if (pCampo.esCampoEnlazado()) {
				valorCorrectoCampoPlantilla_local = getManejadorCampoEnlazado().obtenerValorCampoEnlazado(
						pCampo.getEnlaceCampo().getEnlazado(), Integer.parseInt(valorCorrectoCampoPlantilla_local));
			}
			
			valorCorrectoCampoPlantilla_local = ap.obtenerValorCampoConFormato(valorCorrectoCampoPlantilla_local,
					pCampo.esTipoDatoNumeroEntero(), pCampo.esTipoDatoNumeroReal(), pCampo.esTipoDatoFecha(),
					pCampo.esTipoDatoHora(), pFormatoAplicar, pValorAplicar);

			valorCorrectoCampoPlantilla_local = asignarSubcadenaValorCampoPlantilla(pLimiteSubcadena,
					valorCorrectoCampoPlantilla_local);
			valorCorrectoCampoPlantilla_local = ap.obtenerValorCampoConEstilo(valorCorrectoCampoPlantilla_local,
					pEstiloAplicar);
			
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			tabla_local = null;
		}

		return valorCorrectoCampoPlantilla_local;
	}

	private String obtenerEstiloCampoPlantilla(String pCampoPlantilla) {
		String estilo_local = "";
		int posicionAsterisco_local = -1;
		int posicionFinal_local = -1;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return estilo_local;
		}

		try {
			posicionFinal_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "}");
			posicionAsterisco_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('*'));
			if (posicionAsterisco_local != -1 && posicionFinal_local != -1) {
				estilo_local = mc.obtenerSubCadena(pCampoPlantilla, posicionAsterisco_local + 1, posicionFinal_local);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return estilo_local;
	}

	private String obtenerValorAplicarPlantilla(String pCampoPlantilla) {
		String valorAplicar_local = "";
		int posicionParentesisCuadrado_local = -1;
		int posicionGuion_local = -1;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return valorAplicar_local;
		}

		try {
			posicionGuion_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('-'));
			posicionParentesisCuadrado_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('['));
			if (posicionGuion_local != -1 && posicionParentesisCuadrado_local != -1) {
				valorAplicar_local = mc.obtenerSubCadena(pCampoPlantilla,
						mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('[')) + 1,
						mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf(']')));

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return valorAplicar_local;
	}

	private String obtenerFormatoPlantilla(String pCampoPlantilla) {
		String formatoAplicar_local = "";
		int posicionParentesisCuadrado_local = -1;
		int posicionGuion_local = -1;
		int posicionFinal_local = -1;
		int posicionAsterisco_local = -1;
		int posicionPorcentaje_local = -1;
		int posicionFinalSubcadenaMenos_local = -1;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return formatoAplicar_local;
		}

		try {
			posicionGuion_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('-'));
			posicionParentesisCuadrado_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('['));
			posicionFinal_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "}");
			posicionAsterisco_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('*'));
			posicionPorcentaje_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('%'));
			posicionFinalSubcadenaMenos_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, "/-") + 1;

			if (posicionGuion_local != -1 && posicionGuion_local != posicionFinalSubcadenaMenos_local) {
				if (posicionAsterisco_local != -1) {
					posicionFinal_local = posicionAsterisco_local;
				}
				if (posicionPorcentaje_local != -1) {
					posicionFinal_local = posicionPorcentaje_local;
				}
				if (posicionParentesisCuadrado_local != -1) {
					posicionFinal_local = posicionParentesisCuadrado_local;
				}
				formatoAplicar_local = mc.obtenerSubCadena(pCampoPlantilla, posicionGuion_local + 1,
						posicionFinal_local);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return formatoAplicar_local;
	}

	private String obtenerValorCampoPlantilla(String pCampoPlantilla, int pValorLlavePrimariaPrincipal,
			int pIdAplicacion, int pValorLlavePrimariaGrupo) {
		String valorCampoPlantilla_local = "";
		String valorAplicar_local = null;
		String formatoAplicar_local = null;
		String estiloAplicar_local = null;
		String limitesSubcadena_local = null;
		String registroConsultar_local = null;
		String seudonimo_local = null;
		Campo campo_local = null;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return valorCampoPlantilla_local;
		}

		try {
			valorCampoPlantilla_local = "XXXXXXXXXXXXXXXXXXXX";
			if (mc.comienzaCon(pCampoPlantilla, "{$")) {
				estiloAplicar_local = obtenerEstiloCampoPlantilla(pCampoPlantilla);
				limitesSubcadena_local = ap.obtenerLimitesSubcadena(pCampoPlantilla);
				valorAplicar_local = obtenerValorAplicarPlantilla(pCampoPlantilla);
				formatoAplicar_local = obtenerFormatoPlantilla(pCampoPlantilla);
				registroConsultar_local = obtenerRegistroConsultarPlantilla(pCampoPlantilla);
				seudonimo_local = mc.obtenerSubCadena(pCampoPlantilla, 2,
						obtenerPosicionFinalSeudonimoCampoPlantilla(pCampoPlantilla));

				campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local, pIdAplicacion);
				if (campo_local != ConstantesGeneral.VALOR_NULO) {
					valorCampoPlantilla_local = obtenerValorRegistroCampo(campo_local, pValorLlavePrimariaPrincipal,
							pValorLlavePrimariaGrupo, registroConsultar_local);

					if (!mc.sonCadenasIguales(valorCampoPlantilla_local, "")) {
						valorCampoPlantilla_local = obtenerValorCorrectoPlantilla(valorCampoPlantilla_local,
								campo_local, pValorLlavePrimariaPrincipal, pValorLlavePrimariaGrupo,
								formatoAplicar_local, estiloAplicar_local, valorAplicar_local, limitesSubcadena_local);
					}
				}

			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campo_local = null;
			seudonimo_local = null;
			valorAplicar_local = null;
			estiloAplicar_local = null;
			formatoAplicar_local = null;
			limitesSubcadena_local = null;
			registroConsultar_local = null;
		}

		return valorCampoPlantilla_local;
	}

	private String reemplazarContenidoPlantilla(String pContenidoPlantilla, int pValorLlavePrimariaPrincipal,
			int pIdAplicacion, int pValorLlavePrimariaGrupo) {
		String contenidoPlantilla_local = "";
		int posicionInicial_local = -1;
		int posicionFinal_local = -1;
		String campoPlantilla_local = null;
		String cadenaInicial_local = null;
		String cadenaFinal_local = null;
		String valorCampoPlantilla_local = null;

		if (pContenidoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return contenidoPlantilla_local;
		}

		try {
			contenidoPlantilla_local = pContenidoPlantilla;
			while (mc.verificarExistenciaSubCadena(contenidoPlantilla_local, "{$")
					&& mc.verificarExistenciaSubCadenaAPartirPosicion(contenidoPlantilla_local, "}",
							mc.obtenerPosicionSubCadena(contenidoPlantilla_local, "{$"))) {

				posicionInicial_local = mc.obtenerPosicionSubCadena(contenidoPlantilla_local, "{$");

				posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(contenidoPlantilla_local, "}",
						posicionInicial_local) + 1;

				if (posicionInicial_local < posicionFinal_local) {
					campoPlantilla_local = mc.obtenerSubCadena(contenidoPlantilla_local, posicionInicial_local,
							posicionFinal_local);
					campoPlantilla_local = mc.convertirAMayusculas(campoPlantilla_local);
					cadenaInicial_local = mc.obtenerSubCadena(contenidoPlantilla_local, 0, posicionInicial_local);

					cadenaFinal_local = mc.obtenerSubCadena(contenidoPlantilla_local, posicionFinal_local,
							mc.obtenerLongitudCadena(contenidoPlantilla_local));

					valorCampoPlantilla_local = obtenerValorCampoPlantilla(campoPlantilla_local,
							pValorLlavePrimariaPrincipal, pIdAplicacion, pValorLlavePrimariaGrupo);

					contenidoPlantilla_local = mc.concatenarCadena(cadenaInicial_local, valorCampoPlantilla_local);
					contenidoPlantilla_local = mc.concatenarCadena(contenidoPlantilla_local, cadenaFinal_local);
				}
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campoPlantilla_local = null;
			cadenaInicial_local = null;
			cadenaFinal_local = null;
			valorCampoPlantilla_local = null;
		}

		return contenidoPlantilla_local;
	}

	private int obtenerCantidadRegistrosCampoPlantilla(String pCampoPlantilla, int pValorLlavePrimaria,
			int pIdAplicacion) {
		int cantidadRegistros_local = 0;
		int posicionNumeral_local = -1;
		int posicionFinal_local = -1;
		String seudonimo_local = null;
		Campo campo_local = null;

		if (pCampoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return cantidadRegistros_local;
		}

		try {
			if (mc.comienzaCon(pCampoPlantilla, "{$")) {
				posicionNumeral_local = mc.obtenerPosicionSubCadena(pCampoPlantilla, String.valueOf('#'));

				posicionFinal_local = obtenerPosicionFinalSeudonimoCampoPlantilla(pCampoPlantilla);
				if (posicionNumeral_local != -1) {
					posicionFinal_local = posicionNumeral_local;
				}
				seudonimo_local = mc.obtenerSubCadena(pCampoPlantilla, 2, posicionFinal_local);

				campo_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local, pIdAplicacion);
				if (campo_local != ConstantesGeneral.VALOR_NULO) {
					cantidadRegistros_local = getAdministradorBaseDatosAplicacion()
							.obtenerCantidadRegistros(campo_local, pValorLlavePrimaria);
				}
			}

		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			campo_local = null;
			seudonimo_local = null;
		}

		return cantidadRegistros_local;
	}

	private boolean validarFilaConValores(Campo pPrimerCampoFila, int pValorLlavePrimaria, int pPosicion) {
		boolean filaConValores_local = false;
		double valorNumerico_local = -1.0D;
		String valorCampo_local = null;

		if (pPrimerCampoFila == ConstantesGeneral.VALOR_NULO) {
			return filaConValores_local;
		}

		try {
			if (!pPrimerCampoFila.getGrupoInformacion().esGrupoInformacionMultiple()) {
				pPosicion = 1;
			}
			valorCampo_local = getAdministradorBaseDatosAplicacion().obtenerValorCampoRegistroPorPosicion(
					pPrimerCampoFila, pValorLlavePrimaria, pPosicion,
					getAdministradorBaseDatosSisnet().obtenerNombrePrimerCampoValorUnicoGrupoInformacion(
							pPrimerCampoFila.getGrupoInformacion()));

			if (pPrimerCampoFila.esTipoDatoNumerico() || pPrimerCampoFila.esTipoDatoTabla()) {
				filaConValores_local = mc.esCadenaNumerica(valorCampo_local, pPrimerCampoFila.esTipoDatoNumeroEntero());
				if (filaConValores_local) {
					valorNumerico_local = Double.parseDouble(valorCampo_local);
					filaConValores_local = (valorNumerico_local != 0.0D && valorNumerico_local != -1.0D);
					return filaConValores_local;
				}
			} else {
				filaConValores_local = !mc.esCadenaVacia(valorCampo_local);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return filaConValores_local;
	}

	private String crearTablaPlantilla(String pContenidoPlantilla, int pIdAplicacion, int pValorLlavePrimaria) {
		String contenidoPlantilla_local = "";
		int posicionTablaInicial_local = -1;
		int posicionTablaFinal_local = -1;
		int posicionFilaInicial_local = -1;
		int posicionFilaFinal_local = -1;
		int posicionInicial_local = -1;
		int posicionFinal_local = -1;
		int posicionFinalSeudonimo_local = -1;
		int cantidadRegistros_local = -1;
		int contador_local = -1;
		int contadorCampos_local = -1;
		boolean encuentracampo_local = false;
		boolean masCampos_local = false;
		String tablaPlantilla_local = null;
		String cadenaInicial_local = null;
		String cadenaFinal_local = null;
		String tablaInicial_local = null;
		String tablaFinal_local = null;
		String filaPlantilla_local = null;
		String campoPlantilla_local = null;
		String filaInicial_local = null;
		String filaFinal_local = null;
		String seudonimo_local = null;
		String filasPlantilla_local = null;
		Campo primerCampoFila_local = null;

		if (pContenidoPlantilla == ConstantesGeneral.VALOR_NULO) {
			return tablaPlantilla_local;
		}

		try {
			contenidoPlantilla_local = pContenidoPlantilla;
			while (mc.verificarExistenciaSubCadena(contenidoPlantilla_local, "[$IT]")
					&& mc.verificarExistenciaSubCadena(contenidoPlantilla_local, "[$FT]")) {

				posicionTablaInicial_local = mc.obtenerPosicionSubCadena(contenidoPlantilla_local, "[$IT]");

				posicionTablaFinal_local = mc.obtenerPosicionSubCadena(contenidoPlantilla_local, "[$FT]");

				tablaPlantilla_local = mc.obtenerSubCadena(contenidoPlantilla_local,
						posicionTablaInicial_local + mc.obtenerLongitudCadena("[$IT]"), posicionTablaFinal_local);

				cadenaInicial_local = mc.obtenerSubCadena(contenidoPlantilla_local, 0, posicionTablaInicial_local);

				cadenaFinal_local = mc.obtenerSubCadena(contenidoPlantilla_local,
						posicionTablaFinal_local + mc.obtenerLongitudCadena("[$FT]"),
						mc.obtenerLongitudCadena(contenidoPlantilla_local));

				if (mc.verificarExistenciaSubCadena(tablaPlantilla_local, "{$")) {
					encuentracampo_local = false;
					posicionFilaInicial_local = 0;
					posicionFilaFinal_local = 0;
					tablaInicial_local = "";
					tablaFinal_local = "";
					filaPlantilla_local = "";
					while (!encuentracampo_local) {
						posicionFilaInicial_local = mc.obtenerPosicionSubCadenaAPartirPosicion(tablaPlantilla_local,
								"<tr", posicionFilaFinal_local);

						posicionFilaFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(tablaPlantilla_local,
								"</tr>", posicionFilaFinal_local) + mc.obtenerLongitudCadena("</tr>");

						tablaInicial_local = mc.obtenerSubCadena(tablaPlantilla_local, 0, posicionFilaInicial_local);

						tablaFinal_local = mc.obtenerSubCadena(tablaPlantilla_local, posicionFilaFinal_local,
								mc.obtenerLongitudCadena(tablaPlantilla_local));

						filaPlantilla_local = mc.obtenerSubCadena(tablaPlantilla_local, posicionFilaInicial_local,
								posicionFilaFinal_local);

						encuentracampo_local = mc.verificarExistenciaSubCadena(filaPlantilla_local, "{$");
					}

					posicionInicial_local = 0;
					contadorCampos_local = 0;
					posicionFinal_local = 0;
					campoPlantilla_local = "";
					filaInicial_local = "";
					filaFinal_local = "";
					masCampos_local = (mc.verificarExistenciaSubCadenaAPartirPosicion(filaPlantilla_local, "{$",
							posicionFinal_local)
							&& mc.verificarExistenciaSubCadenaAPartirPosicion(filaPlantilla_local, "}",
									posicionFinal_local));

					cantidadRegistros_local = -1;
					while (masCampos_local) {
						posicionInicial_local = mc.obtenerPosicionSubCadenaAPartirPosicion(filaPlantilla_local, "{$",
								posicionFinal_local);

						posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(filaPlantilla_local, "}",
								posicionFinal_local) + mc.obtenerLongitudCadena("}");

						campoPlantilla_local = mc.obtenerSubCadena(filaPlantilla_local, posicionInicial_local,
								posicionFinal_local);
						campoPlantilla_local = mc.convertirAMayusculas(campoPlantilla_local);
						filaInicial_local = mc.obtenerSubCadena(filaPlantilla_local, 0, posicionInicial_local);

						filaFinal_local = mc.obtenerSubCadena(filaPlantilla_local, posicionFinal_local,
								mc.obtenerLongitudCadena(filaPlantilla_local));

						cantidadRegistros_local = obtenerCantidadRegistrosCampoPlantilla(campoPlantilla_local,
								pValorLlavePrimaria, pIdAplicacion);

						posicionFinalSeudonimo_local = obtenerPosicionFinalSeudonimoCampoPlantilla(
								campoPlantilla_local);
						seudonimo_local = mc.obtenerSubCadena(campoPlantilla_local, 2, posicionFinalSeudonimo_local);

						if (contadorCampos_local == 0) {
							primerCampoFila_local = getMotorAplicacion().obtenerCampoPorSeudonimo(seudonimo_local,
									pIdAplicacion);

							contadorCampos_local++;
						}

						if (!mc.verificarExistenciaSubCadena(campoPlantilla_local, String.valueOf('#'))) {

							seudonimo_local = mc.concatenarCadena("{$",
									seudonimo_local + "#*"
											+ mc.obtenerSubCadena(campoPlantilla_local, posicionFinalSeudonimo_local,
													mc.obtenerLongitudCadena(campoPlantilla_local)));

						} else {

							seudonimo_local = mc.concatenarCadena("{$",
									seudonimo_local + "#*"
											+ mc.obtenerSubCadena(campoPlantilla_local,
													posicionFinalSeudonimo_local + 2,
													mc.obtenerLongitudCadena(campoPlantilla_local)));
						}

						posicionFinal_local += 2;
						filaPlantilla_local = mc.concatenarCadena(filaInicial_local, seudonimo_local);
						filaPlantilla_local = mc.concatenarCadena(filaPlantilla_local, filaFinal_local);
						masCampos_local = (mc.verificarExistenciaSubCadenaAPartirPosicion(filaPlantilla_local, "{$",
								posicionFinal_local)
								&& mc.verificarExistenciaSubCadenaAPartirPosicion(filaPlantilla_local, "}",
										posicionFinal_local));
					}

					filasPlantilla_local = "";
					if (cantidadRegistros_local == 0) {
						filasPlantilla_local = filaPlantilla_local;
					} else {
						for (contador_local = 1; contador_local <= cantidadRegistros_local; contador_local++) {
							if (validarFilaConValores(primerCampoFila_local, pValorLlavePrimaria, contador_local)) {
								filasPlantilla_local = mc.concatenarCadena(filasPlantilla_local, mc.reemplazarCadena(
										filaPlantilla_local, "\\#\\*", String.valueOf('#') + contador_local));
							}
						}
					}

					tablaPlantilla_local = mc.concatenarCadena(tablaInicial_local, filasPlantilla_local);
					tablaPlantilla_local = mc.concatenarCadena(tablaPlantilla_local, tablaFinal_local);
				}
				contenidoPlantilla_local = mc.concatenarCadena(cadenaInicial_local, tablaPlantilla_local);
				contenidoPlantilla_local = mc.concatenarCadena(contenidoPlantilla_local, cadenaFinal_local);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			filaFinal_local = null;
			seudonimo_local = null;
			tablaFinal_local = null;
			filaInicial_local = null;
			cadenaFinal_local = null;
			tablaInicial_local = null;
			filaPlantilla_local = null;
			cadenaInicial_local = null;
			campoPlantilla_local = null;
			tablaPlantilla_local = null;
			filasPlantilla_local = null;
			primerCampoFila_local = null;
		}

		return contenidoPlantilla_local;
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * aplicacion\manejadores\ManejadorPlantilla.class Java compiler version: 6
 * (50.0) JD-Core Version: 1.1.3
 */