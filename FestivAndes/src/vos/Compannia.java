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

import java.sql.Date;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Video
 * @author Juan
 */
public class Compannia 
{
	//// Atributos

	/**
	 * Rol del usuario que env�a este objeto
	 */
	@JsonProperty(value="idRol")
	private int idRol;
	
	/**
	 * Identificador del usuario que env�a este objeto
	 */
	@JsonProperty(value="idUsuario")
	private String idUsuario;
	
	/**
	 * Nombre de la Compa��a
	 */
	@JsonProperty(value="nombre")
	private String nombre;
	
	/**
	 * Nombre del representante de la compañía
	 */
	@JsonProperty(value="nombreRepresentante")
	private String nombreRepresentante;

	/**
	 * País de origen
	 */
	@JsonProperty(value="paisOrigen")
	private String paisOrigen;
	
	/**
	 * Pagina web de la compañía
	 */
	@JsonProperty(value="paginaWeb")
	private int paginaWeb;
	
	/**
	 * Fecha de llegada al festival
	 */
	@JsonProperty(value="fechaLlegada")
	private Date fechaLlegada;
	
	/**
	 * Fecha de partida del festival
	 */
	@JsonProperty(value="fechaPartida")
	private Date fechaPartida;

	/**
	 * M�todo constructor de la clase CompanhiaTeatro
	 * <b>post: </b> Crea la compa��a con los valores que entran como par�metro
	 * @param idRol - Rol del usuario que env�a este objeto
	 * @param idUsuario - Identificador del usuario que env�a este objeto
	 * @param nombre - Nombre de la compa��a. nombre != null
	 * @param nombreRepresentante - Nombre del representante de la compa��a. nombreRepresentante != null
	 * @param paisOrigen - Pa�s de origen de la compa��a. paisOrigen != null
	 * @param paginaWeb - P�gina web de la compa��a. paginaWeb != null
	 * @param fechaLlegada - Fecha llegada de una companhia a un sitio
	 * @param fechaPartida - Fecha partida de una companhia a un sitio
	 */
	public Compannia(@JsonProperty(value="idRol")int idRol, @JsonProperty(value="idUsuario")String idUsuario, 
			@JsonProperty(value="nombre")String nombre,@JsonProperty(value="nombreRepresentante") String nombreRepresentante,
			@JsonProperty(value="paisOrigen") String paisOrigen, @JsonProperty(value="paginaWeb") int paginaWeb,
			@JsonProperty(value="fechaLlegada") Date fechaLlegada, @JsonProperty(value="fechaPartida") Date fechaPartida) {
		super();
		this.idRol = idRol;
		this.nombre = nombre;
		this.nombreRepresentante = nombreRepresentante;
		this.paisOrigen = paisOrigen;
		this.paginaWeb = paginaWeb;
		this.fechaLlegada = fechaLlegada;
		this.fechaPartida = fechaPartida;
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the idRol
	 */
	public int getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the nombreRepresentante
	 */
	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	/**
	 * @param nombreRepresentante the nombreRepresentante to set
	 */
	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	/**
	 * @return the paisOrigen
	 */
	public String getPaisOrigen() {
		return paisOrigen;
	}

	/**
	 * @param pais the paisOrigen to set
	 */
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	/**
	 * @return the fechaLlegada
	 */
	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	/**
	 * @param fechaLlegada the fechaLlegada to set
	 */
	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	/**
	 * @return the fechaPartida
	 */
	public Date getFechaPartida() {
		return fechaPartida;
	}

	/**
	 * @param fechaPartida the fechaPartida to set
	 */
	public void setFechaPartida(Date fechaPartida) {
		this.fechaPartida = fechaPartida;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getPaginaWeb() {
		return paginaWeb;
	}

	public void setPaginaWeb(int paginaWeb) {
		this.paginaWeb = paginaWeb;
	}	
}
