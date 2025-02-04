package io.github.oisaac.pokerest.utils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Utils {
    /**
     * Ejecuta el argumento de la derecha si el argumento de la izquerda no retorna null
     * @param supplier parametro para la get
     * @param set parametro para la funcion set
     */
    public static <T> void replaceIfPresent(Supplier<T> supplier, Consumer<? super T> set) {
        Optional.ofNullable(supplier.get()).ifPresent(set);
    }
}
