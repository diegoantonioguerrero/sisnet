<%@page import="com.sisnet.aplicacion.generadores.GeneradorPaginaAplicacion"%>
<%@page import="com.sisnet.constantes.ConstantesAdministrador"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>

<html>
<%  Pagina pagina_local = null;
    GeneradorPaginaAplicacion generadorPaginaAplicacion_local = null;
        
    try{
       generadorPaginaAplicacion_local = new GeneradorPaginaAplicacion(ConstantesAdministrador.TIPO_USUARIO_ADMINISTRADOR);
       pagina_local = generadorPaginaAplicacion_local.obtenerPaginaMotorAplicacion();
       out.println(pagina_local.getEncabezadoPagina());
       out.println(pagina_local.getInicioCuerpoPagina());
       out.println(pagina_local.getContenidoDatos());
       out.println(pagina_local.getFinCuerpoPagina());
       
    } catch(Exception excepcion){
        excepcion.printStackTrace();
    }
    finally{
        pagina_local = null;
        generadorPaginaAplicacion_local = null;
    }
    %>    
</html>