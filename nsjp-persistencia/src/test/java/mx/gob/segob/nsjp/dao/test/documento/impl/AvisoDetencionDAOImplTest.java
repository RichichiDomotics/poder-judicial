/**
* Nombre del Programa : AvisoDetencionDAOImplTest.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 7 Jul 2011
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
package mx.gob.segob.nsjp.dao.test.documento.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.dao.avisodetencion.AvisoDetencionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.AvisoDetencion;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class AvisoDetencionDAOImplTest extends BaseTestPersistencia<AvisoDetencionDAO> {

    public void testCreate(){
        AvisoDetencion pojo = new AvisoDetencion();
        
        pojo.setForma(new Forma(Formas.SOLICITUD.getValorId()));
        pojo.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()) );
        pojo.setDetenido("Ollin Pax Aguirre");
        pojo.setFechaCreacion(new Date());
        pojo.setConsecutivoNotificacion("1");
        pojo.setEstatus(new Valor(EstatusNotificacion.NO_ATENDIDA.getValorId()));
        pojo.setFolioNotificacion("PG/"+Calendar.getInstance().get(Calendar.YEAR)+""+Calendar.getInstance().getTimeInMillis()%100000);
        pojo.setDetencion(new Detencion(4L));
        pojo.setNombreDocumento("Aviso Detencion " + pojo.getFolioNotificacion());
        super.daoServcice.create(pojo);
    }
    
    public void deleteAvisoDetencion(){
    	Long idAviso = 59L;
    	AvisoDetencion ad = daoServcice.read(idAviso);
    	daoServcice.delete(ad);
    }
    
    public void testConsultarAvisosDetencion(){
    	
        //AvisoDetencion pojo = new AvisoDetencion();
        
        List<AvisoDetencion> avisos = super.daoServcice.consultarAvisosDetencionPorExpediente(40L);
        for (AvisoDetencion aviso : avisos){
        	Set<NombreDemografico> nombres = aviso.getDetencion().getInvolucrado().getNombreDemograficos();
        	for(NombreDemografico nombre: nombres){
        		logger.info("Nombre Involucrado"+nombre);
        	}
        	logger.info("InvolucradoId="+aviso.getDetencion().getInvolucrado().getElementoId());
        	Set<DelitoPersona> delitos = aviso.getDetencion().getInvolucrado().getDelitosCometidos();
        	for(DelitoPersona delito : delitos){
        		logger.info("Delitos cometidos="+delito);
        	}
        }
    }
   
}
