/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogot√° - Colombia)
 * Departamento de Ingenier√≠a de Sistemas y Computaci√≥n
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: FestivAndes
 * -------------------------------------------------------------------
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Espectaculo;
import vos.ReporteEspectaculo;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicaci√≥n
 */
public class DAOTablaEspectaculos
{
	/**
	 * Arraylits de recursos que se usan para la ejecuci√≥n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexi√≥n a la base de datos
	 */
	private Connection conn;

	/**
	 * M√©todo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaEspectaculos() {
		recursos = new ArrayList<Object>();
	}

	/**
	 * M√©todo que cierra todos los recursos que estan enel arreglo de recursos
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
	 * M√©todo que inicializa la connection del DAO a la base de datos con la conexi√≥n que entra como par√°metro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	/**
	 * M√©todo que agrega el video que entra como par√°metro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addEspectaculo(Espectaculo espectaculo) throws SQLException, Exception 
	{
		if(espectaculo.getIdRol() == 1)
		{
			String sql = "INSERT INTO ISIS2304A151720.ESPECTACULOS (NOMBRE, DURACION, IDIOMA, COSTO, DESCRIPCION, "
					+ "REQUERIMIENTOS) VALUES (";
			sql += "'" + espectaculo.getNombre() + "',";
			sql += espectaculo.getDuracion() + ",'";
			sql += espectaculo.getIdioma() + "','";
			sql += espectaculo.getCosto() + "','";
			sql += espectaculo.getDescripcion() + "','";
			sql += espectaculo.getRequerimientos() + "')";

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("Las compaÒÌas solo pueden ser agregadas por un administrador");
		}
	}
	
	/**
	 * MÈtodo que busca el/los espect·culos m·s populares.
	 * @return Arraylist con los espect·culos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Espectaculo> darEspectaculoMasPopular()  throws SQLException, Exception 
	{
		ArrayList<Espectaculo> espectaculos = new ArrayList<Espectaculo>();

		String sql = "SELECT * FROM ESPECTACULOS ESP WHERE ESP.NOMBRE = ( SELECT NOMBRE FROM "
				+ "( SELECT UE.ESPECTACULOS_NOMBRE AS NOMBRE, COUNT(UE.ESPECTACULOS_NOMBRE) AS CUENTA "
				+ "FROM USUARIOS_ESPECTACULOS UE JOIN USUARIOS U ON UE.USUARIOS_IDUSUARIO = U.IDUSUARIO "
				+ "AND U.ROL='CLIENTE' GROUP BY UE.ESPECTACULOS_NOMBRE ORDER BY COUNT(UE.ESPECTACULOS_NOMBRE) DESC ) "
				+ "WHERE ROWNUM = 1 )";	

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next())
		{
			String nombre = rs.getString("NOMBRE");
			int duracion = Integer.parseInt(rs.getString("DURACION"));
			int idioma = Integer.parseInt(rs.getString("IDIOMA"));
			int costo = Integer.parseInt(rs.getString("COSTO"));
			String descr = rs.getString("DESCRIPCION");
			String requTec = rs.getString("REQUERIMIENTOS");
			
			espectaculos.add(new Espectaculo(0, nombre, duracion, idioma, costo, descr, requTec));
		}
		return espectaculos;
	}
	
	/**
	 * MÈtodo que genera el reporte de el espectaculo con el nombre que entra como par·metro.
	 * @param nombreEspectaculo - Nombre de el/los espectaculos a buscar
	 * @return ArrayList con los espectaculos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<ReporteEspectaculo> generarReporteEspectaculo(String nombreEspectaculo) throws SQLException, Exception 
	{
		ArrayList<ReporteEspectaculo> espectaculos = new ArrayList<ReporteEspectaculo>();

		String sql = "SELECT ES.NOMBRE, SUM(B.CANTIDAD), SUM(B.TOTAL) FROM BOLETAS B JOIN FESTIVANDES FE ON FE.IDFESTIVANDES=B.FESTIVANDES_IDFESTIVANDES "
				+ "JOIN CLIENTES_FESTIVANDES CF ON CF.FESTIVANDES_IDFESTIVANDES=FE.IDFESTIVANDES "
				+ "JOIN USUARIOS US ON US.IDUSUARIO=CF.USUARIOS_IDUSUARIO AND US.ROL='CLIENTE' "
				+ "JOIN USUARIOS_ESPECTACULOS UE ON UE.USUARIOS_IDUSUARIO=US.IDUSUARIO "
				+ "JOIN ESPECTACULOS ES ON ES.NOMBRE=UE.ESPECTACULOS_NOMBRE AND ES.NOMBRE like '%" + nombreEspectaculo + "'% "
				+ "JOIN FUNCIONES FU ON FU.ESPECTACULOS_NOMBRE=ES.NOMBRE "
				+ "JOIN COMPANHIAS_TEATROS CT ON CT.NOMBRECOM=ES.COMPANHIAS_TEATROS_NOMBRECOM "
				+ "JOIN SITIOS_COMPANHIAST SC ON SC.COMPANHIAS_TEATROS_NOMBRECOM=CT.NOMBRECOM "
				+ "JOIN SITIOS S ON S.IDSITIO=SC.SITIOS_IDSITIO "
				+ "GROUP BY B.FESTIVANDES_IDFESTIVANDES, ES.NOMBRE "
				+ "ORDER BY B.FESTIVANDES_IDFESTIVANDES ASC";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String nombre = rs.getString("ES.NOMBRE");
			int cant = Integer.parseInt(rs.getString("SUM(B.CANTIDAD)"));
			double dinero = Double.parseDouble(rs.getString("SUM(B.TOTAL)"));

			espectaculos.add(new ReporteEspectaculo(nombre, cant, dinero));
		}

		return espectaculos;
	}
}