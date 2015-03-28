/**
* Nombre del Programa : ConsultarResumenExpedienteParaDocumentoServiceImpl.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Consulta la información de un expediente y la coloca de forma
* que la capa de presentación pueda dar un resumen al usuario del expediente y todos sus elementos
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
package mx.gob.segob.nsjp.service.documento.impl;

import mx.gob.segob.nsjp.comun.enums.configuracion.Parametros;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.EntidadFederativaDAO;
import mx.gob.segob.nsjp.dto.ParametroDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.ParametrosDocumentoDTO;
import mx.gob.segob.nsjp.model.EntidadFederativa;
import mx.gob.segob.nsjp.service.audiencia.ConsultarAudienciaService;
import mx.gob.segob.nsjp.service.documento.ConsultarResumenExpedienteParaDocumentoService;
import mx.gob.segob.nsjp.service.documento.impl.tranform.ParametrosDocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.BuscarExpedienteService;
import mx.gob.segob.nsjp.service.parametro.ConsultarParametroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Consulta la información de un expediente y la coloca de forma
 * que la capa de presentación pueda dar un resumen al usuario del expediente y todos sus elementos
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional(readOnly=true)
public class ConsultarResumenExpedienteParaDocumentoServiceImpl implements
		ConsultarResumenExpedienteParaDocumentoService {

	@Autowired
	BuscarExpedienteService buscarExpedienteService;
	@Autowired
	ConsultarAudienciaService consultarAudienciaService;
	@Autowired
	ConsultarParametroService consultarParametroService;
	@Autowired
	EntidadFederativaDAO entidadFederativaDAO;
	
	public ParametrosDocumentoDTO consultarResumenExpedienteParaDocumento (
			ExpedienteDTO expedienteParam) throws NSJPNegocioException{
		ParametrosDocumentoDTO parametrosDocumento = null;
		ExpedienteDTO expediente = null;
		if(expedienteParam != null && expedienteParam.getExpedienteId() != null){
			expediente = buscarExpedienteService.obtenerExpediente(expedienteParam);
		}
		
		if(expediente != null){
			parametrosDocumento = ParametrosDocumentoTransformer.cargarParametrosDocumentoFrom(expediente);
			
			ParametroDTO loParametro = consultarParametroService.consultarParametro(Parametros.ENTIDAD_FEDERATIVA_DESPLIEGUE);
			Long idEntidadFederativa = Long.parseLong(loParametro.getValor());
			entidadFederativaDAO.read(idEntidadFederativa);
			EntidadFederativa entidadFederativa = entidadFederativaDAO.read(idEntidadFederativa);
			if(entidadFederativa!= null && entidadFederativa.getNombre()!=null){
				//parametrosDocumento.setCiudad("Merida"); Se Omite
				parametrosDocumento.setEstado(entidadFederativa.getNombre());
			}
			parametrosDocumento.setUsuario(expedienteParam.getUsuario());
		}
		if(parametrosDocumento == null){
			parametrosDocumento = new ParametrosDocumentoDTO();
			
		}
		return parametrosDocumento;
		
	}
	
	public ParametrosDocumentoDTO consultarResumenExpedienteParaDocumento (
			AudienciaDTO audiencia) throws NSJPNegocioException{
		ParametrosDocumentoDTO parametrosDocumento = null;
		if(audiencia != null && audiencia.getId() != null){
			audiencia = consultarAudienciaService.obtenerAudiencia(audiencia);
			parametrosDocumento = ParametrosDocumentoTransformer.cargarParametrosDocumentoFrom(audiencia);
		}
		return parametrosDocumento;
	}
}
