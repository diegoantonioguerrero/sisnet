<%@page import="com.sisnet.aplicacion.generadores.GeneradorPagina"%>
<%@page import="com.sisnet.constantes.ConstantesAdministrador"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  Pagina pagina_local = null;
    GeneradorPagina generadorPagina_local = null;
        
    try{
       generadorPagina_local = new GeneradorPagina(ConstantesAdministrador.TIPO_USUARIO_ADMINISTRADOR);
       pagina_local = generadorPagina_local.obtenerPaginaInicioAplicacion();
       out.println(pagina_local.getEncabezadoPagina());
       out.println(pagina_local.getInicioCuerpoPagina());
       out.println(pagina_local.getContenidoDatos());
       out.println(pagina_local.getFinCuerpoPagina());       
    } catch(Exception excepcion){
        excepcion.printStackTrace();
    }
    finally{
        pagina_local = null;
        generadorPagina_local = null;
    }
    %>    
</html>