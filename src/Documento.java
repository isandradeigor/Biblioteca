import java.time.LocalDate;

public abstract class Documento extends Conteudo {
    private int ano;
    private int numDePaginas;

    public Documento(int id, String titulo, int ano, int numDePaginas) throws AnoInvalidoException {
        super(id, titulo);
        if (LocalDate.now().getYear() < ano) {
            throw new AnoInvalidoException();
        }
        this.ano = ano;
        this.numDePaginas = numDePaginas;
    }

    public int getAno() {
        return ano;
    }

    public int getNumDePaginas() {
        return numDePaginas;
    }

    @Override
    public String toString() {
        return "Documento [ano=" + ano + ", Id=" + getId() + ", Titulo=" + getTitulo() + "]";
    }

}
