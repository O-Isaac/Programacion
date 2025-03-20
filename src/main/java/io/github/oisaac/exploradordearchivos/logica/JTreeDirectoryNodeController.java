/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.exploradordearchivos.logica;

import java.io.File;

/**
 * Controlador que se encargar de cada nodo del arbol de archivos
 * @author Isaac
 */
public class JTreeDirectoryNodeController {
    private File file;

    public JTreeDirectoryNodeController(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
