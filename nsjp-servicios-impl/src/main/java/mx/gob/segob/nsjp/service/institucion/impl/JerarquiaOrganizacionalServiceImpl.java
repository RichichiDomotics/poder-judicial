/**
* Nombre del Programa : JerarquiaOrganizacionalServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 29/06/2011
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
package mx.gob.segob.nsjp.service.institucion.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.institucion.Areas;
import mx.gob.segob.nsjp.comun.enums.institucion.TipoJerarquia;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.comun.util.ConsecutivosUtil;
import mx.gob.segob.nsjp.dao.institucion.ConfInstitucionDAO;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrgTipoSolicitudDAO;
import mx.gob.segob.nsjp.dao.institucion.JerarquiaOrganizacionalDAO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;
import mx.gob.segob.nsjp.dto.institucion.InstitucionDTO;
import mx.gob.segob.nsjp.dto.institucion.JerarquiaOrganizacionalDTO;
import mx.gob.segob.nsjp.model.ConfInstitucion;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.expediente.impl.transform.JerarquiaOrganizacionalTransformer;
import mx.gob.segob.nsjp.service.institucion.JerarquiaOrganizacionalService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class JerarquiaOrganizacionalServiceImpl implements JerarquiaOrganizacionalService {
	/**
     * Logger.
     */
    private final static Logger logger = Logger.getLogger(JerarquiaOrganizacionalServiceImpl.class);
    
    @Autowired
    private JerarquiaOrganizacionalDAO jerarquiaOrganizacionalDAO; 
    
    @Autowired
    private JerarquiaOrgTipoSolicitudDAO jerarquiaOrgTipoSolicitudDAO;
    
    @Autowired
    private ConfInstitucionDAO confInsDao;
    
	
	@Override
	public String consultarPrefijo(InstitucionDTO institucionDTO) throws NSJPNegocioException{
		String prefijo= "";
		
		if (logger.isDebugEnabled())
			logger.debug("/**** Servicio para consultar un prefijo de una institucion ****/");
		
		if( institucionDTO== null || institucionDTO.getInstitucionId()==null )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		JerarquiaOrganizacional jerarquiaOrganizacional =  jerarquiaOrganizacionalDAO.read(institucionDTO.getInstitucionId());

		//Verificar que el Id pasado como parámetro sea de una área (Unidad) 
		if ( jerarquiaOrganizacional == null || !jerarquiaOrganizacional.getTipoJerarquia().getValorId().equals( TipoJerarquia.AREA.getValorId()) )
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		//Prefijo del Area/Unidad
		prefijo = jerarquiaOrganizacional.getAbreviatura();
		jerarquiaOrganizacional  = jerarquiaOrganizacional.getJerarquiaOrgResponsable();
		//Prefijo del Distrito
		if ( jerarquiaOrganizacional!= null && jerarquiaOrganizacional.getTipoJerarquia().getValorId().equals( TipoJerarquia.DISTRITO.getValorId())){
			prefijo =  jerarquiaOrganizacional.getAbreviatura()+ ConsecutivosUtil.SEPARADOR_PREFIJOS +prefijo;			
			jerarquiaOrganizacional  = jerarquiaOrganizacional.getJerarquiaOrgResponsable();
		}
		else
			prefijo = "--" + ConsecutivosUtil.SEPARADOR_PREFIJOS +prefijo;

		//Prefijo de Institución
		if ( jerarquiaOrganizacional!= null && jerarquiaOrganizacional.getTipoJerarquia().getValorId().equals( TipoJerarquia.INSTITUCION.getValorId()))
			prefijo =  jerarquiaOrganizacional.getAbreviatura()+ ConsecutivosUtil.SEPARADOR_PREFIJOS +prefijo;			
		else
			prefijo = "--" +ConsecutivosUtil.SEPARADOR_PREFIJOS+prefijo;

		//FIXME Determinar la longitud minima y maxima del prefijo 
		//Se verifica si se requieren los caracteres de relleno
		if( prefijo.length() < ConsecutivosUtil.LONGITUD_PREFIJO_SIN_FECHA - 2){
			prefijo = '/' + prefijo;
			prefijo = StringUtils.leftPad(prefijo, ConsecutivosUtil.LONGITUD_PREFIJO_SIN_FECHA, ConsecutivosUtil.CARACTER_LIBRE );
		}

		logger.info("Prefijo Obtenido:"+prefijo);
		return prefijo;
	}
	
	
	public List<ValorDTO> consultarTipoSolictudesPorJerarquiaOrganizacional(Long idJerarquiaOrganizacional)
			throws NSJPNegocioException  {
		List<ValorDTO> listaValoresTipoSolicitudDTO = new ArrayList<ValorDTO>();
		
		List<Valor> listaValoresTipoSolicitud = jerarquiaOrgTipoSolicitudDAO.consultarTipoSolicitudPorJerarquiaOrganizacional(idJerarquiaOrganizacional);
		
		if(listaValoresTipoSolicitud!= null ){
			for (Valor valor : listaValoresTipoSolicitud) {
				ValorDTO valorDTO = new ValorDTO(valor.getValorId());
				valorDTO.setValor(valor.getValor());
				if(valor.getCampoCatalogo()!= null)
					valorDTO.setNombreCampo(valor.getCampoCatalogo().getNombreCampo());

				listaValoresTipoSolicitudDTO.add(valorDTO);
			}
		}
		return listaValoresTipoSolicitudDTO;
	}
	
	public List<JerarquiaOrganizacionalDTO> consultarDepartamentosExceptoAreasYDepartamentos(Long jerarquiaResponsableId, 
			List<Long> idsAreasDescartar, List<Long> idsDepartamentoDescartar)	throws NSJPNegocioException  {
		List<JerarquiaOrganizacionalDTO> listaJerarquiaOrganizacionalDTO = new ArrayList<JerarquiaOrganizacionalDTO>();
		
		if(jerarquiaResponsableId==null ){
			ConfInstitucion confInstitucion =  confInsDao.consultarInsitucionActual();
			jerarquiaResponsableId = confInstitucion.getConfInstitucionId();
		}
		List<JerarquiaOrganizacional> listaJerarquiaOrganizacional = jerarquiaOrganizacionalDAO.consultarJerarquiaOrganizacionalDependienteExcepto(
				jerarquiaResponsableId, idsAreasDescartar);
		
		if(listaJerarquiaOrganizacional!= null ){
			for (JerarquiaOrganizacional jerarquiaOrg : listaJerarquiaOrganizacional) {
				//Obtener los departamentos
				
				
				List<JerarquiaOrganizacional> departamentos = new ArrayList<JerarquiaOrganizacional>();
				
				if(jerarquiaOrg.getJerarquiaOrganizacionalId() != Areas.AGENCIA_DEL_MINISTERIO_PUBLICO.ordinal()){

					 departamentos =  
						jerarquiaOrganizacionalDAO.consultarDepartamentosByPadre(jerarquiaOrg.getJerarquiaOrganizacionalId());
				}
				else{
					departamentos =  
						jerarquiaOrganizacionalDAO.consultarDepartamentosAgenciaDelMinisterioPublico(jerarquiaOrg.getJerarquiaOrganizacionalId());
				}
				
				
				
				
				//Transformar los departamentos
				List<JerarquiaOrganizacionalDTO> departamentosDTO = new ArrayList<JerarquiaOrganizacionalDTO>();
				for (JerarquiaOrganizacional area : departamentos) {
					if( !idsDepartamentoDescartar.contains(area.getJerarquiaOrganizacionalId()))
						departamentosDTO.add(JerarquiaOrganizacionalTransformer.transformarJerarquiaOrganizacional(area));
				} 
				listaJerarquiaOrganizacionalDTO.addAll(departamentosDTO);
			}
		}
		return listaJerarquiaOrganizacionalDTO;
	}
}
