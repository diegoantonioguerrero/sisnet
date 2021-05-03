package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorAplicacion;
import com.sisnet.aplicacion.manejadores.ManejadorArchivos;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.baseDatos.AdministradorBaseDatos;
import com.sisnet.baseDatos.sisnet.administrador.Aplicacion;
import com.sisnet.baseDatos.sisnet.administrador.Campo;
import com.sisnet.baseDatos.sisnet.administrador.GrupoInformacion;
import com.sisnet.baseDatos.sisnet.administrador.Tabla;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.motorAplicacion.MotorAplicacion;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
import java.util.Iterator;
public class ManejadorImportacion
{
  private static ManejadorAplicacion ap = ManejadorAplicacion.getManejadorAplicacion();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private AdministradorBaseDatos aAdministradorBaseDatosSisnet;
  private AdministradorBaseDatos aAdministradorBaseDatosAplicacion;
  private MotorAplicacion aMotorAplicacion;
  private String aSeparador;
  public ManejadorImportacion() {
    setSeparador("\t");
  }
  public AdministradorBaseDatos getAdministradorBaseDatosSisnet() {
    return this.aAdministradorBaseDatosSisnet;
  }
  public void setAdministradorBaseDatosSisnet(AdministradorBaseDatos pAdministradorBaseDatosSisnet) {
    this.aAdministradorBaseDatosSisnet = pAdministradorBaseDatosSisnet;
  }
  public AdministradorBaseDatos getAdministradorBaseDatosAplicacion() {
    return this.aAdministradorBaseDatosAplicacion;
  }
  public void setAdministradorBaseDatosAplicacion(AdministradorBaseDatos pAdministradorBaseDatosAplicacion) {
    this.aAdministradorBaseDatosAplicacion = pAdministradorBaseDatosAplicacion;
  }
  public MotorAplicacion getMotorAplicacion() {
    return this.aMotorAplicacion;
  }
  public void setMotorAplicacion(MotorAplicacion pMotorAplicacion) {
    this.aMotorAplicacion = pMotorAplicacion;
  }
  public String getSeparador() {
    return this.aSeparador;
  }
  public void setSeparador(String pSeparador) {
    this.aSeparador = pSeparador;
  }
  private boolean verificarValorEnlazado(Aplicacion pAplicacionEnlace, String pDato) {
    boolean existeValorEnlazado = false;
    Campo primerCampo_local = null;
    
    if (pDato == ConstantesGeneral.VALOR_NULO) {
      return existeValorEnlazado;
    }
    
    try {
      primerCampo_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(pAplicacionEnlace.getIdAplicacion());
      existeValorEnlazado = (getAdministradorBaseDatosAplicacion().obtenerIdValorCampoEnlazado(primerCampo_local, pDato, primerCampo_local.esTipoDatoNumerico()) != 0);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      primerCampo_local = null;
    } 
    return existeValorEnlazado;
  }
  private void reportarErrorImportacion(String pRutaArchivoErrores, int pErrorImportacion, int pLineaError, String pNombre) {
    String errorImportacion_local = null;
    String separadorPai_local = null;
    String lineaSeparador_local = null;
    String lineaSeparadorNombre_local = null;
    ManejadorArchivos manejadorArchivos_local = null;
    
    if (pRutaArchivoErrores == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombre == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      errorImportacion_local = "";
      separadorPai_local = mc.concatenarCadena(String.valueOf(' '), String.valueOf('|') + ' ');
      
      lineaSeparador_local = mc.concatenarCadena("Error linea " + pLineaError, separadorPai_local);
      lineaSeparadorNombre_local = mc.concatenarCadena(lineaSeparador_local, pNombre + separadorPai_local);
      switch (pErrorImportacion) {
        case 1:
          errorImportacion_local = mc.concatenarCadena("No existe el grupo de informaci\u00f3n ", pNombre + "\r\n");
          break;
        
        case 2:
          errorImportacion_local = mc.concatenarCadena("No existe el campo ", pNombre + "\r\n");
          break;
        
        case 3:
          errorImportacion_local = mc.concatenarCadena(lineaSeparador_local, "No coincide el n\u00famero de datos con el n\u00famero de campos \r\n");
          break;
        
        case 4:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El valor para \u00e9ste campo es obligatorio y no se encontro \r\n");
          break;
        
        case 5:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El formato de la fecha no es v\u00e1lido \r\n");
          break;
        
        case 6:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El formato de la hora no es v\u00e1lido \r\n");
          break;
        
        case 7:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El valor no corresponde a n\u00famero entero \r\n");
          break;
        
        case 8:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El valor no corresponde a n\u00famero real \r\n");
          break;
        
        case 9:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El valor de tabla no existe \r\n");
          break;
        
        case 10:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "El valor enlazado no existe \r\n");
          break;
        
        case 11:
          errorImportacion_local = mc.concatenarCadena(lineaSeparador_local, "No se encontr\u00f3 el campo llave para referenciar los datos \r\n");
          break;
        
        case 12:
          errorImportacion_local = mc.concatenarCadena(lineaSeparadorNombre_local, "No se encontr\u00f3 el valor de la llave registrado en la base de datos \r\n");
          break;
      } 
      
      manejadorArchivos_local = new ManejadorArchivos();
      manejadorArchivos_local.guardarArchivo(pRutaArchivoErrores, errorImportacion_local, !mc.sonCadenasIguales(errorImportacion_local, ""));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      separadorPai_local = null;
      lineaSeparador_local = null;
      errorImportacion_local = null;
      manejadorArchivos_local = null;
      lineaSeparadorNombre_local = null;
    } 
  }
  private int obtenerValorLlavePrimariaImportacion(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, String[] pDatosImportacion) {
    int valorLlavePrimaria_local = -1;
    int contador_local = 0;
    String dato_local = null;
    String tipoDato_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    if (pDatosImportacion == ConstantesGeneral.VALOR_NULO) {
      return valorLlavePrimaria_local;
    }
    
    try {
      iterador_local = pListaCampo.iterator();
      if (iterador_local.hasNext()) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getRestriccionCampo().esLlavePrimaria() && iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
        }
        if (campo_local.getRestriccionCampo().esLlaveForanea() && mc.sonCadenasIguales(campo_local.getEtiquetaCampo(), "") && iterador_local.hasNext())
        {
          campo_local = (Campo)iterador_local.next();
        }
        tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
        dato_local = mc.borrarEspaciosLaterales(mc.convertirAMayusculas(pDatosImportacion[contador_local]));
        if (pGrupoInformacion.esGrupoInformacionMultiple()) {
          if (campo_local.esCampoEnlazado()) {
            dato_local = obtenerValorEnlazadoImportacion(campo_local.getEnlaceCampo().getEnlazado(), dato_local);
          }
          if (mc.esCadenaNumerica(tipoDato_local, true)) {
            dato_local = String.valueOf(getAdministradorBaseDatosAplicacion().obtenerIdValorTabla(getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(tipoDato_local)).getNombreTabla(), dato_local));
          }
          
          valorLlavePrimaria_local = getAdministradorBaseDatosAplicacion().obtenerIdRegistroValorCampo(campo_local, dato_local, campo_local.esTipoDatoNumerico());
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      dato_local = null;
      campo_local = null;
      iterador_local = null;
      tipoDato_local = null;
    } 
    return valorLlavePrimaria_local;
  }
  private boolean verificarDatosLineaArchivoImportacion(GrupoInformacion pGrupoInformacion, ListaCampo pListaCampo, String[] pDatos, int pNumeroLinea, String pRutaArchivoErrores) {
    boolean datosValidos_local = false;
    int contador_local = 0;
    int numeroErrores_local = 0;
    boolean esObligatorio_local = false;
    boolean esEnlazado_local = false;
    String etiquetaCampo_local = null;
    String tipoDato_local = null;
    String dato_local = null;
    Iterator iterador_local = null;
    Campo campo_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return datosValidos_local;
    }
    if (pDatos == ConstantesGeneral.VALOR_NULO) {
      return datosValidos_local;
    }
    if (pRutaArchivoErrores == ConstantesGeneral.VALOR_NULO) {
      return datosValidos_local;
    }
    
    try {
      iterador_local = pListaCampo.iterator();
      for (contador_local = 0; iterador_local.hasNext(); contador_local++) {
        campo_local = (Campo)iterador_local.next();
        tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
        esEnlazado_local = campo_local.esCampoEnlazado();
        etiquetaCampo_local = campo_local.getEtiquetaCampo();
        esObligatorio_local = campo_local.esObligatorio();
        dato_local = mc.borrarEspaciosLaterales(pDatos[contador_local]);
        if (pGrupoInformacion.esGrupoInformacionMultiple() && contador_local == 0 && obtenerValorLlavePrimariaImportacion(pGrupoInformacion, pListaCampo, pDatos) == -1) {
          
          numeroErrores_local++;
          reportarErrorImportacion(pRutaArchivoErrores, 12, pNumeroLinea, etiquetaCampo_local);
        } 
        
        if (esObligatorio_local && mc.sonCadenasIguales(dato_local, "")) {
          numeroErrores_local++;
          reportarErrorImportacion(pRutaArchivoErrores, 4, pNumeroLinea, etiquetaCampo_local);
        } 
        
        if (esObligatorio_local || !mc.sonCadenasIguales(dato_local, "")) {
          
          if (mc.sonCadenasIguales(tipoDato_local, "F") && !mc.verificarFormatoFecha(dato_local)) {
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 5, pNumeroLinea, etiquetaCampo_local);
          } 
          
          if (mc.sonCadenasIguales(tipoDato_local, "H") && !mc.verificarFormatoHora(dato_local)) {
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 6, pNumeroLinea, etiquetaCampo_local);
          } 
          
          if (campo_local.esTipoDatoNumeroEntero() && !esEnlazado_local && 
            !mc.esCadenaNumerica(dato_local, true)) {
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 7, pNumeroLinea, etiquetaCampo_local);
          } 
          
          if (mc.sonCadenasIguales(tipoDato_local, "R") && !mc.esCadenaNumerica(dato_local, false)) {
            
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 8, pNumeroLinea, etiquetaCampo_local);
          } 
          
          dato_local = mc.convertirAMayusculas(dato_local);
          if (esEnlazado_local && !verificarValorEnlazado(campo_local.getEnlaceCampo().getEnlazado(), dato_local)) {
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 10, pNumeroLinea, etiquetaCampo_local);
          } 
          
          if (mc.esCadenaNumerica(tipoDato_local, true) && 
            !getAdministradorBaseDatosAplicacion().verificarExistenciaValorTabla(getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(tipoDato_local)).getNombreTabla(), dato_local)) {
            
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 9, pNumeroLinea, etiquetaCampo_local);
          } 
        } 
      } 
      
      datosValidos_local = (numeroErrores_local == 0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      dato_local = null;
      campo_local = null;
      iterador_local = null;
      tipoDato_local = null;
      etiquetaCampo_local = null;
    } 
    return datosValidos_local;
  }
  private ListaCampo obtenerCamposArchivoImportacion(GrupoInformacion pGrupoInformacion, String[] pCampos, String pRutaArchivoErrores) {
    ListaCampo listaCampo_local = null;
    int contador_local = 0;
    String etiquetaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    if (pCampos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    if (pRutaArchivoErrores == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      while (contador_local < pCampos.length) {
        etiquetaCampo_local = mc.borrarEspaciosLaterales(pCampos[contador_local]);
        if (!mc.sonCadenasIguales(etiquetaCampo_local, "")) {
          if (!getAdministradorBaseDatosSisnet().verificarExistenciaCampoPorEtiqueta(etiquetaCampo_local, pGrupoInformacion, contador_local)) {
            
            if (pGrupoInformacion.esGrupoInformacionMultiple() && contador_local == 0) {
              reportarErrorImportacion(pRutaArchivoErrores, 11, 2, etiquetaCampo_local);
            } else {
              
              reportarErrorImportacion(pRutaArchivoErrores, 2, 2, etiquetaCampo_local);
            } 
            
            listaCampo_local.borrarElementos();
            break;
          } 
          listaCampo_local.adicionar(getMotorAplicacion().obtenerCampoPorEtiqueta(etiquetaCampo_local, pGrupoInformacion, contador_local));
        } 
        
        contador_local++;
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaCampo_local;
  }
  public boolean revisarArchivoImportacion(int pIdAplicacion, ListaCadenas pListaCadenas, String pRutaArchivoErrores) {
    boolean archivoImportacionValido_local = false;
    int contador_local = 1;
    int numeroCampos_local = -1;
    int numeroErrores_local = 0;
    int numeroLinea_local = 0;
    String linea_local = null;
    String[] campos_local = null;
    String[] datos_local = null;
    GrupoInformacion grupoInformacion_local = null;
    Iterator iterador_local = null;
    ListaCampo listaCampo_local = null;
    
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return archivoImportacionValido_local;
    }
    if (pRutaArchivoErrores == ConstantesGeneral.VALOR_NULO) {
      return archivoImportacionValido_local;
    }
    
    try {
      reportarErrorImportacion(pRutaArchivoErrores, 0, 0, "");
      
      iterador_local = pListaCadenas.iterator();
      for (contador_local = 0; iterador_local.hasNext(); contador_local++) {
        linea_local = (String)iterador_local.next();
        if (contador_local == 0) {
          linea_local = mc.borrarEspaciosLaterales(linea_local);
          if (mc.sonCadenasIgualesIgnorarMayusculas(linea_local, "No multiple") || getAdministradorBaseDatosSisnet().verificarExistenciaGrupoInformacionPorDescripcion(linea_local, pIdAplicacion)) {
            
            grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionPorDescripcion(pIdAplicacion, linea_local);
          } else {
            reportarErrorImportacion(pRutaArchivoErrores, 1, 1, linea_local);
            
            numeroErrores_local++;
            
            break;
          } 
        } else if (contador_local == 1) {
          if (mc.verificarExistenciaSubCadena(linea_local, String.valueOf('&'))) {
            setSeparador(String.valueOf('&'));
          }
          campos_local = mc.fraccionarCadena(mc.convertirAMayusculas(linea_local), getSeparador());
          listaCampo_local = obtenerCamposArchivoImportacion(grupoInformacion_local, campos_local, pRutaArchivoErrores);
          if (listaCampo_local.contarElementos() > 0) {
            numeroCampos_local = campos_local.length;
          } else {
            numeroErrores_local++;
            break;
          } 
        } else {
          numeroLinea_local = contador_local + 1;
          datos_local = mc.fraccionarCadena(linea_local, getSeparador());
          if (numeroCampos_local != datos_local.length) {
            numeroErrores_local++;
            reportarErrorImportacion(pRutaArchivoErrores, 3, numeroLinea_local, "");
          } 
          
          if (!verificarDatosLineaArchivoImportacion(grupoInformacion_local, listaCampo_local, datos_local, numeroLinea_local, pRutaArchivoErrores))
          {
            numeroErrores_local++;
          }
        } 
      } 
      
      archivoImportacionValido_local = (numeroErrores_local == 0);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      linea_local = null;
      datos_local = null;
      campos_local = null;
      iterador_local = null;
      listaCampo_local = null;
      grupoInformacion_local = null;
    } 
    return archivoImportacionValido_local;
  }
  private String obtenerValorEnlazadoImportacion(Aplicacion pAplicacionEnlace, String pValorEnlace_local) {
    String valorCampoEnlazado_local = "";
    Campo primerCampo_local = null;
    
    if (pValorEnlace_local == ConstantesGeneral.VALOR_NULO) {
      return valorCampoEnlazado_local;
    }
    
    try {
      primerCampo_local = getMotorAplicacion().obtenerPrimerCampoValorUnicoAplicacion(pAplicacionEnlace.getIdAplicacion());
      valorCampoEnlazado_local = String.valueOf(getAdministradorBaseDatosAplicacion().obtenerIdValorCampoEnlazado(primerCampo_local, pValorEnlace_local, primerCampo_local.esTipoDatoNumerico()));
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      primerCampo_local = null;
    } 
    
    return valorCampoEnlazado_local;
  }
  private void asignarDatosImportacionListaCampos(ListaCampo pListaCampo, String[] pDatosImportacion) {
    int contador_local = -1;
    String dato_local = null;
    String tipoDato_local = null;
    Tabla tabla_local = null;
    Campo campo_local = null;
    Iterator iterador_local = null;
    
    if (pListaCampo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDatosImportacion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = pListaCampo.iterator();
      for (contador_local = 0; iterador_local.hasNext(); contador_local++) {
        campo_local = (Campo)iterador_local.next();
        if (campo_local.getRestriccionCampo().esLlavePrimaria() && iterador_local.hasNext()) {
          campo_local = (Campo)iterador_local.next();
        }
        if (campo_local.getRestriccionCampo().esLlaveForanea() && mc.sonCadenasIguales(campo_local.getEtiquetaCampo(), "") && iterador_local.hasNext())
        {
          campo_local = (Campo)iterador_local.next();
        }
        tipoDato_local = campo_local.getFormatoCampo().getTipoDato();
        dato_local = mc.borrarEspaciosLaterales(pDatosImportacion[contador_local]);
        if (mc.esCadenaNumerica(tipoDato_local, true)) {
          dato_local = mc.convertirAMayusculas(dato_local);
          tabla_local = getAdministradorBaseDatosSisnet().obtenerTablaPorId(Integer.parseInt(tipoDato_local));
          dato_local = String.valueOf(getAdministradorBaseDatosAplicacion().obtenerIdValorTabla(tabla_local.getNombreTabla(), dato_local));
        } 
        
        if ((mc.sonCadenasIguales(tipoDato_local, "T") || mc.sonCadenasIguales(tipoDato_local, "NN")) && mc.obtenerLongitudCadena(dato_local) > campo_local.getFormatoCampo().getLongitudCampo())
        {
          
          dato_local = mc.truncarCadena(dato_local, campo_local.getFormatoCampo().getLongitudCampo());
        }
        if (campo_local.esCampoEnlazado()) {
          dato_local = obtenerValorEnlazadoImportacion(campo_local.getEnlaceCampo().getEnlazado(), mc.convertirAMayusculas(dato_local));
        
        }
        else if ((mc.sonCadenasIguales(tipoDato_local, "E") || mc.sonCadenasIguales(tipoDato_local, "R")) && mc.sonCadenasIguales(dato_local, "")) {
          
          dato_local = String.valueOf(0);
        } 
        
        campo_local.setValorCampo(dato_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      dato_local = null;
      campo_local = null;
      iterador_local = null;
      tipoDato_local = null;
      tabla_local = null;
    } 
  }
  public ListaCampo obtenerCamposArchivoImportacion(GrupoInformacion pGrupoInformacion, String[] pCampos) {
    ListaCampo listaCampo_local = null;
    int contador_local = 0;
    String etiquetaCampo_local = null;
    
    if (pGrupoInformacion == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    if (pCampos == ConstantesGeneral.VALOR_NULO) {
      return listaCampo_local;
    }
    
    try {
      listaCampo_local = new ListaCampo();
      for (contador_local = 0; contador_local < pCampos.length; contador_local++) {
        etiquetaCampo_local = mc.borrarEspaciosLaterales(mc.convertirAMayusculas(pCampos[contador_local]));
        if (!mc.sonCadenasIguales(etiquetaCampo_local, "")) {
          if (listaCampo_local.contarElementos() == 0) {
            listaCampo_local.adicionar(getMotorAplicacion().obtenerCampoLlavePrimariaGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion()));
            
            if (pGrupoInformacion.esGrupoInformacionMultiple()) {
              listaCampo_local.adicionar(getMotorAplicacion().obtenerCampoLlaveForaneaGrupoInformacion(pGrupoInformacion.getIdGrupoInformacion()));
            }
          } 
          
          listaCampo_local.adicionar(getMotorAplicacion().obtenerCampoPorEtiqueta(pGrupoInformacion.getAplicacion().getIdAplicacion(), etiquetaCampo_local));
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return listaCampo_local;
  }
  public void importarArchivo(int pIdAplicacion, ListaCadenas pListaCadenas) {
    int contador_local = -1;
    String lineaArchivo_local = null;
    String[] contenidoLinea_local = null;
    GrupoInformacion grupoInformacion_local = null;
    ListaCampo listaCampo_local = null;
    ListaCampo listaCampoImportar_local = null;
    Iterator iterador_local = null;
    
    if (pListaCadenas == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      iterador_local = pListaCadenas.iterator();
      for (contador_local = 0; iterador_local.hasNext(); contador_local++) {
        lineaArchivo_local = (String)iterador_local.next();
        if (contador_local == 0) {
          lineaArchivo_local = mc.borrarEspaciosLaterales(mc.convertirAMayusculas(lineaArchivo_local));
          grupoInformacion_local = getMotorAplicacion().obtenerGrupoInformacionPorDescripcion(pIdAplicacion, lineaArchivo_local);
        } else {
          contenidoLinea_local = mc.fraccionarCadena(lineaArchivo_local, getSeparador());
          if (contador_local == 1) {
            listaCampo_local = obtenerCamposArchivoImportacion(grupoInformacion_local, contenidoLinea_local);
          } else {
            asignarDatosImportacionListaCampos(listaCampo_local, contenidoLinea_local);
            if (grupoInformacion_local.esGrupoInformacionMultiple()) {
              listaCampoImportar_local = ap.extraerListaCamposImportarGrupoInformacionMultiple(grupoInformacion_local.getIdGrupoInformacion(), listaCampo_local);
            } else {
              
              listaCampoImportar_local = listaCampo_local;
            } 
            getAdministradorBaseDatosAplicacion().incluirRegistroAplicacion(grupoInformacion_local, obtenerValorLlavePrimariaImportacion(grupoInformacion_local, listaCampo_local, contenidoLinea_local), listaCampoImportar_local);
          }
        
        }
      
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      iterador_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorImportacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */