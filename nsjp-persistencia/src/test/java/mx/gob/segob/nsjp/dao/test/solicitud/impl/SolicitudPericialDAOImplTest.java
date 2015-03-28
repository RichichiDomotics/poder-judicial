/**
 * Nombre del Programa : SolicitudPericialDAOImplTest.java
 * Autor                            : cesar
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 06 Jun 2011
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.funcionario.Especialidades;
import mx.gob.segob.nsjp.comun.enums.funcionario.Puestos;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author rgama
 * 
 */
public class SolicitudPericialDAOImplTest
        extends
            BaseTestPersistencia<SolicitudPericialDAO> {

    public static final long TIPO_SOLICITUD_ASESORIA = 1705L;
    public static final long TIPO_SOLICITUD_DICTAMEN = 1706L;

    // public void testConsultarSolicitudesPericialesDeDictamenNuevas() {
    // List<SolicitudPericial> loSolicitudesPericiales =
    // daoServcice.consultarSolicitudesPericiales(TIPO_SOLICITUD_DICTAMEN,Solicitudes.NUEVA.getNombre(),Solicitudes.ABOGADO_DEFENSOR.getNombre());
    // assertTrue("El total de solicitudes periciales recuperadas debe de ser mayor a 0 ",
    // loSolicitudesPericiales.size() >0);
    // }
    //
    // public void testConsultarSolicitudesPericialesDeDictamenPendientes() {
    // List<SolicitudPericial> loSolicitudesPericiales =
    // daoServcice.consultarSolicitudesPericiales(TIPO_SOLICITUD_DICTAMEN,Solicitudes.PENDIENTE.getNombre(),Solicitudes.ABOGADO_DEFENSOR.getNombre());
    // assertTrue("El total de solicitudes periciales recuperadas debe de ser mayor a 0 ",
    // loSolicitudesPericiales.size() >0);
    // }
    //
    // public void testConsultarSolicitudesPericialesDeDictamenConcluidas() {
    // List<SolicitudPericial> loSolicitudesPericiales =
    // daoServcice.consultarSolicitudesPericiales(TIPO_SOLICITUD_DICTAMEN,Solicitudes.CONCLUIDA.getNombre(),Solicitudes.ABOGADO_DEFENSOR.getNombre());
    // assertTrue("El total de solicitudes periciales recuperadas debe de ser mayor a 0 ",
    // loSolicitudesPericiales.size() >0);
    // }

    public void testCreateEvidencia() {
        SolicitudPericial pojo = new SolicitudPericial();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(1L));
        // pojo.setFechaLimite(new Date());
        pojo.setForma(new Forma(Formas.SOLICITUD.getValorId()));
        pojo.setNombreDocumento("Solicitud Evidencia de defensor (P)");
        pojo.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.EVIDENCIA.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(7L));
        pojo.setPuestoUsuarioSolicitante("Fiscal");
        pojo.setFuncionarioSolicitante(new Funcionario(3L));
        pojo.setDestinatario(new Funcionario(1L));
        // pojo.setConfInstitucion(new
        // ConfInstitucion(Long.valueOf(Instituciones.PJ.ordinal())));
        pojo.setFolioSolicitud("PG/"
                + Calendar.getInstance().get(Calendar.YEAR) + ""
                + Calendar.getInstance().getTimeInMillis() % 100000);
        daoServcice.create(pojo);
    }

    public void testCreateAsesoria() {
        SolicitudPericial pojo = new SolicitudPericial();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(1L));
        // pojo.setFechaLimite(new Date());
        pojo.setForma(new Forma(Formas.SOLICITUD.getValorId()));
        pojo.setNombreDocumento("Solicitud Evidencia 1");
        pojo.setEstatus(new Valor(EstatusSolicitud.EN_PROCESO.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.ASESORIA.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(3L));
        pojo.setPuestoUsuarioSolicitante("Fiscal");
        pojo.setDestinatario(new Funcionario(12L));
        pojo.setFuncionarioSolicitante(new Funcionario(11L));
        // pojo.setConfInstitucion(new
        // ConfInstitucion(Long.valueOf(Instituciones.PJ.ordinal())));
        pojo.setFolioSolicitud("PG/"
                + Calendar.getInstance().get(Calendar.YEAR) + ""
                + Calendar.getInstance().getTimeInMillis() % 100000);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        pojo.setFechaLimite(cal.getTime());
        daoServcice.create(pojo);
    }

    public void testCreateAsesoriaAsignada() {
        SolicitudPericial pojo = new SolicitudPericial();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
        // pojo.setFechaLimite(new Date());
        pojo.setForma(new Forma(Formas.SOLICITUD.getValorId()));
        pojo.setNombreDocumento("Solicitud Dicatamen");
        pojo.setEstatus(new Valor(EstatusSolicitud.CERRADA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.EVIDENCIA.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(114L));
        pojo.setDestinatario(new Funcionario(12L));
        pojo.setPuestoUsuarioSolicitante("AMP");
        Calendar calAux = Calendar.getInstance();
        calAux.add(Calendar.DATE, +3);
        pojo.setFechaLimite(calAux.getTime());
        pojo.setFuncionarioSolicitante(new Funcionario(17L));
        // pojo.setConfInstitucion(new
        // ConfInstitucion(Long.valueOf(Instituciones.PJ.ordinal())));
        pojo.setFolioSolicitud("PG/"
                + Calendar.getInstance().get(Calendar.YEAR) + ""
                + Calendar.getInstance().getTimeInMillis() % 100000);
        daoServcice.create(pojo);
    }

    public void testCreateDictamen() {
        SolicitudPericial pojo = new SolicitudPericial();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
        pojo.setForma(new Forma(Formas.SOLICITUD_DE_ESTUDIO.getValorId()));
        pojo.setNombreDocumento("DICTAMEN PERICIAL 1");
        pojo.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.DICTAMEN.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(4L));
        pojo.setFuncionarioSolicitante(new Funcionario(14L));
        pojo.setDestinatario(new Funcionario(13L));
        pojo.setPuestoUsuarioSolicitante("Defensor");
        pojo.setEspecialidad(new Valor(Especialidades.MECANICA_FORENSE
                .getValorId()));
        pojo.setFolioSolicitud("PG/"
                + Calendar.getInstance().get(Calendar.YEAR) + ""
                + Calendar.getInstance().getTimeInMillis() % 100000);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        pojo.setFechaLimite(cal.getTime());
        daoServcice.create(pojo);
    }
    /**
     * 
     */
    public void testCreateDictamenParaCoordinadorDef() {
        SolicitudPericial pojo = new SolicitudPericial();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
        pojo.setForma(new Forma(Formas.SOLICITUD_DE_ESTUDIO.getValorId()));
        pojo.setNombreDocumento("DICTAMEN PERICIAL 1");
        pojo.setEstatus(new Valor(EstatusSolicitud.ABIERTA.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.DICTAMEN.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(4L));
        pojo.setFuncionarioSolicitante(new Funcionario(14L));
        pojo.setDestinatario(new Funcionario(13L));
        pojo.setPuestoUsuarioSolicitante("Defensor");
        pojo.setEspecialidad(new Valor(Especialidades.MECANICA_FORENSE
                .getValorId()));
        pojo.setFolioSolicitud("PG/"
                + Calendar.getInstance().get(Calendar.YEAR) + ""
                + Calendar.getInstance().getTimeInMillis() % 100000);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 2);
        cal.set(Calendar.HOUR_OF_DAY, 10);
        cal.set(Calendar.MINUTE, 0);
        pojo.setFechaLimite(cal.getTime());
        daoServcice.create(pojo);
    }

    public void testConsultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario()
            throws NSJPNegocioException {
        // TiposSolicitudes.ASESORIA, EstatusSolicitud.ABIERTA,
        // Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES

        TiposSolicitudes tipo = TiposSolicitudes.ASESORIA;
        EstatusSolicitud estado = EstatusSolicitud.ABIERTA;
        Puestos puesto = Puestos.COORDINADOR_DE_SERVICIOS_PERICIALES;
        List<SolicitudPericial> lista = daoServcice
                .consultarSolicitudesPericialesPorTipoYEstatusDePuestoDestinatario(
                        tipo, estado, puesto);

        for (SolicitudPericial solicitudPericial : lista) {
            logger.debug("Id documento: " + solicitudPericial.getDocumentoId());
            logger.debug("Folio: " + solicitudPericial.getFolioSolicitud());
            logger.debug(solicitudPericial);
        }

    }

    
    /**
     * Prueba unitaria de la consulta de solicitudes enviadas
     * @author Emigdio Hernández
     */
    public void testConsultaSolicitudesEnviadasPorEstatus(){
    	List<SolicitudPericial> solicitudes = 
    	daoServcice.
    	consultarSolicitudesPericialesPorPuestoSolicitanteEstatusYNumeroExpediente(Puestos.COMANDANTE_POLICIA_MINISTERIAL, null, 1L);
    	assertNotNull(solicitudes);
    	
    }
}
