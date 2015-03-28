/**
 * 
 */
package mx.gob.segob.nsjp.service.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatDelitoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.service.catalogo.CatalogoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * @author vaguirre
 * 
 */
public class CatalogoServiceImplTest extends BaseTestServicios<CatalogoService> {

    public void testCatalogoSencilloAndLog() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.ESTATUS_EXPEDIENTE);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            for (CatalogoDTO dto : data) {
                logger.debug("dto :: " + dto.getClave() + "->" + dto.getValor());
            }
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
    public void testCatalogoDependienteAndLog() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogoDependiente(Catalogos.ESTUDIO_PERICIAL, 2415L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            for (CatalogoDTO dto : data) {
                logger.debug("dto :: " + dto.getClave() + "->" + dto.getValor());
            }
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testCatalogo() throws NSJPNegocioException {
    	try{
    		List<CatDelitoDTO> resul=service.consultarDelitosSinIdsGrid("");    		
    		logger.debug(resul.size());
    	} catch (NSJPNegocioException e) {
    		logger.debug(e);
    	}    	    
    }

    public void testCatalogoCompletoAndLog() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogoCompleto(Catalogos.AGENCIAS);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("data.size() :: " + data.size());
            for (CatalogoDTO dto : data) {
                logger.debug("dto :: " + dto.getClave() + "->" + dto.getValor() + " _ " + dto.getCampoId());
                for (ValorDTO  ex: dto.getValoresExras()) {
                    logger.debug(" ex :: ["+ex.getIdCampo() +"] " + ex.getNombreCampo() + "->" + ex.getValor()+ " _ " + dto.getCampoId());
                                       
                }
            }
 
            
        } catch (NSJPNegocioException e) {

        }
    }    
    
    public void testCatalogoPaises() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.PAISES);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testCatalogoDelegacionesDF() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.recuperarCatalogoDependiente(
                    Catalogos.DELEGACION_MUNICIPIO, 9L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 16 delegaciones", data.size(), 16);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testCatalogoAsentamientoFiltro() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.consultarAsentamiento(1L, 1L, 7L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 78 coincidencias", data.size(), 78);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testCatalgosMarcasMotocicletas() {

        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.recuperarCatalogoDependiente(
                    Catalogos.MARCA_VEHICULO, 939L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 19 marcas de motos", data.size(), 19);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testCatalgosMarcasHelicopteros() {

        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.recuperarCatalogoDependiente(
                    Catalogos.MARCA_AERONAVE, 455L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 19 marcas de Helicópteros", data.size(),
                    14);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }

    public void testCatalogoTipoObjetos() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.TIPO_OBJETO);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 15 tipos de objetos", data.size(), 15);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    /**
     * 
     */
    public void testCatalogoPuestosServidorPublico() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.PUESTO_SERVIDOR_PUBLICO);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    /**
     * 
     */
    public void testCatalogoActividades() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.TIPO_ACTIVIDAD);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 52 tipos de actividad", data.size(), 52);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    /**
     * 
     */
    public void testCatalogoCompletoDelito() {
        try {
            logger.info("a ver");
            List<CatDelitoDTO> data = service.consultarDelito();
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);

            for (CatDelitoDTO llave : data) {
                logger.debug("Nombre :: " + llave.getNombre() + ", "
                        + llave.getNombre());
                            }
        } catch (NSJPNegocioException e) {

        }
    }

    public void testCatalogoEspecialidadesPericiales() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.TIPO_ESPECIALIDAD_FUNCIONARIO);
            for (CatalogoDTO cat : data) {
            	logger.info("------");
            	logger.info("Valor:"+cat.getValor());
			}
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            assertEquals("Se esperaba 39 tipo de especialidad pericial",
                    data.size(), 39);
        } catch (NSJPNegocioException e) {

        }
    }

    public void testCatalogoArea() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.recuperarCatalogo(Catalogos.AREA);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            assertEquals("Se esperaba 1 tipo de especialidad pericial",
                    data.size(), 1);
        } catch (NSJPNegocioException e) {

        }
    }

    public void testCatalogoInstitucion() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.INSTITUCION_CON_NSJP);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            assertEquals("Deben existir 5 instituciones", data.size(), 5);
        } catch (NSJPNegocioException e) {

        }
    }
    public void testCatalogoOrejaLobuloParticularidad() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.OREJA_LOBULO_PARTICULARIDAD);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            assertEquals("Deben existir 3 particularidaddes", data.size(), 3);
        } catch (NSJPNegocioException e) {

        }
    }
    public void testCatalogoTipoAudiencia() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogoCompleto(Catalogos.TIPO_AUDIENCIA);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("data.size() :: " + data.size());
