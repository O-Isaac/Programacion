/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.proyectosdeflujos.utils;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isaac
 */
public class StdUtils {
    private static final Logger LOG = Logger.getLogger(StdUtils.class.getName());
    
    public static String getLine(Scanner scanner) {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException ex) {
            LOG.log(Level.SEVERE, "Error en la entrada de datos: {0}", ex);
        }

        return null;
    } 
}
