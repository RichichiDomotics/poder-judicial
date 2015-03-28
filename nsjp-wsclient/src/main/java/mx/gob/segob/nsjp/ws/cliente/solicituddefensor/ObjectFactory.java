
package mx.gob.segob.nsjp.ws.cliente.solicituddefensor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.solicituddefensor package. 
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

    private final static QName _RegistrarSolicitudDefensor_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarSolicitudDefensor");
    private final static QName _RegistrarSolicitudDefensorResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarSolicitudDefensorResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.solicituddefensor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarSolicitudDefensor }
     * 
     */
    public RegistrarSolicitudDefensor createRegistrarSolicitudDefensor() {
        return new RegistrarSolicitudDefensor();
    }

    /**
     * Create an instance of {@link RegistrarSolicitudDefensorResponse }
     * 
     */
    public RegistrarSolicitudDefensorResponse createRegistrarSolicitudDefensorResponse() {
        return new RegistrarSolicitudDefensorResponse();
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
     * Create an instance of {@link SolicitudDefensorWSDTO }
     * 
     */
    public SolicitudDefensorWSDTO createSolicitudDefensorWSDTO() {
        return new SolicitudDefensorWSDTO();
    }

    /**
     * Create an instance of {@link DelitoWSDTO }
     * 
     */
    public DelitoWSDTO createDelitoWSDTO() {
        return new DelitoWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarSolicitudDefensor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarSolicitudDefensor")
    public JAXBElement<RegistrarSolicitudDefensor> createRegistrarSolicitudDefensor(RegistrarSolicitudDefensor value) {
        return new JAXBElement<RegistrarSolicitudDefensor>(_RegistrarSolicitudDefensor_QNAME, RegistrarSolicitudDefensor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarSolicitudDefensorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarSolicitudDefensorResponse")
    public JAXBElement<RegistrarSolicitudDefensorResponse> createRegistrarSolicitudDefensorResponse(RegistrarSolicitudDefensorResponse value) {
        return new JAXBElement<RegistrarSolicitudDefensorResponse>(_RegistrarSolicitudDefensorResponse_QNAME, RegistrarSolicitudDefensorResponse.class, null, value);
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
