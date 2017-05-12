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
 * Clase que representa la cantidad de boletas para cliente fiel
 */
public class BuenosClientes
{
	// Atributos

	/**
	 * N�mero de boletas para cliente fiel
	 */
	@JsonProperty(value="numeroBoletas")
	private int numeroBoletas;

	/**
	 * M�todo constructor de la clase buenosClientes
	 * <b>post: </b> Crea el banco con los valores que entran como par�metro
	 * @param numeroBoletas - N�mero de boletas para cliente fiel
	 */
	public BuenosClientes(@JsonProperty(value="numeroBoletas")int numeroBoletas) 
	{
		super();
		this.numeroBoletas = numeroBoletas;
	}
	
	//Getters y setters

	public int getNumeroBoletas() {
		return numeroBoletas;
	}

	public void setNumeroBoletas(int numeroBoletas) {
		this.numeroBoletas = numeroBoletas;
	}		
}