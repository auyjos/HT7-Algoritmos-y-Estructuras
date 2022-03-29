import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Diccionario {
    public static ArrayList<String> getDiccionario() {
    	BinarySearchTree search = new BinarySearchTree();
    	
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
                    
                   
                    System.out.println(line);
                
                }

                return data;
            }
            
            catch (FileNotFoundException fnte) {
                fnte.printStackTrace();
                return null;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
      
        /*/
        public boolean SearchWord(String target){
            boolean found = theBinaryTree.contains(word);
            // write the values of "target" and "found" to file (code omitted)
            return found;
            */
    }


}
