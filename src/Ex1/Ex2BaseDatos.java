package Ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex2BaseDatos {
	private Scanner reader;
	private Connection conn;

	public Ex2BaseDatos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String conex = "jdbc:mysql://localhost:3306/empresa";
			this.conn = DriverManager.getConnection(conex,"root","");
			this.reader = new Scanner(System.in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void Listar() {
		try {
			String sql = "select * from empleados";
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while(rs.next()) {
				System.out.println(rs.getString(1) + " : " + rs.getString(2) +
						" : " + rs.getString(3) + " : " + rs.getString(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int actualizar() {
		String sql = "update `empleados` set `salario`= `salario` + 10000"
				+ "where `cod_emp`=?;";
		System.out.println("Introducir el ID de empleado: ");
		int id = reader.nextInt();
		int num = 0;
		try {
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, id);
			num = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public int insertar() {
		System.out.println("Introducir el nombre del empleado: ");
		String nombre = reader.next();
		System.out.println("Introducir el apellido del empleado: ");
		String apellidos = reader.next();
		System.out.println("Introducir el salario dle empleado: ");
		int salario = reader.nextInt();
		int num = 0;
		try {
			String sql = "insert into empleados(nombre, apellidos, salario) "
					+ "values (?,?,?);";
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setString(1, nombre);
			sta.setString(2, apellidos);
			sta.setInt(3, salario);
			return sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public int borrar() {
		System.out.println("Introducir el nombre del empleado: " );
		int id = reader.nextInt();
		int num = 0;
		try {
			String sql = "delete from empleados where cod =?;";
			PreparedStatement sta = conn.prepareStatement(sql);
			sta.setInt(1, id);
			sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
