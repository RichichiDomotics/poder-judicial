/**
*
* Nombre del Programa : CasoDAOImplTest.java                                    
* Autor                            : Cesar Agustin                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 30/03/2011 
* Marca de cambio        : N/A                                                     
* Descripcion General    : Prueba unitaria para el DAO de Caso                      
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

package mx.gob.segob.nsjp.dao.test.caso.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.caso.CasoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Delito;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.model.Vehiculo;

/**
 * 
 * @author CesarAgustin
 * @version 1.0
 *
 */
public class CasoDAOImplTest extends BaseTestPersistencia<CasoDAO> {

	public void testRecuperarUltimoNumero(){
		ConfInstitucion institucionActual;
		try {
			institucionActual = daoServcice.consultarInsitucionActual();
			String ultimoNumero = daoServcice.recuperarUltimoNumero(institucionActual.getMonograma());
			logger.info(" numero:" + ultimoNumero);
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		}
	}
	
	public void _testAll ()  {
		logger.debug("Prueba para obtener todos los registros de Caso ");
		
		List<Long> respuesta = daoServcice.findAllId();
		assertFalse("La lista no puede venir vacia", respuesta.isEmpty());
		
		logger.info("Respuesta :: " + respuesta);
	}
	
	
	public void _testConsultarCasosPorFecha () {
		logger.info("Prueba unitaria para consultar los casos por el numero de caso");
		
		String inicio = "03/05/2011";
		String fin = "04/05/2011";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
		Date fechaInicio = formato.parse(inicio);
		Date fechaFin =formato.parse(fin);
			fechaInicio = formato.parse(inicio);
			fechaFin = formato.parse(fin);		
				
		List<Caso> respuesta = daoServcice.consultarCasosPorFecha(fechaInicio, fechaFin);
		assertFalse("La lista debe tener registros de Caso ", respuesta.isEmpty());
		
		logger.info("Casos recuperados : " + respuesta.size());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}
	}
	
	public void testCreate(){
	    Caso pojo = new Caso();
	    pojo.setNumeroGeneralCaso("YUC/PG/XX/PGE/2011/AA-00065");
	    
	    super.daoServcice.create(pojo);
	}
	
	public void testConsultarCasosPorDelito () {
		logger.info("Prueba unitaria para consultar los casos por el delito");
						
		List<Caso> respuesta = daoServcice.consultarCasosPorDelito("%Vio%");
		for (Caso caso : respuesta) {
			logger.info("caso ID : " + caso.getCasoId());
			logger.info("Numero general caso : " + caso.getNumeroGeneralCaso());
		}
		assertFalse("La lista debe tener registros de Caso ", respuesta.isEmpty());
		
		logger.info("Casos recuperados : " + respuesta.size());		
	}
	
	public void _testConsultarCasosPorNumero () {
		logger.info("Prueba unitaria para consultar los casos por el numero de caso");
		
		List<Caso> respuesta = daoServcice.consultarCasosPorNumero("%CU0000-VAP-000001%");
		assertFalse("La lista debe tener registros de Caso ", respuesta.isEmpty());
		
		logger.info("Casos recuperados : " + respuesta.size());
	}
	
	public void testConsultarCasosPorUsuario(){
	       logger.info("Prueba unitaria para consultar los casos por el numero de caso");
	        
	        List<Caso> respuesta = daoServcice.consultarCasos(null);
	        assertFalse("La lista debe tener registros de Caso ", respuesta.isEmpty());
	        
	        logger.info("Casos recuperados : " + respuesta.size());
	}
	
	public Delito generaDelitoValido(Expediente exp){
		Delito loDelito = new Delito();
		loDelito.setEsProbable(true);
		loDelito.setExpediente(exp);
//		loDelito.setNombreDelito("nombreDelito");
		return loDelito;		
	}
	
	public Elemento generaElementoValido(Expediente exp){
		Elemento loElemento = new Elemento();
		loElemento.setExpediente(exp);
		loElemento.setFechaCreacionElemento(new Date());
		Calidad loCalPR = new Calidad();
		loCalPR.setCalidadId(Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId());
		loElemento.setCalidad(loCalPR);
		Valor loValor = new Valor();
		loValor.setValorId(427L);
		loElemento.setTipoElemento(loValor);
		return loElemento;
	}
	
	public void testConsultarCasoPorExpediente(){
		Caso caso = daoServcice.consultarCasoPorExpediente(133L);
		logger.info("El caso es:"+
				"\nID: "+caso.getCasoId()+
				"\nNumero: "+caso.getNumeroGeneralCaso());
	}
	
	public void testObtenerCasoByNumeroGeneral() {
		Caso caso = daoServcice.obtenerCasoByNumeroGeneral("YUC/PG/XX/PGE/2011/AA-00085");
		
		assertNotNull(caso);
		logger.info("Caso :: "+caso.getCasoId());
		logger.info("Num Caso :: "+caso.getNumeroGeneralCaso());
	}
	
	public void testConsultarCasosPorCaracteristicaDeElementos_Vehiculo(){	        
			Vehiculo vehiculo = new Vehiculo();
			vehiculo.setPlaca("748PUY");
			vehiculo.setNoSerie("12345");
			//vehiculo.setPlaca(null);
			//vehiculo.setNoSerie(null);
	        List<Caso> respuesta = daoServcice.consultarReincidenciasDeElementos(vehiculo);
	        assertFalse("La lista debe tener registros de Caso ", respuesta.isEmpty());
	        for (Caso caso : respuesta) {
	        	//logger.info("ID caso:" + caso.getCasoId());
	        	logger.info("Numero General del caso:"+ caso.getNumeroGeneralCaso());
	        	//logger.info("Fecha del caso:" + caso.getFechaApertura());
	        	
			}
	        logger.info("Casos recuperados : " + respuesta.size());
	}
	
	
	public void testConsultarCasosPorCaracteristicaDeElementos_Involucrado(){	        
		Involucrado persona = new Involucrado();
		Set<NombreDemografico> nombreDemos = new HashSet<NombreDemografico>();
		NombreDemografico loND = new NombreDemografico();
		loND.setNombre("armando");
		loND.setApellidoPaterno("castaneda");
		loND.setApellidoMaterno("tenango");
		nombreDemos.add(loND);
		persona.setNombreDemograficos(nombreDemos);

        List<Caso> respuesta = daoServcice.consultarReincidenciasDeElementos(persona);
        assertFalse("La lista debe tener registros de Caso ", respuesta.isEmpty());
        for (Caso caso : respuesta) {
        	//logger.info("ID caso:" + caso.getCasoId());
        	logger.info("Numero General del caso:"+ caso.getNumeroGeneralCaso());
        	//logger.info("Fecha del caso:" + caso.getFechaApertura());
        	
		}
        logger.info("Casos recuperados : " + respuesta.size());
}
	
	public void testConsultarIdXNumeroCaso(){
		Long idcaso = daoServcice.consultarIdXNumeroCaso("YUC/PG/XX/PGE/2011/AA-00003");
		assertNotNull(idcaso);
		logger.info("id caso: "+idcaso);
	}
}
