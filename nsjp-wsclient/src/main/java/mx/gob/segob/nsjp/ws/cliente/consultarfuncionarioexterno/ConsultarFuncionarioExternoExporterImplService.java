package mx.gob.segob.nsjp.ws.cliente.consultarfuncionarioexterno;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.4.1
 * 2012-04-11T14:53:47.897-05:00
 * Generated source version: 2.4.1
 * 
 */
@WebServiceClient(name = "ConsultarFuncionarioExternoExporterImplService", 
                  wsdlLocation = "http://localhost:8080/nsjp-web/ConsultarFuncionarioExternoExporterImplService?wsdl",
                  targetNamespace = "http://impl.ws.service.nsjp.segob.gob.mx/") 
public class ConsultarFuncionarioExternoExporterImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://impl.ws.service.nsjp.segob.gob.mx/", "ConsultarFuncionarioExternoExporterImplService");
    public final static QName ConsultarFuncionarioExternoExporterImplPort = new QName("http://impl.ws.service.nsjp.segob.gob.mx/", "ConsultarFuncionarioExternoExporterImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/nsjp-web/ConsultarFuncionarioExternoExporterImplService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ConsultarFuncionarioExternoExporterImplService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/nsjp-web/ConsultarFuncionarioExternoExporterImplService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ConsultarFuncionarioExternoExporterImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ConsultarFuncionarioExternoExporterImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ConsultarFuncionarioExternoExporterImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ConsultarFuncionarioExternoExporter
     */
    @WebEndpoint(name = "ConsultarFuncionarioExternoExporterImplPort")
    public ConsultarFuncionarioExternoExporter getConsultarFuncionarioExternoExporterImplPort() {
        return super.getPort(ConsultarFuncionarioExternoExporterImplPort, ConsultarFuncionarioExternoExporter.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ConsultarFuncionarioExternoExporter
     */
    @WebEndpoint(name = "ConsultarFuncionarioExternoExporterImplPort")
    public ConsultarFuncionarioExternoExporter getConsultarFuncionarioExternoExporterImplPort(WebServiceFeature... features) {
        return super.getPort(ConsultarFuncionarioExternoExporterImplPort, ConsultarFuncionarioExternoExporter.class, features);
    }

}
