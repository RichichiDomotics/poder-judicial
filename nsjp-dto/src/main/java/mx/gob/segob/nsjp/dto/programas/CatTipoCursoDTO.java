/**
* Nombre del Programa : CatTipoCursoDTO.java
* Autor                            : AntonioBV
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/02/2012
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
package mx.gob.segob.nsjp.dto.programas;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author AntonioBV
 *
 */
public class CatTipoCursoDTO extends GenericDTO {

		/**
	 * 
	 */
	private static final long serialVersionUID = -5239261541640459591L;

		private Long catTipoCursoId;
		
		private String descripcion;

		/**
		 * Método de acceso al campo catTipoCursoId.
		 * @return El valor del campo catTipoCursoId
		 */
		public Long getCatTipoCursoId() {
			return catTipoCursoId;
		}

		/**
		 * Asigna el valor al campo catTipoCursoId.
		 * @param catTipoCursoId el valor catTipoCursoId a asignar
		 */
		public void setCatTipoCursoId(Long catTipoCursoId) {
			this.catTipoCursoId = catTipoCursoId;
		}

		/**
		 * Método de acceso al campo descripcion.
		 * @return El valor del campo descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * Asigna el valor al campo descripcion.
		 * @param descripcion el valor descripcion a asignar
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
				
}