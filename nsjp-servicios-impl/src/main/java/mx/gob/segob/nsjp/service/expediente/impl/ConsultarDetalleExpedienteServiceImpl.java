/**
 * Nombre del Programa : ConsultarDetalleExpedienteServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 05-jul-2011
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
package mx.gob.segob.nsjp.service.expediente.impl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.expediente.ExpedienteDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Expediente;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.service.expediente.ConsultarDetalleExpedienteService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ExpedienteTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.JerarquiaOrganizacionalTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Transactional
public class ConsultarDetalleExpedienteServiceImpl implements
        ConsultarDetalleExpedienteService {

    /**
     * Logger de la clase.
     */
    private final static Logger logger = Logger.getLogger(ConsultarDetalleExpedienteServiceImpl.class);
    @Autowired
    private ExpedienteDAO expedienteDao;

    @Autowired
    private NumeroExpedienteDAO numeroExpedienteDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public ExpedienteDTO consultarDetalleExpediente(ExpedienteDTO expedienteDto,
            UsuarioDTO usuarioDto) throws NSJPNegocioException {
        if (expedienteDto == null || usuarioDto == null ||
                expedienteDto.getExpedienteId() == null
                || usuarioDto.getIdUsuario() == null) {
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Expediente expediente =
                ExpedienteTransformer.transformarExpediente(expedienteDto);
        Usuario usuario = UsuarioTransformer.transformarDTO(usuarioDto);
        Expediente consultado =
                expedienteDao.consultarDetalleExpediente(expediente, usuario);
        ExpedienteDTO detalle = null;
        if(consultado != null){
            detalle = ExpedienteTransformer.transformaExpediente(consultado);
            String numeroExpedienteConsultado = consultado.getNumeroExpediente();
            if (logger.isDebugEnabled()) {
                logger.debug("numeroExpedienteConsultado = " + numeroExpedienteConsultado);
            }
            // La etapa la ponemos como la etapa del numero de expediente.
            NumeroExpediente numeroExpediente = numeroExpedienteDao.
                    obtenerNumeroExpediente(numeroExpedienteConsultado,null);
            if (logger.isDebugEnabled()) {
                logger.debug("numeroExpediente = " + numeroExpediente);
                if (numeroExpediente != null) {
                    logger.debug("numeroExpediente.getEtapa() = " +
                            numeroExpediente.getEtapa());
                }
            }
            if(numeroExpediente.getEtapa()!=null)
            	detalle.setEtapa(new ValorDTO(numeroExpediente.getEtapa().getValorId()));
        }
        return detalle;
    }

	@Override
	public JerarquiaOrganizacionalDTO consultarOrigendeExpediente(
			ExpedienteDTO expediente) throws NSJPNegocioException {
		
		if (logger.isDebugEnabled()) {
            logger.debug("***SERVICIO PARA CONSULTAR EL ORIGEN DE UN EXPEDIENTE***");
        }
		if(expediente==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(expediente.getExpedienteId()==null && expediente.getNumeroExpediente()==null)
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		if(expediente.getExpedienteId()==null || expediente.getExpedienteId()==0L)
			expediente.setExpedienteId(expedienteDao.consultarExpedientePorNumeroExpediente(expediente.getNumeroExpediente()));
		
		JerarquiaOrganizacional area =expedienteDao.consultarOrigendeExpediente(expediente.getExpedienteId());
		return JerarquiaOrganizacionalTransformer.transformarJerarquiaOrganizacional(area);
	}
}
