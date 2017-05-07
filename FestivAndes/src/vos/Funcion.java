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
 * Clase que representa una Funci�n
 */
public class Funcion 
{

	//// Atributos

	/**
	 * Rol del usuario que envía este objeto
	 */
	@JsonProperty(value="idRol")
	private int idRol;

	@JsonProperty(value="idFuncion")
	private int idFuncion;

	@JsonProperty(value="sitioId")
	private int sitioId;

	@JsonProperty(value="fechaRealizacion")
	private Date fechaRealizacion;

	@JsonProperty(value="espectaculoId")
	private String espectaculoId;

	@JsonProperty(value="realizada")
	private int realizada;

	/**
	 * M�todo constructor de la clase funci�n
	 * <b>post: </b> Crea la funci�n con los valores que entran como par�metro
	 * @param idRol - Id del rol del usuario que env�a la funci�n.
	 * @param idFuncion - Id de la funci�n
	 * @param sitioId - Id del sitio en el que se va a realizar la funci�n 
	 * @param fechaRealizacion - Fecha en la que se va a realizar la funci�n
	 * @param espectaculoId - Id del espectaculo al cu�l pertenece la funci�n
	 * @param realizada - Indica si la funci�n ya termin�.
	 */
	public Funcion(@JsonProperty(value="idRol")int idRol, @JsonProperty(value="idFuncion")int idFuncion,
			@JsonProperty(value="sitioId") int sitioId, @JsonProperty(value="fechaRealizacion") 
			Date fechaRealizacion, @JsonProperty(value="espectaculoId") String espectaculoId, @JsonProperty(value="realizada") int realizada) 
	{
		super();
		this.idRol = idRol;
		this.idFuncion = idFuncion;
		this.sitioId = sitioId;
		this.fechaRealizacion = fechaRealizacion;
		this.espectaculoId = espectaculoId;
		this.realizada = realizada;
	}

	
	//Getter y Setters
	
	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int getSitioId() {
		return sitioId;
	}
	

	public int getIdFuncion() {
		return idFuncion;
	}


	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}


	public int getRealizada() {
		return realizada;
	}


	public void setSitioId(int sitioId) {
		this.sitioId = sitioId;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public String getEspectaculoId() {
		return espectaculoId;
	}

	public void setEspectaculoId(String espectaculoId) {
		this.espectaculoId = espectaculoId;
	}

	public int isRealizada() {
		return realizada;
	}

	public void setRealizada(int realizada) {
		this.realizada = realizada;
	}
}
