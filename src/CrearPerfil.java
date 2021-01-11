import edu.salleurl.profile.Profile;

import edu.salleurl.profile.ProfileFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CrearPerfil {
    private Profile profile;
    private static String pais;

    public CrearPerfil (String pais){
        CrearPerfil.pais = pais;
    }

    public void setProfile (String ruta, Rapero rapero){
        profile = ProfileFactory.createProfile(ruta, rapero);
    }

    public String getDataAPI (String paisFormatoRest) throws IOException {
        URL url = new URL("https://restcountries.eu/rest/v2/name/" + paisFormatoRest);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Scanner sc = new Scanner(url.openStream());
        String data = "";
        while (sc.hasNext()){
            data += sc.nextLine();
        }
        sc.close();
        return data;
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
            String data = getDataAPI(paisFormatoRest);
            JSONParser parser = new JSONParser();
            JSONArray aux = (JSONArray) parser.parse(data);
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
