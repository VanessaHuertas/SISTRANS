/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * -------------------------------------------------------------------
 */
package rest;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.Compannia;
import vos.ConsultaAs;
import vos.ConsultaFunciones;
import vos.ConsultaSitios;
import vos.Espectaculo;
import vos.FestivAndes;
import vos.Filtros;
import vos.Funcion;
import vos.PreferenciaCliente;
import vos.ReporteEspectaculo;
import vos.ReporteFuncion;
import vos.Respuesta;
import vos.Silla;
import vos.Sitio;
import vos.Usuario;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/FestivAndes/rest/festivales/...
 */
@Path("festivales")
public class FestivAndesFestivalesServices
{
	// Servicios REST tipo GET:


	/**
	 * Atributo que usa la anotación @Context para tener el ServletContext de la conexión actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	@GET
	@Path("sayHello")
	public String sayHello(@QueryParam("name")String name) {
	System.out.println("aasaaa");
		return "asa: "+name;

	}


	/**
	 * Método que expone servicio REST usando GET que da todos los festivales de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/festivales
	 * @return Json con todos los festivales de la base de datos O json con
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFestivales() 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ArrayList<FestivAndes> festivales;
		try
		{
			festivales = tm.darFestivales();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(festivales).build();
	}
	
	@GET
	@Path("/funciones")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarFunciones(ConsultaFunciones consulta) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ConsultaFunciones response;
		try 
		{
			response = tm.consultarFunciones(consulta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/sitios/{sitioId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarSitios(@javax.ws.rs.PathParam("sitioId") int sitioId) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ConsultaSitios response;
		try 
		{
			response = tm.consultarSitios(sitioId);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/cAsistenciaFest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarAsistenciaFest(ConsultaAs consulta) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Usuario response;
		try 
		{
			response = tm.consultarAsistenciaFest(consulta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(consulta).build();
	}
	
	@POST
	@Path("/cNAsistenciaFest")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarNoAsistenciaFest(ConsultaAs consulta) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Usuario response;
		try 
		{
			response = tm.consultarNoAsistenciaFest(consulta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(consulta).build();
	}
	
	@POST
	@Path("/cCompras")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarComprasBoletas(Filtros filtros) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Respuesta response;
		try 
		{
			response = tm.consultarComprasBoletas(filtros);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(filtros).build();
	}
	
	@POST
	@Path("/cBuenos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarBuenosClientes( ) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ArrayList<Usuario> response;
		try 
		{
			response = tm.consultarBuenosClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/funciones/{usuarioId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response consultarAsistencia(@javax.ws.rs.PathParam("usuarioId") int usuarioId) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ConsultaFunciones response;
		try 
		{
			response = tm.consultarAsistencia(usuarioId);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuarioId).build();
	}
	
	@POST
	@Path("/compannia/{idCompannia}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarCompannia(@javax.ws.rs.PathParam("idCompannia") int idCompannia) 
    {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.consultarCompannia(idCompannia);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(idCompannia).build();
	}
	
	/**
	 * M�todo que expone servicio REST usando GET que busca el espectaculo m�s popular
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/espectaculos/MasPopular
	 * @return Json con el/los espectaculos encontrados con el nombre que entra como par�metro o json con 
	 * el error que se produjo
	 */
	@GET
	@Path("/MasPopular")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getEspectaculoMasPopular() 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ArrayList<Espectaculo> espectaculos;
		try 
		{
			espectaculos = tm.espectaculosMasPopulares();
		} 
		catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(espectaculos).build();
	}	
	
	 /**
     * Método que expone servicio REST usando PUT que agrega el usuario que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/festivales/usuario
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/usuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUsuario(Usuario usuario)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addUsuario(usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}

	@PUT
	@Path("/cliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(Usuario usuario) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addCliente(usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}
	
	@PUT
	@Path("/preferenciaCliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPreferenciaCliente(PreferenciaCliente usuario)
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addPreferenciaCliente(usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(usuario).build();
	}

	@PUT
	@Path("/compannia")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCompannia(Compannia comp) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addCompannia(comp);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(comp).build();
	}

	@PUT
	@Path("/sitio")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSitio(Sitio sitio) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addSitio(sitio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(sitio).build();
	}

	@PUT
	@Path("/funcion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFuncion(Funcion funcion) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addFuncion(funcion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcion).build();
	}
	
	@POST
	@Path("/cancelarFuncion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelarFuncion(Funcion funcion) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.cancelarFuncion(funcion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcion).build();
	}
		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/compraAbonamiento/{userId}")
	public Response compraAbonamiento(List<Funcion> funciones, @javax.ws.rs.PathParam("userId") int userId) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.comprarAbonamiento(funciones, userId);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funciones).build();
	}
	
	@POST
	@Path("/devolverAbonamiento")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response devolverAbonamiento(List<Silla> sillas) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.devolverAbonamiento(sillas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(sillas).build();
	}
	
	@POST
	@Path("/compraSilla")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response compraSilla(Silla silla) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.comprarSilla(silla);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(silla).build();
	}
	
	@POST
	@Path("/devolverSilla")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response devolverSilla(Silla silla) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.devolverSilla(silla);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(silla).build();
	}
	
	@POST
	@Path("/compraSillas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response compraSillas(List<Silla> sillas) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.comprarSillas(sillas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(sillas).build();
	}
	
	@PUT
	@Path("/espectaculo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEspectaculo(Espectaculo espectaculo) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addEspectaculo(espectaculo);;
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(espectaculo).build();
	}

    @POST
	@Path("/realizarFuncion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarFuncionRealizada(Funcion funcion) 
    {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.registrarFuncionRelizada(funcion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcion).build();
	} 
    
    /**
	 * M�todo que expone servicio REST usando GET que genera un reporte de una funci�n de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/funciones/reporte/"id de la funci�n"
	 * @param idFuncion . Id de la funci�n
	 * @return Json con la funci�n de la base de datos O json con 
	 * el error que se produjo
	 */
	@GET
	@Path("/reporte/{idFuncion}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReporteFuncion(@javax.ws.rs.PathParam("idFuncion") int idFuncion) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ArrayList<ReporteFuncion> funciones;
		try 
		{
			funciones = tm.darReporteFunciones(idFuncion);
		} 
		catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funciones).build();
	}
	
	/**
	 * M�todo que expone servicio REST usando GET que genera el reporte del espectaculo con el nombre que entra como par�metro
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/espectaculos/reporte/"name para la busqueda"
	 * @param nombreEspectaculo - Nombre del espectaculo a buscar que entra en la URL como par�metro 
	 * @return Json con el/los espectaculos encontrados con el nombre que entra como par�metro o json con 
	 * el error que se produjo
	 */
	@GET
	@Path("/reporte/{nombreEspectaculo}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReporteEspectaculo(@javax.ws.rs.PathParam("nombreEspectaculo") String nombreEspectaculo) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ArrayList<ReporteEspectaculo> espectaculos;
		try 
		{
			if (nombreEspectaculo == null || nombreEspectaculo.length() == 0)
				throw new Exception("Nombre del espectaculo no valido");
			espectaculos = tm.generarReporteEspectaculo(nombreEspectaculo);
		}
		catch (Exception e)
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(espectaculos).build();
	}
}