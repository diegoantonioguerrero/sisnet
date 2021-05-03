package com.sisnet.objetosManejo.listas.objetosRelacionAplicativos;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import com.sisnet.objetosManejo.manejoRelacionAplicativos.Ciclo;
import java.util.Iterator;
public class ListaCiclos
  extends Lista
{
  public void adicionar(Ciclo pCiclo) {
    try {
      super.adicionar(pCiclo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Ciclo obtenerCicloPrincipal() {
    Ciclo ciclo_local = null;
    
    try {
      if (contarElementos() > 0) {
        ciclo_local = (Ciclo)get(0);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return ciclo_local;
  }
  public boolean verificarHaFinalizadoSiguienteCiclo(int pNumeroCiclo) {
    boolean haFinalizadoSiguienteCiclo_local = true;
    Ciclo ciclo_local = null;
    
    try {
      if (contarElementos() - 1 > pNumeroCiclo) {
        ciclo_local = (Ciclo)get(pNumeroCiclo + 1);
        haFinalizadoSiguienteCiclo_local = ciclo_local.esHaFinalizado();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return haFinalizadoSiguienteCiclo_local;
  }
  public boolean verificarHanFinalizadoTodosLosCiclosSecundarios() {
    boolean hanFinalizadoTodosLosCiclosSecundarios_local = false;
    Iterator iterator_local = null;
    Ciclo ciclo_local = null;
    int contadorFinalizados_local = 0;
    Ciclo cicloPrincipal_local = null;
    
    try {
      cicloPrincipal_local = obtenerCicloPrincipal();
      if (cicloPrincipal_local != ConstantesGeneral.VALOR_NULO) {
        iterator_local = iterator();
        while (iterator_local.hasNext()) {
          ciclo_local = (Ciclo)iterator_local.next();
          if (cicloPrincipal_local.getNumeroCiclo() != ciclo_local.getNumeroCiclo() && ciclo_local.esHaFinalizado()) {
            contadorFinalizados_local++;
          }
        } 
        hanFinalizadoTodosLosCiclosSecundarios_local = (contarElementos() - 1 == contadorFinalizados_local);
      }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      ciclo_local = null;
      cicloPrincipal_local = null;
    } 
    
    return hanFinalizadoTodosLosCiclosSecundarios_local;
  }
  public boolean verificarExistenciaCicloSecundarioFinalizado() {
    boolean existenciaCicloSecundarioFinalizado_local = false;
    Iterator iterator_local = null;
    Ciclo ciclo_local = null;
    Ciclo cicloPrincipal_local = null;
    
    try {
      cicloPrincipal_local = obtenerCicloPrincipal();
      if (cicloPrincipal_local != ConstantesGeneral.VALOR_NULO) {
        iterator_local = iterator();
        while (iterator_local.hasNext()) {
          ciclo_local = (Ciclo)iterator_local.next();
          if (cicloPrincipal_local.getNumeroCiclo() != ciclo_local.getNumeroCiclo() && ciclo_local.esHaFinalizado()) {
            existenciaCicloSecundarioFinalizado_local = true;
            break;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      ciclo_local = null;
      cicloPrincipal_local = null;
    } 
    
    return existenciaCicloSecundarioFinalizado_local;
  }
  public void finalizarSiguientesCiclos(int pNumeroCiclo) {
    Iterator iterator_local = null;
    Ciclo ciclo_local = null;
    
    try {
      iterator_local = iterator();
      while (iterator_local.hasNext()) {
        ciclo_local = (Ciclo)iterator_local.next();
        if (ciclo_local.getNumeroCiclo() > pNumeroCiclo) {
          ciclo_local.setHaFinalizado(true);
          ciclo_local.setResultSet(null);
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      ciclo_local = null;
    } 
  }
  public void inicializarCiclosSecundarios() {
    Iterator iterator_local = null;
    Ciclo ciclo_local = null;
    Ciclo cicloPrincipal_local = null;
    
    try {
      cicloPrincipal_local = obtenerCicloPrincipal();
      if (cicloPrincipal_local != ConstantesGeneral.VALOR_NULO) {
        iterator_local = iterator();
        while (iterator_local.hasNext()) {
          ciclo_local = (Ciclo)iterator_local.next();
          if (cicloPrincipal_local.getNumeroCiclo() != ciclo_local.getNumeroCiclo()) {
            ciclo_local.setHaFinalizado(false);
            ciclo_local.setResultSet(null);
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      ciclo_local = null;
      cicloPrincipal_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosRelacionAplicativos\ListaCiclos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */