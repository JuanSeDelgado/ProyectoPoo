/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.Duration;
import java.time.LocalDateTime;


/**
 *
 * @author Diego Alejandro
 */
public class Monitoria {
    
    private String materia, tema;
    private LocalDateTime fechaInicio,fechaFinal;
    private Estudiante suEstudiante;

    public Monitoria() {
    }
    

    public Monitoria(String tema, String materia, LocalDateTime fechaInicio, LocalDateTime fechaFinal, Estudiante suEstudiante) {
        this.tema = tema;
        this.materia = materia;
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

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    private static long[] getTime(LocalDateTime fechaDesde, LocalDateTime fechaHasta){
        Duration duracion = Duration.between(fechaHasta, fechaDesde);
        
        long segundos = duracion.getSeconds();
        long minutos = duracion.toMinutes();
        
        return new long[] {minutos,segundos};
 
    }

    public String tiempoMonitoria(){
        long time[] = getTime(fechaInicio, fechaFinal);
        
        String minutos = time[0] + "minutos";
        String segundos = time [1] + "segundos";
        
        String info = minutos + segundos;

        
        return info ;
    }

    @Override
    public String toString() {
        return "Monitoria{" + "materia=" + materia + ", tema=" + tema + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + ", suEstudiante=" + suEstudiante + '}';
    }
    
}
