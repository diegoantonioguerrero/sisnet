package com.sisnet.baseDatos.sisnet.administrador;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import java.io.Serializable;
public class Aplicacion
  implements Serializable
{
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private int aIdAplicacion;
  private String aNombreAplicacion;
  private String aTituloAplicacion;
  private boolean aEsTabla;
  private int aIdAplicacionConsulta;
  private com.sisnet.baseDatos.sisnet.administrador.Aplicacion aAplicacionConsulta;
  private int aIdAplicacionReporte;
  private com.sisnet.baseDatos.sisnet.administrador.Aplicacion aAplicacionReporte;
  private boolean aActualizarInformacionEnlazada;
  private String aAplicacionesActualizar;
  private String aTipoEventosUsuario;
  private String aEventosAcciones;
  private boolean aAdvertenciaEjecucion;
  private boolean aPermitirConsultaGeneral;
  private int aTamanoMaximoArchivos;
  private boolean aEsOculta;
  private boolean aHacerDobleCalculo;
  private int aNumeroRegistrosPagina;
  private int aIdAplicacionImpresionMasiva;
  private com.sisnet.baseDatos.sisnet.administrador.Aplicacion aAplicacionImpresionMasiva;
  public Aplicacion() {
    setIdAplicacion(-1);
    setNombreAplicacion("");
    setTituloAplicacion("");
    setEsTabla(false);
    setIdAplicacionConsulta(-1);
    setAplicacionConsulta(null);
    setIdAplicacionReporte(-1);
    setAplicacionReporte(null);
    setActualizarInformacionEnlazada(false);
    setAplicacionesActualizar("");
    setAdvertenciaEjecucion(false);
    setPermitirConsultaGeneral(false);
    setTipoEventosUsuario("");
    setEventosAcciones("");
    setTamanoMaximoArchivos(0);
    setEsOculta(false);
    setHacerDobleCalculo(false);
    setNumeroRegistrosPagina(0);
    setIdAplicacionImpresionMasiva(-1);
    setAplicacionImpresionMasiva(null);
  }
  public int getIdAplicacion() {
    return this.aIdAplicacion;
  }
  public void setIdAplicacion(int pIdAplicacion) {
    this.aIdAplicacion = pIdAplicacion;
  }
  public String getNombreAplicacion() {
    return this.aNombreAplicacion;
  }
  public void setNombreAplicacion(String pNombreAplicacion) {
    this.aNombreAplicacion = mc.convertirAMayusculas(pNombreAplicacion);
  }
  public String getTituloAplicacion() {
    return this.aTituloAplicacion;
  }
  public void setTituloAplicacion(String pTituloAplicacion) {
    this.aTituloAplicacion = mc.convertirAMayusculas(pTituloAplicacion);
  }
  public boolean esTabla() {
    return this.aEsTabla;
  }
  public void setEsTabla(boolean pEsTabla) {
    this.aEsTabla = pEsTabla;
  }
  public int getIdAplicacionConsulta() {
    return this.aIdAplicacionConsulta;
  }
  public void setIdAplicacionConsulta(int pIdAplicacionConsulta) {
    this.aIdAplicacionConsulta = pIdAplicacionConsulta;
  }
  public com.sisnet.baseDatos.sisnet.administrador.Aplicacion getAplicacionConsulta() {
    return this.aAplicacionConsulta;
  }
  public void setAplicacionConsulta(com.sisnet.baseDatos.sisnet.administrador.Aplicacion pAplicacionConsulta) {
    this.aAplicacionConsulta = pAplicacionConsulta;
  }
  public int getIdAplicacionReporte() {
    return this.aIdAplicacionReporte;
  }
  public void setIdAplicacionReporte(int pIdAplicacionReporte) {
    this.aIdAplicacionReporte = pIdAplicacionReporte;
  }
  public com.sisnet.baseDatos.sisnet.administrador.Aplicacion getAplicacionReporte() {
    return this.aAplicacionReporte;
  }
  public void setAplicacionReporte(com.sisnet.baseDatos.sisnet.administrador.Aplicacion pAplicacionReporte) {
    this.aAplicacionReporte = pAplicacionReporte;
  }
  public boolean esActualizarInformacionEnlazada() {
    return this.aActualizarInformacionEnlazada;
  }
  public void setActualizarInformacionEnlazada(boolean aActualizarInformacionEnlazada) {
    this.aActualizarInformacionEnlazada = aActualizarInformacionEnlazada;
  }
  public String getAplicacionesActualizar() {
    return this.aAplicacionesActualizar;
  }
  public void setAplicacionesActualizar(String aAplicacionesActualizar) {
    this.aAplicacionesActualizar = aAplicacionesActualizar;
  }
  public String getTipoEventosUsuario() {
    return this.aTipoEventosUsuario;
  }
  public void setTipoEventosUsuario(String pTipoEventosUsuario) {
    this.aTipoEventosUsuario = pTipoEventosUsuario;
  }
  public String getEventosAcciones() {
    return this.aEventosAcciones;
  }
  public void setEventosAcciones(String pEventosAcciones) {
    this.aEventosAcciones = pEventosAcciones;
  }
  public boolean esAdvertenciaEjecucion() {
    return this.aAdvertenciaEjecucion;
  }
  public void setAdvertenciaEjecucion(boolean aAdvertenciaEjecucion) {
    this.aAdvertenciaEjecucion = aAdvertenciaEjecucion;
  }
  public boolean esPermitirConsultaGeneral() {
    return this.aPermitirConsultaGeneral;
  }
  public void setPermitirConsultaGeneral(boolean pPermitirConsultaGeneral) {
    this.aPermitirConsultaGeneral = pPermitirConsultaGeneral;
  }
  public int getTamanoMaximoArchivos() {
    return this.aTamanoMaximoArchivos;
  }
  public void setTamanoMaximoArchivos(int pTamanoMaximoArchivos) {
    this.aTamanoMaximoArchivos = pTamanoMaximoArchivos;
  }
  public boolean esOculta() {
    return this.aEsOculta;
  }
  public void setEsOculta(boolean pEsOculta) {
    this.aEsOculta = pEsOculta;
  }
  public boolean esHacerDobleCalculo() {
    return this.aHacerDobleCalculo;
  }
  public void setHacerDobleCalculo(boolean pHacerDobleCalculo) {
    this.aHacerDobleCalculo = pHacerDobleCalculo;
  }
  public int getNumeroRegistrosPagina() {
    return this.aNumeroRegistrosPagina;
  }
  public void setNumeroRegistrosPagina(int pNumeroRegistrosPagina) {
    this.aNumeroRegistrosPagina = pNumeroRegistrosPagina;
  }
  public int getIdAplicacionImpresionMasiva() {
    return this.aIdAplicacionImpresionMasiva;
  }
  public void setIdAplicacionImpresionMasiva(int pIdAplicacionImpresionMasiva) {
    this.aIdAplicacionImpresionMasiva = pIdAplicacionImpresionMasiva;
  }
  public com.sisnet.baseDatos.sisnet.administrador.Aplicacion getAplicacionImpresionMasiva() {
    return this.aAplicacionImpresionMasiva;
  }
  public void setAplicacionImpresionMasiva(com.sisnet.baseDatos.sisnet.administrador.Aplicacion pAplicacionImpresionMasiva) {
    this.aAplicacionImpresionMasiva = pAplicacionImpresionMasiva;
  }
  
  @Override
  public boolean equals(Object o) {
	  // If the object is compared with itself then return true 
      if (o == this) {
          return true;
      }

      /* Check if o is an instance of Complex or not
        "null instanceof [type]" also returns false */
      if (!(o instanceof Aplicacion)) {
          return false;
      }
       
      // typecast o to Complex so that we can compare data members
      Aplicacion c = (Aplicacion) o;
       
      // Compare the data members and return accordingly
      return this.getIdAplicacion() == c.getIdAplicacion();
	  
  }
  @Override
  public String toString() {
	  
	  return "[" + this.aIdAplicacion + "]" + this.aNombreAplicacion;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\baseDatos\sisnet\administrador\Aplicacion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */