
package mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion package. 
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

    private final static QName _EnviarDocumentoAInstitucionResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "enviarDocumentoAInstitucionResponse");
    private final static QName _NSJPNegocioException_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "NSJPNegocioException");
    private final static QName _EnviarDocumentoAInstitucion_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "enviarDocumentoAInstitucion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.enviardocumentoainstitucion
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviarDocumentoAInstitucionResponse }
     * 
     */
    public EnviarDocumentoAInstitucionResponse createEnviarDocumentoAInstitucionResponse() {
        return new EnviarDocumentoAInstitucionResponse();
    }

    /**
     * Create an instance of {@link NSJPNegocioException }
     * 
     */
    public NSJPNegocioException createNSJPNegocioException() {
        return new NSJPNegocioException();
    }

    /**
     * Create an instance of {@link EnviarDocumentoAInstitucion }
     * 
     */
    public EnviarDocumentoAInstitucion createEnviarDocumentoAInstitucion() {
        return new EnviarDocumentoAInstitucion();
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
     * Create an instance of {@link ActividadWSDTO }
     * 
     */
    public ActividadWSDTO createActividadWSDTO() {
        return new ActividadWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarDocumentoAInstitucionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "enviarDocumentoAInstitucionResponse")
    public JAXBElement<EnviarDocumentoAInstitucionResponse> createEnviarDocumentoAInstitucionResponse(EnviarDocumentoAInstitucionResponse value) {
        return new JAXBElement<EnviarDocumentoAInstitucionResponse>(_EnviarDocumentoAInstitucionResponse_QNAME, EnviarDocumentoAInstitucionResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarDocumentoAInstitucion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "enviarDocumentoAInstitucion")
    public JAXBElement<EnviarDocumentoAInstitucion> createEnviarDocumentoAInstitucion(EnviarDocumentoAInstitucion value) {
        return new JAXBElement<EnviarDocumentoAInstitucion>(_EnviarDocumentoAInstitucion_QNAME, EnviarDocumentoAInstitucion.class, null, value);
    }

}
