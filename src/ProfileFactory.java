import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class ProfileFactory {

    private ArrayList<Pais> paisos;

    public ArrayList<Pais> processarPaisos() {


        Gson gson = new GsonBuilder().create();

        try(Reader lector = new InputStreamReader(new URL("https:///restcountries.eu/rest/v2/all").openStream())){
            Pais llistaPaisos = (JSONObject) jsonParser.parse(lector);





        }
    }
}
