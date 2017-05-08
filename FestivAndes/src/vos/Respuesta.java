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
	private int usuariosRegistrados;

	/**
	 * Método constructor de la clase banco
	 * <b>post: </b> Crea el banco con los valores que entran como parámetro
	 * @param idBanco - Id del banco
	 * @param numeroCuenta- Número de la cuenta
	 * @param saldoCuenta - Saldo en la cuenta
	 * @param idFestival - Identificador del festival 
	 */
	public Respuesta(@JsonProperty(value="nombreEspectaculo")String nombreEspectaculo,@JsonProperty(value="fechaFuncion") Date fechaFuncion, 
			@JsonProperty(value="sitioFuncion") String sitioFuncion, @JsonProperty(value="boletasVendidas") int boletasVendidas, 
			@JsonProperty(value="usuariosRegistrados") int usuariosRegistrados) 
	{
		super();
		this.nombreEspectaculo = nombreEspectaculo;
		this.fechaFuncion = fechaFuncion;
		this.sitioFuncion = sitioFuncion;
		this.boletasVendidas = boletasVendidas;
		this.usuariosRegistrados = usuariosRegistrados;
	}
	
	//Getters y setters

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}

	public Date getFechaFuncion() {
		return fechaFuncion;
	}

	public void setFechaFuncion(Date fechaFuncion) {
		this.fechaFuncion = fechaFuncion;
	}

	public String getSitioFuncion() {
		return sitioFuncion;
	}

	public void setSitioFuncion(String sitioFuncion) {
		this.sitioFuncion = sitioFuncion;
	}

	public int getBoletasVendidas() {
		return boletasVendidas;
	}

	public void setBoletasVendidas(int boletasVendidas) {
		this.boletasVendidas = boletasVendidas;
	}

	public int getUsuariosRegistrados() {
		return usuariosRegistrados;
	}

	public void setUsuariosRegistrados(int usuariosRegistrados) {
		this.usuariosRegistrados = usuariosRegistrados;
	}	
}