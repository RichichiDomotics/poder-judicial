/**
* Nombre del Programa : ConsultarHechosServiceImplTest.java
* Autor                            : adrian
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 11/07/2011
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.domicilio.LugarDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.service.hecho.ConsultarHechosService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author adrian
 *
 */
public class ConsultarHechosServiceImplTest extends BaseTestServicios<ConsultarHechosService> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.service.hecho.impl.ConsultarHechosServiceImpl#consultarHechos(mx.gob.segob.nsjp.dto.hecho.HechoDTO)}.
	 */
	public void testConsultarHechos() {
//		ID 30 exp 638 Lugar 1119 Tiempo 34
		HechoDTO dto=new HechoDTO();
		dto.setHechoId(68L);
//		dto.setExpediente(new ExpedienteDTO(638L));
//		LugarDTO lugar =new LugarDTO();
//		lugar.setElementoId(1119L);
//		dto.setLugar(lugar);
//		dto.setTiempo(new TiempoDTO(34L));
		try {
			List<HechoDTO> hechos = service.consultarHechos(dto);
			if(hechos==null)
				assertTrue("Lista vacia",false);
			else{
				logger.info("Existen "+hechos.size()+" hechos");
				for (HechoDTO hch : hechos) {
					logger.info("-------------------------");
					logger.info("ID  : "+hch.getHechoId());
					logger.info("Exp : "+hch.getExpediente().getExpedienteId());
					
					if(hch.getLugar()!= null ){
						LugarDTO lugar = hch.getLugar();
						logger.info("Lug ID: "+lugar.getElementoId());
						logger.info("getLatitud : "+lugar.getLatitud());
						logger.info("getLongitud : "+lugar.getLongitud());
					}
					logger.info("Fech: "+hch.getTiempo().getTiempoId());
					logger.info("TipoRegistro: "+hch.getTiempo().getTipoRegistro());
					logger.info("Desc: "+hch.getDescNarrativa());
				}
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	}

}
