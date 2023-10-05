package com.sisnet.objetosManejo;

import com.sisnet.objetosManejo.AtributoSesion;

public class AtributoRequest extends AtributoSesion {
	public AtributoRequest(String pNombreAtributo, Object pValorAtributo) {
		super(pNombreAtributo, pValorAtributo);
	}

	@Override
	public String toString() {
		return this.getNombreAtributo() + "[" + this.getValorAtributo() + "]";
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * objetosManejo\AtributoRequest.class Java compiler version: 6 (50.0) JD-Core
 * Version: 1.1.3
 */