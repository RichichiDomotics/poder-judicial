/**
* Nombre del Programa : AsignarEvidenciaServiceImplTest.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20/07/2011
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
package mx.gob.segob.nsjp.service.test.evidencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.funcionario.Especialidades;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.service.evidencia.AsignarEvidenciaService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase de pruebas unitarias para la asignación de evidencias a peritos
 * @version 1.0
 * @author Emigdio
 *
 */
public class AsignarEvidenciaServiceImplTest extends BaseTestServicios<AsignarEvidenciaService> {
	/**
	 * Prueba la asignación de una evidencia a un perito en una solicitud pericial inicial
	 */
	public void testAsignarEvidenciaAPerito(){
		
		try {
			service.asignarEvidenciaASolicitudPericial(new EvidenciaDTO(1L), 
					new SolicitudPericialDTO(50L), new FuncionarioDTO(2L));
			logger.debug("Evidencia asignada");
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertNotNull(null);
		}
		
		
	}
	
	public void testAsignarEvidenciaSolicitud() throws NSJPNegocioException,Exception
	{
		List<EvidenciaDTO> evidencias = new ArrayList<EvidenciaDTO>();
		EvidenciaDTO e = new EvidenciaDTO();
		EvidenciaDTO e2 = new EvidenciaDTO();
		EvidenciaDTO e3 = new EvidenciaDTO();
		
		e.setEvidenciaId(1L);
		evidencias.add(e);
		e2.setEvidenciaId(2L);
		evidencias.add(e2);
		e3.setEvidenciaId(3L);
		evidencias.add(e3);
		
		FuncionarioDTO fSol = new FuncionarioDTO();
		fSol.setClaveFuncionario(37L);
		FuncionarioDTO fDest = new FuncionarioDTO();
		fDest.setClaveFuncionario(2L);
		
		SolicitudPericialDTO s = new SolicitudPericialDTO();
		s.setDestinatario(fDest);
		s.setUsuarioSolicitante(fSol);
		s.setEspecialidad(new ValorDTO(Especialidades.JUEZ_AUDIENCIA.getValorId()));
		s.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.DICTAMEN.getValorId()));	
		ExpedienteDTO exp = new ExpedienteDTO();
		exp.setNumeroExpedienteId(179L);
		s.setExpedienteDTO(exp);
		service.asignarEvidenciaASolicitudPericial(evidencias, s);
		
	}

}
