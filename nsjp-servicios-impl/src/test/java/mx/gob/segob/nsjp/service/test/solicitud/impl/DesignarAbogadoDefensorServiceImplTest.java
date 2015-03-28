/**
* Nombre del Programa : DesignarAbogadoDefensorServiceImplTest.java
* Autor                            : cesar
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
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

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteRestDefensoriaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDefensorDTO;
import mx.gob.segob.nsjp.service.funcionario.DesignarAbogadoDefensorService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author cesar
 *
 */
public class DesignarAbogadoDefensorServiceImplTest extends
		BaseTestServicios<DesignarAbogadoDefensorService> {

	public void testDesignarAbogadoDefensor () {
		ExpedienteRestDefensoriaDTO expedienteDTO = new ExpedienteRestDefensoriaDTO();
		SolicitudDefensorDTO solicitudDefensorDTO = new SolicitudDefensorDTO();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		
		expedienteDTO.setFechaApertura(new Date());
		expedienteDTO.setFechaDeCita(new Date());
		
		funcionarioDTO.setClaveFuncionario(new Long(7));
		
		solicitudDefensorDTO.setDocumentoId(new Long(81));
		solicitudDefensorDTO.setFuncionario(funcionarioDTO);
		
		try {
			SolicitudDefensorDTO respuesta = service.designarAbogadoDefensor(solicitudDefensorDTO);
			assertTrue("El id de expediente debe ser mayor a 0 ", respuesta.getExpedienteDTO().getExpedienteId()>0);
		} catch (NSJPNegocioException e) {
			if (logger.isErrorEnabled())
				logger.error(e.getMessage());
		}
	}
}
