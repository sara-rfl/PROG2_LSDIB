package util;

import model.Paciente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária responsável por filtrar valores de sinais vitais
 * com base num intervalo de datas.
 */
public class FiltroSinaisVitais {

    /**
     * Filtra os valores de sinais vitais cujas datas correspondentes estão dentro do intervalo definido.
     *
     * @param valores Lista de valores dos sinais vitais
     * @param datas Lista de datas e horas correspondentes aos valores
     * @param inicio Data de início do intervalo (inclusive)
     * @param fim Data de fim do intervalo (inclusive)
     * @return Lista de valores filtrados dentro do intervalo fornecido
     */

    public static List<Double> filtrarPorData(List<Double> valores, List<LocalDateTime> datas, LocalDate inicio, LocalDate fim) {


        List<Double> filtrados = new ArrayList<>();
        for (int i = 0; i < valores.size(); i++) {
            LocalDate data = datas.get(i).toLocalDate();

            // Apenas adiciona o valor se a data estiver dentro do intervalo
            if (!data.isBefore(inicio) && !data.isAfter(fim)) {
                filtrados.add(valores.get(i));
            }
        }
        //retorna os dados filtrados
        return filtrados;
    }

    /**
     * Obtém os valores de sinais vitais de um paciente filtrados por tipo e intervalo de datas.
     *
     * @param paciente entidades.Paciente cujos dados serão analisados
     * @param tipo Tipo de sinal vital ("Frequência Cardíaca", "Temperatura", "Saturação de Oxigênio")
     * @param inicio Data de início do intervalo
     * @param fim Data de fim do intervalo
     * @return Lista de valores do tipo especificado que estão dentro do intervalo de datas
     */
    public static List<Double> obterValoresFiltrados(Paciente paciente, String tipo, LocalDate inicio, LocalDate fim) {
        if (tipo.equals("Frequência Cardíaca")) {
            return filtrarPorData(paciente.getFrequenciasCardiacas(), paciente.getDatasFrequencia(), inicio, fim);
        } else if (tipo.equals("Temperatura")) {
            return filtrarPorData(paciente.getTemperaturas(), paciente.getDatasTemperatura(), inicio, fim);
        } else if (tipo.equals("Saturação de Oxigênio")) {
            return filtrarPorData(paciente.getSaturacoesOxigenio(), paciente.getDatasSaturacao(), inicio, fim);
        }

        // Retorna lista vazia caso o tipo não seja reconhecido
        return new ArrayList<>();
    }
}
