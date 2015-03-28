/**
* Nombre del Programa : EliminarInvolucradoServiceImpl.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 24 May 2011
* Marca de cambio        : N/A
* Descripcion General    : Implementacion del servicio para eliminar un involucrado
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
package mx.gob.segob.nsjp.service.involucrado.impl;

import java.util.List;

import javax.xml.bind.helpers.ParseConversionEventImpl;

import mx.gob.segob.nsjp.comun.enums.excepciones.CodigoError;
import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.domicilio.DomicilioDAO;
import mx.gob.segob.nsjp.dao.involucrado.AliasInvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.BiometricoDAO;
import mx.gob.segob.nsjp.dao.involucrado.DetencionDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoNacionalidadDAO;
import mx.gob.segob.nsjp.dao.involucrado.InvolucradoOcupacionDAO;
import mx.gob.segob.nsjp.dao.involucrado.PerfilDAO;
import mx.gob.segob.nsjp.dao.persona.CorreoElectronicoDAO;
import mx.gob.segob.nsjp.dao.persona.DelitoPersonaDAO;
import mx.gob.segob.nsjp.dao.persona.NombreDemograficoDAO;
import mx.gob.segob.nsjp.dao.persona.TelefonoDAO;
import mx.gob.segob.nsjp.dao.relacion.RelacionDAO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.model.AliasInvolucrado;
import mx.gob.segob.nsjp.model.Biometrico;
import mx.gob.segob.nsjp.model.CorreoElectronico;
import mx.gob.segob.nsjp.model.DelitoPersona;
import mx.gob.segob.nsjp.model.Detencion;
import mx.gob.segob.nsjp.model.Domicilio;
import mx.gob.segob.nsjp.model.Involucrado;
import mx.gob.segob.nsjp.model.InvolucradoNacionalidad;
import mx.gob.segob.nsjp.model.InvolucradoOcupacion;
import mx.gob.segob.nsjp.model.NombreDemografico;
import mx.gob.segob.nsjp.model.Perfil;
import mx.gob.segob.nsjp.model.Relacion;
import mx.gob.segob.nsjp.model.Telefono;
import mx.gob.segob.nsjp.service.involucrado.EliminarInvolucradoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementacion del servicio para eliminar un involucrado.
 * @version 1.0
 * @author cesarAgustin
 *
 */
