package com.sisnet.baseDatos.sisnet.administrador;

import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import java.io.Serializable;

@SuppressWarnings("serial")
public class GrupoInformacion implements Serializable {
	private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
	private int aIdGrupoInformacion;
	private int aIdAplicacion;
	private Aplicacion aAplicacion;
	private String aNombreGrupoInformacion;
	private String aDescripcionGrupoInformacion;
	private boolean aGrupoInformacionPrincipal;
	private boolean aGrupoInformacionMultiple;
	private int aPosicion;
	private int aAnchoGrupoInformacion;
	private boolean aIncluirOpcionConsulta;
	private boolean aVisibleUsuarioPrincipal;
	private boolean aMostrarDetalle;
	private int aMargenSuperior;

	public GrupoInformacion() {
		setIdGrupoInformacion(-1);
		setIdAplicacion(-1);
		setAplicacion(null);
		setNombreGrupoInformacion("");
		setDescripcionGrupoInformacion("");
		setGrupoInformacionPrincipal(false);
		setGrupoInformacionMultiple(false);
		setPosicion(-1);
		setMargenSuperior(25);
	}

	public int getIdGrupoInformacion() {
		return this.aIdGrupoInformacion;
	}

	public void setIdGrupoInformacion(int pIdGrupoInformacion) {
		this.aIdGrupoInformacion = pIdGrupoInformacion;
	}

	public int getIdAplicacion() {
		return this.aIdAplicacion;
	}

	public void setIdAplicacion(int pIdAplicacion) {
		this.aIdAplicacion = pIdAplicacion;
	}

	public Aplicacion getAplicacion() {
		return this.aAplicacion;
	}

	public void setAplicacion(Aplicacion pAplicacion) {
		this.aAplicacion = pAplicacion;
	}

	public String getNombreGrupoInformacion() {
		return this.aNombreGrupoInformacion;
	}

	public void setNombreGrupoInformacion(String pNombreGrupoInformacion) {
		this.aNombreGrupoInformacion = mc.convertirAMayusculas(pNombreGrupoInformacion);
	}

	public String getDescripcionGrupoInformacion() {
		return this.aDescripcionGrupoInformacion;
	}

	public void setDescripcionGrupoInformacion(String pDescripcionGrupoInformacion) {
		this.aDescripcionGrupoInformacion = mc.convertirAMayusculas(pDescripcionGrupoInformacion);
	}

	public boolean esGrupoInformacionPrincipal() {
		return this.aGrupoInformacionPrincipal;
	}

	public void setGrupoInformacionPrincipal(boolean pGrupoInformacionPrincipal) {
		this.aGrupoInformacionPrincipal = pGrupoInformacionPrincipal;
	}

	public boolean esGrupoInformacionMultiple() {
		return this.aGrupoInformacionMultiple;
	}

	public void setGrupoInformacionMultiple(boolean pGrupoInformacionMultiple) {
		this.aGrupoInformacionMultiple = pGrupoInformacionMultiple;
	}

	public int getPosicion() {
		return this.aPosicion;
	}

	public void setPosicion(int pPosicion) {
		this.aPosicion = pPosicion;
	}

	public int getAnchoGrupoInformacion() {
		return this.aAnchoGrupoInformacion;
	}

	public void setAnchoGrupoInformacion(int pAnchoGrupoInformacion) {
		this.aAnchoGrupoInformacion = pAnchoGrupoInformacion;
	}

	public boolean esIncluirOpcionConsulta() {
		return this.aIncluirOpcionConsulta;
	}

	public void setIncluirOpcionConsulta(boolean pIncluirOpcionConsulta) {
		this.aIncluirOpcionConsulta = pIncluirOpcionConsulta;
	}

	public boolean esVisibleUsuarioPrincipal() {
		return this.aVisibleUsuarioPrincipal;
	}

	public void setVisibleUsuarioPrincipal(boolean pVisibleUsuarioPrincipal) {
		this.aVisibleUsuarioPrincipal = pVisibleUsuarioPrincipal;
	}

	public boolean esMostrarDetalle() {
		return this.aMostrarDetalle;
	}

	public void setMostrarDetalle(boolean pMostrarDetalle) {
		this.aMostrarDetalle = pMostrarDetalle;
	}

	public int getMargenSuperior() {
		return this.aMargenSuperior;
	}

	public void setMargenSuperior(int pMargenSuperior) {
		this.aMargenSuperior = pMargenSuperior;
	}

	@Override
	public String toString() {

		return "GrupoInf[" + this.aIdGrupoInformacion + "] " + this.aDescripcionGrupoInformacion;
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * baseDatos\sisnet\administrador\GrupoInformacion.class Java compiler version:
 * 6 (50.0) JD-Core Version: 1.1.3
 */