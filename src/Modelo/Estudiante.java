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
public class Estudiante {
    
    private String nombre, programa;
    private int codigo;
    private Monitoria _monitoria;

    public Estudiante(String nombre, String programa, int codigo, Monitoria _monitoria) {
        this.nombre = nombre;
        this.programa = programa;
        this.codigo = codigo;
        this._monitoria = _monitoria;
    }

    public Estudiante() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Monitoria getMonitoria() {
        return _monitoria;
    }

    public void setMonitoria(Monitoria _monitoria) {
        this._monitoria = _monitoria;
    }
    
    
    
}
