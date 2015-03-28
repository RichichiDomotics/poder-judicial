/**
* Nombre del Programa : ConsultarDelitoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 9 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para la consulta de los delitos registrados
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
package mx.gob.segob.nsjp.service.delito.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.delito.DelitoDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.parametro.ParametroDAO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Parametro;
import mx.gob.segob.nsjp.service.delito.ConsultarDelitoService;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para la consulta de los delitos registrados.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarDelitoServiceImpl implements ConsultarDelitoService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(ConsultarDelitoServiceImpl.class);
	
	@Autowired
	private DelitoDAO delitoDAO;
	@Autowired
	private ExpedienteDAO expedienteDAO;
	@Autowired
	private ParametroDAO parametroDAO;
	
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.delito.ConsultarDelitoService#consultarDelitoExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)
	 */
	@Override
	public List<DelitoDTO> consultarDelitoExpediente(ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		if (expedienteDTO.getExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA BUSCAR LOS DELITOS REGISTRADOS A UN EXPEDIENTE ****/");
		
		List<Delito> listDelito = delitoDAO.obtenerDelitoPorExpediente(expedienteDTO.getExpedienteId());
		List<DelitoDTO> listDelitoDTO = new ArrayList<DelitoDTO>();
		for (Delito delito : listDelito) {
			listDelitoDTO.add(DelitoTransfromer.transformarDelito(delito));
		}
		
		return listDelitoDTO;
	}


	@Override
	public Boolean consultarMediaAritmeticaDelitosExpediente(
			ExpedienteDTO inputExpDto) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("/****BIENVENIDO AL SERVICIO PARA CONSULTAR MEDIA ARITMETICA DE LOS DELITOS DE UN EXPEDIENTE ****/");
}

		if (inputExpDto == null || inputExpDto.getNumeroExpedienteId() == null) {
			logger.info("PARAMETROS DE ENTRADA INSUFICIENTES");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		Long expedienteIdRecuperado = expedienteDAO
				.obtenerExpedienteIdPorIdNumerExpediente(inputExpDto
						.getNumeroExpedienteId());

		if (expedienteIdRecuperado == null) {
			logger.info("BUSQUE EL EXPEDIENTE PERO NO EXISTE");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}

		logger.info("EXPEDIENTE ID RECUPERADO::::::" + expedienteIdRecuperado);
		// Recuperamos la lista de delitos asociados a ese expediente
		List<Delito> listaDelitosExp = delitoDAO
				.obtenerListaDeDelitoPorExpediente(expedienteIdRecuperado);

		if (listaDelitosExp == null || listaDelitosExp.size() == 0) {
			// throw new
			// NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			logger.info("LISTA DE DELITOS NULA O DE TAMAÑO CERO");
			return null;
		}
		
		// Media Aritmetica permitida
		Parametro mediaObtenidaParametro = parametroDAO
				.obtenerPorClave(Parametros.MEDIA_ARITMETICA_DELITOS);
		if (mediaObtenidaParametro == null
				|| mediaObtenidaParametro.getValor() == null
				|| NumberUtils.toDouble(mediaObtenidaParametro.getValor(), 0.0) == 0.0) {
			logger.info("NO SE PUEDE OBTENER LA MEDIA ARITMETICA DE BD  O ES CERO");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		
		//Valor de retorno
		Boolean excedeMedia = false;
		//Pena minima del delito en dias
		Double penaMinTotal = 0.0;
		//Pena maxima del delito en dias
		Double penaMaxTotal = 0.0;
		//Media Aritmetica esta dada en dias, ya que es la minima escala
		Double mediaAritmetica = 0.0;			
		
		for(Delito delito:listaDelitosExp){
			logger.info("CALCULANDO LA MEDIA");
			
			if(delito.getCatDelito() == null){
				return null;
			}else{
				
				penaMinTotal += (delito.getCatDelito().getPenaMinimaAnios() * 365);
				penaMinTotal += (delito.getCatDelito().getPenaMinimaMeses() * 30); 
				penaMinTotal += (delito.getCatDelito().getPenaMinimaDias());

				//Pena maxima del delito					
				penaMaxTotal += (delito.getCatDelito().getPenaMaximaAnios() * 365) ;
				penaMaxTotal += (delito.getCatDelito().getPenaMaximaMeses() * 30); 
				penaMaxTotal += delito.getCatDelito().getPenaMaximaDias();
				
				logger.info("Parcial:::penaMinTotal es::::::"+penaMinTotal);
				logger.info("Parcial:::penaMaxTotal es::::::"+penaMaxTotal);
			}
		}
		logger.info("Total:::::penaMin::::::"+penaMinTotal);
		logger.info("Total:::::penaMax::::::"+penaMaxTotal);
		
		mediaAritmetica = ((penaMinTotal + penaMaxTotal)/ (listaDelitosExp.size()* 2));
		
		logger.info("El valor de la media es::::::"+mediaAritmetica);
		
		if(mediaAritmetica > NumberUtils.toDouble(mediaObtenidaParametro.getValor(),0.0)){
			excedeMedia = true;
		}
		return excedeMedia;
	}

}
