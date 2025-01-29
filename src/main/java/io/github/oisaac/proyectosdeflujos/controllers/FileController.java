/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oisaac.proyectosdeflujos.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Isaac
 */
public class FileController {
    private Path path;
    private static final Logger LOG = Logger.getLogger(FileController.class.getName());

    public FileController(Path path) {
        this.path = path;
    }
    
    /**
     * Retornara todos los archivos del directorio
     * INFO: Este metodo omite los directorios
     * @return lista de todos los archivos
     */
    public List<String> getFiles() {
        try {
            return Files.list(path)
            .filter(file -> !Files.isDirectory(file))
            .map(file -> file.toString())
            .toList();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList();
    }
    
    /**
     * Obtienes todos los directorios de la carpeta
     * @return lista de todos los directorios
     */
    public List<String> getDirectories() {
        try {
            return Files.list(path)
            .filter(file -> Files.isDirectory(file))
            .map(file -> file.toString())
            .toList();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
         
        return new ArrayList();
    }
    
    /**
     * Patro glob para obtener archivos del directorio.
     * @param glob
     * @return 
     */
    public List<Path> glob(String glob) {
        Path main = this.path;
        PathMatcher pathMatcher = FileSystems
            .getDefault()
            .getPathMatcher("glob:" + glob);
        
        List<Path> elements = new ArrayList<>();
        
	try {
            Files.walkFileTree(main, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // Si el directorio coincide con el patrón, también lo agregamos
                    if (pathMatcher.matches(dir) && dir != main) {
                        elements.add(dir);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) elements.add(path);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // Prodiamos capturar la excepcion que ha dado el archivo
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
       
        return elements;
    }
    
    /**
     * List file in nested directory using glob function and file attribute view
     * @param globPattern 
     */
    public void listGlobDirectory(String globPattern) {
        List<Path> globFiles = glob(globPattern);
        
        for (Path file : globFiles) {
            try {
                BasicFileAttributeView attrview = Files.getFileAttributeView(path, BasicFileAttributeView.class);
                BasicFileAttributes attr = attrview.readAttributes();
                System.out.printf("[!] Name=%s, size=%s, absolute=", file.getFileName(), attr.size(), file.toAbsolutePath());
            }            
            catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    /**
     * Elimina recursivamente todos los archivos del directorio!
     **/
    public void fullDelete() {
        List<Path> DirectoriesFiles = glob("*/**");
        
        try {
            for (Path dir : DirectoriesFiles) {
                Files.deleteIfExists(dir);
                LOG.log(Level.INFO, "Path {0} eliminado!", dir);
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error al obtener los archivos del directorio {0}", path);
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    public Path getPath() {
        return path;
    }
}
