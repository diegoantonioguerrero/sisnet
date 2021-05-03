<%@page import="com.sisnet.aplicacion.generadores.GeneradorPaginaAplicacion"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorRequest"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorSesion"%>
<%@page import="com.sisnet.baseDatos.AdministradorBaseDatos"%>
<%@page import="com.sisnet.constantes.ConstantesGeneral"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>

<html>    
<%  Pagina pagina_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
    GeneradorPaginaAplicacion generadorPaginaAplicacion_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    AdministradorBaseDatos administradorBaseDatosAplicacion_local = null;
    
    try{
        manejadorRequest_local = new ManejadorRequest(request);
        manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
        
        if (manejadorSesion_local.getSesion() != ConstantesGeneral.VALOR_NULO){
            administradorBaseDatosSisnet_local = manejadorSesion_local.obtenerAdministradorBaseDatosSisnet();
            administradorBaseDatosAplicacion_local = manejadorSesion_local.obtenerAdministradorBaseDatosAplicacion();
            generadorPaginaAplicacion_local = new GeneradorPaginaAplicacion(manejadorSesion_local.obtenerTipoUsuarioActual());
            generadorPaginaAplicacion_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
            generadorPaginaAplicacion_local.setAdministradorBaseDatosAplicacion(administradorBaseDatosAplicacion_local);
            generadorPaginaAplicacion_local.setManejadorRequest(manejadorRequest_local);
            generadorPaginaAplicacion_local.setManejadorSesion(manejadorSesion_local);            
            
            pagina_local = generadorPaginaAplicacion_local.obtenerPaginaImportacion();
            out.println(pagina_local.getEncabezadoPagina());
            out.println(pagina_local.getInicioCuerpoPagina());
            out.println(pagina_local.getTitulo());
            out.println(pagina_local.getMensaje());
            out.println(pagina_local.getContenidoFormulario());
            out.println(pagina_local.getBotonesPrincipales());
            out.println(pagina_local.getFinCuerpoPagina());
        }
    }catch(Exception excepcion){
        excepcion.printStackTrace();
    } finally{
        pagina_local = null;
        manejadorSesion_local = null;
        manejadorRequest_local = null;
        generadorPaginaAplicacion_local = null;
        administradorBaseDatosSisnet_local = null;
        administradorBaseDatosAplicacion_local = null;
    }
    %>    
</html>
