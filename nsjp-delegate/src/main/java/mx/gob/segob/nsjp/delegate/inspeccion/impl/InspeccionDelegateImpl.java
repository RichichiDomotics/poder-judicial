/**
 * Nombre del Programa : InspeccionDelegateImpl.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 19 Oct 2011
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
package mx.gob.segob.nsjp.delegate.inspeccion.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.inspeccion.InspeccionDelegate;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.inspeccion.InspeccionDTO;
import mx.gob.segob.nsjp.dto.inspeccion.MultaSancionDTO;
import mx.gob.segob.nsjp.service.insepeccion.AdministrarInspeccionService;
import mx.gob.segob.nsjp.service.insepeccion.AdministrarMultaSancionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Describir el objetivo de la clase con punto al final.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
@Service
public class InspeccionDelegateImpl implements InspeccionDelegate {
    @Autowired
    private AdministrarInspeccionService adminInspeccionService;
    @Autowired
    private AdministrarMultaSancionService adminMultaService;
    @Override
    public Long registrarMulta(MultaSancionDTO input)
            throws NSJPNegocioException {
        return this.adminMultaService.registrarMulta(input);
    }

    @Override
    public List<MultaSancionDTO> consultarMultas(FuncionarioDTO funMultado)
            throws NSJPNegocioException {
        return this.adminMultaService.consultarMultas(funMultado);
    }

    @Override
    public void actualizarDescripcion(MultaSancionDTO input)
            throws NSJPNegocioException {
        this.adminMultaService.actualizarDescripcion(input);
    }

    @Override
    public void saldarMulta(MultaSancionDTO input) throws NSJPNegocioException {
        this.adminMultaService.saldarMulta(input);
    }

    @Override
    public List<InspeccionDTO> consultarInspecciones(
            FuncionarioDTO funInspeccionado, ExpedienteDTO expInspeccionado)
            throws NSJPNegocioException {
        return this.adminInspeccionService.consultarInspecciones(
                funInspeccionado, expInspeccionado);
    }

    @Override
    public Long registrarInspeccion(InspeccionDTO input)
            throws NSJPNegocioException {
        return this.adminInspeccionService.registrarInspeccion(input);
    }
    @Override
    public void actualizarDescripcion(InspeccionDTO input)
            throws NSJPNegocioException {
        this.adminInspeccionService.actualizarDescripcion(input);
    }

    @Override
    public void concluirInspeccion(InspeccionDTO input)
            throws NSJPNegocioException {
        this.adminInspeccionService.concluirInspeccion(input);
    }

    @Override
    public MultaSancionDTO obtenerMulta(Long idMulta)
            throws NSJPNegocioException {
        return this.adminMultaService.obtenerMulta(idMulta);
    }

    @Override
    public InspeccionDTO obtenerInspeccion(Long idInspeccion)
            throws NSJPNegocioException {
        return this.adminInspeccionService.obtenerInspeccion(idInspeccion);
    }

}
