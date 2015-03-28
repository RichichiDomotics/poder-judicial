/**
* Nombre del Programa : AsociarArchivoDigitalAMedidaServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29 Aug 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para asociar un ArchivoDigital a una Medida
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

import mx.gob.segob.nsjp.dao.archivo.ArchivoDigitalDAO;
import mx.gob.segob.nsjp.dao.documento.DocumentoDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaAdjuntosDAO;
import mx.gob.segob.nsjp.dao.medida.MedidaDAO;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.documento.MedidaCautelarDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.ArchivoDigital;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Medida;
import mx.gob.segob.nsjp.model.MedidaAdjuntos;
import mx.gob.segob.nsjp.model.MedidaAdjuntosId;
import mx.gob.segob.nsjp.service.medida.AsociarArchivoDigitalAMedidaService;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para asociar un ArchivoDigital a una Medida.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class AsociarArchivoDigitalAMedidaServiceImpl implements
		AsociarArchivoDigitalAMedidaService {
	
	/**
	 * 
	 */
	public static final Logger logger = Logger.getLogger(AsociarArchivoDigitalAMedidaServiceImpl.class);

	@Autowired
	MedidaAdjuntosDAO medidaAdjuntosDAO;
	@Autowired
	ArchivoDigitalDAO archivoDigitalDAO;
	@Autowired
	MedidaDAO medidaDAO;
	@Autowired
	DocumentoDAO documentoDAO;
	
	@Override
	public void asociarArchivoDigitalAMedida(
			ArchivoDigitalDTO archivoDigitalDTO, MedidaDTO medidaDTO)
			throws NSJPNegocioException {
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ASOCIAR UN ARCHIVO DIGITAL A UNA MEDIDA ****/");
		
		if (archivoDigitalDTO.getArchivoDigitalId()==null || medidaDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		ArchivoDigital archivoDigital = archivoDigitalDAO.read(archivoDigitalDTO.getArchivoDigitalId());		
		Medida medida = medidaDAO.read(medidaDTO.getDocumentoId());
		
		MedidaAdjuntos medidaAdjuntos = new MedidaAdjuntos();
		MedidaAdjuntosId medidaAdjuntosId = new MedidaAdjuntosId();
		
		medidaAdjuntosId.setArchivoDigitalId(archivoDigital.getArchivoDigitalId());
		medidaAdjuntosId.setMedidaId(medida.getDocumentoId());
		
		medidaAdjuntos.setId(medidaAdjuntosId);
		medidaAdjuntos.setArchivoDigital(archivoDigital);
		medidaAdjuntos.setMedida(medida);
		
		medidaAdjuntosDAO.create(medidaAdjuntos);
		
		if (logger.isDebugEnabled())
			logger.debug("/**** LA ASOCIACION SE REALIZO DE MANERA CORRECTA ****/");
	}

	@Override
	public void asociarDocumentoConMedidaCautelar(DocumentoDTO documentoDTO,
			MedidaCautelarDTO medidaCautelarDTO) throws NSJPNegocioException {		
		
		if (documentoDTO.getDocumentoId()==null || medidaCautelarDTO.getDocumentoId()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Documento documento = documentoDAO.read(documentoDTO.getDocumentoId());
		
		if (documento.getArchivoDigital()!=null)			
			asociarArchivoDigitalAMedida(new ArchivoDigitalDTO(documento.getArchivoDigital().getArchivoDigitalId()), 
													medidaCautelarDTO);
	}
	
	
}
