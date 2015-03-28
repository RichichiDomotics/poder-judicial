/**
* Nombre del Programa : DatosGeneralesExpedienteDTO.java
* Autor                            : Ricardo Gama
* Compania                    : Ultrasist
* Proyecto                      : NSJP                    Fecha: 04/07/2011
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.segob.nsjp.dto.base.GenericDTO;

/**
 * DTO que contiene la información de un expediente asociada a la seccion de "Generales" 
 * @version 1.0
 * @author RGama
 *
 */
public class DatosGeneralesExpedienteDTO extends GenericDTO {

	private static final long serialVersionUID = -5677520541556828238L;
    //Información relacionanda a un numero de expediente
	private Long expedienteId;
    private String origenExpediente;
    private String origenNumeroExpediente;
    private String estatusNumeroExpediente;


    //Información relacionanda a los objetos que tiene un expediente
    private Integer totalVehiculos;
    private Integer totalEquiposComputo;
    private Integer totalEquiposTelefonicos;
    private Integer totalArmas;
    private Integer totalExplosivos;
    private Integer totalSustancias;
    private Integer totalAnimales;
    private Integer totalAeronaves;
    private Integer totalEmbarcaciones;
    private Integer totalInmuelbes;
    private Integer totalNumerarios;
    private Integer totalVegetales;
    private Integer totalDocumentosOficiales;
    private Integer totalJoyas;
    private Integer totalObrasDeArte;
    private Integer totalOtrosObjestos;
    private Integer totalDocumentosDelExpediente;
    
    
    private String vehiculos;
    private String equiposComputo;
    private String equiposTelefonicos;
    private String armas;
    private String explosivos;
    private String sustancias;
    private String animales;
    private String aeronaves;
    private String embarcaciones;
    private String inmuelbes;
    private String numerarios;
    private String vegetales;
    private String documentosOficiales;
    private String joyas;
    private String obrasDeArte;
    private String otrosObjestos;
    private String documentosDelExpediente;
    
    private String ve;
    private String equiCom;
    private String equiTel;
    private String arm;
    private String expl;
    private String sus;
    private String anim;
    private String aero;
    private String embar;
    private String inmu;
    private String nume;
    private String vege;
    private String docuOfi;
    private String joy;
    private String obrArte;
    private String otrObj;
    private String docuExp;
    
    
    //Información relacionanda a los elementos de expediente
    private Integer totalDenunciantes;
    private Integer totalVictimas;
    private Integer totalProbablesResposables;
    private Integer totalTestigos;
    private Integer totalTraductores;
    private String quienDetuvo;
    private String esDesconocido ;
    private String fechaApertura;
    
    private String denunciantes;
    private String victimas;
    private String probablesResposables;
    private String testigos;
    private String traductores;
    private String quienDetuvoNombre;
    
    private String denun;
    private String vic;
    private String proba;
    private String test;
    private String tradu;
    private String quienDetu;
    
