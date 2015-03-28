/**
 * Nombre del Programa  : RegistrarObjetoEnAudiencia.java
 * Autor                : Daniel Jiménez
 * Compania             : TATTVA-IT
 * Proyecto             : NSJP                    Fecha: 24 Jun 2011
 * Marca de cambio      : N/A
 * Descripcion General  : Registra un ojeto en la audiencia
 * Programa Dependiente : N/A
 * Programa Subsecuente : N/A
 * Cond. de ejecucion   : N/A
 * Dias de ejecucion    : N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                :N/A
 * Compania             :N/A
 * Proyecto             :N/A                                 Fecha: N/A
 * Modificacion         :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaDAO;
import mx.gob.segob.nsjp.dao.audiencia.AudienciaEvidenciaDAO;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.objeto.ObjetoDAO;
import mx.gob.segob.nsjp.model.Audiencia;
import mx.gob.segob.nsjp.model.AudienciaEvidencia;
import mx.gob.segob.nsjp.model.AudienciaEvidenciaId;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Objeto;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.RegistrarObjetoEnAudienciaService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarObjetoEnAudienciaServiceImpl implements
		RegistrarObjetoEnAudienciaService {

	private final static Logger logger = Logger.getLogger(RegistrarObjetoEnAudienciaServiceImpl.class);
	
	@Autowired
	private AudienciaEvidenciaDAO objetoAudienciaDAO;
	@Autowired
	private ObjetoDAO objetoDAO;
	@Autowired
	private CadenaDeCustodiaDAO cadenaCustodiaDAO;
	@Autowired
	private EvidenciaDAO evidenciaDAO;
	@Autowired
	private AudienciaDAO audienciaDAO;
    @Autowired
    private CalidadDAO calidadDAO;
    
	@Override
	public void registrarObjetoEnAudiencia(Long audienciaId, Long insitutcion,
			String descripcion, Long condicionFisica, String noCustodia, Long noPrueba) {
		
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA REGISTRAR OBJETO EN AUDIENCIA");
		
		Audiencia audiencia = audienciaDAO.read(audienciaId);
		
		Objeto objeto = new Objeto();
		ConfInstitucion ci = new ConfInstitucion();
		long idCalidad= 0L;
		Calidad calidad = new Calidad();
		ci.setConfInstitucionId(insitutcion);
		objeto.setInstitucionPresenta(ci);
		objeto.setDescripcion(descripcion);
			calidad.setTipoCalidad(new Valor(Calidades.PRUEBA.getValorId()));
			idCalidad = this.calidadDAO.create(calidad);
			calidad.setCalidadId(idCalidad);
		objeto.setCalidad(calidad);
		objeto.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
		objeto.setValorByCondicionFisicaVal(new Valor(condicionFisica));
		objeto.setValorByTipoObjetoVal(new Valor(Objetos.OTRO.getValorId()));
		objeto.setFechaCreacionElemento(Calendar.getInstance().getTime());
		objeto.setExpediente(audiencia.getExpediente());
		
		Long idObjeto = objetoDAO.create(objeto);
		objeto.setElementoId(idObjeto);
		
		CadenaDeCustodia cc = new CadenaDeCustodia();
		
		if(noCustodia != null && !noCustodia.isEmpty()){
			cc.setFolio(noCustodia);
			cc.setExpediente(audiencia.getExpediente());
			Long ccId = cadenaCustodiaDAO.create(cc);
			cc.setCadenaDeCustodiaId(ccId);
		}	

		Evidencia evidencia = new Evidencia();
		evidencia.setObjeto(objeto);
		evidencia.setNumeroEvidencia(noPrueba);
		evidencia.setCadenaDeCustodia(cc);
		evidencia.setCodigoBarras("");
		evidencia.setDescripcion("");
		evidencia.setOrigenEvidencia("");
		evidencia.setFechaLevantamiento(Calendar.getInstance().getTime());
		Long evidenciaId = evidenciaDAO.create(evidencia);
		evidencia.setEvidenciaId(evidenciaId);
		
		final AudienciaEvidencia objetoAudiencia = new AudienciaEvidencia();
		final AudienciaEvidenciaId evidenciaAudienciaId = new AudienciaEvidenciaId();
			evidenciaAudienciaId.setAudienciaId(audienciaId);
			evidenciaAudienciaId.setEvidenciaId(evidenciaId);
			objetoAudiencia.setId(evidenciaAudienciaId);
		objetoAudienciaDAO.create(objetoAudiencia);
		
	}
}
