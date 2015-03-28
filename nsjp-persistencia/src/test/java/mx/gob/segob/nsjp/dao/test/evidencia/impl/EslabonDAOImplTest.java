/**
* Nombre del Programa : EslabonDAOImplTest.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 18 Jul 2011
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
package mx.gob.segob.nsjp.dao.test.evidencia.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dao.eslabon.EslabonDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Documento;
import mx.gob.segob.nsjp.model.Eslabon;
import mx.gob.segob.nsjp.model.Evidencia;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class EslabonDAOImplTest extends BaseTestPersistencia<EslabonDAO> {

    
    public void testCreate() {
        Eslabon pojo = new Eslabon();
        pojo.setEvidencia(new Evidencia(3L));
        pojo.setFuncionarioEntrega(new Funcionario(7L));
        pojo.setQuienEntrega("Funcionario(7L)");
        pojo.setFuncionarioRecibe(new Funcionario(3L));
        pojo.setQuienRecibe("Funcionario(3L)");
        pojo.setNumeroEslabon(Calendar.getInstance().get(Calendar.DATE));
        pojo.setFechaEntrega(new Date());
        pojo.setFechaRecibe(new Date());
        pojo.setTipoEslabon(new Valor(1L));
        daoServcice.create(pojo);
    }
    
    public void testConsultaEslabonesPorEvidencia(){
    	
    	Long idEvidencia = 257L;
    	
    	List<Eslabon> eslabones  = daoServcice.consultaEslabonesPorEvidencia(idEvidencia);
    	logger.info(" Total de eslabones para la evidencia ("+idEvidencia+"): " + eslabones.size());
    	for (Eslabon eslabon : eslabones) {
    		logger.info("eslabonDTO - EslabonId:" + eslabon.getEslabonId());

			logger.info("eslabonDTO Datos de la Entrega ");
			logger.info("eslabonDTO - Nombre:"+ eslabon.getFuncionarioEntrega());
			logger.info("****eslabonDTO - Fecha Entrega:"+ eslabon.getFechaEntrega());
			logger.info("eslabonDTO - TipoEslabon:" + eslabon.getTipoEslabon().getValor());

			
			logger.info("eslabonDTO Datos de la Recepcion ");
			logger.info("eslabonDTO - Nombre:"+ eslabon.getFuncionarioRecibe());
			logger.info("****eslabonDTO - FechaRecibe:" + eslabon.getFechaRecibe());
			
			logger.info("eslabonDTO - Observacion:" + eslabon.getObservacion());
			
			logger.info("eslabonDTO - FechaInicioPrestamo:" + eslabon.getFechaInicioPrestamo());
			logger.info("eslabonDTO - FechaFinPrestamo:" + eslabon.getFechaFinPrestamo());
		}
    }
    
    public void testConsultarDocumentosXEslabonesDEvidencia(){
    	List<Documento> documentos = daoServcice.consultarDocumentosXEslabonesDEvidencia(1L);
    	assertNotNull(documentos);
    	logger.info("Existen "+documentos.size()+" documentos");
    	for (Documento doc : documentos) {
    		logger.info("-----------------------------");
    		logger.info("ID: "+doc.getDocumentoId());
    		logger.info("Tipo: "+doc.getTipoDocumento().getValor());
    		logger.info("Nombre: "+doc.getNombreDocumento());
    		logger.info("Fecha: "+doc.getFechaCreacion());
			
		}
    	
    }
    
    public void testConsultarEvidenciasNoDevueltas(){
    	List<Evidencia> evidencias = daoServcice.consultarEvidenciasNoDevueltas(1L, 2265L, new Date());
    	assertNotNull(evidencias);
		logger.info("Existen "+ evidencias.size()+" evidencias");
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
				Eslabon eslabonUltimo=evi.getEslabones().iterator().next();
				if(eslabonUltimo.getTipoEslabon()!=null){
					logger.info("Tipo Movim: "+eslabonUltimo.getTipoEslabon().getValor());
					if(eslabonUltimo.getFuncionarioEntrega()!=null)
						logger.info("Quien Entrega: "+eslabonUltimo.getFuncionarioEntrega().getNombreCompleto());
					if(eslabonUltimo.getFuncionarioRecibe()!=null)
						logger.info("Quien Recibe: "+eslabonUltimo.getFuncionarioRecibe().getNombreCompleto());
					logger.info("Fecha de devolución: "+eslabonUltimo.getFechaRecibe());
				}
			}else
				logger.info("Tipo Movim: NO REGISTRADO");
		}
    }
    
}
