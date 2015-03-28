/**
 * 
 */
package mx.gob.segob.nsjp.service.catalogo.impl.transform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatFaltaAdministrativaDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.model.Asentamiento;
import mx.gob.segob.nsjp.model.CatDelito;
import mx.gob.segob.nsjp.model.CatDelitoCausa;
import mx.gob.segob.nsjp.model.CatDelitoClasificacion;
import mx.gob.segob.nsjp.model.CatDelitoLugar;
import mx.gob.segob.nsjp.model.CatDelitoModalidad;
import mx.gob.segob.nsjp.model.CatDelitoModus;
import mx.gob.segob.nsjp.model.CatDiscriminante;
import mx.gob.segob.nsjp.model.CatDistrito;
import mx.gob.segob.nsjp.model.CatFaltaAdministrativa;
import mx.gob.segob.nsjp.model.CatTipoAsentamiento;
import mx.gob.segob.nsjp.model.CatUIEspecializada;
import mx.gob.segob.nsjp.model.Ciudad;
import mx.gob.segob.nsjp.model.ConfActividadDocumento;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.EntidadFederativa;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Municipio;
import mx.gob.segob.nsjp.model.Parametro;
import mx.gob.segob.nsjp.model.SalaAudiencia;
import mx.gob.segob.nsjp.model.SalaTemporal;
import mx.gob.segob.nsjp.model.Valor;

import org.apache.log4j.Logger;

/**
 * @author vaguirre
 * 
 */
public class CatalogoTransformer {

    /**
     * Logger.
     */
    private final static Logger logger = Logger
            .getLogger(CatalogoTransformer.class);

