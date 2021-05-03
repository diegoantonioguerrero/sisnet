/* ---------------------------------------------------------------------- */
/*                               OPERACIONES                              */
/* ---------------------------------------------------------------------- */

/* Función que permite sumar dos operandos (int, float, double)
   Parametro pOperando1, pOperando2 
   Modificó ZDOR fecha:
   Modificó GEOT fecha:
 */
function sumar(pOperando1, pOperando2){
    var resultado_local = 0;
    
    if(this.verificarEstaVacio(pOperando1)){
        pOperando1 = 0;
    }
    if(this.verificarEstaVacio(pOperando2)){
        pOperando2 = 0;
    }
    try{
        resultado_local = parseFloat(pOperando1) + parseFloat(pOperando2);
        resultado_local.toPresicion(50);
    } catch (e){

    }
    return resultado_local;  
}

/**
 * Complementa un número adicionándole un número de decimales
 * parámetros pNumero, pNumeroDecimales
 * Modificó GEOT fecha: 10/02/2007
 */

function complementarNumero(pNumero, pNumeroDecimales){
    var posicionPunto_local = 0;
    var numeroDecimales_local = 0;   
    var numeroCadena_local = "";
    
    if (pNumeroDecimales <= 0){
        return pNumero;    
    }    
    
    try{        
        numeroCadena_local = String(pNumero);
        posicionPunto_local = numeroCadena_local.indexOf('.');
        if (posicionPunto_local == -1) {
            numeroCadena_local = numeroCadena_local + '.';
        }
        posicionPunto_local = numeroCadena_local.indexOf('.');
        numeroDecimales_local = (numeroCadena_local.substring(posicionPunto_local, numeroCadena_local.length)).length;
        if (numeroDecimales_local > 0){
            for (var i=numeroDecimales_local; i<=pNumeroDecimales; i++){
                numeroCadena_local = numeroCadena_local + '0';            
            }                
        }
    } catch (e){
    
    }
    
    return numeroCadena_local;
}

/**
 * Redondea un numero a las cifras requeridas
 * parametros pNumero, pCifrasDecimales
 * Modificó ZDOR fecha: 21/06/2007
 */
function redondearNumero(pNumero, pCifrasDecimales){
    var valorElevado_local = 0;
    var valorRedondear_local = 0;

    if(pNumero == null){
        return valorRedondear_local;
    }
    if(pCifrasDecimales == null){
        return valorRedondear_local;
    }

    try {
       valorElevado_local = Math.pow(10, pCifrasDecimales);
       valorRedondear_local = pNumero * valorElevado_local;
       valorRedondear_local = Math.round(valorRedondear_local);
       valorRedondear_local = valorRedondear_local / valorElevado_local;
       if(pCifrasDecimales == 0){
           valorRedondear_local = Math.round(valorRedondear_local);
       }
       valorRedondear_local = complementarNumero(valorRedondear_local, pCifrasDecimales);
    } catch (e) {
        
    }
    return valorRedondear_local;
}

/* Función que permite sumar dos operandos (int, float o double) y guardar el resultado en un objeto
   Parametro pOperando1, pOperando2, pIdObjeto 
   Modificó ZDOR fecha:
   Modificó GEOT fecha:  
 */
