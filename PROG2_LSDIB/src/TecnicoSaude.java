public class TecnicoSaude extends Pessoa {
    private String categoriaProfissional;

    public TecnicoSaude(String nome, String dataDeNascimento, String categoriaProfissional, int id) {
        super(nome, dataDeNascimento, id);
        this.categoriaProfissional = categoriaProfissional;
    }

    public String getCategoriaProfissional() {
        return categoriaProfissional;
    }
    public void setCategoriaProfissional(String categoriaProfissional) {
        this.categoriaProfissional = categoriaProfissional;
    }

    @Override
    public String toString() {
        return String.format("Tecnico: %s, ID: %d, Data de Nascimento: %s, Categoria Profissional: %s", getNome(), getId(), getDataDeNascimento(), getCategoriaProfissional());
    }

}
