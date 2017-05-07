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
public class DAOTablaFestivAndes 
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
	 * Método constructor que crea DAOTablaFestivAndes
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaFestivAndes() 
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
	 * Método que, usando la conexión a la base de datos, saca todos los festivales de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM FESTIVANDES;
	 * @return Arraylist con los festivales de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<FestivAndes> darFestivales() throws SQLException, Exception 
	{
		ArrayList<FestivAndes> festivales = new ArrayList<FestivAndes>();

		String sql = "SELECT * FROM ISIS2304A151720.FESTIVANDES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idFest = Integer.parseInt(rs.getString("IDFESTIVAL"));
			String dia = rs.getString("DIA");
			String horaInicio = rs.getString("HORAINICIO");
			String horaFin = rs.getString("HORAFIN");
			
			festivales.add(new FestivAndes(idFest, dia, horaInicio, horaFin));
		}
		return festivales;
	}


	/**
	 * Método que busca el/los festivales con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los festivales a buscar
	 * @return ArrayList con los festivales encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<FestivAndes> buscarFestivalesPorName(String name) throws SQLException, Exception 
	{
		ArrayList<FestivAndes> festivales = new ArrayList<FestivAndes>();

		String sql = "SELECT * FROM ISIS2304A151720.FESTIVANDES WHERE NOMFESTIVANDES ='" + name + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idFest = Integer.parseInt(rs.getString("IDFESTIVAL"));
			String dia = rs.getString("DIA");
			String horaInicio = rs.getString("HORAINICIO");
			String horaFin = rs.getString("HORAFIN");
			
			festivales.add(new FestivAndes(idFest, dia, horaInicio, horaFin));
		}

		return festivales;
	}

	/**
	 * Método que agrega el festival que entra como parámetro a la base de datos.
	 * @param festival - el festival a agregar. festival !=  null
	 * <b> post: </b> se ha agregado el festival a la base de datos en la transaction actual. pendiente que el festival master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el festival a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addFestival(FestivAndes festival) throws SQLException, Exception 
	{
		String sql = "INSERT INTO ISIS2304A151720.FESTIVANDES (IDFESTIVAL, DIA, HORAINICIO, HORAFIN) VALUES (";
		sql += festival.getIdFestival() + ",";
		sql += "'" + festival.getDia() + "','";
		sql += festival.getHoraInicio() + "','";
		sql += festival.getHoraFin() + "')";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
}