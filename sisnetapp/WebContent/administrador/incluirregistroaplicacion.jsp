<%@page import="com.sisnet.aplicacion.generadores.GeneradorPagina"%>
<%@page import="com.sisnet.aplicacion.generadores.GeneradorPaginaAplicacion"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorAplicacion"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorRequest"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorSesion"%>
<%@page import="com.sisnet.baseDatos.AdministradorBaseDatos"%>
<%@page import="com.sisnet.constantes.ConstantesGeneral"%>
<%@page import="com.sisnet.constantes.ConstantesAdministrador"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorCadenas"%>

<html>
<%
    boolean esModificacion_local = ConstantesGeneral.VALOR_FALSO;
    Pagina pagina_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GeneradorPagina generadorPagina_local = null;
    GeneradorPaginaAplicacion generadorPaginaAplicacion_local = null;
    ManejadorCadenas manejadorCadenas_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    
    try {
        manejadorRequest_local = new ManejadorRequest(request);
        manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
        manejadorCadenas_local = ManejadorCadenas.getManejadorCadenas();

        if (manejadorSesion_local != ConstantesGeneral.VALOR_NULO){
            esModificacion_local = manejadorCadenas_local.sonCadenasIguales(manejadorSesion_local.obtenerEstadoActual(), 
                    ConstantesAdministrador.const_EstadoModificando);
            administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
            administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();

            if(manejadorSesion_local.esConfiguracion()){
                generadorPagina_local = new GeneradorPagina(ConstantesAdministrador.TIPO_USUARIO_ADMINISTRADOR);
                generadorPagina_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
                generadorPagina_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
                generadorPagina_local.setManejadorRequest(manejadorRequest_local);
                generadorPagina_local.setManejadorSesion(manejadorSesion_local);            
                
                pagina_local = generadorPagina_local.obtenerPaginaIncluirRegistro(manejadorSesion_local.obtenerGrupoInformacionActual(), 
                        esModificacion_local);
            } else {
                generadorPaginaAplicacion_local = new GeneradorPaginaAplicacion(manejadorSesion_local.obtenerTipoUsuarioActual());
                generadorPaginaAplicacion_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
                generadorPaginaAplicacion_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
                generadorPaginaAplicacion_local.setManejadorRequest(manejadorRequest_local);
                generadorPaginaAplicacion_local.setManejadorSesion(manejadorSesion_local);                            
                
                pagina_local = generadorPaginaAplicacion_local.obtenerPaginaIncluirRegistro(
                        manejadorSesion_local.obtenerGrupoInformacionActual(), esModificacion_local);
            }
            
            out.println(pagina_local.getEncabezadoPagina());
            out.println(pagina_local.getInicioCuerpoPagina());
            out.println(pagina_local.getTitulo());
            out.println(pagina_local.getMensaje());
            out.println(pagina_local.getContenidoFormulario());
            out.println(pagina_local.getContenidoDatos());
            out.println(pagina_local.getBotonesPrincipales());
            out.println(pagina_local.getBotonesSecundarios());
            out.println(pagina_local.getFinCuerpoPagina());
        }
        else
        {
        	response.sendRedirect(response.encodeRedirectURL("login.jsp"));
        }
    } catch(Exception excepcion){
        excepcion.printStackTrace();
    } finally {
        pagina_local = null;
        manejadorSesion_local = null;
        generadorPagina_local = null;
        manejadorRequest_local = null;
        manejadorCadenas_local = null;
        generadorPaginaAplicacion_local = null;
        administradorBaseDatosSisnet_local = null;
        administradorBaseDatosAplicacion_local = null;
    }
%>
</html>