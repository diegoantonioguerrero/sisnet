package com.sisnet.utilidades;

import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;

public class ConversorNumerosLetras {
	private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
	private String[][] aDescripcionCifras;
	private String aNumeroLetras;
	private String aCadenaNumero;
	private int aModo;
	private int aDecimales;
	private int aPosicion;
	private boolean aDecimal;
	private boolean aDigitoSignificativo;

	public ConversorNumerosLetras() {
		setNumeroLetras("");
		setCadenaNumero("");
		setModo(-1);
		setDecimales(-1);
		setPosicion(-1);
		setDecimal(false);
		setDigitoSignificativo(false);
		llenarValores();
	}

	public String[][] getDescripcionCifras() {
		return this.aDescripcionCifras;
	}

	public void setDescripcionCifras(String[][] pDescripcionCifras) {
		this.aDescripcionCifras = pDescripcionCifras;
	}

	public String getNumeroLetras() {
		return this.aNumeroLetras;
	}

	public void setNumeroLetras(String pNumeroLetras) {
		this.aNumeroLetras = pNumeroLetras;
	}

	public String getCadenaNumero() {
		return this.aCadenaNumero;
	}

	public void setCadenaNumero(String pCadenaNumero) {
		this.aCadenaNumero = mc.concatenarCadena(
				mc.obtenerSubCadena("000000000000", 0, 12 - mc.obtenerLongitudCadena(pCadenaNumero)), pCadenaNumero);
	}

	public int getModo() {
		return this.aModo;
	}

	public void setModo(int pModo) {
		this.aModo = pModo;
	}

	public int getDecimales() {
		return this.aDecimales;
	}

	public void setDecimales(int pDecimales) {
		this.aDecimales = pDecimales;
	}

	public int getPosicion() {
		return this.aPosicion;
	}

	public void setPosicion(int pPosicion) {
		this.aPosicion = pPosicion;
	}

	public boolean esDecimal() {
		return this.aDecimal;
	}

	public void setDecimal(boolean pDecimal) {
		this.aDecimal = pDecimal;
	}

	public boolean esDigitoSignificativo() {
		return this.aDigitoSignificativo;
	}

	public void setDigitoSignificativo(boolean pDigitoSignificativo) {
		this.aDigitoSignificativo = pDigitoSignificativo;
	}

	private void llenarValores() {
		try {
			String[][] descripcionCifras_local = {
					{ "UNA ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "UN " },
					{ "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS ", "DIECISIETE ", "DIECIOCHO ",
							"DIECINUEVE ", "" },
					{ "DIEZ ", "VEINTE ", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ",
							"NOVENTA ", "VEINTI" },
					{ "CIEN ", "DOSCIENTAS ", "TRESCIENTAS ", "CUATROCIENTAS ", "QUINIENTAS ", "SEISCIENTAS ",
							"SETECIENTAS ", "OCHOCIENTAS ", "NOVECIENTAS ", "CIENTO " },
					{ "CIEN ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
							"SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS ", "CIENTO " },
					{ "MIL ", "MILLON ", "MILLONES ", "CERO ", "Y ", "UNO ", "DOS ", "CON ", "", "" } };

			setDescripcionCifras(descripcionCifras_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
	}

	private void obtenerValoresLetras() {
		try {
			String[][] descripcionCifras_local = getDescripcionCifras();
			if (getModo() == 1) {
				int j;
				for (j = 0; j < 1; j++) {
					descripcionCifras_local[0][j] = descripcionCifras_local[5][j + 5];
				}
				for (j = 0; j < 9; j++) {
					descripcionCifras_local[3][j] = descripcionCifras_local[4][j];
				}
			}
			setDescripcionCifras(descripcionCifras_local);
			descripcionCifras_local = (String[][]) null;
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
	}

	private String obtenerEquivalenteUnidad() {
		String numeroLetras_local = "";
		int auxiliar_local = -1;

		try {
			numeroLetras_local = getNumeroLetras();
			auxiliar_local = getPosicion() - 2;

			if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) != '0'
					&& mc.obtenerCarater(getCadenaNumero(), getPosicion() - 1) != '1'
					&& (!mc.sonCadenasIguales(
							mc.obtenerSubCadena(getCadenaNumero(), auxiliar_local, auxiliar_local + 3), "001")
							|| (getPosicion() != 2 && getPosicion() != 8))) {

				if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) == '1' && getPosicion() <= 6) {

					numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[0][9]);
				} else {

					numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[0][Integer
							.parseInt(String.valueOf(mc.obtenerCarater(getCadenaNumero(), getPosicion()))) - 1]);
				}
			}

