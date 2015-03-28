package mx.gob.segob.nsjp.service.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoCompletoDTO;
import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.service.catalogo.AdministrarCatalogoService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

public class AdministrarCatalogoServiceImplTest
        extends
            BaseTestServicios<AdministrarCatalogoService> {
    public void testObtenerValor() {
        try {
            CatalogoDTO input = new CatalogoDTO();
            input.setIdCatalogo(new Long(Catalogos.ACTUACIONES.ordinal()));
            input.setClave(1L);
            CatalogoDTO row = super.service.obtenerValor(input);
            logger.debug(row);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    public void testRegistrarValor() {
        try {
            CatalogoDTO niu = new CatalogoDTO();
            niu.setCampoId(171L);
            niu.setValor("F");
            ValorDTO cuota = new ValorDTO();
            cuota.setIdCampo(172L);
            cuota.setValor("20");
            niu.addValorExtra(cuota);
            super.service.registrarValor(niu);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }

    public void testActualizarValor() {
        try {
            CatalogoDTO niu = new CatalogoDTO();
            niu.setClave(2798L);
            niu.setValor("EE");
            ValorDTO cuota = new ValorDTO();
            cuota.setIdCampo(172L);
            cuota.setValor("21");
            niu.addValorExtra(cuota);
            super.service.actualizarValor(niu);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }

    public void testEliminarValor() {
        try {
            CatalogoDTO del =new CatalogoDTO();
            del.setClave(2798L);
            super.service.eliminarValor(del);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }

    public void testObtenerCatalogo() {
        try {
            CatalogoCompletoDTO catCom = super.service.obtenerCatalogo(new Long(Catalogos.TIPO_AUDIENCIA.ordinal()));
            for (CatalogoDTO dto : catCom.getValores()) {
                logger.debug("dto :: " + dto.getClave() + "->" + dto.getValor());
                for (ValorDTO  ex: dto.getValoresExras()) {
                    logger.debug(" ex :: ["+ex.getIdCampo() +"] " + ex.getNombreCampo() + "->" + ex.getValor());
                }
            }
            logger.debug(catCom);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }

    public void testObtenerListaCatalogos() {
        try {
            List<CatalogoDTO> data = super.service.obtenerListaCatalogos();
            for (CatalogoDTO row: data) {
                logger.debug("row :: "+ row);
            }
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }

    }
    
    public void testObtenerDefinicion(){
        try {
            CatalogoDTO row = super.service.obtenerDefinicion(new Long(Catalogos.DELITO.ordinal()));
            logger.debug(row);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    
        
    }
    
}
