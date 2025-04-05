/**
 * Classe auxiliar responsável por classificar individualmente
 * valores de sinais vitais como Frequência Cardíaca, Temperatura
 * e Saturação de Oxigénio.
 *
 * Cada método devolve uma String com o nível de risco.
 */
public class ClassificadorSinaisVitais {

    /**
     * Classifica um valor de frequência cardíaca.
     *
     * Regras:
     * - Crítico: < 60 bpm ou > 120 bpm
     * - Atenção: > 100 bpm até 120 bpm
     * - Normal: entre 60 e 100 bpm (inclusive)
     *
     * @param valor Valor da frequência cardíaca em bpm
     * @return String com a classificação
     */
    public static String classificarFrequenciaCardiaca(double valor) {
        if (valor < ClassificadorPaciente.FC_NORMAL_MIN || valor > ClassificadorPaciente.FC_ATENCAO_MAX) {
            return "Crítico - Frequência Cardíaca\n";
        } else if (valor > ClassificadorPaciente.FC_NORMAL_MAX) {
            return "Atenção - Frequência Cardíaca\n";
        } else {
            return "Normal - Frequência Cardíaca\n";
        }
    }

    /**
     * Classifica um valor de temperatura corporal.
     *
     * Regras:
     * - Crítico: < 36.0 ºC ou > 38.5 ºC
     * - Atenção: > 37.5 ºC até 38.5 ºC
     * - Normal: entre 36.0 ºC e 37.5 ºC (inclusive)
     *
     * @param valor Valor da temperatura em graus Celsius
     * @return String com a classificação
     */
    public static String classificarTemperatura(double valor) {
        if (valor < ClassificadorPaciente.TEMP_NORMAL_MIN || valor > ClassificadorPaciente.TEMP_ATENCAO_MAX) {
            return "Crítico - Temperatura\n";
        } else if (valor > ClassificadorPaciente.TEMP_NORMAL_MAX) {
            return "Atenção - Temperatura\n";
        } else {
            return "Normal - Temperatura\n";
        }
    }

    /**
     * Classifica um valor de saturação de oxigénio no sangue.
     *
     * Regras:
     * - Crítico: < 90%
     * - Atenção: >= 90% e < 95%
     * - Normal: >= 95%
     *
     * @param valor Valor da saturação em percentagem
     * @return String com a classificação
     */
    public static String classificarSaturacao(double valor) {
        if (valor < ClassificadorPaciente.SAT_ATENCAO_MIN) {
            return "Crítico - Saturação de Oxigénio\n";
        } else if (valor < ClassificadorPaciente.SAT_NORMAL_MIN) {
            return "Atenção - Saturação de Oxigénio\n";
        } else {
            return "Normal - Saturação de Oxigénio\n";
        }
    }
}
