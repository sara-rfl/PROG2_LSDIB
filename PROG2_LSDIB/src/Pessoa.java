import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa implements PessoaInterface, OrdenavelPorData {

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String nome;
    private LocalDate dataDeNascimento;
    private double altura;
    private double peso;
    private int id;

    public Pessoa(String nome, String dataDeNascimento,  double altura, double peso, int id) {
        this.dataDeNascimento = LocalDate.parse(dataDeNascimento, FORMATADOR);
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.id = id;
    }

    //Faço construtor vazio?

    public String getNome() {
        return nome;
    }

    @Override
    public LocalDate getDataNascimento() {
        return dataDeNascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = LocalDate.parse(dataDeNascimento, FORMATADOR);
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

    @Override
    public LocalDate getDataReferencia() {
        return dataDeNascimento;
    }
}
