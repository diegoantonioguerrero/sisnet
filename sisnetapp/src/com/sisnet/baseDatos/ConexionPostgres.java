package com.sisnet.baseDatos;

import java.io.Serializable;

public class ConexionPostgres implements Serializable {
	private String aSuperUsuario;
	private String aContrasenaSuperUsuario;
	private int aNumeroPuertoConexion;

	public ConexionPostgres() {
		setSuperUsuario("postgres");
		setContrasenaSuperUsuario("postgres");
		setNumeroPuertoConexion(Integer.parseInt(com.sisnet.constantes.ConstantesBaseDatos.const_Puerto));
	}

	public String getSuperUsuario() {
		return this.aSuperUsuario;
	}

	public void setSuperUsuario(String pSuperUsuario) {
		this.aSuperUsuario = pSuperUsuario;
	}

	public String getContrasenaSuperUsuario() {
		return this.aContrasenaSuperUsuario;
	}

	public void setContrasenaSuperUsuario(String pContrasenaSuperUsuario) {
		this.aContrasenaSuperUsuario = pContrasenaSuperUsuario;
	}

	public int getNumeroPuertoConexion() {
		return this.aNumeroPuertoConexion;
	}

	public void setNumeroPuertoConexion(int pNumeroPuertoConexion) {
		this.aNumeroPuertoConexion = pNumeroPuertoConexion;
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * baseDatos\ConexionPostgres.class Java compiler version: 6 (50.0) JD-Core
 * Version: 1.1.3
 */