//            assertEquals("DEben existir 7 tipos de audiencias", data.size(), 7);
//            assertNotNull("debe contener más campos", data.get(0)
//                    .getValoresExras());
//            assertFalse("debe contener más campos", data.get(0)
//                    .getValoresExras().isEmpty());
            
            for (ValorDTO valex : data.get(0).getValoresExras()) {
                logger.debug("valex :: " + valex);
            }
            
        } catch (NSJPNegocioException e) {

        }
    }

    public void testCatalogoEtapasExpediente() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.ETAPA_EXPEDIENTE);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("data.size() :: " + data.size());
            assertEquals("DEben existir 4 etapas para el expediente",
                    data.size(), 4);
        } catch (NSJPNegocioException e) {

        }
    }
    
    public void testCatalogoNacionalidades() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.NACIONALIDAD);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("Nacionalidades:: " + data.size());
            assertFalse("No puedes estar vacia la lista", data.isEmpty());
            for (CatalogoDTO uno : data){
                logger.debug("Valor :: " + uno.getValor());
            }
        } catch (NSJPNegocioException e) {

        }
    }
    
    public void testCatalogoNormas() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogo(Catalogos.TIPO_NORMA);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("Nacionalidades:: " + data.size());
            assertFalse("No puedes estar vacia la lista", data.isEmpty());
            assertEquals("Deben existir 8 Tipos", data.size(), 8);
        } catch (NSJPNegocioException e) {

        }
    }
    
    
    
    public void testCatalogoCompletoSolicitudes() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogoCompleto(Catalogos.TIPO_DOCUMENTO);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("data.size() :: " + data.size());
            assertEquals("DEben existir 6 tipos de audiencias", data.size(), 6);
        } catch (NSJPNegocioException e) {

        }
    }
    
    public void testCatalogoTipoParticipacion() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service
                    .recuperarCatalogoCompleto(Catalogos.TIPO_PARTICIPACION);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando no existan datos",
                    data);
            logger.debug("data.size() :: " + data.size());
            assertEquals("DEben existir 3 tipos de audiencias", data.size(), 3);
            assertNotNull("debe contener más campos", data.get(0)
                    .getValoresExras());
//            assertFalse("debe contener más campos", data.get(0)
//                    .getValoresExras().isEmpty());
            
            for (ValorDTO valex : data.get(0).getValoresExras()) {
                logger.debug("valex :: " + valex);
            }
            
        } catch (NSJPNegocioException e) {

        }
    }
    
    
    public void testCatalogoAreasDependiente() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.recuperarCatalogoDependiente(
                    Catalogos.AREA, 1L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 16 delegaciones", data.size(), 16);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
    
    public void testCatalogoDepartamentosDependiente() {
        try {
            logger.info("a ver");
            List<CatalogoDTO> data = service.recuperarCatalogoDependiente(
                    Catalogos.DEPARTAMENTO, 6L);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);
            assertEquals("Se esperaban 16 delegaciones", data.size(), 16);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testConsultarCatDelitoPorFilro() {
        try {
        	CatDelitoDTO catDelitoFiltroDTO= new CatDelitoDTO();
//        	catDelitoFiltroDTO.setCatDelitoId(1L);
//        	catDelitoFiltroDTO.setClaveDelito("1");
//        	catDelitoFiltroDTO.setNombre("Violación");
//        	catDelitoFiltroDTO.setEsGrave(false);
        	catDelitoFiltroDTO.setEsAccionPenPriv(true);
            
            
            List<CatDelitoDTO> data = service.consultarCatDelitoPorFilro(catDelitoFiltroDTO);
            assertNotNull(
                    "La consulta de catalogos debe entregar lista vacia cuando noa existan datos",
                    data);

            for (CatDelitoDTO catDelitoDTO : data) {
            	logger.info(" Delito:"+ catDelitoDTO.getCatDelitoId() );
    			logger.info(" Delito:"+ catDelitoDTO.getNombre() );
    			logger.info(" Delito:"+ catDelitoDTO.getClaveDelito() );
    			logger.info(" Delito:"+ catDelitoDTO.getEsGrave());
    			logger.info(" Delito:"+ catDelitoDTO.getEsAccionPenPriv() );
                logger.debug("departamento :: " + catDelitoDTO.getDepartamento());
            }
        } catch (NSJPNegocioException e) {

        }
    }

    public void testEliminarCatDelito(){
    	try {
    		Long Valor = service.eliminarCatDelito(133L);
			
			if(Valor == null){
				logger.debug("EL OBJETO ES NULL::::::::::::: ");
			}
			
			
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
		}
    	
    }

}
