package clase.practica20_11_18;

import java.util.Scanner;

public class MenuOpciones {
	
	Scanner lector = new Scanner(System.in);
	
	public void mostrarMenuPrincipal() {
		System.out.println("\n\n");
		System.out.println("===================================");
		System.out.println("==========MENU PRINCIPAL===========");
		System.out.println("===================================");
		System.out.println("1. Insertar datos");
		System.out.println("2. Mostrar contenido en productos");
		System.out.println("3. Mostrar contenido en secciones");
		System.out.println("0. Salir");
		System.out.print("\nElija una opción: ");
	}

	
	public int elegirOpcion() {
		int opcion = 0;

		try {
			opcion = Integer.parseInt(lector.nextLine());
		} catch (NumberFormatException e) {
			opcion = -1;
		}
		return opcion;
	}
	
	
	
	
	
}
