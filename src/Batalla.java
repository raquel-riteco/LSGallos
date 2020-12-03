import java.util.ArrayList;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;

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

    /*
    protected String batallesEnCurs[];
    protected Tema temes[];


    public String generarAparellaments(Rapero raperos[]){



    }

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
