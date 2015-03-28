/**
* Nombre del Programa 	: IngresarDomicilioForm.java                                    
* Autor               	: Alejandro Galavíz                                              
* Compania            	: Ultrasist                                                
* Proyecto            	: NSJP              			Fecha:01/03/2011 
* Marca de cambio     	: N/A                                                     
* Descripcion General   : Clase que implementa la forma que se tiene en la JSP 
* Programa Dependiente  : N/A                                                      
* Programa Subsecuente 	: N/A                                                      
* Cond. de ejecucion    : N/A                                                    
* Dias de ejecucion     : N/A                             Horario: N/A
*                               MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                 :N/A                                                           
* Compania              :N/A                                                           
* Proyecto              :N/A                                   Fecha: N/A       
* Modificacion          :N/A                                                           
*------------------------------------------------------------------------------      
*/
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.web.base.form.GenericForm;


/**
 * Clase que implementa la forma que se tiene en la JSP Ingresar domicilio
 * 
 * @version 1.0 
 * @author AlejandroGA
 */
public class IngresarDomicilioForm extends GenericForm {
	
	private static final long serialVersionUID = 1L;
	
	/**Nombre del pais*/
	private String gcPais;
	/** identificador del pais */
	private Long glCatPaisId;
	
	/**Nombre de la entidad federativa (Abreviacion)*/
	private String gcAbreviacion;
	/**identificador de la entidad federativa*/
	private Long glCatEntidadFederativaId;
	
	/**Nombre de la ciudad*/
	private String gcCiudad;
	/**identificador de la ciudad*/
	private Long glCatCiudadId;
	
	
	/**Nombre de la delegacion o municipio*/
	private String gcDelgMun;
	/** identificador de la delegacion o Municipio */
	private Long glDelgMunId;
	
	
	
	/**Nombre del asentamiento o colonia*/
	private String nombreAsentColonia;
	/**identificador del asentamiento o colonia*/
	private String idAsentColonia;
	
	/**Nombre del tipo de asentamiento*/
	private String gcTipoAsentamiento;
	/**Identificador del Tipo de Asentamiento*/
	private Long glCatTipoAsentamientoId;
	
	
	/**Codigo postal*/
	private String codigoPostal;


	/**
	 * Serializacion
	 * @return serialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	/**
	 * @return gcPais	,el valor del nombre del pais
	 */
	public String getGcPais() {
		return gcPais;
	}



	/**
	 * @param gcPais	,el nombre del pais
	 */
	public void setGcPais(String gcPais) {
		this.gcPais = gcPais;
	}



	/**
	 * @return glCatPaisId	,el valor del id del pais
	 */
	public Long getGlCatPaisId() {
		return glCatPaisId;
	}



	/**
	 * @param glCatPaisId 	,el id del pais
	 */
	public void setGlCatPaisId(Long glCatPaisId) {
		this.glCatPaisId = glCatPaisId;
	}

	/**
	 * @return gcAbreviacion	,el valor del nombre de la entidad federativa
	 */
	public String getGcAbreviacion() {
		return gcAbreviacion;
	}


	/**
	 * @param gcAbreviacion		,el nombre de la entidad
	 */
	public void setGcAbreviacion(String gcAbreviacion) {
		this.gcAbreviacion = gcAbreviacion;
	}


	/**
	 * @return glCatEntidadFederativaId	,el valor del id de la entidad federativa
	 */
	public Long getGlCatEntidadFederativaId() {
		return glCatEntidadFederativaId;
	}


	/**
	 * @param glCatEntidadFederativaId , el id de la entidad federativa
	 */
	public void setGlCatEntidadFederativaId(Long glCatEntidadFederativaId) {
		this.glCatEntidadFederativaId = glCatEntidadFederativaId;
	}

	/**
	 * 
	 * @return gcDelgMun , 	el valor del nombre de la delegacion o municipio
	 */
	public String getGcDelgMun() {
		return gcDelgMun;
	}

	/**
	 * 
	 * @param gcDelgMun, el nombre de la delagacion o municipio
	 */
	public void setGcDelgMun(String gcDelgMun) {
		this.gcDelgMun = gcDelgMun;
	}

	/**
	 * 
	 * @return glDelgMunId, el valor del id de la delegacion o municipio
	 */
	public Long getGlDelgMunId() {
		return glDelgMunId;
	}

	/**
	 * 
	 * @param glDelgMunId, el id de la delagacion o municipio
	 */
	public void setGlDelgMunId(Long glDelgMunId) {
		this.glDelgMunId = glDelgMunId;
	}


	/**
	 * @return gcCiudad, el valor del nombre de la ciudad
	 */
	public String getGcCiudad() {
		return gcCiudad;
	}


	/**
	 * @param gcCiudad, el nombre de la ciudad
	 */
	public void setGcCiudad(String gcCiudad) {
		this.gcCiudad = gcCiudad;
	}

	/**
	 * @return glCatCiudadId, el valor id de la ciudad
	 */
	public Long getGlCatCiudadId() {
		return glCatCiudadId;
	}

	/**
	 * @param glCatCiudadId, el id de la ciudad
	 */
	public void setGlCatCiudadId(Long glCatCiudadId) {
		this.glCatCiudadId = glCatCiudadId;
	}


	/**
	 * @return nombreAsentColonia , valor del nombre del asentamineto o colonia
	 */
	public String getNombreAsentColonia() {
		return nombreAsentColonia;
	}

	/**
	 * @param nombreAsentColonia, el nombre del asentamiento o colonia
	 */
	public void setNombreAsentColonia(String nombreAsentColonia) {
		this.nombreAsentColonia = nombreAsentColonia;
	}

	/**
	 * @return idAsentColonia , el valor del id del asentamiento o colonia
	 */
	public String getIdAsentColonia() {
		return idAsentColonia;
	}

	/**
	 * @param idAsentColonia, el id del asentamiento o colonia
	 */
	public void setIdAsentColonia(String idAsentColonia) {
		this.idAsentColonia = idAsentColonia;
	}
	
	public String getGcTipoAsentamiento() {
		return gcTipoAsentamiento;
	}


	public void setGcTipoAsentamiento(String gcTipoAsentamiento) {
		this.gcTipoAsentamiento = gcTipoAsentamiento;
	}


	public Long getGlCatTipoAsentamientoId() {
		return glCatTipoAsentamientoId;
	}


	public void setGlCatTipoAsentamientoId(Long glCatTipoAsentamientoId) {
		this.glCatTipoAsentamientoId = glCatTipoAsentamientoId;
	}


	/**
	 * @return, el codigoPostal , el valor del codigo postal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal ,el codigo postal 
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
}
