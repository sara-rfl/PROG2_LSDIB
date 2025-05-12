package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import interfaces.Classificavel;
import util.ClassificadorPaciente;

/**
 * A classe entidades.Paciente representa um paciente com informações pessoais,
 * dados de saúde e implementa as 'interfaces' {@code Comparable<entidades.Paciente>} e {@code interfaces.Classificavel}.
 * O paciente possui informações como altura, peso, e listas para armazenar
 * frequências cardíacas, temperaturas e saturações de oxigénio, com seus respetivos horários.
 */
public class Paciente extends Pessoa implements Comparable<Paciente>, Classificavel {
    // Constantes para os limites dos sinais vitais
    public static final double FREQUENCIA_CARDIACA_MIN = 30.0;
    public static final double FREQUENCIA_CARDIACA_MAX = 220.0;
    public static final double TEMPERATURA_MIN = 30.0;
    public static final double TEMPERATURA_MAX = 45.0;
    public static final double SATURACAO_MIN = 70.0;
    public static final double SATURACAO_MAX = 100.0;

    private double altura;
    private double peso;

    private List<Registos> registosFrequencia = new ArrayList<>();
    private List<Registos> registosTemperatura = new ArrayList<>();
    private List<Registos> registosSaturacao = new ArrayList<>();


    /**
     * Construtor da classe entidades.Paciente.
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
        if (frequencia >= FREQUENCIA_CARDIACA_MIN && frequencia <= FREQUENCIA_CARDIACA_MAX) {
            registosFrequencia.add(new Registos(frequencia, data));
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
        if (temperatura >= TEMPERATURA_MIN && temperatura <= TEMPERATURA_MAX) {
            registosTemperatura.add(new Registos(temperatura, data));
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
        if (saturacao >= SATURACAO_MIN && saturacao <= SATURACAO_MAX) {
            registosSaturacao.add(new Registos(saturacao, data));
        }
    }

    /**
     * Retorna a lista de valores de frequência cardíaca registados para o paciente.
     * Os valores são extraídos dos registos completos, contendo data e valor.
     *
     * @return Lista de valores de frequência cardíaca (em bpm)
     */
    public List<Double> getFrequenciasCardiacas() {
        List<Double> valores = new ArrayList<>();
        for (Registos r : registosFrequencia) {
            valores.add(r.getValor());
        }
        return valores;
    }

    /**
     * Retorna a lista de datas e horas em que foram registadas as frequências cardíacas.
     *
     * @return Lista de datas e horas dos registos de frequência cardíaca
     */
    public List<LocalDateTime> getDatasFrequencia() {
        List<LocalDateTime> datas = new ArrayList<>();
        for (Registos r : registosFrequencia) {
            datas.add(r.getData());
        }
        return datas;
    }

    /**
     * Retorna a lista de valores de temperatura corporal registados para o paciente.
     * Os valores são extraídos dos registos completos, contendo data e valor.
     *
     * @return Lista de valores de temperatura (em ºC)
     */
    public List<Double> getTemperaturas() {
        List<Double> valores = new ArrayList<>();
        for (Registos r : registosTemperatura) {
            valores.add(r.getValor());
        }
        return valores;
    }

    /**
     * Retorna a lista de datas e horas em que foram registadas as temperaturas corporais.
     *
     * @return Lista de datas e horas dos registos de temperatura
     */
    public List<LocalDateTime> getDatasTemperatura() {
        List<LocalDateTime> datas = new ArrayList<>();
        for (Registos r : registosTemperatura) {
            datas.add(r.getData());
        }
        return datas;
    }

    /**
     * Retorna a lista de valores de saturação de oxigénio registados para o paciente.
     * Os valores são extraídos dos registos completos, contendo data e valor.
     *
     * @return Lista de valores de saturação de oxigénio (em %)
     */
    public List<Double> getSaturacoesOxigenio() {
        List<Double> valores = new ArrayList<>();
        for (Registos r : registosSaturacao) {
            valores.add(r.getValor());
        }
        return valores;
    }

    /**
     * Retorna a lista de datas e horas em que foram registadas as saturações de oxigénio.
     *
     * @return Lista de datas e horas dos registos de saturação de oxigénio
     */
    public List<LocalDateTime> getDatasSaturacao() {
        List<LocalDateTime> datas = new ArrayList<>();
        for (Registos r : registosSaturacao) {
            datas.add(r.getData());
        }
        return datas;
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
        if (getFrequenciasCardiacas().isEmpty() || getTemperaturas().isEmpty() || getSaturacoesOxigenio().isEmpty()) {
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

    public List<Registos> getFrequenciaCardiaca() {
        return registosFrequencia;
    }

    public List<Registos> getTemperatura() {
        return registosTemperatura;
    }

    public List<Registos> getSaturacaoOxigenio() {
        return registosSaturacao;
    }


}
