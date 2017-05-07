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

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un Video
 * @author Juan
 */
public class ConsultaSitios {

	//// Atributos


	@JsonProperty(value="abierto")
	private boolean abierto;


	@JsonProperty(value="capacidad")
	private int capacidad;

	@JsonProperty(value="aptoNecesidadesEsp")
	private boolean aptoNecesidadesEsp;


	@JsonProperty(value="funciones")
	private List<Funcion> funciones;

	@JsonProperty(value="sillas")
	private List<Silla> sillas;

	@JsonProperty(value="descripcionTecnica")
	private String descripcionTecnica;

	@JsonProperty(value="tipoSilleteria")
	private String tipoSilleteria;

	@JsonProperty(value="proteccionClima")
	private boolean proteccionClima;

	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Método constructor de la clase video
	 * <b>post: </b> Crea el video con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duración en minutos del video.
	 */
	public ConsultaSitios(@JsonProperty(value="abierto")boolean abierto,@JsonProperty(value="capacidad") int capacidad,
			@JsonProperty(value="aptoNecesidadesEsp") boolean aptoNecesidadesEsp, @JsonProperty(value="funciones") List<Funcion> funciones,
			@JsonProperty(value="sillas") List<Silla> sillas, @JsonProperty(value="descripcionTecnica") String descripcionTecnica,
			@JsonProperty(value="tipoSilleteria") String tipoSilleteria, @JsonProperty(value="proteccionClima") boolean proteccionClima,
			@JsonProperty(value="nombre") String nombre) {
		super();
		this.abierto = abierto;
		this.capacidad = capacidad;
		this.aptoNecesidadesEsp = aptoNecesidadesEsp;
		this.funciones = funciones;
		this.sillas = sillas;
		this.descripcionTecnica = descripcionTecnica;
		this.tipoSilleteria = tipoSilleteria;
		this.proteccionClima = proteccionClima;
		this.nombre = nombre;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean esAbierto) {
		this.abierto = esAbierto;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isAptoNecesidadesEsp() {
		return aptoNecesidadesEsp;
	}

	public void setAptoNecesidadesEsp(boolean aptoNecesidadesEsp) {
		this.aptoNecesidadesEsp = aptoNecesidadesEsp;
	}

	public String getDescripcionTecnica() {
		return descripcionTecnica;
	}

	public void setDescripcionTecnica(String descripcionTecnica) {
		this.descripcionTecnica = descripcionTecnica;
	}

	public String getTipoSilleteria() {
		return tipoSilleteria;
	}

	public void setTipoSilleteria(String tipoSilleteria) {
		this.tipoSilleteria = tipoSilleteria;
	}

	public boolean isProteccionClima() {
		return proteccionClima;
	}

	public void setProteccionClima(boolean proteccionClima) {
		this.proteccionClima = proteccionClima;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	/**
	 * @return the sillas
	 */
	public List<Silla> getSillas() {
		return sillas;
	}

	/**
	 * @param sillas the sillas to set
	 */
	public void setSillas(List<Silla> sillas) {
		this.sillas = sillas;
	}


}
