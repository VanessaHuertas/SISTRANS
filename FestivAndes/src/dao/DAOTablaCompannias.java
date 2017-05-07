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

import vos.Compannia;
import vos.ConsultaCompannias;
import vos.ConsultaFunciones;
import vos.Funcion;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOTablaCompannias
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
	public DAOTablaCompannias() {
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
	public void addCompannia(Compannia comp) throws SQLException, Exception 
	{
		if(comp.getIdRol() == 1)
		{
			String sql = "INSERT INTO ISIS2304A151720.COMPANHIAS (NOMBRE, NOMBREREP, PAISORIGEN, PAGINAWEB, "
					+ "FECHASALIDA, FECHALLEGADA, USUARIOS_IDUSUARIO) VALUES (";
			sql += "'" + comp.getNombre() + "','";
			sql += comp.getNombreRepresentante() + "','";
			sql += comp.getPaisOrigen() + "',";
			sql += comp.getPaginaWeb() + ", to_date('";
			sql += comp.getFechaPartida() + "', 'YYYY-MM-DD') , to_date('";
			sql += comp.getFechaLlegada() + "', 'YYYY-MM-DD'),";
			sql += comp.getIdUsuario() + ")";

			System.out.println("SQL stmt:" + sql);

			PreparedStatement prepStmt = conn.prepareStatement(sql);
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		else
		{
			throw new Exception("Las compa��as solo pueden ser agregadas por un administrador");
		}
	}

	public ConsultaCompannias consultarCompannia(int idCompannia) throws SQLException, Exception 
	{
		ArrayList<ConsultaCompannias> compannias = new ArrayList<ConsultaCompannias>();
		ConsultaCompannias con = null;

		String sql = "SELECT f.SITIO_ID, f.FECHA_REALIZACION, f.ESPECTACULO_ID, f.REALIZADA "
				+ "FROM ISIS2304A291720.FUNCIONES f "
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
			int asistencia = rs.getInt("f.SITIOS_IDSITIO");
			int clienReg = rs.getInt("f.IDFUNCION");
			double dinero = Double.parseDouble(rs.getString("f.REALIZADA"));
			double porcOc = Double.parseDouble(rs.getString("f.REALIZADA"));
			compannias.add(new ConsultaCompannias(asistencia, clienReg, dinero, porcOc));
		}
		return con;
	}
}