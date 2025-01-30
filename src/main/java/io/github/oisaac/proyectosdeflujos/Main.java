/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.proyectosdeflujos;

import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author usumaniana
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[] edades = new int[3];
        String[] nombres = new String[3];
        
      
        for (int i = 0; i <= edades.length - 1; i++) {
            System.out.printf("Introduce una edad > ");
            edades[i] = scanner.nextInt();
            
            scanner.nextLine();
            
            System.out.printf("Introduce un nombre > ");
            nombres[i] = scanner.nextLine();
        }
        
        System.out.format("+----------+-------------+-----------+%n");
        System.out.format("| Edad     | Nombre      | Fecha     |%n");
        System.out.format("+----------+-------------+-----------+%n");

        for (int i = 0; i <= edades.length - 1; i++) {
            System.out.printf("| %-8d | %-11s | %3$-9tD |%n", edades[i], nombres[i], LocalDate.now());
        }
        
        System.out.format("+----------+-------------+-----------+%n");    }
}
