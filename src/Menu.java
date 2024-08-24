
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // USUARIOS
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // REGISTRO BIBLIOTECÁRIO
        Bibliotecario tiaguinho = new Bibliotecario("Tiaguinho", "Rua Militante", "25/02/1881", "8599123123", "1234");

        Bibliotecario paulinho = new Bibliotecario("Paulinho", "Rua Sargento Serafim", "22/05/2003", "8599123123",
                "1234");

        // REGISTRO MEMBROS
        Membro membrotest = new Membro("cirillo", "r", "data", "859960049");

        Membro membrotest2 = new Membro("xuxa", "r", "data2", "859960049");

        // ADICIONANDO USUARIOS
        usuarios.add(tiaguinho);
        usuarios.add(paulinho);
        usuarios.add(membrotest);
        usuarios.add(membrotest2);

        // MENU INICIAL
        String option = "";
        while (true) {
            option = MenuMetodos.inicioSistema(scanner);
            if (option.equals("1")) {
                MenuMetodos.criarNovoUsuario(usuarios, scanner);
            }
            if (option.equals("2")) {
                MenuMetodos.usuariosLogin(usuarios, scanner);
            } else if (option.equals("3")) {
                System.out.println("Sessão encerrada. Aguardamos sua próxima visita!");
                MenuMetodos.limparArquivo("RegistroDeMembros.txt");
                MenuMetodos.limparArquivo("RegistroDeFuncionarios.txt");
                break;
            }
        }
        scanner.close();
    }

}
