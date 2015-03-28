/**
 * Nombre del Programa : GenericWSDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO Genérico para que extiendan todas los DTO's expuestos por web services de la aplicación
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
package mx.gob.segob.nsjp.dto.base;

import java.io.Serializable;

/**
 * DTO Genérico para que extiendan todas los DTO's expuestos por web services de
 * la aplicación.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class GenericWSDTO implements Serializable {
    private Long confInstitucionId;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3475695319353375389L;
    /**
     * Método de acceso al campo confInstitucionId.
     * @return El valor del campo confInstitucionId
     */
    public Long getConfInstitucionId() {
        return confInstitucionId;
    }
    /**
     * Asigna el valor al campo confInstitucionId.
     * @param confInstitucionId el valor confInstitucionId a asignar
     */
    public void setConfInstitucionId(Long confInstitucionId) {
        this.confInstitucionId = confInstitucionId;
    }

}
