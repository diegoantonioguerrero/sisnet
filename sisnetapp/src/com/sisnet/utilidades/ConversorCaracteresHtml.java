/*
 * ConversorCaracteresHtml.java
 *
 * Created on 15 de agosto de 2006, 08:23 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.sisnet.utilidades;
import java.text.Normalizer;
import java.util.regex.Pattern;

import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesConversorCaracteresHtml;
import com.sisnet.constantes.ConstantesGeneral;
/**
 * clase encargada de convertir los caracteres de cadenas normales en caracteres HTML
 * @author Sisnet
 */
public class ConversorCaracteresHtml{
    
    /**
     * manejador de cadenas
     */
    private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
    
    /* Modific\u00f3 ZDOR fecha: */
    /* Modific\u00f3 GEOT fecha: */
    /**
     * Creates a new instance of ConversorCaracteresHtml
     */
    public ConversorCaracteresHtml() {
    }    
    
    /* Modific\u00f3 ZDOR fecha: 24/09/2007 */
    /* Modific\u00f3 GEOT fecha: 24/09/2007 */
    /**
     * Coloca una cadena Html
     * @param pCadenaConvertir String
     */
    public String getCadenaHtml(String pCadenaConvertir) {
        String cadenaHTML_local = ConstantesGeneral.CADENA_VACIA;
        
        if(pCadenaConvertir == ConstantesGeneral.VALOR_NULO){
            return cadenaHTML_local;
        }    
        try{
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeA),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeA);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeE),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeE);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeI),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeI);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeO),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeO);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeU),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeU);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildea),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildea);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildee),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildee);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildei),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildei);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeo),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeo);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeu),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlTildeu);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTilden),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmln);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, String.valueOf(ConstantesConversorCaracteresHtml.const_CaracterTildeN),
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlN);
            pCadenaConvertir = mc.reemplazarCadena(pCadenaConvertir, ConstantesConversorCaracteresHtml.const_CadenaEspacio,
                    ConstantesConversorCaracteresHtml.const_EquivalenteHtmlEspacio);
            cadenaHTML_local = pCadenaConvertir;
        } catch(Exception excepcion){
            excepcion.printStackTrace();
        }
        return cadenaHTML_local;
    }
    
    public static String removeSpecialCharsAndAccents(String input) {
        // Normalizar el texto para descomponer los caracteres acentuados
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        // Eliminar caracteres especiales (diacríticos)
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String result = pattern.matcher(normalized).replaceAll("");

        // Eliminar caracteres no alfabéticos ni numéricos (excepto espacios y puntos)
        result = result.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit} .-]", "");

        // Reemplazar ñ y Ñ manualmente
        result = result.replace("ñ", "n").replace("Ñ", "N");

        return result;
    }
}    