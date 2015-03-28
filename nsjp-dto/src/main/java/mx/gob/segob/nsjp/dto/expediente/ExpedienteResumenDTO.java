/**
* Nombre del Programa : ExpedienteResumenDTO.java
* Autor                            : Emigdio Hernández
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 08/06/2011
* Marca de cambio        : N/A
* Descripcion General    : DTO que contiene la información ordenada del 
* expediente
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
package mx.gob.segob.nsjp.dto.expediente;

import java.util.Date;
import java.util.List;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.documento.GrupoObjetosExpedienteDTO;
import mx.gob.segob.nsjp.dto.involucrado.InvolucradoDTO;
import mx.gob.segob.nsjp.dto.usuario.UsuarioDTO;

/**
 * DTO que contiene la información ordenada del 
 * expediente
 * @version 1.0
 * @author Emigdio Hernández
 *
 */
public class ExpedienteResumenDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5677530541596828292L;
	private Long expedienteId;
    private String numeroExpediente;
    private Date fechaApertura;
    private Date fechaCierre;
    private String narrativa;
    private String strFechaApertura;
    private String strHoraApertura;

    private String strFechaCierre;
    private String strHoraCierre;
    
    private String strFechaActual;
    private String strHoraActual;
    
    private DelitoDTO delitoPrincipal;
    
    private String diaActual;
    private String nombreMesActual;
    private String anioActual;
    
    private UsuarioDTO usuario;
    
    private String ciudad;
   

	private String estado;
    
	List<InvolucradoDTO> denunciantes;
    
    List<InvolucradoDTO> denunciantesOrganizacion;
    
  
	List<InvolucradoDTO> victimasPersona;
    
    List<InvolucradoDTO> victimasOrganizaciones;
    
    List<InvolucradoDTO> probablesResponsablesPersona;
    
    List<InvolucradoDTO> probablesResponsablesOrganizacion;
    
    List<InvolucradoDTO> testigos;
    
    List<InvolucradoDTO> traductores;
    
    List<InvolucradoDTO> quienDetuvo;
    
    List<DelitoDTO> delitos;

    List<GrupoObjetosExpedienteDTO> grupoObjetosExpediente;
    
    
    
    /**
	 * Método de acceso al campo ciudad.
	 * @return El valor del campo ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Asigna el valor al campo ciudad.
	 * @param ciudad el valor ciudad a asignar
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Método de acceso al campo estado.
	 * @return El valor del campo estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Asigna el valor al campo estado.
	 * @param estado el valor estado a asignar
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
    /**
	 * Método de acceso al campo grupoObjetosExpediente.
	 * @return El valor del campo grupoObjetosExpediente
	 */
	public List<GrupoObjetosExpedienteDTO> getGrupoObjetosExpediente() {
		return grupoObjetosExpediente;
	}

	/**
	 * Asigna el valor al campo grupoObjetosExpediente.
	 * @param grupoObjetosExpediente el valor grupoObjetosExpediente a asignar
	 */
	public void setGrupoObjetosExpediente(
			List<GrupoObjetosExpedienteDTO> grupoObjetosExpediente) {
		this.grupoObjetosExpediente = grupoObjetosExpediente;
	}

	/**
	 * Método de acceso al campo denunciantesOrganizacion.
	 * @return El valor del campo denunciantesOrganizacion
	 */
	public List<InvolucradoDTO> getDenunciantesOrganizacion() {
		return denunciantesOrganizacion;
	}

	/**
	 * Asigna el valor al campo denunciantesOrganizacion.
	 * @param denunciantesOrganizacion el valor denunciantesOrganizacion a asignar
	 */
	public void setDenunciantesOrganizacion(
			List<InvolucradoDTO> denunciantesOrganizacion) {
		this.denunciantesOrganizacion = denunciantesOrganizacion;
	}

	/**
	 * Método de acceso al campo strFechaActual.
	 * @return El valor del campo strFechaActual
	 */
	public String getStrFechaActual() {
		return strFechaActual;
	}

	/**
	 * Asigna el valor al campo strFechaActual.
	 * @param strFechaActual el valor strFechaActual a asignar
	 */
	public void setStrFechaActual(String strFechaActual) {
		this.strFechaActual = strFechaActual;
	}

	/**
	 * Método de acceso al campo strHoraActual.
	 * @return El valor del campo strHoraActual
	 */
	public String getStrHoraActual() {
		return strHoraActual;
	}

	/**
	 * Asigna el valor al campo strHoraActual.
	 * @param strHoraActual el valor strHoraActual a asignar
	 */
	public void setStrHoraActual(String strHoraActual) {
		this.strHoraActual = strHoraActual;
	}

	/**
	 * Método de acceso al campo delitos.
	 * @return El valor del campo delitos
	 */
	public List<DelitoDTO> getDelitos() {
		return delitos;
	}

	/**
	 * Asigna el valor al campo delitos.
	 * @param delitos el valor delitos a asignar
	 */
	public void setDelitos(List<DelitoDTO> delitos) {
		this.delitos = delitos;
	}

	/**
	 * Método de acceso al campo victimasPersona.
	 * @return El valor del campo victimasPersona
	 */
	public List<InvolucradoDTO> getVictimasPersona() {
		return victimasPersona;
	}

	/**
	 * Asigna el valor al campo victimasPersona.
	 * @param victimasPersona el valor victimasPersona a asignar
	 */
	public void setVictimasPersona(List<InvolucradoDTO> victimasPersona) {
		this.victimasPersona = victimasPersona;
	}

	/**
	 * Método de acceso al campo victimasOrganizaciones.
	 * @return El valor del campo victimasOrganizaciones
	 */
	public List<InvolucradoDTO> getVictimasOrganizaciones() {
		return victimasOrganizaciones;
	}

	/**
	 * Asigna el valor al campo victimasOrganizaciones.
	 * @param victimasOrganizaciones el valor victimasOrganizaciones a asignar
	 */
	public void setVictimasOrganizaciones(
			List<InvolucradoDTO> victimasOrganizaciones) {
		this.victimasOrganizaciones = victimasOrganizaciones;
	}

	/**
	 * Método de acceso al campo expedienteId.
	 * @return El valor del campo expedienteId
	 */
	public Long getExpedienteId() {
		return expedienteId;
	}

	/**
	 * Asigna el valor al campo expedienteId.
	 * @param expedienteId el valor expedienteId a asignar
	 */
	public void setExpedienteId(Long expedienteId) {
		this.expedienteId = expedienteId;
	}

	/**
	 * Método de acceso al campo numeroExpediente.
	 * @return El valor del campo numeroExpediente
	 */
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	/**
	 * Asigna el valor al campo numeroExpediente.
	 * @param numeroExpediente el valor numeroExpediente a asignar
	 */
	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	/**
	 * Método de acceso al campo fechaApertura.
	 * @return El valor del campo fechaApertura
	 */
	public Date getFechaApertura() {
		return fechaApertura;
	}

	/**
	 * Asigna el valor al campo fechaApertura.
	 * @param fechaApertura el valor fechaApertura a asignar
	 */
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	/**
	 * Método de acceso al campo fechaCierre.
	 * @return El valor del campo fechaCierre
	 */
	public Date getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Asigna el valor al campo fechaCierre.
	 * @param fechaCierre el valor fechaCierre a asignar
	 */
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Método de acceso al campo narrativa.
	 * @return El valor del campo narrativa
	 */
	public String getNarrativa() {
		return narrativa;
	}

	/**
	 * Asigna el valor al campo narrativa.
	 * @param narrativa el valor narrativa a asignar
	 */
	public void setNarrativa(String narrativa) {
		this.narrativa = narrativa;
	}

	/**
	 * Método de acceso al campo strFechaApertura.
	 * @return El valor del campo strFechaApertura
	 */
	public String getStrFechaApertura() {
		return strFechaApertura;
	}

	/**
	 * Asigna el valor al campo strFechaApertura.
	 * @param strFechaApertura el valor strFechaApertura a asignar
	 */
	public void setStrFechaApertura(String strFechaApertura) {
		this.strFechaApertura = strFechaApertura;
	}

	/**
	 * Método de acceso al campo strHoraApertura.
	 * @return El valor del campo strHoraApertura
	 */
	public String getStrHoraApertura() {
		return strHoraApertura;
	}

	/**
	 * Asigna el valor al campo strHoraApertura.
	 * @param strHoraApertura el valor strHoraApertura a asignar
	 */
	public void setStrHoraApertura(String strHoraApertura) {
		this.strHoraApertura = strHoraApertura;
	}

	/**
	 * Método de acceso al campo strFechaCierre.
	 * @return El valor del campo strFechaCierre
	 */
	public String getStrFechaCierre() {
		return strFechaCierre;
	}

	/**
	 * Asigna el valor al campo strFechaCierre.
	 * @param strFechaCierre el valor strFechaCierre a asignar
	 */
	public void setStrFechaCierre(String strFechaCierre) {
		this.strFechaCierre = strFechaCierre;
	}

	/**
	 * Método de acceso al campo strHoraCierre.
	 * @return El valor del campo strHoraCierre
	 */
	public String getStrHoraCierre() {
		return strHoraCierre;
	}

	/**
	 * Asigna el valor al campo strHoraCierre.
	 * @param strHoraCierre el valor strHoraCierre a asignar
	 */
	public void setStrHoraCierre(String strHoraCierre) {
		this.strHoraCierre = strHoraCierre;
	}

	/**
	 * Método de acceso al campo delitoPrincipal.
	 * @return El valor del campo delitoPrincipal
	 */
	public DelitoDTO getDelitoPrincipal() {
		return delitoPrincipal;
	}

	/**
	 * Asigna el valor al campo delitoPrincipal.
	 * @param delitoPrincipal el valor delitoPrincipal a asignar
	 */
	public void setDelitoPrincipal(DelitoDTO delitoPrincipal) {
		this.delitoPrincipal = delitoPrincipal;
	}

	/**
	 * Método de acceso al campo denunciantes.
	 * @return El valor del campo denunciantes
	 */
	public List<InvolucradoDTO> getDenunciantes() {
		return denunciantes;
	}

	/**
	 * Asigna el valor al campo denunciantes.
	 * @param denunciantes el valor denunciantes a asignar
	 */
	public void setDenunciantes(List<InvolucradoDTO> denunciantes) {
		this.denunciantes = denunciantes;
	}

	

	

	/**
	 * Método de acceso al campo probablesResponsablesPersona.
	 * @return El valor del campo probablesResponsablesPersona
	 */
	public List<InvolucradoDTO> getProbablesResponsablesPersona() {
		return probablesResponsablesPersona;
	}

	/**
	 * Asigna el valor al campo probablesResponsablesPersona.
	 * @param probablesResponsablesPersona el valor probablesResponsablesPersona a asignar
	 */
	public void setProbablesResponsablesPersona(
			List<InvolucradoDTO> probablesResponsablesPersona) {
		this.probablesResponsablesPersona = probablesResponsablesPersona;
	}

	/**
	 * Método de acceso al campo probablesResponsablesOrganizacion.
	 * @return El valor del campo probablesResponsablesOrganizacion
	 */
	public List<InvolucradoDTO> getProbablesResponsablesOrganizacion() {
		return probablesResponsablesOrganizacion;
	}

	/**
	 * Asigna el valor al campo probablesResponsablesOrganizacion.
	 * @param probablesResponsablesOrganizacion el valor probablesResponsablesOrganizacion a asignar
	 */
	public void setProbablesResponsablesOrganizacion(
			List<InvolucradoDTO> probablesResponsablesOrganizacion) {
		this.probablesResponsablesOrganizacion = probablesResponsablesOrganizacion;
	}

	/**
	 * Método de acceso al campo testigos.
	 * @return El valor del campo testigos
	 */
	public List<InvolucradoDTO> getTestigos() {
		return testigos;
	}

	/**
	 * Asigna el valor al campo testigos.
	 * @param testigos el valor testigos a asignar
	 */
	public void setTestigos(List<InvolucradoDTO> testigos) {
		this.testigos = testigos;
	}

	/**
	 * Método de acceso al campo traductores.
	 * @return El valor del campo traductores
	 */
	public List<InvolucradoDTO> getTraductores() {
		return traductores;
	}

	/**
	 * Asigna el valor al campo traductores.
	 * @param traductores el valor traductores a asignar
	 */
	public void setTraductores(List<InvolucradoDTO> traductores) {
		this.traductores = traductores;
	}

	/**
	 * Método de acceso al campo quienDetuvo.
	 * @return El valor del campo quienDetuvo
	 */
	public List<InvolucradoDTO> getQuienDetuvo() {
		return quienDetuvo;
	}

	/**
	 * Asigna el valor al campo quienDetuvo.
	 * @param quienDetuvo el valor quienDetuvo a asignar
	 */
	public void setQuienDetuvo(List<InvolucradoDTO> quienDetuvo) {
		this.quienDetuvo = quienDetuvo;
	}
	
	/**
	 * Obtiene el primer denunciante, si no hay denunciante se busca la primera
	 * organizción denunciante
	 * @return
	 */
	public InvolucradoDTO getPrimerDenunciante(){
		if(denunciantes != null && !denunciantes.isEmpty()){
			return denunciantes.get(0);
		}else{
			if(denunciantesOrganizacion != null && !denunciantesOrganizacion.isEmpty()){
				return denunciantesOrganizacion.get(0);
			}
		}
		return null;
	}
	/**
	 * Obtiene el primer probable responsable, si no hay probable responsable busca
	 * la primera organización probable responsable
	 * @return
	 */
	public InvolucradoDTO getPrimerProbableResponsable(){
		
		if(probablesResponsablesPersona != null && !probablesResponsablesPersona.isEmpty()){
			return probablesResponsablesPersona.get(0);
		}else{
			if(probablesResponsablesOrganizacion != null && !probablesResponsablesOrganizacion.isEmpty()){
				return probablesResponsablesOrganizacion.get(0);
			}
		}
		return null;
		
	}
	
	/**
	 * Método de acceso al campo diaActual.
	 * @return El valor del campo diaActual
	 */
	public String getDiaActual() {
		return diaActual;
	}

	/**
	 * Asigna el valor al campo diaActual.
	 * @param diaActual el valor diaActual a asignar
	 */
	public void setDiaActual(String diaActual) {
		this.diaActual = diaActual;
	}

	/**
	 * Método de acceso al campo nombreMesActual.
	 * @return El valor del campo nombreMesActual
	 */
	public String getNombreMesActual() {
		return nombreMesActual;
	}

	/**
	 * Asigna el valor al campo nombreMesActual.
	 * @param nombreMesActual el valor nombreMesActual a asignar
	 */
	public void setNombreMesActual(String nombreMesActual) {
		this.nombreMesActual = nombreMesActual;
	}

	/**
	 * Método de acceso al campo anioActual.
	 * @return El valor del campo anioActual
	 */
	public String getAnioActual() {
		return anioActual;
	}

	/**
	 * Asigna el valor al campo anioActual.
	 * @param anioActual el valor anioActual a asignar
	 */
	public void setAnioActual(String anioActual) {
		this.anioActual = anioActual;
	}

	/**
	 * Método de acceso al campo usuario.
	 * @return El valor del campo usuario
	 */
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	/**
	 * Asigna el valor al campo usuario.
	 * @param usuario el valor usuario a asignar
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

    
    
	
}
