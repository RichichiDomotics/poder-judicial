
package mx.gob.segob.nsjp.ws.cliente.generaracuse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.generaracuse package. 
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

    private final static QName _GenerarAcuseDeReciboResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "generarAcuseDeReciboResponse");
    private final static QName _GenerarAcuseDeRecibo_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "generarAcuseDeRecibo");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.generaracuse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenerarAcuseDeReciboResponse }
     * 
     */
    public GenerarAcuseDeReciboResponse createGenerarAcuseDeReciboResponse() {
        return new GenerarAcuseDeReciboResponse();
    }

    /**
     * Create an instance of {@link GenerarAcuseDeRecibo }
     * 
     */
    public GenerarAcuseDeRecibo createGenerarAcuseDeRecibo() {
        return new GenerarAcuseDeRecibo();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarAcuseDeReciboResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "generarAcuseDeReciboResponse")
    public JAXBElement<GenerarAcuseDeReciboResponse> createGenerarAcuseDeReciboResponse(GenerarAcuseDeReciboResponse value) {
        return new JAXBElement<GenerarAcuseDeReciboResponse>(_GenerarAcuseDeReciboResponse_QNAME, GenerarAcuseDeReciboResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarAcuseDeRecibo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "generarAcuseDeRecibo")
    public JAXBElement<GenerarAcuseDeRecibo> createGenerarAcuseDeRecibo(GenerarAcuseDeRecibo value) {
        return new JAXBElement<GenerarAcuseDeRecibo>(_GenerarAcuseDeRecibo_QNAME, GenerarAcuseDeRecibo.class, null, value);
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
