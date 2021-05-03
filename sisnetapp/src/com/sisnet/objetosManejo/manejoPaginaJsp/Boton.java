package com.sisnet.objetosManejo.manejoPaginaJsp;

import com.sisnet.aplicacion.manejadores.ManejadorCadenas;

public class Boton {
	private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
	private String aNombreBoton;
	private String aUbicacionImagen1;
	private String aUbicacionImagen2;
	private boolean aEntradaFormulario;
	private String aEventos;
	private String aDescripcionBoton;
	private String aDestinoBoton;
	private boolean aAbrirVentanaNueva;
	/***
	 * Indicates that the button must use custom images for hover and focus events.
	 */
	private boolean aWithCustomImages;

	public Boton(String pNombreBoton, boolean pEntradaFormulario, String pEventos, String pDescripcionBoton,
			String pDestinoBoton, int pNumeroDirectoriosAnteriores, boolean pAbrirVentanaNueva) {
		String ubicacionImagen_local = null;
		try {
			ubicacionImagen_local = mc.complementarDirectorio(pNumeroDirectoriosAnteriores);
			setNombreBoton(pNombreBoton);
			setUbicacionImagen1(ubicacionImagen_local + "../imagenes/botones/" + pNombreBoton + ".gif");

			setUbicacionImagen2(ubicacionImagen_local + "../imagenes/botones/" + pNombreBoton + '2' + ".gif");

			setEntradaFormulario(pEntradaFormulario);
			setEventos(pEventos);
			setDescripcionBoton(pDescripcionBoton);
			setDestinoBoton(pDestinoBoton);
			setAbrirVentanaNueva(pAbrirVentanaNueva);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			ubicacionImagen_local = null;
		}
	}

	public String getNombreBoton() {
		return this.aNombreBoton;
	}

	public void setNombreBoton(String pNombreBoton) {
		this.aNombreBoton = pNombreBoton;
	}

	public String getUbicacionImagen1() {
		return this.aUbicacionImagen1;
	}

	public void setUbicacionImagen1(String pUbicacionImagen1) {
		this.aUbicacionImagen1 = pUbicacionImagen1;
	}

	public String getUbicacionImagen2() {
		return this.aUbicacionImagen2;
	}

	public void setUbicacionImagen2(String pUbicacionImagen2) {
		this.aUbicacionImagen2 = pUbicacionImagen2;
	}

	public boolean esEntradaFormulario() {
		return this.aEntradaFormulario;
	}

	public void setEntradaFormulario(boolean pEntradaFormulario) {
		this.aEntradaFormulario = pEntradaFormulario;
	}

	public String getEventos() {
		return this.aEventos;
	}

	public void setEventos(String pEventos) {
		this.aEventos = pEventos;
	}

	public String getDescripcionBoton() {
		return this.aDescripcionBoton;
	}

	public void setDescripcionBoton(String pDescripcionBoton) {
		this.aDescripcionBoton = pDescripcionBoton;
	}

	public String getDestinoBoton() {
		return this.aDestinoBoton;
	}

	public void setDestinoBoton(String pDestinoBoton) {
		this.aDestinoBoton = pDestinoBoton;
	}

	public boolean esAbrirVentanaNueva() {
		return this.aAbrirVentanaNueva;
	}

	public void setAbrirVentanaNueva(boolean pAbrirVentanaNueva) {
		this.aAbrirVentanaNueva = pAbrirVentanaNueva;
	}

	/***
	 * Get if button must use custom images for hover and focus events.
	 */
	public boolean isWithCustomImages() {
		return aWithCustomImages;
	}

	/***
	 * Set if button must use custom images for hover and focus events.
	 */
	public void setWithCustomImages(boolean aWithCustomImages) {
		this.aWithCustomImages = aWithCustomImages;
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * objetosManejo\manejoPaginaJsp\Boton.class Java compiler version: 6 (50.0)
 * JD-Core Version: 1.1.3
 */