package com.sisnet.objetosManejo.listas.objetosReporte;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoReportes.estructuraReporte.ColumnaReporte;
public class ListaColumnasReporte
  extends Lista
{
  public void adicionar(String pNombreColumna, int pAnchoColumna, int pTipoRepeticion, String pFormatoEstilo, Campo pCampo) {
    if (pNombreColumna == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFormatoEstilo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(new ColumnaReporte(pNombreColumna, pAnchoColumna, pTipoRepeticion, pFormatoEstilo, pCampo, contarElementos()));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionar(ColumnaReporte pColumnaReporte) {
    if (pColumnaReporte == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      super.adicionar(pColumnaReporte);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosReporte\ListaColumnasReporte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */