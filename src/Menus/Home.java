import types.Menu;

public class Home extends Menu {

    public Home() {
        super("home", getTitle());
    }

    public static String getTitle() {
        return """
                Bem vindo, selecione uma opção abaixo;
                """;
    }

    
}
