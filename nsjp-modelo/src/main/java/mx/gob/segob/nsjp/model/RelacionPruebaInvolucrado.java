/**
* Nombre del Programa : RelacionPruebaInvolucrado.java
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
 * Entidad que ralaciona los Datos de Prueba a un Involucrado
 * 
 * @version 1.0
 * @author GustavoBP
 */
@Entity
@Table(name = "RelacionPruebaInvolucrado")
public class RelacionPruebaInvolucrado implements java.io.Serializable {
	
	private Long relacionPruebaInvolucradoId;
	
	//Relaciones
	private Involucrado involucrado;
	private DatoPrueba datoPrueba;
	
	
	public RelacionPruebaInvolucrado(){
	
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "RelacionPruebaInvolucrado_id", unique = true, nullable = false, precision = 18, scale = 0)
	public Long getRelacionPruebaInvolucradoId() {
		return relacionPruebaInvolucradoId;
	}
	public void setRelacionPruebaInvolucradoId(Long relacionPruebaInvolucradoId) {
		this.relacionPruebaInvolucradoId = relacionPruebaInvolucradoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Involucrado_id", nullable = false)
	public Involucrado getInvolucrado() {
		return involucrado;
	}
	public void setInvolucrado(Involucrado involucrado) {
		this.involucrado = involucrado;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DatoPrueba_id", nullable = false)
	public DatoPrueba getDatoPrueba() {
		return datoPrueba;
	}

	public void setDatoPrueba(DatoPrueba datoPrueba) {
		this.datoPrueba = datoPrueba;
	}
}
