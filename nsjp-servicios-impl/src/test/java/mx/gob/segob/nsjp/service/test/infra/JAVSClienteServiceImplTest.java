/**
* Nombre del Programa : JAVSClienteServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 8 Nov 2011
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
package mx.gob.segob.nsjp.service.test.infra;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.service.infra.JAVSClienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias del Cliente para comunicarse con el WS.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class JAVSClienteServiceImplTest extends BaseTestServicios<JAVSClienteService> {

	
	public void testAgendarAudiencia(){
		logger.info("Probando el servicio de: agendarAudiencia");
		Long idAudiencia = 206L;
		String claveUsuario = "encargadoSegIn"; 
		try {
			Long idEvento = service.agendarAudiencia(idAudiencia, claveUsuario);
			logger.info(" Valor:"+ idEvento);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	public void testConsultarAudiencia(){
		logger.info("Probando el servicio de: consultarAudiencia");
		Long idAudiencia = 206L;
		String claveUsuario = "encargadoSegIn"; 
		try {
			Long idEvento = service.consultarAudiencia(idAudiencia, claveUsuario);
			logger.info(" Valor:"+ idEvento);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	public void testEliminarAudiencia(){
		logger.info("Probando el servicio de: eliminarAudiencia");
		Long idAudiencia = 206L;
		String claveUsuario = "encargadoSegIn"; 
		try {
			Long idEvento = service.eliminarAudiencia(idAudiencia, claveUsuario);
			logger.info(" Valor:"+ idEvento);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
	
	public void testEstadoAudiencia(){
		logger.info("Probando el servicio de: estadoAudiencia");
		Long idAudiencia = 326L;
		String claveUsuario = "admonPJ"; 
		try {
			Long idEvento = service.estadoAudiencia(idAudiencia, claveUsuario);
			logger.info(" Valor:"+ idEvento);
		} catch (NSJPNegocioException e) {
			logger.info(e.getMessage(), e);
		}
	}
}
