package mx.gob.segob.nsjp.service.informepolicial.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.dto.informepolicial.DelitoIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.FaltaAdministrativaIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.TurnoLaboralIphDTO;
import mx.gob.segob.nsjp.dto.informepolicial.TurnoLaboralIphIdDTO;
import mx.gob.segob.nsjp.dto.turnolaboral.TurnoLaboralDTO;
import mx.gob.segob.nsjp.model.DelitoIph;
import mx.gob.segob.nsjp.model.DelitoIphId;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIph;
import mx.gob.segob.nsjp.model.FaltaAdministrativaIphId;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.InformePolicialHomologado;
import mx.gob.segob.nsjp.model.TurnoLaboralIph;
import mx.gob.segob.nsjp.model.TurnoLaboralIphId;
import mx.gob.segob.nsjp.service.catalogo.impl.transform.CatalogoTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;

public class InformePolicialHomologadoTransformer {
	
	
	public static InformePolicialHomologado transformIPH(InformePolicialHomologadoDTO iphDTO)
	{
		if(iphDTO == null){
			return null;
		}
		InformePolicialHomologado informe = new InformePolicialHomologado();		
		
		informe.setInformePolicialHomologadoId(iphDTO.getInformePolicialHomologadoId());
		if(iphDTO.getExpediente()!=null){
//			informe.setExpediente(ExpedienteTransformer.transformarExpediente(iphDTO.getExpediente()));
			informe.setExpediente(new Expediente(iphDTO.getExpediente().getExpedienteId()));
		}
		if(iphDTO.getHecho()!=null)
			informe.setHecho(HechoTransformer.transformarHecho(iphDTO.getHecho()));
		if(iphDTO.getFuncionarioDestinatario()!=null)
			informe.setFuncionarioDestinatario(FuncionarioTransformer.transformarFuncionario(iphDTO.getFuncionarioDestinatario()));
		if(iphDTO.getFuncionarioElabora()!=null)
			informe.setFuncionarioElabora(FuncionarioTransformer.transformarFuncionario(iphDTO.getFuncionarioElabora()));
		if(iphDTO.getTipoParticipacion()!=null)
			informe.setTipoParticipacion(CatalogoTransformer.transformValor(iphDTO.getTipoParticipacion()));
		informe.setFechaCaptura(iphDTO.getFechaCaptura());
		informe.setObjetivosGenerales(iphDTO.getObjetivosGenerales());
		informe.setNumeroOficio(iphDTO.getNumeroOficio());
		informe.setAnio(iphDTO.getAnio());
		informe.setFolioIPH(iphDTO.getFolioIPH());
		informe.setNumEcoTransporte(iphDTO.getNumEcoTransporte());
		informe.setAsunto(iphDTO.getAsunto());
		
		return informe;
	}
	
