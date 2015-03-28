
package mx.gob.segob.nsjp.ws.cliente.respuestatranscripcion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.respuestatranscripcion package. 
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

    private final static QName _RegistrarTranscripcionAudiencia_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarTranscripcionAudiencia");
    private final static QName _RegistrarTranscripcionAudienciaResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarTranscripcionAudienciaResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.respuestatranscripcion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarTranscripcionAudiencia }
     * 
     */
    public RegistrarTranscripcionAudiencia createRegistrarTranscripcionAudiencia() {
        return new RegistrarTranscripcionAudiencia();
    }

    /**
     * Create an instance of {@link RegistrarTranscripcionAudienciaResponse }
     * 
     */
    public RegistrarTranscripcionAudienciaResponse createRegistrarTranscripcionAudienciaResponse() {
        return new RegistrarTranscripcionAudienciaResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarTranscripcionAudiencia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarTranscripcionAudiencia")
    public JAXBElement<RegistrarTranscripcionAudiencia> createRegistrarTranscripcionAudiencia(RegistrarTranscripcionAudiencia value) {
        return new JAXBElement<RegistrarTranscripcionAudiencia>(_RegistrarTranscripcionAudiencia_QNAME, RegistrarTranscripcionAudiencia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarTranscripcionAudienciaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarTranscripcionAudienciaResponse")
    public JAXBElement<RegistrarTranscripcionAudienciaResponse> createRegistrarTranscripcionAudienciaResponse(RegistrarTranscripcionAudienciaResponse value) {
        return new JAXBElement<RegistrarTranscripcionAudienciaResponse>(_RegistrarTranscripcionAudienciaResponse_QNAME, RegistrarTranscripcionAudienciaResponse.class, null, value);
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
