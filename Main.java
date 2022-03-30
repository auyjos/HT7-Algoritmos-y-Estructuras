import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
   
    public static void main(String[] args) {
        ArrayList<String> lector = Diccionario.getDiccionario();
        BinarySearchTree <String> searchEsp = new BinarySearchTree<String>();
    	BinarySearchTree <String>searchIng= new BinarySearchTree<String>();
    	BinarySearchTree <String>searchFra= new BinarySearchTree<String>();
    	
    	for (int i=0; i< lector.size(); i++) {
    		String[] separacion = lector.get(i).split(",");
    		Traduccion translate = new Traduccion(separacion[0],separacion[1], separacion[2]);
    		
    		searchEsp.add(separacion[1], translate);
    		searchIng.add(separacion[0], translate);
    		searchFra.add(separacion[2], translate);
    		
    	}
    	menu(searchEsp, searchIng, searchFra);
    	
    }

    public  static void menu(BinarySearchTree <String> searchEsp,BinarySearchTree <String> searchIng, BinarySearchTree <String> searchFra){
        int opcion=0;
        
        System.out.println("\n Hola Bienvenido al programa que compite contra Google Translator");
        do{

            
            try{
                System.out.println("\n En qué idioma de origen está lo que desea traducir?\n1.Ingles\n2.Español\n3.Frances\n4.Salir");

                String menu=scan.nextLine();
                opcion=Integer.parseInt(menu);


                if(opcion==1){

                    System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Español\n2.Frances");
                    String submenu=scan.nextLine();
                    int subopcion=Integer.parseInt(submenu);

                    if(subopcion==1){
                        System.out.println("\nSe traducirá del Ingles al español\n Ingrese el texto");
                        String palabraIng=scan.nextLine();
                    
                        Traduccion palabratraducida = searchIng.getTraduccion(palabraIng);
                        System.out.println(palabratraducida.traduccionEsp);
                   	
                    }
                    if(subopcion==2){
                        System.out.println("\nSe traducirá del Ingles al frances\n Ingrese el texto");
                        String palabraIng=scan.nextLine();
                    
                        Traduccion palabratraducida = searchIng.getTraduccion(palabraIng);
                        System.out.println(palabratraducida.traduccionFra);

                    }else{
                        System.out.println("\n Opci�n no valida");
                    }

                }else if(opcion==2){

                    System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Ingles\n2.Frances");
                    String submenu=scan.nextLine();
                    int subopcion=Integer.parseInt(submenu);

                    if(subopcion==1){
                        System.out.println("\nSe traducirá del Español al ingles\nIngrese el texto");
                        String palabraEsp=scan.nextLine();
                    
                        Traduccion palabratraducida = searchEsp.getTraduccion(palabraEsp);
                        System.out.println(palabratraducida.traduccionIng);
                    }
                    if(subopcion==2){
                        System.out.println("\nSe traducirá del Español al frances\nIngrese el texto");
                        String palabraEsp=scan.nextLine();
                    
                        Traduccion palabratraducida = searchEsp.getTraduccion(palabraEsp);
                        System.out.println(palabratraducida.traduccionFra);
                    }else{
                        System.out.println("\nOpci�n no valida");
                    }

                }else if(opcion==3){
                    
                    System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Ingles\n2.Español");
                    String submenu=scan.nextLine();
                    int subopcion=Integer.parseInt(submenu);

                    if(subopcion==1){
                        System.out.println("\nSe traducirá del Frances al ingles\nIngrese el texto");
                        String palabraFra=scan.nextLine();
                    
                        Traduccion palabratraducida = searchEsp.getTraduccion(palabraFra);
                        System.out.println(palabratraducida.traduccionIng);
                    }
                    if(subopcion==2){
                        System.out.println("\n Se traducirá del Frances al español\nIngrese el texto");
                        String palabraFra=scan.nextLine();
                    
                        Traduccion palabratraducida = searchEsp.getTraduccion(palabraFra);
                        System.out.println(palabratraducida.traduccionEsp);
                    }else{
                        System.out.println("\n Opcion no valida");
                    }


                }else if(opcion==4){

                    System.exit(0);

                }else{
                    System.out.println("\n Opci�n no valida");
                }


            }catch(Exception e){
                System.out.println("\n Opci�n no valida");
            }
            

        }while(opcion!=4);

    }
}
