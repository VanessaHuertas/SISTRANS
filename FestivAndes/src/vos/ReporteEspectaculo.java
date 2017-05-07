package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ReporteEspectaculo
{
	@JsonProperty(value="nombreEspectaculo")
	private String nombreEspectaculo;
	
	@JsonProperty(value="boletasVendidas")
	private int boletasVendidas;
	
	@JsonProperty(value="producido")
	private double producido;

	public ReporteEspectaculo(@JsonProperty(value="nombreEspectaculo") String nombreEspectaculo, @JsonProperty(value="boletasVendidas") int boletasVendidas, @JsonProperty(value="producido") double producido)
	{
		super();
		this.nombreEspectaculo = nombreEspectaculo;
		this.boletasVendidas = boletasVendidas;
		this.producido = producido;
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

	public String getNombreEspectaculo() {
		return nombreEspectaculo;
	}

	public void setNombreEspectaculo(String nombreEspectaculo) {
		this.nombreEspectaculo = nombreEspectaculo;
	}			
}
