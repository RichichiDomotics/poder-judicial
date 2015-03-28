
package mx.gob.segob.nsjp.ws.cliente.medidacautelar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.medidacautelar package. 
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

    private final static QName _RegistrarMedidaCautelarResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarMedidaCautelarResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _RegistrarMedidaCautelar_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarMedidaCautelar");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.medidacautelar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarMedidaCautelarResponse }
     * 
     */
    public RegistrarMedidaCautelarResponse createRegistrarMedidaCautelarResponse() {
        return new RegistrarMedidaCautelarResponse();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link RegistrarMedidaCautelar }
     * 
     */
    public RegistrarMedidaCautelar createRegistrarMedidaCautelar() {
        return new RegistrarMedidaCautelar();
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
     * Create an instance of {@link MedidaWSDTO }
     * 
     */
    public MedidaWSDTO createMedidaWSDTO() {
        return new MedidaWSDTO();
    }

    /**
     * Create an instance of {@link MedidaCautelarWSDTO }
     * 
     */
    public MedidaCautelarWSDTO createMedidaCautelarWSDTO() {
        return new MedidaCautelarWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarMedidaCautelarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarMedidaCautelarResponse")
    public JAXBElement<RegistrarMedidaCautelarResponse> createRegistrarMedidaCautelarResponse(RegistrarMedidaCautelarResponse value) {
        return new JAXBElement<RegistrarMedidaCautelarResponse>(_RegistrarMedidaCautelarResponse_QNAME, RegistrarMedidaCautelarResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarMedidaCautelar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarMedidaCautelar")
    public JAXBElement<RegistrarMedidaCautelar> createRegistrarMedidaCautelar(RegistrarMedidaCautelar value) {
        return new JAXBElement<RegistrarMedidaCautelar>(_RegistrarMedidaCautelar_QNAME, RegistrarMedidaCautelar.class, null, value);
    }

}
