/**
 * Nombre del Programa : PJClienteServiceImplTest.java
 * Autor                            : rgama
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-Sep-2011
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
package mx.gob.segob.nsjp.service.test.infra;
		

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.audiencia.EstatusAudiencia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.audiencia.AudienciaDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudTranscripcionAudienciaDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.infra.PJClienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author rgama
 */
public class PJClienteServiceImplTest
    extends BaseTestServicios<PJClienteService> {
	
	@Autowired
	private DocumentoDAO documentoDAO;

    public void testPJClienteService(){
        try {
            logger.info("Probando el servicio de: enviarSolicitudTranscripcion");
            assert service != null;
            SolicitudTranscripcionAudienciaDTO input = new SolicitudTranscripcionAudienciaDTO();
            //Tipo de Solicitud
            input.setTipoSolicitudDTO(new ValorDTO(110L));
            //Nombre solicitante
            input.setNombreSolicitante("Ricardo Gama Garcia.");
            //Audiencia ID
            input.setAudiencia(new AudienciaDTO(7L));
            //Fecha de creacion
            input.setFechaCreacion(new Date());
            
            SolicitudTranscripcionAudienciaDTO enviarSolicitud = service.enviarSolicitudTranscripcion(input);
            assertNotNull("enviarSolicitud", enviarSolicitud);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test PJClienteService");
        }
    }
   
    public void testEnviarDocumentoIncumplimientoMedidaPJ(){
    	
    	DocumentoDTO documentoDTO = new DocumentoDTO();
		String numeroCausa = "NSJYUCPJ2011263333W";
		String numeroCarpetaEjecucion = null;
		
		try {
			Documento documento = documentoDAO.consultarDocumentoPorId(147L);
			documentoDTO = DocumentoTransformer.transformarDocumento(documento);
			if(documento.getArchivoDigital()!= null){
				ArchivoDigitalDTO arDGDTO = ArchivoDigitalTransformer.transformarArchivoDigital(documento.getArchivoDigital());
				documentoDTO.setArchivoDigital(arDGDTO);
			}
			logger.info("Documento a Enviar: "+ documentoDTO);
			
			service.enviarDocumentoIncumplimientoMedidaPJ(documentoDTO, numeroCausa,numeroCarpetaEjecucion);
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
			fail("Ocurrio una excepcion al ejecutar el test PJClienteService - testEnviarDocumentoIncumplimientoMedidaPJ");
		} 
    }
    
    public void testConsultarAudienciasByFechasYEstatusPJ(){
    	
		Calendar calendario = Calendar.getInstance();
    	AudienciaDTO audiencia = new AudienciaDTO();
		
    	audiencia.setEstatusAudiencia(new ValorDTO(EstatusAudiencia.PROGRAMADA.getValorId()));
    	audiencia.setFechaFiltroInicio(calendario.getTime());
		audiencia.setFechaFiltroFin(calendario.getTime());
		
		try {						
			List<AudienciaDTO> lista = service.consultarAudienciasByFechasYEstatus(audiencia);
			logger.debug("El tamaño de la lista es: " + lista.size());
			
		} catch (NSJPNegocioException e) {
			logger.debug(e.getMessage(), e);
		} 
    }
   
}
