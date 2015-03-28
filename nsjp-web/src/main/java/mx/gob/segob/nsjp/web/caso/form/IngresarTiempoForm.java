/**
* Nombre del Programa : IngresarTiempoForm.java                                    
* Autor: Cuauhtemoc Paredes Serrano
* Compania: Ultrasist                                                
* Proyecto: NSJP 
* Fecha: 02/03/2011 
* Marca de cambio: N/A                                                     
* Descripcion General: Integracion xxxxxxxxxxx                      
* Programa Dependiente: N/A                                                      
* Programa Subsecuente: N/A                                                      
* Cond. de ejecucion: N/A                                                      
* Dias de ejecucion: N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.web.caso.form;

import java.util.Date;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * Forma asociada a la pantalla Ingresar Tiempo
 * @version 1.0
 * @author Cuauhtemoc Paredes Serrano
 */
public class IngresarTiempoForm extends GenericForm {
	
	private static final long serialVersionUID = -4146419986275591993L;
	
	private Date dFechaInicioDetencion;
	private Date dHoraInicioDetencion;
	private Date dFechaFinDetencion;
	private Date dHoraFinDetencion;
	/**
	 * @return el dFechaInicioDetencion
	 */
	public Date getdFechaInicioDetencion() {
		return dFechaInicioDetencion;
	}
	/**
	 * @param dFechaInicioDetencion el dFechaInicioDetencion a asignar
	 */
	public void setdFechaInicioDetencion(Date dFechaInicioDetencion) {
		this.dFechaInicioDetencion = dFechaInicioDetencion;
	}
	/**
	 * @return el dHoraInicioDetencion
	 */
	public Date getdHoraInicioDetencion() {
		return dHoraInicioDetencion;
	}
	/**
	 * @param dHoraInicioDetencion el dHoraInicioDetencion a asignar
	 */
	public void setdHoraInicioDetencion(Date dHoraInicioDetencion) {
		this.dHoraInicioDetencion = dHoraInicioDetencion;
	}
	/**
	 * @return el dFechaFinDetencion
	 */
	public Date getdFechaFinDetencion() {
		return dFechaFinDetencion;
	}
	/**
	 * @param dFechaFinDetencion el dFechaFinDetencion a asignar
	 */
	public void setdFechaFinDetencion(Date dFechaFinDetencion) {
		this.dFechaFinDetencion = dFechaFinDetencion;
	}
	/**
	 * @return el dHoraFinDetencion
	 */
	public Date getdHoraFinDetencion() {
		return dHoraFinDetencion;
	}
	/**
	 * @param dHoraFinDetencion el dHoraFinDetencion a asignar
	 */
	public void setdHoraFinDetencion(Date dHoraFinDetencion) {
		this.dHoraFinDetencion = dHoraFinDetencion;
	}	
	
}
