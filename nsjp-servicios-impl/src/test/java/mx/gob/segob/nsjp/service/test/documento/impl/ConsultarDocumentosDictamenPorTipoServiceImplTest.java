/**
* Nombre del Programa : ConsultarDocumentosDictamenPorTipoServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/07/2011
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
package mx.gob.segob.nsjp.service.test.documento.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.documento.ConsultarDocumentosDictamenPorTipoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author Emigdio
 *
 */
public class ConsultarDocumentosDictamenPorTipoServiceImplTest extends BaseTestServicios<ConsultarDocumentosDictamenPorTipoService>{

	public void testConsultaDictamenesRecibidos(){
		
		try {
			List<DictamenDTO> dictamenes = service.consultarDocumentosRecibidosAMP(Formas.DICTAMEN_PERICIAL,new FuncionarioDTO(1L));
			logger.debug("Tamaño de documentos recibido:" + dictamenes.size());
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
}
