/**
* Nombre del Programa : RegistrarMedidaAlternaPJServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Sep 2011
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
package mx.gob.segob.nsjp.service.test.medida.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.medida.TipoMedida;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.service.medidasalternas.RegistrarMedidaAlternaPJService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author cesarAgustin
 *
 */
public class RegistrarMedidaAlternaPJServiceImplTest extends
		BaseTestServicios<RegistrarMedidaAlternaPJService> {

	public void testRegistrarMedidaAlternaPJ() {
		MedidaAlternaDTO medidaAlternaDTO = new MedidaAlternaDTO();
		InvolucradoDTO involucradoDTO = new InvolucradoDTO(1L);				
		
		medidaAlternaDTO.setAnios((short)12);
		medidaAlternaDTO.setMeses((short)3);
		
		//Medida
		medidaAlternaDTO.setFechaInicio(new Date());
		medidaAlternaDTO.setFechaFin(new Date());
		medidaAlternaDTO.setDescripcionMedida("Prueba de medida alterna");
		medidaAlternaDTO.setNumeroCarpetaEjecucion("NSJYUCPJ20114433333");
		medidaAlternaDTO.setInvolucrado(involucradoDTO);	
		medidaAlternaDTO.setSeguimiento("Seguimiento prueba");
		medidaAlternaDTO.setValorTipoMedida(new ValorDTO(TipoMedida.COLOCAR_LOCALIZADORES_ELECTRONICOS.getValorId()));
		
		try {
			Long respuesta = service.resgistrarMedidaAlterna(medidaAlternaDTO);
			
			assertNotNull(respuesta);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
