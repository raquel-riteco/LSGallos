import javax.json.JsonException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Fitxer f = new Fitxer();
        Competicio competicio = new Competicio();
        try{
            f.llegirCompeticio("src/competicio.json", competicio);
        }catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero de competicion: " + e.getMessage());
        }catch (JsonException e){
            System.out.println("JsonExeption: " + e.getMessage());
        }
        System.out.println(competicio.toString());

    }
}
