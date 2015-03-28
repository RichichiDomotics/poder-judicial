/**
* Nombre del Programa : IntervinienteDAOImplTest.java
* Autor                            : LuisMG
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 10/10/2011
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
package mx.gob.segob.nsjp.dao.test.audiencia.impl;


import mx.gob.segob.nsjp.dao.audiencia.IntervinienteDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Interviniente;
import mx.gob.segob.nsjp.model.Involucrado;



/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author LuisMG
 *
 */
public class IntervinienteDAOImplTest extends BaseTestPersistencia<IntervinienteDAO>{
	
	public void testRegistrarInterviniente(){
		Interviniente interviniente = new Interviniente();
		
		interviniente.setEsAceptado(true);
		interviniente.setEsParteFiscalia(true);
		interviniente.setEsPresente(true);
		interviniente.setEsSuperVeniente(true);
		interviniente.setEsTitular(true);

		// Los objetos
		interviniente.setFuncionario(new Funcionario(1L));
		interviniente.setInvolucrado(new Involucrado(1L));
		interviniente.setAudiencia(new Audiencia (1L));
		Long idInterviniente = daoServcice.create(interviniente);
		logger.info(" Interviniente Registrado "+ idInterviniente);

		
//		Long idDatoPrueba = daoServcice.create(datoPrueba);
//		logger.info(" Dato Prueba Registrado "+ idDatoPrueba);
	}
	
	public void tesConsultarInterviniente(){
		Long idInterviniente = 1L;
		
		Interviniente interviniente = daoServcice.read(idInterviniente);
		
		logger.info(interviniente);
		if(interviniente!= null){
			
			
			
			logger.info(" interviniente - IntervinienteId:"+interviniente.getIntervinienteId());
			logger.info(" interviniente - EsAceptado:"+interviniente.getEsAceptado());
			logger.info(" interviniente - EsParteFiscalía:"+interviniente.getEsParteFiscalia());
			logger.info(" interviniente - EsPresente:"+interviniente.getEsPresente());
			logger.info(" interviniente - EsSuperVeniente:"+interviniente.getEsSuperVeniente());
			logger.info(" interviniente - EsTitular:"+interviniente.getEsTitular());
// Los objetos
			logger.info(" interviniente - Funcionario:"+interviniente.getFuncionario().getClaveFuncionario());
			logger.info(" interviniente - Involucrado:"+interviniente.getInvolucrado().getElementoId());
			logger.info(" interviniente - Audiencia:"+interviniente.getAudiencia().getAudienciaId());

				
			}
	}
	
}
