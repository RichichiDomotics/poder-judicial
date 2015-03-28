
package mx.gob.segob.nsjp.ws.cliente.medidaalterna;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.medidaalterna package. 
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

    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _RegistrarMedidaAlterna_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarMedidaAlterna");
    private final static QName _RegistrarMedidaAlternaResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarMedidaAlternaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.medidaalterna
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link RegistrarMedidaAlterna }
     * 
     */
    public RegistrarMedidaAlterna createRegistrarMedidaAlterna() {
        return new RegistrarMedidaAlterna();
    }

    /**
     * Create an instance of {@link RegistrarMedidaAlternaResponse }
     * 
     */
    public RegistrarMedidaAlternaResponse createRegistrarMedidaAlternaResponse() {
        return new RegistrarMedidaAlternaResponse();
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
     * Create an instance of {@link MedidaAlternaWSDTO }
     * 
     */
    public MedidaAlternaWSDTO createMedidaAlternaWSDTO() {
        return new MedidaAlternaWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarMedidaAlterna }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarMedidaAlterna")
    public JAXBElement<RegistrarMedidaAlterna> createRegistrarMedidaAlterna(RegistrarMedidaAlterna value) {
        return new JAXBElement<RegistrarMedidaAlterna>(_RegistrarMedidaAlterna_QNAME, RegistrarMedidaAlterna.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarMedidaAlternaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarMedidaAlternaResponse")
    public JAXBElement<RegistrarMedidaAlternaResponse> createRegistrarMedidaAlternaResponse(RegistrarMedidaAlternaResponse value) {
        return new JAXBElement<RegistrarMedidaAlternaResponse>(_RegistrarMedidaAlternaResponse_QNAME, RegistrarMedidaAlternaResponse.class, null, value);
    }

}
