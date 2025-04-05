import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Paciente extends Pessoa implements Comparable<Paciente>, Classificavel {

    private double altura;
    private double peso;

    private List<Double> frequenciasCardiacas = new ArrayList<>();
    private List<LocalDateTime> datasFrequencia = new ArrayList<>();

    private List<Double> temperaturas = new ArrayList<>();
    private List<LocalDateTime> datasTemperatura = new ArrayList<>();

    private List<Double> saturacoesOxigenio = new ArrayList<>();
    private List<LocalDateTime> datasSaturacao = new ArrayList<>();

    public Paciente(String nome, String dataDeNascimento, double altura, double peso, int id) {
        super(nome, dataDeNascimento, id);
        this.altura = altura;
        this.peso = peso;
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


    public void addFrequenciaCardiaca(double frequencia, LocalDateTime data) {
        if (frequencia >= Main.FREQUENCIA_CARDIACA_MIN && frequencia <= Main.FREQUENCIA_CARDIACA_MAX) {
            frequenciasCardiacas.add(frequencia);
            datasFrequencia.add(data);
        }
    }

    public void addTemperatura(double temperatura, LocalDateTime data) {
        if (temperatura >= Main.TEMPERATURA_MIN && temperatura <= Main.TEMPERATURA_MAX) {
            temperaturas.add(temperatura);
            datasTemperatura.add(data);
        }
    }

    public void addSaturacaoOxigenio(double saturacao, LocalDateTime data) {
        if (saturacao >= Main.SATURACAO_MIN && saturacao <= Main.SATURACAO_MAX) {
            saturacoesOxigenio.add(saturacao);
            datasSaturacao.add(data);
        }
    }

    public List<Double> getFrequenciasCardiacas() {
        return frequenciasCardiacas;
    }

    public List<LocalDateTime> getDatasFrequencia() {
        return datasFrequencia;
    }

    public List<Double> getTemperaturas() {
        return temperaturas;
    }

    public List<LocalDateTime> getDatasTemperatura() {
        return datasTemperatura;
    }

    public List<Double> getSaturacoesOxigenio() {
        return saturacoesOxigenio;
    }

    public List<LocalDateTime> getDatasSaturacao() {
        return datasSaturacao;
    }


    /**
     * Devolve a classificação geral do paciente com base nos últimos valores de
     * frequência cardíaca, temperatura e saturação de oxigénio.
     *
     * @return Uma string com a classificação ("Normal", "Atenção", "Crítico") ou
     *         "Sem dados suficientes" se algum dos sinais vitais estiver em falta.
     */
    @Override
    public String getClassificacao() {
        if (frequenciasCardiacas.isEmpty() || temperaturas.isEmpty() || saturacoesOxigenio.isEmpty()) {
            return "Sem dados suficientes";
        }
        return ClassificadorPaciente.classificarPaciente(this);
    }

    /**
     * Compara dois pacientes com base na sua data de nascimento.
     * Pode ser usado para ordenação por idade (mais velho primeiro).
     *
     * @param o Outro paciente a comparar
     * @return Valor negativo se este paciente for mais velho, positivo se for mais novo, 0 se forem iguais
     */
    @Override
    public int compareTo(Paciente o) {
        return this.getDataDeNascimento().compareTo(o.getDataDeNascimento ());
    }



}
