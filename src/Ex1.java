import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex1 {
	static Scanner reader;
	static Connection conn;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		reader = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		//String ruta ="/home/cf17jose.torres/Baixades/futbol.mdb";
		String conex = "jdbc:mysql://localhost:3306/futbol_mysql";
		conn=DriverManager.getConnection(conex,"root","");
		while (true) {
			System.out.println("1.- Visualiza la lista de empleados");
			System.out.println("2.- Mostar el empleado con maximo salario");
			System.out.println("3.- Insertar un nuevo empleado");
			System.out.println("4.- Borrar un nuevo empleado");
			System.out.println("5.- Salir");
			System.out.println("Introducir que opcion quieres? ");

			int opcion = reader.nextInt();
			switch (opcion) {
			case 1:
				Listar();
				break;
			case 2:
				Listar();
				actualizar();
				Listar();
				break;
			case 3:
				insertar();
				break;
			case 4:
				borrar();
				break;
			case 5:
				salir();
			}
		}
	}

	public static void Listar() throws SQLException {
		String sql = "select * from empleados";
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1) + " : " + rs.getString(2) +
					" : " + rs.getString(3) + " : " + rs.getString(4));
		}
	}

	public static int actualizar() throws SQLException {
		String sql = "update `empleados` set `salario`= `salario` + 10000"
				+ "where `cod_emp`=?;";
		System.out.println("Introducir el ID de empleado: ");
		int id = reader.nextInt();
		PreparedStatement sta = conn.prepareStatement(sql);
		sta.setInt(1, id);
		return sta.executeUpdate();
		//ResultSet rs = sta.executeQuery(sql);
	}

	public static int insertar() throws SQLException {
		System.out.println("Introducir el nombre del empleado: ");
		String nombre = reader.next();
		System.out.println("Introducir el apellido del empleado: ");
		String apellidos = reader.next();
		System.out.println("Introducir el salario dle empleado: ");
		int salario = reader.nextInt();
		
		String sql = "insert into empleados(nombre, apellidos, salario) "
				+ "values (?,?,?);";
		PreparedStatement sta = conn.prepareStatement(sql);
		sta.setString(1, nombre);
		sta.setString(2, apellidos);
		sta.setInt(3, salario);
		return sta.executeUpdate();
	}
	
	public static void borrar() throws SQLException {

	}
	
	public static void salir() throws SQLException {

	}
}