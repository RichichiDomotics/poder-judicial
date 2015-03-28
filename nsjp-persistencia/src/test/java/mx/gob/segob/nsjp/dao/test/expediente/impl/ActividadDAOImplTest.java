/**
 * Nombre del Programa : ActividadDAOImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 26 May 2011
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
package mx.gob.segob.nsjp.dao.test.expediente.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.actividad.Actividades;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ActividadDAOImplTest extends BaseTestPersistencia<ActividadDAO> {

	public void testInsertar() {
		Actividad act = new Actividad();
		act.setExpediente(new Expediente(2L));
		act.setTipoActividad(new Valor(Actividades.GENERAR_DENUNCIA_EN_ATP
				.getValorId()));
		act.setFechaCreacion(new Date());
		act.setFuncionario(new Funcionario(2L));
		Long idAct = daoServcice.create(act);
		assertNotNull("EL ID registrado no puede ser null", idAct);

	}

	public void testObtenerActividadActual() {
		Actividad act = daoServcice.obtenerActividadActual(133L);
		assertNotNull("EL registro no puede ser null", act);
		assertEquals("La actividad tiene que ser del expediente uno", 133, act
				.getExpediente().getExpedienteId().longValue());
		logger.info("ActividadID: "+ act.getActividadId());
		logger.info("Actividad docu:"+act.getDocumento().getDocumentoId());
	}

	public void testConsultarDocumentosXExpediente() {
		Documento doc = daoServcice
				.consultarDocumentosXExpediente(42L, TipoDocumento.TEORIA_DEL_CASO.getValorId());
		assertNotNull(doc);
			logger.info("------------------");
			logger.info("ID: " + doc.getDocumentoId());
//			logger.info("Tipo: " + doc.getTipoDocumento().getValor());
//			if (doc.getOficioEstructurado() != null) {
//				logger.info("Oficio: "
//						+ doc.getOficioEstructurado().getEncabezado());
//				logger.info("Cuerpos: "
//						+ doc.getOficioEstructurado().getCuerposOficio().size());
//			}
	}
	
	public void testConsultarActividadXExpedienteYDocumento(){
		Long expediente= 2L;
		Long documentoId=35L;
		Actividad actividad = daoServcice.consultarActividadXExpedienteYDocumento(expediente, documentoId);
		assertNotNull(actividad);
		logger.info("Id: "+actividad.getActividadId());
		logger.info("Exp: "+actividad.getExpediente().getExpedienteId()+ "NE: "+actividad.getExpediente().getNumeroExpediente());
		logger.info("Doc: "+actividad.getDocumento().getDocumentoId());
	}
	
	public void testConsultarActividadPorExpedienteIdTipoActividad(){
		Long idExpediente= 104L;
		Long tipoActividad=Actividades.NOTIFICAR_AUDITORIA.getValorId();
		Actividad actividad = daoServcice.consultarActividadPorExpedienteIdTipoActividad(idExpediente, tipoActividad);
		assertNotNull(actividad);
		logger.info("Id: "+actividad.getActividadId());
		logger.info("Exp: "+actividad.getExpediente().getExpedienteId()+ "NE: "+actividad.getExpediente().getNumeroExpediente());
		logger.info("Doc: "+actividad.getDocumento().getDocumentoId());
	}
	
	public void testConsultarActividadesPorTipoActividadExpedienteId(){
		Long idExpediente= 790L;
		Long[] idTA= {Actividades.GENERAR_QUERELLA.getValorId(),
				Actividades.GENERAR_DENUNCIA_EN_ATP.getValorId(),
				Actividades.ATENDER_CANALIZACION_UI.getValorId()};
		List<Long> idTipoActividades= Arrays.asList(idTA);
		Boolean documentoRec = true;
		
		List<Actividad> actividades = daoServcice.consultarActividadesPorTipoActividadExpedienteId(idExpediente, idTipoActividades, documentoRec);
		logger.info(" #Actividades: "+ actividades);
		assertNotNull(actividades);
		for (Actividad actividad : actividades) {
			logger.info("*****Id: "+actividad.getActividadId());
			logger.info("Exp: "+actividad.getExpediente().getExpedienteId()+ " - NE: "+actividad.getExpediente().getNumeroExpediente());
		}
	}
	
	public void consultarNumeroActividadesPorTipoAtencionExpedienteId(){
		Long expedienteId=1L;
		Long tipoAtencionId=298L;
		Long numero = daoServcice.consultarNumeroActividadesPorTipoAtencionExpedienteId(expedienteId, tipoAtencionId);
		logger.info("*****numero de consulta obtenida: "+numero);
	}
}
