/**
* Nombre del Programa : RelacionDatoMedioPrueba.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidad de Relación entre Dato de Prueba y Medio de Prueba 
 * @version 1.0
 * @author GustavoBP
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "RelacionDatoMedioPrueba")
public class RelacionDatoMedioPrueba implements java.io.Serializable {
	
	private Long relacionDatoMedioPruebaId;
	private Boolean esAceptado;
	private Date tiempoCancelacion;
	
	//Relaciones
	private Valor motivoCancelacion;
	private DatoPrueba datoPrueba;
	private MedioPrueba medioPrueba;
	
	
	public RelacionDatoMedioPrueba(){
	}

	public RelacionDatoMedioPrueba(Long relacionDatoMedioPruebaId,
			Boolean esAceptado, Date tiempoCancelacion) {
		super();
		this.relacionDatoMedioPruebaId = relacionDatoMedioPruebaId;
		this.esAceptado = esAceptado;
		this.tiempoCancelacion = tiempoCancelacion;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RelacionDatoMedioPrueba_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRelacionDatoMedioPruebaId() {
		return relacionDatoMedioPruebaId;
	}

	public void setRelacionDatoMedioPruebaId(Long relacionDatoMedioPruebaId) {
		this.relacionDatoMedioPruebaId = relacionDatoMedioPruebaId;
	}

	@Column(name = "bEsAceptado", precision = 1, scale = 0)
	public Boolean getEsAceptado() {
		return esAceptado;
	}

	public void setEsAceptado(Boolean esAceptado) {
		this.esAceptado = esAceptado;
	}

	@Column(name = "dTiempoCancelacion", length = 23)
	public Date getTiempoCancelacion() {
		return tiempoCancelacion;
	}

	public void setTiempoCancelacion(Date tiempoCancelacion) {
		this.tiempoCancelacion = tiempoCancelacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MotivoCancelacion_val")
	public Valor getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(Valor motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DatoPrueba_id")
	public DatoPrueba getDatoPrueba() {
		return datoPrueba;
	}

	public void setDatoPrueba(DatoPrueba datoPrueba) {
		this.datoPrueba = datoPrueba;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MedioPrueba_id")
	public MedioPrueba getMedioPrueba() {
		return medioPrueba;
	}

	public void setMedioPrueba(MedioPrueba medioPrueba) {
		this.medioPrueba = medioPrueba;
	}
	
}
