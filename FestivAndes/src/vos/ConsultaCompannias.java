package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaCompannias
{
	@JsonProperty(value="asistencia")
	private int asistenciaTotal;

	@JsonProperty(value="clientesRegistrados")
	private int asistenciaClientesRegistrados;

	@JsonProperty(value="dinero")
	private double dineroGenerado;

	@JsonProperty(value="ocupacion")
	private double porcentajeOcupacionSitio;

	public ConsultaCompannias(@JsonProperty(value="asistencia")int asistenciaTotal, @JsonProperty(value="clientesRegistrados")int asistenciaClientesRegistrados, @JsonProperty(value="dinero")double dineroGenerado,
			@JsonProperty(value="ocupacion") double porcentajeOcupacionSitio) {
		super();
		this.asistenciaTotal = asistenciaTotal;
		this.asistenciaClientesRegistrados = asistenciaClientesRegistrados;
		this.dineroGenerado = dineroGenerado;
		this.porcentajeOcupacionSitio = porcentajeOcupacionSitio;
	}

	public int getAsistenciaTotal() {
		return asistenciaTotal;
	}

	public void setAsistenciaTotal(int asistenciaTotal) {
		this.asistenciaTotal = asistenciaTotal;
	}

	public int getAsistenciaClientesRegistrados() {
		return asistenciaClientesRegistrados;
	}

	public void setAsistenciaClientesRegistrados(int asistenciaClientesRegistrados) {
		this.asistenciaClientesRegistrados = asistenciaClientesRegistrados;
	}

	public double getDineroGenerado() {
		return dineroGenerado;
	}

	public void setDineroGenerado(double dineroGenerado) {
		this.dineroGenerado = dineroGenerado;
	}

	public double getPorcentajeOcupacionSitio() {
		return porcentajeOcupacionSitio;
	}

	public void setPorcentajeOcupacionSitio(double porcentajeOcupacionSitio) {
		this.porcentajeOcupacionSitio = porcentajeOcupacionSitio;
	}	
}
