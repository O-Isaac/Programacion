/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.editordearchivos.logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Isaac
 */
public class JFileController {
    private File file;

    public JFileController(File file) {
        this.file = file;
    }
    
    // Metodos de instancia
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
   // Metodos personalizados
    public String leerFile() throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }
    
    public void escribirArchivo(String content, File file) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(content);
        }
    }
    
    public void escribirArchivo(String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.file))) {
            bw.write(content);
        }
    }
}
