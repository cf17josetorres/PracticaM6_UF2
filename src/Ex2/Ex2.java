package Ex2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex2 {
	//static Scanner reader;
	//static Connection conn;
	public static void main(String[] args) throws Exception {
		Scanner reader = new Scanner(System.in);
		Connection conn;
        Ex2BaseDatos db = new Ex2BaseDatos();

		// TODO Auto-generated method stub
		reader = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		String conex = "jdbc:mysql://localhost:3306/empresa";
		conn = DriverManager.getConnection(conex,"root","");
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
				db.Listar();
				break;
			case 2:
				db.Listar();
				System.out.println("Introducir el ID de empleado: ");
				int id = reader.nextInt();
				db.actualizar(new Empleado(id,"","",0));
				db.Listar();
				break;
			case 3:
				System.out.println("Introducir el nombre del empleado: ");
				String nombre = reader.next();
				System.out.println("Introducir el apellido del empleado: ");
				String apellido = reader.next();
				System.out.println("Introducir el salario dle empleado: ");
				int salario = reader.nextInt();
				db.insertar(new Empleado(0,nombre,apellido,salario));
				db.Listar();
				break;
			case 4:
				db.Listar();
				System.out.println("Introducir el ID de empleado: ");
				id = reader.nextInt();
				db.borrar(new Empleado(id,"","",0));
				db.Listar();
				break;

			}
		}
	}
}