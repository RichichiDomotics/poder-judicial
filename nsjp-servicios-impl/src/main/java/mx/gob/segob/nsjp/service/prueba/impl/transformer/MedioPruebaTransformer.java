/**
 * 
 */
package mx.gob.segob.nsjp.service.prueba.impl.transformer;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.prueba.MedioPruebaDTO;
import mx.gob.segob.nsjp.dto.prueba.RelacionDatoMedioPruebaDTO;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.MedioPrueba;
import mx.gob.segob.nsjp.model.RelacionDatoMedioPrueba;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;
import mx.gob.segob.nsjp.service.documento.impl.tranform.DocumentoTransformer;
import mx.gob.segob.nsjp.service.elemento.impl.ElementoTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * @author adrian
 *
 */
public class MedioPruebaTransformer {

	public static MedioPrueba transformarMedioPruebaDTO(
			MedioPruebaDTO dto) {
		MedioPrueba medio=new MedioPrueba(dto.getMedioPruebaId(), dto.getNombreMedio(), dto.getNumeroIdentificacion(), dto.getDescripcion(), dto.getUbicacionFisica());
		medio.setDocumentoMedioPrueba(DocumentoTransformer.transformarDocumentoDTO(dto.getDocumentoMedioPrueba()));
		return medio;
	}

	public static MedioPruebaDTO transformarMedioPrueba(MedioPrueba scr) {
		MedioPruebaDTO dto= new MedioPruebaDTO(scr.getMedioPruebaId(), scr.getNombreMedio(), scr.getNumeroIdentificacion(), scr.getDescripcion(), scr.getUbicacionFisica());
		
		if( scr.getRelacionesDatoMedioPrueba()!= null && !scr.getRelacionesDatoMedioPrueba().isEmpty()){
			List<RelacionDatoMedioPruebaDTO> relacionesDatoMedioPruebaDTO = new ArrayList<RelacionDatoMedioPruebaDTO>();
			for (RelacionDatoMedioPrueba relacionDatoMedioPrueba : scr.getRelacionesDatoMedioPrueba()) {
				relacionesDatoMedioPruebaDTO.add(RelacionDatoMedioPruebaTransformer.transformarRelacionDatoMedioBasico(relacionDatoMedioPrueba));
			}
			dto.setRelacionesDatoMedioPrueba(relacionesDatoMedioPruebaDTO);
		}
		
		if( scr.getDocumentoMedioPrueba()!= null){
			DocumentoDTO documentoDTO = DocumentoTransformer.transformarDocumento(scr.getDocumentoMedioPrueba());
			if( scr.getDocumentoMedioPrueba().getArchivoDigital()!=null){
				documentoDTO.setArchivoDigital( ArchivoDigitalTransformer.transformarArchivoDigital(scr.getDocumentoMedioPrueba().getArchivoDigital()));
			}
			dto.setDocumentoMedioPrueba(documentoDTO);
		}
		if(scr.getElementoMedioPrueba() != null){
			if(scr.getElementoMedioPrueba() instanceof Involucrado){
				Involucrado inv = (Involucrado) scr.getElementoMedioPrueba();
				InvolucradoDTO invDTO = InvolucradoTransformer.transformarInvolucradoBasico(inv);
				dto.setElementoMedioPrueba(invDTO);
			}
			else{
				ElementoDTO elemDTO = ElementoTransformer.transformarElemento(scr.getElementoMedioPrueba());
				dto.setElementoMedioPrueba(elemDTO);
			}
		}
		return dto;
	}
}
