package com.sisnet.objetosManejo.manejoReportes;
import com.sisnet.aplicacion.manejadores.ManejadorCadenas;
import com.sisnet.constantes.ConstantesGeneral;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
public class GeneradorArchivoExcel
{
  protected static ManejadorCadenas mc = ManejadorCadenas.getManejadorCadenas();
  private String aNombreArchivo;
  private String aRutaArchivo;
  private HSSFWorkbook aLibro;
  private HSSFCellStyle aEstiloCeldaNumeroEntero;
  private HSSFCellStyle aEstiloCeldaNumeroReal;
  private HSSFCellStyle aEstiloCeldaTexto;
  public GeneradorArchivoExcel(String pNombreArchivo, String pRutaArchivo) {
    setNombreArchivo(pNombreArchivo);
    setRutaArchivo(pRutaArchivo);
    setLibro(new HSSFWorkbook());
    setEstiloCeldaNumeroEntero(getLibro().createCellStyle());
    setEstiloCeldaNumeroReal(getLibro().createCellStyle());
    setEstiloCeldaTexto(getLibro().createCellStyle());
  }
  public String getNombreArchivo() {
    return this.aNombreArchivo;
  }
  public void setNombreArchivo(String pNombreArchivo) {
    this.aNombreArchivo = pNombreArchivo;
  }
  public String getRutaArchivo() {
    return this.aRutaArchivo;
  }
  public void setRutaArchivo(String pRutaArchivo) {
    this.aRutaArchivo = pRutaArchivo;
  }
  public HSSFWorkbook getLibro() {
    return this.aLibro;
  }
  public void setLibro(HSSFWorkbook pLibro) {
    this.aLibro = pLibro;
  }
  public HSSFCellStyle getEstiloCeldaNumeroEntero() {
    return this.aEstiloCeldaNumeroEntero;
  }
  public void setEstiloCeldaNumeroEntero(HSSFCellStyle pEstiloCeldaNumeroEntero) {
    this.aEstiloCeldaNumeroEntero = pEstiloCeldaNumeroEntero;
    asignarAlineacionEstiloCelda(this.aEstiloCeldaNumeroEntero, 3);
    asignarFormatoEstiloCelda(this.aEstiloCeldaNumeroEntero, "#,##0");
  }
  public HSSFCellStyle getEstiloCeldaNumeroReal() {
    return this.aEstiloCeldaNumeroReal;
  }
  public void setEstiloCeldaNumeroReal(HSSFCellStyle pEstiloCeldaNumeroReal) {
    this.aEstiloCeldaNumeroReal = pEstiloCeldaNumeroReal;
    asignarAlineacionEstiloCelda(this.aEstiloCeldaNumeroReal, 3);
    asignarFormatoEstiloCelda(this.aEstiloCeldaNumeroReal, "#,##0.00");
  }
  public HSSFCellStyle getEstiloCeldaTexto() {
    return this.aEstiloCeldaTexto;
  }
  public void setEstiloCeldaTexto(HSSFCellStyle pEstiloCeldaTexto) {
    this.aEstiloCeldaTexto = pEstiloCeldaTexto;
    asignarAlineacionEstiloCelda(this.aEstiloCeldaTexto, 1);
  }
  public HSSFSheet crearHoja(String pNombreHoja) {
    HSSFSheet hoja_local = null;
    
    if (pNombreHoja == ConstantesGeneral.VALOR_NULO) {
      return hoja_local;
    }
    
    try {
      hoja_local = getLibro().createSheet(pNombreHoja);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return hoja_local;
  }
  public HSSFRow crearFila(HSSFSheet pHoja, int pNumeroFila) {
    HSSFRow fila_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return fila_local;
    }
    
    try {
      fila_local = pHoja.createRow(pNumeroFila);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fila_local;
  }
  private HSSFCell crearCelda(HSSFRow pFila, int pNumeroColumna) {
    HSSFCell celda_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = pFila.createCell((short)pNumeroColumna);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  private Region crearRegion(int pNumeroFilaInicial, int pNumeroColumnaInicial, int pNumeroFilaFinal, int pNumeroColumnaFinal) {
    Region region_local = null;
    
    try {
      region_local = new Region(pNumeroFilaInicial, (short)pNumeroColumnaInicial, pNumeroFilaFinal, (short)pNumeroColumnaFinal);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return region_local;
  }
  private void adicionarRegionCombinada(HSSFSheet pHoja, Region pRegion) {
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pRegion == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pHoja.addMergedRegion(pRegion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public HSSFCell crearCeldaCombinada(HSSFSheet pHoja, HSSFRow pFilaCombinada, int pNumeroCeldaInicial, int pCantidadCeldasCombinadas, int pBorde) {
    HSSFCell celdaCombinada_local = null;
    int numeroFila_local = -1;
    int numeroCeldaFinal_local = -1;
    int numeroCelda_local = -1;
    Region region_local = null;
    HSSFCellStyle estiloCeldaCombinada_local = null;
    HSSFCellStyle estiloCeldaIntermedia_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return celdaCombinada_local;
    }
    if (pFilaCombinada == ConstantesGeneral.VALOR_NULO) {
      return celdaCombinada_local;
    }
    
    try {
      numeroFila_local = obtenerNumeroFila(pFilaCombinada);
      numeroCeldaFinal_local = pNumeroCeldaInicial + pCantidadCeldasCombinadas - 1;
      region_local = crearRegion(numeroFila_local, pNumeroCeldaInicial, numeroFila_local, numeroCeldaFinal_local);
      adicionarRegionCombinada(pHoja, region_local);
      
      estiloCeldaCombinada_local = crearEstiloCelda();
      asignarBordeInferiorEstiloCelda(estiloCeldaCombinada_local, pBorde);
      asignarBordeIzquierdoEstiloCelda(estiloCeldaCombinada_local, pBorde);
      asignarBordeDerechoEstiloCelda(estiloCeldaCombinada_local, pBorde);
      asignarBordeSuperiorEstiloCelda(estiloCeldaCombinada_local, pBorde);
      asignarAlineacionEstiloCelda(estiloCeldaCombinada_local, 2);
      
      estiloCeldaIntermedia_local = crearEstiloCelda();
      asignarBordeInferiorEstiloCelda(estiloCeldaIntermedia_local, pBorde);
      asignarBordeSuperiorEstiloCelda(estiloCeldaIntermedia_local, pBorde);
      
      for (numeroCelda_local = pNumeroCeldaInicial; numeroCelda_local <= numeroCeldaFinal_local; numeroCelda_local++) {
        crearCelda(pFilaCombinada, numeroCelda_local);
        asignarEstiloCelda(obtenerCelda(pFilaCombinada, numeroCelda_local), estiloCeldaIntermedia_local);
      } 
      
      celdaCombinada_local = obtenerCelda(pFilaCombinada, pNumeroCeldaInicial);
      asignarEstiloCelda(celdaCombinada_local, estiloCeldaCombinada_local);
      asignarEstiloCelda(obtenerCelda(pFilaCombinada, numeroCeldaFinal_local), estiloCeldaCombinada_local);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      region_local = null;
      estiloCeldaCombinada_local = null;
      estiloCeldaIntermedia_local = null;
    } 
    return celdaCombinada_local;
  }
  public HSSFCell crearCeldaEncabezado(HSSFRow pFila, int pNumeroColumna) {
    HSSFCell celdaEncabezado_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celdaEncabezado_local;
    }
    
    try {
      celdaEncabezado_local = crearCelda(pFila, pNumeroColumna);
      asignarEstiloNegrita(celdaEncabezado_local, 10);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaEncabezado_local;
  }
  public HSSFCell crearCeldaNumeroEntero(HSSFRow pFila, int pNumeroColumna, int pValorCelda) {
    HSSFCell celdaNumerica_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celdaNumerica_local;
    }
    
    try {
      celdaNumerica_local = crearCelda(pFila, pNumeroColumna);
      asignarEstiloCelda(celdaNumerica_local, getEstiloCeldaNumeroEntero());
      asignarValorCelda(celdaNumerica_local, pValorCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaNumerica_local;
  }
  public HSSFCell crearCeldaNumeroDecimal(HSSFRow pFila, int pNumeroColumna, double pValorCelda) {
    HSSFCell celdaNumerica_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celdaNumerica_local;
    }
    
    try {
      celdaNumerica_local = crearCelda(pFila, pNumeroColumna);
      asignarEstiloCelda(celdaNumerica_local, getEstiloCeldaNumeroReal());
      asignarValorCelda(celdaNumerica_local, pValorCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaNumerica_local;
  }
  public HSSFCell crearCeldaTexto(HSSFRow pFila, int pNumeroColumna, String pValorCelda) {
    HSSFCell celdaTexto_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pValorCelda == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    
    try {
      celdaTexto_local = crearCelda(pFila, pNumeroColumna);
      asignarEstiloCelda(celdaTexto_local, getEstiloCeldaTexto());
      asignarValorCelda(celdaTexto_local, pValorCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaTexto_local;
  }
  public HSSFCell crearCeldaTextoConNegrita(HSSFRow pFila, int pNumeroColumna, String pValorCelda) {
    HSSFCell celdaTexto_local = null;
    HSSFCellStyle estiloCelda_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    if (pValorCelda == ConstantesGeneral.VALOR_NULO) {
      return celdaTexto_local;
    }
    
    try {
      celdaTexto_local = crearCelda(pFila, pNumeroColumna);
      estiloCelda_local = crearEstiloCelda();
      asignarAlineacionEstiloCelda(estiloCelda_local, 1);
      asignarEstiloCelda(celdaTexto_local, estiloCelda_local);
      asignarEstiloNegrita(celdaTexto_local, 10);
      asignarValorCelda(celdaTexto_local, pValorCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      estiloCelda_local = null;
    } 
    return celdaTexto_local;
  }
  public HSSFCell crearCeldaFormula(HSSFRow pFila, int pNumeroColumna, String pFormulaCelda, String pFormatoCelda, boolean pAplicarNegrilla) {
    HSSFCell celdaFormula_local = null;
    HSSFCellStyle estiloCelda_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celdaFormula_local;
    }
    if (pFormulaCelda == ConstantesGeneral.VALOR_NULO) {
      return celdaFormula_local;
    }
    if (pFormatoCelda == ConstantesGeneral.VALOR_NULO) {
      return celdaFormula_local;
    }
    
    try {
      celdaFormula_local = crearCelda(pFila, pNumeroColumna);
      estiloCelda_local = crearEstiloCelda();
      asignarFormatoEstiloCelda(estiloCelda_local, pFormatoCelda);
      asignarEstiloCelda(celdaFormula_local, estiloCelda_local);
      if (pAplicarNegrilla) {
        asignarEstiloNegrita(celdaFormula_local, 10);
      }
      asignarFormulaCelda(celdaFormula_local, pFormulaCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celdaFormula_local;
  }
  private HSSFCellStyle crearEstiloCelda() {
    HSSFCellStyle estiloCelda_local = null;
    
    try {
      estiloCelda_local = getLibro().createCellStyle();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return estiloCelda_local;
  }
  private HSSFFont crearFuente() {
    HSSFFont fuente_local = null;
    
    try {
      fuente_local = getLibro().createFont();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fuente_local;
  }
  public void asignarAnchoColumna(HSSFSheet pHoja, int pNumeroColumna, int pAnchoColumna) {
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pHoja.setColumnWidth((short)pNumeroColumna, (short)pAnchoColumna);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarBordeInferiorEstiloCelda(HSSFCellStyle pEstiloCelda, int pBorde) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setBorderBottom((short)pBorde);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarBordeIzquierdoEstiloCelda(HSSFCellStyle pEstiloCelda, int pBorde) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setBorderLeft((short)pBorde);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarBordeDerechoEstiloCelda(HSSFCellStyle pEstiloCelda, int pBorde) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setBorderRight((short)pBorde);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarBordeSuperiorEstiloCelda(HSSFCellStyle pEstiloCelda, int pBorde) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setBorderTop((short)pBorde);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarAlineacionEstiloCelda(HSSFCellStyle pEstiloCelda, int pAlineacion) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setAlignment((short)pAlineacion);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarAlturaFuente(HSSFFont pFuente, int pAlturaFuente) {
    if (pFuente == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pFuente.setFontHeightInPoints((short)pAlturaFuente);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarNegritaFuente(HSSFFont pFuente, int pTipoNegrita) {
    if (pFuente == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pFuente.setBoldweight((short)pTipoNegrita);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarFuenteEstiloCelda(HSSFCellStyle pEstiloCelda, HSSFFont pFuente) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFuente == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setFont(pFuente);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarFormatoEstiloCelda(HSSFCellStyle pEstiloCelda, String pFormato) {
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFormato == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pEstiloCelda.setDataFormat(HSSFDataFormat.getBuiltinFormat(pFormato));
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarEstiloCelda(HSSFCell pCelda, HSSFCellStyle pEstiloCelda) {
    if (pCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pEstiloCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pCelda.setCellStyle(pEstiloCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarEstiloNegrita(HSSFCell pCelda, int pAlturaFuente) {
    HSSFCellStyle estiloCelda_local = null;
    HSSFFont fuente_local = null;
    
    if (pCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      estiloCelda_local = pCelda.getCellStyle();
      if (estiloCelda_local == ConstantesGeneral.VALOR_NULO) {
        estiloCelda_local = crearEstiloCelda();
      }
      fuente_local = crearFuente();
      asignarAlturaFuente(fuente_local, pAlturaFuente);
      asignarNegritaFuente(fuente_local, 700);
      asignarFuenteEstiloCelda(estiloCelda_local, fuente_local);
      asignarEstiloCelda(pCelda, estiloCelda_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fuente_local = null;
      estiloCelda_local = null;
    } 
  }
  private void asignarValorCelda(HSSFCell pCelda, int pValorCelda) {
    if (pCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pCelda.setCellValue(pValorCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void asignarValorCelda(HSSFCell pCelda, double pValorCelda) {
    if (pCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pCelda.setCellValue(pValorCelda);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarValorCelda(HSSFCell pCelda, String pValorCelda) {
    HSSFRichTextString hssfRichTextString_local = null;
    
    if (pCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      hssfRichTextString_local = new HSSFRichTextString(pValorCelda);
      pCelda.setCellValue(hssfRichTextString_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      hssfRichTextString_local = null;
    } 
  }
  private void asignarFormulaCelda(HSSFCell pCelda, String pFormula) {
    if (pCelda == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pFormula == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pCelda.setCellFormula(pFormula);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public HSSFRow obtenerFila(HSSFSheet pHoja, int pNumeroFila) {
    HSSFRow fila_local = null;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return fila_local;
    }
    
    try {
      fila_local = pHoja.getRow(pNumeroFila);
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fila_local;
  }
  public int obtenerNumeroUltimaFila(HSSFSheet pHoja) {
    int numeroFila_local = -1;
    
    if (pHoja == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    
    try {
      numeroFila_local = pHoja.getLastRowNum();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return numeroFila_local;
  }
  private int obtenerNumeroFila(HSSFRow pFila) {
    int numeroFila_local = -1;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return numeroFila_local;
    }
    
    try {
      numeroFila_local = pFila.getRowNum();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return numeroFila_local;
  }
  public HSSFCell obtenerCelda(HSSFRow pFila, int pNumeroColumna) {
    HSSFCell celda_local = null;
    
    if (pFila == ConstantesGeneral.VALOR_NULO) {
      return celda_local;
    }
    
    try {
      celda_local = pFila.getCell((short)pNumeroColumna);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return celda_local;
  }
  public boolean escribirArchivo() {
    boolean archivoGuardado_local = false;
    FileOutputStream fileOutputStream_local = null;
    
    try {
      fileOutputStream_local = new FileOutputStream(mc.concatenarCadena(getRutaArchivo(), getNombreArchivo() + ".xls"));
      getLibro().write(fileOutputStream_local);
      fileOutputStream_local.close();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } finally {
      fileOutputStream_local = null;
    } 
    return archivoGuardado_local;
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\manejoReportes\GeneradorArchivoExcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */