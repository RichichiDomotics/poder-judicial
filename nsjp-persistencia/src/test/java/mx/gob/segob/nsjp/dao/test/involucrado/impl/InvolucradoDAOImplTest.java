/**
* Nombre del Programa : InvolucradoDAOImplTest.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 12 Apr 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para generar pruebas unitarias de los medotos de InvolucradoDAO
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
package mx.gob.segob.nsjp.dao.test.involucrado.impl;

import java.util.Arrays;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;

/**
 * Clase para generar pruebas unitarias de los medotos de InvolucradoDAO.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class InvolucradoDAOImplTest extends BaseTestPersistencia<InvolucradoDAO> {

	public void testbuscarExpedientesByNombre() {
		logger.debug("Prueba para recuperar los involucrados que correpondan con el nombre enviado");
	
		List<Involucrado> involucradosResultado = daoServcice.consultarExpedientesByNombre("%%", "%lacatolica%", "%%");
	
		assertTrue("La lista debe traer almenos un registro ", involucradosResultado.size()>0);
		logger.info("Involucrados : " + involucradosResultado.size());
		for (Involucrado involucrado : involucradosResultado) {
			logger.info("Id Involucrado : " + involucrado.getElementoId());			
		}
		
	}
	
	public void testEliminarInvolucrado(){
		Involucrado inv = daoServcice.read(310L);		
		daoServcice.delete(inv);
		 inv = daoServcice.read(312L);		
		daoServcice.delete(inv);
		 inv = daoServcice.read(313L);		
		daoServcice.delete(inv);
		 inv = daoServcice.read(314L);		
		daoServcice.delete(inv);
		 inv = daoServcice.read(315L);		
		daoServcice.delete(inv);
	}
	
	public void _testConsultarInvolucradosByExpediente() {
		logger.debug("Prueba para recuperar los involucrados que correpondan con el alias enviado");
	
		List<Involucrado> involucradosResultado = daoServcice.consultarInvolucradosByExpediente(168L);
				
		assertTrue("La lista debe traer almenos un registro ", involucradosResultado.size()>0);
		for (Involucrado involucrado : involucradosResultado) {
			//logger.info("Expediente : " + involucrado.getExpediente().getNumeroExpediente());
			logger.info("Id::::"+involucrado.getElementoId());
			logger.info("Nombre::::"+involucrado.getNombreDemograficos().iterator().next().getNombre());
			logger.info("Calidad::::"+involucrado.getCalidad().getTipoCalidad().getValor());
		}
		logger.info("Involucrados : " + involucradosResultado.size());

		
	}
	
	public void _testConsultarInvolucradosByExpedienteLike() {
		logger.debug("Prueba para recuperar los involucrados que correpondan con el alias enviado");
	
		List<Involucrado> involucradosResultado = daoServcice.consultarExpedientesByAlias("%hom%");
				
		assertTrue("La lista debe traer almenos un registro ", involucradosResultado.size()>0);
		logger.info("Involucrados : " + involucradosResultado.size());
		for (Involucrado involucrado : involucradosResultado) {
			logger.info("Expediente : " + involucrado.getExpediente().getNumeroExpediente());
		}
		
	}
	
	
	public void testObtenerInvolucradosPorExpedienteYCalidades () {
		Calidades[] calidades = new Calidades[]{Calidades.VICTIMA_PERSONA,
				Calidades.PROBABLE_RESPONSABLE_PERSONA,
				Calidades.PROBABLE_RESPONSABLE_ORGANIZACION,
				Calidades.DENUNCIANTE}; 
		List<Involucrado> involucrados = daoServcice.obtenerInvolucradosPorExpedienteYCalidades(69L, calidades);
		assertFalse("La lista no puede venir vacia: ", involucrados.isEmpty());
		logger.info("Involucrado " + involucrados.size());
		for (Involucrado involucrado : involucrados) {
			logger.info("Involucrado " + involucrado.getElementoId());
			logger.info("Involucrado " + involucrado);
			logger.info("Involucrado " + involucrado.getNombreDemograficos());
		}
		logger.info("Involucrado " + involucrados.size());
	}
	
	public void _testIngresarInvolucrado() {
		//ExpedienteDAOImplTest expedienteDao = new ExpedienteDAOImplTest();
		logger.debug("Prueba para ingresar un nuevo involucrado");
		Expediente expediente = new Expediente(new Long(1), "0");
		
		Involucrado involucrado = new Involucrado();
		
		
		involucrado.setEsVivo(false);		
		involucrado.setMotivoComparecencia("Por rata");		
		involucrado.setExpediente(expediente);
		involucrado.setCondicion((short) 1L);
		
		Long idInvolucrado = daoServcice.create(involucrado);
		assertTrue("El idInvolucrado debe ser mayor a cero", idInvolucrado>0);
	}
	
	public void testObtenerInvolucrados() {
		logger.debug("Prueba para obtener los registros de involucrados");
		
		List<Involucrado> involucrados = daoServcice.obtenerInvolucradosAll();
		assertFalse("La lista no puede venir vacia", involucrados.isEmpty());	
	}
	
	public void testObtenerInvolucradosExpedinteCalidad () {
		List<Involucrado> involucrados =  daoServcice.obtenerInvByIdExpAndCalidad(136L, Calidades.DENUNCIANTE.getValorId(),null);
		assertTrue("El id debe ser mayor a cero : ", involucrados.size()>0);
		for (Involucrado involucrado : involucrados) {
			logger.info("ID involucrado " + involucrado.getElementoId());	
		}
		
	}
	
	public void obtenerInvolucradosByAudiencia(){
		Long audienciaId=2L;
		Calidades calidadId=Calidades.PROBABLE_RESPONSABLE_PERSONA;
		List<Involucrado> involucrados = daoServcice.obtenerInvolucradosByAudiencia(audienciaId, calidadId.getValorId());
		
		logger.info("\n\rExisten "+involucrados.size()+" involucrados");
		for (Involucrado inv : involucrados) {
			logger.info("ID elemento"+inv.getElementoId());
			logger.info("Situacion juridica: "+inv.getSituacionJuridica());
			logger.info("CAlidad: "+inv.getCalidad().getTipoCalidad());
		}
	}
	
	public void testObtenerInvolucradosByNumeroExpedienteIdYCalidades(){
		
		
		List<Involucrado> involucrados = daoServcice.obtenerInvolucradosByCasoYCalidades("YUC/PG/XX/PGE/2011/AA-00000", 
				new Calidades[]{Calidades.VICTIMA_PERSONA,Calidades.PROBABLE_RESPONSABLE_PERSONA});
		for (Involucrado inv : involucrados) {
			logger.info("ID elemento"+inv.getElementoId());
			
		}
		
	}
	
	public void testConsultarInvolucradosByIds(){
		
		List<Long> idInvolucrados = Arrays.asList(1L,2L, 22L);
		
		List<Involucrado> involucrados = daoServcice.consultarInvolucradosByIds(idInvolucrados);
		
		assertTrue("La lista debe traer almenos un registro ", involucrados.size()>0);
		logger.info("#Involucrados : " + involucrados.size());
		for (Involucrado involucradoDTO : involucrados) {
			logger.info(" Involucrado ID: "+ involucradoDTO.getElementoId());
			logger.info(" Involucrado: "+ involucradoDTO);
		}
	}
	
	public void testObtenerExpedientesPorCondicionDetencionInvYMes () {
		try {
			List<Object[]> respuesta = daoServcice.obtenerExpedientesPorCondicionDetencionInvYMes(
										DateUtils.obtener("01/07/2011"), DateUtils.obtener("31/08/2011"), false);
			assertTrue("La lista debe tener minimo un registro ", respuesta.size()>0);
			logger.info("Numero registros rspuesta " + respuesta.size());
			
			for (Object[] objects : respuesta) {
				logger.info("MES "+objects[0]+" REGISTROS "+objects[0]);				
			}
		} catch (NSJPNegocioException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void testObtenerInvolucradoByFolioElemento () {
		Involucrado respuesta = daoServcice.obtenerInvolucradoByFolioElemento("pj1");
		
		assertNotNull(respuesta);
		logger.info("-----------------------");
		logger.info("Involucrado ID :: "+respuesta.getElementoId());
		logger.info("-----------------------");
	}
}
