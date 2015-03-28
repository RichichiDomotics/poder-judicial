/**
 * Nombre del Programa : HechoTransfromer.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jun 2011
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
package mx.gob.segob.nsjp.service.hecho.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.Tiempo;
import mx.gob.segob.nsjp.service.lugar.impl.transform.LugarTransformer;

/**
 * Convierte objetos HechoDTO a Hecho y viceversa.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class HechoTransformer {

	/**
	 * Convierte de {@link HechoDTO} a {@link Hecho}
	 * 
	 * @param hechoDTO
	 * @return
	 */
	public static Hecho transformarHecho(HechoDTO dto) {
		Hecho hecho = new Hecho();

		hecho.setHechoId(dto.getHechoId());
		hecho.setDescNarrativa(dto.getDescNarrativa());

		if (dto.getExpediente() != null)
			hecho.setExpediente(new Expediente(dto.getExpediente()
					.getExpedienteId()));
		if (dto.getLugar() != null)
			hecho.setLugar(LugarTransformer.transformarLugar(dto.getLugar()));
		if (dto.getTiempo() != null)
			hecho.setTiempo(TiempoTransformer.transformarTiempo(dto.getTiempo()));
		hecho.setFechaDeArribo(dto.getFechaDeArribo());
		return hecho;
	}

	/**
	 * 
	 * @param hecho
	 * @return
	 */
	public static HechoDTO transformarHecho(Hecho hecho) {
		HechoDTO hechoDTO = new HechoDTO();

		hechoDTO.setHechoId(hecho.getHechoId());
		hechoDTO.setDescNarrativa(hecho.getDescNarrativa());
		hechoDTO.setExpediente(new ExpedienteDTO(hecho.getExpediente()
				.getExpedienteId()));

		if (hecho.getTiempo() != null)
			hechoDTO.setTiempo(transformarTiempo(hecho.getTiempo()));
		if(hecho.getLugar()!=null)
			hechoDTO.setLugar(LugarTransformer.transformarLugar(hecho.getLugar()));
		hechoDTO.setFechaDeArribo(hecho.getFechaDeArribo());
		return hechoDTO;
	}

	/**
	 * 
	 * @param tiempo
	 * @return
	 */
	private static TiempoDTO transformarTiempo(Tiempo tiempo) {
		TiempoDTO tiempoDTO = new TiempoDTO();

		tiempoDTO.setTiempoId(tiempo.getTiempoId());
		tiempoDTO.setDescripcion(tiempo.getDescripcion());
		tiempoDTO.setFechaInicio(tiempo.getFechaInicio());
		tiempoDTO.setFechaFin(tiempo.getFechaFin());

		if (tiempo.getTipoRegistro() != null)
			tiempoDTO.setTipoRegistro(new ValorDTO(tiempo.getTipoRegistro()
					.getValorId()));

		return tiempoDTO;
	}
}
