/**
 * Nombre del Programa : IngresarEquipoDeComputoServiceImpl.java
 * Autor                            : Tattva-IT
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 4 May 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Implementacion del servicios equipo de computo
 * Programa Dependiente  :N/A
 * Programa Subsecuente :N/A
 * Cond. de ejecucion        :N/A
 * Dias de ejecucion          :N/A                             Horario: N/A
 *                              MODIFICACIONES
 *------------------------------------------------------------------------------
 * Autor                       :N/A
 * Compania               :N/A
 * Proyecto                 :N/A                                   Fecha: N/A
 * Modificacion           :N/A
 *------------------------------------------------------------------------------
 */
package mx.gob.segob.nsjp.service.objeto.impl;

import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.segob.nsjp.comun.enums.elemento.Elementos;
import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.objeto.Objetos;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.elemento.CalidadDAO;
import mx.gob.segob.nsjp.dao.objeto.EquipoComputoDAO;
import mx.gob.segob.nsjp.dto.cadenadecustoria.CadenaDeCustodiaDTO;
import mx.gob.segob.nsjp.dto.objeto.EquipoComputoDTO;
import mx.gob.segob.nsjp.dto.objeto.ObjetoDTO;
import mx.gob.segob.nsjp.model.Calidad;
import mx.gob.segob.nsjp.model.EquipoComputo;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.objeto.IngresarEquipoDeComputoService;
import mx.gob.segob.nsjp.service.objeto.impl.transform.EquipoComputoTransformer;

/**
 * Implementacion de servicios para ingresar equipo de computo
 * @version 1.0
 * @author Tattva-IT
 *
 */
@Service
@Transactional
public class IngresarEquipoDeComputoServiceImpl implements
        IngresarEquipoDeComputoService {

    private final static Logger logger = Logger.getLogger(IngresarEquipoDeComputoServiceImpl.class);
    @Autowired
    private EquipoComputoDAO equipoComputoDAO;
    /**
     * Objeto de Acceso a Datos para la entidad Calidad
     */
    @Autowired
    private CalidadDAO calidadDAO;

    @Autowired
    private GuardaOActualizaEvidencia guardaOActualizaEvidencia;


    @Override
    public Long ingresarEquipoComputo(EquipoComputoDTO equipoComputoDto)
            throws NSJPNegocioException {
        if(equipoComputoDto == null){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        if(equipoComputoDto.getElementoId() == null ||equipoComputoDto.getElementoId() == 0){
            return guarda(equipoComputoDto);
        }else{
            return actualiza(equipoComputoDto);
        }
    }

    private Long guarda(EquipoComputoDTO equipoComputoDto) throws NSJPNegocioException {
        Long idCalidad = 0L;
        Long idEquipoComputo = 0L;
        EquipoComputo eqc = new EquipoComputo();
        Calidad cal = new Calidad();
        if (logger.isDebugEnabled()) {
            logger.debug("Inicia almacenamiento de Equipo de computo");
        }
        if (equipoComputoDto == null) {
            logger.error("El equipo de computo es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        if (equipoComputoDto.getExpedienteDTO() == null && equipoComputoDto.getExpedienteDTO().getExpedienteId() == null) {
            logger.error("El expediente es requerido para el guardado");
            throw new NSJPNegocioException(CodigoError.FORMATO);
        }
        eqc = EquipoComputoTransformer.transformarEquipoComputo(equipoComputoDto);
        logger.debug("Verificando Calidad");
        if (equipoComputoDto.getCalidadDTO() != null && equipoComputoDto.getCalidadDTO().getDescripcionEstadoFisico() != null) {
            cal.setDescripcionEstadoFisico(equipoComputoDto.getCalidadDTO().getDescripcionEstadoFisico());
        }
        if (equipoComputoDto.getCalidadDTO() != null && equipoComputoDto.getCalidadDTO().getCalidades() != null) {
            cal.setTipoCalidad(new Valor(equipoComputoDto.getCalidadDTO().getCalidades().getValorId()));
        }
        idCalidad = this.calidadDAO.create(cal);
        logger.debug("Insertando Calidad " + idCalidad);
        cal.setCalidadId(idCalidad);
        eqc.setCalidad(cal);
        eqc.setValorByTipoObjetoVal(new Valor(Objetos.EQUIPO_DE_COMPUTO.getValorId()));
        eqc.setTipoElemento(new Valor(Elementos.OBJETO.getValorId()));
        idEquipoComputo = this.equipoComputoDAO.create(eqc);
        equipoComputoDto.setElementoId(idEquipoComputo);
        guardaOActualizaObjetos(equipoComputoDto);
        logger.debug("Se genero el vehiculo con id: " + idEquipoComputo);
        return idEquipoComputo;
    }


    private void guardaOActualizaObjetos(EquipoComputoDTO equipoComputoDto) throws NSJPNegocioException {
        CadenaDeCustodiaDTO cadenaDeCustodia = equipoComputoDto.getCadenaDeCustodia();
        if (cadenaDeCustodia != null) {
            guardaOActualizaEvidencia.guardaOActualizaEvidencia(equipoComputoDto);
        }
    }

    private Long actualiza(EquipoComputoDTO equipoComputoDto) throws NSJPNegocioException {
        if(equipoComputoDto.getElementoId() <= 0){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        EquipoComputo actualizador =
                EquipoComputoTransformer.transformarEquipoComputo(equipoComputoDto);
        EquipoComputo actualizable = equipoComputoDAO.read(actualizador.getElementoId());
        IngresarVehiculoServiceImpl.actualizaObjeto(actualizador, actualizable);
        guardaOActualizaObjetos(equipoComputoDto);
        equipoComputoDAO.merge(actualizable);
        return actualizable.getElementoId();
    }
}
