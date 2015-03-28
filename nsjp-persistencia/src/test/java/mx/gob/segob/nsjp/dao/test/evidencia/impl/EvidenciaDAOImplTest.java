/**
 * 
 */
package mx.gob.segob.nsjp.dao.test.evidencia.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Objeto;

/**
 * @author adrian
 *
 */
public class EvidenciaDAOImplTest extends BaseTestPersistencia<EvidenciaDAO> {

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.evidencia.imp.EvidenciaDaoImp#consultarEvidenciasXCadenaCustodia(mx.gob.segob.nsjp.model.CadenaDeCustodia)}.
	 */
	public void testConsultarEvidenciasXCadenaCustodia() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.evidencia.imp.EvidenciaDaoImp#consultarEvidenciasResguardadasXUsario(java.lang.Long, java.lang.Long)}.
	 */
	public void testConsultarEvidenciasResguardadasXUsario() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mx.gob.segob.nsjp.dao.evidencia.imp.EvidenciaDaoImp#consultarEvidenciasXCadenaYEstatus(java.lang.Long, java.lang.Long)}.
	 */
	public void testConsultarEvidenciasXCadenaYEstatus() {
		List<Evidencia> evidencias = daoServcice.consultarEvidenciasXCadenaYEstatus(3L, 1L);
		logger.info("Existen "+evidencias.size()+" evidencias");
		for (Evidencia evi : evidencias) {
			logger.info("--------------------");
			logger.info("ID:"+evi.getEvidenciaId());
			logger.info("Estatus: "+evi.getEstatus().getValorId());
		}
	}
    public void testCreate(){
        Evidencia pojo = new Evidencia();
        pojo.setCadenaDeCustodia(new CadenaDeCustodia(12L));
        pojo.setObjeto(new Objeto(367L));
        pojo.setDescripcion("Arma como evidencia");
        pojo.setNumeroEvidencia(1L);
        pojo.setFechaLevantamiento(new Date());
        pojo.setOrigenEvidencia("Escena 1");
        pojo.setCodigoBarras("1236787890");
        super.daoServcice.create(pojo);
    }
    
    public void testConsultarevidenciaXAlmacenXEstatus(){
    	List<Evidencia> evidencias = daoServcice.consultarevidenciaXAlmacenXEstatus(1L, -1L, null);
    	assertNotNull(evidencias);
    	logger.info("Existen "+evidencias.size()+" evidencias");
		for (Evidencia evi : evidencias) {
			logger.info("--------------------");
			if(evi.getFuncionario()!=null)
				logger.info("AMP: "+evi.getFuncionario().getNombreCompleto());
			if(evi.getCadenaDeCustodia()!=null){
				if(evi.getCadenaDeCustodia().getExpediente()!=null){
					if(evi.getCadenaDeCustodia().getExpediente().getCaso()!=null)
						logger.info("Caso: "+evi.getCadenaDeCustodia().getExpediente().getCaso().getNumeroGeneralCaso());
				}
			}
			logger.info("Evidencia:"+evi.getEvidenciaId());
			if(evi.getObjeto()!=null)
				logger.info("Nombre: "+evi.getObjeto().getTipoElemento().getValor());
			logger.info("Codigo: "+evi.getCodigoBarras());
			if(evi.getEslabones()!=null){
				Eslabon eslabonUltimo=ultimoEslabon(evi.getEslabones().iterator());
				if(eslabonUltimo.getTipoEslabon()!=null)
					logger.info("Tipo Movim: "+eslabonUltimo.getTipoEslabon().getValor());
			}
		}
    }

	private Eslabon ultimoEslabon(Iterator<Eslabon> iterator) {
		Eslabon resp=new Eslabon();
		Long id=-1L;
		while (iterator.hasNext()) {
			Eslabon eslabon = (Eslabon) iterator.next();
			if(eslabon.getEslabonId()>id)
				resp=eslabon;
		}
		return resp;
	}

}
