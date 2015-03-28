/**
* Nombre del Programa : JerarquiaOrganizacionalServiceImplTest.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
* Marca de cambio        : N/A
* Descripcion General    : Describir el objetivo de la clase de manera breve
* Programa Dependiente  :N/A
* Programa Subsecuente :N/A
* Cond. de ejecucion        :N/A
* Dias de ejecucion          :N/A                             Horario: N/A
*                              MODIFICACIONES
*------------------------------------------------------------------------------
* Autor                       :N/A
* Compania               :N/A
* Proyecto                 :N/A                                 Fecha: N/A
* Modificacion           :N/A
*------------------------------------------------------------------------------
*/
package mx.gob.segob.nsjp.service.test.institucion.impl;

import java.util.Arrays;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.service.institucion.JerarquiaOrganizacionalService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Clase que implementa las pruebas unitarias sobre la JerarquiaOrganizacional.
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
public class JerarquiaOrganizacionalServiceImplTest extends BaseTestServicios<JerarquiaOrganizacionalService> {

	public void testConsultarPrefijo(){
		 //Los datos para la Institucion se consultan en JeraquiaOrginazional, TipoJerarquia 2012 (Valor de Institucion)
//		InstitucionDTO institucionDTO = new InstitucionDTO(10L, "Atención temprana defensoria");
		//De prueba
//		INSERT INTO [dbo].[JerarquiaOrganizacional]([JerarquiaOrganizacional_id], [JerarquiaResponsable_id], [cAbreviatura], [cNombre], [TipoJerarquia_val], [Domicilio_id])
//		VALUES(14, 2, 'ATD', 'Atención temprana defensoria Prueba', 2014, NULL)
		InstitucionDTO institucionDTO = new InstitucionDTO(14L, "Atención temprana defensoria Prueba");
		
		String prefijo=null;
		
		try {
			prefijo = service.consultarPrefijo(institucionDTO);
			assertNotNull("El se logro obtener el prefijo", prefijo);
			logger.info("El prefijo es: :" + prefijo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void testConsultarTipoSolictudesPorJerarquiaOrganizacional(){
		Long idJO =1L;
		 
		try {
			List<ValorDTO> listadeSolicitudes = service.consultarTipoSolictudesPorJerarquiaOrganizacional(idJO);
			for (ValorDTO valorDTO : listadeSolicitudes) {
				logger.info(" valor: "+ valorDTO.getIdCampo());
				logger.info(" valor: "+ valorDTO.getValor());
				logger.info(" valor: "+ valorDTO.getNombreCampo());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void testConsultarDepartamentosExceptoAreasYDepartamentos(){
		Long[] idAreas = {Areas.ALMACEN.parseLong(),Areas.COORDINACION_POLICIA_MINISTERIAL.parseLong(), Areas.COORDINACION_VISITADURIA.parseLong() };
		List<Long> idsAreasADescartar = Arrays.asList(idAreas);
		Long[] idDepartamentos = {Areas.REGISTRO_INICIAL.parseLong(), Areas.ATENCION_TEMPRANA_PG_NO_PENAL.parseLong()};
		List<Long> idsDepartamentosADescartar = Arrays.asList(idDepartamentos);
		
		try {
			Long jerarquiaResponsableId = null; //1L;
			List<JerarquiaOrganizacionalDTO> listadeJO = 
				service.consultarDepartamentosExceptoAreasYDepartamentos(jerarquiaResponsableId, 
						idsAreasADescartar, idsDepartamentosADescartar);
			if (listadeJO!= null){
				logger.info(" Lista: "+ listadeJO);
				for (JerarquiaOrganizacionalDTO jerarquiaOrganizacionalDTO : listadeJO) {
					logger.info(" JerarquiaOrganizacionalId: "+ jerarquiaOrganizacionalDTO.getJerarquiaOrganizacionalId());
					logger.info(" Nombre: "+ jerarquiaOrganizacionalDTO.getNombre());
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
