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
 * Clase que representa un Tipo de silletería
 */
public class TipoSilleteria
{
	// Atributos

	/**
	 * Id del tipo
	 */
	@JsonProperty(value="idTipo")
	private int idTipo;

	/**
	 * Descripción del tipo
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * Método constructor de la clase tipoSilleteria
	 * <b>post: </b> Crea el tipo de silleteria con los valores que entran como parámetro
	 * @param idTipo - Id del tipo.
	 * @param descripcion - Descripción del tipo de silletería. descripcion != null
	 */
	public TipoSilleteria(@JsonProperty(value="idTipo")int idTipo, @JsonProperty(value="descripcion")String descripcion) 
	{
		super();
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}

	/**
	 * Método getter del atributo idTipo
	 * @return Identificador del tipo de silletería
	 */
	public int getIdTipo() 
	{
		return idTipo;
	}

	/**
	 * Método setter del atributo idTipo 
	 * <b>post: </b> El identificador del tipo de silletería sido cambiado con el valor que entra como parámetro
	 * @param idTipo - Identificador del tipo de silletería
	 */
	public void setIdTipo(int idTipo) 
	{
		this.idTipo = idTipo;
	}

	/**
	 * Método getter del atributo descripcion
	 * @return descripción del tipo de silleteria
	 */
	public String getDescripcion() 
	{
		return descripcion;
	}

	/**
	 * Método setter del atributo descripcion 
	 * <b>post: </b> La descripción del tipo de silletería ha sido cambiada con el valor que entra como parámetro
	 * @param descripcion - Descripción del tipo de silletería
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}