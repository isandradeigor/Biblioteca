public interface Alugavel {
    <T extends Midia> void alugar(Estoque estoque, T item) throws ForaDeEstoqueException;
    <T extends Midia> void devolver(Estoque estoque, T item) throws ItemJaEmEstoqueException;
}

