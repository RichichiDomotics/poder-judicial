/**
 * Nombre del Programa : MesAgendaDTO.java
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
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import mx.gob.segob.nsjp.comun.enums.comun.Meses;
import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class MesDisponibilidadDTO extends GenericDTO {
    private final static Logger log = Logger.getLogger(MesDisponibilidadDTO.class);
    /**
     * Mes
     */
    private Meses mes;
    private int anio;

    public MesDisponibilidadDTO() {
        final Calendar cal = Calendar.getInstance();
        mes = Meses.values()[cal.get(Calendar.MONTH)];
        anio = cal.get(Calendar.YEAR);
    }

    /**
     * semansa del mes, puede tener 4 o 5.
     */
    private List<SemanaDisponibilidadDTO> semanas = new ArrayList<SemanaDisponibilidadDTO>();
    /**
     * Usado para calcular el mes siguiente
     */
    public void avanzarMes() {
        log.warn("avanzando el mes " + mes);
        int next = mes.ordinal() + 1;
        log.warn("Meses.values().length " + Meses.values().length);
        log.warn("next " + next);
        if (next >= Meses.values().length) {
            anio++;
            mes = Meses.ENERO;
        } else {
            mes = Meses.values()[next];
        }
    }
    /**
     * Usado para calcular el mes anterior
     */
    public void retrocederMes() {
        int prev = mes.ordinal() - 1;
        if (prev < 0) {
            anio--;
            mes = Meses.DICIEMBRE;
        } else {
            mes = Meses.values()[prev];
        }
    }

    /**
     * 
     * @param sem
     */
    public void addSemana(SemanaDisponibilidadDTO sem) {
        if (semanas == null) {
            semanas = new ArrayList<SemanaDisponibilidadDTO>();
        }
        semanas.add(sem);
    }

    /**
     * Método de acceso al campo semanas.
     * 
     * @return El valor del campo semanas
     */
    public List<SemanaDisponibilidadDTO> getSemanas() {
        return semanas;
    }

    /**
     * Asigna el valor al campo semanas.
     * 
     * @param semanas
     *            el valor semanas a asignar
     */
    public void setSemanas(List<SemanaDisponibilidadDTO> semanas) {
        this.semanas = semanas;
    }

    /**
     * Método de acceso al campo mes.
     * 
     * @return El valor del campo mes
     */
    public Meses getMes() {
        return mes;
    }

    /**
     * Asigna el valor al campo mes.
     * 
     * @param mes
     *            el valor mes a asignar
     */
    public void setMes(Meses mes) {
        this.mes = mes;
    }
    /**
     * Método de acceso al campo anio.
     * 
     * @return El valor del campo anio
     */
    public int getAnio() {
        return anio;
    }
    /**
     * Asigna el valor al campo anio.
     * 
     * @param anio
     *            el valor anio a asignar
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

}
