/**
 * 
 */
package mx.gob.segob.nsjp.service.documento.impl.tranform;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.calidad.Calidades;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoDTO;
import mx.gob.segob.nsjp.dto.documento.AvisoHechoDelictivoWSDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioDTO;
import mx.gob.segob.nsjp.dto.domicilio.DomicilioWSDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.hecho.HechoDTO;
import mx.gob.segob.nsjp.dto.hecho.TiempoDTO;
import mx.gob.segob.nsjp.dto.persona.CorreoElectronicoDTO;
import mx.gob.segob.nsjp.dto.persona.MedioDeContactoDTO;
import mx.gob.segob.nsjp.dto.persona.TelefonoDTO;
import mx.gob.segob.nsjp.model.AvisoHechoDelictivo;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Hecho;
import mx.gob.segob.nsjp.model.MedioDeContacto;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.hecho.impl.transform.HechoTransformer;

import org.apache.log4j.Logger;

/**
 * @author adrian
 * 
 */
public class AvisoHechoDelictivoTransformer {
    /**
     * 
     */
    private final static Logger logger = Logger.getLogger(AvisoHechoDelictivoTransformer.class);
	public static AvisoHechoDelictivo transformarAviso(
            AvisoHechoDelictivoDTO dto) {
        AvisoHechoDelictivo aviso = new AvisoHechoDelictivo();
        if (dto.getDocumentoId() != null)
            aviso.setDocumentoId(dto.getDocumentoId());
        if (dto.getFechaAtencion() != null)
            aviso.setFechaAtencion(dto.getFechaAtencion());
        if (dto.getFechaCreacion() != null)
            aviso.setFechaCreacion(dto.getFechaCreacion());

        aviso.setConsecutivoNotificacion(dto.getConsecutivoNotificacion());
        if (dto.getHechoDTO() != null) {
            Hecho hecho = new Hecho();
            hecho.setHechoId(dto.getHechoDTO().getHechoId());
            aviso.setHecho(hecho);
        }
        if (dto.getFormaDTO() != null)
            aviso.setForma(new Forma(dto.getFormaDTO().getFormaId()));
        if (dto.getNombreDocumento() != null)
            aviso.setNombreDocumento(dto.getNombreDocumento());
        if (dto.getTipoDocumentoDTO() != null)
            aviso.setTipoDocumento(new Valor(dto.getTipoDocumentoDTO()
                    .getIdCampo()));

        return aviso;
    }

	public static AvisoHechoDelictivoDTO transformarAvisoDTO(
			AvisoHechoDelictivo avi) {
		AvisoHechoDelictivoDTO dto = new AvisoHechoDelictivoDTO();

		if (avi.getDocumentoId() != null)
			dto.setDocumentoId(avi.getDocumentoId());
		if (avi.getFechaAtencion() != null)
			dto.setFechaAtencion(avi.getFechaAtencion());
		if (avi.getEstatus() != null)
			dto.setEstatus(new ValorDTO(avi.getEstatus().getValorId()));
		if (avi.getConsecutivoNotificacion() != null)
			dto.setConsecutivoNotificacion(avi.getConsecutivoNotificacion());
		if (avi.getFolioNotificacion() != null)
			dto.setFolioNotificacion(avi.getFolioNotificacion());
		if (avi.getHecho() != null)
			dto.setHechoDTO(HechoTransformer.transformarHecho(avi.getHecho()));
		if (avi.getCatDelito() != null){
			CatDelitoDTO delito = new CatDelitoDTO();
			delito.setCatDelitoId(avi.getCatDelito().getCatDelitoId());
			delito.setClaveDelito(avi.getCatDelito().getClaveDelito());
			delito.setNombre(avi.getCatDelito().getNombre());
			delito.setEsGrave(avi.getCatDelito().getEsGrave());
			dto.setCatDelito(delito);
		}
		if (avi.getImplicado()!=null) {
		    dto.setEsAnonimo(Boolean.FALSE);
		    if(avi.getImplicado() != null && avi.getImplicado().getImplicadoId()!= null)
		    	dto.setIdImplicado(avi.getImplicado().getImplicadoId());
		    dto.setNombreImplicado(avi.getImplicado().getNombre());
			dto.setApellidoPatImplicado(avi.getImplicado().getApellidoPaterno());
			dto.setApellidoMatImplicado(avi.getImplicado().getApellidoMaterno());
			dto.setCalidadImplicado(new ValorDTO(avi.getImplicado().getTipoCalidad().getValorId(),avi.getImplicado().getTipoCalidad().getValor()));
			logger.debug("avi.getImplicado().getNombre() :: " + avi.getImplicado().getNombre());
			if (!avi.getImplicado().getMedioDeContactos().isEmpty()) {
				List<MedioDeContactoDTO> mediosDTO = new ArrayList<MedioDeContactoDTO>();
				for (MedioDeContacto medioContacto : avi.getImplicado().getMedioDeContactos()) {
					if (medioContacto instanceof Telefono) {
						Telefono telefono = (Telefono)medioContacto;
						TelefonoDTO telDTO = new TelefonoDTO();
						telDTO.setMedioDeContadoId(telefono.getMedioDeContactoId());
						telDTO.setCodigoArea(telefono.getCodigoArea());
						telDTO.setCodigoPais(telefono.getCodigoPais());
						telDTO.setNumeroTelefonico(telefono.getNumeroTelefonico());
						mediosDTO.add(telDTO);
					} else if (medioContacto instanceof CorreoElectronico){
						CorreoElectronico correo = (CorreoElectronico)medioContacto;
						CorreoElectronicoDTO correoDTO = new CorreoElectronicoDTO();
						correoDTO.setMedioDeContadoId(correo.getMedioDeContactoId());
						correoDTO.setDireccionElectronica(correo.getDireccionElectronica());
						mediosDTO.add(correoDTO);
					}
				}
				logger.debug("mediosDTO.size() :: "+mediosDTO.size());
				dto.setMediosImplicado(mediosDTO);
			}
		} else {
		    dto.setEsAnonimo(Boolean.TRUE);
		}
		dto.setFolioNotificacion(avi.getFolioNotificacion());
		dto.setFechaCreacion(avi.getFechaCreacion());
        if (avi.getMotivoRechazo() != null) {
            dto.setMotivoRechazo(new ValorDTO(avi.getMotivoRechazo()
                    .getValorId(), avi.getMotivoRechazo().getValor()));
        }
		return dto;
	}
	
