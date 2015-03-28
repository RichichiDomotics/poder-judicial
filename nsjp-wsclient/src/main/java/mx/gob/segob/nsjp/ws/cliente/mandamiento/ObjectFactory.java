
package mx.gob.segob.nsjp.ws.cliente.mandamiento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.mandamiento package. 
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

    private final static QName _RegistrarMandamiento_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarMandamiento");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _RegistrarMandamientoResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarMandamientoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.mandamiento
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarMandamiento }
     * 
     */
    public RegistrarMandamiento createRegistrarMandamiento() {
        return new RegistrarMandamiento();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link RegistrarMandamientoResponse }
     * 
     */
    public RegistrarMandamientoResponse createRegistrarMandamientoResponse() {
        return new RegistrarMandamientoResponse();
    }

    /**
     * Create an instance of {@link MandamientoWSDTO }
     * 
     */
    public MandamientoWSDTO createMandamientoWSDTO() {
        return new MandamientoWSDTO();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarMandamiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarMandamiento")
    public JAXBElement<RegistrarMandamiento> createRegistrarMandamiento(RegistrarMandamiento value) {
        return new JAXBElement<RegistrarMandamiento>(_RegistrarMandamiento_QNAME, RegistrarMandamiento.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarMandamientoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarMandamientoResponse")
    public JAXBElement<RegistrarMandamientoResponse> createRegistrarMandamientoResponse(RegistrarMandamientoResponse value) {
        return new JAXBElement<RegistrarMandamientoResponse>(_RegistrarMandamientoResponse_QNAME, RegistrarMandamientoResponse.class, null, value);
    }

}
