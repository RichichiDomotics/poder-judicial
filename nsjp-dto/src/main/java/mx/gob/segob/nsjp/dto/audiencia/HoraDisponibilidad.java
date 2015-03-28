/**
 * Nombre del Programa : HoraDisponibilidad.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 9 Jun 2011
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

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class HoraDisponibilidad extends GenericDTO {
    private int hora;
    private int minuto;
    private SalaAudienciaDTO sala;
    /**
     * Representa las columnas de una lista, puede tener nulos lo cual representa que esa celda está disponible.
     */
    private List<SalaAudienciaDTO> salas;
    /**
     * 
     * @param salita
     */
    public void addSala(SalaAudienciaDTO salita) {
        if (salas == null) {
            salas = new ArrayList<SalaAudienciaDTO>();
        }
        salas.add(salita);
    }

    /**
     * Método de acceso al campo sala.
     * 
     * @return El valor del campo sala
     */
    public SalaAudienciaDTO getSala() {
        return sala;
    }

    /**
     * Asigna el valor al campo sala.
     * 
     * @param sala
     *            el valor sala a asignar
     */
    public void setSala(SalaAudienciaDTO sala) {
        this.sala = sala;
    }

    /**
     * Método de acceso al campo hora.
     * 
     * @return El valor del campo hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * Asigna el valor al campo hora.
     * 
     * @param hora
     *            el valor hora a asignar
     */
    public void setHora(int hora) {
        this.hora = hora;
    }

    /**
     * Método de acceso al campo minuto.
     * 
     * @return El valor del campo minuto
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Asigna el valor al campo minuto.
     * 
     * @param minuto
     *            el valor minuto a asignar
     */
    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    /**
     * Método de acceso al campo salas.
     * 
     * @return El valor del campo salas
     */
    public List<SalaAudienciaDTO> getSalas() {
        return salas;
    }

    /**
     * Asigna el valor al campo salas.
     * 
     * @param salas
     *            el valor salas a asignar
     */
    public void setSalas(List<SalaAudienciaDTO> salas) {
        this.salas = salas;
    }

}