function sumarValores(pOperando1, pOperando2, pIdObjeto, pNumeroDecimales, pEsSoloPositivo){
    var resultado_local = 0;
    var elemento_local = null;

    if(pIdObjeto == null){
        return;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = sumar(pOperando1, pOperando2);
            resultado_local = redondearNumero(resultado_local, pNumeroDecimales);
            
            if (pEsSoloPositivo){
                if (resultado_local < 0){
                    resultado_local = 0;                                    
                }            
            }
            
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que permite restar dos operandos 
   Parametro pOperando1, pOperando2 
   Modificó ZDOR fecha:
   Modificó GEOT fecha:
 */
function restar(pOperando1, pOperando2){
    var resultado_local = 0;
    
    if(this.verificarEstaVacio(pOperando1)){
        pOperando1 = 0;
    }
    if(this.verificarEstaVacio(pOperando2)){
        pOperando2 = 0;
    }
    try{
        resultado_local = pOperando1 - pOperando2;
        resultado_local.toPresicion(50);        
    } catch (e){

    }
    return resultado_local;  
}

/* Función que permite restar dos operandos y guardar el resultado en un objeto 
   Parametro pOperando1, pOperando2, pIdObjeto
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function restarValores(pOperando1, pOperando2, pIdObjeto, pNumeroDecimales, pEsSoloPositivo){
    var resultado_local = 0;
    var elemento_local = null;
    
    if(pIdObjeto == null){
        return;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = restar(pOperando1, pOperando2);
            resultado_local = redondearNumero(resultado_local, pNumeroDecimales);
            
            if (pEsSoloPositivo){
                if (resultado_local < 0){
                    resultado_local = 0;                                    
                }            
            }
            
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que permite multiplicar dos operandos
   Parametro pOperando1, pOperando2 
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function multiplicar(pOperando1, pOperando2){
    var resultado_local = 0;
    if(this.verificarEstaVacio(pOperando1)){
        pOperando1 = 0;
    }
    if(this.verificarEstaVacio(pOperando2)){
        pOperando2 = 0;
    }
    try{
        resultado_local = pOperando1 * pOperando2;
        resultado_local.toPresicion(50);
    } catch (e){

    }
    return resultado_local;  
}

/* Función que permite multiplicar dos operandos y guardar el resultado en un objeto 
   Parametro pOperando1, pOperando2, pIdObjeto
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function multiplicarValores(pOperando1, pOperando2, pIdObjeto, pNumeroDecimales, pEsSoloPositivo){
    var resultado_local = 0;
    var elemento_local = null;
   
    if(pIdObjeto == null){
        return;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = multiplicar(pOperando1, pOperando2);
            resultado_local = redondearNumero(resultado_local, pNumeroDecimales);
            
            if (pEsSoloPositivo){
                if (resultado_local < 0){
                    resultado_local = 0;                                    
                }            
            }
            
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que permite dividir dos operandos
   Parametro pOperando1, pOperando2 
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function dividir(pOperando1, pOperando2){
    var resultado_local = 0;
    if(this.verificarEstaVacio(pOperando1)){
        pOperando1 = 0;
    }
    if(this.verificarEstaVacio(pOperando2)){
        pOperando2 = 0;
    }
    if(pOperando2 == 0 ){
        return resultado_local;
    }
    try{
        resultado_local = pOperando1 / pOperando2;
        resultado_local.toPresicion(50);
    } catch (e){

    }
    return resultado_local;  
}

/* Función que permite dividir dos operandos y guardar el resultado en un objeto  
   Parametro pOperando1, pOperando2, pIdObjeto
   Modificó ZDOR fecha:
   Modificó GEOT fecha: 
 */
function dividirValores(pOperando1, pOperando2, pIdObjeto, pNumeroDecimales, pEsSoloPositivo){
    var resultado_local = 0;
    var elemento_local = null;
    if(pIdObjeto == null){
        return;
    }
    if(pOperando2 == 0){
        elemento_local = document.getElementById(pIdObjeto);
        elemento_local.value = resultado_local;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = dividir(pOperando1, pOperando2);
            resultado_local = redondearNumero(resultado_local, pNumeroDecimales);
            
            if (pEsSoloPositivo){
                if (resultado_local < 0){
                    resultado_local = 0;                                    
                }            
            }
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que permite concatenar dos cadenas
   Parametro pCadena1, pCadena2 
   Modificó ZDOR fecha: 30/04/2008
   Modificó GEOT fecha: 17/12/2007
 */
function concatenar(pCadena1, pCadena2, pUnion, pLongitud){
    var resultado_local = "";
    
    if(pCadena1 == null){
        return resultado_local;
    }
    if(pCadena2 == null){
        return resultado_local;
    }
    
    try{
        if(pUnion != "" && (pCadena1 == "" || pCadena2 == "")){
            pUnion = "";
        }
        if(pUnion == "{SALTO}"){
            pUnion = "\r\n";
        }
        resultado_local = pCadena1 + pUnion + pCadena2;
        resultado_local = resultado_local.substring(0, pLongitud);
    } catch (e){

    } 
    
    return resultado_local;  
}

/* Función que permite concatenar dos cadenas
   Parametro pCadena1, pCadena2, pIdObjeto 
   Modificó ZDOR fecha: 21/01/2008
   Modificó GEOT fecha: 17/12/2007
 */
function concatenarValores(pCadena1, pCadena2, pIdObjeto, pUnion, pLongitud){
    var resultado_local = "";
    var elemento_local = null;

    if(pIdObjeto == null){
        return;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = concatenar(pCadena1, pCadena2, pUnion, pLongitud);
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que quita los ceros que hayan a la izquierda de un valor
   Parametro pValor 
   Modificó ZDOR fecha:
   Modificó GEOT fecha:  
*/
function quitarCerosIzquierda(pValor){
    var resultado_local = 0;
    if (pValor == null){
        return resultado_local;
    }
    try{
        resultado_local = pValor;
        while (resultado_local.charAt(0) == '0'){
            resultado_local = resultado_local.substring(1, resultado_local.length);
        }
    } catch (e){
        
    }
    return resultado_local;
}

/* Función que permite restar dos fechas
   Parametro pFechaInicio, pFechaFinal
   Modificó ZDOR fecha: 10/05/2007
   Modificó GEOT fecha: 27/05/2008 
*/
function restarFechas(pFechaInicio, pFechaFinal){
    var dias_local = 0;
    var arrayFecha1 = null;
    var arrayFecha2 = null;
    var anio1 = 0;
    var anio2 = 0;
    var mes1 = 0;
    var mes2 = 0;
    var dia1 = 0;
    var dia2 = 0;
    var fecha1 = null;
    var fecha2 = null;
    var diasFechas = 0;
    
    if(pFechaInicio == null){
        return dias_local;
    }
    if(pFechaFinal == null){
        return dias_local;
    }
    try{
        arrayFecha1 = pFechaInicio.split("-");
        arrayFecha2 = pFechaFinal.split("-");
        if (arrayFecha1.length != 3){
            return dias_local;
        }
        if (arrayFecha2.length != 3){
            return dias_local;
        }
        anio1 = parseInt(arrayFecha1[0]);
        mes1 = parseInt(quitarCerosIzquierda(arrayFecha1[1]));
        dia1 = parseInt(quitarCerosIzquierda(arrayFecha1[2]));
        fecha1 = new Date (anio1, mes1-1, dia1);
        
        anio2 = parseInt(arrayFecha2[0]);
        mes2 = parseInt(quitarCerosIzquierda(arrayFecha2[1]));
        dia2 = parseInt(quitarCerosIzquierda(arrayFecha2[2]));
        fecha2 = new Date (anio2, mes2-1, dia2);
        
        diasFechas = fecha1.getTime() - fecha2.getTime();
        dias_local = Math.floor(diasFechas / (1000*60*60*24));
    } catch (e){
        
    }
    return dias_local;
}

/* Función que llama la función de restar dos fechas y lo guarda en un objeto
   Parametro pFechaInicio, pFechaFinal, pIdObjeto
   Modificó ZDOR fecha:
   Modificó GEOT fecha:  
*/
function restarDosFechas(pFechaInicio, pFechaFinal, pIdObjeto){
    var resultado_local = 0;
    var elemento_local = null;
    if(pFechaInicio == null){
        return;
    }
    if(pFechaFinal == null){
        return;
    }
    if(pIdObjeto == null){
        return;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = restarFechas(pFechaInicio, pFechaFinal);
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que permite sumar días a una fecha
   Parametro pFecha, pDiasSumar
   Modificó ZDOR fecha: 10/07/2007
   Modificó GEOT fecha: 27/05/2008 
*/
function sumarDias(pFecha, pDiasSumar){
    var arrayFecha = null;
    var tiempo = null;
    var anio = "";
    var mes = "";
    var dia = "";
    var fecha = null;
    var milisegundos = 0;
    
    if (pFecha == null){
        return "";
    }
    if (pDiasSumar == null){
        return "";
    }
    try{
        arrayFecha = pFecha.split("-");
        anio = arrayFecha[0];
        mes = quitarCerosIzquierda(arrayFecha[1]);
        dia = quitarCerosIzquierda(arrayFecha[2]);
        fecha = new Date (anio,mes-1,dia);
        milisegundos = parseInt(pDiasSumar*24*60*60*1000);
        
        tiempo = fecha.getTime();
        fecha.setTime(parseInt(tiempo + milisegundos));
        
        dia = fecha.getDate();
        mes = fecha.getMonth() + 1;
        anio = fecha.getFullYear();
        dia = this.completarNumero(dia);
        mes = this.completarNumero(mes);
    } catch (e) {
        
    }
    return (anio + "-" + mes + "-" + dia);
} 

/* Función que llama la función que suma dos fechas y las guarda en un objeto
   Parametro pFecha, pDiasSumar, pIdObjeto
   Modificó ZDOR fecha: 11/05/2007
   Modificó GEOT fecha:  
*/
function sumarDiasFecha(pFecha, pDiasSumar, pIdObjeto){
    var resultado_local = 0;
    var elemento_local = null;

    if(pFecha == null){
        return;
    }
    if (pFecha == ""){
        return;
    }
    if(pDiasSumar == null){
        return;
    }
    if(pIdObjeto == null){
        return;
    }
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = sumarDias(pFecha, pDiasSumar);
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función que resta dias a un fecha
   Parametro pFecha, pDiasRestar
   Modificó ZDOR fecha: 10/05/2007
   Modificó GEOT fecha:  
*/
function restarDias(pFecha, pDiasRestar){
    var arrayFecha = null;
    var anio = "";
    var mes = "";
    var dia = "";
    var fecha = null;
    
    if (pFecha == null){
        return "";
    }
    if (pDiasRestar == null){
        return "";
    }
    try{
        arrayFecha = pFecha.split("-");
        anio = arrayFecha[0];
        mes = quitarCerosIzquierda(arrayFecha[1]);
        dia = quitarCerosIzquierda(arrayFecha[2]);
        fecha = new Date (anio,mes-1,dia);
        dia = fecha.getDate();
        mes = fecha.getMonth() + 1;
        anio = fecha.getFullYear();
        dia = this.completarNumero(dia);
        mes = this.completarNumero(mes);
    } catch (e) {
        
    }
    return (anio + "-" + mes + "-" + dia);
}

/* Función que llama la función que suma dias a un fecha y las guarda en un objeto
   Parametro pFechaInicio, pFechaFinal, pIdObjeto
   Modificó ZDOR fecha: 11/05/2007
   Modificó GEOT fecha:  
*/
function restarDiasFecha(pFecha, pDiasRestar, pIdObjeto){
    var resultado_local = 0;
    var elemento_local = null;
    
    if(pFecha == null){
        return;
    }
    if (pFecha == ''){
        return;
    }
    if(pDiasRestar == null){
        return;
    }
    if(pIdObjeto == null){
        return;
    }
    
    try{
        elemento_local = document.getElementById(pIdObjeto);
        if(elemento_local != null){
            resultado_local = restarDias(pFecha, pDiasRestar);
            if (!elemento_local.disabled){
                elemento_local.value = resultado_local;
            }
        }
    } catch (e){

    }
}

/* Función para el cálculo del valor absoluto de un número 
   Parametro pNumero
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 23/10/2007  
 */
function calcularValorAbsoluto(pNumero){    
    var valorAbsoluto = 0;
    
    try{
        valorAbsoluto = this.Math.abs(pNumero);
    } catch (e){
    
    }
    
    return valorAbsoluto;
}

/* Función para el cálculo del valor absoluto de un número y colocarlo en un control
   Parametro pIdControlOrigen, pIdControlDestino
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 23/10/2007  
 */
function obtenerValorAbsoluto(pIdControlOrigen, pIdControlDestino){
    var valor = 0;
    var valorAbsoluto = 0;
    var elementoOrigen_local = null;
    var elementoDestino_local = null;
    
    try{
        elementoOrigen_local = document.getElementById(pIdControlOrigen);
        if (elementoOrigen_local != null){
            valor = elementoOrigen_local.value;
            valorAbsoluto = this.calcularValorAbsoluto(valor);
            elementoDestino_local = document.getElementById(pIdControlDestino);
            if (elementoDestino_local != null){
                if (!elementoDestino_local.disabled){
                    elementoDestino_local.value = valorAbsoluto;
                }
            }
        }
    } catch (e){
    
    }     
}

/* Función para sumar una unidad a un número
   Parametro pNumero
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 23/10/2007  
 */
function sumaUnidad(pNumero){    
    var numero = 0;
    
    try{
        numero = pNumero;        
        numero++;
    } catch (e){
    
    }
    
    return numero;
}

/* Función para sumar una unidad a un número y colocarlo en un control
   Parametro pIdControlOrigen, pIdControlDestino
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 23/10/2007  
 */
function sumarUnidad(pIdControlOrigen, pIdControlDestino){
    var numero_local = 0;
    var elementoOrigen_local = null;
    var elementoDestino_local = null;
    
    try{
        elementoOrigen_local = document.getElementById(pIdControlOrigen);
        if (elementoOrigen_local != null){        
            numero_local = elementoOrigen_local.value; 
            numero_local = this.sumaUnidad(numero_local);
            elementoDestino_local = document.getElementById(pIdControlDestino);
            if (elementoDestino_local != null){
                elementoDestino_local.value = numero_local;
            }
        }
    } catch (e){
    
    }     
}

/* Función para evaluar si un valor es menor que cero
   Parametro pValor
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 29/10/2007  
 */
function evaluarMenorQueCero(pValor){
    return pValor < 0;
}

/* Función para evaluar si un valor es menor o igual que cero
   Parametro pValor
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 29/10/2007  
 */
function evaluarMenorIgualQueCero(pValor){
    return pValor <= 0;
}

/* Función para evaluar si un valor es igual a cero
   Parametro pValor
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 29/10/2007  
 */
function evaluarIgualQueCero(pValor){
    return pValor == 0;
}

/* Función para evaluar una condicion y retornar un 1 o un 2
   Parametro pValor, pOperando, pIdControlDestino
   Modificó ZDOR fecha: 
   Modificó GEOT fecha: 29/10/2007  
 */
function evaluarCondicion(pValor, pOperando, pIdControlDestino){
    var valor_local = false;
    var valorNumerico_local = 2;
    var elementoDestino_local = null;
    
    if(pValor == null || pValor == "" ){
        return;
    }
    
    try{    
        switch (pOperando){
            case 15: valor_local = this.evaluarMenorQueCero(pValor); break;
            case 16: valor_local = this.evaluarMenorIgualQueCero(pValor); break;
            case 17: valor_local = !this.evaluarMenorIgualQueCero(pValor); break;
            case 18: valor_local = !this.evaluarMenorQueCero(pValor); break;
            case 19: valor_local = this.evaluarIgualQueCero(pValor); break;
            case 20: valor_local = !this.evaluarIgualQueCero(pValor); break;
        }    
        
        if (valor_local){
            valorNumerico_local = 1;        
        }
        
        elementoDestino_local = document.getElementById(pIdControlDestino);
        if (elementoDestino_local != null){
            if (!elementoDestino_local.disabled){
                elementoDestino_local.value = valorNumerico_local;          
            }
        }
    } catch (e){
        
    }
}