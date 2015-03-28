/**
* Nombre del Programa : EnviarCarpetaDeInvestigacionProcuraduriaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/08/2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.expediente.EnviarCarpetaDeInvestigacionProcuraduriaService;
import mx.gob.segob.nsjp.service.infra.DefensoriaClienteService;

/**
 * Implementacion del Servicio que invoca al WS para enviar la Carpeta de investigación de Procuraduría a Defensoría
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class EnviarCarpetaDeInvestigacionProcuraduriaServiceImpl implements
	EnviarCarpetaDeInvestigacionProcuraduriaService{

	@Autowired 
	DefensoriaClienteService defensoriaClienteService;
	
	public ExpedienteDTO enviarCarpetaDeInvestigacion(
			String numeroGeneralCaso, String folioSolicitud)
    		throws NSJPNegocioException{
		return (defensoriaClienteService.enviarCarpetaDeInvestigacion(numeroGeneralCaso, folioSolicitud));
	}
}
