/**
 * Nombre del Programa : ConfInstitucionDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 20 Jun 2011
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
package mx.gob.segob.nsjp.dto.configuracion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConfInstitucionDTO extends GenericDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6993882604602520100L;
	private Long confInstitucionId;
    private String clave;
    private String nombreInst;
    private String urlInst;
    private Boolean esInstalacionActual;
    /**
     */
    public ConfInstitucionDTO() {
        super();
    }
     /**
     * 
     * @param nombreInst
     */
    public ConfInstitucionDTO(String nombreInst) {
        super();
        this.nombreInst = nombreInst;
    }
    
    /**
     * 
     * @param id
     */
    public ConfInstitucionDTO(Long id) {
        super();
        this.confInstitucionId = id;
    }
    /**
     * 
     * @param id
     * @param nombreInst
     */
    public ConfInstitucionDTO(Long id, String nombreInst) {
        super();
        this.confInstitucionId = id;
        this.nombreInst = nombreInst;
    }
    
 
    
    /**
     * Método de acceso al campo confInstitucionId.
     * 
     * @return El valor del campo confInstitucionId
     */
    public Long getConfInstitucionId() {
        return confInstitucionId;
    }
    /**
     * Asigna el valor al campo confInstitucionId.
     * 
     * @param confInstitucionId
     *            el valor confInstitucionId a asignar
     */
    public void setConfInstitucionId(Long confInstitucionId) {
        this.confInstitucionId = confInstitucionId;
    }
    /**
     * Método de acceso al campo clave.
     * 
     * @return El valor del campo clave
     */
    public String getClave() {
        return clave;
    }
    /**
     * Asigna el valor al campo clave.
     * 
     * @param clave
     *            el valor clave a asignar
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    /**
     * Método de acceso al campo nombreInst.
     * 
     * @return El valor del campo nombreInst
     */
    public String getNombreInst() {
        return nombreInst;
    }
    /**
     * Asigna el valor al campo nombreInst.
     * 
     * @param nombreInst
     *            el valor nombreInst a asignar
     */
    public void setNombreInst(String nombreInst) {
        this.nombreInst = nombreInst;
    }
    /**
     * Método de acceso al campo urlInst.
     * 
     * @return El valor del campo urlInst
     */
    public String getUrlInst() {
        return urlInst;
    }
    /**
     * Asigna el valor al campo urlInst.
     * 
     * @param urlInst
     *            el valor urlInst a asignar
     */
    public void setUrlInst(String urlInst) {
        this.urlInst = urlInst;
    }
    /**
     * Método de acceso al campo esInstalacionActual.
     * 
     * @return El valor del campo esInstalacionActual
     */
    public Boolean getEsInstalacionActual() {
        return esInstalacionActual;
    }
    /**
     * Asigna el valor al campo esInstalacionActual.
     * 
     * @param esInstalacionActual
     *            el valor esInstalacionActual a asignar
     */
    public void setEsInstalacionActual(Boolean esInstalacionActual) {
        this.esInstalacionActual = esInstalacionActual;
    }
}
