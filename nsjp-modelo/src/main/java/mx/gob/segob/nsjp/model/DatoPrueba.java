/**
* Nombre del Programa : DatoPrueba.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/09/2011
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
package mx.gob.segob.nsjp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Clase que representa un Dato de Prueba 
 * que es la referencia a una evidencia que se empleará como prueba
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Entity
@Table(name = "DatoPrueba")
public class DatoPrueba implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5943609700153505529L;
	private Long datoPruebaId;
	private String nombreDato;
	private String numeroIdentificacion;
	private String descripcion;
	private String folioCadenaCustodia;
	private Boolean esAceptado;
	private Date tiempoCancelacion;
	
	//Relaciones
	private Elemento elementoPrueba;
	private Documento documentoPrueba;
	private Valor motivoCancelacion;
	private List<RelacionPruebaInvolucrado> relacionesPruebaInvolucrado;
	private Expediente expediente;
	private List<RelacionDatoMedioPrueba> relacionesDatosMedioPrueba;
	
	
	public DatoPrueba(){
	}
	
	

	/**
	 * @param datoPruebaId
	 */
	public DatoPrueba(Long datoPruebaId) {
		super();
		this.datoPruebaId = datoPruebaId;
	}



	public DatoPrueba(Long datoPruebaId, String nombreDato,
			String numeroIdentificacion) {
		super();
		this.datoPruebaId = datoPruebaId;
		this.nombreDato = nombreDato;
		this.numeroIdentificacion = numeroIdentificacion;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DatoPrueba_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getDatoPruebaId() {
		return datoPruebaId;
	}

	public void setDatoPruebaId(Long datoPruebaId) {
		this.datoPruebaId = datoPruebaId;
	}

	@Column(name = "cNombreDato", nullable = false, length = 30)
	public String getNombreDato() {
		return nombreDato;
	}

	public void setNombreDato(String nombreDato) {
		this.nombreDato = nombreDato;
	}

	@Column(name = "cNumeroIdentificacion", nullable = false, length = 20)
	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	@Column(name = "cDescripcion", length = 200)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "cFolioCadenaCustodia", length = 30)
	public String getFolioCadenaCustodia() {
		return folioCadenaCustodia;
	}

	public void setFolioCadenaCustodia(String folioCadenaCustodia) {
		this.folioCadenaCustodia = folioCadenaCustodia;
	}

	@Column(name = "bEsAceptado", precision = 1, scale = 0)
	public Boolean getEsAceptado() {
		return esAceptado;
	}

	public void setEsAceptado(Boolean esAceptado) {
		this.esAceptado = esAceptado;
	}

	@Column(name = "dTiempoCancelacion", length = 23)
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	public Date getTiempoCancelacion() {
		return tiempoCancelacion;
	}

	public void setTiempoCancelacion(Date tiempoCancelacion) {
		this.tiempoCancelacion = tiempoCancelacion;
	}

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Elemento_id", nullable = true)
	public Elemento getElementoPrueba() {
		return elementoPrueba;
	}

	public void setElementoPrueba(Elemento elementoPrueba) {
		this.elementoPrueba = elementoPrueba;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Documento_id", nullable = true)
	public Documento getDocumentoPrueba() {
		return documentoPrueba;
	}

	public void setDocumentoPrueba(Documento documentoPrueba) {
		this.documentoPrueba = documentoPrueba;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MotivoCancelacion_val")
	public Valor getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(Valor motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "datoPrueba")	
	public List<RelacionPruebaInvolucrado> getRelacionesPruebaInvolucrado() {
		return relacionesPruebaInvolucrado;
	}

	public void setRelacionesPruebaInvolucrado(
			List<RelacionPruebaInvolucrado> relacionesPruebaInvolucrado) {
		this.relacionesPruebaInvolucrado = relacionesPruebaInvolucrado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Expediente_id")
	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "datoPrueba")
	public List<RelacionDatoMedioPrueba> getRelacionesDatosMedioPrueba() {
		return relacionesDatosMedioPrueba;
	}

	public void setRelacionesDatosMedioPrueba(
			List<RelacionDatoMedioPrueba> relacionesDatosMedioPrueba) {
		this.relacionesDatosMedioPrueba = relacionesDatosMedioPrueba;
	}
}
