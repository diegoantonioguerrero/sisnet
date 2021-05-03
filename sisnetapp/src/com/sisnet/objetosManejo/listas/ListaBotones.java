package com.sisnet.objetosManejo.listas;

import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.manejoPaginaJsp.Boton;

public class ListaBotones extends Lista {
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;

	public void adicionar(String pNombreBoton, boolean pEntradaFormulario, String pEventos, String pDescripcionBoton,
			String pDestinoBoton, int pNumeroDirectoriosAnteriores, boolean pAbrirVentanaNueva, boolean pWithCustomImages) {
		if (pNombreBoton == ConstantesGeneral.VALOR_NULO) {
			return;
		}
		if (pEventos == ConstantesGeneral.VALOR_NULO) {
			return;
		}
		if (pDescripcionBoton == ConstantesGeneral.VALOR_NULO) {
			return;
		}
		if (pDestinoBoton == ConstantesGeneral.VALOR_NULO) {
			return;
		}
		try {
			Boton btn = new Boton(pNombreBoton, pEntradaFormulario, pEventos, pDescripcionBoton, pDestinoBoton,
					pNumeroDirectoriosAnteriores, pAbrirVentanaNueva);
			btn.setWithCustomImages(pWithCustomImages);
			super.adicionar(btn);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
	}
	
	public void adicionar(String pNombreBoton, boolean pEntradaFormulario, String pEventos, String pDescripcionBoton,
			String pDestinoBoton, int pNumeroDirectoriosAnteriores, boolean pAbrirVentanaNueva) {
		
		this.adicionar(pNombreBoton, pEntradaFormulario, pEventos, pDescripcionBoton, pDestinoBoton, pNumeroDirectoriosAnteriores, pAbrirVentanaNueva, false);
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\
 * objetosManejo\listas\ListaBotones.class Java compiler version: 6 (50.0)
 * JD-Core Version: 1.1.3
 */