package com.sisnet.objetosManejo.manejoPaginaJsp;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.aplicacion.manejadores.ManejadorCadenasHTML;
import com.sisnet.objetosManejo.listas.objetosBaseDatos.ListaCampo;
public class Formulario
{
  private static ManejadorCadenasHTML mcHTML = ManejadorCadenasHTML.getManejadorCadenasHTML();
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aId;
  private String aNombre;
  private String aAction;
  private String aMethod;
  private String aEventoOnSubmit;
  private ListaCampo aListaCampo;
  private String aContenido;
  private boolean aEsMultipart;
  public Formulario() {
    setId("");
    setNombre("");
    setAction("");
    setMethod("post");
    setEventoOnSubmit("");
    setListaCampo(null);
    setContenido("");
    setEsMultipart(false);
  }
  public String getId() {
    return this.aId;
  }
  public void setId(String pId) {
    this.aId = pId;
  }
  public String getNombre() {
    return this.aNombre;
  }
  public void setNombre(String pNombre) {
    this.aNombre = pNombre;
  }
  public String getAction() {
    return this.aAction;
  }
  public void setAction(String pAction) {
    this.aAction = pAction;
  }
  public String getMethod() {
    return this.aMethod;
  }
  public void setMethod(String pMethod) {
    this.aMethod = pMethod;
  }
  public String getEventoOnSubmit() {
    return this.aEventoOnSubmit;
  }
  public void setEventoOnSubmit(String pEventoOnSubmit) {
    this.aEventoOnSubmit = pEventoOnSubmit;
  }
  public ListaCampo getListaCampo() {
    return this.aListaCampo;
  }
  public void setListaCampo(ListaCampo pListaCampo) {
    this.aListaCampo = pListaCampo;
  }
  public String getContenido() {
    return this.aContenido;
  }
  public void setContenido(String pContenido) {
    this.aContenido = pContenido;
  }
  public boolean esMultipart() {
    return this.aEsMultipart;
  }
  public void setEsMultipart(boolean pEsMultipart) {
    this.aEsMultipart = pEsMultipart;
  }
  public String dibujar() {
    String formulario_local = "";
    
    try {
      setId(getNombre());
      formulario_local = mc.concatenarCadena(" <form ", mcHTML.conformarAtributoHTML(" id=\"", getId()));
      
      formulario_local = mc.concatenarCadena(formulario_local, mcHTML.conformarAtributoHTML(" name=\"", getNombre()));
      
      formulario_local = mc.concatenarCadena(formulario_local, mcHTML.conformarAtributoHTML(" action=\"", getAction()));
      
      formulario_local = mc.concatenarCadena(formulario_local, mcHTML.conformarAtributoHTML(" method=\"", getMethod()));
      
      formulario_local = mc.concatenarCadena(formulario_local, mcHTML.conformarAtributoHTML(" onSubmit=\"", getEventoOnSubmit()));
      
      if (esMultipart()) {
        formulario_local = mc.concatenarCadena(formulario_local, mcHTML.conformarAtributoHTML(" enctype=\"", "multipart/form-data"));
      }
      
      formulario_local = mc.concatenarCadena(formulario_local, ">\n");
      formulario_local = mc.concatenarCadena(formulario_local, getContenido());
      formulario_local = mc.concatenarCadena(formulario_local, " </form>\n");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return formulario_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\Formulario.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */