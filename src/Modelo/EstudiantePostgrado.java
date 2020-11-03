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
public class EstudiantePostgrado extends Estudiante {
    
    private int semestre;

    public EstudiantePostgrado(int semestre, String nombre, String programa, int codigo, Monitoria _monitoria) {
        super(nombre, programa, codigo, _monitoria);
        this.semestre = semestre;
    }

    public EstudiantePostgrado() {
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    
}
