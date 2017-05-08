/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * -------------------------------------------------------------------
 */
package dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vos.*;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaUsuarios 
{
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaUsuarios() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * Método que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}


	/**
	 * M�todo que, usando la conexi�n a la base de datos, saca todos los usuarios de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM USUARIOS;
	 * @return Arraylist con los usuarios de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Usuario> darUsuarios() throws SQLException, Exception 
	{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM ISIS2304A151720.USUARIOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int id = Integer.parseInt(rs.getString("IDUSUARIO"));
			String name = rs.getString("NOMBRE");
			String email = rs.getString("EMAIL");
			int idRol = Integer.parseInt(rs.getString("ROLES_IDROL"));
			int idFest = Integer.parseInt(rs.getString("FESTIVANDES_IDFESTIVAL"));
			usuarios.add(new Usuario(id, name, email, idFest, idRol));
		}
		return usuarios;
	}

	/**
	 * M�todo que busca el/los usuarios con el nombre que entra como par�metro.
	 * @param id - Identificador de el/los usuarios a buscar
	 * @return ArrayList con los usuarios encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Usuario> buscarUsuariosPorId(int id) throws SQLException, Exception 
	{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		String sql = "SELECT * FROM ISIS2304A151720.USUARIOS WHERE IDUSUARIO ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idU = Integer.parseInt(rs.getString("IDUSUARIO"));
			String name = rs.getString("NOMBRE");
			String email = rs.getString("EMAIL");
			int idRol = Integer.parseInt(rs.getString("ROLES_IDROL"));
			int idFest = Integer.parseInt(rs.getString("FESTIVANDES_IDFESTIVAL"));
			usuarios.add(new Usuario(idU, name, email, idFest, idRol));
		}

		return usuarios;
	}

	/**
	 * Método que agrega el video que entra como parámetro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addUsuario(Usuario usuario) throws SQLException, Exception {

		String sql = "INSERT INTO ISIS2304A151720.USUARIOS VALUES (";
		sql += usuario.getIdUsuario() + ",'";
		sql += usuario.getNombreUsuario() + "','";
		sql += usuario.getEmailUsuario() + "',";
		sql += usuario.getIdFestival() + ",";
		sql += usuario.getIdRol() + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}

	public void addCliente(Usuario usuario) throws SQLException, Exception 
	{
		if(usuario.getIdRol() == 1)
		{
			String sql = "INSERT INTO ISIS2304A151720.USUARIOS VALUES (";
			sql += usuario.getIdUsuario() + ",'";
			sql += usuario.getNombreUsuario() + "','";
			sql += usuario.getEmailUsuario() + "',";
			sql += usuario.getIdFestival() + ",";
			sql += "2)";

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("Los clientes solo pueden ser agregados por un administrador");
		}
	}
	
	public Usuario consultarAsistenciaFest(ConsultaAs consulta) throws SQLException, Exception 
	{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario con = null;
		
		String nombreCompanhia = consulta.getNombreCompanhia();
		Date fechaInicio = consulta.getFechaInicio();
		Date fechaFin = consulta.getFechaFin();

		String sql = "SELECT u.IDUSUARIO, u.NOMBRE, u.EMAIL FROM USUARIOS u INNER JOIN SILLAS s ON s.USUARIOS_IDUSUARIO = u.IDUSUARIO "
				+ "INNER JOIN FUNCIONES f ON f.IDFUNCION = s.FUNCIONES_IDFUNCION INNER JOIN ESPECTACULOS e ON e.NOMBRE = f.ESPECTACULOS_NOMBRE "
				+ "INNER JOIN COMPANHIAS_ESPECTACULOS ce ON ce.ESPECTACULOS_NOMBRE = e.NOMBRE WHERE s.ASISTIO = '1' AND ce.COMPANHIAS_NOMBRE ='" + nombreCompanhia 
				+ "' AND f.FECHAR BETWEEN " + fechaInicio + " AND " + fechaFin;
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int usuId = rs.getInt("u.IDUSUARIO");
			String nombUsu = rs.getString("u.NOMBRE");
			String emailUsu = rs.getString("u.EMAIL");
			usuarios.add(new Usuario(usuId,nombUsu,emailUsu,-1,-1));
		}
		return con;
	}
	
	public Usuario consultarNoAsistenciaFest(ConsultaAs consulta) throws SQLException, Exception 
	{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario con = null;
		
		String nombreCompanhia = consulta.getNombreCompanhia();
		Date fechaInicio = consulta.getFechaInicio();
		Date fechaFin = consulta.getFechaFin();

		String sql = "SELECT u.IDUSUARIO, u.NOMBRE, u.EMAIL FROM USUARIOS u INNER JOIN SILLAS s ON s.USUARIOS_IDUSUARIO = u.IDUSUARIO "
				+ "INNER JOIN FUNCIONES f ON f.IDFUNCION = s.FUNCIONES_IDFUNCION INNER JOIN ESPECTACULOS e ON e.NOMBRE = f.ESPECTACULOS_NOMBRE "
				+ "INNER JOIN COMPANHIAS_ESPECTACULOS ce ON ce.ESPECTACULOS_NOMBRE = e.NOMBRE WHERE s.ASISTIO = '0' AND ce.COMPANHIAS_NOMBRE ='" + nombreCompanhia 
				+ "' AND f.FECHAR BETWEEN " + fechaInicio + " AND " + fechaFin;
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int usuId = rs.getInt("u.IDUSUARIO");
			String nombUsu = rs.getString("u.NOMBRE");
			String emailUsu = rs.getString("u.EMAIL");
			usuarios.add(new Usuario(usuId,nombUsu,emailUsu,-1,-1));
		}
		return con;
	}
}