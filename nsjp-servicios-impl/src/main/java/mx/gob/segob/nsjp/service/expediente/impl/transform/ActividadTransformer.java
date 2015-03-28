/**
 * Nombre del Programa : ActividadTransformer.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 May 2011
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
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.actividad.ActividadesRS;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ActividadTransformer {

	public static ActividadDTO transformarActividad(Actividad input) {
		if (input == null) {
			return null;
		}
		final ActividadDTO resp = new ActividadDTO();
		if(Actividades.getByValor(input.getTipoActividad()
				.getValorId()) != null) {
			resp.setTipoActividad(Actividades.getByValor(input.getTipoActividad()
				.getValorId()));
		} else { 
			resp.setTipoActividadRS(ActividadesRS.getByValor(input.getTipoActividad()
					.getValorId()));
		}
		resp.setNombre(input.getTipoActividad().getValor());
		resp.setFechaCreacion(input.getFechaCreacion());
		resp.setActividadId(input.getActividadId());
		UsuarioDTO loUsuarioDTO = new UsuarioDTO();
		loUsuarioDTO.setFuncionario(FuncionarioTransformer.transformarFuncionarioBasico(input.getFuncionario()));
		resp.setUsuario(loUsuarioDTO);
		return resp;
	}

	public static Actividad transformarActividadDTO(ActividadDTO dto) {
		Actividad activ = new Actividad();
		if (dto.getActividadId() != null)
			activ.setActividadId(dto.getActividadId());
		if (dto.getTipoActividad() != null)
			activ.setTipoActividad(new Valor(dto.getTipoActividad()
					.getValorId()));
		if (dto.getFechaCreacion() != null)
			activ.setFechaCreacion(dto.getFechaCreacion());
		if (dto.getUsuario() != null)
			if (dto.getUsuario().getFuncionario() != null)
				activ.setFuncionario(FuncionarioTransformer
						.modificarDefensor(dto.getUsuario().getFuncionario(),null));
		return activ;
	}

}
