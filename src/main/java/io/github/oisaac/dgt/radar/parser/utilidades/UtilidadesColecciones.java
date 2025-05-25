package io.github.oisaac.dgt.radar.parser.utilidades;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Clase utilitaria que proporciona métodos para la manipulación y transformación
 * de colecciones, facilitando la conversión de listas a matrices y la construcción
 * de filas de objetos para representaciones tabulares.
 * 
 * <p>Ideal para transformar estructuras de datos complejas en formatos que puedan
 * ser fácilmente consumidos por componentes de interfaz o sistemas que requieran
 * datos tabulares.</p>
 * 
 * @author Isaac
 */
public class UtilidadesColecciones {

    /**
     * Convierte una lista de elementos en una matriz bidimensional de objetos (`Object[][]`),
     * aplicando una función de mapeo que transforma cada elemento en una fila representada
     * como un arreglo de objetos.
     * 
     * <p>Este método es especialmente útil para generar datos en formato tabular, donde
     * cada fila corresponde a un elemento de la lista y cada columna representa un atributo
     * o propiedad derivada de ese elemento.</p>
     * 
     * <pre>{@code
     * List<Persona> personas = ...
     * Object[][] filas = toRow(personas, persona -> row(
     *     persona.getNombre(),
     *     persona.getEdad(),
     *     persona.getCorreo()
     * ));
     * }</pre>
     * 
     * @param <T>    Tipo de los elementos en la lista de entrada.
     * @param lista  Lista de elementos a convertir.
     * @param mapper Función que define cómo transformar cada elemento en un arreglo de objetos (fila).
     * @return Matriz bidimensional donde cada fila representa un elemento transformado de la lista.
     */
    public static <T> Object[][] toRow(ArrayList<T> lista, Function<T, Object[]> mapper) {
        return lista.stream()
                    .map(mapper)
                    .toArray(Object[][]::new);
    }

    /**
     * Método auxiliar para facilitar la creación de filas de objetos dentro del mapeo
     * en el método {@link #toRow(ArrayList, Function)}.
     * 
     * <p>Este método mejora la legibilidad del código al evitar la sintaxis explícita
     * de creación de arrays, permitiendo una construcción más fluida y expresiva.</p>
     * 
     * <pre>{@code
     * toRow(lista, elemento -> new Object[] { elemento });
     * }</pre>
     * 
     * se puede reemplazar por:
     * 
     * <pre>{@code
     * toRow(lista, elemento -> row(elemento));
     * }</pre>
     * 
     * <p>Se recomienda importar este método de forma estática para optimizar su uso:</p>
     * 
     * <pre>{@code
     * import static io.github.oisaac.dgt.radar.parser.utilidades.UtilidadesColecciones.row;
     * }</pre>
     * 
     * @param valores Valores que componen la fila.
     * @return Arreglo de objetos que representa una fila.
     */
    public static Object[] row(Object... valores) {
        return valores;
    }
}
