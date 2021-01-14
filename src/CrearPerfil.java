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

/**
 *  Clase utilizada para la creacion de perfiles, correspondiente con la tercera opcion del menu del lobby
 * */


public class CrearPerfil {
    private Profile profile;
    private static String pais;

    public CrearPerfil (String pais){
        CrearPerfil.pais = pais;
    }

    /**
     *  Metodo para la creacion de un perfil a traves de la clase ProfileFactory
     *
     *  @param ruta String que contiene l ruta a seguir para el guardado del perfil en
     *              un fichero .html
     *  @param rapero Participante del cual se quiere generr un perfil
     * */

    public void setProfile (String ruta, Rapero rapero){
        profile = ProfileFactory.createProfile(ruta, rapero);
    }

    /**
     *  Metodo para la bajada de información desde la web restcountries.
     *
     *  @param paisFormatoRest String que contiene el nombre del pasi en el formato
     *                         rest para la busqueda de este en el webservice.
     *  @return Devuelve un String que contiene toda la informacion acerca del pais.
     * */

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

    /**
     *  Metodo para el guardado de la informacion del pais en el perfil.
     *  Este recorre el array que contiene la informacion y busca las variables a
     *  guardar.
     * */

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

    /**
     *  Metodo que genera el fichero que contiene el perfil y abre este en el navegador
     *  predeterminado para la visualizacon por parte del usuario.
     * */

    public void generarPerfil () {
        try{
            profile.writeAndOpen();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
