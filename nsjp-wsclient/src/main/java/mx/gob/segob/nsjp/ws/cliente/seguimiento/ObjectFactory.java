
package mx.gob.segob.nsjp.ws.cliente.seguimiento;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.seguimiento package. 
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

    private final static QName _ActualizarSeguimientoResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "actualizarSeguimientoResponse");
    private final static QName _ActualizarSeguimiento_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "actualizarSeguimiento");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.seguimiento
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActualizarSeguimientoResponse }
     * 
     */
    public ActualizarSeguimientoResponse createActualizarSeguimientoResponse() {
        return new ActualizarSeguimientoResponse();
    }

    /**
     * Create an instance of {@link ActualizarSeguimiento }
     * 
     */
    public ActualizarSeguimiento createActualizarSeguimiento() {
        return new ActualizarSeguimiento();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link ArchivoDigitalWSDTO }
     * 
     */
    public ArchivoDigitalWSDTO createArchivoDigitalWSDTO() {
        return new ArchivoDigitalWSDTO();
    }

    /**
     * Create an instance of {@link SeguimientoMandamientoMedidaWSDTO }
     * 
     */
    public SeguimientoMandamientoMedidaWSDTO createSeguimientoMandamientoMedidaWSDTO() {
        return new SeguimientoMandamientoMedidaWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarSeguimientoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "actualizarSeguimientoResponse")
    public JAXBElement<ActualizarSeguimientoResponse> createActualizarSeguimientoResponse(ActualizarSeguimientoResponse value) {
        return new JAXBElement<ActualizarSeguimientoResponse>(_ActualizarSeguimientoResponse_QNAME, ActualizarSeguimientoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActualizarSeguimiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "actualizarSeguimiento")
    public JAXBElement<ActualizarSeguimiento> createActualizarSeguimiento(ActualizarSeguimiento value) {
        return new JAXBElement<ActualizarSeguimiento>(_ActualizarSeguimiento_QNAME, ActualizarSeguimiento.class, null, value);
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
