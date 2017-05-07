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

import java.util.Date;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa una Silla
 */
public class Silla 
{

	//// Atributos

	/**
	 * El usuario que compra la boleta, null si no está registrado
	 */
	@JsonProperty(value="userId")
	private int userId;

	@JsonProperty(value="idSilla")
	private int idSilla;

	@JsonProperty(value="precio")
	private int precio;

	@JsonProperty(value="disponible")
	private int disponible;

	@JsonProperty(value="devolucion")
	private int devolucion;

	@JsonProperty(value="asistio")
	private int asistio;
	
	@JsonProperty(value="fechaRealizacion")
	private Date fechaRealizacion;

	@JsonProperty(value="funcionId")
	private int funcionId;

	@JsonProperty(value="localidadId")
	private int localidadId;

	/**
	 * Método constructor de la clase video
	 * <b>post: </b> Crea el video con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duración en minutos del video.
	 */
	public Silla(@JsonProperty(value="userId")int userId, @JsonProperty(value="idSilla")int idSilla,
			@JsonProperty(value="precio") int precio, @JsonProperty(value="devolucion") int devolucion, 
			@JsonProperty(value="disponible") int disponible,@JsonProperty(value="asistio")int asistio,
			@JsonProperty(value="fechaRealizacion") Date fechaRealizacion, @JsonProperty(value="funcionId") int funcionId, 
			@JsonProperty(value="localidadId") int localidadId) 
	{
		super();
		this.userId = userId;
		this.idSilla = idSilla;
		this.precio = precio;
		this.disponible = disponible;
		this.asistio = asistio;
		this.fechaRealizacion = fechaRealizacion;
		this.devolucion = devolucion;
		this.funcionId = funcionId;
		this.localidadId = localidadId;
	}
	
	public Silla(){
		super();
	}

	//Getters y Setters
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIdSilla() {
		return idSilla;
	}

	public int getPrecio() {
		return precio;
	}

	public int getDisponible() {
		return disponible;
	}

	public int getDevolucion() {
		return devolucion;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public int getFuncionId() {
		return funcionId;
	}

	public int getLocalidadId() {
		return localidadId;
	}

	public void setIdSilla(int idSilla) {
		this.idSilla = idSilla;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public void setDevolucion(int devolucion) {
		this.devolucion = devolucion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public void setFuncionId(int funcionId) {
		this.funcionId = funcionId;
	}

	public void setLocalidadId(int localidadId) {
		this.localidadId = localidadId;
	}

	public int getAsistio() {
		return asistio;
	}

	public void setAsistio(int asistio) {
		this.asistio = asistio;
	}		
}