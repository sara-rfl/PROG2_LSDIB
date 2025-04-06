import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitária responsável por preencher listas de pacientes
 * e técnicos de saúde para testes.
 */
public class DadosTeste {

    /**
     * Lista pública contendo objetos {@code Paciente} utilizados nos testes.
     */
    public static List<Paciente> pacientes = new ArrayList<>();

    /**
     * Lista pública contendo objetos {@code TecnicoSaude} utilizados nos testes.
     */
    public static List<TecnicoSaude> tecnicos = new ArrayList<>();

    /**
     * Cria pacientes de teste e adiciona-os à lista {@code pacientes}.
     * Cada paciente recebe medições simuladas de frequência cardíaca, temperatura corporal
     * e saturação de oxigénio.
     */
    public static void criarPacienteTeste() {
        Paciente p1 = new Paciente("João Rodrigues", "25/06/2009", 1.78, 69, GestorPacientes.gerarNovoId());
        p1.addFrequenciaCardiaca(72, LocalDateTime.of(2024, 3, 10, 14, 30));
        p1.addFrequenciaCardiaca(89, LocalDateTime.of(2024, 3, 11, 9, 15));
        p1.addTemperatura(37.5, LocalDateTime.of(2024, 3, 10, 14, 45));
        p1.addTemperatura(37.0, LocalDateTime.of(2024, 3, 11, 10, 30));
        p1.addSaturacaoOxigenio(98.0, LocalDateTime.of(2024, 3, 9, 18, 20));
        pacientes.add(p1);

        Paciente p2 = new Paciente("Pablo Caetano", "05/06/2001", 1.89, 90, GestorPacientes.gerarNovoId());
        p2.addFrequenciaCardiaca(79, LocalDateTime.of(2024, 3, 8, 12, 10));
        p2.addFrequenciaCardiaca(99, LocalDateTime.of(2024, 3, 9, 16, 45));
        p2.addTemperatura(37.9, LocalDateTime.of(2024, 3, 8, 12, 30));
        p2.addTemperatura(37.0, LocalDateTime.of(2024, 3, 9, 17, 10));
        p2.addSaturacaoOxigenio(97.0, LocalDateTime.of(2024, 3, 7, 20, 00));
        pacientes.add(p2);

        Paciente p3 = new Paciente("Julio César", "02/02/1980", 1.94, 95, GestorPacientes.gerarNovoId());
        p2.addFrequenciaCardiaca(115, LocalDateTime.of(2024, 3, 15, 12, 10));
        p2.addFrequenciaCardiaca(98, LocalDateTime.of(2024, 3, 16, 16, 45));
        p2.addTemperatura(38.0, LocalDateTime.of(2024, 3, 15, 12, 30));
        p2.addTemperatura(37.5, LocalDateTime.of(2024, 3, 16, 17, 10));
        p2.addSaturacaoOxigenio(89.0, LocalDateTime.of(2024, 3, 12, 20, 00));
        pacientes.add(p3);
    }

    /**
     * Cria técnicos de saúde de teste e adiciona-os à lista {@code tecnicos}.
     */
    public static void criarTecnicoTeste(){
        TecnicoSaude t1 = new TecnicoSaude("Pietro Alvez", "22/12/1994", "Enfermeiro", GestorTecnicos.gerarNovoId());
        tecnicos.add(t1);

        TecnicoSaude t2 = new TecnicoSaude("Anita Vieira", "12/04/1990", "Médica", GestorTecnicos.gerarNovoId());
        tecnicos.add(t2);
    }
}
