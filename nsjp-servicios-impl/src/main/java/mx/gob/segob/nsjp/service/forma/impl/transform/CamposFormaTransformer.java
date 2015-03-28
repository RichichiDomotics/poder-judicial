/**
* Nombre del Programa : CamposFormaTransformer.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Clase de transoformación para el objeto del modelo de CamposForma
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
package mx.gob.segob.nsjp.service.forma.impl.transform;

import mx.gob.segob.nsjp.dto.forma.CamposFormaDTO;
import mx.gob.segob.nsjp.model.CamposForma;

/**
 * Clase de transoformación para el objeto del modelo de CamposForma
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class CamposFormaTransformer {

	/**
	 * Transoforma un objeto <code>CamposForma</code> en un DTO del tipo
	 * <code>CamposFormaDTO</code>
	 * @param src Datos de origen
	 * @return DTO con los datos de origen
	 */
	public static CamposFormaDTO transformarCamposForma(CamposForma src){
		CamposFormaDTO destino = null;
		if(src != null){
			destino = new CamposFormaDTO();
			destino.setCamposFormaId(src.getCamposFormaId());
			destino.setDescripcion(src.getDescripcion());
			destino.setNombreNegocio(src.getNombreNegocio());
			destino.setRutaDato(src.getRutaDato());		
		}
		return destino;
	}
	
}
