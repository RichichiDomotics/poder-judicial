/**
 * 
 */
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.evidencia.ConsultarEvidenciasNoDevueltasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class ConsultarEvidenciasNoDevueltasServiceImplTest extends
		BaseTestServicios<ConsultarEvidenciasNoDevueltasService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.evidencia.impl.ConsultarEvidenciasNoDevueltasServiceImpl#consultarEvidenciasNoDevueltas(mx.gob.segob.nsjp.dto.almacen.AlmacenDTO)}.
	 */
	public void testConsultarEvidenciasNoDevueltas() {
		UsuarioDTO usuario=new UsuarioDTO();
    	usuario.setFuncionario(new FuncionarioDTO(11L));
		try {
			List<EvidenciaDTO> evidencias = service.consultarEvidenciasNoDevueltas(null);
			assertNotNull(evidencias);
			logger.info("Existen "+ evidencias.size()+" evidencias");
			for (EvidenciaDTO evi : evidencias) {
				logger.info("--------------------");
				if(evi.getFuncionario()!=null)
					logger.info("AMP: "+evi.getFuncionario().getNombreCompleto());
				if(evi.getCadenaDeCustodia()!=null){
					if(evi.getCadenaDeCustodia().getExpedienteDTO()!=null){
						if(evi.getCadenaDeCustodia().getExpedienteDTO().getCasoDTO()!=null)
							logger.info("Caso: "+evi.getCadenaDeCustodia().getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
					}
				}
				logger.info("Evidencia:"+evi.getEvidenciaId());
				if(evi.getObjeto()!=null)
					logger.info("Nombre: "+evi.getObjeto().getTipoObjeto().getNombreEntity());
				logger.info("Codigo: "+evi.getCodigoBarras());
				if(evi.getEslabones()!=null){
					EslabonDTO eslabonUltimo=evi.getEslabones().iterator().next();
					if(eslabonUltimo.getTipoEslabon()!=null){
						logger.info("Tipo Movim: "+eslabonUltimo.getTipoEslabon().getValor());
						if(eslabonUltimo.getFuncionariEntrega()!=null)
							logger.info("Quien Entrega: "+eslabonUltimo.getFuncionariEntrega().getNombreCompleto());
						if(eslabonUltimo.getFuncionariRecibe()!=null)
							logger.info("Quien Recibe: "+eslabonUltimo.getFuncionariRecibe().getNombreCompleto());
						logger.info("Fecha de devolución: "+eslabonUltimo.getFechaFinMovimiento());
					}
				}else
					logger.info("Tipo Movim: NO REGISTRADO");
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
