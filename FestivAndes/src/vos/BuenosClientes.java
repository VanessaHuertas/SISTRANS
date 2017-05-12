/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
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
	 * Número de boletas para cliente fiel
	 */
	@JsonProperty(value="numeroBoletas")
	private int numeroBoletas;

	/**
	 * Método constructor de la clase buenosClientes
	 * <b>post: </b> Crea el banco con los valores que entran como parámetro
	 * @param numeroBoletas - Número de boletas para cliente fiel
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