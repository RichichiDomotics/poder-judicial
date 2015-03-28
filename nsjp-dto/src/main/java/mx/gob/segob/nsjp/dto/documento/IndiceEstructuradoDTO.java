/**
 * Nombre del Programa : IndiceEstructuradoDTO.java
 * Autor                            : rgama
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27 Jul 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto de transferencia de la notificacion.
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
package mx.gob.segob.nsjp.dto.documento;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Objeto de transferencia de un Indice Estructurado
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class IndiceEstructuradoDTO extends GenericDTO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7335257747197973003L;
	private Long indiceEstructuradoId;
	private Long indiceEstructuradoIdHijo;
    private String nombreEtiqueta;
    private String nombreEtiquetaHijo;
    private String textoEtiqueta;
    private String textoEtiquetaHijo;
    private Short nivel;
    private ValorDTO tipoOficio;
    private IndiceEstructuradoDTO indiceEstructuradoPadre;
    private List<IndiceEstructuradoDTO> indicesEstructurados = new ArrayList<IndiceEstructuradoDTO>(0);
    
	/**
	 * Método de acceso al campo indiceEstructuradoId.
	 * @return El valor del campo indiceEstructuradoId
	 */
	public Long getIndiceEstructuradoId() {
		return indiceEstructuradoId;
	}
	/**
	 * Asigna el valor al campo indiceEstructuradoId.
	 * @param indiceEstructuradoId el valor indiceEstructuradoId a asignar
	 */
	public void setIndiceEstructuradoId(Long indiceEstructuradoId) {
		this.indiceEstructuradoId = indiceEstructuradoId;
	}
	/**
	 * Método de acceso al campo nombreEtiqueta.
	 * @return El valor del campo nombreEtiqueta
	 */
	public String getNombreEtiqueta() {
		return nombreEtiqueta;
	}
	/**
	 * Asigna el valor al campo nombreEtiqueta.
	 * @param nombreEtiqueta el valor nombreEtiqueta a asignar
	 */
	public void setNombreEtiqueta(String nombreEtiqueta) {
		this.nombreEtiqueta = nombreEtiqueta;
	}
	/**
	 * Método de acceso al campo textoEtiqueta.
	 * @return El valor del campo textoEtiqueta
	 */
	public String getTextoEtiqueta() {
		return textoEtiqueta;
	}
	/**
	 * Asigna el valor al campo textoEtiqueta.
	 * @param textoEtiqueta el valor textoEtiqueta a asignar
	 */
	public void setTextoEtiqueta(String textoEtiqueta) {
		this.textoEtiqueta = textoEtiqueta;
	}
	/**
	 * Método de acceso al campo nivel.
	 * @return El valor del campo nivel
	 */
	public Short getNivel() {
		return nivel;
	}
	/**
	 * Asigna el valor al campo nivel.
	 * @param nivel el valor nivel a asignar
	 */
	public void setNivel(Short nivel) {
		this.nivel = nivel;
	}
	/**
	 * Método de acceso al campo tipoOficio.
	 * @return El valor del campo tipoOficio
	 */
	public ValorDTO getTipoOficio() {
		return tipoOficio;
	}
	/**
	 * Asigna el valor al campo tipoOficio.
	 * @param tipoOficio el valor tipoOficio a asignar
	 */
	public void setTipoOficio(ValorDTO tipoOficio) {
		this.tipoOficio = tipoOficio;
	}
	/**
	 * Método de acceso al campo indiceEstructuradoPadre.
	 * @return El valor del campo indiceEstructuradoPadre
	 */
	public IndiceEstructuradoDTO getIndiceEstructuradoPadre() {
		return indiceEstructuradoPadre;
	}
	/**
	 * Asigna el valor al campo indiceEstructuradoPadre.
	 * @param indiceEstructuradoPadre el valor indiceEstructuradoPadre a asignar
	 */
	public void setIndiceEstructuradoPadre(
			IndiceEstructuradoDTO indiceEstructuradoPadre) {
		this.indiceEstructuradoPadre = indiceEstructuradoPadre;
	}
	/**
	 * Método de acceso al campo indicesEstructurados.
	 * @return El valor del campo indicesEstructurados
	 */
	public List<IndiceEstructuradoDTO> getIndicesEstructurados() {
		return indicesEstructurados;
	}
	/**
	 * Asigna el valor al campo indicesEstructurados.
	 * @param indicesEstructurados el valor indicesEstructurados a asignar
	 */
	public void setIndicesEstructurados(
			List<IndiceEstructuradoDTO> indicesEstructurados) {
		this.indicesEstructurados = indicesEstructurados;
	}
	/**
	 * @return the indiceEstructuradoIdHijo
	 */
	public Long getIndiceEstructuradoIdHijo() {
		return indiceEstructuradoIdHijo;
	}
	/**
	 * @param indiceEstructuradoIdHijo the indiceEstructuradoIdHijo to set
	 */
	public void setIndiceEstructuradoIdHijo(Long indiceEstructuradoIdHijo) {
		this.indiceEstructuradoIdHijo = indiceEstructuradoIdHijo;
	}
	/**
	 * @return the nombreEtiquetaHijo
	 */
	public String getNombreEtiquetaHijo() {
		return nombreEtiquetaHijo;
	}
	/**
	 * @param nombreEtiquetaHijo the nombreEtiquetaHijo to set
	 */
	public void setNombreEtiquetaHijo(String nombreEtiquetaHijo) {
		this.nombreEtiquetaHijo = nombreEtiquetaHijo;
	}
	/**
	 * @return the textoEtiquetaHijo
	 */
	public String getTextoEtiquetaHijo() {
		return textoEtiquetaHijo;
	}
	/**
	 * @param textoEtiquetaHijo the textoEtiquetaHijo to set
	 */
	public void setTextoEtiquetaHijo(String textoEtiquetaHijo) {
		this.textoEtiquetaHijo = textoEtiquetaHijo;
	}   
	
}
