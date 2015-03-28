/**
* Nombre del Programa : ConsultarInformePolicialHomologadoServiceImplTest.java
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
package mx.gob.segob.nsjp.service.test.informepolicial;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.OperativoDTO;
import mx.gob.segob.nsjp.service.informepolicial.ConsultarInformePolicialHomologadoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;
import mx.gob.segob.nsjp.services.dtos.print.ExpedientePrintIPH;

/**
 * Pruebas Unitarias para consultar el IPH completo
 * @version 1.0
 * @author GustavoBP
 *
 */
public class ConsultarInformePolicialHomologadoServiceImplTest   extends BaseTestServicios<ConsultarInformePolicialHomologadoService> {

	public void testConsultarInformePolicialHomologadoPorFolio(){
		
//		Long folioIPH = 2011000009L;
		Long folioIPH = 2011000079L;
		try {
			InformePolicialHomologadoDTO iphDTO = service.consultarInformePolicialHomologadoPorFolio(folioIPH );
			assertNotNull("InformePolicialHomologadoDTO", iphDTO);
			logger.info(" IPHDTO - InformePolicialHomologadoId:" + iphDTO.getInformePolicialHomologadoId());
			logger.info(" IPHDTO - FolioIPH:" + iphDTO.getFolioIPH());
			logger.info(" IPHDTO - Expediente:" + iphDTO.getExpediente());
			if(iphDTO.getExpediente()!= null){
				logger.info(" IPHDTO - :" + iphDTO.getExpediente().getExpedienteId());
				logger.info(" IPHDTO - :" + iphDTO.getExpediente().getNumeroExpediente());
				logger.info(" IPHDTO - :" + iphDTO.getExpediente().getInvolucradosDTO());
				if(iphDTO.getExpediente().getInvolucradosDTO()!= null)
					logger.info(" IPHDTO - :" + iphDTO.getExpediente().getInvolucradosDTO().size());
				ExpedientePrintIPH.imprimirDatosExpediente(iphDTO.getExpediente());
			}
			logger.info(" IPHDTO - Operativo: " + iphDTO.getOperativo());
			if(iphDTO.getOperativo()!= null){
				OperativoDTO operativoDTO = iphDTO.getOperativo();
				logger.info(" IPHDTO - OperativoDTO - Id: " + operativoDTO.getOperativoId());
				logger.info(" IPHDTO - OperativoDTO - Nombre: " + operativoDTO.getNombre());
				logger.info(" IPHDTO - OperativoDTO - NombreComte: " + operativoDTO.getNombreComte());
				logger.info(" IPHDTO - OperativoDTO - NombreComteAgrupto: " + operativoDTO.getNombreComteAgrupto());
			}
			if(iphDTO.getExpediente()!=null){
				ExpedienteDTO expDTO =iphDTO.getExpediente();
				logger.info(" ExpedienteDTO - : " + expDTO.getExpedienteId());
				logger.info(" ExpedienteDTO - : " + expDTO.getDocumentosDTO());
				if(expDTO.getDocumentosDTO()!=null){
					logger.info(" ExpedienteDTO - Documentos: " + expDTO.getDocumentosDTO().size());
					for (DocumentoDTO documentoDTO : expDTO.getDocumentosDTO()) {
						logger.info(" DocumentoDTO - : " + documentoDTO.getDocumentoId());
						logger.info(" DocumentoDTO - Actividad: " + documentoDTO.getActividadDTO());
					}
				}
				
			}
		} catch (NSJPNegocioException e) {
			fail("Ocurrio una excepcion al ejecutar el test consultarConsultarInformePolicialHomologadoPorFolio");
		}
		
	}
}