	/**
	 * Transforma del objeto WSDTO(web service) al objeto DTO (local)
	 * @param avisoWs
	 * @return
	 */
    public static AvisoHechoDelictivoDTO transformarAvisoWS(
            AvisoHechoDelictivoWSDTO avisoWs) {
        if (avisoWs == null) {
            return null;
        }
        final AvisoHechoDelictivoDTO dto = new AvisoHechoDelictivoDTO();
        if (avisoWs.getCatDelitoId()!=null) {
            CatDelitoDTO del = new CatDelitoDTO();
            del.setCatDelitoId(avisoWs.getCatDelitoId());
            dto.setCatDelito(del);
        }
        dto.setEsAnonimo(avisoWs.getEsAnonimo());
        dto.setFechaAtencion(avisoWs.getFechaAtencion());
        dto.setFolioNotificacion(avisoWs.getFolioNotificacion());
        dto.setConsecutivoNotificacion(avisoWs.getConsecutivoNotificacion());
        if (avisoWs.getImplicado() != null) {
            dto.setCalidadImplicado(new ValorDTO(avisoWs.getImplicado().getCalidad().getValorIdCalidad()));
            if (avisoWs.getImplicado().getNombresDemograficos() != null
                    && !avisoWs.getImplicado().getNombresDemograficos().isEmpty()) {
                dto.setNombreImplicado(avisoWs.getImplicado()
                        .getNombresDemograficos().get(0).getNombre());
                dto.setApellidoPatImplicado(avisoWs.getImplicado()
                        .getNombresDemograficos().get(0).getApellidoPaterno());
                dto.setApellidoMatImplicado(avisoWs.getImplicado()
                        .getNombresDemograficos().get(0).getApellidoPaterno());
            }
        }
        if (avisoWs.getHecho()!=null) {
            HechoDTO hechDto = new HechoDTO();
            
            hechDto.setDescNarrativa(avisoWs.getHecho().getDescNarrativa());
            if (avisoWs.getHecho().getTiempo()!=null){
                TiempoDTO tim = new TiempoDTO();
                tim.setDescripcion(avisoWs.getHecho().getTiempo().getDescripcion());
                tim.setFechaFin(avisoWs.getHecho().getTiempo().getFechaFin());
                tim.setFechaInicio(avisoWs.getHecho().getTiempo().getFechaInicio());
                tim.setTipoRegistro(new ValorDTO(avisoWs.getHecho().getTiempo().getTipoRegistro()));
                logger.debug("tim :: " + tim);
                hechDto.setTiempo(tim);
            }
            if (avisoWs.getHecho().getLugar()!=null) {
                if (avisoWs.getHecho().getLugar() instanceof DomicilioWSDTO) {
                    DomicilioDTO lug = new DomicilioDTO();
                    DomicilioWSDTO ws = (DomicilioWSDTO) avisoWs.getHecho()
                            .getLugar();
                    logger.debug("DomicilioWSDTO.folioElemento :: "+ws.getFolioElemento());
                    lug.setAlias(ws.getAlias());
                    if (ws.getAsentamientoId() != null) {
                        lug.setAsentamientoDTO(new AsentamientoDTO(ws
                                .getAsentamientoId()));
                    }
                    if (ws.getCalidad() != null
                            && ws.getCalidad().getValorIdCalidad() != null) {
                        CalidadDTO cal = new CalidadDTO();
                        cal.setCalidades(Calidades.getByValor(ws.getCalidad()
                                .getValorIdCalidad()));
                        lug.setCalidadDTO(cal);
                    }
                    lug.setCalle(ws.getCalle());
                    if (ws.getCiudadId() != null) {
                        lug.setCiudadDTO(new CiudadDTO(ws.getCiudadId()));
                    }
                    lug.setDescripcion(ws.getDescripcion());
                    lug.setEdificio(ws.getEdificio());
                    if (ws.getEntidadId() != null) {
                        lug.setEntidadDTO(new EntidadFederativaDTO(ws
                                .getEntidadId()));
                    }
                    lug.setEntreCalle1(ws.getEntreCalle1());
                    lug.setEntreCalle2(ws.getEntreCalle2());
                    lug.setFolioElemento(ws.getFolioElemento());
                    if (ws.getMunicipioId() != null) {
                        lug.setMunicipioDTO(new MunicipioDTO(ws
                                .getMunicipioId()));
                    }
                    lug.setNumeroExterior(ws.getNumeroExterior());
                    lug.setNumeroInterior(ws.getNumeroInterior());
                    lug.setNumeroLote(ws.getNumeroLote());
                    lug.setReferencias(ws.getReferencias());
                    if (ws.getValorCalleId() != null) {
                        lug.setValorCalleId(new ValorDTO(ws.getValorCalleId()));
                    }
                    hechDto.setLugar(lug);
                }
                
            }
            dto.setHechoDTO(hechDto);
        }
        
        return dto;
    }

}
