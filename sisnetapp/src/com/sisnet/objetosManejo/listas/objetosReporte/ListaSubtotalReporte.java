package com.sisnet.objetosManejo.listas.objetosReporte;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.SubtotalReporte;
public class ListaSubtotalReporte
  extends Lista
{
  public void adicionar(String pDescripcionInicialSubreporte, String pDesripcionFinalSubreporte, Campo pCampoDetalle, ListaCampo pListaCamposSubtotal) {
    if (pDescripcionInicialSubreporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDesripcionFinalSubreporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pCampoDetalle == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCamposSubtotal == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(new SubtotalReporte(pDescripcionInicialSubreporte, pDesripcionFinalSubreporte, pCampoDetalle, pListaCamposSubtotal));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionar(SubtotalReporte pSubtotalReporte) {
    if (pSubtotalReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(pSubtotalReporte);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosReporte\ListaSubtotalReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */