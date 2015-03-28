package mx.gob.segob.nsjp.dto.evidencia;

import java.util.Date;

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;

public class EslabonWSDTO extends GenericWSDTO {

    private String quienEntrega;
    private String quienRecibe;
    private Date fechaEntrega;
    private Date fechaRecibe;
    private String observacion;
    private Integer numeroEslabon;
    private EvidenciaWSDTO evidencia;
    private Long idTpoEslabon;
    private DocumentoWSDTO documento;
    private String ubicacionFisica;
    private String posicion;

	/**
	 * Regresa el valor de la propiedad quienEntrega
	 * @return the quienEntrega
	 */
	public String getQuienEntrega() {
		return quienEntrega;
	}

	/**
	 * Establece el valor de la propiedad quienEntrega
	 * @param quienEntrega valo quienEntrega a almacenar
	 */
	public void setQuienEntrega(String quienEntrega) {
		this.quienEntrega = quienEntrega;
	}

	/**
	 * Regresa el valor de la propiedad quienRecibe
	 * @return the quienRecibe
	 */
	public String getQuienRecibe() {
		return quienRecibe;
	}

	/**
	 * Establece el valor de la propiedad quienRecibe
	 * @param quienRecibe valo quienRecibe a almacenar
	 */
	public void setQuienRecibe(String quienRecibe) {
		this.quienRecibe = quienRecibe;
	}

	/**
	 * Regresa el valor de la propiedad fechaEntrega
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Establece el valor de la propiedad fechaEntrega
	 * @param fechaEntrega valo fechaEntrega a almacenar
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Regresa el valor de la propiedad fechaRecibe
	 * @return the fechaRecibe
	 */
	public Date getFechaRecibe() {
		return fechaRecibe;
	}

	/**
	 * Establece el valor de la propiedad fechaRecibe
	 * @param fechaRecibe valo fechaRecibe a almacenar
	 */
	public void setFechaRecibe(Date fechaRecibe) {
		this.fechaRecibe = fechaRecibe;
	}

	/**
	 * Regresa el valor de la propiedad observacion
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * Establece el valor de la propiedad observacion
	 * @param observacion valo observacion a almacenar
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * Regresa el valor de la propiedad numeroEslabon
	 * @return the numeroEslabon
	 */
	public Integer getNumeroEslabon() {
		return numeroEslabon;
	}

	/**
	 * Establece el valor de la propiedad numeroEslabon
	 * @param numeroEslabon valo numeroEslabon a almacenar
	 */
	public void setNumeroEslabon(Integer numeroEslabon) {
		this.numeroEslabon = numeroEslabon;
	}

	/**
	 * Regresa el valor de la propiedad evidencia
	 * @return the evidencia
	 */
	public EvidenciaWSDTO getEvidencia() {
		return evidencia;
	}

	/**
	 * Establece el valor de la propiedad evidencia
	 * @param evidencia valo evidencia a almacenar
	 */
	public void setEvidencia(EvidenciaWSDTO evidencia) {
		this.evidencia = evidencia;
	}

	/**
	 * Regresa el valor de la propiedad idTpoEslabon
	 * @return the idTpoEslabon
	 */
	public Long getIdTpoEslabon() {
		return idTpoEslabon;
	}

	/**
	 * Establece el valor de la propiedad idTpoEslabon
	 * @param idTpoEslabon valo idTpoEslabon a almacenar
	 */
	public void setIdTpoEslabon(Long idTpoEslabon) {
		this.idTpoEslabon = idTpoEslabon;
	}

	/**
	 * Regresa el valor de la propiedad documento
	 * @return the documento
	 */
	public DocumentoWSDTO getDocumento() {
		return documento;
	}

	/**
	 * Establece el valor de la propiedad documento
	 * @param documento valo documento a almacenar
	 */
	public void setDocumento(DocumentoWSDTO documento) {
		this.documento = documento;
	}

	/**
	 * Regresa el valor de la propiedad ubicacionFisica
	 * @return the ubicacionFisica
	 */
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}

	/**
	 * Establece el valor de la propiedad ubicacionFisica
	 * @param ubicacionFisica valo ubicacionFisica a almacenar
	 */
	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	/**
	 * Regresa el valor de la propiedad posicion
	 * @return the posicion
	 */
	public String getPosicion() {
		return posicion;
	}

	/**
	 * Establece el valor de la propiedad posicion
	 * @param posicion valo posicion a almacenar
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
}
