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
 * Clase que representa una Localidad
 */
public class Localidad
{
	// Atributos

	/**
	 * Id de la localidad
	 */
	@JsonProperty(value="idLocalidad")
	private int idLocalidad;

	/**
	 * Descripcion del tipo de la localidad
	 */
	@JsonProperty(value="descripcionLocalidad")
	private String descripcionLocalidad;

	/**
	 * Localidad numerada
	 */
	@JsonProperty(value="numerada")
	private int numerada;

	/**
	 * M�todo constructor de la clase Localidad
	 * <b>post: </b> Crea la localidad con los valores que entran como par�metro
	 * @param idLocalidad - Id de la localidad.
	 * @param descripcionLocalidad - Descripcion del tipo de la localidad.
	 * @param numerada - Localidad numerada, 1 si lo est�, 0 de lo congtrario.
	 */
	public Localidad(@JsonProperty(value="idLocalidad")int idLocalidad, @JsonProperty(value="descripcionLocalidad") String descripcionLocalidad, 
			@JsonProperty(value="numerada") int numerada) 
	{
		super();
		this.idLocalidad = idLocalidad;
		this.descripcionLocalidad = descripcionLocalidad;
		this.numerada = numerada;
	}

	/**
	 * M�todo getter del atributo idLocalidad
	 * @return Identificador de la localidad
	 */
	public int getIdLocalidad() 
	{
		return idLocalidad;
	}

	/**
	 * M�todo setter del atributo idLocalidad
	 * <b>post: </b> El identificador de la localidad ha sido cambiado con el valor que entra como par�metro
	 * @param idLocalidad - Identificador de la localidad
	 */
	public void setIdLocalidad(int idLocalidad) 
	{
		this.idLocalidad = idLocalidad;
	}

	/**
	 * M�todo getter del atributo numerada
	 * @return 1 si la localidad est� numerada, 0 de lo contrario
	 */
	public int getNumerada() 
	{
		return numerada;
	}

	/**
	 * M�todo setter del atributo numerada 
	 * <b>post: </b> La localidad se cambia de numerada a no numerada o viceversa de acuerdo al valor que entra como par�metro
	 * Puede que quede igual
	 * @param numerada - 1 si la localidad est� numerada, 0 de lo contrario
	 */
	public void setNumerada(int numerada)
	{
		this.numerada = numerada;
	}	
	
	//M�todos que modifican y retornan los atributos

	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}

	public void setDescripcionLocalidad(String descripcionLocalidad) {
		this.descripcionLocalidad = descripcionLocalidad;
	}
}