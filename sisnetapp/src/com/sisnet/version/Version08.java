package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version08
  extends AdministradorBaseDatos
{
  private static final String const_CampoTipoUsuario = "fldtipousuario";
  private static final String const_CampoUsuariosAutorizables = "fldusuariosautorizables";
  private static final String const_CampoNumeroAplicaciones = "fldnumeroaplicaciones";
  private static final String const_TablaPermisoUsuario = "PERMISOUSUARIO";
  private static final String const_TablaUsuarioAutorizado = "USUARIOAUTORIZADO";
  public Version08(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion08() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      
      if (verificarExistenciaCampo("fldtipousuario", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario drop column fldtipousuario;");
      }
      if (verificarExistenciaCampo("fldusuariosautorizables", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario drop column fldusuariosautorizables;");
      }
      if (verificarExistenciaCampo("fldnumeroaplicaciones", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario drop column fldnumeroaplicaciones;");
      }
      if (verificarExistenciaTabla("PERMISOUSUARIO")) {
        listaCadenas_local.adicionar("drop table permisousuario cascade;");
      }
      if (verificarExistenciaTabla("USUARIOAUTORIZADO")) {
        listaCadenas_local.adicionar("drop table usuarioautorizado cascade;");
      }
      
      if (!verificarExistenciaCampo("fldidtipousuario", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldidtipousuario integer;");
      }
      if (!verificarExistenciaCampo("flddiasvigenciacontrasena", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add flddiasvigenciacontrasena integer;");
      }
      if (!verificarExistenciaCampo("fldcontrasenasfallidaspermitidas", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldcontrasenasfallidaspermitidas integer;");
      }
      if (!verificarExistenciaCampo("fldfechaultimacontrasenafallida", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldfechaultimacontrasenafallida date;");
      }
      if (!verificarExistenciaCampo("fldcantidadcontrasenasfallidas", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldcantidadcontrasenasfallidas integer;");
      }
      if (!verificarExistenciaCampo("fldtiemposesion", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldtiemposesion integer;");
      }
      if (!verificarExistenciaCampo("fldtipobloqueo", "USUARIO")) {
        listaCadenas_local.adicionar("alter table usuario add fldtipobloqueo integer;");
      }
      
      if (!verificarExistenciaTabla("TIPOUSUARIO")) {
        listaCadenas_local.adicionar("create table tipousuario( fldidtipousuario integer not null);");
        listaCadenas_local.adicionar("alter table tipousuario add fldnombretipousuario varchar(50);");
        listaCadenas_local.adicionar("alter table tipousuario add fldpermitirutilizarmenu bool;");
        listaCadenas_local.adicionar("alter table tipousuario add fldnivelacceso varchar(10000);");
        
        listaCadenas_local.adicionar("alter table tipousuario add constraint pktipousuario primary key (fldidtipousuario);");
        listaCadenas_local.adicionar("create unique index idxtipousuario on tipousuario (fldnombretipousuario);");
      } 
      
      listaCadenas_local.adicionar("update usuario set fldidtipousuario = 0 where fldnombreusuario = 'ADMINISTRADOR';");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion08() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion08();
      iterador_local = listaCadenas_local.iterator();
      while (iterador_local.hasNext()) {
        consultaSql_local = (String)iterador_local.next();
        ejecutarInstruccionSQL(consultaSql_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      consultaSql_local = null;
      listaCadenas_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version08.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */