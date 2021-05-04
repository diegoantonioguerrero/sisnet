package com.sisnet.baseDatos.consultasBaseDatos;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.TipoUsuario;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.ListaGeneral;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
public class ConsultasAdministrador
{
  private static com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador consultasAdministradorSingleton = null;
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  public static com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador getConsultasAdministrador()
  {
    if (consultasAdministradorSingleton == null)
    {
      consultasAdministradorSingleton = new com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador();
    }
    return consultasAdministradorSingleton;
  }
  public String obtenerCadenaSQLVerificarExistenciaBaseDatos(String pNombreBaseDatos)
  {
    String verificarBaseDatos_local = "";
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return verificarBaseDatos_local;
    }
    try
    {
      verificarBaseDatos_local = mc.concatenarCadena("select ", "*  from pg_database  where datname =" + mc.colocarEntreComillas(pNombreBaseDatos) + ';');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarBaseDatos_local;
  }
  public String obtenerCadenaSQLVerificarExistenciaRestriccion(String pNombreRestriccion)
  {
    String verificarBaseDatos_local = "";
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO)
    {
      return verificarBaseDatos_local;
    }
    try
    {
      verificarBaseDatos_local = mc.concatenarCadena("select ", "*  from pg_constraint  where conname =" + mc.colocarEntreComillas(pNombreRestriccion) + ';');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarBaseDatos_local;
  }
  public String obtenerCadenaSQLVerificarExistenciaIndice(String pNombreIndice)
  {
    String verificarBaseDatos_local = "";
    if (pNombreIndice == ConstantesGeneral.VALOR_NULO)
    {
      return verificarBaseDatos_local;
    }
    try
    {
      verificarBaseDatos_local = mc.concatenarCadena("select ", "*  from pg_indexes  where indexname=" + mc.colocarEntreComillas(pNombreIndice));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarBaseDatos_local;
  }
  public String obtenerCadenaSQLCamposTabla(String pNombreTabla)
  {
    String cadenaSQLCamposTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLCamposTabla_local;
    }
    try
    {
      cadenaSQLCamposTabla_local = mc.concatenarCadena("select * ", " from " + pNombreTabla + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaSQLCamposTabla_local;
  }
  public String obtenerCadenaSQLCrearBaseDatos(String pNombreBaseDatos)
  {
    String crearBaseDatos_local = "";
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return crearBaseDatos_local;
    }
    try
    {
      crearBaseDatos_local = mc.concatenarCadena("create database ", pNombreBaseDatos + " encoding = 'LATIN1'; ");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearBaseDatos_local;
  }
  public String obtenerCadenaSQLBorrarBaseDatos(String pNombreBaseDatos)
  {
    String borrarBaseDatos_local = "";
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return borrarBaseDatos_local;
    }
    try
    {
      borrarBaseDatos_local = mc.concatenarCadena("drop database ", pNombreBaseDatos + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarBaseDatos_local;
  }
  private String obtenerCadenaSQLCampo(Campo pCampo, boolean pEsUltimoCampo, String pNombreLlavePrimariaGrupoPrincipal)
  {
    String cadenaSQLCampo_local = "";
    String nombreCampo_local = null;
    boolean esLlavePrimaria_local = false;
    boolean esLlaveForanea_local = false;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLCampo_local;
    }
    if (pNombreLlavePrimariaGrupoPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLCampo_local;
    }
    try
    {
      nombreCampo_local = pCampo.getNombreCampo();
      esLlavePrimaria_local = pCampo.getRestriccionCampo().esLlavePrimaria();
      esLlaveForanea_local = pCampo.getRestriccionCampo().esLlaveForanea();
      if (esLlavePrimaria_local)
      {
        nombreCampo_local = ap.conformarNombreCampoLlavePrimaria(nombreCampo_local);
      }
      cadenaSQLCampo_local = mc.concatenarCadena(nombreCampo_local, String.valueOf(' '));
      cadenaSQLCampo_local = mc.concatenarCadena(cadenaSQLCampo_local, pCampo.obtenerTipoDatoEquivalenteCampo());
      if (esLlavePrimaria_local || (esLlaveForanea_local && mc.sonCadenasIguales(pNombreLlavePrimariaGrupoPrincipal, nombreCampo_local)))
      {
        cadenaSQLCampo_local = mc.concatenarCadena(cadenaSQLCampo_local, " not null ");
      }
      if (pCampo.esTipoDatoNumerico() || pCampo.esTipoDatoTabla())
      {
        cadenaSQLCampo_local = mc.concatenarCadena(cadenaSQLCampo_local, " default 0 ");
      }
      else if (!mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "F") && !mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "H"))
      {
        cadenaSQLCampo_local = mc.concatenarCadena(cadenaSQLCampo_local, " default '' ");
      }
      if (!pEsUltimoCampo)
      {
        cadenaSQLCampo_local = mc.concatenarCadena(cadenaSQLCampo_local, String.valueOf(','));
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreCampo_local = null;
    }
    return cadenaSQLCampo_local;
  }
  public String obtenerCadenaSQLCrearTabla(String pNombreTabla)
  {
    String crearTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return crearTabla_local;
    }
    try
    {
      crearTabla_local = "create table " + pNombreTabla + '(' + ap.conformarNombreCampoLlavePrimaria(pNombreTabla) + ' ' + "integer" + " not null " + ')';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearTabla_local;
  }
  public String obtenerCadenaSQLBorrarTabla(String pNombreTabla)
  {
    String borrarTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return borrarTabla_local;
    }
    try
    {
      borrarTabla_local = mc.concatenarCadena("drop table ", pNombreTabla + " cascade " + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarTabla_local;
  }
  public String obtenerCadenaSQLCrearVista(String pNombreVista, String pConsultaSQL)
  {
    String crearVista_local = "";
    if (pNombreVista == ConstantesGeneral.VALOR_NULO)
    {
      return crearVista_local;
    }
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO)
    {
      return crearVista_local;
    }
    try
    {
      crearVista_local = mc.concatenarCadena("create view ", pNombreVista + " as ");
      crearVista_local = mc.concatenarCadena(crearVista_local, pConsultaSQL + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearVista_local;
  }
  public String obtenerCadenaSQLBorrarVista(String pNombreVista)
  {
    String borrarVista_local = "";
    if (pNombreVista == ConstantesGeneral.VALOR_NULO)
    {
      return borrarVista_local;
    }
    try
    {
      borrarVista_local = mc.concatenarCadena("drop view ", pNombreVista + " cascade " + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarVista_local;
  }
  public String obtenerCadenaSQLCrearCampo(String pNombreTabla, Campo pCampo, String pNombreLlavePrimariaGrupoPrincipal)
  {
    String crearCampo_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return crearCampo_local;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return crearCampo_local;
    }
    if (pNombreLlavePrimariaGrupoPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return crearCampo_local;
    }
    try
    {
      crearCampo_local = mc.concatenarCadena("alter table ", pNombreTabla + " add column ");
      crearCampo_local = mc.concatenarCadena(crearCampo_local, obtenerCadenaSQLCampo(pCampo, true, pNombreLlavePrimariaGrupoPrincipal));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearCampo_local;
  }
  public String obtenerCadenaSQLBorrarCampo(String pNombreTabla, String pNombreCampo)
  {
    String borrarCampo_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return borrarCampo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return borrarCampo_local;
    }
    try
    {
      borrarCampo_local = mc.concatenarCadena("alter table ", pNombreTabla + " drop column ");
      borrarCampo_local = mc.concatenarCadena(borrarCampo_local, pNombreCampo + " cascade " + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarCampo_local;
  }
  public String obtenerCadenaSQLCrearIndice(String pNombreIndice, String pNombreTabla, String pNombreCampo)
  {
    String crearIndice_local = "";
    if (pNombreIndice == ConstantesGeneral.VALOR_NULO)
    {
      return crearIndice_local;
    }
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return crearIndice_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return crearIndice_local;
    }
    try
    {
      crearIndice_local = mc.concatenarCadena(" create unique index ", pNombreIndice + " on " + pNombreTabla + mc.colocarEntreParentesis(pNombreCampo));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearIndice_local;
  }
  public String obtenerCadenaSQLBorrarIndice(String pNombreIndice)
  {
    String borrarIndice_local = "";
    if (pNombreIndice == ConstantesGeneral.VALOR_NULO)
    {
      return borrarIndice_local;
    }
    try
    {
      borrarIndice_local = mc.concatenarCadena(" drop index ", pNombreIndice);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarIndice_local;
  }
  public String obtenerCadenaSQLCrearRestriccionLlavePrimaria(String pNombreTabla, String pNombreCampo, String pNombreRestriccion)
  {
    String crearRestriccion_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    try
    {
      crearRestriccion_local = mc.concatenarCadena("alter table ", pNombreTabla);
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " add constraint " + pNombreRestriccion);
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " primary key " + String.valueOf('(') + pNombreCampo);
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, String.valueOf(')') + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearRestriccion_local;
  }
  public String obtenerCadenaSQLBorrarRestriccionLlavePrimaria(String pNombreTabla, String pNombreRestriccion)
  {
    String borrarRestriccion_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return borrarRestriccion_local;
    }
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO)
    {
      return borrarRestriccion_local;
    }
    try
    {
      borrarRestriccion_local = mc.concatenarCadena("alter table ", pNombreTabla + " drop constraint ");
      borrarRestriccion_local = mc.concatenarCadena(borrarRestriccion_local, pNombreRestriccion + " cascade " + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarRestriccion_local;
  }
  public String obtenerCadenaSQLCrearRestriccionLlaveForanea(String pNombreTabla, String pNombreCampo, String pNombreTablaReferencia, String pNombreCampoReferencia, String pNombreRestriccion)
  {
    String crearRestriccion_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    if (pNombreTablaReferencia == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    if (pNombreCampoReferencia == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO)
    {
      return crearRestriccion_local;
    }
    try
    {
      crearRestriccion_local = mc.concatenarCadena("alter table ", pNombreTabla);
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " add constraint fk" + pNombreRestriccion);
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " foreign key " + String.valueOf('('));
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, pNombreCampo + String.valueOf(')'));
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " references " + pNombreTablaReferencia);
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, '(' + pNombreCampoReferencia + ')');
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " on update  cascade ");
      crearRestriccion_local = mc.concatenarCadena(crearRestriccion_local, " on  delete  cascade " + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return crearRestriccion_local;
  }
  public String obtenerCadenaSQLBorrarRestriccionLlaveForanea(String pNombreTabla, String pNombreRestriccion)
  {
    String borrarRestriccion_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return borrarRestriccion_local;
    }
    if (pNombreRestriccion == ConstantesGeneral.VALOR_NULO)
    {
      return borrarRestriccion_local;
    }
    try
    {
      borrarRestriccion_local = mc.concatenarCadena("alter table ", pNombreTabla + " drop constraint ");
      borrarRestriccion_local = mc.concatenarCadena(borrarRestriccion_local, pNombreRestriccion + " cascade " + String.valueOf(';'));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return borrarRestriccion_local;
  }
  public String obtenerCadenaSQLCambiarNombreBaseDatos(String pNombreBaseDatos, String pNuevoNombreBaseDatos)
  {
    String modificarBaseDatos_local = "";
    if (pNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    if (pNuevoNombreBaseDatos == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    try
    {
      modificarBaseDatos_local = mc.concatenarCadena("alter database ", pNombreBaseDatos + " rename " + " to " + pNuevoNombreBaseDatos);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return modificarBaseDatos_local;
  }
  public String obtenerCadenaSQLCambiarNombreGrupoInformacion(String pNombreTabla, String pNuevoNombreTabla)
  {
    String modificarBaseDatos_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    if (pNuevoNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    try
    {
      modificarBaseDatos_local = mc.concatenarCadena("alter table ", pNombreTabla + " rename " + " to " + pNuevoNombreTabla);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return modificarBaseDatos_local;
  }
  public String obtenerCadenaSQLCambiarNombreCampo(String pNombreGrupoInformacion, String pNombreCampo, String pNuevoNombreCampo)
  {
    String modificarBaseDatos_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    if (pNuevoNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return modificarBaseDatos_local;
    }
    try
    {
      modificarBaseDatos_local = mc.concatenarCadena("alter table ", pNombreGrupoInformacion + " rename " + " column " + pNombreCampo + " to " + pNuevoNombreCampo);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return modificarBaseDatos_local;
  }
  public String consultaSQLAplicaciones(int pTipoSeleccionAplicaciones, int pIdAplicacion)
  {
    String aplicaciones_local = "";
    try
    {
      aplicaciones_local = "select " + ap.conformarBloqueCamposTablaAplicacion() + " from " + "APLICACION";
      if (pTipoSeleccionAplicaciones != 0)
      {
        switch (pTipoSeleccionAplicaciones)
        {
          case 1:
          case 2:
            aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " where fldestabla = " + String.valueOf((pTipoSeleccionAplicaciones == 2)));
            if (pIdAplicacion != -1)
            {
              aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " and fldidaplicacion <> " + pIdAplicacion);
            }
            break;
          case 3:
          case 4:
            aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " where fldestabla = " + String.valueOf((pTipoSeleccionAplicaciones == 2)));
            aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " and fldesoculta = " + String.valueOf(false));
            if (pIdAplicacion != -1)
            {
              aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " and fldidaplicacion <> " + pIdAplicacion);
            }
            break;
        }
      }
      else if (pIdAplicacion != -1)
      {
        aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " where fldidaplicacion <> " + pIdAplicacion);
      }
      aplicaciones_local = mc.concatenarCadena(aplicaciones_local, " order by fldtituloaplicacion");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return aplicaciones_local;
  }
  public String consultaSQLCamposEnlazadosAplicacion(int pIdAplicacion)
  {
    return "select fldidcampo,fldnombrecampo,fldetiquetacampo from CAMPO,GRUPOINFORMACION where fldidaplicacion = " + pIdAplicacion + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldenlazado" + " <> " + (int)Character.MIN_VALUE + ';';
  }
  public String consultaSQLTipoUsuarioPorId(int pIdTipoUsuario)
  {
    return "select " + ap.conformarBloqueCamposTablaTipoUsuario() + " from " + "TIPOUSUARIO" + " where " + "fldidtipousuario" + " = " + pIdTipoUsuario;
  }
  public String consultaSQLUsuarios()
  {
    String consultaUsuario_local = "select fldidusuario,fldnombreusuario,fldnombrecompletousuario,fldidtipousuario from USUARIO where fldidtipousuario <> 0 and fldidtipousuario <> 1000 order by fldnombreusuario";
    return consultaUsuario_local;
  }
  public String consultaSQLUsuarioPorNombre(String pNombreUsuario)
  {
    String consultaUsuario_local = "select " + ap.conformarBloqueCamposTablaUsuario() + " from " + "USUARIO" + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario);
    return consultaUsuario_local;
  }
  public String consultaSQLUsuarioPorId(int pIdUsuario)
  {
    String consultaUsuario_local = "select " + ap.conformarBloqueCamposTablaUsuario() + " from " + "USUARIO" + " where " + "fldidusuario" + " = " + pIdUsuario;
    return consultaUsuario_local;
  }
  public String consultaSQLAsignarFechaUltimaContrasenaFallida(String pNombreUsuario)
  {
    String consultaFechaUltimaContrasenaFallida_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaFechaUltimaContrasenaFallida_local;
    }
    try
    {
      consultaFechaUltimaContrasenaFallida_local = "update USUARIO set fldfechaultimacontrasenafallida = " + mc.colocarEntreComillas(mf.obtenerFechaActualSistema(true)) + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario) + " and " + "fldidtipousuario" + " <> " + (int)Character.MIN_VALUE;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaFechaUltimaContrasenaFallida_local;
  }
  public String consultaSQLAsignarCantidadContrasenasFallidas(String pNombreUsuario)
  {
    String consultaContrasenasFallidas_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaContrasenasFallidas_local;
    }
    try
    {
      consultaContrasenasFallidas_local = "update USUARIO set fldcantidadcontrasenasfallidas = fldcantidadcontrasenasfallidas+1 where fldnombreusuario = " + mc.colocarEntreComillas(pNombreUsuario) + " and " + "fldidtipousuario" + " <> " + (int)Character.MIN_VALUE;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaContrasenasFallidas_local;
  }
  public String consultaSQLBorrarCantidadContrasenasFallidas()
  {
    String consultaContrasenasFallidas_local = "";
    try
    {
      consultaContrasenasFallidas_local = "update USUARIO set fldcantidadcontrasenasfallidas = 0,fldtipobloqueo = 1 where fldfechaultimacontrasenafallida<" + mc.colocarEntreComillas(mf.obtenerFechaActualSistema(true)) + " and " + "fldtipobloqueo" + " <> " + '2';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaContrasenasFallidas_local;
  }
  public String consultaSQLAsignarTipoBloqueo(String pNombreUsuario, int pTipoBloqueo)
  {
    String consultaTipoBloqueo_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaTipoBloqueo_local;
    }
    try
    {
      consultaTipoBloqueo_local = "update USUARIO set fldtipobloqueo = " + pTipoBloqueo + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario) + " and " + "fldidtipousuario" + " <> " + (int)Character.MIN_VALUE;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaTipoBloqueo_local;
  }
  public String consultaSQLAsignarBloqueoPorVencimientoContrasena()
  {
    String consultaContrasenasFallidas_local = "";
    try
    {
      consultaContrasenasFallidas_local = "update USUARIO set fldtipobloqueo = 4 where fldfechavencimiento<" + mc.colocarEntreComillas(mf.obtenerFechaActualSistema(true)) + " and " + "fldidtipousuario" + " <> " + (int)Character.MIN_VALUE;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaContrasenasFallidas_local;
  }
  public String consultaSQLCambiarNombreUsuario(String pNombreUsuario, String pNuevoNombreUsuario)
  {
    String cambiarNombreUsuario_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return cambiarNombreUsuario_local;
    }
    if (pNuevoNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return cambiarNombreUsuario_local;
    }
    try
    {
      cambiarNombreUsuario_local = "update USUARIO set fldnombreusuario = " + mc.colocarEntreComillas(pNuevoNombreUsuario) + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cambiarNombreUsuario_local;
  }
  public String consultaSQLCambiarContrasenaUsuario(String pNombreUsuario, String pNuevaContrasena)
  {
    String cambiarContrasena_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return cambiarContrasena_local;
    }
    if (pNuevaContrasena == ConstantesGeneral.VALOR_NULO)
    {
      return cambiarContrasena_local;
    }
    try
    {
      cambiarContrasena_local = "update USUARIO set fldcontrasena = " + mc.colocarEntreComillas(pNuevaContrasena) + ',' + "fldfechacambiocontrasena" + " = " + mc.colocarEntreComillas(mf.obtenerFechaActualSistema(true)) + ',' + "fldasignacionadministrador" + " = false where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cambiarContrasena_local;
  }
  public String consultaSQLCambiarContrasenaUsuarioAdministrador()
  {
    String cambiarContrasena_local = "";
    try
    {
      cambiarContrasena_local = "update USUARIO set fldcontrasena = " + mc.colocarEntreComillas(ap.obtenerContrasenaDinamicaAdministradorSistema()) + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(mc.convertirAMayusculas("ADMINISTRADOR"));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cambiarContrasena_local;
  }
  public String consultaSQLGruposInformacionAplicacion(int pIdAplicacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionprincipal" + " <> " + "true" + " order by " + "fldposicion";
  }
  public String consultaSQLGruposInformacionAplicacionMotor(int pIdAplicacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " order by " + "fldidaplicacion" + ',' + "fldposicion";
  }
  public String consultaSQLGruposInformacionEnlazadosAplicacion(int pIdAplicacion, int pIdAplicacionEnlace)
  {
    String consultaSQLGruposInformacionEnlazadosAplicacion_local = "";
    String cadenaAplicacionEnlace_local = null;
    try
    {
      cadenaAplicacionEnlace_local = "";
      consultaSQLGruposInformacionEnlazadosAplicacion_local = "select  distinct " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + ',' + "CAMPO" + " where " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " = " + "CAMPO" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and ";
      if (pIdAplicacion != pIdAplicacionEnlace)
      {
        cadenaAplicacionEnlace_local = "fldenlazado = " + pIdAplicacionEnlace + " or ";
      }
      consultaSQLGruposInformacionEnlazadosAplicacion_local = mc.concatenarCadena(consultaSQLGruposInformacionEnlazadosAplicacion_local, mc.colocarEntreParentesis(cadenaAplicacionEnlace_local + "fldcampocalculado" + " <> " + '1') + " order by " + "fldgrupoinformacionmultiple" + " desc ");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      cadenaAplicacionEnlace_local = null;
    }
    return consultaSQLGruposInformacionEnlazadosAplicacion_local;
  }
  public String consultaSQLGruposInformacionEnlazadosAplicacion(int pIdAplicacion)
  {
    String consultaSQLGruposInformacionEnlazadosAplicacion_local = "";
    try
    {
      consultaSQLGruposInformacionEnlazadosAplicacion_local = "select  distinct " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + ',' + "CAMPO" + " where " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " = " + "CAMPO" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + mc.colocarEntreParentesis("fldenlazado <> 0 or fldcampocalculado <> 1") + " order by " + "fldgrupoinformacionmultiple" + " desc ";
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLGruposInformacionEnlazadosAplicacion_local;
  }
  public String consultaSQLGruposInformacionMultiplesAplicacion(int pIdAplicacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionmultiple" + " = " + "true" + " order by " + "fldposicion";
  }
  public String consultaSQLGruposInformacionAplicaciones()
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION";
  }
  public String consultaSQLGrupoInformacionPorId(int pIdGrupoInformacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion;
  }
  public String consultaSQLNumeroGruposInformacionAplicacion(int pIdAplicacion)
  {
    return "select count" + mc.colocarEntreParentesis("fldidgrupoinformacion") + " as " + "numerogruposinformacion" + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionprincipal" + " <> " + "true";
  }
  public String consultaSQLTablasAplicacion()
  {
    return "select fldidtabla,fldnombretabla,flddescripciontabla from TABLA order by fldidtabla;";
  }
  public String consultaSQLTablaPorId(int pIdTabla)
  {
    return "select " + ap.conformarBloqueCamposTablaTabla() + " from " + "TABLA" + " where " + "fldidtabla" + " = " + pIdTabla;
  }
  public String consultaSQLDescripcionTabla(int pIdTabla)
  {
    return "select flddescripciontabla from TABLA where fldidtabla = " + pIdTabla + ';';
  }
  public String consultaSQLCamposGrupoSinLlavePrimaria(int pIdGrupoInformacion)
  {
    return "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " and " + "fldllaveprimaria" + " = false and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " order by " + "fldposicion";
  }
  public String consultaSQLCamposGrupoInformacionMotor(int pIdGrupoInformacion)
  {
    return "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " order by " + "fldidgrupoinformacion" + ',' + "fldposicion";
  }
  public String consultaSQLCampoPorId(int pIdCampo)
  {
    return "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldidcampo" + " = " + pIdCampo;
  }
  public String consultaSQLCamposTipoTablaAplicacion(int pIdAplicacion, ListaGeneral pListaTablas)
  {
    String consultaCamposTipoTablaAplicacion_local = "";
    if (pListaTablas == ConstantesGeneral.VALOR_NULO)
    {
      return consultaCamposTipoTablaAplicacion_local;
    }
    try
    {
      consultaCamposTipoTablaAplicacion_local = "select fldidcampo,fldetiquetacampo,flddescripciongrupoinformacion from CAMPO,GRUPOINFORMACION where CAMPO.fldidgrupoinformacion = GRUPOINFORMACION.fldidgrupoinformacion and fldidaplicacion = " + pIdAplicacion;
      if (pListaTablas.contarElementos() > 0)
      {
        consultaCamposTipoTablaAplicacion_local = mc.concatenarCadena(consultaCamposTipoTablaAplicacion_local, " and fldtipodato in " + mc.concatenarValoresLista(pListaTablas));
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaCamposTipoTablaAplicacion_local;
  }
  public String consultaSQLCamposTipoTablaGrupoInformacion(int pIdGrupoInformacion, ListaGeneral pListaTablas)
  {
    String consultaCamposTipoTablaGrupoInformacion_local = "";
    if (pListaTablas == ConstantesGeneral.VALOR_NULO)
    {
      return consultaCamposTipoTablaGrupoInformacion_local;
    }
    try
    {
      consultaCamposTipoTablaGrupoInformacion_local = "select fldidcampo,fldetiquetacampo,flddescripciongrupoinformacion from CAMPO,GRUPOINFORMACION where CAMPO.fldidgrupoinformacion = GRUPOINFORMACION.fldidgrupoinformacion and GRUPOINFORMACION.fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldllaveprimaria" + " <> " + "true" + " and " + "fldtipodato" + " in " + mc.concatenarValoresLista(pListaTablas);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaCamposTipoTablaGrupoInformacion_local;
  }
  public String consultaSQLCamposTipoTablaGruposInformacionAplicacion(int pIdAplicacion, boolean pGruposMultiples, ListaGeneral pListaTablas)
  {
    String consultaCamposTipoTablaGrupoInformacion_local = "";
    if (pListaTablas == ConstantesGeneral.VALOR_NULO)
    {
      return consultaCamposTipoTablaGrupoInformacion_local;
    }
    try
    {
      consultaCamposTipoTablaGrupoInformacion_local = "select fldidcampo,fldetiquetacampo,flddescripciongrupoinformacion from CAMPO,GRUPOINFORMACION where CAMPO.fldidgrupoinformacion = GRUPOINFORMACION.fldidgrupoinformacion and fldidaplicacion = " + pIdAplicacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " and " + "fldgrupoinformacionmultiple" + " = " + pGruposMultiples + " and " + "fldllaveprimaria" + " <> " + "true" + " and " + "fldtipodato" + " in " + mc.concatenarValoresLista(pListaTablas);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaCamposTipoTablaGrupoInformacion_local;
  }
  public String consultaSQLCamposListasDependientesCampoMaestro(int pIdCampoMaestro, int pIdGrupoInformacion)
  {
    return "select  distinct fldidcampo,fldnombrecampo from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldlistadependiente" + " = " + pIdCampoMaestro + " order by " + "fldidcampo";
  }
  public String consultaSQLCamposListasDependientesCampoMaestroGruposNoMultiples(int pIdCampoMaestro)
  {
    return "select  distinct fldidcampo,fldnombrecampo from CAMPO,GRUPOINFORMACION where CAMPO.fldidgrupoinformacion = GRUPOINFORMACION.fldidgrupoinformacion and fldgrupoinformacionmultiple <> true and fldlistadependiente = " + pIdCampoMaestro + " order by " + "fldidcampo";
  }
  public String consultaSQLVerificarEsCampoMaestro(int pIdCampo)
  {
    return "select fldlistadependiente from CAMPO where fldlistadependiente = " + pIdCampo + " order by " + "fldidcampo";
  }
  public String consultaSQLCamposHabilitadosPorCampo(int pIdCampo)
  {
    return "select  distinct fldidcampo,fldnombrecampo from CAMPO where fldhabilitadopor = " + pIdCampo + " order by " + "fldidcampo";
  }
  public String consultaSQLVerificarEsCampoHabilitaOtros(int pIdCampo)
  {
    return "select fldhabilitadopor from CAMPO where fldhabilitadopor = " + pIdCampo + " order by " + "fldidcampo";
  }
  public String consultaSQLAplicacionEnlazadaCampo(int pIdCampoEnlazado)
  {
    return "select " + ap.conformarBloqueCamposTablaAplicacion() + " from " + "APLICACION" + ',' + "CAMPO" + " where " + "fldidcampo" + " = " + pIdCampoEnlazado + " and " + "fldidaplicacion" + " = " + "fldenlazado";
  }
  public String consultaSQLCamposMismoNombreEnAplicacion(String pNombreCampo, int pIdAplicacion)
  {
    String consultaCamposMismoNombre_local = "";
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaCamposMismoNombre_local;
    }
    try
    {
      consultaCamposMismoNombre_local = "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldnombrecampo" + " = " + mc.colocarEntreComillas(pNombreCampo) + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " in " + mc.colocarEntreParentesis("select fldidgrupoinformacion from GRUPOINFORMACION where fldidaplicacion = " + pIdAplicacion);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaCamposMismoNombre_local;
  }
  public String consultaSQLCamposMismoTipoGrupoInformacion(int pIdGrupoInformacion, int pIdCampo, String pTipoDato)
  {
    String consultaCamposMismoTipoGrupoInformacion_local = "";
    if (pTipoDato == ConstantesGeneral.VALOR_NULO)
    {
      return consultaCamposMismoTipoGrupoInformacion_local;
    }
    try
    {
      consultaCamposMismoTipoGrupoInformacion_local = "select fldidcampo,fldetiquetacampo from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldtipodato" + " = " + mc.colocarEntreComillas(pTipoDato) + " and " + "fldidcampo" + " <> " + pIdCampo;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaCamposMismoTipoGrupoInformacion_local;
  }
  public String consultaSQLCamposEnlazadosGrupoInformacion(int pIdGrupoInformacion, int pIdCampo)
  {
    String consultaSQLCamposEnlazadosGrupoInformacion = "";
    try
    {
      consultaSQLCamposEnlazadosGrupoInformacion = "select fldidcampo,fldetiquetacampo,flddescripciongrupoinformacion from CAMPO,GRUPOINFORMACION where CAMPO.fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldenlazado" + " <> " + (int)Character.MIN_VALUE + " and " + "fldidcampo" + " <> " + pIdCampo;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLCamposEnlazadosGrupoInformacion;
  }
  public String consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion(GrupoInformacion pGrupoInformacion)
  {
    String consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion_local = "";
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion_local;
    }
    try
    {
      consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion_local = "select fldidcampo,fldetiquetacampo,flddescripciongrupoinformacion from CAMPO,GRUPOINFORMACION where fldidaplicacion = " + pGrupoInformacion.getAplicacion().getIdAplicacion() + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldgrupoinformacionmultiple" + " <> " + "true" + " and " + "fldenlazado" + " <> " + (int)Character.MIN_VALUE + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " <> " + pGrupoInformacion.getIdGrupoInformacion();
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLCamposEnlazadosGruposInformacionNoMultiplesAplicacion_local;
  }
  public String consultaSQLNumeroCamposDocumento(int pIdGrupoInformacion)
  {
    String numeroCamposNoTexto_local = "select count" + mc.colocarEntreParentesis("fldidcampo") + " as " + "fldnumerocamposdocumentos" + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " and " + "fldtipodato" + " = " + mc.colocarEntreComillas("DD");
    return numeroCamposNoTexto_local;
  }
  public String consultaSQLNumeroCamposDocumentosGruposNoMultiples(int pIdAplicacion)
  {
    return "select count" + mc.colocarEntreParentesis("fldidcampo") + " as " + "fldnumerocamposdocumentos" + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " = " + "CAMPO" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionmultiple" + " = false and " + "fldtipodato" + " = " + mc.colocarEntreComillas("DD");
  }
  public String consultaSQLNumeroCamposArchivo(int pIdGrupoInformacion)
  {
    String numeroCamposNoTexto_local = "select count" + mc.colocarEntreParentesis("fldidcampo") + " as " + "fldnumerocampos" + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " and " + "fldtipodato" + " = " + mc.colocarEntreComillas("J");
    return numeroCamposNoTexto_local;
  }
  public String consultaSQLNumeroCamposArchivoGruposNoMultiples(int pIdAplicacion)
  {
    return "select count" + mc.colocarEntreParentesis("fldidcampo") + " as " + "fldnumerocampos" + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " = " + "CAMPO" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionmultiple" + " = false and " + "fldtipodato" + " = " + mc.colocarEntreComillas("J");
  }
  public String consultaSQLObtenerTipoUsuarioPorContrasena(String pNombreUsuario, String pContrasena)
  {
    String verificarUsuario_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return verificarUsuario_local;
    }
    if (pContrasena == ConstantesGeneral.VALOR_NULO)
    {
      return verificarUsuario_local;
    }
    try
    {
      verificarUsuario_local = "select fldidtipousuario from USUARIO where fldnombreusuario = " + mc.colocarEntreComillas(pNombreUsuario) + " and " + "fldcontrasena" + " = " + mc.colocarEntreComillas(pContrasena);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarUsuario_local;
  }
  public String consultaSQLObtenerAsignacionAdministradorUsuario(String pNombreUsuario)
  {
    String consultaSQLObtenerAsignacionAdministradorUsuario_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLObtenerAsignacionAdministradorUsuario_local;
    }
    try
    {
      consultaSQLObtenerAsignacionAdministradorUsuario_local = "select fldasignacionadministrador from USUARIO where fldnombreusuario = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLObtenerAsignacionAdministradorUsuario_local;
  }
  public String consultaSQLVerificarUsuarioPorNombre(String pNombreUsuario)
  {
    String verificarUsuario_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return verificarUsuario_local;
    }
    try
    {
      verificarUsuario_local = "select fldidtipousuario from USUARIO where fldnombreusuario = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarUsuario_local;
  }
  public String consultaSQLVerificarExistenciaGrupoInformacion(int pIdAplicacion, String pNombreGrupoInformacion)
  {
    String consultaVerificarExistenciaGrupoInformacion_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaVerificarExistenciaGrupoInformacion_local;
    }
    try
    {
      consultaVerificarExistenciaGrupoInformacion_local = "select fldidgrupoinformacion from GRUPOINFORMACION where fldidaplicacion = " + pIdAplicacion + " and " + "fldnombregrupoinformacion" + " = " + '\'' + pNombreGrupoInformacion + '\'';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaVerificarExistenciaGrupoInformacion_local;
  }
  public String consultaSQLGrupoInformacionPorDescripcion(int pIdAplicacion, String pDescripcionGrupoInformacion)
  {
    String consultaVerificarExistenciaGrupoInformacion_local = "";
    if (pDescripcionGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaVerificarExistenciaGrupoInformacion_local;
    }
    try
    {
      consultaVerificarExistenciaGrupoInformacion_local = "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "flddescripciongrupoinformacion" + " = " + mc.colocarEntreComillas(mc.convertirAMayusculas(pDescripcionGrupoInformacion));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaVerificarExistenciaGrupoInformacion_local;
  }
  public String consultaSQLCampoPorEtiquetaGruposNoMultiples(int pIdAplicacion, String pEtiquetaCampo)
  {
    String consultaIdCampo_local = "";
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaIdCampo_local;
    }
    try
    {
      consultaIdCampo_local = "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionmultiple" + " = false and " + "fldetiquetacampo" + " = " + mc.colocarEntreComillas(pEtiquetaCampo);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaIdCampo_local;
  }
  public String consultaSQLCampoPorEtiquetaGrupoMultiple(int pIdGrupoInformacion, String pEtiquetaCampo)
  {
    String consultaIdCampo_local = "";
    if (pEtiquetaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaIdCampo_local;
    }
    try
    {
      consultaIdCampo_local = "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " and " + "fldetiquetacampo" + " = " + mc.colocarEntreComillas(pEtiquetaCampo);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaIdCampo_local;
  }
  public String consultaSQLBorrarCampo(int pIdCampo)
  {
    return " delete  from CAMPO where fldidcampo = " + pIdCampo + ';';
  }
  public String consultaSQLIdUltimoRegistro(String pNombreTabla, String pNombreIdentificador, String pNombreLlavePrimaria, int pValorLlavePrimaria)
  {
    String consultaSQLIdUltimoRegistro_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreIdentificador == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    try
    {
      consultaSQLIdUltimoRegistro_local = "select  max(" + pNombreIdentificador + ')' + " as " + "ultimoregistro" + " from " + pNombreTabla;
      if (pValorLlavePrimaria != -1)
      {
        consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, " where " + pNombreLlavePrimaria + " = " + pValorLlavePrimaria);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLIdUltimoRegistro_local;
  }
  public String consultaSQLIdUltimoRegistroIncluido(String pNombreTabla, String pNombreLlavePrimaria, String pNombreLlavePrimariaPrincipal, int pValorLlavePrincipal)
  {
    String consultaSQLIdUltimoRegistro_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreLlavePrimariaPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    try
    {
      consultaSQLIdUltimoRegistro_local = "select  max(" + pNombreLlavePrimaria + ')' + " as " + "ultimoregistro" + " from " + pNombreTabla;
      if (pValorLlavePrincipal != -1)
      {
        consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, " where " + pNombreLlavePrimariaPrincipal + " = " + pValorLlavePrincipal);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLIdUltimoRegistro_local;
  }
  public String consultaSQLIdUltimoRegistroIncluidoGrupoInformacion(String pNombreGrupoInformacion, String pNombreLlavePrimaria, String pNombreCampoValorUnico, String pNombreLlavePrincipal, int pValorLlavePrincipal)
  {
    String consultaSQLIdUltimoRegistro_local = "";
    String nombreCampo_local = null;
    String subConsulta_local = null;
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreCampoValorUnico == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdUltimoRegistro_local;
    }
    try
    {
      nombreCampo_local = pNombreLlavePrimaria;
      if (!mc.esCadenaVacia(pNombreCampoValorUnico))
      {
        nombreCampo_local = pNombreCampoValorUnico;
      }
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, "select ");
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, pNombreLlavePrimaria);
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, " as ultimoregistro");
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, " from " + pNombreGrupoInformacion);
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, " where ");
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, nombreCampo_local + " in ");
      subConsulta_local = mc.concatenarCadena("select  max(", nombreCampo_local + String.valueOf(')') + " from " + pNombreGrupoInformacion);
      if (pValorLlavePrincipal != -1)
      {
        subConsulta_local = mc.concatenarCadena(subConsulta_local, " where ");
        subConsulta_local = mc.concatenarCadena(subConsulta_local, pNombreLlavePrincipal + " = " + pValorLlavePrincipal);
      }
      consultaSQLIdUltimoRegistro_local = mc.concatenarCadena(consultaSQLIdUltimoRegistro_local, mc.colocarEntreParentesis(subConsulta_local));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreCampo_local = null;
      subConsulta_local = null;
    }
    return consultaSQLIdUltimoRegistro_local;
  }
  public String consultaSQLIdPrimerRegistroIncluido(String pNombreGrupoInformacion, String pNombreLlavePrimaria, String pNombreCampoValorUnico, String pNombreLlavePrincipal, int pValorLlavePrincipal)
  {
    String consultaSQLIdPrimerRegistro_local = "";
    String nombreCampo_local = null;
    String subConsulta_local = null;
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdPrimerRegistro_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdPrimerRegistro_local;
    }
    if (pNombreCampoValorUnico == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdPrimerRegistro_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdPrimerRegistro_local;
    }
    try
    {
      nombreCampo_local = pNombreLlavePrimaria;
      if (!mc.esCadenaVacia(pNombreCampoValorUnico))
      {
        nombreCampo_local = pNombreCampoValorUnico;
      }
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, "select ");
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, pNombreLlavePrimaria);
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, " as primerregistro");
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, " from " + pNombreGrupoInformacion);
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, " where ");
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, nombreCampo_local + " in ");
      subConsulta_local = mc.concatenarCadena("select  min(", nombreCampo_local + String.valueOf(')') + " from " + pNombreGrupoInformacion);
      if (pValorLlavePrincipal != -1)
      {
        subConsulta_local = mc.concatenarCadena(subConsulta_local, " where ");
        subConsulta_local = mc.concatenarCadena(subConsulta_local, pNombreLlavePrincipal + " = " + pValorLlavePrincipal);
      }
      consultaSQLIdPrimerRegistro_local = mc.concatenarCadena(consultaSQLIdPrimerRegistro_local, mc.colocarEntreParentesis(subConsulta_local));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreCampo_local = null;
      subConsulta_local = null;
    }
    return consultaSQLIdPrimerRegistro_local;
  }
  public String consultaSQLNombrePrimerCampoValorUnicoGrupoInformacionMultiple(int pIdGrupoInformacion)
  {
    return "select fldnombrecampo from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldvalorunico" + " = " + "true" + " and " + "fldllaveprimaria" + " = false order by " + "CAMPO" + '.' + "fldposicion";
  }
  public String consultaSQLNombrePrimerCampoValorUnicoGruposInformacionNoMultiple(int pIdAplicacion)
  {
    return "select fldnombrecampo from CAMPO,GRUPOINFORMACION where CAMPO.fldidgrupoinformacion = GRUPOINFORMACION.fldidgrupoinformacion and fldgrupoinformacionmultiple <> true and fldidaplicacion = " + pIdAplicacion + " and " + "fldvalorunico" + " = " + "true" + " and " + "fldllaveprimaria" + " = false order by " + "CAMPO" + '.' + "fldposicion";
  }
  public String consultaSQLAplicacionPorId(int pIdAplicacion)
  {
    String consultaSQLAplicacion_local = "";
    try
    {
      consultaSQLAplicacion_local = "select " + ap.conformarBloqueCamposTablaAplicacion() + " from " + "APLICACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLAplicacion_local;
  }
  public String consultaSQLAplicacionPorNombre(String pNombreAplicacion)
  {
    String consultaSQLAplicacion_local = "";
    if (pNombreAplicacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLAplicacion_local;
    }
    try
    {
      pNombreAplicacion = mc.convertirAMayusculas(pNombreAplicacion);
      consultaSQLAplicacion_local = "select " + ap.conformarBloqueCamposTablaAplicacion() + " from " + "APLICACION" + " where " + "fldnombreaplicacion" + " = " + mc.colocarEntreComillas(pNombreAplicacion);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLAplicacion_local;
  }
  public String consultaSQLGrupoInformacionPrincipal(int pIdAplicacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldgrupoinformacionprincipal" + " = " + "true";
  }
  public String consultaSQLGrupoInformacionCampoEsLlavePrimaria(String pNombreCampo, int pIdAplicacion)
  {
    String consultaSQL_local = "";
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " = " + "CAMPO" + '.' + "fldidgrupoinformacion" + " and " + "GRUPOINFORMACION" + '.' + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldnombrecampo" + " = " + mc.colocarEntreComillas(pNombreCampo) + " and " + "fldllaveprimaria" + " = " + "true";
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLBorrarRegistrosCamposSisnet(Campo pCampo)
  {
    String consultaCamposMismoNombre_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaCamposMismoNombre_local;
    }
    try
    {
      consultaCamposMismoNombre_local = " delete  from CAMPO where fldnombrecampo = " + mc.colocarEntreComillas(pCampo.getNombreCampo()) + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " in " + mc.colocarEntreParentesis("select fldidgrupoinformacion from GRUPOINFORMACION where fldidaplicacion = " + pCampo.getGrupoInformacion().getAplicacion().getIdAplicacion());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaCamposMismoNombre_local;
  }
  public String consultaSQLBorrarRegistrosCamposGrupoInformacion(int pIdGrupoInformacion)
  {
    return " delete  from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion;
  }
  public String consultaSQLSeleccionValorTabla(String pNombreTabla, int pValorLlavePrimaria)
  {
    String consultaSQLSeleccionValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLSeleccionValorTabla_local;
    }
    try
    {
      consultaSQLSeleccionValorTabla_local = "select fldid" + pNombreTabla + ',' + pNombreTabla + " from " + pNombreTabla + " where " + "fldid" + pNombreTabla + " = " + String.valueOf(pValorLlavePrimaria);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLSeleccionValorTabla_local;
  }
  public String consultaSQLUltimaPosicionGrupoInformacion(int pIdAplicacion)
  {
    return "select  max(fldposicion) as ultimaposicion from GRUPOINFORMACION where fldidaplicacion = " + pIdAplicacion + " and " + "fldposicion" + " <> " + -1;
  }
  public String consultaSQLUltimaPosicionCamposGrupoInformacion(int pIdGrupoInformacion)
  {
    return "select  max(fldposicion) as ultimaposicion from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldposicion" + " <> " + -1;
  }
  public String consultaSQLValorTabla(String pNombreTabla, int pIdValor)
  {
    String consultaSQLValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValorTabla_local;
    }
    try
    {
      consultaSQLValorTabla_local = "select " + pNombreTabla + " from " + pNombreTabla + " where " + "fldid" + pNombreTabla + " = " + pIdValor + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLValorTabla_local;
  }
  public String consultaSQLValorDependiente(int pIdValorDependiente)
  {
    return "select fldidvalordependiente,fldidcampo,fldidvalormaestro,fldidvalordetalle from VALORDEPENDIENTE where fldidvalordependiente = " + pIdValorDependiente + ';';
  }
  public String consultaSQLValoresHabilitadosCampo(int pIdCampo)
  {
    return "select  distinct fldidvalormaestro from DEPENDIENTEHABILITACION where fldidcampo = " + pIdCampo + " and " + "fldhabilitacion" + " = true order by " + "fldidvalormaestro" + ';';
  }
  public String consultaSQLIdValorTabla(String pNombreTabla, String pValorTabla)
  {
    String consultaSQLIdValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorTabla_local;
    }
    try
    {
      consultaSQLIdValorTabla_local = "select fldid" + pNombreTabla + " from " + pNombreTabla + " where " + pNombreTabla + " = " + mc.colocarEntreComillas(pValorTabla) + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLIdValorTabla_local;
  }
  public String consultaSQLIdValorDetalle(int pIdValorDependiente)
  {
    return "select fldidvalordetalle from VALORDEPENDIENTE where fldidvalordependiente = " + pIdValorDependiente + ';';
  }
  public String consultaSQLIdValoresMaestroCampo(int pIdCampo)
  {
    return "select  distinct fldidvalormaestro from VALORDEPENDIENTE where fldidcampo = " + pIdCampo + " order by " + "fldidvalormaestro" + ';';
  }
  public String consultaSQLIdValoresDetalleDeMaestro(int pIdCampo, int pIdValorMaestro)
  {
    return "select fldidvalordetalle from VALORDEPENDIENTE where fldidcampo = " + pIdCampo + " and " + "fldidvalormaestro" + " = " + pIdValorMaestro + " order by " + "fldidvalordetalle" + ';';
  }
  public String consultaSQLValorCampo(Campo pCampo, String pNombreLlave, int pValorIdentificador)
  {
    String consultaSQLIdValorRegistro_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLIdValorRegistro_local = "select " + pCampo.getNombreCampo() + " from " + nombreGrupoInformacion_local + " where " + pNombreLlave + " = " + pValorIdentificador + " order by " + pCampo.getNombreCampo();
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLIdValorRegistro_local;
  }
  public String consultaSQLIdValorCampoHabilita(String pNombreGrupoInformacion, String pNombreCampoHabilita, String pNombreLlave, String pNombreLlavePrincipal, int pValorLlavePrincipal, ListaGeneral pListaValoresHabilitan)
  {
    String consultaSQLIdValorRegistro_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    if (pNombreCampoHabilita == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    if (pListaValoresHabilitan == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    try
    {
      consultaSQLIdValorRegistro_local = "select  max(" + pNombreLlave + ')' + " as " + "ultimoregistro" + " from " + pNombreGrupoInformacion + " where " + pNombreCampoHabilita + " in " + mc.concatenarValoresLista(pListaValoresHabilitan) + " and " + pNombreLlavePrincipal + " = " + pValorLlavePrincipal;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLIdValorRegistro_local;
  }
  public String consultaSQLActualizarValorCampo(Campo pCampo, String pNombreLlave, int pValorLlavePrimaria, String pValorCampo, boolean pEsNumero)
  {
    String consultaSQLActualizarValorCampo_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorCampo_local;
    }
    if (pNombreLlave == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorCampo_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorCampo_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLActualizarValorCampo_local = "update " + nombreGrupoInformacion_local + " set " + pCampo.getNombreCampo() + " = ";
      if (pEsNumero || mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo, "null"))
      {
        consultaSQLActualizarValorCampo_local = mc.concatenarCadena(consultaSQLActualizarValorCampo_local, pValorCampo);
      }
      else
      {
        consultaSQLActualizarValorCampo_local = mc.concatenarCadena(consultaSQLActualizarValorCampo_local, mc.colocarEntreComillas(pValorCampo));
      }
      consultaSQLActualizarValorCampo_local = mc.concatenarCadena(consultaSQLActualizarValorCampo_local, " where " + pNombreLlave + " = " + pValorLlavePrimaria);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLActualizarValorCampo_local;
  }
  public String consultaSQLValoresRegistro(Campo pCampo, int pValorLlavePrincipal, String pNombreCampoValorUnico)
  {
    String consultaSQLValoresRegistro_local = "";
    String nombreGrupoInformacion_local = null;
    String nombreCampoValorUnico_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValoresRegistro_local;
    }
    try
    {
      if (!mc.esCadenaVacia(pNombreCampoValorUnico))
      {
        nombreCampoValorUnico_local = pNombreCampoValorUnico;
      }
      else
      {
        nombreCampoValorUnico_local = ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local);
      }
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLValoresRegistro_local = "select " + pCampo.getNombreCampo() + ',' + ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local) + " from " + nombreGrupoInformacion_local + " where " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()) + " = " + pValorLlavePrincipal + " order by " + nombreCampoValorUnico_local;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
      nombreCampoValorUnico_local = null;
    }
    return consultaSQLValoresRegistro_local;
  }
  public String consultaSQLVerificarValorRegistro(String pNombreGrupoInformacion, String pNombreCampo, String pNombreLlavePrincipal, int pValorRegistro)
  {
    String consultaSQLVerificarValorRegistro_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificarValorRegistro_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificarValorRegistro_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificarValorRegistro_local;
    }
    try
    {
      consultaSQLVerificarValorRegistro_local = "select " + pNombreLlavePrincipal + " from " + pNombreGrupoInformacion + " where " + pNombreCampo + " = " + pValorRegistro + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLVerificarValorRegistro_local;
  }
  public String consultaSQLVerificarExistenciaRegistrosGrupoInformacion(String pNombreGrupoInformacion, String pNombreLlavePrincipal)
  {
    String consultaSQLVerificarExistenciaRegistrosGrupoInformacion_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificarExistenciaRegistrosGrupoInformacion_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificarExistenciaRegistrosGrupoInformacion_local;
    }
    try
    {
      consultaSQLVerificarExistenciaRegistrosGrupoInformacion_local = "select " + pNombreLlavePrincipal + " from " + pNombreGrupoInformacion + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLVerificarExistenciaRegistrosGrupoInformacion_local;
  }
  public String consultaSQLActualizarValorDependienteEnlazado(Campo pCampo, String pValorCampo, int pValorEnlace, String pNombreLlavePrincipal, int pValorLlavePrincipal, int pValorLlavePrimaria)
  {
    String consultaSQLActualizarValorDependienteEnlazado_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorDependienteEnlazado_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorDependienteEnlazado_local;
    }
    if (pNombreLlavePrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorDependienteEnlazado_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      if (mc.sonCadenasIguales(pValorCampo, "") && (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "F") || mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "H")))
      {
        pValorCampo = "null";
      }
      if (pCampo.esTipoDatoTexto() && !mc.sonCadenasIgualesIgnorarMayusculas(pValorCampo, "null"))
      {
        pValorCampo = mc.colocarEntreComillas(pValorCampo);
      }
      if ((pCampo.esTipoDatoNumerico() || pCampo.esTipoDatoTabla()) && mc.sonCadenasIguales(pValorCampo, ""))
      {
        pValorCampo = String.valueOf(0);
      }
      consultaSQLActualizarValorDependienteEnlazado_local = "update " + nombreGrupoInformacion_local + " set " + pCampo.getNombreCampo() + " = " + pValorCampo + " where " + pCampo.getEnlaceCampo().getCampoEnlaceDepende().getNombreCampo() + " = " + pValorEnlace + " and " + pNombreLlavePrincipal + " = " + pValorLlavePrincipal;
      if (pValorLlavePrimaria != -1)
      {
        consultaSQLActualizarValorDependienteEnlazado_local = mc.concatenarCadena(consultaSQLActualizarValorDependienteEnlazado_local, " and " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getNombreGrupoInformacion()) + " = " + pValorLlavePrimaria);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLActualizarValorDependienteEnlazado_local;
  }
  public String conformarConsultaSQLSeleccionTabla(String pNombreTabla)
  {
    String consultaSqlSeleccionTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSqlSeleccionTabla_local;
    }
    try
    {
      consultaSqlSeleccionTabla_local = "select " + ap.conformarNombreCampoLlavePrimaria(pNombreTabla) + ',' + pNombreTabla + " from " + pNombreTabla + " order by " + pNombreTabla;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSqlSeleccionTabla_local;
  }
  public String conformarConsultaSQLSeleccionTablaDepende(int pIdCampo, int pIdValorMaestro)
  {
    String consultaSqlSeleccionTabla_local = "select fldidvalordependiente,fldidvalordetalle from VALORDEPENDIENTE where fldidcampo = " + String.valueOf(pIdCampo) + " and " + "fldidvalormaestro" + " = " + String.valueOf(pIdValorMaestro) + " order by " + "fldidvalordependiente";
    return consultaSqlSeleccionTabla_local;
  }
  public String consultaSQLSeleccionDependienteHabilitacion(int pIdCampo, int pIdValorMaestro)
  {
    return "select fldiddependientehabilitacion,fldhabilitacion from DEPENDIENTEHABILITACION where fldidcampo = " + pIdCampo + " and " + "fldidvalormaestro" + " = " + pIdValorMaestro + " order by " + "fldiddependientehabilitacion";
  }
  public String consultaSQLHabilitacion(int pIdDependienteHabilitacion)
  {
    return "select fldiddependientehabilitacion,fldidcampo,fldidvalormaestro,fldhabilitacion from DEPENDIENTEHABILITACION where fldiddependientehabilitacion = " + pIdDependienteHabilitacion;
  }
  public String conformarConsultaSQLBorrado(GrupoInformacion pGrupoInformacion, int pValorLlavePrimaria)
  {
    String cadenaSQLBorrado_local = "";
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLBorrado_local;
    }
    try
    {
      cadenaSQLBorrado_local = " delete  from " + pGrupoInformacion.getNombreGrupoInformacion() + " where " + ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getNombreGrupoInformacion()) + " = " + String.valueOf(pValorLlavePrimaria);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaSQLBorrado_local;
  }
  public String conformarConsultaSQLBorradoRegistrosGrupoInformacionMultiple(GrupoInformacion pGrupoInformacion)
  {
    String cadenaSQLBorrado_local = "";
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLBorrado_local;
    }
    try
    {
      cadenaSQLBorrado_local = " delete  from " + pGrupoInformacion.getNombreGrupoInformacion();
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaSQLBorrado_local;
  }
  public String obtenerCadenaSQLBorrarRegistroValorTabla(String pNombreTabla)
  {
    String cadenaBorrarRegistroValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaBorrarRegistroValorTabla_local;
    }
    try
    {
      cadenaBorrarRegistroValorTabla_local = " delete ";
      cadenaBorrarRegistroValorTabla_local = mc.concatenarCadena(cadenaBorrarRegistroValorTabla_local, " from ");
      cadenaBorrarRegistroValorTabla_local = mc.concatenarCadena(cadenaBorrarRegistroValorTabla_local, pNombreTabla);
      cadenaBorrarRegistroValorTabla_local = mc.concatenarCadena(cadenaBorrarRegistroValorTabla_local, " where ");
      cadenaBorrarRegistroValorTabla_local = mc.concatenarCadena(cadenaBorrarRegistroValorTabla_local, "fldid" + pNombreTabla + String.valueOf('='));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaBorrarRegistroValorTabla_local;
  }
  public String conformarConsultaSQLBorrarValorTabla(String pNombreTabla, int pValorLlavePrimaria)
  {
    String cadenaSQLBorrarValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLBorrarValorTabla_local;
    }
    try
    {
      cadenaSQLBorrarValorTabla_local = obtenerCadenaSQLBorrarRegistroValorTabla(pNombreTabla);
      cadenaSQLBorrarValorTabla_local = mc.concatenarCadena(cadenaSQLBorrarValorTabla_local, String.valueOf(pValorLlavePrimaria));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaSQLBorrarValorTabla_local;
  }
  public String conformarConsultaSQLModificacionValorTabla(String pNombreTabla, int pValorLlavePrimaria, String pValorTabla)
  {
    String cadenaSQLModificacionValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLModificacionValorTabla_local;
    }
    if (pValorTabla == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaSQLModificacionValorTabla_local;
    }
    try
    {
      cadenaSQLModificacionValorTabla_local = "update " + pNombreTabla + " set " + pNombreTabla + " = " + mc.colocarEntreComillas(mc.convertirAMayusculas(pValorTabla)) + " where " + "fldid" + pNombreTabla + " = " + String.valueOf(pValorLlavePrimaria);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaSQLModificacionValorTabla_local;
  }
  public String conformarConsultaSQLInsercionValorDependiente(int pConsecutivo, int pIdCampo, int pValorMaestro, int pValorDetalle)
  {
    return "insert into VALORDEPENDIENTE" + mc.colocarEntreParentesis("fldidvalordependiente,fldidcampo,fldidvalormaestro,fldidvalordetalle") + ' ' + " values( " + pConsecutivo + ',' + pIdCampo + ',' + pValorMaestro + ',' + pValorDetalle + ')' + ';';
  }
  public String conformarConsultaSQLModificacionValorDependencia(int pIdValorDependiente, int pIdValorDetalle)
  {
    return "update VALORDEPENDIENTE set fldidvalordetalle = " + pIdValorDetalle + " where " + "fldidvalordependiente" + " = " + pIdValorDependiente;
  }
  public String conformarConsultaSQLInsercionDependienteHabilitacion(int pConsecutivo, int pIdCampo, int pValorMaestro, boolean pHabilitado)
  {
    return "insert into DEPENDIENTEHABILITACION" + mc.colocarEntreParentesis("fldiddependientehabilitacion,fldidcampo,fldidvalormaestro,fldhabilitacion") + ' ' + " values( " + pConsecutivo + ',' + pIdCampo + ',' + pValorMaestro + ',' + pHabilitado + ')' + ';';
  }
  public String conformarConsultaSQLModificacionDependienteHabilitacion(int pIdDependienteHabilitacion, boolean pHabilitacion)
  {
    return "update DEPENDIENTEHABILITACION set fldhabilitacion = " + pHabilitacion + " where " + "fldiddependientehabilitacion" + " = " + pIdDependienteHabilitacion;
  }
  public String conformarConsultaSQLBorrarValorDependiente(int pIdValorDependiente)
  {
    return " delete  from VALORDEPENDIENTE where fldidvalordependiente = " + pIdValorDependiente + ';';
  }
  public String conformarConsultaSQLBorrarValoresDependientesCampo(int pIdCampo)
  {
    return " delete  from VALORDEPENDIENTE where fldidcampo = " + pIdCampo + ';';
  }
  public String conformarConsultaSQLBorrarDependienteHabilitacion(int pIdDependienteHabilitacion)
  {
    return " delete  from DEPENDIENTEHABILITACION where fldiddependientehabilitacion = " + pIdDependienteHabilitacion + ';';
  }
  public String conformarConsultaSQLBorrarDependientesHabilitacionCampo(int pIdCampo)
  {
    return " delete  from DEPENDIENTEHABILITACION where fldidcampo = " + pIdCampo + ';';
  }
  public String conformarConsultaSQLPrincipal(String pNombreGrupoInformacionPrincipal, String pNombreLlavePrimaria, String pNombreCampoValorUnico, int pValorLlavePrimaria)
  {
    String consultaPrincipal_local = "";
    if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaPrincipal_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaPrincipal_local;
    }
    if (pNombreCampoValorUnico == ConstantesGeneral.VALOR_NULO)
    {
      return consultaPrincipal_local;
    }
    try
    {
      consultaPrincipal_local = "select " + pNombreLlavePrimaria + " from " + pNombreGrupoInformacionPrincipal;
      if (pValorLlavePrimaria != -1)
      {
        consultaPrincipal_local = mc.concatenarCadena(consultaPrincipal_local, " where " + pNombreLlavePrimaria + " = " + pValorLlavePrimaria);
      }
      if (mc.esCadenaVacia(pNombreCampoValorUnico))
      {
        consultaPrincipal_local = mc.concatenarCadena(consultaPrincipal_local, " order by " + pNombreLlavePrimaria);
      }
      else
      {
        consultaPrincipal_local = mc.concatenarCadena(consultaPrincipal_local, " order by " + pNombreCampoValorUnico);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaPrincipal_local;
  }
  public String conformarConsultaSQLListaCamposGrupoInformacionNoMultiple(String pNombreGrupoInformacion, int pValorLlavePrimaria, ListaCampo pListaCampo)
  {
    String consultaSQLListaCampos_local = "";
    String nombreLlavePrimaria_local = null;
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    try
    {
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pNombreGrupoInformacion);
      consultaSQLListaCampos_local = "select " + pListaCampo.concatenarCamposGrupoInformacion();
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, " from " + pNombreGrupoInformacion);
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, " where " + nombreLlavePrimaria_local + " = " + pValorLlavePrimaria);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreLlavePrimaria_local = null;
    }
    return consultaSQLListaCampos_local;
  }
  public String conformarConsultaSQLListaCamposGrupoInformacionNoMultiple(String pNombreGrupoInformacion, ArrayList<Integer> pValorLlavePrimaria, ListaCampo pListaCampo)
  {
    String consultaSQLListaCampos_local = "";
    String nombreLlavePrimaria_local = null;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <pValorLlavePrimaria.size() ; i++) {
      int num = pValorLlavePrimaria.get(i);
      sb.append(num);
      if(i+1 < pValorLlavePrimaria.size())
      {
    	 sb.append(",");  
      }
      
    }
    String result = sb.toString();
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    try
    {
      nombreLlavePrimaria_local = ap.conformarNombreCampoLlavePrimaria(pNombreGrupoInformacion);
      //se agrega tambien el campo de la clave 
      consultaSQLListaCampos_local = "select " + nombreLlavePrimaria_local + " , " + pListaCampo.concatenarCamposGrupoInformacion();
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, " from " + pNombreGrupoInformacion);
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, " where " + nombreLlavePrimaria_local + " in (" + sb + ")");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreLlavePrimaria_local = null;
    }
    return consultaSQLListaCampos_local;
  }
  public String conformarConsultaSQLListaCamposGrupoInformacionMultiple(String pNombreGrupoInformacion, String pNombreLlavePrimariaPrincipal, String pNombreLlavePrimariaGrupoInformacion, int pValorLlavePrimaria, ListaCampo pListaCampo)
  {
    String consultaSQLListaCampos_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    if (pNombreLlavePrimariaPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    if (pNombreLlavePrimariaGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLListaCampos_local;
    }
    try
    {
      consultaSQLListaCampos_local = "select " + pNombreLlavePrimariaGrupoInformacion;
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, String.valueOf(','));
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, pListaCampo.concatenarCamposGrupoInformacion());
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, " from " + pNombreGrupoInformacion);
      consultaSQLListaCampos_local = mc.concatenarCadena(consultaSQLListaCampos_local, " where " + pNombreLlavePrimariaPrincipal + " = " + pValorLlavePrimaria + " order by " + pNombreLlavePrimariaGrupoInformacion + " desc ");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLListaCampos_local;
  }
  public String consultaSQLVerificarCamposVisibles(int pIdGrupoInformacion)
  {
    return "select count" + mc.colocarEntreParentesis("fldidcampo") + " as " + "fldnumerocamposcamposvisiblesusuario" + " from " + "CAMPO" + " where " + "fldvisibleusuarioprincipal" + " = " + '1' + " and " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion;
  }
  public String consultaSQLRegistrosCampoValorUnico(String pNombreGrupoPrincipal, String pNombreCampo, String pCondicionesFiltrado)
  {
    String consultaSQLRegistrosCampoValorUnico_local = "";
    if (pNombreGrupoPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLRegistrosCampoValorUnico_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLRegistrosCampoValorUnico_local;
    }
    if (pCondicionesFiltrado == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLRegistrosCampoValorUnico_local;
    }
    try
    {
      consultaSQLRegistrosCampoValorUnico_local = mc.concatenarCadena("select ", mc.concatenarCadena("fldid", pNombreGrupoPrincipal));
      consultaSQLRegistrosCampoValorUnico_local = mc.concatenarCadena(consultaSQLRegistrosCampoValorUnico_local, mc.concatenarCadena(String.valueOf(','), pNombreCampo));
      consultaSQLRegistrosCampoValorUnico_local = mc.concatenarCadena(consultaSQLRegistrosCampoValorUnico_local, mc.concatenarCadena(" from ", pNombreGrupoPrincipal));
      if (!mc.esCadenaVacia(pCondicionesFiltrado))
      {
        consultaSQLRegistrosCampoValorUnico_local = mc.concatenarCadena(consultaSQLRegistrosCampoValorUnico_local, mc.concatenarCadena(" where ", pCondicionesFiltrado));
      }
      consultaSQLRegistrosCampoValorUnico_local = mc.concatenarCadena(consultaSQLRegistrosCampoValorUnico_local, mc.concatenarCadena(" order by ", pNombreCampo));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLRegistrosCampoValorUnico_local;
  }
  public String consultaSQLPosicionGrupoInformacion(int pIdGrupoInformacion)
  {
    return "select fldposicion from GRUPOINFORMACION where fldidgrupoinformacion = " + pIdGrupoInformacion + ';';
  }
  public String consultaSQLActualizarPosicionesGruposInformacionAplicacion(int pIdAplicacion, int pPosicionInicial)
  {
    return "update GRUPOINFORMACION set fldposicion = fldposicion-1 where fldidaplicacion = " + pIdAplicacion + " and " + "fldposicion" + '>' + pPosicionInicial + " and " + "fldgrupoinformacionprincipal" + " <> " + "true" + ';';
  }
  public String consultaSQLGrupoInformacionPorPosicion(int pPosicionGrupoInformacion, int pIdAplicacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldposicion" + " = " + pPosicionGrupoInformacion;
  }
  public String consultaSQLActualizarGrupoInformacionIncrementar(int pIdGrupoInformacion)
  {
    return "update GRUPOINFORMACION set fldposicion = fldposicion+1 where fldidgrupoinformacion = " + pIdGrupoInformacion;
  }
  public String consultaSQLActualizarGrupoInformacionDecrementar(int pIdGrupoInformacion)
  {
    return "update GRUPOINFORMACION set fldposicion = fldposicion-1 where fldidgrupoinformacion = " + pIdGrupoInformacion;
  }
  public String consultaSQLPosicionCampo(int pIdCampo)
  {
    return "select fldposicion from CAMPO where fldidcampo = " + pIdCampo + ';';
  }
  public String consultaSQLActualizarPosicionesCamposGrupoInformacion(int pIdGrupoInformacion, int pPosicionInicial)
  {
    return "update CAMPO set fldposicion = fldposicion-1 where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldposicion" + '>' + pPosicionInicial + " and " + "fldllaveprimaria" + " <> " + "true" + ';';
  }
  public String consultaSQLUltimaPosicionCampo(int pIdGrupoInformacion)
  {
    return "select  max(fldposicion) as ultimaposicion from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + ';';
  }
  public String consultaSQLCampoPorPosicion(int pPosicionCampo, int pIdGrupoInformacion)
  {
    return "select " + ap.conformarBloqueCamposTablaCampo() + " from " + "CAMPO" + " where " + "fldposicion" + " = " + pPosicionCampo + " and " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + ';';
  }
  public String consultaSQLActualizarCampoIncrementar(int pIdCampo)
  {
    return "update CAMPO set fldposicion = fldposicion+1 where fldidcampo = " + pIdCampo;
  }
  public String consultaSQLActualizarCampoDecrementar(int pIdCampo)
  {
    return "update CAMPO set fldposicion = fldposicion-1 where fldidcampo = " + pIdCampo;
  }
  public String consultaSQLValoresAplicacionRelacionada(String pNombreGrupoInformacion, String pNombreCampo, String pCampoLlavePrimaria)
  {
    String consultaSQLValoresPlantilla_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValoresPlantilla_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValoresPlantilla_local;
    }
    if (pCampoLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValoresPlantilla_local;
    }
    try
    {
      consultaSQLValoresPlantilla_local = "select " + pNombreCampo + ',' + pCampoLlavePrimaria + " from " + pNombreGrupoInformacion + " order by " + pNombreCampo + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLValoresPlantilla_local;
  }
  public String consultaSQLContenidoPlantilla(String pNombreGrupoInformacion, String pCampoLlavePrimaria, int pValorLlavePrimaria, String pCampoContenido)
  {
    String consultaSQLContenidoPlantilla_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLContenidoPlantilla_local;
    }
    if (pCampoLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLContenidoPlantilla_local;
    }
    if (pCampoContenido == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLContenidoPlantilla_local;
    }
    try
    {
      consultaSQLContenidoPlantilla_local = "select " + pCampoContenido + " from " + pNombreGrupoInformacion + " where " + pCampoLlavePrimaria + " = " + pValorLlavePrimaria + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLContenidoPlantilla_local;
  }
  public String consultaSQLUltimoValorAlfanumericoRegistrado(Campo pCampo, String pNombreGrupoInformacionReferencia, int pValorLlavePrimariaGrupo, String pNombreLlavePrimaria, int pValorLlavePrimaria)
  {
    String consultaSQLUltimoValorAlfanumericoRegistrado_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLUltimoValorAlfanumericoRegistrado_local;
    }
    if (pNombreGrupoInformacionReferencia == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLUltimoValorAlfanumericoRegistrado_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLUltimoValorAlfanumericoRegistrado_local;
    }
    try
    {
      consultaSQLUltimoValorAlfanumericoRegistrado_local = "select " + pCampo.getNombreCampo() + " from " + pNombreGrupoInformacionReferencia + " where " + ap.conformarNombreCampoLlavePrimaria(pNombreGrupoInformacionReferencia) + " = " + pValorLlavePrimariaGrupo;
      if (pValorLlavePrimaria != -1)
      {
        consultaSQLUltimoValorAlfanumericoRegistrado_local = mc.concatenarCadena(consultaSQLUltimoValorAlfanumericoRegistrado_local, " and " + pNombreLlavePrimaria + " = " + pValorLlavePrimaria);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLUltimoValorAlfanumericoRegistrado_local;
  }
  public String consultaSQLVerificaDisponibilidadValorNumerico(Campo pCampo, int pValorIdentificador, int pValorLlavePrimaria)
  {
    String consultaSQLVerificaDisponibilidadValorNumerico_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificaDisponibilidadValorNumerico_local;
    }
    try
    {
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      }
      else
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLVerificaDisponibilidadValorNumerico_local = "select " + pCampo.getNombreCampo() + " from " + nombreGrupoInformacion_local + " where " + pCampo.getNombreCampo() + " = " + pValorIdentificador;
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        consultaSQLVerificaDisponibilidadValorNumerico_local = mc.concatenarCadena(consultaSQLVerificaDisponibilidadValorNumerico_local, " and " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()) + " = " + String.valueOf(pValorLlavePrimaria));
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLVerificaDisponibilidadValorNumerico_local;
  }
  public String consultaSQLVerificaDisponibilidadValorAlfanumerico(Campo pCampo, String pValorIdentificador, int pValorLlavePrimaria)
  {
    String consultaSQLVerificaDisponibilidadValorAlfanumerico_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificaDisponibilidadValorAlfanumerico_local;
    }
    if (pValorIdentificador == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLVerificaDisponibilidadValorAlfanumerico_local;
    }
    try
    {
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      }
      else
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLVerificaDisponibilidadValorAlfanumerico_local = "select " + pCampo.getNombreCampo() + " from " + nombreGrupoInformacion_local + " where " + pCampo.getNombreCampo() + " = " + mc.colocarEntreComillas(pValorIdentificador);
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        consultaSQLVerificaDisponibilidadValorAlfanumerico_local = mc.concatenarCadena(consultaSQLVerificaDisponibilidadValorAlfanumerico_local, " and " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()) + " = " + String.valueOf(pValorLlavePrimaria));
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLVerificaDisponibilidadValorAlfanumerico_local;
  }
  public String consultaSQLCantidadRegistrosTabla(String pNombreTabla, String pNombreCampo)
  {
    String consultaSQLCantidadRegistrosTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLCantidadRegistrosTabla_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLCantidadRegistrosTabla_local;
    }
    try
    {
      consultaSQLCantidadRegistrosTabla_local = "select count" + mc.colocarEntreParentesis(pNombreCampo) + " as " + "cantidadregistrostabla" + " from " + pNombreTabla + ';';
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLCantidadRegistrosTabla_local;
  }
  public String consultaSQLObtenerNumeroVersion()
  {
    return "select fldnumeroversion from CONFIGURACION;";
  }
  public String consultaSQLObtenerFechaVersion()
  {
    return "select fldfechaversion from CONFIGURACION;";
  }
  public String consultaSQLValorEnlace(Campo pCampo, String pNombreLlavePrimaria, int pValorEnlace)
  {
    String consultaSQLValorEnlace_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValorEnlace_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValorEnlace_local;
    }
    try
    {
      consultaSQLValorEnlace_local = "select " + pCampo.getNombreCampo() + " from " + pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion() + " where " + pNombreLlavePrimaria + " = " + pValorEnlace;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLValorEnlace_local;
  }
  public String consultaSQLTotalizarCampo(Campo pCampo, String pValorLlavePrimaria, String pNombreCampoReferencia, int pValorLlavePrincipal, boolean pEsModificacion, boolean pEsValorTexto)
  {
    String consultaSQLActualizarValorCampo_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorCampo_local;
    }
    if (pNombreCampoReferencia == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLActualizarValorCampo_local;
    }
    try
    {
      consultaSQLActualizarValorCampo_local = "select  sum( " + pCampo.getNombreCampo() + ')' + " as " + "totalcampo" + " from " + pCampo.getGrupoInformacion().getNombreGrupoInformacion() + " where " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()) + " = " + pValorLlavePrincipal;
      if (!mc.sonCadenasIguales(pValorLlavePrimaria, String.valueOf(-1)) && pEsModificacion)
      {
        consultaSQLActualizarValorCampo_local = mc.concatenarCadena(consultaSQLActualizarValorCampo_local, " and " + pNombreCampoReferencia + " <= ");
        if (pEsValorTexto)
        {
          consultaSQLActualizarValorCampo_local = mc.concatenarCadena(consultaSQLActualizarValorCampo_local, mc.colocarEntreComillas(pValorLlavePrimaria));
        }
        else
        {
          consultaSQLActualizarValorCampo_local = mc.concatenarCadena(consultaSQLActualizarValorCampo_local, pValorLlavePrimaria);
        }
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLActualizarValorCampo_local;
  }
  public String consultaSQLCamposGrupoInformacion(int pIdGrupoInformacion, boolean pSoloNumericos, boolean pSoloFechas)
  {
    String consultaCamposGrupoInformacion_local = "select " + ap.conformarBloqueCamposTablaCampo() + ',' + "flddescripciongrupoinformacion" + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("");
    if (pSoloNumericos)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and " + mc.colocarEntreParentesis("fldtipodato = " + mc.colocarEntreComillas("E") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("R") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("W") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("BB") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("GG")));
    }
    if (pSoloFechas)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and fldtipodato = " + mc.colocarEntreComillas("F"));
    }
    consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " order by fldposicion");
    return consultaCamposGrupoInformacion_local;
  }
  public String consultaSQLCamposGruposInformacionNoMultiplesAplicacion(int pIdAplicacion, boolean pSoloNumericos, boolean pSoloFechas)
  {
    String consultaCamposGrupoInformacion_local = "select " + ap.conformarBloqueCamposTablaCampo() + ',' + "flddescripciongrupoinformacion" + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " and " + "fldgrupoinformacionmultiple" + " <> " + "true";
    if (pSoloNumericos)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and " + mc.colocarEntreParentesis("fldtipodato = " + mc.colocarEntreComillas("E") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("R") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("W") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("BB") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("GG")));
    }
    if (pSoloFechas)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and fldtipodato = " + mc.colocarEntreComillas("F"));
    }
    consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " order by fldidgrupoinformacion");
    return consultaCamposGrupoInformacion_local;
  }
  public String consultaSQLCamposGruposInformacionMultiplesAplicacion(int pIdAplicacion, boolean pSoloNumericos, boolean pSoloFechas)
  {
    String consultaCamposGrupoInformacion_local = "select " + ap.conformarBloqueCamposTablaCampo() + ',' + "flddescripciongrupoinformacion" + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " and " + "fldgrupoinformacionmultiple" + " = " + "true";
    if (pSoloNumericos)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and " + mc.colocarEntreParentesis("fldtipodato = " + mc.colocarEntreComillas("E") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("R") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("W") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("BB") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("GG")));
    }
    if (pSoloFechas)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and fldtipodato = " + mc.colocarEntreComillas("F"));
    }
    consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " order by fldidgrupoinformacion");
    return consultaCamposGrupoInformacion_local;
  }
  public String consultaSQLCamposAplicacion(int pIdAplicacion, boolean pSoloNumericos, boolean pSoloFechas, boolean pSoloHoras)
  {
    String consultaCamposGrupoInformacion_local = "select " + ap.conformarBloqueCamposTablaCampo() + ',' + "flddescripciongrupoinformacion" + " from " + "CAMPO" + ',' + "GRUPOINFORMACION" + " where " + "CAMPO" + '.' + "fldidgrupoinformacion" + " = " + "GRUPOINFORMACION" + '.' + "fldidgrupoinformacion" + " and " + "fldidaplicacion" + " = " + pIdAplicacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("");
    if (pSoloNumericos)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and " + mc.colocarEntreParentesis("fldtipodato = " + mc.colocarEntreComillas("E") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("R") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("W") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("BB") + " or " + "fldtipodato" + " = " + mc.colocarEntreComillas("GG")));
    }
    else if (pSoloFechas)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and fldtipodato = " + mc.colocarEntreComillas("F"));
    }
    else if (pSoloHoras)
    {
      consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " and fldtipodato = " + mc.colocarEntreComillas("H"));
    }
    consultaCamposGrupoInformacion_local = mc.concatenarCadena(consultaCamposGrupoInformacion_local, " order by fldidgrupoinformacion");
    return consultaCamposGrupoInformacion_local;
  }
  public String consultaSQLVerificarEsCampoValor(int pIdCampo)
  {
    String verificarEsCampoValor_local = "";
    try
    {
      verificarEsCampoValor_local = "select fldidcampo from CAMPO where fldidcampovalor = " + pIdCampo + " and " + "fldcampocalculado" + " <> " + "1";
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarEsCampoValor_local;
  }
  public String consultaSQLVerificarEsCampoOrigen(int pIdCampo)
  {
    String verificarEsCampoValor_local = "";
    try
    {
      verificarEsCampoValor_local = "select fldidcampo from CAMPO where " + mc.colocarEntreParentesis("fldidcampoorigenuno = " + pIdCampo + " or " + "fldidcampoorigendos" + " = " + pIdCampo + " and " + "fldcampocalculado" + " <> " + "1");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return verificarEsCampoValor_local;
  }
  public String consultaSQLNumeroCamposExportar(int pIdGrupoInformacion)
  {
    String numeroCampos_local = "select count" + mc.colocarEntreParentesis("fldidcampo") + " as " + "fldnumerocampos" + " from " + "CAMPO" + " where " + "fldidgrupoinformacion" + " = " + pIdGrupoInformacion + " and " + "fldllaveprimaria" + " = false and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " and " + "fldtipodato" + " <> " + mc.colocarEntreComillas("DD") + " and " + "fldtipodato" + " <> " + mc.colocarEntreComillas("J");
    return numeroCampos_local;
  }
  public String consultaSQLListaTiposUsuario()
  {
    return "select fldidtipousuario,fldnombretipousuario from TIPOUSUARIO order by fldnombretipousuario";
  }
  public String consultaSQLTiposUsuario()
  {
    return "select fldidtipousuario,fldnombretipousuario,fldpermitirutilizarmenu,fldnivelacceso from TIPOUSUARIO order by fldidtipousuario";
  }
  public String consultaSQLPermisosTipoUsuario(int pIdTipoUsuario)
  {
    return "select fldnivelacceso from TIPOUSUARIO where fldidtipousuario = " + pIdTipoUsuario;
  }
  public String consultaSQLPermitirUtilizarMenuConfiguracion(int pIdTipoUsuario)
  {
    return "select fldpermitirutilizarmenu from TIPOUSUARIO where fldidtipousuario = " + pIdTipoUsuario;
  }
  public String consultaSQLTotalGruposInformacionAplicacion(int pIdAplicacion)
  {
    return "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldidaplicacion" + " = " + pIdAplicacion + " order by " + "fldidgrupoinformacion";
  }
  public String consultaSQLIdValorCampoEnlazado(Campo pCampo, String pValorEnlace, boolean pEsValorNumerico)
  {
    String consultaSQLValorEnlace_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValorEnlace_local;
    }
    if (pValorEnlace == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValorEnlace_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLValorEnlace_local = "select " + ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local) + " from " + nombreGrupoInformacion_local + " where " + pCampo.getNombreCampo() + " = ";
      if (pEsValorNumerico)
      {
        consultaSQLValorEnlace_local = mc.concatenarCadena(consultaSQLValorEnlace_local, pValorEnlace);
      }
      else
      {
        consultaSQLValorEnlace_local = mc.concatenarCadena(consultaSQLValorEnlace_local, mc.colocarEntreComillas(pValorEnlace));
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLValorEnlace_local;
  }
  public String consultaSQLIdRegistroValorCampo(Campo pCampo, String pValorCampo, boolean pEsTipoNumerico)
  {
    String consultaSQLIdRegistroValorCampo_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdRegistroValorCampo_local;
    }
    if (pValorCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdRegistroValorCampo_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLIdRegistroValorCampo_local = "select " + ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local) + " from " + nombreGrupoInformacion_local + " where " + pCampo.getNombreCampo() + " = ";
      if (pEsTipoNumerico)
      {
        consultaSQLIdRegistroValorCampo_local = mc.concatenarCadena(consultaSQLIdRegistroValorCampo_local, pValorCampo);
      }
      else
      {
        consultaSQLIdRegistroValorCampo_local = mc.concatenarCadena(consultaSQLIdRegistroValorCampo_local, mc.colocarEntreComillas(pValorCampo));
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLIdRegistroValorCampo_local;
  }
  public String conformarConsultaSQLValoresCampoGrupoMultiple(String pNombreGrupoInformacionPrincipal, String pNombreLlavePrimaria, String pNombreGrupoInformacion, String pNombreCampo, int pValorLlavePrimaria)
  {
    String consultaValoresCampo_local = "";
    if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    try
    {
      consultaValoresCampo_local = "select " + pNombreGrupoInformacion + '.' + pNombreCampo + " from " + pNombreGrupoInformacion + ',' + pNombreGrupoInformacionPrincipal + " where " + pNombreGrupoInformacionPrincipal + '.' + pNombreLlavePrimaria + " = " + pNombreGrupoInformacion + '.' + pNombreLlavePrimaria + " and " + pNombreGrupoInformacion + '.' + pNombreLlavePrimaria + " = " + pValorLlavePrimaria;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaValoresCampo_local;
  }
  public String conformarConsultaSQLUltimoValorCampoGrupoMultiple(String pNombreGrupoInformacion, String pNombreLlavePrimariaGrupoInformacion, String pNombreCampo, int pValorLlavePrimaria)
  {
    String consultaValoresCampo_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreLlavePrimariaGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    try
    {
      consultaValoresCampo_local = "select " + pNombreCampo + " from " + pNombreGrupoInformacion + " where " + pNombreLlavePrimariaGrupoInformacion + " = " + pValorLlavePrimaria;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaValoresCampo_local;
  }
  public String conformarConsultaSQLSubtotalCampo(String pNombreGrupoInformacionPrincipal, String pNombreLlavePrimaria, String pNombreGrupoInformacion, String pNombreCampo, int pValorLlavePrimaria)
  {
    String consultaValoresCampo_local = "";
    if (pNombreGrupoInformacionPrincipal == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreLlavePrimaria == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaValoresCampo_local;
    }
    try
    {
      consultaValoresCampo_local = "select  sum( " + pNombreGrupoInformacion + '.' + pNombreCampo + ')' + " as " + "totalcampo" + " from " + pNombreGrupoInformacion + ',' + pNombreGrupoInformacionPrincipal + " where " + pNombreGrupoInformacionPrincipal + '.' + pNombreLlavePrimaria + " = " + pNombreGrupoInformacion + '.' + pNombreLlavePrimaria + " and " + pNombreGrupoInformacion + '.' + pNombreLlavePrimaria + " = " + pValorLlavePrimaria;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaValoresCampo_local;
  }
  public String consultaSQLInsertarCampo(Campo pCampo)
  {
    String consultaSQL_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "insert into CAMPO" + mc.colocarEntreParentesis(ap.conformarBloqueCamposTablaCampoInsercion()) + " values( ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getIdCampo()) + ',' + String.valueOf(pCampo.getGrupoInformacion().getIdGrupoInformacion()) + ',' + mc.colocarEntreComillas(pCampo.getNombreCampo()) + ',' + mc.colocarEntreComillas(pCampo.getEtiquetaCampo()) + ',' + mc.colocarEntreComillas(pCampo.getSeudonimo()) + ',' + mc.colocarEntreComillas(pCampo.getFormatoCampo().getTipoDato()) + ',' + String.valueOf(pCampo.getPlantillaCampo().esTienePlantilla()) + ',');
      if (pCampo.getPlantillaCampo().getAplicacionPlantilla() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getPlantillaCampo().getAplicacionPlantilla().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(0) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getFormatoCampo().esValorUnico()) + ',' + String.valueOf(pCampo.getFormatoCampo().getLongitudCampo()) + ',' + String.valueOf(pCampo.getFormatoCampo().getNumeroDecimales()) + ',' + String.valueOf(pCampo.getAnchoColumna()) + ',' + String.valueOf(pCampo.getRestriccionCampo().esLlavePrimaria()) + ',' + String.valueOf(pCampo.getRestriccionCampo().esLlaveForanea()) + ',' + String.valueOf(pCampo.esObligatorio()) + ',' + String.valueOf(pCampo.esVisibleUsuarioPrincipal()) + ',' + String.valueOf(pCampo.esVisibleUsuarioSecundario()) + ',' + String.valueOf(pCampo.esModificable()) + ',' + String.valueOf(pCampo.getPosicion()) + ',');
      if (pCampo.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getHabilitadoPor().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(0) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.esBorrarValorNoHabilitado()) + ',');
      if (pCampo.getListaDependiente() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getListaDependiente().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(0) + ',');
      }
      if (pCampo.esCampoEnlazado())
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getEnlazado().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(0) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getFiltradoRegistrosEnlazados()) + ',');
      if (pCampo.getEnlaceCampo().getCampoOrigenFiltrado() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getCampoOrigenFiltrado().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      if (pCampo.getEnlaceCampo().getCampoDestinoFiltrado() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getCampoDestinoFiltrado().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, mc.colocarEntreComillas(pCampo.getEnlaceCampo().getValorFiltrado()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getDependienteEnlazado()) + ',' + String.valueOf(pCampo.getEnlaceCampo().esOpcionDesconocido()) + ',');
      if (pCampo.getEnlaceCampo().getCampoEnlaceDepende() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getCampoEnlaceDepende().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      if (pCampo.getEnlaceCampo().getCampoOrigenEnlace() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getEnlaceCampo().getCampoOrigenEnlace().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getTipoHabilitacion()) + ',' + String.valueOf(pCampo.getCalculoCampo().getCampoCalculado()) + ',' + String.valueOf(pCampo.getCalculoCampo().esRecalculable()) + ',');
      if (pCampo.getCalculoCampo().getCampoValor() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getCalculoCampo().getCampoValor().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      if (pCampo.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getCalculoCampo().getCampoOrigenUno().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, mc.colocarEntreComillas(pCampo.getCalculoCampo().getFormatoCampoOrigenUno()) + ',');
      if (pCampo.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pCampo.getCalculoCampo().getCampoOrigenDos().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, mc.colocarEntreComillas(pCampo.getCalculoCampo().getFormatoCampoOrigenDos()) + ',' + mc.colocarEntreComillas(pCampo.getCalculoCampo().getFormatoCampoCalculado()) + ',' + String.valueOf(pCampo.esIncluirOpcionConsulta()) + ',' + String.valueOf(pCampo.esRecargarPantalla()) + ',' + String.valueOf(pCampo.getEstiloCampo().cambiarRenglon()) + ',' + String.valueOf(pCampo.getEstiloCampo().getMargenSuperior()) + ',' + String.valueOf(pCampo.getEstiloCampo().getAnchoEtiqueta()) + ',' + String.valueOf(pCampo.getEstiloCampo().getAnchoControl()) + ',' + String.valueOf(pCampo.getEstiloCampo().getCantidadRenglones()) + ',' + String.valueOf(pCampo.getEstiloCampo().getMargenIzquierdaEtiqueta()) + ',' + String.valueOf(pCampo.getEstiloCampo().getMargenIzquierdaControl()) + ',' + String.valueOf(pCampo.esImagen()) + ',' + String.valueOf(pCampo.getAltoImagenPantallaPresentacion()) + ',' + String.valueOf(pCampo.getAltoImagenPantallaEdicion()) + ',' + String.valueOf(pCampo.ocultarEtiquetaControlExaminar()) + ',' + String.valueOf(pCampo.ocultarEtiquetaControlVer()) + ')');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLInsertarGrupoInformacion(GrupoInformacion pGrupoInformacion)
  {
    String consultaSQL_local = "";
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "insert into GRUPOINFORMACION" + mc.colocarEntreParentesis(ap.conformarBloqueCamposTablaGrupoInformacionInsercion()) + " values( ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pGrupoInformacion.getIdGrupoInformacion()) + ',' + String.valueOf(pGrupoInformacion.getAplicacion().getIdAplicacion()) + ',' + mc.colocarEntreComillas(pGrupoInformacion.getNombreGrupoInformacion()) + ',' + mc.colocarEntreComillas(pGrupoInformacion.getDescripcionGrupoInformacion()) + ',' + String.valueOf(pGrupoInformacion.esGrupoInformacionPrincipal()) + ',' + String.valueOf(pGrupoInformacion.esGrupoInformacionMultiple()) + ',' + String.valueOf(pGrupoInformacion.getPosicion()) + ',' + String.valueOf(pGrupoInformacion.esMostrarDetalle()) + ',' + String.valueOf(pGrupoInformacion.getMargenSuperior()) + ')');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLInsertarAplicacion(Aplicacion pAplicacion)
  {
    String consultaSQL_local = "";
    if (pAplicacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "insert into APLICACION" + mc.colocarEntreParentesis(ap.conformarBloqueCamposTablaAplicacionInsercion()) + " values( ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pAplicacion.getIdAplicacion()) + ',' + mc.colocarEntreComillas(pAplicacion.getNombreAplicacion()) + ',' + mc.colocarEntreComillas(pAplicacion.getTituloAplicacion()) + ',' + String.valueOf(pAplicacion.esTabla()) + ',');
      if (pAplicacion.getAplicacionConsulta() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pAplicacion.getAplicacionConsulta().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      if (pAplicacion.getAplicacionReporte() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pAplicacion.getAplicacionReporte().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pAplicacion.esActualizarInformacionEnlazada()) + ',' + mc.colocarEntreComillas(pAplicacion.getAplicacionesActualizar()) + ',' + String.valueOf(pAplicacion.esAdvertenciaEjecucion()) + ',' + String.valueOf(pAplicacion.esPermitirConsultaGeneral()) + ',' + String.valueOf(pAplicacion.getTamanoMaximoArchivos()) + ',' + String.valueOf(pAplicacion.esOculta()) + ',' + String.valueOf(pAplicacion.esHacerDobleCalculo()) + ',' + String.valueOf(pAplicacion.getNumeroRegistrosPagina()) + ',' + mc.colocarEntreComillas(pAplicacion.getTipoEventosUsuario()) + ',' + mc.colocarEntreComillas(pAplicacion.getEventosAcciones()) + ')');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLInsertarTabla(Tabla pTabla)
  {
    String consultaSQL_local = "";
    if (pTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "insert into TABLA" + mc.colocarEntreParentesis(ap.conformarBloqueCamposTablaTabla()) + " values( ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pTabla.getIdTabla()) + ',' + mc.colocarEntreComillas(pTabla.getNombreTabla()) + ',' + mc.colocarEntreComillas(pTabla.getDescripcionTabla()) + ')');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLInsertarTipoUsuario(TipoUsuario pTipoUsuario)
  {
    String consultaSQL_local = "";
    if (pTipoUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "insert into TIPOUSUARIO" + mc.colocarEntreParentesis(ap.conformarBloqueCamposTablaTipoUsuarioInsercion()) + " values( ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pTipoUsuario.getIdTipoUsuario()) + ',' + mc.colocarEntreComillas(pTipoUsuario.getNombreTipoUsuario()) + ',' + String.valueOf(pTipoUsuario.esPermitirUtilizarMenu()) + ',' + mc.colocarEntreComillas(pTipoUsuario.getNivelAcceso()) + ')');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLInsertarUsuario(Usuario pUsuario)
  {
    String consultaSQL_local = "";
    if (pUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "insert into USUARIO" + mc.colocarEntreParentesis(ap.conformarBloqueCamposTablaUsuarioInsercion()) + " values( ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pUsuario.getIdUsuario()) + ',' + mc.colocarEntreComillas(pUsuario.getNombreUsuario()) + ',' + mc.colocarEntreComillas(pUsuario.getContrasena()) + ',' + mc.colocarEntreComillas(pUsuario.getNombreCompletoUsuario()) + ',' + mc.colocarEntreComillas(pUsuario.getTipoLicencia()) + ',' + mc.colocarEntreComillas(pUsuario.getFechaVencimiento().toString()) + ',' + String.valueOf(pUsuario.getTipoUsuario().getIdTipoUsuario()) + ',' + String.valueOf(pUsuario.getDiasVigenciaContrasena()) + ',' + String.valueOf(pUsuario.getContrasenasFallidasPermitidas()) + ',');
      if (pUsuario.getFechaUltimaContrasenaFallida() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, mc.colocarEntreComillas(pUsuario.getFechaUltimaContrasenaFallida().toString()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "null,");
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(pUsuario.getCantidadContrasenasFallidas()) + ',' + String.valueOf(pUsuario.getTiempoSesion()) + ',' + String.valueOf(pUsuario.getTipoBloqueo()) + ',' + '1' + ')');
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarAplicacion(Aplicacion pAplicacion)
  {
    String consultaSQL_local = "";
    if (pAplicacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "update APLICACION set ";
      if (!mc.esCadenaVacia(pAplicacion.getTituloAplicacion()))
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldtituloaplicacion = " + mc.colocarEntreComillas(pAplicacion.getTituloAplicacion()) + ',');
      }
      if (pAplicacion.getAplicacionConsulta() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionconsulta = " + String.valueOf(pAplicacion.getAplicacionConsulta().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionconsulta = 0,");
      }
      if (pAplicacion.getAplicacionReporte() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionreporte = " + String.valueOf(pAplicacion.getAplicacionReporte().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionreporte = 0,");
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldactualizarinformacionenlazada = " + String.valueOf(pAplicacion.esActualizarInformacionEnlazada()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldaplicacionesactualizar = " + mc.colocarEntreComillas(pAplicacion.getAplicacionesActualizar()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldadvertenciaejecucion = " + String.valueOf(pAplicacion.esAdvertenciaEjecucion()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldpermitirconsultageneral = " + String.valueOf(pAplicacion.esPermitirConsultaGeneral()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldtamanomaximoarchivos = " + String.valueOf(pAplicacion.getTamanoMaximoArchivos()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldhacerdoblecalculo = " + String.valueOf(pAplicacion.esHacerDobleCalculo()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldnumeroregistrospagina = " + String.valueOf(pAplicacion.getNumeroRegistrosPagina()));
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, String.valueOf(','));
      if (pAplicacion.getAplicacionImpresionMasiva() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionimpresionmasiva = " + String.valueOf(pAplicacion.getAplicacionImpresionMasiva().getIdAplicacion()));
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionimpresionmasiva = 0");
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, " where fldidaplicacion = " + pAplicacion.getIdAplicacion());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarEventosAccionesAplicacion(Aplicacion pAplicacion)
  {
    String consultaSQL_local = "";
    if (pAplicacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "update APLICACION set ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldtipoeventosusuario = " + mc.colocarEntreComillas(pAplicacion.getTipoEventosUsuario()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldeventosacciones = " + mc.colocarEntreComillas(pAplicacion.getEventosAcciones()));
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, " where fldidaplicacion = " + pAplicacion.getIdAplicacion());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarTabla(Tabla pTabla)
  {
    String consultaSQL_local = "";
    if (pTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "update TABLA set ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "flddescripciontabla = " + mc.colocarEntreComillas(pTabla.getDescripcionTabla()));
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, " where fldidtabla = " + pTabla.getIdTabla());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarGrupoInformacion(GrupoInformacion pGrupoInformacion)
  {
    String consultaSQL_local = "";
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "update GRUPOINFORMACION set ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "flddescripciongrupoinformacion = " + mc.colocarEntreComillas(pGrupoInformacion.getDescripcionGrupoInformacion()) + ',' + "fldmostrardetalle" + " = " + pGrupoInformacion.esMostrarDetalle() + ',' + "fldmargensuperiorgrupoinformacion" + " = " + pGrupoInformacion.getMargenSuperior());
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, " where fldidgrupoinformacion = " + pGrupoInformacion.getIdGrupoInformacion());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarCampo(Campo pCampo)
  {
    String consultaSQL_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "update CAMPO set ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldetiquetacampo = " + mc.colocarEntreComillas(pCampo.getEtiquetaCampo()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldseudonimo = " + mc.colocarEntreComillas(pCampo.getSeudonimo()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldtieneplantilla = " + String.valueOf(pCampo.getPlantillaCampo().esTienePlantilla()) + ',');
      if (pCampo.getPlantillaCampo().getAplicacionPlantilla() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionplantilla = " + String.valueOf(pCampo.getPlantillaCampo().getAplicacionPlantilla().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidaplicacionplantilla = " + String.valueOf(0) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldvalorunico = " + String.valueOf(pCampo.getFormatoCampo().esValorUnico()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldanchocolumna = " + String.valueOf(pCampo.getAnchoColumna()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldobligatorio = " + String.valueOf(pCampo.esObligatorio()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldvisibleusuarioprincipal = " + String.valueOf(pCampo.esVisibleUsuarioPrincipal()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldvisibleusuariosecundario = " + String.valueOf(pCampo.esVisibleUsuarioSecundario()) + ',');
      if (pCampo.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldhabilitadopor = " + String.valueOf(pCampo.getHabilitadoPor().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldhabilitadopor = " + String.valueOf(0) + ',');
      }
      if (pCampo.getListaDependiente() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldlistadependiente = " + String.valueOf(pCampo.getListaDependiente().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldlistadependiente = " + String.valueOf(0) + ',');
      }
      if (pCampo.esCampoEnlazado())
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldenlazado = " + String.valueOf(pCampo.getEnlaceCampo().getEnlazado().getIdAplicacion()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldenlazado = " + String.valueOf(0) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "flddependienteenlazado = " + String.valueOf(pCampo.getEnlaceCampo().getDependienteEnlazado()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldopciondesconocido = " + String.valueOf(pCampo.getEnlaceCampo().esOpcionDesconocido()) + ',');
      if (pCampo.getEnlaceCampo().getCampoEnlaceDepende() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampoenlacedepende = " + String.valueOf(pCampo.getEnlaceCampo().getCampoEnlaceDepende().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampoenlacedepende = " + String.valueOf(-1) + ',');
      }
      if (pCampo.getEnlaceCampo().getCampoOrigenEnlace() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampoorigenenlace = " + String.valueOf(pCampo.getEnlaceCampo().getCampoOrigenEnlace().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampoorigenenlace = " + String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldtipohabilitacion = " + String.valueOf(pCampo.getTipoHabilitacion()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampocalculado = " + String.valueOf(pCampo.getCalculoCampo().getCampoCalculado()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldesrecalculable = " + String.valueOf(pCampo.getCalculoCampo().esRecalculable()) + ',');
      if (pCampo.getCalculoCampo().getCampoValor() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidcampovalor = " + String.valueOf(pCampo.getCalculoCampo().getCampoValor().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidcampovalor = " + String.valueOf(-1) + ',');
      }
      if (pCampo.getCalculoCampo().getCampoOrigenUno() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidcampoorigenuno = " + String.valueOf(pCampo.getCalculoCampo().getCampoOrigenUno().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidcampoorigenuno = " + String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldformatocampoorigenuno = " + mc.colocarEntreComillas(pCampo.getCalculoCampo().getFormatoCampoOrigenUno()) + ',');
      if (pCampo.getCalculoCampo().getCampoOrigenDos() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidcampoorigendos = " + String.valueOf(pCampo.getCalculoCampo().getCampoOrigenDos().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldidcampoorigendos = " + String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldformatocampoorigendos = " + mc.colocarEntreComillas(pCampo.getCalculoCampo().getFormatoCampoOrigenDos()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldformatocampocalculado = " + mc.colocarEntreComillas(pCampo.getCalculoCampo().getFormatoCampoCalculado()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldincluiropcionconsulta = " + String.valueOf(pCampo.esIncluirOpcionConsulta()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldborrarvalornohabilitado = " + String.valueOf(pCampo.esBorrarValorNoHabilitado()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldfiltradoregistrosenlazados = " + String.valueOf(pCampo.getEnlaceCampo().getFiltradoRegistrosEnlazados()) + ',');
      if (pCampo.getEnlaceCampo().getCampoOrigenFiltrado() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampoorigenfiltrado = " + String.valueOf(pCampo.getEnlaceCampo().getCampoOrigenFiltrado().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampoorigenfiltrado = " + String.valueOf(-1) + ',');
      }
      if (pCampo.getEnlaceCampo().getCampoDestinoFiltrado() != ConstantesGeneral.VALOR_NULO)
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampodestinofiltrado = " + String.valueOf(pCampo.getEnlaceCampo().getCampoDestinoFiltrado().getIdCampo()) + ',');
      }
      else
      {
        consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcampodestinofiltrado = " + String.valueOf(-1) + ',');
      }
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldvalorfiltrado = " + mc.colocarEntreComillas(pCampo.getEnlaceCampo().getValorFiltrado()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldrecargarpantalla = " + String.valueOf(pCampo.esRecargarPantalla()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcambiarrenglon = " + String.valueOf(pCampo.getEstiloCampo().cambiarRenglon()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldmargensuperiorcampo = " + String.valueOf(pCampo.getEstiloCampo().getMargenSuperior()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldanchoetiquetacampo = " + String.valueOf(pCampo.getEstiloCampo().getAnchoEtiqueta()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldanchocontrolcampo = " + String.valueOf(pCampo.getEstiloCampo().getAnchoControl()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldcantidadrenglonescontrolcampo = " + String.valueOf(pCampo.getEstiloCampo().getCantidadRenglones()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldmargenizquierdaetiquetacampo = " + String.valueOf(pCampo.getEstiloCampo().getMargenIzquierdaEtiqueta()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldmargenizquierdacontrolcampo = " + String.valueOf(pCampo.getEstiloCampo().getMargenIzquierdaControl()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldesimagen = " + String.valueOf(pCampo.esImagen()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldaltoimagenpantallapresentacion = " + String.valueOf(pCampo.getAltoImagenPantallaPresentacion()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldaltoimagenpantallaedicion = " + String.valueOf(pCampo.getAltoImagenPantallaEdicion()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldocultaretiquetacontrolexaminar = " + String.valueOf(pCampo.ocultarEtiquetaControlExaminar()) + ',');
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldocultaretiquetacontrolver = " + String.valueOf(pCampo.ocultarEtiquetaControlVer()));
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, " where fldidcampo = " + pCampo.getIdCampo());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarTipoUsuario(TipoUsuario pTipoUsuario)
  {
    String consultaSQL_local = "";
    if (pTipoUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "update TIPOUSUARIO set ";
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, "fldnombretipousuario = " + mc.colocarEntreComillas(pTipoUsuario.getNombreTipoUsuario()) + ',' + "fldpermitirutilizarmenu" + " = " + String.valueOf(pTipoUsuario.esPermitirUtilizarMenu()) + ',' + "fldnivelacceso" + " = " + mc.colocarEntreComillas(pTipoUsuario.getNivelAcceso()));
      consultaSQL_local = mc.concatenarCadena(consultaSQL_local, " where fldidtipousuario = " + pTipoUsuario.getIdTipoUsuario());
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLModificarUsuario(Usuario pUsuario)
  {
    String consultaSQL_local = "";
    StringBuffer stringBuffer_local = null;
    if (pUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      stringBuffer_local = new StringBuffer();
      stringBuffer_local.append("update ");
      stringBuffer_local.append("USUARIO");
      stringBuffer_local.append(" set ");
      stringBuffer_local.append("fldnombreusuario = ");
      stringBuffer_local.append(mc.colocarEntreComillas(pUsuario.getNombreUsuario()) + ',');
      stringBuffer_local.append("fldnombrecompletousuario = ");
      stringBuffer_local.append(mc.colocarEntreComillas(pUsuario.getNombreCompletoUsuario()) + ',');
      stringBuffer_local.append("fldtipolicencia = ");
      stringBuffer_local.append(mc.colocarEntreComillas(pUsuario.getTipoLicencia()) + ',');
      stringBuffer_local.append("fldfechavencimiento = ");
      stringBuffer_local.append(mc.colocarEntreComillas(pUsuario.getFechaVencimiento().toString()) + ',');
      stringBuffer_local.append("fldidtipousuario = ");
      stringBuffer_local.append(String.valueOf(pUsuario.getTipoUsuario().getIdTipoUsuario()) + ',');
      stringBuffer_local.append("flddiasvigenciacontrasena = ");
      stringBuffer_local.append(String.valueOf(pUsuario.getDiasVigenciaContrasena()) + ',');
      stringBuffer_local.append("fldcontrasenasfallidaspermitidas = ");
      stringBuffer_local.append(String.valueOf(pUsuario.getContrasenasFallidasPermitidas()) + ',');
      if (pUsuario.getFechaUltimaContrasenaFallida() != ConstantesGeneral.VALOR_NULO)
      {
        stringBuffer_local.append("fldfechaultimacontrasenafallida = ");
        stringBuffer_local.append(mc.colocarEntreComillas(pUsuario.getFechaUltimaContrasenaFallida().toString()) + ',');
      }
      else
      {
        stringBuffer_local.append("fldfechaultimacontrasenafallida = ");
        stringBuffer_local.append("null,");
      }
      stringBuffer_local.append("fldcantidadcontrasenasfallidas = ");
      stringBuffer_local.append(String.valueOf(pUsuario.getCantidadContrasenasFallidas()) + ',');
      stringBuffer_local.append("fldtiemposesion = ");
      stringBuffer_local.append(String.valueOf(pUsuario.getTiempoSesion()) + ',');
      stringBuffer_local.append("fldtipobloqueo = ");
      stringBuffer_local.append(String.valueOf(pUsuario.getTipoBloqueo()) + ',');
      stringBuffer_local.append("fldcontrasena = ");
      stringBuffer_local.append(mc.colocarEntreComillas(pUsuario.getContrasena()));
      stringBuffer_local.append(',');
      stringBuffer_local.append("fldasignacionadministrador = ");
      stringBuffer_local.append(pUsuario.esAsignacionAdministrador());
      stringBuffer_local.append(" where fldidusuario");
      stringBuffer_local.append(" = " + pUsuario.getIdUsuario());
      consultaSQL_local = stringBuffer_local.toString();
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      stringBuffer_local = null;
    }
    return consultaSQL_local;
  }
  public String consultaSQLCargarArchivoABaseDatos(Campo pCampo, int pValorLlavePrimaria)
  {
    String cargarDocumento_local = "";
    String nombreGrupoInformacion_local = null;
    String nombreCampoNombreArchivo_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return cargarDocumento_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      nombreCampoNombreArchivo_local = ap.complementarNombreCampoNombreArchivo(pCampo.getNombreCampo());
      cargarDocumento_local = "update " + nombreGrupoInformacion_local + " set " + nombreCampoNombreArchivo_local + " = " + '?' + ',' + pCampo.getNombreCampo() + " = " + '?' + " where " + ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local) + " = " + pValorLlavePrimaria;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
      nombreCampoNombreArchivo_local = null;
    }
    return cargarDocumento_local;
  }
  public String consultaSQLObtenerArchivoDeBaseDatos(Campo pCampo, int pValorLlavePrimaria)
  {
    String cargarDocumento_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return cargarDocumento_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      cargarDocumento_local = "select " + pCampo.getNombreCampo() + " from " + nombreGrupoInformacion_local + " where " + ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local) + " = " + pValorLlavePrimaria;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return cargarDocumento_local;
  }
  public String consultaSQLPrincipalCondicionada(Aplicacion pAplicacion, String pCondiciones)
  {
    String consulta_local = "";
    if (pAplicacion == ConstantesGeneral.VALOR_NULO)
    {
      return consulta_local;
    }
    if (pCondiciones == ConstantesGeneral.VALOR_NULO)
    {
      return consulta_local;
    }
    try
    {
      consulta_local = "select " + ap.conformarNombreCampoLlavePrimaria(pAplicacion.getNombreAplicacion()) + " from " + pAplicacion.getNombreAplicacion();
      if (!mc.esCadenaVacia(pCondiciones))
      {
        consulta_local = mc.concatenarCadena(consulta_local, " where " + pCondiciones);
      }
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consulta_local;
  }
  public String consultaSQLNombreArchivo(int pValorLlavePrimaria, Campo pCampoArchivo)
  {
    String consultaNombreArchivo_local = "";
    String nombreGrupo_local = "";
    if (pCampoArchivo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaNombreArchivo_local;
    }
    try
    {
      if (pCampoArchivo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupo_local = pCampoArchivo.getGrupoInformacion().getNombreGrupoInformacion();
      }
      else
      {
        nombreGrupo_local = pCampoArchivo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaNombreArchivo_local = "select " + ap.complementarNombreCampoNombreArchivo(pCampoArchivo.getNombreCampo()) + " from " + nombreGrupo_local + " where " + ap.conformarNombreCampoLlavePrimaria(nombreGrupo_local) + " = " + pValorLlavePrimaria;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupo_local = null;
    }
    return consultaNombreArchivo_local;
  }
  public String consultaSQLAsignarValorConsecutivoInternoCampo(Campo pCampo, int pValorLlavePrimariaGrupoInformacion, int pPosicion)
  {
    String asignarConsecutivoInterno_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return asignarConsecutivoInterno_local;
    }
    try
    {
      asignarConsecutivoInterno_local = "update " + pCampo.getGrupoInformacion().getNombreGrupoInformacion() + " set " + pCampo.getNombreCampo() + " = " + pPosicion + " where " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getNombreGrupoInformacion()) + " = " + pValorLlavePrimariaGrupoInformacion;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return asignarConsecutivoInterno_local;
  }
  public String consultaSQLUltimoValorConsecutivoInterno(Campo pCampo, int pValorLlavePrimariaPrincipal)
  {
    String consultaSQLUltimoValorConsecutivoInterno_local = "";
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLUltimoValorConsecutivoInterno_local;
    }
    try
    {
      consultaSQLUltimoValorConsecutivoInterno_local = "select  max(" + pCampo.getNombreCampo() + ')' + " as " + "ultimoconsecutivointerno" + " from " + pCampo.getGrupoInformacion().getNombreGrupoInformacion() + " where " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()) + " = " + pValorLlavePrimariaPrincipal + " and " + pCampo.getNombreCampo() + " <> " + String.valueOf(0);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLUltimoValorConsecutivoInterno_local;
  }
  public String consultaSQLNombresCamposVisiblesGrupoInformacion(int pIdGrupoInformacion, boolean pVerificarVisibilidad)
  {
    String consultaCamposVisiblesGrupoInformacion_local = "select fldnombrecampo from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " and " + "fldtipodato" + " <> " + mc.colocarEntreComillas("DD") + " and " + "fldtipodato" + " <> " + mc.colocarEntreComillas("J");
    if (pVerificarVisibilidad)
    {
      consultaCamposVisiblesGrupoInformacion_local = mc.concatenarCadena(consultaCamposVisiblesGrupoInformacion_local, " and fldvisibleusuarioprincipal = true");
    }
    consultaCamposVisiblesGrupoInformacion_local = mc.concatenarCadena(consultaCamposVisiblesGrupoInformacion_local, " order by fldposicion");
    return consultaCamposVisiblesGrupoInformacion_local;
  }
  public String consultaSQLNombresCamposVisiblesGrupoInformacionMultiple(int pIdGrupoInformacion)
  {
    return "select fldnombrecampo from CAMPO where fldidgrupoinformacion = " + pIdGrupoInformacion + " and " + "fldetiquetacampo" + " <> " + mc.colocarEntreComillas("") + " and " + "fldtipodato" + " <> " + mc.colocarEntreComillas("DD") + " and " + "fldtipodato" + " <> " + mc.colocarEntreComillas("J") + " and " + "fldvisibleusuariosecundario" + " = " + "true" + " order by " + "fldposicion";
  }
  public String consultaSQLTablaPorNombre(String pNombreTabla)
  {
    String consultaVerificarExistenciaTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaVerificarExistenciaTabla_local;
    }
    try
    {
      consultaVerificarExistenciaTabla_local = "select " + ap.conformarBloqueCamposTablaTabla() + " from " + "TABLA" + " where " + "fldnombretabla" + " = " + mc.colocarEntreComillas(mc.convertirAMayusculas(pNombreTabla));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaVerificarExistenciaTabla_local;
  }
  public String consultaSQLGrupoInformacionMultiplePorNombre(String pNombreGrupoInformacion)
  {
    String consultaVerificarExistenciaGrupoInformacion_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return consultaVerificarExistenciaGrupoInformacion_local;
    }
    try
    {
      consultaVerificarExistenciaGrupoInformacion_local = "select " + ap.conformarBloqueCamposTablaGrupoInformacion() + " from " + "GRUPOINFORMACION" + " where " + "fldgrupoinformacionmultiple" + " = " + "true" + " and " + "fldnombregrupoinformacion" + " = " + mc.colocarEntreComillas(mc.convertirAMayusculas(pNombreGrupoInformacion));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaVerificarExistenciaGrupoInformacion_local;
  }
  public String consultaSQLValoresTablaInterna(String pNombreTabla)
  {
    String consultaSQLValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLValorTabla_local;
    }
    try
    {
      consultaSQLValorTabla_local = "select " + ap.conformarNombreCampoLlavePrimaria(pNombreTabla) + ',' + pNombreTabla + " from " + pNombreTabla;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQLValorTabla_local;
  }
  public String consultaSQLValoresCampo(Campo pCampo, int pValorLlavePrimariaPrincipal, String pNombreCampoOrden)
  {
    String consultaSQLIdValorRegistro_local = "";
    String nombreGrupoInformacion_local = null;
    if (pCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    if (pNombreCampoOrden == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLIdValorRegistro_local;
    }
    try
    {
      nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
      if (!pCampo.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLIdValorRegistro_local = "select " + pCampo.getNombreCampo() + " from " + nombreGrupoInformacion_local + " where " + ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()) + " = " + pValorLlavePrimariaPrincipal + " order by " + pNombreCampoOrden;
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLIdValorRegistro_local;
  }
  public String consultaSQLSumaDesdeHasta(ListaCampo pListaCamposDesdeHasta, int pValorLlavePrimaria)
  {
    String consultaSQLSumaDesdeHasta = "";
    String camposDesdeHasta_local = null;
    String nombreGrupoInformacion_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    if (pListaCamposDesdeHasta == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLSumaDesdeHasta;
    }
    try
    {
      consultaSQLSumaDesdeHasta = "select ";
      camposDesdeHasta_local = "";
      iterador_local = pListaCamposDesdeHasta.iterator();
      while (iterador_local.hasNext())
      {
        campo_local = (Campo)iterador_local.next();
        camposDesdeHasta_local = mc.concatenarCadena(camposDesdeHasta_local, campo_local.getNombreCampo());
        if (iterador_local.hasNext())
        {
          camposDesdeHasta_local = mc.concatenarCadena(camposDesdeHasta_local, String.valueOf('+'));
        }
      }
      nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getNombreGrupoInformacion();
      if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple())
      {
        nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      consultaSQLSumaDesdeHasta = mc.concatenarCadena(consultaSQLSumaDesdeHasta, mc.colocarEntreParentesis(camposDesdeHasta_local) + " as " + "sumadesdehasta" + " from " + nombreGrupoInformacion_local + " where " + ap.conformarNombreCampoLlavePrimaria(nombreGrupoInformacion_local) + " = " + pValorLlavePrimaria);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      campo_local = null;
      iterador_local = null;
      camposDesdeHasta_local = null;
      nombreGrupoInformacion_local = null;
    }
    return consultaSQLSumaDesdeHasta;
  }
  public String conformarConsultaContarRegistros(String pConsultaSQL)
  {
    String consultaContarRegistros_local = "";
    int posicionInicial_local = 0;
    int posicionFinal_local = 0;
    String finalConsulta_local = null;
    if (pConsultaSQL == ConstantesGeneral.VALOR_NULO)
    {
      return consultaContarRegistros_local;
    }
    try
    {
      consultaContarRegistros_local = mc.concatenarCadena("select ", " count(1) as totalregistros ");
      posicionInicial_local = mc.obtenerPosicionSubCadena(pConsultaSQL, " from ");
      if (mc.verificarExistenciaSubCadena(pConsultaSQL, " order by "))
      {
        posicionFinal_local = mc.obtenerPosicionSubCadena(pConsultaSQL, " order by ");
      }
      else
      {
        posicionFinal_local = mc.obtenerLongitudCadena(pConsultaSQL);
      }
      finalConsulta_local = mc.obtenerSubCadena(pConsultaSQL, posicionInicial_local, posicionFinal_local);
      consultaContarRegistros_local = mc.concatenarCadena(consultaContarRegistros_local, finalConsulta_local);
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    finally
    {
      finalConsulta_local = null;
    }
    return consultaContarRegistros_local;
  }
  public String obtenerCadenaSQLInsertarRegistroAplicacion(ListaCampo pListaCampo, String pNombreGrupoInformacion)
  {
    String cadenaInsertarRegistro_local = "";
    if (pListaCampo == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaInsertarRegistro_local;
    }
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaInsertarRegistro_local;
    }
    try
    {
      cadenaInsertarRegistro_local = "insert into ";
      cadenaInsertarRegistro_local = mc.concatenarCadena(cadenaInsertarRegistro_local, pNombreGrupoInformacion);
      cadenaInsertarRegistro_local = mc.concatenarCadena(cadenaInsertarRegistro_local, mc.colocarEntreParentesis(pListaCampo.concatenarCamposGrupoInformacion()));
      cadenaInsertarRegistro_local = mc.concatenarCadena(cadenaInsertarRegistro_local, " values( ");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaInsertarRegistro_local;
  }
  public String obtenerCadenaSQLInsertarRegistroValorTabla(String pNombreTabla)
  {
    String cadenaInsertarRegistroValorTabla_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaInsertarRegistroValorTabla_local;
    }
    try
    {
      cadenaInsertarRegistroValorTabla_local = "insert into ";
      cadenaInsertarRegistroValorTabla_local = mc.concatenarCadena(cadenaInsertarRegistroValorTabla_local, pNombreTabla);
      cadenaInsertarRegistroValorTabla_local = mc.concatenarCadena(cadenaInsertarRegistroValorTabla_local, String.valueOf('('));
      cadenaInsertarRegistroValorTabla_local = mc.concatenarCadena(cadenaInsertarRegistroValorTabla_local, "fldid" + pNombreTabla + String.valueOf(','));
      cadenaInsertarRegistroValorTabla_local = mc.concatenarCadena(cadenaInsertarRegistroValorTabla_local, pNombreTabla);
      cadenaInsertarRegistroValorTabla_local = mc.concatenarCadena(cadenaInsertarRegistroValorTabla_local, String.valueOf(')'));
      cadenaInsertarRegistroValorTabla_local = mc.concatenarCadena(cadenaInsertarRegistroValorTabla_local, " values( ");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaInsertarRegistroValorTabla_local;
  }
  public String obtenerCadenaSQLModificarRegistroAplicacion(String pNombreGrupoInformacion)
  {
    String cadenaModificarRegistro_local = "";
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO)
    {
      return cadenaModificarRegistro_local;
    }
    try
    {
      cadenaModificarRegistro_local = "update ";
      cadenaModificarRegistro_local = mc.concatenarCadena(cadenaModificarRegistro_local, pNombreGrupoInformacion);
      cadenaModificarRegistro_local = mc.concatenarCadena(cadenaModificarRegistro_local, " set ");
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return cadenaModificarRegistro_local;
  }
  public String conformarConsultaSQLModificarTamanoCampo(String pNombreTabla, String pNombreCampo, int pNuevaLongitud)
  {
    String consultaSQL_local = "";
    if (pNombreTabla == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQL_local;
    }
    try
    {
      consultaSQL_local = "alter table " + pNombreTabla + " alter " + pNombreCampo + " type " + "varchar" + mc.colocarEntreParentesis(String.valueOf(pNuevaLongitud));
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLBorrarRegistrosConfiguracion()
  {
    String consultaSQL_local = "";
    try
    {
      consultaSQL_local = " delete  from CONFIGURACION";
    }
    catch (Exception excepcion)
    {
      excepcion.printStackTrace();
    }
    return consultaSQL_local;
  }
  public String consultaSQLFechaCambioContrasena(String pNombreUsuario)
  {
    String consultaSQLFechaCambioContrasena_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLFechaCambioContrasena_local;
    }
    try
    {
      consultaSQLFechaCambioContrasena_local = "select fldfechacambiocontrasena from USUARIO where fldnombreusuario = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion_local)
    {
      excepcion_local.printStackTrace();
    }
    return consultaSQLFechaCambioContrasena_local;
  }
  public String consultaSQLModificarFechaVencimientoUsuario(String pNombreUsuario, Date pFecha)
  {
    String consultaSQLModificarFechaVencimientoUsuario_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLModificarFechaVencimientoUsuario_local;
    }
    if (pFecha == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLModificarFechaVencimientoUsuario_local;
    }
    try
    {
      consultaSQLModificarFechaVencimientoUsuario_local = "update USUARIO set fldfechavencimiento = " + mc.colocarEntreComillas(String.valueOf(pFecha)) + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion_local)
    {
      excepcion_local.printStackTrace();
    }
    return consultaSQLModificarFechaVencimientoUsuario_local;
  }
  public String consultaSQLModificarTipoBloqueoUsuario(String pNombreUsuario, int pTipoBloqueo)
  {
    String consultaSQLModificarTipoBloqueoUsuario_local = "";
    if (pNombreUsuario == ConstantesGeneral.VALOR_NULO)
    {
      return consultaSQLModificarTipoBloqueoUsuario_local;
    }
    try
    {
      consultaSQLModificarTipoBloqueoUsuario_local = "update USUARIO set fldtipobloqueo = " + pTipoBloqueo + " where " + "fldnombreusuario" + " = " + mc.colocarEntreComillas(pNombreUsuario);
    }
    catch (Exception excepcion_local)
    {
      excepcion_local.printStackTrace();
    }
    return consultaSQLModificarTipoBloqueoUsuario_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\consultasBaseDatos\ConsultasAdministrador.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */