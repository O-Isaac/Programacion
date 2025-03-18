/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.editordearchivos.logica;

import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author usumaniana
 */
public class JGUIController {
    private final JFileChooser JCHOOSER = new JFileChooser();
    private Component component;
    
    
    public JGUIController(Component componentRoot) {
        this.component = componentRoot;
        setFiltersForChooser();
    }
    
    private void setFiltersForChooser() {
        JCHOOSER.setAcceptAllFileFilterUsed(false);
        JCHOOSER.addChoosableFileFilter(new FileNameExtensionFilter("Textos", "txt", "docx"));
        JCHOOSER.addChoosableFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "gif"));
    }
    
    /**
     * Enum para controlar los tipos de mensajes dado para show alert
     */
    public enum AlertType {
        ERROR(JOptionPane.ERROR_MESSAGE),
        INFORMATION_MESSAGE(JOptionPane.INFORMATION_MESSAGE),
        WARNING_MESSAGE(JOptionPane.WARNING_MESSAGE),
        QUESTION_MESSAGE(JOptionPane.QUESTION_MESSAGE),;
        
        private int messageType;
        
        AlertType(int messageType) {
            this.messageType = messageType;
        }
        
        public int getMessageType() {
            return this.messageType;
        }
    }
    
    /**
     * Muestra una alerta 
     * @param title
     * @param description
     * @param type 
     */
    public void showAlert(String title, String description, AlertType type) {
        JOptionPane.showMessageDialog(
            component, 
            description, 
            title, 
            type.getMessageType()
        );
    }
    
    /**
     * Muestra un dialogo para elegir un archivo
     * @return El archivo selecionado por el usuario
     */
    public File getFileFromDialog() {
        return JCHOOSER.showOpenDialog(component) == JFileChooser.APPROVE_OPTION ? 
            JCHOOSER.getSelectedFile() : null;
    }
    
    /**
     * Muestra el dialogo para guardar un archivo
     * @return El archivo que va ha ser guardado
     */
    public File GetFileFromSaveDialog() {
        return JCHOOSER.showSaveDialog(component) == JFileChooser.APPROVE_OPTION ? 
            JCHOOSER.getSelectedFile() : null;
    }
    
    /**
     * Muestra al usuario un dialogo de si/no
     * @param title titulo del dialogo
     * @param content descripcion del contenido
     * @return true/false opcion elegida por  el usuario.
     */
    public boolean getBooleanFromDialog(String title, String content) {
        return JOptionPane.showConfirmDialog(
            component, 
            content, title,
            JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION;
    }
    
    
    
}


