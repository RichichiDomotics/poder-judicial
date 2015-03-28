/**
 * Nombre del Programa : Dictamen.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 15 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Entity para Dictamen
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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entity para Dictamen.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Entity
@Table(name = "Dictamen")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "Dictamen_id")
public class Dictamen extends Documento {
    // TODO relaciones con archivo digital para los adjuntos

    private SolicitudPericial solicitudPericial;

    /**
     * Método de acceso al campo solicitudPericial.
     * 
     * @return El valor del campo solicitudPericial
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolicitudPericial_id")
    public SolicitudPericial getSolicitudPericial() {
        return solicitudPericial;
    }

    /**
     * Asigna el valor al campo solicitudPericial.
     * 
     * @param solicitudPericial
     *            el valor solicitudPericial a asignar
     */
    public void setSolicitudPericial(SolicitudPericial solicitudPericial) {
        this.solicitudPericial = solicitudPericial;
    }
}
