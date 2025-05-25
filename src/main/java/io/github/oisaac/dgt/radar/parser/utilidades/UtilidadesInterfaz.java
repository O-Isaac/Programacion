package io.github.oisaac.dgt.radar.parser.utilidades;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * Proporciona utilidades para interacción con el usuario mediante interfaces gráficas,
 * facilitando la selección de archivos y directorios, así como la presentación de mensajes y tablas.
 * <p>
 * Esta clase encapsula componentes Swing para simplificar diálogos comunes en aplicaciones Java.
 * </p>
 * 
 * @author Isaac
 */
public class UtilidadesInterfaz {
    
    private JFileChooser chooser = new JFileChooser();
    private Component rootComponent;
    
    /**
     * Enum que define los tipos de mensajes para los diálogos de alerta.
     */
    public static enum MESSAGE_TYPE {
        INFORMACION(JOptionPane.INFORMATION_MESSAGE),
        ERROR(JOptionPane.ERROR_MESSAGE),
        AVISO(JOptionPane.WARNING_MESSAGE);

        private final int tipo; 
        
        MESSAGE_TYPE(int tipo) {
            this.tipo = tipo;
        }
        
        /**
         * Obtiene el código entero correspondiente al tipo de mensaje en JOptionPane.
         * 
         * @return el código entero para el tipo de mensaje.
         */
        public int getType() {
            return this.tipo;
        }
    }
    
    /**
     * Crea una instancia de UtilidadesInterfaz asociada a un componente raíz,
     * que servirá como padre para los diálogos modales.
     * 
     * @param rootComponent componente padre (por ejemplo, JFrame o JInternalFrame).
     */
    public UtilidadesInterfaz(Component rootComponent) {
        this.rootComponent = rootComponent;
    }
    
    /**
     * Crea una instancia de UtilidadesInterfaz sin componente padre asociado.
     * Los diálogos se mostrarán sin un componente raíz específico.
     */
    public UtilidadesInterfaz() {
    }
    
    /**
     * Muestra un diálogo para que el usuario seleccione un directorio.
     * El método bloquea la ejecución hasta que el usuario realiza una selección o cancela.
     * 
     * @return el directorio seleccionado, o {@code null} si se canceló la operación.
     */
    public File openDirectory() {
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        return chooser.showOpenDialog(rootComponent) == JFileChooser.APPROVE_OPTION ?
            chooser.getSelectedFile() : null;
    }
    
    /**
     * Muestra un diálogo para que el usuario seleccione un archivo.
     * El método bloquea la ejecución hasta que el usuario realiza una selección o cancela.
     * 
     * @return el archivo seleccionado, o {@code null} si se canceló la operación.
     * 
     * <p><b>Nota:</b> Se recomienda extender este método para incluir el tipo MIME o filtro de archivos.</p>
     */
    public File openFile() {
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return chooser.showOpenDialog(rootComponent) == JFileChooser.APPROVE_OPTION ?
            chooser.getSelectedFile() : null;
    }
    
    /**
     * Muestra un diálogo para seleccionar un archivo y retorna su contenido leído como lista de líneas.
     * 
     * @return una lista de cadenas con las líneas del archivo seleccionado, o una lista vacía si no se seleccionó archivo o hubo un error.
     */
    public ArrayList<String> openAndReadFile() {
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(rootComponent) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = chooser.getSelectedFile();
                return UtilidadesIO.read(file, true);
            } catch (IOException ex) {
                System.err.println("Error de E/S al leer el archivo: " + ex.getMessage());
            }
        }
        return new ArrayList<>();
    }
    
    /**
     * Muestra un diálogo para que el usuario seleccione la ubicación y nombre de un archivo a guardar.
     * 
     * @return el archivo seleccionado para guardar, o {@code null} si se cancela la operación.
     */
    public File saveFile() {
        return chooser.showSaveDialog(rootComponent) == JFileChooser.APPROVE_OPTION ?
            chooser.getSelectedFile() : null;
    }
    
    /**
     * Muestra un cuadro de diálogo con una pregunta de tipo Sí/No/Cancelar.
     * 
     * @param title   el título del cuadro de diálogo.
     * @param message el mensaje que se mostrará al usuario.
     * @return {@code true} si el usuario seleccionó "Sí", {@code false} en caso contrario.
     */
    public boolean prompt(String title, String message) {
        return JOptionPane.showConfirmDialog(rootComponent, message, title, JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION;
    }
    
    /**
     * Muestra un cuadro de diálogo de alerta con un mensaje y un tipo específico.
     * 
     * @param title   el título del cuadro de diálogo.
     * @param message el mensaje que se mostrará al usuario.
     * @param tipo    el tipo de mensaje (información, error o aviso).
     */
    public void alert(String title, String message, MESSAGE_TYPE tipo) {
        JOptionPane.showMessageDialog(rootComponent, message, title, tipo.getType());
    }
    
    /**
     * Muestra una tabla con datos en una ventana independiente.
     * La tabla es scrollable y la ventana es redimensionable.
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/table/TableRowSorter.html
     * 
     * @param titulo   el título de la ventana.
     * @param columnas los encabezados de las columnas.
     * @param filas    los datos de la tabla en forma de matriz de objetos.
     */
    public void mostrarTabla(String titulo, String[] columnas, Object[][] filas) {
        DefaultTableModel model = new DefaultTableModel(filas, columnas);

        JTable tabla = new JTable(model);
        tabla.setFillsViewportHeight(true);

        // Crear el TableRowSorter para habilitar ordenamiento
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
        tabla.setRowSorter(sorter);

        // Opcional: establecer comparadores personalizados para columnas si es necesario
        // Por ejemplo, para números (si tus datos no son Integer/Double, debes convertirlos):
        /*
            sorter.setComparator(0, new Comparator<String>() {
                public int compare(String s1, String s2) {
                    return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
                }
            });
        */

        JFrame frame = new JFrame(titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(tabla));
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
