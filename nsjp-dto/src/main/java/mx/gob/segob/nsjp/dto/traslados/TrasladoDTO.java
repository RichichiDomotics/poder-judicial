package mx.gob.segob.nsjp.dto.traslados;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class TrasladoDTO extends GenericDTO
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7163965701382752239L;

	// Número consecutivo que  identifica de manera única del traslado
	private	Long idTraslado;

	// Fecha y hora en la que se llega  al destino del traslado
	private	Date fechaFintraslado;

	// Fecha en que se inicia el traslado
	private Date fechaIniciotraslado;

	private MotivoTrasladoDTO motivoTrasladoDTO;
	
	private MedioTrasladoDTO medioTrasladoDTO;

	/**
	 * Método de acceso al campo idTraslado.
	 * @return El valor del campo idTraslado
	 */
	public Long getIdTraslado() {
		return idTraslado;
	}

	/**
	 * Asigna el valor al campo idTraslado.
	 * @param idTraslado el valor idTraslado a asignar
	 */
	public void setIdTraslado(Long idTraslado) {
		this.idTraslado = idTraslado;
	}

	/**
	 * Método de acceso al campo fechaFintraslado.
	 * @return El valor del campo fechaFintraslado
	 */
	public Date getFechaFintraslado() {
		return fechaFintraslado;
	}

	/**
	 * Asigna el valor al campo fechaFintraslado.
	 * @param fechaFintraslado el valor fechaFintraslado a asignar
	 */
	public void setFechaFintraslado(Date fechaFintraslado) {
		this.fechaFintraslado = fechaFintraslado;
	}

	/**
	 * Método de acceso al campo fechaIniciotraslado.
	 * @return El valor del campo fechaIniciotraslado
	 */
	public Date getFechaIniciotraslado() {
		return fechaIniciotraslado;
	}

	/**
	 * Asigna el valor al campo fechaIniciotraslado.
	 * @param fechaIniciotraslado el valor fechaIniciotraslado a asignar
	 */
	public void setFechaIniciotraslado(Date fechaIniciotraslado) {
		this.fechaIniciotraslado = fechaIniciotraslado;
	}

	/**
	 * Método de acceso al campo motivoTrasladoDTO.
	 * @return El valor del campo motivoTrasladoDTO
	 */
	public MotivoTrasladoDTO getMotivoTrasladoDTO() {
		return motivoTrasladoDTO;
	}

	/**
	 * Asigna el valor al campo motivoTrasladoDTO.
	 * @param motivoTrasladoDTO el valor motivoTrasladoDTO a asignar
	 */
	public void setMotivoTrasladoDTO(MotivoTrasladoDTO motivoTrasladoDTO) {
		this.motivoTrasladoDTO = motivoTrasladoDTO;
	}

	/**
	 * Método de acceso al campo medioTrasladoDTO.
	 * @return El valor del campo medioTrasladoDTO
	 */
	public MedioTrasladoDTO getMedioTrasladoDTO() {
		return medioTrasladoDTO;
	}

	/**
	 * Asigna el valor al campo medioTrasladoDTO.
	 * @param medioTrasladoDTO el valor medioTrasladoDTO a asignar
	 */
	public void setMedioTrasladoDTO(MedioTrasladoDTO medioTrasladoDTO) {
		this.medioTrasladoDTO = medioTrasladoDTO;
	}

}
