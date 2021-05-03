package com.sisnet.utilidades;
import com.sisnet.constantes.ConstantesGeneral;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
public class XML
{
  private static com.sisnet.utilidades.XML XMLSingleton = null;
  public static com.sisnet.utilidades.XML getXML() {
    if (XMLSingleton == null) {
      XMLSingleton = new com.sisnet.utilidades.XML();
    }
    return XMLSingleton;
  }
  public void adicionarNodoHijo(Node pNodo, Node pNodoNuevo) {
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pNodo.appendChild(pNodoNuevo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionarNodoHijo(Element pElemento, Node pNodoNuevo) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pElemento.appendChild(pNodoNuevo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void adicionarNodoHijo(Document pDocumento, Node pNodoNuevo) {
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pDocumento.appendChild(pNodoNuevo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void clonarNodo(Node pNodo, boolean pNivel) {
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pNodo.cloneNode(pNivel);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void clonarNodo(Element pElemento, boolean pNivel) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pElemento.cloneNode(pNivel);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void clonarNodo(Document pDocumento, boolean pNivel) {
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pDocumento.cloneNode(pNivel);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public short compararPosicionDocumento(Node pNodo, Node pNodoComparar) {
    short posicionDocumento_local = -1;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return posicionDocumento_local;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return posicionDocumento_local;
    }
    try {
      posicionDocumento_local = pNodo.compareDocumentPosition(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return posicionDocumento_local;
  }
  public short compararPosicionDocumento(Element pElemento, Node pNodoComparar) {
    short posicionDocumento_local = -1;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return posicionDocumento_local;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return posicionDocumento_local;
    }
    try {
      posicionDocumento_local = pElemento.compareDocumentPosition(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return posicionDocumento_local;
  }
  public short compararPosicionDocumento(Document pDocumento, Node pNodoComparar) {
    short posicionDocumento_local = -1;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return posicionDocumento_local;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return posicionDocumento_local;
    }
    try {
      posicionDocumento_local = pDocumento.compareDocumentPosition(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return posicionDocumento_local;
  }
  public NamedNodeMap obtenerAtributos(Node pNodo) {
    NamedNodeMap atributoNodo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    try {
      atributoNodo_local = pNodo.getAttributes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public NamedNodeMap obtenerAtributos(Element pElemento) {
    NamedNodeMap atributoNodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    try {
      atributoNodo_local = pElemento.getAttributes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public NamedNodeMap obtenerAtributos(Document pDocumento) {
    NamedNodeMap atributoNodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    try {
      atributoNodo_local = pDocumento.getAttributes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public NodeList obtenerListaNodosHijo(Node pNodo) {
    NodeList nodosHijo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodosHijo_local;
    }
    try {
      nodosHijo_local = pNodo.getChildNodes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodosHijo_local;
  }
  public NodeList obtenerListaNodosHijo(Element pElemento) {
    NodeList nodosHijo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nodosHijo_local;
    }
    try {
      nodosHijo_local = pElemento.getChildNodes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodosHijo_local;
  }
  public NodeList obtenerListaNodosHijo(Document pDocumento) {
    NodeList nodosHijo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodosHijo_local;
    }
    try {
      nodosHijo_local = pDocumento.getChildNodes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodosHijo_local;
  }
  public Node obtenerPrimerNodoHijo(Node pNodo) {
    Node primerNodoHijo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return primerNodoHijo_local;
    }
    try {
      primerNodoHijo_local = pNodo.getFirstChild();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return primerNodoHijo_local;
  }
  public Node obtenerPrimerNodoHijo(Element pElemento) {
    Node primerNodoHijo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return primerNodoHijo_local;
    }
    try {
      primerNodoHijo_local = pElemento.getFirstChild();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return primerNodoHijo_local;
  }
  public Node obtenerPrimerNodoHijo(Document pDocumento) {
    Node primerNodoHijo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return primerNodoHijo_local;
    }
    try {
      primerNodoHijo_local = pDocumento.getFirstChild();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return primerNodoHijo_local;
  }
  public Node obtenerAnteriorNodoHijo(Node pNodo) {
    Node nodoHijo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pNodo.getLastChild();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node obtenerAnteriorNodoHijo(Element pElemento) {
    Node nodoHijo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pElemento.getLastChild();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node obtenerAnteriorNodoHijo(Document pDocumento) {
    Node nodoHijo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pDocumento.getLastChild();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public String obtenerNombreNodoLocal(Node pNodo) {
    String nombreNodo_local = "";
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nombreNodo_local;
    }
    try {
      nombreNodo_local = pNodo.getLocalName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreNodo_local;
  }
  public String obtenerNombreNodoLocal(Element pElemento) {
    String nombreNodo_local = "";
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nombreNodo_local;
    }
    try {
      nombreNodo_local = pElemento.getLocalName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreNodo_local;
  }
  public String obtenerNombreNodoLocal(Document pDocumento) {
    String nombreNodo_local = "";
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nombreNodo_local;
    }
    try {
      nombreNodo_local = pDocumento.getLocalName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreNodo_local;
  }
  public Node obtenerSiguienteNivelNodo(Node pNodo) {
    Node siguienteNivelNodo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return siguienteNivelNodo_local;
    }
    try {
      siguienteNivelNodo_local = pNodo.getNextSibling();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return siguienteNivelNodo_local;
  }
  public Node obtenerSiguienteNivelNodo(Element pElemento) {
    Node siguienteNivelNodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return siguienteNivelNodo_local;
    }
    try {
      siguienteNivelNodo_local = pElemento.getNextSibling();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return siguienteNivelNodo_local;
  }
  public Node obtenerSiguienteNivelNodo(Document pDocumento) {
    Node siguienteNivelNodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return siguienteNivelNodo_local;
    }
    try {
      siguienteNivelNodo_local = pDocumento.getNextSibling();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return siguienteNivelNodo_local;
  }
  public String obtenerNombreNodo(Node pNodo) {
    String nombreNodo_local = "";
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nombreNodo_local;
    }
    try {
      nombreNodo_local = pNodo.getNodeName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreNodo_local;
  }
  public String obtenerNombreNodo(Element pElemento) {
    String nombreNodo_local = "";
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nombreNodo_local;
    }
    try {
      nombreNodo_local = pElemento.getNodeName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreNodo_local;
  }
  public String obtenerNombreNodo(Document pDocumento) {
    String nombreNodo_local = "";
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nombreNodo_local;
    }
    try {
      nombreNodo_local = pDocumento.getNodeName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreNodo_local;
  }
  public short obtenerTipoNodo(Node pNodo) {
    short tipoNodo_local = -1;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return tipoNodo_local;
    }
    try {
      tipoNodo_local = pNodo.getNodeType();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoNodo_local;
  }
  public short obtenerTipoNodo(Element pElemento) {
    short tipoNodo_local = -1;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return tipoNodo_local;
    }
    try {
      tipoNodo_local = pElemento.getNodeType();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoNodo_local;
  }
  public short obtenerTipoNodo(Document pDocumento) {
    short tipoNodo_local = -1;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return tipoNodo_local;
    }
    try {
      tipoNodo_local = pDocumento.getNodeType();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tipoNodo_local;
  }
  public String obtenerValorNodo(Node pNodo) {
    String valorNodo_local = "";
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return valorNodo_local;
    }
    try {
      valorNodo_local = pNodo.getNodeValue();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return valorNodo_local;
  }
  public String obtenerValorNodo(Element pElemento) {
    String valorNodo_local = "";
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return valorNodo_local;
    }
    try {
      valorNodo_local = pElemento.getNodeValue();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return valorNodo_local;
  }
  public String obtenerValorNodo(Document pDocumento) {
    String valorNodo_local = "";
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return valorNodo_local;
    }
    try {
      valorNodo_local = pDocumento.getNodeValue();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return valorNodo_local;
  }
  public Node obtenerNodoPadre(Node pNodo) {
    Node nodoPadre_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodoPadre_local;
    }
    try {
      nodoPadre_local = pNodo.getParentNode();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoPadre_local;
  }
  public Node obtenerNodoPadre(Element pElemento) {
    Node nodoPadre_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nodoPadre_local;
    }
    try {
      nodoPadre_local = pElemento.getParentNode();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoPadre_local;
  }
  public Node obtenerNodoPadre(Document pDocumento) {
    Node nodoPadre_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodoPadre_local;
    }
    try {
      nodoPadre_local = pDocumento.getParentNode();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoPadre_local;
  }
  public Node obtenerAnteriorNivelNodo(Node pNodo) {
    Node anteriorNivelNodo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return anteriorNivelNodo_local;
    }
    try {
      anteriorNivelNodo_local = pNodo.getPreviousSibling();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return anteriorNivelNodo_local;
  }
  public Node obtenerAnteriorNivelNodo(Element pElemento) {
    Node anteriorNivelNodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return anteriorNivelNodo_local;
    }
    try {
      anteriorNivelNodo_local = pElemento.getPreviousSibling();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return anteriorNivelNodo_local;
  }
  public Node obtenerAnteriorNivelNodo(Document pDocumento) {
    Node anteriorNivelNodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return anteriorNivelNodo_local;
    }
    try {
      anteriorNivelNodo_local = pDocumento.getPreviousSibling();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return anteriorNivelNodo_local;
  }
  public String obtenerContenidoTexto(Node pNodo) {
    String contenidoTextoNodo_local = "";
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return contenidoTextoNodo_local;
    }
    try {
      contenidoTextoNodo_local = pNodo.getTextContent();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return contenidoTextoNodo_local;
  }
  public String obtenerContenidoTexto(Element pElemento) {
    String contenidoTextoNodo_local = "";
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return contenidoTextoNodo_local;
    }
    try {
      contenidoTextoNodo_local = pElemento.getTextContent();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return contenidoTextoNodo_local;
  }
  public String obtenerContenidoTexto(Document pDocumento) {
    String contenidoTextoNodo_local = "";
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return contenidoTextoNodo_local;
    }
    try {
      contenidoTextoNodo_local = pDocumento.getTextContent();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return contenidoTextoNodo_local;
  }
  public boolean tieneAtributos(Node pNodo) {
    boolean tieneAtributosNodo_local = false;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return tieneAtributosNodo_local;
    }
    try {
      tieneAtributosNodo_local = pNodo.hasAttributes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneAtributosNodo_local;
  }
  public boolean tieneAtributos(Element pElemento) {
    boolean tieneAtributosNodo_local = false;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return tieneAtributosNodo_local;
    }
    try {
      tieneAtributosNodo_local = pElemento.hasAttributes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneAtributosNodo_local;
  }
  public boolean tieneAtributos(Document pDocumento) {
    boolean tieneAtributosNodo_local = false;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return tieneAtributosNodo_local;
    }
    try {
      tieneAtributosNodo_local = pDocumento.hasAttributes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneAtributosNodo_local;
  }
  public boolean tieneNodosHijo(Node pNodo) {
    boolean tieneNodosHijo_local = false;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return tieneNodosHijo_local;
    }
    try {
      tieneNodosHijo_local = pNodo.hasChildNodes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneNodosHijo_local;
  }
  public boolean tieneNodosHijo(Element pElemento) {
    boolean tieneNodosHijo_local = false;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return tieneNodosHijo_local;
    }
    try {
      tieneNodosHijo_local = pElemento.hasChildNodes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneNodosHijo_local;
  }
  public boolean tieneNodosHijo(Document pDocumento) {
    boolean tieneNodosHijo_local = false;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return tieneNodosHijo_local;
    }
    try {
      tieneNodosHijo_local = pDocumento.hasChildNodes();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneNodosHijo_local;
  }
  public Node insertarAntes(Node pNodo, Node pNodoNuevo, Node pNodoReferencia) {
    Node nodo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoReferencia == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    try {
      nodo_local = pNodo.insertBefore(pNodoNuevo, pNodoReferencia);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodo_local;
  }
  public Node insertarAntes(Element pElemento, Node pNodoNuevo, Node pNodoReferencia) {
    Node nodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoReferencia == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    try {
      nodo_local = pElemento.insertBefore(pNodoNuevo, pNodoReferencia);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodo_local;
  }
  public Node insertarAntes(Document pDocumento, Node pNodoNuevo, Node pNodoReferencia) {
    Node nodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoReferencia == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    try {
      nodo_local = pDocumento.insertBefore(pNodoNuevo, pNodoReferencia);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodo_local;
  }
  public boolean esIgualNodo(Node pNodo, Node pNodoComparar) {
    boolean esIgualNodo_local = false;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return esIgualNodo_local;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return esIgualNodo_local;
    }
    try {
      esIgualNodo_local = pNodo.isEqualNode(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esIgualNodo_local;
  }
  public boolean esIgualNodo(Element pElemento, Node pNodoComparar) {
    boolean esIgualNodo_local = false;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return esIgualNodo_local;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return esIgualNodo_local;
    }
    try {
      esIgualNodo_local = pElemento.isEqualNode(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esIgualNodo_local;
  }
  public boolean esIgualNodo(Document pDocumento, Node pNodoComparar) {
    boolean esIgualNodo_local = false;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return esIgualNodo_local;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return esIgualNodo_local;
    }
    try {
      esIgualNodo_local = pDocumento.isEqualNode(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esIgualNodo_local;
  }
  public boolean esMismoNodo(Node pNodo, Node pNodoComparar) {
    boolean esMismoNodo = false;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return esMismoNodo;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return esMismoNodo;
    }
    try {
      esMismoNodo = pNodo.isSameNode(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esMismoNodo;
  }
  public boolean esMismoNodo(Element pElemento, Node pNodoComparar) {
    boolean esMismoNodo = false;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return esMismoNodo;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return esMismoNodo;
    }
    try {
      esMismoNodo = pElemento.isSameNode(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esMismoNodo;
  }
  public boolean esMismoNodo(Document pDocumento, Node pNodoComparar) {
    boolean esMismoNodo = false;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return esMismoNodo;
    }
    if (pNodoComparar == ConstantesGeneral.VALOR_NULO) {
      return esMismoNodo;
    }
    try {
      esMismoNodo = pDocumento.isSameNode(pNodoComparar);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return esMismoNodo;
  }
  public Node borrarNodoHijo(Node pNodo, Node pNodoAntiguo) {
    Node nodoHijo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoAntiguo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pNodo.removeChild(pNodoAntiguo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node borrarNodoHijo(Element pElemento, Node pNodoAntiguo) {
    Node nodoHijo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoAntiguo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pElemento.removeChild(pNodoAntiguo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node borrarNodoHijo(Document pDocumento, Node pNodoAntiguo) {
    Node nodoHijo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoAntiguo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pDocumento.removeChild(pNodoAntiguo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node remplazarNodoHijo(Node pNodo, Node pNodoNuevo, Node pNodoAnterior) {
    Node nodoHijo_local = null;
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoAnterior == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pNodo.replaceChild(pNodoNuevo, pNodoAnterior);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node remplazarNodoHijo(Element pElemento, Node pNodoNuevo, Node pNodoAnterior) {
    Node nodoHijo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoAnterior == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pElemento.replaceChild(pNodoNuevo, pNodoAnterior);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public Node remplazarNodoHijo(Document pDocumento, Node pNodoNuevo, Node pNodoAnterior) {
    Node nodoHijo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoNuevo == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    if (pNodoAnterior == ConstantesGeneral.VALOR_NULO) {
      return nodoHijo_local;
    }
    try {
      nodoHijo_local = pDocumento.replaceChild(pNodoNuevo, pNodoAnterior);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoHijo_local;
  }
  public void asignarValor(Node pNodo, String pValorNodo) {
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pNodo.setNodeValue(pValorNodo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarValor(Element pElemento, String pValorNodo) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pElemento.setNodeValue(pValorNodo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarValor(Document pDocumento, String pValorNodo) {
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pDocumento.setNodeValue(pValorNodo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarContenidoTexto(Node pNodo, String pContenidoTexto) {
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pContenidoTexto == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pNodo.setTextContent(pContenidoTexto);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarContenidoTexto(Element pElemento, String pContenidoTexto) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pContenidoTexto == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pElemento.setTextContent(pContenidoTexto);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarContenidoTexto(Document pDocumento, String pContenidoTexto) {
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pContenidoTexto == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pDocumento.setTextContent(pContenidoTexto);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public NodeList obtenerListaElementosPorNombreElemento(Element pElemento, String pNombreElemento) {
    NodeList listaElementos_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return listaElementos_local;
    }
    if (pNombreElemento == ConstantesGeneral.VALOR_NULO) {
      return listaElementos_local;
    }
    try {
      listaElementos_local = pElemento.getElementsByTagName(pNombreElemento);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaElementos_local;
  }
  public NodeList obtenerListaElementosPorNombreElemento(Document pDocumento, String pNombreElemento) {
    NodeList listaElementos_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return listaElementos_local;
    }
    if (pNombreElemento == ConstantesGeneral.VALOR_NULO) {
      return listaElementos_local;
    }
    try {
      listaElementos_local = pDocumento.getElementsByTagName(pNombreElemento);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaElementos_local;
  }
  public Attr crearAtributo(Document pDocumento, String pNombreAtributo) {
    Attr atributoNodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    try {
      atributoNodo_local = pDocumento.createAttribute(pNombreAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public CDATASection crearSeccionDataDocumento(Document pDocumento, String pData) {
    CDATASection seccionDocumento_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return seccionDocumento_local;
    }
    if (pData == ConstantesGeneral.VALOR_NULO) {
      return seccionDocumento_local;
    }
    try {
      seccionDocumento_local = pDocumento.createCDATASection(pData);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return seccionDocumento_local;
  }
  public Comment crearComentarioDocumento(Document pDocumento, String pComentario) {
    Comment comentarioDocumento_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return comentarioDocumento_local;
    }
    if (pComentario == ConstantesGeneral.VALOR_NULO) {
      return comentarioDocumento_local;
    }
    try {
      comentarioDocumento_local = pDocumento.createComment(pComentario);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return comentarioDocumento_local;
  }
  public DocumentFragment crearFragmentoDataDocumento(Document pDocumento) {
    DocumentFragment fragmentoDocumento_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return fragmentoDocumento_local;
    }
    try {
      fragmentoDocumento_local = pDocumento.createDocumentFragment();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return fragmentoDocumento_local;
  }
  public Element crearElementoDocumento(Document pDocumento, String pNombreElemento) {
    Element elementoDocumento_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return elementoDocumento_local;
    }
    if (pNombreElemento == ConstantesGeneral.VALOR_NULO) {
      return elementoDocumento_local;
    }
    try {
      elementoDocumento_local = pDocumento.createElement(pNombreElemento);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return elementoDocumento_local;
  }
  public EntityReference crearEntidadReferenciaDocumento(Document pDocumento, String pNombreEntidad) {
    EntityReference entidadReferencia_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return entidadReferencia_local;
    }
    if (pNombreEntidad == ConstantesGeneral.VALOR_NULO) {
      return entidadReferencia_local;
    }
    try {
      entidadReferencia_local = pDocumento.createEntityReference(pNombreEntidad);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return entidadReferencia_local;
  }
  public Text crearNodoTexto(Document pDocumento, String pTexto) {
    Text nodoTexto_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodoTexto_local;
    }
    if (pTexto == ConstantesGeneral.VALOR_NULO) {
      return nodoTexto_local;
    }
    try {
      nodoTexto_local = pDocumento.createTextNode(pTexto);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodoTexto_local;
  }
  public Element obtenerElementoDocumento(Document pDocumento) {
    Element elementoDocumento_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return elementoDocumento_local;
    }
    try {
      elementoDocumento_local = pDocumento.getDocumentElement();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return elementoDocumento_local;
  }
  public Element obtenerElementoPorIdentificacion(Document pDocumento, String pIdElemento) {
    Element elemento_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return elemento_local;
    }
    if (pIdElemento == ConstantesGeneral.VALOR_NULO) {
      return elemento_local;
    }
    try {
      elemento_local = pDocumento.getElementById(pIdElemento);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return elemento_local;
  }
  public Node importarNodo(Document pDocumento, Node pNodoImportar, boolean pNivel) {
    Node nodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodoImportar == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    try {
      nodo_local = pDocumento.importNode(pNodoImportar, pNivel);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodo_local;
  }
  public Node renombrarNodo(Document pDocumento, Node pNodo, String pNombreURI, String pNombreNodo) {
    Node nodo_local = null;
    if (pDocumento == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNodo == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNombreURI == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    if (pNombreNodo == ConstantesGeneral.VALOR_NULO) {
      return nodo_local;
    }
    try {
      nodo_local = pDocumento.renameNode(pNodo, pNombreURI, pNombreNodo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nodo_local;
  }
  public String obtenerAtributo(Element pElemento, String pNombreAtributo) {
    String atributo_local = "";
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return atributo_local;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return atributo_local;
    }
    
    try {
      atributo_local = pElemento.getAttribute(pNombreAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributo_local;
  }
  public Attr obtenerAtributoNodo(Element pElemento, String pNombreAtributoNodo) {
    Attr atributoNodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    if (pNombreAtributoNodo == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    
    try {
      atributoNodo_local = pElemento.getAttributeNode(pNombreAtributoNodo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public String obtenerNombreElemento(Element pElemento) {
    String nombreElemento_local = "";
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return nombreElemento_local;
    }
    
    try {
      nombreElemento_local = pElemento.getTagName();
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return nombreElemento_local;
  }
  public boolean tieneAtributo(Element pElemento, String pNombreAtributo) {
    boolean tieneAtributo_local = false;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return tieneAtributo_local;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return tieneAtributo_local;
    }
    try {
      tieneAtributo_local = pElemento.hasAttribute(pNombreAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return tieneAtributo_local;
  }
  public void borrarAtributo(Element pElemento, String pNombreAtributo) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pElemento.removeAttribute(pNombreAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Attr borrarAtributoNodo(Element pElemento, Attr pAntiguoNodo) {
    Attr atributoNodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    if (pAntiguoNodo == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    
    try {
      pElemento.removeAttributeNode(pAntiguoNodo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public void asignarAtributo(Element pElemento, String pNombreAtributo, String pValorAtributo) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pValorAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pElemento.setAttribute(pNombreAtributo, pValorAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public Attr asignarAtributoNodo(Element pElemento, Attr pNuevoAtributo) {
    Attr atributoNodo_local = null;
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    if (pNuevoAtributo == ConstantesGeneral.VALOR_NULO) {
      return atributoNodo_local;
    }
    
    try {
      atributoNodo_local = pElemento.setAttributeNode(pNuevoAtributo);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return atributoNodo_local;
  }
  public void asignarIdAtributo(Element pElemento, String pNombreAtributo, boolean pEsIdentificador) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pNombreAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pElemento.setIdAttribute(pNombreAtributo, pEsIdentificador);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  public void asignarIdAtributoNodo(Element pElemento, Attr pIdAtributo, boolean pEsIdentificador) {
    if (pElemento == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    if (pIdAtributo == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pElemento.setIdAttributeNode(pIdAtributo, pEsIdentificador);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisne\\utilidades\XML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */