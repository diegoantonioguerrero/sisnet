package com.sisnet.servlets;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorArchivos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorEventos;
import com.sisnet.aplicacion.manejadores.ManejadorFechas;
import com.sisnet.aplicacion.manejadores.ManejadorHabilitacionCampos;
import com.sisnet.aplicacion.manejadores.ManejadorImportacion;
import com.sisnet.aplicacion.manejadores.ManejadorPermisoUsuario;
import com.sisnet.aplicacion.manejadores.ManejadorPlantilla;
import com.sisnet.aplicacion.manejadores.ManejadorRequest;
import com.sisnet.aplicacion.manejadores.ManejadorResultadoConsultaSQL;
import com.sisnet.aplicacion.manejadores.ManejadorSesion;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorCampoEnlazado;
import com.sisnet.aplicacion.manejadores.informacionRecalculable.ManejadorInformacionRecalculable;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ConexionPostgres;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.baseDatos.sisnet.usuario.TipoUsuario;
import com.sisnet.baseDatos.sisnet.usuario.Usuario;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.AtributoRequest;
import com.sisnet.objetosManejo.AtributoSesion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaConsulta;
import com.sisnet.objetosManejo.listas.ListaNavegacion;
import com.sisnet.objetosManejo.listas.ListaParametrosRedireccion;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.manejoPaginaJsp.ItemConsulta;
import com.sisnet.objetosManejo.manejoReportes.ReporteExcel;
import com.sisnet.utilidades.CargadorPropiedades;
import com.sisnet.utilidades.Encriptor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class AdministradorServlet
  extends HttpServlet
{
  private static final long serialVersionUID = 5297312981033175210L;
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static CargadorPropiedades cp = CargadorPropiedades.getCargadorPropiedades();
  private static ManejadorFechas mf = ManejadorFechas.getManejadorFechas();
  public void iniciarNavegacion(HttpServletRequest pRequest) {
    int idAplicacionActual_local = 0;
    int valorLlavePrimaria_local = 0;
    int accion_local = 0;
    int numeroPagina_local = 0;
    int numeroError_local = 0;
    int tipoError_local = 0;
    boolean recargarPagina_local = false;
    boolean ejecutarConsulta_local = false;
    boolean configuracion_local = false;
    boolean esDocumento_local = false;
    int plantillaUtilizar_local = 0;
    String estadoActual_local = null;
    ListaNavegacion listaNavegacion_local = null;
    ListaConsulta listaConsulta_local = null;
    ManejadorRequest manejadorRequest_local = null;
    GrupoInformacion grupoInformacionActual_local = null;
    ManejadorSesion manejadorSesion_local = null;
    List listaAtributosRequestMultiparte_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      estadoActual_local = "";
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          if (manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
            
            idAplicacionActual_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local).toString());
          } else {
            
            idAplicacionActual_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerIdUltimoRegistro("APLICACION", ap.conformarNombreCampoLlavePrimaria("APLICACION"), "", -1);
          } 
          
          grupoInformacionActual_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerGrupoInformacionPrincipalAplicacion(idAplicacionActual_local);
          
          valorLlavePrimaria_local = -1;
          estadoActual_local = "Consultando";
          accion_local = 83;
          numeroPagina_local = 12;
          ejecutarConsulta_local = grupoInformacionActual_local.getAplicacion().esPermitirConsultaGeneral();
        } 
        numeroError_local = 0;
        tipoError_local = -1;
        listaNavegacion_local = new ListaNavegacion();
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("listaNavegacion", listaNavegacion_local));
        
        manejadorSesion_local.adicionarElementoNavegacionPagina(ap.obtenerNuevoElementoNavegacionAplicacion(grupoInformacionActual_local, valorLlavePrimaria_local, null, null, null), ap.obtenerNuevoElementoNavegacionEstado(estadoActual_local, accion_local, numeroPagina_local, numeroError_local, tipoError_local), ap.obtenerNuevoElementoNavegacionUbicacionAplicacion(configuracion_local, recargarPagina_local, esDocumento_local, plantillaUtilizar_local, ejecutarConsulta_local));
        
        listaConsulta_local = new ListaConsulta();
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("listaConsulta", listaConsulta_local));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("listaAtributosRequestMultiparte", listaAtributosRequestMultiparte_local));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("numeroPaginaNavegacion", Integer.valueOf(1)));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("existenEventosEnEjecucion", Boolean.valueOf(false)));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("consultaPrincipal", ""));
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      estadoActual_local = null;
      listaConsulta_local = null;
      manejadorSesion_local = null;
      listaNavegacion_local = null;
      manejadorRequest_local = null;
      grupoInformacionActual_local = null;
      listaAtributosRequestMultiparte_local = null;
    } 
  }
  private void crearDirectorioUsuario(HttpServletRequest pRequest) {
    String directorioUsuario_local = null;
    String nombreDirectorio_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Usuario usuario_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      usuario_local = manejadorSesion_local.obtenerUsuarioActual();
      if (usuario_local != ConstantesGeneral.VALOR_NULO) {
        manejadorArchivos_local = new ManejadorArchivos();
        directorioUsuario_local = mc.concatenarCadena("/administrador/", usuario_local.getNombreUsuario() + ap.obtenerSeparadorArchivos());
        
        nombreDirectorio_local = manejadorSesion_local.obtenerRutaRealArchivoSesion(directorioUsuario_local);
        manejadorArchivos_local.crearDirectorio(nombreDirectorio_local);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      usuario_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      nombreDirectorio_local = null;
      manejadorArchivos_local = null;
      directorioUsuario_local = null;
    } 
  }
  private void borrarContenidoDirectorioUsuario(HttpServletRequest pRequest) {
    String directorioUsuario_local = null;
    String nombreDirectorio_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Usuario usuario_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      usuario_local = manejadorSesion_local.obtenerUsuarioActual();
      if (usuario_local != ConstantesGeneral.VALOR_NULO) {
        manejadorArchivos_local = new ManejadorArchivos();
        directorioUsuario_local = mc.concatenarCadena("/administrador/", usuario_local.getNombreUsuario() + ap.obtenerSeparadorArchivos());
        
        nombreDirectorio_local = manejadorSesion_local.obtenerRutaRealArchivoSesion(directorioUsuario_local);
        manejadorArchivos_local.borrarContenidoDirectorio(new File(nombreDirectorio_local));
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      usuario_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      nombreDirectorio_local = null;
      manejadorArchivos_local = null;
      directorioUsuario_local = null;
    } 
  }
  protected void iniciarSesion(HttpServletRequest pRequest) {
    int tiempoSesion_local = -1;
    String nombreUsuario_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Aplicacion aplicacion_local = null;
    Usuario usuario_local = null;
    ManejadorEventos manejadorEventos_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerNuevaSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("atributoSisnetLtda", "SISNET"));
        
        administradorBaseDatosSisnet_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosSisnet(manejadorRequest_local.obtenerNombreRecursoAplicacion()));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("basedatossisnet", administradorBaseDatosSisnet_local));
        
        manejadorEventos_local = new ManejadorEventos();
        manejadorEventos_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          aplicacion_local = administradorBaseDatosSisnet_local.obtenerAplicacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local).toString()), false);
        }
        else {
          
          aplicacion_local = administradorBaseDatosSisnet_local.obtenerAplicacionPorId(administradorBaseDatosSisnet_local.obtenerIdUltimoRegistro("APLICACION", ap.conformarNombreCampoLlavePrimaria("APLICACION"), "", -1), false);
        } 
        
        if (aplicacion_local != ConstantesGeneral.VALOR_NULO) {
          administradorBaseDatosAplicacion_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosAplicacion(manejadorRequest_local.obtenerNombreRecursoAplicacionWeb()));
          
          manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("basedatosaplicacion", administradorBaseDatosAplicacion_local));
          
          manejadorEventos_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
          manejadorEventos_local.setGrupoInformacion(administradorBaseDatosSisnet_local.obtenerGrupoInformacionPrincipalAplicacion(aplicacion_local.getIdAplicacion()));
        } else {
          
          manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("basedatosaplicacion", administradorBaseDatosSisnet_local));
        } 
        
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          nombreUsuario_local = manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local).toString();
          
          nombreUsuario_local = mc.convertirAMayusculas(nombreUsuario_local);
          usuario_local = administradorBaseDatosSisnet_local.obtenerUsuarioPorNombre(nombreUsuario_local);
          tiempoSesion_local = usuario_local.getTiempoSesion();
          manejadorEventos_local.setUsuario(usuario_local);
          manejadorEventos_local.setIdSesion(manejadorSesion_local.obtenerIdSesion());
        } 
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("usuarioActual", usuario_local));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("manejadorEventos", manejadorEventos_local));
        
        manejadorSesion_local.asignarValorAtributoSesionNulo("listaCampoValoresAnteriores");
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("mensajeEventos", ""));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("tipoMensajeEventos", Integer.valueOf(-1)));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("idregistrovisitado", Integer.valueOf(-1)));
        
        manejadorSesion_local.adicionarAtributoSesion(new AtributoSesion("consecutivoarchivos", Integer.valueOf(1)));
        
        if (tiempoSesion_local != 0) {
          tiempoSesion_local *= 60;
        } else {
          tiempoSesion_local = 7200;
        } 
        manejadorSesion_local.asignarMaximoTiempoInactividad(tiempoSesion_local);
        iniciarNavegacion(pRequest);
        crearDirectorioUsuario(pRequest);
        borrarContenidoDirectorioUsuario(pRequest);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      aplicacion_local = null;
      nombreUsuario_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      administradorBaseDatosSisnet_local = null;
      manejadorEventos_local = null;
      administradorBaseDatosAplicacion_local = null;
    } 
  }
  protected void cerrarSesion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      abrirPaginaLogin(pRequest, true);
      manejadorSesion_local.actualizarNumeroError(4);
      manejadorSesion_local.actualizarTipoError(3);
      manejadorSesion_local.actualizarAccion(96);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  protected void abrirPaginaLogin(HttpServletRequest pRequest, boolean pEsUsuarioValido) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.actualizarNumeroPagina(3);
      if (pEsUsuarioValido) {
        manejadorSesion_local.actualizarTipoError(-1);
        manejadorSesion_local.actualizarNumeroError(0);
      } else {
        manejadorSesion_local.actualizarTipoError(2);
        manejadorSesion_local.actualizarNumeroError(2);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaMotorAplicacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarNumeroPagina(24);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
        manejadorSesion_local.actualizarConfiguracion(false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  protected int entrarAplicacion(HttpServletRequest pRequest, HttpServletResponse pResponse, AdministradorBaseDatos pAdministradorBaseDatosSisnet, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) throws ServletException, IOException {
    int tipoAutorizacion_local = 1;
    int tipoUsuario_local = -1;
    int tipoBloqueo_local = -1;
    boolean existenAplicaciones_local = false;
    String nombreUsuario_local = null;
    String contrasena_local = null;
    String tituloAplicacion_local = null;
    ManejadorPermisoUsuario manejadorPermisoUsuario_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Usuario usuario_local = null;
    Aplicacion aplicacion_local = null;
    Aplicacion aplicacionInicial_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return tipoAutorizacion_local;
    }
    if (pResponse == ConstantesGeneral.VALOR_NULO) {
      return tipoAutorizacion_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return tipoAutorizacion_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return tipoAutorizacion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      if (manejadorRequest_local.getListaAtributosRequest() != ConstantesGeneral.VALOR_NULO) {
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          nombreUsuario_local = manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local).toString();
          
          nombreUsuario_local = mc.convertirAMayusculas(nombreUsuario_local);
        } 
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          contrasena_local = mc.convertirAMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local).toString());
        }
        
        if (pAdministradorBaseDatosSisnet.verificarUsuarioPorNombre(nombreUsuario_local)) {
          usuario_local = pAdministradorBaseDatosSisnet.obtenerUsuarioPorNombre(nombreUsuario_local);
          if (usuario_local.esAsignacionAdministrador()) {
            return 3;
          }
          tipoUsuario_local = pAdministradorBaseDatosSisnet.obtenerTipoUsuarioPorContrasena(nombreUsuario_local, contrasena_local);
          if (tipoUsuario_local == 1000) {
            return 10;
          }
          if (tipoUsuario_local == 0 && 
            !mc.sonCadenasIgualesIgnorarMayusculas(cp.obtenerValorPropiedadSisnet("PERMITIR_INGRESO_ADMINISTRADOR"), "Si"))
          {
            
            return 10;
          }
          
          if (tipoUsuario_local != -1) {
            existenAplicaciones_local = (pAdministradorBaseDatosSisnet.obtenerIdPrimeraAplicacion(1, -1) != -1);
            
            if (!existenAplicaciones_local) {
              if (mc.sonCadenasIguales(nombreUsuario_local, "ADMINISTRADOR")) {
                if (manejadorRequest_local.obtenerValorAtributoRequest("fldtituloaplicacion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
                {
                  tituloAplicacion_local = manejadorRequest_local.obtenerValorAtributoRequest("fldtituloaplicacion", manejadorSesion_local).toString();
                  
                  aplicacionInicial_local = conformarAplicacionInicial(tituloAplicacion_local);
                  if (pAdministradorBaseDatosSisnet.incluirAplicacion(aplicacionInicial_local) == 0)
                  {
                    pAdministradorBaseDatosAplicacion.crearTabla(aplicacionInicial_local.getNombreAplicacion());
                  }
                  aplicacion_local = pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(pAdministradorBaseDatosSisnet.obtenerIdUltimoRegistro("APLICACION", "fldidaplicacion", "", -1), false);
                }
              
              }
              else {
                
                return 9;
              }
            
            } else if (manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
              
              aplicacion_local = pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local).toString()), false);
            } 
            
            manejadorPermisoUsuario_local = new ManejadorPermisoUsuario(tipoUsuario_local);
            manejadorPermisoUsuario_local.setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
            
            if (!manejadorPermisoUsuario_local.verificarPermisoVerRegistrosAplicacion(aplicacion_local)) {
              return 2;
            }
            tipoBloqueo_local = usuario_local.getTipoBloqueo();
            if (tipoBloqueo_local == 2) {
              return 4;
            }
            if (tipoBloqueo_local == 3) {
              return 5;
            }
            if (tipoBloqueo_local == 4) {
              return 6;
            }
            tipoAutorizacion_local = 0;
            iniciarSesion(pRequest);
            abrirPaginaMotorAplicacion(pRequest);
          } else {
            pAdministradorBaseDatosSisnet.asignarCantidadContrasenasFallidas(usuario_local);
            return 1;
          } 
        } else {
          if (nombreUsuario_local == ConstantesGeneral.VALOR_NULO && contrasena_local == ConstantesGeneral.VALOR_NULO) {
            return 7;
          }
          return 1;
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      usuario_local = null;
      aplicacion_local = null;
      contrasena_local = null;
      tituloAplicacion_local = null;
      nombreUsuario_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      aplicacionInicial_local = null;
      manejadorPermisoUsuario_local = null;
    } 
    return tipoAutorizacion_local;
  }
  protected void abrirPaginaLoginAcceso(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.cerrarSesion();
      iniciarSesion(pRequest);
      manejadorSesion_local.setSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
      {
        grupoInformacion_local = ap.obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()), ap.obtenerAplicacionAdministradorSisnetWeb(manejadorRequest_local.obtenerNombreRecursoAplicacionWeb()));
      }
      
      manejadorSesion_local.actualizarAccion(96);
      manejadorSesion_local.actualizarNumeroPagina(3);
      manejadorSesion_local.clonarUltimoElementoListaNavegacion();
      manejadorSesion_local.actualizarGrupoInformacionActual(grupoInformacion_local);
      manejadorSesion_local.actualizarAccion(79);
      manejadorSesion_local.actualizarEstadoActual("Consultando");
      manejadorSesion_local.actualizarConfiguracion(true);
      manejadorSesion_local.actualizarNumeroPagina(21);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void abrirPaginaIncluirRegistroPrincipalAplicacion(HttpServletRequest pRequest) {
    int numeroPagina_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        numeroPagina_local = 13;
        manejadorSesion_local.actualizarEstadoActual("Incluyendo");
        manejadorSesion_local.actualizarNumeroPagina(numeroPagina_local);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } else {
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaModificarRegistroPrincipalAplicacion(HttpServletRequest pRequest) {
    int numeroPagina_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        numeroPagina_local = 14;
        manejadorSesion_local.actualizarEstadoActual("Modificando");
        manejadorSesion_local.actualizarNumeroPagina(numeroPagina_local);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } else {
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaConsultaPrincipalAplicacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarNumeroPagina(12);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
        manejadorSesion_local.actualizarConfiguracion(false);
        manejadorSesion_local.actualizarGrupoInformacionActual(manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion()));
      }
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaIncluirValorTabla(HttpServletRequest pRequest, String pEstadoAplicacion) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pEstadoAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.actualizarEstadoActual(pEstadoAplicacion);
      manejadorSesion_local.actualizarAccion(73);
      manejadorSesion_local.actualizarNumeroPagina(17);
      manejadorSesion_local.actualizarNumeroError(0);
      manejadorSesion_local.actualizarTipoError(-1);
      manejadorSesion_local.actualizarConfiguracion(true);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  protected void abrirPaginaConsultaGeneralGrupoInformacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        if (manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          if (manejadorSesion_local.esConfiguracion()) {
            grupoInformacion_local = ap.obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()), manejadorSesion_local.obtenerAplicacionActual());
          }
          else {
            
            grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()));
          } 
          
          manejadorSesion_local.actualizarGrupoInformacionActual(grupoInformacion_local);
          manejadorSesion_local.actualizarEstadoActual("Consultando");
        } 
        manejadorSesion_local.actualizarNumeroPagina(15);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void abrirPaginaIncluirRegistroAplicacion(HttpServletRequest pRequest, String pEstadoAplicacion) {
    int valorLlavePrimariaGrupoInformacion_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pEstadoAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.actualizarEstadoActual(pEstadoAplicacion);
      manejadorSesion_local.actualizarAccion(90);
      if (manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        if (manejadorSesion_local.esConfiguracion()) {
          grupoInformacion_local = ap.obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()), manejadorSesion_local.obtenerAplicacionActual());
        }
        else {
          
          grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()));
        } 
        
        manejadorSesion_local.actualizarGrupoInformacionActual(grupoInformacion_local);
      } 
      if (mc.sonCadenasIguales(pEstadoAplicacion, "Modificando") && manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        valorLlavePrimariaGrupoInformacion_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
        
        manejadorSesion_local.actualizarValorLlavePrimaria(valorLlavePrimariaGrupoInformacion_local);
      } 
      manejadorSesion_local.actualizarNumeroPagina(16);
      manejadorSesion_local.actualizarNumeroError(0);
      manejadorSesion_local.actualizarTipoError(-1);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void abrirPaginaVerDependencias(HttpServletRequest pRequest) {
    int valorLlavePrimaria_local = -1;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Tabla tablaActual_local = null;
    Tabla tablaDepende_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        tablaActual_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("tabla", manejadorSesion_local).toString()));
        
        tablaDepende_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("tablaDepende", manejadorSesion_local).toString()));
        
        manejadorSesion_local.actualizarValorLlavePrimaria(valorLlavePrimaria_local);
        manejadorSesion_local.actualizarTablaActual(tablaActual_local);
        manejadorSesion_local.actualizarTablaDepende(tablaDepende_local);
        manejadorSesion_local.actualizarEstadoActual("Consultando");
        manejadorSesion_local.actualizarNumeroPagina(18);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } else {
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      tablaActual_local = null;
      tablaDepende_local = null;
    } 
  }
  private void abrirPaginaIncluirValorDependiente(HttpServletRequest pRequest, String pEstadoAplicacion) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pEstadoAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.actualizarEstadoActual(pEstadoAplicacion);
      manejadorSesion_local.actualizarAccion(20);
      manejadorSesion_local.actualizarNumeroPagina(17);
      manejadorSesion_local.actualizarNumeroError(0);
      manejadorSesion_local.actualizarTipoError(-1);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaDependienteHabilitacion(HttpServletRequest pRequest) {
    int valorLlavePrimaria_local = -1;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Tabla tablaDepende_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        tablaDepende_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("tablaDepende", manejadorSesion_local).toString()));
        
        manejadorSesion_local.actualizarValorLlavePrimaria(valorLlavePrimaria_local);
        manejadorSesion_local.actualizarTablaDepende(tablaDepende_local);
        manejadorSesion_local.actualizarEstadoActual("Consultando");
        manejadorSesion_local.actualizarNumeroPagina(18);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } else {
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      tablaDepende_local = null;
    } 
  }
  private void abrirPaginaIncluirDependienteHabilitacion(HttpServletRequest pRequest, String pEstadoAplicacion) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pEstadoAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.actualizarEstadoActual(pEstadoAplicacion);
      manejadorSesion_local.actualizarAccion(32);
      manejadorSesion_local.actualizarNumeroPagina(17);
      manejadorSesion_local.actualizarNumeroError(0);
      manejadorSesion_local.actualizarTipoError(-1);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaModificarActuacion(HttpServletRequest pRequest, String pEstadoActuacion) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pEstadoActuacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarEstadoActual(pEstadoActuacion);
        manejadorSesion_local.actualizarNumeroPagina(19);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaImpresionMasiva(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarNumeroPagina(27);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void generarImpresionMasiva(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    ManejadorPlantilla manejadorPlantilla_local = null;
    ManejadorCampoEnlazado manejadorCampoEnlazado_local = null;
    int valorIdPlantilla_local = -1;
    String contenidoImpresionMasiva_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    String rutaArchivoImpresionMasiva_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorArchivos_local = new ManejadorArchivos();
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorCampoEnlazado_local = new ManejadorCampoEnlazado();
      manejadorCampoEnlazado_local.setAdministradorBaseDatosAplicacion(manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion());
      manejadorCampoEnlazado_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
      manejadorCampoEnlazado_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
      manejadorPlantilla_local = new ManejadorPlantilla();
      manejadorPlantilla_local.setAdministradorBaseDatosAplicacion(manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion());
      manejadorPlantilla_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
      manejadorPlantilla_local.setManejadorCampoEnlazado(manejadorCampoEnlazado_local);
      manejadorPlantilla_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
      manejadorPlantilla_local.setRutaDescargaArchivos((new URL(manejadorRequest_local.obtenerURLAplicacion(), manejadorRequest_local.obtenerRecursoAplicacion() + manejadorSesion_local.obtenerRutaDirectorioUsuarioActual())).toString());
      
      manejadorPlantilla_local.setManejadorSesion(manejadorSesion_local);
      valorIdPlantilla_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
      
      contenidoImpresionMasiva_local = manejadorPlantilla_local.obtenerContenidoImpresionMasiva(manejadorSesion_local.obtenerGrupoInformacionActual(), valorIdPlantilla_local, manejadorSesion_local.obtenerConsultaPrincipal());
      
      rutaArchivoImpresionMasiva_local = manejadorSesion_local.obtenerRutaRealArchivoSesion("/administrador/exportacion/" + manejadorSesion_local.obtenerIdSesion() + ".txt");
      
      manejadorArchivos_local.guardarArchivo(rutaArchivoImpresionMasiva_local, contenidoImpresionMasiva_local, false);
      
      abrirPaginaImpresionMasiva(pRequest);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      manejadorPlantilla_local = null;
      manejadorCampoEnlazado_local = null;
      contenidoImpresionMasiva_local = null;
      manejadorArchivos_local = null;
      rutaArchivoImpresionMasiva_local = null;
    } 
  }
  private void abrirPaginaConsultaGeneral(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarEstadoActual("Consultando");
        manejadorSesion_local.actualizarNumeroPagina(1);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private int crearEstructuraAplicacion(HttpServletRequest pRequest, Aplicacion pAplicacion) {
    int errorEjecucion_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      errorEjecucion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().crearTabla(pAplicacion.getNombreAplicacion());
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    
    return errorEjecucion_local;
  }
  private int crearEstructuraTabla(Tabla pTabla, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int errorEjecucion_local = 0;
    String nombreTabla_local = null;
    Campo campoValorTabla_local = null;
    
    if (pTabla == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      nombreTabla_local = pTabla.getNombreTabla();
      errorEjecucion_local = pAdministradorBaseDatosAplicacion.crearTabla(nombreTabla_local);
      if (errorEjecucion_local == 0) {
        campoValorTabla_local = new Campo();
        campoValorTabla_local.setNombreCampo(nombreTabla_local);
        campoValorTabla_local.setEtiquetaCampo("Valor");
        campoValorTabla_local.setSeudonimo(nombreTabla_local);
        campoValorTabla_local.getFormatoCampo().setTipoDato("T");
        campoValorTabla_local.getFormatoCampo().setLongitudCampo(80);
        campoValorTabla_local.setObligatorio(true);
        campoValorTabla_local.setModificable(true);
        campoValorTabla_local.setVisibleUsuarioPrincipal(true);
        errorEjecucion_local = pAdministradorBaseDatosAplicacion.crearCampo(nombreTabla_local, campoValorTabla_local, "");
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      nombreTabla_local = null;
      campoValorTabla_local = null;
    } 
    
    return errorEjecucion_local;
  }
  private int crearEstructuraGrupoInformacion(GrupoInformacion pGrupoInformacion, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int errorEjecucion_local = 0;
    Campo campoLlaveForanea_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      if (pGrupoInformacion.esGrupoInformacionMultiple()) {
        campoLlaveForanea_local = ap.conformarCampoLlaveForanea(pGrupoInformacion);
        pAdministradorBaseDatosAplicacion.crearTabla(pGrupoInformacion.getNombreGrupoInformacion());
        pAdministradorBaseDatosAplicacion.crearCampo(pGrupoInformacion.getNombreGrupoInformacion(), campoLlaveForanea_local, ap.conformarNombreCampoLlavePrimaria(pGrupoInformacion.getAplicacion().getNombreAplicacion()));
        
        pAdministradorBaseDatosAplicacion.crearRestriccionLlaveForanea(pGrupoInformacion.getNombreGrupoInformacion(), campoLlaveForanea_local.getNombreCampo(), pGrupoInformacion.getAplicacion().getNombreAplicacion(), campoLlaveForanea_local.getNombreCampo(), mc.concatenarCadena(pGrupoInformacion.getNombreGrupoInformacion(), pGrupoInformacion.getAplicacion().getNombreAplicacion()));
      }
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campoLlaveForanea_local = null;
    } 
    
    return errorEjecucion_local;
  }
  private int crearEstructuraCampo(Campo pCampo, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int errorConsulta_local = 0;
    String nombreGrupoInformacion_local = null;
    String nombreCampo_local = null;
    String nombreCampoIndice_local = null;
    Campo campoNombreArchivo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorConsulta_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorConsulta_local;
    }
    
    try {
      nombreCampo_local = pCampo.getNombreCampo();
      if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
        nombreCampoIndice_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()), String.valueOf(',') + nombreCampo_local);
      } else {
        
        nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
        nombreCampoIndice_local = nombreCampo_local;
      } 
      
      errorConsulta_local = pAdministradorBaseDatosAplicacion.crearCampo(nombreGrupoInformacion_local, pCampo, ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()));
      
      if (pCampo.getFormatoCampo().esValorUnico() && errorConsulta_local == 0) {
        if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
          nombreCampoIndice_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()), String.valueOf(',') + nombreCampo_local);
        } else {
          
          nombreCampoIndice_local = nombreCampo_local;
        } 
        errorConsulta_local = pAdministradorBaseDatosAplicacion.crearIndice("idx" + nombreGrupoInformacion_local + nombreCampo_local, nombreGrupoInformacion_local, nombreCampoIndice_local);
      } 
      
      if (mc.sonCadenasIguales(pCampo.getFormatoCampo().getTipoDato(), "J")) {
        campoNombreArchivo_local = ap.conformarCampoNombreArchivo(pCampo);
        errorConsulta_local = pAdministradorBaseDatosAplicacion.crearCampo(nombreGrupoInformacion_local, campoNombreArchivo_local, ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()));
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      nombreCampo_local = null;
      nombreCampoIndice_local = null;
      campoNombreArchivo_local = null;
      nombreGrupoInformacion_local = null;
    } 
    
    return errorConsulta_local;
  }
  private void asignarValoresListaAtributosRequestCampos(HttpServletRequest pRequest, ListaCampo pListaCampo) {
    Iterator iterator_local = null;
    AtributoRequest atributoRequest_local = null;
    String nombreAtributoRequest_local = null;
    Object valor_local = null;
    ManejadorRequest manejadorRequest_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      iterator_local = manejadorRequest_local.getListaAtributosRequest().iterator();
      while (iterator_local.hasNext()) {
        atributoRequest_local = (AtributoRequest)iterator_local.next();
        nombreAtributoRequest_local = atributoRequest_local.getNombreAtributo();
        valor_local = atributoRequest_local.getValorAtributo();
        if (valor_local != ConstantesGeneral.VALOR_NULO) {
          pListaCampo.asignarValorCampo(nombreAtributoRequest_local, valor_local);
        }
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      iterator_local = null;
      atributoRequest_local = null;
      nombreAtributoRequest_local = null;
      valor_local = null;
      manejadorRequest_local = null;
    } 
  }
  private void asignarValoresFormFieldACampos(HttpServletRequest pRequest, ListaCampo pListaCampo) {
    int tamanoMaximo_local = 0;
    String nombreCampo_local = null;
    String valorCampo_local = null;
    String nombreArchivo_local = null;
    ManejadorSesion manejadorSesion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    DiskFileItemFactory diskFileItemFactory_local = null;
    ServletFileUpload servletFileUpload_local = null;
    List items_local = null;
    FileItem fileItem_local = null;
    Iterator iterador_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      diskFileItemFactory_local = new DiskFileItemFactory();
      servletFileUpload_local = new ServletFileUpload((FileItemFactory)diskFileItemFactory_local);
      items_local = servletFileUpload_local.parseRequest(pRequest);
      if (manejadorSesion_local.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
        iterador_local = manejadorSesion_local.obtenerListaAtributosRequestMultiparte().iterator();
      }
      else if (items_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = items_local.iterator();
      } 
      
      while (iterador_local.hasNext()) {
        fileItem_local = (FileItem)iterador_local.next();
        nombreCampo_local = fileItem_local.getFieldName();
        if (fileItem_local.isFormField()) {
          valorCampo_local = fileItem_local.getString();
        } else {
          valorCampo_local = manejadorSesion_local.obtenerRutaRealArchivoSesion("/administrador/" + manejadorSesion_local.obtenerUsuarioActual().getNombreUsuario() + ap.obtenerSeparadorArchivos());
          
          nombreArchivo_local = mc.convertirTildeANoTilde(ap.obtenerNombreArchivo(fileItem_local.getName()));
          tamanoMaximo_local = manejadorSesion_local.obtenerAplicacionActual().getTamanoMaximoArchivos();
          if (!mc.esCadenaVacia(nombreArchivo_local) && (tamanoMaximo_local == 0 || fileItem_local.getSize() <= tamanoMaximo_local)) {
            
            manejadorRequest_local.subirArchivo(fileItem_local, valorCampo_local);
            valorCampo_local = mc.concatenarCadena(valorCampo_local, ap.obtenerSeparadorArchivos() + nombreArchivo_local);
          } else {
            valorCampo_local = "";
          } 
        } 
        pListaCampo.asignarValorCampo(nombreCampo_local, valorCampo_local);
      } 
      manejadorRequest_local.borrarAtributosRequest();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      items_local = null;
      iterador_local = null;
      fileItem_local = null;
      valorCampo_local = null;
      nombreCampo_local = null;
      nombreArchivo_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      servletFileUpload_local = null;
      diskFileItemFactory_local = null;
    } 
  }
  public void asignarValoresRequestCamposGrupoInformacion(HttpServletRequest pRequest, ListaCampo pListaCampo) {
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (ServletFileUpload.isMultipartContent(pRequest)) {
        asignarValoresFormFieldACampos(pRequest, pListaCampo);
      } else {
        asignarValoresListaAtributosRequestCampos(pRequest, pListaCampo);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
  }
  private int incluirUsuarioLogin(HttpServletRequest pRequest) {
    int errorEjecucion_local = -1;
    int comparaFecha_local = 0;
    String contrasena_local = null;
    Usuario usuario_local = null;
    ListaCampo listaCampo_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    TipoUsuario tipoUsuario_local = null;
    Encriptor encriptor_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      listaCampo_local = ap.obtenerCamposTabla(manejadorRequest_local.obtenerNombreRecursoAplicacion(), 5);
      
      asignarValoresRequestCamposGrupoInformacion(pRequest, listaCampo_local);
      tipoUsuario_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTipoUsuarioPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidtipousuario", manejadorSesion_local).toString()));
      
      usuario_local = new Usuario();
      usuario_local.setNombreUsuario(mc.convertirCadenaFormatoNombre(manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local).toString()));
      
      encriptor_local = new Encriptor("", false);
      contrasena_local = encriptor_local.encriptarCadena();
      usuario_local.setContrasena(contrasena_local);
      usuario_local.setTipoLicencia(manejadorRequest_local.obtenerValorAtributoRequest("fldtipolicencia", manejadorSesion_local).toString());
      
      usuario_local.setTipoUsuario(tipoUsuario_local);
      usuario_local.setFechaVencimiento(Date.valueOf(mc.convertirFormatoFechaDDMMAAAA(manejadorRequest_local.obtenerValorAtributoRequest("fldfechavencimiento", manejadorSesion_local).toString())));
      
      comparaFecha_local = Date.valueOf(mc.convertirFormatoFechaDDMMAAAA(mf.obtenerFechaActualSistema(true))).compareTo(usuario_local.getFechaVencimiento());
      
      if (comparaFecha_local < 0) {
        errorEjecucion_local = incluirRegistroAplicacion(pRequest, false);
      } else {
        errorEjecucion_local = 14;
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      usuario_local = null;
      encriptor_local = null;
      contrasena_local = null;
      listaCampo_local = null;
      tipoUsuario_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
    } 
    return errorEjecucion_local;
  }
  private Aplicacion conformarAplicacion(ListaCampo pListaCampo, boolean pEsTabla, AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    Aplicacion aplicacion_local = null;
    int idAplicacion_local = -1;
    int idAplicacionConsulta_local = -1;
    int idAplicacionReporte_local = -1;
    int idAplicacionImpresionMasiva_local = -1;
    int tamanoMaximoArchivos_local = 0;
    int numeroRegistrosPagina_local = 0;
    String valorIdAplicacion_local = null;
    String valorAplicacionConsulta_local = null;
    String valorAplicacionReporte_local = null;
    String valorAplicacionImpresionMasiva_local = null;
    String valorTamanoMaximoArchivos_local = null;
    String valorNumeroRegistrosPagina_local = null;
    Aplicacion aplicacionConsulta_local = null;
    Aplicacion aplicacionReporte_local = null;
    Aplicacion aplicacionImpresionMasiva_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    
    try {
      valorIdAplicacion_local = pListaCampo.obtenerValorCampo("fldidaplicacion");
      valorAplicacionConsulta_local = pListaCampo.obtenerValorCampo("fldidaplicacionconsulta");
      valorAplicacionReporte_local = pListaCampo.obtenerValorCampo("fldidaplicacionreporte");
      valorAplicacionImpresionMasiva_local = pListaCampo.obtenerValorCampo("fldidaplicacionimpresionmasiva");
      valorTamanoMaximoArchivos_local = pListaCampo.obtenerValorCampo("fldtamanomaximoarchivos");
      valorNumeroRegistrosPagina_local = pListaCampo.obtenerValorCampo("fldnumeroregistrospagina");
      
      if (mc.esCadenaNumerica(valorIdAplicacion_local, true)) {
        idAplicacion_local = Integer.parseInt(valorIdAplicacion_local);
      }
      if (mc.esCadenaNumerica(valorAplicacionConsulta_local, true)) {
        idAplicacionConsulta_local = Integer.parseInt(valorAplicacionConsulta_local);
        aplicacionConsulta_local = pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(idAplicacionConsulta_local, false);
      } 
      
      if (mc.esCadenaNumerica(valorAplicacionReporte_local, true)) {
        idAplicacionReporte_local = Integer.parseInt(valorAplicacionReporte_local);
        aplicacionReporte_local = pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(idAplicacionReporte_local, false);
      } 
      
      if (mc.esCadenaNumerica(valorAplicacionImpresionMasiva_local, true)) {
        idAplicacionImpresionMasiva_local = Integer.parseInt(valorAplicacionImpresionMasiva_local);
        aplicacionImpresionMasiva_local = pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(idAplicacionImpresionMasiva_local, false);
      } 
      
      if (mc.esCadenaNumerica(valorTamanoMaximoArchivos_local, true)) {
        tamanoMaximoArchivos_local = Integer.parseInt(valorTamanoMaximoArchivos_local);
      }
      if (mc.esCadenaNumerica(valorNumeroRegistrosPagina_local, true)) {
        numeroRegistrosPagina_local = Integer.parseInt(valorNumeroRegistrosPagina_local);
      }
      
      aplicacion_local = new Aplicacion();
      aplicacion_local.setIdAplicacion(idAplicacion_local);
      aplicacion_local.setTituloAplicacion(pListaCampo.obtenerValorCampo("fldtituloaplicacion"));
      aplicacion_local.setNombreAplicacion(mc.convertirCadenaFormatoNombre(aplicacion_local.getTituloAplicacion()));
      aplicacion_local.setEsTabla(pEsTabla);
      aplicacion_local.setAplicacionConsulta(aplicacionConsulta_local);
      aplicacion_local.setAplicacionReporte(aplicacionReporte_local);
      aplicacion_local.setAplicacionImpresionMasiva(aplicacionImpresionMasiva_local);
      aplicacion_local.setActualizarInformacionEnlazada(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldactualizarinformacionenlazada")));
      
      aplicacion_local.setAplicacionesActualizar(pListaCampo.obtenerValorCampo("fldaplicacionesactualizar"));
      aplicacion_local.setAdvertenciaEjecucion(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldadvertenciaejecucion")));
      
      aplicacion_local.setPermitirConsultaGeneral(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldpermitirconsultageneral")));
      
      aplicacion_local.setTamanoMaximoArchivos(tamanoMaximoArchivos_local);
      aplicacion_local.setHacerDobleCalculo(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldhacerdoblecalculo")));
      
      aplicacion_local.setNumeroRegistrosPagina(numeroRegistrosPagina_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorIdAplicacion_local = null;
      aplicacionReporte_local = null;
      aplicacionConsulta_local = null;
      aplicacionImpresionMasiva_local = null;
      valorAplicacionReporte_local = null;
      valorAplicacionConsulta_local = null;
      valorTamanoMaximoArchivos_local = null;
      valorNumeroRegistrosPagina_local = null;
    } 
    
    return aplicacion_local;
  }
  private Aplicacion conformarAplicacionInicial(String pTituloAplicacion) {
    Aplicacion aplicacion_local = null;
    
    if (pTituloAplicacion == ConstantesGeneral.VALOR_NULO) {
      return aplicacion_local;
    }
    
    try {
      aplicacion_local = new Aplicacion();
      aplicacion_local.setTituloAplicacion(pTituloAplicacion);
      aplicacion_local.setNombreAplicacion(mc.convertirCadenaFormatoNombre(pTituloAplicacion));
      aplicacion_local.setActualizarInformacionEnlazada(true);
      aplicacion_local.setAdvertenciaEjecucion(true);
      aplicacion_local.setPermitirConsultaGeneral(false);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return aplicacion_local;
  }
  private GrupoInformacion conformarGrupoInformacion(HttpServletRequest pRequest, ListaCampo pListaCampo, Aplicacion pAplicacion, boolean pEsModificacion) {
    GrupoInformacion grupoInformacion_local = null;
    int idGrupoInformacion_local = -1;
    String valorIdGrupo_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return grupoInformacion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      valorIdGrupo_local = pListaCampo.obtenerValorCampo("fldidgrupoinformacion");
      if (mc.esCadenaNumerica(valorIdGrupo_local, true)) {
        idGrupoInformacion_local = Integer.parseInt(valorIdGrupo_local);
      }
      if (pEsModificacion) {
        grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(idGrupoInformacion_local);
        grupoInformacion_local.setDescripcionGrupoInformacion(pListaCampo.obtenerValorCampo("flddescripciongrupoinformacion"));
      } else {
        
        grupoInformacion_local = new GrupoInformacion();
        grupoInformacion_local.setIdGrupoInformacion(idGrupoInformacion_local);
        grupoInformacion_local.setAplicacion(pAplicacion);
        grupoInformacion_local.setDescripcionGrupoInformacion(pListaCampo.obtenerValorCampo("flddescripciongrupoinformacion"));
        
        grupoInformacion_local.setNombreGrupoInformacion(mc.convertirCadenaFormatoNombre(grupoInformacion_local.getDescripcionGrupoInformacion()));
        
        grupoInformacion_local.setGrupoInformacionMultiple(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldgrupoinformacionmultiple")));
        
        grupoInformacion_local.setPosicion(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerUltimaPosicionGrupoInformacion(pAplicacion.getIdAplicacion()) + 1);
      } 
      
      grupoInformacion_local.setMostrarDetalle(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldmostrardetalle")));
      
      grupoInformacion_local.setMargenSuperior(Integer.parseInt(pListaCampo.obtenerValorCampo("fldmargensuperiorgrupoinformacion")));
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorIdGrupo_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
    } 
    
    return grupoInformacion_local;
  }
  private boolean verificarDuplicidadCampoGruposNoMultiples(HttpServletRequest pRequest, int pIdAplicacion, String pNombreCampo, AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    boolean duplicidad_local = false;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return duplicidad_local;
    }
    if (pNombreCampo == ConstantesGeneral.VALOR_NULO) {
      return duplicidad_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return duplicidad_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposGruposInformacionNoMultiples(pIdAplicacion);
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext() && !duplicidad_local) {
        campo_local = (Campo)iterador_local.next();
        duplicidad_local = mc.sonCadenasIguales(campo_local.getNombreCampo(), pNombreCampo);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    
    return duplicidad_local;
  }
  private Campo conformarCampo(ListaCampo pListaCampo, GrupoInformacion pGrupoInformacion, boolean pEsModificacion, AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    Campo campo_local = null;
    int idCampo_local = -1;
    int idHabilitadoPor_local = -1;
    int idListaDependiente_local = -1;
    int idEnlazado_local = -1;
    int idCampoOrigenFiltrado_local = -1;
    int idCampoDestinoFiltrado_local = -1;
    int idCampoEnlaceDepende_local = -1;
    int idCampoOrigenEnlace_local = -1;
    int idCampoValor_local = -1;
    int idCampoOrigenUno_local = -1;
    int idCampoOrigenDos_local = -1;
    String valorIdCampo_local = null;
    String valorHabilitadoPor_local = null;
    String valorListaDependiente_local = null;
    String valorEnlazado_local = null;
    String valorCampoOrigenFiltrado_local = null;
    String valorCampoDestinoFiltrado_local = null;
    String valorCampoEnlaceDepende_local = null;
    String valorCampoOrigenEnlace_local = null;
    String valorCampoValor_local = null;
    String valorCampoOrigenUno_local = null;
    String valorCampoOrigenDos_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return campo_local;
    }
    
    try {
      valorIdCampo_local = pListaCampo.obtenerValorCampo("fldidcampo");
      valorHabilitadoPor_local = pListaCampo.obtenerValorCampo("fldhabilitadopor");
      valorListaDependiente_local = pListaCampo.obtenerValorCampo("fldlistadependiente");
      valorEnlazado_local = pListaCampo.obtenerValorCampo("fldenlazado");
      valorCampoOrigenFiltrado_local = pListaCampo.obtenerValorCampo("fldcampoorigenfiltrado");
      valorCampoDestinoFiltrado_local = pListaCampo.obtenerValorCampo("fldcampodestinofiltrado");
      valorCampoEnlaceDepende_local = pListaCampo.obtenerValorCampo("fldcampoenlacedepende");
      valorCampoOrigenEnlace_local = pListaCampo.obtenerValorCampo("fldcampoorigenenlace");
      valorCampoValor_local = pListaCampo.obtenerValorCampo("fldidcampovalor");
      valorCampoOrigenUno_local = pListaCampo.obtenerValorCampo("fldidcampoorigenuno");
      valorCampoOrigenDos_local = pListaCampo.obtenerValorCampo("fldidcampoorigendos");
      
      if (mc.esCadenaNumerica(valorIdCampo_local, true)) {
        idCampo_local = Integer.parseInt(valorIdCampo_local);
      }
      if (mc.esCadenaNumerica(valorHabilitadoPor_local, true)) {
        idHabilitadoPor_local = Integer.parseInt(valorHabilitadoPor_local);
      }
      if (mc.esCadenaNumerica(valorListaDependiente_local, true)) {
        idListaDependiente_local = Integer.parseInt(valorListaDependiente_local);
      }
      if (mc.esCadenaNumerica(valorEnlazado_local, true)) {
        idEnlazado_local = Integer.parseInt(valorEnlazado_local);
      }
      if (mc.esCadenaNumerica(valorCampoOrigenFiltrado_local, true)) {
        idCampoOrigenFiltrado_local = Integer.parseInt(valorCampoOrigenFiltrado_local);
      }
      if (mc.esCadenaNumerica(valorCampoDestinoFiltrado_local, true)) {
        idCampoDestinoFiltrado_local = Integer.parseInt(valorCampoDestinoFiltrado_local);
      }
      if (mc.esCadenaNumerica(valorCampoEnlaceDepende_local, true)) {
        idCampoEnlaceDepende_local = Integer.parseInt(valorCampoEnlaceDepende_local);
      }
      if (mc.esCadenaNumerica(valorCampoOrigenEnlace_local, true)) {
        idCampoOrigenEnlace_local = Integer.parseInt(valorCampoOrigenEnlace_local);
      }
      if (mc.esCadenaNumerica(valorCampoValor_local, true)) {
        idCampoValor_local = Integer.parseInt(valorCampoValor_local);
      }
      if (mc.esCadenaNumerica(valorCampoOrigenUno_local, true)) {
        idCampoOrigenUno_local = Integer.parseInt(valorCampoOrigenUno_local);
      }
      if (mc.esCadenaNumerica(valorCampoOrigenDos_local, true)) {
        idCampoOrigenDos_local = Integer.parseInt(valorCampoOrigenDos_local);
      }
      
      if (pEsModificacion) {
        campo_local = pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampo_local, false);
        campo_local.setEtiquetaCampo(pListaCampo.obtenerValorCampo("fldetiquetacampo"));
      } else {
        campo_local = new Campo();
        campo_local.setIdCampo(idCampo_local);
        campo_local.setGrupoInformacion(pGrupoInformacion);
        campo_local.setEtiquetaCampo(pListaCampo.obtenerValorCampo("fldetiquetacampo"));
        campo_local.setNombreCampo(mc.convertirCadenaFormatoNombre(campo_local.getEtiquetaCampo()));
        campo_local.getFormatoCampo().setTipoDato(pListaCampo.obtenerValorCampo("fldtipodato"));
        campo_local.getFormatoCampo().setLongitudCampo(Integer.parseInt(pListaCampo.obtenerValorCampo("fldlongitudcampo")));
        
        campo_local.getFormatoCampo().setNumeroDecimales(Integer.parseInt(pListaCampo.obtenerValorCampo("fldnumerodecimales")));
        
        campo_local.getRestriccionCampo().setLlaveForanea(campo_local.esTipoDatoTabla());
        campo_local.setPosicion(pAdministradorBaseDatosSisnet.obtenerUltimaPosicionCamposGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion()) + 1);
        
        campo_local.setModificable(true);
        if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "W")) {
          campo_local.setModificable(false);
        }
      } 
      
      campo_local.setSeudonimo(pListaCampo.obtenerValorCampo("fldseudonimo"));
      campo_local.getPlantillaCampo().setTienePlantilla(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldtieneplantilla")));
      
      if (campo_local.getPlantillaCampo().esTienePlantilla()) {
        campo_local.getPlantillaCampo().setAplicacionPlantilla(pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(Integer.parseInt(pListaCampo.obtenerValorCampo("fldidaplicacionplantilla")), false));
      }
      
      campo_local.getFormatoCampo().setValorUnico(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldvalorunico")));
      
      campo_local.setAnchoColumna(Integer.parseInt(pListaCampo.obtenerValorCampo("fldanchocolumna")));
      campo_local.setObligatorio(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldobligatorio")));
      campo_local.setVisibleUsuarioPrincipal(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldvisibleusuarioprincipal")));
      
      campo_local.setVisibleUsuarioSecundario(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldvisibleusuariosecundario")));
      
      campo_local.setHabilitadoPor(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idHabilitadoPor_local, false));
      
      campo_local.setBorrarValorNoHabilitado(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldborrarvalornohabilitado")));
      
      campo_local.setListaDependiente(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idListaDependiente_local, false));
      campo_local.getEnlaceCampo().setEnlazado(pAdministradorBaseDatosSisnet.obtenerAplicacionPorId(idEnlazado_local, false));
      
      campo_local.getEnlaceCampo().setFiltradoRegistrosEnlazados(Integer.parseInt(pListaCampo.obtenerValorCampo("fldfiltradoregistrosenlazados")));
      
      campo_local.getEnlaceCampo().setCampoOrigenFiltrado(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoOrigenFiltrado_local, false));
      
      campo_local.getEnlaceCampo().setCampoDestinoFiltrado(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoDestinoFiltrado_local, false));
      
      campo_local.getEnlaceCampo().setValorFiltrado(pListaCampo.obtenerValorCampo("fldvalorfiltrado"));
      campo_local.getEnlaceCampo().setDependienteEnlazado(Integer.parseInt(pListaCampo.obtenerValorCampo("flddependienteenlazado")));
      
      campo_local.getEnlaceCampo().setOpcionDesconocido(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldopciondesconocido")));
      
      campo_local.getEnlaceCampo().setCampoEnlaceDepende(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoEnlaceDepende_local, false));
      
      campo_local.getEnlaceCampo().setCampoOrigenEnlace(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoOrigenEnlace_local, false));
      
      campo_local.setTipoHabilitacion(Integer.parseInt(pListaCampo.obtenerValorCampo("fldtipohabilitacion")));
      campo_local.getCalculoCampo().setCampoCalculado(Integer.parseInt(pListaCampo.obtenerValorCampo("fldcampocalculado")));
      
      campo_local.getCalculoCampo().setRecalculable(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldesrecalculable")));
      
      campo_local.getCalculoCampo().setCampoValor(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoValor_local, false));
      
      campo_local.getCalculoCampo().setCampoOrigenUno(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoOrigenUno_local, false));
      
      campo_local.getCalculoCampo().setFormatoCampoOrigenUno(pListaCampo.obtenerValorCampo("fldformatocampoorigenuno"));
      
      campo_local.getCalculoCampo().setCampoOrigenDos(pAdministradorBaseDatosSisnet.obtenerCampoPorId(idCampoOrigenDos_local, false));
      
      campo_local.getCalculoCampo().setFormatoCampoOrigenDos(pListaCampo.obtenerValorCampo("fldformatocampoorigendos"));
      
      campo_local.getCalculoCampo().setFormatoCampoCalculado(pListaCampo.obtenerValorCampo("fldformatocampocalculado"));
      
      campo_local.setIncluirOpcionConsulta(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldincluiropcionconsulta")));
      
      campo_local.setRecargarPantalla(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldrecargarpantalla")));
      
      campo_local.getEstiloCampo().setCambiarRenglon(Boolean.valueOf(pListaCampo.obtenerValorCampo("fldcambiarrenglon")).booleanValue());
      
      campo_local.getEstiloCampo().setMargenSuperior(Integer.parseInt(pListaCampo.obtenerValorCampo("fldmargensuperiorcampo")));
      
      campo_local.getEstiloCampo().setAnchoEtiqueta(Integer.parseInt(pListaCampo.obtenerValorCampo("fldanchoetiquetacampo")));
      
      campo_local.getEstiloCampo().setAnchoControl(Integer.parseInt(pListaCampo.obtenerValorCampo("fldanchocontrolcampo")));
      
      campo_local.getEstiloCampo().setCantidadRenglones(Integer.parseInt(pListaCampo.obtenerValorCampo("fldcantidadrenglonescontrolcampo")));
      
      campo_local.getEstiloCampo().setMargenIzquierdaEtiqueta(Integer.parseInt(pListaCampo.obtenerValorCampo("fldmargenizquierdaetiquetacampo")));
      
      campo_local.getEstiloCampo().setMargenIzquierdaControl(Integer.parseInt(pListaCampo.obtenerValorCampo("fldmargenizquierdacontrolcampo")));
      
      campo_local.setEsImagen(Boolean.valueOf(pListaCampo.obtenerValorCampo("fldesimagen")).booleanValue());
      
      campo_local.setAltoImagenPantallaPresentacion(Integer.parseInt(pListaCampo.obtenerValorCampo("fldaltoimagenpantallapresentacion")));
      
      campo_local.setAltoImagenPantallaEdicion(Integer.parseInt(pListaCampo.obtenerValorCampo("fldaltoimagenpantallaedicion")));
      
      campo_local.setOcultarEtiquetaControlExaminar(Boolean.valueOf(pListaCampo.obtenerValorCampo("fldocultaretiquetacontrolexaminar")).booleanValue());
      
      campo_local.setOcultarEtiquetaControlVer(Boolean.valueOf(pListaCampo.obtenerValorCampo("fldocultaretiquetacontrolver")).booleanValue());
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorIdCampo_local = null;
      valorEnlazado_local = null;
      valorCampoValor_local = null;
      valorHabilitadoPor_local = null;
      valorCampoOrigenUno_local = null;
      valorCampoOrigenDos_local = null;
      valorListaDependiente_local = null;
      valorCampoOrigenEnlace_local = null;
      valorCampoEnlaceDepende_local = null;
      valorCampoOrigenFiltrado_local = null;
      valorCampoDestinoFiltrado_local = null;
    } 
    
    return campo_local;
  }
  private Tabla conformarTabla(ListaCampo pListaCampo) {
    Tabla tabla_local = null;
    int idTabla_local = -1;
    String valorIdTabla_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return tabla_local;
    }
    
    try {
      valorIdTabla_local = pListaCampo.obtenerValorCampo("fldidtabla");
      if (mc.esCadenaNumerica(valorIdTabla_local, true)) {
        idTabla_local = Integer.parseInt(valorIdTabla_local);
      }
      
      tabla_local = new Tabla();
      tabla_local.setIdTabla(idTabla_local);
      tabla_local.setDescripcionTabla(pListaCampo.obtenerValorCampo("flddescripciontabla"));
      tabla_local.setNombreTabla(mc.convertirCadenaFormatoNombre(tabla_local.getDescripcionTabla()));
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return tabla_local;
  }
  private TipoUsuario conformarTipoUsuario(ListaCampo pListaCampo) {
    TipoUsuario tipoUsuario_local = null;
    int idTipoUsuario_local = -1;
    String valorIdTipoUsuario_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return tipoUsuario_local;
    }
    
    try {
      valorIdTipoUsuario_local = pListaCampo.obtenerValorCampo("fldidtipousuario");
      if (mc.esCadenaNumerica(valorIdTipoUsuario_local, true)) {
        idTipoUsuario_local = Integer.parseInt(valorIdTipoUsuario_local);
      }
      
      tipoUsuario_local = new TipoUsuario();
      tipoUsuario_local.setIdTipoUsuario(idTipoUsuario_local);
      tipoUsuario_local.setNombreTipoUsuario(pListaCampo.obtenerValorCampo("fldnombretipousuario"));
      tipoUsuario_local.setPermitirUtilizarMenu(!mc.esCadenaVacia(pListaCampo.obtenerValorCampo("fldpermitirutilizarmenu")));
      tipoUsuario_local.setNivelAcceso(pListaCampo.obtenerValorCampo("fldnivelacceso"));
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorIdTipoUsuario_local = null;
    } 
    
    return tipoUsuario_local;
  }
  private Usuario conformarUsuario(ListaCampo pListaCampo, boolean pEsModificacion, AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    Usuario usuario_local = null;
    int idUsuario_local = -1;
    String valorFechaContrasenaFallida_local = null;
    String valorIdUsuario_local = null;
    Encriptor encriptor_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return usuario_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return usuario_local;
    }
    
    try {
      valorIdUsuario_local = pListaCampo.obtenerValorCampo("fldidusuario");
      valorFechaContrasenaFallida_local = pListaCampo.obtenerValorCampo("fldfechaultimacontrasenafallida");
      if (mc.esCadenaNumerica(valorIdUsuario_local, true)) {
        idUsuario_local = Integer.parseInt(valorIdUsuario_local);
      }
      
      if (pEsModificacion) {
        usuario_local = pAdministradorBaseDatosSisnet.obtenerUsuarioPorId(idUsuario_local);
        encriptor_local = new Encriptor("", false);
        if (!mc.sonCadenasIgualesIgnorarMayusculas(encriptor_local.encriptarCadena(), pListaCampo.obtenerValorCampo("fldcontrasena"))) {
          
          usuario_local.setContrasena(pListaCampo.obtenerValorCampo("fldcontrasena"));
          usuario_local.setAsignacionAdministrador(true);
        } else {
          usuario_local.setAsignacionAdministrador(false);
        } 
      } else {
        usuario_local = new Usuario();
        usuario_local.setContrasena(pListaCampo.obtenerValorCampo("fldcontrasena"));
      } 
      usuario_local.setNombreUsuario(pListaCampo.obtenerValorCampo("fldnombreusuario"));
      usuario_local.setNombreCompletoUsuario(pListaCampo.obtenerValorCampo("fldnombrecompletousuario"));
      usuario_local.setTipoLicencia(pListaCampo.obtenerValorCampo("fldtipolicencia"));
      usuario_local.setTipoUsuario(pAdministradorBaseDatosSisnet.obtenerTipoUsuarioPorId(Integer.parseInt(pListaCampo.obtenerValorCampo("fldidtipousuario"))));
      
      usuario_local.setFechaVencimiento(Date.valueOf(mc.convertirFormatoFechaDDMMAAAA(pListaCampo.obtenerValorCampo("fldfechavencimiento"))));
      
      usuario_local.setDiasVigenciaContrasena(Integer.parseInt(pListaCampo.obtenerValorCampo("flddiasvigenciacontrasena")));
      usuario_local.setContrasenasFallidasPermitidas(Integer.parseInt(pListaCampo.obtenerValorCampo("fldcontrasenasfallidaspermitidas")));
      
      if (!mc.esCadenaVacia(valorFechaContrasenaFallida_local)) {
        usuario_local.setFechaUltimaContrasenaFallida(Date.valueOf(mc.convertirFormatoFechaDDMMAAAA(valorFechaContrasenaFallida_local)));
      }
      usuario_local.setCantidadContrasenasFallidas(Integer.parseInt(pListaCampo.obtenerValorCampo("fldcantidadcontrasenasfallidas")));
      usuario_local.setTiempoSesion(Integer.parseInt(pListaCampo.obtenerValorCampo("fldtiemposesion")));
      usuario_local.setTipoBloqueo(Integer.parseInt(pListaCampo.obtenerValorCampo("fldtipobloqueo")));
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorIdUsuario_local = null;
      valorFechaContrasenaFallida_local = null;
    } 
    
    return usuario_local;
  }
  private void asignarHabilitacionCampo(HttpServletRequest pRequest, Campo pCampo) {
    Tabla tabla_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    ResultSet resultSet_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      if (pCampo.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO && pCampo.getHabilitadoPor().esTipoDatoTabla()) {
        manejadorRequest_local = new ManejadorRequest(pRequest);
        manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
        tabla_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(pCampo.getHabilitadoPor().getFormatoCampo().getTipoDato()));
        
        resultSet_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().obtenerValoresTablaInterna(tabla_local.getNombreTabla());
        
        if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
          while (resultSet_local.next()) {
            manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().insertarRegistroDependienteHabilitacion(pCampo.getIdCampo(), resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(tabla_local.getNombreTabla())), false);
          }
        }
      }
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      tabla_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      resultSet_local = null;
    } 
  }
  private int crearEstructurasAplicacion(HttpServletRequest pRequest, int pIdGrupoInformacion, AdministradorBaseDatos pAdministradorBaseDatosSisnet, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int estructurasAplicacion_local = 0;
    ListaCampo listaCampo_local = null;
    Aplicacion aplicacionActual_local = null;
    Aplicacion nuevaAplicacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Campo campo_local = null;
    Tabla tabla_local = null;
    TipoUsuario tipoUsuario_local = null;
    Usuario usuario_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      aplicacionActual_local = manejadorSesion_local.obtenerAplicacionActual();
      listaCampo_local = ap.obtenerCamposTabla(manejadorRequest_local.obtenerNombreRecursoAplicacion(), pIdGrupoInformacion);
      asignarValoresRequestCamposGrupoInformacion(pRequest, listaCampo_local);
      
      switch (pIdGrupoInformacion) {
        case 1:
        case 10:
          nuevaAplicacion_local = conformarAplicacion(listaCampo_local, (pIdGrupoInformacion == 10), pAdministradorBaseDatosSisnet);
          
          estructurasAplicacion_local = pAdministradorBaseDatosSisnet.incluirAplicacion(nuevaAplicacion_local);
          if (estructurasAplicacion_local == 0) {
            estructurasAplicacion_local = crearEstructuraAplicacion(pRequest, nuevaAplicacion_local);
            manejadorSesion_local.obtenerMotorAplicacion().recargarAplicaciones();
          } 
          break;
        case 3:
          tabla_local = conformarTabla(listaCampo_local);
          estructurasAplicacion_local = pAdministradorBaseDatosSisnet.incluirTabla(tabla_local);
          if (estructurasAplicacion_local == 0) {
            estructurasAplicacion_local = crearEstructuraTabla(tabla_local, pAdministradorBaseDatosAplicacion);
          }
          break;
        case 2:
          grupoInformacion_local = conformarGrupoInformacion(pRequest, listaCampo_local, aplicacionActual_local, false);
          
          estructurasAplicacion_local = pAdministradorBaseDatosSisnet.incluirGrupoInformacion(grupoInformacion_local);
          if (estructurasAplicacion_local == 0) {
            estructurasAplicacion_local = crearEstructuraGrupoInformacion(grupoInformacion_local, pAdministradorBaseDatosAplicacion);
            
            manejadorSesion_local.obtenerMotorAplicacion().recargarGruposInformacionAplicacion(aplicacionActual_local.getIdAplicacion());
          } 
          break;
        case 4:
          grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(manejadorSesion_local.obtenerValorLlavePrimaria());
          
          campo_local = conformarCampo(listaCampo_local, grupoInformacion_local, false, pAdministradorBaseDatosSisnet);
          
          if (!grupoInformacion_local.esGrupoInformacionMultiple() && verificarDuplicidadCampoGruposNoMultiples(pRequest, aplicacionActual_local.getIdAplicacion(), campo_local.getNombreCampo(), pAdministradorBaseDatosSisnet))
          {
            estructurasAplicacion_local = 23505;
          }
          if (estructurasAplicacion_local == 0) {
            estructurasAplicacion_local = pAdministradorBaseDatosSisnet.incluirCampo(campo_local);
            if (estructurasAplicacion_local == 0) {
              campo_local = pAdministradorBaseDatosSisnet.obtenerCampoPorId(pAdministradorBaseDatosSisnet.obtenerIdUltimoRegistro("CAMPO", "fldidcampo", "", -1), false);
              
              asignarHabilitacionCampo(pRequest, campo_local);
              estructurasAplicacion_local = crearEstructuraCampo(campo_local, pAdministradorBaseDatosAplicacion);
              manejadorSesion_local.obtenerMotorAplicacion().adicionarCampoPorId(pAdministradorBaseDatosSisnet.obtenerIdUltimoRegistroIncluido("CAMPO", ap.conformarNombreCampoLlavePrimaria("CAMPO"), "", -1));
            } 
          } 
          break;
        
        case 9:
          tipoUsuario_local = conformarTipoUsuario(listaCampo_local);
          estructurasAplicacion_local = pAdministradorBaseDatosSisnet.incluirTipoUsuario(tipoUsuario_local);
          break;
        case 5:
          usuario_local = conformarUsuario(listaCampo_local, false, pAdministradorBaseDatosSisnet);
          estructurasAplicacion_local = pAdministradorBaseDatosSisnet.incluirUsuario(usuario_local);
          break;
      } 
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      tabla_local = null;
      usuario_local = null;
      listaCampo_local = null;
      nuevaAplicacion_local = null;
      tipoUsuario_local = null;
      grupoInformacion_local = null;
      manejadorRequest_local = null;
    } 
    
    return estructurasAplicacion_local;
  }
  private void asignarPlantillaUtilizarListaNavegacion(HttpServletRequest pRequest) {
    int plantillaUtilizar_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("FLDPLANTILLAUTILIZAR", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
      {
        plantillaUtilizar_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("FLDPLANTILLAUTILIZAR", manejadorSesion_local).toString());
      }
      
      manejadorSesion_local.actualizarPlantillaUtilizar(plantillaUtilizar_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private int cargarArchivosABaseDatos(HttpServletRequest pRequest, ListaCampo pListaCampo, int pValorLlavePrimaria) {
    int errorCargaArchivo_local = 0;
    String rutaArchivo_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return errorCargaArchivo_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return errorCargaArchivo_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        manejadorArchivos_local = new ManejadorArchivos();
        iterador_local = pListaCampo.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          rutaArchivo_local = pListaCampo.obtenerValorCampo(campo_local.getNombreCampo());
          if (manejadorArchivos_local.existeArchivo(rutaArchivo_local)) {
            errorCargaArchivo_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().cargarArchivoABaseDatos(campo_local, rutaArchivo_local, pValorLlavePrimaria);
          }
        } 
      } else {
        
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      rutaArchivo_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      manejadorArchivos_local = null;
    } 
    
    return errorCargaArchivo_local;
  }
  private void revisarCamposHabilitados(HttpServletRequest pRequest, ListaCampo pListaCampo) {
    ManejadorHabilitacionCampos manejadorHabilitacionCampos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorHabilitacionCampos_local = new ManejadorHabilitacionCampos();
      manejadorHabilitacionCampos_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
      manejadorHabilitacionCampos_local.setAdministradorBaseDatosAplicacion(manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion());
      iterador_local = pListaCampo.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getHabilitadoPor() != ConstantesGeneral.VALOR_NULO && 
          !manejadorHabilitacionCampos_local.verificarHabilitarCampo(pListaCampo, campo_local, manejadorSesion_local.obtenerValorLlavePrimariaAnterior(), manejadorSesion_local.obtenerValorLlavePrimaria(), false))
        {
          
          if (campo_local.esBorrarValorNoHabilitado()) {
            if (campo_local.esTipoDatoNumerico() || campo_local.esTipoDatoTabla()) {
              campo_local.setValorCampo(Integer.valueOf(0)); continue;
            } 
            if (campo_local.esTipoDatoTexto()) {
              campo_local.setValorCampo("");
            }
          }
        
        }
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      manejadorHabilitacionCampos_local = null;
    } 
  }
  private int incluirRegistroAplicacion(HttpServletRequest pRequest, boolean pEsRegistroPrincipal) {
    int registroAplicacion_local = -1;
    int idAplicacion_local = -1;
    int valorLlavePrimariaPrincipal_local = -1;
    int idUltimoRegistroIncluido_local = -1;
    boolean esConfiguracion_local = false;
    String condicionesAplicacion_local = null;
    ListaCampo listaCampo_local = null;
    ListaCampo listaCampoAplicacion_local = null;
    ListaCampo listaCampoArchivo_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    ManejadorInformacionRecalculable manejadorInformacionRecalculable_local = null;
    ListaCadenas listaAplicacionesActualizadas_local = null;
    ListaCadenas listaLlavesPrimarias_local = null;
    Aplicacion aplicacionActual_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      aplicacionActual_local = manejadorSesion_local.obtenerAplicacionActual();
      idAplicacion_local = aplicacionActual_local.getIdAplicacion();
      grupoInformacion_local = manejadorSesion_local.obtenerGrupoInformacionActual();
      esConfiguracion_local = manejadorSesion_local.esConfiguracion();
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
        asignarPlantillaUtilizarListaNavegacion(pRequest);
        if (esConfiguracion_local) {
          registroAplicacion_local = crearEstructurasAplicacion(pRequest, grupoInformacion_local.getIdGrupoInformacion(), administradorBaseDatosSisnet_local, administradorBaseDatosAplicacion_local);
          
          if (registroAplicacion_local != 0) {
            manejadorSesion_local.copiarAtributosRequestSesion(pRequest);
          }
        } else {
          valorLlavePrimariaPrincipal_local = -1;
          if (pEsRegistroPrincipal) {
            listaCampoAplicacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposGruposInformacionNoMultiples(idAplicacion_local);
          }
          else {
            
            listaCampoAplicacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposGrupoInformacion(grupoInformacion_local.getIdGrupoInformacion());
            
            valorLlavePrimariaPrincipal_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
          } 
          listaCampoArchivo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposTipoArchivoGrupoInformacion(grupoInformacion_local);
          
          listaCampo_local = new ListaCampo();
          
          listaCampo_local.concatenarListaCampo(listaCampoAplicacion_local);
          listaCampo_local.concatenarListaCampo(listaCampoArchivo_local);
          asignarValoresRequestCamposGrupoInformacion(pRequest, listaCampo_local);
          listaCampoAplicacion_local.copiarValoresListaCampoConsultaSQL(listaCampo_local);
          listaCampoArchivo_local.copiarValoresListaCampoConsultaSQL(listaCampo_local);
          
          manejadorInformacionRecalculable_local = new ManejadorInformacionRecalculable();
          manejadorInformacionRecalculable_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
          manejadorInformacionRecalculable_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
          manejadorInformacionRecalculable_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
          manejadorInformacionRecalculable_local.asignarValoresSemiautomaticosListaCampo(listaCampoAplicacion_local, manejadorSesion_local.obtenerValorLlavePrimariaAnterior());
          
          manejadorInformacionRecalculable_local.asignarValoresConsecutivosInternosListaCampo(listaCampoAplicacion_local, manejadorSesion_local.obtenerValorLlavePrimariaAnterior());
          
          revisarCamposHabilitados(pRequest, listaCampoAplicacion_local);
          
          manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(manejadorSesion_local.obtenerGrupoInformacionActual());
          
          manejadorSesion_local.obtenerManejadorEventos().setListaCampo(listaCampoAplicacion_local);
          if (pEsRegistroPrincipal) {
            manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("ACEPTARINCLUIRPRINCIPAL");
            
            manejadorSesion_local.obtenerManejadorEventos().setListaCampoValoresAnteriores(null);
          } else {
            manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("ACEPTARINCLUIR " + grupoInformacion_local.getDescripcionGrupoInformacion());
          } 
          
          manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(false);
          manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
          manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
          manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
          
          if (!manejadorSesion_local.obtenerManejadorEventos().esHuboError()) {
            registroAplicacion_local = administradorBaseDatosAplicacion_local.incluirRegistroAplicacion(grupoInformacion_local, valorLlavePrimariaPrincipal_local, listaCampoAplicacion_local);
          } else {
            
            manejadorSesion_local.actualizarRecargarPagina(true);
          } 
          manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
          
          manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
          
          if (registroAplicacion_local == 0) {
            idUltimoRegistroIncluido_local = administradorBaseDatosAplicacion_local.obtenerIdUltimoRegistroIncluido(grupoInformacion_local.getNombreGrupoInformacion(), ap.conformarNombreCampoLlavePrimaria(grupoInformacion_local.getNombreGrupoInformacion()), "", -1);
            
            if (grupoInformacion_local.esGrupoInformacionPrincipal()) {
              manejadorSesion_local.actualizarIdRegistroVisitado(idUltimoRegistroIncluido_local);
            }
            registroAplicacion_local = cargarArchivosABaseDatos(pRequest, listaCampoArchivo_local, idUltimoRegistroIncluido_local);
            
            if ((pEsRegistroPrincipal && administradorBaseDatosSisnet_local.verificarAplicacionTieneGruposMultiples(idAplicacion_local)) || administradorBaseDatosSisnet_local.verificarGrupoInformacionContieneCampoDocumento(grupoInformacion_local))
            {
              manejadorSesion_local.actualizarValorLlavePrimaria(idUltimoRegistroIncluido_local);
            }
            
            manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);
            if (pEsRegistroPrincipal) {
              manejadorInformacionRecalculable_local.actualizarInformacionRecalculableGrupoInformacion(grupoInformacion_local, idUltimoRegistroIncluido_local);
              
              manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoPrincipal(idUltimoRegistroIncluido_local);
              manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoInformacion(idUltimoRegistroIncluido_local);
            } else {
              condicionesAplicacion_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(grupoInformacion_local.getAplicacion().getNombreAplicacion()), " = " + valorLlavePrimariaPrincipal_local);
              
              manejadorInformacionRecalculable_local.actualizarInformacionRecalculableGrupoInformacion(grupoInformacion_local, valorLlavePrimariaPrincipal_local);
              
              manejadorInformacionRecalculable_local.actualizarInformacionRecalculableAplicacion(grupoInformacion_local.getAplicacion(), grupoInformacion_local.getAplicacion().getIdAplicacion(), condicionesAplicacion_local);
              
              if (grupoInformacion_local.getAplicacion().esHacerDobleCalculo()) {
                manejadorInformacionRecalculable_local.actualizarInformacionRecalculableAplicacion(grupoInformacion_local.getAplicacion(), grupoInformacion_local.getAplicacion().getIdAplicacion(), condicionesAplicacion_local);
              }
              
              manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoPrincipal(valorLlavePrimariaPrincipal_local);
              manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoInformacion(idUltimoRegistroIncluido_local);
            } 
            
            listaAplicacionesActualizadas_local = new ListaCadenas();
            listaAplicacionesActualizadas_local.adicionar(aplicacionActual_local.getNombreAplicacion());
            listaLlavesPrimarias_local = new ListaCadenas();
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              listaLlavesPrimarias_local.adicionar(String.valueOf(valorLlavePrimariaPrincipal_local));
            } else {
              listaLlavesPrimarias_local.adicionar(String.valueOf(idUltimoRegistroIncluido_local));
            } 
            actualizarAplicacionesRelacionadas(pRequest, aplicacionActual_local, listaAplicacionesActualizadas_local, listaLlavesPrimarias_local);
            
            manejadorInformacionRecalculable_local.asignarValoresConsultaRegistroCampos(grupoInformacion_local, listaCampoAplicacion_local, idUltimoRegistroIncluido_local);
            
            manejadorSesion_local.obtenerManejadorEventos().setListaCampo(listaCampoAplicacion_local);
            manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
            manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
            manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
            
            manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
            
            manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
          } else {
            
            manejadorSesion_local.copiarAtributosRequestSesion(pRequest);
          } 
        } 
      } 
      if (registroAplicacion_local == 0) {
        manejadorSesion_local.borrarAtributosCamposSesion();
        if (manejadorSesion_local.obtenerMotorAplicacion() != ConstantesGeneral.VALOR_NULO) {
          borrarValoresListaCampoMotorAplicacion(pRequest);
        }
        if (manejadorSesion_local.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
          manejadorSesion_local.actualizarAtributoListaAtributosRequestMultiparteNulo();
        }
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      listaCampo_local = null;
      manejadorSesion_local = null;
      grupoInformacion_local = null;
      manejadorRequest_local = null;
      aplicacionActual_local = null;
      listaCampoArchivo_local = null;
      listaLlavesPrimarias_local = null;
      listaCampoAplicacion_local = null;
      condicionesAplicacion_local = null;
      administradorBaseDatosSisnet_local = null;
      listaAplicacionesActualizadas_local = null;
      administradorBaseDatosAplicacion_local = null;
      manejadorInformacionRecalculable_local = null;
    } 
    return registroAplicacion_local;
  }
  private int incluirRegistroValorTabla(HttpServletRequest pRequest) {
    int registroValorTabla_local = 0;
    String valorTabla_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroValorTabla_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroValorTabla_local = 0;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorTabla_local = manejadorRequest_local.obtenerValorAtributoRequest("fldvalortabla", manejadorSesion_local).toString();
        
        registroValorTabla_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().incluirRegistroValorTabla(manejadorSesion_local.obtenerTablaActual().getNombreTabla(), valorTabla_local);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorTabla_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return registroValorTabla_local;
  }
  private int incluirRegistroValorDependiente(HttpServletRequest pRequest) {
    int registroValorDependiente_local = 0;
    int idCampo_local = -1;
    int valorMaestro_local = 0;
    int valorDetalle_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroValorDependiente_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroValorDependiente_local = 0;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        idCampo_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
        valorMaestro_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        valorDetalle_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidvalordetalle", manejadorSesion_local).toString());
        
        registroValorDependiente_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().insertarRegistroValorDependiente(idCampo_local, valorMaestro_local, valorDetalle_local);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return registroValorDependiente_local;
  }
  private int incluirRegistroDependienteHabilitacion(HttpServletRequest pRequest) {
    int registroDependienteHabilitacion_local = 0;
    int idCampo_local = -1;
    int valorMaestro_local = 0;
    boolean habilitado_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroDependienteHabilitacion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroDependienteHabilitacion_local = 0;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        idCampo_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
        valorMaestro_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        habilitado_local = (manejadorRequest_local.obtenerValorAtributoRequest("fldhabilitacion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO);
        
        registroDependienteHabilitacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().insertarRegistroDependienteHabilitacion(idCampo_local, valorMaestro_local, habilitado_local);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return registroDependienteHabilitacion_local;
  }
  private int modificarRegistroValorDependiente(HttpServletRequest pRequest) {
    int registroValorDependiente_local = 0;
    int idValorDetalle_local = 0;
    int idValorDependiente_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroValorDependiente_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroValorDependiente_local = 0;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        idValorDetalle_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidvalordetalle", manejadorSesion_local).toString());
        
        idValorDependiente_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        registroValorDependiente_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().modificarRegistroValorDependendencia(idValorDependiente_local, idValorDetalle_local);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return registroValorDependiente_local;
  }
  private int modificarRegistroDependienteHabilitacion(HttpServletRequest pRequest) {
    int dependienteHabilitacion_local = 0;
    int idDependienteHabilitacion_local = 0;
    boolean habilitado_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return dependienteHabilitacion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      dependienteHabilitacion_local = 0;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        habilitado_local = (manejadorRequest_local.obtenerValorAtributoRequest("fldhabilitacion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO);
        
        idDependienteHabilitacion_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        dependienteHabilitacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().modificarRegistroDependendienteHabilitacion(idDependienteHabilitacion_local, habilitado_local);
      }
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return dependienteHabilitacion_local;
  }
  private int modificarRegistroValorTabla(HttpServletRequest pRequest) {
    int registroValorTabla_local = 0;
    String valorTabla_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroValorTabla_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroValorTabla_local = 0;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorTabla_local = manejadorRequest_local.obtenerValorAtributoRequest("fldvalortabla", manejadorSesion_local).toString();
        
        registroValorTabla_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().modificarRegistroValorTabla(manejadorSesion_local.obtenerTablaActual().getNombreTabla(), manejadorSesion_local.obtenerValorLlavePrimaria(), valorTabla_local);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorTabla_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return registroValorTabla_local;
  }
  private void borrarRegistroValorTabla(HttpServletRequest pRequest) {
    int valorLlavePrimaria_local = 0;
    boolean error_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      error_local = false;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
        
        if (manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().borrarRegistroValorTabla(manejadorSesion_local.obtenerTablaActual().getNombreTabla(), valorLlavePrimaria_local) == 0) {
          
          manejadorSesion_local.actualizarNumeroPagina(16);
          manejadorSesion_local.actualizarNumeroError(0);
          manejadorSesion_local.actualizarTipoError(-1);
        } else {
          error_local = true;
        } 
        if (error_local) {
          manejadorSesion_local.actualizarNumeroPagina(16);
          manejadorSesion_local.actualizarNumeroError(15);
          manejadorSesion_local.actualizarTipoError(2);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void borrarRegistroValorDependiente(HttpServletRequest pRequest) {
    int valorLlavePrimaria_local = -1;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
        
        if (manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().borrarRegistroValorDependiente(valorLlavePrimaria_local) == 0) {
          
          manejadorSesion_local.actualizarNumeroPagina(18);
          manejadorSesion_local.actualizarNumeroError(0);
          manejadorSesion_local.actualizarTipoError(-1);
        } else {
          manejadorSesion_local.actualizarNumeroPagina(18);
          manejadorSesion_local.actualizarNumeroError(15);
          manejadorSesion_local.actualizarTipoError(2);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void borrarRegistroDependienteHabilitacion(HttpServletRequest pRequest) {
    int valorLlavePrimaria_local = -1;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
        
        if (manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().borrarRegistroDependienteHabilitacion(valorLlavePrimaria_local) == 0) {
          
          manejadorSesion_local.actualizarNumeroPagina(18);
          manejadorSesion_local.actualizarNumeroError(0);
          manejadorSesion_local.actualizarTipoError(-1);
        } else {
          manejadorSesion_local.actualizarNumeroPagina(18);
          manejadorSesion_local.actualizarNumeroError(15);
          manejadorSesion_local.actualizarTipoError(2);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private int borrarEstructuraTabla(int pIdAplicacionActual, int pValorLlavePrimaria, AdministradorBaseDatos pAdministradorBaseDatosSisnet, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int errorEjecucion_local = 0;
    String nombreTabla_local = null;
    String nombreLlavePrimariaTabla_local = null;
    Campo campo_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      nombreTabla_local = pAdministradorBaseDatosSisnet.obtenerTablaPorId(pValorLlavePrimaria).getNombreTabla();
      nombreLlavePrimariaTabla_local = ap.conformarNombreCampoLlavePrimaria(nombreTabla_local);
      errorEjecucion_local = pAdministradorBaseDatosAplicacion.borrarTabla(nombreTabla_local);
      listaCampo_local = pAdministradorBaseDatosSisnet.obtenerListaCamposMismoNombreEnAplicacion(mc.convertirAMayusculas(nombreLlavePrimariaTabla_local), pIdAplicacionActual);
      
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (errorEjecucion_local == 0) {
          errorEjecucion_local = pAdministradorBaseDatosAplicacion.borrarCampoBaseDatos(campo_local.getGrupoInformacion().getNombreGrupoInformacion(), nombreLlavePrimariaTabla_local);
        }
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      nombreTabla_local = null;
      listaCampo_local = null;
      nombreLlavePrimariaTabla_local = null;
    } 
    return errorEjecucion_local;
  }
  private int borrarEstructuraGrupoInformacion(HttpServletRequest pRequest, int pValorLlavePrimaria) {
    int errorEjecucion_local = 0;
    Campo campo_local = null;
    ListaCampo listaCampo_local = null;
    Iterator iterador_local = null;
    GrupoInformacion grupoInformacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
      administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
      errorEjecucion_local = 0;
      grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(pValorLlavePrimaria);
      if (!grupoInformacion_local.esGrupoInformacionMultiple()) {
        listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposGrupoInformacion(pValorLlavePrimaria);
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          if (errorEjecucion_local == 0) {
            errorEjecucion_local = administradorBaseDatosAplicacion_local.borrarCampoBaseDatos(grupoInformacion_local.getAplicacion().getNombreAplicacion(), campo_local.getNombreCampo());
          }
        } 
      } else {
        
        errorEjecucion_local = administradorBaseDatosAplicacion_local.borrarTabla(grupoInformacion_local.getNombreGrupoInformacion());
      } 
      if (errorEjecucion_local == 0) {
        errorEjecucion_local = administradorBaseDatosSisnet_local.borrarRegistrosCamposGrupoInformacion(pValorLlavePrimaria);
      }
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
      grupoInformacion_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return errorEjecucion_local;
  }
  private int borrarEstructuraCampo(int pValorLlavePrimaria, AdministradorBaseDatos pAdministradorBaseDatosSisnet, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int errorEjecucion_local = 0;
    String nombreGrupoInformacion_local = null;
    Campo campo_local = null;
    
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      errorEjecucion_local = 0;
      campo_local = pAdministradorBaseDatosSisnet.obtenerCampoPorId(pValorLlavePrimaria, false);
      nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getNombreGrupoInformacion();
      if (!campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
        nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
      }
      if (pAdministradorBaseDatosAplicacion.verificarExistenciaCampo(campo_local.getNombreCampo(), nombreGrupoInformacion_local)) {
        errorEjecucion_local = pAdministradorBaseDatosAplicacion.borrarCampoBaseDatos(nombreGrupoInformacion_local, campo_local.getNombreCampo());
        
        if (mc.sonCadenasIguales(campo_local.getFormatoCampo().getTipoDato(), "J") && errorEjecucion_local == 0)
        {
          errorEjecucion_local = pAdministradorBaseDatosAplicacion.borrarCampoBaseDatos(nombreGrupoInformacion_local, ap.complementarNombreCampoNombreArchivo(campo_local.getNombreCampo()));
        }
      }
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      nombreGrupoInformacion_local = null;
    } 
    return errorEjecucion_local;
  }
  private boolean verificarRegistroRelacionado(HttpServletRequest pRequest, Aplicacion pAplicacion, int pValorLlavePrimaria, AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    boolean registroRelacionado_local = false;
    String nombreLlaveGrupoPrincipal_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    ListaCampo listaCampo_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroRelacionado_local;
    }
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return registroRelacionado_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return registroRelacionado_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroRelacionado_local = false;
      listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposRelacionadosAplicacionEnlazada(pAplicacion.getIdAplicacion());
      iterador_local = listaCampo_local.iterator();
      while (iterador_local.hasNext() && !registroRelacionado_local) {
        campo_local = (Campo)iterador_local.next();
        nombreLlaveGrupoPrincipal_local = ap.conformarNombreCampoLlavePrimaria(campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion());
        registroRelacionado_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion().verificarValorRegistro(campo_local, nombreLlaveGrupoPrincipal_local, pValorLlavePrimaria);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
      nombreLlaveGrupoPrincipal_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    return registroRelacionado_local;
  }
  protected int borrarRegistroAplicacion(HttpServletRequest pRequest, boolean pEsRegistroPrincipal, boolean pRetornarModificacionPrincipal) {
    int errorEjecucion_local = -1;
    int valorLlavePrimaria_local = -1;
    int valorLlavePrimariaAnterior_local = -1;
    int idAplicacionActual_local = 0;
    int numeroError_local = 0;
    boolean error_local = false;
    boolean registroRelacionado_local = false;
    String nombreGrupoInformacionActual_local = null;
    String condicionesAplicacion_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    GrupoInformacion grupoInformacionBorrar_local = null;
    ManejadorInformacionRecalculable manejadorInformacionRecalculable_local = null;
    ListaCampo listaCampoValoresAnteriores_local = null;
    ListaCampo listaCampoGruposNoMultiples_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      registroRelacionado_local = false;
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        valorLlavePrimaria_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
        
        grupoInformacion_local = manejadorSesion_local.obtenerGrupoInformacionActual();
        idAplicacionActual_local = grupoInformacion_local.getAplicacion().getIdAplicacion();
        nombreGrupoInformacionActual_local = grupoInformacion_local.getNombreGrupoInformacion();
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
        if (manejadorSesion_local.esConfiguracion()) {
          if (mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "TABLA")) {
            errorEjecucion_local = borrarEstructuraTabla(idAplicacionActual_local, valorLlavePrimaria_local, administradorBaseDatosSisnet_local, administradorBaseDatosAplicacion_local);
          }
          
          if (mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "GRUPOINFORMACION")) {
            errorEjecucion_local = borrarEstructuraGrupoInformacion(pRequest, valorLlavePrimaria_local);
            if (errorEjecucion_local == 0) {
              grupoInformacionBorrar_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(valorLlavePrimaria_local);
              
              administradorBaseDatosSisnet_local.actualizarPosicionesGruposInformacionAplicacion(grupoInformacionBorrar_local);
            } 
          } 
          if (mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "CAMPO")) {
            errorEjecucion_local = borrarEstructuraCampo(valorLlavePrimaria_local, administradorBaseDatosSisnet_local, administradorBaseDatosAplicacion_local);
            
            if (errorEjecucion_local == 0) {
              administradorBaseDatosSisnet_local.actualizarPosicionesCamposGrupoInformacion(manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(valorLlavePrimaria_local));
              
              administradorBaseDatosSisnet_local.borrarRegistrosValoresDependientesCampo(valorLlavePrimaria_local);
              administradorBaseDatosSisnet_local.borrarRegistrosDependientesHabilitacionCampo(valorLlavePrimaria_local);
              manejadorSesion_local.obtenerMotorAplicacion().reorganizarPosicionesCampos(manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(valorLlavePrimaria_local));
            } 
          } 
          
          if (mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "USUARIO") || mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "TIPOUSUARIO"))
          {
            errorEjecucion_local = 0;
          }
          if (errorEjecucion_local == 0) {
            errorEjecucion_local = administradorBaseDatosSisnet_local.borrarRegistroAplicacion(grupoInformacion_local, valorLlavePrimaria_local);
            
            if (errorEjecucion_local != 0) {
              error_local = true;
            } else {
              if (mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "GRUPOINFORMACION")) {
                manejadorSesion_local.obtenerMotorAplicacion().recargarGruposInformacionAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
              }
              
              if (mc.sonCadenasIguales(nombreGrupoInformacionActual_local, "CAMPO")) {
                manejadorSesion_local.obtenerMotorAplicacion().borrarCampoPorId(valorLlavePrimaria_local);
              }
            } 
          } 
        } else {
          valorLlavePrimariaAnterior_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
          manejadorInformacionRecalculable_local = new ManejadorInformacionRecalculable();
          manejadorInformacionRecalculable_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
          manejadorInformacionRecalculable_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
          manejadorInformacionRecalculable_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
          if (pEsRegistroPrincipal) {
            listaCampoValoresAnteriores_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCopiaListaCamposGruposInformacionNoMultiplesConArchivos(idAplicacionActual_local);
            
            valorLlavePrimariaAnterior_local = valorLlavePrimaria_local;
          } else {
            listaCampoValoresAnteriores_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCopiaListaCamposGrupoInformacionConArchivos(grupoInformacion_local.getIdGrupoInformacion());
            
            listaCampoGruposNoMultiples_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCopiaListaCamposGruposInformacionNoMultiplesConArchivos(idAplicacionActual_local);
            
            manejadorInformacionRecalculable_local.asignarValoresConsultaRegistroCampos(manejadorInformacionRecalculable_local.getMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(idAplicacionActual_local), listaCampoGruposNoMultiples_local, valorLlavePrimariaAnterior_local);
            
            manejadorSesion_local.obtenerManejadorEventos().setListaCampo(listaCampoValoresAnteriores_local);
          } 
          manejadorInformacionRecalculable_local.asignarValoresConsultaRegistroCampos(grupoInformacion_local, listaCampoValoresAnteriores_local, valorLlavePrimaria_local);
          
          if (!pEsRegistroPrincipal) {
            listaCampoGruposNoMultiples_local.concatenar(listaCampoValoresAnteriores_local);
            listaCampoValoresAnteriores_local = listaCampoGruposNoMultiples_local;
          } else {
            manejadorSesion_local.obtenerManejadorEventos().setListaCampo(listaCampoValoresAnteriores_local);
          } 
          manejadorSesion_local.obtenerManejadorEventos().setListaCampoValoresAnteriores(listaCampoValoresAnteriores_local);
          manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(grupoInformacion_local);
          manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoInformacion(valorLlavePrimaria_local);
          if (pEsRegistroPrincipal) {
            manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("BORRARPRINCIPAL");
            
            manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoPrincipal(valorLlavePrimaria_local);
          } else {
            manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("BORRAR " + grupoInformacion_local.getDescripcionGrupoInformacion());
            
            manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoPrincipal(valorLlavePrimariaAnterior_local);
          } 
          
          manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(false);
          manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
          manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
          manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
          
          manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
          
          manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
          
          if (!manejadorSesion_local.obtenerManejadorEventos().esHuboError()) {
            if (administradorBaseDatosSisnet_local.obtenerNumeroGruposInformacionAplicacion(idAplicacionActual_local) == 1)
            {
              registroRelacionado_local = (manejadorSesion_local.obtenerMotorAplicacion().verificarEsAplicacionEnlazada(idAplicacionActual_local) && verificarRegistroRelacionado(pRequest, grupoInformacion_local.getAplicacion(), valorLlavePrimaria_local, administradorBaseDatosSisnet_local));
            }
            
            error_local = registroRelacionado_local;
            if (!registroRelacionado_local) {
              errorEjecucion_local = administradorBaseDatosAplicacion_local.borrarRegistroAplicacion(grupoInformacion_local, valorLlavePrimaria_local);
              
              manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
              
              manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
              
              error_local = (errorEjecucion_local != 0);
              if (!error_local) {
                manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);
                if (pEsRegistroPrincipal) {
                  manejadorSesion_local.actualizarNumeroPagina(12);
                } else {
                  if (pRetornarModificacionPrincipal) {
                    errorEjecucion_local = 14;
                    manejadorSesion_local.actualizarNumeroPagina(14);
                  } else {
                    manejadorSesion_local.actualizarNumeroPagina(15);
                  } 
                  manejadorInformacionRecalculable_local.reorganizarValoresConsecutivoInternoRegistro(listaCampoValoresAnteriores_local, valorLlavePrimariaAnterior_local);
                  
                  manejadorInformacionRecalculable_local.actualizarInformacionRecalculableGrupoInformacion(grupoInformacion_local, valorLlavePrimariaAnterior_local);
                  
                  condicionesAplicacion_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(grupoInformacion_local.getAplicacion().getNombreAplicacion()), " = " + valorLlavePrimariaAnterior_local);
                  
                  manejadorInformacionRecalculable_local.actualizarInformacionRecalculableAplicacion(grupoInformacion_local.getAplicacion(), grupoInformacion_local.getAplicacion().getIdAplicacion(), condicionesAplicacion_local);
                } 
                
                manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
                manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
                manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
                
                manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
                
                manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
                
                manejadorSesion_local.actualizarNumeroError(0);
                manejadorSesion_local.actualizarTipoError(-1);
                if (manejadorSesion_local.obtenerMotorAplicacion() != ConstantesGeneral.VALOR_NULO) {
                  borrarValoresListaCampoMotorAplicacion(pRequest);
                }
                if (manejadorSesion_local.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
                  manejadorSesion_local.actualizarAtributoListaAtributosRequestMultiparteNulo();
                }
              } 
            } 
          } 
        } 
        if (error_local) {
          numeroError_local = 16;
          if (registroRelacionado_local) {
            errorEjecucion_local = 17;
            numeroError_local = 17;
          } 
          manejadorSesion_local.actualizarNumeroError(numeroError_local);
          manejadorSesion_local.actualizarTipoError(2);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      listaCampoValoresAnteriores_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      grupoInformacion_local = null;
      condicionesAplicacion_local = null;
      grupoInformacionBorrar_local = null;
      administradorBaseDatosSisnet_local = null;
      nombreGrupoInformacionActual_local = null;
      listaCampoGruposNoMultiples_local = null;
      administradorBaseDatosAplicacion_local = null;
      manejadorInformacionRecalculable_local = null;
    } 
    return errorEjecucion_local;
  }
  private int modificarEstructuraCampo(Campo pCampo, AdministradorBaseDatos pAdministradorBaseDatosSisnet, AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    int errorEjecucion_local = 0;
    String nombreIndice_local = null;
    String nombreCampoIndice_local = null;
    String nombreGrupoInformacion_local = null;
    Campo campo_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAdministradorBaseDatosSisnet == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    if (pAdministradorBaseDatosAplicacion == ConstantesGeneral.VALOR_NULO) {
      return errorEjecucion_local;
    }
    
    try {
      errorEjecucion_local = 0;
      campo_local = pAdministradorBaseDatosSisnet.obtenerCampoPorId(pCampo.getIdCampo(), false);
      if (campo_local.getFormatoCampo().esValorUnico() != pCampo.getFormatoCampo().esValorUnico()) {
        if (pCampo.getGrupoInformacion().esGrupoInformacionMultiple()) {
          nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getNombreGrupoInformacion();
          nombreCampoIndice_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion()), String.valueOf(',') + pCampo.getNombreCampo());
        }
        else {
          
          nombreGrupoInformacion_local = pCampo.getGrupoInformacion().getAplicacion().getNombreAplicacion();
          nombreCampoIndice_local = pCampo.getNombreCampo();
        } 
        nombreIndice_local = mc.concatenarCadena("idx", nombreGrupoInformacion_local + campo_local.getNombreCampo());
        
        if (pCampo.getFormatoCampo().esValorUnico()) {
          errorEjecucion_local = pAdministradorBaseDatosAplicacion.crearIndice(nombreIndice_local, nombreGrupoInformacion_local, nombreCampoIndice_local);
        
        }
        else if (pAdministradorBaseDatosAplicacion.verificarExistenciaIndice(pCampo)) {
          errorEjecucion_local = pAdministradorBaseDatosAplicacion.borrarIndice(nombreIndice_local);
        }
      
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campo_local = null;
      nombreIndice_local = null;
      nombreCampoIndice_local = null;
      nombreGrupoInformacion_local = null;
    } 
    return errorEjecucion_local;
  }
  private String conformarConsultaSQLValoresLlavesPrimarias(String pNombreCampoLlavePrimariaAplicacion, String pNombreCampoEnlazado, String pNombreGrupoInformacion, ListaCadenas pListaLlavesPrimarias) {
    String consultaSQL_local = null;
    String concatenacionValoresLlavesPrimarias_local = null;
    
    if (pNombreCampoLlavePrimariaAplicacion == ConstantesGeneral.VALOR_NULO) {
      return consultaSQL_local;
    }
    if (pNombreCampoEnlazado == ConstantesGeneral.VALOR_NULO) {
      return consultaSQL_local;
    }
    if (pNombreGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return consultaSQL_local;
    }
    if (pListaLlavesPrimarias == ConstantesGeneral.VALOR_NULO) {
      return consultaSQL_local;
    }
    
    try {
      concatenacionValoresLlavesPrimarias_local = pListaLlavesPrimarias.concantenarValoresConComas();
      consultaSQL_local = "select " + pNombreCampoLlavePrimariaAplicacion + " from " + pNombreGrupoInformacion + " where " + pNombreCampoEnlazado + " in " + mc.colocarEntreParentesis(concatenacionValoresLlavesPrimarias_local);
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      concatenacionValoresLlavesPrimarias_local = null;
    } 
    
    return consultaSQL_local;
  }
  private ListaCadenas actualizarRegistrosAplicacionRelacionada(HttpServletRequest pRequest, Aplicacion pAplicacionActualizar, int pIdAplicacionActual, ListaCadenas pListaLlavesPrimarias, ListaCampo pListaCamposEnlazados) {
    ListaCadenas listaLlavesPrimarias_local = null;
    Iterator iterator_local = null;
    int valorLlavePrimaria_local = -1;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    ManejadorInformacionRecalculable manejadorInformacionRecalculable_local = null;
    String condiciones_local = null;
    ManejadorResultadoConsultaSQL manejadorResultadoConsultaSQL_local = null;
    String consultaSQL_local = null;
    ResultSet resultSet_local = null;
    String nombreGrupoInformacion_local = null;
    Campo campo_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return listaLlavesPrimarias_local;
    }
    if (pAplicacionActualizar == ConstantesGeneral.VALOR_NULO) {
      return listaLlavesPrimarias_local;
    }
    if (pListaLlavesPrimarias == ConstantesGeneral.VALOR_NULO) {
      return listaLlavesPrimarias_local;
    }
    if (pListaCamposEnlazados == ConstantesGeneral.VALOR_NULO) {
      return listaLlavesPrimarias_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      
      manejadorResultadoConsultaSQL_local = new ManejadorResultadoConsultaSQL();
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosAplicacion(manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion());
      manejadorResultadoConsultaSQL_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
      
      listaLlavesPrimarias_local = new ListaCadenas();
      
      iterator_local = pListaCamposEnlazados.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        
        if (campo_local.getGrupoInformacion().esGrupoInformacionMultiple()) {
          nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getNombreGrupoInformacion();
        } else {
          nombreGrupoInformacion_local = campo_local.getGrupoInformacion().getAplicacion().getNombreAplicacion();
        } 
        
        consultaSQL_local = conformarConsultaSQLValoresLlavesPrimarias(ap.conformarNombreCampoLlavePrimaria(pAplicacionActualizar.getNombreAplicacion()), campo_local.getNombreCampo(), nombreGrupoInformacion_local, pListaLlavesPrimarias);
        
        resultSet_local = manejadorResultadoConsultaSQL_local.obtenerResultadoConsultaAplicacion(consultaSQL_local);
        if (resultSet_local != ConstantesGeneral.VALOR_NULO) {
          while (resultSet_local.next()) {
            valorLlavePrimaria_local = resultSet_local.getInt(ap.conformarNombreCampoLlavePrimaria(pAplicacionActualizar.getNombreAplicacion()));
            
            if (!listaLlavesPrimarias_local.verificarExistenciaCadena(String.valueOf(valorLlavePrimaria_local))) {
              listaLlavesPrimarias_local.adicionar(String.valueOf(valorLlavePrimaria_local));
            }
          } 
        }
      } 
      
      manejadorInformacionRecalculable_local = new ManejadorInformacionRecalculable();
      manejadorInformacionRecalculable_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
      manejadorInformacionRecalculable_local.setAdministradorBaseDatosAplicacion(manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion());
      manejadorInformacionRecalculable_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
      
      iterator_local = null;
      iterator_local = listaLlavesPrimarias_local.iterator();
      while (iterator_local.hasNext()) {
        valorLlavePrimaria_local = Integer.parseInt((String)iterator_local.next());
        condiciones_local = ap.conformarNombreCampoLlavePrimaria(pAplicacionActualizar.getNombreAplicacion()) + '=' + valorLlavePrimaria_local;
        
        manejadorInformacionRecalculable_local.actualizarInformacionRecalculableAplicacion(pAplicacionActualizar, pIdAplicacionActual, condiciones_local);
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      iterator_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      manejadorInformacionRecalculable_local = null;
      condiciones_local = null;
      manejadorResultadoConsultaSQL_local = null;
      consultaSQL_local = null;
      resultSet_local = null;
      nombreGrupoInformacion_local = null;
      campo_local = null;
    } 
    
    return listaLlavesPrimarias_local;
  }
  private void actualizarAplicacionesRelacionadas(HttpServletRequest pRequest, Aplicacion pAplicacion, ListaCadenas pListaAplicacionesActualizadas, ListaCadenas pListaLlavesPrimarias) {
    String tituloAplicacion_local = null;
    ListaCadenas listaAplicacionesActualizar_local = null;
    Iterator iterador_local = null;
    Aplicacion aplicacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaAplicacionesActualizadas == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaLlavesPrimarias == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      listaAplicacionesActualizar_local = mc.obtenerParrafoComoListaCadenas(pAplicacion.getAplicacionesActualizar());
      if (listaAplicacionesActualizar_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaAplicacionesActualizar_local.iterator();
        while (iterador_local.hasNext()) {
          tituloAplicacion_local = mc.convertirAMayusculas((String)iterador_local.next());
          aplicacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerAplicacionPorTitulo(tituloAplicacion_local);
          if (aplicacion_local != ConstantesGeneral.VALOR_NULO && !pListaAplicacionesActualizadas.verificarExistenciaCadena(aplicacion_local.getNombreAplicacion()))
          {
            pListaAplicacionesActualizadas.adicionar(aplicacion_local.getNombreAplicacion());
            actualizarRegistrosAplicacionRelacionada(pRequest, aplicacion_local, pAplicacion.getIdAplicacion(), pListaLlavesPrimarias, manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposEnlazados(aplicacion_local.getIdAplicacion(), pAplicacion.getIdAplicacion()));
            
            actualizarAplicacionesRelacionadas(pRequest, aplicacion_local, pListaAplicacionesActualizadas, pListaLlavesPrimarias);
          }
        
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      iterador_local = null;
      aplicacion_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      tituloAplicacion_local = null;
      listaAplicacionesActualizar_local = null;
    } 
  }
  private int modificarRegistroAplicacion(HttpServletRequest pRequest, boolean pEsRegistroPrincipal, boolean pEsDocumento) {
    int registroAplicacion_local = -1;
    int idGrupoInformacion_local = -1;
    int valorLlavePrimaria_local = -1;
    int valorLlavePrimariaAnterior_local = -1;
    ListaCampo listaCampo_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    ListaCampo listaCamposModificables_local = null;
    ListaCampo listaCamposConArchivos_local = null;
    ListaCampo listaCampoArchivo_local = null;
    ManejadorInformacionRecalculable manejadorInformacionRecalculable_local = null;
    ManejadorPermisoUsuario manejadorPermisoUsuario_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Aplicacion aplicacionActual_local = null;
    Aplicacion aplicacion_local = null;
    Tabla tabla_local = null;
    GrupoInformacion nuevoGrupoInformacion_local = null;
    Campo campo_local = null;
    TipoUsuario tipoUsuario_local = null;
    Usuario usuario_local = null;
    ListaCadenas listaAplicacionesActualizadas_local = null;
    String condicionesAplicacion_local = null;
    ListaCadenas listaLlavesPrimarias_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return registroAplicacion_local;
    }
    
    try {
      condicionesAplicacion_local = "";
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      aplicacionActual_local = manejadorSesion_local.obtenerAplicacionActual();
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        asignarPlantillaUtilizarListaNavegacion(pRequest);
        grupoInformacion_local = manejadorSesion_local.obtenerGrupoInformacionActual();
        idGrupoInformacion_local = grupoInformacion_local.getIdGrupoInformacion();
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
        valorLlavePrimaria_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        valorLlavePrimariaAnterior_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
        if (manejadorSesion_local.esConfiguracion()) {
          listaCampo_local = ap.obtenerCamposTabla(manejadorRequest_local.obtenerNombreRecursoAplicacion(), idGrupoInformacion_local);
          asignarValoresRequestCamposGrupoInformacion(pRequest, listaCampo_local);
          
          switch (idGrupoInformacion_local) {
            case 1:
            case 10:
              aplicacion_local = conformarAplicacion(listaCampo_local, (idGrupoInformacion_local == 10), administradorBaseDatosSisnet_local);
              
              registroAplicacion_local = administradorBaseDatosSisnet_local.modificarAplicacion(aplicacion_local);
              if (registroAplicacion_local == 0) {
                manejadorSesion_local.obtenerMotorAplicacion().recargarAplicaciones();
              }
              break;
            case 11:
              aplicacion_local = manejadorSesion_local.obtenerAplicacionActual();
              aplicacion_local.setTipoEventosUsuario(listaCampo_local.obtenerValorCampo("fldtipoeventosusuario"));
              
              aplicacion_local.setEventosAcciones(listaCampo_local.obtenerValorCampo("fldeventosacciones"));
              
              registroAplicacion_local = administradorBaseDatosSisnet_local.modificarEventosAccionesAplicacion(aplicacion_local);
              if (registroAplicacion_local == 0) {
                manejadorSesion_local.obtenerMotorAplicacion().recargarAplicaciones();
                manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(manejadorSesion_local.obtenerGrupoInformacionActual());
              } 
              break;
            
            case 3:
              tabla_local = conformarTabla(listaCampo_local);
              registroAplicacion_local = administradorBaseDatosSisnet_local.modificarTabla(tabla_local);
              break;
            case 2:
              nuevoGrupoInformacion_local = conformarGrupoInformacion(pRequest, listaCampo_local, manejadorSesion_local.obtenerAplicacionActual(), true);
              
              registroAplicacion_local = administradorBaseDatosSisnet_local.modificarGrupoInformacion(nuevoGrupoInformacion_local);
              if (registroAplicacion_local == 0) {
                if (nuevoGrupoInformacion_local.esGrupoInformacionMultiple() && !administradorBaseDatosAplicacion_local.verificarExistenciaLlaveForanea(nuevoGrupoInformacion_local.getNombreGrupoInformacion(), nuevoGrupoInformacion_local.getAplicacion().getNombreAplicacion())) {
                  
                  administradorBaseDatosAplicacion_local.borrarRegistrosGrupoInformacionMultiple(nuevoGrupoInformacion_local);
                  administradorBaseDatosAplicacion_local.crearRestriccionLlaveForanea(nuevoGrupoInformacion_local.getNombreGrupoInformacion(), ap.conformarNombreCampoLlavePrimaria(nuevoGrupoInformacion_local.getAplicacion().getNombreAplicacion()), nuevoGrupoInformacion_local.getAplicacion().getNombreAplicacion(), ap.conformarNombreCampoLlavePrimaria(nuevoGrupoInformacion_local.getAplicacion().getNombreAplicacion()), mc.concatenarCadena(nuevoGrupoInformacion_local.getNombreGrupoInformacion(), nuevoGrupoInformacion_local.getAplicacion().getNombreAplicacion()));
                } 
                
                manejadorSesion_local.obtenerMotorAplicacion().recargarGruposInformacionAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
              } 
              break;
            
            case 4:
              campo_local = conformarCampo(listaCampo_local, manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(valorLlavePrimariaAnterior_local), true, administradorBaseDatosSisnet_local);
              
              registroAplicacion_local = modificarEstructuraCampo(campo_local, administradorBaseDatosSisnet_local, administradorBaseDatosAplicacion_local);
              
              if (registroAplicacion_local == 0) {
                registroAplicacion_local = administradorBaseDatosSisnet_local.modificarCampo(campo_local);
                manejadorSesion_local.obtenerMotorAplicacion().modificarCampoPorId(valorLlavePrimaria_local);
              } 
              break;
            case 9:
              tipoUsuario_local = conformarTipoUsuario(listaCampo_local);
              registroAplicacion_local = administradorBaseDatosSisnet_local.modificarTipoUsuario(tipoUsuario_local);
              break;
            case 5:
              usuario_local = conformarUsuario(listaCampo_local, true, administradorBaseDatosSisnet_local);
              
              registroAplicacion_local = administradorBaseDatosSisnet_local.modificarUsuario(usuario_local);
              break;
          } 
          if (registroAplicacion_local == 0) {
            abrirPaginaConsultaGeneralGrupoInformacion(pRequest);
            manejadorSesion_local.borrarAtributosCamposSesion();
          } else {
            manejadorSesion_local.copiarAtributosRequestSesion(pRequest);
          } 
        } else {
          if (pEsRegistroPrincipal) {
            if (pEsDocumento) {
              listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposTipoDocumentoGrupoInformacion(grupoInformacion_local);
            } else {
              
              listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposGruposInformacionNoMultiples(grupoInformacion_local.getAplicacion().getIdAplicacion());
            }
          
          }
          else if (!pEsDocumento) {
            listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposGrupoInformacion(idGrupoInformacion_local);
          } else {
            
            listaCampo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposTipoDocumentoGrupoInformacion(grupoInformacion_local);
          } 
          
          listaCampoArchivo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerListaCamposTipoArchivoGrupoInformacion(grupoInformacion_local);
          
          listaCamposConArchivos_local = new ListaCampo();
          listaCamposConArchivos_local.concatenarListaCampo(listaCampo_local);
          listaCamposConArchivos_local.concatenarListaCampo(listaCampoArchivo_local);
          asignarValoresRequestCamposGrupoInformacion(pRequest, listaCamposConArchivos_local);
          listaCampo_local.copiarValoresListaCampoConsultaSQL(listaCamposConArchivos_local);
          listaCampoArchivo_local.copiarValoresListaCampoConsultaSQL(listaCamposConArchivos_local);
          
          manejadorPermisoUsuario_local = new ManejadorPermisoUsuario(manejadorSesion_local.obtenerTipoUsuarioActual());
          manejadorPermisoUsuario_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
          manejadorPermisoUsuario_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
          
          listaCamposModificables_local = manejadorPermisoUsuario_local.extraerCamposPermisoModificar(listaCampo_local);
          revisarCamposHabilitados(pRequest, listaCamposModificables_local);
          manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(grupoInformacion_local);
          manejadorSesion_local.obtenerManejadorEventos().setListaCampo(listaCamposModificables_local);
          if (pEsRegistroPrincipal) {
            manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoPrincipal(valorLlavePrimaria_local);
            manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoInformacion(valorLlavePrimaria_local);
            if (pEsDocumento) {
              manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("ACEPTARMODIFICARDOCUMENTOPRINCIPAL");
            } else {
              
              manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("ACEPTARMODIFICARPRINCIPAL");
            } 
          } else {
            
            manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoPrincipal(valorLlavePrimariaAnterior_local);
            
            manejadorSesion_local.obtenerManejadorEventos().setValorLlaveGrupoInformacion(valorLlavePrimaria_local);
            
            if (pEsDocumento) {
              manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("ACEPTARMODIFICARDOCUMENTO " + grupoInformacion_local.getDescripcionGrupoInformacion());
            }
            else {
              
              manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("ACEPTARMODIFICAR " + grupoInformacion_local.getDescripcionGrupoInformacion());
            } 
          } 
          
          manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(false);
          manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
          manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
          manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
          
          if (!manejadorSesion_local.obtenerManejadorEventos().esHuboError()) {
            registroAplicacion_local = administradorBaseDatosAplicacion_local.modificarRegistroAplicacion(grupoInformacion_local, listaCamposModificables_local, valorLlavePrimaria_local);
          } else {
            
            manejadorSesion_local.actualizarRecargarPagina(true);
          } 
          manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
          
          manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
          
          manejadorInformacionRecalculable_local = new ManejadorInformacionRecalculable();
          manejadorInformacionRecalculable_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
          manejadorInformacionRecalculable_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
          manejadorInformacionRecalculable_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
          
          if (registroAplicacion_local != 0) {
            manejadorSesion_local.copiarAtributosRequestSesion(pRequest);
          } else {
            registroAplicacion_local = cargarArchivosABaseDatos(pRequest, listaCampoArchivo_local, valorLlavePrimaria_local);
            
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              manejadorInformacionRecalculable_local.reorganizarValoresConsecutivoInternoRegistro(listaCamposModificables_local, valorLlavePrimariaAnterior_local);
              
              condicionesAplicacion_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(grupoInformacion_local.getAplicacion().getNombreAplicacion()), " = " + valorLlavePrimariaAnterior_local);
              
              manejadorInformacionRecalculable_local.actualizarInformacionRecalculableGrupoInformacion(grupoInformacion_local, valorLlavePrimariaAnterior_local);
            } else {
              
              condicionesAplicacion_local = mc.concatenarCadena(ap.conformarNombreCampoLlavePrimaria(grupoInformacion_local.getAplicacion().getNombreAplicacion()), " = " + valorLlavePrimaria_local);
              
              manejadorInformacionRecalculable_local.actualizarInformacionRecalculableGrupoInformacion(grupoInformacion_local, valorLlavePrimaria_local);
            } 
            
            manejadorInformacionRecalculable_local.actualizarInformacionRecalculableAplicacion(grupoInformacion_local.getAplicacion(), grupoInformacion_local.getAplicacion().getIdAplicacion(), condicionesAplicacion_local);
            
            if (grupoInformacion_local.getAplicacion().esHacerDobleCalculo()) {
              manejadorInformacionRecalculable_local.actualizarInformacionRecalculableAplicacion(grupoInformacion_local.getAplicacion(), grupoInformacion_local.getAplicacion().getIdAplicacion(), condicionesAplicacion_local);
            }
            
            listaAplicacionesActualizadas_local = new ListaCadenas();
            listaAplicacionesActualizadas_local.adicionar(aplicacionActual_local.getNombreAplicacion());
            listaLlavesPrimarias_local = new ListaCadenas();
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              listaLlavesPrimarias_local.adicionar(String.valueOf(valorLlavePrimariaAnterior_local));
            } else {
              listaLlavesPrimarias_local.adicionar(String.valueOf(valorLlavePrimaria_local));
            } 
            actualizarAplicacionesRelacionadas(pRequest, aplicacionActual_local, listaAplicacionesActualizadas_local, listaLlavesPrimarias_local);
            
            manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);
            
            manejadorInformacionRecalculable_local.asignarValoresConsultaRegistroCampos(grupoInformacion_local, listaCamposModificables_local, manejadorSesion_local.obtenerValorLlavePrimaria());
            
            manejadorSesion_local.obtenerManejadorEventos().setListaCampo(listaCamposModificables_local);
            manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
            manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
            manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
            
            manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
            
            manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
          } 
        } 
        
        if (registroAplicacion_local == 0) {
          manejadorSesion_local.borrarAtributosCamposSesion();
          if (manejadorSesion_local.obtenerMotorAplicacion() != ConstantesGeneral.VALOR_NULO) {
            borrarValoresListaCampoMotorAplicacion(pRequest);
          }
          if (manejadorSesion_local.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
            manejadorSesion_local.actualizarAtributoListaAtributosRequestMultiparteNulo();
          }
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      tabla_local = null;
      campo_local = null;
      usuario_local = null;
      aplicacion_local = null;
      listaCampo_local = null;
      tipoUsuario_local = null;
      manejadorSesion_local = null;
      aplicacionActual_local = null;
      manejadorRequest_local = null;
      grupoInformacion_local = null;
      listaCampoArchivo_local = null;
      condicionesAplicacion_local = null;
      nuevoGrupoInformacion_local = null;
      listaCamposConArchivos_local = null;
      listaCamposModificables_local = null;
      manejadorPermisoUsuario_local = null;
      administradorBaseDatosSisnet_local = null;
      listaAplicacionesActualizadas_local = null;
      administradorBaseDatosAplicacion_local = null;
      manejadorInformacionRecalculable_local = null;
    } 
    return registroAplicacion_local;
  }
  private void decrementarPosicionGrupoInformacion(HttpServletRequest pRequest) {
    int idGrupoInformacionActual_local = -1;
    int idGrupoInformacionAnterior_local = -1;
    int posicionGrupoInformacionActual_local = -1;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatos_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        idGrupoInformacionActual_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        posicionGrupoInformacionActual_local = administradorBaseDatos_local.obtenerPosicionGrupoInformacion(idGrupoInformacionActual_local);
        idGrupoInformacionAnterior_local = administradorBaseDatos_local.obtenerIdGrupoInformacionAnterior(posicionGrupoInformacionActual_local, manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
        
        administradorBaseDatos_local.actualizarGrupoInformacionDecrementar(idGrupoInformacionActual_local);
        administradorBaseDatos_local.actualizarGrupoInformacionIncrementar(idGrupoInformacionAnterior_local);
        manejadorSesion_local.obtenerMotorAplicacion().recargarGruposInformacionAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      administradorBaseDatos_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void incrementarPosicionGrupoInformacion(HttpServletRequest pRequest) {
    int idGrupoInformacionActual_local = -1;
    int idGrupoInformacionSiguiente_local = -1;
    int posicionGrupoInformacionActual_local = -1;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatos_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        idGrupoInformacionActual_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        posicionGrupoInformacionActual_local = administradorBaseDatos_local.obtenerPosicionGrupoInformacion(idGrupoInformacionActual_local);
        idGrupoInformacionSiguiente_local = administradorBaseDatos_local.obtenerIdGrupoInformacionSiguiente(posicionGrupoInformacionActual_local, manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
        
        administradorBaseDatos_local.actualizarGrupoInformacionIncrementar(idGrupoInformacionActual_local);
        administradorBaseDatos_local.actualizarGrupoInformacionDecrementar(idGrupoInformacionSiguiente_local);
        manejadorSesion_local.obtenerMotorAplicacion().recargarGruposInformacionAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      administradorBaseDatos_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void decrementarPosicionCampo(HttpServletRequest pRequest) {
    int idCampoActual_local = -1;
    int idGrupoInformacion_local = -1;
    int posicionCampoActual_local = -1;
    int idCampoAnterior_local = -1;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatos_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        idCampoActual_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        idGrupoInformacion_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
        posicionCampoActual_local = administradorBaseDatos_local.obtenerPosicionCampo(idCampoActual_local);
        idCampoAnterior_local = administradorBaseDatos_local.obtenerIdCampoAnterior(posicionCampoActual_local, idGrupoInformacion_local);
        administradorBaseDatos_local.actualizarCampoDecrementar(idCampoActual_local);
        administradorBaseDatos_local.actualizarCampoIncrementar(idCampoAnterior_local);
        manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(idCampoActual_local).decrementarPosicion();
        manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(idCampoAnterior_local).incrementarPosicion();
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      administradorBaseDatos_local = null;
    } 
  }
  private void incrementarPosicionCampo(HttpServletRequest pRequest) {
    int idCampoActual_local = -1;
    int idGrupoInformacion_local = -1;
    int posicionCampoActual_local = -1;
    int idCampoSiguiente_local = -1;
    AdministradorBaseDatos administradorBaseDatos_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatos_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        idCampoActual_local = manejadorSesion_local.obtenerValorLlavePrimaria();
        idGrupoInformacion_local = manejadorSesion_local.obtenerValorLlavePrimariaAnterior();
        posicionCampoActual_local = administradorBaseDatos_local.obtenerPosicionCampo(idCampoActual_local);
        idCampoSiguiente_local = administradorBaseDatos_local.obtenerIdCampoSiguiente(posicionCampoActual_local, idGrupoInformacion_local);
        
        administradorBaseDatos_local.actualizarCampoIncrementar(idCampoActual_local);
        administradorBaseDatos_local.actualizarCampoDecrementar(idCampoSiguiente_local);
        manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(idCampoActual_local).incrementarPosicion();
        manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(idCampoSiguiente_local).decrementarPosicion();
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      administradorBaseDatos_local = null;
    } 
  }
  private void seleccionarAplicacion(HttpServletRequest pRequest) {
    int idAplicacion_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.borrarAtributosCamposSesion();
      idAplicacion_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidaplicacion", manejadorSesion_local).toString());
      
      grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPrincipalAplicacion(idAplicacion_local);
      
      manejadorSesion_local.actualizarGrupoInformacionActual(grupoInformacion_local);
      manejadorSesion_local.actualizarValorLlavePrimaria(-1);
      manejadorSesion_local.actualizarEstadoActual("Consultando");
      manejadorSesion_local.actualizarAccion(83);
      manejadorSesion_local.actualizarNumeroPagina(12);
      manejadorSesion_local.actualizarNumeroError(0);
      manejadorSesion_local.actualizarTipoError(-1);
      manejadorSesion_local.actualizarConfiguracion(false);
      manejadorSesion_local.actualizarEjecutarConsulta(grupoInformacion_local.getAplicacion().esPermitirConsultaGeneral());
      manejadorSesion_local.limpiarAtributoListaConsulta();
      inicializarListaOpcionesConsultaPorPerfilUsuario(pRequest);
      manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("INICIOSESIONAPLICATIVO");
      
      manejadorSesion_local.obtenerManejadorEventos().setListaCampo(null);
      manejadorSesion_local.obtenerManejadorEventos().setListaCampoValoresAnteriores(null);
      manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(grupoInformacion_local);
      manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);
      
      manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
      manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
      manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
      
      manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
      
      manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
      
      manejadorSesion_local.actualizarNumeroPaginaNavegacion(1);
      manejadorSesion_local.actualizarIdRegistroVisitado(-1);
      manejadorSesion_local.actualizarConsecutivoArchivos(1);
      borrarContenidoDirectorioUsuario(pRequest);
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      grupoInformacion_local = null;
    } 
  }
  protected void processRequest(HttpServletRequest request, HttpServletResponse response, int pTipoAutorizacion) throws ServletException, IOException {
    int numeroError_local = 0;
    int tipoMensaje_local = 0;
    String nombrePagina_local = null;
    String redireccionPagina_local = null;
    ListaParametrosRedireccion listaParametrosRedireccion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (request == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (response == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(request);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      listaParametrosRedireccion_local = new ListaParametrosRedireccion();
      
      switch (pTipoAutorizacion) {
        case 0:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(manejadorSesion_local.obtenerNumeroPagina());
          numeroError_local = manejadorSesion_local.obtenerNumeroError();
          tipoMensaje_local = manejadorSesion_local.obtenerTipoError();
          break;
        case 1:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 2;
          tipoMensaje_local = 2;
          break;
        case 3:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 20;
          tipoMensaje_local = 1;
          break;
        case 2:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 19;
          tipoMensaje_local = 1;
          break;
        case 10:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 33;
          tipoMensaje_local = 1;
          break;
        case 4:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 21;
          tipoMensaje_local = 2;
          break;
        case 5:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 22;
          tipoMensaje_local = 2;
          break;
        case 6:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 23;
          tipoMensaje_local = 2;
          break;
        case 7:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          numeroError_local = 3;
          tipoMensaje_local = 3;
          break;
        case 8:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(4);
          numeroError_local = 0;
          tipoMensaje_local = -1;
          break;
        case 9:
          nombrePagina_local = ap.obtenerNombrePaginaAplicacion(3);
          break;
      } 
      if (numeroError_local != 0) {
        listaParametrosRedireccion_local.adicionar("numeroerror", String.valueOf(numeroError_local));
        
        listaParametrosRedireccion_local.adicionar("tipoerror", String.valueOf(tipoMensaje_local));
        
        listaParametrosRedireccion_local.setRecursoDestino(nombrePagina_local);
        redireccionPagina_local = listaParametrosRedireccion_local.concatenarParametros();
      } else {
        redireccionPagina_local = nombrePagina_local;
      } 
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        response.sendRedirect(response.encodeRedirectURL(redireccionPagina_local));
      } else {
        response.sendRedirect(response.encodeURL(redireccionPagina_local));
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      nombrePagina_local = null;
      redireccionPagina_local = null;
      listaParametrosRedireccion_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void asignarValorIdTablaListaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Tabla tabla_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("tabla", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        tabla_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("tabla", manejadorSesion_local).toString()));
        
        manejadorSesion_local.actualizarTablaActual(tabla_local);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      tabla_local = null;
    } 
  }
  private void asignarValorIdTablaDependeListaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Tabla tabla_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("tablaDepende", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        tabla_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("tablaDepende", manejadorSesion_local).toString()));
        
        manejadorSesion_local.actualizarTablaDepende(tabla_local);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      tabla_local = null;
    } 
  }
  private void asignarValorLlavePrimariaListaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    int valorLlavePrimaria_local = -1;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        valorLlavePrimaria_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local).toString());
        
        manejadorSesion_local.actualizarValorLlavePrimaria(valorLlavePrimaria_local);
        if (manejadorSesion_local.obtenerGrupoInformacionActual().esGrupoInformacionPrincipal()) {
          manejadorSesion_local.actualizarIdRegistroVisitado(valorLlavePrimaria_local);
        }
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void asignarGrupoInformacionListaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        if (manejadorSesion_local.esConfiguracion()) {
          grupoInformacion_local = ap.obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()), manejadorSesion_local.obtenerAplicacionActual());
        
        }
        else {
          
          grupoInformacion_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerGrupoInformacionPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString()));
        } 
        
        manejadorSesion_local.actualizarGrupoInformacionActual(grupoInformacion_local);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      grupoInformacion_local = null;
    } 
  }
  private void asignarEsConfiguracionListaNavegacion(HttpServletRequest pRequest) {
    boolean esConfiguracion_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("configuracion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
      {
        esConfiguracion_local = Boolean.parseBoolean(manejadorRequest_local.obtenerValorAtributoRequest("configuracion", manejadorSesion_local).toString());
      }
      
      manejadorSesion_local.actualizarConfiguracion(esConfiguracion_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void asignarEsDocumentoListaNavegacion(HttpServletRequest pRequest) {
    boolean esDocumento_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("esdocumento", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
      {
        esDocumento_local = Boolean.parseBoolean(manejadorRequest_local.obtenerValorAtributoRequest("esdocumento", manejadorSesion_local).toString());
      }
      
      manejadorSesion_local.actualizarEsDocumento(esDocumento_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void asignarCampoArchivoListaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Campo campoArchivo_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.obtenerValorAtributoRequest("campoArchivo", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
        
        campoArchivo_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoPorId(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("campoArchivo", manejadorSesion_local).toString()));
        
        manejadorSesion_local.actualizarCampoArchivo(campoArchivo_local);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      campoArchivo_local = null;
    } 
  }
  private void asignarValoresCamposConsulta(HttpServletRequest pRequest, ListaConsulta pListaConsulta) {
    int nivelConsulta_local = 0;
    String nombreCampoDesde_local = null;
    String nombreCampoHasta_local = null;
    String valorCampoDesde_local = null;
    String valorCampoHasta_local = null;
    String nombreCampo_local = null;
    Iterator iterator_local = null;
    boolean ordenAscendente_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pListaConsulta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      iterator_local = pListaConsulta.iterator();
      while (iterator_local.hasNext()) {
        ItemConsulta itemConsulta_local = (ItemConsulta)iterator_local.next();
        Campo campoDesde_local = itemConsulta_local.getCampoDesde();
        Campo campoHasta_local = itemConsulta_local.getCampoHasta();
        nivelConsulta_local = itemConsulta_local.getNivelConsulta();
        nombreCampoDesde_local = campoDesde_local.conformarNombreCampoConsulta(nivelConsulta_local, true);
        
        nombreCampoHasta_local = campoHasta_local.conformarNombreCampoConsulta(nivelConsulta_local, false);
        
        valorCampoDesde_local = "";
        valorCampoHasta_local = "";
        if (manejadorRequest_local.obtenerValorAtributoRequest(nombreCampoDesde_local, manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          valorCampoDesde_local = manejadorRequest_local.obtenerValorAtributoRequest(nombreCampoDesde_local, manejadorSesion_local).toString();
        }
        
        if (manejadorRequest_local.obtenerValorAtributoRequest(nombreCampoHasta_local, manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          valorCampoHasta_local = manejadorRequest_local.obtenerValorAtributoRequest(nombreCampoHasta_local, manejadorSesion_local).toString();
        }
        
        campoDesde_local.setValorCampo(valorCampoDesde_local);
        campoHasta_local.setValorCampo(valorCampoHasta_local);
        
        if (itemConsulta_local.esOrdenadoPor()) {
          nombreCampo_local = campoDesde_local.getNombreCampo();
          nombreCampo_local = mc.concatenarCadena(nombreCampo_local, String.valueOf(nivelConsulta_local));
          ordenAscendente_local = (manejadorRequest_local.obtenerValorAtributoRequest(nombreCampo_local, manejadorSesion_local) != ConstantesGeneral.VALOR_NULO && Boolean.parseBoolean(manejadorRequest_local.obtenerValorAtributoRequest(nombreCampo_local, manejadorSesion_local).toString()) == true);
          
          itemConsulta_local.setOrdenAscendente(ordenAscendente_local);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      iterator_local = null;
      valorCampoDesde_local = null;
      valorCampoHasta_local = null;
      nombreCampoDesde_local = null;
      nombreCampoHasta_local = null;
      nombreCampo_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void conformarListaOpcionesConsulta(HttpServletRequest pRequest) {
    int idCampo_local = -1;
    int nivelConsulta_local = 0;
    Campo campoDesde_local = null;
    Campo campoHasta_local = null;
    boolean ordenadoPor_local = false;
    Object valorOrdenadoPor_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        idCampo_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("fldidcampo", manejadorSesion_local).toString());
        
        nivelConsulta_local = manejadorSesion_local.obtenerUltimoNivelConsulta();
        nivelConsulta_local++;
        campoDesde_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoConsultaPorId(idCampo_local);
        campoHasta_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoConsultaPorId(idCampo_local);
        valorOrdenadoPor_local = manejadorRequest_local.obtenerValorAtributoRequest("ordenadopor", manejadorSesion_local);
        
        ordenadoPor_local = (valorOrdenadoPor_local != ConstantesGeneral.VALOR_NULO && Boolean.parseBoolean(valorOrdenadoPor_local.toString()) == true);
        
        manejadorSesion_local.adicionarItemConsulta(campoDesde_local, campoHasta_local, nivelConsulta_local, ordenadoPor_local, true, true);
      } 
      
      manejadorSesion_local.actualizarEjecutarConsulta(false);
      manejadorSesion_local.actualizarAtributoListaConsulta();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      campoDesde_local = null;
      campoHasta_local = null;
      valorOrdenadoPor_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void borrarOpcionConsulta(HttpServletRequest pRequest) {
    int nivelConsulta_local = 0;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      nivelConsulta_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("nivelConsulta", manejadorSesion_local).toString());
      
      manejadorSesion_local.eliminarItemListaConsulta(nivelConsulta_local);
      manejadorSesion_local.actualizarEjecutarConsulta(false);
      manejadorSesion_local.actualizarAtributoListaConsulta();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaExportacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarEstadoActual("Consultando");
        manejadorSesion_local.actualizarNumeroPagina(20);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void exportarReporte(HttpServletRequest pRequest) {
    int valorLlavePrimaria_local = -1;
    String rutaArchivo_local = null;
    Object valorLlave_local = null;
    ReporteExcel reporteExcel_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Aplicacion aplicacionActual_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        valorLlave_local = manejadorRequest_local.obtenerValorAtributoRequest("valorllaveprimaria", manejadorSesion_local);
        
        if (valorLlave_local != ConstantesGeneral.VALOR_NULO && mc.esCadenaNumerica(valorLlave_local.toString(), true)) {
          
          aplicacionActual_local = manejadorSesion_local.obtenerAplicacionActual();
          valorLlavePrimaria_local = Integer.parseInt(valorLlave_local.toString());
          manejadorSesion_local.actualizarValorLlavePrimaria(valorLlavePrimaria_local);
          manejadorSesion_local.actualizarAccion(39);
          reporteExcel_local = new ReporteExcel(aplicacionActual_local, valorLlavePrimaria_local, manejadorSesion_local.obtenerUsuarioActual(), mf.obtenerFechaActualSistema(true), ap.obtenerHoraActualSistema(), manejadorSesion_local.obtenerIdSesion());
          
          reporteExcel_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
          reporteExcel_local.setAdministradorBaseDatosAplicacion(manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion());
          reporteExcel_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
          reporteExcel_local.cargarReporte();
          
          rutaArchivo_local = manejadorSesion_local.obtenerRutaRealArchivoSesion("/administrador/exportacion/");
          reporteExcel_local.generarReporte(manejadorSesion_local.obtenerUsuarioActual().getNombreUsuario(), rutaArchivo_local, manejadorSesion_local.obtenerAtributoListaConsulta());
          
          abrirPaginaExportacion(pRequest);
        } 
      } else {
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      rutaArchivo_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      aplicacionActual_local = null;
    } 
  }
  private void abrirPaginaImportacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarEstadoActual("Incluyendo");
        manejadorSesion_local.actualizarNumeroPagina(23);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void importarArchivo(HttpServletRequest pRequest) {
    String directorioArchivoImportacion_local = null;
    String rutaArchivoErrores_local = null;
    String nombreArchivo_local = null;
    Aplicacion aplicacionActual_local = null;
    ListaCadenas listaCadenas_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    ManejadorImportacion importacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    DiskFileItemFactory diskFileItemFactory_local = null;
    ServletFileUpload servletFileUpload_local = null;
    List items_local = null;
    FileItem fileItem_local = null;
    Iterator iterador_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      diskFileItemFactory_local = new DiskFileItemFactory();
      servletFileUpload_local = new ServletFileUpload((FileItemFactory)diskFileItemFactory_local);
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        rutaArchivoErrores_local = manejadorSesion_local.obtenerRutaRealArchivoSesion("/administrador/importacion/importacion" + manejadorSesion_local.obtenerUsuarioActual().getNombreUsuario() + ".rtf");
        
        aplicacionActual_local = manejadorSesion_local.obtenerAplicacionActual();
        directorioArchivoImportacion_local = manejadorSesion_local.obtenerRutaRealArchivoSesion("/administrador/importacion/");
        
        if (ServletFileUpload.isMultipartContent(pRequest)) {
          items_local = servletFileUpload_local.parseRequest(pRequest);
          if (manejadorSesion_local.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
            iterador_local = manejadorSesion_local.obtenerListaAtributosRequestMultiparte().iterator();
          }
          else if (items_local != ConstantesGeneral.VALOR_NULO) {
            iterador_local = items_local.iterator();
          } 
          
          while (iterador_local.hasNext()) {
            fileItem_local = (FileItem)iterador_local.next();
            nombreArchivo_local = ap.obtenerNombreArchivo(fileItem_local.getName());
            if (!fileItem_local.isFormField()) {
              manejadorRequest_local.subirArchivo(fileItem_local, directorioArchivoImportacion_local);
              
              break;
            } 
          } 
        } 
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
        manejadorArchivos_local = new ManejadorArchivos();
        listaCadenas_local = manejadorArchivos_local.leerArchivo(directorioArchivoImportacion_local + ap.obtenerSeparadorArchivos() + nombreArchivo_local);
        
        importacion_local = new ManejadorImportacion();
        importacion_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
        importacion_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
        importacion_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
        
        if (listaCadenas_local.contarElementos() > 0) {
          if (importacion_local.revisarArchivoImportacion(aplicacionActual_local.getIdAplicacion(), listaCadenas_local, rutaArchivoErrores_local)) {
            
            manejadorArchivos_local.guardarArchivo(rutaArchivoErrores_local, "", false);
            
            importacion_local.importarArchivo(aplicacionActual_local.getIdAplicacion(), listaCadenas_local);
            manejadorSesion_local.actualizarNumeroError(27);
            manejadorSesion_local.actualizarTipoError(3);
          } else {
            manejadorSesion_local.actualizarAtributoListaAtributosRequestMultiparteNulo();
          } 
        } else {
          manejadorSesion_local.actualizarNumeroError(26);
          manejadorSesion_local.actualizarTipoError(2);
        } 
        manejadorArchivos_local.borrarArchivo(directorioArchivoImportacion_local + ap.obtenerSeparadorArchivos() + nombreArchivo_local);
      } else {
        abrirPaginaLogin(pRequest, false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      items_local = null;
      iterador_local = null;
      fileItem_local = null;
      importacion_local = null;
      listaCadenas_local = null;
      nombreArchivo_local = null;
      manejadorSesion_local = null;
      aplicacionActual_local = null;
      manejadorRequest_local = null;
      servletFileUpload_local = null;
      manejadorArchivos_local = null;
      rutaArchivoErrores_local = null;
      diskFileItemFactory_local = null;
      directorioArchivoImportacion_local = null;
      administradorBaseDatosSisnet_local = null;
      administradorBaseDatosAplicacion_local = null;
    } 
  }
  private void direccionarError(HttpServletRequest pRequest, int pNumeroError, boolean pVerificarEsRegistroPrincipal) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (pNumeroError == 0) {
        if (pVerificarEsRegistroPrincipal && !manejadorSesion_local.obtenerAdministradorBaseDatosSisnet().verificarGrupoInformacionContieneCampoDocumento(manejadorSesion_local.obtenerGrupoInformacionActual()))
        {
          
          if (manejadorSesion_local.obtenerValorLlavePrimaria() != -1) {
            abrirPaginaModificarRegistroPrincipalAplicacion(pRequest);
            return;
          } 
        }
        if (manejadorSesion_local.esDocumento() && 
          manejadorSesion_local.obtenerValorLlavePrimaria() != -1) {
          abrirPaginaModificarActuacion(pRequest, manejadorSesion_local.obtenerEstadoActual());
          
          return;
        } 
        manejadorSesion_local.borrarUltimoElementoListaNavegacion();
        manejadorSesion_local.actualizarEjecutarConsulta(true);
        manejadorSesion_local.actualizarNumeroError(pNumeroError);
        manejadorSesion_local.actualizarTipoError(-1);
      } else {
        manejadorSesion_local.actualizarNumeroError(pNumeroError);
        manejadorSesion_local.actualizarTipoError(2);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void ejecutarConsulta(HttpServletRequest pRequest) {
    ListaConsulta listaConsulta_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      listaConsulta_local = manejadorSesion_local.obtenerAtributoListaConsulta();
      asignarValoresCamposConsulta(pRequest, listaConsulta_local);
      manejadorSesion_local.actualizarEjecutarConsulta(true);
      manejadorSesion_local.actualizarAtributoListaConsulta();
      manejadorSesion_local.asignarValorAtributoSesion("numeroPaginaNavegacion", Integer.valueOf(1));
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      listaConsulta_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  protected void validarAdministrador(HttpServletRequest pRequest) throws ServletException, IOException {
    boolean validaAdministrador_local = false;
    int tipoUsuario_local = -1;
    String nombreUsuario_local = null;
    String contrasena_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Usuario usuarioActual_local = null;
    boolean permitirAccesoUsuarioLocal_local = false;
    int idGrupoInformacion_local = -1;
    boolean debeCambiarContrasena_local = false;
    Date fechaCambioContrasena_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.getListaAtributosRequest() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          nombreUsuario_local = manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local).toString();
          
          nombreUsuario_local = mc.convertirAMayusculas(nombreUsuario_local);
        } 
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          contrasena_local = mc.convertirAMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local).toString());
        }
        
        if (administradorBaseDatosSisnet_local.verificarUsuarioPorNombre(nombreUsuario_local)) {
          tipoUsuario_local = administradorBaseDatosSisnet_local.obtenerTipoUsuarioPorContrasena(nombreUsuario_local, contrasena_local);
        }
        
        permitirAccesoUsuarioLocal_local = (manejadorRequest_local.obtenerValorAtributoRequest("validarUsuarioLocal", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO && mc.sonCadenasIgualesIgnorarMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("validarUsuarioLocal", manejadorSesion_local).toString(), String.valueOf(true)));
        
        validaAdministrador_local = (tipoUsuario_local == 0);
        
        if (permitirAccesoUsuarioLocal_local) {
          validaAdministrador_local = (tipoUsuario_local == 1000);
          if (validaAdministrador_local) {
            fechaCambioContrasena_local = administradorBaseDatosSisnet_local.obtenerFechaCambioContrasenaUsuario(nombreUsuario_local);
            
            debeCambiarContrasena_local = (mf.obtenerNumeroMesActual() > mf.obtenerNumeroMesFecha(fechaCambioContrasena_local));
            
            validaAdministrador_local = !debeCambiarContrasena_local;
          } 
        } 
        
        if (manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          idGrupoInformacion_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("grupoinformacionactual", manejadorSesion_local).toString());
        }
        
        if (!validaAdministrador_local) {
          validaAdministrador_local = (tipoUsuario_local == 1000 && idGrupoInformacion_local == 13);
        }
        
        if (validaAdministrador_local) {
          usuarioActual_local = administradorBaseDatosSisnet_local.obtenerUsuarioPorNombre(nombreUsuario_local);
          if (idGrupoInformacion_local != -1) {
            grupoInformacion_local = ap.obtenerGrupoInformacionPorId(idGrupoInformacion_local, ap.obtenerAplicacionAdministradorSisnetWeb(manejadorRequest_local.obtenerNombreRecursoAplicacionWeb()));
          }
          
          manejadorSesion_local.actualizarAccion(96);
          manejadorSesion_local.actualizarNumeroPagina(3);
          manejadorSesion_local.clonarUltimoElementoListaNavegacion();
          if (grupoInformacion_local.getIdGrupoInformacion() == 13) {
            manejadorSesion_local.actualizarAccion(15);
            manejadorSesion_local.actualizarEstadoActual("Consultando");
            manejadorSesion_local.actualizarNumeroPagina(22);
          } else {
            
            manejadorSesion_local.actualizarAccion(79);
            manejadorSesion_local.actualizarEstadoActual("Consultando");
            manejadorSesion_local.actualizarNumeroPagina(15);
          } 
          manejadorSesion_local.actualizarGrupoInformacionActual(grupoInformacion_local);
          manejadorSesion_local.actualizarConfiguracion(true);
          manejadorSesion_local.actualizarNumeroError(0);
          manejadorSesion_local.actualizarTipoError(-1);
          manejadorSesion_local.actualizarUsuarioActual(usuarioActual_local);
        }
        else if (debeCambiarContrasena_local) {
          manejadorSesion_local.actualizarNumeroError(9);
          manejadorSesion_local.actualizarTipoError(1);
        } else {
          manejadorSesion_local.actualizarNumeroError(2);
          manejadorSesion_local.actualizarTipoError(2);
        }
      
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      contrasena_local = null;
      nombreUsuario_local = null;
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      grupoInformacion_local = null;
      administradorBaseDatosSisnet_local = null;
      usuarioActual_local = null;
      fechaCambioContrasena_local = null;
    } 
  }
  protected void validarCambioContrasena(HttpServletRequest pRequest) throws ServletException, IOException {
    boolean validaCambioContrasena_local = false;
    String nombreUsuario_local = null;
    String contrasena_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Usuario usuario_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.getListaAtributosRequest() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO) {
          
          nombreUsuario_local = manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local).toString();
          
          nombreUsuario_local = mc.convertirAMayusculas(nombreUsuario_local);
        } 
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          contrasena_local = mc.convertirAMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local).toString());
        }
        
        usuario_local = administradorBaseDatosSisnet_local.obtenerUsuarioPorNombre(nombreUsuario_local);
        if (usuario_local == ConstantesGeneral.VALOR_NULO) {
          manejadorSesion_local.actualizarNumeroError(2);
          manejadorSesion_local.actualizarTipoError(2);
          return;
        } 
        if (usuario_local.getTipoUsuario().getIdTipoUsuario() == 0 || usuario_local.getTipoUsuario().getIdTipoUsuario() == 1000) {
          
          manejadorSesion_local.actualizarNumeroError(34);
          manejadorSesion_local.actualizarTipoError(2);
          
          return;
        } 
        validaCambioContrasena_local = mc.sonCadenasIguales(usuario_local.getContrasena(), contrasena_local);
        
        if (!validaCambioContrasena_local) {
          manejadorSesion_local.actualizarNumeroError(2);
          manejadorSesion_local.actualizarTipoError(2);
          return;
        } 
        manejadorSesion_local.actualizarAccion(96);
        manejadorSesion_local.actualizarNumeroPagina(3);
        manejadorSesion_local.clonarUltimoElementoListaNavegacion();
        manejadorSesion_local.actualizarAccion(13);
        manejadorSesion_local.actualizarEstadoActual("Modificando");
        manejadorSesion_local.actualizarConfiguracion(true);
        manejadorSesion_local.actualizarNumeroPagina(22);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
        manejadorSesion_local.actualizarUsuarioActual(usuario_local);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      contrasena_local = null;
      nombreUsuario_local = null;
      administradorBaseDatosSisnet_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      usuario_local = null;
    } 
  }
  protected void cambiarContrasena(HttpServletRequest pRequest) throws ServletException, IOException {
    String contrasena_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    Usuario usuario_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.getListaAtributosRequest() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          contrasena_local = mc.convertirAMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local).toString());
        }
        
        usuario_local = manejadorSesion_local.obtenerUsuarioActual();
        if (administradorBaseDatosSisnet_local.cambiarContrasenaUsuario(usuario_local.getNombreUsuario(), contrasena_local) == 0) {
          
          if (usuario_local.getTipoBloqueo() == 4) {
            administradorBaseDatosSisnet_local.desbloquearUsuarioVencimiento(usuario_local.getNombreUsuario(), usuario_local.getDiasVigenciaContrasena());
          }
          
          manejadorSesion_local.borrarUltimoElementoListaNavegacion();
          manejadorSesion_local.actualizarAccion(96);
          manejadorSesion_local.actualizarNumeroPagina(3);
          manejadorSesion_local.actualizarNumeroError(24);
          manejadorSesion_local.actualizarTipoError(3);
        } else {
          manejadorSesion_local.actualizarNumeroError(18);
          manejadorSesion_local.actualizarTipoError(2);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      contrasena_local = null;
      administradorBaseDatosSisnet_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      usuario_local = null;
    } 
  }
  protected void cambiarUsuarioAdministrador(HttpServletRequest pRequest) throws ServletException, IOException {
    int errorEjecucion_local = 0;
    String nombreUsuario_local = null;
    String nuevoNombreUsuario_local = null;
    String nuevaContrasena_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorRequest_local.getListaAtributosRequest() != ConstantesGeneral.VALOR_NULO) {
        administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
        nombreUsuario_local = administradorBaseDatosSisnet_local.obtenerUsuarioPorId(1).getNombreUsuario();
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          nuevaContrasena_local = mc.convertirAMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("fldcontrasena", manejadorSesion_local).toString());
        }
        
        if (manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          nuevoNombreUsuario_local = mc.convertirAMayusculas(manejadorRequest_local.obtenerValorAtributoRequest("fldnombreusuario", manejadorSesion_local).toString());
        }
        
        errorEjecucion_local = administradorBaseDatosSisnet_local.cambiarNombreUsuario(nombreUsuario_local, nuevoNombreUsuario_local);
        if (errorEjecucion_local == 0) {
          administradorBaseDatosSisnet_local.cambiarContrasenaUsuario(nuevoNombreUsuario_local, nuevaContrasena_local);
          manejadorSesion_local.borrarUltimoElementoListaNavegacion();
          manejadorSesion_local.actualizarAccion(96);
          manejadorSesion_local.actualizarNumeroPagina(3);
          manejadorSesion_local.actualizarNumeroError(24);
          manejadorSesion_local.actualizarTipoError(3);
        } else {
          manejadorSesion_local.actualizarNumeroError(errorEjecucion_local);
          manejadorSesion_local.actualizarTipoError(2);
        } 
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      nombreUsuario_local = null;
      administradorBaseDatosSisnet_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private ConexionPostgres conformarConexionPostgres(ManejadorRequest pManejadorRequest) {
    ConexionPostgres conexionPostgres_local = null;
    Object valorSuperUsuario_local = null;
    Object valorContrasenaSuperUsuario_local = null;
    Object valorNumeroPuertoConexion_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pManejadorRequest == ConstantesGeneral.VALOR_NULO) {
      return conexionPostgres_local;
    }
    
    try {
      manejadorSesion_local = new ManejadorSesion(pManejadorRequest.obtenerSesion());
      conexionPostgres_local = new ConexionPostgres();
      valorSuperUsuario_local = pManejadorRequest.obtenerValorAtributoRequest("fldsuperusuario", manejadorSesion_local);
      
      valorContrasenaSuperUsuario_local = pManejadorRequest.obtenerValorAtributoRequest("fldcontrasenasuperusuario", manejadorSesion_local);
      
      valorNumeroPuertoConexion_local = pManejadorRequest.obtenerValorAtributoRequest("fldnumeropuertoconexion", manejadorSesion_local);
      
      if (valorSuperUsuario_local != ConstantesGeneral.VALOR_NULO) {
        conexionPostgres_local.setSuperUsuario(mc.convertirAMinusculas(valorSuperUsuario_local.toString()));
      }
      if (valorContrasenaSuperUsuario_local != ConstantesGeneral.VALOR_NULO) {
        conexionPostgres_local.setContrasenaSuperUsuario(valorContrasenaSuperUsuario_local.toString());
      }
      if (valorNumeroPuertoConexion_local != ConstantesGeneral.VALOR_NULO && mc.esCadenaNumerica(valorNumeroPuertoConexion_local.toString(), true))
      {
        conexionPostgres_local.setNumeroPuertoConexion(Integer.parseInt(valorNumeroPuertoConexion_local.toString()));
      }
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      valorSuperUsuario_local = null;
      valorNumeroPuertoConexion_local = null;
      valorContrasenaSuperUsuario_local = null;
      manejadorSesion_local = null;
    } 
    
    return conexionPostgres_local;
  }
  private int guardarConfiguracion(HttpServletRequest pRequest) {
    int tipoAutorizacion_local = -1;
    String rutaArchivo_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ConexionPostgres conexionPostgres_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return tipoAutorizacion_local;
    }
    
    try {
      manejadorArchivos_local = new ManejadorArchivos();
      manejadorRequest_local = new ManejadorRequest(pRequest);
      conexionPostgres_local = conformarConexionPostgres(manejadorRequest_local);
      rutaArchivo_local = getServletContext().getRealPath(mc.concatenarCadena("/administrador/conf/", "sisnet.conf"));
      
      rutaArchivo_local = mc.reemplazarCadena(rutaArchivo_local, String.valueOf("\\\\"), System.getProperty("file.separator") + System.getProperty("file.separator"));
      
      if (manejadorArchivos_local.existeArchivo(rutaArchivo_local)) {
        manejadorArchivos_local.modificarArchivoConexionPostgres(rutaArchivo_local, conexionPostgres_local);
      } else {
        manejadorArchivos_local.guardarObjetoArchivo(rutaArchivo_local, conexionPostgres_local);
      } 
      tipoAutorizacion_local = 8;
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      rutaArchivo_local = null;
      manejadorRequest_local = null;
      conexionPostgres_local = null;
      manejadorArchivos_local = null;
    } 
    
    return tipoAutorizacion_local;
  }
  private void inicializarListaOpcionesConsultaPorPerfilUsuario(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    ManejadorPermisoUsuario manejadorPermisoUsuario_local = null;
    ListaCadenas listaNombresCamposConsulta_local = null;
    Iterator iterator_local = null;
    String etiquetaCampo_local = null;
    int nivelConsulta_local = 0;
    Campo campoDesde_local = null;
    Campo campoHasta_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        manejadorPermisoUsuario_local = new ManejadorPermisoUsuario(manejadorSesion_local.obtenerTipoUsuarioActual());
        manejadorPermisoUsuario_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
        manejadorPermisoUsuario_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
        listaNombresCamposConsulta_local = manejadorPermisoUsuario_local.obtenerListaCamposOpcionConsultaPredeterminados(manejadorSesion_local.obtenerAplicacionActual());
        
        if (listaNombresCamposConsulta_local != ConstantesGeneral.VALOR_NULO) {
          iterator_local = listaNombresCamposConsulta_local.iterator();
          while (iterator_local.hasNext()) {
            etiquetaCampo_local = (String)iterator_local.next();
            
            if (!mc.esCadenaVacia(etiquetaCampo_local)) {
              nivelConsulta_local = manejadorSesion_local.obtenerUltimoNivelConsulta();
              nivelConsulta_local++;
              
              campoDesde_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoConsultaPorEtiqueta(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion(), etiquetaCampo_local);
              
              campoHasta_local = manejadorSesion_local.obtenerMotorAplicacion().obtenerCampoConsultaPorEtiqueta(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion(), etiquetaCampo_local);
              
              if (campoDesde_local.esTipoDatoTabla() || campoDesde_local.esCampoEnlazado()) {
                campoDesde_local.setValorCampo(Integer.valueOf(-1));
              }
              if (campoHasta_local.esTipoDatoTabla() || campoHasta_local.esCampoEnlazado()) {
                campoHasta_local.setValorCampo(Integer.valueOf(-1));
              }
              
              if (campoDesde_local != ConstantesGeneral.VALOR_NULO) {
                manejadorSesion_local.adicionarItemConsulta(campoDesde_local, campoHasta_local, nivelConsulta_local, false, true, false);
              }
            } 
          } 
        } 
      } 
      
      manejadorSesion_local.actualizarAtributoListaConsulta();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      manejadorPermisoUsuario_local = null;
      listaNombresCamposConsulta_local = null;
      iterator_local = null;
      etiquetaCampo_local = null;
      campoDesde_local = null;
      campoHasta_local = null;
    } 
  }
  private void cargarListaVariablesSistemaPorPerfilUsuario(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    ManejadorPermisoUsuario manejadorPermisoUsuario_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        manejadorPermisoUsuario_local = new ManejadorPermisoUsuario(manejadorSesion_local.obtenerTipoUsuarioActual());
        manejadorPermisoUsuario_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
        manejadorPermisoUsuario_local.setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
        manejadorSesion_local.obtenerUsuarioActual().setListaVariablesSistema(manejadorPermisoUsuario_local.obtenerListaVariablesSistemaPerfilUsuario());
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
      manejadorPermisoUsuario_local = null;
    } 
  }
  private void cargarMotorAplicacion(HttpServletRequest pRequest) {
    MotorAplicacion motorAplicacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    AtributoSesion atributoSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.obtenerMotorAplicacion() == ConstantesGeneral.VALOR_NULO) {
        motorAplicacion_local = new MotorAplicacion();
        motorAplicacion_local.setAdministradorBaseDatosSisnet(manejadorSesion_local.obtenerAdministradorBaseDatosSisnet());
        motorAplicacion_local.cargarMotorAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
        atributoSesion_local = new AtributoSesion("motorAplicacion", motorAplicacion_local);
        manejadorSesion_local.adicionarAtributoSesion(atributoSesion_local);
      } else {
        manejadorSesion_local.obtenerMotorAplicacion().cargarMotorAplicacion(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
      } 
      inicializarListaOpcionesConsultaPorPerfilUsuario(pRequest);
      cargarListaVariablesSistemaPorPerfilUsuario(pRequest);
      
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      manejadorSesion_local.obtenerManejadorEventos().setMotorAplicacion(manejadorSesion_local.obtenerMotorAplicacion());
      
      manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("INICIOSESIONAPLICATIVO");
      
      manejadorSesion_local.obtenerManejadorEventos().setListaCampo(null);
      manejadorSesion_local.obtenerManejadorEventos().setListaCampoValoresAnteriores(null);
      manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(manejadorSesion_local.obtenerGrupoInformacionActual());
      
      manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);
      
      manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
      manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
      manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
      
      manejadorSesion_local.actualizarMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getMensajeEventos());
      
      manejadorSesion_local.actualizarTipoMensajeEventos(manejadorSesion_local.obtenerManejadorEventos().getTipoMensajeEventos());
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      atributoSesion_local = null;
      motorAplicacion_local = null;
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void borrarValoresListaCampoMotorAplicacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (!manejadorSesion_local.esConfiguracion() && manejadorSesion_local.obtenerMotorAplicacion() != ConstantesGeneral.VALOR_NULO) {
        manejadorSesion_local.obtenerMotorAplicacion().borrarValoresListaCampos(manejadorSesion_local.obtenerAplicacionActual().getIdAplicacion());
      }
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  public boolean verificarParametrosRecargaPagina(HttpServletRequest pRequest) {
    boolean recargaPagina_local = false;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return recargaPagina_local;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      recargaPagina_local = (manejadorRequest_local.obtenerValorAtributoRequest("recargarPagina", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO && mc.sonCadenasIguales(manejadorRequest_local.obtenerValorAtributoRequest("recargarPagina", manejadorSesion_local).toString(), String.valueOf(true)));
    
    }
    catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
    
    return recargaPagina_local;
  }
  private void abrirPaginaMensaje(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarNumeroPagina(25);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
        manejadorSesion_local.actualizarConfiguracion(false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void abrirPaginaDescargarArchivo(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        abrirPaginaLogin(pRequest, false);
      } else {
        manejadorSesion_local.actualizarNumeroPagina(26);
        manejadorSesion_local.actualizarNumeroError(0);
        manejadorSesion_local.actualizarTipoError(-1);
        manejadorSesion_local.actualizarConfiguracion(false);
      } 
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void ejecutarEventoUsuario(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    int numeroEvento_local = -1;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      ManejadorEventos me_local = manejadorSesion_local.obtenerManejadorEventos();
      if (manejadorRequest_local.obtenerValorAtributoRequest("opcionEvento", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
      {
        numeroEvento_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("opcionEvento", manejadorSesion_local).toString());
      }
      
      me_local.setGrupoInformacion(manejadorSesion_local.obtenerGrupoInformacionActual());
      
      me_local.setRealizarAccionUsuario(false);
      me_local.setValorLlaveGrupoPrincipal(-1);
      me_local.setValorLlaveGrupoInformacion(-1);
      me_local.setNumeroEvento(numeroEvento_local);
      manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
      me_local.ejecutarEventoUsuario();
      manejadorSesion_local.actualizarExistenEventosEnEjecucion(false);
      if (!me_local.esHuboError()) {
    	me_local.setRealizarAccionUsuario(true);
        manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
        me_local.ejecutarEventoUsuario();
        manejadorSesion_local.actualizarExistenEventosEnEjecucion(false);
      } 
      manejadorSesion_local.actualizarMensajeEventos(me_local.getMensajeEventos());
      
      manejadorSesion_local.actualizarTipoMensajeEventos(me_local.getTipoMensajeEventos());
      
      manejadorSesion_local.actualizarEjecutarConsulta(false);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void incrementarNumeroPaginaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      
      manejadorSesion_local.incrementarNumeroPaginaNavegacion();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  private void decrementarNumeroPaginaNavegacion(HttpServletRequest pRequest) {
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (pRequest == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(pRequest);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      
      manejadorSesion_local.decrementarNumeroPaginaNavegacion();
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorRequest_local = null;
      manejadorSesion_local = null;
    } 
  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int accion_local = 0;
    int tipoAutorizacion_local = -1;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (request == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (response == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(request);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      tipoAutorizacion_local = 0;
      accion_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("accion", manejadorSesion_local).toString());
      
      if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO) {
        int error_local; switch (accion_local) {
          case 1:
            asignarEsConfiguracionListaNavegacion(request);
            manejadorSesion_local.obtenerManejadorEventos().setNombreEvento("FINALSESIONAPLICATIVO");
            
            manejadorSesion_local.obtenerManejadorEventos().setListaCampo(null);
            manejadorSesion_local.obtenerManejadorEventos().setListaCampoValoresAnteriores(null);
            manejadorSesion_local.obtenerManejadorEventos().setGrupoInformacion(manejadorSesion_local.obtenerGrupoInformacionActual());
            
            manejadorSesion_local.obtenerManejadorEventos().setRealizarAccionUsuario(true);
            
            manejadorSesion_local.actualizarExistenEventosEnEjecucion(true);
            manejadorSesion_local.obtenerManejadorEventos().ejecutarEvento();
            manejadorSesion_local.actualizarExistenEventosEnEjecucion(!manejadorSesion_local.obtenerManejadorEventos().haFinalizadoEjecucion());
            
            seleccionarAplicacion(request);
            break;
          case 86:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarGrupoInformacionListaNavegacion(request);
            asignarEsDocumentoListaNavegacion(request);
            abrirPaginaIncluirRegistroAplicacion(request, "Incluyendo");
            break;
          case 88:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarGrupoInformacionListaNavegacion(request);
            asignarEsDocumentoListaNavegacion(request);
            manejadorSesion_local.borrarAtributosCamposSesion();
            abrirPaginaIncluirRegistroAplicacion(request, "Modificando");
            break;
          case 4:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarGrupoInformacionListaNavegacion(request);
            asignarEsDocumentoListaNavegacion(request);
            abrirPaginaIncluirRegistroAplicacion(request, "Modificando");
            break;
          case 23:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarGrupoInformacionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            asignarEsDocumentoListaNavegacion(request);
            abrirPaginaModificarActuacion(request, "Modificando");
            break;
          case 83:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            abrirPaginaConsultaPrincipalAplicacion(request);
            manejadorRequest_local.borrarParametrosRequest();
            manejadorSesion_local.borrarAtributosCamposSesion();
            break;
          case 84:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            abrirPaginaConsultaGeneralGrupoInformacion(request);
            break;
          case 97:
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            abrirPaginaConsultaPrincipalAplicacion(request);
            manejadorSesion_local.borrarAtributosCamposSesion();
            break;
          case 61:
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            abrirPaginaMotorAplicacion(request);
            manejadorSesion_local.borrarAtributosCamposSesion();
            break;
          case 95:
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            manejadorSesion_local.borrarAtributosCamposSesion();
            manejadorRequest_local.borrarParametrosRequest();
            borrarValoresListaCampoMotorAplicacion(request);
            if (manejadorSesion_local.obtenerListaAtributosRequestMultiparte() != ConstantesGeneral.VALOR_NULO) {
              manejadorSesion_local.actualizarAtributoListaAtributosRequestMultiparteNulo();
            }
            manejadorSesion_local.actualizarMensajeEventos("");
            manejadorSesion_local.actualizarTipoMensajeEventos(-1);
            manejadorSesion_local.obtenerManejadorEventos().setMensajeEventos("");
            break;
          case 93:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            if (borrarRegistroAplicacion(request, false, false) == -1)
            {
              manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            }
            break;
          
          case 71:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaIncluirValorTabla(request, "Incluyendo");
            break;
          case 72:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaIncluirValorTabla(request, "Modificando");
            break;
          case 75:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            borrarRegistroValorTabla(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          
          case 67:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            manejadorSesion_local.actualizarGrupoInformacionActual(ap.obtenerGrupoInformacionPorId(4, manejadorSesion_local.obtenerAplicacionActual()));
            
            abrirPaginaIncluirRegistroAplicacion(request, "Incluyendo");
            break;
          case 68:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            manejadorSesion_local.actualizarGrupoInformacionActual(ap.obtenerGrupoInformacionPorId(4, manejadorSesion_local.obtenerAplicacionActual()));
            
            abrirPaginaIncluirRegistroAplicacion(request, "Modificando");
            break;
          case 70:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            manejadorSesion_local.actualizarGrupoInformacionActual(ap.obtenerGrupoInformacionPorId(4, manejadorSesion_local.obtenerAplicacionActual()));
            
            borrarRegistroAplicacion(request, false, false);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            manejadorSesion_local.borrarAtributosCamposSesion();
            break;
          case 27:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            decrementarPosicionCampo(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          case 28:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            incrementarPosicionCampo(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          
          case 96:
            cerrarSesion(request);
            break;
          
          case 85:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarEsDocumentoListaNavegacion(request);
            abrirPaginaIncluirRegistroPrincipalAplicacion(request);
            break;
          case 87:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            asignarEsDocumentoListaNavegacion(request);
            abrirPaginaModificarRegistroPrincipalAplicacion(request);
            break;
          case 94:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            error_local = borrarRegistroAplicacion(request, true, false);
            direccionarError(request, error_local, false);
            break;
          case 66:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarGrupoInformacionListaNavegacion(request);
            borrarRegistroAplicacion(request, false, true);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            manejadorSesion_local.borrarAtributosCamposSesion();
            break;
          
          case 17:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorIdTablaDependeListaNavegacion(request);
            abrirPaginaVerDependencias(request);
            break;
          case 18:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaIncluirValorDependiente(request, "Incluyendo");
            break;
          case 19:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaIncluirValorDependiente(request, "Modificando");
            break;
          case 22:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            borrarRegistroValorDependiente(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          
          case 29:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            asignarValorIdTablaDependeListaNavegacion(request);
            abrirPaginaDependienteHabilitacion(request);
            break;
          case 30:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaIncluirDependienteHabilitacion(request, "Incluyendo");
            break;
          case 31:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaIncluirDependienteHabilitacion(request, "Modificando");
            break;
          case 34:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorIdTablaListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            borrarRegistroDependienteHabilitacion(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          
          case 25:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            decrementarPosicionGrupoInformacion(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          case 26:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            incrementarPosicionGrupoInformacion(request);
            manejadorSesion_local.borrarUltimoElementoListaNavegacion();
            break;
          
          case 63:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarEsConfiguracionListaNavegacion(request);
            asignarGrupoInformacionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            abrirPaginaConsultaGeneral(request);
            break;
          
          case 35:
            conformarListaOpcionesConsulta(request);
            break;
          
          case 37:
            borrarOpcionConsulta(request);
            break;
          
          case 39:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            exportarReporte(request);
            break;
          
          case 40:
            manejadorSesion_local.actualizarEjecutarConsulta((manejadorSesion_local.obtenerAtributoListaConsulta().contarElementos() > 0));
            
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            abrirPaginaImportacion(request);
            break;
          case 62:
            cargarMotorAplicacion(request);
            abrirPaginaConsultaPrincipalAplicacion(request);
            break;
          case 60:
            manejadorSesion_local.clonarUltimoElementoListaNavegacion();
            asignarGrupoInformacionListaNavegacion(request);
            asignarValorLlavePrimariaListaNavegacion(request);
            asignarCampoArchivoListaNavegacion(request);
            abrirPaginaMensaje(request);
            break;
          case 43:
            abrirPaginaDescargarArchivo(request);
            break;
          case 45:
            ejecutarEventoUsuario(request);
            break;
          case 46:
            incrementarNumeroPaginaNavegacion(request);
            break;
          case 47:
            decrementarNumeroPaginaNavegacion(request);
            break;
          case 49:
            generarImpresionMasiva(request);
            break;
        } 
      
      } else if (accion_local == 11) {
        abrirPaginaLoginAcceso(request);
      } else {
        tipoAutorizacion_local = 7;
      } 
      
      processRequest(request, response, tipoAutorizacion_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorSesion_local = null;
      manejadorRequest_local = null;
    } 
  }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int accion_local = 0;
    int numeroError_local = 0;
    int tipoAutorizacion_local = 1;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    
    if (request == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (response == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      manejadorRequest_local = new ManejadorRequest(request);
      manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
      accion_local = -1;
      if (manejadorSesion_local.getSesion() == ConstantesGeneral.VALOR_NULO) {
        if (manejadorRequest_local.obtenerValorAtributoRequest("accion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
        {
          accion_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("accion", manejadorSesion_local).toString());
        }
        
        if (accion_local != 42) {
          administradorBaseDatosSisnet_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosSisnet(manejadorRequest_local.obtenerNombreRecursoAplicacion()));
          
          administradorBaseDatosAplicacion_local = new AdministradorBaseDatos(ap.obtenerConexionBaseDatosAplicacion(manejadorRequest_local.obtenerNombreRecursoAplicacionWeb()));
          
          tipoAutorizacion_local = entrarAplicacion(request, response, administradorBaseDatosSisnet_local, administradorBaseDatosAplicacion_local);
          
          administradorBaseDatosSisnet_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
          administradorBaseDatosAplicacion_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
        } else {
          tipoAutorizacion_local = guardarConfiguracion(request);
        } 
      } else {
        tipoAutorizacion_local = 0;
        manejadorSesion_local.actualizarRecargarPagina(verificarParametrosRecargaPagina(request));
        if (manejadorSesion_local.esRecargarPagina()) {
          asignarPlantillaUtilizarListaNavegacion(request);
          manejadorSesion_local.borrarAtributosCamposSesion();
          manejadorSesion_local.copiarAtributosRequestSesion(request);
        } else {
          if (manejadorRequest_local.obtenerValorAtributoRequest("accion", manejadorSesion_local) != ConstantesGeneral.VALOR_NULO)
          {
            manejadorSesion_local.actualizarAccion(Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("accion", manejadorSesion_local).toString()));
          }
          
          numeroError_local = 0;
          accion_local = Integer.parseInt(manejadorRequest_local.obtenerValorAtributoRequest("accion", manejadorSesion_local).toString());
          
          switch (accion_local) {
            case 90:
              numeroError_local = incluirRegistroAplicacion(request, false);
              direccionarError(request, numeroError_local, false);
              break;
            case 73:
              numeroError_local = incluirRegistroValorTabla(request);
              direccionarError(request, numeroError_local, false);
              break;
            case 74:
              numeroError_local = modificarRegistroValorTabla(request);
              direccionarError(request, numeroError_local, false);
              break;
            case 91:
              numeroError_local = modificarRegistroAplicacion(request, false, false);
              
              direccionarError(request, numeroError_local, false);
              break;
            case 69:
              numeroError_local = modificarRegistroAplicacion(request, false, false);
              
              direccionarError(request, numeroError_local, false);
              break;
            case 20:
              numeroError_local = incluirRegistroValorDependiente(request);
              direccionarError(request, numeroError_local, false);
              break;
            case 21:
              numeroError_local = modificarRegistroValorDependiente(request);
              direccionarError(request, numeroError_local, false);
              break;
            case 32:
              numeroError_local = incluirRegistroDependienteHabilitacion(request);
              direccionarError(request, numeroError_local, false);
              break;
            case 33:
              numeroError_local = modificarRegistroDependienteHabilitacion(request);
              direccionarError(request, numeroError_local, false);
              break;
            case 9:
              numeroError_local = incluirUsuarioLogin(request);
              direccionarError(request, numeroError_local, false);
              break;
            
            case 89:
              numeroError_local = incluirRegistroAplicacion(request, true);
              direccionarError(request, numeroError_local, true);
              break;
            case 92:
              numeroError_local = modificarRegistroAplicacion(request, true, false);
              
              direccionarError(request, numeroError_local, false);
              break;
            case 64:
              numeroError_local = incluirRegistroAplicacion(request, false);
              direccionarError(request, numeroError_local, false);
              break;
            case 65:
              numeroError_local = modificarRegistroAplicacion(request, false, false);
              
              direccionarError(request, numeroError_local, false);
              break;
            case 24:
              numeroError_local = modificarRegistroAplicacion(request, !manejadorSesion_local.obtenerGrupoInformacionActual().esGrupoInformacionMultiple(), true);
              
              if (numeroError_local == 0) {
                manejadorSesion_local.actualizarEsDocumento(false);
              }
              direccionarError(request, numeroError_local, false);
              break;
            case 36:
              ejecutarConsulta(request);
              break;
            case 10:
              validarAdministrador(request);
              break;
            case 12:
              validarCambioContrasena(request);
              break;
            case 13:
              cambiarContrasena(request);
              break;
            case 15:
              cambiarUsuarioAdministrador(request);
              break;
            case 41:
              importarArchivo(request);
              break;
          } 
        } 
      } 
      processRequest(request, response, tipoAutorizacion_local);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      manejadorSesion_local = null;
      manejadorRequest_local = null;
      administradorBaseDatosSisnet_local = null;
      administradorBaseDatosAplicacion_local = null;
    } 
  }
  public String getServletInfo() {
    return "Servlet administrador de aplicaciones Sisnet Ltda";
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\servlets\AdministradorServlet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */