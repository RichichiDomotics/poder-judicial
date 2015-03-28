/**
 * Nombre del Programa : DelitoDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia del delito.
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
package mx.gob.segob.nsjp.dto.expediente;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;

/**
 * Objeto de transferencia del delito.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class DelitoDTO extends GenericDTO {

    /**
	 * 
	 */
    private static final long serialVersionUID = -3405932988415510124L;
    private Long delitoId;
    private CatDelitoDTO catDelitoDTO;
    private String nombreDelito;
    private Boolean esProbable;
    private Boolean esPrincipal;
    private ExpedienteDTO expedienteDTO;

    /**
     * 
     * @param idDelito
     */
    public DelitoDTO(Long idDelito) {
        this.delitoId = idDelito;
    }

    /**
     * 
     * @param idCatDelito
     * @param esElprincipal
     */
    public DelitoDTO(Long idCatDelito, Boolean esElprincipal) {
        super();
        this.catDelitoDTO = new CatDelitoDTO(idCatDelito);
        this.esPrincipal = esElprincipal;
    }

    public DelitoDTO(Long delitoId, CatDelitoDTO catDelitoDTO,
            Boolean esProbable, Boolean esPrincipal, ExpedienteDTO expedienteDTO) {
        super();
        this.delitoId = delitoId;
        this.catDelitoDTO = catDelitoDTO;
        this.esProbable = esProbable;
        this.esPrincipal = esPrincipal;
        this.expedienteDTO = expedienteDTO;
    }

    public DelitoDTO() {
        super();
    }
    /**
     * Método de acceso al campo delitoId.
     * 
     * @return El valor del campo delitoId
     */
    public Long getDelitoId() {
        return delitoId;
    }
    /**
     * Asigna el valor al campo delitoId.
     * 
     * @param delitoId
     *            el valor delitoId a asignar
     */
    public void setDelitoId(Long delitoId) {
        this.delitoId = delitoId;
    }
    /**
     * Método de acceso al campo esProbable.
     * 
     * @return El valor del campo esProbable
     */
    public Boolean getEsProbable() {
        return esProbable;
    }
    /**
     * Asigna el valor al campo esProbable.
     * 
     * @param esProbable
     *            el valor esProbable a asignar
     */
    public void setEsProbable(Boolean esProbable) {
        this.esProbable = esProbable;
    }
    /**
     * Método de acceso al campo esPrincipal.
     * 
     * @return El valor del campo esPrincipal
     */
    public Boolean getEsPrincipal() {
        return esPrincipal;
    }
    /**
     * Asigna el valor al campo esPrincipal.
     * 
     * @param esPrincipal
     *            el valor esPrincipal a asignar
     */
    public void setEsPrincipal(Boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }
    public ExpedienteDTO getExpedienteDTO() {
        return expedienteDTO;
    }
    public void setExpedienteDTO(ExpedienteDTO expedienteDTO) {
        this.expedienteDTO = expedienteDTO;
    }

    /**
     * Asigna el valor al campo catDelitoDTO.
     * 
     * @param catDelitoDTO
     *            el valor catDelitoDTO a asignar
     */
    public void setCatDelitoDTO(CatDelitoDTO catDelitoDTO) {
        this.catDelitoDTO = catDelitoDTO;
    }

    /**
     * Método de acceso al campo catDelitoDTO.
     * 
     * @return El valor del campo catDelitoDTO
     */
    public CatDelitoDTO getCatDelitoDTO() {
        return catDelitoDTO;
    }

    /**
     * Asigna el valor al campo nombreDelito.
     * 
     * @param nombreDelito
     *            el valor nombreDelito a asignar
     */
    public void setNombreDelito(String nombreDelito) {
        this.nombreDelito = nombreDelito;
    }

    /**
     * @deprecated Se utiliza catDelitoDTO.getNombre() Método de acceso al campo
     *             nombreDelito.
     * @return El valor del campo nombreDelito
     */
    public String getNombreDelito() {
        return nombreDelito;
    }

}
