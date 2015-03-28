/**
* Nombre del Programa : MediaFiliacionWSDTO.java
* Autor                            : GustavoBP                                               
* Compania                    : Ultrasist                                                
* Proyecto                      : NSJP                    Fecha: 22/07/2012 
* Marca de cambio        : N/A                                                     
* Descripcion General    : DTO de intercambio entre sistemas para transportar los datos básicos de Media Filiación.  
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

import mx.gob.segob.nsjp.dto.base.GenericWSDTO;

/**
 * DTO de intercambio entre sistemas para transportar los datos básicos de Media Filiación.
 * @version 1.0
 * @author GustavoBP
 *
 */
public class MediaFiliacionWSDTO extends GenericWSDTO {

	private SeniaParticularWSDTO seniaParticular; 
	
    private Double estatura;
    private Double peso;
    private String perfil;    
    private Boolean tieneBarba;    
    private Boolean tieneBigote;    
    private Boolean usaLentes;
    private String factorRH;
    private Long tipoSangre;
    private Long formaOreja;
    private Long tamanioCeja;
    private Long tamanioOjo;
    private Long formaCeja;
    private Long anchoNariz;
    private Long espesorLabioInf;
    private Long orejaTamanio;
    private Long cabelloImplantacion;
    private Long colorOjos;
    private Long frenteAltura;
    private Long formaOjos;
    private Long raizNariz;
    private Long formaMenton;
    private Long calvicieTipo;
    private Long inclinacionMenton;
    private Long cabelloCantidad;
    private Long alturaNasoLabial;
    private Long baseNariz;
    private Long dorsoNariz;
    private Long colorCabello;
    private Long labioComisuras;
    private Long helixPosterior;
    private Long tamanioBoca;
    private Long labiosProminencia;
    private Long tez;
    private Long tipoCara;
    private Long formaCabello;
    private Long implantacionCeja;
    private Long tipoMenton;
    private Long orejaLobDimension;
    private Long direccionCeja;
    private Long espesorLabioSup;
    private Long frenteAncho;
    private Long helixContorno;
    private Long alturaNariz;
    private Long orejaLobParticularidad;
    private Long helixAdherencia;
    private Long orejaLobAdherencia;
    private Long helixSuperior;
    private Long frenteInclinacion;
    private Long helixOriginal;
    private Long orejaLobContorno;
    private Long complexion;

