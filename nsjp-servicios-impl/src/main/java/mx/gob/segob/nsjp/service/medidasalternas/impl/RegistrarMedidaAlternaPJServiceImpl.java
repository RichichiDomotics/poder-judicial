/**
* Nombre del Programa : RegistrarMedidaAlternaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 6 Sep 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para registrar una medida alterna
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

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusMedida;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAlternaDAO;
import mx.gob.segob.nsjp.dto.medida.MedidaAlternaDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedidaAlterna;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.medida.impl.transform.MedidaAlternaTransformer;
import mx.gob.segob.nsjp.service.medidasalternas.RegistrarMedidaAlternaPJService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para registrar una medida alterna.
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class RegistrarMedidaAlternaPJServiceImpl implements
		RegistrarMedidaAlternaPJService {

	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(RegistrarMedidaAlternaPJServiceImpl.class);
	
	@Autowired
	private MedidaAlternaDAO medidaAlternaDAO;
	@Autowired
	private NumeroExpedienteDAO numeroExpedienteDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	
	@Override
	public Long resgistrarMedidaAlterna(
			MedidaAlternaDTO medidaAlternaDTO) throws NSJPNegocioException {
		
		if(logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA REGISTRAR UNA MEDIDA ALTERNA EN PODER JUDICIAL ****/");
		
		if(medidaAlternaDTO.getNumeroCarpetaEjecucion()==null ||
				medidaAlternaDTO.getInvolucrado().getElementoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		MedidaAlterna medidaAlterna = MedidaAlternaTransformer.transformarMedidaAlterna(medidaAlternaDTO);
		
		NumeroExpediente carpetaEjecucion = numeroExpedienteDAO.obtenerNumeroExpediente(medidaAlternaDTO.getNumeroCarpetaEjecucion(),null);		
		medidaAlterna.setNumeroExpediente(carpetaEjecucion);
		medidaAlterna.setTipoDocumento(new Valor(TipoDocumento.MEDIDA.getValorId()));
		medidaAlterna.setNombreDocumento(TipoDocumento.MEDIDA.toString());
		medidaAlterna.setFechaCreacion(new Date());
		medidaAlterna.setForma(new Forma(Formas.MEDIDA_ALTERNA.getValorId()));
		medidaAlterna.setEstatus(new Valor(EstatusMedida.EN_PROCESO.getValorId()));
		medidaAlterna.setNumeroCaso(carpetaEjecucion.getNumeroExpedientePadre().getNumeroExpediente());
		medidaAlterna.setNumeroCaso(carpetaEjecucion.getExpediente().getCaso().getNumeroGeneralCaso());
		medidaAlterna.setJuezOrdena(medidaAlternaDTO.getUsuario().getFuncionario().getNombreCompleto());
		
		if (carpetaEjecucion.getNumeroExpedientePadre()!=null)
			medidaAlterna.setNumeroCausa(carpetaEjecucion.getNumeroExpedientePadre().getNumeroExpediente());
				
		Involucrado involucrado = involucradoDAO.read(medidaAlternaDTO.getInvolucrado().getElementoId());
		medidaAlterna.setInvolucrado(involucrado);		
		
		Long idMedidaAlterna = medidaAlternaDAO.create(medidaAlterna);			
					
		if(logger.isDebugEnabled())
			logger.debug("/**** SE REGISTRO LA MEDIDA ALTERNA EN PODER JUDICIAL EXITOSAMENTE ****/");
		
		return idMedidaAlterna;
	}

}
