package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReporteFuncion
{
	@JsonProperty(value="idFuncion")
	private int idFuncion;
	
	@JsonProperty(value="boletasVendidas")
	private int boletasVendidas;
	
	@JsonProperty(value="producido")
	private double producido;

	public ReporteFuncion(@JsonProperty(value="idFuncion") int idFuncion, @JsonProperty(value="boletasVendidas") int boletasVendidas, @JsonProperty(value="producido") double producido)
	{
		super();
		this.idFuncion = idFuncion;
		this.boletasVendidas = boletasVendidas;
		this.producido = producido;
	}

	public int getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	public int getBoletasVendidas() {
		return boletasVendidas;
	}

	public void setBoletasVendidas(int boletasVendidas) {
		this.boletasVendidas = boletasVendidas;
	}

	public double getProducido() {
		return producido;
	}

	public void setProducido(double producido) {
		this.producido = producido;
	}		
}
