/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.model.CompromisoPeriodico;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;

/**
 * @author adrian
 *
 */
public class CompromisoPeriodicoTransformer {

	public static CompromisoPeriodico transformarCompromisoDTO(
			CompromisoPeriodicoDTO dto) {
		CompromisoPeriodico comp = new CompromisoPeriodico();
		
		comp.setMonto(dto.getMonto());
		if(dto.getFechaCompromisos()!=null){
			List<FechaCompromiso> fechas=new ArrayList<FechaCompromiso>();
			if(dto.getFechaCompromisos().size()>0){
				for (FechaCompromisoDTO fcDto : dto.getFechaCompromisos()) {
					fechas.add(FechaCompromisoTransformer.transformarFechaCompromisoDTO(fcDto));
				}
			}
			comp.setFechaCompromisos(fechas);
		}
		if(dto.getLugarPresentacion()!=null)
			comp.setLugarPresentacion(new JerarquiaOrganizacional(dto.getLugarPresentacion().getJerarquiaOrganizacionalId()));
		
		return comp;
	}
	
	public static CompromisoPeriodicoDTO transformarCompromiso(
			CompromisoPeriodico compromisoPeriodico) {
		CompromisoPeriodicoDTO compromisoPeriodicoDTO = new CompromisoPeriodicoDTO();
		
		compromisoPeriodicoDTO.setMonto(compromisoPeriodico.getMonto());
		if(compromisoPeriodico.getFechaCompromisos()!=null){
			List<FechaCompromisoDTO> fechasCompromisoDTO=new ArrayList<FechaCompromisoDTO>();
			if(compromisoPeriodico.getFechaCompromisos().size()>0){
				for (FechaCompromiso fechacompromiso : compromisoPeriodico.getFechaCompromisos()) {
					fechasCompromisoDTO.add(FechaCompromisoTransformer.transformarFechaCompromiso(fechacompromiso));
				}
			}
			compromisoPeriodicoDTO.setFechaCompromisos(fechasCompromisoDTO);
		}
		if(compromisoPeriodico.getLugarPresentacion()!=null)
			compromisoPeriodicoDTO.setLugarPresentacion(new JerarquiaOrganizacionalDTO(compromisoPeriodico.getLugarPresentacion().getJerarquiaOrganizacionalId()));
		
		return compromisoPeriodicoDTO;
	}

}
