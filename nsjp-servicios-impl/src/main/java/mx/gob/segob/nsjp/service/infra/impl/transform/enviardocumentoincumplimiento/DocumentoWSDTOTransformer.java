/**
* Nombre del Programa : DocumentoWSDTOTransformer.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 03/11/2011
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
package mx.gob.segob.nsjp.service.infra.impl.transform.enviardocumentoincumplimiento;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.service.infra.impl.transform.WsTransformer;
import mx.gob.segob.nsjp.ws.cliente.enviardocumentoincumplimientomedida.ArchivoDigitalWSDTO;
import mx.gob.segob.nsjp.ws.cliente.enviardocumentoincumplimientomedida.DocumentoWSDTO;

/**
 * Trasnformer para envio de Documento de Incumplimiento de Medida 
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class DocumentoWSDTOTransformer {
//	private final static Logger logger = Logger.getLogger(DocumentoWSDTOTransformer.class);
	
	public static DocumentoWSDTO transformarWSDTO( DocumentoDTO src ){
		DocumentoWSDTO documentoWSDTO = new DocumentoWSDTO();
		
		if (src.getFechaCreacion() != null){
			documentoWSDTO.setStrFechaCreacion(DateUtils.formatear(src.getFechaCreacion()));
			documentoWSDTO.setFechaCreacion(WsTransformer.transformFecha( src.getFechaCreacion()));
			documentoWSDTO.setStrHoraCreacion(DateUtils.formatearHora(src.getFechaCreacion()));
		}
		documentoWSDTO.setFolioDocumento(src.getFolioDocumento());
		documentoWSDTO.setFormaId(src.getFormaDTO()!=null? src.getFormaDTO().getFormaId() :  4L); //Forma en Blanco
		documentoWSDTO.setNombreDocumento(src.getNombreDocumento());
		documentoWSDTO.setNumeroFojas(src.getNumeroFojas());
		documentoWSDTO.setOrigenDocumento(src.getOrigenDocumento());
		documentoWSDTO.setTextoParcial(src.getTextoParcial());
		documentoWSDTO.setTipoDocumentoDTO(src.getTipoDocumentoDTO()!= null? src.getTipoDocumentoDTO().getIdCampo() : TipoDocumento.MEDIDA.getValorId());
		documentoWSDTO.setVersion(src.getVersion());
		
		if (src.getArchivoDigital() != null) {
        	ArchivoDigitalDTO archivoDigitalDto = src.getArchivoDigital();
        	ArchivoDigitalWSDTO archivoDigitalWSDTO = new ArchivoDigitalWSDTO();
        	
        	archivoDigitalWSDTO.setNombreArchivo(archivoDigitalDto.getNombreArchivo());
            archivoDigitalWSDTO.setTipoArchivo(archivoDigitalDto.getTipoArchivo());
            archivoDigitalWSDTO.setContenido(archivoDigitalDto.getContenido());
            
            documentoWSDTO.setArchivoDigital(archivoDigitalWSDTO);
        }
		return documentoWSDTO;
	}
	
	public static DocumentoDTO transformarDTO( mx.gob.segob.nsjp.dto.documento.DocumentoWSDTO src ){
		DocumentoDTO documentoDTO = new DocumentoDTO();
		
		
		if (src.getFechaCreacion() != null)
			documentoDTO.setFechaCreacion(src.getFechaCreacion());
//		else if(src.getStrFechaCreacion()!= null && !src.getStrFechaCreacion().trim().isEmpty())
//			documentoDTO.setFechaCreacion(DateUtils.obtener(src.getStrFechaCreacion()));
		else
			documentoDTO.setFechaCreacion(new Date());

		documentoDTO.setFolioDocumento(src.getFolioDocumento());
		documentoDTO.setFormaDTO(new FormaDTO(src.getFormaId()!= null ? src.getFormaId():  4L)); //Forma en Blanco
		
		documentoDTO.setNombreDocumento(src.getNombreDocumento());
		documentoDTO.setNumeroFojas(src.getNumeroFojas());
		documentoDTO.setOrigenDocumento(src.getOrigenDocumento());
		documentoDTO.setTextoParcial(src.getTextoParcial());
		documentoDTO.setTipoDocumentoDTO( new ValorDTO( src.getTipoDocumentoDTO() != null? src.getTipoDocumentoDTO(): TipoDocumento.MEDIDA.getValorId()));
		documentoDTO.setVersion(src.getVersion());
		
		if (src.getArchivoDigital() != null) {
        	mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalWSDTO archivoDigitalWSDTO = src.getArchivoDigital();
        	ArchivoDigitalDTO archivoDigitalDTO = new ArchivoDigitalDTO();
        	
        	archivoDigitalDTO.setNombreArchivo(archivoDigitalWSDTO.getNombreArchivo());
            archivoDigitalDTO.setTipoArchivo(archivoDigitalWSDTO.getTipoArchivo());
            archivoDigitalDTO.setContenido(archivoDigitalWSDTO.getContenido());
            
            documentoDTO.setArchivoDigital(archivoDigitalDTO);
        }
		documentoDTO.setDescripcion(src.getDescripcion());
		return documentoDTO;
	}
}
