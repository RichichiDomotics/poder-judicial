/**
* Nombre del Programa : MedioPrueba.java
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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entidad que representa el Medio de Prueba por el cual se presentará lo 
 * indicado en un Dato de Prueba.
 * El Medio de Prueba puede ser un Objeto, Documento o Persona
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Entity
@Table(name = "MedioPrueba")
public class MedioPrueba implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9074576372292642000L;
	private Long medioPruebaId;
	private String nombreMedio;
	private String numeroIdentificacion;
	private String descripcion;
	private String ubicacionFisica;
	
	//Relaciones
	private List<RelacionDatoMedioPrueba> relacionesDatoMedioPrueba;
	private Elemento elementoMedioPrueba;
	private Documento documentoMedioPrueba;
	
	
	public MedioPrueba(){
	}

	/**
	 * @param medioPruebaId
	 */
	public MedioPrueba(Long medioPruebaId) {
		super();
		this.medioPruebaId = medioPruebaId;
	}

	public MedioPrueba(Long medioPruebaId, String nombreMedio,
			String numeroIdentificacion, String descripcion,
			String ubicacionFisica) {
		super();
		this.medioPruebaId = medioPruebaId;
		this.nombreMedio = nombreMedio;
		this.numeroIdentificacion = numeroIdentificacion;
		this.descripcion = descripcion;
		this.ubicacionFisica = ubicacionFisica;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MedioPrueba_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getMedioPruebaId() {
		return medioPruebaId;
	}

	public void setMedioPruebaId(Long medioPruebaId) {
		this.medioPruebaId = medioPruebaId;
	}

	@Column(name = "cNombreMedio", nullable = false, length = 30)
	public String getNombreMedio() {
		return nombreMedio;
	}

	public void setNombreMedio(String nombreMedio) {
		this.nombreMedio = nombreMedio;
	}
	
	@Column(name = "cNumeroIdentificacion ", nullable = false, length = 20)
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

	@Column(name = "cUbicacionFisica", length = 200)
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}

	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medioPrueba")
	public List<RelacionDatoMedioPrueba> getRelacionesDatoMedioPrueba() {
		return relacionesDatoMedioPrueba;
	}

	public void setRelacionesDatoMedioPrueba(
			List<RelacionDatoMedioPrueba> relacionesDatoMedioPrueba) {
		this.relacionesDatoMedioPrueba = relacionesDatoMedioPrueba;
	}

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Elemento_id", nullable = true)
	public Elemento getElementoMedioPrueba() {
		return elementoMedioPrueba;
	}

	public void setElementoMedioPrueba(Elemento elementoMedioPrueba) {
		this.elementoMedioPrueba = elementoMedioPrueba;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Documento_id", nullable = true)
	public Documento getDocumentoMedioPrueba() {
		return documentoMedioPrueba;
	}

	public void setDocumentoMedioPrueba(Documento documentoMedioPrueba) {
		this.documentoMedioPrueba = documentoMedioPrueba;
	}
}
