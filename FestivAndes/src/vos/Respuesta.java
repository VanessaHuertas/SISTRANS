/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * -------------------------------------------------------------------
 */
package vos;

import java.util.Date;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa una Banco
 */
public class Respuesta
{
	// Atributos

	/**
	 * Id del banco
	 */
	@JsonProperty(value="nombreEspectaculo")
	private String nombreEspectaculo;

	/**
	 * Número de la cuenta 
	 */
	@JsonProperty(value="fechaFuncion")
	private Date fechaFuncion;

	/**
	 * Saldo de la cuenta
	 */
	@JsonProperty(value="sitioFuncion")
	private String sitioFuncion;

	/**
	 * Id del festival
	 */
	@JsonProperty(value="boletasVendidas")
	private int boletasVendidas;
	
	@JsonProperty(value="usuariosRegistrados")
	private String usuariosRegistrados;

	@JsonProperty(value="horaFin")
	private String horaFin;
	
	@JsonProperty(value="dia")
	private String dia;
	
	/**
	 * Método constructor de la clase banco
	 * <b>post: </b> Crea el banco con los valores que entran como parámetro
	 * @param idBanco - Id del banco
	 * @param numeroCuenta- Número de la cuenta
	 * @param saldoCuenta - Saldo en la cuenta
	 * @param idFestival - Identificador del festival 
	 */
	public Respuesta(@JsonProperty(value="fechaInicio")Date fechaInicio,@JsonProperty(value="fechaFin") Date fechaFin, 
			@JsonProperty(value="elementosEscenario") String elementosEscenario, @JsonProperty(value="tipoLocalidad") String tipoLocalidad, 
			@JsonProperty(value="horaInicio") String horaInicio, @JsonProperty(value="horaFin") String horaFin, @JsonProperty(value="dia") String dia) 
	{
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.elementosEscenario = elementosEscenario;
		this.tipoLocalidad = tipoLocalidad;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.dia = dia;
	}
	
	//Getters y setters

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getElementosEscenario() {
		return elementosEscenario;
	}

	public void setElementosEscenario(String elementosEscenario) {
		this.elementosEscenario = elementosEscenario;
	}

	public String getTipoLocalidad() {
		return tipoLocalidad;
	}

	public void setTipoLocalidad(String tipoLocalidad) {
		this.tipoLocalidad = tipoLocalidad;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}	
}