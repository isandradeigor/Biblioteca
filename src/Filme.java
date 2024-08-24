import java.time.LocalTime;

public class Filme extends Midia {
    private String diretor;
    private LocalTime duracao;

    public Filme(int id, String titulo, String diretor, LocalTime duracao) {
        super(id, titulo);
        this.diretor = diretor;
        this.duracao = duracao;
    }

    public void avaliar(float nota) throws NotaInvalidaException {
        if (nota >= 0 || nota <= 10) {
            setNota(nota);
        } else {
            throw new NotaInvalidaException("Nota de 0.0 a 10");
        }
    }

    @Override
    public String toString() {
        return "Filme [diretor=" + diretor + ", Id=" + getId() + ", Titulo=" + getTitulo() + ", Duração=" + getDuracao()
                + "]";
    }

    public String getDiretor() {
        return diretor;
    }

    public LocalTime getDuracao() {
        return duracao;
    }

}
