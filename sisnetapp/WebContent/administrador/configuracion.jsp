<%@page import="com.sisnet.aplicacion.generadores.GeneradorPagina"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorRequest"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorSesion"%>
<%@page import="com.sisnet.constantes.ConstantesAdministrador"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>

<html>
<%  Pagina pagina_local = null;
    GeneradorPagina generadorPagina_local = null;
    ManejadorRequest manejadorRequest_local = null;
    ManejadorSesion manejadorSesion_local = null;
        
    try{
       generadorPagina_local = new GeneradorPagina(ConstantesAdministrador.TIPO_USUARIO_ADMINISTRADOR);
       manejadorRequest_local = new ManejadorRequest(request);
       manejadorSesion_local = new ManejadorSesion(manejadorRequest_local.obtenerNuevaSesion());
       generadorPagina_local.setManejadorRequest(manejadorRequest_local);
       generadorPagina_local.setManejadorSesion(manejadorSesion_local);
            
       pagina_local = generadorPagina_local.obtenerPaginaConfiguracion();
       out.println(pagina_local.getEncabezadoPagina());
       out.println(pagina_local.getInicioCuerpoPagina());
       out.println(pagina_local.getContenidoDatos());
       out.println(pagina_local.getContenidoFormulario());
       out.println(pagina_local.getFinCuerpoPagina());
       
       manejadorSesion_local.cerrarSesion();
    } catch(Exception excepcion){
        excepcion.printStackTrace();
    }
    finally{
        pagina_local = null;
        generadorPagina_local = null;
        manejadorSesion_local = null;
        manejadorRequest_local = null;
    }
    %>    
</html>