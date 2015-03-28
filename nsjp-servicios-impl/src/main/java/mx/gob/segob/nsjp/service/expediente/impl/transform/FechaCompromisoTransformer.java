/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.archivo.ArchivoDigitalDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.dto.medida.MedidaDTO;
import mx.gob.segob.nsjp.model.FechaCompArchDig;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.service.archivo.impl.transform.ArchivoDigitalTransformer;

/**
 * @author adrian
 *
 */
public class FechaCompromisoTransformer {

	public static FechaCompromiso transformarFechaCompromisoDTO(
			FechaCompromisoDTO dto) {
		FechaCompromiso feco=new FechaCompromiso();
		
		if(dto.getFechaCompromisoId()!=null)
			feco.setFechaCompromisoId(dto.getFechaCompromisoId());
		feco.setFechaCompromiso(dto.getFechaCompromiso());
		feco.setFechaCumplimiento(dto.getFechaCumplimiento());
		feco.setDescripcionCompromiso(dto.getDescripcionCompromiso());
		feco.setMonto(dto.getMonto());
		feco.setObservaciones(dto.getObservaciones());
		
		return feco;
	}

	public static FechaCompromisoDTO transformarFechaCompromisoSimple(
			FechaCompromiso feco) {
		FechaCompromisoDTO dto = new FechaCompromisoDTO();
		
		if(feco.getFechaCompromisoId()!=null)
			dto.setFechaCompromisoId(feco.getFechaCompromisoId());
		dto.setFechaCumplimiento(feco.getFechaCumplimiento());
		dto.setMonto(feco.getMonto());
		dto.setFechaCumplimiento(feco.getFechaCumplimiento());
		return dto;
	}

	public static FechaCompromisoDTO transformarFechaCompromiso(
			FechaCompromiso feco) {
		FechaCompromisoDTO dto =transformarFechaCompromisoSimple(feco);
		
		dto.setFechaCompromiso(feco.getFechaCompromiso());
		dto.setFechaCumplimiento(feco.getFechaCumplimiento());
		dto.setDescripcionCompromiso(feco.getDescripcionCompromiso());
		dto.setObservaciones(feco.getObservaciones());
		
		if(feco.getArchivosDigital()!=null){
			List<ArchivoDigitalDTO> archivosDTO=new ArrayList<ArchivoDigitalDTO>();
			for (FechaCompArchDig	arch : feco.getArchivosDigital()) {
				if(arch.getArchivoDigital()!=null)
					archivosDTO.add(ArchivoDigitalTransformer.transformarArchivoDigital(arch.getArchivoDigital()));
			}
//			dto.setArchivosDigital(archivosDTO);
		}
		return dto;
	}
    public static FechaCompromisoDTO transformarFechaCompromisoMaestro(
            FechaCompromiso feco) {
        FechaCompromisoDTO dto =transformarFechaCompromisoSimple(feco);
        
        dto.setFechaCompromiso(feco.getFechaCompromiso());
        dto.setFechaCumplimiento(feco.getFechaCumplimiento());
        dto.setDescripcionCompromiso(feco.getDescripcionCompromiso());
        dto.setObservaciones(feco.getObservaciones());
        final CompromisoPeriodicoDTO comDto = new CompromisoPeriodicoDTO();
        comDto.setCompromisoPeriodicoId(feco.getCompromisoPeriodico().getCompromisoPeriodicoId());
        final MedidaDTO med = new MedidaDTO();
        med.setDocumentoId(feco.getCompromisoPeriodico().getMedida().getDocumentoId());
        med.setDescripcionMedida(feco.getCompromisoPeriodico().getMedida().getDescripcionMedida());
        med.setValorTipoMedida(new ValorDTO(feco.getCompromisoPeriodico()
                .getMedida().getValorTipoMedida().getValorId(), feco
                .getCompromisoPeriodico().getMedida().getValorTipoMedida()
                .getValor()));
        med.setSeguimiento(feco.getCompromisoPeriodico().getMedida().getSeguimiento());
        med.setFechaInicio(feco.getCompromisoPeriodico().getMedida().getFechaInicio());
        med.setFechaFin(feco.getCompromisoPeriodico().getMedida().getFechaFin());
        med.setFolioDocumento(feco.getCompromisoPeriodico().getMedida().getFolioDocumento());
        comDto.setMedida(med);
        dto.setCompromisoPeriodico(comDto);
        
        return dto;
    }
}
