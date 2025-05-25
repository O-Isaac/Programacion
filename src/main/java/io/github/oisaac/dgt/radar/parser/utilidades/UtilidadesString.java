package io.github.oisaac.dgt.radar.parser.utilidades;

import io.github.oisaac.dgt.radar.parser.modelos.Infracion;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilidades para el análisis (parsing) y creación de cadenas de texto relacionadas con infracciones.
 * <p>
 * Contiene métodos para extraer objetos {@link Infracion} a partir de líneas de texto
 * siguiendo un formato específico.
 * </p>
 * 
 * @author Isaac
 */
public class UtilidadesString {
    /**
     * Patrón que divide la línea en partes, excluyendo los caracteres especiales #, $, &
     */
    private static final Pattern PATTERN = Pattern.compile("[^#$&]+");
    
    /**
     * Formato esperado para fechas y horas en las cadenas, con patrón {@code dd/MM/yyyy HH:mm:ss}.
     */
    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Parsea una línea de texto para extraer un objeto {@link Infracion}.
     * <p>
     * La línea debe contener cinco partes separadas por los caracteres #, $, & u otros delimitadores
     * que no formen parte del patrón, que se corresponden con:
     * <ol>
     *   <li>Fecha (dd/MM/yyyy)</li>
     *   <li>Hora (HH:mm:ss)</li>
     *   <li>Primer valor numérico (double)</li>
     *   <li>Segundo valor numérico (double)</li>
     *   <li>Descripción (cadena de texto)</li>
     * </ol>
     * Si la línea no cumple con este formato o los valores no pueden convertirse correctamente,
     * el método devuelve {@code null}.
     * </p>
     *
     * @param linea la línea de texto que representa una infracción
     * @return un objeto {@link Infracion} con los datos extraídos, o {@code null} si la línea no es válida
     */
    public static Infracion getInfracionFromLine(String linea) {
        Matcher matcher = PATTERN.matcher(linea);
        
        List<String> matches = matcher.results()
            .map(MatchResult::group)
            .toList();

        if (matches.size() != 5) {
            System.out.println("Saltando línea no válida");
            return null;
        }
        
        try {
            Infracion infracion = new Infracion(
                LocalDateTime.parse(matches.get(0) + " " + matches.get(1), formato),
                Double.parseDouble(matches.get(2)),
                Double.parseDouble(matches.get(3)),
                matches.get(4)
            );
            return infracion;
        } catch (NumberFormatException e) {
            System.out.println(matches);
            System.out.println("Línea: error número no válido");
        } catch (DateTimeParseException e) {
            System.out.println(matches.get(0) + " " + matches.get(1));
            System.out.println("Línea: error fecha no válida");
        }
        
        return null;
    }
}
