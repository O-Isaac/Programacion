package io.github.oisaac.dgt.radar.parser.utilidades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// package io.github.oisaac.exploradordearchivos.logica; ¡CAMBIAR ESTO Y DESCOMENTAR!

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Clase utilitaria para operaciones comunes de entrada/salida (I/O) sobre archivos.
 * Proporciona métodos para leer, escribir, comparar y manipular archivos de manera eficiente.
 * 
 * <p>Incluye funcionalidades para:</p>
 * <ul>
 *   <li>Simulación de limpieza de consola</li>
 *   <li>Lectura y escritura de bytes y texto</li>
 *   <li>Comparación de archivos a nivel de byte</li>
 *   <li>Lectura y escritura en posiciones específicas dentro de un archivo</li>
 * </ul>
 * 
 * <p>Esta clase facilita la gestión de archivos para aplicaciones que requieren manipulación directa y control preciso.</p>
 * 
 * @author Isaac
 */
public class UtilidadesIO {

    /**
     * Simula la limpieza de la consola imprimiendo múltiples líneas en blanco.
     * <p>
     * Este método no limpia realmente el buffer de la consola, sino que desplaza
     * el contenido visible hacia arriba, emulando un efecto de pantalla limpia.
     * </p>
     * <p>
     * El comportamiento puede variar según el entorno (IDE, terminal, sistema operativo).
     * Para una limpieza efectiva en terminales compatibles, se recomienda usar secuencias ANSI
     * o comandos específicos del sistema.
     * </p>
     */
    public final static void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    /**
     * Lee todos los bytes contenidos en un archivo.
     *
     * @param file Archivo del cual se leerán los bytes.
     * @return Arreglo de bytes que contiene todos los datos del archivo.
     * @throws FileNotFoundException Si el archivo no existe.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public static byte[] readBytes(File file) throws FileNotFoundException, IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        }
    }

    /**
     * Compara byte a byte el contenido de dos archivos para determinar si son idénticos.
     *
     * @param file Primer archivo para comparar.
     * @param secondFile Segundo archivo para comparar.
     * @return -1 si los archivos son idénticos; en caso contrario, retorna el valor del byte donde difieren.
     * @throws FileNotFoundException Si alguno de los archivos no existe.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public static int compareBytes(File file, File secondFile) throws FileNotFoundException, IOException {
        try(FileInputStream fis = new FileInputStream(file); FileInputStream fis2 = new FileInputStream(secondFile)) {
            int fisByte;
            int fisByte2;

            while ((fisByte = fis.read()) != -1 && (fisByte2 = fis2.read()) != -1) {
                if (fisByte != fisByte2)
                    return fisByte;
            }

            if (fis.read() != -1 || fis2.read() != -1) {
                return -1; // Diferencia en la longitud de los archivos.
            }
        }
        return -1;
    }

    /**
     * Escribe una cadena de texto en una posición específica dentro de un archivo sin sobrescribir el contenido restante.
     *
     * @param file Archivo donde se realizará la escritura.
     * @param position Posición en bytes dentro del archivo donde se iniciará la escritura.
     * @param text Texto que se escribirá a partir de la posición especificada.
     * @throws IOException Si la posición es inválida o ocurre un error de acceso al archivo.
     */
    public static void writeTextAtPosition(File file, int position, String text) throws IOException {
        byte[] textBytes = text.getBytes(StandardCharsets.UTF_8);
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            if (position >= raf.length()) {
                throw new IOException("La posición especificada excede la longitud del archivo.");
            }
            raf.seek(position);
            raf.write(textBytes);
        }
    }

    /**
     * Escribe contenido en un archivo, permitiendo anexar o sobrescribir según el parámetro indicado.
     *
     * @param content Contenido de texto que será escrito en el archivo.
     * @param file Archivo donde se escribirá el contenido.
     * @param append Indica si el contenido debe anexarse al final del archivo (true) o sobrescribirlo (false).
     * @throws IOException Si ocurre un error durante la escritura.
     */
    public static void write(String content, File file, boolean append) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, append))) {
            bw.write(content);
        }
    }

    /**
     * Lee un segmento de texto desde una posición específica dentro de un archivo.
     *
     * @param file Archivo del cual se leerá el texto.
     * @param position Posición inicial en bytes desde donde se comenzará a leer.
     * @param length Cantidad de bytes a leer.
     * @return Texto leído en el rango especificado, codificado en UTF-8.
     * @throws IOException Si la posición es inválida o ocurre un error durante la lectura.
     */
    public static String readTextAtPosition(File file, int position, int length) throws IOException {
        byte[] buffer = new byte[length];
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            if (position >= raf.length()) {
                throw new IOException("La posición especificada excede la longitud del archivo.");
            }
            raf.seek(position);
            int bytesRead = raf.read(buffer);
            return new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
        }
    }

    /**
     * Lee el contenido completo de un archivo y lo devuelve como una cadena de texto.
     *
     * @param file Archivo que será leído.
     * @return Contenido completo del archivo en forma de cadena.
     * @throws FileNotFoundException Si el archivo no existe.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public static String read(File file) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String systemBreakLine = System.lineSeparator();
            return br.lines().collect(Collectors.joining(systemBreakLine));
        }
    }

    /**
     * Lee todas las líneas de un archivo y las devuelve en una lista de cadenas.
     *
     * @param file Archivo que será leído.
     * @param array Parámetro auxiliar para seleccionar la sobrecarga (sin funcionalidad específica).
     * @return Lista con las líneas del archivo.
     * @throws FileNotFoundException Si el archivo no existe.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public static ArrayList<String> read(File file, boolean array) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            return new ArrayList<>(br.lines().toList());
        }
    }
}
