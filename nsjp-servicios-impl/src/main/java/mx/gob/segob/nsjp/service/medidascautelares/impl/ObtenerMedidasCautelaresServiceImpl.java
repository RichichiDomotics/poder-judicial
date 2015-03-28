/**
* Nombre del Programa : ObtenerPresuntosResponsablesPorCausaServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Jul 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del Contrato Service para los prsuntos responsables por causa
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
package mx.gob.segob.nsjp.service.medidascautelares.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.documento.MedidaCautelarDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedidaCautelar;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.medidascautelares.ObtenerMedidasCautelaresService;
import mx.gob.segob.nsjp.service.medidascautelares.impl.transform.MedidaCautelarTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del Contrato Service para los prsuntos responsables por causa
 * @version 1.0
 * @author Tattva-IT
 *
 */

@Service
@Transactional
public class ObtenerMedidasCautelaresServiceImpl implements
		ObtenerMedidasCautelaresService {

	private final static Logger logger = Logger.getLogger(ObtenerMedidasCautelaresServiceImpl.class);
	
	@Autowired
	private MedidaCautelarDAO medidaCautelarDAO;
	
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Override
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorInvolucrado(
			Long idInv) throws NSJPNegocioException {
		
		List<MedidaCautelarDTO> lstMedidasDTO = new ArrayList<MedidaCautelarDTO>();
		
		if (logger.isDebugEnabled())
			logger.debug("SERVICIO PARA OBTENER LAS MEDIDAS CAUTELARES POR INVOLUCRADO");

		List<MedidaCautelar> lstMedidas = new ArrayList<MedidaCautelar>();
		lstMedidas = medidaCautelarDAO.consultarMedidasCautelaesPorInvolucrado(idInv);
		
		lstMedidasDTO = MedidaCautelarTransformer.transformarMedidaCautelar(lstMedidas); 
		
		return lstMedidasDTO;
	}

	@Override
	public MedidaCautelarDTO obtenerDetalleMedidaCautelar(
			Long idMedidaCautelar, Long idInvolucrado)
			throws NSJPNegocioException {
		
		MedidaCautelarDTO response = null;
		
        if (logger.isDebugEnabled()) {
            logger.debug("SERVICIO PARA OBTENER EL DETALLE DE UNA MEDIDA CAUTELAR "
                    + idMedidaCautelar);
        }
        MedidaCautelar medida = this.medidaCautelarDAO.obtenerMedidaCautelar(
                idMedidaCautelar, idInvolucrado);

        if (medida != null) {
            response = MedidaCautelarTransformer.transformarMedidaCautelar(medida);
        } else {
            response = new MedidaCautelarDTO();
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
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.medidascautelares.ObtenerMedidasCautelaresService#obtenerMedidasCautelaresPorExpediente(java.lang.Long)
	 */
	@Override
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorExpediente(
			Long numeroExpedienteId) throws NSJPNegocioException {
		List<MedidaCautelar> medidas = medidaCautelarDAO.obtenerMedidasCautelaresPorExpediente(numeroExpedienteId);
		return MedidaCautelarTransformer.transformarMedidaCautelar(medidas);
	}

	@Override
	public List<MedidaCautelarDTO> consultaMedidasCautelaresPorEstatus(
			MedidaCautelarDTO medidaCautelar) throws NSJPNegocioException {
		
		List<MedidaCautelarDTO> mcDtoList = new ArrayList<MedidaCautelarDTO>();
		List<MedidaCautelar> mcList = medidaCautelarDAO.consultarMedidasCautelaresPorEstatus(medidaCautelar.getEstatus().getIdCampo());
		try
		{
			for(MedidaCautelar row:mcList)
			{
				MedidaCautelarDTO reg = new MedidaCautelarDTO();
				reg = MedidaCautelarTransformer.transformarMedidaCautelar(row);
				mcDtoList.add(reg);
			}
		}			
		catch(Exception ex)	{
			logger.debug(ex.getStackTrace());			
		}
		return mcDtoList;
	}
	/*
	 * (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.medidascautelares.ObtenerMedidasCautelaresService#consultarInvolucradosConMedidasCautelaresPorNumeroExpedienteOCausa(java.lang.String)
	 */
	@Override
	public List<InvolucradoDTO> consultarInvolucradosConMedidasCautelaresPorNumeroExpedienteOCausa(
			String numeroExpediente) throws NSJPNegocioException {
		List<InvolucradoDTO> resultado = new ArrayList<InvolucradoDTO>();
		NumeroExpediente nexp = numeroExpedienteDAO.obtenerNumeroExpediente(numeroExpediente,null);
		
		if(nexp != null){
			InvolucradoDTO elInvolucrado = null;
			List<Involucrado> involucrados = involucradoDAO.consultarInvolucradosByExpediente(nexp.getExpediente().getExpedienteId());
			List<MedidaCautelar> medidas = medidaCautelarDAO.obtenerMedidasCautelaresPorExpediente(nexp.getNumeroExpedienteId());
			for(Involucrado inv:involucrados){
				
				if(inv.getCalidad() != null && inv.getCalidad().getTipoCalidad() != null &&
						inv.getCalidad().getTipoCalidad().getValorId().longValue() ==
					Calidades.PROBABLE_RESPONSABLE_PERSONA.getValorId().longValue()){
					elInvolucrado =  InvolucradoTransformer.transformarInvolucradoBasico(inv);
					agregarMedidasCautelares(elInvolucrado,medidas);
					resultado.add(elInvolucrado);
				}
				
				
			}
		}
		
		
		return resultado;
	}
	/**
	 * Agrega las medidas cautelares correspondientes al involucrado actual de una lista 
	 * de medidas cautelares.
	 * Una vez asignadas las medidas, se retiran de la lista
	 * @param elInvolucrado Involucrado a llenar sus medidas cautelares
	 * @param medidas Lista de medidas de donde se tiene que elegir
	 */
	private void agregarMedidasCautelares(InvolucradoDTO elInvolucrado,
			List<MedidaCautelar> medidas) {
		List<MedidaCautelar> medidasAsignadas = new ArrayList<MedidaCautelar>();
		for(MedidaCautelar medida:medidas){
			if(medida.getInvolucrado().getElementoId().equals(elInvolucrado.getElementoId())){
				medidasAsignadas.add(medida);
			}
		}
		medidas.removeAll(medidasAsignadas);
		elInvolucrado.setMedidasCautelaresDTO(MedidaCautelarTransformer.transformarMedidaCautelar(medidasAsignadas));
	}

	@Override
	public MedidaCautelarDTO consultarMedidasCautelaresPorId(
			Long idMedidaCautelar) throws NSJPNegocioException {
		
		MedidaCautelarDTO medida = new MedidaCautelarDTO();
		
		medida = MedidaCautelarTransformer.transformarMedidaCautelar(medidaCautelarDAO.read(idMedidaCautelar));
		
		return medida;
	}

	@Override
	public List<MedidaCautelarDTO> obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior()
			throws NSJPNegocioException {
		
		List<MedidaCautelarDTO> lstMedidasDTO = new ArrayList<MedidaCautelarDTO>();
		
		if (logger.isDebugEnabled())
			logger.debug("obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior");

		List<MedidaCautelar> lstMedidas = new ArrayList<MedidaCautelar>();
		lstMedidas = medidaCautelarDAO.obtenerMedidasCautelaresPorFechaIncumplientoDelDiaAnterior();
		
		Set<MedidaCautelar> listaSinDuplicados = new HashSet<MedidaCautelar>();
		for (MedidaCautelar medidaCautelarBD : lstMedidas) {
			listaSinDuplicados.add(medidaCautelarBD);
		}
		
		lstMedidasDTO = MedidaCautelarTransformer.transformarMedidaCautelar(new ArrayList<MedidaCautelar>(listaSinDuplicados));
		
		return lstMedidasDTO;
	}

    @Override
    public List<MedidaCautelarDTO> consultarMedidasCautelaresPorNumeroExpedienteOCausa(
            String numeroExpediente,UsuarioDTO usuario) throws NSJPNegocioException {
    	

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
    	
        List<MedidaCautelar> medidas = medidaCautelarDAO.obtenerMedidasCautelaresPorNumeroExpediente(numeroExpediente,discriminanteId);
        final List<MedidaCautelarDTO> resp = new ArrayList<MedidaCautelarDTO>(); 
        MedidaCautelarDTO dto = null;
        for (MedidaCautelar medPojo : medidas) {
            dto = MedidaCautelarTransformer.transformarMedidaCautelar(medPojo);
            resp.add(dto);
        }
        return resp;
    }

}