	/**
	 * Método de acceso al WSDTO de Senia Particular
	 * @return
	 */
	public SeniaParticularWSDTO getSeniaParticular() {
		return seniaParticular;
	}
	public void setSeniaParticular(SeniaParticularWSDTO seniaParticular) {
		this.seniaParticular = seniaParticular;
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
	public Long getTipoSangre() {
		return tipoSangre;
	}
	/**
	 * 
	 * @param tipoSangre
	 */
	public void setTipoSangre(Long tipoSangre) {
		this.tipoSangre = tipoSangre;
	}
	/**
	 * Método de acceso al campo formaOreja.
	 * @return El valor del campo formaOreja
	 */
	public Long getFormaOreja() {
		return formaOreja;
	}
	/**
	 * Asigna el valor al campo formaOreja.
	 * @param formaOreja el valor formaOreja a asignar
	 */
	public void setFormaOreja(Long formaOreja) {
		this.formaOreja = formaOreja;
	}
	/**
	 * Método de acceso al campo tamanioCeja.
	 * @return El valor del campo tamanioCeja
	 */
	public Long getTamanioCeja() {
		return tamanioCeja;
	}
	/**
	 * Asigna el valor al campo tamanioCeja.
	 * @param tamanioCeja el valor tamanioCeja a asignar
	 */
	public void setTamanioCeja(Long tamanioCeja) {
		this.tamanioCeja = tamanioCeja;
	}
	/**
	 * Método de acceso al campo tamanioOjo.
	 * @return El valor del campo tamanioOjo
	 */
	public Long getTamanioOjo() {
		return tamanioOjo;
	}
	/**
	 * Asigna el valor al campo tamanioOjo.
	 * @param tamanioOjo el valor tamanioOjo a asignar
	 */
	public void setTamanioOjo(Long tamanioOjo) {
		this.tamanioOjo = tamanioOjo;
	}
	/**
	 * Método de acceso al campo formaCeja.
	 * @return El valor del campo formaCeja
	 */
	public Long getFormaCeja() {
		return formaCeja;
	}
	/**
	 * Asigna el valor al campo formaCeja.
	 * @param formaCeja el valor formaCeja a asignar
	 */
	public void setFormaCeja(Long formaCeja) {
		this.formaCeja = formaCeja;
	}
	/**
	 * Método de acceso al campo anchoNariz.
	 * @return El valor del campo anchoNariz
	 */
	public Long getAnchoNariz() {
		return anchoNariz;
	}
	/**
	 * Asigna el valor al campo anchoNariz.
	 * @param anchoNariz el valor anchoNariz a asignar
	 */
	public void setAnchoNariz(Long anchoNariz) {
		this.anchoNariz = anchoNariz;
	}
	/**
	 * Método de acceso al campo espesorLabioInf.
	 * @return El valor del campo espesorLabioInf
	 */
	public Long getEspesorLabioInf() {
		return espesorLabioInf;
	}
	/**
	 * Asigna el valor al campo espesorLabioInf.
	 * @param espesorLabioInf el valor espesorLabioInf a asignar
	 */
	public void setEspesorLabioInf(Long espesorLabioInf) {
		this.espesorLabioInf = espesorLabioInf;
	}
	/**
	 * Método de acceso al campo orejaTamanio.
	 * @return El valor del campo orejaTamanio
	 */
	public Long getOrejaTamanio() {
		return orejaTamanio;
	}
	/**
	 * Asigna el valor al campo orejaTamanio.
	 * @param orejaTamanio el valor orejaTamanio a asignar
	 */
	public void setOrejaTamanio(Long orejaTamanio) {
		this.orejaTamanio = orejaTamanio;
	}
	/**
	 * Método de acceso al campo cabelloImplantacion.
	 * @return El valor del campo cabelloImplantacion
	 */
	public Long getCabelloImplantacion() {
		return cabelloImplantacion;
	}
	/**
	 * Asigna el valor al campo cabelloImplantacion.
	 * @param cabelloImplantacion el valor cabelloImplantacion a asignar
	 */
	public void setCabelloImplantacion(Long cabelloImplantacion) {
		this.cabelloImplantacion = cabelloImplantacion;
	}
	/**
	 * Método de acceso al campo tamanioOjoParticularidad.
	 * @return El valor del campo tamanioOjoParticularidad
	 */
	public Long getColorOjos() {
		return colorOjos;
	}
	/**
	 * Asigna el valor al campo tamanioOjoParticularidad.
	 * @param tamanioOjoParticularidad el valor tamanioOjoParticularidad a asignar
	 */
	public void setColorOjos(Long colorOjos) {
		this.colorOjos = colorOjos;
	}
	/**
	 * Método de acceso al campo frenteAltura.
	 * @return El valor del campo frenteAltura
	 */
	public Long getFrenteAltura() {
		return frenteAltura;
	}
	/**
	 * Asigna el valor al campo frenteAltura.
	 * @param frenteAltura el valor frenteAltura a asignar
	 */
	public void setFrenteAltura(Long frenteAltura) {
		this.frenteAltura = frenteAltura;
	}
	/**
	 * Método de acceso al campo tamanioOjoDimension.
	 * @return El valor del campo tamanioOjoDimension
	 */
	public Long getFormaOjos() {
		return formaOjos;
	}
	/**
	 * Asigna el valor al campo tamanioOjoDimension.
	 * @param tamanioOjoDimension el valor tamanioOjoDimension a asignar
	 */
	public void setFormaOjos(Long formaOjos) {
		this.formaOjos = formaOjos;
	}
	/**
	 * Método de acceso al campo raizNariz.
	 * @return El valor del campo raizNariz
	 */
	public Long getRaizNariz() {
		return raizNariz;
	}
	/**
	 * Asigna el valor al campo raizNariz.
	 * @param raizNariz el valor raizNariz a asignar
	 */
	public void setRaizNariz(Long raizNariz) {
		this.raizNariz = raizNariz;
	}
	/**
	 * Método de acceso al campo formaMenton.
	 * @return El valor del campo formaMenton
	 */
	public Long getFormaMenton() {
		return formaMenton;
	}
	/**
	 * Asigna el valor al campo formaMenton.
	 * @param formaMenton el valor formaMenton a asignar
	 */
	public void setFormaMenton(Long formaMenton) {
		this.formaMenton = formaMenton;
	}
	/**
	 * Método de acceso al campo calvicieTipo.
	 * @return El valor del campo calvicieTipo
	 */
	public Long getCalvicieTipo() {
		return calvicieTipo;
	}
	/**
	 * Asigna el valor al campo calvicieTipo.
	 * @param calvicieTipo el valor calvicieTipo a asignar
	 */
	public void setCalvicieTipo(Long calvicieTipo) {
		this.calvicieTipo = calvicieTipo;
	}
	/**
	 * Método de acceso al campo inclinacionMenton.
	 * @return El valor del campo inclinacionMenton
	 */
	public Long getInclinacionMenton() {
		return inclinacionMenton;
	}
	/**
	 * Asigna el valor al campo inclinacionMenton.
	 * @param inclinacionMenton el valor inclinacionMenton a asignar
	 */
	public void setInclinacionMenton(Long inclinacionMenton) {
		this.inclinacionMenton = inclinacionMenton;
	}
	/**
	 * Método de acceso al campo cabelloCantidad.
	 * @return El valor del campo cabelloCantidad
	 */
	public Long getCabelloCantidad() {
		return cabelloCantidad;
	}
	/**
	 * Asigna el valor al campo cabelloCantidad.
	 * @param cabelloCantidad el valor cabelloCantidad a asignar
	 */
	public void setCabelloCantidad(Long cabelloCantidad) {
		this.cabelloCantidad = cabelloCantidad;
	}
	/**
	 * Método de acceso al campo alturaNasoLabial.
	 * @return El valor del campo alturaNasoLabial
	 */
	public Long getAlturaNasoLabial() {
		return alturaNasoLabial;
	}
	/**
	 * Asigna el valor al campo alturaNasoLabial.
	 * @param alturaNasoLabial el valor alturaNasoLabial a asignar
	 */
	public void setAlturaNasoLabial(Long alturaNasoLabial) {
		this.alturaNasoLabial = alturaNasoLabial;
	}
	/**
	 * Método de acceso al campo baseNariz.
	 * @return El valor del campo baseNariz
	 */
	public Long getBaseNariz() {
		return baseNariz;
	}
	/**
	 * Asigna el valor al campo baseNariz.
	 * @param baseNariz el valor baseNariz a asignar
	 */
	public void setBaseNariz(Long baseNariz) {
		this.baseNariz = baseNariz;
	}
	/**
	 * Método de acceso al campo dorsoNariz.
	 * @return El valor del campo dorsoNariz
	 */
	public Long getDorsoNariz() {
		return dorsoNariz;
	}
	/**
	 * Asigna el valor al campo dorsoNariz.
	 * @param dorsoNariz el valor dorsoNariz a asignar
	 */
	public void setDorsoNariz(Long dorsoNariz) {
		this.dorsoNariz = dorsoNariz;
	}
	/**
	 * Método de acceso al campo colorCabello.
	 * @return El valor del campo colorCabello
	 */
	public Long getColorCabello() {
		return colorCabello;
	}
	/**
	 * Asigna el valor al campo colorCabello.
	 * @param colorCabello el valor colorCabello a asignar
	 */
	public void setColorCabello(Long colorCabello) {
		this.colorCabello = colorCabello;
	}
	/**
	 * Método de acceso al campo labioComisuras.
	 * @return El valor del campo labioComisuras
	 */
	public Long getLabioComisuras() {
		return labioComisuras;
	}
	/**
	 * Asigna el valor al campo labioComisuras.
	 * @param labioComisuras el valor labioComisuras a asignar
	 */
	public void setLabioComisuras(Long labioComisuras) {
		this.labioComisuras = labioComisuras;
	}
	/**
	 * Método de acceso al campo helixPosterior.
	 * @return El valor del campo helixPosterior
	 */
	public Long getHelixPosterior() {
		return helixPosterior;
	}
	/**
	 * Asigna el valor al campo helixPosterior.
	 * @param helixPosterior el valor helixPosterior a asignar
	 */
	public void setHelixPosterior(Long helixPosterior) {
		this.helixPosterior = helixPosterior;
	}
	/**
	 * Método de acceso al campo tamanioBoca.
	 * @return El valor del campo tamanioBoca
	 */
	public Long getTamanioBoca() {
		return tamanioBoca;
	}
	/**
	 * Asigna el valor al campo tamanioBoca.
	 * @param tamanioBoca el valor tamanioBoca a asignar
	 */
	public void setTamanioBoca(Long tamanioBoca) {
		this.tamanioBoca = tamanioBoca;
	}
	/**
	 * Método de acceso al campo labiosProminencia.
	 * @return El valor del campo labiosProminencia
	 */
	public Long getLabiosProminencia() {
		return labiosProminencia;
	}
	/**
	 * Asigna el valor al campo labiosProminencia.
	 * @param labiosProminencia el valor labiosProminencia a asignar
	 */
	public void setLabiosProminencia(Long labiosProminencia) {
		this.labiosProminencia = labiosProminencia;
	}
	/**
	 * Método de acceso al campo tez.
	 * @return El valor del campo tez
	 */
	public Long getTez() {
		return tez;
	}
	/**
	 * Asigna el valor al campo tez.
	 * @param tez el valor tez a asignar
	 */
	public void setTez(Long tez) {
		this.tez = tez;
	}
	/**
	 * Método de acceso al campo tipoCara.
	 * @return El valor del campo tipoCara
	 */
	public Long getTipoCara() {
		return tipoCara;
	}
	/**
	 * Asigna el valor al campo tipoCara.
	 * @param tipoCara el valor tipoCara a asignar
	 */
	public void setTipoCara(Long tipoCara) {
		this.tipoCara = tipoCara;
	}
	/**
	 * Método de acceso al campo formaCabello.
	 * @return El valor del campo formaCabello
	 */
	public Long getFormaCabello() {
		return formaCabello;
	}
	/**
	 * Asigna el valor al campo formaCabello.
	 * @param formaCabello el valor formaCabello a asignar
	 */
	public void setFormaCabello(Long formaCabello) {
		this.formaCabello = formaCabello;
	}
	/**
	 * Método de acceso al campo implantacionCeja.
	 * @return El valor del campo implantacionCeja
	 */
	public Long getImplantacionCeja() {
		return implantacionCeja;
	}
	/**
	 * Asigna el valor al campo implantacionCeja.
	 * @param implantacionCeja el valor implantacionCeja a asignar
	 */
	public void setImplantacionCeja(Long implantacionCeja) {
		this.implantacionCeja = implantacionCeja;
	}
	/**
	 * Método de acceso al campo tipoMenton.
	 * @return El valor del campo tipoMenton
	 */
	public Long getTipoMenton() {
		return tipoMenton;
	}
	/**
	 * Asigna el valor al campo tipoMenton.
	 * @param tipoMenton el valor tipoMenton a asignar
	 */
	public void setTipoMenton(Long tipoMenton) {
		this.tipoMenton = tipoMenton;
	}
	/**
	 * Método de acceso al campo orejaLobDimension.
	 * @return El valor del campo orejaLobDimension
	 */
	public Long getOrejaLobDimension() {
		return orejaLobDimension;
	}
	/**
	 * Asigna el valor al campo orejaLobDimension.
	 * @param orejaLobDimension el valor orejaLobDimension a asignar
	 */
	public void setOrejaLobDimension(Long orejaLobDimension) {
		this.orejaLobDimension = orejaLobDimension;
	}
	/**
	 * Método de acceso al campo direccionCeja.
	 * @return El valor del campo direccionCeja
	 */
	public Long getDireccionCeja() {
		return direccionCeja;
	}
	/**
	 * Asigna el valor al campo direccionCeja.
	 * @param direccionCeja el valor direccionCeja a asignar
	 */
	public void setDireccionCeja(Long direccionCeja) {
		this.direccionCeja = direccionCeja;
	}
	/**
	 * Método de acceso al campo espesorLabioSup.
	 * @return El valor del campo espesorLabioSup
	 */
	public Long getEspesorLabioSup() {
		return espesorLabioSup;
	}
	/**
	 * Asigna el valor al campo espesorLabioSup.
	 * @param espesorLabioSup el valor espesorLabioSup a asignar
	 */
	public void setEspesorLabioSup(Long espesorLabioSup) {
		this.espesorLabioSup = espesorLabioSup;
	}
	/**
	 * Método de acceso al campo frenteAncho.
	 * @return El valor del campo frenteAncho
	 */
	public Long getFrenteAncho() {
		return frenteAncho;
	}
	/**
	 * Asigna el valor al campo frenteAncho.
	 * @param frenteAncho el valor frenteAncho a asignar
	 */
	public void setFrenteAncho(Long frenteAncho) {
		this.frenteAncho = frenteAncho;
	}
	/**
	 * Método de acceso al campo helixContorno.
	 * @return El valor del campo helixContorno
	 */
	public Long getHelixContorno() {
		return helixContorno;
	}
	/**
	 * Asigna el valor al campo helixContorno.
	 * @param helixContorno el valor helixContorno a asignar
	 */
	public void setHelixContorno(Long helixContorno) {
		this.helixContorno = helixContorno;
	}
	/**
	 * Método de acceso al campo alturaNariz.
	 * @return El valor del campo alturaNariz
	 */
	public Long getAlturaNariz() {
		return alturaNariz;
	}
	/**
	 * Asigna el valor al campo alturaNariz.
	 * @param alturaNariz el valor alturaNariz a asignar
	 */
	public void setAlturaNariz(Long alturaNariz) {
		this.alturaNariz = alturaNariz;
	}
	/**
	 * Método de acceso al campo orejaLobParticularidad.
	 * @return El valor del campo orejaLobParticularidad
	 */
	public Long getOrejaLobParticularidad() {
		return orejaLobParticularidad;
	}
	/**
	 * Asigna el valor al campo orejaLobParticularidad.
	 * @param orejaLobParticularidad el valor orejaLobParticularidad a asignar
	 */
	public void setOrejaLobParticularidad(Long orejaLobParticularidad) {
		this.orejaLobParticularidad = orejaLobParticularidad;
	}
	/**
	 * Método de acceso al campo helixAdherencia.
	 * @return El valor del campo helixAdherencia
	 */
	public Long getHelixAdherencia() {
		return helixAdherencia;
	}
	/**
	 * Asigna el valor al campo helixAdherencia.
	 * @param helixAdherencia el valor helixAdherencia a asignar
	 */
	public void setHelixAdherencia(Long helixAdherencia) {
		this.helixAdherencia = helixAdherencia;
	}
	/**
	 * Método de acceso al campo orejaLobAdherencia.
	 * @return El valor del campo orejaLobAdherencia
	 */
	public Long getOrejaLobAdherencia() {
		return orejaLobAdherencia;
	}
	/**
	 * Asigna el valor al campo orejaLobAdherencia.
	 * @param orejaLobAdherencia el valor orejaLobAdherencia a asignar
	 */
	public void setOrejaLobAdherencia(Long orejaLobAdherencia) {
		this.orejaLobAdherencia = orejaLobAdherencia;
	}
	/**
	 * Método de acceso al campo helixSuperior.
	 * @return El valor del campo helixSuperior
	 */
	public Long getHelixSuperior() {
		return helixSuperior;
	}
	/**
	 * Asigna el valor al campo helixSuperior.
	 * @param helixAnterior el valor helixSuperior a asignar
	 */
	public void setHelixSuperior(Long helixSuperior) {
		this.helixSuperior = helixSuperior;
	}
	/**
	 * Método de acceso al campo frenteInclinacion.
	 * @return El valor del campo frenteInclinacion
	 */
	public Long getFrenteInclinacion() {
		return frenteInclinacion;
	}
	/**
	 * Asigna el valor al campo frenteInclinacion.
	 * @param frenteInclinacion el valor frenteInclinacion a asignar
	 */
	public void setFrenteInclinacion(Long frenteInclinacion) {
		this.frenteInclinacion = frenteInclinacion;
	}
	/**
	 * Método de acceso al campo helixOriginal.
	 * @return El valor del campo helixOriginal
	 */
	public Long getHelixOriginal() {
		return helixOriginal;
	}
	/**
	 * Asigna el valor al campo helixOriginal.
	 * @param helixAnterior el valor helixOriginal a asignar
	 */
	public void setHelixOriginal(Long helixOriginal) {
		this.helixOriginal = helixOriginal;
	}
	/**
	 * Método de acceso al campo OrejaLobContorno.
	 * @return El valor del campo OrejaLobContorno
	 */
	public Long getOrejaLobContorno() {
		return orejaLobContorno;
	}
	/**
	 * Asigna el valor al campo OrejaLobContorno.
	 * @param helixAnterior el valor OrejaLobContorno a asignar
	 */
	public void setOrejaLobContorno(Long orejaLobContorno) {
		this.orejaLobContorno = orejaLobContorno;
	}	
	/**
	 * Método de acceso al campo Complexion.
	 * @return El valor del campo Complexion
	 */
	public Long getComplexion() {
		return complexion;
	}
	/**
	 * Asigna el valor al campo Complexion.
	 * @param helixAnterior el valor Complexion a asignar
	 */
	public void setComplexion(Long complexion) {
		this.complexion = complexion;
	}
}