@Service
@Transactional
public class EliminarInvolucradoServiceImpl implements
		EliminarInvolucradoService {

	/**
	 * 
	 */
	public final static Logger logger = Logger.getLogger(EliminarInvolucradoServiceImpl.class);

	@Autowired
	private AliasInvolucradoDAO aliasInvolucrado;
	@Autowired
	private NombreDemograficoDAO nombreDemograficoDAO;
	@Autowired
	private BiometricoDAO biometricoDAO;
	@Autowired
	private PerfilDAO perfilDAO;
	@Autowired
	private TelefonoDAO telefonoDAO;
	@Autowired
	private CorreoElectronicoDAO correoElectronicoDAO;
	@Autowired
	private InvolucradoNacionalidadDAO involucradoNacionalidadDAO;
	@Autowired
	private InvolucradoOcupacionDAO involucradoOcupacionDAO;
	@Autowired
	private DelitoPersonaDAO delitoPersonaDAO;
	@Autowired
	private InvolucradoDAO involucradoDAO;
	@Autowired
	private RelacionDAO relacionDAO;
	@Autowired
	private DetencionDAO detencionDAO;
	@Autowired
	private DomicilioDAO domicilioDAO;
	
	
	@Override
	public void eliminarInvolucrado(InvolucradoDTO involucradoDTO)
			throws NSJPNegocioException {
		
		if (logger.isDebugEnabled())
			logger.debug("/**** SERVICIO PARA ELIMINAR UN INVOLUCRADO ****/");
		
		if (!(involucradoDTO!=null && involucradoDTO.getElementoId()!=null))
			throw new NSJPNegocioException(CodigoError.PARAMETROS_INSUFICIENTES);
		
		Involucrado involucrado = involucradoDAO.read(involucradoDTO.getElementoId());
		
		//Verificar y eliminar detenciones del involucrado		
		List<Detencion> listDetenciones = detencionDAO.consultarDetencionByInvolucrado(involucrado.getElementoId());
		if(listDetenciones!=null && listDetenciones.size()>0)
			detencionDAO.deleteAll(listDetenciones);
		
		//Verificar y eliminar alias del involucrado
		List<AliasInvolucrado> listAlias = aliasInvolucrado.consultarAliasByInvolucrado(involucrado.getElementoId());
		if (listAlias!=null && listAlias.size()>0)
			aliasInvolucrado.deleteAll(listAlias);
		
		//Verificar y eliminar nombres del involucrado
		List<NombreDemografico> listNombres = nombreDemograficoDAO.consutarNombresByInvolucrado(involucrado.getElementoId());
		if (listNombres!=null && listNombres.size()>0)
			nombreDemograficoDAO.deleteAll(listNombres);
		
		//Verificar y eliminar biometricos del involucrado
		List<Biometrico> listBiometrico = biometricoDAO.consultarBiometricoByInvolucrado(involucrado.getElementoId());
		if (listBiometrico!=null && listBiometrico.size()>0)
			biometricoDAO.deleteAll(listBiometrico);
		
		//Verificar y eliminar perfiles del involucrado
		List<Perfil> listPerfiles = perfilDAO.consultarPerfilByInvolucrado(involucrado.getElementoId());
		if (listPerfiles!=null && listPerfiles.size()>0)
			perfilDAO.deleteAll(listPerfiles);
		
		//Verificar y eliminar medios de contacto del involucrado
		List<Telefono> listTelefonos = telefonoDAO.consultarTelefonosByPersona(involucrado.getElementoId());
		if (listTelefonos!=null && listTelefonos.size()>0)
			telefonoDAO.deleteAll(listTelefonos);
		List<CorreoElectronico> listCorreos = correoElectronicoDAO.consultarCorreosByPersona(involucrado.getElementoId());
		if (listCorreos!=null && listCorreos.size()>0)
			correoElectronicoDAO.deleteAll(listCorreos);				
		
		//Verificar y eliminar idiomas del involucrado
		List<InvolucradoNacionalidad> listNacionalidad = involucradoNacionalidadDAO.consultarNacionalidadByInvolucrado(involucrado.getElementoId());
		if (listNacionalidad!=null && listNacionalidad.size()>0)
			involucradoNacionalidadDAO.deleteAll(listNacionalidad);		
		
		//Verificar y eliminar ocupaciones del involucrado
		List<InvolucradoOcupacion> listOcupacion = involucradoOcupacionDAO.consultarOcupacionByInvolucrado(involucrado.getElementoId());
		if (listOcupacion!=null && listOcupacion.size()>0)
			involucradoOcupacionDAO.deleteAll(listOcupacion);
		
		//Verificar y eliminar delitos del involucrado
		List<DelitoPersona> listDelPer = delitoPersonaDAO.consultarDelitoPerByInvolucrado(involucrado.getElementoId());
		if (listDelPer!=null && listDelPer.size()>0)
			delitoPersonaDAO.deleteAll(listDelPer);
		
		//Verificar y eliminar relacicones del involucrado
		List<Relacion> listRelacion = relacionDAO.consultarRelacionByElemento(involucrado.getElementoId());
		if (listRelacion!=null && listRelacion.size()>0) {
			for (Relacion relacion : listRelacion) {
				if (relacion.getCatRelacion().getCatRelacionId().equals(new Long(Relaciones.RESIDENCIA.ordinal())) 
						|| relacion.getCatRelacion().getCatRelacionId().equals(new Long(Relaciones.NOTIFICACION.ordinal()))) {					
					Domicilio domicilio = domicilioDAO.read(relacion.getElementoByComplementoId().getElementoId());
					logger.debug("/**** ELIMINAR DOMICILIO ****/ " + domicilio.getElementoId());
					domicilioDAO.delete(domicilio);				
				}
			
		
			}
			//relacionDAO.deleteAll(listRelacion);
		}
		
		logger.debug("/**** ELIMINAR INVOLUCRADO ****/ " + involucrado.getElementoId());
		involucradoDAO.delete(involucrado);	
	}

	}
