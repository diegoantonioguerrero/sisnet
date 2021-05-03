package com.sisnet.objetosManejo.listas.objetosBaseDatos;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.objetosManejo.listas.Lista;
public class ListaUsuario
  extends Lista
{
  public void adicionar(Usuario pUsuario) {
    try {
      super.adicionar(pUsuario);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\listas\objetosBaseDatos\ListaUsuario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */