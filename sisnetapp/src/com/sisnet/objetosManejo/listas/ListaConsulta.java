package com.sisnet.objetosManejo.listas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoPaginaJsp.ItemConsulta;
import java.io.Serializable;
import java.util.Iterator;
public class ListaConsulta
  extends Lista
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public void adicionar(ItemConsulta pItemConsulta) {
    if (pItemConsulta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      super.adicionar(pItemConsulta);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void eliminar(int pNivelConsulta) {
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    
    try {
      if (contarElementos() > 0) {
        iterator_local = iterator();
        
        while (iterator_local.hasNext()) {
          itemConsulta_local = (ItemConsulta)iterator_local.next();
          if (itemConsulta_local.getNivelConsulta() == pNivelConsulta) {
            remove(itemConsulta_local);
            break;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      
      iterator_local = null;
      itemConsulta_local = null;
    } 
  }
  public int obtenerUltimoNivel() {
    int nivel_local = 0;
    ItemConsulta itemConsulta_local = null;
    
    try {
      itemConsulta_local = (ItemConsulta)obtenerUltimoElemento();
      if (itemConsulta_local != ConstantesGeneral.VALOR_NULO) {
        nivel_local = itemConsulta_local.getNivelConsulta();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      itemConsulta_local = null;
    } 
    return nivel_local;
  }
  public boolean verificarExistenciaOrdenadoPor(String pNombreCampo) {
    boolean existenciaOrdenadoPor_local = false;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return existenciaOrdenadoPor_local;
    }
    
    try {
      iterator_local = iterator();
      
      while (iterator_local.hasNext()) {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        
        existenciaOrdenadoPor_local = (itemConsulta_local.esOrdenadoPor() && mc.sonCadenasIguales(itemConsulta_local.getCampoDesde().getNombreCampo(), pNombreCampo));
        
        if (existenciaOrdenadoPor_local) {
          break;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      itemConsulta_local = null;
    } 
    
    return existenciaOrdenadoPor_local;
  }
  public Campo obtenerPrimerCampoConsulta() {
    Campo campo_local = null;
    
    try {
      if (get(0) != ConstantesGeneral.VALOR_NULO) {
        campo_local = ((ItemConsulta)get(0)).getCampoHasta();
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return campo_local;
  }
  public void borrarItemsNoPredefinidos() {
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (itemConsulta_local.esPermitirBorrar()) {
          iterator_local.remove();
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      itemConsulta_local = null;
    } 
  }
  public void validarOpcionesConsulta() {
    boolean omitirOpcionConsulta_local = false;
    Iterator iterator_local = null;
    ItemConsulta itemConsulta_local = null;
    boolean esCampoTablaEnlazado_local = false;
    Campo campoDesde_local = null;
    Campo campoHasta_local = null;
    String valorCampoDesde_local = null;
    String valorCampoHasta_local = null;
    boolean esValorDesdeNulo_local = false;
    boolean esValorHastaNulo_local = false;
    
    try {
      iterator_local = iterator();
      
      while (iterator_local.hasNext()) {
        valorCampoDesde_local = "";
        valorCampoHasta_local = "";
        itemConsulta_local = (ItemConsulta)iterator_local.next();
        if (!itemConsulta_local.esOrdenadoPor()) {
          campoDesde_local = itemConsulta_local.getCampoDesde();
          campoHasta_local = itemConsulta_local.getCampoHasta();
          
          if (campoDesde_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
            valorCampoDesde_local = mc.convertirAMayusculas(mc.borrarEspaciosLaterales(campoDesde_local.getValorCampo().toString()));
          }
          
          if (campoHasta_local.getValorCampo() != ConstantesGeneral.VALOR_NULO) {
            valorCampoHasta_local = mc.convertirAMayusculas(mc.borrarEspaciosLaterales(campoHasta_local.getValorCampo().toString()));
          }
          
          esValorDesdeNulo_local = (mc.sonCadenasIguales(valorCampoDesde_local, "") || (campoDesde_local.esTipoDatoNumerico() && mc.sonCadenasIguales(valorCampoDesde_local, String.valueOf('0'))));
          
          esValorHastaNulo_local = (mc.sonCadenasIguales(valorCampoHasta_local, "") || (campoDesde_local.esTipoDatoNumerico() && mc.sonCadenasIguales(valorCampoHasta_local, String.valueOf('0'))));
          
          esCampoTablaEnlazado_local = (campoDesde_local.esTipoDatoTabla() || campoDesde_local.esCampoEnlazado());
          
          omitirOpcionConsulta_local = ((esCampoTablaEnlazado_local && mc.sonCadenasIguales(valorCampoHasta_local, String.valueOf(-1))) || (esValorDesdeNulo_local && esValorHastaNulo_local));
          
          itemConsulta_local.setOmitirOpcionConsulta(omitirOpcionConsulta_local);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      iterator_local = null;
      itemConsulta_local = null;
      campoDesde_local = null;
      campoHasta_local = null;
      valorCampoHasta_local = null;
      valorCampoDesde_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaConsulta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */