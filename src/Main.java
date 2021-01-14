

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller("src/JSON/competicio.json", "src/JSON/batalles.json");
        controller.mostrarMenu();
    }
}
