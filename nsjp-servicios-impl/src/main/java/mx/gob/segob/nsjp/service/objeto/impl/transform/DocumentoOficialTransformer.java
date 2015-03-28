/**
* Nombre del Programa : DocumentoOficialTransformer.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 16 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Transformer de objetos Documento Oficial
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
package mx.gob.segob.nsjp.service.objeto.impl.transform;

import java.util.Date;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.objeto.DocumentoOficialDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.DocumentoOficial;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * Transformer de objetos Documento Oficial
 * @version 1.0
 * @author Tattva-IT
 *
 */
public class DocumentoOficialTransformer {
	private final static Logger logger = Logger.getLogger(DocumentoOficialTransformer.class);
	
	public static DocumentoOficial transformarDocumentoOficial(DocumentoOficialDTO documentoOficialDto){
		DocumentoOficial docOfi = new DocumentoOficial();
		docOfi.setFolioElemento(documentoOficialDto.getFolioElemento());
		if(documentoOficialDto.getTipoDocumento()!=null && documentoOficialDto.getTipoDocumento().getIdCampo()!=null )
			docOfi.setTipoDocumento(new Valor(documentoOficialDto.getTipoDocumento().getIdCampo()));
	
		
		docOfi.setCantidad(documentoOficialDto.getCantidad());
		docOfi.setDescripcion(documentoOficialDto.getDescripcion());

		
		if(documentoOficialDto.getValorByCondicionFisicaVal()!=null && documentoOficialDto.getValorByCondicionFisicaVal().getIdCampo()!=null)
			docOfi.setValorByCondicionFisicaVal(new Valor(documentoOficialDto.getValorByCondicionFisicaVal().getIdCampo()));
		
		if(documentoOficialDto.getExpedienteDTO()!=null){
			docOfi.setExpediente(new Expediente(documentoOficialDto.getExpedienteDTO().getExpedienteId()));
			docOfi.setFechaCreacionElemento(new Date());
			
		}else
			 logger.debug("Sin expediente en DocumentoOficialDTO");
		
		docOfi.setEsActivo(documentoOficialDto.getEsActivo());
		
		return docOfi;
	}
	/**
	 * Transforma un objeto del tipo <code>DocumentoOficial</code> del modelo en su 
	 * correspondiente DTo
	 * @param src Dasto de origen
	 * @return DTO transformado
	 */
	public static DocumentoOficialDTO transformarDocumentoOficial(DocumentoOficial src){
		DocumentoOficialDTO dest = null;
		
		if(src != null){
			dest = new DocumentoOficialDTO();
			if(src.getTipoDocumento() != null){
				dest.setTipoDocumento(new ValorDTO(src.getTipoDocumento().getValorId(),src.getTipoDocumento().getValor()));
			}
			
			dest.setCantidad(src.getCantidad());
			dest.setEsActivo(src.getEsActivo());		
		}
		return dest;
	}
}
