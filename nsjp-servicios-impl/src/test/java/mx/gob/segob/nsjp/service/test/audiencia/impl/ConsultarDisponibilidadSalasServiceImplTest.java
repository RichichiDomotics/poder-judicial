/**
 * Nombre del Programa : ConsultarDisponibilidadSalasServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Jun 2011
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
package mx.gob.segob.nsjp.service.test.audiencia.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.audiencia.DiaDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.EspacioCalendarioDTO;
import mx.gob.segob.nsjp.dto.audiencia.MesDisponibilidadDTO;
import mx.gob.segob.nsjp.dto.audiencia.SalaAudienciaDTO;
import mx.gob.segob.nsjp.dto.audiencia.SemanaDisponibilidadDTO;
import mx.gob.segob.nsjp.service.audiencia.ConsultarDisponibilidadSalasService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConsultarDisponibilidadSalasServiceImplTest
        extends
            BaseTestServicios<ConsultarDisponibilidadSalasService> {

    public void testConsultaMesActual() {
        try {
            MesDisponibilidadDTO mes = new MesDisponibilidadDTO();
            MesDisponibilidadDTO disp = service
                    .consultarDisponibilidadSalas(mes);
            assertNotNull("No puede ser nulo el mes", disp);
            assertFalse("Debe tener semanas", disp.getSemanas().isEmpty());
            assertTrue("Debe tener al menos 4 semanas", disp.getSemanas()
                    .size() >= 4);
            imprimirDias(disp);
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    public void testConsultaMesAnterior() {
        try {
            MesDisponibilidadDTO mes = new MesDisponibilidadDTO();
            mes.retrocederMes();
            MesDisponibilidadDTO disp = service
                    .consultarDisponibilidadSalas(mes);
            assertNotNull("No puede ser nulo el mes", disp);
            assertFalse("Debe tener semanas", disp.getSemanas().isEmpty());
            assertTrue("Debe tener al menos 4 semanas", disp.getSemanas()
                    .size() >= 4);
            imprimirDias(disp);
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    public void testConsultaMesSiguiente() {
        try {
            MesDisponibilidadDTO mes = new MesDisponibilidadDTO();
            mes.avanzarMes();
            MesDisponibilidadDTO disp = service
                    .consultarDisponibilidadSalas(mes);
            assertNotNull("No puede ser nulo el mes", disp);
            assertFalse("Debe tener semanas", disp.getSemanas().isEmpty());
            assertTrue("Debe tener al menos 4 semanas", disp.getSemanas()
                    .size() >= 4);
            imprimirDias(disp);
        } catch (NSJPNegocioException e) {
            logger.error(e.getMessage(), e);
            fail(e.getMessage());
        }
    }

    /**
     * Consulta la disponibilidad de la sala.
     */
    public void testConsultarDisponibilidadDiaSalas() {
        DiaDisponibilidadDTO filtro = new DiaDisponibilidadDTO();
        filtro.setMes(new MesDisponibilidadDTO());
        filtro.setDia(new Integer(23));
        try {
            DiaDisponibilidadDTO resp = service
                    .consultarDisponibilidadDiaSalas(filtro);
            assertNotNull(resp);
            assertFalse(resp.getSalas().isEmpty());
            imprimirSalas(resp);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

    private void imprimirSalas(DiaDisponibilidadDTO dis) {
        logger.debug("dis.getSalas().size() :: " + dis.getSalas().size());
        for (SalaAudienciaDTO dto : dis.getSalas()) {
            logger.debug("Sala :: " + dto.getNombreSala());
            for (EspacioCalendarioDTO ev : dto.getEventos()) {
                logger.debug("evento :: " + ev);
            }
        }
    }

    private void imprimirDias(MesDisponibilidadDTO dis) {
        int cont = 1;
        logger.debug("dis.getSemanas().size() :: " + dis.getSemanas().size());
        for (SemanaDisponibilidadDTO sm : dis.getSemanas()) {
            logger.debug("semana :: " + sm.getNoSemana());
            for (DiaDisponibilidadDTO dia : sm.getDias()) {
                logger.debug("dia " + cont + " :: " + dia);
                cont++;
            }
        }
    }

    
    public void testObtenerNombreSalas(){
        try {
            List<SalaAudienciaDTO> data =  super.service.obtenerNombresSalas(super.getUsuario());
            logger.debug("data :: " + data);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }
}
