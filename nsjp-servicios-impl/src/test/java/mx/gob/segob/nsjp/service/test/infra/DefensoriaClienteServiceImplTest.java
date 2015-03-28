/**
 * Nombre del Programa : DefensoriaClienteServiceImplTest.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 22-jul-2011
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
package mx.gob.segob.nsjp.service.test.infra;



import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.infra.DefensoriaClienteService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
public class DefensoriaClienteServiceImplTest
    extends BaseTestServicios<DefensoriaClienteService> {

    public void testDefensoriaClienteService(){
        try {
            logger.info("Probando el servicio de: DefensoriaClienteService");
            assert service != null;
            
            //Se invoca al servicio para hacer una consulta a la carpeta de investigacion y enviarla
    		String numeroGeneralCaso ="YUC/PG/XX/PGE/2011/AA-00071"; 
    		String folioSolicitud ="DE/201100153";
    		
            ExpedienteDTO enviarCarpetaDeInvestigacion = service.
                    enviarCarpetaDeInvestigacion( numeroGeneralCaso, folioSolicitud);
            assertNotNull("enviarCarpetaDeInvestigacion", enviarCarpetaDeInvestigacion);
        } catch (NSJPNegocioException ex) {
            if (logger.isDebugEnabled()) {
                logger.debug(ex);
            }
            fail("Ocurrio una excepcion al ejecutar el test DefensoriaClienteService");
        }
    }
   
}
