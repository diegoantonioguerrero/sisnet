package com.sisnet.aplicacion.manejadores;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.ManejadorConsultaSQL;
import com.sisnet.baseDatos.consultasBaseDatos.ConsultasAdministrador;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.VariableSistema;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.ListaVariablesSistema;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaGrupoInformacion;
import com.sisnet.objetosManejo.manejoBaseDatos.ObjetoManejadorConsultaSQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
public class ManejadorPermisoUsuario
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private static ConsultasAdministrador ca = ConsultasAdministrador.getConsultasAdministrador();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private int aTipoUsuario;
  private String aPermisosTipoUsuario;
  private MotorAplicacion aMotorAplicacion;
  
  private static ArrayList<ManejadorPermisoUsuario> mngPermisoUsuario = null;
  
  public static ManejadorPermisoUsuario getManejadorPermisoUsuario(int pTipoUsuario, AdministradorBaseDatos pAdministradorBaseDatosSisnet, MotorAplicacion pMotorAplicacion)
  {
	  if(mngPermisoUsuario == null)
	  {
		  mngPermisoUsuario = new ArrayList<ManejadorPermisoUsuario>(); 
	  }
	  
	  for (ManejadorPermisoUsuario item : mngPermisoUsuario) { 		      
           if(item.getTipoUsuario() == pTipoUsuario) {
        	   return item;
           } 		
      }
	  
	  //When not found
	  ManejadorPermisoUsuario mpu = new ManejadorPermisoUsuario(pTipoUsuario);
	  mpu.setAdministradorBaseDatosSisnet(pAdministradorBaseDatosSisnet);
	  mpu.setMotorAplicacion(pMotorAplicacion);
	  mngPermisoUsuario.add(mpu);
	  return mpu; 
  }
  
  private ManejadorPermisoUsuario(int pTipoUsuario) {
    setTipoUsuario(pTipoUsuario);
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
    setPermisosTipoUsuario(obtenerPermisosTipoUsuario());
  }
  public int getTipoUsuario() {
    return this.aTipoUsuario;
  }
  public void setTipoUsuario(int pTipoUsuario) {
    this.aTipoUsuario = pTipoUsuario;
  }
  
  public String getPermisosTipoUsuario() {
    return this.aPermisosTipoUsuario;
  }
  
  public void setPermisosTipoUsuario(String pPermisosTipoUsuario) {
    this.aPermisosTipoUsuario = pPermisosTipoUsuario;
  }
  
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  private String obtenerPermisosTipoUsuario() {
    String permisosTipoUsuario_local = "";
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLPermisosTipoUsuario(getTipoUsuario());
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getAdministradorBaseDatosSisnet().getConexionBaseDatos(), consulta_local, "seleccion");
      
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          permisosTipoUsuario_local = resultSet_local.getString("fldnivelacceso");
        }
      } else {
        getAdministradorBaseDatosSisnet().setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return permisosTipoUsuario_local;
  }
  private String obtenerPermisos(String pPermisoAcceso) {
    String permisos_local = "";
    int posicionComillasFinal_local = -1;
    
    if (pPermisoAcceso == ConstantesGeneral.VALOR_NULO) {
      return permisos_local;
    }
    
    try {
      posicionComillasFinal_local = mc.obtenerUltimaPosicionSubCadena(pPermisoAcceso, String.valueOf('"')) + 1;
      
      if (posicionComillasFinal_local != -1) {
        permisos_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(pPermisoAcceso, posicionComillasFinal_local, mc.obtenerLongitudCadena(pPermisoAcceso)));
        
        if (!mc.verificarExistenciaSubCadena(permisos_local, "V")) {
          permisos_local = mc.concatenarCadena(permisos_local, "V");
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return permisos_local;
  }
  private String obtenerCadenaPermisoAcceso(String pBloqueNiveles, int pPosicionInicial) {
    String cadenaNivelAcceso_local = "";
    int posicionFinal_local = -1;
    
    if (pBloqueNiveles == ConstantesGeneral.VALOR_NULO) {
      return cadenaNivelAcceso_local;
    }
    
    try {
      posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pBloqueNiveles, "\r\n", pPosicionInicial);
      
      if (posicionFinal_local == -1) {
        posicionFinal_local = mc.obtenerLongitudCadena(pBloqueNiveles);
      }
      cadenaNivelAcceso_local = mc.obtenerSubCadena(pBloqueNiveles, pPosicionInicial, posicionFinal_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return cadenaNivelAcceso_local;
  }
  private String obtenerBloquePermisosAPartirPosicion(String pBloqueBase, String pTipoNiveles, int pPosicionInicial) {
    String bloqueNiveles_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    
    if (pBloqueBase == ConstantesGeneral.VALOR_NULO) {
      return bloqueNiveles_local;
    }
    if (pTipoNiveles == ConstantesGeneral.VALOR_NULO) {
      return bloqueNiveles_local;
    }
    
    try {
      posicionInicial_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pBloqueBase, pTipoNiveles, pPosicionInicial);
      
      if (posicionInicial_local != -1) {
        if (mc.verificarExistenciaSubCadenaAPartirPosicion(pBloqueBase, pTipoNiveles, posicionInicial_local + 1)) {
          
          posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pBloqueBase, pTipoNiveles, posicionInicial_local + 1);
        } else {
          
          posicionFinal_local = mc.obtenerLongitudCadena(pBloqueBase);
        } 
        bloqueNiveles_local = mc.obtenerSubCadena(pBloqueBase, posicionInicial_local, posicionFinal_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return bloqueNiveles_local;
  }
  private boolean verificarPermisoVer(String pPermisos) {
    boolean permisoVer_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoVer_local;
    }
    
    try {
      if (!mc.verificarExistenciaSubCadena(pPermisos, "-V"))
      {
        permisoVer_local = mc.verificarExistenciaSubCadena(pPermisos, "V");
      }
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoVer_local;
  }
  private boolean verificarPermisoIncluir(String pPermisos) {
    boolean permisoIncluir_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoIncluir_local;
    }
    
    try {
      permisoIncluir_local = mc.verificarExistenciaSubCadena(pPermisos, "I");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoIncluir_local;
  }
  private boolean verificarPermisoOpcionReportes(String pPermisos) {
    boolean permisoOpcionReportes_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoOpcionReportes_local;
    }
    
    try {
      permisoOpcionReportes_local = mc.verificarExistenciaSubCadena(pPermisos, "R");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoOpcionReportes_local;
  }
  private boolean verificarPermisoImpresionMasiva(String pPermisos) {
    boolean permisoImpresionMasiva_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoImpresionMasiva_local;
    }
    
    try {
      permisoImpresionMasiva_local = mc.verificarExistenciaSubCadena(pPermisos, "P");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoImpresionMasiva_local;
  }
  private boolean verificarPermisoImportacion(String pPermisos) {
    boolean permisoImportacion_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoImportacion_local;
    }
    
    try {
      permisoImportacion_local = mc.verificarExistenciaSubCadena(pPermisos, "T");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoImportacion_local;
  }
  private boolean verificarPermisoModificar(String pPermisos) {
    boolean permisoModificar_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoModificar_local;
    }
    
    try {
      permisoModificar_local = mc.verificarExistenciaSubCadena(pPermisos, "M");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoModificar_local;
  }
  private boolean verificarPermisoBorrar(String pPermisos) {
    boolean permisoBorrar_local = false;
    
    if (pPermisos == ConstantesGeneral.VALOR_NULO) {
      return permisoBorrar_local;
    }
    
    try {
      permisoBorrar_local = (mc.verificarExistenciaSubCadena(pPermisos, "B") || mc.verificarExistenciaSubCadena(pPermisos, "D"));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return permisoBorrar_local;
  }
  private String obtenerBloqueRestricciones(String pBloqueBase, String pTipoRestriccion) {
    String bloqueRestricciones_local = "";
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    
    if (pBloqueBase == ConstantesGeneral.VALOR_NULO) {
      return bloqueRestricciones_local;
    }
    if (pTipoRestriccion == ConstantesGeneral.VALOR_NULO) {
      return bloqueRestricciones_local;
    }
    
    try {
      pBloqueBase = mc.convertirAMayusculas(pBloqueBase);
      posicionInicial_local = mc.obtenerPosicionSubCadena(pBloqueBase, pTipoRestriccion);
      if (posicionInicial_local != -1) {
        posicionInicial_local = posicionInicial_local + mc.obtenerLongitudCadena(pTipoRestriccion) + mc.obtenerLongitudCadena("\r\n");
        
        if (mc.verificarExistenciaSubCadenaAPartirPosicion(pBloqueBase, "$C", posicionInicial_local)) {
          posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pBloqueBase, "$C", posicionInicial_local);
        }
        
        if (posicionFinal_local == -1) {
          if (mc.verificarExistenciaSubCadenaAPartirPosicion(pBloqueBase, "$G", posicionInicial_local)) {
            
            posicionFinal_local = mc.obtenerPosicionSubCadenaAPartirPosicion(pBloqueBase, "$G", posicionInicial_local);
          } else {
            
            posicionFinal_local = mc.obtenerLongitudCadena(pBloqueBase);
          } 
        }
        bloqueRestricciones_local = mc.obtenerSubCadena(pBloqueBase, posicionInicial_local, posicionFinal_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return bloqueRestricciones_local;
  }
  public boolean obtenerPermisoUtilizarMenuConfiguracion() {
    boolean permisoUtilizarMenuConfiguracion_local = false;
    String consulta_local = null;
    ObjetoManejadorConsultaSQL objetoManejadorConsultaSQL_local = null;
    ManejadorConsultaSQL manejadorConsultaSQL_local = null;
    ResultSet resultSet_local = null;
    
    try {
      consulta_local = ca.consultaSQLPermitirUtilizarMenuConfiguracion(getTipoUsuario());
      objetoManejadorConsultaSQL_local = new ObjetoManejadorConsultaSQL(getAdministradorBaseDatosSisnet().getConexionBaseDatos(), consulta_local, "seleccion");
      
      if(objetoManejadorConsultaSQL_local.getConeccionBaseDatos().getConexion().isClosed()){
    	  objetoManejadorConsultaSQL_local.getConeccionBaseDatos().conectarBaseDatos();
      }
      manejadorConsultaSQL_local = new ManejadorConsultaSQL(objetoManejadorConsultaSQL_local);
      if (manejadorConsultaSQL_local.ejecutarConsulta() == 0) {
        resultSet_local = manejadorConsultaSQL_local.getResultSet();
        if (resultSet_local.next()) {
          permisoUtilizarMenuConfiguracion_local = resultSet_local.getBoolean("fldpermitirutilizarmenu");
        }
      } else {
        getAdministradorBaseDatosSisnet().setError(manejadorConsultaSQL_local.getErrorConsultaSQL());
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      consulta_local = null;
      resultSet_local = null;
      manejadorConsultaSQL_local = null;
      objetoManejadorConsultaSQL_local = null;
    } 
    
    return permisoUtilizarMenuConfiguracion_local;
  }
  private String obtenerBloquePermisosAplicacion(Aplicacion pAplicacion) {
    String bloquePermisos_local = "";
    int posicionInicialNivel_local = -1;
    String permisosUsuario_local = null;
    String descripcionNivel_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return bloquePermisos_local;
    }
    
    try {
      permisosUsuario_local = getPermisosTipoUsuario();
      posicionInicialNivel_local = 0;
      if (!mc.sonCadenasIguales(pAplicacion.getTituloAplicacion(), "")) {
        while (mc.verificarExistenciaSubCadenaAPartirPosicion(permisosUsuario_local, "$A", posicionInicialNivel_local)) {
          
          posicionInicialNivel_local = mc.obtenerPosicionSubCadenaAPartirPosicion(permisosUsuario_local, "$A", posicionInicialNivel_local);
          
          descripcionNivel_local = ap.obtenerContenidoEntreComillas(obtenerCadenaPermisoAcceso(permisosUsuario_local, posicionInicialNivel_local));
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pAplicacion.getTituloAplicacion(), descripcionNivel_local)) {
            bloquePermisos_local = obtenerBloquePermisosAPartirPosicion(permisosUsuario_local, "$A", posicionInicialNivel_local);
            
            break;
          } 
          posicionInicialNivel_local++;
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisosUsuario_local = null;
      descripcionNivel_local = null;
    } 
    
    return bloquePermisos_local;
  }
  private String obtenerBloquePermisosGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    String bloquePermisos_local = "";
    int posicionInicialNivel_local = -1;
    String permisosAplicacion_local = null;
    String descripcionNivel_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return bloquePermisos_local;
    }
    
    try {
      permisosAplicacion_local = obtenerBloquePermisosAplicacion(pGrupoInformacion.getAplicacion());
      posicionInicialNivel_local = 0;
      if (!mc.sonCadenasIguales(pGrupoInformacion.getDescripcionGrupoInformacion(), "")) {
        while (mc.verificarExistenciaSubCadenaAPartirPosicion(permisosAplicacion_local, "$G", posicionInicialNivel_local)) {
          
          posicionInicialNivel_local = mc.obtenerPosicionSubCadenaAPartirPosicion(permisosAplicacion_local, "$G", posicionInicialNivel_local);
          
          descripcionNivel_local = ap.obtenerContenidoEntreComillas(obtenerCadenaPermisoAcceso(permisosAplicacion_local, posicionInicialNivel_local));
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pGrupoInformacion.getDescripcionGrupoInformacion(), descripcionNivel_local)) {
            bloquePermisos_local = obtenerBloquePermisosAPartirPosicion(permisosAplicacion_local, "$G", posicionInicialNivel_local);
            
            break;
          } 
          
          posicionInicialNivel_local++;
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      descripcionNivel_local = null;
      permisosAplicacion_local = null;
    } 
    
    return bloquePermisos_local;
  }
  private String obtenerBloquePermisosCampo(Campo pCampo) {
    String bloquePermisos_local = "";
    int posicionInicialNivel_local = -1;
    String permisosGrupoInformacion_local = null;
    String descripcionNivel_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return bloquePermisos_local;
    }
    
    try {
      permisosGrupoInformacion_local = obtenerBloquePermisosGrupoInformacion(pCampo.getGrupoInformacion());
      posicionInicialNivel_local = 0;
      if (!mc.sonCadenasIguales(pCampo.getEtiquetaCampo(), "")) {
        while (mc.verificarExistenciaSubCadenaAPartirPosicion(permisosGrupoInformacion_local, "$C", posicionInicialNivel_local)) {
          
          posicionInicialNivel_local = mc.obtenerPosicionSubCadenaAPartirPosicion(permisosGrupoInformacion_local, "$C", posicionInicialNivel_local);
          
          descripcionNivel_local = ap.obtenerContenidoEntreComillas(obtenerCadenaPermisoAcceso(permisosGrupoInformacion_local, posicionInicialNivel_local));
          
          if (mc.sonCadenasIgualesIgnorarMayusculas(pCampo.getEtiquetaCampo(), descripcionNivel_local)) {
            bloquePermisos_local = obtenerBloquePermisosAPartirPosicion(permisosGrupoInformacion_local, "$C", posicionInicialNivel_local);
            
            break;
          } 
          posicionInicialNivel_local++;
        } 
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      descripcionNivel_local = null;
      permisosGrupoInformacion_local = null;
    } 
    
    return bloquePermisos_local;
  }
  private String obtenerPermisosAplicacion(Aplicacion pAplicacion) {
    String permisosAplicacion_local = "";
    String bloquePermisosAplicacion_local = null;
    String permiso_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisosAplicacion_local;
    }
    
    try {
      bloquePermisosAplicacion_local = obtenerBloquePermisosAplicacion(pAplicacion);
      listaCadenas_local = mc.obtenerParrafoComoListaCadenas(bloquePermisosAplicacion_local);
      if (listaCadenas_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCadenas_local.iterator();
        while (iterador_local.hasNext()) {
          permiso_local = (String)iterador_local.next();
          if (mc.comienzaCon(permiso_local, "$A")) {
            permisosAplicacion_local = obtenerPermisos(permiso_local);
            break;
          } 
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permiso_local = null;
      iterador_local = null;
      listaCadenas_local = null;
      bloquePermisosAplicacion_local = null;
    } 
    
    return permisosAplicacion_local;
  }
  private String obtenerPermisosGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    String permisosGrupoInformacion_local = "";
    String bloquePermisosGrupoInformacion_local = null;
    String permiso_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return permisosGrupoInformacion_local;
    }
    
    try {
      bloquePermisosGrupoInformacion_local = obtenerBloquePermisosGrupoInformacion(pGrupoInformacion);
      listaCadenas_local = mc.obtenerParrafoComoListaCadenas(bloquePermisosGrupoInformacion_local);
      if (listaCadenas_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCadenas_local.iterator();
        while (iterador_local.hasNext()) {
          permiso_local = (String)iterador_local.next();
          if (mc.comienzaCon(permiso_local, "$G")) {
            permisosGrupoInformacion_local = obtenerPermisos(permiso_local);
            break;
          } 
        } 
      } 
      if (mc.sonCadenasIguales(permisosGrupoInformacion_local, "")) {
        permisosGrupoInformacion_local = obtenerPermisosAplicacion(pGrupoInformacion.getAplicacion());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permiso_local = null;
      iterador_local = null;
      listaCadenas_local = null;
      bloquePermisosGrupoInformacion_local = null;
    } 
    
    return permisosGrupoInformacion_local;
  }
  private String obtenerPermisosCampo(Campo pCampo) {
    String permisosCampo_local = "";
    String bloquePermisosCampo_local = null;
    String permiso_local = null;
    ListaCadenas listaCadenas_local = null;
    Iterator iterador_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return permisosCampo_local;
    }
    
    try {
      bloquePermisosCampo_local = obtenerBloquePermisosCampo(pCampo);
      listaCadenas_local = mc.obtenerParrafoComoListaCadenas(bloquePermisosCampo_local);
      if (listaCadenas_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCadenas_local.iterator();
        while (iterador_local.hasNext()) {
          permiso_local = (String)iterador_local.next();
          if (mc.comienzaCon(permiso_local, "$C")) {
            permisosCampo_local = obtenerPermisos(permiso_local);
            break;
          } 
        } 
      } 
      if (mc.sonCadenasIguales(permisosCampo_local, "")) {
        permisosCampo_local = obtenerPermisosGrupoInformacion(pCampo.getGrupoInformacion());
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permiso_local = null;
      iterador_local = null;
      listaCadenas_local = null;
      bloquePermisosCampo_local = null;
    } 
    return permisosCampo_local;
  }
  public boolean verificarPermisoVerRegistrosAplicacion(Aplicacion pAplicacion) {
    boolean permisoVer_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoVer_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoVer_local = (verificarPermisoVer(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoVer_local;
  }
  public boolean verificarPermisoVerGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    boolean permisoVer_local = false;
    String permisos_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return permisoVer_local;
    }
    
    try {
      permisos_local = obtenerPermisosGrupoInformacion(pGrupoInformacion);
      permisoVer_local = (verificarPermisoVer(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoVer_local;
  }
  public boolean verificarPermisoVerCampo(Campo pCampo) {
    boolean permisoVer_local = false;
    String permisos_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return permisoVer_local;
    }
    
    try {
      permisos_local = obtenerPermisosCampo(pCampo);
      permisoVer_local = (verificarPermisoVer(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    return permisoVer_local;
  }
  public boolean verificarPermisoIncluirRegistrosAplicacion(Aplicacion pAplicacion) {
    boolean permisoIncluir_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoIncluir_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoIncluir_local = (verificarPermisoIncluir(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoIncluir_local;
  }
  public boolean verificarPermisoOpcionReportes(Aplicacion pAplicacion) {
    boolean permisoOpcionReportes_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoOpcionReportes_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoOpcionReportes_local = (verificarPermisoOpcionReportes(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoOpcionReportes_local;
  }
  public boolean verificarPermisoImpresionMasiva(Aplicacion pAplicacion) {
    boolean permisoImpresionMasiva_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoImpresionMasiva_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoImpresionMasiva_local = (verificarPermisoImpresionMasiva(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoImpresionMasiva_local;
  }
  public boolean verificarPermisoImportacion(Aplicacion pAplicacion) {
    boolean permisoImportacion_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoImportacion_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoImportacion_local = (verificarPermisoImportacion(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoImportacion_local;
  }
  public boolean verificarPermisoIncluirGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    boolean permisoIncluir_local = false;
    String permisos_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return permisoIncluir_local;
    }
    
    try {
      permisos_local = obtenerPermisosGrupoInformacion(pGrupoInformacion);
      permisoIncluir_local = (verificarPermisoIncluir(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoIncluir_local;
  }
  public boolean verificarPermisoModificarRegistrosAplicacion(Aplicacion pAplicacion) {
    boolean permisoModificar_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoModificar_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoModificar_local = (verificarPermisoModificar(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoModificar_local;
  }
  public boolean verificarPermisoModificarGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    boolean permisoModificar_local = false;
    String permisos_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return permisoModificar_local;
    }
    
    try {
      permisos_local = obtenerPermisosGrupoInformacion(pGrupoInformacion);
      permisoModificar_local = (verificarPermisoModificar(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    return permisoModificar_local;
  }
  public boolean verificarPermisoModificarCampo(Campo pCampo) {
    boolean permisoModificar_local = false;
    String permisos_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return permisoModificar_local;
    }
    
    try {
      permisos_local = obtenerPermisosCampo(pCampo);
      permisoModificar_local = (verificarPermisoModificar(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    return permisoModificar_local;
  }
  public boolean verificarPermisoBorrarRegistrosAplicacion(Aplicacion pAplicacion) {
    boolean permisoBorrar_local = false;
    String permisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return permisoBorrar_local;
    }
    
    try {
      permisos_local = obtenerPermisosAplicacion(pAplicacion);
      permisoBorrar_local = (verificarPermisoBorrar(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoBorrar_local;
  }
  public boolean verificarPermisoBorrarGrupoInformacion(GrupoInformacion pGrupoInformacion) {
    boolean permisoBorrar_local = false;
    String permisos_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return permisoBorrar_local;
    }
    
    try {
      permisos_local = obtenerPermisosGrupoInformacion(pGrupoInformacion);
      permisoBorrar_local = (verificarPermisoBorrar(permisos_local) || getTipoUsuario() == 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      permisos_local = null;
    } 
    
    return permisoBorrar_local;
  }
  public boolean verificarExistenciaGrupoInformacionNoMultiplePermisoIncluir(int pIdAplicacion) {
    boolean existePermisoIncluir_local = false;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
    	MotorAplicacion m = getMotorAplicacion();
    	if(m == null)
    	{
    		m = null;
    	}
      listaGrupoInformacion_local = m.obtenerListaGruposInformacionNoMultiplesAplicacion(pIdAplicacion);
      if (listaGrupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaGrupoInformacion_local.iterator();
        while (iterador_local.hasNext()) {
          grupoInformacion_local = (GrupoInformacion)iterador_local.next();
          existePermisoIncluir_local = verificarPermisoIncluirGrupoInformacion(grupoInformacion_local);
          if (existePermisoIncluir_local) {
            break;
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
      listaGrupoInformacion_local = null;
    } 
    
    return existePermisoIncluir_local;
  }
  public boolean verificarExistenciaGrupoInformacionNoMultiplePermisoModificar(int pIdAplicacion) {
    boolean existePermisoModificar_local = false;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
      listaGrupoInformacion_local = getMotorAplicacion().obtenerListaGruposInformacionNoMultiplesAplicacion(pIdAplicacion);
      if (listaGrupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaGrupoInformacion_local.iterator();
        while (iterador_local.hasNext()) {
          grupoInformacion_local = (GrupoInformacion)iterador_local.next();
          existePermisoModificar_local = verificarPermisoModificarGrupoInformacion(grupoInformacion_local);
          if (existePermisoModificar_local) {
            break;
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
      listaGrupoInformacion_local = null;
    } 
    
    return existePermisoModificar_local;
  }
  public boolean verificarExistenciaGrupoInformacionNoMultiplePermisoBorrar(int pIdAplicacion) {
    boolean existePermisoBorrar_local = false;
    ListaGrupoInformacion listaGrupoInformacion_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    
    try {
      listaGrupoInformacion_local = getMotorAplicacion().obtenerListaGruposInformacionNoMultiplesAplicacion(pIdAplicacion);
      if (listaGrupoInformacion_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaGrupoInformacion_local.iterator();
        while (iterador_local.hasNext()) {
          grupoInformacion_local = (GrupoInformacion)iterador_local.next();
          existePermisoBorrar_local = verificarPermisoBorrarGrupoInformacion(grupoInformacion_local);
          if (existePermisoBorrar_local) {
            break;
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      grupoInformacion_local = null;
      listaGrupoInformacion_local = null;
    } 
    
    return existePermisoBorrar_local;
  }
  public boolean verificarExistenciaCampoPermisoVer(int pIdGrupoInformacion) {
    boolean existePermisoVer_local = false;
    ListaCampo listaCampo_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposGrupoInformacion(pIdGrupoInformacion);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          existePermisoVer_local = verificarPermisoVerCampo(campo_local);
          if (existePermisoVer_local) {
            break;
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
    } 
    
    return existePermisoVer_local;
  }
  public boolean verificarExistenciaCampoPermisoModificar(int pIdGrupoInformacion) {
    boolean existePermisoModificar_local = false;
    ListaCampo listaCampo_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposGrupoInformacion(pIdGrupoInformacion);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          existePermisoModificar_local = verificarPermisoModificarCampo(campo_local);
          if (existePermisoModificar_local) {
            break;
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
    } 
    
    return existePermisoModificar_local;
  }
  public boolean verificarExistenciaCampoGrupoNoMultiplePermisoModificar(int pIdAplicacion) {
    boolean existePermisoModificar_local = false;
    ListaCampo listaCampo_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    try {
      listaCampo_local = getMotorAplicacion().obtenerListaCamposGruposInformacionNoMultiples(pIdAplicacion);
      if (listaCampo_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaCampo_local.iterator();
        while (iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
          existePermisoModificar_local = verificarPermisoModificarCampo(campo_local);
          if (existePermisoModificar_local) {
            break;
          }
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      campo_local = null;
      iterador_local = null;
      listaCampo_local = null;
    } 
    
    return existePermisoModificar_local;
  }
  public ListaCadenas obtenerListaRestriccionesAplicacion(Aplicacion pAplicacion, boolean pEsSoloRegistrosDonde) {
    ListaCadenas listaRestricciones_local = null;
    String bloqueRestricciones_local = null;
    String bloquePermisos_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return listaRestricciones_local;
    }
    
    try {
      bloquePermisos_local = obtenerBloquePermisosAplicacion(pAplicacion);
      if (pEsSoloRegistrosDonde) {
        bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "SOLO REGISTROS DONDE");
        
        if (mc.esCadenaVacia(bloqueRestricciones_local)) {
          bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "ONLY RECORDS WHERE");
        }
      } else {
        
        bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "TODOS LOS REGISTROS MENOS");
        
        if (mc.esCadenaVacia(bloqueRestricciones_local)) {
          bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "ALL THE RECORDS EXCEPT");
        }
      } 
      
      if (mc.verificarExistenciaSubCadena(bloqueRestricciones_local, "$G")) {
        bloqueRestricciones_local = mc.obtenerSubCadena(bloqueRestricciones_local, 0, mc.obtenerPosicionSubCadena(bloqueRestricciones_local, "$G"));
      }
      
      if (mc.verificarExistenciaSubCadena(bloqueRestricciones_local, "$C")) {
        bloqueRestricciones_local = mc.obtenerSubCadena(bloqueRestricciones_local, 0, mc.obtenerPosicionSubCadena(bloqueRestricciones_local, "$C"));
      }
      
      listaRestricciones_local = mc.obtenerParrafoComoListaCadenas(bloqueRestricciones_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaRestricciones_local;
  }
  public ListaCadenas obtenerListaRestriccionesCampoValores(Campo pCampo) {
    ListaCadenas listaRestricciones_local = null;
    String bloqueRestricciones_local = null;
    String bloquePermisos_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaRestricciones_local;
    }
    
    try {
      bloquePermisos_local = obtenerBloquePermisosCampo(pCampo);
      bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "SOLO VALORES");
      if (mc.sonCadenasIguales(bloqueRestricciones_local, "")) {
        bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "ONLY VALUES");
      }
      
      bloqueRestricciones_local = mc.reemplazarCadena(bloqueRestricciones_local, String.valueOf('"'), "");
      
      listaRestricciones_local = mc.obtenerParrafoComoListaCadenas(bloqueRestricciones_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaRestricciones_local;
  }
  public ListaCadenas obtenerListaRestriccionesCampoValoresMenos(Campo pCampo) {
    ListaCadenas listaRestricciones_local = null;
    String bloqueRestricciones_local = null;
    String bloquePermisos_local = null;
    
    if (pCampo == ConstantesGeneral.VALOR_NULO) {
      return listaRestricciones_local;
    }
    
    try {
      bloquePermisos_local = obtenerBloquePermisosCampo(pCampo);
      bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "TODOS LOS VALORES MENOS");
      if (mc.sonCadenasIguales(bloqueRestricciones_local, "")) {
        bloqueRestricciones_local = obtenerBloqueRestricciones(bloquePermisos_local, "ALL THE VALUES EXCEPT");
      }
      
      bloqueRestricciones_local = mc.reemplazarCadena(bloqueRestricciones_local, String.valueOf('"'), "");
      
      listaRestricciones_local = mc.obtenerParrafoComoListaCadenas(bloqueRestricciones_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaRestricciones_local;
  }
  public int obtenerNumeroCamposVisibles(ListaCampo pListaCampo) {
    int numeroCampos_local = 0;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return numeroCampos_local;
    }
    
    try {
      iterator_local = pListaCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (verificarPermisoVerCampo(campo_local)) {
          numeroCampos_local++;
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
    
    return numeroCampos_local;
  }
  public ListaCampo extraerCamposPermisoModificar(ListaCampo pListaTCampo) {
    ListaCampo listaCampo_local = null;
    Iterator iterator_local = null;
    Campo campo_local = null;
    
    if (pListaTCampo == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      iterator_local = pListaTCampo.iterator();
      while (iterator_local.hasNext()) {
        campo_local = (Campo)iterator_local.next();
        if (verificarPermisoModificarCampo(campo_local)) {
          listaCampo_local.adicionar(campo_local);
        }
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterator_local = null;
      campo_local = null;
    } 
    
    return listaCampo_local;
  }
  public ListaCadenas obtenerListaCamposOpcionConsultaPredeterminados(Aplicacion pAplicacion) {
    ListaCadenas listaEtiquetasCamposOpcionConsulta_local = null;
    int posicionInicialOpcion_local = -1;
    int posicionFinalOpcion_local = -1;
    String bloquePermisosAplicacion_local = null;
    String bloqueCamposOpcionConsulta_local = null;
    
    if (pAplicacion == ConstantesGeneral.VALOR_NULO) {
      return listaEtiquetasCamposOpcionConsulta_local;
    }
    
    try {
      bloquePermisosAplicacion_local = obtenerBloquePermisosAplicacion(pAplicacion);
      if (mc.verificarExistenciaSubCadena(bloquePermisosAplicacion_local, "$O") && mc.verificarExistenciaSubCadena(bloquePermisosAplicacion_local, "$FINO")) {
        
        posicionInicialOpcion_local = mc.obtenerPosicionSubCadena(bloquePermisosAplicacion_local, "$O") + mc.obtenerLongitudCadena("$O");
        
        posicionFinalOpcion_local = mc.obtenerPosicionSubCadena(bloquePermisosAplicacion_local, "$FINO");
        
        bloqueCamposOpcionConsulta_local = mc.obtenerSubCadena(bloquePermisosAplicacion_local, posicionInicialOpcion_local, posicionFinalOpcion_local);
        
        listaEtiquetasCamposOpcionConsulta_local = mc.obtenerParrafoComoListaCadenas(bloqueCamposOpcionConsulta_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloquePermisosAplicacion_local = null;
      bloqueCamposOpcionConsulta_local = null;
    } 
    
    return listaEtiquetasCamposOpcionConsulta_local;
  }
  private ListaCadenas obtenerListaVariablesPerfilUsuario() {
    ListaCadenas listaVariablesPerfilUsuario_local = null;
    int posicionInicial_local = -1;
    int posicionFinal_local = -1;
    String bloquePermisos_local = null;
    String bloqueVariables_local = null;
    
    try {
      //bloquePermisos_local = obtenerPermisosTipoUsuario();
      bloquePermisos_local = getPermisosTipoUsuario();
      if (mc.verificarExistenciaSubCadena(bloquePermisos_local, "$V") && mc.verificarExistenciaSubCadena(bloquePermisos_local, "$FINV")) {
        
        posicionInicial_local = mc.obtenerPosicionSubCadena(bloquePermisos_local, "$V") + mc.obtenerLongitudCadena("$V");
        
        posicionFinal_local = mc.obtenerPosicionSubCadena(bloquePermisos_local, "$FINV");
        
        bloqueVariables_local = mc.obtenerSubCadena(bloquePermisos_local, posicionInicial_local, posicionFinal_local);
        listaVariablesPerfilUsuario_local = mc.obtenerParrafoComoListaCadenas(bloqueVariables_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      bloquePermisos_local = null;
      bloqueVariables_local = null;
    } 
    
    return listaVariablesPerfilUsuario_local;
  }
  public ListaVariablesSistema obtenerListaVariablesSistemaPerfilUsuario() {
    ListaVariablesSistema listaVariablesSistema_local = null;
    int posicionIgual_local = -1;
    String variablePerfil_local = null;
    String nombreVariable_local = null;
    String valorVariable_local = null;
    String tipoDatoVariable_local = null;
    ListaCadenas listaVariablesPerfil_local = null;
    Iterator iterador_local = null;
    
    try {
      listaVariablesPerfil_local = obtenerListaVariablesPerfilUsuario();
      if (listaVariablesPerfil_local != ConstantesGeneral.VALOR_NULO) {
        iterador_local = listaVariablesPerfil_local.iterator();
        listaVariablesSistema_local = new ListaVariablesSistema();
        while (iterador_local.hasNext()) {
          variablePerfil_local = (String)iterador_local.next();
          if (mc.verificarExistenciaSubCadena(variablePerfil_local, String.valueOf('='))) {
            posicionIgual_local = mc.obtenerPosicionSubCadena(variablePerfil_local, String.valueOf('='));
            
            nombreVariable_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(variablePerfil_local, 0, posicionIgual_local));
            
            valorVariable_local = mc.borrarEspaciosLaterales(mc.obtenerSubCadena(variablePerfil_local, posicionIgual_local + 1, mc.obtenerLongitudCadena(variablePerfil_local)));
            
            tipoDatoVariable_local = "";
            if (mc.verificarExistenciaSubCadena(valorVariable_local, String.valueOf('"'))) {
              valorVariable_local = ap.obtenerContenidoEntreComillas(valorVariable_local);
              tipoDatoVariable_local = "T";
            }
            else if (mc.esCadenaNumerica(valorVariable_local, true)) {
              tipoDatoVariable_local = "E";
            }
            else if (mc.esCadenaNumerica(valorVariable_local, false)) {
              tipoDatoVariable_local = "R";
            } 
            
            if (!mc.esCadenaVacia(nombreVariable_local) && !mc.esCadenaVacia(tipoDatoVariable_local)) {
              listaVariablesSistema_local.adicionar(new VariableSistema(nombreVariable_local, tipoDatoVariable_local, valorVariable_local));
            }
          }
        
        } 
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
      valorVariable_local = null;
      variablePerfil_local = null;
      nombreVariable_local = null;
      tipoDatoVariable_local = null;
      listaVariablesPerfil_local = null;
    } 
    
    return listaVariablesSistema_local;
  }
  
//Overriding equals() to compare two Complex objects
  @Override
  public boolean equals(Object o) {
	// If the object is compared with itself then return true 
      if (o == this) {
          return true;
      }

      /* Check if o is an instance of Complex or not
        "null instanceof [type]" also returns false */
      if (!(o instanceof ManejadorPermisoUsuario)) {
          return false;
      }
       
      // typecast o to ManejadorPermisoUsuario so that we can compare data members
      ManejadorPermisoUsuario c = (ManejadorPermisoUsuario) o;
       
      // Compare the data members and return accordingly
      return c.getTipoUsuario() == this.getTipoUsuario();
	  
  } 
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorPermisoUsuario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */