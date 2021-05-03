/*                              FORMATOS FECHAS                           */
/* ---------------------------------------------------------------------- */
var nombreMes = ["enero", "febrero", "marzo", "abril", "mayo", "junio","julio","agosto", "septiembre", "octubre", "noviembre", "diciembre"];
var nombreDia = ["Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"];


/* Devuelve la cadena en minúsculas
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoEstiloMIN(pCadena){
    var cadena_local = '';
    if (pCadena == ''){
        return cadena_local;
    }
    try{
        cadena_local = pCadena.toLowerCase();
    } catch (e){
    }
    return (cadena_local);
}

/* Devuelve la cadena en mayúsculas
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoEstiloMAY(pCadena){
    var cadena_local = '';
    if (pCadena == ''){
        return cadena_local;
    }
    try{
        cadena_local = pCadena.toUpperCase();
    } catch (e){
    }
    return (cadena_local);
}

/* Devuelve la cadena con la prmera letra en mayuscula
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoEstiloCAP(pCadena){
    var cadena_local = '';
    var cadenaEnMinusculas_local = '';
    if (pCadena == ''){
        return cadena_local;
    }
    try{
        cadenaEnMinusculas_local = obtenerFormatoEstiloMIN(pCadena);
        cadena_local = obtenerFormatoEstiloMAY(cadenaEnMinusculas_local.substring(0,1)) + cadenaEnMinusculas_local.substring(1, cadenaEnMinusculas_local.length);
    } catch (e){
    }
    return (cadena_local);
}

/* Devuelve la cadena en estilo tipo título
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoEstiloTIT(pCadena){
    var cadena_local = '';
    var estilo_local = '';
    if (pCadena == ''){
        return cadena_local;
    }
    try{
        estilo_local = pCadena.split(' ');
        for(var i = 0; i < estilo_local.length; i++) {
            if(i == 0 || estilo_local[i].length > 2){
                cadena_local += obtenerFormatoEstiloMAY(estilo_local[i].substring(0,1)) + estilo_local[i].substring(1, estilo_local[i].length) + ' ';
            } else {
                cadena_local += estilo_local[i] + ' ';
            }
        }
    } catch (e){
    }
    return (cadena_local);
}

/* Da la fecha en formato Dia, Mes, Año
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaDMA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local = '';
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new this.Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local = fecha_local.getFullYear();
    } catch (e){
    }
    return ( dia_local + "/" + mes_local + "/" + anio_local);
}

/* Da la fecha en formato dia, mes y año (25 de enero de 1980)
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaDMA1SPA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local= '';
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local= fecha_local.getFullYear();
    } catch (e){
    }
    return (dia_local + " de " + nombreMes[mes_local - 1]  + " de " + anio_local);
}

/* Devuelve la fecha en formato dia, mes y año (25 dias del mes de enero de 1980)
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaDMA2SPA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local= '';
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local= fecha_local.getFullYear();
    } catch (e){
    }
    return (dia_local + " días del mes de " + nombreMes[mes_local - 1]  + " de " + anio_local);
}

/* Devuelve la fecha en formato dia, mes y año (Lunes, 25 de enero de 1980)
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaDMA3SPA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local= '';
    var diaLetra_local = '';
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local= fecha_local.getFullYear();
        diaLetra_local = fecha_local.getDay();
    } catch (e){
    }
    return (nombreDia[diaLetra_local] + ", " + dia_local + " de " +  nombreMes[mes_local-1] + " de " + anio_local);
}

/* Devuelve la fecha en formato mes, dia y año (Enero 25 de 1980)
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaMDA1SPA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local= '';
    var cadenaMes_local = '';
    var mesLetra_local = '';
    
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local= fecha_local.getFullYear();
        cadenaMes_local = nombreMes[mes_local-1];
        mesLetra_local = obtenerFormatoEstiloMAY(cadenaMes_local.substring(0,1)) + cadenaMes_local.substring(1, cadenaMes_local.length);
    } catch (e){
    }
    return (mesLetra_local + " " + dia_local + " de " + anio_local);
}

/* Devuelve la fecha en formato mes, dia y año (Ene 25 de 1980)
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaMDA2SPA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local= '';
    var cadenaMes_local = '';
    var mesLetra_local = '';
    
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local= fecha_local.getFullYear();
        cadenaMes_local = nombreMes[mes_local-1];
        mesLetra_local = obtenerFormatoEstiloMAY(cadenaMes_local.substring(0,1)) + cadenaMes_local.substring(1,3);
    } catch (e){
    }
    return (mesLetra_local + " " + dia_local + "/" + anio_local);
}

/* Devuelve la fecha en formato mes, dia y año (Ene 25/80)
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoFechaMDA3SPA(pFecha){
    var fecha_local = '';
    var dia_local = '';
    var mes_local = ''; 
    var anio_local= '';
    var cadenaMes_local = '';
    var mesLetra_local = '';
    var anioCadena_local = "";
    
    if (pFecha == ''){
        return fecha_local;
    }
    try{
        var arrayFecha_local = pFecha.split("/");
        anio_local = arrayFecha_local[0];
        mes_local = arrayFecha_local[1];
        dia_local = arrayFecha_local[2];
        fecha_local = new Date(anio_local, mes_local-1, dia_local);
        dia_local = fecha_local.getDate();
        dia_local = this.completarNumero(dia_local);
        mes_local = fecha_local.getMonth() + 1; 
        mes_local = this.completarNumero(mes_local);
        anio_local= fecha_local.getFullYear();
        cadenaMes_local = nombreMes[mes_local-1];
        mesLetra_local = obtenerFormatoEstiloMAY(cadenaMes_local.substring(0,1)) + cadenaMes_local.substring(1,3);
        anioCadena_local = String(anio_local);
        anioCadena_local = anioCadena_local.substring(2,4);
    } catch (e){
    }
    return (mesLetra_local + " " + dia_local + "/" + anioCadena_local);  
}

/* Devuelve la hora en formato a.m./p.m.(1:30 p.m.)
   Parametro pFecha
   Modificó ZDOR fecha: 01/06/2007
   Modificó GEOT fecha: 
 */
function obtenerFormatoHoraH12(pHora){
    var formato = '';
    var arrayHora = '';
    var hora = 0;
    var minutos = 0;
    
    if (pHora == ''){
        return formato;
    }
    try{
        formato = "a.m.";
        arrayHora = pHora.split(":");
        hora = arrayHora[0];
        minutos = arrayHora[1];
        if (hora > 12) {
            hora -= 12;
            formato = "p.m.";
        }
    } catch (e){
    }
    return (hora + ":" + minutos + " " + formato);
}

/* De acuerdo al género devuelve Señor ó Señora
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroSENSPA(pGenero){
    var senspa_local = '';
    if (pGenero == ''){
        return senspa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return senspa_local;
    }
    try{
        senspa_local = "Señor";
        if (obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            senspa_local = "Señora";
        }
    } catch (e){
    }
    return (senspa_local);
}



/* De acuerdo al género devuelve Doctor ó Doctora
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroDOCSPA(pGenero){
    var docspa_local = '';
       
    if (pGenero == ''){
        return docspa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return docspa_local;
    }
    try{
        docspa_local = "Doctor";
        if(obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            docspa_local = "Doctora";
        }
    } catch (e){
    }
    return (docspa_local);
}

/* De acuerdo al género devuelve Abogado ó Abogada
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroABOSPA(pGenero){
    var abospa_local = '';
    if (pGenero == ''){
        return abospa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return abospa_local;
    }
    try{
        abospa_local = "Abogado";
        if(obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            abospa_local = "Abogada";
        }
    } catch (e){
    }
    return (abospa_local);
}

/* De acuerdo al género devuelve el ó la
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroELLSPA(pGenero){
    var ellspa_local = '';
    if (pGenero == ''){
        return ellspa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return ellspa_local;
    }
    try{
        ellspa_local = "el";
        if (obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            ellspa_local = "la";
        }
    } catch (e){
    }
    return (ellspa_local);
}

/* De acuerdo al género devuelve él ó ella
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroELASPA(pGenero){
    var elaspa_local = '';
    if (pGenero == ''){
        return elaspa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return elaspa_local;
    }
    try{
        elaspa_local = "él";
        if (obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            elaspa_local = "ella";
        }
    } catch (e){
    }
    return (elaspa_local);
}

/* De acuerdo al género devuelve el señor ó la señora
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroELLSENSPA(pGenero){
    var ellsenspa_local = '';
    if (pGenero == ''){
        return ellsenspa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return ellsenspa_local;
    }
    try{
        ellsenspa_local = "el señor";
        if(obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            ellsenspa_local = "la señora";
        }
    } catch (e){
    }
    return (ellsenspa_local);
}

/* De acuerdo al género devuelve el doctor ó la doctora
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroELLDOCSPA(pGenero){
    var elldocspa_local = '';
    if (pGenero == ''){
        return elldocspa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return elldocspa_local;
    }
    try{
        elldocspa_local = "el doctor";
        if (obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            elldocspa_local = "la doctora";
        }
    } catch (e){
    }
    return (elldocspa_local);
}

/* De acuerdo al género devuelve el abogado ó la abogada
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function obtenerFormatoGeneroELLABOSPA(pGenero){
    var ellabospa_local = '';
    if (pGenero == ''){
        return ellabospa_local;
    }
    if (obtenerFormatoEstiloMIN(pGenero) != 'masculino'  && obtenerFormatoEstiloMIN(pGenero) != 'femenino' ){         
        return ellabospa_local;
    }
    try{
        ellabospa_local = "el abogado";
        if (obtenerFormatoEstiloMIN(pGenero) == "femenino"){
            ellabospa_local = "la abogada";
        }
    } catch (e){
    }
    return ellabospa_local;
}

/* Da formato al dato de acuerdo al tipo
   Parametro pFecha
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function formatearValor(pDato, pFormato, pIdObjeto){
    var resultado_local = '';
    var elemento_local = '';
    if(pDato == ''){
        return resultado_local;
    }
    if(pFormato == ''){
        return resultado_local;
    }
    if(pIdObjeto == ''){
        return resultado_local;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            switch (pFormato){
                case "DMA" : resultado_local = obtenerFormatoFechaDMA(pDato); break; 
                case "DMA1SPA" : resultado_local = obtenerFormatoFechaDMA1SPA(pDato); break;
                case "DMA2SPA" : resultado_local = obtenerFormatoFechaDMA2SPA(pDato); break;
                case "DMA3SPA" : resultado_local = obtenerFormatoFechaDMA3SPA(pDato); break;
                case "MDA1SPA" : resultado_local = obtenerFormatoFechaMDA1SPA(pDato); break;
                case "MDA2SPA" : resultado_local = obtenerFormatoFechaMDA2SPA(pDato); break;
                case "MDA3SPA" : resultado_local = obtenerFormatoFechaMDA3SPA(pDato); break;

                case "H12" : resultado_local = obtenerFormatoHoraH12(pDato); break;

                case "SENSPA" : resultado_local = obtenerFormatoGeneroSENSPA(pDato); break;
                case "DOCSPA" : resultado_local = obtenerFormatoGeneroDOCSPA(pDato); break;
                case "ABOSPA" : resultado_local = obtenerFormatoGeneroABOSPA(pDato); break;
                case "ELLSPA" : resultado_local = obtenerFormatoGeneroELLSPA(pDato); break;
                case "ELASPA" : resultado_local = obtenerFormatoGeneroELASPA(pDato); break;
                case "ELLSENSPA" : resultado_local = obtenerFormatoGeneroELLSENSPA(pDato); break;
                case "ELLDOCSPA" : resultado_local = obtenerFormatoGeneroELLDOCSPA(pDato); break;
                case "ELLABOSPA" : resultado_local = obtenerFormatoGeneroELLABOSPA(pDato); break;

                case "MIN" : resultado_local = obtenerFormatoEstiloMIN(pDato); break;
                case "MAY" : resultado_local = obtenerFormatoEstiloMAY(pDato); break;
                case "CAP" : resultado_local = obtenerFormatoEstiloCAP(pDato); break;
                case "TIT" : resultado_local = obtenerFormatoEstiloTIT(pDato); break;
            }
            elemento_local.value = resultado_local;
        }
    } catch(e) {        
    }
}
