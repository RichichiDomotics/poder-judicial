/**
 * 
 */
package mx.gob.segob.nsjp.service.elemento.impl;
import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.ElementoDAO;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Elemento;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.service.elemento.AnularElementoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author MelitonBC
 *
 */
@Service
@Transactional
public class AnularElementoServiceImpl implements AnularElementoService{
	private static final Logger logger = Logger
    .getLogger(AdjuntarArchivoAElementoServiceImpl.class);
	@Autowired
	private ElementoDAO elementoDao;
	@Autowired
	private EvidenciaDAO evidenciaDAO;
	@Autowired
	private RelacionDAO relacionDAO;
	@Autowired
	private DelitoPersonaDAO delitoPersonaDAO;

	@Override
	public Boolean anularElemento(Long idElemento) throws NSJPNegocioException {
		logger.debug("SERVICIO QUE ELIMINA UN ELEMENTO POR SU ID:::::::::: " + idElemento);
		if(idElemento==null || idElemento < 0 ){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		final Elemento elePojo=this.elementoDao.read(idElemento);

		if (elePojo.getCalidad().getTipoCalidad().getValorId()
				.equals(Calidades.EVIDENCIA.getValorId())) {
			// Eliminar la evidencia asociada al Elemento
			final Evidencia evidencia = evidenciaDAO
					.consultarEvidenciaXObjetoId(idElemento);
			if (evidencia != null) {
				evidenciaDAO.delete(evidencia);
			}
		}
		else {
	
			Calidades calidadInvolucrado = Calidades.getByValor(elePojo
					.getCalidad().getTipoCalidad().getValorId());
			switch (calidadInvolucrado) {
				case PROBABLE_RESPONSABLE_PERSONA:
				case PROBABLE_RESPONSABLE_ORGANIZACION:
				case DENUNCIANTE:
				case DENUNCIANTE_ORGANIZACION:
				case VICTIMA_PERSONA:
				case QUIEN_DETUVO:
				case DEFENSOR_PRIVADO:
				case DEFENSOR_PUBLICO:
				case REPRESENTANTE_LEGAL:
				case TESTIGO:
				case TRADUCTOR:
				case TUTOR: {
					// Consultar las relaciones
					final List<Relacion> relacionesInvolucrado = relacionDAO
							.consultarRelacionByElemento(idElemento);
					// Eliminar la relación independientemente del tipo de relación
					relacionDAO.deleteAll(relacionesInvolucrado);
					break;
				}
			}
			
			// Buscar relaciones con delito
			List<DelitoPersona> delitosPersona = delitoPersonaDAO
					.consultarDelitoPerByInvolucrado(idElemento);
			if (!delitosPersona.isEmpty()) {
				delitoPersonaDAO.deleteAll(delitosPersona);
			}
		}
		
		elePojo.setEsActivo(Boolean.FALSE);
		this.elementoDao.update(elePojo);
		return true;
	}
	
	@Override
	public List<String> consultarRelacionesElemento(Long idElemento) throws NSJPNegocioException {
		logger.debug("SERVICIO QUE CONSULTA LAS RELACIONES DEL ELEMENTO DE ACUERDO A SU CALDAD:::::::::: " + idElemento);
		if(idElemento==null || idElemento < 0 ){
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		final List<String> relacionesExistentes = new ArrayList<String>();
		final Relaciones relacionesEnum[] = Relaciones.values();
		final Elemento elePojo=this.elementoDao.read(idElemento);

		if (elePojo.getCalidad().getTipoCalidad().getValorId()
				.equals(Calidades.EVIDENCIA.getValorId())) {
			// Eliminar la evidencia asociada al Elemento
			final Evidencia evidencia = evidenciaDAO
					.consultarEvidenciaXObjetoId(idElemento);
			if (evidencia != null) {
				relacionesExistentes.add(" Evidencia");
			}
			return relacionesExistentes;
		}
		else{

			Calidades calidadInvolucrado = Calidades.getByValor(elePojo
					.getCalidad().getTipoCalidad().getValorId());
			switch (calidadInvolucrado) {
				case PROBABLE_RESPONSABLE_PERSONA:
				case PROBABLE_RESPONSABLE_ORGANIZACION:
				case DENUNCIANTE:
				case DENUNCIANTE_ORGANIZACION:
				case VICTIMA_PERSONA:
				case QUIEN_DETUVO:
				case DEFENSOR_PRIVADO:
				case DEFENSOR_PUBLICO:
				case REPRESENTANTE_LEGAL:
				case TESTIGO:
				case TRADUCTOR:
				case TUTOR: {
					// Consultar las relaciones
					final List<Relacion> relacionesInvolucrado = relacionDAO
							.consultarRelacionByElemento(idElemento);
	
					for (Relacion relacion : relacionesInvolucrado) {
						if (relacion != null
								&& relacion.getCatRelacion() != null
								&& relacion.getCatRelacion().getCatRelacionId() != null) {
							Relaciones relacionInvolucrado = relacionesEnum[relacion
									.getCatRelacion().getCatRelacionId().intValue()];
							switch (relacionInvolucrado) {
							case ABOGADO:
							case ORGANIZACION_INVOLUCRADA:
							case QUIEN_DETUVO:
							case REPRESENTANTE_LEGAL:
							case TRADUCTOR_INTERPRETE:{
									String calidadRelacion = null;
									if( relacion.getElementoByComplementoId().getElementoId().equals(idElemento)){
										calidadRelacion =  relacion.getElementoBySujetoId().getCalidad().getTipoCalidad().getValor();	
									}
									else{
										calidadRelacion = relacion.getElementoByComplementoId().getCalidad().getTipoCalidad().getValor();
									}
									//logger.info("**Relación:"+ calidadRelacion);
									relacionesExistentes.add(calidadRelacion);
									break;
								}
							}
						}
					}
					break;
				}
			}
		
			//Buscar relaciones con delito
			List<DelitoPersona> delitosPersona = delitoPersonaDAO.consultarDelitoPerByInvolucrado(idElemento);
			if(!delitosPersona.isEmpty()){
				relacionesExistentes.add(delitosPersona.size() +  " delito(s)" );
			}
		}
		
		return relacionesExistentes;
	}
}
