/**
 * Nombre del Programa : FuncionTransformer.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 1 Aug 2011
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
package mx.gob.segob.nsjp.service.usuario.impl.transformer;

import mx.gob.segob.nsjp.dto.usuario.FuncionDTO;
import mx.gob.segob.nsjp.model.Funcion;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author cesarAgustin
 * 
 */
public class FuncionTransformer {

	public static FuncionDTO transformarFuncion(Funcion funcion) {
		FuncionDTO funcionDTO = null;

		if (funcion != null) {
			funcionDTO = new FuncionDTO();

			funcionDTO.setDescripcionFuncion(funcion.getDescripcionFuncion());
			funcionDTO.setFuncionId(funcion.getFuncionId());
			funcionDTO.setNombreFuncion(funcion.getNombreFuncion());
		}
		return funcionDTO;
	}

	public static Funcion transformarFuncionDTO(FuncionDTO funcionDTO) {
		Funcion funcion = new Funcion();
		funcion.setDescripcionFuncion(funcionDTO.getDescripcionFuncion());
		funcion.setFuncionId(funcionDTO.getFuncionId());
		funcion.setNombreFuncion(funcionDTO.getNombreFuncion());
		return funcion;
	}

}
