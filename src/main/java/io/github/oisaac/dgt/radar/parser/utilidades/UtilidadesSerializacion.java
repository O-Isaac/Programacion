package io.github.oisaac.dgt.radar.parser.utilidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Proporciona métodos estáticos para serializar y deserializar objetos
 * de manera sencilla, asegurando que las clases tengan definido el campo
 * <code>serialVersionUID</code> para compatibilidad en la serialización.
 * <p>
 * Esta clase facilita guardar objetos serializables en archivos y recuperarlos posteriormente.
 * </p>
 * 
 * @author Isaac
 */
public class UtilidadesSerializacion {
    /**
     * Serializa un objeto y lo guarda en un archivo.
     * <p>
     * Antes de serializar, verifica que la clase del objeto tenga definido
     * el campo <code>serialVersionUID</code> para evitar problemas de compatibilidad.
     * </p>
     * 
     * @param <T>    el tipo del objeto que debe implementar {@link Serializable}
     * @param objeto el objeto a serializar
     * @param file   el archivo donde se guardará el objeto serializado
     * @throws IllegalArgumentException si la clase del objeto no tiene serialVersionUID
     * @throws FileNotFoundException    si el archivo no se puede crear o abrir
     * @throws IOException              si ocurre un error durante la escritura
     */
    public static <T extends Serializable> void save(T objeto, File file) throws FileNotFoundException, IOException {
        if (!tieneSerialVersionUID(objeto.getClass())) {
            throw new IllegalArgumentException("La clase " + objeto.getClass().getName() + " no tiene serialVersionUID.");
        }
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(objeto);
        }
    }
    
    /**
     * Deserializa un objeto desde un archivo y lo devuelve con el tipo especificado.
     * 
     * @param <T>   el tipo del objeto esperado, que debe implementar {@link Serializable}
     * @param file  el archivo desde el que se leerá el objeto serializado
     * @param clazz la clase del tipo esperado para realizar un casteo seguro
     * @return el objeto deserializado
     * @throws FileNotFoundException   si el archivo no existe
     * @throws IOException             si ocurre un error en la lectura
     * @throws ClassNotFoundException  si la clase del objeto no se encuentra en el classpath
     */
    public static <T extends Serializable> T open(File file, Class<T> clazz) 
            throws FileNotFoundException, IOException, ClassNotFoundException {
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return clazz.cast(ois.readObject());
        }
    }
    
    /**
     * Verifica si una clase tiene declarado el campo estático <code>serialVersionUID</code> de tipo <code>long</code>.
     * 
     * @param clazz la clase a verificar
     * @return {@code true} si la clase declara un campo <code>serialVersionUID</code> de tipo <code>long</code>, {@code false} en caso contrario
     */
    private static boolean tieneSerialVersionUID(Class<?> clazz) {
        try {
            Field field = clazz.getDeclaredField("serialVersionUID");
            return field.getType() == long.class;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
}
