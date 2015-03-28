/**
 * Nombre del Programa : SolicitudTranscricpionAudienciaDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 7 Jul 2011
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudTranscricpionAudienciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.SolicitudTranscripcionAudiencia;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class SolicitudTranscricpionAudienciaDAOImplTest
        extends
            BaseTestPersistencia<SolicitudTranscricpionAudienciaDAO> {

    public void testCrear() {
        SolicitudTranscripcionAudiencia pojo = new SolicitudTranscripcionAudiencia();
        
        pojo.setTipoDocumento(new Valor(1L));
        pojo.setForma(new Forma(1L));
        pojo.setNombreDocumento("Solicitud Defensor");
        pojo.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        pojo.setFolioSolicitud("PG/201100024");

        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.RECURSO_APELACION.getValorId()));
        pojo.setFechaCreacion(new Date());
        pojo.setNombreSolicitante("Juanito Perez Canutu");
        pojo.setAudiencia(new Audiencia(2L));
        pojo.setConfInstitucion(new ConfInstitucion(Long.valueOf(Instituciones.PJ.ordinal())));
        
        daoServcice.create(pojo);
    }
    
    public void testconsultarSolicitudTranscripcionAudienciaPorTipoYEstatus(){
    	List<SolicitudTranscripcionAudiencia> pojoList = new ArrayList<SolicitudTranscripcionAudiencia>();
//    	pojoList = daoServcice.consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(1L, TiposSolicitudes.LIBERTAD.getValorId(), EstatusSolicitud.ABIERTA.getValorId());
    	pojoList = daoServcice.consultarSolicitudTranscripcionAudienciaPorTipoYEstatus(TiposSolicitudes.TRANSCRIPCION_DE_AUDIENCIA.getValorId(), EstatusSolicitud.ABIERTA.getValorId(),0L,0L, new Date(), null);

    	logger.debug("Solicitudes encontradas: " + pojoList.size());
    	for(SolicitudTranscripcionAudiencia row:pojoList)
    	{
    		logger.debug("Id de Solicitud: " + row.getDocumentoId());
    		logger.debug("Id de Audiencia: " + row.getAudiencia().getAudienciaId());
    	}
    }
}
