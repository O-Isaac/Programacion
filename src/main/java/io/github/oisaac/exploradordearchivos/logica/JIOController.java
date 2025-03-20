/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.exploradordearchivos.logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author Isaac
 */
public class JIOController {
    private static final Logger LOG = Logger.getLogger(JIOController.class.getName());
    private File file;
    
    public JIOController(File file) {
        this.file = file;
    }
    
    /**
     * Leer todos bytes de un archivo
     * @return todos los bytes del archivo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public byte[] readBytes() throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }
    
    /**
     * Compara los bytes de dos archivos para saber si son identicos o no
     * @param secondFilePath ruta del segundo archivo a comparar.
     * @return -1 es identico, retorna el byte donde es diferente
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public int compareBytes(File secondFile) throws FileNotFoundException, IOException {
        try(FileInputStream fis = new FileInputStream(file); FileInputStream fis2 = new FileInputStream(secondFile)) {
            int fisByte;
            int fisByte2;
            
            while ((fisByte = fis.read()) != -1 && (fisByte2 = fis2.read()) != -1) {
                if (fisByte != fisByte2)
                    return fisByte;
            }
        }
        
        return -1;
    }
    
    /**
     * Añade contenido a un archivo.
     * @param content Lo que va escribir el archivo
     * @param anexar va ha remplaza el archiovo / añade una nueva linea
     * @return ha sido escrito
     */
    public void write(String content, boolean anexar) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, anexar))) {
            bw.write(content);
        }
    }
    
    public void write(String content, File file, boolean anexar) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, anexar))) {
            bw.write(content);
        }
    }
    
    
    /**
     * Lee el contenido del archivo
     * @return Lineas del archivo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String read() throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder((int) file.length());
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            
            return sb.toString();
        }
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
    
}
