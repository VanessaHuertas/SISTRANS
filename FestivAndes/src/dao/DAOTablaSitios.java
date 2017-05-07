/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
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
import java.util.Date;

import vos.ConsultaSitios;
import vos.Funcion;
import vos.Silla;
import vos.Sitio;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaSitios
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
	public DAOTablaSitios() {
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
	 * Método que agrega el video que entra como parámetro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addSitio(Sitio sitio) throws SQLException, Exception
	{
		if(sitio.getIdRol() == 1)
		{
			String sql = "INSERT INTO ISIS2304A151720.SITIOS (IDSITIO, NOMBRES, CAPACIDAD, ESABIERTO, APTO, HORAINICIO, "
					+ "HORAFIN, CONDICIONES, PROTECCIONCLIMA, TIPOSSILLETERIA_IDTIPO) VALUES (";
			sql += sitio.getIdSitio() + ",'";
			sql += sitio.getNombre() + "',";
			sql += sitio.getCapacidad() + ",'";
			sql += sitio.getAbierto() + "','";
			sql += sitio.isAptoNecesidadesEsp() + "', to_date(";
			sql += sitio.getHoraDispInicio() + "), to_date(";
			sql += sitio.getHoraDispFin() + "),'";
			sql += sitio.getDescripcionTecnica() + "','";
			sql += sitio.isProteccionClima() + "',";
			sql += sitio.getTipoSilleteria() + ")";

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("Los sitios solo pueden ser agregadas por un administrador");
		}
	}
	
	public ConsultaSitios consultarSitios(int sitioId) throws SQLException, Exception 
	{
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		ArrayList<Silla> sillas = new ArrayList<Silla>();

		String sql = "SELECT * "
				+ "FROM ISIS2304A151720.SITIOS Sitios "
				+ "WHERE Sitios.IDSITIO = " + sitioId;

		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		ConsultaSitios result = null;
		
		while (rs.next()) 
		{
			boolean esAbierto = rs.getBoolean("ES_ABIERTO");
			int capacidad = rs.getInt("CAPACIDAD");
			boolean aptoNecEsp = rs.getBoolean("APTO_NECESIDADES_ESP");
			String descripcionT = rs.getString("DESCRIPCION_TECNICA");
			String tipoSilleteria = rs.getString("TIPO_SILLETERIA");
			boolean proteccionClima = rs.getBoolean("PROTECCION_CLIMA");	
			String nombre = rs.getString("NOMBRE");
			result = new ConsultaSitios(esAbierto, capacidad, aptoNecEsp, null, null, descripcionT, tipoSilleteria, proteccionClima, nombre);
		}
		String sql2 = "SELECT f.SITIOS_IDSITIO, f.FECHAR, f.ESPECTACULOS_NOMBRE, f.REALIZADA, "
				+ "s.IDSILLA, s.LOCALIDADES_IDLOCALIDAD, s.PRECIO, s.DISPONIBLE"
				+ "FROM ISIS2304A151720.FUNCIONES f "
				+ "INNER JOIN ISIS2304A151720.SILLAS s ON f.IDFUNCION = s.FUNCIONES_IDFUNCION AND f.FECHAR = s.FECHAR "
				+ "WHERE f.SITIOS_IDSITIO = " + sitioId;
		
		prepStmt = conn.prepareStatement(sql2);
		rs = prepStmt.executeQuery();
		
		while (rs.next()) 
		{
			int idfun = Integer.parseInt(rs.getString("f.IDFUNCION"));
			int sitioIdB = rs.getInt(1);
			Date fechaRealizacion = rs.getDate(2);
			String espectaculoId = rs.getString(3);
			int realizada = Integer.parseInt(rs.getString(4));
			int sillaId = rs.getInt(5);
			int localidadId = Integer.parseInt(rs.getString(6));
			int precio = rs.getInt(7);
			int disponible = Integer.parseInt(rs.getString(8));
			funciones.add(new Funcion(0, idfun, sitioIdB, fechaRealizacion, espectaculoId, realizada));
			sillas.add(new Silla(-1, sillaId, precio, 0, disponible,0, fechaRealizacion, idfun, localidadId));
		}
		
		result.setFunciones(funciones);
		result.setSillas(sillas);
		return result;
	}
}
