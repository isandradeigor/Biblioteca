import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMetodos {
    public static Estoque estoque = new Estoque(new ArrayList<Livro>(), new ArrayList<Filme>(),
            new ArrayList<Documento>());

    public static ArrayList<Midia> midiaAlugadaBiblioteca = new ArrayList<Midia>();

    public static String inicioSistema(Scanner scanner) {
        String option;
        do {
            System.out.println("Bem-vindo ao Menu.");
            System.out.println("1. Criar novo usuário!");
            System.out.println("2. Usuários Registrados.");
            System.out.println("3. Sair.");
            option = scanner.nextLine();
        } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
        return option;
    }

    public static void usuariosLogin(ArrayList<Usuario> usuarios, Scanner scanner) {
        System.out.println("Escolha o usuário:");

        for (int i = 0; i < usuarios.size(); i++) {
            String tipoUsuario = "";
            if (usuarios.get(i) instanceof Membro) {
                tipoUsuario = "Membro";
            } else if (usuarios.get(i) instanceof Bibliotecario) {
                tipoUsuario = "Bibliotecario";
            }
            System.out.println((i + 1) + ". " + usuarios.get(i).getNome() + " (" + tipoUsuario + ")");
        }

        System.out.println("Pressione 'b' para voltar.");
        String option2 = scanner.nextLine();
        if (option2.equals("1") || option2.equals("2")) {
            System.out.println("Insira sua senha:");
            bibliotecarioLogado(usuarios, option2, scanner.nextLine(), 4, scanner);
        } else if (option2.equals("b")) {
            // quebra de padrão
        } else {
            membroLogado(usuarios, option2, scanner);
        }
    }

    public static void criarNovoUsuario(ArrayList<Usuario> usuarios, Scanner scanner) {
        // Criar novo usuário
        System.out.println("Criar novo usuário:\n");
        System.out.println("Insira seu nome: ");
        String nomeDoMembro = scanner.nextLine();
        System.out.println("Insira sua data de nascimento: ");
        String dataDeNascimentoDoMembro = scanner.nextLine();
        System.out.println("Insira seu endereço residencial: ");
        String enderecoDoMembro = scanner.nextLine();
        System.out.println("Insira seu telefone: ");
        String telefoneDoMembro = scanner.nextLine();
        Membro novoMembro = new Membro(nomeDoMembro, enderecoDoMembro, dataDeNascimentoDoMembro,
                telefoneDoMembro);
        System.out.println("\nRegistrando suas informações...");
        // Registrar novo membro.
        registrarUsuario(novoMembro);
        usuarios.add(novoMembro);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Erro ao tentar esperar: " + e.getMessage());
        }
        System.out.println("Usuário registrado com sucesso. Seja bem-vindo " + novoMembro.getNome() + "!\n");
    }

    public static void membroLogado(ArrayList<Usuario> usuarios, String option2, Scanner scanner) {
        int contador = Integer.parseInt(option2) - 1;
        System.out.println("Bem-vindo " + usuarios.get(contador).getNome() + ": ");
        System.out.println("1. Perfil");
        System.out.println("2. Estoque");
        System.out.println("3. Alugados");
        System.out.println("Pressione 'b' para voltar.");
        membrosMenu(usuarios, option2, scanner);
    }

    public static void membrosMenu(ArrayList<Usuario> usuarios, String option2, Scanner scanner) {
        int contador = Integer.parseInt(option2) - 1;
        Membro usuarioM = (Membro) usuarios.get(contador);
        switch (scanner.nextLine()) {
            case "1":
                perfil(usuarios.get(contador), scanner);
                membroLogado(usuarios, option2, scanner);
                break;
            case "2":
                estoqueMenuMembro(usuarios, option2, scanner);
                membroLogado(usuarios, option2, scanner);
                break;
            case "3":
                System.out.print("Alugados: [");
                for (Midia x : usuarioM.getAlugados()) {
                    System.out.println(x.getTitulo() + ", ");
                }
                System.out.println("]");
                membroLogado(usuarios, option2, scanner);
                break;
            case "b":
            case "B":
                usuariosLogin(usuarios, scanner);
                break;
            default:
                break;
        }
    }

    public static void perfil(Usuario usuario, Scanner scanner) {
        System.out.println("1. Alterar nome: " + usuario.getNome());
        System.out.println("2. Alterar telefone: " + usuario.getTelefone());
        System.out.println("3. Alterar endereço: " + usuario.getEndereco());
        System.out.println("4. Alterar data de nascimento: " + usuario.getDataDeNascimento());
        System.out.println("Pressione 'b' para voltar.");
        switch (scanner.nextLine()) {
            case "1":
                System.out.println("Digite o novo nome:");
                String novoNome = scanner.nextLine();
                usuario.setNome(novoNome);
                System.out.println("Nome alterado com sucesso para: " + usuario.getNome());
                break;
            case "2":
                System.out.println("Digite o novo telefone:");
                String novoTelefone = scanner.nextLine();
                usuario.setTelefone(novoTelefone);
                System.out.println("Telefone alterado com sucesso para: " + usuario.getTelefone());
                break;
            case "3":
                System.out.println("Digite o novo endereço:");
                String novoEndereco = scanner.nextLine();
                usuario.setEndereco(novoEndereco);
                System.out.println("Endereço alterado com sucesso para: " + usuario.getEndereco());
                break;
            case "4":
                System.out.println("Digite a nova data de nascimento:");
                String novaDataDeNascimento = scanner.nextLine();
                usuario.setDataDeNascimento(novaDataDeNascimento);
                System.out.println("Data de nascimento alterada com sucesso para: " + usuario.getDataDeNascimento());
                break;
            case "b":
            case "B":
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    public static void bibliotecarioLogado(ArrayList<Usuario> usuarios, String option2, String senha, int tentativas,
            Scanner scanner) {
        int contador = Integer.parseInt(option2) - 1;
        Bibliotecario usuariosB = (Bibliotecario) usuarios.get(contador);
        if (senha.equals(usuariosB.getSenha())) {
            System.out.println("Bem-vindo " + usuariosB.getNome() + ": ");
            System.out.println("1. Perfil");
            System.out.println("2. Estoque");
            System.out.println("3. Membros");
            System.out.println("Pressione 'b' para voltar.");
            bibliotecarioMenu(usuarios, option2, scanner);

        } else {
            tentativas -= 1;
            System.out.println("Senha Incorreta. " + tentativas + " restantes.");
            if (tentativas == 0) {
                System.out.println(
                        "Falha ao se conectar ao sistema. Número de tentavidas ultrapassada, sessão encerrada!");
                System.exit(1);
            }
            bibliotecarioLogado(usuarios, option2, scanner.nextLine(), tentativas, scanner);
        }
    }

    public static void bibliotecarioMenu(ArrayList<Usuario> usuarios, String option2, Scanner scanner) {
        int contador = Integer.parseInt(option2) - 1;
        Bibliotecario bibliotecario = (Bibliotecario) usuarios.get(contador);
        switch (scanner.nextLine()) {
            case "1":
                perfil(usuarios.get(contador), scanner);
                bibliotecarioLogado(usuarios, option2, bibliotecario.getSenha(), contador, scanner);
                break;
            case "2":
                estoqueMenuBibliotecario(usuarios, option2, scanner);
                break;
            case "3":
                System.out.print("[");
                for (Usuario x : usuarios) {
                    if (x instanceof Membro) {
                        System.out.print(x.getNome() + ",");
                    }
                }
                System.out.println("]");
                System.out.println("Pressione 'b' para voltar.");
                String quebra = "";
                do {
                    quebra = scanner.nextLine();
                } while (!quebra.equals("b"));
                bibliotecarioLogado(usuarios, option2, bibliotecario.getSenha(), contador, scanner);
                break;
            case "b":
            case "B":
                usuariosLogin(usuarios, scanner);
                break;
            default:
                break;
        }
    }

    public static void estoqueMenuBibliotecario(ArrayList<Usuario> usuarios, String option2, Scanner scanner) {
        System.out.println("Estoque:");
        System.out.println("1. Visualizar Estoque");
        System.out.println("2. Adicionar novos títulos");
        System.out.println("3. Alugados");
        System.out.println("Pressione 'b' para voltar.");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                System.out.println("------------------");
                lerArquivoEExibir("acervoAtual.txt");
                System.out.println("------------------");
                estoqueMenuBibliotecario(usuarios, option2, scanner);
                break;
            case "2":
                adicionarNovosTitulos(usuarios, option2, scanner);
                break;
            case "3":
                System.out.print("Alugados: [");
                for (Midia x : midiaAlugadaBiblioteca) {
                    if (x instanceof Livro) {
                        System.out.print("(Livro) " + x.getTitulo() + ", ");
                    } else if (x instanceof Filme) {
                        System.out.print("(Filme) " + x.getTitulo() + ", ");
                    }
                }
                System.out.println("]");
                estoqueMenuBibliotecario(usuarios, option2, scanner);
                break;
            case "b":
            case "B":
                bibliotecarioLogado(usuarios, option2, "1234", 4, scanner);
                break;
            default:
                break;
        }
    }

    public static void adicionarNovosTitulos(ArrayList<Usuario> usuarios, String option2, Scanner scanner) {
        // Adicionar novos títulos
        Bibliotecario b = (Bibliotecario) usuarios.get(Integer.parseInt(option2) - 1);
        System.out.println("Novos títulos: ");
        System.out.println("------------------");
        lerArquivoEExibir("novoTitulo.txt");
        System.out.println("------------------");
        System.out.println("1. Registrar no estoque.");
        System.out.println("Pressione 'b' para voltar.");
        String option = scanner.nextLine();
        if (option.equals("1")) {
            // code
            b.novoTitulo(estoque);
            estoque.registrarAcervo();
            System.out.println("Títulos registrados com sucesso.");
            estoqueMenuBibliotecario(usuarios, option2, scanner);
        } else if (option.equals("b") || option.equals("B")) {
            estoqueMenuBibliotecario(usuarios, option2, scanner);
        }

    }

    // ESTOQUE MEMBRO

    public static void estoqueMenuMembro(ArrayList<Usuario> usuarios, String option2, Scanner scanner) {

        Membro usuarioM = (Membro) usuarios.get(Integer.parseInt(option2) - 1);

        System.out.println("Estoque:");
        System.out.println("1. Visualizar Estoque");
        System.out.println("2. Alugar Livro");
        System.out.println("3. Alugar Filme");
        System.out.println("4. Devolver");
        System.out.println("Pressione 'b' para voltar.");
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                System.out.println("------------------");
                lerArquivoEExibir("acervoAtual.txt");
                System.out.println("------------------");
                estoqueMenuMembro(usuarios, option2, scanner);
                break;
            case "2":
                // alugar livro
                for (Livro livro : estoque.getLivros()) {
                    System.out.println(livro.getId() + ". " + livro.getTitulo());
                }

                System.out.println("Insira o ID do livro que deseja alugar:");
                int idLivro = Integer.parseInt(scanner.nextLine());
                for (Livro livro : estoque.getLivros()) {
                    if (livro.getId() == idLivro) {
                        try {
                            usuarioM.alugar(estoque, livro);
                            System.out.println("Livro alugado com sucesso.");
                            midiaAlugadaBiblioteca.add(livro);
                        } catch (ForaDeEstoqueException e) {
                            System.out.println("Livro fora de estoque.");
                        }
                        break;
                    }
                }
                break;
            case "3":
                // alugar filme
                for (Filme filme : estoque.getFilmes()) {
                    System.out.println(filme.getId() + ". " + filme.getTitulo());
                }
                int idFilme = Integer.parseInt(scanner.nextLine());
                for (Filme filme : estoque.getFilmes()) {
                    if (filme.getId() == idFilme) {
                        try {
                            usuarioM.alugar(estoque, filme);
                            System.out.println("Filme alugado com sucesso.");
                            // usuarioM.setAlugados(filme);
                            midiaAlugadaBiblioteca.add(filme);
                        } catch (ForaDeEstoqueException e) {
                            System.out.println("Filme fora de estoque.");
                        }
                        break;
                    }
                }
                break;
            case "4":
                // devolver
                for (Midia midia : usuarioM.getAlugados()) {
                    System.out.println(midia.getId() + ". " + midia.getTitulo());
                }

                System.out.println("Insira o ID da mídia que deseja devolver:");
                int idMidia = Integer.parseInt(scanner.nextLine());
                for (Midia midia : usuarioM.getAlugados()) {
                    if (midia.getId() == idMidia) {
                        try {
                            usuarioM.devolver(estoque, midia);
                            System.out.println("Item devolvido com sucesso.");
                            midiaAlugadaBiblioteca.remove(midia);
                        } catch (ItemJaEmEstoqueException e) {
                            System.out.println("Item já em estoque.");
                        }
                        break;
                    }
                }
                break;
            case "b":
            case "B":
                membroLogado(usuarios, option2, scanner);
                break;
            default:
                break;
        }
    }

    // REGISTROS TXT
    public static void registrarUsuario(Usuario usuario) {
        String fileName = "";
        if (usuario instanceof Membro) {
            fileName = "RegistroDeMembros.txt";
        } else if (usuario instanceof Bibliotecario) {
            fileName = "RegistroDeFuncionarios.txt";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Nome: " + usuario.getNome());
            writer.newLine();
            writer.write("Id: " + usuario.getId());
            writer.newLine();
            writer.write("Data de Nascimento: " + usuario.getDataDeNascimento());
            writer.newLine();
            writer.write("Endereço: " + usuario.getEndereco());
            writer.newLine();
            writer.write("Telefone: " + usuario.getTelefone());
            writer.newLine();
            writer.write("------------------------------");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lerArquivoEExibir(String caminhoArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void limparArquivo(String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            // Abrir o arquivo no modo de escrita (append = false) limpa o conteúdo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
