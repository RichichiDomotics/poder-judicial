/**
 *
 * Nombre del Programa : InvolucradoTransformer.java                                    
 * Autor                            : Cesar Agustin                                               
 * Compania                    : Ultrasist                                                
 * Proyecto                      : NSJP                    Fecha: 05/04/2011 
 * Marca de cambio        : N/A                                                     
 * Descripcion General    : Clase para la conversion de datos DTO a los de entidad Invlolucrado                      
 * Programa Dependiente  :N/A                                                      
 * Programa Subsecuente :N/A                                                      
 * Cond. de ejecucion        :N/A                                                      
 * Dias de ejecucion          :N/A                             Horario: N/A       
 *                              MODIFICACIONES                                       
 *------------------------------------------------------------------------------           
 * Autor                       :N/A                                                           
 * Compania               :N/A                                                           
 * Proyecto                 :N/A                                   Fecha: N/A       
 * Modificacion           :N/A                                                           
 *------------------------------------------------------------------------------           
 */
package mx.gob.segob.nsjp.service.involucrado.impl.transform;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.institucion.Instituciones;
import mx.gob.segob.nsjp.comun.util.DateUtils;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.DelitoDTO;
import mx.gob.segob.nsjp.dto.involucrado.AliasInvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.BiometricoDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoViewDTO;
import mx.gob.segob.nsjp.dto.involucrado.MediaFiliacionDTO;
import mx.gob.segob.nsjp.dto.involucrado.PerfilDTO;
import mx.gob.segob.nsjp.dto.involucrado.SeniaParticularDTO;
import mx.gob.segob.nsjp.dto.involucrado.ServidorPublicoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.AliasInvolucrado;
import mx.gob.segob.nsjp.model.Biometrico;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoNacionalidad;
import mx.gob.segob.nsjp.model.InvolucradoOcupacion;
import mx.gob.segob.nsjp.model.MediaFiliacion;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Perfil;
import mx.gob.segob.nsjp.model.SeniaParticular;
import mx.gob.segob.nsjp.model.ServidorPublico;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoPersonaTransfromer;
import mx.gob.segob.nsjp.service.delito.impl.transform.DelitoTransfromer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.MedioDeContactoTransformer;
import mx.gob.segob.nsjp.service.persona.impl.transform.NombreDemograficoTransformer;

import org.apache.log4j.Logger;

/**
 * @author CesarAgustin
 * @version 1.0
 */
public class InvolucradoTransformer {
	/**
	 * Logger.
	 */
	private final static Logger logger = Logger
			.getLogger(InvolucradoTransformer.class);

