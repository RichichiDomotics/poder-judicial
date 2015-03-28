/**
* Nombre del Programa : ModificarHechoServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 06/07/2011
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
package mx.gob.segob.nsjp.service.test.hecho.impl;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.service.hecho.ModificarHechoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ModificarHechoServiceImplTest extends BaseTestServicios<ModificarHechoService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.hecho.impl.ModificarHechoServiceImpl#modificarHecho(mx.gob.segob.nsjp.dto.hecho.HechoDTO)}.
	 */
	public void testModificarHecho() {
//		28	<p>prueba jojojojojo</p>	623	1110	32
//		29	<p>hecho con un domicilio extranjero</p>	636	1118	33
//		30	<p>sad,jsadjasklda</p>	638	1119	34
		
		HechoDTO dto=new HechoDTO();
		ExpedienteDTO exp=new ExpedienteDTO(638L);
		LugarDTO lugar=new LugarDTO();
		lugar.setElementoId(1119L);
		TiempoDTO tiempo=new TiempoDTO(34L);

		dto.setHechoId(30L);
		dto.setDescNarrativa("Esta es la modificación exitosa en 11Jul2011 b");
		dto.setExpediente(exp);
//		dto.setLugar(lugar);
//		dto.setTiempo(tiempo);
		dto.setLugar(null);
		dto.setTiempo(null);
		
		try {
			service.modificarHecho(dto);
			assertTrue("EXITO", true);
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue("FALLO: modificarHecho",false);
		}
	}

}