	public static InformePolicialHomologadoDTO tranformIPH(InformePolicialHomologado iph)
	{
		InformePolicialHomologadoDTO resp = new InformePolicialHomologadoDTO();
		if(iph.getInformePolicialHomologadoId()!=null)
		resp.setInformePolicialHomologadoId(iph.getInformePolicialHomologadoId());
		if(iph.getExpediente()!=null)
			resp.setExpediente(ExpedienteTransformer.transformaExpediente(iph.getExpediente()));			
		if(iph.getHecho()!=null)
			resp.setHecho(HechoTransformer.transformarHecho(iph.getHecho()));
		if(iph.getFuncionarioDestinatario()!=null)
			resp.setFuncionarioDestinatario(FuncionarioTransformer.transformarFuncionario(iph.getFuncionarioDestinatario()));		
		if(iph.getFuncionarioElabora()!=null)
			resp.setFuncionarioElabora(FuncionarioTransformer.transformarFuncionario(iph.getFuncionarioElabora()));
		if(iph.getTipoParticipacion()!=null)
			resp.setTipoParticipacion(CatalogoTransformer.transformValor(iph.getTipoParticipacion()));		
		resp.setFechaCaptura(iph.getFechaCaptura());
		resp.setObjetivosGenerales(iph.getObjetivosGenerales());
		resp.setNumeroOficio(iph.getNumeroOficio());
		resp.setAnio(iph.getAnio());
		resp.setFolioIPH(iph.getFolioIPH());
		resp.setNumEcoTransporte(iph.getNumEcoTransporte());
		
		if(iph.getDelitoIphs()!=null){
			List<DelitoIphDTO> delitosIph=new ArrayList<DelitoIphDTO>();
			for (DelitoIph delIPH : iph.getDelitoIphs()) {
				delitosIph.add(DelitoIphTransformer.transformarDelitoIph(delIPH));
			}
			resp.setDelitoIph(delitosIph);
		}
		/*Faltas*/
		if(iph.getFaltaAdministrativaIphs()!=null){
			List<FaltaAdministrativaIphDTO> faltasIph=new ArrayList<FaltaAdministrativaIphDTO>();
			for (FaltaAdministrativaIph falIPH : iph.getFaltaAdministrativaIphs()) {
				faltasIph.add(FaltaAdministrativaIphTransformer.transformarFaltaAdministrativaIph(falIPH));
			}
			resp.setFaltaIph(faltasIph);
		}
		/*Turnos*/
		if(iph.getTurnoLaboralIphs()!=null){
			List<TurnoLaboralIphDTO> turnos=new ArrayList<TurnoLaboralIphDTO>();
			for (TurnoLaboralIph turIPH : iph.getTurnoLaboralIphs()) {
				InformePolicialHomologadoDTO informePolicialHomologado=new InformePolicialHomologadoDTO();
				TurnoLaboralDTO turnoLaboral=new TurnoLaboralDTO();
				TurnoLaboralIphIdDTO id=new TurnoLaboralIphIdDTO();
				informePolicialHomologado.setInformePolicialHomologadoId(iph.getInformePolicialHomologadoId());
				id.setInformePolicialHomologadoId(iph.getInformePolicialHomologadoId());
				turnoLaboral.setTurnoLaboralId(turIPH.getTurnoLaboral().getTurnoLaboralId());
				id.setTurnoLaboralId(turIPH.getTurnoLaboral().getTurnoLaboralId());
				
				turnos.add(new TurnoLaboralIphDTO(id, turnoLaboral, informePolicialHomologado));
			}
			resp.setTurnoLaboralIphs(turnos);
		}
		resp.setAsunto(iph.getAsunto());
			
		return resp;
	}
	/**
	 * Actualiza los datos de un Informe Policial Homologado
	 * @param informeActualizado
	 * @param iph
	 */
	public static void tranformIPHUpdate(
			InformePolicialHomologado dest,
			InformePolicialHomologadoDTO src) {
		
		if(src.getExpediente()!=null){
//			informe.setExpediente(ExpedienteTransformer.transformarExpediente(iphDTO.getExpediente()));
			dest.setExpediente(new Expediente(src.getExpediente().getExpedienteId()));
		}
		
		if(src.getFuncionarioElabora()!=null && src.getFuncionarioElabora().getClaveFuncionario()!=null)
			dest.setFuncionarioElabora(new Funcionario(src.getFuncionarioElabora().getClaveFuncionario()));
		if(src.getTipoParticipacion()!=null)
			dest.setTipoParticipacion(CatalogoTransformer.transformValor(src.getTipoParticipacion()));
		if(src.getFechaCaptura()!=null){
			dest.setFechaCaptura(src.getFechaCaptura());
		}
		
		dest.setObjetivosGenerales(src.getObjetivosGenerales());
		dest.setNumeroOficio(src.getNumeroOficio());
		if(src.getAnio() != null)
			dest.setAnio(src.getAnio());
		dest.setFolioIPH(src.getFolioIPH());
		dest.setNumEcoTransporte(src.getNumEcoTransporte());
		
//		InformePolicialHomologado informePolicialHomologado=new InformePolicialHomologado();
//		informePolicialHomologado.setInformePolicialHomologadoId(src.getInformePolicialHomologadoId());
		
		/*TURNOS*/
		if(src.getTurnoLaboralIphs()!=null){
			Set<TurnoLaboralIph> turnoLaboralIphs= new HashSet<TurnoLaboralIph>(0);
			for (TurnoLaboralIphDTO dto : src.getTurnoLaboralIphs()) {
//				TurnoLaboral turnoLaboral=new TurnoLaboral();
//				turnoLaboral.setTurnoLaboralId(dto.getTurnoLaboral().getTurnoLaboralId());
//				
//				TurnoLaboralIphId id=new TurnoLaboralIphId(informePolicialHomologado.getInformePolicialHomologadoId(), dto.getTurnoLaboral().getTurnoLaboralId());
//				turnoLaboralIphs.add(new TurnoLaboralIph(id, turnoLaboral, informePolicialHomologado));
				TurnoLaboralIphId id=new TurnoLaboralIphId(dest.getInformePolicialHomologadoId(), dto.getTurnoLaboral().getTurnoLaboralId());
				turnoLaboralIphs.add(new TurnoLaboralIph(id, null, null));
			}
			dest.setTurnoLaboralIphs(turnoLaboralIphs);
		}
		/*DELITOS*/
		if(src.getDelitoIph()!=null){
			Set<DelitoIph> delitoIphs=new HashSet<DelitoIph>(0);
			for (DelitoIphDTO dto : src.getDelitoIph()) {
				
//				CatDelito catDelito=new CatDelito(dto.getCatDelito().getCatDelitoId());
				
				DelitoIphId id=new DelitoIphId(dest.getInformePolicialHomologadoId(), dto.getCatDelito().getCatDelitoId());
//				delitoIphs.add(new DelitoIph(id, catDelito, informePolicialHomologado));
				delitoIphs.add(new DelitoIph(id, null, null));
			}
			dest.setDelitoIphs(delitoIphs);
		}
		/*FALTAS*/
		if(src.getFaltaIph()!=null){
			Set<FaltaAdministrativaIph> faltaAdministrativaIphs=new HashSet<FaltaAdministrativaIph>();
			for (FaltaAdministrativaIphDTO dto : src.getFaltaIph()) {
				
//				CatFaltaAdministrativa catFaltaAdministrativa=new CatFaltaAdministrativa();
//				catFaltaAdministrativa.setCatFaltaAdministrativaId(dto.getCatFaltaAdministrativa().getCatFaltaAdministrativaId());
				FaltaAdministrativaIphId id=new FaltaAdministrativaIphId(dest.getInformePolicialHomologadoId(), dto.getCatFaltaAdministrativa().getCatFaltaAdministrativaId());
//				faltaAdministrativaIphs.add(new FaltaAdministrativaIph(id, catFaltaAdministrativa, informePolicialHomologado));
				faltaAdministrativaIphs.add(new FaltaAdministrativaIph(id, null, null));
			}
			dest.setFaltaAdministrativaIphs(faltaAdministrativaIphs);
		}
		
		dest.setAsunto(src.getAsunto());
	}

