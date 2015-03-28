/**
 * Nombre del Programa : CadenaDeCustodiaDelegateImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 29-jun-2011
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
package mx.gob.segob.nsjp.delegate.cadenadecustodia.impl;

import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.delegate.cadenadecustodia.CadenaDeCustodiaDelegate;
import mx.gob.segob.nsjp.dto.almacen.AlmacenDTO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.caso.CasoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaCustodiaXAlmacenService;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaCustodiaXExpedienteService;
import mx.gob.segob.nsjp.service.cadenadecustodia.ConsultarCadenaDeCustodiaXFolioService;
import mx.gob.segob.nsjp.service.cadenadecustodia.GuardarCadenaCustodiaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Service("cadenaDeCustodiaDelegate")
public class CadenaDeCustodiaDelegateImpl implements CadenaDeCustodiaDelegate {

    @Autowired
    private ConsultarCadenaCustodiaXExpedienteService
            consultarCadenaCustodiaXExpedienteService;
    @Autowired
    private GuardarCadenaCustodiaService guardarCadenaCustodiaService;
    @Autowired
    private ConsultarCadenaDeCustodiaXFolioService consultarCadenaCustodiaXFolioService;
    @Autowired
    private ConsultarCadenaCustodiaXAlmacenService consultarCadenaCustodiaXAlmacenService;


    /**
     * {@inheritDoc}
     */
    @Override
    public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXExpediente(
            ExpedienteDTO expedienteDto) throws NSJPNegocioException {
        return consultarCadenaCustodiaXExpedienteService.
                consultarCadenaCustodiaXExpediente(expedienteDto);
    }


	@Override
	public CadenaDeCustodiaDTO guardarCadenaCustodia(
			CadenaDeCustodiaDTO custodiaDTO, ExpedienteDTO expedienteDTO)
			throws NSJPNegocioException {
		return guardarCadenaCustodiaService.guardarCadenaCustodia(custodiaDTO, expedienteDTO);
	}


	@Override
	public CadenaDeCustodiaDTO consultarCadenaCustodia(
			CadenaDeCustodiaDTO cadenaDTO) throws NSJPNegocioException {
		return consultarCadenaCustodiaXExpedienteService.consultarCadenaCustodia(cadenaDTO);
	}

    @Override
    public CadenaDeCustodiaDTO consultarCadenaCustodiaXFolio(String folio)
            throws NSJPNegocioException {
        return consultarCadenaCustodiaXFolioService.consultarCadenaDeCustodiaXFolio(folio);
    }


	@Override
	public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXNumeroExpediente(
			Long expedienteId, String folioCadena)throws NSJPNegocioException {
		return consultarCadenaCustodiaXExpedienteService.consultarCadenaCustodiaXExpedienteYFolio(expedienteId,folioCadena);		
	}


	@Override
	public List<CadenaDeCustodiaDTO> consultarCadenaCustodiaXAlmacen(
			AlmacenDTO almacenDTO, CasoDTO casoDTO) throws NSJPNegocioException {
		return consultarCadenaCustodiaXAlmacenService.consultarCadenaCustodiaXAlmacen(almacenDTO,casoDTO);
	}

}
