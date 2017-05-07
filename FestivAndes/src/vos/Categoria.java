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
	 * Descripción de la categoria
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * Nombre del espectaculo al cual categoriza la categoría
	 */
	@JsonProperty(value="nombreEspectaculo")
	private String nombreEspectaculo;

	/**
	 * Método constructor de la clase categoría
	 * <b>post: </b> Crea la categoria con los valores que entran como parámetro
	 * @param idCategoria - Id de la categoría.
	 * @param descripcion - Descripción de la categoría. descripcion != null
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