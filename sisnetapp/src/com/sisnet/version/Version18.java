package com.sisnet.version;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoConexionBaseDatos;
import java.util.Iterator;
public class Version18
  extends AdministradorBaseDatos
{
  private static final String const_CampoVisibleUsuario = "fldvisibleusuario";
  private static final String const_CampoInclusionObligatoria = "fldinclusionobligatoria";
  private static final String const_CampoGrupoInformacionPredeterminado = "fldgrupoinformacionpredeterminado";
  private static final String const_CampoRepeticionUltimoRegistro = "fldrepeticionultimoregistro";
  private static final String const_CampoExigirAlerta = "fldexigiralerta";
  private static final String const_CampoFormatoCampo = "fldformatocampo";
  private static final String const_CampoValorDefecto = "fldvalordefecto";
  private static final String const_CampoValorMinimo = "fldvalorminimo";
  private static final String const_CampoValorMaximo = "fldvalormaximo";
  public Version18(ObjetoConexionBaseDatos pObjetoConexionBaseDatos) {
    super(pObjetoConexionBaseDatos);
  }
  private ListaCadenas obtenerListadoInstruccionesSqlCamposSisnetVersion18() {
    ListaCadenas listaCadenas_local = null;
    
    try {
      listaCadenas_local = new ListaCadenas();
      if (verificarExistenciaCampo("fldinclusionobligatoria", "GRUPOINFORMACION")) {
        listaCadenas_local.adicionar("alter table grupoinformacion drop column fldinclusionobligatoria");
      }
      if (verificarExistenciaCampo("fldgrupoinformacionpredeterminado", "GRUPOINFORMACION")) {
        listaCadenas_local.adicionar("delete from grupoinformacion where fldgrupoinformacionpredeterminado = true and fldgrupoinformacionprincipal = false");
        listaCadenas_local.adicionar("alter table grupoinformacion drop column fldgrupoinformacionpredeterminado");
      } 
      if (verificarExistenciaCampo("fldrepeticionultimoregistro", "GRUPOINFORMACION")) {
        listaCadenas_local.adicionar("alter table grupoinformacion drop column fldrepeticionultimoregistro");
      }
      if (verificarExistenciaCampo("fldexigiralerta", "GRUPOINFORMACION")) {
        listaCadenas_local.adicionar("alter table grupoinformacion drop column fldexigiralerta");
      }
      
      if (verificarExistenciaCampo("fldformatocampo", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo drop column fldformatocampo");
      }
      if (verificarExistenciaCampo("fldvalordefecto", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo drop column fldvalordefecto");
      }
      if (verificarExistenciaCampo("fldvalorminimo", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo drop column fldvalorminimo");
      }
      if (verificarExistenciaCampo("fldvalormaximo", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo drop column fldvalormaximo");
      }
      
      if (verificarExistenciaCampo("fldvisibleusuario", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo rename column fldvisibleusuario to fldvisibleusuarioprincipal");
      }
      if (!verificarExistenciaCampo("fldvisibleusuariosecundario", "CAMPO")) {
        listaCadenas_local.adicionar("alter table campo add fldvisibleusuariosecundario bool");
      }
      
      listaCadenas_local.adicionar("update campo set fldvisibleusuariosecundario = true where fldvisibleusuariosecundario is null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCadenas_local;
  }
  public void actualizarBaseDatosSisnetVersion18() {
    String consultaSql_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    try {
      actualizarRegistroConfiguracion();
      listaCadenas_local = obtenerListadoInstruccionesSqlCamposSisnetVersion18();
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
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\version\Version18.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */