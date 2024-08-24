public class Livro extends Midia {
    private String autor;
    private int numDePaginas;

    public Livro(int id, String titulo, String autor, int numDePaginas) {
        super(id, titulo);
        this.autor = autor;
        this.numDePaginas = numDePaginas;
    }

    public void avaliar(float nota) throws NotaInvalidaException {
        if (nota >= 0 || nota <= 5 || nota - (int) nota == 0) {
            setNota(nota);
        } else {
            throw new NotaInvalidaException("Nota de 0 a 5");
        }
    }

    @Override
    public String toString() {
        return "Livro [autor=" + autor + ", id=" + getId() + ", Nota=" + getNota() + ", Titulo=" + getTitulo() + "]";
    }

    public String getAutor() {
        return autor;
    }

    public int getNumDePaginas() {
        return numDePaginas;
    }

}
