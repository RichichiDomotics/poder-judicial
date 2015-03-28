/**
 * Nombre del Programa : FormaTransform.java
 * Autor                            : Emigdio Hernández
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 30/05/2011
 * Marca de cambio        : N/A
 * Descripcion General    : Clase de tranformaciones para el objeto Forma y FormaDTO
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
package mx.gob.segob.nsjp.service.forma.impl.transform;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.forma.FormaDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

/**
 * Clase de tranformaciones para el objeto Forma y FormaDTO
 * 
 * @version 1.0
 * @author Emigdio Hernández
 * 
 */
public class FormaTransformer {

	/**
	 * Transforma un objeto de entidad del tipo <code>Forma</code> a un DTO
	 * <code>FormaDTO</code>
	 * 
	 * @param src
	 *            Objeto con los datos a transformar
	 * @return DTO Transformado
	 */
	public static FormaDTO transformarForma(Forma src) {
		FormaDTO dto = null;
		if (src != null) {
			dto = new FormaDTO();
			dto.setCuerpo(src.getCuerpo());
			dto.setDescForma(src.getDescForma());
			dto.setFormaId(src.getFormaId());
			dto.setNombre(src.getNombre());
			dto.setFechaCreacion(src.getFechaCreacion());
			if(src.getTipoForma() != null){
				dto.setTipoFormaDTO(new ValorDTO(src.getTipoForma().getValorId(),
						src.getTipoForma().getValor()));
			}
			if (src.getTipoDocumento() != null) {
				dto.setTipoDocumentoDTO(new ValorDTO(src.getTipoDocumento().getValorId(), src.getTipoDocumento().getValor()));
			}
			//FIXME: VLAD
		/*	
			if(src.getTipoDocumento() != null){
				dto.setTipoDocumentoDTO(new ValorDTO(src.getTipoDocumento()
						.getValorId(), src.getTipoDocumento().getValor()));
			}
			
			dto.setFechaCreacion(src.getFechaCreacion());

			Set<Documento> documentos = src.getDocumentos();
			List<DocumentoDTO> documentosDTO=new ArrayList<DocumentoDTO>();
			for (Documento documento : documentos) {
				documentosDTO.add(DocumentoTransformer.transformarDocumento(documento));
			}
			dto.setDocumentosDTO(documentosDTO);
			Llenado de set de documentos provocando un stack Overflow !!!
			Campos tipos de documento y fecha de creación son transient, ¿Para que se utilizan?
			
			*/
		}
		return dto;
	}

	/**
	 * Transforma un objeto DTO del tipo <code>FormaDTO</code> a un objeto de
	 * entidad <code>Forma</code>
	 * 
	 * @param src
	 *            Objeto con los datos a transformar
	 * @return Objeto transformado
	 */
	public static Forma transformarFormaDTO(FormaDTO src) {
		Forma forma = null;
		if (src != null) {
			forma = new Forma();
			forma.setCuerpo(src.getCuerpo());
			forma.setDescForma(src.getDescForma());
			forma.setFormaId(src.getFormaId());
			forma.setNombre(src.getNombre());
			if (src.getTipoFormaDTO() != null) {
				forma.setTipoForma(new Valor(src.getTipoFormaDTO().getIdCampo()));
			}

		}
		return forma;
	}

}