	public static InformePolicialHomologadoDTO tranformIPHSimple(
			InformePolicialHomologado iph) {
		InformePolicialHomologadoDTO resp = new InformePolicialHomologadoDTO();
		if(iph.getInformePolicialHomologadoId()!=null)
		resp.setInformePolicialHomologadoId(iph.getInformePolicialHomologadoId());
		if(iph.getExpediente()!=null)
			resp.setExpediente(ExpedienteTransformer.transformaExpediente(iph.getExpediente()));			
		if(iph.getHecho()!=null)
			resp.setHecho(HechoTransformer.transformarHecho(iph.getHecho()));
		if(iph.getFuncionarioDestinatario()!=null)
			resp.setFuncionarioDestinatario(FuncionarioTransformer.transformarFuncionario(iph.getFuncionarioDestinatario()));		
		if(iph.getFuncionarioElabora()!=null)
			resp.setFuncionarioElabora(FuncionarioTransformer.transformarFuncionario(iph.getFuncionarioElabora()));
		if(iph.getTipoParticipacion()!=null)
			resp.setTipoParticipacion(CatalogoTransformer.transformValor(iph.getTipoParticipacion()));		
		resp.setFechaCaptura(iph.getFechaCaptura());
		resp.setObjetivosGenerales(iph.getObjetivosGenerales());
		resp.setNumeroOficio(iph.getNumeroOficio());
		resp.setAnio(iph.getAnio());
		resp.setFolioIPH(iph.getFolioIPH());
		resp.setNumEcoTransporte(iph.getNumEcoTransporte());
		resp.setAsunto(iph.getAsunto());
		return resp;
	}
}
