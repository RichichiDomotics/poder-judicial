
package mx.gob.segob.nsjp.ws.cliente.consultaraudiencia;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.consultaraudiencia package. 
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

    private final static QName _ConsultarAudienciaById_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarAudienciaById");
    private final static QName _ConsultarAudienciasByFechasYEstatus_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarAudienciasByFechasYEstatus");
    private final static QName _ConsultarAudienciaByIdResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarAudienciaByIdResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _ConsultarAudienciasByFechasYEstatusResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "consultarAudienciasByFechasYEstatusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.consultaraudiencia
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarAudienciaById }
     * 
     */
    public ConsultarAudienciaById createConsultarAudienciaById() {
        return new ConsultarAudienciaById();
    }

    /**
     * Create an instance of {@link ConsultarAudienciasByFechasYEstatus }
     * 
     */
    public ConsultarAudienciasByFechasYEstatus createConsultarAudienciasByFechasYEstatus() {
        return new ConsultarAudienciasByFechasYEstatus();
    }

    /**
     * Create an instance of {@link ConsultarAudienciaByIdResponse }
     * 
     */
    public ConsultarAudienciaByIdResponse createConsultarAudienciaByIdResponse() {
        return new ConsultarAudienciaByIdResponse();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link ConsultarAudienciasByFechasYEstatusResponse }
     * 
     */
    public ConsultarAudienciasByFechasYEstatusResponse createConsultarAudienciasByFechasYEstatusResponse() {
        return new ConsultarAudienciasByFechasYEstatusResponse();
    }

    /**
     * Create an instance of {@link InvolucradoAudienciaDefWSDTO }
     * 
     */
    public InvolucradoAudienciaDefWSDTO createInvolucradoAudienciaDefWSDTO() {
        return new InvolucradoAudienciaDefWSDTO();
    }

    /**
     * Create an instance of {@link AudienciaDefensoriaWSDTO }
     * 
     */
    public AudienciaDefensoriaWSDTO createAudienciaDefensoriaWSDTO() {
        return new AudienciaDefensoriaWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAudienciaById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarAudienciaById")
    public JAXBElement<ConsultarAudienciaById> createConsultarAudienciaById(ConsultarAudienciaById value) {
        return new JAXBElement<ConsultarAudienciaById>(_ConsultarAudienciaById_QNAME, ConsultarAudienciaById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAudienciasByFechasYEstatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarAudienciasByFechasYEstatus")
    public JAXBElement<ConsultarAudienciasByFechasYEstatus> createConsultarAudienciasByFechasYEstatus(ConsultarAudienciasByFechasYEstatus value) {
        return new JAXBElement<ConsultarAudienciasByFechasYEstatus>(_ConsultarAudienciasByFechasYEstatus_QNAME, ConsultarAudienciasByFechasYEstatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAudienciaByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarAudienciaByIdResponse")
    public JAXBElement<ConsultarAudienciaByIdResponse> createConsultarAudienciaByIdResponse(ConsultarAudienciaByIdResponse value) {
        return new JAXBElement<ConsultarAudienciaByIdResponse>(_ConsultarAudienciaByIdResponse_QNAME, ConsultarAudienciaByIdResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAudienciasByFechasYEstatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "consultarAudienciasByFechasYEstatusResponse")
    public JAXBElement<ConsultarAudienciasByFechasYEstatusResponse> createConsultarAudienciasByFechasYEstatusResponse(ConsultarAudienciasByFechasYEstatusResponse value) {
        return new JAXBElement<ConsultarAudienciasByFechasYEstatusResponse>(_ConsultarAudienciasByFechasYEstatusResponse_QNAME, ConsultarAudienciasByFechasYEstatusResponse.class, null, value);
    }

}
