<%@page import="com.sisnet.aplicacion.generadores.GeneradorPagina"%>
<%@page import="com.sisnet.constantes.ConstantesAdministrador"%>
<%@page import="com.sisnet.objetosManejo.manejoPaginaJsp.Pagina"%>
<%@page import="com.sisnet.aplicacion.manejadores.ManejadorCadenas"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  Pagina pagina_local = null;
    GeneradorPagina generadorPagina_local = null;
        
    try{
    	/*
    	ManejadorCadenas mg = ManejadorCadenas.getManejadorCadenas();
    	String ss = mg.formatearNumeroEntero(123123);

    	ss = mg.formatearNumeroEntero(12);
    	ss = mg.formatearNumeroEntero(80259532);
    	ss = mg.formatearNumeroEntero(576);
    	
    	mg.formatearNumeroEntero(-123123);
    	ss = mg.formatearNumeroEntero(-12);
    	ss = mg.formatearNumeroEntero(-80259532);
    	ss = mg.formatearNumeroEntero(-576);
    	
    	ss = mg.formatearNumeroReal(0);
    	ss = mg.formatearNumeroReal(123123);
    	ss = mg.formatearNumeroReal(12);
    	ss = mg.formatearNumeroReal(80259532);
    	ss = mg.formatearNumeroReal(576);
    	
    	ss = mg.formatearNumeroReal(123123.2);
    	ss = mg.formatearNumeroReal(12.45);
    	ss = mg.formatearNumeroReal(80259532.1234);
    	ss = mg.formatearNumeroReal(576.23565);
    	
    	ss = mg.formatearNumeroReal(-1);
    	ss = mg.formatearNumeroReal(-123123);
    	ss = mg.formatearNumeroReal(-12);
    	ss = mg.formatearNumeroReal(-80259532);
    	ss = mg.formatearNumeroReal(-80259532.12);
    	ss = mg.formatearNumeroReal(-576);
    	*/
    	
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