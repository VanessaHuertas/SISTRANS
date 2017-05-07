/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: FestivAndes
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Rol
 */
public class Rol 
{

	// Atributos

	/**
	 * Id del rol
	 */
	@JsonProperty(value="idRol")
	private int idRol;

	/**
	 * Descripci�n del rol
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * M�todo constructor de la clase rol
	 * <b>post: </b> Crea el rol con los valores que entran como par�metro
	 * @param id - Id del rol.
	 * @param descripcion - Descripci�n del rol. descripcion != null
	 */
	public Rol(@JsonProperty(value="idRol")int idRol, @JsonProperty(value="descripcion")String descripcion)
	{
		super();
		this.idRol = idRol;
		this.descripcion = descripcion;
	}

	//Getters y Setters
	
	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}
