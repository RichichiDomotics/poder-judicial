/**
* Nombre del Programa : ConsultarEslabonServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 30/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas Unitarias de los servicios asociados a la consulta de eslabones.
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
package mx.gob.segob.nsjp.service.test.eslabon.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.eslabon.ConsultarEslabonService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas Unitarias de los servicios asociados a la consulta de eslabones.
 * @version 1.0
 * @author GustavoBP
 */
public class ConsultarEslabonServiceImplTest extends BaseTestServicios<ConsultarEslabonService> {

	public void testConsultarEslabonesPorEvidencia(){
		logger.info("Probando el servicio de: consultarEslabonesPorEvidencia");
		List<EslabonDTO> eslabonesDTO = new ArrayList<EslabonDTO>();
		
		Long idEvidencia = 1L;
		
 		EvidenciaDTO evidenciaDTO = new EvidenciaDTO(idEvidencia);
		try {
			eslabonesDTO = service.consultarEslabonesPorEvidencia(evidenciaDTO );
			assertFalse("Se obtuvo una lista de eslabones vacia", eslabonesDTO.isEmpty() );
			for (EslabonDTO eslabonDTO : eslabonesDTO) {
				logger.info("eslabonDTO - EslabonId:" + eslabonDTO.getEslabonId());
				logger.info("eslabonDTO - Institucion Que entrega:" + eslabonDTO.getInstitucionQueEntrega());
				logger.info("eslabonDTO - Institucion Que reibe:" + eslabonDTO.getInstitucionQueRecibe());
				logger.info("eslabonDTO - Tipo de eslabon de Recepcion" + eslabonDTO.getTipoEslabonDeRecepcion());


				logger.info("eslabonDTO Datos de la Entrega ");
				logger.info("eslabonDTO - Nombre:"+ eslabonDTO.getFuncionariEntrega());
				logger.info("****eslabonDTO - Fecha Entrega:"+ eslabonDTO.getFechaInicioMovimiento());
				logger.info("****eslabonDTO - StrFecha Entrega:"+ eslabonDTO.getStrFechaEntrega());
				logger.info("****eslabonDTO - StrHora Entrega:"+ eslabonDTO.getStrHoraEntrega());
				logger.info("eslabonDTO - TipoEslabon:" + eslabonDTO.getTipoEslabon().getValor());

				
				logger.info("eslabonDTO Datos de la Recepcion ");
				logger.info("eslabonDTO - Nombre:"+ eslabonDTO.getFuncionariRecibe());
				logger.info("****eslabonDTO - FechaRecibe:" + eslabonDTO.getFechaFinMovimiento());
				logger.info("****eslabonDTO - StrFechaRecepcion:" + eslabonDTO.getStrFechaRecepcion());
				logger.info("****eslabonDTO - StrHoraRecepcion:" + eslabonDTO.getStrHoraRecepcion());
				
				logger.info("eslabonDTO - Observacion:" + eslabonDTO.getObservacion());
				logger.info("eslabonDTO - Quien entrega:" + eslabonDTO.getQuienEntrega());
				logger.info("eslabonDTO - Quien recibe:" + eslabonDTO.getQuienRecibe());
			}
		} catch (NSJPNegocioException e) {
			logger.info(e);
            fail("Ocurrio una excepcion al ejecutar el test de consultarEslabonesPorEvidencia");
		}
		
		
		
	}
	
	public void testTieneEslabonPorEvidenciaYObjeto(){
		Boolean tieneEslabon = false;
		Long idObjeto = 8310L;
		
		try {
			tieneEslabon = service.tieneEslabonPorEvidenciaYObjeto(idObjeto );
			assertFalse("No tiene Eslabones", tieneEslabon);
			logger.info("TieneEslabon: "+tieneEslabon);
		} catch (NSJPNegocioException e) {
			logger.info(e);
			fail("Ocurrio una excepcion al ejecutar el test de tieneEslabonPorEvidenciaYObjeto");
		}
		
	}
	
	public void testEsEvidenciaNoTieneEslabon(){
		Boolean esEvidenciaNoEsEslabon = false;
		Long idObjeto = 8310L;
		
		try {
			esEvidenciaNoEsEslabon = service.esEvidenciaNoTieneEslabon(idObjeto );
			logger.info("TieneEslabon: "+esEvidenciaNoEsEslabon);
			assertTrue("Es Evidencia No tiene Eslabon (true):", esEvidenciaNoEsEslabon);
		} catch (NSJPNegocioException e) {
			logger.info(e);
			fail("Ocurrio una excepcion al ejecutar el test de tieneEslabonPorEvidenciaYObjeto");
		}
		
	}
}
