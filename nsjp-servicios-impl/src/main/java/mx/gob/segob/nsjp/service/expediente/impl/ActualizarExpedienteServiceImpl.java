/**
* Nombre del Programa : ActualizarExpedienteServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/08/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.catalogo.CatDiscriminateDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.ActualizarExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio que permite la actualización del Expediente.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class ActualizarExpedienteServiceImpl implements
		ActualizarExpedienteService {

    private static final Logger logger = Logger.getLogger(ActualizarTipoExpedienteServiceImpl.class);
    
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;  
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private CatDiscriminateDAO catDiscriminateDAO;
    
	@Override
	public ExpedienteDTO actualizarEstatusExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		logger.info("Servicio actualizarEstatusExpediente");
		if( expedienteDTO == null || expedienteDTO.getNumeroExpedienteId()==null ||
				expedienteDTO.getEstatus()== null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		logger.info("Datos Entrada: ");
		logger.info("NumeroExpedienteId: "+expedienteDTO.getNumeroExpedienteId());
		logger.info("Estatus: "+expedienteDTO.getEstatus());
		NumeroExpediente numeroExpediente = numeroExpedienteDAO.read(expedienteDTO.getNumeroExpedienteId());
		logger.info("Datos BD: ");
//		logger.info("Estatus: "+numeroExpediente.getEstatus());
		if(numeroExpediente.getEstatus()!= null)
			logger.info("Estatus: "+numeroExpediente.getEstatus().getValorId());
		
		if(numeroExpediente==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);

		//Se settean los valores
		numeroExpediente.setEstatus(new Valor(expedienteDTO.getEstatus().getIdCampo()));
		
		logger.info("Actualizar...");
		numeroExpedienteDAO.update(numeroExpediente);
		logger.info("Actualizado..." + numeroExpediente.getEstatus().getValorId());
		
		expedienteDTO = ExpedienteTransformer.transformarExpedienteBasico(numeroExpediente);
		
		return expedienteDTO;
	}

	
	@Override
	public Boolean actualizarCatDiscriminanteDeExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {

		try{

			if (expedienteDTO == null || expedienteDTO.getNumeroExpedienteId() == null) {
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
}

			if (expedienteDTO.getDiscriminante() == null
					|| expedienteDTO.getDiscriminante().getCatDiscriminanteId() == null) {
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			}
			
			Long expedienteIdObtenido = expedienteDAO.obtenerExpedienteIdPorIdNumerExpediente(expedienteDTO.getNumeroExpedienteId());
			
			if(expedienteIdObtenido != null){
				
				CatDiscriminante catDiscriminante = new CatDiscriminante();
				
				catDiscriminante = catDiscriminateDAO.consultarDiscriminantePorId(expedienteDTO.getDiscriminante().getCatDiscriminanteId());
				
				if(catDiscriminante != null){
					
					Expediente expedienteBD = new Expediente();
					expedienteBD = expedienteDAO.read(expedienteIdObtenido);
					if(expedienteBD != null){
						
						expedienteBD.setDiscriminante(catDiscriminante);
						expedienteDAO.update(expedienteBD);
						
						return true;
					}else{
						return false;
					}
				}
				else{
					return false;
				}
			}else{
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}
}
