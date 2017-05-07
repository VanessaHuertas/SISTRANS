/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (BogotÃ¡ - Colombia)
 * Departamento de IngenierÃ­a de Sistemas y ComputaciÃ³n
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
	 * Rol del usuario que envía este objeto
	 */
	@JsonProperty(value="idRol")
	private int idRol;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * DuraciÃ³n en minutos del espectaculo
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
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param idRol - Id del rol del usuario que envía el objeto.
	 * @param nombre - Nombre del espectáculo. nombre != null
	 * @param duracion - Duración del espectáculo.
	 * @param descripcion - Descripción del espectáculo. descripcion != null
	 * @param requerimientos - Requerimientos del espectáculo. requerimientos != null
	 * @param idioma - Si el espectaculo necesita traduccion o no. 
	 * @param costo - Se el espectáculo tiene costo o no.
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