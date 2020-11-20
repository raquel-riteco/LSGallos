import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Fitxers {
    private ArrayList<ArrayList> temes;



    public static void llegirBatalles(File batalles) throws FileNotFoundException{
        Scanner s = new Scanner(batalles);
        String linea;
        while (s.hasNextLine()) {
            linea = s.nextLine();
            System.out.println(linea);
        }
        s.close();

    }


}
