/**
 * Nombre del Programa : DepartamentoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 2 Jun 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia de Departamento
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
package mx.gob.segob.nsjp.dto.institucion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de transferencia de Departamento.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class DepartamentoDTO extends GenericDTO {

    private Long departamentoId;
    private String nombreDepto;

    private AreaDTO area;
    /**
     * 
     */
    public DepartamentoDTO(){
        
    }
    
    /**
     * @param departamentoId
     * @param nombreDepto
     */
    public DepartamentoDTO(Long departamentoId, String nombreDepto) {
        super();
        this.departamentoId = departamentoId;
        this.nombreDepto = nombreDepto;
    }

    /**
     * Método de acceso al campo area.
     * 
     * @return El valor del campo area
     */
    public AreaDTO getArea() {
        return area;
    }

    /**
     * Asigna el valor al campo area.
     * 
     * @param area
     *            el valor area a asignar
     */
    public void setArea(AreaDTO area) {
        this.area = area;
    }

    /**
     * Método de acceso al campo departamentoId.
     * 
     * @return El valor del campo departamentoId
     */
    public Long getDepartamentoId() {
        return departamentoId;
    }

    /**
     * Asigna el valor al campo departamentoId.
     * 
     * @param departamentoId
     *            el valor departamentoId a asignar
     */
    public void setDepartamentoId(Long departamentoId) {
        this.departamentoId = departamentoId;
    }

    /**
     * Método de acceso al campo nombreDepto.
     * 
     * @return El valor del campo nombreDepto
     */
    public String getNombreDepto() {
        return nombreDepto;
    }

    /**
     * Asigna el valor al campo nombreDepto.
     * 
     * @param nombreDepto
     *            el valor nombreDepto a asignar
     */
    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }
}
