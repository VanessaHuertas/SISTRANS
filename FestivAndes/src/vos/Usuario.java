/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogot√° - Colombia)
 * Departamento de Ingenier√≠a de Sistemas y Computaci√≥n
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: FestivAndes
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Usuario
 */
public class Usuario
{
	// Atributos

	/**
	 * Id del usuario
	 */
	@JsonProperty(value="idUsuario")
	private int idUsuario;

	/**
	 * Nombre del usuario
	 */
	@JsonProperty(value="nombreUsuario")
	private String nombreUsuario;

	/**
	 * Mail del usuario
	 */
	@JsonProperty(value="emailUsuario")
	private String emailUsuario;

	/**
	 * Identificador del rol del usuario
	 */
	@JsonProperty(value="idRol")
	private int idRol;

	/**
	 * Id del festival
	 */
	@JsonProperty(value="idFestival")
	private int idFestival;	

	/**
	 * Rol del usuario
	 */
	private Rol rol;

	/**
	 * MÈtodo constructor de la clase usuario
	 * <b>post: </b> Crea el usuario con los valores que entran como par·metro
	 * @param idUsuario - Id del usuario.
	 * @param nombreUsuario - Nombre del usuario. name != null
	 * @param emailUsuario - Correo electrÛnico del usuario. email != null
	 * @param idRol - Identificador del rol del usuario.
	 * @param idFestival - Identificador del festival al que va a asistir el usuario.
	 */	
	public Usuario(@JsonProperty(value="idUsuario")int idUsuario, @JsonProperty(value="nombreUsuario")String nombreUsuario,@JsonProperty(value="emailUsuario") String emailUsuario, @JsonProperty(value="idRol") int idRol, @JsonProperty(value="idFestival") int idFestival)
	{
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.emailUsuario = emailUsuario;
		this.idFestival = idFestival;
		this.idRol = idRol;
	}

	//Getters y Setters
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getIdFestival() {
		return idFestival;
	}

	public void setIdFestival(int idFestival) {
		this.idFestival = idFestival;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}	
}