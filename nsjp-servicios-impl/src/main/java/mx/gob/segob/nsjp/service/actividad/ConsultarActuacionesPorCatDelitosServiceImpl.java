/**
 * Nombre del Programa : ConsultarActuacionesPorCatDelitosServiceImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 08 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación de CanalizarCausaService
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
package mx.gob.segob.nsjp.service.actividad;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.actividad.CatDelitoActuacionDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementación de CanalizarCausaServiceImpl
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@Service
@Transactional
public class ConsultarActuacionesPorCatDelitosServiceImpl implements ConsultarActuacionesPorCatDelitosService {
	
    @Autowired
    private CatDelitoActuacionDAO catDelitoActuacion;
    
    
    private final static Logger logger = Logger
    .getLogger(ConsultarActuacionesPorCatDelitosServiceImpl.class);
    
    
	public List<ValorDTO> consultarActividadesPorIdsCatDelito(List<Long> idsCatDelito) throws NSJPNegocioException{

		if(idsCatDelito == null || idsCatDelito.isEmpty()){			
			logger.info("La lista de CatDelitos no puede ser vacia");
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}		

		List<Valor> loActuaciones = catDelitoActuacion.consultarActuacionesPorIdsCatDelito(idsCatDelito);
		List<ValorDTO> loActuacionesDTO = new ArrayList<ValorDTO>();
		
		for (Valor loActuacionRow : loActuaciones) {
			System.out.println(loActuacionRow.getValorId());
			loActuacionesDTO.add(new ValorDTO(loActuacionRow.getValorId(),loActuacionRow.getValor()));
		}
		return loActuacionesDTO;		
	}

}
