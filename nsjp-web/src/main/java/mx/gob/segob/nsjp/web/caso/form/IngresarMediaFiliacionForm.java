/**
*
* Nombre del Programa: IngresarMediaFiliacionForm.java                                    
* Autor: Cuauhtemoc Paredes Serrano
* Compania: Ultrasist                                                
* Proyecto: NSJP                    Fecha: 03/03/2011 
* Marca de cambio: N/A                                                     
* Descripcion General: Form que carga informacion de media filiacion             
* Programa Dependiente:N/A                                                      
* Programa Subsecuente:N/A                                                      
* Cond. de ejecucion:N/A                                                      
* Dias de ejecucion:N/A                             Horario: N/A       
*                              MODIFICACIONES                                       
*------------------------------------------------------------------------------           
* Autor:N/A                                                           
* Compania:N/A                                                           
* Proyecto:N/A                                   Fecha: N/A       
* Modificacion:N/A                                                           
*------------------------------------------------------------------------------           
*/
package mx.gob.segob.nsjp.web.caso.form;

import mx.gob.segob.nsjp.dto.catalogo.CatalogoDTO;
import mx.gob.segob.nsjp.web.base.form.GenericForm;

/**
 * General: Form que carga informacion de media filiacion
 * 
 * @version 1.0
 * @author CuauhtemocPS Ultrasist
 * 
 */
public class IngresarMediaFiliacionForm extends GenericForm{
	
	
	private static final long serialVersionUID = -82717667334162586L;
	
