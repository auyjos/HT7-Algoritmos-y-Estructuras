import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        ArrayList<String> lector = getDiccionario();
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

        System.exit(0);
    }

    /**
     *
     * @return
     */
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

}
