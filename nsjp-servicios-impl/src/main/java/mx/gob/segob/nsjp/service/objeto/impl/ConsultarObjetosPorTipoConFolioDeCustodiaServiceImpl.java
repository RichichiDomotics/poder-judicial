/**
 *
 * Nombre del Programa : ConsultarObjetosPorTipoConFolioDeCustodiaServiceImpl.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 14/09/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Implementación del servicio para la búsqueda de objetos por tipo                         
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */

package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.dto.elemento.RelacionReincidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.service.cadenadecustodia.impl.transform.CadenaDeCustodiaTransformer;
import mx.gob.segob.nsjp.service.elemento.ConsultarRelacionesDeReincidenciaXElementoService;
import mx.gob.segob.nsjp.service.objeto.ConsultarObjetosPorTipoConFolioDeCustodiaService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObjetoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Busca Objetos por tipo.
 * 
 * @version 1.0
 * @author rgama
 * @version 1.0
 * 
 */
@Service
@Transactional
public class ConsultarObjetosPorTipoConFolioDeCustodiaServiceImpl implements ConsultarObjetosPorTipoConFolioDeCustodiaService {
	
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(ConsultarObjetosPorTipoConFolioDeCustodiaServiceImpl.class);

    @Autowired
    private ObjetoDAO objetoDAO;
    @Autowired
    private CadenaDeCustodiaDAO cadenaDeCustodiaDAO;
    @Autowired
    private ConsultarRelacionesDeReincidenciaXElementoService consultarRelacionesDeReincidenciaXElementoService;
    
   
    
 	@Override
	public List<ObjetoDTO> obtenerObjetosPorTipo(Objetos tipoObjeto, ExpedienteDTO input)
			throws NSJPNegocioException {
		logger.info("/**** Consultar Objetos por tipo ****/");
		
        if(input == null || input.getExpedienteId() == null || input.getNumeroExpedienteId() == null
        		|| tipoObjeto == null || tipoObjeto.getValorId() == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }

		List<Objeto> objetosExpediente = new ArrayList<Objeto>();
		List<ObjetoDTO> objetosExpedienteDTO = new ArrayList<ObjetoDTO>();
		List<RelacionReincidenciaDTO> listaReincidenciasDTOs = new ArrayList<RelacionReincidenciaDTO>();
		
		if (input.getNumeroExpedienteId() != null) {
			objetosExpediente = objetoDAO
			.consultarObjetosPorTipoByExpediente(input.getExpedienteId(),tipoObjeto.getValorId());
		}

		if (objetosExpediente != null) {
		    logger.debug("objetosExpediente() :: "+objetosExpediente.size());
			for (Objeto objeto : objetosExpediente) {
				ObjetoDTO loObjetoDTO = ObjetoTransformer.transformarObjeto(objeto);
				//Se consulta el numero de folio de la cadena de custodia en caso de existir para el objeto
				loObjetoDTO.setCadenaDeCustodia(
						CadenaDeCustodiaTransformer.transformarCadenaDeCustodia(
								cadenaDeCustodiaDAO.consultarFolioDeCadenaXObjetoId(objeto.getElementoId(), input.getNumeroExpedienteId())));
				//Se consultan los casos relacionados a la reincidencia del objeto
					listaReincidenciasDTOs= consultarRelacionesDeReincidenciaXElementoService.consultarReincidenciasXElemento(objeto.getElementoId());
					for(RelacionReincidenciaDTO relacionReincidenciaDTO: listaReincidenciasDTOs){
						if(input.getCasoDTO()!=null && input.getCasoDTO().getNumeroGeneralCaso()!=null
						   && relacionReincidenciaDTO!=null && relacionReincidenciaDTO.getCaso()!=null
						   && relacionReincidenciaDTO.getCaso().getNumeroGeneralCaso()!=null
						   && !input.getCasoDTO().getNumeroGeneralCaso().equals(relacionReincidenciaDTO.getCaso().getNumeroGeneralCaso())){
							loObjetoDTO.addNumeroDeCaso(relacionReincidenciaDTO.getCaso().getNumeroGeneralCaso());
						}
					}

				objetosExpedienteDTO.add(loObjetoDTO);
			}
		}
		logger.debug("objetosExpedienteDTO.size() :: "+objetosExpedienteDTO.size());
		return objetosExpedienteDTO;
	}
}
