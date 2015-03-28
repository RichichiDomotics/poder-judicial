/**
* Nombre del Programa : RelNumExpedienteAuditoriaDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 26/09/2011
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
package mx.gob.segob.nsjp.dao.test.expediente.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.expediente.EstatusExpediente;
import mx.gob.segob.nsjp.dao.expediente.RelNumExpedienteAuditoriaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RelNumExpedienteAuditoria;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Clase de Pruebas Unitarias para Relacion de Número de Expediente y número de Auditoría.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class RelNumExpedienteAuditoriaDAOImplTest extends BaseTestPersistencia<RelNumExpedienteAuditoriaDAO>{

	public void testIngresarRegistro(){
		RelNumExpedienteAuditoria auditoria = new RelNumExpedienteAuditoria();
		auditoria.setNumeroExpediente(new NumeroExpediente(1L));
		auditoria.setNumeroAuditoriaId(2L);
		
//		Long id = daoServcice.create(auditoria);
//		logger.info("Valor ingresado:"+ id);
	}

	
	public void testConsultarRelNumeroExpedienteAuditoriaPorFiltroCompleto(){
		RelNumExpedienteAuditoria filtro = new RelNumExpedienteAuditoria();
//		filtro.setRelNumExpedienteAuditoriaId(4L);
//		filtro.setNumeroAuditoriaId(5L);
		
		//ADVERTENCIA: Si se instancia el objeto nuevo de NumeroExpediente, se settea en estatus Abierto por default.
		//DATOS DE PRUEBA NUMERO EXPEDIENTE AUDITADO
		NumeroExpediente numExpedienteAuditado =  null; //new NumeroExpediente();
//		numExpedienteAuditado.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
//		numExpedienteAuditado.setEstatus(null);
//		numExpedienteAuditado.setNumeroExpedienteId(10L);
//		JerarquiaOrganizacional jerarquiaOrganizacional = new JerarquiaOrganizacional(10L);
//		numExpedienteAuditado.setJerarquiaOrganizacional(jerarquiaOrganizacional);
//		Funcionario funcionario = new Funcionario(10L);
//		numExpedienteAuditado.setFuncionario(funcionario );
		
		//DATOS DE PRUEBA NUMERO EXPEDIENTE CARPETA DE AUDITORIA
		NumeroExpediente numCarpetaAuditoria =  new NumeroExpediente(); //null;
		numCarpetaAuditoria.setEstatus(new Valor(EstatusExpediente.ABIERTO.getValorId()));
//		numCarpetaAuditoria.setEstatus(null);
//		numCarpetaAuditoria.setNumeroExpedienteId(187L);
//		JerarquiaOrganizacional jerarquiaOrganizacional = new JerarquiaOrganizacional(16L);
//		numCarpetaAuditoria.setJerarquiaOrganizacional(jerarquiaOrganizacional);
//		Funcionario funcionario = new Funcionario(2L);
//		numCarpetaAuditoria.setFuncionario(funcionario );
		
		
		List<RelNumExpedienteAuditoria> resultados = daoServcice.consultarRelNumeroExpedienteAuditoriaPorFiltro(
				filtro, numExpedienteAuditado, numCarpetaAuditoria);
		assertTrue("La lista debe tener minimo un registro : ", resultados.size()>0);
		logger.info("Número de registros : " + resultados.size());
		
		for (RelNumExpedienteAuditoria relNumExpedienteAuditoria : resultados) {
			logger.info(" "+ relNumExpedienteAuditoria.getRelNumExpedienteAuditoriaId());
			logger.info(" "+ relNumExpedienteAuditoria.getNumeroExpediente());
			if(relNumExpedienteAuditoria.getNumeroExpediente()!= null){
				logger.info(" "+ relNumExpedienteAuditoria.getNumeroExpediente().getNumeroExpedienteId());
				logger.info(" "+ relNumExpedienteAuditoria.getNumeroExpediente().getNumeroExpediente());
			}
			logger.info(" "+ relNumExpedienteAuditoria.getNumeroAuditoriaId());
		}
		logger.info("Número de registros : " + resultados.size());
	}
	
	public void testBuscarRelacionPorIdDeAuditoria(){
		Long idAuditoria = 5L;		
		RelNumExpedienteAuditoria relacion = daoServcice.buscarRelacionDeNumExpPorIdAuditoria(idAuditoria);
		
		if(relacion != null){
			assertNotNull(relacion);
			logger.info("ID de la relacion: "+ relacion.getRelNumExpedienteAuditoriaId());
			logger.info("Numero de auditoria: "+ relacion.getNumeroAuditoriaId());
			NumeroExpediente loNumeroExpediente = relacion.getNumeroExpediente();
			if(loNumeroExpediente != null){
				logger.info("INFO DEL NUMERO EXPEDIENTE BASE");
				logger.info("  Nombre del AMP dueño del expediente auditado: "+ (loNumeroExpediente.getFuncionario() != null && loNumeroExpediente.getFuncionario().getNombreCompleto() != null?
						loNumeroExpediente.getFuncionario().getNombreCompleto() :"-"));
				logger.info("  Numero de expediente auditado: "+ (loNumeroExpediente.getNumeroExpediente() != null ? loNumeroExpediente.getNumeroExpediente() :"-"));
				logger.info("  Area del expediente auditado: "+ (loNumeroExpediente.getJerarquiaOrganizacional() != null &&
						loNumeroExpediente.getJerarquiaOrganizacional().getNombre() != null ? loNumeroExpediente.getJerarquiaOrganizacional().getNombre() :"-"));
				logger.info("  Fecha de creacion de la carpeta auditada: "+ (loNumeroExpediente.getFechaApertura()!= null && loNumeroExpediente.getFechaApertura() != null?
						loNumeroExpediente.getFechaApertura() :"-"));
			}
			
		}else{
			assertNull(relacion);
		}
	}
}
