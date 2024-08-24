public abstract class Conteudo {
    private int id;
    private String titulo;

    public Conteudo(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Conteudo [id=" + id + ", titulo=" + titulo + "]";
    }

}
