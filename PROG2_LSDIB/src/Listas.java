import java.util.Comparator;
import java.util.List;

public class Listas {
    //metodo que organiza os pacientes por ordem de data de nascimento
    public static void ordenarPacientes(List<Paciente> pacientes) {
        pacientes.sort(Comparator.comparing(Paciente::getDataDeNascimento));
    }
    //metodo que faz a exibição dos pacientes, ja organizados pela sua data de nascimento
    public static void mostrarPacientes(List<Paciente> pacientes) {
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
    }
    //metodo que organiza os tecnicos de saude por ordem alfabetica
    public static void ordenarTecnicos(List<TecnicoSaude> tecnicos) {
        tecnicos.sort(Comparator.comparing(TecnicoSaude::getNome));
    }

    //metodo que faz a exibica, ja organizada por ordem alfabetica
    public static void mostrarTecnicos(List<TecnicoSaude> tecnicos) {
        for (TecnicoSaude tecnico : tecnicos) {
            System.out.println("Nome: " + tecnico.getNome());
            System.out.println("Data de nascimento: " + tecnico.getDataNascimento());
            System.out.println("Categoria profissional: " + tecnico.getCategoriaProfissional());
            System.out.println();
        }
    }
}





