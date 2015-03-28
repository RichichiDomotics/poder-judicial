/**
* Nombre del Programa : ConsultarMedidasAlternasServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 19 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para realizar las consultas de medidas alternas
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
package mx.gob.segob.nsjp.service.medidasalternas.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.medidasalternas.ConsultarMedidasAlternasService;
import mx.gob.segob.nsjp.service.medidasalternas.impl.transform.MedidaAlternaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para realizar las consultas de medidas alternas.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class ConsultarMedidasAlternasServiceImpl implements
		ConsultarMedidasAlternasService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(ConsultarMedidasAlternasServiceImpl.class);
	
	@Autowired
	private MedidaAlternaDAO medidaAlternaDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	
	@Override
	public List<MedidaAlternaDTO> consultarMedidasAlternasPorNumeroExpediente(
			ExpedienteDTO expedienteDTO) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICO PARA CONSULTAR MEDIDAS ALTERNAS POR NUMERO EXPEDIENTE ID ****/");
		
		if  (expedienteDTO.getNumeroExpedienteId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		List<MedidaAlterna> mediasAlternas = medidaAlternaDAO.consultarMedidasAlternasPorNumeroExpediente(expedienteDTO.getNumeroExpedienteId()); 
		
		List<MedidaAlternaDTO> medAlternasDTO = new ArrayList<MedidaAlternaDTO>();
		for (MedidaAlterna medidaAlterna : mediasAlternas) {
			medAlternasDTO.add(MedidaAlternaTransformer.TransformaMedidaAlterna(medidaAlterna));
		}
		return medAlternasDTO;
	}

	@Override
	public List<MedidaAlternaDTO> consultarMedidasAlternasPorEstatus(
			EstatusMedida estatusMedida) throws NSJPNegocioException {
		
		List<MedidaAlterna> ma = medidaAlternaDAO.consultarMedidasAlternasPorEstatus(estatusMedida.getValorId());
		List<MedidaAlternaDTO> maDto = new ArrayList<MedidaAlternaDTO>();
		for(MedidaAlterna row:ma)
		{
			MedidaAlternaDTO medidaDto = new MedidaAlternaDTO();
			medidaDto = MedidaAlternaTransformer.TransformaMedidaAlterna(row);
			maDto.add(medidaDto);
		}
		return maDto;
	}

	@Override
	public MedidaAlternaDTO consultarMedidasAlternasPorId(
			MedidaAlternaDTO medidaAlternaDTO) throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICO PARA OBTENER EL DETALL DE UN MEDIDA ALTERNA ****/");
		
		if (medidaAlternaDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		MedidaAlterna medidaAlterna = medidaAlternaDAO.read(medidaAlternaDTO.getDocumentoId());
		
		MedidaAlternaDTO medAlternaRet = MedidaAlternaTransformer.TransformaMedidaAlterna(medidaAlterna);
		
		return medAlternaRet;
	}

	@Override
	public List<InvolucradoDTO> consultarInvolucradosConMedidasAlternativasPorCarpetaEjecucion(
			String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
		List<InvolucradoDTO> resultado = new ArrayList<InvolucradoDTO>();
		
		/*
		* Usado para obtener el discriminante Id
		*/
		  long discriminanteId = 0L; 
				

		if (usuario != null
				&& usuario.getFuncionario() != null
				&& usuario.getFuncionario().getDiscriminante() != null
				&& usuario.getFuncionario().getDiscriminante().getCatDiscriminanteId() != null) {

			discriminanteId = usuario.getFuncionario().getDiscriminante()
					.getCatDiscriminanteId();
		}
		
		NumeroExpediente nexp = numeroExpedienteDAO.obtenerNumeroExpediente(numeroExpediente,discriminanteId);
		
		if(nexp != null){
			InvolucradoDTO elInvolucrado = null;
			List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(nexp.getExpediente().getExpedienteId());
			List<MedidaAlterna> medidas = medidaAlternaDAO.consultarMedidasAlternasPorNumeroExpediente(nexp.getNumeroExpedienteId());
			for(Involucrado inv:involucrados){
				
				if(inv.getCalidad() != null && inv.getCalidad().getTipoCalidad() != null &&
						inv.getCalidad().getTipoCalidad().getValorId().longValue() ==
					Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId().longValue()){
					elInvolucrado =  InvolucradoTransformer.transformarInvolucradoBasico(inv);
					agregarMedidasAlternas(elInvolucrado,medidas);
					resultado.add(elInvolucrado);
				}
			}
		}
		return resultado;
	}
	/**
	 * Agrega las medidas Altenras correspondientes al involucrado actual de una lista 
	 * de medidas Alterna.
	 * Una vez asignadas las medidas, se retiran de la lista
	 * @param elInvolucrado Involucrado a llenar sus medidas Alternas
	 * @param medidas Lista de medidas de donde se tiene que elegir
	 */
	private void agregarMedidasAlternas(InvolucradoDTO elInvolucrado,
			List<MedidaAlterna> medidas) {
		List<MedidaAlterna> medidasAsignadas = new ArrayList<MedidaAlterna>();
		for(MedidaAlterna medida:medidas){
			if(medida.getInvolucrado().getElementoId().equals(elInvolucrado.getElementoId())){
				medidasAsignadas.add(medida);
			}
		}
		medidas.removeAll(medidasAsignadas);
		elInvolucrado.setMedidasAlternasDTO(MedidaAlternaTransformer.transformarMedidaAlterna(medidasAsignadas));
	}
	
	@Override
	public MedidaAlternaDTO obtenerDetalleMedidaAlterna(
			Long idMedidaAlterna, Long idInvolucrado)
			throws NSJPNegocioException {
		
		MedidaAlternaDTO response = null;
		
        if (logger.isDebugEnabled()) {
            logger.debug("SERVICIO PARA OBTENER EL DETALLE DE UNA MEDIDA ALTERNA "
                    + idMedidaAlterna);
        }
        MedidaAlterna medida = this.medidaAlternaDAO.obtenerMedidaAlterna(idMedidaAlterna, idInvolucrado);

        if (medida != null) {
            response = MedidaAlternaTransformer.TransformaMedidaAlterna(medida);
        } else {
            response = new MedidaAlternaDTO();
            Involucrado involucrado = involucradoDAO.read(idInvolucrado);
            InvolucradoDTO involDTO = InvolucradoTransformer
                    .transformarInvolucrado(involucrado);
            response.setInvolucrado(involDTO);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("response :: " + response);
        }
        return response;
	}
}
