import java.util.ArrayList;

public class Artigo extends Documento implements Buscavel {
    private ArrayList<String> palavrasChave;
    private String autor;

    public Artigo(int id, String titulo, int ano, int numDePaginas, ArrayList<String> palavrasChave, String autor) {
        super(id, titulo, ano, numDePaginas);
        this.palavrasChave = palavrasChave;
        this.autor = autor;
    }

    public ArrayList<String> getPalavrasChave() {
        return palavrasChave;
    }

    public void setPalavrasChave(ArrayList<String> palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean buscar(String palavraChave) {
        for (String palavra : palavrasChave) {
            if (palavraChave.equals(palavra)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Artigo [autor=" + autor + ", Id=" + getId() + ", Ano=" + getAno() + ", Titulo="
                + getTitulo() + "]";
    }

}
