/**
 * 
 */
package mx.gob.segob.nsjp.service.lugar.impl.transform;

import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.domicilio.AsentamientoDTO;
import mx.gob.segob.nsjp.dto.domicilio.CiudadDTO;
import mx.gob.segob.nsjp.dto.domicilio.EntidadFederativaDTO;
import mx.gob.segob.nsjp.dto.domicilio.MunicipioDTO;
import mx.gob.segob.nsjp.dto.domicilio.TipoAsentamientoDTO;
import mx.gob.segob.nsjp.model.Asentamiento;

/**
 * @author adrian
 * 
 */
public class AsentamientoTransformer {

	public static AsentamientoDTO transformarAsentamiento(Asentamiento scr) {
		TipoAsentamientoDTO tipoAsentamientoDTO = null;
		CiudadDTO ciudadDTO = null;
		MunicipioDTO municipioDTO = null;

		if (scr.getCatTipoAsentamiento() != null)
			tipoAsentamientoDTO = new TipoAsentamientoDTO(scr
					.getCatTipoAsentamiento().getCatTipoAsentamientoId(), scr
					.getCatTipoAsentamiento().getNombre());
		if (scr.getCiudad() != null) {
			EntidadFederativaDTO entidadFederativaDTO = null;
			if (scr.getCiudad().getEntidadFederativa() != null) {
				entidadFederativaDTO = new EntidadFederativaDTO(scr.getCiudad()
						.getEntidadFederativa().getEntidadFederativaId());
				entidadFederativaDTO.setNombreEntidad(scr.getCiudad()
						.getEntidadFederativa().getNombre());
				entidadFederativaDTO.setAbreviacion(scr.getCiudad()
						.getEntidadFederativa().getAbreviacion());
				if (scr.getCiudad().getEntidadFederativa().getPais() != null)
					entidadFederativaDTO.setValorIdPais(new ValorDTO(scr
							.getCiudad().getEntidadFederativa().getPais()
							.getValorId(), scr.getCiudad()
							.getEntidadFederativa().getPais().getValor()));
			}
			ciudadDTO = new CiudadDTO(scr.getCiudad().getCiudadId(), scr
					.getCiudad().getAbreviacion(), scr.getCiudad()
					.getNombreCiudad(), entidadFederativaDTO);
		}
		if (scr.getMunicipio() != null) {
			EntidadFederativaDTO entidadFederativaDTO = null;
			if (scr.getMunicipio().getEntidadFederativa() != null) {
				entidadFederativaDTO = new EntidadFederativaDTO(scr
						.getMunicipio().getEntidadFederativa()
						.getEntidadFederativaId());
				entidadFederativaDTO.setNombreEntidad(scr.getMunicipio()
						.getEntidadFederativa().getNombre());
				entidadFederativaDTO.setAbreviacion(scr.getMunicipio()
						.getEntidadFederativa().getAbreviacion());
				if (scr.getMunicipio().getEntidadFederativa().getPais() != null)
					entidadFederativaDTO.setValorIdPais(new ValorDTO(scr
							.getMunicipio().getEntidadFederativa().getPais()
							.getValorId(), scr.getMunicipio()
							.getEntidadFederativa().getPais().getValor()));
			}
			municipioDTO = new MunicipioDTO(
					scr.getMunicipio().getMunicipioId(), scr.getMunicipio()
							.getNombreMunicipio(), entidadFederativaDTO);
		}

		return new AsentamientoDTO(scr.getAsentamientoId(),
				scr.getCodigoPostal(), scr.getNombreAsentamiento(),
				tipoAsentamientoDTO, ciudadDTO, municipioDTO);
	}

}
