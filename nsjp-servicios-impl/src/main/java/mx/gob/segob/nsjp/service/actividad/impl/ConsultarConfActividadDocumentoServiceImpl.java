/**
 * Nombre del Programa : ConsultarConfActividadDocumentoServiceImpl.java
 * Autor                            : Jacob Lobaco
 * Compania                         : Ultrasist
 * Proyecto                         : NSJP                    Fecha: 06-jul-2011
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
package mx.gob.segob.nsjp.service.actividad.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.TipoJerarquia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.actividad.ConfActividadDocumentoDAO;
import mx.gob.segob.nsjp.dao.expediente.ActividadDAO;
import mx.gob.segob.nsjp.dao.expediente.NumeroExpedienteDAO;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrganizacionalDAO;
import mx.gob.segob.nsjp.dao.usuario.UsuarioDAO;
import mx.gob.segob.nsjp.dto.ActividadDTO;
import mx.gob.segob.nsjp.dto.ConfActividadDocumentoDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;
import mx.gob.segob.nsjp.model.Actividad;
import mx.gob.segob.nsjp.model.ConfActividadDocumento;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.Usuario;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.actividad.ConsultarConfActividadDocumentoService;
import mx.gob.segob.nsjp.service.expediente.impl.transform.ActividadTransformer;
import mx.gob.segob.nsjp.service.expediente.impl.transform.UsuarioTransformer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @version 1.0
 * @author Jacob Lobaco
 */
