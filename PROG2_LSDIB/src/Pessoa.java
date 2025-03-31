public class Pessoa {
    private String nome;
    private String dataDeNascimento;
    private double altura;
    private double peso;
    private int id;

    public Pessoa(String nome, String dataDeNascimento,  double altura, double peso, int id) {
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    // Criar funções para o construtor ir buscar o ID implementado associado ao paciente
    public int getId() { return id;}

    public void setId(int id) { this.id = id; }

    public String toString() {
        return String.format("Pessoa: %s, ID: %d, Data de Nascimento: %s, Altura: %.2f, Peso: %.2f", nome, id, dataDeNascimento, altura, peso);
    }
}
