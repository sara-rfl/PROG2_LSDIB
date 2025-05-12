package model;

import interfaces.OrdenavelPorData;
import interfaces.PessoaInterface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma pessoa com nome, data de nascimento e identificador.
 * Implementa as 'interfaces' {@code interfaces.PessoaInterface} e {@code interfaces.OrdenavelPorData}.
 */
public class Pessoa implements PessoaInterface, OrdenavelPorData {

    // Formatador para interpretar datas no formato dd/MM/aaaa
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String nome;
    private LocalDate dataDeNascimento;
    private int id;

    /**
     * Construtor da classe entidades.Pessoa.
     *
     * @param nome o nome da pessoa
     * @param dataDeNascimento a data de nascimento da pessoa no formato dd/MM/yyyy
     * @param id o identificador único da pessoa
     */
    public Pessoa(String nome, String dataDeNascimento, int id) {
        this.dataDeNascimento = LocalDate.parse(dataDeNascimento, FORMATADOR);
        this.nome = nome;
        this.id = id;
    }

    /**
     * Retorna o nome da pessoa.
     *
     * @return o nome da pessoa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Implementação do método {@code getDataNascimento} da interface {@code interfaces.PessoaInterface}.
     * Retorna a data de nascimento da pessoa.
     *
     * @return a data de nascimento da pessoa
     */
    @Override
    public LocalDate getDataNascimento() {
        return dataDeNascimento;
    }

    /**
     * Define o nome da pessoa.
     *
     * @param nome o novo nome da pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a data de nascimento da pessoa.
     *
     * @return a data de nascimento da pessoa
     */
    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    /**
     * Define a data de nascimento da pessoa.
     * A data de nascimento deve ser fornecida no formato dd/MM/yyyy.
     *
     * @param dataDeNascimento a nova data de nascimento da pessoa no formato dd/MM/yyyy
     */
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
        return String.format("entidades.Pessoa: %s, ID: %d, Data de Nascimento: %s", nome, id, dataDeNascimento);
    }

    /**
     * Implementação do método da interface {@code interfaces.OrdenavelPorData},
     * que define a data de referência para ordenação.
     *
     * @return Data de nascimento da pessoa
     */
    @Override
    public LocalDate getDataReferencia() {
        return dataDeNascimento;
    }
}
