import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe responsável por classificar os sinais vitais de um paciente
 * com base nos valores registados para datas específicas.
 */
public class AvaliadorSinalVital {

    /**
     * Classifica o valor de um sinal vital registado numa data específica.
     * A classificação é feita com base no primeiro registo encontrado que ocorre
     * no próprio dia ou após a data fornecida.
     *
     * @param datas   Lista de datas e horas dos registos
     * @param valores Lista de valores correspondentes aos sinais vitais
     * @param alvo    Data alvo para procurar o registo
     * @param tipo    Tipo de sinal vital: "FC" (frequência cardíaca), "TEMP" (temperatura corporal) ou "SAT" (saturação de oxigénio)
     * @return Um objeto {@code ClassificacaoComData} com a classificação do valor encontrado e a data correspondente,
     *         ou uma mensagem de ausência de registos se nenhum valor for encontrado.
     */
    public static ClassificacaoComData classificarValorEmData(List<LocalDateTime> datas, List<Double> valores, LocalDate alvo, String tipo) {
        for (int i = 0; i < datas.size(); i++) {
            LocalDate dataRegisto = datas.get(i).toLocalDate();

            // Procurar o primeiro registo igual ou posterior ao dia escolhido
            if (!dataRegisto.isBefore(alvo)) {
                double valor = valores.get(i);
                String classificacao = "";

                if (tipo.equals("FC")) classificacao = classificarFrequenciaCardiaca(valor);
                else if (tipo.equals("TEMP")) classificacao = classificarTemperatura(valor);
                else if (tipo.equals("SAT")) classificacao = classificarSaturacao(valor);

                return new ClassificacaoComData(classificacao, dataRegisto);
            }
        }

        // Se nenhum valor for encontrado, devolve "Nenhum valor registado"
        return new ClassificacaoComData("Nenhum valor registado", alvo);
    }

    /**
     * Classifica um valor de frequência cardíaca (FC) com base em limiares definidos.
     *
     * @param valor Valor da frequência cardíaca a classificar
     * @return Classificação como "Normal", "Atenção" ou "Crítico"
     */
    public static String classificarFrequenciaCardiaca(double valor) {
        if (valor < ClassificadorPaciente.FC_NORMAL_MIN || valor > ClassificadorPaciente.FC_ATENCAO_MAX) return "Crítico";
        if (valor > ClassificadorPaciente.FC_NORMAL_MAX) return "Atenção";
        return "Normal";
    }

    /**
     * Classifica um valor de temperatura corporal com base em limiares definidos.
     *
     * @param valor Valor da temperatura a classificar
     * @return Classificação como "Normal", "Atenção" ou "Crítico"
     */
    public static String classificarTemperatura(double valor) {
        if (valor < ClassificadorPaciente.TEMP_NORMAL_MIN || valor > ClassificadorPaciente.TEMP_ATENCAO_MAX) return "Crítico";
        if (valor > ClassificadorPaciente.TEMP_NORMAL_MAX) return "Atenção";
        return "Normal";
    }

    /**
     * Classifica um valor de saturação de oxigénio com base em limiares definidos.
     *
     * @param valor Valor da saturação a classificar
     * @return Classificação como "Normal", "Atenção" ou "Crítico"
     */
    public static String classificarSaturacao(double valor) {
        if (valor < ClassificadorPaciente.SAT_ATENCAO_MIN) return "Crítico";
        if (valor < ClassificadorPaciente.SAT_NORMAL_MIN) return "Atenção";
        return "Normal";
    }
}