	private Float dcEstatura;
	private Float dcPeso;
	private String cPerfil;
	private Boolean bBarba;
	private Boolean bBigote;
	private Boolean bLentes;
	private CatalogoDTO catTezDTO;
	private CatalogoDTO catColorCabelloDTO;
	private CatalogoDTO catColorOjosDTO;
	private CatalogoDTO catComplexionDTO;
	private CatalogoDTO catFormaBocaDTO;
	private CatalogoDTO catFormaCabelloDTO;
	private CatalogoDTO catFormaCaraDTO;
	private CatalogoDTO catFormaCejasDTO;
	private CatalogoDTO catFormaFrenteDTO;
	private CatalogoDTO catFormaMentonDTO;
	private CatalogoDTO catFormaNarizDTO;
	private CatalogoDTO catFormaOjosDTO;
	private CatalogoDTO catFormaOrejaDTO;
	private CatalogoDTO catGrosorCejasDTO;
	private CatalogoDTO catTipoCabelloDTO;
	private CatalogoDTO catTipoCaraDTO;
	public Float getDcEstatura() {
		return dcEstatura;
	}
	public void setDcEstatura(Float dcEstatura) {
		this.dcEstatura = dcEstatura;
	}
	public Float getDcPeso() {
		return dcPeso;
	}
	public void setDcPeso(Float dcPeso) {
		this.dcPeso = dcPeso;
	}
	public String getcPerfil() {
		return cPerfil;
	}
	public void setcPerfil(String cPerfil) {
		this.cPerfil = cPerfil;
	}
	public Boolean getbBarba() {
		return bBarba;
	}
	public void setbBarba(Boolean bBarba) {
		this.bBarba = bBarba;
	}
	public Boolean getbBigote() {
		return bBigote;
	}
	public void setbBigote(Boolean bBigote) {
		this.bBigote = bBigote;
	}
	public Boolean getbLentes() {
		return bLentes;
	}
	public void setbLentes(Boolean bLentes) {
		this.bLentes = bLentes;
	}
	public CatalogoDTO getCatTezDTO() {
		return catTezDTO;
	}
	public void setCatTezDTO(CatalogoDTO catTezDTO) {
		this.catTezDTO = catTezDTO;
	}
	public CatalogoDTO getCatColorCabelloDTO() {
		return catColorCabelloDTO;
	}
	public void setCatColorCabelloDTO(CatalogoDTO catColorCabelloDTO) {
		this.catColorCabelloDTO = catColorCabelloDTO;
	}
	public CatalogoDTO getCatColorOjosDTO() {
		return catColorOjosDTO;
	}
	public void setCatColorOjosDTO(CatalogoDTO catColorOjosDTO) {
		this.catColorOjosDTO = catColorOjosDTO;
	}
	public CatalogoDTO getCatComplexionDTO() {
		return catComplexionDTO;
	}
	public void setCatComplexionDTO(CatalogoDTO catComplexionDTO) {
		this.catComplexionDTO = catComplexionDTO;
	}
	public CatalogoDTO getCatFormaBocaDTO() {
		return catFormaBocaDTO;
	}
	public void setCatFormaBocaDTO(CatalogoDTO catFormaBocaDTO) {
		this.catFormaBocaDTO = catFormaBocaDTO;
	}
	public CatalogoDTO getCatFormaCabelloDTO() {
		return catFormaCabelloDTO;
	}
	public void setCatFormaCabelloDTO(CatalogoDTO catFormaCabelloDTO) {
		this.catFormaCabelloDTO = catFormaCabelloDTO;
	}
	public CatalogoDTO getCatFormaCaraDTO() {
		return catFormaCaraDTO;
	}
	public void setCatFormaCaraDTO(CatalogoDTO catFormaCaraDTO) {
		this.catFormaCaraDTO = catFormaCaraDTO;
	}
	public CatalogoDTO getCatFormaCejasDTO() {
		return catFormaCejasDTO;
	}
	public void setCatFormaCejasDTO(CatalogoDTO catFormaCejasDTO) {
		this.catFormaCejasDTO = catFormaCejasDTO;
	}
	public CatalogoDTO getCatFormaFrenteDTO() {
		return catFormaFrenteDTO;
	}
	public void setCatFormaFrenteDTO(CatalogoDTO catFormaFrenteDTO) {
		this.catFormaFrenteDTO = catFormaFrenteDTO;
	}
	public CatalogoDTO getCatFormaMentonDTO() {
		return catFormaMentonDTO;
	}
	public void setCatFormaMentonDTO(CatalogoDTO catFormaMentonDTO) {
		this.catFormaMentonDTO = catFormaMentonDTO;
	}
	public CatalogoDTO getCatFormaNarizDTO() {
		return catFormaNarizDTO;
	}
	public void setCatFormaNarizDTO(CatalogoDTO catFormaNarizDTO) {
		this.catFormaNarizDTO = catFormaNarizDTO;
	}
	public CatalogoDTO getCatFormaOjosDTO() {
		return catFormaOjosDTO;
	}
	public void setCatFormaOjosDTO(CatalogoDTO catFormaOjosDTO) {
		this.catFormaOjosDTO = catFormaOjosDTO;
	}
	public CatalogoDTO getCatFormaOrejaDTO() {
		return catFormaOrejaDTO;
	}
	public void setCatFormaOrejaDTO(CatalogoDTO catFormaOrejaDTO) {
		this.catFormaOrejaDTO = catFormaOrejaDTO;
	}
	public CatalogoDTO getCatGrosorCejasDTO() {
		return catGrosorCejasDTO;
	}
	public void setCatGrosorCejasDTO(CatalogoDTO catGrosorCejasDTO) {
		this.catGrosorCejasDTO = catGrosorCejasDTO;
	}
	public CatalogoDTO getCatTipoCabelloDTO() {
		return catTipoCabelloDTO;
	}
	public void setCatTipoCabelloDTO(CatalogoDTO catTipoCabelloDTO) {
		this.catTipoCabelloDTO = catTipoCabelloDTO;
	}
	public CatalogoDTO getCatTipoCaraDTO() {
		return catTipoCaraDTO;
	}
	public void setCatTipoCaraDTO(CatalogoDTO catTipoCaraDTO) {
		this.catTipoCaraDTO = catTipoCaraDTO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