@Repository
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ConsultarConfActividadDocumentoServiceImpl implements
        ConsultarConfActividadDocumentoService {

    /**
      * Logger de la clase.
      */
    private final static Logger logger = Logger
            .getLogger(ConsultarConfActividadDocumentoServiceImpl.class);

    @Autowired
    private UsuarioDAO usuarioDao;

    @Autowired
    private ConfActividadDocumentoDAO confActividadDocumentoDao;

    @Autowired
    private NumeroExpedienteDAO NumeroExpedienteDAO;
    
    @Autowired
    private JerarquiaOrganizacionalDAO jerarquiaDAO;
    @Autowired
    private ActividadDAO actividadDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConfActividadDocumentoDTO> consultarConfActividadDocumento(
            UsuarioDTO usuarioDto, ExpedienteDTO expedienteDto, Long idCategoriaActividad)
            throws NSJPNegocioException {
        if(usuarioDto == null || usuarioDto.getIdUsuario() == null ||expedienteDto == null ||
          (expedienteDto.getNumeroExpediente() == null && expedienteDto.getNumeroExpedienteId() == null)){
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Usuario usuario = UsuarioTransformer.transformarDTO(usuarioDto);
        List<ConfActividadDocumentoDTO> configuracionesDto = Collections.EMPTY_LIST;
        if(usuario.getFuncionario() == null ||
                usuario.getFuncionario().getArea() == null){
            usuario = usuarioDao.read(usuarioDto.getIdUsuario());
        }
        NumeroExpediente numeroExpediente;
        if(expedienteDto.getNumeroExpedienteId() != null && expedienteDto.getNumeroExpedienteId() > 0){
        	numeroExpediente = NumeroExpedienteDAO.read(expedienteDto.getNumeroExpedienteId());
        }else{
        	numeroExpediente = NumeroExpedienteDAO.obtenerNumeroExpediente(expedienteDto.getNumeroExpediente(),null);
        }
        
        if (numeroExpediente == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("No ex posible encontrar el expediente con numero = "
                        + expedienteDto.getNumeroExpediente());
            }
            throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
        }
        Funcionario funcionario = usuario.getFuncionario();
        List<ConfActividadDocumento> configuraciones =
                confActividadDocumentoDao.
                consultarConfActividadDocumento(
                funcionario, numeroExpediente, idCategoriaActividad);
        if(!configuraciones.isEmpty()){
            configuracionesDto = new LinkedList<ConfActividadDocumentoDTO>();
            for (ConfActividadDocumento confActividadDocumento : configuraciones) {
                ConfActividadDocumentoDTO configuracionDto =
                        ConfActividadDocumentoTransformer.
                        transformarConfActividadDocumento(confActividadDocumento);
                configuracionesDto.add(configuracionDto);
            }
        }
        return configuracionesDto;
    }
    
    
    public ConfActividadDocumentoDTO consultaConfActividadDocumentoPorId(Long idConfActividadDocumento) throws NSJPNegocioException{
    	logger.info("Servicion consultaConfActividadDocumentoPorIdActividad");
    	if(idConfActividadDocumento==null || idConfActividadDocumento<0)
    		throw new  NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
    	
    	ConfActividadDocumentoDTO confActividadDocumentoDTO = new ConfActividadDocumentoDTO();
    	
//    	ConfActividadDocumento confActividadDocumento = confActividadDocumentoDao.consultaConfActividadDocumentoPorIdActividad(idTipoActividad);
    	ConfActividadDocumento confActividadDocumento = confActividadDocumentoDao.read(idConfActividadDocumento);
    	
    	if(confActividadDocumento!= null)
    		confActividadDocumentoDTO = ConfActividadDocumentoTransformer.transformarConfActividadDocumento(confActividadDocumento);
    	
    	return confActividadDocumentoDTO;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ValorDTO> consultarEstadosPorJerarquiaOrganizacional(Long idJerarquiaOrganizacional) throws NSJPNegocioException{
    	if(idJerarquiaOrganizacional==null || idJerarquiaOrganizacional<0L)
    		throw new  NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
    	Set<ValorDTO> listaEstatus = new HashSet<ValorDTO>();
    	
    	List<Valor> listaValores = confActividadDocumentoDao.consultarEstadosDistintosPorJerarquiaOrganizacional(idJerarquiaOrganizacional);
    	
    	//Obtener el departamento del área
		logger.info("Obtener el área al que pertenece el departamento: "+ idJerarquiaOrganizacional );
		JerarquiaOrganizacional departamento  = jerarquiaDAO.read(idJerarquiaOrganizacional);
		
		//Verificar que sea un departamento para obtener el área 
		if( departamento.getTipoJerarquia().getValorId().equals(TipoJerarquia.DEPARTAMENTO.getValorId() )){
			JerarquiaOrganizacional area = departamento.getJerarquiaOrgResponsable();
			logger.info("Obtener el estatus para el area: " + area.getJerarquiaOrganizacionalId());
			List<Valor> listaValoresArea = confActividadDocumentoDao.consultarEstadosDistintosPorJerarquiaOrganizacional(area.getJerarquiaOrganizacionalId());
			
			listaValores.addAll(listaValoresArea);
		}
    	
    	for (Valor valor : listaValores) 
			listaEstatus.add(new ValorDTO(valor.getValorId(), valor.getValor()));
    	
    	List<ValorDTO> listaOrdenada = new ArrayList<ValorDTO>(listaEstatus);
    	Collections.sort(listaOrdenada);
    	return listaOrdenada;
    }
    
    @Override
	public List<ActividadDTO>  consultarActividadesPorTipoActividadExpedienteId (Long idExpediente, List<Long> idTipoActividades, Boolean documentoRec)throws NSJPNegocioException{
		
		logger.info(" consultarActividadesPorTipoActividadExpedienteId : ");
		logger.info(" idExpediente : " + idExpediente);
		logger.info(" idTipoActividades : " + idTipoActividades);
		List<Actividad> actividaes = actividadDao.consultarActividadesPorTipoActividadExpedienteId(idExpediente, idTipoActividades, documentoRec);
		
		List<ActividadDTO> actividadesDTO = new ArrayList<ActividadDTO>();
		for (Actividad actividad : actividaes) {
			actividadesDTO.add( ActividadTransformer.transformarActividad(actividad) );
		}
		
		return actividadesDTO;
	}
}
