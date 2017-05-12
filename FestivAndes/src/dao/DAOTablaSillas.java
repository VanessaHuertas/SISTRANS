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
import java.util.List;
import java.util.Objects;

import vos.ConsultaAs;
import vos.Filtros;
import vos.Funcion;
import vos.Respuesta;
import vos.Silla;
import vos.Usuario;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaSillas 
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
	public DAOTablaSillas() {
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

	public void comprarAbonamiento(List<Funcion> funciones, int userId) throws SQLException, Exception 
	{
		this.conn.setAutoCommit(false);
		PreparedStatement prepStmt = null;
		recursos.add(prepStmt);
		for(Funcion funcionA : funciones)
		{
			String sql = "SELECT IDSILLA FROM ISIS2304A151720.SILLAS WHERE DISPONIBLE = 1 AND FUNCIONES_IDFUNCION = " + funcionA.getIdFuncion();
			prepStmt = conn.prepareStatement(sql);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) 
			{
				Silla sillaA = new Silla();
				sillaA.setIdSilla(rs.getInt(1));
				sillaA.setUserId(userId);
				comprarSilla(sillaA);
			}
		}
		this.conn.commit();
		this.conn.setAutoCommit(true);
	}

	/**
	 * Método que agrega el video que entra como parámetro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void devolverAbonamiento(List<Silla> sillas) throws SQLException, Exception 
	{
		conn.setAutoCommit(false);
		for(Silla sillaA : sillas){
			devolverSilla(sillaA);
		}
		conn.commit();
		conn.setAutoCommit(true);
	}

	/**
	 * Método que agrega el video que entra como parámetro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void comprarSilla(Silla silla) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A151720.SILLAS SET DISPONIBLE = 0, USUARIOS_IDUSUARIO = ? "
				+ "WHERE IDSILLA = ? AND DISPONIBLE = 1";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.setInt(1, silla.getUserId());
		prepStmt.setInt(2, silla.getIdSilla());
		prepStmt.executeQuery();
	}

	/**
	 * Método que agrega el video que entra como parámetro a la base de datos.
	 * @param video - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void devolverSilla(Silla silla) throws SQLException, Exception 
	{
		conn.setAutoCommit(false);
		PreparedStatement prepStmt = null;
		String sqlSelect = "SELECT IDSILLA, PRECIO FROM ISIS2304A151720.\"Sillas\" WHERE ID = " + silla.getIdSilla();
		prepStmt = conn.prepareStatement(sqlSelect);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String sql = "UPDATE ISIS2304A151720.\"SILLAS\" SET DISPONIBLE = 1, USER_ID = null, DEVUELTO = 1 "
					+ "WHERE ID = ? ";
			prepStmt = conn.prepareStatement(sql);
			prepStmt.setInt(1, rs.getInt(1));
			prepStmt.executeQuery();
			sql = "INSERT INTO ISIS2304A151720.\"NOTAS_CREDITO\" (VALOR, USER_ID) VALUES (?, " + silla.getUserId() + ")";
			prepStmt = conn.prepareStatement(sql);
			prepStmt.setInt(1, rs.getInt(2));
			prepStmt.executeQuery();
		}
		conn.commit();
		conn.setAutoCommit(true);
	}

	/**
	 * M�todo que permite la compra de m�ltiples sillas en una sola transacci�n
	 * @param sillas - Una lista con las sillas a comprar. Se espera que cada
	 * silla traiga al menos su id y el id del usuario que la adquiere. sillas !=  null
	 * <b> post: </b> se ha persistido la informaci�n de la compra de las boletas
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo persistir la compra
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void comprarSillas(List<Silla> sillas) throws SQLException, Exception 
	{       
		this.conn.setAutoCommit(false);

		for(Silla sillaA : sillas)
		{
			comprarSilla(sillaA);
		}
		this.conn.commit();
		this.conn.setAutoCommit(true);
	}

	/**
	 * Método que agrega el video que entra como parámetro a la base de datos.
	 * @param funcion - el video a agregar. video !=  null
	 * <b> post: </b> se ha agregado el video a la base de datos en la transaction actual. pendiente que el video master
	 * haga commit para que el video baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void cancelarFuncion(Funcion funcion) throws SQLException, Exception 
	{
		if(funcion.getIdRol() == 1)
		{
			conn.setAutoCommit(false);
			String sqlSelect = "SELECT REALIZADA FROM ISIS2304A151720.FUNCIONES WHERE IDFUNCION = " + funcion.getIdFuncion();

			PreparedStatement prepStmt = conn.prepareStatement(sqlSelect);
			recursos.add(prepStmt);

			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) 
			{
				boolean realizada = rs.getInt(1) == 0 ? false : true;
				if(!realizada){
					sqlSelect = "SELECT IDSILLA, USUARIOS_IDUSUARIO FROM ISIS2304A151720.SILLAS WHERE FUNCIONES_IDFUNCION = " + funcion.getIdFuncion()
					+ " AND DISPONIBLE = 0";
					PreparedStatement prepStmt1 = conn.prepareStatement(sqlSelect);
					recursos.add(prepStmt1);
					ResultSet rs1 = prepStmt.executeQuery();
					while(rs1.next()){
						Silla sillaDevolver = new Silla();
						sillaDevolver.setIdSilla(rs1.getInt(1));
						sillaDevolver.setUserId(rs1.getInt(2));
						devolverSilla(sillaDevolver);
					}
					String sqlDelete = "DELETE FROM ISIS2304A151720.FUNCIONES WHERE IDFUNCION = " + funcion.getIdFuncion();
					PreparedStatement prepStmt2 = conn.prepareStatement(sqlDelete);
					recursos.add(prepStmt2);
					prepStmt2.executeUpdate();
				}
			}
		}
		else
		{
			throw new Exception("Las funciones de espect�culos solo pueden ser canceladas por un administrador");
		}
	}
	
	public Respuesta consultarComprasBoletas(Filtros filtros) throws SQLException, Exception 
	{
		ArrayList<Respuesta> respuesta = new ArrayList<Respuesta>();
		Respuesta con = null;
		
		Date fechaInicio = filtros.getFechaInicio();
		Date fechaFin = filtros.getFechaFin();
		String elementos = filtros.getElementosEscenario();
		String locali = filtros.getTipoLocalidad();
		String hInicio = filtros.getHoraInicio();
		String hFin = filtros.getHoraFin();
		String dia = filtros.getDia();


		StringBuilder sql = new StringBuilder("SELECT f.ESPECTACULOS_nombre, f.fechaR, SITIOS.NOMBRES, COUNT(*) AS boletas_vendidas, COUNT(s.USUARIOS_idUsuario) AS usuarios_registrados "
				+ "FROM FUNCIONES f INNER JOIN SILLAS s ON f.IDFUNCION = s.FUNCIONES_IDFUNCION "
				+ "INNER JOIN ESPECTACULOS e ON f.ESPECTACULOS_NOMBRE = e.NOMBRE "
				+ "INNER JOIN LOCALIDADES l ON s.LOCALIDADES_IDLOCALIDAD = l.IDLOCALIDAD " 
				+ "INNER JOIN SITIOS ON SITIOS.IDSITIO = f.SITIOS_IDSITIO " 
				+ "WHERE s.DISPONIBLE = '0' ");
		sql.append((Objects.nonNull(fechaInicio)) ? " AND f.FECHAR >= " + fechaInicio : "");
		sql.append((Objects.nonNull(fechaFin)) ? " AND f.FECHAR <= " + fechaFin : "");
		sql.append((Objects.nonNull(elementos)) ? " AND e.REQUERIMIENTOS LIKE %" + elementos + "%" : "");
		sql.append((Objects.nonNull(locali)) ? " AND l.DESCRIPCION LIKE %" + locali + "%" : "");
		sql.append((Objects.nonNull(hInicio)) ? " AND TO_CHAR(f.FECHAR, 'HH24:MI:SS') >= " + hInicio : "");
		sql.append((Objects.nonNull(hFin)) ? " AND TO_CHAR(f.FECHAR, 'HH24:MI:SS') <= " + hFin : "");
		sql.append((Objects.nonNull(dia)) ? " AND TO_CHAR(TO_DATE('f.FECHAR','dd/mm/yyyy'), 'DAY') = " + dia.toUpperCase() : "");
		
		sql.append(" GROUP BY f.ESPECTACULOS_nombre, f.fechaR, SITIOS.NOMBRES");
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql.toString());
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String nombreE = rs.getString("f.ESPECTACULOS_nombre");
			Date fecha = rs.getDate("f.fechaR");
			String sitio = rs.getString("SITIOS.NOMBRES");
			int vendidas = rs.getInt("boletas_vendidas");
			int registrados = rs.getInt("usuarios_registrados");
			respuesta.add(new Respuesta(nombreE,fecha,sitio,vendidas,registrados));
		}
		return con;
	}
}