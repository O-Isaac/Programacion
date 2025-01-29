/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package io.github.oisaac.proyectosdeflujos;

import io.github.oisaac.proyectosdeflujos.controllers.FileController;
import io.github.oisaac.proyectosdeflujos.utils.StdUtils;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import java.nio.file.Path;

/**
 * LS Personal con JAVA
 * @author Isaac
 */
public class ProyectosDeFlujos {
    private static final Logger LOG = Logger.getLogger(ProyectosDeFlujos.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Obtienes path del input del usuarios
        System.out.print("Introduce el path para el archivo: ");
        Path path = Paths.get(StdUtils.getLine(scanner));
        
        // Crear un controlador sobre el path que se ha introducido
        FileController controller = new FileController(path);
    }
}
