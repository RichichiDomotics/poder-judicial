/**
 * 
 */
package mx.gob.segob.nsjp.dto.documento;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * @author adrian
 *
 */
public class CuerpoOficioEstructuradoDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4538354648779734316L;
	
	private Long cuerpoOficioEstructuradoId;
    private String texto;
    private Integer secuencia;
    private Integer numeracion;    
    private IndiceEstructuradoDTO indiceEstructurado;
    private Boolean interesa;
    private OficioEstructuradoDTO oficioEstructuradoDTO;
	/**
	 * @return the cuerpoOficioEstructuradoId
	 */
	public Long getCuerpoOficioEstructuradoId() {
		return cuerpoOficioEstructuradoId;
	}
	/**
	 * @param cuerpoOficioEstructuradoId the cuerpoOficioEstructuradoId to set
	 */
	public void setCuerpoOficioEstructuradoId(Long cuerpoOficioEstructuradoId) {
		this.cuerpoOficioEstructuradoId = cuerpoOficioEstructuradoId;
	}
	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * @return the secuencia
	 */
	public Integer getSecuencia() {
		return secuencia;
	}
	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}
	/**
	 * @return the numeracion
	 */
	public Integer getNumeracion() {
		return numeracion;
	}
	/**
	 * @param numeracion the numeracion to set
	 */
	public void setNumeracion(Integer numeracion) {
		this.numeracion = numeracion;
	}
	/**
	 * @return the indiceEstructurado
	 */
	public IndiceEstructuradoDTO getIndiceEstructurado() {
		return indiceEstructurado;
	}
	/**
	 * @param indiceEstructurado the indiceEstructurado to set
	 */
	public void setIndiceEstructurado(IndiceEstructuradoDTO indiceEstructurado) {
		this.indiceEstructurado = indiceEstructurado;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @param interesa the interesa to set
	 */
	public void setInteresa(Boolean interesa) {
		this.interesa = interesa;
	}
	/**
	 * @return the interesa
	 */
	public Boolean getInteresa() {
		return interesa;
	}
	/**
	 * @param oficioEstructuradoDTO the oficioEstructuradoDTO to set
	 */
	public void setOficioEstructuradoDTO(OficioEstructuradoDTO oficioEstructuradoDTO) {
		this.oficioEstructuradoDTO = oficioEstructuradoDTO;
	}
	/**
	 * @return the oficioEstructuradoDTO
	 */
	public OficioEstructuradoDTO getOficioEstructuradoDTO() {
		return oficioEstructuradoDTO;
	}

    
}
