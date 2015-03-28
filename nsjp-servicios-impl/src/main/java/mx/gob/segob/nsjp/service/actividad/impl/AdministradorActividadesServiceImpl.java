/**


 * Nombre del Programa : AdministradorActividadesServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementación de AdministradorActividadesService
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
package mx.gob.segob.nsjp.service.actividad.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.actividad.AdministradorActividadesService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ActividadTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación de AdministradorActividadesService.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class AdministradorActividadesServiceImpl
        implements
            AdministradorActividadesService {
    @Autowired
    private ExpedienteDAO expedienteDAO;

    @Autowired
    private ActividadDAO actividadDao;
    
    @Autowired
    private ConfInstitucionDAO instituionDao;
    
    private static final Logger logger = Logger
            .getLogger(AdministradorActividadesServiceImpl.class);
    
    /*
     * (non-Javadoc)
     * 
     * @see mx.gob.segob.nsjp.service.actividad.AdministradorActividadesService#
     * generarActividad(mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO,
     * mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO,
     * mx.gob.segob.nsjp.model.Expediente)
     */
    @Override
    public void generarActividad(ExpedienteDTO inputExpediente,
            ExpedienteDTO nuevoExpDTO, Expediente nuevoBD)
            throws NSJPNegocioException {
        Actividad act = new Actividad();
        act.setFechaCreacion(new Date());
        act.setFuncionario(new Funcionario(inputExpediente.getUsuario()
                .getFuncionario().getClaveFuncionario()));

        switch (Areas.values()[inputExpediente.getArea().getAreaId().intValue()]) {
            case ATENCION_TEMPRANA_PG_PENAL :
                nuevoBD.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));

                logger.info("Se crea actividad " + act.getTipoActividad());
                act.setTipoActividad(new Valor(Actividades.GENERAR_DENUNCIA_EN_ATP
                        .getValorId()));
                break;
            case JUSTICIA_ALTERNATIVA_RESTAURATIVA :
                nuevoBD.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
                act.setTipoActividad(new Valor(
                        Actividades.CAPTURAR_ENTREVISTA_INICIAL.getValorId()));
                break;
            
            case AGENCIA_DEL_MINISTERIO_PUBLICO :
            	nuevoBD.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
                act.setTipoActividad(new Valor(
                		Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId()));
                break;
                
            default :
                nuevoBD.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
                act.setTipoActividad(new Valor(Actividades.GENERAR_DENUNCIA_EN_ATP
                        .getValorId()));
                break;

        }
        Long idNuevo = null;
        if (inputExpediente.getExpedienteId() == null) {
            nuevoBD.setPertenceConfInst(this.instituionDao.consultarInsitucionActual());
            if(inputExpediente != null && inputExpediente.getUsuario() != null && inputExpediente.getUsuario().getFuncionario() != null 
            		&& inputExpediente.getUsuario().getFuncionario().getDiscriminante() != null && inputExpediente.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId() != null)
            nuevoBD.setDiscriminante(new CatDiscriminante(inputExpediente.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId()));
            idNuevo = expedienteDAO.create(nuevoBD);
        } else {
            idNuevo = inputExpediente.getExpedienteId();
        }

        nuevoBD.setExpedienteId(idNuevo);
        act.setTipoActividad(new Valor(Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId()));
        logger.info("***Se crea actividad " + act.getTipoActividad());
        act.setExpediente(new Expediente(nuevoBD.getExpedienteId()));
        this.actividadDao.create(act);
        nuevoExpDTO.setActividadActual(ActividadTransformer.transformarActividad(act));
    }
    /*
     * (non-Javadoc)
     * @see mx.gob.segob.nsjp.service.actividad.AdministradorActividadesService#generarActividadEnBaseAOtra(java.lang.Long)
     */
	@Override
	public ActividadDTO generarActividadEnBaseAOtra(Long actividadOriginalId) {
		Actividad actividadOriginal = actividadDao.read(actividadOriginalId);
		ActividadDTO actividadNueva = null;
		if(actividadOriginal != null){
			Actividad nuevaActividad = new Actividad();
			nuevaActividad.setExpediente(actividadOriginal.getExpediente());
			nuevaActividad.setTipoActividad(actividadOriginal.getTipoActividad());
			nuevaActividad.setFechaCreacion(new Date());
			nuevaActividad.setFuncionario(actividadOriginal.getFuncionario());
			
			actividadDao.create(nuevaActividad);
			actividadNueva = ActividadTransformer.transformarActividad(nuevaActividad);
		}
		
		return actividadNueva;
	}
	
	public ActividadDTO generarActividadAExpediente(ExpedienteDTO inputExpediente, ActividadDTO actividadDTO) throws NSJPNegocioException {
		
			if(inputExpediente == null || inputExpediente.getExpedienteId() == null || 
					actividadDTO.getTipoActividad()== null || actividadDTO.getTipoActividad().getValorId()== null 
					|| inputExpediente.getUsuario() == null || inputExpediente.getUsuario().getFuncionario() == null ||
					inputExpediente.getUsuario().getFuncionario().getClaveFuncionario() == null	){
				throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);	
			}
		
			Actividad nuevaActividad = new Actividad();			
			nuevaActividad.setExpediente(new Expediente(inputExpediente.getExpedienteId()));
			nuevaActividad.setTipoActividad(new Valor(actividadDTO.getTipoActividad().getValorId()));
			nuevaActividad.setFechaCreacion(new Date());
			nuevaActividad.setFuncionario(new Funcionario(inputExpediente.getUsuario()
	                .getFuncionario().getClaveFuncionario()));			
			Long idActividad = actividadDao.create(nuevaActividad);
			
			inputExpediente.getUsuario().getFuncionario().getClaveFuncionario();
			
			ActividadDTO nuevaActividadDTO = new ActividadDTO();
			nuevaActividadDTO = ActividadTransformer.transformarActividad(nuevaActividad);
			nuevaActividadDTO.setActividadId(idActividad);
		
		return nuevaActividadDTO;
	}
}
