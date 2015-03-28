
package mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito package. 
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

    private final static QName _ConsultarTribunalesPorDistrito_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarTribunalesPorDistrito");
    private final static QName _ConsultarAgenciasPorDistritoResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarAgenciasPorDistritoResponse");
    private final static QName _ConsultarTribunalesPorDistritoResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarTribunalesPorDistritoResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _ConsultarAgenciasPorDistrito_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarAgenciasPorDistrito");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.consultartribunalespordistrito
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarTribunalesPorDistrito }
     * 
     */
    public ConsultarTribunalesPorDistrito createConsultarTribunalesPorDistrito() {
        return new ConsultarTribunalesPorDistrito();
    }

    /**
     * Create an instance of {@link ConsultarTribunalesPorDistritoResponse }
     * 
     */
    public ConsultarTribunalesPorDistritoResponse createConsultarTribunalesPorDistritoResponse() {
        return new ConsultarTribunalesPorDistritoResponse();
    }

    /**
     * Create an instance of {@link ConsultarAgenciasPorDistritoResponse }
     * 
     */
    public ConsultarAgenciasPorDistritoResponse createConsultarAgenciasPorDistritoResponse() {
        return new ConsultarAgenciasPorDistritoResponse();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link ConsultarAgenciasPorDistrito }
     * 
     */
    public ConsultarAgenciasPorDistrito createConsultarAgenciasPorDistrito() {
        return new ConsultarAgenciasPorDistrito();
    }

    /**
     * Create an instance of {@link CatDiscriminanteWSDTO }
     * 
     */
    public CatDiscriminanteWSDTO createCatDiscriminanteWSDTO() {
        return new CatDiscriminanteWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarTribunalesPorDistrito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarTribunalesPorDistrito")
    public JAXBElement<ConsultarTribunalesPorDistrito> createConsultarTribunalesPorDistrito(ConsultarTribunalesPorDistrito value) {
        return new JAXBElement<ConsultarTribunalesPorDistrito>(_ConsultarTribunalesPorDistrito_QNAME, ConsultarTribunalesPorDistrito.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAgenciasPorDistritoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarAgenciasPorDistritoResponse")
    public JAXBElement<ConsultarAgenciasPorDistritoResponse> createConsultarAgenciasPorDistritoResponse(ConsultarAgenciasPorDistritoResponse value) {
        return new JAXBElement<ConsultarAgenciasPorDistritoResponse>(_ConsultarAgenciasPorDistritoResponse_QNAME, ConsultarAgenciasPorDistritoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarTribunalesPorDistritoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarTribunalesPorDistritoResponse")
    public JAXBElement<ConsultarTribunalesPorDistritoResponse> createConsultarTribunalesPorDistritoResponse(ConsultarTribunalesPorDistritoResponse value) {
        return new JAXBElement<ConsultarTribunalesPorDistritoResponse>(_ConsultarTribunalesPorDistritoResponse_QNAME, ConsultarTribunalesPorDistritoResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAgenciasPorDistrito }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarAgenciasPorDistrito")
    public JAXBElement<ConsultarAgenciasPorDistrito> createConsultarAgenciasPorDistrito(ConsultarAgenciasPorDistrito value) {
        return new JAXBElement<ConsultarAgenciasPorDistrito>(_ConsultarAgenciasPorDistrito_QNAME, ConsultarAgenciasPorDistrito.class, null, value);
}

}
