import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

public class Fitxer {


    public void llegirCompeticio(String nomCompeticio, Competicio competicio){
        try{
            FileReader fr = new FileReader(nomCompeticio);
            BufferedReader br = new BufferedReader(fr);

            competicio.setNom(br.readLine());
            //no se que estoy haciendo aqui
            competicio.setDataInici(DateFormat.getDateInstance().parse(br.readLine()));
            //competicio.setDataFinal();


        }catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO Exception: " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void llegirBatalles(){

        //Funcio

    }

}
