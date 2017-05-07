/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * -------------------------------------------------------------------
 */
package dao;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.*;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaPreferenciaClientes 
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
	 * Método constructor que crea DAOTablaUsuarioEspectaculo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaPreferenciaClientes() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * Método que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() 
	{
		for(Object ob : recursos)
		{
			if(ob instanceof PreparedStatement)
				try 
			{
					((PreparedStatement) ob).close();
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con)
	{
		this.conn = con;
	}


	/**
	 * Método que, usando la conexión a la base de datos, saca todos los usuarios que asisten a un espectaculo de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM USUARIOS_ESPECTACULOS;
	 * @return Arraylist con los usuarios que asisten a un espectaculo de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<PreferenciaCliente> darPreferencias() throws SQLException, Exception 
	{
		ArrayList<PreferenciaCliente> preferencias = new ArrayList<PreferenciaCliente>();

		String sql = "SELECT * FROM ISIS2304A151720.PREFERENCIASCLIENTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idPrefer = Integer.parseInt(rs.getString("IDPREFERENCIA"));
			String tipoPrefer = rs.getString("TIPO");
			String valorPrefer = rs.getString("VALOR");
			int idUsu = Integer.parseInt(rs.getString("USUARIOS_IDUSUARIO"));

			preferencias.add(new PreferenciaCliente(idPrefer, tipoPrefer, valorPrefer, idUsu));		
		}
		return preferencias;
	}


	/**
	 * Método que busca el/los espectaculos con el nombre y el id del usuario que entra como parámetro.
	 * @param id - Id de la/las boletas a buscar
	 * @return ArrayList con los espectaculos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<PreferenciaCliente> buscarPreferenciaPorUsuId(int id) throws SQLException, Exception 
	{
		ArrayList<PreferenciaCliente> preferencias = new ArrayList<PreferenciaCliente>();

		String sql = "SELECT * ISIS2304A151720.PREFERENCIASCLIENTES WHERE IDPREFERENCIA ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idPrefer = Integer.parseInt(rs.getString("IDPREFERENCIA"));
			String tipoPrefer = rs.getString("TIPO");
			String valorPrefer = rs.getString("VALOR");
			int idUsu = Integer.parseInt(rs.getString("USUARIOS_IDUSUARIO"));

			preferencias.add(new PreferenciaCliente(idPrefer, tipoPrefer, valorPrefer, idUsu));		
		}
		return preferencias;
	}


	/**
	 * Método que agrega la preferencia del usuario que entra como parámetro a la base de datos.
	 * @param usuarioEspectaculo - el usuarioEspectaculo a agregar. usuarioEspectaculo !=  null
	 * <b> post: </b> se ha agregado el usuarioEspectaculo a la base de datos en la transaction actual. pendiente que el usuarioEspectaculo master
	 * haga commit para que el usuarioEspectaculo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el usuarioEspectaculo a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPreferencia(PreferenciaCliente preferencia) throws SQLException, Exception
	{
		String sql1 = "INSERT INTO ISIS2304A151720.PREFERENCIASCLIENTES ( IDPREFERENCIA, TIPO, VALOR, USUARIOS_IDUSUARIO) VALUES (";
		sql1 += preferencia.getIdPreferencia() + ",";
		sql1 += "'" + preferencia.getTipoPreferencia() + "',";
		sql1 += "'" + preferencia.getValor() + "',";
		sql1 += preferencia.getIdUsuario() + ")";

		System.out.println("SQL stmt:" + sql1);

		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		recursos.add(prepStmt1);
		prepStmt1.executeQuery();
	}
}