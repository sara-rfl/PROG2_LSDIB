import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PeriodoAnalise {
    public static LocalDate[] obterPeriodoDeAnalise(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Introduza a data de início (dd/mm/aaaa): ");
                String dataInicioStr = scanner.nextLine();
                LocalDate dataInicio = LocalDate.parse(dataInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                System.out.print("Introduza a data de fim (dd/mm/aaaa): ");
                String dataFimStr = scanner.nextLine();
                LocalDate dataFim = LocalDate.parse(dataFimStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                if (dataFim.isBefore(dataInicio)) {
                    System.out.println("Erro: A data de fim não pode ser anterior à data de início.");
                    continue;
                }

                return new LocalDate[]{dataInicio, dataFim};

            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Introduza no formato dd/mm/aaaa.");
            }
        }
    }

    public static LocalDate[] selecionarPeriodoDeAnalisePaciente(Scanner scanner, Paciente paciente) {
        // mostra os intervalos para cada paciente
        System.out.println("\n|| Datas de Registo para o Paciente " + paciente.getId() + " ||");
        String intervalo = obterIntervaloDeRegistos(paciente);
        System.out.println("Intervalo de registos: " + intervalo + "\n");


        while (true) {
            LocalDate[] periodo = obterPeriodoDeAnalise(scanner);
            LocalDate dataInicio = periodo[0];
            LocalDate dataFim = periodo[1];

            if (temRegistosNoIntervalo(paciente, dataInicio, dataFim)) {
                return periodo;
            } else {
                System.out.println("\nNão existem dados para este período. Introduza novo período de análise.");
            }
        }
    }

    public static LocalDate[] selecionarPeriodoDeAnaliseGrupo(Scanner scanner, List<Paciente> pacientesSelecionados) {
        // Mostra intervalos de registos de cada paciente
        System.out.println("\n|| Intervalos de Registo dos Pacientes Selecionados ||");
        for (Paciente p : pacientesSelecionados) {
            String intervalo = obterIntervaloDeRegistos(p);
            System.out.println("ID " + p.getId() + ": " + intervalo);
        }
        while (true) {
            LocalDate[] periodo = obterPeriodoDeAnalise(scanner);
            LocalDate dataInicio = periodo[0];
            LocalDate dataFim = periodo[1];

            for (Paciente paciente : pacientesSelecionados) {
                if (temRegistosNoIntervalo(paciente, dataInicio, dataFim)) {
                    System.out.println("Calculando medidas de " + dataInicio + " a " + dataFim);
                    return periodo;
                }
            }

            System.out.println("Nenhum dos pacientes tem dados nesse período. Escolha outro.");
        }
    }


    public static boolean temRegistosNoIntervalo(Paciente paciente, LocalDate dataInicio, LocalDate dataFim) {
        return verificaRegistosNoIntervalo(paciente.getDatasFrequencia(), dataInicio, dataFim) ||
                verificaRegistosNoIntervalo(paciente.getDatasTemperatura(), dataInicio, dataFim) ||
                verificaRegistosNoIntervalo(paciente.getDatasSaturacao(), dataInicio, dataFim);
    }

    public static boolean verificaRegistosNoIntervalo(List<LocalDateTime> datas, LocalDate dataInicio, LocalDate dataFim) {
        for (LocalDateTime dataRegisto : datas) {
            LocalDate dataRegistoLocal = dataRegisto.toLocalDate();
            if (!dataRegistoLocal.isBefore(dataInicio) && !dataRegistoLocal.isAfter(dataFim)) {
                return true;
            }
        }
        return false;
    }



    public static String obterIntervaloDeRegistos(Paciente paciente) {
        List<LocalDateTime> todasAsDatas = new ArrayList<>();
        todasAsDatas.addAll(paciente.getDatasFrequencia());
        todasAsDatas.addAll(paciente.getDatasTemperatura());
        todasAsDatas.addAll(paciente.getDatasSaturacao());

        if (todasAsDatas.isEmpty()) {
            return "Sem registos";
        }

        LocalDateTime min = Collections.min(todasAsDatas);
        LocalDateTime max = Collections.max(todasAsDatas);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return min.toLocalDate().format(formatter) + " a " + max.toLocalDate().format(formatter);
    }



}
