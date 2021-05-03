package com.sisnet.objetosManejo.listas;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.AtributoSesion;
import com.sisnet.objetosManejo.listas.Lista;
public class ListaAtributosSesion
  extends Lista
{
  public void adicionar(String pNombreAtributo, Object pValorAtributo) {
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      super.adicionar(new AtributoSesion(pNombreAtributo, pValorAtributo));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\ListaAtributosSesion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */