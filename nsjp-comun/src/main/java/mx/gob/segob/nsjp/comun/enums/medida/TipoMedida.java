package mx.gob.segob.nsjp.comun.enums.medida;

public enum TipoMedida {

	ARRESTO_DOMICILIARIO(2085L),
	COLOCAR_LOCALIZADORES_ELECTRONICOS(2084L),
	GARANTIA_ECONOMICA(2081L),
	INTERNAR_EN_CENTRO_DE_SALUD(2089L),
	PRESENTARSE_PERIODICAMENTE(2083L),
	Prision_Preventiva(2080L),
	PROHIBIR_DETERMINADA_CONVIVENCIA(2086L),
	PROHIBIR_SALIDA_TERRITORIAL(2090L),
	SEPARACION_DEL_DOMICILIO(2087L),
	SOMETER_A_VIGILANCIA(2082L),
	SUSPENDER_DERECHOS(2088L);
	
	private Long valorId;
	
	private TipoMedida (Long valorIdPredefinido) {
		this.valorId=valorIdPredefinido;
	}
	
	/**
     * M�todo de acceso al campo valorId.
     * 
     * @return El valor del campo valorId asociado en le BD.
     */
	public Long getValorId() {
		return valorId;
	}

	
}
