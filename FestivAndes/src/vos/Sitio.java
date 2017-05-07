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
 * Clase que representa un Sitio
 */
public class Sitio 
{

	// Atributos

	/**
	 * Rol del usuario que envía este objeto
	 */
	@JsonProperty(value="idRol")
	private int idRol;

	@JsonProperty(value="idSitio")
	private int idSitio;

	@JsonProperty(value="abierto")
	private int abierto;


	@JsonProperty(value="capacidad")
	private int capacidad;

	@JsonProperty(value="aptoNecesidadesEsp")
	private int aptoNecesidadesEsp;

	@JsonProperty(value="horaDispInicio")
	private String horaDispInicio;

	@JsonProperty(value="horaDispFin")
	private String horaDispFin;

	@JsonProperty(value="condicionesSitio")
	private String descripcionTecnica;

	@JsonProperty(value="tipoSilleteria")
	private int tipoSilleteria;

	@JsonProperty(value="proteccionClima")
	private int proteccionClima;

	@JsonProperty(value="nombre")
	private String nombre;

	/**
	 * Método constructor de la clase video
	 * <b>post: </b> Crea el video con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duración en minutos del video.
	 */
	public Sitio(@JsonProperty(value="idRol")int idRol, @JsonProperty(value="abierto")int abierto, 
			@JsonProperty(value="capacidad") int capacidad, @JsonProperty(value="aptoNecesidadesEsp") int 
			aptoNecesidadesEsp, @JsonProperty(value="horaDispInicio") String horaDispInicio, 
			@JsonProperty(value="horaDispFin") String horaDispFin, @JsonProperty(value="condicionesSitio") String 
			descripcionTecnica, @JsonProperty(value="tipoSilleteria") int tipoSilleteria, 
			@JsonProperty(value="proteccionClima") int proteccionClima, @JsonProperty(value="nombre") String nombre)
	{
		super();
		this.idRol = idRol;
		this.abierto = abierto;
		this.capacidad = capacidad;
		this.aptoNecesidadesEsp = aptoNecesidadesEsp;
		this.horaDispInicio = horaDispInicio;
		this.horaDispFin = horaDispFin;
		this.descripcionTecnica = descripcionTecnica;
		this.tipoSilleteria = tipoSilleteria;
		this.proteccionClima = proteccionClima;
		this.nombre = nombre;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public int isAbierto() {
		return abierto;
	}

	public void setAbierto(int esAbierto) {
		this.abierto = esAbierto;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int isAptoNecesidadesEsp() {
		return aptoNecesidadesEsp;
	}

	public void setAptoNecesidadesEsp(int aptoNecesidadesEsp) {
		this.aptoNecesidadesEsp = aptoNecesidadesEsp;
	}

	public String getHoraDispInicio() {
		return horaDispInicio;
	}

	public void setHoraDispInicio(String horaDispInicio) {
		this.horaDispInicio = horaDispInicio;
	}

	public String getHoraDispFin() {
		return horaDispFin;
	}

	public void setHoraDispFin(String horaDispFin) {
		this.horaDispFin = horaDispFin;
	}

	public String getDescripcionTecnica() {
		return descripcionTecnica;
	}

	public void setDescripcionTecnica(String descripcionTecnica) {
		this.descripcionTecnica = descripcionTecnica;
	}

	public int getTipoSilleteria() {
		return tipoSilleteria;
	}

	public void setTipoSilleteria(int tipoSilleteria) {
		this.tipoSilleteria = tipoSilleteria;
	}

	public int isProteccionClima() {
		return proteccionClima;
	}

	public void setProteccionClima(int proteccionClima) {
		this.proteccionClima = proteccionClima;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdSitio() {
		return idSitio;
	}

	public void setIdSitio(int idSitio) {
		this.idSitio = idSitio;
	}

	public int getAbierto() {
		return abierto;
	}

	public int getAptoNecesidadesEsp() {
		return aptoNecesidadesEsp;
	}

	public int getProteccionClima() {
		return proteccionClima;
	}	
}
