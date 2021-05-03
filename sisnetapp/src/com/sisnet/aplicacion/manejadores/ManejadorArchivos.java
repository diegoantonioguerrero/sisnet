package com.sisnet.aplicacion.manejadores;
import com.sisnet.baseDatos.ConexionPostgres;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.ListaCadenas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
public class ManejadorArchivos
{
  public ListaCadenas leerArchivo(String pRutaArchivo) {
    ListaCadenas listaCadenas_local = null;
    String linea_local = null;
    File file_local = null;
    BufferedReader bufferedReader_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return listaCadenas_local;
    }
    try {
      listaCadenas_local = new ListaCadenas();
      if (existeArchivo(pRutaArchivo)) {
        file_local = new File(pRutaArchivo);
        bufferedReader_local = new BufferedReader(new FileReader(file_local));
        while ((linea_local = bufferedReader_local.readLine()) != null) {
          listaCadenas_local.adicionar(linea_local);
        }
        bufferedReader_local.close();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      file_local = null;
      linea_local = null;
      bufferedReader_local = null;
    } 
    return listaCadenas_local;
  }
  public void guardarArchivo(String pRutaArchivo, String pDatosArchivo, boolean pModificarArchivo) {
    FileWriter fileWriter_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pDatosArchivo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      fileWriter_local = new FileWriter(pRutaArchivo, pModificarArchivo);
      fileWriter_local.write(pDatosArchivo);
      fileWriter_local.close();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fileWriter_local = null;
    } 
  }
  public boolean borrarArchivo(String pRutaArchivo) {
    boolean archivo_local = false;
    File file_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return archivo_local;
    }
    
    try {
      if (existeArchivo(pRutaArchivo)) {
        file_local = new File(pRutaArchivo);
        archivo_local = file_local.delete();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      file_local = null;
    } 
    
    return archivo_local;
  }
  public boolean existeArchivo(String pRutaArchivo) {
    boolean existeArchivo_local = false;
    File file_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return existeArchivo_local;
    }
    
    try {
      file_local = new File(pRutaArchivo);
      existeArchivo_local = file_local.exists();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      file_local = null;
    } 
    
    return existeArchivo_local;
  }
  public void crearDirectorio(String pNombreDirectorio) {
    File file_local = null;
    
    if (pNombreDirectorio == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      file_local = new File(pNombreDirectorio);
      file_local.mkdir();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      file_local = null;
    } 
  }
  public void guardarObjetoArchivo(String pRuta, Object pObjeto) {
    ObjectOutputStream objectOutputStream_local = null;
    FileOutputStream fileOutputStream_local = null;
    
    if (pRuta == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pObjeto == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      fileOutputStream_local = new FileOutputStream(pRuta);
      objectOutputStream_local = new ObjectOutputStream(fileOutputStream_local);
      objectOutputStream_local.writeObject(pObjeto);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fileOutputStream_local = null;
      objectOutputStream_local = null;
    } 
  }
  public Object recuperarObjetoArchivo(String pRuta) {
    Object objeto_local = null;
    ObjectInputStream objectInputStream_local = null;
    FileInputStream fileInputStream_local = null;
    
    if (pRuta == ConstantesGeneral.VALOR_NULO) {
      return objeto_local;
    }
    
    try {
      if (existeArchivo(pRuta)) {
        fileInputStream_local = new FileInputStream(pRuta);
        objectInputStream_local = new ObjectInputStream(fileInputStream_local);
        objeto_local = objectInputStream_local.readObject();
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fileInputStream_local = null;
      objectInputStream_local = null;
    } 
    
    return objeto_local;
  }
  public void modificarArchivoConexionPostgres(String pRutaArchivo, ConexionPostgres pConexionPostgres) {
    ConexionPostgres conexionPostgres_local = null;
    
    if (pRutaArchivo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pConexionPostgres == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      conexionPostgres_local = (ConexionPostgres)recuperarObjetoArchivo(pRutaArchivo);
      if (conexionPostgres_local != ConstantesGeneral.VALOR_NULO) {
        conexionPostgres_local.setSuperUsuario(pConexionPostgres.getSuperUsuario());
        conexionPostgres_local.setContrasenaSuperUsuario(pConexionPostgres.getContrasenaSuperUsuario());
        conexionPostgres_local.setNumeroPuertoConexion(pConexionPostgres.getNumeroPuertoConexion());
        guardarObjetoArchivo(pRutaArchivo, conexionPostgres_local);
      } 
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void copiaArchivos(String pNombreOrigen, String pNombreDestino) {
    FileInputStream FileInputStream_local = null;
    FileOutputStream FileOutputStream_local = null;
    FileChannel canalOrigen = null;
    FileChannel canalDestino = null;
    
    if (pNombreOrigen == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombreDestino == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      FileInputStream_local = new FileInputStream(pNombreOrigen);
      FileOutputStream_local = new FileOutputStream(pNombreDestino);
      canalOrigen = FileInputStream_local.getChannel();
      canalDestino = FileOutputStream_local.getChannel();
      canalOrigen.transferTo(0L, canalOrigen.size(), canalDestino);
      FileInputStream_local.close();
      FileOutputStream_local.close();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      FileInputStream_local = null;
      FileOutputStream_local = null;
      canalDestino = null;
      canalOrigen = null;
    } 
  }
  public void borrarContenidoDirectorio(File pDirectorio) {
    File[] archivos_local = null;
    int i_local = -1;
    
    if (pDirectorio == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      archivos_local = pDirectorio.listFiles();
      for (i_local = 0; i_local < archivos_local.length; i_local++) {
        if (archivos_local[i_local].isDirectory()) {
          borrarContenidoDirectorio(archivos_local[i_local]);
        }
        archivos_local[i_local].delete();
      }
    
    } catch (Exception excepcion_local) {
      excepcion_local.printStackTrace();
    } finally {
      archivos_local = null;
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\aplicacion\manejadores\ManejadorArchivos.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */