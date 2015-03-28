/**
* Nombre del Programa : IngresarObraArteServiceImpl.java
* Autor                            : Hugo Serrano
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 13 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicios para ingresar Obra de Arte
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
package mx.gob.segob.nsjp.service.objeto.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.ObraArteDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.elemento.CalidadDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.objeto.ObraArteDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.ObraArte;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarObraArteService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.ObraArteTransformer;

/**
 * Implementacion del servicios para ingresar Obra de Arte
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarObraArteServiceImpl implements IngresarObraArteService {

	private final static Logger logger = Logger.getLogger(IngresarArmaServiceImpl.class);
	
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;
    
    @Autowired
    private ObraArteDAO obraArteDAO;
    
    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;
	
	/**
     * {@inheritDoc}
     */
    @Override
    public Long ingresarObraArte(ObraArteDTO obraArteDto)
            throws NSJPNegocioException {
        if (obraArteDto == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Long elementoId = obraArteDto.getElementoId();
        if (elementoId == null || elementoId == 0) {
            return guarda(obraArteDto);
        } else {
            return actualiza(obraArteDto);
        }
    }

    private Long guarda(ObraArteDTO obraArteDto) throws NSJPNegocioException {
        Long idObraArte = 0L;
        ObraArte obraArte = new ObraArte();
        Long idCalidad = 0L;
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento del ObraArte");
        }
        if (obraArteDto == null) {
            logger.error("El obraArte es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        ExpedienteDTO expedienteDTO = obraArteDto.getExpedienteDTO();
        if (expedienteDTO == null || expedienteDTO.getExpedienteId() == null) {
            logger.error("El obraArte es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        logger.info("ObraArte    !!!!!!!");
        obraArte = ObraArteTransformer.transformarObraArte(obraArteDto);
        CalidadDTO calidadDTO = obraArteDto.getCalidadDTO();
        if (calidadDTO != null && calidadDTO.getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(calidadDTO.getDescripcionEstadoFisico());
        }
        if (calidadDTO != null && calidadDTO.getCalidades() != null) {
            cal.setTipoCalidad(new Valor(calidadDTO.getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        obraArte.setCalidad(this.calidadDAO.read(idCalidad));
        obraArte.setValorByTipoObjetoVal(new Valor(Objetos.OBRA_DE_ARTE.getValorId()));
        obraArte.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idObraArte = this.obraArteDAO.create(obraArte);
        obraArteDto.setElementoId(idObraArte);
        guardaOActualizaObjetos(obraArteDto);
        logger.debug("Se genero el obraArte con id: " + idObraArte);
        return idObraArte;
    }

    private Long actualiza(ObraArteDTO obraArteDto) throws NSJPNegocioException {
        Long elementoId = obraArteDto.getElementoId();
        if (elementoId < 0) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        ObraArte actualizable = obraArteDAO.read(elementoId);
        ObraArte actualizador = ObraArteTransformer.transformarObraArte(obraArteDto);
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        return elementoId;
    }

    private void guardaOActualizaObjetos(ObraArteDTO obraDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = obraDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(obraDto);
        }
    }
}
