/**
* Nombre del Programa : ConsultarBitacoraConsultaServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
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
package mx.gob.segob.nsjp.service.test.bitacora.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.bitacora.FiltroBitacoraConsultaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.service.bitacora.ConsultarBitacoraConsultaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias de los servicios para consultar la Bitacora de Consulta.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarBitacoraConsultaServiceImplTest extends BaseTestServicios<ConsultarBitacoraConsultaService> {

	public void testConsultarBitacoraConsultaPorFiltro(){

		String numeroExpediente = "NSJYUCPG2010441"; 
		Date fechaConsulta  = null; //new Date();
		Date horaConsulta = null; //new Date();
		
		FuncionarioDTO funcionario = new FuncionarioDTO();
//		funcionario.setNombreFuncionario("Daniel");
//		funcionario.setApellidoPaternoFuncionario("Flores");
//		funcionario.setApellidoMaternoFuncionario("de");
		
		Boolean esPermitida = null;//true;
		FiltroBitacoraConsultaDTO filtroBitacoraConsultaDTO = new FiltroBitacoraConsultaDTO(
				numeroExpediente, fechaConsulta, horaConsulta, funcionario, esPermitida);
		
		try {
			List<BitacoraConsultaDTO> bitacoras = service.consultarBitacoraConsultaPorFiltro(filtroBitacoraConsultaDTO);
			assertFalse("Se regresa una lista vacia de bitacota Consulta", bitacoras.isEmpty());
			
			for (BitacoraConsultaDTO bitacoraConsulta : bitacoras) {
				logger.info(" BitacoraConsulta - BitacoraConsultaId - : " + bitacoraConsulta.getBitacoraConsultaId());
				logger.info(" BitacoraConsulta - FechaConsulta - : " + bitacoraConsulta.getFechaConsulta());
				logger.info(" BitacoraConsulta - EsPermitida - : " + bitacoraConsulta.getEsPermitida());
				logger.info(" BitacoraConsulta - NumeroExpediente - : " + bitacoraConsulta.getNumeroExpediente());
				if(bitacoraConsulta.getNumeroExpediente()!= null){
					logger.info(" BitacoraConsulta - NumeroExpediente - NumeroExpedienteID : " + bitacoraConsulta.getNumeroExpedienteId());
					logger.info(" BitacoraConsulta - NumeroExpediente - NumeroExpediente : " + bitacoraConsulta.getNumeroExpediente());
				}
				logger.info(" BitacoraConsulta - Usuario - : " + bitacoraConsulta.getUsuario());
				if(bitacoraConsulta.getUsuario()!= null){
					logger.info(" BitacoraConsulta - Usuario - : " + bitacoraConsulta.getUsuario().getClaveUsuario());
					logger.info(" BitacoraConsulta - Usuario - : " + bitacoraConsulta.getUsuario().getFuncionario());
					if(bitacoraConsulta.getUsuario().getFuncionario()!= null){
						logger.info(" BitacoraConsulta - Usuario - Funcionario - ClaveFuncionario: " + bitacoraConsulta.getUsuario().getFuncionario().getClaveFuncionario());
						logger.info(" BitacoraConsulta - Usuario - Funcionario - NombreCompleto: " + bitacoraConsulta.getUsuario().getFuncionario().getNombreCompleto());
						logger.info(" BitacoraConsulta - Usuario - Funcionario - NombreFuncionario: " + bitacoraConsulta.getUsuario().getFuncionario().getNombreFuncionario());
						logger.info(" BitacoraConsulta - Usuario - Funcionario - ApellidoPaternoFuncionario: " + bitacoraConsulta.getUsuario().getFuncionario().getApellidoPaternoFuncionario());
						logger.info(" BitacoraConsulta - Usuario - Funcionario - ApellidoMaternoFuncionario: " + bitacoraConsulta.getUsuario().getFuncionario().getApellidoMaternoFuncionario());
					}
				}
			}
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		
	}
	
}
