package clase.practica20_11_18;


public class Main {

	public static void main(String[] args) {

		MenuOpciones menu = new MenuOpciones();
		int respuesta;
		Conecta conecta;
		
		do {
			menu.mostrarMenuPrincipal();
			respuesta = menu.elegirOpcion();
			
			switch (respuesta) {
			case 1:
				String protocolo = "jdbc:mysql://";  // esto es la libreria necesaria para la conexion
				String EndPoint = "tienda.c6jxxywowqcr.eu-west-1.rds.amazonaws.com"; // lugar donde esta la base de datos remota
				String puerto = ":3306/";  // El puerto por defecto de conexion
				String baseDatos = "tiendaElectronica"; // es la base de datos a consultar
				
				String user = "admin"; // el usuario del gestor de bases de datos
				String password = "x9893913a"; // la clave que tiene el usuario.
				
				conecta = new Conecta(protocolo, EndPoint, puerto, baseDatos, user, password);
				conecta.conectarBaseDatos();
				break;
				
			case 2:
				Conecta contenidoProductos = new Conecta();
				contenidoProductos.MostrarDatosProductos();
				break;
				
			case 3:
				Conecta contenidoSecciones = new Conecta();
				contenidoSecciones.MostrarDatosSeccion();
				break;
			}
		} while (respuesta != 0);
	}

}
