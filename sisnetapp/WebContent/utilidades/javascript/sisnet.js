
/* ---------------------------------------------------------------------- */
/*                                VARIABLES                               */
/* ---------------------------------------------------------------------- */

/* Esta variable indica si est� bien dejar las casillas */
/* en blanco como regla general                         */
var defectoVacioOK = false;

/* listas de caracteres */
var punto = ".";
var letrasMinusculas = "abcdefghijklmnopqrstuvwxyz������� ";
var letrasMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ������ ";
var espacioEnBlanco = " \t\n\r";
var caracteresEspecialesPermitidos= "!@#$%&/()=?�{}*+-._";
var caracteresEspecialesNoPermitidos= "<>'";

/* caracteres admitidos en nos de telefono */
var caracteresTelefono = "()-+/ ";

/* ---------------------------------------------------------------------- */
/*                     TEXTOS PARA LOS MENSAJES                           */
/* ---------------------------------------------------------------------- */

/* m abrevia "missing" (faltante) */
var mensajeGeneral = "Error: no puede dejar este espacio vacio";

/* p abrevia "prompt" */
var tituloMensaje = "Error: ";
var alfaNumerico = "ingrese un texto que contenga solo letras y/o numeros";
var alfabetico   = "ingrese un texto que contenga solo letras";
var entero = "ingrese un numero entero";
var numero = "ingrese un numero";
var numeroTelefonico = "ingrese un n�mero de tel�fono";
var email = "ingrese una direcci�n de correo electr�nico v�lida";
var nombre = "ingrese un texto que contenga solo letras, numeros o espacios";
var parrafo = "ingrese un texto que contenga solo letras, numeros y/o espacios";

/* ---------------------------------------------------------------------- */
/*                FUNCIONES PARA MANEJO DE ARREGLOS                       */
/* ---------------------------------------------------------------------- */

/* JavaScript 1.0 (Netscape 2.0) no tenia un constructor para arreglos,   */
/* asi que ellos tenian que ser hechos a mano. Desde JavaScript 1.1       */
/* (Netscape 3.0) en adelante, las funciones de manejo de arreglos no     */
/* son necesarias.                                                        */


/* ---------------------------------------------------------------------- */
/*                  CODIGO PARA FUNCIONES BASICAS                         */
/* ---------------------------------------------------------------------- */


/* s es vacio */
function verificarEstaVacio(s)
{
    return ((s == null) || (s.length == 0));
}

/* s es vacio o solo caracteres de espacio */
function verificarEspacioEnBlanco (s)
{var i;
    if (verificarEstaVacio(s)) return true;
    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        // si el caracter en que estoy no aparece en espacioEnBlanco,
        // entonces retornar falso
        if (espacioEnBlanco.indexOf(c) == -1) return false;
    }
    return true;
}

