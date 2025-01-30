/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.proyectosdeflujos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author usumaniana
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("Introduce la fecha: ");
        LocalDate localDate = LocalDate.parse(
            scanner.nextLine()
        );
        
        
        
        System.out.format("+----------+-------------+%n");
        System.out.format("| Indice   | Formato     |%n");
        System.out.format("+----------+-------------+%n");

        
        Date date = Date.from(
            localDate.atTime(LocalTime.now()).toInstant(ZoneOffset.UTC)
        );
        
        System.out.printf("| %-8d | %-11tc |%n", 1, date);
        System.out.printf("| %-8d | %-11tD |%n", 1, localDate);

        System.out.format("+----------+-------------+%n");    }
}
