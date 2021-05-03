package com.sisnet.aplicacion.manejadores;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import java.text.DecimalFormat;
import java.util.Iterator;
public class ManejadorCadenas
{
  private static com.sisnet.aplicacion.manejadores.ManejadorCadenas manejadorCadenasSingleton = null;
  public static com.sisnet.aplicacion.manejadores.ManejadorCadenas getManejadorCadenas() {
    if (manejadorCadenasSingleton == null) {
      manejadorCadenasSingleton = new com.sisnet.aplicacion.manejadores.ManejadorCadenas();
    }
    return manejadorCadenasSingleton;
  }
  public String obtenerSubCadena(String pCadena, int pPosicionInicial, int pPosicionFinal) {
    String subCadena_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return subCadena_local;
    }
    if (pPosicionInicial > pPosicionFinal) {
      return subCadena_local;
    }
    if (pPosicionInicial == -1) {
      return subCadena_local;
    }
    if (pPosicionFinal == -1) {
      return subCadena_local;
    }
    
    try {
      subCadena_local = pCadena;
      if (obtenerLongitudCadena(pCadena) >= pPosicionFinal) {
        subCadena_local = pCadena.substring(pPosicionInicial, pPosicionFinal);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return subCadena_local;
  }
  public String concatenarCadena(String pCadenaPrincipal, String pCadenaConcatenar) {
    String cadena_local = "";
    
    if (pCadenaPrincipal == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    if (pCadenaConcatenar == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    try {
      cadena_local = pCadenaPrincipal.concat(pCadenaConcatenar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadena_local;
  }
  public char obtenerCarater(String pCadena, int pPosicionCaracter) {
    char caracter_local = '\n';
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return caracter_local;
    }
    try {
      caracter_local = pCadena.charAt(pPosicionCaracter);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return caracter_local;
  }
  public String convertirAMinusculas(String pCadena) {
    String minuscula_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return minuscula_local;
    }
    try {
      minuscula_local = pCadena.toLowerCase();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return minuscula_local;
  }
  public String convertirAMayusculas(String pCadena) {
    String mayuscula_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return mayuscula_local;
    }
    try {
      mayuscula_local = pCadena.toUpperCase();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return mayuscula_local;
  }
  public String borrarEspaciosLaterales(String pCadena) {
    String espaciosLaterales_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return espaciosLaterales_local;
    }
    try {
      espaciosLaterales_local = pCadena.trim();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return espaciosLaterales_local;
  }
  public String reemplazarCadena(String pCadena, String pCadenaBusqueda, String pCadenaNueva) {
    String cadena_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    if (pCadenaBusqueda == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    if (pCadenaNueva == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    
    try {
      if (sonCadenasIguales(pCadenaBusqueda, String.valueOf('\\'))) {
        pCadenaBusqueda = concatenarCadena(pCadenaBusqueda, String.valueOf('\\'));
      }
      cadena_local = pCadena.replaceAll(pCadenaBusqueda, pCadenaNueva);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadena_local;
  }
  public String reemplazarCaracter(String pCadena, char pCaracterBusqueda, char pCaracterNuevo) {
    String caracter_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return caracter_local;
    }
    if (Character.valueOf(pCaracterBusqueda) == ConstantesGeneral.VALOR_NULO) {
      return caracter_local;
    }
    if (Character.valueOf(pCaracterNuevo) == ConstantesGeneral.VALOR_NULO) {
      return caracter_local;
    }
    try {
      caracter_local = pCadena.replace(pCaracterBusqueda, pCaracterNuevo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return caracter_local;
  }
  public String borrarEspacios(String pCadena) {
    String espacios_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return espacios_local;
    }
    try {
      espacios_local = reemplazarCadena(pCadena, String.valueOf(' '), "");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return espacios_local;
  }
  public int obtenerLongitudCadena(String pCadena) {
    int longitudCadena_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return longitudCadena_local;
    }
    try {
      longitudCadena_local = pCadena.length();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return longitudCadena_local;
  }
  public ListaCadenas fraccionarCadenaCaracteres(String pCadena, int pNumeroCaracteresGrupo) {
    ListaCadenas cadenaFraccionada_local = null;
    String cadenaAuxiliar_local = null;
    int numeroGrupos_local = -1;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    int longitudCadena_local = -1;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadenaFraccionada_local;
    }
    try {
      posicionInicial_local = 0;
      cadenaFraccionada_local = new ListaCadenas();
      longitudCadena_local = obtenerLongitudCadena(pCadena) + 1;
      if (pNumeroCaracteresGrupo >= longitudCadena_local) {
        cadenaFraccionada_local.adicionar(pCadena);
      } else {
        numeroGrupos_local = longitudCadena_local / pNumeroCaracteresGrupo;
        if (longitudCadena_local % pNumeroCaracteresGrupo != 0) {
          numeroGrupos_local++;
        }
        for (int i = 1; i <= numeroGrupos_local; i++) {
          posicionFinal_local = pNumeroCaracteresGrupo * i;
          if (posicionFinal_local >= longitudCadena_local) {
            posicionFinal_local = longitudCadena_local - 1;
          }
          cadenaAuxiliar_local = obtenerSubCadena(pCadena, posicionInicial_local, posicionFinal_local);
          cadenaFraccionada_local.adicionar(cadenaAuxiliar_local);
          posicionInicial_local = posicionFinal_local;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cadenaAuxiliar_local = null;
    } 
    return cadenaFraccionada_local;
  }
  public ListaCadenas fraccionarContenidoDocumento(String pCadena) {
    ListaCadenas cadenaFraccionada_local = null;
    String cadenaAuxiliar_local = null;
    String nuevaCadena_local = null;
    String cadenaFinLinea_local = null;
    String cadenaListaCadenas_local = null;
    int posicionInicial_local = 0;
    int posicionFinal_local = 0;
    int longitudCadenaMarca_local = -1;
    int longitudCadena_local = -1;
    ListaCadenas listaCadenasAuxiliar_local = null;
    Iterator iterator_local = null;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadenaFraccionada_local;
    }
    try {
      cadenaFinLinea_local = "\n";
      cadenaFraccionada_local = new ListaCadenas();
      nuevaCadena_local = pCadena;
      longitudCadenaMarca_local = obtenerLongitudCadena("{S}");
      nuevaCadena_local = reemplazarCadena(nuevaCadena_local, "\r\n", "{S}");
      nuevaCadena_local = reemplazarCadena(nuevaCadena_local, cadenaFinLinea_local, "{L}");
      
      while (posicionFinal_local != -1) {
        longitudCadena_local = obtenerLongitudCadena(nuevaCadena_local);
        posicionFinal_local = obtenerPosicionSubCadena(nuevaCadena_local, "{L}");
        if (posicionFinal_local == -1) {
          posicionFinal_local = obtenerPosicionSubCadena(nuevaCadena_local, "{S}");
        }
        
        if (posicionFinal_local != -1) {
          if (posicionFinal_local > longitudCadena_local) {
            posicionFinal_local = longitudCadena_local;
          }
          cadenaAuxiliar_local = obtenerSubCadena(nuevaCadena_local, posicionInicial_local, posicionFinal_local + longitudCadenaMarca_local);
          listaCadenasAuxiliar_local = fraccionarCadenaCaracteres(cadenaAuxiliar_local, 255);
          iterator_local = listaCadenasAuxiliar_local.iterator();
          while (iterator_local.hasNext()) {
            cadenaListaCadenas_local = (String)iterator_local.next();
            if (iterator_local.hasNext()) {
              cadenaFraccionada_local.adicionar(concatenarCadena(cadenaListaCadenas_local, "{L}")); continue;
            } 
            cadenaFraccionada_local.adicionar(cadenaListaCadenas_local);
          } 
          
          nuevaCadena_local = obtenerSubCadena(nuevaCadena_local, posicionFinal_local + longitudCadenaMarca_local, longitudCadena_local); continue;
        } 
        cadenaFraccionada_local.adicionar(concatenarCadena(nuevaCadena_local, "{L}"));
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      listaCadenasAuxiliar_local = null;
      iterator_local = null;
      cadenaAuxiliar_local = null;
      cadenaFinLinea_local = null;
      nuevaCadena_local = null;
      cadenaListaCadenas_local = null;
    } 
    
    return cadenaFraccionada_local;
  }
  public String[] fraccionarCadena(String pCadena, String pSeparador) {
    String[] cadenaFraccionada_local = null;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadenaFraccionada_local;
    }
    if (pSeparador == ConstantesGeneral.VALOR_NULO) {
      return cadenaFraccionada_local;
    }
    
    try {
      cadenaFraccionada_local = pCadena.split(pSeparador);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadenaFraccionada_local;
  }
  public String completarCadena(String pCadena, boolean pCompletarDerecha, char pCaracter, int pLongitud) {
    String cadena_local = "";
    int camposCompletar_local = -1;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    if (Character.valueOf(pCaracter) == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    try {
      if (obtenerLongitudCadena(pCadena) >= pLongitud) {
        return pCadena;
      }
      camposCompletar_local = pLongitud - obtenerLongitudCadena(pCadena);
      
      for (int i = 0; i < camposCompletar_local; i++) {
        cadena_local = concatenarCadena(cadena_local, String.valueOf(pCaracter));
      }
      if (pCompletarDerecha) {
        cadena_local = concatenarCadena(pCadena, cadena_local);
      } else {
        cadena_local = concatenarCadena(cadena_local, pCadena);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadena_local;
  }
  public String convertirFormatoFechaDDMMAAAAaAAAAMMDDD(String pFecha) {
    String fecha_local = "null";
    String dia_local = null;
    String mes_local = null;
    String anio_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fecha_local;
    }
    try {
      if (obtenerLongitudCadena(pFecha) > 0) {
        dia_local = "";
        mes_local = "";
        anio_local = "";
        
        dia_local = obtenerSubCadena(pFecha, 0, 2);
        mes_local = obtenerSubCadena(pFecha, 3, 5);
        anio_local = obtenerSubCadena(pFecha, 6, 10);
        fecha_local = anio_local + '-' + mes_local + '-' + dia_local;
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      dia_local = null;
      mes_local = null;
      anio_local = null;
    } 
    return fecha_local;
  }
  public String convertirFormatoFechaDDMMAAAA(String pFecha) {
    String fecha_local = "null";
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fecha_local;
    }
    try {
      if (obtenerLongitudCadena(pFecha) > 0) {
        fecha_local = reemplazarCadena(pFecha, String.valueOf('/'), String.valueOf('-'));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fecha_local;
  }
  public String convertirFormatoFechaAAAAMMDDaDDMMAAAA(String pFecha) {
    String fecha_local = "null";
    String dia_local = null;
    String mes_local = null;
    String anio_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fecha_local;
    }
    
    try {
      anio_local = obtenerSubCadena(pFecha, 0, 4);
      mes_local = obtenerSubCadena(pFecha, 5, 7);
      dia_local = obtenerSubCadena(pFecha, 8, 10);
      fecha_local = dia_local + '/' + mes_local + '/' + anio_local;
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      dia_local = null;
      mes_local = null;
      anio_local = null;
    } 
    return fecha_local;
  }
  public String convertirFormatoFechaAAAAMMDD(String pFecha) {
    String fecha_local = "null";
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return fecha_local;
    }
    try {
      if (obtenerLongitudCadena(pFecha) > 0) {
        fecha_local = reemplazarCadena(pFecha, String.valueOf('-'), String.valueOf('/'));
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fecha_local;
  }
  public String truncarCadena(String pCadena, int pLongitud) {
    String truncarCadena_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return truncarCadena_local;
    }
    try {
      truncarCadena_local = pCadena;
      if (obtenerLongitudCadena(pCadena) > pLongitud) {
        truncarCadena_local = obtenerSubCadena(pCadena, 0, pLongitud);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return truncarCadena_local;
  }
  public String colocarEntreParentesis(String pCadena) {
    String entreParentesis_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return entreParentesis_local;
    }
    try {
      entreParentesis_local = concatenarCadena(String.valueOf('('), pCadena);
      entreParentesis_local = concatenarCadena(entreParentesis_local, String.valueOf(')'));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return entreParentesis_local;
  }
  public String colocarEntreComillas(String pCadena) {
    String entreComillas_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return entreComillas_local;
    }
    try {
      entreComillas_local = concatenarCadena(String.valueOf('\''), pCadena);
      entreComillas_local = concatenarCadena(entreComillas_local, String.valueOf('\''));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return entreComillas_local;
  }
  public String colocarEntreComillasDobles(String pCadena) {
    String entreComillas_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return entreComillas_local;
    }
    try {
      entreComillas_local = concatenarCadena(String.valueOf('"'), pCadena);
      entreComillas_local = concatenarCadena(entreComillas_local, String.valueOf('"'));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return entreComillas_local;
  }
  public String convertirTildeANoTilde(String pCadenaConvertir) {
    String cadenaConvertida_local = "";
    
    if (pCadenaConvertir == ConstantesGeneral.VALOR_NULO) {
      return cadenaConvertida_local;
    }
    try {
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00e1'), "A");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00e9'), "E");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00ed'), "I");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00f3'), "O");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00fa'), "U");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00e1'), "a");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00e9'), "e");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00ed'), "i");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00f3'), "o");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00fa'), "u");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00f1'), "n");
      pCadenaConvertir = reemplazarCadena(pCadenaConvertir, String.valueOf('\u00f1'), "N");
      
      cadenaConvertida_local = pCadenaConvertir;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadenaConvertida_local;
  }
  public boolean esLetra(char pCaracter) {
    boolean letra_local = false;
    
    if (Character.valueOf(pCaracter) == ConstantesGeneral.VALOR_NULO) {
      return letra_local;
    }
    try {
      letra_local = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(pCaracter) != -1 || "abcdefghijklmnopqrstuvwxyz".indexOf(pCaracter) != -1);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return letra_local;
  }
  public boolean esNumero(char pCaracter) {
    boolean numero_local = false;
    
    if (Character.valueOf(pCaracter) == ConstantesGeneral.VALOR_NULO) {
      return numero_local;
    }
    try {
      numero_local = ("1234567890".indexOf(pCaracter) != -1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return numero_local;
  }
  public String quitarCaracteresNumericosIniciales(String pCadena) {
    String cadena_local = "";
    int posicion_local = -1;
    char caracter_local = '\n';
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    if (obtenerLongitudCadena(pCadena) == 0) {
      return cadena_local;
    }
    
    try {
      cadena_local = pCadena;
      for (int i = 0; i < obtenerLongitudCadena(pCadena); i++) {
        caracter_local = pCadena.charAt(i);
        if (esLetra(caracter_local)) {
          posicion_local = i;
          break;
        } 
      } 
      if (posicion_local != -1) {
        cadena_local = obtenerSubCadena(pCadena, posicion_local, obtenerLongitudCadena(pCadena));
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadena_local;
  }
  public String convertirCadenaFormatoNombre(String pCadena) {
    String cadenaFormatoNombre_local = "";
    char caracter_local = '\n';
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadenaFormatoNombre_local;
    }
    try {
      pCadena = borrarEspacios(pCadena);
      pCadena = quitarCaracteresNumericosIniciales(pCadena);
      pCadena = convertirTildeANoTilde(pCadena);
      for (int i = 0; i < obtenerLongitudCadena(pCadena); i++) {
        caracter_local = pCadena.charAt(i);
        if (esLetra(caracter_local) || esNumero(caracter_local)) {
          cadenaFormatoNombre_local = concatenarCadena(cadenaFormatoNombre_local, String.valueOf(caracter_local));
        }
      } 
      cadenaFormatoNombre_local = convertirAMinusculas(cadenaFormatoNombre_local);
      if (obtenerLongitudCadena(cadenaFormatoNombre_local) > 25) {
        cadenaFormatoNombre_local = obtenerSubCadena(cadenaFormatoNombre_local, 0, 25);
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadenaFormatoNombre_local;
  }
  public String concatenarValoresLista(ListaGeneral pListaGeneral) {
    String idTablas_local = "";
    Iterator iterador_local = null;
    ItemLista itemListaTablas_local = null;
    
    if (pListaGeneral == ConstantesGeneral.VALOR_NULO) {
      return idTablas_local;
    }
    try {
      iterador_local = pListaGeneral.iterator();
      while (iterador_local.hasNext()) {
        itemListaTablas_local = (ItemLista)iterador_local.next();
        idTablas_local = concatenarCadena(idTablas_local, colocarEntreComillas(itemListaTablas_local.getValorItem()));
        if (iterador_local.hasNext()) {
          idTablas_local = concatenarCadena(idTablas_local, String.valueOf(','));
        }
      } 
      idTablas_local = colocarEntreParentesis(idTablas_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterador_local = null;
      itemListaTablas_local = null;
    } 
    return idTablas_local;
  }
  private int obtenerNumeroVecesCaracterEnCadena(char pCaracter, String pCadena) {
    int numeroVeces_local = 0;
    int i_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return numeroVeces_local;
    }
    
    try {
      for (i_local = 0; i_local < obtenerLongitudCadena(pCadena); i_local++) {
        if (obtenerCarater(pCadena, i_local) == pCaracter) {
          numeroVeces_local++;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numeroVeces_local;
  }
  public boolean esCadenaNumerica(String pCadena, boolean pEsNumeroEntero) {
    boolean esCadenaNumerica_local = false;
    char caracter_local = '\n';
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esCadenaNumerica_local;
    }
    if (sonCadenasIguales(pCadena, "")) {
      return esCadenaNumerica_local;
    }
    
    try {
      if (sonCadenasIguales(pCadena, String.valueOf('-'))) {
        return esCadenaNumerica_local;
      }
      if (obtenerNumeroVecesCaracterEnCadena('-', pCadena) > 1) {
        return esCadenaNumerica_local;
      }
      for (int i = 0; i < obtenerLongitudCadena(pCadena); i++) {
        caracter_local = obtenerCarater(pCadena, i);
        if (pEsNumeroEntero) {
          if ("-1234567890".indexOf(caracter_local) == -1) {
            return esCadenaNumerica_local;
          }
        } else {
          if (verificarExistenciaSubCadena(pCadena, String.valueOf('E')) && obtenerNumeroVecesCaracterEnCadena('E', pCadena) > 1)
          {
            
            return esCadenaNumerica_local;
          }
          if ("-.1234567890E".indexOf(caracter_local) == -1) {
            return esCadenaNumerica_local;
          }
        } 
      } 
      esCadenaNumerica_local = true;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esCadenaNumerica_local;
  }
  public String darFormatoTitulo(String pCadena) {
    String formatoTitulo_local = "";
    String nuevaCadena_local = null;
    String[] subCadenas_local = null;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return formatoTitulo_local;
    }
    
    try {
      if (esCadenaVacia(pCadena)) {
        return formatoTitulo_local;
      }
      subCadenas_local = fraccionarCadena(pCadena, String.valueOf(' '));
      for (int i = 0; i < subCadenas_local.length; i++) {
        subCadenas_local[i] = convertirAMinusculas(subCadenas_local[i]);
        if (subCadenas_local[i].length() > 2 || i == 0) {
          nuevaCadena_local = convertirAMayusculas(String.valueOf(obtenerCarater(subCadenas_local[i], 0)));
          
          nuevaCadena_local = concatenarCadena(nuevaCadena_local, obtenerSubCadena(subCadenas_local[i], 1, subCadenas_local[i].length()));
          
          formatoTitulo_local = concatenarCadena(formatoTitulo_local, nuevaCadena_local);
        } else {
          formatoTitulo_local = concatenarCadena(formatoTitulo_local, subCadenas_local[i]);
        } 
        formatoTitulo_local = concatenarCadena(formatoTitulo_local, String.valueOf(' '));
      } 
      formatoTitulo_local = borrarEspaciosLaterales(formatoTitulo_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      nuevaCadena_local = null;
      subCadenas_local = null;
    } 
    return formatoTitulo_local;
  }
  public String complementarDirectorio(int pNivelesAnterioresDirectorio) {
    String directorio_local = "";
    try {
      for (int i = 0; i < pNivelesAnterioresDirectorio; i++) {
        directorio_local = concatenarCadena(directorio_local, "../");
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return directorio_local;
  }
  public boolean verificarExistenciaSubCadena(String pCadena, String pSubCadena) {
    boolean subCadena_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return subCadena_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return subCadena_local;
    }
    try {
      subCadena_local = (pCadena.indexOf(pSubCadena) != -1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return subCadena_local;
  }
  public boolean verificarExistenciaSubCadenaAPartirPosicion(String pCadena, String pSubCadena, int pPosicion) {
    boolean existenciaSubCadena_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return existenciaSubCadena_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return existenciaSubCadena_local;
    }
    try {
      existenciaSubCadena_local = (pCadena.indexOf(pSubCadena, pPosicion) != -1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return existenciaSubCadena_local;
  }
  public int obtenerPosicionSubCadena(String pCadena, String pSubCadena) {
    int posicionSubCadena_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return posicionSubCadena_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return posicionSubCadena_local;
    }
    try {
      posicionSubCadena_local = pCadena.indexOf(pSubCadena);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return posicionSubCadena_local;
  }
  public int obtenerPosicionSubCadenaIgnorarMayusculas(String pCadena, String pSubCadena) {
    int posicionSubCadena_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return posicionSubCadena_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return posicionSubCadena_local;
    }
    try {
      posicionSubCadena_local = convertirAMinusculas(pCadena).indexOf(convertirAMinusculas(pSubCadena));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return posicionSubCadena_local;
  }
  public int obtenerPosicionSubCadenaAPartirPosicion(String pCadena, String pSubCadena, int pPosicionAPartir) {
    int posicionSubCadena_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return posicionSubCadena_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return posicionSubCadena_local;
    }
    try {
      posicionSubCadena_local = pCadena.indexOf(pSubCadena, pPosicionAPartir);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return posicionSubCadena_local;
  }
  public int obtenerUltimaPosicionSubCadena(String pCadena, String pSubCadena) {
    int ultimaPosicion_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return ultimaPosicion_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return ultimaPosicion_local;
    }
    try {
      ultimaPosicion_local = pCadena.lastIndexOf(pSubCadena);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return ultimaPosicion_local;
  }
  public int obtenerParteNumericaCadena(String pCadena) {
    String parteNumerica_local = "";
    int posicionFinal_local = -1;
    boolean existeNumero_local = false;
    char caracter_local = '\n';
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return Integer.valueOf(parteNumerica_local).intValue();
    }
    try {
      posicionFinal_local = obtenerLongitudCadena(pCadena) - 1;
      if (esNumero(pCadena.charAt(posicionFinal_local))) {
        existeNumero_local = true;
        while (posicionFinal_local >= 0 && existeNumero_local) {
          caracter_local = pCadena.charAt(posicionFinal_local);
          parteNumerica_local = String.valueOf(caracter_local) + parteNumerica_local;
          posicionFinal_local--;
          existeNumero_local = (posicionFinal_local >= 0 && esNumero(pCadena.charAt(posicionFinal_local)));
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return Integer.valueOf(parteNumerica_local).intValue();
  }
  public String obtenerParteAlfabeticaCadena(String pCadena) {
    String parteAlfabetica_local = "";
    int posicionFinal_local = -1;
    boolean existeNumero_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return parteAlfabetica_local;
    }
    try {
      posicionFinal_local = obtenerLongitudCadena(pCadena) - 1;
      if (esNumero(pCadena.charAt(posicionFinal_local))) {
        existeNumero_local = true;
        while (posicionFinal_local >= 0 && existeNumero_local) {
          existeNumero_local = esNumero(pCadena.charAt(posicionFinal_local));
          if (existeNumero_local) {
            posicionFinal_local--;
          }
        } 
      } 
      parteAlfabetica_local = obtenerSubCadena(pCadena, 0, posicionFinal_local + 1);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return parteAlfabetica_local;
  }
  public String formatearNumeroEntero(long pNumero) {
    String formatearNumero_local = "";
    DecimalFormat numero_local = null;
    
    try {
      numero_local = new DecimalFormat("#,##0;-#,##0");
      formatearNumero_local = numero_local.format(pNumero);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      numero_local = null;
    } 
    return formatearNumero_local;
  }
  public String formatearNumeroReal(double pNumero) {
    String formatearNumero_local = "";
    DecimalFormat numero_local = null;
    
    try {
      numero_local = new DecimalFormat("#,##0.00#;-#,##0.00#");
      formatearNumero_local = numero_local.format(pNumero);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      numero_local = null;
    } 
    return formatearNumero_local;
  }
  public String convertirEstiloLetraCapital(String pCadena) {
    String letraCapital_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return letraCapital_local;
    }
    try {
      letraCapital_local = convertirAMayusculas(obtenerSubCadena(pCadena, 0, 1));
      
      letraCapital_local = concatenarCadena(letraCapital_local, convertirAMinusculas(obtenerSubCadena(pCadena, 1, obtenerLongitudCadena(pCadena))));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return letraCapital_local;
  }
  public ListaCadenas obtenerParrafoComoListaCadenas(String pParrafo) {
    ListaCadenas listaCadenas_local = null;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String cadena_local = null;
    
    if (pParrafo == ConstantesGeneral.VALOR_NULO) {
      return listaCadenas_local;
    }
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (!sonCadenasIguales(pParrafo, "")) {
        posicionInicial_local = 0;
        while (verificarExistenciaSubCadenaAPartirPosicion(pParrafo, "\r\n", posicionInicial_local)) {
          posicionFinal_local = obtenerPosicionSubCadenaAPartirPosicion(pParrafo, "\r\n", posicionInicial_local);
          
          cadena_local = borrarEspaciosLaterales(obtenerSubCadena(pParrafo, posicionInicial_local, posicionFinal_local));
          if (!sonCadenasIguales(cadena_local, "")) {
            listaCadenas_local.adicionar(cadena_local);
          }
          posicionInicial_local = posicionFinal_local + obtenerLongitudCadena("\r\n");
        } 
        cadena_local = borrarEspaciosLaterales(obtenerSubCadena(pParrafo, posicionInicial_local, obtenerLongitudCadena(pParrafo)));
        
        if (!sonCadenasIguales(cadena_local, "")) {
          listaCadenas_local.adicionar(cadena_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      cadena_local = null;
    } 
    return listaCadenas_local;
  }
  public boolean verificarFormatoFecha(String pFecha) {
    boolean formatoValido_local = false;
    int numeroErrores_local = 0;
    int mesFecha_local = -1;
    int diaFecha_local = -1;
    String anio_local = null;
    String mes_local = null;
    String dia_local = null;
    String separador_local = null;
    String[] fecha_local = null;
    
    if (pFecha == ConstantesGeneral.VALOR_NULO) {
      return formatoValido_local;
    }
    
    try {
      separador_local = String.valueOf('/');
      if (verificarExistenciaSubCadena(pFecha, String.valueOf('-'))) {
        separador_local = String.valueOf('-');
      }
      fecha_local = fraccionarCadena(pFecha, separador_local);
      if (fecha_local.length == 3) {
        anio_local = fecha_local[0];
        mes_local = fecha_local[1];
        dia_local = fecha_local[2];
        if (!esCadenaNumerica(anio_local, true) || obtenerLongitudCadena(anio_local) != 4)
        {
          numeroErrores_local++;
        }
        if (esCadenaNumerica(mes_local, true) && obtenerLongitudCadena(mes_local) == 2) {
          mesFecha_local = Integer.parseInt(mes_local);
          if (mesFecha_local < 1 || mesFecha_local > 12) {
            numeroErrores_local++;
          }
        } else {
          numeroErrores_local++;
        } 
        if (esCadenaNumerica(dia_local, true) && obtenerLongitudCadena(dia_local) == 2) {
          diaFecha_local = Integer.parseInt(dia_local);
          if (diaFecha_local < 1 || diaFecha_local > 31) {
            numeroErrores_local++;
          }
        } else {
          numeroErrores_local++;
        }
      
      } else if (sonCadenasIgualesIgnorarMayusculas(pFecha, "null")) {
        numeroErrores_local++;
      } 
      
      formatoValido_local = (numeroErrores_local == 0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      mes_local = null;
      dia_local = null;
      anio_local = null;
      fecha_local = null;
      separador_local = null;
    } 
    return formatoValido_local;
  }
  public boolean verificarFormatoHora(String pHora) {
    boolean formatoValido_local = false;
    int numeroErrores_local = 0;
    int horas_local = -1;
    int minutos_local = -1;
    String cadenaHoras_local = null;
    String cadenaMinutos_local = null;
    String[] hora_local = null;
    
    if (pHora == ConstantesGeneral.VALOR_NULO) {
      return formatoValido_local;
    }
    
    try {
      hora_local = fraccionarCadena(pHora, String.valueOf(':'));
      if (hora_local.length <= 3 && hora_local.length > 0) {
        cadenaHoras_local = hora_local[0];
        if (esCadenaNumerica(cadenaHoras_local, true)) {
          horas_local = Integer.parseInt(cadenaHoras_local);
          if (horas_local < 0 || horas_local > 23) {
            numeroErrores_local++;
          }
        } else {
          numeroErrores_local++;
        } 
        if (hora_local.length > 1) {
          cadenaMinutos_local = hora_local[1];
          if (esCadenaNumerica(cadenaMinutos_local, true)) {
            minutos_local = Integer.parseInt(cadenaMinutos_local);
            if (minutos_local < 0 || minutos_local > 60) {
              numeroErrores_local++;
            }
          } else {
            numeroErrores_local++;
          }
        
        } 
      } else if (!sonCadenasIgualesIgnorarMayusculas(pHora, "null")) {
        numeroErrores_local++;
      } 
      
      formatoValido_local = (numeroErrores_local == 0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      hora_local = null;
      cadenaHoras_local = null;
      cadenaMinutos_local = null;
    } 
    return formatoValido_local;
  }
  public boolean esCadenaVacia(String pCadena) {
    boolean esCadenaVacia_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esCadenaVacia_local;
    }
    
    try {
      esCadenaVacia_local = sonCadenasIguales(pCadena, "");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esCadenaVacia_local;
  }
  public boolean verificarCadenaNull(String pCadena) {
    boolean esCadenaNull_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esCadenaNull_local;
    }
    
    try {
      esCadenaNull_local = sonCadenasIguales(pCadena, "null");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esCadenaNull_local;
  }
  public String quitarSaltoLinea(String pCadena) {
    String cadena_local = "";
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return cadena_local;
    }
    
    try {
      cadena_local = reemplazarCadena(pCadena, "\r\n", "");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadena_local;
  }
  public boolean verificarExpresionCondicionValida(String pExpresion) {
    boolean expresionCondicionValida_local = false;
    
    if (pExpresion == ConstantesGeneral.VALOR_NULO) {
      return expresionCondicionValida_local;
    }
    
    try {
      expresionCondicionValida_local = (verificarExistenciaSubCadena(pExpresion, String.valueOf('=')) || verificarExistenciaSubCadena(pExpresion, "NOIGUALA") || verificarExistenciaSubCadena(pExpresion, "MENORQUE") || verificarExistenciaSubCadena(pExpresion, "MAYORQUE") || verificarExistenciaSubCadena(pExpresion, "MENOROIGUALA") || verificarExistenciaSubCadena(pExpresion, "MAYOROIGUALA") || verificarExistenciaSubCadena(pExpresion, "ESCONTENIDOPOR") || verificarExistenciaSubCadena(pExpresion, "NOESCONTENIDOPOR") || verificarExistenciaSubCadena(pExpresion, "COMIENZACON") || verificarExistenciaSubCadena(pExpresion, "COMIENZACONPERONOIGUALA") || verificarExistenciaSubCadena(pExpresion, "NOCOMIENZACON") || verificarExistenciaSubCadena(pExpresion, "CONTIENEA") || verificarExistenciaSubCadena(pExpresion, "NOCONTIENEA") || verificarExistenciaSubCadena(pExpresion, "ESCOMIENZODE") || verificarExistenciaSubCadena(pExpresion, "ESCOMIENZODEPERONOIGUALA") || verificarExistenciaSubCadena(pExpresion, "NOESCOMIENZODE") || verificarExistenciaSubCadena(pExpresion, "NOCONTIENEPALABRAS") || verificarExistenciaSubCadena(pExpresion, "CONTIENEPALABRAS"));
    
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return expresionCondicionValida_local;
  }
  public String obtenerOperadorCondicionalEquivalente(String pOperadorCondicional) {
    String operadorEquivalente_local = "";
    
    if (pOperadorCondicional == ConstantesGeneral.VALOR_NULO) {
      return operadorEquivalente_local;
    }
    
    try {
      if (sonCadenasIgualesIgnorarMayusculas(pOperadorCondicional, "Y")) {
        operadorEquivalente_local = "and";
      }
      if (sonCadenasIgualesIgnorarMayusculas(pOperadorCondicional, "O")) {
        operadorEquivalente_local = "or";
      }
      if (sonCadenasIgualesIgnorarMayusculas(pOperadorCondicional, "and")) {
        operadorEquivalente_local = "and";
      }
      if (sonCadenasIgualesIgnorarMayusculas(pOperadorCondicional, "or")) {
        operadorEquivalente_local = "or";
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return operadorEquivalente_local;
  }
  public boolean sonCadenasIgualesIgnorarMayusculas(String pCadena, String pCadenaComparar) {
    boolean compararCadena_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return compararCadena_local;
    }
    if (pCadenaComparar == ConstantesGeneral.VALOR_NULO) {
      return compararCadena_local;
    }
    
    try {
      compararCadena_local = pCadena.equalsIgnoreCase(pCadenaComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return compararCadena_local;
  }
  public boolean sonCadenasIguales(String pCadena, String pCadenaComparar) {
    boolean compararCadena_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return compararCadena_local;
    }
    if (pCadenaComparar == ConstantesGeneral.VALOR_NULO) {
      return compararCadena_local;
    }
    
    try {
      compararCadena_local = pCadena.equals(pCadenaComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return compararCadena_local;
  }
  public boolean esCadenaMenorQue(String pCadenaUno, String pCadenaDos) {
    boolean cadenaMenor_local = false;
    
    if (pCadenaUno == ConstantesGeneral.VALOR_NULO) {
      return cadenaMenor_local;
    }
    if (pCadenaDos == ConstantesGeneral.VALOR_NULO) {
      return cadenaMenor_local;
    }
    
    try {
      cadenaMenor_local = (pCadenaUno.compareTo(pCadenaDos) < 0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadenaMenor_local;
  }
  public boolean esCadenaMayorQue(String pCadenaUno, String pCadenaDos) {
    boolean cadenaMayor_local = false;
    
    if (pCadenaUno == ConstantesGeneral.VALOR_NULO) {
      return cadenaMayor_local;
    }
    if (pCadenaDos == ConstantesGeneral.VALOR_NULO) {
      return cadenaMayor_local;
    }
    
    try {
      cadenaMayor_local = (pCadenaUno.compareTo(pCadenaDos) > 0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadenaMayor_local;
  }
  public boolean esCadenaMenorOIgualA(String pCadenaUno, String pCadenaDos) {
    boolean cadenaMenorIgual_local = false;
    
    if (pCadenaUno == ConstantesGeneral.VALOR_NULO) {
      return cadenaMenorIgual_local;
    }
    if (pCadenaDos == ConstantesGeneral.VALOR_NULO) {
      return cadenaMenorIgual_local;
    }
    
    try {
      cadenaMenorIgual_local = (esCadenaMenorQue(pCadenaUno, pCadenaDos) || sonCadenasIguales(pCadenaUno, pCadenaDos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadenaMenorIgual_local;
  }
  public boolean esCadenaMayorOIgualA(String pCadenaUno, String pCadenaDos) {
    boolean cadenaMayorIgual_local = false;
    
    if (pCadenaUno == ConstantesGeneral.VALOR_NULO) {
      return cadenaMayorIgual_local;
    }
    if (pCadenaDos == ConstantesGeneral.VALOR_NULO) {
      return cadenaMayorIgual_local;
    }
    
    try {
      cadenaMayorIgual_local = (esCadenaMayorQue(pCadenaUno, pCadenaDos) || sonCadenasIguales(pCadenaUno, pCadenaDos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadenaMayorIgual_local;
  }
  public boolean comienzaCon(String pCadena, String pCadenaComienzo) {
    boolean comienzaCon_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return comienzaCon_local;
    }
    if (pCadenaComienzo == ConstantesGeneral.VALOR_NULO) {
      return comienzaCon_local;
    }
    
    try {
      comienzaCon_local = pCadena.startsWith(pCadenaComienzo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return comienzaCon_local;
  }
  public boolean comienzaConPeroNoIgualA(String pCadena, String pCadenaComienzo) {
    boolean comienzaConPeroNoIgualA_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return comienzaConPeroNoIgualA_local;
    }
    if (pCadenaComienzo == ConstantesGeneral.VALOR_NULO) {
      return comienzaConPeroNoIgualA_local;
    }
    
    try {
      comienzaConPeroNoIgualA_local = (comienzaCon(pCadena, pCadenaComienzo) && !sonCadenasIguales(pCadena, pCadenaComienzo));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return comienzaConPeroNoIgualA_local;
  }
  public boolean esContenidoPor(String pCadenaContenida, String pCadena) {
    boolean esContenidoPor_local = false;
    
    if (pCadenaContenida == ConstantesGeneral.VALOR_NULO) {
      return esContenidoPor_local;
    }
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esContenidoPor_local;
    }
    
    try {
      esContenidoPor_local = (pCadena.indexOf(pCadenaContenida) != -1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esContenidoPor_local;
  }
  public boolean esContenidoPorPeroNoIgualA(String pCadenaContenida, String pCadena) {
    boolean esContenidoPorPeroNoIgualA_local = false;
    
    if (pCadenaContenida == ConstantesGeneral.VALOR_NULO) {
      return esContenidoPorPeroNoIgualA_local;
    }
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esContenidoPorPeroNoIgualA_local;
    }
    
    try {
      esContenidoPorPeroNoIgualA_local = (esContenidoPor(pCadenaContenida, pCadena) && !sonCadenasIguales(pCadena, pCadenaContenida));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esContenidoPorPeroNoIgualA_local;
  }
  public boolean contieneA(String pCadena, String pCadenaContenida) {
    boolean contieneA_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return contieneA_local;
    }
    if (pCadenaContenida == ConstantesGeneral.VALOR_NULO) {
      return contieneA_local;
    }
    
    try {
      contieneA_local = esContenidoPor(pCadenaContenida, pCadena);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return contieneA_local;
  }
  public boolean contieneAPeroNoIgualA(String pCadena, String pCadenaContenida) {
    boolean contieneAPeroNoIgualA_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return contieneAPeroNoIgualA_local;
    }
    if (pCadenaContenida == ConstantesGeneral.VALOR_NULO) {
      return contieneAPeroNoIgualA_local;
    }
    
    try {
      contieneAPeroNoIgualA_local = (esContenidoPor(pCadenaContenida, pCadena) && !sonCadenasIguales(pCadena, pCadenaContenida));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return contieneAPeroNoIgualA_local;
  }
  public boolean esComienzoDe(String pCadenaComienzo, String pCadena) {
    boolean esComienzoDe_local = false;
    
    if (pCadenaComienzo == ConstantesGeneral.VALOR_NULO) {
      return esComienzoDe_local;
    }
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esComienzoDe_local;
    }
    
    try {
      esComienzoDe_local = comienzaCon(pCadena, pCadenaComienzo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esComienzoDe_local;
  }
  public boolean esComienzoDePeroNoIgualA(String pCadenaComienzo, String pCadena) {
    boolean esComienzoDePeroNoIgualA = false;
    
    if (pCadenaComienzo == ConstantesGeneral.VALOR_NULO) {
      return esComienzoDePeroNoIgualA;
    }
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return esComienzoDePeroNoIgualA;
    }
    
    try {
      esComienzoDePeroNoIgualA = comienzaConPeroNoIgualA(pCadena, pCadenaComienzo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return esComienzoDePeroNoIgualA;
  }
  public boolean terminaCon(String pCadena, String pSubCadena) {
    boolean terminar_local = false;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return terminar_local;
    }
    if (pSubCadena == ConstantesGeneral.VALOR_NULO) {
      return terminar_local;
    }
    
    try {
      terminar_local = pCadena.endsWith(borrarEspaciosLaterales(pSubCadena));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return terminar_local;
  }
  public boolean contienePalabras(String pCadena, String pCadenaPalabras) {
    boolean contienePalabras_local = false;
    String[] palabras_local = null;
    int numeroPalabras_local = 0;
    int numeroPalabrasEncontradas = 0;
    int i_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return contienePalabras_local;
    }
    if (pCadenaPalabras == ConstantesGeneral.VALOR_NULO) {
      return contienePalabras_local;
    }
    
    try {
      palabras_local = fraccionarCadena(pCadenaPalabras, String.valueOf(' '));
      for (i_local = 0; i_local < palabras_local.length; i_local++) {
        if (!esCadenaVacia(borrarEspaciosLaterales(palabras_local[i_local]))) {
          numeroPalabras_local++;
          if (verificarExistenciaSubCadena(pCadena, palabras_local[i_local])) {
            numeroPalabrasEncontradas++;
          }
        } 
      } 
      contienePalabras_local = (numeroPalabras_local == numeroPalabrasEncontradas);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      palabras_local = null;
    } 
    
    return contienePalabras_local;
  }
  public boolean noContienePalabras(String pCadena, String pCadenaPalabras) {
    boolean noContienePalabras_local = false;
    String[] palabras_local = null;
    int numeroPalabras_local = 0;
    int numeroPalabrasNoEncontradas = 0;
    int i_local = 0;
    
    if (pCadena == ConstantesGeneral.VALOR_NULO) {
      return noContienePalabras_local;
    }
    if (pCadenaPalabras == ConstantesGeneral.VALOR_NULO) {
      return noContienePalabras_local;
    }
    
    try {
      palabras_local = fraccionarCadena(pCadenaPalabras, String.valueOf(' '));
      for (i_local = 0; i_local < palabras_local.length; i_local++) {
        if (!esCadenaVacia(borrarEspaciosLaterales(palabras_local[i_local]))) {
          numeroPalabras_local++;
          if (!verificarExistenciaSubCadena(pCadena, palabras_local[i_local])) {
            numeroPalabrasNoEncontradas++;
          }
        } 
      } 
      noContienePalabras_local = (numeroPalabras_local == numeroPalabrasNoEncontradas);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      palabras_local = null;
    } 
    
    return noContienePalabras_local;
  }
  public String recortarCadenaHora(String pCadenaHora) {
    String cadenaHora_local = "";
    int posicion_local = -1;
    
    if (pCadenaHora == ConstantesGeneral.VALOR_NULO) {
      return cadenaHora_local;
    }
    
    try {
      cadenaHora_local = pCadenaHora;
      if (obtenerLongitudCadena(pCadenaHora) > 4) {
        posicion_local = obtenerUltimaPosicionSubCadena(pCadenaHora, ":00");
        if (posicion_local > 0) {
          cadenaHora_local = obtenerSubCadena(pCadenaHora, 0, posicion_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return cadenaHora_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorCadenas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */