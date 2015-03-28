/**
 * Nombre del Programa : SolicitudAudienciaDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudAudienciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.Caso;
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
 * @author vaguirre
 * 
 */
public class SolicitudAudienciaDAOImplTest
        extends
            BaseTestPersistencia<SolicitudAudienciaDAO> {

    public void testCreateAbierta() {

        final SolicitudAudiencia pojo = new SolicitudAudiencia();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(3L));
        pojo.setFechaLimite(new Date());
        pojo.setForma(new Forma(4L));
        pojo.setAudiencia(new Audiencia(9L)); 
        pojo.setNombreDocumento("Solicitud audiencia");
        pojo.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.AUDIENCIA.getValorId()));
        pojo.setFolioSolicitud("PJ/"+Calendar.getInstance().get(Calendar.YEAR)+""+Calendar.getInstance().getTimeInMillis()%100000);
        daoServcice.create(pojo);

    }
    
    public void testCreateAtendida() {

        final SolicitudAudiencia pojo = new SolicitudAudiencia();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(4L));
        pojo.setFechaLimite(new Date());
        pojo.setForma(new Forma(4L));
        pojo.setAudiencia(new Audiencia(4L));
        pojo.setNombreDocumento("Solicitud audiencia");
        pojo.setEstatus(new Valor(EstatusSolicitud.CERRADA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.AUDIENCIA.getValorId()));
        pojo.setFolioSolicitud("PJ/"+Calendar.getInstance().get(Calendar.YEAR)+""+Calendar.getInstance().getTimeInMillis()%100000);
        daoServcice.create(pojo);

    }

    public void testConsultarSolicitudesAudienciaPendientes() {
    	List<SolicitudAudiencia> respuesta = daoServcice.consultarSolicitudesAudienciaPendientes(26L);
    	
    	assertTrue("La lista debe regresar minimo un registro ",respuesta.size()>0);
    	for (SolicitudAudiencia solicitudAudiencia : respuesta) {
			logger.info("----------------------------");
			logger.info("Sol Audiencia ID "+solicitudAudiencia.getDocumentoId());
			logger.info("Nombre solAud "+solicitudAudiencia.getNombreDocumento());
			logger.info("----------------------------");
		}
    }
    
    public void testConsultarOtrasSolicitudesPendientes() {
        List<Solicitud> data = daoServcice
                .consultarOtrasSolicitudesPendientes();

        logger.debug(data);
        logger.debug("data.size() :: " + data.size());
        logger.debug("id :: " + data.get(0).getDocumentoId());
    }
    
    public void testConsultarSolicitudesPorTipoEstado(){
    	
    	List<SolicitudAudiencia>  solicitudes = 
    		daoServcice.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA, EstatusSolicitud.ABIERTA);
    	assertFalse("Debe de haber almenos un dato en la lista", solicitudes.isEmpty());
    	logger.debug("Total :: " + solicitudes.size());
    	for (SolicitudAudiencia solicitudAudiencia : solicitudes) {
    		logger.debug("Folio:: " + solicitudAudiencia.getFolioSolicitud());
		}
    	logger.debug("Total :: " + solicitudes.size());
//    		daoServcice.consultarSolicitudesAudienciaPorTipoyEstado(TiposSolicitudes.TRASLADO_DE_IMPUTADO, EstatusSolicitud.ABIERTA);
    	
    }

    
	public void testConsultarSolicitudesConCriterios(){
		SolicitudAudiencia solicitudIn = new SolicitudAudiencia();
		Expediente expediente = new Expediente();
		Caso caso = new Caso();
		caso.setNumeroGeneralCaso("YUC/FG/XX/PGU/2012/AA-01366");
		expediente.setCaso(caso);
		NumeroExpediente numeroExpediente = new NumeroExpediente();
		numeroExpediente.setExpediente(expediente);
		solicitudIn.setNumeroExpediente(numeroExpediente);

		Long[] idES= {1775L};//{1775L, 1776L, 1777L, 1778L, 1779L, 2096L};
		Long[] idTS= {1771L};//1714L, 1715L,1718L, 1771L, 1772L,1774L, 1767L, 1766L, 2114L };
		Long[] idEA= {2019L};//{1775L, 1776L, 1777L, 1778L, 1779L, 2096L};
		Long[] idTA= {2774L};//
		
		List<Long> lstIdEstatusSolicitud= Arrays.asList(idES);
		List<Long> lstIdTipoSolicitud = Arrays.asList(idTS);
		List<Long> lstIdEstatusAudiencia= Arrays.asList(idEA);
		List<Long> lstIdTipoAudiencia = Arrays.asList(idTA);
		String tipoConsulta = Solicitud.NUMEROCASOASOCIADO;
			
		List<SolicitudAudiencia> solicitudes = null;
		try {
			solicitudes = daoServcice.consultarSolicitudesAudienciaConCriterios(solicitudIn, lstIdEstatusSolicitud, lstIdTipoSolicitud,lstIdEstatusAudiencia, lstIdTipoAudiencia, tipoConsulta);
		} catch (NSJPNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue("No se regresaron elementos", !solicitudes.isEmpty());
		logger.info("Solicitudes:"+ solicitudes.size());
		
	}    
    
    
}
