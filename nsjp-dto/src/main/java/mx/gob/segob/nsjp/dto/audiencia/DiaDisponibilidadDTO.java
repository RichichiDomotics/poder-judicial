/**
 * Nombre del Programa : DiaAgendaDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
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
public class DiaDisponibilidadDTO extends GenericDTO {

    /* propiedades usadas para el render del mes */
    private Integer dia = new Integer(0);
    private boolean mesActual;
    private boolean habil;
    private boolean disponible;
    /* propiedades usadas para el render del dia */
    
    private int horaInicio;
    private int horaFin;
    private int espacio;
    
    private MesDisponibilidadDTO mes;
    /**
     * Horas del día, la disponibilidad se determina a partir del contenido de salas.
     */
    private List<HoraDisponibilidad> horas = new ArrayList<HoraDisponibilidad>();
    
    private List<SalaAudienciaDTO> salas = new ArrayList<SalaAudienciaDTO>();

    /**
     * 
     * @param hour
     */
    public void addHora(HoraDisponibilidad hour) {
        if (horas == null) {
            horas = new ArrayList<HoraDisponibilidad>();
        }
        horas.add(hour);
    }

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
     * Método de acceso al campo dia.
     * 
     * @return El valor del campo dia
     */
    public Integer getDia() {
        return dia;
    }
    /**
     * Asigna el valor al campo dia.
     * 
     * @param dia
     *            el valor dia a asignar
     */
    public void setDia(Integer dia) {
        this.dia = dia;
    }
    /**
     * Método de acceso al campo mesActual.
     * 
     * @return El valor del campo mesActual
     */
    public boolean isMesActual() {
        return mesActual;
    }
    /**
     * Asigna el valor al campo mesActual.
     * 
     * @param mesActual
     *            el valor mesActual a asignar
     */
    public void setMesActual(boolean mesActual) {
        this.mesActual = mesActual;
    }
    /**
     * Método de acceso al campo habil.
     * 
     * @return El valor del campo habil
     */
    public boolean isHabil() {
        return habil;
    }
    /**
     * Asigna el valor al campo habil.
     * 
     * @param habil
     *            el valor habil a asignar
     */
    public void setHabil(boolean habil) {
        this.habil = habil;
    }
    /**
     * Método de acceso al campo disponible.
     * 
     * @return El valor del campo disponible
     */
    public boolean isDisponible() {
        return disponible;
    }
    /**
     * Asigna el valor al campo disponible.
     * 
     * @param disponible
     *            el valor disponible a asignar
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    /**
     * Método de acceso al campo mes.
     * 
     * @return El valor del campo mes
     */
    public MesDisponibilidadDTO getMes() {
        return mes;
    }
    /**
     * Asigna el valor al campo mes.
     * 
     * @param mes
     *            el valor mes a asignar
     */
    public void setMes(MesDisponibilidadDTO mes) {
        this.mes = mes;
    }
    /**
     * Método de acceso al campo horas.
     * 
     * @return El valor del campo horas
     */
    public List<HoraDisponibilidad> getHoras() {
        return horas;
    }
    /**
     * Asigna el valor al campo horas.
     * 
     * @param horas
     *            el valor horas a asignar
     */
    public void setHoras(List<HoraDisponibilidad> horas) {
        this.horas = horas;
    }

    /**
     * Método de acceso al campo salas.
     * @return El valor del campo salas
     */
    public List<SalaAudienciaDTO> getSalas() {
        return salas;
    }

    /**
     * Asigna el valor al campo salas.
     * @param salas el valor salas a asignar
     */
    public void setSalas(List<SalaAudienciaDTO> salas) {
        this.salas = salas;
    }

    /**
     * Método de acceso al campo horaInicio.
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
     * Método de acceso al campo horaFin.
     * @return El valor del campo horaFin
     */
    public int getHoraFin() {
        return horaFin;
    }

    /**
     * Asigna el valor al campo horaFin.
     * @param horaFin el valor horaFin a asignar
     */
    public void setHoraFin(int horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Método de acceso al campo espacio.
     * @return El valor del campo espacio
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * Asigna el valor al campo espacio.
     * @param espacio el valor espacio a asignar
     */
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }
}
