/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una Banco
 */
public class ConsultaAs
{
	// Atributos

	/**
	 * nombre comapñia
	 */
	@JsonProperty(value="nombreCompanhia")
	private String nombreCompanhia;

	/**
	 * Fecha inicio
	 */
	@JsonProperty(value="fechaInicio")
	private String fechaInicio;

	/**
	 * Fecha fin
	 */
	@JsonProperty(value="fechaFin")
	private String fechaFin;

	/**
	 * Método constructor de la clase banco
	 * <b>post: </b> Crea el banco con los valores que entran como parámetro
	 * @param nombreCompanhia - Id del banco
	 * @param fechaInicio- Número de la cuenta
	 * @param fechaFin - Saldo en la cuenta
	 */
	public ConsultaAs(@JsonProperty(value="nombreCompanhia") String nombreCompanhia,@JsonProperty(value="fechaInicio") String fechaInicio, @JsonProperty(value="fechaFin") String fechaFin) 
	{
		super();
		this.nombreCompanhia = nombreCompanhia;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}	
	
	//Getters y setters

	public String getNombreCompanhia() {
		return nombreCompanhia;
	}

	public void setNombreCompanhia(String nombreCompanhia) {
		this.nombreCompanhia = nombreCompanhia;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}	
}