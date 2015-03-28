/**
* Nombre del Programa : DatoPruebaDAOImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/09/2011
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
package mx.gob.segob.nsjp.dao.test.prueba.impl;

import java.util.Arrays;
import java.util.List;

import mx.gob.segob.nsjp.dao.prueba.DatoPruebaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.DatoPrueba;
import mx.gob.segob.nsjp.model.Involucrado;


/**
 * Pruebas unitarias para la entidad de Dato Prueba
 * @version 1.0
 * @author GustavoBP
 *
 */
public class DatoPruebaDAOImplTest  extends BaseTestPersistencia<DatoPruebaDAO>{

	public void testRegistrarDatoPrueba(){
		String nombreDato ="Dato Prueba";
		String numeroIdentificacion = "1234DFS232";
		
		DatoPrueba datoPrueba = new DatoPrueba();
		datoPrueba.setNombreDato(nombreDato);
		datoPrueba.setNumeroIdentificacion(numeroIdentificacion);
		
//		Long idDatoPrueba = daoServcice.create(datoPrueba);
//		logger.info(" Dato Prueba Registrado "+ idDatoPrueba);
	}
	
	public void tesConsultarDatoPrueba(){
		Long idDatoPrueba = 24L;
		
		DatoPrueba datoPrueba = daoServcice.read(idDatoPrueba);
		
		logger.info(datoPrueba);
		if(datoPrueba!= null){
			logger.info(" datoPrueba - DatoPruebaId:"+datoPrueba.getDatoPruebaId());
			logger.info(" datoPrueba - NombreDato:"+datoPrueba.getNombreDato());
			logger.info(" datoPrueba - NumeroIdentificacion:"+datoPrueba.getNumeroIdentificacion());
			logger.info(" datoPrueba - Descripcion:"+datoPrueba.getDescripcion());
			logger.info(" datoPrueba - FolioCadenaCustodia:"+datoPrueba.getFolioCadenaCustodia());
			logger.info(" datoPrueba - EsAceptado:"+datoPrueba.getEsAceptado());
			logger.info(" datoPrueba - TiempoCancelacion:"+datoPrueba.getTiempoCancelacion());
			
			logger.info(" datoPrueba - Expediente:"+datoPrueba.getExpediente());
			logger.info(" datoPrueba - ElementoPrueba:"+datoPrueba.getElementoPrueba());
//			logger.info(" datoPrueba - RelacionesDatosMedioPrueba:"+datoPrueba.getRelacionesDatosMedioPrueba());
//			logger.info(" datoPrueba - RelacionesPruebaInvolucrado:"+datoPrueba.getRelacionesPruebaInvolucrado());	
			}
	}
	

	public void testBuscarDatosDePrueba() {

		Long expedienteId = 10L;

		List<DatoPrueba> datosPrueba = daoServcice.buscarDatosDePrueba(expedienteId);
		assertTrue("La lista debe tener minimo un registro : ", datosPrueba.size()>0);
		for (DatoPrueba  datoPrueba : datosPrueba) {
			logger.info("datoPrueba - Id:" + datoPrueba.getDatoPruebaId());
			logger.info("datoPrueba - descripcion:" + datoPrueba.getDescripcion());
			logger.info("datoPrueba - folio CDC:" + datoPrueba.getFolioCadenaCustodia());
			logger.info("datoPrueba - folio Num Identificacion:" + datoPrueba.getNumeroIdentificacion());
			logger.info("datoPrueba - Es aceptado:" + datoPrueba.getEsAceptado());
			logger.info("datoPrueba - nombre:" + datoPrueba.getNombreDato());
			logger.info("datoPrueba - nombre:" + datoPrueba.getElementoPrueba());
		}
	}
	
	public void testBuscarDatosDePruebaPorInvolucrados(){
		Long[] idInv= {24L, 32L};
		List<Long> involucradosId= Arrays.asList(idInv);
		
		List<DatoPrueba> datosPrueba = daoServcice.buscarDatosDePruebaPorInvolucrados(involucradosId);
		logger.info("datoPrueba : "+ datosPrueba);
		for (DatoPrueba  datoPrueba : datosPrueba) {
			logger.info("datoPrueba - Id:" + datoPrueba.getDatoPruebaId());
			logger.info("datoPrueba - descripcion:" + datoPrueba.getDescripcion());
			logger.info("datoPrueba - folio CDC:" + datoPrueba.getFolioCadenaCustodia());
			logger.info("datoPrueba - folio Num Identificacion:" + datoPrueba.getNumeroIdentificacion());
			logger.info("datoPrueba - Es aceptado:" + datoPrueba.getEsAceptado());
			logger.info("datoPrueba - nombre:" + datoPrueba.getNombreDato());
			logger.info("datoPrueba - nombre:" + datoPrueba.getElementoPrueba());
		}
	}
	
	public void testConsultarInvolucradoConRelacionADatoPrueba(){
		Long expedienteId = 2L ;
		Long datoPruebaId = 41L;
		List<Involucrado> involucrados = daoServcice.consultarInvolucradoConRelacionADatoPrueba(expedienteId, datoPruebaId);
		assertTrue("La lista debe tener minimo un registro : ", involucrados.size()>0);
		logger.info("involucrados : "+ involucrados);
		for (Involucrado  involucrado : involucrados) {
			logger.info("Involucrado - Id:" + involucrado.getElementoId());
		}
	}
	
	public void testBuscarPruebasPorExpediente() {

		Long expedienteId = 1L;

		List<DatoPrueba> datosPrueba = daoServcice.buscarPruebasPorExpediente(expedienteId);
		assertTrue("La lista debe tener minimo un registro : ", datosPrueba.size()>0);
		for (DatoPrueba  datoPrueba : datosPrueba) {
			logger.info("datoPrueba - Id:" + datoPrueba.getDatoPruebaId());
			logger.info("datoPrueba - descripcion:" + datoPrueba.getDescripcion());
			logger.info("datoPrueba - folio CDC:" + datoPrueba.getFolioCadenaCustodia());
			logger.info("datoPrueba - folio Num Identificacion:" + datoPrueba.getNumeroIdentificacion());
			logger.info("datoPrueba - Es aceptado:" + datoPrueba.getEsAceptado());
			logger.info("datoPrueba - nombre:" + datoPrueba.getNombreDato());
			logger.info("datoPrueba - nombre:" + datoPrueba.getElementoPrueba());
		}
	}
}
