/**
* Nombre del Programa : ProcuraduriaClienteServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 07/09/2011
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
package mx.gob.segob.nsjp.service.test.infra;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudAudienciaDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.infra.ProcuraduriaClienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase para Pruebas unitarias en la invocación de WS.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ProcuraduriaClienteServiceImplTest 
	extends BaseTestServicios<ProcuraduriaClienteService> {
	
	public void testEnviarInformePolicialHomologado(){
		logger.info("Probando el servicio de: ProcuraduriaClienteService");
		
		Long informePolicialHomologadoId = 10L;
		Long idAgencia = 6L; //Representa el id de la Agencia(Fiscalia) a la cual se enviara el IPH 
		
		InformePolicialHomologadoDTO iph = new InformePolicialHomologadoDTO();
		iph.setInformePolicialHomologadoId(informePolicialHomologadoId );

		try{
			InformePolicialHomologadoDTO iphRegreso = service.enviarInformePolicialHomologado(iph, idAgencia);
			assertNotNull("enviarInformePolicialHomologado", iphRegreso);
			logger.info("iphRegreso:"+ iphRegreso);
		} catch (NSJPNegocioException ex) {
			fail("Ocurrio una excepcion al ejecutar el test EnviarInformePolicialHomologado");
		}
	}
	
	public void testSolicitarCopiaCarpetaDeInvestigacion(){
		try{
			SolicitudDTO solicitud = new SolicitudDTO();
			solicitud.setTipoSolicitudDTO(new ValorDTO(TiposSolicitudes.CARPETA_DE_INVESTIGACION.getValorId()));
			//solicitud.setNumeroCasoAsociado("YUC/FG/XX/PGU/2011/AA-01163");
			solicitud.setFolioSolicitud("DE/201200009");
			solicitud.setNombreSolicitante("NOMBRE SOLICITANTE");
			solicitud.setNombreSolicitanteExternoInterno("SOLICITANTE EXTERNO");
			solicitud.setInvolucradoDTO(new InvolucradoDTO(1073L));			

			SolicitudDTO iphRegreso = service.solicitarCopiaCarpetaDeInvestigacion(solicitud, 1L);
			assertNotNull("solicitarCopiaCarpetaDeInvestigacion", iphRegreso);
			logger.info("iphRegreso:"+ iphRegreso);
		} catch (NSJPNegocioException ex) {
			fail("Ocurrio una excepcion al ejecutar el test EnviarInformePolicialHomologado");
		}
	}

}
