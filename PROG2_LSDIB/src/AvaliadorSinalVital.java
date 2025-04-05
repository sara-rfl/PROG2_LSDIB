import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe responsável por classificar os sinais vitais de um paciente
 * com base em valores registados para datas específicas.
 */
public class AvaliadorSinalVital {

    /**
     * Classifica um valor de sinal vital registado numa data específica.
     *
     * @param datas   Lista de datas dos registos
     * @param valores Lista de valores dos sinais vitais
     * @param tipo    Tipo de sinal vital ("FC", "TEMP", "SAT")
     * @return Classificação do valor nesse dia ou mensagem de ausência
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

        // Se nenhum valor encontrado, devolve "Nenhum valor registado"
        return new ClassificacaoComData("Nenhum valor registado", alvo);
    }



    /**
     * Classifica um valor de frequência cardíaca.
     *
     * @param valor Valor da FC
     * @return Classificação ("Normal", "Atenção", "Crítico")
     */
    public static String classificarFrequenciaCardiaca(double valor) {
        if (valor < ClassificadorPaciente.FC_NORMAL_MIN || valor > ClassificadorPaciente.FC_ATENCAO_MAX) return "Crítico";
        if (valor > ClassificadorPaciente.FC_NORMAL_MAX) return "Atenção";
        return "Normal";
    }

    /**
     * Classifica um valor de temperatura corporal.
     *
     * @param valor Valor da temperatura
     * @return Classificação ("Normal", "Atenção", "Crítico")
     */
    public static String classificarTemperatura(double valor) {
        if (valor < ClassificadorPaciente.TEMP_NORMAL_MIN || valor > ClassificadorPaciente.TEMP_ATENCAO_MAX) return "Crítico";
        if (valor > ClassificadorPaciente.TEMP_NORMAL_MAX) return "Atenção";
        return "Normal";
    }

    /**
     * Classifica um valor de saturação de oxigénio.
     *
     * @param valor Valor da saturação
     * @return Classificação ("Normal", "Atenção", "Crítico")
     */
    public static String classificarSaturacao(double valor) {
        if (valor < ClassificadorPaciente.SAT_ATENCAO_MIN) return "Crítico";
        if (valor < ClassificadorPaciente.SAT_NORMAL_MIN) return "Atenção";
        return "Normal";
    }
}