    Map<Long,List<String>> objetos = new HashMap<Long, List<String>>();
    Map<Long,List<String>> involucrados = new HashMap<Long, List<String>>();
    
    
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
	 * Método de acceso al campo totalVehiculos.
	 * @return El valor del campo totalVehiculos
	 */
	public Integer getTotalVehiculos() {
		return totalVehiculos;
	}
	/**
	 * Asigna el valor al campo totalVehiculos.
	 * @param totalVehiculos el valor totalVehiculos a asignar
	 */
	public void setTotalVehiculos(Integer totalVehiculos) {
		this.totalVehiculos = totalVehiculos;
	}
	/**
	 * Método de acceso al campo totalEquiposComputo.
	 * @return El valor del campo totalEquiposComputo
	 */
	public Integer getTotalEquiposComputo() {
		return totalEquiposComputo;
	}
	/**
	 * Asigna el valor al campo totalEquiposComputo.
	 * @param totalEquiposComputo el valor totalEquiposComputo a asignar
	 */
	public void setTotalEquiposComputo(Integer totalEquiposComputo) {
		this.totalEquiposComputo = totalEquiposComputo;
	}
	/**
	 * Método de acceso al campo totalEquiposTelefonicos.
	 * @return El valor del campo totalEquiposTelefonicos
	 */
	public Integer getTotalEquiposTelefonicos() {
		return totalEquiposTelefonicos;
	}
	/**
	 * Asigna el valor al campo totalEquiposTelefonicos.
	 * @param totalEquiposTelefonicos el valor totalEquiposTelefonicos a asignar
	 */
	public void setTotalEquiposTelefonicos(Integer totalEquiposTelefonicos) {
		this.totalEquiposTelefonicos = totalEquiposTelefonicos;
	}
	/**
	 * Método de acceso al campo totalArmas.
	 * @return El valor del campo totalArmas
	 */
	public Integer getTotalArmas() {
		return totalArmas;
	}
	/**
	 * Asigna el valor al campo totalArmas.
	 * @param totalArmas el valor totalArmas a asignar
	 */
	public void setTotalArmas(Integer totalArmas) {
		this.totalArmas = totalArmas;
	}
	/**
	 * Método de acceso al campo totalExplosivos.
	 * @return El valor del campo totalExplosivos
	 */
	public Integer getTotalExplosivos() {
		return totalExplosivos;
	}
	/**
	 * Asigna el valor al campo totalExplosivos.
	 * @param totalExplosivos el valor totalExplosivos a asignar
	 */
	public void setTotalExplosivos(Integer totalExplosivos) {
		this.totalExplosivos = totalExplosivos;
	}
	/**
	 * Método de acceso al campo totalSustancias.
	 * @return El valor del campo totalSustancias
	 */
	public Integer getTotalSustancias() {
		return totalSustancias;
	}
	/**
	 * Asigna el valor al campo totalSustancias.
	 * @param totalSustancias el valor totalSustancias a asignar
	 */
	public void setTotalSustancias(Integer totalSustancias) {
		this.totalSustancias = totalSustancias;
	}
	/**
	 * Método de acceso al campo totalAnimales.
	 * @return El valor del campo totalAnimales
	 */
	public Integer getTotalAnimales() {
		return totalAnimales;
	}
	/**
	 * Asigna el valor al campo totalAnimales.
	 * @param totalAnimales el valor totalAnimales a asignar
	 */
	public void setTotalAnimales(Integer totalAnimales) {
		this.totalAnimales = totalAnimales;
	}
	/**
	 * Método de acceso al campo totalAeronaves.
	 * @return El valor del campo totalAeronaves
	 */
	public Integer getTotalAeronaves() {
		return totalAeronaves;
	}
	/**
	 * Asigna el valor al campo totalAeronaves.
	 * @param totalAeronaves el valor totalAeronaves a asignar
	 */
	public void setTotalAeronaves(Integer totalAeronaves) {
		this.totalAeronaves = totalAeronaves;
	}
	/**
	 * Método de acceso al campo totalEmbarcaciones.
	 * @return El valor del campo totalEmbarcaciones
	 */
	public Integer getTotalEmbarcaciones() {
		return totalEmbarcaciones;
	}
	/**
	 * Asigna el valor al campo totalEmbarcaciones.
	 * @param totalEmbarcaciones el valor totalEmbarcaciones a asignar
	 */
	public void setTotalEmbarcaciones(Integer totalEmbarcaciones) {
		this.totalEmbarcaciones = totalEmbarcaciones;
	}
	/**
	 * Método de acceso al campo totalInmuelbes.
	 * @return El valor del campo totalInmuelbes
	 */
	public Integer getTotalInmuelbes() {
		return totalInmuelbes;
	}
	/**
	 * Asigna el valor al campo totalInmuelbes.
	 * @param totalInmuelbes el valor totalInmuelbes a asignar
	 */
	public void setTotalInmuelbes(Integer totalInmuelbes) {
		this.totalInmuelbes = totalInmuelbes;
	}
	/**
	 * Método de acceso al campo totalNumerarios.
	 * @return El valor del campo totalNumerarios
	 */
	public Integer getTotalNumerarios() {
		return totalNumerarios;
	}
	/**
	 * Asigna el valor al campo totalNumerarios.
	 * @param totalNumerarios el valor totalNumerarios a asignar
	 */
	public void setTotalNumerarios(Integer totalNumerarios) {
		this.totalNumerarios = totalNumerarios;
	}
	/**
	 * Método de acceso al campo totalVegetales.
	 * @return El valor del campo totalVegetales
	 */
	public Integer getTotalVegetales() {
		return totalVegetales;
	}
	/**
	 * Asigna el valor al campo totalVegetales.
	 * @param totalVegetales el valor totalVegetales a asignar
	 */
	public void setTotalVegetales(Integer totalVegetales) {
		this.totalVegetales = totalVegetales;
	}
	/**
	 * Método de acceso al campo totalDocumentosOficiales.
	 * @return El valor del campo totalDocumentosOficiales
	 */
	public Integer getTotalDocumentosOficiales() {
		return totalDocumentosOficiales;
	}
	/**
	 * Asigna el valor al campo totalDocumentosOficiales.
	 * @param totalDocumentosOficiales el valor totalDocumentosOficiales a asignar
	 */
	public void setTotalDocumentosOficiales(Integer totalDocumentosOficiales) {
		this.totalDocumentosOficiales = totalDocumentosOficiales;
	}
	/**
	 * Método de acceso al campo totalJoyas.
	 * @return El valor del campo totalJoyas
	 */
	public Integer getTotalJoyas() {
		return totalJoyas;
	}
	/**
	 * Asigna el valor al campo totalJoyas.
	 * @param totalJoyas el valor totalJoyas a asignar
	 */
	public void setTotalJoyas(Integer totalJoyas) {
		this.totalJoyas = totalJoyas;
	}
	/**
	 * Método de acceso al campo totalObrasDeArte.
	 * @return El valor del campo totalObrasDeArte
	 */
	public Integer getTotalObrasDeArte() {
		return totalObrasDeArte;
	}
	/**
	 * Asigna el valor al campo totalObrasDeArte.
	 * @param totalObrasDeArte el valor totalObrasDeArte a asignar
	 */
	public void setTotalObrasDeArte(Integer totalObrasDeArte) {
		this.totalObrasDeArte = totalObrasDeArte;
	}
	/**
	 * Método de acceso al campo totalOtrosObjestos.
	 * @return El valor del campo totalOtrosObjestos
	 */
	public Integer getTotalOtrosObjestos() {
		return totalOtrosObjestos;
	}
	/**
	 * Asigna el valor al campo totalOtrosObjestos.
	 * @param totalOtrosObjestos el valor totalOtrosObjestos a asignar
	 */
	public void setTotalOtrosObjestos(Integer totalOtrosObjestos) {
		this.totalOtrosObjestos = totalOtrosObjestos;
	}
	/**
	 * Método de acceso al campo totalDenunciantes.
	 * @return El valor del campo totalDenunciantes
	 */
	public Integer getTotalDenunciantes() {
		return totalDenunciantes;
	}
	/**
	 * Asigna el valor al campo totalDenunciantes.
	 * @param totalDenunciantes el valor totalDenunciantes a asignar
	 */
	public void setTotalDenunciantes(Integer totalDenunciantes) {
		this.totalDenunciantes = totalDenunciantes;
	}
	/**
	 * Método de acceso al campo totalVictimas.
	 * @return El valor del campo totalVictimas
	 */
	public Integer getTotalVictimas() {
		return totalVictimas;
	}
	/**
	 * Asigna el valor al campo totalVictimas.
	 * @param totalVictimas el valor totalVictimas a asignar
	 */
	public void setTotalVictimas(Integer totalVictimas) {
		this.totalVictimas = totalVictimas;
	}
	/**
	 * Método de acceso al campo totalProbablesResposables.
	 * @return El valor del campo totalProbablesResposables
	 */
	public Integer getTotalProbablesResposables() {
		return totalProbablesResposables;
	}
	/**
	 * Asigna el valor al campo totalProbablesResposables.
	 * @param totalProbablesResposables el valor totalProbablesResposables a asignar
	 */
	public void setTotalProbablesResposables(Integer totalProbablesResposables) {
		this.totalProbablesResposables = totalProbablesResposables;
	}
	/**
	 * Método de acceso al campo totalTestigos.
	 * @return El valor del campo totalTestigos
	 */
	public Integer getTotalTestigos() {
		return totalTestigos;
	}
	/**
	 * Asigna el valor al campo totalTestigos.
	 * @param totalTestigos el valor totalTestigos a asignar
	 */
	public void setTotalTestigos(Integer totalTestigos) {
		this.totalTestigos = totalTestigos;
	}
	/**
	 * Método de acceso al campo totalTraductores.
	 * @return El valor del campo totalTraductores
	 */
	public Integer getTotalTraductores() {
		return totalTraductores;
	}
	/**
	 * Asigna el valor al campo totalTraductores.
	 * @param totalTraductores el valor totalTraductores a asignar
	 */
	public void setTotalTraductores(Integer totalTraductores) {
		this.totalTraductores = totalTraductores;
	}
	/**
	 * Método de acceso al campo quienDetuvo.
	 * @return El valor del campo quienDetuvo
	 */
	public String getQuienDetuvo() {
		return quienDetuvo;
	}
	/**
	 * Asigna el valor al campo quienDetuvo.
	 * @param quienDetuvo el valor quienDetuvo a asignar
	 */
	public void setQuienDetuvo(String quienDetuvo) {
		this.quienDetuvo = quienDetuvo;
	}
	/**
	 * Método de acceso al campo origenExpediente.
	 * @return El valor del campo origenExpediente
	 */
	public String getOrigenExpediente() {
		return origenExpediente;
	}
	/**
	 * Asigna el valor al campo origenExpediente.
	 * @param origenExpediente el valor origenExpediente a asignar
	 */
	public void setOrigenExpediente(String origenExpediente) {
		this.origenExpediente = origenExpediente;
	}
	/**
	 * Método de acceso al campo estatusNumeroExpediente.
	 * @return El valor del campo estatusNumeroExpediente
	 */
	public String getEstatusNumeroExpediente() {
		return estatusNumeroExpediente;
	}
	/**
	 * Asigna el valor al campo estatusNumeroExpediente.
	 * @param estatusNumeroExpediente el valor estatusNumeroExpediente a asignar
	 */
	public void setEstatusNumeroExpediente(String estatusNumeroExpediente) {
		this.estatusNumeroExpediente = estatusNumeroExpediente;
	}
	/**
	 * Método de acceso al campo esDesconocido.
	 * @return El valor del campo esDesconocido
	 */
	public String getEsDesconocido() {
		return esDesconocido;
	}
	/**
	 * Asigna el valor al campo esDesconocido.
	 * @param esDesconocido el valor esDesconocido a asignar
	 */
	public void setEsDesconocido(String esDesconocido) {
		this.esDesconocido = esDesconocido;
	}
	/**
	 * Método de acceso al campo fechaApertura.
	 * @return El valor del campo fechaApertura
	 */
	public String getFechaApertura() {
		return fechaApertura;
	}
	/**
	 * Asigna el valor al campo fechaApertura.
	 * @param fechaApertura el valor fechaApertura a asignar
	 */
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	/**
	 * Método de acceso al campo objetos.
	 * @return El valor del campo objetos
	 */
	public Map<Long, List<String>> getObjetos() {
		return objetos;
	}
	/**
	 * Asigna el valor al campo objetos.
	 * @param objetos el valor objetos a asignar
	 */
	public void setObjetos(Map<Long, List<String>> objetos) {
		this.objetos = objetos;
	}
	/**
	 * Método de acceso al campo involucrados.
	 * @return El valor del campo involucrados
	 */
	public Map<Long, List<String>> getInvolucrados() {
		return involucrados;
	}
	/**
	 * Asigna el valor al campo involucrados.
	 * @param involucrados el valor involucrados a asignar
	 */
	public void setInvolucrados(Map<Long, List<String>> involucrados) {
		this.involucrados = involucrados;
	}
	/**
	 * Método de acceso al campo totalDocumentosDelExpediente.
	 * @return El valor del campo totalDocumentosDelExpediente
	 */
	public Integer getTotalDocumentosDelExpediente() {
		return totalDocumentosDelExpediente;
	}
	/**
	 * Asigna el valor al campo totalDocumentosDelExpediente.
	 * @param totalDocumentosDelExpediente el valor totalDocumentosDelExpediente a asignar
	 */
	public void setTotalDocumentosDelExpediente(Integer totalDocumentosDelExpediente) {
		this.totalDocumentosDelExpediente = totalDocumentosDelExpediente;
	}
	/**
	 * @return the vehiculos
	 */
	public String getVehiculos() {
		return vehiculos;
	}
	/**
	 * @param vehiculos the vehiculos to set
	 */
	public void setVehiculos(String vehiculos) {
		this.vehiculos = vehiculos;
	}
	/**
	 * @return the equiposComputo
	 */
	public String getEquiposComputo() {
		return equiposComputo;
	}
	/**
	 * @param equiposComputo the equiposComputo to set
	 */
	public void setEquiposComputo(String equiposComputo) {
		this.equiposComputo = equiposComputo;
	}
	/**
	 * @return the equiposTelefonicos
	 */
	public String getEquiposTelefonicos() {
		return equiposTelefonicos;
	}
	/**
	 * @param equiposTelefonicos the equiposTelefonicos to set
	 */
	public void setEquiposTelefonicos(String equiposTelefonicos) {
		this.equiposTelefonicos = equiposTelefonicos;
	}
	/**
	 * @return the armas
	 */
	public String getArmas() {
		return armas;
	}
	/**
	 * @param armas the armas to set
	 */
	public void setArmas(String armas) {
		this.armas = armas;
	}
	/**
	 * @return the explosivos
	 */
	public String getExplosivos() {
		return explosivos;
	}
	/**
	 * @param explosivos the explosivos to set
	 */
	public void setExplosivos(String explosivos) {
		this.explosivos = explosivos;
	}
	/**
	 * @return the sustancias
	 */
	public String getSustancias() {
		return sustancias;
	}
	/**
	 * @param sustancias the sustancias to set
	 */
	public void setSustancias(String sustancias) {
		this.sustancias = sustancias;
	}
	/**
	 * @return the animales
	 */
	public String getAnimales() {
		return animales;
	}
	/**
	 * @param animales the animales to set
	 */
	public void setAnimales(String animales) {
		this.animales = animales;
	}
	/**
	 * @return the aeronaves
	 */
	public String getAeronaves() {
		return aeronaves;
	}
	/**
	 * @param aeronaves the aeronaves to set
	 */
	public void setAeronaves(String aeronaves) {
		this.aeronaves = aeronaves;
	}
	/**
	 * @return the embarcaciones
	 */
	public String getEmbarcaciones() {
		return embarcaciones;
	}
	/**
	 * @param embarcaciones the embarcaciones to set
	 */
	public void setEmbarcaciones(String embarcaciones) {
		this.embarcaciones = embarcaciones;
	}
	/**
	 * @return the inmuelbes
	 */
	public String getInmuelbes() {
		return inmuelbes;
	}
	/**
	 * @param inmuelbes the inmuelbes to set
	 */
	public void setInmuelbes(String inmuelbes) {
		this.inmuelbes = inmuelbes;
	}
	/**
	 * @return the numerarios
	 */
	public String getNumerarios() {
		return numerarios;
	}
	/**
	 * @param numerarios the numerarios to set
	 */
	public void setNumerarios(String numerarios) {
		this.numerarios = numerarios;
	}
	/**
	 * @return the vegetales
	 */
	public String getVegetales() {
		return vegetales;
	}
	/**
	 * @param vegetales the vegetales to set
	 */
	public void setVegetales(String vegetales) {
		this.vegetales = vegetales;
	}
	/**
	 * @return the documentosOficiales
	 */
	public String getDocumentosOficiales() {
		return documentosOficiales;
	}
	/**
	 * @param documentosOficiales the documentosOficiales to set
	 */
	public void setDocumentosOficiales(String documentosOficiales) {
		this.documentosOficiales = documentosOficiales;
	}
	/**
	 * @return the joyas
	 */
	public String getJoyas() {
		return joyas;
	}
	/**
	 * @param joyas the joyas to set
	 */
	public void setJoyas(String joyas) {
		this.joyas = joyas;
	}
	/**
	 * @return the obrasDeArte
	 */
	public String getObrasDeArte() {
		return obrasDeArte;
	}
	/**
	 * @param obrasDeArte the obrasDeArte to set
	 */
	public void setObrasDeArte(String obrasDeArte) {
		this.obrasDeArte = obrasDeArte;
	}
	/**
	 * @return the otrosObjestos
	 */
	public String getOtrosObjestos() {
		return otrosObjestos;
	}
	/**
	 * @param otrosObjestos the otrosObjestos to set
	 */
	public void setOtrosObjestos(String otrosObjestos) {
		this.otrosObjestos = otrosObjestos;
	}
	/**
	 * @return the documentosDelExpediente
	 */
	public String getDocumentosDelExpediente() {
		return documentosDelExpediente;
	}
	/**
	 * @param documentosDelExpediente the documentosDelExpediente to set
	 */
	public void setDocumentosDelExpediente(String documentosDelExpediente) {
		this.documentosDelExpediente = documentosDelExpediente;
	}
	/**
	 * @return the denunciantes
	 */
	public String getDenunciantes() {
		return denunciantes;
	}
	/**
	 * @param denunciantes the denunciantes to set
	 */
	public void setDenunciantes(String denunciantes) {
		this.denunciantes = denunciantes;
	}
	/**
	 * @return the victimas
	 */
	public String getVictimas() {
		return victimas;
	}
	/**
	 * @param victimas the victimas to set
	 */
	public void setVictimas(String victimas) {
		this.victimas = victimas;
	}
	/**
	 * @return the probablesResposables
	 */
	public String getProbablesResposables() {
		return probablesResposables;
	}
	/**
	 * @param probablesResposables the probablesResposables to set
	 */
	public void setProbablesResposables(String probablesResposables) {
		this.probablesResposables = probablesResposables;
	}
	/**
	 * @return the testigos
	 */
	public String getTestigos() {
		return testigos;
	}
	/**
	 * @param testigos the testigos to set
	 */
	public void setTestigos(String testigos) {
		this.testigos = testigos;
	}
	/**
	 * @return the traductores
	 */
	public String getTraductores() {
		return traductores;
	}
	/**
	 * @param traductores the traductores to set
	 */
	public void setTraductores(String traductores) {
		this.traductores = traductores;
	}
	/**
	 * @return the quienDetuvoNombre
	 */
	public String getQuienDetuvoNombre() {
		return quienDetuvoNombre;
	}
	/**
	 * @param quienDetuvoNombre the quienDetuvoNombre to set
	 */
	public void setQuienDetuvoNombre(String quienDetuvoNombre) {
		this.quienDetuvoNombre = quienDetuvoNombre;
	}
	/**
	 * @return the ve
	 */
	public String getVe() {
		return ve;
	}
	/**
	 * @param ve the ve to set
	 */
	public void setVe(String ve) {
		this.ve = ve;
	}
	/**
	 * @return the equiCom
	 */
	public String getEquiCom() {
		return equiCom;
	}
	/**
	 * @param equiCom the equiCom to set
	 */
	public void setEquiCom(String equiCom) {
		this.equiCom = equiCom;
	}
	/**
	 * @return the equiTel
	 */
	public String getEquiTel() {
		return equiTel;
	}
	/**
	 * @param equiTel the equiTel to set
	 */
	public void setEquiTel(String equiTel) {
		this.equiTel = equiTel;
	}
	/**
	 * @return the arm
	 */
	public String getArm() {
		return arm;
	}
	/**
	 * @param arm the arm to set
	 */
	public void setArm(String arm) {
		this.arm = arm;
	}
	/**
	 * @return the expl
	 */
	public String getExpl() {
		return expl;
	}
	/**
	 * @param expl the expl to set
	 */
	public void setExpl(String expl) {
		this.expl = expl;
	}
	/**
	 * @return the sus
	 */
	public String getSus() {
		return sus;
	}
	/**
	 * @param sus the sus to set
	 */
	public void setSus(String sus) {
		this.sus = sus;
	}
	/**
	 * @return the anim
	 */
	public String getAnim() {
		return anim;
	}
	/**
	 * @param anim the anim to set
	 */
	public void setAnim(String anim) {
		this.anim = anim;
	}
	/**
	 * @return the aero
	 */
	public String getAero() {
		return aero;
	}
	/**
	 * @param aero the aero to set
	 */
	public void setAero(String aero) {
		this.aero = aero;
	}
	/**
	 * @return the embar
	 */
	public String getEmbar() {
		return embar;
	}
	/**
	 * @param embar the embar to set
	 */
	public void setEmbar(String embar) {
		this.embar = embar;
	}
	/**
	 * @return the inmu
	 */
	public String getInmu() {
		return inmu;
	}
	/**
	 * @param inmu the inmu to set
	 */
	public void setInmu(String inmu) {
		this.inmu = inmu;
	}
	/**
	 * @return the nume
	 */
	public String getNume() {
		return nume;
	}
	/**
	 * @param nume the nume to set
	 */
	public void setNume(String nume) {
		this.nume = nume;
	}
	/**
	 * @return the vege
	 */
	public String getVege() {
		return vege;
	}
	/**
	 * @param vege the vege to set
	 */
	public void setVege(String vege) {
		this.vege = vege;
	}
	/**
	 * @return the docuOfi
	 */
	public String getDocuOfi() {
		return docuOfi;
	}
	/**
	 * @param docuOfi the docuOfi to set
	 */
	public void setDocuOfi(String docuOfi) {
		this.docuOfi = docuOfi;
	}
	/**
	 * @return the joy
	 */
	public String getJoy() {
		return joy;
	}
	/**
	 * @param joy the joy to set
	 */
	public void setJoy(String joy) {
		this.joy = joy;
	}
	/**
	 * @return the obrArte
	 */
	public String getObrArte() {
		return obrArte;
	}
	/**
	 * @param obrArte the obrArte to set
	 */
	public void setObrArte(String obrArte) {
		this.obrArte = obrArte;
	}
	/**
	 * @return the otrObj
	 */
	public String getOtrObj() {
		return otrObj;
	}
	/**
	 * @param otrObj the otrObj to set
	 */
	public void setOtrObj(String otrObj) {
		this.otrObj = otrObj;
	}
	/**
	 * @return the docuExp
	 */
	public String getDocuExp() {
		return docuExp;
	}
	/**
	 * @param docuExp the docuExp to set
	 */
	public void setDocuExp(String docuExp) {
		this.docuExp = docuExp;
	}
	/**
	 * @return the denun
	 */
	public String getDenun() {
		return denun;
	}
	/**
	 * @param denun the denun to set
	 */
	public void setDenun(String denun) {
		this.denun = denun;
	}
	/**
	 * @return the vic
	 */
	public String getVic() {
		return vic;
	}
	/**
	 * @param vic the vic to set
	 */
	public void setVic(String vic) {
		this.vic = vic;
	}
	/**
	 * @return the proba
	 */
	public String getProba() {
		return proba;
	}
	/**
	 * @param proba the proba to set
	 */
	public void setProba(String proba) {
		this.proba = proba;
	}
	/**
	 * @return the test
	 */
	public String getTest() {
		return test;
	}
	/**
	 * @param test the test to set
	 */
	public void setTest(String test) {
		this.test = test;
	}
	/**
	 * @return the tradu
	 */
	public String getTradu() {
		return tradu;
	}
	/**
	 * @param tradu the tradu to set
	 */
	public void setTradu(String tradu) {
		this.tradu = tradu;
	}
	/**
	 * @return the quienDetu
	 */
	public String getQuienDetu() {
		return quienDetu;
	}
	/**
	 * @param quienDetu the quienDetu to set
	 */
	public void setQuienDetu(String quienDetu) {
		this.quienDetu = quienDetu;
	}
	public String getOrigenNumeroExpediente() {
		return origenNumeroExpediente;
	}
	public void setOrigenNumeroExpediente(String origenNumeroExpediente) {
		this.origenNumeroExpediente = origenNumeroExpediente;
	}
	
	
}
