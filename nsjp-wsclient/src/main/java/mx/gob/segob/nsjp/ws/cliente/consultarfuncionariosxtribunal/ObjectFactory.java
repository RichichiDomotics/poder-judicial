
package mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal package. 
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

    private final static QName _ConsultarFuncionariosXTribunal_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarFuncionariosXTribunal");
    private final static QName _ConsultarFuncionariosXTribunalResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarFuncionariosXTribunalResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.consultarfuncionariosxtribunal
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarFuncionariosXTribunal }
     * 
     */
    public ConsultarFuncionariosXTribunal createConsultarFuncionariosXTribunal() {
        return new ConsultarFuncionariosXTribunal();
    }

    /**
     * Create an instance of {@link ConsultarFuncionariosXTribunalResponse }
     * 
     */
    public ConsultarFuncionariosXTribunalResponse createConsultarFuncionariosXTribunalResponse() {
        return new ConsultarFuncionariosXTribunalResponse();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link FuncionarioWSDTO }
     * 
     */
    public FuncionarioWSDTO createFuncionarioWSDTO() {
        return new FuncionarioWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarFuncionariosXTribunal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarFuncionariosXTribunal")
    public JAXBElement<ConsultarFuncionariosXTribunal> createConsultarFuncionariosXTribunal(ConsultarFuncionariosXTribunal value) {
        return new JAXBElement<ConsultarFuncionariosXTribunal>(_ConsultarFuncionariosXTribunal_QNAME, ConsultarFuncionariosXTribunal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarFuncionariosXTribunalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarFuncionariosXTribunalResponse")
    public JAXBElement<ConsultarFuncionariosXTribunalResponse> createConsultarFuncionariosXTribunalResponse(ConsultarFuncionariosXTribunalResponse value) {
        return new JAXBElement<ConsultarFuncionariosXTribunalResponse>(_ConsultarFuncionariosXTribunalResponse_QNAME, ConsultarFuncionariosXTribunalResponse.class, null, value);
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
