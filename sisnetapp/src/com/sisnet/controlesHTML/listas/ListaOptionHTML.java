package com.sisnet.controlesHTML.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.controlesHTML.OptionHTML;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Iterator;
public class ListaOptionHTML
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(OptionHTML pOptionHTML) {
    try {
      adicionar(pOptionHTML);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionar(String pValor, String pDescripcion, boolean pEsSeleccionado) {
    OptionHTML optionHTML_local = null;
    
    if (pValor == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDescripcion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      optionHTML_local = new OptionHTML();
      optionHTML_local.setValor(pValor);
      optionHTML_local.setDescripcion(pDescripcion);
      optionHTML_local.setEsSeleccionado(pEsSeleccionado);
      adicionar(optionHTML_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      optionHTML_local = null;
    } 
  }
  public String dibujar() {
    String optionsHTML_local = "";
    OptionHTML optionHTML_local = null;
    Iterator iterador_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        optionHTML_local = (OptionHTML)iterador_local.next();
        optionsHTML_local = mc.concatenarCadena(optionsHTML_local, optionHTML_local.dibujar());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      optionHTML_local = null;
    } 
    
    return optionsHTML_local;
  }
  public void seleccionarOpcionPorValor(String pValor) {
    Iterator iterador_local = null;
    OptionHTML optionHTML_local = null;
    
    try {
      iterador_local = iterator();
      while (iterador_local.hasNext()) {
        optionHTML_local = (OptionHTML)iterador_local.next();
        optionHTML_local.setEsSeleccionado(mc.sonCadenasIgualesIgnorarMayusculas(pValor, optionHTML_local.getValor()));
        if (optionHTML_local.esSeleccionado()) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      optionHTML_local = null;
    } 
  }
  public void concatenarCon(com.sisnet.controlesHTML.listas.ListaOptionHTML pListaOptionHTML) {
    try {
      concatenarCon(pListaOptionHTML);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\controlesHTML\listas\ListaOptionHTML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */