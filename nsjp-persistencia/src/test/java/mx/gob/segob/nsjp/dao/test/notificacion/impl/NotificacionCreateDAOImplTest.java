package mx.gob.segob.nsjp.dao.test.notificacion.impl;

import java.util.Date;

import mx.gob.segob.nsjp.comun.enums.documento.EstatusNotificacion;
import mx.gob.segob.nsjp.comun.enums.documento.TipoDocumento;
import mx.gob.segob.nsjp.dao.documento.NotificacionDAO;
import mx.gob.segob.nsjp.dao.test.base.BaseTestPersistencia;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.Notificacion;
import mx.gob.segob.nsjp.model.Valor;

/**
 *
 * @author Jacob Lobaco
 */
public class NotificacionCreateDAOImplTest extends BaseTestPersistencia<NotificacionDAO> {


    public void testCreaNuevaNotificacion(){
        Notificacion nueva = null;
        int total = 22;
        for (int i= 0; i < total; i++) {
            nueva = new Notificacion();
            nueva.setConsecutivoNotificacion("0000000" + i);
            nueva.setResponsableDocumento(new Funcionario(1L));
            nueva.setEstatus(new Valor(EstatusNotificacion.NO_ATENDIDA.getValorId()));
            nueva.setFechaCreacion(new Date());
            nueva.setForma(new Forma(1L));
            nueva.setNombreDocumento("Prueba cosme fulanito");
            nueva.setTipoDocumento(new Valor(TipoDocumento.SOLICITUD.getValorId()));
            //
            nueva.setLugar("Av siempre viva " + i);
            nueva.setMotivo("Me cae mal ");
            nueva.setDomicilio("Direccion falsa 123");
            nueva.setLugarCitado("Bar de moe");
            
            daoServcice.create(nueva);
        }
    }
}
