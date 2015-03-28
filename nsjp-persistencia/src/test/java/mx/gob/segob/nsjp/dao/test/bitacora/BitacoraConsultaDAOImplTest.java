/**
* Nombre del Programa : BitacoraConsultaDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 31/08/2011
* Marca de cambio        : N/A
* Descripcion General    : Pruebas Unitarias para el DAO de Bitacora Consulta
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

import mx.gob.segob.nsjp.dao.bitacora.BitacoraConsultaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.BitacoraConsulta;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;

/**
 * Pruebas Unitarias para el DAO de Bitacora Consulta
 * @version 1.0
 * @author GustavoBP
 *
 */
public class BitacoraConsultaDAOImplTest extends BaseTestPersistencia<BitacoraConsultaDAO> {

	public void testConsultarBitacoraConsultaPorFiltros(){
		String numeroExpediente = null; //"NSJYUCPG2010441"; 
		Date fechaConsulta  = null; //new Date();
		Date horaConsulta = null; //new Date();
		
		Funcionario funcionario = new Funcionario();
//		funcionario.setNombreFuncionario("Daniel");
//		funcionario.setApellidoPaternoFuncionario("Flores");
//		funcionario.setApellidoMaternoFuncionario("de");
		
		Boolean esPermitida = true;
		
		List<BitacoraConsulta> bitacoras = daoServcice.consultarBitacoraConsultaPorFiltros(numeroExpediente, fechaConsulta, horaConsulta, funcionario, esPermitida);
		assertFalse("Se regresa una lista vacia de bitacota Consulta", bitacoras.isEmpty());
		
		for (BitacoraConsulta bitacoraConsulta : bitacoras) {
			logger.info(" BitacoraConsulta - BitacoraConsultaId - : " + bitacoraConsulta.getBitacoraConsultaId());
			logger.info(" BitacoraConsulta - FechaConsulta - : " + bitacoraConsulta.getFechaConsulta());
			logger.info(" BitacoraConsulta - EsPermitida - : " + bitacoraConsulta.getEsPermitida());
			logger.info(" BitacoraConsulta - NumeroExpediente - : " + bitacoraConsulta.getNumeroExpediente());
			if(bitacoraConsulta.getNumeroExpediente()!= null){
				logger.info(" BitacoraConsulta - NumeroExpediente - NumeroExpedienteID : " + bitacoraConsulta.getNumeroExpediente().getNumeroExpedienteId());
				logger.info(" BitacoraConsulta - NumeroExpediente - NumeroExpediente : " + bitacoraConsulta.getNumeroExpediente().getNumeroExpediente());
			}
			logger.info(" BitacoraConsulta - Usuario - : " + bitacoraConsulta.getUsuario());
			if(bitacoraConsulta.getUsuario()!= null){
				logger.info(" BitacoraConsulta - Usuario - : " + bitacoraConsulta.getUsuario().getUsuarioId());
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
		logger.info(" BitacoraConsulta - TOTAL : " + bitacoras.size());
	}
	
	public void __testCrearRegistroNuevo(){
		Date fechaConsulta = new Date();
		Boolean esPermitida = true;
		Long numeroExpedienteId = 2L ;
		Long usuarioId = 3L;
		
		BitacoraConsulta bitacora = new BitacoraConsulta();
		bitacora.setFechaConsulta(fechaConsulta);
		bitacora.setEsPermitida(esPermitida);
		bitacora.setNumeroExpediente(new NumeroExpediente(numeroExpedienteId));
		bitacora.setUsuario(new Usuario(usuarioId));
		
		Long idBitacoraConsulta = daoServcice.create(bitacora);
		
		logger.info(" Datos registrados id: "+ idBitacoraConsulta);
	}
}
