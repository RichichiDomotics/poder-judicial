/**
* Nombre del Programa : BitacoraMovimientoDAO.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas Unitarias para el DAO de Bitacora Movimiento
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
package mx.gob.segob.nsjp.dao.test.bitacora;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.bitacora.BitacoraMovimientoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.BitacoraMovimiento;
import mx.gob.segob.nsjp.model.CategoriaElemento;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * Pruebas Unitarias para el DAO de Bitacora  Movimiento.
 * 
 * @version 1.0
 * @author GustavoBP
 */
public class BitacoraMovimientoDAOImplTest extends BaseTestPersistencia<BitacoraMovimientoDAO> {
	
	public void testConsultarBitacoraMovimientoPorFiltro(){
		String numeroExpediente = null; //"NSJYUCPG2010442"; 
		Long idCategoriaElemento = 2L ;
		
		List<BitacoraMovimiento> bitacoras = daoServcice.consultarBitacoraMovimientoPorFiltro(numeroExpediente, idCategoriaElemento);
		assertFalse("Se regresa una lista vacia de bitacota Consulta", bitacoras.isEmpty());
		
		for (BitacoraMovimiento bitacoraMovimiento : bitacoras) {
			logger.info(" BitacoraMovimiento - BitacoraMovimientoId - : " + bitacoraMovimiento.getBitacoraMovimientoId());
			logger.info(" BitacoraMovimiento - FechaMovimiento - : " + bitacoraMovimiento.getFechaMovimiento());
			logger.info(" BitacoraMovimiento - GrupoDatos - : " + bitacoraMovimiento.getGrupoDatos());
			logger.info(" BitacoraMovimiento - Accion - : " + bitacoraMovimiento.getAccion());
			logger.info(" BitacoraMovimiento - Campo : " + bitacoraMovimiento.getCampo());
			logger.info(" BitacoraMovimiento - ValorAnterior: " + bitacoraMovimiento.getValorAnterior());
			logger.info(" BitacoraMovimiento - ValorNuevo: " + bitacoraMovimiento.getValorNuevo());
			logger.info(" BitacoraMovimiento - CategoriaElemento - : " + bitacoraMovimiento.getCategoriaElemento());
			if(bitacoraMovimiento.getCategoriaElemento()!=null){
				logger.info(" BitacoraMovimiento - CategoriaElemento - CategoriaElementoId : " + bitacoraMovimiento.getCategoriaElemento().getCategoriaElementoId());
				logger.info(" BitacoraMovimiento - CategoriaElemento - Nombre : " + bitacoraMovimiento.getCategoriaElemento().getNombre());
			}
			
			logger.info(" BitacoraMovimiento - NumeroExpediente - : " + bitacoraMovimiento.getNumeroExpediente());
			if(bitacoraMovimiento.getNumeroExpediente()!= null){
				logger.info(" BitacoraMovimiento - NumeroExpediente - NumeroExpedienteID : " + bitacoraMovimiento.getNumeroExpediente().getNumeroExpedienteId());
				logger.info(" BitacoraMovimiento - NumeroExpediente - NumeroExpediente : " + bitacoraMovimiento.getNumeroExpediente().getNumeroExpediente());
			}
			logger.info(" BitacoraMovimiento - Usuario - : " + bitacoraMovimiento.getUsuario());
			if(bitacoraMovimiento.getUsuario()!= null){
				logger.info(" BitacoraMovimiento - Usuario - : " + bitacoraMovimiento.getUsuario().getUsuarioId());
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
				logger.info(" BitacoraMovimiento - Elemento - TipoElemento: " + bitacoraMovimiento.getElemento());
				logger.info(" BitacoraMovimiento - Elemento - TipoElemento(: " + bitacoraMovimiento.getElemento());
			}
		}
		logger.info(" BitacoraMovimiento - TOTAL : " + bitacoras.size());
	}
	
	
	public void testCrearRegistroNuevo(){
		Date fechaMovimiento = new Date();
		String grupoDatos = "Grupo de Datos Prueba 2";
		String accion = "Ingresar 2";
		
		Long numeroExpedienteId = 2L ;
		Long usuarioId = 2L;
		Long elementoId = 939L;
		Long categoriaElementoId = 5L;
		
		BitacoraMovimiento bitacora = new BitacoraMovimiento();
		bitacora.setFechaMovimiento(fechaMovimiento);
		bitacora.setGrupoDatos(grupoDatos);
		bitacora.setAccion(accion);
		
		bitacora.setNumeroExpediente(new NumeroExpediente(numeroExpedienteId));
		bitacora.setUsuario(new Usuario(usuarioId));
		bitacora.setElemento(elementoId);
		bitacora.setCategoriaElemento(new CategoriaElemento(categoriaElementoId));
		
		Long idBitacoraMovimiento = daoServcice.create(bitacora);
		
		logger.info(" Datos registrados id: "+ idBitacoraMovimiento);
	}

}
