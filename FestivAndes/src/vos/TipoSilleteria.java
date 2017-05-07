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
 * Clase que representa un Tipo de silleter�a
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
	 * Descripci�n del tipo
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * M�todo constructor de la clase tipoSilleteria
	 * <b>post: </b> Crea el tipo de silleteria con los valores que entran como par�metro
	 * @param idTipo - Id del tipo.
	 * @param descripcion - Descripci�n del tipo de silleter�a. descripcion != null
	 */
	public TipoSilleteria(@JsonProperty(value="idTipo")int idTipo, @JsonProperty(value="descripcion")String descripcion) 
	{
		super();
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}

	/**
	 * M�todo getter del atributo idTipo
	 * @return Identificador del tipo de silleter�a
	 */
	public int getIdTipo() 
	{
		return idTipo;
	}

	/**
	 * M�todo setter del atributo idTipo 
	 * <b>post: </b> El identificador del tipo de silleter�a sido cambiado con el valor que entra como par�metro
	 * @param idTipo - Identificador del tipo de silleter�a
	 */
	public void setIdTipo(int idTipo) 
	{
		this.idTipo = idTipo;
	}

	/**
	 * M�todo getter del atributo descripcion
	 * @return descripci�n del tipo de silleteria
	 */
	public String getDescripcion() 
	{
		return descripcion;
	}

	/**
	 * M�todo setter del atributo descripcion 
	 * <b>post: </b> La descripci�n del tipo de silleter�a ha sido cambiada con el valor que entra como par�metro
	 * @param descripcion - Descripci�n del tipo de silleter�a
	 */
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}