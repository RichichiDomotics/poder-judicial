
package mx.gob.segob.nsjp.ws.cliente.registarreplicacaso;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.segob.nsjp.ws.cliente.registarreplicacaso package. 
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
    private final static QName _RegistrarReplicaCasoResponse_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarReplicaCasoResponse");
    private final static QName _RegistrarReplicaCaso_QNAME = new QName("http://ws.service.nsjp.segob.gob.mx/", "registrarReplicaCaso");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.segob.nsjp.ws.cliente.registarreplicacaso
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
     * Create an instance of {@link RegistrarReplicaCasoResponse }
     * 
     */
    public RegistrarReplicaCasoResponse createRegistrarReplicaCasoResponse() {
        return new RegistrarReplicaCasoResponse();
    }

    /**
     * Create an instance of {@link RegistrarReplicaCaso }
     * 
     */
    public RegistrarReplicaCaso createRegistrarReplicaCaso() {
        return new RegistrarReplicaCaso();
    }

    /**
     * Create an instance of {@link PersonaWSDTO }
     * 
     */
    public PersonaWSDTO createPersonaWSDTO() {
        return new PersonaWSDTO();
    }

    /**
     * Create an instance of {@link CalidadWSDTO }
     * 
     */
    public CalidadWSDTO createCalidadWSDTO() {
        return new CalidadWSDTO();
    }

    /**
     * Create an instance of {@link SeniaParticularWSDTO }
     * 
     */
    public SeniaParticularWSDTO createSeniaParticularWSDTO() {
        return new SeniaParticularWSDTO();
    }

    /**
     * Create an instance of {@link ArchivoDigitalWSDTO }
     * 
     */
    public ArchivoDigitalWSDTO createArchivoDigitalWSDTO() {
        return new ArchivoDigitalWSDTO();
    }

    /**
     * Create an instance of {@link NombreDemograficoWSDTO }
     * 
     */
    public NombreDemograficoWSDTO createNombreDemograficoWSDTO() {
        return new NombreDemograficoWSDTO();
    }

    /**
     * Create an instance of {@link CorreoElectronicoWSDTO }
     * 
     */
    public CorreoElectronicoWSDTO createCorreoElectronicoWSDTO() {
        return new CorreoElectronicoWSDTO();
    }

    /**
     * Create an instance of {@link CasoWSDTO }
     * 
     */
    public CasoWSDTO createCasoWSDTO() {
        return new CasoWSDTO();
    }

    /**
     * Create an instance of {@link ElementoWSDTO }
     * 
     */
    public ElementoWSDTO createElementoWSDTO() {
        return new ElementoWSDTO();
    }

    /**
     * Create an instance of {@link OrganizacionWSDTO }
     * 
     */
    public OrganizacionWSDTO createOrganizacionWSDTO() {
        return new OrganizacionWSDTO();
    }

    /**
     * Create an instance of {@link GenericWSDTO }
     * 
     */
    public GenericWSDTO createGenericWSDTO() {
        return new GenericWSDTO();
    }

    /**
     * Create an instance of {@link DelitoWSDTO }
     * 
     */
    public DelitoWSDTO createDelitoWSDTO() {
        return new DelitoWSDTO();
    }

    /**
     * Create an instance of {@link LugarWSDTO }
     * 
     */
    public LugarWSDTO createLugarWSDTO() {
        return new LugarWSDTO();
    }

    /**
     * Create an instance of {@link MediaFiliacionWSDTO }
     * 
     */
    public MediaFiliacionWSDTO createMediaFiliacionWSDTO() {
        return new MediaFiliacionWSDTO();
    }

    /**
     * Create an instance of {@link TelefonoWSDTO }
     * 
     */
    public TelefonoWSDTO createTelefonoWSDTO() {
        return new TelefonoWSDTO();
    }

    /**
     * Create an instance of {@link DomicilioWSDTO }
     * 
     */
    public DomicilioWSDTO createDomicilioWSDTO() {
        return new DomicilioWSDTO();
    }

    /**
     * Create an instance of {@link InmediacionWSDTO }
     * 
     */
    public InmediacionWSDTO createInmediacionWSDTO() {
        return new InmediacionWSDTO();
    }

    /**
     * Create an instance of {@link DetencionWSDTO }
     * 
     */
    public DetencionWSDTO createDetencionWSDTO() {
        return new DetencionWSDTO();
    }

    /**
     * Create an instance of {@link InvolucradoWSDTO }
     * 
     */
    public InvolucradoWSDTO createInvolucradoWSDTO() {
        return new InvolucradoWSDTO();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarReplicaCasoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarReplicaCasoResponse")
    public JAXBElement<RegistrarReplicaCasoResponse> createRegistrarReplicaCasoResponse(RegistrarReplicaCasoResponse value) {
        return new JAXBElement<RegistrarReplicaCasoResponse>(_RegistrarReplicaCasoResponse_QNAME, RegistrarReplicaCasoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarReplicaCaso }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.service.nsjp.segob.gob.mx/", name = "registrarReplicaCaso")
    public JAXBElement<RegistrarReplicaCaso> createRegistrarReplicaCaso(RegistrarReplicaCaso value) {
        return new JAXBElement<RegistrarReplicaCaso>(_RegistrarReplicaCaso_QNAME, RegistrarReplicaCaso.class, null, value);
    }

}
