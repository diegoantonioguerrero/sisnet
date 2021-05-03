package com.sisnet.objetosManejo.listas.objetosReporte;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.OrdenReporte;
public class ListaOrdenReporte
  extends Lista
{
  public void adicionar(int pTipoOrdenamiento, Campo pCampo) {
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(new OrdenReporte(pTipoOrdenamiento, pCampo));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionar(OrdenReporte pOrdenReporte) {
    if (pOrdenReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(pOrdenReporte);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosReporte\ListaOrdenReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */