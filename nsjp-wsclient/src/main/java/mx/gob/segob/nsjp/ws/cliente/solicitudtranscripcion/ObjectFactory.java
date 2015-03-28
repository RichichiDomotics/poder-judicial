
package mx.gob.segob.nsjp.ws.cliente.solicitudtranscripcion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.solicitudtranscripcion package. 
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

    private final static QName _RegistrarSolicitudTranscripcionResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarSolicitudTranscripcionResponse");
    private final static QName _RegistrarSolicitudTranscripcion_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarSolicitudTranscripcion");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.solicitudtranscripcion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarSolicitudTranscripcionResponse }
     * 
     */
    public RegistrarSolicitudTranscripcionResponse createRegistrarSolicitudTranscripcionResponse() {
        return new RegistrarSolicitudTranscripcionResponse();
    }

    /**
     * Create an instance of {@link RegistrarSolicitudTranscripcion }
     * 
     */
    public RegistrarSolicitudTranscripcion createRegistrarSolicitudTranscripcion() {
        return new RegistrarSolicitudTranscripcion();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link AudienciaWSDTO }
     * 
     */
    public AudienciaWSDTO createAudienciaWSDTO() {
        return new AudienciaWSDTO();
    }

    /**
     * Create an instance of {@link SolicitudAudienciaBasicoWSDTO }
     * 
     */
    public SolicitudAudienciaBasicoWSDTO createSolicitudAudienciaBasicoWSDTO() {
        return new SolicitudAudienciaBasicoWSDTO();
    }

    /**
     * Create an instance of {@link SolicitudTranscripcionWSDTO }
     * 
     */
    public SolicitudTranscripcionWSDTO createSolicitudTranscripcionWSDTO() {
        return new SolicitudTranscripcionWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarSolicitudTranscripcionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarSolicitudTranscripcionResponse")
    public JAXBElement<RegistrarSolicitudTranscripcionResponse> createRegistrarSolicitudTranscripcionResponse(RegistrarSolicitudTranscripcionResponse value) {
        return new JAXBElement<RegistrarSolicitudTranscripcionResponse>(_RegistrarSolicitudTranscripcionResponse_QNAME, RegistrarSolicitudTranscripcionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarSolicitudTranscripcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarSolicitudTranscripcion")
    public JAXBElement<RegistrarSolicitudTranscripcion> createRegistrarSolicitudTranscripcion(RegistrarSolicitudTranscripcion value) {
        return new JAXBElement<RegistrarSolicitudTranscripcion>(_RegistrarSolicitudTranscripcion_QNAME, RegistrarSolicitudTranscripcion.class, null, value);
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
