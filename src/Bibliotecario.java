import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Usuario {
    private String senha;

    public Bibliotecario(String nome, String endereco, String dataDeNascimento, String telefone, String senha) {
        super(nome, endereco, dataDeNascimento, telefone);
        this.senha = senha;
    }

    public void novoTitulo(Estoque estoque) {
        String caminhoDoArquivo = "novoTitulo.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoDoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] palavrasDaLinha = linha.split(", ");
                switch (palavrasDaLinha[0]) {
                    case "Livro":
                        Livro livro = new Livro(
                                Integer.parseInt(palavrasDaLinha[1]),
                                palavrasDaLinha[2],
                                palavrasDaLinha[3],
                                Integer.parseInt(palavrasDaLinha[4]));
                        adicionar(estoque, livro);
                        break;
                    case "Filme":
                        Filme filme = new Filme(
                                Integer.parseInt(palavrasDaLinha[1]),
                                palavrasDaLinha[2],
                                palavrasDaLinha[3],
                                LocalTime.parse(palavrasDaLinha[4]));
                        adicionar(estoque, filme);
                        break;
                    case "Documento":
                        Documento documento = new Artigo(
                                Integer.parseInt(palavrasDaLinha[1]),
                                palavrasDaLinha[2],
                                Integer.parseInt(palavrasDaLinha[3]),
                                Integer.parseInt(palavrasDaLinha[4]),
                                new ArrayList<>(List.of(palavrasDaLinha[5].split(";"))),
                                palavrasDaLinha[6]);
                        adicionar(estoque, documento);
                        break;
                    default:
                        System.out.println("Tipo desconhecido: " + palavrasDaLinha[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T extends Conteudo> void adicionar(Estoque estoque, T item) {
        if (item instanceof Livro) {
            estoque.getLivros().add((Livro) item);
        } else if (item instanceof Filme) {
            estoque.getFilmes().add((Filme) item);
        } else if (item instanceof Documento) {
            estoque.getDocumentos().add((Documento) item);
        } else {
            throw new IllegalArgumentException("Item não é livro nem filme");
        }
    }

    public <T extends Conteudo> void remover(Estoque estoque, T item) throws ForaDeEstoqueException {
        if (item instanceof Livro) {
            if (!estoque.getLivros().remove((Livro) item)) {
                throw new ForaDeEstoqueException("Item não encontrado");
            }
        } else if (item instanceof Filme) {
            if (!estoque.getFilmes().remove((Filme) item)) {
                throw new ForaDeEstoqueException("Item não encontrado");
            }
        } else if (item instanceof Documento) {
            if (!estoque.getDocumentos().remove((Documento) item))
                ;
            {
                throw new ForaDeEstoqueException("Acervo não possui documento");
            }
        } else {
            throw new IllegalArgumentException("Item não se em caixa nas categorias");
        }
    }

    @Override
    public String toString() {
        return "Bibliotecario [ Nome=" + getNome() + ", Id=" + getId() + "]";
    }

    public String getSenha() {
        return senha;
    }

}
