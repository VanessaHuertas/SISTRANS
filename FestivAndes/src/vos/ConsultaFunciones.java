/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * -------------------------------------------------------------------
 */
package vos;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Video
 * @author Juan
 */
public class ConsultaFunciones {

	//// Atributos

	@JsonProperty(value="sitioId")
	private int sitioId;

	@JsonProperty(value="fechaRealizacion1")
	private Date fechaRealizacion1;
	
	@JsonProperty(value="fechaRealizacion2")
	private Date fechaRealizacion2;

	@JsonProperty(value="espectaculoId")
	private int espectaculoId;

	@JsonProperty(value="companniaId")
	private int companniaId;
	
	@JsonProperty(value="idiomaObra")
	private String idiomaObra;
	
	@JsonProperty(value="generoId")
	private int generoId;
	
	@JsonProperty(value="funciones")
	private List<Funcion> funciones;

	/**
	 * Método constructor de la clase video
	 * <b>post: </b> Crea el video con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duración en minutos del video.
	 */
	public ConsultaFunciones(@JsonProperty(value="sitioId")int sitioId,@JsonProperty(value="fechaRealizacion1") Date fechaRealizacion1,
			@JsonProperty(value="fechaRealizacion2") Date fechaRealizacion2, @JsonProperty(value="espectaculoId") int espectaculoId,
			@JsonProperty(value="companniaId") int companniaId, @JsonProperty(value="idiomaObra") String idiomaObra, @JsonProperty(value="generoId") int generoId) {
		super();
		this.sitioId = sitioId;
		this.fechaRealizacion1 = fechaRealizacion1;
		this.fechaRealizacion2 = fechaRealizacion2;
		this.espectaculoId = espectaculoId;
		this.companniaId = companniaId;
		this.idiomaObra = idiomaObra;
		this.generoId = generoId;
	}

	/**
	 * @return the sitioId
	 */
	public int getSitioId() {
		return sitioId;
	}

	/**
	 * @param sitioId the sitioId to set
	 */
	public void setSitioId(int sitioId) {
		this.sitioId = sitioId;
	}

	/**
	 * @return the fechaRealizacion1
	 */
	public Date getFechaRealizacion1() {
		return fechaRealizacion1;
	}

	/**
	 * @param fechaRealizacion1 the fechaRealizacion1 to set
	 */
	public void setFechaRealizacion1(Date fechaRealizacion1) {
		this.fechaRealizacion1 = fechaRealizacion1;
	}

	/**
	 * @return the fechaRealizacion2
	 */
	public Date getFechaRealizacion2() {
		return fechaRealizacion2;
	}

	/**
	 * @param fechaRealizacion2 the fechaRealizacion2 to set
	 */
	public void setFechaRealizacion2(Date fechaRealizacion2) {
		this.fechaRealizacion2 = fechaRealizacion2;
	}

	/**
	 * @return the espectaculoId
	 */
	public int getEspectaculoId() {
		return espectaculoId;
	}

	/**
	 * @param espectaculoId the espectaculoId to set
	 */
	public void setEspectaculoId(int espectaculoId) {
		this.espectaculoId = espectaculoId;
	}

	/**
	 * @return the companniaId
	 */
	public int getCompanniaId() {
		return companniaId;
	}

	/**
	 * @param companniaId the companniaId to set
	 */
	public void setCompanniaId(int companniaId) {
		this.companniaId = companniaId;
	}

	/**
	 * @return the idiomaObra
	 */
	public String getIdiomaObra() {
		return idiomaObra;
	}

	/**
	 * @param idiomaObra the idiomaObra to set
	 */
	public void setIdiomaObra(String idiomaObra) {
		this.idiomaObra = idiomaObra;
	}

	/**
	 * @return the generoId
	 */
	public int getGeneroId() {
		return generoId;
	}

	/**
	 * @param generoId the generoId to set
	 */
	public void setGeneroId(int generoId) {
		this.generoId = generoId;
	}

	/**
	 * @return the funciones
	 */
	public List<Funcion> getFunciones() {
		return funciones;
	}

	/**
	 * @param funciones the funciones to set
	 */
	public void setFunciones(List<Funcion> funciones) {
		this.funciones = funciones;
	}

}
