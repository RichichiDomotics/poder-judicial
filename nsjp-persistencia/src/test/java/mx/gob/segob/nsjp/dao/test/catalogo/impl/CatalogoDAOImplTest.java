package mx.gob.segob.nsjp.dao.test.catalogo.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.enums.catalogo.Catalogos;
import mx.gob.segob.nsjp.dao.catalogo.CatalogoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Valor;

public class CatalogoDAOImplTest extends BaseTestPersistencia<CatalogoDAO> {

    public void testRecuperarCatalogoSencillo() {
        logger.debug("hola tomatal :: " + daoServcice);
        List<Valor> resp = daoServcice
        .recuperarCatalogoSencillo(Catalogos.PUESTO_SERVIDOR_PUBLICO);

        assertFalse("La lista no deberia estar vacía", resp.isEmpty());
        logger.info("resp :: " + resp);
        for (Valor valor : resp) {
			logger.info( "Valor: "+ valor.getValor());
		}
    }

    public void testRecuperarCatalogoCalidades() {
        logger.debug("hola tomatal :: " + daoServcice);
        List<Valor> resp = daoServcice
                .recuperarCatalogoSencillo(Catalogos.CALIDAD);

        assertFalse("La lista no deberia estar vacía", resp.isEmpty());
        assertEquals("Debe tener 18 elementos", resp.size(), 18);
        logger.info("resp :: " + resp);
    }
    
    public void testRecuperarCatalogoCompleto(){
    	
		List<Valor> catalogo = daoServcice.recuperarCatalogoCompleto(Catalogos.TIPO_PARTICIPACION);
		assertNotNull(catalogo);
		for (Valor val : catalogo) {
			logger.info("-----------------------");
			logger.info("id: "+val.getValorId());
			logger.info("valor: "+val.getValor());
		}
    }
    
}