			if ((getPosicion() == 2 || getPosicion() == 8)
					&& !mc.sonCadenasIguales(mc.obtenerSubCadena(getCadenaNumero(), auxiliar_local, auxiliar_local + 3),
							"000")) {

				numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[5][0]);
			}

			if (getPosicion() == 5) {
				if (mc.sonCadenasIguales(mc.obtenerSubCadena(getCadenaNumero(), 0, 6), "000001")) {

					numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[5][1]);
				} else {

					numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[5][2]);
				}
			}

			setNumeroLetras(numeroLetras_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return numeroLetras_local;
	}

	private String obtenerEquivalenteDecena() {
		String numeroLetras_local = "";

		try {
			numeroLetras_local = getNumeroLetras();

			if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) != '0') {
				if (mc.obtenerCarater(getCadenaNumero(), getPosicion() + 1) == '0') {
					numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[2][Integer
							.parseInt(String.valueOf(mc.obtenerCarater(getCadenaNumero(), getPosicion()))) - 1]);
				} else if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) == '1') {
					numeroLetras_local = mc
							.concatenarCadena(numeroLetras_local,
									getDescripcionCifras()[1][Integer
											.parseInt(String
													.valueOf(mc.obtenerCarater(getCadenaNumero(), getPosicion() + 1)))
											- 1]);
				} else if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) == '2') {
					numeroLetras_local = mc.concatenarCadena(numeroLetras_local, getDescripcionCifras()[2][9]);
				} else {
					numeroLetras_local = mc.concatenarCadena(numeroLetras_local,
							getDescripcionCifras()[2][Integer
									.parseInt(String.valueOf(mc.obtenerCarater(getCadenaNumero(), getPosicion()))) - 1]
									+ getDescripcionCifras()[5][4]);
				}
			}

			setNumeroLetras(numeroLetras_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return numeroLetras_local;
	}

	private String obtenerEquivalenteCentena() {
		String numeroLetras_local = "";
		int posicion1_local = -1;
		int auxliliar_local = -1;

		try {
			posicion1_local = 4;
			numeroLetras_local = getNumeroLetras();

			if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) != '0') {
				if (getPosicion() >= 6) {
					posicion1_local--;
				}
				auxliliar_local = getPosicion() + 1;
				if (mc.obtenerCarater(getCadenaNumero(), getPosicion()) == '1' && !mc.sonCadenasIguales(
						mc.obtenerSubCadena(getCadenaNumero(), auxliliar_local, auxliliar_local + 2), "00")) {

					numeroLetras_local = mc.concatenarCadena(numeroLetras_local,
							getDescripcionCifras()[posicion1_local][9]);
				} else {
					numeroLetras_local = mc.concatenarCadena(numeroLetras_local,
							getDescripcionCifras()[posicion1_local][Integer.parseInt(
									String.valueOf(mc.obtenerCarater(getCadenaNumero(), getPosicion()))) - 1]);
				}
			}

			setNumeroLetras(numeroLetras_local);
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}

		return numeroLetras_local;
	}

	private void asignarValores(String pNumero, int pModo) {
		String cadenaNumero_local = null;
		String cadenaDecimales_local = null;
		int posicionPunto_local = -1;
		int posicionFinal_local = -1;

		if (pNumero == ConstantesGeneral.VALOR_NULO) {
			return;
		}
		try {
			setModo(pModo);
			cadenaNumero_local = pNumero;
			cadenaDecimales_local = String.valueOf(0);
			if (mc.verificarExistenciaSubCadena(cadenaNumero_local, String.valueOf('.'))) {
				posicionPunto_local = mc.obtenerPosicionSubCadena(cadenaNumero_local, String.valueOf('.'));
				posicionFinal_local = mc.obtenerLongitudCadena(cadenaNumero_local);
				if (posicionPunto_local + 3 < mc.obtenerLongitudCadena(cadenaNumero_local)) {
					posicionFinal_local = posicionPunto_local + 3;
				}
				cadenaDecimales_local = mc.obtenerSubCadena(cadenaNumero_local, posicionPunto_local + 1,
						posicionFinal_local);
				cadenaNumero_local = mc.obtenerSubCadena(cadenaNumero_local, 0, posicionPunto_local);
			}
			setCadenaNumero(cadenaNumero_local);
			setDecimales(Integer.parseInt(cadenaDecimales_local));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {

			cadenaNumero_local = null;
			cadenaDecimales_local = null;
		}
	}

	private boolean verificarArgumentosValidos(String pNumero, int pModo) {
		boolean argumentosValidos_local = false;
		boolean numeroValido_local = false;
		boolean modoValido_local = false;
		double numero_local = 0.0D;
		if (pNumero == ConstantesGeneral.VALOR_NULO) {
			return argumentosValidos_local;
		}
		try {
			numero_local = Double.parseDouble(pNumero);
			numeroValido_local = (numero_local > 0.0D && numero_local < 9.9999999999999E11D);
			modoValido_local = (pModo > 0 && pModo < 2);
			argumentosValidos_local = (numeroValido_local && modoValido_local);
			if (argumentosValidos_local) {
				asignarValores(pNumero, pModo);
			}
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		}
		return argumentosValidos_local;
	}

	public String obtenerNumeroEnLetras(String pNumero, int pModo, String pValorInicial, String pValorEntero,
			String pValorSeparador, String pValorDecimal) {
		boolean existenDecimales_local = false;
		boolean esDecimal_local = false;
		int decimales_local = -1;
		int longitudCadena_local = 0;
		String numeroLetras_local = null;

		if (pNumero == ConstantesGeneral.VALOR_NULO) {
			return numeroLetras_local;
		}
		if (pValorEntero == ConstantesGeneral.VALOR_NULO) {
			return numeroLetras_local;
		}
		if (pValorSeparador == ConstantesGeneral.VALOR_NULO) {
			return numeroLetras_local;
		}
		if (pValorDecimal == ConstantesGeneral.VALOR_NULO) {
			return numeroLetras_local;
		}

		try {
			if(pNumero.indexOf(".") > 0){
				int iS = pNumero.indexOf(".");
				String pNumeroDiscard = pNumero.substring(iS + 1);
				double valueDiscard = Double.parseDouble(pNumeroDiscard);
				if( valueDiscard == 0)
				{
					pNumero = pNumero.substring(0, iS +1-1);
				}
			}
			setNumeroLetras("");
			if (verificarArgumentosValidos(pNumero, pModo)) {
				obtenerValoresLetras();
				numeroLetras_local = "";
				decimales_local = 0;
				while (true) {
					for (int posicion_local = 0; posicion_local < 12; posicion_local++) {
						setPosicion(posicion_local);
						if (esDigitoSignificativo() || mc.obtenerCarater(getCadenaNumero(), posicion_local) != '0') {

							setDigitoSignificativo(true);
							switch (posicion_local % 3) {
							case 0:
								numeroLetras_local = obtenerEquivalenteCentena();
								break;
							case 1:
								numeroLetras_local = obtenerEquivalenteDecena();
								break;
							case 2:
								numeroLetras_local = obtenerEquivalenteUnidad();
								break;
							}

						}
					}
					if (mc.sonCadenasIguales(numeroLetras_local, "")) {
						numeroLetras_local = getDescripcionCifras()[5][3];
					}
					if (!existenDecimales_local) {
						longitudCadena_local = mc.obtenerLongitudCadena(getCadenaNumero());
						if (mc.sonCadenasIguales(
								mc.obtenerSubCadena(getCadenaNumero(), longitudCadena_local - 6, longitudCadena_local),
								"000000")) {
							numeroLetras_local = mc.concatenarCadena(numeroLetras_local, pValorInicial);
						}
						numeroLetras_local = mc.concatenarCadena(numeroLetras_local, pValorEntero);
					}
					decimales_local = getDecimales();
					setDecimal((decimales_local != 0));
					esDecimal_local = esDecimal();
					if (esDecimal_local) {
						existenDecimales_local = esDecimal_local;
						numeroLetras_local = mc.concatenarCadena(numeroLetras_local, pValorSeparador);
						setNumeroLetras(numeroLetras_local);
						setCadenaNumero(String.valueOf(decimales_local));
						setDecimales(0);
						setDigitoSignificativo(!esDecimal_local);
					}
					if (!esDecimal_local) {
						if (existenDecimales_local) {
							numeroLetras_local = mc.concatenarCadena(numeroLetras_local, pValorDecimal);
						}
						setNumeroLetras(numeroLetras_local);
						if(numeroLetras_local.contains(pValorEntero) ){
							break;
						}
					} else {
						continue;
					}

					numeroLetras_local = null;
				}
			}
			if (mc.esCadenaNumerica(pNumero, false) && Double.parseDouble(pNumero) == 0.0D)
				setNumeroLetras(mc.concatenarCadena("CERO ", pValorEntero));
		} catch (Exception excepcion) {
			excepcion.printStackTrace();
		} finally {
			numeroLetras_local = null;
		}

		return getNumeroLetras();
	}
}
/*
 * Location:
 * D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisne\\
 * utilidades\ConversorNumerosLetras.class Java compiler version: 6 (50.0)
 * JD-Core Version: 1.1.3
 */