/* Quita todos los caracteres que que estan en "bag" del string "s" s. */
function quitarCaracteresEnCadena (s, bag)
{var i;
    var returnString = "";

    /* Buscar por el string, si el caracter no esta en "bag",
    // agregarlo a returnString                              */

    for (i = 0; i < s.length; i++)
    {var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

/* Lo contrario, quitar todos los caracteres que no estan en "bag" de "s" */
function quitarCaracteresNoEnCadena (s, bag)
{var i;
    var returnString = "";
    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        if (bag.indexOf(c) != -1) returnString += c;
    }

    return returnString;
}

/* Quitar todos los espacios en blanco de un string */
function quitarEspaciosEnBlanco (s)
{  
    return quitarCaracteresEnCadena (s, espacioEnBlanco);
}

/* La rutina siguiente es para cubrir un bug en Netscape
/* 2.0.2 - seria mejor usar indexOf, pero si se hace
/* asi stripInitialespacioEnBlanco() no funcionaria */

function verificarCaracterEnCadena (c, s)
{
    for (var i = 0; i < s.length; i++){   
        if (s.charAt(i) == c) return true;
    }
    return false;
}

/* Quita todos los espacios que antecedan al string */
function quitarEspacioEnBlancoInicial (s)
{var i = 0;
    while ((i < s.length) && verificarCaracterEnCadena (s.charAt(i), espacioEnBlanco))
       i++;
    return s.substring (i, s.length);
}

/* c es una letra del alfabeto espanol */
function verificarEsLetra (c)
{
    return( ( letrasMayusculas.indexOf( c ) != -1 ) ||
            ( letrasMinusculas.indexOf( c ) != -1 ) );
}

/* c es una letra del alfabeto espanol */
function verificarEsLetraMayuscula (c)
{
    return( letrasMayusculas.indexOf( c ) != -1 );
}

/* c es una letra del alfabeto espanol */
function verificarEsLetraMinuscula (c)
{
    return( letrasMinusculas.indexOf( c ) != -1 );
}

/* c es una letra del alfabeto espanol */
function verificarEsPunto (c)
{
    return ( punto.indexOf( c ) != -1 );
}

/* c es un digito */
function verificarEsDigito (c)
{   
    return ((c >= "0") && (c <= "9"));
}

/* c es letra o digito */
function verificarEsLetraDigito (c)
{   
    return (verificarEsLetra(c) || verificarEsDigito(c));
}

/* c es un caracter */
function verificarEsCaracterEspecial (c)
{
    return ( caracteresEspecialesNoPermitidos.indexOf( c ) == -1 );
}

/* c es un caracter especial permitido*/
function verificarEsCaracterEspecialPermitido (c)
{
    return ( caracteresEspecialesPermitidos.indexOf( c ) != -1 );
}

/* ---------------------------------------------------------------------- //
/*                          NUMEROS                                       //
/* ---------------------------------------------------------------------- */

/* s es un numero entero (con o sin signo) */
function verificarEsEntero (s)
{var i;
    if (verificarEstaVacio(s))
       if (verificarEsEntero.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsEntero.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        if( i != 0 ) {
            if (!verificarEsDigito(c)) return false;
        } else {
            if (!verificarEsDigito(c) && ((c != "-") && (c != "+"))) return false;
        }
    }
    return true;
}

/* s es un numero (entero o flotante, con o sin signo) */
function verificarEsNumero (s)
{var i;
    var dotAppeared;
    dotAppeared = false;
    if (verificarEstaVacio(s))
       if (verificarEsNumero.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsNumero.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        if( i != 0 ) {
            if ( c == "." ) {
                if( !dotAppeared )
                    dotAppeared = true;
                else
                    return false;
            } else
                if (!verificarEsDigito(c)) return false;
        } else {
            if ( c == "." ) {
                if( !dotAppeared )
                    dotAppeared = true;
                else
                    return false;
            } else
                if (!verificarEsDigito(c) && ((c != "-") && (c != "+"))) return false;
        }
    }
    return true;
}

/* ---------------------------------------------------------------------- //
//                        STRINGS SIMPLES                                 //
// ---------------------------------------------------------------------- */

/* s tiene solo letras */
function verificarEsAlfabetico (s)
{var i;

    if (verificarEstaVacio(s))
       if (verificarEsAlfabetico.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsAlfabetico.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {
        // Check that current character is letter.
        var c = s.charAt(i);

        if (!verificarEsLetra(c))
        return false;
    }
    return true;
}

/* s tiene solo letras o puntos */
function verificarEsAlfabeticoConPuntos (s)
{var i;

    if (verificarEstaVacio(s))
       if (verificarEsAlfabetico.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsAlfabetico.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {
        // Check that current character is letter.
        var c = s.charAt(i);

        if ((!verificarEsLetra(c))&&(!verificarEsPunto(c)))
        return false;
    }
    return true;
}

/* s tiene solo letras y numeros */
function verificarEsAlfanumerico (s)
{var i;

    if (verificarEstaVacio(s))
       if (verificarEsAlfanumerico.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsAlfanumerico.arguments[1] == true);

    for (i = 0; i < s.length; i++)
    {
        var c = s.charAt(i);
        if (! (verificarEsLetra(c) || verificarEsDigito(c) || verificarEsCaracterEspecial(c))  )
        return false;
    }

    return true;
}

/* s tiene solo letras, numeros o espacios en blanco */
function verificarEsNombre (s)
{
    if (verificarEstaVacio(s))
       if (verificarEsNombre.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsAlfanumerico.arguments[1] == true);

    return( verificarEsAlfanumerico( quitarCaracteresEnCadena( s, espacioEnBlanco ) ) );
}

/* Modific� ZDOR fecha: 11/02/2008 */
/* Modific� GEOT fecha: */

/* FUNCION QUE VERIFICA SI UN CARACTER ES ENTER */
function verificarEsEnter(c){
    return (espacioEnBlanco.indexOf(c) != -1);
}

/* Modific� ZDOR fecha: 11/02/2008 */
/* Modific� GEOT fecha: */

/* FUNCION QUE VERIFICA EL CONTENIDO DE UN PARRAFO */
function verificarEsParrafo (s)
{var i;
    if (verificarEstaVacio(s)){
        if (verificarEsAlfanumerico.arguments.length == 1){
            return defectoVacioOK;
        } else { 
            return (verificarEsAlfanumerico.arguments[1] == true);
        }
    }
    for (i = 0; i < s.length; i++){
        var c = s.charAt(i);
        if (!(verificarEsLetra(c) || verificarEsDigito(c) || verificarEsCaracterEspecial(c) || verificarEsEnter(c))){
            return false;
        }
    }
    return true;
}

/* ---------------------------------------------------------------------- //
//                           FONO o EMAIL                                 //
// ---------------------------------------------------------------------- */

/* s es numero de telefono valido */
function verificarEsNumeroTelefonico (s)
{   
    var modString;
    if (verificarEstaVacio(s))
       if (verificarEsNumeroTelefonico.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsNumeroTelefonico.arguments[1] == true);
    modString = quitarCaracteresEnCadena( s, caracteresTelefono );
    return (verificarEsEntero(modString));
}

/* s es una direccion de correo valida */
function verificarEsEmail (s)
{
    if (verificarEstaVacio(s))
       if (verificarEsEmail.arguments.length == 1) return defectoVacioOK;
       else return (verificarEsEmail.arguments[1] == true);
    if (verificarEspacioEnBlanco(s)) return false;
    var i = 1;
    var sLength = s.length;
    while ((i < sLength) && (s.charAt(i) != "@"))
    { 
        i++;
    }

    if ((i >= sLength) || (s.charAt(i) != "@")) return false;
    else i += 2;

    while ((i < sLength) && (s.charAt(i) != "."))
    { 
        i++;
    }

    if ((i >= sLength - 1) || (s.charAt(i) != ".")) return false;
    else return true;
}

/* ---------------------------------------------------------------------- //
//                  FUNCIONES PARA RECLAMARLE AL USUARIO                  //
// ---------------------------------------------------------------------- */

/* pone el string s en la barra de estado */
function mostrarMensajeBarraEstado (s)
{   
    window.status = s;
}

/* notificar que el campo pCampo esta vacio    */
function notificarCampoVacio (pCampo)
{   
    pCampo.focus();
    alert(mensajeGeneral);
    mostrarMensajeBarraEstado(mensajeGeneral);
    return false;
}

/* notificar que el campo pCampo es invalido */
function notificarCampoInvalido (pCampo, s)
{   
    pCampo.focus();
    pCampo.select();
    alert(s);
    mostrarMensajeBarraEstado(tituloMensaje + s);
    return false;
}

/* Modific� ZDOR fecha: 11/02/2008 */
/* Modific� GEOT fecha: */

/* FUNCION QUE VERIFICA EL CONTENIDO DE UN CAMPO */
function verificarCampo (pCampo, pFuncion, emptyOK, s)
{
    var msg;
    if (verificarCampo.arguments.length < 3) emptyOK = defectoVacioOK;
    if (verificarCampo.arguments.length == 4) {
        msg = s;
    } else {
        if( pFuncion == verificarEsAlfabetico ) msg = alfabetico;
        if( pFuncion == verificarEsAlfabeticoConPuntos ) msg = alfabetico;
        if( pFuncion == verificarEsAlfanumerico ) msg = alfaNumerico;
        if( pFuncion == verificarEsEntero ) msg = entero;
        if( pFuncion == verificarEsNumero ) msg = numero;
        if( pFuncion == verificarEsEmail ) msg = email;
        if( pFuncion == verificarEsNumeroTelefonico ) msg = numeroTelefonico;
        if( pFuncion == verificarEsNombre ) msg = nombre;
        if( pFuncion == verificarEsParrafo ) msg = parrafo;
    }

    if ((emptyOK == true) && (verificarEstaVacio(pCampo.value))) return true;

    if ((emptyOK == false) && ((verificarEstaVacio(pCampo.value)) || verificarEspacioEnBlanco(pCampo.value)))
        return notificarCampoVacio(pCampo);

    if (pFuncion(pCampo.value) == true)
        return true;
    else
        return notificarCampoInvalido(pCampo,msg);

}

/*Obtener digito de verificaci�n del nit*/
function obtenerDigitoVerificacion(nit){
  var tmp, i, residuo, chequeo, digitoVerificacion;
  var numerosPrimos = new Array(3,7,13,17,19,23,29,37,41,43,47,53,59,67,71);
  chequeo = 0;
  residuo = 0;
  nit = nit.trim;
  for(i = 0; i<(nit.length-1);i++){
    tmp = nit.substr((nit.length - i), 1);
    chequeo = chequeo + tmp * numerosPrimos(i + 1);
  }
  residuo = chequeo % 11;
  if(residuo <= 1){
    digitoVerificacion = residuo;
  }
  else{
    digitoVerificacion = 11 - residuo;
  }
  return digitoVerificacion;
}

/*Validar NIT*/
function validarNIT(nit) {
  nit = nit.trim;
  var digitoVerificacion = nit.substr(nit.length -1,1);
  if (obtenerDigitoVerificacion(nit) == digitoVerificacion){
    alert('Valido');
    return true;
  }
  else{
    alert('No V�lido');
    return false;
  }
}

/*establecer el foco en un elemento*/
function establecerFoco(Control){
  Control.focus();
  return false;
}

/* Valida que la entrada digitada sea un numero*/
function esNumeroEnteroDigitacion(e){
  var charCode;

  if (this.navigator.appName == "Netscape")
     charCode = e.which;
  else
     charCode = e.keyCode;  
   
  if (charCode > 31 && charCode != 45 && (charCode < 48 || charCode > 57)){
     return false;
  }
  return true;
}

/* Valida que la entrada digitada sea un numero entero positivo*/
function esNumeroEnteroSoloPositivoDigitacion(e){
  var charCode;

  if (this.navigator.appName == "Netscape")
     charCode = e.which;
  else
     charCode = e.keyCode;  
   
  if (charCode > 31 && (charCode < 48 || charCode > 57)){
     return false;
  }
  return true;
}

/* Valida que la entrada digitada sea un numero real*/
function esNumeroRealDigitacion(e){
  var charCode;

  if (this.navigator.appName == "Netscape")
     charCode = e.which;
  else
     charCode = e.keyCode;
    
  if ((charCode > 31 && charCode != 45 && charCode != 46) && (charCode < 48 || charCode > 57)){
     return false;
  }
  return true;
}

/* Valida que la entrada digitada sea un numero real positivo*/
function esNumeroRealSoloPositivoDigitacion(e){
  var charCode;

  if (this.navigator.appName == "Netscape")
     charCode = e.which;
  else
     charCode = e.keyCode;
    
  if ((charCode > 31 && charCode != 46) && (charCode < 48 || charCode > 57)){
     return false;
  }
  return true;
}

/* ---------------------------------------------------------------------- */
/*               FUNCIONES DE MENSAJES AL USUARIO                         */
/* ---------------------------------------------------------------------- */

/* Cierra una ventana*/
function cerrarVentana(){
    if (this.confirm("Desea salir de la aplicaci�n?")){
        window.close();  
        return true;
    }
    return false;
}

/* Salir de la aplicacion desde la consulta principal*/
function salirAplicacion() {
  if (this.confirm('Desea salir de la aplicaci�n?')){
     location.href="login.jsp?accion=96&numeroerror=4&tipoerror=3";
  }  
 }


/*Compara y confirma la contrase�a*/
function verificarContrasena(pContrasena, pVerificarContrasena){
   if (pContrasena == pVerificarContrasena){
      return true;
   }
   else{
      alert("La verificaci�n de su contrase�a no coincide con la digitada");
      return false;
   }
}

/*Pregunta antes de eliminar un registro*/
function preguntarEliminar(){
    var respuesta_local = null;

    try {
        respuesta_local = (this.confirm('Est� seguro de eliminar el registro?') &&
            this.confirm('Puede ocasionar una perdida de datos, Desea Continuar?'));
        if (respuesta_local){
            this.mostrarMensajeProcesandoInformacion();
        }
    } catch (exception) {

    }    
    return respuesta_local;
}

/*Mensaje con la restricci�n para usuarios que no son administradores*/
function mensajeUsuarioAdministrador(){
    alert("Opci�n v�lida s�lo para el administrador del sistema");
}

/*Mensaje con la restricci�n para usuarios que no tiene permisos para una aplicaci�n*/
function mensajeAplicacionNoPermitida(){
    alert("No esta autorizado para ver la aplicaci�n");
}

/*Mensaje con la restriccion para campos de tipo lista dependiente*/
function restriccionVerDependencias(){
    alert("Opci�n valida s�lo para campos tipo lista dependiente");
}

/*Mensaje de autorizaci�n para incluir informaci�n*/
function mensajeAutorizacionInclusion(){
    alert("Usuario no autorizado para incluir �sta informaci�n");
}

/*Mensaje de autorizaci�n para modificar informaci�n*/
function mensajeAutorizacionModificacion(){
    alert("Usuario no autorizado para modificar �sta informaci�n");
    return false;
}

/*Mensaje de autorizaci�n para borrar informaci�n*/
function mensajeAutorizacionBorrar(){
    alert("Usuario no autorizado para borrar �sta informaci�n");
}

/*Mensaje de autorizaci�n para realizar una importaci�n*/
function mensajeAutorizacionImportacion(){
    alert("Usuario no autorizado para realizar importaciones");
}

/*Mensaje de opci�n en construcci�n*/
function mensajeOpcionEnConstruccion(){
    alert("Esta opci�n est� en construcci�n");
}

/* ---------------------------------------------------------------------- */
/*                 FUNCIONES DE VALIDACION DE CAMPOS                      */
/* ---------------------------------------------------------------------- */

/*Cambia un control de visible a no visible o visceversa*/
function cambiarVisibilidad(Control, Seleccion) {
    if(Seleccion.checked == true){
        Control.style.display='inline'; 
    } else {
        Control.style.display='none'; 
    }
}

/*Activa las listas desplegables segun el enlace seleccionado*/
function activarListasDesplegablesDependientesEnlazado(Control) {
    if((Control.value == 1)||(Control.value == 2)){
        document.formularioIncluir.FLDCAMPOENLACEDEPENDE.style.display='inline';
        document.formularioIncluir.FLDCAMPOORIGENENLACE.style.display='inline';
    } else {
        document.formularioIncluir.FLDCAMPOENLACEDEPENDE.style.display='none';
        document.formularioIncluir.FLDCAMPOORIGENENLACE.style.display='none';
    }
}

/* Activa la opcion desconocido para las aplicaciones enlazadas*/
function activarCamposParaEnlazado() {
    if(document.formularioIncluir.FLDENLAZADO.value != 0){
        document.formularioIncluir.FLDOPCIONDESCONOCIDO.style.display='inline';
        document.formularioIncluir.FLDFILTRADOREGISTROSENLAZADOS.style.display='inline';
        document.formularioIncluir.FLDVALORFILTRADO.style.display='inline';
        llenarCampoDestinoFiltrado();
    } else {
        document.formularioIncluir.FLDOPCIONDESCONOCIDO.style.display='none';
        document.formularioIncluir.FLDFILTRADOREGISTROSENLAZADOS.style.display='none';
        document.formularioIncluir.FLDVALORFILTRADO.style.display='none';
        document.formularioIncluir.FLDCAMPOORIGENFILTRADO.style.display='none';
        document.formularioIncluir.FLDCAMPODESTINOFILTRADO.style.display='none';
    }
}

/* Activa las opciones de campos para el filtrado de registros enlazados*/
function activarCamposParaFiltradoRegistrosEnlazados() {
    var seleccion_local = parseInt(document.formularioIncluir.FLDFILTRADOREGISTROSENLAZADOS.value);
    switch(seleccion_local){
        case 2: 
        case 3: 
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
        case 9: 
        case 10: 
        case 11: 
        case 12: 
        case 13: 
        case 14:
            document.formularioIncluir.FLDCAMPOORIGENFILTRADO.style.display='inline';
            document.formularioIncluir.FLDCAMPODESTINOFILTRADO.style.display='inline';
            break;
        default:
            document.formularioIncluir.FLDCAMPOORIGENFILTRADO.style.display='none';
            document.formularioIncluir.FLDCAMPODESTINOFILTRADO.style.display='none';
            break;
        
    }
}

/* Activa borrar valor no habilitado para los campos habilitados por*/
function activarCamposParaHabilitadoPor() {
    if(document.formularioIncluir.FLDHABILITADOPOR.value != 0){
        document.formularioIncluir.FLDBORRARVALORNOHABILITADO.style.display='inline';
    } else {
        document.formularioIncluir.FLDBORRARVALORNOHABILITADO.style.display='none';
    }
}

/*Activa la lista desplegable de plantilla si es un documento que tiene plantilla*/
function activarListaDesplegablePlantillas(Control) {
    if(Control.value == 'DD'){
        document.formularioIncluir.FLDTIENEPLANTILLA.style.display='inline';
    } else {
        document.formularioIncluir.FLDTIENEPLANTILLA.style.display='none';
        document.formularioIncluir.FLDTIENEPLANTILLA.checked = false;
    }
}

/*Valida las posibles combinaciones de un campo*/
function validarCombinacionCampo(){
    if((document.formularioIncluir.FLDHABILITADOPOR.value != 0) && (document.formularioIncluir.FLDLISTADEPENDIENTE.value != 0)){
        alert("No puede combinar un campo habilitado por otro con lista dependiente");
        return false;
    }
    return true;
}

/*Limpia una lista desplegable y le da un valor con -1*/
function limpiarLista(objeto){
   var variable = new this.Option("Escoja un valor","0");
   while (objeto.length > 0){
         objeto.options[0] = null;
   }
   objeto.options[0] = variable;
}

/*Obtiene el nombre de l grupo de informacion de un campo*/
function obtenerNombreGrupoInformacionCampo(pCampo){
    var posicionInicial_local = pCampo.indexOf('(');
    var posicionFinal_local = pCampo.indexOf(')');
    var nombreGrupoInformacion_local = pCampo.substr(posicionInicial_local, posicionFinal_local);
    return nombreGrupoInformacion_local;
}

/*Permite enviar un formulario*/
function enviarFormulario(){
    try {
        document.formularioIncluir.recargarPagina.value = true;
        document.formularioIncluir.submit();
        this.mostrarMensajeProcesandoInformacion();
    } catch (exception) {

    }
}

/*Verifica que un select contenga una opcion con valor cero*/
function verificarValorNulo(objeto){
  for (var i=0; i<objeto.length; i++){
      if (objeto.options[i].value == "0"){
         return true;
      }
  }
  return false;
}

/* ---------------------------------------------------------------------- */
/*               FUNCIONES PARA MANEJO DE PANTALLA COMPLETA               */
/* ---------------------------------------------------------------------- */

function pantallaCompleta(){
    var vent = null;
    vent = window.open('','vVent','fullscreen=yes');
    /*llenaVentana(vent);*/
    /* Para direccionar a una p�gina espec�fica, elimine la linea anterior y utilice la siguiente:*/
    vent.location = "../index.jsp";
    this.write("<input type='button' value='Cerrar' onclick='javascript:window.close()'>");
}

/* ---------------------------------------------------------------------- */
/*               FUNCIONES PARA MANEJO DE CONTROL PARA HORA               */
/* ---------------------------------------------------------------------- */

/* Valida que la entrada digitada para hora sea n�mero entero o dos puntos (:)*/
function esValorHora(e){
    var charCode;
    if (this.navigator.appName == "Netscape")
        charCode = e.which;
    else
        charCode = e.keyCode;    
    if (charCode > 31 && (charCode < 48 || charCode > 58)){
        return false;
    }
    return true;
}

/* Cuenta los caracteres que hay dentro de una cadena*/
function contarCaracter(pCadena, pCaracter){
    var contador = 0;
    for(var i=0; i < 3; i++){
        if(pCadena.charAt(i) == pCaracter){
            contador++;
        }
    }
    return contador;
}

/* Completa una cadena con determinada cantidad de caracteres*/
function completarCadena(pCadena, pCaracter, pCantidad){
    var cadena_local = pCadena;
    for(var i=0; i < pCantidad; i++){
        cadena_local = cadena_local + pCaracter;
    }
    return cadena_local;
}

/* Completa un numero con 1 o 2 ceros segun sea necesario*/
function completarNumero(pNumero){   
    var cadena_local = String(pNumero);
    var longitudCadena = cadena_local.length;
    if(longitudCadena == 0){
        cadena_local = "00";
    } else{
        if(longitudCadena == 1){
            cadena_local = "0" + cadena_local;
        } else{
            cadena_local = cadena_local.substr(0,2); 
        }
    }
    return cadena_local;
}

/*Completa la hora ajustandola al formato HH:MM:SS */           
function completarHora(pHora) {
    var completarHora_local = ""; 
    var hora_local = pHora;
    if(hora_local != ''){
        var numeroCaracteres = contarCaracter(hora_local, ":");
        var caracterSeparadorHora = 2 - numeroCaracteres;
        var cadena_local = completarCadena(hora_local, ":", caracterSeparadorHora);
        var validaHora_local = cadena_local.split(":");
        var horas = completarNumero(validaHora_local[0]);
        var minutos = completarNumero(validaHora_local[1]);
        completarHora_local = horas + ":" + minutos;
    }
    return completarHora_local;
}  

/* Valida que la hora sea correcta*/
function validarHora(pHora){
    var validaHora = pHora.value;
    if(validaHora != ''){
        var hora_local = completarHora(validaHora);
        var patronTiempo = /^(0[0-9]|1\d|2[0-3]):([0-5]\d)$/; 
        var arrayHora = hora_local.match(patronTiempo); 
        if (hora_local == "") {
            return true;
        }
        if (arrayHora == null) {
            alert("Formato de hora no v�lido");
            hora_local = "";
            return false;
        } 
    }  
    return true;
}

/* Permite establecer el foco en el control si la hora no es valida de lo contrario envia el formulario*/
function validacionHoras(pHora){
    if(pHora != null){
        var elemento = document.getElementById(pHora.id);
        if(!validarHora(pHora)){
            if(elemento != null){
                elemento.focus();
            }
            return false;
        } else {
            pHora.value = completarHora(pHora.value);
        }
    }
    return true;
}

/* ---------------------------------------------------------------------- */
/*               FUNCIONES PARA MANEJO DE CAMPOS CALCULADOS               */
/* ---------------------------------------------------------------------- */

/* Copia el valor de un campo en otro */
/* @ param pCampoDestino              */
/* @ param pCampoOrigen               */
/* Modific� ZDOR fecha: 26/04/2007 */
/* Modific� GEOT fecha: 11/09/2008 */

function copiarValorCampo(pCampoDestino, pValorCampoOrigen, pEsNumerico, pNumeroDecimales, pLongitud){
    var respuesta_local = null;

    if(pCampoDestino == null){
        return;
    }
    if(pValorCampoOrigen == null){
        return;
    }
    
    try {
        respuesta_local = new String("");

        var campoDestino_local = document.getElementById(pCampoDestino.id);
        if(campoDestino_local  == null){
            return;
        }
        respuesta_local = pValorCampoOrigen;        
        if(pEsNumerico){
            respuesta_local = this.redondearNumero(respuesta_local, pNumeroDecimales);
        } 
                
        if (pLongitud > 0 && respuesta_local.length > pLongitud){
            respuesta_local = respuesta_local.substring(0, pLongitud);
        }        

        if (campoDestino_local.type == "text"){
            if (!campoDestino_local.disabled){
                if (!pEsNumerico){
                    campoDestino_local.value = respuesta_local.toUpperCase();
                } else {
                    campoDestino_local.value = respuesta_local;                    
                }
            }
        } else {
            campoDestino_local.value = respuesta_local;            
        }        
    } catch(e){
    }
}

/* Copia el valor del primer campo que tenga valor en otro */
/* @ param pCampoDestino              */
/* @ param pCampoOrigenUno            */
/* @ param pCampoOrigenDos            */
/* Modific� ZDOR fecha: 22/01/2008 */
/* Modific� GEOT fecha: 11/09/2008 */

function copiarValorPrimerCampoConValor(pCampoDestino, pCampoOrigenUno, pCampoOrigenDos, pEsNumerico, pNumeroDecimales){
    var respuesta_local = null;
    
    if(pCampoDestino == null){
        return;
    }
    if(pCampoOrigenUno == null){
        return;
    }
    if(pCampoOrigenDos == null){
        return;
    }
    
    try {
        var campoDestino_local = document.getElementById(pCampoDestino.id);
        var campoOrigenUno_local = document.getElementById(pCampoOrigenUno.id);
        var campoOrigenDos_local = document.getElementById(pCampoOrigenDos.id);
        
        if(campoDestino_local  == null){
            return;
        }
        if(campoOrigenUno_local  == null){
            return;
        }
        if(campoOrigenDos_local  == null){
            return;
        }
        
        respuesta_local = campoOrigenUno_local.value;
        if(this.verificarEstaVacio(respuesta_local) || respuesta_local == "0" || respuesta_local == "0.00"){
            respuesta_local = campoOrigenDos_local.value;
        }
        
        if(pEsNumerico){
            respuesta_local = this.redondearNumero(respuesta_local, pNumeroDecimales);
        }         
        if (campoDestino_local.type == "text"){
            if (!campoDestino_local.disabled){
                campoDestino_local.value = respuesta_local;
            }
        } else {
            campoDestino_local.value = respuesta_local;        
        }
    } catch(e){
    }
}

/* Mensaje de operaci�n no v�lido     */
/* Modific� ZDOR fecha: 10/05/2007 */
/* Modific� GEOT fecha: */

function operacionNoValida(){
    try {
        this.alert("Operaci�n no permitida con fechas revise la configuraci�n del campo");
    } catch(e){
    }
}

/* Mensaje de advertencia para ejecutar consulta principal  */
/* Modific� ZDOR fecha: 13/11/2007 */
/* Modific� GEOT fecha: 19/10/2007 */

function advertenciaConsultaPrincipal(){
    return this.confirm('No existen opciones de consulta seleccionadas. \n Es posible que el tiempo empleado en la ejecuci�n de la consulta sea considerable. \n ' +
        'Desea Continuar?') && this.confirm('Esta seguro que desea continuar?');
}

/* Mensaje de advertencia para ejecutar consulta principal sin opciones de consulta */
/* Modific� ZDOR fecha: */
/* Modific� GEOT fecha: 21/01/2008 */

function advertenciaConsultaPrincipalSinOpciones(){
    this.alert('Debe seleccionar al menos una opci�n de consulta. \n');    
    return false;
}

/* Mensaje de confirmaci�n para incluir un registro */
/* Modific� ZDOR fecha: */
/* Modific� GEOT fecha: 21/01/2009 */

function preguntaIncluirRegistro(){
    var respuesta_local = null;

    try {
        respuesta_local = this.confirm('Desea incluir el registro actual?')
        if (respuesta_local){
            this.mostrarMensajeProcesandoInformacion();
        }
    } catch (exception) { 

    }

    return respuesta_local;
}

/* Mensaje de confirmaci�n para modificar un registro */
/* Modific� ZDOR fecha: */
/* Modific� GEOT fecha: 21/01/2009 */

function preguntaModificarRegistro(){
    var respuesta_local = null;

    try {
        respuesta_local = this.confirm('Desea modificar el registro actual?');
        if (respuesta_local){
            this.mostrarMensajeProcesandoInformacion();
        }
    } catch (exception) {

    }

    return respuesta_local;
}

/* Mensaje de confirmaci�n para regresar a pantalla anterior*/
/* Modific� ZDOR fecha: */
/* Modific� GEOT fecha: 21/01/2009 */

function preguntaRegresarPantallaAnterior(){
    var respuesta_local = null;

    try {
        respuesta_local = this.confirm('Desea regresar a la pantalla anterior?');
        if (respuesta_local){
            this.mostrarMensajeProcesandoInformacion();
        }
    } catch (exception) {

    }

    return respuesta_local;
}

/* Mensaje de confirmaci�n para cancelar inclusi�n */
/* Modific� ZDOR fecha: */
/* Modific� GEOT fecha: 21/01/2009 */

function preguntaCancelarInclusion(){
    var respuesta_local = null;

    try {
        respuesta_local = this.confirm('Desea cancelar la inclusi�n?');
        if (respuesta_local){
            this.mostrarMensajeProcesandoInformacion();
        }
    } catch (exception) {

    }

    return respuesta_local;
}

/* Mensaje de confirmaci�n para cancelar modificaci�n*/
/* Modific� ZDOR fecha: */
/* Modific� GEOT fecha: 21/01/2009 */

function preguntaCancelarModificacion(){
    var respuesta_local = null;

    try {
        respuesta_local = this.confirm('Desea cancelar la modificaci�n?');
        if (respuesta_local){
            this.mostrarMensajeProcesandoInformacion();
        }
    } catch (exception) {

    }

    return respuesta_local;
}


/* Modific� ZDOR fecha: 04/02/2008 */
/* Modific� GEOT fecha: 08/02/2008 */

/* FUNCION QUE INHABILITA LA TECLA ENTER */
function inhabilitarEnter(){
   if(event.keyCode==13) 
       return false;
   else return true;
}

/* Modific� ZDOR fecha: 11/02/2008 */
/* Modific� GEOT fecha: 11/09/2008 */

/* FUNCION QUE COMPLETA UNA CADENA CON LOS CARACTERES INDICADOS */
function completarCadenaConCaracteres(pCadena, pCaracter, pLongitud, pCompletarDerecha){
    var cadena_local = "";
    var longitudCadenaCompletar_local = 0;
    var cadenaCompletar_local = "";
    
    if(pCadena == null){
        return cadena_local;
    }
    if(pCaracter == null){
        return cadena_local;
    }
    try{
        cadena_local = pCadena;
        if(cadena_local.length < pLongitud){
            longitudCadenaCompletar_local = parseInt(pLongitud - cadena_local.length);
            for(i = 0; i < longitudCadenaCompletar_local; i++){
                cadenaCompletar_local = cadenaCompletar_local.concat(pCaracter);
            }
            
            if(pCompletarDerecha){
                cadena_local = cadena_local.concat(cadenaCompletar_local);
            } else {
                cadena_local = cadenaCompletar_local.concat(cadena_local);
            }
        }
    } catch (e){
        
    }
    return cadena_local;
}

/* Modific� ZDOR fecha: 11/02/2008 */
/* Modific� GEOT fecha: */

/* FUNCION QUE COMPLETA EL VALOR DE UN OBJETO CON CEROS A IZQUIERDA */
function completarValorConCeros(pObjeto, pLongitud){
    var elemento_local = null;
    
    if(pObjeto == null){
        return;
    }
    
    try{
        elemento_local = document.getElementById(pObjeto.id);
        if(elemento_local != null){
            elemento_local.value = completarCadenaConCaracteres(elemento_local.value, "0", pLongitud, false);
        }
    } catch (e){
    }
}

/* Modific� ZDOR fecha:  */
/* Modific� GEOT fecha: 12/09/2008 */

/* FUNCION QUE BORRA LOS CEROS A IZQUIERDA */
function borrarCerosIzquierda(pNumero, pEsEntero){
    var nuevoNumero_local = null;
    
    try {
        if (pNumero == "0" || pNumero == "0.00"){
            nuevoNumero_local = pNumero;
        } else {
            if (pNumero.charAt(0) != '0'){
                nuevoNumero_local = pNumero;       
            } else {
                while (pNumero.charAt(0) == '0'){
                    pNumero = pNumero.substring(1, pNumero.length);
                }
                if (this.verificarEstaVacio(pNumero)){
                    if(pEsEntero){
                        pNumero = "0";
                    } else {
                        pNumero = "0.00";
                    }                   
                }
                nuevoNumero_local = pNumero; 
            }
        }
    } catch (exception) { 
    }
    
    return nuevoNumero_local;
}

/* Modific� ZDOR fecha: 11/02/2008 */
/* Modific� GEOT fecha: */

/* FUNCION QUE COLOCA EL VALOR POR DEFECTO A CAMPOS NUM�RICOS*/
function colocarValorPorDefecto(pObjeto, pEsEntero, pBorrarCerosIzquierda){
    var elemento_local = null;
    var valor_local = null;
    if(pObjeto == null){
        return;
    }
    
    try{
        elemento_local = document.getElementById(pObjeto.id);
        if(elemento_local != null){
            valor_local = elemento_local.value;
            if(valor_local.length == 0){
                if(pEsEntero){
                    elemento_local.value = "0";
                } else {
                    elemento_local.value = "0.00";
                }
            } else {
                if (pBorrarCerosIzquierda){
                    elemento_local.value = this.borrarCerosIzquierda(valor_local, pEsEntero);
                }
            }
        }
    } catch (e){
    }
}

/* Modific� ZDOR fecha:  */
/* Modific� GEOT fecha: 21/01/2009 */

/* FUNCION QUE MUESTRA EL MENSAJE PROCESANDO INFORMACI�N Y DESAPARECE LAS TABLAS DE CONSULTA GENERAL, NAVEGACI�N Y FORMULARIO*/

function mostrarMensajeProcesandoInformacion(){
    var tablasPagina_local = null;
    var tabla_local = null
    var imagenMensaje_local = null;
    var i = 0;

    try {
        tablasPagina_local = document.getElementsByTagName('table');

        for (i=0; i<tablasPagina_local.length; i++){
          tabla_local = tablasPagina_local[i];
          if (tabla_local != null){
              tabla_local.style.display = 'none';
          }
        }

        imagenMensaje_local = document.getElementById('imagenprocesandoinformacion');
        if (imagenMensaje_local != null){
           imagenMensaje_local.style.display='inline';
        }
    } catch (excepcion) {

    }
}

/* Modific� GEOT fecha: 15/04/2009 */

/* FUNCION QUE CONTROLA EL MAXIMO DE CARACTERES EN UN TEXTAREA*/
function controlarMaximaLongitud(pObjetoTextArea){
    var maximaLongitud_local = pObjetoTextArea.getAttribute? parseInt(pObjetoTextArea.getAttribute("maxlength")) : ""

    try{
        if (pObjetoTextArea.getAttribute && pObjetoTextArea.value.length>maximaLongitud_local){
            pObjetoTextArea.value=pObjetoTextArea.value.substring(0,maximaLongitud_local)
        }
    } catch (excepcion) {

    }
}

/* Modific� GEOT fecha: 26/05/2009 */

/* FUNCION QUE OCULTA EL DESTINO DE LOS V�NCULOS EN LA BARRA DE ESTADO*/
function ocultarEstado(){
    try{
     /*window.status='';*/
     return true;
    } catch (excepcion){
    }
}

/* Modific� GEOT fecha: 30/01/2010 */

/* FUNCION QUE VERIFICA SI LA CADENA TIENE AL MENOS UN N�MERO*/
function contieneNumeros( pCadena ){
    var contieneNumeros_local = false;
    var i_local;
    var c;

    if ( pCadena == null){
        return contieneNumeros_local;
    }
    
    try{        
        for (i_local=0; i_local<pCadena.length; i_local++){
            c = pCadena.charAt(i_local);            
            if (this.verificarEsDigito(c)){
                contieneNumeros_local = true;
                break;
            }
        }
    } catch ( excepcion ){

    }

    return contieneNumeros_local;
}

/* Modific� GEOT fecha: 30/01/2010 */

/* FUNCION QUE VERIFICA SI LA CADENA TIENE AL MENOS UNA LETRA*/
function contieneLetras( pCadena ){
    var contieneLetras_local = false;
    var i_local;
    var c;

    if ( pCadena == null){
        return contieneLetras_local;
    }

    try{
        for (i_local=0; i_local<pCadena.length; i_local++){
            c = pCadena.charAt(i_local);
            if (this.verificarEsLetra(c)){
                contieneLetras_local = true;
                break;
            }
        }
    } catch ( excepcion ){

    }

    return contieneLetras_local;
}

/* Modific� GEOT fecha: 06/02/2010 */

/* FUNCION QUE VERIFICA SI LA CADENA TIENE AL MENOS UNA LETRA MAYUSCULA*/
function contieneLetraMayusucula( pCadena ){
    var contieneLetraMayuscula_local = false;
    var i_local;
    var c;

    if ( pCadena == null){
        return contieneLetraMayuscula_local;
    }

    try{
        for (i_local=0; i_local<pCadena.length; i_local++){
            c = pCadena.charAt(i_local);
            if (this.verificarEsLetraMayuscula(c)){
                contieneLetraMayuscula_local = true;
                break;
            }
        }
    } catch ( excepcion ){

    }

    return contieneLetraMayuscula_local;
}

/* Modific� GEOT fecha: 06/02/2010 */

/* FUNCION QUE VERIFICA SI LA CADENA TIENE AL MENOS UNA LETRA MINUSCULA*/
function contieneLetraMinuscula( pCadena ){
    var contieneLetraMinuscula_local = false;
    var i_local;
    var c;

    if ( pCadena == null){
        return contieneLetraMinuscula_local;
    }

    try{
        for (i_local=0; i_local<pCadena.length; i_local++){
            c = pCadena.charAt(i_local);
            if (this.verificarEsLetraMinuscula(c)){
                contieneLetraMinuscula_local = true;
                break;
            }
        }
    } catch ( excepcion ){

    }

    return contieneLetraMinuscula_local;
}

/* Modific� GEOT fecha: 30/01/2010 */

/* FUNCION QUE CUENTA EL N�MERO DE CARACTERES ESPECIALES*/
function obtenerNumeroCaracteresEspeciales( pCadena ){
    var numeroCaracteresEspeciales_local = 0;
    var i_local;
    var c;

    if ( pCadena == null){
        return numeroCaracteresEspeciales_local;
    }

    try{
        for (i_local=0; i_local<pCadena.length; i_local++){
            c = pCadena.charAt(i_local);
            if (!this.verificarEsLetra(c) && !this.verificarEsNumero(c) &&
                this.verificarEsCaracterEspecialPermitido(c)){
                numeroCaracteresEspeciales_local++;                
            }
        }
    } catch ( excepcion ){

    }

    return numeroCaracteresEspeciales_local;
}

/* Modific� GEOT fecha: 30/01/2010 */

/* FUNCION QUE VALIDA UNA CONTRASE�A M�NIMO 8 CARACTERES ALFANUMERICA
 * DEBE EXISTIR AL MENOS UN N�MERO Y UNA LETRA MASYUSCULAS Y MINUSCULAS
 * UN CARACTER ESPECIAL SOLO UNO*/

function validarContrasena( pContrasena ){
    var validarContrasena_local = false;

    if (pContrasena == null){        
        return true;
    }
    if (pContrasena == ""){
        return true;
    }

    try{        
        validarContrasena_local = (pContrasena.length >= 8) &&
            (this.contieneNumeros(pContrasena)) &&
            (this.contieneLetras(pContrasena)) &&
            (this.contieneLetraMayusucula(pContrasena)) &&
            (this.contieneLetraMinuscula(pContrasena)) &&
            (this.obtenerNumeroCaracteresEspeciales(pContrasena) == 1);

        if (!validarContrasena_local){
            alert("La contrase�a es inv�lida: debe contener m�nimo 8 caracteres, n�meros, letras (al menos una min�scula y una may�scula) y un caracter especial ")
        }
    } catch (excepcion){
        
    }

    return validarContrasena_local;
}

/*
window.onpopstate = function(event) {
  alert("location: " + document.location + ", state: " + JSON.stringify(event.state));
};

// Vanilla javascript
window.addEventListener('popstate', function (e) {
    var state = e.state;
    if (state !== null) {
        //load content with ajax
    }
});*/
/*
$(window).on('popstate', function(event) {
 alert("pop");
});

window.onhashchange = function() {
 alert("poper");
}
*/
$(document).on('mouseenter', 'img[data-customimg="true"],input[data-customimg="true"]', function() {
   //do something
   var src = $(this).attr("src");
   var basen = src.lastIndexOf("/");
   var baseUrl = src.substr(0, basen + 1);
   var n = src.lastIndexOf("/");
   src = src.substr(n + 1);
   src = src.replace("_hover", "").replace("_focus", "");
   src = src.replace(".", "_hover.");
   src = baseUrl + src;
   $(this).attr("src", src);
   //console.log("overe " + src);
});
$(document).on('mouseleave', 'img[data-customimg="true"],input[data-customimg="true"]', function() {
   //do something
   var self = $(this);
   var src = self.attr("src");
   var basen = src.lastIndexOf("/");
   var baseUrl = src.substr(0, basen + 1);
   var n = src.lastIndexOf("/");
   src = src.substr(n + 1);
   src = src.replace("_hover", "").replace("_focus", "");
   if(self.is("input")){
	   if(self.is(":focus"))
	   {
		src = src.replace(".", "_focus."); 
	   }
   }
   else	if(self.closest("a").is(":focus"))
   {
	  src = src.replace(".", "_focus."); 
   }   
   
   src = baseUrl + src;
   self.attr("src", src);
   //console.log("oute");
});
document.onkeydown = disableF5;

jQuery(document).on("keydown",disableF5); 
 
function disableF5(evt) { 
	var f5key = 116;
	var rkey = 82;
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	// Check for refresh
	if (charCode == f5key || (evt.ctrlKey && charCode == rkey)) {
		//console.log("disableF5");
		evt.preventDefault();
		return false;
	}
	return true;
 };
	 
/*
(function($) {
    var refreshKeyPressed = false;
    var modifierPressed = false;

    var f5key = 116;
    var rkey = 82;
    var modkey = [17, 224, 91, 93];

    // Check for refresh keys
    $(document).bind(
        'keydown',
        function(evt) {
            // Check for refresh
            if (evt.which == f5key || window.modifierPressed && evt.which == rkey) {
                refreshKeyPressed = true;
            }

            // Check for modifier
            if (modkey.indexOf(evt.which) >= 0) {
                modifierPressed = true;
            }
        }
    );

    // Check for refresh keys
    $(document).bind(
        'keyup',
        function(evt) {
            // Check undo keys
            if (evt.which == f5key || evt.which == rkey) {
                refreshKeyPressed = false;
            }

            // Check for modifier
            if (modkey.indexOf(evt.which) >= 0) {
                modifierPressed = false;
            }
        }
    );

    $(window).bind('beforeunload', function(event) {

        if (refreshKeyPressed) {
			var message = true;
            event.preventDefault();
			event.returnValue = message;
			return message;
        }
    });
}(jQuery));
*/
jQuery(document).ready(function($) {
	
		//console.log("window 3", typeof history.pushState);
		if (typeof history.pushState === "function") {
			history.pushState("jibberish", null, null);
			window.onpopstate = function () {
				history.pushState('newjibberish', null, null);
				// Handle the back (or forward) buttons here
				// Will NOT handle refresh, use onbeforeunload for this.
			};
		}
		else {
			var ignoreHashChange = true;
			window.onhashchange = function () {
				if (!ignoreHashChange) {
					ignoreHashChange = true;
					window.location.hash = Math.random();
					// Detect and redirect change here
					// Works in older FF and IE9
					// * it does mess with your hash symbol (anchor?) pound sign
					// delimiter on the end of the URL
				}
				else {
					ignoreHashChange = false;   
				}
			};
		}
	
   $('img[data-customimg="true"],input[data-customimg="true"]').each(function(index, element) {
		var self = $(this); 
		var a = self.is("input") ? self : $(this).closest("a");
		
		a.focus(function(){
			//console.log("focus 1");
			//do something
		   var _self =  $(this);
		   //console.info(_self);
		   var src = _self.is("input") ? _self.attr("src") : _self.find("img").attr("src");
		   //console.info("ss");
		   //console.info(src);
		   var basen = src.lastIndexOf("/");
		   var baseUrl = src.substr(0, basen + 1);
		   var n = src.lastIndexOf("/");
		   src = src.substr(n + 1);
		   src = src.replace("_hover", "").replace("_focus", "");
		   src = src.replace(".", "_focus.");
		   src = baseUrl + src;
		   if(_self.is("input"))
		   {
			_self.attr("src", src);
		   }
		   else{
			_self.find("img").attr("src", src);   
		   }
		   
		   //console.log("focus 2");
	   });
	   
	   a.blur(function(){
		    //console.log("blur 1");
			//do something
		   var _self =  $(this);
		   //console.info(_self);	
		   var src = _self.is("input") ? _self.attr("src") : _self.find("img").attr("src");
		   var basen = src.lastIndexOf("/");
		   var baseUrl = src.substr(0, basen + 1);
		   var n = src.lastIndexOf("/");
		   src = src.substr(n + 1);
		   src = src.replace("_hover", "").replace("_focus", "");
		   src = baseUrl + src;
		   if(_self.is("input"))
		   {
			_self.attr("src", src);
		   }
		   else{
			_self.find("img").attr("src", src);
		   }
		   
		   //console.log("blur 2");
	   });
	});
	
	
		
	/**/
});

