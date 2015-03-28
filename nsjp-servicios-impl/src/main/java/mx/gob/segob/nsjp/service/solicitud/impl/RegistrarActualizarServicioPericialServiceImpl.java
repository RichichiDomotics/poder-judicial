/**
* Nombre del Programa : RegistrarActualizarServicioPericialServiceImpl.java
* Autor                            : GustavoBP
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 23/06/2011
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
package mx.gob.segob.nsjp.service.solicitud.impl;

import mx.gob.segob.nsjp.comun.enums.relacion.Relaciones;
import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dao.funcionario.FuncionarioDAO;
import mx.gob.segob.nsjp.dao.solicitud.SolicitudPericialDAO;
import mx.gob.segob.nsjp.dto.elemento.ElementoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.funcionario.FuncionarioDTO;
import mx.gob.segob.nsjp.dto.solicitud.SolicitudPericialDTO;
import mx.gob.segob.nsjp.model.Forma;
import mx.gob.segob.nsjp.model.Funcionario;
import mx.gob.segob.nsjp.model.JerarquiaOrganizacional;
import mx.gob.segob.nsjp.model.NumeroExpediente;
import mx.gob.segob.nsjp.model.SolicitudPericial;
import mx.gob.segob.nsjp.model.Valor;
import mx.gob.segob.nsjp.service.documento.GenerarRelacionDocumentoElementoService;
import mx.gob.segob.nsjp.service.funcionario.impl.transform.FuncionarioTransformer;
import mx.gob.segob.nsjp.service.solicitud.GenerarFolioSolicitudService;
import mx.gob.segob.nsjp.service.solicitud.RegistrarActualizarServicioPericialService;
import mx.gob.segob.nsjp.service.solicitud.impl.transform.SolicitudPericialTransformer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contraro del servicio para el manejo de Servicios Periciales.
 * @version 1.0
 * @author GustavoBP
 *
 */
@Service
@Transactional
public class RegistrarActualizarServicioPericialServiceImpl implements RegistrarActualizarServicioPericialService{
	
	@Autowired
	private SolicitudPericialDAO solicitudPericialDAO;
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	@Autowired
	private GenerarRelacionDocumentoElementoService relacionService;
    @Autowired
    private GenerarFolioSolicitudService generarFolioSolicitudService;
    
