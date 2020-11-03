/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Diego Alejandro
 */
public class EstudiantePregrado extends Estudiante{
    
    private double promedio;

    public EstudiantePregrado(double promedio, String nombre, String programa, int codigo, Monitoria _monitoria) {
        super(nombre, programa, codigo, _monitoria);
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
