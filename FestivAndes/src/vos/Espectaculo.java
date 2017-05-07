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
 * Clase que representa un Espectaculo
 */
public class Espectaculo 
{

	// Atributos

	/**
	 * Rol del usuario que env�a este objeto
	 */
	@JsonProperty(value="idRol")
	private int idRol;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="duracion")
	private int duracion;

	/**
	 * Idioma local o no del espectaculo
	 */
	@JsonProperty(value="idioma")
	private int idioma;

	/**
	 * Idioma del espectaculo
	 */
	@JsonProperty(value="descripcion")
	private String descripcion;

	/**
	 * Costo o no del espectaculo
	 */
	@JsonProperty(value="costo")
	private int costo;

	/**
	 * Requerimientos del espectaculo
	 */
	@JsonProperty(value="requerimientos")
	private String requerimientos;
	
	/**
	 * M�todo constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como par�metro
	 * @param idRol - Id del rol del usuario que env�a el objeto.
	 * @param nombre - Nombre del espect�culo. nombre != null
	 * @param duracion - Duraci�n del espect�culo.
	 * @param descripcion - Descripci�n del espect�culo. descripcion != null
	 * @param requerimientos - Requerimientos del espect�culo. requerimientos != null
	 * @param idioma - Si el espectaculo necesita traduccion o no. 
	 * @param costo - Se el espect�culo tiene costo o no.
	 */
	public Espectaculo(@JsonProperty(value="idRol")int idRol, @JsonProperty(value="nombre")String nombre,
			@JsonProperty(value="duracion") int duracion,@JsonProperty(value="idioma") int idioma, 
			@JsonProperty(value="costo")int costo, @JsonProperty(value="descripcion")String descripcion, 
			@JsonProperty(value="requerimientos")String requerimientos) 
	{
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.duracion = duracion;
		this.idioma = idioma;
		this.costo = costo;
		this.descripcion = descripcion;
		this.requerimientos = requerimientos;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdioma() {
		return idioma;
	}

	public String getRequerimientos() {
		return requerimientos;
	}

	public void setIdioma(int idioma) {
		this.idioma = idioma;
	}

	public void setRequerimientos(String requerimientos) {
		this.requerimientos = requerimientos;
	}	
}