	/**
	 * 
	 * @param involucrado
	 * @return
	 */
	public static InvolucradoDTO transformarInvolucrado(Involucrado involucrado) {

		InvolucradoDTO involucradoDto = new InvolucradoDTO();
		involucradoDto.setEsMismoDomicilio(involucrado.getEsMismoDomicilio());
		logger.debug("El valor de  EsMismoDomicilio:: " + involucrado.getEsMismoDomicilio());

		CalidadDTO calidadDto = new CalidadDTO();

		Calidad calidad = new Calidad();
		Funcionario funcionario = new Funcionario();
		involucradoDto.setElementoId(involucrado.getElementoId());
		involucradoDto.setFolioElemento(involucrado.getFolioElemento());
		involucradoDto.setMotivoComparecencia(involucrado
				.getMotivoComparecencia());
		involucradoDto.setEsDetenido(involucrado.getEsDetenido());
		involucradoDto.setEsServidor(involucrado.getEsServidor());
		involucradoDto.setCondicion(involucrado.getCondicion());
		involucradoDto.setDesconocido(involucrado.getDesconocido());
		involucradoDto.setTipoPersona(involucrado.getTipoPersona() != null
				&& involucrado.getTipoPersona().equals("Fisica") ? 1L : 0L);
		if (involucrado.getSituacionJuridica() != null
				&& involucrado.getSituacionJuridica().getValorId() != null)
			involucradoDto.setValorSituacionJuridica(new ValorDTO(involucrado
					.getSituacionJuridica().getValorId(), involucrado
					.getSituacionJuridica().getValor()));
		
		involucradoDto.setFolioIdentificacion(involucrado
				.getFolioIdentificacion());
		involucradoDto.setEsVivo(involucrado.getEsVivo() != null
				&& involucrado.getEsVivo() == true ? true : false);
		involucradoDto.setStrFechaCreacion(DateUtils.formatear(involucrado
				.getFechaCreacionElemento()));
		involucradoDto.setFechaCreacionElemento(involucrado
				.getFechaCreacionElemento());

		if (involucrado.getCalidad() != null
				&& involucrado.getCalidad().getTipoCalidad() != null) {
			calidad = involucrado.getCalidad();
			calidadDto.setCalidadId(calidad.getCalidadId());
			calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad()
					.getValorId(), calidad.getTipoCalidad().getValor()));
			calidadDto.setCalidades(Calidades.getByValor(calidad.getTipoCalidad()
					.getValorId()));
			calidadDto.setDescripcionEstadoFisico(calidad
					.getDescripcionEstadoFisico());
		}
		if (involucrado.getExpediente() != null) {
			involucradoDto.setExpedienteDTO(ExpedienteTransformer
					.transformarExpedienteBasico(involucrado.getExpediente()));
		}

		if (involucrado.getTipoDoctoId() != null) {
			involucradoDto.setValorIdIdentificaion(new ValorDTO(involucrado
					.getTipoDoctoId().getValorId()));
		}

		if (involucrado.getFuncionario() != null) {
			funcionario = involucrado.getFuncionario();
			involucradoDto.setFuncionario(FuncionarioTransformer
					.transformarFuncionario(funcionario));
		}
		if (involucrado.getIdioma() != null
				&& involucrado.getIdioma().getValorId() != null)
			involucradoDto.setValorIdIdioma(new ValorDTO(involucrado
					.getIdioma().getValorId(), involucrado.getIdioma()
					.getValor()));

		if (involucrado.getReligion() != null
				&& involucrado.getReligion().getValorId() != null)
			involucradoDto.setValorIdReligion(new ValorDTO(involucrado
					.getReligion().getValorId(), involucrado.getReligion()
					.getValor()));

		if (involucrado.getEstadoCivil() != null
				&& involucrado.getEstadoCivil().getValorId() != null)
			involucradoDto.setValorIdEstadoCivil(new ValorDTO(involucrado
					.getEstadoCivil().getValorId(), involucrado
					.getEstadoCivil().getValor()));

		if (involucrado.getEscolaridad() != null
				&& involucrado.getEscolaridad().getValorId() != null)
			involucradoDto.setValorIdEscolaridad(new ValorDTO(involucrado
					.getEscolaridad().getValorId(), involucrado
					.getEscolaridad().getValor()));

		if (involucrado.getDetencions() != null
				&& !involucrado.getDetencions().isEmpty()) {
			Set<Detencion> hashdetencion = (Set<Detencion>) involucrado
					.getDetencions();
			List<DetencionDTO> detDto = new ArrayList<DetencionDTO>();
			for (Detencion det : hashdetencion)
				detDto.add(DetencionTransformer.transformarDetencionBasico(det));

			involucradoDto.setDetenciones(detDto);
		}
		if (involucrado.getPerfils() != null
				&& !involucrado.getPerfils().isEmpty()) {
			Set<Perfil> hashperfil = (HashSet<Perfil>) involucrado.getPerfils();
			List<PerfilDTO> perfDto = new ArrayList<PerfilDTO>();
			for (Perfil per : hashperfil)
				perfDto.add(PerfilTransformer.transformarPerfil(per));

			involucradoDto.setPerfiles(perfDto);
		}

		if (involucrado.getBiometricos() != null
				&& !involucrado.getBiometricos().isEmpty()) {
			Set<Biometrico> hashbiomt = (HashSet<Biometrico>) involucrado
					.getBiometricos();
			List<BiometricoDTO> biolst = new ArrayList<BiometricoDTO>();
			for (Biometrico biom : hashbiomt)
				biolst.add(BiometricoTransformer.transfomarBiometrico(biom));

			involucradoDto.setBiometricosDTO(biolst);
		}

		if (involucrado.getAliasInvolucrados() != null
				&& !involucrado.getAliasInvolucrados().isEmpty()) {
			Set<AliasInvolucrado> hashaliasinv = involucrado
					.getAliasInvolucrados();
			List<AliasInvolucradoDTO> aliasInvList = new ArrayList<AliasInvolucradoDTO>();
			for (AliasInvolucrado alinv : hashaliasinv)
				aliasInvList.add(AliasInvolucradoTransfromer
						.trasnformarAliasInv(alinv));

			involucradoDto.setAliasInvolucradoDTO(aliasInvList);
		}

		if (involucrado.getInvolucradoOcupacions() != null
				&& !involucrado.getInvolucradoOcupacions().isEmpty()) {
			Set<InvolucradoOcupacion> hashOcupacion = involucrado
					.getInvolucradoOcupacions();
			List<ValorDTO> ocupacionList = new ArrayList<ValorDTO>();
			for (InvolucradoOcupacion invocup : hashOcupacion)
				ocupacionList.add(InvolucradoOcupacionTransfromer
						.transformarInvolucradoOcupacion(invocup));

			involucradoDto.setValorIdOcupacion(ocupacionList);
		}

		if (involucrado.getInvolucradoNacionalidads() != null
				&& !involucrado.getInvolucradoNacionalidads().isEmpty()) {
			Set<InvolucradoNacionalidad> hashNacionalidad = involucrado
					.getInvolucradoNacionalidads();
			List<ValorDTO> nacionalidadList = new ArrayList<ValorDTO>();
			for (InvolucradoNacionalidad invonac : hashNacionalidad)
				nacionalidadList.add(InvolucradoNacionalidadTransfromer
						.transformarInvolucradoNacionalidad(invonac));

			involucradoDto.setValorIdNacionalidad(nacionalidadList);
		}

		if (involucrado.getDelitosCometidos() != null
				&& !involucrado.getDelitosCometidos().isEmpty()) {
			involucradoDto.setDelitosCometidos(new ArrayList<DelitoDTO>());
			for (DelitoPersona delito : involucrado.getDelitosCometidos()) {
				involucradoDto.getDelitosCometidos()
						.add(DelitoTransfromer.transformarDelito(delito
								.getDelito()));
			}
		}

		if (involucrado.getNombreDemograficos() != null
				&& !involucrado.getNombreDemograficos().isEmpty()) {
			involucradoDto
					.setNombresDemograficoDTO(new ArrayList<NombreDemograficoDTO>());
			for (NombreDemografico nombre : involucrado.getNombreDemograficos()) {
				NombreDemograficoDTO nombreDTO = NombreDemograficoTransformer
						.transformarNombreDemografico(nombre);
				involucradoDto.getNombresDemograficoDTO().add(nombreDTO);
			}

		}

		if (involucrado.getInstitucionPresenta() != null) {
			ConfInstitucionDTO inst = new ConfInstitucionDTO();
			ConfInstitucion ins = involucrado.getInstitucionPresenta();
			inst.setClave(ins.getClave());
			inst.setConfInstitucionId(ins.getConfInstitucionId());
			inst.setEsInstalacionActual(ins.getEsInstalacionActual());
			inst.setNombreInst(ins.getNombreInst());
			inst.setUrlInst(ins.getUrlInst());
			involucradoDto.setInstitucionPresenta(inst);
		}

		// MEDIOS DE CONTACTO
		if (involucrado.getMedioDeContactos() != null) {
			Iterator<MedioDeContacto> it = involucrado.getMedioDeContactos()
					.iterator();
			List<CorreoElectronicoDTO> correosDTO = new ArrayList<CorreoElectronicoDTO>();
			List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();
			while (it.hasNext()) {
				MedioDeContacto medCont = (MedioDeContacto) it.next();
				if (medCont instanceof CorreoElectronico) {
					correosDTO.add(new CorreoElectronicoDTO(
							((CorreoElectronico) medCont)
									.getDireccionElectronica()));
				} else if (medCont instanceof Telefono) {
					telefonosDTO.add(new TelefonoDTO(((Telefono) medCont)
							.getCodigoPais(), ((Telefono) medCont)
							.getCodigoArea(), ((Telefono) medCont)
							.getNumeroTelefonico(), 
							new ValorDTO(((Telefono) medCont).getValor().getValorId(), ((Telefono) medCont).getValor().getValor())));
				}
			}

			involucradoDto.setCorreosDTO(correosDTO);
			involucradoDto.setTelefonosDTO(telefonosDTO);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("calidadDto :: " + calidadDto);
		}

		involucradoDto.setCalidadDTO(calidadDto);
		
		involucradoDto.setEsActivo(involucrado.getEsActivo()==null?true:involucrado.getEsActivo());
		involucradoDto.setEsProtegido(involucrado.getEsProtegido());
		return involucradoDto;

	}

	/**
	 * 
	 * @param involucrados
	 * @return
	 */
	public static List<InvolucradoDTO> transformarInvolucrado(
			List<Involucrado> involucrados) {
		List<InvolucradoDTO> involucradosDto = new ArrayList<InvolucradoDTO>();

		for (Involucrado involucrado : involucrados) {
			InvolucradoDTO involucradoDto = new InvolucradoDTO();
			CalidadDTO calidadDto = new CalidadDTO();
			Funcionario funcionario = new Funcionario();
			Calidad calidad = new Calidad();

			involucradoDto.setElementoId(involucrado.getElementoId());
			involucradoDto.setFolioElemento(involucrado.getFolioElemento());
			involucradoDto.setMotivoComparecencia(involucrado
					.getMotivoComparecencia());
			involucradoDto.setEsDetenido(involucrado.getEsDetenido());
			involucradoDto.setEsServidor(involucrado.getEsServidor());
			involucradoDto.setCondicion(involucrado.getCondicion());
			involucradoDto.setDesconocido(involucrado.getDesconocido());
			involucradoDto.setTipoPersona(involucrado.getTipoPersona() != null
					&& involucrado.getTipoPersona().equals("Fisica") ? 1L : 0L);
			if (involucrado.getSituacionJuridica() != null
					&& involucrado.getSituacionJuridica().getValorId() != null)
				involucradoDto.setValorSituacionJuridica(new ValorDTO(
						involucrado.getSituacionJuridica().getValorId()));

			involucradoDto.setFolioIdentificacion(involucrado
					.getFolioIdentificacion());
			involucradoDto.setEsVivo(involucrado.getEsVivo() != null
					&& involucrado.getEsVivo() == true ? true : false);
			involucradoDto.setFechaCreacionElemento(involucrado
					.getFechaCreacionElemento());

			if (involucrado.getCalidad() != null
					&& involucrado.getCalidad().getTipoCalidad() != null) {
				calidad = involucrado.getCalidad();

				calidadDto.setCalidadId(calidad.getCalidadId());
				calidadDto.setValorIdCalidad(new ValorDTO(calidad
						.getTipoCalidad().getValorId(), calidad
						.getTipoCalidad().getValor()));
				calidadDto.setDescripcionEstadoFisico(calidad
						.getDescripcionEstadoFisico());
			}

			if (involucrado.getExpediente() != null) {
				involucradoDto.setExpedienteDTO(ExpedienteTransformer
						.transformaExpediente(involucrado.getExpediente()));
			}

			if (involucrado.getFuncionario() != null) {
				funcionario = involucrado.getFuncionario();
				involucradoDto.setFuncionario(FuncionarioTransformer
						.transformarFuncionario(funcionario));
			}
			if (involucrado.getIdioma() != null
					&& involucrado.getIdioma().getValorId() != null)
				involucradoDto.setValorIdIdioma(new ValorDTO(involucrado
						.getIdioma().getValorId()));

			if (involucrado.getReligion() != null
					&& involucrado.getReligion().getValorId() != null)
				involucradoDto.setValorIdReligion(new ValorDTO(involucrado
						.getReligion().getValorId()));

			if (involucrado.getEstadoCivil() != null
					&& involucrado.getEstadoCivil().getValorId() != null)
				involucradoDto.setValorIdEstadoCivil(new ValorDTO(involucrado
						.getEstadoCivil().getValorId()));

			if (involucrado.getEscolaridad() != null
					&& involucrado.getEscolaridad().getValorId() != null)
				involucradoDto.setValorIdEscolaridad(new ValorDTO(involucrado
						.getEscolaridad().getValorId()));

			if (involucrado.getDetencions() != null
					&& !involucrado.getDetencions().isEmpty()) {
				Set<Detencion> hashdetencion = involucrado.getDetencions();
				List<DetencionDTO> detDto = new ArrayList<DetencionDTO>();
				for (Detencion det : hashdetencion)
					detDto.add(DetencionTransformer.transformarDetencion(det));

				involucradoDto.setDetenciones(detDto);
			}
			if (involucrado.getPerfils() != null
					&& !involucrado.getPerfils().isEmpty()) {
				Set<Perfil> hashperfil = involucrado.getPerfils();
				List<PerfilDTO> perfDto = new ArrayList<PerfilDTO>();
				for (Perfil per : hashperfil)
					perfDto.add(PerfilTransformer.transformarPerfil(per));

				involucradoDto.setPerfiles(perfDto);
			}

			if (involucrado.getBiometricos() != null
					&& !involucrado.getBiometricos().isEmpty()) {
				Set<Biometrico> hashbiomt = involucrado.getBiometricos();
				List<BiometricoDTO> biolst = new ArrayList<BiometricoDTO>();
				for (Biometrico biom : hashbiomt)
					biolst.add(BiometricoTransformer.transfomarBiometrico(biom));

				involucradoDto.setBiometricosDTO(biolst);
			}

			if (involucrado.getAliasInvolucrados() != null
					&& !involucrado.getAliasInvolucrados().isEmpty()) {
				Set<AliasInvolucrado> hashaliasinv = involucrado
						.getAliasInvolucrados();
				List<AliasInvolucradoDTO> aliasInvList = new ArrayList<AliasInvolucradoDTO>();
				for (AliasInvolucrado alinv : hashaliasinv)
					aliasInvList.add(AliasInvolucradoTransfromer
							.trasnformarAliasInv(alinv));

				involucradoDto.setAliasInvolucradoDTO(aliasInvList);
			}

			if (involucrado.getInvolucradoOcupacions() != null
					&& !involucrado.getInvolucradoOcupacions().isEmpty()) {
				Set<InvolucradoOcupacion> hashOcupacion = involucrado
						.getInvolucradoOcupacions();
				List<ValorDTO> ocupacionList = new ArrayList<ValorDTO>();
				for (InvolucradoOcupacion invocup : hashOcupacion)
					ocupacionList.add(InvolucradoOcupacionTransfromer
							.transformarInvolucradoOcupacion(invocup));

				involucradoDto.setValorIdOcupacion(ocupacionList);
			}

			if (involucrado.getInvolucradoNacionalidads() != null
					&& !involucrado.getInvolucradoNacionalidads().isEmpty()) {
				Set<InvolucradoNacionalidad> hashNacionalidad = involucrado
						.getInvolucradoNacionalidads();
				List<ValorDTO> nacionalidadList = new ArrayList<ValorDTO>();
				for (InvolucradoNacionalidad invonac : hashNacionalidad)
					nacionalidadList.add(InvolucradoNacionalidadTransfromer
							.transformarInvolucradoNacionalidad(invonac));

				involucradoDto.setValorIdOcupacion(nacionalidadList);
			}

			if (involucrado.getDelitosCometidos() != null
					&& !involucrado.getDelitosCometidos().isEmpty()) {
				Set<Detencion> hashdetencion = involucrado.getDetencions();
				List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
				for (DelitoPersona dp : involucrado.getDelitosCometidos())
					delitosDTO.add(DelitoPersonaTransfromer
							.transformarDelitoPersona(dp));
				involucradoDto.setDelitosCometidos(delitosDTO);
			}

			involucradoDto.setCalidadDTO(calidadDto);
			involucradosDto.add(involucradoDto);
			involucradoDto.setEsProtegido(involucrado.getEsProtegido());
		}
		return involucradosDto;
	}

	/**
	 * 
	 * @param involucrados
	 * @return
	 */
	public static InvolucradoDTO transformarInvolucradoBasico(
			Involucrado involucrado) {
		if (involucrado == null) {
			return null;
		}
		InvolucradoDTO involucradoDto = new InvolucradoDTO();
		CalidadDTO calidadDto = new CalidadDTO();
		List<NombreDemograficoDTO> listNomDTO = new ArrayList<NombreDemograficoDTO>();

		if (involucrado.getExpediente() != null)
			involucradoDto.setExpedienteDTO(ExpedienteTransformer
					.transformarExpedienteBasico(involucrado.getExpediente()));

		involucradoDto.setElementoId(involucrado.getElementoId());
		involucradoDto.setFolioElemento(involucrado.getFolioElemento());
		involucradoDto.setFolioIdentificacion(involucrado
				.getFolioIdentificacion());
		involucradoDto.setEsDetenido(involucrado.getEsDetenido());
		involucradoDto.setCondicion(involucrado.getCondicion());
		involucradoDto.setTipoPersona(involucrado.getTipoPersona() != null
                && involucrado.getTipoPersona().equals("Fisica") ? 1L : 0L);

		if (involucrado.getCalidad() != null
				&& involucrado.getCalidad().getTipoCalidad() != null) {
			Calidad calidad = involucrado.getCalidad();
			calidadDto.setCalidadId(calidad.getCalidadId());
			calidadDto.setValorIdCalidad(new ValorDTO(calidad.getTipoCalidad()
					.getValorId(), calidad.getTipoCalidad().getValor()));
			calidadDto.setCalidades(Calidades.getByValor(calidad.getTipoCalidad()
					.getValorId()));
			calidadDto.setDescripcionEstadoFisico(calidad
					.getDescripcionEstadoFisico());
		}

		if (involucrado.getNombreDemograficos() != null) {
			for (NombreDemografico nombre : involucrado.getNombreDemograficos()) {
				NombreDemograficoDTO nombreDTO = NombreDemograficoTransformer
						.transformarNombreDemografico(nombre);
				listNomDTO.add(nombreDTO);
			}
			involucradoDto.setNombresDemograficoDTO(listNomDTO);
		}

		involucradoDto.setCalidadDTO(calidadDto);

		Iterator<DelitoPersona> delitos = involucrado.getDelitosCometidos()
				.iterator();
		List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
		while (delitos.hasNext()) {
			DelitoPersona delitoPersona = (DelitoPersona) delitos.next();
			delitosDTO.add(DelitoTransfromer.transformarDelito(delitoPersona
					.getDelito()));
		}
		involucradoDto.setDelitosCometidos(delitosDTO);

		involucradoDto.setEsActivo(involucrado.getEsActivo()==null?true:involucrado.getEsActivo());
		involucradoDto.setEsProtegido(involucrado.getEsProtegido());
		return involucradoDto;
	}

	/**
	 * 
	 * @param involucradoDTO
	 * @return
	 */
	public static Involucrado transformarInvolucrado(
			InvolucradoDTO involucradoDTO) {

		Involucrado involucrado = new Involucrado();
		
		involucrado.setEsMismoDomicilio(involucradoDTO.getEsMismoDomicilio());
		
		involucrado.setFolioElemento(involucradoDTO.getFolioElemento());
		// Atributos Involucrado
		if (involucradoDTO.getValorSituacionJuridica() != null
				&& involucradoDTO.getValorSituacionJuridica().getIdCampo() != null)
			involucrado.setSituacionJuridica(new Valor(involucradoDTO
					.getValorSituacionJuridica().getIdCampo()));

		if (involucradoDTO.getValorIdEscolaridad() != null
				&& involucradoDTO.getValorIdEscolaridad().getIdCampo() != null)
			involucrado.setEscolaridad(new Valor(involucradoDTO
					.getValorIdEscolaridad().getIdCampo()));

		if (involucradoDTO.getValorIdEstadoCivil() != null
				&& involucradoDTO.getValorIdEstadoCivil().getIdCampo() != null)
			involucrado.setEstadoCivil(new Valor(involucradoDTO
					.getValorIdEstadoCivil().getIdCampo()));

		if (involucradoDTO.getValorIdIdioma() != null
				&& involucradoDTO.getValorIdIdioma().getIdCampo() != null)
			involucrado.setIdioma(new Valor(involucradoDTO.getValorIdIdioma()
					.getIdCampo()));

		if (involucradoDTO.getValorIdReligion() != null
				&& involucradoDTO.getValorIdReligion().getIdCampo() != null)
			involucrado.setReligion(new Valor(involucradoDTO
					.getValorIdReligion().getIdCampo()));

		if (involucradoDTO.getCalidadDTO() != null) {
			Calidad calidad = new Calidad(involucradoDTO.getCalidadDTO()
					.getCalidadId());
			calidad.setDescripcionEstadoFisico(involucradoDTO.getCalidadDTO()
					.getDescripcionEstadoFisico());
			if (involucradoDTO.getCalidadDTO().getCalidades() != null) {
				calidad.setTipoCalidad(new Valor(involucradoDTO.getCalidadDTO().getCalidades().getValorId()));
			}
			involucrado.setCalidad(calidad);
		}

		involucrado.setMotivoComparecencia(involucradoDTO
				.getMotivoComparecencia());
		if (involucradoDTO.getEsServidor() != null)
			involucrado.setEsServidor(involucradoDTO.getEsServidor());
		else
			involucrado.setEsServidor(false);
		involucrado.setEsDetenido(involucradoDTO.getEsDetenido());
		involucrado.setCondicion(involucradoDTO.getCondicion());
		involucrado.setTipoPersona(involucradoDTO.getTipoPersona() != null
				&& involucradoDTO.getTipoPersona().equals(1L) ? "Fisica"
				: "Moral");
		involucrado.setDesconocido(involucradoDTO.getDesconocido());

		// Atributo Persona
		if (involucradoDTO.getValorIdIdentificaion() != null
				&& involucradoDTO.getValorIdIdentificaion().getIdCampo() != null)
			involucrado.setTipoDoctoId(new Valor(involucradoDTO
					.getValorIdIdentificaion().getIdCampo()));

		involucrado.setEsVivo(involucradoDTO.getEsVivo() != null
				&& involucradoDTO.getEsVivo().equals(true) ? true : false);
		involucrado.setFolioIdentificacion(involucradoDTO
				.getFolioIdentificacion() != null ? involucradoDTO
				.getFolioIdentificacion() : null);

		// Obtener Expediente
		if (involucradoDTO.getExpedienteDTO() != null)
			involucrado.setExpediente(new Expediente(involucradoDTO
					.getExpedienteDTO().getExpedienteId()));

		// Atributos elemento
		involucrado.setTipoElemento(new Valor(Elementos.PERSONA.getValorId()));
		involucrado.setFechaCreacionElemento(involucradoDTO
				.getFechaCreacionElemento());

		if (involucradoDTO.getElementoId() != null
				&& involucradoDTO.getElementoId() > 0)
			involucrado.setElementoId(involucradoDTO.getElementoId());

		if (involucradoDTO.getAliasInvolucradoDTO() != null
				&& involucradoDTO.getAliasInvolucradoDTO().size() > 0) {
			Set<AliasInvolucrado> colAliasInvolucrado = new HashSet<AliasInvolucrado>();
			for (AliasInvolucradoDTO aliasInvDTO : involucradoDTO
					.getAliasInvolucradoDTO()) {
				AliasInvolucrado aliasInv = AliasInvolucradoTransfromer
						.transformarAliasInv(aliasInvDTO);
				aliasInv.setInvolucrado(involucrado);
				colAliasInvolucrado.add(aliasInv);
			}
			involucrado.setAliasInvolucrados(colAliasInvolucrado);
		}

		if (involucradoDTO.getDetenciones() != null
				&& involucradoDTO.getDetenciones().size() > 0) {
			Set<Detencion> colDetencion = new HashSet<Detencion>();
			for (DetencionDTO detencionDTO : involucradoDTO.getDetenciones()) {
				Detencion detencion = DetencionTransformer
						.transformarDetencion(detencionDTO);
				detencion.setInvolucrado(involucrado);
				colDetencion.add(detencion);
			}
			involucrado.setDetencions(colDetencion);
		}

		if (involucradoDTO.getBiometricosDTO() != null
				&& involucradoDTO.getBiometricosDTO().size() > 0) {
			Set<Biometrico> colBiometrico = new HashSet<Biometrico>();
			for (BiometricoDTO biometricoDTO : involucradoDTO
					.getBiometricosDTO()) {
				Biometrico biometrico = BiometricoTransformer
						.transfomarBiometrico(biometricoDTO);
				biometrico.setInvolucrado(involucrado);
				colBiometrico.add(biometrico);
			}
			involucrado.setBiometricos(colBiometrico);
		}

		if (involucradoDTO.getPerfiles() != null
				&& involucradoDTO.getPerfiles().size() > 0) {
			Set<Perfil> colPerfil = new HashSet<Perfil>();
			for (PerfilDTO perfilDTO : involucradoDTO.getPerfiles()) {
				Perfil perfil = PerfilTransformer.transformarPerfil(perfilDTO);
				perfil.setInvolucrado(involucrado);
				colPerfil.add(perfil);
			}
			involucrado.setPerfils(colPerfil);
		}

		if (involucradoDTO.getNombresDemograficoDTO() != null
				&& involucradoDTO.getNombresDemograficoDTO().size() > 0) {
			Set<NombreDemografico> colNombreDemografico = new HashSet<NombreDemografico>();
			for (NombreDemograficoDTO nomDemograficoDTO : involucradoDTO
					.getNombresDemograficoDTO()) {
				NombreDemografico nombreDemografico = NombreDemograficoTransformer
						.transformarNombreDemografico(nomDemograficoDTO);
				nombreDemografico.setPersona(involucrado);
				colNombreDemografico.add(nombreDemografico);
			}
			involucrado.setNombreDemograficos(colNombreDemografico);
		}

		if (involucradoDTO.getTelefonosDTO() != null
				&& involucradoDTO.getCorreosDTO() != null) {
			Set<MedioDeContacto> colMedioContacto = new HashSet<MedioDeContacto>();
			for (TelefonoDTO telefonoDTO : involucradoDTO.getTelefonosDTO()) {
				Telefono telefono = MedioDeContactoTransformer
						.transformarTelefono(telefonoDTO);
				telefono.setPersona(involucrado);
				colMedioContacto.add(telefono);
			}
			for (CorreoElectronicoDTO correoDTO : involucradoDTO
					.getCorreosDTO()) {
				CorreoElectronico correo = MedioDeContactoTransformer
						.transformarCorreo(correoDTO);
				correo.setPersona(involucrado);
				colMedioContacto.add(correo);
			}
			involucrado.setMedioDeContactos(colMedioContacto);
		}

		involucrado.setEsActivo(involucradoDTO.getEsActivo()==null?true:involucradoDTO.getEsActivo());
		involucrado.setEsProtegido(involucradoDTO.getEsProtegido());
		return involucrado;

	}

	public static Involucrado involucradoUpdate(Involucrado involucrado,
			Involucrado involucradoMod) {
		// Atributos Involucrado
		involucrado.setFechaCreacionElemento((involucradoMod
				.getFechaCreacionElemento() != null) ? involucradoMod
				.getFechaCreacionElemento() : involucrado
				.getFechaCreacionElemento());

		if (involucradoMod.getSituacionJuridica() != null)
			involucrado.setSituacionJuridica(involucradoMod
					.getSituacionJuridica());

		if (involucradoMod.getEscolaridad() != null)
			involucrado.setEscolaridad(involucradoMod.getEscolaridad());

		if (involucradoMod.getEstadoCivil() != null)
			involucrado.setEstadoCivil(involucradoMod.getEstadoCivil());

		if (involucradoMod.getIdioma() != null)
			involucrado.setIdioma(involucradoMod.getIdioma());

		if (involucradoMod.getReligion() != null)
			involucrado.setReligion(involucradoMod.getReligion());

		if (involucradoMod.getCalidad() != null)
			involucrado.setCalidad(involucradoMod.getCalidad());

		if (involucradoMod.getServidorPublico() != null)
			involucrado.setServidorPublico(involucradoMod.getServidorPublico());

		involucrado.setMotivoComparecencia(involucradoMod
				.getMotivoComparecencia());
		involucrado.setEsServidor(involucradoMod.getEsServidor());
		involucrado.setEsDetenido(involucradoMod.getEsDetenido());
		involucrado.setCondicion(involucradoMod.getCondicion());
		involucrado.setTipoPersona(involucradoMod.getTipoPersona());
		involucrado.setDesconocido(involucradoMod.getDesconocido());

		// Atributo Persona
		if (involucradoMod.getTipoDoctoId() != null)
			involucrado.setTipoDoctoId(involucradoMod.getTipoDoctoId());

		involucrado.setEsVivo(involucradoMod.getEsVivo());
		involucrado.setFolioIdentificacion(involucradoMod
				.getFolioIdentificacion());

		// Obtener Expediente
		if (involucradoMod.getExpediente() != null)
			involucrado.setExpediente(involucradoMod.getExpediente());

		// Atributos elemento
		involucrado.setTipoElemento(new Valor(Elementos.PERSONA.getValorId()));

		if (involucradoMod.getAliasInvolucrados() != null)
			involucrado.setAliasInvolucrados(involucradoMod
					.getAliasInvolucrados());

		if (involucradoMod.getDetencions() != null)
			involucrado.setDetencions(involucradoMod.getDetencions());

		if (involucradoMod.getBiometricos() != null)
			involucrado.setBiometricos(involucradoMod.getBiometricos());

		if (involucradoMod.getPerfils() != null)
			involucrado.setPerfils(involucradoMod.getPerfils());

		if (involucradoMod.getInvolucradoOcupacions() != null)
			involucrado.setInvolucradoOcupacions(involucradoMod
					.getInvolucradoOcupacions());

		if (involucradoMod.getInvolucradoNacionalidads() != null)
			involucrado.setInvolucradoNacionalidads(involucradoMod
					.getInvolucradoNacionalidads());
		
		if (involucradoMod.getNombreDemograficos() != null)
			involucrado.setNombreDemograficos(involucradoMod
					.getNombreDemograficos());
		
	

		if (involucradoMod.getMedioDeContactos() != null)
			involucrado.setMedioDeContactos(involucradoMod
					.getMedioDeContactos());

		involucrado.setEsActivo(involucradoMod.getEsActivo()==null?true:involucradoMod.getEsActivo());
		involucrado.setEsProtegido(involucradoMod.getEsProtegido());
		return involucrado;
	}

	public static InvolucradoViewDTO transformarInvolucradoView(
			InvolucradoDTO inv) {
		InvolucradoViewDTO involucrado = new InvolucradoViewDTO();

		involucrado.setInvolucradoId(inv.getElementoId());
		if( inv.getTipoPersona().equals(0L)){
			if(inv.getOrganizacionDTO()!= null){
				involucrado.setNombreCompleto(inv.getOrganizacionDTO().getNombreOrganizacion());
			}
			
		}else{
			if(inv.getNombresDemograficoDTO() != null && inv.getNombresDemograficoDTO().size()>0){
				NombreDemograficoDTO nd = inv.getNombresDemograficoDTO().get(0);
				involucrado.setNombre(nd.getNombre());
				involucrado.setApellidoPaterno(nd.getApellidoPaterno());
				involucrado.setApellidoMaterno(nd.getApellidoMaterno());
				involucrado.setNombreCompleto(nd.getNombre() +" " 
						+ nd.getApellidoPaterno() +" " +nd.getApellidoMaterno() );
			}
		}
			
		String delitos = "";
		List<DelitoDTO> delitosDTO = new ArrayList<DelitoDTO>();
		if (inv.getDelitosCometidos() != null
				&& !inv.getDelitosCometidos().isEmpty()) {
			delitosDTO.addAll(inv.getDelitosCometidos());
			involucrado.setDelitosCometidos(delitosDTO);
		}

		if (!delitos.equals("")) {
			delitos = delitos.substring(0, delitos.lastIndexOf(", "));
		}

		involucrado.setDelitos(delitos);

		if (inv.getEsDetenido() != null && inv.getEsDetenido()) {
			involucrado.setDetenido("SI");
		} else {
			involucrado.setDetenido(" ");
		}

		if (inv.getCalidadDTO().getCalidadId() == Calidades.TESTIGO
				.getValorId()) {
			involucrado.setNombreInstitucion(inv.getInstitucionPresenta()
					.getNombreInst());
		} else {
			involucrado.setNombreInstitucion("");
		}

		if (inv.getCalidadDTO().getCalidadId() == Calidades.REPRESENTANTE_LEGAL
				.getValorId()) {
			involucrado.setCalidad(inv.getOrganizacionDTO().getCalidadDTO()
//					.getCalidades().getValorId().toString());
					.getValorIdCalidad().getValor());
			involucrado.setIdCalidadTipoEspecialidad(inv.getOrganizacionDTO().getCalidadDTO()
					.getCalidades().getValorId());
		} else {
			involucrado.setCalidad(inv.getCalidadDTO()
//					.getCalidades().getValorId().toString());
					.getValorIdCalidad().getValor());
			involucrado.setIdCalidadTipoEspecialidad(inv.getCalidadDTO()
					.getCalidades().getValorId());
		}

		involucrado.setEsActivo(inv.getEsActivo()==null?true:inv.getEsActivo());
		involucrado.setEsProtegido(inv.getEsProtegido());

		return involucrado;
	}

	/**
	 * 
	 * @param servidorPublicoDTO
	 * @return
	 */
	public static ServidorPublico transformarServidorPublico(
			ServidorPublicoDTO servidorPublicoDTO) {

		ServidorPublico servidorPublico = new ServidorPublico();

		servidorPublico.setDependencia(servidorPublicoDTO.getDependencia());
		servidorPublico.setNumeroEmpleado(servidorPublicoDTO
				.getNumeroEmpleado());
		servidorPublico.setPuesto(servidorPublicoDTO.getPuesto());

		if (servidorPublicoDTO.getNivelDependencia() != null
				&& servidorPublicoDTO.getNivelDependencia().getIdCampo() != null)
			servidorPublico.setNivelDependencia(new Valor(servidorPublicoDTO
					.getNivelDependencia().getIdCampo()));

		if (servidorPublicoDTO.getTipoDependencia() != null
				&& servidorPublicoDTO.getTipoDependencia().getIdCampo() != null)
			servidorPublico.setTipoDependencia(new Valor(servidorPublicoDTO
					.getTipoDependencia().getIdCampo()));

		return servidorPublico;
	}

	/**
	 * 
	 * @param servidorPublico
	 * @return
	 */
	public static ServidorPublicoDTO transformarServidorPublico(
			ServidorPublico servidorPublico) {
		if (servidorPublico == null) {
			return null;
		}
		ServidorPublicoDTO servidorPublicoDTO = new ServidorPublicoDTO();

		servidorPublicoDTO.setServidorPublicoId(servidorPublico
				.getServidorPublicoId());
		servidorPublicoDTO.setDependencia(servidorPublico.getDependencia());
		servidorPublicoDTO.setNumeroEmpleado(servidorPublico
				.getNumeroEmpleado());
		servidorPublicoDTO.setPuesto(servidorPublico.getPuesto());

		if (servidorPublico.getNivelDependencia() != null
				&& servidorPublico.getNivelDependencia().getValorId() != null)
			servidorPublicoDTO.setNivelDependencia(new ValorDTO(servidorPublico
					.getNivelDependencia().getValorId()));

		if (servidorPublico.getTipoDependencia() != null
				&& servidorPublico.getTipoDependencia().getValorId() != null)
			servidorPublicoDTO.setTipoDependencia(new ValorDTO(servidorPublico
					.getTipoDependencia().getValorId()));

		return servidorPublicoDTO;
	}

	/**
	 * 
	 * @param servidorPublico
	 * @param servidorPublicoDTO
	 * @return
	 */
	public static ServidorPublico transformarServidorPublicoUpdate(
			ServidorPublico servidorPublico,
			ServidorPublicoDTO servidorPublicoDTO) {

		servidorPublico.setDependencia(servidorPublicoDTO.getDependencia());
		servidorPublico.setNumeroEmpleado(servidorPublicoDTO
				.getNumeroEmpleado());
		servidorPublico.setPuesto(servidorPublicoDTO.getPuesto());

		if (servidorPublicoDTO.getNivelDependencia() != null
				&& servidorPublicoDTO.getNivelDependencia().getIdCampo() != null)
			servidorPublico.setNivelDependencia(new Valor(servidorPublicoDTO
					.getNivelDependencia().getIdCampo()));

		if (servidorPublicoDTO.getTipoDependencia() != null
				&& servidorPublicoDTO.getTipoDependencia().getIdCampo() != null)
			servidorPublico.setTipoDependencia(new Valor(servidorPublicoDTO
					.getTipoDependencia().getIdCampo()));

		return servidorPublico;
	}

	/**
	 * Transforma un objeto mediaFiliacionDTO a una mediaFiliacion,
	 * 
	 * @param mediaFiliacionDTO
	 * @return mediaFiliacion
	 */
	public static MediaFiliacion transformarMediaFilacion(
			MediaFiliacionDTO mediaFiliacionDTO) {

		MediaFiliacion mediaFiliacion = InvolucradoTransformer
				.transformarMediaFilacionUpdate(null, mediaFiliacionDTO);

		return mediaFiliacion;
	}

	/**
	 * Transforma un objeto mediaFiliacionDTO a una mediaFiliacion, En caso de
	 * que el parámetro mediaFiliacion sea null, es una transformacion normal.
	 * Si es diferente de null, se trata de un objeto de fue extraido de BD y
	 * contiene referencias a otras entidades. El objetivo es setear los valores
	 * que viene de la interfaz (presentación) y ser actualizado en BD.
	 * 
	 * @param mediaFiliacion
	 *            objeto a actualizar.
	 * @param mediaFiliacionDTO
	 * @return mediaFiliacion
	 */
	public static MediaFiliacion transformarMediaFilacionUpdate(
			MediaFiliacion mediaFiliacion, MediaFiliacionDTO mediaFiliacionDTO) {

		if (mediaFiliacion == null)
			mediaFiliacion = new MediaFiliacion();

		if (mediaFiliacionDTO.getMediaFiliacionId() != null)
			mediaFiliacion.setMediaFiliacionId(mediaFiliacionDTO
					.getMediaFiliacionId());

		mediaFiliacion.setEstatura(mediaFiliacionDTO.getEstatura());
		mediaFiliacion.setPeso(mediaFiliacionDTO.getPeso());
		mediaFiliacion.setPerfil(mediaFiliacionDTO.getPerfil());
		mediaFiliacion.setTieneBarba(mediaFiliacionDTO.getTieneBarba());
		mediaFiliacion.setTieneBigote(mediaFiliacionDTO.getTieneBigote());
		mediaFiliacion.setUsaLentes(mediaFiliacionDTO.getUsaLentes());
		mediaFiliacion.setFactorRH(mediaFiliacionDTO.getFactorRH());

		if (mediaFiliacionDTO.getTipoSangre() != null
				&& mediaFiliacionDTO.getTipoSangre().getIdCampo() != null)
			mediaFiliacion.setTipoSangre(new Valor(mediaFiliacionDTO
					.getTipoSangre().getIdCampo()));
		if (mediaFiliacionDTO.getFormaOreja() != null
				&& mediaFiliacionDTO.getFormaOreja().getIdCampo() != null)
			mediaFiliacion.setFormaOreja(new Valor(mediaFiliacionDTO
					.getFormaOreja().getIdCampo()));
		if (mediaFiliacionDTO.getTamanioCeja() != null
				&& mediaFiliacionDTO.getTamanioCeja().getIdCampo() != null)
			mediaFiliacion.setTamanioCeja(new Valor(mediaFiliacionDTO
					.getTamanioCeja().getIdCampo()));
		if (mediaFiliacionDTO.getTamanioOjo() != null
				&& mediaFiliacionDTO.getTamanioOjo().getIdCampo() != null)
			mediaFiliacion.setTamanioOjo(new Valor(mediaFiliacionDTO
					.getTamanioOjo().getIdCampo()));
		if (mediaFiliacionDTO.getFormaCeja() != null
				&& mediaFiliacionDTO.getFormaCeja().getIdCampo() != null)
			mediaFiliacion.setFormaCeja(new Valor(mediaFiliacionDTO
					.getFormaCeja().getIdCampo()));
		if (mediaFiliacionDTO.getAnchoNariz() != null
				&& mediaFiliacionDTO.getAnchoNariz().getIdCampo() != null)
			mediaFiliacion.setAnchoNariz(new Valor(mediaFiliacionDTO
					.getAnchoNariz().getIdCampo()));
		if (mediaFiliacionDTO.getEspesorLabioInf() != null
				&& mediaFiliacionDTO.getEspesorLabioInf().getIdCampo() != null)
			mediaFiliacion.setEspesorLabioInf(new Valor(mediaFiliacionDTO
					.getEspesorLabioInf().getIdCampo()));
		if (mediaFiliacionDTO.getOrejaTamanio() != null
				&& mediaFiliacionDTO.getOrejaTamanio().getIdCampo() != null)
			mediaFiliacion.setOrejaTamanio(new Valor(mediaFiliacionDTO
					.getOrejaTamanio().getIdCampo()));
		if (mediaFiliacionDTO.getCabelloImplantacion() != null
				&& mediaFiliacionDTO.getCabelloImplantacion().getIdCampo() != null)
			mediaFiliacion.setCabelloImplantacion(new Valor(mediaFiliacionDTO
					.getCabelloImplantacion().getIdCampo()));
		if (mediaFiliacionDTO.getColorOjos() != null
				&& mediaFiliacionDTO.getColorOjos().getIdCampo() != null)
			mediaFiliacion.setColorOjos(new Valor(mediaFiliacionDTO
					.getColorOjos().getIdCampo()));
		if (mediaFiliacionDTO.getFormaOjos() != null
				&& mediaFiliacionDTO.getFormaOjos().getIdCampo() != null)
			mediaFiliacion.setFormaOjos(new Valor(mediaFiliacionDTO
					.getFormaOjos().getIdCampo()));
		if (mediaFiliacionDTO.getFrenteAltura() != null
				&& mediaFiliacionDTO.getFrenteAltura().getIdCampo() != null)
			mediaFiliacion.setFrenteAltura(new Valor(mediaFiliacionDTO
					.getFrenteAltura().getIdCampo()));
		if (mediaFiliacionDTO.getRaizNariz() != null
				&& mediaFiliacionDTO.getRaizNariz().getIdCampo() != null)
			mediaFiliacion.setRaizNariz(new Valor(mediaFiliacionDTO
					.getRaizNariz().getIdCampo()));
		if (mediaFiliacionDTO.getFormaMenton() != null
				&& mediaFiliacionDTO.getFormaMenton().getIdCampo() != null)
			mediaFiliacion.setFormaMenton(new Valor(mediaFiliacionDTO
					.getFormaMenton().getIdCampo()));
		if (mediaFiliacionDTO.getCalvicieTipo() != null
				&& mediaFiliacionDTO.getCalvicieTipo().getIdCampo() != null)
			mediaFiliacion.setCalvicieTipo(new Valor(mediaFiliacionDTO
					.getCalvicieTipo().getIdCampo()));
		if (mediaFiliacionDTO.getInclinacionMenton() != null
				&& mediaFiliacionDTO.getInclinacionMenton().getIdCampo() != null)
			mediaFiliacion.setInclinacionMenton(new Valor(mediaFiliacionDTO
					.getInclinacionMenton().getIdCampo()));
		if (mediaFiliacionDTO.getCabelloCantidad() != null
				&& mediaFiliacionDTO.getCabelloCantidad().getIdCampo() != null)
			mediaFiliacion.setCabelloCantidad(new Valor(mediaFiliacionDTO
					.getCabelloCantidad().getIdCampo()));
		if (mediaFiliacionDTO.getAlturaNasoLabial() != null
				&& mediaFiliacionDTO.getAlturaNasoLabial().getIdCampo() != null)
			mediaFiliacion.setAlturaNasoLabial(new Valor(mediaFiliacionDTO
					.getAlturaNasoLabial().getIdCampo()));
		if (mediaFiliacionDTO.getBaseNariz() != null
				&& mediaFiliacionDTO.getBaseNariz().getIdCampo() != null)
			mediaFiliacion.setBaseNariz(new Valor(mediaFiliacionDTO
					.getBaseNariz().getIdCampo()));

		if (mediaFiliacionDTO.getDorsoNariz() != null
				&& mediaFiliacionDTO.getDorsoNariz().getIdCampo() != null)
			mediaFiliacion.setDorsoNariz(new Valor(mediaFiliacionDTO
					.getDorsoNariz().getIdCampo()));

		if (mediaFiliacionDTO.getColorCabello() != null
				&& mediaFiliacionDTO.getColorCabello().getIdCampo() != null)
			mediaFiliacion.setColorCabello(new Valor(mediaFiliacionDTO
					.getColorCabello().getIdCampo()));
		if (mediaFiliacionDTO.getLabioComisuras() != null
				&& mediaFiliacionDTO.getLabioComisuras().getIdCampo() != null)
			mediaFiliacion.setLabioComisuras(new Valor(mediaFiliacionDTO
					.getLabioComisuras().getIdCampo()));
		if (mediaFiliacionDTO.getHelixPosterior() != null
				&& mediaFiliacionDTO.getHelixPosterior().getIdCampo() != null)
			mediaFiliacion.setHelixPosterior(new Valor(mediaFiliacionDTO
					.getHelixPosterior().getIdCampo()));
		if (mediaFiliacionDTO.getTamanioBoca() != null
				&& mediaFiliacionDTO.getTamanioBoca().getIdCampo() != null)
			mediaFiliacion.setTamanioBoca(new Valor(mediaFiliacionDTO
					.getTamanioBoca().getIdCampo()));
		if (mediaFiliacionDTO.getLabiosProminencia() != null
				&& mediaFiliacionDTO.getLabiosProminencia().getIdCampo() != null)
			mediaFiliacion.setLabiosProminencia(new Valor(mediaFiliacionDTO
					.getLabiosProminencia().getIdCampo()));
		if (mediaFiliacionDTO.getTez() != null
				&& mediaFiliacionDTO.getTez().getIdCampo() != null)
			mediaFiliacion.setTez(new Valor(mediaFiliacionDTO.getTez()
					.getIdCampo()));
		if (mediaFiliacionDTO.getTipoCara() != null
				&& mediaFiliacionDTO.getTipoCara().getIdCampo() != null)
			mediaFiliacion.setTipoCara(new Valor(mediaFiliacionDTO
					.getTipoCara().getIdCampo()));
		if (mediaFiliacionDTO.getFormaCabello() != null
				&& mediaFiliacionDTO.getFormaCabello().getIdCampo() != null)
			mediaFiliacion.setFormaCabello(new Valor(mediaFiliacionDTO
					.getFormaCabello().getIdCampo()));
		if (mediaFiliacionDTO.getImplantacionCeja() != null
				&& mediaFiliacionDTO.getImplantacionCeja().getIdCampo() != null)
			mediaFiliacion.setImplantacionCeja(new Valor(mediaFiliacionDTO
					.getImplantacionCeja().getIdCampo()));
		if (mediaFiliacionDTO.getTipoMenton() != null
				&& mediaFiliacionDTO.getTipoMenton().getIdCampo() != null)
			mediaFiliacion.setTipoMenton(new Valor(mediaFiliacionDTO
					.getTipoMenton().getIdCampo()));
		if (mediaFiliacionDTO.getOrejaLobDimension() != null
				&& mediaFiliacionDTO.getOrejaLobDimension().getIdCampo() != null)
			mediaFiliacion.setOrejaLobDimension(new Valor(mediaFiliacionDTO
					.getOrejaLobDimension().getIdCampo()));
		if (mediaFiliacionDTO.getDireccionCeja() != null
				&& mediaFiliacionDTO.getDireccionCeja().getIdCampo() != null)
			mediaFiliacion.setDireccionCeja(new Valor(mediaFiliacionDTO
					.getDireccionCeja().getIdCampo()));
		if (mediaFiliacionDTO.getEspesorLabioSup() != null
				&& mediaFiliacionDTO.getEspesorLabioSup().getIdCampo() != null)
			mediaFiliacion.setEspesorLabioSup(new Valor(mediaFiliacionDTO
					.getEspesorLabioSup().getIdCampo()));
		if (mediaFiliacionDTO.getFrenteAncho() != null
				&& mediaFiliacionDTO.getFrenteAncho().getIdCampo() != null)
			mediaFiliacion.setFrenteAncho(new Valor(mediaFiliacionDTO
					.getFrenteAncho().getIdCampo()));
		if (mediaFiliacionDTO.getHelixContorno() != null
				&& mediaFiliacionDTO.getHelixContorno().getIdCampo() != null)
			mediaFiliacion.setHelixContorno(new Valor(mediaFiliacionDTO
					.getHelixContorno().getIdCampo()));
		if (mediaFiliacionDTO.getAlturaNariz() != null
				&& mediaFiliacionDTO.getAlturaNariz().getIdCampo() != null)
			mediaFiliacion.setAlturaNariz(new Valor(mediaFiliacionDTO
					.getAlturaNariz().getIdCampo()));
		if (mediaFiliacionDTO.getOrejaLobParticularidad() != null
				&& mediaFiliacionDTO.getOrejaLobParticularidad().getIdCampo() != null)
			mediaFiliacion
					.setOrejaLobParticularidad(new Valor(mediaFiliacionDTO
							.getOrejaLobParticularidad().getIdCampo()));
		if (mediaFiliacionDTO.getHelixAdherencia() != null
				&& mediaFiliacionDTO.getHelixAdherencia().getIdCampo() != null)
			mediaFiliacion.setHelixAdherencia(new Valor(mediaFiliacionDTO
					.getHelixAdherencia().getIdCampo()));
		if (mediaFiliacionDTO.getOrejaLobAdherencia() != null
				&& mediaFiliacionDTO.getOrejaLobAdherencia().getIdCampo() != null)
			mediaFiliacion.setOrejaLobAdherencia(new Valor(mediaFiliacionDTO
					.getOrejaLobAdherencia().getIdCampo()));
		if (mediaFiliacionDTO.getHelixSuperior() != null
				&& mediaFiliacionDTO.getHelixSuperior().getIdCampo() != null)
			mediaFiliacion.setHelixSuperior(new Valor(mediaFiliacionDTO
					.getHelixSuperior().getIdCampo()));
		if (mediaFiliacionDTO.getFrenteInclinacion() != null
				&& mediaFiliacionDTO.getFrenteInclinacion().getIdCampo() != null)
			mediaFiliacion.setFrenteInclinacion(new Valor(mediaFiliacionDTO
					.getFrenteInclinacion().getIdCampo()));
		if (mediaFiliacionDTO.getHelixOriginal() != null
				&& mediaFiliacionDTO.getHelixOriginal().getIdCampo() != null)
			mediaFiliacion.setHelixOriginal(new Valor(mediaFiliacionDTO
					.getHelixOriginal().getIdCampo()));
		if (mediaFiliacionDTO.getOrejaLobContorno() != null
				&& mediaFiliacionDTO.getOrejaLobContorno().getIdCampo() != null)
			mediaFiliacion.setOrejaLobContorno(new Valor(mediaFiliacionDTO
					.getOrejaLobContorno().getIdCampo()));
		if (mediaFiliacionDTO.getComplexion() != null
				&& mediaFiliacionDTO.getComplexion().getIdCampo() != null)
			mediaFiliacion.setComplexion(new Valor(mediaFiliacionDTO
					.getComplexion().getIdCampo()));

		return mediaFiliacion;
	}

	public static MediaFiliacionDTO transformarMediaFilacion(
			MediaFiliacion mediaFiliacion) {
		MediaFiliacionDTO mediaFiliacionDTO = new MediaFiliacionDTO();

		mediaFiliacionDTO.setMediaFiliacionId(mediaFiliacion
				.getMediaFiliacionId());
		mediaFiliacionDTO.setEstatura(mediaFiliacion.getEstatura());
		mediaFiliacionDTO.setPeso(mediaFiliacion.getPeso());
		mediaFiliacionDTO.setPerfil(mediaFiliacion.getPerfil());
		mediaFiliacionDTO.setTieneBarba(mediaFiliacion.getTieneBarba());
		mediaFiliacionDTO.setTieneBigote(mediaFiliacion.getTieneBigote());
		mediaFiliacionDTO.setUsaLentes(mediaFiliacion.getUsaLentes());
		mediaFiliacionDTO.setFactorRH(mediaFiliacion.getFactorRH());

		if (mediaFiliacion.getTipoSangre() != null
				&& mediaFiliacion.getTipoSangre().getValorId() != null)
			mediaFiliacionDTO.setTipoSangre(new ValorDTO(mediaFiliacion
					.getTipoSangre().getValorId()));
		if (mediaFiliacion.getFormaOreja() != null
				&& mediaFiliacion.getFormaOreja().getValorId() != null)
			mediaFiliacionDTO.setFormaOreja(new ValorDTO(mediaFiliacion
					.getFormaOreja().getValorId()));
		if (mediaFiliacion.getTamanioCeja() != null
				&& mediaFiliacion.getTamanioCeja().getValorId() != null)
			mediaFiliacionDTO.setTamanioCeja(new ValorDTO(mediaFiliacion
					.getTamanioCeja().getValorId()));
		if (mediaFiliacion.getTamanioOjo() != null
				&& mediaFiliacion.getTamanioOjo().getValorId() != null)
			mediaFiliacionDTO.setTamanioOjo(new ValorDTO(mediaFiliacion
					.getTamanioOjo().getValorId()));
		if (mediaFiliacion.getFormaCeja() != null
				&& mediaFiliacion.getFormaCeja().getValorId() != null)
			mediaFiliacionDTO.setFormaCeja(new ValorDTO(mediaFiliacion
					.getFormaCeja().getValorId()));
		if (mediaFiliacion.getAnchoNariz() != null
				&& mediaFiliacion.getAnchoNariz().getValorId() != null)
			mediaFiliacionDTO.setAnchoNariz(new ValorDTO(mediaFiliacion
					.getAnchoNariz().getValorId()));
		if (mediaFiliacion.getEspesorLabioInf() != null
				&& mediaFiliacion.getEspesorLabioInf().getValorId() != null)
			mediaFiliacionDTO.setEspesorLabioInf(new ValorDTO(mediaFiliacion
					.getEspesorLabioInf().getValorId()));
		if (mediaFiliacion.getOrejaTamanio() != null
				&& mediaFiliacion.getOrejaTamanio().getValorId() != null)
			mediaFiliacionDTO.setOrejaTamanio(new ValorDTO(mediaFiliacion
					.getOrejaTamanio().getValorId()));
		if (mediaFiliacion.getCabelloImplantacion() != null
				&& mediaFiliacion.getCabelloImplantacion().getValorId() != null)
			mediaFiliacionDTO.setCabelloImplantacion(new ValorDTO(
					mediaFiliacion.getCabelloImplantacion().getValorId()));
		if (mediaFiliacion.getColorOjos() != null
				&& mediaFiliacion.getColorOjos().getValorId() != null)
			mediaFiliacionDTO.setColorOjos(new ValorDTO(mediaFiliacion
					.getColorOjos().getValorId()));
		if (mediaFiliacion.getFrenteAltura() != null
				&& mediaFiliacion.getFrenteAltura().getValorId() != null)
			mediaFiliacionDTO.setFrenteAltura(new ValorDTO(mediaFiliacion
					.getFrenteAltura().getValorId()));
		if (mediaFiliacion.getFormaOjos() != null
				&& mediaFiliacion.getFormaOjos().getValorId() != null)
			mediaFiliacionDTO.setFormaOjos(new ValorDTO(mediaFiliacion
					.getFormaOjos().getValorId()));
		if (mediaFiliacion.getRaizNariz() != null
				&& mediaFiliacion.getRaizNariz().getValorId() != null)
			mediaFiliacionDTO.setRaizNariz(new ValorDTO(mediaFiliacion
					.getRaizNariz().getValorId()));
		if (mediaFiliacion.getFormaMenton() != null
				&& mediaFiliacion.getFormaMenton().getValorId() != null)
			mediaFiliacionDTO.setFormaMenton(new ValorDTO(mediaFiliacion
					.getFormaMenton().getValorId()));
		if (mediaFiliacion.getCalvicieTipo() != null
				&& mediaFiliacion.getCalvicieTipo().getValorId() != null)
			mediaFiliacionDTO.setCalvicieTipo(new ValorDTO(mediaFiliacion
					.getCalvicieTipo().getValorId()));
		if (mediaFiliacion.getInclinacionMenton() != null
				&& mediaFiliacion.getInclinacionMenton().getValorId() != null)
			mediaFiliacionDTO.setInclinacionMenton(new ValorDTO(mediaFiliacion
					.getInclinacionMenton().getValorId()));
		if (mediaFiliacion.getCabelloCantidad() != null
				&& mediaFiliacion.getCabelloCantidad().getValorId() != null)
			mediaFiliacionDTO.setCabelloCantidad(new ValorDTO(mediaFiliacion
					.getCabelloCantidad().getValorId()));
		if (mediaFiliacion.getAlturaNasoLabial() != null
				&& mediaFiliacion.getAlturaNasoLabial().getValorId() != null)
			mediaFiliacionDTO.setAlturaNasoLabial(new ValorDTO(mediaFiliacion
					.getAlturaNasoLabial().getValorId()));
		if (mediaFiliacion.getBaseNariz() != null
				&& mediaFiliacion.getBaseNariz().getValorId() != null)
			mediaFiliacionDTO.setBaseNariz(new ValorDTO(mediaFiliacion
					.getBaseNariz().getValorId()));
		if (mediaFiliacion.getDorsoNariz() != null
				&& mediaFiliacion.getDorsoNariz().getValorId() != null)
			mediaFiliacionDTO.setDorsoNariz(new ValorDTO(mediaFiliacion
					.getDorsoNariz().getValorId()));
		if (mediaFiliacion.getColorCabello() != null
				&& mediaFiliacion.getColorCabello().getValorId() != null)
			mediaFiliacionDTO.setColorCabello(new ValorDTO(mediaFiliacion
					.getColorCabello().getValorId()));
		if (mediaFiliacion.getLabioComisuras() != null
				&& mediaFiliacion.getLabioComisuras().getValorId() != null)
			mediaFiliacionDTO.setLabioComisuras(new ValorDTO(mediaFiliacion
					.getLabioComisuras().getValorId()));
		if (mediaFiliacion.getHelixPosterior() != null
				&& mediaFiliacion.getHelixPosterior().getValorId() != null)
			mediaFiliacionDTO.setHelixPosterior(new ValorDTO(mediaFiliacion
					.getHelixPosterior().getValorId()));
		if (mediaFiliacion.getTamanioBoca() != null
				&& mediaFiliacion.getTamanioBoca().getValorId() != null)
			mediaFiliacionDTO.setTamanioBoca(new ValorDTO(mediaFiliacion
					.getTamanioBoca().getValorId()));
		if (mediaFiliacion.getLabiosProminencia() != null
				&& mediaFiliacion.getLabiosProminencia().getValorId() != null)
			mediaFiliacionDTO.setLabiosProminencia(new ValorDTO(mediaFiliacion
					.getLabiosProminencia().getValorId()));
		if (mediaFiliacion.getTez() != null
				&& mediaFiliacion.getTez().getValorId() != null)
			mediaFiliacionDTO.setTez(new ValorDTO(mediaFiliacion.getTez()
					.getValorId()));
		if (mediaFiliacion.getTipoCara() != null
				&& mediaFiliacion.getTipoCara().getValorId() != null)
			mediaFiliacionDTO.setTipoCara(new ValorDTO(mediaFiliacion
					.getTipoCara().getValorId()));
		if (mediaFiliacion.getFormaCabello() != null
				&& mediaFiliacion.getFormaCabello().getValorId() != null)
			mediaFiliacionDTO.setFormaCabello(new ValorDTO(mediaFiliacion
					.getFormaCabello().getValorId()));
		if (mediaFiliacion.getImplantacionCeja() != null
				&& mediaFiliacion.getImplantacionCeja().getValorId() != null)
			mediaFiliacionDTO.setImplantacionCeja(new ValorDTO(mediaFiliacion
					.getImplantacionCeja().getValorId()));
		if (mediaFiliacion.getTipoMenton() != null
				&& mediaFiliacion.getTipoMenton().getValorId() != null)
			mediaFiliacionDTO.setTipoMenton(new ValorDTO(mediaFiliacion
					.getTipoMenton().getValorId()));
		if (mediaFiliacion.getOrejaLobDimension() != null
				&& mediaFiliacion.getOrejaLobDimension().getValorId() != null)
			mediaFiliacionDTO.setOrejaLobDimension(new ValorDTO(mediaFiliacion
					.getOrejaLobDimension().getValorId()));
		if (mediaFiliacion.getDireccionCeja() != null
				&& mediaFiliacion.getDireccionCeja().getValorId() != null)
			mediaFiliacionDTO.setDireccionCeja(new ValorDTO(mediaFiliacion
					.getDireccionCeja().getValorId()));
		if (mediaFiliacion.getEspesorLabioSup() != null
				&& mediaFiliacion.getEspesorLabioSup().getValorId() != null)
			mediaFiliacionDTO.setEspesorLabioSup(new ValorDTO(mediaFiliacion
					.getEspesorLabioSup().getValorId()));
		if (mediaFiliacion.getFrenteAncho() != null
				&& mediaFiliacion.getFrenteAncho().getValorId() != null)
			mediaFiliacionDTO.setFrenteAncho(new ValorDTO(mediaFiliacion
					.getFrenteAncho().getValorId()));
		if (mediaFiliacion.getHelixContorno() != null
				&& mediaFiliacion.getHelixContorno().getValorId() != null)
			mediaFiliacionDTO.setHelixContorno(new ValorDTO(mediaFiliacion
					.getHelixContorno().getValorId()));
		if (mediaFiliacion.getAlturaNariz() != null
				&& mediaFiliacion.getAlturaNariz().getValorId() != null)
			mediaFiliacionDTO.setAlturaNariz(new ValorDTO(mediaFiliacion
					.getAlturaNariz().getValorId()));
		if (mediaFiliacion.getOrejaLobParticularidad() != null
				&& mediaFiliacion.getOrejaLobParticularidad().getValorId() != null)
			mediaFiliacionDTO.setOrejaLobParticularidad(new ValorDTO(
					mediaFiliacion.getOrejaLobParticularidad().getValorId()));
		if (mediaFiliacion.getHelixAdherencia() != null
				&& mediaFiliacion.getHelixAdherencia().getValorId() != null)
			mediaFiliacionDTO.setHelixAdherencia(new ValorDTO(mediaFiliacion
					.getHelixAdherencia().getValorId()));
		if (mediaFiliacion.getOrejaLobAdherencia() != null
				&& mediaFiliacion.getOrejaLobAdherencia().getValorId() != null)
			mediaFiliacionDTO.setOrejaLobAdherencia(new ValorDTO(mediaFiliacion
					.getOrejaLobAdherencia().getValorId()));
		if (mediaFiliacion.getHelixSuperior() != null
				&& mediaFiliacion.getHelixSuperior().getValorId() != null)
			mediaFiliacionDTO.setHelixSuperior(new ValorDTO(mediaFiliacion
					.getHelixSuperior().getValorId()));
		if (mediaFiliacion.getFrenteInclinacion() != null
				&& mediaFiliacion.getFrenteInclinacion().getValorId() != null)
			mediaFiliacionDTO.setFrenteInclinacion(new ValorDTO(mediaFiliacion
					.getFrenteInclinacion().getValorId()));
		if (mediaFiliacion.getHelixOriginal() != null
				&& mediaFiliacion.getHelixOriginal().getValorId() != null)
			mediaFiliacionDTO.setHelixOriginal(new ValorDTO(mediaFiliacion
					.getHelixOriginal().getValorId()));
		if (mediaFiliacion.getOrejaLobContorno() != null
				&& mediaFiliacion.getOrejaLobContorno().getValorId() != null)
			mediaFiliacionDTO.setOrejaLobContorno(new ValorDTO(mediaFiliacion
					.getOrejaLobContorno().getValorId()));
		if (mediaFiliacion.getComplexion() != null
				&& mediaFiliacion.getComplexion().getValorId() != null)
			mediaFiliacionDTO.setComplexion(new ValorDTO(mediaFiliacion
					.getComplexion().getValorId()));

		return mediaFiliacionDTO;
	}

	public static SeniaParticularDTO transformarSeniaParticular(
			SeniaParticular seniaParticular) {
		SeniaParticularDTO seniaParticularDTO = new SeniaParticularDTO();

		if (seniaParticular.getSeniaParticularId() != null)
			seniaParticularDTO.setSeniaParticularId(seniaParticular
					.getSeniaParticularId());

		seniaParticularDTO.setTieneCicatrices(seniaParticular
				.getTieneCicatrices());
		seniaParticularDTO.setDescripcionCicatrices(seniaParticular
				.getDescripcionCicatrices());

		seniaParticularDTO.setTieneProtesis(seniaParticular.getTieneProtesis());
		seniaParticularDTO.setDescripcionProtesis(seniaParticular
				.getDescripcionProtesis());

		seniaParticularDTO.setTieneTatuajes(seniaParticular.getTieneTatuajes());
		seniaParticularDTO.setDescripcionTatuajes(seniaParticular
				.getDescripcionTatuajes());

		seniaParticularDTO.setTieneLunares(seniaParticular.getTieneLunares());
		seniaParticularDTO.setDescripcionLunares(seniaParticular
				.getDescripcionLunares());

		seniaParticularDTO.setTieneDefectosFisicos(seniaParticular
				.getTieneDefectosFisicos());
		seniaParticularDTO.setDescripcionDefectosFisicos(seniaParticular
				.getDescripcionDefectosFisicos());

		seniaParticularDTO.setTieneOtraSenia(seniaParticular
				.getTieneOtraSenia());
		seniaParticularDTO.setDescripcionOtraSenia(seniaParticular
				.getDescripcionOtraSenia());

		return seniaParticularDTO;
	}

	/**
	 * Transforma un objeto seniaParticularDTO a un objeto seniaParticular,
	 * 
	 * @param seniaParticularDTO
	 * @return seniaParticular
	 */
	public static SeniaParticular transformarSeniaParticularDTO(
			SeniaParticularDTO seniaParticularDTO) {

		SeniaParticular seniaParticular = InvolucradoTransformer
				.transformarSeniaParticularUpdate(null, seniaParticularDTO);

		return seniaParticular;
	}

	/**
	 * Transforma un objeto seniaParticularDTO a un objeto seniaParticular, En
	 * caso de que el parámetro seniaParticular sea null, es una transformacion
	 * normal. Si es diferente de null, se trata de un objeto de fue extraido de
	 * BD y contiene referencias a otras entidades. El objetivo es setear los
	 * valores que viene de la interfaz (presentación) y ser actualizado en BD.
	 * 
	 * @param seniaParticular
	 *            objeto a actualizar.
	 * @param seniaParticularDTO
	 * @return seniaParticular
	 */
	public static SeniaParticular transformarSeniaParticularUpdate(
			SeniaParticular seniaParticular,
			SeniaParticularDTO seniaParticularDTO) {
		if (seniaParticular == null)
			seniaParticular = new SeniaParticular();

		if (seniaParticularDTO.getSeniaParticularId() != null)
			seniaParticular.setSeniaParticularId(seniaParticularDTO
					.getSeniaParticularId());

		seniaParticular.setTieneCicatrices(seniaParticularDTO
				.getTieneCicatrices());
		seniaParticular.setDescripcionCicatrices(seniaParticularDTO
				.getDescripcionCicatrices());

		seniaParticular.setTieneProtesis(seniaParticularDTO.getTieneProtesis());
		seniaParticular.setDescripcionProtesis(seniaParticularDTO
				.getDescripcionProtesis());

		seniaParticular.setTieneTatuajes(seniaParticularDTO.getTieneTatuajes());
		seniaParticular.setDescripcionTatuajes(seniaParticularDTO
				.getDescripcionTatuajes());

		seniaParticular.setTieneLunares(seniaParticularDTO.getTieneLunares());
		seniaParticular.setDescripcionLunares(seniaParticularDTO
				.getDescripcionLunares());

		seniaParticular.setTieneDefectosFisicos(seniaParticularDTO
				.getTieneDefectosFisicos());
		seniaParticular.setDescripcionDefectosFisicos(seniaParticularDTO
				.getDescripcionDefectosFisicos());

		seniaParticular.setTieneOtraSenia(seniaParticularDTO
				.getTieneOtraSenia());
		seniaParticular.setDescripcionOtraSenia(seniaParticularDTO
				.getDescripcionOtraSenia());

		return seniaParticular;
	}

	
	public static InvolucradoDTO clonarInvolucrado(Involucrado involucrado, Calidades nuevaCalida, boolean copiarFolio) {
		Valor valor = null;
		InvolucradoDTO involucradoDto = new InvolucradoDTO();

		if (copiarFolio) {
		    involucradoDto.setFolioElemento(involucrado.getFolioElemento());
		}
		
		CalidadDTO calidadDto = new CalidadDTO();

		Calidad calidad = new Calidad();
		Funcionario funcionario = new Funcionario();
		involucradoDto.setMotivoComparecencia(involucrado.getMotivoComparecencia());
		involucradoDto.setEsDetenido(involucrado.getEsDetenido());
		involucradoDto.setEsServidor(involucrado.getEsServidor());
		involucradoDto.setCondicion(involucrado.getCondicion());
		involucradoDto.setDesconocido(involucrado.getDesconocido());
		involucradoDto.setStrFechaCreacion(DateUtils.formatear(involucrado.getFechaCreacionElemento()));
		involucradoDto.setFechaCreacionElemento(involucrado.getFechaCreacionElemento());

		if(involucrado.getTipoPersona() == null || (involucrado.getTipoPersona() != null && involucrado.getTipoPersona().equals("Fisica"))){
			involucradoDto.setTipoPersona(1L);
			involucradoDto.setOrganizacionDTO(null);
		}else{
			involucradoDto.setTipoPersona(0L);
		}
		if (involucrado.getSituacionJuridica() != null && involucrado.getSituacionJuridica().getValorId() != null){
			valor = involucrado.getSituacionJuridica();
			involucradoDto.setValorSituacionJuridica(new ValorDTO(valor.getValorId(), valor.getValor()));
		}
		
		involucradoDto.setFolioIdentificacion(involucrado.getFolioIdentificacion());
		if(involucrado.getEsVivo() != null && involucrado.getEsVivo()){
			involucradoDto.setEsVivo(true);
		}else{
			involucradoDto.setEsVivo(false);
		}
		
		if (involucrado.getCalidad() != null && involucrado.getCalidad().getTipoCalidad() != null) {
			calidad = involucrado.getCalidad();
			calidadDto.setValorIdCalidad(new ValorDTO(nuevaCalida.getValorId(), nuevaCalida.name()));
			calidadDto.setDescripcionEstadoFisico(calidad.getDescripcionEstadoFisico());
			calidadDto.setCalidades(nuevaCalida);
			involucradoDto.setCalidadDTO(calidadDto);
		}
		if (involucrado.getExpediente() != null) {
			involucradoDto.setExpedienteDTO(ExpedienteTransformer.transformarExpedienteBasico(involucrado.getExpediente()));
		}

		if (involucrado.getTipoDoctoId() != null) {
			valor = involucrado.getTipoDoctoId();
			involucradoDto.setValorIdIdentificaion(new ValorDTO(valor.getValorId(), valor.getValor()));
		}

		if (involucrado.getFuncionario() != null) {
			funcionario = involucrado.getFuncionario();
			involucradoDto.setFuncionario(FuncionarioTransformer.transformarFuncionario(funcionario));
		}
		if (involucrado.getIdioma() != null && involucrado.getIdioma().getValorId() != null) {
			valor =involucrado.getIdioma();
			involucradoDto.setValorIdIdioma(new ValorDTO(valor.getValorId(), valor.getValor()));
		}
		if (involucrado.getReligion() != null && involucrado.getReligion().getValorId() != null){
			valor =involucrado.getReligion();
			involucradoDto.setValorIdReligion(new ValorDTO(valor.getValorId(), valor.getValor()));
		}

		if (involucrado.getEstadoCivil() != null && involucrado.getEstadoCivil().getValorId() != null){
			valor =involucrado.getEstadoCivil();
			involucradoDto.setValorIdEstadoCivil(new ValorDTO(valor.getValorId(), valor.getValor()));		
		}

		if (involucrado.getEscolaridad() != null && involucrado.getEscolaridad().getValorId() != null){
			valor =involucrado.getEscolaridad();
			involucradoDto.setValorIdEscolaridad(new ValorDTO(valor.getValorId(), valor.getValor()));			
		}

		if (involucrado.getDetencions() != null && !involucrado.getDetencions().isEmpty()) {
			Set<Detencion> hashdetencion = (Set<Detencion>) involucrado.getDetencions();
			DetencionDTO detencion = null;
			List<DetencionDTO> detDto = new ArrayList<DetencionDTO>();
			for (Detencion det : hashdetencion){
				detencion = DetencionTransformer.transformarDetencionBasico(det);
				detencion.setDetencionId(null);
				detDto.add(detencion);
			}
			involucradoDto.setDetenciones(detDto);
		}
		if (involucrado.getPerfils() != null && !involucrado.getPerfils().isEmpty()) {
			Set<Perfil> hashperfil = (HashSet<Perfil>) involucrado.getPerfils();
			PerfilDTO perfil = null;
			List<PerfilDTO> perfDto = new ArrayList<PerfilDTO>();
			for (Perfil per : hashperfil){
				perfil = PerfilTransformer.transformarPerfil(per);
				perfil.setPerfilId(null);
				perfDto.add(perfil);
			}
			involucradoDto.setPerfiles(perfDto);
		}

		if (involucrado.getBiometricos() != null && !involucrado.getBiometricos().isEmpty()) {
			Set<Biometrico> hashbiomt = (HashSet<Biometrico>) involucrado.getBiometricos();
			List<BiometricoDTO> biolst = new ArrayList<BiometricoDTO>();
			BiometricoDTO biometrico = null;
			for (Biometrico biom : hashbiomt){
				biometrico = BiometricoTransformer.transfomarBiometrico(biom);
				biometrico.setBiometricoId(null);
				biolst.add(biometrico);
			}
			involucradoDto.setBiometricosDTO(biolst);
		}

		if (involucrado.getAliasInvolucrados() != null && !involucrado.getAliasInvolucrados().isEmpty()) {
			Set<AliasInvolucrado> hashaliasinv = involucrado.getAliasInvolucrados();
			List<AliasInvolucradoDTO> aliasInvList = new ArrayList<AliasInvolucradoDTO>();
			AliasInvolucradoDTO aliasDtoN= null;
			for (AliasInvolucrado alinv : hashaliasinv){
			    aliasDtoN = new AliasInvolucradoDTO();
			    aliasDtoN.setAlias(alinv.getAlias());
				aliasInvList.add(aliasDtoN);
			}
			involucradoDto.setAliasInvolucradoDTO(aliasInvList);
		}

		if (involucrado.getInvolucradoOcupacions() != null && !involucrado.getInvolucradoOcupacions().isEmpty()) {
			Set<InvolucradoOcupacion> hashOcupacion = involucrado.getInvolucradoOcupacions();
			List<ValorDTO> ocupacionList = new ArrayList<ValorDTO>();
			for (InvolucradoOcupacion invocup : hashOcupacion){
				ocupacionList.add(InvolucradoOcupacionTransfromer.transformarInvolucradoOcupacion(invocup));
			}
			involucradoDto.setValorIdOcupacion(ocupacionList);
		}

		if (involucrado.getInvolucradoNacionalidads() != null && !involucrado.getInvolucradoNacionalidads().isEmpty()) {
			Set<InvolucradoNacionalidad> hashNacionalidad = involucrado.getInvolucradoNacionalidads();
			List<ValorDTO> nacionalidadList = new ArrayList<ValorDTO>();
			for (InvolucradoNacionalidad invonac : hashNacionalidad)
				nacionalidadList.add(InvolucradoNacionalidadTransfromer
						.transformarInvolucradoNacionalidad(invonac));
			involucradoDto.setValorIdNacionalidad(nacionalidadList);
		}

		if (involucrado.getDelitosCometidos() != null && !involucrado.getDelitosCometidos().isEmpty()) {
			involucradoDto.setDelitosCometidos(new ArrayList<DelitoDTO>());
			DelitoDTO delitoDTO = null;
			for (DelitoPersona delito : involucrado.getDelitosCometidos()) {
				delitoDTO = DelitoTransfromer.transformarDelito(delito.getDelito());
//				delitoDTO.setDelitoId(null);
				involucradoDto.getDelitosCometidos().add(delitoDTO);
			}
		}

		if (involucrado.getNombreDemograficos() != null && !involucrado.getNombreDemograficos().isEmpty()) {
			involucradoDto.setNombresDemograficoDTO(new ArrayList<NombreDemograficoDTO>());
			NombreDemograficoDTO name = null;
			for (NombreDemografico nombre : involucrado.getNombreDemograficos()) {
				name = NombreDemograficoTransformer.transformarNombreDemografico(nombre);
				name.setNombreDemograficoId(null);
				involucradoDto.getNombresDemograficoDTO().add(name);
			}
		}

		if (involucrado.getInstitucionPresenta() != null) {
			ConfInstitucionDTO inst = new ConfInstitucionDTO();
			inst.setConfInstitucionId(Instituciones.DEF.getValorId());
			involucradoDto.setInstitucionPresenta(inst);
		}

		// MEDIOS DE CONTACTO
		if (involucrado.getMedioDeContactos() != null) {
			Iterator<MedioDeContacto> it = involucrado.getMedioDeContactos().iterator();
			List<CorreoElectronicoDTO> correosDTO = new ArrayList<CorreoElectronicoDTO>();
			List<TelefonoDTO> telefonosDTO = new ArrayList<TelefonoDTO>();
			CorreoElectronicoDTO correo = null;
			TelefonoDTO telefono = null;
			while (it.hasNext()) {
				MedioDeContacto medCont = (MedioDeContacto) it.next();
				if (medCont instanceof CorreoElectronico) {
					correosDTO.add(MedioDeContactoTransformer.transformarCorreo((CorreoElectronico)medCont));
				}else if (medCont instanceof Telefono) {
					telefono = MedioDeContactoTransformer.transformarTelefono((Telefono)medCont);
					telefono.setTelefonoId(null);
					telefono.setMedioDeContadoId(null);
					telefonosDTO.add(telefono);
				}
				
			}

			involucradoDto.setCorreosDTO(correosDTO);
			involucradoDto.setTelefonosDTO(telefonosDTO);
		}
		
		involucradoDto.setEsActivo(involucrado.getEsActivo()==null?true:involucrado.getEsActivo());
		involucradoDto.setEsProtegido(involucrado.getEsProtegido());
		return involucradoDto;
	}
}
