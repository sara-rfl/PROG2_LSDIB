import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FiltroSinaisVitais {

    public static List<Double> filtrarPorData(List<Double> valores, List<LocalDateTime> datas, LocalDate inicio, LocalDate fim) {


        List<Double> filtrados = new ArrayList<>();
        for (int i = 0; i < valores.size(); i++) {
            LocalDate data = datas.get(i).toLocalDate();
            if (!data.isBefore(inicio) && !data.isAfter(fim)) {
                filtrados.add(valores.get(i));
            }
        }
        //retorna os dados filtrados
        return filtrados;
    }


    public static List<Double> obterValoresFiltrados(Paciente paciente, String tipo, LocalDate inicio, LocalDate fim) {
        if (tipo.equals("Frequência Cardíaca")) {
            return filtrarPorData(paciente.getFrequenciasCardiacas(), paciente.getDatasFrequencia(), inicio, fim);
        } else if (tipo.equals("Temperatura")) {
            return filtrarPorData(paciente.getTemperaturas(), paciente.getDatasTemperatura(), inicio, fim);
        } else if (tipo.equals("Saturação de Oxigênio")) {
            return filtrarPorData(paciente.getSaturacoesOxigenio(), paciente.getDatasSaturacao(), inicio, fim);
        }
        return new ArrayList<>();
    }
}
