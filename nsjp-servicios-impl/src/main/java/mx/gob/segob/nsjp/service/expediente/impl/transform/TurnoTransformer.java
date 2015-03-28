/**
 * Nombre del Programa : TurnoTransformer.java
 * Autor                            : Hugo Serrano
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 24 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase para convertir objetos Turno
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
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusTurno;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoTurno;
import mx.gob.segob.nsjp.dto.catalogo.CatDiscriminanteDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.TurnoDTO;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Turno;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase para convertir objetos Turno
 * 
 * @version 1.0
 * @author Tattva-IT
 * 
 */
public class TurnoTransformer {
    /**
     * Logger
     */
    private final static Logger logger = Logger
            .getLogger(TurnoTransformer.class);
    
    public static List<TurnoDTO> transformarTurno(List<Turno> lturno) {
        List<TurnoDTO> lturnoDTO = new ArrayList<TurnoDTO>();

        for (Turno t : lturno) {
            TurnoDTO tdto = new TurnoDTO();
            tdto.setTurnoId(t.getTurnoId());
            tdto.setNumeroTurno(t.getNumeroTurno());
            tdto.setTipoTurno(TipoTurno.valueOf(t.getTipoTurno()));
            tdto.setEsUrgente(t.getEsUrgente());
            tdto.setFichaAtencion(t.getFichaAtencion());
            tdto.setEstado(EstatusTurno.getByValor(t.getEstatus().getValorId()));
            
            if(t.getDiscriminante() != null && t.getDiscriminante().getCatDiscriminanteId() != null && t.getDiscriminante().getClave() != null ){
            	tdto.setDiscriminante(new CatDiscriminanteDTO(t.getDiscriminante().getCatDiscriminanteId(),t.getDiscriminante().getClave()));
            }
            
            if (t.getExpediente() != null) {
                tdto.setExpediente(ExpedienteTransformer.transformaExpediente(t
                        .getExpediente()));
            }
            lturnoDTO.add(tdto);

        }
        return lturnoDTO;
    }

    public static TurnoDTO trannsformarTurno(Turno t) {
        logger.debug("t.getNumeroTurno() :: " + t.getNumeroTurno());
        TurnoDTO tdto = new TurnoDTO();
        tdto.setTurnoId(t.getTurnoId());
        tdto.setNumeroTurno(t.getNumeroTurno());
        tdto.setTipoTurno(TipoTurno.valueOf(t.getTipoTurno()));
        tdto.setEsUrgente(t.getEsUrgente());
        tdto.setFichaAtencion(t.getFichaAtencion());
        tdto.setFechaAtencion(t.getFechaAtencion());
        tdto.setNombreCiudadano(t.getNombreCiudadano());
        tdto.setApellidoPaternoCiudadano(t.getApellidoPaternoCiudadano());
        tdto.setApellidoMaternoCiudadano(t.getApellidoMaternoCiudadano());
        if (t.getEstatus() != null) {
            tdto.setEstado(EstatusTurno.getByValor(t.getEstatus().getValorId()));
        }
        if (t.getExpediente() != null) {
            tdto.setExpediente(ExpedienteTransformer.transformarExpedienteBasico(t
                    .getExpediente()));
        }
        if (t.getUsuario() != null) {
            tdto.setUsuario(UsuarioTransformer.transformarUsuarioMinimo(t
                    .getUsuario()));
        }
        
        if(t.getDiscriminante() != null && t.getDiscriminante().getCatDiscriminanteId() != null && t.getDiscriminante().getClave() != null){
        	tdto.setDiscriminante(new CatDiscriminanteDTO(t.getDiscriminante().getCatDiscriminanteId(),t.getDiscriminante().getClave()));
        }

        return tdto;
    }

    public static Turno transformarTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        turno.setTurnoId(turnoDTO.getTurnoId());
        turno.setNumeroTurno(turnoDTO.getNumeroTurno());
        if(turnoDTO.getTipoTurno()!= null)
        	turno.setTipoTurno(turnoDTO.getTipoTurno().name());
        turno.setEsUrgente(turnoDTO.getEsUrgente());
        turno.setFichaAtencion(turnoDTO.getFichaAtencion());
        turno.setFechaAtencion(turnoDTO.getFechaAtencion());
        turno.setNombreCiudadano(turnoDTO.getNombreCiudadano());
        turno.setApellidoPaternoCiudadano(turnoDTO.getApellidoPaternoCiudadano());
        turno.setApellidoMaternoCiudadano(turnoDTO.getApellidoMaternoCiudadano());
        
        if (turnoDTO.getEstado()!= null) 
            turno.setEstatus(new Valor(turnoDTO.getEstado().getValorId()));
        if (turnoDTO.getExpediente() != null && turnoDTO.getExpediente().getExpedienteId() != null ) 
            turno.setExpediente( new Expediente(turnoDTO.getExpediente().getExpedienteId()));
        if (turnoDTO.getUsuario() != null && turnoDTO.getUsuario().getIdUsuario()!=null){
        	
        	turno.setUsuario( new Usuario(turnoDTO.getUsuario().getIdUsuario()));
        	
        	if(turnoDTO.getUsuario().getFuncionario() != null){
        		
				if (turnoDTO.getUsuario().getFuncionario().getDiscriminante() != null
						&& turnoDTO.getUsuario().getFuncionario()
								.getDiscriminante().getCatDiscriminanteId() != null) {
					
					Funcionario func = new Funcionario();
					CatDiscriminante disc = new CatDiscriminante();
					
					disc.setCatDiscriminanteId(turnoDTO.getUsuario().getFuncionario()
								.getDiscriminante().getCatDiscriminanteId());
					func.setDiscriminante(disc);	
					turno.getUsuario().setFuncionario(func);
				}
        	}
        }
            
        if(turnoDTO.getDiscriminante() != null && turnoDTO.getDiscriminante().getCatDiscriminanteId() != null && turnoDTO.getDiscriminante().getClave() != null){
        	turno.setDiscriminante(new CatDiscriminante(turnoDTO.getDiscriminante().getCatDiscriminanteId(),turnoDTO.getDiscriminante().getClave()));
        }
        return turno;
    }
}
