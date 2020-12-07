
public class Main {
    public static void main(String[] args) {
        CompeticioController controller = new CompeticioController("src/competicio.json", "src/batalles.json");

        controller.mostrarMenu();

    }
}
