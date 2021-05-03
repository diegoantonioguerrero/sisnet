package com.sisnet.aplicacion.manejadores;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Random;
public class ManejadorOperaciones
{
  private static com.sisnet.aplicacion.manejadores.ManejadorOperaciones operacionesSingleton = null;
  private static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  public static com.sisnet.aplicacion.manejadores.ManejadorOperaciones getManejadorOperaciones() {
    if (operacionesSingleton == ConstantesGeneral.VALOR_NULO) {
      operacionesSingleton = new com.sisnet.aplicacion.manejadores.ManejadorOperaciones();
    }
    
    return operacionesSingleton;
  }
  public int sumar(int pNumeroEnteroUno, int pNumeroEnteroDos) {
    int suma_local = 0;
    
    try {
      suma_local = pNumeroEnteroUno + pNumeroEnteroDos;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return suma_local;
  }
  public BigDecimal sumar(int pNumeroEntero, double pNumeroReal) {
    BigDecimal suma_local = null;
    BigDecimal numeroEntero_local = null;
    BigDecimal numeroReal_local = null;
    
    try {
      numeroEntero_local = new BigDecimal(pNumeroEntero);
      numeroReal_local = new BigDecimal(pNumeroReal);
      suma_local = numeroEntero_local.add(numeroReal_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroReal_local = null;
      numeroEntero_local = null;
    } 
    
    return suma_local;
  }
  public BigDecimal sumar(double pNumeroRealUno, double pNumeroRealDos) {
    BigDecimal suma_local = null;
    BigDecimal numeroRealUno_local = null;
    BigDecimal numeroRealDos_local = null;
    
    try {
      numeroRealUno_local = new BigDecimal(pNumeroRealUno);
      numeroRealDos_local = new BigDecimal(pNumeroRealDos);
      suma_local = numeroRealUno_local.add(numeroRealDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroRealUno_local = null;
      numeroRealDos_local = null;
    } 
    
    return suma_local;
  }
  public BigDecimal sumar(double pNumeroReal, int pNumeroEntero) {
    BigDecimal suma_local = null;
    BigDecimal numeroReal_local = null;
    BigDecimal numeroEntero_local = null;
    
    try {
      numeroReal_local = new BigDecimal(pNumeroReal);
      numeroEntero_local = new BigDecimal(pNumeroEntero);
      suma_local = numeroReal_local.add(numeroEntero_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroReal_local = null;
      numeroEntero_local = null;
    } 
    
    return suma_local;
  }
  public String concatenar(String pCadenaUno, String pCadenaDos, String pUnion, int pLongitudMaxima) {
    String concatenacion_local = "";
    
    if (pCadenaUno == ConstantesGeneral.VALOR_NULO) {
      return concatenacion_local;
    }
    if (pCadenaDos == ConstantesGeneral.VALOR_NULO) {
      return concatenacion_local;
    }
    
    try {
      if (!mc.esCadenaVacia(pUnion) && (mc.esCadenaVacia(pCadenaUno) || mc.esCadenaVacia(pCadenaDos))) {
        pUnion = "";
      }
      concatenacion_local = mc.concatenarCadena(pCadenaUno + pUnion, pCadenaDos);
      if (mc.obtenerLongitudCadena(concatenacion_local) > pLongitudMaxima) {
        concatenacion_local = mc.obtenerSubCadena(concatenacion_local, 0, pLongitudMaxima);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return concatenacion_local;
  }
  public BigDecimal restar(int pNumeroEnteroUno, int pNumeroEnteroDos) {
    BigDecimal resta_local = null;
    
    try {
      resta_local = new BigDecimal(pNumeroEnteroUno - pNumeroEnteroDos);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return resta_local;
  }
  public BigDecimal restar(int pNumeroEntero, double pNumeroReal) {
    BigDecimal resta_local = null;
    BigDecimal numeroEntero_local = null;
    BigDecimal numeroReal_local = null;
    
    try {
      numeroEntero_local = new BigDecimal(pNumeroEntero);
      numeroReal_local = new BigDecimal(pNumeroReal);
      resta_local = numeroEntero_local.subtract(numeroReal_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroReal_local = null;
      numeroEntero_local = null;
    } 
    
    return resta_local;
  }
  public BigDecimal restar(double pNumeroRealUno, double pNumeroRealDos) {
    BigDecimal resta_local = null;
    BigDecimal numeroRealUno_local = null;
    BigDecimal numeroRealDos_local = null;
    
    try {
      numeroRealUno_local = new BigDecimal(pNumeroRealUno);
      numeroRealDos_local = new BigDecimal(pNumeroRealDos);
      resta_local = numeroRealUno_local.subtract(numeroRealDos_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroRealUno_local = null;
      numeroRealDos_local = null;
    } 
    
    return resta_local;
  }
  public BigDecimal restar(double pNumeroReal, int pNumeroEntero) {
    BigDecimal resta_local = null;
    BigDecimal numeroReal_local = null;
    BigDecimal numeroEntero_local = null;
    
    try {
      numeroReal_local = new BigDecimal(pNumeroReal);
      numeroEntero_local = new BigDecimal(pNumeroEntero);
      resta_local = numeroReal_local.subtract(numeroEntero_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      numeroReal_local = null;
      numeroEntero_local = null;
    } 
    
    return resta_local;
  }
  public BigDecimal multiplicar(int pNumeroEnteroUno, int pNumeroEnteroDos) {
    BigDecimal multiplicacion_local = null;
    
    try {
      multiplicacion_local = new BigDecimal(pNumeroEnteroUno * pNumeroEnteroDos);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return multiplicacion_local;
  }
  public BigDecimal multiplicar(int pNumeroEntero, double pNumeroReal) {
    BigDecimal multiplicacion_local = null;
    
    try {
      multiplicacion_local = new BigDecimal(pNumeroEntero * pNumeroReal);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return multiplicacion_local;
  }
  public BigDecimal multiplicar(double pNumeroRealUno, double pNumeroRealDos) {
    BigDecimal multiplicacion_local = null;
    
    try {
      multiplicacion_local = new BigDecimal(pNumeroRealUno * pNumeroRealDos);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return multiplicacion_local;
  }
  public BigDecimal multiplicar(double pNumeroReal, int pNumeroEntero) {
    BigDecimal multiplicacion_local = null;
    
    try {
      multiplicacion_local = new BigDecimal(pNumeroReal * pNumeroEntero);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return multiplicacion_local;
  }
  public BigDecimal dividir(int pNumeroEnteroUno, int pNumeroEnteroDos) {
    BigDecimal division_local = null;
    
    if (pNumeroEnteroDos == 0) {
      return division_local;
    }
    
    try {
      division_local = new BigDecimal(pNumeroEnteroUno / pNumeroEnteroDos);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return division_local;
  }
  public BigDecimal dividir(int pNumeroEntero, double pNumeroReal) {
    BigDecimal division_local = null;
    
    if (pNumeroReal == 0.0D) {
      return division_local;
    }
    
    try {
      division_local = new BigDecimal(pNumeroEntero / pNumeroReal);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return division_local;
  }
  public BigDecimal dividir(double pNumeroRealUno, double pNumeroRealDos) {
    BigDecimal division_local = null;
    
    try {
      if (pNumeroRealDos == 0.0D) {
        return new BigDecimal(0);
      }
      division_local = new BigDecimal(pNumeroRealUno / pNumeroRealDos);
      if (division_local == ConstantesGeneral.VALOR_NULO) {
        division_local = new BigDecimal(0);
      }
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return division_local;
  }
  public BigDecimal dividir(double pNumeroReal, int pNumeroEntero) {
    BigDecimal division_local = null;
    
    if (pNumeroEntero == 0) {
      return division_local;
    }
    
    try {
      division_local = new BigDecimal(pNumeroReal / pNumeroEntero);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return division_local;
  }
  public BigDecimal redondearNumero(BigDecimal pNumero, int pCifrasDecimales) {
    BigDecimal numero_local = null;
    
    if (pNumero == ConstantesGeneral.VALOR_NULO) {
      return numero_local;
    }
    
    try {
      numero_local = pNumero.setScale(pCifrasDecimales, 4);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public BigDecimal redondearNumeroHaciaAbajo(BigDecimal pNumero, int pCifrasDecimales) {
    BigDecimal numero_local = null;
    
    if (pNumero == ConstantesGeneral.VALOR_NULO) {
      return numero_local;
    }
    
    try {
      numero_local = pNumero.setScale(pCifrasDecimales, 1);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public BigDecimal obtenerValorAbsoluto(BigDecimal pNumero) {
    BigDecimal numero_local = null;
    
    if (pNumero == ConstantesGeneral.VALOR_NULO) {
      return numero_local;
    }
    
    try {
      numero_local = pNumero.abs();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public int obtenerValorAbsoluto(int pNumero) {
    int numero_local = 0;
    
    try {
      numero_local = Math.abs(pNumero);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public BigDecimal sumarUnidad(BigDecimal pNumero) {
    BigDecimal numero_local = null;
    
    if (pNumero == ConstantesGeneral.VALOR_NULO) {
      return numero_local;
    }
    
    try {
      numero_local = pNumero.add(BigDecimal.valueOf(1L));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public int sumarUnidad(int pNumero) {
    int numero_local = 0;
    
    try {
      numero_local = pNumero;
      numero_local++;
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return numero_local;
  }
  public int obtenerNumeroInternoHora(String pHora) {
    int numeroInternoHora_local = 0;
    Time time_local = null;
    
    if (pHora == ConstantesGeneral.VALOR_NULO) {
      return numeroInternoHora_local;
    }
    
    try {
      if (mc.verificarFormatoHora(pHora)) {
        time_local = Time.valueOf(pHora);
        numeroInternoHora_local = obtenerValorAbsoluto(time_local.hashCode() / 1000);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      time_local = null;
    } 
    
    return numeroInternoHora_local;
  }
  public BigDecimal elevarA(int pNumeroEnteroUno, int pNumeroEnteroDos) {
    BigDecimal elevadoA_local = null;
    
    try {
      elevadoA_local = new BigDecimal(pNumeroEnteroUno);
      elevadoA_local = elevadoA_local.pow(pNumeroEnteroDos);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return elevadoA_local;
  }
  public BigDecimal elevarA(int pNumeroEntero, double pNumeroReal) {
    BigDecimal elevarA_local = null;
    
    try {
      elevarA_local = new BigDecimal(pNumeroEntero);
      elevarA_local = new BigDecimal(Math.pow(elevarA_local.doubleValue(), pNumeroReal));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return elevarA_local;
  }
  public BigDecimal elevarA(double pNumeroRealUno, double pNumeroRealDos) {
    BigDecimal division_local = null;
    
    try {
      division_local = new BigDecimal(Math.pow(pNumeroRealUno, pNumeroRealDos));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return division_local;
  }
  public BigDecimal elevarA(double pNumeroReal, int pNumeroEntero) {
    BigDecimal division_local = null;
    
    try {
      division_local = new BigDecimal(pNumeroReal);
      division_local = division_local.pow(pNumeroEntero);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    
    return division_local;
  }
  public int generarNumeroAleatorio() {
    int numeroAleatorio_local = 0;
    Random random_local = null;
    
    try {
      random_local = new Random();
      numeroAleatorio_local = random_local.nextInt(100000);
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      random_local = null;
    } 
    
    return numeroAleatorio_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorOperaciones.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */