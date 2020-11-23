import java.util.ArrayList;

public class Batalla {

    private String idiomaNatiu;
    private String idiomaAngles;
    private ArrayList<Tema> temas;


    public Batalla (String idiomaNatiu, String idiomaAngles) {
        this.idiomaNatiu = idiomaNatiu;
        this.idiomaAngles = idiomaAngles;
    }

    public void setTemas(String nomTema, int nivel, String barra) {
        Tema tema = new Tema(nomTema, nivel,barra);
        temas.add(tema);
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
