/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package io.github.oisaac.dgt.radar.parser.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registro de infracion
 * @author Isaac
 */
public record Infracion(LocalDateTime fecha, double velocidad, double velocidadMaxima, String matricula) implements Serializable {
    public static final long serialVersionUID = 1L;
    
    public static final DateTimeFormatter FORMATO_FECHA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public double multa() {
        double exceso = velocidad - velocidadMaxima;
        
        if (exceso > 40) return 500;
        if (exceso > 20) return 200;
        if (exceso > 5) return 100;
        
        return 0;
    }
    
    public boolean esMatriculaValida() {
        String limpia = matricula.replaceAll("[-\\s]", "").toUpperCase();
        
        if (limpia.matches("\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}")) return true;

        // Matrícula formato antiguo: XX0000XX
        return limpia.matches("[A-Z]{1,3}\\d{4}[A-Z]{1,2}");
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s - %.1f/%.1f km/h - Multa: %.2f€",
                fecha.format(FORMATO_FECHA_HORA), matricula, velocidad, velocidadMaxima, multa());
    }
}
