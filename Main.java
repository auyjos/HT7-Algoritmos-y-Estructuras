import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<String> lector = Diccionario.getDiccionario();
		BinarySearchTree<String> searchEsp = new BinarySearchTree<String>();
		BinarySearchTree<String> searchIng = new BinarySearchTree<String>();
		BinarySearchTree<String> searchFra = new BinarySearchTree<String>();

		for (int i = 0; i < lector.size(); i++) {
			String[] separacion = lector.get(i).split(",");
			Traduccion translate = new Traduccion(separacion[0], separacion[1], separacion[2]);

			searchEsp.add(separacion[1], translate);
			searchIng.add(separacion[0], translate);
			searchFra.add(separacion[2], translate);

		}
		menu(searchEsp, searchIng, searchFra);

	}

	public static void menu(BinarySearchTree<String> searchEsp, BinarySearchTree<String> searchIng,
			BinarySearchTree<String> searchFra) {
		int opcion = 0;

		System.out.println("\n Hola Bienvenido al programa que compite contra Google Translator");
		do {

			try {
				System.out.println(
						"\n En qué idioma de origen está lo que desea traducir?\n1.Ingles\n2.Español\n3.Frances\n4.Salir");

				String menu = scan.nextLine();
				opcion = Integer.parseInt(menu);

				if (opcion == 1) {

					System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Español\n2.Frances");
					String submenu = scan.nextLine();
					int subopcion = Integer.parseInt(submenu);

					if (subopcion == 1) {
						System.out.println("\nSe traducirá del Ingles al español\n Ingrese el texto");
						String palabraIng = scan.nextLine();

			
						System.out.println(traducirOracion("espanol", searchIng, palabraIng));
						
					}
					if (subopcion == 2) {
						System.out.println("\nSe traducirá del Ingles al frances\n Ingrese el texto");
						String palabraIng = scan.nextLine();

						System.out.println(traducirOracion("frances", searchIng, palabraIng));
					} else {
						System.out.println("\n Opci�n no valida");
					}

				} else if (opcion == 2) {

					System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Ingles\n2.Frances");
					String submenu = scan.nextLine();
					int subopcion = Integer.parseInt(submenu);

					if (subopcion == 1) {
						System.out.println("\nSe traducirá del Español al ingles\nIngrese el texto");
						String palabraEsp = scan.nextLine();

						System.out.println(traducirOracion("ingles", searchEsp, palabraEsp));
					}
					if (subopcion == 2) {
						System.out.println("\nSe traducirá del Español al frances\nIngrese el texto");
						String palabraEsp = scan.nextLine();

						System.out.println(traducirOracion("frances", searchEsp, palabraEsp));
					} else {
						System.out.println("\nOpci�n no valida");
					}

				} else if (opcion == 3) {

					System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Ingles\n2.Español");
					String submenu = scan.nextLine();
					int subopcion = Integer.parseInt(submenu);

					if (subopcion == 1) {
						System.out.println("\nSe traducirá del Frances al ingles\nIngrese el texto");
						String palabraFra = scan.nextLine();

						System.out.println(traducirOracion("ingles", searchFra, palabraFra));
					}
					if (subopcion == 2) {
						System.out.println("\n Se traducirá del Frances al español\nIngrese el texto");
						String palabraFra = scan.nextLine();

						System.out.println(traducirOracion("espanol", searchFra, palabraFra));
					} else {
						System.out.println("\n Opcion no valida");
					}

				} else if (opcion == 4) {

					System.exit(0);

				} else {
					System.out.println("\n Opci�n no valida");
				}

			} catch (Exception e) {
				System.out.println(e.toString());
				System.out.println("\n Opci�n no valida");
			}

		} while (opcion != 4);

	}
	
	private static String convertObjectArrayToString(Object[] arr, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : arr)
			sb.append(obj.toString()).append(delimiter);
		return sb.substring(0, sb.length() - 1);

	}

	public static String traducirOracion(String traducirA, BinarySearchTree<String> BST, String oracion) {
		String[] separacion = oracion.split(" ");
		String [] palabrasTraducidas = new String[separacion.length];
		for (int i = 0; i < separacion.length; i++) {
			Traduccion traduccionOpc = BST.getTraduccion(separacion[i]);
			String palabraTraducida = null;
			
			if(traduccionOpc == null) {
				
				palabraTraducida=	"*"+separacion[i]+"*";
			}

			else if (traducirA == "ingles") {
				palabraTraducida= traduccionOpc.traduccionIng;
			} else if (traducirA == "espanol") {
				palabraTraducida= traduccionOpc.traduccionEsp;
			}

			else if (traducirA == "frances") {
				palabraTraducida= traduccionOpc.traduccionFra;
			}
			
		
			
			palabrasTraducidas[i] = palabraTraducida;
			
			

		}
	
		return convertObjectArrayToString(palabrasTraducidas, " ");
	}

}
