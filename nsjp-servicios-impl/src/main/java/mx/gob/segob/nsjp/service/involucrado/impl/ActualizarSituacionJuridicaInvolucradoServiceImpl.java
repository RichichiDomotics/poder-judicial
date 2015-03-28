/**
* Nombre del Programa : ActualizarSituacionJuridicaInvolucradoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 2 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : implementacion de servicio para actualizar la situacion juridica de un involucrado
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoExpediente;
import mx.gob.segob.nsjp.comun.enums.expediente.TipoMovimiento;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.enums.involucrado.SituacionJuridica;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.sentencia.SentenciaDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.AreaDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.sentencia.SentenciaDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.RegistroBitacora;
import mx.gob.segob.nsjp.model.Sentencia;
import mx.gob.segob.nsjp.model.SentenciaId;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.bitacora.RegistrarBitacoraService;
import mx.gob.segob.nsjp.service.expediente.AsignarNumeroExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.involucrado.ActualizarSituacionJuridicaInvolucradoService;
import mx.gob.segob.nsjp.service.sentencia.SentenciaService;
import mx.gob.segob.nsjp.service.sentencia.impl.transform.SentenciaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementacion de servicio para actualizar la situacion juridica de un involucrado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ActualizarSituacionJuridicaInvolucradoServiceImpl implements
		ActualizarSituacionJuridicaInvolucradoService {
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ActualizarSituacionJuridicaInvolucradoServiceImpl.class);
	
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private SentenciaDAO sentenciaDAO;
	   @Autowired
	    private RegistrarBitacoraService registrarBitacoraService;
	
	@Autowired
	private AsignarNumeroExpedienteService asignarNumeroExpedienteService;
	
	@Autowired
	private SentenciaService sentenciaService;
	
	@Override
	public Long actualizarSituacionJuridicaInvolucrado(
			InvolucradoDTO involucradoDTO, Long situacionJuridica, SentenciaDTO sentenciaDTO)
			throws NSJPNegocioException {
		if(logger.isDebugEnabled()){
			logger.debug("/**** SERVICIO PARA ACTUALIZAR LA SITUACION JURIDICA DEL INVOLUCRADO ****/");
		}
		if (involucradoDTO.getElementoId()==null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		Involucrado involucrado = involucradoDAO.read(involucradoDTO.getElementoId());		
		involucrado.setSituacionJuridica(new Valor(situacionJuridica));
		involucradoDAO.update(involucrado);
		
		NumeroExpediente carpetaEjecucion = numeroExpedienteDAO.obtenerCarpetaEjecucionPorInvolucrado(involucrado.getElementoId()); 
		
		if (SituacionJuridica.SENTENCIADO.getValorId().equals(situacionJuridica) && carpetaEjecucion==null) {
			logger.debug("/**** COMIENZA GENERACION DE CARPETA DE EJECUCION ****/");
			
			ExpedienteDTO expedienteDTO = new ExpedienteDTO();
			NumeroExpediente numCausa = numeroExpedienteDAO.obtenerCausaByExpediente(involucrado.getExpediente().getExpedienteId());
			ExpedienteDTO causaDTO = new ExpedienteDTO();
			causaDTO.setNumeroExpedienteId(numCausa.getNumeroExpedienteId());
			
			logger.debug("/**** ASOCIADA AL EXPEDIENTE ID :: "+involucrado.getExpediente().getExpedienteId());
			logger.debug("/**** Y A LA CAUSA ID :: "+numCausa.getNumeroExpedienteId());
			expedienteDTO.setExpedienteId(involucrado.getExpediente().getExpedienteId());
			expedienteDTO.setArea(new AreaDTO(Areas.DEPARTAMENTO_CAUSA));
			expedienteDTO.setTipoExpediente(new ValorDTO(TipoExpediente.CARPETA_DE_EJECUCION.getValorId()));
			expedienteDTO.setUsuario(involucradoDTO.getUsuario());
			expedienteDTO.setCausaPadre(causaDTO);
					
			ExpedienteDTO nuevoExp = asignarNumeroExpedienteService.asignarNumeroExpediente(expedienteDTO);	
//			Expediente nuevaCarpeta = expedienteDAO.read(nuevoExp.getExpedienteId());
			
//			involucrado.setExpediente(nuevaCarpeta);
			
//			NumeroExpediente newCarpetaEjecucion = numeroExpedienteDAO.read(nuevoExp.getNumeroExpedienteId());
			
			Sentencia sentencia = SentenciaTransformer.transformar(sentenciaDTO);
			
			NumeroExpediente numeroExpediente = new NumeroExpediente();
			numeroExpediente.setNumeroExpedienteId(numCausa.getNumeroExpedienteId());
			sentencia.setNumeroExpediente(numeroExpediente);
			
			Involucrado involucradoSentencia  = new Involucrado();
			involucradoSentencia.setElementoId(involucradoDTO.getElementoId());
			sentencia.setInvolucrado(involucradoSentencia);
			Long sentenciaId = sentenciaDAO.create(sentencia);
			sentenciaDTO.setSentenciaId(sentenciaId);
			sentenciaService.enviarSentencia(sentenciaDTO, Instituciones.RS);
			
			return nuevoExp.getNumeroExpedienteId();
		} else if (carpetaEjecucion==null) {
			carpetaEjecucion=new NumeroExpediente();
			carpetaEjecucion.setNumeroExpedienteId(new Long(0));
		}	
		
		return carpetaEjecucion.getNumeroExpedienteId();
	}
	
	@Override
	public Long obtenerSituacionJuridicaInvolucrado(
			InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		if(logger.isDebugEnabled()){
			logger.debug("/**** SERVICIO PARA OBTENER LA SITUACION JURIDICA DEL INVOLUCRADO ****/");
		}
		if (involucradoDTO.getElementoId()==null) {
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		}
		Involucrado involucrado = involucradoDAO.read(involucradoDTO.getElementoId());		
		Valor situacionJuridica = involucrado.getSituacionJuridica();
		
		if(situacionJuridica!=null && situacionJuridica.getValorId() != null){
			logger.debug("situacionJuridica = "+situacionJuridica.getValorId());
			return situacionJuridica.getValorId();
		}else{
			logger.debug("situacionJuridica = nulo");
			return null;
		}
	}

    @Override
    public void actualizarSituacionJuridicaDefensoria(
            InvolucradoDTO involucradoDTO, SituacionJuridica situacionJuridica)
            throws NSJPNegocioException {
        if (logger.isInfoEnabled()) {
            logger.info("Inicia - actualizarSituacionJuridicaDefensoria(...)");
        }
        if (involucradoDTO.getElementoId() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Involucrado involucrado = involucradoDAO.read(involucradoDTO
                .getElementoId());
        involucrado.setSituacionJuridica(new Valor(situacionJuridica
                .getValorId()));
        involucradoDAO.update(involucrado);
        logger.debug("involucrado.getDefensaInvolucrado() :: " + involucrado.getDefensaInvolucrado());
        if (involucrado.getDefensaInvolucrado() != null) {
            final RegistroBitacora regBitSJ = new RegistroBitacora();

            regBitSJ.setFechaInicio(new Date());
            regBitSJ.setTipoMovimiento(new Valor(
                    TipoMovimiento.CAMBIO_DE_SITUACION_JURIDICA.getValorId()));
            regBitSJ.setNuevo(String.valueOf(situacionJuridica.getValorId()));
            regBitSJ.setNumeroExpediente(involucrado.getDefensaInvolucrado()
                    .getNumeroExpediente());

            registrarBitacoraService
                    .registrarMovimientoDeExpedienteEnBitacora(regBitSJ);
        }
        if (logger.isInfoEnabled()) {
            logger.info("Fin - actualizarSituacionJuridicaDefensoria(...)");
        }
    }

}
