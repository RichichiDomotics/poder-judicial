/**
* Nombre del Programa : IngresarAeronaveServiceImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Prueba unitaria para inserccion del objeto Aeronave
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
package mx.gob.segob.nsjp.service.test.objeto.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.AeronaveDTO;
import mx.gob.segob.nsjp.service.objeto.IngresarAeronaveService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Prueba unitaria para inserccion del objeto Aeronave.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class IngresarAeronaveServiceImplTest extends BaseTestServicios<IngresarAeronaveService> {

	public void testIngresarAeronave() {
		AeronaveDTO aeronaveDTO = new AeronaveDTO();
		CalidadDTO calidadDTO = new CalidadDTO();
		Long expedienteId = 16L;
		calidadDTO.setDescripcionEstadoFisico("Se encontro en buen estado");
		calidadDTO.setCalidades(Calidades.EVIDENCIA);
		
		//Propiedades Aeronave
		aeronaveDTO.setMarca(new ValorDTO(1201L));
		aeronaveDTO.setSubMarca(new ValorDTO(1273L));
		aeronaveDTO.setColor(new ValorDTO(608L));
		aeronaveDTO.setMatricula("abcd123");
		aeronaveDTO.setModelo("sesna");
		aeronaveDTO.setNoMotor("mot1234");
		aeronaveDTO.setNoSerie("123456789");
		aeronaveDTO.setPaisProcedencia(new ValorDTO(3L));
		aeronaveDTO.setTipoAeroNave(new ValorDTO(454L));
		
		//Propiedades Objeto
		aeronaveDTO.setDescripcion("Descripcion prueba aeronave");
		aeronaveDTO.setAlmacen(new AlmacenDTO(1L));
		aeronaveDTO.setTipoObjeto(Objetos.AERONAVE);
		aeronaveDTO.setValorByCondicionFisicaVal(new ValorDTO(432L));
		
		//Propiedades Elemeneto
		aeronaveDTO.setFechaCreacionElemento(new Date());
		aeronaveDTO.setValorIdElemento(new ValorDTO(Elementos.OBJETO.getValorId()));
		aeronaveDTO.setCalidadDTO(calidadDTO);
		
		aeronaveDTO.setExpedienteDTO(new ExpedienteDTO(expedienteId));
		
		CadenaDeCustodiaDTO cadena = new CadenaDeCustodiaDTO(150L);
		EvidenciaDTO evidencia = new EvidenciaDTO();
		evidencia.setObjeto(aeronaveDTO);
		evidencia.setOrigenEvidencia("Obtenido en escena");
		evidencia.setFechaLevantamiento(new Date());
		
		cadena.setEvidencia(evidencia);
		aeronaveDTO.setCadenaDeCustodia(cadena);
		
		try {
			Long idAeronave = service.ingresarAeronave(aeronaveDTO);
			assertTrue("El identificador de la aeronave debe ser mayor a cero", idAeronave>0);
                        // prueba para actualizar una aeronave
                        AeronaveDTO actualizable = new AeronaveDTO();
                       actualizable.setElementoId(idAeronave);
                        actualizable.setNoSerie("123");
                        service.ingresarAeronave(actualizable);
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
}
