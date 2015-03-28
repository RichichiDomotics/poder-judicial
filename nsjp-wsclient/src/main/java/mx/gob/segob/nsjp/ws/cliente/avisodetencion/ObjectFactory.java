
package mx.gob.segob.nsjp.ws.cliente.avisodetencion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.avisodetencion package. 
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

    private final static QName _RegistrarAvisoDetencion_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarAvisoDetencion");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _RegistrarAvisoDetencionResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarAvisoDetencionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.avisodetencion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarAvisoDetencion }
     * 
     */
    public RegistrarAvisoDetencion createRegistrarAvisoDetencion() {
        return new RegistrarAvisoDetencion();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link RegistrarAvisoDetencionResponse }
     * 
     */
    public RegistrarAvisoDetencionResponse createRegistrarAvisoDetencionResponse() {
        return new RegistrarAvisoDetencionResponse();
    }

    /**
     * Create an instance of {@link AvisoDetencionWSDTO }
     * 
     */
    public AvisoDetencionWSDTO createAvisoDetencionWSDTO() {
        return new AvisoDetencionWSDTO();
    }

    /**
     * Create an instance of {@link DetencionWSDTO }
     * 
     */
    public DetencionWSDTO createDetencionWSDTO() {
        return new DetencionWSDTO();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarAvisoDetencion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarAvisoDetencion")
    public JAXBElement<RegistrarAvisoDetencion> createRegistrarAvisoDetencion(RegistrarAvisoDetencion value) {
        return new JAXBElement<RegistrarAvisoDetencion>(_RegistrarAvisoDetencion_QNAME, RegistrarAvisoDetencion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NSJPNegocioException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "NSJPNegocioException")
    public JAXBElement<NSJPNegocioException> createNSJPNegocioException(NSJPNegocioException value) {
        return new JAXBElement<NSJPNegocioException>(_NSJPNegocioException_QNAME, NSJPNegocioException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarAvisoDetencionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarAvisoDetencionResponse")
    public JAXBElement<RegistrarAvisoDetencionResponse> createRegistrarAvisoDetencionResponse(RegistrarAvisoDetencionResponse value) {
        return new JAXBElement<RegistrarAvisoDetencionResponse>(_RegistrarAvisoDetencionResponse_QNAME, RegistrarAvisoDetencionResponse.class, null, value);
    }

}