    /**
     * 
     * @param input
     * @return
     */
    public static List<CatalogoDTO> transformarValor(List<Valor> input) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        if (input != null && !input.isEmpty()) {
            for (Valor row : input) {
                resp.add(new CatalogoDTO(row.getValorId(), row.getValor()));
            }
        }
        return resp;
    }
    /**
     * 
     * @param input
     * @return
     */
    public static List<CatalogoDTO> transformarEF(List<EntidadFederativa> input) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        if (input != null && !input.isEmpty()) {
            for (EntidadFederativa row : input) {
                resp.add(new CatalogoDTO(row.getEntidadFederativaId(), row
                        .getNombre()));
            }
        }
        return resp;
    }
    /**
     * 
     * @param input
     * @return
     */
    public static List<CatalogoDTO> transformarCiudad(List<Ciudad> input) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        if (input != null && !input.isEmpty()) {
            for (Ciudad row : input) {
                resp.add(new CatalogoDTO(row.getCiudadId(), row
                        .getNombreCiudad()));
            }
        }
        return resp;
    }
    /**
     * 
     * @param input
     * @return
     */
    public static List<CatalogoDTO> transformarMpio(List<Municipio> input) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        if (input != null && !input.isEmpty()) {
            for (Municipio row : input) {
                resp.add(new CatalogoDTO(row.getMunicipioId(), row
                        .getNombreMunicipio()));
            }
        }
        return resp;
    }
    /**
     * 
     * @param input
     * @return
     */
    public static List<CatalogoDTO> transformarTipoAsentamiento(
            List<CatTipoAsentamiento> input) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        if (input != null && !input.isEmpty()) {
            for (CatTipoAsentamiento row : input) {
                resp.add(new CatalogoDTO(row.getCatTipoAsentamientoId(), row
                        .getNombre()));
            }
        }
        return resp;
    }
    /**
     * 
     * @param input
     * @return
     */
    public static List<CatalogoDTO> transformarAsentamiento(
            List<Asentamiento> input) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        if (input != null && !input.isEmpty()) {
            for (Asentamiento row : input) {
                resp.add(new CatalogoDTO(row.getAsentamientoId(), row
                        .getNombreAsentamiento()));
            }
        }
        return resp;
    }
    /**
     * 
     * @param temp
     * @return
     */
    public static List<CatalogoDTO> transformarValorCompleto(List<Valor> input) {
        List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();

        final Map<Long, CatalogoDTO> llaves = new HashMap<Long, CatalogoDTO>();

        for (Valor reg : input) {
            if (reg.getCampoCatalogo().getEsLlave()) {
                if (llaves.containsKey(reg.getRegistro().getRegistroId())) {
                    CatalogoDTO temp = llaves.get(reg.getRegistro()
                            .getRegistroId());
                    temp.setClave(reg.getValorId());
                    temp.setValor(reg.getValor());
                    llaves.put(reg.getRegistro().getRegistroId(), temp);
                } else {
                    llaves.put(reg.getRegistro().getRegistroId(),
                            new CatalogoDTO(reg.getValorId(), reg.getValor()));
                }
            } else {
                if (llaves.containsKey(reg.getRegistro().getRegistroId())) {
                    CatalogoDTO temp = llaves.get(reg.getRegistro()
                            .getRegistroId());
                    temp.addValorExtra(new ValorDTO(reg.getValorId(), reg
                            .getValor(), reg.getCampoCatalogo()
                            .getNombreCampo()));
                } else {
                    CatalogoDTO temp = new CatalogoDTO();
                    temp.addValorExtra(new ValorDTO(reg.getValorId(), reg
                            .getValor(), reg.getCampoCatalogo()
                            .getNombreCampo()));
                    llaves.put(reg.getRegistro().getRegistroId(), temp);
                }
            }

        }
        resp.addAll(llaves.values());
        return resp;
    }

    /**
     * 
     * @param consultarTodos
     * @return
     */
    public static List<CatDelitoDTO> transformarDelitos(List<CatDelito> input) {
        List<CatDelitoDTO> resp = new ArrayList<CatDelitoDTO>();
        CatDelitoDTO dto = null;
        if (input != null && !input.isEmpty()) {
            for (CatDelito row : input) {
                dto = new CatDelitoDTO();
                dto.setClaveDelito(row.getClaveDelito());
                dto.setCatDelitoId(row.getCatDelitoId());
                dto.setEsGrave(row.getEsGrave());
                dto.setNombre(row.getNombre());
                resp.add(dto);
            }
        }

        return resp;
    }

    /**
     * 
     * @param tipos
     * @return
     */
    public static List<CatalogoDTO> transformarJerarquias(
            List<JerarquiaOrganizacional> tipos) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (JerarquiaOrganizacional row : tipos) {
            resp.add(new CatalogoDTO(row.getJerarquiaOrganizacionalId(), row
                    .getNombre()));
        }
        logger.debug("resp.size() :: " + resp.size());
        return resp;
    }
    /**
     * 
     * @param inss
     * @return
     */
    public static List<CatalogoDTO> transformarInstitucion(
            List<ConfInstitucion> inss) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (ConfInstitucion row : inss) {
            resp.add(new CatalogoDTO(row.getConfInstitucionId(), row
                    .getNombreInst()));
        }
        return resp;
    }

    /**
     * Transforma un valor de BD a un <code>valorDTO</code>
     * 
     * @param in
     * @return
     */
    public static ValorDTO transformValor(Valor in) {
        if (in == null) {
            return null;
        }
        final ValorDTO out = new ValorDTO(in.getValorId(), in.getValor());
        return out;
    }


    /**
     * Transforma un valor de BD a un <code>valorDTO</code>
     * 
     * @param in
     * @return
     */
    public static Valor transformValor(ValorDTO in) {
        if (in == null) {
            return null;
        }
        final Valor out = new Valor(in.getIdCampo(), in.getValor());
        return out;
    }

    public static List<CatFaltaAdministrativaDTO> transformarFaltaAdministrativa(
            List<CatFaltaAdministrativa> cfa) {
        final List<CatFaltaAdministrativaDTO> resp = new ArrayList<CatFaltaAdministrativaDTO>();
        for (CatFaltaAdministrativa row : cfa) {
            CatFaltaAdministrativaDTO reg = new CatFaltaAdministrativaDTO();
            reg.setCatFaltaAdministrativaId(row.getCatFaltaAdministrativaId());
            reg.setClaveFalta(row.getClaveFalta());
            reg.setNombreFalta(row.getNombreFalta());
            reg.setDescripcionFalta(row.getDescripcionFalta());
            resp.add(reg);
        }
        return resp;
    }
    public static List<CatalogoDTO> transformarDelitosSingle(
            List<CatDelito> dels) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDelito row : dels) {
            resp.add(new CatalogoDTO(row.getCatDelitoId(), row
                    .getNombre()));
        }
        return resp;
    }
    public static List<CatalogoDTO> transformarFormasSingle(List<Forma> forms) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (Forma row : forms) {
            resp.add(new CatalogoDTO(row.getFormaId(), row
                    .getNombre()));
        }
        return resp;
    }
    public static List<CatalogoDTO> transformarActuacionSingle(
            List<ConfActividadDocumento> actus) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (ConfActividadDocumento row : actus) {
            resp.add(new CatalogoDTO(row.getConfActividadDocumentoId(), row
                    .getTipoActividad().getValor()));
        }
        return resp;
    }
    public static List<CatalogoDTO> transformarParametro(List<Parametro> params) {
        final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (Parametro row : params) {
            resp.add(new CatalogoDTO(row.getParametroId(), row
                    .getValor()));
        }
        return resp;
    }
	public static List<CatalogoDTO> transformarDistritosSingle(
			List<CatDistrito> distritos) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDistrito row : distritos) {
            resp.add(new CatalogoDTO(row.getCatDistritoId(), row
                    .getNombreDist()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarDiscriminanteSingle(
			List<CatDiscriminante> discriminantes) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDiscriminante row : discriminantes) {
            resp.add(new CatalogoDTO(row.getCatDiscriminanteId(), row
                    .getNombre()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarDelitoClasificacion(
			List<CatDelitoClasificacion> delitoClasificacion) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDelitoClasificacion row : delitoClasificacion) {
            resp.add(new CatalogoDTO(row.getCatDelitoClasificacionId(), row
                    .getDescripcion()));
}
		return resp;
	}
	public static List<CatalogoDTO> transformarDelitoLugar(
			List<CatDelitoLugar> delitoLugar) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDelitoLugar row : delitoLugar) {
            resp.add(new CatalogoDTO(row.getCatDelitoLugarId(), row
                    .getDescripcion()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarDelitoModalidad(
			List<CatDelitoModalidad> delitoModalidad) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDelitoModalidad row : delitoModalidad) {
            resp.add(new CatalogoDTO(row.getCatDelitoModalidadId(), row
                    .getDescripcion()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarDelitoModus(
			List<CatDelitoModus> delitoModus) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDelitoModus row : delitoModus) {
            resp.add(new CatalogoDTO(row.getCatDelitoModusId(), row
                    .getDescripcion()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarDelitoCausa(
			List<CatDelitoCausa> delitoCausa) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
        for (CatDelitoCausa row : delitoCausa) {
            resp.add(new CatalogoDTO(row.getCatDelitoCausaId(), row
                    .getDescripcion()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarCatUIEspecializada(
			List<CatUIEspecializada> listaCatUIE) {

		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
		
        for (CatUIEspecializada row : listaCatUIE) {
            resp.add(new CatalogoDTO(row.getCatUIEId(),row.getNombreUIE()));
        }
		return resp;
	}
	public static List<CatalogoDTO> transformarSalaTemporal(
			List<SalaTemporal> salasTemporales) {
		
		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
		
		for (SalaTemporal row : salasTemporales) {
			resp.add(new CatalogoDTO(row.getSalaTemporalId(), row.getMotivo()));
		}
		return resp;
	}
	public static List<CatalogoDTO> transformarSalaAudiencia(
			List<SalaAudiencia> salasAudiencia) {
		
		final List<CatalogoDTO> resp = new ArrayList<CatalogoDTO>();
		
		for (SalaAudiencia row : salasAudiencia) {
			resp.add(new CatalogoDTO(row.getSalaAudienciaId(), row.getNombreSala()));
		}
		return resp;
	}
}
