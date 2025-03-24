public class Pessoa {
    private String nome;
    private String dataDeNascimento;
    private double altura;
    private double peso;

    public Pessoa(String nome, String dataDeNascimento,  double altura, double peso) {
        this.dataDeNascimento = dataDeNascimento;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
    }

    //Faço construtor vazio?

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

    public String toString() {
        return String.format("Pessoa: %s, Data de Nascimento: %s, Altura: %.2f, Peso: %.2f", nome, dataDeNascimento, altura, peso);
    }
}
