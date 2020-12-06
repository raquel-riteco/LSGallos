import java.util.ArrayList;
import java.util.Random;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;

    protected ArrayList<String> aparellaments;
    //protected String batallesEnCurs[];
    //protected Tema temes[];

    public Batalla () {
        temas = new ArrayList<>();
    }

    public Batalla (String idiomaNatiu, String idiomaAngles) {
        this.idiomaNatiu = idiomaNatiu;
        this.idiomaAngles = idiomaAngles;
        temas = new ArrayList<>();

    }

    public void setTemas(Tema tema) {
        temas.add(tema);
    }

    public ArrayList<Tema> getTemas() {
        return temas;
    }

    @Override
    public String toString() {
        return "Batalla{" +
                "\nidiomaNatiu='" + idiomaNatiu + '\'' +
                "\nidiomaAngles='" + idiomaAngles + '\'' +
                "\ntemas=" + temas +
                '}';
    }






    public String generarAparellaments(ArrayList<Rapero> raperos){

        ArrayList<String> aparellaments = new ArrayList<>();

            raperos.remove(rapero_out);

            Random rand = new Random();

        if((raperos.size()%2)!=0){

            int rapero_out = rand.nextInt(raperos.length-1);

        }

        for(i=)


    }
/*
    public void simular(){



    }

    public String escollirTemaEntrada(Tema temes[]){



    }

    public Rapero batallar(String batallesEnCurs[]){



    }

    public float calcularPuntuacio(String frase){



    }

     */
}
