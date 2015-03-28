/**
* Nombre del Programa : AtenderSolicitudPericialServiceImpl.java
* Autor                            : Emigdio
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 25/07/2011
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
package mx.gob.segob.nsjp.service.solicitud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.dao.documento.DictamenDAO;
import mx.gob.segob.nsjp.dto.documento.DictamenDTO;
import mx.gob.segob.nsjp.model.Dictamen;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.forma.impl.transform.FormaTransformer;

/**
 * Implementación del servicio de negocio para darle atención y seguimiento 
 * a una solicitud de pericial
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
@Service
@Transactional
public class AtenderSolicitudPericialServiceImpl implements
		AtenderSolicitudPericialService {
	@Autowired
	DictamenDAO dictamenDAO;
	
	/* (non-Javadoc)
	 * @see mx.gob.segob.nsjp.service.solicitud.AtenderSolicitudPericialService#registrarDictamenParaSolicitudPericial(mx.gob.segob.nsjp.dto.documento.DictamenDTO)
	 */
	@Override
	public DictamenDTO registrarDictamenParaSolicitudPericial(
			DictamenDTO dictamen) {
		
		if(dictamen != null){
			Dictamen dictamenBD = new Dictamen();
			
			dictamenBD.setSolicitudPericial(new SolicitudPericial(dictamen.getSolicitudPericial().getDocumentoId()));
			
			DocumentoTransformer.tranformarDocumentoUpdate(dictamenBD, dictamen);
			dictamenBD.setTipoDocumento(new Valor(82L));//Solicitud
			dictamenBD.setFolioDocumento("Folio Doc.");
			dictamenBD.setNombreDocumento("Nombre Documento");			
			dictamenDAO.create(dictamenBD);
			dictamen.setDocumentoId(dictamenBD.getDocumentoId());
		}
		
		return dictamen;
		

		
	}

}
