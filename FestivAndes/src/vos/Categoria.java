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
 * Clase que representa una Categoria
 */
public class Categoria
{
	// Atributos

	/**
	 * Id de la categoria
	 */
	@JsonProperty(value="idCategoria")
	private int idCategoria;

	/**
	 * Descripci�n de la categoria
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * Nombre del espectaculo al cual categoriza la categor�a
	 */
	@JsonProperty(value="nombreEspectaculo")
	private String nombreEspectaculo;

	/**
	 * M�todo constructor de la clase categor�a
	 * <b>post: </b> Crea la categoria con los valores que entran como par�metro
	 * @param idCategoria - Id de la categor�a.
	 * @param descripcion - Descripci�n de la categor�a. descripcion != null
	 */
	public Categoria(@JsonProperty(value="idCategoria")int idCategoria,@JsonProperty(value="descripcion") String 
			descripcion, @JsonProperty(value="nombreEspectaculo") String nombreEspectaculo) 
	{
		super();
		this.idCategoria = idCategoria;
		this.descripcion = descripcion;
		this.nombreEspectaculo = nombreEspectaculo;
	}
	
	//Getters y setters

	public int getIdCategoria() {
		return idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}	
}