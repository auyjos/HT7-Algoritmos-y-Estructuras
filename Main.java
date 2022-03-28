import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static Diccionario diccionrio = new Diccionario();
    public static void main(String[] args) {
        ArrayList<String> lector = diccionrio.getDiccionario();
        ArrayList<String[]> archivo = new ArrayList<>();
        

        for (String operation: lector) {
            System.out.println("Operación: " + operation );
            String[] separador=operation.split(",");
            archivo.add(separador);
        }


        System.out.println("\n");
        for(int i=0; i<archivo.size();i++){

            System.out.println(archivo.get(i)[0]+" "+archivo.get(i)[1]+" "+archivo.get(i)[2]);


        }

        Menu(archivo);

        System.exit(0);
    }

    /**
     *
     * @return
     
    public static ArrayList<String> getDiccionario() {
        FileDialog dialog = new FileDialog((Frame) null, "Seleccion el archivo con las operaciones");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String dir = dialog.getDirectory() + dialog.getFile();
        System.out.println(dir);

        try {
            File file = new File(dir);

            if (!file.exists()) {
                file.createNewFile();
            }

            try {
                BufferedReader buffer = new BufferedReader(new FileReader(dir));
                ArrayList<String> data = new ArrayList<>();
                String line = buffer.readLine();

                while (line != null) {
                    data.add(line);
                    line = buffer.readLine();
                }

                return data;
            } catch (FileNotFoundException fnte) {
                fnte.printStackTrace();
                return null;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }
*/

    public static void Menu(ArrayList<String[]> archivo){
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
                    }
                    if(subopcion==2){
                        System.out.println("\nSe traducirá del Ingles al frances\n Ingrese el texto");
                    }else{
                        System.out.println("\n Opci�n no valida");
                    }

                }else if(opcion==2){

                    System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Ingles\n2.Frances");
                    String submenu=scan.nextLine();
                    int subopcion=Integer.parseInt(submenu);

                    if(subopcion==1){
                        System.out.println("\nSe traducirá del Español al ingles\nIngrese el texto");
                    }
                    if(subopcion==2){
                        System.out.println("\nSe traducirá del Español al frances\nIngrese el texto");
                    }else{
                        System.out.println("\nTOpcion no valida");
                    }

                }else if(opcion==3){
                    
                    System.out.println("\nA qué idioma de destino desea traducirlo?\n1.Ingles\n2.Español");
                    String submenu=scan.nextLine();
                    int subopcion=Integer.parseInt(submenu);

                    if(subopcion==1){
                        System.out.println("\nSe traducirá del Frances al ingles\nIngrese el texto");
                    }
                    if(subopcion==2){
                        System.out.println("\n Se traducirá del Frances al español\nIngrese el texto");
                    }else{
                        System.out.println("\n Opcion no valida");
                    }


                }else if(opcion==4){

                }else{
                    System.out.println("\n Opcion no valida");
                }


            }catch(Exception e){
                System.out.println("\n Opción no válida");
            }
            

        }while(opcion!=4);

    }
}
