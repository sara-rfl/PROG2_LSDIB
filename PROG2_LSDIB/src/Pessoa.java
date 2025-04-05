import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma pessoa com nome, data de nascimento e identificador.
 * Implementa as interfaces {@code PessoaInterface} e {@code OrdenavelPorData}.
 */

public class Pessoa implements PessoaInterface, OrdenavelPorData {

    // Formatador para interpretar datas no formato dd/MM/aaaa
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

    /**
     * Obtém o ID da pessoa.
     *
     * @return Identificador único
     */
    public int getId() { return id;}

    /**
     * Define um novo ID para a pessoa.
     *
     * @param id Novo identificador
     */
    public void setId(int id) { this.id = id; }

    public String toString() {
        return String.format("Pessoa: %s, ID: %d, Data de Nascimento: %s", nome, id, dataDeNascimento);
    }

    /**
     * Implementação do metodo da interface {@code OrdenavelPorData},
     * que define a data de referência para ordenação.
     *
     * @return Data de nascimento da pessoa
     */
    @Override
    public LocalDate getDataReferencia() {
        return dataDeNascimento;
    }
}
