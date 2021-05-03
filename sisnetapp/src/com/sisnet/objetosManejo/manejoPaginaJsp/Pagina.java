package com.sisnet.objetosManejo.manejoPaginaJsp;
import com.sisnet.controlesHTML.Formulario;
public class Pagina
{
  private boolean aIncluirMenuPrincipal;
  private boolean aIncluirBotonSalir;
  private boolean aIncluirBotonAtras;
  private String aNombreUsuario;
  private String aEstadoPagina;
  private String aSubtitulo;
  private int aIdGrupoInformacion;
  private boolean aIncluirOpcionConsulta;
  private Formulario aFormularioPagina;
  private String aEncabezadoPagina;
  private String aTitulo;
  private String aMensaje;
  private String aOpcionesConsulta;
  private String aContenidoDatos;
  private String aContenidoFormulario;
  private String aBotonesPrincipales;
  private String aBotonesSecundarios;
  private String aVersion;
  private boolean aIncluirNumeroVersion;
  private String aInicioCuerpoPagina;
  private String aFinCuerpoPagina;
  public Pagina() {
    setIncluirMenuPrincipal(false);
    setIncluirBotonSalir(false);
    setIncluirBotonAtras(false);
    setNombreUsuario("");
    setEstadoPagina("");
    setSubtitulo("");
    setIdGrupoInformacion(-1);
    setIncluirOpcionConsulta(false);
    setFormularioPagina(null);
    setEncabezadoPagina("");
    setTitulo("");
    setOpcionesConsulta("");
    setMensaje("");
    setContenidoDatos("");
    setContenidoFormulario("");
    setBotonesPrincipales("");
    setBotonesSecundarios("");
    setVersion("");
    setIncluirNumeroVersion(false);
    setInicioCuerpoPagina("");
    setFinCuerpoPagina("");
  }
  public boolean esIncluirMenuPrincipal() {
    return this.aIncluirMenuPrincipal;
  }
  public void setIncluirMenuPrincipal(boolean pIncluirMenuPrincipal) {
    this.aIncluirMenuPrincipal = pIncluirMenuPrincipal;
  }
  public boolean esIncluirBotonSalir() {
    return this.aIncluirBotonSalir;
  }
  public void setIncluirBotonSalir(boolean pIncluirBotonSalir) {
    this.aIncluirBotonSalir = pIncluirBotonSalir;
  }
  public boolean esIncluirBotonAtras() {
    return this.aIncluirBotonAtras;
  }
  public void setIncluirBotonAtras(boolean pIncluirBotonAtras) {
    this.aIncluirBotonAtras = pIncluirBotonAtras;
  }
  public String getNombreUsuario() {
    return this.aNombreUsuario;
  }
  public void setNombreUsuario(String pNombreUsuario) {
    this.aNombreUsuario = pNombreUsuario;
  }
  public String getEstadoPagina() {
    return this.aEstadoPagina;
  }
  public void setEstadoPagina(String pEstadoPagina) {
    this.aEstadoPagina = pEstadoPagina;
  }
  public String getSubtitulo() {
    return this.aSubtitulo;
  }
  public void setSubtitulo(String pSubtitulo) {
    this.aSubtitulo = pSubtitulo;
  }
  public int getIdGrupoInformacion() {
    return this.aIdGrupoInformacion;
  }
  public void setIdGrupoInformacion(int pIdGrupoInformacion) {
    this.aIdGrupoInformacion = pIdGrupoInformacion;
  }
  public boolean esIncluirOpcionConsulta() {
    return this.aIncluirOpcionConsulta;
  }
  public void setIncluirOpcionConsulta(boolean pIncluirOpcionConsulta) {
    this.aIncluirOpcionConsulta = pIncluirOpcionConsulta;
  }
  public Formulario getFormularioPagina() {
    return this.aFormularioPagina;
  }
  public void setFormularioPagina(Formulario pFormularioPagina) {
    this.aFormularioPagina = pFormularioPagina;
  }
  public String getEncabezadoPagina() {
    return this.aEncabezadoPagina;
  }
  public void setEncabezadoPagina(String pEncabezadoPagina) {
    this.aEncabezadoPagina = pEncabezadoPagina;
  }
  public String getTitulo() {
    return this.aTitulo;
  }
  public void setTitulo(String aTitulo) {
    this.aTitulo = aTitulo;
  }
  public String getOpcionesConsulta() {
    return this.aOpcionesConsulta;
  }
  public void setOpcionesConsulta(String pOpcionesConsulta) {
    this.aOpcionesConsulta = pOpcionesConsulta;
  }
  public String getMensaje() {
    return this.aMensaje;
  }
  public void setMensaje(String pMensaje) {
    this.aMensaje = pMensaje;
  }
  public String getContenidoDatos() {
    return this.aContenidoDatos;
  }
  public void setContenidoDatos(String pContenidoDatos) {
    this.aContenidoDatos = pContenidoDatos;
  }
  public String getContenidoFormulario() {
    return this.aContenidoFormulario;
  }
  public void setContenidoFormulario(String pContenidoFormulario) {
    this.aContenidoFormulario = pContenidoFormulario;
  }
  public String getBotonesPrincipales() {
    return this.aBotonesPrincipales;
  }
  public void setBotonesPrincipales(String pBotonesPrincipales) {
    this.aBotonesPrincipales = pBotonesPrincipales;
  }
  public String getBotonesSecundarios() {
    return this.aBotonesSecundarios;
  }
  public void setBotonesSecundarios(String pBotonesSecundarios) {
    this.aBotonesSecundarios = pBotonesSecundarios;
  }
  public String getVersion() {
    return this.aVersion;
  }
  public void setVersion(String pVersion) {
    this.aVersion = pVersion;
  }
  public boolean esIncluirNumeroVersion() {
    return this.aIncluirNumeroVersion;
  }
  public void setIncluirNumeroVersion(boolean pIncluirNumeroVersion) {
    this.aIncluirNumeroVersion = pIncluirNumeroVersion;
  }
  public String getInicioCuerpoPagina() {
    return this.aInicioCuerpoPagina;
  }
  public void setInicioCuerpoPagina(String aInicioCuerpoPagina) {
    this.aInicioCuerpoPagina = aInicioCuerpoPagina;
  }
  public String getFinCuerpoPagina() {
    return this.aFinCuerpoPagina;
  }
  public void setFinCuerpoPagina(String aFinCuerpoPagina) {
    this.aFinCuerpoPagina = aFinCuerpoPagina;
  }
  public String obtenerEncabezadoPagina() {
    String encabezadoPagina_local = "";
    
    return encabezadoPagina_local;
  }
  public String obtenerMensaje() {
    String mensaje_local = "";
    
    return mensaje_local;
  }
  public String obtenerOpcionesConsulta() {
    String opcionesConsulta_local = "";
    
    return opcionesConsulta_local;
  }
  public String obtenerFormulario() {
    String formulario_local = "";
    
    return formulario_local;
  }
  public String obtenerContenidoDatos() {
    String contenidoDatos_local = "";
    
    return contenidoDatos_local;
  }
  public String obtenerNumeroVersion() {
    String _local = "";
    
    return _local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoPaginaJsp\Pagina.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */