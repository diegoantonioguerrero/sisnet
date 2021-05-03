package com.sisnet.objetosManejo.manejoReportes.estructuraReporte;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.ListaCadenas;
public class BloqueEncabezadoPie
{
  private ListaCadenas aLineasIzquierda;
  private ListaCadenas aLineasCentro;
  private ListaCadenas aLineasDerecha;
  private int aNumeroLineas;
  public BloqueEncabezadoPie(ListaCadenas pLineasIzquierda, ListaCadenas pLineasCentro, ListaCadenas pLineasDerecha) {
    setLineasIzquierda(pLineasIzquierda);
    setLineasCentro(pLineasCentro);
    setLineasDerecha(pLineasDerecha);
    cargarNumeroLineas();
  }
  public ListaCadenas getLineasIzquierda() {
    return this.aLineasIzquierda;
  }
  public void setLineasIzquierda(ListaCadenas pLineasIzquierda) {
    this.aLineasIzquierda = pLineasIzquierda;
  }
  public ListaCadenas getLineasCentro() {
    return this.aLineasCentro;
  }
  public void setLineasCentro(ListaCadenas pLineasCentro) {
    this.aLineasCentro = pLineasCentro;
  }
  public ListaCadenas getLineasDerecha() {
    return this.aLineasDerecha;
  }
  public void setLineasDerecha(ListaCadenas pLineasDerecha) {
    this.aLineasDerecha = pLineasDerecha;
  }
  public int getNumeroLineas() {
    return this.aNumeroLineas;
  }
  public void setNumeroLineas(int pNumeroLineas) {
    this.aNumeroLineas = pNumeroLineas;
  }
  private void cargarNumeroLineas() {
    int numeroLineas_local = 0;
    int lineasIzquierda_local = 0;
    int lineasCentro_local = 0;
    int lineasDerecha_local = 0;
    
    try {
      if (getLineasIzquierda() != ConstantesGeneral.VALOR_NULO) {
        lineasIzquierda_local = getLineasIzquierda().contarElementos();
      }
      if (getLineasCentro() != ConstantesGeneral.VALOR_NULO) {
        lineasCentro_local = getLineasCentro().contarElementos();
      }
      if (getLineasDerecha() != ConstantesGeneral.VALOR_NULO) {
        lineasDerecha_local = getLineasDerecha().contarElementos();
      }
      numeroLineas_local = lineasIzquierda_local;
      if (lineasCentro_local > numeroLineas_local) {
        numeroLineas_local = lineasCentro_local;
      }
      if (lineasDerecha_local > numeroLineas_local) {
        numeroLineas_local = lineasDerecha_local;
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    setNumeroLineas(numeroLineas_local);
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\estructuraReporte\BloqueEncabezadoPie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */