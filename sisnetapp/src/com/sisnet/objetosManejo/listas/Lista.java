package com.sisnet.objetosManejo.listas;
import com.sisnet.constantes.ConstantesGeneral;
import java.util.ArrayList;
public class Lista
   extends ArrayList<Object>
 {
   protected void adicionar(Object pObjetoAdicionar)
{
  if (pObjetoAdicionar == ConstantesGeneral.VALOR_NULO)
  {
    return;
  }
  try
  {
    add(pObjetoAdicionar);
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
}
public int contarElementos()
{
  int elementos_local = 0;
  try
  {
    elementos_local = size();
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  return elementos_local;
}
public void borrarElementos()
{
  try
  {
    clear();
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
}
public Object obtenerUltimoElemento()
{
  Object ultimoElemento_local = null;
  try
  {
    if (contarElementos() > 0)
    {
      ultimoElemento_local = get(contarElementos() - 1);
    }
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
  return ultimoElemento_local;
}
protected void concatenarCon(com.sisnet.objetosManejo.listas.Lista pLista)
{
  try
  {
    addAll(pLista);
  }
  catch (Exception excepcion)
  {
    excepcion.printStackTrace();
  }
}
 }
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\Lista.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */