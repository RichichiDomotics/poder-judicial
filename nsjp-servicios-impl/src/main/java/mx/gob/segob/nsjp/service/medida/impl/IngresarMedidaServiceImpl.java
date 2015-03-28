/**
* Nombre del Programa : IngresarMedidaServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 05/08/2011
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
package mx.gob.segob.nsjp.service.medida.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.service.domicilio.IngresarDomicilioService;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;
import mx.gob.segob.nsjp.service.medida.IngresarMedidaService;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaAlternaTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del Servicio de Ingresar Medidas 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class IngresarMedidaServiceImpl implements IngresarMedidaService {
	
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(InvolucradoTransformer.class);
	
	@Autowired
	private MedidaAlternaDAO medidaAlternaDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;
	@Autowired
    private IngresarDomicilioService ingresarDomicilioService;

	public Long ingresarMedida(MedidaDTO medidaDTO) throws NSJPNegocioException{
		Long medidaId = null;
		Long domicilioId = null;
		MedidaAlterna medidaAlterna = null;
		
		//Validar los parametros necesarios para Medida y Medida Alterna
		if(medidaDTO==null || medidaDTO.getValorTipoMedida()==null || medidaDTO.getNombreDocumento()==null ||  
				//medidaDTO.getTipoDocumentoDTO()==null ||
				//medidaDTO.getFormaDTO()==null || medidaDTO.getFormaDTO().getFormaId()==null || medidaDTO.getFormaDTO().getFormaId()<0 ||
				medidaDTO.getFechaInicio()==null || medidaDTO.getInvolucrado()== null || medidaDTO.getInvolucrado().getElementoId()== null ||
				medidaDTO.getInvolucrado().getElementoId()<0  )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
			
		if(medidaDTO.getFechaCreacion()== null)
			medidaDTO.setFechaCreacion(new Date());
		medidaDTO.setTipoDocumentoDTO(new ValorDTO(TipoDocumento.MEDIDA.getValorId()));
		medidaDTO.setFormaDTO(new FormaDTO(38L));
		
		//Ingresar Domicilio
		if(medidaDTO.getDomicilio()!=null ){
			logger.info(" Medida - Se ingresara domicilio: "+ medidaDTO.getDomicilio());
			domicilioId = ingresarDomicilioService.ingresarDomicilio(medidaDTO.getDomicilio());
			logger.info(" Medida - Se ingreso domicilio: "+ domicilioId);
		}
		
		if(medidaDTO instanceof MedidaAlternaDTO){
			logger.info("Es Medida Alterna ");
			MedidaAlternaDTO medidaAlternaDTO = (MedidaAlternaDTO)medidaDTO;
			medidaAlterna = MedidaAlternaTransformer.transformarMedidaAlterna(medidaAlternaDTO);
			
			//Asociar Domicilio
			Domicilio domicilio = domicilioDAO.read(domicilioId);
			medidaAlterna.setDomicilio(domicilio);
			
			//Consultar la sentencia asociada
			if(medidaAlternaDTO.getSentenciaId()!= null && medidaAlternaDTO.getSentenciaId()>0){
			}
			medidaId= medidaAlternaDAO.create(medidaAlterna);
			logger.info("Se ingreso Medida Alterna ID:"+ medidaId);
		}
		//TODO GBP Hacer los mismo para Medida Cautelar 

		return medidaId;
	}
	
}
