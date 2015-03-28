/**
 * Nombre del Programa : CanalizarCausaServiceImpl.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 11 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación de CanalizarCausaService
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
package mx.gob.segob.nsjp.service.actividad;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.actividad.impl.AdministradorActividadesServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Implementación de CanalizarCausaServiceImpl
 * 
 * @version 1.0
 * @author rgama
 * 
 */
@Service
@Transactional
public class CanalizarCausaServiceImpl implements CanalizarCausaService {
    @Autowired
    private ExpedienteDAO expedienteDAO;
    @Autowired
    private AdministradorActividadesService administradorActividadesService;
    
    private final static Logger logger = Logger
    .getLogger(CanalizarCausaServiceImpl.class);

	public ActividadDTO canalizarCausaServiceImpl(ExpedienteDTO inputExpediente, ActividadDTO actividadDTO,
			Boolean bDelitoPrincipalGrave, Boolean bInputadoReincidente, Boolean bSeguimientoInterno) throws NSJPNegocioException {
	
		if(inputExpediente == null || inputExpediente.getExpedienteId() == null || 
				actividadDTO.getTipoActividad()== null || actividadDTO.getTipoActividad().getValorId()== null 
				|| inputExpediente.getUsuario() == null || inputExpediente.getUsuario().getFuncionario() == null ||
				inputExpediente.getUsuario().getFuncionario().getClaveFuncionario() == null	|| 
				bDelitoPrincipalGrave == null || bInputadoReincidente==null || bSeguimientoInterno == null){
			
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);	
		}		

		Expediente loExpediente = expedienteDAO.read(inputExpediente.getExpedienteId());
		if(actividadDTO.getTipoActividad().getValorId().equals(Actividades.CANALIZAR_JUSTICIA_ALTERNATIVA_RESTAURATIVA.getValorId())){
				//FIXME: Agregar la actividad cuando este lista en la BD
				//|| actividadDTO.getTipoActividad().getValorId().equals(Actividades.REGRESAR_A_RESTAURATIVA_CONCILIACION_MEDIACION.getValorId()){
			if(bDelitoPrincipalGrave == false && bInputadoReincidente == false && bSeguimientoInterno == true){
				loExpediente.setEstatus(new Valor(EstatusExpediente.CANALIZADO.getValorId()));
				return administradorActividadesService.generarActividadAExpediente(inputExpediente, actividadDTO);
			}
			else{
				logger.info("Convinacion de variables no validad al momento de intentar canalizar a Justicia Alternativo");
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			}
		}
		
		if(actividadDTO.getTipoActividad().CANALIZAR_UNIDAD_INVESTIGACION.equals(Actividades.CANALIZAR_UNIDAD_INVESTIGACION)){												//Importa el imputado
			if((bDelitoPrincipalGrave == true ||  bInputadoReincidente == true) && bSeguimientoInterno == true){
				loExpediente.setEstatus(new Valor(EstatusExpediente.CANALIZADO.getValorId()));
				return administradorActividadesService.generarActividadAExpediente(inputExpediente, actividadDTO);
			}
			else{
				logger.info("Convinacion de variables no validad al momento de intentar canalizar a unidad investigacion");
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			}
		}
//		
//		if(actividadDTO.getTipoActividad().CANALIZAR_A_INSTITUCION_EXTERNA.equals(Actividades.CANALIZAR_A_INSTITUCION_EXTERNA){
//			if(bSeguimientoInterno == false){
//				//Actualizar estatus del expediente a cerrado				
//				loExpediente.setEstatus(new Valor(EstatusExpediente.CERRADO.getValorId()));
//				return administradorActividadesService.generarActividadAExpediente(inputExpediente, actividadDTO);
//			}
//			else{
//				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
//			}
//		}
		return null;

	}

}
