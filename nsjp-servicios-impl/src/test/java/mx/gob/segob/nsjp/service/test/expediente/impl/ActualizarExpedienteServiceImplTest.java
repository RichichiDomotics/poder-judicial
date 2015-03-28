/**
* Nombre del Programa : ActualizarExpedienteServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.expediente.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.ActualizarExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria del Servicio para la actualizacion del Expediente 
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class ActualizarExpedienteServiceImplTest extends BaseTestServicios<ActualizarExpedienteService>{

	public void testActualizarEstatusExpediente(){
		
		Long numeroExpedienteId = 12L ;
		Long estatusId = 2288L;
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(numeroExpedienteId);
		expedienteDTO.setEstatus(new ValorDTO(estatusId));
		
		try {
			expedienteDTO = service.actualizarEstatusExpediente(expedienteDTO);
			logger.info("Expediente:"+ expedienteDTO.getEstatus());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
public void testActualizarCatDiscriminanteExpediente(){
		
		Long numeroExpedienteId = 90L ;
		Long discri = 1L;
		Boolean resp;
		
		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
		expedienteDTO.setNumeroExpedienteId(numeroExpedienteId);
		
		CatDiscriminanteDTO catDis = new CatDiscriminanteDTO();
		catDis.setCatDiscriminanteId(discri);
		expedienteDTO.setDiscriminante(catDis);
		
		try {
			resp = service.actualizarCatDiscriminanteDeExpediente(expedienteDTO);
			logger.info("Respuesta OBtenida:"+resp);
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
}
	}
	
}
