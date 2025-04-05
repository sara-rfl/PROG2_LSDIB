import java.util.List;

public class Listas {
    //metodo que organiza os pacientes por ordem de data de nascimento
    public static void ordenarPacientes(List<Paciente> pacientes) {
        int n = pacientes.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pacientes.get(j).compareTo(pacientes.get(j + 1)) > 0) {

                    Paciente temp = pacientes.get(j);
                    pacientes.set(j, pacientes.get(j + 1));
                    pacientes.set(j + 1, temp);
                }
            }
        }
    }
    //metodo que faz a exibição dos pacientes, ja organizados pela sua data de nascimento
    public static void mostrarPacientes(List<Paciente> pacientes) {
        for (Paciente p : pacientes) {
            System.out.println(p);
        }
    }
    //metodo que organiza os tecnicos de saude por ordem alfabetica
    public static void ordenarTecnicos(List<TecnicoSaude> tecnicos) {
        int n = tecnicos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (tecnicos.get(j).getNome().compareTo(tecnicos.get(j + 1).getNome()) > 0) {
                    // Troca os técnicos de posição
                    TecnicoSaude temp = tecnicos.get(j);
                    tecnicos.set(j, tecnicos.get(j + 1));
                    tecnicos.set(j + 1, temp);
                }
            }
        }
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





