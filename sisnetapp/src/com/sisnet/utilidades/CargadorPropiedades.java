package com.sisnet.utilidades;
import com.sisnet.constantes.ConstantesGeneral;
import java.util.Properties;
public class CargadorPropiedades
{
  private static com.sisnet.utilidades.CargadorPropiedades cargadorPropiedadesSingleton = null;
  private static final String ARCHIVO_CONFIGURACION_SISNET = "sisnet.properties";
  private static final String ARCHIVO_CONFIGURACION_IMAGENES = "imagenes.properties";
  private Properties propertiesSisnet;
  private Properties propertiesImagenes;
  private CargadorPropiedades() {
    setPropertiesSisnet(cargarPropiedadesArchivo("sisnet.properties"));
    setPropertiesImagenes(cargarPropiedadesArchivo("imagenes.properties"));
  }
  public static com.sisnet.utilidades.CargadorPropiedades getCargadorPropiedades() {
    if (cargadorPropiedadesSingleton == null) {
      cargadorPropiedadesSingleton = new com.sisnet.utilidades.CargadorPropiedades();
    }
    return cargadorPropiedadesSingleton;
  }
  public Properties getPropertiesSisnet() {
    return this.propertiesSisnet;
  }
  public void setPropertiesSisnet(Properties pPropertiesSisnet) {
    this.propertiesSisnet = pPropertiesSisnet;
  }
  public Properties getPropertiesImagenes() {
    return this.propertiesImagenes;
  }
  public void setPropertiesImagenes(Properties pPropertiesImagenes) {
    this.propertiesImagenes = pPropertiesImagenes;
  }
  private Properties cargarPropiedadesArchivo(String pNombreArchivo) {
    Properties properties_local = null;
    
    if (pNombreArchivo == ConstantesGeneral.VALOR_NULO) {
      return properties_local;
    }
    
    try {
      properties_local = new Properties();
      properties_local.load(com.sisnet.utilidades.CargadorPropiedades.class.getResourceAsStream(pNombreArchivo));
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
    
    return properties_local;
  }
  public String obtenerValorPropiedadSisnet(String pNombrePropiedad) throws Exception {
    String valor_local = "";
    
    if (getPropertiesSisnet() == ConstantesGeneral.VALOR_NULO) {
      return valor_local;
    }
    
    try {
      valor_local = getPropertiesSisnet().getProperty(pNombrePropiedad);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return valor_local;
  }
  public String obtenerValorPropiedadImagenes(String pNombrePropiedad) throws Exception {
    String valor_local = "";
    
    if (getPropertiesImagenes() == ConstantesGeneral.VALOR_NULO) {
      return valor_local;
    }
    
    try {
      valor_local = getPropertiesImagenes().getProperty(pNombrePropiedad);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } 
    
    return valor_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisne\\utilidades\CargadorPropiedades.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */