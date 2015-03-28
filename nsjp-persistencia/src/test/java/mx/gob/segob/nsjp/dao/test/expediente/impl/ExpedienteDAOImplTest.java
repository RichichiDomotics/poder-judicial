/**
*
* Nombre del Programa : ExpedienteDAOImplTest.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba para el DAO de Expediente                      
* Programa Dependiente  :N/A                                                      
* Programa Subsecuente :N/A                                                      
* Cond. de ejecucion        :N/A                                                      
* Dias de ejecucion          :N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor                       :N/A                                                           
* Compania               :N/A                                                           
* Proyecto                 :N/A                                   Fecha: N/A       
* Modificacion           :N/A                                                           
*------------------------------------------------------------------------------           
*/


package mx.gob.segob.nsjp.dao.test.expediente.impl;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NumeroExpediente;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 * 
 */
public class ExpedienteDAOImplTest extends BaseTestPersistencia<ExpedienteDAO> {

	public void testObtenerUltimoNumTipo () {
		String respuesta = daoServcice.obtenerUltimoNumTipoExp("ExpedienteRestDefensoria");
		assertNotNull("El string no debes ser nulo ", respuesta); 
	}
	
	public void testBuscarUltimoNumeroPorExpedienteIdAreaId(){
		Long areaId = 7L;
//		Long expedienteId = 47L; //49L;
		Long expedienteId = 296L;
		Expediente expediente = daoServcice.buscarUltimoNumeroPorExpedienteIdAreaId(expedienteId, areaId);
		assertNotNull("El expediente no debes ser nulo ", expediente);
		logger.info(" Expediente: "+ expediente.getExpedienteId());
		logger.info(" Expediente: "+ expediente.getNumeroExpedienteId());
		logger.info(" Expediente: "+ expediente.getNumeroExpediente());
		
	}
	public void _testObtenerPorEvidencia () {
		logger.debug("Prueba para obtener los expedientes que correspondan con la evidencia solicitada");

		List<Expediente> respuesta = daoServcice.buscarExpedientes("%NSJYUCPJ20114433333%",null,26L);
		assertFalse("La lista no puede venir vacia", respuesta.isEmpty());

		logger.info("Respuesta :: " + respuesta.size());
	}
	
	public void _testObtenerExpedientes() {
		logger.debug("Prueba para obtener los expedientes que correspondan con el parametro enviado");
		Long expedienteID = 1L;
		List<NumeroExpediente> respuesta = daoServcice.buscarNumeroExpedientes(expedienteID,10L);
		assertFalse("La lista no puede venir vacia", respuesta.isEmpty());

		logger.info("Respuesta :: " + respuesta.size());
		for (NumeroExpediente expediente : respuesta) {
//			logger.info(" Expediente: "+ expediente.getExpedienteId());
			logger.info(" Expediente: "+ expediente.getNumeroExpediente());
			logger.info(" Expediente: "+ expediente.getNumeroExpedienteId());
			
			
		}
	}

	public void _testRecuperarUltimoNumero() {
		logger.debug("Prueba para obtener todos los registros de Expediente ");

		String respuesta = daoServcice.obtenerUltimoNumero(44L);

		assertNotNull("No se ha obtenido el m�ximo de manera correcta",
				respuesta);
		assertTrue("No se ha obtenido el m�ximo de manera correcta",
				respuesta.length() > 0);
		logger.debug("Respuesta :: " + respuesta);
	}

	public void _testInsertar() {
		final Expediente exp = new Expediente();
		/*final Caso cas = new Caso();
		cas.setCasoId(1L);
		exp.setCaso(cas);*/
		exp.setFechaCreacion(new Date());
		exp.setNumeroExpediente("EXP0000INST0023");
		exp.setDescNarrativa("Narrativa de prueba 22");
		Long resultado = daoServcice.create(exp);
		assertTrue("El resultado debe ser mayor a 0 : ", resultado>0); 		
	}
	
	public void testConsultarExpedientesPorIdCaso(){
	    List<Expediente> respuesta = daoServcice.consultarExpedientesPorIdCaso(14L, null);
	    assertNotNull("No puede ser nula la lista", respuesta);
	    assertFalse("No puede estar vac�a la lista", respuesta.isEmpty());
	}
	
	public void testConsultarExpedientesPorId(){
	    List<Expediente> respuesta = daoServcice.consultarExpedientesPorId(14L, null);
	    assertNotNull("No puede ser nula la lista", respuesta);
	    assertFalse("No puede estar vac�a la lista", respuesta.isEmpty());
	}
	
	public void consultarExpedienteIdPorNumeroExpediente(){
		String numeroExpediente = "NSJYUCPJ2011263333W";
	    Long respuesta = daoServcice.consultarExpedienteIdPorNumeroExpediente(numeroExpediente);
	    assertNotNull("No puede ser nulo", respuesta);
	    logger.info("Respuesta"+ respuesta);
	}
	
	public void _testObtenerExpedientesPorActividad(){
	    List<Expediente> respuesta = daoServcice.consultarExpedientesPorActividadActual(1L,1L);
	    assertNotNull("No puede ser nula la lista", respuesta);
	    assertFalse("No puede estar vac�a la lista", respuesta.isEmpty());
	}
	
	public void testConsultarExpedientesActividadAreaAnio() {				
		List<NumeroExpediente> respuesta = daoServcice.consultarExpedientesActividadAreaAnio(new Long(Areas.UNIDAD_INVESTIGACION.ordinal()), 
																						Actividades.ATENDER_CANALIZACION_UI.getValorId(), 
																						2012L,1L,null);
		assertTrue("La lista debe tener minimo un registro ", respuesta.size()>0);
		
		logger.info("La lista debe tener minimo un registro " + respuesta.size());		
	}
	
	public void testConsultarExpedientesPorIdCasoConfInstitucion(){
		
		Long idCaso = 4L;
		Long idConfInstitucion = null;
		
		List<Expediente> expedientes = daoServcice.consultarExpedientesPorIdCasoConfInstitucion(idCaso, idConfInstitucion);
		
		if(expedientes!= null ){
			for (Expediente expediente : expedientes) {
				logger.info("ID:"+expediente.getExpedienteId());
			} 
		}
	}
	
public void testConsultarCausasPorIdCaso(){
		
		Long idCaso = 1L;  
		
		List<Expediente> expedientes = daoServcice.consultarCausasPorIdCaso(idCaso);
		
		if(expedientes!= null ){
			for (Expediente expediente : expedientes) {
				logger.info("IDExpediente:"+expediente.getExpedienteId());
				logger.info("IDNumeroExpediente:"+expediente.getNumeroExpedienteId());
				logger.info("Numero Expediente:"+expediente.getNumeroExpediente());
			} 
		}
	}

public void testReadExpediente(){
	Expediente expediente = daoServcice.read(1L);
	assertNotNull(expediente);
	logger.info("IDExpediente:"+expediente.getExpedienteId());
	logger.info("IDNumeroExpediente:"+expediente.getNumeroExpedienteId());
	logger.info("Numero Expediente:"+expediente.getNumeroExpediente());
	if(expediente.getNumeroExpedientes()!=null){
		for (NumeroExpediente nexp : expediente.getNumeroExpedientes()) {
			logger.info("-----------------");
			logger.info("id: "+nexp.getNumeroExpedienteId());
			logger.info("numero:"+nexp.getNumeroExpediente());
			if(nexp.getNumeroExpedientePadre()!=null)
			logger.info("padre: "+nexp.getNumeroExpedientePadre().getNumeroExpedienteId());
//			if(nexp.getNumeroExpedientes()!=null)
//			logger.info("# hijos:"+nexp.getNumeroExpedientes().size());
		}
	}
}

	public void testObtenerExpedientesPorMes () {
		
		try {
			List<Object[]> resultado = daoServcice.obtenerExpedientesPorMes(DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"));
			assertTrue("La lista debe tener minimo un registro ", resultado.size()>0);
			
			for (Object[] objects : resultado) {
				logger.info("Mes "+ objects[0] + " num " + objects[1]);
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public void testRecuperarUltimoNumeroDeExpedienteDeProcu() {
		logger.debug("Prueba para obtener todos los registros de Expediente ");

		String respuesta = daoServcice.obtenerUltimoNumeroDeExpediente(44L);

		assertNotNull("No se ha obtenido el m�ximo de manera correcta",
				respuesta);
		assertTrue("No se ha obtenido el m�ximo de manera correcta",
				respuesta.length() > 0);
		logger.debug("Respuesta :: " + respuesta);
	}
}
