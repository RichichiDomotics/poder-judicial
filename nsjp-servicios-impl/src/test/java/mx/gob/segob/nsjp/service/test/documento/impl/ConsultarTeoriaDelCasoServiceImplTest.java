/**
 * 
 */
package mx.gob.segob.nsjp.service.test.documento.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.CuerpoOficioEstructuradoDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarTeoriaDelCasoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarTeoriaDelCasoServiceImplTest extends BaseTestServicios<ConsultarTeoriaDelCasoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.documento.impl.ConsultarTeoriaDelCasoServiceImpl#consultarTeoriasDelCasoXExpediente(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO)}.
	 */
	public void testConsultarTeoriasDelCasoXExpediente() {
		
		ExpedienteDTO expedienteDTO=new ExpedienteDTO(13L);
		try {
			DocumentoDTO doc = service.consultarTeoriasDelCasoXExpediente(expedienteDTO);
			assertNotNull(doc);
				logger.info("------------------");
				logger.info("ID: "+doc.getDocumentoId());
				logger.info("Tipo: "+doc.getTipoDocumentoDTO().getValor());
				logger.info("Oficio: "+doc.getOficioEstructuradoDTO().getEncabezado());
				logger.info("Cuerpos: "+doc.getOficioEstructuradoDTO().getCuerposOficio().size());
				if(doc.getOficioEstructuradoDTO().getCuerposOficio().size()>0){
					for (CuerpoOficioEstructuradoDTO coe : doc.getOficioEstructuradoDTO().getCuerposOficio()) {
						logger.info("Cuerpo id:"+coe.getCuerpoOficioEstructuradoId());
						logger.info("texto:"+coe.getTexto());
						logger.info("Indice estruct: "+coe.getIndiceEstructurado());	
					}
				}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
