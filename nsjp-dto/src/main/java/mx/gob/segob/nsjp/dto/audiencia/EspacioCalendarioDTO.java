/**
* Nombre del Programa : EspacioCalendarioDTO.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 Jun 2011
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
package mx.gob.segob.nsjp.dto.audiencia;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class EspacioCalendarioDTO extends GenericDTO{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6191513839875813235L;
	private Long id;
    private int horaInicio;
    private int minutoInicio;
    private int tamanio;
    private int duracion;
    /**
     * M�todo de acceso al campo horaInicio.
     * @return El valor del campo horaInicio
     */
    public int getHoraInicio() {
        return horaInicio;
    }
    /**
     * Asigna el valor al campo horaInicio.
     * @param horaInicio el valor horaInicio a asignar
     */
    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }
    /**
     * M�todo de acceso al campo minutoInicio.
     * @return El valor del campo minutoInicio
     */
    public int getMinutoInicio() {
        return minutoInicio;
    }
    /**
     * Asigna el valor al campo minutoInicio.
     * @param minutoInicio el valor minutoInicio a asignar
     */
    public void setMinutoInicio(int minutoInicio) {
        this.minutoInicio = minutoInicio;
    }
    /**
     * M�todo de acceso al campo tamanio.
     * @return El valor del campo tamanio
     */
    public int getTamanio() {
        return tamanio;
    }
    /**
     * Asigna el valor al campo tamanio.
     * @param tamanio el valor tamanio a asignar
     */
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
    /**
     * M�todo de acceso al campo duracion.
     * @return El valor del campo duracion
     */
    public int getDuracion() {
        return duracion;
    }
    /**
     * Asigna el valor al campo duracion.
     * @param duracion el valor duracion a asignar
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    /**
     * M�todo de acceso al campo id.
     * @return El valor del campo id
     */
    public Long getId() {
        return id;
    }
    /**
     * Asigna el valor al campo id.
     * @param id el valor id a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }
}
