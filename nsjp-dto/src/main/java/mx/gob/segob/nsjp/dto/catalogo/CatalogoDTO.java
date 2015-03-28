/**
 * Nombre del Programa : Error.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    
 * Fecha: 29 Mar 2011
 * Marca de cambio        : N/A
 * Descripcion General    : DTO que representa un registro de una catalogo.
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
package mx.gob.segob.nsjp.dto.catalogo;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto de transferecnia que representa un registro de un catalogo.
 * 
 * @author vaguirre
 * 
 */
public class CatalogoDTO extends GenericDTO implements Comparable{

    /**
	 * 
	 */
    private static final long serialVersionUID = 6321141609006270498L;
    
    private Long clave;
    private String valor;
    private Long campoId;
    private Boolean esSistema;
    private Long idCatalogo;
    /**
     * Contiene los valores extras
     */
    private List<ValorDTO> valoresExras = new ArrayList<ValorDTO>();
    /**
     * Constructor vacío.
     */
    public CatalogoDTO() {
        super();
    }

    /**
     * Constructor con campos.
     * 
     * @param clave
     * @param valor
     */
    public CatalogoDTO(Long clave, String valor) {
        super();
        this.clave = clave;
        this.valor = valor;
    }

    /**
     * Constructor con campos.
     * 
     * @param clave
     * @param valor
     */
    public CatalogoDTO(Long clave) {
        super();
        this.clave = clave;
    }

    /**
     * 
     * @param valEx
     */
    public void addValorExtra(ValorDTO valEx) {
        if (this.valoresExras == null) {
            this.valoresExras = new ArrayList<ValorDTO>();
        }
        this.valoresExras.add(valEx);
    }

    /**
     * @return the clave
     */
    public Long getClave() {
        return clave;
    }

    /**
     * @param clave
     *            the clave to set
     */
    public void setClave(Long clave) {
        this.clave = clave;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor
     *            the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the valoresExras
     */
    public List<ValorDTO> getValoresExras() {
        return valoresExras;
    }

    /**
     * @param valoresExras
     *            the valoresExras to set
     */
    public void setValoresExras(List<ValorDTO> valoresExras) {
        this.valoresExras = valoresExras;
    }

    /**
     * Método de acceso al campo campoId.
     * @return El valor del campo campoId
     */
    public Long getCampoId() {
        return campoId;
    }

    /**
     * Asigna el valor al campo campoId.
     * @param campoId el valor campoId a asignar
     */
    public void setCampoId(Long campoId) {
        this.campoId = campoId;
    }

    /**
     * Método de acceso al campo esSistema.
     * @return El valor del campo esSistema
     */
    public Boolean getEsSistema() {
        return esSistema;
    }

    /**
     * Asigna el valor al campo esSistema.
     * @param esSistema el valor esSistema a asignar
     */
    public void setEsSistema(Boolean esSistema) {
        this.esSistema = esSistema;
    }

    /**
     * Método de acceso al campo idCatalogo.
     * @return El valor del campo idCatalogo
     */
    public Long getIdCatalogo() {
        return idCatalogo;
    }

    /**
     * Asigna el valor al campo idCatalogo.
     * @param idCatalogo el valor idCatalogo a asignar
     */
    public void setIdCatalogo(Long idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    //Implementado para ser utilizado en Servicio de ConsultarTipoSolicitudAudienciaService
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof CatalogoDTO))
			return false;
		CatalogoDTO catalogoTemp = (CatalogoDTO) obj;

		return ((this.getClave() == catalogoTemp
				.getClave()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getClave() == null ? 0 : this
						.getClave().hashCode());
		return result;
	}
	
	@Override
	public int compareTo(Object o) {
		CatalogoDTO loCatalogoDTO = (CatalogoDTO)o; 
		return this.valor.compareToIgnoreCase(loCatalogoDTO.valor);        	
	} 
}
