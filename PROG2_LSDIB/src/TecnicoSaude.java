public class TecnicoSaude extends Pessoa {
    private String categoriaProfissional;

    public TecnicoSaude(String nome, String dataDeNascimento,  double altura, double peso, String categoriaProfissional) {
        super(nome, dataDeNascimento,  altura, peso);
        this.categoriaProfissional = categoriaProfissional;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
