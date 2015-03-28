/**
 * 
 */
package mx.gob.segob.nsjp.service.test.cadenadecustodia.impl;

import java.util.Iterator;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaCustodiaXAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarCadenaCustodiaXAlmacenServiceImplTest extends
		BaseTestServicios<ConsultarCadenaCustodiaXAlmacenService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.cadenadecustodia.impl.ConsultarCadenaCustodiaXAlmacenServiceImpl#consultarCadenaCustodiaXAlmacen(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)}.
	 */
	public void testConsultarCadenaCustodiaXAlmacen() {
		try {
			List<CadenaDeCustodiaDTO> cadenas = service.consultarCadenaCustodiaXAlmacen(new AlmacenDTO(1L),new CasoDTO(4L));
			assertNotNull("cadenaDeCustodia", cadenas);
			logger.info("Existen " + cadenas.size() + " cadenas");
			for (CadenaDeCustodiaDTO dto : cadenas) {
				logger.info("----------------------------");
				logger.info("ID: " + dto.getCadenaDeCustodiaId());
				if(dto.getExpedienteDTO()!=null){
					logger.info("Expediente: " + dto.getExpedienteDTO().getExpedienteId());
					logger.info("NumExp: " + dto.getExpedienteDTO().getNumeroExpedienteId()+" ("+dto.getExpedienteDTO().getNumeroExpediente()+")");
					logger.info("Caso: " + dto.getExpedienteDTO().getCasoDTO().getCasoId());
				}
				
				assertNotNull("cadenaDeCustodia.getFolio()", dto.getFolio());
				logger.debug("cadenaDeCustodia.getFolio() = " + dto.getFolio());
				if (dto.getEvidencias() != null) {
					logger.info("Evidencias" + dto.getEvidencias().size());
					for (EvidenciaDTO evi : dto.getEvidencias()) {
						logger.info("*********************************");
						logger.info("Evidencia id"+evi.getEvidenciaId());
						logger.info("estatus Evidencia: "+evi.getEstatus());
						logger.info("Cod Barras"+ evi.getCodigoBarras());
						if (evi.getObjeto() != null){
							ObjetoDTO obj = evi.getObjeto();
							logger.info("Objeto id: "
									+ obj.getElementoId());
							logger.info("Almacen id: "+obj.getAlmacen());
							
						}
						if(evi.getEslabones()!=null){
							logger.info("Existen "+evi.getEslabones().size()+ " eslabones");
							Iterator<EslabonDTO> itEslab = evi.getEslabones().iterator();
							while (itEslab.hasNext()) {
								EslabonDTO eslabonDTO = (EslabonDTO) itEslab.next();
								logger.info("Eslabon: "+eslabonDTO);
							}
							
						}
					}
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
