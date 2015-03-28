/**
 * Nombre del Programa : SolicitudDAOImplTest.java
 * Autor                            : adrian
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 27/06/2011
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
package mx.gob.segob.nsjp.dao.test.solicitud.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.impl.SolicitudDAOImpl;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Caso;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Solicitud;
import mx.gob.segob.nsjp.model.SolicitudAudiencia;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author adrian
 * 
 */
public class SolicitudDAOImplTest extends
		BaseTestPersistencia<SolicitudDAOImpl> {



	/**
	 * Test method for
	 * {@link mx.gob.segob.nsjp.dao.solicitud.impl.SolicitudDAOImpl#consultarSolicitudXEstatus(int, mx.gob.segob.nsjp.model.Usuario, long)}
	 * .
	 */
	public void testConsultarSolicitudXEstatus() {
		/* Datos de entrada */
		EstatusSolicitud estatusSolicitud = EstatusSolicitud.ABIERTA;
		TiposSolicitudes tipoSolicitud = TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA;

		List<SolicitudAudiencia> solicitudes = daoServcice.consultarSolicitudAudienciaXEstatus(estatusSolicitud.getValorId(), tipoSolicitud.getValorId());

		logger.info("Existen " + solicitudes.size() + "formas");
		for (SolicitudAudiencia sol : solicitudes) {
			logger.info("-----------------------------------------------------------");
			logger.info("Identificador de documento: " + sol.getDocumentoId());
			logger.info("Nombre del solicitante: " + sol.getNombreSolicitante());
			logger.info("Número de caso: " + sol.getNumeroCasoAsociado());
			logger.info("Fecha Inicio Prestamo: " + sol.getFechaModificacion());
			logger.info("Fecha Fin Prestamo: " + sol.getFechaCierre());
			logger.info("Fecha Límite: " + sol.getFechaLimite());
			logger.info("Cadena de custodia" + sol.getCadenaCustodia());
			//logger.info("Acuse de recibo: " + sol.getAcuseRecibos().size());
			
			
			if (sol.getExpediente() != null){
				logger.info("Existe Expediente");
				logger.info("Expediente ID: "+sol.getExpediente().getExpedienteId());
				logger.info("Numero Expediente ID e aud: "+sol.getAudiencia().getNumeroExpediente().getNumeroExpedienteId());
			
				logger.info("Número de expediente: "
						+ sol.getExpediente().getNumeroExpediente());
			}
		}
	}
	
	
	public void testEliminarSolicictud(){
		Solicitud s = daoServcice.read(43L);
		daoServcice.delete(s);
		
//		s = daoServcice.read(45L);
//		daoServcice.delete(s);
	}
	
	public void testCreateTranscripcion() {
	    Solicitud pojo = new Solicitud();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(1L));
        pojo.setForma(new Forma(1L));
        pojo.setNombreDocumento("Solicitud Defensor");
        pojo.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.RECURSO_APELACION.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(11L));
        pojo.setConfInstitucion(new ConfInstitucion(Long.valueOf(Instituciones.PJ.ordinal())));
	    daoServcice.create(pojo);
	}

	public void testConsultarNumeroExpediente(){
		
		logger.debug(daoServcice.consultarNumeroExpedienteDeSolicitud(27L));
		
	}


	public void testConsultarSolicitudPorFolio(){
		String folioSolicitud = "CD/199900006";
		
		Solicitud solicitud;
		solicitud = daoServcice.consultarSolicitudPorFolio(folioSolicitud);
		if (solicitud!=null){
			logger.info(" Solicitud :"+solicitud);
			logger.info(" Solicitud :"+solicitud.getDocumentoId());
		}
	}
	
	public void testConsultarExpedienteDeNumeroExpedienteSolicitud(){
		Long idSolicitud = 37L;
		
		Expediente expediente;
		expediente = daoServcice.consultarExpedienteDeNumeroExpedienteSolicitud(idSolicitud);
		if (expediente!=null){
			logger.info(" Expediente :"+expediente);
			logger.info(" Expediente :"+expediente.getExpedienteId());
		}
	}

	public void testConsultarSolicitudPorDocumentoId(){
		Solicitud resp = daoServcice.consultarSolicitudPorDocumentoId(49L);
		assertNotNull(resp);
		logger.info("Causa: "+resp.getExpediente().getCaso().getCasoId());
		logger.info("Solicitante: "+resp.getNombreSolicitante());
		logger.info("SolicitanteExt: "+resp.getSolicitanteExterno());
		logger.info("Estatus: "+resp.getEstatus().getValor());
		logger.info("Fecha Sol: "+resp.getFechaCreacion());
		logger.info("Fecha Entrega: "+resp.getFechaCierre());
		logger.info("Expediente: "+resp.getExpediente().getExpedienteId());
		logger.info("Num Exp: "+resp.getExpediente().getNumeroExpedienteId()+" ("+resp.getExpediente().getNumeroExpediente()+")");
	}
	
	
	public void testConsultarSolicitudesGeneradas(){
		
		Long[] idES= {};//{1775L, 1776L, 1777L, 1778L, 1779L, 2096L};
		Long[] idTS= {};//1714L, 1715L,1718L, 1771L, 1772L,1774L, 1767L, 1766L, 2114L };
		List<Long> idEstatusSolicitud= Arrays.asList(idES);
		List<Long> idTipoSolicitud = Arrays.asList(idTS);
		Long idAreaOrigen = null;//1L;
		Long idFuncionarioSolicitante  = null;//5L; 
			
		logger.info("Lista idEstatusSolicitud:"+ idEstatusSolicitud);
		logger.info("Lista idTipoSolicitud:"+ idTipoSolicitud);
		logger.info("Institucion:"+ idAreaOrigen);
		
		List<Solicitud> solicitudes = daoServcice.consultarSolicitudesGeneradas(idEstatusSolicitud, idTipoSolicitud, idAreaOrigen, idFuncionarioSolicitante);
		assertTrue("No se regresaron elementos", !solicitudes.isEmpty());
		logger.info("Solicitudes:"+ solicitudes.size());
		for (Solicitud solicitud : solicitudes) {
			imprimirSolicitud(solicitud);
		}
	}
	
public void testConsultarSolicitudesGeneradasPorNumeroExpediente(){
		
		Long[] idES= {};//{1775L, 1776L, 1777L, 1778L, 1779L, 2096L};
		Long[] idTS= {};//1714L, 1715L,1718L, 1771L, 1772L,1774L, 1767L, 1766L, 2114L };
		List<Long> idEstatusSolicitud= Arrays.asList(idES);
		List<Long> idTipoSolicitud = Arrays.asList(idTS);
		
		Long idAreaOrigen = 44L;
		Long idFuncionarioSolicitante  = null; //3L; 
		String numeroExpediente  = "NSJYUCPG20104495";
			
		logger.info("Lista idEstatusSolicitud:"+ idEstatusSolicitud);
		logger.info("idTipoSolicitud:"+ idTipoSolicitud);
		logger.info("Institucion:"+ idAreaOrigen);
		
		List<Solicitud> solicitudes = daoServcice
				.consultarSolicitudesGeneradasPorNumeroExpediente(
						idEstatusSolicitud, idTipoSolicitud, idAreaOrigen,
						idFuncionarioSolicitante, numeroExpediente);
		
		assertTrue("No se regresaron elementos", !solicitudes.isEmpty());
		for (Solicitud solicitud : solicitudes) {
			imprimirSolicitud(solicitud);
		}
		logger.info("Solicitudes:"+ solicitudes.size());
	}

	public void testConsultarSolicitudesParaAtender(){
		
		Long[] idES= {};//{1775L, 1776L, 1777L, 1778L, 1779L, 2096L};
		Long[] idTS= {};//1714L, 1715L,1718L, 1771L, 1772L,1774L, 1767L, 1766L, 2114L };
		List<Long> idEstatusSolicitud= Arrays.asList(idES);
		List<Long> idTipoSolicitud = Arrays.asList(idTS);
		Long idAreaDestino = null; //Instituciones.PGJ.getValorId();
		Long idFuncionarioDestinatario  = 5L; 
		Long idAgencia = 5L;
			
		logger.info("Lista idEstatusSolicitud:"+ idEstatusSolicitud);
		logger.info("Lista idTipoSolicitud:"+ idTipoSolicitud);
		logger.info("Institucion:"+ idAreaDestino);
		
		List<Solicitud> solicitudes = daoServcice.consultarSolicitudesParaAtender(idEstatusSolicitud, idTipoSolicitud, idAreaDestino, idFuncionarioDestinatario,idAgencia);
		assertTrue("No se regresaron elementos", !solicitudes.isEmpty());
		logger.info("Solicitudes:"+ solicitudes.size());
		for (Solicitud solicitud : solicitudes) {
			imprimirSolicitud(solicitud);
		}
	}

	public void testConsultarSolicitudesConCriterios(){
		Solicitud solicitudIn = new Solicitud();
		Expediente expediente = new Expediente();
		Caso caso = new Caso();
		caso.setNumeroGeneralCaso("YUC/PG/XX/PGE/2011/AA-00105");
		expediente.setCaso(caso);
		NumeroExpediente numeroExpediente = new NumeroExpediente();
		numeroExpediente.setExpediente(expediente);
		solicitudIn.setNumeroExpediente(numeroExpediente);

		Long[] idES= {1775L};//{1775L, 1776L, 1777L, 1778L, 1779L, 2096L};
		Long[] idTS= {1771L};//1714L, 1715L,1718L, 1771L, 1772L,1774L, 1767L, 1766L, 2114L };
		List<Long> lstIdEstatusSolicitud= Arrays.asList(idES);
		List<Long> lstIdTipoSolicitud = Arrays.asList(idTS);
		String tipoConsulta = Solicitud.NUMEROCASOASOCIADO;
			
		List<Solicitud> solicitudes = null;
		try {
			solicitudes = daoServcice.consultarSolicitudesConCriterios(solicitudIn, lstIdEstatusSolicitud, lstIdTipoSolicitud, tipoConsulta);
		} catch (NSJPNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("No se regresaron elementos", !solicitudes.isEmpty());
		logger.info("Solicitudes:"+ solicitudes.size());
		for (Solicitud solicitud : solicitudes) {
			imprimirSolicitud(solicitud);
		}
	}
	
	public void imprimirSolicitud(Solicitud solicitud){
		logger.info("*********************************************");
		logger.info("Solicitud - getDocumentoId:"+ solicitud.getDocumentoId());
		logger.info("Solicitud - getNumeroCasoAsociado:"+ solicitud.getNumeroCasoAsociado());
		logger.info("Solicitud - getMotivo:"+ solicitud.getMotivo());
		logger.info("Solicitud - getExpediente:"+ solicitud.getExpediente());
		
		if(solicitud.getExpediente()!= null){
			Expediente expediente = solicitud.getExpediente();
			logger.info("expediente - getExpedienteId:"+ expediente.getExpedienteId());
			logger.info("expediente - getNumeroExpediente:"+ expediente.getNumeroExpediente());
		}
		logger.info("Solicitud - getFolioSolicitud:"+ solicitud.getFolioSolicitud());
		logger.info("Solicitud - getEstatus:"+ solicitud.getEstatus());
		
		if(solicitud.getEstatus()!= null)
			logger.info("Solicitud - getEstatus:"+ solicitud.getEstatus().getValorId());
		logger.info("Solicitud - getFechaCreacion:"+ solicitud.getFechaCreacion());
		logger.info("Solicitud - getFechaLimite:"+ solicitud.getFechaLimite());
		logger.info("Solicitud - getConfInstitucion:"+ solicitud.getConfInstitucion());

		
		if(solicitud.getConfInstitucion()!= null)
			logger.info("Solicitud - getConfInstitucion:"+ solicitud.getConfInstitucion().getNombreInst());
		
		if(solicitud.getDestinatario()!= null){
			logger.info("getDestinatario:"+ solicitud.getDestinatario().getClaveFuncionario());
			logger.info("getDestinatario:"+ solicitud.getDestinatario().getNombreCompleto());
		}
			
		if(solicitud.getFuncionarioSolicitante()!= null){
			logger.info("Solicitud - FuncionarioSolicitante:"+ solicitud.getFuncionarioSolicitante().getClaveFuncionario());
			logger.info("Solicitud - FuncionarioSolicitante:"+ solicitud.getFuncionarioSolicitante().getNombreCompleto());
		}
		logger.info("Solicitud - AreaOrigen:"+ solicitud.getAreaOrigen());
		logger.info("Solicitud - AreaDestino:"+ solicitud.getAreaDestino());
		
	}

}

