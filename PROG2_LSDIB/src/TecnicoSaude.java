/**
 * A classe TecnicoSaude representa um técnico de saúde, que é uma subclasse de Pessoa.
 * Esta armazena informações sobre a categoria profissional do técnico.
 */
public class TecnicoSaude extends Pessoa {
    private String categoriaProfissional;

    /**
     * Construtor para a classe TecnicoSaude.
     *
     * @param nome o nome do técnico de saúde
     * @param dataDeNascimento a data de nascimento do técnico de saúde
     * @param categoriaProfissional a categoria profissional do técnico de saúde
     * @param id o ID do técnico de saúde
     */
    public TecnicoSaude(String nome, String dataDeNascimento, String categoriaProfissional, int id) {
        super(nome, dataDeNascimento, id);
        this.categoriaProfissional = categoriaProfissional;
    }

    /**
     * Retorna a categoria profissional do técnico de saúde.
     *
     * @return a categoria profissional
     */
    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }

    /**
     * Define a categoria profissional do técnico de saúde.
     *
     * @param categoriaProfissional a categoria profissional a ser definida
     */
    public void setCategoriaProfissional(String categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    /**
     * Retorna uma representação em formato de string do técnico de saúde.
     *
     * @return uma string contendo o nome, ID, data de nascimento e categoria profissional do técnico de saúde
     */
    @Override
    public String toString() {
        return String.format("Tecnico: %s, ID: %d, Data de Nascimento: %s, Categoria Profissional: %s",
                getNome(), getId(), getDataDeNascimento(), getCategoriaProfissional());
    }
}
