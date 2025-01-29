/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.proyectosdeflujos.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de utilidades para manejar fujos
 * @author Isaac
 */
public class StreamUtils {
    private final String PATH;
    
    public StreamUtils(String path) {
        this.PATH = path;
    }
    
    /**
     * Lee un archivo
     * @return Una lista de las lineas del archivo
     * @throws IOException
     */
    public List<String> readFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            return br.lines().toList();
        }
    }
    /**
     * Lee un archivo
     * @param concat Concatena todo en una cadena de texto
     * @return una cadena de texto
     * @throws IOException 
     */
    public String readFile(boolean concat) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            return br.lines().collect(Collectors.joining("\n"));
        }
    }
    
    /**
     * Escribe en un archivo
     * @param content contenido del archivo
     * @param withNewLine añadir una nueva linea despues del contenido
     * @throws IOException 
     */
    public void writeFile(String content, boolean withNewLine) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH))) {
            bw.write(content);
            if (withNewLine) bw.newLine();
        }
    }
}
