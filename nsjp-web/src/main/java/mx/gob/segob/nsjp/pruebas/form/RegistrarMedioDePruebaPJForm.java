/**
 * 
 */
package mx.gob.segob.nsjp.pruebas.form;

import org.apache.struts.upload.FormFile;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * @author AlejandroGA
 *
 */
public class RegistrarMedioDePruebaPJForm extends GenericForm{
	
private static final long serialVersionUID = 1L;
	
	/** archivo adjunto */
	private FormFile archivoAdjunto;
	
	/** id de la audiencia */
	private String audienciaId;
	
	/** tipo del medio de prueba, documento */
	private String tipoMedioPrueba;
	
	/** sub tipo del medio de prueba, texto,foto */
	private Integer subTipoMedioPrueba;
	
	/** nombre del documento */
	private String nombreDoc;
	
	/** num de id del documento */
	private String numIdDoc;
	
	/** referencia a ubicacion fisica del documento*/
	private String refUbicacionFisica;
	
	/** descripcion del documento */
	private String descDocumento;
	
	/** numero de expediente */
	private String numeroExpediente;
	
	/**Dato de prueba Id con el que se relacionara el medio*/
	private Long datoPruebaId;

	
	/**
	 * @return el archivoAdjunto
	 */
	public FormFile getArchivoAdjunto() {
		return archivoAdjunto;
	}

	/**
	 * @param archivoAdjunto el archivoAdjunto su valor
	 */
	public void setArchivoAdjunto(FormFile archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}

	/**
	 * @return el audienciaId
	 */
	public String getAudienciaId() {
		return audienciaId;
	}

	/**
	 * @param audienciaId el audienciaId su valor
	 */
	public void setAudienciaId(String audienciaId) {
		this.audienciaId = audienciaId;
	}

	/**
	 * @return el tipoMedioPrueba
	 */
	public String getTipoMedioPrueba() {
		return tipoMedioPrueba;
	}

	/**
	 * @param tipoMedioPrueba el tipoMedioPrueba su valor
	 */
	public void setTipoMedioPrueba(String tipoMedioPrueba) {
		this.tipoMedioPrueba = tipoMedioPrueba;
	}

	/**
	 * @return el subTipoMedioPrueba
	 */
	public Integer getSubTipoMedioPrueba() {
		return subTipoMedioPrueba;
	}

	/**
	 * @param subTipoMedioPrueba el subTipoMedioPrueba su valor
	 */
	public void setSubTipoMedioPrueba(Integer subTipoMedioPrueba) {
		this.subTipoMedioPrueba = subTipoMedioPrueba;
	}

	/**
	 * @return el nombreDoc
	 */
	public String getNombreDoc() {
		return nombreDoc;
	}

	/**
	 * @param nombreDoc el nombreDoc su valor
	 */
	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	/**
	 * @return el numIdDoc
	 */
	public String getNumIdDoc() {
		return numIdDoc;
	}

	/**
	 * @param numIdDoc el numIdDoc su valor
	 */
	public void setNumIdDoc(String numIdDoc) {
		this.numIdDoc = numIdDoc;
	}

	/**
	 * @return el refUbicacionFisica
	 */
	public String getRefUbicacionFisica() {
		return refUbicacionFisica;
	}

	/**
	 * @param refUbicacionFisica el refUbicacionFisica su valor
	 */
	public void setRefUbicacionFisica(String refUbicacionFisica) {
		this.refUbicacionFisica = refUbicacionFisica;
	}

	/**
	 * @return el descDocumento
	 */
	public String getDescDocumento() {
		return descDocumento;
	}

	/**
	 * @param descDocumento el descDocumento su valor
	 */
	public void setDescDocumento(String descDocumento) {
		this.descDocumento = descDocumento;
	}

	/**
	 * @return el numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * @param numeroExpediente el numeroExpediente su valor
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * @return the datoPruebaId
	 */
	public Long getDatoPruebaId() {
		return datoPruebaId;
	}

	/**
	 * @param datoPruebaId the datoPruebaId to set
	 */
	public void setDatoPruebaId(Long datoPruebaId) {
		this.datoPruebaId = datoPruebaId;
	}

	/**
	 * @return el serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
