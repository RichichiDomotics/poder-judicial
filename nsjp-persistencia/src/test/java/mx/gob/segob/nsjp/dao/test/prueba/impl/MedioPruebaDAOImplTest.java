/**
* Nombre del Programa : MedioPruebaDAOImplTest.java
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

import java.util.List;

import mx.gob.segob.nsjp.dao.prueba.MedioPruebaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.MedioPrueba;
import mx.gob.segob.nsjp.model.RelacionDatoMedioPrueba;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MedioPruebaDAOImplTest  extends BaseTestPersistencia<MedioPruebaDAO> {

	public void testRegistrarMedioPrueba(){
		String nombreMedio ="Medio Prueba";
		String numeroIdentificacion = "1234DFS232XX33";
		
		MedioPrueba medioPrueba = new MedioPrueba();
		medioPrueba.setNombreMedio(nombreMedio);
		medioPrueba.setNumeroIdentificacion(numeroIdentificacion);
		
//		Long idMedioPrueba = daoServcice.create(medioPrueba);
//		logger.info(" Medio Prueba Registrado "+ idMedioPrueba);
	}
	
	public void testConsultarMedioPrueba(){
		Long idMedioPrueba = 20L;
		
		MedioPrueba medioPrueba = daoServcice.read(idMedioPrueba);
		
		logger.info(medioPrueba);
		if(medioPrueba!= null){
			logger.info(" medioPrueba - MedioPruebaId:"+medioPrueba.getMedioPruebaId());
			logger.info(" medioPrueba - NombreMedio:"+medioPrueba.getNombreMedio());
			logger.info(" medioPrueba - NumeroIdentificacion:"+medioPrueba.getNumeroIdentificacion());
			logger.info(" medioPrueba - Descripcion:"+medioPrueba.getDescripcion());
			logger.info(" medioPrueba - UbicacionFisica:"+medioPrueba.getUbicacionFisica());

			logger.info(" medioPrueba - ElementoMedioPrueba:"+medioPrueba.getElementoMedioPrueba());
//			logger.info(" medioPrueba - RelacionesDatoMedioPrueba:"+medioPrueba.getRelacionesDatoMedioPrueba());
			}
	}
	
	public void tesConsultarMediosPruebaPorDatoPrueba(){
		Long expedienteID = 1L;
		Long datoPruebaId = 28L;
		List<MedioPrueba> listaMedioPrueba = daoServcice.consultarMediosPruebaPorDatoPrueba(datoPruebaId, expedienteID, true);
		
		for (MedioPrueba medioPrueba : listaMedioPrueba) {
			logger.info(medioPrueba);
			if(medioPrueba!= null){
				logger.info(" medioPrueba - MedioPruebaId:"+medioPrueba.getMedioPruebaId());
				logger.info(" medioPrueba - NombreMedio:"+medioPrueba.getNombreMedio());
				logger.info(" medioPrueba - NumeroIdentificacion:"+medioPrueba.getNumeroIdentificacion());
				logger.info(" medioPrueba - Descripcion:"+medioPrueba.getDescripcion());
				logger.info(" medioPrueba - UbicacionFisica:"+medioPrueba.getUbicacionFisica());
	
				logger.info(" medioPrueba - ElementoMedioPrueba:"+medioPrueba.getElementoMedioPrueba());
				logger.info(" medioPrueba - RelacionesDatoMedioPrueba:"+medioPrueba.getRelacionesDatoMedioPrueba());
				if(medioPrueba.getRelacionesDatoMedioPrueba()!= null && !medioPrueba.getRelacionesDatoMedioPrueba().isEmpty()){
					for (RelacionDatoMedioPrueba relacionDatoMedioPrueba : medioPrueba.getRelacionesDatoMedioPrueba()) {
						logger.info(" RelacionesDatoMedioPrueba:"+relacionDatoMedioPrueba.getRelacionDatoMedioPruebaId());
						logger.info(" RelacionesDatoMedioPrueba:"+relacionDatoMedioPrueba.getEsAceptado());
						
					}
				}
			}
		}
	}

	
}
