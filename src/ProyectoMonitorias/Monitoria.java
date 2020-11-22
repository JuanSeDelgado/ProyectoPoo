/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoMonitorias;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Monitoria {

    private String materia, tema, consecutivo;
    private LocalDateTime fechaInicio, fechaFinal;
    private Estudiante suEstudiante;

    public Monitoria() {
    }

    public Monitoria(String materia, String tema, String consecutivo, LocalDateTime fechaInicio, LocalDateTime fechaFinal, Estudiante suEstudiante) {
        this.materia = materia;
        this.tema = tema;
        this.consecutivo = consecutivo;
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

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    private static long getTime(LocalDateTime fechaDesde, LocalDateTime fechaHasta) {
        Duration duracion = Duration.between(fechaHasta, fechaDesde);

        long minutos = duracion.toMinutes();

        return minutos;

    }

    public int tiempoMonitoria() {

        long time = getTime(fechaFinal, fechaInicio);

        int minutos = (int) time;

        return minutos;
    }

    @Override
    public String toString() {
        return "Monitoria{" + "materia=" + materia + ", tema=" + tema + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal + ", suEstudiante=" + suEstudiante + '}';
    }

}
