import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa implements PessoaInterface, OrdenavelPorData {

    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String nome;
    private LocalDate dataDeNascimento;
    private int id;

    public Pessoa(String nome, String dataDeNascimento, int id) {
        this.dataDeNascimento = LocalDate.parse(dataDeNascimento, FORMATADOR);
        this.nome = nome;
        this.id = id;
    }

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

    // Criar funções para o construtor ir buscar o ID implementado associado ao paciente
    public int getId() { return id;}

    public void setId(int id) { this.id = id; }

    public String toString() {
        return String.format("Pessoa: %s, ID: %d, Data de Nascimento: %s", nome, id, dataDeNascimento);
    }

    @Override
    public LocalDate getDataReferencia() {
        return dataDeNascimento;
    }
}
