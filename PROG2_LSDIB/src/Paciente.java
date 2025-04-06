import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Paciente representa um paciente com informações pessoais,
 * dados de saúde e implementa as 'interfaces' {@code Comparable<Paciente>} e {@code Classificavel}.
 * O paciente possui informações como altura, peso, e listas para armazenar
 * frequências cardíacas, temperaturas e saturações de oxigénio, com seus respetivos horários.
 */
public class Paciente extends Pessoa implements Comparable<Paciente>, Classificavel {

    private double altura;
    private double peso;

    private List<Double> frequenciasCardiacas = new ArrayList<>();
    private List<LocalDateTime> datasFrequencia = new ArrayList<>();

    private List<Double> temperaturas = new ArrayList<>();
    private List<LocalDateTime> datasTemperatura = new ArrayList<>();

    private List<Double> saturacoesOxigenio = new ArrayList<>();
    private List<LocalDateTime> datasSaturacao = new ArrayList<>();

    /**
     * Construtor da classe Paciente.
     *
     * @param nome o nome do paciente
     * @param dataDeNascimento a data de nascimento do paciente no formato dd/MM/yyyy
     * @param altura a altura do paciente
     * @param peso o peso do paciente
     * @param id o identificador único do paciente
     */
    public Paciente(String nome, String dataDeNascimento, double altura, double peso, int id) {
        super(nome, dataDeNascimento, id);
        this.altura = altura;
        this.peso = peso;
    }

    /**
     * Retorna a altura do paciente.
     *
     * @return a altura do paciente
     */
    public double getAltura() {
        return altura;
    }

    /**
     * Define a altura do paciente.
     *
     * @param altura a nova altura do paciente
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }

    /**
     * Retorna o peso do paciente.
     *
     * @return o peso do paciente
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Define o peso do paciente.
     *
     * @param peso o novo peso do paciente
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Adiciona uma frequência cardíaca com a respetiva data ao paciente,
     * se estiver dentro dos limites permitidos.
     *
     * @param frequencia a frequência cardíaca a ser registada
     * @param data a data e hora em que a frequência foi medida
     */
    public void addFrequenciaCardiaca(double frequencia, LocalDateTime data) {
        if (frequencia >= Main.FREQUENCIA_CARDIACA_MIN && frequencia <= Main.FREQUENCIA_CARDIACA_MAX) {
            frequenciasCardiacas.add(frequencia);
            datasFrequencia.add(data);
        }
    }

    /**
     * Adiciona uma temperatura com a respetiva data ao paciente,
     * se estiver dentro dos limites permitidos.
     *
     * @param temperatura a temperatura a ser registada
     * @param data a data e hora em que a temperatura foi medida
     */
    public void addTemperatura(double temperatura, LocalDateTime data) {
        if (temperatura >= Main.TEMPERATURA_MIN && temperatura <= Main.TEMPERATURA_MAX) {
            temperaturas.add(temperatura);
            datasTemperatura.add(data);
        }
    }

    /**
     * Adiciona uma saturação de oxigénio com a respetiva data ao paciente,
     * se estiver dentro dos limites permitidos.
     *
     * @param saturacao a saturação de oxigénio a ser registada
     * @param data a data e hora em que a saturação foi medida
     */
    public void addSaturacaoOxigenio(double saturacao, LocalDateTime data) {
        if (saturacao >= Main.SATURACAO_MIN && saturacao <= Main.SATURACAO_MAX) {
            saturacoesOxigenio.add(saturacao);
            datasSaturacao.add(data);
        }
    }

    /**
     * Retorna a lista de frequências cardíacas registadas para o paciente.
     *
     * @return lista de frequências cardíacas
     */
    public List<Double> getFrequenciasCardiacas() {
        return frequenciasCardiacas;
    }

    /**
     * Retorna a lista de datas em que as frequências cardíacas foram registadas.
     *
     * @return lista de datas das frequências cardíacas
     */
    public List<LocalDateTime> getDatasFrequencia() {
        return datasFrequencia;
    }

    /**
     * Retorna a lista de temperaturas registadas para o paciente.
     *
     * @return lista de temperaturas
     */
    public List<Double> getTemperaturas() {
        return temperaturas;
    }

    /**
     * Retorna a lista de datas em que as temperaturas foram registadas.
     *
     * @return lista de datas das temperaturas
     */
    public List<LocalDateTime> getDatasTemperatura() {
        return datasTemperatura;
    }

    /**
     * Retorna a lista de saturações de oxigênio registadas para o paciente.
     *
     * @return lista de saturações de oxigénio
     */
    public List<Double> getSaturacoesOxigenio() {
        return saturacoesOxigenio;
    }

    /**
     * Retorna a lista de datas em que as saturações de oxigénio foram registadas.
     *
     * @return lista de datas das saturações de oxigénio
     */
    public List<LocalDateTime> getDatasSaturacao() {
        return datasSaturacao;
    }

    /**
     * Devolve a classificação geral do paciente com base nos últimos valores de
     * frequência cardíaca, temperatura e saturação de oxigénio.
     *
     * @return Uma 'string' com a classificação ("Normal", "Atenção", "Crítico") ou
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
