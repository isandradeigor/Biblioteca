import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Estoque {
    private ArrayList<Livro> livros;
    private ArrayList<Filme> filmes;
    private ArrayList<Documento> documentos;

    public Estoque(ArrayList<Livro> livros, ArrayList<Filme> filmes, ArrayList<Documento> documentos) {
        this.livros = livros;
        this.filmes = filmes;
        this.documentos = documentos;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }

    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }

    public void registrarAcervo() {
        try {
            File acervoAtual = new File("acervoAtual.txt");
            acervoAtual.createNewFile();
            FileWriter writer = new FileWriter("acervoAtual.txt");
            writer.write("Livros:\n");
            for (Livro livro : livros) {
                writer.write(livro + "\n");
            }
            writer.write("Filmes:\n");
            for (Filme filme : filmes) {
                writer.write(filme + "\n");
            }
            writer.write("Documentos:\n");
            for (Documento documento : documentos) {
                writer.write(documento + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    @Override
    public String toString() {
        return "Estoque [livros=" + livros + ", filmes=" + filmes + ", documentos=" + documentos + "]";
    }

    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

}
