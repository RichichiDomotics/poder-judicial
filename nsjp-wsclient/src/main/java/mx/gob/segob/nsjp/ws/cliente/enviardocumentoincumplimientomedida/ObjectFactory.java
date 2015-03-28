
package mx.gob.segob.nsjp.ws.cliente.enviardocumentoincumplimientomedida;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.enviardocumentoincumplimientomedida package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EnviarDocumentoDeIncumplimientoDeMedidaResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "enviarDocumentoDeIncumplimientoDeMedidaResponse");
    private final static QName _EnviarDocumentoDeIncumplimientoDeMedida_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "enviarDocumentoDeIncumplimientoDeMedida");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.enviardocumentoincumplimientomedida
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviarDocumentoDeIncumplimientoDeMedidaResponse }
     * 
     */
    public EnviarDocumentoDeIncumplimientoDeMedidaResponse createEnviarDocumentoDeIncumplimientoDeMedidaResponse() {
        return new EnviarDocumentoDeIncumplimientoDeMedidaResponse();
    }

    /**
     * Create an instance of {@link EnviarDocumentoDeIncumplimientoDeMedida }
     * 
     */
    public EnviarDocumentoDeIncumplimientoDeMedida createEnviarDocumentoDeIncumplimientoDeMedida() {
        return new EnviarDocumentoDeIncumplimientoDeMedida();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link DocumentoWSDTO }
     * 
     */
    public DocumentoWSDTO createDocumentoWSDTO() {
        return new DocumentoWSDTO();
    }

    /**
     * Create an instance of {@link ArchivoDigitalWSDTO }
     * 
     */
    public ArchivoDigitalWSDTO createArchivoDigitalWSDTO() {
        return new ArchivoDigitalWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarDocumentoDeIncumplimientoDeMedidaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "enviarDocumentoDeIncumplimientoDeMedidaResponse")
    public JAXBElement<EnviarDocumentoDeIncumplimientoDeMedidaResponse> createEnviarDocumentoDeIncumplimientoDeMedidaResponse(EnviarDocumentoDeIncumplimientoDeMedidaResponse value) {
        return new JAXBElement<EnviarDocumentoDeIncumplimientoDeMedidaResponse>(_EnviarDocumentoDeIncumplimientoDeMedidaResponse_QNAME, EnviarDocumentoDeIncumplimientoDeMedidaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarDocumentoDeIncumplimientoDeMedida }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "enviarDocumentoDeIncumplimientoDeMedida")
    public JAXBElement<EnviarDocumentoDeIncumplimientoDeMedida> createEnviarDocumentoDeIncumplimientoDeMedida(EnviarDocumentoDeIncumplimientoDeMedida value) {
        return new JAXBElement<EnviarDocumentoDeIncumplimientoDeMedida>(_EnviarDocumentoDeIncumplimientoDeMedida_QNAME, EnviarDocumentoDeIncumplimientoDeMedida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NSJPNegocioException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "NSJPNegocioException")
    public JAXBElement<NSJPNegocioException> createNSJPNegocioException(NSJPNegocioException value) {
        return new JAXBElement<NSJPNegocioException>(_NSJPNegocioException_QNAME, NSJPNegocioException.class, null, value);
    }

}
