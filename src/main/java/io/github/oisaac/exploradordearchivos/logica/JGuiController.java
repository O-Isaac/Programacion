/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.exploradordearchivos.logica;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Clase que permite mostrar dialogos para que el usuario interacture con el programa
 * @author Isaac
 */
public class JGuiController {
    private JFileChooser chooser = new JFileChooser();
    private Component rootComponent;

    public JGuiController(Component rootComponent) {
        this.rootComponent = rootComponent;
    }
    
    public File openDirectory() {
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        return chooser.showOpenDialog(rootComponent) == JFileChooser.APPROVE_OPTION ?
            chooser.getSelectedFile() : null;
    }
    
    public File openFile() {
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        return chooser.showOpenDialog(rootComponent) == JFileChooser.APPROVE_OPTION ?
            chooser.getSelectedFile() : null;
    }
    
    public File saveFile() {
        return chooser.showSaveDialog(rootComponent) == JFileChooser.APPROVE_OPTION ?
            chooser.getSelectedFile() : null;
    }
    
    public boolean prompt(String title, String message) {
        return JOptionPane.showConfirmDialog(rootComponent, message, title, JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION;
    }
    
    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(rootComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
