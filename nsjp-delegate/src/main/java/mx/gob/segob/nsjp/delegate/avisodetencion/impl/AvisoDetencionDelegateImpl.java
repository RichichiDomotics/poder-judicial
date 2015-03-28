/**
* Nombre del Programa : AvisoDetencionDelegateImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del Delegate para los Avisos de Detencion
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
package mx.gob.segob.nsjp.delegate.avisodetencion.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.avisodetencion.AvisoDetencionDelegate;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.service.avisodetencion.AvisoDetencionService;
import mx.gob.segob.nsjp.service.avisodetencion.EnviarAvisoDetencionService;

/**
 * Implementacion del Delegate para los Avisos de Detencion
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
public class AvisoDetencionDelegateImpl implements AvisoDetencionDelegate {

	@Autowired
	private AvisoDetencionService avisoDetencionService; 
	
	@Autowired
	private EnviarAvisoDetencionService enviarAvisoService;
	
	@Override
	public List<AvisoDetencionDTO> obtenerAvisosDetencionPorEstatus(
			EstatusNotificacion estatusnotificacion) throws NSJPNegocioException {
		
		return this.avisoDetencionService.obtenerAvisosDetencionPorEstatus(estatusnotificacion);
	}

	@Override
	public List<AvisoDetencionDTO> consultarAvisosDetencionHistoricoByEstatus(
			EstatusNotificacion estatusNotificacion)
			throws NSJPNegocioException {		
		return avisoDetencionService.consultarAvisosDetencionHistoricoByEstatus(estatusNotificacion);
	}

	@Override
	public AvisoDetencionDTO obtenerAvisoDetencion(
			AvisoDetencionDTO avisoDetencionDTO) throws NSJPNegocioException {		
		return avisoDetencionService.obtenerAvisoDetencion(avisoDetencionDTO);
	}

	@Override
	public AvisoDetencionDTO atenderAvisoDetencion(
			AvisoDetencionDTO avisoDetencionDTO) throws NSJPNegocioException {		
		return avisoDetencionService.atenderAvisoDetencion(avisoDetencionDTO);
	}

    @Override
    public void enviarAvisoDetencion(DetencionDTO input, Long idAgencia, String claveAgencia, Long idFuncionarioSolicitante)
            throws NSJPNegocioException {
        this.enviarAvisoService.enviarAvisoDetencion(input,idAgencia,claveAgencia, idFuncionarioSolicitante);
        
    }
}
