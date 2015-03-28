/**
 * 
 */
package mx.gob.segob.nsjp.dto.catalogo;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Clase que representa el valor de una columna en el catalogo.
 * 
 * @author vaguirre
 * 
 */
public class ValorDTO extends GenericDTO implements Comparable<Object> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -5759015701971012231L;
    /**
     * Valor.lCatCampoId
     */
    private Long idCampo;
    /**
     * Valor.cValor
     */
    private String valor;
    /**
     * CatCampo.cNombre
     */
    private String nombreCampo;

    private ValorDTO valorPadre;
    
    private Long catalogoPadre;
    
    /**
	 * 
	 */
    public ValorDTO() {
        super();
    }

    /**
     * @param idCampo
     */
    public ValorDTO(Long idCampo) {
        super();
        this.idCampo = idCampo;
    }

    /**
     * @param valor
     * @param nombreCampo
     */
    public ValorDTO(Long idCampo, String valor) {
        super();
        this.idCampo = idCampo;
        this.valor = valor;
    }

    /**
     * @param idCampo
     * @param valor
     * @param nombreCampo
     */
    public ValorDTO(Long idCampo, String valor, String nombreCampo) {
        super();
        this.idCampo = idCampo;
        this.valor = valor;
        this.nombreCampo = nombreCampo;
    }

  //Implementado para ser utilizado en Servicio de ConsultarEstadosPorJerarquiaOrganizacional
    public boolean equals(Object obj) {
		if ((this == obj))
			return true;
		if ((obj == null))
			return false;
		if (!(obj instanceof ValorDTO))
			return false;
		ValorDTO valorTemp = (ValorDTO) obj;

		return ((this.getIdCampo() == valorTemp
				.getIdCampo()));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (this.getIdCampo() == null ? 0 : this
						.getIdCampo().hashCode());
		return result;
	}
	
    /**
     * @return the idCampo
     */
    public Long getIdCampo() {
        return idCampo;
    }

    /**
     * @param idCampo
     *            the idCampo to set
     */
    public void setIdCampo(Long idCampo) {
        this.idCampo = idCampo;
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
     * @return the nombreCampo
     */
    public String getNombreCampo() {
        return nombreCampo;
    }

    /**
     * @param nombreCampo
     *            the nombreCampo to set
     */
    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    /**
     * Método de acceso al campo valorPadre.
     * @return El valor del campo valorPadre
     */
    public ValorDTO getValorPadre() {
        return valorPadre;
    }

    /**
     * Asigna el valor al campo valorPadre.
     * @param valorPadre el valor valorPadre a asignar
     */
    public void setValorPadre(ValorDTO valorPadre) {
        this.valorPadre = valorPadre;
    }

    /**
     * Método de acceso al campo catalogoPadre.
     * @return El valor del campo catalogoPadre
     */
    public Long getCatalogoPadre() {
        return catalogoPadre;
    }

    /**
     * Asigna el valor al campo catalogoPadre.
     * @param catalogoPadre el valor catalogoPadre a asignar
     */
    public void setCatalogoPadre(Long catalogoPadre) {
        this.catalogoPadre = catalogoPadre;
    }
    
    /**
     * Utilizado para el servicio consultarEstadosPorJerarquiaOrganizacional, 
     * para ordenar una lista de valores
     * @param obj
     */
    public int compareTo(Object obj) {
    	ValorDTO valorDTO = (ValorDTO)obj;       
    	 return (this.getValor().compareTo(valorDTO.getValor()));
    }
}