	/**
	 * Método que realiza la funcionalidad de Registrar/Actualizar la información 
	 * de una Solicitud Pericial. 
	 * Registar Nueva Solicitud Pericial no se pasa el IdDocumento
	 * Actualizar Solicitud Pericial es necsario ingresar el IdDocumento
	 * 
	 * Es posible para Registrar/Actualizar la informacion de la solicitud Pericial para el caso de 
	 * pasar, o no, los siguientes parametros 
	 *  -Manejo de expediente (con un numero de expediente) para ser asignado a la solicitud
	 *  -Funcionario - Solicitante considerando el area a que pertence y el puesto del solicitante. 
	 *  
	 * Pendientes: 
	 * 1)El manejo de estatus de la solicitud.
	 * 2)Los atributos de documento y forma, que son necesario para guardar/actualiza la solicitud.
	 * 
	 * @param solicitudPericialDTO
	 * @return
	 * @throws NSJPNegocioException
	 */
	@Override
    public SolicitudPericialDTO registrarActulizarSolicitudPericial( SolicitudPericialDTO solicitudPericialDTO ) throws NSJPNegocioException{

		SolicitudPericial solicitudPericial = SolicitudPericialTransformer.solPericialTransformer(solicitudPericialDTO);
		
		//Manejo de expediente - Número de expediente
		ExpedienteDTO expedienteDTO =   solicitudPericialDTO.getExpedienteDTO();
		if ( expedienteDTO !=null ){		
			
		
			
				solicitudPericial.setNumeroExpediente(new NumeroExpediente(expedienteDTO.getNumeroExpedienteId()));
			
		}
		
		//Funcionario		
		FuncionarioDTO funcionarioDTO = solicitudPericialDTO.getUsuarioSolicitante();
		if( funcionarioDTO !=null ){
			Funcionario funcionario = FuncionarioTransformer.transformarFuncionario(funcionarioDTO);
			
			//Area Administrativa
			funcionario.setArea( new JerarquiaOrganizacional(solicitudPericialDTO.getAreaOrigen()));
			//Puesto en la Solicitud			
			
			solicitudPericial.setPuestoUsuarioSolicitante(solicitudPericialDTO.getUsuarioSolicitante().getPuesto()!=null?
					solicitudPericialDTO.getUsuarioSolicitante().getPuesto().getValor():StringUtils.EMPTY);
			
			solicitudPericial.setFuncionarioSolicitante(funcionario);
		}
		//asignar el destinatario de la solicitud pericial
		
		if(solicitudPericialDTO.getDestinatario() != null){
			solicitudPericial.setDestinatario(new Funcionario(solicitudPericialDTO.getDestinatario().getClaveFuncionario()));
		}

		
		
		//Actualizar / Guardar la Solicitud
		if( solicitudPericialDTO.getDocumentoId() != null ){
			
			
			solicitudPericialDAO.saveOrUpdate(solicitudPericial);			
		}
		else{
			//Valores requeridos para dar de lata la solicitud
			solicitudPericial.setForma(new Forma(1L));
			solicitudPericial.setTipoDocumento(new Valor(82L));//Solicitud
			solicitudPericial.setFolioDocumento("Folio Doc.");
			solicitudPericial.setNombreDocumento("Nombre Documento");
			solicitudPericial.setFolioSolicitud(generarFolioSolicitudService.generarFolioSolicitud());
			Long idSolicitud = solicitudPericialDAO.create(solicitudPericial);	
			
			solicitudPericialDTO.setDocumentoId(idSolicitud);
		}
		
		// flujo para las de evidencias
		if (solicitudPericialDTO.getElementos()!=null || !solicitudPericialDTO.getElementos().isEmpty()) {
		    for (ElementoDTO ele: solicitudPericialDTO.getElementos()) {
		        solicitudPericial.setDocumentoId(solicitudPericialDTO.getDocumentoId());
		        this.relacionService.generarRelacion(solicitudPericial, ele.getElementoId(), Relaciones.EVIDENCIA_EN_SOLICITUD);
		    }
		}
		
		//Borrar y crear las solicitudes de pericial hijas donde se asignan a los peritos
		//se crea una solicitud hija por cada perito, copia de la solicitud padre pero donde el destinatario es el perito
		//y el solicitante es el destinatario de la padre
		if(solicitudPericialDTO.getPeritosDesignados() != null){
			if(!solicitudPericial.getSolicitudesHijas().isEmpty()){
				for(SolicitudPericial hija:solicitudPericial.getSolicitudesHijas()){
					solicitudPericialDAO.delete(hija);
				}
				solicitudPericial.getSolicitudesHijas().clear();
				solicitudPericialDAO.saveOrUpdate(solicitudPericial);
			}
			SolicitudPericial nuevaSolHija = null;
			for(FuncionarioDTO perito:solicitudPericialDTO.getPeritosDesignados()){
				
				nuevaSolHija = SolicitudPericialTransformer.solPericialTransformer(solicitudPericialDTO);
				nuevaSolHija.setNumeroExpediente(null);
				nuevaSolHija.setDocumentoId(null);
				nuevaSolHija.setFuncionarioSolicitante(solicitudPericial.getDestinatario()!=null?funcionarioDAO.read(
						solicitudPericial.getDestinatario().getClaveFuncionario()):null);
				nuevaSolHija.setDestinatario(new Funcionario(perito.getClaveFuncionario()));
				nuevaSolHija.setSolicitudPadre(solicitudPericial);	
				nuevaSolHija.setPuestoUsuarioSolicitante(nuevaSolHija.getFuncionarioSolicitante()!=null &&
						nuevaSolHija.getFuncionarioSolicitante().getPuesto()!=null?
						nuevaSolHija.getFuncionarioSolicitante().getPuesto().getValor():StringUtils.EMPTY);
				//Valores requeridos para dar de lata la solicitud
				nuevaSolHija.setForma(new Forma(1L));
				nuevaSolHija.setTipoDocumento(new Valor(82L));//Solicitud
				nuevaSolHija.setFolioDocumento("Folio Doc.");
				nuevaSolHija.setNombreDocumento("Nombre Documento");
				solicitudPericialDAO.create(nuevaSolHija);
			}
		}
		
		
		
		return solicitudPericialDTO;		
	}
		
}
