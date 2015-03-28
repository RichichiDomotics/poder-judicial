/**
 * 
 */
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.Iterator;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasXEstatusService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarEvidenciasXEstatusServiceImplTest extends
		BaseTestServicios<ConsultarEvidenciasXEstatusService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.evidencia.impl.ConsultarEvidenciasXEstatusServiceImpl#consultarEvidenciasXCadenaCustodiaYEstatus(mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO, java.lang.Long)}.
	 */
	public void testConsultarEvidenciasXCadenaCustodiaYEstatus() {
		try {
			List<EvidenciaDTO> evidencias = service.consultarEvidenciasXCadenaCustodiaYEstatus(new CadenaDeCustodiaDTO(3L), 1L);
			assertNotNull(evidencias);
			for (EvidenciaDTO evi : evidencias) {
				logger.info("--------------------");
				logger.info("ID:"+evi.getEvidenciaId());
				logger.info("Estatus: "+evi.getEstatus().getIdCampo());
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testConsultaEvidencia(){
		try {
			EvidenciaDTO evidencia = service.consultaEvidencia(14L);
			assertNotNull(evidencia);
//			logger.info("*********************************");
//			logger.info("Evidencia id"+evidencia.getEvidenciaId());
//			logger.info("estatus Evidencia: "+evidencia.getEstatus());
//			logger.info("Cod Barras"+ evidencia.getCodigoBarras());
//			if (evidencia.getObjeto() != null){
//				ObjetoDTO obj = evidencia.getObjeto();
//				logger.info("Objeto id: "
//						+ obj.getElementoId());
//				logger.info("Almacen id: "+obj.getAlmacen());
//				if(obj.getExpedienteDTO()!=null){
//					logger.info("Expediente: " + obj.getExpedienteDTO().getExpedienteId());
//					logger.info("NumExp: " + obj.getExpedienteDTO().getNumeroExpedienteId()+" ("+obj.getExpedienteDTO().getNumeroExpediente()+")");
//					logger.info("Caso: " + obj.getExpedienteDTO().getCasoDTO().getCasoId());
//				}
//			}
//			if(evidencia.getEslabones()!=null){
//				logger.info("Existen "+evidencia.getEslabones().size()+ " eslabones");
//				Iterator<EslabonDTO> itEslab = evidencia.getEslabones().iterator();
//				while (itEslab.hasNext()) {
//					EslabonDTO eslabonDTO = (EslabonDTO) itEslab.next();
//					logger.info("Eslabon: "+eslabonDTO);
//				}
//				
//			}
			
			logger.info("--------------------");
			if(evidencia.getFuncionario()!=null)
				logger.info("AMP: "+evidencia.getFuncionario().getNombreCompleto());
			if(evidencia.getCadenaDeCustodia()!=null){
				if(evidencia.getCadenaDeCustodia().getExpedienteDTO()!=null){
					if(evidencia.getCadenaDeCustodia().getExpedienteDTO().getCasoDTO()!=null)
						logger.info("Caso: "+evidencia.getCadenaDeCustodia().getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
				}
			}
			logger.info("Evidencia:"+evidencia.getEvidenciaId());
			if(evidencia.getObjeto()!=null)
				logger.info("Nombre: "+evidencia.getObjeto().getTipoObjeto().getNombreEntity());
			logger.info("Codigo: "+evidencia.getCodigoBarras());
			if(evidencia.getEslabones()!=null){
				EslabonDTO eslabonUltimo=ultimoEslabon(evidencia.getEslabones().iterator());
				if(eslabonUltimo.getTipoEslabon()!=null){
					logger.info("Tipo Movim: "+eslabonUltimo.getTipoEslabon().getValor());
					if(eslabonUltimo.getFuncionariEntrega()!=null)
						logger.info("Quien Entrega: "+eslabonUltimo.getFuncionariEntrega().getNombreCompleto());
					if(eslabonUltimo.getFuncionariRecibe()!=null)
						logger.info("Quien Recibe: "+eslabonUltimo.getFuncionariRecibe().getNombreCompleto());
					if(eslabonUltimo.getDocumentoDTO()!=null)
						logger.info("Documento: "+eslabonUltimo.getDocumentoDTO());
				}
			}else
				logger.info("Tipo Movim: NO REGISTRADO");
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	 private EslabonDTO ultimoEslabon(Iterator<EslabonDTO> iterator) {
			EslabonDTO resp=new EslabonDTO();
			Long id=-1L;
			while (iterator.hasNext()) {
				EslabonDTO eslabon = (EslabonDTO) iterator.next();
				if(eslabon.getEslabonId()>id)
					resp=eslabon;
			}
			return resp;
		}

}
