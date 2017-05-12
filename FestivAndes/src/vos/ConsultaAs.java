/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
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
	 * nombre comap�ia
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
	 * M�todo constructor de la clase banco
	 * <b>post: </b> Crea el banco con los valores que entran como par�metro
	 * @param nombreCompanhia - Id del banco
	 * @param fechaInicio- N�mero de la cuenta
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