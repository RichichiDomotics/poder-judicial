/**
 * Nombre del Programa : ConfiguracionDTO.java
 * Autor                            : vaguirre
 * Compania                    : Ultrasist
 * Proyecto                      : NSJP                    Fecha: 31 Aug 2011
 * Marca de cambio        : N/A
 * Descripcion General    : Objeto para concentrar la configuración y se ponga en sesión
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
package mx.gob.segob.nsjp.dto.configuracion;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * Objeto para concentrar la configuración y se ponga en sesión.
 * 
 * @version 1.0
 * @author vaguirre
 * 
 */
public class ConfiguracionDTO extends GenericDTO {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9141476331925947147L;
	private Long tiempoRevisionAlarmas;
    private String urlServidorChat;
    private Long tiempoBloqueoSesion;
    private String entidadFederativaDespliegue;
    private Long habilitarTurno;
    private Long validaDelitoGrave;
    private String urlServidorImag;

    /**
     * Método de acceso al campo tiempoRevisionAlarmas.
     * 
     * @return El valor del campo tiempoRevisionAlarmas
     */
    public Long getTiempoRevisionAlarmas() {
        return tiempoRevisionAlarmas;
    }

    /**
     * Asigna el valor al campo tiempoRevisionAlarmas.
     * 
     * @param tiempoRevisionAlarmas
     *            el valor tiempoRevisionAlarmas a asignar
     */
    public void setTiempoRevisionAlarmas(Long tiempoRevisionAlarmas) {
        this.tiempoRevisionAlarmas = tiempoRevisionAlarmas;
    }

    /**
     * Método de acceso al campo urlServidorChat.
     * @return El valor del campo urlServidorChat
     */
    public String getUrlServidorChat() {
        return urlServidorChat;
    }

    /**
     * Asigna el valor al campo urlServidorChat.
     * @param urlServidorChat el valor urlServidorChat a asignar
     */
    public void setUrlServidorChat(String urlServidorChat) {
        this.urlServidorChat = urlServidorChat;
    }
	/**
	 * @return the entidadFederativaDespliegue
	 */
	public final String getEntidadFederativaDespliegue() {
		return entidadFederativaDespliegue;
	}

	/**
	 * @param entidadFederativaDespliegue the entidadFederativaDespliegue to set
	 */
	public final void setEntidadFederativaDespliegue(
			String entidadFederativaDespliegue) {
		this.entidadFederativaDespliegue = entidadFederativaDespliegue;
	}

	/**
	 * @return the habilitarTurno
	 */
	public Long getHabilitarTurno() {
		return habilitarTurno;
	}

	/**
	 * @param habilitarTurno the habilitarTurno to set
	 */
	public void setHabilitarTurno(Long habilitarTurno) {
		this.habilitarTurno = habilitarTurno;
	}

	/**
	 * @return the validaDelitoGrave
	 */
	public Long getValidaDelitoGrave() {
		return validaDelitoGrave;
	}

	/**
	 * @param validaDelitoGrave the validaDelitoGrave to set
	 */
	public void setValidaDelitoGrave(Long validaDelitoGrave) {
		this.validaDelitoGrave = validaDelitoGrave;
	}


	/**
	 * @return the tiempoBloqueoSesion
	 */
	public Long getTiempoBloqueoSesion() {
		return tiempoBloqueoSesion;
	}

	/**
	 * @param tiempoBloqueoSesion the tiempoBloqueoSesion to set
	 */
	public void setTiempoBloqueoSesion(Long tiempoBloqueoSesion) {
		this.tiempoBloqueoSesion = tiempoBloqueoSesion;
	}

	/**
	 * @return the urlservidorImag
	 */
	public String getUrlServidorImag() {
		return urlServidorImag;
	}

	/**
	 * @param urlservidorImag the urlservidorImag to set
	 */
	public void setUrlServidorImag(String urlServidorImag) {
		this.urlServidorImag = urlServidorImag;
	}
	
	
	
}
