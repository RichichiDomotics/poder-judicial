/**
* Nombre del Programa : ActividadDelegateImpl.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 21/07/2011
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
package mx.gob.segob.nsjp.delegate.actividad.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.actividad.ConsultarActividadService;
import mx.gob.segob.nsjp.service.actividad.ConsultarActividadesXTipoAtencionExpedienteService;
import mx.gob.segob.nsjp.service.actividad.ConsultarActuacionesPorCatDelitosService;
import mx.gob.segob.nsjp.service.actividad.ConsultarConfActividadDocumentoService;
import mx.gob.segob.nsjp.service.actividad.EliminarAsociacionExpedienteDocumentoService;
import mx.gob.segob.nsjp.service.actividad.RegistrarActividadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
@Service
public class ActividadDelegateImpl implements ActividadDelegate {

	@Autowired
	private RegistrarActividadService registrarActividadService;
	@Autowired
	private EliminarAsociacionExpedienteDocumentoService eliminarAsociacionExpedienteDocumentoService;
	@Autowired
	private ConsultarActuacionesPorCatDelitosService actuacionesPorCatDelitosService; 
	@Autowired
	private ConsultarConfActividadDocumentoService consultarConfActividadDocumentoService;
	@Autowired
	private ConsultarActividadesXTipoAtencionExpedienteService consultarActividadesXTipoAtencionExpedienteService;
	@Autowired
	private ConsultarActividadService consultarActividadService;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.delegate.actividad.ActividadDelegate#registrarActividad(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO, java.lang.Long)
	 */
	@Override
	public Long registrarActividad(ExpedienteDTO expedienteDTO, FuncionarioDTO funcionarioDTO, 
			Long tipoActividad) throws NSJPNegocioException {
		return registrarActividadService.registrarActividad(expedienteDTO,funcionarioDTO,tipoActividad);
	}

	@Override
	public void eliminarAsocArchivoAExpediente(ExpedienteDTO expedienteDTO,
			DocumentoDTO documentoDTO) throws NSJPNegocioException {
		eliminarAsociacionExpedienteDocumentoService.eliminarAsocArchivoAExpediente(expedienteDTO, documentoDTO);
	}

	@Override
	public List<ValorDTO> consultarActividadesPorIdsCatDelito(
			List<Long> idsCatDelito) throws NSJPNegocioException {
		return actuacionesPorCatDelitosService.consultarActividadesPorIdsCatDelito(idsCatDelito);
	}

	@Override
	public List<ActividadDTO> consultarActividadesPorTipoActividadExpedienteId (Long idExpediente, List<Long> idTipoActividades, Boolean documentoRec)throws NSJPNegocioException{
		return consultarConfActividadDocumentoService.consultarActividadesPorTipoActividadExpedienteId(idExpediente, idTipoActividades, documentoRec);
	}

	@Override
	public Long consultarNumeroActividadesPorTipoAtencionExpedienteId(
			Long expedienteId, Long tipoAtencion) throws NSJPNegocioException {
		return consultarActividadesXTipoAtencionExpedienteService.consultarNumeroDeActividadesXTipoAtencion(expedienteId, tipoAtencion);
	}

	@Override
	public ActividadDTO obtenerActPorExpTipoAct(Long expedienteId,
			Actividades actividad) throws NSJPNegocioException {		
		return consultarActividadService.obtenerActPorExpTipoAct(expedienteId, actividad);
	}
}
