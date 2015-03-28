/**
 * Nombre del Programa : CadenaDeCustodiaDAOImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jun-2011
 * Marca de cambio        : N/A
 * Descripcion General    : N/A
 * Programa Dependient    :N/A
 * Programa Subsecuente   :N/A
 * Cond. de ejecucion     :N/A
 * Dias de ejecucion      :N/A                                Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                            :N/A
 * Compania                         :N/A
 * Proyecto                         :N/A                      Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.dao.test.cadenacustodia.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.cadenadecustodia.CadenaDeCustodiaDAO;
import mx.gob.segob.nsjp.dao.cadenadecustodia.impl.CadenaDeCustodiaDAOImpl;
import mx.gob.segob.nsjp.dao.evidencia.EvidenciaDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.CadenaDeCustodia;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.Objeto;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class CadenaDeCustodiaDAOImplTest extends BaseTestPersistencia<CadenaDeCustodiaDAO> {

    @Autowired
    private CadenaDeCustodiaDAOImpl impl;
    @Autowired
    private EvidenciaDAO evidenciaDAO;

    public void testConsultarCadenaDeCustodiaXFolio(){
        logger.info("testConsultarCadenaDeCustodiaXFolio:");
        assert daoServcice != null;
        // El caso feliz =)
        CadenaDeCustodia buscame = new CadenaDeCustodia();
        buscame.setCadenaDeCustodiaId(10L);
        buscame.setFechaIntercambio(new Date());
        buscame.setFechaLevantamiento(new Date());
        buscame.setObservacion("Observacion de cosmeFulanito");
//        buscame.setQuienEntrega(1);
//        buscame.setQuienRecibe(1);
        buscame.setFolio("cosme");
        impl.saveOrUpdate(buscame);
        assert buscame.getCadenaDeCustodiaId() != null;
        CadenaDeCustodia buscada = daoServcice.consultarCadenaDeCustodiaXFolio("cosme");
        assert buscada.getCadenaDeCustodiaId() == buscame.getCadenaDeCustodiaId();
        impl.delete(buscame);
        // El caso triste =(
        
    }
   
    
    public void testConsultarCadenaCustodia(){
    	Long idCadena= 54L;
    	
    	CadenaDeCustodia  cadenaDeCustodia  = daoServcice.read(idCadena);
    	logger.info("CAdena:"+ cadenaDeCustodia );
    	if(cadenaDeCustodia!= null){
    		logger.info("Cadena ID:"+ cadenaDeCustodia.getCadenaDeCustodiaId() );
    	}
    }
    
    public void testConsultarEvidenciasXCadenaCustodia(){
    	Long cadenaDeCustodiaId = 54L;
		CadenaDeCustodia cadenaDeCustodia = new CadenaDeCustodia();
		cadenaDeCustodia.setCadenaDeCustodiaId(cadenaDeCustodiaId); 
     	List<Evidencia>  evidencia  = evidenciaDAO.consultarEvidenciasXCadenaCustodia(cadenaDeCustodia);
     	logger.info("Evidencia :"+ evidencia );
    }
    
    public void testCreate(){
        CadenaDeCustodia pojo = new CadenaDeCustodia();
        pojo.setExpediente(new Expediente(114L));
        pojo.setFechaLevantamiento(new Date());
        pojo.setFolio(Calendar.getInstance().get(Calendar.YEAR)+""+Calendar.getInstance().getTimeInMillis()%100000);
        super.daoServcice.create(pojo);
    }
    
    public void testConsultarCadenaCustodiaXAlmacen(){
    	List<CadenaDeCustodia> cadenas = daoServcice.consultarCadenaCustodiaXAlmacen(1L,4L);
    	assertNotNull(cadenas);
    	logger.info("Existen "+cadenas.size()+" cadenas");
    	for (CadenaDeCustodia cad : cadenas) {
    		logger.info("----------------------------");
			logger.info("ID: " + cad.getCadenaDeCustodiaId());
			if(cad.getExpediente()!=null){
				logger.info("Expediente: " + cad.getExpediente().getExpedienteId());
				logger.info("NumExp: " + cad.getExpediente().getNumeroExpedienteId()+" ("+cad.getExpediente().getNumeroExpediente()+")");
				logger.info("Caso: " + cad.getExpediente().getCaso().getCasoId());
			}
			assertNotNull("cadenaDeCustodia.getFolio()", cad.getFolio());
			logger.debug("cadenaDeCustodia.getFolio() = " + cad.getFolio());
			if (cad.getEvidencias() != null) {
				logger.info("Cant Evidencias: " + cad.getEvidencias().size());
				for (Evidencia evi : cad.getEvidencias()) {
					logger.info("*********************************");
					logger.info("Evidencia id"+evi.getEvidenciaId());
					logger.info("estatus Evidencia: "+evi.getEstatus());
					logger.info("Cod Barras"+ evi.getCodigoBarras());
					if(evi.getFuncionario()!=null)
						logger.info("AMP dueño: "+evi.getFuncionario().getNombreCompleto());
					if (evi.getObjeto() != null){
						Objeto obj = evi.getObjeto();
						logger.info("Objeto id: "
								+ obj.getElementoId());
						logger.info("Almacen id: "+obj.getAlmacen());
						
					}
					if(evi.getEslabones()!=null){
						logger.info("Existen "+evi.getEslabones().size()+ " eslabones");
						Iterator<Eslabon> itEslab = evi.getEslabones().iterator();
						while (itEslab.hasNext()) {
							Eslabon eslabon = (Eslabon) itEslab.next();
							logger.info("Eslabon: "+eslabon);
						}
						
					}
				}
			}
		}
    	
    }
    
    
    public void testConsultarFolioDeCadenaXObjetoId() throws NSJPNegocioException{        
        CadenaDeCustodia cadena = super.daoServcice.consultarFolioDeCadenaXObjetoId(35L,1L);
        	logger.info("Folio: "+cadena.getFolio());
    }
}
