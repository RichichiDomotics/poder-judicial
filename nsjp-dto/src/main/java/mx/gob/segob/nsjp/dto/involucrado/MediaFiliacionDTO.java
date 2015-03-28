/**
* Nombre del Programa : MediaFiliacionDTO.java
* Autor                            : cesarAgustin
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 28 Jun 2011
* Marca de cambio        : N/A
* Descripcion General    : Clase para la transferencia de datos entre la vista y el negocio, del objeto MediaFilacion
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
package mx.gob.segob.nsjp.dto.involucrado;

import mx.gob.segob.nsjp.dto.base.GenericDTO;
import mx.gob.segob.nsjp.dto.catalogo.ValorDTO;

/**
 * Clase para la transferencia de datos entre la vista y el negocio, del objeto MediaFilacion.
 * @version 1.0
 * @author cesarAgustin
 *
 */
public class MediaFiliacionDTO extends GenericDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4259871669430291376L;

	private Long mediaFiliacionId;  
	private SeniaParticularDTO seniaParticularDTO; 
    private Double estatura;
    private Double peso;
    private String perfil;    
    private Boolean tieneBarba;    
    private Boolean tieneBigote;    
    private Boolean usaLentes;
    private String factorRH;
    private ValorDTO tipoSangre;
    private ValorDTO formaOreja;
    private ValorDTO tamanioCeja;
    private ValorDTO tamanioOjo;
    private ValorDTO formaCeja;
    private ValorDTO anchoNariz;
    private ValorDTO espesorLabioInf;
    private ValorDTO orejaTamanio;
    private ValorDTO cabelloImplantacion;
    private ValorDTO colorOjos;
    private ValorDTO frenteAltura;
    private ValorDTO formaOjos;
    private ValorDTO raizNariz;
    private ValorDTO formaMenton;
    private ValorDTO calvicieTipo;
    private ValorDTO inclinacionMenton;
    private ValorDTO cabelloCantidad;
    private ValorDTO alturaNasoLabial;
    private ValorDTO baseNariz;
    private ValorDTO dorsoNariz;
    private ValorDTO colorCabello;
    private ValorDTO labioComisuras;
    private ValorDTO helixPosterior;
    private ValorDTO tamanioBoca;
    private ValorDTO labiosProminencia;
    private ValorDTO tez;
    private ValorDTO tipoCara;
    private ValorDTO formaCabello;
    private ValorDTO implantacionCeja;
    private ValorDTO tipoMenton;
    private ValorDTO orejaLobDimension;
    private ValorDTO direccionCeja;
    private ValorDTO espesorLabioSup;
    private ValorDTO frenteAncho;
    private ValorDTO helixContorno;
    private ValorDTO alturaNariz;
    private ValorDTO orejaLobParticularidad;
    private ValorDTO helixAdherencia;
    private ValorDTO orejaLobAdherencia;
    private ValorDTO helixSuperior;
    private ValorDTO frenteInclinacion;
    private ValorDTO helixOriginal;
    private ValorDTO orejaLobContorno;
    private ValorDTO complexion;

    
	/**
	 * Método de acceso al campo mediaFiliacionId.
	 * @return El valor del campo mediaFiliacionId
	 */
	public Long getMediaFiliacionId() {
		return mediaFiliacionId;
	}
	/**
	 * Asigna el valor al campo mediaFiliacionId.
	 * @param mediaFiliacionId el valor mediaFiliacionId a asignar
	 */
	public void setMediaFiliacionId(Long mediaFiliacionId) {
		this.mediaFiliacionId = mediaFiliacionId;
	}
	/**
	 * Método de acceso al DTO de Senia Particular
	 * @return
	 */
	public SeniaParticularDTO getSeniaParticularDTO() {
		return seniaParticularDTO;
	}
	public void setSeniaParticularDTO(SeniaParticularDTO seniaParticularDTO) {
		this.seniaParticularDTO = seniaParticularDTO;
	}
	/**
	 * Método de acceso al campo estatura.
	 * @return El valor del campo estatura
	 */
	public Double getEstatura() {
		return estatura;
	}
	/**
	 * Asigna el valor al campo estatura.
	 * @param estatura el valor estatura a asignar
	 */
	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}
	/**
	 * Método de acceso al campo peso.
	 * @return El valor del campo peso
	 */
	public Double getPeso() {
		return peso;
	}
	/**
	 * Asigna el valor al campo peso.
	 * @param peso el valor peso a asignar
	 */
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	/**
	 * Método de acceso al campo perfil.
	 * @return El valor del campo perfil
	 */
	public String getPerfil() {
		return perfil;
	}
	/**
	 * Asigna el valor al campo perfil.
	 * @param perfil el valor perfil a asignar
	 */
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	/**
	 * Método de acceso al campo tieneBarba.
	 * @return El valor del campo tieneBarba
	 */
	public Boolean getTieneBarba() {
		return tieneBarba;
	}
	/**
	 * Asigna el valor al campo tieneBarba.
	 * @param tieneBarba el valor tieneBarba a asignar
	 */
	public void setTieneBarba(Boolean tieneBarba) {
		this.tieneBarba = tieneBarba;
	}
	/**
	 * Método de acceso al campo tieneBigote.
	 * @return El valor del campo tieneBigote
	 */
	public Boolean getTieneBigote() {
		return tieneBigote;
	}
	/**
	 * Asigna el valor al campo tieneBigote.
	 * @param tieneBigote el valor tieneBigote a asignar
	 */
	public void setTieneBigote(Boolean tieneBigote) {
		this.tieneBigote = tieneBigote;
	}
	/**
	 * Método de acceso al campo usaLentes.
	 * @return El valor del campo usaLentes
	 */
	public Boolean getUsaLentes() {
		return usaLentes;
	}
	/**
	 * Asigna el valor al campo usaLentes.
	 * @param usaLentes el valor usaLentes a asignar
	 */
	public void setUsaLentes(Boolean usaLentes) {
		this.usaLentes = usaLentes;
	}
	/**
	 * Método de acceso al campo factorRH.
	 * @return El valor del campo factorRH
	 */
	public String getFactorRH() {
		return factorRH;
	}
	/**
	 * 
	 * @param factorRH
	 */
	public void setFactorRH(String factorRH) {
		this.factorRH = factorRH;
	}
	/**
	 * Método de acceso al campo tipoSangre.
	 * @return
	 */
	public ValorDTO getTipoSangre() {
		return tipoSangre;
	}
	/**
	 * 
	 * @param tipoSangre
	 */
	public void setTipoSangre(ValorDTO tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	/**
	 * Método de acceso al campo formaOreja.
	 * @return El valor del campo formaOreja
	 */
	public ValorDTO getFormaOreja() {
		return formaOreja;
	}
	/**
	 * Asigna el valor al campo formaOreja.
	 * @param formaOreja el valor formaOreja a asignar
	 */
	public void setFormaOreja(ValorDTO formaOreja) {
		this.formaOreja = formaOreja;
	}
	/**
	 * Método de acceso al campo tamanioCeja.
	 * @return El valor del campo tamanioCeja
	 */
	public ValorDTO getTamanioCeja() {
		return tamanioCeja;
	}
	/**
	 * Asigna el valor al campo tamanioCeja.
	 * @param tamanioCeja el valor tamanioCeja a asignar
	 */
	public void setTamanioCeja(ValorDTO tamanioCeja) {
		this.tamanioCeja = tamanioCeja;
	}
	/**
	 * Método de acceso al campo tamanioOjo.
	 * @return El valor del campo tamanioOjo
	 */
	public ValorDTO getTamanioOjo() {
		return tamanioOjo;
	}
	/**
	 * Asigna el valor al campo tamanioOjo.
	 * @param tamanioOjo el valor tamanioOjo a asignar
	 */
	public void setTamanioOjo(ValorDTO tamanioOjo) {
		this.tamanioOjo = tamanioOjo;
	}
	/**
	 * Método de acceso al campo formaCeja.
	 * @return El valor del campo formaCeja
	 */
	public ValorDTO getFormaCeja() {
		return formaCeja;
	}
	/**
	 * Asigna el valor al campo formaCeja.
	 * @param formaCeja el valor formaCeja a asignar
	 */
	public void setFormaCeja(ValorDTO formaCeja) {
		this.formaCeja = formaCeja;
	}
	/**
	 * Método de acceso al campo anchoNariz.
	 * @return El valor del campo anchoNariz
	 */
	public ValorDTO getAnchoNariz() {
		return anchoNariz;
	}
	/**
	 * Asigna el valor al campo anchoNariz.
	 * @param anchoNariz el valor anchoNariz a asignar
	 */
	public void setAnchoNariz(ValorDTO anchoNariz) {
		this.anchoNariz = anchoNariz;
	}
	/**
	 * Método de acceso al campo espesorLabioInf.
	 * @return El valor del campo espesorLabioInf
	 */
	public ValorDTO getEspesorLabioInf() {
		return espesorLabioInf;
	}
	/**
	 * Asigna el valor al campo espesorLabioInf.
	 * @param espesorLabioInf el valor espesorLabioInf a asignar
	 */
	public void setEspesorLabioInf(ValorDTO espesorLabioInf) {
		this.espesorLabioInf = espesorLabioInf;
	}
	/**
	 * Método de acceso al campo orejaTamanio.
	 * @return El valor del campo orejaTamanio
	 */
	public ValorDTO getOrejaTamanio() {
		return orejaTamanio;
	}
	/**
	 * Asigna el valor al campo orejaTamanio.
	 * @param orejaTamanio el valor orejaTamanio a asignar
	 */
	public void setOrejaTamanio(ValorDTO orejaTamanio) {
		this.orejaTamanio = orejaTamanio;
	}
	/**
	 * Método de acceso al campo cabelloImplantacion.
	 * @return El valor del campo cabelloImplantacion
	 */
	public ValorDTO getCabelloImplantacion() {
		return cabelloImplantacion;
	}
	/**
	 * Asigna el valor al campo cabelloImplantacion.
	 * @param cabelloImplantacion el valor cabelloImplantacion a asignar
	 */
	public void setCabelloImplantacion(ValorDTO cabelloImplantacion) {
		this.cabelloImplantacion = cabelloImplantacion;
	}
	/**
	 * Método de acceso al campo tamanioOjoParticularidad.
	 * @return El valor del campo tamanioOjoParticularidad
	 */
	public ValorDTO getColorOjos() {
		return colorOjos;
	}
	/**
	 * Asigna el valor al campo tamanioOjoParticularidad.
	 * @param tamanioOjoParticularidad el valor tamanioOjoParticularidad a asignar
	 */
	public void setColorOjos(ValorDTO colorOjos) {
		this.colorOjos = colorOjos;
	}
	/**
	 * Método de acceso al campo frenteAltura.
	 * @return El valor del campo frenteAltura
	 */
	public ValorDTO getFrenteAltura() {
		return frenteAltura;
	}
	/**
	 * Asigna el valor al campo frenteAltura.
	 * @param frenteAltura el valor frenteAltura a asignar
	 */
	public void setFrenteAltura(ValorDTO frenteAltura) {
		this.frenteAltura = frenteAltura;
	}
	/**
	 * Método de acceso al campo tamanioOjoDimension.
	 * @return El valor del campo tamanioOjoDimension
	 */
	public ValorDTO getFormaOjos() {
		return formaOjos;
	}
	/**
	 * Asigna el valor al campo tamanioOjoDimension.
	 * @param tamanioOjoDimension el valor tamanioOjoDimension a asignar
	 */
	public void setFormaOjos(ValorDTO formaOjos) {
		this.formaOjos = formaOjos;
	}
	/**
	 * Método de acceso al campo raizNariz.
	 * @return El valor del campo raizNariz
	 */
	public ValorDTO getRaizNariz() {
		return raizNariz;
	}
	/**
	 * Asigna el valor al campo raizNariz.
	 * @param raizNariz el valor raizNariz a asignar
	 */
	public void setRaizNariz(ValorDTO raizNariz) {
		this.raizNariz = raizNariz;
	}
	/**
	 * Método de acceso al campo formaMenton.
	 * @return El valor del campo formaMenton
	 */
	public ValorDTO getFormaMenton() {
		return formaMenton;
	}
	/**
	 * Asigna el valor al campo formaMenton.
	 * @param formaMenton el valor formaMenton a asignar
	 */
	public void setFormaMenton(ValorDTO formaMenton) {
		this.formaMenton = formaMenton;
	}
	/**
	 * Método de acceso al campo calvicieTipo.
	 * @return El valor del campo calvicieTipo
	 */
	public ValorDTO getCalvicieTipo() {
		return calvicieTipo;
	}
	/**
	 * Asigna el valor al campo calvicieTipo.
	 * @param calvicieTipo el valor calvicieTipo a asignar
	 */
	public void setCalvicieTipo(ValorDTO calvicieTipo) {
		this.calvicieTipo = calvicieTipo;
	}
	/**
	 * Método de acceso al campo inclinacionMenton.
	 * @return El valor del campo inclinacionMenton
	 */
	public ValorDTO getInclinacionMenton() {
		return inclinacionMenton;
	}
	/**
	 * Asigna el valor al campo inclinacionMenton.
	 * @param inclinacionMenton el valor inclinacionMenton a asignar
	 */
	public void setInclinacionMenton(ValorDTO inclinacionMenton) {
		this.inclinacionMenton = inclinacionMenton;
	}
	/**
	 * Método de acceso al campo cabelloCantidad.
	 * @return El valor del campo cabelloCantidad
	 */
	public ValorDTO getCabelloCantidad() {
		return cabelloCantidad;
	}
	/**
	 * Asigna el valor al campo cabelloCantidad.
	 * @param cabelloCantidad el valor cabelloCantidad a asignar
	 */
	public void setCabelloCantidad(ValorDTO cabelloCantidad) {
		this.cabelloCantidad = cabelloCantidad;
	}
	/**
	 * Método de acceso al campo alturaNasoLabial.
	 * @return El valor del campo alturaNasoLabial
	 */
	public ValorDTO getAlturaNasoLabial() {
		return alturaNasoLabial;
	}
	/**
	 * Asigna el valor al campo alturaNasoLabial.
	 * @param alturaNasoLabial el valor alturaNasoLabial a asignar
	 */
	public void setAlturaNasoLabial(ValorDTO alturaNasoLabial) {
		this.alturaNasoLabial = alturaNasoLabial;
	}
	/**
	 * Método de acceso al campo baseNariz.
	 * @return El valor del campo baseNariz
	 */
	public ValorDTO getBaseNariz() {
		return baseNariz;
	}
	/**
	 * Asigna el valor al campo baseNariz.
	 * @param baseNariz el valor baseNariz a asignar
	 */
	public void setBaseNariz(ValorDTO baseNariz) {
		this.baseNariz = baseNariz;
	}
	/**
	 * Método de acceso al campo dorsoNariz.
	 * @return El valor del campo dorsoNariz
	 */
	public ValorDTO getDorsoNariz() {
		return dorsoNariz;
	}
	/**
	 * Asigna el valor al campo dorsoNariz.
	 * @param dorsoNariz el valor dorsoNariz a asignar
	 */
	public void setDorsoNariz(ValorDTO dorsoNariz) {
		this.dorsoNariz = dorsoNariz;
	}
	/**
	 * Método de acceso al campo colorCabello.
	 * @return El valor del campo colorCabello
	 */
	public ValorDTO getColorCabello() {
		return colorCabello;
	}
	/**
	 * Asigna el valor al campo colorCabello.
	 * @param colorCabello el valor colorCabello a asignar
	 */
	public void setColorCabello(ValorDTO colorCabello) {
		this.colorCabello = colorCabello;
	}
	/**
	 * Método de acceso al campo labioComisuras.
	 * @return El valor del campo labioComisuras
	 */
	public ValorDTO getLabioComisuras() {
		return labioComisuras;
	}
	/**
	 * Asigna el valor al campo labioComisuras.
	 * @param labioComisuras el valor labioComisuras a asignar
	 */
	public void setLabioComisuras(ValorDTO labioComisuras) {
		this.labioComisuras = labioComisuras;
	}
	/**
	 * Método de acceso al campo helixPosterior.
	 * @return El valor del campo helixPosterior
	 */
	public ValorDTO getHelixPosterior() {
		return helixPosterior;
	}
	/**
	 * Asigna el valor al campo helixPosterior.
	 * @param helixPosterior el valor helixPosterior a asignar
	 */
	public void setHelixPosterior(ValorDTO helixPosterior) {
		this.helixPosterior = helixPosterior;
	}
	/**
	 * Método de acceso al campo tamanioBoca.
	 * @return El valor del campo tamanioBoca
	 */
	public ValorDTO getTamanioBoca() {
		return tamanioBoca;
	}
	/**
	 * Asigna el valor al campo tamanioBoca.
	 * @param tamanioBoca el valor tamanioBoca a asignar
	 */
	public void setTamanioBoca(ValorDTO tamanioBoca) {
		this.tamanioBoca = tamanioBoca;
	}
	/**
	 * Método de acceso al campo labiosProminencia.
	 * @return El valor del campo labiosProminencia
	 */
	public ValorDTO getLabiosProminencia() {
		return labiosProminencia;
	}
	/**
	 * Asigna el valor al campo labiosProminencia.
	 * @param labiosProminencia el valor labiosProminencia a asignar
	 */
	public void setLabiosProminencia(ValorDTO labiosProminencia) {
		this.labiosProminencia = labiosProminencia;
	}
	/**
	 * Método de acceso al campo tez.
	 * @return El valor del campo tez
	 */
	public ValorDTO getTez() {
		return tez;
	}
	/**
	 * Asigna el valor al campo tez.
	 * @param tez el valor tez a asignar
	 */
	public void setTez(ValorDTO tez) {
		this.tez = tez;
	}
	/**
	 * Método de acceso al campo tipoCara.
	 * @return El valor del campo tipoCara
	 */
	public ValorDTO getTipoCara() {
		return tipoCara;
	}
	/**
	 * Asigna el valor al campo tipoCara.
	 * @param tipoCara el valor tipoCara a asignar
	 */
	public void setTipoCara(ValorDTO tipoCara) {
		this.tipoCara = tipoCara;
	}
	/**
	 * Método de acceso al campo formaCabello.
	 * @return El valor del campo formaCabello
	 */
	public ValorDTO getFormaCabello() {
		return formaCabello;
	}
	/**
	 * Asigna el valor al campo formaCabello.
	 * @param formaCabello el valor formaCabello a asignar
	 */
	public void setFormaCabello(ValorDTO formaCabello) {
		this.formaCabello = formaCabello;
	}
	/**
	 * Método de acceso al campo implantacionCeja.
	 * @return El valor del campo implantacionCeja
	 */
	public ValorDTO getImplantacionCeja() {
		return implantacionCeja;
	}
	/**
	 * Asigna el valor al campo implantacionCeja.
	 * @param implantacionCeja el valor implantacionCeja a asignar
	 */
	public void setImplantacionCeja(ValorDTO implantacionCeja) {
		this.implantacionCeja = implantacionCeja;
	}
	/**
	 * Método de acceso al campo tipoMenton.
	 * @return El valor del campo tipoMenton
	 */
	public ValorDTO getTipoMenton() {
		return tipoMenton;
	}
	/**
	 * Asigna el valor al campo tipoMenton.
	 * @param tipoMenton el valor tipoMenton a asignar
	 */
	public void setTipoMenton(ValorDTO tipoMenton) {
		this.tipoMenton = tipoMenton;
	}
	/**
	 * Método de acceso al campo orejaLobDimension.
	 * @return El valor del campo orejaLobDimension
	 */
	public ValorDTO getOrejaLobDimension() {
		return orejaLobDimension;
	}
	/**
	 * Asigna el valor al campo orejaLobDimension.
	 * @param orejaLobDimension el valor orejaLobDimension a asignar
	 */
	public void setOrejaLobDimension(ValorDTO orejaLobDimension) {
		this.orejaLobDimension = orejaLobDimension;
	}
	/**
	 * Método de acceso al campo direccionCeja.
	 * @return El valor del campo direccionCeja
	 */
	public ValorDTO getDireccionCeja() {
		return direccionCeja;
	}
	/**
	 * Asigna el valor al campo direccionCeja.
	 * @param direccionCeja el valor direccionCeja a asignar
	 */
	public void setDireccionCeja(ValorDTO direccionCeja) {
		this.direccionCeja = direccionCeja;
	}
	/**
	 * Método de acceso al campo espesorLabioSup.
	 * @return El valor del campo espesorLabioSup
	 */
	public ValorDTO getEspesorLabioSup() {
		return espesorLabioSup;
	}
	/**
	 * Asigna el valor al campo espesorLabioSup.
	 * @param espesorLabioSup el valor espesorLabioSup a asignar
	 */
	public void setEspesorLabioSup(ValorDTO espesorLabioSup) {
		this.espesorLabioSup = espesorLabioSup;
	}
	/**
	 * Método de acceso al campo frenteAncho.
	 * @return El valor del campo frenteAncho
	 */
	public ValorDTO getFrenteAncho() {
		return frenteAncho;
	}
	/**
	 * Asigna el valor al campo frenteAncho.
	 * @param frenteAncho el valor frenteAncho a asignar
	 */
	public void setFrenteAncho(ValorDTO frenteAncho) {
		this.frenteAncho = frenteAncho;
	}
	/**
	 * Método de acceso al campo helixContorno.
	 * @return El valor del campo helixContorno
	 */
	public ValorDTO getHelixContorno() {
		return helixContorno;
	}
	/**
	 * Asigna el valor al campo helixContorno.
	 * @param helixContorno el valor helixContorno a asignar
	 */
	public void setHelixContorno(ValorDTO helixContorno) {
		this.helixContorno = helixContorno;
	}
	/**
	 * Método de acceso al campo alturaNariz.
	 * @return El valor del campo alturaNariz
	 */
	public ValorDTO getAlturaNariz() {
		return alturaNariz;
	}
	/**
	 * Asigna el valor al campo alturaNariz.
	 * @param alturaNariz el valor alturaNariz a asignar
	 */
	public void setAlturaNariz(ValorDTO alturaNariz) {
		this.alturaNariz = alturaNariz;
	}
	/**
	 * Método de acceso al campo orejaLobParticularidad.
	 * @return El valor del campo orejaLobParticularidad
	 */
	public ValorDTO getOrejaLobParticularidad() {
		return orejaLobParticularidad;
	}
	/**
	 * Asigna el valor al campo orejaLobParticularidad.
	 * @param orejaLobParticularidad el valor orejaLobParticularidad a asignar
	 */
	public void setOrejaLobParticularidad(ValorDTO orejaLobParticularidad) {
		this.orejaLobParticularidad = orejaLobParticularidad;
	}
	/**
	 * Método de acceso al campo helixAdherencia.
	 * @return El valor del campo helixAdherencia
	 */
	public ValorDTO getHelixAdherencia() {
		return helixAdherencia;
	}
	/**
	 * Asigna el valor al campo helixAdherencia.
	 * @param helixAdherencia el valor helixAdherencia a asignar
	 */
	public void setHelixAdherencia(ValorDTO helixAdherencia) {
		this.helixAdherencia = helixAdherencia;
	}
	/**
	 * Método de acceso al campo orejaLobAdherencia.
	 * @return El valor del campo orejaLobAdherencia
	 */
	public ValorDTO getOrejaLobAdherencia() {
		return orejaLobAdherencia;
	}
	/**
	 * Asigna el valor al campo orejaLobAdherencia.
	 * @param orejaLobAdherencia el valor orejaLobAdherencia a asignar
	 */
	public void setOrejaLobAdherencia(ValorDTO orejaLobAdherencia) {
		this.orejaLobAdherencia = orejaLobAdherencia;
	}
	/**
	 * Método de acceso al campo helixSuperior.
	 * @return El valor del campo helixSuperior
	 */
	public ValorDTO getHelixSuperior() {
		return helixSuperior;
	}
	/**
	 * Asigna el valor al campo helixSuperior.
	 * @param helixAnterior el valor helixSuperior a asignar
	 */
	public void setHelixSuperior(ValorDTO helixSuperior) {
		this.helixSuperior = helixSuperior;
	}
	/**
	 * Método de acceso al campo frenteInclinacion.
	 * @return El valor del campo frenteInclinacion
	 */
	public ValorDTO getFrenteInclinacion() {
		return frenteInclinacion;
	}
	/**
	 * Asigna el valor al campo frenteInclinacion.
	 * @param frenteInclinacion el valor frenteInclinacion a asignar
	 */
	public void setFrenteInclinacion(ValorDTO frenteInclinacion) {
		this.frenteInclinacion = frenteInclinacion;
	}
	/**
	 * Método de acceso al campo helixOriginal.
	 * @return El valor del campo helixOriginal
	 */
	public ValorDTO getHelixOriginal() {
		return helixOriginal;
	}
	/**
	 * Asigna el valor al campo helixOriginal.
	 * @param helixAnterior el valor helixOriginal a asignar
	 */
	public void setHelixOriginal(ValorDTO helixOriginal) {
		this.helixOriginal = helixOriginal;
	}
	/**
	 * Método de acceso al campo OrejaLobContorno.
	 * @return El valor del campo OrejaLobContorno
	 */
	public ValorDTO getOrejaLobContorno() {
		return orejaLobContorno;
	}
	/**
	 * Asigna el valor al campo OrejaLobContorno.
	 * @param helixAnterior el valor OrejaLobContorno a asignar
	 */
	public void setOrejaLobContorno(ValorDTO orejaLobContorno) {
		this.orejaLobContorno = orejaLobContorno;
	}	
	/**
	 * Método de acceso al campo Complexion.
	 * @return El valor del campo Complexion
	 */
	public ValorDTO getComplexion() {
		return complexion;
	}
	/**
	 * Asigna el valor al campo Complexion.
	 * @param helixAnterior el valor Complexion a asignar
	 */
	public void setComplexion(ValorDTO complexion) {
		this.complexion = complexion;
	}
}
