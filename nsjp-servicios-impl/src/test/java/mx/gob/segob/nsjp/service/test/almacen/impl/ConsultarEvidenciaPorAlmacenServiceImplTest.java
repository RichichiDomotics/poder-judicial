/**
 * Nombre del Programa : ConsultarEvidenciaPorAlmacenServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jul-2011
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
package mx.gob.segob.nsjp.service.test.almacen.impl;

import java.util.Iterator;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.evidencia.EslabonDTO;
import mx.gob.segob.nsjp.dto.evidencia.EvidenciaDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.service.almacen.ConsultarEvidenciaPorAlmacenService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class ConsultarEvidenciaPorAlmacenServiceImplTest
    extends BaseTestServicios<ConsultarEvidenciaPorAlmacenService> {

    public void testConsultarEvidenciaPorAlmacenService(){
        try {
            logger.info("Probando el servicio de: ConsultarEvidenciaPorAlmacenService");
            assert service != null;
            List<EvidenciaDTO> consultarEvidenciaPorAlmacen = service.consultarEvidenciaPorAlmacen(new AlmacenDTO(1L));
            assertNotNull("consultarEvidenciaPorAlmacen", consultarEvidenciaPorAlmacen);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test ConsultarEvidenciaPorAlmacenService");
        }
    }
    
    public void testConsultarEvidenciasXAlmacenXEstatus(){
    	CasoDTO caso=new CasoDTO();
    	caso.setCasoId(154L);
    	caso.setNumeroGeneralCaso("YUC/PG/XX/PGE/2011/AA-00003");
    	caso = null;

    	UsuarioDTO usuario=new UsuarioDTO();
    	usuario.setFuncionario(new FuncionarioDTO(11L));
    	
    	try {
			
			List<EvidenciaDTO> evidencias = service.consultarEvidenciasXAlmacenXEstatus(usuario, null, caso,1L);
			assertNotNull(evidencias);
			
			logger.info("Existen "+evidencias.size()+ " evidencias");
			for (EvidenciaDTO evi : evidencias) {
				logger.info("--------------------");
				logger.info("Evidencia:"+evi.getEvidenciaId());

				if(evi.getFuncionario()!=null)
					logger.info("AMP: "+evi.getFuncionario().getNombreCompleto());
				if(evi.getCadenaDeCustodia()!=null){
					logger.info("Id cadena de custoida:"+ evi.getCadenaDeCustodia().getCadenaDeCustodiaId());

					if(evi.getCadenaDeCustodia().getExpedienteDTO()!=null){
						logger.info("NumeroExpediente: "+evi.getCadenaDeCustodia().getExpedienteDTO().getNumeroExpediente());
						if(evi.getCadenaDeCustodia().getExpedienteDTO().getCasoDTO()!=null)
							logger.info("Caso: "+evi.getCadenaDeCustodia().getExpedienteDTO().getCasoDTO().getNumeroGeneralCaso());
					}
				}
				
				
				if(evi.getObjeto()!=null)
					logger.info("Nombre: "+evi.getObjeto().getTipoObjeto().getNombreEntity());
				logger.info("Codigo: "+evi.getCodigoBarras());
				logger.info("Cantidad: "+evi.getCantidad());
				String strEstatus=(evi.getEstatus()!=null)?evi.getEstatus().getValor():"NO REGISTRADO";
				logger.info("Estatus: "+strEstatus);
				if(evi.getEslabones()!=null){
					EslabonDTO eslabonUltimo=ultimoEslabon(evi.getEslabones().iterator());
					if(eslabonUltimo.getTipoEslabon()!=null)
						logger.info("Tipo Movim: "+eslabonUltimo.getTipoEslabon().getValor());
					else
						logger.info("Tipo Movim: NO REGISTRADO");
				}else
					logger.info("Tipo Movim: NO REGISTRADO");
			}
		} catch (NSJPNegocioException e) {
			e.printStackTrace();
			assertTrue(false);
		}
    }
    
    private EslabonDTO ultimoEslabon(Iterator<EslabonDTO> iterator) {
		EslabonDTO resp=new EslabonDTO();
		Long id=-1L;
		while (iterator.hasNext()) {
			EslabonDTO eslabon = (EslabonDTO) iterator.next();
			if(eslabon.getEslabonId()>id)
				resp=eslabon;
		}
		return resp;
	}
   
}
