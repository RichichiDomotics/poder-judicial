/**
* Nombre del Programa : ActaulizarTurnoAtencionServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 17/11/2011
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

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.ActualizarTurnoAtencionService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.TurnoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de los servicios para actualizar los turnos.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
public class ActualizarTurnoAtencionServiceImpl implements
		ActualizarTurnoAtencionService {
	private final static Logger logger = Logger
    .getLogger(ActualizarTurnoAtencionServiceImpl.class);
    		
    @Autowired
    private TurnoDAO turnoDAO;


	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.expediente.ActulizarTurnoAtencionService#actulizarEstatusTurno(java.lang.Long)
	 */
	@Override
	public TurnoDTO actualizarTurno(TurnoDTO turnoDTO)
			throws NSJPNegocioException {
		logger.info(" ActulizarTurnoAtencionService - actualizarEstatusTurno: TusnoDTO:"+ turnoDTO);
		
		if(turnoDTO==null || turnoDTO.getTurnoId()==null ||  turnoDTO.getTurnoId()<0)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Turno turnoBD = turnoDAO.read(turnoDTO.getTurnoId());
		if(turnoBD==null)
			throw new NSJPNegocioException(CodigoError.INFORMACION_PARAMETROS_ERRONEA);
		
		if(turnoDTO.getTipoTurno()!=null)
			turnoBD.setTipoTurno(turnoDTO.getTipoTurno().name());
		
		if(turnoDTO.getEsUrgente()!=null)
			turnoBD.setEsUrgente(turnoDTO.getEsUrgente());
		
		if(turnoDTO.getEstado()!=null)
			turnoBD.setEstatus(new Valor(turnoDTO.getEstado().getValorId()));
		
		if(turnoDTO.getExpediente()!=null && 
				turnoDTO.getExpediente().getExpedienteId()!=null && 
				turnoDTO.getExpediente().getExpedienteId()>0)
			turnoBD.setExpediente(new Expediente(turnoDTO.getExpediente().getExpedienteId()));
		
		if(turnoDTO.getUsuario()!=null && turnoDTO.getUsuario().getIdUsuario()!=null)
			turnoBD.setUsuario(new Usuario(turnoDTO.getUsuario().getIdUsuario()));
		
		if(turnoDTO.getNombreCiudadano()!=null && !turnoDTO.getNombreCiudadano().trim().isEmpty())
			turnoBD.setNombreCiudadano(turnoDTO.getNombreCiudadano());
		
		if(turnoDTO.getApellidoPaternoCiudadano()!=null && !turnoDTO.getApellidoPaternoCiudadano().trim().isEmpty())
			turnoBD.setApellidoPaternoCiudadano(turnoDTO.getApellidoPaternoCiudadano());
		
		if(turnoDTO.getApellidoMaternoCiudadano()!=null && !turnoDTO.getApellidoMaternoCiudadano().trim().isEmpty())
			turnoBD.setApellidoMaternoCiudadano(turnoDTO.getApellidoMaternoCiudadano());
		
		if(turnoDTO.getUsuario() != null && turnoDTO.getUsuario().getFuncionario() != null && turnoDTO.getUsuario().getFuncionario().getDiscriminante() != null && turnoDTO.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId() != null && turnoDTO.getUsuario().getFuncionario().getDiscriminante().getClave() != null){
			turnoBD.setDiscriminante(new CatDiscriminante(turnoDTO.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId(),turnoDTO.getUsuario().getFuncionario().getDiscriminante().getClave()));
	    }

		turnoDAO.update(turnoBD);
		
		TurnoDTO turnoRegresoDTO = TurnoTransformer.trannsformarTurno(turnoBD);
		
		return turnoRegresoDTO;
	}

}
