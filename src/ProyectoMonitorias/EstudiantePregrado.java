/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoMonitorias;

/**
 *
 * @author 
 */
public class EstudiantePregrado extends Estudiante{
    
    private double promedio;

    public EstudiantePregrado(double promedio, String nombre, String programa, String codigo) {
        super(nombre, programa, codigo);
        this.promedio = promedio;
    }

    public EstudiantePregrado() {
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    
}
