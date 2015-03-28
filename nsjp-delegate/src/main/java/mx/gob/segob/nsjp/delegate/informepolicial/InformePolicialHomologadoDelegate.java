package mx.gob.segob.nsjp.delegate.informepolicial;

/**
 * Contrato del delegate para los servicios relacionados con Informe Policial Homologado.
 * @version 1.0
 * @author mgallardo
 *
 */
import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.comun.excepcion.NSJPNegocioException;
import mx.gob.segob.nsjp.dto.documento.DocumentoDTO;
import mx.gob.segob.nsjp.dto.expediente.ExpedienteDTO;
import mx.gob.segob.nsjp.dto.informepolicial.InformePolicialHomologadoDTO;
import mx.gob.segob.nsjp.dto.informepolicial.OperativoDTO;

public interface InformePolicialHomologadoDelegate {
	/**
	 * Operación que permite guardar los datos generales para un IPH ya creado
	 * @param iph
	 * @param operativo (opcional)
	 * @return
	 * @throws NSJPNegocioException
	 */
	public Long ingresarDatosGenerales(InformePolicialHomologadoDTO iph, OperativoDTO operativo) throws NSJPNegocioException;
	public InformePolicialHomologadoDTO ObtenerFolioIPH(ExpedienteDTO expedienteDTO) throws NSJPNegocioException;
	public InformePolicialHomologadoDTO consultarInformePorFolio(Long folio) throws NSJPNegocioException;
	public List<InformePolicialHomologadoDTO> consultarInformePorNombreoFecha(Date fechaInicio, Date fechaFin, String nombre) throws NSJPNegocioException;

	/**
	 * Operación que permite consultar todos los informes policiales homologados distinguiendo por el parámetro recibido
	 * @param conDetenido: 	True	(Consulta los informes que en sus involucrados tengan algún detenido)
	 * 						False	(Consulta los informes que en sus involucrados no exista ningún detenido)
	 * 						Null 	(Consulta Todos los informes)
	 * @return
	 * @throws NSJPNegocioException
	 */
	public List<InformePolicialHomologadoDTO> consultarInformes(Boolean conDetenido)throws NSJPNegocioException;
	
	/**
	 * Operación que pérmite crear el documento de IPH
	 * @param homologadoDTO: idHomologado, expediente: idExpediente
	 * @return
	 * @throws NSJPNegocioException
	 */
	public DocumentoDTO cargarInformeIPH(InformePolicialHomologadoDTO homologadoDTO)throws NSJPNegocioException;
	
	/**
	 * Obtiene el numero de IPH o IP resgistrados dentro de un rango de fechas.
	 * @author cesarAgustin
	 * @param fechaInicio
	 * @param fechaFin
	 * @param condicion
	 * @throws NSJPNegocioException
	 * @return
	 */
	public Long obtenerIPHPorFechas(Date fechaInicio, Date fechaFin,
			Boolean condicion) throws NSJPNegocioException;
	
	
	/**
	 * Servicio que permite la consulta del Informe Policial Homologado
	 * completo mediante el folioIPH.  
	 * 
	 * @param folioIPH
	 * @return
	 * @throws NSJPNegocioException
	 */
	InformePolicialHomologadoDTO consultarInformePolicialHomologadoPorFolio(Long folioIPH)throws NSJPNegocioException ;
	
	/**
	 * Servicio que se encarga de consultar un IPH por folio e invocar
	 * al WS para ser enviado a PGJ
	 * 
	 * @param informePolicialHomologadoDTO
	 * @param idAgencia Agencia a la cual se enviara el IPH
	 * @return
	 * @throws NSJPNegocioException
	 */
	InformePolicialHomologadoDTO consultarEnviarInformePolicialHomologado( Long folioIPH , Long idAgencia) throws NSJPNegocioException;
	
}
