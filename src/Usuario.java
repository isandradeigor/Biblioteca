public abstract class Usuario {
    // Informações principais
    private String nome;
    private int id;
    // Informações adicionais
    private String endereco;
    private String dataDeNascimento;
    private String telefone;

    public Usuario(String nome, String endereco, String dataDeNascimento, String telefone) {
        this.nome = nome;
        this.id = (int) (Math.random() * 10);
        this.endereco = endereco;
        this.dataDeNascimento = dataDeNascimento;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", id=" + id + "]";
    }

    public String getEndereco() {
        return endereco;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    

}
