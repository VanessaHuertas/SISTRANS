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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import vos.ConsultaFunciones;
import vos.Funcion;
import vos.ReporteFuncion;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaFunciones 
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
	public DAOTablaFunciones() {
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
	public void addFuncion(Funcion funcion) throws SQLException, Exception 
	{
		if(funcion.getIdRol() == 1)
		{
			String sqlSelect = "SELECT DURACION, FECHA_LLEGADA, FECHA_PARTIDA, HORA_DISP_INICIO, HORA_DISP_FIN "
					+ "FROM ISIS2304A151720.COMPANHIAS_ESPECTACULOS EspectaculosCompannias "
					+ "INNER JOIN ISIS2304A151720.COMPANHIAS Compannias ON EspectaculosCompannias.COMPANNIA_ID = Compannias.USUARIO_ID "
					+ "INNER JOIN ISIS2304A151720.ESPECTACULOS Espectaculos ON Espectaculos.ID = EspectaculosCompannias.ESPECTACULO_ID, "
					+ "ISIS2304A151720.SITIOS Sitios "
					+ "WHERE Sitios.ID = ? AND EspectaculosCompannias.ESPECTACULO_ID = ? "
					+ "FETCH FIRST ROW ONLY";

			PreparedStatement prepStmt = conn.prepareStatement(sqlSelect);
			recursos.add(prepStmt);
			prepStmt.setInt(1, funcion.getSitioId());
			prepStmt.setString(2, funcion.getEspectaculoId());

			ResultSet rs = prepStmt.executeQuery();

			Date fechaLlegada = null;
			Date fechaPartida = null;
			Date horaDispInicio = null;
			Date horaDispFin = null;
			int duracion = 0;

			Date horaFuncion = new Date(funcion.getFechaRealizacion().getTime());
			Calendar calendarFuncion = Calendar.getInstance();
			calendarFuncion.setTime(horaFuncion);
			calendarFuncion.set(0, 0, 0);


			while (rs.next()) 
			{
				duracion = rs.getInt("DURACION");
				fechaLlegada = rs.getDate("FECHA_LLEGADA");
				fechaPartida = rs.getDate("FECHA_PARTIDA");
				horaDispInicio = rs.getDate("HORA_DISP_INICIO");
				horaDispFin = rs.getDate("HORA_DISP_FIN");
			}

			if(Objects.isNull(fechaLlegada) || Objects.isNull(fechaPartida) || Objects.isNull(horaDispInicio) || Objects.isNull(horaDispFin)){
				throw new Exception("Ocurri� un error al verificar los datos ingresados");
			}
			else if(funcion.getFechaRealizacion().compareTo(fechaLlegada) < 0)
			{
				throw new Exception("La fecha de realización debe ser posterior a la llegada de la compañía responsable del espectáculo");
			}
			else if(funcion.getFechaRealizacion().compareTo(fechaPartida) > 0){
				throw new Exception("La fecha de realización debe ser anterior a la partida de la compañía responsable del espectáculo");
			}
			else
			{
				Calendar calendarInicio = Calendar.getInstance();
				calendarInicio.setTime(horaDispInicio);
				Calendar calendarFin = Calendar.getInstance();
				calendarFin.setTime(horaDispFin);
				calendarInicio.set(0, 0, 0);
				calendarFin.set(0, 0, 0);
				Calendar calendarFuncionFin = Calendar.getInstance();
				calendarFuncionFin.setTime(horaFuncion);
				calendarFuncionFin.add(Calendar.MINUTE, duracion);
				calendarFuncionFin.set(0, 0, 0);
				System.out.println(calendarInicio.toString());
				System.out.println(calendarFin.toString());
				System.out.println(calendarFuncion.toString());
				System.out.println(calendarFuncionFin.toString());
				if(calendarFuncion.before(calendarInicio) || calendarFuncionFin.after(calendarInicio)){
					throw new Exception("La fecha de realizaci�n debe estar dentro del rango de disponibilidad del sitio");
				}
				else
				{
					String sqlInsert = "INSERT INTO ISIS2304A151720.FUNCIONES VALUES (";
					sqlInsert += funcion.getIdFuncion() + ", ";
					sqlInsert += funcion.getFechaRealizacion() + ", ";
					sqlInsert += funcion.isRealizada() + ")";
					sqlInsert += funcion.getEspectaculoId() + ", ";
					sqlInsert += funcion.getSitioId() + ", ";

					prepStmt = conn.prepareStatement(sqlInsert);
					prepStmt.executeQuery();
				}
			}
		}
		else
		{
			throw new Exception("Las funciones de espect�culos solo pueden ser agregadas por un administrador");
		}
	}

	public void registrarRealizada(Funcion funcion) throws SQLException, Exception
	{
		if(funcion.getIdRol() == 1 || funcion.getIdRol() == 3)
		{
			String sql = "UPDATE ISIS2304A151720.FUNCIONES SET REALIZADA = 1 WHERE SITIOS_IDSITIO = ? "
					+ "AND FECHAR = ?";

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.setInt(1, funcion.getSitioId());
			prepStmt.setTimestamp(2, new Timestamp(funcion.getFechaRealizacion().getTime()));
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("Las funciones solo pueden ser modificadas por un administrador o un operario");
		}
	}

	public ConsultaFunciones consultarFunciones(ConsultaFunciones consulta) throws SQLException, Exception 
	{
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();

		String sql = "SELECT f.SITIO_ID, f.FECHA_REALIZACION, f.ESPECTACULO_ID, f.REALIZADA "
				+ "FROM ISIS2304A151720.FUNCIONES f "
				+ "INNER JOIN ISIS2304A151720.COMPANHIAS_ESPECTACULOS ec ON f.ESPECTACULO_ID = ec.ESPECTACULO_ID "
				+ "INNER JOIN ISIS2304A151720.ESPECTACULOS e ON ec.ESPECTACULO_ID = e.ID "
				+ "INNER JOIN ISIS2304A151720.CATEGORIAS ge ON e.ID = ge.ESPECTACULOS_NOMBRE WHERE true";
		sql += (consulta.getSitioId() != 0) ? " AND f.SITIOS_IDSITIO = " + consulta.getSitioId() : "";
		sql += (consulta.getFechaRealizacion1() != null) ? " AND f.FECHAR >= " + consulta.getFechaRealizacion1() : "";
		sql += (consulta.getFechaRealizacion2() != null) ? " AND f.FECHAR <= " + consulta.getFechaRealizacion2() : "";
		sql += (consulta.getEspectaculoId() != 0) ? " AND f.ESPECTACULOS_NOMBRE = " + consulta.getEspectaculoId() : "";
		sql += (consulta.getCompanniaId() != 0) ? " AND ec.COMPANHIAS_NOMBRE = " + consulta.getCompanniaId() : "";
		sql += (consulta.getIdiomaObra() != null) ? " AND e.IDIOMA = " + consulta.getIdiomaObra() : "";
		sql += (consulta.getGeneroId() != 0) ? " AND ge.IDCATEGORIA = " + consulta.getGeneroId() : "";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int sitioId = rs.getInt("f.SITIOS_IDSITIO");
			int funId = rs.getInt("f.IDFUNCION");
			Date fechaRealizacion = rs.getDate("f.FECHAR");
			String espectaculoId = rs.getString("f.ESPECTACULOS_NOMBRE");
			int realizada = Integer.parseInt(rs.getString("f.REALIZADA"));
			funciones.add(new Funcion(0, funId, sitioId, fechaRealizacion, espectaculoId, realizada));
		}
		consulta.setFunciones(funciones);
		return consulta;
	}

	public ConsultaFunciones consultarAsistencia(int usuarioId) throws SQLException, Exception 
	{
		ArrayList<Funcion> funciones = new ArrayList<Funcion>();
		ConsultaFunciones con = null;

		String sql = "SELECT f.SITIO_ID, f.FECHA_REALIZACION, f.ESPECTACULO_ID, f.REALIZADA "
				+ "FROM ISIS2304A151720.FUNCIONES f "
				+ "INNER JOIN ISIS2304A151720.COMPANHIAS_ESPECTACULOS ec ON f.ESPECTACULO_ID = ec.ESPECTACULO_ID "
				+ "INNER JOIN ISIS2304A151720.ESPECTACULOS e ON ec.ESPECTACULO_ID = e.ID "
				+ "INNER JOIN ISIS2304A151720.CATEGORIAS ge ON e.ID = ge.ESPECTACULOS_NOMBRE WHERE true";
		//		sql += (consulta.getSitioId() != 0) ? " AND f.SITIOS_IDSITIO = " + consulta.getSitioId() : "";
		//		sql += (consulta.getFechaRealizacion1() != null) ? " AND f.FECHAR >= " + consulta.getFechaRealizacion1() : "";
		//		sql += (consulta.getFechaRealizacion2() != null) ? " AND f.FECHAR <= " + consulta.getFechaRealizacion2() : "";
		//		sql += (consulta.getEspectaculoId() != 0) ? " AND f.ESPECTACULOS_NOMBRE = " + consulta.getEspectaculoId() : "";
		//		sql += (consulta.getCompanniaId() != 0) ? " AND ec.COMPANHIAS_NOMBRE = " + consulta.getCompanniaId() : "";
		//		sql += (consulta.getIdiomaObra() != null) ? " AND e.IDIOMA = " + consulta.getIdiomaObra() : "";
		//		sql += (consulta.getGeneroId() != 0) ? " AND ge.IDCATEGORIA = " + consulta.getGeneroId() : "";
		//		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int sitioId = rs.getInt("f.SITIOS_IDSITIO");
			int funId = rs.getInt("f.IDFUNCION");
			Date fechaRealizacion = rs.getDate("f.FECHAR");
			String espectaculoId = rs.getString("f.ESPECTACULOS_NOMBRE");
			int realizada = Integer.parseInt(rs.getString("f.REALIZADA"));
			funciones.add(new Funcion(0, funId, sitioId, fechaRealizacion, espectaculoId, realizada));
		}
		//		consulta.setFunciones(funciones);
		return con;
	}
	
	/**
	 * M�todo que busca la/las funciones con el id que entra como par�metro.
	 * @param id - Id de la/las funciones a buscar
	 * @return ArrayList con las funciones encontradas
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<ReporteFuncion> darReporteFunciones(int id) throws SQLException, Exception 
	{
		ArrayList<ReporteFuncion> funciones = new ArrayList<ReporteFuncion>();

		String sql = "SELECT FU.IDFUNCION, SUM(B.CANTIDAD), SUM(B.TOTAL) "
				+ "FROM BOLETAS B "
				+ "JOIN FESTIVANDES FE ON FE.IDFESTIVANDES=B.FESTIVANDES_IDFESTIVANDES "
				+ "JOIN CLIENTES_FESTIVANDES CF ON CF.FESTIVANDES_IDFESTIVANDES=FE.IDFESTIVANDES "
				+ "JOIN USUARIOS US ON US.IDUSUARIO=CF.USUARIOS_IDUSUARIO AND US.ROL='CLIENTE' "
				+ "JOIN USUARIOS_ESPECTACULOS UE ON UE.USUARIOS_IDUSUARIO=US.IDUSUARIO "
				+ "JOIN ESPECTACULOS ES ON ES.NOMBRE=UE.ESPECTACULOS_NOMBRE "
				+ "JOIN FUNCIONES FU ON FU.ESPECTACULOS_NOMBRE=ES.NOMBRE AND FU.IDFUNCION='" + id + "' "
				+ "JOIN COMPANHIAS_TEATROS CT ON CT.NOMBRECOM=ES.COMPANHIAS_TEATROS_NOMBRECOM "
				+ "JOIN SITIOS_COMPANHIAST SC ON SC.COMPANHIAS_TEATROS_NOMBRECOM=CT.NOMBRECOM "
				+ "JOIN SITIOS S ON S.IDSITIO=SC.SITIOS_IDSITIO "
				+ "JOIN LOCALIDADES LC ON LC.IDSITIO=S.IDSITIO "
				+ "GROUP BY B.FESTIVANDES_IDFESTIVANDES, FU.IDFUNCION "
				+ "ORDER BY B.FESTIVANDES_IDFESTIVANDES ASC";
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int idFuncion = Integer.parseInt(rs.getString("IDFUNCION"));
			int cantidad = Integer.parseInt(rs.getString("SUM(B.CANTIDAD)"));
			double total = Double.parseDouble(rs.getString("SUM(B.TOTAL)"));
			funciones.add(new ReporteFuncion(idFuncion, cantidad, total));
		}
		return funciones;
	}
}
