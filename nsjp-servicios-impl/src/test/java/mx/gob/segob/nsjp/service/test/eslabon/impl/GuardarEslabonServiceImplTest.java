/**
 * 
 */
package mx.gob.segob.nsjp.service.test.eslabon.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.evidencia.TiposEslabon;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.eslabon.GuardarEslabonService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author adrian
 *
 */
public class GuardarEslabonServiceImplTest extends BaseTestServicios<GuardarEslabonService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.eslabon.impl.GuardarEslabonServiceImpl#guardarEslabon(mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO, mx.gob.segob.nsjp.dto.evidencia.EslabonDTO)}.
	 */
	public void testGuardarEslabon() {
		
		EvidenciaDTO evidenciaDTO=new EvidenciaDTO(1L);
		EslabonDTO eslabonDTO=new EslabonDTO();
//		eslabonDTO.setEslabonId(13L);
//		eslabonDTO.setNumeroEslabon(66);
		eslabonDTO.setFechaFinMovimiento(new Date());
		eslabonDTO.setFechaInicioMovimiento(new Date());
		FuncionarioDTO funcionariRecibe=new FuncionarioDTO();
		funcionariRecibe.setClaveFuncionario(3L);
		funcionariRecibe.setNombreFuncionario("Nombre recibe");
//		funcionariRecibe.setApellidoPaternoFuncionario("Paterno");
//		funcionariRecibe.setApellidoMaternoFuncionario("Materno");
		FuncionarioDTO funcionariEntrega=new FuncionarioDTO();
		funcionariEntrega.setNombreFuncionario("Nombre Entrega");
		funcionariEntrega.setClaveFuncionario(4L);
		
		eslabonDTO.setInstitucionQueEntrega("Institucion que entrega");
		eslabonDTO.setInstitucionQueRecibe("Institucion que recibe");
		eslabonDTO.setTipoEslabonDeRecepcion(new ValorDTO(5L));
		
		eslabonDTO.setFuncionariEntrega(funcionariEntrega);
		eslabonDTO.setFuncionariRecibe(funcionariRecibe);
		eslabonDTO.setObservacion("Solicitud de registro");
		eslabonDTO.setFechaInicioMovimiento(new Date());
		eslabonDTO.setFechaFinMovimiento(new Date());
		eslabonDTO.setTipoEslabon(new ValorDTO(TiposEslabon.OTROS.getValorId()));
//		eslabonDTO.setUbicacionFisica("Esta tras la puerta roja");
//		eslabonDTO.setPosicion("PRO-12-342");
		try {
			Long eslabon=service.guardarEslabon(evidenciaDTO, eslabonDTO);
			assertNotNull("Exito",eslabon);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}
	
	public void testAsociarDocumentoAEslabon(){
		EslabonDTO dto=new EslabonDTO();
		dto.setEslabonId(13L);
		dto.setDocumentoDTO(new DocumentoDTO(3L));
		try {
			Long idEslabon = service.asociarDocumentoAEslabon(dto);
			assertNotNull(idEslabon);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
