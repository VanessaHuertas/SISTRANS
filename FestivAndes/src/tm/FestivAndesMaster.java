/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dao.DAOTablaCompannias;
import dao.DAOTablaEspectaculos;
import dao.DAOTablaFestivAndes;
import dao.DAOTablaFunciones;
import dao.DAOTablaPreferenciaClientes;
import dao.DAOTablaSillas;
import dao.DAOTablaSitios;
import dao.DAOTablaUsuarios;
import vos.Compannia;
import vos.ConsultaAs;
import vos.ConsultaCompannias;
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
 * Fachada en patron singleton de la aplicaci�n
 */
public class FestivAndesMaster 
{

	/**
	 * Atributo estático que contiene el path relativo del archivo que tiene los datos de la conexión
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estático que contiene el path absoluto del archivo que tiene los datos de la conexión
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Conexión a la base de datos
	 */
	private Connection conn;


	/**
	 * Método constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las
	 * transacciones y la logia de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesMaster, se inicializa el path absoluto de el archivo de conexión y se
	 * inicializa los atributos que se usan par la conexión a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public FestivAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/*
	 * Método que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexión a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que  retorna la conexión a la base de datos
	 * @return Connection - la conexión a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexión a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	//-----------------------------------------------------------------------
	//Transacciones
	//-----------------------------------------------------------------------

	/**
	 * M�todo que modela la transacci�n que retorna todos los usuarios de la base de datos.
	 * @return ListaUsuarios - objeto que modela  un arreglo de usuarios. este arreglo contiene el resultado de la b�squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public ArrayList<FestivAndes> darFestivales() throws Exception 
	{
		ArrayList<FestivAndes> festivales;
		DAOTablaFestivAndes daoTablaFestivales = new DAOTablaFestivAndes();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoTablaFestivales.setConn(conn);
			festivales = daoTablaFestivales.darFestivales();
		}
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try
			{
				daoTablaFestivales.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return festivales;
	}
	
	/**
	 * M�todo que modela la transacci�n que retorna todos los usuarios de la base de datos.
	 * @return ListaUsuarios - objeto que modela  un arreglo de usuarios. este arreglo contiene el resultado de la b�squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public ArrayList<Usuario> darUsuarios() throws Exception 
	{
		ArrayList<Usuario> usuarios;
		DAOTablaUsuarios daoTablaUsuarios = new DAOTablaUsuarios();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoTablaUsuarios.setConn(conn);
			usuarios = daoTablaUsuarios.darUsuarios();
		}
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		finally 
		{
			try
			{
				daoTablaUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
	}

	/**
	 * M�todo que modela la transacci�n que busca el/los usuarios en la base de datos con el id que entra como par�metro.
	 * @param id - Identificador del usuario a buscar. name != null
	 * @return ArrayList<Usuario> - objeto que modela  un arreglo de usuarios. este arreglo contiene el resultado de la b�squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public ArrayList<Usuario> buscarUsuariosPorId(int id) throws Exception 
	{
		ArrayList<Usuario> usuarios;
		DAOTablaUsuarios daoUsuario = new DAOTablaUsuarios();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoUsuario.setConn(conn);
			usuarios = daoUsuario.buscarUsuariosPorId(id);

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return usuarios;
	}

	/**
	 * M�todo que modela la transacci�n que agrega un solo usuario a la base de datos.
	 * <b> post: </b> se ha agregado el usuario que entra como par�metro
	 * @param usuario - el usuario a agregar. usuario != null
	 * @throws Exception - cualquier error que se genera agregando el usuario
	 */
	public void addUsuario(Usuario usuario) throws Exception 
	{
		DAOTablaUsuarios daoTablaUsuario = new DAOTablaUsuarios();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoTablaUsuario.setConn(conn);
			daoTablaUsuario.addUsuario(usuario);
			conn.commit();

		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally
		{
			try
			{
				daoTablaUsuario.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addCliente(Usuario usuario) throws Exception 
	{
		DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
		try
		{
			//Transacci�n
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.addCliente(usuario);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addCompannia(Compannia compannia) throws Exception 
	{
		DAOTablaCompannias daoCompannias = new DAOTablaCompannias();
		try
		{
			//////Transacci�n
			this.conn = darConexion();
			daoCompannias.setConn(conn);
			daoCompannias.addCompannia(compannia);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompannias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addSitio(Sitio sitio) throws Exception
	{
		DAOTablaSitios daoSitios = new DAOTablaSitios();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSitios.setConn(conn);
			daoSitios.addSitio(sitio);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSitios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addEspectaculo(Espectaculo espectaculo) throws Exception
	{
		DAOTablaEspectaculos daoEspectaculos = new DAOTablaEspectaculos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoEspectaculos.setConn(conn);
			daoEspectaculos.addEspectaculo(espectaculo);;			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEspectaculos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addFuncion(Funcion funcion) throws Exception
	{
		DAOTablaFunciones daoFunciones = new DAOTablaFunciones();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoFunciones.setConn(conn);
			daoFunciones.addFuncion(funcion);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFunciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void cancelarFuncion(Funcion funcion) throws Exception 
	{
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSillas.setConn(conn);
			daoSillas.cancelarFuncion(funcion);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void comprarAbonamiento(List<Funcion> funciones, int userId) throws Exception 
	{
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSillas.setConn(conn);
			daoSillas.comprarAbonamiento(funciones, userId);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void devolverAbonamiento(List<Silla> sillas) throws Exception 
	{
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSillas.setConn(conn);
			daoSillas.devolverAbonamiento(sillas);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void comprarSilla(Silla silla) throws Exception 
	{
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSillas.setConn(conn);
			daoSillas.comprarSilla(silla);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void devolverSilla(Silla silla) throws Exception 
	{
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSillas.setConn(conn);
			daoSillas.devolverSilla(silla);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void comprarSillas(List<Silla> sillas) throws Exception 
	{
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSillas.setConn(conn);
			daoSillas.comprarSillas(sillas);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void registrarFuncionRelizada(Funcion funcion) throws Exception 
	{
		DAOTablaFunciones daoFunciones = new DAOTablaFunciones();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoFunciones.setConn(conn);
			daoFunciones.registrarRealizada(funcion);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFunciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public ConsultaSitios consultarSitios(int sitioId) throws Exception 
	{
		ConsultaSitios response;
		DAOTablaSitios daoSitios = new DAOTablaSitios();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSitios.setConn(conn);
			response = daoSitios.consultarSitios(sitioId);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSitios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}

	public ConsultaFunciones consultarFunciones(ConsultaFunciones consulta) throws Exception 
	{
		ConsultaFunciones response;
		DAOTablaFunciones daoFunciones = new DAOTablaFunciones();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoFunciones.setConn(conn);
			response = daoFunciones.consultarFunciones(consulta);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFunciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	public ConsultaFunciones consultarAsistencia(int usuarioId) throws Exception 
	{
		ConsultaFunciones response;
		DAOTablaFunciones daoFunciones = new DAOTablaFunciones();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoFunciones.setConn(conn);
			response = daoFunciones.consultarAsistencia(usuarioId);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFunciones.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	public ConsultaCompannias consultarCompannia(int idCompannia) throws Exception 
	{
		ConsultaCompannias response;
		DAOTablaCompannias daoCompannias = new DAOTablaCompannias();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoCompannias.setConn(conn);
			response = daoCompannias.consultarCompannia(idCompannia);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompannias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	public Usuario consultarAsistenciaFest(ConsultaAs consulta) throws Exception 
	{
		Usuario response;
		DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
		try
		{
			//Transacci�n
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			response = daoUsuarios.consultarAsistenciaFest(consulta);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	public Usuario consultarNoAsistenciaFest(ConsultaAs consulta) throws Exception 
	{
		Usuario response;
		DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
		try
		{
			//Transacci�n
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			response = daoUsuarios.consultarNoAsistenciaFest(consulta);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	public Respuesta consultarComprasBoletas(Filtros filtros) throws Exception 
	{
		Respuesta response;
		DAOTablaSillas daoSillas = new DAOTablaSillas();
		try
		{
			//Transacci�n
			this.conn = darConexion();
			daoSillas.setConn(conn);
			response = daoSillas.consultarComprasBoletas(filtros);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSillas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	public ArrayList<Usuario> consultarBuenosClientes(int numBoletas) throws Exception 
	{
		ArrayList<Usuario> response;
		DAOTablaUsuarios daoUsuarios = new DAOTablaUsuarios();
		try
		{
			//Transacci�n
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			response = daoUsuarios.consultarBuenosClientes(numBoletas);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return response;
	}
	
	/**
	 * M�todo que modela la transacci�n que retorna el/los espectaculos m�s populares
	 * @return ListaEspectaculos - objeto que modela  un arreglo de espectaculos. este arreglo contiene el resultado de la búsqueda
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public ArrayList<Espectaculo> espectaculosMasPopulares() throws Exception 
	{
		ArrayList<Espectaculo> espectaculos;
		DAOTablaEspectaculos daoTablaEspectaculo = new DAOTablaEspectaculos();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoTablaEspectaculo.setConn(conn);
			espectaculos = daoTablaEspectaculo.darEspectaculoMasPopular();

		}
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoTablaEspectaculo.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return espectaculos;
	}
	
	/**
	 * M�todo que modela la transacci�n que retorna el/las preferencias de un cliente
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public void addPreferenciaCliente(PreferenciaCliente usuarioCli) throws Exception 
	{
		DAOTablaPreferenciaClientes daoTablaPreferencias = new DAOTablaPreferenciaClientes();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoTablaPreferencias.setConn(conn);
			daoTablaPreferencias.addPreferencia(usuarioCli);

		}
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoTablaPreferencias.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	/**
	 * M�todo que modela la transacci�n que genera el reporte de la funci�n con el id que entra como par�metro.
	 * @param id - Id de la funcion a buscar. 
	 * @return ListaFunciones - objeto que modela  un arreglo de funciones. este arreglo contiene el resultado de la b�squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public ArrayList<ReporteFuncion> darReporteFunciones(int id) throws Exception 
	{
		ArrayList<ReporteFuncion> funciones;
		DAOTablaFunciones daoFuncion = new DAOTablaFunciones();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoFuncion.setConn(conn);
			funciones = daoFuncion.darReporteFunciones(id);
		} 
		catch (SQLException e) 
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			try 
			{
				daoFuncion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			}
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return funciones;
	}
	
	/**
	 * M�todo que modela la transacci�n que genera el reporte del espect�culo con el nombre que entra como par�metro.
	 * @param nombreEspectaculo - Nombre del espectaculo a buscar. nombreEspectaculo != null
	 * @return ListaEspectaculos - objeto que modela  un arreglo de espectaculos. este arreglo contiene el resultado de la b�squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci�n
	 */
	public ArrayList<ReporteEspectaculo> generarReporteEspectaculo(String nombreEspectaculo) throws Exception
	{
		ArrayList<ReporteEspectaculo> espectaculos;
		DAOTablaEspectaculos daoEspectaculo = new DAOTablaEspectaculos();
		try 
		{
			//Transacci�n
			this.conn = darConexion();
			daoEspectaculo.setConn(conn);
			espectaculos = daoEspectaculo.generarReporteEspectaculo(nombreEspectaculo);

		}
		catch (SQLException e)
		{
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) 
		{
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally
		{
			try 
			{
				daoEspectaculo.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} 
			catch (SQLException exception) 
			{
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return espectaculos;
	}
}