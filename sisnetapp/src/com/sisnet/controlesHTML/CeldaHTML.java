package com.sisnet.controlesHTML;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
public class CeldaHTML
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private String aEstilo;
  private int aNumeroFila;
  private int aAlineacion;
  private int aNumeroFilasCombinadas;
  private int aNumeroColumnasCombinadas;
  private String aContenido;
  public CeldaHTML() {
    setEstilo("");
    setNumeroFila(0);
    setAlineacion(-1);
    setNumeroFilasCombinadas(0);
    setNumeroColumnasCombinadas(0);
    setContenido("");
  }
  public String getEstilo() {
    return this.aEstilo;
  }
  public void setEstilo(String pEstilo) {
    this.aEstilo = pEstilo;
  }
  public int getNumeroFila() {
    return this.aNumeroFila;
  }
  public void setNumeroFila(int pNumeroFila) {
    this.aNumeroFila = pNumeroFila;
  }
  public int getAlineacion() {
    return this.aAlineacion;
  }
  public void setAlineacion(int pAlineacion) {
    this.aAlineacion = pAlineacion;
  }
  public int getNumeroFilasCombinadas() {
    return this.aNumeroFilasCombinadas;
  }
  public void setNumeroFilasCombinadas(int pNumeroFilasCombinadas) {
    this.aNumeroFilasCombinadas = pNumeroFilasCombinadas;
  }
  public int getNumeroColumnasCombinadas() {
    return this.aNumeroColumnasCombinadas;
  }
  public void setNumeroColumnasCombinadas(int pNumeroColumnasCombinadas) {
    this.aNumeroColumnasCombinadas = pNumeroColumnasCombinadas;
  }
  public String getContenido() {
    return this.aContenido;
  }
  public void setContenido(String pContenido) {
    this.aContenido = pContenido;
  }
  private String obtenerAlineacion() {
    String alineacion_local = "";
    
    try {
      switch (getAlineacion()) {
        case com.sisnet.constantes.ConstantesHTML.const_AlineacionCentrada:
          alineacion_local = "center"; break;
        case com.sisnet.constantes.ConstantesHTML.const_AlineacionIzquierda:
          alineacion_local = "left"; break;
        case com.sisnet.constantes.ConstantesHTML.const_AlineacionDerecha:
          alineacion_local = "rigth"; break;
        case com.sisnet.constantes.ConstantesHTML.const_AlineacionJustificada:
          alineacion_local = "justify"; break;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return alineacion_local;
  }
  public String dibujar() {
    String celda_local = "";
    
    try {
      celda_local = " <th ";
      celda_local = mc.concatenarCadena(celda_local, mcHTML.conformarAtributoHTML(" class=\"", getEstilo()));
      celda_local = mc.concatenarCadena(celda_local, mcHTML.conformarAtributoHTML(" align=\"", obtenerAlineacion()));
      
      celda_local = mc.concatenarCadena(celda_local, mcHTML.conformarAtributoHTML(" colspan=\"", String.valueOf(getNumeroColumnasCombinadas())));
      
      celda_local = mc.concatenarCadena(celda_local, mcHTML.conformarAtributoHTML(" rowspan=\"", String.valueOf(getNumeroFilasCombinadas())));
      
      celda_local = mc.concatenarCadena(celda_local, getContenido());
      
      celda_local = mc.concatenarCadena(celda_local, " </th>\n ");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return celda_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\CeldaHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */