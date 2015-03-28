/**
 * 
 */
package mx.gob.segob.nsjp.service.expediente.impl.transform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ConvenioDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.medida.CompromisoPeriodicoDTO;
import mx.gob.segob.nsjp.dto.medida.FechaCompromisoDTO;
import mx.gob.segob.nsjp.model.Convenio;
import mx.gob.segob.nsjp.model.FechaCompromiso;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.involucrado.impl.transform.InvolucradoTransformer;

/**
 * @author adrian
 * 
 */
public class ConvenioTransformer {

	public static Convenio transformarConvenioDTO(ConvenioDTO dto) {
		Convenio acu = new Convenio();

		if (dto.getConvenioID() != null)
			acu.setDocumentoId(dto.getConvenioID());
		if (dto.getNumeroExpediente() != null)
			acu.setNumeroExpediente(new NumeroExpediente(dto
					.getNumeroExpediente().getNumeroExpedienteId()));
		if (dto.getInvolucradoPR() != null)
			acu.setInvolucradoPR(new Involucrado(dto.getInvolucradoPR()
					.getElementoId()));
		if (dto.getInvolucradoVictima() != null)
			acu.setInvolucradoVictima(new Involucrado(dto.getInvolucradoVictima().getElementoId()));
		if (dto.getTipoConvenio() != null)
			acu.setTipoConvenio(new Valor(dto.getTipoConvenio().getIdCampo()));
		if (dto.getFuncionario() != null)
			acu.setFuncionario(new Funcionario(dto.getFuncionario()
					.getClaveFuncionario()));
		if (dto.getPeriodicidad() != null)
			acu.setPeriodicidad(new Valor(dto.getPeriodicidad().getIdCampo()));

		acu.setNumeroMediacion(dto.getNumeroConvenio());
		acu.setFechaInicio(dto.getFechaInicio());
		acu.setFechaFin(dto.getFechaFin());
		acu.setMonto(dto.getMonto());

		if (dto.getCompromisoPeriodicoDTO() != null)
			acu.setCompromisoPeriodico(CompromisoPeriodicoTransformer
					.transformarCompromisoDTO(dto.getCompromisoPeriodicoDTO()));

		return acu;
	}

	public static ConvenioDTO transformarConvenioSimple(Convenio ar) {
		ConvenioDTO dto = new ConvenioDTO();
		if (ar.getDocumentoId() != null)
			dto.setConvenioID(ar.getDocumentoId());
		dto.setNumeroConvenio(ar.getNumeroMediacion());
		dto.setFechaInicio(ar.getFechaInicio());
		dto.setFechaFin(ar.getFechaFin());
		if (ar.getPeriodicidad() != null)
			dto.setPeriodicidad(new ValorDTO(ar.getPeriodicidad().getValorId(),
					ar.getPeriodicidad().getValor()));
		dto.setMonto(ar.getMonto());

		if (ar.getCompromisoPeriodico() != null) {
			CompromisoPeriodicoDTO compromiso = new CompromisoPeriodicoDTO();
			if (ar.getCompromisoPeriodico().getFechaCompromisos() != null) {
				List<FechaCompromisoDTO> fechas = new ArrayList<FechaCompromisoDTO>();
				for (FechaCompromiso feCo : ar.getCompromisoPeriodico()
						.getFechaCompromisos()) {
					fechas.add(FechaCompromisoTransformer
							.transformarFechaCompromisoSimple(feCo));
				}
				compromiso.setFechaCompromisos(fechas);
				compromiso.setMonto(ar.getCompromisoPeriodico().getMonto());
			}

			dto.setCompromisoPeriodicoDTO(compromiso);
		}
		return dto;
	}

	public static ConvenioDTO transformarConvenio(Convenio conv) {
		ConvenioDTO dto = new ConvenioDTO();
		if (conv.getDocumentoId() != null)
			dto.setConvenioID(conv.getDocumentoId());
		dto.setNumeroConvenio(conv.getNumeroMediacion());
		dto.setFechaInicio(conv.getFechaInicio());
		dto.setFechaFin(conv.getFechaFin());
		if (conv.getPeriodicidad() != null)
			dto.setPeriodicidad(new ValorDTO(conv.getPeriodicidad()
					.getValorId(), conv.getPeriodicidad().getValor()));
		dto.setMonto(conv.getMonto());
		if (conv.getInvolucradoPR() != null)
			dto.setInvolucradoPR(InvolucradoTransformer
					.transformarInvolucrado(conv.getInvolucradoPR()));
		
		if (conv.getInvolucradoVictima() != null)
			dto.setInvolucradoVictima(InvolucradoTransformer
					.transformarInvolucrado(conv.getInvolucradoVictima()));
		dto.setTipoConvenio(new ValorDTO(conv.getTipoConvenio().getValorId(), conv.getTipoConvenio().getValor()));  
		
		if (conv.getFuncionario() != null)
			dto.setFuncionario(FuncionarioTransformer
					.transformarFuncionario(conv.getFuncionario()));

		if (conv.getCompromisoPeriodico() != null) {
			CompromisoPeriodicoDTO compromisoDTO = new CompromisoPeriodicoDTO();
			compromisoDTO.setMonto(conv.getCompromisoPeriodico().getMonto());
			if (conv.getCompromisoPeriodico().getLugarPresentacion() != null)
				compromisoDTO.setLugarPresentacion(new JerarquiaOrganizacionalDTO(
						conv.getCompromisoPeriodico().getLugarPresentacion()
								.getJerarquiaOrganizacionalId()));
			if (conv.getCompromisoPeriodico().getFechaCompromisos() != null) {
				List<FechaCompromisoDTO> fechas = new ArrayList<FechaCompromisoDTO>();
				for (FechaCompromiso feCo : conv.getCompromisoPeriodico()
						.getFechaCompromisos()) {
					fechas.add(FechaCompromisoTransformer
							.transformarFechaCompromiso(feCo));
				}
				compromisoDTO.setFechaCompromisos(fechas);
			}

			dto.setCompromisoPeriodicoDTO(compromisoDTO);
		}
		return dto;
	}

}
