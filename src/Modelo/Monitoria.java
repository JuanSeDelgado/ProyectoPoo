/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author Diego Alejandro
 */
public class Monitoria {
    
    private String materia, tema;
    private LocalDate fechaInicio,fechaFinal;
    private Estudiante suEstudiante;

    public Monitoria() {
    }
    
    

    public Monitoria(String tema, LocalDate fechaInicio, LocalDate fechaFinal, Estudiante suEstudiante) {
        this.tema = tema;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.suEstudiante = suEstudiante;
    }

    public Estudiante getSuEstudiante() {
        return suEstudiante;
    }

    public void setSuEstudiante(Estudiante suEstudiante) {
        this.suEstudiante = suEstudiante;
    }

    

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


}
