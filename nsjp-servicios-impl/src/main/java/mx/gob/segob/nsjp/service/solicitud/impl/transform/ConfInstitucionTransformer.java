package mx.gob.segob.nsjp.service.solicitud.impl.transform;

import mx.gob.segob.nsjp.dto.configuracion.ConfInstitucionDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;

public class ConfInstitucionTransformer {

	public static ConfInstitucionDTO transformarInstitucion(
			ConfInstitucion institucion) {
		ConfInstitucionDTO cinstitucion = new ConfInstitucionDTO();
		cinstitucion.setClave(institucion.getClave());
		cinstitucion.setConfInstitucionId(institucion.getConfInstitucionId());
		cinstitucion.setEsInstalacionActual(institucion
				.getEsInstalacionActual());
		cinstitucion.setNombreInst(institucion.getNombreInst());
		cinstitucion.setUrlInst(institucion.getUrlInst());

		return cinstitucion;

	}

	public static ConfInstitucion transformarInstitucion(
			ConfInstitucionDTO institucion) {

		ConfInstitucion cinstitucion = null;
		if (institucion != null) {
			cinstitucion= new ConfInstitucion ();
			cinstitucion.setClave(institucion.getClave());
			cinstitucion.setConfInstitucionId(institucion
					.getConfInstitucionId());
			cinstitucion.setEsInstalacionActual(institucion
					.getEsInstalacionActual());
			cinstitucion.setNombreInst(institucion.getNombreInst());
			cinstitucion.setUrlInst(institucion.getUrlInst());
		}

		return cinstitucion;

	}
}
