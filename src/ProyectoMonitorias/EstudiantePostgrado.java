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
public class EstudiantePostgrado extends Estudiante {
    
    private int semestre;

    public EstudiantePostgrado(int semestre, String nombre, String programa, String codigo) {
        super(nombre, programa, codigo);
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
