package com.sisnet.objetosManejo.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.ItemLista;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Iterator;
public class ListaGeneral
  extends Lista
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(String pNombreItem, String pValorItem, boolean pSeleccionado) {
    if (pNombreItem == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorItem == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      super.adicionar(new ItemLista(pNombreItem, pValorItem, pSeleccionado));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void seleccionarItemListaPorValor(String pValor) {
    ItemLista itemLista_local = null;
    Iterator iterator_local = null;
    
    if (pValor == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        itemLista_local = (ItemLista)iterator_local.next();
        if (mc.sonCadenasIguales(itemLista_local.getValorItem(), pValor)) {
          itemLista_local.setSeleccionado(true);
          break;
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
  }
  public void seleccionarPorDefectoUltimoItemLista() {
    ItemLista itemLista_local = null;
    Iterator iterator_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        itemLista_local = (ItemLista)iterator_local.next();
        if (iterator_local.hasNext()) {
          itemLista_local.setSeleccionado(false); continue;
        } 
        itemLista_local.setSeleccionado(true);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
  }
  public String obtenerValorItemUltimoItemLista() {
    String valorItemLista_local = "";
    ItemLista itemLista_local = null;
    Iterator iterator_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        itemLista_local = (ItemLista)iterator_local.next();
        if (!iterator_local.hasNext()) {
          valorItemLista_local = itemLista_local.getValorItem();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
    } 
    
    return valorItemLista_local;
  }
  public void concatenarListaGeneral(com.sisnet.objetosManejo.listas.ListaGeneral pListaGeneralAdicionar) {
    Iterator iterator_local = null;
    ItemLista itemLista_local = null;
    
    if (pListaGeneralAdicionar == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterator_local = pListaGeneralAdicionar.iterator();
      while (iterator_local.hasNext()) {
        itemLista_local = (ItemLista)iterator_local.next();
        adicionar(itemLista_local.getNombreItem(), itemLista_local.getValorItem(), itemLista_local.esSeleccionado());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      itemLista_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaGeneral.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */