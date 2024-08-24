public abstract class Midia extends Conteudo implements Avaliavel{
    private float nota;

    public Midia(int id, String titulo) {
        super(id, titulo);
    }
    public float getNota() {
        return nota;
    }
    public void setNota(float nota) {
        this.nota = nota;
    }
    @Override
    public String toString() {
        return "Midia [nota=" + nota + ", Id=" + getId() + ", Titulo=" + getTitulo() + "]";
    }



}