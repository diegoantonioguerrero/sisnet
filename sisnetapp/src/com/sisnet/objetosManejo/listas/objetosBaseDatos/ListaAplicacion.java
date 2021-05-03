package com.sisnet.objetosManejo.listas.objetosBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.Lista;
import java.util.Collection;
import java.util.Iterator;
public class ListaAplicacion extends Lista
{
   public void adicionar(Aplicacion pAplicacion)
{
  try
  {
    super.adicionar(pAplicacion);
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
}
public boolean concatenar(com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaAplicacion pListaAplicacionAdicionar)
{
  boolean concatenado_local = false;
  if (pListaAplicacionAdicionar == ConstantesGeneral.VALOR_NULO)
  {
    return concatenado_local;
  }
  try
  {
    concatenado_local = addAll((Collection)pListaAplicacionAdicionar);
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  return concatenado_local;
}
public Aplicacion obtenerAplicacionPorId(int pIdAplicacion)
{
  Aplicacion aplicacion_local = null;
  Iterator iterador_local = null;
  try
  {
    if (pIdAplicacion > 0)
    {
      iterador_local = iterator();
      while (iterador_local.hasNext())
      {
        aplicacion_local = (Aplicacion)iterador_local.next();
        if (pIdAplicacion == aplicacion_local.getIdAplicacion())
        {
          break;
        }
        aplicacion_local = null;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
  return aplicacion_local;
}
public void modificarAplicacionPorId(Aplicacion pAplicacionModificada)
{
  Aplicacion aplicacion_local = null;
  Iterator iterador_local = null;
  if (pAplicacionModificada == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      aplicacion_local = (Aplicacion)iterador_local.next();
      if (pAplicacionModificada.getIdAplicacion() == aplicacion_local.getIdAplicacion())
      {
        aplicacion_local = pAplicacionModificada;
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
    aplicacion_local = null;
  }
}
public void borrarAplicacionPorId(int pIdAplicacion)
{
  Iterator iterador_local = null;
  try
  {
    iterador_local = iterator();
    while (iterador_local.hasNext())
    {
      if (pIdAplicacion == ((Aplicacion)iterador_local.next()).getIdAplicacion())
      {
        iterador_local.remove();
        break;
      }
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  finally
  {
    iterador_local = null;
  }
}
 }
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosBaseDatos\ListaAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */