/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: FestivAndes
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Festival
 */
public class FestivAndes
{

	//// Atributos

	/**
	 * Id del festival
	 */
	@JsonProperty(value="idFestival")
	private int idFestival;

	/**
	 * D�a del festival
	 */
	@JsonProperty(value="dia")
	private String dia;

	/**
	 * Hora inicio del festival
	 */
	@JsonProperty(value="horaInicio")
	private String horaInicio;

	/**
	 * Hora fin del festival
	 */
	@JsonProperty(value="horaFin")
	private String horaFin;

	/**
	 * M�todo constructor de la clase FestivAndes
	 * <b>post: </b> Crea el festival con los valores que entran como par�metro
	 * @param idFestival - Id del festival.
	 * @param dia - D�a del festival. dia != null
	 * @param horaInicio - Hora inicio del festival. horaInicio != null
	 * @param horaFin - Hora fin del festival. horaFin != null
	 */
	public FestivAndes(@JsonProperty(value="idFestival")int idFestival, @JsonProperty(value="dia")String dia,@JsonProperty(value="horaInicio") String horaInicio, @JsonProperty(value="horaFin") String horaFin)
	{
		super();
		this.idFestival = idFestival;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	//Getters y setters
	
	public int getIdFestival() 
	{
		return idFestival;
	}

	public void setIdFestival(int idFestival) 
	{
		this.idFestival = idFestival;
	}

	public String getDia() 
	{
		return dia;
	}

	public void setDia(String dia) 
	{
		this.dia = dia;
	}

	public String getHoraInicio() 
	{
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) 
	{
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() 
	{
		return horaFin;
	}

	public void setHoraFin(String horaFin)
	{
		this.horaFin = horaFin;
	}	
}
