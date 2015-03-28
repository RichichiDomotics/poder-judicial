/**
* Nombre del Programa : SolicitudMandamientoDAOImplTest.java
* Autor                            : vaguirre
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 20 Jul 2011
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
package mx.gob.segob.nsjp.dao.test.solicitud.impl;

import java.util.Calendar;
import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.forma.Formas;
import mx.gob.segob.nsjp.comun.enums.solicitud.EstatusSolicitud;
import mx.gob.segob.nsjp.comun.enums.solicitud.TiposSolicitudes;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudMandamientoDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudMandamiento;
import mx.gob.segob.nsjp.model.Valor;

/**
 * Describir el objetivo de la clase con punto al final.
 * @version 1.0
 * @author vaguirre
 *
 */
public class SolicitudMandamientoDAOImplTest extends BaseTestPersistencia<SolicitudMandamientoDAO> {

    public void testCreate(){
        SolicitudMandamiento pojo = new SolicitudMandamiento();
        pojo.setFechaCreacion(new Date());
        pojo.setTipoDocumento(new Valor(1L));
//      pojo.setFechaLimite(new Date());
        pojo.setForma(new Forma(Formas.SOLICITUD.getValorId()));
        pojo.setNombreDocumento("Solicitud Mandamiento No." + Calendar.getInstance().get(Calendar.MINUTE));
        pojo.setEstatus(new Valor(EstatusSolicitud.EN_PROCESO.getValorId()));
        pojo.setTipoSolicitud(new Valor(TiposSolicitudes.MEDIDAS_ALTERNATIVAS.getValorId()));
        pojo.setNumeroExpediente(new NumeroExpediente(7L));
        pojo.setFuncionarioSolicitante(new Funcionario(3L));
        pojo.setDestinatario(new Funcionario(1L));
//      pojo.setConfInstitucion(new ConfInstitucion(Long.valueOf(Instituciones.PJ.ordinal())));
        daoServcice.create(pojo);
    }
    
}
