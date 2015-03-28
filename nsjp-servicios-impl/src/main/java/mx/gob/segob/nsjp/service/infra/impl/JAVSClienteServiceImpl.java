/**
* Nombre del Programa : JAVSClienteServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Nov 2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.infra.impl;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import mx.gob.segob.nsjp.comun.constants.ConstantesGenerales;
import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.ws.cliente.agendaraudienciajavs.Principal;
import mx.gob.segob.nsjp.ws.cliente.agendaraudienciajavs.PrincipalSoap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del cliente del ws.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class JAVSClienteServiceImpl implements
		mx.gob.segob.nsjp.service.infra.JAVSClienteService {
	/**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(JAVSClienteServiceImpl.class);
          
    @Autowired
    private ParametroDAO parametroDAO;
    
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.infra.JAVSClienteService#agendarAudiencia(java.lang.Long)
	 */
	@Override
	public Long agendarAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException {
		 	
		logger.debug("JAVSClienteService - agendarAudiencia ");
		logger.debug("idAudiencia = " + idAudiencia);
		logger.debug("claveUsuario = " + claveUsuario);

		URL ep;
		long idEvento =0L;
		if(idAudiencia==null || idAudiencia<0 || claveUsuario==null || claveUsuario.trim().isEmpty())
			return idEvento = (long) ConstantesGenerales.FALLO_CONEXION_WEB_SERVICE_JAVS;
		
		try {
			String rutaJAVS = 
				parametroDAO.obtenerPorClave(Parametros.RUTA_JAVS).getValor();
			logger.info(" Ruta:"+ rutaJAVS);
			ep = new URL(
						rutaJAVS 
						+ "/Principal.asmx?wsdl");  
			//Los nombres se obtienen de PrincipalSoap_PrincipalSoap_Client generado
			final QName SERVICE_NAME = new QName("http://tempuri.org/", "Principal");
			
			// Configuracion para acceder al web service
			Principal  principal = new Principal(ep, SERVICE_NAME);
			
			PrincipalSoap proxy =  principal.getPrincipalSoap();
			
			logger.info(" Inicia la comunicacion con el WS: "+ proxy);
			idEvento = proxy.agendarAudiencia(idAudiencia.toString(), claveUsuario);
			logger.info(" Evento: "+ idEvento);
			
			return  idEvento;
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.infra.JAVSClienteService#consultarAudiencia(java.lang.Long)
	 */
	@Override
	public Long consultarAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException {
		 	
		logger.debug("JAVSClienteService - consultarAudiencia ");
		logger.debug("idAudiencia = " + idAudiencia);
		logger.debug("claveUsuario = " + claveUsuario);

		URL ep;
		long idEvento =0L;
		if(idAudiencia==null || idAudiencia<0 || claveUsuario==null || claveUsuario.trim().isEmpty())
			return idEvento;
		
		try {
			String rutaJAVS = 
				parametroDAO.obtenerPorClave(Parametros.RUTA_JAVS).getValor();
			logger.info(" Ruta:"+ rutaJAVS);
			ep = new URL(
						rutaJAVS 
						+ "/Principal.asmx?wsdl");  
			//Los nombres se obtienen de PrincipalSoap_PrincipalSoap_Client generado
			final QName SERVICE_NAME = new QName("http://tempuri.org/", "Principal");
			
			// Configuracion para acceder al web service
			logger.info(" Inicia la comunicacion con el WS: "+ ep);
			Principal  principal = new Principal(ep, SERVICE_NAME);
			logger.info(" Inicia la comunicacion con el WS: "+ principal);
			PrincipalSoap proxy =  principal.getPrincipalSoap();
			
			logger.info(" Inicia la comunicacion con el WS: "+ proxy);
			idEvento = proxy.consultarAudiencia(idAudiencia.toString(), claveUsuario);
			logger.info(" Evento: "+ idEvento);
			
			return idEvento;
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.infra.JAVSClienteService#eliminarAudiencia(java.lang.Long)
	 */
	@Override
	public Long eliminarAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException {
		 	
		logger.debug("JAVSClienteService - eliminarAudiencia ");
		logger.debug("idAudiencia = " + idAudiencia);
		logger.debug("claveUsuario = " + claveUsuario);
		URL ep;
		Long idEvento =0L;
		if(idAudiencia==null || idAudiencia<0 || claveUsuario==null || claveUsuario.trim().isEmpty())
			return idEvento;
		
		try {
			String rutaJAVS = 
				parametroDAO.obtenerPorClave(Parametros.RUTA_JAVS).getValor();
			logger.info(" Ruta:"+ rutaJAVS);
			ep = new URL(
						rutaJAVS 
						+ "/Principal.asmx?wsdl");  
			//Los nombres se obtienen de PrincipalSoap_PrincipalSoap_Client generado
			final QName SERVICE_NAME = new QName("http://tempuri.org/", "Principal");
			
			// Configuracion para acceder al web service
			Principal  principal = new Principal(ep, SERVICE_NAME);
			
			PrincipalSoap proxy =  principal.getPrincipalSoap();
			
			logger.info(" Inicia la comunicacion con el WS: "+ proxy);
			idEvento = (long) proxy.eliminarAudiencia(idAudiencia.toString(), claveUsuario);
			logger.info(" Evento: "+ idEvento);
			
			return idEvento;
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.infra.JAVSClienteService#estadoAudiencia(java.lang.Long)
	 */
	@Override
	public Long estadoAudiencia(Long idAudiencia, String claveUsuario) throws NSJPNegocioException {
		 	
		logger.debug("JAVSClienteService - estadoAudiencia ");
		logger.debug("idAudiencia = " + idAudiencia);
		logger.debug("claveUsuario = " + claveUsuario);
		URL ep;
		Long idEvento = 0L;
		if(idAudiencia==null || idAudiencia<0 || claveUsuario==null || claveUsuario.trim().isEmpty())
			return idEvento;
		
		try {
			String rutaJAVS = 
				parametroDAO.obtenerPorClave(Parametros.RUTA_JAVS).getValor();
			logger.info(" Ruta:"+ rutaJAVS);
			ep = new URL(
						rutaJAVS 
						+ "/Principal.asmx?wsdl");  
			//Los nombres se obtienen de PrincipalSoap_PrincipalSoap_Client generado
			final QName SERVICE_NAME = new QName("http://tempuri.org/", "Principal");
			
			// Configuracion para acceder al web service
			Principal  principal = new Principal(ep, SERVICE_NAME);
			
			PrincipalSoap proxy =  principal.getPrincipalSoap();
			
			logger.info(" Inicia la comunicacion con el WS: "+ proxy);
			idEvento = (long) proxy.estadoAudiencia(idAudiencia.toString(), claveUsuario);
			logger.info(" Evento: "+ idEvento);
			
			return idEvento;
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			throw new NSJPNegocioException(CodigoError.ERROR_COMUNICACION, e);
		}
	}
}
