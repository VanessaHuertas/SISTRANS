/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 *
 * Materia: Sistemas Transaccionales
 * -------------------------------------------------------------------
 */
package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa una Preferencia d eun cliente
 */
public class PreferenciaCliente
{
	// Atributos

	/**
	 * Id de la preferencia
	 */
	@JsonProperty(value="idPreferencia")
	private int idPreferencia;

	/**
	 * Tipo de la preferencia 
	 */
	@JsonProperty(value="tipoPreferencia")
	private String tipoPreferencia;

	/**
	 * Valor de la preferencia
	 */
	@JsonProperty(value="valor")
	private String valor;

	/**
	 * Id del usuario que tiene esta preferencia
	 */
	@JsonProperty(value="idUsuario")
	private int idUsuario;

	/**
	 * M�todo constructor de la clase preferenciaCliente
	 * <b>post: </b> Crea la preferencia con los valores que entran como par�metro
	 * @param idPreferencia - Id de la preferencia
	 * @param tipoPreferencia- Tipo de la preferencia 
	 * @param valor - Valor de la preferencia
	 * @param idUsuario - Id del usuario que tiene esta preferencia
	 */
	public PreferenciaCliente(@JsonProperty(value="idPreferencia")int idPreferencia,@JsonProperty(value="tipoPreferencia") String tipoPreferencia,
			@JsonProperty(value="valor") String valor, @JsonProperty(value="idUsuario") int idUsuario) 
	{
		super();
		this.idPreferencia = idPreferencia;
		this.tipoPreferencia = tipoPreferencia;
		this.valor = valor;
		this.idUsuario = idUsuario;
	}

	//Getters y setters

	public int getIdPreferencia() {
		return idPreferencia;
	}

	public String getTipoPreferencia() {
		return tipoPreferencia;
	}

	public String getValor() {
		return valor;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdPreferencia(int idPreferencia) {
		this.idPreferencia = idPreferencia;
	}

	public void setTipoPreferencia(String tipoPreferencia) {
		this.tipoPreferencia = tipoPreferencia;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}	
}