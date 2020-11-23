import java.io.*;
import java.util.Date;

public class Fitxer {

    public void llegirCompeticio (String nomCompeticio, Competicio competicio){
        try{
            FileInputStream fileIn = new FileInputStream(nomCompeticio);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            competicio.setNom((String) objectIn.readObject());
            competicio.setDataInici((Date) objectIn.readObject());
            competicio.setDataFinal((Date) objectIn.readObject());
            for (int i = 0; i < 3; i++) {
                competicio.setFases(objectIn.readFloat(), (String) objectIn.readObject());
            }

            System.out.println(competicio.getClass());
            objectIn.close();


        }catch (IOException e){
            System.out.println("IO Exeption: " + e.getMessage());

        }catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());

        }
    }

    public void llegirBatalles(String nomBatalles, Batalla batalla) {
        try{
            FileInputStream fileIn = new FileInputStream(nomBatalles);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            batalla.setTemas((String) objectIn.readObject(), objectIn.readInt(), (String) objectIn.readObject());


            objectIn.close();


        }catch (IOException e){
            System.out.println("IO Exeption: " + e.getMessage());

        }catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());

        }


    }

}
