import java.util.ArrayList;

public class Membro extends Usuario implements Alugavel {
    private ArrayList<Midia> alugados;

    public Membro(String nome, String endereco, String dataDeNascimento, String telefone) {
        super(nome, endereco, dataDeNascimento, telefone);
        alugados = new ArrayList<Midia>();
    }

    @Override
    public <T extends Midia> void alugar(Estoque estoque, T item) throws ForaDeEstoqueException {
        if (item instanceof Livro) {
            for (Livro livro : estoque.getLivros()) {
                if (livro.getId() == item.getId()) {
                    alugados.add(livro);
                    estoque.getLivros().remove(livro);
                    return;
                }
            }
            throw new ForaDeEstoqueException("Item fora de estoque");
        } else if (item instanceof Filme) {
            for (Filme filme : estoque.getFilmes()) {
                if (filme.getId() == item.getId()) {
                    alugados.add(filme);
                    estoque.getFilmes().remove(filme);
                    return;
                }
            }
            throw new ForaDeEstoqueException("Item fora de estoque");
        } else {
            throw new IllegalArgumentException("Argumento inválido");
        }
    }

    public <T extends Midia> void devolver(Estoque estoque, T item) throws ItemJaEmEstoqueException {
        for (Midia midia : alugados) {
            if (midia.getId() == item.getId()) {
                alugados.remove(midia);
                if (item instanceof Livro) {
                    estoque.getLivros().add((Livro) midia);
                    return;
                } else if (item instanceof Filme) {
                    estoque.getFilmes().add((Filme) midia);
                    return;
                }
            }
        }
        throw new ItemJaEmEstoqueException();
    }

    @Override
    public String toString() {
        return "Membro [alugados=" + alugados + ", Nome=" + getNome() + ", Id=" + getId() + "]";
    }

    public ArrayList<Midia> getAlugados() {
        return alugados;
    }

    public void setAlugados(Midia midia) {
        alugados.add(midia);
    }

}
