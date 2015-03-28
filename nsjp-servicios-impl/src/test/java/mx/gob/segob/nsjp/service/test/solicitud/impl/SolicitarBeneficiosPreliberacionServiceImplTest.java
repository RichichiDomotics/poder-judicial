/**
* Nombre del Programa : SolicitarBeneficiosPreliberacionServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Aug 2011
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
package mx.gob.segob.nsjp.service.test.solicitud.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudBeneficiosPreliberacionWSDTO;
import mx.gob.segob.nsjp.service.solicitud.SolicitarBeneficiosPreliberacionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para elservicio de solicitar beneficios preliberacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class SolicitarBeneficiosPreliberacionServiceImplTest extends
		BaseTestServicios<SolicitarBeneficiosPreliberacionService> {

	public void testRegistrarSolicitudBeneficiosPreliberacion () {	
				
		try {
			SolicitudBeneficiosPreliberacionWSDTO solBeneficios = new SolicitudBeneficiosPreliberacionWSDTO();
			solBeneficios.setNumCarpetaEjecucion("NSJYUCPJ20112633333");
			solBeneficios.setConfInstitucionId(Instituciones.SSP.getValorId());
			solBeneficios.setFechaLimite(new Date());	
			solBeneficios.setFolioSolicitud("00023");
			solBeneficios.setNombreSolicitante("Juan Perez");
			
			SolicitudBeneficiosPreliberacionWSDTO respuesta = service.registrarSolicitudBeneficiosPreliberacion(solBeneficios);
			assertNotNull("no puede ser nulo", respuesta);
			logger.info("Id solicitud :: "+respuesta.getSolicitudId());
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}				
	}
	
}
