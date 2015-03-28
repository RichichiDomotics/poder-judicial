/**
* Nombre del Programa : Incidencia.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Clase que contiene las operaciones y los atributos para realizar la función 
 * asociada a las incidencias que se puedan registrar sobre una medida cautelar.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Entity
@Table(name = "Incidencia")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Incidencia_id")
public class Incidencia extends Documento  {

	//Atributo que representa la descripcion del reporte de la incidencia
	private String reporte;

	private Medida medida; 
		
	public Incidencia(){
	}
	
	public Incidencia(Long incidenciaId){
        super();
        setDocumentoId(incidenciaId);
	}
	
	public Incidencia(Long incidenciaId, String reporte){
		this(incidenciaId);
		this.reporte= reporte;
	}
	
	/**
	 * Método de acceso al campo reporte.
	 * @return El valor del campo reporte
	 */
	@Column(name = "cReporte")
	public String getReporte() {
		return reporte;
	}
	/**
	 * Asigna el valor al campo reporte.
	 * @param reporte el valor reporte a asignar
	 */
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	/**
	 * Método de acceso al campo medida.
	 * @return El valor del campo medida
	 */
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Medida_id", nullable = false)
	public Medida getMedida() {
		return medida;
	}
	/**
	 * Asigna el valor al campo medida.
	 * @param medida el valor medida a asignar
	 */
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
}
