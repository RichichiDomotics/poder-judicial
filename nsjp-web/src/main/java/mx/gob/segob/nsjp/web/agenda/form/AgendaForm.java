package mx.gob.segob.nsjp.web.agenda.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mx.gob.segob.nsjp.web.base.form.GenericForm;

public class AgendaForm extends GenericForm {

	public static final String VIEW_TYPE_DAY    = "day";
	public static final String VIEW_TYPE_WEEK   = "week";
	public static final String VIEW_TYPE_MONTH  = "month";
	public static final int    DAYS_WEEK        = 6;
	public static final int    MAX_HOUR_DAY     = 23;
	public static final int    MAX_MIN_SEC      = 59;
	public static final String DATE_PATTERN     = "MM/dd/yyyy HH:mm:ss";
	public static final String CALENDAR_LIST    = "list";
	public static final String CALENDAR_ADD     = "add";
	public static final String CALENDAR_REMOVE  = "remove";
	public static final String CALENDAR_UPDATE  = "update";
	
	private String method;
	private String showdate;
	private String viewtype;
	
	private String idEvento;
	private String asunto;
	private String tipoTarea;
	private String fechaInicio;
	private String horaInicio;
	private String fechaFinal;
	private String horaFinal;
	private String lugar;
	private String descripcion;
	private String alarma;
	private SimpleDateFormat sdt;
	
	public AgendaForm() {
		sdt = new SimpleDateFormat ("MM/dd/yyyy HH:mm:ss");
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}

	public String getViewtype() {
		return viewtype;
	}
	
	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}
	
	public Date getShowdateToDate() {
		Date fecha = null;
		try {
			fecha = sdt.parse(showdate + " 00:00:00");
		} catch (ParseException e) {
		}
		return fecha;
	}

	public SimpleDateFormat getDateFormat() {
		return sdt;
	}

	
	/**
	 * Regresa el valor de la propiedad idEvento
	 * @return the idEvento
	 */
	public String getIdEvento() {
		return idEvento;
	}

	
	/**
	 * Establece el valor de la propiedad idEvento
	 * @param idEvento valo idEvento a almacenar
	 */
	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	
	/**
	 * Regresa el valor de la propiedad asunto
	 * @return the asunto
	 */
	public String getAsunto() {
		return asunto;
	}

	
	/**
	 * Establece el valor de la propiedad asunto
	 * @param asunto valo asunto a almacenar
	 */
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	
	/**
	 * Regresa el valor de la propiedad tipoTarea
	 * @return the tipoTarea
	 */
	public String getTipoTarea() {
		return tipoTarea;
	}

	
	/**
	 * Establece el valor de la propiedad tipoTarea
	 * @param tipoTarea valo tipoTarea a almacenar
	 */
	public void setTipoTarea(String tipoTarea) {
		this.tipoTarea = tipoTarea;
	}

	
	/**
	 * Regresa el valor de la propiedad fechaInicio
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	
	/**
	 * Establece el valor de la propiedad fechaInicio
	 * @param fechaInicio valo fechaInicio a almacenar
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	
	/**
	 * Regresa el valor de la propiedad horaInicio
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	
	/**
	 * Establece el valor de la propiedad horaInicio
	 * @param horaInicio valo horaInicio a almacenar
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	
	/**
	 * Regresa el valor de la propiedad fechaFinal
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	
	/**
	 * Establece el valor de la propiedad fechaFinal
	 * @param fechaFinal valo fechaFinal a almacenar
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	
	/**
	 * Regresa el valor de la propiedad horaFinal
	 * @return the horaFinal
	 */
	public String getHoraFinal() {
		return horaFinal;
	}

	
	/**
	 * Establece el valor de la propiedad horaFinal
	 * @param horaFinal valo horaFinal a almacenar
	 */
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	
	/**
	 * Regresa el valor de la propiedad lugar
	 * @return the lugar
	 */
	public String getLugar() {
		return lugar;
	}

	
	/**
	 * Establece el valor de la propiedad lugar
	 * @param lugar valo lugar a almacenar
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	
	/**
	 * Regresa el valor de la propiedad descripcion
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	
	/**
	 * Establece el valor de la propiedad descripcion
	 * @param descripcion valo descripcion a almacenar
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * Establece el valor de la propiedad alarma
	 * @param alarma valo alarma a almacenar
	 */
	public void setAlarma(String alarma) {
		this.alarma = alarma;
	}

	/**
	 * Regresa el valor de la propiedad alarma
	 * @return the alarma
	 */
	public String getAlarma() {
		return alarma;
	}

	/**
	 * Regresa el valor de la propiedad sdt
	 * @return the sdt
	 */
	public SimpleDateFormat getSdt() {
		return sdt;
	}


	/**
	 * Establece el valor de la propiedad sdt
	 * @param sdt valo sdt a almacenar
	 */
	public void setSdt(SimpleDateFormat sdt) {
		this.sdt = sdt;
	}
	
}
