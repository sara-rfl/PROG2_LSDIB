package util;

import management.GestorPacientes;
import management.GestorRegistos;
import model.Paciente;
import model.Registos;

import java.util.List;


public class AlteradorSinaisVitais {

    private GestorPacientes gestorPacientes;
    private GestorRegistos gestorRegistos;

    public AlteradorSinaisVitais(GestorPacientes gestorPacientes, GestorRegistos gestorRegistos) {
        this.gestorPacientes = gestorPacientes;
        this.gestorRegistos = gestorRegistos;
    }



        public void alterarTodos(double percentagem) {
            List<Paciente> pacientes = gestorPacientes.getPacientes();

            for (Paciente paciente : pacientes) {
                for (Registos r : paciente.getFrequenciaCardiaca()) {
                    r.alterarValorPercentagem(percentagem);
                }
                for (Registos r : paciente.getTemperatura()) {
                    r.alterarValorPercentagem(percentagem);
                }
                for (Registos r : paciente.getSaturacaoOxigenio()) {
                    r.alterarValorPercentagem(percentagem);
                }
            }
        }
}