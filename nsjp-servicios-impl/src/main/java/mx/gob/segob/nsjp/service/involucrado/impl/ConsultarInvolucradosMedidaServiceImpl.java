/**
* Nombre del Programa : ConsultarInvolucradosMedidaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 09/08/2011
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.service.involucrado.ConsultarInvolucradosMedidaService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.medida.ConsultarMedidaService;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
public class ConsultarInvolucradosMedidaServiceImpl implements
		ConsultarInvolucradosMedidaService {

    private final static Logger logger = Logger.getLogger(IngresarIndividuoServiceImpl.class);

    @Autowired
    private MedidaDAO medidaDAO;  
    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDAO;
    @Autowired
    private  NombreDemograficoDAO nombreDemograficoDAO;
    @Autowired
	private ConsultarMedidaService consultarMedidaService;
	
    
	public List<InvolucradoDTO>  consultarInvolucradosMedidasPorFecha(Long cFuncionario, Date fechaActual, Boolean esMedidaAlterna) throws NSJPNegocioException{
		List<InvolucradoDTO>  involucradosDTO  = new ArrayList<InvolucradoDTO>() ;
		
		if(cFuncionario==null ||  cFuncionario<0 )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		if(fechaActual==null)
			fechaActual = new Date();
		
		//Consultar los ID
		//Es MedidaAlterna tiene un valor de 0
		List<Involucrado> involucrados = medidaDAO.consultarIdInvolucradosMedidasTipoPorFechas(cFuncionario, 0L, fechaActual);
		logger.info("InvolucradosID: "+ involucrados.size());
		
		for (Involucrado involucrado  : involucrados) {
				InvolucradoDTO involucradoDTO = InvolucradoTransformer.transformarInvolucrado(involucrado);
	
				List<NombreDemografico> nombres = nombreDemograficoDAO.consutarNombresByInvolucrado(involucrado.getElementoId());       			       			
				involucradoDTO.setNombresDemograficoDTO(NombreDemograficoTransformer.transformarNombreDemografico(nombres));
				
				if(involucradoDTO.getExpedienteDTO()!= null && involucradoDTO.getExpedienteDTO().getExpedienteId()!= null ) {
					NumeroExpediente numeroExpediente = numeroExpedienteDAO.obtenerNumeroExpedienteXExpediente(involucradoDTO.getExpedienteDTO().getExpedienteId());
					involucradoDTO.getExpedienteDTO().setNumeroExpediente(numeroExpediente.getNumeroExpediente());
					involucradoDTO.getExpedienteDTO().setNumeroExpedienteId(numeroExpediente.getNumeroExpedienteId());
					involucradoDTO.getExpedienteDTO().setTipoExpediente(new ValorDTO(numeroExpediente.getTipoExpediente().getValorId()));
				}
				//Medidas e incidencias
				if(involucrado.getMedidas()!= null && !involucrado.getMedidas().isEmpty()){
					List<MedidaDTO> medidasDTO = new ArrayList<MedidaDTO>(0); 
					for (Medida medida : involucrado.getMedidas()) {
						MedidaDTO medidaDTO = consultarMedidaService.consultarMedida(medida.getDocumentoId(), esMedidaAlterna);
						medidasDTO.add(medidaDTO);
					}
					involucradoDTO.setMedidasDTO(medidasDTO);
				}
				involucradosDTO.add(involucradoDTO);
		}
		return involucradosDTO;
	}
}
