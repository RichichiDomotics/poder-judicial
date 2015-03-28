/**
 * Nombre del Programa :ActualizarNotasExpedienteServiceImplTest.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 07-jul-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.test.expediente.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NotaExpedienteDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.expediente.NotaExpedienteDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.NotaExpediente;
import mx.gob.segob.nsjp.service.expediente.RegistarNotasExpedienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class RegistarNotasExpedienteServiceImplTest
    extends BaseTestServicios<RegistarNotasExpedienteService> {
	
	@Autowired
	private NotaExpedienteDAO notaExpedienteDAO;

    public void testGuardarNotasService(){
        try {
            logger.info("Probando el servicio de: ActualizarNotasExpedienteService");
    		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
    		expedienteDTO.setNumeroExpedienteId(762L);    		
    		List<NotaExpedienteDTO> loNotas = this.generarNotas(5);
    		System.out.println("El total de Notas generadas es:" + loNotas.size());
            service.registrarActualizarNotasExpediente(loNotas, expedienteDTO);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ActualizarNotaService");
        }
    }
    
    public void testActualizarNotasService(){
        try {
            logger.info("Probando el servicio de: ActualizarNotasExpedienteService");
    		ExpedienteDTO expedienteDTO = new ExpedienteDTO();
    		expedienteDTO.setNumeroExpedienteId(1L);    		
    		List<NotaExpedienteDTO> loNotas = this.actualizarNotas(5);
    		System.out.println("El total de Notas generadas es:" + loNotas.size());
            service.registrarActualizarNotasExpediente(loNotas, expedienteDTO);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ActualizarNotaService");
        }
    }
    
     
    
    public List<NotaExpedienteDTO> generarNotas(Integer numDeNotas){
    	List<NotaExpedienteDTO> notas = new ArrayList<NotaExpedienteDTO>();
    	for (Long i = 1L; i <= numDeNotas; i++) {
    		NotaExpedienteDTO loNota = new NotaExpedienteDTO();
    		loNota.setNombreNota("Nombre de nota" + i);
    		loNota.setDescripcion("descripcion" + i);
    		notas.add(loNota);
		}
    	System.out.println();
    	return notas;    	
    }
    
    public List<NotaExpedienteDTO> actualizarNotas(Integer numDeNotas){
    	List<NotaExpedienteDTO> notas = new ArrayList<NotaExpedienteDTO>();
    	for (Long i = 1L; i <= numDeNotas; i++) {
    		NotaExpedienteDTO loNota = new NotaExpedienteDTO();
    		//loNota.setIdNota(39 + i);
    		loNota.setNombreNota("Nuevo nombre" + i);
    		loNota.setDescripcion("Nueva descripcion" + i);
    		notas.add(loNota);
		}
    	System.out.println();
    	return notas;    	
    }
    
    
    public void _testGuardaNota(){
  		NotaExpediente loNota = new NotaExpediente();
		loNota.setExpediente(new Expediente(1L));
		loNota.setFechaCreacion(new Date());
		loNota.setNombreNota("nombre");
		notaExpedienteDAO.create(loNota);
    }
    
	
}
