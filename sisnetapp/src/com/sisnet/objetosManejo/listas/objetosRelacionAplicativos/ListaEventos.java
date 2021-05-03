 package com.sisnet.objetosManejo.listas.objetosRelacionAplicativos;
 import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
 import com.sisnet.constantes.ConstantesGeneral;
 import com.sisnet.objetosManejo.listas.Lista;
 import com.sisnet.objetosManejo.manejoRelacionAplicativos.Evento;
 import java.util.Iterator;
 public class ListaEventos extends Lista
 {
   private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
   
   public void adicionar(Evento pEvento) {
     try {
       super.adicionar(pEvento);
     } catch (Exception excepcion) {
       excepcion.printStackTrace();
     } 
   }
   
   public com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaEventos obtenerListaEventoPorNombre(String pNombreEvento) {
     com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaEventos listaEventos_local = null;
     Iterator iterador_local = null;
     Evento evento_local = null;
     
     if (pNombreEvento == ConstantesGeneral.VALOR_NULO) {
       return listaEventos_local;
     }
     
     try {
       iterador_local = iterator();
       listaEventos_local = new com.sisnet.objetosManejo.listas.objetosRelacionAplicativos.ListaEventos();
       while (iterador_local.hasNext()) {
         evento_local = (Evento)iterador_local.next();
         if (evento_local.getListaNombresEventos() != ConstantesGeneral.VALOR_NULO && evento_local.getListaNombresEventos().verificarExistenciaCadena(pNombreEvento))
         {
           listaEventos_local.adicionar(evento_local);
         }
       } 
     } catch (Exception excepcion) {
       excepcion.printStackTrace();
     } finally {
       iterador_local = null;
     } 
     
     return listaEventos_local;
   }
 }
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosRelacionAplicativos\ListaEventos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */