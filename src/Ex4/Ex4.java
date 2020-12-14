package Ex4;

import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Ex4 {

	static Scanner reader;
	static Connection conn;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		String conex = "jdbc:mysql://localhost:3306/empresa";
		conn = DriverManager.getConnection(conex,"root","");
		reader = new Scanner(System.in);

		//DatabaseMetaData  dbmd = 
		
		while (true) {
			System.out.println("1.- Visualiza datos de la metadata");
			System.out.println("2.- Muestra las bases de datos de mysql");
			System.out.println("3.- Ejecuta show databases;");
			System.out.println("4.- Muestra las tablas de una base de datos");
			System.out.println("5.- Muestra las columnas de una tabla de una base de datos");
			System.out.println("6.- Muestra las claves primarias y foregin keys de una tabla una tabla");
			System.out.println("7.- Salir");
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
				Listar();
				break;
			case 4:
				Listar();
				borrar();
				Listar();
				break;
			case 5:
				salir();
			}
		}
	}
	
	public static void show_primary_keys(DatabaseMetaData dbmd) throws SQLException {
		System.out.println("Introduce base de datos: ");
		String based = reader.next();
		System.out.println("Introduce tabla de la base de datos: ");
		String tabla = reader.next();
		ResultSet rs = dbmd.getPrimaryKeys(based, null, tabla);
		
		while (rs.next()) {
			System.out.println("Table name: " + rs.getString("TABLE_NAME"));
			System.out.println("Column name: " + rs.getString("COLUMN_NAME"));
			System.out.println("Primary key sequence: " + rs.getString("KEY_SEQ"));
			System.out.println("Primary key name: " + rs.getString("PK_NAME"));
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
		String apellido = reader.next();
		System.out.println("Introducir el salario dle empleado: ");
		int salario = reader.nextInt();

		String sql = "insert into empleados(nombre, apellido, salario) "
				+ "values (?,?,?);";
		PreparedStatement sta = conn.prepareStatement(sql);
		sta.setString(1, nombre);
		sta.setString(2, apellido);
		sta.setInt(3, salario);
		return sta.executeUpdate();
	}

	public static int borrar() throws SQLException {
		System.out.println("Introducir el nombre del empleado: " );
		int id = reader.nextInt();
		String sql = "delete from empleados where cod =?;";
		PreparedStatement sta = conn.prepareStatement(sql);
		sta.setInt(1, id);
		return sta.executeUpdate();
	}

	public static void salir() throws SQLException {

	}
}