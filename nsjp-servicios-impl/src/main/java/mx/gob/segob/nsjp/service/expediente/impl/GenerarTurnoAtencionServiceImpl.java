/**
 * Nombre del Programa : GenerarTurnoAtencionServiceImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 Apr 2011
 * Marca de cambio        : N/A
 * Descripcion General    :  Generar el turno para orientar a la víctima y darle la atención adecuada
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

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.TurnoDAO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.GenerarTurnoAtencionService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Generar el turno para orientar a la víctima y darle la atención adecuada.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
@Transactional
public class GenerarTurnoAtencionServiceImpl
        implements
            GenerarTurnoAtencionService {

    @Autowired
    private TurnoDAO turnoDAO;

    @Autowired
    private ExpedienteDAO expedienteDAO;
    

    private final static Logger log = Logger
            .getLogger(GenerarTurnoAtencionServiceImpl.class);
    /**
     * 
     */
    @Override
    public TurnoDTO generarTurnoAtencion(TurnoDTO defTurno)
            throws NSJPNegocioException {

        if (log.isDebugEnabled()) {
            log.debug("defTurno :: " + defTurno);
        }
        
        Turno turno = new Turno();
        String ultimoTurno = "";
        Long siguienteTurno = new Long(0);
        Calendar calTemp = Calendar.getInstance();
        
        turno.setEstatus(new Valor(EstatusTurno.ESPERA.getValorId()));
        turno.setEsUrgente(defTurno.getEsUrgente());
        turno.setFichaAtencion(defTurno.getFichaAtencion());
        turno.setTipoTurno(defTurno.getTipoTurno().toString());
        turno.setFechaAtencion(calTemp.getTime());
        turno.setNombreCiudadano(defTurno.getNombreCiudadano());
        turno.setApellidoPaternoCiudadano(defTurno.getApellidoPaternoCiudadano());
        turno.setApellidoMaternoCiudadano(defTurno.getApellidoMaternoCiudadano());
       
        if (defTurno.getUsuario()==null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        
        Usuario usu = UsuarioTransformer.transformarDTO(defTurno.getUsuario());
        turno.setUsuario(usu);
        /*
         * Usado para setear la agencia y generar turnos para agencias de PGJ
         */
        long discriminanteId = 0L; 
        
        ConfInstitucion confInstitucion = expedienteDAO.consultarInsitucionActual();
        if(confInstitucion.getConfInstitucionId().equals(Instituciones.PGJ.getValorId()) || confInstitucion.getConfInstitucionId().equals(Instituciones.PJ.getValorId())){
			if (defTurno.getUsuario() != null
					&& defTurno.getUsuario().getFuncionario() != null
					&& defTurno.getUsuario().getFuncionario().getDiscriminante() != null
					&& defTurno.getUsuario().getFuncionario().getDiscriminante()
							.getCatDiscriminanteId() != null) {
				
				discriminanteId = defTurno.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId();	
			    turno.setDiscriminante(new CatDiscriminante(defTurno.getUsuario().getFuncionario().getDiscriminante().getCatDiscriminanteId(),defTurno.getUsuario().getFuncionario().getDiscriminante().getClave()));
			}
        }
        log.debug("siguienteTurno :: " + siguienteTurno);
        
        ultimoTurno = turnoDAO.obtenerUltimoNumero(defTurno.getTipoTurno()
                .toString(), defTurno.getEsUrgente(), calTemp.getTime(),discriminanteId);

        if (ultimoTurno != null) {
            siguienteTurno = new Long(ultimoTurno);
            siguienteTurno++;
        } else
            siguienteTurno = new Long(1);

        turno.setNumeroTurno(siguienteTurno.toString());
        log.debug("siguienteTurno :: " + siguienteTurno);
        turnoDAO.create(turno);
        defTurno.setNumeroTurno(siguienteTurno.toString());
        return defTurno;
    }

}
