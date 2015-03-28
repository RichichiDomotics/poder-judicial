/**
 * Nombre del Programa : SemanaDTO.java
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
public class SemanaDisponibilidadDTO extends GenericDTO {
    private int noSemana;
    private boolean visible;
    private List<DiaDisponibilidadDTO> dias = new ArrayList<DiaDisponibilidadDTO>();

    /**
     * 
     * @param dia
     */
    public void addDia(DiaDisponibilidadDTO dia) {
        if (dias == null) {
            dias = new ArrayList<DiaDisponibilidadDTO>();
        }
        dias.add(dia);
    }
    /**
     * Obtiene el último número de día del mes asignado a la semana.
     * 
     * @return
     */
    public int getUltimoDia() {
        if (dias == null || dias.isEmpty()) {
            return 0;
        }
        return dias.get(dias.size() - 1).getDia();
    }
    /**
     * Obtiene el primer número de día del mes asignado a la semana.
     * 
     * @return
     */
    public int getPrimerDia() {
        if (dias == null || dias.isEmpty()) {
            return 0;
        }
        return dias.get(0).getDia();
    }
    /**
     * Método de acceso al campo dias.
     * 
     * @return El valor del campo dias
     */
    public List<DiaDisponibilidadDTO> getDias() {
        return dias;
    }

    /**
     * Asigna el valor al campo dias.
     * 
     * @param dias
     *            el valor dias a asignar
     */
    public void setDias(List<DiaDisponibilidadDTO> dias) {
        this.dias = dias;
    }

    /**
     * Método de acceso al campo noSemana.
     * 
     * @return El valor del campo noSemana
     */
    public int getNoSemana() {
        return noSemana;
    }

    /**
     * Asigna el valor al campo noSemana.
     * 
     * @param noSemana
     *            el valor noSemana a asignar
     */
    public void setNoSemana(int noSemana) {
        this.noSemana = noSemana;
    }

    /**
     * Método de acceso al campo visible.
     * 
     * @return El valor del campo visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Asigna el valor al campo visible.
     * 
     * @param visible
     *            el valor visible a asignar
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
