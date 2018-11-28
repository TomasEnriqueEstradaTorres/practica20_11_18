package clase.practica20_11_18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Conecta {
	
	Scanner sc = new Scanner(System.in);
	private String protocolo, EndPoint, puerto, baseDatos, url, user, password;
	private Connection miConexion;
	Statement miStatement;
	ResultSet miResultSet;
	PreparedStatement miSentencia;
	
	
	//CONSTRUCORES
	public Conecta() {
		this.protocolo = "jdbc:mysql://";  // esto es la libreria necesaria para la conexion
		this.EndPoint = "tienda.c6jxxywowqcr.eu-west-1.rds.amazonaws.com"; // lugar donde esta la base de datos remota
		this.puerto = ":3306/";  // El puerto por defecto de conexion
		this.baseDatos = "tiendaElectronica"; // es la base de datos a consultar
		
		this.url = getProtocolo() + getEndPoint() + getPuerto() + getBaseDatos(); // Combinacion para obtener la direccion URL completa
		this.user = "admin"; // el usuario del gestor de bases de datos
		this.password = "x9893913a"; // la clave que tiene el usuario.
	}
	
	
	public Conecta(String protocolo, String endPoint, String puerto, String baseDatos, String user,String password) {
		this.protocolo = protocolo;
		this.EndPoint = endPoint;
		this.puerto = puerto;
		this.baseDatos = baseDatos;
		this.url = getProtocolo() + getEndPoint() + getPuerto() + getBaseDatos();
		this.user = user;
		this.password = password;
	}
	
	
	//GETTER Y SETTER
	public String getProtocolo() {
		return protocolo;
	}


	public String getEndPoint() {
		return EndPoint;
	}


	public String getPuerto() {
		return puerto;
	}


	public String getBaseDatos() {
		return baseDatos;
	}


	public String getUrl() {
		return url;
	}


	public String getUser() {
		return user;
	}


	public String getPassword() {
		return password;
	}


	public Connection getMiConexion() {
		return miConexion;
	}


	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}


	public void setEndPoint(String endPoint) {
		EndPoint = endPoint;
	}


	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}


	public void setBaseDatos(String baseDatos) {
		this.baseDatos = baseDatos;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setMiConexion(Connection miConexion) {
		this.miConexion = miConexion;
	}
	
	
	//METODOS

	public void conectarBaseDatos() {
		String nombre, imagen, descripcion;
		double precio;
		int id_seccion;
		
		//String instruccionSQL =  "INSERT INTO productos(nombre, precio, imagen, descripcion, seccion) VALUES(?,?,?,?,?)";
		try {
			miConexion = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			System.out.println("Conexion realizada a la Base de Datos: " + getBaseDatos());
			
			miSentencia = miConexion.prepareStatement("INSERT INTO productos(nombre, precio, imagen, descripcion, seccion) VALUES(?,?,?,?,?)");
			
			System.out.print("\nNombre del producto: ");
			nombre = sc.nextLine();
			
			System.out.print("Precio: ");
			precio = Double.parseDouble(sc.nextLine());
			
			System.out.print("Imagen: ");
			imagen = sc.nextLine();
			
			System.out.print("Descripcion: ");
			descripcion = sc.nextLine();
			
			System.out.print("Id de Seccion: ");
			id_seccion = Integer.parseInt(sc.nextLine());
		
			
			miSentencia.setString(1, nombre);
			miSentencia.setDouble(2, precio);
			miSentencia.setString(3, imagen);
			miSentencia.setString(4, descripcion);
			miSentencia.setInt(5, id_seccion);
			
			miSentencia.executeUpdate();  // este metodo devuelte tambien un entero, 1 si esta modificado o cero si no lo esta
			// miStatement.executeUpdate(instruccionSQL);
			System.out.println("Datos insertados correctamente");
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("---> No existe la base de datos");
		}
	}
	
	
	
	public void MostrarDatosProductos() {
		try {
			miConexion = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			miStatement = miConexion.createStatement();
			miResultSet = miStatement.executeQuery("SELECT * FROM productos");

			System.out.println("\nLISTA DE DATOS EN LA TABLA PRODUCTOS");
			while (miResultSet.next()) {  
				/** mientras alla un siguente registro mas adelante de donde esta el cursor, esto se ejecutara.
				 * Los nombres de los campos deben de ser iguales a los que estan en la BD   */
				String id = miResultSet.getString("id");
				String nombre = miResultSet.getString("nombre");
				String precio = miResultSet.getString("precio");
				String imagen = miResultSet.getString("imagen");
				String descripcion = miResultSet.getString("descripcion");
				String seccion = miResultSet.getString("seccion");
				
		        System.out.println("\nID: " + id +
		        				   "\nNombre: " + nombre + 
		        				   "\nPrecio: " + precio + 
		        				   "\nImagen: " + imagen + 
		        				   "\nDescripcion: " + descripcion + 
		        				   "\nSeccion: " + seccion);
			}
			miResultSet.close();
			miStatement.close(); // siempre hay que cerrar todas las sentencias
			miConexion.close();
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("No existe la base de datos <---");
		}
	}
	
	
	public void MostrarDatosSeccion() {
		try {
			miConexion = DriverManager.getConnection(getUrl(), getUser(), getPassword());
			miStatement = miConexion.createStatement();
			miResultSet = miStatement.executeQuery("SELECT * FROM secciones");

			System.out.println("\nLISTA DE DATOS EN LA TABLA SECCIONES");
			while (miResultSet.next()) {  
				String id = miResultSet.getString("id");
				String nombre = miResultSet.getString("nombre");

		        System.out.println("\nID: " + id +
		        				   "\nNombre: " + nombre);
			}
			miResultSet.close();
			miStatement.close();  // siempre hay que cerrar todas las sentencias
			miConexion.close();
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("No existe la base de datos <---");
		}
	}
	
	
	
	

}
