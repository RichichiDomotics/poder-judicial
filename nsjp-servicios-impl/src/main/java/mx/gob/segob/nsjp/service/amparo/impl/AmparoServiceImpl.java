/**
 * Nombre del Programa : AmparoServiceImpl.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 15-Feb-2012
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.amparo.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.relacion.RelacionDTO;
import mx.gob.segob.nsjp.service.amparo.AmparoService;
import mx.gob.segob.nsjp.service.elemento.ConsultarElementosXIdExpedienteCatRelacionService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author rgama
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AmparoServiceImpl implements AmparoService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(AmparoServiceImpl.class);

    
    @Autowired
    private ConsultarElementosXIdExpedienteCatRelacionService relacion;
	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;


	@Override
	public List<InvolucradoDTO> consultarInvolucradosXAmparo(Long idAmparo)
			throws NSJPNegocioException {
		 if (idAmparo == null) {
	            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
	        }	        
		 	logger.info("El id del Amparo recibido es: " + idAmparo);
		 	List<RelacionDTO> relaciones = relacion.consultarRelacionesByComplementoIdAndTipoRelacion(idAmparo, new Long(Relaciones.AMPARADO.ordinal()));
		 	List<InvolucradoDTO> involucrados = new ArrayList<InvolucradoDTO>();
	        
		 	if(relaciones != null)
		 		for (RelacionDTO relacionDTO : relaciones) {
		 			Long idElemento= relacionDTO.getElementoBySujetoId().getElementoId();
		 			involucrados.add(consultarIndividuoService.obtenerInvolucrado(new InvolucradoDTO(idElemento)));		 			
				}
	        return involucrados;
	}
   
}
