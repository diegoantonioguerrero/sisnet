package com.sisnet.constantes;
public final class ConstantesMensajesAplicacion
{
  private static String const_DescripcionMensajeBienvenida = "Bienvenido al aplicativo SISNET, para ambiente Intranet e Internet.";
  private static String const_DescripcionMensajeUsuarioNoValido = "Usuario y/o contrase\u00f1a no v\u00e1lidos";
  private static String const_DescripcionMensajeSesionCaducada = "Ha excedido el tiempo m\u00e1ximo de inactividad de sesi\u00f3n, por lo tanto \u00e9sta ha caducado";
  private static String const_DescripcionMensajeSesionCerrada = "Su Sesi\u00f3n se ha cerrado satisfactoriamente";
  private static String const_DescripcionMensajeNoConexionBaseDatos = "No se ha establecido conexi\u00f3n con la Base de Datos";
  private static String const_DescripcionMensajeFalloConsultaSQL = "No se ha efectuado la Consulta en la Base de Datos";
  private static String const_DescripcionMensajeNoFinalizacionConexionBaseDatos = "No se ha finalizado la conexi\u00f3n con la Base de Datos";
  private static String const_DescripcionMensajeRegistroUsuario = "El usuario ha sido registrado satisfactoriamente";
  private static String const_DescripcionMensajeAplicacionModificada = "La aplicaci\u00f3n se ha modificado correctamente";
  private static String const_DescripcionMensajeGrupoInformacionModificado = "El m\u00f3dulo se ha modificado correctamente";
  private static String const_DescripcionMensajeCampoModificado = "El campo se ha modificado correctamente";
  private static String const_DescripcionMensajeFechaNoValida = "La fecha que seleccion\u00f3 no es v\u00e1lida, intente nuevamente";
  private static String const_DescripcionMensajeFalloRegistroPrincipal = "Error al incluir registro";
  private static String const_DescripcionMensajeFalloBorrarRegistro = "Error al borrar registro";
  private static String const_DescripcionMensajeFalloModificarRegistro = "Error al modificar el registro";
  private static String const_DescripcionMensajeFalloCambioTipoDato = "No puede realizar ese cambio de tipo de dato";
  private static String const_DescripcionMensajeRegistroRelacionado = "No puede borrar este registro pues est\u00e1 siendo utilizado por otra aplicaci\u00f3n";
  public static String const_DescripcionMensajeExportacion = "El resultado de la exportaci\u00f3n se encuentra a continuaci\u00f3n, haga click sobre el v\u00ednculo para descargar el archivo.";
  public static String const_DescripcionMensajeErrorAlCrearArchivo = "No fue posible crear el archivo, revise los permisos en la carpeta correspondiente";
  public static String const_DescripcionMensajePermisosAplicacion = "No posee autorizaci\u00f3n para ingresar a la aplicaci\u00f3n deseada.";
  public static String const_DescripcionMensajeCambioContrasena = "Debe cambiar su contrase\u00f1a para ingresar al sistema";
  public static String const_DescripcionMensajeBloqueoManual = "Usuario bloqueado";
  public static String const_DescripcionMensajeBloqueoContrasenasFallidas = "Usuario bloqueado por cantidad de contrase\u00f1as fallidas ingresadas";
  public static String const_DescripcionMensajeBloqueoVencimientoContrasena = "Usuario bloqueado por vencimiento de la contrase\u00f1a";
  public static String const_DescripcionMensajeModificacionContrasena = "Se realiz\u00f3 correctamente el cambio de contrase\u00f1a";
  public static String const_DescripcionMensajeErrorImportacion = "Se encontraron errores en el archivo de importaci\u00f3n, para ver el informe haga click sobre el v\u00ednculo para descargar el archivo.";
  public static String const_DescripcionMensajeArchivoImportacionNoEncontrado = "No se pudo leer el archivo de importaci\u00f3n, verif\u00edquelo e intente nuevamente";
  public static String const_DescripcionMensajeImportacionExistosa = "La importaci\u00f3n se realizo correctamente";
  public static String const_DescripcionMensajeDigiteNuevaContrasena = "Digite la nueva contrase\u00f1a y conf\u00edrmela para validar que sea correcta";
  public static String const_DescripcionMensajeConfigurandoAplicacion = "Espere un momento mientras se configura la aplicaci\u00f3n. Este proceso puede tardar algunos minutos...";
  public static final String const_DescripcionMensajeDuplicadoAplicacionTablaGrupoInformacion = "El nombre de la Aplicaci\u00f3n, Tabla o Grupo de Informaci\u00f3n ya se encuentra referenciado en el sistema, por lo tanto genera error de duplicidad";
  public static final String const_DescripcionMensajeAplicacionInicial = "No existen aplicaciones registradas, digite el t\u00edtulo de la aplicaci\u00f3n inicial. Recuerde que debe ser un usuario administrador del sistema";
  public static String const_DescripcionMensajeDescargandoArchivo = "Espere un momento mientras se descarga el archivo. Este proceso puede tardar algunos minutos...";
  public static String const_DescripcionMensajeArchivoDescargado = "El archivo descargado se encuentra a continuaci\u00f3n, haga click sobre el v\u00ednculo para obtenerlo.";
  public static String const_DescripcionMensajeFalloLongitudCampos = "Revise la longitud de los datos, ya que es superior a la permitida";
  public static String const_DescripcionMensajeFalloEjecucionEventos = "No puede hacer click dos veces; debe cerrar la ventana internet y volver a entrar a SISNET";
  public static String const_DescripcionMensajeNoAutorizacion = "No est\u00e1 autorizado para ingresar a la aplicaci\u00f3n";
  public static String const_DescripcionMensajeNoAutorizacionCambioContrasena = "No est\u00e1 autorizado para cambiar la contrase\u00f1a";
  public static String const_DescripcionMensajeDebeCambiarContrasena = "Debe cambiar su contrase\u00f1a";
  public static final int const_NumeroMensajeHuboError = -1;
  public static final int const_NumeroMensajeNoHuboError = 0;
  public static final int const_NumeroMensajeBienvenida = 1;
  public static final int const_NumeroMensajeUsuarioNoValido = 2;
  public static final int const_NumeroMensajeSesionCaducada = 3;
  public static final int const_NumeroMensajeSesionCerrada = 4;
  public static final int const_NumeroMensajePaginaIncluirModulos = 5;
  public static final int const_NumeroMensajeNoConexionBaseDatos = 6;
  public static final int const_NumeroMensajeFalloConsultaSQL = 7;
  public static final int const_NumeroMensajeNoFinalizacionConexionBaseDatos = 8;
  public static final int const_NumeroMensajeDebeCambiarContrasena = 9;
  public static final int const_NumeroMensajeRegistroUsuario = 10;
  public static final int const_NumeroMensajeAplicacionModificada = 11;
  public static final int const_NumeroMensajeGrupoInformacionModificado = 12;
  public static final int const_NumeroMensajeCampoModificado = 13;
  public static final int const_NumeroMensajeFechaNoValida = 14;
  public static final int const_NumeroMensajeFalloRegistroPrincipal = 15;
  public static final int const_NumeroMensajeFalloBorrarRegistro = 16;
  public static final int const_NumeroMensajeRegistroRelacionado = 17;
  public static final int const_NumeroMensajeFalloModificarRegistro = 18;
  public static final int const_NumeroMensajePermisosAplicacion = 19;
  public static final int const_NumeroMensajeCambioContrasena = 20;
  public static final int const_NumeroMensajeBloqueoManual = 21;
  public static final int const_NumeroMensajeBloqueoContrasenasFallidas = 22;
  public static final int const_NumeroMensajeBloqueoVencimientoContrasena = 23;
  public static final int const_NumeroMensajeModificacionContrasena = 24;
  public static final int const_NumeroMensajeArchivoImportacionNoEncontrado = 26;
  public static final int const_NumeroMensajeImportacionExitosa = 27;
  public static final int const_NumeroMensajeConfigurandoAplicacion = 28;
  public static final int const_NumeroMensajeDuplicadoAplicacionTablaGrupoInformacion = 29;
  public static final int const_NumeroMensajeAplicacionInicial = 30;
  public static final int const_NumeroMensajeDescargandoArchivo = 31;
  public static final int const_NumeroMensajeArchivoDescargado = 32;
  public static final int const_NumeroMensajeNoAutorizacion = 33;
  public static final int const_NumeroMensajeNoAutorizacionCambioContrasena = 34;
  public static final String const_DescripcionMensajeDuplicadoLlavePrimaria = "Registro repetido; revise los datos.";
  public static final int const_NumeroMensajeDuplicadoLlavePrimaria = 23505;
  public static final int const_NumeroMensajeFalloCambioTipoDato = 42804;
  public static final int const_NumeroMensajeFalloLongitudCampos = 22001;
  public static final String const_ParametroNumeroError = "numeroerror";
  public static final String const_ParametroTipoError = "tipoerror";
  public static final int const_ValorParametroNoHayError = -1;
  public static final int const_ValorParametroAdvertencia = 1;
  public static final int const_ValorParametroError = 2;
  public static final int const_ValorParametroMensaje = 3;
  public static final String getMensajeAplicacion(int pConstanteMensaje) {
    String mensaje_local = "";
    
    try {
      switch (pConstanteMensaje) { case 1:
          mensaje_local = const_DescripcionMensajeBienvenida; break;
        case 2:
          mensaje_local = const_DescripcionMensajeUsuarioNoValido; break;
        case 3:
          mensaje_local = const_DescripcionMensajeSesionCaducada; break;
        case 4:
          mensaje_local = const_DescripcionMensajeSesionCerrada; break;
        case 6:
          mensaje_local = const_DescripcionMensajeNoConexionBaseDatos; break;
        case 7:
          mensaje_local = const_DescripcionMensajeFalloConsultaSQL; break;
        case 8:
          mensaje_local = const_DescripcionMensajeNoFinalizacionConexionBaseDatos; break;
        case 10:
          mensaje_local = const_DescripcionMensajeRegistroUsuario; break;
        case 14:
          mensaje_local = const_DescripcionMensajeFechaNoValida; break;
        case 11:
          mensaje_local = const_DescripcionMensajeAplicacionModificada; break;
        case 12:
          mensaje_local = const_DescripcionMensajeGrupoInformacionModificado; break;
        case 13:
          mensaje_local = const_DescripcionMensajeCampoModificado; break;
        case 15:
          mensaje_local = const_DescripcionMensajeFalloRegistroPrincipal; break;
        case 16:
          mensaje_local = const_DescripcionMensajeFalloBorrarRegistro; break;
        case 18:
          mensaje_local = const_DescripcionMensajeFalloModificarRegistro; break;
        case 42804:
          mensaje_local = const_DescripcionMensajeFalloCambioTipoDato; break;
        case 23505:
          mensaje_local = "Registro repetido; revise los datos."; break;
        case 17:
          mensaje_local = const_DescripcionMensajeRegistroRelacionado; break;
        case 19:
          mensaje_local = const_DescripcionMensajePermisosAplicacion; break;
        case 20:
          mensaje_local = const_DescripcionMensajeCambioContrasena; break;
        case 21:
          mensaje_local = const_DescripcionMensajeBloqueoManual; break;
        case 22:
          mensaje_local = const_DescripcionMensajeBloqueoContrasenasFallidas; break;
        case 23:
          mensaje_local = const_DescripcionMensajeBloqueoVencimientoContrasena; break;
        case 24:
          mensaje_local = const_DescripcionMensajeModificacionContrasena; break;
        case 26:
          mensaje_local = const_DescripcionMensajeArchivoImportacionNoEncontrado; break;
        case 27:
          mensaje_local = const_DescripcionMensajeImportacionExistosa; break;
        case 28:
          mensaje_local = const_DescripcionMensajeConfigurandoAplicacion;
          break;
        case 29:
          mensaje_local = "El nombre de la Aplicaci\u00f3n, Tabla o Grupo de Informaci\u00f3n ya se encuentra referenciado en el sistema, por lo tanto genera error de duplicidad"; break;
        case 30:
          mensaje_local = "No existen aplicaciones registradas, digite el t\u00edtulo de la aplicaci\u00f3n inicial. Recuerde que debe ser un usuario administrador del sistema"; break;
        case 31:
          mensaje_local = const_DescripcionMensajeDescargandoArchivo; break;
        case 32:
          mensaje_local = const_DescripcionMensajeArchivoDescargado; break;
        case 22001:
          mensaje_local = const_DescripcionMensajeFalloLongitudCampos; break;
        case 33:
          mensaje_local = const_DescripcionMensajeNoAutorizacion; break;
        case 34:
          mensaje_local = const_DescripcionMensajeNoAutorizacionCambioContrasena; break;
        case 9:
          mensaje_local = const_DescripcionMensajeDebeCambiarContrasena;
          break; }
    
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return mensaje_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\constantes\ConstantesMensajesAplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */