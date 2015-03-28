/**
* Nombre del Programa 		: EslabonCadenaCustodiaForm.java
* Autor                     : ArmandoCT
* Compania                  : Ultrasist
* Proyecto                  : NSJP                    Fecha: 3 Agostos 2011
* Marca de cambio        	: N/A
* Descripcion General    	: Forma para el objeto Hecho.
* Programa Dependiente  	: N/A
* Programa Subsecuente 		: N/A
* Cond. de ejecucion        : N/A
* Dias de ejecucion         : N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                     : N/A
* Compania               	: N/A
* Proyecto                 	: N/A                                 Fecha: N/A
* Modificacion           	: N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.web.cadenadecustodia.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma para el objeto Hecho.
 * @version 1.0
 * @author ArmandoCT
 *
 */
public class EslabonCadenaCustodiaForm extends GenericForm{

	/**
	 * Serialize de la clase
	 */
	private static final long serialVersionUID = 1L;
	//enrega
    private String tipoEslabon;
    private String nombrePersonaEntrega;
    private String fechaEntrega;
    private String horaEntrega;
    private String obsEntrega;
	private String institucionEnt;
	//recepcion
	private String tipoEslabonRec;
	private String nombrePersonaRecepcion;
	private String fechaRecepcion;
	private String horaRecepcion;
	private String obsRecepcion;
	private String institucionRec;
	private String idsEvidencias;
	
	//Lapso prestamo
	private String fechaIniPres;
    private String horaIniPres;
    private String fechaFinPres;
    private String horaFinPres;
    
    /**
	 * @return the fechaIniPres
	 */
	public String getFechaIniPres() {
		return fechaIniPres;
	}
	/**
	 * @param fechaIniPres the fechaIniPres to set
	 */
	public void setFechaIniPres(String fechaIniPres) {
		this.fechaIniPres = fechaIniPres;
	}
	
	/**
	 * @return the horaIniPres
	 */
	public String getHoraIniPres() {
		return horaIniPres;
	}
	/**
	 * @param horaIniPres the horaIniPres to set
	 */
	public void setHoraIniPres(String horaIniPres) {
		this.horaIniPres = horaIniPres;
	}
	
	/**
	 * @return the fechaFinPres
	 */
	public String getFechaFinPres() {
		return fechaFinPres;
	}
	/**
	 * @param fechaFinPres the fechaFinPres to set
	 */
	public void setFechaFinPres(String fechaFinPres) {
		this.fechaFinPres = fechaFinPres;
	}
	
	/**
	 * @return the horaFinPres
	 */
	public String getHoraFinPres() {
		return horaFinPres;
	}
	/**
	 * @param horaFinPres the horaFinPres to set
	 */
	public void setHoraFinPres(String horaFinPres) {
		this.horaFinPres = horaFinPres;
	}
	
	/**
	 * @return the tipoEslabonRec
	 */
	public String getTipoEslabonRec() {
		return tipoEslabonRec;
	}
	/**
	 * @param tipoEslabonRec the tipoEslabonRec to set
	 */
	public void setTipoEslabonRec(String tipoEslabonRec) {
		this.tipoEslabonRec = tipoEslabonRec;
	}
	/**
	 * @return the institucionEnt
	 */
	public String getInstitucionEnt() {
		return institucionEnt;
	}
	/**
	 * @param institucionEnt the institucionEnt to set
	 */
	public void setInstitucionEnt(String institucionEnt) {
		this.institucionEnt = institucionEnt;
	}
	/**
	 * @return the institucionRec
	 */
	public String getInstitucionRec() {
		return institucionRec;
	}
	/**
	 * @param institucionRec the institucionRec to set
	 */
	public void setInstitucionRec(String institucionRec) {
		this.institucionRec = institucionRec;
	}
	/**
	 * @return the idsEvidencias
	 */
	public String getIdsEvidencias() {
		return idsEvidencias;
	}
	/**
	 * @param idsEvidencias the idsEvidencias to set
	 */
	public void setIdsEvidencias(String idsEvidencias) {
		this.idsEvidencias = idsEvidencias;
	}
	/**
	 * @return the tipoEslabon
	 */
	public String getTipoEslabon() {
		return tipoEslabon;
	}
	/**
	 * @param tipoEslabon the tipoEslabon to set
	 */
	public void setTipoEslabon(String tipoEslabon) {
		this.tipoEslabon = tipoEslabon;
	}
	/**
	 * @return the nombrePersonaEntrega
	 */
	public String getNombrePersonaEntrega() {
		return nombrePersonaEntrega;
	}
	/**
	 * @param nombrePersonaEntrega the nombrePersonaEntrega to set
	 */
	public void setNombrePersonaEntrega(String nombrePersonaEntrega) {
		this.nombrePersonaEntrega = nombrePersonaEntrega;
	}
	/**
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}
	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	/**
	 * @return the horaEntrega
	 */
	public String getHoraEntrega() {
		return horaEntrega;
	}
	/**
	 * @param horaEntrega the horaEntrega to set
	 */
	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	/**
	 * @return the obsEntrega
	 */
	public String getObsEntrega() {
		return obsEntrega;
	}
	/**
	 * @param obsEntrega the obsEntrega to set
	 */
	public void setObsEntrega(String obsEntrega) {
		this.obsEntrega = obsEntrega;
	}

	/**
	 * @return the nombrePersonaRecepcion
	 */
	public String getNombrePersonaRecepcion() {
		return nombrePersonaRecepcion;
	}
	/**
	 * @param nombrePersonaRecepcion the nombrePersonaRecepcion to set
	 */
	public void setNombrePersonaRecepcion(String nombrePersonaRecepcion) {
		this.nombrePersonaRecepcion = nombrePersonaRecepcion;
	}
	/**
	 * @return the fechaRecepcion
	 */
	public String getFechaRecepcion() {
		return fechaRecepcion;
	}
	/**
	 * @param fechaRecepcion the fechaRecepcion to set
	 */
	public void setFechaRecepcion(String fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}
	/**
	 * @return the horaRecepcion
	 */
	public String getHoraRecepcion() {
		return horaRecepcion;
	}
	/**
	 * @param horaRecepcion the horaRecepcion to set
	 */
	public void setHoraRecepcion(String horaRecepcion) {
		this.horaRecepcion = horaRecepcion;
	}
	/**
	 * @return the obsRecepcion
	 */
	public String getObsRecepcion() {
		return obsRecepcion;
	}
	/**
	 * @param obsRecepcion the obsRecepcion to set
	 */
	public void setObsRecepcion(String obsRecepcion) {
		this.obsRecepcion = obsRecepcion;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    
}
