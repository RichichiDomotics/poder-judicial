/**
 * Nombre del Programa  : MedidaWSDTO.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 29 Ago 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Contenedor de la informacicón de la medida cautelar
 * que se desea enviar.
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dto.medida;

import java.util.Date;

import mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO;

public class MedidaWSDTO extends DocumentoWSDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nombre del Sujeto/Involucrado al que se aplica la medida 
	 */
    private String 	nombreSujeto;
	
    /**
	 * Apellido Paterno del Sujeto/Involucrado al que se aplica la medida 
	 */
    private String 	aPaternoSujeto;
	
    /**
	 * Apellido Materno del Sujeto/Involucrado al que se aplica la medida 
	 */
    private String 	aMaternoSujeto;
    
    /**
     * Fecha de Finalización de la aplicación de la medida
     */
    private Date 	fechaFin;
    
    /**
     * Fecha de Inicio de la aplicación de la medida
     */
    private Date 	fechaInicio;
    
    /**
     * Nombre del Juez que ordena la medida
     */
    private String 	juezOrdena;
    
    /**
     * Descripción de la medida
     */
    private String 	descripcionMedida;
    
    /**
     * Identificador del estatus de la medida
     */
    private Long 	idEstatus;
    
    /**
     * Identificador del tipo de medida
     */
    private Long 	idValorTipoMedida;
    
    /**
     * Identificador de la periodicidad de la medida
     */
    private Long 	idValorPeriodicidad;
    
    /**
     * Numero de Caso al que pertenece la medida
     */
    private String numeroCaso;
    
    /**
     *  Numero de la Causa en PJ a la que pertenece la medida
     */
    private String 	numeroCausa;
    
    /**
     *  Numero de la carpeta de ejecución en PJ a la que pertenece la medida
     */
    private String 	numeroCarpetaEjecucion;


	/**
	 * Regresa el valor de la propiedad nombreSujeto
	 * @return the nombreSujeto
	 */
	public String getNombreSujeto() {
		return nombreSujeto;
	}

	/**
	 * Establece el valor de la propiedad nombreSujeto
	 * @param nombreSujeto valo nombreSujeto a almacenar
	 */
	public void setNombreSujeto(String nombreSujeto) {
		this.nombreSujeto = nombreSujeto;
	}

	/**
	 * Regresa el valor de la propiedad aPaternoSujeto
	 * @return the aPaternoSujeto
	 */
	public String getaPaternoSujeto() {
		return aPaternoSujeto;
	}

	/**
	 * Establece el valor de la propiedad aPaternoSujeto
	 * @param aPaternoSujeto valo aPaternoSujeto a almacenar
	 */
	public void setaPaternoSujeto(String aPaternoSujeto) {
		this.aPaternoSujeto = aPaternoSujeto;
	}

	/**
	 * Regresa el valor de la propiedad aMaternoSujeto
	 * @return the aMaternoSujeto
	 */
	public String getaMaternoSujeto() {
		return aMaternoSujeto;
	}

	/**
	 * Establece el valor de la propiedad aMaternoSujeto
	 * @param aMaternoSujeto valo aMaternoSujeto a almacenar
	 */
	public void setaMaternoSujeto(String aMaternoSujeto) {
		this.aMaternoSujeto = aMaternoSujeto;
	}

	/**
	 * Regresa el valor de la propiedad fechaFin
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Establece el valor de la propiedad fechaFin
	 * @param fechaFin valo fechaFin a almacenar
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Regresa el valor de la propiedad fechaInicio
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Establece el valor de la propiedad fechaInicio
	 * @param fechaInicio valo fechaInicio a almacenar
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Regresa el valor de la propiedad juezOrdena
	 * @return the juezOrdena
	 */
	public String getJuezOrdena() {
		return juezOrdena;
	}

	/**
	 * Establece el valor de la propiedad juezOrdena
	 * @param juezOrdena valo juezOrdena a almacenar
	 */
	public void setJuezOrdena(String juezOrdena) {
		this.juezOrdena = juezOrdena;
	}

	/**
	 * Regresa el valor de la propiedad descripcionMedida
	 * @return the descripcionMedida
	 */
	public String getDescripcionMedida() {
		return descripcionMedida;
	}

	/**
	 * Establece el valor de la propiedad descripcionMedida
	 * @param descripcionMedida valo descripcionMedida a almacenar
	 */
	public void setDescripcionMedida(String descripcionMedida) {
		this.descripcionMedida = descripcionMedida;
	}

	/**
	 * Regresa el valor de la propiedad idEstatus
	 * @return the idEstatus
	 */
	public Long getIdEstatus() {
		return idEstatus;
	}

	/**
	 * Establece el valor de la propiedad idEstatus
	 * @param idEstatus valo idEstatus a almacenar
	 */
	public void setIdEstatus(Long idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * Regresa el valor de la propiedad idValorTipoMedida
	 * @return the idValorTipoMedida
	 */
	public Long getIdValorTipoMedida() {
		return idValorTipoMedida;
	}

	/**
	 * Establece el valor de la propiedad idValorTipoMedida
	 * @param idValorTipoMedida valo idValorTipoMedida a almacenar
	 */
	public void setIdValorTipoMedida(Long idValorTipoMedida) {
		this.idValorTipoMedida = idValorTipoMedida;
	}

	/**
	 * Regresa el valor de la propiedad idValorPeriodicidad
	 * @return the idValorPeriodicidad
	 */
	public Long getIdValorPeriodicidad() {
		return idValorPeriodicidad;
	}

	/**
	 * Establece el valor de la propiedad idValorPeriodicidad
	 * @param idValorPeriodicidad valo idValorPeriodicidad a almacenar
	 */
	public void setIdValorPeriodicidad(Long idValorPeriodicidad) {
		this.idValorPeriodicidad = idValorPeriodicidad;
	}

	/**
	 * Regresa el valor de la propiedad numeroCaso
	 * @return the numeroCaso
	 */
	public String getNumeroCaso() {
		return numeroCaso;
	}
	

	/**
	 * Establece el valor de la propiedad numeroCaso
	 * @param numeroCaso valo numeroCaso a almacenar
	 */
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}


	/**
	 * Regresa el valor de la propiedad numeroCausa
	 * @return the numeroCausa
	 */
	public String getNumeroCausa() {
		return numeroCausa;
	}

	/**
	 * Establece el valor de la propiedad numeroCausa
	 * @param numeroCausa valo numeroCausa a almacenar
	 */
	public void setNumeroCausa(String numeroCausa) {
		this.numeroCausa = numeroCausa;
	}

	/**
	 * Regresa el valor de la propiedad numeroCarpetaEjecucion
	 * @return the numeroCarpetaEjecucion
	 */
	public String getNumeroCarpetaEjecucion() {
		return numeroCarpetaEjecucion;
	}

	/**
	 * Establece el valor de la propiedad numeroCarpetaEjecucion
	 * @param numeroCarpetaEjecucion valo numeroCarpetaEjecucion a almacenar
	 */
	public void setNumeroCarpetaEjecucion(String numeroCarpetaEjecucion) {
		this.numeroCarpetaEjecucion = numeroCarpetaEjecucion;
	}

}
