package com.sisnet.servlets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import com.sisnet.aplicacion.generadores.GeneradorPaginaAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorRequest;
import com.sisnet.aplicacion.manejadores.ManejadorSesion;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.constantes.ConstantesAdministrador;
import com.sisnet.objetosManejo.manejoPaginaJsp.Pagina;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;

public class EditorJSP {
private JspWriter out;
private HttpServletRequest request;
	public EditorJSP(JspWriter out, HttpServletRequest request){
		this.out = out;
		this.request = request;
		
	}
	public void renderPage(){

		boolean esModificacion_local = ConstantesGeneral.VALOR_FALSO;
	    Pagina pagina_local = null;
	    ManejadorRequest manejadorRequest_local = null;
	    ManejadorSesion manejadorSesion_local = null;
	    GeneradorPaginaAplicacion generadorPaginaAplicacion_local = null;
	    ManejadorCadenas manejadorCadenas_local = null;
	    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
	    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
	    
	    try{
	        manejadorRequest_local = new ManejadorRequest(request);
	        manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
	        manejadorCadenas_local = ManejadorCadenas.getManejadorCadenas();
	        
	        if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO){
	            esModificacion_local = manejadorCadenas_local.sonCadenasIguales(manejadorSesion_local.obtenerEstadoActual(), ConstantesAdministrador.const_EstadoModificando);
	            administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
	            administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
	            generadorPaginaAplicacion_local = new GeneradorPaginaAplicacion(manejadorSesion_local.obtenerTipoUsuarioActual());
	            generadorPaginaAplicacion_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
	            generadorPaginaAplicacion_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
	            generadorPaginaAplicacion_local.setManejadorRequest(manejadorRequest_local);
	            generadorPaginaAplicacion_local.setManejadorSesion(manejadorSesion_local);            
	            GrupoInformacion gi = manejadorSesion_local.obtenerGrupoInformacionActual();
	            pagina_local = generadorPaginaAplicacion_local.obtenerPaginaIncluirActuacion(gi, esModificacion_local);
	            out.println(pagina_local.getEncabezadoPagina());
	            out.println(pagina_local.getInicioCuerpoPagina());
	            out.println(pagina_local.getTitulo());
	            out.println(pagina_local.getMensaje());
	            out.println(pagina_local.getContenidoFormulario());
	            out.println(pagina_local.getFinCuerpoPagina());
	        }
	        
	    } catch (Exception excepcion){
	        excepcion.printStackTrace();
	    } finally{
	        pagina_local = null;
	        manejadorSesion_local = null;
	        manejadorRequest_local = null;
	        generadorPaginaAplicacion_local = null;
	        manejadorCadenas_local = null;
	        administradorBaseDatosSisnet_local = null;
	        administradorBaseDatosAplicacion_local = null;
	    }
		
	}
}



