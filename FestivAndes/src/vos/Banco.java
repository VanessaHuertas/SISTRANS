/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 *
 * Materia: Sistemas Transaccionales
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa una Banco
 */
public class Banco
{
	// Atributos

	/**
	 * Id del banco
	 */
	@JsonProperty(value="idBanco")
	private int idBanco;

	/**
	 * N�mero de la cuenta 
	 */
	@JsonProperty(value="numeroCuenta")
	private int numeroCuenta;

	/**
	 * Saldo de la cuenta
	 */
	@JsonProperty(value="saldoCuenta")
	private double saldoCuenta;

	/**
	 * Id del festival
	 */
	@JsonProperty(value="idFestival")
	private int idFestival;

	/**
	 * M�todo constructor de la clase banco
	 * <b>post: </b> Crea el banco con los valores que entran como par�metro
	 * @param idBanco - Id del banco
	 * @param numeroCuenta- N�mero de la cuenta
	 * @param saldoCuenta - Saldo en la cuenta
	 * @param idFestival - Identificador del festival 
	 */
	public Banco(@JsonProperty(value="idBanco")int idBanco,@JsonProperty(value="numeroCuenta") int numeroCuenta, @JsonProperty(value="saldoCuenta") double saldoCuenta, @JsonProperty(value="idFestival") int idFestival) 
	{
		super();
		this.idBanco = idBanco;
		this.numeroCuenta= numeroCuenta;
		this.saldoCuenta = saldoCuenta;
		this.idFestival = idFestival;
	}

	
	//Getters y setters
	
	public int getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public int getIdFestival() {
		return idFestival;
	}

	public void setIdFestival(int idFestival) {
		this.idFestival = idFestival;
	}		
}