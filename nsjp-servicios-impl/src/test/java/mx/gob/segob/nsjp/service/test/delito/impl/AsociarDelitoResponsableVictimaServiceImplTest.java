/**
* Nombre del Programa : AsociarDelitoResponsableVictimaServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/07/2011
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
package mx.gob.segob.nsjp.service.test.delito.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.delito.AsociarDelitoResponsableVictimaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class AsociarDelitoResponsableVictimaServiceImplTest extends
		BaseTestServicios<AsociarDelitoResponsableVictimaService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.delito.impl.AsociarDelitoResponsableVictimaServiceImpl#asociarDelitoResponsableVictima(java.lang.Long, mx.gob.segob.nsjp.dto.expediente.DelitoDTO, mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO, mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO, java.lang.Long, java.lang.Long)}.
	 */
	public void testAsociarDelitoResponsableVictima() {
		Long asociacionID=null;
		DelitoDTO delito=new DelitoDTO(6L);
		InvolucradoDTO responsable=new InvolucradoDTO(1480L);//5
		InvolucradoDTO victima=null;//new InvolucradoDTO(5L);//8
		Long formaParticipacion=1L;
		Long bienTutelado=null;
		Long idClasificacion = null;
		Long idLugar  = null;
		Long idModalidad = null;
		Long idModus = null;
		Long idCausa = null;
		try {
			service.asociarDelitoResponsableVictima(asociacionID, delito, responsable, victima, formaParticipacion, bienTutelado, idClasificacion, idLugar, idModalidad, idModus, idCausa);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO: asociarDelitoResponsableVictima", false);
		}
	}

	public void testExisteRelacionProbableResponsableVictimaDelito(){
		Long idDelito = 1404L;
		Long idProbableResponsable = 8900L;
		Long idVictima = 8897L;
		Long idFormaParticipacion = 2162L;
		
		
		Boolean existeRelacion = false;
		
		try {
			existeRelacion = service
					.existeRelacionProbableResponsableVictimaDelitoFormaParticipacion(
							idDelito, idProbableResponsable, idVictima,
							idFormaParticipacion);
			logger.info("Existe:"+ existeRelacion);
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}
}
