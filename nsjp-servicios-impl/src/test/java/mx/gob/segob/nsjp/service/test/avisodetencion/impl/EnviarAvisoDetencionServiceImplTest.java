/**
 * Nombre del Programa : EnviarAvisoDetencionServiceImplTest.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 8 Aug 2011
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
package mx.gob.segob.nsjp.service.test.avisodetencion.impl;

import java.util.Calendar;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.DetencionDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.persona.NombreDemograficoDTO;
import mx.gob.segob.nsjp.service.avisodetencion.EnviarAvisoDetencionService;
import mx.gob.segob.nsjp.service.test.base.BaseTestServicios;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class EnviarAvisoDetencionServiceImplTest
        extends
            BaseTestServicios<EnviarAvisoDetencionService> {
    public void testEnviarAvisoDetencion() {
        try {

            DetencionDTO ddto = new DetencionDTO();

            InvolucradoDTO invo = new InvolucradoDTO(1L);
            invo.addNombreDemografico(new NombreDemograficoDTO(1L, "Alex",
                    "Lora", "Rocks"));
            invo.setExpedienteDTO(new ExpedienteDTO(1L, "X", new CasoDTO(1L,
                    "YUC/PG/XX/PGE/2011/AA-00001")));
            ddto.setInvolucradoDTO(invo);
            ddto.setUsuario(super.getUsuario());
            Calendar cal = Calendar.getInstance();
            ddto.setFechaRecepcionDetencion(cal.getTime());
            cal.add(Calendar.HOUR_OF_DAY, -2);
            ddto.setFechaInicioDetencion(cal.getTime());
            Long idAgencia = 1L;
            String claveAgencia = "CLV";
            Long idFuncionarioSolicitante = 1L;
            super.service.enviarAvisoDetencion(ddto,idAgencia,claveAgencia,idFuncionarioSolicitante);
        } catch (NSJPNegocioException e) {
            fail(e.getMessage());
        }
    }

}
