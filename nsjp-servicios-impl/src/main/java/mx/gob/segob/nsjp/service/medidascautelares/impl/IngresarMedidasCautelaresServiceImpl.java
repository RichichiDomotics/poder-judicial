/**
* Nombre del Programa : IngresarMedidasCautelaresServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del Servicio de Ingresar Medidas Cautelares
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
package mx.gob.segob.nsjp.service.medidascautelares.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.funcionario.TipoEspecialidad;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.FuncionarioAudiencia;
import mx.gob.segob.nsjp.model.MedidaCautelar;
import mx.gob.segob.nsjp.service.domicilio.impl.transform.DomicilioTransformer;
import mx.gob.segob.nsjp.service.infra.SSPClienteService;
import mx.gob.segob.nsjp.service.medidascautelares.IngresarMedidasCautelaresService;
import mx.gob.segob.nsjp.service.medidascautelares.impl.transform.MedidaCautelarTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del Servicio de Ingresar Medidas Cautelares
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class IngresarMedidasCautelaresServiceImpl implements
		IngresarMedidasCautelaresService {

	@Autowired
	private MedidaCautelarDAO medidaCautelarDAO; 
	@Autowired
	private SSPClienteService sspClientService;
	@Autowired
	private DomicilioDAO domDao;
	@Autowired
	private InvolucradoAudienciaDAO involucradoAudienciaDAO;
	@Autowired
	private GenerarFolioSolicitudService generarFolioSolicitudService; 
	private final static Logger logger = Logger.getLogger(IngresarMedidasCautelaresServiceImpl.class);
	
	@Override
	public Long ingresarMedidaCautelar(MedidaCautelarDTO medidaDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO INGRESAR MEDIDAS CAUTELARES");

		MedidaCautelar medida = MedidaCautelarTransformer.transformarMedidaCautelar(medidaDTO);
		medida.setFolioDocumento(generarFolioSolicitudService.generarFoliodDocumento());
        if (medida.getDomicilio() != null) {
            Domicilio nuevoDom = DomicilioTransformer.transformarDomicilio(medidaDTO
                    .getDomicilio());
            nuevoDom.setElementoId(domDao.create(nuevoDom));
            logger.debug("Domicilio guardado :: " + nuevoDom.getElementoId());
            medida.setDomicilio(nuevoDom);
            
        }
		//Encontrar el juez que ordena
        
        List<Audiencia> audienciasInvolucrado = involucradoAudienciaDAO.consultarAudienciasDeInvolucrado(medida.getInvolucrado().getElementoId());
        if(audienciasInvolucrado.size()>0){
        	Audiencia audienciaInv = audienciasInvolucrado.get(0);
        	for(FuncionarioAudiencia funcionario:audienciaInv.getFuncionarioAudiencias()){
				if(funcionario.getFuncionario().getTipoEspecialidad() != null &&
						TipoEspecialidad.JUEZ.getValorId().equals(funcionario.getFuncionario().getTipoEspecialidad().getValorId())){
					medida.setJuezOrdena(funcionario.getFuncionario().getNombreCompleto());
					break;
				}
			}
        }
        if(medida.getJuezOrdena() == null){
        	medida.setJuezOrdena(StringUtils.EMPTY);
        }
        
		Long idMedida = this.medidaCautelarDAO.create(medida);
		
		return idMedida;
	}

    @Override
    public void desactivarMedidaCautelar(MedidaCautelarDTO medidaCautelar)
            throws NSJPNegocioException {
        
        if (medidaCautelar.getDocumentoId()==null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        
       MedidaCautelar pojoBd = this.medidaCautelarDAO.read(medidaCautelar.getDocumentoId());
       
       if (pojoBd==null){
           throw new NSJPNegocioException(CodigoError.EJCUCION_OPERACION_ESTADO_INCORRECTO);
       }
        
       pojoBd.setEsActivo(Boolean.FALSE);
       
    }
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.medidascautelares.IngresarMedidasCautelaresService#enviarMedidaCautelarSSP(java.lang.Long)
     */
	@Override
	public void enviarMedidaCautelarSSP(Long medidaId) {
		MedidaCautelar medida = medidaCautelarDAO.read(medidaId);
		if(medida != null){
			try {
				logger.debug("Enviando medida cautelar : " + medidaId);
				medida.setNumeroCaso(medida.getNumeroExpediente().getExpediente().getCaso().getNumeroGeneralCaso());
				medida.setNumeroCausa(medida.getNumeroExpediente().getNumeroExpediente());
				sspClientService.enviarMedidaCautelar(medida);
				logger.debug("Medida cautelar enviada a SSP: " + medidaId);
			} catch (NSJPNegocioException e) {
				logger.error(e.getMessage(),e);
			}
		}
	}

	
}
