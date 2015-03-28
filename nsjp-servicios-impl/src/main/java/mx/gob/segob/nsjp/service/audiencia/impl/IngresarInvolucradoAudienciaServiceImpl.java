/**
* Nombre del Programa : IngresarInvolucradoAudienciaServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/09/2011
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
package mx.gob.segob.nsjp.service.audiencia.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.audiencia.InvolucradoAudienciaDAO;
import mx.gob.segob.nsjp.model.InvolucradoAudiencia;
import mx.gob.segob.nsjp.model.InvolucradoAudienciaId;
import mx.gob.segob.nsjp.service.audiencia.IngresarInvolucradoAudienciaService;

/**
 * Implementación del servicio de negocio para asociar un involucrado a una audiencia
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class IngresarInvolucradoAudienciaServiceImpl implements
		IngresarInvolucradoAudienciaService {
	@Autowired
	InvolucradoAudienciaDAO involucradoAudienciaDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.audiencia.IngresarInvolucradoAudienciaService#asociarInvolucradoAAudiencia(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void asociarInvolucradoAAudiencia(Long involucradoId,
			Long audienciaId) throws NSJPNegocioException {


		if(involucradoId != null && audienciaId != null){
			//si no está asociado a una audiencia, asociarlo
			InvolucradoAudienciaId identificador = new InvolucradoAudienciaId(audienciaId, involucradoId);
			if(involucradoAudienciaDAO.read(identificador)==null){
				InvolucradoAudiencia involucradoAud = new InvolucradoAudiencia();
				involucradoAud.setId(identificador);
				involucradoAud.setEsPresente(true);
				involucradoAudienciaDAO.create(involucradoAud);
			}
			
			
		}

	}

}
