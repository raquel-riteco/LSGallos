package proyecto.presentacion;

import edu.salleurl.profile.Profile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CrearPerfil {
    private static Profile profile;
    private static String pais;

    public CrearPerfil (Profile profile, String pais){
        CrearPerfil.profile = profile;
        CrearPerfil.pais = pais;
    }

    public void buscarInfoPais (){
        String paisFormatoRest = "";
        for (int i = 0; i < CrearPerfil.pais.length(); i++){
            if (CrearPerfil.pais.charAt(i) == ' '){
                paisFormatoRest = paisFormatoRest.concat("%20");
            }else{
                paisFormatoRest = paisFormatoRest.concat(String.valueOf(CrearPerfil.pais.charAt(i)));
            }
        }
        try{
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader("https://restcountries.eu/rest/v2/name/United%20States%20of%20America");
            JSONArray aux = (JSONArray) parser.parse(reader);
            JSONObject info = (JSONObject) aux.get(0);
            profile.setFlagUrl((String) info.get("flag"));
            JSONArray idiomas = (JSONArray) info.get("languages");
            for (Object o : idiomas) {
                JSONObject idioma = (JSONObject) o;
                profile.addLanguage((String) idioma.get("name"));
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public void generarPerfil () {
        try{
            profile.writeAndOpen();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
