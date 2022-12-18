<%@page import="com.sisnet.aplicacion.generadores.GeneradorPagina"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorAplicacion"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorRequest"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorSesion"%>
<%@page import="com.sisnet.baseDatos.AdministradorBaseDatos"%>
<%@page import="com.sisnet.constantes.ConstantesAdministrador"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>

<html>
<%  Pagina pagina_local = null;
    ManejadorAplicacion manejadorAplicacion_local = null;
    GeneradorPagina generadorPagina_local = null;
    AdministradorBaseDatos administradorBaseDatosSisnet_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
        
    try{
        manejadorRequest_local = new ManejadorRequest(request);
        manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerSesion());
        manejadorAplicacion_local = ManejadorAplicacion.getManejadorAplicacion();
        //coco
        String rutaArchivo_local = getServletContext().getRealPath(com.sisnet.constantes.ConstantesBaseDatos.const_rutaConfigPostgres);
        //ConexionPostgres conexionPostgres_local = manejadorAplicacion_local.obtenerConexionPostgres(rutaArchivo_local);
        
        administradorBaseDatosSisnet_local = new AdministradorBaseDatos(
                manejadorAplicacion_local.obtenerConexionBaseDatosSisnet(
                manejadorRequest_local.obtenerNombreRecursoAplicacion(), rutaArchivo_local ));
        generadorPagina_local = new GeneradorPagina(ConstantesAdministrador.TIPO_USUARIO_ADMINISTRADOR);
        generadorPagina_local.setAdministradorBaseDatosSisnet(administradorBaseDatosSisnet_local);
        generadorPagina_local.setManejadorRequest(manejadorRequest_local);
        generadorPagina_local.setManejadorSesion(manejadorSesion_local);                  
        
        pagina_local = generadorPagina_local.obtenerPaginaLogin();
        out.println(pagina_local.getEncabezadoPagina());
        out.println(pagina_local.getInicioCuerpoPagina());
        out.println(pagina_local.getContenidoDatos());
        out.println(pagina_local.getMensaje());
        out.println(pagina_local.getContenidoFormulario());
        out.println(pagina_local.getBotonesSecundarios());
        out.println(pagina_local.getFinCuerpoPagina());
        administradorBaseDatosSisnet_local.getConexionBaseDatos().cerrarConeccionBaseDatos();
    } catch(Exception excepcion){
        excepcion.printStackTrace();
    }
    finally{
        pagina_local = null;
        generadorPagina_local = null;
        manejadorSesion_local = null;
        manejadorRequest_local = null;
        manejadorAplicacion_local = null;
        administradorBaseDatosSisnet_local = null;
    }
    %>    
</html>