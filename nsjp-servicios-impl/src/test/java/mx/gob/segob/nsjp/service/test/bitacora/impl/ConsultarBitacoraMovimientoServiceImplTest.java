/**
* Nombre del Programa : ConsultarBitacoraMovimientoServiceImplTest.java
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

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.bitacora.BitacoraMovimientoDTO;
import mx.gob.segob.nsjp.service.bitacora.ConsultarBitacoraMovimientoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Pruebas unitarias de los servicios para consultar la Bitacora de Movimiento.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarBitacoraMovimientoServiceImplTest extends BaseTestServicios<ConsultarBitacoraMovimientoService> {
	
	public void testConsultarBitacoraMovimientoPorNumeroExpedienteCategoria(){
		String numeroExpediente = null;//"NSJYUCPG2010442"; 
		Long idCategoriaElemento = null;//1L ;
		
		try {
			List<BitacoraMovimientoDTO> bitacoras = service.consultarBitacoraMovimientoPorNumeroExpedienteCategoria(numeroExpediente, idCategoriaElemento);
			
			for (BitacoraMovimientoDTO bitacoraMovimiento : bitacoras) {
				logger.info(" BitacoraMovimiento - BitacoraMovimientoId - : " + bitacoraMovimiento.getBitacoraMovimientoId());
				logger.info(" BitacoraMovimiento - FechaMovimiento - : " + bitacoraMovimiento.getFechaMovimiento());
				logger.info(" BitacoraMovimiento - GrupoDatos - : " + bitacoraMovimiento.getGrupoDatos());
				logger.info(" BitacoraMovimiento - Accion - : " + bitacoraMovimiento.getAccion());
				logger.info(" BitacoraMovimiento - Campo: " + bitacoraMovimiento.getCampo());
				logger.info(" BitacoraMovimiento - ValorAnterior: " + bitacoraMovimiento.getValorAnterior());
				logger.info(" BitacoraMovimiento - ValorNuevo: " + bitacoraMovimiento.getValorNuevo());

				logger.info(" BitacoraMovimiento - CategoriaElemento - : " + bitacoraMovimiento.getCategoriaElemento());
				if(bitacoraMovimiento.getCategoriaElemento()!=null){
					logger.info(" BitacoraMovimiento - CategoriaElemento - CategoriaElementoId : " + bitacoraMovimiento.getCategoriaElemento().getCategoriaElementoId());
					logger.info(" BitacoraMovimiento - CategoriaElemento - Nombre : " + bitacoraMovimiento.getCategoriaElemento().getNombre());
				}
				
				logger.info(" BitacoraMovimiento - NumeroExpediente - : " + bitacoraMovimiento.getNumeroExpediente());
				if(bitacoraMovimiento.getNumeroExpediente()!= null){
					logger.info(" BitacoraMovimiento - NumeroExpediente - NumeroExpedienteID : " + bitacoraMovimiento.getNumeroExpedienteId());
					logger.info(" BitacoraMovimiento - NumeroExpediente - NumeroExpediente : " + bitacoraMovimiento.getNumeroExpediente());
				}
				logger.info(" BitacoraMovimiento - Usuario - : " + bitacoraMovimiento.getUsuario());
				if(bitacoraMovimiento.getUsuario()!= null){
					logger.info(" BitacoraMovimiento - Usuario - : " + bitacoraMovimiento.getUsuario().getClaveUsuario());
					logger.info(" BitacoraMovimiento - Usuario - : " + bitacoraMovimiento.getUsuario().getFuncionario());
					if(bitacoraMovimiento.getUsuario().getFuncionario()!= null){
						logger.info(" BitacoraMovimiento - Usuario - Funcionario - ClaveFuncionario: " + bitacoraMovimiento.getUsuario().getFuncionario().getClaveFuncionario());
						logger.info(" BitacoraMovimiento - Usuario - Funcionario - NombreCompleto: " + bitacoraMovimiento.getUsuario().getFuncionario().getNombreCompleto());
						logger.info(" BitacoraMovimiento - Usuario - Funcionario - NombreFuncionario: " + bitacoraMovimiento.getUsuario().getFuncionario().getNombreFuncionario());
						logger.info(" BitacoraMovimiento - Usuario - Funcionario - ApellidoPaternoFuncionario: " + bitacoraMovimiento.getUsuario().getFuncionario().getApellidoPaternoFuncionario());
						logger.info(" BitacoraMovimiento - Usuario - Funcionario - ApellidoMaternoFuncionario: " + bitacoraMovimiento.getUsuario().getFuncionario().getApellidoMaternoFuncionario());
					}
				}
				logger.info(" BitacoraMovimiento - Elemento - : " + bitacoraMovimiento.getElemento());
				if(bitacoraMovimiento.getElemento()!=null){
					logger.info(" BitacoraMovimiento - Elemento - ElementoId: " + bitacoraMovimiento.getElemento());
				}
			}
			logger.info(" BitacoraMovimiento - TOTAL : " + bitacoras.size());
			
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		
		
		
	}
}
