/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.dgt.radar.parser.modelos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que se utilizara para serializar y deserializar los datos del programa
 * @author Isaac
 */
public class Datos implements Serializable {
    public static long serialVersionUID = 3L;
    
    private ArrayList<String> lineas = new ArrayList<>();
    private ArrayList<Infracion> infraciones = new ArrayList<>();

    public ArrayList<String> getLineas() {
        return lineas;
    }

    public ArrayList<Infracion> getInfraciones() {
        return infraciones;
    }

    public void setLineas(ArrayList<String> lineas) {
        this.lineas = lineas;
    }

    public void setInfraciones(ArrayList<Infracion> infraciones) {
        this.infraciones = infraciones;
    }
    
    public void setDatos(Datos datos) {
        this.infraciones = datos.getInfraciones();
        this.lineas = datos.getLineas();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    
}
