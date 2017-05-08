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
	private Date fechaInicio;

	/**
	 * Fecha fin
	 */
	@JsonProperty(value="fechaFin")
	private Date fechaFin;

	/**
	 * Método constructor de la clase banco
	 * <b>post: </b> Crea el banco con los valores que entran como parámetro
	 * @param nombreCompanhia - Id del banco
	 * @param fechaInicio- Número de la cuenta
	 * @param fechaFin - Saldo en la cuenta
	 */
	public ConsultaAs(@JsonProperty(value="nombreCompanhia") String nombreCompanhia,@JsonProperty(value="fechaInicio") Date fechaInicio, @JsonProperty(value="fechaFin") Date fechaFin) 
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
}