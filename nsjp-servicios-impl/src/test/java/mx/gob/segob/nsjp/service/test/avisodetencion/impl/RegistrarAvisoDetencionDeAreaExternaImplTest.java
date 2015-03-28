/**
* Nombre del Programa : RegistrarAvisoDetencionDeAreaExternaImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 01/07/2011
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
package mx.gob.segob.nsjp.service.test.avisodetencion.impl;

import java.util.ArrayList;
import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.detencion.DetencionWSDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoDetencionWSDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoWSDTO;
import mx.gob.segob.nsjp.service.avisodetencion.RegistrarAvisoDetencionDeAreaExterna;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para el registro de un aviso de detención de
 * área externa
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class RegistrarAvisoDetencionDeAreaExternaImplTest extends BaseTestServicios<RegistrarAvisoDetencionDeAreaExterna>{
	
	public void testRegistrarAvisoDetencion(){
		
		AvisoDetencionWSDTO aviso = new AvisoDetencionWSDTO();
		aviso.setDetencion(new DetencionWSDTO());
		String consec = "1005";
		String nombre = "Naurto";
		String apater = "Uzumaki";
		String amater = "Jinchuruki";
		
		aviso.setFolioNotificacion("PG/2011"+consec);
		aviso.setNumeroCasoAsociado("YUC/PG/XX/PGE/2011/AA-"+consec);
		aviso.getDetencion().setDetenido(nombre+" "+apater+" "+amater);
		aviso.getDetencion().setNombreDetenido(nombre);
		aviso.getDetencion().setApellidoPaternoDetenido(apater);
		aviso.getDetencion().setApellidoMaternoDetenido(amater);
		
		aviso.setEstatusNotificacionId(EstatusNotificacion.NO_ATENDIDA.getValorId());
		aviso.getDetencion().setFechaFinDetencion(new Date());
		aviso.getDetencion().setFechaInicioDetencion(new Date());
		aviso.getDetencion().setFechaRecepcionDetecion(new Date());
		aviso.getDetencion().setLugarDetencion("Periferico sur 4121");
		aviso.getDetencion().setLugarDetencionProvisional("Agencia del ministerio público 34");
		aviso.getDetencion().setMotivoDetencion("Participación en hechos");
		aviso.getDetencion().setObservaciones("Probable responsable de delito principal");
		aviso.setDelitos(new ArrayList<DelitoWSDTO>());
		DelitoWSDTO delito = new DelitoWSDTO();
		delito.setIdCatDelito(new Long(4));
		aviso.getDelitos().add(delito);
		
		try {
			Long idFuncionarioSolicitante = 8L;
			aviso = service.registrarAvisoDetencion(aviso,null,null,idFuncionarioSolicitante);
			assertNotNull(aviso.getAvisoDetencionId());
		} catch (NSJPNegocioException e) {
			logger.error(e);
			assertNotNull("Se generó excepción de negosio",null);
		}
		
	}

}
