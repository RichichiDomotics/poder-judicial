/**
 * Nombre del Programa  : RegistrarAvisoDeDetencionServiceImpljava
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 15 Jul 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Envia un aviso de detención a Coordinación de Defensoria
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.solicitud.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.service.infra.DefensoriaClienteService;
import mx.gob.segob.nsjp.service.involucrado.ConsultarIndividuoService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarAvisoDeDetencionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RegistrarAvisoDeDetencionServiceImpl implements RegistrarAvisoDeDetencionService {

	@Autowired
	private ConsultarIndividuoService consultarIndividuoService;
	
	@Autowired
	private DefensoriaClienteService clienteService;
	
	@Override
	public void registrarAvisoDeDetencion(Long imputadoID, CasoDTO noCaso,Long idAgencia, String claveAgencia)
			throws NSJPNegocioException {
		
		InvolucradoDTO imputado = new InvolucradoDTO ();
		imputado.setElementoId(imputadoID);
		imputado = consultarIndividuoService.obtenerInvolucrado(imputado);
		DetencionDTO detencion = null;
		if(imputado.getDetenciones() != null && !imputado.getDetenciones().isEmpty()){
			detencion = imputado.getDetenciones().get(0);
			for (DetencionDTO det : imputado.getDetenciones()) {
				if((detencion == null) || (detencion != null && detencion.getFechaInicioDetencion().compareTo(det.getFechaInicioDetencion()) == -1 )){
					detencion = det;
				}
			}
			imputado.setDetenciones(null);
			detencion.setInvolucradoDTO(imputado);
			clienteService.enviarAvisoDetencion(detencion, null, noCaso,idAgencia,claveAgencia,null);
		}
	}
}
