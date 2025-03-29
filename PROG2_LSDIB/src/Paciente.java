import java.util.ArrayList;
import java.util.List;

public class Paciente extends Pessoa {

    private List<Double> frequenciasCardiacas = new ArrayList<Double>();
    private List<Double> temperaturas = new ArrayList<Double>();
    private List<Double> saturacoesOxigenio = new ArrayList<Double>();

    public Paciente(String nome, String dataDeNascimento, double altura, double peso, int id) {
        super(nome, dataDeNascimento, altura, peso, id);
    }

    public void addFrequenciaCardiaca (double frequenciaCardiaca) {
        if(frequenciaCardiaca >= Main.FREQUENCIA_CARDIACA_MIN && frequenciaCardiaca <= Main.FREQUENCIA_CARDIACA_MAX) {
            frequenciasCardiacas.add(frequenciaCardiaca);
        }
    }

    public void addTemperatura (double temperatura) {
        if(temperatura >= Main.TEMPERATURA_MIN && temperatura <= Main.TEMPERATURA_MAX) {
            temperaturas.add(temperatura);
        }
    }

    public void addSaturacaoOxigenio (double saturacaoOxigenio) {
        if(saturacaoOxigenio >= Main.SATURACAO_MIN && saturacaoOxigenio <= Main.SATURACAO_MAX) {
            saturacoesOxigenio.add(saturacaoOxigenio);
        }
    }

    public List<Double> getFrequenciasCardiacas() {
        return frequenciasCardiacas;
    }

    public List<Double> getTemperaturas() {
        return temperaturas;
    }

    public List<Double> getSaturacoesOxigenio() {
        return saturacoesOxigenio;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
